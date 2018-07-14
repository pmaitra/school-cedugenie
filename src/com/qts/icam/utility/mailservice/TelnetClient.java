package com.qts.icam.utility.mailservice;

import com.jscape.inet.telnet.*;
import org.apache.log4j.Logger;

import java.io.*;

public class TelnetClient extends TelnetAdapter {

	private Telnet telnet = null;
	private OutputStream output = null;
	private static BufferedReader reader = null;
	private boolean connected = false;

	public static Logger logger = Logger.getLogger(TelnetClient.class);
	public TelnetClient(String hostname) throws IOException {

    
		// create new Telnet instance
		telnet = new Telnet(hostname);
		telnet.setPort(4555);

		// register this class as TelnetListener
		telnet.addTelnetListener(this);
		try{
			// establish Telnet connection
			telnet.connect();
		}catch(Exception ex){
			logger.error("Exception caught while connecting" +ex);
			ex.printStackTrace();
		}
		connected = true;
	}
  
  
	public String addUser(String userName, String password) throws Exception{
		logger.info("In String addUser(String userName, String password) in Telnetclient.java");
		String status = "";
		String input = null;
	  
		// get output stream
	    output = telnet.getOutputStream();

	    // sends all data entered at console to Telnet server
	    System.out.println("going to add user through telnet session");
	    String serverLogin = "ssr";
	    String serverPasswd = "ssr";
	    input = "adduser " + userName + " " + password;
	    
	    if (connected) {        
	        ((TelnetOutputStream) output).println(serverLogin);
	        //int command = telnet.TSC_CR;
	        ((TelnetOutputStream) output).println(serverPasswd);
	        //command = telnet.TSC_CR;
	        ((TelnetOutputStream) output).println(input);
	        
	        status = "This login id "+userName+"has registered successfully.";
	    } else {
	        //break;
	    	System.out.println("Could not connect to server for login id registration");
	    	logger.info("Could not connect to server for login id registration");
	    	status = "This login id "+userName+" registration failed.";
	    }
	    telnet.disconnect();
	    return status;
  	}
  
	public String deleteUser(String userName) throws Exception{
		logger.info("In String deleteUser(String userName) of TelnetClient.java");
		String status = "";
		String input = null;
	  
		// get output stream
	    output = telnet.getOutputStream();

	    // sends all data entered at console to Telnet server
	    String serverLogin = "ssr";
	    String serverPasswd = "ssr";
	    input = "deluser " + userName;
	    
	    if (connected) {        
	        ((TelnetOutputStream) output).println(serverLogin);
	        //int command = telnet.TSC_CR;
	        ((TelnetOutputStream) output).println(serverPasswd);
	        //command = telnet.TSC_CR;
	        ((TelnetOutputStream) output).println(input);
	        
	        status = "This login id "+userName+" has deleted successfully.";
	    } else {
	        //break;
	    	System.out.println("could not connect to server");
	    	logger.info("Could not connect to James Server at localhost");
	    	status = "This login id "+userName+" deletion failed.";
	    }
	    telnet.disconnect();  
	    return status;
	}
  
	/*public static void main(String[] args){
	  
	  try{
		  TelnetClient telnetClient = new TelnetClient("localhost");
		  
		  //String addStatus = telnetClient.addUser("saikat.das", "saikat.das");
		  //System.out.println(addStatus);
		  
		 String deleteStatus = telnetClient.deleteUser("saikat.das") ;
		 System.out.println(deleteStatus);
		  
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	  
  }*/
  
}
