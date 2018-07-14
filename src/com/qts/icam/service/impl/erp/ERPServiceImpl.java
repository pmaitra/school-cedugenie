package com.qts.icam.service.impl.erp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.JsonElement;
import com.qts.icam.dao.bulkdata.DataDAO;
import com.qts.icam.dao.common.CommonDao;
import com.qts.icam.dao.erp.ERPDao;
import com.qts.icam.model.common.Address;
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.Image;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.Staff;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.common.TeacherSubjectMapping;
import com.qts.icam.model.common.TeachingLevel;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.model.erp.Designation;
import com.qts.icam.model.erp.DesignationLevel;
import com.qts.icam.model.erp.DesignationType;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.erp.EmployeeDependent;
import com.qts.icam.model.erp.EmployeeType;
import com.qts.icam.model.erp.IncomeTaxParameters;
import com.qts.icam.model.erp.IncomeTaxSlabDetails;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.erp.Leave;
import com.qts.icam.model.erp.MiscellaneousTax;
import com.qts.icam.model.erp.Organization;
import com.qts.icam.model.erp.Publication;
import com.qts.icam.model.erp.Qualification;
import com.qts.icam.model.erp.SalaryBreakUp;
import com.qts.icam.model.erp.SalaryTemplate;
import com.qts.icam.service.erp.ERPService;
import com.qts.icam.utility.bulkdata.DataUtility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;

@Service
public class ERPServiceImpl implements ERPService{
	
	@Autowired
	ERPDao erpDao;
	
	@Autowired
	CommonDao commonDAO = null;
	
	@Autowired
	DataUtility dataUtility;
	
	@Autowired
	DataDAO dataDAO;
	
	@Autowired
	FileUploadDownload fileUploadDownload; 
	List<Task> taskList = null;
	List<Leave> pendingLeaveList = null;	
	
	public static Logger logger = Logger.getLogger(ERPServiceImpl.class);

	List<Employee> staffDetailsList = null;
	
	@Override
	public List<Department> getAllDepartment() {
		return erpDao.getAllDepartment();
	}

	@Override
	public String addDepartment(Department department) {
		department.setDepartmentName(department.getDepartmentName().trim());
		return erpDao.addDepartment(department);
	}

	@Override
	public String editDepartment(Department department) {
		department.setDepartmentName(department.getDepartmentName().trim());
		return erpDao.editDepartment(department);
	}

	@Override
	public List<Designation> getAllDesignation() {
		return erpDao.getAllDesignation();
	}

	@Override
	public String addDesignation(Designation designation) {
		designation.setDesignationName(designation.getDesignationName().trim());
		return erpDao.addDesignation(designation);
	}

	@Override
	public String editDesignation(Designation designation) {		
		return erpDao.editDesignation(designation);
	}

	@Override
	public List<ResourceType> getAllResourceType() {
		return erpDao.getAllResourceType();
	}

	@Override
	public String addEmployeeType(EmployeeType employeeType) {
		employeeType.setEmployeeTypeName(employeeType.getEmployeeTypeName().trim());
		return erpDao.addEmployeeType(employeeType);
	}

	@Override
	public String editEmployeeType(EmployeeType employeeType) {
		employeeType.setEmployeeTypeName(employeeType.getEmployeeTypeName().trim());
		return erpDao.editEmployeeType(employeeType);
	}
	
	@Override
	public List<DesignationType> getAllDesignationType() {
		return erpDao.getAllDesignationType();
	}
	
	@Override
	public String addDesignationType(DesignationType designationType) {
		designationType.setDesignationTypeName(designationType.getDesignationTypeName().trim());
		return erpDao.addDesignationType(designationType);
	}

	@Override
	public String editDesignationType(DesignationType desginationType) {
		desginationType.setDesignationTypeName(desginationType.getDesignationTypeName().trim());
		return erpDao.editDesignationType(desginationType);
	}
	

	@Override
	public List<JobType> getAllJobType() {
		return erpDao.getAllJobType();
	}

	@Override
	public String addJobType(JobType jobType) {
		jobType.setJobTypeName(jobType.getJobTypeName().trim());
		return erpDao.addJobType(jobType);
	}

	@Override
	public String editJobType(JobType jobType) {
		jobType.setJobTypeName(jobType.getJobTypeName().trim());
		return erpDao.editJobType(jobType);
	}
	
	@Override
	public List<DesignationLevel> getAllDesignationLevel() {
		return erpDao.getAllDesignationLevel();
	}

	@Override
	public String addDesignationLevel(DesignationLevel designationLevel) {
		designationLevel.setDesignationLevelName(designationLevel.getDesignationLevelName().trim());
		return erpDao.addDesignationLevel(designationLevel);
	}

	@Override
	public String editDesignationLevel(DesignationLevel designationLevel) {
		designationLevel.setDesignationLevelName(designationLevel.getDesignationLevelName().trim());
		return erpDao.editDesignationLevel(designationLevel);
	}	

