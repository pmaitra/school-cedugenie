package com.qts.icam.dao.impl.finance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.qts.icam.dao.finance.FinanceDao;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.LedgerWiseView;
import com.qts.icam.model.common.ProfitAndLoss;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.finance.FinancialYear;
import com.qts.icam.model.finance.Group;
import com.qts.icam.model.common.Ledger;

import com.qts.icam.model.finance.BalanceSheet;
import com.qts.icam.model.common.Budget;
import com.qts.icam.model.common.Daybook;
import com.qts.icam.model.common.DelarDetails;
import com.qts.icam.model.finance.DesignationSalaryDetails;
import com.qts.icam.model.finance.DisbursementSalaryDetails;
import com.qts.icam.model.finance.SalaryBreakUp;
import com.qts.icam.model.finance.SalaryDisbursementList;
import com.qts.icam.model.finance.StaffSalaryDetails;
import com.qts.icam.model.common.TemplateLedgerMapping;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.finance.Transaction;
import com.qts.icam.model.finance.TransactionDetails;
import com.qts.icam.model.finance.Brs;
import com.qts.icam.model.finance.CashBook;
import com.qts.icam.model.finance.FeesLedgerMapping;
import com.qts.icam.model.finance.IncomeAndExpense;
import com.qts.icam.model.finance.Passbook;
import com.qts.icam.model.finance.StudentFeesPayment;
import com.qts.icam.model.finance.StudentFeesPaymentDetails;
import com.qts.icam.model.finance.Tax;
import com.qts.icam.model.finance.Transaction;
import com.qts.icam.model.finance.TransactionDetails;
import com.qts.icam.model.finance.TransactionWorkingAreaDetails;
import com.qts.icam.model.finance.TransactionalWorkingArea;
import com.qts.icam.model.finance.TrialBalance;
import com.qts.icam.model.finance.VendorPayment;
import com.qts.icam.model.finance.VoucherType;
import com.qts.icam.model.inventory.IndividualCommodity;
import com.qts.icam.model.ticket.ServiceType;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.service.finance.FinanceService;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;

@Repository
public class FinanceDaoImpl implements FinanceDao {
	
	public static Logger logger = Logger.getLogger("FinanceDaoImpl.class");

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Autowired
	FinanceService financeService;
	
	@Autowired
	EncryptDecrypt encryptDecrypt; 
	
	
	
