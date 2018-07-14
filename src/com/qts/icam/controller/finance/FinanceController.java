package com.qts.icam.controller.finance;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.qts.icam.model.backoffice.Fees;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.erp.Designation;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.erp.SalaryTemplate;
import com.qts.icam.model.finance.BalanceSheet;
import com.qts.icam.model.finance.Brs;
import com.qts.icam.model.common.Budget;
import com.qts.icam.model.finance.CashBook;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Daybook;
import com.qts.icam.model.common.DelarDetails;
import com.qts.icam.model.finance.DesignationSalaryDetails;
import com.qts.icam.model.finance.DisbursementSalaryDetails;
import com.qts.icam.model.finance.FeesLedgerMapping;
import com.qts.icam.model.finance.FinancialYear;
import com.qts.icam.model.finance.Group;
import com.qts.icam.model.finance.IncomeAndExpense;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.finance.Passbook;
import com.qts.icam.model.finance.SalaryBreakUp;
import com.qts.icam.model.finance.SalaryDisbursementList;
import com.qts.icam.model.finance.StaffSalaryDetails;
import com.qts.icam.model.finance.StudentFeesPayment;
import com.qts.icam.model.finance.Tax;
import com.qts.icam.model.common.TemplateLedgerMapping;
import com.qts.icam.model.finance.Transaction;
import com.qts.icam.model.finance.TransactionDetails;
import com.qts.icam.model.finance.TransactionalWorkingArea;
import com.qts.icam.model.finance.TrialBalance;
import com.qts.icam.model.finance.VendorPayment;
import com.qts.icam.model.finance.VoucherType;
import com.qts.icam.model.inventory.IndividualCommodity;
import com.qts.icam.model.library.BookRequisition;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.service.backoffice.BackOfficeService;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.erp.ERPService;
import com.qts.icam.service.finance.FinanceService;
import com.qts.icam.service.inventory.InventoryService;
import com.qts.icam.service.library.LibraryService;
import com.qts.icam.utility.customexception.CustomException;

import com.qts.icam.model.common.LedgerWiseView;

import com.qts.icam.model.common.ProfitAndLoss;

@Controller
@SessionAttributes("sessionObject")
public class FinanceController {
	public static Logger logger = Logger.getLogger(FinanceController.class);

	@Autowired
	FinanceService financeService;
	
	@Autowired
	LibraryService libraryService;
	
	@Autowired
	ERPService erpService;
	
	@Autowired
	BackOfficeService backOfficeService;
	
	@Autowired
	CommonService commonService; 
	
	@Autowired
	InventoryService inventoryService;

	/*********Modified by naimisha15122016************/
	
