package com.qts.icam.service.impl.finance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.qts.icam.dao.finance.FinanceDao;
import com.qts.icam.model.common.ProfitAndLoss;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.finance.BalanceSheet;
import com.qts.icam.model.finance.Brs;
import com.qts.icam.model.finance.CashBook;
import com.qts.icam.model.academics.ExamMarks;
import com.qts.icam.model.common.Budget;
import com.qts.icam.model.common.Daybook;
import com.qts.icam.model.common.DelarDetails;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.LedgerWiseView;
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
import com.qts.icam.model.finance.TransactionalWorkingArea;
import com.qts.icam.model.finance.TrialBalance;
import com.qts.icam.model.finance.VendorPayment;
import com.qts.icam.model.finance.VoucherType;
import com.qts.icam.model.inventory.IndividualCommodity;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.service.finance.FinanceService;
import com.qts.icam.utility.customexception.CustomException;

@Service
public class FinanceServiceImpl implements FinanceService {
	@Autowired
	FinanceDao financeDao;
	

	

	@Override
	public List<Group> getGroupTypeList() {
		
		return financeDao.getGroupTypeList();
	}


	@Override
	public List<Group> getGroupList() {
		return financeDao.getGroupList();
	}
	
	@Override
	public List<Group> getChildGroupList(String groupCode) {
		return financeDao.getChildGroupList(groupCode);
	}
	
	@Override
	public List<Group> getSubChildList(String child) {
		return financeDao.getChildGroupList(child);
	}


	@Override
	public List<Group> getParentGroup() {
		return financeDao.getParentGroup();
	}


	@Override
	public String createGroup(Group group) {
		return financeDao.createGroup(group);
	}


	@Override
	public List<Ledger> getLedgerList() {
		return financeDao.getLedgerList();
	}


