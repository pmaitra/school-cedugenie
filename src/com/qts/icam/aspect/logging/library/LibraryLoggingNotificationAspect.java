package com.qts.icam.aspect.logging.library;



import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qts.icam.model.administrator.Functionality;
import com.qts.icam.model.administrator.Module;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.library.Book;
import com.qts.icam.model.library.BookId;
import com.qts.icam.service.common.CommonService;

@Aspect
@Component
public class LibraryLoggingNotificationAspect {

	@Autowired
	CommonService commonService;
	private final static Logger logger = Logger.getLogger(LibraryLoggingNotificationAspect.class);
	
	/**
	 * this method is used for log into database for  access addNewBook() method  in LibraryService   and calling to CommonServiceImpl.java for insert logging information.
	 */
	@AfterReturning(pointcut ="execution(* com.qts.icam.service.library.LibraryService.addNewBook(..))"+"&& args(book)",returning= "result")
	public void addNewBook(JoinPoint joinPoint,Book book,Object result) {
		try{
			String functionalityName="Book Cataloging";
			String subjectName="Inserted New Book";
			String moduleName="LIBRARY";
			String loggingDesc="Insert new "+book.getTotalNumberOfBookCopies() +" copies "+book.getBookName()+" "+book.getBookType();
			
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
			loggingAspect.setUpdatedBy(book.getUpdatedBy());
			commonService.createLoggingByAspect(loggingAspect);
			if(result!=null){
				commonService.createNotificationByAspect(loggingAspect);
			}
		}catch(Exception e){
			logger.error("Exception in addNewBook(JoinPoint joinPoint) method in LibraryLoggingNotificationAspect.java", e);
		}
	}
	
	/**
	 * this method is used for log into database for  access addNewBook() method  in LibraryService   and calling to CommonServiceImpl.java for insert logging information.
	 */
//	@AfterReturning(pointcut ="execution(* qls.sms.service.library.LibraryService.updateBook(..))"+"&& args(book,updatedBy)",returning= "result")
//	public void updateBook(JoinPoint joinPoint,Book book,String updatedBy,Object result) {
//		try{
//			String functionalityName="Book Cataloging";
//			String subjectName="Updated Book";
//			String moduleName="LIBRARY";
//			String loggingDesc="Updated  "+book.getBookType() + " Details of : "+book.getBookName()+"("+book.getBookCode()+")";
//			String recId = commonService.getRecIdForBook(book.getBookCode());
//
//			LoggingAspect loggingAspect = new LoggingAspect();
//			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
//			loggingAspect.setMethodName(joinPoint.getSignature().getName());
//			Module module = new Module();
//			module.setModuleName(moduleName);
//			loggingAspect.setModule(module);
//			Functionality functionality=new Functionality();
//			functionality.setFunctionalityName(functionalityName);
//			loggingAspect.setFunctionality(functionality);
//			loggingAspect.setLoggingDesc(loggingDesc);
//			loggingAspect.setLoggingSubject(subjectName);
//			loggingAspect.setUpdatedBy(book.getUpdatedBy());
//			loggingAspect.setRecId(recId);
//			commonService.createLoggingByAspect(loggingAspect);
//			if(result!=null){
//				commonService.createNotificationByAspect(loggingAspect);
//			}
//		}catch(Exception e){
//			logger.error("Exception in updateBook(JoinPoint joinPoint) method in LibraryLoggingNotificationAspect.java", e);
//		}
//	}
	
	/**
	 * this method is used for log into database for  access removeBookIds() method  in LibraryService   and calling to CommonServiceImpl.java for insert logging information.
	 */
//	@AfterReturning(pointcut ="execution(* qls.sms.service.library.LibraryService.removeBookIds(..))"+"&& args(bookIdList)",returning= "result")
//	public void removeBookIds(JoinPoint joinPoint,List<BookId> bookIdList,Object result) {
//		try{
//			String functionalityName="Book Retirement";
//			String subjectName="Retire Book";
//			String loggingDesc="";
//			String moduleName="LIBRARY";
//			if(bookIdList!=null && bookIdList.size()!=0){
//			loggingDesc="Retire "+bookIdList.size() +" copies of "+bookIdList.get(0).getNewBookEntryDate()+"("+bookIdList.get(0).getBookCode()+")from stock ";
//			String recId = commonService.getRecIdForBook(bookIdList.get(0).getBookCode());
//			LoggingAspect loggingAspect = new LoggingAspect();
//			loggingAspect.setMethodSignatureName(joinPoint.getSignature().toString());
//			loggingAspect.setMethodName(joinPoint.getSignature().getName());
//			Module module = new Module();
//			module.setModuleName(moduleName);
//			loggingAspect.setModule(module);
//			Functionality functionality=new Functionality();
//			functionality.setFunctionalityName(functionalityName);
//			loggingAspect.setFunctionality(functionality);
//			loggingAspect.setLoggingDesc(loggingDesc);
//			loggingAspect.setLoggingSubject(subjectName);
//			loggingAspect.setUpdatedBy(bookIdList.get(0).getUpdatedBy());
//			loggingAspect.setRecId(recId);
//			commonService.createLoggingByAspect(loggingAspect);
//				if(result!=null){
//					commonService.createNotificationByAspect(loggingAspect);
//				}
//			}
//		}catch(Exception e){
//			logger.error("Exception in removeBookIds(JoinPoint joinPoint,List<String> bookIdList,Book book,Object result) method in LibraryLoggingNotificationAspect.java", e);
//		}
//	}
	
	
	
	
	
	
}
