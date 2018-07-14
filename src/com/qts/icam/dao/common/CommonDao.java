package com.qts.icam.dao.common;

import java.util.List;
import java.util.Map;

import com.qts.icam.model.admission.AdmissionDrive;
import com.qts.icam.model.backoffice.Fees;
import com.qts.icam.model.backoffice.TimeTableGridData;
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
import com.qts.icam.model.common.NotificationDetails;
import com.qts.icam.model.common.NotificationMedium;
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
import com.qts.icam.model.login.LoginForm;
import com.qts.icam.model.survey.Answer;
import com.qts.icam.model.survey.Question;
import com.qts.icam.model.survey.QuestionMaster;
import com.qts.icam.model.ticket.ServiceType;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.ticket.TicketStatus;
import com.qts.icam.utility.customexception.CustomException;

public interface CommonDao {

	public List<NoticeBoard> getNoticeList();

	public AcademicYear getCurrentAcademicYear();

	public List<Module> getAllActiveModules();

	public Notification getEmailDetails(String userId);

	public List<Notification> getNotificationDescriptionList(String userId);

	public String getTimeFromDB();

	public List<Country> getCountryList();

	public List<SocialCategory> getSocialCategoryList();

	public List<Standard> getStandards();

	public List<State> getStateNameList(String country);

	public List<AcademicYear> getCurrentAndNextAcademicYear();

	public List<Resource> getActiveLoggedInUser(String userid);

	public String getUserNameForId(String userId);

	public List<TicketStatus> getAllTicketStatus()throws CustomException ;

	public List<ResourceType> getAllResourceType()throws CustomException ;

	public List<Role> getAllRoleList()throws CustomException ;

	public List<Section> getSectionAgainstStandard(String standard)throws CustomException ;

	public List<String> getUserIdForResourceType(ResourceType resourceType)throws CustomException;
	
	public List<VendorType> getVendorTypes();

	public int addVendor(Vendor vendor,Ledger ledger);

	public List<Vendor> getVendorList();

	public Vendor getVendorDetails(String vendorCode);

	public int updateVendorDetails(Vendor vendor);

	public int deleteVendorDetails(Vendor vendor);
	
	public AcademicYear getInventorySession();

	public List<Resource> getStaffUserIdList();

	public List<Department> selectAllDepartment();

	public List<Resource> getDepartmentWiseUserList(String department);

	public SchoolDetails selectSchoolTimeTable()throws CustomException;

	List<Subject> getSubjectsStudiedByStudent(String rollNumber)
			throws CustomException;

	List<Student> getStudentsAgainstStandardAndSection(Student student)
			throws CustomException;

	public int updateAndInsertUserPasswordStatus(LoginForm login);

	public Resource getResourceDetails(String updatedBy);

	public List<Resource> getAllTeacherList() throws CustomException;

	public LoggingAspect getLogActivityRules(LoggingAspect loggingAspect);

	public void insertLoggingDetails(LoggingAspect loggingAspect);

	public LoggingAspect getNotificationActivityRules(
			LoggingAspect loggingAspect);

	public List<NotificationMedium> getNotificationMediums(String status);

	public int createAlert(Notification notification);

	public List<String> getCity(String strQuery);

	public List<String> getDistrict(String strQuery);

	public String getRecIdForVenue(String venueName);

	public Notification getNotificationDetails(Notification notification);

	public void changeMailReadStatus(String emailAlertCode);

	public List<Resource> getGroupList();


	public List<Resource> getUsersForUserGroup(NotificationDetails notificationDetails);

	public Notification insertEmailDetails(List<EmailDetails> readEmail, String userId);

	public List<AcademicYear> getPreviousAndCurrentAcademicYear();

	public Student getStudentDetails(String rollNumber);

	public List<String> getUserIdToCreateNotification(String strQuery);
	
	
	public List<AnnualStock> selectAssetsForDepartment() throws CustomException;

	public String submitASTV(AnnualStockList annualStockList) throws CustomException;

	public List<AnnualStock> listASTV(String department) throws CustomException;

	public String updateASTV(AnnualStockList annualStockList) throws CustomException;

	/**@author Saif.Ali
	 * Date- 20/07/2017
	 * Used to get the list of items for the condemnation*/
	public List<Condemnation> listASTVForCondemnation() throws CustomException;

	public List<Condemnation> listCondemnation(String department) throws CustomException;

	public String submitCondemnation(CondemnationList condemnationList) throws CustomException;

	public String updateCondemnation(CondemnationList condemnationList) throws CustomException;
	
	public LicenseInfo validateLicenseDetails();

	public String insertIntoActivityLog(UpdateLog updateLog)throws CustomException;

	public List<UpdateLog> getActivityLog(String module, String functionality)throws CustomException;
	
	public List<AssetType> getAllAssetType() throws CustomException;

	public String addAssetType(AssetType assetType) throws CustomException;

	public String editAssetType(AssetType assetType) throws CustomException;

	public List<AssetSubType> getAllAssetSubType() throws CustomException;

	public String addAssetSubType(AssetSubType assetSubType) throws CustomException;

	public String editAssetSubType(AssetSubType assetSubType) throws CustomException;

	public String deleteAssetSubType(AssetSubType assetSubType) throws CustomException;
	
	public List<AssetSubType> getAssetSubTypeForAssetType(String assetType) throws CustomException;

	public int updateLibraryAssetDetails(Asset asset);
	
	/**@author naimisha*/
	
	List<Leave> getLeaveHistory(String userId);

