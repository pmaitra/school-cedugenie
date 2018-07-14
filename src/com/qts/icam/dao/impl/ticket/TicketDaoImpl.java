package com.qts.icam.dao.impl.ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.qts.icam.dao.ticket.TicketDao;
import com.qts.icam.model.common.Attachment;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.erp.Leave;
import com.qts.icam.model.ticket.ServiceType;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.ticket.TicketStatus;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;



@Repository
public class TicketDaoImpl implements TicketDao {

	private final static Logger logger = Logger.getLogger(TicketDaoImpl.class);	
	
	@Autowired
	EncryptDecrypt encryptDecrypt; 

	@Autowired
	private SqlSessionFactory SqlSessionFactory;

	

	

	

	@Override
	public String saveTicketService(ServiceType serviceType) {
		SqlSession session = SqlSessionFactory.openSession();
		String status="Successful";
		try {
			logger.info("In saveTicketService() method of TicketDAOImpl");
			serviceType.setTicketServiceObjectId(encryptDecrypt.encrypt("TicketDaoImpl"));
			int insertStatus = session.insert("insertServiceType", serviceType);
			
		} catch (NullPointerException e) {
			logger.error("saveTicketService() In TicketDAOImpl.java: NullPointerException" + e);
			status="Failed";
		} catch (Exception e) {
			logger.error("saveTicketService() In TicketDAOImpl.java: Exception" + e);
			status="Failed";
		} finally {
			session.close();
		}

		return status;
	}

	

	


	

	@Override
	public String deleteTicketService(ServiceType serviceType) {
		SqlSession session = SqlSessionFactory.openSession();
		String status="Successful";
		try {
			logger.info("In deleteTicketService() method of TicketDaoImpl");
			int deleteStatus = session.update("deleteServiceType", serviceType);
		} catch (NullPointerException e) {
			logger.error("deleteTicketService() In TicketDaoImpl.java: NullPointerException"+ e);
			status="Failed";
		} catch (Exception e) {
			logger.error("deleteTicketService() In TicketDaoImpl.java: Exception" + e);
			status="Failed";
		} finally {
			session.close();
		}
		return status;
	}

	@Override
	public String updateTicketService(ServiceType serviceType) {
		SqlSession session = SqlSessionFactory.openSession();
		String status="Successful";
		try {
			logger.info("In updateTicketService() method of TicketDaoImpl");
			session.update("updateServiceType", serviceType);

		} catch (NullPointerException e) {
			logger.error("updateTicketService() In TicketDaoImpl.java: NullPointerException" + e);
			status="Failed";
		} catch (Exception e) {
			logger.error("updateTicketService() In TicketDaoImpl.java: Exception" + e);
			status="Failed";
		} finally {
			session.close();
		}
		return status;
	}


	@Override
	public List<Ticket> getTicketSearchList(Map<String, Object> parameters) {		
		List<Ticket> ticketList = null;	
		SqlSession session = SqlSessionFactory.openSession();
		try {
			logger.info("In getTicketSearchList() method of TicketDaoImpl");
			session = SqlSessionFactory.openSession();
			ticketList = session.selectList("getSearchBasedTicketList", parameters);
		} catch (NullPointerException e) {
			logger.error("getTicketSearchList() In TicketDaoImpl.java: NullPointerException" + e);
		} catch (Exception e) {
			logger.error("getTicketSearchList() In TicketDaoImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return ticketList;
	}

	@Override
	public List<Ticket> getInwardTicketList(String userId) {
		List<Ticket> ticketList = null;
		SqlSession session = SqlSessionFactory.openSession();
		try {
			logger.info("In getInwardTicketList() method of TicketDaoImpl");
			session = SqlSessionFactory.openSession();
			ticketList = session.selectList("selectInwardTicketList", userId);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("getInwardTicketList() In TicketDaoImpl.java: NullPointerException" + e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getInwardTicketList() In TicketDaoImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return ticketList;
	}
	
	/*Added By Naimisha 29082017*/
	@Override
	public List<Ticket> getListClosedTicket(String userId) {
		List<Ticket> ticketList = null;
		SqlSession session = SqlSessionFactory.openSession();
		try {
			logger.info("In getListClosedTicket() method of TicketDaoImpl");
			session = SqlSessionFactory.openSession();
			ticketList = session.selectList("selectListClosedTicket", userId);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("getListClosedTicket() In TicketDaoImpl.java: NullPointerException" + e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getListClosedTicket() In TicketDaoImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return ticketList;
	}



	/*********Added By Naimisha 23052018********/
	@Override
	public List<Ticket> inwardListTicketForTicketAdministrator() {
		List<Ticket> ticketList = null;
		SqlSession session = SqlSessionFactory.openSession();
		try {
			logger.info("In inwardListTicketForTicketAdministrator() method of TicketDaoImpl");
			session = SqlSessionFactory.openSession();
			ticketList = session.selectList("inwardListTicketForTicketAdministrator");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("inwardListTicketForTicketAdministrator() In TicketDaoImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return ticketList;
	}



	@Override
	public List<Ticket> listClosedTicketForForTicketAdministrator() {
		List<Ticket> ticketList = null;
		SqlSession session = SqlSessionFactory.openSession();
		try {
			logger.info("In listClosedTicketForForTicketAdministrator() method of TicketDaoImpl");
			session = SqlSessionFactory.openSession();
			ticketList = session.selectList("listClosedTicketForForTicketAdministrator");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("listClosedTicketForForTicketAdministrator() In TicketDaoImpl.java: Exception" + e);
		} finally {
			session.close();
		}
		return ticketList;
	}
	
	
}
