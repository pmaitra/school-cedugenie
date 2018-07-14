package com.qts.icam.utility.messageservice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.qts.icam.service.impl.ticket.TicketServiceImpl;

public class MessageSender {
	
	private final static Logger logger = Logger.getLogger(MessageSender.class);

	
	@Autowired
	private ServletContext servletContext;

	/**
	 * Your authentication key
	 */
	//private String authkey = "72523A8sKvSLJwy54228b82";
	/**
	 * Multiple mobiles numbers seperated by comma
	 */
	//String mobiles = "9163664109";
	/**
	 * Sender ID,While using route4 sender id should be 6 characters long.
	 */
		//private String senderId = "NewUsr";
	/**
	 * Your message to send, Add URL endcoding here.
	 */
		//String message = "SMS message from Application (Quantalogi) through msg91 SMS broker.";
	/**
	 * define route
	 */
		//private String route="Route 4";
	/**
	 * Prepare Url
	 */
	private URLConnection myURLConnection=null;
	/**
	 * encoding message 
	 */
	private String encoded_message = null;
	
	URL myURL=null;
	BufferedReader reader=null;
	public void sendMessage(final String receiverMobileNo, final String messageDesc) {
	String smsProxyPassword = null;
	String smsSenderId = null;
	String smsHttpsProxyHost = null;
	String smsHttpsProxyPort = null;
	String smsHttpsProxySet = null;
	String smsProxy = null;
	String smsAuthkey = null;
	String smsRoute = null;
	String smsProxyUser = null;
	String smsURL = null;
	try {
		/*String configurationPropertiesFile = servletContext.getInitParameter("smsfolderpath") + "conf/configuration.properties";
		Properties prop = new Properties();
		InputStream input = new FileInputStream(configurationPropertiesFile);
		prop.load(input);*/
		
		Properties prop = new Properties();
		//String propertyFile = "configuration.properties";
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("configuration.properties");
		prop.load(input);
		
		
		smsProxyPassword = prop.getProperty("sms.proxy.password");
		smsSenderId = prop.getProperty("sms.senderId");
		smsHttpsProxyHost = prop.getProperty("sms.https.proxyHost");
		smsHttpsProxySet = prop.getProperty("sms.https.proxySet");
		smsProxy = prop.getProperty("sms.proxy");
		smsAuthkey = prop.getProperty("sms.authkey");
		smsRoute =  prop.getProperty("sms.route");
		smsProxyUser =  prop.getProperty("sms.proxy.user");
		smsURL =  prop.getProperty("sms.smsURL");
		smsHttpsProxyPort =  prop.getProperty("sms.https.proxyPort");
		try {
			encoded_message = URLEncoder.encode(messageDesc, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		/**
		 * Send SMS API
		 */
		String mainUrl=smsURL;
		/**
		 * Prepare parameter string 
		 */
		StringBuilder sbPostData= new StringBuilder(mainUrl);
		sbPostData.append("authkey="+smsAuthkey); 
		sbPostData.append("&mobiles="+receiverMobileNo);
		sbPostData.append("&message="+encoded_message);
		sbPostData.append("&route="+smsRoute);
		sbPostData.append("&sender="+smsSenderId);
		/**
		 * final string
		 */
		mainUrl = sbPostData.toString();
		if(smsProxy.equals("YES")){
		Authenticator.setDefault(new ProxyAuthenticator(smsProxyUser, smsProxyPassword));
		System.getProperties().put("https.proxySet", smsHttpsProxySet);
		System.getProperties().put("https.proxyHost", smsHttpsProxyHost);
		System.getProperties().put("https.proxyPort", smsHttpsProxyPort);
		}
		try {
		/**
		 * prepare connection
		 */
		myURL = new URL(mainUrl);
		myURLConnection = myURL.openConnection();
		/**
		 * System.setProperty("http.proxyHost", null);
		 */
		myURLConnection.connect();
		reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
		/**
		* reading response 
		*/
		String response;
		while ((response = reader.readLine()) != null) 
		/**
		 * print response 
		 */
		System.out.println(response);
		/**
		 * finally close connection
		 */
		reader.close();
		}catch (IOException e) { 
			logger.error(e);
		} 
	} catch (FileNotFoundException e2) {
		e2.printStackTrace();
	}catch (IOException e) {
		logger.error(e);
	}
	}
}

