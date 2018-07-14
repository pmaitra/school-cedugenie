package com.qts.icam.listner;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DefaultPropertiesPersister;

import com.qts.icam.utility.Utility;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;


public class PropertyPersister extends DefaultPropertiesPersister
{
	
	private static String location;

	public static String getLocation() {
		return location;
	}

	public static void setLocation(String location) {
		PropertyPersister.location = location;
	}
	
	 public void load(Properties props, InputStream is) throws IOException
	    {
	    	//ServletContext servletContext = get();
	    	//String path = "classpath:configuration.properties";
	    	//is = new FileInputStream("classpath:configuration.properties");
	       //props.load(is); 
	    	EncryptDecrypt encryptDecrypt=new EncryptDecrypt();
	        
	        Map<String, String> propertiesField = new HashMap<String, String>();
	        
	       /* propertiesField.put("jdbc.username", props.getProperty("jdbc.username"));
	        propertiesField.put("jdbc.password", props.getProperty("jdbc.password"));
	        propertiesField.put("sms.proxy.user", props.getProperty("sms.proxy.user"));
	        propertiesField.put("sms.proxy.password", props.getProperty("sms.proxy.password"));
	        propertiesField.put("mail.username", props.getProperty("mail.username"));
	        propertiesField.put("mail.password", props.getProperty("mail.password"));
	        propertiesField.put("directoryServer.password", props.getProperty("directoryServer.password"));*/
	        
	        propertiesField.put("jdbc.username","jB99aJucQZGvo9AMt74jbQ" );
	        propertiesField.put("jdbc.password","jB99aJucQZGvo9AMt74jbQ");
	        propertiesField.put("sms.proxy.user", "nz2uLUqxVE6VCk8GRSvFHw");
	        propertiesField.put("sms.proxy.password", "c7/mtwCwz/HeFIQxozxdVA");
	        propertiesField.put("mail.username", "UMkgRiGe5kS8qgVUC16dJQ");
	        propertiesField.put("mail.password","UMkgRiGe5kS8qgVUC16dJQ");
	        propertiesField.put("directoryServer.password", "1m1ADBRxwzb6pjcHO95n+A");
	        
	       try {
	        	Iterator itr = propertiesField.entrySet().iterator();
	        	while(itr.hasNext()){
	        		Map.Entry mapEntry = (Map.Entry) itr.next();
	        		String encryptedText = (String) mapEntry.getValue();
	     		    String decryptedValue = encryptDecrypt.decrypt(encryptedText);
	     		    props.setProperty((String) mapEntry.getKey(), decryptedValue);
	        	}
	        	
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }

    	
    
   
}