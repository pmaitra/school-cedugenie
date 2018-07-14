package com.qts.icam.service.common;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.support.PagedListHolder;

import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.AnnualStock;
import com.qts.icam.model.common.AnnualStockList;
import com.qts.icam.model.common.Asset;
import com.qts.icam.model.common.AssetSubType;
import com.qts.icam.model.common.AssetType;
import com.qts.icam.model.common.BloodGroup;
import com.qts.icam.model.common.Budget;
import com.qts.icam.model.common.Condemnation;
import com.qts.icam.model.common.CondemnationList;
import com.qts.icam.model.common.Country;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.EmailDetails;
import com.qts.icam.model.common.FinancialYear;
import com.qts.icam.model.common.Item;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.LicenseInfo;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.MeritListType;
import com.qts.icam.model.common.NoticeBoard;
import com.qts.icam.model.common.Notification;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.SchoolDetails;
import com.qts.icam.model.common.SchoolNote;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.SmsAudit;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.State;
import com.qts.icam.model.common.StatusOfItem;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.common.TeachingLevel;
import com.qts.icam.model.common.UpdateLog;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.common.VendorType;
import com.qts.icam.model.common.IncomeAge;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.erp.Leave;
import com.qts.icam.model.inventory.CommodityPurchaseOrder;
import com.qts.icam.model.library.BookAllocation;
import com.qts.icam.model.survey.Answer;
import com.qts.icam.model.survey.Question;
import com.qts.icam.model.survey.QuestionMaster;
import com.qts.icam.model.ticket.ServiceType;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.ticket.TicketStatus;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.model.admission.AdmissionDrive;
import com.qts.icam.model.backoffice.Fees;
import com.qts.icam.model.backoffice.TimeTableGridData;


public interface CommonService {

	public List<NoticeBoard> getNoticeList() throws CustomException;

	public List<Notification> getNotificationDescriptionList(String trim) throws CustomException;

	public Notification getEmailDetails(String trim) throws CustomException;

	public AcademicYear getCurrentAcademicYear() throws CustomException;

	public List<Module> getAllActiveModules() throws CustomException;

	public String getTimeFromDB() throws CustomException;

	public String getStateNameList(String country) throws CustomException;

	public List<Resource> getActiveLoggedInUser(String userId) throws CustomException;

	public List<Standard> getStandards() throws CustomException;

	public String getUserNameForId(String userId)throws CustomException;

	public List<Country> getCountryList();

	public List<TicketStatus> getAllTicketStatus()throws CustomException;	

	public List<State> getAllStatesInIndia(String indiaCode) throws CustomException;

	public List<ResourceType> getAllResourceType()throws CustomException;

	public String getSectionAgainstStandard(String standard)throws CustomException;
	
	public List<String> getUserIdForResourceType(ResourceType resourceType) throws CustomException;
	
	public List<VendorType> getVendorTypes();

	public int addVendor(Vendor vendor,Ledger ledger);

	public PagedListHolder<Vendor> getVendorListPaging(HttpServletRequest request);

	public List<Vendor> getVendorList();

	public Vendor getVendorDetails(String vendorCode);

	public int updateVendorDetails(Vendor vendor);

	public int deleteVendorDetails(Vendor vendor);

	public List<Role> getAllRoleList()throws CustomException;
	
	public AcademicYear getInventorySession();

	public List<Resource> getDepartmentWiseUserList(String department);

	public List<Resource> getStaffUserIdList();

	public List<Department> selectAllDepartment();

	public SchoolDetails selectSchoolTimeTable()throws CustomException;
	
	public String getStudentsAgainstStandardAndSection(String standard, String section)throws CustomException;

	public String getSubjectsStudiedByStudent(String rollNumber)throws CustomException;

	public List<Resource> getAllTeacherList() throws CustomException;

	public void createLoggingByAspect(LoggingAspect loggingAspect);

	public void createNotificationByAspect(LoggingAspect loggingAspect);

	public List<String> getCity(String strQuery);

	public List<String> getDistrict(String strQuery);

	public String getRecIdForVenue(String venueName);

	public String getUserLdapStatus(String resourceId);

	public Notification getNotificationDetails(Notification notification);

	void changeMailReadStatus(String emailAlertCode);

	public List<Resource> getGroupList();

	public String createNotification(Notification notification);

	public Notification insertEmailDetails(List<EmailDetails> readEmail, String trim);

	public List<AcademicYear> getPreviousAndCurrentAcademicYear();

	public Student getStudentDetails(String rollNumber);

	public List<AcademicYear> getCurrentAndNextAcademicYear();

	public PagedListHolder<NoticeBoard> getNoticeListPaging(HttpServletRequest request);

	public List<String> getUserIdToCreateNotification(String strQuery);
	
	/**@author Saif.Ali Modified by*/
	public List<AnnualStock> selectAssetsForDepartment() throws CustomException;

	public String submitASTV(AnnualStockList annualStockList) throws CustomException;

	public List<AnnualStock> listASTV(String department) throws CustomException;

	public String updateASTV(AnnualStockList annualStockList) throws CustomException;

