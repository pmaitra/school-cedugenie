package com.qts.icam.utility.mailservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.qts.icam.controller.academics.AcademicsController;
import com.qts.icam.model.common.EmailDetails;



public class EmailReceiver {
	
	@Value("${emailAttachments.path}")
	private String emailAttachmentsPath;
	
	public static Logger logger = Logger.getLogger(EmailReceiver.class);


	public static final int SHOW_MESSAGES = 1;
	public static final int CLEAR_MESSAGES = 2;
	public static final int SHOW_AND_CLEAR = SHOW_MESSAGES + CLEAR_MESSAGES;
	protected String mailFor;
	protected Session session;
	String mailUsername = null;
	String mailHost = null;
	String mailStoreProtocol = null;
	String mailTransportProtocol = null;
	String mailPassword = null;
	
	
	
	public EmailReceiver()  {
	}
	
	public EmailReceiver(String mailUsername, String mailPassword,ServletContext servletContext)  {
		try{
			this.mailUsername=mailUsername;
			this.mailPassword = mailPassword;
			logger.info("WWWWWWWWWWWWW "+servletContext);
			/*String configurationPropertiesFile = servletContext.getInitParameter("icamfolderpath") + "conf/configuration.properties";
			Properties prop = new Properties();
			InputStream input = new FileInputStream(configurationPropertiesFile);
			prop.load(input);*/
			
			
			Properties prop = new Properties();
			//String propertyFile = "configuration.properties";
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("configuration.properties");
			prop.load(input);
			
			mailHost = prop.getProperty("mail.host");
			mailStoreProtocol = prop.getProperty("mail.store.protocol");
			mailTransportProtocol = prop.getProperty("mail.transport.protocol");
			mailFor = mailUsername + '@' + mailHost;
			Authenticator auth = null;
			auth = new SMTPAuthenticator();
			Properties props = null;
			props = new Properties();
			props.put("mail.user", mailUsername);
			props.put("mail.host", mailHost);    
			props.put("mail.debug", false); 	
			props.put("mail.store.protocol", mailStoreProtocol); 
			props.put("mail.transport.protocol", mailTransportProtocol);
			session = Session.getInstance(props,auth);
		}catch(FileNotFoundException e){
			logger.error(e);
		}catch(IOException e ){
			logger.error(e);
		}catch(Exception e ){
			logger.error(e);
		}
	}
	
