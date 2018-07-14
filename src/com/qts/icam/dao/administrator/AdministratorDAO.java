package com.qts.icam.dao.administrator;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.qts.icam.model.common.TaskDetails;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.administrator.AccessType;
import com.qts.icam.model.administrator.Approver;
import com.qts.icam.model.administrator.EmailEventAndTemplate;
import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.administrator.ProfileMatrix;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.administrator.StaffForXml;
import com.qts.icam.model.administrator.StudentForXml;
import com.qts.icam.model.administrator.UserGroup;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.RepositoryStructure;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.erp.Designation;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.login.LoginForm;
import com.qts.icam.model.survey.Question;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.ticket.TicketStatus;
import com.qts.icam.utility.customexception.CustomException;


public interface AdministratorDAO {

	List<Module> getmoduleList() throws CustomException;

	List<Role> getRolesForModule(String moduleCode)throws CustomException;

	String addRoles(Role role)throws CustomException;

	String inActiveRole(Role role)throws CustomException;

	Resource getResourceAndRolesFromDB()throws CustomException;

	String addRoleContactMapping(List<Resource> resourceList)throws CustomException;

	List<Role> getlistRoleContactMapping()throws CustomException;

	List<Role> searchRoleContactmapping(Map<String, Object> parameters)throws CustomException;

	List<Resource> getRoleContactMapping(Role role)throws CustomException;

	String editRoleContactMapping(List<Resource> resourceList)throws CustomException;

	List<AccessType> getAccessTypeList()throws CustomException;

	String addRoleAccessMapping(AccessType accessType)throws CustomException;

	AccessType editAccessTypeRoleMapping(AccessType accessType)throws CustomException;

	String updateAccessTypeRoleMapping(AccessType accessType)throws CustomException;

	String inactiveAccessType(AccessType accessType)throws CustomException;

	Resource getResourceAndAccessFromDB()throws CustomException;

	String addAccessTypeContactMapping(Resource resource)throws CustomException;

	List<Resource> listAccessTypeContactMapping()throws CustomException;

	List<Resource> accessTypeContactMappingSearch(
			Map<String, Object> parameters)throws CustomException;

	String inactiveAccessTypeContactMapping(String resourceId, String updatedBy)throws CustomException;

	Module getFunctionalitiesForRole(Role role)throws CustomException;

	String insertFunctionalityRoleMapping(List<Role> roleList)throws CustomException;

	String addUserGroup(UserGroup userGroup)throws CustomException;

	List<UserGroup> getAllUserGroups()throws CustomException;

	String inactiveUserGroupDetails(UserGroup userGroup)throws CustomException;

	UserGroup getUserGroupDetails(UserGroup userGroup)throws CustomException;

	Module getNotificationDetails(String module)throws CustomException;

	String updateLoggingNotificationSetUp(Module module, String updatedBy)throws CustomException;

	List<Ticket> viewSLAForTicketing()throws CustomException;

	String createTicketingSLA(List<Ticket> ticketList)throws CustomException;

	String updateTicketingSLA(Ticket ticket)throws CustomException;

	String createArchiving(String academicYear, String userId)throws CustomException;

	String purgeRecordPost(String academicYear, String userId)throws CustomException;

	StudentForXml readArchivedDataForStudent(File file)throws CustomException;

	StaffForXml readArchivedDataForStaff(File file)throws CustomException;

	List<LoggingAspect> getNotificationMediums();

	String updateNotificationMediums(List<LoggingAspect> notificationList);

	String insertApprovers(Approver approver)throws CustomException;

	List<Approver> getAllApproverGroups()throws CustomException;

	String inactiveApproverGroupDetails(Approver approver)throws CustomException;

	Approver getApproverGroupDetails(Approver approver)throws CustomException;

	int insertUserPasswordStatus(LoginForm login)throws CustomException;

	int updateUserPasswordStatus(LoginForm login)throws CustomException;

	String createTicketingSLA(Ticket ticket)throws CustomException;
	//missing link integration 17042018
	List<JobType> getAllJobDetails();
	
	int saveJobDetails(JobType jobType);

	List<Approver> getAllApprovalOrderList();

	public String insertApprovalOrder(List<Approver> approverGroupList); //Modified By Naimisha 25082017

//----------------For task delegation and notification-------------	
	
	List<Resource> getResourceDetails(String resourceTypeName) throws CustomException;

	List<String> getAllTaskNameList() throws CustomException;

	String insertIntoTaskDetails(Resource resource);

	List<TaskDetails> getAllTaskDetailsList(String userName);
	
	/********************Done By Naimisha********************/
	
	public String setInactiveSubjectGroup(List<SubjectGroup> subjectGroupList);

	public String setInactiveSubject(List<Subject> subjectList);

	public String setInactiveStaff(List<Resource> resourceList);

	public String setInactiveStudent(List<Resource> resourceList);

	public String setCourseInactive(List<Course> courseList);
	
	public String submitRepositoryDirectory(RepositoryStructure repository);

	public RepositoryStructure getRespositoryStructure();

	public String deleteSLATicket(Ticket ticket);

	public List<Approver> getApproverGroupDetailsForJobType(String jobType);

	public List<Approver> getApproversListAgainstUserId(String userId);

