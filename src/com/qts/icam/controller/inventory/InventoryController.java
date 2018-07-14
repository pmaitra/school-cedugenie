package com.qts.icam.controller.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.qts.icam.model.common.AnnualStock;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.FinancialYear;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.Task;
import com.qts.icam.model.common.UpdateLog;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.finance.Group;
import com.qts.icam.model.finance.Tax;
import com.qts.icam.model.hostel.Hostel;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.model.inventory.CommodityPurchaseOrder;
import com.qts.icam.model.inventory.CommodityPurchaseOrderDetails;
import com.qts.icam.model.inventory.IndividualCommodity;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.service.inventory.InventoryService;

import com.google.gson.Gson;
import com.qts.icam.service.academics.AcademicsService;
import com.qts.icam.service.administrator.AdministratorService;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.finance.FinanceService;


@Controller
@SessionAttributes("sessionObject")
public class InventoryController {
	public static Logger logger = Logger.getLogger(InventoryController.class);
	@Autowired
	InventoryService inventoryService = null;
	
	@Autowired
	AcademicsService academicsService = null;
	
	@Autowired
	AdministratorService administratorService = null;
	
	@Autowired
	FinanceService financeService = null;
		
	@Value("${root.path}")
	private String rootPath;
	
	@Value("${excelUpload.folder}")
	private String excelUploadFolder;
	
	@Value("${excelDownload.folder}")
	private String excelDownloadFolder;
	
	@Value("${commodity.excel}")
	private String commodityExcel;
	
	/**added by saif 23-03-2018*/
	@Autowired
	CommonService commonService = null;
	/***/
	
	
	
