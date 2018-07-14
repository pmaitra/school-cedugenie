package com.qts.icam.aspect.logging.ticket;




import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.ticket.ServiceType;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.service.common.CommonService;


@Aspect
@Component
public class TicketLoggingNotificationAspect {

	@Autowired
	CommonService commonService;
	private final static Logger logger = Logger.getLogger(TicketLoggingNotificationAspect.class);
	
	
	/**
	 * this method is used for log into database for  access saveTicketService() method  in TicketService   and calling to TicketServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.ticket.TicketService.saveTicketService(..))"+"&& args(serviceType)",returning= "result")
	public void saveTicketService(JoinPoint joinPoint,ServiceType serviceType,Object result) {
		try{
			String functionalityName="Generate Ticket";
			String subjectName="Create Ticket Service";
			String moduleName="TICKETING";
			String loggingDesc="Create New Ticket Service "+serviceType.getTicketServiceName();
			
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
			loggingAspect.setUpdatedBy(serviceType.getUpdatedBy());
			if(result!=null){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in saveTicketService(JoinPoint joinPoint,ServiceType serviceType,Object result) method in TicketLoggingNotificationAspect.java", e);
		}
	}
	
	
	
	
	/**
	 * this method is used for log into database for  access updateTicketService() method  in TicketService   and calling to TicketServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.ticket.TicketService.updateTicketService(..))"+"&& args(serviceType)",returning= "result")
	public void updateTicketService(JoinPoint joinPoint,ServiceType serviceType,Object result) {
		try{
			String functionalityName="Generate Ticket";
			String subjectName="Update Ticket Service";
			String moduleName="TICKETING";
			String loggingDesc="Update Ticket Service "+serviceType.getTicketServiceName();
			
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
			loggingAspect.setUpdatedBy(serviceType.getUpdatedBy());
			if(result!=null){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in updateTicketService(JoinPoint joinPoint,ServiceType serviceType,Object result) method in TicketLoggingNotificationAspect.java", e);
		}
	}
	

	/**
	 * this method is used for log into database for  access generateTicket() method  in TicketService   and calling to TicketServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.ticket.TicketService.generateTicket(..))"+"&& args(ticket)",returning= "result")
	public void generateTicket(JoinPoint joinPoint,Ticket ticket,Object result) {
		try{
			String functionalityName="Generate Ticket";
			String subjectName="Create New Ticket";
			String moduleName="TICKETING";
			String loggingDesc="Create New Ticket "+ticket.getTicketDesc()+ " "+ticket.getReportedBy()+ " "+ticket.getAffectedUser();			
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
			loggingAspect.setUpdatedBy(ticket.getUpdatedBy());
			if(result!=null){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in generateTicket(JoinPoint joinPoint,Ticket ticket,Object result) method in TicketLoggingNotificationAspect.java", e);
		}
	}
	
	

	/**
	 * this method is used for log into database for  access updateTicket() method  in TicketService   and calling to TicketServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(*  com.qts.icam.service.ticket.TicketService.updateTicket(..))"+"&& args(ticket)",returning= "result")
	public void updateTicket(JoinPoint joinPoint,Ticket ticket,Object result) {
		try{
			String functionalityName="Generate Ticket";
			String subjectName="Update Ticket";
			String moduleName="TICKETING";
			String loggingDesc="Update Ticket "+ticket.getAffectedUser()+ " "+ticket.getStatus();			
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
			loggingAspect.setUpdatedBy(ticket.getUpdatedBy());
			if(result!=null){
				commonService.createLoggingByAspect(loggingAspect);
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in updateTicket(JoinPoint joinPoint,Ticket ticket,Object result) method in TicketLoggingNotificationAspect.java", e);
		}
	}
}
