package com.qts.icam.dao.impl.tender;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.qts.icam.dao.impl.ticket.TicketDaoImpl;
import com.qts.icam.dao.tender.TenderDao;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.tender.Tender;
import com.qts.icam.model.tender.TenderCategory;
import com.qts.icam.model.tender.TenderType;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;

@Repository
public class TenderDaoImpl implements TenderDao{
	
	private final static Logger logger = Logger.getLogger(TicketDaoImpl.class);	
	
	@Autowired
	EncryptDecrypt encryptDecrypt; 

	@Autowired
	private SqlSessionFactory SqlSessionFactory;
	
	@Override
	public List <TenderType> getAllTenderType(){
		List<TenderType> tenderList = null;	
		SqlSession session = SqlSessionFactory.openSession();
		try {
			logger.info("In getAllTenderType() method of TenderDaoImpl");
			session = SqlSessionFactory.openSession();
			tenderList = session.selectList("getAllTenderType");
		} catch (Exception e) {
			logger.error("getAllTenderType() In TenderDaoImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return tenderList;
		
	}

	@Override
	public List <TenderCategory> getAllTenderCategory(){
		List<TenderCategory> tenderCategoryList = null;	
		SqlSession session = SqlSessionFactory.openSession();
		try {
			logger.info("In getAllTenderCategory() method of TenderDaoImpl");
			session = SqlSessionFactory.openSession();
			tenderCategoryList = session.selectList("getAllTenderCategory");
		} catch (Exception e) {
			logger.error("getAllTenderCategory() In TenderDaoImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return tenderCategoryList;
		
	}
	
	@Override
	public List <TenderCategory> getAllTenderSubCategory(String tenderCategoryCode){
		List<TenderCategory> tenderSubCategoryList = null;	
		SqlSession session = SqlSessionFactory.openSession();
		try {
			logger.info("In getAllTenderSubCategory() method of TenderDaoImpl");
			session = SqlSessionFactory.openSession();
			tenderSubCategoryList = session.selectList("getAllTenderSubCategory",tenderCategoryCode);
		} catch (Exception e) {
			logger.error("getAllTenderSubCategory() In TenderDaoImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return tenderSubCategoryList;
		
	}

	@Override
	public String submitTenderAttachmentDoc(Tender tender) {
		SqlSession session = SqlSessionFactory.openSession();
		String uploadStatus = "success";
		try{
			tender.setObjectId(encryptDecrypt.encrypt("TenderDaoImpl"));
			int intInsertStatus = session.insert("submitTenderAttachmentDoc", tender);
			session.commit();
		}catch(Exception e){
			uploadStatus = "fail";
			e.printStackTrace();
			logger.error("In submitTenderAttachmentDoc() In TenderDaoImpl.java: Exception" + e);
		}
		return uploadStatus;
	}

	@Override
	public String submitTenderForm(Tender tender) {
		SqlSession session = SqlSessionFactory.openSession();
		String status = "success";
		try {
			tender.setObjectId(encryptDecrypt.getBase64EncodedID("TenderDAOImpl"));
			int insertStatus = session.insert("insertIntoTender", tender);
			if(insertStatus != 0){
				int insertStatus2 = session.insert("insertTenderCommodity", tender);
			}
			
			
				session.commit();
		}catch (Exception e) {
			e.printStackTrace();
			status="fail";
			logger.error("submitTenderForm(Tender tender) In TenderDaoImpl.java: Exception",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return status;
	}
	
}
