package com.qts.icam.utility.mailservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.qts.icam.model.common.EmailDetails;


public class EmailSender {

	@Autowired
	private VelocityEngine velocityEngine;
	@Autowired
    private JavaMailSender mailSender;
	
	 public void sendMail(final String from, final String to,EmailDetails emailDetails, final String emailSubject, final String emailBody) {
		 try{	
	    	
	    	MimeMessage message = mailSender.createMimeMessage();
	    	MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
	    	 Map<String, Object> model = new HashMap<String, Object>();
	    	 model.put("emailDetails", emailDetails);
	    	 messageHelper.setTo(to);
	    	 messageHelper.setFrom(from);
	    	 messageHelper.setSubject(emailSubject);
	    	 messageHelper.setText(emailBody);
	    	 /*if("manualmail".equals(emailDetails.getStatus())){
	    		 messageHelper.setSubject(emailDetails.getEmailDetailsSubject());
	    	 }
	    	 if("automail".equals(emailDetails.getStatus())){
	    		 messageHelper.setSubject(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,emailSubject ,model)); 
	    	 }*/
	    	// String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,emailBody, model);
	    	// message.setText(text);
            /* FileSystemResource file = new FileSystemResource("C:/MYTEST/ABC.txt");
             messageHelper.addAttachment(file.getFilename(), file);*/
	    	 mailSender.send(message);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
      }

	 public String sendEMail(EmailDetails emailDetails) {
		String status = "success";
		 try{	
	    	
	    	MimeMessage message = mailSender.createMimeMessage();
	    	MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
	    	// Map<String, Object> model = new HashMap<String, Object>();
	    	// model.put("emailDetails", emailDetails);
	    	System.out.println(emailDetails.getEmailDetailsReceiver());
	    	System.out.println(emailDetails.getEmailDetailsSender());
	    	System.out.println(emailDetails.getEmailDetailsSubject());
	    	System.out.println(emailDetails.getEmailDetailsDesc());
	    	 messageHelper.setTo(emailDetails.getEmailDetailsReceiver());
	    	 messageHelper.setFrom(emailDetails.getEmailDetailsSender());
	    	 messageHelper.setSubject(emailDetails.getEmailDetailsSubject());
	    	 messageHelper.setText(emailDetails.getEmailDetailsDesc());
	    	 
	    	 //List<CommonsMultipartFile> emailRelatedFile = emailDetails.getEmailRelatedFile();
	    	 if( null != emailDetails.getFileList() && emailDetails.getFileList().size() !=0){
	    		 for(String  fileString : emailDetails.getFileList()){
		    		 String wholeFilePath = emailDetails.getFilePath()+"/"+ fileString;
		    		 System.out.println("wholeFilePath==="+wholeFilePath);
		    		  FileSystemResource file = new FileSystemResource(wholeFilePath);
		             messageHelper.addAttachment(fileString, file);
		    	 }
	    	 }
	    	 
            // FileSystemResource file = new FileSystemResource("C:/MYTEST/ABC.txt");
            // messageHelper.addAttachment(file.getFilename(), file);
	    	 mailSender.send(message);
    	}catch(Exception e){
    		status = "fail";
    		e.printStackTrace();
    	}
    	return status;
      }
}
