package com.qts.icam.dao.finance;

import java.util.List;

import com.qts.icam.model.common.ProfitAndLoss;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.erp.Employee;
import com.qts.icam.model.finance.BalanceSheet;
import com.qts.icam.model.finance.Brs;
import com.qts.icam.model.finance.CashBook;
import com.qts.icam.model.common.Budget;
import com.qts.icam.model.common.Daybook;
import com.qts.icam.model.common.DelarDetails;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.finance.FinancialYear;
import com.qts.icam.model.common.LedgerWiseView;
import com.qts.icam.model.finance.DesignationSalaryDetails;
import com.qts.icam.model.finance.DisbursementSalaryDetails;
import com.qts.icam.model.finance.FeesLedgerMapping;
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
import com.qts.icam.utility.customexception.CustomException;


public interface FinanceDao {	
	
	public String checkForBankGroup(String ledger);
	
	public List<Group> getGroupTypeList();
	
	public List<Group> getGroupList();
	
	public List<Group> getChildGroupList(String groupCode);
	
	public List<Group> getSubChildList(String child);
	
	public List<Group> getParentGroup();
	
	public String createGroup(Group group);
	
	public List<Ledger> getLedgerList();
	
	public String createLedger(Ledger ledger);
	
	public String saveTransaction(Transaction transaction);
	
	public List<TrialBalance> getTrialBalance(String from, String to); /* modified by sourav.bhadra on 09-08-2017 */
	
	public List<IncomeAndExpense> getIncomeAndExpense(String from, String to);
	
	public List<TransactionalWorkingArea> getCashBook(String from, String to);
	
	public List<String> getAllBankNames();
	
	public String savePassbook(List<Passbook> passbookList);
	
	public List<Brs> getBrs(String from, String to, String bank);
	
	public String getStudentFeesPaymentDetails(StudentFeesPayment studentFeesPayment);
	
	public String saveStudentFees(StudentFeesPayment studentFeesPayment, Transaction at);
	
	public String saveFeesLedgerMapping(List<FeesLedgerMapping> feesLedgerMappingList);
	
	public List<VendorPayment> getAllPurchaseOrdersForPayment();
	
	public String makeVendorPayment(Transaction at, VendorPayment vp);
	
	public List<Student> getNewStudents(Student student);
	
	public List<Employee> getAllStaffCodeList();
	
	public List<SalaryBreakUp> getAllSalaryBreakUpList();
	
	public StaffSalaryDetails getStaffSalaryDetails(String strEmployeeCode);
	
	public String addStaffSalaryDetails(StaffSalaryDetails staffSalaryDetails);
	
	public String editStaffSalaryDetails(StaffSalaryDetails staffSalaryDetails);
	
	public String saveDesignationSalaryDetails(DesignationSalaryDetails designationSalaryDetails);
	
	public DesignationSalaryDetails getDesignationSalaryDetails(String designation, String parameter);
	
	public List<SalaryDisbursementList> getSalaryDisbursementList(String month);
	
	public List<Employee> getStaffCodeListToDisburseSalary(SalaryDisbursementList sdl);
	
	public String saveSalaryDisbursement(DisbursementSalaryDetails disbursementSalaryDetails);
	
	public List<Employee> getSalaryDisbursedStaffList(SalaryDisbursementList sdl);
	
	public DisbursementSalaryDetails viewStaffDisbursedSalaryDetails(DisbursementSalaryDetails dsd);
	
	public List<BalanceSheet> getBalanceSheet(String from, String to);

	public List<VoucherType> getVoucherTypeList();
	
	public List<TransactionalWorkingArea> getTransactionalWorkingAreaList();
	
	public TransactionalWorkingArea getTransactionWorkingAreaDetails(String twaCode);
	
	public String transactionWorkingAreaSanction(String twaCode, String userId,Resource r);
	
	public List<Resource> getAllStudentList();
	
	public String updateSecurityDeposit(List<Resource> studentList);
	
	public List<TemplateLedgerMapping> getAllTemplateListForLedgerMapping();
	
	public List<TemplateLedgerMapping> getTemplateDetailsListForLedgerMapping(String templateCode);
	
	public String mapLedgerTemplate(List<TemplateLedgerMapping> templateLedgerMappingList,String templateCode);
	
	/**new added by naimisha**/
	
	/***************Added by naimisha15122016************/
	public String getBudgetForAcademicYear(String academicYear);
	
	public String saveBudget(List<Budget> budgetList);
	
	public String saveDelarDetails(DelarDetails delarDetails);
	
	public String inactiveLedger(String ledgerCode);
	
	public String inactiveLedgerGroup(String groupCode);
	
	/**@author anup.roy*
	 * */
	
	public List<ProfitAndLoss> getProfitAndLoss(String from, String to);
	
