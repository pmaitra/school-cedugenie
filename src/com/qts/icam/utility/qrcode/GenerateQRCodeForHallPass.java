package com.qts.icam.utility.qrcode;

import java.util.Hashtable;
import java.util.List;
import java.util.TimerTask;

import com.qts.icam.utility.Utility;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.qts.icam.model.common.QRCodeForHallPass;

public class GenerateQRCodeForHallPass extends TimerTask {

	List<QRCodeForHallPass> dataForQRCode;
	String path;
	public GenerateQRCodeForHallPass(List<QRCodeForHallPass> dataString, String path){
		this.dataForQRCode=dataString;
		this.path=path;
	}
	
	@Override
	public void run() {
		try{					
			Utility util = new Utility();		
			
			String charset = "UTF-8"; // or "ISO-8859-1"
			Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
			hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			
			for(QRCodeForHallPass s:dataForQRCode){
				String filePath = path+s.getRollNumber()+".png";
				
				util.createQRCode(s.getTotalString(), filePath, charset, hintMap, 200, 200);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
