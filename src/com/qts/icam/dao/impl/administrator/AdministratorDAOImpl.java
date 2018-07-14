package com.qts.icam.dao.impl.administrator;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.qts.icam.dao.administrator.AdministratorDAO;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.administrator.AccessType;
import com.qts.icam.model.administrator.Approver;
import com.qts.icam.model.administrator.EmailEventAndTemplate;
import com.qts.icam.model.administrator.ExamForXml;
import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.administrator.PreviousData;
import com.qts.icam.model.administrator.ProfileMatrix;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.administrator.SalaryDetailsForXml;
import com.qts.icam.model.administrator.SalaryForXml;
import com.qts.icam.model.administrator.StaffForXml;
import com.qts.icam.model.administrator.StudentForXml;
import com.qts.icam.model.administrator.SubjectMarksForXml;
import com.qts.icam.model.administrator.SubjectsForXml;
import com.qts.icam.model.administrator.UserGroup;
import com.qts.icam.model.backoffice.LeavePolicy;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.NotificationMedium;
import com.qts.icam.model.common.RepositoryStructure;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.common.TaskDetails;
import com.qts.icam.model.erp.Designation;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.login.LoginForm;
import com.qts.icam.model.survey.Question;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.ticket.TicketStatus;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;

@Repository
public class AdministratorDAOImpl implements AdministratorDAO {

	private final static Logger logger = Logger
			.getLogger(AdministratorDAOImpl.class);
	
	

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Autowired
	EncryptDecrypt encryptDecrypt;
	
	
	// Selects All Modules
	
	@Override
	public List<Module> getmoduleList() throws CustomException {		
		logger.info("Get List of Modules from DB");
		SqlSession session =sqlSessionFactory.openSession();
		List<Module> moduleList=null;
		try{
			moduleList=session.selectList("getmoduleList");			
		}
		catch(Exception e){			
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}	
		return moduleList;
	}


	// Selects All Roles For Module
	
	@Override
	public List<Role> getRolesForModule(String moduleCode) throws CustomException {
		logger.info("Get List of Roles for module from DB");
		SqlSession session =sqlSessionFactory.openSession();
		List<Role> roleList=null;
		try{
			roleList=session.selectList("getRolesForModule",moduleCode);
		}catch(Exception e){
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return roleList;
	}

	// Insert Role  
	
	@Override
	public String addRoles(Role role) throws CustomException  {
		logger.info("insertRoles into DB");
		SqlSession session =sqlSessionFactory.openSession();	
		String statusMessage="FAILURE";
		try{
			role.setObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));			
			int roleCount=session.selectOne("roleCountForModule", role.getModuleCode());
			if(roleCount>0){
				role.setRoleTypeName("MODULE_USER");
			}else{
				role.setRoleTypeName("MODULE_ADMIN");
			}
			int status=session.insert("insertRole", role);
			session.commit();
			if(status>0){
				statusMessage="SUCCESS";
			}
		}catch(Exception e){
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return statusMessage;
	}

	// Inactive Role 
	
	@Override
	public String inActiveRole(Role role) throws CustomException {
		logger.debug("deleteRole from DB");
		SqlSession session =sqlSessionFactory.openSession();
		String statusMessage="FAILED";
		try {				
			int status=session.update("deleteRole", role);
			if(status>0){
				statusMessage="SUCCESS";
			}
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return statusMessage;
	}


	@Override
	public Resource getResourceAndRolesFromDB()throws CustomException {
		logger.info("Get getResourceAndRolesFromDB from DB");
		SqlSession session =sqlSessionFactory.openSession();
		Resource resource = new Resource();
		List<ResourceType> resourceTypeList = null;
		List<Role> roleList = null ;
		try {
			resourceTypeList = session.selectList("selectAllResourceType");
			roleList = session.selectList("selectAllRoleList");
			if (null!=resourceTypeList  &&  resourceTypeList.size() != 0) {
				resource.setResourceTypeList(resourceTypeList);
				resource.setRoleList(roleList);
			}
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}

		return resource;
	}





	@Override
	public String addRoleContactMapping(List<Resource> resourceList)
			throws CustomException {
		logger.info("Get addRoleContactMapping into DB");
		SqlSession session =sqlSessionFactory.openSession();		 
		String statusMessage="FAILED";
		try {
			if(null!=resourceList && resourceList.size()!=0)
			for (Resource resource : resourceList) {
				resource.setObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));				
				int status=session.insert("insertRoleContactMapping", resource);
				session.commit();
				if(status>0){
					statusMessage="SUCCESS";
				}
			}			
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return statusMessage;
	}


	@Override
	public List<Role> getlistRoleContactMapping() throws CustomException {
		logger.info("Get getlistRoleContactMapping into DB");
		SqlSession session =sqlSessionFactory.openSession();
		List<Role> roleList = null;
		try {
			roleList = session.selectList("selectRoleContactMappingList");
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return roleList;
	}

		
	@Override
	public List<Role> searchRoleContactmapping(Map<String, Object> parameters)throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<Role> roleList = null;
		try{				
			roleList=session.selectList("searchRoleContactmapping", parameters);			
		}catch(Exception e){
			CustomException.exceptionHandler(e);
		}
		return roleList;
	}


	@Override
	public List<Resource> getRoleContactMapping(Role role)
			throws CustomException {
		logger.info("Get getRoleContactMapping into DB");
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> resourceList = null;
		try {
			resourceList = session.selectList("getRoleContactMapping", role);
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return resourceList;
	}


	@Override
	public String editRoleContactMapping(List<Resource> resourceList)
			throws CustomException {
		logger.info("Get editRoleContactMapping into DB");
		SqlSession session =sqlSessionFactory.openSession();
		String statusMessage="SUCCESS";
		List<Resource> resourceListFromDB=null;		
		try {			
			if(resourceList.get(0).getRoleName()!=null){
				Role role =new Role();
				role.setRoleName(resourceList.get(0).getRoleName());				
				resourceListFromDB = session.selectList("getRoleContactMapping", role);
			}	
			if(null!=resourceListFromDB && resourceListFromDB.size()!=0){
				for(Resource resourceToDelete : resourceListFromDB){
					resourceToDelete.setUpdatedBy(resourceList.get(0).getUpdatedBy());
					int status= session.update("inactiveRoleContactMapping",resourceToDelete );
					session.commit();
					if(status>0){
						statusMessage="SUCCESS";
					}
				}
			}			
			for(Resource resource:resourceList){				
				resource.setObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));				
				int status=session.update("updateRoleContactMapping",resource);
				session.commit();				
				if(status>=0){
					statusMessage="FAILED";
				}
			}
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return statusMessage;
	}