	public List<Daybook> getDayBook(String from, String to);
	
	public List<LedgerWiseView> getLedgerWiseView(String from, String to, String ledger);

	/**changes from naimisha 02 jan 2017**/
	
	/**********Added By Naimisha29122016*************/
	public String getTransactionWorkingAreaStatus(String twaCode);
	
	public String updateTransactionWorkingArea(TransactionalWorkingArea transaction);
	
	public List<Group> getBalanceDetails(String from, String to);
	
	public List<Group> getAllBalance();
	
	public List<Group> getPrevBalance();
	
	public List<Group> getSubGroupName(String name);
	
	public List<Group>getSubGroupAgainstParentList(String parent);

	public String getBudgetForAcademicYearAndResrveFund(String academicYear);
	
	public String updateReserveFund(Budget b);
	
	public String updateDepartmentFund(Budget b);
	
	public String getParentName(String code);
	
	public List<Group> getChild(String parent);
	
	/*/Added By Naimisha /*/
	public List<Ledger> getLedgerListFromTransactionWorkingArea();
	
	public List<Ledger> getLedgerForTransaction();
	
	/*******Added By Naimisha 02062017******/
	List<TransactionalWorkingArea> getLedgerWithDetails(String ledgerCode) throws CustomException;
	
	public String updateLedgerDetails(TransactionalWorkingArea transactionWorkingArea);
	
	public FinancialYear getCurrentFinancialYear();
	
	public List<IndividualCommodity> getIndividualCommodityDetailsForCurrentYear();
	
	public String insertDepreciationInTransactionWorkingArea(TransactionalWorkingArea transactionalWorkingArea);
	
	public String getPreviousYearUnallocatedFund(String academicYear);

	public String saveBudgetDetails(Budget budgetDetails);

	public String getTaxPercentageAgainstTaxCode(String taxCode);
	
	public String submitTaxPercentages(Tax tax);

	public List<Tax> getTaxPercentages();
	
	public List<Resource> getResourceLedgerDetails(Resource resource);
	
	public List<Resource> getResourceDetailsAgainstResourceType(String resourceTypeName);
	
	/**Edit Tax Details
	 By Ranita.Sur 26072017**/
	public String editTaxPercentages(Tax tax);
	/**Delete Tax Details
	 By Ranita.Sur 26072017**/
	public String inactiveTaxDetails(String taxCode);
	/**UpdateLedgerName By Ranita.Sur27072017 **/
	public String updateLedgerName(Ledger ledger);
	/**
	 * @author ranita.sur
	 * changes taken on 29072017*/
	
	public String saveForDaybook(Transaction transaction);
	
	/**Addeb by ranita.sur on 08/08/2017 for edit daybook details**/
	public String editDaybookDetails(Daybook dayBook);
	
	/*added by rannita.sur on 18/08/2017 for current balance and opening balance in cashbook*/
	public Double[] getCurrentBalanceAndOpeningBalance(String from, String to);
	
	/*Addeb by ranita.sur on 22/08/2017 for edit daybook details*/
	public String getAllLedgerForDayBookEdit();
	
	public String getAllVoucherForDayBookEdit();
	
	/**@author Saif.Ali
	 * Date- 19/09/2017*/
	public List<TransactionalWorkingArea> getTransactionalWorkingAreaListOfApprovedTransactions();
	
	/**@author Saif.Ali
	 * Date- 19/09/2017*/
	public List<TransactionalWorkingArea> getTransactionalWorkingAreaListOfDonePayments();
	
	/* added by sourav.bhadra on 22-09-2017 */
	public List<TransactionalWorkingArea> getLedgerDetailsWithinDateRange(String from, String to, String ledger);

	/* added by sourav.bhadra on 22-09-2017 */
	public Double[] getOpeningAndClosingBalanceForAParticularLedger(String from, String to, String ledger);
	
	/* added by sourav.bhadra on 06-04-2018 */
	public List<Department> getAllParentDepartments();

	
	//Added By Naimisha 17/04/2018
	
	public List<SalaryBreakUp> getDeductionTypeSalaryBreakUpList(String breakUpType);

	public String insertSalaryBreakUpLedgerMapping(SalaryBreakUp salaryBreakUp);

	public List<SalaryBreakUp> getSalaryBreakUpLedgerMappingList();
	
	/* added by sourav.bhadra on 20-04-2018 */
	public String createVoucherType(VoucherType voucherType);
	
	/* added by sourav.bhadra on 20-04-2018 */
	public String getVoucherTypeDetails(String voucherTypeCode);
	
	/* added by sourav.bhadra on 23-04-2018 */
	public String getLedgerListForJournalVoucher();
	
	/*  added by sourav.bhadra on 24-04-2018  */
	public List<Ticket> getTicketListForDayBook();
}
