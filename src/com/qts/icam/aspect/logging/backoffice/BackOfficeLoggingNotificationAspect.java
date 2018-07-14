package com.qts.icam.aspect.logging.backoffice;

import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.backoffice.AcademicTimeTable;
import com.qts.icam.model.backoffice.CalendarEvent;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.backoffice.Fees;
import com.qts.icam.model.backoffice.FeesTemplate;
import com.qts.icam.model.common.TeacherSubjectMapping;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Candidate;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.Scholarship;
import com.qts.icam.model.common.Student;
import com.qts.icam.service.common.CommonService;




@Aspect
@Component
public class BackOfficeLoggingNotificationAspect {
	
	@Autowired
	CommonService commonService;
	private final static Logger logger = Logger.getLogger(BackOfficeLoggingNotificationAspect.class);
	
	
	/**
	 * this method is used for log into database for  access editAcademicYear() method  in BackOfficeService   for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.backoffice.BackOfficeService.editAcademicYear(..))"+"&& args(academicYear)",returning= "result")
	public void editAcademicYear(JoinPoint joinPoint,AcademicYear academicYear,Object result) {
		try{
			String functionalityName="Create Academic Year";
			String moduleName="OFFICE ADMINISTRATION";
			
			String subjectName="Updated Academic Year";
			String loggingDesc="Academic year updated- "+academicYear.getAcademicYearCode()+", Session open date :: "+academicYear.getSessionStartDate()+", Session close date :: "+academicYear.getSessionEndDate();
			
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
			loggingAspect.setUpdatedBy(academicYear.getUpdatedBy());
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in editAcademicYear(JoinPoint joinPoint,...........) method in BackOfficeLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access addFees() method  in BackOfficeService   for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.backoffice.BackOfficeService.addFees(..))"+"&& args(fees)",returning= "result")
	public void addFees(JoinPoint joinPoint,Fees fees,Object result) {
		try{
			String functionalityName="Create Fees";
			String moduleName="OFFICE ADMINISTRATION";
			
			String subjectName="Added New Fees category";
			String loggingDesc="Fees category added- "+fees.getFeesName();
			
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
			loggingAspect.setUpdatedBy(fees.getUpdatedBy());
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addFees(JoinPoint joinPoint,...........) method in BackOfficeLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * this method is used for log into database for  access addFeesTemplate() method  in BackOfficeService   for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.backoffice.BackOfficeService.addFeesTemplate(..))"+"&& args(feesTemplate)",returning= "result")
	public void addFeesTemplate(JoinPoint joinPoint,FeesTemplate feesTemplate,Object result) {
		try{
			String functionalityName="Create Fees";
			String moduleName="OFFICE ADMINISTRATION";
			
			String subjectName="New Fees Template Created";
			String loggingDesc="New Fees Template Created- "+feesTemplate.getTemplateName() +" for class "+feesTemplate.getStandard();
			
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
			loggingAspect.setUpdatedBy(feesTemplate.getUpdatedBy());
			if(result!=null && "Insert Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addFeesTemplate(JoinPoint joinPoint,...........) method in BackOfficeLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access addScholarship() method  in BackOfficeService   for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.backoffice.BackOfficeService.addFeesTemplate(..))"+"&& args(scholarship)",returning= "result")
	public void addScholarship(JoinPoint joinPoint,Scholarship scholarship,Object result) {
		try{
			String functionalityName="Create Scholarship";
			String moduleName="OFFICE ADMINISTRATION";
			
			String subjectName="New Scholarship Created";
			String loggingDesc="New Scholarship Created- "+scholarship.getScholarshipName() +" with concession  "+scholarship.getConcession()+"("+scholarship.getConcessionUnit()+")";
			
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
			loggingAspect.setUpdatedBy(scholarship.getUpdatedBy());
			if(result!=null && "Insert Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addScholarship(JoinPoint joinPoint,...........) method in BackOfficeLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * this method is used for log into database for  access editScholarship() method  in BackOfficeService   for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.backoffice.BackOfficeService.editScholarship(..))"+"&& args(scholarshipList)",returning= "result")
	public void editScholarship(JoinPoint joinPoint,List<Scholarship> scholarshipList,Object result) {
		try{
			String functionalityName="Create Scholarship";
			String moduleName="OFFICE ADMINISTRATION";
			
			String subjectName="Scholarship Updated";
			String loggingDesc=scholarshipList.size()+ " scholarship updated";
			
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
			loggingAspect.setUpdatedBy(scholarshipList.get(0).getUpdatedBy());
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in editScholarship(JoinPoint joinPoint,...........) method in BackOfficeLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * this method is used for log into database for  access addExamDateSetUp() method  in BackOfficeService   for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.backoffice.BackOfficeService.addExamDateSetUp(..))"+"&& args(exam)",returning= "result")
	public void addExamDateSetUp(JoinPoint joinPoint,Exam exam,Object result) {
		try{
			String functionalityName="Assign Event";
			String moduleName="OFFICE ADMINISTRATION";
			
			String subjectName="Set up exam date";
			String loggingDesc=exam.getExamCode() +" exam date set up from "+exam.getExamStartDate() +" to "+exam.getExamEndDate();
			
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
			loggingAspect.setUpdatedBy(exam.getUpdatedBy());
			if(result!=null && "Insert Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addExamDateSetUp(JoinPoint joinPoint,...........) method in BackOfficeLoggingNotificationAspect.java", e);
		}
	}
	

	/**
	 * this method is used for log into database for  access insertAssignedEvent() method  in BackOfficeService   for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.backoffice.BackOfficeService.insertAssignedEvent(..))"+"&& args(calendarEvent)",returning= "result")
	public void insertAssignedEvent(JoinPoint joinPoint,CalendarEvent calendarEvent,Object result) {
		try{
			String functionalityName="Assign Event";
			String moduleName="OFFICE ADMINISTRATION";
			
			String subjectName="New Event Assigned";
			String loggingDesc="Event Assigned for "+calendarEvent.getCalendarEventName() +" from "+calendarEvent.getCalendarEventStartDate() +" to "+calendarEvent.getCalendarEventEndDate();
			loggingDesc=loggingDesc+", Start time: "+calendarEvent.getCalendarEventStartTime()+" End Time: "+calendarEvent.getCalendarEventEndTime();
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
			loggingAspect.setUpdatedBy(calendarEvent.getUpdatedBy());
			if(result!=null && "Insert Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in insertAssignedEvent(JoinPoint joinPoint,...........) method in BackOfficeLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * this method is used for log into database for  access addSubjectBreakAndTeacherForTimeTable() method  in BackOfficeService   for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.backoffice.BackOfficeService.addSubjectBreakAndTeacherForTimeTable(..))"+"&& args(academicTimeTable1)",returning= "result")
	public void addSubjectBreakAndTeacherForTimeTable(JoinPoint joinPoint,AcademicTimeTable academicTimeTable1,Object result) {
		try{
			String functionalityName="Assign Event";
			String moduleName="OFFICE ADMINISTRATION";
			
			String subjectName="Added Subject For TimeTable";
			String loggingDesc="Added subject and teacher for time table for class "+academicTimeTable1.getTimeTableClass().getStandardCode();
			loggingDesc=loggingDesc+",Section  "+academicTimeTable1.getTimeTableSection().getSectionCode();
			loggingDesc=loggingDesc+",Academic Year "+academicTimeTable1.getAcademicYear().getAcademicYearCode();
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
			loggingAspect.setUpdatedBy(academicTimeTable1.getUpdatedBy());
			if(result!=null && "Insert Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addSubjectBreakAndTeacherForTimeTable(JoinPoint joinPoint,...........) method in BackOfficeLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * this method is used for log into database for  access editTeacherSubjectMapping() method  in BackOfficeService   for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.backoffice.BackOfficeService.editTeacherSubjectMapping(..))"+"&& args(teacherSubjectMapping)",returning= "result")
	public void editTeacherSubjectMapping(JoinPoint joinPoint,TeacherSubjectMapping teacherSubjectMapping,Object result) {
		try{
			String functionalityName="Teacher Subject Mapping";
			String moduleName="OFFICE ADMINISTRATION";
			
			String subjectName="Updated Teacher Subject Mapping";
			String loggingDesc="Teacher subject mapping updated for "+teacherSubjectMapping.getResource().getUserId();
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
			loggingAspect.setUpdatedBy(teacherSubjectMapping.getUpdatedBy());
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in editTeacherSubjectMapping(JoinPoint joinPoint,...........) method in BackOfficeLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * this method is used for log into database for  access addStudent() method  in BackOfficeService   for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.backoffice.BackOfficeService.addStudent(..))"+"&& args(student)",returning= "result")
	public void addStudent(JoinPoint joinPoint,Student student,Object result) {
		try{
			String functionalityName="Create Student";
			String moduleName="OFFICE ADMINISTRATION";
			
			String subjectName="New Student Added";
			String loggingDesc="Student added for "+student.getStandard()+", roll no. : "+student.getRollNumber();
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
			loggingAspect.setUpdatedBy(student.getUpdatedBy());
			if(result!=null && "Insert Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addStudent(JoinPoint joinPoint,...........) method in BackOfficeLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * this method is used for log into database for  access editStudent() method  in BackOfficeService   for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.backoffice.BackOfficeService.editStudent(..))"+"&& args(student)",returning= "result")
	public void editStudent(JoinPoint joinPoint,Student student,Object result) {
		try{
			String functionalityName="Create Student";
			String moduleName="OFFICE ADMINISTRATION";
			
			String subjectName="Student Details Updated";
			String loggingDesc="Student details updated for roll no.  "+student.getRollNumber();
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
			loggingAspect.setUpdatedBy(student.getUpdatedBy());
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in editStudent(JoinPoint joinPoint,...........) method in BackOfficeLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * this method is used for log into database for  access updateStudentPromotion() method  in BackOfficeService   for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.backoffice.BackOfficeService.updateStudentPromotion(..))"+"&& args(studentResultList)",returning= "result")
	public void updateStudentPromotion(JoinPoint joinPoint,List<StudentResult> studentResultList,Object result) {
		try{
			String functionalityName="Create Student";
			String moduleName="OFFICE ADMINISTRATION";
			
			String subjectName="Update Student Promotion/Passout";
			String loggingDesc="Students "+studentResultList.get(0).getStatus() +" to  class "+studentResultList.get(0).getStandard()+" and section "+studentResultList.get(0).getSection();
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
			loggingAspect.setUpdatedBy(studentResultList.get(0).getUpdatedBy());
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in updateStudentPromotion(JoinPoint joinPoint,...........) method in BackOfficeLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * this method is used for log into database for  access approveDocument() method  in BackOfficeService   for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.backoffice.BackOfficeService.approveDocument(..))"+"&& args(candidate)",returning= "result")
	public void approveDocument(JoinPoint joinPoint,Candidate candidate,Object result) {
		try{
			String functionalityName="Create Student";
			String moduleName="OFFICE ADMINISTRATION";
			
			String subjectName="Student Document Verification";
			String loggingDesc="Approved Student for roll no."+candidate.getRollNumber();
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
			loggingAspect.setUpdatedBy(candidate.getUpdatedBy());
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in approveDocument(JoinPoint joinPoint,...........) method in BackOfficeLoggingNotificationAspect.java", e);
		}
	}
	
	
}
