package com.qts.icam.dao.inventory;

import java.util.List;
import java.util.Map;

import com.qts.icam.model.common.Task;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.model.inventory.CommodityPurchaseOrder;
import com.qts.icam.model.inventory.IndividualCommodity;
import com.qts.icam.model.ticket.Ticket;

public interface InventoryDao {

	String checkCommodityName(String commodityName);

	String saveCommodity(Commodity commodity, String updatedBy);

	List<Commodity> listCommodity();

	String editCommodity(Commodity commodity);

	List<Vendor> commodityVendorList();

	String vendorCommodityList(String vendorCode, String financialYear);

	String mapCommodityVendor(List<Commodity> commodityList, String updatedBy);

	String vendorCommodityPriceHistory(Commodity commodity);

	String getNextCommodityPurchaseOrderCode();

	String remeaningCommodities(String vendorCode);

	String createCommodityPurchaseOrder(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy);

	List<CommodityPurchaseOrder> commodityPurchaseOrderList(String status);

	CommodityPurchaseOrder commodityPurchaseOrderPaymentDetails(String orderID);

	String makeCommodityPayment(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy);

	CommodityPurchaseOrder commodityPurchaseOrderReceiveDetails(String orderID);

	String makeCommodityReceive(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy);

	String getIndividualNotAllotedCommodity(String commodityCode);

	String allotCommodity(List<IndividualCommodity> individualCommodityList, String updatedBy);

	String getIndividualAllotedCommodity(String commodityCode);

	String deAllotCommodity(List<IndividualCommodity> individualCommodityList, String updatedBy);

	List<Commodity> getAssetCommodity();

	String getIndividualCommodityList(String commodityCode);

	String getCommodityAllotmentHistory(String individualCommodity);

	String getIndividualCommodity(String commodityCode);

	String updateIndividualCommodityDetails(List<IndividualCommodity> individualCommodityList, String updatedBy);

	String retireCommodity(List<IndividualCommodity> individualCommodityList, String updatedBy);

	String closeCommodityOrder(String orderID, String updatedBy);

	List<Vendor> getBankDetailsList();

	public String getVendorBankAllDetails(String vendorName);

	String getNextmessCommodityPurchaseOrderCode();

	String createMessCommodityPurchaseOrder(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy);

	List<CommodityPurchaseOrder> messCommodityPurchaseOrderList(String status);

	CommodityPurchaseOrder messCommodityPurchaseOrderReceiveDetails(String orderID);

	String makeMessCommodityReceive(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy);

	String makeMessCommodityPayment(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy);

	/* added by sourav.bhadra on 27062017
	 * to get department budget details */
	public String getDepartmentBudgetDetails(String departmentCode);
	
	/* added by sourav.bhadra on 28-07-2017
	 * to get commodity unit list */
	public List<Commodity> getCommodityUnitList();
	
	/* added by sourav.bhadra on 28-07-2017
	 * to get commodity unit for PO */
	public String getCommodityUnitForPO(String commodity);
	
	/* added by sourav.bhadra on 31-07-2017
	 * to get vendor's ledger for PO */
	public String getVendorsLedgerForCommodityPO(String vendorCode);
	
	//Added by naimisha ghosh 24042018

	public String getNextCommodityRequisionCode();

	public String createCommodityRequisition(CommodityPurchaseOrder commodityPurchaseOrder, String userId);

	public List<CommodityPurchaseOrder> commodityRequisitionList();

	public CommodityPurchaseOrder getCommodityRequisitionDetails(String requisitionCode);
	
	//Added by naimisha 03052018

	public List<Commodity> commodityListMappedForAFinancialYear(String financialYear);

	public List<Commodity> getVendorListMappedForAFinancialYearAndCommodity(Commodity commodity);
	
	//Added by naimisha 07052018

	public String submitTenderWisePricing(Vendor vendor);

	public String vendorCommodityListAccordingToTender(String vendorCode, String financialYear);

	public List<Commodity> getVendorsListFromItemTenderPricing(Commodity commodityObj);

	public List<CommodityPurchaseOrder> commodityRequisitionListNotPresentInPO();

	
	//Added by naimisha 09052018
	public List<Task> getTaskNoListForAUser(String userId);

	public Ticket getTicketNoAgainstTaskCode(String taskCode);
	
	//PRAD MAY 25 2018
	public List<Commodity> getAllCommodityName();
	
	public String getCommodityUnit(String commodityName);
}
