package com.qts.icam.controller.hostel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletContext;
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

import com.google.gson.Gson;
import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.backoffice.FeesTemplate;
import com.qts.icam.model.backoffice.TimeTableConfigModel;
import com.qts.icam.model.backoffice.WebIQTransaction;
import com.qts.icam.model.common.HostelType;
import com.qts.icam.model.common.Section;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.common.UpdateLog;
import com.qts.icam.model.hostel.Hostel;
import com.qts.icam.model.hostel.HostelFacility;
import com.qts.icam.model.hostel.House;
import com.qts.icam.model.hostel.HouseMaster;
import com.qts.icam.model.hostel.HouseResidenceMapping;
import com.qts.icam.model.hostel.HouseType;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.service.academics.AcademicsService;
import com.qts.icam.service.backoffice.BackOfficeService;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.hostel.HostelService;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.HostelExpense;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Room;
import com.qts.icam.model.common.RoomFacility;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.RoomType;

import org.json.JSONObject;

@Controller
@SessionAttributes("sessionObject")
public class HostelController {
	public static Logger logger = Logger.getLogger(HostelController.class);
	
	@Autowired
	HostelService hostelService;

	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	BackOfficeService backOfficeService;
	
	@Autowired
	AcademicsService academicsService;
	
	/**Added by @author Saif.Ali 26-03-2018*/
	@Autowired
	CommonService commonService;
	/***/
	
	@Value("${root.path}")
	private String rootPath;

	@Value("${URI.updateHostelOfCadet}")
	private String URIForUpdateHostelOfCadet;

	@Value("${Portal.userName}")
	private String portalUserName;
	
	@Value("${Portal.passWord}")
	private String portalPassWord;
	
	@Value("${webiq.available}")
	private String isWebIQAvailable;
	
	@RequestMapping(value = "/hostelProcessFlow", method = RequestMethod.GET)
	public String hostelProcessFlow() {
		return "hostel/hostelProcessFlow";
	}

	
	
	/*
	 * @author vinod.singh
	 * Opens Hostel Facility list page
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getHostelFacilityList", method = RequestMethod.GET)
	public ModelAndView getHostelFacilityList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView("hostel/listHostelFacility");
		try {
			logger.info("Inside Method getHostelFacilityList-GET of HostelController");
			
			List<HostelFacility> hostelFacilityList=hostelService.getHostelFacility();
			//model.addAttribute("hostelFacilityList", hostelFacilityList);
			if (hostelFacilityList != null
					&& hostelFacilityList.size() != 0) {
				
				PagedListHolder<HostelFacility> pagedListHolder = hostelService
						.getHostelFacilityPaging(request);
				mav.addObject("first",
						pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last",
						pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
			
		} catch (CustomException ce){
			logger.error("Exception in method getHostelFacilityList-GET of HostelController", ce);
		}
		return mav;
	}
	
	/*
	 * @author vinod.singh
	 * Opens fees template list page
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getHostelFacilityDetails", method = RequestMethod.POST)
	public String getHostelFacilityDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(value="hostelFacilityCode") String hostelFacilityCode) {
		String strView = "hostel/editHostelFacility";
		try {
			logger.info("Inside Method getHostelFacilityDetails-POST of HostelController");

			List<HostelFacility> hostelFacilityList=hostelService.getHostelFacility();
			model.addAttribute("hostelFacilityList", hostelFacilityList);
			
			List<Hostel> hostelList=hostelService.getHostel();
			model.addAttribute("hostelList", hostelList);
			
			HostelFacility hostelFacility=hostelService.getHostelFacilityDetails(hostelFacilityCode);
			model.addAttribute("hostelFacility", hostelFacility);
			
		} catch (CustomException ce){
			logger.error("Exception in method getHostelFacilityDetails-POST of HostelController", ce);
		}
		return strView;
	}
	
	/*
	 * @author vinod.singh
	 * Updates Hostel Facility
	 * Returns String
	 * 
	 */	
	/*@RequestMapping(value = "/editHostelFacility", method = RequestMethod.POST)
	public String editHostelFacility(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editHostelFacility-POST of HostelController");
			String saveId=request.getParameter("saveId");
			String []hostelCodes=request.getParameterValues("hostel"+saveId);
			String []oldHostels=request.getParameterValues("oldHostels"+saveId);
			//for(String s: hostelCodes)System.out.println(s);
			
			HostelFacility hostelFacility=new HostelFacility();
				if(null!=hostelCodes && hostelCodes.length!=0){
					
					hostelFacility.setHostelFacilityCode(request.getParameter("facilityCode"+saveId));
					hostelFacility.setDesc(request.getParameter("facilityName"+saveId).trim());
					hostelFacility.setHostelFacilityName(request.getParameter("facilityName"+saveId).trim().toUpperCase());
					hostelFacility.setUpdatedBy(sessionObject.getUserId());
					
					List<String> hostelList=new ArrayList<String>();
					List<String> oldHostelList=new ArrayList<String>();
					
					List<Hostel> insertHostelList=new ArrayList<Hostel>();
					List<Hostel> deleteHostelList=new ArrayList<Hostel>();
					if(null!=hostelCodes && hostelCodes.length!=0){
						hostelList=Arrays.asList(hostelCodes);
					}
					if(null!=oldHostels && oldHostels.length!=0){
						oldHostelList=Arrays.asList(oldHostels);
					}
					for(String newHostel:hostelList){
						if(! oldHostelList.contains(newHostel)){
							Hostel hostel=new Hostel();
							hostel.setHostelCode(newHostel);
							insertHostelList.add(hostel);
						}				
					}
					for(String oldHostel:oldHostelList){
						if(! hostelList.contains(oldHostel)){
							Hostel hostel=new Hostel();
							hostel.setHostelCode(oldHostel);
							deleteHostelList.add(hostel);
						}				
					}
					
					System.out.println("Cont"+insertHostelList);
					
					if(insertHostelList.size()!=0)
						hostelFacility.setHostelList(insertHostelList);
					if(deleteHostelList.size()!=0)
						hostelFacility.setOldHostelList(deleteHostelList);
					
					
					
					
					
					String updateStatus=hostelService.editHostelFacility(hostelFacility);
					model.addAttribute("insertUpdateStatus", updateStatus);
				
			}else{
				logger.info("Invalid HostelFacility Name");
			}
		} catch (CustomException ce){
			logger.error("Exception in method editClassSubjectMapping-POST of HostelController", ce);
		}		
		return getHostelFacility(request, response, model);
	}*/
	
	//****************************************************************Hostel Facility Ends
	
	/*
	 * @author vinod.singh
	 * Opens page to view Hostel Students
	 * Returns String
	 * 
	 */	
	@RequestMapping(value = "/getHostelStudentList", method = RequestMethod.GET)
	public String getHostelStudentList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "hostel/listHostelStudents";
		try {
			logger.info("Inside Method getHostel-GET of HostelController");
			List<Hostel> hostelList = hostelService.getHostel();
			model.addAttribute("hostelList", hostelList);
			
			List<Student> studentList = hostelService.getStudentListForEachHotel();
			model.addAttribute("studentList", studentList);
			
		} catch (CustomException ce){
			logger.error("Exception in method getHostel-GET of HostelController", ce);
		}
		return strView;
	}
	
