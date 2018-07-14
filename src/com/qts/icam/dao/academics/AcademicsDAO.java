package com.qts.icam.dao.academics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.qts.icam.model.academics.Algorithm;
import com.qts.icam.model.academics.AssetConsumption;
import com.qts.icam.model.academics.CoScholasticResult;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.academics.CourseSubjectMapping;
import com.qts.icam.model.academics.CourseType;
import com.qts.icam.model.academics.DescriptiveIndicatorSkill;
import com.qts.icam.model.academics.ExamMarks;
import com.qts.icam.model.academics.RoutineTableGridData;
import com.qts.icam.model.academics.StudentCourseSubjectMapping;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.academics.UserDefinedExams;
import com.qts.icam.model.academics.UserExamMarks;
import com.qts.icam.model.admission.AdmissionForm;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.backoffice.Term;
import com.qts.icam.model.backoffice.TimeTableGridData;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Asset;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.ExamType;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.common.TeacherSubjectMapping;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.event.EventAchievement;
import com.qts.icam.model.event.SchoolEvent;
import com.qts.icam.model.grade.GradingSystem;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.utility.customexception.CustomException;

public interface AcademicsDAO {
	
	List<Standard> getStandardsWithSection() throws CustomException;
	
	List<SchoolEvent> getAllEventAchievements();
	
	String addStandard(Standard standard) throws CustomException;

	String editStandard(Standard standard) throws CustomException;

	List<SubjectGroup> getSubjectGroup() throws CustomException;

	String addSubjectGroup(SubjectGroup subjectGroup) throws CustomException;

	String editSubjectGroup(SubjectGroup subjectGroup) throws CustomException;
	
	String addSubject(Subject subject) throws CustomException;

	String editSubject(Subject subject) throws CustomException;

	String getSubjectsForCourse(String course) throws CustomException;

	String editCourseSubjectMapping(CourseSubjectMapping courseSubjectMapping) throws CustomException;

	public List<ExamMarks> getSubjectsAndMarksForStandard(String standard, String exam) throws CustomException;

	String editExamMarks(List<ExamMarks> examMarksList) throws CustomException;

	String editStudentResult(List<StudentResult> studentResultList, String insertUpdate) throws CustomException;

	String insertAssignSection(List<Student> studentList) throws CustomException;

	List<Student> getPendingSectionAssignment() throws CustomException;

	List<Student> getStudentsToAssignSection(Student student) throws CustomException;

	List<StudentResult> getStudentsAndMarks(StudentResult studentResult,String loggedInUser) throws CustomException;

	List<DescriptiveIndicatorSkill> getDescriptiveIndicatorHeadList()throws CustomException;

	List<CoScholasticResult> getCoScholasticResultList()throws CustomException;

	List<CoScholasticResult> getStudentsForCoScholastic(
			CoScholasticResult coScholasticResult)throws CustomException;

	String saveCoScholasticResult(List<CoScholasticResult> studentList)throws CustomException;

	List<CoScholasticResult> getInsertedCoScholasticStudents(
			CoScholasticResult coScholasticResult)throws CustomException;

	String getStudentsCoScholasticResult(String roll, String loggedInUser)throws CustomException;

	String editCoScholasticResult(List<CoScholasticResult> studentList)throws CustomException;

	String getSubjectGroupForStandard(String standard)throws CustomException;

	String addUserDefinedExams(UserDefinedExams userDefinedExams)throws CustomException;

	String editUserDefinedExams(List<UserDefinedExams> userDefinedExamsList)throws CustomException;

	List<UserDefinedExams> getAllUserDefinedExams()throws CustomException;

	List<UserDefinedExams> getUserDefinedExamsForStandard(String standard)throws CustomException;

	List<UserExamMarks> getSubjectAndMarksForUserDefinedExams(UserExamMarks userExamMarks)throws CustomException;

	String editUserExamMarks(List<UserExamMarks> examMarksList)throws CustomException;

	List<StudentResult> getStudentsAndMarksForUserDefinedExams(StudentResult studentResult)throws CustomException;

	String editStudentResultForUserExam(List<StudentResult> studentResultList)throws CustomException;
	
	public int addNewAssetDetails(Asset asset) throws CustomException;

	public String assetNameValidation(String assetName) throws CustomException;

	public List<Asset> getAssetList() throws CustomException;

