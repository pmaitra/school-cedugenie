package com.qts.icam.aspect.logging.inventory;





import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Condemnation;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.service.common.CommonService;


@Aspect
@Component
public class InventoryLoggingNotificationAspect {

	@Autowired
	CommonService commonService;
	private final static Logger logger = Logger.getLogger(InventoryLoggingNotificationAspect.class);
	
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.inventory.InventoryService.updateInventorySession(..))"+"&& args(academicYear)",returning= "result")
	public void updateInventorySession(JoinPoint joinPoint,AcademicYear academicYear,Object result) {
		try{
			String functionalityName="Create Inventory Session";
			String subjectName="Inventoty Session Updated";
			String moduleName="INVENTORY";
			String loggingDesc="Updated  Inventoty Session for "+academicYear.getAcademicYearCode();
			
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(academicYear.getUpdatedBy());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in updateInventorySession(JoinPoint joinPoint,..........) method in InventoryLoggingNotificationAspect.java", e);
		}
	}
	
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.inventory.InventoryService.addCommodity(..))"+"&& args(commodity)",returning= "result")
	public void addCommodity(JoinPoint joinPoint,Commodity commodity,Object result) {
		try{
			String functionalityName="Add Commodity";
			String subjectName="new commodity added";
			String moduleName="INVENTORY";
			String loggingDesc="Added new commodity into stock for "+commodity.getCommodityName();
			
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(commodity.getUpdatedBy());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			if(result!=null && "Update Successful".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addCommodity(JoinPoint joinPoint,..........) method in InventoryLoggingNotificationAspect.java", e);
		}
	}
	
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.inventory.InventoryService.updateCommodityDetails(..))"+"&& args(commodity)",returning= "result")
	public void updateCommodityDetails(JoinPoint joinPoint,Commodity commodity,Object result) {
		try{
			String functionalityName="Add Commodity";
			String subjectName="Commodity Updated";
			String moduleName="INVENTORY";
			String loggingDesc="Commodity details updated for "+commodity.getCommodityName();
			
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(commodity.getUpdatedBy());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			if(result!=null && (Integer)result != 0){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in updateCommodityDetails(JoinPoint joinPoint,..........) method in InventoryLoggingNotificationAspect.java", e);
		}
	}
	
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.inventory.InventoryService.deleteCommodityDetails(..))"+"&& args(commodity)",returning= "result")
	public void deleteCommodityDetails(JoinPoint joinPoint,Commodity commodity,Object result) {
		try{
			String functionalityName="Add Commodity";
			String subjectName="Commodity Deleted";
			String moduleName="INVENTORY";
			String loggingDesc="Removed commodity from stock for "+commodity.getCommodityName();
			
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(commodity.getUpdatedBy());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			if(result!=null && (Integer)result != 0){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in deleteCommodityDetails(JoinPoint joinPoint,..........) method in InventoryLoggingNotificationAspect.java", e);
		}
	}
	

	
	
	
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.inventory.InventoryService.addDemandVoucher(..))"+"&& args(commodity)",returning= "result")
	public void addDemandVoucher(JoinPoint joinPoint,Commodity commodity,Object result) {
		try{
			String functionalityName="Create Inventory Purchase Order";
			String subjectName="Demand Voucher Generated";
			String moduleName="INVENTORY";
			String loggingDesc="New Demand Voucher Generated.....";
			
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(commodity.getUpdatedBy());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			if(result!=null && (Integer)result != 0){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addDemandVoucher(JoinPoint joinPoint,..........) method in InventoryLoggingNotificationAspect.java", e);
		}
	}
	

	

	
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.inventory.InventoryService.submitCondemnation(..))"+"&& args(condemnation)",returning= "result")
	public void submitCondemnation(JoinPoint joinPoint,Condemnation condemnation,Object result) {
		try{
			String functionalityName="Add Commodity";
			String subjectName="Submitted Condemnation";
			String moduleName="INVENTORY";
			String loggingDesc="Submitted Condemnation.....   ";
			LoggingAspect loggingAspect = new LoggingAspect();
			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
			loggingAspect.setMethodName(joinPoint.getSignature().getName());
			loggingAspect.setLoggingDesc(loggingDesc);
			loggingAspect.setLoggingSubject(subjectName);
			loggingAspect.setUpdatedBy(condemnation.getUpdatedBy());
			Module module = new Module();
			module.setModuleName(moduleName);
			loggingAspect.setModule(module);
			Functionality functionality=new Functionality();
			functionality.setFunctionalityName(functionalityName);
			loggingAspect.setFunctionality(functionality);
			if(result!=null && "Success".equals(result.toString())){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in submitCondemnation(JoinPoint joinPoint,..........) method in InventoryLoggingNotificationAspect.java", e);
		}
	}
	

	
	

	


	
	

	

	
	
}