	@Override
	public String createLedger(Ledger ledger) {
		return financeDao.createLedger(ledger);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String saveTransaction(Transaction transaction) {
		return financeDao.saveTransaction(transaction);
	}
	
	@Override
	public String checkForBankGroup(String ledger) {
		return financeDao.checkForBankGroup(ledger);
	}


	

	@Override
	public List<TrialBalance> getTrialBalance(String from, String to) {
		return financeDao.getTrialBalance(from, to);
	}


	

	@Override
	public List<IncomeAndExpense> getIncomeAndExpense(String from, String to) {
		return financeDao.getIncomeAndExpense(from, to);
	}


	/* modified by ranita.sur on 21082017*/
	@Override
	public List<TransactionalWorkingArea> getCashBook(String from, String to) {
		return financeDao.getCashBook(from, to);
	}


	@Override
	public List<String> getAllBankNames() {
		return financeDao.getAllBankNames();
	}


	@Override
	public String savePassbook(List<Passbook> passbookList) {
		return financeDao.savePassbook(passbookList);
	}


	@Override
	public List<Brs> getBrs(String from, String to, String bank) {
		return financeDao.getBrs(from, to, bank);
	}
	
	@Override
	public String getNewStudents(String standard){
		String studentString = "";
 		try{
 			Student student=new Student();
 			student.setStandard(standard);
 			List<Student> studentList = financeDao.getNewStudents(student);
 			if(studentList!=null && studentList.size()!=0){
				StringBuilder sb = new StringBuilder();
				for(Student studentObject : studentList){
					if (sb.length() != 0) {
						sb.append(";");
				    }
					sb.append(studentObject.getRollNumber());
					sb.append("*");
					sb.append(studentObject.getStudentName());
				}
				studentString = (new Gson().toJson(sb.toString()));
			}else{
				System.out.println("Something went wrong");
			}
 		}catch(Exception e){
 			e.printStackTrace();
 			System.out.println("Something went wrong");
 		}
		return studentString;
	}
	
	@Override
	public String getStudentFeesPaymentDetails(StudentFeesPayment studentFeesPayment) {
		return financeDao.getStudentFeesPaymentDetails(studentFeesPayment);
	}


	@Override
	public String saveStudentFees(StudentFeesPayment studentFeesPayment, Transaction at) {
		return financeDao.saveStudentFees(studentFeesPayment, at);
	}


	@Override
	public String saveFeesLedgerMapping(List<FeesLedgerMapping> feesLedgerMappingList) {
		return financeDao.saveFeesLedgerMapping(feesLedgerMappingList);
	}


	@Override
	public List<VendorPayment> getAllPurchaseOrdersForPayment() {
		return financeDao.getAllPurchaseOrdersForPayment();
	}


	@Override
	public String makeVendorPayment(Transaction at, VendorPayment vp) {
		return financeDao.makeVendorPayment(at, vp);
	}

	@Override
	public List<Employee> getAllStaffCodeList() {
		return financeDao.getAllStaffCodeList();
	}

	

	@Override
	public List<SalaryBreakUp> getAllSalaryBreakUpList() {
		return financeDao.getAllSalaryBreakUpList();
	}

	@Override
	public StaffSalaryDetails getStaffSalaryDetails(String strEmployeeCode) {
		return financeDao.getStaffSalaryDetails(strEmployeeCode);
	}


	@Override
	public String addStaffSalaryDetails(StaffSalaryDetails staffSalaryDetails) {
		return financeDao.addStaffSalaryDetails(staffSalaryDetails);
	}


	@Override
	public String editStaffSalaryDetails(StaffSalaryDetails staffSalaryDetails) {
		return financeDao.editStaffSalaryDetails(staffSalaryDetails);
	}


	@Override
	public String saveDesignationSalaryDetails(DesignationSalaryDetails designationSalaryDetails) {
		return financeDao.saveDesignationSalaryDetails(designationSalaryDetails);
	}


	@Override
	public DesignationSalaryDetails getDesignationSalaryDetails(String designation, String parameter) {
		return financeDao.getDesignationSalaryDetails(designation, parameter);
	}


	@Override
	public List<SalaryDisbursementList> getSalaryDisbursementList(String month) {
		return financeDao.getSalaryDisbursementList(month);
	}


	@Override
	public List<Employee> getStaffCodeListToDisburseSalary(SalaryDisbursementList sdl) {
		return financeDao.getStaffCodeListToDisburseSalary(sdl);
	}


	@Override
	public String saveSalaryDisbursement(DisbursementSalaryDetails disbursementSalaryDetails) {
		return financeDao.saveSalaryDisbursement(disbursementSalaryDetails);
	}


	@Override
	public List<Employee> getSalaryDisbursedStaffList(SalaryDisbursementList sdl) {
		return financeDao.getSalaryDisbursedStaffList(sdl);
	}


	@Override
	public DisbursementSalaryDetails viewStaffDisbursedSalaryDetails(DisbursementSalaryDetails dsd) {
		return financeDao.viewStaffDisbursedSalaryDetails(dsd);
	}
	
	@Override
	public List<BalanceSheet> getBalanceSheet(String from, String to) {
		return financeDao.getBalanceSheet(from, to);
	}
	
	


	@Override
	public List<VoucherType> getVoucherTypeList() {
		return financeDao.getVoucherTypeList();
	}


	@Override
	public List<TransactionalWorkingArea> getTransactionalWorkingAreaList() {
		return financeDao.getTransactionalWorkingAreaList();
	}


	@Override
	public TransactionalWorkingArea getTransactionWorkingAreaDetails(String twaCode) {
		return financeDao.getTransactionWorkingAreaDetails(twaCode);
	}

	/**cHANGED bY nAIMISHA 30052017***/
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String transactionWorkingAreaSanction(String twaCode, String userId,Resource resource) {
		return financeDao.transactionWorkingAreaSanction(twaCode,userId,resource);
	}

	@Override
	public List<Resource> getAllStudentList() {
		return financeDao.getAllStudentList();
	}


	@Override
	public String updateSecurityDeposit(List<Resource> studentList) {
		return financeDao.updateSecurityDeposit(studentList);
	}


	@Override
	public List<TemplateLedgerMapping> getAllTemplateListForLedgerMapping() {
		return financeDao.getAllTemplateListForLedgerMapping();
	}


	@Override
	public List<TemplateLedgerMapping> getTemplateDetailsListForLedgerMapping(String templateCode) {
		return financeDao.getTemplateDetailsListForLedgerMapping(templateCode);
	}


	@Override
	public String mapLedgerTemplate(List<TemplateLedgerMapping> templateLedgerMappingList, String templateCode) {
		return financeDao.mapLedgerTemplate(templateLedgerMappingList, templateCode);
	}
	
	
	/**new methods by naimisha**/
	
	@Override
	public String getBudgetForAcademicYear(String academicYear) {
		return financeDao.getBudgetForAcademicYear(academicYear);
	}


	@Override
	public String saveBudget(List<Budget> budgetList) {
		return financeDao.saveBudget(budgetList);
	}
	
	
	@Override
	public String saveDelarDetails(DelarDetails delarDetails) {
		return financeDao.saveDelarDetails(delarDetails);
	}
	
	
	@Override
	public String inactiveLedger(String ledgerCode) {
		return financeDao.inactiveLedger(ledgerCode);
	}
	
	
	@Override
	public String inactiveLedgerGroup(String groupCode) {
		return financeDao.inactiveLedgerGroup(groupCode);
	}

	/**@author anup.roy**/
	
	@Override
	public List<ProfitAndLoss> getProfitAndLoss(String from, String to) {
		return financeDao.getProfitAndLoss(from, to);
	}
	
	@Override
	public List<Daybook> getDayBook(String from, String to) {
		return financeDao.getDayBook(from, to);
	}


	@Override
	public List<LedgerWiseView> getLedgerWiseView(String from, String to, String ledger) {
		return financeDao.getLedgerWiseView(from, to, ledger);
	}

	/**Changes from naimisha 02 Jan 2017****/
	
	@Override
	public String getTransactionWorkingAreaStatus(String twaCode) {
		return financeDao.getTransactionWorkingAreaStatus(twaCode);
	}

	@Override
	public String updateTransactionWorkingArea(TransactionalWorkingArea transaction) {
		return financeDao.updateTransactionWorkingArea(transaction);
	}
	
	@Override
	public List<Group> getBalanceDetails(String from, String to) {
		return financeDao.getBalanceDetails(from, to);
	}
	
	@Override
	public List<Group> getAllBalance() {
		return financeDao.getAllBalance();
	}
	
	@Override
	public List<Group> getSubGroupName(String name) {
		return financeDao.getSubGroupName(name);
	}
	
	
	@Override
	public List<Group> getPrevBalance() {
		return financeDao.getPrevBalance();
	}
	
	@Override
	public List<Group> getChild(String parent) {
		return financeDao.getChild(parent);
	}
	
	
	@Override
	public String getSubGroupAgainstParent(String parent){
		String groupList = "";
		//List<ExamMarks> examMarksList = academicsDAO.getSubjectsAndMarksForStandard(standard, exam);
		List<Group> subGroupList=financeDao.getSubGroupAgainstParentList(parent);
		System.out.println(subGroupList);
		if(null != subGroupList && subGroupList.size() != 0){
			for(Group list : subGroupList){
				groupList = groupList+list.getSubGroupName()+"*"+list.getSubGroupCode()+"~";
				System.out.println("Ln395 service impl"+ groupList);
			}
		}
		return groupList;
	}
	
	@Override
	public String getBudgetForAcademicYearAndResrveFund(String academicYear) {
		return financeDao.getBudgetForAcademicYearAndResrveFund(academicYear);
	}
	
	@Override
	public String updateReserveFund(Budget b) {
		return financeDao.updateReserveFund(b);
	}
	
	@Override
	public String updateDepartmentFund(Budget b) {
		return financeDao.updateDepartmentFund(b);
	}


	@Override
	public String getParentName(String code) {
		
		return financeDao.getParentName(code);
	}

	
	/*/Added By Naimisha/*/

	@Override
	public List<Ledger> getLedgerListFromTransactionWorkingArea() {
		return financeDao.getLedgerListFromTransactionWorkingArea();
	}


	@Override
	public List<Ledger> getLedgerForTransaction() {
		return financeDao.getLedgerForTransaction();
	}

	/******Added By Ranita 02062017******/
	
	@Override
	public List<TransactionalWorkingArea> getLedgerWithDetails(String ledgerCode) throws CustomException {
		return financeDao.getLedgerWithDetails(ledgerCode);
	}

	@Override
	public String updateLedgerDetails(TransactionalWorkingArea transactionWorkingArea) {
		return financeDao.updateLedgerDetails(transactionWorkingArea);
	}
	
	@Override
	public FinancialYear getCurrentFinancialYear() {
		return financeDao.getCurrentFinancialYear();
	}

	@Override
	public List<IndividualCommodity> getIndividualCommodityDetailsForCurrentYear() {
		return financeDao.getIndividualCommodityDetailsForCurrentYear();
	}

	@Override
	public String insertDepreciationInTransactionWorkingArea(TransactionalWorkingArea transactionalWorkingArea) {
		return financeDao.insertDepreciationInTransactionWorkingArea(transactionalWorkingArea);
	}
	
	@Override
	public String getPreviousYearUnallocatedFund(String academicYear) {
		return financeDao.getPreviousYearUnallocatedFund(academicYear);
	}

	@Override
	public String saveBudgetDetails(Budget budgetDetails) {
		return financeDao.saveBudgetDetails(budgetDetails);
	}
	
	@Override
	public String getTaxPercentageAgainstTaxCode(String taxCode) {
		return financeDao.getTaxPercentageAgainstTaxCode(taxCode);
	}
	
	@Override
	public String submitTaxPercentages(Tax tax) {
		return financeDao.submitTaxPercentages(tax);
	}

	@Override
	public List<Tax> getTaxPercentages() {
		return financeDao.getTaxPercentages();
	}


	@Override
	public List<Resource> getResourceLedgerDetails(Resource resource) {
		return financeDao.getResourceLedgerDetails(resource);
	}


	@Override
	public List<Resource> getResourceDetailsAgainstResourceType(String resourceTypeName) {
		return financeDao.getResourceDetailsAgainstResourceType(resourceTypeName);
	}
	
	@Override
	public String editTaxPercentages(Tax tax) {
		return financeDao.editTaxPercentages(tax);
	}
	
	@Override
	public String inactiveTaxDetails(String taxCode) {
		return financeDao.inactiveTaxDetails(taxCode);
	}
	/**UpdateLedgerName By Ranita.Sur27072017 **/
	@Override
	public String updateLedgerName(Ledger ledger) {
		return financeDao.updateLedgerName(ledger);
	}
	/**
	 * @author ranita.sur
	 * changes taken on 29072017*/
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String saveForDaybook(Transaction transaction) {
		return financeDao.saveForDaybook(transaction);
	}
	
	/**Addeb by ranita.sur on 08/08/2017 for edit daybook details**/
	@Override
	public String editDaybookDetails(Daybook dayBook) {
		return financeDao.editDaybookDetails(dayBook);
	}
	/*added by rannita.sur on 18/08/2017 for current balance and opening balance in cashbook*/
	@Override
	public Double[] getCurrentBalanceAndOpeningBalance(String from, String to) {
		return financeDao.getCurrentBalanceAndOpeningBalance(from, to);
	}
	/*Addeb by ranita.sur on 08/08/2017 for edit daybook details*/
	@Override
	public String getAllLedgerForDayBookEdit() {
		return financeDao.getAllLedgerForDayBookEdit();
	}
	/*Addeb by ranita.sur on 08/08/2017 for edit daybook details*/
	@Override
	public String getAllVoucherForDayBookEdit() {
		return financeDao.getAllVoucherForDayBookEdit();
	}
	
	/**@author Saif.Ali
	 * Date- 19/09/2017*/
	public List<TransactionalWorkingArea> getTransactionalWorkingAreaListOfApprovedTransactions()
	{
		return financeDao.getTransactionalWorkingAreaListOfApprovedTransactions();
	}
	
	/**@author Saif.Ali
	 * Date- 19/09/2017*/
	@Override
	public List<TransactionalWorkingArea> getTransactionalWorkingAreaListOfDonePayments()
	{
		return financeDao.getTransactionalWorkingAreaListOfDonePayments();
	}
	
	/* added by sourav.bhadra on 22-09-2017 */
	@Override
	public List<TransactionalWorkingArea> getLedgerDetailsWithinDateRange(String from, String to, String ledger) {
		return financeDao.getLedgerDetailsWithinDateRange(from, to, ledger);
	}

	/* added by sourav.bhadra on 22-09-2017 */
	@Override
	public Double[] getOpeningAndClosingBalanceForAParticularLedger(String from, String to, String ledger) {
		return financeDao.getOpeningAndClosingBalanceForAParticularLedger(from, to, ledger);
	}

	/* added by sourav.bhadra on 06-04-2018 */
	@Override
	public List<Department> getAllParentDepartments() {
		return financeDao.getAllParentDepartments();
	}

	//Added By Naimisha 17/04/2018
	
	@Override
	public List<SalaryBreakUp> getDeductionTypeSalaryBreakUpList(String breakUpType) {
		return financeDao.getDeductionTypeSalaryBreakUpList(breakUpType);
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String insertSalaryBreakUpLedgerMapping(SalaryBreakUp salaryBreakUp) {
		return financeDao.insertSalaryBreakUpLedgerMapping(salaryBreakUp);
	}


	@Override
	public List<SalaryBreakUp> getSalaryBreakUpLedgerMappingList() {
		return financeDao.getSalaryBreakUpLedgerMappingList();
	}

	/* added by sourav.bhadra on 20-04-2018 */
	@Override
	public String createVoucherType(VoucherType voucherType) {
		return financeDao.createVoucherType(voucherType);
	}

	/* added by sourav.bhadra on 20-04-2018 */
	@Override
	public String getVoucherTypeDetails(String voucherTypeCode) {
		return financeDao.getVoucherTypeDetails(voucherTypeCode);
	}

	/* added by sourav.bhadra on 23-04-2018 */
	@Override
	public String getLedgerListForJournalVoucher() {
		return financeDao.getLedgerListForJournalVoucher();
	}

	/* added by sourav.bhadra on 24-04-2018 */
	@Override
	public List<Ticket> getTicketListForDayBook() {
		return financeDao.getTicketListForDayBook();
	}
}