package com.qts.icam.service.impl.tender;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.qts.icam.dao.tender.TenderDao;
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.model.tender.Tender;
import com.qts.icam.model.tender.TenderCategory;
import com.qts.icam.model.tender.TenderType;
import com.qts.icam.service.tender.TenderService;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;

@Service
public class TenderServiceImpl implements TenderService {

	@Autowired
	TenderDao tenderDao;
	
	@Autowired
	FileUploadDownload fileUploadDownload; 
	
	public static Logger logger = Logger.getLogger(TenderServiceImpl.class);
	
	@Override
	public List<TenderType> getAllTenderType() {
		return tenderDao.getAllTenderType();
	}
	
	@Override
	public List<TenderCategory> getAllTenderCategory() {
		return tenderDao.getAllTenderCategory();
	}	
	
	@Override
	public List<TenderCategory> getAllTenderSubCategory(String tenderSubCategory) {
		return tenderDao.getAllTenderSubCategory(tenderSubCategory);
	}

	@Override
	public void tenderDocumentUpload(Tender tender) {
		
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		Attachment attachment = null;
		String filePath = tender.getUploadFile().getAttachment().getStorageRootPath()+"/";
		filePath = filePath + tender.getUploadFile().getAttachment().getFolderName();
		System.out.println("filePath======"+filePath);
		
		try{
			UploadFile uploadFile = tender.getUploadFile();					
			if(uploadFile!=null){
				/*
				 * this is used for upload Tender Related Documents
				 */
				if(uploadFile.getTenderRelatedFile()!=null && !uploadFile.getTenderRelatedFile().isEmpty()){
					String tenderDocPath =  filePath+tender.getTenderReferenceNumber()+"/";
					logger.info("UPDATE TENDER DOC PATH:"+tenderDocPath);
					for (CommonsMultipartFile file : uploadFile.getTenderRelatedFile()) {
						if(file.getOriginalFilename()!=""){
							attachment = new Attachment();
							int fileSize = fileUploadDownload.fileUpload(tenderDocPath, file);
							attachment.setStorageRootPath(tenderDocPath+"/"+file.getOriginalFilename());
							attachmentList.add(attachment);
						}
					}
				}
				tender.setAttachmentList(attachmentList);
				String uploadStatus = tenderDao.submitTenderAttachmentDoc(tender);
				logger.info("tenderDocumentUpload Status:" + uploadStatus);
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("tenderDocumentUpload() In TenderServiceImpl.java: Exception" + e);
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String submitTenderForm(Tender tender) {
		return tenderDao.submitTenderForm(tender);
	}	
	

}
