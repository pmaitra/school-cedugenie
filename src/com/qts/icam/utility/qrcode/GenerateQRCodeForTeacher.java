package com.qts.icam.utility.qrcode;

import java.util.Hashtable;
import java.util.List;
import java.util.TimerTask;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;
import com.qts.icam.model.common.TeacherForQRCode;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.qts.icam.dao.backoffice.BackOfficeDAO;

public class GenerateQRCodeForTeacher extends TimerTask {
//public static Logger logger = Logger.getLogger(ReadExcelForStudentDetails.class);
	
	BackOfficeDAO backOfficeDAO=null;
	List<String> attributeColumnForTeacher;
	String path;
	public GenerateQRCodeForTeacher(BackOfficeDAO backOfficeDAO, List<String> attributeColumnForTeacher, String path){
		this.backOfficeDAO=backOfficeDAO;
		this.attributeColumnForTeacher=attributeColumnForTeacher;
		this.path=path;
	}
	@Override
	public void run() {
		try{					
			Utility util = new Utility();		
			
			String charset = "UTF-8"; // or "ISO-8859-1"
			Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			
			List<TeacherForQRCode> teacherForQRCodeList = backOfficeDAO.readTeacherDataToGenerateQRCode();
			
			for(TeacherForQRCode teacherForQRCode:teacherForQRCodeList){
				String filePath = path+teacherForQRCode.getUserId()+".png";
				String qrCodeData="User Id : "+teacherForQRCode.getUserId();
				
				if(attributeColumnForTeacher.contains("name"))
					qrCodeData=qrCodeData+" ; Name : "+teacherForQRCode.getName();
				if(attributeColumnForTeacher.contains("gender"))
					qrCodeData=qrCodeData+" ; Gender : "+teacherForQRCode.getGender();
				if(attributeColumnForTeacher.contains("dateOfBirth"))
					qrCodeData=qrCodeData+" ; DOB : "+teacherForQRCode.getDateOfBirth();
				if(attributeColumnForTeacher.contains("designation"))
					qrCodeData=qrCodeData+" ; Designation : "+teacherForQRCode.getDesignation();
				if(attributeColumnForTeacher.contains("bloodGroup"))
					qrCodeData=qrCodeData+" ; Blood Group : "+teacherForQRCode.getBloodGroup();
				if(attributeColumnForTeacher.contains("mobile"))
					qrCodeData=qrCodeData+" ; Mobile : "+teacherForQRCode.getMobile();
				if(attributeColumnForTeacher.contains("emailId"))
					qrCodeData=qrCodeData+" ; EmailId : "+teacherForQRCode.getEmailId();		
				
				util.createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);			
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}