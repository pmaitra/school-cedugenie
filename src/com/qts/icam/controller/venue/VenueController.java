package com.qts.icam.controller.venue;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
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

import com.google.gson.Gson;
import com.qts.icam.controller.ticket.TicketController;
import com.qts.icam.model.academics.StudentResult;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.model.common.Country;
import com.qts.icam.model.common.HostelType;
import com.qts.icam.model.common.Location;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.State;
import com.qts.icam.model.common.UpdateLog;
import com.qts.icam.model.facility.Facility;
import com.qts.icam.model.hostel.Hostel;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.hostel.HostelService;
import com.qts.icam.service.venue.VenueService;
import com.qts.icam.utility.customexception.CustomException;

@Controller
@SessionAttributes("sessionObject")
public class VenueController {
	
	
	@Autowired
	VenueService venueService;
	
	@Autowired
	CommonService commonService;
	@Autowired
	HostelService hostelService;
	
	@Autowired
	private ServletContext servletContext;
	
	public static Logger logger = Logger.getLogger(TicketController.class);
	
	/*
	 * @author naimisha.ghosh
	 * Opens page to create Zone for venue
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getZone", method = RequestMethod.GET)
	public String getZone(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "venue/createZone";
		try {
			logger.info("Inside Method getStandard-GET of AcademicsController");
			List<Venue> zoneList = venueService.getZoneList();
			model.addAttribute("zoneList", zoneList);
			
		} catch (Exception ce){
			logger.error("Exception in method createZone-GET of VenueController", ce);
		}
		return strView;
	}
	
	@RequestMapping(value = "/createZone", method = RequestMethod.POST)
	public String createZone(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Venue venue,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		//String strView = "venue/createZone";
		try {
			logger.info("Inside Method getStandard-GET of VenueController");
			venue.setZoneName(venue.getZoneName().trim().toUpperCase());
			venue.setZoneDesc(venue.getZoneDesc().trim());
			venue.setUpdatedBy(sessionObject.getUserId());		
			String updateStatus = venueService.addZone(venue);
			model.addAttribute("updateStatus", updateStatus);
			
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method createZone-GET of VenueController", ce);
		}
		return getZone(request,response,model);
	}
	@RequestMapping(value = "/editZone", method = RequestMethod.POST)
	public String editZone(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		//String strView = "venue/createZone";
		try {
			logger.info("Inside Method getStandard-GET of VenueController");
			
			Venue venue = new Venue();
			String saveId=request.getParameter("saveId");
			//System.out.println(saveId);				
					
			venue.setZoneCode(request.getParameter("zoneCode"+saveId).trim());
			venue.setZoneName(request.getParameter("zoneName"+saveId).trim().toUpperCase());
			venue.setZoneDesc(request.getParameter("zoneDesc"+saveId).trim());
			venue.setUpdatedBy(sessionObject.getUserId());
			String updateStatus = venueService.editZone(venue);
			model.addAttribute("updateStatus", updateStatus);
			
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method createZone-GET of VenueController", ce);
		}
		return getZone(request,response,model);
	}
	
	@RequestMapping(value = "/getLocation", method = RequestMethod.GET)
	public String getLocation(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "venue/createLocation";
		try {
			logger.info("Inside Method getStandard-GET of AcademicsController");
			List<Venue> zoneList = venueService.getZoneList();
			model.addAttribute("zoneList", zoneList);
			List<Country>countryList = commonService.getCountryList();
			List<Location>locationList = venueService.getLocationList();
			//System.out.println("locationList size==="+locationList.size());
			model.addAttribute("locationList", locationList);
			model.addAttribute("countryList", countryList);
		} catch (Exception ce){
			logger.error("Exception in method createZone-GET of VenueController", ce);
		}
		return strView;
	}
	
	@RequestMapping(value = "/getStatesAgainstZone", method = RequestMethod.GET)
	public @ResponseBody
	String getStatesAgainstZone(@RequestParam("zone") String zone) {
		logger.info("Inside Method getStudentsAndMarksForUserDefinedExams-GET of VenueController");
		String state = "";
		try {
			Venue venue = new Venue();
			venue.setZoneCode(zone);
			List<State> stateList = venueService.getStateListAgainstZone(venue);
			//System.out.println("stateList size=="+stateList.size());
			for(State st :stateList){
				state =state+","+ st.getStateCode()+":"+st.getStateName();
			}
			
		} catch (Exception ce) {
			ce.printStackTrace();
			logger.error("Exception in method getStudentsAndMarksForUserDefinedExams-GET of AcademicsController", ce);
		}		
		return (new Gson().toJson(state));
	}
	
	/**
	 * modified by naimisha.ghosh
	 * changes taken on  13042017**/
	