	public List<Asset> getSearchAssetList(Map<String, Object> parameters) throws CustomException;

	public Asset getAssetDetails(String assetId) throws CustomException;

	public int updateAssetDetails(Asset asset) throws CustomException;

	public List<Department> getAllDepartment() throws CustomException;

	public String resetStudentResult(StudentResult studentResult) throws CustomException;
	
	public List<Exam> getExamForStandard(String standard) throws CustomException;

	public String submitAssetConsumption(AssetConsumption assetConsumption) throws CustomException;

	public List<AssetConsumption> getAssetConsumptionList(AssetConsumption assetConsumption) throws CustomException;

	public AssetConsumption getAssetCurrentQuantity(int assetId) throws CustomException;

	public List<CourseType> getCourseTypeList() throws CustomException;

	public String createCourseType(CourseType courseType) throws CustomException;

	public String editCourseType(CourseType courseType) throws CustomException;

	public String createCourse(Course course) throws CustomException;

	public List<Course> getCourseList() throws CustomException;

	

	public List<Student> getStudentsNameAndRollForCourse(String course) throws CustomException;

	public String createStudentCourseSubjectMapping(List<StudentCourseSubjectMapping> scsmList) throws CustomException;

	public List<Student> getSubjectsMappedStudents(String course) throws CustomException;

	public String getSubjectsStudiedByStudentInCourse(StudentCourseSubjectMapping scsm) throws CustomException;

	

	

	public List<String> getExamsForCourse(String course) throws CustomException;

	public String editCourse(AdmissionForm admissionForm) throws CustomException;

	public int saveCourse(AdmissionForm admissionForm, String updatedBy);

	public List<AdmissionForm> getAllCourseList();

	public List<Student> getAllStudentsList() throws CustomException;
	
	/**********Added by naimisha22102016**/
	
	public List<Subject> getSubjectFromStandardTeacherSubjectMapping(TeacherSubjectMapping teacherSubjectMapping);

	public List<ExamType> getExamType();

	public List<ExamType> saveExamType(ArrayList<ExamType> examTypeList);

	public String saveExam(ArrayList<Exam> examList) throws CustomException;

	public List<Exam> getExamDetails();

	public List<Exam> savePromotionalExam(Exam exam);

	public List<Exam> getPromotionalExamList();

	public String submitEditedPromotionalExam(Exam ex);

	public List<Term> getTermsForACourse(String course);

	public String editExam(Exam exam);

	public List<Exam> getExamsForTermAndCourse(Exam exam);
	
	/**********Added By Naimisha 27022017**********/

	public String getSubjectsForACourseAndTermAndTeacher(Course courseObj);

	public List<ExamMarks> getSubjectsAndMarksForCourseExamTerm(String standard, String exam, String term);
	
	/**************Added By Sourav 01032017*************/
	

	public AcademicYear getCurrentAcademicYear() throws CustomException; 
		
	public String createTerm(Term term) throws CustomException; 
		
	public List<Term> getTermList() throws CustomException;
		
	//public List<Term> getTermNameForCourse(String course) throws CustomException;
		
	String editTermCourseSubjectMapping(CourseSubjectMapping courseSubjectMapping) throws CustomException;
		
	public List<Subject> getSubjectForCourseAndTerm(Term term) throws CustomException;

	/*******************************03032017GFor Aminities Usage Of Student****************/
	public List<Student> getStudentsForAllProgrammes();

	public Student getAminitiesUsageByStudent(String rollNumber);

	public Employee getTeacherAgainstCourse(Course course);

	public List<Student> getStudentsForAllProgrammesAndBatches(Course course);
	
	/*******Work From Home*******/

	public String inactiveStandard(Standard standard);

	public String editTerm(Term term);
	
	/*********kaustav21032017**********/
	public String inactiveExam(Exam exam);
	public String inactiveTerm(Term term);
	public String inactiveDeleteCourse(Course course);
	
	
	/*******sourav 21032017***********/
	public String inactiveProgramType(CourseType  courseType);

	List<Standard> getStandardsForAssignBatch();

	public List<Subject> getSubjectsForACourseAndTeacher(Course courseObj);
	
	public List<Venue> getVenueListForExam();

	public List<Algorithm> getAllAlgorithmList();

	public String insertExternalExamination(Exam exam);
	
	public Venue getVenueDetailsAgainstVenueCode(String venueCode);
	