	/**Modied by
	 * @author Saif.Ali
	 * Date- 20/07/2017*/
	public List<Condemnation> listASTVForCondemnation() throws CustomException;

	public List<Condemnation> listCondemnation(String department) throws CustomException;

	public String submitCondemnation(CondemnationList condemnationList) throws CustomException;

	public String updateCondemnation(CondemnationList condemnationList) throws CustomException;
	
	public LicenseInfo validateLicenseDetails();

	public PagedListHolder<UpdateLog> activityLogPagination(HttpServletRequest request)throws CustomException;

	public void getActivityLog(String module, String functionality)throws CustomException;;

	public String insertIntoActivityLog(UpdateLog updateLog)throws CustomException;
	
	public int insertAssetDetailsExcelBulkData(String excelFile, String updatedBy) throws CustomException;
	
	public List<AssetType> getAllAssetType() throws CustomException;

	public String addAssetType(AssetType assetType) throws CustomException;

	public String editAssetType(AssetType assetType) throws CustomException;

	public List<AssetSubType> getAllAssetSubType() throws CustomException;

	public String addAssetSubType(AssetSubType assetSubType) throws CustomException;

	public String editAssetSubType(AssetSubType assetSubType) throws CustomException;

	public String deleteAssetSubType(AssetSubType assetSubType) throws CustomException;
	
	public String getAssetSubTypeForAssetType(String assetType) throws CustomException;
	
	public List<AssetSubType> getAllAssetSubTypeForAssetType(String assetCode) throws CustomException;

	public int updateLibraryAssetDetails(Asset asset);
	
	public List<Leave> getLeaveHistory(String userId);

	public List<Leave> getLeaveDetails();

	public String getApprovarGroupName(String user_id);

	public int insertIntoApprovalAndLeave(Leave leave) throws CustomException;

	public List<Leave> getResourceLeaveDetails(String userId);
	
	public ResourceType getResourceTypeofUser(String userId);
	
	public List<ServiceType> getServiceTypeList();

	public Ticket generateTicket(Ticket ticket);
	
	public void ticketDocumentUpload(Ticket ticket);

	public List<Ticket> getTicketList(String updatedBy);

	public PagedListHolder<Ticket> getTicketListPaging(HttpServletRequest request);

	public List<Ticket> getClosedTicketList(String updatedBy);

	public PagedListHolder<Ticket> closedTicketListPaging(HttpServletRequest request);

	public Ticket getTicketDetails(Ticket ticket);

	public Ticket updateTicket(Ticket ticket);
	
//-----------------------------ADDED FOR NEW BACKOFFICE--------------------------------
	
	public List<FinancialYear> getFinancialYearList();

	public List<IncomeAge> getIncomeAgeList();

	public List<TeachingLevel> getTeachingLevelList();

//-----------------------------------FOR ADMISSION-------------------------------------------
	
	public List<SocialCategory> getExistingSocialCategory();
	
	public List<AdmissionDrive> getDriveList(Resource r);
	
	public List<MeritListType> getMeritListTypes();
	
	public List<Venue> getExamVenues(Map<String, Object> parameters);
	
	public Venue getExamVenueDetails(Venue venue);
//---------------------------------task delegation
	public List<Task> getDelegatedTask(Task task);

	public List<Task> getOutwardDelegatedTask(Task task);

	public String editVendorDetails(Vendor vendor);
	
	public Notification deleteEmailDetails(Notification emailNotification);

	public List<EmailDetails> getEmailDetailsPaging(HttpServletRequest request);

	public FinancialYear getFinancialYear();
	
	public List<AcademicYear> getAllAcademicYear();
	
	public String getCourseForStandard(String standard);
		
	public List<Subject> getSubject();

	public String getStandatdCodeAgainstCourse(String course);
	
	public String getBatchAgainstCourse(String course);

	public List<Course>getCourseList();
	
	
/********changes bY Naimisha 04042017*****/
	public String updateTicketAndTaskStatus(Ticket ticket);

	public List<Task> getAllTaskCommentForATask(Task task);

	public String getSectionsAgainstStandard(String standard)throws CustomException;

	public String createXMLFileForNOC(Ticket ticket, String reportNOCConfigFilePath, 
			String reportNOCXSLTFilePath, String ticketAttachmentsPath);
	
	public String createXMLFileForGatePass(Ticket ticket, String reportGatePassConfigFilePath, 
			String reportGatePassXSLTFilePath, String ticketAttachmentsPath);
	
	public Course getCourseAgainstCourseCode(String courseCode, String userId);

	public Resource getResourceAgainstUserId(String userId);

	public List<Section> getSectionListAgainstStandard(String standard);

	public String getrollNumberAgainstProgramAndUserId(String userId, String course);
	
	public String submitVendorType(VendorType vendorType);
	
	public String editVendorType(VendorType vendorType);
	
	public String inactiveVendorDetails(Vendor vendor);

	public String sendMailFromMyservice(Notification notification);

	public List<EmailDetails> getsentEmailDetailsList(String userId);

	public EmailDetails getEmailDetailsAgainstEmailCode(String emailId);

