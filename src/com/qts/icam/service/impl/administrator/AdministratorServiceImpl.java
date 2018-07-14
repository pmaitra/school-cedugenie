package com.qts.icam.service.impl.administrator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;

import com.qts.icam.dao.administrator.AdministratorDAO;
import com.qts.icam.dao.bulkdata.DataDAO;
import com.qts.icam.dao.common.CommonDao;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.administrator.AccessType;
import com.qts.icam.model.administrator.Approver;
import com.qts.icam.model.administrator.EmailEventAndTemplate;
import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.administrator.ProfileMatrix;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.administrator.ServerConfiguration;
import com.qts.icam.model.administrator.StaffForXml;
import com.qts.icam.model.administrator.StudentForXml;
import com.qts.icam.model.administrator.UserGroup;
import com.qts.icam.model.common.EmailDetails;
import com.qts.icam.model.common.LoggingAspect;
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
import com.qts.icam.service.administrator.AdministratorService;
import com.qts.icam.utility.bulkdata.DataUtility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;
import com.qts.icam.utility.ldap.Ldap;
import com.qts.icam.utility.mailservice.EmailSender;
import com.qts.icam.utility.messageservice.MessageSender;



@Service
public class AdministratorServiceImpl implements AdministratorService{

	
	
	@Autowired
	AdministratorDAO administratorDAO;
	
	@Autowired
	CommonDao commonDao;
	
	@Autowired
	DataUtility dataUtility;
	
	@Autowired
	DataDAO dataDAO;
	
	@Autowired
	Ldap ldap;
	
	@Autowired
	EmailSender emailSender;
	
	//@Autowired
	//MessageSender messageSender;
	
	@Autowired
	private ServletContext servletContext;
	
	List<Role> roleList = null;
	List<AccessType> accessTypeList= null;
	List<Resource> resourceList = null;
	List<UserGroup> userGroupList = null;
	List<Approver> approverGroupList = null;
	
	private final static Logger logger = Logger.getLogger("AdministratorServiceImpl.class");


	@Override
	public List<Module> getmoduleList() throws CustomException {		
		return administratorDAO.getmoduleList();
	}


	@Override
	public List<Role> getRolesForModule(String moduleCode)
			throws CustomException {
		return administratorDAO.getRolesForModule(moduleCode);
	}