	@Override
	public List<Group> getGroupTypeList() {
		logger.info("In getGroupTypeList() method of FinanceDaoImpl");
		List<Group> GroupTypeList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			GroupTypeList = session.selectList("getGroupTypeList");
		} catch (Exception e) {
			logger.error("In getGroupTypeList() method of FinanceDaoImpl", e);
		} finally {
			session.close();
		}
		return GroupTypeList;
		
		
	}



	@Override
	public List<Group> getGroupList() {
		logger.info("In getGroupList() method of FinanceDaoImpl");
		List<Group> groupList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			groupList = session.selectList("getGroupList");
		} catch (Exception e) {
			logger.error("In getGroupList() method of FinanceDaoImpl", e);
		} finally {
			session.close();
		}
		
		return groupList;
	
	}
	
	@Override
	public List<Group> getChildGroupList(String groupCode) {
		logger.info("In getGroupList() method of FinanceDaoImpl");
		List<Group> groupList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			groupList = session.selectList("getChildGroupList",groupCode);
		} catch (Exception e) {
			logger.error("In getGroupList() method of FinanceDaoImpl", e);
		} finally {
			session.close();
		}
		
		return groupList;
	
	}
	
	@Override
	public List<Group> getSubChildList(String child) {
		logger.info("In getGroupList() method of FinanceDaoImpl");
		List<Group> groupList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			groupList = session.selectList("getSubChildGroupList",child);
		} catch (Exception e) {
			logger.error("In getGroupList() method of FinanceDaoImpl", e);
		} finally {
			session.close();
		}
		
		return groupList;
	
	}
	
	@Override
	public List<Group> getParentGroup() {
		logger.info("In getGroupList() method of FinanceDaoImpl");
		List<Group> groupList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			groupList = session.selectList("getParentGroupList");
		} catch (Exception e) {
			logger.error("In getGroupList() method of FinanceDaoImpl", e);
		} finally {
			session.close();
		}
		
		return groupList;
	
	}



	@Override
	public String createGroup(Group group) {
		String status = "Insertion Failed";
		
		SqlSession session =sqlSessionFactory.openSession();
		try{
			group.setObjectId(encryptDecrypt.getBase64EncodedID("FinanceDaoImpl"));
			group.setGroupCode(group.getGroupName().trim().toUpperCase());
			group.setGroupName(group.getGroupName().trim().toUpperCase());
			session.insert("createGroup", group);
			status="Insertion successful";
			session.commit();			
		}catch(Exception e){			
			status="Insertion Failed";
			logger.error("In Create Group Method FinanceDaoImpl",e);
		}finally {
			session.close();
		}
		
		return status;
	}



	@Override
	public List<Ledger> getLedgerList() {
		logger.info("In getLedgerList() method of FinanceDaoImpl");
		List<Ledger> ledgerList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			ledgerList = session.selectList("getLedgerList");
			/* added by sourav.bhadra on 11-08-2017
			 * to fetch ledger wise current and opening balance */
			for(Ledger l : ledgerList){
				String ledgerCode = l.getLedgerCode();
				Ledger ledgerBalance = session.selectOne("getCurrentAndOpeningBalanceForALedger", ledgerCode);
				l.setCurrentBal(ledgerBalance.getCurrentBal());
				l.setOpeningBal(ledgerBalance.getOpeningBal());
			}
		}catch (Exception e){
			logger.error("In getLedgerList() method of FinanceDaoImpl",e);
		} finally {
			session.close();
		}
		
		return ledgerList;
	
	}

	/**
	 * modified by sourav.bhadra 17062017*/

	@Override
	public String createLedger(Ledger ledger) {
		String status ="Insertion Failed";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			ledger.setObjectId(encryptDecrypt.getBase64EncodedID("FinanceDaoImpl"));
			ledger.setLedgerCode(ledger.getLedgerName().trim().toUpperCase());
			ledger.setLedgerName(ledger.getLedgerName().trim().toUpperCase());
			session.insert("createLedger",ledger);
			session.commit();
			status="Record Successfully Added.";
			
		}catch(Exception e){
			status="Insertion Failed";
			e.printStackTrace();
			logger.error("In create Ledger method FinanceDaoImpl",e);
		}
		return status;
	}

	
	@Override
	public String checkForBankGroup(String ledger) {
		SqlSession session =sqlSessionFactory.openSession();
		String checker="FALSE";
		try{				
			
		    String group=session.selectOne("checkForBankGroup", ledger.trim());
		
		    if(group.equalsIgnoreCase("BANK ACCOUNT"))
			checker="TRUE";
		     }catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();	
			
		}
		return checker;
  }


	

	@Override
	public List<TrialBalance> getTrialBalance(String from, String to) {
		SqlSession session = sqlSessionFactory.openSession();		
		List<TrialBalance> tbList=new ArrayList<TrialBalance>();
		to += "23:59:59";
		try{				
			List<String> liabilityLedgerList = session.selectList("liabilityLedgerListForTB");
			List<String> assetLedgerList = session.selectList("assetLedgerListForTB");
						
			/* added by sourav.bhadra on 17-08-2017 */
			/* Liability Ledgers */
			for(String s:liabilityLedgerList){
				TrialBalance tb=new TrialBalance();
				tb.setLedger(s);
				if(s.equalsIgnoreCase("PROFIT & LOSS")){
					List<String> incomeLedgerList = session.selectList("incomeLedgerListForTB");
					List<String> expenceLedgerList = session.selectList("expenceLedgerListForTB");
					double totalIncome = 0.0;
					double totalExpence = 0.0;
					for(String iL : incomeLedgerList){
						Map<String, String> map = new HashMap<String,String>();
						map.put("to", to);
				        map.put("ledger", iL);
						Double incomeCurrentBalance=session.selectOne("ledgerCurrentBalanceForTB",map);
						if(null != incomeCurrentBalance){
							totalIncome += Math.abs(incomeCurrentBalance);
						}else{
							incomeCurrentBalance = 0.0;
							totalIncome += Math.abs(incomeCurrentBalance);
						}
					}
					for(String eL : expenceLedgerList){
						Map<String, String> map = new HashMap<String,String>();
						map.put("to", to);
				        map.put("ledger", eL);
						Double expenceCurrentBalance=session.selectOne("ledgerCurrentBalanceForTB",map);
						if(null != expenceCurrentBalance){
							totalIncome += Math.abs(expenceCurrentBalance);
						}else{
							expenceCurrentBalance = 0.0;
							totalIncome += Math.abs(expenceCurrentBalance);
						}
					}
					double rest = totalIncome - totalExpence;
					if(rest >= 0){
						tb.setCreditAmount(rest);
						tb.setDebitAmount(0.0);
					}else{
						tb.setCreditAmount(0.0);
						tb.setDebitAmount(Math.abs(rest));
					}
				}else{
					Map<String, String> map = new HashMap<String,String>();
					map.put("to", to);
			        map.put("ledger", s);
					Double liabilityCurrentBalance=session.selectOne("ledgerCurrentBalanceForTB",map);
					if(null != liabilityCurrentBalance){
						if(liabilityCurrentBalance >= 0){
							tb.setDebitAmount(0.0);
							tb.setCreditAmount(liabilityCurrentBalance);
						}else{
							tb.setDebitAmount(Math.abs(liabilityCurrentBalance));
							tb.setCreditAmount(0.0);
						}
					}else{
						tb.setDebitAmount(0.0);
						tb.setCreditAmount(0.0);
					}
				}
				tbList.add(tb);
			}
			/* Asset Ledgers */
			for(String s:assetLedgerList){
				TrialBalance tb=new TrialBalance();
				tb.setLedger(s);
				Map<String, String> map = new HashMap<String,String>();
				map.put("to", to);
		        map.put("ledger", s);
				Double assetCurrentBalance=session.selectOne("ledgerCurrentBalanceForTB",map);
				if(null != assetCurrentBalance){
					if(assetCurrentBalance >= 0){
						tb.setDebitAmount(assetCurrentBalance);
						tb.setCreditAmount(0.0);
					}else{
						tb.setDebitAmount(0.0);
						tb.setCreditAmount(Math.abs(assetCurrentBalance));
					}
				}else{
					tb.setDebitAmount(0.0);
					tb.setCreditAmount(0.0);
				}
				tbList.add(tb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return tbList;
	}



	/**
	 * author ranita.sur*
	 * changes taken on 07062017
	 * */
	
	@Override
	public List<IncomeAndExpense> getIncomeAndExpense(String from, String to) {
		SqlSession session =sqlSessionFactory.openSession();
		List<IncomeAndExpense> ieList=new ArrayList<IncomeAndExpense>();
		try{				
		Map<String, String> map = new HashMap<String, String>();
        map.put("from", from);
        map.put("to", to);
        List<IncomeAndExpense> ieListDebit=session.selectList("getIncomeAndExpenseDebit", map);
        List<IncomeAndExpense> ieListCredit=session.selectList("getIncomeAndExpenseCredit", map);
		for(IncomeAndExpense iec:ieListCredit){
			IncomeAndExpense incomeAndExpense=new IncomeAndExpense();
			incomeAndExpense.setLedger(iec.getLedger());
			incomeAndExpense.setIncomeExpense(iec.getIncomeExpense());
			//incomeAndExpense.setDate(iec.getDate());
				
				int found=0;
				for(IncomeAndExpense ied:ieListDebit){
					if(ied.getLedger().equalsIgnoreCase(iec.getLedger())){
						found=1;
						//FOUND IN BOTH CREDIT AND DEBIT LIST
						incomeAndExpense.setAmount(Math.abs(ied.getAmount()-iec.getAmount()));
						break;
					}						
				}
				//FOUND ONLY IN CREDIT LIST
				if(found==0)
					incomeAndExpense.setAmount(Math.abs(iec.getAmount()));
				
				ieList.add(incomeAndExpense);
		}
		
		for(IncomeAndExpense pld:ieListDebit){
			int found=0;
			for(IncomeAndExpense pll:ieList){
				if(pld.getLedger().equalsIgnoreCase(pll.getLedger())){
					found=1;
					break;
				}
			}
			//FOUND ONLY IN DEBIT LIST
			if(found==0){
				IncomeAndExpense incomeAndExpense=new IncomeAndExpense();
				incomeAndExpense.setLedger(pld.getLedger());
				incomeAndExpense.setIncomeExpense(pld.getIncomeExpense());
				//incomeAndExpense.setDate(pld.getDate());
				incomeAndExpense.setAmount(Math.abs(pld.getAmount()));
				
				ieList.add(incomeAndExpense);
			}
		}		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return ieList;
	}

	/**
	 * @author ranita.sur
	 * changes on 21082017**/

	@Override
	public List<TransactionalWorkingArea> getCashBook(String from, String to) {
		SqlSession session =sqlSessionFactory.openSession();
		List<TransactionalWorkingArea> cbList=new ArrayList<TransactionalWorkingArea>();
		try{				
			Map<String, String> map = new HashMap<String, String>();
			map.put("from", from);
			map.put("to", to);
			cbList = session.selectList("getCashBookDetails", map);
			for(TransactionalWorkingArea twa : cbList){
				if(null != twa.getObjectId()){
					String voucher = twa.getObjectId();
					String ledgerCode = twa.getTransactionalWorkingAreaDesc();
					Map<String, String> maps = new HashMap<String,String>();
			        maps.put("voucherNo", voucher);
			        maps.put("ledgerCode", ledgerCode);
			        String paidTo = session.selectOne("getPaymentParty", maps);
			        twa.setPaidAgainst(paidTo);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return cbList;
	}




	@Override
	public List<String> getAllBankNames() {
		SqlSession session =sqlSessionFactory.openSession();
		List<String> bankNames=null;
		try{				
			
			bankNames=session.selectList("getAllBankNames");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return bankNames;
	}



	@Override
	public String savePassbook(List<Passbook> passbookList) {
		SqlSession session =sqlSessionFactory.openSession();
		String status="Insert Failed";
		try{
			for(Passbook passbook:passbookList){				
				passbook.setObjectId(encryptDecrypt.getBase64EncodedID("FinanceDaoImpl"));	
				System.out.println("fdgsdfgfdsgfsdg:"+passbook.getParticulars());
				session.insert("savePassbook", passbook);
			}
			status="Insert Successful";
		}catch(Exception e){
			e.printStackTrace();
			status="Insert Failed";
			
		}finally{
			session.close();
		}
		return status;
	}



	@Override
	public List<Brs> getBrs(String from, String to, String bank) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Brs> brsListTD=null,brsListPB=null;
		List<Brs> allBrsList=new ArrayList<Brs>();
		try{
			Map<String, String> map = new HashMap<String, String>();
	        map.put("from", from);
	        map.put("to", to);
	        map.put("bank", bank);
	        
	        brsListTD=session.selectList("getAllChequeNumberFromTransaction",map);
			System.out.println("Found In Transaction ::"+brsListTD);
			for(Brs b:brsListTD){
				b.setTransactionPassbook("TRANSACTION");
				allBrsList.add(b);
			}
			
			 brsListPB=session.selectList("getAllChequeNumberFromPassbook",map);
			 System.out.println("Found In PassBook ::"+brsListPB);
				for(Brs b:brsListPB){
					b.setTransactionPassbook("PASSBOOK");
					allBrsList.add(b);
				}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return allBrsList;
	}





	@Override
	public String getStudentFeesPaymentDetails(StudentFeesPayment studentFeesPayment) {
		String fees="";
		SqlSession session =sqlSessionFactory.openSession();		
		try{
			List<StudentFeesPaymentDetails> studentFeesPaymentDetailsList=null;
			studentFeesPaymentDetailsList=session.selectList("selectFeesPaid",studentFeesPayment);
			if(null!=studentFeesPaymentDetailsList && studentFeesPaymentDetailsList.size()!=0){
				for(StudentFeesPaymentDetails sfpd:studentFeesPaymentDetailsList){
					String ledger="Not Found";
					if(null!=sfpd.getLedger() && sfpd.getLedger().length()!=0)
						ledger=sfpd.getLedger();
					fees=fees+sfpd.getFees()+"*"+sfpd.getTotalAmount()+"*"+sfpd.getPaidAmount()+"*"+sfpd.getPendingAmount()+"*"+ledger+"~";
				}
				fees=fees+"OLD";
			}else{
				if(null!=studentFeesPayment.getSection() && studentFeesPayment.getSection().trim().length()!=0){
					studentFeesPaymentDetailsList=session.selectList("selectFeesPayable",studentFeesPayment);
					for(StudentFeesPaymentDetails sfpd:studentFeesPaymentDetailsList){
						String ledger="Not Found";
						if(null!=sfpd.getLedger() && sfpd.getLedger().length()!=0)
							ledger=sfpd.getLedger();
						fees=fees+sfpd.getFees()+"*"+sfpd.getTotalAmount()+"*"+0.00+"*"+sfpd.getTotalAmount()+"*"+ledger+"~";
					}					
				}else{
					studentFeesPaymentDetailsList=session.selectList("selectFeesPayableForNewCandidate",studentFeesPayment);
					for(StudentFeesPaymentDetails sfpd:studentFeesPaymentDetailsList){
						String ledger="Not Found";
						if(null!=sfpd.getLedger() && sfpd.getLedger().length()!=0)
							ledger=sfpd.getLedger();
						fees=fees+sfpd.getFees()+"*"+sfpd.getTotalAmount()+"*"+0.00+"*"+sfpd.getTotalAmount()+"*"+ledger+"~";
					}
					
				}
				
				
				
				
				
				
				
				fees=fees+"NEW";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return fees;
	}



	@Override
	public String saveStudentFees(StudentFeesPayment studentFeesPayment, Transaction at) {
		SqlSession session =sqlSessionFactory.openSession();
		String status="Insert Failed";
//		try{
//			at.setObjectId(encryptDecrypt.getBase64EncodedID("FinanceDaoImpl"));
//			studentFeesPayment.setObjectId(encryptDecrypt.getBase64EncodedID("FinanceDaoImpl"));
//			session.insert("saveStudentFees", studentFeesPayment);
//			
//			if(null!=at.getTransactionDetailsList() && at.getTransactionDetailsList().size()!=0){
//				for(TransactionDetails trd:at.getTransactionDetailsList()){
//					String ledgerGroupType=session.selectOne("getGroupTypeOfLedger", trd.getLedger());
//					if(ledgerGroupType.equalsIgnoreCase("INCOME") || ledgerGroupType.equalsIgnoreCase("LIABLITY")){
//						if(trd.isDebit()==true){
//							session.update("updateDebitBalance",trd);		//-
//						}else{
//							session.update("updateCreditBalance",trd);	//+
//						}
//					}else if(ledgerGroupType.equalsIgnoreCase("ASSET") || ledgerGroupType.equalsIgnoreCase("EXPENSE")){
//						if(trd.isDebit()==true){
//							session.update("updateCreditBalance",trd);	//+
//						}else{
//							session.update("updateDebitBalance",trd);		//-
//						}
//					}else{
//						session.update("updateDebitBalance",trd);		//-
//					}
//				}
//			}
//						
//			session.insert("insertTransaction", at);
//			session.commit();
//			status="Insert Successful";
//		}catch(Exception e){
//			status="Insert Failed";
//			e.printStackTrace();
//		}finally{
//			session.close();
//		}
		return status;
	}



	@Override
	public String saveFeesLedgerMapping(List<FeesLedgerMapping> feesLedgerMappingList) {
		SqlSession session =sqlSessionFactory.openSession();
		String status="Update Failed";
		try{
			session.update("saveFeesLedgerMapping", feesLedgerMappingList);
			status="Update Successful";
		}catch(Exception e){
			status="Update Failed";
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}


	@Override
	public List<VendorPayment> getAllPurchaseOrdersForPayment() {
		SqlSession session = sqlSessionFactory.openSession();
		List<VendorPayment> poList=new ArrayList<VendorPayment>();
		try{				
			/*
			List<VendorPayment> nonTenderPoList=session.selectList("selectAllNonTenderPOList");
			if(null!=nonTenderPoList && nonTenderPoList.size()!=0)
				poList.addAll(nonTenderPoList);
			*/
			List<VendorPayment> tenderPoList = session.selectList("selectAllTenderPOList");
			if(null != tenderPoList && tenderPoList.size() != 0){poList.addAll(tenderPoList);}			
			List<VendorPayment> messPoList = session.selectList("selectAllMessPOList");
			if(null != messPoList && messPoList.size() != 0){poList.addAll(messPoList);}				
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return poList;
	}


	@Override
	public String makeVendorPayment(Transaction at, VendorPayment vp) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "Insert Failed";
//		try{
//			at.setObjectId(encryptDecrypt.getBase64EncodedID("FinanceDaoImpl"));			
//			System.out.println(vp.getStatus()+"::"+vp.getPoCode());			
//			session.update("makeVendorPayment", vp);
//			
//			if(null != at.getTransactionDetailsList() && at.getTransactionDetailsList().size() != 0){
//				for(TransactionDetails trd:at.getTransactionDetailsList()){
//					System.out.println(trd.getLedger());
//					String ledgerGroupType = session.selectOne("getGroupTypeOfLedger", trd.getLedger());
//					if(ledgerGroupType.equalsIgnoreCase("INCOME") || ledgerGroupType.equalsIgnoreCase("LIABLITY")){
//						if(trd.isDebit() == true){
//							session.update("updateDebitBalance",trd);		//-
//						}else{
//							session.update("updateCreditBalance",trd);	//+
//						}
//					}else if(ledgerGroupType.equalsIgnoreCase("ASSET") || ledgerGroupType.equalsIgnoreCase("EXPENSE")){
//						if(trd.isDebit() == true){
//							session.update("updateCreditBalance",trd);	//+
//						}else{
//							session.update("updateDebitBalance",trd);		//-
//						}
//					}else{
//						session.update("updateDebitBalance",trd);		//-
//					}
//				}
//			}
//						
//			session.insert("insertTransaction", at);
//			session.commit();
//			status = "Insert Successful";
//		}catch(Exception e){
//			status = "Insert Failed";
//			e.printStackTrace();
//		}finally{
//			session.close();
//		}
		return status;
	}


	@Override
	public List<Student> getNewStudents(Student student) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try {
			studentList = session.selectList("selectNewStudents", student);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getStudentsAgainstStandardAndSection() In CommonDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return studentList;
	}



	@Override
	public List<Employee> getAllStaffCodeList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Employee> employeeCodeList = null;
		try {
			employeeCodeList = session.selectList("selectEmployeeCodeList");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllStaffCodeList() In FinanceDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return employeeCodeList;
	}

	@Override
	public List<SalaryBreakUp> getAllSalaryBreakUpList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<SalaryBreakUp> breakUpList = null;
		try {
			breakUpList = session.selectList("selectSalaryBreakUpList");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllSalaryBreakUpList() In FinanceDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return breakUpList;
	}

@Override
	public StaffSalaryDetails getStaffSalaryDetails(String strEmployeeCode) {
		SqlSession session =sqlSessionFactory.openSession();
		StaffSalaryDetails staffSalaryDetails = new StaffSalaryDetails();
		try{
			staffSalaryDetails=session.selectOne("selectStaffSalaryDetails", strEmployeeCode);
		}catch(Exception e){
			logger.error("getAllStaffCodeList() In FinanceDaoImpl.java: Exception" ,e);
			e.printStackTrace();
		}finally{
			session.close();	
			
		}
		return staffSalaryDetails;
	}



	@Override
	public String addStaffSalaryDetails(StaffSalaryDetails staffSalaryDetails) {
		SqlSession session =sqlSessionFactory.openSession();
		String status="Fail";
		try{
			staffSalaryDetails.setObjectId(encryptDecrypt.getBase64EncodedID("FinanceDaoImpl"));
			int insertStatus = session.insert("insertStaffSalaryDetails", staffSalaryDetails);	
			session.commit();
			status="Success";
			if(insertStatus == 1){
				status = "Success";
			}
		}catch(Exception e){			
			logger.error("addStaffSalaryDetails() In FinanceDaoImpl.java: Exception" ,e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}

	@Override
	public String editStaffSalaryDetails(StaffSalaryDetails staffSalaryDetails) {
		SqlSession session =sqlSessionFactory.openSession();
		String status="Fail";
		try{
			staffSalaryDetails.setObjectId(encryptDecrypt.getBase64EncodedID("FinanceDaoImpl"));
			int updateStatus = session.update("updateStaffSalaryDetails", staffSalaryDetails);	
			session.commit();
			if(updateStatus == 1){
				status = "Success";
			}
			status="Success";
		}catch(Exception e){			
			logger.error("editStaffSalaryDetails() In FinanceDaoImpl.java: Exception" ,e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}



	@Override
	public String saveDesignationSalaryDetails(DesignationSalaryDetails designationSalaryDetails) {
		SqlSession session =sqlSessionFactory.openSession();
		String status="Fail";
		int insertStatus = 0;
		try{
			System.out.println(designationSalaryDetails);
			if(designationSalaryDetails.getStatus().equalsIgnoreCase("INSERT")){
			designationSalaryDetails.setObjectId(encryptDecrypt.getBase64EncodedID("FinanceDaoImpl"));
				insertStatus = session.insert("saveDesignationSalaryDetails", designationSalaryDetails);
			}else{
				insertStatus = session.insert("updateDesignationSalaryDetails", designationSalaryDetails);
			}
			session.commit();
			
			if(insertStatus == 1){
				status = "Success";
			}
			System.out.println(status);
		}catch(Exception e){			
			logger.error("saveDesignationSalaryDetails() In FinanceDaoImpl.java: Exception" ,e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}



	@Override
	public DesignationSalaryDetails getDesignationSalaryDetails(String designation, String parameter) {
		SqlSession session =sqlSessionFactory.openSession();
		DesignationSalaryDetails designationSalaryDetails = null;
		try{
			if(parameter.equalsIgnoreCase("EMPLOYEE"))
				designation=session.selectOne("getEmployeeDesignation", designation);
			designationSalaryDetails=session.selectOne("selectDesignationSalaryDetails", designation);
		}catch(Exception e){
			logger.error("getDesignationSalaryDetails() In FinanceDaoImpl.java: Exception" ,e);
			e.printStackTrace();
		}finally{
			session.close();	
			
		}
		return designationSalaryDetails;

	}



	@Override
	public List<SalaryDisbursementList> getSalaryDisbursementList(String month) {
		SqlSession session =sqlSessionFactory.openSession();
		List<ResourceType> resourceTypeList=null;
		List<SalaryDisbursementList> salaryDisbursementList=new ArrayList<SalaryDisbursementList>();
		try{
			resourceTypeList = session.selectList("selectAllResourceType");
			if(null!=resourceTypeList && resourceTypeList.size()!=0){
				for(ResourceType rt:resourceTypeList){
					SalaryDisbursementList sdl=new SalaryDisbursementList();
					sdl.setResourceType(rt.getResourceTypeName());
					sdl.setMonth(month);
					Integer rCount=session.selectOne("getResourceCountForResourceType", rt.getResourceTypeName());
					Integer dsCount=session.selectOne("getDisbursedSalaryCount", sdl);
					if(null!=rCount)
						sdl.setTotal(rCount);
					else
						sdl.setTotal(0);
					if(null!=dsCount)
						sdl.setUpdated(dsCount);
					else
						sdl.setUpdated(0);
					sdl.setRemaining(sdl.getTotal()-sdl.getUpdated());
					
					salaryDisbursementList.add(sdl);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return salaryDisbursementList;
	}



	@Override
	public List<Employee> getStaffCodeListToDisburseSalary(SalaryDisbursementList sdl) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Employee> employeeCodeList = null;
		try {
			employeeCodeList = session.selectList("selectStaffCodeListToDisburseSalary", sdl);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllStaffCodeList() In FinanceDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return employeeCodeList;
	}



	@Override
	public String saveSalaryDisbursement(DisbursementSalaryDetails disbursementSalaryDetails) {
		SqlSession session =sqlSessionFactory.openSession();
		String status="Fail";
		int insertStatus = 0;
		try{
			disbursementSalaryDetails.setObjectId(encryptDecrypt.getBase64EncodedID("FinanceDaoImpl"));
			insertStatus = session.insert("saveSalaryDisbursement", disbursementSalaryDetails);
			
			
			
			
			List<TransactionDetails> transactionDetailsList = new ArrayList<TransactionDetails>();

			TransactionDetails transactionDetails = new TransactionDetails();
			transactionDetails.setLedger("PAY&ALLOWANCES");
			System.out.println(transactionDetails+"\t"+disbursementSalaryDetails+"\t"+disbursementSalaryDetails.getGross());
			transactionDetails.setAmount(disbursementSalaryDetails.getBasic() + disbursementSalaryDetails.getGradePay() + disbursementSalaryDetails.getDa() + disbursementSalaryDetails.getSmaHma() + disbursementSalaryDetails.getTptl() + disbursementSalaryDetails.getMa() + disbursementSalaryDetails.getSa() + disbursementSalaryDetails.getArrear() +disbursementSalaryDetails.getMiscInc() + disbursementSalaryDetails.getEd() + disbursementSalaryDetails.getNps());
			transactionDetails.setDebit(true);
			transactionDetailsList.add(transactionDetails);
	
			
			transactionDetails.setLedger("GPF");
			transactionDetails.setAmount(disbursementSalaryDetails.getGpf());
			transactionDetails.setDebit(false);
			transactionDetailsList.add(transactionDetails);
			
			transactionDetails.setLedger("CPF");
			transactionDetails.setAmount(disbursementSalaryDetails.getCpf());
			transactionDetails.setDebit(false);
			transactionDetailsList.add(transactionDetails);
			
			transactionDetails.setLedger("NPS(BOTH)");
			transactionDetails.setAmount(disbursementSalaryDetails.getNpsBoth());
			transactionDetails.setDebit(false);
			transactionDetailsList.add(transactionDetails);
			
			transactionDetails.setLedger("WC");
			transactionDetails.setAmount(disbursementSalaryDetails.getWc());
			transactionDetails.setDebit(false);
			transactionDetailsList.add(transactionDetails);
			
			transactionDetails.setLedger("EC");
			transactionDetails.setAmount(disbursementSalaryDetails.getElectricCharge());
			transactionDetails.setDebit(false);
			transactionDetailsList.add(transactionDetails);
			
			transactionDetails.setLedger("INSURANCE PREMIUM");
			transactionDetails.setAmount(disbursementSalaryDetails.getIp());
			transactionDetails.setDebit(false);
			transactionDetailsList.add(transactionDetails);
			
			transactionDetails.setLedger("PFL");
			transactionDetails.setAmount(disbursementSalaryDetails.getPfl());
			transactionDetails.setDebit(false);
			transactionDetailsList.add(transactionDetails);
			
			transactionDetails.setLedger("FESTIVAL ADVANCE");
			transactionDetails.setAmount(disbursementSalaryDetails.getFa());
			transactionDetails.setDebit(false);
			transactionDetailsList.add(transactionDetails);
			
			transactionDetails.setLedger("GIP");
			transactionDetails.setAmount(disbursementSalaryDetails.getGip());
			transactionDetails.setDebit(false);
			transactionDetailsList.add(transactionDetails);
			
			transactionDetails.setLedger("PROFESSIONAL TAX");
			transactionDetails.setAmount(disbursementSalaryDetails.getPt());
			transactionDetails.setDebit(false);
			transactionDetailsList.add(transactionDetails);
			
			transactionDetails.setLedger("INCOME TAX");
			transactionDetails.setAmount(disbursementSalaryDetails.getIt());
			transactionDetails.setDebit(false);
			transactionDetailsList.add(transactionDetails);
			
			transactionDetails.setLedger("MISC EXPENSES");
			transactionDetails.setAmount(disbursementSalaryDetails.getPt());
			transactionDetails.setDebit(false);
			transactionDetailsList.add(transactionDetails);
			
			transactionDetails.setLedger("CASH A/C");
			transactionDetails.setAmount(disbursementSalaryDetails.getPt());
			transactionDetails.setDebit(false);
			transactionDetailsList.add(transactionDetails);
			
			Transaction transaction=new Transaction();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
//			transaction.setDate(sdf.format(date));			
//			transaction.setObjectId(encryptDecrypt.getBase64EncodedID("FinanceDaoImpl"));
//			transaction.setUpdatedBy(transaction.getUpdatedBy());
//			transaction.setReceiptChallan("RECEIPT");
//			transaction.setNarration("Being Salary Paid To Employee ::"+disbursementSalaryDetails.getEmployee());
//			transaction.setId(disbursementSalaryDetails.getEmployee());
//			transaction.setYearType("FINANTIAL");
//			transaction.setReason("Salary Paid");
//			transaction.setVoucherType("PAYMENT");
//			
//			transaction.setTransactionDetailsList(transactionDetailsList);
			saveTransaction(transaction);

			
			session.commit();
			if(insertStatus == 1){
				status = "Success";
			}
		}catch(Exception e){
			logger.error("saveDesignationSalaryDetails() In FinanceDaoImpl.java: Exception" ,e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}




	@Override
	public List<Employee> getSalaryDisbursedStaffList(SalaryDisbursementList sdl) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Employee> employeeCodeList = null;
		try {
			employeeCodeList = session.selectList("selectSalaryDisbursedStaffList", sdl);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllStaffCodeList() In FinanceDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return employeeCodeList;
	}



	@Override
	public DisbursementSalaryDetails viewStaffDisbursedSalaryDetails(DisbursementSalaryDetails dsd) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			dsd = session.selectOne("selectStaffDisbursedSalaryDetails", dsd);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("getAllStaffCodeList() In FinanceDaoImpl.java: Exception" ,e);
		} finally {
			session.close();
		}
		return dsd;
	}
	
	@Override
	public List<BalanceSheet> getBalanceSheet(String from, String to) {
		SqlSession session =sqlSessionFactory.openSession();
		List<BalanceSheet> plList=new ArrayList<BalanceSheet>();
		try{				
		
		
		Map<String, String> map = new HashMap<String, String>();
        map.put("from", from);
        map.put("to", to);
        List<BalanceSheet> plListDebit=session.selectList("getBalanceSheetDebit", map);
        List<BalanceSheet> plListCredit=session.selectList("getBalanceSheetCredit", map);
		for(BalanceSheet plc:plListCredit){
			BalanceSheet balanceSheet=new BalanceSheet();
			balanceSheet.setLedger(plc.getLedger());
			balanceSheet.setAssetLiablity(plc.getAssetLiablity());
				
				int found=0;
				for(BalanceSheet pld:plListDebit){
					if(pld.getLedger().equalsIgnoreCase(plc.getLedger())){
						found=1;
						//FOUND IN BOTH CREDIT AND DEBIT LIST
						balanceSheet.setAmount(Math.abs(pld.getAmount()-plc.getAmount()));
						break;
					}						
				}
				//FOUND ONLY IN CREDIT LIST
				if(found==0)					
					balanceSheet.setAmount(Math.abs(plc.getAmount()));
				
				plList.add(balanceSheet);
		}
		
		for(BalanceSheet pld:plListDebit){
			int found=0;
			for(BalanceSheet pll:plList){
				if(pld.getLedger().equalsIgnoreCase(pll.getLedger())){
					found=1;
					break;
				}
			}
			//FOUND ONLY IN DEBIT LIST
			if(found==0){
				BalanceSheet balanceSheet=new BalanceSheet();
				
				balanceSheet.setLedger(pld.getLedger());
				balanceSheet.setAssetLiablity(pld.getAssetLiablity());
				balanceSheet.setAmount(Math.abs(pld.getAmount()));
				plList.add(balanceSheet);
			}
		}		
		}catch(Exception e){
			e.printStackTrace();
		}
		return plList;
	}



	



	@Override
	public List<VoucherType> getVoucherTypeList() {
		SqlSession session =sqlSessionFactory.openSession();	
		List<VoucherType> voucherTypeList=null;
		try{			
			voucherTypeList=session.selectList("getVoucherTypeList");
			System.out.println("voucherTypeList:"+voucherTypeList.size());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		if(voucherTypeList != null)
			return voucherTypeList;
		else
			return null;
	}
	
	@Override
	public List<TransactionalWorkingArea> getTransactionalWorkingAreaList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<TransactionalWorkingArea> transactionalWorkingAreaList=null;
		try{
			transactionalWorkingAreaList=session.selectList("getTransactionalWorkingAreaList");
			/*for(TransactionalWorkingArea twa:transactionalWorkingAreaList){
				if(twa.getResourceType().equalsIgnoreCase("CANDIDATE")){
					System.out.println("1190resourceId::>>"+twa.getResourceId());
					TransactionalWorkingArea nameId=session.selectOne("getResourceNameForCANDIDATE", twa.getResourceId());
					twa.setResource(nameId.getResource());
					twa.setCodeId(nameId.getCodeId());
					System.out.println("resource::>>"+nameId.getResource()+"\nCode::>>"+nameId.getCodeId());
				}else if(twa.getResourceType().equalsIgnoreCase("ASSET VENDOR") || twa.getResourceType().equalsIgnoreCase("BOOK VENDOR")){
					System.out.println("1196resourceId::>>"+twa.getResourceId());
					TransactionalWorkingArea nameId=session.selectOne("getResourceNameForVENDOR", twa.getResourceId());
					twa.setResource(nameId.getResource());
					twa.setCodeId(nameId.getCodeId());
					System.out.println("resource::>>"+nameId.getResource()+"\nCode::>>"+nameId.getCodeId());
				}else if(twa.getResourceType().equalsIgnoreCase("TEACHER") || twa.getResourceType().equalsIgnoreCase("NONTEACHINGSTAFF")){
					System.out.println("1202resourceId::>>"+twa.getResourceId());
					TransactionalWorkingArea nameId=session.selectOne("getResourceNameForTEACHER", twa.getResourceId());
					twa.setResource(nameId.getResource());
					twa.setCodeId(nameId.getCodeId());
					System.out.println("resource::>>"+nameId.getResource()+"\nCode::>>"+nameId.getCodeId());
				}else if(twa.getResourceType().equalsIgnoreCase("STUDENT")){
					System.out.println("1208resourceId::>>"+twa.getResourceId());
					TransactionalWorkingArea nameId=session.selectOne("getResourceNameForSTUDENT", twa.getResourceId());
					twa.setResource(nameId.getResource());
					twa.setCodeId(nameId.getCodeId());
					System.out.println("resource::>>"+nameId.getResource()+"\nCode::>>"+nameId.getCodeId());
				}
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		if(transactionalWorkingAreaList != null)
			return transactionalWorkingAreaList;
		else
			return null;
	}

	@Override
	public TransactionalWorkingArea getTransactionWorkingAreaDetails(String twaCode) {
		SqlSession session =sqlSessionFactory.openSession();
		TransactionalWorkingArea transactionalWorkingArea=new TransactionalWorkingArea();
		try{			
			transactionalWorkingArea=session.selectOne("getTransactionWorkingAreaDetails", twaCode);
			/*if(transactionalWorkingArea.getResourceType().equalsIgnoreCase("CANDIDATE")){
				TransactionalWorkingArea nameId=session.selectOne("getResourceNameForCANDIDATE", transactionalWorkingArea.getResourceId());
				transactionalWorkingArea.setResource(nameId.getResource());
				transactionalWorkingArea.setCodeId(nameId.getCodeId());
			}else if(transactionalWorkingArea.getResourceType().equalsIgnoreCase("ASSET VENDOR") || transactionalWorkingArea.getResourceType().equalsIgnoreCase("BOOK VENDOR")){
				TransactionalWorkingArea nameId=session.selectOne("getResourceNameForVENDOR", transactionalWorkingArea.getResourceId());
				transactionalWorkingArea.setResource(nameId.getResource());
				transactionalWorkingArea.setCodeId(nameId.getCodeId());
			}else if(transactionalWorkingArea.getResourceType().equalsIgnoreCase("TEACHER") || transactionalWorkingArea.getResourceType().equalsIgnoreCase("NONTEACHINGSTAFF")){
				TransactionalWorkingArea nameId=session.selectOne("getResourceNameForTEACHER", transactionalWorkingArea.getResourceId());
				transactionalWorkingArea.setResource(nameId.getResource());
				transactionalWorkingArea.setCodeId(nameId.getCodeId());
			}else if(transactionalWorkingArea.getResourceType().equalsIgnoreCase("STUDENT")){
				TransactionalWorkingArea nameId=session.selectOne("getResourceNameForSTUDENT", transactionalWorkingArea.getResourceId());
				transactionalWorkingArea.setResource(nameId.getResource());
				transactionalWorkingArea.setCodeId(nameId.getCodeId());
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return transactionalWorkingArea;
	}


	
	@Override
	public String transactionWorkingAreaSanction(String twaCode,String userId,Resource resource) {
		String status="Update Success";
		String status1="Notification Failed";
		String status2="";
		SqlSession session =sqlSessionFactory.openSession();
		Budget budgetvalue;
		TransactionalWorkingArea amountForTransaction;
		try{			
			TransactionalWorkingArea transactionalWorkingArea=new TransactionalWorkingArea();
			
			List<TransactionalWorkingArea>transactionalWorkingArealist=session.selectList("getTransactionWorkingAreaDetails", twaCode);
			for(TransactionalWorkingArea twaObj:transactionalWorkingArealist)
			{
			/*if(twaObj.getResourceType().equalsIgnoreCase("CANDIDATE")){
				TransactionalWorkingArea nameId=session.selectOne("getResourceNameForCANDIDATE", twaObj.getResourceId());
				twaObj.setResource(nameId.getResource());
				twaObj.setCodeId(nameId.getCodeId());
			}else if(twaObj.getResourceType().equalsIgnoreCase("ASSET VENDOR") || twaObj.getResourceType().equalsIgnoreCase("BOOK VENDOR")){
				TransactionalWorkingArea nameId=session.selectOne("getResourceNameForVENDOR", twaObj.getResourceId());
				twaObj.setResource(nameId.getResource());
				twaObj.setCodeId(nameId.getCodeId());
			}else if(twaObj.getResourceType().equalsIgnoreCase("TEACHER") || twaObj.getResourceType().equalsIgnoreCase("NONTEACHINGSTAFF")||twaObj.getResourceType().equalsIgnoreCase("TEACHING STAFF") ){
				TransactionalWorkingArea nameId=session.selectOne("getResourceNameForTEACHER", twaObj.getResourceId());
				twaObj.setResource(nameId.getResource());
				transactionalWorkingArea.setCodeId(nameId.getCodeId());
			}else if(twaObj.getResourceType().equalsIgnoreCase("STUDENT")){
				TransactionalWorkingArea nameId=session.selectOne("getResourceNameForSTUDENT", twaObj.getResourceId());
				twaObj.setResource(nameId.getResource());
				twaObj.setCodeId(nameId.getCodeId());
			}*/
		
			

			
			
			Transaction transaction=new Transaction();
		//	List<TransactionDetails> transactionDetailsList=new ArrayList<TransactionDetails>();
			
			transaction.setUpdatedBy(userId);
			transaction.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
			transaction.setWorkingAreaCode(twaCode);
			/*System.out.println("transactionalWorkingArea.getTransactionDate()==="+t.getTransactionDate());
			System.out.println("transactionalWorkingArea.getReasonOfTransaction()==="+t.getReasonOfTransaction());*/
			transaction.setTransactionDate(transactionalWorkingArea.getTransactionDate());
			
			String statusMode = resource.getStatus();
			if(statusMode.equalsIgnoreCase("INCOME")){
				session.update("transactionWorkingAreaSanction", transaction);
			}else{
				List<Department>departmentList = session.selectList("selectAllDepartment");
				for( Department departmentObj :departmentList){
					if(resource.getDepartment().equalsIgnoreCase(departmentObj.getDepartmentName()))
					{
						String department = resource.getDepartment();
						budgetvalue=session.selectOne("getBudgetForADepartmentInCurrentAcademicYear",department);
						
						amountForTransaction=session.selectOne("amountRequestedForTransactionFromADepartment",twaCode);
						//System.out.println("budgetvalue===="+budgetvalue.getExpectedExpense());
						//System.out.println("bookammount===="+amountForTransaction);
						if(null != budgetvalue){
							if(amountForTransaction.getNetAmount()<budgetvalue.getExpectedExpense()){
								System.out.println("within");
									session.update("transactionWorkingAreaSanction", transaction);
									if(null != resource.getAccountType()){
										//if(resource.getAccountType().equalsIgnoreCase("LIBRARY PO")){
												
											Ticket ticket=new Ticket();
											ticket.setTicketObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
											ticket.setUpdatedBy(userId);
											ticket.setComment(resource.getAccountType()+" Requested From "+resource.getDepartment()+" Is Approved");
											ServiceType service=new ServiceType();
											service.setTicketServiceName(resource.getAccountType());
											ticket.setTicketService(service);
											//String reportedUserId=session.selectOne("selectCode",ticket);
											
											ticket.setModuleName(amountForTransaction.getUpdatedBy());
											//ticket.setModuleName(reportedUserId);
											int insertStatus=session.insert("insertintoNotification",ticket);
											//status1="Notification";
									}else{
										Ticket ticket=new Ticket();
										ticket.setTicketObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
										ticket.setUpdatedBy(userId);
										ticket.setComment(" Requested Of "+resource.getDepartment()+" Is Approved");
										ServiceType service=new ServiceType();
										service.setTicketServiceName("Request From "+resource.getDepartment());
										ticket.setTicketService(service);
										//String reportedUserId=session.selectOne("selectCode",ticket);
										
										ticket.setModuleName(amountForTransaction.getUpdatedBy());
										//ticket.setModuleName(reportedUserId);
										int insertStatus=session.insert("insertintoNotification",ticket);
										//status1="Notification";
									}
									
							}else{
								
								if(null != resource.getAccountType()){
									//if(resource.getAccountType().equalsIgnoreCase("LIBRARY PO")){
											
										Ticket ticket=new Ticket();
										ticket.setTicketObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
										ticket.setUpdatedBy(userId);
										ticket.setComment(resource.getAccountType()+" Requested From "+resource.getDepartment()+" Is Rejected As Budget Not Available");
										ServiceType service=new ServiceType();
										service.setTicketServiceName(resource.getAccountType());
										ticket.setTicketService(service);
										//String reportedUserId=session.selectOne("selectCode",ticket);
										
										ticket.setModuleName(amountForTransaction.getUpdatedBy());
										//ticket.setModuleName(reportedUserId);
										int insertStatus=session.insert("insertintoNotification",ticket);
										//status1="Notification";
								}else{
									Ticket ticket=new Ticket();
									ticket.setTicketObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
									ticket.setUpdatedBy(userId);
									ticket.setComment(" Requested Of "+resource.getDepartment()+" Is Is Rejected As Budget Not Available");
									ServiceType service=new ServiceType();
									service.setTicketServiceName("Request From "+resource.getDepartment());
									ticket.setTicketService(service);
									//String reportedUserId=session.selectOne("selectCode",ticket);
									
									ticket.setModuleName(amountForTransaction.getUpdatedBy());
									//ticket.setModuleName(reportedUserId);
									int insertStatus=session.insert("insertintoNotification",ticket);
									//status1="Notification";
								}
								status = "moreThanBudget";
							}
						}else{
							status = "noBudget";
						}
						
					}
				}
			}
			
				
			
			
			}
			
			
			
		}catch(Exception e){
			
			status="Update Failed";
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}
	
	
	@Override
	public List<Resource> getAllStudentList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> studentList=null;
		try{
			studentList=session.selectList("getAllStudentList");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		if(studentList != null)
			return studentList;
		else
			return null;
	}
	
	@Override
	public String updateSecurityDeposit(List<Resource> studentList) {
		SqlSession session=sqlSessionFactory.openSession();
		String status="Update Failed";
		try{			
			for(Resource r:studentList){					
				r.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
				session.update("updateSecurityDeposit",r);
			}
			status="Update successful";			
		}catch(Exception e){
			status="Update Failed";
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}



	@Override
	public List<TemplateLedgerMapping> getAllTemplateListForLedgerMapping() {
		SqlSession session=sqlSessionFactory.openSession();
		List<TemplateLedgerMapping> templateList=null;
		try{
			templateList=session.selectList("getAllTemplateListForLedgerMapping");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		if(templateList != null)
			return templateList;
		else
			return null;
	}

	@Override
	public List<TemplateLedgerMapping> getTemplateDetailsListForLedgerMapping(String templateCode) {
		SqlSession session=sqlSessionFactory.openSession();
		List<TemplateLedgerMapping> templateList=null;
		try{
			templateList=session.selectList("getTemplateDetailsListForLedgerMapping", templateCode);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		if(templateList != null)
			return templateList;
		else
			return null;
	}

	@Override
	public String mapLedgerTemplate(List<TemplateLedgerMapping> templateLedgerMappingList, String templateCode) {
		SqlSession session=sqlSessionFactory.openSession();
		String status="success";
		try{
			for(TemplateLedgerMapping tlm:templateLedgerMappingList){	
				tlm.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
				session.insert("mapLedgerTemplateResource", tlm);
			}
			//session.update("updateLedgerTemplateMappingStatus", templateCode);
			
			
		}catch(Exception e){
			status="fail";
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}

	/**
	 * modified by sourav.bhadra
	 * changes taken on 04072017*/
	
	@Override
	public String getBudgetForAcademicYear(String academicYear) {
		String budgetString="";
		String budgetStatus="unallocated";
		SqlSession session=sqlSessionFactory.openSession();
		try{
			/* modified by sourav.bhadra on 06-04-2018 */
			List<Department> departmentFromDB = session.selectList("selectDeptsForDudgetAllocation");
			List<Budget> budgetList = new ArrayList<Budget>();
			for(Department d:departmentFromDB){
				Budget b = new Budget();
				b.setAcademicYear(academicYear);
				b.setDepartment(d.getDepartmentName());
				b = session.selectOne("getBudgetForAcademicYear", b);
				/* new */
				if(null != b){
					budgetStatus="allocated";
				}
				if(b == null){
					b = new Budget();
					b.setAcademicYear(academicYear);
					b.setDepartment(d.getDepartmentName());
					b.setTotalExpence(0.0);
					b.setActualIncome(0.0);
					b.setExpectedExpense(0.0);
					b.setExpectedIncome(0.0);
					b.setReserveFund(0.0);
				}
				b.setAcademicYear(academicYear);
				b.setDepartment(d.getDepartmentName());
				budgetList.add(b);
			}
			/* modified by sourav.bhadra 13-03-2018 */
			for(Budget b:budgetList){
				/* modified by sourav.bhadra 06-04-2018 */
				budgetString=budgetString+b.getDepartment()+"*~*"+String.format("%.2f", b.getExpectedIncome())+"*~*"+
						String.format("%.0f", b.getActualIncome())+"*~*"+String.format("%.0f", b.getTotalExpence())+"*~*"+
						String.format("%.0f", b.getExpectedExpense())+"*~*"+String.format("%.0f", b.getReserveFund())+"~*~";
			}
			budgetString=budgetStatus+"@@"+budgetString;
			System.out.println("budget=="+budgetString);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}		
		return budgetString;
	}

	
	@Override
	public String saveBudget(List<Budget> budgetList) {
		String status="success";
		SqlSession session=sqlSessionFactory.openSession();
		try{
			for(Budget b:budgetList){
				Budget budget=session.selectOne("getBudgetForAcademicYear", b);
				if(budget == null){
					b.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
					session.insert("saveBudget",b);
					/* added by sourav.bhadra on 17-04-2018 */
					String department = b.getDepartment();
					session.update("updateBudgetStatusInDepartmentTable", department);
					
				}else{
					session.update("updateBudget",b);
					status="update";	
				}
			}
					
		}catch(Exception e){
			status="fail";
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}



	@Override
	public String saveDelarDetails(DelarDetails delarDetails) {
		String status="Insert Failed";
		SqlSession session=sqlSessionFactory.openSession();
		try{
			//Utility util=new Utility();			
			delarDetails.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
			session.update("saveDelarDetails",delarDetails);
			status="Insert successful";
		}catch(Exception e){
			status="Insert Failed";
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}



	@Override
	public String inactiveLedger(String ledgerCode) {
		String status="Failed to Delete";
		SqlSession session=sqlSessionFactory.openSession();
		try{
			//Utility util=new Utility();			
			//delarDetails.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
			List<TemplateLedgerMapping>templateLedgerMappingList = session.selectList("selectLedgerFromSalaryTemplateDetails", ledgerCode);
			System.out.println("templateLedgerMappingList====="+templateLedgerMappingList.size());
			if(templateLedgerMappingList.size() ==0 || templateLedgerMappingList==null){
				session.update("inactiveLedger",ledgerCode);
				status="Deleted SuccessFully";
			}else{
				status = "Ledger is already mapped with Salary Template";
			}
			
		}catch(Exception e){
			status="Fail";
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}



	@Override
	public String inactiveLedgerGroup(String groupCode) {
		String status="Failed to Delete";
		SqlSession session=sqlSessionFactory.openSession();
		try{
			//Utility util=new Utility();			
			//delarDetails.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
			List<Group>groupNameList = session.selectList("selectGroupNameFromLedger", groupCode);
			System.out.println("templateLedgerMappingList====="+groupNameList.size());
			if(groupNameList.size() ==0 || groupNameList == null){
				session.update("inactiveLedgerGroup",groupCode);
				status="Deleted SuccessFully";
			}else{
				status = "Ledger already exists For This Group";
			}
			
		}catch(Exception e){
			status="Fail";
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}
	
	/**@author anup.roy*
	 * */

	@Override
	public List<ProfitAndLoss> getProfitAndLoss(String from, String to) {
		List<ProfitAndLoss> plList=new ArrayList<ProfitAndLoss>();
		SqlSession session = sqlSessionFactory.openSession();
		try{
			Map<String, String> map = new HashMap<String, String>();
	        map.put("from", from);
	        map.put("to", to);
	        List<ProfitAndLoss> plListDebit=session.selectList("getProfitAndLossDebit", map);
	        List<ProfitAndLoss> plListCredit=session.selectList("getProfitAndLossCredit", map);
			for(ProfitAndLoss plc:plListCredit){
					ProfitAndLoss profitAndLoss=new ProfitAndLoss();
					profitAndLoss.setLedger(plc.getLedger());
					profitAndLoss.setIncomeExpense(plc.getIncomeExpense());
					
					int found=0;
					for(ProfitAndLoss pld:plListDebit){
						if(pld.getLedger().equalsIgnoreCase(plc.getLedger())){
							found=1;
							//FOUND IN BOTH CREDIT AND DEBIT LIST
							profitAndLoss.setAmount(Math.abs(pld.getAmount()-plc.getAmount()));
							break;
						}						
					}
					//FOUND ONLY IN CREDIT LIST
					if(found==0)
						profitAndLoss.setAmount(Math.abs(plc.getAmount()));
					
					plList.add(profitAndLoss);
			}
			for(ProfitAndLoss pld:plListDebit){
				int found=0;
				for(ProfitAndLoss pll:plList){
					if(pld.getLedger().equalsIgnoreCase(pll.getLedger())){
						found=1;
						break;
					}
				}
				//FOUND ONLY IN DEBIT LIST
				if(found==0){
					ProfitAndLoss profitAndLoss=new ProfitAndLoss();
					profitAndLoss.setLedger(pld.getLedger());
					profitAndLoss.setIncomeExpense(pld.getIncomeExpense());
					profitAndLoss.setAmount(Math.abs(pld.getAmount()));
					
					plList.add(profitAndLoss);
				}
			}		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return plList;
	}
	
	/**Addeb by ranita.sur on 08/08/2017 for edit daybook details**/
	@Override
	public List<Daybook> getDayBook(String from, String to) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Daybook> allDayBookList=new ArrayList<Daybook>();
		to +=" 23:59:59";
		try{
			Map<String, String> map = new HashMap<String, String>();
	        map.put("from", from);
	       if(null==to || to.trim().length()==0){
	        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    		to=sdf.format(new Date());
	        }else{
	        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	        	Calendar c = Calendar.getInstance();
	        	c.setTime(sdf.parse(to));
	        	c.add(Calendar.DATE, 1);
	        	to = sdf.format(c.getTime());
	        }
	       map.put("to", to);
	        allDayBookList=session.selectList("getDayBook",map);
	  }catch(Exception e){
			e.printStackTrace();
		}
		return allDayBookList;
	}
	
/*	/Naimisha 01062017/*/
	
	@Override
	public String saveTransaction(Transaction transaction) {
		SqlSession session =sqlSessionFactory.openSession();	
		String status="Insert Failed";
		try{			
			Budget budget=new Budget();
			budget.setUpdatedBy(transaction.getUpdatedBy());
			transaction.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
			
			double amount=0.0;
			
			/* modified by sourav.bhadra on 28-08-2017
			 * for debit-credit operation */
			if(transaction.getTrDetList()!=null && transaction.getTrDetList().size()!=0){
				for(TransactionDetails trd:transaction.getTrDetList()){
					amount=trd.getAmount();
					String ledger = trd.getLedger();
					System.out.println("LN1756 Ledger :: "+ledger+"\tAMOUNT ::"+amount);
					/*added by ranita.sur on 18082017 for enry in daybook*/
					String ledgerGroupType=session.selectOne("selectGroupTypeForALedger", ledger);
					Ledger ledgerBalance = session.selectOne("selectBalanceForParentLedger", ledger);
					System.out.println("LN1760 :: Ledger Group Type :: "+ledgerGroupType+"\tCurrent Balance :: "+ledgerBalance.getCurrentBal());
					if(ledgerGroupType.equalsIgnoreCase("ASSET")){//debit type
						if(ledgerBalance.getCurrentBal() > 0 ){//if current balance is +ve then in debit side
							if(trd.isDebit()==true){//if in debit true then add operation
								trd.setUpdatedBy(transaction.getUpdatedBy());
								trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
								trd.setGrossAmount(ledgerBalance.getOpeningBal());
								trd.setOnbasic(ledgerBalance.getCurrentBal() + amount);
							}else{// debit false then subtract operation
								trd.setUpdatedBy(transaction.getUpdatedBy());
								trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
								trd.setGrossAmount(ledgerBalance.getOpeningBal());
								trd.setOnbasic(ledgerBalance.getCurrentBal() - amount);
							}System.out.println("LN1773 :: ASSET +VE :: Final Amount :: "+trd.getOnbasic());
							session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
						}else{//in credit side
							if(trd.isDebit()==true){//if in debit true then subtract operation
								trd.setUpdatedBy(transaction.getUpdatedBy());
								trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
								trd.setGrossAmount(ledgerBalance.getOpeningBal());
								trd.setOnbasic(ledgerBalance.getCurrentBal() + amount);
							}else{// debit false then add operation
								trd.setUpdatedBy(transaction.getUpdatedBy());
								trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
								trd.setGrossAmount(ledgerBalance.getOpeningBal());
								trd.setOnbasic(ledgerBalance.getCurrentBal() - amount);
							}System.out.println("LN1786 :: ASSET -VE :: Final Amount :: "+trd.getOnbasic());
							session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
						}
					}else if(ledgerGroupType.equalsIgnoreCase("LIABLITY")){//credit type
						if(ledgerBalance.getCurrentBal() > 0 ){//if current balance is +ve then in credit side
							if(trd.isDebit()==true){//if in debit true then subtract operation
								trd.setUpdatedBy(transaction.getUpdatedBy());
								trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
								trd.setGrossAmount(ledgerBalance.getOpeningBal());
								trd.setOnbasic(ledgerBalance.getCurrentBal() - amount);
							}else{// debit false then add operation
								trd.setUpdatedBy(transaction.getUpdatedBy());
								trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
								trd.setGrossAmount(ledgerBalance.getOpeningBal());
								trd.setOnbasic(ledgerBalance.getCurrentBal() + amount);
							}System.out.println("LN1801 :: LIABLITY +VE :: Final Amount :: "+trd.getOnbasic());
							session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
						}else{//in debit side
							if(trd.isDebit()==true){//if in debit true then add operation
								trd.setUpdatedBy(transaction.getUpdatedBy());
								trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
								trd.setGrossAmount(ledgerBalance.getOpeningBal());
								trd.setOnbasic(ledgerBalance.getCurrentBal() - amount);
							}else{// debit false then subtract operation
								trd.setUpdatedBy(transaction.getUpdatedBy());
								trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
								trd.setGrossAmount(ledgerBalance.getOpeningBal());
								trd.setOnbasic(ledgerBalance.getCurrentBal() + amount);
							}
							session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
						}System.out.println("LN1816 :: LIABLITY -VE :: Final Amount :: "+trd.getOnbasic());
					}else{//for income and expense
						if(trd.isDebit()==true){//if in debit true then subtract operation
							trd.setUpdatedBy(transaction.getUpdatedBy());
							trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
							trd.setGrossAmount(ledgerBalance.getOpeningBal());
							trd.setOnbasic(ledgerBalance.getCurrentBal() - amount);
						}else{// debit false then add operation
							trd.setUpdatedBy(transaction.getUpdatedBy());
							trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
							trd.setGrossAmount(ledgerBalance.getOpeningBal());
							trd.setOnbasic(ledgerBalance.getCurrentBal() + amount);
						}System.out.println("LN1828 :: Final Amount :: "+trd.getOnbasic());
						session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
					}
				}
			}
			
			session.insert("createTransaction", transaction);
			
			if(!(transaction.getIncomeExpense().equalsIgnoreCase("INCOME"))){
				String aYear=session.selectOne("getAcademicYearForDate1", transaction.getTransactionDate());
				if(aYear==null || aYear.trim().equalsIgnoreCase(""))
					aYear=session.selectOne("getAcademicYearForDate2", transaction.getTransactionDate());
				budget.setAcademicYear(aYear);
				budget.setDepartment(transaction.getDepartment());
				System.out.println("prior to budget::"+transaction.getIncomeExpense());
				budget.setActualIncome(0.0);
				budget.setActualExpense(amount);/* 12-03-2018 */
				System.out.println("*****************************EXPENSE");
				System.out.println(	budget.getAcademicYear()+"\n"+transaction.getIncomeExpense()+"\n"+
						budget.getDepartment()+"\n"+budget.getActualIncome()+"\n"+budget.getActualExpense());
				session.update("updateBudgetForIncomeExpenseAmount", budget);	
			}
			
			String twaCode = transaction.getTransactionCode();
			session.update("transactionWorkingAreaPaymentDone",twaCode);
			session.commit();
			status="Insert Successful";
		}catch(Exception e){
			status="Insert Failed";
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}finally{
			session.close();
		}
		return status;
	}



	@Override
	public List<LedgerWiseView> getLedgerWiseView(String from, String to, String ledger) {
		List<LedgerWiseView> allLedgerWiseViewList=new ArrayList<LedgerWiseView>();
		SqlSession session = null;
		try{
			session = sqlSessionFactory.openSession();
			Map<String, String> map = new HashMap<String, String>();
	        map.put("from", from);
	        map.put("to", to);
	        map.put("ledger", ledger);
	        
	        allLedgerWiseViewList=session.selectList("getLedgerWiseView",map);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return allLedgerWiseViewList;
	}

	/**changes from naimisha 02 jan 17**/
	
	@Override
	public String getTransactionWorkingAreaStatus(String twaCode) {
		SqlSession session=sqlSessionFactory.openSession();
		String transactionStatus = null;
		
		try{
			TransactionalWorkingArea transactionalWorkingArea = session.selectOne("getTransactionalWorkingAreaStatus", twaCode);
			transactionStatus = transactionalWorkingArea.getTransactionStatus();
			System.out.println("transactionStatus====="+transactionStatus);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
			return transactionStatus;
	}



	@Override
	public String updateTransactionWorkingArea(TransactionalWorkingArea transaction) {
		String status="fail";
		SqlSession session=sqlSessionFactory.openSession();
		try{
			//Utility util=new Utility();			
			transaction.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
			session.update("updateIntoTransactionWorkingArea",transaction);
			status="success";
		}catch(Exception e){
			status="fail";
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}
	
	/**
	 * @author naimisha.ghosh
	 * changes taken on 05062017
	 * */
			
	@Override
	public List<Group> getBalanceDetails(String from,String to) {
		SqlSession session =sqlSessionFactory.openSession();
		
		List<Group> parentList=null;
		List<Group> childList=null;
		List<Group> subChildList=null;
		List<Group> balanceDetails=new ArrayList<Group>();
		
		try{
			parentList=session.selectList("getBalanceDetails");
			for(Group g:parentList){
				double totalAmountCurrent = 0.0;
				double totalAmountPrevious = 0.0;
				Group group=new Group();
				group.setGroupName(g.getGroupName());
				List<Group> parentDetailList=new ArrayList<Group>();
				childList=session.selectList("getBalanceDetailsForChild",g.getGroupCode());
				
				for(Group g1: childList){
					subChildList=session.selectList("getBalanceDetailsForSubChild",g1.getGroupCode());
					Group gp = new Group();
					gp.setGroupName(g1.getGroupName());
					if(null !=subChildList && subChildList.size()!=0){
						Group gr1=new Group();
						gr1.setGroupName(g1.getGroupName());
					
						List<Group> childDetailList=new ArrayList<Group>();
						for(Group g2 : subChildList){
							if( g2.getGroupCode() !=""){
								Group gr=new Group();
								gr.setGroupName(g2.getGroupName());
								Group balanceCurrent = new Group();
								Group balancePrevious = new Group();
								String subChildGroupName = gr.getGroupName();
								
								/* added by sourav.bhadra on 08-08-2017
								 * to select total amount of 'INCOME' & 'EXPENCE' group
									for 'RESERVE AND SURPLUS' */
								if(gr.getGroupName().equalsIgnoreCase("RESERVES AND SURPLUS")){
									/* modified and added by sourav.bhadra on 21-08-2017 to select
									 * all ledgers of 'INCOME' and 'EXPENCE' groups */
									List<String> incomeLedgerList = session.selectList("getIncomeLedgerListForReserveAndSurplus");
									List<String> expenceLedgerList = session.selectList("getExpenceLedgerListForReserveAndSurplus");
									double incomeCurrentBalance = 0.0;
									double incomePreviousBalance = 0.0;
									
									double expenceCurrentBalance = 0.0;
									double expencePreviousBalance = 0.0;
									
									/* fetching INCOME ledgers current & previous balance */
									if(null != incomeLedgerList){
										for(String ledger : incomeLedgerList){
											/* fetching current data of INCOME */
											Map<String, String> map = new HashMap<String, String>();
											map.put("to", to);
											map.put("ledger",ledger);
											Double currentBalance = session.selectOne("ledgerCurrentBalanceForBS", map);
											if(null != currentBalance){
												incomeCurrentBalance += currentBalance;
											}else{
												incomeCurrentBalance += 0.0;
											}
											
											/* fetching previous data of INCOME */
											Map<String, String> map1 = new HashMap<String, String>();
											map1.put("to", from);
											map1.put("ledger",ledger);
											Double previousBalance = session.selectOne("ledgerCurrentBalanceForBS", map1);
											if(null != previousBalance){
												incomePreviousBalance += previousBalance;
											}else{
												incomePreviousBalance += 0.0;
											}
											
										}
									}else{
										incomeCurrentBalance = 0.0;
										incomePreviousBalance = 0.0;
									}
									
									/* fetching EXPENCE ledgers current & previous balance */
									if(null != expenceLedgerList){
										for(String ledger : expenceLedgerList){
											/* fetching current data of EXPENCE */
											Map<String, String> map = new HashMap<String, String>();
											map.put("to", to);
											map.put("ledger",ledger);
											Double currentBalance = session.selectOne("ledgerCurrentBalanceForBS", map);
											if(null != currentBalance){
												expenceCurrentBalance += currentBalance;
											}else{
												expenceCurrentBalance += 0.0;
											}
											
											/* fetching previous data of EXPENCE */
											Map<String, String> map1 = new HashMap<String, String>();
											map1.put("to", from);
											map1.put("ledger",ledger);
											Double previousBalance = session.selectOne("ledgerCurrentBalanceForBS", map1);
											if(null != previousBalance){
												expencePreviousBalance += previousBalance;
											}else{
												expencePreviousBalance += 0.0;
											}
											
										}
									}else{
										expenceCurrentBalance = 0.0;
										expencePreviousBalance = 0.0;
									}
									
									double totalIncomeExpenceCurrent = incomeCurrentBalance - expenceCurrentBalance;
									double totalIncomeExpencePrevious = incomePreviousBalance - expencePreviousBalance;
									balanceCurrent.setCurrentBalance(totalIncomeExpenceCurrent);
									balanceCurrent.setPrevBalance(totalIncomeExpencePrevious);
								}else{
									/* subchild groups other than 'RESERVE AND SURPLUS' */
									List<String> ledgerList = session.selectList("selectLedgersOfSubChildGroups", subChildGroupName);
									
									double totalCurrentbalance = 0.0;
									double totalPreviousbalance = 0.0;
									if(null != ledgerList){
										for(String ledger : ledgerList){
											/* selecting ledger's current balance */
											Map<String, String> map = new HashMap<String, String>();
											map.put("to", to);
											map.put("ledger",ledger);
											Double currentBalance = session.selectOne("ledgerCurrentBalanceForBS", map);
											if(null != currentBalance){
												totalCurrentbalance += currentBalance;
											}else{
												totalCurrentbalance += 0.0;
											}
											
											/* selecting ledger's previous balance */
											Map<String, String> map1 = new HashMap<String, String>();
											map1.put("to", from);
											map1.put("ledger",ledger);
											Double previousBalance = session.selectOne("ledgerCurrentBalanceForBS", map1);
											
											if(null != previousBalance){
												totalPreviousbalance += previousBalance;
											}else{
												totalPreviousbalance += 0.0;
											}
											
										}
										
										balanceCurrent.setCurrentBalance(totalCurrentbalance);
										balanceCurrent.setPrevBalance(totalPreviousbalance);
									}else{
										balanceCurrent.setCurrentBalance(0.0);
										balanceCurrent.setPrevBalance(0.0);
									}
									
								}
								if(null != balanceCurrent){
									gr.setCreditAmmount(balanceCurrent.getCurrentBalance());
									gr.setDebitAmmount(balanceCurrent.getPrevBalance());
									totalAmountCurrent += gr.getCreditAmmount();
									totalAmountPrevious += gr.getDebitAmmount();
								}else{
									gr.setDebitAmmount(0.00);
									gr.setCreditAmmount(0.00);
								}
								childDetailList.add(gr);
							}
							
						}
						gr1.setChildGroupList(childDetailList);
						parentDetailList.add(gr1);
					}else{
						Group childBalance = new Group();
						String childGroup = g1.getGroupName();
						List<String> ledgerList = session.selectList("selectLedgersOfChildGroups", childGroup);
						double totalChildCurrentbalance = 0.0;
						double totalChildPreviousbalance = 0.0;
						if(null != ledgerList){
							for(String ledger : ledgerList){
								Map<String, String> map = new HashMap<String, String>();
								map.put("to", to);
								map.put("ledger",ledger);
								Double currentBalance = session.selectOne("ledgerCurrentBalanceForBS", map);
								if(null != currentBalance){
									totalChildCurrentbalance += currentBalance;
								}else{
									totalChildCurrentbalance += 0.0;
								}
								
								Map<String, String> map1 = new HashMap<String, String>();
								map1.put("to", from);
								map1.put("ledger",ledger);
								Double previousBalance = session.selectOne("ledgerCurrentBalanceForBS", map1);
								if(null != previousBalance){
									totalChildPreviousbalance += previousBalance;
								}else{
									totalChildPreviousbalance += 0.0;
								}
								
							}
							
							childBalance.setCurrentBalance(totalChildCurrentbalance);
							childBalance.setPrevBalance(totalChildPreviousbalance);
						}else{
							childBalance.setCurrentBalance(0.0);
							childBalance.setPrevBalance(0.0);
						}
						
						if(null != childBalance){
							gp.setCreditAmmount(childBalance.getCurrentBalance());
							gp.setDebitAmmount(childBalance.getPrevBalance());
							totalAmountCurrent += gp.getCreditAmmount();
							totalAmountPrevious += gp.getDebitAmmount();
						}else{
					 		gp.setCreditAmmount(0.0);
							gp.setDebitAmmount(0.0);
						}
						
					}
				}
				group.setTotalAmount(totalAmountCurrent);
				group.setValue(totalAmountPrevious);
				group.setParentGroupList(parentDetailList);
				balanceDetails.add(group);
			}
			
	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		if(null != balanceDetails)
			return balanceDetails ;
		else
			return null;
	}
			
	
	@Override
	public List<Group> getAllBalance() {
		SqlSession session =sqlSessionFactory.openSession();
		
		List<Group> parentList=null;
		
		
		try{
			parentList=session.selectList("getAllBalanceDetails");
			//System.out.println("parentList size====="+parentList.size());
		}
			catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	
		return parentList;
		
	}
	
	
	@Override
	public List<Group> getChild(String parent) {
		SqlSession session =sqlSessionFactory.openSession();
		
		List<Group> child=null;
		
		
		try{
			child=session.selectList("getBalanceDetailsForSubChild",parent);
			//System.out.println("parentList size====="+parentList.size());
		}
			catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	
		return child;
		
	}
	
	
	@Override
	public List<Group> getSubGroupName(String name) {
		SqlSession session =sqlSessionFactory.openSession();
		
		List<Group> subList=null;
		
		
		try{
			subList=session.selectList("getBalanceDetailsForSubChild",name);
			//System.out.println("parentList size====="+parentList.size());
		}
			catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	
		return subList;
		
	}
	

	@Override
	public List<Group> getPrevBalance() {
		SqlSession session =sqlSessionFactory.openSession();
		
		List<Group> parentList=null;
		
		
		try{
			parentList=session.selectList("getPrevBalance");
			//System.out.println("parentList size====="+parentList.size());
		}
			catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	
		return parentList;
		
	}
	
	@Override
	public List<Group> getSubGroupAgainstParentList(String parent) {
		logger.info("In getGroupTypeList() method of FinanceDaoImpl");
		List<Group> GroupTypeList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			GroupTypeList = session.selectList("getSubGroupTypeList",parent);
			for(Group g:GroupTypeList)
			{
				System.out.println("aa"+g.getSubGroupName());
			}
			System.out.println("1908"+GroupTypeList);
		} catch (Exception e) {
			logger.error("In getGroupTypeList() method of FinanceDaoImpl", e);
		} finally {
			session.close();
		}
		return GroupTypeList;		
	}
	
	/**
	 * sourav.bhadra
	 * 01072017*/
	
	@Override
	public String getBudgetForAcademicYearAndResrveFund(String academicYear) {
		String reserveFund="";
		SqlSession session=sqlSessionFactory.openSession();
		try{
			Budget budget = session.selectOne("getPreviousYearUnallocatedFund", academicYear);
			/* modified by sourav.bhadra on 22-03-2018 */
			reserveFund = String.format("%.0f", budget.getReserveFund()) + "#";
			reserveFund += String.format("%.0f", budget.getExpectedIncome());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}		
		return reserveFund;
	}

	
	
	@Override
	public String updateReserveFund(Budget b) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "Insert Failed";
		try{
			session.update("updateReserveFund",b);
			status="Update Successful";
			session.commit();
			status = "Insert Successful";
		}catch(Exception e){
			status = "Insert Failed";
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}

	/**
	 * @author anup.roy
	 * 01072017*/
	
	@Override
	public String updateDepartmentFund(Budget b) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "Insert Failed";
		try{
			/* modified by sourav.bhadra on 22-03-2018 */
			String check = session.selectOne("checkDepartmentBudgetAvailability", b);
			if(null != check && check != ""){
				b.setExpectedExpense(b.getActualIncome());
				b.setTotalExpence(0.0);
				b.setReserveFund(b.getActualIncome());
				session.update("updateBudget", b);
				status="Update Successful";
			}else{
				b.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
				b.setExpectedExpense(b.getActualIncome());
				b.setReserveFund(b.getActualIncome());
				b.setTotalExpence(0.0);
				session.insert("saveBudget", b);
				/* added by sourav.bhadra on 17-04-2018 */
				String department = b.getDepartment();
				session.update("updateBudgetStatusInDepartmentTable", department);
				status = "Insert Successful";
			}
			
			session.commit();
			
		}catch(Exception e){
			status = "Insert Failed";
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}



	@Override
	public String getParentName(String code) {
		SqlSession session = sqlSessionFactory.openSession();
		String name = "";
		try{
			name=session.selectOne("getParentName",code);
			
			session.commit();
			
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			session.close();
		}
		return name;
	}



	@Override
	public List<Ledger> getLedgerListFromTransactionWorkingArea() {
		List<Ledger> ledgerList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			ledgerList = session.selectList("getLedgerListFromTransactionWorkingArea");
		} catch (Exception e) 
		{
			logger.error("In getLedgerListFromTransactionWorkingArea() method of FinanceDaoImpl",e);
			
			
		} finally {
			session.close();
		}
		
		return ledgerList;
	}



	@Override
	public List<Ledger> getLedgerForTransaction() {
		List<Ledger> ledgerList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try {
			ledgerList = session.selectList("getLedgerForTransaction");
		} catch (Exception e) 
		{
			logger.error("In getLedgerForTransaction() method of FinanceDaoImpl",e);
			
			
		} finally {
			session.close();
		}
		
		return ledgerList;
	}
	
	/********Added By Naimisha 02062017***********/
	@Override
	public List<TransactionalWorkingArea> getLedgerWithDetails(String ledgerCode) throws CustomException {
		SqlSession session = sqlSessionFactory.openSession();
		List<TransactionalWorkingArea> transactionWorkingAreaList = null;
		/*Address address = null;
		List<Subject> subjectList=null;*/
		try{
			transactionWorkingAreaList = session.selectList("selectLedgerWithDetails", ledgerCode);
			/* added by sourav.bhadra on 14-08-2017
			 * to fetch payment party for ledgers payment details */
			for(TransactionalWorkingArea twa : transactionWorkingAreaList){
				if(null != twa.getObjectId()){
					String voucher = twa.getObjectId();
					Map<String, String> map = new HashMap<String,String>();
			        map.put("voucherNo", voucher);
			        map.put("ledgerCode", ledgerCode);
			        String paidTo = session.selectOne("getPaymentParty", map);
			        twa.setPaidAgainst(paidTo);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return transactionWorkingAreaList;
	}



	@Override
	public String updateLedgerDetails(TransactionalWorkingArea transactionWorkingArea)  {
		String Status="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			if(null != transactionWorkingArea ){
				
				int updateStatus = session.update("updateLedgerDetails", transactionWorkingArea);
				int updateStatus1 = session.update("updateLedgerInNarration", transactionWorkingArea);
				System.out.println("updateStatus:"+updateStatus);
			}
		}catch(Exception e) {
			
			Status="fail";
			logger.error(e);
			
			e.printStackTrace();
		}finally{
			session.close();
		}
		return Status;
	}
	
	
	@Override
	public FinancialYear getCurrentFinancialYear() {
		FinancialYear financialYear = null;
	
		SqlSession session=sqlSessionFactory.openSession();
		try{
			
			financialYear = session.selectOne("getCurrentFinancialYear");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}		
		return financialYear;
	}



	@Override
	public List<IndividualCommodity> getIndividualCommodityDetailsForCurrentYear() {
		List<IndividualCommodity> individualCommodityList = null;
		
		SqlSession session=sqlSessionFactory.openSession();
		try{
			
			individualCommodityList = session.selectList("getIndividualCommodityDetails");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}		
		return individualCommodityList;
	}



	@Override
	public String insertDepreciationInTransactionWorkingArea(TransactionalWorkingArea transactionalWorkingArea) {
			String status = "success";
		
		SqlSession session =sqlSessionFactory.openSession();
		try{
			transactionalWorkingArea.setObjectId(encryptDecrypt.getBase64EncodedID("FinanceDaoImpl"));
			session.insert("insertDepreciationInTransactionWorkingArea", transactionalWorkingArea);
			//status="Insertion successful";
			session.commit();			
		}catch(Exception e){			
			status="fail";
			logger.error("In Create Group Method FinanceDaoImpl",e);
		}finally {
			session.close();
		}
		
		return status;
	}
	
	/**
	 * sourav.bhadra
	 * 04072017*/
	
	@Override
	public String getPreviousYearUnallocatedFund(String academicYear) {
		SqlSession session =sqlSessionFactory.openSession();
		String previousYearUnallocatedFund = "";
		try{
			Budget budget = session.selectOne("getPreviousYearUnallocatedFund", academicYear);
			if(null == budget){
				budget = new Budget();
				budget.setReserveFund(0.0);
				budget.setExpectedIncome(0.0);
			}
			/* modified by sourav.bhadra on 13-03-2018 */
			previousYearUnallocatedFund += String.format("%.0f", budget.getExpectedIncome())+"*#*"+String.format("%.0f", budget.getReserveFund());
		}catch(Exception e){
			e.printStackTrace();
		}
		return previousYearUnallocatedFund;
	}

	@Override
	public String saveBudgetDetails(Budget budgetDetails) {
		SqlSession session =sqlSessionFactory.openSession();
		String status = "";
		try{
			budgetDetails.setObjectId(encryptDecrypt.getBase64EncodedID("FinanceDaoImpl"));
			session.insert("saveBudgetDetailsAndUnallocatedFund", budgetDetails);
			status="Insertion successful";
			session.update("updateReseveFundLedger",budgetDetails);
			session.commit();			
		}catch(Exception e){			
			status="fail";
			e.printStackTrace();
			logger.error("In saveBudgetDetails Method FinanceDaoImpl",e);
		}finally {
			session.close();
		}
		return status;
	}
	
	@Override
	public String getTaxPercentageAgainstTaxCode(String taxCode) {
		SqlSession session =sqlSessionFactory.openSession();
		String taxPercentage = "";
		double percentage;
		try{
			percentage = session.selectOne("selectTaxPercentageAgainstTaxCode", taxCode);
			taxPercentage = Double.toString(percentage);
		}catch(Exception e){
			e.printStackTrace();
		}
		return taxPercentage;
	}
	
	@Override
	public String submitTaxPercentages(Tax tax) {
		SqlSession session =sqlSessionFactory.openSession();
		String status = "";
		try{
			tax.setObjectId(encryptDecrypt.getBase64EncodedID("FinanceDaoImpl"));
			session.insert("insertTaxPercentages",tax);
			status = "Success";
		}catch(Exception e){
			e.printStackTrace();
			status = "Fail";
		}
		return status;
	}

	@Override
	public List<Tax> getTaxPercentages() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Tax> taxList = null;
		try{
			taxList = session.selectList("getTaxDetails");
		}catch(Exception e){
			e.printStackTrace();
		}
		return taxList;
	}



	@Override
	public List<Resource> getResourceLedgerDetails(Resource resource) {
		SqlSession session =sqlSessionFactory.openSession();
		List<Resource> rasourceLedgerList = null;
		try{
			rasourceLedgerList = session.selectList("getResourceLedgerDetails",resource);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rasourceLedgerList;
	}



	@Override
	public List<Resource> getResourceDetailsAgainstResourceType(String resourceTypeName) {
		logger.info("In getAllUserIdList() method of BackOfficeDAOImpl: ");
		SqlSession session = sqlSessionFactory.openSession();
		List<Resource> resourceList = null;
		try {
			String resourceType = resourceTypeName;
			resourceList = session.selectList("selectAllUserIdAgainstResourceType",resourceType);
			
		} catch (NullPointerException e) {
			logger.error("Error In getTeachersFromStandardTeacherSubjectMappingList() method of ERPDAOImpl:NullPointerException:: "+ e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error In getTeachersFromStandardTeacherSubjectMappingList() method of ERPDAOImpl:Exception:: "+ e);
		} finally {
			session.close();
		}
		return resourceList;
	}
	
	/**Edit Tax Details
	 By Ranita.Sur 26072017**/
	@Override
	public String editTaxPercentages(Tax tax) {
		SqlSession session =sqlSessionFactory.openSession();
		String updateResponse = "";
		try{
			int updateStatus = session.update("saveTaxPercentageAndStatus", tax);
			if(updateStatus == 1){
				updateResponse = "Success";
			}else{
				updateResponse = "Fail";
			}
		}catch(Exception e){
			e.printStackTrace();
			updateResponse = "Fail";
		}
		return updateResponse;
	}
		
		
	
	/**Delete Tax Details
	 By Ranita.Sur 26072017**/
	@Override
	public String inactiveTaxDetails(String taxCode) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			Tax tax =new Tax();
			tax.setObjectId(encryptDecrypt.getBase64EncodedID("FinanceDAOImpl"));
			session.update("inactiveTaxDetails", taxCode);	
		}catch(Exception e) {
		
			status="fail";
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}
	/**UpdateLedgerName By Ranita.Sur27072017 **/
	@Override
	public String updateLedgerName(Ledger ledgers)  {
		String Status="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			/*modified by ranita.sur on 28082017 for ledger balance*/
			 String ledger =ledgers.getLedgerCode();
			 Ledger ledger1 = session.selectOne("selectBalanceForParentLedger", ledger);
			 if(ledger1.getOpeningBal()>ledgers.getOpeningBal())
			 {
				 Double differnece1=ledger1.getOpeningBal()-ledgers.getOpeningBal();
			     ledgers.setCurrentBal(ledger1.getCurrentBal()-differnece1);
				 int updateStatus = session.update("updateLedgerName", ledgers);
				 
			 }else{
				 Double differnece=ledgers.getOpeningBal()-ledger1.getOpeningBal();
				 ledgers.setCurrentBal(ledger1.getCurrentBal()+differnece);
				 int updateStatus = session.update("updateLedgerName", ledgers);
			 }
		}catch(Exception e) {
			Status="fail";
			logger.error(e);
			
			e.printStackTrace();
		}finally{
			session.close();
		}
		return Status;
	}
	/**
	 * @author ranita.sur For inserting into ledgerBalance
	 * changes taken on 16082017*/
	/* modified by sourav.bhadra on 28-08-2017
	 * for debit-credit operations */
	@Override
	public String saveForDaybook(Transaction transaction) {
		SqlSession session =sqlSessionFactory.openSession();	
		String status="Insert Failed";
		try{			
			Budget budget=new Budget();
			budget.setUpdatedBy(transaction.getUpdatedBy());
			transaction.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
			
			double amount=0.0;
			
			if(transaction.getTrDetList()!=null && transaction.getTrDetList().size()!=0){
				for(TransactionDetails trd:transaction.getTrDetList()){
					amount=trd.getAmount();
					String ledger = trd.getLedger();
					System.out.println("LN2697 Ledger :: "+ledger+"\tAMOUNT ::"+amount);
					Ledger ledger1 = new Ledger();
					/*added by ranita.sur on 18082017 for enry in daybook*/
					String ledgerGroupType=session.selectOne("selectGroupTypeForALedger", ledger);
					Ledger ledgerBalance = session.selectOne("selectBalanceForParentLedger", ledger);
					System.out.println("LN2702 :: Ledger Group Type :: "+ledgerGroupType+"\tCurrent Balance :: "+ledgerBalance.getCurrentBal());
					if(ledgerGroupType.equalsIgnoreCase("ASSET")){//debit type
						if(ledgerBalance.getCurrentBal() > 0 ){//if current balance is +ve then in debit side
							if(trd.isDebit()==true){//if in debit true then add operation
								trd.setUpdatedBy(transaction.getUpdatedBy());
								trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
								trd.setGrossAmount(ledgerBalance.getOpeningBal());
								trd.setOnbasic(ledgerBalance.getCurrentBal() + amount);
							}else{// debit false then subtract operation
								trd.setUpdatedBy(transaction.getUpdatedBy());
								trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
								trd.setGrossAmount(ledgerBalance.getOpeningBal());
								trd.setOnbasic(ledgerBalance.getCurrentBal() - amount);
							}System.out.println("LN2715 :: ASSET +VE :: Final Amount :: "+trd.getOnbasic());
							session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
						}else{//in credit side
							if(trd.isDebit()==true){//if in debit true then subtract operation
								trd.setUpdatedBy(transaction.getUpdatedBy());
								trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
								trd.setGrossAmount(ledgerBalance.getOpeningBal());
								trd.setOnbasic(ledgerBalance.getCurrentBal() + amount);
							}else{// debit false then add operation
								trd.setUpdatedBy(transaction.getUpdatedBy());
								trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
								trd.setGrossAmount(ledgerBalance.getOpeningBal());
								trd.setOnbasic(ledgerBalance.getCurrentBal() - amount);
							}System.out.println("LN2728 :: ASSET -VE :: Final Amount :: "+trd.getOnbasic());
							session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
						}
					}else if(ledgerGroupType.equalsIgnoreCase("LIABLITY")){//credit type
						if(ledgerBalance.getCurrentBal() > 0 ){//if current balance is +ve then in credit side
							if(trd.isDebit()==true){//if in debit true then subtract operation
								trd.setUpdatedBy(transaction.getUpdatedBy());
								trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
								trd.setGrossAmount(ledgerBalance.getOpeningBal());
								trd.setOnbasic(ledgerBalance.getCurrentBal() - amount);
							}else{// debit false then add operation
								trd.setUpdatedBy(transaction.getUpdatedBy());
								trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
								trd.setGrossAmount(ledgerBalance.getOpeningBal());
								trd.setOnbasic(ledgerBalance.getCurrentBal() + amount);
							}System.out.println("LN2743 :: LIABLITY +VE :: Final Amount :: "+trd.getOnbasic());
							session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
						}else{//in debit side
							if(trd.isDebit()==true){//if in debit true then add operation
								trd.setUpdatedBy(transaction.getUpdatedBy());
								trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
								trd.setGrossAmount(ledgerBalance.getOpeningBal());
								trd.setOnbasic(ledgerBalance.getCurrentBal() - amount);
							}else{// debit false then subtract operation
								trd.setUpdatedBy(transaction.getUpdatedBy());
								trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
								trd.setGrossAmount(ledgerBalance.getOpeningBal());
								trd.setOnbasic(ledgerBalance.getCurrentBal() + amount);
							}
							session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
						}System.out.println("LN2758 :: LIABLITY -VE :: Final Amount :: "+trd.getOnbasic());
					}else{//for income and expense
						if(trd.isDebit()==true){//if in debit true then subtract operation
							trd.setUpdatedBy(transaction.getUpdatedBy());
							trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
							trd.setGrossAmount(ledgerBalance.getOpeningBal());
							trd.setOnbasic(ledgerBalance.getCurrentBal() - amount);
						}else{// debit false then add operation
							trd.setUpdatedBy(transaction.getUpdatedBy());
							trd.setObjectId(encryptDecrypt.encrypt("FinanceDaoImpl"));
							trd.setGrossAmount(ledgerBalance.getOpeningBal());
							trd.setOnbasic(ledgerBalance.getCurrentBal() + amount);
						}System.out.println("LN2770 :: Final Amount :: "+trd.getOnbasic());
						session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
					}
				}
			}
			System.out.println("LN2797 :: "+transaction.getPaymentMode()+"\n"+transaction.getObjectId()+"\n"+transaction.getUpdatedBy()+"\n"+transaction.getTransactionDate()+"\n"+transaction.getVoucherTypeCode()+"\n"+transaction.getVoucherTypeName()+"\n"+transaction.getNarration());
			session.insert("createTransaction", transaction);
						
			
			if(!(transaction.getIncomeExpense().equalsIgnoreCase("INCOME"))){
				String aYear=session.selectOne("getAcademicYearForDate1", transaction.getTransactionDate());
				if(aYear==null || aYear.trim().equalsIgnoreCase(""))
					aYear=session.selectOne("getAcademicYearForDate2", transaction.getTransactionDate());
				budget.setAcademicYear(aYear);
				budget.setDepartment(transaction.getDepartment());
				System.out.println("prior to budget::"+transaction.getIncomeExpense());
				budget.setActualIncome(0.0);
				budget.setActualExpense(amount/2);
				
				session.update("updateBudgetForIncomeExpenseAmount", budget);	
			}
			/* added by sourav.bhadra on 21-04-2018 */
			if(null != transaction.getTransactionCode() && transaction.getTransactionCode() != ""){
				String twaCode = transaction.getTransactionCode();
				session.update("transactionWorkingAreaPaymentDone",twaCode);
			}
			session.commit();
			status="Insert Successful";
		}catch(Exception e){
			status="Insert Failed";
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}finally{
			session.close();
		}
		return status;
	}

	/**Addeb by ranita.sur on 08/08/2017 for edit daybook details**/
	@Override
	public String editDaybookDetails(Daybook dayBook) {
		SqlSession session = sqlSessionFactory.openSession();
		String status = "Update Failed.";
		try {
		int statusValue = session.update("editDaybookDetails", dayBook);
		if (statusValue != 0) {
				status = "update successful";
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
	/*added by rannita.sur on 18/08/2017 for current balance and opening balance in cashbook*/
	@Override
	public Double[] getCurrentBalanceAndOpeningBalance(String from, String to) {
		SqlSession session =sqlSessionFactory.openSession();
		Double[] balance = new Double[2];
		to += " 23:59:59";
		try{				
			Double openingBalance=session.selectOne("getOpeningBalance",from);
  			Double currentBalance=session.selectOne("getCurrentBalance",to);
  			if(openingBalance != null){
  			balance[0]=openingBalance;
  			}else{
  				balance[0]=0.0;	
  			 }
  			if(currentBalance != null){
  				balance[1]=currentBalance;
  			}else{
  				balance[1]=0.0;	
  			 }
  			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return balance;
	}

	/*Addeb by ranita.sur on 22/08/2017 for edit daybook details*/
	@Override
	public String getAllLedgerForDayBookEdit() {
		logger.info("In getBankDetails() method of FinanceDaoImpl");
		String ledgerName = "";
		SqlSession session =sqlSessionFactory.openSession();
		try {
			List<Ledger> ledger = null;
			ledger = session.selectList("getLedgerListForDaybook");
			for(Ledger l:ledger)
			{
				ledgerName +=l.getLedgerName()+"~";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("In getGroupTypeList() method of FinanceDaoImpl", e);
		} finally {
			session.close();
		}
		return ledgerName;
	}

	/*Addeb by ranita.sur on 22/08/2017 for edit daybook details*/
	@Override
	public String getAllVoucherForDayBookEdit() {
		logger.info("In getBankDetails() method of FinanceDaoImpl");
		String voucherType = "";
		SqlSession session =sqlSessionFactory.openSession();
		try {
			List<VoucherType> voucher = null;
			voucher = session.selectList("getVoucherTypeListForDaybook");
			for(VoucherType v:voucher)
			{
				voucherType +=v.getVoucherTypeName()+"~";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("In getGroupTypeList() method of FinanceDaoImpl", e);
		} finally {
			session.close();
		}
		return voucherType;
	}
	
	/**Added by @author Saif.Ali
	 * Date- 19/09/2017*/
	@Override
	public List<TransactionalWorkingArea> getTransactionalWorkingAreaListOfApprovedTransactions() {
		SqlSession session =sqlSessionFactory.openSession();
		List<TransactionalWorkingArea> getTransactionalWorkingAreaListOfApprovedTransactions=null;
		try{
			getTransactionalWorkingAreaListOfApprovedTransactions=session.selectList("getTransactionalWorkingAreaListOfApprovedTransactions");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		if(getTransactionalWorkingAreaListOfApprovedTransactions != null)
			return getTransactionalWorkingAreaListOfApprovedTransactions;
		else
			return null;
	}
	
	/**Added by @author Saif.Ali
	 * Date- 19/09/2017*/
	@Override
	public List<TransactionalWorkingArea> getTransactionalWorkingAreaListOfDonePayments() {
		SqlSession session =sqlSessionFactory.openSession();
		List<TransactionalWorkingArea> getTransactionalWorkingAreaListOfDonePayments=null;
		try{
			getTransactionalWorkingAreaListOfDonePayments=session.selectList("getTransactionalWorkingAreaListOfDonePayments");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		if(getTransactionalWorkingAreaListOfDonePayments != null)
			return getTransactionalWorkingAreaListOfDonePayments;
		else
			return null;
	}
	
	/* added by sourav.bhadra on 22-09-2017 */
	@Override
	public List<TransactionalWorkingArea> getLedgerDetailsWithinDateRange(String from, String to, String ledger) {
		SqlSession session =sqlSessionFactory.openSession();
		List<TransactionalWorkingArea> getLedgerDetials = null;
		to += " 23:59:59";
		try{
			Map<String, String> map = new HashMap<String,String>();
			map.put("from", from);
			map.put("to", to);
			map.put("ledger", ledger);
			getLedgerDetials = session.selectList("getLedgerDetailsWithinDateRange", map);
			for(TransactionalWorkingArea twa : getLedgerDetials){
				if(null != twa.getObjectId()){
					String voucher = twa.getObjectId();
					Map<String, String> map1 = new HashMap<String,String>();
					map1.put("voucherNo", voucher);
					map1.put("ledgerCode", ledger);
					String paidTo = session.selectOne("getPaymentParty", map1);
					twa.setPaidAgainst(paidTo);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return getLedgerDetials;
	}


	/* added by sourav.bhadra on 22-09-2017 */
	@Override
	public Double[] getOpeningAndClosingBalanceForAParticularLedger(String from, String to, String ledger) {
		SqlSession session =sqlSessionFactory.openSession();
		Double[] balances = new Double[2];
		to += " 23:59:59";
		try{
			Map<String, String> map = new HashMap<String,String>();
			map.put("from", from);
			map.put("ledger", ledger);
			Double openingBalance = session.selectOne("selectOpeningBalanceOfAParticularLedger", map);
			
			Map<String, String> map1 = new HashMap<String,String>();
			map1.put("to", to);
			map1.put("ledger", ledger);
			Double currentBalance = session.selectOne("selectClosingBalanceOfAParticularLedger", map1);
			
			if(openingBalance != null){
				balances[0]=openingBalance;
			}else{
				balances[0]=0.0;	
			}
			if(currentBalance != null){
				balances[1]=currentBalance;
			}else{
				balances[1]=0.0;	
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		return balances;
	}


	/* added by sourav.bhadra on 06-04-2018 */
	@Override
	public List<Department> getAllParentDepartments() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Department> deptList = new ArrayList<Department>();
		try{
			deptList = session.selectList("selectDeptsForDudgetAllocation");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return deptList;
	}



	@Override
	public List<SalaryBreakUp> getDeductionTypeSalaryBreakUpList(String breakUpType) {
		SqlSession session =sqlSessionFactory.openSession();
		List<SalaryBreakUp> breakUpList = new ArrayList<SalaryBreakUp>();
		try{
			breakUpList = session.selectList("selecteSalaryBreakUpListForABreakUpType",breakUpType);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return breakUpList;
	}



	@Override
	public String insertSalaryBreakUpLedgerMapping(SalaryBreakUp salaryBreakUp) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			salaryBreakUp.setSalaryBreakUpObjectId(encryptDecrypt.getBase64EncodedID("FinanceDaoImpl"));
			int insertValue = session.insert("insertSalaryBreakUpLedgerMapping", salaryBreakUp);
		}catch(Exception e) {
			insertStatus="fail";
			logger.error(e);
			e.printStackTrace();
			
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}finally{
			session.close();
		}
		return insertStatus;
	}



	@Override
	public List<SalaryBreakUp> getSalaryBreakUpLedgerMappingList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<SalaryBreakUp> leddgerMappedBreakUpList = new ArrayList<SalaryBreakUp>();
		try{
			leddgerMappedBreakUpList = session.selectList("getSalaryBreakUpLedgerMappingList");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return leddgerMappedBreakUpList;
	}


	/* added by sourav.bhadra on 20-04-2018 */
	@Override
	public String createVoucherType(VoucherType voucherType) {
		SqlSession session =sqlSessionFactory.openSession();
		String status = "";
		try{
			System.out.println("Voucher Type Name :: "+voucherType.getVoucherTypeName());
			System.out.println("Department :: "+voucherType.isDepartment());
			System.out.println("Income-Expence :: "+voucherType.isIncExp());
			System.out.println("Ticket No. :: "+voucherType.isTicketNo());
			System.out.println("Multiple Debit Ledger :: "+voucherType.isMultipleDebitLedger());
			
			voucherType.setObjectId(encryptDecrypt.getBase64EncodedID("FinanceDaoImpl"));
			int i = session.insert("insertVoucherType", voucherType);
			if(i != 0){
				status = "success";
			}
		}catch (Exception e) {
			e.printStackTrace();
			status = "fail";
		}
		return status;
	}

	/* added by sourav.bhadra on 20-04-2018 */
	@Override
	public String getVoucherTypeDetails(String voucherTypeCode) {
		SqlSession session =sqlSessionFactory.openSession();
		String voucherType = "";
		try{
			VoucherType vt = new VoucherType();
			vt = session.selectOne("selectAVoucherTypeDetails", voucherTypeCode);
			if(null != vt){
				voucherType += vt.isDepartment() + "*" + vt.isIncExp() + "*" + vt.isTicketNo() + "*" + vt.isMultipleDebitLedger();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return voucherType;
	}

	/* added by sourav.bhadra on 23-04-2018 */
	@Override
	public String getLedgerListForJournalVoucher() {
		SqlSession session =sqlSessionFactory.openSession();
		String ledgers = "";
		try{
			List<Ledger> ledgerList = session.selectList("selectLedgersForJournalVouchers");
			for(Ledger ledger : ledgerList){
				ledgers += ledger.getLedgerName() + "*";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ledgers;
	}

	/* added by sourav.bhadra on 24-04-2018 */
	@Override
	public List<Ticket> getTicketListForDayBook() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Ticket> ticketList = new ArrayList<>();
		try{
			ticketList = session.selectList("selectTicketListForDayBook");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ticketList;
	}

}