package com.qts.icam.utility.customexception;

import org.apache.log4j.Logger;

import com.qts.icam.service.impl.ticket.TicketServiceImpl;


/**
 * 
 * @author sovan.mukherjee
 * 
 * this class handle different custom exception
 *
 */
public class CustomException extends Exception {
	private final static Logger logger = Logger.getLogger(CustomException.class);

	private static final long serialVersionUID = 4556810951327566006L;
	private String errorCode;
	
	public CustomException(String message, String errorCode){
		super(message);
		this.errorCode = errorCode;
	}
	public String getErrorCode(){
		return errorCode;
	}
	
	public static void exceptionHandler(Exception e) throws CustomException{
		logger.error(e);
		System.out.println(e);
		if(e instanceof NullPointerException){
			System.out.println(e);
			throw new CustomException("DATA NOT FOUND", "DATA NOT FOUND");
		}
	}
}