	public EmailDetails getEmailContentForSentItemsAgainstEmailCode(String emailId);

	public String inactiveEmailFromSentBox(List<EmailDetails> emailDetailsList);

	public List<EmailDetails> inactiveEmailFromInBox(List<EmailDetails> emailDetailsList);
	/*added by ranita.sur on 02082017 For Vendor emailId Validation */
	public String serverSideValidationOfVendorEmailId(String emailId);
	
	
	//Added By Naimisha 02082017
	public Employee getStaffSalaryDetailsForSalarySlip(Resource resource);

	public Ticket getTicketDetailsForMyService(Ticket ticket);

	public List<Task> getClosedTaskList(Task task);


//Added By Naimisha 29082017
	public List<QuestionMaster> fetchQuestionAnswerForSurveyOfATicket(JobType jobType);

	public String insertTicketSurvey(Question question);

	public  List<Answer> getServeyDetailsForATicket(String ticketCode);
	
	//Added By Naimisha ghosh 06092017

	public String getIndividualReportForMyService(StudentResult studentResult, HttpServletResponse response);
	
	/**Added by Saif.Ali
	 * Date-05/09/2017*/
	public List<Course> getProgramDetailsList(Course course);

	/**@author Saif.Ali*/
	public List<Fees> getAllFeesRelatedInformationForStudent(Course course);
	//Added By Naimisha 08092017

	public Student getStudentAgainstProgramAndUserId(String userId, String courseCode);

	/*added by ranita.sur on 08092017*/
	public List<BookAllocation> getBookFineDetails(String userId);

	
	//Added By Naimisha 12092017
	public String insertChatDetailsForIndividualChat(String from, String to, String msg);

	public String updateChatStatusToReadForAUser(String user);

	public List<Notification> getChatDetailsForIndividualChatForAUser(String to, String from);

	public String emailDocumentUpload(Notification notification);

	public String updateMyEventsForMeetingInvitation(Notification notification);

	
	//Added By Naimisha 24102017
	public List<Task> getTaskListForATicket(String ticketCode);

	public List<Task> getTaskListForACategory(String ticketServiceCode);

	public String getResourceUserIdForMinimumNoOfOpenTicket(String groupCode);

	public Ticket getTaskDetailsOfATask(Task task);

	public List<TicketStatus> getAllTaskStatusList(Task task);
	
	//Added By Naimisha 01112017

	public Ticket updateTaskDetails(Ticket ticket);
	
	public List<TimeTableGridData> getMyTimeTableGridData(String semester, String userId);

	public List<TimeTableGridData> getTimeTableGridDataForStudent(String semester, String program);

	public Ticket updateMyTicket(Ticket ticket);
	
	//Added By Naimisha 22022018

	public Ticket getStudentLeaveDetailsAgainstTicket(String ticketCodeValue);
	
	//Added By Naimisha 29032018
	
	public List<String> getLevelListForTicket();

	public CommodityPurchaseOrder getCommodityPurchaseOrderDetaialsForATicket(String ticketCodeValue);

	public String getUserListAssociatedWithATicket(String ticketCode);
	
	//Added by Naimisha 09042018
	
	public String insertTicketStatus(Ticket ticket);
	
	

	/**
	 * @author anup.roy
	 * this method list of all status of items*/
	public List<StatusOfItem> getAllStatusOfItems();
	/**
	 * @author anup.roy
	 * this method is for submitting all status of items*/
	public String submitStatusOfItem(StatusOfItem status);
	
	
	
	/* added by sourav.bhadra on 10-04-2018 */
	public String getBudgetOfSubDeptsForAFinancialYear(String financialYear, String department);
	
	/* added by sourav.bhadra on 11-04-2018 */
	public String updateParentDepartmentBudgetDetails(Budget budgetDetails);
	
	/* added by sourav.bhadra on 11-04-2018 */
	public Budget getBudgetDetailsForADepartment(String financialYear, String department);
	
	/* added by sourav.bhadra on 11-04-2018 */
	public String reserveFundEstimation(Budget budget);

	//Added by Naimisha 11042018

	public Department getDepartmentForAUser(String userId);

	public TicketStatus getTicketStatusForAType(String type);
	
	/* added by sourav.bhadra on 17-04-2018 */
	public List<Department> getAllSubDepartmentsOfADepartment(String parentDepartment);
	
	/* added by sourav.bhadra on 17-04-2018 */
	public String updateSubDepartmentsBudget(Budget budget);

	public List<TicketStatus> getTicketStatusListForAType(String type);

	
	//Added by naimisha 21052018
	public List<Task> getTaskNoListForUserAndFunctinalityWise(String userId, String functionality);

	public String insertIntoSchoolNote(SchoolNote schoolNote);

	public List<SchoolNote> getSchoolNoteList();
	
	//PRAD JUNE 13 2018
	public List<String> getMobileNumberAgainstRollNumbers(String[] rollNumbers);
	
	// Added by Saikat 16/6/2018
	public String saveSMSDetailsForAudit(SmsAudit smsAudit);
}