	@Override
	public Employee getEmployeeFormBasicData() {
		Employee employee = null;		
		try{	
			List<SocialCategory> socialCategoryList = commonDAO.getSocialCategoryList();
			List<ResourceType> resourceTypeList = erpDao.getAllResourceType();			
			List<JobType> jobTypeList = erpDao.getAllJobType();
			List<Designation> designationList = erpDao.getAllDesignation();				
			List<Department> departmentList = erpDao.getAllDepartment();
			List<TeachingLevel> teachingLevelList = commonDAO.getTeachingLevelList();			
			employee = new Employee();
		//	employee.setSalaryTemplateList(salaryTemplateList);
			employee.setResourceTypeList(resourceTypeList);
			employee.setJobTypeList(jobTypeList);
			employee.setDesignationList(designationList);
			employee.setDepartmentList(departmentList);
			employee.setSocialCategoryList(socialCategoryList);	
			employee.setTeachingLevelList(teachingLevelList);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("exception in getEmployeeFormBasicData() in ERPServiceImpl", e);
		}
		return employee;
	}
	
	/**
	 * ranita.sur
	 * 03072017*/
	
	@Override
//	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String submitEmployeeDetails(Employee employee,Ledger ledger) {
		String submitStatus= "Fail";
		try{			
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			Attachment attachment = null;
			String dynamicSubFolderPath = employee.getResource().getUserId()+"/";
			/*String filePath = employee.getResource().getUploadFile().getAttachment().getStorageRootPath();*/
			String filePath = employee.getResource().getUploadFile().getAttachment().getStorageRootPath()+"/";
			filePath = filePath+employee.getResource().getUploadFile().getAttachment().getFolderName();
			filePath = filePath+dynamicSubFolderPath;
			/*
			 * upload candidate profile photo
			 */
			try{				
				String userId = employee.getResource().getUserId();
				Image image = employee.getResource().getImage();
				if(image != null && image.getImageData()!=null && image.getImageData().getOriginalFilename()!=""){
					String profilePicturePath =  filePath+"profile_image"+"/";
					image.setImagepath(profilePicturePath);
					attachment = new Attachment();
					attachment.setAttachmentType("profile_image");
					attachment.setStorageRootPath(profilePicturePath);
					image = fileUploadDownload.uploadImage(image, userId);
					attachment.setAttachmentName(image.getReplacedImagedName());
					attachmentList.add(attachment);
				}			
				
				UploadFile uploadFile = employee.getResource().getUploadFile();					
				if(uploadFile!=null){
					/*
					 * this is used for upload Qualification Related Documents
					 */
					if(uploadFile.getQualificationRelatedFile()!=null && !uploadFile.getQualificationRelatedFile().isEmpty()){
						String qualificationDocPath =  filePath+"qualification_doc"+"/";
						for (CommonsMultipartFile file : uploadFile.getQualificationRelatedFile()) {
							if(file.getOriginalFilename()!=""){
								attachment = new Attachment();
								int fileSize = fileUploadDownload.fileUpload(qualificationDocPath, file);
								attachment.setStorageRootPath(qualificationDocPath);
								attachment.setAttachmentType("qualification_doc");
								attachment.setAttachmentName(file.getOriginalFilename());
								attachment.setAttachmentSize(fileSize);
								attachmentList.add(attachment);
							}
						}
					}

					/*
					 * this is used for upload previous school doc
					 */
					if(uploadFile.getExperienceRelatedFile()!=null && !uploadFile.getExperienceRelatedFile().isEmpty()){
						String previousOrganizationDocPath =  filePath+"previous_organization_doc"+"/";
						for (CommonsMultipartFile file : uploadFile.getExperienceRelatedFile()) {
							if(file.getOriginalFilename()!=""){
								attachment = new Attachment();
								int fileSize = fileUploadDownload.fileUpload(previousOrganizationDocPath, file);
								attachment.setStorageRootPath(previousOrganizationDocPath);
								attachment.setAttachmentType("previous_organization_doc");
								attachment.setAttachmentName(file.getOriginalFilename());
								attachment.setAttachmentSize(fileSize);
								attachmentList.add(attachment);
							}
						}
					}
					
					/*
					 * this is used for Publication doc
					 */
					if(uploadFile.getPublicationRelatedFile()!=null && !uploadFile.getPublicationRelatedFile().isEmpty()){
						String publicationDocPath =  filePath+"publication_doc"+"/";
						for (CommonsMultipartFile file : uploadFile.getPublicationRelatedFile()) {
							if(file.getOriginalFilename()!=""){
								attachment = new Attachment();
								int fileSize = fileUploadDownload.fileUpload(publicationDocPath, file);
								attachment.setStorageRootPath(publicationDocPath);
								attachment.setAttachmentType("publication_doc");
								attachment.setAttachmentName(file.getOriginalFilename());
								attachment.setAttachmentSize(fileSize);
								attachmentList.add(attachment);
							}
						}
					}
				}				
				employee.setAttachmentList(attachmentList);
				submitStatus = erpDao.submitEmployeeDetails(employee,ledger);
				if(submitStatus == "Fail"){
					fileUploadDownload.deleteDirectory(filePath);
				}
			}catch(Exception e){
				logger.error("exception in submitEmployeeDetails() in ERPServiceImpl", e);
			}				
		}catch(Exception e){
			logger.error("exception in submitEmployeeDetails() in ERPServiceImpl", e);
		}		
		return submitStatus;
	}
	
	
	
	@Override
	public List<Employee> getStaffList(Map<String, Object> parameters) {
		staffDetailsList = erpDao.getStaffList(parameters);
		return staffDetailsList;
	}	

	@Override
	public Employee getEmployeeDetails(Employee employee) {		
		try{
			String profileImage = null;
			employee = erpDao.getEmployeeDetails(employee);			
			if(employee.getAttachmentList() != null && employee.getAttachmentList().size() != 0){
				for(Attachment a : employee.getAttachmentList()){
					if(a.getAttachmentType().equalsIgnoreCase("profile_image")){
						profileImage = a.getStorageRootPath()+a.getAttachmentName();
						Image image = new Image();
						image.setImageName(fileUploadDownload.getBase64Image(profileImage));
						employee.getResource().setImage(image);
					}
				}
			}			
		}catch(Exception e){
			logger.error("Exception in getEmployeeDetails() in ERPServiceImpl", e);
		}		
		return employee;
	}

	@Override
	public String updateEmployeeBasicDetails(Employee employee) {	
		return erpDao.updateEmployeeBasicDetails(employee);
	}

	@Override
	public String updateEmployeePersonalDetails(Employee employee) {		
		return erpDao.updateEmployeePersonalDetails(employee);
	}

	@Override
	public String updateEmployeeQualificationDetails(Employee employee) {
		try{			
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			Attachment attachment = null;
			String dynamicSubFolderPath = employee.getResource().getUserId()+"/";			
			String filePath = employee.getResource().getUploadFile().getAttachment().getStorageRootPath();			
			filePath = filePath+employee.getResource().getUploadFile().getAttachment().getFolderName();
			filePath = filePath+dynamicSubFolderPath;			
			UploadFile uploadFile = employee.getResource().getUploadFile();					
			if(uploadFile!=null){				
				/*
				 * this is used for Qualification doc
				 */
				if(uploadFile.getQualificationRelatedFile()!=null && !uploadFile.getQualificationRelatedFile().isEmpty()){
					String publicationDocPath =  filePath+"qualification_doc"+"/";
					for (CommonsMultipartFile file : uploadFile.getQualificationRelatedFile()) {
						if(file.getOriginalFilename()!=""){
							attachment = new Attachment();
							int fileSize = fileUploadDownload.fileUpload(publicationDocPath, file);
							attachment.setStorageRootPath(publicationDocPath);
							attachment.setAttachmentType("qualification_doc");
							attachment.setAttachmentName(file.getOriginalFilename());
							attachment.setAttachmentSize(fileSize);
							attachmentList.add(attachment);
						}
					}
				}
				employee.setAttachmentList(attachmentList);
			}
		}catch(Exception e){
			logger.error("Exception occured in updateEmployeeQualificationDetails() from ERPServiceImpl : ",e);
		}
		return erpDao.updateEmployeeQualificationDetails(employee);
	}

	@Override
	public String updateEmployeeBankDetails(Employee employee) {		
		return erpDao.updateEmployeeBankDetails(employee);
	}

	@Override
	public String updateEmployeeDependentsDetails(Employee employee) {		
		return erpDao.updateEmployeeDependentsDetails(employee);
	}

	@Override
	public String updateEmployeeAddressDetails(Employee employee) {		
		return erpDao.updateEmployeeAddressDetails(employee);
	}

	@Override
	public String serverSideValidationOfUserId(String strUserId) {
		return erpDao.serverSideValidationOfUserId(strUserId);
	}
	
	@Override
	public String serverSideValidationOfEmployeeCode(String employeeCode) {
		return erpDao.serverSideValidationOfEmployeeCode(employeeCode);
	}

	@Override
	public String updateEmployeePublicationDetails(Employee employee) {
		try{			
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			Attachment attachment = null;
			String dynamicSubFolderPath = employee.getResource().getUserId()+"/";			
			/*String filePath = employee.getResource().getUploadFile().getAttachment().getStorageRootPath();*/
			String filePath = employee.getResource().getUploadFile().getAttachment().getStorageRootPath()+"/";
			filePath = filePath+employee.getResource().getUploadFile().getAttachment().getFolderName();
			filePath = filePath+dynamicSubFolderPath;			
			UploadFile uploadFile = employee.getResource().getUploadFile();					
			if(uploadFile!=null){				
				/*
				 * this is used for Publication doc
				 */
				if(uploadFile.getPublicationRelatedFile()!=null && !uploadFile.getPublicationRelatedFile().isEmpty()){
					String publicationDocPath =  filePath+"publication_doc"+"/";
					for (CommonsMultipartFile file : uploadFile.getPublicationRelatedFile()) {
						if(file.getOriginalFilename()!=""){
							attachment = new Attachment();
							int fileSize = fileUploadDownload.fileUpload(publicationDocPath, file);
							attachment.setStorageRootPath(publicationDocPath);
							attachment.setAttachmentType("publication_doc");
							attachment.setAttachmentName(file.getOriginalFilename());
							attachment.setAttachmentSize(fileSize);
							attachmentList.add(attachment);
						}
					}
				}
				employee.setAttachmentList(attachmentList);
			}
		}catch(Exception e){
			logger.error("Exception occured in updateEmployeePublicationDetails() from ERPServiceImpl : ",e);
		}
		return erpDao.updateEmployeePublicationDetails(employee);
	}

	@Override
	public String updateEmployeeWorkingDetails(Employee employee) {	
		try{			
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			Attachment attachment = null;
			String dynamicSubFolderPath = employee.getResource().getUserId()+"/";			
			/*String filePath = employee.getResource().getUploadFile().getAttachment().getStorageRootPath();*/
			String filePath = employee.getResource().getUploadFile().getAttachment().getStorageRootPath()+"/";
			filePath = filePath+employee.getResource().getUploadFile().getAttachment().getFolderName();
			filePath = filePath+dynamicSubFolderPath;			
			UploadFile uploadFile = employee.getResource().getUploadFile();					
			if(uploadFile!=null){				
				/*
				 * this is used for Working Experience doc
				 */
				if(uploadFile.getExperienceRelatedFile()!=null && !uploadFile.getExperienceRelatedFile().isEmpty()){
					String workExDocPath =  filePath+"previous_organization_doc"+"/";
					for (CommonsMultipartFile file : uploadFile.getExperienceRelatedFile()) {
						if(file.getOriginalFilename()!=""){
							attachment = new Attachment();
							int fileSize = fileUploadDownload.fileUpload(workExDocPath, file);
							attachment.setStorageRootPath(workExDocPath);
							attachment.setAttachmentType("previous_organization_doc");
							attachment.setAttachmentName(file.getOriginalFilename());
							attachment.setAttachmentSize(fileSize);
							attachmentList.add(attachment);
						}
					}
				}
				employee.setAttachmentList(attachmentList);
			}
		}catch(Exception e){
			logger.error("Exception occured in updateEmployeeWorkingDetails() from ERPServiceImpl : ",e);
		}
		return erpDao.updateEmployeeWorkingDetails(employee);
	}

	@Override
	public String updateEmployeeImage(Employee employee) {
		/*
		 * upload candidate profile photo
		 */
		try{			
			String dynamicSubFolderPath = employee.getResource().getUserId()+"/";
			/*String filePath = employee.getAttachment().getStorageRootPath();*/
			String filePath = employee.getAttachment().getStorageRootPath()+"/";
			filePath = filePath+employee.getAttachment().getFolderName();
			filePath = filePath+dynamicSubFolderPath;
			String userId = employee.getResource().getUserId();
			Image image = employee.getResource().getImage();
			if(image != null && image.getImageData()!=null && image.getImageData().getOriginalFilename()!=""){
				String profilePicturePath =  filePath+"profile_image"+"/";
				image.setImagepath(profilePicturePath);					
				employee.getAttachment();
				employee.getAttachment().setAttachmentType("profile_image");
				employee.getAttachment().setStorageRootPath(profilePicturePath);
				image = fileUploadDownload.uploadImage(image, userId);
				employee.getAttachment().setAttachmentName(image.getReplacedImagedName());				
			}
		}catch (Exception e) {
			logger.error("Exception occured in updateEmployeeImage() from ERPServiceImpl : ",e);
		}
		return erpDao.updateEmployeeImageToAttachment(employee);	
	}
	
	@Override
	public int insertStaffDetailsExcelBulkData(String excelFile, String updatedBy) throws CustomException {
		int insertStatus = 0;
		int employeeDetailsInsertStatus = 0;
		int employeeAddressInsertStatus = 0;
		int employeeQualificationInsertStatus = 0;
		int employeeOrganizationInsertStatus = 0;
		int employeePublicationInsertStatus = 0;
		int employeeDependentInsertStatus = 0;
		try{
			logger.info("In insertStaffDetailsExcelBulkData(String excelFile, String userId) method of ERPServiceImpl");
			
			List<Employee> employeeDetailsList = dataUtility.readExcelFileForStaffDetails(excelFile);					
			if(employeeDetailsList.size() != 0){
				employeeDetailsInsertStatus = dataDAO.batchInsertForEmployeeDetails(employeeDetailsList, updatedBy);
				logger.info("@@ employeeDetailsInsertStatus  ::  "+employeeDetailsInsertStatus);
			}
			if(employeeDetailsInsertStatus != 0){
				int cityStatus = 0;
				int districtStatus = 0;
				List<String> cityList = dataUtility.readExcelFileForCityList(excelFile);
				if(null != cityList){
					cityStatus = dataDAO.batchInsertForCity(cityList, updatedBy);
					logger.info("cityStatus :: "+cityStatus);
				}				
				List<String> districtList = dataUtility.readExcelFileForDistrictList(excelFile);
				if(null != districtList){
					districtStatus = dataDAO.batchInsertForDistrict(districtList, updatedBy);
					logger.info("districtStatus :: "+districtStatus);
				}	
				//if(cityStatus != 0 && districtStatus != 0){
					List<Address> employeeAddressList = dataUtility.readExcelFileForStaffAddressDetails(excelFile);
					if(employeeAddressList.size() != 0){
						employeeAddressInsertStatus = dataDAO.batchInsertForEmployeeAddressDetails(employeeAddressList,updatedBy);
						logger.info("@@ employeeAddressInsertStatus  ::  "+employeeAddressInsertStatus);
					}
				//}
				if(employeeAddressInsertStatus != 0){
					List<Qualification> employeeQualificationList = dataUtility.readExcelFileForStaffQualificationDetails(excelFile);					
					if(employeeQualificationList.size() != 0){
						employeeQualificationInsertStatus = dataDAO.batchInsertForEmployeeQualificationDetails(employeeQualificationList, updatedBy);
						logger.info("@@ employeeQualificationInsertStatus  ::  "+employeeQualificationInsertStatus);
					}			
					List<Organization> employeeOrganizationList = dataUtility.readExcelFileForStaffPreviousOrganizationDetails(excelFile);
					if(employeeOrganizationList.size() != 0){
						employeeOrganizationInsertStatus = dataDAO.batchInsertForEmployeeOrganizationDetails(employeeOrganizationList, updatedBy);
						logger.info("@@ employeeOrganizationInsertStatus  ::  "+employeeOrganizationInsertStatus);
					}			
					List<Publication> employeePublicationList = dataUtility.readExcelFileForStaffPublicationDetails(excelFile);
					if(employeePublicationList.size() != 0){
						employeePublicationInsertStatus = dataDAO.batchInsertForEmployeePublicationDetails(employeePublicationList, updatedBy);
						logger.info("@@ employeePublicationInsertStatus  ::  "+employeePublicationInsertStatus);
					}
					List<EmployeeDependent> employeeDependentList = dataUtility.readExcelFileForEmployeeDependentDetails(excelFile);
					if(employeeDependentList.size() != 0){
						employeeDependentInsertStatus = dataDAO.batchInsertForEmployeeDependents(employeeDependentList, updatedBy);
						logger.info("@@ employeeDependentInsertStatus  ::  "+employeeDependentInsertStatus);
					}
				}
			}
			if(employeeDetailsInsertStatus != 0 && employeeAddressInsertStatus != 0){
				insertStatus = 1;
			}
		}catch(Exception e){
			logger.error("Exception in insertStaffDetailsExcelBulkData() to read excel and insert in ERPServiceImpl", e);
			e.printStackTrace();
		}
		return insertStatus;
	}
	
	
	//lEAVE
	
		@Override
		public List<Leave> getleaveTypeList() {
			return erpDao.getleaveTypeList();
		}

		@Override
		public List<Leave> getResourceLeaveDetails(String userId) {
			return erpDao.getResourceLeaveDetails(userId);
		}

		@Override
		public String startLeaveRequest(Task task) {
			String createdTaskCode= null;
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			Attachment attachment = null;
			String dynamicSubFolderPath = task.getCreatedById()+"/";
			String filePath = task.getUploadFile().getAttachment().getStorageRootPath();
			filePath = filePath+task.getUploadFile().getAttachment().getFolderName();
			filePath = filePath+dynamicSubFolderPath;		
			try{				
				String userId = task.getCreatedById();
				createdTaskCode=erpDao.startLeaveRequest(task);
				if(createdTaskCode != null){
					UploadFile uploadFile = task.getUploadFile();					
					if(uploadFile!=null){
						/*
						 * this is used for upload Leave Related Documents
						 */
						if(uploadFile.getFileData()!=null && !uploadFile.getFileData().isEmpty()){
							String leaveDocPath=filePath+"leave_doc"+"/"+createdTaskCode+"/";
							for (CommonsMultipartFile file : uploadFile.getFileData()) {
								if(file.getOriginalFilename()!=""){
									attachment = new Attachment();
									int fileSize = fileUploadDownload.fileUpload(leaveDocPath, file);
									attachment.setStorageRootPath(leaveDocPath);
									attachment.setAttachmentType("leave_doc");
									attachment.setAttachmentName(file.getOriginalFilename());
									attachment.setAttachmentSize(fileSize);
									attachmentList.add(attachment);
								}
							}
						}
					}
				}
			
				task.setAttachmentList(attachmentList);
			}
			catch (Exception e) {
				logger.error("Exception occured in startLeaveRequest() from ERPServiceImpl : ",e);
			}
			return createdTaskCode;
		}

		@Override
		public Leave getRemainingLeave(String leaveType, String userId) {
			return erpDao.getRemainingLeave(leaveType,userId);
		}

		@Override
		public Task getWorkingDaysForLeaveRequest(Task task) {
			return erpDao.getWorkingDaysForLeaveRequest(task);
		}

		@Override
		public Integer getRoleOfUser(Task task) {
			return erpDao.getRoleOfUser(task);
		}

		@Override
		public List<Leave> getPendingTaskList() {
			pendingLeaveList = erpDao.getPendingTaskList();
			return pendingLeaveList;
		}

		@Override
		public PagedListHolder<Leave> getPendingTaskListPaging(
				HttpServletRequest request) {
			PagedListHolder<Leave> pagedListHolder = new PagedListHolder<Leave>();
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 5;
			pagedListHolder.setPageSize(pageSize);
			return pagedListHolder;
		}

		@Override
		public List<Task> searchOnListPendingTask(Map<String, Object> parameters) {
			taskList = erpDao.searchOnListPendingTask(parameters);
			return taskList;
		}

		@Override
		public Leave getPendingLeaveDetails(Leave leave) {
			return erpDao.getPendingLeaveDetails(leave);
		}

		@Override
		public String insertLeaveResponse(Task task) {
			return erpDao.insertLeaveResponse(task);
		}
		
		@Override
		public List<Task> getTaskHistoryList(Task task) {
			logger.info("In getTaskHistoryList(Task task) method of ERPServiceImpl");
			taskList = erpDao.getTaskHistoryList(task);
			return taskList;
		}	
		
		@Override
		public PagedListHolder<Task> getTaskHistoryListPaging(HttpServletRequest request) {
			PagedListHolder<Task> pagedListHolder = new PagedListHolder<Task>(taskList);
			int page = ServletRequestUtils.getIntParameter(request, "p", 0);
			pagedListHolder.setPage(page);
			int pageSize = 5;
			pagedListHolder.setPageSize(pageSize);
			return pagedListHolder;
		}

		@Override
		public List<Task> listPersonalTaskHistory(Task task) {
			logger.info("In listPersonalTaskHistory(Task task) method of ERPServiceImpl");
			taskList = erpDao.listPersonalTaskHistory(task);
			return taskList;
		}

		@Override
		public Employee getTeacherDetails(String strStaffUserId) {
			return erpDao.getTeacherDetails(strStaffUserId);
		}

		@Override
		public String submitStaffRetirement(Employee employee) {
			return erpDao.submitStaffRetirement(employee);
		}

		public List<Employee> getRetiredStaffList() {
			logger.info("getRetiredStaffList() method In erpServiceImpl.java:");
			return erpDao.getRetiredStaffList();			
		}

		@Override
		public List<Designation> getDesignationForResourceType(String resourceType) {
			return erpDao.getDesignationForResourceType(resourceType);
		}
		@Override
		public String updateEmployeeNomineeDetails(Employee employee) {
			return erpDao.updateEmployeeNomineeDetails(employee);
		}

		@Override
		public String updateEmployeeWorkShopAndTrainingDetails(Employee employee) {
			return erpDao.updateEmployeeWorkShopAndTrainingDetails(employee);
		}

		@Override
		public String updateEmployeeAwardsAndRecognizationDetails(Employee employee) {
			return erpDao.updateEmployeeAwardsAndRecognizationDetails(employee);
		}

		@Override
		public String updateEmployeeConfidentialDetails(Employee employee) {
			return erpDao.updateEmployeeConfidentialDetails(employee);
		}

		@Override
		@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
		public String insertManualLeaveResponse(Task task) {
			return erpDao.insertManualLeaveResponse(task);
		}
		
		@Override
		public List<Task> listPersonalLeaveHistory(Task task) {
			taskList = erpDao.listPersonalLeaveHistory(task);
			return taskList;
		}

		@Override
		public String addDesignationLevelMapping(List<Designation> designationList) {
			return erpDao.addDesignationLevelMapping(designationList);
		}

		@Override
		public List<Designation> getAllMappedDesignation() {
			return erpDao.getAllMappedDesignation();
		}

		@Override
		public String updateDesignationLevelMapping(Designation designation) {
			return erpDao.updateDesignationLevelMapping(designation);	//Modified by naimisha 16082017		
		}

		@Override
		public List<SalaryBreakUp> getSalaryBreakUp() {
			logger.info("getSalaryBreakUp() method In erpServiceImpl.java: ");
			return erpDao.getSalaryBreakUp();
		}
		
		@Override
		public List<SalaryBreakUp> createSalaryBreakUp(SalaryBreakUp salaryBreakUp) {
			logger.info("createSalaryBreakUp(SalaryBreakUp salaryBreakUp) method In erpServiceImpl.java: ");
			return erpDao.createSalaryBreakUp(salaryBreakUp);
		}

		@Override
		public List<SalaryBreakUp> getSalaryBreakUpList() {
			return erpDao.getSalaryBreakUpList();
		}

		@Override
		public List<SalaryTemplate> salaryTemplateList() {
			return erpDao.salaryTemplateList();
		}

		@Override
		public List<SalaryBreakUp> getSalaryBreakUpForSlab() {
			return erpDao.getSalaryBreakUpForSlab();
		}

		@Override
		public List<SalaryBreakUp> getSalaryBreakUpForNonSlab() {
			return erpDao.getSalaryBreakUpForNonSlab();
		}

		@Override
		public String getSubmittedSlabTypeForMiscTax(String taxTypeCode) {
			return erpDao.getSubmittedSlabTypeForMiscTax(taxTypeCode);
		}

		@Override
		public String submitMiscTaxSlab(List<MiscellaneousTax> miscTaxSlabList, MiscellaneousTax miscTax) {
			return erpDao.submitMiscTaxSlab(miscTaxSlabList, miscTax);
		}

		@Override
		public List<SalaryBreakUp> getSalaryBreakUpForMiscTaxSlab() {
			return erpDao.getSalaryBreakUpForMiscTaxSlab();
		}

		@Override
		public List<MiscellaneousTax> getMiscTaxSlabForEdit(String taxTypeCode) {
			return erpDao.getMiscTaxSlabForEdit(taxTypeCode);
		}

		@Override
		public String updateMiscTaxSlab(List<MiscellaneousTax> miscTaxSlabList, MiscellaneousTax miscTax) {
			return erpDao.updateMiscTaxSlab(miscTaxSlabList, miscTax);
		}

		@Override
		public String getSubmittedEmployerContribution(String taxTypeCode) {
			return erpDao.getSubmittedEmployerContribution(taxTypeCode);
		}

		@Override
		public String submitEmployerContribution(List<MiscellaneousTax> miscTaxSlabList, MiscellaneousTax miscTax) {
			return erpDao.submitEmployerContribution(miscTaxSlabList,miscTax);
		}

		@Override
		public IncomeTaxSlabDetails viewParameterOfIncomeTaxSalarySlab(IncomeTaxSlabDetails incomeTaxSlabDetails) {
			return erpDao.viewParameterOfIncomeTaxSalarySlab(incomeTaxSlabDetails);
		}

		@Override
		public String editIncomeTaxSalarySlab(IncomeTaxSlabDetails itIncomeTaxSlabDetails) {
			return erpDao.editIncomeTaxSalarySlab(itIncomeTaxSlabDetails);
		}

		@Override
		public String getDesignationBasedOnDesignationType(String designationTypeCode) {
			return erpDao.getDesignationBasedOnDesignationType(designationTypeCode);
		}

		@Override
		public String getLevelBasedOnDesignation(String designationCode) {
			return erpDao.getLevelBasedOnDesignation(designationCode);
		}
		
		@Override
		public String checkSalaryTemplateName(String getsalaryTemplateName) {
			return erpDao.checkSalaryTemplateName(getsalaryTemplateName);
		}

		@Override
		public SalaryTemplate getSalaryTemplateDetails(String templateCode) {
			return erpDao.getSalaryTemplateDetails(templateCode);
		}

		@Override
		public String updateSalaryTemplate(SalaryTemplate salaryTemplate) {
			return erpDao.updateSalaryTemplate(salaryTemplate);
		}

		@Override
		public String submitIncomeTaxSalarySlab(IncomeTaxSlabDetails itIncomeTaxSlabDetails) {
			return erpDao.submitIncomeTaxSalarySlab(itIncomeTaxSlabDetails);
		}

		@Override
		public Employee getStaffDetailsForPromotionAndSalaryMapping(Resource resource) {
			return erpDao.getStaffDetailsForPromotionAndSalaryMapping(resource);
		}

		@Override
		public String submitSalaryTemplate(SalaryTemplate salaryTemplate) {
			return erpDao.submitSalaryTemplate(salaryTemplate);
		}

		@Override
		@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
		public String setTeacherInterviewSchedule(Employee employee) {
			return erpDao.setTeacherInterviewSchedule(employee);
		}

		@Override
		public List<Employee> getTeacherInterviewScheduleList() {			
			return erpDao.getTeacherInterviewScheduleList();
		}

		@Override
		public Employee getTeacherInterviewScheduleDetails(Employee employee) {
			return erpDao.getTeacherInterviewScheduleDetails(employee);
		}

		@Override
		public List<Employee> updateTeacherInterviewSchedule(Employee employee) {			
			return erpDao.updateTeacherInterviewSchedule(employee);
		}

		@Override
		public List<Employee> getCandidateId() {
			return erpDao.getCandidateId();
		}

		@Override
		public String getCandidateName(Employee employee) {
			return erpDao.getCandidateName(employee);
		}

		@Override
		public String submitTeacherInterviewDetails(Employee employee) {
			return erpDao.submitTeacherInterviewDetails(employee);
		}

		@Override
		public List<Employee> getTeacherInterviewList() {
			return erpDao.getTeacherInterviewList();
		}

		@Override
		public Resource getCandidateDetails(String strStaffCode) {			
			return erpDao.getCandidateDetails(strStaffCode);
		}

		@Override
		public Employee getTeacherInterviewDetails(Employee employee) {
			return erpDao.getTeacherInterviewDetails(employee);
		}

		@Override
		public List<String> getStaffUserIdList(String strQuery) {
			return erpDao.getStaffUserIdList(strQuery);
		}

		@Override
		public String getReportingPerson(String designation) {
			return erpDao.getReportingPerson(designation);
		}

		@Override
		public List<SalaryTemplate> getSalaryTemp(Designation designation) {
			return erpDao.getSalaryTemp(designation);
		}

		@Override
		public List<SalaryBreakUp> getSalaryBreakUpForTemplate(SalaryTemplate salaryTemplate) {
			return erpDao.getSalaryBreakUpForTemplate(salaryTemplate);
		}

		@Override
		@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
		public Employee createErpSalaryMapping(Employee employee) {
			return erpDao.createErpSalaryMapping(employee);
		}

		@Override
		public List<SalaryBreakUp> getTaxDeductionAmount(String totalGross,	String totalNet, Employee employee) {
			return erpDao.getTaxDeductionAmount(totalGross,totalNet,employee);
		}

		@Override
		public List<IncomeTaxParameters> getIncomeTaxParameter() {
			return erpDao.getIncomeTaxParameter();
		}
		
		@Override
		public IncomeTaxSlabDetails getSlabCalculationParameter(String hidVal) {
			return erpDao.getSlabCalculationParameter(hidVal);
		}
		
		@Override
		public Employee getStaffSalaryDetails(Resource resource) {
			return erpDao.getStaffSalaryDetails(resource);
		}
		
		/**ranita.sur 04072017*/
		
		@Override
		@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
		public String saveStaffSalaryDetails(Employee staff) {
			return erpDao.saveStaffSalaryDetails(staff);
		}

		@Override
		public List<DesignationLevel> getDesignationLevelListForDesignation(String designationName) {			
			return erpDao.getDesignationLevelListForDesignation(designationName);
		}

	/**done by naimisha
	 * changs taken on 11jan 2017**/
		
		/*Done by naimisha for erp*/
		@Override
		public Staff getTeacherSubjectForMapping() {
			logger.info("getTeacherSubjectForMapping() method In erpServiceImpl.java: ");
			return erpDao.getTeacherSubjectForMapping();
		}

		@Override
		public Staff saveTeacherSubjectMapping(Staff staff) {
			// TODO Auto-generated method stub
			return erpDao.saveTeacherSubjectMapping(staff);
		}

		@Override
		public String getTeacherSubjectMappping(String strStaffUserId) {
			
			return erpDao.getTeacherSubjectMappping(strStaffUserId);
		}
		
		@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
		@Override
		public String submitTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping) {

			return erpDao.submitTeacherSubjectMapping(teacherSubjectMapping);
		}

		@Override
		public List<Resource> getTeachersFromTeacherSubjectMappingList() {
			// TODO Auto-generated method stub
			return erpDao.getTeachersFromTeacherSubjectMappingList();
		}

		@Override
		public Staff getTeacherSubjectMappingForEdit(Staff staff) {
			// TODO Auto-generated method stub
			return erpDao.getTeacherSubjectMappingForEdit(staff);
		}

		@Override
		public String submitStandardTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping) {
			return erpDao.submitStandardTeacherSubjectMapping(teacherSubjectMapping);
		}

		@Override
		public List<TeacherSubjectMapping> getTeachersFromStandardTeacherSubjectMappingList() {
			return erpDao.getTeachersFromStandardTeacherSubjectMappingList();
		}

		@Override
		public String editStandardTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping) {
			return erpDao.editStandardTeacherSubjectMapping(teacherSubjectMapping);
		}

		@Override
		public String deleteStandardTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping) {
			return erpDao.deleteStandardTeacherSubjectMapping(teacherSubjectMapping);
		}

		@Override
		public List<EmployeeType> getAllEmployeeType() {
			return erpDao.getAllEmployeeType();
		}
		
		@Override
		public String updateHodForDepartment(Department department) {
			return erpDao.updateHodForDepartment(department);
		}
		
		@Override
		public String getAllUserIdList(String parent){
			return erpDao.getAllUserIdList(parent);
			
		}
		
		@Override
		public List<Department>getMapDepartmentWithResourceType() {
			return erpDao.getMapDepartmentWithResourceType();
		}
		
		@Override
		public String saveUpdateDetails(Department department) {
			return erpDao.saveUpdateDetails(department);
		}

		
		//Added By Naimisha 31072017For disburse Salary
		@Override
		public List<Resource> getResourceDetailsForSalary(Resource resource) {
			return erpDao.getResourceDetailsForSalary(resource);
		}

		
		@Override
		@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
		public String saveStaffSalaryDetailsNew(List<Resource> resourceList) {
			return erpDao.saveStaffSalaryDetailsNew(resourceList);
		}

		
		
		//Added By Naimisha 09082017
		@Override
		public List<Resource> getPaymentStatusForEmployeeForAYearAndMonth(Resource resource) {
			
			 return erpDao.getPaymentStatusForEmployeeForAYearAndMonth(resource);
		}

		//Added By Naimisha 11082017
		
		@Override
		public SalaryTemplate getTemplateForDesignationTypeAndDesignationAndLevel(Resource resource) {
			return erpDao.getTemplateForDesignationTypeAndDesignationAndLevel(resource);
		}
		
		/*modified by ranita.sur on 20092017 for getting unmapped designation*/
		@Override
		public List<Designation> getAllUnMappedDesignation() {
			return erpDao.getAllUnMappedDesignation();
		}
		
		@Override
		public String getEventEmployeeName(String eventIncharge){
			System.out.println("In Service getEventEmployeeName()");
			return erpDao.getEventEmployeeName(eventIncharge);
		}
}