	@RequestMapping(value = "/addCommodity", method = RequestMethod.GET)
	public String addCommodity(HttpServletRequest request,
							  HttpServletResponse response,
							  ModelMap model) {
		try {
			List<Commodity> commodityList=inventoryService.listCommodity();
			
			if (commodityList != null && commodityList.size() != 0) {				
				model.addAttribute("commodityList", commodityList);
			}else{
				model.addAttribute("message", "Commodity Not Created Yet.");
			}
			
			/* added by sourav.bhadra on 28-07-2017
			 * to get commodity unit list */
			List<Commodity> commodityUnitList=inventoryService.getCommodityUnitList();
			
			if (commodityUnitList != null && commodityUnitList.size() != 0) {				
				model.addAttribute("commodityUnitList", commodityUnitList);
			}
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/addCommodity";
	}
	
	@RequestMapping(value = "/inventoryProcessFlow", method = RequestMethod.GET)
	public ModelAndView showerpProcessFlow(ModelMap model) {
		String strView = null;		
		strView = "inventory/inventoryProcessFlow";		
		return new ModelAndView(strView);
	}
	
	@RequestMapping(value = "/checkCommodityName", method = RequestMethod.GET)
	public @ResponseBody
	String checkCommodityName(@RequestParam("commodityName") String commodityName) {
		String valid = null;
		try {
			valid = inventoryService.checkCommodityName(commodityName.trim().toUpperCase());
		} catch (Exception e) {
			logger.error("Exception In checkCommodityName() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(valid));
	}
	
	@RequestMapping(value = "/saveCommodity", method = RequestMethod.POST)
	public String saveCommodity(HttpServletRequest request,
							  HttpServletResponse response,
							  ModelMap model,
							  Commodity commodity,
							  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		String msg="";
		try {
			status=inventoryService.saveCommodity(commodity, sessionObject.getUserId());
			if("success".equalsIgnoreCase(status)){
				msg = "Commodity Successfully Added";
			}else{
				msg = "Failed to Add Commodity";
			}
			model.addAttribute("status", status);
			model.addAttribute("msg", msg);
		} catch (Exception e) {
			logger.error("Exception In saveCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(status);
		}
		return addCommodity(request, response, model);
	}
	
	/***
	 * modified by saurav.bhadra
	 * changes taken on 10042017
	 * */
	
	@RequestMapping(value = "/editCommodity", method = RequestMethod.POST)
	public String editCommodity(HttpServletRequest request,
							  HttpServletResponse response,
							  ModelMap model,
							  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try {
			
			Commodity commodity=new Commodity();
			String saveId=request.getParameter("saveId");
			String commodityCode = request.getParameter("oldCommodityCode"+saveId);
			String commodityName = request.getParameter("commodityNewName").trim().toUpperCase();
			String commodityDesc = request.getParameter("commodityNewDesc").trim();
			//String threshold = request.getParameter("newThreshold").trim();
			String commodityType = request.getParameter("commodityNewType");
			String commoditySubType = request.getParameter("commodityNewSubType");
			
			commodity.setCommodityCode(commodityCode);
			commodity.setCommodityName(commodityName);
			commodity.setCommodityDesc(commodityDesc);
			commodity.setCommodityType(commodityType);
			commodity.setCommoditySubType(commoditySubType);
			//commodity.setThreshold(Double.parseDouble(threshold));
			commodity.setUpdatedBy(sessionObject.getUserId());
			
			status=inventoryService.editCommodity(commodity);
			model.addAttribute("insertUpdateStatus", status);
			/**Added by Saif 23-03-2018
			 * Used to insert into the activity_log table*/
			if(status.equalsIgnoreCase("Update Successful")){
				String oldCommodityName= request.getParameter("oldCommodityCode"+saveId);
				String oldCommodityDesc= request.getParameter("commodityDesc"+saveId);
				//String oldCommodityThreshold= request.getParameter("threshold"+saveId);
				String oldCommodityType= request.getParameter("commodityType"+saveId);
				String oldCommoditySubType= request.getParameter("commoditySubType"+saveId); 
				String description = "";
				if(!(oldCommodityName.equalsIgnoreCase(commodityName)))
				{
					description = description + ("Commodity Name :: " + oldCommodityName + " updated to new commodity name :: " + commodityName + " ; ");
				}
				if(!(oldCommodityDesc.equalsIgnoreCase(commodityDesc)))
				{
					description = description + ("Commodity Description :: " + oldCommodityDesc + " of " +" Commodity Name :: " + oldCommodityName + " updated to new Commodity Description :: " + commodityDesc+ " ; ");
				}
				/*if(!(oldCommodityThreshold.equalsIgnoreCase(threshold)))
				{
					description = description + ("Commodity Threshold :: " + oldCommodityThreshold +" of " +" Commodity Name :: " + oldCommodityName + " updated to new Commodity Threshold :: " + threshold+ " ; ");
				}*/
				if(!(oldCommodityType.equalsIgnoreCase(commodityType)))
				{
					description = description + ("Commodity Type :: " + oldCommodityType + " of " +" Commodity Name :: " + oldCommodityName +" updated to new Commodity Type :: " + commodityType+ " ; ");
				}
				if(!(oldCommoditySubType.equalsIgnoreCase(commoditySubType)))
				{
					description = description + ("Commodity Sub Type :: " + oldCommoditySubType +" of " +" Commodity Name :: " + oldCommodityName + " updated to new Commodity Sub Type :: " + commoditySubType+ " ; ");
				}
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT COMMODITY DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("INVENTORY");
				updateLog.setUpdatedFor(commodityCode);
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 3178 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
			
		} catch (Exception e) {
			logger.error("Exception In editCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(status);
		}
		return addCommodity(request, response, model);
	}
	
	@RequestMapping(value = "/commodityVendorMapping", method = RequestMethod.GET)
	public String commodityVendorMapping(HttpServletRequest request,
										  HttpServletResponse response,
										  ModelMap model) {
		try {
			List<Vendor> vendorList=inventoryService.commodityVendorList();
			model.addAttribute("vendorList",vendorList);
			
			List<Commodity> commodityList=inventoryService.listCommodity();
			model.addAttribute("commodityList", commodityList);
			
			List<FinancialYear> financialYearList = commonService.getFinancialYearList();
			model.addAttribute("financialYearList", financialYearList);
			
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/commodityVendorMapping";
	}
	
	@RequestMapping(value = "/vendorCommodityList", method = RequestMethod.GET)
	public @ResponseBody
	String vendorCommodityList(@RequestParam("vendorCode") String vendorCode,
								@RequestParam("financialYear") String financialYear) {
		String vendorCommodityList = "";
		try {
			vendorCommodityList = inventoryService.vendorCommodityList(vendorCode.trim(),financialYear.trim());
			//System.out.println(vendorCommodityList);
		} catch (Exception e) {
			logger.error("Exception In vendorCommodityList() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(vendorCommodityList));
	}
	
	@RequestMapping(value = "/mapCommodityVendor", method = RequestMethod.POST)
	public String mapCommodityVendor(HttpServletRequest request,
									  HttpServletResponse response,
									  ModelMap model,
									  @RequestParam("vendorCode") String vendorCode,
									  @RequestParam("financialYear") String financialYear, // Added By Naimisha 03052018
									  @RequestParam("commodity") String[] commodityCode,
									  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		int count= 0;
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
				
				commodity.setDate(financialYear);
				
				commodityList.add(commodity);
				count=commodityList.size();	// added by Saif 26-03-2018
				
			}
			
			status=inventoryService.mapCommodityVendor(commodityList, sessionObject.getUserId());
			
			model.addAttribute("message", status);
			/**Added by @author Saif.Ali
			 * Date-26-03-2018*/
			String vendorName = request.getParameter("vendorName");
			if(status.equalsIgnoreCase(count+" Commodities Mapped Successfully.")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT COMMODITY VENDOR MAPPING");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("INVENTORY");
				updateLog.setUpdatedFor("vendor code :: " + vendorCode);
				String description = "" ;
				for(int i=0;i<commodityCode.length;i++)
				{
					double oldPrice=Double.parseDouble(request.getParameter("old"+commodityCode[i].trim()));
					double newPrice=Double.parseDouble(request.getParameter(commodityCode[i].trim()));
					if(oldPrice != newPrice)
					{
						description = description + ("vendor code :: " + vendorCode + " mapped with commodity :: " + commodityCode[i] + " with Old price :: " + oldPrice + " is now updated to new price :: " + newPrice + " ; ");
					}
					else
					{
						description= description + ("vendor code :: " + vendorCode + " mapped with commodity :: " + commodityCode[i] + " with price :: " + newPrice + " ; ");
					}
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 327 :: InventoryController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		} catch (Exception e) {
			logger.error("Exception In mapCommodityVendor() method of InventoryController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(status);
		}
		return commodityVendorMapping(request, response, model);
	}
	
	@RequestMapping(value = "/vendorCommodityPriceHistory", method = RequestMethod.GET)
	public @ResponseBody
	String vendorCommodityPriceHistory(@RequestParam("vendorCode") String vendorCode,
										@RequestParam("commodityCode") String commodityCode) {
		String vendorCommodityPriceHistory = "";
		try {
			Commodity commodity=new Commodity();
			commodity.setVendor(vendorCode);
			commodity.setCommodityCode(commodityCode);
			
			vendorCommodityPriceHistory=inventoryService.vendorCommodityPriceHistory(commodity);
		} catch (Exception e) {
			logger.error("Exception In vendorCommodityPriceHistory() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(vendorCommodityPriceHistory));
	}
	
	
	/**
	 * modified by sourav.bhadra
	 * changes taken on 23062017
	 * */
	@RequestMapping(value = "/commodityPurchaseOrder", method = RequestMethod.GET)
	public String commodityPurchaseOrder(HttpServletRequest request,
										  HttpServletResponse response,
										  ModelMap model) {
		try {
			String commodityPurchaseOrderCode = inventoryService.getNextCommodityPurchaseOrderCode();
			List<Vendor> vendorList = inventoryService.commodityVendorList();
			model.addAttribute("vendorList", vendorList);
			List<Vendor> bankList = inventoryService.getBankDetailsList();
			model.addAttribute("bankList", bankList);
			if(null != commodityPurchaseOrderCode && !commodityPurchaseOrderCode.trim().equalsIgnoreCase(""))
			model.addAttribute("commodityPurchaseOrderCode",commodityPurchaseOrderCode);
			
			List<CommodityPurchaseOrder> commodityRequisitionList = inventoryService.commodityRequisitionListNotPresentInPO();
			model.addAttribute("commodityRequisitionList",commodityRequisitionList);
			
			
			//List<Department> departmentList= academicsService.getAllDepartment();
			//model.addAttribute("departmentList", departmentList);
			
			//List<Ledger>ledgerList = financeService.getLedgerList();//Added By Naimisha 03012017
			
			///Added By Naimisha 06062017
			
			/* added by sourav.bhadra on 22-06-2017 */
			/*List<Tax> taxList = financeService.getTaxPercentages();
			if(null != taxList){
				model.addAttribute("taxList", taxList);
			}*/
			
			/* added by sourav.bhadra on 28-07-2017 
			 * to display group list*/
			/*List<Group> groupList = financeService.getGroupList();
			if (groupList != null && groupList.size() != 0) {
				model.addAttribute("groupList", groupList);
			}*/
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/commodityPurchaseOrder";
	}
	
	@RequestMapping(value = "/getRemeaningCommodities", method = RequestMethod.GET)
	public @ResponseBody
	String getRemeaningCommodities(@RequestParam("vendorCode") String vendorCode) {
		String remeaningCommodityList = "";
		try {
			remeaningCommodityList = inventoryService.remeaningCommodities(vendorCode.trim());
		} catch (Exception e) {
			logger.error("Exception In vendorCommodityList() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(remeaningCommodityList));
	}
	
	/**
	 * modified by sourav.bhadra
	 * changes taken on 04072017
	 * **/
	
	@RequestMapping(value = "/createCommodityPurchaseOrder", method = RequestMethod.POST)
	public String createCommodityPurchaseOrder(HttpServletRequest request,
											  HttpServletResponse response,
											  ModelMap model,
											  CommodityPurchaseOrder commodityPurchaseOrder,
											  @RequestParam(value = "commodityCode", required = false) String[] commodityCode,
											  @RequestParam(value = "purchaseRate", required = false) String[] purchaseRate,
											  @RequestParam(value = "quantity", required = false) String[] quantity,
											//  @RequestParam(value = "discount", required = false) String[] discount,
											  @RequestParam(value = "total", required = false) String[] total,
											  @RequestParam(value = "expenceDescription", required = false) String[] expenceDesc,
											  @RequestParam(value = "expenceType", required = false) String[] paymentType,
											  @RequestParam(value = "expenceAmount", required = false) String[] expenceAmount,
											  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try {
			commodityPurchaseOrder.setPendingAmount(commodityPurchaseOrder.getTotalCommodityAmount());
			commodityPurchaseOrder.setNetTotal(commodityPurchaseOrder.getTotalCommodityAmount());
			commodityPurchaseOrder.setReceiveStatus("OPEN");
			commodityPurchaseOrder.setUpdatedBy(sessionObject.getUserId());
			
			commodityPurchaseOrder.setAmountStatus("OPEN");
			commodityPurchaseOrder.setAdvanceAmount(0);
			commodityPurchaseOrder.setTotalQtyReceived(0.0);
			commodityPurchaseOrder.setOrderStatus("OPEN");
			double totalQty = 0.0;
			
			commodityPurchaseOrder.setTotalQtyDeficit(commodityPurchaseOrder.getTotalQtyOrdered());
			
			List<CommodityPurchaseOrderDetails> commodityPurchaseOrderDetailsList=new ArrayList<CommodityPurchaseOrderDetails>();
			if(quantity!=null && quantity.length!=0){
				for(int i=0;i<quantity.length;i++){
					if(Double.parseDouble(quantity[i])!=0.0 && commodityCode[i].trim()!=""){
						CommodityPurchaseOrderDetails cpod=new CommodityPurchaseOrderDetails();
						cpod.setCommodity(commodityCode[i].trim().toUpperCase());
						cpod.setQtyOrdered(Double.parseDouble(quantity[i]));
						cpod.setRate(Double.parseDouble(purchaseRate[i]));
						//cpod.setDiscount(Double.parseDouble(discount[i]));
						cpod.setAmount(Double.parseDouble(total[i]));
						cpod.setQtyDeficit(Double.parseDouble(quantity[i]));
						cpod.setQtyDefect(0.0);
						cpod.setQtyReceived(0.0);
						cpod.setReceiveStatus("OPEN");
						cpod.setCommodityPurchaseOrderCode(commodityPurchaseOrder.getPurchaseOrderCode());
						
						
						
						if(null != expenceDesc && expenceDesc.length !=0){
							cpod.setExpenceDesc(expenceDesc[i]);
							cpod.setPaymentType(paymentType[i]);
							cpod.setExpenceAmount(Double.parseDouble(expenceAmount[i]));
						}
						
						commodityPurchaseOrderDetailsList.add(cpod);
					}
				}
			}
			
			if(commodityPurchaseOrderDetailsList.size()!=0)
				commodityPurchaseOrder.setCommodityPurchaseOrderDetailsList(commodityPurchaseOrderDetailsList);
			else
				commodityPurchaseOrder.setReceiveStatus("CLOSED");
			
			
			if((commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList()!=null && commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList().size()!=0)){
				
				status = inventoryService.createCommodityPurchaseOrder(commodityPurchaseOrder, sessionObject.getUserId());
			}else{
				logger.info("No Service Or Commodity Found In Purchase Order While Inserting, hence Not Inserted And Rejected");
			}
		} catch (Exception e) {
			logger.error("Exception In createCommodityPurchaseOrder() method of InventoryController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(status);
		}
		return commodityPurchaseOrderList(request, response, model, "OPEN");
	}
	
	@RequestMapping(value = "/commodityPurchaseOrderList", method = RequestMethod.GET)
	public String commodityPurchaseOrderList(HttpServletRequest request,
											  HttpServletResponse response,
											  ModelMap model,
											  @RequestParam("status") String status) {
		try {
			
			List<CommodityPurchaseOrder> commodityPurchaseOrderList=inventoryService.commodityPurchaseOrderList(status);
			model.addAttribute("commodityPurchaseOrderList",commodityPurchaseOrderList);
			model.addAttribute("status",status);
			
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/commodityPurchaseOrderList";
	}
	
	

	@RequestMapping(value = "/commodityPayment", method = RequestMethod.GET)
	public String commodityPayment(HttpServletRequest request,
									  HttpServletResponse response,
									  ModelMap model,
									  @RequestParam("orderID") String orderID) {
		try {
			CommodityPurchaseOrder commodityPurchaseOrder=inventoryService.commodityPurchaseOrderPaymentDetails(orderID);
			if(commodityPurchaseOrder!=null){
				model.addAttribute("commodityPurchaseOrder",commodityPurchaseOrder);
				List<Ledger>ledgerList = financeService.getLedgerList();//Added By Naimisha 03012017
				
				if (ledgerList != null && ledgerList.size() != 0) {
					model.addAttribute("ledgerList",ledgerList);
				}
			}else{
				String message1="Payment Details Not Found For Commodity Purchase Order "+ orderID;
				model.addAttribute("message1",message1);
			}
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/commodityPurchaseOrderPayment";
	}
	
	@RequestMapping(value = "/makeCommodityPayment", method = RequestMethod.POST)
	public String makeCommodityPayment(HttpServletRequest request,
									  HttpServletResponse response,
									  ModelMap model,
									  CommodityPurchaseOrder commodityPurchaseOrder,
									  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try {
			double paying=commodityPurchaseOrder.getPayAmount();
			double advance=commodityPurchaseOrder.getAdvanceAmount()+paying;
			commodityPurchaseOrder.setAdvanceAmount(advance);
			double pending=commodityPurchaseOrder.getPendingAmount()-paying;
			commodityPurchaseOrder.setPendingAmount(pending);
			
			if(commodityPurchaseOrder.getPendingAmount()<=0){
				commodityPurchaseOrder.setAmountStatus("CLOSED");
				if(commodityPurchaseOrder.getReceiveStatus().trim().equalsIgnoreCase("CLOSED")){
					
					commodityPurchaseOrder.setOrderStatus("CLOSED");
				}else{
					commodityPurchaseOrder.setOrderStatus("OPEN");
				}
			}else{
				commodityPurchaseOrder.setAmountStatus("OPEN");
				commodityPurchaseOrder.setOrderStatus("OPEN");
			}
			
			status=inventoryService.makeCommodityPayment(commodityPurchaseOrder, sessionObject.getUserId());
			
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(status);
		}
		return commodityPurchaseOrderList(request, response, model, "OPEN");
	}	
	
	@RequestMapping(value = "/receiveCommodityOrder", method = RequestMethod.GET)
	public String receiveCommodityOrder(HttpServletRequest request,
									  HttpServletResponse response,
									  ModelMap model,
									  @RequestParam("orderID") String orderID) {
		try {
			CommodityPurchaseOrder commodityPurchaseOrder=inventoryService.commodityPurchaseOrderReceiveDetails(orderID);
			if(commodityPurchaseOrder!=null){
				model.addAttribute("commodityPurchaseOrder",commodityPurchaseOrder);
			}else{
			}
			List<Ledger>ledgerList = financeService.getLedgerList();//Added By naimisha.ghosh 03062017
			if (ledgerList != null && ledgerList.size() != 0) {
				model.addAttribute("ledgerList",ledgerList);
			}
			/* added by ranita.sur on 01-08-2017 
			 * to display group list*/
			List<Group> groupList = financeService.getGroupList();
			if (groupList != null && groupList.size() != 0) {
				model.addAttribute("groupList", groupList);
			}
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/commodityPurchaseOrderReceive";
	}
	
	/**
	 * modified by sourav.bhadra
	 * changes taken on 24062017*/
	
	@RequestMapping(value = "/makeCommodityReceive", method = RequestMethod.POST)
	public String makeCommodityReceive(HttpServletRequest request,
									  HttpServletResponse response,
									  ModelMap model,
									  CommodityPurchaseOrder commodityPurchaseOrder,
									  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String receiveStatus="";
		String paymentStatus="";
		try {
			String commodities[]= request.getParameterValues("commodity");
			
			if(commodities!=null && commodities.length!=0){
				List<CommodityPurchaseOrderDetails> commodityPurchaseOrderDetailsList=new ArrayList<CommodityPurchaseOrderDetails>();
				double newTotalReceive=0.0;
				double newTotalRemeaning=0.0;
				for(int i=0;i<commodities.length;i++){
					CommodityPurchaseOrderDetails cpod = new CommodityPurchaseOrderDetails();
					
					double oldDamage=Double.parseDouble(request.getParameter(commodities[i]+"oldDamage"));
					double newDamage=Double.parseDouble(request.getParameter(commodities[i]+"damage"));
					double oldReceive=Double.parseDouble(request.getParameter(commodities[i]+"received"));
					double newReceive=Double.parseDouble(request.getParameter(commodities[i]+"receiving"));
					double receive=(newReceive+oldReceive)-newDamage;
					double increaseInStock=newReceive-newDamage;
					double damage=newDamage+oldDamage;
					double oldRemeaning=Double.parseDouble(request.getParameter(commodities[i]+"remeaning"));
					double remeaning=oldRemeaning-(newReceive-newDamage);
					
					newTotalReceive=newTotalReceive+receive;
					newTotalRemeaning=newTotalRemeaning+remeaning;
					
					cpod.setCommodity(commodities[i]);
					cpod.setQtyDefect(damage);
					cpod.setQtyDeficit(remeaning);
					cpod.setQtyReceived(receive);
					cpod.setIncreaseInStock(increaseInStock);
					cpod.setCommodityPurchaseOrderCode(commodityPurchaseOrder.getPurchaseOrderCode());
					
					/* modified by sourav.bhadra on 11-09-2017 */
					double comRate = Double.parseDouble(request.getParameter(commodities[i]+"rate"));
					cpod.setRate(comRate);
					commodityPurchaseOrderDetailsList.add(cpod);
				}
				
				if(commodityPurchaseOrderDetailsList.size()!=0){
					commodityPurchaseOrder.setCommodityPurchaseOrderDetailsList(commodityPurchaseOrderDetailsList);
					commodityPurchaseOrder.setTotalQtyDeficit(newTotalRemeaning);
					commodityPurchaseOrder.setTotalQtyReceived(newTotalReceive);
					if(newTotalRemeaning>0){
						commodityPurchaseOrder.setReceiveStatus("OPEN");
						commodityPurchaseOrder.setOrderStatus("OPEN");
					}else{
						commodityPurchaseOrder.setReceiveStatus("CLOSED");
						if(request.getParameter("amountStatus").equalsIgnoreCase("OPEN")){
							commodityPurchaseOrder.setOrderStatus("OPEN");
						}else{
							commodityPurchaseOrder.setOrderStatus("CLOSED");
						}
					}
				}
				receiveStatus = inventoryService.makeCommodityReceive(commodityPurchaseOrder, sessionObject.getUserId());
			}
			
			if("" != receiveStatus || null != receiveStatus){							//Added by naimisha ghosh 30042018
				double paying=commodityPurchaseOrder.getPayAmount();
				double advance=commodityPurchaseOrder.getAdvanceAmount()+paying;
				commodityPurchaseOrder.setAdvanceAmount(advance);
				double pending=commodityPurchaseOrder.getPendingAmount()-paying;
				commodityPurchaseOrder.setPendingAmount(pending);
				
				if(commodityPurchaseOrder.getPendingAmount()<=0){
					commodityPurchaseOrder.setAmountStatus("CLOSED");
					if(commodityPurchaseOrder.getReceiveStatus().trim().equalsIgnoreCase("CLOSED")){
						
						commodityPurchaseOrder.setOrderStatus("CLOSED");
					}else{
						commodityPurchaseOrder.setOrderStatus("OPEN");
					}
				}else{
					commodityPurchaseOrder.setAmountStatus("OPEN");
					commodityPurchaseOrder.setOrderStatus("OPEN");
				}
				paymentStatus = inventoryService.makeCommodityPayment(commodityPurchaseOrder, sessionObject.getUserId());
			}
			
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(receiveStatus);
			logger.info(paymentStatus);
		}
		return commodityRequisitionList(request, response, model, "OPEN");
	}
	
	
	@RequestMapping(value = "/allocateCommodity", method = RequestMethod.GET)
	public String allocateCommodity(HttpServletRequest request,
									  HttpServletResponse response,
									  ModelMap model,
									  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			List<Commodity> commodityList=inventoryService.getAssetCommodity();
			model.addAttribute("commodityList", commodityList);
			model.addAttribute("allocatedBy", sessionObject.getUserId());
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/allocateCommodity";
	}
	
	@RequestMapping(value = "/getIndividualNotAllotedCommodity", method = RequestMethod.GET)
	public @ResponseBody
	String getIndividualNotAllotedCommodity(@RequestParam("commodityCode") String commodityCode) {
		String individualCommodities = "";
		try {
			individualCommodities = inventoryService.getIndividualNotAllotedCommodity(commodityCode.trim());
		} catch (Exception e) {
			logger.error("Exception In vendorCommodityList() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(individualCommodities));
	}
	
	
	@RequestMapping(value = "/allotCommodity", method = RequestMethod.POST)
	public String allotCommodity(HttpServletRequest request,
								  HttpServletResponse response,
								  ModelMap model,
								  @RequestParam("allotedBy") String allotedBy,
								  @RequestParam("commodityCode") String[] commodityCode,
								  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try {
			List<IndividualCommodity> individualCommodityList = new ArrayList<IndividualCommodity>();
			if(commodityCode!=null && commodityCode.length!=0){
				for(int i=0;i<commodityCode.length;i++){
					IndividualCommodity ic=new IndividualCommodity();
					ic.setAllotedBy(allotedBy);
					ic.setIndividualCommodityCode(commodityCode[i]);
					ic.setAllotedTo(request.getParameter(commodityCode[i]+"allotTo"));
					
					individualCommodityList.add(ic);
				}
			}
			if(individualCommodityList.size()!=0)
				status=inventoryService.allotCommodity(individualCommodityList, sessionObject.getUserId());
			else
				status="No Commodities Found To Allocate";
			/**Added by @author Saif.Ali
			 * Date-26-03-2018*/
			if(status.equalsIgnoreCase("Allocation Successful")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT ALLOCATE COMMODITY");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("INVENTORY");
				String description = "";
				if(commodityCode!=null && commodityCode.length!=0)
				{
					for(int i=0; i<commodityCode.length; i++)
					{
						updateLog.setUpdatedFor("Commodity Code :: " + commodityCode[i]);
						description = description + ("Commodity :: " + commodityCode[i] + " allocated by ::" + allotedBy +" allocated to :: " + request.getParameter(commodityCode[i]+"allotTo") + " ; ");
					}
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 794 :: InventoryController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		} catch (Exception e) {
			logger.error("Exception In allotCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(status);
		}
		return commodityAllotmentHistory(request, response, model);
	}
	
	@RequestMapping(value = "/deAllocateCommodity", method = RequestMethod.GET)
	public String deAllocateCommodity(HttpServletRequest request,
									  HttpServletResponse response,
									  ModelMap model,
									  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			List<Commodity> commodityList=inventoryService.getAssetCommodity();
			model.addAttribute("commodityList", commodityList);
			model.addAttribute("deAllocatedBy", sessionObject.getUserId());
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/deAllocateCommodity";
	}

	

	@RequestMapping(value = "/getIndividualAllotedCommodity", method = RequestMethod.GET)
	public @ResponseBody
	String getIndividualAllotedCommodity(@RequestParam("commodityCode") String commodityCode) {
		String individualAllotedCommodities = "";
		try {
			individualAllotedCommodities = inventoryService.getIndividualAllotedCommodity(commodityCode.trim());
		} catch (Exception e) {
			logger.error("Exception In vendorCommodityList() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(individualAllotedCommodities));
	}
	
	
	@RequestMapping(value = "/deAllotCommodity", method = RequestMethod.POST)
	public String deAllotCommodity(HttpServletRequest request,
								  HttpServletResponse response,
								  ModelMap model,
								  @RequestParam("returnedTo") String returnedTo,
								  @RequestParam("commodityCode") String[] commodityCode,
								  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try {
			List<IndividualCommodity> individualCommodityList = new ArrayList<IndividualCommodity>();
			if(commodityCode!=null && commodityCode.length!=0){
				for(int i=0;i<commodityCode.length;i++){
					IndividualCommodity ic=new IndividualCommodity();
					ic.setReturnedTo(returnedTo);
					ic.setIndividualCommodityCode(commodityCode[i]);
					ic.setComment(request.getParameter(commodityCode[i]+"comment"));
					
					individualCommodityList.add(ic);
				}
			}
			if(individualCommodityList.size()!=0)
				status=inventoryService.deAllotCommodity(individualCommodityList, sessionObject.getUserId());
			else
				status="No Commodities Found To De-Allocate";
			/**Added by @author Saif.Ali
			 * Date-13-03-2018*/
			if(status.equalsIgnoreCase("De-Allocation Successful")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT DE ALLOCATE COMMODITY");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("INVENTORY");
				String description = "";
				if(commodityCode!=null && commodityCode.length!=0)
				{
					for(int i=0;i<commodityCode.length;i++)
					{
						updateLog.setUpdatedFor("Commodity Code :: " + commodityCode[i]);
						description = description + ("Commodity Code :: " + commodityCode[i] + " returned to :: " + returnedTo + " with comment :: " + request.getParameter(commodityCode[i]+"comment") + " ; ");
					}
				}
				
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 889 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		} catch (Exception e) {
			logger.error("Exception In allotCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(status);
		}
		return commodityAllotmentHistory(request, response, model);
	}
	
	@RequestMapping(value = "/commodityAllotmentHistory", method = RequestMethod.GET)
	public String commodityAllotmentHistory(HttpServletRequest request,
									  HttpServletResponse response,
									  ModelMap model) {
		try {
			List<Commodity> commodityList=inventoryService.getAssetCommodity();
			model.addAttribute("commodityList", commodityList);
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/commodityAllotmentHistory";
	}
	
	
	
	
	@RequestMapping(value = "/getIndividualCommodityList", method = RequestMethod.GET)
	public @ResponseBody
	String getIndividualCommodityList(@RequestParam("commodityCode") String commodityCode) {
		String individualCommodities = "";
		try {
			individualCommodities = inventoryService.getIndividualCommodityList(commodityCode.trim());
		} catch (Exception e) {
			logger.error("Exception In vendorCommodityList() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(individualCommodities));
	}
	
	
	@RequestMapping(value = "/getCommodityAllotmentHistory", method = RequestMethod.GET)
	public @ResponseBody
	String getCommodityAllotmentHistory(@RequestParam("individualCommodity") String individualCommodity) {
		String allotmentHistory = "";
		try {
			allotmentHistory = inventoryService.getCommodityAllotmentHistory(individualCommodity.trim());
		} catch (Exception e) {
			logger.error("Exception In vendorCommodityList() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(allotmentHistory));
	}
	
	
	
	@RequestMapping(value = "/commodityDetails", method = RequestMethod.GET)
	public String commodityDetails(HttpServletRequest request,
									  HttpServletResponse response,
									  ModelMap model) {
		try {
			List<Commodity> commodityList=inventoryService.getAssetCommodity();
			if(commodityList!=null && commodityList.size()!=0){
				model.addAttribute("commodityList", commodityList);
			}else{
				model.addAttribute("message", "Commodity Not Created Yet.");
			}
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/individualCommodityDetails";
	}
	
	
	@RequestMapping(value = "/getIndividualCommodity", method = RequestMethod.GET)
	public @ResponseBody
	String getIndividualCommodity(@RequestParam("commodityCode") String commodityCode) {
		String individualCommodities = "";
		try {
			individualCommodities = inventoryService.getIndividualCommodity(commodityCode.trim());
		} catch (Exception e) {
			logger.error("Exception In vendorCommodityList() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(individualCommodities));
	}
		
	
	
	@RequestMapping(value = "/updateIndividualCommodityDetails", method = RequestMethod.POST)
	public String updateIndividualCommodityDetails(HttpServletRequest request,
												  HttpServletResponse response,
												  ModelMap model,
												  @RequestParam("commodityCode") String[] commodityCode,
												  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try {
			List<IndividualCommodity> individualCommodityList = new ArrayList<IndividualCommodity>();
			if(commodityCode!=null && commodityCode.length!=0){
				for(int i=0;i<commodityCode.length;i++){
					IndividualCommodity ic=new IndividualCommodity();
					ic.setIndividualCommodityCode(commodityCode[i]);
					ic.setModelNo(request.getParameter(commodityCode[i]+"model"));
					ic.setWarranty(Double.parseDouble(request.getParameter(commodityCode[i]+"warranty")));
					ic.setDepreciation(Double.parseDouble(request.getParameter(commodityCode[i]+"depreciation")));
					
					individualCommodityList.add(ic);
				}
			}
			if(individualCommodityList.size()!=0)
				status=inventoryService.updateIndividualCommodityDetails(individualCommodityList, sessionObject.getUserId());
			else
				status="No Commodities Found To Update Details";
			
			model.addAttribute("status", status);
			/**Added by Saif Date-23-03-2018
			 * Used to insert data into the activity log table*/
			if(status.equalsIgnoreCase("Update Successful")){
				String commodityDetailsName = request.getParameter("commodityDetailsIndividual");
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT COMMODITY DETAILS ");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("INVENTORY");
				String description = "";
				if(commodityCode!=null && commodityCode.length!=0)
				{
					for(int i=0;i<commodityCode.length;i++)
					{
						String modelName = request.getParameter(commodityCode[i]+"model");
						double warranty = Double.parseDouble(request.getParameter(commodityCode[i]+"warranty"));
						double depreciation = Double.parseDouble(request.getParameter(commodityCode[i]+"depreciation"));
						updateLog.setUpdatedFor(commodityDetailsName); 
						description = description + (" Commodity :: " + commodityDetailsName + " commodity code :: " + commodityCode[i]
								+ " with model :: " + modelName + " with warranty :: " + warranty + " with depriciation :: " + depreciation + " ; ");
					}
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 942 :: InventoryController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		} catch (Exception e) {
			logger.error("Exception In updateIndividualCommodityDetails() method of InventoryController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(status);
		}
		return commodityDetails(request, response, model);
	}	
	
	@RequestMapping(value = "/retireCommodity", method = RequestMethod.POST)
	public String retireCommodity(HttpServletRequest request,
								  HttpServletResponse response,
								  ModelMap model,
								  @RequestParam("commodityCode") String[] commodityCode,
								  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try {
			List<IndividualCommodity> individualCommodityList = new ArrayList<IndividualCommodity>();
			if(commodityCode!=null && commodityCode.length!=0){
				for(int i=0;i<commodityCode.length;i++){
					IndividualCommodity ic=new IndividualCommodity();
					ic.setIndividualCommodityCode(commodityCode[i]);
					
					individualCommodityList.add(ic);
				}
			}
			if(individualCommodityList.size()!=0)
				status=inventoryService.retireCommodity(individualCommodityList, sessionObject.getUserId());
			else
				status="No Commodities Found To Update Details";
			
			model.addAttribute("status", status);
		} catch (Exception e) {
			logger.error("Exception In retireCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(status);
		}
		return commodityDetails(request, response, model);
	}
	
	@RequestMapping(value = "/closeCommodityOrder", method = RequestMethod.GET)
	public String closeCommodityOrder(HttpServletRequest request,
									  HttpServletResponse response,
									  ModelMap model,
									  @RequestParam("orderID") String orderID,
									  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try {
			status=inventoryService.closeCommodityOrder(orderID, sessionObject.getUserId());
			
			model.addAttribute("status2",status);
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return commodityPurchaseOrderList(request, response, model, "OPEN");
	}
	

	/**
	 * sourav.bhadra
	 * changes taken 27062017*/
	
	@RequestMapping(value = "/getVendorBankDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getSubGroup(@RequestParam("vendorName") String vendorName) {
		String bankCode="";
		try {
			bankCode = inventoryService.getVendorBankAllDetails(vendorName);
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(bankCode));
	}
	
	/**@author Saif.Ali
	 * DATE-10/07/2017
	 * Used to create mess purchase order*/
	@RequestMapping(value = "/createMessPurchaseOrder", method = RequestMethod.GET)
	public String messPurchaseOrder(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		try {
			String messCommodityPurchaseOrderCode = inventoryService.getNextmessCommodityPurchaseOrderCode();
			List<Vendor> vendorList=inventoryService.commodityVendorList();
			model.addAttribute("vendorList", vendorList);
			
			List<Vendor> bankList=inventoryService.getBankDetailsList();
			model.addAttribute("bankList", bankList);
			
			List<Department> departmentList= academicsService.getAllDepartment();
			model.addAttribute("departmentList", departmentList);
			
			if(null != messCommodityPurchaseOrderCode && !messCommodityPurchaseOrderCode.trim().equalsIgnoreCase(""))
				model.addAttribute("messCommodityPurchaseOrderCode",messCommodityPurchaseOrderCode);
			
			List<Ledger>ledgerList = financeService.getLedgerList();//Added By Naimisha 03012017
			
			if (ledgerList != null && ledgerList.size() != 0) {
				model.addAttribute("ledgerList",ledgerList);
			}
			
			List<Tax> taxList = financeService.getTaxPercentages();
			if(null != taxList){
				model.addAttribute("taxList", taxList);
			}
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/messPurchaseOrder";
	}
	
	/**@author Saif.Ali
	 * DATE-10/07/2017
	 * Used to Submit the Mess Purchase order into the Database*/
	@RequestMapping(value = "/createMessPurchaseOrder", method = RequestMethod.POST)
	public String createMessPurchaseOrder(HttpServletRequest request,
											  HttpServletResponse response,
											  ModelMap model,
											  CommodityPurchaseOrder commodityPurchaseOrder,
											 @RequestParam(value = "commodityCode", required = false) String[] commodityCode,
											  @RequestParam(value = "purchaseRate", required = false) String[] purchaseRate,
											  @RequestParam(value = "quantity", required = false) String[] quantity,
											  @RequestParam(value = "discount", required = false) String[] discount,
											  @RequestParam(value = "total", required = false) String[] total,
											  /*@RequestParam(value = "expenceDescription", required = false) String[] expenceDesc,
											  @RequestParam(value = "expenceType", required = false) String[] paymentType,
											  @RequestParam(value = "expenceAmount", required = false) String[] expenceAmount,*/
											  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try {
			double totPaid=commodityPurchaseOrder.getAdvanceAmount();
			commodityPurchaseOrder.setAdvanceAmount(totPaid);
			commodityPurchaseOrder.setPendingAmount(commodityPurchaseOrder.getNetTotal()-commodityPurchaseOrder.getAdvanceAmount());
			
			commodityPurchaseOrder.setReceiveStatus("OPEN");
			if(commodityPurchaseOrder.getPendingAmount()<=0.0)
				commodityPurchaseOrder.setAmountStatus("CLOSED");
			else
				commodityPurchaseOrder.setAmountStatus("OPEN");
			
			commodityPurchaseOrder.setTotalQtyReceived(0.0);
			commodityPurchaseOrder.setOrderStatus("OPEN");
			double totalQty = 0.0;
			for(int i=0;i<quantity.length;i++){
				if(Double.parseDouble(quantity[i])!=0.0 && commodityCode[i].trim()!=""){
					System.out.println(quantity[i]);
					totalQty += Double.parseDouble(quantity[i]);
				}
			}
			commodityPurchaseOrder.setTotalQtyDeficit(totalQty);
			commodityPurchaseOrder.setTotalQtyOrdered(totalQty);
			
			List<CommodityPurchaseOrderDetails> commodityPurchaseOrderDetailsList=new ArrayList<CommodityPurchaseOrderDetails>();
			if(quantity!=null && quantity.length!=0){
				for(int i=0;i<quantity.length;i++){
					if(Double.parseDouble(quantity[i])!=0.0 && commodityCode[i].trim()!=""){
						CommodityPurchaseOrderDetails cpod=new CommodityPurchaseOrderDetails();
						cpod.setCommodity(commodityCode[i].trim().toUpperCase());
						cpod.setQtyOrdered(Double.parseDouble(quantity[i]));
						cpod.setRate(Double.parseDouble(purchaseRate[i]));
						cpod.setDiscount(Double.parseDouble(discount[i]));
						cpod.setAmount(Double.parseDouble(total[i]));
						cpod.setQtyDeficit(Double.parseDouble(quantity[i]));
						cpod.setQtyDefect(0.0);
						cpod.setQtyReceived(0.0);
						cpod.setReceiveStatus("OPEN");
						cpod.setCommodityPurchaseOrderCode(commodityPurchaseOrder.getPurchaseOrderCode());
						/*if(null != expenceDesc && expenceDesc.length !=0){
							cpod.setExpenceDesc(expenceDesc[i]);
							cpod.setPaymentType(paymentType[i]);
							cpod.setExpenceAmount(Double.parseDouble(expenceAmount[i]));
						}*/
						commodityPurchaseOrderDetailsList.add(cpod);
					}
				}
			}
			
			if(commodityPurchaseOrderDetailsList.size()!=0)
				commodityPurchaseOrder.setCommodityPurchaseOrderDetailsList(commodityPurchaseOrderDetailsList);
			else
				commodityPurchaseOrder.setReceiveStatus("CLOSED");
			
			List<CommodityPurchaseOrderDetails> serviceDetailsList=new ArrayList<CommodityPurchaseOrderDetails>();
			
			if((commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList()!=null && commodityPurchaseOrder.getCommodityPurchaseOrderDetailsList().size()!=0)
					|| (commodityPurchaseOrder.getServiceDetailsList()!=null && commodityPurchaseOrder.getServiceDetailsList().size()!=0)){
				
				status=inventoryService.createMessCommodityPurchaseOrder(commodityPurchaseOrder, sessionObject.getUserId());
			}else{
				logger.info("No Service Or Commodity Found In Purchase Order While Inserting, hence Not Inserted And Rejected");
			}
		} catch (Exception e) {
			logger.error("Exception In createCommodityPurchaseOrder() method of InventoryController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(status);
		}
		return viewMessPurchaseOrderList(request, response, model, "OPEN");
	}
	
	@RequestMapping(value = "/viewMessPurchaseOrderList", method = RequestMethod.GET)
	public String viewMessPurchaseOrderList(HttpServletRequest request,
											  HttpServletResponse response,
											  ModelMap model,
											  @RequestParam(required=false, value="status") String status) {
		try {
			//System.out.println("Status is="+status);
			List<CommodityPurchaseOrder> commodityPurchaseOrderList=inventoryService.messCommodityPurchaseOrderList(status);
			model.addAttribute("commodityPurchaseOrderList",commodityPurchaseOrderList);
			model.addAttribute("status",status);
			
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/messCommodityPurchaseOrderList";
	}
	
	/**@author Saif.Ali
	 * Date-11/07/2017
	 * Used to Receive the commodities ordered for the mess*/
	@RequestMapping(value = "/receiveMessCommodityOrder.html", method = RequestMethod.GET)
	public String receiveMessCommodityOrder(HttpServletRequest request,
									  HttpServletResponse response,
									  ModelMap model,
									  @RequestParam("orderID") String orderID) {
		try {
			CommodityPurchaseOrder commodityPurchaseOrder=inventoryService.messCommodityPurchaseOrderReceiveDetails(orderID);
			if(commodityPurchaseOrder!=null){
				model.addAttribute("commodityPurchaseOrder",commodityPurchaseOrder);
			}else{
			}
			List<Ledger>ledgerList = financeService.getLedgerList();//Added By naimisha.ghosh 03062017
			if (ledgerList != null && ledgerList.size() != 0) {
				model.addAttribute("ledgerList",ledgerList);
			}
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/messCommodityPurchaseOrderReceive";
	}
	
	/**@author Saif.Ali
	 * Make Mess commodity Receive
	 * Date-12/07/2017*/
	@RequestMapping(value = "/makeMessCommodityReceive", method = RequestMethod.POST)
	public String makeMessCommodityReceive(HttpServletRequest request,
									  HttpServletResponse response,
									  ModelMap model,
									  CommodityPurchaseOrder commodityPurchaseOrder,
									  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String receiveStatus="";
		String paymentStatus="";
		try {
			String commodities[]= request.getParameterValues("commodity");
			
			if(commodities!=null && commodities.length!=0){
				List<CommodityPurchaseOrderDetails> commodityPurchaseOrderDetailsList=new ArrayList<CommodityPurchaseOrderDetails>();
				double newTotalReceive=0.0;
				double newTotalRemeaning=0.0;
				for(int i=0;i<commodities.length;i++){
					CommodityPurchaseOrderDetails cpod = new CommodityPurchaseOrderDetails();
					
					double oldDamage=Double.parseDouble(request.getParameter(commodities[i]+"oldDamage"));
					double newDamage=Double.parseDouble(request.getParameter(commodities[i]+"damage"));
					double oldReceive=Double.parseDouble(request.getParameter(commodities[i]+"received"));
					double newReceive=Double.parseDouble(request.getParameter(commodities[i]+"receiving"));
					double receive=(newReceive+oldReceive)-newDamage;
					double increaseInStock=newReceive-newDamage;
					double damage=newDamage+oldDamage;
					double oldRemeaning=Double.parseDouble(request.getParameter(commodities[i]+"remeaning"));
					double remeaning=oldRemeaning-(newReceive-newDamage);
					
					newTotalReceive=newTotalReceive+receive;
					newTotalRemeaning=newTotalRemeaning+remeaning;
					
					cpod.setCommodity(commodities[i]);
					cpod.setQtyDefect(damage);
					cpod.setQtyDeficit(remeaning);
					cpod.setQtyReceived(receive);
					cpod.setIncreaseInStock(increaseInStock);
					cpod.setCommodityPurchaseOrderCode(commodityPurchaseOrder.getPurchaseOrderCode());
					
					commodityPurchaseOrderDetailsList.add(cpod);
				}
				
				if(commodityPurchaseOrderDetailsList.size()!=0){
					commodityPurchaseOrder.setCommodityPurchaseOrderDetailsList(commodityPurchaseOrderDetailsList);
					commodityPurchaseOrder.setTotalQtyDeficit(newTotalRemeaning);
					commodityPurchaseOrder.setTotalQtyReceived(newTotalReceive);
					if(newTotalRemeaning>0){
						commodityPurchaseOrder.setReceiveStatus("OPEN");
						commodityPurchaseOrder.setOrderStatus("OPEN");
					}else{
						commodityPurchaseOrder.setReceiveStatus("CLOSED");
						if(request.getParameter("amountStatus").equalsIgnoreCase("OPEN")){
							commodityPurchaseOrder.setOrderStatus("OPEN");
						}else{
							commodityPurchaseOrder.setOrderStatus("CLOSED");
						}
					}
				}
				receiveStatus = inventoryService.makeMessCommodityReceive(commodityPurchaseOrder, sessionObject.getUserId());
			}
			double paying=commodityPurchaseOrder.getPayAmount();
			double advance=commodityPurchaseOrder.getAdvanceAmount()+paying;
			commodityPurchaseOrder.setAdvanceAmount(advance);
			double pending=commodityPurchaseOrder.getPendingAmount()-paying;
			commodityPurchaseOrder.setPendingAmount(pending);
			
			if(commodityPurchaseOrder.getPendingAmount()<=0){
				commodityPurchaseOrder.setAmountStatus("CLOSED");
				if(commodityPurchaseOrder.getReceiveStatus().trim().equalsIgnoreCase("CLOSED")){
					
					commodityPurchaseOrder.setOrderStatus("CLOSED");
				}else{
					commodityPurchaseOrder.setOrderStatus("OPEN");
				}
			}else{
				commodityPurchaseOrder.setAmountStatus("OPEN");
				commodityPurchaseOrder.setOrderStatus("OPEN");
			}
			paymentStatus = inventoryService.makeMessCommodityPayment(commodityPurchaseOrder, sessionObject.getUserId());
		} catch (Exception e) {
			logger.error("Exception In addCommodity() method of InventoryController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(receiveStatus);
			logger.info(paymentStatus);
		}
		return viewMessPurchaseOrderList(request, response, model, "OPEN");
	}
	
	/* added by sourav.bhadra on 27062017
	 * to get department budget details */
	@RequestMapping(value = "/getDepartmentBudgetDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getDepartmentBudgetDetails(@RequestParam("departmentCode") String departmentCode) {
		String departmentBudgetDetails="";
		try {
			departmentBudgetDetails = inventoryService.getDepartmentBudgetDetails(departmentCode);
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(departmentBudgetDetails));
	}
	
	/* added by sourav.bhadra on 28-07-2017
	 * to get commodity unit for PO */
	@RequestMapping(value = "/getCommodityUnitForPO", method = RequestMethod.GET)
	public @ResponseBody
	String getCommodityUnitForPO(@RequestParam("commodity") String commodity) {
		String commodityUnit="";
		try {
			commodityUnit = inventoryService.getCommodityUnitForPO(commodity);
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(commodityUnit));
	}
	
	/* added by sourav.bhadra on 31-07-2017
	 * to get vendor's ledger for PO */
	@RequestMapping(value = "/getVendorsLedgerForCommodityPO", method = RequestMethod.GET)
	public @ResponseBody
	String getVendorsLedgerForCommodityPO(@RequestParam("vendorCode") String vendorCode) {
		String vendorLedger="";
		try {
			vendorLedger = inventoryService.getVendorsLedgerForCommodityPO(vendorCode); //System.out.println("LN961...Controller :: "+vendorLedger);
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(vendorLedger));
	}
	
	
	//Added by naimisha 24042018
	
	@RequestMapping(value = "/commodityRequisition", method = RequestMethod.GET)
	public String commodityRequisition(HttpServletRequest request,
										  HttpServletResponse response,
										  ModelMap model,
										  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			
				List<Department> departmentList= academicsService.getAllDepartment();
				model.addAttribute("departmentList", departmentList);
				String commodityRequisitionCode = inventoryService.getNextCommodityRequisionCode();
				
				model.addAttribute("commodityRequisitionCode",commodityRequisitionCode);
				
				List<Vendor> vendorList=inventoryService.commodityVendorList();
				model.addAttribute("vendorList", vendorList);
				
				List<FinancialYear> financialYearList = commonService.getFinancialYearList();
				model.addAttribute("financialYearList", financialYearList);
				
				
				//Added by naimisha 09052018
				String userId = sessionObject.getUserId();
				List<Task> taskList = inventoryService.getTaskNoListForAUser(userId);
				model.addAttribute("taskList", taskList);

		} catch (Exception e) {
			logger.error("Exception In commodityRequisition() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/commodityRequisition";
	}
	
	@RequestMapping(value = "/createCommodityRequisition", method = RequestMethod.POST)
	public String createCommodityRequisition(HttpServletRequest request,
											  HttpServletResponse response,
											  ModelMap model,
											  CommodityPurchaseOrder commodityPurchaseOrder,
											  @RequestParam(value = "commodityName", required = false) String[] commodityName,
											  @RequestParam(value = "purchaseRate", required = false) String[] purchaseRate,
											  @RequestParam(value = "quantity", required = false) String[] quantity,
											  @RequestParam(value = "total", required = false) String[] total,
											  @RequestParam(value = "commodityUnit", required = false) String[] commodityUnit,
											  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try {
				double totalQty = 0.0;
				double totalAmount = 0.0;
				for(int i=0;i<quantity.length;i++){
					if(Double.parseDouble(quantity[i])!=0.0 && commodityName[i].trim()!=""){
						System.out.println(quantity[i]);
						totalQty += Double.parseDouble(quantity[i]);
						totalAmount += Double.parseDouble(total[i]);
					}
				}
				commodityPurchaseOrder.setTotalQtyOrdered(totalQty);
				commodityPurchaseOrder.setAmount(totalAmount);
				
				
				List<CommodityPurchaseOrderDetails> commodityPurchaseOrderDetailsList=new ArrayList<CommodityPurchaseOrderDetails>();
				if(quantity!=null && quantity.length!=0){
					for(int i=0;i<quantity.length;i++){
						if(Double.parseDouble(quantity[i])!=0.0 && commodityName[i].trim()!=""){
							CommodityPurchaseOrderDetails cpod=new CommodityPurchaseOrderDetails();
							cpod.setCommodity(commodityName[i].trim().toUpperCase());
							cpod.setQtyOrdered(Double.parseDouble(quantity[i]));
							cpod.setRate(Double.parseDouble(purchaseRate[i]));
							cpod.setAmount(Double.parseDouble(total[i]));
							cpod.setPaymentType(commodityUnit[i]);
							cpod.setCommodityPurchaseOrderCode(commodityPurchaseOrder.getPurchaseOrderCode());
							
							commodityPurchaseOrderDetailsList.add(cpod);
						}
					}
				}
				
				if(commodityPurchaseOrderDetailsList.size()!=0){
					commodityPurchaseOrder.setCommodityPurchaseOrderDetailsList(commodityPurchaseOrderDetailsList);
				}
					
				
				
				
				status = inventoryService.createCommodityRequisition(commodityPurchaseOrder, sessionObject.getUserId());
				model.addAttribute("status", status);
		} catch (Exception e) {
			logger.error("Exception In createCommodityRequisition() method of InventoryController: Exception",e);
			e.printStackTrace();
		}finally{
			logger.info(status);
		}
		return commodityRequisitionList(request, response, model);
	}
	
	@RequestMapping(value = "/commodityRequisitionList", method = RequestMethod.GET)
	public String commodityRequisitionList(HttpServletRequest request,
											  HttpServletResponse response,
											  ModelMap model) {
		try {
			
			List<CommodityPurchaseOrder> commodityRequisitionList = inventoryService.commodityRequisitionList();
			model.addAttribute("commodityRequisitionList",commodityRequisitionList);
			//model.addAttribute("status",status);
			
		} catch (Exception e) {
			logger.error("Exception In commodityRequisitionList() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/commodityRequisitionList";
	}
	
	@RequestMapping(value = "/comodityRequisitionDetails", method = RequestMethod.GET)
	public String comodityRequisitionDetails(HttpServletRequest request,
											  HttpServletResponse response,
											  ModelMap model) {
		try {
				String requisitionCode = request.getParameter("requisitionCode");
				System.out.println("requisitionCode=="+requisitionCode);
				
				CommodityPurchaseOrder commodityRequisitionDetails = inventoryService.getCommodityRequisitionDetails(requisitionCode);
				
				//List<CommodityPurchaseOrder> commodityRequisitionList = inventoryService.commodityRequisitionList();
				model.addAttribute("commodityRequisitionDetails",commodityRequisitionDetails);
				//model.addAttribute("status",status);requisitionCode
			
		} catch (Exception e) {
			logger.error("Exception In comodityRequisitionDetails() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/commodityRequisitionDetails";
	}
	
	//Added By naimisha 27052018
	
	@RequestMapping(value = "/requisitionDetailsAgainstRequisitionCode", method = RequestMethod.GET)
	public @ResponseBody
	String requisitionDetailsAgainstRequisitionCode(@RequestParam("requisitionCode") String requisitionCode) {
		String requisitionDetails = "";
		String requisition = "";
		try {
			CommodityPurchaseOrder commodityRequisitionDetails = inventoryService.getCommodityRequisitionDetails(requisitionCode.trim());
			
			List<CommodityPurchaseOrderDetails>commodityRequisitionList = commodityRequisitionDetails.getCommodityPurchaseOrderDetailsList();
			
			for(CommodityPurchaseOrderDetails cpod : commodityRequisitionList){
				requisitionDetails = requisitionDetails + cpod.getCommodity()+"##"+cpod.getQtyOrdered()+"##"+cpod.getPaymentType()+"##"+cpod.getRate()+"##"+cpod.getAmount()+"*";
			}
			
			requisition = requisition + commodityRequisitionDetails.getDepartmentName()+"+-+"+commodityRequisitionDetails.getVendorName()+"+-+"+
						commodityRequisitionDetails.getTotalQtyOrdered()+"+-+"+commodityRequisitionDetails.getPayAmount()+"+-+"+requisitionDetails
						+"+-+"+commodityRequisitionDetails.getVendorCode();

			System.out.println(requisition);
			
		} catch (Exception e) {
			logger.error("Exception In requisitionDetailsAgainstRequisitionCode() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(requisition));
	}
	
	@RequestMapping(value = "/commodityReceivingList", method = RequestMethod.GET)
	public String commodityRequisitionList(HttpServletRequest request,
											  HttpServletResponse response,
											  ModelMap model,
											  @RequestParam("status") String status) {
		try {
			
			List<CommodityPurchaseOrder> commodityPurchaseOrderList=inventoryService.commodityPurchaseOrderList(status);
			model.addAttribute("commodityPurchaseOrderList",commodityPurchaseOrderList);
			model.addAttribute("status",status);
			
		} catch (Exception e) {
			logger.error("Exception In commodityReceivingList() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/commodityReceivingList";
	}
	
	
	//Added By Naimisha 03052018
	@RequestMapping(value = "/tenderWisePricing", method = RequestMethod.GET)
	public String tenderWisePricing(HttpServletRequest request,
										  HttpServletResponse response,
										  ModelMap model) {
		try {
			
			
				List<Commodity> commodityList=inventoryService.listCommodity();
				model.addAttribute("commodityList", commodityList);
				
				
				
				List<FinancialYear> financialYearList = commonService.getFinancialYearList();
				model.addAttribute("financialYearList", financialYearList);
			
		} catch (Exception e) {
			logger.error("Exception In tenderWisePricing() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return "inventory/tenderWisePricing";
	}
	
	@RequestMapping(value = "/commodityListMappedForAFinancialYear", method = RequestMethod.GET)
	public @ResponseBody
	String commodityListMappedForAFinancialYear(@RequestParam("financialYear") String financialYear) {
		String commodityString = "";
		try {
			List<Commodity>commodityList = inventoryService.commodityListMappedForAFinancialYear(financialYear);
			for(Commodity commodity : commodityList){
				commodityString = commodityString + commodity.getCommodityName()+"*";
			}
		} catch (Exception e) {
			logger.error("Exception In commodityListMappedForAFinancialYear() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(commodityString));
	}
	
	@RequestMapping(value = "/vendorListMappedForAFinancialYearAndCommodity", method = RequestMethod.GET)
	public @ResponseBody
	String getVendorListMappedForAFinancialYearAndCommodity(@RequestParam("financialYear") String financialYear,
															@RequestParam("commodityName") String commodityName) {
		String commodityVendorString = "";
		try {
				Commodity commodityObj = new Commodity();
				commodityObj.setCommodityName(commodityName);
				commodityObj.setDate(financialYear);
				
				List<Commodity>vendorListForCommodity = inventoryService.getVendorsListFromItemTenderPricing(commodityObj);
				if(null == vendorListForCommodity || vendorListForCommodity.size() ==0){
					List<Commodity>vendorAndItemRateList = inventoryService.getVendorListMappedForAFinancialYearAndCommodity(commodityObj);
					commodityVendorString = commodityVendorString + "radio"+"@";
					for(Commodity commodity : vendorAndItemRateList){
						commodityVendorString = commodityVendorString + commodity.getVendorCode()+"*"+commodity.getVendor()+"*"+
											commodity.getModelNo()+"*"+commodity.getSellingRate()+"#";
					}
				}else{
					commodityVendorString = commodityVendorString + "nonradio"+"@";
					for(Commodity commodity : vendorListForCommodity){
						commodityVendorString = commodityVendorString + commodity.getVendorCode()+"*"+commodity.getVendor()+"*"+
											commodity.getModelNo()+"*"+commodity.getSellingRate()+"#";
					}
				}
				
		} catch (Exception e) {
			logger.error("Exception In commodityListMappedForAFinancialYear() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(commodityVendorString));
	}
	
	//Added by naimisha 07052018
	
	@RequestMapping(value = "/tenderWisePricing", method = RequestMethod.POST)
	public String submitTenderWisePricing(HttpServletRequest request,
										  HttpServletResponse response,
										  ModelMap model,Vendor vendor,
										  @ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			
			vendor.setUpdatedBy(sessionObject.getUserId());
			String status = inventoryService.submitTenderWisePricing(vendor);
			String message = "";
			
			if(status.equalsIgnoreCase("success")){
				message = "Succesfullly Done";
			}else{
				message = "Failed To Insert";
			}
			model.addAttribute("status", status);
			model.addAttribute("message", message);
			
		} catch (Exception e) {
			logger.error("Exception In submitTenderWisePricing() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return tenderWisePricing(request,response,model);
	}
	
	@RequestMapping(value = "vendorCommodityListAccordingToTender", method = RequestMethod.GET)
	public @ResponseBody
	String vendorCommodityListAccordingToTender(@RequestParam("vendorCode") String vendorCode,
								@RequestParam("financialYear") String financialYear) {
		String vendorCommodityList = "";
		try {
			vendorCommodityList = inventoryService.vendorCommodityListAccordingToTender(vendorCode.trim(),financialYear.trim());
			//System.out.println(vendorCommodityList);
		} catch (Exception e) {
			logger.error("Exception In vendorCommodityListAccordingToTender() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(vendorCommodityList));
	}
	
	//Added by naimisha 09052018
	
	@RequestMapping(value = "/getTicketNoAgainstTaskCode", method = RequestMethod.GET)
	public @ResponseBody
	String getTicketNoAgainstTaskCode(@RequestParam("taskCode") String taskCode) {
		String ticketCode = "";
		try {
			Ticket ticket = inventoryService.getTicketNoAgainstTaskCode(taskCode);
			ticketCode = ticketCode + ticket.getTicketCode();
			
		} catch (Exception e) {
			logger.error("Exception In getTicketNoAgainstTaskCode() method of InventoryController: Exception",e);
			e.printStackTrace();
		}
		return (new Gson().toJson(ticketCode));
	}
}