//////////////////////////////sayani Started/////////////////////////////////////////
	
	/*
	* @author sayani.datta
	* Pagination for document verification
	* Returns String
	* 
	*/	
	@RequestMapping(value = "/listHostelFacilityPagination", method = RequestMethod.GET)
	public ModelAndView hostelFacilityPagination(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("Inside Method hostelFacilityPagination-GET of HostelController");
			mav = new ModelAndView("hostel/listHostelFacility");
			PagedListHolder<HostelFacility> pagedListHolder = hostelService
			.getHostelFacilityPaging(request);
			if (pagedListHolder != null) {
				mav.addObject("first",
				pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last",
				pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
		}
		}  catch (CustomException ce){
			logger.error("Exception in method hostelFacilityPagination-GET of HostelController", ce);
		}
			return mav;
		}

	/*
	* @author sayani.datta
	* Search for hostel facility
	* Returns String
	* 
	*/	
	
	
	@RequestMapping(value = "/getHostelFacilityDetails", method = RequestMethod.POST, params = "searchSubmit")
	public ModelAndView searchHostelFacility(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(required = false, value = "searchKey") String query,
			@RequestParam(required = false, value = "searchValue") String data) {
		logger.info("Inside Method searchHostelFacility-POST of HostelController");
		Map<String, Object> parameters = new HashMap<String, Object>();
		if(query!=null && data!=null){
			if (query.equalsIgnoreCase("FacilityName")) {
				parameters.put("FacilityName", data.trim());
			}
			if (query.equalsIgnoreCase("Hostel")) {
				parameters.put("Hostel", data.trim());
		}
		}
		try {
			hostelService.getSearchBasedHostelFacility(parameters);
		
		} catch (CustomException ce){
			logger.error("Exception in method searchHostelFacility-POST of HostelController", ce);
		}
		return hostelFacilityPagination(request, response);
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap model,
			@RequestParam(value="saveId") String saveId) {
		ModelAndView mav = null;
		try {
			//System.out.println("inside Test   "+saveId);
		}  catch (Exception ce){
			
		}
			return getHostel(request, response, model );
		}
	
	/**@author anup.roy*
	 * the total hostel portion will be start from here*
	 * */
	
	/**@author anup.roy*
	 * this method is to get create and list page of hostels */
	
	@RequestMapping(value = "/getHostel", method = RequestMethod.GET)
	public String getHostel(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "hostel/createHostel";
		
		try {
			logger.info("Inside Method getHostel-GET of HostelController");
			List<HostelType> hostelTypeList = hostelService.getHostelTypeList();
			model.addAttribute("hostelTypeList", hostelTypeList);
			List<Hostel> hostelList=hostelService.getHostel();
			model.addAttribute("hostelList", hostelList);
		} catch (Exception ce){
			logger.error("Exception in method getHostel-GET of HostelController", ce);
		}
		return strView;
	}
	
	/**@author anup.roy*
	 * this method is for adding hostel*/
	
	@RequestMapping(value = "/addHostel", method = RequestMethod.POST)
	public String addHostel(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			Hostel hostel,HostelType hostelType,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method addHostel-POST of HostelController");
			if(null!=hostel && null!=hostel.getHostelName() && hostel.getHostelName().trim().length()!=0){
				hostel.setHostelName(hostel.getHostelName().trim().toUpperCase());
				hostel.setHostelCode(hostel.getHostelName().trim().toUpperCase());
				hostel.setHostelAbbreviation(hostel.getHostelAbbreviation().trim().toUpperCase());
				hostel.setZone(hostel.getZone().trim().toUpperCase());
				hostel.setUpdatedBy(sessionObject.getUserId());
				hostel.setHostelType(hostelType);
				String insertStatus = hostelService.addHostel(hostel);
				model.addAttribute("insertStatus", insertStatus);
			}else{
				logger.info("Invalid Hostel Name.");
			}
		} catch (CustomException ce){
			logger.error("Exception in method addHostel-POST of HostelController", ce);
			ce.printStackTrace();
		}
		return getHostel(request, response, model);
	}
	
	/**@author anup.roy*
	 * this method is for editing hostels
	 * modified by saurav.bhadra
	 * changes taken on 05042017*/
	
	@RequestMapping(value = "/editHostel", method = RequestMethod.POST)
	public String editHostel(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("Inside Method editHostel-POST of HostelController");
			Hostel hostel = new Hostel();
			String saveId=request.getParameter("saveId");
			//System.out.println(saveId);
			String newHostelName = request.getParameter("hostelNameNew");
			String newAbbreviation = request.getParameter("newAbbreviationDesc");
			String newZone = request.getParameter("newZoneName");
			//System.out.println("New Hostel Name-->"+newHostelName+"\nNew Abbreviation-->"+newAbbreviation+"\nNew Zone-->"+newZone);
			hostel.setHostelAbbreviation(newAbbreviation);
			hostel.setHostelName(newHostelName);
			hostel.setHostelCode(request.getParameter("oldHostelCode"+saveId));
			hostel.setZone(newZone);
			hostel.setUpdatedBy(sessionObject.getUserId());
			String updateStatus = hostelService.editHostel(hostel);
			model.addAttribute("updateStatus", updateStatus);
			/**Added by @author Saif.Ali
			 * Date-26-03-2018*/
			String hostelTypeName = request.getParameter("newHostelTypeName"+saveId);
			String oldHostelName = request.getParameter("oldHostelCode"+saveId);
			String oldHostelAbbreviation = request.getParameter("oldHostelAbbreviation"+saveId);
			String oldHostelZone = request.getParameter("oldHostelZone"+saveId);
			String description = "";
			if(updateStatus.equalsIgnoreCase("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT HOSTEL DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("HOSTEL");
				updateLog.setUpdatedFor(hostelTypeName);
				if(!(oldHostelName.equalsIgnoreCase(newHostelName)))
				{
					description= description + ("Hostel Name :: " + oldHostelName + " updated to new Hostel Name ::" + newHostelName + " ; ");
				}
				if(!(oldHostelAbbreviation.equalsIgnoreCase(newAbbreviation)))
				{
					description= description + ("Hostel Abbreviation :: " + oldHostelAbbreviation + " updated to new Hostel Abbreviation ::" + newAbbreviation + " ; ");
				}
				if(!(oldHostelZone.equalsIgnoreCase(newZone)))
				{
					description= description + ("Hostel Zone :: " + oldHostelZone + " updated to new Hostel Zone ::" + newZone + " ; ");
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 458 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
			
		} catch (CustomException ce){
			ce.printStackTrace();
			logger.error("Exception in method editStandard-POST of HostelController", ce);
		}
		return getHostel(request, response, model);
	}
	
	/**@author anup.roy*
	 * this method is for create room type page*/
	
	@RequestMapping(value = "/addRoomType", method = RequestMethod.GET)
	public ModelAndView addRoomType(HttpServletRequest request,
									HttpServletResponse response,
									ModelMap model) {
		logger.info("In addRoomType() method of HostelController");
		try {
			List<RoomType> roomTypeList = hostelService.getRoomTypeList();
			List<SocialCategory> socialCategoryList = hostelService.getSocialCategoryList();
			if (socialCategoryList != null && socialCategoryList.size() != 0) {
				model.addAttribute("socialCategoryList", socialCategoryList);
			}
			if (roomTypeList != null && roomTypeList.size() != 0) {
				model.addAttribute("roomTypeList", roomTypeList);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("addRoomType() In HostelController.java: NullPointerException"+ e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addRoomType() In HostelController.java: Exception"+ e);
		}
		return new ModelAndView("hostel/addRoomType");
	}
	
	/**@author anup.roy*
	 * this method is for submit room type**/
	
	@RequestMapping(value = "/addRoomTypeNew", method = RequestMethod.POST)
	public ModelAndView addRoomTypeNew(HttpServletRequest request,
										HttpServletResponse response,
										ModelMap model,
										RoomType roomType,
										@RequestParam("sc") String[] category,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("In addRoomTypeNew() method of HostelController");
		
		try {
			if (roomType != null) {
				roomType.setUpdatedBy(sessionObject.getUserId());
				List<SocialCategory> scList = new ArrayList<SocialCategory>();
				for(int i=0;i<category.length;i++){
					SocialCategory sc = new SocialCategory(); 
					double amount = Double.parseDouble(request.getParameter(category[i]));
					sc.setSocialCategoryCode(category[i]);
					sc.setAmount(amount);
					sc.setUpdatedBy(sessionObject.getUserId());
					sc.setDesc(roomType.getRoomTypeName().trim().toUpperCase());
					scList.add(sc);
				}
				roomType.setSocialCategoryList(scList);
				hostelService.saveRoomType(roomType);
				/**Added by @author Saif.Ali
				 * Date-26-03-2018*/
				String roomTypeName = request.getParameter("roomTypeName");
				String description = "";
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("INSERT ROOM TYPE DETAILS");
				updateLog.setInsertUpdate("INSERT");
				updateLog.setModule("HOSTEL");
				updateLog.setUpdatedFor(roomTypeName);
				description = description + ("Room Type Name :: " + roomTypeName);
				
				for(int i=0;i<category.length;i++)
				{
					double amount = Double.parseDouble(request.getParameter(category[i]));
					description =description + (" with Category :: " + category[i] + " with Charges :: " + amount + " ; ");
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
							
				System.out.println("LN 549 :: HOSTELController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
				/***/
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("addRoomTypeNew() In HostelController.java: NullPointerException", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addRoomTypeNew() In HostelController.java: Exception", e);
		}
		return listRoomType(request,response,model);
	}
	
	/**@author anup.roy*
	 * this method is for list page of the room types*/
	
	@RequestMapping(value = "/listRoomType", method = RequestMethod.GET)
	public ModelAndView listRoomType(HttpServletRequest request,
									HttpServletResponse response,
									ModelMap model) {
	ModelAndView mav = new ModelAndView("hostel/listRoomType");
		try {
			List<RoomType> roomTypeList = hostelService.getRoomTypeList();
			if (roomTypeList != null && roomTypeList.size() != 0) {
				logger.info("In hostelController listRoomType() method");
				model.addAttribute("roomTypeList", roomTypeList);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	/**@author Anup*
	 * this method is for editing hostel room types*
	 * */
	
	@RequestMapping(value = "/editRoomType", method = RequestMethod.POST)
	public ModelAndView editRoomType(HttpServletRequest request,
									HttpServletResponse response,
									ModelMap model,
									@RequestParam("roomType") String roomType) {
		try {
			List<RoomType> roomTypeList = hostelService.getRoomTypeList();
			if (roomTypeList != null && roomTypeList.size() != 0) {
				model.addAttribute("roomTypeList", roomTypeList);
			}
			RoomType roomTypeDetails = hostelService.getRoomTypeDetails(roomType);
			
			if (roomTypeDetails != null) {
				model.addAttribute("roomTypeDetails", roomTypeDetails);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("hostel/editRoomType");
	}
	
	/**@author Anup*
	 * this method changes the room type and details*/
	
	@RequestMapping(value = "/updateRoomType", method = RequestMethod.POST)
	public ModelAndView updateRoomType(HttpServletRequest request,
										HttpServletResponse response,
										ModelMap model,
										RoomType roomType,
										@RequestParam("sc") String[] socialCategory,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		
		try {
			if (roomType != null) {
				roomType.setUpdatedBy(sessionObject.getUserId());
				
				List<SocialCategory> scList=new ArrayList<SocialCategory>();
				for(int i=0;i<socialCategory.length;i++){
					SocialCategory sc=new SocialCategory(); 
					double amount = Double.parseDouble(request.getParameter(socialCategory[i]));
					sc.setSocialCategoryCode(socialCategory[i]);
					sc.setAmount(amount);
					sc.setUpdatedBy(sessionObject.getUserId());
					sc.setDesc(roomType.getRoomTypeName().trim().toUpperCase());
					scList.add(sc);
				}
				roomType.setSocialCategoryList(scList);
				hostelService.updateRoomType(roomType);
				/**Added by @author Saif.Ali
				 * Date-26-03-2018*/
				String oldRoomTypeName = request.getParameter("roomTypeCode");
				String newRoomTypeName = request.getParameter("roomTypeName");
				String description = "";
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT ROOM TYPE DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("HOSTEL");
				updateLog.setUpdatedFor(newRoomTypeName);
				if(!(oldRoomTypeName.equalsIgnoreCase(newRoomTypeName)))
				{
					description = description + ("Room Type Name :: " + oldRoomTypeName + " updated to new Room Type Name :: " + newRoomTypeName);
				}
				else
				{
					description = description + ("Room Type Name :: " + newRoomTypeName);
				}
				for(int i=0;i<socialCategory.length;i++)
				{
					double amount = Double.parseDouble(request.getParameter(socialCategory[i]));
					description =description + (" with Category :: " + socialCategory[i] + " with Charges :: " + amount + " ; ");
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
					
				System.out.println("LN 671 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
				/***/
				
			}
		} catch (NullPointerException e) {
			logger.error("addRoomTypeNew() In HostelController.java: NullPointerException"+ e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("addRoomTypeNew() In HostelController.java: Exception"+ e);
			e.printStackTrace();
		}
		return listRoomType(request,response,model);

	}
	
	/**@author Anup*
	 * this page is to display create hostel faclity page**/
	
	@RequestMapping(value = "/addHostelFacility", method = RequestMethod.GET)
	public ModelAndView addHostelFacility(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = null;
		try {
			List<Hostel> hostelList = hostelService.getHostel();
			if (hostelList != null && hostelList.size() != 0) {
				model.addAttribute("hostelList", hostelList);
			}
			List<SocialCategory> socialCategoryList = hostelService.getSocialCategoryList();
			if (socialCategoryList != null && socialCategoryList.size() != 0) {
				model.addAttribute("socialCategoryList", socialCategoryList);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("addHostelFacility() In HostelController.java: NullPointerException",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addHostelFacility() In HostelController.java: Exception",e);
		}
		strView = "hostel/addHostelFacility";
		return new ModelAndView(strView);
	}
	
	/**@author Anup*
	 * to submit hostel facility and view list facility page*/
	
	@RequestMapping(value = "/addHostelFacilityDetails", method = RequestMethod.POST)
	public ModelAndView addHostelFacilityDetails(HttpServletRequest request,
												HttpServletResponse response,
												ModelMap model,
												@RequestParam("hostelName") String[] hostelName,
												HostelFacility hostelFacility,
												@RequestParam(required = false, value = "category") String[] category,
												@ModelAttribute("sessionObject") SessionObject sessionObject) {

		logger.info("In addHostelFacilityDetails() method of HostelController");
		List<Hostel> hostelList = new ArrayList<Hostel>();
		List<SocialCategory> socialCategoryList = new ArrayList<SocialCategory>();
		try {
			if (hostelName != null && hostelName.length != 0) {
				for (int hostelname = 0; hostelname < hostelName.length; hostelname++) {
					Hostel hostel = new Hostel();
					hostel.setHostelName(hostelName[hostelname]);
					hostelList.add(hostel);
				}
			}
			if (category != null && category.length != 0) {
				for (int i = 0; i < category.length; i++) {
					double charge = Double.parseDouble(request.getParameter(category[i]));
					SocialCategory socialCategory = new SocialCategory();
					socialCategory.setSocialCategoryCode(category[i]);
					socialCategory.setAmount(charge);
					socialCategoryList.add(socialCategory);
				}
			}
			hostelFacility.setHostelList(hostelList);
			hostelFacility.setSocialCategoryList(socialCategoryList);
			hostelFacility.setUpdatedBy(sessionObject.getUserId());
			hostelService.saveHostelFacilities(hostelFacility);
			/**Added by @author Saif.Ali
			 * Date-26-03-2018*/
			String facilityName = request.getParameter("hostelFacilityName");
			String facilityDescription = request.getParameter("hostelFacilityDesc");
			String description = "";
			UpdateLog updateLog=new UpdateLog();
			updateLog.setUpdatedByUserId(sessionObject.getUserId());
			updateLog.setFunctionality("ADD HOSTEL FACILITY");
			updateLog.setInsertUpdate("INSERT");
			updateLog.setModule("HOSTEL");
			updateLog.setUpdatedFor("Facility Name :: " + facilityName);
			description = description + ("Facility Name :: " + facilityName + " with Facility Description :: " + facilityDescription);
			if (hostelName != null && hostelName.length != 0)
			{
				for (int i = 0; i < hostelName.length; i++) 
				{
					for (int j = 0; j < category.length; j++) 
					{
						double charge = Double.parseDouble(request.getParameter(category[j]));
						description = description + (" added for category :: " + category[j]
								+ " of charges :: " + charge + " ; "); 
					}
					description = description + (" added for Hostel :: " + hostelName[i] + " ; "); 
				}
				
			}
			updateLog.setDescription(description);
			String response_update=commonService.insertIntoActivityLog(updateLog);
				
			System.out.println("LN 703 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
					":: Functonality ::"+ updateLog.getFunctionality() + 
					":: Module ::"+updateLog.getModule() + 
					":: Updated For ::"+ updateLog.getUpdatedFor() +
					":: Description :: "+updateLog.getDescription() +
					":: Response :: " + response_update +
					":: Insert/Update :: " + updateLog.getInsertUpdate());
			/***/
		}// try ends
		catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			logger.error("addHostelFacilityDetails() In HostelController.java: ArrayIndexOutOfBoundsException",e);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("addHostelFacilityDetails() In HostelController.java: NullPointerException",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addHostelFacilityDetails() In HostelController.java: Exception",e);
		}
		return listHostelFacility(request, response, model);
	}
	
	@RequestMapping(value = "/listHostelFacility", method = RequestMethod.GET)
	public ModelAndView listHostelFacility(HttpServletRequest request,
											HttpServletResponse response, ModelMap model) {
		logger.info("In listHostelFacility() method of HostelController");
		ModelAndView mav = new ModelAndView("hostel/listHostelFacility");
		try {
			List<HostelFacility> hostelFacilityList = hostelService.getHostelFacilityList();
			if (hostelFacilityList != null && hostelFacilityList.size() != 0) {
				model.addAttribute("hostelFacilityList", hostelFacilityList);
			}
		}catch (Exception e) {
			logger.error("listHostelFacility() In HostelController.java: Exception",e);
		}
		return mav;
	}
	
	/**@author anup.roy*
	 * this method is for edit facility*/
	
	@RequestMapping(value = "/editHostelFacility", method = RequestMethod.POST)
	public ModelAndView editHostelFacility(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("hostelFacilityCode") String hostelFacilityCode) {
		logger.info("In editHostelFacility() method of HostelController");
		try {
			HostelFacility hostelFacilityObj = hostelService.getHostelFacilities(hostelFacilityCode);
			if (hostelFacilityObj != null) {
				model.addAttribute("hostelFacility", hostelFacilityObj);
			}
			List<Hostel> hostelList = hostelService.getHostel();
			if (hostelList != null && hostelList.size() != 0 && hostelFacilityObj.getHostelList()!=null && hostelFacilityObj.getHostelList().size()!=0) {
				for(Hostel h:hostelFacilityObj.getHostelList()){
					for(Hostel ho:hostelList){
						if(ho.getHostelCode().equalsIgnoreCase(h.getHostelCode()))
							ho.setStatus("selected");
					}
				}
			}
			if (hostelList != null && hostelList.size() != 0) {
				model.addAttribute("hostelList", hostelList);
			}
		} catch (NullPointerException e) {
			logger.error("editHostelFacility() In HostelController.java: NullPointerException",e);
		}
		return new ModelAndView("hostel/editHostelFacility");
	}
	
	/**@author anup.roy*
	 * this method is for create new room**/
	
	@RequestMapping(value = "/addRoom", method = RequestMethod.GET)
	public ModelAndView addRoom(HttpServletRequest request,
								HttpServletResponse response,
								ModelMap model) {
		logger.info("In addRoom() method of HostelController");
		try {
			List<RoomType> roomTypeList = hostelService.getRoomTypeList();
			List<Hostel> hostelList = hostelService.getHostel();
			List<RoomFacility> roomFacilityList = hostelService.getRoomFacilitiesList();
			if (roomTypeList != null && roomTypeList.size() != 0) {
				model.addAttribute("roomTypeList", roomTypeList);
			}
			if (hostelList != null && hostelList.size() != 0) {
				model.addAttribute("hostelList", hostelList);
			}
			if (roomFacilityList != null && roomFacilityList.size() != 0) {
				model.addAttribute("roomFacilityList", roomFacilityList);
			}
		} catch (Exception e) {
			logger.error("addRoom() In HostelController.java: Exception" + e);
		}
		return new ModelAndView("hostel/addRoom");
	}
	
	/**@author Anup*
	 * this method is for submit add Room with facilities*/
	
	@RequestMapping(value = "/addroomDetails", method = RequestMethod.POST)
	public ModelAndView addroomDetails(HttpServletRequest request,
										HttpServletResponse response,
										ModelMap model,
										@RequestParam("facilitydetails") String facilitydetails,
										Room room,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("In addroomDetails() method of HostelController");
		List<RoomFacility> addroomFacilityList = new ArrayList<RoomFacility>();
		try {
			//System.out.println("facilityDetails in controller::::"+facilitydetails+"\nlendgth::"+facilitydetails.trim().length());
			if (facilitydetails != null && facilitydetails.trim().length() != 0) {
				String[] roomFacility_Quantity = facilitydetails.split("/");
				if (roomFacility_Quantity != null && roomFacility_Quantity.length != 0) {
					for (int roomfacility_quantity = roomFacility_Quantity.length - 1; roomfacility_quantity > -1; roomfacility_quantity--) {
						RoomFacility roomFacility = new RoomFacility();
						String[] facility_Quantity = roomFacility_Quantity[roomfacility_quantity].split("-");

						if (facility_Quantity != null && facility_Quantity.length != 0) {
							for (int facility_quantity = 0; facility_quantity < facility_Quantity.length; facility_quantity++) {
								int quantity = Integer.parseInt(facility_Quantity[2]);
								roomFacility.setUpdatedBy(sessionObject.getUserId());
								roomFacility.setRoomFacilityCode(facility_Quantity[0]);
								roomFacility.setRoomFacilityName(facility_Quantity[1]);
								roomFacility.setRoomFacilityQuantity(quantity);
							}
						}
						addroomFacilityList.add(roomFacility);
					}
				}
			}
			room.setRoomFacilityList(addroomFacilityList);
			room.setUpdatedBy(sessionObject.getUserId());
			room.setBedOccupied(0);
			hostelService.saveRoomFacilities(room);
			/**Added by @author Saif.Ali
			 * Date-13-03-2018*/
			String hostelName = request.getParameter("hostelName");
			String roomTypeName = request.getParameter("roomTypeCode");
			String roomNumber = request.getParameter("roomName");
			String roomPosition = request.getParameter("roomDesc");
			String roomFacility = request.getParameter("roomFacilityName");
			String totalBed = request.getParameter("bedTotal");
			
			UpdateLog updateLog=new UpdateLog();
			updateLog.setUpdatedByUserId(sessionObject.getUserId());
			updateLog.setFunctionality("INSERT ROOM DETAILS");
			updateLog.setInsertUpdate("INSERT");
			updateLog.setModule("HOSTEL");
			updateLog.setUpdatedFor(hostelName);
			updateLog.setDescription("Hostel Name :: " + hostelName + " with Room Type :: " + roomTypeName + " with Room Number :: " + roomNumber
					+ " with Room Position :: " + roomPosition + " with room facility :: " + roomFacility + " with total bed as :: " + totalBed + " ; ");
			String response_update=commonService.insertIntoActivityLog(updateLog);
				
			System.out.println("LN 937 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
					":: Functonality ::"+ updateLog.getFunctionality() + 
					":: Module ::"+updateLog.getModule() + 
					":: Updated For ::"+ updateLog.getUpdatedFor() +
					":: Description :: "+updateLog.getDescription() +
					":: Response :: " + response_update +
					":: Insert/Update :: " + updateLog.getInsertUpdate());
			/***/
		}
		catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("addroomDetails() In HostelController.java: NullPointerException"+ e);
		} catch (ArrayIndexOutOfBoundsException ae) {
			ae.printStackTrace();
			logger.error("addroomDetails() In HostelController.java: Exception"+ ae);
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
			logger.error("addroomDetails() In HostelController.java: Exception"+ ne);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addroomDetails() In HostelController.java: Exception"+ e);
		}
		return listRoom(request, response , model);
	}
	
	/**@author Anup*
	 * for view list page of hostel room*/
	
	@RequestMapping(value = "/listRoom", method = RequestMethod.GET)
	public ModelAndView listRoom(HttpServletRequest request,
								HttpServletResponse response,
								ModelMap model) {
		logger.info("In listRoom() method of HostelController");
		ModelAndView mav = new ModelAndView("hostel/listRoom");
		try {
			List<Room> roomList = hostelService.getRoomList();
			if (roomList != null && roomList.size() != 0) {
				model.addAttribute("roomList",roomList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("listRoom() In HostelController.java: Exception" + e);
		}
		return mav;
	}
	
	/**@author Anup*
	 * this method is for edit room in hostel*/
	
	@RequestMapping(value = "/editRoomDetails", method = RequestMethod.POST)
	public ModelAndView editRoomDetails(HttpServletRequest request,
										HttpServletResponse response,
										ModelMap model,
										Room room) {
		logger.info("In editRoomDetails() method of HostelController");
		try {
			//System.out.println("*************************************");
			Hostel hostelFromDB = hostelService.getRoomDetails(room);
			if (hostelFromDB != null) {
				model.addAttribute("hostelFromDB", hostelFromDB);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("editRoomDetails() In HostelController.java: NullPointerException",e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("editRoomDetails() In HostelController.java: Exception",e);
		}
		return new ModelAndView("hostel/editRoomDetails");

	}
	
	/**@author anup.roy*
	 * modified by ranita.sur
	 * changes taken 11052017
	 * this method is for assign student page*/
	
	@RequestMapping(value = "/assignHostelToStudent")
	public ModelAndView assignHostelToStudent(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.info("In addStudent() method of HostelController");
		try {
			List<Hostel> hostelList = hostelService.getStudentHostelList();
			Hostel hostel = null;
			if((Resource) request.getAttribute("studentFromStudentManagement")!=null){
				hostel = hostelService.getSelectedHostelRoomTypeByStudent((Resource) request.getAttribute("studentFromStudentManagement"));
				model.addAttribute("hostelRoomTypeByStudent", hostel);
			}
			if (hostelList != null && hostelList.size() != 0) {
				model.addAttribute("studentFromStudentManagement",(Resource) request.getAttribute("studentFromStudentManagement"));
				model.addAttribute("hostelList", hostelList);
			}
		} catch (NullPointerException e) {
			logger.error("addStudent() In HostelController.java: NullPointerException",e);
			e.printStackTrace();
		}
		return new ModelAndView("hostel/assignHostelToStudent");
	}
	
	/**@author Anup***/
	
	@RequestMapping(value = "/getStudent", method = RequestMethod.GET)
	public @ResponseBody
	String getStudent(@RequestParam("rollNumber") String rollNumber) {
		logger.info("In getStudent() method of HostelController");
		String str = "";
		try {
			Resource resource = hostelService.getStudentDetails(rollNumber);
			str = str + resource.getFirstName() + " "
					+ resource.getMiddleName() + " " + resource.getLastName();
					/*+ "~" + resource.getKlass() + "~" + resource.getSection()*/
		} catch (NullPointerException e) {
			logger.error("getStudent() In HostelController.java: NullPointerException"
					+ e);
		}catch(Exception e){
			logger.error("getStudent() In HostelController.java: Exception"+ e);
		 }
		return (new Gson().toJson(str));
	}
	
	/**@author Anup**/
	
	@RequestMapping(value = "/getRoomType", method = RequestMethod.GET)
	public @ResponseBody
	String getRoomType(@RequestParam("hostelName") String hostelName) {
		String str = "";
		try {
			List<RoomType> roomTypeList = hostelService.getRoomTypeList(hostelName);
			if (roomTypeList != null && roomTypeList.size() != 0) {
				for (RoomType rt : roomTypeList) {
					str = str + rt.getRoomTypeCode() + "~" + rt.getRoomTypeName() + "*";
				}
			}
		} catch (NullPointerException e) {
			logger.error("getRoomType() In HostelController.java: NullPointerException"+ e);
			e.printStackTrace();
		}catch(Exception e){
			logger.error("getRoomType() In HostelController.java: Exception"+ e);
			e.printStackTrace();
		 }
		 
		return (new Gson().toJson(str));
	}
	
	/**@author Anup***/
	
	@RequestMapping(value = "/getRoomList", method = RequestMethod.GET)
	public @ResponseBody
	String getRoomList(@RequestParam("hostelName") String hostelName,
			@RequestParam("roomTypeName") String roomTypeName, Room room) {
		logger.info("In getRoomList() method of HostelController");
		String str = "";
		try {
			room.setRoomTypeName(roomTypeName);
			room.setHostelName(hostelName);
			List<Room> roomNameList = hostelService.getRoomNameList(room);
			if (roomNameList != null && roomNameList.size() != 0) {
				for (Room r : roomNameList) {
					str = str + r.getRoomCode() + "~" + r.getRoomName() + "*";
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("getRoomList() In HostelController.java: NullPointerException"+ e);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getRoomList() In HostelController.java: Exception"+ e);
		}
		return (new Gson().toJson(str));
	}
	
	/**@author Anup**/
	
	@RequestMapping(value = "/getRoomFacilities", method = RequestMethod.GET)
	public @ResponseBody
	String getRoomFacilities(@RequestParam("roomName") String roomName,
			Room room) {
		logger.info("In getRoomFacilities() method of HostelController");
		String str = "";
		try {
			room.setRoomCode(roomName);
			Room rfl = hostelService.getRoomFacilities(room);
			List<RoomFacility> roomFacilityList = rfl.getRoomFacilityList();
			str = rfl.getBedTotal() + "~" + rfl.getBedVaccent() + "*";
			if (roomFacilityList != null && roomFacilityList.size() != 0) {
				for (RoomFacility rf : roomFacilityList) {
					str = str + rf.getRoomFacilityName() + "~"
							+ rf.getRoomFacilityQuantity() + "*";
				}
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("getRoomFacilities() In HostelController.java: NullPointerException"+ e);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("getRoomFacilities() In HostelController.java: Exception"+ e);
		 }
		return (new Gson().toJson(str));
	}
	
	/**@author Anup*
	 * this method is for submit assigning hostel to a student**/
	
	@RequestMapping(value = "/assignedHostel", method = RequestMethod.POST)
	public ModelAndView assignedHostel(HttpServletRequest request,
										HttpServletResponse response,
										Hostel hostel,
										ModelMap model,
										Resource resource,
										Room room,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("In listStudentADD() method of HostelController");
		ModelAndView mav = new ModelAndView("hostel/assignedHostelToStudents");
		try {
			hostel.setResource(resource);
			hostel.setRoom(room);
			hostel.setUpdatedBy(sessionObject.getUserId());
			
			List<Hostel> hostelList =hostelService.saveStudentRoomHostelMapping(hostel, sessionObject.getUserId());
			
			if (hostelList != null && hostelList.size() != 0) {
				model.addAttribute("hostelList",hostelList);
			}
			/**Added by Saif 26-03-2018*/
			if(hostelList != null && hostelList.size() != 0)
			{
				String userId = request.getParameter("userId");
				String name = request.getParameter("name");
				String hostelName = request.getParameter("hostelName");
				String roomType = request.getParameter("roomTypeName");
				String roomName = request.getParameter("roomName");
				
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("INSERT STUDENT AND HOSTEL MAPPING DETAILS");
				updateLog.setInsertUpdate("INSERT");
				updateLog.setModule("HOSTEL");
				updateLog.setUpdatedFor("User Id :: " + userId + " with Name :: " + name + " ; ");
				updateLog.setDescription("User Id :: " + userId + " with Name :: " + name + " mapped to hostel name :: " + hostelName + " and room type ::" + roomType
						+ " and room name :: " + roomName + " ; ");
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 1179 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("listStudentADD() In HostelController.java: NullPointerException", e);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("listStudentADD() In HostelController.java: Exception", e);
		 }
		return mav;
	}
	
	/**@author Anup*
	 * this method is for view page of assigned student list*/
	
	@RequestMapping(value = "/listStudentAssignedHostel", method = RequestMethod.GET)
	public ModelAndView listStudentAssignedHostel(HttpServletRequest request,
										HttpServletResponse response,
										ModelMap model
										) {
		logger.info("In listStudentGET() method of HostelController");
		ModelAndView mav = new ModelAndView("hostel/assignedHostelToStudents");
		try {
			List<Hostel> hostelList = hostelService.getStudentRoomHostelMappingList();
			if (hostelList != null && hostelList.size() != 0) {
				model.addAttribute("hostelList", hostelList);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("listStudentGET() In HostelController.java: Exception"+ e);
		}
		return mav;
	}
	
	/**modified by ranita.sur
	 * 24062017
	 * this method is for de allocate hostel*/
	
	@RequestMapping(value = "/deAllocateStudentFromHostel", method = RequestMethod.GET)
	public ModelAndView deAllocateStudentFromHostel(HttpServletRequest request,
											HttpServletResponse response,ModelMap model,
											@ModelAttribute("sessionObject") SessionObject sessionObject
											) {
		logger.info("In deAllocateStudentFromHostel() method of HostelController");
		try {
			String hostelName=request.getParameter("hostelName");
			String userIdNew=request.getParameter("userId");
			hostelService.deAllocateStudentFromHostel(userIdNew,sessionObject.getUserId(),hostelName);
			/**Added by saif 27-03-2018
			 * Used to insert the informations into the activity_log table*/
			String studentId= request.getParameter("studentUserId");
			String studentName= request.getParameter("studentName");
			String studentStandard= request.getParameter("studentStandard");
			String studentSection= request.getParameter("studentSection");
			String studentRoomName= request.getParameter("studentRoomName");
			String studentHostelName= request.getParameter("studentHostelName");
			
			UpdateLog updateLog=new UpdateLog();
			updateLog.setUpdatedByUserId(sessionObject.getUserId());
			updateLog.setFunctionality("DE- ALLOCATE HOSTEL FROM STUDENTS");
			updateLog.setInsertUpdate("UPDATE");
			updateLog.setModule("HOSTEL");
			updateLog.setUpdatedFor("Student ID :: " + studentId + " Student Name :: " + studentName);
			updateLog.setDescription("Student ID :: " + studentId + " Student Name :: " + studentName + " of standard :: " + studentStandard
					+ " of section :: " + studentSection + " of Room Name :: " + studentRoomName + " is de - allocated from Hostel Name :: " + studentHostelName + " ; ");
			String response_update=commonService.insertIntoActivityLog(updateLog);
			
			System.out.println("LN 1253 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
					":: Functonality ::"+ updateLog.getFunctionality() + 
					":: Module ::"+updateLog.getModule() + 
					":: Updated For ::"+ updateLog.getUpdatedFor() +
					":: Description :: "+updateLog.getDescription() +
					":: Response :: " + response_update +
					":: Insert/Update :: " + updateLog.getInsertUpdate());
			/***/
		}catch(NullPointerException e){
			logger.error("listHostelPagination() In HostelController.java: NullPointerException"+ e);
			e.printStackTrace();
		}
		 catch (Exception e) {
			logger.error("listHostelPagination() In HostelController.java: Exception"+ e);
			e.printStackTrace();
		}
		return listStudentAssignedHostel(request,response,model);
	}
	
	/**@author anup.roy*
	 * this method is to view hostel expense page*/
	
	@RequestMapping(value = "/hostelExpense", method = RequestMethod.GET)
	public String hostelExpense(HttpServletRequest request,
									HttpServletResponse response,
									ModelMap model) {
		try {
			logger.info("calling hostelExpense()GET method of HostelController");
			//List<Commodity> commodityList = hostelService.getAllCommodityListForHostelExpense();
			//model.addAttribute("commodityList", commodityList);
		} catch (Exception e) {
			logger.error("Exception in hostelExpense() GET method Of HostelController",e);
		}
		return "hostel/hostelExpense";
	}
	
	/**@author anup.roy
	 * this method is for getting the commodity name in autocomplete way
	 * */
	
	@RequestMapping(value = "/getCommodityListForHostelExpense", method = RequestMethod.GET)
	public  @ResponseBody String getCommodityListForHostelExpense() {
		//System.out.println("ajax called");
		String commodityNames = null;
		try {
			commodityNames = hostelService.getAllCommodityListForHostelExpense();
		} catch (Exception ce){
			ce.printStackTrace();
			logger.error("Exception in method getCommodityListForHostelExpense-GET of BackOfficeController", ce);
		}
		return (new Gson().toJson(commodityNames.toString()));
	}

	/**@author anup.roy
	 * this method is foe getting the commodity stock of any respective commodity
	 * */
	
	@RequestMapping(value = "/getCommodityStock", method = RequestMethod.GET)
	public @ResponseBody
	String getCommodityStock(@RequestParam("commodityName") String commodityName) {
		String stock = "";
		try {
			stock = hostelService.getCommodityStock(commodityName);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getCommodityStock() In HostelController.java: Exception"+ e);
		}
		return (new Gson().toJson(stock));
	}
	
	/**@author anup.roy*
	 * this method is for submit hostel expense*/
	
	@RequestMapping(value = "/saveHostelExpense", method = RequestMethod.POST)
	public String saveMessExpense(HttpServletRequest request,
									HttpServletResponse response,
									ModelMap model,
									@RequestParam("commodity") String[] commodity,
									@RequestParam("morning") String[] morning,
									@RequestParam("noon") String[] noon,
									@RequestParam("evening") String[] evening,
									@RequestParam("night") String[] night,
									@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String status="";
		try {
			List<HostelExpense> hostelExpenseList = new ArrayList<HostelExpense>();
			if (commodity != null) {
				for (int i = 0; i < commodity.length; i++) {
					if (commodity[i] != null && commodity.length != 0) {
						double total = 0.00;
						HostelExpense me = new HostelExpense();
						me.setCommodityCode(commodity[i].trim());

						if (morning[i] != null && morning[i].length() != 0) {
							morning[i] = morning[i].trim();
							me.setMorning(Double.parseDouble(morning[i]));
							total = total + me.getMorning();
						}
						if (noon[i] != null && noon[i].length() != 0) {
							noon[i] = noon[i].trim();
							me.setNoon(Double.parseDouble(noon[i]));
							total = total + me.getNoon();
						}
						if (evening[i] != null && evening[i].length() != 0) {
							evening[i] = evening[i].trim();
							me.setEvening(Double.parseDouble(evening[i]));
							total = total + me.getEvening();
						}
						if (night[i] != null && night[i].length() != 0) {
							night[i] = night[i].trim();
							me.setNight(Double.parseDouble(night[i]));
							total = total + me.getNight();
						}
						me.setTotal(total);
						hostelExpenseList.add(me);
					}
				}
			}
			status=hostelService.saveHostelExpense(hostelExpenseList, sessionObject.getUserId());
			model.addAttribute("status", status);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.error("saveMessExpense() In AdminController.java: Exception"+ e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("saveMessExpense() In AdminController.java: NullPointerException"+ e);
		}
		return hostelExpense(request, response, model);
	}
	
	@RequestMapping(value = "/submitEditedRoomDetails", method = RequestMethod.POST)
	public ModelAndView submitEditedRoomDetails(HttpServletRequest request,
												HttpServletResponse response,
												ModelMap model,
												@RequestParam("facilitydetails") String facilitydetails,
												Room room,
												@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("In submitEditedRoomDetails() method of HostelController");
		ModelAndView mav = new ModelAndView("hostel/listRoom");
		List<RoomFacility> addroomFacilityList = new ArrayList<RoomFacility>();
		try {
			Hostel hostelFromDB = hostelService.getRoomDetails(room);
			if (hostelFromDB != null) {
				model.addAttribute("hostelFromDB", hostelFromDB);
			}
			if (facilitydetails != null && facilitydetails.trim().length() != 0) {
				String[] roomFacility_Quantity = facilitydetails.split("/");
				if (roomFacility_Quantity != null && roomFacility_Quantity.length != 0) {
					for (int roomfacility_quantity = roomFacility_Quantity.length - 1; roomfacility_quantity > -1; roomfacility_quantity--) {
						RoomFacility roomFacility = new RoomFacility();
						String[] facility_Quantity = roomFacility_Quantity[roomfacility_quantity].split("-");
						if (facility_Quantity != null && facility_Quantity.length != 0) {
							for (int facility_quantity = 0; facility_quantity < facility_Quantity.length; facility_quantity++) {
								int quantity = Integer.parseInt(facility_Quantity[2]);
								roomFacility.setUpdatedBy(sessionObject.getUserId());
								roomFacility.setRoomFacilityCode(facility_Quantity[0]);
								roomFacility.setRoomFacilityName(facility_Quantity[1]);
								roomFacility.setRoomFacilityQuantity(quantity);
								roomFacility.setRoomCode(room.getRoomCode());
							}
						}
						addroomFacilityList.add(roomFacility);
					}
				}
			}
			List<Room> roomList = hostelService.updateRoomFacilities(addroomFacilityList);
			if (roomList != null && roomList.size() != 0) {
				model.addAttribute("roomList", roomList);
			}
			/**Added by Saif 26-03-2018
			 * Used to insert the details into the activity_log table*/
			if(roomList.size() != 0 && roomList!= null)
			{
				String description = "";
				String hostelname = request.getParameter("oldHostelName");
				String oldRoomNumber = request.getParameter("roomCode");
				String oldRoomPosition = request.getParameter("oldRoomPosition");
				String oldRoomBed = request.getParameter("oldBedTotal");
				
				String newRoomNumber = request.getParameter("roomName");
				String newRoomPosition = request.getParameter("roomDesc");
				String newRoomBed = request.getParameter("bedTotal");
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT ROOM DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("HOSTEL");
				updateLog.setUpdatedFor(hostelname);
				description= description + ("Hostel Name ::" + hostelname + " ; ") ;
				if(!(oldRoomNumber.equalsIgnoreCase(newRoomNumber)))
				{
					description= description + ("Room Number :: " + oldRoomNumber + " updated to new Room Number :: " + newRoomNumber + " ; ");
				}
				if(!(oldRoomPosition.equalsIgnoreCase(newRoomPosition)))
				{
					description= description + ("Room Position :: " + oldRoomPosition + " updated to new Room Position :: " + newRoomPosition + " ; ");
				}
				if(!(oldRoomBed.equalsIgnoreCase(newRoomBed)))
				{
					description= description + ("Room with Total Bed :: " + oldRoomBed + " updated to new Room Bed :: " + newRoomBed + " ; ");
				}
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 1401 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
		}
		catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("submitEditedRoomDetails() In HostelController.java: NullPointerException"+ e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("submitEditedRoomDetails() In HostelController.java: Exception"+ e);
		}
		return mav;
	}
	
	/**
	 * @author ranita.sur
	 * changes taken on 24062017*/
	
	@RequestMapping(value="/getHostelType", method = RequestMethod.GET)
	public String getHostelType(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		List<Hostel> hostelTypeListNew = null;		
		try{
			logger.info("Excecuting viewDepartment() from hostelController, calling getAllHostelTypeList() in hostelService.java");
			hostelTypeListNew = hostelService.getAllHostelTypeList();					
			model.addAttribute("hostelTypeListNew", hostelTypeListNew);			
		}catch(Exception e){
			logger.error("Exception in viewDepartment() from hostelController : ",e);
		}
		return "hostel/addHostelType";
	}
	
	@RequestMapping(value="/submitHostelType", method = RequestMethod.POST)
	public String submitHostelType(HttpServletRequest request, HttpServletResponse response, ModelMap model,
								@RequestParam("hostelTypeName") String hostelTypeName,
								@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{			
			Hostel hostel=new Hostel();
			logger.info("Excecuting addDepartment() from hostelController, calling addHostelType() in hostelService.java");
			hostel.setUpdatedBy(sessionObject.getUserId());	
			hostel.setHostelTypeName(hostelTypeName);
			String submitResponse = hostelService.addHostelType(hostel);
			model.addAttribute("submitResponse", submitResponse);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception in addHostelType() from hostelController : ",e);
		}
		return getHostelType(request, response, model);
	}
	/**
	 * @author anup.roy
	 * this method is for loading the create house page
	 * */
	
	@RequestMapping(value = "/getHouse", method = RequestMethod.GET)
	public ModelAndView getHouse(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String strview = null;
		try {
			logger.info("In ModelAndView getHouse(request, response, model) of HostelController.java");
			List<HouseType> houseTypeList = hostelService.getAllHouseTypeList();
			model.addAttribute("houseTypeList", houseTypeList);
			List<House> houseList = hostelService.getAllHouseList();
			model.addAttribute("houseList", houseList);
			strview = "hostel/createHouse";
		}catch (Exception e) {
			logger.error("Exception in ModelAndView getHouse(request, response, model) of HostelController.java:"+e);
			e.printStackTrace();
		}
		return new ModelAndView(strview);
	}
	
	/**
	 * @author anup.roy
	 * this method is for adding new house*/
	
	@RequestMapping(value = "/addNewHouse" , method = RequestMethod.POST)
	public ModelAndView addNewHouse (HttpServletRequest request, HttpServletResponse response, ModelMap model,
				@ModelAttribute("sessionObject") SessionObject sessionObject, House house) {
		try {
			logger.info("In addNewHouse()-POST of HostelController.java");
			house.setUpdatedBy(sessionObject.getUserId());
			String insertStatus = hostelService.addNewHouse(house);
			model.addAttribute("insertStatus", insertStatus);
		}catch (Exception e) {
			logger.error("Exception in addNewHouse()-POST of HostelController.java:"+e);
			e.printStackTrace();
		}
		return getHouse(request, response, model);
	}
	
	@RequestMapping(value = "/getHouseResidenceMapping" , method = RequestMethod.GET)
	public ModelAndView getHouseResidenceMapping (HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String strView = null;
		try {
			logger.info("In ModelAndView getHouseResidenceMapping(request, response, model)-GET of HostelController.java");
			List<House> houseList = hostelService.getAllHouseList();
			model.addAttribute("houseList", houseList);
			List<Hostel> hostelList=hostelService.getHostel();
			model.addAttribute("hostelList", hostelList);
			List<HouseResidenceMapping> houseResidenceMappingList = hostelService.getAllHouseResidenceMapping();
			model.addAttribute("houseResidenceMappingList", houseResidenceMappingList);
			strView = "hostel/createHouseResidenceMapping";
		}catch (Exception e) {
			logger.error("Exception in ModelAndView getHouseResidenceMapping(request, response, model)-GET of HostelController.java:"+e);
			e.printStackTrace();
		}
		return new ModelAndView(strView);
	}
	
	@RequestMapping(value = "/submitHouseResidenceMapping" , method = RequestMethod.POST)
	public ModelAndView submitHouseResidenceMapping (HttpServletRequest request, HttpServletResponse response,
			ModelMap model, @ModelAttribute("sessionObject") SessionObject sessionObject, HouseResidenceMapping houseResidenceMapping) {
		try {
			logger.info("In submitHouseResidenceMapping()-POST of HostelController.java");
			houseResidenceMapping.setUpdatedBy(sessionObject.getUserId());
			String insertStatus = hostelService.submitHouseResidenceMapping(houseResidenceMapping);
			model.addAttribute("insertStatus", insertStatus);
		}catch (Exception e) {
			logger.error("Exception in submitHouseResidenceMapping()-POST of HostelController.java:"+e);
			e.printStackTrace();
		}
		return getHouseResidenceMapping(request, response, model);
	}
	
	/**
	 * @author anup.roy
	 * this method is for viewing the create page of house master and attached house master*/
	
	@RequestMapping(value = "/getAssignedHouseMaster" , method = RequestMethod.GET)
	public ModelAndView getAssignedHouseMaster (HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		String strView = null;
		List<AcademicYear> academicYearList = null;
		try {
			logger.info("In public ModelAndView getAssignedHouseMaster(req, res, model)- GET of HostelController.java");
			academicYearList = backOfficeService.getAcademicYearList();
			model.addAttribute("academicYearList", academicYearList);
			strView = "hostel/assignHouseMaster";
		}catch (Exception e) {
			logger.error("Exception In public ModelAndView getAssignedHouseMaster(req, res, model)- GET of HostelController.java:"+e);
			e.printStackTrace();
		}
		return new ModelAndView(strView);
	}
	
	@RequestMapping(value = "/fetchCreatedHouseMaster" , method = RequestMethod.GET)
	@ResponseBody
	public String fetchCreatedHouseMaster(@RequestParam("academicYear") String academicYear) {
		String houseMasterData = "";
		List<HouseMaster> houseMasterList = null;
		try {
			logger.info("In String fetchCreatedHouseMaster(String academicYear) of HostelController.java");
			houseMasterList = hostelService.getHouseMasterData(academicYear);
			if(null != houseMasterList) {
				StringBuilder sb = new StringBuilder();
				for(HouseMaster hm : houseMasterList) {
					sb.append(hm.getHouseCode()+"*");
					sb.append(hm.getHouseName()+"#");
					sb.append(hm.getHouseMasterName()+"#");
					sb.append(hm.getAsstHouseMasterName()+"#");
				}
			}
		}catch (Exception e) {
			logger.error("Exception in String fetchCreatedHouseMaster(String academicYear) of HostelController.java:"+e);
			e.printStackTrace();
		}
		return houseMasterData.toString();
	}
	
	

	/**
	 * @author anup.roy
	 * this method is for update house of cadet*/
	
	@RequestMapping(value = "/updateHostelOfCadet" , method = RequestMethod.GET)
	public String updateHostelOfCadet(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		String strView = "hostel/updateHostelOfCadet";
		try{
			logger.info("In updateHostelOfCadet-GET of HostelController.java");
		}catch (Exception e) {
			logger.error("Exception in updateHostelOfCadet-GET of HostelController.java:"+e);
			e.printStackTrace();
		}
		return strView;
	}
	
	/**
	 * @author anup.roy
	 * this method is for fetching the info and house info for cadet*/
	@ResponseBody
	@RequestMapping(value = "/getHouseAndInfoOfCadet" , method = RequestMethod.GET)
	public String getHouseAndInfoOfCadet(HttpServletRequest request, HttpServletResponse response,
										@RequestParam("userId")String userId){
		String house = null;
		try{
			logger.info("In getHouseAndInfoOfCadet-GET of HostelController.java");
			Resource resource = hostelService.getHouseAndInfoOfCadet(userId);
			StringBuilder stbr = new StringBuilder();
			if(null != resource){
				stbr.append(resource.getUserId()+"#");
				stbr.append(resource.getResourceName()+"#");
				stbr.append(resource.getHouse().getHouseName()+"#");
				house = stbr.toString().substring(0, stbr.toString().length() - 1);
			}
		}catch (Exception e) {
			logger.error("Exception in getHouseAndInfoOfCadet-GET of HostelController.java:"+e);
			e.printStackTrace();
		}
		return house;
	}
	
	/**
	 *@author anup.roy */
	@ResponseBody
	@RequestMapping(value = "/getAllHostels" , method = RequestMethod.GET)
	public String getAllHostels(HttpServletRequest request, HttpServletResponse response){
		String house = null;
		try{
			logger.info("In getHouseAndInfoOfCadet-GET of HostelController.java");
			List<House> houseList = hostelService.getAllHouseList();
			StringBuilder stbr = new StringBuilder();
			if(houseList.size()!=0){
				for(House h : houseList){
					stbr.append(h.getHouseCode()+"#");
					stbr.append(h.getHouseName()+"^");
					house = stbr.toString().substring(0, stbr.toString().length() - 1);
				}
			}
		}catch (Exception e) {
			logger.error("Exception in getHouseAndInfoOfCadet-GET of HostelController.java:"+e);
			e.printStackTrace();
		}
		return house;
	}
	
	@RequestMapping(value = "/submitUpdateOfHouseForCadet" , method = RequestMethod.POST)
	public String submitUpdateOfHouseForCadet(HttpServletRequest request, HttpServletResponse response,
											ModelMap model,	@ModelAttribute("resource") Resource resource,
											@ModelAttribute("sessionObject") SessionObject sessionObject){
		try{
			logger.info("In submitUpdateOfHouseForCadet-POST of HostelController.java");
			resource.setUpdatedBy(sessionObject.getUserId());
			
			//CALL to DB
			String updateStatus = hostelService.submitUpdateOfHouseForCadet(resource);
			model.addAttribute("updateStatus", updateStatus);
			
			if(isWebIQAvailable.equalsIgnoreCase("true")){
				JSONObject jsonObj = new JSONObject();
				AcademicYear academicYear = commonService.getCurrentAcademicYear();
				
				jsonObj.put("username", portalUserName);
				jsonObj.put("password", portalPassWord);
				jsonObj.put("rollNumber", resource.getUserId());
				
				//PRAD JUNE 7 2018
				//Get the House Name from the House Code
				String houseName = backOfficeService.getHouseName(resource.getHouse().getHouseName());
				//jsonObj.put("house", resource.getHouse().getHouseName());
				jsonObj.put("house", houseName);
				jsonObj.put("academicsSession", academicYear.getAcademicYearName());
				
				System.out.println(jsonObj.toString());
				final String uri = URIForUpdateHostelOfCadet;
				System.out.println("URI:::"+uri);
				URL url = new URL(uri);
				HttpURLConnection connection = (HttpURLConnection)url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				connection.setRequestMethod("POST");
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(jsonObj.toString());
				out.close();
				
				String json_response = "";
				InputStreamReader in = new InputStreamReader(connection.getInputStream());
				BufferedReader br = new BufferedReader(in);
				String text = "";
				while((text = br.readLine())!= null){
					json_response += text;
				}
				System.out.println("JSON response:::"+ json_response);
				
				if((!json_response.isEmpty())){
					JSONObject object = new JSONObject(json_response);
					int statusFromJsonResponse = (int)object.get("status");
					
					WebIQTransaction webIQTran = null;
					if(statusFromJsonResponse == 200){
						//If call to the API is successful, then insert into the webiq_transaction_details table 
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(sessionObject.getUserId());
						webIQTran.setUri(URIForUpdateHostelOfCadet);
						webIQTran.setRequestJSON(jsonObj.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(true);
					}else{
						//If Failure then also insert into the webiq_transaction_details table
						webIQTran = new WebIQTransaction();
						webIQTran.setUpdatedBy(sessionObject.getUserId());
						webIQTran.setUri(URIForUpdateHostelOfCadet);
						webIQTran.setRequestJSON(jsonObj.toString());
						webIQTran.setResponseJSON(json_response);
						webIQTran.setStatus(false);
					}
					
					//Call to the BackOffice Service
					backOfficeService.addWebIQTransaction(webIQTran);
				}
			}
		}catch (Exception e) {
			logger.error("Exception in getHouseAndInfoOfCadet-GET of HostelController.java:"+e);
			e.printStackTrace();
		}
		return updateHostelOfCadet(request, response, model);
	}

}
