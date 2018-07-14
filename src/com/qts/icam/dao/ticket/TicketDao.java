package com.qts.icam.dao.ticket;

import java.util.List;
import java.util.Map;

import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.erp.Leave;
import com.qts.icam.model.ticket.ServiceType;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.ticket.TicketStatus;



public interface TicketDao {

	String saveTicketService(ServiceType serviceType);

	String deleteTicketService(ServiceType serviceType);

	String updateTicketService(ServiceType serviceType);

	List<Ticket> getTicketSearchList(Map<String, Object> parameters);

	List<Ticket> getInwardTicketList(String userId);
	/*Added By Naimisha 29082017*/

	public List<Ticket> getListClosedTicket(String userId);

	/********Added by Naimisha 23052018**********/
	public List<Ticket> inwardListTicketForTicketAdministrator();

	public List<Ticket> listClosedTicketForForTicketAdministrator();	
	

}
