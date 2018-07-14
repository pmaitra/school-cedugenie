package com.qts.icam.dao.impl.facility;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.qts.icam.dao.facility.FacilityDao;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Room;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.facility.Facility;
import com.qts.icam.model.hostel.Hostel;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.utility.Utility;

@Repository
public class FacilityDaoImpl implements FacilityDao{
	private final static Logger logger = Logger.getLogger(FacilityDaoImpl.class);
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
	public List<SocialCategory> getSocialCategoryList() {
		SqlSession session = sqlSessionFactory.openSession();
		List <SocialCategory> socialCategoryList = null;
		try{
			logger.info("In getSocialCategoryList() of FacilityDaoImpl.java");
			socialCategoryList = session.selectList("getSocialCategoryList");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e);
		}finally{
			session.close();
		}	
		return socialCategoryList;
	}

	@Override
	public void saveFacilities(Facility facility) 
	{
		logger.info("In saveFacilities(Facility facility) In FacilityDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		Utility util = new Utility();
		try 
		{
			facility.setFacilityObjectId(util.getBase64EncodedID("FacilityDaoImpl"));
			session.insert("insertFacilities",facility);
			session.commit();
		} catch (NullPointerException e) 
		{
			e.printStackTrace();
		} catch (Exception e) 
		{
			e.printStackTrace();
		} finally 
		{
			session.close();
		}
	}

	@Override
	public List<Facility> getAllFacilities() 
	{
		logger.info("In getAllFacilities() in facilityDaoImpl");
		SqlSession session = sqlSessionFactory.openSession();
		List<Facility> allFacilities = null;
		try
		{
			allFacilities = session.selectList("getAllFacilities");
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
			session.close();
		}
		return allFacilities;
	}

	@Override
	public Facility getFacilityDetails(String facilityCode) 
	{
		logger.info("In getFacilityDetails(String facilityCode) of facilityDaoImpl.java");
		SqlSession session = sqlSessionFactory.openSession();
		Facility facility = new Facility();
		try
		{
			facility = session.selectOne("getFacilityDetailsForUnpaid", facilityCode);
			if(facility.isIspaid()== true)
			{	
				facility = session.selectOne("getFacilityDetailsForPaid", facilityCode);
			}
		}
		catch (Exception e)
		{
			logger.error(e);
			e.printStackTrace();
		}
		finally 
		{
			session.close();
		}
		return facility;
	}

	@Override
	public void updateFacilityDetails(Facility facility) 
	{
		logger.info("In updateFacilityDetails(Facility facility) In FacilityDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		Utility util = new Utility();
		try 
		{
			//System.out.println("facilityCode in dao:"+facility.getFacilityCode());
			facility.setFacilityObjectId(util.getBase64EncodedID("FacilityDaoImpl"));
			//System.out.println("socialcategoryList Size:"+facility.getSocialCategoryList().size());
			String status = session.selectOne("selectPreviousPaymentStatus", facility);
			if(status.equalsIgnoreCase("t"))
			{
				if(facility.isIspaid()== false)
				{
					//System.out.println("anup1");
					session.update("updateFacilityDetailsForPaidToUnpaid", facility);
				}
				else
				{
					//System.out.println("anup2");
					//System.out.println("code1:"+facility.getSocialCategoryList().get(0).getSocialCategoryCode()+"\tamount1:"+facility.getSocialCategoryList().get(0).getAmount());
					//System.out.println("code2:"+facility.getSocialCategoryList().get(1).getSocialCategoryCode()+"\tamount2:"+facility.getSocialCategoryList().get(1).getAmount());
					//System.out.println("code3:"+facility.getSocialCategoryList().get(2).getSocialCategoryCode()+"\tamount3:"+facility.getSocialCategoryList().get(2).getAmount());
					//System.out.println("code4:"+facility.getSocialCategoryList().get(3).getSocialCategoryCode()+"\tamount4:"+facility.getSocialCategoryList().get(3).getAmount());
					session.update("updateFacilityForPaid",facility);
					//System.out.println("anup6");
					session.insert("insertFacilityChargeForUnpaidToPaid", facility);
				}
			}
			else
			{
				if(facility.isIspaid()== false)
				{
					//System.out.println("anup3");
					session.update("updateFacilityDetailsForUnpaid",facility);
				}
				else
				{
					//System.out.println("anup4");
					session.update("updateFacilityForUnpaidToPaid",facility);
					//System.out.println("anup5");
					session.insert("insertFacilityChargeForUnpaidToPaid", facility);
				}
			}
			session.commit();
		} catch (NullPointerException e) 
		{
			e.printStackTrace();
		} catch (Exception e) 
		{
			e.printStackTrace();
		} finally 
		{
			session.close();
		}
	}

	@Override
	public List<Venue> getVenueList() {
		logger.info("In getVenueList() of facilityDaoImpl.java");
		SqlSession session = sqlSessionFactory.openSession();
		List<Venue> venueList = null;
		try{
			venueList = session.selectList("getAllVenues");
		}catch(NullPointerException e){
			e.printStackTrace();
			logger.error(e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally {
			session.close();
		}
		return venueList;
	}

	
	@Override
	public String getBlocksOfVenue(String venueName) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Venue> blockList = null;
		String allBlocks = null;
		try {
			//System.out.println("venue:"+venueName);
			blockList = session.selectList("getBlocksOfVenue", venueName);
			StringBuilder sb = new StringBuilder();
			for (Venue v : blockList) {
				sb.append(v.getVenueCode() + "*");
				sb.append(v.getVenueName() + "#");
				allBlocks = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch (Exception e) {
			logger.error("getAvailableRoomOfVenue() method in FacilityDaoImpl", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return allBlocks;
	}
	
	@Override
	public String getAvailableRoomOfBlock(String block) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Venue> roomNoList = null;
		String output = null;
		try {
			//System.out.println("block:"+block);
			roomNoList = session.selectList("getAvailableRoomOfBlock", block);
			StringBuilder sb = new StringBuilder();
			for (Venue v : roomNoList) {
				sb.append(v.getVenueCode() + "*");
				sb.append(v.getRoomNo() + "#");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch (Exception e) {
			logger.error("getAvailableRoomOfBlock() method in FacilityDaoImpl", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return output;
	}

	@Override
	public String getAvailableBedsInRoom(String roomNo) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Room> bedCount = null;
		String output = null;
		try {
			//System.out.println("roomNo:"+roomNo);
			bedCount = session.selectList("getAvailableBedsInRoom", roomNo);
			StringBuilder sb = new StringBuilder();
			for (Room v : bedCount) {
				sb.append(v.getBedVaccent() + "*");
				sb.append(v.getBedTotal() + "#");
				output = sb.toString().substring(0, sb.toString().length() - 1);
			}
		} catch (Exception e) {
			logger.error("getAvailableRoomOfBlock() method in FacilityDaoImpl", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return output;
	}

	@Override
	public List<Resource> getResourceIdList() {
		logger.info("In getResourceIdList() of facilityDaoImpl.java");
		SqlSession session = sqlSessionFactory.openSession();
		List<Resource> resourceIdList = null;
		try{
			resourceIdList = session.selectList("getResourceIdList");
		}catch(NullPointerException e){
			e.printStackTrace();
			logger.error(e);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally {
			session.close();
		}
		return resourceIdList;
	}
	
	@Override
	public Resource getResourceDetails(String userId) {
		logger.info("getResourceDetails(String userId) In FacilityDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		Resource resource = null;
		try {
			//System.out.println("userId::"+userId);
			resource = session.selectOne("getResourceDetails", userId);
		} catch (NullPointerException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resource;
	}
	
	@Override
	public void submitAssignedVenueToResource(Venue venue) {
		logger.info("saveStudentRoomHostelMapping(Hostel hostel) In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		Utility util = new Utility();
		try {
			venue.setObjectId(util.getBase64EncodedID("FacilityDAOImpl"));
			/*venue.setUpdatedBy(userId);
			venue.setHostelId(hostel.getResource().getRollNumber());
			venue.setHostelDesc(hostel.getRoom().getRoomName());*/
			
			session.insert("assignVenueToResource", venue);
			session.update("updateResource", venue);
			session.commit();
			String code = venue.getRoomNo();
			session.update("updateVacancies", code);
			session.commit();
		} catch (NullPointerException e) {
			e.printStackTrace(); 
			logger.info("saveStudentRoomHostelMapping(Hostel hostel) In HostelDAOImpl.java: NullPointerException");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("saveStudentRoomHostelMapping(Hostel hostel) In HostelDAOImpl.java: Exception");
		} finally {
			session.close();
		}
	}

	@Override
	public List<Venue> getListVenuesAssignedToResource() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Venue> venueList = null;
		try{
			venueList = session.selectList("getListVenuesAssignedToResource");
		}catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return venueList;
	}

	@Override
	public void deAllocateVenueFromResource(Venue venue) {
		SqlSession session = sqlSessionFactory.openSession();
		try{
			session.update("deAllocateVenueFromResource",venue);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	@Override
	public void facilityDeactivation(String facilityCode) {
		SqlSession session = sqlSessionFactory.openSession();
		try{
			//System.out.println("facilityCode::"+facilityCode);
			session.update("facilityDeactivation",facilityCode);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}