package com.qts.icam.controller.ticket;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
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
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.RepositoryStructure;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.model.erp.Leave;
import com.qts.icam.model.ticket.ServiceType;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.ticket.TicketStatus;
import com.qts.icam.service.administrator.AdministratorService;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.ticket.TicketService;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;



@Controller
@SessionAttributes("sessionObject")
public class TicketController {

	@Autowired
	TicketService ticketService;

	@Autowired
	CommonService commonService;
	
	@Autowired
	FileUploadDownload fileUploadDownload;
	
	@Autowired
	AdministratorService administratorService;
	
	@Value("${root.path}")
	private String rootPath;
	
	@Value("${employeeDoc.path}")
	private String employeeDocFolderPath;
	
	@Value("${ticketAttachments.path}")
	private String ticketAttachmentsPath;
	
	
	@Value("${assignment.path}")
	private String uploadAssignmenetPath;
	
	@Value("${reportNOCConfigFile.path}")
	private String reportNOCConfigFilePath;

	public static Logger logger = Logger.getLogger(TicketController.class);

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/serviceType", method = RequestMethod.GET)
	public ModelAndView serviceTypeGET(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			logger.info("In serviceTypeGET() method of TicketController");
			List<Resource> staffList = commonService.getStaffUserIdList();
			if (staffList != null && staffList.size() != 0) {
				model.addAttribute("userIdList", staffList);
			}
			
			List<Department> departmentFromDB = commonService.selectAllDepartment();
			if (departmentFromDB != null && departmentFromDB.size() != 0) {				
				model.addAttribute("departmentFromDB", departmentFromDB);
			}

			List<ServiceType> serviceTypeList = commonService.getServiceTypeList();
			if (serviceTypeList != null && serviceTypeList.size() != 0) {
				model.addAttribute("serviceTypeList", serviceTypeList);
			}
		} catch (Exception e) {
			logger.error("serviceTypeGET() In TicketController.java: Exception"+ e);
		}
		return new ModelAndView("ticketing/serviceType");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/serviceType", method = RequestMethod.POST)
	public ModelAndView serviceTypePOST(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			ServiceType serviceType,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("In serviceTypePOST() method of TicketController");			
			serviceType.setUpdatedBy(sessionObject.getUserId());
			String status = ticketService.saveTicketService(serviceType);
			List<ServiceType> serviceTypeList=commonService.getServiceTypeList();
			if (serviceTypeList != null && serviceTypeList.size() != 0) {
				model.addAttribute("serviceTypeList", serviceTypeList);
			}

			List<Resource> staffList = commonService.getStaffUserIdList();
			if (staffList != null && staffList.size() != 0) {
				model.addAttribute("userIdList", staffList);
			}
			
			List<Department> departmentFromDB = commonService.selectAllDepartment();
			if (departmentFromDB != null && departmentFromDB.size() != 0) {				
				model.addAttribute("departmentFromDB", departmentFromDB);
			}

		} catch (Exception e) {
			logger.error("serviceTypePOST() In TicketController.java: Exception" + e);
		}
		return new ModelAndView("ticketing/serviceType");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/updateServicetype", method = RequestMethod.POST)
	public ModelAndView updateServicetype(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("saveId") String saveId,
			@ModelAttribute("sessionObject") SessionObject sessionObject
) {
		try {		
			logger.info("In updateServicetype() method of TicketController");
			String oldService = request.getParameter("oldService"+saveId);
			String newService = request.getParameter("newService"+saveId);
			String newOwner = request.getParameter("newOwner"+saveId);
			String newDepartment = request.getParameter("newDepartment"+saveId);
			
			ServiceType serviceType=new ServiceType();
			serviceType.setTicketServiceCode(oldService);
			serviceType.setTicketServiceName(newService);
			Resource r = new Resource();
			r.setUserId(newOwner);
			serviceType.setTicketServiceOner(r);
			
			Department d = new Department();
			d.setDepartmentCode(newDepartment);
			serviceType.setDepartment(d);
			serviceType.setUpdatedBy(sessionObject.getUserId());
			String status = ticketService.updateTicketService(serviceType);
			List<ServiceType> serviceTypeList=commonService.getServiceTypeList();
			if (serviceTypeList != null && serviceTypeList.size() != 0) {
				model.addAttribute("serviceTypeList", serviceTypeList);
			}

			List<Resource> staffList = commonService.getStaffUserIdList();
			if (staffList != null && staffList.size() != 0) {
				model.addAttribute("userIdList", staffList);
			}
			
			List<Department> departmentFromDB = commonService.selectAllDepartment();
			if (departmentFromDB != null && departmentFromDB.size() != 0) {				
				model.addAttribute("departmentFromDB", departmentFromDB);
			}

		} catch (Exception e) {
			logger.error("TicketController() In TicketController.java: Exception" + e);
		}
		return new ModelAndView("ticketing/serviceType");
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/updateServicetype", method = RequestMethod.POST, params = "delete")
	public ModelAndView deleteServicetype(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			ServiceType serviceType, @RequestParam("radio") String radio,
			@ModelAttribute("sessionObject") SessionObject sessionObject
	) {
		try {
			logger.info("In deleteServicetype() method of TicketController");
			serviceType.setTicketServiceName(radio);
			serviceType.setUpdatedBy(sessionObject.getUserId());
			String status = ticketService.deleteTicketService(serviceType);
			List<ServiceType> serviceTypeList=commonService.getServiceTypeList();
			if (serviceTypeList != null && serviceTypeList.size() != 0) {
				model.addAttribute("serviceTypeList", serviceTypeList);
			}

			List<Resource> staffList = commonService.getStaffUserIdList();
			if (staffList != null && staffList.size() != 0) {
				model.addAttribute("userIdList", staffList);
			}
			List<Department> departmentFromDB = commonService.selectAllDepartment();
			if (departmentFromDB != null && departmentFromDB.size() != 0) {				
				model.addAttribute("departmentFromDB", departmentFromDB);
			}

		} catch (Exception e) {
			logger.error("deleteServicetype() In TicketController.java: Exception"+ e);
		}
		return new ModelAndView("ticketing/serviceType");
	}
	
	
	//Modified By Naimisha29082017

	@RequestMapping(value = "/listTicketPagination", method = RequestMethod.GET)
	public ModelAndView listTicketPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("In listTicketPagination() method of TicketController");
			mav = new ModelAndView("ticketing/listTicket");
			logger.info("In TicketController listTicketPagination() method: calling getTicketListPaging(HttpServletRequest request) in TicketService.java");
			PagedListHolder<Ticket> pagedListHolder = commonService.getTicketListPaging(request);
			if (pagedListHolder != null) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in TicketController getTicketListPaging() method for Exception: ", e);
		}
		return mav;
	}

	
	
	@RequestMapping(value = "/closedTicketListPagination", method = RequestMethod.GET)
	public ModelAndView closedTicketListPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("In closedTicketListPagination() method of TicketController");
			mav = new ModelAndView("ticketing/closedTicketList");
			logger.info("In TicketController closedTicketListPagination() method: calling closedTicketListPaging(HttpServletRequest request) in TicketController.java");
			PagedListHolder<Ticket> pagedListHolder = commonService.closedTicketListPaging(request);
			if (pagedListHolder != null) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in TicketController closedTicketListPagination() method for Exception: ", e);
		}
		return mav;
	}

	
