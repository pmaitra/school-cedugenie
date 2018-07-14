package com.qts.icam.controller.tender;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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

import com.google.gson.Gson;
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.RepositoryStructure;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.model.tender.Tender;
import com.qts.icam.model.tender.TenderCategory;
import com.qts.icam.model.tender.TenderType;
import com.qts.icam.service.academics.AcademicsService;
import com.qts.icam.service.administrator.AdministratorService;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.inventory.InventoryService;
import com.qts.icam.service.tender.TenderService;

@Controller
@SessionAttributes("sessionObject")
public class TenderController {
	
	@Autowired
	TenderService tenderService;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	AcademicsService academicService;
	
	@Autowired
	InventoryService inventoryService;
	
	@Autowired
	AdministratorService administratorService;
	
	@Value("${tenderAttachment.path}")
	private String tenderAttachmentPath;
	
	public static Logger logger = Logger.getLogger(TenderController.class);

	
	@RequestMapping(value = "/getTenderForm", method = RequestMethod.GET)
	public String getTenderForm(HttpServletRequest request,
			HttpServletResponse response, 
			@ModelAttribute("sessionObject") SessionObject sessionObject,
			ModelMap model) {
		
		try {
			logger.info("In getTenderForm() method of TenderController");
			
			//Get All departments
			List <Department> departmentList = academicService.getAllDepartment();
			model.addAttribute("departmentList", departmentList);
			
			//Get All Tender Type
			List <TenderType> tenderTypeList = tenderService.getAllTenderType();
			model.addAttribute("tenderTypeList", tenderTypeList);
			
			//Get All Tender Category
			List <TenderCategory> tenderCategoryList = tenderService.getAllTenderCategory();
			model.addAttribute("tenderCategoryList", tenderCategoryList);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getTenderForm() In TenderController.java: Exception"+ e);
		}
		return "tender/tenderForm";
	}
	
	@RequestMapping(value = "/getAllUserId", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	String getAllUserId() {
		System.out.println("within function");
		List<String> userIdFromResource = new ArrayList<>();
		try {
			logger.info("getAllUserId()In TenderController.java");
			List<Resource> resourceList = commonService.getStaffUserIdList();
			for(Resource resource : resourceList){
				userIdFromResource.add(resource.getUserId());
			}
		} catch (Exception e) {
			logger.error("getAllUserId() In TenderController.java: Exception"
					+ e);
		}
		return (new Gson().toJson(userIdFromResource));
	}

	@RequestMapping(value = "/getAllSubCategory", method = RequestMethod.GET)
	public @ResponseBody
	String getAllSubCategory(@RequestParam ("tenderCategory") String tenderCategory) {
		System.out.println("within function");
		String concatString = "";
		try {
			logger.info("getAllSubCategory()In TenderController.java");
			List<TenderCategory> tenderSubCategoryList = tenderService.getAllTenderSubCategory(tenderCategory);
			for(TenderCategory tenderSubCategory : tenderSubCategoryList){
				concatString = concatString + tenderSubCategory.getTenderCategoryCode() + "*" + tenderSubCategory.getTenderCategoryName() + "#";
			}
		} catch (Exception e) {
			logger.error("getAllSubCategory() In TenderController.java: Exception"
					+ e);
		}
		return (new Gson().toJson(concatString));
	}

	@RequestMapping(value = "/getAllCommodityName", method = RequestMethod.GET)
	public @ResponseBody
	String getAllCommodityName(@RequestParam ("tenderCategory") String tenderCategory, 
							@RequestParam ("tenderSubCategory") String tenderSubCategory) {
		System.out.println("In getAllCommodityName");
		String concatString = "";
		try {
			logger.info("getAllCommodity()In TenderController.java");
			
			//if TC-1 and TC-3, then Library and Goods
			
			if (tenderCategory.equalsIgnoreCase("TC-1") && tenderSubCategory.equalsIgnoreCase("TC-3")){
				//Then we should be talking to the LIBRARY DB and get the BOOKS details
			}
			
			//If TC-2 and TC-6, then commodity and Goods
			if (tenderCategory.equalsIgnoreCase("TC-2") && tenderSubCategory.equalsIgnoreCase("TC-6")){
				//Then we should be talking to the COMMODITY DB and get the GOODS details
			
				List<Commodity> commodityList = inventoryService.getAllCommodityName();
				for(Commodity commodity : commodityList){
					concatString = concatString + commodity.getCommodityCode() + "*" + commodity.getCommodityName() + "#";
				}
			}
		} catch (Exception e) {
			logger.error("getAllCommodityName() In TenderController.java: Exception"
					+ e);
		}
		return (new Gson().toJson(concatString));
	}

	@RequestMapping(value = "/getCommodityUnit", method = RequestMethod.GET)
	public @ResponseBody
	String getCommodityUnit(@RequestParam ("commodityName") String commodityName) {
		String commodityUnit = "";
		try {
			logger.info("getCommodityUnit() In TenderController.java");
			
			commodityUnit = inventoryService.getCommodityUnit(commodityName);
		} catch (Exception e) {
			logger.error("getCommodityUnit() In TenderController.java: Exception"
					+ e);
		}
		return commodityUnit;
	}

	@RequestMapping(value = "/submitTender", method = RequestMethod.POST)
	public String submitTender(HttpServletRequest request,
										   HttpServletResponse response, 
										   ModelMap model, 										   
										   Tender tender,
										   @ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("In submitTender() method of TenderController");	
			tender.setUpdatedBy(sessionObject.getUserId());
			
			String[] commodityName = request.getParameterValues("commodityName");
			String[] unit = request.getParameterValues("unit");
			
			
			String[] quantity = request.getParameterValues("quantity");
			
			List<Commodity>commodityList = new ArrayList<>();
			
			if(null != commodityName){
				
					for(int j = 0;j<commodityName.length;j++){
						
						Commodity commodity = new Commodity();
						commodity.setCommodityName(commodityName[j]);
						commodity.setUnit(unit[j]);
						commodity.setQuantity(quantity[j]);
						commodityList.add(commodity);
					}
					
					tender.setCommodityList(commodityList);
			}
			
			String insertStaus = tenderService.submitTenderForm(tender);
			
			
			
			if(tender != null){
				/***new code added for save this edited file into external repository**/
				if(insertStaus.equalsIgnoreCase("success")){
					RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
					String repository = repositoryStructure.getRepositoryPathName();
					File directory = new File(repository);
					boolean isExists = directory.exists();
					if(isExists == true){
						
							Attachment attachment = new Attachment();
							attachment.setStorageRootPath(repository);
							attachment.setFolderName(tenderAttachmentPath);					
							logger.info(attachment.getStorageRootPath()+":"+attachment.getFolderName());					
							if(tender.getUploadFile()!=null){ 
								tender.getUploadFile().setAttachment(attachment);
								tenderService.tenderDocumentUpload(tender);
							}
						
						
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("submitTender() In TenderController.java: Exception"+ e);
		}
		return getTenderForm(request, response, sessionObject, model);
	}
	
}
