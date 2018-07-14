package com.qts.icam.dao.impl.venue;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qts.icam.dao.impl.backoffice.BackOfficeDAOImpl;
import com.qts.icam.dao.venue.VenueDAO;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.model.common.Location;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.State;
import com.qts.icam.model.facility.Facility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;

@Repository
public class VenueDAOImpl implements VenueDAO{
private final static Logger logger = Logger.getLogger(VenueDAOImpl.class);
	
	EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public String addZone(Venue venue) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			venue.setObjectId(encryptDecrypt.getBase64EncodedID("VenueDAOImpl"));
			int insertvalue = session.insert("insertZone", venue);
		}catch(Exception e) {
			insertStatus="fail";
			e.printStackTrace();
			logger.error(e);
			
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public List<Venue> getZoneList() {
		List<Venue> zoneList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			zoneList=session.selectList("listZone");
		}catch(Exception e) {
			logger.error(e);
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return zoneList;
	}

	@Override
	public String editZone(Venue venue) {
		String insertStatus="updatesuccess";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			venue.setObjectId(encryptDecrypt.getBase64EncodedID("VenueDAOImpl"));
			int updateValue = session.update("editZone", venue);
		}catch(Exception e) {
			insertStatus="updatefail";
			e.printStackTrace();
			logger.error(e);
			
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public List<State> getStateListAgainstZone(Venue venue) {
		List<State> stateList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			System.out.println("ZoneCode:"+venue.getZoneCode());
			stateList=session.selectList("listStateAgainstZone",venue);
		}catch(Exception e) {
			logger.error(e);
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return stateList;
	}

	@Override
	public String addLocation(Location location) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			location.setObjectId(encryptDecrypt.getBase64EncodedID("VenueDAOImpl"));
			int insertvalue = session.insert("insertLocation", location);
		}catch(Exception e) {
			insertStatus="fail";
			e.printStackTrace();
			logger.error(e);
			
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public List<Location> getLocationList() {
		List<Location> locationList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			locationList=session.selectList("listLocation");
		}catch(Exception e) {
			logger.error(e);
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return locationList;
	}

	@Override
	public String editLocation(Location location) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			location.setObjectId(encryptDecrypt.getBase64EncodedID("VenueDAOImpl"));
			int updateValue = session.update("editLocation", location);
		}catch(Exception e) {
			insertStatus="fail";
			e.printStackTrace();
			logger.error(e);
			
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String inactiveLocation(Location location) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			location.setObjectId(encryptDecrypt.getBase64EncodedID("VenueDAOImpl"));
			int updateValue = session.update("inactiveLocation", location);
		}catch(Exception e) {
			insertStatus="fail";
			e.printStackTrace();
			logger.error(e);
			
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public String addVenue(Venue venue) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			venue.setObjectId(encryptDecrypt.getBase64EncodedID("VenueDAOImpl"));
			int insertvalue = session.insert("insertVenue", venue);
		}catch(Exception e) {
			insertStatus="fail";
			e.printStackTrace();
			logger.error(e);
			
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public List<Venue> getVenueList() {
		List<Venue> venueList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			venueList=session.selectList("listVenue");
		}catch(Exception e) {
			logger.error(e);
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return venueList;
	}

	/**
	 * modified by ranita.sur
	 * changes taken on 05042017*/
	
	@Override
	public String editVenue(Venue venue) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		venue.setObjectId(encryptDecrypt.getBase64EncodedID("VenueDAOImpl"));
		List<Venue> updateList = null;
		try {
			//System.out.println("Status...DAOI...::"+venue.getAvailability());
			//System.out.println("Venue Code...DAOI...::"+venue.getVenueCode());
			updateList = session.selectList("listVenueForUpdateDetails", venue);
			//System.out.println("UpdateListSize"+updateList.size());
			
			if(null==updateList || updateList.size()==0){
				int updateValue = session.update("editVenue", venue);
			}
			if(null!=updateList && updateList.size()!=0){
//					for(Venue venuEdit : updateList){
				for(int i=0; i<updateList.size(); i++){
					int updateValue = session.update("editVenueDetail", venue);
					session.update("editVenue", venue);
					session.commit();
				}
//					}
			}
		}catch(Exception e) {
			insertStatus="fail";
			e.printStackTrace();
			logger.error(e);
			
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public List<Facility> getFacilityList() {
		List<Facility> facilityList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			facilityList=session.selectList("listFacility");
		}catch(Exception e) {
			logger.error(e);
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return facilityList;
	}

	@Override
	public String addVenueFacilityMapping(Venue venue) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			venue.setObjectId(encryptDecrypt.getBase64EncodedID("VenueDAOImpl"));
			//List<Facility>facilityList = venue.getFacilityList();
			int insertvalue = session.insert("insertVenueFacilityMapping", venue);
		}catch(Exception e) {
			insertStatus="fail";
			e.printStackTrace();
			logger.error(e);
			
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public List<Venue> getFacilityListAgainstVenue(String venueCode) {
		List<Venue> venueList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			//System.out.println("venueCode======"+venueCode);
			venueList=session.selectList("listFacilityAgainstVenue",venueCode);
			//System.out.println("size puja==="+venueList.size());
		}catch(Exception e) {
			logger.error(e);
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return venueList;
	}

	@Override
	public String updateVenueFacilityMapping(Venue venue) {
		String updateStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			venue.setObjectId(encryptDecrypt.getBase64EncodedID("VenueDAOImpl"));
			//List<Facility>facilityList = venue.getFacilityList();

			//System.out.println("venue code==="+venue.getVenueCode());
			//System.out.println("facilityCode======="+venue.getFacilityList().get(0).getFacilityCode());
			int deletevalue = session.update("inactiveVenueFacilityMapping", venue);
			//System.out.println("deletevalue==="+deletevalue);
			int insertValue  = session.update("insertVenueFacilityMapping", venue);
		}catch(Exception e) {
			updateStatus="fail";
			e.printStackTrace();
			logger.error(e);
			
		}finally{
			session.close();
		}
		return updateStatus;
	}

	@Override
	public List<Venue> getVenueTypeList() {
		List<Venue> venueTypeList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			venueTypeList = session.selectList("listVenueType");
		}catch(Exception e) {
			logger.error(e);
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return venueTypeList;
	}

	/*@Override
	public List<Venue> getVenueListAgainstVenueType(String venueTypeCode) {
		List<Venue> venueTypeList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			System.out.println("venueTypeCode is ===="+venueTypeCode);
			venueTypeList = session.selectList("listVenueAgainstVenueType",venueTypeCode);
			System.out.println("venueTypeList===="+venueTypeList.size());
		}catch(Exception e) {
			logger.error(e);
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return venueTypeList;
	}*/
	public List<Venue> getVenueListAgainstVenueType(String venueTypeCode) {
		List<Venue> venueTypeList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			//System.out.println("venueTypeCode is ===="+venueTypeCode);
			venueTypeList = session.selectList("listVenueAgainstVenueType",venueTypeCode);
			//System.out.println("venueTypeList===="+venueTypeList.size());
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return venueTypeList;
	}

	@Override
	public String getBuildingAgainstVenue(String venueCode) {
		String buildingName = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			//System.out.println("venueCode is ===="+venueCode);
			buildingName = session.selectOne("buildingAgainstName",venueCode);
			//System.out.println("buildingName===="+buildingName);
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return buildingName;
	}

	/*@Override
	public String insertVenue(Venue venue) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			venue.setObjectId(encryptDecrypt.getBase64EncodedID("VenueDAOImpl"));
			//List<Facility>facilityList = venue.getFacilityList();
			int insertvalue = session.insert("insertIntoVenueAllocation", venue);
		}catch(Exception e) {
			insertStatus="fail";
			e.printStackTrace();
			logger.error(e);
			
		}finally{
			session.close();
		}
		return insertStatus;
	}*/

	@Override
	public List<Venue> getAllocatedVenueList() {
		List<Venue> allocatedVenueList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			allocatedVenueList = session.selectList("listAllAllocatedVenue");
			//System.out.println("allocatedVenueList===="+allocatedVenueList.size());
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return allocatedVenueList;
	}

	@Override
	public Venue getAllocatedVenueAgainstVenue(Venue venue) {
		//List<Venue> allocatedVenueList = null;
		SqlSession session =sqlSessionFactory.openSession();
		Venue venueAllocationDetails = new Venue();
		try{
			String msg  = session.selectOne("checkVenueStatus",venue);
			//System.out.println("msg======="+msg);
			//System.out.println("status=="+venue.getStatus());
			if(null != venue.getStatus()){
				String[] venueAllocationDetailsArray = venue.getStatus().split(";");
				//System.out.println("venueAllocationDetailsArray===="+venueAllocationDetailsArray);
				venueAllocationDetails.setVenueName(venueAllocationDetailsArray[0]);
				venueAllocationDetails.setStartDate(venueAllocationDetailsArray[1]);
				venueAllocationDetails.setEndDate(venueAllocationDetailsArray[2]);
				venueAllocationDetails.setStartTime(venueAllocationDetailsArray[3]);
				venueAllocationDetails.setEndTime(venueAllocationDetailsArray[4]);
				venueAllocationDetails.setStatus(venueAllocationDetailsArray[5]);
			}else{
				venueAllocationDetails.setStatus("NOTALLOCATED");
			}
			
		}catch(Exception e) {
			logger.error(e);
			e.printStackTrace();
			//CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return venueAllocationDetails;
	}

	@Override
	public String insertVenueALlocation(Venue venue) {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			venue.setObjectId(encryptDecrypt.getBase64EncodedID("VenueDAOImpl"));
			//List<Facility>facilityList = venue.getFacilityList();
			int insertvalue = session.insert("insertIntoVenueAllocation", venue);
		}catch(Exception e) {
			insertStatus="fail";
			e.printStackTrace();
			logger.error(e);
			
		}finally{
			session.close();
		}
		return insertStatus;
	}

	@Override
	public List<Venue> getVenueFacilityList() {
		List<Venue> venueFacilityList = null;
		SqlSession session =sqlSessionFactory.openSession();
		//System.out.println("In getVenueFacilityList method of VenueDAOImpl.");
		try{
			venueFacilityList = session.selectList("listVenueFacility");
			if(null != venueFacilityList){
				//System.out.println("Active Venue list Size :"+venueFacilityList.size());
				for(Venue v : venueFacilityList){
					//System.out.println("Venue Name : "+v.getVenueName());
					//System.out.println("Venue Code : "+v.getVenueCode());
				}
			}else{
				//System.out.println("venue list null");
			}
			
			//System.out.println();
		}catch(Exception e) {
			logger.error(e);
			//CustomException.exceptionHandler(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return venueFacilityList;
	}
@Override
	public String inactiveVenue(Venue venue) {
		String status="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			venue.setObjectId(encryptDecrypt.getBase64EncodedID("VenueDAOImpl"));
			session.update("inactiveVenue", venue);	
		}catch(Exception e) {
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			status="fail";
			logger.error(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}
}
