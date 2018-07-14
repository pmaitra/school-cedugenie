package com.qts.icam.dao.impl.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.qts.icam.dao.inventory.InventoryDao;
import com.qts.icam.model.administrator.Approver;
import com.qts.icam.model.common.Budget;
import com.qts.icam.model.common.EmailDetails;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.finance.Transaction;
import com.qts.icam.model.finance.TransactionDetails;
import com.qts.icam.model.finance.TransactionalWorkingArea;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.model.inventory.CommodityPurchaseOrder;
import com.qts.icam.model.inventory.CommodityPurchaseOrderDetails;
import com.qts.icam.model.inventory.IndividualCommodity;
import com.qts.icam.model.ticket.ServiceType;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.mailservice.EmailSender;

@Repository
public class InventoryDaoImpl implements InventoryDao {	
	public static Logger logger = Logger.getLogger(InventoryDaoImpl.class);
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	Utility util = new Utility();

	@Autowired
	EmailSender emailSender;
	
	@Override
	public String checkCommodityName(String commodityName) {
		SqlSession session = sqlSessionFactory.openSession();
		String valid = "NO";
		try {
			//System.out.println(commodityName);
			Commodity commodity = session.selectOne("checkCommodityName", commodityName);
			//System.out.println(commodity);
			if (commodity != null) {
				//System.out.println(commodity.getCommodityCode());
				valid = "YES";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return valid;
	}



	@Override
	public String saveCommodity(Commodity commodity, String updatedBy) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "Success";
		try {
			commodity.setObjectId(util.getBase64EncodedID("InventoryDaoImpl"));
			commodity.setUpdatedBy(updatedBy);
			int statusValue=session.insert("saveCommodity", commodity);
			if (statusValue != 0) {
				/* added by sourav.bhadra on 04-09-2017 to create ledger for each commodities */
				Ledger ledger = new Ledger();
				ledger.setObjectId(util.getBase64EncodedID("InventoryDaoImpl"));
				ledger.setUpdatedBy(updatedBy);
				ledger.setLedgerCode(commodity.getCommodityName().toUpperCase());
				ledger.setLedgerName(commodity.getCommodityName().toUpperCase());
				ledger.setOpeningBal(0);
				if(commodity.getCommodityType().equalsIgnoreCase("ASSET")){
					ledger.setParentGroupName("FIXED ASSETS");
					ledger.setSubGroupName("COMMODITIES");
					session.insert("createLedgerForCommodities", ledger);
				}else{
					ledger.setParentGroupName("CURRENT ASSETS");
					ledger.setSubGroupName("STOCK");
					session.insert("createLedgerForCommodities", ledger);
				}
				
			}
		} /*catch (NullPointerException e) {
			e.printStackTrace();
			//  added by sourav.bhadra on 04-09-2017 
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} */catch (Exception e) {
			e.printStackTrace();
			/* added by sourav.bhadra on 04-09-2017 */
			status = "Failed";
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return status;
	}



	@Override
	public List<Commodity> listCommodity() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Commodity> commodityList=null;
		try {
			commodityList=session.selectList("listCommodity");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commodityList;
	}



	@Override
	public String editCommodity(Commodity commodity) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "Update Failed.";
		try {
			int statusValue=session.update("editCommodity", commodity);
			if (statusValue != 0) {
				/* added by sourav.bhadra on 04-09-2017 to update ledger for each commodities */
				Ledger ledger = new Ledger();
				ledger.setUpdatedBy(commodity.getUpdatedBy());
				ledger.setLedgerCode(commodity.getCommodityName().toUpperCase());
				ledger.setLedgerName(commodity.getCommodityName().toUpperCase());
				session.update("updateCommodityLedgers", ledger);
				status = "Update Successful";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			/* added by sourav.bhadra on 04-09-2017 */
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} catch (Exception e) {
			e.printStackTrace();
			/* added by sourav.bhadra on 04-09-2017 */
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return status;
	}



	@Override
	public List<Vendor> commodityVendorList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Vendor> commodityVendorList=null;
		try {
			commodityVendorList=session.selectList("commodityVendorList");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commodityVendorList;
	}



	@Override
	public String vendorCommodityList(String vendorCode, String financialYear) {
		SqlSession session = sqlSessionFactory.openSession();
		String vcl="";
		try {
			Vendor vendor = new Vendor();
			vendor.setVendorCode(vendorCode);
			vendor.setFinancialYear(financialYear);
			List<Commodity> vendorCommodityList=session.selectList("vendorCommodityList",vendor);
			if(vendorCommodityList!=null && vendorCommodityList.size()!=0){
				for(Commodity c:vendorCommodityList){
					vcl=vcl+c.getCommodityName()+"##"+c.getSellingRate()+"##"+c.getModelNo()+"+-+";
					/* c.getModelNo() added by sourav.bhadra on 28-07-2017 to fetch  */
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		//System.out.println(vcl);
		return vcl;
	}



	@Override
	public String mapCommodityVendor(List<Commodity> commodityList, String updatedBy) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "";
		try {
			if(commodityList!=null && commodityList.size()!=0){
				int count=0;
				int statusValue = 0;
				//String vendorCode = commodityList.get(0).getVendor();
				//String financialYear = commodityList.get(0).getDate();
				//session.delete("deleteMapCommodityVendor", vendorCode);
				
				for(Commodity commodity: commodityList){
					
					//check if commodity already present for the vendor
					
					Commodity commodityObj = session.selectOne("selectCommodityAgainstCommodityCodeVendorAndFinancialYear", commodity);
					if(null == commodityObj){
						commodity.setObjectId(util.getBase64EncodedID("InventoryDaoImpl"));
						commodity.setUpdatedBy(updatedBy);
						
						statusValue = session.insert("mapCommodityVendor", commodity);
						
						session.insert("mapCommodityVendorPriceHistory", commodity);
						
						
						if (statusValue != 0) {
							count++;
						}else{
							status = commodity.getCommodityCode()+" @ "+commodity.getSellingRate()+" By "+commodity.getVendor()+" :: Mapping Failed.<br>";
						}
					}
					
					
					
						
					
					
				}
				if(status.trim().equalsIgnoreCase("")){
					status=count+" Commodities Mapped Successfully.";
					//status=count+" Commodities Mapped Successfully With "+commodityList.get(0).getVendor();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return status;
	}



	@Override
	public String vendorCommodityPriceHistory(Commodity commodity) {
		SqlSession session = sqlSessionFactory.openSession();
		String vcph="";
		try {
			List<Commodity> commodityList = new ArrayList<Commodity>();
			commodityList = session.selectList("vendorCommodityPriceHistory", commodity);
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



	@Override
	public String getNextCommodityPurchaseOrderCode() {
		SqlSession session = sqlSessionFactory.openSession();
		String nextCommodityPurchaseOrderCode = "";
		try {
			nextCommodityPurchaseOrderCode=session.selectOne("nextCommodityPurchaseOrderCode");
			if(nextCommodityPurchaseOrderCode.trim().equalsIgnoreCase("")){
				nextCommodityPurchaseOrderCode=null;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return nextCommodityPurchaseOrderCode;
	}



	@Override
	public String remeaningCommodities(String vendorCode) {
		SqlSession session = sqlSessionFactory.openSession();
		String remeaningCommodities="";
		try {
			List<Commodity> remeaningCommoditiesList=session.selectList("remeaningCommodities",vendorCode);
			if(remeaningCommoditiesList!=null && remeaningCommoditiesList.size()!=0){
				for(Commodity c:remeaningCommoditiesList){
					remeaningCommodities = remeaningCommodities + c.getCommodityCode() + "*-*" + c.getCommodityName() + "~*~";
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return remeaningCommodities;
	}

	/**
	 * modified by sourav.bhadra
	 * changes taken on 24062017
	 * */

	@Override
	public String createCommodityPurchaseOrder(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy) {
		SqlSession session = sqlSessionFactory.openSession();
		String status="Purchase Order With Code "+commodityPurchaseOrder.getPurchaseOrderCode()+" Created Successfully.";
		try {
					//for inserting into commodity order table
					if((commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList()!=null &&
							commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList().size()!=0)){
							
							commodityPurchaseOrder.setObjId(util.getBase64EncodedID("InventoryDaoImpl"));
							commodityPurchaseOrder.setUpdatedBy(updatedBy);
							
							
							session.insert("createCommodityPurchaseOrder", commodityPurchaseOrder);
						}
						
						if(commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList()!=null &&
							commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList().size()!=0){
							
							for(CommodityPurchaseOrderDetails cpod : commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList()){
								cpod.setObjId(util.getBase64EncodedID("InventoryDaoImpl"));
								cpod.setUpdatedBy(updatedBy);
								
								session.insert("createCommodityPurchaseOrderDetails", cpod);
								
								/*Commodity c = new Commodity();
								c.setUpdatedBy(updatedBy);
								c.setObjectId(util.getBase64EncodedID("InventoryDaoImpl"));
								c.setCommodityCode(cpod.getCommodity());
								c.setSellingRate(cpod.getRate());
								c.setVendor(commodityPurchaseOrder.getVendorCode());
								
								Commodity com=session.selectOne("checkVendorCommodity", c);
								
								if(com.getCommodityId()<=0){
									session.insert("mapCommodityVendor", c);
									session.insert("mapCommodityVendorPriceHistory", c);
								}*/
							}
						}
						
						commodityPurchaseOrder.setAdvanceAmount(commodityPurchaseOrder.getAdvanceAmount());
				//}
			
			
			
		
				
			 
			 
		} catch (NullPointerException e) {
			status="Error While Creating Purchase Order With Code "+commodityPurchaseOrder.getPurchaseOrderCode();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}



	@Override
	public List<CommodityPurchaseOrder> commodityPurchaseOrderList(String status) {
		List<CommodityPurchaseOrder> commodityPurchaseOrderList=null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			commodityPurchaseOrderList=session.selectList("commodityPurchaseOrderList", status);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commodityPurchaseOrderList;
	}



	@Override
	public CommodityPurchaseOrder commodityPurchaseOrderPaymentDetails(String orderID) {
		CommodityPurchaseOrder commodityPurchaseOrder=null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			commodityPurchaseOrder=session.selectOne("commodityPurchaseOrderPaymentDetails", orderID);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commodityPurchaseOrder;
	}

	/**
	 * @author sourav.bhadra
	 * changes taken on 24062017
	 * **/

	@Override
	public String makeCommodityPayment(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy) {
		SqlSession session = sqlSessionFactory.openSession();
		String status="";
		try {
			commodityPurchaseOrder.setUpdatedBy(updatedBy);
			int updateStatus = session.update("makeCommodityPayment", commodityPurchaseOrder);
			if(updateStatus!=0){
				status="Payment Updated For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
				if(commodityPurchaseOrder.getOrderStatus().equalsIgnoreCase("CLOSED")){
					List<CommodityPurchaseOrderDetails> cpodList=session.selectList("commoditiesInPurchaseOrder", commodityPurchaseOrder.getPurchaseOrderCode());
					if(cpodList!=null && cpodList.size()!=0){
						for(CommodityPurchaseOrderDetails cpod:cpodList){
							for(int i=1;i<=cpod.getQtyReceived();i++){
								cpod.setUpdatedBy(updatedBy);
								cpod.setObjId(util.getBase64EncodedID("InventoryDaoImpl"));
								session.insert("createIndividualCommodity", cpod);
							}
						}
					}
				}
				if(commodityPurchaseOrder.getPayAmount()>0.0){
					TransactionalWorkingArea twa=new TransactionalWorkingArea();
					twa.setUpdatedBy(updatedBy);
					twa.setObjectId(util.getBase64EncodedID("InventoryDaoImpl"));
					twa.setResource(commodityPurchaseOrder.getVendorCode());
					twa.setNetAmount(commodityPurchaseOrder.getPayAmount());
					twa.setTransactionMode(commodityPurchaseOrder.getTransactionMode());
					twa.setTransactionalWorkingAreaName("COMMODITY PO");
					twa.setTransactionalWorkingAreaDesc("COMMODITY PO");
					
					twa.setDescOfTransaction("PO PENDING");
					twa.setPaidAgainst(commodityPurchaseOrder.getPurchaseOrderCode());
					twa.setDepartment(commodityPurchaseOrder.getDepartmentCode());
					twa.setAcademicYear(commodityPurchaseOrder.getLedger());
					twa.setIncomeExpense(commodityPurchaseOrder.getType());
					
					twa.setTicketCode(commodityPurchaseOrder.getTicket());
					twa.setTaskCode(commodityPurchaseOrder.getTaskDetailsCode());
					
					session.insert("insertPOInTransactionWorkingArea", twa);
				}
				Ledger ledgerObj = new Ledger();
				ledgerObj.setCurrentBal(commodityPurchaseOrder.getPayAmount());
				ledgerObj.setLedgerCode(commodityPurchaseOrder.getLedger());
				session.update("updateCurrentBalanceOfALedger", ledgerObj);
			}else{
				status="Payment Updated Failed For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
			}
		} catch (NullPointerException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			status="Payment Updated Failed For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			status="Payment Updated Failed For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
		} finally {
			session.close();
		}
		return status;
	}



	@Override
	public CommodityPurchaseOrder commodityPurchaseOrderReceiveDetails(String orderID) {
		CommodityPurchaseOrder commodityPurchaseOrder=null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			
			commodityPurchaseOrder = session.selectOne("commodityPurchaseOrderPaymentDetails", orderID);
			List<CommodityPurchaseOrderDetails> commodityPurchaseOrderDetailsList = session.selectList("commodityPurchaseOrderDetailsList", orderID);
			List<CommodityPurchaseOrderDetails> serviceDetailsList = session.selectList("serviceDetailsList", orderID);
			if(commodityPurchaseOrder != null){
				commodityPurchaseOrder.setCommodityPurchaseOrderDetailsList(commodityPurchaseOrderDetailsList);
				commodityPurchaseOrder.setServiceDetailsList(serviceDetailsList);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commodityPurchaseOrder;
	}



	@Override
	public String makeCommodityReceive(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy) {
		SqlSession session = sqlSessionFactory.openSession();
		String status="Payment Updated Failed For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
		try {
			if(commodityPurchaseOrder!=null && commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList()!=null && commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList().size()!=0){
				commodityPurchaseOrder.setUpdatedBy(updatedBy);
				int updateStatus = session.update("makeCommodityReceive", commodityPurchaseOrder);
				
				String narration = "";
				for(CommodityPurchaseOrderDetails cpod:commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList()){
					cpod.setUpdatedBy(updatedBy);
					session.update("receiveIndividualCommodity", cpod);
					
					/* added by sourav.bhadra on 11-09-2017
					 * to make a transaction for commodity receive 
					 * transaction details :: commodity debit to vendor credit */
					double totalAmt = (cpod.getIncreaseInStock() * cpod.getRate());
					
					TransactionDetails trd = new TransactionDetails();
					trd.setUpdatedBy(updatedBy);
					trd.setObjectId(util.getBase64EncodedID("InventoryDaoImpl"));
					
					//for vendor (credit)
					String vendorLedger = commodityPurchaseOrder.getLedger();
					Ledger vendorLedgerBalance = session.selectOne("selectBalanceForParentLedger", vendorLedger);
					trd.setLedger(commodityPurchaseOrder.getLedger());
					trd.setGrossAmount(vendorLedgerBalance.getOpeningBal());
					if(vendorLedgerBalance.getCurrentBal() < 0 ){
						/*if current balance is -ve then in debit side
						 * credit on debit = subtract */
						trd.setOnbasic(vendorLedgerBalance.getCurrentBal() - totalAmt);
						System.out.println("LN537 :: Vendor -ve(debit)  Final Amount :: "+trd.getOnbasic());
						session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
					}else{
						/*if current balance is -ve then in credit side
						 * credit on credit = add */
						trd.setOnbasic(vendorLedgerBalance.getCurrentBal() + totalAmt);
						System.out.println("LN543 :: Vendor +ve(credit)  Final Amount :: "+trd.getOnbasic());
						session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
					}
					TransactionDetails tranDetVen = new TransactionDetails();
					tranDetVen.setUpdatedBy(updatedBy);
					tranDetVen.setObjectId(util.getBase64EncodedID("InventoryDaoImpl"));
					tranDetVen.setLedger(commodityPurchaseOrder.getLedger());
					tranDetVen.setAmount(totalAmt);
					tranDetVen.setDebit(false);
					
					//for commodity (debit)
					String commodity = cpod.getCommodity();
					String commodityLedger = session.selectOne("selectLedgerOfACommodity", commodity);
					Ledger commodityLedgerBalance = session.selectOne("selectBalanceForParentLedger", commodityLedger);
					trd.setLedger(commodityLedger);
					trd.setGrossAmount(commodityLedgerBalance.getOpeningBal());
					
					if(commodityLedgerBalance.getCurrentBal() < 0 ){
						/*if current balance is -ve then in credit side
						 * debit on credit = subtract */
						trd.setOnbasic(commodityLedgerBalance.getCurrentBal() - totalAmt);
						System.out.println("LN557 :: Commodity -ve(credit) Final Amount :: "+trd.getOnbasic());
						session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
					}else{
						/*if current balance is -ve then in credit side
						 * debit on debit = add */
						trd.setOnbasic(commodityLedgerBalance.getCurrentBal() + totalAmt);
						System.out.println("LN563 :: Commodity +ve(debit)  Final Amount :: "+trd.getOnbasic());
						session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
					}
					TransactionDetails tranDetComm = new TransactionDetails();
					tranDetComm.setUpdatedBy(updatedBy);
					tranDetComm.setObjectId(util.getBase64EncodedID("InventoryDaoImpl"));
					tranDetComm.setLedger(commodityLedger);
					tranDetComm.setAmount(totalAmt);
					tranDetComm.setDebit(true);
					
					List<TransactionDetails> transactionDetailsList = new ArrayList<TransactionDetails>();
					transactionDetailsList.add(tranDetComm);
					transactionDetailsList.add(tranDetVen);
					
					narration = "Receiving "+cpod.getIncreaseInStock()+" "+commodity+"(s) from "+commodityPurchaseOrder.getLedger();
					Transaction transaction = new Transaction();
					transaction.setObjectId(util.getBase64EncodedID("InventoryDaoImpl"));
					transaction.setUpdatedBy(updatedBy);
					transaction.setVoucherTypeCode("PURCHASE");
					transaction.setVoucherTypeName("PURCHASE");
					transaction.setNarration(narration);
					transaction.setTrDetList(transactionDetailsList);
					
					
					//Added By Naimisha 30042018
					transaction.setJobType(commodityPurchaseOrder.getTicket());
					session.insert("createTransactionForCommodityReceive", transaction);
					
					
					/*
					System.out.println("************************************");
					System.out.println("LN568 :: Commodity :: "+commodity);
					System.out.println("LN569 :: Ledger :: "+commodityLedger);
					System.out.println("LN570 :: Qty :: "+cpod.getIncreaseInStock());
					System.out.println("LN571 :: Rate :: "+cpod.getRate());
					System.out.println("LN572 :: Total Amount :: "+totalAmt);
					System.out.println("LN573 :: Opening Balance :: "+commodityLedgerBalance.getOpeningBal());
					System.out.println("LN574 :: Current Balance :: "+commodityLedgerBalance.getCurrentBal());
					System.out.println("-------------------------------------");
					System.out.println("LN576 :: Vendor :: "+commodityPurchaseOrder.getVendorCode());
					System.out.println("LN577 :: Vendor Ledger :: "+commodityPurchaseOrder.getLedger());
					System.out.println("LN578 :: Opening Balance :: "+vendorLedgerBalance.getOpeningBal());
					System.out.println("LN579 :: Current Balance :: "+vendorLedgerBalance.getCurrentBal());
					System.out.println("************************************");
					*/
				}
				
				if(updateStatus!=0){
					status="Commodities Received For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
					if(commodityPurchaseOrder.getOrderStatus().equalsIgnoreCase("CLOSED")){
						List<CommodityPurchaseOrderDetails> cpodList=session.selectList("commoditiesInPurchaseOrder", commodityPurchaseOrder.getPurchaseOrderCode());
						if(cpodList!=null && cpodList.size()!=0){
							for(CommodityPurchaseOrderDetails cpod:cpodList){
								for(int i=1;i<=cpod.getQtyReceived();i++){
									cpod.setUpdatedBy(updatedBy);
									cpod.setObjId(util.getBase64EncodedID("InventoryDaoImpl"));
									session.insert("createIndividualCommodity", cpod);
								}
							}
						}
					}
				}else{
					status="Commodities Received Failed For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
				}
			}
		} catch (NullPointerException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			status="Payment Updated Failed For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			status="Payment Updated Failed For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
		} finally {
			session.close();
		}
		return status;
	}


	@Override
	public String getIndividualNotAllotedCommodity(String commodityCode) {
		SqlSession session = sqlSessionFactory.openSession();
		String individualCommodities="";
		try {
			List<IndividualCommodity> individualCommodityList=session.selectList("getIndividualNotAllotedCommodity",commodityCode);
			if(individualCommodityList!=null && individualCommodityList.size()!=0){
				for(IndividualCommodity ic:individualCommodityList){
					individualCommodities = individualCommodities + ic.getIndividualCommodityCode() + "*-*" + ic.getModelNo() + "*-*" + ic.getWarranty() + "~*~";
				}
			}
			individualCommodities=individualCommodities+"@@@";
			List<Resource> resourceList=session.selectList("getTeachersAndNotTeachers");
			if(resourceList!=null && resourceList.size()!=0){
				for(Resource r:resourceList){
					individualCommodities = individualCommodities + r.getUserId() + "*-*" + r.getName() + "~*~";
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return individualCommodities;

	}



	@Override
	public String allotCommodity(List<IndividualCommodity> individualCommodityList, String updatedBy) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "Allocation Successful";
		try {
			if(individualCommodityList!=null && individualCommodityList.size()!=0){
				for(IndividualCommodity ic: individualCommodityList){
					ic.setObjectId(util.getBase64EncodedID("InventoryDaoImpl"));
					ic.setUpdatedBy(updatedBy);
					
					session.update("allotCommodity", ic);
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			status="Allocation Failed";
		} catch (Exception e) {
			e.printStackTrace();
			status="Allocation Failed";
		} finally {
			session.close();
		}
		return status;
	}



	@Override
	public String getIndividualAllotedCommodity(String commodityCode) {
		SqlSession session = sqlSessionFactory.openSession();
		String individualAllotedCommodities="";
		try {
			List<IndividualCommodity> individualCommodityList=session.selectList("getIndividualAllotedCommodity",commodityCode);
			if(individualCommodityList!=null && individualCommodityList.size()!=0){
				for(IndividualCommodity ic:individualCommodityList){
					individualAllotedCommodities = individualAllotedCommodities + ic.getIndividualCommodityCode() + "*-*" + ic.getModelNo() + "*-*" + ic.getWarranty() + "*-*" + ic.getAllotedOn() + "*-*" + ic.getAllotedTo() + "*-*" + ic.getStatus() + "~*~";
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return individualAllotedCommodities;
	}



	@Override
	public String deAllotCommodity(List<IndividualCommodity> individualCommodityList, String updatedBy) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "De-Allocation Successful";
		try {
			if(individualCommodityList!=null && individualCommodityList.size()!=0){
				for(IndividualCommodity ic: individualCommodityList){
					ic.setUpdatedBy(updatedBy);
					
					session.update("deAllotCommodity", ic);
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			status="De-Allocation Failed";
		} catch (Exception e) {
			e.printStackTrace();
			status="De-Allocation Failed";
		} finally {
			session.close();
		}
		return status;
	}



	@Override
	public List<Commodity> getAssetCommodity() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Commodity> commodityList=null;
		try {
			commodityList=session.selectList("getAssetCommodity");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commodityList;
	}



	@Override
	public String getIndividualCommodityList(String commodityCode) {
		SqlSession session = sqlSessionFactory.openSession();
		String individualCommodities="";
		try {
			List<IndividualCommodity> individualCommodityList=session.selectList("getIndividualCommodityList",commodityCode);
			if(individualCommodityList!=null && individualCommodityList.size()!=0){
				for(IndividualCommodity ic:individualCommodityList){
					individualCommodities = individualCommodities + ic.getIndividualCommodityCode()+"~*~";
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return individualCommodities;
	}



	@Override
	public String getCommodityAllotmentHistory(String individualCommodity) {
		SqlSession session = sqlSessionFactory.openSession();
		String allotmentHistory="";
		try {
			List<IndividualCommodity> individualCommodityList=session.selectList("getCommodityAllotmentHistory",individualCommodity);
			if(individualCommodityList!=null && individualCommodityList.size()!=0){
				for(IndividualCommodity ic:individualCommodityList){
					allotmentHistory = allotmentHistory + ic.getAllotedTo() + "*-*" + ic.getAllotedOn() + "*-*" + ic.getAllotedBy() + "*-*" + ic.getReturnedTo() + "*-*" + ic.getReturnedOn() + "*-*" + ic.getComment() + "~*~";
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return allotmentHistory;
	}
	
	@Override
	public String getIndividualCommodity(String commodityCode) {
		SqlSession session = sqlSessionFactory.openSession();
		String individualCommodities="";
		try {
			List<IndividualCommodity> individualCommodityList=session.selectList("getIndividualCommodity",commodityCode);
			if(individualCommodityList!=null && individualCommodityList.size()!=0){
				for(IndividualCommodity ic:individualCommodityList){
					individualCommodities = individualCommodities + ic.getIndividualCommodityCode() + "*-*" + ic.getPurchaseDate() + "*-*" + ic.getModelNo() + "*-*" + ic.getWarranty() + "*-*" + ic.getDepreciation() +"~*~";
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return individualCommodities;
	}



	@Override
	public String updateIndividualCommodityDetails(List<IndividualCommodity> individualCommodityList, String updatedBy) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "Update Successful";
		try {
			if(individualCommodityList!=null && individualCommodityList.size()!=0){
				for(IndividualCommodity ic: individualCommodityList){
					ic.setUpdatedBy(updatedBy);
					
					session.update("setWarrantyAndModelNoForCommodity", ic);
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			status="Update Failed";
		} catch (Exception e) {
			e.printStackTrace();
			status="Update Failed";
		} finally {
			session.close();
		}
		return status;
	}



	@Override
	public String retireCommodity(List<IndividualCommodity> individualCommodityList, String updatedBy) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "Retire Successful";
		try {
			if(individualCommodityList!=null && individualCommodityList.size()!=0){
				for(IndividualCommodity ic: individualCommodityList){
					ic.setUpdatedBy(updatedBy);
					
					session.update("retireIndividualCommodity", ic);
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			status="Retire Failed";
		} catch (Exception e) {
			e.printStackTrace();
			status="Retire Failed";
		} finally {
			session.close();
		}
		return status;
	}



	@Override
	public String closeCommodityOrder(String orderID, String updatedBy) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "Commodity Order With Order Code "+orderID+ "Closed Successfully";
		try {
			List<CommodityPurchaseOrderDetails> cpodList=session.selectList("commoditiesInPurchaseOrder", orderID);
			if(cpodList!=null && cpodList.size()!=0){
				for(CommodityPurchaseOrderDetails cpod:cpodList){
					for(int i=1;i<=cpod.getQtyReceived();i++){
						cpod.setUpdatedBy(updatedBy);
						cpod.setObjId(util.getBase64EncodedID("InventoryDaoImpl"));
						session.insert("createIndividualCommodity", cpod);
					}
				}
			}
			CommodityPurchaseOrder cpo =new CommodityPurchaseOrder();
			cpo.setUpdatedBy(updatedBy);
			cpo.setOrderStatus("CLOSED");
			cpo.setPurchaseOrderCode(orderID);
			
			session.update("closeCommodityOrder", cpo);
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			status = "Commodity Order With Order Code "+orderID+ "Closing Failed";
		} catch (Exception e) {
			e.printStackTrace();
			status = "Commodity Order With Order Code "+orderID+ "Closing Failed";
		} finally {
			session.close();
		}
		return status;
	}

	
	@Override
	public List<Vendor> getBankDetailsList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Vendor> bankDetailList = null;
		try {
			bankDetailList = session.selectList("selectBankDetails");
		}catch (Exception e) {
			logger.error("getBankDetails() In BackOfficeDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return bankDetailList;
	}
	
	/**
	 * @author sourav.bhadra
	 * chnages taken on 27062017*/
	
	@Override
	public String getVendorBankAllDetails(String vendorName) {
		logger.info("In getBankDetails() method of FinanceDaoImpl");
		String bankRecord = "";
		SqlSession session =sqlSessionFactory.openSession();
		try {
			Vendor bankDetails = new Vendor();
			bankDetails = session.selectOne("getVendorBankAllDetailsList",vendorName);
			bankRecord += bankDetails.getBankName()+"~"+bankDetails.getAccountNo()+"~"+bankDetails.getBankIfscCode()+"~"+bankDetails.getBankCode()+"~"+bankDetails.getBankLocation();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("In getGroupTypeList() method of FinanceDaoImpl", e);
		} finally {
			session.close();
		}
		return bankRecord;
	}

	/**@author Saif.Ali
	 * date-10/07/2017**/
	@Override
	public String getNextmessCommodityPurchaseOrderCode() {
		SqlSession session = sqlSessionFactory.openSession();
		String getNextmessCommodityPurchaseOrderCode = "";
		try {
			getNextmessCommodityPurchaseOrderCode=session.selectOne("nextMessCommodityPurchaseOrderCode");
			if(getNextmessCommodityPurchaseOrderCode.trim().equalsIgnoreCase("")){
				getNextmessCommodityPurchaseOrderCode=null;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return getNextmessCommodityPurchaseOrderCode;
	}
	
	/**@author Saif.Ali
	 * DATE-11/07/2017
	 * used to add the mess commodity purchase order into the database*/
	@Override
	public String createMessCommodityPurchaseOrder(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy) {
		SqlSession session = sqlSessionFactory.openSession();
		String status="Purchase Order With Code "+commodityPurchaseOrder.getPurchaseOrderCode()+" Created Successfully.";
		try {
			if((commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList()!=null &&
				commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList().size()!=0)	||
				(commodityPurchaseOrder.getServiceDetailsList()!=null && 
				commodityPurchaseOrder.getServiceDetailsList().size()!=0)){
				
				commodityPurchaseOrder.setObjId(util.getBase64EncodedID("InventoryDaoImpl"));
				commodityPurchaseOrder.setUpdatedBy(updatedBy);
				
				session.insert("createMessCommodityPurchaseOrder", commodityPurchaseOrder);
			}
			
			if(commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList()!=null &&
				commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList().size()!=0){
				
				for(CommodityPurchaseOrderDetails cpod : commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList()){
					cpod.setObjId(util.getBase64EncodedID("InventoryDaoImpl"));
					cpod.setUpdatedBy(updatedBy);
					
					session.insert("createMessCommodityPurchaseOrderDetails", cpod);
					
					Commodity c = new Commodity();
					c.setUpdatedBy(updatedBy);
					c.setObjectId(util.getBase64EncodedID("InventoryDaoImpl"));
					c.setCommodityCode(cpod.getCommodity());
					c.setSellingRate(cpod.getRate());
					c.setVendor(commodityPurchaseOrder.getVendorCode());
					
					Commodity com=session.selectOne("checkVendorCommodity", c);
					
					if(com.getCommodityId()<=0){
						session.insert("mapCommodityVendor", c);
						session.insert("mapCommodityVendorPriceHistory", c);
					}
				}
			}
			
			commodityPurchaseOrder.setAdvanceAmount(commodityPurchaseOrder.getAdvanceAmount());
			if(commodityPurchaseOrder.getAdvanceAmount()>0.0){
				TransactionalWorkingArea twa=new TransactionalWorkingArea();
				twa.setUpdatedBy(updatedBy);
				twa.setObjectId(util.getBase64EncodedID("InventoryDaoImpl"));
				twa.setResource(commodityPurchaseOrder.getVendorCode());
				twa.setNetAmount(commodityPurchaseOrder.getAdvanceAmount());
				twa.setTransactionMode(commodityPurchaseOrder.getTransactionMode());
				twa.setTransactionalWorkingAreaName("MESS COMMODITY PO");
				twa.setTransactionalWorkingAreaDesc("MESS COMMODITY PO");
				
				twa.setDescOfTransaction("PO");
				twa.setPaidAgainst(commodityPurchaseOrder.getPurchaseOrderCode());
				twa.setDepartment(commodityPurchaseOrder.getDepartmentCode());
				twa.setAcademicYear(commodityPurchaseOrder.getLedger());
				twa.setIncomeExpense("EXPENSE");
				session.insert("insertPOInTransactionWorkingArea", twa);
			}
		} catch (NullPointerException e) {
			status="Error While Creating Purchase Order With Code "+commodityPurchaseOrder.getPurchaseOrderCode();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return status;
	}
	
	@Override
	public List<CommodityPurchaseOrder> messCommodityPurchaseOrderList(String status) {
		List<CommodityPurchaseOrder> commodityPurchaseOrderList=null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			commodityPurchaseOrderList=session.selectList("messCommodityPurchaseOrderList", status);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commodityPurchaseOrderList;
	}
	
	@Override
	public CommodityPurchaseOrder messCommodityPurchaseOrderReceiveDetails(String orderID) {
		CommodityPurchaseOrder commodityPurchaseOrder=null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			
			commodityPurchaseOrder=session.selectOne("messCommodityPurchaseOrderPaymentDetails", orderID);
			List<CommodityPurchaseOrderDetails> commodityPurchaseOrderDetailsList=session.selectList("messCommodityPurchaseOrderDetailsList", orderID);
			List<CommodityPurchaseOrderDetails> serviceDetailsList=session.selectList("serviceDetailsList", orderID);
			if(commodityPurchaseOrder != null){
				commodityPurchaseOrder.setCommodityPurchaseOrderDetailsList(commodityPurchaseOrderDetailsList);
				commodityPurchaseOrder.setServiceDetailsList(serviceDetailsList);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commodityPurchaseOrder;
	}
	
	/**@author Saif.Ali
	 * Date- 12/07/2017
	 * Used to receive the ordered items from the mess*/
	@Override
	public String makeMessCommodityReceive(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy) {
		SqlSession session = sqlSessionFactory.openSession();
		String status="Payment Updated Failed For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
		try {
			if(commodityPurchaseOrder!=null && commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList()!=null && commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList().size()!=0){
				commodityPurchaseOrder.setUpdatedBy(updatedBy);
				
				int updateStatus = session.update("makeMessCommodityReceive", commodityPurchaseOrder);
				for(CommodityPurchaseOrderDetails cpod:commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList()){
					cpod.setUpdatedBy(updatedBy);
					session.update("receiveIndividualCommodity", cpod);
				}
				if(updateStatus!=0){
					status="Commodities Received For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
					if(commodityPurchaseOrder.getOrderStatus().equalsIgnoreCase("CLOSED")){
						
						List<CommodityPurchaseOrderDetails> cpodList=session.selectList("commoditiesInMessPurchaseOrder", commodityPurchaseOrder.getPurchaseOrderCode());
						if(cpodList!=null && cpodList.size()!=0){
							for(CommodityPurchaseOrderDetails cpod:cpodList){
								for(int i=1;i<=cpod.getQtyReceived();i++){
									cpod.setUpdatedBy(updatedBy);
									cpod.setObjId(util.getBase64EncodedID("InventoryDaoImpl"));
									session.insert("createIndividualCommodity", cpod);
								}
							}
						}
					}
				}else{
					status="Commodities Received Failed For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
				}
			}
		} catch (NullPointerException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			status="Payment Updated Failed For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			status="Payment Updated Failed For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
		} finally {
			session.close();
		}
		return status;
	}
	
	@Override
	public String makeMessCommodityPayment(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy) {
		SqlSession session = sqlSessionFactory.openSession();
		String status="";
		try {
			commodityPurchaseOrder.setUpdatedBy(updatedBy);
			int updateStatus = session.update("makeMessCommodityPayment", commodityPurchaseOrder);
			if(updateStatus!=0){
				status="Payment Updated For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
				if(commodityPurchaseOrder.getOrderStatus().equalsIgnoreCase("CLOSED")){
					List<CommodityPurchaseOrderDetails> cpodList=session.selectList("commoditiesInPurchaseOrder", commodityPurchaseOrder.getPurchaseOrderCode());
					if(cpodList!=null && cpodList.size()!=0){
						for(CommodityPurchaseOrderDetails cpod:cpodList){
							for(int i=1;i<=cpod.getQtyReceived();i++){
								cpod.setUpdatedBy(updatedBy);
								cpod.setObjId(util.getBase64EncodedID("InventoryDaoImpl"));
								session.insert("createIndividualCommodity", cpod);
							}
						}
					}
				}
				if(commodityPurchaseOrder.getPayAmount()>0.0){
					TransactionalWorkingArea twa=new TransactionalWorkingArea();
					twa.setUpdatedBy(updatedBy);
					twa.setObjectId(util.getBase64EncodedID("InventoryDaoImpl"));
					twa.setResource(commodityPurchaseOrder.getVendorCode());
					twa.setNetAmount(commodityPurchaseOrder.getPayAmount());
					twa.setTransactionMode(commodityPurchaseOrder.getTransactionMode());
					twa.setTransactionalWorkingAreaName("MESS COMMODITY PO");
					twa.setTransactionalWorkingAreaDesc("MESS COMMODITY PO");
					
					twa.setDescOfTransaction("PO PENDING");
					twa.setPaidAgainst(commodityPurchaseOrder.getPurchaseOrderCode());
					twa.setDepartment(commodityPurchaseOrder.getDepartmentCode());
					twa.setAcademicYear(commodityPurchaseOrder.getLedger());
					twa.setIncomeExpense("EXPENSE");
					session.insert("insertPOInTransactionWorkingArea", twa);
				}
				Ledger ledgerObj = new Ledger();
				ledgerObj.setCurrentBal(commodityPurchaseOrder.getPayAmount());
				ledgerObj.setLedgerCode(commodityPurchaseOrder.getLedger());
				session.update("updateCurrentBalanceOfALedger", ledgerObj);
			}else{
				status="Payment Updated Failed For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
			}
		} catch (NullPointerException e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			status="Payment Updated Failed For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			status="Payment Updated Failed For Purchase Order Code : "+commodityPurchaseOrder.getPurchaseOrderCode();
		} finally {
			session.close();
		}
		return status;
	}
	
	/* added by sourav.bhadra on 27062017
	 * to get department budget details */
	@Override
	public String getDepartmentBudgetDetails(String departmentCode) {
		logger.info("In getDepartmentBudgetDetails() method of InventoryDaoImpl");
		String departmentBudgetDetails = "";
		SqlSession session =sqlSessionFactory.openSession();
		try {
			Budget departmentBudget = new Budget();
			departmentBudget = session.selectOne("getDepartmentBudgetDetails",departmentCode);
			/* modified by sourav.bhadra on 17-08-2017 to avoid null values of departmental budget */
			if(null == departmentBudget){
				departmentBudgetDetails += "0.0"+"~"+"0.0"+"~"+"0.0";
			}else{
				//departmentBudgetDetails += String.format(".00f", departmentBudget.getActualIncome())+"~"+departmentBudget.getTotalExpence().toString()+"~"+departmentBudget.getReserveFund().toString();
				departmentBudgetDetails += String.format("%.0f",departmentBudget.getActualIncome())+"~"+
				String.format("%.0f",departmentBudget.getTotalExpence())+"~"+String.format("%.0f", departmentBudget.getReserveFund());
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("In getDepartmentBudgetDetails() method of InventoryDaoImpl", e);
		} finally {
			session.close();
		}
		return departmentBudgetDetails;
	}


	/* added by sourav.bhadra on 28-07-2017
	 * to get commodity unit list */
	@Override
	public List<Commodity> getCommodityUnitList() {
		logger.info("In getCommodityUnitList() method of InventoryDaoImpl");
		SqlSession session =sqlSessionFactory.openSession();
		List<Commodity> commodityUnitList = null;
		try {
			commodityUnitList = session.selectList("selectCommodityUnitsList");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("In getCommodityUnitList() method of InventoryDaoImpl", e);
		} finally {
			session.close();
		}
		return commodityUnitList;
	}


	/* added by sourav.bhadra on 28-07-2017
	 * to get commodity unit for PO */
	@Override
	public String getCommodityUnitForPO(String commodity) {
		logger.info("In getCommodityUnitList() method of InventoryDaoImpl");
		SqlSession session =sqlSessionFactory.openSession();
		String commodityUnit = "";
		try {
			commodityUnit = session.selectOne("selectCommodityUnitForPO", commodity);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("In getCommodityUnitList() method of InventoryDaoImpl", e);
		} finally {
			session.close();
		}
		return commodityUnit;
	}


	/* added by sourav.bhadra on 31-07-2017
	 * to get vendor's ledger for PO */
	@Override
	public String getVendorsLedgerForCommodityPO(String vendorCode) {
		logger.info("In getCommodityUnitList() method of InventoryDaoImpl");
		SqlSession session =sqlSessionFactory.openSession();
		String vendorsLedger = ""; //System.out.println("LN907...DAOImpl :: "+vendorCode);
		try {
			vendorsLedger = session.selectOne("selectvendorsLedgerForPO", vendorCode); //System.out.println("LN910...DAOImpl :: "+vendorsLedger);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("In getCommodityUnitList() method of InventoryDaoImpl", e);
		} finally {
			session.close();
		}
		return vendorsLedger;
	}

//Added By Naimisha 24042018

	@Override
	public String getNextCommodityRequisionCode() {
		SqlSession session = sqlSessionFactory.openSession();
		String nextCommodityRequisitionCode = "";
		try {
			nextCommodityRequisitionCode=session.selectOne("nextCommodityRequisitionCode");
			if(nextCommodityRequisitionCode.trim().equalsIgnoreCase("")){
				nextCommodityRequisitionCode=null;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return nextCommodityRequisitionCode;
	}



	@Override
	public String createCommodityRequisition(CommodityPurchaseOrder commodityPurchaseOrder, String userId) {
		SqlSession session = sqlSessionFactory.openSession();
		String status="Requisition With Code "+commodityPurchaseOrder.getPurchaseOrderCode()+" Created Successfully.";
		int insertStatus1 = 0;
		int insertStatus2 = 0;
		try {
			//for inserting into commodity order table
			if((commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList()!=null &&
					commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList().size()!=0)){
					
					commodityPurchaseOrder.setObjId(util.getBase64EncodedID("InventoryDaoImpl"));
					commodityPurchaseOrder.setUpdatedBy(userId);
					
					
					insertStatus1 = session.insert("createCommodityRequisition", commodityPurchaseOrder);
					if(commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList()!=null &&
							commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList().size()!=0){
							
							for(CommodityPurchaseOrderDetails cpod : commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList()){
								cpod.setObjId(util.getBase64EncodedID("InventoryDaoImpl"));
								cpod.setUpdatedBy(userId);
								cpod.setTaskCode(commodityPurchaseOrder.getPurchaseId());
								cpod.setTickeCode(commodityPurchaseOrder.getTicket());
								insertStatus2 = session.insert("createCommodityRequisitionDetails", cpod);
								
							}
						}
					
					//Naimisha you have to change here on monday
					if(insertStatus1 != 0 && insertStatus2 != 0){
						
						List<String>tableNameList = null;
						String taskDetailsCode = commodityPurchaseOrder.getPurchaseId();
						
						//fetch table names for a task
						tableNameList = session.selectList("selectTableListForATask", taskDetailsCode);
						
						if(null != tableNameList && tableNameList.size() != 0){
							for(String tableName : tableNameList){
								Ticket ticket = new Ticket();
								ticket.setTicketObjectId(commodityPurchaseOrder.getObjId());
								ticket.setUpdatedBy(commodityPurchaseOrder.getUpdatedBy());
								ticket.setTicketCode(commodityPurchaseOrder.getTicket());
								ticket.setTicketDesc(commodityPurchaseOrder.getPurchaseId());
								ticket.setTableName(tableName);
								ticket.setStatus("INSERT");
								
								//get last inserted record
								
								String lastInsertedRecId = session.selectOne("selectLastInsertedRecId",ticket);
								
								
								ticket.setTicketRecId(lastInsertedRecId);
								
								int insertStatus = session.insert("insertIntoTicketTaskTablenameMapping",ticket);
							}
							
						}
						
					}
				}
				
				
				
				//commodityPurchaseOrder.setAdvanceAmount(commodityPurchaseOrder.getAdvanceAmount());
				
		} catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return status;
	}



	@Override
	public List<CommodityPurchaseOrder> commodityRequisitionList() {
		List<CommodityPurchaseOrder> commodityRequisitionList=null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			commodityRequisitionList = session.selectList("commodityRequisitionList");
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commodityRequisitionList;
	}



	@Override
	public CommodityPurchaseOrder getCommodityRequisitionDetails(String requisitionCode) {
		CommodityPurchaseOrder commodityRequisition = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			commodityRequisition = session.selectOne("commodityRequisitionDetails",requisitionCode);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commodityRequisition;
	}


//Added by Naimisha 03052018
	@Override
	public List<Commodity> commodityListMappedForAFinancialYear(String financialYear) {
		List<Commodity> commodityList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			commodityList = session.selectList("commodityListMappedForAFinancialYear",financialYear);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commodityList;
	}

	
	@Override
	public List<Commodity> getVendorListMappedForAFinancialYearAndCommodity(Commodity commodity) {
		List<Commodity> commodityList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			commodityList = session.selectList("getVendorListMappedForAFinancialYearAndCommodity",commodity);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commodityList;
	}


//Added By naimisha ghosh 07052018
	@Override
	public String submitTenderWisePricing(Vendor vendor) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "success";
		int insertStatus1= 0;
		try {
			vendor.setVendorObjectId(util.getBase64EncodedID("InventoryDaoImpl"));
			
			int statusValue = session.insert("saveTenderWisePricing", vendor);
			
			Vendor vendorobj = session.selectOne("selectVendors",vendor);
			if (statusValue != 0) {
				
				String ledgerName = vendorobj.getVendorName().toUpperCase().trim();
				Ledger ledgerObj = session.selectOne("checkLedgerNameExistOrNot", ledgerName);
				
				if(null == ledgerObj){
					Ledger ledger = new Ledger();
					
					Vendor vendor1=session.selectOne("selectVendorRecordForLedgerEntry",vendorobj);
					
					ledger.setObjectId(util.getBase64EncodedID("InventoryDaoImpl"));
					ledger.setLedgerName(vendorobj.getVendorName().toUpperCase().trim());
					
					ledger.setOpeningDrCr(vendor1.getVedorRecId());
					insertStatus1 = session.insert("insertinLedgerForVendor", ledger);
					
					if(insertStatus1 != 0)
						statusValue = session.update("editinLedgerBalanceForVendor", ledger);
				}
				
				
					
				
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return status;
	}



	@Override
	public String vendorCommodityListAccordingToTender(String vendorCode, String financialYear) {
		SqlSession session = sqlSessionFactory.openSession();
		String vcl="";
		try {
			Vendor vendor = new Vendor();
			vendor.setVendorCode(vendorCode);
			vendor.setFinancialYear(financialYear);
			List<Commodity> vendorCommodityList=session.selectList("vendorCommodityListAccordingToTender",vendor);
			if(vendorCommodityList!=null && vendorCommodityList.size()!=0){
				for(Commodity c:vendorCommodityList){
					vcl=vcl+c.getCommodityName()+"##"+c.getSellingRate()+"##"+c.getModelNo()+"+-+";
					
				}
			}
			vcl = vcl + "@"+ vendorCommodityList.get(0).getVendor();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		//System.out.println(vcl);
		return vcl;
	}



	@Override
	public List<Commodity> getVendorsListFromItemTenderPricing(Commodity commodityObj) {
		List<Commodity> commodityList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			commodityList = session.selectList("getVendorsListFromItemTenderPricing",commodityObj);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commodityList;
	}



	@Override
	public List<CommodityPurchaseOrder> commodityRequisitionListNotPresentInPO() {
		List<CommodityPurchaseOrder> commodityList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			commodityList = session.selectList("commodityRequisitionListNotPresentInPO");
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commodityList;
	}



	@Override
	public List<Task> getTaskNoListForAUser(String userId) {
		List<Task> taskList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			taskList = session.selectList("getTaskNoListForAUser",userId);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return taskList;
	}



	@Override
	public Ticket getTicketNoAgainstTaskCode(String taskCode) {
		Ticket ticket = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			ticket = session.selectOne("selectTicketNoAgainstTaskCode",taskCode);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return ticket;
	}
	
	@Override
	public List<Commodity> getAllCommodityName(){
		SqlSession session = sqlSessionFactory.openSession();
		List<Commodity> commodityList=null;
		try {
			commodityList=session.selectList("getAllCommodityName");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commodityList;
	}
	
	@Override
	public String getCommodityUnit(String commodityName){
		SqlSession session = sqlSessionFactory.openSession();
		String commodityUnit=null;
		try {
			commodityUnit=session.selectOne("getCommodityUnit", commodityName);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return commodityUnit;
	}
}

	

