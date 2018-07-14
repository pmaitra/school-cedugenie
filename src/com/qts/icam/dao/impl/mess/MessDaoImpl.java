package com.qts.icam.dao.impl.mess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.qts.icam.dao.impl.inventory.InventoryDaoImpl;
import com.qts.icam.dao.mess.MessDao;
import com.qts.icam.model.common.CommodityIssueVoucher;
import com.qts.icam.model.common.CommodityIssueVoucherDetails;
import com.qts.icam.model.common.CommodityReceiveVoucher;
import com.qts.icam.model.common.CommodityReceiveVoucherDetails;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.MessDailyConsumption;
import com.qts.icam.model.common.MessDailyConsumptionDetails;
import com.qts.icam.model.common.MessDailyRationPurchaseOrder;
import com.qts.icam.model.common.MessDailyRationPurchaseOrderDetails;
import com.qts.icam.model.common.MessDemandVoucher;
import com.qts.icam.model.common.MessDemandVoucherDetails;
import com.qts.icam.model.common.MessMenuRoutine;
import com.qts.icam.model.common.MessMenuTime;
import com.qts.icam.model.common.Notification;
import com.qts.icam.model.common.PerishableMaterial;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.RoutineSlab;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.finance.TransactionalWorkingArea;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;

@Repository
public class MessDaoImpl implements MessDao {
	
	public static Logger logger = Logger.getLogger(MessDaoImpl.class);
	
	EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	Utility util = new Utility();
	