	private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
           return new PasswordAuthentication(mailUsername, mailPassword);
        }
    }
	
	public List<EmailDetails> checkInbox(int mode) throws MessagingException, IOException  {
		List<EmailDetails> emailDetailsList = null;
		try{
			if (mode == 0) 
				emailDetailsList = null;
			Message[] msgs = null;
			boolean show = (mode & SHOW_MESSAGES) > 0;
			boolean clear = (mode & CLEAR_MESSAGES) > 0;
			String action =(show ? "Show" : "") + (show && clear ? "and " : "") + (clear ? "Clear" : "");
			logger.info(action + " INBOX for " + mailFor);
			Store store = null;
			store = session.getStore();
			store.connect();
			Folder root = store.getDefaultFolder();
			Folder inbox = null;
			inbox = root.getFolder("Inbox");
			inbox.open(Folder.READ_WRITE);
			msgs = inbox.getMessages();
			if (msgs.length == 0 && show){
				logger.info("No messages in inbox");
			}
			emailDetailsList = new ArrayList<EmailDetails>();
			for (int i = 0; i < msgs.length; i++){
				Message msg = (Message)msgs[i];
				if (show){
					logger.info("From: " + msg.getFrom()[0]); 
					logger.info("Subject: " + msg.getSubject()); 
					logger.info("Content: " + msg.getContent());
					logger.info("date: " + msg.getReceivedDate());
					String result = "";
					
					
					
					try{
						result = getTextFromMessage(msg);
					}catch(Exception e){
						e.printStackTrace();
					}
					
					System.out.println("Content===="+result);
					EmailDetails emailDetails = new EmailDetails();
					emailDetails.setEmailDetailsSender(msg.getFrom()[0].toString());
					emailDetails.setEmailDetailsSubject(msg.getSubject());
					emailDetails.setEmailDetailsDesc(result.trim());
					emailDetails.setTime(msg.getSentDate().toString());
					
					emailDetailsList.add(emailDetails);
		      	}
				if (clear){
					msg.setFlag(Flags.Flag.DELETED, true);
				}
			}
			inbox.close(true);
			store.close();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
		return emailDetailsList;
	}
	
	

	private String getTextFromMessage(Message msg) throws MessagingException,IOException {
		
		String result = "";
		if(msg.isMimeType("text/plain")){
			result = msg.getContent().toString();
		}else if (msg.isMimeType("multipart/*")){
			MimeMultipart mimeMultipart = (MimeMultipart)msg.getContent();
			result = getTextFromMimeMultiPath(mimeMultipart);
		}
		return result;
	}

	private String getTextFromMimeMultiPath(MimeMultipart mimeMultipart)throws MessagingException,IOException  {
		String result = "";
		int count = mimeMultipart.getCount();
		for(int i = 0;i < count ;i++){
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if(bodyPart.isMimeType("text/plain")){
				result = result +"\n"+bodyPart.getContent();
				break;
			}else if (bodyPart.getContent() instanceof MimeMultipart){
				
				result = result + getTextFromMimeMultiPath((MimeMultipart)bodyPart.getContent());
			}
		}
		return result;
	}

	
	//Added By Naimisha16092017
	public List<EmailDetails> readEmail(String emailUser,String emailPasword,ServletContext servletContext)  {
		List<EmailDetails> emailDetailsList=null;
		EmailReceiver emailReceiver = null;
		try {
			emailReceiver = new EmailReceiver(emailUser,emailPasword,servletContext);
			// LIST MESSAGES FOR email client
			emailDetailsList =  emailReceiver.checkInbox(EmailReceiver.SHOW_MESSAGES);
			// CLEAR MESSAGES FROM email client
			emailReceiver.checkInbox(EmailReceiver.CLEAR_MESSAGES);
		} catch (MessagingException | IOException e ) {
			e.printStackTrace();
			logger.error(e);
		}
		return emailDetailsList;
	}

	public List<EmailDetails> readEmail(String emailUser,String emailPasword, ServletContext servletContext, String path) {
		List<EmailDetails> emailDetailsList=null;
		EmailReceiver emailReceiver = null;
		try {
			emailReceiver = new EmailReceiver(emailUser,emailPasword,servletContext);
			// LIST MESSAGES FOR email client
			System.out.println("path=="+path);
			emailDetailsList =  emailReceiver.checkInbox(EmailReceiver.SHOW_MESSAGES,emailUser,path);
			// CLEAR MESSAGES FROM email client
			emailReceiver.checkInbox(EmailReceiver.CLEAR_MESSAGES);
		} catch (MessagingException | IOException e ) {
			e.printStackTrace();
			logger.error(e);
		}
		return emailDetailsList;
	}
	
	/**
	 * @param mode
	 * @param emailUser
	 * @param path
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public List<EmailDetails> checkInbox(int mode,String emailUser,String path) throws MessagingException, IOException  {
		List<EmailDetails> emailDetailsList = null;
		
		try{
			
			System.out.println("path2====="+path);
			if (mode == 0) 
				emailDetailsList = null;
			Message[] msgs = null;
			boolean show = (mode & SHOW_MESSAGES) > 0;
			boolean clear = (mode & CLEAR_MESSAGES) > 0;
			String action =(show ? "Show" : "") + (show && clear ? "and " : "") + (clear ? "Clear" : "");
			logger.info(action + " INBOX for " + mailFor);
			Store store = null;
			store = session.getStore();
			store.connect();
			Folder root = store.getDefaultFolder();
			Folder inbox = null;
			inbox = root.getFolder("Inbox");
			inbox.open(Folder.READ_WRITE);
			msgs = inbox.getMessages();
			if (msgs.length == 0 && show){
				logger.info("No messages in inbox");
			}
			emailDetailsList = new ArrayList<EmailDetails>();
			for (int i = 0; i < msgs.length; i++){
				Message msg = (Message)msgs[i];
				if (show){
					logger.info("From: " + msg.getFrom()[0]); 
					logger.info("Subject: " + msg.getSubject()); 
					logger.info("Content: " + msg.getContent());
					logger.info("date: " + msg.getReceivedDate());
					String result = "";
					
					
					/*MimeMultipart mimeMultipart = (MimeMultipart)msg.getContent();
					BodyPart bodyPart = mimeMultipart.getBodyPart(0);
					
					if(bodyPart.isMimeType("text/plain")){
						result = bodyPart.getContent().toString();
					}
					*//*try{
						result = getTextFromMessage(msg);
					}catch(Exception e){
						e.printStackTrace();
					}*/
					
					System.out.println("Content===="+result);
					EmailDetails emailDetails = new EmailDetails();
					emailDetails.setEmailDetailsSender(msg.getFrom()[0].toString());
					emailDetails.setEmailDetailsSubject(msg.getSubject());
				//	emailDetails.setEmailDetailsDesc(result.trim());
					emailDetails.setTime(msg.getSentDate().toString());
					 String contentType = msg.getContentType();
		             String messageContent = "";
		             String attachFiles = "";
		             
		             if (contentType.contains("multipart")) {
		                    // content may contain attachments
		                    Multipart multiPart = (Multipart) msg.getContent();
		                    int numberOfParts = multiPart.getCount();
		                    for (int partCount = 0; partCount < numberOfParts; partCount++) {
		                        MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
		                        if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
		                            // this part is attachment
		                            String fileName = part.getFileName();
		                            attachFiles += fileName + ", ";
		                            Date date =new Date();
		        				    long epoch = date.getTime();
		        				    String fullPath = path+"received/"+emailUser+"/"+epoch+"/";
		        				    emailDetails.setFilePath(fullPath);
		        				    File f = new File(fullPath);
		        				    boolean isCreated = f.mkdirs();
		        				  //  if(isCreated){
		        				    	part.saveFile(fullPath+ File.separator + fileName);
		        				   // }
		                            
		                        } else {
		                            // this part may be the message content
		                         //   messageContent = part.getDescription().toString();
		                            //Object content = msg.getContent();
		                           // System.out.println("content=="+content.toString());
		                        	 MimeMultipart mimeMultipart = (MimeMultipart) part.getContent();
		                        	 String emalBodyComtent = getTextFromMimeMultiPath(mimeMultipart);
		                        	 System.out.println("content=="+emalBodyComtent);
		                            emailDetails.setEmailDetailsDesc(emalBodyComtent.trim());
		                        }
		                    }
		                    System.out.println("attachFiles=="+attachFiles);
		                    List<String>fileList = new ArrayList<String>();
		                    if (attachFiles.length() > 1) {
		                        attachFiles = attachFiles.substring(0, attachFiles.length() - 2);
		                        
		                        fileList.add(attachFiles);
		                    }
		                    emailDetails.setFileList(fileList);
		                } else if (contentType.contains("text/plain")
		                        || contentType.contains("text/html")) {
		                    Object content = msg.getContent();
		                    if (content != null) {
		                        messageContent = content.toString();
		                        emailDetails.setEmailDetailsDesc(messageContent.trim());
		                    }
		                }
					emailDetailsList.add(emailDetails);
		      	}
				if (clear){
					msg.setFlag(Flags.Flag.DELETED, true);
				}
			}
			inbox.close(true);
			store.close();
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}
		return emailDetailsList;
	}
	
}
