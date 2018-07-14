package com.qts.icam.aspect.logging.admission;



import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.admission.AdmissionForm;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.model.common.Candidate;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.service.common.CommonService;


@Aspect
@Component
public class AdmissionLoggingNotificationAspect {

	@Autowired
	CommonService commonService;
	private final static Logger logger = Logger.getLogger(AdmissionLoggingNotificationAspect.class);
	
	//********************************OPTIONAL CODE***********************************************//
	//******* @Pointcut("execution(* qls.sms.service.admission.AdmissionService.*(..))")*********// 
	//******* public void classAdmissionService(){} ********************************************//
	//******* @Pointcut("execution(* getAdmissionDriveList(..))") *****************************//
	//******* private void getAdmissionDriveList() {}*****************************************//
	
	String interfaceName = "com.qts.icam.service.admission.AdmissionService";
	
	/**
	 * this method is used for log into database for  access addAdmissionForm(Candidate candidate) method  in AdmissionService   and calling to CommonServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.admission.AdmissionService.addAdmissionForm(..))"+"&& args(candidate)",returning= "result")
	public void addAdmissionForm(JoinPoint joinPoint,Candidate candidate,Object result) {
		try{
			String moduleName="ADMISSION";
			String subjectName="Created New Admission Form";
			String functionalityName="Admission Form";
			String loggingDesc="Submitted new admission form for class "+candidate.getStandard().getStandardName()+", Academic Year:  "+candidate.getAcademicYear().getAcademicYearName()+".";
			if(result!=null){
				loggingDesc=loggingDesc+" Auto generated form id is: "+result;
			}
			
			if(candidate!=null){
				//loggingDesc=loggingDesc+admissionForm.getAdmissionDriveName();
			}
			//String recId = commonService.getRecIdForAdmissionDrive(admissionForm.getAdmissionDriveCode());
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
			//loggingAspect.setRecId(recId);
			//System.out.println("SSSSSSSSSSS: "+result);
			if(result!=null){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in addAdmissionForm(JoinPoint joinPoint,Candidate candidate,Object result) method in AdmissionLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * this method is used for log into database for  access addVenue(Venue venue) method  in AdmissionService   and calling to CommonServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.admission.AdmissionService.addVenue(..))"+"&& args(venue)",returning= "result")
	public void addVenue(JoinPoint joinPoint,Venue venue,Object result) {
		try{
			String moduleName="ADMISSION";
			String subjectName="New Exam Venue Created";
			String functionalityName="Create Exam Venue";
			String loggingDesc="Created  new exam venue "+venue.getVenueName()+"by "+venue.getUpdatedBy()+".";
			if(venue!=null){
				//loggingDesc=loggingDesc+admissionForm.getAdmissionDriveName();
			}
			String recId = commonService.getRecIdForVenue(venue.getVenueName());
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
			loggingAspect.setUpdatedBy(venue.getUpdatedBy());
			loggingAspect.setRecId(recId);
			//System.out.println("SSSSSSSSSSS: "+result);
			if(result!=null && (Integer)result != 0){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in addVenue(JoinPoint joinPoint,Candidate candidate,Object result) method in AdmissionLoggingNotificationAspect.java", e);e.printStackTrace();
		}
	}

	/**
	 * this method is used for log into database for  access deleteExamVenueDetails(Venue venue) method  in AdmissionService   and calling to CommonServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.admission.AdmissionService.deleteExamVenueDetails(..))"+"&& args(venue)",returning= "result")
	public void deleteExamVenueDetails(JoinPoint joinPoint,Venue venue,Object result) {
		try{
			String moduleName="ADMISSION";
			String subjectName="Exam Venue Deleted";
			String functionalityName="Create Exam Venue";
			String loggingDesc="Deleted  exam venue "+venue.getVenueName()+"by "+venue.getUpdatedBy()+".";
			if(venue!=null){
				//loggingDesc=loggingDesc+admissionForm.getAdmissionDriveName();
			}
			String recId = commonService.getRecIdForVenue(venue.getVenueName());
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
			loggingAspect.setUpdatedBy(venue.getUpdatedBy());
			loggingAspect.setRecId(recId);
			//System.out.println("SSSSSSSSSSS: "+result);
			if(result!=null && (Integer)result != 0){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in deleteExamVenueDetails(JoinPoint joinPoint,Candidate candidate,Object result) method in AdmissionLoggingNotificationAspect.java", e);e.printStackTrace();
		}
	}
	
	
	/**
	 * this method is used for log into database for  access updateExamVenueDetails(Venue venue) method  in AdmissionService   and calling to CommonServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.admission.AdmissionService.updateExamVenueDetails(..))"+"&& args(venue)",returning= "result")
	public void updateExamVenueDetails(JoinPoint joinPoint,Venue venue,Object result) {
		try{
			String moduleName="ADMISSION";
			String subjectName="Updated Exam Venue";
			String functionalityName="Create Exam Venue";
			String loggingDesc="Updated  exam venue "+venue.getVenueName()+" by "+venue.getUpdatedBy()+".";
			if(venue!=null){
				//loggingDesc=loggingDesc+admissionForm.getAdmissionDriveName();
			}
			String recId = commonService.getRecIdForVenue(venue.getVenueName());
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
			loggingAspect.setUpdatedBy(venue.getUpdatedBy());
			loggingAspect.setRecId(recId);
			//System.out.println("SSSSSSSSSSS: "+result);
			if(result!=null && (Integer)result != 0){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in updateExamVenueDetails(JoinPoint joinPoint,Candidate candidate,Object result) method in AdmissionLoggingNotificationAspect.java", e);e.printStackTrace();
		}
	}
	
	
	/**
	 * this method is used for log into database for  access addSeatAllotment(Venue venue) method  in AdmissionService   and calling to CommonServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.admission.AdmissionService.addSeatAllotment(..))"+"&& args(venue)",returning= "result")
	public void addSeatAllotment(JoinPoint joinPoint,Venue venue,Object result) {
		try{
			String moduleName="ADMISSION";
			String subjectName="Seat Alloted For Exam Venue";
			String functionalityName="Create Exam Venue";
			String loggingDesc="Alloted Seat for  exam venue by "+venue.getUpdatedBy()+".";
			if(venue!=null){
				//loggingDesc=loggingDesc+admissionForm.getAdmissionDriveName();
			}
			String recId = commonService.getRecIdForVenue(venue.getVenueName());
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
			loggingAspect.setUpdatedBy(venue.getUpdatedBy());
			loggingAspect.setRecId(recId);
			//System.out.println("SSSSSSSSSSS: "+result);
			if(result!=null && (Integer)result != 0){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in addSeatAllotment(JoinPoint joinPoint,Candidate candidate,Object result) method in AdmissionLoggingNotificationAspect.java", e);e.printStackTrace();
		}
	}
	
	/**
	 * this method is used for log into database for  access addDispatchAdmitCard(AdmissionForm admissionForm, HttpServletResponse response) method  in AdmissionService   and calling to CommonServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.admission.AdmissionService.addDispatchAdmitCard(..))"+"&& args(admissionForm,response)",returning= "result")
	public void addDispatchAdmitCard(JoinPoint joinPoint,AdmissionForm admissionForm, HttpServletResponse response,Object result) {
		try{
			String moduleName="ADMISSION";
			String subjectName="Dispatch Admit Card";
			String functionalityName="Dispatch Admit Card";
			String loggingDesc="Admit Card dispatched by  "+admissionForm.getUpdatedBy()+" for Examination center "+admissionForm.getVenue().getVenueName()+". Admission Exam date set up on "+admissionForm.getExaminationDate()+" at "+admissionForm.getExaminationTime();
			
			String recId = commonService.getRecIdForVenue(admissionForm.getVenue().getVenueName());
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
			loggingAspect.setUpdatedBy(admissionForm.getUpdatedBy());
			loggingAspect.setRecId(recId);
			if(result!=null && (Integer)result != 0){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in addSeatAllotment(JoinPoint joinPoint,AdmissionForm admissionForm, HttpServletResponse response,Object result) method in AdmissionLoggingNotificationAspect.java", e);e.printStackTrace();
		}
	}
	
	
	/**
	 * this method is used for log into database for  access addMeritList(AdmissionForm admissionForm) method  in AdmissionService   and calling to CommonServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.admission.AdmissionService.addMeritList(..))"+"&& args(admissionForm)",returning= "result")
	public void addMeritList(JoinPoint joinPoint,AdmissionForm admissionForm,Object result) {
		try{
			String moduleName="ADMISSION";
			String subjectName="Merit List Generated";
			String functionalityName="Generate Merit List";
			String loggingDesc=admissionForm.getMeritListType().getMeritListTypeCode()+" merit list generated by "+admissionForm.getUpdatedBy() +" for  class "+admissionForm.getStandard().getStandardCode();
			
			//String recId = commonService.getRecIdForVenue(venue.getVenueName());
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
			loggingAspect.setUpdatedBy(admissionForm.getUpdatedBy());
			//loggingAspect.setRecId(recId);
			//System.out.println("SSSSSSSSSSS: "+result);
			if(result!=null && (Integer)result != 0){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in addMeritList(JoinPoint joinPoint,AdmissionForm admissionForm,Object result) method in AdmissionLoggingNotificationAspect.java", e);e.printStackTrace();
		}
	}
	
	
	/**
	 * this method is used for log into database for  access updateFormStatus(AdmissionForm admissionForm) method  in AdmissionService   and calling to CommonServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.admission.AdmissionService.updateFormStatus(..))"+"&& args(admissionForm)",returning= "result")
	public void updateFormStatus(JoinPoint joinPoint,AdmissionForm admissionForm,Object result) {
		try{
			String moduleName="ADMISSION";
			String subjectName="Admission Form Status Updated";
			String functionalityName="Admission Form ";
			String loggingDesc="Admission Form Status updated by "+admissionForm.getUpdatedBy()+" of Class  "+admissionForm.getStandard().getStandardName();
			
			//String recId = commonService.getRecIdForVenue(venue.getVenueName());
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
			loggingAspect.setUpdatedBy(admissionForm.getUpdatedBy());
			//loggingAspect.setRecId(recId);
			if(result!=null && (Integer)result != 0){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in updateFormStatus(JoinPoint joinPoint,AdmissionForm admissionForm,Object result) method in AdmissionLoggingNotificationAspect.java", e);e.printStackTrace();
		}
	}
	
	
	
}