	/**
	 * @author anup.roy
	 * this method is for getting commodities for demand voucher*/
	@Override
	public List<Commodity> getCommodityListForMess() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Commodity> messCommodityList = null;;
		try {
			messCommodityList = session.selectList("selectCommodityListForMess");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return messCommodityList;
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting demand voucher list*/

	@Override
	public List<MessDemandVoucher> getMessDemandVoucherList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<MessDemandVoucher> messDemandVoucherList = null;;
		try {
			logger.info("In List<MessDemandVoucher> getMessDemandVoucherList() of MessDaoImpl.java");
			messDemandVoucherList = session.selectList("selectMessDemandVoucherList");
		}catch (Exception e) {
			logger.error("Exception In List<MessDemandVoucher> getMessDemandVoucherList() of MessDaoImpl.java:",e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return messDemandVoucherList;
	}

	/**
	 * @author anup.roy
	 * this method is for getting stock, rate, unit for a commodity*/
	
	@Override
	public Commodity getCommodityDetailsForIndentSheet(String commodity) {
		SqlSession session = sqlSessionFactory.openSession();
		Commodity commodityDetails = null;
		try{
			logger.info("In String getCommodityDetailsForIndentSheet(commodity) in MessDaoImpl");
			commodityDetails = session.selectOne("getCommodityDetailsForIndentSheet", commodity);
		}catch (Exception e) {
			logger.error("Exception In String getCommodityDetailsForIndentSheet(commodity) in MessDaoImpl:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return commodityDetails;
	}

	/**
	 * @author anup.roy
	 * this method is for getting last demand voucher id*/
	
	@Override
	public int getLastDemandVoucherId() {
		int messVoucherId = 0;
		String voucher = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			logger.info("In String getLastDemandVoucherId() of MessDaoImpl.java");
			voucher = session.selectOne("getLastDemandVoucherId");
			if(null!= voucher){
				messVoucherId = Integer.parseInt(voucher);
			}
		}catch (Exception e) {
			logger.error("Exception in String getLastDemandVoucherId() of MessDaoImpl.java",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return messVoucherId;
	}

	/**
	 * @author anup.roy
	 * this method is for submitting the demand voucher*/
	
	
	@Override
	public String submitMessDemandVoucher(MessDemandVoucher demandVoucher) {
		String insertStatus = "fail";
		SqlSession session = sqlSessionFactory.openSession();
		int voucherInsertStatus = 0;
		int voucherDetailsInsertStatus = 0;
		try{
			logger.info("In String submitMessDemandVoucher(MessDemandVoucher demandVoucher) of MessdaoImpl.java");
			demandVoucher.setDemandVoucherObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			voucherInsertStatus = session.insert("insertIntoMessDemandVoucher",demandVoucher);
			if(voucherInsertStatus!=0){
				List<MessDemandVoucherDetails> messDemandVoucherDetailsList = demandVoucher.getMessDemandVoucherDetailsList();				
				for(MessDemandVoucherDetails voucherDetails : messDemandVoucherDetailsList){
					voucherDetails.setMessDemandVoucherDetailsObjectId(demandVoucher.getDemandVoucherObjectId());
					voucherDetails.setUpdatedBy(demandVoucher.getUpdatedBy());
					voucherDetails.setIndentSheetId(demandVoucher.getDemandVoucherId());
					voucherDetailsInsertStatus = session.insert("insertIntoMessDemandVoucherDetails", voucherDetails);
				}
			}
			if(voucherInsertStatus!= 0 && voucherDetailsInsertStatus!=0){
				insertStatus = "success";
			}
		}catch (Exception e) {
			logger.error("In String submitMessDemandVoucher(MessDemandVoucher demandVoucher) of MessDaoImpl.java:",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return insertStatus;
	}

	/**
	 * @author anup.roy
	 * this method is for sending notification to specific role*/
	
	@Override
	public void sendNotification(Notification notification) {
		SqlSession session = sqlSessionFactory.openSession();
		try{
			logger.info("In sendNotification(Notification notification) in MessDaoImpl");
			notification.setNotificationObjectId(encryptDecrypt.getBase64EncodedID("MessDAOImpl"));
			List<Resource> resourceList = session.selectList("getAllUserIdOfQM");
			for(Resource resource : resourceList){
				notification.setNotificationReplyTo(resource.getUserId());
				session.insert("sendNotification", notification);
			}
		}catch (Exception e) {
			logger.error("Exception in sendNotification(Notification notification) in MessDaoImpl:",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting the details of Indent sheet
	 * for creating CIV*/

	@Override
	public MessDemandVoucher getDetailsOfDemandVoucher(String indentSheetId) {
		SqlSession session = sqlSessionFactory.openSession();
		MessDemandVoucher demand = null;
		List<MessDemandVoucherDetails> demandDetails = null;
		try{
			logger.info("IN MessDemandVoucher getDetailsOfDemandVoucher(String indentSheetId) of MessDaOImpl.java");
			demand = session.selectOne("getDetailsOfIndentSheet", indentSheetId);
			if(null!= demand){
				demandDetails = session.selectList("getItemDetailsOfIndentSheet", indentSheetId);
				demand.setMessDemandVoucherDetailsList(demandDetails);
			}
		}catch (Exception e) {
			logger.error("Exception in MessDemandVoucher getDetailsOfDemandVoucher(String indentSheetId) in MessDaoImpl.java::"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return demand;
	}

	
	/**
	 * @author anup.roy
	 * this method is for getting last commodity issue voucher id*/
	
	@Override
	public int getLastCommodityIssueVoucherId() {
		SqlSession session = sqlSessionFactory.openSession();
		String lastIssueVoucherCode = null;
		int lastId =0;
		try{
			logger.info("In int getLastCommodityIssueVoucherId() in MessDaoImpl.java");
			lastIssueVoucherCode = session.selectOne("getLastCommodityIssueVoucherId");
			if(null!= lastIssueVoucherCode){
				lastId = Integer.parseInt(lastIssueVoucherCode.substring(3));
			}
		}catch (Exception e) {
			logger.error("Exception in int getLastCommodityIssueVoucherId() in MessDaoImpl.java",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return lastId;
	}

	/**
	 * @author anup.roy
	 * this method is for submitting commodity issue voucher*/
	
	@Override
	public String submitCommodityIssueVoucher(CommodityIssueVoucher issueVoucher) {
		SqlSession session = sqlSessionFactory.openSession();
		int civStatus = 0;
		int civDetailsStatus = 0;
		int qmStockStatus = 0;
		String status = "fail";
		try{
			logger.info("In String submitCommodityIssueVoucher(CommodityIssueVoucher issueVoucher) In MessDaoImpl.java");
			issueVoucher.setCommodityIssueVoucherObjectId(encryptDecrypt.getBase64EncodedID("MessDAOImpl"));
			civStatus = session.insert("insertIntoMessCommodityIssueVoucher",issueVoucher);
			if(civStatus!=0){
				List<CommodityIssueVoucherDetails> commodityIssueVoucherDetailsList = issueVoucher.getCommodityIssueVoucherDetailsList();
				for(CommodityIssueVoucherDetails voucherDetails : commodityIssueVoucherDetailsList){
					voucherDetails.setCommodityIssueVoucherDetailsObjectId(issueVoucher.getCommodityIssueVoucherObjectId());
					voucherDetails.setUpdatedBy(issueVoucher.getUpdatedBy());
					voucherDetails.setVoucherCode(issueVoucher.getCommodityIssueVoucherCode());
					civDetailsStatus = session.insert("insertIntoMessCommodityIssueVoucherDetails", voucherDetails);
					qmStockStatus = session.update("updateQMStockForCommodity", voucherDetails);
				}
			}
			if(civStatus!= 0 && civDetailsStatus!=0 && qmStockStatus!=0){
				status = "success";
			}
		}catch (Exception e) {
			logger.error("Exception in String submitCommodityIssueVoucher(CommodityIssueVoucher issueVoucher) in MessDaoImpl.java"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return status;
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting details of issued materials*/

	@Override
	public CommodityIssueVoucher getDetailsOfIssueVoucher(String civId) {
		SqlSession session = sqlSessionFactory.openSession();
		CommodityIssueVoucher issueVoucher = null;
		List<CommodityIssueVoucherDetails> issueVoucherDetails = null;
		try{
			logger.info("IN CommodityIssueVoucher getDetailsOfIssueVoucher(String civId) of MessDaOImpl.java");
			issueVoucher = session.selectOne("getDetailsOfIssueVoucher", civId);
			if(null!= issueVoucher){
				issueVoucherDetails = session.selectList("getItemDetailsOfIssueVoucher", civId);
				issueVoucher.setCommodityIssueVoucherDetailsList(issueVoucherDetails);
			}
		}catch (Exception e) {
			logger.error("Exception in CommodityIssueVoucher getDetailsOfIssueVoucher(String civId) in MessDaoImpl.java::"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return issueVoucher;
	}
	
	
	/**
	 * @author anup.roy
	 * this method is for submitting receive voucher*/
	

	@Override
	public String submitCommodityReceiveVoucher(CommodityReceiveVoucher receiveVoucher) {
		SqlSession session = sqlSessionFactory.openSession();
		int crvStatus = 0;
		int crvDetailsStatus = 0;
		int indentSheetStatus = 0;
		int messStockStatus = 0;
		String status = "fail";
		try{
			logger.info("In String submitCommodityReceiveVoucher(CommodityReceiveVoucher receiveVoucher) In MessDaoImpl.java");
			receiveVoucher.setCommodityReceiveVoucherObjectId(encryptDecrypt.getBase64EncodedID("MessDAOImpl"));
			crvStatus = session.insert("insertIntoMessCommodityReceiveVoucher",receiveVoucher);
			if(crvStatus!=0){
				List<CommodityReceiveVoucherDetails> voucherDetails = receiveVoucher.getCommodityReceiveVoucherDetailsList();
				for(CommodityReceiveVoucherDetails voucher : voucherDetails){
					voucher.setCommodityReceiveVoucherDetailsObjectId(encryptDecrypt.getBase64EncodedID("MessDAOImpl"));
					voucher.setUpdatedBy(receiveVoucher.getUpdatedBy());
					voucher.setReceiveVoucherCode(receiveVoucher.getCommodityReceiveVoucherCode());
					crvDetailsStatus = session.insert("insertIntoMessCommodityReceiveVoucherDetails", voucher);
					indentSheetStatus = session.update("updateStatusAndCloseDate", receiveVoucher);
					String stockData = session.selectOne("getStockInMessForCommodity",voucher);
					if(null!=stockData){
						messStockStatus = session.update("updateStockInMess", voucher);
					}else{
						messStockStatus = session.insert("insertStockInMess", voucher);
					}
				}
			}
			if(crvStatus!= 0 && crvDetailsStatus!=0 && indentSheetStatus!=0 && messStockStatus!=0){
				status = "success";
			}
		}catch (Exception e) {
			logger.error("Exception in String submitCommodityReceiveVoucher(CommodityReceiveVoucher receiveVoucher) in MessDaoImpl.java"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return status;
	}

	/**
	 * @author anup.roy
	 * this method is for getting the stock of mess*/
	
	@Override
	public List<Commodity> getStockOfMess() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Commodity> commodityList = null;
		try{
			logger.error("In List<Commodity> getStockOfMess() of MessDaoImpl.java");
			commodityList = session.selectList("getStockOfMess");
		}catch (Exception e) {
			logger.error("Exception in List<Commodity> getStockOfMess() of MessDaoImpl.java:",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return commodityList;
	}
	
	/**
	 * @author anup.roy
	 * this method is for get current session*/

	@Override
	public String getCurrentSession() {
		SqlSession session = sqlSessionFactory.openSession();
		String inventorySession = null;
		try{
			logger.info("In String getCurrentSession() of MessDaoImpl.java");
			inventorySession = session.selectOne("getCurrentSession");
		}catch (Exception e) {
			logger.error("Exception in String getCurrentSession() of MessDaoImpl.java :",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return inventorySession;
	}

	/**
	 * @author anup.roy
	 * this method is for getting all vendors
	 * */
	@Override
	public List<Vendor> getAllVendors() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Vendor> vendorList = null;
		try{
			logger.info("In List<Vendor> getAllVendors() in MessDaoImpl.java");
			vendorList = session.selectList("getAllVendors");
		}catch (Exception e) {
			logger.error("Exception in List<Vendor> getAllVendors() in MessDaoImpl.java:",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return vendorList;
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting all commodities*/

	@Override
	public List<Commodity> getMessDailyRationCommodities() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Commodity> commodityList = null;
		try{
			logger.info("In List<Commodity> getMessDailyRationCommodities() in MessDaoImpl.java");
			commodityList = session.selectList("getMessDailyRationCommodities");
		}catch (Exception e) {
			logger.error("Exception in List<Commodity> getMessDailyRationCommodities() in MessDaoImpl.java:",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return commodityList;
	}

	/**
	 * @author anup.roy
	 * this method is for submitting mess daily ration PO*/
	@Override
	public String submitMessDailyRationPO(MessDailyRationPurchaseOrder messDailyPO) {
		SqlSession session = sqlSessionFactory.openSession();
		String vendorInsertStatus = "fail";
		String commodityInsertStatus = "fail";
		String POInsertStatus = "fail";
		String PODetailsInsertStatus = "fail";
		String insertStatus = "fail";
		try{
			logger.info("In submitMessDailyRationPO(MessDailyRationPurchaseOrder messDailyPO) of MessDaoImpl.java");
			Vendor vendor = messDailyPO.getVendor();
			vendor.setUpdatedBy(messDailyPO.getUpdatedBy());
			vendor.setVendorObjectId(encryptDecrypt.getBase64EncodedID("MessDAOImpl"));
			
			/** modified by @author Sourav.Bhadra on 24-10-2017 **/
			String ven = vendor.getVendorName();
			String vendorName = session.selectOne("checkVendorExistenceInMessDailyRationVendors", ven);
			if(null == vendorName || vendorName == ""){
				int vendorStatus = session.insert("insertIntoMessDailyRationVendor", vendor);
				if(vendorStatus != 0){
					vendorInsertStatus = "success";
				}
			}
			vendorInsertStatus = "success";
			messDailyPO.setMessDailyRationPurchaseOrderObjId(encryptDecrypt.getBase64EncodedID("MessDAOImpl"));
			int POStatus = session.insert("insertIntoMessDailyRationPO", messDailyPO);
			System.out.println("POStatus:"+POStatus);
			if(POStatus != 0){
				POInsertStatus = "success";
				/* added by @author Sourav.Bhadra on 06-03-2018 */
				TransactionalWorkingArea twa=new TransactionalWorkingArea();
				twa.setUpdatedBy(messDailyPO.getUpdatedBy());
				twa.setObjectId(util.getBase64EncodedID("MessDaoImpl"));
				twa.setResource(ven);
				twa.setNetAmount(messDailyPO.getTotalAmount());
				//twa.setTransactionMode(commodityPurchaseOrder.getTransactionMode());
				twa.setTransactionalWorkingAreaName("Perishable Material PO");
				twa.setTransactionalWorkingAreaDesc("Perishable Material PO");
				
				twa.setDescOfTransaction("Perishable Material PO");
				twa.setPaidAgainst(messDailyPO.getMessDailyRationPurchaseOrderCode());
				twa.setDepartment("MESS DEPARTMENT");
				twa.setAcademicYear(messDailyPO.getInventorySession());
				twa.setIncomeExpense("OTHER EXPENSE");
				session.insert("insertPMPOInTransactionalWorkingArea", twa);
				/**/
				List<MessDailyRationPurchaseOrderDetails> poDetailsList = messDailyPO.getMessDailyRationPoDetailsList();
				for(MessDailyRationPurchaseOrderDetails poDetails : poDetailsList){
					poDetails.setUpdatedBy(messDailyPO.getUpdatedBy());
					poDetails.setMessDailyRationPoDetailsObjId(encryptDecrypt.getBase64EncodedID("MessDAOImpl"));
					poDetails.setMessDailyRationPoDetailsCode(messDailyPO.getMessDailyRationPurchaseOrderCode());
					int PODetailsStatus = session.insert("insertIntoMessDailyRationPODetails", poDetails);
					System.out.println("PODetailsStatus:"+PODetailsStatus);
					if(PODetailsStatus != 0){
						PODetailsInsertStatus = "success";
						Commodity com = poDetails.getCommodity();
						com.setInStock(poDetails.getQuantity());
						session.update("updateMessDailyRationCommodityStock", com);
					}
				}
				String reqId = messDailyPO.getRequisitionId();
				session.update("updateStatusInPerishableMaterialRequisition", reqId);
			}
			if(vendorInsertStatus == "success" && POInsertStatus == "success" && PODetailsInsertStatus == "success" && commodityInsertStatus == "success"){
				insertStatus = "success";
			}
		}catch (Exception e) {
			logger.error("Exception in submitMessDailyRationPO(MessDailyRationPurchaseOrder messDailyPO) of MessDaoImpl.java",e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}finally {
			session.close();
		}
		System.out.println("insertStatus returning from daoimpl:"+insertStatus);
		return insertStatus;
	}

	@Override
	public int getLastMessDailyRationPOCode() {
		SqlSession session = sqlSessionFactory.openSession();
		String lastDailyRationPOCode = null;
		int lastIndex =0;
		try{
			logger.info("In int getLastMessDailyRationPOCode() in MessDaoImpl.java");
			lastDailyRationPOCode = session.selectOne("getLastMessDailyRationPOCode");
			if(null!= lastDailyRationPOCode){
				lastIndex = Integer.parseInt(lastDailyRationPOCode.substring(13));
			}
		}catch (Exception e) {
			logger.error("Exception in int getLastMessDailyRationPOCode() in MessDaoImpl.java",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return lastIndex;
	}

	/** @author Sourav.Bhadra
	 * for selecting mess daily ration vendor's details **/
	@Override
	public String getDailyRationVendorDetails(String vendorCode) {
		SqlSession session = sqlSessionFactory.openSession();
		Vendor detailsOfVendor = new Vendor();
		String vendorDetails = "";
		try{
			detailsOfVendor = session.selectOne("selectMessDailyRationVendorDetails", vendorCode);
			vendorDetails += detailsOfVendor.getVendorAddress() +";"+ detailsOfVendor.getVendorContactNumber();
		}catch(Exception e){
			e.printStackTrace();
		}
		return vendorDetails;
	}

	@Override
	public int getLastMessDailyConsumptionCIVCode() {
		SqlSession session = sqlSessionFactory.openSession();
		String lastMessDailyConsumptionCIVCode = null;
		int lastIndex =0;
		try{
			logger.info("In int getLastMessDailyConsumptionCIVCode() in MessDaoImpl.java");
			lastMessDailyConsumptionCIVCode = session.selectOne("getLastMessDailyConsumptionCIVCode");
			if(null!= lastMessDailyConsumptionCIVCode){
				lastIndex = Integer.parseInt(lastMessDailyConsumptionCIVCode.substring(19));
			}
		}catch (Exception e) {
			logger.error("Exception in int getLastMessDailyConsumptionCIVCode() in MessDaoImpl.java",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return lastIndex;
	}
	
	/** @author Sourav.Bhadra // to get commodities for daily mess consumption on 10-10-2017 **/
	@Override
	public String getCommoditiesForDailyMessConsumption(String commodityType) {
		SqlSession session = sqlSessionFactory.openSession();
		String commodities = "";
		try{
			List<String> commodityList = null;
			
			if(commodityType.equalsIgnoreCase("dailyration")){
				commodityList = session.selectList("getDailyRationCommodities");
			}else if(commodityType.equalsIgnoreCase("nondailyration")){
				commodityList = session.selectList("getNonDailyRationCommodities");
			}
			
			for(String com : commodityList){
				commodities += com + ";";
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return commodities;
	}
	
	/** @author Sourav.Bhadra // to get commodities mess stock for daily mess consumption on 10-10-2017 **/
	@Override
	public MessDailyConsumptionDetails getCommoditiesMessStockForDailyMessConsumption(String commodityType, String commodity,
			String issueDate) {
		SqlSession session = sqlSessionFactory.openSession();
		MessDailyConsumptionDetails consumptionDetails = null;
		try{
			issueDate += " 23:59:59";
			Map<String, String> map = new HashMap<String,String>();
			map.put("commodity", commodity);
	        map.put("issueDate", issueDate);
	        Double stock = 0.0;
	        if(commodityType.equalsIgnoreCase("dailyration")){
	        	consumptionDetails = session.selectOne("getMessStockOfADailyRationCommodity", map);
			}else if(commodityType.equalsIgnoreCase("nondailyration")){
				consumptionDetails = session.selectOne("getMessStockOfANonDailyRationCommodity", map);
			}
	    }catch (Exception e) {
			e.printStackTrace();
		}
		return consumptionDetails;
	}
	
	/**
	 * @author anup.roy
	 * this method is for submitting daily mess consumption*/
	@Override
	public String submitMessDailyConsumption(MessDailyConsumption consumption) {
		SqlSession session = sqlSessionFactory.openSession();
		List<MessDailyConsumptionDetails> consumptionDetailsList = null;
		String consumptionInsertStatus = "fail";
		String insertStatus = "fail";
		int consumptionDetailsStatus = 0;
		int messStockStatus = 0;
		try{
			logger.error("In String submitMessDailyConsumption(consumption) of MessdaoImpl");
			consumption.setMessDailyConsumptionObjectId(encryptDecrypt.getBase64EncodedID("MessDAOImpl"));
			int consumptionStatus = session.insert("insertIntoMessDailyConsumption", consumption);
			if(consumptionStatus != 0){
				consumptionInsertStatus = "success";
				consumptionDetailsList = consumption.getConsumptionDetailsList();
				for(MessDailyConsumptionDetails consumptionDetails : consumptionDetailsList){
					consumptionDetails.setMessDailyConsumptionDetailsObjectId(encryptDecrypt.getBase64EncodedID("MessDAOImpl"));
					consumptionDetails.setMessDailyConsumptionCode(consumption.getIssueToKitchenCiv());
					consumptionDetailsStatus = session.insert("insertIntoMessDailyConsumptionDetails", consumptionDetails);
					if(consumptionDetailsStatus != 0){
						messStockStatus = session.update("updateMessStatus", consumptionDetails);
					}
				}
			}
			if(consumptionInsertStatus == "success" && consumptionDetailsStatus != 0 && messStockStatus!=0){
				insertStatus = "success";
			}
		}catch (Exception e) {
			logger.error("Exception in String submitMessDailyConsumption(consumption) of MessdaoImpl.java",e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return insertStatus;
	}

	/**
	 * @author anup.roy
	 * for getting mess menu time list
	 * */

	@Override
	public List<MessMenuTime> getMessMenuTimeList() {
		List<MessMenuTime> messMenuTimeList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			logger.info("In List<MessMenuTime> getMessMenuTimeList() of MessDaoImpl.java");
			messMenuTimeList = session.selectList("getMessMenuTimeList");
		}catch (Exception e) {
			logger.error("Exception in List<MessMenuTime> getMessMenuTimeList() in MessDaoImpl.java,"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return messMenuTimeList;
	}
	
	/** @author Sourav.Bhadra // to get commodities' units for perishable material requisition request on 16-10-2017 **/
	@Override
	public String getCommodityUnitForPerishableMaterialRequisition(String commodityName) {
		SqlSession session = sqlSessionFactory.openSession();
		String commodityUnit = "";
		try{
			commodityUnit = session.selectOne("selectCommodityUnitForPerishableMaterialRequisition", commodityName);
		}catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println("LN632 :: MDI :: Commodity :: "+commodityName+"\tUnit :: "+commodityUnit);
		return commodityUnit;
	}
	
	/** @author Sourav.Bhadra // to get next perishable material requisition code on 18-10-2017 **/
	@Override
	public String getNextPerishableMaterialRequisitionCode() {
		SqlSession session = sqlSessionFactory.openSession();
		String nextPerishableMaterialRequisitionCode = "";
		try{
			nextPerishableMaterialRequisitionCode = session.selectOne("nextPerishableMaterialRequisitionCode");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return nextPerishableMaterialRequisitionCode;
	}
	
	/**@author Sourav.Bhadra // for submitting perishable material requisition on 18-10-2017 */
	@Override
	public String submitPerishableMaterialRequisition(PerishableMaterial perishablematerial) {
		/*System.out.println("*********************MessDAOImpl**********************");
		System.out.println("Financial Session :: "+perishablematerial.getFinancialSession());
		System.out.println("Order ID :: "+perishablematerial.getOrderId());
		System.out.println("*******************************************");
		for(PerishableMaterial pm : perishablematerial.getPerishableMaterialsList()){
			System.out.println("Order ID :: "+pm.getOrderId());
			System.out.println("Commodity :: "+pm.getCommodityName());
			System.out.println("Unit :: "+pm.getCommodityUnit());
			System.out.println("Quantity :: "+pm.getCommodityQuantity());
			System.out.println("----------------------------------");
		}*/
		SqlSession session = sqlSessionFactory.openSession();
		String status = "";
		int insertStatus;
		try{
			perishablematerial.setObjectId(encryptDecrypt.getBase64EncodedID("MessDAOImpl"));
			insertStatus = session.insert("insertPerishableMaterialRequisition", perishablematerial);
			if(insertStatus != 0){
				for(PerishableMaterial pm : perishablematerial.getPerishableMaterialsList()){
					pm.setObjectId(encryptDecrypt.getBase64EncodedID("MessDAOImpl"));
					/* modified by sourav.bhadra on 23-10-2017 */
					String com = pm.getCommodityName();
					String comCode = session.selectOne("checkCommodityExistenceInMessDailyRationCommodities", com);
					
					if(null == comCode || comCode == ""){
						Commodity commodity = new Commodity();
						commodity.setCommodityCode(pm.getCommodityName().trim());
						commodity.setCommodityDesc(pm.getCommodityUnit().trim());
						commodity.setObjectId(encryptDecrypt.getBase64EncodedID("MessDAOImpl"));
						commodity.setUpdatedBy(pm.getUpdatedBy());
						session.insert("insertIntoMessDailyRationCommodity", commodity);
					}
					session.insert("insertPerishableMaterialRequisitionDetails", pm);
				}
				status = "success";
			}
		}catch(Exception e){
			status="Fail";
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}
	
	/**@author Sourav.Bhadra // for select perishable material requisition list on 18-10-2017 */
	@Override
	public List<PerishableMaterial> getPerishableMaterialRequisitionList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<PerishableMaterial> pmList = new ArrayList<PerishableMaterial>();
		try{
			pmList = session.selectList("selectPerishableMaterialRequisitionList");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pmList;
	}
	
	/**@author Sourav.Bhadra // for select perishable material requisition individual details on 18-10-2017 */
	@Override
	public List<PerishableMaterial> viewIndividualPerishableMaterialRequisitionDetails(String orderId) {
		SqlSession session = sqlSessionFactory.openSession();
		List<PerishableMaterial> pmList = new ArrayList<PerishableMaterial>();
		try{
			pmList = session.selectList("selectIndividualPerishableMaterialRequisitionDetails", orderId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return pmList;
	}
	
	/** @author Sourav.Bhadra // to get next perishable material requisition order number on 19-10-2017 **/
	@Override
	public String getNextPerishableMaterialRequisitionOrderNumber() {
		SqlSession session = sqlSessionFactory.openSession();
		String nextPerishableMaterialRequisitionOrderNumber = "";
		try{
			nextPerishableMaterialRequisitionOrderNumber = session.selectOne("nextPerishableMaterialRequisitionOrderNumber");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return nextPerishableMaterialRequisitionOrderNumber;
	}
	
	/**@author Sourav.Bhadra // for select daily ration commodities price details for a vendor on 26-10-2017 */
	@Override
	public String getDailyRationCommoditiesPriceDetailsForAVendor(String vendorCode) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Commodity> commodityPriceList = new ArrayList<Commodity>();
		String commodityPriceDetails = "";
		try{
			commodityPriceList = session.selectList("getDailyRationCommoditiesPriceDetailsForAVendor", vendorCode);
			String commodities = "";
			String priceDetails = "";
			for(Commodity c : commodityPriceList){
				commodities += c.getCommodityName() + "*";
				priceDetails += Double.toString(c.getPurchaseRate()) + "#";
			}
			commodityPriceDetails += commodities +";"+ priceDetails;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return commodityPriceDetails;
	}

	/**
	 * @author anup.roy
	 * this method is for adding mess menu details*/
	
	@Override
	public int addMessMenuDetails(MessMenuRoutine messMenuRoutine) {
		SqlSession session = sqlSessionFactory.openSession();
		int updateStatus=0;
		try {
			logger.info("In int addMessMenuDetails of MessDaoImpl.java");
			if(null != messMenuRoutine){
				messMenuRoutine.setObjectId(encryptDecrypt.encrypt("MessDaoImpl"));
				session.insert("insertMessMenuRoutine",messMenuRoutine);
				String messMenuCode = session.selectOne("selectCodeOfMessMenuRoutine");
				if(messMenuCode != null){
					for(RoutineSlab rs : messMenuRoutine.getRoutineSlabList()){
						for(MessMenuTime messMenuTime : rs.getMessMenuTimeList()){
							messMenuTime.setObjectId(encryptDecrypt.encrypt("MessDaoImpl"));
							messMenuTime.setDaysName(rs.getRoutineSlabName());
							messMenuTime.setStatus(messMenuCode);
							updateStatus = session.insert("insertMessMenuDetails",messMenuTime);
						}
					}
				}
			}
			if(updateStatus!=0)
				session.commit();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in addMessMenuDetails() of MessDaoImpl ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return updateStatus;
	}
	
	/**@author anup.roy
	 * this method is for getting list of mess menu*/

	@Override
	public List<MessMenuRoutine> getMessMenuList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<MessMenuRoutine> messMenuRoutineList = null;
		try {
			logger.info("In List<MessMenuRoutine> getMessMenuList() of MessDaoImpl.java");
			messMenuRoutineList = session.selectList("selectMessMenuList");	
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In List<MessMenuRoutine> getMessMenuList() in MessDaoImpl : " ,e);
		} finally {
			session.close();
		}
		return messMenuRoutineList;
	}
	
	/**
	 * @author anup.roy
	 * this method is for viewing details of a menu for edit
	 */
	
	@Override
	public MessMenuRoutine getMessMenuDetails(MessMenuRoutine messMenuRoutine) {
		SqlSession session = sqlSessionFactory.openSession();		
		try {
			logger.info("In getMessMenuDetails method in messDaoimpl.java");
			messMenuRoutine = session.selectOne("selectMessMenuDetails", messMenuRoutine);			
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getMessMenuDetails method in messDaoimpl.java : " +e);
		} finally {
			session.close();
		}
		return messMenuRoutine;
	}
	
	/**
	 * @author anup.roy
	 * this method is for submit edited mess menu
	 * */
	
	@Override
	public int editMessMenuDetails(MessMenuRoutine messMenuRoutine) {
		SqlSession session = sqlSessionFactory.openSession();
		int updateStatus=0;		
		try {
			logger.info("In editMessMenuDetails method of MessDaoImpl.java");
			if(null != messMenuRoutine){
				session.update("updateMessMenuRoutine", messMenuRoutine);
				session.delete("deletemessMenuRoutine", messMenuRoutine);
				String messMenuCode = messMenuRoutine.getMessMenuRoutineCode();
				if(messMenuCode != null){
					for(RoutineSlab rs : messMenuRoutine.getRoutineSlabList()){
						for(MessMenuTime messMenuTime : rs.getMessMenuTimeList()){
							messMenuTime.setObjectId(encryptDecrypt.encrypt("MessDaoImpl"));
							messMenuTime.setDaysName(rs.getRoutineSlabName());
							messMenuTime.setStatus(messMenuCode);
							updateStatus = session.insert("insertMessMenuDetails",messMenuTime);
						}
					}
				}
			}
			if(updateStatus!=0)
				session.commit();
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in editMessMenuDetails method of MessDaoImpl.java ", e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return updateStatus;
	}

	/**@author Sourav.Bhadra // to map daily ration commodities with a vendor on 31-10-2017 */
	@Override
	public String mapCommodityVendorForPerishableMaterial(List<Commodity> commodityList, String updatedBy) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "";
		try {
			if(commodityList!=null && commodityList.size()!=0){
				int count=0;
				String vendorCode=commodityList.get(0).getVendor();
				session.delete("deleteMapCommodityVendorForPerishableMaterial", vendorCode);
				
				for(Commodity c: commodityList){
					c.setObjectId(util.getBase64EncodedID("MessDaoImpl"));
					c.setUpdatedBy(updatedBy);
					//System.out.println("LN806 :: "+c.getCommodityCode());
					//System.out.println("LN806 :: "+c.getVendor());
					//System.out.println("LN806 :: "+c.getSellingRate());
					int statusValue=session.insert("mapCommodityVendorForPerishableMaterial", c);
					
					if(c.getSellingRate()!=c.getOldSellingRate())
						session.insert("mapCommodityVendorPriceHistoryForPerishableMaterial", c);
					
					if (statusValue != 0) {
						count++;
					}else{
						status=c.getCommodityCode()+" @ "+c.getSellingRate()+" By "+c.getVendor()+" :: Mapping Failed.<br>";
					}
				}
				if(status.trim().equalsIgnoreCase("")){
					status=count+" Commodities Mapped Successfully.";
					//status=count+" Commodities Mapped Successfully With "+commodityList.get(0).getVendor();
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}

	/**@author Sourav.Bhadra // to get daily ration vendor commodity mapping on 31-10-2017 */
	@Override
	public String vendorCommodityListForPerishableMaterial(String vendorCode) {
		SqlSession session = sqlSessionFactory.openSession();
		String vcl="";
		try {
			List<Commodity> vendorCommodityList=session.selectList("vendorCommodityListForPerishableMaterial",vendorCode);
			if(vendorCommodityList!=null && vendorCommodityList.size()!=0){
				for(Commodity c:vendorCommodityList){
					vcl=vcl+c.getCommodityName()+"##"+c.getSellingRate()+"##"+c.getModelNo()+"+-+";
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		//System.out.println("LN851 DAOI :: "+vendorCode);
		//System.out.println("LN852 DAOI :: "+vcl);
		return vcl;
	}

	/**@author Sourav.Bhadra // to get daily ration commodity price history on 31-10-2017 */
	@Override
	public String vendorCommodityPriceHistoryForPerishableMaterial(Commodity commodity) {
		SqlSession session = sqlSessionFactory.openSession();
		String vcph="";
		try {
			List<Commodity> commodityList = new ArrayList<Commodity>();
			commodityList = session.selectList("vendorCommodityPriceHistoryForPerishableMaterial", commodity);
			for (Commodity c : commodityList) {
				vcph = vcph + c.getDate() + "*" + c.getSellingRate() + "#";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return vcph;
	}
	
	/**@author Sourav.Bhadra // to get daily ration vendors list on 14-11-2017 */
	@Override
	public List<Vendor> getMessDailyRationVendorList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Vendor> messDailyRationVendorsList = new ArrayList<Vendor>();
		try{
			messDailyRationVendorsList = session.selectList("selectAllMessDaqilyRationVendorsList");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return messDailyRationVendorsList;
	}
	
	/**@author Sourav.Bhadra // to submit daily ration vendor on 14-11-2017 */
	@Override
	public String submitMessDailyRationVendor(Vendor vendor) {
		SqlSession session = sqlSessionFactory.openSession();
		int vendorInsertStatus = 0;
		int ledgerInsertStatus = 0;
		int ledgerBalanceInsertStatus = 0;
		String status = "Fail";
		try{
			vendor.setVendorObjectId(encryptDecrypt.getBase64EncodedID("MessDAOImpl"));
			vendor.setVendorContactNumber(vendor.getVendorContactNo1());
			vendor.setVendorAddress(vendor.getAddress());
			vendorInsertStatus = session.insert("insertIntoMessDailyRationVendor", vendor);
			
			if(vendorInsertStatus != 0){
				Ledger messDailyRationVendortLedger = new Ledger();
				messDailyRationVendortLedger.setObjectId(encryptDecrypt.getBase64EncodedID("MessDAOImpl"));
				messDailyRationVendortLedger.setUpdatedBy(vendor.getUpdatedBy());
				messDailyRationVendortLedger.setLedgerName(vendor.getVendorName());
				messDailyRationVendortLedger.setOpeningBal(0.0);
				
				ledgerInsertStatus = session.insert("insertMessDailyRationVendorLedger", messDailyRationVendortLedger);
				
				if(ledgerInsertStatus !=0){
					ledgerBalanceInsertStatus = session.insert("insertMessDailyRationVendorLedgerBalance", messDailyRationVendortLedger);
				}else{
					status = "Fail";
				}
			}else{
				status = "Fail";
			}
			
			if((vendorInsertStatus != 0) && (ledgerInsertStatus !=0) && (ledgerBalanceInsertStatus != 0)){
				status = "Success";
			}
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			status = "Fail";
		}finally {
			session.close();
		}
		return status;
	}
	
	/**@author Sourav.Bhadra // to update a daily ration vendor's details on 17-11-2017 */
	@Override
	public String editMessDailyRationVendorDetails(Vendor vendor) {
		SqlSession session = sqlSessionFactory.openSession();
		int vendorUpdateStatus = 0;
		int ledgerUpdateStatus = 0;
		String updateStatus = "fail";
		try{
			vendorUpdateStatus = session.update("updateMessDailyRationVendorsDetails", vendor);
			
			if(vendorUpdateStatus != 0){
				ledgerUpdateStatus = session.update("updateMessDailyRationVendorsLedger", vendor);
			}
			
			if((vendorUpdateStatus != 0) && (ledgerUpdateStatus != 0)){
				updateStatus = "success";
			}
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			updateStatus = "fail";
		}
		return updateStatus;
	}
}
