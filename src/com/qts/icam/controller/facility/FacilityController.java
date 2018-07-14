package com.qts.icam.controller.facility;

import java.util.ArrayList;
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
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Room;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.UpdateLog;
import com.qts.icam.model.facility.Facility;
import com.qts.icam.model.hostel.Hostel;
import com.qts.icam.model.hostel.HostelFacility;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.facility.FacilityService;

@Controller
@SessionAttributes("sessionObject")
public class FacilityController{
	public static Logger logger = Logger.getLogger(FacilityController.class);
	@Autowired
	FacilityService facilityService;
	
	/**Added by Saif 28-03-2018*/
	@Autowired
	CommonService commonService;
	/***/
	/**
	 * @author anup.roy
	 * this method is for display page of adding facility
	 * independent of any other module
	 * */
	
	@RequestMapping(value = "/createFacility", method = RequestMethod.GET)
	public ModelAndView createFacility(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String strView = null;
		try {
			List<SocialCategory> socialCategoryList = facilityService.getSocialCategoryList();
			if (socialCategoryList != null && socialCategoryList.size() != 0) {
				model.addAttribute("socialCategoryList", socialCategoryList);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("addHostelFacility() In FacilityController.java: NullPointerException",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addHostelFacility() In FacilityController.java: Exception",e);
		}
		strView = "facility/createFacility";
		return new ModelAndView(strView);
	}
	
	/**
	 * @author anup.roy
	 * this method is for add new facility */
	
	@RequestMapping(value = "/addFacility", method = RequestMethod.POST)
	public ModelAndView addFacility(HttpServletRequest request, HttpServletResponse response,
									ModelMap model, Facility facility,
									@RequestParam(required = false, value = "category") String[] category,
									@ModelAttribute("sessionObject") SessionObject sessionObject) 
	{
		logger.info("In addFacility() method of FacilityController");
		List<SocialCategory> socialCategoryList = new ArrayList<SocialCategory>();
		try 
		{
			if(null!=facility.getFacilityName() && facility.getFacilityName().trim().length()!=0)
			{
				facility.setFacilityName(facility.getFacilityName().trim().toUpperCase());
				facility.setFacilityDesc(facility.getFacilityDesc().trim());
				if (category != null && category.length != 0) 
				{
					//System.out.println("length:"+category.length);
					for (int i = 0; i < category.length; i++) 
					{
						double charge = Double.parseDouble(request.getParameter(category[i]));
						SocialCategory socialCategory = new SocialCategory();
						socialCategory.setSocialCategoryCode(category[i]);
						socialCategory.setAmount(charge);
						socialCategoryList.add(socialCategory);
					}
				}
				facility.setSocialCategoryList(socialCategoryList);
				facility.setUpdatedBy(sessionObject.getUserId());
				facilityService.saveFacilities(facility);
				/**Added by @author Saif.Ali
				 * Date-28-03-2018*/
				 String facilityName = request.getParameter("facilityName");
				 String facilityDescription = request.getParameter("facilityDesc");
				 String facilityPaymentStatus = request.getParameter("ispaid");
				 String description = "" ;
				 
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("ENTER FACILITY DETAILS");
				updateLog.setInsertUpdate("INSERT");
				updateLog.setModule("FACILITY MANAGEMENT");
				updateLog.setUpdatedFor("Facility Name :: " + facilityName + " with facility description :: " + facilityDescription);
				if(facilityName != "")
				{
					description= description + ("Facility Name :: " + facilityName + " ; ");
				}
				if(facilityDescription != "")
				{
					description= description + ("Facility Description :: " + facilityDescription + " ; ");
				}
				if(facilityPaymentStatus != "")
				{
					if(facilityPaymentStatus.equalsIgnoreCase("true"))
					{
						if (category != null && category.length != 0) 
						{
							for (int i = 0; i < category.length; i++) 
							{
								description= description + (" Category :: " + category[i] + " ; ");
								double charge = Double.parseDouble(request.getParameter(category[i]));
								description= description + (" Charge :: " + charge + " ; ");
							}
						}
						description= description + ("Facility Payment Status :: " + " paid" + " ; ");
					}
					if(facilityPaymentStatus.equalsIgnoreCase("false"))
					{
						description= description + ("Facility Payment Status :: " + " unpaid" + " ; ");
					}
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
					
				System.out.println("LN 138 :: FacilityController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
				/***/
			}
		}
		catch (ArrayIndexOutOfBoundsException e) 
		{
			e.printStackTrace();
			logger.error("addFacility() In FacilityController.java: ArrayIndexOutOfBoundsException",e);
		} catch (NullPointerException e) 
		{
			e.printStackTrace();
			logger.error("addFacility() In FacilityController.java: NullPointerException",e);
		} catch (Exception e) 
		{
			e.printStackTrace();
			logger.error("addFacility() In FacilityController.java: Exception",e);
		}
		return listFacility(request,response,model);
	}
	
	/**
	 * @author anup.roy
	 * this method returns list page of facility
	 * */
	
	@RequestMapping(value = "/listFacility", method = RequestMethod.GET)
	public ModelAndView listFacility(HttpServletRequest request, HttpServletResponse response, ModelMap model) 
	{
		logger.info("In listFacility() method of FacilityController: ");
		ModelAndView  mav = new ModelAndView("facility/listFacility");
		try 
		{
			List<Facility> facilityList = facilityService.getAllFacilities();
			if(null!= facilityList && facilityList.size()!=0)
			{
				model.addAttribute("facilityList",facilityList);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			logger.error("Exception in listBook() method Of LibraryController",	e);
		}
		return mav;
	}
	
	/**
	 * @author anup.roy
	 * this method is for showing edit page
	 * */
	
	@RequestMapping(value = "/editFacility", method = RequestMethod.POST)
	public ModelAndView editFacility(HttpServletRequest request,HttpServletResponse response, ModelMap model,
									@RequestParam("facilityCode") String facilityCode) 
	{
		logger.info("In editFacility() method of FacilityController");
		try 
		{
			Facility facilityDetails = facilityService.getFacilityDetails(facilityCode);
			List<SocialCategory> socialCategoryList = facilityService.getSocialCategoryList();
			if (facilityDetails != null) 
			{
				model.addAttribute("facilityDetails", facilityDetails);
				model.addAttribute("socialCategoryList", socialCategoryList);
			}
		} 
		catch (NullPointerException e) 
		{
			e.printStackTrace();
			logger.error("editHostelFacility() In HostelController.java: NullPointerException",e);
		}
		return new ModelAndView("facility/editFacilityDetails");
	}
	
	/**
	 * @author anup.roy
	 * this method is for add edited facility */
	
	@RequestMapping(value = "/updateFacilityDetails", method = RequestMethod.POST)
	public ModelAndView updateFacilityDetails(HttpServletRequest request, HttpServletResponse response,
												ModelMap model, Facility facility,
												@RequestParam(required = false, value = "category") String[] category,
												@ModelAttribute("sessionObject") SessionObject sessionObject) 
	{
		logger.info("In updateFacilityDetails() method of FacilityController");
		List<SocialCategory> socialCategoryList = new ArrayList<SocialCategory>();
		try 
		{
			if(null!=facility.getFacilityName() && facility.getFacilityName().trim().length()!=0)
			{
				facility.setFacilityName(facility.getFacilityName().trim().toUpperCase());
				facility.setFacilityDesc(facility.getFacilityDesc().trim());
				if (category != null && category.length != 0) 
				{
					//System.out.println("length:"+category.length);
					for (int i = 0; i < category.length; i++) 
					{
						double charge = Double.parseDouble(request.getParameter(category[i]));
						SocialCategory socialCategory = new SocialCategory();
						socialCategory.setSocialCategoryCode(category[i]);
						socialCategory.setAmount(charge);
						//System.out.println("categoryCode:"+socialCategory.getSocialCategoryCode()+"\tamount:"+socialCategory.getAmount());
						socialCategoryList.add(socialCategory);
					}
				}
				facility.setSocialCategoryList(socialCategoryList);
				facility.setUpdatedBy(sessionObject.getUserId());
				facilityService.updateFacilityDetails(facility);
				/**Added by @author Saif.Ali
				 * Date-13-03-2018*/
				String oldFacilityName = request.getParameter("oldFacilityName");
				String oldFacilityDesc = request.getParameter("oldFacilityDesc");
				String oldFacilityStatus = request.getParameter("oldFacilityStatus");
				String newFacilityName = request.getParameter("facilityName");
				String newFacilityDesc = request.getParameter("facilityDesc");
				String newFacilityStatus = request.getParameter("ispaid");
				String description = "";
				
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT FACILITY DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("FACILITY MANAGEMENT");
				updateLog.setUpdatedFor("Facility Name ::" + newFacilityName + " with Facility Description :: " + newFacilityDesc);
				if(!(oldFacilityName.equalsIgnoreCase(newFacilityName)))
				{
					description= description + ("Facility Name ::" + oldFacilityName + " is now updated to new Facility Name :: " + newFacilityName + " ; ");
				}
				if(!(oldFacilityDesc.equalsIgnoreCase(newFacilityDesc)))
				{
					description= description + ("Facility Description ::" + oldFacilityDesc + " is now updated to new Facility Description :: " + newFacilityDesc + " ; ");
				}
				if(!(oldFacilityStatus.equalsIgnoreCase(newFacilityStatus)))
				{
					if(newFacilityStatus.equalsIgnoreCase("false"))
					{
						description= description + ("Facility Status ::" + oldFacilityStatus + " is now updated to new Facility Status :: " + " Unpaid" + " ; ");
					}
					if(newFacilityStatus.equalsIgnoreCase("true"))
					{
						if (category != null && category.length != 0) 
						{
							for (int i = 0; i < category.length; i++) 
							{
								double charge = Double.parseDouble(request.getParameter(category[i]));
								description= description + (" Category :: " + category[i] + " Charge :: " + charge + " ; ");
							}
						}
						description= description + ("Facility Payment Status :: " + " paid" + " ; ");
					}
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
					
				System.out.println("LN 307 :: FacilityController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
				
				/***/
			}
		}
		catch (ArrayIndexOutOfBoundsException e) 
		{
			e.printStackTrace();
			logger.error("In updateFacilityDetails() In FacilityController.java: ArrayIndexOutOfBoundsException",e);
		} catch (NullPointerException e) 
		{
			e.printStackTrace();
			logger.error("In updateFacilityDetails() In FacilityController.java: NullPointerException",e);
		} catch (Exception e) 
		{
			e.printStackTrace();
			logger.error("In updateFacilityDetails() In FacilityController.java: Exception",e);
		}
		return listFacility(request,response,model);
	}
	
	/**
	 * @author anup.roy
	 * this method is for the page of check the availablity status of room and allocate to student
	 * **/
	
	@RequestMapping(value = "/assignVenueToResource" , method = RequestMethod.GET)
	public ModelAndView assignVenueToResource(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		logger.info("In assignVenueToResource() method of FacilityController");
		try {
			List<Venue> venueList = facilityService.getVenueList();
			model.addAttribute("venueList", venueList);
			List<Resource> userIdList = facilityService.getResourceIdList();
			model.addAttribute("userIdList", userIdList);
		} catch (NullPointerException e) {
			logger.error("assignVenueToResource() In FacilityController.java: NullPointerException",e);
			e.printStackTrace();
		}
		catch (Exception e) {
			logger.error("assignVenueToResource() In FacilityController.java: NullPointerException",e);
			e.printStackTrace();
		}
		return new ModelAndView("facility/assignVenueToResource");
	}
	
	/**
	 * @author anup.roy
	 * this method is to get all blocks available in one venue*/
	
	@ResponseBody
	@RequestMapping(value = "/getBlocksOfVenue", method = RequestMethod.GET)
	public String getBlocksOfVenue(HttpServletRequest request, HttpServletResponse response, ModelMap model, 
											@RequestParam("venueName") String venueName) {
		logger.info("In getBlocksOfVenue() method of FacilityController: ");
		String output = null;
		try {
			output = facilityService.getBlocksOfVenue(venueName);
		} catch (Exception e) {
			logger.error("Error in FacilityController getBlocksOfVenue() method for Exception: ", e);
			e.printStackTrace();
		}
		return (new Gson().toJson(output));
	}
	
	/**
	 * @author anup.roy
	 * this method is to get all available rooms of any block
	 * **/
	
	@ResponseBody
	@RequestMapping(value = "/getAvailableRoomOfBlock", method = RequestMethod.GET)
	public String getAvailableRoomOfBlock(HttpServletRequest request, HttpServletResponse response, ModelMap model, 
											@RequestParam("block") String block) {
		logger.info("In getAvailableRoomOfBlock() method of FacilityController: ");
		String output = null;
		try {
			output = facilityService.getAvailableRoomOfBlock(block);
		} catch (Exception e) {
			logger.error("Error in FacilityController getAvailableRoomOfBlock() method for Exception: ", e);
			e.printStackTrace();
		}
		return (new Gson().toJson(output));
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting bed count (vacant and allocated) of any room*/
	
	@ResponseBody
	@RequestMapping(value = "/getAvailableBedsInRoom", method = RequestMethod.GET)
	public String getAvailableBedsInRoom(HttpServletRequest request, HttpServletResponse response, ModelMap model, 
											@RequestParam("roomNo") String roomNo) {
		logger.info("In getAvailableBedsInRoom() method of FacilityController: ");
		String output = null;
		try {
			output = facilityService.getAvailableBedsInRoom(roomNo);
		} catch (Exception e) {
			logger.error("Error in FacilityController getAvailableBedsInRoom() method for Exception: ", e);
			e.printStackTrace();
		}
		return (new Gson().toJson(output));
	}
	
	/**
	 * @author anup.roy
	 * this method will get name, standarad, section of any userId
	 * **/
	
	@RequestMapping(value = "/getResourceDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getResourceDetails(@RequestParam("userId") String userId) {
		logger.info("In getResourceDetails() method of FacilityController");
		String resourceDetails = "";
		try {
			Resource resource = facilityService.getResourceDetails(userId);
			/*resourceDetails = resourceDetails + resource.getFirstName() + " "+ resource.getMiddleName() + " " + resource.getLastName()+ "~" 
							+ resource.getKlass() + "~" 
							+ resource.getSection();*/
			resourceDetails = resource.getResourceName();
		} catch (NullPointerException e) {
			logger.error("getResourceDetails() In FacilityController.java: NullPointerException"+ e);
			e.printStackTrace();
		}catch(Exception e){
			logger.error("getResourceDetails() In FacilityController.java: Exception"+ e);
			e.printStackTrace();
		 }
		return (new Gson().toJson(resourceDetails));
	}
	
	/**
	 * @author anup.roy
	 * this method is for assign venue to resource
	 * **/
	
	@RequestMapping(value = "/submitAssignedVenueToResource", method = RequestMethod.POST)
	public ModelAndView submitAssignedVenueToResource(HttpServletRequest request, HttpServletResponse response,Venue venue,
													ModelMap model, Resource resource, Room room, @ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("In submitAssignedVenueToResource() method of FacilityController");
		try {
			venue.setResource(resource);
			venue.setRoom(room);
			venue.setUpdatedBy(sessionObject.getUserId());
			facilityService.submitAssignedVenueToResource(venue);
			/**Added by @author Saif.Ali
			 * Date-28-03-2018*/
			String venueName = request.getParameter("venueName");
			String block = request.getParameter("block");
			String roomNo = request.getParameter("roomNo");
			String userId = request.getParameter("userId");
			String resourceName = request.getParameter("resourceName");
			
			UpdateLog updateLog=new UpdateLog();
			updateLog.setUpdatedByUserId(sessionObject.getUserId());
			updateLog.setFunctionality("VENUE RESOURCE MAPPING");
			updateLog.setInsertUpdate("INSERT");
			updateLog.setModule("FACILITY MANAGEMENT");
			updateLog.setUpdatedFor("User Id :: " + userId + " with Name :: " + resourceName);
			updateLog.setDescription("User Id :: " + userId + " with Name :: " + resourceName + " mapped to venue name :: " + venueName + " block :: " + block
					+ " room number :: " + roomNo);
			String response_update=commonService.insertIntoActivityLog(updateLog);
				
			System.out.println("LN 3178 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
					":: Functonality ::"+ updateLog.getFunctionality() + 
					":: Module ::"+updateLog.getModule() + 
					":: Updated For ::"+ updateLog.getUpdatedFor() +
					":: Description :: "+updateLog.getDescription() +
					":: Response :: " + response_update +
					":: Insert/Update :: " + updateLog.getInsertUpdate());
			/***/
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("submitAssignedVenueToResource() In FacilityController.java: NullPointerException", e);
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			logger.error("submitAssignedVenueToResource() In FacilityController.java: Exception", e);
		 }
		return assignVenueToResource(request,response,model);
	}
	
	/**
	 * @author anup.roy
	 * this method is for returning the list page of assigned venue to resources
	 * **/
	
	@RequestMapping(value = "/listVenuesAssignedToResource", method = RequestMethod.GET)
	public ModelAndView listVenuesAssignedToResource(HttpServletRequest request,
										HttpServletResponse response,
										ModelMap model
										) {
		logger.info("In listStudentGET() method of HostelController");
		ModelAndView mav = new ModelAndView("facility/listVenuesAssignedToResource");
		try {
			List<Venue> venueList = facilityService.getListVenuesAssignedToResource();
			if (venueList != null && venueList.size() != 0) {
				model.addAttribute("venueList", venueList);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("listStudentGET() In HostelController.java: Exception"+ e);
		}
		return mav;
	}
	
	/**
	 * @author anup.roy
	 * this method is for de allocating assigned venue to any resource**/
	
	@RequestMapping(value = "/deAllocateVenueFromResource", method = RequestMethod.GET)
	public ModelAndView deAllocateVenueFromResource(HttpServletRequest request,
											HttpServletResponse response,ModelMap model,
											@ModelAttribute("sessionObject") SessionObject sessionObject
											) {
		logger.info("In deAllocateVenueFromResource() method of FacilityController");
		try {
			String userId = request.getParameter("userId");
			//System.out.println("user::"+userId);
			String venueCode = request.getParameter("venueName");
			//System.out.println("venue:"+venueCode);
			Venue venue = new Venue();
			Resource resource = new Resource();
			resource.setUserId(userId);
			venue.setResource(resource);
			venue.setVenueCode(venueCode);
			venue.setUpdatedBy(sessionObject.getUserId());
			facilityService.deAllocateVenueFromResource(venue);
		}catch(NullPointerException e){
			logger.error("deAllocateVenueFromResource() In FacilityController.java: NullPointerException"+ e);
			e.printStackTrace();
		}
		 catch (Exception e) {
			logger.error("deAllocateVenueFromResource() In FacilityController.java: Exception"+ e);
			e.printStackTrace();
		}
		return listVenuesAssignedToResource(request,response,model);
	}
	
	/**
	 * @author anup.roy
	 * this method returns list page of facility for deactivate
	 * */
	
	@RequestMapping(value = "/deactivateFacilty", method = RequestMethod.GET)
	public ModelAndView deactivateFacilty(HttpServletRequest request, HttpServletResponse response, ModelMap model) 
	{
		logger.info("In deactivateFacilty() method of FacilityController: ");
		ModelAndView  mav = new ModelAndView("facility/facilityListToDeActivate");
		try 
		{
			List<Facility> facilityList = facilityService.getAllFacilities();
			if(null!= facilityList && facilityList.size()!=0)
			{
				model.addAttribute("facilityList",facilityList);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			logger.error("Exception in deactivateFacilty() method Of FacilityController",	e);
		}
		return mav;
	}
	
	/**
	 * @author anup.roy
	 * this method is for de activate facility*/
	
	@RequestMapping(value = "/facilityDeactivation", method = RequestMethod.GET)
	public ModelAndView facilityDeactivation(HttpServletRequest request,
											HttpServletResponse response,ModelMap model,
											@ModelAttribute("sessionObject") SessionObject sessionObject/*,
											@RequestParam("oldFacilityName") String oldFacilityName,
											@RequestParam("oldFacilityDesc") String oldFacilityDesc,
											@RequestParam("oldFacilityStatus") String oldFacilityStatus*/
											) {
		logger.info("In facilityDeactivation() method of FacilityController");
		try {
			String facilityCode = request.getParameter("facilityCode");
			facilityService.facilityDeactivation(facilityCode);
			/**Added by @author Saif.Ali
			 * Date-28-03-2018*/
			String oldFacilityName = request.getParameter("oldFacilityName");
			String oldFacilityDesc = request.getParameter("oldFacilityDesc");
			String oldFacilityStatus = request.getParameter("oldFacilityStatus");
			
			UpdateLog updateLog=new UpdateLog();
			updateLog.setUpdatedByUserId(sessionObject.getUserId());
			updateLog.setFunctionality("DE-ACTIVATE FACILITY");
			updateLog.setInsertUpdate("UPDATE");
			updateLog.setModule("FACILITY MANAGEMENT");
			updateLog.setUpdatedFor("Facility Code :: " + facilityCode + " of Facility Name :: " + oldFacilityName);
			updateLog.setDescription("Facility Code :: " + facilityCode + " of Facility Name :: " + oldFacilityName + " of Facility Description :: " + oldFacilityDesc
					+ " of Facility status :: " + oldFacilityStatus + " is de-activated");
			String response_update=commonService.insertIntoActivityLog(updateLog);
				
			System.out.println("LN 577 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
					":: Functonality ::"+ updateLog.getFunctionality() + 
					":: Module ::"+updateLog.getModule() + 
					":: Updated For ::"+ updateLog.getUpdatedFor() +
					":: Description :: "+updateLog.getDescription() +
					":: Response :: " + response_update +
					":: Insert/Update :: " + updateLog.getInsertUpdate());
			/***/
		}catch(NullPointerException e){
			logger.error("facilityDeactivation() In FacilityController.java: NullPointerException"+ e);
			e.printStackTrace();
		}
		 catch (Exception e) {
			logger.error("facilityDeactivation() In FacilityController.java: Exception"+ e);
			e.printStackTrace();
		}
		return deactivateFacilty(request,response,model);
	}
}	
