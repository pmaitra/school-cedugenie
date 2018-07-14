package com.qts.icam.aspect.logging.academics;

import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qts.icam.model.academics.ExamMarks;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.Standard;
import com.qts.icam.service.academics.AcademicsService;
import com.qts.icam.service.common.CommonService;



@Aspect
@Component
public class AcademicsLoggingNotificationAspect {

	
	@Autowired
	CommonService commonService;
	
	@Autowired
	AcademicsService academicsService;
	
	private final static Logger logger = Logger.getLogger(AcademicsLoggingNotificationAspect.class);
	
	
	
	/**
	 * this method is used for log into database for  access addStandard() method  in AcademicsService   and calling to AcademicsServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.academics.AcademicsService.addStandard(..))"+"&& args(standard)",returning= "result")
	public void addStandard(JoinPoint joinPoint,Standard standard,Object result) {
		try{
			String functionalityName="Create Standard";
			String subjectName="Create New Class";
			String moduleName="ACADEMICS";
			String loggingDesc="Create new "+standard.getStandardName();
			
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
			loggingAspect.setUpdatedBy(standard.getUpdatedBy());
			if(result!=null && "Insert Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addStandard(JoinPoint joinPoint,Book book,String updatedBy,Object result) method in AcademicsLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access editStandard() method  in AcademicsService   and calling to AcademicsServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.academics.AcademicsService.editStandard(..))"+"&& args(standardList)",returning= "result")
	public void editStandard(JoinPoint joinPoint,List<Standard> standardList,Object result) {
		try{
			String functionalityName="Create Standard";
			String subjectName="Update Class";
			String moduleName="ACADEMICS";
			String loggingDesc = "";
			if(null!=standardList && standardList.size()!=0){
				for(Standard standard:standardList){
					loggingDesc="Update"+standard.getStandardName();
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
			if(null!=standardList && standardList.size()!=0){
			loggingAspect.setUpdatedBy(standardList.get(0).getUpdatedBy());
			}
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in editStandard(JoinPoint joinPoint,List<Standard> standardList,Object result) method in AcademicsLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access addSubjectGroup() method  in AcademicsService   and calling to AcademicsServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.academics.AcademicsService.addSubjectGroup(..))"+"&& args(subjectGroup)",returning= "result")
	public void addSubjectGroup(JoinPoint joinPoint,SubjectGroup subjectGroup,Object result) {
		try{
			String functionalityName="Create Subject Group";
			String subjectName="Create Subject Group";
			String moduleName="ACADEMICS";
			String loggingDesc="Create New "+subjectGroup.getSubjectGroupName();
				
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
			loggingAspect.setUpdatedBy(subjectGroup.getUpdatedBy());			
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addSubjectGroup(JoinPoint joinPoint,SubjectGroup subjectGroup,Object result) method in AcademicsLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access editSubjectGroup() method  in AcademicsService   and calling to AcademicsServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.academics.AcademicsService.editSubjectGroup(..))"+"&& args(subjectGroupList)",returning= "result")
	public void editSubjectGroup(JoinPoint joinPoint,List<SubjectGroup> subjectGroupList,Object result) {
		try{
			String functionalityName="Create Subject Group";
			String subjectName="Update Subject Group";
			String moduleName="ACADEMICS";
			String loggingDesc="";
			if(null!=subjectGroupList && subjectGroupList.size()!=0){
				for(SubjectGroup subjectGroup:subjectGroupList){
					loggingDesc="Update"+subjectGroup.getSubjectGroupName();
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
			if(null!=subjectGroupList && subjectGroupList.size()!=0){
				loggingAspect.setUpdatedBy(subjectGroupList.get(0).getUpdatedBy());
			}						
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in editSubjectGroup(JoinPoint joinPoint,List<SubjectGroup> subjectGroupList,Object result) method in AcademicsLoggingNotificationAspect.java", e);
		}
	}
	
	
	
	/**
	 * this method is used for log into database for  access addSubject() method  in AcademicsService   and calling to AcademicsServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.academics.AcademicsService.addSubject(..))"+"&& args(subject)",returning= "result")
	public void addSubject(JoinPoint joinPoint,Subject subject,Object result) {
		try{
			String functionalityName="Create Subject Group";
			String subjectName="Create New Subject";
			String moduleName="ACADEMICS";
			String loggingDesc="Add New Subject"+subject.getSubjectName();
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
			loggingAspect.setUpdatedBy(subject.getUpdatedBy());
									
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addSubject(JoinPoint joinPoint,Subject subject,Object result) method in AcademicsLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access editSubject() method  in AcademicsService   and calling to AcademicsServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.academics.AcademicsService.editSubject(..))"+"&& args(subjectList)",returning= "result")
	public void editSubject(JoinPoint joinPoint,List<Subject> subjectList,Object result) {
		try{
			String functionalityName="Create Subject Group";
			String subjectName="Update Subject";
			String moduleName="ACADEMICS";
			String loggingDesc="";
			if(null!=subjectList && subjectList.size()!=0){
				for(Subject subject:subjectList){
					loggingDesc="Update Subject"+subject.getSubjectName();
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
			if(null!=subjectList && subjectList.size()!=0){
				loggingAspect.setUpdatedBy(subjectList.get(0).getUpdatedBy());
			}									
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in editSubject(JoinPoint joinPoint,List<Subject> subjectList,Object result) method in AcademicsLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * this method is used for log into database for  access editClassSubjectMapping() method  in AcademicsService   and calling to AcademicsServiceImpl.java for insert logging information.
	 *//*
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.academics.AcademicsService.editClassSubjectMapping(..))"+"&& args(standardSubjectMapping)",returning= "result")
	public void editClassSubjectMapping(JoinPoint joinPoint,StandardSubjectMapping standardSubjectMapping,Object result) {
		try{
			String functionalityName="Standard Subject Mapping";
			String subjectName="Class Subject Mapping";
			String moduleName="ACADEMICS";
			String loggingDesc="Subject Mapping For"+standardSubjectMapping.getSubject();
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
			loggingAspect.setUpdatedBy(standardSubjectMapping.getUpdatedBy());
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in editClassSubjectMapping(JoinPoint joinPoint,StandardSubjectMapping standardSubjectMapping,Object result) method in AcademicsLoggingNotificationAspect.java", e);
		}
	}*/
	
	
	/**
	 * this method is used for log into database for  access editGradingSystem() method  in AcademicsService   and calling to AcademicsServiceImpl.java for insert logging information.
	 */
	/*@AfterReturning(pointcut ="execution(*  com.qts.icam.service.academics.AcademicsService.editGradingSystem(..))"+"&& args(gradingSystemList)",returning= "result")
	public void editGradingSystem(JoinPoint joinPoint,List<GradingSystem> gradingSystemList,Object result) {
		try{
			String functionalityName="Set Exam Marks";
			String subjectName="Update Grading System";
			String moduleName="ACADEMICS";
			String loggingDesc="";
			if(null!=gradingSystemList && gradingSystemList.size()!=0){
				for(GradingSystem gradingSystem:gradingSystemList){
					loggingDesc="Update Grading System For "+gradingSystem.getStandard();
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
			if(null!=gradingSystemList && gradingSystemList.size()!=0){
				loggingAspect.setUpdatedBy(gradingSystemList.get(0).getUpdatedBy());
			}			
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in editGradingSystem(JoinPoint joinPoint,List<GradingSystem> gradingSystemList,Object result) method in AcademicsLoggingNotificationAspect.java", e);
		}
	}*/
	
	
	/**
	 * this method is used for log into database for  access editExamMarks() method  in AcademicsService   and calling to AcademicsServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.academics.AcademicsService.editExamMarks(..))"+"&& args(examMarksList)",returning= "result")
	public void editExamMarks(JoinPoint joinPoint,List<ExamMarks> examMarksList,Object result) {
		try{
			String functionalityName="Set Exam Marks";
			String subjectName="Update Exam Marks";
			String moduleName="ACADEMICS";
			String loggingDesc="";
			if(null!=examMarksList && examMarksList.size()!=0){
				for(ExamMarks examMarks:examMarksList){
					loggingDesc="Update Exam Marks For "+examMarks.getStandard();
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
			if(null!=examMarksList && examMarksList.size()!=0){
				loggingAspect.setUpdatedBy(examMarksList.get(0).getUpdatedBy());
			}			
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in editExamMarks(JoinPoint joinPoint,List<ExamMarks> examMarksList,Object result) method in AcademicsLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access editStudentResult() method  in AcademicsService   and calling to AcademicsServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.academics.AcademicsService.editStudentResult(..))"+"&& args(studentResultList,insertUpdate)",returning= "result")
	public void editStudentResult(JoinPoint joinPoint,List<StudentResult> studentResultList, String insertUpdate,Object result) {
		try{
			String functionalityName="Set Exam Marks";
			String subjectName="Update Student Result";
			String moduleName="ACADEMICS";
			String loggingDesc="";
			if(null!=studentResultList && studentResultList.size()!=0){
				for(StudentResult studentResult:studentResultList){
					loggingDesc="Update Student Result For "+studentResult.getStandard()+" In "+studentResult.getRollNumber()+" "+insertUpdate;
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
			if(null!=studentResultList && studentResultList.size()!=0){
				loggingAspect.setUpdatedBy(studentResultList.get(0).getUpdatedBy());
			}			
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in editStudentResult(JoinPoint joinPoint,List<StudentResult> studentResultList, String insertUpdate,Object result) method in AcademicsLoggingNotificationAspect.java", e);
		}
	}
	
	
	
}
