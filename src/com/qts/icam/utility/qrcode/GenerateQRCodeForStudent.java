package com.qts.icam.utility.qrcode;

import java.util.Hashtable;
import java.util.List;
import java.util.TimerTask;

import com.qts.icam.utility.Utility;
import com.qts.icam.model.common.StudentForQRCode;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.qts.icam.dao.backoffice.BackOfficeDAO;
//import qls.sms.utility.qrCode.StudentForQRCode;

public class GenerateQRCodeForStudent extends TimerTask {
//public static Logger logger = Logger.getLogger(ReadExcelForStudentDetails.class);
	
	BackOfficeDAO backOfficeDAO=null;
	List<String> attributeColumnForBook;
	String path;
	public GenerateQRCodeForStudent(BackOfficeDAO backOfficeDAO, List<String> attributeColumnForBook, String path){
		this.backOfficeDAO=backOfficeDAO;
		this.attributeColumnForBook=attributeColumnForBook;
		this.path=path;
	}
	@Override
	public void run() {
		try{					
			Utility util = new Utility();		
			
			String charset = "UTF-8"; // or "ISO-8859-1"
			Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			
			List<StudentForQRCode> studentForQRCodeList = backOfficeDAO.readStudentDataToGenerateQRCode();
			System.out.println("StudentList::"+studentForQRCodeList);
			if(null != studentForQRCodeList && studentForQRCodeList.size()!= 0){
				for(StudentForQRCode studentForQRCode:studentForQRCodeList){
					String filePath = path+studentForQRCode.getRollNumber()+".png";
					
					String qrCodeData="Roll Number : "+studentForQRCode.getRollNumber();
					
					if(attributeColumnForBook.contains("name"))
						qrCodeData=qrCodeData+" ; Student Name : "+studentForQRCode.getName();
					if(attributeColumnForBook.contains("gender"))
						qrCodeData=qrCodeData+" ; Gender : "+studentForQRCode.getGender();
					if(attributeColumnForBook.contains("dateOfBirth"))
						qrCodeData=qrCodeData+" ; DOB : "+studentForQRCode.getDateOfBirth();
					if(attributeColumnForBook.contains("fathersName"))
						qrCodeData=qrCodeData+" ; Father's Name : "+studentForQRCode.getFathersName();
					if(attributeColumnForBook.contains("motherName"))
						qrCodeData=qrCodeData+" ; Mother's Name : "+studentForQRCode.getMotherName();
					if(attributeColumnForBook.contains("klass"))
						qrCodeData=qrCodeData+" ; Class : "+studentForQRCode.getKlass();
					if(attributeColumnForBook.contains("section"))
						qrCodeData=qrCodeData+" ; Section : "+studentForQRCode.getSection();
					if(attributeColumnForBook.contains("stream"))
						qrCodeData=qrCodeData+" ; Stream : "+studentForQRCode.getStream();
					/*if(attributeColumnForBook.contains("rollNumber"))
						qrCodeData=qrCodeData+" ; Roll Number : "+studentForQRCode.getRollNumber();*/
					if(attributeColumnForBook.contains("course"))
						qrCodeData=qrCodeData+" ; Course : "+studentForQRCode.getCourse();
					if(attributeColumnForBook.contains("bloodGroup"))
						qrCodeData=qrCodeData+" ; Blood Group : "+studentForQRCode.getBloodGroup();
					if(attributeColumnForBook.contains("contactNumber"))
						qrCodeData=qrCodeData+" ; Contact Number : "+studentForQRCode.getContactNumber();
					if(attributeColumnForBook.contains("contactMailId"))
						qrCodeData=qrCodeData+" ; Mail Id : "+studentForQRCode.getContactMailId();
				
					System.out.println("line 69::"+qrCodeData);
					util.createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);
				}
			}else{
				System.out.println("Student list is empty.");
			}		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
}