	@RequestMapping(value = "/groupCreatePage", method = RequestMethod.GET)
	public String groupCreatePage(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			List<Group> groupTypeList = financeService.getGroupTypeList();
			if (groupTypeList != null && groupTypeList.size() != 0)
				model.addAttribute("groupTypeList", groupTypeList);

			List<Group> parentGroupList = financeService.getGroupList();
			if (parentGroupList != null && parentGroupList.size() != 0)
				model.addAttribute("parentGroupList", parentGroupList);
			
			List<Group> groupList = financeService.getGroupList();
			if (groupList != null && groupList.size() != 0) {
				model.addAttribute("groupList", groupList);
				model.addAttribute("parentGroupList", groupList);
			}
			/*List<Group> groupTypeList = financeService.getGroupTypeList();
			if (groupTypeList != null && groupTypeList.size() != 0)
				model.addAttribute("groupTypeList", groupTypeList);*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "finance/groupCreate";
	}

	/**author naimisha**/
	/*@RequestMapping(value = "/groupListPage", method = RequestMethod.GET)
	public String groupListPage(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			List<Group> groupList = financeService.getGroupList();
			if (groupList != null && groupList.size() != 0) {
				model.addAttribute("groupList", groupList);
				model.addAttribute("parentGroupList", groupList);
			}
			List<Group> groupTypeList = financeService.getGroupTypeList();
			if (groupTypeList != null && groupTypeList.size() != 0)
				model.addAttribute("groupTypeList", groupTypeList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "finance/groupList";
	}*/

	/**author naimisha**/
	
	@RequestMapping(value = "/createGroup", method = RequestMethod.POST)
	public String createGroup(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Group group,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status = "";
		try {
			group.setUpdatedBy(sessionObject.getUserId());
			status = financeService.createGroup(group);
			model.addAttribute("submitResponse", status);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(status);
		}
		return groupCreatePage(request, response, model);
	}

	/***author naimisha**/
	
	// Creation of ledgers
	@RequestMapping(value = "/ledgerCreatePage", method = RequestMethod.GET)
	public String ledgerCreatePage(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			List<Group> total=new ArrayList<Group>();
			List<Group> groupList=new ArrayList<Group>();
			List<Ledger> ledgerList = financeService.getLedgerList();
			if (ledgerList != null && ledgerList.size() != 0) {
				model.addAttribute("parentLedgerList", ledgerList);
			}
			List<Group> parentgroup=financeService.getParentGroup();
			
			for(Group group : parentgroup){
				//Group g1=new Group();
				//g1.setGroupCode(group.getParentGroupName());
				System.out.println("ln173"+group.getParentGroupName());
				groupList = financeService.getChildGroupList(group.getParentGroupName());
				total.addAll(groupList);
				
			}
			
			System.out.println(total.size());
			if(total !=null && total.size() !=0){
				model.addAttribute("total",total);
			}
			
		
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "finance/ledgerCreate";
	}
	
	/**author naimisha**/
	/* modified by sourav.bhadra on 24-04-2018 to insert ledger type */
	@RequestMapping(value = "/createLedger", method = RequestMethod.POST)
	public String createLedger(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Ledger ledger,
			@RequestParam("subGroup") String subGroup,
			@RequestParam("ledgerType") String ledgerType,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status = "";
		try {
			/*if(ledger.getOpeningDrCr().equalsIgnoreCase("DR")){
				if(ledger.getOpeningBal()<0){
					ledger.setOpeningBal(ledger.getOpeningBal()*-1);
				}
			}else{
				if(ledger.getOpeningBal()>0){
					ledger.setOpeningBal(ledger.getOpeningBal()*-1);
				}
			}*/
			
			ledger.setUpdatedBy(sessionObject.getUserId());
			ledger.setSubGroupName(subGroup.trim().toUpperCase());
			ledger.setParentLedgerName(ledgerType);
			System.out.println(ledger.getLedgerName());
			
			status = financeService.createLedger(ledger);
			model.addAttribute("submitResponse", status);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(status);
		}
		return ledgerCreatePage(request, response, model);
	}
	
	/**author naimisha*/

	/*@RequestMapping(value = "/ledgerListPage", method = RequestMethod.GET)
	public String ledgerListPage(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {

			List<Ledger> ledgerList = financeService.getLedgerList();
			if (ledgerList != null && ledgerList.size() != 0) {
				model.addAttribute("ledgerList", ledgerList);
				// model.addAttribute("parentGroupList", groupList);
				model.addAttribute("parentLedgerList", ledgerList);
			}

			List<Group> groupList = financeService.getGroupList();
			if (groupList != null && groupList.size() != 0) {
				model.addAttribute("groupList", groupList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "finance/ledgerList";
	}*/
	
	/**
	 * @author sourav.bhadra
	 * changes taken on 27062017*/
	
	@RequestMapping(value = "/createTransactionPage", method = RequestMethod.GET)
	public ModelAndView createTransaction(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("twaCode") String twaCode) {
		ModelAndView modelAndView = new ModelAndView();
		//modelAndView.setViewName("finance/transactionCreate");
		modelAndView.setViewName("finance/entryForDayBook");
		try {
			List<Department> departmentList=commonService.selectAllDepartment();
			if(departmentList!=null && departmentList.size()!=0)
				model.addAttribute("departmentList", departmentList);
			
			List<VoucherType> voucherTypeList=financeService.getVoucherTypeList();
			if(voucherTypeList!=null && voucherTypeList.size()!=0)
				model.addAttribute("voucherTypeList", voucherTypeList);
			else
				model.addAttribute("message", "No Voucher List Available.");
			
			List<Ledger> ledgerList = financeService.getLedgerForTransaction();
			if (ledgerList != null && ledgerList.size() != 0)
				model.addAttribute("ledgerList", ledgerList);
			else
				model.addAttribute("message","No Transaction Available.<br>Approve A Transaction First.");
			
			TransactionalWorkingArea transactionalWorkingArea=financeService.getTransactionWorkingAreaDetails(twaCode);
			model.addAttribute("transactionalWorkingArea", transactionalWorkingArea);
			
			/* new added by sourav.bhadra on 26-06-2017 */
			List<Vendor> bankList=inventoryService.getBankDetailsList();
			model.addAttribute("bankList", bankList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * modified by saif.ali
	 * changes taken on 20092017*/

	@RequestMapping(value = "/saveTransaction", method = RequestMethod.POST)
	public String saveTransaction(HttpServletRequest request,
		HttpServletResponse response,
		ModelMap model,
		Transaction transaction,
		@RequestParam("debitLedger") String[] debitLedger,
		@RequestParam("debitAmount") String[] debitAmount,
		@RequestParam("creditLedger") String[] creditLedger,
		@RequestParam("creditAmount") String[] creditAmount,
		@RequestParam("creditBank") String[] creditBank,
		@RequestParam("debitBank") String[] debitBank,
		@ModelAttribute("sessionObject") SessionObject sessionObject){
		String status="";
		try{
			/* payment section */
			/* for bank */
			if(transaction.getPaymentMode().equalsIgnoreCase("BANK")){
				String chequeNo, bankName, bankAccountNo, bankCode, bankLocation;
				
				chequeNo = request.getParameter("chequeNo");
				bankName = request.getParameter("bankName");
				bankAccountNo = request.getParameter("accountNo");
				bankCode = request.getParameter("bankCode");
				bankLocation = request.getParameter("bankLocation");
				
				transaction.setChequeNo(chequeNo);
				transaction.setBankName(bankName);
				transaction.setBankAccountNo(bankAccountNo);
				transaction.setBankCode(bankCode);
				transaction.setBankLocation(bankLocation);
			}
			
			/* for money transfer */
			if(transaction.getPaymentMode().equalsIgnoreCase("MONEY_TRANSFER")){
				String vendorBankName, vendorBankAccountNo, vendorBankIFSCCode, vendorBankCode, vendorBankLocation;
				
				vendorBankName = request.getParameter("bankNames");
				vendorBankAccountNo = request.getParameter("vendorAccountNo");
				vendorBankIFSCCode = request.getParameter("vendorBankIfscCode");
				vendorBankCode = request.getParameter("vendorBankCode");
				vendorBankLocation = request.getParameter("vendorBankLocation");
				
				transaction.setBankName(vendorBankName);
				transaction.setBankAccountNo(vendorBankAccountNo);
				transaction.setBankIFSCCode(vendorBankIFSCCode);
				transaction.setBankCode(vendorBankCode);
				transaction.setBankLocation(vendorBankLocation);
			}
			
			List<TransactionDetails> transactionDetailsList=new ArrayList<TransactionDetails>();
			
			if(debitLedger!=null && debitLedger.length!=0){
				for(int i=0;i<debitLedger.length;i++){
					TransactionDetails transactionDetails=new TransactionDetails();
					transactionDetails.setLedger(debitLedger[i].trim().toUpperCase());
					transactionDetails.setAmount(Double.parseDouble(debitAmount[i]));
					transactionDetails.setDebit(true);
			
					transactionDetailsList.add(transactionDetails);
				}
			}
			if(creditLedger!=null && creditLedger.length!=0){
				for(int i=0;i<creditLedger.length;i++){
					TransactionDetails transactionDetails=new TransactionDetails();
					transactionDetails.setLedger(creditLedger[i].trim().toUpperCase());
					transactionDetails.setAmount(Double.parseDouble(creditAmount[i]));
					transactionDetails.setDebit(false);
								
					transactionDetailsList.add(transactionDetails);
				}
			}
			
			if(transactionDetailsList.size()!=0){
				transaction.setTrDetList(transactionDetailsList);
				transaction.setUpdatedBy(sessionObject.getUserId());
				transaction.setTransactionCode(transaction.getTransactionCode());
				status=financeService.saveTransaction(transaction);
				
			}else{
				status="No Transactions Found To Insert.";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println(status);
		}
		return getPaymentDoneListFromTransactionWorkingArea(request,response,model, sessionObject);/**Modified by Saif.Ali  Date- 19/09/2017*/
	}

	@RequestMapping(value = "/checkForBankGroup", method = RequestMethod.GET)
	public @ResponseBody
	String checkForBankGroup(@RequestParam("ledger") String ledger) {
		String checker = financeService.checkForBankGroup(ledger);

		return (new Gson().toJson(checker));
	}

	@RequestMapping(value = "/studentFeesPaymentTransactionPage", method = RequestMethod.GET)
	public ModelAndView studentFeesPaymentTransactionPage(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, StudentFeesPayment studentFeesPayment1) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("finance/studentFeesPaymentTransactionPage");

		String str = "";
				return modelAndView;
	}

	@RequestMapping(value = "/trialBalance", method = RequestMethod.GET)
	public ModelAndView trialBalance(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("finance/trialBalance");
		return modelAndView;
	}

	/**author naimisha**/
	
	
	/****************Modified by Naimisha*******************/
	@RequestMapping(value = "/getTrialBalance", method = RequestMethod.GET)
	public @ResponseBody
	String getTrialBalance(@RequestParam("from") String from,@RequestParam("to") String to) {
		
		to +="23:59:59";
		List<TrialBalance> tbList= financeService.getTrialBalance(from,to);
				
		
		String plTable="<table cellspacing='0' cellpadding='0' class='table table-bordered table-striped mb-none dataTable no-footer'>";
		plTable=plTable+"<tr><th colspan='2'>Account Head</th><th>Debit</th><th>Credit</th></tr>";
		
		/* modified by sourav.bhadra on 10-08-2017
		 * to change the view of trial balance */
		double debitTotal=0.0;
		double creditTotal=0.0;
		
		for (TrialBalance tb : tbList) {
			if (tb.isFound() == true && tb.getDebitAmount() != 0 && tb.getCreditAmount() != 0) {
				plTable=plTable+"<tr><td colspan='2'>"+tb.getLedger()+"</td><td>"+tb.getDebitAmount()+"</td><td>"+tb.getCreditAmount()+"</td></tr>";
				debitTotal=debitTotal+ tb.getDebitAmount();
				creditTotal=creditTotal+tb.getCreditAmount();
			}else{
				plTable=plTable+"<tr><td colspan='2'>"+tb.getLedger()+"</td><td>"+tb.getDebitAmount()+"</td><td>"+tb.getCreditAmount()+"</td></tr>";
				debitTotal=debitTotal+ tb.getDebitAmount();
				creditTotal=creditTotal+tb.getCreditAmount();
			}			
		}
		/* modified by sourav.bhadra on 23-08-2017 */
		plTable = plTable 
				+ "<tr style='border-top: thick; border-top-style: double; border-top-color: black;'><td colspan='2'><b>Grand Total ::</b><td>" + debitTotal
				+ "</td><td>" + creditTotal
				+ "</td></tr>";
		if(debitTotal != creditTotal){
			plTable += "<tr class='danger'><td colspan='4'><b>Credit And Debit Balances Are Not Matching.</b></td></tr>";
		}
		plTable += "</table>";
		return (new Gson().toJson(plTable));
	}
		
		

	/**
	 * modified by naimisha.ghosh
	 * changes taken 09062017*/
	
	
	//Modified Naimisha 18082017
	@RequestMapping(value = "/createIncomeAndExpense", method = RequestMethod.GET)
	public ModelAndView createIncomeAndExpense(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			 @ModelAttribute("sessionObject") SessionObject sessionObject) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("finance/incomeAndExpense");

		FinancialYear financialYear = financeService.getCurrentFinancialYear();
		if(null != financialYear){
			String sessionEndDate  = financialYear.getSessionEndDate();
			System.out.println("session end date===="+financialYear.getSessionEndDate());
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			String currentDate = dateFormat.format(date);
			System.out.println(dateFormat.format(date));
			//System.out.println("currentDate=="+currentDate);
			double totalDepreciatedValue = 0.0;
			if(currentDate.equalsIgnoreCase(sessionEndDate)){
				System.out.println("within");
				List<IndividualCommodity> individualCommodityList = financeService.getIndividualCommodityDetailsForCurrentYear();
				for(IndividualCommodity individualCm : individualCommodityList){
					 double depreciationAmount = (individualCm.getWarranty() * individualCm.getDepreciation())/100;
					 //System.out.println("depreciationAmount===="+depreciationAmount);
					 totalDepreciatedValue = totalDepreciatedValue + depreciationAmount;
				}
			//	System.out.println("totalDepreciatedValue==="+totalDepreciatedValue);
				
				TransactionalWorkingArea  transactionalWorkingArea = new TransactionalWorkingArea();
				transactionalWorkingArea.setUpdatedBy(sessionObject.getUserId());
				transactionalWorkingArea.setTransactionMode("CASH");
				transactionalWorkingArea.setIncomeExpense("EXPENSE");
				transactionalWorkingArea.setNetAmount(totalDepreciatedValue);
				transactionalWorkingArea.setDepartment("INVENTORY AND MESS");
				transactionalWorkingArea.setAcademicYear("depreciation");
				transactionalWorkingArea.setDescOfTransaction("Depreciation is Calculated");
				transactionalWorkingArea.setPaidAgainst("depreciation");
				transactionalWorkingArea.setTransactionalWorkingAreaDesc("DEPRECIATION");
				transactionalWorkingArea.setTransactionalWorkingAreaName("DEPRECIATION");
				String insertStatus = financeService.insertDepreciationInTransactionWorkingArea(transactionalWorkingArea);
				System.out.println("insertStatus==="+insertStatus);
			}
			
		}else{
			System.out.println("within else");
			model.addAttribute("msg", "Financial Year Not Configured Yet. Please Configure Financial Year");
		}
		return modelAndView;
	}
	
	/**
	 * author ranita.sur
	 * changes taken on 06082017
	 * */
	
	@RequestMapping(value = "/getIncomeAndExpenditure", method = RequestMethod.GET)
	public @ResponseBody
	String getIncomeAndExpense(@RequestParam("from") String from,@RequestParam("to") String to) {
		to +=" 23:59:59"; 
		List<IncomeAndExpense> ieList = financeService.getIncomeAndExpense(from, to);
		
		/* modified by ranita.sur on 09-08-2017
		 * to remove Income and Expense labels from table head */
		String plTable = "<table cellspacing='0' cellpadding='0' class='table table-bordered table-striped mb-none dataTable no-footer'><tr><td>";
		String expenseTable = "<table cellspacing='0' cellpadding='0' class='table table-bordered table-striped mb-none dataTable no-footer'><tr><th colspan = 2>Expense</th></tr><tr><th>Particulars</th><th>Amount</th></tr>";
		String incomeTable = "<table cellspacing='0' cellpadding='0' class='table table-bordered table-striped mb-none dataTable no-footer'><tr><th colspan = 2>Income</th></tr><tr><th>Particulars</th><th>Amount</th></tr>";
		double expenseTotal = 0.0;
		double incomeTotal = 0.0;
		int expenseCount = 0;
		int incomeCount = 0;
		int countDiff = 0;
		
		for (IncomeAndExpense p : ieList) {
			if (p.getIncomeExpense().equalsIgnoreCase("EXPENSE")) {
				//expenseTotal = expenseTotal + p.getAmount();
				expenseTable = expenseTable + "<tr><td>" + p.getLedger()
						+ "</td><td>" + p.getAmount() + "</td></tr>";
				expenseTotal = expenseTotal + p.getAmount();
				expenseCount ++;
			}
		}
		
		for (IncomeAndExpense p : ieList) {
			if (p.getIncomeExpense().equalsIgnoreCase("INCOME")) {
				//incomeTotal = incomeTotal + p.getAmount();
				incomeTable = incomeTable +"<tr><td>"+ p.getLedger()
						+ "</td><td>" + p.getAmount()+ "</td></tr>";
				incomeTotal = incomeTotal + p.getAmount();
				incomeCount ++;
			}
			
		}
		
		if(expenseCount > incomeCount){
			countDiff = expenseCount - incomeCount;
			for(int counter = 1; counter <= countDiff; counter++ ){
				incomeTable = incomeTable + "<tr><td></td></tr>";
			}
		}else{
			countDiff = incomeCount - expenseCount;
			for(int counter = 1; counter <= countDiff; counter++ ){
				expenseTable = expenseTable + "<tr><td></td></tr>";
			}
		}
		
		double difference=incomeTotal-expenseTotal;
		if(difference>0){
			expenseTable = expenseTable + "<tr><td>Surplus</td><td>" + Math.abs(difference) + "</td></tr>";
			expenseTotal = expenseTotal + Math.abs(difference);/* modified by ranita.sur on 09-08-2017 */
		}
		if(difference<0){
			incomeTable = incomeTable + "<tr><td>Deficit</td><td>" + Math.abs(difference) + "</td></tr>";
			incomeTotal=incomeTotal + Math.abs(difference);/* modified by ranita.sur on 09-08-2017 */	
		}
		
		plTable = plTable + expenseTable + "</table></td><td>";
		
		
		plTable = plTable + incomeTable + "</table></td></tr>";
				
		plTable = plTable 
				+ "<tr><td><b>Total ::</b>" + expenseTotal + "</td>"
				+ "<td><b>Total::</b>" + incomeTotal + "</td></tr>"
				+ "</table>";
		//System.out.println(plTable);
		return (new Gson().toJson(plTable));
	}

	

	@RequestMapping(value = "/cashBook",method= RequestMethod.GET)
	public ModelAndView cashBook(HttpServletRequest request,
									 HttpServletResponse response,
									 ModelMap model){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("finance/cashBook");
		
		return modelAndView;
	}	
	
	/**
	 * @author ranita.sur
	 * changes taken on 21082017
	 * */
	@RequestMapping(value = "/getCashBook", method = RequestMethod.GET)
	public String getCashBook(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam(required = false, value ="fromDate") String from,
			@RequestParam(required = false, value ="toDate") String to) {
		to += " 23:59:59";
		
		/*added by rannita.sur on 18/08/2017 for current balance and opening balance in cashbook*/
			try {
				List<TransactionalWorkingArea> cbList = financeService.getCashBook(from,to);
				Double[] balanceDetails= financeService.getCurrentBalanceAndOpeningBalance(from,to);
				Double openingbalance=balanceDetails[0];
				model.addAttribute("openingbalance", openingbalance);
				Double closingbalance = balanceDetails[1];
				model.addAttribute("closingbalance", closingbalance);
				double debit = 0.0;
				double credit = 0.0;
		
				for(TransactionalWorkingArea d:cbList){
					
					if(d.isDebit()==true)
					{
						debit += d.getNetAmount();
						model.addAttribute("debit", debit);
					}else{
						credit += d.getNetAmount();
						model.addAttribute("credit", credit);
					}
				}
				if (cbList != null) {
					model.addAttribute("cbList", cbList);
					/* added by sourav.bhadra on 10-08-2017
					 * to display fromDate and toDate after search */
					model.addAttribute("fromDate", from);
					model.addAttribute("toDate", to);
				} 
			}catch (Exception e) {
				logger.error("addVendor() In CommonController.java: Exception",e);
			}
			return "finance/cashBook";
	}
	
	
	
	@RequestMapping(value = "/passbook",method= RequestMethod.GET)
	public ModelAndView passbook(HttpServletRequest request,
									 HttpServletResponse response,
									 ModelMap model){
		ModelAndView modelAndView = new ModelAndView();
		List<String> allBanks=financeService.getAllBankNames();
		model.addAttribute("allBanks", allBanks);
		modelAndView.setViewName("finance/passbook");
		return modelAndView;
	}
	
	@RequestMapping(value = "/savePassbook",method= RequestMethod.POST)
	public ModelAndView savePassbook(HttpServletRequest request,
									 HttpServletResponse response,
									 ModelMap model,
									 @RequestParam("date") String[] date,
									 @RequestParam("particulars") String[] particulars,
									 @RequestParam("withdraw") String[] withdraw,
									 @RequestParam("instrumentNumber") String[] instrumentNumber,
									 @RequestParam("instrumentDate") String[] instrumentDate,
									 @RequestParam("debit") String[] debit,
									 @RequestParam("balance") String[] balance,
									 @RequestParam("bank") String[] bank,
									 @ModelAttribute("sessionObject") SessionObject sessionObject){
		System.out.println("sdfsfsdafsadfdskfhdsakjfh");
		try{
			List<Passbook> passbookList=new ArrayList<Passbook>();
			for(int i=0;i<date.length;i++){
				Passbook passbook=new Passbook();
				passbook.setDate(date[i]);
				passbook.setParticulars(particulars[i]);
				passbook.setWithdraw(Boolean.parseBoolean(withdraw[i]));
				passbook.setInstrumentNumber(instrumentNumber[i]);
				passbook.setInstrumentDate(instrumentDate[i]);
				passbook.setDebit(Boolean.parseBoolean(debit[i]));
				passbook.setBalance(Double.parseDouble(balance[i]));
				passbook.setBank(bank[i]);
				passbook.setUpdatedBy(sessionObject.getUserId());
				passbookList.add(passbook);
			}
			String message="No Records Found To Save.";
			if(passbookList.size()!=0)
				message=financeService.savePassbook(passbookList);
			model.addAttribute("message", message);
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return passbook(request, response, model);
	}
	
	
	
	
	@RequestMapping(value = "/feesTemplateLedgerMapping", method = RequestMethod.GET)
	public ModelAndView feesTemplateLedgerMapping(HttpServletRequest request,
													HttpServletResponse response,
													ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		
		List<Ledger> ledgerList=financeService.getLedgerList();
			model.addAttribute("ledgerList", ledgerList);
		
		List<Fees> feesList = null;
		try {
			feesList = backOfficeService.getFeesList();
		} catch (CustomException e) {
			e.printStackTrace();
		}
			model.addAttribute("feesList", feesList);
		
		
		modelAndView.setViewName("finance/mapFeesLedger");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/saveFeesLedgerMapping",method= RequestMethod.POST)
	public ModelAndView saveFeesLedgerMapping(HttpServletRequest request,
									 HttpServletResponse response,
									 ModelMap model,
									 @RequestParam("fees") String[] fees,
									 @ModelAttribute("sessionObject") SessionObject sessionObject){
		try {
			logger.info("Inside Method saveStudentFees-GET of FinanceController");
			List<FeesLedgerMapping> feesLedgerMappingList=new ArrayList<FeesLedgerMapping>();
			if(null!=fees && fees.length!=0){
				for(int i=0;i<fees.length;i++){
					FeesLedgerMapping flm=new FeesLedgerMapping();
					flm.setFeesCode(fees[i]);
					flm.setLedger(request.getParameter(fees[i]));
					flm.setUpdatedBy(sessionObject.getUserId());
					feesLedgerMappingList.add(flm);
				}
			}
			String status=financeService.saveFeesLedgerMapping(feesLedgerMappingList);		
		}catch (Exception e){
			System.out.println("exception"+e.getMessage());
			logger.error("Exception in method saveStudentFees-GET of FinanceController", e);
		}
		return feesTemplateLedgerMapping(request, response, model);
	}
	
	//****************************************************************************
	@RequestMapping(value = "/getNewStudents", method = RequestMethod.GET)
	public @ResponseBody
	String getNewStudents(@RequestParam("standard") String standard) {
		String student = null;
		try {
			logger.info("getNewStudents() In FinanceController.java");
			student = financeService.getNewStudents(standard);
		} catch (NullPointerException e) {
			logger.error("getNewStudents() In FinanceController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			logger.error("getNewStudents() In FinanceController.java: Exception"
					+ e);
		}
		return student;
	}
	
	@RequestMapping(value = "/getStudentFees",method= RequestMethod.GET)
	public ModelAndView getStudentFees(HttpServletRequest request,
									 HttpServletResponse response,
									 ModelMap model){
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			logger.info("Inside Method getStudentFees-GET of FinanceController");
			
			List<Standard> standardList=commonService.getStandards();
			model.addAttribute("standardList", standardList);
			
			List<String> allBanks=financeService.getAllBankNames();
			allBanks.add(0, "CASH A/C");
			model.addAttribute("allBanks", allBanks);
						
		} catch (Exception ce){
			System.out.println("exception"+ce.getMessage());
			logger.error("Exception in method getStudentFees-GET of FinanceController", ce);
		}
		
		modelAndView.setViewName("finance/studentFees");
		return modelAndView;
	}
	
	
	
	@RequestMapping(value = "/getStudentFeesPaymentDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getStudentFeesPaymentDetails(@RequestParam("standard") String standard,
										@RequestParam("rollNumber") String rollNumber,
										@RequestParam("section") String section) {
		String fees="";
		StudentFeesPayment studentFeesPayment=new StudentFeesPayment();
		studentFeesPayment.setStandard(standard);
		studentFeesPayment.setRollNumber(rollNumber);
		studentFeesPayment.setSection(section);
		fees=financeService.getStudentFeesPaymentDetails(studentFeesPayment);
		return (new Gson().toJson(fees));
	}
	
	
//	@RequestMapping(value = "/saveStudentFees",method= RequestMethod.POST)
//	public ModelAndView saveStudentFees(HttpServletRequest request,
//									 HttpServletResponse response,
//									 ModelMap model,
//									 @RequestParam("fees") String[] fees,
//									 StudentFeesPayment studentFeesPayment,
//									 
//									 @RequestParam("bankLedger") String[] bankLedger,
//									 @RequestParam("ledgerAmount") String[] ledgerAmount,
//									 @RequestParam("chequeDraft") String[] chequeDraft,
//									 
//									 @ModelAttribute("sessionObject") SessionObject sessionObject){		
//		try {
//			logger.info("Inside Method saveStudentFees-GET of FinanceController");
//			List<StudentFeesPaymentDetails> sfpdList=new ArrayList<StudentFeesPaymentDetails>();
//			List<TransactionDetails> atdList=new ArrayList<TransactionDetails>();
//			if(null!=fees && fees.length!=0){
//				String feesStatus="PAID";
//				for(int i=0;i<fees.length;i++){
//					double total=Double.parseDouble(request.getParameter(fees[i]+"Total"));
//					double paid=Double.parseDouble(request.getParameter(fees[i]+"Paid"));
//					double pending=Double.parseDouble(request.getParameter(fees[i]+"Pending"));
//					double payable=Double.parseDouble(request.getParameter(fees[i]+"Payable"));
//					paid=paid+payable;
//					pending=total-paid;
//					
//					StudentFeesPaymentDetails sfpd=new StudentFeesPaymentDetails();
//					sfpd.setFees(fees[i]);
//					sfpd.setTotalAmount(total);
//					sfpd.setPaidAmount(paid);
//					sfpd.setPendingAmount(pending);
//					sfpd.setPayAmount(payable);
//					
//					TransactionDetails atd=new TransactionDetails();
//					atd.setLedger(request.getParameter(fees[i]+"Ledger"));
//					atd.setAmount(payable);
//					atd.setDebit(false);
//					atdList.add(atd);
//					
//					if(pending>0)
//						feesStatus="REMAINING";
//						
//					sfpdList.add(sfpd);
//				}
//				studentFeesPayment.setUpdatedBy(sessionObject.getUserId());
//				studentFeesPayment.setFeesStatus(feesStatus);
//				studentFeesPayment.setStudentFeesPaymentDetailsList(sfpdList);
//				
//				
//				
//				if(null!=bankLedger && bankLedger.length!=0){
//					for(int i=0;i<bankLedger.length;i++){
//						double amount=Double.parseDouble(ledgerAmount[i]);
//						if(amount>0){
//							TransactionDetails atd=new TransactionDetails();
//							atd.setLedger(bankLedger[i]);
//							atd.setAmount(amount);
//							atd.setDebit(true);
//							if(! bankLedger[i].equalsIgnoreCase("CASH A/C")){
//								atd.setChequeNumber(chequeDraft[i]);
//							}
//							atdList.add(atd);
//						}
//					}
//				}
//				
//				Transaction at=new Transaction();
//				at.setUpdatedBy(sessionObject.getUserId());
//				at.setNarration(request.getParameter("narration"));
//				at.setId(studentFeesPayment.getRollNumber());
//				at.setReceiptChallan("RECEIPT");
//				at.setReason("FEES");
//				at.setYearType("ACADEMIC");
//				at.setVoucherType("RECEIPT");
//				at.setTransactionDetailsList(atdList);
//				String status=financeService.saveStudentFees(studentFeesPayment, at);
//			}
//		} catch (Exception e){
//			e.printStackTrace();
//			System.out.println("exception"+e.getMessage());
//			logger.error("Exception in method saveStudentFees-POST of FinanceController", e);
//		}
//		return getStudentFees(request, response, model);
//	}
	
	
	
	@RequestMapping(value = "/getAllPurchaseOrdersForPayment",method= RequestMethod.GET)
	public ModelAndView getAllPurchaseOrdersForPayment(HttpServletRequest request,
									 HttpServletResponse response,
									 ModelMap model){
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			logger.info("Inside Method getAllPurchaseOrdersForPayment-GET of FinanceController");
			List<String> allBanks=financeService.getAllBankNames();
			allBanks.add(0, "CASH A/C");
			model.addAttribute("allBanks", allBanks);
			
			List<VendorPayment> vendorPaymentList=financeService.getAllPurchaseOrdersForPayment();
			model.addAttribute("vendorPaymentList", vendorPaymentList);		
		} catch (Exception ce){
			System.out.println("exception"+ce.getMessage());
			logger.error("Exception in method getAllPurchaseOrdersForPayment-GET of FinanceController", ce);
		}
		
		modelAndView.setViewName("finance/listAllPurchaseOrders");
		return modelAndView;
	}
	
	
//	@RequestMapping(value = "/makeVendorPayment",method= RequestMethod.POST)
//	public ModelAndView makeVendorPayment(HttpServletRequest request,
//									 HttpServletResponse response,
//									 ModelMap model,
//									 @ModelAttribute("sessionObject") SessionObject sessionObject){		
//		try {
//			logger.info("Inside Method makeVendorPayment-GET of FinanceController");
//			
//			List<TransactionDetails> atdList=new ArrayList<TransactionDetails>();
//			
//			TransactionDetails atd1=new TransactionDetails();
//			atd1.setLedger("PURCHASE A/C");
//			atd1.setAmount(Double.parseDouble(request.getParameter("amount")));
//			atd1.setDebit(true);			
//			atdList.add(atd1);
//			
//			TransactionDetails atd2=new TransactionDetails();
//			atd2.setLedger(request.getParameter("bankLedger"));
//			atd2.setAmount(Double.parseDouble(request.getParameter("amount")));
//			atd2.setDebit(false);
//			if(! request.getParameter("bankLedger").equalsIgnoreCase("CASH A/C")){
//				atd2.setChequeNumber(request.getParameter("chequeNo"));
//			}
//			atdList.add(atd2);
//			
//			Transaction at=new Transaction();
//			at.setUpdatedBy(sessionObject.getUserId());
//			at.setNarration(request.getParameter("narration"));
//			at.setId(request.getParameter("poCode"));
//			at.setReceiptChallan("CHALLAN");
//			at.setReason("VENDOR PAYMENT");
//			at.setYearType("FINANTIAL");
//			at.setVoucherType("PAYMENT");
//			at.setTransactionDetailsList(atdList);
//			
//			at.setStatus(request.getParameter("poType"));
//			
//			
//			VendorPayment vp=new VendorPayment();
//			vp.setPoCode(request.getParameter("poCode"));
//			vp.setUpdatedBy(sessionObject.getUserId());
//			vp.setStatus(request.getParameter("poType"));
//			vp.setPayAmount(Double.parseDouble(request.getParameter("amount")));
//			if((Double.parseDouble(request.getParameter("maxAmount"))-Double.parseDouble(request.getParameter("amount")))>0.0)
//				vp.setPaidStatus("PARTIAL");
//			else
//				vp.setPaidStatus("PAID");
//			String status=financeService.makeVendorPayment(at, vp);
//			
//		} catch (Exception e){
//			e.printStackTrace();
//			System.out.println("exception"+e.getMessage());
//			logger.error("Exception in method makeVendorPayment-POST of FinanceController", e);
//		}
//		return getAllPurchaseOrdersForPayment(request, response, model);
//	}
	
	
	@RequestMapping(value = "/getSalaryBreakUpCalculator", method = RequestMethod.GET)
	public String getSalaryBreakUpCalculator(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			logger.info("Inside Method getSalaryBreakUpCalculator(HttpServletRequest request, HttpServletResponse response, ModelMap model) of BackOfficeController");
			List<Employee> staffCodeList = financeService.getAllStaffCodeList();
			if (staffCodeList != null && staffCodeList.size() != 0) {
				model.addAttribute("staffCodeList", staffCodeList);
			}
			List<SalaryBreakUp> breakUpList = financeService.getAllSalaryBreakUpList();
			if (breakUpList != null && breakUpList.size() != 0) {
				model.addAttribute("breakUpList", breakUpList);
			}
			
			
		} catch (Exception e) {

			logger.error("Exception in method getSalaryBreakUpCalculator(HttpServletRequest request, HttpServletResponse response, ModelMap model) of BackOfficeController",e);
		}

		return "finance/salaryBreakUpCalculator";
	}
	
//	@ResponseBody
//	@RequestMapping(value = "/getCalculatedValueOfGivenFormula", method = RequestMethod.GET)
//	public String getCalculatedValueOfGivenFormula(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model,
//			@RequestParam("finalFormula") String finalFormulaStr) {
//		logger.info("In getCalculatedValueOfGivenFormula() method of FinanceController: ");
//		String output = null;
//		try {
//			Utility util = new Utility();		
//			double value = util.evaluate(finalFormulaStr);
//			output = Double.toString(value);
//
//		} catch (Exception e) {
//			logger.error("Error in FinanceController getCalculatedValueOfGivenFormula() method for Exception: ", e);
//		}
//		return (new Gson().toJson(output));
//	}
	

	@RequestMapping(value = "/getEmployeeSalaryDetails", method = RequestMethod.GET)
	public ModelAndView getEmployeeSalaryDetails(ModelMap model) {
		logger.info("Inside Method getEmployeeSalaryDetails-GET of FinanceController");
		try {
			List<Employee> staffCodeList = financeService.getAllStaffCodeList();
			if (staffCodeList != null && staffCodeList.size() != 0) {
				model.addAttribute("staffCodeList", staffCodeList);
			}
			List<SalaryTemplate> salaryTemplateList = null;//erpService.getAllSalaryTemplates();
			if (salaryTemplateList != null && salaryTemplateList.size() != 0) {
				model.addAttribute("salaryTemplateList", salaryTemplateList);
			}
		} catch (Exception e) {
			logger.error("Exception in method getEmployeeSalaryDetails-GET of FinanceController", e);
		}
		return new ModelAndView("finance/staffSalarydetails");
	}
	
	/*@RequestMapping(value = "/getStaffSalaryDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getStaffDetails(@RequestParam("employeeCode") String strEmployeeCode,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("In getStaffSalaryDetails() Ajax method of FinanceController");
		String sm = "";
		try {
			StaffSalaryDetails staffSalaryDetails = financeService.getStaffSalaryDetails(strEmployeeCode);
			
			if (staffSalaryDetails != null) {				
				if (staffSalaryDetails.getSalaryTemplate().getSalaryTemplateCode() == null) {
					staffSalaryDetails.getSalaryTemplate().setSalaryTemplateCode(" ");
				}
//				if (staffSalaryDetails.getSalaryTemplate().getFixationOfPay().getFixationOfPayCode() == null) {
//					staffSalaryDetails.getSalaryTemplate().getFixationOfPay().setFixationOfPayCode(" ");
//				}
//				if (staffSalaryDetails.getSalaryTemplate().getFixationOfPay().getFixationOfPayId() == 0) {
//					staffSalaryDetails.getSalaryTemplate().getFixationOfPay().setFixationOfPayId(0);
//				}
//				if (staffSalaryDetails.getSalaryTemplate().getFixationOfPay().getFixationOfPayDesc() == null) {
//					staffSalaryDetails.getSalaryTemplate().getFixationOfPay().setFixationOfPayDesc(" ");
//				}
				if (staffSalaryDetails.getBasic() == 0) {
					staffSalaryDetails.setBasic(0);
				}
				if (staffSalaryDetails.getEd() == 0) {
					staffSalaryDetails.setEd(0);
				}
				if (staffSalaryDetails.getGip() == 0) {
					staffSalaryDetails.setGip(0);
				}
				if (staffSalaryDetails.getEd()==0) {
					staffSalaryDetails.setEd(0);
				}
//				sm = staffSalaryDetails.getSalaryTemplate().getSalaryTemplateCode()+ "*" 
//						+ staffSalaryDetails.getSalaryTemplate().getFixationOfPay().getFixationOfPayName()+"(" + staffSalaryDetails.getSalaryTemplate().getFixationOfPay().getFixationOfPayStartRange() + "-" + staffSalaryDetails.getSalaryTemplate().getFixationOfPay().getFixationOfPayEndRange() + ")" +"*"
//						+ staffSalaryDetails.getSalaryTemplate().getFixationOfPay().getFixationOfPayId() + "*"
//						+ staffSalaryDetails.getSalaryTemplate().getFixationOfPay().getFixationOfPayDesc() + "*"
//						+ staffSalaryDetails.getBasic() + "*" 
//						+ staffSalaryDetails.getGpf() + "*"
//						+ staffSalaryDetails.getEd() + "*"
//						+ staffSalaryDetails.getWc() + "*" 
//						+ staffSalaryDetails.getFreeElectricCharge() + "*"
//						+ staffSalaryDetails.getIp() + "*"						
//						+ staffSalaryDetails.getGip() + "*"						
//						+ staffSalaryDetails.getPt() + "*"
//						+ staffSalaryDetails.getNps() + "*" 						
				//		+ staffSalaryDetails.getNpsBoth() + "*"
				//		+ staffSalaryDetails.getSalaryTemplate().getFixationOfPay().getFixationOfPayCode();
			}			
		} catch (NullPointerException e) {
			logger.error(
					"Error in FinanceController getStaffSalaryDetails() method for NullPointerException: ",
					e);
		} catch (Exception e) {
			logger.error(
					"Error in FinanceController getStaffSalaryDetails() method for Exception: ",
					e);
		}
		return sm;
	}*/
	

	
	@RequestMapping(value = "/submitStaffSalaryDetails",method= RequestMethod.POST, params = "submit")
	public ModelAndView submitStaffSalaryDetails(HttpServletRequest request,
									 HttpServletResponse response,
									 ModelMap model,
									 StaffSalaryDetails staffSalaryDetails,
									 @ModelAttribute("sessionObject") SessionObject sessionObject){		
		try {
			logger.info("Inside Method submitStaffSalaryDetails-POST of FinanceController");	
			staffSalaryDetails.setUpdatedBy(sessionObject.getUserId());
			String status=financeService.addStaffSalaryDetails(staffSalaryDetails);	
			model.addAttribute("insertStatus", status);
		} catch (Exception e){
			logger.error("Exception in method submitStaffSalaryDetails-POST of FinanceController", e);
		}
		return getEmployeeSalaryDetails(model);
	}
	
	@RequestMapping(value = "/submitStaffSalaryDetails",method= RequestMethod.POST, params = "update")
	public ModelAndView editStaffSalaryDetails(HttpServletRequest request,
									 HttpServletResponse response,
									 ModelMap model,
									 StaffSalaryDetails staffSalaryDetails,
									 @ModelAttribute("sessionObject") SessionObject sessionObject){		
		try {
			logger.info("Inside Method editStaffSalaryDetails-POST of FinanceController");	
			staffSalaryDetails.setUpdatedBy(sessionObject.getUserId());
			String status=financeService.editStaffSalaryDetails(staffSalaryDetails);
			model.addAttribute("updateStatus", status);
		} catch (Exception e){
			logger.error("Exception in method editStaffSalaryDetails-POST of FinanceController", e);
		}
		return getEmployeeSalaryDetails(model);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/designationSalaryDetails", method = RequestMethod.GET)
	public String designationSalaryDetails(HttpServletRequest request, HttpServletResponse response, ModelMap model) {			
		List<Designation> designationList=null;
		try {
			logger.info("Excecuting designationSalaryDetails() from financeController, calling getAllDesignation() in erpService.java");
			designationList = erpService.getAllDesignation();							
			model.addAttribute("designationList", designationList);			
		} catch (NullPointerException e) {
			logger.error("Exception occured in designationSalaryDetails() from financeController : ",e);
		} catch (Exception e) {
			logger.error("Exception occured in designationSalaryDetails() from financeController : ",e);
		}
		return "finance/designationSalaryDetails";
	}
	
	
	@RequestMapping(value = "/saveDesignationSalaryDetails",method= RequestMethod.POST)
	public String saveDesignationSalaryDetails(HttpServletRequest request,
									 HttpServletResponse response,
									 ModelMap model,
									 DesignationSalaryDetails designationSalaryDetails,
									 @ModelAttribute("sessionObject") SessionObject sessionObject){		
		try {
			logger.info("Inside Method saveDesignationSalaryDetails-POST of FinanceController");	
			designationSalaryDetails.setUpdatedBy(sessionObject.getUserId());
			String status=financeService.saveDesignationSalaryDetails(designationSalaryDetails);
			model.addAttribute("updateStatus", status);
		} catch (Exception e){
			logger.error("Exception in method saveDesignationSalaryDetails-POST of FinanceController", e);
			e.printStackTrace();
		}
		return designationSalaryDetails(request, response, model);
	}
	
	
	@RequestMapping(value = "/getDesignationSalaryDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getDesignationSalaryDetails(@RequestParam("designation") String designation,
			@RequestParam("parameter") String parameter,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("In getDesignationSalaryDetails() Ajax method of FinanceController");
		String sm = "";
		try {
			DesignationSalaryDetails designationSalaryDetails = financeService.getDesignationSalaryDetails(designation,parameter);
			
			if (designationSalaryDetails != null) {				
				sm = sm + designationSalaryDetails.getDa()+ "*"+
						designationSalaryDetails.getTptl()+ "*"+
						designationSalaryDetails.getSmaHma()+ "*"+
						designationSalaryDetails.getMa()+ "*"+
						designationSalaryDetails.getSa()+ "*"+
						designationSalaryDetails.getGpf()+ "*"+
						designationSalaryDetails.getCpf()+ "*"+
						designationSalaryDetails.getMeterCharge()+ "*"+
						designationSalaryDetails.getUpto100ECRate()+ "*"+
						designationSalaryDetails.getAbove100ECRate()+ "*UPDATE";
			}			
		} catch (NullPointerException e) {
			logger.error(
					"Error in FinanceController getDesignationSalaryDetails() method for NullPointerException: ",
					e);
		} catch (Exception e) {
			logger.error(
					"Error in FinanceController getDesignationSalaryDetails() method for Exception: ",
					e);
		}
		return sm;
	}
	
	
	@RequestMapping(value = "/openDisbursementSalaryDetails", method = RequestMethod.GET)
	public String openDisbursementSalaryDetails(HttpServletRequest request,
													HttpServletResponse response,
													ModelMap model,
													@RequestParam("resourceType") String resourceType,
													@RequestParam("month") String month,
													@RequestParam("type") String type) {
		logger.info("Inside Method getDisbursementSalaryDetails-GET of FinanceController");
		String strView = "finance/disbursementSalaryDetails"+type;
		try {
			System.out.println(strView);
			SalaryDisbursementList sdl=new SalaryDisbursementList();
			sdl.setMonth(month);
			sdl.setResourceType(resourceType);
			List<Employee> staffCodeList =new ArrayList<Employee>();
			
			if(type.equalsIgnoreCase("Insert")){
				staffCodeList = financeService.getStaffCodeListToDisburseSalary(sdl);
			}else{
				staffCodeList = financeService.getSalaryDisbursedStaffList(sdl);
			}
			if (staffCodeList != null && staffCodeList.size() != 0) {
				model.addAttribute("staffCodeList", staffCodeList);
			}else{
				return getSalaryDisbursementList(request, response, model, "NA");
			}
			model.addAttribute("month", month);
			model.addAttribute("resourceType", resourceType);
		} catch (Exception e) {
			logger.error("Exception in method getDisbursementSalaryDetails-GET of FinanceController", e);
		}
		return strView;
	}
	
	
	
	
	@RequestMapping(value = "/getSalaryDisbursementList", method = RequestMethod.GET)
	public String getSalaryDisbursementList(HttpServletRequest request,
						HttpServletResponse response,
						ModelMap model,
						@RequestParam("month") String month) {
		String strView = "finance/salaryDisbursementList";
		
		try {
			logger.info("Inside Method getSalaryDisbursementList-GET of FinanceController");
			if(!month.equalsIgnoreCase("NA")){
				List<SalaryDisbursementList> salaryDisbursementList=financeService.getSalaryDisbursementList(month);
				if (salaryDisbursementList != null && salaryDisbursementList.size() != 0) {
					model.addAttribute("salaryDisbursementList", salaryDisbursementList);
				}				
			}
			model.addAttribute("month", month);
		} catch (Exception e){
			System.out.println("exception"+e.getMessage());
			logger.error("Exception in method getSalaryDisbursementList-GET of AcademicsController", e);
		}
		return strView;
	}
	
	
	@RequestMapping(value = "/viewStaffDisbursedSalaryDetails", method = RequestMethod.GET)
	public @ResponseBody
	String viewStaffDisbursedSalaryDetails(@RequestParam("employeeCode") String employeeCode,
									@RequestParam("month") String month,
									HttpServletRequest request,
									HttpServletResponse response) {
		logger.info("In viewStaffSalaryDetails() Ajax method of FinanceController");
		String sm = "";
		try {
			DisbursementSalaryDetails dsd=new DisbursementSalaryDetails();
			dsd.setEmployee(employeeCode);
			dsd.setMonth(month);
			dsd=financeService.viewStaffDisbursedSalaryDetails(dsd);
			if(null!=dsd){
				sm=sm+dsd.getPay()+"*"+
						dsd.getGradePay()+"*"+
						dsd.getBasic()+"*"+
						dsd.getDa()+"*"+
						dsd.getSmaHma()+"*"+
						dsd.getTptl()+"*"+
						dsd.getMa()+"*"+
						dsd.getSa()+"*"+
						dsd.getArrear()+"*"+
						dsd.getMiscInc()+"*"+						
						dsd.getEd()+"*"+
						dsd.getNps()+"*"+						
						dsd.getGpf()+"*"+
						dsd.getCpf()+"*"+
						dsd.getNpsBoth()+"*"+
						dsd.getWc()+"*"+
						dsd.getElectricCharge()+"*"+						
						dsd.getIp()+"*"+
						dsd.getPfl()+"*"+
						dsd.getFa()+"*"+						
						dsd.getGip()+"*"+
						dsd.getPt()+"*"+
						dsd.getIt()+"*"+						
						dsd.getMiscExpenses()+"*"+
						dsd.getGross()+"*"+
						dsd.getTotal()+"*"+
						dsd.getNetSalary();
			}
			
		} catch (NullPointerException e) {
			logger.error("Error in FinanceController viewStaffSalaryDetails() method for NullPointerException: ", e);
		} catch (Exception e) {
			logger.error("Error in FinanceController viewStaffSalaryDetails() method for Exception: ", e);
		}
		return sm;
	}
	
	
	@RequestMapping(value = "/saveSalaryDisbursement",method= RequestMethod.POST)
	public String saveSalaryDisbursement(HttpServletRequest request,
									 HttpServletResponse response,
									 ModelMap model,
									 DisbursementSalaryDetails disbursementSalaryDetails,
									 @ModelAttribute("sessionObject") SessionObject sessionObject){		
		try {
			logger.info("Inside Method saveSalaryDisbursement-POST of FinanceController");	
			disbursementSalaryDetails.setUpdatedBy(sessionObject.getUserId());
			String status=financeService.saveSalaryDisbursement(disbursementSalaryDetails);
			model.addAttribute("insertStatus", status);
		} catch (Exception e){
			logger.error("Exception in method saveSalaryDisbursement-POST of FinanceController", e);
			e.printStackTrace();
		}
		return openDisbursementSalaryDetails
				(request, response, model,
				disbursementSalaryDetails.getResourceType(),
				disbursementSalaryDetails.getMonth(), "Insert");
	}
	
	/**
	 * @author naimisha.ghosh
	 * changes taken on 05062017*/
	
	@RequestMapping(value = "/createBalanceSheet", method = RequestMethod.GET)
	public ModelAndView createBalanceSheet(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		try{
			modelAndView.setViewName("finance/balanceSheet");
		}catch(Exception e){
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * @author sourav.bhadra
	 * changes taken on 07062017*/
	
	@RequestMapping(value = "/getBalanceSheet", method = RequestMethod.GET)
	public @ResponseBody
	String getBalanceSheet(@RequestParam("from") String from, @RequestParam("to") String to) {
		System.out.println("13003");
		from += " 00:00:00"; /* added by sourav.bhadra on 19-08-2017 */
		to += " 23:59:59";
		//List<BalanceSheet> plList=financeService.getBalanceSheet(from,to);
		List<Group> balanceSheetList= financeService.getBalanceDetails(from,to);
		Double liabilityBalance = balanceSheetList.get(0).getTotalAmount();
		Double assetBalance = balanceSheetList.get(1).getTotalAmount();
		Double liabilityDifference = 0.0;
		Double assetDifference = 0.0;
		int i = 0;
		if(liabilityBalance > assetBalance){
			liabilityDifference = liabilityBalance - assetBalance;
		}else{
			assetDifference = assetBalance - liabilityBalance;
		}
		
		String plTable="<table class='table table-bordered table-striped mb-none' id='datatable-tabletools'>";
		plTable = plTable + "<thead><tr><th style = 'width : 500px'>Particulars</th><th>Note No</th><th>Figure as at the end of the current reporting period</th><th>Figure as at the previous of the current reporting period</th>";
		plTable = plTable +"<tbody><tr><td><ul style='list-style-type: upper-roman;'>";
		for(Group group :balanceSheetList){
			plTable = plTable + " <li>"+group.getGroupName()+"<ol>";
			for(Group childGroup : group.getParentGroupList()){
				plTable = plTable + "<li>"+childGroup.getGroupName()+"<ul style=list-style-type: lower-alpha;''>";
				for(Group subChildGroup : childGroup.getChildGroupList()){
					if(null != subChildGroup.getGroupName()){
						plTable = plTable + "<li>"+subChildGroup.getGroupName()+"</li>";
					}
					
				}
				plTable = plTable +"</ul></li>";
			}
			plTable = plTable +"</ol></li> <span class='total_show'>Total</span>";
			if(i==0 && liabilityDifference != 0.0){
				plTable = plTable +"<span class='hide_data' id = 'diffrence0'><b>Difference = "+liabilityDifference+"</b></span>";
			}else{
				plTable = plTable +"<span class='hide_data_rs'>&nbsp;</span>";
			}
			if(i==1 && assetDifference != 0.0){
				plTable = plTable +"<span class='hide_data'id = 'diffrence1'><b>Difference = "+assetDifference+"</b></span>";
			}
			i++;
		}
		plTable = plTable +"</ul>";
		plTable = plTable + "</td><td></td><td><ul style='list-style-type:none' class='data_show'>";
		for(Group group :balanceSheetList){
			plTable = plTable + " <li><br>";
			for(Group childGroup : group.getParentGroupList()){
				plTable = plTable + "<li><br>";
				for(Group subChildGroup : childGroup.getChildGroupList()){
					if(null != subChildGroup.getGroupName()){
						plTable = plTable + "<li>"+subChildGroup.getCreditAmmount()+"</li>";
					}
					
				}
				plTable = plTable +"</li>";
			}
			plTable = plTable +"</li>";
			plTable = plTable +"<span class='total_show' >"+group.getTotalAmount()+"</span><span class='hide_data_rs'>&nbsp;</span>";
			  
		}
		plTable = plTable +"</ul></td>";
		
		plTable = plTable + "<td><ul style='list-style-type:none' class='data_show'>";
		int j = 0;
		for(Group group :balanceSheetList){
			plTable = plTable + " <li><br>";
			for(Group childGroup : group.getParentGroupList()){
				plTable = plTable + "<li><br>";
				for(Group subChildGroup : childGroup.getChildGroupList()){
					if(null != subChildGroup.getGroupName()){
						plTable = plTable + "<li>"+subChildGroup.getDebitAmmount()+"</li>";
					}
					
				}
				plTable = plTable +"</li>";
			}
			plTable = plTable +"</li>";
			plTable = plTable +"<span class='total_show' >"+group.getValue()+"</span><span class='hide_data_rs'>&nbsp;</span>";
			  
		}
		plTable = plTable +"</ul></td>";
		plTable = plTable + "</tr></tbody></table>";
		return (new Gson().toJson(plTable));
	}
	
	
	
	
	@RequestMapping(value = "/transactionWorkingAreaList", method = RequestMethod.GET)
	public String transactionWorkingAreaList(HttpServletRequest request,
											HttpServletResponse response,												
											ModelMap model) {
		try{
			List<TransactionalWorkingArea> transactionalWorkingAreaList=financeService.getTransactionalWorkingAreaList();
			if(transactionalWorkingAreaList != null && transactionalWorkingAreaList.size()!=0){
				model.addAttribute("transactionalWorkingAreaList", transactionalWorkingAreaList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "finance/transactionWorkingAreaList";
	}
	
	@RequestMapping(value = "/transactionWorkingAreaDetails", method = RequestMethod.GET)
	public String transactionWorkingAreaDetails(HttpServletRequest request,
											HttpServletResponse response,												
											ModelMap model,
											@RequestParam("twaCode") String twaCode) {
		try{
			TransactionalWorkingArea transactionalWorkingArea=financeService.getTransactionWorkingAreaDetails(twaCode);
			if(transactionalWorkingArea != null){
				model.addAttribute("transactionalWorkingArea", transactionalWorkingArea);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "finance/transactionWorkingAreaDetails";
	}
	
	/**
	*modified by saif 20092017	
	*/
	@RequestMapping(value = "/transactionWorkingAreaSanction", method = RequestMethod.GET)
	public String transactionWorkingAreaSanction(HttpServletRequest request,
											HttpServletResponse response,												
											ModelMap model,
											@RequestParam("twaCode") String twaCode,
											/*@RequestParam("department") String department,
											@RequestParam("year") String academicYear,
											@RequestParam("id") String id,*/
											@ModelAttribute("sessionObject") SessionObject sessionObject) {
		List<BookRequisition> bookRequisitionList = null;
		try{
			System.out.println(" In Controller :: "+twaCode);
			String twaCode1[]=twaCode.split(",");
			String Code=twaCode1[0];
			String department=twaCode1[1];
			String id=twaCode1[2];
			String year=twaCode1[3];
			String subType=twaCode1[4];
			String mode=twaCode1[5];
			
			Resource resource = new Resource();
			resource.setAcademicYear(year);
			resource.setDepartment(department);
			resource.setObjectId(id);
			resource.setAccountType(subType);
			resource.setStatus(mode);   //Naimisha 24062017
			String status=financeService.transactionWorkingAreaSanction(Code,sessionObject.getUserId(),resource);
			
			model.addAttribute("status", status);
		}catch(Exception e){
			e.printStackTrace();
		}
		//return transactionWorkingAreaList(request, response, model);??
		return getApprovedTransactionsFromTransactionWorkingArea(request, response, model, sessionObject);/**Modified by Saif.Ali Date- 19/09/2017*/
	}
		
	@RequestMapping(value = "/templateLedgerMapping", method = RequestMethod.GET)
	public ModelAndView templateLedgerMapping(HttpServletRequest request,
												HttpServletResponse response,
												ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		
		List<TemplateLedgerMapping> templateList = financeService.getAllTemplateListForLedgerMapping();
		if (templateList != null && templateList.size() != 0) {
			model.addAttribute("templateList", templateList);
		}		
		modelAndView.setViewName("finance/templateLedgerMapping");
		return modelAndView;
	}
	
	//Naimisha 06072017
	@RequestMapping(value = "/mapTemplateLedger", method = RequestMethod.GET)
	public ModelAndView mapTemplateLedger(HttpServletRequest request,
										HttpServletResponse response,
										ModelMap model,
										@RequestParam("templateCode") String templateCode,
										@RequestParam("type") String type) {
		ModelAndView modelAndView = new ModelAndView();
		try{
			List<TemplateLedgerMapping> templateDetailsList = financeService.getTemplateDetailsListForLedgerMapping(templateCode);
			if (templateDetailsList != null && templateDetailsList.size() != 0) {
				model.addAttribute("templateDetailsList", templateDetailsList);
			}
			if(type.equalsIgnoreCase("MAP")){
				List<Ledger> ledgerList=financeService.getLedgerList();
				if(ledgerList != null && ledgerList.size()!=0)
				{
					model.addAttribute("ledgerList", ledgerList);
				}
			}
			List<ResourceType> resourceTypes= backOfficeService.getResourceTypes();
			model.addAttribute("templateCode", templateCode);
			model.addAttribute("type", type);
			model.addAttribute("resourceTypes", resourceTypes);
			modelAndView.setViewName("finance/mapTemplateLedger");
		}catch(Exception e){
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/***author naimisha**/
	
	@RequestMapping(value = "/mapLedgerTemplate", method = RequestMethod.POST)
	public ModelAndView mapLedgerTemplate(HttpServletRequest request,
											HttpServletResponse response,												
											ModelMap model,
											//@RequestParam("tempDetCode") String[] tempDetCode,
											@RequestParam("templateCode") String templateCode,
											@RequestParam(value="user") String []userDetails,
											/*@RequestParam(value="ledger") String []ledger,*/
											@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try{
			List<TemplateLedgerMapping> templateLedgerMappingList = new ArrayList<TemplateLedgerMapping>();
			for(int i=0;i<userDetails.length;i++){
				TemplateLedgerMapping tlm=new TemplateLedgerMapping();
				tlm.setUpdatedBy(sessionObject.getUserId());	
				tlm.setSalaryTemplateDetailsCode(templateCode);
				//tlm.setLedger(ledger[i]);	
				String ledger = request.getParameter(userDetails[i]+"ledger");
				tlm.setLedger(ledger);	
				tlm.setSalaryTemplateCode(userDetails[i]);
				if(null != ledger){
					templateLedgerMappingList.add(tlm);
				}
				
			}
			
			if(templateLedgerMappingList.size()>0){
				status=financeService.mapLedgerTemplate(templateLedgerMappingList,templateCode);
				model.addAttribute("submitResponse", status); //Added By Naimisha
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println(status);
		}
		return templateLedgerMapping(request, response, model);
	}
	
	/**sourav.bhadra 29062017*/
	@RequestMapping(value = "/budget", method = RequestMethod.GET)
	public String budget(HttpServletRequest request,
						HttpServletResponse response,												
						ModelMap model) {
		try{
			List<com.qts.icam.model.common.FinancialYear> financialYearList = commonService.getFinancialYearList();
			if(null!=financialYearList && financialYearList.size() != 0){
				model.addAttribute("financialYearList", financialYearList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "finance/budget";
	}
	
	
	@RequestMapping(value = "/getBudgetForAcademicYear", method = RequestMethod.GET)
	public @ResponseBody
	String getBudgetForAcademicYear(@RequestParam("academicYear") String academicYear) {
		String budget="";
		try {
			budget = financeService.getBudgetForAcademicYear(academicYear.trim());		
		
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(budget));
	}
	
	/**
	 * modified by sourav.bhadra
	 * changes taken on 17062017*/
	
	@RequestMapping(value = "/saveBudget", method = RequestMethod.POST)
	public String saveBudget(HttpServletRequest request,
									HttpServletResponse response,												
									ModelMap model,
									 @RequestParam("department") String[] department,
									@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try{
			List<Budget> budgetList=new ArrayList<Budget>();
			for(int i=0;i<department.length;i++){
				Budget b=new Budget();
				String percent = request.getParameter(department[i]+"_percentage");
				String actualAmount = request.getParameter(department[i]+"ActualAmount");
				String totalExpences = request.getParameter(department[i]+"_totalExpences");
				String balance = request.getParameter(department[i]+"balance");
				String reserve = request.getParameter(department[i]+"reserveBalance");
				if((null !=actualAmount && actualAmount != "") && (null !=balance && balance != "") &&  (null !=totalExpences && totalExpences != "")){
					b.setDepartment(department[i]);				
					b.setExpectedIncome(Double.parseDouble(percent));
					b.setActualIncome(Double.parseDouble(actualAmount));
					b.setTotalExpence(Double.parseDouble(totalExpences));
					b.setExpectedExpense(Double.parseDouble(balance));
					b.setReserveFund(Double.parseDouble(reserve));
					b.setAcademicYear(request.getParameter("academicYear"));
					b.setUpdatedBy(sessionObject.getUserId());
					budgetList.add(b);
				}
				
			}
			status = financeService.saveBudget(budgetList);
			
			String currentBudget = request.getParameter("totalIncome");
			String reserveFund = request.getParameter("remainingBalance");
			if((null !=currentBudget && currentBudget != "") && (null !=reserveFund && reserveFund != "")){
				Budget budgetDetails=new Budget();
				budgetDetails.setAcademicYear(request.getParameter("academicYear"));
				budgetDetails.setUpdatedBy(sessionObject.getUserId());
				budgetDetails.setReserveFund(Double.parseDouble(reserveFund));
				budgetDetails.setExpectedIncome(Double.parseDouble(currentBudget));
				
				financeService.saveBudgetDetails(budgetDetails);
			}
			model.addAttribute("status",status);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println(status);
		}
		return budget(request, response, model);
	}
	
	
	
	@RequestMapping(value = "/dealerDetails", method = RequestMethod.GET)
	public ModelAndView dealerDetails(HttpServletRequest request,
												HttpServletResponse response,
												ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		
		List<Vendor> vendorList = commonService.getVendorList();
		if (vendorList != null && vendorList.size() != 0) {
			model.addAttribute("vendorList", vendorList);
		}
		
		modelAndView.setViewName("finance/dealerDetails");
		return modelAndView;
	}	
	
	
	@RequestMapping(value = "/saveDelarDetails", method = RequestMethod.POST)
	public ModelAndView saveDelarDetails(HttpServletRequest request,
									HttpServletResponse response,												
									ModelMap model,
									Group group,
									DelarDetails delarDetails,
									@ModelAttribute("sessionObject") SessionObject sessionObject
									) {
		String status="";
		try{
			delarDetails.setUpdatedBy(sessionObject.getUserId());
			status=financeService.saveDelarDetails(delarDetails);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println(status);
		}
		return dealerDetails(request, response, model);
	}
	
	
	@RequestMapping(value = "/inactiveLegdger", method = RequestMethod.GET)
	public String inactiveLegdger(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String status = null;
		try {
			String ledgerCode = request.getParameter("ledgerCode");
			System.out.println("ledgerCode===="+ledgerCode);
			status = financeService.inactiveLedger(ledgerCode);
			System.out.println("status ===="+status);
			model.addAttribute("submitResponse", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ledgerCreatePage(request,response,model);
	}
	
	@RequestMapping(value = "/inactiveLegdgerGroup", method = RequestMethod.GET)
	public String inactiveLegdgerGroup(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String status = null;
		try {
			String groupCode = request.getParameter("groupCode");
			System.out.println("groupCode===="+groupCode);
			status = financeService.inactiveLedgerGroup(groupCode);
			System.out.println("status ===="+status);
			model.addAttribute("status", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return groupCreatePage(request,response,model);
	}


	/**@author anup.roy*
	 * this method is for create page of profit and loss account*
	 * */
	
	@RequestMapping(value = "/createProfitAndLoss",method= RequestMethod.GET)
	public ModelAndView profitAndLoss(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("finance/profitAndLoss");
		return modelAndView;
	}
	
	/**@author anup.roy*
	 * get profitAndLoss w.r.t from and to date*/
	/* modified by sourav.bhadra on 28-03-2018 */
	@RequestMapping(value = "/getProfitAndLoss", method = RequestMethod.GET)
	public @ResponseBody
	String getProfitAndLoss(@RequestParam("from") String from, 
							@RequestParam("to") String to) {
		to += " 23:59:59";
		List<ProfitAndLoss> plList = financeService.getProfitAndLoss(from,to);
		String plTable="<table class='table table-bordered table-striped mb-none'><tr><td>";
		String expenseTable="<table class='table table-bordered table-striped mb-none'><tr><th>Expense</th><th>Amount</th></tr>";
		String incomeTable="<table class='table table-bordered table-striped mb-none'><tr><th>Income</th><th>Amount</th></tr>";
		double expenseTotal=0.0;
		double incomeTotal=0.0;
		
		for(ProfitAndLoss p:plList){
			if(p.getIncomeExpense().equalsIgnoreCase("EXPENSE")){
				expenseTable=expenseTable+"<tr><td>"+p.getLedger()+"</td><td>"+p.getAmount()+"</td></tr>";
				expenseTotal=expenseTotal+p.getAmount();
			}
		}
		expenseTable=expenseTable+"</table>";		
		plTable=plTable+expenseTable+"</td><td>";
		
		for(ProfitAndLoss p:plList){
			if(p.getIncomeExpense().equalsIgnoreCase("INCOME")){
				incomeTable=incomeTable+"<tr><td>"+p.getLedger()+"</td><td>"+p.getAmount()+"</td></tr>";
				incomeTotal=incomeTotal+p.getAmount();
			}
		}
		incomeTable=incomeTable+"</table>";		
		plTable=plTable+incomeTable+"</td></tr><tr><td><b>Expense Total ::</b>"+expenseTotal+"</td><td><b>Income Total ::</b>"+incomeTotal+"</td></tr></table>";
		
		System.out.println(plTable);
		return (new Gson().toJson(plTable));
	}
	
	/**@author anup.roy*
	 * this page is for create page of day book**/
	
	@RequestMapping(value = "/dayBook",method= RequestMethod.GET)
	public ModelAndView dayBook(HttpServletRequest request,
									 HttpServletResponse response,
									 ModelMap model){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("finance/dayBook");
		List<Ledger> ledgerList=financeService.getLedgerList();
		if(ledgerList != null && ledgerList.size()!=0){
			model.addAttribute("ledgerList", ledgerList);
		}
		return modelAndView;
	}
	
	/**@author ranita.sur
	 * changes taken on 08082017 for edit in daybook
	 * **/
	
	
	
		@RequestMapping(value = "/getDayBook", method = RequestMethod.GET)
		public String getDayBook(HttpServletRequest request, HttpServletResponse response, ModelMap model,
				@RequestParam(required = false, value ="fromDate") String from,
				@RequestParam(required = false, value ="toDate") String to) {
			
			try {
				List<Daybook> dayBookListList = financeService.getDayBook(from,to);
				
				double debit = 0.0;
				double credit = 0.0;
		
				for(Daybook d:dayBookListList){
					if(d.getDebit()==true){
						debit += d.getAmount();
						model.addAttribute("debit", debit);
					}else{
						credit += d.getAmount();
						model.addAttribute("credit", credit);
					}
				}
				if (dayBookListList != null) {
					model.addAttribute("dayBookListList", dayBookListList);
					/* added by sourav.bhadra on 10-08-2017
					 * to display fromDate and toDate after search */
					model.addAttribute("fromDate", from);
					model.addAttribute("toDate", to);
				} 
			}catch (Exception e) {
				logger.error("addVendor() In CommonController.java: Exception",e);
			}
			return "finance/dayBook";
		}
	
		
		
	/**@author anup.roy*/
	
	@RequestMapping(value = "/securityDeposit", method = RequestMethod.GET)
	public ModelAndView securityDeposit(HttpServletRequest request,
												HttpServletResponse response,
												ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		
		List<Resource> studentList = financeService.getAllStudentList();
		if (studentList != null && studentList.size() != 0) {
			model.addAttribute("studentList", studentList);
		}
		modelAndView.setViewName("finance/securityDeposit");
		return modelAndView;
	}
	
	
	/**@author anup.roy*/
	
	@RequestMapping(value = "/updateSecurityDeposit", method = RequestMethod.POST)
	public ModelAndView updateSecurityDeposit(HttpServletRequest request,
												HttpServletResponse response,												
												ModelMap model,
												@RequestParam("userId") String[] registrationId,
												@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try{
			List<Resource> studentList=new ArrayList<Resource>();
			double payingAmount=0.0;
			 for(int i=0; i<registrationId.length;i++){
				 Resource r =new Resource();
				 r.setUpdatedBy(sessionObject.getUserId());
				 r.setRegistrationId(registrationId[i]);
				 int roll = Integer.parseInt(r.getRegistrationId());
				 r.setStudentRoll(roll);
				 double amount=Double.parseDouble(request.getParameter(registrationId[i]));
				 r.setSecurityDeposit(amount);
				 studentList.add(r);
				 
				 payingAmount=payingAmount+amount;
			 }
			 status=financeService.updateSecurityDeposit(studentList);
			 Transaction transaction = new Transaction();
				
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		        //get current date time with Date()
		        Date d = new Date();
		        transaction.setTransactionDate(dateFormat.format(d));
				transaction.setVoucherTypeCode("RECEIPT");
				transaction.setNarration("Paid Rs. "+ payingAmount + "As Security Deposit");
				
				List<TransactionDetails> transactionDetailsList=new ArrayList<TransactionDetails>();
				
				TransactionDetails transactionDetails;
				
				transactionDetails=new TransactionDetails();
				transactionDetails.setLedger("SECURITY DEPOSIT");
				transactionDetails.setAmount(payingAmount);
				transactionDetails.setDebit(true);
				transactionDetailsList.add(transactionDetails);
				transactionDetails=null;
				
				transactionDetails=new TransactionDetails();
				transactionDetails.setLedger("CASH A/C");
				transactionDetails.setAmount(payingAmount);
				transactionDetails.setDebit(false);
				transactionDetailsList.add(transactionDetails);
						
				if(transactionDetailsList.size()!=0){
					transaction.setTrDetList(transactionDetailsList);
					transaction.setIncomeExpense("INCOME");
					status=financeService.saveTransaction(transaction);
				}
			 
			 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println(status);
		}
		return securityDeposit(request, response, model);
	}	
	
	/**@author anup.roy*
	 * this method is for view ledger create page*/
	
	@RequestMapping(value = "/ledgerWiseView",method= RequestMethod.GET)
	public ModelAndView ledgerWiseView(HttpServletRequest request,
									 HttpServletResponse response,
									 ModelMap model){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("finance/ledgerWiseView");
		List<Ledger> ledgerList=financeService.getLedgerList();
		if(ledgerList != null && ledgerList.size()!=0){
			model.addAttribute("ledgerList", ledgerList);
		}
		return modelAndView;
	}
	
	/**@author anup.roy*/
	
	@RequestMapping(value = "/getLedgerWiseView", method = RequestMethod.GET)
	public @ResponseBody
	String getLedgerWiseView(@RequestParam("from") String from,
							@RequestParam("to") String to,
							@RequestParam("ledger") String ledger) {
		to += " 23:59:59";
		List<LedgerWiseView> ledgerWiseView = financeService.getLedgerWiseView(from,to,ledger);
		String dayBookTable="";
		if(ledgerWiseView.size()!=0){
			dayBookTable="<table class='table table-bordered table-striped mb-none dataTable no-footer'>";
			dayBookTable=dayBookTable+"<tr><th>Date</th><th>Particular</th><th>Voucher Type</th><th>Voucher Number</th><th>Debit</th><th>Credit</th><th>Cash/Cheque No.</th></tr>";
			
			for(LedgerWiseView d:ledgerWiseView){
				if(d.getDebit()==true){
					dayBookTable=dayBookTable+"<tr><td>"+d.getDate()+"</td><td>"+
							d.getLedgerCode()+"</td><td>"+d.getVoucherType()+
							"</td><td>"+d.getVoucherNumber()+"</td><td>"+
							d.getAmount()+"</td><td></td><td>";
					if(d.getCheque()!=null)
						dayBookTable=dayBookTable+d.getCheque()+"</td></tr>";
					else
						dayBookTable=dayBookTable+"CASH</td></tr>";
				}else{
					dayBookTable=dayBookTable+"<tr><td>"+d.getDate()+"</td><td>"+
							d.getLedgerCode()+"</td><td>"+d.getVoucherType()+
							"</td><td>"+d.getVoucherNumber()+"</td><td></td><td>"+
							d.getAmount()+"</td><td>";
					if(d.getCheque()!=null)
						dayBookTable=dayBookTable+d.getCheque()+"</td></tr>";
					else
						dayBookTable=dayBookTable+"CASH</td></tr>";
				}
			}
			dayBookTable=dayBookTable+"</table>";
		}else{
			dayBookTable="No Transactions Found";
		}
		return (new Gson().toJson(dayBookTable));
	}
	
	/**@author anup.roy*
	 * this method is for BRS page*/
	
	@RequestMapping(value = "/brs",method= RequestMethod.GET)
	public ModelAndView brs(HttpServletRequest request,
									 HttpServletResponse response,
									 ModelMap model){
		ModelAndView modelAndView = new ModelAndView();
		List<String> allBanks=financeService.getAllBankNames();
		model.addAttribute("allBanks", allBanks);
		modelAndView.setViewName("finance/brs");
		return modelAndView;
	}	
	
	/**@author anup.roy*
	 * this page is to get the brs*/
	
	@RequestMapping(value = "/getBrs",method= RequestMethod.GET)
	@ResponseBody
	public String getBrs(	@RequestParam("fromDate") String from,
							@RequestParam("toDate") String to,
							@RequestParam("bank") String bank){
		String brsTable = "";
		String brsFooterTable = "";
		String joinedTables = "";
		List<Brs> brsList = financeService.getBrs(from,to,bank);
		System.out.println("Brs List Size:::"+brsList.size());
		if(brsList.size()!=0){
			double companyBook=0.0, notInBank=0.0, notInComp=0.0;
			
			brsTable = "<table class='table table-bordered table-striped mb-none dataTable no-footer'>";
			brsTable = brsTable+"<tr><th>Cheque No.</th><th>Date</th><th>Narration</th><th>Voucher Number</th><th>Voucher Type</th><th>Debit Amount</th><th>Credit Amount</th></tr>";

			List<Brs> brsTRList=new ArrayList<Brs>();
			List<Brs> brsPBList=new ArrayList<Brs>();
			
			for(Brs b:brsList){
				if(b.getTransactionPassbook().equalsIgnoreCase("TRANSACTION")){
					brsTable = brsTable+"<tr><td>"+b.getChequeNumber()+
										"</td><td>"+b.getDate()+
										"</td><td>"+b.getNarration()+
										"</td><td>"+b.getVoucherNumber()+
										"</td><td>"+b.getVoucherType()+"</td>";
					if(b.getDebit() == true){
						brsTable = brsTable+"<td>"+b.getAmount()+"</td><td>"+"</td></tr>";
					}
					else{
						brsTable = brsTable+"<td></td><td>"+b.getAmount()+"</td></tr>";
					}
					brsTRList.add(b);
				}
				if(b.getTransactionPassbook().equalsIgnoreCase("PASSBOOK")){
					brsTable = brsTable+"<tr><td>"+b.getChequeNumber()+
										"</td><td>"+b.getDate()+
										"</td><td>"+b.getNarration()+
										"</td><td>"+b.getVoucherNumber()+
										"</td><td>"+b.getVoucherType()+"</td>";
					if(b.getDebit() == true){
						brsTable = brsTable+"<td>"+b.getAmount()+"</td><td>"+"</td></tr>";
					}
					else{
						brsTable = brsTable+"<td></td><td>"+b.getAmount()+"</td></tr>";
					}
					brsPBList.add(b);
				}
			}
			brsTable = brsTable+"</td></tr>"+"</table>";
			
			for(Brs b:brsTRList){
				int found=0;
				for(Brs b1: brsPBList){
					if(b.getChequeNumber().equalsIgnoreCase(b1.getChequeNumber())){
						companyBook=companyBook+b.getAmount();
						found=1;
					}	
				}
				
				if(found==0){
					notInBank=notInBank+b.getAmount();
				}
			}
			
			brsFooterTable = "<table class='table table-bordered table-striped mb-none dataTable no-footer'>";
			brsFooterTable = brsFooterTable+"<tr><th>"+"Balance As Per Company Book ::"+companyBook+"</th></tb>";
			brsFooterTable = brsFooterTable+"<tr><th>"+"Amount not reflected in bank ::"+notInBank+"</th></tb>";
			
			for(Brs b:brsPBList){
				int found=0;
				for(Brs b1: brsTRList){
					if(b.getChequeNumber().equalsIgnoreCase(b1.getChequeNumber())){
						found=1;
					}
				}
				if(found==0){
					notInComp=notInComp+b.getAmount();
				}
			}
			brsFooterTable = brsFooterTable+"<tr><th>"+"Amount not reflected in company book ::"+notInComp+"</th></tr>"+"</table>";
			joinedTables = brsTable +"#"+ brsFooterTable;
			System.out.println("alltables::"+joinedTables);
		}
		else{
			joinedTables = "No Details Found!";
		}
		return (new Gson().toJson(joinedTables));
	}
	
	/***changes from naimisha 02Jan 2017**/
	/****************Added By Naimisha 28122016***************/
	@RequestMapping(value = "/transactionWorkingAreaEdit", method = RequestMethod.GET)
	public @ResponseBody
	 String transactionWorkingAreaEdit(HttpServletRequest request,
											HttpServletResponse response,												
											ModelMap model,
											@RequestParam("twaCode") String twaCode) {
		System.out.println("hello");
		String ledger = null;
		try{
			String status = financeService.getTransactionWorkingAreaStatus(twaCode);
			System.out.println("Stauts=="+status);
			List<Ledger>ledgerList = financeService.getLedgerList();
			for(Ledger l : ledgerList){
				ledger = ledger+";"+l.getLedgerCode()+"*"+l.getLedgerName();
			}
			ledger = ledger +"#"+ status;
			//System.out.println("ledger=="+ledger);
		}catch(Exception e){
			e.printStackTrace();
		}        
		return (new Gson().toJson(ledger));
	}
	
	@RequestMapping(value = "/updateTransactionWorkingArea", method = RequestMethod.POST)
	public String updateTransactionWorkingArea(HttpServletRequest request,
												HttpServletResponse response,												
												ModelMap model,
												@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try{
			TransactionalWorkingArea transaction = new TransactionalWorkingArea();
			
			String rowId = request.getParameter("saveId");
			String twaCode = request.getParameter("transactionCode"+rowId);
			String ledgerCode = request.getParameter("selectledgerCode"+rowId);
			transaction.setTransactionalWorkingAreaCode(twaCode);
			transaction.setUpdatedBy(sessionObject.getUserId());
			transaction.setTransactionalWorkingAreaDesc(ledgerCode);
			String updateStatus = financeService.updateTransactionWorkingArea(transaction);
			System.out.println("updateStatus====="+updateStatus);
			model.addAttribute("updateStatus", updateStatus);
			 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println(status);
		}
		return transactionWorkingAreaList(request, response, model);
	}
	
	@RequestMapping(value = "/getSubGroup", method = RequestMethod.GET)
	public @ResponseBody
	String getSubGroup(@RequestParam("parent") String parent) {
		String subGroup="";
		System.out.println("ln1949 finance controller"+parent);
		try {
			subGroup = financeService.getSubGroupAgainstParent(parent);			
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(subGroup));
	}
	
	/* modified by sourav.bhadra on 22-02-2018*/
	@RequestMapping(value = "/fundAllocation", method = RequestMethod.GET)
	public ModelAndView fundAllocation(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		
		try {
			//System.out.println("hii");
			logger.info("Inside Method fundAllocation-GET of FinanceController");
			List<AcademicYear> academicYearList = commonService.getAllAcademicYear();
			/* modified by sourav.bhadra on 06-04-2018*/
			List<Department> departmentList=financeService.getAllParentDepartments();
			if(departmentList!=null && departmentList.size()!=0)
				model.addAttribute("departmentList", departmentList);
			if(academicYearList!=null && academicYearList.size()!=0){
				model.addAttribute("academicYearList", academicYearList);		
			}
			//System.out.println("Line 2053");
			/* added by sourav.bhadra on 22-02-2018*/
			FinancialYear financialYear = financeService.getCurrentFinancialYear();
			String financialYearName = financialYear.getFinancialYearName();
			String reserveFund = "";
			if(null != financialYearName && financialYearName != ""){
				reserveFund = financeService.getBudgetForAcademicYearAndResrveFund(financialYearName);
				model.addAttribute("financialYear", financialYearName);
			}
			if(null != reserveFund && reserveFund != ""){
				/* added by sourav.bhadra on 22-03-2018 */
				String[] resFund = reserveFund.split("#");
				model.addAttribute("budgetAmount", resFund[1]);
				/**/
				model.addAttribute("reserveFund", resFund[0]);
			}
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in Method fundAllocation-GET of FinanceController", ce);
		}
		return new ModelAndView("finance/fundAllocation");
	}
	
	
	
	/*@RequestMapping(value = "/getBudgetForAcademicYearAndResrveFund", method = RequestMethod.GET)
	public @ResponseBody
	String getBudgetForAcademicYearAndResrveFund(@RequestParam("academicYear") String academicYear) {
		String reserveFund="";
		try {
			reserveFund = financeService.getBudgetForAcademicYearAndResrveFund(academicYear);			
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(reserveFund));
	}*/
	
	
	

	@RequestMapping(value = "/updateBudgetDetails", method = RequestMethod.POST)
	public ModelAndView updateBudgetDetails(HttpServletRequest request,
									HttpServletResponse response,												
									ModelMap model,
									 @RequestParam("department") String department,
									 @RequestParam("reserveFund") double reserveFund,
									 @RequestParam("amount") double amount,
									 @RequestParam("academicYear") String academicYear,
									 @RequestParam("percentage") double percentage, /* added by sourav.bhadra on 22-03-2018 */
									@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status1="";
		String status2="";
		try{
			Budget b=new Budget();
			b.setAcademicYear(academicYear);
			b.setDepartment(department);
			b.setActualIncome(amount);
			b.setReserveFund(reserveFund);
			b.setExpectedIncome(percentage);/* added by sourav.bhadra on 22-03-2018 */
			b.setUpdatedBy(sessionObject.getUserId());/* added by sourav.bhadra on 22-03-2018 */
			/* modified by sourav.bhadra on 22-03-2018 */
			status2=financeService.updateDepartmentFund(b);
			if(status2 != "Insert Failed"){
				b.setReserveFund(reserveFund);/* added by sourav.bhadra on 22-03-2018 */
				status1=financeService.updateReserveFund(b);
			}
			model.addAttribute("submitResponse1", status1);
			model.addAttribute("submitResponse2", status2);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//System.out.println(status);
		}
		return fundAllocation(request, response, model);
	}
	
	/*/modified by sourav bhadra 25092017/*/
	
	@RequestMapping(value = "/getLedgerDetailsToEdit", method = RequestMethod.GET)
	public String getLedgerDetailsToEdit(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam(value="ledgerCode") String ledgerCode,
			/* added by sourav.bhadra on 22-08-2017 to display ledger opening and current balance */
			@RequestParam(value="ledgerOpeningBalance") String openingBalance,
			@RequestParam(value="ledgerCurrentBalance") String currentBalance,
			@RequestParam(value="ledgerGroup") String ledgerGroup) {
		String strView = "finance/editLedger";
		try {
			logger.info("Inside Method getStudentDetails-POST of BackOfficeController");
			/* added by sourav.bhadra on 22-08-2017 */
			double ledgerOpeningBalance = Double.parseDouble(openingBalance);
			double ledgerCurrentBalance = Double.parseDouble(currentBalance);
			if(null != ledgerCode){
				List<TransactionalWorkingArea> transactionWorkingAreaList = financeService.getLedgerWithDetails(ledgerCode);
				model.addAttribute("transactionWorkingAreaList", transactionWorkingAreaList);
				/* added by sourav.bhadra on 22-08-2017 to display ledger opening and current balance */
				model.addAttribute("ledgerOpeningBalance", ledgerOpeningBalance);
				model.addAttribute("ledgerCurrentBalance", ledgerCurrentBalance);
				model.addAttribute("ledgerGroup", ledgerGroup);
				model.addAttribute("ledgerCode", ledgerCode);/* added by sourav.bhadra on 22-09-2017 */
			}			
		}catch (NullPointerException e) {
				e.printStackTrace();
		}catch (CustomException ce){
			ce.printStackTrace();
			model.addAttribute("message", "Ledger Details Not Found");
			ledgerCreatePage( request, response,  model);
			logger.error("Exception in method getLedgerWithDetails-POST of BackOfficeController", ce);
		}
		return strView;
	}
	

	@RequestMapping(value = "/updateLedgerDetails", method = RequestMethod.POST)
	
	public String updatePurchaseOrderDetails(HttpServletRequest request, HttpServletResponse response,
						ModelMap model,
						@ModelAttribute("sessionObject") SessionObject sessionObject) {
	
		try {
			TransactionalWorkingArea transactionWorkingArea = new TransactionalWorkingArea();
			String saveId=request.getParameter("saveId");
			String amount=request.getParameter("netAmount"+saveId);
			transactionWorkingArea.setNetAmount(Double.parseDouble(amount));
			transactionWorkingArea.setTransactionalWorkingAreaName(request.getParameter("transactionalWorkingAreaName"+saveId));
			transactionWorkingArea.setObjectId((request.getParameter("objectId"+saveId)));
			transactionWorkingArea.setTransactionalWorkingAreaDesc(request.getParameter("transactionalWorkingAreaDesc"+saveId));
			financeService.updateLedgerDetails(transactionWorkingArea);
			
			} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in receiveRequisitionDetails() GET method Of LibraryController",e);
		}
		return ledgerCreatePage(request, response, model);
	}

	/**
	 * @author sourav.bhadra
	 * changes taken 17062017*/
	
	@RequestMapping(value = "/getPreviousYearUnallocatedFund", method = RequestMethod.GET)
	public @ResponseBody
	String getPreviousYearUnallocatedFund(@RequestParam("academicYear") String academicYear) {
		String unallocatedBalance="";
		try {
			unallocatedBalance = financeService.getPreviousYearUnallocatedFund(academicYear.trim());		
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(unallocatedBalance));
	}
	
	/* new added by sourav.bhadra on 21-06-2017 */
	@RequestMapping(value = "/setupTaxPercentages", method = RequestMethod.GET)
	public String setupTaxPercentages(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "finance/setupTaxPercentages";
		try{
			List<Tax> taxList = financeService.getTaxPercentages();
			if(null != taxList){
				model.addAttribute("taxList", taxList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return strView;
	}

	/* new added by sourav.bhadra on 21-06-2017 */
	@RequestMapping(value = "/submitTaxPercentages", method = RequestMethod.POST)
	public String submitTaxPercentages(HttpServletRequest request,
									HttpServletResponse response,												
									ModelMap model,
									Tax tax,
									@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status = "";
		try{
			tax.setUpdatedBy(sessionObject.getUserId());
			status=financeService.submitTaxPercentages(tax);
			model.addAttribute("insertResponse", status);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//System.out.println(status);
		}
		return setupTaxPercentages(request, response, model);
	}
	
	
	/**Edit Tax Details
	 By Ranita.Sur 26072017**/
	@RequestMapping(value = "/editTaxPercentages", method = RequestMethod.POST)
	public String editTaxPercentages(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editTaxPercentages-GET of FinanceController");
			Tax tax = new Tax();
			String saveId=request.getParameter("saveId");
			String newPercentage = request.getParameter("getPercentage");
			String newTaxStatus = request.getParameter("getStatus");
			tax.setTaxCode(request.getParameter("taxCode"+saveId).trim());
			tax.setPercentage(Double.parseDouble(newPercentage));
			tax.setTaxStatus(newTaxStatus);
			String updateStatus = financeService.editTaxPercentages(tax);
			model.addAttribute("updateStatus", updateStatus);
						
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method editVenue-GET of VenueController", ce);
		}
		return setupTaxPercentages(request,response,model);
	}
	
	/**Delete Tax Details
	 By Ranita.Sur 26072017**/
	@RequestMapping(value = "/inactiveTaxDetails", method = RequestMethod.GET)
	public String inactiveTaxDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String status = null;
		try {
			String taxCode = request.getParameter("taxCode");
			status = financeService.inactiveTaxDetails(taxCode);
			model.addAttribute("submitResponse", status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return setupTaxPercentages(request,response,model);
	}
	
	/**modified by sourav bhadra 05102017*/
	
	@RequestMapping(value = "/getResourceLedgerDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getResourceLedgerDetails(@RequestParam("resourceTypeName") String resourceTypeName,
			@RequestParam("tempDetCode") String tempDetCode) {
		String resourceLedgerDetailsString="";
		String ledgerDetails = "";
		String finalString = "";
		int flag =1;
		try {
			Resource resource = new Resource();
			resource.setResourceTypeName(resourceTypeName);
			resource.setResourceName(tempDetCode);
			List<Resource>resourceLedgerDetailsList = financeService.getResourceLedgerDetails(resource);	
			
			List<Ledger> ledgerList=financeService.getLedgerList();
			List<Resource> resourceList = financeService.getResourceDetailsAgainstResourceType(resourceTypeName);
			List<Resource> finalResourceList = new ArrayList<Resource>();
			List<String>mappedResourceList = new ArrayList<>();
			//int flag = 0;
			
			if(null != resourceLedgerDetailsList && resourceLedgerDetailsList.size() !=0){
				for(Resource r2:resourceLedgerDetailsList ){
					for(Resource r1 :resourceList){
						if((r1.getUserId()).equalsIgnoreCase((r2.getUserId()))){
							r1.setStatus("check");
							r1.setLedger(r2.getLedger());
							finalResourceList.add(r1); 
							mappedResourceList.add(r1.getUserId());
							break;
						}
					}
				}
				for(Resource r3 : resourceList){
					for(String r4 : mappedResourceList){
						if(r3.getUserId().equalsIgnoreCase(r4)){
							flag = 0;
						}
					}
					if(flag == 1){
						r3.setStatus("uncheck");
						r3.setLedger(r3.getLedger());
						finalResourceList.add(r3); 
						
					}
					flag = 1;
				}
			}else{
				for(Resource re:resourceList){
					re.setStatus("uncheck");
					finalResourceList.add(re);
				}
			}
			//System.out.println("resourceLedgerDetailsString without ledger===="+resourceLedgerDetailsString);
			
			for(Resource res :finalResourceList){
				resourceLedgerDetailsString = resourceLedgerDetailsString + res.getUserId()+"*"+res.getName()+"*"+res.getStatus()+"*"+res.getLedger()+"~";
			}
			//System.out.println("resourceLedgerDetailsString==="+resourceLedgerDetailsString);
			for(Ledger ledger : ledgerList){
				ledgerDetails = ledgerDetails +ledger.getLedgerCode()+"*"+ledger.getLedgerName()+"~";
			}
			finalString = finalString+resourceLedgerDetailsString+";"+ledgerDetails;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(finalString));
	}
	/**UpdateLedgerName By Ranita.Sur27072017 **/
	
	@RequestMapping(value = "/updateLedgerName", method = RequestMethod.POST)
	public String updateLedgerName(HttpServletRequest request, HttpServletResponse response,
						ModelMap model,
						@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			Ledger ledger = new Ledger();
			String saveId=request.getParameter("saveId");
			ledger.setLedgerName(request.getParameter("ledgerName"+saveId));
			ledger.setLedgerCode(request.getParameter("ledgerId"+saveId));
			/*<!-- 	modified by ranita.sur on 23/08/2017 for updating opening balance in ledger -->*/
			ledger.setOpeningBal(Double.parseDouble(request.getParameter("openingBal"+saveId)));
			ledger.setCurrentBal(Double.parseDouble(request.getParameter("currentBal"+saveId)));
			financeService.updateLedgerName(ledger);
			
			} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in receiveRequisitionDetails() GET method Of LibraryController",e);
		}
		return ledgerCreatePage(request, response, model);
	}
	/**
	 * @author ranita.sur
	 * changes taken on 29072017*/
	/* modified by sourav.bhadra on 28-03-2018 */
	@RequestMapping(value = "/getEntryInDayBook", method = RequestMethod.GET)
	public String getEntryInDayBook(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		
		String strView =("finance/entryForDayBook");
		try {
			List<Department> departmentList=commonService.selectAllDepartment();
			if(departmentList!=null && departmentList.size()!=0)
				model.addAttribute("departmentList", departmentList);
			
			List<VoucherType> voucherTypeList=financeService.getVoucherTypeList();
			if(voucherTypeList!=null && voucherTypeList.size()!=0)
				model.addAttribute("voucherTypeList", voucherTypeList);
			else
				model.addAttribute("message", "No Voucher List Available.");
			
			List<Ledger> ledgerList = financeService.getLedgerForTransaction();
			if (ledgerList != null && ledgerList.size() != 0)
				model.addAttribute("ledgerList", ledgerList);
			else
				model.addAttribute("message","No Ledger Available.");
			
			
			List<Vendor> bankList=inventoryService.getBankDetailsList();
			model.addAttribute("bankList", bankList);
			/* added by sourav.bhadra on 24-04-2018 to fetch ticket list for day book */
			List<Ticket> ticketList = financeService.getTicketListForDayBook();
			model.addAttribute("ticketList", ticketList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strView;
	}
	
	/**
	 * @author ranita.sur
	 * changes taken on 29072017*/
	/* modified by sourav.bhadra on 21-04-2018 */
	@RequestMapping(value = "/saveForDayBook", method = RequestMethod.POST)
	public String saveForDayBook(HttpServletRequest request,
		HttpServletResponse response,
		ModelMap model,
		Transaction transaction,
		@RequestParam(required=false,value="debitLedger") String[] debitLedger,
		@RequestParam(required=false,value="debitAmount") String[] debitAmount,
		@RequestParam(required=false,value="creditLedger") String[] creditLedger,
		@RequestParam(required=false,value="creditAmount") String[] creditAmount,
		@RequestParam(required=false,value="creditBank") String[] creditBank,
		@RequestParam(required=false,value="debitBank") String[] debitBank,
		/*<!-- modified by ranita.sur on 25082017 for petty cash entry -->*/
		@RequestParam(required=false,value="voucherTypeCode") String voucherType,
		@RequestParam(required=false,value="departmentName") String department,
		/* added by sourav.bhadra on 24-04-2018 */
		@RequestParam(required=false,value="ticketNo") String ticketNo,
		@ModelAttribute("sessionObject") SessionObject sessionObject){
		String status="";
		try{
			/* payment section */
			/* for bank */
		/*	<!-- modified by ranita.sur on 25082017 for petty cash entry -->*/
			if(transaction.getPaymentMode()!=null && transaction.getPaymentMode().equalsIgnoreCase("BANK")){
				
				if(voucherType.equalsIgnoreCase("PAYMENT")){
					String chequeNo, bankName, bankAccountNo, bankCode, bankLocation;
					chequeNo = request.getParameter("chequeNo");
					bankName = request.getParameter("bankName");
					bankAccountNo = request.getParameter("accountNo");
					bankCode = request.getParameter("bankCode");
					bankLocation = request.getParameter("bankLocation");
					
					transaction.setChequeNo(chequeNo);
					transaction.setBankName(bankName);
					transaction.setBankAccountNo(bankAccountNo);
					transaction.setBankCode(bankCode);
					transaction.setBankLocation(bankLocation);
				}
				if(voucherType.equalsIgnoreCase("RECEIPT")){
					String chequeNos, bankNames, bankAccountNos, bankCodes, bankLocations;
					chequeNos = request.getParameter("chequeNos");
					bankNames = request.getParameter("bankNames");
					bankAccountNos = request.getParameter("accountNos");
					bankCodes = request.getParameter("bankCodes");
					bankLocations = request.getParameter("bankLocations");
					
					transaction.setChequeNo(chequeNos);
					transaction.setBankName(bankNames);
					transaction.setBankAccountNo(bankAccountNos);
					transaction.setBankCode(bankCodes);
					transaction.setBankLocation(bankLocations);
				}
			}
		
			
			List<TransactionDetails> transactionDetailsList=new ArrayList<TransactionDetails>();
			
			if(debitLedger!=null && debitLedger.length!=0){
				for(int i=0;i<debitLedger.length;i++){
					TransactionDetails transactionDetails=new TransactionDetails();
					transactionDetails.setLedger(debitLedger[i].trim().toUpperCase());
					transactionDetails.setAmount(Double.parseDouble(debitAmount[i]));
					transactionDetails.setDebit(true);
			
					transactionDetailsList.add(transactionDetails);
				}
			}
			if(creditLedger!=null && creditLedger.length!=0){
				for(int i=0;i<creditLedger.length;i++){
					TransactionDetails transactionDetails=new TransactionDetails();
					transactionDetails.setLedger(creditLedger[i].trim().toUpperCase());
					transactionDetails.setAmount(Double.parseDouble(creditAmount[i]));
					transactionDetails.setDebit(false);
								
					transactionDetailsList.add(transactionDetails);
				}
			}
			
			if(transactionDetailsList.size()!=0){
				transaction.setTrDetList(transactionDetailsList);
				transaction.setUpdatedBy(sessionObject.getUserId());
				transaction.setTransactionCode(transaction.getTransactionCode());
				transaction.setDepartment(department);
				/* added by sourav.bhadra on 24-04-2018 */
				if(null != ticketNo && ticketNo != ""){
					transaction.setJobType(ticketNo);
				}
				status=financeService.saveForDaybook(transaction);
				model.addAttribute("status",status);
			}else{
				status="No Transactions Found To Insert.";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println(status);
		}
		return getEntryInDayBook(request,response,model);
	}

	/*Addeb by ranita.sur on 08/08/2017 for edit daybook details*/
	@RequestMapping(value = "/editDaybookDetails", method = RequestMethod.POST)
	public String editDaybookDetails(HttpServletRequest request,
							  HttpServletResponse response,
							  ModelMap model,
							  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		/* modified by sourav.bhadra on 10-08-2017
		 * to get from date and to date */
		String fromDate = request.getParameter("newFromDate");
		String toDate = request.getParameter("newToDate");
		String status="";
		try {
			Daybook dayBook = new Daybook();
			String saveId=request.getParameter("saveId");
			String transaction=request.getParameter("transactionId"+saveId);
			String date = request.getParameter("getNewDate");
				/*Modified for ledger and vouchertype in popup 22082017 */
			String ledgerName = request.getParameter("getNewLedgerName");
			String vouchertype=request.getParameter("getNewVoucherType");
			String voucherno =request.getParameter("getNewVoucherNo");
			String narration= request.getParameter("getNewNarration");
			String debitStatus=request.getParameter("getDebitStatus");
		   if(debitStatus.equalsIgnoreCase("true")){
				String debitamount = request.getParameter("getNewDebit");
				dayBook.setAmount(Double.parseDouble(debitamount));
			}else{
				String creditamount= request.getParameter("getNewCredit");
			    dayBook.setAmount(Double.parseDouble(creditamount));
			}
		    dayBook.setUpdatedBy(sessionObject.getUserId());
		    dayBook.setLedgerCode(ledgerName);
		   	/*Modified for ledger and vouchertype in popup 22082017*/ 
		    dayBook.setDebit(Boolean.parseBoolean(debitStatus));
			dayBook.setDate(date);;
			dayBook.setVoucherType(vouchertype);
			dayBook.setVoucherNumber(voucherno);
			dayBook.setNarration(narration);
			dayBook.setTransactionSerialId(Integer.parseInt(transaction));
			status=financeService.editDaybookDetails(dayBook);
			model.addAttribute("insertUpdateStatus", status);
		} catch (Exception e) {
			logger.error("Exception In editBankDetails() method of BackOfficeController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(status);
		}
		/* modified by sourav.bhadra on 10-08-2017 */
		return getDayBook(request,response,model, fromDate, toDate);
	}
	/*Addeb by ranita.sur on 22/08/2017 for edit daybook details*/
	@RequestMapping(value = "/getAllLedgerForDayBookEdit", method = RequestMethod.GET)
	public @ResponseBody
	String getAllLedgerForDayBookEdit() {
		String ledgerName="";
		try {
			ledgerName = financeService.getAllLedgerForDayBookEdit();
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(ledgerName));
	}
	/*Addeb by ranita.sur on 22/08/2017 for edit daybook details*/
	@RequestMapping(value = "/getAllVoucherForDayBookEdit", method = RequestMethod.GET)
	public @ResponseBody
	String getAllVoucherForDayBookEdit() {
		String voucherType="";
		try {
			voucherType = financeService.getAllVoucherForDayBookEdit();
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(voucherType));
	}

	/**Added by Saif.Ali
	 * Date-19/09/2017
	 * Used to show the list of approved items from the TRANSACTION WORKING AREA*/
	@RequestMapping(value = "/approvedList", method = RequestMethod.GET)
	public String getApprovedTransactionsFromTransactionWorkingArea(HttpServletRequest request, HttpServletResponse response,
						ModelMap model,
						@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			List<TransactionalWorkingArea> transactionalWorkingAreaListOfApprovedTransactions=financeService.getTransactionalWorkingAreaListOfApprovedTransactions();
			if(transactionalWorkingAreaListOfApprovedTransactions != null && transactionalWorkingAreaListOfApprovedTransactions.size()!=0)
			{
				model.addAttribute("transactionalWorkingAreaListOfApprovedTransactions", transactionalWorkingAreaListOfApprovedTransactions);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getApprovedTransactionsFromTransactionWorkingArea() GET method Of FinanceController",e);
		}
		return "finance/listOfApprovedTransactions";
	}
	
	/**Added by Saif.Ali
	 * Date-19/09/2017
	 * Used to show the list of Payment Done item list from the TRANSACTION WORKING AREA*/
	@RequestMapping(value = "/paymentDoneList", method = RequestMethod.GET)
	public String getPaymentDoneListFromTransactionWorkingArea(HttpServletRequest request, HttpServletResponse response,
						ModelMap model,
						@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			List<TransactionalWorkingArea> transactionalWorkingAreaListOfDonePayments=financeService.getTransactionalWorkingAreaListOfDonePayments();
			if(transactionalWorkingAreaListOfDonePayments != null && transactionalWorkingAreaListOfDonePayments.size()!=0)
			{
				model.addAttribute("transactionalWorkingAreaListOfDonePayments", transactionalWorkingAreaListOfDonePayments);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in getPaymentDoneListFromTransactionWorkingArea() GET method Of FinanceController",e);
		}
		return "finance/listOfPaymentDoneStatus";
	}

	
	/* added by sourav.bhadra on 22-09-2017
	 * to get ledger details within date range */
	@RequestMapping(value = "/getLedgerDetailsWithinDateRange", method = RequestMethod.GET)
	public String getLedgerDetailsWithinDateRange(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam(required = false, value ="fromDate") String from,
			@RequestParam(required = false, value ="toDate") String to) {
		
		try {
			String group, ledger;
			group = request.getParameter("ledgerGroup");
			ledger = request.getParameter("ledgerCode");
			String status = "Search Result";
			
			List<TransactionalWorkingArea> transactionWorkingAreaList = financeService.getLedgerDetailsWithinDateRange(from, to, ledger);
			Double[] balances = financeService.getOpeningAndClosingBalanceForAParticularLedger(from, to, ledger);
			Double openingBalance = balances[0];
			Double closingBalance = balances[1];
			
			if(null != transactionWorkingAreaList && transactionWorkingAreaList.size() != 0){
				model.addAttribute("transactionWorkingAreaList", transactionWorkingAreaList);
			}
			model.addAttribute("ledgerOpeningBalance", openingBalance);
			model.addAttribute("ledgerCurrentBalance", closingBalance);
			model.addAttribute("fromDate", from);
			model.addAttribute("toDate", to);
			model.addAttribute("status", status);
			model.addAttribute("ledgerGroup", group);
			model.addAttribute("ledgerCode", ledger);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("addVendor() In CommonController.java: Exception",e);
		}
		return "finance/editLedger";
	}
	
	//Added by naimisha 17042018
	
	@RequestMapping(value = "/salaryBreakupLedgerMapping", method = RequestMethod.GET)
	public String salaryBreakupLedgerMapping(HttpServletRequest request,
												HttpServletResponse response,
												ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		String breakType = "DEDUCTION";
		List<SalaryBreakUp> salaryBreakUpList = financeService.getDeductionTypeSalaryBreakUpList(breakType);
		List<Ledger>ledgerList = financeService.getLedgerList();
		
		
		model.addAttribute("salaryBreakUpList", salaryBreakUpList);
		model.addAttribute("ledgerList", ledgerList);
		
		List<SalaryBreakUp>salaryBreakUpLedgerMappingList = financeService.getSalaryBreakUpLedgerMappingList();
		model.addAttribute("salaryBreakUpLedgerMappingList", salaryBreakUpLedgerMappingList);
		
		return "finance/salaryBreakupLedgerMapping";
	}
	
	@RequestMapping(value = "/insertSalaryBreakUpLedgerMapping", method = RequestMethod.POST)
	public String insertSalaryBreakUpLedgerMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, SalaryBreakUp salaryBreakUp,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status = "";
		try {
			salaryBreakUp.setUpdatedBy(sessionObject.getUserId());
			status = financeService.insertSalaryBreakUpLedgerMapping(salaryBreakUp);
			model.addAttribute("submitResponse", status);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(status);
		}
		return salaryBreakupLedgerMapping(request, response, model);
	}
	
	/**
	 * @author Sourav.Bhadra
	 * created on 20-04-2018
	 * for voucher type creation screen
	 */
	@RequestMapping(value = "/createVoucherType", method = RequestMethod.GET)
	public String createVoucherType(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			List<VoucherType> voucherTypeList=financeService.getVoucherTypeList();
			if(voucherTypeList!=null && voucherTypeList.size()!=0)
				model.addAttribute("voucherTypeList", voucherTypeList);
			else
				model.addAttribute("message", "No Voucher List Available.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "finance/createVoucherType";
	}
	
	/**
	 * @author Sourav.Bhadra
	 * created on 20-04-2018
	 * for submit voucher type
	 */
	@RequestMapping(value = "/submitVoucherType", method = RequestMethod.POST)
	public String submitVoucherType(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, SalaryBreakUp salaryBreakUp,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status = "";
		try {
			String voucher, dept, incExp, ticketNo, multiDebit;
			voucher = request.getParameter("voucherTypeName");
			dept = request.getParameter("department");
			incExp = request.getParameter("incExp");
			ticketNo = request.getParameter("ticketNo");
			multiDebit = request.getParameter("multiDebit");
			
			VoucherType voucherType = new VoucherType();
			voucherType.setUpdatedBy(sessionObject.getUserId());
			voucherType.setVoucherTypeName(voucher);
			if(null != dept){
				if(dept.equals("on")){
					voucherType.setDepartment(true);
				}else{
					voucherType.setDepartment(false);
				}
			}else{
				voucherType.setDepartment(false);
			}
			if(null != incExp){
				if(incExp.equals("on")){
					voucherType.setIncExp(true);
				}else{
					voucherType.setIncExp(false);
				}
			}else{
				voucherType.setIncExp(false);
			}
			if(null != ticketNo){
				if(ticketNo.equals("on")){
					voucherType.setTicketNo(true);
				}else{
					voucherType.setTicketNo(false);
				}
			}else{
				voucherType.setTicketNo(false);
			}
			if(null != multiDebit){
				if(multiDebit.equals("on")){
					voucherType.setMultipleDebitLedger(true);
				}else{
					voucherType.setMultipleDebitLedger(false);
				}
			}else{
				voucherType.setMultipleDebitLedger(false);
			}
			status = financeService.createVoucherType(voucherType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createVoucherType(request, response, model);
	}
	
	/**
	 * @author Sourav.Bhadra
	 * created on 20-04-2018
	 * to fetch a voucher type's details
	 */
	@RequestMapping(value = "/getVoucherTypeDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getVoucherTypeDetails(@RequestParam("voucherTypeCode") String voucherTypeCode) {
		String voucherType="";
		try {
			voucherType = financeService.getVoucherTypeDetails(voucherTypeCode);
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(voucherType));
	}
	
	/**
	 * @author Sourav.Bhadra
	 * created on 23-04-2018
	 * to fetch ledger list for journal voucher type
	 */
	@RequestMapping(value = "/getLedgerListForJournalVoucher", method = RequestMethod.GET)
	public @ResponseBody
	String getLedgerListForJournalVoucher(@RequestParam("voucherTypeCode") String voucherTypeCode) {
		String ledgers="";
		try {
			System.out.println("LN2983 :: Voucher Type :: "+voucherTypeCode);
			ledgers = financeService.getLedgerListForJournalVoucher();
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("LN2987 :: Ledgers :: "+ledgers);
		return (new Gson().toJson(ledgers));
	}
}