	@Override
	public String addRoles(Role role) throws CustomException {
		return administratorDAO.addRoles(role);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String inActiveRole(List<Role> roleList) throws CustomException {
		String message=null;
		try{
			if (null!=roleList && roleList.size() != 0) {
				for (Role role : roleList) {
					message=administratorDAO.inActiveRole(role);
				}
				logger.info("Role is Inactivated");
			}
		}catch(Exception e){
			CustomException.exceptionHandler(e);
		}
		return message;
	}


	@Override
	public Resource getResourceAndRolesFromDB() throws CustomException {
		Resource resource=administratorDAO.getResourceAndRolesFromDB();
		List<Role> roleList=new ArrayList<Role>();
		roleList=commonDao.getAllRoleList();
		if(null!=roleList && roleList.size()!=0){
			resource.setRoleList(roleList);
		}
		return resource;
		
	}



	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String addRoleContactMapping(List<Resource> resourceList)
			throws CustomException {
		return administratorDAO.addRoleContactMapping(resourceList);
	}


	@Override
	public List<Role> getlistRoleContactMapping() throws CustomException {
		roleList = administratorDAO.getlistRoleContactMapping();
		return roleList;
	}


	@Override
	public PagedListHolder<Role> getlistRoleContactMappingPaging(HttpServletRequest request) {
		logger.info("In getlistRoleContactMappingPaging(HttpServletRequest request) method of AdministratorServiceImpl");
		PagedListHolder<Role> pagedListHolder = new PagedListHolder<Role>(roleList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}
	
	@Override
	public List<Role> searchRoleContactmapping(Map<String, Object> parameters) throws CustomException {
			try {
				roleList = administratorDAO.searchRoleContactmapping(parameters);				
			} catch (CustomException e) {			
				CustomException.exceptionHandler(e);
			}
			return roleList;
		}
	


	@Override
	public List<Resource> getRoleContactMapping(Role role)
			throws CustomException {		
		return administratorDAO.getRoleContactMapping(role);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String editRoleContactMapping(List<Resource> resourceList)
			throws CustomException {
		return administratorDAO.editRoleContactMapping(resourceList);
	}


	@Override
	public List<AccessType> getAccessTypeList() throws CustomException {
		accessTypeList=administratorDAO.getAccessTypeList();
		return accessTypeList;
	}


	@Override
	public PagedListHolder<AccessType> getAccessTypeListPaging(HttpServletRequest request) {
		logger.info("In getAccessTypeListPaging(HttpServletRequest request) method of LAdministratorServiceImpl");
		PagedListHolder<AccessType> pagedListHolder = new PagedListHolder<AccessType>(accessTypeList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String addRoleAccessMapping(AccessType accessType)
			throws CustomException {		
		return administratorDAO.addRoleAccessMapping(accessType);
	}


	@Override
	public AccessType editAccessTypeRoleMapping(AccessType accessType)
			throws CustomException {
		return administratorDAO.editAccessTypeRoleMapping(accessType);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String updateAccessTypeRoleMapping(AccessType accessType)
			throws CustomException {
		return administratorDAO.updateAccessTypeRoleMapping(accessType);
	}


	@Override
	public String inactiveAccessType(AccessType accessType)
			throws CustomException {
		return administratorDAO.inactiveAccessType(accessType);
	}


	@Override
	public Resource getResourceAndAccessFromDB() throws CustomException {
		return administratorDAO.getResourceAndAccessFromDB();
	}


	@Override
	public String addAccessTypeContactMapping(Resource resource)
			throws CustomException {
		return administratorDAO.addAccessTypeContactMapping(resource);
	}


	@Override
	public List<Resource> getAccessTypeContactMappingList()
			throws CustomException {
		resourceList = administratorDAO.listAccessTypeContactMapping();
		return resourceList;
	}


	@Override
	public PagedListHolder<Resource> getListAccessTypeContactMappingPaging(
			HttpServletRequest request) throws CustomException {
		logger.info("In getListAccessTypeContactMappingPaging(HttpServletRequest request) method of AdministratorServiceImpl");
		PagedListHolder<Resource> pagedListHolder = new PagedListHolder<Resource>(resourceList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}


	@Override
	public void accessTypeContactMappingSearch(Map<String, Object> parameters)throws CustomException {	
		resourceList = administratorDAO.accessTypeContactMappingSearch(parameters);
	}


	@Override
	public String inactiveAccessTypeContactMapping(String resourceId,
			String updatedBy) throws CustomException {		
		return administratorDAO.inactiveAccessTypeContactMapping(resourceId,updatedBy);
	}


	@Override
	public Module getFunctionalitiesForRole(Role role) throws CustomException {
		return administratorDAO.getFunctionalitiesForRole(role);
	}


	@Override
	public boolean writeExcelForFunctionalityRoleMapping(Module module,
			String path, String fileName)
			throws CustomException {
		boolean status = false;
		if(path!= null && fileName!=null){
			path=path+fileName;
			int sheetCount=0;
			HSSFWorkbook workbook = new HSSFWorkbook();         
			//Create a blank sheet
			for(Role role:module.getRoleList()){
				try  {	
					HSSFSheet sheet = workbook.createSheet(role.getRoleName());	 
			        sheet.autoSizeColumn(5);
			        sheet.protectSheet("secretPassword");		        
			        			        			        
			        HSSFFont hSSFFont = workbook.createFont();
			        hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
			        hSSFFont.setFontHeightInPoints((short) 12);
			        hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			        hSSFFont.setColor(HSSFColor.BLACK.index);
			        			        
			        /* cell style for locking */
			        CellStyle lockedCellStyle = workbook.createCellStyle();
			        lockedCellStyle.setFont(hSSFFont);
			        lockedCellStyle.setLocked(true);
			        /* cell style for editable cells */
			        CellStyle unlockedCellStyle = workbook.createCellStyle();
			        unlockedCellStyle.setLocked(false);		        
			        
			        
			        //This data needs to be written (Object[])
			        Map<String, Object[]> data = new TreeMap<String, Object[]>();			        			       
			        data.put("1", new Object[] { "Functionality Name", "View","Insert","Update"});			        
			        String count="2";
			        for(Functionality  functionality: role.getFunctionalityList()){	
			        	data.put(count, new Object[] {functionality.getFunctionalityName(), functionality.isView(),functionality.isInsert(),functionality.isUpdate()});
			        	int loopControl=Integer.parseInt(count);
			        	loopControl++;
			        	count=""+loopControl;
			        }			        
			        //Iterate over data and write to sheet
					        Set<String> keyset = data.keySet();
					        int rownum = 0;
					        for (String key : keyset){
					            Row row = sheet.createRow(rownum++);
					            Object [] objArr = data.get(key);
					            int cellnum = 0;
					            for (Object obj : objArr) {
					               Cell cell = row.createCell(cellnum++);
					               if(obj instanceof String){
					                    cell.setCellValue((String)obj);
					                    cell.setCellStyle(lockedCellStyle);
					               }
					                else if(obj instanceof Boolean){
					                    cell.setCellValue((Boolean)obj);
					                    cell.setCellStyle(unlockedCellStyle);
					                }
					            }
					        }
					        					        
					        HSSFRow rows = workbook.getSheetAt(sheetCount).getRow(0);
					        for(int colNum = 0; colNum<rows.getLastCellNum();colNum++){   
					        	workbook.getSheetAt(sheetCount).autoSizeColumn(colNum);
					        }
					        sheetCount++;
					        //Write the workbook in file system  
				            FileOutputStream out = new FileOutputStream(new File(path));
				            workbook.write(out);
				            out.flush();
				            out.close();
				            status=true;
				            logger.info("functionalityRoleMapping.xlsx written successfully on "+path);
				            logger.info("functionalityRoleMapping.xlsx written successfully on "+path);
							}
					        catch (Exception e) {
					        	CustomException.exceptionHandler(e);
					            status=false;
					        	}
			        	}
					}				
			else{
			logger.error("Exception in writeExcelForFunctionalityRoleMapping() in administratorService for Excel download");
			}
		return status;	
		}


	@Override
	public String updateFunctionalityRoleMappingFromExcel(String uploadFile,
			String functionalityRoleMappingExcel, String moduleCode,
			String userId) throws CustomException {
		String message = null;	
		try {
			List<Role> roleList = dataUtility.updateFunctionalityRoleMappingFromExcel(uploadFile,functionalityRoleMappingExcel);			
			if(null!=roleList && roleList.size()!=0){
				int status=dataDAO.insertFunctionalityRoleMappingToDB(roleList,moduleCode,userId);
				if(status==0){
					message="FAILED";
				}else{
					message="SUCCESS";
				}
			}
			else{
				message="FAILED";
			}
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
		}
		return message;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String insertFunctionalityRoleMapping(List<Role> roleList)
			throws CustomException {		
		return administratorDAO.insertFunctionalityRoleMapping(roleList);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String addUserGroup(UserGroup userGroup) throws CustomException {
		return administratorDAO.addUserGroup(userGroup);
	}


	@Override
	public List<UserGroup> getAllUserGroups() throws CustomException {
		userGroupList=administratorDAO.getAllUserGroups();
		return userGroupList;
	}


	@Override
	public PagedListHolder<UserGroup> getUserGroupPagination(
			HttpServletRequest request) throws CustomException {
		logger.info("In getUserGroupPagination(HttpServletRequest request) method of AdministratorServiceImpl");
		PagedListHolder<UserGroup> pagedListHolder = new PagedListHolder<UserGroup>(userGroupList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize =5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String inactiveUserGroupDetails(UserGroup userGroup)
			throws CustomException {		
		return administratorDAO.inactiveUserGroupDetails(userGroup);
	}


	@Override
	public UserGroup getUserGroupDetails(UserGroup userGroup)
			throws CustomException {
		return administratorDAO.getUserGroupDetails(userGroup);
	}
	
	
	@Override
	public Module getNotificationDetails(String module)throws CustomException {
		return administratorDAO.getNotificationDetails(module);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String updateLoggingNotificationSetUp(Module module, String updatedBy)
			throws CustomException {
		return administratorDAO.updateLoggingNotificationSetUp(module,updatedBy);
	}


	@Override
	public List<Ticket> viewSLAForTicketing() throws CustomException {		
		return administratorDAO.viewSLAForTicketing();
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String createTicketingSLA(List<Ticket> ticketList)
			throws CustomException {
		return administratorDAO.createTicketingSLA(ticketList);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String updateTicketingSLA(Ticket ticket) throws CustomException {
		return administratorDAO.updateTicketingSLA(ticket);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String createArchiving(String academicYear, String userId)
			throws CustomException {
			return administratorDAO.createArchiving(academicYear,userId);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String purgeRecordPost(String academicYear, String userId)
			throws CustomException {
		return administratorDAO.purgeRecordPost(academicYear, userId);
	}


	@Override
	public StudentForXml readArchivedDataForStudent(File file)
			throws CustomException {
		return administratorDAO.readArchivedDataForStudent(file);
	}


	@Override
	public StaffForXml readArchivedDataForStaff(File file)
			throws CustomException {
		return administratorDAO.readArchivedDataForStaff(file);
	}


	@Override
	public List<LoggingAspect> getNotificationMediums() {
		return administratorDAO.getNotificationMediums();
	}


	@Override
	public String updateNotificationMediums(List<LoggingAspect> notificationList) {
		return administratorDAO.updateNotificationMediums(notificationList);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String insertApprovers(Approver approver) throws CustomException {		
		return administratorDAO.insertApprovers(approver);
	}


	@Override
	public List<Approver> getAllApproverGroups() throws CustomException {
		approverGroupList=administratorDAO.getAllApproverGroups();
		return approverGroupList;
	}


	@Override
	public PagedListHolder<Approver> getapproverGroupPagination(
			HttpServletRequest request) throws CustomException {
		logger.info("In getUserGroupPagination(HttpServletRequest request) method of AdministratorServiceImpl");
		PagedListHolder<Approver> pagedListHolder = new PagedListHolder<Approver>(approverGroupList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize =5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String inactiveApproverGroupDetails(Approver approver)
			throws CustomException {		
		return administratorDAO.inactiveApproverGroupDetails(approver);
	}


	@Override
	public Approver getApproverGroupDetails(Approver approver)
			throws CustomException {
		return administratorDAO.getApproverGroupDetails(approver);
	}


	/**Modified by @author Saif.Ali
	 * Date-31/07/2017
	 * Used to get the server configuration LDAP*/
	@Override
	public ServerConfiguration getServerConfigurationLDAP() throws CustomException {		
		Properties prop = new Properties();
		EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
		ServerConfiguration serverConfiguration = new ServerConfiguration();
		try {
			ClassPathResource resource = new ClassPathResource("configuration.properties");
			InputStream inputStream = resource.getInputStream();
			prop.load(inputStream);
			if (!prop.isEmpty()) {
				logger.info("Loaded Ldap Configuration in serverConfiguration :");
				// Reading Properties file and Populating Ldap model
				
				serverConfiguration.setDirectoryServerType((prop.getProperty("directoryServer.serverType") != null) ? prop
						.getProperty("directoryServer.serverType") : "");				
				String ldapUrl = ((prop.getProperty("directoryServer.url") != null) ? prop
						.getProperty("directoryServer.url") : "");	
				String splitLdapUrl[] = ldapUrl.split("//");
				serverConfiguration.setDirectoryServerUrl(splitLdapUrl[1]);					
				serverConfiguration.setDirectoryServerSecurityAuthenticationType((prop
						.getProperty("directoryServer.authenticationType") != null) ? prop
						.getProperty("directoryServer.authenticationType") : "");
				serverConfiguration.setDirectoryServerPort((prop.getProperty("directoryServer.port") != null) ? prop
						.getProperty("directoryServer.port") : "");
				serverConfiguration.setDirectoryServerUserDN((prop.getProperty("directoryServer.userDN") != null) ? prop
						.getProperty("directoryServer.userDN") : "");
				serverConfiguration.setDirectoryServerBaseDN((prop.getProperty("directoryServer.baseDN") != null) ? prop
						.getProperty("directoryServer.baseDN") : "");
				serverConfiguration.setDirectoryServerPassword((encryptDecrypt.decrypt(prop.getProperty("directoryServer.password")) != null) ? encryptDecrypt.decrypt(prop
						.getProperty("directoryServer.password")) : "");
				serverConfiguration.setDirectoryServerFilter((prop.getProperty("directoryServer.filter") != null) ? prop
						.getProperty("directoryServer.filter") : "");
				
			} else {
				logger.error("Server Cinfiguration file doesn't exist ");
			}
		} catch (IOException io) {
			CustomException.exceptionHandler(io);
			logger.error("Error in loading configuration.properties file in AdministratorController : ",io);
		} catch (Exception e) {
			CustomException.exceptionHandler(e);
			logger.error("serverConfigurationLDAP() In AdministratorController.java: Exception", e);
		}
		return serverConfiguration;
	}


	/**Modified by @author Saif.Ali
	 * Date-31/07/2017
	 * Used to insert the server configuration LDAP*/
	@Override
	public String insertServerConfigurationLDAP(ServerConfiguration serverConfiguration) throws CustomException {
		Properties prop = new Properties();		
		EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
		// Set DB Properties Value in Properties file		
		String status="errorMessage";
		try {
			logger.info("insertServerConfiguration() In AdministratorController.java: ");
			// Loading the properties file
			ClassLoader classLoader = getClass().getClassLoader();
			 File file = new File(classLoader.getResource("configuration.properties").getFile());
			 InputStream inputStream = new FileInputStream(file);
			prop.load(inputStream);
			inputStream.close();
		
			// Set DB Properties Value in Properties file // Trim user input values
			FileOutputStream out = new FileOutputStream(file);
			// Trim user input values
			if(serverConfiguration.getDirectoryServerType()!=null){
				String directoryServerType = serverConfiguration.getDirectoryServerType() .replaceAll("\\s+", "");
				prop.setProperty("directoryServer.serverType", directoryServerType);
			}			
			if(serverConfiguration.getDirectoryServerUrl()!=null){
				String directoryServerUrl = "ldap://" + serverConfiguration.getDirectoryServerUrl();
				directoryServerUrl = directoryServerUrl.replaceAll("\\s+", "");				
				prop.setProperty("directoryServer.url", directoryServerUrl);
			}
			if(serverConfiguration.getDirectoryServerSecurityAuthenticationType()!=null){
				String directoryServerAuthentication = serverConfiguration.getDirectoryServerSecurityAuthenticationType().replaceAll("\\s+", "");
				prop.setProperty("directoryServer.authenticationType", directoryServerAuthentication);
			}
			if(serverConfiguration.getDirectoryServerPort()!=null){
				String directoryServerPort = serverConfiguration.getDirectoryServerPort().replaceAll("\\s+", "");
				prop.setProperty("directoryServer.port", directoryServerPort);
			}
			
			if(serverConfiguration.getDirectoryServerUserDN()!=null){
				String userDN = serverConfiguration.getDirectoryServerUserDN().replaceAll("\\s+", "");
				prop.setProperty("directoryServer.userDN", userDN);
			}			
			if(serverConfiguration.getDirectoryServerBaseDN()!=null){
				String base = serverConfiguration.getDirectoryServerBaseDN().replaceAll("\\s+", "");
				prop.setProperty("directoryServer.baseDN", base);
			}
			if(serverConfiguration.getDirectoryServerPassword()!=null){
				String directoryServerPassword = encryptDecrypt.encrypt(serverConfiguration.getDirectoryServerPassword() .replaceAll("\\s+", ""));
				prop.setProperty("directoryServer.password", directoryServerPassword);
			}
			if(serverConfiguration.getDirectoryServerFilter()!=null){
				String directoryServerFilter = serverConfiguration.getDirectoryServerFilter().replaceAll("\\s+", "");
				prop.setProperty("directoryServer.filter", directoryServerFilter);
			}			
			prop.store(out, null);
			out.close();	
			status="successMessage";			
		} catch (IOException ex) {
			status="errorMessage";			
			logger.error("insertServerConfigurationLDAP() In AdministratorController.java: NullPointerException", ex);
		} catch (NullPointerException e) {
			status="errorMessage";
			logger.error("insertServerConfigurationLDAP() In AdministratorController.java: NullPointerException", e);
		} catch (Exception e) {
			status="errorMessage";
			logger.error("insertServerConfigurationLDAP() In AdministratorController.java: NullPointerException", e);
		}
		return status;
	}


	
	/**Modified by @author Saif.Ali
	 * Date-31/07/2017
	 * Used to get the details of Server Configuration DB*/
	@Override
	public ServerConfiguration getServerConfigurationDB()throws CustomException {
		Properties prop = new Properties();
		ServerConfiguration serverConfiguration = new ServerConfiguration();
		EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
		try {
			//File propertiesFile = new File(propertyFile);
			ClassPathResource resource = new ClassPathResource("configuration.properties");
			InputStream inputStream = resource.getInputStream();
			prop.load(inputStream);
			if (!prop.isEmpty()) {
				// Reading Properties file and Populating Ldap model						
				String dbUrl = ((prop.getProperty("jdbc.url") != null) ? prop.getProperty("jdbc.url") : "");				
				String[] parts = dbUrl.split("//");
				String[] urlWithDB = parts[1].split("/");
				String ip = urlWithDB[0];				
				
				serverConfiguration.setJdbcURL(ip);
				serverConfiguration.setJdbcUserName((encryptDecrypt.decrypt(prop.getProperty("jdbc.username")) != null) ? encryptDecrypt.decrypt(prop
						.getProperty("jdbc.username")) : "");				
				serverConfiguration.setJdbcPassword((encryptDecrypt.decrypt(prop.getProperty("jdbc.password")) != null) ? encryptDecrypt.decrypt(prop
						.getProperty("jdbc.password")) : "");
				serverConfiguration.setJdbcAcquireIncrement((prop.getProperty("jdbc.acquireIncrement") != null) ? prop
						.getProperty("jdbc.acquireIncrement") : "");				
				serverConfiguration.setJdbcDialect((prop.getProperty("jdbc.dialect") != null) ? prop
						.getProperty("jdbc.dialect") : "");				
				serverConfiguration.setJdbcDriverClassName((prop.getProperty("jdbc.driverClassName") != null) ? prop
						.getProperty("jdbc.driverClassName") : "");	
				serverConfiguration.setJdbcMaxActive((prop.getProperty("jdbc.maxActive") != null) ? prop
						.getProperty("jdbc.maxActive") : "");				
				serverConfiguration.setJdbcMaxIdleTime((prop.getProperty("jdbc.maxIdleTime") != null) ? prop
						.getProperty("jdbc.maxIdleTime") : "");	
				serverConfiguration.setJdbcMaxStatement((prop.getProperty("jdbc.maxStatements") != null) ? prop
						.getProperty("jdbc.maxStatements") : "");
				serverConfiguration.setJdbcPort((prop.getProperty("jdbc.port") != null) ? prop
						.getProperty("jdbc.port") : "");				
				serverConfiguration.setJdbcStatementCacheNumDeferredCloseThread((prop.getProperty("jdbc.statementCacheNumDeferredCloseThreads") != null) ? prop
						.getProperty("jdbc.statementCacheNumDeferredCloseThreads") : "");				
				serverConfiguration.setJdbcInitialSize((prop.getProperty("jdbc.initialSize") != null) ? prop
						.getProperty("jdbc.initialSize") : "");
				serverConfiguration.setJdbcDatabaseName((prop.getProperty("jdbc.databaseName") != null) ? prop
						.getProperty("jdbc.databaseName") : "");
			} else {
				logger.error("Configuration file doesn't exist ");
			}	
		} catch (IOException io) {
			logger.error("Error in loading configuration.properties file in AdministratorController : ",io);
		} catch (Exception e) {
			logger.error("serverConfigurationDB() In AdministratorController.java: Exception",e);
		}
		return serverConfiguration;
	}


	/**@author Saif.Ali
	 * Date-31/07/2017
	 * Used to insert the details of server configuration on DB*/
	@Override
	public String insertServerConfigurationDB( ServerConfiguration serverConfiguration) throws CustomException {
		String status="errorMessage";
		Properties prop = new Properties();
		EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
		try {
			logger.info("insertServerConfigurationDB() In AdministratorController.java: ");
			// Loading the properties file
			ClassLoader classLoader = getClass().getClassLoader();
			 File file = new File(classLoader.getResource("configuration.properties").getFile());
			 InputStream inputStream = new FileInputStream(file);
			prop.load(inputStream);
			inputStream.close();
		
			// Set DB Properties Value in Properties file // Trim user input values
			FileOutputStream out = new FileOutputStream(file);
	
			// Trim user input values
		
			String dbUrl = "jdbc:postgresql://" + serverConfiguration.getJdbcURL() + "/"
					+ serverConfiguration.getJdbcDatabaseName().replaceAll("\\s+", "");			
			if(null!=dbUrl && dbUrl!=""){
				prop.setProperty("jdbc.url", dbUrl);
			}
			
			if(serverConfiguration.getJdbcPort()!=null){
				String jdbcPort =(serverConfiguration.getJdbcPort().replaceAll("\\s+", ""));
				prop.setProperty("jdbc.port", jdbcPort);
			}
			
			if(serverConfiguration.getJdbcMaxStatement()!=null){
				String jdbcMaxStatement =(serverConfiguration.getJdbcMaxStatement().replaceAll("\\s+", ""));
				prop.setProperty("jdbc.maxStatements", jdbcMaxStatement);
			}
			
			if(serverConfiguration.getJdbcStatementCacheNumDeferredCloseThread()!=null){
				String jdbcStatementCacheNumDeferredCloseThread =(serverConfiguration.getJdbcStatementCacheNumDeferredCloseThread().replaceAll("\\s+", ""));
				prop.setProperty("jdbc.statementCacheNumDeferredCloseThreads", jdbcStatementCacheNumDeferredCloseThread);
			}
			
			if(serverConfiguration.getJdbcMaxIdleTime()!=null){
				String jdbcMaxIdleTime =(serverConfiguration.getJdbcMaxIdleTime().replaceAll("\\s+", ""));
				prop.setProperty("jdbc.maxIdleTime", jdbcMaxIdleTime);
			}
			
			if(serverConfiguration.getJdbcDriverClassName()!=null){
				String jdbcDriverClassName =(serverConfiguration.getJdbcDriverClassName().replaceAll("\\s+", ""));
				prop.setProperty("jdbc.driverClassName", jdbcDriverClassName);
			}
			
			if(serverConfiguration.getJdbcMaxActive()!=null){
				String jdbcMaxActive =(serverConfiguration.getJdbcMaxActive().replaceAll("\\s+", ""));
				prop.setProperty("jdbc.maxActive", jdbcMaxActive);
			}
			
			if(serverConfiguration.getJdbcDialect()!=null){
				String jdbcDialect =(serverConfiguration.getJdbcDialect().replaceAll("\\s+", ""));
				prop.setProperty("jdbc.dialect", jdbcDialect);
			}
			
			if(serverConfiguration.getJdbcInitialSize()!=null){
				String jdbcInitialSize =(serverConfiguration.getJdbcInitialSize().replaceAll("\\s+", ""));
				prop.setProperty("jdbc.initialSize", jdbcInitialSize);
			}
			
			if(serverConfiguration.getJdbcAcquireIncrement()!=null){
				String jdbcAcquireIncrement =(serverConfiguration.getJdbcAcquireIncrement().replaceAll("\\s+", ""));
				prop.setProperty("jdbc.acquireIncrement", jdbcAcquireIncrement);
			}
					
			if(serverConfiguration.getJdbcUserName()!=null){
				String jdbcUserName =encryptDecrypt.encrypt(serverConfiguration.getJdbcUserName().replaceAll("\\s+", ""));
				prop.setProperty("jdbc.username", jdbcUserName);
			}
			
			if(serverConfiguration.getJdbcPassword()!=null){
				String jdbcPassword =encryptDecrypt.encrypt(serverConfiguration.getJdbcPassword().replaceAll("\\s+", ""));
				prop.setProperty("jdbc.password", jdbcPassword);
			}
			
			if(serverConfiguration.getJdbcDatabaseName()!=null){
				String jdbcDatabaseName =(serverConfiguration.getJdbcDatabaseName().replaceAll("\\s+", ""));
				prop.setProperty("jdbc.databaseName", jdbcDatabaseName);
			}
			
			// Set DB Properties Value in Properties file				
			// Overwrite properties file values
			prop.store(out, null);
			out.close();
			status="successMessage";
		} catch (IOException ex) {
			status="errorMessage";
			logger.error("insertServerConfigurationDB() In AdministratorController.java: NullPointerException", ex);
		} catch (NullPointerException e) {
			status="errorMessage";
			logger.error("insertServerConfigurationDB() In AdministratorController.java: NullPointerException", e);
		} catch (Exception e) {
			status="errorMessage";
			logger.error("insertServerConfigurationDB() In AdministratorController.java: NullPointerException", e);
		}
		return status;
	}


	
	/**Modified by @author Saif.Ali
	 * Date-31/07/2017
	 * Used to get the details of Server Configuration EMAIL*/
	@Override
	public ServerConfiguration getServerConfigurationEMAIL(ServerConfiguration serverConfiguration) throws CustomException {
		Properties prop = new Properties();		
		EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
		try {
			ClassPathResource resource = new ClassPathResource("configuration.properties");
			InputStream inputStream = resource.getInputStream();
			prop.load(inputStream);
			if (!prop.isEmpty()) {
				// Reading Properties file and Populating Ldap model
							
				String mailUrl = ((prop.getProperty("mail.host") != null) ? prop
						.getProperty("mail.host") : "");
				serverConfiguration.setMailServerPort((prop.getProperty("mail.port") != null) ? prop
						.getProperty("mail.port") : "");
				serverConfiguration.setMailUserName((encryptDecrypt.decrypt(prop.getProperty("mail.username")) != null) ? encryptDecrypt.decrypt(prop
						.getProperty("mail.username")) : "");
				serverConfiguration.setMailPassword((encryptDecrypt.decrypt(prop.getProperty("mail.password")) != null) ? encryptDecrypt.decrypt(prop
						.getProperty("mail.password")) : "");
				
				
				serverConfiguration.setMailTransportProtocol((prop.getProperty("mail.transport.protocol") != null) ? prop
						.getProperty("mail.transport.protocol") : "");
				serverConfiguration.setMailSmtpAuth((prop.getProperty("mail.smtp.auth") != null) ? prop
						.getProperty("mail.smtp.auth") : "");
				serverConfiguration.setMailSmtpStarttlsEnable((prop.getProperty("mail.smtp.starttls.enable") != null) ? prop
						.getProperty("mail.smtp.starttls.enable") : "");
				serverConfiguration.setMailDebug((prop.getProperty("mail.debug") != null) ? prop
						.getProperty("mail.debug") : "");
												
				serverConfiguration.setMailServerIp(mailUrl);				
			} else {
				logger.error("Configuration file doesn't exist ");
			}
	
		} catch (IOException ex) {
			CustomException.exceptionHandler(ex);
		} catch (NullPointerException e) {
			logger.error("serverConfigurationEMAIL() In AdministratorController.java: NullPointerException", e);
		} catch (Exception e) {
			logger.error("serverConfigurationEMAIL() In AdministratorController.java: Exception", e);
		}		
		return serverConfiguration; 
	}


	/**Modified by @author Saif.Ali
	 * Date-31/07/2017
	 * Used to insert the details of server Configuration EMAIL*/
	@Override
	public String insertServerConfigurationEMAIL(
			ServerConfiguration serverConfiguration) throws CustomException {
		String status="errorMessage";	
		Properties prop = new Properties();
		EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
		try {
			logger.info("insertServerConfiguration() In AdministratorController.java: ");
			// Loading the properties file
			ClassLoader classLoader = getClass().getClassLoader();
			 File file = new File(classLoader.getResource("configuration.properties").getFile());
			 InputStream inputStream = new FileInputStream(file);
			prop.load(inputStream);
			inputStream.close();
		
			// Set DB Properties Value in Properties file // Trim user input values
			FileOutputStream out = new FileOutputStream(file);
		
			// Trim user input values
		
			if(null!=serverConfiguration.getMailServerIp()){
				String mailUrl = serverConfiguration.getMailServerIp().replaceAll("\\s+", "");
				prop.setProperty("mail.host", mailUrl);
			}
			
			if(null!=serverConfiguration.getMailServerPort()){
				String mailPort = serverConfiguration.getMailServerPort().replaceAll("\\s+", "");
				prop.setProperty("mail.port", mailPort);
			}
			
			if(null!=serverConfiguration.getMailUserName()){
				String mailUserName = encryptDecrypt.encrypt(serverConfiguration.getMailUserName().replaceAll("\\s+", ""));
				prop.setProperty("mail.username", mailUserName);
			}
			
			if(null!=serverConfiguration.getMailPassword()){
				String mailUserPassword = encryptDecrypt.encrypt(serverConfiguration.getMailPassword().replaceAll("\\s+", ""));
				prop.setProperty("mail.password", mailUserPassword);
			}
			
			if(null!=serverConfiguration.getMailTransportProtocol()){
				String mailTransportProtocol = serverConfiguration.getMailTransportProtocol().replaceAll("\\s+", "");
				prop.setProperty("mail.transport.protocol", mailTransportProtocol);
			}
			
			if(null!=serverConfiguration.getMailSmtpAuth()){
				String mailSmtpAuth = serverConfiguration.getMailSmtpAuth().replaceAll("\\s+", "");
				prop.setProperty("mail.smtp.auth", mailSmtpAuth);
			}
			
			if(null!=serverConfiguration.getMailSmtpStarttlsEnable()){
				String mailSmtpStarttlsEnable = serverConfiguration.getMailSmtpStarttlsEnable().replaceAll("\\s+", "");
				prop.setProperty("mail.smtp.starttls.enable", mailSmtpStarttlsEnable);
			}
			
			if(null!=serverConfiguration.getMailDebug()){
				String mailDebug = serverConfiguration.getMailDebug().replaceAll("\\s+", "");
				prop.setProperty("mail.debug", mailDebug);
			}			
	
			// Set DB Properties Value in Properties file			
			// Overwrite properties file values
			prop.store(out, null);
			out.close();
			status="successMessage";
		} catch (IOException ex) {
			status="errorMessage";	
			logger.error("insertServerConfigurationEMAIL() In AdministratorController.java: NullPointerException", ex);
		} catch (NullPointerException e) {
			status="errorMessage";	
			logger.error("insertServerConfigurationEMAIL() In AdministratorController.java: NullPointerException", e);
		} catch (Exception e) {
			status="errorMessage";	
			logger.error("insertServerConfigurationEMAIL() In AdministratorController.java: NullPointerException", e);
		}
		return status;
	}


	/**Modified by @author Saif.Ali
	 * Date-31/07/2017
	 * Used to get the details of Configuration SMS server*/
	@Override
	public ServerConfiguration getConfigureSMSServer() throws CustomException {
		Properties prop = new Properties();		
		EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
		ServerConfiguration serverConfiguration = new ServerConfiguration();
		try {	
			ClassPathResource resource = new ClassPathResource("configuration.properties");
			InputStream inputStream = resource.getInputStream();
			prop.load(inputStream);
			if (!prop.isEmpty()) {
				// Reading Properties file and Populating Ldap model
								
				serverConfiguration.setAuthkey((prop.getProperty("sms.authkey") != null) ? prop
						.getProperty("sms.authkey") : "");			
				serverConfiguration.setSenderId((prop.getProperty("sms.senderId") != null) ? prop
						.getProperty("sms.senderId") : "");
				serverConfiguration.setRoute((prop.getProperty("sms.route") != null) ? prop
						.getProperty("sms.route") : "");
				serverConfiguration.setSmsURL((prop.getProperty("sms.smsURL") != null) ? prop
						.getProperty("sms.smsURL") : "");
				serverConfiguration.setProxy((prop.getProperty("sms.proxy") != null) ? prop
						.getProperty("sms.proxy") : "");			
				
				serverConfiguration.setProxyUser((encryptDecrypt.decrypt(prop.getProperty("sms.proxy.user")) != null) ? encryptDecrypt.decrypt(prop
						.getProperty("sms.proxy.user")) : "");
				serverConfiguration.setProxyPassword((encryptDecrypt.decrypt(prop.getProperty("sms.proxy.password")) != null) ? encryptDecrypt.decrypt(prop
						.getProperty("sms.proxy.password")) : "");
				serverConfiguration.setProxySet((prop.getProperty("sms.https.proxySet") != null) ? prop
						.getProperty("sms.https.proxySet") : "");
				serverConfiguration.setProxyHost((prop.getProperty("sms.https.proxyHost") != null) ? prop
						.getProperty("sms.https.proxyHost") : "");
				serverConfiguration.setProxyPort((prop.getProperty("sms.https.proxyPort") != null) ? prop
						.getProperty("sms.https.proxyPort") : "");
			} else {
				logger.error("SMS Configuration file doesn't exist ");
			}
		} catch (IOException ex) {
			logger.error("insertServerConfigurationEMAIL() In AdministratorController.java: NullPointerException", ex);
		} catch (NullPointerException e) {
			logger.error("insertServerConfigurationEMAIL() In AdministratorController.java: NullPointerException", e);
		} catch (Exception e) {
			logger.error("insertServerConfigurationEMAIL() In AdministratorController.java: Exception", e);
		}	
		return serverConfiguration;
	}


	
	/**Modified by @author Saif.Ali
	 * Date- 31/07/2017*/
	@Override
	public String insertSMSServerConfiguration(
			ServerConfiguration serverConfiguration) throws CustomException {
		Properties prop = new Properties();
		EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
		String status="errorMessage";	
		try {
			logger.info("insertServerConfiguration() In AdministratorController.java: ");
			// Loading the properties file
			ClassLoader classLoader = getClass().getClassLoader();
			 File file = new File(classLoader.getResource("configuration.properties").getFile());
			 InputStream inputStream = new FileInputStream(file);
			prop.load(inputStream);
			inputStream.close();
		
			// Set DB Properties Value in Properties file // Trim user input values
			FileOutputStream out = new FileOutputStream(file);
			
			if(null!=serverConfiguration.getAuthkey()){
				prop.setProperty("sms.authkey", serverConfiguration.getAuthkey().replaceAll("\\s+", ""));
			}
			
			if(null!=serverConfiguration.getSenderId()){
				prop.setProperty("sms.senderId",  serverConfiguration.getSenderId().replaceAll("\\s+", ""));
			}
			
			if(null!=serverConfiguration.getRoute()){
				prop.setProperty("sms.route", serverConfiguration.getRoute().replaceAll("\\s+", ""));
			}
			
			if(null!=serverConfiguration.getSmsURL()){
				prop.setProperty("sms.smsURL", serverConfiguration.getSmsURL().replaceAll("\\s+", ""));
			}
			
			if(null!=serverConfiguration.getProxy()){
				prop.setProperty("sms.proxy", serverConfiguration.getProxy().replaceAll("\\s+", ""));
			}
			
			
			
			if(serverConfiguration.getProxy().replaceAll("\\s+", "").equalsIgnoreCase("yes")){
				
				if(null!=serverConfiguration.getProxyUser()){
					prop.setProperty("sms.proxy.user", encryptDecrypt.encrypt(serverConfiguration.getProxyUser().replaceAll("\\s+", "")));
				}
				logger.info(serverConfiguration.getProxyPassword());
				if(null!=serverConfiguration.getProxyPassword()){
					prop.setProperty("sms.proxy.password", encryptDecrypt.encrypt(serverConfiguration.getProxyPassword().replaceAll("\\s+", "")));
				}
				if(null!=serverConfiguration.getProxySet()){
					prop.setProperty("sms.https.proxySet", serverConfiguration.getProxySet().replaceAll("\\s+", ""));
				}
				if(null!=serverConfiguration.getProxyHost()){
					prop.setProperty("sms.https.proxyHost",  serverConfiguration.getProxyHost().replaceAll("\\s+", ""));
				}
				if(null!=serverConfiguration.getProxyPort()){
					prop.setProperty("sms.https.proxyPort",  serverConfiguration.getProxyPort().replaceAll("\\s+", ""));
				}			
			}
			// Overwrite properties file values
			prop.store(out, null);
			out.close();
			status="successMessage";
		} catch (IOException ex) {			
			status="errorMessage";	
			logger.error("insertSMSServerConfiguration() In AdministratorController.java: NullPointerException", ex);
		} catch (NullPointerException e) {
			status="errorMessage";	
			logger.error("insertSMSServerConfiguration() In AdministratorController.java: NullPointerException", e);
		} catch (Exception e) {
			status="errorMessage";	
			logger.error("insertSMSServerConfiguration() In AdministratorController.java: NullPointerException", e);
		}
		return status;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String createPassword(LoginForm login) {
		String status = "fail";
		try{
			status = ldap.createPassword(login);
			if(status.equals("success")){
				int updateStatus = administratorDAO.insertUserPasswordStatus(login);
				if(updateStatus!=0){
					try{
						String emailSubjectTemplate = servletContext.getInitParameter("automailtemplatesubjectpath");
						String emailBodyTemplate = servletContext.getInitParameter("automailtemplatebodypath");
						Resource senderDetails = commonDao.getResourceDetails(login.getUpdatedBy());
						Resource receiverDetails = commonDao.getResourceDetails(login.getUserId());
						if(receiverDetails != null && senderDetails != null){
							EmailDetails emailDetails = new EmailDetails();
							emailDetails.setEmailDetailsSubject("Login ID generated!!!");
							emailDetails.setEmailDetailsDesc("Hi! "+login.getUserId()+"\nYou can login with \nUser Id: "+login.getUserId()+"\nPassword: "+login.getPassword());
							emailDetails.setEmailDetailsReceiver(login.getUserId());
							emailDetails.setStatus("automail");
							emailSender.sendMail(login.getUpdatedBy(), login.getUserId(),emailDetails,emailSubjectTemplate,emailBodyTemplate);										
							//messageSender.sendMessage(receiverDetails.getMobile(), emailDetails.getEmailDetailsDesc());
						}
					}catch(MailSendException mse){
						logger.error("Mail Exception in createPassword() in BackOfficeServiceImpl", mse);
						mse.printStackTrace();
					}
					status = "success";
				}else{
					status = "fail";
				}
			}
		}catch(Exception e){
			logger.error("Exception in createPassword() in BackOfficeServiceImpl", e);
			status = "fail";
		}
		return status;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String updatePassword(LoginForm login) {
		String status = "fail";
		try{
			int updateStatus = commonDao.updateAndInsertUserPasswordStatus(login);
			if(updateStatus != 0){
				status = ldap.updatePassword(login);
				if(status.equals("success")){
					try{
						String emailSubjectTemplate = servletContext.getInitParameter("automailtemplatesubjectpath");
						String emailBodyTemplate = servletContext.getInitParameter("automailtemplatebodypath");
						Resource senderDetails = commonDao.getResourceDetails(login.getUpdatedBy());
						Resource receiverDetails = commonDao.getResourceDetails(login.getUserId());
						if(receiverDetails != null && senderDetails != null){
							EmailDetails emailDetails = new EmailDetails();
							emailDetails.setEmailDetailsSubject("Login Password Changed!!!");
							emailDetails.setEmailDetailsDesc("Hi! "+login.getUserId()+"\nYour password has been change successfully. Now you can login with \nUser Id: "+login.getUserId()+"\nPassword: "+login.getPassword());
							emailDetails.setEmailDetailsReceiver(receiverDetails.getEmailId());
							emailDetails.setStatus("automail");
							emailSender.sendMail(login.getUpdatedBy(), login.getUserId(),emailDetails,emailSubjectTemplate,emailBodyTemplate);										
							//messageSender.sendMessage(receiverDetails.getMobile(), emailDetails.getEmailDetailsDesc());
						}
					}catch(MailSendException mse){
						logger.error("Mail Exception in updatePassword() in AdministratorServiceImpl", mse);
					}
				}
			}
		}catch(Exception e){
			logger.error("Exception in createPassword() in AdministratorServiceImpl", e);
			status = "fail";
		}
		return status;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String deletePassword(LoginForm login) {
		String status="fail";
		try{
			int deleteStatus = administratorDAO.updateUserPasswordStatus(login);
			if(deleteStatus!=0){
				status = ldap.deletePassword(login);
			}
		}catch(Exception e){
			logger.error("Exception in deletePassword() in BackOfficeServiceImpl", e);
			status = "fail";
		}
		return status;
	}


	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String createTicketingSLA(Ticket ticket) throws CustomException 
	 {
			return administratorDAO.createTicketingSLA(ticket);
	}
	//missing link integration 17042018
	@Override
	public List<JobType> getAllJobDetails() {
		return administratorDAO.getAllJobDetails();
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public int saveJobDetails(JobType jobType) {
		return administratorDAO.saveJobDetails(jobType);
	}
	
	
	@Override
	public List<Approver> getAllApprovalOrderList() {
		return administratorDAO.getAllApprovalOrderList();
	}
	
	@Override
	public String insertApprovalOrder(List<Approver> approverGroupList) {
		return administratorDAO.insertApprovalOrder(approverGroupList);  //Modified By Naimisha 25082017
	}
	
//------------------------For Task Delegation And Task Notification------------------
	
	
	@Override
	public List<Resource> getResourceDetails(String resourceTypeName) throws CustomException {
		// TODO Auto-generated method stub
		return administratorDAO.getResourceDetails(resourceTypeName);
	}


	@Override
	public List<String> getAllTaskNAmeList() throws CustomException {
		// TODO Auto-generated method stub
		return administratorDAO.getAllTaskNameList();
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String insertIntoTaskDetails(Resource resource) {
		// TODO Auto-generated method stub
		return administratorDAO.insertIntoTaskDetails(resource);
	}


	@Override
	public List<TaskDetails> getAllTaskDetailsList(String userName) {
		// TODO Auto-generated method stub
		return administratorDAO.getAllTaskDetailsList(userName);
	}
	
	/****************Done By Naimisha 23112016**********************/
	@Override
	public String setInactiveSubjectGroup(List<SubjectGroup> subjectGroupList) {
		return administratorDAO.setInactiveSubjectGroup(subjectGroupList);
	}


	@Override
	public String setInactiveSubject(List<Subject> subjectList) {
		// TODO Auto-generated method stub
		return administratorDAO.setInactiveSubject(subjectList);
	}
	
	
	@Override
	public String setInactiveStaff(List<Resource> resourceList) {
		return administratorDAO.setInactiveStaff(resourceList);
	}
	
	
	@Override
	public String setInactiveStudent(List<Resource> resourceList) {
		return administratorDAO.setInactiveStudent(resourceList);
	}
	
	
	@Override
	public String setCourseInactive(List<Course> courseList) {
		return administratorDAO.setCourseInactive(courseList);
	}
	
	@Override
	public String submitRepositoryDirectory(RepositoryStructure repository){
		return administratorDAO.submitRepositoryDirectory(repository);
	}


	@Override
	public RepositoryStructure getRespositoryStructure() {
		return administratorDAO.getRespositoryStructure();
	}
	
	@Override
	public String deleteSLATicket(Ticket ticket) {
		return administratorDAO.deleteSLATicket(ticket);
	}
	
	@Override
	public List<Approver> getApproverGroupDetailsForJobType(String jobType) {
		return administratorDAO.getApproverGroupDetailsForJobType(jobType);
	}


	@Override
	public List<Approver> getApproversListAgainstUserId(String userId) {
		return administratorDAO.getApproversListAgainstUserId(userId);
	}


	@Override
	public List<JobType> getJobListAgainstApproverGroup(String approvarGroupCode) {
		return administratorDAO.getJobListAgainstApproverGroup(approvarGroupCode);
	}


	@Override
	public List<TaskDetails> getTaskDetailsAgainstJobTypeAndApproverGroup(TaskDetails taskDetails) {
		return administratorDAO.getTaskDetailsAgainstJobTypeAndApproverGroup(taskDetails);
	}


	@Override
	public List<Task> getTaskDetailsForATicket(Task task) {
		return administratorDAO.getTaskDetailsForATicket(task);
	}

	@Override
	public String inActiveRoleContactMapping(Role role) {
		return administratorDAO.inActiveRoleContactMapping(role);
	}
	
	@Override
	public List<Role> getAllRolesForProfilematrix() {
		return administratorDAO.getAllRolesForProfilematrix();
	}

	@Override
	public List<Module> getAllModulesForProfilematrix() {
		return administratorDAO.getAllModulesForProfilematrix();
	}

	@Override
	public String submitProfileMatrix(List<ProfileMatrix> profileMatrixList) {
		return administratorDAO.submitProfileMatrix(profileMatrixList);
	}
	
	@Override
	public List<Module> getModuleListForSpecificRole(String roleCode) {
		return administratorDAO.getModuleListForSpecificRole(roleCode);
	}

	@Override
	public Module getTabAndSearchForModuleAndRole(String roleCode, String moduleCode) {
		return administratorDAO.getTabAndSearchForModuleAndRole(roleCode, moduleCode);
	}
	
	/**@author Saif.Ali
	 * Date-27/07/2017
	 * Used to get the Configuration Rest APIs*/
	@Override
	public ServerConfiguration getConfigurationRestAPIs() throws CustomException {
		Properties prop = new Properties();		
		EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
		ServerConfiguration serverConfiguration = new ServerConfiguration();
		try {
			ClassPathResource resource = new ClassPathResource("configuration.properties");
			InputStream inputStream = resource.getInputStream();
			prop.load(inputStream);

			if (!prop.isEmpty()) {
				// Reading Properties file and Populating ServerConfiguration model
								
				serverConfiguration.setProgramDetails((prop.getProperty("URI.sendProgrammeDetails") != null) ? prop
						.getProperty("URI.sendProgrammeDetails") : "");
				serverConfiguration.setNewCandidate((prop.getProperty("URI.NewCandidates") != null) ? prop
						.getProperty("URI.NewCandidates") : ""); 
				serverConfiguration.setScrutinizedCandidate((prop.getProperty("URI.ScrutinizedCandidates") != null) ? prop
						.getProperty("URI.ScrutinizedCandidates") : "");
				serverConfiguration.setLocationDetails((prop.getProperty("URI.sendLocationDetails") != null) ? prop
						.getProperty("URI.sendLocationDetails") : ""); 
				serverConfiguration.setSelectedCandidates((prop.getProperty("URI.SelectedCandidates") != null) ? prop
						.getProperty("URI.SelectedCandidates") : ""); 
				serverConfiguration.setAdmittedCandidates((prop.getProperty("URI.AdmittedCandidates") != null) ? prop
						.getProperty("URI.AdmittedCandidates") : ""); 
				serverConfiguration.setAlumniData((prop.getProperty("URI.AlumniData") != null) ? prop
						.getProperty("URI.AlumniData") : "");
				serverConfiguration.setDisplayNotice((prop.getProperty("URI.DisplayNotice") != null) ? prop
						.getProperty("URI.DisplayNotice") : ""); 
			} else {
				logger.error("API Configuration file doesn't exist ");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("getConfigurationRestAPIs() In AdministratorController.java: NullPointerException", ex);
		} catch (NullPointerException e) {
			e.printStackTrace();
			e.printStackTrace();
			logger.error("getConfigurationRestAPIs() In AdministratorController.java: NullPointerException", e);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			logger.error("getConfigurationRestAPIs() In AdministratorController.java: Exception", e);
		}	
		return serverConfiguration;
	}
	
	/**@author Saif.Ali
	 * date-27/07/2017
	 * Used to insert the Configuration Rest APIs */
	
	@Override
	public String insertConfigureRestAPIs(
			ServerConfiguration serverConfiguration) throws CustomException {
		Properties prop = new Properties();
		EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
		String status="errorMessage";	
		try {
			logger.info("insertConfigureRestAPIs() In AdministratorController.java: ");
			// Loading the properties file
			 ClassLoader classLoader = getClass().getClassLoader();
			 File file = new File(classLoader.getResource("configuration.properties").getFile());
			 InputStream inputStream = new FileInputStream(file);
			prop.load(inputStream);
			inputStream.close();
		
			// Set DB Properties Value in Properties file // Trim user input values
			FileOutputStream out = new FileOutputStream(file);
			
			if(null!=serverConfiguration.getProgramDetails()){
				prop.setProperty("URI.sendProgrammeDetails", serverConfiguration.getProgramDetails().replaceAll("\\s+", ""));
			}
			
			if(null!=serverConfiguration.getNewCandidate()){
				prop.setProperty("URI.NewCandidates",  serverConfiguration.getNewCandidate().replaceAll("\\s+", ""));
			}
			
			if(null!=serverConfiguration.getScrutinizedCandidate()){
				prop.setProperty("URI.ScrutinizedCandidates", serverConfiguration.getScrutinizedCandidate().replaceAll("\\s+", ""));
			}
			
			if(null!=serverConfiguration.getLocationDetails()){
				prop.setProperty("URI.sendLocationDetails", serverConfiguration.getLocationDetails().replaceAll("\\s+", ""));
			}
			
			if(null!=serverConfiguration.getSelectedCandidates()){
				prop.setProperty("URI.SelectedCandidates", serverConfiguration.getSelectedCandidates().replaceAll("\\s+", ""));
			}
			
			if(null!=serverConfiguration.getAdmittedCandidates()){
				prop.setProperty("URI.AdmittedCandidates", serverConfiguration.getAdmittedCandidates().replaceAll("\\s+", ""));
			}
			
			if(null!=serverConfiguration.getAlumniData()){
				prop.setProperty("URI.AlumniData", serverConfiguration.getAlumniData().replaceAll("\\s+", ""));
			}
			
			if(null!=serverConfiguration.getDisplayNotice()){
				prop.setProperty("URI.DisplayNotice", serverConfiguration.getDisplayNotice().replaceAll("\\s+", ""));
			}
			
			// Overwrite properties file values
			prop.store(out, null);
			out.close();
			status="successMessage";
		} catch (IOException ex) {		
			ex.printStackTrace();
			status="errorMessage";	
			logger.error("insertConfigureRestAPIs() In AdministratorController.java: NullPointerException", ex);
		} catch (NullPointerException e) {
			e.printStackTrace();
			status="errorMessage";	
			logger.error("insertConfigureRestAPIs() In AdministratorController.java: NullPointerException", e);
		} catch (Exception e) {
			e.printStackTrace();
			status="errorMessage";	
			logger.error("insertConfigureRestAPIs() In AdministratorController.java: NullPointerException", e);
		}
		return status;
	}
	
	/**@author Saif.Ali
	 * Date-01/08/2017
	 * Used to insert the email events into the database*/
	public String insertEventForTemplate(EmailEventAndTemplate emailEvent){
		return administratorDAO.insertEventForTemplate(emailEvent);
	}
	
	/**@author Saif.Ali
	 * Date- 02/08/2017
	 * Used to get the list of Events of template to show in JSP*/
	public List<EmailEventAndTemplate> getEventForTemplateList()
	{
		return administratorDAO.getEventForTemplateList();
	}
	
	/**@author Saif.Ali
	 * Date-02/08/2017
	 * Used to edit the email event names*/
	public String editEventForTemplate(EmailEventAndTemplate emailEvent)
	{
		return administratorDAO.editEventForTemplate(emailEvent);
	}
	
	/**@author Saif.Ali
	 * Date-03/08/2017
	 * Used to insert the email template into the database*/
	public String insertTemplateForEvent(EmailEventAndTemplate emailEvent)
	{
		return administratorDAO.insertTemplateForEvent(emailEvent);
	}
	
	/**@author Saif.Ali
	 * Date-03/08/2017
	 * Used to get the list of Email template for event*/
	public List<EmailEventAndTemplate> getListOfTemplateForEvent()
	{
		return administratorDAO.getListOfTemplateForEvent();
	}
	
	/**@author Saif.Ali
	 * Date-04/08/2017
	 * Used to get the list of Event for mapping*/
	public List<EmailEventAndTemplate> getEventListForMapping()
	{
		return administratorDAO.getEventListForMapping();
	}
	
	/**@author Saif.Ali
	 * Date-04/08/2017
	 * Used to get the list of template for mapping*/
	public List<EmailEventAndTemplate> getTemplateListForMapping()
	{
		return administratorDAO.getTemplateListForMapping();
	}
	
	/**@author Saif.Ali
	 * Date- 04/08/2017
	 * Used to insert the email and template mapping data into the database*/
	public String insertEmailEventAndTemplateMapping(EmailEventAndTemplate emailEvent)
	{
		return administratorDAO.insertEmailEventAndTemplateMapping(emailEvent);
	}
	
	/**@author Saif.Ali
	 * Date- 04/08/2017
	 * Used to get the email and template mapping data from the database*/
	public List<EmailEventAndTemplate> getEmailEventTemplateMappingList()
	{
		return administratorDAO.getEmailEventTemplateMappingList();
	}
	
	/**@author Saif.Ali
	 * Date- 04/08/2017
	 * Used to get the template data from the database*/
	public EmailEventAndTemplate getTheTemplateValuesToEdit(String templateCode){
		return administratorDAO.getTheTemplateValuesToEdit(templateCode);
	}
	
	/**@author Saif.Ali
	 * Date- 04/08/2017
	 * Used to update the template */
	public String editTemplateInformationForemail(EmailEventAndTemplate emailEvent)
	{
		return administratorDAO.editTemplateInformationForemail(emailEvent);
	}
	//missing link integration 17042018
	@Override
	public List<Question> getAllSurveyList() {
		return administratorDAO.getAllSurveyList();
	}

	/*<!-- added By ranita.sur on 28082017 for mapping with survey -->*/
	//missing link integration 17042018
	@Override
	public String submitMapWithServey(JobType jt) {
		// TODO Auto-generated method stub
		return administratorDAO.submitMapWithServey(jt);
	}
	/*<!-- added By ranita.sur on 29082017 for mapping with survey -->*/
	//missing link integration 17042018
	@Override
	public List<JobType> getAllTaskSurveyList() {
		return administratorDAO.getAllTaskSurveyList();
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String insertIntoCategoryAndCategoryTaskMapping(JobType jobType) {
		return administratorDAO.insertIntoCategoryAndCategoryTaskMapping(jobType);
	}

	//missing link integration 17042018
	@Override
	public List<JobType> getCategoryList() {
		return administratorDAO.getCategoryList();
	}


	@Override
	public List<JobType> getTaskistForCategory(String categoryCode) {
		return administratorDAO.getTaskistForCategory(categoryCode);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String updateIntoCategoryAndCategoryTaskMapping(JobType jobType) {
		
		return administratorDAO.updateIntoCategoryAndCategoryTaskMapping(jobType);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String insertCategoryRecipientMapping(JobType jobType) {
		return administratorDAO.insertCategoryRecipientMapping(jobType);
	}


	@Override
	public List<JobType> getCategoryListForRecipientMapping() {
		return administratorDAO.getCategoryListForRecipientMapping();
	}


	@Override
	public List<JobType> getCategoryRecipientMapping(String categoryCode) {
		return administratorDAO.getCategoryRecipientMapping(categoryCode);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String editCategoryRecipientMapping(JobType jobType) {
		return administratorDAO.editCategoryRecipientMapping(jobType);
	}


	@Override
	public List<Ticket> getAllTicketStatusList() {
		return administratorDAO.getAllTicketStatusList();
	}
	
	
/*Added by ranita.sur on 17102017 for editing the recipient group*/
	
	@Override
	public List<Approver> getUserIdListForApprover(Approver approverCode) {
		return administratorDAO.getUserIdListForApprover(approverCode);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String updateIntoApproverGroupTaskMapping(Approver approver) {
		
		return administratorDAO.updateIntoApproverGroupTaskMapping(approver);
	}


	@Override
	public String insertTaskStatus(Ticket ticket) {
		return administratorDAO.insertTaskStatus(ticket);
	}


	@Override
	public List<Ticket> getAllTaskStatusList() {
		return administratorDAO.getAllTaskStatusList();
	}
	
	/**Edit Task Status Details
	 By Ranita.Sur 24102017**/
	@Override
	public String editTaskStatus(Ticket ticket) {
		return administratorDAO.editTaskStatus(ticket);
	}

	//Added By Naimisha 05042018
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String insertCategoryKeyMapping(JobType jobType) {
		return administratorDAO.insertCategoryKeyMapping(jobType);
	}


	@Override
	public List<Ticket> getKeyForACategory(String category) {
		return administratorDAO.getKeyForACategory(category);
	}


	@Override
	public List<Ticket> getAllCategoryWithKeys() {
		return administratorDAO.getAllCategoryWithKeys();
	}


	@Override
	public List<JobType> getAllKeysList() {
		return administratorDAO.getAllKeysList();
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String editCategoryKeyMapping(JobType jobType) {
		return administratorDAO.editCategoryKeyMapping(jobType);
	}

	
	// Added by naimisha 09042018
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String insertCategoryDepartmentMapping(JobType jobType) {
		return administratorDAO.insertCategoryDepartmentMapping(jobType);
	}


	@Override
	public List<Department> getAllCategoryMappedWithDepartment() {
		return administratorDAO.getAllCategoryMappedWithDepartment();
	}


	@Override
	public List<JobType> getCategoryListForADepartment(String departmentCode) {
		return administratorDAO.getCategoryListForADepartment(departmentCode);
	}

//Added By Naimisha 10042018
	@Override
	public List<Designation> getDesignationListForDepartment(String department) {
		return administratorDAO.getDesignationListForDepartment(department);
	}


	@Override
	public List<Functionality> getAllFunctionalityList() {
		return administratorDAO.getAllFunctionalityList();
	}


	@Override
	public JobType taskDetailsAgainstTaskCode(String taskCode) {
		return administratorDAO.taskDetailsAgainstTaskCode(taskCode);
	}


	@Override
	public TicketStatus getTaskStatusForDuplicateCheck(Ticket ticket) {
		return administratorDAO.getTaskStatusForDuplicateCheck(ticket);
	}
}