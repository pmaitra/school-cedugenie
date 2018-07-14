package com.qts.icam.aspect.logging.hostel;




import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.administrator.Role;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.hostel.Hostel;
import com.qts.icam.model.hostel.HostelFacility;
import com.qts.icam.service.common.CommonService;

@Aspect
@Component
public class HostelLoggingNotificationAspect {

	@Autowired
	CommonService commonService;
	private final static Logger logger = Logger.getLogger(HostelLoggingNotificationAspect.class);
	
	
	
	/**
	 * this method is used for log into database for  access addHostel() method  in HostelService   and calling to HostelServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.hostel.HostelService.addHostel(..))"+"&& args(hostel)",returning= "result")
	public void addHostel(JoinPoint joinPoint,Hostel hostel,Object result) {
		try{
			String functionalityName="Create Hostel";
			String subjectName="Create New Hostel";
			String moduleName="HOSTEL";
			String loggingDesc="Create New Hostel"+hostel.getHostelName();
			
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
			loggingAspect.setUpdatedBy(hostel.getUpdatedBy());
			if(result!=null && "Insert Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addHostel(JoinPoint joinPoint,Hostel hostel,Object result) method in HostelLoggingNotificationAspect.java", e);
		}
	}
	

	
	/**
	 * this method is used for log into database for  access editHostel() method  in HostelService   and calling to HostelServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.hostel.HostelService.editHostel(..))"+"&& args(hostelList)",returning= "result")
	public void editHostel(JoinPoint joinPoint,List<Hostel> hostelList,Object result) {
		try{
			String functionalityName="Create Hostel";
			String subjectName="EditHostel";
			String moduleName="HOSTEL";
			String loggingDesc="";
			if(null!=hostelList && hostelList.size()!=0){
				for(Hostel hostel :hostelList){
					loggingDesc="Edit Hostel "+hostel.getHostelName();
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
			if(null!=hostelList && hostelList.size()!=0){
				loggingAspect.setUpdatedBy(hostelList.get(0).getUpdatedBy());
			}
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in editHostel(JoinPoint joinPoint,List<Hostel> hostelList,Object result) method in HostelLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access addhostelFacility() method  in HostelService   and calling to HostelServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.hostel.HostelService.addhostelFacility(..))"+"&& args(hostelFacility)",returning= "result")
	public void addhostelFacility(JoinPoint joinPoint,HostelFacility hostelFacility,Object result) {
		try{
			String functionalityName="Create Hostel";
			String subjectName="Create New Hostel Facility";
			String moduleName="HOSTEL";
			String loggingDesc="Create New Hostel Facility "+hostelFacility.getHostelFacilityName();
			
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
			loggingAspect.setUpdatedBy(hostelFacility.getUpdatedBy());
			if(result!=null && "Insert Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addhostelFacility(JoinPoint joinPoint,HostelFacility hostelFacility,Object result) method in HostelLoggingNotificationAspect.java", e);
		}
	}
	
	
	/**
	 * this method is used for log into database for  access editHostelFacility() method  in HostelService   and calling to HostelServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.hostel.HostelService.editHostelFacility(..))"+"&& args(hostelFacility)",returning= "result")
	public void editHostelFacility(JoinPoint joinPoint,HostelFacility hostelFacility,Object result) {
		try{
			String functionalityName="Create Hostel";
			String subjectName="Edit Hostel Facility";
			String moduleName="HOSTEL";
			String loggingDesc="Edit Hostel Facility "+hostelFacility.getHostelFacilityName();
			
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
			loggingAspect.setUpdatedBy(hostelFacility.getUpdatedBy());
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in editHostelFacility(JoinPoint joinPoint,HostelFacility hostelFacility,Object result) method in HostelLoggingNotificationAspect.java", e);
		}
	}
	
}
