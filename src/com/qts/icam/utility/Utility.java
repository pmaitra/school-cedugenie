package com.qts.icam.utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.naming.Name;
import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.axiom.attachments.ByteArrayDataSource;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
//import com.jscape.inet.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.LdapInfo;
import com.qts.icam.model.common.SMS;
import com.qts.icam.model.common.SMSData;
import com.qts.icam.model.common.SchoolNote;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.model.event.SchoolEvent;
import com.qts.icam.model.login.LoginForm;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.Comparator;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
/**
 * 
 * @author parmanand.singh
 * 
 */

public class Utility {
	public static Logger logger = Logger.getLogger(Utility.class);
	
	@Autowired
	private LdapTemplate ldapTemplate;
	
	public Map<String, String> readXML(String xmlPath,String chartID) {		 
		Map<String, String> mMap = new HashMap<String, String>();		
		try {	 
		File fXmlFile = new File(xmlPath);		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();		
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();		
		org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);		
		doc.getDocumentElement().normalize();
		
		NodeList pieList = doc.getElementsByTagName("chart");			
		for (int i = 0; i < pieList.getLength(); i++) {	 
			Node nNode = pieList.item(i);				
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {	 
				Element eElement = (Element) nNode;					
				if((eElement.getAttribute("id")).equalsIgnoreCase(chartID)){
					logger.info(eElement.getElementsByTagName("description").item(0).getTextContent());
					mMap.put("description",eElement.getElementsByTagName("description").item(0).getTextContent());				
					}
				}			
			}
	    } 
	    catch (Exception e) {
		logger.error(e);
	    }
	    return mMap;
	  }
	
	

	/*Calculator*/

	public static double evaluate(String expression)
	{
		logger.info("@@ expression : "+expression);
		
	    char[] tokens = expression.toCharArray();
	
	     // Stack for numbers: 'values'
	    Stack<Double> values = new Stack<Double>();
	
	    // Stack for Operators: 'ops'
	    Stack<Character> ops = new Stack<Character>();
	
	    for (int i = 0; i < tokens.length; i++)
	    {
	    	logger.info("@@ Token "+(i+1)+" : "+tokens[i]);
	         // Current token is a whitespace, skip it
	        if (tokens[i] == ' ')
	            continue;
	
	        // Current token is a number, push it to stack for numbers		// && tokens[i] == '.'
	        if (tokens[i] >= '0' && tokens[i] <= '9')
	        {
	            StringBuffer sbuf = new StringBuffer();
	            // There may be more than one digits in number
	            while (i < tokens.length){
	            	if(tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.'){  
	                    sbuf.append(tokens[i]);
	            	}
	            	if (tokens[i] == ' ' || tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/' || tokens[i] == '%' || tokens[i] == ')'){
	            		i--;
	            		break;
	            	}
	            	i++;
	            }
	            values.push(Double.parseDouble(sbuf.toString()));
	        }
	
	        // Current token is an opening brace, push it to 'ops'
	        else if (tokens[i] == '(')
	        {
	            ops.push(tokens[i]);
	        }
	
	        // Closing brace encountered, solve entire brace
	        else if (tokens[i] == ')')
	        {
	            while (ops.peek() != '('){
	            	values.push(applyOp(ops.pop(), values.pop(), values.pop()));
	            }                  
	            ops.pop();
	        }
	
	        // Current token is an operator.
	        else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/' || tokens[i] == '%')
	        {
	            // While top of 'ops' has same or greater precedence to current
	            // token, which is an operator. Apply operator on top of 'ops'
	            // to top two elements in values stack
	            
	        	while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())){
	            	values.push(applyOp(ops.pop(), values.pop(), values.pop()));
	            }
	
	            // Push current token to 'ops'.
	            ops.push(tokens[i]);
	        }
	    }
	
	    // Entire expression has been parsed at this point, apply remaining
	    // ops to remaining values
	    while (!ops.empty()){
	        values.push(applyOp(ops.pop(), values.pop(), values.pop()));
	    }
	
	    // Top of 'values' contains result, return it
	    logger.info("\t Final Result  ........  ");
	    return values.pop();
	}

	public static String evaluateAnother(String expression,String textId)
	{
		logger.info("@@ expression : "+expression);
		
	    char[] tokens = expression.toCharArray();
	
	     // Stack for numbers: 'values'
	    Stack<Double> values = new Stack<Double>();
	
	    // Stack for Operators: 'ops'
	    Stack<Character> ops = new Stack<Character>();
	
	    for (int i = 0; i < tokens.length; i++)
	    {
	    	logger.info("@@ Token "+(i+1)+" : "+tokens[i]);
	         // Current token is a whitespace, skip it
	        if (tokens[i] == ' ')
	            continue;
	
	        // Current token is a number, push it to stack for numbers		// && tokens[i] == '.'
	        if (tokens[i] >= '0' && tokens[i] <= '9')
	        {
	            StringBuffer sbuf = new StringBuffer();
	            // There may be more than one digits in number
	            while (i < tokens.length){
	            	if(tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.'){  
	                    sbuf.append(tokens[i]);
	            	}
	            	if (tokens[i] == ' ' || tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/' || tokens[i] == '%' || tokens[i] == ')'){
	            		i--;
	            		break;
	            	}
	            	i++;
	            }
	            values.push(Double.parseDouble(sbuf.toString()));
	        }
	
	        // Current token is an opening brace, push it to 'ops'
	        else if (tokens[i] == '(')
	        {
	            ops.push(tokens[i]);
	        }
	
	        // Closing brace encountered, solve entire brace
	        else if (tokens[i] == ')')
	        {
	            while (ops.peek() != '('){
	            	values.push(applyOp(ops.pop(), values.pop(), values.pop()));
	            }                  
	            ops.pop();
	        }
	
	        // Current token is an operator.
	        else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/' || tokens[i] == '%')
	        {
	            // While top of 'ops' has same or greater precedence to current
	            // token, which is an operator. Apply operator on top of 'ops'
	            // to top two elements in values stack
	            
	        	while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())){
	            	values.push(applyOp(ops.pop(), values.pop(), values.pop()));
	            }
	
	            // Push current token to 'ops'.
	            ops.push(tokens[i]);
	        }
	    }
	
	    // Entire expression has been parsed at this point, apply remaining
	    // ops to remaining values
	    while (!ops.empty()){
	        values.push(applyOp(ops.pop(), values.pop(), values.pop()));
	    }
	
	    // Top of 'values' contains result, return it
	    logger.info("\t Final Result  ........  ");
	    double finalRes = values.pop();
	    String finalOutput = Double.toString(finalRes) +";"+ textId;
	    return finalOutput;
	}

	// Returns true if 'op2' has higher or same precedence as 'op1',
	// otherwise returns false.
	public static boolean hasPrecedence(char op1, char op2)
	{
		logger.info("@@ [op1] :  "+op1+"  ||  [op2] :  "+op2);
	    if (op2 == '(' || op2 == ')')
	        return false;
	    if ((op1 == '*' || op1 == '/' || op1 == '%') && (op2 == '+' || op2 == '-'))
	        return false;
	    else
	        return true;
	}
	
	// A utility method to apply an operator 'op' on operands 'a'
	// and 'b'. Return the result.
	public static double applyOp(char op, double b, double a)
	{
		logger.info("applyOp	op	:: "+op+"		a	:: "+a+"		b	:: "+b);
		switch (op)
	    {
	        case '+':
	            return a + b;
	        case '-':
	            return a - b;
	        case '*':
	            return a * b;
	        case '/':
	            if (b == 0){
	                throw new
	                UnsupportedOperationException("Cannot divide by zero");
	            }
	            return a / b;
	        case '%':
	        	return ((a * b)/100);
	    }
	    return 0;
	}

	/**
	 * this method is used to get mac address of machine
	 * 
	 * @return String
	 */
	
	public String getMacAddress(){
		String macAddress=null;
		try {
			InetAddress ip = InetAddress.getLocalHost();
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));        
			}
			macAddress=sb.toString();
		} catch (UnknownHostException e) {
			logger.error(e);
		} catch (SocketException e){
			logger.error(e);
		}catch (NullPointerException e){
			logger.error(e);
		}catch (Exception e){
			logger.error(e);
		}
		return macAddress;
	}
	
	/**
	 * this method get static ip address of machine
	 * 
	 * @return String
	 */
	public String getStaticIpAddress(){
		String staticIpAddress = null;
		try {
			InetAddress ip = InetAddress.getLocalHost();
			staticIpAddress = ip.getHostAddress();
		} catch (UnknownHostException e) {
			logger.error(e);
		} catch (NullPointerException e){
			logger.error(e);
		} catch (Exception e){
			logger.error(e);
		}
		return staticIpAddress;
	}
	
	
	/**
	 * this method is used to update password into LDAP for a user.
	 */
	public String updatePassword(LoginForm login) {
		String passwordStatus="fail";
		try{
			logger.info("updatePassword(LoginForm login) method in Utility");
			LdapInfo ldapInfo = ldapTemplate.findOne(query().where("uid").is(login.getUserId()), LdapInfo.class);
			if(ldapInfo!=null && ldapInfo.getDn()!=null){
				Attribute newattr = new BasicAttribute("userPassword", login.getNewPassword());
				ModificationItem modificationItem = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, newattr);
				ldapTemplate.modifyAttributes(ldapInfo.getDn().toString(), new ModificationItem[] { modificationItem });
				passwordStatus="success";
			}
		}catch(Exception e){
			logger.error("Exception in updatePassword(LoginForm login) method in Utility for LDAP operation EXCEPTION: ",e);
			passwordStatus="fail";
		}
		return passwordStatus;
	}



	/**
	 * This method takes an object name as a String and returns the base64
	 * encoded value as a String.
	 * 
	 * @param String
	 * @return String
	 * 
	 */
	public String getBase64EncodedID(String strName) {
		byte[] encodedBytes = Base64.encodeBase64(strName.getBytes());
		return new String(encodedBytes);
	}



	/**
	 * 
	 * @param epochDate
	 * @return
	 */
	public String epochToHumanReadableFormat(String epochDate) {
		String readableDate = new SimpleDateFormat("dd/MM/yyyy")
				.format(new Date(Integer.parseInt(epochDate) * 1000L));
		return readableDate;
	}
	
	public String readQRCode(String filePath, String charset,
			Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap)
			throws FileNotFoundException, IOException, NotFoundException {
		System.out.println("path in utility::"+filePath);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
				new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(filePath)))));
		Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
				hintMap);
		return qrCodeResult.getText();

	}
	
	
	public  void createQRCode(String qrCodeData, String filePath,
			String charset,
			Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap,
			int qrCodeheight, int qrCodewidth) throws WriterException,
			IOException {
		BitMatrix matrix = new MultiFormatWriter().encode(
				new String(qrCodeData.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
				.lastIndexOf('.') + 1), new File(filePath));
	}
	