	public List<Exam> getExternalExamList();

	//Modified By Naimisha 21092017
	
	public Exam getVenueDetailsAgainstExam(String exam);
	///modifiedin v2
	public String saveAdmissionDrive(AdmissionForm admissionForm, String updatedBy);

	public List<AdmissionForm> getAllCourseDetails();

	public AdmissionForm getAllCourseDetailsForEdit(String courseDetailsCode);

	public String updateCourseDetailsForAdmissionDrive(AdmissionForm admissionForm);

	public List<Course> getListOfProgramsToPublish();

	public List<Course> listOfPublishedProgramsList();

	public Course getProgramDriveDetailsForProgram(String courseCode);
	
	/**New CBSE System start**/
	
	/**
	 * @author anup.roy
	 * */
	
	public List<ExamType> getExamTypeNew();
	
	/**
	 * @author anup.roy
	 * */
	
	public List<Course> getAllCourses();
	
	public List<Term> getAllTermList();
	
	public String getExamTypesForATerm(String term);
	
	/**
	 * @author anup.roy
	 * this method is for get all exams*/
	
	public List<Exam> getAllNewExams();
	
	/**
	 * @author anup.roy
	 * this method is for submit exam*/
	
	public String submitExamNew(Exam exam);
	
	public List<Exam> getTermForStandard(String standard);

	public List<Exam> getExamForStandardAndTerm(Exam examObj);

	public String editIntoExamMarks(List<ExamMarks> examMarksList);

	public List<ExamMarks> getSubjectsAndMarksForStandardAndExam(String standard, String exam);

	public List<Exam> getExamsForStandard(String standard) throws CustomException;
	
	public List<StudentResult> getMarksForStudents(StudentResult studentResult,String loggedInUser) throws CustomException;

	public List<CoScholasticResult> getStudentsForCoScholasticNew(CoScholasticResult coScholasticResult);

	public List<DescriptiveIndicatorSkill> getDescriptiveIndicatorHeadListNew();

	public String saveCoScholasticResultNew(List<CoScholasticResult> studentList);

	public String editGradingSystemNew(List<GradingSystem> gradingSystemList);

	public List<GradingSystem> getGradingSystemForStandardNew(String standard);

	public String getCoScolasticResultAgainsRollNumberNew(String roll, String loggedInUser, String term);

	public String editStudentResultNew(List<StudentResult> studentResultList, String insertUpdate);

	public List<StudentResult> getMarksForStudentsNew(StudentResult studentResult, String loggedInUser);	
	/**New CBSE System end**/
	
	/**@author anup.roy
	 * this is for get all scholastic types
	 * 04.08.2017 */
	public List<SubjectGroup> getScholasticTypeList();

	/*Added By ranita.sur on 03082017 for getting the strength of Student*/
	public String getStrengthOfStudents(String standard,String section);

	public List<Standard> getStandardsForSetExamMarks();

	public Exam getPrograamDetailsAgainstExam(String exam);
	
	//Added by naimisha 17102017
	public List<Student> getStudentListForAProgram(String programCode);

	//******Added By Naimisha 16042018******//
	
	public String createClassTeacher(Standard standard);

	public List<StudentResult> getAllClassTeacherList();
	
	public String getExamNameFromExamTableTimeGrid(String exam);
	
	public List<Course> getProgramListAndSemeterForExamRoutine(String exam);
	
	public List<Subject> getSubjectForCourseAndTermForExamRoutine(Course course);

	public String insertIntoExamTableTimeGrid(List<Course> courseList);
	
	public List<RoutineTableGridData> getAllRoutineTableGridDataAgainstExam(String exam);
	
	public TimeTableGridData getRoutineTableGridData(TimeTableGridData timeTableGridData);

	public void updateRoutineTableGridData(TimeTableGridData timeTableGridData);

	public void insertRoutineTableGridData(TimeTableGridData timeTableGridData);
	
	//PRAD JUNE 5 2018
	public List<SchoolEvent> getSchoolEventList();
	
	public String insertSchoolEvent(SchoolEvent schoolEvent);
	
	//PRAD JUNE 6 2018
	public String getEventDescription(String eventName);
	
	public String submitEventAchievement(List<EventAchievement> eventAcheivementList);
	
	//PRAD JUNE 12 2018
	public SchoolEvent selectEventName(String eventCode);
}
