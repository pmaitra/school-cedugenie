package com.qts.icam.service.impl.mess;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qts.icam.controller.mess.MessController;
import com.qts.icam.dao.mess.MessDao;
import com.qts.icam.model.common.CommodityIssueVoucher;
import com.qts.icam.model.common.CommodityReceiveVoucher;
import com.qts.icam.model.common.MessDailyConsumption;
import com.qts.icam.model.common.MessDailyConsumptionDetails;
import com.qts.icam.model.common.MessDailyRationPurchaseOrder;
import com.qts.icam.model.common.MessDemandVoucher;
import com.qts.icam.model.common.MessMenuRoutine;
import com.qts.icam.model.common.MessMenuTime;
import com.qts.icam.model.common.Notification;
import com.qts.icam.model.common.PerishableMaterial;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.service.mess.MessService;

@Service
public class MessServiceImpl implements MessService {
	
	public static Logger logger = Logger.getLogger(MessServiceImpl.class);
	@Autowired
	MessDao messDao = null;
	
	/**
	 * @author anup.roy
	 * this method is for getting commodities for demand voucher*/
	@Override
	public List<Commodity> getCommodityListForMess() {
		return messDao.getCommodityListForMess();
	}
	/**
	 * @author anup.roy
	 * this method is for getting list voucher
	 * */
	@Override
	public List<MessDemandVoucher> getMessDemandVoucherList() {
		return messDao.getMessDemandVoucherList();
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting unit, rate, stock w.r.t commodity
	 * */
	
	@Override
	public Commodity getCommodityDetailsForIndentSheet(String commodity) {
		return messDao.getCommodityDetailsForIndentSheet(commodity);
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting last indent sheet id
	 * modification on 16.10.17*/
	
	@Override
	public int getLastDemandVoucherId() {
		int lastVoucherId = messDao.getLastDemandVoucherId();
		int nextVoucherId = 0;
		if (lastVoucherId != 0) {
			nextVoucherId = lastVoucherId+1;
			//voucherId = "INDS" + nextVoucherId;
		} else {
			nextVoucherId = 1;
		}
		return nextVoucherId;
	}
	
	/**
	 * @author anup.roy
	 * this method is for submitting demand voucher*/

	@Override
	public String submitMessDemandVoucher(MessDemandVoucher demandVoucher) {
		return messDao.submitMessDemandVoucher(demandVoucher);
	}
	
	/**
	 * @author anup.roy
	 * this method is for send notification to a specific role*/
	@Override
	public void sendNotification(Notification notification) {
		messDao.sendNotification(notification);
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting details of the indent sheet for creating CIV*/
	@Override
	public MessDemandVoucher getDetailsOfDemandVoucher(String indentSheetId) {
		return messDao.getDetailsOfDemandVoucher(indentSheetId);
	}
	
	/**
	 * @author anup.roy
	 * for getting the last commodity issue voucher code*/
	@Override
	public String getLastCommodityIssueVoucherId() {
		int lastCivId = messDao.getLastCommodityIssueVoucherId();
		String voucherId = null;
		if (lastCivId != 0) {
			int nextVoucherId = lastCivId+1;
			voucherId = "CIV" + nextVoucherId;
		} else {
			voucherId = "CIV1";
		}
		return voucherId;
	}
	
	/**
	 * @author anup.roy
	 * this method is for submit issue voucher*/
	
	@Override
	public String submitCommodityIssueVoucher(CommodityIssueVoucher issueVoucher) {
		return messDao.submitCommodityIssueVoucher(issueVoucher);
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting details of issued materials*/
	@Override
	public CommodityIssueVoucher getDetailsOfIssueVoucher(String civId) {
		return messDao.getDetailsOfIssueVoucher(civId);
	}
	
	/**
	 * @author anup.roy
	 * this method is for submitting receive voucher*/
	
	@Override
	public String submitCommodityReceiveVoucher(CommodityReceiveVoucher receiveVoucher) {
		return messDao.submitCommodityReceiveVoucher(receiveVoucher);
	}
	
	/**
	 * @author anup.roy // for get stock of mess*/
	@Override
	public List<Commodity> getStockOfMess() {
		return messDao.getStockOfMess();
	}
	
	/**
	 * @author anup.roy
	 * for getting current session*/
	
	@Override
	public String getCurrentSession() {
		return messDao.getCurrentSession();
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting vendors */
	@Override
	public List<Vendor> getAllVendors() {
		return messDao.getAllVendors();
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting all commodities*/
	@Override
	public List<Commodity> getMessDailyRationCommodities() {
		return messDao.getMessDailyRationCommodities();
	}
	
	/**
	 * @author anup.roy
	 * this method is for submitting daily ration PO*/
	/** modified by @author Sourav.Bhadra on 24-10-2017 **/
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String submitMessDailyRationPO(MessDailyRationPurchaseOrder messDailyPO) {
		return messDao.submitMessDailyRationPO(messDailyPO);
	}
	
	/**
	 * @author anup.roy
	 * this method is for get last daily ration PO Code*/
	@Override
	public String getLastMessDailyRationPOCode() {
		String messCurrentSession = messDao.getCurrentSession();
		int lastDailyRationPOCode = messDao.getLastMessDailyRationPOCode();
		String lastPOCode = null;
		if (lastDailyRationPOCode != 0) {
			int nextId = lastDailyRationPOCode+1;
			lastPOCode = "PO-" + messCurrentSession +"-"+ nextId;
		} else {
			lastPOCode = "PO-"+messCurrentSession+"-1";
		}
		return lastPOCode;
	}
	
	/** @author Sourav.Bhadra 
	 * for selecting mess daily ration vendor's details **/
	@Override
	public String getDailyRationVendorDetails(String vendorCode) {
		return messDao.getDailyRationVendorDetails(vendorCode);
	}
	
	/**
	 * @author anup.roy
	 * this method is for get last daily consumption CIV Code*/
	@Override
	public String getLastMessDailyConsumptionCIVCode() {
		String messCurrentSession = messDao.getCurrentSession();
		int lastDailyConsumptionCIVCode = messDao.getLastMessDailyConsumptionCIVCode();
		String code = null;
		if (lastDailyConsumptionCIVCode != 0) {
			int nextId = lastDailyConsumptionCIVCode+1;
			code = "SSR/" + messCurrentSession +"/K/CIV/"+ nextId;
		} else {
			code = "SSR/" + messCurrentSession +"/K/CIV/1";
		}
		return code;
	}
	
	/** @author Sourav.Bhadra // to get commodities for daily mess consumption on 10-10-2017 **/
	@Override
	public String getCommoditiesForDailyMessConsumption(String commodityType) {
		return messDao.getCommoditiesForDailyMessConsumption(commodityType);
	}
	
	/** @author Sourav.Bhadra // to get commodities mess stock for daily mess consumption on 10-10-2017 **/
	@Override
	public MessDailyConsumptionDetails getCommoditiesMessStockForDailyMessConsumption(String commodityType, String commodity,
			String issueDate) {
		return messDao.getCommoditiesMessStockForDailyMessConsumption(commodityType, commodity, issueDate);
	}
	
	/**
	 * @author anup.roy
	 * this method is for mess daily consumption*/
	@Override
	public String submitMessDailyConsumption(MessDailyConsumption consumption) {
		return messDao.submitMessDailyConsumption(consumption);
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting mess menu time list*/
	
	@Override
	public List<MessMenuTime> getMessMenuTimeList() {
		return messDao.getMessMenuTimeList();
	}

	/** @author Sourav.Bhadra // to get commodities' units for perishable material requisition request on 16-10-2017 **/
	@Override
	public String getCommodityUnitForPerishableMaterialRequisition(String commodityName) {
		return messDao.getCommodityUnitForPerishableMaterialRequisition(commodityName);
	}
	
	/** @author Sourav.Bhadra // to get next perishable material requisition code on 18-10-2017 **/
	@Override
	public String getNextPerishableMaterialRequisitionCode() {
		return messDao.getNextPerishableMaterialRequisitionCode();
	}
	
	/** @author Sourav.Bhadra // to get next perishable material requisition code on 18-10-2017 **/
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String submitPerishableMaterialRequisition(PerishableMaterial perishablematerial) {
		return messDao.submitPerishableMaterialRequisition(perishablematerial);
	}
	
	/**@author Sourav.Bhadra // for select perishable material requisition list on 18-10-2017 */
	@Override
	public List<PerishableMaterial> getPerishableMaterialRequisitionList() {
		return messDao.getPerishableMaterialRequisitionList();
	}
	
	/**@author Sourav.Bhadra // for select perishable material requisition individual details on 18-10-2017 */
	@Override
	public List<PerishableMaterial> viewIndividualPerishableMaterialRequisitionDetails(String orderId) {
		return messDao.viewIndividualPerishableMaterialRequisitionDetails(orderId);
	}
	
	/** @author Sourav.Bhadra // to get next perishable material requisition order number on 19-10-2017 **/
	@Override
	public String getNextPerishableMaterialRequisitionOrderNumber() {
		return messDao.getNextPerishableMaterialRequisitionOrderNumber();
	}
	
	/**@author Sourav.Bhadra // for select daily ration commodities price details for a vendor on 26-10-2017 */
	@Override
	public String getDailyRationCommoditiesPriceDetailsForAVendor(String vendorCode) {
		return messDao.getDailyRationCommoditiesPriceDetailsForAVendor(vendorCode);
	}

	/**
	 * @author anup.roy
	 * this method is for adding mess menu details*/
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public int addMessMenuDetails(MessMenuRoutine messMenuRoutine) {
		return messDao.addMessMenuDetails(messMenuRoutine);
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting list of mess menu*/
	@Override
	public List<MessMenuRoutine> getMessMenuList() {
		return messDao.getMessMenuList();
	}
	
	/**
	 * @author anup.roy
	 * this method is for viewing details of a menu for edit
	 */
	@Override
	public MessMenuRoutine getMessMenuDetails(MessMenuRoutine messMenuRoutine) {
		return messDao.getMessMenuDetails(messMenuRoutine);
	}
	
	/**
	 * @author anup.roy
	 * this method is for submit the edited mess menu*/
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public int editMessMenuDetails(MessMenuRoutine messMenuRoutine) {
		return messDao.editMessMenuDetails(messMenuRoutine);
	}

	/**@author Sourav.Bhadra // to map daily ration commodities with a vendor on 31-10-2017 */
	@Override
	public String mapCommodityVendorForPerishableMaterial(List<Commodity> commodityList, String updatedBy) {
		return messDao.mapCommodityVendorForPerishableMaterial(commodityList, updatedBy);
	}
	
	/**@author Sourav.Bhadra // to get daily ration vendor commodity mapping on 31-10-2017 */
	@Override
	public String vendorCommodityListForPerishableMaterial(String vendorCode) {
		return messDao.vendorCommodityListForPerishableMaterial(vendorCode);
	}
	
	/**@author Sourav.Bhadra // to get daily ration commodity price history on 31-10-2017 */
	@Override
	public String vendorCommodityPriceHistoryForPerishableMaterial(Commodity commodity) {
		return messDao.vendorCommodityPriceHistoryForPerishableMaterial(commodity);
	}
	
	/**@author Sourav.Bhadra // to get daily ration vendors list on 14-11-2017 */
	@Override
	public List<Vendor> getMessDailyRationVendorList() {
		return messDao.getMessDailyRationVendorList();
	}
	
	/**@author Sourav.Bhadra // to submit daily ration vendor on 14-11-2017 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String submitMessDailyRationVendor(Vendor vendor) {
		return messDao.submitMessDailyRationVendor(vendor);
	}
	
	/**@author Sourav.Bhadra // to update a daily ration vendor's details on 17-11-2017 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String editMessDailyRationVendorDetails(Vendor vendor) {
		return messDao.editMessDailyRationVendorDetails(vendor);
	}
	
}