	@RequestMapping(value = "/createLocation", method = RequestMethod.POST)
	public String createLocation(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Location location,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		//String strView = "venue/createZone";
		try {
			logger.info("Inside Method getStandard-GET of VenueController");
			location.setLocationName(location.getLocationName().trim().toUpperCase());
			location.setLocationDesc(location.getLocationDesc().trim().toUpperCase());
			location.setPin(location.getPin().trim());
			location.setArea(location.getArea().trim().toUpperCase());
			location.setCity(location.getCity().trim().toUpperCase());
			location.setState(location.getState().trim());
			//location.setCity(location.getCountry().trim());
			location.setZone(location.getZone().trim());
			location.setUpdatedBy(sessionObject.getUserId());		
			String updateStatus = venueService.addLocation(location);
			String msg = null;
			if(updateStatus.equalsIgnoreCase("success")){
				msg = "Location Created Successfully";
			}else{
				msg = "Creation Failed";
			}
			model.addAttribute("updateStatus", updateStatus);
			model.addAttribute("msg", msg);
			/**Added by @author Saif.Ali
			 * Date-28-03-2018*/
			String locationName = request.getParameter("locationName");
			String locationDesc = request.getParameter("locationDesc");
			String area = request.getParameter("area");
			String zone = request.getParameter("zone");
			String country = request.getParameter("country");
			String state = request.getParameter("state");
			String city = request.getParameter("city");
			String pin = request.getParameter("pin");
						
			if(updateStatus.equalsIgnoreCase("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("ENTER LOCATION DETAILS");
				updateLog.setInsertUpdate("INSERT");
				updateLog.setModule("FACILITY MANAGEMENT");
				updateLog.setUpdatedFor("Location Name"+ locationName);
				updateLog.setDescription("Location Name"+ locationName + " Location Description" + locationDesc + " Location Area" + area
						+ " Zone :: " + zone + " Country :: " + country + " State :: " + state + " City :: " + city + " Pin :: " + pin + " ; ");
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 214 :: VenueController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method createZone-GET of VenueController", ce);
		}
		return getLocation(request,response,model);
	}
	
	/**
	 * modified by saurav.bhadra
	 * changes taken on 05042017**/
	
	@RequestMapping(value = "/editLocation", method = RequestMethod.POST)
	public String editLocation(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method getStandard-GET of VenueController");
			
			Location location = new Location();
			String saveId=request.getParameter("saveId");
			String newLocation=request.getParameter("newLocationName").trim().toUpperCase();
			//System.out.println("Save ID-->"+saveId+"\nNew Location Name-->"+newLocation);				
					
			location.setLocationCode(request.getParameter("locationCode"+saveId).trim());
			location.setLocationName(newLocation);
			location.setUpdatedBy(sessionObject.getUserId());
			String updateStatus = venueService.editLocation(location);
			String msg = null;
			if(updateStatus.equalsIgnoreCase("success")){
				msg = "Location Updated Successfully";
			}else{
				msg = "Updation Failed";
			}
			model.addAttribute("updateStatus", updateStatus);
			model.addAttribute("msg", msg);
			/**Added by @author Saif.Ali
			 * Date-28-03-2018*/
			String oldLocationName = request.getParameter("locationName"+saveId);
			String description = "";
			if(updateStatus.equalsIgnoreCase("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT LOCATION DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("FACILITY MANAGEMENT");
				updateLog.setUpdatedFor("Location Name :: " + newLocation);
				if(!(newLocation.equalsIgnoreCase(oldLocationName)))
				{
					description = description + ("Location Name :: " + oldLocationName + " updated to new Location Name :: " + newLocation + " ; ");
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 276 :: VenueController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
			
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method createZone-GET of VenueController", ce);
		}
		return getLocation(request,response,model);
	}
	
	@RequestMapping(value = "/inactiveLocation", method = RequestMethod.GET)
	public String inactiveLocation(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,@RequestParam(value="locationCode", required = false) String locationCode,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		//String strView = "venue/createZone";
		try {
			logger.info("Inside Method getStandard-GET of VenueController");
			
			Location location = new Location();				
					
			location.setLocationCode(locationCode);
			//venue.setZoneDesc(request.getParameter("zoneDesc"+saveId).trim());
			location.setUpdatedBy(sessionObject.getUserId());
			String updateStatus = venueService.inactiveLocation(location);
			String msg = null;
			if(updateStatus.equalsIgnoreCase("success")){
				msg = "Location Deleted Successfully";
			}else{
				msg = "Deletion Failed";
			}
			model.addAttribute("updateStatus", updateStatus);
			model.addAttribute("msg", msg);
			
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method createZone-GET of VenueController", ce);
		}
		return getLocation(request,response,model);
	}
	

	
	@RequestMapping(value = "/getVenue", method = RequestMethod.GET)
	public String getVenue(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "venue/createVenue";
		try {
			logger.info("Inside Method getVenue-GET of VenueController");
			List<Venue> venueList = venueService.getVenueList();
			model.addAttribute("venueList", venueList);
			//List<Country>countryList = commonService.getCountryList();
			List<Location>locationList = venueService.getLocationList();
			model.addAttribute("locationList", locationList);
			List<Venue>venueTypeList = venueService.getVenueTypeList();
			model.addAttribute("venueTypeList", venueTypeList);
		} catch (Exception ce){
			logger.error("Exception in method createZone-GET of VenueController", ce);
		}
		return strView;
	}
	
	@RequestMapping(value = "/getVenueAgainstVenueType", method = RequestMethod.GET)
	public @ResponseBody
	String getVenueAgainstVenueType(@RequestParam("venueTypeCode") String venueTypeCode) {
		logger.info("Inside Method getFacilityListAgainstVenue-GET of VenueController");
		String venues = "";
		try {
				//System.out.println("venueTypeCode====="+venueTypeCode);
				List<Venue> venueList = venueService.getVenueListAgainstVenueType(venueTypeCode);
				//System.out.println("venueList size=="+venueList.size());
				for(Venue v :venueList){
					venues = venues+","+ v.getVenueCode()+":"+v.getVenueName();
				}
			//System.out.println("venues=="+venues);
		} catch (Exception ce) {
			ce.printStackTrace();
			logger.error("Exception in method getFacilityListAgainstVenue-GET of VenueController", ce);
		}	
		
		//System.out.println("venue====="+venues);
		return (new Gson().toJson(venues));
	}
	
	@RequestMapping(value = "/getBuildingAgainstParentVenue", method = RequestMethod.GET)
	public @ResponseBody
	String getBuildingAgainstParentVenue(@RequestParam("venueCode") String venueCode) {
		logger.info("Inside Method getBuildingAgainstParentVenue-GET of VenueController");
		String buildingName = "";
		try {
				//System.out.println("venueCode====="+venueCode);
				 buildingName = venueService.getBuildingAgainstVenue(venueCode);
				//System.out.println("buildingName==="+buildingName);
				/*System.out.println("venueList size=="+hostelTypeList.size());
				for(HostelType ht :hostelTypeList){
					hostelType = hostelType+","+ ht.getHostelTypeCode()+":"+ht.getHostelTypeName();
				}*/
			//System.out.println("hostelType=="+hostelType);
		} catch (Exception ce) {
			ce.printStackTrace();
			logger.error("Exception in method getBuildingAgainstParentVenue-GET of VenueController", ce);
		}	
		
		//System.out.println("hostelType====="+hostelType);
		return (new Gson().toJson(buildingName));
	}
	
	
	@RequestMapping(value = "/createVenue", method = RequestMethod.POST)
	public String createVenue(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Venue venue,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		//String strView = "venue/createZone";
		try {
			logger.info("Inside Method createVenue-POST of VenueController");
			venue.setVenueName(venue.getVenueName().trim().toUpperCase());
			venue.setVenueDesc(venue.getVenueDesc().trim().toUpperCase());
			venue.setLocation(venue.getLocation().trim());
			venue.setBuilding(venue.getBuilding().trim().toUpperCase());
			venue.setFloor(venue.getFloor().trim());
			venue.setRoomNo(venue.getRoomNo().trim().toUpperCase());
			venue.setAvailableSeat(venue.getAvailableSeat());
			venue.setUpdatedBy(sessionObject.getUserId());	
			venue.setVenueTypeCode(venue.getVenueTypeCode().trim());
			if(venue.getVenueCode()!=null){
				venue.setVenueCode(venue.getVenueCode().trim());
			}
			String updateStatus = venueService.addVenue(venue);
			String msg = null;
			if(updateStatus.equalsIgnoreCase("success")){
				msg = "Venue Created Successfully";
			}else{
				msg = "Creation Failed";
			}
			model.addAttribute("updateStatus", updateStatus);
			model.addAttribute("msg", msg);
			/**Added by @author Saif.Ali
			 * Date-28-03-2018*/
			String venueName = request.getParameter("venueName");
			String venueDesc = request.getParameter("venueDesc");
			String availableSeat = request.getParameter("availableSeat");
			String location = request.getParameter("location");
			String parentVenue = request.getParameter("venueCode");
			String roomNo = request.getParameter("roomNo");
			String venueType = request.getParameter("venueTypeCode");
			String building = request.getParameter("building");
			String floor = request.getParameter("floor");
			String description = "";
			
			if(updateStatus.equalsIgnoreCase("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("ENTER VENUE DETAILS");
				updateLog.setInsertUpdate("INSERT");
				updateLog.setModule("FACILITY MANAGEMENT");
				updateLog.setUpdatedFor("Venue Name :: " + venueName);
				if(venueName != "")
				{
					description = description + ("Venue Name :: " + venueName + " ; ");
				}
				if(venueDesc != "")
				{
					description = description + ("Venue Description :: " + venueDesc + " ; ");
				}
				if(availableSeat != "")
				{
					description = description + ("Venue Available Seat :: " + availableSeat + " ; ");
				}
				if(location != "")
				{
					description = description + ("Venue Location :: " + location + " ; ");
				}
				if(parentVenue != "")
				{
					description = description + ("Parent Venue :: " + parentVenue + " ; ");
				}
				if(roomNo != "")
				{
					description = description + ("Room Number :: " + roomNo + " ; ");
				}
				if(venueType != "")
				{
					description = description + ("Venue Type :: " + venueType + " ; ");
				}
				if(building != "")
				{
					description = description + ("Building :: " + building + " ; ");
				}
				if(floor != "")
				{
					description = description + ("Floor :: " + floor + " ; ");
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 420 :: VenueController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
			
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method createVenue-POST of VenueController", ce);
		}
		return getVenue(request,response,model);
	}
	
	/**
	 * modified by saurav.bhadra
	 * changes taken on 05042017**/
	
	@RequestMapping(value = "/editVenue", method = RequestMethod.POST)
	public String editVenue(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editVenue-GET of VenueController");
			
			Venue venue = new Venue();
			String saveId=request.getParameter("saveId");
			String newVenueName = request.getParameter("newvenuename");
			String newVenueStatus = request.getParameter("newvenuestatus").trim();
			//System.out.println("saveId-->"+saveId+"\nNew Venue Name-->"+newVenueName+"\nNew Venue Status-->"+newVenueStatus);				
					
			venue.setVenueCode(request.getParameter("venueCode"+saveId).trim());
			venue.setVenueName(newVenueName);
			venue.setAvailability(newVenueStatus);	
			venue.setUpdatedBy(sessionObject.getUserId());
			//System.out.println("Venue Name :: "+venue.getVenueName());
			//System.out.println("Venue Status :: "+venue.getStatus());
			String updateStatus = venueService.editVenue(venue);
			String msg = null;
			if(updateStatus.equalsIgnoreCase("success")){
				msg = "Venue Updated Successfully";
			}else{
				msg = "Updation Failed";
			}
			model.addAttribute("updateStatus", updateStatus);
			model.addAttribute("msg", msg);
			
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method editVenue-GET of VenueController", ce);
		}
		return getVenue(request,response,model);
	}
	
	@RequestMapping(value = "/getVenueFacilityMapping", method = RequestMethod.GET)
	public String getVenueFacilityMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "venue/facilityVenueMapping";
		try {
			logger.info("Inside Method getVenueFacilityMapping-GET of VenueController");
			List<Venue> venueList = venueService.getVenueFacilityList();
			model.addAttribute("venueList", venueList);
			List<Facility>facilityList = venueService.getFacilityList();
			model.addAttribute("facilityList", facilityList);
		} catch (Exception ce){
			logger.error("Exception in method getVenueFacilityMapping-GET of VenueController", ce);
		}
		return strView;
	}
	
	
	@RequestMapping(value = "/createVenueFacility", method = RequestMethod.POST)
	public String createVenueFacility(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Venue venue,
			@RequestParam(value="facilityCode") String []facilities,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		//String strView = "venue/createZone";
		List<Facility>facilityList = new ArrayList<Facility>();
		try {
			logger.info("Inside Method createVenueFacility-POST of VenueController");
			venue.setVenueCode(venue.getVenueCode().trim().toUpperCase());
			for (String facility :facilities ){
				Facility facilityObj = new Facility();
				facilityObj.setFacilityCode(facility);
				facilityList.add(facilityObj);
			}
			venue.setFacilityList(facilityList);
			venue.setUpdatedBy(sessionObject.getUserId());		
			String updateStatus = venueService.addVenueFacilityMapping(venue);
			String msg = null;
			if(updateStatus.equalsIgnoreCase("success")){
				msg = "Venue And Facility Mapped Successfully";
			}else{
				msg = "Mapping Failed";
			}
			model.addAttribute("updateStatus", updateStatus);
			model.addAttribute("msg", msg);
			/**Added by @author Saif.Ali
			 * Date-28-03-2018*/
			String venueName = request.getParameter("venueCode");
			String description = "";
			if(updateStatus.equalsIgnoreCase("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("ENTER VENUE FACILITY MAPPING");
				updateLog.setInsertUpdate("INSERT");
				updateLog.setModule("FACILITY MANAGEMENT");
				updateLog.setUpdatedFor("Venue Code :: " + venueName);
				description = description + ("Venue Code :: " + venueName + " ; ");
				if(facilities != null && facilities.length != 0)
				{
					for (int i=0 ; i<facilities.length; i++)
					{
						description = description + ("Facility Code :: " + facilities[i] + " ; ");
					}
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 599 :: VenueController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
			
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method createVenueFacility-POST of VenueController", ce);
		}
		return getVenueFacilityMapping(request,response,model);
	}
	
	@RequestMapping(value = "/editVenueFacilityMapping", method = RequestMethod.GET)
	public String editVenueFacilityMapping(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "venue/editfacilityVenueMapping";
		try {
			logger.info("Inside Method editVenueFacilityMapping-GET of VenueController");
			List<Venue> venueList = venueService.getVenueFacilityList();
			model.addAttribute("venueList", venueList);
			//List<Facility>facilityList = venueService.getFacilityList();
			//model.addAttribute("facilityList", facilityList);
		} catch (Exception ce){
			logger.error("Exception in method editVenueFacilityMapping-GET of VenueController", ce);
		}
		return strView;
	}
	
	@RequestMapping(value = "/getFacilityListAgainstVenue", method = RequestMethod.GET)
	public @ResponseBody
	String getFacilityListAgainstVenue(@RequestParam("venueCode") String venueCode) {
		logger.info("Inside Method getFacilityListAgainstVenue-GET of VenueController");
		String venues = "";
		try {
				//System.out.println("venueCode====="+venueCode);
				List<Venue> venueList = venueService.getFacilityListAgainstVenue(venueCode);
				//System.out.println("venueList size=="+venueList.size());
				for(Venue v :venueList){
					venues = venues+","+ v.getStatus()+":"+v.getFacility();
				}
			
		} catch (Exception ce) {
			ce.printStackTrace();
			logger.error("Exception in method getFacilityListAgainstVenue-GET of VenueController", ce);
		}	
		
		//System.out.println("venue====="+venues);
		return (new Gson().toJson(venues));
	}
	
	@RequestMapping(value = "/editVenueFacility", method = RequestMethod.POST)
	public String editVenueFacility(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Venue venue,
			@RequestParam(required = false, value = "facilityCode") String []facilities,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		//String strView = "venue/createZone";
		List<Facility>facilityList = new ArrayList<Facility>();
		//System.out.println("within editVenueFacility");
		try {
			logger.info("Inside Method createVenueFacility-POST of VenueController");
			//System.out.println("facilityCodes length====="+facilities.length);
			venue.setVenueCode(venue.getVenueCode());
			for (String facility :facilities ){
				Facility facilityObj = new Facility();
				facilityObj.setFacilityCode(facility);
				facilityList.add(facilityObj);
			}
			venue.setFacilityList(facilityList);
			venue.setUpdatedBy(sessionObject.getUserId());		
			String updateStatus = venueService.updateVenueFacilityMapping(venue);
			String msg = null;
			if(updateStatus.equalsIgnoreCase("success")){
				msg = "Venue And Facility Mapping Updated Successfully";
			}else{
				msg = "Updation Failed";
			}
			model.addAttribute("updateStatus", updateStatus);
			model.addAttribute("msg", msg);
			/**Added by @author Saif.Ali
			 * Date-28-03-2018*/
			String venueName = request.getParameter("venueCode");
			String description = "";
			if(updateStatus.equalsIgnoreCase("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT VENUE RESOURCE MAPPING");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("FACILITY MANAGEMENT");
				updateLog.setUpdatedFor("Venue Code :: " + venueName);
				description = description + ("Venue Code :: " + venueName + " ; ");
				if(facilities != null && facilities.length != 0)
				{
					for (int i=0 ; i<facilities.length; i++)
					{
						description = description + (" mapped with Facility :: " + facilities[i]);
					}
				}
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
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method createVenueFacility-POST of VenueController", ce);
		}
		return editVenueFacilityMapping(request,response,model);
	}
	
	@RequestMapping(value = "/allocateVenue", method = RequestMethod.GET)
	public String allocateVenue(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "venue/venueAllocation";
		try {
			logger.info("Inside Method allocateVenue-GET of VenueController");
			List<Venue> venueList = venueService.getVenueList();
			model.addAttribute("venueList", venueList);
			List<Venue>allocatedVenueList = venueService.getAllocatedVenueList();
			model.addAttribute("allocatedVenueList", allocatedVenueList); //new line
			//List<Facility>facilityList = venueService.getFacilityList();
			//model.addAttribute("facilityList", facilityList);
		} catch (Exception ce){
			logger.error("Exception in method allocateVenue-GET of VenueController", ce);
		}
		return strView;
	}
	
	@RequestMapping(value = "/insertVenueAllocation", method = RequestMethod.POST)
	public String insertVenue(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Venue venue,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
	//	String strView = "venue/editfacilityVenueMapping";
		String msgForAllocationStatus = "";
		String updateStatus = "";
		try {
			logger.info("Inside Method insertVenueAllocation-POST of VenueController");
			venue.setUpdatedBy(sessionObject.getUserId());	
			Venue venueDetails  = venueService.getAllocatedVenueAgainstVenue(venue);
			//System.out.println("status===="+venueDetails.getStatus());
			if(venueDetails.getStatus().equalsIgnoreCase("ALLOCATED")){
				msgForAllocationStatus = venueDetails.getVenueName()+" is already allocated from "+venueDetails.getStartDate()+" "+venueDetails.getStartTime()+" to "+venueDetails.getEndDate()+" "+venueDetails.getEndTime();
			}else
				msgForAllocationStatus = "NOTALLOCATED";
			
			
			//System.out.println("msgForAllocationStatus===="+msgForAllocationStatus);
			if(venueDetails.getStatus().equalsIgnoreCase("NOTALLOCATED")){
				 updateStatus = venueService.insertVenueALlocation(venue);
				 String msg = null;
					if(updateStatus.equalsIgnoreCase("success")){
						msg = "Venue Allocated Successfully";
					}else{
						msg = "Allocation Failed";
					}
					model.addAttribute("updateStatus", updateStatus);
					model.addAttribute("msg", msg);
					/**Added by @author Saif.Ali
					 * Date-28-03-2018*/
					String venueName = request.getParameter("venueCode");
					String startDate = request.getParameter("startDate");
					String endDate = request.getParameter("endDate");
					String startTime = request.getParameter("startTime");
					String endTime = request.getParameter("endTime");
					String venueAllocationDescription = request.getParameter("zoneDesc");
					
					if(updateStatus.equalsIgnoreCase("success")){
						UpdateLog updateLog=new UpdateLog();
						updateLog.setUpdatedByUserId(sessionObject.getUserId());
						updateLog.setFunctionality("INSERT VENUE ALLOCATION DETAILS");
						updateLog.setInsertUpdate("INSERT");
						updateLog.setModule("FACILITY MANAGEMENT");
						updateLog.setUpdatedFor("Venue Code :: " + venueName);
						updateLog.setDescription("Venue Code :: " + venueName + " Start Date :: " + startDate + " End Date :: " + endDate + " Start Time :: " + startTime
								+ " End Time :: " + endTime + " with Description :: " + venueAllocationDescription + " ; ");
						String response_update=commonService.insertIntoActivityLog(updateLog);
						
						System.out.println("LN 787 :: VenueController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
								":: Functonality ::"+ updateLog.getFunctionality() + 
								":: Module ::"+updateLog.getModule() + 
								":: Updated For ::"+ updateLog.getUpdatedFor() +
								":: Description :: "+updateLog.getDescription() +
								":: Response :: " + response_update +
								":: Insert/Update :: " + updateLog.getInsertUpdate());
					}
					/***/
			}else{
				model.addAttribute("updateStatus", "ALLOCATED");
				model.addAttribute("msg", msgForAllocationStatus);
			}
			
			
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method insertVenueAllocation-POST of VenueController", ce);
		}
		return allocateVenue(request,response,model);
	}
	
	@RequestMapping(value = "/getAlloctedVenue", method = RequestMethod.GET)
	public @ResponseBody
	String getAlloctedVenue(@RequestParam("venueCode") String venueCode,
							@RequestParam("startDate") String startDate,
							@RequestParam("endDate") String endDate,
							@RequestParam("startTime") String startTime,
							@RequestParam("endTime") String endTime) {
		logger.info("Inside Method getFacilityListAgainstVenue-GET of VenueController");
		String msgForAllocationStatus = "";
		try {
				//System.out.println("venueCode==="+venueCode);
				//System.out.println("startDate==="+startDate);
				//System.out.println("endDate==="+endDate);
				//System.out.println("startTime==="+startTime);
			Venue venue = new Venue();
			venue.setVenueCode(venueCode);
			venue.setStartDate(startDate);
			venue.setEndDate(endDate);
			venue.setStartTime(startTime);
			venue.setEndTime(endTime);
			Venue venueDetails  = venueService.getAllocatedVenueAgainstVenue(venue);
			if(venueDetails.getStatus().equalsIgnoreCase("ALLOCATED")){
				msgForAllocationStatus = venueDetails.getVenueName()+" is already allocated from "+venueDetails.getStartDate()+" to "+venueDetails.getEndDate();
			}else
				msgForAllocationStatus = "NOTALLOCATED";
			
			
			//System.out.println("msgForAllocationStatus===="+msgForAllocationStatus);
		} catch (Exception ce) {
			ce.printStackTrace();
			logger.error("Exception in method getFacilityListAgainstVenue-GET of VenueController", ce);
		}	
		
		//System.out.println("venue====="+venues);
		return (new Gson().toJson(msgForAllocationStatus));
	}
	
	/********Added By Ranita21032017**************/
	@RequestMapping(value = "/inactiveVenue", method = RequestMethod.GET)
	public String inactiveStandard(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,@RequestParam("venueCode") String venueCode,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		//System.out.println("within");
		try {
			logger.info("Inside Method inactiveVenue-GET of VenueController");
			Venue  venue = new Venue();
			venue.setVenueCode(venueCode);
			venue.setUpdatedBy(sessionObject.getUserId());
			String status = venueService.inactiveVenue(venue);
			String msg = null;
			if(status.equalsIgnoreCase("success")){
				msg = "Deleted SuccessFully";
			}else{
				msg = "Failed To Delete";
			}
			model.addAttribute("msg", msg);
			model.addAttribute("updateStatus", status);
		} catch (Exception ce){
			logger.error("Exception in method inactiveStandard-GET of VenueController", ce);
		}
		return getVenue(request,response,model);
	}
}


