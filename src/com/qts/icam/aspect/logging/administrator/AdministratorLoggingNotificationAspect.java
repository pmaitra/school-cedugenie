package com.qts.icam.aspect.logging.administrator;

import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qts.icam.model.administrator.AccessType;
import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.administrator.ServerConfiguration;
import com.qts.icam.model.administrator.UserGroup;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.service.academics.AcademicsService;
import com.qts.icam.service.common.CommonService;



@Aspect
@Component
public class AdministratorLoggingNotificationAspect {

	
	@Autowired
	CommonService commonService;
	
	@Autowired
	AcademicsService academicsService;
	
	private final static Logger logger = Logger.getLogger(AdministratorLoggingNotificationAspect.class);
	
	
	
	/**
	 * this method is used for log into database for  access addRoles() method  in AdministratorService   and calling to AdministratorServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.administrator.AdministratorService.addRoles(..))"+"&& args(role)",returning= "result")
	public void addRoles(JoinPoint joinPoint,Role role,Object result) {
		try{
			String functionalityName="Functionality Role Mapping";
			String subjectName="Create New Role";
			String moduleName="SYSTEM ADMINISTRATION";
			String loggingDesc="Create New Role"+role.getRoleName() +" by "+role.getUpdatedBy();
			
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(role.getUpdatedBy());
			if(result!=null && "SUCCESS".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addRoles(JoinPoint joinPoint,Role role,Object result) method in AdministratorLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access insertFunctionalityRoleMapping() method  in AdministratorService   and calling to AdministratorServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.administrator.AdministratorService.insertFunctionalityRoleMapping(..))"+"&& args(roleList)",returning= "result")
	public void insertFunctionalityRoleMapping(JoinPoint joinPoint,List<Role> roleList,Object result) {
		try{
			String functionalityName="Functionality Role Mapping";
			String subjectName="Role Functionality Mapped";
			String moduleName="SYSTEM ADMINISTRATION";
			String loggingDesc="Role functionality mapped ";
			if(null!=roleList && roleList.size()!=0){
				String updatedBy = "";
				loggingDesc=loggingDesc+" for ";
				for(Role role : roleList){
					loggingDesc=loggingDesc+role.getRoleName()+", ";
					updatedBy = role.getFunctionalityList().get(0).getUpdatedBy();
				}
				loggingDesc=loggingDesc+" by "+updatedBy;
			}
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			if(null!=roleList && roleList.size()!=0){
				loggingAspect.setUpdatedBy(roleList.get(0).getUpdatedBy());
			}			
			if(result!=null && "SUCCESS".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in insertFunctionalityRoleMapping(JoinPoint joinPoint,List<Role> roleList,Object result) method in AdministratorLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access addRoleContactMapping() method  in AdministratorService   and calling to AdministratorServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.administrator.AdministratorService.addRoleContactMapping(..))"+"&& args(resourceList)",returning= "result")
	public void addRoleContactMapping(JoinPoint joinPoint,List<Resource> resourceList,Object result) {
		try{
			String functionalityName="Functionality Role Mapping";
			String subjectName="Role Resource Mapping";
			String moduleName="SYSTEM ADMINISTRATION";
			String loggingDesc="";
			if(null!=resourceList && resourceList.size()!=0){
				loggingDesc=loggingDesc+"Role Resource Mapped For ";
				for(Resource resource : resourceList){
					loggingDesc=loggingDesc+resource.getUserId()+", ";
				}
				loggingDesc=loggingDesc+" by "+resourceList.get(0).getUpdatedBy();
			}
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			if(null!=resourceList && resourceList.size()!=0){
				loggingAspect.setUpdatedBy(resourceList.get(0).getUpdatedBy());
			}			
			if(result!=null && "SUCCESS".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addRoleContactMapping(JoinPoint joinPoint,List<Resource> resourceList,Object result) method in AdministratorLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access addRoleAccessMapping() method  in AdministratorService   and calling to AdministratorServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.administrator.AdministratorService.addRoleAccessMapping(..))"+"&& args(accessType)",returning= "result")
	public void addRoleAccessMapping(JoinPoint joinPoint,AccessType accessType,Object result) {
		try{
			String functionalityName="Access Type Role Mapping";
			String subjectName="Create New Access Type";
			String moduleName="SYSTEM ADMINISTRATION";
			String loggingDesc="Create New Access Type "+accessType.getAccessTypeName()+" by "+accessType.getUpdatedBy();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(accessType.getUpdatedBy());
			if(result!=null && "SUCCESS".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addRoleAccessMapping(JoinPoint joinPoint,AccessType accessType,Object result) method in AdministratorLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access updateAccessTypeRoleMapping() method  in AdministratorService   and calling to AdministratorServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.administrator.AdministratorService.updateAccessTypeRoleMapping(..))"+"&& args(accessType)",returning= "result")
	public void updateAccessTypeRoleMapping(JoinPoint joinPoint,AccessType accessType,Object result) {
		try{
			String functionalityName="Access Type Role Mapping";
			String subjectName="Update Access Type";
			String moduleName="SYSTEM ADMINISTRATION";
			String loggingDesc="Update Access Type "+accessType.getAccessTypeName()+" by "+accessType.getUpdatedBy();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(accessType.getUpdatedBy());
			if(result!=null && "SUCCESS".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in updateAccessTypeRoleMapping(JoinPoint joinPoint,AccessType accessType,Object result) method in AdministratorLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access inactiveAccessType() method  in AdministratorService   and calling to AdministratorServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.administrator.AdministratorService.inactiveAccessType(..))"+"&& args(accessType)",returning= "result")
	public void inactiveAccessType(JoinPoint joinPoint,AccessType accessType,Object result) {
		try{
			String functionalityName="Access Type Role Mapping";
			String subjectName="Delete Access Type";
			String moduleName="SYSTEM ADMINISTRATION";
			String loggingDesc="Deleted Access Type "+accessType.getAccessTypeName()+" - "+accessType.getAccessTypeCode() +" by "+accessType.getUpdatedBy();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(accessType.getUpdatedBy());
			if(result!=null && "SUCCESS".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in inactiveAccessType(JoinPoint joinPoint,AccessType accessType,Object result) method in AdministratorLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access addAccessTypeContactMapping() method  in AdministratorService   and calling to AdministratorServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.administrator.AdministratorService.addAccessTypeContactMapping(..))"+"&& args(resource)",returning= "result")
	public void addAccessTypeContactMapping(JoinPoint joinPoint,Resource resource,Object result) {
		try{
			String functionalityName="Access Type Role Mapping";
			String subjectName="Access Type Contact Mapping";
			String moduleName="SYSTEM ADMINISTRATION";
			String loggingDesc="Access Type Allocated For  "+resource.getUserId() +"by "+resource.getUpdatedBy();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(resource.getUpdatedBy());
			if(result!=null && "SUCCESS".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addAccessTypeContactMapping(JoinPoint joinPoint,Resource resource,Object result) method in AdministratorLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access addUserGroup() method  in AdministratorService   and calling to AdministratorServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.administrator.AdministratorService.addUserGroup(..))"+"&& args(userGroup)",returning= "result")
	public void addUserGroup(JoinPoint joinPoint,UserGroup userGroup,Object result) {
		try{
			String functionalityName="Create User Group";
			String subjectName="Create New User Group";
			String moduleName="SYSTEM ADMINISTRATION";
			String loggingDesc="Create New User Group  "+userGroup.getUserGroupName() +" by "+userGroup.getUpdatedBy();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(userGroup.getUpdatedBy());
			if(result!=null && "SUCCESS".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addUserGroup(JoinPoint joinPoint,UserGroup userGroup,Object result) method in AdministratorLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * this method is used for log into database for  access insertServerConfigurationLDAP() method  in AdministratorService   and calling to AdministratorServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.administrator.AdministratorService.insertServerConfigurationLDAP(..))"+"&& args(serverConfiguration)",returning= "result")
	public void insertServerConfigurationLDAP(JoinPoint joinPoint,ServerConfiguration serverConfiguration,Object result) {
		try{
			String functionalityName="Insert LDAP Configuration";
			String subjectName="Insert LDAP Configuration";
			String moduleName="SYSTEM ADMINISTRATION";
			String loggingDesc="Insert LDAP Configuration  "+serverConfiguration.getDirectoryServerUserDN()+" "+serverConfiguration.getDirectoryServerBaseDN();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(serverConfiguration.getUpdatedBy());
			if(result!=null && "successMessage".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in insertServerConfigurationLDAP(JoinPoint joinPoint,ServerConfiguration serverConfiguration,Object result) method in AdministratorLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access insertServerConfigurationDB() method  in AdministratorService   and calling to AdministratorServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.administrator.AdministratorService.insertServerConfigurationDB(..))"+"&& args(serverConfiguration)",returning= "result")
	public void insertServerConfigurationDB(JoinPoint joinPoint,ServerConfiguration serverConfiguration,Object result) {
		try{
			String functionalityName="Insert DB Configuration";
			String subjectName="Insert DB Configuration";
			String moduleName="SYSTEM ADMINISTRATION";
			String loggingDesc="Insert DB Configuration  "+serverConfiguration.getJdbcDatabaseName()+" "+serverConfiguration.getJdbcURL();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(serverConfiguration.getUpdatedBy());
			if(result!=null && "successMessage".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in insertServerConfigurationDB(JoinPoint joinPoint,ServerConfiguration serverConfiguration,Object result) method in AdministratorLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access insertServerConfigurationEMAIL() method  in AdministratorService   and calling to AdministratorServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.administrator.AdministratorService.insertServerConfigurationEMAIL(..))"+"&& args(serverConfiguration)",returning= "result")
	public void insertServerConfigurationEMAIL(JoinPoint joinPoint,ServerConfiguration serverConfiguration,Object result) {
		try{
			String functionalityName="Insert EMAIL Configuration";
			String subjectName="Insert EMAIL Configuration";
			String moduleName="SYSTEM ADMINISTRATION";
			String loggingDesc="Insert EMAIL Configuration  "+serverConfiguration.getMailServerIp()+" "+serverConfiguration.getMailUserName();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(serverConfiguration.getUpdatedBy());
			if(result!=null && "successMessage".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in insertServerConfigurationEMAIL(JoinPoint joinPoint,ServerConfiguration serverConfiguration,Object result) method in AdministratorLoggingNotificationAspect.java", e);
		}
	}
	
	
	
	/**
	 * this method is used for log into database for  access insertSMSServerConfiguration() method  in AdministratorService   and calling to AdministratorServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.administrator.AdministratorService.insertSMSServerConfiguration(..))"+"&& args(serverConfiguration)",returning= "result")
	public void insertSMSServerConfiguration(JoinPoint joinPoint,ServerConfiguration serverConfiguration,Object result) {
		try{
			String functionalityName="Insert SMS Configuration";
			String subjectName="Insert SMS Configuration";
			String moduleName="SYSTEM ADMINISTRATION";
			String loggingDesc="Insert SMS Configuration  "+serverConfiguration.getSmsURL()+" "+serverConfiguration.getSenderId();
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(serverConfiguration.getUpdatedBy());
			if(result!=null && "successMessage".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in insertSMSServerConfiguration(JoinPoint joinPoint,ServerConfiguration serverConfiguration,Object result) method in AdministratorLoggingNotificationAspect.java", e);
		}
	}
	
	
	
	
	/**
	 * this method is used for log into database for  access createTicketingSLA() method  in AdministratorService   and calling to AdministratorServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.administrator.AdministratorService.createTicketingSLA(..))"+"&& args(ticketList)",returning= "result")
	public void createTicketingSLA(JoinPoint joinPoint,List<Ticket> ticketList,Object result) {
		try{
			String functionalityName="Set Up SLA For Ticketing";
			String subjectName="Create SLA For Ticketing";
			String moduleName="SYSTEM ADMINISTRATION";
			String loggingDesc="";
			if(null!=ticketList && ticketList.size()!=0){
				for(Ticket ticket : ticketList){
					if(null!=ticket.getModuleList())
						for(Module module : ticket.getModuleList())
					loggingDesc="Create SLA For Ticketing For Module "+module.getModuleName();
				}
			}
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			if(null!=ticketList && ticketList.size()!=0){
				loggingAspect.setUpdatedBy(ticketList.get(0).getUpdatedBy());
			}			
			if(result!=null && "true".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in createTicketingSLA(JoinPoint joinPoint,List<Ticket> ticketList,Object result) method in AdministratorLoggingNotificationAspect.java", e);
		}
	}
	
	
	
	
	
}