	@Override
	public List<AccessType> getAccessTypeList() throws CustomException {
		logger.info("getAccessTypeList into DB");
		SqlSession session =sqlSessionFactory.openSession();
		List<AccessType> accessTypeList = null;
		try {
			accessTypeList = session.selectList("getAccessTypeList");
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return accessTypeList;
	}


	@Override
	public String addRoleAccessMapping(AccessType accessType)
			throws CustomException {
		logger.info("addRoleAccessMapping into DB");
		SqlSession session =sqlSessionFactory.openSession();
		String statusMessage="FAILED";
		try {
			accessType.setObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
			int accessTypeInsertStatus=session.insert("insertAccessType", accessType);
			session.commit();
			if(accessTypeInsertStatus>0){
				String accessTypeCode=session.selectOne("lastInsertedAccessType");
				List<Role> roleList = accessType.getRoleList();
				for (Role role : roleList) {
					role.setObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
					role.setAccessTypeName(accessTypeCode);
					int status=session.insert("insertRoleAccessMapping", role);
					session.commit();
					if(status>0){
						statusMessage="SUCCESS";
					}
				}
			}
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return statusMessage;
	}


	@Override
	public AccessType editAccessTypeRoleMapping(AccessType accessType)
			throws CustomException {
		logger.info("editAccessTypeRoleMapping from DB");
		SqlSession session =sqlSessionFactory.openSession();
		AccessType accessTypeDetails = null;
		List<Role> roleList = null;
		try {
			accessTypeDetails=session.selectOne("getDetailsForAccessType", accessType);
			roleList = session.selectList("getAccessTypeRoleMapping",accessType);
			if (null!=roleList && roleList.size() != 0 && null!=accessTypeDetails) {
				accessTypeDetails.setRoleList(roleList);				
			}
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return accessTypeDetails;
	}


	@Override
	public String updateAccessTypeRoleMapping(AccessType accessType)
			throws CustomException {
		logger.info("updateAccessTypeRoleMapping into DB");
		SqlSession session =sqlSessionFactory.openSession();
		String statusMessage="FAILED";
		try {
			int accessTypeUpdateStatus=session.update("updateAccessType", accessType);
			session.commit();			
			if(accessTypeUpdateStatus>0){
				List<Role> roleList = accessType.getRoleList();	
				int inactiveMappingStatus=session.update("updateAccessRoleMapping", accessType);
				session.commit();
				for (Role role : roleList) {						
					if(inactiveMappingStatus>0){
						role.setAccessTypeName(accessType.getAccessTypeCode());// for mapping purpose
						role.setObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));					
						int status=session.insert("insertRoleAccessMapping", role);
						session.commit();
						if(status>0){
							statusMessage="SUCCESS";
						}
					}					
				}
			}
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return statusMessage;
	}


	@Override
	public String inactiveAccessType(AccessType accessType) throws CustomException{
		logger.info("inactiveAccessType from DB");
		SqlSession session =sqlSessionFactory.openSession();
		String statusMessage=null;
		try {			
			session.update("deleteAccessType", accessType);
			statusMessage="SUCCESS";
			
		} catch (Exception e) {
			statusMessage="FAILED";
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return statusMessage;
	}


	@Override
	public Resource getResourceAndAccessFromDB() throws CustomException {
		logger.info("Get getResourceAndAccessFromDB from DB");
		SqlSession session =sqlSessionFactory.openSession();
		Resource resource = new Resource();
		List<ResourceType> resourceTypeList = null;
		List<AccessType> accessTypeList = null;
		try {
			resourceTypeList = session.selectList("getAllResourceType");
			accessTypeList = session.selectList("getAccessTypeList");
			if (null!=resourceTypeList && resourceTypeList.size() != 0){
				resource.setResourceTypeList(resourceTypeList);
			}
			if ( null!=accessTypeList && accessTypeList.size() != 0) {
				
				resource.setAccessTypeList(accessTypeList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return resource;
	}


	@Override
	public String addAccessTypeContactMapping(Resource resource)
			throws CustomException {
		logger.info("Get addAccessTypeContactMapping into DB");
		SqlSession session =sqlSessionFactory.openSession();
		String statusMessage="FAILED";
		try {
			resource.setObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
			int status=session.insert("insertAccessTypeContactMapping", resource);
			session.commit();				
			if(status>0){
				statusMessage="SUCCESS";
			}
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return statusMessage;
	}


	@Override
	public List<Resource> listAccessTypeContactMapping() throws CustomException {
		logger.info("Get listAccessTypeContactMapping  DB");
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> resourceList = null;
		try {
			resourceList = session.selectList("getAccessTypeContactMapping");
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return resourceList;
	}


	@Override
	public List<Resource> accessTypeContactMappingSearch(
			Map<String, Object> parameters) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> resourceList =null;
		try{				
			resourceList=session.selectList("accessTypeContactMappingSearch", parameters);				
		}catch(Exception e){
			CustomException.exceptionHandler(e);
		}
		return resourceList;
	}


	@Override
	public String inactiveAccessTypeContactMapping(String resourceId,String updatedBy) throws CustomException {
		logger.info("Get inactiveAccessTypeContactMapping  DB");
		SqlSession session =sqlSessionFactory.openSession();		
		AccessType accessType = new AccessType();
		String statusMessage="FAILED";
		try {
			String[] str = resourceId.split("~");
			String userId = str[0];
			String accessTypeCode = str[1];
			accessType.setAccessTypeCode(accessTypeCode);
			accessType.setAccessTypeDesc(userId);// Mapping purpose
			accessType.setUpdatedBy(updatedBy);
			int status=session.update("deleteAccessTypeContactMapping", accessType);
			session.commit();	
			if(status>0){
				statusMessage="SUCCESS";
			}
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return statusMessage;
	}


	@Override
	public Module getFunctionalitiesForRole(Role role) throws CustomException {
		logger.info("getRolesAndFunctionalities from DB");
		SqlSession session =sqlSessionFactory.openSession();
		Module module = new Module();
		List<Role> roleList = new ArrayList<Role>();
		try {	
				List <Role> roleDetailsList=session.selectList("getRolesForModule",role.getModuleCode());
				List<Functionality> functionalityListFromDB  = session.selectList("getFunctionalitiesFromRole",role);
				if(null!=functionalityListFromDB && functionalityListFromDB.size()!=0){
					role.setFunctionalityList(functionalityListFromDB);
					if(null!=roleDetailsList && roleDetailsList.size()!=0){
						for(Role roleDetails:roleDetailsList){
							if(roleDetails.getRoleCode().equals(role.getRoleCode())){
								role.setRoleName(roleDetails.getRoleName());
							}
						}
					}
					roleList.add(role);
				}else{
					functionalityListFromDB  = session.selectList("getFunctionalitiesForModule",role);
					if(null!=functionalityListFromDB && functionalityListFromDB.size()!=0){
						for(Functionality functionality:functionalityListFromDB){
							functionality.setView(false);
							functionality.setInsert(false);
							functionality.setUpdate(false);
						}
						role.setFunctionalityList(functionalityListFromDB);
						if(null!=roleDetailsList && roleDetailsList.size()!=0){
							for(Role roleDetails:roleDetailsList){
								if(roleDetails.getRoleCode().equals(role.getRoleCode())){
									role.setRoleName(roleDetails.getRoleName());
								}
							}
						}
						roleList.add(role);
					}
				}				
				
//				for(Role role : module.getRoleList()){
//					System.out.print(role.getRoleName()+"@@@@@@"+role.getModuleName());
//					for(Functionality functionality:role.getFunctionalityList()){
//						System.out.println(functionality.getFunctionalityName()+"\t"+functionality.isView()+"\t"+functionality.isInsert()+"\t"+functionality.isUpdate());
//					}
//				}
			if(roleList.size()!=0){	
				module.setRoleList(roleList);
				module.setModuleName(roleList.get(0).getFunctionalityList().get(0).getModuleName());				
				module.setModuleCode(role.getModuleCode());
				module.setModuleDescription(role.getRoleCode());
			}			
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return module;
	}


	@Override
	public String insertFunctionalityRoleMapping(List<Role> roleList)
			throws CustomException {
		logger.info("insertFunctionalityRoleMapping into DB");
		SqlSession session =sqlSessionFactory.openSession();
		String statusMessage="SUCCESS";
		try {
			for (Role role : roleList) {
				for (Functionality functionality : role.getFunctionalityList()) {
					functionality.setObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));					
					functionality.setFunctionalityDesc(role.getRoleCode());
					functionality.setModuleName(role.getModuleCode());
					//System.out.println(functionality.getUpdatedBy()+"---"+functionality.getFunctionalityName()+"--"+functionality.getFunctionalityDesc()+"--"+functionality.getModuleName()+"--"+functionality.isUpdate());
					int status=session.insert("insertFunctionalityRoleMapping",	functionality);
					session.commit();					
					if(status>=0){
						statusMessage="FAILED";
					}
				}
			}
		} catch (Exception e) {
			statusMessage="FAILED";
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
		} finally {			
			session.close();
		}
		return statusMessage;
	}


	@Override
	public String addUserGroup(UserGroup userGroup) throws CustomException {
		logger.info("Get addUserGroup  DB");
		SqlSession session =sqlSessionFactory.openSession();	
		String statusMessage="FAILED";
		int userGroupStatus=0;
		int userGroupResourceMappingStatus=0;
		try {
			userGroup.setObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
			userGroupStatus=session.insert("insertUserGroup", userGroup);
			session.commit();
			if(userGroupStatus>0){
				for(Resource resource:userGroup.getResourceList()){
					resource.setUserGroup(userGroup);
					userGroupResourceMappingStatus=session.insert("insertUserGroupResourceMapping", resource);
					session.commit();
				}				
				if(userGroupResourceMappingStatus>0){
					statusMessage="SUCCESS";
				}
			}
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return statusMessage;
	}


	@Override
	public List<UserGroup> getAllUserGroups() throws CustomException {
		logger.info("Get getAllUserGroups");
		SqlSession session =sqlSessionFactory.openSession();
		List<UserGroup> userGroupList=null;
		try {
			userGroupList=session.selectList("selectAllUserGroups");						
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return userGroupList;
	}


	@Override
	public String inactiveUserGroupDetails(UserGroup userGroup)
			throws CustomException {
		logger.info("Get inactiveUserGroupDetails");
		SqlSession session =sqlSessionFactory.openSession();
		String statusMessage="FAILED";
		try {
			
			int status=session.update("inactiveUserGroupDetails", userGroup);
			session.commit();	
			
			if(status>0){
				statusMessage="SUCCESS";
			}
			
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return statusMessage;
	}


	@Override
	public UserGroup getUserGroupDetails(UserGroup userGroup)
			throws CustomException {
		logger.info("Get getUserGroupDetails");
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> resourceList=null;
		UserGroup userGroupDetails=new UserGroup();
		try {
			resourceList=session.selectList("selectUserGroupDetails",userGroup);	
			if(null!=resourceList && resourceList.size()!=0){				
				userGroupDetails.setResourceList(resourceList);
				userGroupDetails.setUserGroupCode(userGroup.getUserGroupCode());
				
				List<UserGroup> userGroupList=session.selectList("selectAllUserGroups");
				if(null!=userGroupList && userGroupList.size()!=0){
					for(UserGroup userGroupCollection:userGroupList){
						if(userGroupCollection.getUserGroupCode().equals(userGroup.getUserGroupCode())){
							userGroupDetails.setUserGroupName(userGroupCollection.getUserGroupName());
						}
					}
				}
			}
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return userGroupDetails;
	}


	public Module getNotificationDetails(String moduleName)
			throws CustomException {
		logger.info("getNotificationDetails() method in AdministratorDAOImpl");
		SqlSession session = sqlSessionFactory.openSession();
		Module module = null;
		try {
			module = session.selectOne("getNotificationDetails", moduleName);
			logger.info(module); 
			if (null != module) {
				if (null != module.getFunctionalityList()&& module.getFunctionalityList().size() != 0) {
					for (Functionality functionality : module.getFunctionalityList()) {
						if (null != functionality.getLoggingAspectList() && functionality.getLoggingAspectList().size() != 0) {
							for (LoggingAspect loggingAspect : functionality.getLoggingAspectList()) {
								List<Approver> approverGroupList = session.selectList("selectAllApproverGroups");// role
								List<Resource> allUserGroupList = new ArrayList<Resource>();
								for(Approver appproverGroup:approverGroupList){
									Resource resource = new Resource();
									resource.setCode(appproverGroup.getApproverGroupCode());
									resource.setUserId(appproverGroup.getApproverGroupName());
									allUserGroupList.add(resource);
								}
																			// code
																			// and
																			// role
																			// name
																			// set
																			// to
																			// Resource
																			// object
								if (null != allUserGroupList && allUserGroupList.size() != 0) {
									loggingAspect.setFunctionality(functionality);
									loggingAspect.setModule(module);
									loggingAspect.setNotificationLevel("URGENT");
									List<Resource> allUserGroupListForUrgentNotification = session.selectList("selectAllUserGroupListForUrgentOrNormalNotification",loggingAspect);
									if (null != allUserGroupListForUrgentNotification && allUserGroupListForUrgentNotification.size() != 0) {
										List<Resource> urgentUserGroupForNotificationList = new ArrayList<Resource>();
										HashMap<String, String> urgentUserGroupForNotificationMap = new HashMap<String, String>();
										for (Resource resource : allUserGroupListForUrgentNotification) {
											urgentUserGroupForNotificationMap.put(resource.getCode(),resource.getCode());
										}
										for (Resource allUserGroup : allUserGroupList) {
											Resource resource = new Resource();
											resource.setCode(allUserGroup.getCode());
											resource.setUserId(allUserGroup.getUserId());
											if (urgentUserGroupForNotificationMap.containsKey(allUserGroup.getCode())) {
												resource.setStatus("checked");
												urgentUserGroupForNotificationList.add(resource);
											} else {
												urgentUserGroupForNotificationList.add(resource);
											}
										}
										loggingAspect.setUrgentNotificationResourceList(urgentUserGroupForNotificationList);
									} else {
										loggingAspect.setUrgentNotificationResourceList(allUserGroupList);
									}
									loggingAspect.setNotificationLevel("NORMAL");
									List<Resource> allUserGroupListForNormalNotification = session.selectList("selectAllUserGroupListForUrgentOrNormalNotification",loggingAspect);
									if (allUserGroupListForNormalNotification != null && allUserGroupListForNormalNotification.size() != 0) {
										List<Resource> normalUserGroupForNotificationList = new ArrayList<Resource>();
										HashMap<String, String> normalUserGroupForNotificationMap = new HashMap<String, String>();
										for (Resource resource : allUserGroupListForNormalNotification) {
											normalUserGroupForNotificationMap.put(resource.getCode(),resource.getCode());
										}
										for (Resource allUserGroup : allUserGroupList) {
											Resource resource = new Resource();
											resource.setUserId(allUserGroup.getUserId());
											resource.setCode(allUserGroup.getCode());
											if (normalUserGroupForNotificationMap.containsKey(allUserGroup.getCode())) {
												resource.setStatus("checked");
												normalUserGroupForNotificationList.add(resource);
											} else {
												normalUserGroupForNotificationList.add(resource);
											}
										}
										loggingAspect.setNormalNotificationResourceList(normalUserGroupForNotificationList);
									} else {
										//System.out.println("within else");
										loggingAspect.setNormalNotificationResourceList(allUserGroupList);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception in getNotificationDetails(String moduleName) method in AdministratorDAOImpl",e);
			CustomException.exceptionHandler(e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return module;
	}


	@Override
	public String updateLoggingNotificationSetUp(Module module, String updatedBy)
			throws CustomException {
		logger.info("updateLoggingNotificationSetUp(Module module) method in AdministratorDAOImpl");
		SqlSession session = sqlSessionFactory.openSession();
		String strUpdateStatus = "false";
		int updateStatus = 0;
		try {
			if (null != module && null != module.getFunctionalityList() && module.getFunctionalityList().size() != 0) {
				for (Functionality functionality : module.getFunctionalityList()) {
					if (null != functionality.getLoggingAspectList() && functionality.getLoggingAspectList().size() != 0) {
						for (LoggingAspect loggingAspect : functionality.getLoggingAspectList()) {
							loggingAspect.setUpdatedBy(updatedBy);
							loggingAspect.setLoggingObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
							loggingAspect.setModule(module);
							loggingAspect.setFunctionality(functionality);
							
							List<Resource> selectedUserGroupForNotification = session.selectList("selectAllUserGroupListForUrgentOrNormalNotification",loggingAspect);
							if (loggingAspect.isNotification() == false) {
								if (null != selectedUserGroupForNotification) {
									updateStatus = session.update("inactiveUrgentAndNormalNotification",loggingAspect); // inactive all urgent and normal
								}
								
								updateStatus = session.insert("updateNotification", loggingAspect);
							}
							if (loggingAspect.isNotification() == true) {
								if (null == loggingAspect.getNormalNotificationResourceList() && null == loggingAspect.getUrgentNotificationResourceList()) {
									if (null != selectedUserGroupForNotification) {
										loggingAspect.setNotification(false);
										updateStatus = session.update("inactiveUrgentAndNormalNotification",loggingAspect);// inactive all urgent and normal
										updateStatus = session.insert("updateNotification",loggingAspect);
									}
								} else {
									loggingAspect.setNotificationLevel("NORMAL");
									List<Resource> allTeacherAndOfficeStaffListForNormalNotification = session.selectList("selectAllUserGroupListForUrgentOrNormalNotification",loggingAspect);
									if (null != loggingAspect.getNormalNotificationResourceList() && loggingAspect.getNormalNotificationResourceList().size() != 0) {
										if (null != allTeacherAndOfficeStaffListForNormalNotification && allTeacherAndOfficeStaffListForNormalNotification.size() != 0) {
											HashMap<String, String> dBUserGroupListForNormalNotification = new HashMap<String, String>();
											for (Resource resource : allTeacherAndOfficeStaffListForNormalNotification) {
												dBUserGroupListForNormalNotification.put(resource.getCode(),resource.getCode());
											}
											for (Resource resource : loggingAspect.getNormalNotificationResourceList()) {
												if (null != resource.getUserId()&& resource.getUserId().length() != 0) {
													String[] userId = resource.getUserId().split(",");
													if (null != userId&& userId.length != 0) {
														for (String userIdArray : userId) {
															if (dBUserGroupListForNormalNotification.containsKey(userIdArray)) {
																dBUserGroupListForNormalNotification.remove(userIdArray);
															} else {
																loggingAspect.setTask(userIdArray);// set userid into task
																updateStatus = session.insert("updateNotification",loggingAspect);
															}
														}
													}
												}
											}
											for (Object key : dBUserGroupListForNormalNotification.keySet()) {
												loggingAspect.setServiceName(key.toString());// user id set to service name
												session.update("inactiveUrgentAndNormalNotification",loggingAspect);
											}
										} else {
											for (Resource resource : loggingAspect.getNormalNotificationResourceList()) {
												if (null != resource.getUserId()&& resource.getUserId().length() != 0) {
													String[] userId = resource.getUserId().split(",");
													if (null != userId && userId.length != 0) {
														for (String userIdArray : userId) {
															loggingAspect.setTask(userIdArray);// set userid into task
															updateStatus = session.insert("updateNotification",loggingAspect);
														}
													}
												}
											}
										}
									} else {
										if (null != allTeacherAndOfficeStaffListForNormalNotification) {
											updateStatus = session.update("inactiveUrgentAndNormalNotification",loggingAspect); // inactive all normal
										}
									}
									loggingAspect.setNotificationLevel("URGENT");
									List<Resource> allUserGroupListForUrgentNotification = session.selectList("selectAllUserGroupListForUrgentOrNormalNotification",loggingAspect);
									if (null != loggingAspect.getUrgentNotificationResourceList() && loggingAspect.getUrgentNotificationResourceList().size() != 0) {
										if (null != allUserGroupListForUrgentNotification && allUserGroupListForUrgentNotification.size() != 0) {
											HashMap<String, String> dBUserGroupListForUrgentNotification = new HashMap<String, String>();
											for (Resource resource : allUserGroupListForUrgentNotification) {
												dBUserGroupListForUrgentNotification.put(resource.getCode(),resource.getCode());
											}
											for (Resource resource : loggingAspect.getUrgentNotificationResourceList()) {
												if (null != resource.getUserId() && resource.getUserId().length() != 0) {
													String[] userId = resource.getUserId().split(",");
													if (null != userId && userId.length != 0) {
														for (String userIdArray : userId) {
															if (dBUserGroupListForUrgentNotification.containsKey(userIdArray)) {
																dBUserGroupListForUrgentNotification.remove(userIdArray);
															} else {
																loggingAspect.setTask(userIdArray);// set userid into task
																updateStatus = session.insert("updateNotification",loggingAspect);
															}
														}
													}
												}
											}
											for (Object key : dBUserGroupListForUrgentNotification.keySet()) {
												loggingAspect.setServiceName(key.toString());// user id set to service name
												updateStatus = session.update("inactiveUrgentAndNormalNotification",loggingAspect);
											}
										} else {
											for (Resource resource : loggingAspect.getUrgentNotificationResourceList()) {
												if (null != resource.getUserId()&& resource.getUserId().length() != 0) {
													String[] userId = resource.getUserId().split(",");
													if (null != userId && userId.length != 0) {
														for (String userIdArray : userId) {
															loggingAspect.setTask(userIdArray);// set userid into task
															updateStatus = session.insert("updateNotification",loggingAspect);
														}
													}
												}
											}
										}
									} else {
										if (null != allUserGroupListForUrgentNotification) {
											updateStatus = session.update("inactiveUrgentAndNormalNotification",loggingAspect); // inactive all urgent
										}
									}
								}
								updateStatus = session.update("inactiveActiveActivityLog",loggingAspect);
							}
						}
					}
				}
			}
			session.commit();
		} catch (Exception e) {			
			updateStatus = 0;
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		if (updateStatus != 0) {
			strUpdateStatus = "success";
		}
		return strUpdateStatus;
	}


	@Override
	public List<Ticket> viewSLAForTicketing() throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<Ticket> ticketList = null;		
		try{
			ticketList=session.selectList("viewSLAForTicketing");						
		}catch(Exception e){
			CustomException.exceptionHandler(e);
		}
		return ticketList;
	}


	@Override
	public String createTicketingSLA(List<Ticket> ticketList)
			throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		String insertStatus="true";
		int updateStatus=0;
		try{
			for(Ticket ticket:ticketList){
				List<Module>moduleList=new ArrayList<Module>();
				moduleList=ticket.getModuleList();
				for(Module module:moduleList){
					ticket.setModuleName(module.getModuleName());
					ticket.setTicketObjectId(encryptDecrypt.getBase64DecodedID("AdministratorDAOImpl"));
					updateStatus=session.insert("createTicketingSLA", ticket);
				}				
				if(updateStatus==0){
					insertStatus="false";
				}
			}			
		}catch(Exception e){
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return insertStatus;
	}


	@Override
	public String updateTicketingSLA(Ticket ticket) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		String insertStatus="true";
		int updateStatus=0;
		try{
			//System.out.println("User id =="+ticket.getUpdatedBy()); 
			//System.out.println("Module Name =="+ticket.getModuleName());
			//System.out.println("Status =="+ticket.getStatus());
			updateStatus=session.update("updateTicketingSLA", ticket);								
				if(updateStatus==0){
					insertStatus="false";
				}						
		}catch(Exception e){
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return insertStatus;
	}


	@Override
	public String createArchiving(String academicYear, String updatedBy)
			throws CustomException {
		String message="Archive Successfull";
		List<StudentForXml> studentList=new ArrayList<StudentForXml>();
		try{
			SqlSession session =sqlSessionFactory.openSession();			
			studentList=session.selectList("studentListForXml", academicYear);			
			for(StudentForXml studentForXml:studentList){				
				StudentForXml studentDetails=new StudentForXml();
				studentDetails=session.selectOne("studentDetailsForXml", studentForXml.getRegistrationId());
				if(null!=studentDetails){
				studentForXml.setName(studentDetails.getName());
	            studentForXml.setFathersName(studentDetails.getFathersName());
	            studentForXml.setMothersName(studentDetails.getMothersName());
	            studentForXml.setAdmissionDate(studentDetails.getAdmissionDate());
	            studentForXml.setAdmissionCourse(studentDetails.getAdmissionCourse());
	            studentForXml.setAdmissionClass(studentDetails.getAdmissionClass());
	            studentForXml.setDateOfBirth(studentDetails.getDateOfBirth());
	            studentForXml.setPrsentAddress(studentDetails.getPrsentAddress());
	            studentForXml.setPermanentAddress(studentDetails.getPermanentAddress());
	            studentForXml.setNationality(studentDetails.getNationality());
	            studentForXml.setEmail(studentDetails.getEmail());
	            studentForXml.setContactNumber(studentDetails.getContactNumber());
	            studentForXml.setGender(studentDetails.getGender());
	            studentForXml.setReligion(studentDetails.getReligion());
	            studentForXml.setBloodGroup(studentDetails.getBloodGroup());
	            studentForXml.setLeavingCourse(studentDetails.getLeavingCourse());
	            studentForXml.setLeavingClass(studentDetails.getLeavingClass());	            
	            studentForXml.setYear(academicYear);
				}
			}
			
			for(StudentForXml studentForXml:studentList){
				List<ExamForXml> examList=session.selectList("examListForXml",studentForXml);
				for(ExamForXml examForXml:examList){
					examForXml.setRegistrationId(studentForXml.getRegistrationId());
					examForXml.setYear(academicYear);
					logger.info("DAO :: "+examForXml.getExamType()+"\t"+examForXml.getExamName()+"\t"+examForXml.getTerm());
					List<SubjectMarksForXml> subjectMarksForXmlList=session.selectList("subjectMarksListForXml", examForXml);
					if(null!=subjectMarksForXmlList && subjectMarksForXmlList.size()!=0)
						examForXml.setSubjectMarksList(subjectMarksForXmlList);
				}
				if(null!=examList && examList.size()!=0)
					studentForXml.setExamlist(examList);
			}
			
			
			for(StudentForXml studentForXml:studentList){
				try {
					
					String path = "C:"+File.separator+"Backup Data"+File.separator+academicYear+File.separator+"Student";
					String fname= path+File.separator+studentForXml.getRegistrationId()+".xml";
				    File makeFile = new File(path);
				    File file = new File(fname);

				    makeFile.mkdirs() ;
				    try {
				    	file.createNewFile();
				    } catch (IOException e) {
				    	CustomException.exceptionHandler(e);
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				        message="Archive Failed";
				    }
					
					JAXBContext jaxbContext = JAXBContext.newInstance(StudentForXml.class);
					Marshaller jaxbMarshaller = jaxbContext.createMarshaller();					
					jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);					
					jaxbMarshaller.marshal(studentForXml, file);
					
				}catch (JAXBException e){
					CustomException.exceptionHandler(e);
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					message="Archive Failed";
				}
			}
			
	//****************************************************************************************
			
			List<StaffForXml> staffList=session.selectList("staffListForXml");
			
			for(StaffForXml staffForXml:staffList){
				if(! staffForXml.getResourceType().equalsIgnoreCase("TEACHER"))
					staffForXml.setTeachingLevel("NOT APPLICABLE");				
				SalaryForXml salary=new SalaryForXml();
				List<SalaryDetailsForXml> salaryDetailsList=session.selectList("salaryDetailsList", staffForXml);
				if(salaryDetailsList!=null && salaryDetailsList.size()!=0){
	        		 salary.setSalarydetailsList(salaryDetailsList);
	        		 staffForXml.setSalary(salary);
	        	 }
			}
			
			for(StaffForXml staffForXml:staffList){
				String userId=staffForXml.getUserId();
				List<SubjectsForXml> subjectList=session.selectList("getSubjectsTaught",userId);
				if(subjectList.size()!=0)
					staffForXml.setSubjectList(subjectList);
			}
			if(staffList!=null && staffList.size()!=0){
				for(StaffForXml staffForXml: staffList){
					try{
							String path = "C:"+File.separator+"Backup Data"+File.separator+academicYear+File.separator+"Staff";
							String fname= path+File.separator+staffForXml.getUserId()+".xml";
						    File makeFile = new File(path);
						    File file = new File(fname);
						    makeFile.mkdirs() ;
						    try {
						    	file.createNewFile();
						    } catch (IOException e) {
						    	CustomException.exceptionHandler(e);
								TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						        message="Archive Failed";
						    }							
							JAXBContext jaxbContext = JAXBContext.newInstance(StaffForXml.class);
							Marshaller jaxbMarshaller = jaxbContext.createMarshaller();							
							jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);							
							jaxbMarshaller.marshal(staffForXml, file);						
						}catch (JAXBException e){
							CustomException.exceptionHandler(e);
							TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
							message="Archive Failed";
						}
				}
			}
			PreviousData previousData=new PreviousData();
			previousData.setNext(academicYear);
			previousData.setUpdatedBy(updatedBy);
			previousData.setObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
			session.insert("insertInArchiveAndPurge", previousData);
			session.commit();
		}catch(Exception e){
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			message="Archive Failed";
		}
		return message;		
	}


	@Override
	public String purgeRecordPost(String academicYearName, String userID)
			throws CustomException {
			SqlSession session =sqlSessionFactory.openSession();
		String purgeStatus="Successful";
		try{
			PreviousData previousData=session.selectOne("checkForPurging", academicYearName);
			if(null!=previousData){
				if(null==previousData.getNext() && previousData.isStaff()==false)	{
					AcademicYear academicYear=new AcademicYear();
					academicYear.setAcademicYearName(academicYearName);
					academicYear.setUpdatedBy(userID);					
					session.delete("purgeRecordPost", academicYear);					
					session.update("updateArchiveAndPurge", academicYear);
				}else{
					purgeStatus="Data Already Purged on "+ previousData.getNext();
				}
			}else{
				purgeStatus="Data Not Archieced Yet. Sorry";
			}
		}catch(Exception e){
			purgeStatus="Failure";
			CustomException.exceptionHandler(e);
		}
		return purgeStatus;
	}


	@Override
	public StudentForXml readArchivedDataForStudent(File file)
			throws CustomException {
		JAXBContext jaxbContext = null;
		StudentForXml student=null;
		try{
			jaxbContext=JAXBContext.newInstance(StudentForXml.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			student = (StudentForXml) jaxbUnmarshaller.unmarshal(file);
		}catch(JAXBException jAXBException){
			CustomException.exceptionHandler(jAXBException);			
		}catch (Exception e) {
			CustomException.exceptionHandler(e);
		}
		return student;
	}


	@Override
	public StaffForXml readArchivedDataForStaff(File file)
			throws CustomException {
		JAXBContext jaxbContext = null;
		StaffForXml staff=null;
		try{
			jaxbContext=JAXBContext.newInstance(StaffForXml.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			staff = (StaffForXml) jaxbUnmarshaller.unmarshal(file);	
		}catch(JAXBException jAXBException){
			CustomException.exceptionHandler(jAXBException);		
		}catch (Exception e) {
			logger.error(e);
		}
		return staff;
	}

	@Override
	public List<LoggingAspect> getNotificationMediums() {
		List<LoggingAspect> notificationMediumList=null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info("getNotificationMediums() method in AdministratorDAOImpl");			
			notificationMediumList = session.selectList("selectNotificationMediums");
			if(notificationMediumList!=null && notificationMediumList.size() != 0){
				for(LoggingAspect la : notificationMediumList){
					if(la.getNotificationMediums()!=null && la.getNotificationMediums().size()!=0){
						for(NotificationMedium nm : la.getNotificationMediums()){
							nm.setNotificationMediumDesc(la.getNotificationLevel());
							String notificationLevelMediumCode=session.selectOne("checkingNotificationLevelMediumCode", nm);
							if(notificationLevelMediumCode != null){
								nm.setStatus("checked");
							}
						}
					}
				}
			}
		}catch(Exception e){
			logger.error("Exception in getNotificationMediums() method in AdministratorDAOImpl", e);
			
		}finally{
			session.close();
		}
		return notificationMediumList;
	}


	@Override
	public String updateNotificationMediums(List<LoggingAspect> notificationList) {
		String  successFailMessage=null;
		SqlSession session =sqlSessionFactory.openSession();
		try{			
			logger.info("updateNotificationMediums() method in AdministratorDAOImpl");
			if(notificationList != null && notificationList.size() !=0){
				for(LoggingAspect la: notificationList){
					List<NotificationMedium> oldNotificationMediumList=session.selectList("getAllNotificationMediumsForNotificationLevel", la.getNotificationLevel());
					/**
					 * if previous mapped notification medium is not true for a notification level and updated(coming from jsp) notification medium is false
					 * then inactive  all previous mapped notification medium for a notification level			
					 */
					if(oldNotificationMediumList != null && oldNotificationMediumList.size() != 0 && la.getNotificationMediums()==null){
						for(NotificationMedium nm : oldNotificationMediumList){
							if(nm.isActive()== true){
								nm.setNotificationMediumDesc(la.getNotificationLevel());//set notificationLevel into notificationMediumDesc
								session.update("inActiveNotificationMediumAndLevelMapping", nm);
							}
						}
					}
					/**
					 * if previous mapped notification medium is null for a notification level and updated(coming from jsp) notification medium is not null
					 * then insert  all updated(coming from jsp) notification medium for a notification level			
					 */	
					if((la.getNotificationMediums() !=null && la.getNotificationMediums().size() != 0 )&&(oldNotificationMediumList==null || oldNotificationMediumList.size()==0)){
						for(NotificationMedium nm : la.getNotificationMediums()){
							nm.setNotificationMediumDesc(la.getNotificationLevel());//set notificationLevel into notificationMediumDesc
							nm.setObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
							session.insert("insertNotificationMediumAndLevelMapping", nm);
						}
					}
					/**
					 * if previous mapped notification medium is not null for a notification level and updated(coming from jsp) notification medium  is not null
					 * then checking all active and inactive notification medium  for a notification level	and then insert/inactive/active notification medium  for a notification level.		
					 */
					if((la.getNotificationMediums() !=null && la.getNotificationMediums().size() != 0)&&(oldNotificationMediumList!=null && oldNotificationMediumList.size()!=0)){
						Map<String, Boolean> oldNotificationMediumMap=new HashMap<String, Boolean>();
						for(NotificationMedium nm:oldNotificationMediumList){
							oldNotificationMediumMap.put(nm.getNotificationMediumName(),nm.isActive());
						}
						for(NotificationMedium nm : la.getNotificationMediums()){
							if(oldNotificationMediumMap.containsKey(nm.getNotificationMediumName())){
								if(oldNotificationMediumMap.get(nm.getNotificationMediumName()).equals(false)){
									nm.setNotificationMediumDesc(la.getNotificationLevel());//set notificationLevel into notificationMediumDesc
									session.update("activeNotificationMediumAndLevelMapping", nm);
								}
								if(oldNotificationMediumMap.get(nm.getNotificationMediumName()).equals(true)){
									oldNotificationMediumMap.remove(nm.getNotificationMediumName());
								}
							}else{
								nm.setNotificationMediumDesc(la.getNotificationLevel());//set notificationLevel into notificationMediumDesc
								nm.setObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
								session.insert("insertNotificationMediumAndLevelMapping", nm);
							}
						}
						if(oldNotificationMediumMap != null && oldNotificationMediumMap.size() != 0){
							for (Object key : oldNotificationMediumMap.keySet()) {
								if(oldNotificationMediumMap.get(key).equals(true)){
									NotificationMedium nm =new NotificationMedium();
									nm.setNotificationMediumName(key.toString());
									nm.setNotificationMediumDesc(la.getNotificationLevel());//set notificationLevel into notificationMediumDesc
									session.update("inActiveNotificationMediumAndLevelMapping", nm);
								}
							}
						}
					}
				}
			}
			session.commit();
		}catch(Exception e){
			logger.error("Exception in updateNotificationMediums() method in AdministratorDAOImpl", e);
			
		}finally{
			session.close();
		}
		return successFailMessage;
	}


	@Override
	public String insertApprovers(Approver approver) throws CustomException {
		logger.info("Get addUserGroup  DB");
		SqlSession session =sqlSessionFactory.openSession();	
		String statusMessage="FAILED";
		int approverGroupStatus=0;
		int approverGroupResourceMappingStatus=0;
		try {
			approver.setObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
			approverGroupStatus=session.insert("insertApproverGroup", approver);
			session.commit();
			if(approverGroupStatus>0){
				for(Resource resource:approver.getResourceList()){
					resource.setApprover(approver);
					approverGroupResourceMappingStatus=session.insert("insertApproverGroupResourceMapping", resource);
					session.commit();
				}				
				if(approverGroupResourceMappingStatus>0){
					statusMessage="SUCCESS";
				}
			}
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return statusMessage;
	}


	@Override
	public List<Approver> getAllApproverGroups() throws CustomException {
		logger.info("Get getAllApproverGroups");
		SqlSession session =sqlSessionFactory.openSession();
		List<Approver> approverGroupList=null;
		try {
			approverGroupList=session.selectList("selectAllApproverGroups");						
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return approverGroupList;
	}


	@Override
	public String inactiveApproverGroupDetails(Approver approver)
			throws CustomException {
		logger.info("Get inactiveApproverGroupDetails");
		SqlSession session =sqlSessionFactory.openSession();
		String statusMessage="FAILED";
		try {	
			int status=session.update("inactiveApproverGroupDetails", approver);
			session.commit();						
			if(status>0){
				statusMessage="SUCCESS";
			}
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return statusMessage;
	}


	@Override
	public Approver getApproverGroupDetails(Approver approver)
			throws CustomException {
		logger.info("Get getApproverGroupDetails");
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> resourceList=null;
		Approver approverDetails=new Approver();
		try {
			resourceList=session.selectList("selectApproverGroupDetails",approver);	
			if(null!=resourceList && resourceList.size()!=0){				
				approverDetails.setResourceList(resourceList);
				approverDetails.setApproverGroupCode(approver.getApproverGroupCode());				
				List<Approver> approverGroupList=session.selectList("selectAllApproverGroups");	
				if(null!=approverGroupList && approverGroupList.size()!=0){
					for(Approver approverGroupCollection:approverGroupList){
						if(approverGroupCollection.getApproverGroupCode().equals(approver.getApproverGroupCode())){
							approverDetails.setApproverGroupName(approverGroupCollection.getApproverGroupName());
						}
					}
				}
			}
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return approverDetails;
	}


	@Override
	public int insertUserPasswordStatus(LoginForm login)throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		int status=0;
		try{
		  logger.info("insertUserPasswordStatus(LoginForm login) method in AdministratorDaoImpl");		
		  login.setObjId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
		  status = session.insert("insertUserPasswordStatus", login);
		  session.commit();
		}catch(Exception e){
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("Exception in insertUserPasswordStatus(LoginForm login) method in AdministratorDaoImpl for LDAP operation EXCEPTION: ",e);
			status=0;
		}
		return status;
	}

	

	@Override
	public int updateUserPasswordStatus(LoginForm login)throws CustomException {
		 int status=0;
		logger.info("updateUserPasswordStatus(LoginForm login) method in AdministratorDaoImpl");
		try{
			SqlSession session =sqlSessionFactory.openSession();
			login.setStatus("ByAdmin");
			status = session.update("updateUserPasswordStatus", login);
			session.commit();
		}catch(Exception e){
			CustomException.exceptionHandler(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("Exception in updateUserPasswordStatus(LoginForm login) method in AdministratorDaoImpl for LDAP operation EXCEPTION: ",e);
			status=0;
		}
		return status;
	}


	@Override
	public String createTicketingSLA(Ticket ticket) throws CustomException {
		
			SqlSession session =sqlSessionFactory.openSession();
			String insertStatus="true";
			int updateStatus=0;
			try{
					List<Module>moduleList=new ArrayList<Module>();
					moduleList=ticket.getModuleList();
					for(Module module:moduleList){
						ticket.setModuleName(module.getModuleName());
						ticket.setTicketObjectId(encryptDecrypt.getBase64DecodedID("AdministratorDAOImpl"));
						updateStatus=session.insert("createTicketingSLA", ticket);
					}				
					if(updateStatus==0){
						insertStatus="false";
					}
							
			}catch(Exception e){
				CustomException.exceptionHandler(e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
			return insertStatus;
		}
	
	//missing link integration 17042018
	@Override
	public List<JobType> getAllJobDetails() {
		
		List<JobType> jobTypeList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getAllJobDetails() method of TicketDaoImpl");			
			jobTypeList = session.selectList("getjobDetailsList");
			
		} catch (NullPointerException e) {
			
			logger.error("getAllJobDetails() In TicketDAOImpl.java: NullPointerException" + e);
		} catch (Exception e) {
			
			logger.error("getAllJobDetails() In TicketDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return jobTypeList;
	}
	
	
	@Override
	public int saveJobDetails(JobType jobType) {
		SqlSession session = sqlSessionFactory.openSession();
		int insertStatus = 0;
		try {
			logger.info("In saveJobDetails() method of TicketDAOImpl");
			jobType.setJobTypeObjectId(encryptDecrypt.encrypt("TicketDaoImpl"));
			
			 insertStatus = session.insert("insertJobDetails", jobType);
			
		}  catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			logger.error("saveJobDetails() In TicketDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}

		return insertStatus;
	}
	
	@Override
	public List<Approver> getAllApprovalOrderList() {
		List<Approver> approvalOrderList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getAllApprovalOrderList() method of TicketDaoImpl");
			
			approvalOrderList = session.selectList("getAllDataOfApprovalOrder");
			
		} catch (NullPointerException e) {
			
			logger.error("getAllApprovalOrderList() In TicketDAOImpl.java: NullPointerException" + e);
		} catch (Exception e) {
			
			logger.error("getAllApprovalOrderList() In TicketDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return approvalOrderList;
	}
	
	//Modified By Naimisha 25082017
	@Override
	public String insertApprovalOrder(List<Approver> approverGroupList) {
		
		SqlSession session = sqlSessionFactory.openSession();	
		String statusMessage="success";
		int insertStatus = 0;
		try{
			for(Approver approver : approverGroupList){
				approver.setObjectId(encryptDecrypt.encrypt("TicketDaoImpl"));
				insertStatus = session.insert("insertApproverOrderDetails", approver);
			}
			
			
		} catch (Exception e) {
			statusMessage = "fail";
			logger.error("insertApprovalOrder() In TicketDAOImpl.java: Exception" + e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return statusMessage;
	}

//-----------------For Task Notification And Delegation-----------------
	
	@Override
	public List<Resource> getResourceDetails(String resourceTypeName) throws CustomException {
		
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> resourceDetailsList = null;
		try{
			resourceDetailsList = session.selectList("allResourceDetailsList", resourceTypeName);
						
		}catch(Exception e){
			//e.printStackTrace();
			CustomException.exceptionHandler(e);
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		
		return resourceDetailsList;
	}


	@Override
	public List<String> getAllTaskNameList() throws CustomException {
		List<String> allTaskNameList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getAllTaskNameList() method of TicketDaoImpl");
			
			allTaskNameList = session.selectList("getAllTaskList");
			
		} catch (NullPointerException e) {
			
			logger.error("getAllTaskNameList() In TicketDAOImpl.java: NullPointerException" + e);
		} catch (Exception e) {
			
			logger.error("getAllTaskNameList() In TicketDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return allTaskNameList;
	}


	@Override
	public String insertIntoTaskDetails(Resource resource) {
		SqlSession session =sqlSessionFactory.openSession();
		String insertStatus="success";
		int updateStatus=0;
		try{
			String userId = null;
			Resource taskAallocatedFromName = null;
			Resource taskAallocatedToName = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
			Date dNow = new Date();
			String startDate = (dateFormat.format(dNow)).toString();
			resource.setObjectId(encryptDecrypt.encrypt("AdministratorDaoImpl"));
			//String taskType  = resource.getRoleName();
			userId = resource.getUpdatedBy();
			//Resource resourceName = session.selectOne("selectUserNameByUserId",userId );
			//if( resourceName.getUpdatedBy()!=null){
			//	 userId = resource.getName();
				
			//	 taskAallocatedToName = session.selectOne("selectUserNameByUserId",userId );
			//}
			taskAallocatedFromName = session.selectOne("selectUserNameByUserId",userId );
			
			//String notofication  changes done to be here
			//resource.setDesc(msg);
			List<ResourceType>resourceTypeList = resource.getResourceTypeList();
			//System.out.println("resource Type list size ===="+resourceTypeList.size());
			for(ResourceType rt : resourceTypeList){
				
				String msg  = taskAallocatedFromName.getUpdatedBy()+" Allocatetd The Task of "+resource.getJobType().getJobTypeName()+" to "+rt.getResourceTypeName();
				resource.setDesc(msg);
				ResourceType resourceType   = new ResourceType();
				resourceType.setResourceTypeCode(rt.getResourceTypeCode());
				resourceType.setResourceTypeName(rt.getResourceTypeName());
				resourceType.setResourceTypeDesc(rt.getResourceTypeDesc());
				resource.setResourceType(resourceType);
				updateStatus = session.insert("insertIntoTaskDetails", resource);
				/*if(updateStatus != 0){
					resource.setStartDate(startDate);
					updateStatus = session.insert("insertIntoMyEventsForTaskDetails", resource);
				}*/
			}
			
			//System.out.println("updateStatus===="+updateStatus);
			
			
					
				/*if(updateStatus==0){
					insertStatus="fail";
				}else{*/
					session.commit();
				//}
		}catch(Exception e){
			insertStatus="fail";
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return insertStatus;
	}


	@Override
	public List<TaskDetails> getAllTaskDetailsList(String userName) {
		SqlSession session =sqlSessionFactory.openSession();
		List<TaskDetails> allTaskDetailsList = null;
		try{
			allTaskDetailsList = session.selectList("allTaskDetailsList", userName);
						
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return allTaskDetailsList;
	}


	
	/***********Done By Naimisha 23112016**************/
	@Override
	public String setInactiveSubjectGroup(List<SubjectGroup> subjectGroupList) {
		SqlSession session =sqlSessionFactory.openSession();
		String strUpdateStatus="true";
		int updateStatus;
		try{
			for(SubjectGroup subjectGroup:subjectGroupList){				
				updateStatus = session.update("setInactiveSubjectGroup", subjectGroup);
				//System.out.println("updateStatus========="+updateStatus);
				if(updateStatus!=1){
					strUpdateStatus="false";
				}
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		return strUpdateStatus;
	}


	@Override
	public String setInactiveSubject(List<Subject> subjectList) {
		SqlSession session =sqlSessionFactory.openSession();
		String strUpdateStatus="true";
		int updateStatus;
		try{
			for(Subject subject:subjectList){				
				updateStatus=session.update("setInactiveSubject", subject);
				if(updateStatus!=0){
					strUpdateStatus="false";
				}
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		return strUpdateStatus;
	}


	@Override
	public String setInactiveStaff(List<Resource> resourceList) {
		SqlSession session =sqlSessionFactory.openSession();
		String strUpdateStatus="true";
		int updateStatus;
		try{
			for(Resource resource:resourceList){				
				updateStatus=session.update("setInactiveStaff", resource);
				if(updateStatus==0){
					strUpdateStatus="false";
				}
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		return strUpdateStatus;
	}


	@Override
	public String setInactiveStudent(List<Resource> resourceList) {
		SqlSession session =sqlSessionFactory.openSession();
		String strUpdateStatus="true";
		int updateStatus;
		try{
			for(Resource resource:resourceList){				
				updateStatus=session.update("setInactiveStudent", resource);
				if(updateStatus==0){
					strUpdateStatus="false";
				}
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		return strUpdateStatus;
	}


	@Override
	public String setCourseInactive(List<Course> courseList) {
		SqlSession session =sqlSessionFactory.openSession();
		String strUpdateStatus="true";
		int updateStatus;
		try{
			for(Course course:courseList){				
				updateStatus=session.update("setCourseInactive", course);
				if(updateStatus==0){
					strUpdateStatus="false";
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return strUpdateStatus;
	}
	

	@Override
	public String submitRepositoryDirectory(RepositoryStructure repository) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "fail";
		try{
			repository.setRepositoryObjectId(encryptDecrypt.encrypt("AdministratorDaoimpl"));
			String existingRepository = session.selectOne("selectExistingRepository", repository);
			if(null != existingRepository) {
				session.update("inactiveExistingRepository", repository);
				int insertStatus = session.insert("saveRepository", repository);
				if(insertStatus != 0) {
					status = "success";
				}
			}else {
				int insertStatus = session.insert("saveRepository", repository);
				if(insertStatus != 0) {
					status = "success";
				}
			}
		}catch (Exception e) {
			logger.error("Exception in submitRepositoryDirectory() of administratorDaoImpl.java:"+e);
			e.printStackTrace();
		}
		return status;
	}


	@Override
	public RepositoryStructure getRespositoryStructure() {
		SqlSession session = sqlSessionFactory.openSession();
		RepositoryStructure repoStructure = null;
		try{
			repoStructure = session.selectOne("getRespositoryStructure");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return repoStructure;
	}
	
	@Override
	public String deleteSLATicket(Ticket ticket) {
		SqlSession session =sqlSessionFactory.openSession();
		String strUpdateStatus="SUCCESS";
		int updateStatus;
		try{
			//System.out.println("In DAOImpl...LN1792-->"+ticket.getTicketId());
			//System.out.println("User ID-->"+ticket.getUpdatedBy());
			updateStatus=session.update("deleteSLATicket", ticket);
			//System.out.println("updateStatus-->"+updateStatus);
			
		}catch(Exception e){
			strUpdateStatus="FAIL";
			e.printStackTrace();
		}
		return strUpdateStatus;
	}
	
	@Override
	public List<Approver> getApproverGroupDetailsForJobType(String jobType) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Approver> approverGroupList = null;
		try{
			approverGroupList = session.selectList("approverGroupListForAJobType", jobType);
						
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return approverGroupList;
	}


	@Override
	public List<Approver> getApproversListAgainstUserId(String userId) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Approver> approverGroupList = null;
		try{
			//System.out.println("user id============"+userId);
			approverGroupList = session.selectList("approverGroupListAgainstUserId", userId);
						
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return approverGroupList;
	}


	@Override
	public List<JobType> getJobListAgainstApproverGroup(String approvarGroupCode) {
		SqlSession session =sqlSessionFactory.openSession();
		List<JobType> jobTypeList = null;
		try{
			//System.out.println("approvarGroupCode============"+approvarGroupCode);
			jobTypeList = session.selectList("jobTypeListAgainstApproverGroup", approvarGroupCode);
						
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jobTypeList;
	}


	@Override
	public List<TaskDetails> getTaskDetailsAgainstJobTypeAndApproverGroup(TaskDetails taskDetails) {
		SqlSession session =sqlSessionFactory.openSession();
		List<TaskDetails> taskDetailsList = null;
		try{
			//System.out.println("approvarGroupCode============"+approvarGroupCode);
			//System.out.println("task type======"+taskDetails.getTaskType());
			//System.out.println("task details_code ====="+taskDetails.getTaskDetailsCode());
			taskDetailsList = session.selectList("getTaskDetailsAgainstJobTypeAndApproverGroup", taskDetails);
						
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return taskDetailsList;
	}


	@Override
	public List<Task> getTaskDetailsForATicket(Task task) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Task> taskDetailsList = null;
		try{
			taskDetailsList = session.selectList("selecTaskDetailsForATicket", task);
						
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return taskDetailsList;
	}

	@Override
	public String inActiveRoleContactMapping(Role role) {
		SqlSession session =sqlSessionFactory.openSession();
		String strUpdateStatus="SUCCESS";
		int updateStatus;
		try{
			//System.out.println("In DAOImpl...LN1792-->"+ticket.getTicketId());
			//System.out.println("User ID-->"+ticket.getUpdatedBy());
			System.out.println("role code==="+role.getRoleCode());
			System.out.println("role name==="+role.getRoleName());
			updateStatus = session.update("deleteRoleContactMapping", role);
			//System.out.println("updateStatus-->"+updateStatus);
			
		}catch(Exception e){
			strUpdateStatus="FAIL";
			e.printStackTrace();
		}
		return strUpdateStatus;

	}
	
	@Override
	public List<Role> getAllRolesForProfilematrix() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Role> roleList = null;
		try{
			roleList = session.selectList("getAllRolesForProfilematrix");
			System.out.println("roleList size:"+roleList.size());
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return roleList;
	}


	@Override
	public List<Module> getAllModulesForProfilematrix() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Module> moduleList = null;
		try{
			moduleList = session.selectList("getAllModulesForProfilematrix");
			System.out.println("moduleList:"+moduleList.size());
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return moduleList;
	}


	@Override
	public String submitProfileMatrix(List<ProfileMatrix> profileMatrixList) {
		logger.info("In submitProfileMatrix() method of AdministratorDAOImpl: ");
		SqlSession session = sqlSessionFactory.openSession();
		String status = null;
		int insertStatus = 0;
		try{
			session.delete("deletePreviousCombinationOfProfileMatrix");
			if(null!= profileMatrixList && profileMatrixList.size()!=0){
				for(ProfileMatrix profileMatrix : profileMatrixList){
					for(Module module : profileMatrix.getRole().getModuleList()){
						module.setModuleName(profileMatrix.getRole().getRoleCode());
						module.setUpdatedBy(profileMatrix.getUpdatedBy());
						module.setObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
						insertStatus = session.insert("submitProfileMatrix", module);
					}
				}
				if(insertStatus != 0){
					status = "success";
				}
			}
		}catch (Exception e) {
			status = "fail";
			logger.error("Error In submitProfileMatrix() method of AdministratorDAOImpl:",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return status;
	}
	
	@Override
	public List<Module> getModuleListForSpecificRole(String roleCode) {
		logger.info("In getModuleListForSpecificRole(rolecode) of AdministratorDaoImpl.java");
		SqlSession session = sqlSessionFactory.openSession();
		List<Module> moduleList = null;
		try{
			//System.out.println("line 2024 RoleCode::"+roleCode);
			moduleList = session.selectList("getModuleListForSpecificRole", roleCode);
			//System.out.println("line 2026 moduleList size::"+moduleList.size());
		}catch (Exception e) {
			logger.error("Error in getModuleListForSpecificRole(rolecode) of AdministratorDaoImpl.java",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return moduleList;
	}
	
	@Override
	public Module getTabAndSearchForModuleAndRole(String roleCode, String moduleCode) {
		logger.info("In getTabAndSearchForModuleAndRole(roleCode, moduleCode) in AdministratorDaoImpl.java");
		SqlSession session = sqlSessionFactory.openSession();
		Module tabAndSearchForModuleAndRole = null;
		try{
			//System.out.println("RoleCode::"+roleCode+"\tModuleCode::"+moduleCode);
			Module module = new Module();
			module.setModuleCode(moduleCode);
			module.setModuleName(roleCode);
			//System.out.println("RoleCode::"+module.getModuleName()+"\tModuleCode::"+module.getModuleCode());
			tabAndSearchForModuleAndRole = session.selectOne("getTabAndSearchForModuleAndRole",module);
			//System.out.println("Tab:"+tabAndSearchForModuleAndRole.isTabCheck()+"Search:"+tabAndSearchForModuleAndRole.isSearchCheck());
		}catch (Exception e) {
			logger.error("Error in getTabAndSearchForModuleAndRole(roleCode, moduleCode) in AdministratorDaoImpl.java", e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return tabAndSearchForModuleAndRole;
	}
	
	/**@author Saif.Ali
	 * Date-01/08/2017
	 * Used to insert the email events into the database*/
	@Override
	public String insertEventForTemplate(EmailEventAndTemplate emailEvent) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			emailEvent.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("insertEmailEventForTemplate", emailEvent);
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
		}finally{
			session.close();
		}
		return status;
	}
	
	/**@author Saif.Ali
	 * Date- 02/08/2017
	 * Used to get the list of Events of template to show in JSP*/
	public List<EmailEventAndTemplate> getEventForTemplateList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<EmailEventAndTemplate> getEventForTemplateList =null;
		try{
			getEventForTemplateList= session.selectList("getListOfEmailEventForTemplate");
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally{
			session.close();
		}
		return getEventForTemplateList;
	}
	
	/**@author Saif.Ali
	 * Date- 02/08/2017
	 * Used to edit the Events of template to show in JSP*/
	@Override
	public String editEventForTemplate(EmailEventAndTemplate emailEvent) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			emailEvent.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.update("updateEmailEventForTemplate", emailEvent);
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
		}finally{
			session.close();
		}
		return status;
	}
	
	/**@author Saif.Ali
	 * Date-03/08/2017
	 * Used to insert the email template into the database*/
	@Override
	public String insertTemplateForEvent(EmailEventAndTemplate emailEvent) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			emailEvent.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("insertTemplateForEmailEvent", emailEvent);
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
		}finally{
			session.close();
		}
		return status;
	}
	
	/**@author Saif.Ali
	 * Date-03/08/2017
	 * Used to get the list of email template into the database*/
	@Override
	public List<EmailEventAndTemplate> getListOfTemplateForEvent() {
		SqlSession session =sqlSessionFactory.openSession();
		List<EmailEventAndTemplate> emailEventTemplateList= null;
		try{
			emailEventTemplateList= session.selectList("getEmailTemplateListToShow");
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally{
			session.close();
		}
		return emailEventTemplateList;
	}
	
	/**@author Saif.Ali
	 * Date-04/08/2017
	 * Used to get the list of events from the database*/
	@Override
	public List<EmailEventAndTemplate> getEventListForMapping() {
		SqlSession session =sqlSessionFactory.openSession();
		List<EmailEventAndTemplate> emailEventListForMapping= null;
		try{
			emailEventListForMapping= session.selectList("getEmailEventListForMapping");
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally{
			session.close();
		}
		return emailEventListForMapping;
	}
	
	/**@author Saif.Ali
	 * Date-04/08/2017
	 * Used to get the list of templates from the database*/
	@Override
	public List<EmailEventAndTemplate> getTemplateListForMapping() {
		SqlSession session =sqlSessionFactory.openSession();
		List<EmailEventAndTemplate> emailTemplateListForMapping= null;
		try{
			emailTemplateListForMapping= session.selectList("getEmailTemplateListForMapping");
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally{
			session.close();
		}
		return emailTemplateListForMapping;
	}

	/**@author Saif.Ali
	 * Date-04/08/2017
	 * Used to insert the email and template mapping into the database*/
	@Override
	public String insertEmailEventAndTemplateMapping(EmailEventAndTemplate emailEvent) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			emailEvent.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("insertEmailEventAndTemplateMapping", emailEvent);
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
		}finally{
			session.close();
		}
		return status;
	}
	
	/**@author Saif.Ali
	 * Date- 04/08/2017
	 * Used to get the email and template mapping data into the database*/
	public List<EmailEventAndTemplate> getEmailEventTemplateMappingList()
	{
		SqlSession session =sqlSessionFactory.openSession();
		List<EmailEventAndTemplate> emailEventTemplateMappingList= null;
		try{
			emailEventTemplateMappingList= session.selectList("getMappingListOfEmailEventAndTemplate");
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally{
			session.close();
		}
		return emailEventTemplateMappingList;
	}
	
	/**@author Saif.Ali
	 * Date- 04/08/2017
	 * Used to get the template data from the database*/
	public EmailEventAndTemplate getTheTemplateValuesToEdit(String templateCode){
		SqlSession session =sqlSessionFactory.openSession();
		EmailEventAndTemplate emailEvent= new EmailEventAndTemplate();
		try{
			emailEvent.setTemplateCode(templateCode);
			emailEvent= session.selectOne("getTemplateDetailsToEditForEvent",emailEvent);
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally{
			session.close();
		}
		return emailEvent;
	}
	
	/**@author Saif.Ali
	 * Date- 04/08/2017
	 * Used to update the template */
	public String editTemplateInformationForemail(EmailEventAndTemplate emailEvent)
	{
		String status="updatesuccess";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			emailEvent.setTemplateCode(emailEvent.getTemplateCode());
			emailEvent.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.update("updateTemplateInformationForEmail", emailEvent);
		}catch(Exception e) {
			e.printStackTrace();
			status="updatefail";
			logger.error(e);
		}finally{
			session.close();
		}
		return status;
	}
	//missing link integration 17042018
	@Override
	public List<Question> getAllSurveyList() {
		
		List<Question> surveyList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getAllJobDetails() method of TicketDaoImpl");			
			surveyList = session.selectList("getAllSurveyList");
			
		} catch (NullPointerException e) {
			
			logger.error("getAllJobDetails() In TicketDAOImpl.java: NullPointerException" + e);
		} catch (Exception e) {
			
			logger.error("getAllJobDetails() In TicketDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return surveyList;
	}
	
	/*<!-- added By ranita.sur on 28082017 for mapping with survey -->*/
	//missing link integration 17042018
	@Override
	public String submitMapWithServey(JobType jt) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			jt.setJobTypeObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
			session.insert("insertCategorySurveyMapping", jt);
			
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
		}finally{
			session.close();
		}
		return status;
	}
	/*<!-- added By ranita.sur on 29082017 for mapping with survey -->*/
	//missing link integration 17042018
	@Override
	public List<JobType> getAllTaskSurveyList() {
		
		List<JobType> getTaskSurveyList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getAllJobDetails() method of TicketDaoImpl");			
			getTaskSurveyList = session.selectList("getAllTaskSurveyList");
			
		} catch (NullPointerException e) {
			
			logger.error("getAllJobDetails() In TicketDAOImpl.java: NullPointerException" + e);
		} catch (Exception e) {
			
			logger.error("getAllJobDetails() In TicketDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return getTaskSurveyList;
	}

//Added By Naimisha 16102017
	@Override
	public String insertIntoCategoryAndCategoryTaskMapping(JobType jobType) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info("In insertIntoCategoryAndCategoryTaskMapping() method of AdministratorDAOImpl");		
			jobType.setJobTypeObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
			int insertStatus = session.insert("insertIntoCategory", jobType);
			List<String> taskList = jobType.getStringList();
			if(null != taskList && taskList.size() != 0){
				for(String task : taskList){
					jobType.setJobTypeCode(task);
					int mappingInsertStatus = session.insert("insertIntoCategoryTaskMapping", jobType);
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("insertIntoCategoryAndCategoryTaskMapping() In AdministratorDAOImpl.java: Exception" + e);
		}finally{
			session.close();
		}
		return status;
	}

	//missing link integration 17042018
	@Override
	public List<JobType> getCategoryList() {
		List<JobType> getCategoryList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getCategoryList() method of AdministratorDAOImpl");			
			getCategoryList = session.selectList("getCategoryList");
			
		}catch (Exception e) {
			
			logger.error("getCategoryList() In AdministratorDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return getCategoryList;
	}


	@Override
	public List<JobType> getTaskistForCategory(String categoryCode) {
		List<JobType> getCategoryList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getTaskistForCategory() method of AdministratorDAOImpl");			
			getCategoryList = session.selectList("getTaskistForCategory",categoryCode);
			
		}catch (Exception e) {
			
			logger.error("getTaskistForCategory() In AdministratorDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return getCategoryList;
	}


	@Override
	public String updateIntoCategoryAndCategoryTaskMapping(JobType jobType) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info("In updateIntoCategoryAndCategoryTaskMapping() method of AdministratorDAOImpl");		
			
			int updateStatus = session.update("updateIntoCategory", jobType);
			int inactiveStatus = session.update("inactiveCategoryTaskMapping", jobType);
			List<String> taskList = jobType.getStringList();
			if(null != taskList && taskList.size() != 0){
				for(String task : taskList){
					jobType.setJobTypeCode(task);
					JobType jobTypeObj = session.selectOne("selecInactivetCategoryTaskMapping", jobType);
					if(null != jobTypeObj){
						int mappingUpdateStatus = session.insert("updateIntoCategoryTaskMapping", jobType);
					}else{
						int mappingInsertStatus = session.insert("insertCategoryTaskMapping", jobType);
					}
					
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("updateIntoCategoryAndCategoryTaskMapping() In AdministratorDAOImpl.java: Exception" + e);
		}finally{
			session.close();
		}
		return status;
	}


	@Override
	public String insertCategoryRecipientMapping(JobType jobType) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info("In insertCategoryRecipientMapping() method of AdministratorDAOImpl");		
			jobType.setJobTypeObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
			
			List<String> taskList = jobType.getStringList();
			if(null != taskList && taskList.size() != 0){
				for(String task : taskList){
					jobType.setJobTypeCode(task);
					int mappingInsertStatus = session.insert("insertCategoryRecipientMapping", jobType);
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("insertCategoryRecipientMapping() In AdministratorDAOImpl.java: Exception" + e);
		}finally{
			session.close();
		}
		return status;
	}


	@Override
	public List<JobType> getCategoryListForRecipientMapping() {
		List<JobType> getCategoryList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getCategoryListForRecipientMapping() method of AdministratorDAOImpl");			
			getCategoryList = session.selectList("getCategoryListForRecipientMapping");
			
		}catch (Exception e) {
			
			logger.error("getCategoryListForRecipientMapping() In AdministratorDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return getCategoryList;
	}


	@Override
	public List<JobType> getCategoryRecipientMapping(String categoryCode) {
		List<JobType> getCategoryList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getCategoryRecipientMapping() method of AdministratorDAOImpl");			
			getCategoryList = session.selectList("getCategoryRecipientMapping",categoryCode);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getCategoryRecipientMapping() In AdministratorDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return getCategoryList;
	}


	@Override
	public String editCategoryRecipientMapping(JobType jobType) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info("In editCategoryRecipientMapping() method of AdministratorDAOImpl");		
			
			
			int inactiveStatus = session.update("inactiveCategoryApproverGroupMapping", jobType);
			List<String> recipientList = jobType.getStringList();
			if(null != recipientList && recipientList.size() != 0){
				for(String recipient : recipientList){
					jobType.setJobTypeCode(recipient);
					JobType jobTypeObj = session.selectOne("selecInactivetCategoryApproverGroupMapping", jobType);
					if(null != jobTypeObj){
						int mappingUpdateStatus = session.insert("updateIntoCategoryApproverGroupMapping", jobType);
					}else{
						int mappingInsertStatus = session.insert("insertIntoCategoryApproverGroupMapping", jobType);
					}
					
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("editCategoryRecipientMapping() In AdministratorDAOImpl.java: Exception" + e);
		}finally{
			session.close();
		}
		return status;
	}


	@Override
	public List<Ticket> getAllTicketStatusList() {
		List<Ticket> getTicketList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getAllTicketStatusList() method of AdministratorDAOImpl");			
			getTicketList = session.selectList("getAllTicketStatusList");
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllTicketStatusList() In AdministratorDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return getTicketList;
	}
	
	
	/*Added by ranita.sur on 17102017 for editing the recipient group*/
	@Override
	public List<Approver> getUserIdListForApprover(Approver approverCode) {
		List<Approver> getCategoryList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getTaskistForCategory() method of AdministratorDAOImpl");			
			getCategoryList = session.selectList("selectRecipientGroupDetails",approverCode);
			System.out.println("LN 2439  ::"+getCategoryList.size());
		}catch (Exception e) {
			
			logger.error("getTaskistForCategory() In AdministratorDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return getCategoryList;
	}
	
	@Override
	public String updateIntoApproverGroupTaskMapping(Approver approver) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info("In updateIntoCategoryAndCategoryTaskMapping() method of AdministratorDAOImpl");		
			
			int updateStatus = session.update("updateIntoApproverGroup", approver);
			int inactiveStatus = session.update("inactiveApproverGroupMapping", approver);
			List<String> userIdist = approver.getStringList();
			for(String userId : userIdist){
				//jobType.setJobTypeCode(task);
				approver.setApproverGroupDesc(userId);
				approver.setObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
				Approver approverObj = session.selectOne("selectApproverGroupMapping", approver);
				if(null != approverObj){
					int mappingUpdateStatus = session.insert("updateIntoApproverGroupResourceMapping", approver);
				}else{
					int mappingInsertStatus = session.insert("insertIntoApproverGroupResourceMapping", approver);
				}
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("updateIntoCategoryAndCategoryTaskMapping() In AdministratorDAOImpl.java: Exception" + e);
		}finally{
			session.close();
		}
		return status;
	}


	@Override
	public String insertTaskStatus(Ticket ticket) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info("In insertTaskStatus() method of AdministratorDAOImpl");		
			ticket.setTicketObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
			
			
					int insertStatus = session.insert("insertTaskStatus", ticket);
				
			
			
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
			
			logger.error("insertTaskStatus() In AdministratorDAOImpl.java: Exception" + e);
		}finally{
			session.close();
		}
		return status;
	}


	@Override
	public List<Ticket> getAllTaskStatusList() {
		List<Ticket> getTastStatusList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getAllTaskStatusList() method of AdministratorDAOImpl");			
			getTastStatusList = session.selectList("getAllTaskStatusList");
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllTaskStatusList() In AdministratorDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return getTastStatusList;
	}
	
	/**Edit Tax Status Details
	 By Ranita.Sur 24102017**/
	@Override
	public String editTaskStatus(Ticket ticket) {
		SqlSession session =sqlSessionFactory.openSession();
		String updateResponse = "";
		System.out.println("HELLO DAOIMPL");
		try{
			int updateStatus = session.update("saveNewTaskStatus", ticket);
			if(updateStatus == 1){
				updateResponse = "Success";
			}else{
				updateResponse = "Fail";
			}
		}catch(Exception e){
			e.printStackTrace();
			updateResponse = "Fail";
		}
		return updateResponse;
	}

//Added by naimisha 03042018
	@Override
	public String insertCategoryKeyMapping(JobType jobType) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info("In insertCategoryKeyMapping() method of AdministratorDAOImpl");		
			jobType.setJobTypeObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
			
			List<String> taskList = jobType.getStringList();
			if(null != taskList && taskList.size() != 0){
				for(String task : taskList){
					jobType.setJobTypeCode(task);
					int mappingInsertStatus = session.insert("insertCategoryKeyMapping", jobType);
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("insertCategoryKeyMapping() In AdministratorDAOImpl.java: Exception" + e);
		}finally{
			session.close();
		}
		return status;
	}


	@Override
	public List<Ticket> getKeyForACategory(String category) {
		List<Ticket> keyList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getKeyForACategory() method of AdministratorDAOImpl");			
			keyList = session.selectList("getKeyForACategory",category);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getKeyForACategory() In AdministratorDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return keyList;
	}


	@Override
	public List<Ticket> getAllCategoryWithKeys() {
		List<Ticket> keyList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getAllCategoryWitkKeys() method of AdministratorDAOImpl");			
			keyList = session.selectList("getAllCategoryWithKeys");
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllCategoryWitkKeys() In AdministratorDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return keyList;
	}


	@Override
	public List<JobType> getAllKeysList() {
		List<JobType> keyList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getAllKeysList() method of AdministratorDAOImpl");			
			keyList = session.selectList("getAllKeysList");
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllKeysList() In AdministratorDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return keyList;
	}


	@Override
	public String editCategoryKeyMapping(JobType jobType) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info("In editCategoryKeyMapping() method of AdministratorDAOImpl");		
			
			
			int inactiveStatus = session.update("inactiveCategoryKeyMapping", jobType);
			List<String> recipientList = jobType.getStringList();
			if(null != recipientList && recipientList.size() != 0){
				for(String recipient : recipientList){
					jobType.setJobTypeCode(recipient);
					JobType jobTypeObj = session.selectOne("selecInactivetCategoryKeyMapping", jobType);
					if(null != jobTypeObj){
						int mappingUpdateStatus = session.insert("updateIntoCategoryKeyMapping", jobType);
					}else{
						int mappingInsertStatus = session.insert("insertCategoryKeyMapping", jobType);
					}
					
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("editCategoryKeyMapping() In AdministratorDAOImpl.java: Exception" + e);
		}finally{
			session.close();
		}
		return status;
	}

//Added by Naimisha 09042018
	
	@Override
	public String insertCategoryDepartmentMapping(JobType jobType) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			logger.info("In insertCategoryDepartmentMapping() method of AdministratorDAOImpl");		
			jobType.setJobTypeObjectId(encryptDecrypt.getBase64EncodedID("AdministratorDAOImpl"));
			
			List<String> categoryList = jobType.getStringList();
			if(null != categoryList && categoryList.size() != 0){
				for(String category : categoryList){
					jobType.setJobTypeCode(category);
					int mappingInsertStatus = session.insert("insertCategoryDepartmentMapping", jobType);
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("insertCategoryDepartmentMapping() In AdministratorDAOImpl.java: Exception" + e);
		}finally{
			session.close();
		}
		return status;
	}


	@Override
	public List<Department> getAllCategoryMappedWithDepartment() {
		List<Department> categoryDepartmentList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getAllCategoryMappedWithDepartment() method of AdministratorDAOImpl");			
			categoryDepartmentList = session.selectList("getAllCategoryMappedWithDepartment");
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllCategoryMappedWithDepartment() In AdministratorDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return categoryDepartmentList;
	}


	@Override
	public List<JobType> getCategoryListForADepartment(String departmentCode) {
		List<JobType> categorytList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getCategoryListForADepartment() method of AdministratorDAOImpl");			
			categorytList = session.selectList("getCategoryListForADepartment",departmentCode);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getCategoryListForADepartment() In AdministratorDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return categorytList;
	}


	@Override
	public List<Designation> getDesignationListForDepartment(String department) {
		List<Designation> designationList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getDesignationListForDepartment() method of AdministratorDAOImpl");			
			designationList = session.selectList("getDesignationListForDepartment",department);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getDesignationListForDepartment() In AdministratorDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return designationList;
	}


	@Override
	public List<Functionality> getAllFunctionalityList() {
		List<Functionality> designationList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getAllFunctionalityList() method of AdministratorDAOImpl");			
			designationList = session.selectList("getAllFunctionalityList");
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllFunctionalityList() In AdministratorDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return designationList;
	}


	@Override
	public JobType taskDetailsAgainstTaskCode(String taskCode) {
		JobType taskDetails = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In taskDetailsAgainstTaskCode() method of AdministratorDAOImpl");			
			taskDetails = session.selectOne("selectTaskDetailsAgainstTaskCode",taskCode);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("taskDetailsAgainstTaskCode() In AdministratorDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return taskDetails;
	}


	@Override
	public TicketStatus getTaskStatusForDuplicateCheck(Ticket ticket) {
		TicketStatus taskStatusDetails = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getTaskStatusForDuplicateCheck() method of AdministratorDAOImpl");			
			taskStatusDetails = session.selectOne("getTaskStatusForDuplicateCheck",ticket);
			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getTaskStatusForDuplicateCheck() In AdministratorDAOImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return taskStatusDetails;
	}
	
}