	int insertIntoApprovalAndLeave(Leave leave);

	String getApprovarGroupName(String user_id);

	List<Leave> getLeaveDetails();
	
	public List<Leave> getResourceLeaveDetails(String userId);
	
	ResourceType getResourceTypeofUser(String userId);	
	
	List<ServiceType> getServiceTypeList();

	Ticket generateTicket(Ticket ticket);	

	String submitTicketAttachmentDoc(Ticket ticket);

	List<Ticket> getTicketList(String updatedBy);

	List<Ticket> getClosedTicketList(String updatedBy);

	Ticket getTicketDetails(Ticket ticket);

	Ticket updateTicket(Ticket ticket);	
	
	public List<FinancialYear> getFinancialYearList();

	public List<IncomeAge> getIncomeAgeList();

	public List<TeachingLevel> getTeachingLevelList();
	
//-------------------------FOR ADMISSION---------------------------
	
	public List<SocialCategory> getExistingSocialCategory();
	
	public List<AdmissionDrive> getDriveList(Resource r);
	
	List<MeritListType> getMeritListTypes();

	List<Venue> getExamVenues(Map<String, Object> parameters);

	int getTotalAllotedSeat(Venue venue);

	Venue getExamVenueDetails(Venue venue);

//------------------- For task delegation ------------------------	
	
	public List<Task> getDelegatedTask(Task task);

	public List<Task> getOutwardDelegatedTask(Task task);

	public String editVendorDetails(Vendor vendor);

	public Notification deleteEmailDetails(Notification notification);

	public FinancialYear getFinancialYear();
	
	public List<AcademicYear> getAllAcademicYear();

	public List<Course> getCourseForStandard(String standard);

	public List<Subject> getSubject();

	public String getStandatdCodeAgainstCourse(String course);
	
	public List<Section>getBatchAgainstCourse(String course);

	public List<Course> getCourseList();
	
	/**********Added by Naimisha 04042017**********/

	public String updateTicketAndTaskStatus(Ticket ticket);

	public List<Task> getAllTaskCommentForATask(Task task);

	public List<Section> getSectionsAgainstStandard(String standard)throws CustomException ;
	
	/**********Added By Naimisha 22042017*********/

	public List<Course> getCourseListForAStudent(String userId);
	
	public Course getCourseAgainstCourseCode(String courseCode, String userId);

	public Resource getResourceAgainstUserId(String userId);

	public String getrollNumberAgainstProgramAndUserId(String userId, String course);
	
	public String submitVendorType(VendorType vendorType);

	public String editVendorType(VendorType vendorType);

	public String inactiveVendorDetails(Vendor vendor);

	public String insertEmailNotification(Notification emailNotification);

	public List<EmailDetails> getsentEmailDetailsList(String userId);

	public EmailDetails getEmailDetailsAgainstEmailCode(String emailId);

	public EmailDetails getEmailContentForSentItemsAgainstEmailCode(String emailId);

	public String inactiveEmailFromSentBox(List<EmailDetails> emailDetailsList);

	public List<EmailDetails> inactiveEmailFromInBox(List<EmailDetails> emailDetailsList);

	/**@author Saif.Ali
	 * Date-19/07/2017
	 * Used to get the asset lists from the commodity*/
	/*public List<AnnualStock> selectAssetListForASTB();*/

	
	/*added by ranita.sur on 02082017 For Vendor emailId Validation */
	public String serverSideValidationOfVendorEmailId(String emailId);
	
	//Added By NAimisha 02082017
	public Employee getStaffSalaryDetailsForSalarySlip(Resource resource);

	public Ticket getTicketDetailsForMyService(Ticket ticket);

	public List<Task> getClosedTaskList(Task task);
	//Added By Naimisha 29082017

	public List<QuestionMaster> fetchQuestionAnswerForSurveyOfATicket(JobType jobType);

	public String insertTicketSurvey(Question question);

	public  List<Answer> getServeyDetailsForATicket(String ticketCode);
	
	/**Added by @author Saif.Ali
	 * Date-05/09/2017*/
	public List<Course> getProgramDetailsList(Course course);

	/**@author Saif.Ali*/
	public List<Fees> getAllFeesRelatedInformationForStudent(Course course);
	
	//Added By Naimisha 08092017

	public Student getStudentAgainstProgramAndUserId(String userId, String course);

	public List<BookAllocation> getBookFineDetails(String userId);

	public String insertChatDetailsForIndividualChat(String from, String to, String msg);

	public String updateChatStatusToReadForAUser(String user);

	public List<Notification> getChatDetailsForIndividualChatForAUser(String to, String from);

	public String updateEmailAttachmentDoc(Notification notification);

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
	
	//Added By Naimisha 06112017

	public Ticket updateMyTicket(Ticket ticket);

	//Added By Naimisha 220220018
	public Ticket getStudentLeaveDetailsAgainstTicket(String ticketCodeValue);

	//Added By Naimisha 29032018
	
	public List<String> getLevelListForTicket();

	public CommodityPurchaseOrder getCommodityPurchaseOrderDetaialsForATicket(String ticketCodeValue);

	public Ticket getUserListAssociatedWithATicket(String ticketCode);
	
	//Added by Naimisha 09042018

	public String insertTicketStatus(Ticket ticket);

	/**
	 * @author anup.roy
	 * this method is for getting status*/
	public List<StatusOfItem> getAllStatusOfItems();

	/**
	 * @author anup.roy
	 * this method is for getting status*/
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