/************************Naimisha****************************/
	
	public String convertDDMMYYtoYYMMDD(String inputDate) {
		DateFormat userDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-MM-dd");
		String outputDate = null;
		try {
			outputDate = dateFormatNeeded.format(userDateFormat
					.parse(inputDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return outputDate;
	}
	
	/**
	 * 
	 * This method takes a particular formId and creates a PDF admission form
	 * and saves it at a predefined location.
	 * 
	 * @param String
	 * @return void
	 */
	public void printAdmissionFormPDF(
			ArrayList<com.qts.icam.model.admission.AdmissionForm> admissionFormList) {

		for (com.qts.icam.model.admission.AdmissionForm admissionForm : admissionFormList) {
			Document document = new Document();
			try {
				String path = admissionForm.getRootPath()
						+ admissionForm.getPdfFolder()
						+ admissionForm.getAdmissionFormYear() + "/"
						+ admissionForm.getCourseName() + "/"
						+ admissionForm.getAdmissionDriveName() + "/"
						+ admissionForm.getCourseClass() + "/";
				File dir = new File(path);
				boolean isDirCreated = dir.mkdirs();
				if (isDirCreated)
					logger.debug("created");
				else
					logger.debug("Failed");
				/*
				 * PdfWriter.getInstance(document, new FileOutputStream(
				 * "C:/Test/" +admissionForm.getAdmissionFormName() + "-" +
				 * admissionForm.getAdmissionFormCode() + ".pdf"));
				 */
				PdfWriter.getInstance(document, new FileOutputStream(path
						+ admissionForm.getAdmissionDriveName() + "-"
						+ admissionForm.getAdmissionDriveCode() + ".pdf"));

				document.open();

				Font fontb = new Font(Font.FontFamily.TIMES_ROMAN, 15,
						Font.BOLD);

				Phrase phrase10 = new Phrase("Admission Form No:  ", fontb);
				Phrase phrase20 = new Phrase(
						admissionForm.getAdmissionDriveCode(), fontb);
				Paragraph paragraph10 = new Paragraph();
				paragraph10.add(phrase10);
				paragraph10.add(phrase20);
				document.add(paragraph10);

				Phrase phrase11 = new Phrase("Class:  ", fontb);
				Phrase phrase21 = new Phrase(admissionForm.getCourseClass(),
						fontb);
				Paragraph paragraph11 = new Paragraph();
				paragraph11.add(phrase11);
				paragraph11.add(phrase21);
				document.add(paragraph11);

				Phrase phrase12 = new Phrase("Academic Year:  ", fontb);
				Phrase phrase22 = new Phrase(
						admissionForm.getAdmissionFormYear(), fontb);
				Paragraph paragraph12 = new Paragraph();
				paragraph12.add(phrase12);
				paragraph12.add(phrase22);
				document.add(paragraph12);

				Phrase phrase13 = new Phrase("Course Name:  ", fontb);
				Phrase phrase23 = new Phrase(admissionForm.getCourseName(),
						fontb);
				Paragraph paragraph13 = new Paragraph();
				paragraph13.add(phrase13);
				paragraph13.add(phrase23);
				document.add(paragraph13);

				Phrase phrase14 = new Phrase("Course Type:  ", fontb);
				Phrase phrase24 = new Phrase(admissionForm.getCourseType(),
						fontb);
				Paragraph paragraph14 = new Paragraph();
				paragraph14.add(phrase14);
				paragraph14.add(phrase24);
				document.add(paragraph14);

				Phrase phrase15 = new Phrase("Admissin Drive:  ", fontb);
				Phrase phrase25 = new Phrase(
						admissionForm.getAdmissionDriveName(), fontb);
				Paragraph paragraph15 = new Paragraph();
				paragraph15.add(phrase15);
				paragraph15.add(phrase25);
				document.add(paragraph15);

				Phrase phrase16 = new Phrase("Form Submission Last Date:  ",
						fontb);
				Phrase phrase26 = new Phrase(
						admissionForm.getAdmissionFormSubmissionLastDate(),
						fontb);
				Paragraph paragraph16 = new Paragraph();
				paragraph16.add(phrase16);
				paragraph16.add(phrase26);
				document.add(paragraph16);

				/*
				 * Image image1 =
				 * Image.getInstance("C:/Test/admission_form_printable.gif");
				 */
				Image image1 = Image.getInstance(admissionForm.getImagePath()
						+ admissionForm.getImageFile1name());

				document.add(image1);

				/* Image image2 = Image.getInstance("C:/Test/terms.jpeg"); */
				Image image2 = Image.getInstance(admissionForm.getImagePath()
						+ admissionForm.getImageFile2name());
				document.add(image2);

				document.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @author anup.roy
	 * this method converts epoch to date format DD/MM/YYYY
	 * */
	
	public String convertEpochToDDMMYYYY(int epoch){
		long timeStamp = (long)epoch*1000;
		Date date = new Date(timeStamp);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dateFromEpoch = format.format(date);
		return dateFromEpoch;
	}
	
	//Modified by Saikat 16/6/2018
	public Map<String, String> sendSMSForDisciplinaryAction(Student student, String smsURL, String smsAuthkey){
		boolean status = false;
		Map<String, String> dataList = new HashMap<String, String>();
		String message = null;
		
		try{
			SMS sms = new SMS();
			message = "Disciplinary Action ("+student.getDisciplinaryCode()+"): " + student.getDescription() + " Comment: "+student.getChequeNo();
			System.out.println("Message :"+message);
			dataList.put("message", message);
			sms.setMessage(message);
			List<String> phoneNumbers = new ArrayList<String>();
			phoneNumbers.add(student.getMobileNo());
			sms.setTo(phoneNumbers);
			
			List<SMS> smsList = new ArrayList<SMS>();
			smsList.add(sms);
			
			/*Config Items*/
			SMSData smsData = new SMSData();
			smsData.setSender("SOCKET");
			smsData.setRoute("4");
			smsData.setCountry("91");
			
			smsData.setSms(smsList);
			
			/*Send Now*/
			Gson gson = new Gson();
			
			HttpResponse<String> response = null;
			response = Unirest.post(smsURL)
						  .header("authkey", smsAuthkey)
						  .header("content-type", "application/json")
						  .body(gson.toJson(smsData))
						  .asString();
			System.out.println("Response.getBody():"+response.getBody());
			status = true;
		}catch(Exception e){
			e.printStackTrace();
			status = false;
			dataList.put("status", String.valueOf(status));
		}
		status = true;
		dataList.put("status", String.valueOf(status));
		return dataList;
	}
	
	//Modified by Saikat 16/6/2018
	public Map<String, String> sendSMSForSchoolNote(String phoneNumber, SchoolNote schoolNote, String smsURL, String smsAuthkey){
		boolean status = false;
		Map<String, String> dataList = new HashMap<String, String>();
		String message = null;
		
		try{
			SMS sms = new SMS();
			message = "School Note (" + schoolNote.getNote() + "): " + schoolNote.getDescription();
			dataList.put("message", message);
			//System.out.println("Message :"+message);
			List<String> phoneNumbers = new ArrayList<String>();
			phoneNumbers.add(phoneNumber);
			sms.setMessage(message);
			sms.setTo(phoneNumbers);
			
			List<SMS> smsList = new ArrayList<SMS>();
			smsList.add(sms);
			
			/*Config Items*/
			SMSData smsData = new SMSData();
			smsData.setSender("SOCKET");
			smsData.setRoute("4");
			smsData.setCountry("91");
			
			smsData.setSms(smsList);
			
			/*Send Now*/
			Gson gson = new Gson();
			
			HttpResponse<String> response = null;
			response = Unirest.post(smsURL)
						  .header("authkey", smsAuthkey)
						  .header("content-type", "application/json")
						  .body(gson.toJson(smsData))
						  .asString();
			System.out.println("Response.getBody():"+response.getBody());
			status = true;
			dataList.put("status", String.valueOf(status));
			
		}catch(Exception e){
			e.printStackTrace();
			status = false;
			dataList.put("status", String.valueOf(status));
		}
		return dataList;
	}

	//Modified by Saikat 16/6/2018
	public Map<String, String> sendSMSForSchoolEvent(String phoneNumber, SchoolEvent schoolEvent, String smsURL, String smsAuthkey){
		boolean status = false;
		Map<String, String> dataList = new HashMap<String, String>();
		String message = null;
		
		try{
			SMS sms = new SMS();
			message = "School would organize " + schoolEvent.getEventName() + " from " + schoolEvent.getEventStartDate() + " to " + schoolEvent.getEventEndDate();
			//System.out.println("Message :"+message);
			sms.setMessage(message);
			dataList.put("message", message);
			List<String> phoneNumbers = new ArrayList<String>();
			phoneNumbers.add(phoneNumber);
			sms.setTo(phoneNumbers);
			
			List<SMS> smsList = new ArrayList<SMS>();
			smsList.add(sms);
			
			/*Config Items*/
			SMSData smsData = new SMSData();
			smsData.setSender("SOCKET");
			smsData.setRoute("4");
			smsData.setCountry("91");
			
			smsData.setSms(smsList);
			
			/*Send Now*/
			Gson gson = new Gson();
			
			HttpResponse<String> response = null;
			response = Unirest.post(smsURL)
						  .header("authkey", smsAuthkey)
						  .header("content-type", "application/json")
						  .body(gson.toJson(smsData))
						  .asString();
			System.out.println("Response.getBody():"+response.getBody());
			status = true;
			dataList.put("status", String.valueOf(status));
			
		}catch(Exception e){
			e.printStackTrace();
			status = false;
			dataList.put("status", String.valueOf(status));
		}
		return dataList;
	}

	//Modified by Saikat 16/6/2018
	public Map<String, String> sendSMSForStudentAchievement(String phoneNumber, SchoolEvent schoolEvent, String eventPosition, String smsURL, String smsAuthkey){
		boolean status = false;
		Map<String, String> dataList = new HashMap<String, String>();
		String message = null;
		
		try{
			SMS sms = new SMS();
			message = "Congratulations: Your ward secured " +  eventPosition + " position in the event "+ schoolEvent.getEventName();
			//System.out.println("Message :"+message);
			sms.setMessage(message);
			dataList.put("message", message);
			List<String> phoneNumbers = new ArrayList<String>();
			phoneNumbers.add(phoneNumber);
			sms.setTo(phoneNumbers);
			
			List<SMS> smsList = new ArrayList<SMS>();
			smsList.add(sms);
			
			/*Config Items*/
			SMSData smsData = new SMSData();
			smsData.setSender("SOCKET");
			smsData.setRoute("4");
			smsData.setCountry("91");
			
			smsData.setSms(smsList);
			
			/*Send Now*/
			Gson gson = new Gson();
			
			HttpResponse<String> response = null;
			response = Unirest.post(smsURL)
						  .header("authkey", smsAuthkey)
						  .header("content-type", "application/json")
						  .body(gson.toJson(smsData))
						  .asString();
			System.out.println("Response.getBody():"+response.getBody());
			status = true;
			dataList.put("status", String.valueOf(status));
			
		}catch(Exception e){
			e.printStackTrace();
			status = false;
			dataList.put("status", String.valueOf(status));
		}
		return dataList;
	}

}