//	@RequestMapping(value = "/editTicket", method = RequestMethod.POST, params = "ticketSearchSubmit")
//	public ModelAndView searchTicket(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
//		logger.info("In searchTicket() method of TicketController");
//		ModelAndView mav = new ModelAndView("ticketing/listTicket");
//		//String strKey = request.getParameter("query");
//		String strKey = "status";
//		String strValue = request.getParameter("data").trim();
//		logger.info("calling searchTicket() TicketController with searching parameter. Key:=" + strKey + " and Value:=" + strValue);
//		Map<String, Object> parameters = new HashMap<String, Object>();	
//		
//		if (strKey.equalsIgnoreCase("status")) {
//			parameters.put("status", strValue);
//		}		
//		try {
//			logger.info("In listTicketGET() method of TicketController");
//			List<Ticket> ticketList = ticketService.getTicketSearchList(parameters);
//			if (ticketList != null && ticketList.size() != 0) {
//				logger.info("In TicketController searchTicket() method: calling getTicketListPaging(HttpServletRequest request) in TicketService.java");
//				PagedListHolder<Ticket> pagedListHolder = commonService.getTicketListPaging(request);
//				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
//				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
//				mav.addObject("total", pagedListHolder.getNrOfElements());
//				mav.addObject("pagedListHolder", pagedListHolder);
//			}
//			model.addAttribute("ticketList", ticketList);
//		} catch (NullPointerException e) {
//			logger.error("Error in TicketController searchTicket() method for NullPointerException: ",e);
//		} catch (Exception e) {
//			logger.error("Error in TicketController searchTicket() method for Exception: ",e);
//		}		
//		return mav;
//	}
	
	
	
	
	@RequestMapping(value = "/ticketingProcessFlow", method = RequestMethod.GET)
	public ModelAndView showBackOfficeProcessFlow(ModelMap model) {
		String strView = null;
		try {
			logger.info("In TicketController showBackOfficeProcessFlow() method");
				strView = "ticketing/ticketingProcessFlow";	
		} catch (NullPointerException e) {
			logger.error("Error in TicketController showBackOfficeProcessFlow() method for NullPointerException: ", e);
		} catch (Exception e) {
			logger.error("Error in TicketController showBackOfficeProcessFlow() method for Exception: ", e);
		}
		return new ModelAndView(strView);
	}
	

	@RequestMapping(value = "/downloadTicketRelatedAttachments", method = RequestMethod.GET)
	public ModelAndView downloadTicketRelatedAttachments(HttpServletRequest request,HttpServletResponse response, ModelMap model) {		
		
		try {			
			String ticketNumber = request.getParameter("ticketNumber");
			String fileName = request.getParameter("fileName");
			//String affectedUser = request.getParameter("affectedUser");
			RepositoryStructure repositoryStructure = administratorService.getRespositoryStructure();
			String repository = repositoryStructure.getRepositoryPathName();
			
			String folderName = repository+"/" + ticketAttachmentsPath +ticketNumber+"/";
			String returnMessage = fileUploadDownload.downloadFile(response, folderName, fileName);
			if(returnMessage==null){
				model.addAttribute("ResultError", "File not available");
			}
		} catch (Exception e) {
			
			String error = "file  not found";
			logger.error("Exception in download() method in TicketController:  ",	e);
			return new ModelAndView("common/errorpage", "ResultError", error);
		}
		return null;
	}
	

	@RequestMapping(value = "/getDepartmentWiseUserList", method = RequestMethod.GET)
		public @ResponseBody
		String getStateNames(@RequestParam("department") String department) {
			logger.info("In TicketController getDepartmentWiseUserList() method: calling getDepartmentWiseUserList(String department) in TicketService.java");
			List<Resource> staffList = commonService.getDepartmentWiseUserList(department);
			StringBuffer json = new StringBuffer();
			if (staffList != null && staffList.size() != 0) {
				logger.info("Getting User Id Department Wise");
				for (Resource resource : staffList) {
					if (json.length() != 0) {
						json.append(";");
					} else {
					}
					json.append(resource.getUserId());
				}
			} else {
				logger.info("In TicketController getDepartmentWiseUserList() method");
			}
			return (new Gson().toJson(json.toString()));
	}
	
	
