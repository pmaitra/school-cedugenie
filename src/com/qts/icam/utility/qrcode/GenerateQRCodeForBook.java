package com.qts.icam.utility.qrcode;

import java.util.Hashtable;
import java.util.List;
import java.util.TimerTask;
import com.qts.icam.utility.Utility;
import com.qts.icam.model.common.BookForQRCode;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.qts.icam.dao.backoffice.BackOfficeDAO;

public class GenerateQRCodeForBook extends TimerTask {

//public static Logger logger = Logger.getLogger(ReadExcelForStudentDetails.class);
	
	BackOfficeDAO backOfficeDAO=null;
	List<String> attributeColumnForBook;
	String path;
	public GenerateQRCodeForBook(BackOfficeDAO backOfficeDAO, List<String> attributeColumnForBook, String path){
		System.out.println("in cont");
		this.backOfficeDAO=backOfficeDAO;
		this.attributeColumnForBook=attributeColumnForBook;
		this.path=path;
	}
	@Override
	public void run() {
		System.out.println("in run");
		try{					
			Utility util = new Utility();		
			
			String charset = "UTF-8"; // or "ISO-8859-1"
			Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			
			List<BookForQRCode> bookForQRCodeList = backOfficeDAO.readBookDataToGenerateQRCode();
			System.out.println(bookForQRCodeList.size());
			for(BookForQRCode bookForQRCode:bookForQRCodeList){
				System.out.println(bookForQRCode.getBookIndividualCode());
				String filePath = path+bookForQRCode.getBookIndividualCode()+".png";
				String qrCodeData="Individual Code : "+bookForQRCode.getBookIndividualCode();
				
				if(attributeColumnForBook.contains("bookName"))
					qrCodeData=qrCodeData+" ; Book Name : "+bookForQRCode.getBookName();
				if(attributeColumnForBook.contains("dateOfEntry"))
					qrCodeData=qrCodeData+" ; Book Entery Date : "+bookForQRCode.getBookEntryDate();
				if(attributeColumnForBook.contains("price"))
					qrCodeData=qrCodeData+" ; Book Price : "+bookForQRCode.getPrice();
				if(attributeColumnForBook.contains("author"))
					qrCodeData=qrCodeData+" ; Book Author : "+bookForQRCode.getBookAuthor();
				if(attributeColumnForBook.contains("publisher"))
					qrCodeData=qrCodeData+" ; Book Publisher : "+bookForQRCode.getBookPublisherName();
				if(attributeColumnForBook.contains("isbn"))
					qrCodeData=qrCodeData+" ; Book Isbn : "+bookForQRCode.getBookIsbn();
				if(attributeColumnForBook.contains("medium"))
					qrCodeData=qrCodeData+" ; Book Medium : "+bookForQRCode.getBookMediumName();
				if(attributeColumnForBook.contains("edition"))
					qrCodeData=qrCodeData+" ; Book Edition : "+bookForQRCode.getBookEdition();
				if(attributeColumnForBook.contains("bookLanguage"))
					qrCodeData=qrCodeData+" ; Book Language : "+bookForQRCode.getBookLanguageName();
			
				
				util.createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);
				
				backOfficeDAO.updateBookIdForQRCode(bookForQRCode.getBookIndividualCode());
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
}
