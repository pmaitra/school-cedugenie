package com.qts.icam.service.impl.ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.qts.icam.dao.common.CommonDao;
import com.qts.icam.dao.ticket.TicketDao;
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.model.erp.Leave;
import com.qts.icam.model.ticket.ServiceType;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.ticket.TicketStatus;
import com.qts.icam.service.ticket.TicketService;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;



@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketDao ticketDao;
	
	@Autowired
	FileUploadDownload fileUploadDownload; 
	
	@Autowired
	CommonDao commonDao;
	
	
	List<Ticket> ticketList = null;
	int pageSize = 10;
	
	private final static Logger logger = Logger.getLogger(TicketServiceImpl.class);
	
	

	@Override
	public String saveTicketService(ServiceType serviceType) {
		return ticketDao.saveTicketService(serviceType);
	}


	
	@Override
	public String deleteTicketService(ServiceType serviceType) {
		return ticketDao.deleteTicketService(serviceType);
	}

	@Override
	public String updateTicketService(ServiceType serviceType) {
		return ticketDao.updateTicketService(serviceType);
	}


	@Override
	public List<Ticket> getTicketSearchList(Map<String, Object> parameters) {		
		ticketList = ticketDao.getTicketSearchList(parameters);
		return ticketList;
	}

	@Override
	public List<Ticket> getInwardTicketList(String userId) {
		ticketList = ticketDao.getInwardTicketList(userId);
		return ticketList;
	}

	@Override
	public List<Ticket> getListClosedTicket(String userId) {
		ticketList = ticketDao.getListClosedTicket(userId);
		return ticketList;
	}


	//Added by Naimisha 23052018//

	@Override
	public List<Ticket> inwardListTicketForTicketAdministrator() {
		return ticketDao.inwardListTicketForTicketAdministrator();
	}



	@Override
	public List<Ticket> listClosedTicketForForTicketAdministrator() {
		return ticketDao.listClosedTicketForForTicketAdministrator();
	}

	
}