/*	Added By Naimisha 29082017*/
	@RequestMapping(value = "/listClosedTicket", method = RequestMethod.GET)
	public ModelAndView listClosedTicket(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model, @ModelAttribute("sessionObject") SessionObject sessionObject) {
		ModelAndView mav = new ModelAndView("ticketing/listTicket");
		try {
			logger.info("In listClosedTicket() method of TicketController");
			List<Ticket> ticketList = ticketService.getListClosedTicket(sessionObject.getUserId());
			if (ticketList != null && ticketList.size() != 0) {
				model.addAttribute("ticketList", ticketList);
				/*logger.info("In TicketController inwardListTicket() method: calling getTicketListPaging(HttpServletRequest request) in TicketService.java");
				PagedListHolder<Ticket> pagedListHolder = commonService.getTicketListPaging(request);
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);*/
			}
		} catch (Exception e) {
			logger.error("listClosedTicket() In TicketController.java: Exception"+ e);
		}
		return mav;
	}
	
	/**************Added by naimisha 23052018**************/
	
	@RequestMapping(value = "/inwardListTicketForTicketAdministrator", method = RequestMethod.GET)
	public ModelAndView inwardListTicketForTicketAdministrator(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model, @ModelAttribute("sessionObject") SessionObject sessionObject) {
		ModelAndView mav = new ModelAndView("ticketing/listTicket");
		try {
			logger.info("In inwardListTicketForTicketAdministrator() method of TicketController");
			List<Ticket> ticketList = ticketService.inwardListTicketForTicketAdministrator();
			if (ticketList != null && ticketList.size() != 0) {
				model.addAttribute("ticketList", ticketList);
				
			}
		} catch (Exception e) {
			logger.error("inwardListTicketForTicketAdministrator() In TicketController.java: Exception"+ e);
		}
		return mav;
	}
	
	@RequestMapping(value = "/listClosedTicketForForTicketAdministrator", method = RequestMethod.GET)
	public ModelAndView listClosedTicketForForTicketAdministrator(HttpServletRequest request, HttpServletResponse response, 
			ModelMap model, @ModelAttribute("sessionObject") SessionObject sessionObject) {
		ModelAndView mav = new ModelAndView("ticketing/listTicket");
		try {
			logger.info("In listClosedTicketForForTicketAdministrator() method of TicketController");
			List<Ticket> ticketList = ticketService.listClosedTicketForForTicketAdministrator();
			if (ticketList != null && ticketList.size() != 0) {
				model.addAttribute("ticketList", ticketList);
				
			}
		} catch (Exception e) {
			logger.error("listClosedTicketForForTicketAdministrator() In TicketController.java: Exception"+ e);
		}
		return mav;
	}
}