	public List<JobType> getJobListAgainstApproverGroup(String approvarGroupCode);

	public List<TaskDetails> getTaskDetailsAgainstJobTypeAndApproverGroup(TaskDetails taskDetails);

	public List<Task> getTaskDetailsForATicket(Task task);
	
	public String inActiveRoleContactMapping(Role role);
	
	public List<Role> getAllRolesForProfilematrix();

	public List<Module> getAllModulesForProfilematrix();

	public String submitProfileMatrix(List<ProfileMatrix> profileMatrixList);

	public List<Module> getModuleListForSpecificRole(String roleCode);

	public Module getTabAndSearchForModuleAndRole(String roleCode, String moduleCode);

	/**@author Saif.Ali
	 * Date- 01/08/2017
	 * Used to insert the email events into the database*/
	public String insertEventForTemplate(EmailEventAndTemplate emailEvent);

	/**@author Saif.Ali
	 * Date- 02/08/2017
	 * Used to get the list of Events of template to show in JSP*/
	public List<EmailEventAndTemplate> getEventForTemplateList();

	/**@author Saif.Ali
	 * Date-02/08/2017
	 * used to edit the event names into the database*/
	public String editEventForTemplate(EmailEventAndTemplate emailEvent);

	/**@author Saif.Ali
	 * Date-03/08/2017
	 * Used to insert the email template into the database*/
	public String insertTemplateForEvent(EmailEventAndTemplate emailEvent);

	
	/**@author Saif.Ali
	 * Date-03/08/2017
	 * Used to get the list of email template into the database*/
	public List<EmailEventAndTemplate> getListOfTemplateForEvent();

	
	/**@author Saif.Ali
	 * Date-04/08/2017
	 * Used to get the list of Event for mapping*/
	public List<EmailEventAndTemplate> getEventListForMapping();

	
	/**@author Saif.Ali
	 * Date-04/08/2017
	 * Used to get the list of Template for mapping*/
	public List<EmailEventAndTemplate> getTemplateListForMapping();

	/**@author Saif.Ali
	 * Date- 04/08/2017
	 * Used to insert the email and template mapping data into the database*/
	public String insertEmailEventAndTemplateMapping(EmailEventAndTemplate emailEvent);

	/**@author Saif.Ali
	 * Date- 04/08/2017
	 * Used to get the email and template mapping data into the database*/
	public List<EmailEventAndTemplate> getEmailEventTemplateMappingList();

	/**@author Saif.Ali
	 * Date- 04/08/2017
	 * Used to get the template data from the database*/
	public EmailEventAndTemplate getTheTemplateValuesToEdit(String templateCode);
	
	/**@author Saif.Ali
	 * Date- 04/08/2017
	 * Used to update the template */
	public String editTemplateInformationForemail(EmailEventAndTemplate emailEvent);
	//missing link integration 17042018
	public List<Question> getAllSurveyList();
	//missing link integration 17042018
	public String submitMapWithServey(JobType jt);
	/*<!-- added By ranita.sur on 29082017 for mapping with survey -->*/
	//missing link integration 17042018
	public List<JobType> getAllTaskSurveyList();

	
	//Added By Naimisha 16102017
	public String insertIntoCategoryAndCategoryTaskMapping(JobType jobType);
	//missing link integration 17042018
	public List<JobType> getCategoryList();

	public List<JobType> getTaskistForCategory(String categoryCode);

	public String updateIntoCategoryAndCategoryTaskMapping(JobType jobType);

	public String insertCategoryRecipientMapping(JobType jobType);

	public List<JobType> getCategoryListForRecipientMapping();

	public List<JobType> getCategoryRecipientMapping(String categoryCode);

	public String editCategoryRecipientMapping(JobType jobType);

	public List<Ticket> getAllTicketStatusList();
	
	
	
	/*Added by ranita.sur on 17102017 for editing the recipient group*/
	public List<Approver> getUserIdListForApprover(Approver approverCode);
	
	public String updateIntoApproverGroupTaskMapping(Approver approver);
	
	//added By Naimisha 23102017

	public String insertTaskStatus(Ticket ticket);

	public List<Ticket> getAllTaskStatusList();
	
	/**Edit Task Status Details
	 By Ranita.Sur 24102017**/
	public String editTaskStatus(Ticket ticket);

	//Added By Naimisha 03042018
	
	public String insertCategoryKeyMapping(JobType jobType);

	public List<Ticket> getKeyForACategory(String category);

	public List<Ticket> getAllCategoryWithKeys();

	public List<JobType> getAllKeysList();

	public String editCategoryKeyMapping(JobType jobType);
	
	///Added by Naimisha 09042018  

	public String insertCategoryDepartmentMapping(JobType jobType);

	public List<Department> getAllCategoryMappedWithDepartment();

	public List<JobType> getCategoryListForADepartment(String departmentCode);

	//Added by naimisha 10042018
	
	public List<Designation> getDesignationListForDepartment(String department);

	public List<Functionality> getAllFunctionalityList();
	
	//Added by naimisha 08052018

	public JobType taskDetailsAgainstTaskCode(String taskCode);
	
	//Added By Naimisha 17052018

	public TicketStatus getTaskStatusForDuplicateCheck(Ticket ticket);
}
