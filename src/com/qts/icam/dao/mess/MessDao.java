package com.qts.icam.dao.mess;

import java.util.List;

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

public interface MessDao {
	
	/**@author anup.roy // for getting commodity list in demand voucher*/
	public List<Commodity> getCommodityListForMess();
	
	/**@author anup.roy//for getting mess demand voucher list*/
	public List<MessDemandVoucher> getMessDemandVoucherList();
	
	/**anup.roy//for getting the details w.r.t commodity*/
	public Commodity getCommodityDetailsForIndentSheet(String commodity);
	
	/**anup.roy//for getting the last indent sheet id*/
	public int getLastDemandVoucherId();
	
	/**@author anup.roy//for submitting mess demand voucher*/
	public String submitMessDemandVoucher(MessDemandVoucher demandVoucher);
	
	/**@author anup.roy//for sending notification to specific role*/
	public void sendNotification(Notification notification);

	/**
	 * @author anup.roy//for getting details of indent sheet for creating CIV*/
	public MessDemandVoucher getDetailsOfDemandVoucher(String indentSheetId);

	/**
	 * @author anup.roy// for getting last commodity voucher id*/
	public int getLastCommodityIssueVoucherId();

	/**
	 * @author anup.roy// for submitting commodityIssueVoucher*/
	public String submitCommodityIssueVoucher(CommodityIssueVoucher issueVoucher);

	/**
	 * @author anup.roy //for getting details of issue voucher*/
	public CommodityIssueVoucher getDetailsOfIssueVoucher(String civId);

	/**@author anup.roy // for submit receive voucher*/
	public String submitCommodityReceiveVoucher(CommodityReceiveVoucher receiveVoucher);

	/**
	 * @author anup.roy // for get the stock of mess*/
	public List<Commodity> getStockOfMess();
	
	/**
	 * @author anup.roy // for get current session*/
	public String getCurrentSession();

	/**@author anup.roy // for getting vendor details*/
	public List<Vendor> getAllVendors();

	/**@author anup.roy // for getting daily ration commodities*/
	public List<Commodity> getMessDailyRationCommodities();

	/**@author anup.roy // for submitting mess daily ration PO*/
	public String submitMessDailyRationPO(MessDailyRationPurchaseOrder messDailyPO);

	/**@author anup.roy // for getting last PO code*/
	public int getLastMessDailyRationPOCode();
	
	/** @author Sourav.Bhadra // for selecting mess daily ration vendor's details **/
	public String getDailyRationVendorDetails(String vendorCode);
	
	/**@author anup.roy // for getting last Consumption CIV code*/
	public int getLastMessDailyConsumptionCIVCode();
	
	/** @author Sourav.Bhadra // to get commodities for daily mess consumption on 10-10-2017 **/
	public String getCommoditiesForDailyMessConsumption(String commodityType);
	
	/** @author Sourav.Bhadra // to get commodities mess stock for daily mess consumption on 10-10-2017 **/
	public MessDailyConsumptionDetails getCommoditiesMessStockForDailyMessConsumption(String commodityType, String commodity, String issueDate);

	/**@author anup.roy // for submitting mess daily consumption*/
	public String submitMessDailyConsumption(MessDailyConsumption consumption);

	/**@author anup.roy // for getting mess menu time list*/
	public List<MessMenuTime> getMessMenuTimeList();

	/** @author Sourav.Bhadra // to get commodities' units for perishable material requisition request on 16-10-2017 **/
	public String getCommodityUnitForPerishableMaterialRequisition(String commodityName);
	
	/** @author Sourav.Bhadra // to get next perishable material requisition code on 18-10-2017 **/
	public String getNextPerishableMaterialRequisitionCode();
	
	/**@author Sourav.Bhadra // for submitting perishable material requisition on 18-10-2017 */
	public String submitPerishableMaterialRequisition(PerishableMaterial perishablematerial);
	
	/**@author Sourav.Bhadra // for select perishable material requisition list on 18-10-2017 */
	public List<PerishableMaterial> getPerishableMaterialRequisitionList();
	
	/**@author Sourav.Bhadra // for select perishable material requisition individual details on 18-10-2017 */
	public List<PerishableMaterial> viewIndividualPerishableMaterialRequisitionDetails(String orderId);
	
	/** @author Sourav.Bhadra // to get next perishable material requisition order number on 19-10-2017 **/
	public String getNextPerishableMaterialRequisitionOrderNumber();
	
	/**@author Sourav.Bhadra // for select daily ration commodities price details for a vendor on 26-10-2017 */
	public String getDailyRationCommoditiesPriceDetailsForAVendor(String vendorCode);
	/**@author anup.roy // for add mess menu details*/
	public int addMessMenuDetails(MessMenuRoutine messMenuRoutine);

	/**@author anup.roy // for getting mess menu list*/
	public List<MessMenuRoutine> getMessMenuList();

	/**@author anup.roy // for view the details of menu to edit*/
	public MessMenuRoutine getMessMenuDetails(MessMenuRoutine messMenuRoutine);

	/**@author anup.roy // for submitting edited mess menu*/
	public int editMessMenuDetails(MessMenuRoutine messMenuRoutine);

	/**@author Sourav.Bhadra // to map daily ration commodities with a vendor on 31-10-2017 */
	public String mapCommodityVendorForPerishableMaterial(List<Commodity> commodityList, String updatedBy);
	
	/**@author Sourav.Bhadra // to get daily ration vendor commodity mapping on 31-10-2017 */
	public String vendorCommodityListForPerishableMaterial(String vendorCode);
	
	/**@author Sourav.Bhadra // to get daily ration commodity price history on 31-10-2017 */
	public String vendorCommodityPriceHistoryForPerishableMaterial(Commodity commodity);
	
	/**@author Sourav.Bhadra // to get daily ration vendors list on 14-11-2017 */
	public List<Vendor> getMessDailyRationVendorList();
	
	/**@author Sourav.Bhadra // to submit daily ration vendor on 14-11-2017 */
	public String submitMessDailyRationVendor(Vendor vendor);
	
	/**@author Sourav.Bhadra // to update a daily ration vendor's details on 17-11-2017 */
	public String editMessDailyRationVendorDetails(Vendor vendor);
}
