package com.qts.icam.service.impl.inventory;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;

import com.qts.icam.dao.inventory.InventoryDao;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.model.inventory.CommodityPurchaseOrder;
import com.qts.icam.model.inventory.IndividualCommodity;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.service.inventory.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	InventoryDao inventoryDao;
	
	
	List<Commodity> commodityList=null;
	List<CommodityPurchaseOrder> commodityPurchaseOrderList=null;
	
	@Override
	public String checkCommodityName(String commodityName) {
		return inventoryDao.checkCommodityName(commodityName);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String saveCommodity(Commodity commodity, String updatedBy) {
		return inventoryDao.saveCommodity(commodity, updatedBy);
	}

	@Override
	public List<Commodity> listCommodity() {
		commodityList=inventoryDao.listCommodity();
		return commodityList;
	}
	
	@Override
	public String editCommodity(Commodity commodity) {
		return inventoryDao.editCommodity(commodity);
	}

	@Override
	public List<Vendor> commodityVendorList() {
		return inventoryDao.commodityVendorList();
	}

	@Override
	public String vendorCommodityList(String vendorCode, String financialYear) {
		return inventoryDao.vendorCommodityList(vendorCode, financialYear);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String mapCommodityVendor(List<Commodity> commodityList, String updatedBy) {
		return inventoryDao.mapCommodityVendor(commodityList, updatedBy);
	}

	@Override
	public String vendorCommodityPriceHistory(Commodity commodity) {
		return inventoryDao.vendorCommodityPriceHistory(commodity);
	}

	@Override
	public String getNextCommodityPurchaseOrderCode() {
		return inventoryDao.getNextCommodityPurchaseOrderCode();
	}

	@Override
	public String remeaningCommodities(String vendorCode) {
		return inventoryDao.remeaningCommodities(vendorCode);
	}

	/**
	 * modified by ranita.sur
	 * changes taken on 12042017
	 * revert back on 12042017
	 * */
	//Added By Naimisha 12032018//
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String createCommodityPurchaseOrder(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy) {
		return inventoryDao.createCommodityPurchaseOrder(commodityPurchaseOrder, updatedBy);
	}

	@Override
	public List<CommodityPurchaseOrder> commodityPurchaseOrderList(String status) {
		commodityPurchaseOrderList=inventoryDao.commodityPurchaseOrderList(status);
		return commodityPurchaseOrderList;
	}
	
	@Override
	public CommodityPurchaseOrder commodityPurchaseOrderPaymentDetails(String orderID) {
		return inventoryDao.commodityPurchaseOrderPaymentDetails(orderID);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String makeCommodityPayment(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy) {
		return inventoryDao.makeCommodityPayment(commodityPurchaseOrder, updatedBy);
	}

	@Override
	public CommodityPurchaseOrder commodityPurchaseOrderReceiveDetails(String orderID) {
		return inventoryDao.commodityPurchaseOrderReceiveDetails(orderID);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String makeCommodityReceive(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy) {
		return inventoryDao.makeCommodityReceive(commodityPurchaseOrder, updatedBy);
	}

	@Override
	public String getIndividualNotAllotedCommodity(String commodityCode) {
		return inventoryDao.getIndividualNotAllotedCommodity(commodityCode);
	}

	@Override
	public String allotCommodity(List<IndividualCommodity> individualCommodityList, String updatedBy) {
		return inventoryDao.allotCommodity(individualCommodityList, updatedBy);
	}

	@Override
	public String getIndividualAllotedCommodity(String commodityCode) {
		return inventoryDao.getIndividualAllotedCommodity(commodityCode);
	}

	@Override
	public String deAllotCommodity(List<IndividualCommodity> individualCommodityList, String updatedBy) {
		return inventoryDao.deAllotCommodity(individualCommodityList, updatedBy);
	}

	@Override
	public List<Commodity> getAssetCommodity() {
		return inventoryDao.getAssetCommodity();
	}

	@Override
	public String getIndividualCommodityList(String commodityCode) {
		return inventoryDao.getIndividualCommodityList(commodityCode);
	}

	@Override
	public String getCommodityAllotmentHistory(String individualCommodity) {
		return inventoryDao.getCommodityAllotmentHistory(individualCommodity);
	}

	@Override
	public String getIndividualCommodity(String commodityCode) {
		return inventoryDao.getIndividualCommodity(commodityCode);
	}

	@Override
	public String updateIndividualCommodityDetails(List<IndividualCommodity> individualCommodityList, String updatedBy) {
		return inventoryDao.updateIndividualCommodityDetails(individualCommodityList, updatedBy);
	}

	@Override
	public String retireCommodity(List<IndividualCommodity> individualCommodityList, String updatedBy) {
		return inventoryDao.retireCommodity(individualCommodityList, updatedBy);
	}

	@Override
	public String closeCommodityOrder(String orderID, String updatedBy) {
		return inventoryDao.closeCommodityOrder(orderID, updatedBy);
	}

	@Override
	public List<Vendor> getBankDetailsList() {
		return inventoryDao.getBankDetailsList();
	}

	@Override
	public String getVendorBankAllDetails(String vendorName) {
		return inventoryDao.getVendorBankAllDetails(vendorName);
	}
	
	/**@author Saif.Ali
	 * date- 10/07/2017*/
	@Override
	public String getNextmessCommodityPurchaseOrderCode() {
		return inventoryDao.getNextmessCommodityPurchaseOrderCode();
	}
	
	@Override
	public String createMessCommodityPurchaseOrder(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy) {
		return inventoryDao.createMessCommodityPurchaseOrder(commodityPurchaseOrder, updatedBy);
	}
	
	@Override
	public List<CommodityPurchaseOrder> messCommodityPurchaseOrderList(String status)
	{
		commodityPurchaseOrderList=inventoryDao.messCommodityPurchaseOrderList(status);
		return commodityPurchaseOrderList;
	}
	
	@Override
	public CommodityPurchaseOrder messCommodityPurchaseOrderReceiveDetails(String orderID) {
		return inventoryDao.messCommodityPurchaseOrderReceiveDetails(orderID);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String makeMessCommodityReceive(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy) {
		return inventoryDao.makeMessCommodityReceive(commodityPurchaseOrder, updatedBy);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String makeMessCommodityPayment(CommodityPurchaseOrder commodityPurchaseOrder, String updatedBy) {
		return inventoryDao.makeMessCommodityPayment(commodityPurchaseOrder, updatedBy);
	}
	
	/* added by sourav.bhadra on 27-07-2017
	 * to get department budget details */
	@Override
	public String getDepartmentBudgetDetails(String departmentCode) {
		return inventoryDao.getDepartmentBudgetDetails(departmentCode);
	}
	
	/* added by sourav.bhadra on 28-07-2017
	 * to get commodity unit list */
	@Override
	public List<Commodity> getCommodityUnitList() {
		return inventoryDao.getCommodityUnitList();
	}
	
	/* added by sourav.bhadra on 28-07-2017
	 * to get commodity unit for PO */
	@Override
	public String getCommodityUnitForPO(String commodity) {
		return inventoryDao.getCommodityUnitForPO(commodity);
	}
	
	/* added by sourav.bhadra on 31-07-2017
	 * to get vendor's ledger for PO */
	@Override
	public String getVendorsLedgerForCommodityPO(String vendorCode) {
		return inventoryDao.getVendorsLedgerForCommodityPO(vendorCode);
	}
	
	//Added By Naimisha ghosh 25/042018

	@Override
	public String getNextCommodityRequisionCode() {
		return inventoryDao.getNextCommodityRequisionCode();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String createCommodityRequisition(CommodityPurchaseOrder commodityPurchaseOrder, String userId) {
		return inventoryDao.createCommodityRequisition(commodityPurchaseOrder,userId);
	}

	@Override
	public List<CommodityPurchaseOrder> commodityRequisitionList() {
		return inventoryDao.commodityRequisitionList();
	}

	@Override
	public CommodityPurchaseOrder getCommodityRequisitionDetails(String requisitionCode) {
		return inventoryDao.getCommodityRequisitionDetails(requisitionCode);
	}
	
	//Addedd by naimisha 03052018

	@Override
	public List<Commodity> commodityListMappedForAFinancialYear(String financialYear) {
		return inventoryDao.commodityListMappedForAFinancialYear(financialYear);
	}

	@Override
	public List<Commodity> getVendorListMappedForAFinancialYearAndCommodity(Commodity commodity) {
		return inventoryDao.getVendorListMappedForAFinancialYearAndCommodity(commodity);
	}

	//Added by Naimisha 07052018
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String submitTenderWisePricing(Vendor vendor) {
		return inventoryDao.submitTenderWisePricing(vendor);
	}

	@Override
	public String vendorCommodityListAccordingToTender(String vendorCode, String financialYear) {
		return inventoryDao.vendorCommodityListAccordingToTender(vendorCode,financialYear);
	}

	@Override
	public List<Commodity> getVendorsListFromItemTenderPricing(Commodity commodityObj) {
		return inventoryDao.getVendorsListFromItemTenderPricing(commodityObj);
	}

	@Override
	public List<CommodityPurchaseOrder> commodityRequisitionListNotPresentInPO() {
		return inventoryDao.commodityRequisitionListNotPresentInPO();
	}
	
	//Added by Naimkisha 09052018

	@Override
	public List<Task> getTaskNoListForAUser(String userId) {
		return inventoryDao.getTaskNoListForAUser(userId);
	}

	@Override
	public Ticket getTicketNoAgainstTaskCode(String taskCode) {
		return inventoryDao.getTicketNoAgainstTaskCode(taskCode);
	}
	
	@Override
	public List<Commodity> getAllCommodityName(){
		return inventoryDao.getAllCommodityName();
	}
	
	@Override
	public String getCommodityUnit(String commodityName){
		return inventoryDao.getCommodityUnit(commodityName);
	}
}
