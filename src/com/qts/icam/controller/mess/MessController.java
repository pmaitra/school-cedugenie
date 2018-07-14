package com.qts.icam.controller.mess;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.xml.resolver.apps.resolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.qts.icam.controller.inventory.InventoryController;
import com.qts.icam.model.common.CommodityIssueVoucher;
import com.qts.icam.model.common.CommodityIssueVoucherDetails;
import com.qts.icam.model.common.CommodityReceiveVoucher;
import com.qts.icam.model.common.CommodityReceiveVoucherDetails;
import com.qts.icam.model.common.FinancialYear;
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
import com.qts.icam.model.common.RoutineSlab;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.UpdateLog;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.common.VendorType;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.inventory.InventoryService;
import com.qts.icam.service.mess.MessService;
import com.google.gson.Gson;

@Controller
@SessionAttributes("sessionObject")
public class MessController {
	public static Logger logger = Logger.getLogger(MessController.class);
	@Autowired
	MessService messService = null;
	@Autowired
	InventoryService inventoryService = null;
	@Autowired
	CommonService commonService;	// added by Saif 21-03-2018
	/**
	 * @author anup.roy
	 * this method is for getting the view page of demand voucher*/
	
	@RequestMapping(value = "/createMessDemandVoucher", method = RequestMethod.GET)
	public String createMessDemandVoucher(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			logger.info("In createMessDemandVoucher() method of MessController.java");
			List<Commodity> commodityList = messService.getCommodityListForMess();
			model.addAttribute("commodityList",commodityList);
			int messVoucherRequestId = messService.getLastDemandVoucherId();
			model.addAttribute("messVoucherRequestId", messVoucherRequestId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In createMessDemandVoucher() method of MessController: Exception",e);
		}
		return "mess/createMessDemandVoucher";
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting unit,stock,rate of a commodity*/
	
	@RequestMapping(value="/getCommodityDetailsForIndentSheet", method = RequestMethod.GET)
	@ResponseBody
	public String getCommodityDetailsForIndentSheet(@RequestParam("commodity")String commodity){
		String commodityDetails = null;
		try{
			logger.info("In String getCommodityDetailsForIndentSheet(commodity) in MessController");
			Commodity detailsOfCommodity = messService.getCommodityDetailsForIndentSheet(commodity);
			StringBuilder str = new StringBuilder();
			if(null != detailsOfCommodity){
				str.append(detailsOfCommodity.getCommodityType()+"*");
				str.append(detailsOfCommodity.getInStock()+"*");
				str.append(detailsOfCommodity.getPurchaseRate()+"*");
				commodityDetails = str.toString().substring(0, str.toString().length() - 1);
			}
		}catch (Exception e) {
			logger.error("Exception in String getCommodityDetailsForIndentSheet(commodity) in MessController");
			e.printStackTrace();
		}
		return commodityDetails.toString();
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting list of all commodties for mess
	 * and populate to dynamic selectbox*/
	
	@RequestMapping(value = "/getCommoditiesForMess", method = RequestMethod.GET)
	public @ResponseBody String getCommoditiesForMess() {
		StringBuffer sb = new StringBuffer();
		try {
			logger.info("In String getCommoditiesForMess() of LibraryController.java");
			List<Commodity> commodityList = messService.getCommodityListForMess();
			for(Commodity com : commodityList){
				sb.append("<option value =\"" + com.getCommodityCode() + "\">"+ com.getCommodityName() + "</option>");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	@RequestMapping(value = "/submitMessDemandVoucher", method = RequestMethod.POST)
	public String submitMessDemandVoucher(HttpServletRequest request, HttpServletResponse response, ModelMap model,
										@RequestParam(required=false, value="commodity") String[] commodity,
										@RequestParam(required=false, value="commodityUnit") String[] commodityUnit,
										@RequestParam(required=false, value="commodityStock") String[] commodityStock,
										@RequestParam(required=false, value="commodityDemandedQuantity") String[] commodityDemandedQuantity,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
				logger.info("In submitMessDemandVoucher of MessController.java");
				MessDemandVoucher demandVoucher = new MessDemandVoucher();
				List<MessDemandVoucherDetails> messDemandVoucherDetailsList = new ArrayList<MessDemandVoucherDetails>();
				if (null!= commodity && commodity.length!=0){
					for(int i=0; i<commodity.length; i++){
						MessDemandVoucherDetails voucher = new MessDemandVoucherDetails();
						voucher.setCommodity(commodity[i]);
						voucher.setCommodityUnit(commodityUnit[i]);
						voucher.setCommodityInQMStock(Double.parseDouble(commodityStock[i]));
						voucher.setDemandedQuantity(Double.parseDouble(commodityDemandedQuantity[i]));
						messDemandVoucherDetailsList.add(voucher);
					}
				}
				demandVoucher.setUpdatedBy(sessionObject.getUserId());
				demandVoucher.setDemandVoucherId(request.getParameter("demandVoucherId"));
				demandVoucher.setMessDemandVoucherDetailsList(messDemandVoucherDetailsList);
				String insertStatus = messService.submitMessDemandVoucher(demandVoucher);
				if(insertStatus == "success"){
					Notification notification = new Notification();
					notification.setNotificationDesc(demandVoucher.getDemandVoucherId()+" is issued by "+sessionObject.getUserId());
					notification.setNotificationSender(demandVoucher.getUpdatedBy());
					notification.setUpdatedBy(sessionObject.getUserId());
					notification.setNotificationSubject(request.getParameter("demandVoucherId"));
					messService.sendNotification(notification);
				}				
				model.addAttribute("insertStatus", insertStatus);
				model.addAttribute("indentSheetId", demandVoucher.getDemandVoucherId());
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In submitMessDemandVoucherof MessController.java: Exception",e);
		}
		return viewDemandVoucherList(request, response, model, sessionObject); /* modified by sourav.bhadra on 13-10-2017 */
	}
	
	/**
	 * @author anup.roy
	 * this method is for list of demand vouchers*/
	
	@RequestMapping(value = "/viewDemandVoucherList", method = RequestMethod.GET)
	public String viewDemandVoucherList(HttpServletRequest request, HttpServletResponse response, ModelMap model,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			List<MessDemandVoucher> messDemandVoucherList = messService.getMessDemandVoucherList();
			model.addAttribute("messDemandVoucherList",messDemandVoucherList);
			String role = sessionObject.getCurrentRoleOrAccess();
			System.out.println("Role:"+role);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In viewDemandVoucherList() method of MessController: Exception",e);
		}
		return "mess/viewMessDemandVoucherList";
	}
	
	/**
	 * @author anup.roy
	 * this method is for view stock of mess*/
	
	@RequestMapping(value = "/viewMessStock", method = RequestMethod.GET)
	public String viewMessStock(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			logger.info("In String viewMessStock method in MessController.java");
			List<Commodity> commodityList = messService.getStockOfMess();
			model.addAttribute("commodityList", commodityList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception String viewMessStock method in MessController.java: Exception",e);
		}
		return "mess/viewMessStock";
	}
	
	/**
	 * @author anup.roy
	 * this method is for create daily ration PO*/
	
	@RequestMapping(value = "/createMessDailyRationPurchaseOrder", method = RequestMethod.GET)
	public String createMessDailyRationPurchaseOrder(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			/** added by sourav.bhadra on 23-10-2017 **/
			@RequestParam(required = false, value = "orderNumber")String orderNumber,
			@RequestParam(required = false, value = "orderID")String orderId) {
		try {
			logger.info("In String createMessDailyRationPurchaseOrder of MessController.java");
			String inventorySession = messService.getCurrentSession();
			model.addAttribute("inventorySession", inventorySession);
			List<Vendor> vendorList = messService.getAllVendors();
			model.addAttribute("vendorList", vendorList);
			List<Commodity> commodityList = messService.getMessDailyRationCommodities();
			model.addAttribute("commodityList", commodityList);
			String lastPOCode = messService.getLastMessDailyRationPOCode();
			model.addAttribute("lastPOCode", lastPOCode);
			/** added by @author Sourav.Bhadra on 09-10-2017
			 * to get commodity unit list **/
			List<Commodity> commodityUnitList = inventoryService.getCommodityUnitList();
			if (commodityUnitList != null && commodityUnitList.size() != 0) {				
				model.addAttribute("commodityUnitList", commodityUnitList);
			}
			/** added by @author Sourav.Bhadra on 23-10-2017
			 * to get commodity unit list **/
			List<PerishableMaterial> perishableMaterialsList = messService.viewIndividualPerishableMaterialRequisitionDetails(orderId);
			if (perishableMaterialsList != null && perishableMaterialsList.size() != 0) {				
				model.addAttribute("perishableMaterialsList", perishableMaterialsList);
				/** added by @author Sourav.Bhadra on 24-10-2017 **/
				model.addAttribute("orderId", orderId);
				model.addAttribute("orderNumber", orderNumber);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In String createMessDailyRationPurchaseOrder of MessController.java:",e);
		}
		return "mess/createMessDailyRationPurchaseOrder";
	}
	
	
	/**
	 * @author anup.roy
	 * this method is for submitting daily ration PO*/
	
	@RequestMapping(value="/submitMessDailyRationPO" , method = RequestMethod.POST)
	public String submitMessDailyRationPO ( HttpServletRequest request, HttpServletResponse response,
											ModelMap model, Vendor vendor,
											@RequestParam(required = false, value = "commodity")String[] commodity,
											@RequestParam(required = false, value = "commodityUnit")String[] commodityUnit,
											@RequestParam(required = false, value = "commodityRate")String[] commodityRate,
											@RequestParam(required = false, value = "commodityDemandedQuantity")String[] quantity,
											/** added by @author Sourav.Bhadra on 24-10-2017 **/
											@RequestParam(required = false, value = "reqID")String orderId,
											@RequestParam(required = false, value = "reqCode")String orderNumber,
											/** added by @author Sourav.Bhadra on 06-03-2018 **/
											@RequestParam(required = false, value = "amount")String[] amount,
											@ModelAttribute("sessionObject") SessionObject sessionObj
											){
		try{
			logger.info("In submitMessDailyRationPO of MessController.java");
			MessDailyRationPurchaseOrder messDailyPO = new MessDailyRationPurchaseOrder();
			messDailyPO.setVendor(vendor);
			messDailyPO.setUpdatedBy(sessionObj.getUserId());
			messDailyPO.setMessDailyRationPurchaseOrderCode(request.getParameter("messDailyRationPurchaseOrderCode"));
			messDailyPO.setInventorySession(request.getParameter("inventorySession"));
			List<MessDailyRationPurchaseOrderDetails> messDailyDetailsPOList = new ArrayList<MessDailyRationPurchaseOrderDetails>();
			if(null!= commodity){
				for(int i=0;i<commodity.length;i++){
					MessDailyRationPurchaseOrderDetails messDailyPODetails = new MessDailyRationPurchaseOrderDetails();
					Commodity cmd = new Commodity();
					cmd.setCommodityCode(commodity[i]);
					messDailyPODetails.setCommodity(cmd);
					messDailyPODetails.setUnit(commodityUnit[i]);
					messDailyPODetails.setRate(Double.parseDouble(commodityRate[i]));
					messDailyPODetails.setQuantity(Double.parseDouble(quantity[i]));
					messDailyPODetails.setTotalPrice(Double.parseDouble(amount[i]));/** added by @author Sourav.Bhadra on 06-03-2018 **/
					messDailyDetailsPOList.add(messDailyPODetails);
				}
				messDailyPO.setMessDailyRationPoDetailsList(messDailyDetailsPOList);
			}
			messDailyPO.setRequisitionId(orderId);	/** added by @author Sourav.Bhadra on 24-10-2017 **/
			messDailyPO.setTotalAmount(Double.parseDouble(request.getParameter("totalAmount")));/** added by @author Sourav.Bhadra on 06-03-2018 **/
			String insertStatus = messService.submitMessDailyRationPO(messDailyPO);
		}catch (Exception e) {
			logger.error("Exception in submitMessDailyRationPO of MessController.java");
			e.printStackTrace();
		}
		return getPerishableMaterialRequisitionList(request, response, model);	/** modified by @author Sourav.Bhadra on 24-10-2017 **/
	}
	
	/**
	 * @author anup.roy
	 * this method is for create and submit mess menu*/
	
	@RequestMapping(value = "/createMessMenu", method = RequestMethod.GET)
	public String createMessMenu(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			logger.info("In String createMessMenu() of MessController.java");
			List<MessMenuTime> menuTimeList = messService.getMessMenuTimeList();
			model.addAttribute("menuTimeList", menuTimeList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In createMessMenu() method of MessController: Exception",e);
		}
		return "mess/createMessMenu";
	}
	
	/**
	 * @author anup.roy
	 * this method is for displaying mess menu list
	 * */
	
	@RequestMapping(value = "/viewMessMenuList", method = RequestMethod.GET)
	public String viewMessMenuList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			logger.info("In viewMessMenuList in messController.java");
			List<MessMenuRoutine> messMenuRoutineList = messService.getMessMenuList();
			System.out.println("size of list:"+messMenuRoutineList.size());
			model.addAttribute("messMenuRoutineList", messMenuRoutineList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In viewMessMenuList in messController.java: Exception",e);
		}
		return "mess/messMenuList";
	}
	
	
	/**
	 * @author anup.roy
	 * this method is for create CIV
	 * w.r.t Indent Sheet
	 */
	@RequestMapping(value = "/createCIV", method = RequestMethod.GET)
	public String createCIV(
							HttpServletRequest request, HttpServletResponse response,
							ModelMap model,
							@RequestParam("indentSheetId") String indentSheetId
							) {
		MessDemandVoucher messDemandVoucher = null;
		String strView = null;
		try {
			logger.info("In createCIV method of MessController");
			String nextCIVId = messService.getLastCommodityIssueVoucherId();
			model.addAttribute("nextCIVId", nextCIVId);
			messDemandVoucher = messService.getDetailsOfDemandVoucher(indentSheetId);
			if (messDemandVoucher != null) {
				model.addAttribute("messDemandVoucher", messDemandVoucher);
				strView = "mess/createCIV";
			} else {
				strView = "common/error404";
			}
		} catch (Exception e) {
			logger.error("Exception in createCIV method of MessController",e);
			e.printStackTrace();
		}
		return strView;
	}
	
	/**
	 * @author anup.roy
	 * this method is for submitting CIV
	 * */
	
	@RequestMapping(value = "/submitCommodityIssueVoucher", method = RequestMethod.POST)
	public String submitCommodityIssueVoucher(HttpServletRequest request, HttpServletResponse response, ModelMap model,
												@RequestParam(required=false, value="commodityName") String[] commodityName,
												@RequestParam(required=false, value="issuedQuantityToMess") String[] issuedQuantityToMess,
												@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
				logger.info("In submitCommodityIssueVoucher of MessController.java");
				CommodityIssueVoucher issueVoucher = new CommodityIssueVoucher();
				List<CommodityIssueVoucherDetails> issueVoucherDetailsList = new ArrayList<CommodityIssueVoucherDetails>();
				if(null!= commodityName && commodityName.length!= 0){
					for(int i = 0; i<commodityName.length; i++){
						CommodityIssueVoucherDetails issueVoucherDetails = new CommodityIssueVoucherDetails();
						issueVoucherDetails.setCommodityName(commodityName[i]);
						issueVoucherDetails.setIssuedQuantityToMess(Double.parseDouble(issuedQuantityToMess[i]));
						issueVoucherDetailsList.add(issueVoucherDetails);
					}
				}
				issueVoucher.setUpdatedBy(sessionObject.getUserId());
				issueVoucher.setCommodityIssueVoucherCode(request.getParameter("commodityIssueVoucherId"));
				issueVoucher.setCommodityIssueVoucherDetailsList(issueVoucherDetailsList);
				MessDemandVoucher voucherId = new MessDemandVoucher();
				voucherId.setDemandVoucherId(request.getParameter("demandVoucherId"));
				issueVoucher.setMessDemanVoucher(voucherId);
				String civCreationStatus = messService.submitCommodityIssueVoucher(issueVoucher);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in submitCommodityIssueVoucher of MessController.java: Exception",e);
		}
		return viewDemandVoucherList(request, response, model, sessionObject);
	}
	
	/**
	 * @author anup.roy
	 * this method is for create CIV
	 * w.r.t Indent Sheet
	 */
	@RequestMapping(value = "/createCRV", method = RequestMethod.GET)
	public String createCRV(
							HttpServletRequest request, HttpServletResponse response,
							ModelMap model,
							@RequestParam("civId") String civId
							) {
		CommodityIssueVoucher issueVoucher = null;
		String strView = null;
		try {
			logger.info("In createCRV method of MessController");
			int index = Integer.parseInt(civId.substring(3));
			String nextCRVId = "CRV"+index;
			model.addAttribute("nextCRVId", nextCRVId);
			issueVoucher = messService.getDetailsOfIssueVoucher(civId);
			if (issueVoucher != null) {
				model.addAttribute("issueVoucher", issueVoucher);
				strView = "mess/createCRV";
			} else {
				strView = "common/error404";
			}
		} catch (Exception e) {
			logger.error("Exception in createCRV method of MessController",e);
			e.printStackTrace();
		}
		return strView;
	}
	
	/**
	 * @author anup.roy
	 * this method is for submitting CIV
	 * */
	
	@RequestMapping(value = "/submitCommodityReceiveVoucher", method = RequestMethod.POST)
	public String submitCommodityReceiveVoucher(HttpServletRequest request, HttpServletResponse response, ModelMap model,
												@RequestParam(required=false, value="commodityName") String[] commodityName,
												@RequestParam(required=false, value="issuedQuantityToMess") String[] issuedQuantityToMess,
												@RequestParam(required=false, value="lpNo") String[] lpNo,
												@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
				logger.info("In submitCommodityReceiveVoucher of MessController.java");
				CommodityReceiveVoucher receiveVoucher = new CommodityReceiveVoucher();
				receiveVoucher.setUpdatedBy(sessionObject.getUserId());
				receiveVoucher.setCommodityIssueVoucherCode(request.getParameter("commodityIssueVoucherId"));
				receiveVoucher.setCommodityReceiveVoucherCode(request.getParameter("commodityReceiveVoucherId"));
				receiveVoucher.setIndentSheetId(request.getParameter("demandVoucherId"));
				List<CommodityReceiveVoucherDetails> receiveVoucherDetailsList = new ArrayList<CommodityReceiveVoucherDetails>();
				if(null!= commodityName){
					for(int i=0; i< commodityName.length; i++){
						CommodityReceiveVoucherDetails receiveVoucherDetails = new CommodityReceiveVoucherDetails();
						receiveVoucherDetails.setCommodityName(commodityName[i]);
						receiveVoucherDetails.setIssuedQuantityToMess(Double.parseDouble(issuedQuantityToMess[i]));
						receiveVoucherDetails.setLpNo(Integer.parseInt(lpNo[i]));
						receiveVoucherDetailsList.add(receiveVoucherDetails);
					}
				}
				receiveVoucher.setCommodityReceiveVoucherDetailsList(receiveVoucherDetailsList);
				String receiveStatus = messService.submitCommodityReceiveVoucher(receiveVoucher);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in submitCommodityReceiveVoucher of MessController.java: Exception",e);
		}
		return viewDemandVoucherList(request, response, model, sessionObject);
	}
	
	/** @author Sourav.Bhadra
	 * to get daily mess ration vendors details **/
	@RequestMapping(value="/getDailyRationVendorDetails", method = RequestMethod.GET)
	@ResponseBody
	public String getDailyRationVendorDetails(@RequestParam("vendorCode")String vendorCode){
		String vendorDetails = "";
		try{
			logger.info("In String getDailyRationVendorDetails(vendorCode) in MessController");
			vendorDetails = messService.getDailyRationVendorDetails(vendorCode);
		}catch (Exception e) {
			logger.error("Exception in String getCommodityDetailsForIndentSheet(commodity) in MessController");
			e.printStackTrace();
		}
		return vendorDetails.toString();
	}
	
	/**
	 * @author anup.roy
	 * this method is for daily mess consumption view page
	 */
	
	@RequestMapping(value = "/createDailyMessConsumption", method = RequestMethod.GET)
	public String createDailyMessConsumption(HttpServletRequest request, HttpServletResponse response, 
											ModelMap model, @ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("In String createDailyMessConsumption of MessController.java");
			String inventorySession = messService.getCurrentSession();
			model.addAttribute("inventorySession", inventorySession);
			model.addAttribute("userId", sessionObject.getUserId());
			model.addAttribute("userName", sessionObject.getUserName());
			String lastConsumptionCIVCode = messService.getLastMessDailyConsumptionCIVCode();
			model.addAttribute("lastConsumptionCIVCode", lastConsumptionCIVCode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In String createDailyMessConsumption of MessController.java:",e);
		}
		return "mess/createDailyMessConsumption";
	}
	
	/** @author Sourav.Bhadra 10-10-2017
	 * to get commodities for daily mess consumption **/
	@RequestMapping(value="/getCommoditiesForDailyMessConsumption", method = RequestMethod.GET)
	@ResponseBody
	public String getCommoditiesForDailyMessConsumption(@RequestParam("commodityType")String commodityType){
		String allCommodities = "";
		try{
			logger.info("In String getDailyRationVendorDetails(vendorCode) in MessController");
			allCommodities = messService.getCommoditiesForDailyMessConsumption(commodityType);
		}catch (Exception e) {
			logger.error("Exception in String getCommodityDetailsForIndentSheet(commodity) in MessController");
			e.printStackTrace();
		}
		return allCommodities.toString();
	}
	
	/** @author Sourav.Bhadra 10-10-2017
	 * to get commodities mess stock for daily mess consumption **/
	@RequestMapping(value="/getCommoditiesMessStockForDailyMessConsumption", method = RequestMethod.GET)
	@ResponseBody
	public String getCommoditiesMessStockForDailyMessConsumption(
			@RequestParam("commodityType")String commodityType,
			@RequestParam("commodity")String commodity,
			@RequestParam("issueDate")String issueDate){
		String messStock = "";
		try{
			MessDailyConsumptionDetails consumptionDetails = null;
			consumptionDetails = messService.getCommoditiesMessStockForDailyMessConsumption(commodityType, commodity, issueDate);
			StringBuilder sb = new StringBuilder();
			if(consumptionDetails != null){
				sb.append(consumptionDetails.getCommodityAU() + "#");
				sb.append(consumptionDetails.getOldStockInForIssue() + "#");
				sb.append(consumptionDetails.getRate() + "#");
				messStock = sb.toString().substring(0, sb.toString().length() - 1);
			}	
		}catch (Exception e) {
			logger.error("Exception in String getCommodityDetailsForIndentSheet(commodity) in MessController");
			e.printStackTrace();
		}
		System.out.println(messStock.toString());
		return messStock.toString();
	}
	
	/**
	 * @author anup.roy
	 * this method is for submitting daily mess consumption*/
	
	@RequestMapping(value="/submitMessDailyConsumption" , method = RequestMethod.POST)
	public String submitMessDailyConsumption ( HttpServletRequest request, HttpServletResponse response,
											ModelMap model,
											@RequestParam(required = false, value = "lpNo")String[] lpNo,
											@RequestParam(required = false, value = "commodityType")String[] commodityType,
											@RequestParam(required = false, value = "commodityName")String[] commodityName,
											@RequestParam(required = false, value = "commodityAU")String[] commodityAU,
											@RequestParam(required = false, value = "oldStockInForIssue")String[] oldStockInForIssue,
											@RequestParam(required = false, value = "rate")String[] rate,
											@RequestParam(required = false, value = "issuingQuantity")String[] issuingQuantity,
											@RequestParam(required = false, value = "amount")String[] amount,
											@ModelAttribute("sessionObject") SessionObject sessionObj
											){
		try{
			logger.info("In submitMessDailyConsumption of MessController.java");
			MessDailyConsumption consumption = new MessDailyConsumption();
			consumption.setUpdatedBy(consumption.getUserId());
			List<MessDailyConsumptionDetails> consumptionDetailsList = new ArrayList<MessDailyConsumptionDetails>();
			if(null!= commodityName){
				for(int i=0;i< commodityName.length;i++){
					MessDailyConsumptionDetails consumptionDetails = new MessDailyConsumptionDetails();
					consumptionDetails.setLpNo(lpNo[i]);
					consumptionDetails.setCommodityType(commodityType[i]);
					consumptionDetails.setCommodityName(commodityName[i]);
					consumptionDetails.setCommodityAU(commodityAU[i]);
					consumptionDetails.setOldStockInForIssue(Double.parseDouble(oldStockInForIssue[i]));
					consumptionDetails.setRate(Double.parseDouble(rate[i]));
					consumptionDetails.setIssuingQuantity(Double.parseDouble(issuingQuantity[i]));
					consumptionDetails.setAmount(Double.parseDouble(amount[i]));
					consumptionDetailsList.add(consumptionDetails);
				}
			}
			consumption.setConsumptionDetailsList(consumptionDetailsList);
			String consumptionStatus = messService.submitMessDailyConsumption(consumption);
		}catch (Exception e) {
			logger.error("Exception in submitMessDailyConsumption of MessController.java");
			e.printStackTrace();
		}
		return createDailyMessConsumption(request, response, model, sessionObj);
	}
	
	/**
	 * @author Sourav.Bhadra 16-09-2017
	 * this method is for create perishable material requisition */
	
	@RequestMapping(value = "/createPerishableMaterialRequisition", method = RequestMethod.GET)
	public String createPerishableMaterialRequisition(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			logger.info("In String createMessDailyRationPurchaseOrder of MessController.java");
			String inventorySession = messService.getCurrentSession();
			model.addAttribute("inventorySession", inventorySession);
			List<Vendor> vendorList = messService.getAllVendors();
			model.addAttribute("vendorList", vendorList);
			List<Commodity> commodityList = messService.getMessDailyRationCommodities();
			model.addAttribute("commodityList", commodityList);
			String lastPOCode = messService.getLastMessDailyRationPOCode();
			model.addAttribute("lastPOCode", lastPOCode);
			List<Commodity> commodityUnitList = inventoryService.getCommodityUnitList();
			if (commodityUnitList != null && commodityUnitList.size() != 0) {				
				model.addAttribute("commodityUnitList", commodityUnitList);
			}
			/** added by Sourav.Bhadra on 18-10-2017 **/
			String nextPerishableMaterialRequisitionCode = messService.getNextPerishableMaterialRequisitionCode();
			if (nextPerishableMaterialRequisitionCode != null && nextPerishableMaterialRequisitionCode != "") {				
				model.addAttribute("nextPMR", nextPerishableMaterialRequisitionCode);
			}
			/** added by Sourav.Bhadra on 19-10-2017 **/
			String nextPerishableMaterialRequisitionOrderNumber = messService.getNextPerishableMaterialRequisitionOrderNumber();
			if (nextPerishableMaterialRequisitionOrderNumber != null && nextPerishableMaterialRequisitionOrderNumber != "") {				
				model.addAttribute("nextPMROrderNo", nextPerishableMaterialRequisitionOrderNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In String createMessDailyRationPurchaseOrder of MessController.java:",e);
		}
		return "mess/createPerishableMaterialRequisition";
	}
	
	/**
	 * @author Sourav.Bhadra 16-09-2017
	 * this method is to get all commodities for perishable material requisition request */
	@RequestMapping(value = "/getNameOfCommodities", method = RequestMethod.GET)
	public @ResponseBody String getNameOfCommodities() {
		String nameOfCommodities = "";
		try {
			logger.info("getNameOfVendors()In LibraryController.java: calling getBookName( String strQuery) in BackOfficeService.java");
			List<Commodity> dailyRationCommodities = messService.getMessDailyRationCommodities();
			if(null != dailyRationCommodities || dailyRationCommodities.size() !=0){
				for(Commodity c : dailyRationCommodities){
					nameOfCommodities += c.getCommodityName() + ";";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getNameOfVendors() In LibraryController.java: Exception"+ e);
		}
		return (new Gson().toJson(nameOfCommodities.toString()));
	}
	
	/** @author Sourav.Bhadra 16-10-2017
	 * to get commodities mess stock for perishable material requisition request **/
	@RequestMapping(value="/getCommodityUnitForPerishableMaterialRequisition", method = RequestMethod.GET)
	@ResponseBody
	public String getCommodityUnitForPerishableMaterialRequisition(@RequestParam("commodityName")String commodityName){
		String commodityUnit = "";
		try{
			commodityUnit = messService.getCommodityUnitForPerishableMaterialRequisition(commodityName);
		}catch (Exception e) {
			logger.error("Exception in String getCommodityDetailsForIndentSheet(commodity) in MessController");
			e.printStackTrace();
		}
		//System.out.println("LN613 :: MCon :: Commodity :: "+commodityName+"\tUnit :: "+commodityUnit);
		return (new Gson().toJson(commodityUnit.toString()));
	}
	
	/**
	 * @author Sourav.Bhadra 18-10-2017
	 * this method is for submitting perishable material requisition request */
	
	@RequestMapping(value="/submitPerishableMaterialRequisition" , method = RequestMethod.POST)
	public String submitPerishableMaterialRequisition( HttpServletRequest request, HttpServletResponse response,
											ModelMap model,
											@RequestParam(required = false, value = "inventorySession")String inventorySession,
											@RequestParam(required = false, value = "orderId")String orderId,
											@RequestParam(required = false, value = "orderNumber")String orderNumber,
											@RequestParam(required = false, value = "endDate")String endDate,
											@RequestParam(required = false, value = "commodity")String[] commodityName,
											@RequestParam(required = false, value = "commodityUnit")String[] commodityUnit,
											@RequestParam(required = false, value = "commodityDemandedQuantity")String[] commodityQuantity,
											@ModelAttribute("sessionObject") SessionObject sessionObj
											){
		try{
			logger.info("In submitMessDailyConsumption of MessController.java");
			PerishableMaterial perishablematerial = new PerishableMaterial();
			List<PerishableMaterial> pmList = new ArrayList<PerishableMaterial>();
			for(int i=0; i<commodityName.length; i++){
				PerishableMaterial pm = new PerishableMaterial();
				pm.setCommodityName(commodityName[i]);
				pm.setCommodityUnit(commodityUnit[i]);
				pm.setCommodityQuantity(commodityQuantity[i]);
				pm.setOrderId(orderId);
				pm.setUpdatedBy(sessionObj.getUserId());
				
				pmList.add(pm);
			}
			perishablematerial.setPerishableMaterialsList(pmList);
			perishablematerial.setFinancialSession(inventorySession);
			perishablematerial.setOrderId(orderId);
			perishablematerial.setUpdatedBy(sessionObj.getUserId());
			perishablematerial.setOrderNumber(orderNumber);
			perishablematerial.setCloseDate(endDate);
			String status = messService.submitPerishableMaterialRequisition(perishablematerial);
		}catch (Exception e) {
			logger.error("Exception in submitMessDailyConsumption of MessController.java");
			e.printStackTrace();
		}
		return getPerishableMaterialRequisitionList(request, response, model);
	}
	
	/**
	 * @author Sourav.Bhadra 18-09-2017
	 * this method is for select perishable material requisition */
	
	@RequestMapping(value = "/perishableMaterialRequisitionList", method = RequestMethod.GET)
	public String getPerishableMaterialRequisitionList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			logger.info("In String getPerishableMaterialRequisitionList of MessController.java");
			List<PerishableMaterial> perishableMaterialsList = messService.getPerishableMaterialRequisitionList();
			model.addAttribute("perishableMaterialsList", perishableMaterialsList);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In String createMessDailyRationPurchaseOrder of MessController.java:",e);
		}
		return "mess/perishableMaterialRequisitionList";
	}
	
	/**
	 * @author Sourav.Bhadra 18-09-2017
	 * this method is for select individual perishable material requisition details */
	
	@RequestMapping(value = "/viewIndividualPerishableMaterialRequisitionDetails", method = RequestMethod.GET)
	public String viewIndividualPerishableMaterialRequisitionDetails(HttpServletRequest request, HttpServletResponse response, ModelMap model,
			@RequestParam(required = false, value = "orderNumber")String orderNumber,
			@RequestParam(required = false, value = "orderID")String orderId) {
		try {
			logger.info("In String getPerishableMaterialRequisitionList of MessController.java");
			List<PerishableMaterial> perishableMaterialsList = messService.viewIndividualPerishableMaterialRequisitionDetails(orderId);
			model.addAttribute("perishableMaterialsList", perishableMaterialsList);
			model.addAttribute("orderNumber", orderNumber);
			model.addAttribute("orderId", orderId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In String createMessDailyRationPurchaseOrder of MessController.java:",e);
		}
		return "mess/perishableMaterialRequisitionIndividualDetails";
	}
	
	/** @author Sourav.Bhadra on 26-10-2017
	 * to get daily mess ration vendors details **/
	@RequestMapping(value="/getDailyRationCommoditiesPriceDetailsForAVendor", method = RequestMethod.GET)
	@ResponseBody
	public String getDailyRationCommoditiesPriceDetailsForAVendor(@RequestParam("vendorCode")String vendorCode){
		String commodityPriceDetails = "";
		try{
			logger.info("In String getDailyRationVendorDetails(vendorCode) in MessController");
			commodityPriceDetails = messService.getDailyRationCommoditiesPriceDetailsForAVendor(vendorCode);
		}catch (Exception e) {
			logger.error("Exception in String getCommodityDetailsForIndentSheet(commodity) in MessController");
			e.printStackTrace();
		}
		return commodityPriceDetails.toString();
	}

	/**
	 * @author anup.roy
	 * this method is for submitting mess menu details
	 * */
	
	@RequestMapping(value = "/submitMessMenuDetails", method = RequestMethod.POST)
	public String submitMessMenuDetails(HttpServletRequest request, HttpServletResponse response,
			ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			MessMenuRoutine messMenuRoutine) {
		try {
			logger.info("In submitMessMenuDetails of MessController.java");
			String[] params = request.getParameterValues("messMenuTime");	
			String[] days = request.getParameterValues("days");
			List<RoutineSlab> routineSlabList = new ArrayList<RoutineSlab>();
			for(int j = 0 ; j < request.getParameterValues(params[0]).length; j++){
				RoutineSlab routineSlab = new RoutineSlab();
				List<MessMenuTime> messMenuTimeList = new ArrayList<MessMenuTime>();
				routineSlab.setRoutineSlabCode("RTN-"+(j+1));
				routineSlab.setRoutineSlabName(days[j]);
				routineSlab.setUpdatedBy(sessionObject.getUserId());
				for(int i = 0; i < params.length; i++){
					MessMenuTime messMenuTime = new MessMenuTime();
					messMenuTime.setUpdatedBy(sessionObject.getUserId());
					messMenuTime.setMessMenuTimeCode(params[i]);
					messMenuTime.setMessMenuTimeName((request.getParameterValues(params[i])[0]));
					messMenuTime.setMessMenuValue((request.getParameterValues(params[i])[j]));
					messMenuTimeList.add(messMenuTime);
				}
				routineSlab.setMessMenuTimeList(messMenuTimeList);
				routineSlabList.add(routineSlab);
			}
			messMenuRoutine.setUpdatedBy(sessionObject.getUserId());
			messMenuRoutine.setRoutineSlabList(routineSlabList);
			int insertStatus = messService.addMessMenuDetails(messMenuRoutine);
		} catch (Exception e) {
			logger.error("Exception In submitMessMenuDetails of MessController.java",e);
			e.printStackTrace();
		}
		return viewMessMenuList(request, response, model);
	}

	/**
	 * @author anup.roy
	 * this method is for view the edit page of a menu
	 * */
	
	@RequestMapping(value = "/viewMessMenuDetails", method = RequestMethod.GET)
	public String viewMessMenuDetails(HttpServletRequest request,ModelMap model,
									@RequestParam(required = false, value = "messMenuCode") String messMenuCode) {
		try {
			logger.info("In viewMessMenuDetails of MessController.java");
			MessMenuRoutine messMenuRoutine = new MessMenuRoutine();
			messMenuRoutine.setMessMenuRoutineCode(messMenuCode);
			messMenuRoutine = messService.getMessMenuDetails(messMenuRoutine);
			model.addAttribute("messMenuRoutineDetails", messMenuRoutine);
			List<MessMenuTime> messMenuList = messService.getMessMenuTimeList();
			if (messMenuList != null) {
				model.addAttribute("messMenuList", messMenuList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in viewMessMenuDetails of MessController.java:" + e);
		}
		return "mess/editMessMenuDetails";
	}
	
	/**
	 * @author anup.roy
	 * this method is for submitting the edited menu*/
	
	@RequestMapping(value = "/updateMessMenuDetails", method = RequestMethod.POST)
	public String updateMessMenuDetails(HttpServletRequest request,
			ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			MessMenuRoutine messMenuRoutine) {
		try {
			logger.info("In updateMessMenuDetails method of MessController.java");
			String[] params = request.getParameterValues("messMenuTime");	
			String[] days = request.getParameterValues("days");			
									
			List<RoutineSlab> routineSlabList = new ArrayList<RoutineSlab>();
			for(int j = 0 ; j < request.getParameterValues(params[0]).length; j++){		
				RoutineSlab routineSlab = new RoutineSlab();
				List<MessMenuTime> messMenuTimeList = new ArrayList<MessMenuTime>();
				routineSlab.setRoutineSlabCode("RTN-"+(j+1));
				routineSlab.setRoutineSlabName(days[j]);
				routineSlab.setUpdatedBy(sessionObject.getUserId());				
				for(int i = 0; i < params.length; i++){	
					MessMenuTime messMenuTime = new MessMenuTime();
					messMenuTime.setUpdatedBy(sessionObject.getUserId());
					messMenuTime.setMessMenuTimeCode(params[i]);
					messMenuTime.setMessMenuTimeName(request.getParameterValues(params[i])[0]);					
					messMenuTime.setMessMenuValue((request.getParameterValues(params[i])[j]));
					messMenuTimeList.add(messMenuTime);
				}			
				routineSlab.setMessMenuTimeList(messMenuTimeList);
				routineSlabList.add(routineSlab);
			}
			messMenuRoutine.setUpdatedBy(sessionObject.getUserId());
			messMenuRoutine.setRoutineSlabList(routineSlabList);
			int insertStatus = messService.editMessMenuDetails(messMenuRoutine);
			if (insertStatus != 0) {
				model.addAttribute("successStatus", "Successfully updated");
			} else {
				model.addAttribute("failStatus", "Update failed");
			}
			List<MessMenuTime> messMenuList = messService.getMessMenuTimeList();
			model.addAttribute("messMenuList", messMenuList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception In updateMessMenuDetails() method of MessController: Exception",e);
		}
		return viewMessMenuDetails(request, model, messMenuRoutine.getMessMenuRoutineCode());
	}

	/** @author Sourav.Bhadra on 30-10-2017
	 * for daily ration vendor commodity mapping **/
	@RequestMapping(value = "/dailyRationVendorCommodityMapping", method = RequestMethod.GET)
	public String dailyRationVendorCommodityMapping(HttpServletRequest request,
										  HttpServletResponse response,
										  ModelMap model) {
		try {
			List<Vendor> vendorList = messService.getAllVendors();
			model.addAttribute("vendorList",vendorList);
			
			List<Commodity> commodityList = messService.getMessDailyRationCommodities();
			model.addAttribute("commodityList", commodityList);
			
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "mess/messDailyRationCommodityVendorMapping";
	}
	
	/** @author Sourav.Bhadra on 31-10-2017
	 * to get daily ration vendor commodity mapping **/
	@RequestMapping(value = "/vendorCommodityListForPerishableMaterial", method = RequestMethod.GET)
	public @ResponseBody
	String vendorCommodityListForPerishableMaterial(@RequestParam("vendorCode") String vendorCode) {
		String vendorCommodityList = "";
		try {
			vendorCommodityList = messService.vendorCommodityListForPerishableMaterial(vendorCode.trim());
			//System.out.println("LN823 MessCon :: "+vendorCode);
			//System.out.println("LN823 MessCon :: "+vendorCommodityList);
		} catch (Exception e) {
			logger.error("Exception In vendorCommodityList() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(vendorCommodityList));
	}
	
	/** @author Sourav.Bhadra on 31-10-2017
	 * to save daily ration vendor commodity mapping **/
	@RequestMapping(value = "/mapCommodityVendorForPerishableMaterial", method = RequestMethod.POST)
	public String mapCommodityVendorForPerishableMaterial(HttpServletRequest request,
									  HttpServletResponse response,
									  ModelMap model,
									  @RequestParam("vendorCode") String vendorCode,
									  @RequestParam("commodity") String[] commodityCode,
									  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try {
			List<Commodity> commodityList=new ArrayList<Commodity>();
			for(int i=0;i<commodityCode.length;i++){
				Commodity commodity=new Commodity();
				
				double oldPrice=Double.parseDouble(request.getParameter("old"+commodityCode[i].trim()));
				double newPrice=Double.parseDouble(request.getParameter(commodityCode[i].trim()));
				
				commodity.setSellingRate(newPrice);
				commodity.setOldSellingRate(oldPrice);
				
				commodity.setCommodityCode(commodityCode[i].trim().toUpperCase());
				commodity.setVendor(vendorCode);
				
				commodityList.add(commodity);
			}
			
			status=messService.mapCommodityVendorForPerishableMaterial(commodityList, sessionObject.getUserId());
			
			model.addAttribute("message", status);
		} catch (Exception e) {
			logger.error("Exception In mapCommodityVendor() method of InventoryController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(status);
		}
		return dailyRationVendorCommodityMapping(request, response, model);
	}
	
	/** @author Sourav.Bhadra on 31-10-2017
	 * to get daily ration commodity price history **/
	@RequestMapping(value = "/vendorCommodityPriceHistoryForPerishableMaterial", method = RequestMethod.GET)
	public @ResponseBody
	String vendorCommodityPriceHistoryForPerishableMaterial(@RequestParam("vendorCode") String vendorCode,
										@RequestParam("commodityCode") String commodityCode) {
		String vendorCommodityPriceHistory = "";
		try {
			Commodity commodity=new Commodity();
			commodity.setVendor(vendorCode);
			commodity.setCommodityCode(commodityCode);
			
			vendorCommodityPriceHistory=messService.vendorCommodityPriceHistoryForPerishableMaterial(commodity);
		} catch (Exception e) {
			logger.error("Exception In vendorCommodityPriceHistory() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(vendorCommodityPriceHistory));
	}
	
	/** @author Sourav.Bhadra on 14-11-2017
	 * to add mess daily ration vendor **/
	@RequestMapping(value = "/addDailyRationVendor", method = RequestMethod.GET)
	public String addDailyRationVendor(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		try {
			logger.info("In addDailyRationVendor method of MessController.java");
			List vendorList = messService.getMessDailyRationVendorList();
			model.addAttribute("vendorDetailsList", vendorList);
		} catch (Exception e) {
			logger.error("addDailyRationVendor() In MessController.java: Exception",e);
		}
		return "mess/addMessDailyRationVendor";
	}
	
	/** @author Sourav.Bhadra on 14-11-2017
	 * to submit add mess daily ration vendor **/
	@RequestMapping(value = "/submitAddMessDailyRationVendor", method = RequestMethod.POST)
	public String submitAddVendor(ModelMap model, Vendor vendor, HttpServletRequest request, HttpServletResponse response,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("In submitVendor method of CommonController.java");
			vendor.setUpdatedBy(sessionObject.getUserId());
			String insertStatus = messService.submitMessDailyRationVendor(vendor);
			if(insertStatus == "Success"){
				model.addAttribute("successStatus", "Successfully updated");
			}else{
				model.addAttribute("failStatus", "Update failed");
			}
		} catch (Exception e) {
			logger.error("submitAddVendor() In CommonController.java: Exception", e);
		}
		return addDailyRationVendor(request,response,model);
	}
	
	/** @author Sourav.Bhadra on 17-11-2017
	 * to update a mess daily ration vendor's details **/
	@RequestMapping(value = "/editMessDailyRationVendorDetails", method = RequestMethod.POST)
	public String editMessDailyRationVendorDetails(HttpServletRequest request,
							  HttpServletResponse response,
							  ModelMap model,
							  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try {
			
			Vendor vendor = new Vendor();
			
			String saveId=request.getParameter("saveId");
			String vendorname = request.getParameter("getVendorName");
			String contactno = request.getParameter("getContactNo1");
			//String contctno1 = request.getParameter("getContactNo2");
			//String email = request.getParameter("getEmailId");
			String add = request.getParameter("getAddress");
			//String bankname = request.getParameter("getBankName");
			//String accno =request.getParameter("getAccountNo");
			//String code= request.getParameter("getBankCode");
			//String location = request.getParameter("getBankLocation");
			//String branchcod =request.getParameter("getBranchCode");
			vendor.setVendorCode(request.getParameter("oldVendorCode"+saveId));
			
			vendor.setUpdatedBy(sessionObject.getUserId());
			vendor.setVendorName(vendorname);
			vendor.setVendorDesc(vendorname);
			vendor.setVendorContactNo1(contactno);
			//vendor.setVendorContactNo2(contctno1);
			
			//vendor.setEmailId(email);
			//vendor.setBranchCode(branchcod);
			//System.out.println(vendor.getEmailId());
			vendor.setVendorAddress(add);
			//vendor.setBankName(bankname);
			//vendor.setAccountNo(Long.parseLong((accno)));
			//vendor.setBankCode(code);
			//vendor.setBankLocation(location);
			status=messService.editMessDailyRationVendorDetails(vendor);
			model.addAttribute("insertUpdateStatus", status);
			/**Added by @author Saif.Ali
			 * Date- 21/03/2018
			 * Used to insert the information into the activity_log table*/
			String oldVendorName = request.getParameter("newVendorName"+saveId);	// old vendor name
			String oldContactNumber= request.getParameter("vendorContact1"+saveId);	// old contact number
			String oldvendorAddress= request.getParameter("vendorAddress"+saveId);	// old vendor address
		
			if(status.equalsIgnoreCase("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT DAILY RATION VENDOR DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("MESS");
				updateLog.setUpdatedFor(oldVendorName);
				String description = "";
				if(!(oldVendorName.equalsIgnoreCase(vendorname)))
				{
					description=description + ("Vendor Name :: " + oldVendorName + " updated to new Vendor Name :: " + vendorname + " ; ");
				}
				if(!(oldContactNumber.equalsIgnoreCase(contactno)))
				{
					description=description +  (" Contact number" + oldContactNumber+ " of "+ oldVendorName + " updated to ::" + contactno+ " ; ");
				}
				if(!(oldvendorAddress.equalsIgnoreCase(add)))
				{
					description=description +  (" Address" + oldvendorAddress+ " of "+ oldVendorName + " updated to ::" + add+ " ; ");
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 1083 :: MessController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/**Addition ends*/
			
		} catch (Exception e) {
			logger.error("Exception In editCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(status);
		}
		return addDailyRationVendor(request,response,model);
	}
}
