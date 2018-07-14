package com.qts.icam.dao.impl.hostel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.qts.icam.dao.hostel.HostelDAO;
import com.qts.icam.model.common.HostelType;
import com.qts.icam.model.common.Room;
import com.qts.icam.model.common.RoomFacility;
import com.qts.icam.model.common.RoomType;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.Standard;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.hostel.Hostel;
import com.qts.icam.model.hostel.HostelFacility;
import com.qts.icam.model.hostel.House;
import com.qts.icam.model.hostel.HouseMaster;
import com.qts.icam.model.hostel.HouseResidenceMapping;
import com.qts.icam.model.hostel.HouseType;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.encryptdecrypt.EncryptDecrypt;

import com.qts.icam.model.common.Address;
import com.qts.icam.model.common.HostelExpense;
import com.qts.icam.model.common.Resource;

@Repository
public class HostelDAOImpl implements HostelDAO {
	private final static Logger logger = Logger.getLogger(HostelDAOImpl.class);
	
	EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<HostelFacility> getHostelFacility() throws CustomException {
		List<HostelFacility> hostelFavilityList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			hostelFavilityList=session.selectList("selectHostelFacility");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return hostelFavilityList;
	}

	@Override
	public String addhostelFacility(HostelFacility hostelFacility) throws CustomException {
		String insertStatus="Insert Successful";
		/*SqlSession session =sqlSessionFactory.openSession();
		try{
			hostelFacility.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("insertHostelFacility", hostelFacility);
		}catch(Exception e) {
			insertStatus="Insert Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}*/
		return insertStatus;
	}

	@Override
	public HostelFacility getHostelFacilityDetails(String hostelFacilityCode) throws CustomException {
		HostelFacility hostelFavility = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			hostelFavility=session.selectOne("selectHostelFacilityDetails", hostelFacilityCode);
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return hostelFavility;
	}

	@Override
	public String editHostelFacility(HostelFacility hostelFacility) throws CustomException {
		/*String status="Update Successful";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			hostelFacility.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			if(null!=hostelFacility.getOldHostelList() && hostelFacility.getOldHostelList().size()!=0){
				for(Hostel h:hostelFacility.getOldHostelList()){
					System.out.println("Inactive For "+hostelFacility.getHostelFacilityCode()+"\t\t"+h.getHostelCode());
				}
				session.update("inactiveHostelFacilityMapping", hostelFacility);				
			}
			System.out.println(hostelFacility.getHostelList());
			if(null!=hostelFacility.getHostelList() && hostelFacility.getHostelList().size()!=0){
				for(Hostel newHostel:hostelFacility.getHostelList()){
					HostelFacility hostelFacilityParam=new HostelFacility();
					hostelFacilityParam.setHostel(newHostel.getHostelCode());
					hostelFacilityParam.setHostelFacilityCode(hostelFacility.getHostelFacilityCode());
					hostelFacilityParam.setUpdatedBy(hostelFacility.getUpdatedBy());
					HostelFacility checker=session.selectOne("selectInactiveHostelFacilityMapping", hostelFacilityParam);
					System.out.print("Check Availability For "+hostelFacilityParam.getHostelFacilityCode()+"\t\t"+hostelFacilityParam.getHostel());
					if(null!=checker && null!=checker.getHostel()){
						System.out.println("\t\tAvailable");
						session.insert("updateHostelFacilityMapping", hostelFacilityParam);
					}else{
						System.out.println("\t\tNotAvailable");
						hostelFacilityParam.setObjectId(hostelFacility.getObjectId());
						session.update("insertNewHostelFacilityMapping", hostelFacilityParam);
					}
				}
			}
			session.update("updateHostelFacility", hostelFacility);
		}catch(Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			status="Update Failed";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}*/
		return null;
	}
	
	@Override
	public List<HostelFacility> getSearchBasedHostelFacility(
			Map<String, Object> parameters) throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<HostelFacility> hostelFacility = null;
		try {
			hostelFacility = session.selectList("selectHostelFacility",parameters);
		} catch (Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		} finally {
			session.close();
		}
		return hostelFacility;
	}
	
	@Override
	public List<Student> getStudentListForEachHotel() throws CustomException {
		SqlSession session =sqlSessionFactory.openSession();
		List<Student> studentList = null;
		try{
			studentList=session.selectList("selectAllStudentsForEveryHostel");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return studentList;
	}

	/**@author anup.roy*
	 * total hostel portion will start form here*/
	
	@Override
	public List<HostelType> getHostelTypeList() {
		logger.info("getHostelTypeList() In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		List<HostelType> hostelTypeList = null;
		try {
			hostelTypeList = session.selectList("getHostelTypeList");
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("getHostelTypeList() In HostelDAOImpl.java: NullPointerException", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getHostelTypeList() In HostelDAOImpl.java: Exception", e);
		} finally {
			session.close();
		}
		return hostelTypeList;
	}
	
	@Override
	public List<Hostel> getHostel(){
		List<Hostel> hostelList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			hostelList=session.selectList("selectHostel");
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}finally{
			session.close();
		}
		return hostelList;
	}
	
	@Override
	public String addHostel(Hostel hostel) throws CustomException {
		String insertStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			hostel.setObjectId(encryptDecrypt.getBase64EncodedID("BackOfficeDAOImpl"));
			session.insert("insertHostel", hostel);
		}catch(Exception e) {
			insertStatus="fail";
			logger.error(e);
			CustomException.exceptionHandler(e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return insertStatus;
	}
	
	@Override
	public String editHostel(Hostel hostel) throws CustomException {
		String updateStatus="success";
		SqlSession session =sqlSessionFactory.openSession();
		try{
			session.update("updateHostel", hostel);
		}catch(Exception e) {
			updateStatus="fail";
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}		
		return updateStatus;
	}

	@Override
	public List<RoomType> getRoomTypeList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<RoomType> roomTypeList = null;
		try {
			roomTypeList = session.selectList("selectRoomType");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return roomTypeList;
	}

	@Override
	public List<SocialCategory> getSocialCategoryList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<SocialCategory> categoryList = null;
		try {
			categoryList = session.selectList("getSocialCategoryList");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return categoryList;
	}

	@Override
	public void saveRoomType(RoomType roomType) {
		SqlSession session = sqlSessionFactory.openSession();
		Utility util = new Utility();
		try {
			roomType.setRoomTypeObjectId(util.getBase64EncodedID("HostelDAOImpl"));
			session.insert("insertRoomType", roomType);
			session.commit();
			
			for(SocialCategory sc:roomType.getSocialCategoryList()){
				sc.setObjectId(util.getBase64EncodedID("HostelDAOImpl"));
				session.insert("insertRoomTypeCharge", sc);
			}
			session.commit();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@Override
	public RoomType getRoomTypeDetails(String roomType) {
		SqlSession session = sqlSessionFactory.openSession();
		RoomType roomTypeDetails = null;
		try {
			roomTypeDetails = session.selectOne("selectRoomTypeDetails",roomType);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return roomTypeDetails;
	}

	@Override
	public void updateRoomType(RoomType roomType) {
		SqlSession session = sqlSessionFactory.openSession();
		Utility util = new Utility();
		try {
			session.update("inactiveRoomTypeCharge", roomType);
			session.insert("updateRoomType", roomType);
			session.commit();
			for(SocialCategory sc:roomType.getSocialCategoryList()){
				sc.setObjectId(util.getBase64EncodedID("HostelDAOImpl"));
				session.insert("insertRoomTypeCharge", sc);
			}
			session.commit();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void saveHostelFacilities(HostelFacility hostelFacility) {
		logger.info("saveHostelFacilities(HostelFacility hostelFacility) In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		Utility util = new Utility();
		try {
			List<Hostel> hostelList = new ArrayList<Hostel>();
			hostelList = hostelFacility.getHostelList();
			for (Hostel hostelName : hostelList) {
				hostelFacility.setHostelFacilityObjectId(util.getBase64EncodedID("HostelDAOImpl"));
				hostelFacility.setHostelName(hostelName.getHostelName());
		//		hostelFacility.setHostelFacilityCode(hostelFacility.getHostelFacilityCode().toUpperCase());
				session.insert("insertHostelFacilities", hostelFacility);
				session.commit();
				for (SocialCategory sc : hostelFacility.getSocialCategoryList()) {
					hostelFacility.setSocialCategory(sc);
					//System.out.println("hostel:"+hostelFacility.getHostelName()+"facility name:"+hostelFacility.getHostelFacilityName());
					session.insert("insertHostelFacilitiesCharge",hostelFacility);
					session.commit();
				}
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@Override
	public List<HostelFacility> getHostelFacilityList() {
		logger.info("saveHostelFacilityList() In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		List<HostelFacility> hostelFacilityList = null;
		try {
			hostelFacilityList = session.selectList("selectHostelFacilitiesList");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return hostelFacilityList;
	}

	@Override
	public HostelFacility getHostelFacilities(String hostelFacilityCode) {
		logger.info("getHostelFacilities(HostelFacility hostelFacility) In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		HostelFacility hostelFacilityObj = new HostelFacility();
		try {
			//System.out.println("code:"+hostelFacilityCode);
			hostelFacilityObj = session.selectOne("selectHostelFacility",hostelFacilityCode);
			//System.out.println(hostelFacilityObj.getHostelFacilityCode());
			//for(Hostel h:hostelFacilityObj.getHostelList())
				//System.out.println("    "+h.getHostelCode());
			//for(SocialCategory s:hostelFacilityObj.getSocialCategoryList())
				//System.out.println("    "+s.getSocialCategoryName()+"    "+s.getAmount());

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return hostelFacilityObj;
	}

	@Override
	public List<RoomFacility> getRoomFacilitiesList() {
		logger.info("getRoomFacilitiesList() In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		List<RoomFacility> roomFacilityList = null;
		try {
			roomFacilityList = session.selectList("hostelRoomFcilityList");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return roomFacilityList;
	}

	@Override
	public void saveRoomFacilities(Room room) {
		logger.info("In saveRoomFacilities(List<Room> roomList) In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		List<RoomFacility> roomFacilityList = null;
		Utility util = new Utility();
		try {
			room.setRoomObjectId(util.getBase64EncodedID("HostelDAOImpl"));
			@SuppressWarnings("unused")
			int roomInsert = session.insert("insertRoom", room);
			session.commit();
			roomFacilityList = room.getRoomFacilityList();
			for (RoomFacility roomFacilityDetails : roomFacilityList) {
				roomFacilityDetails.setRoomCode(room.getRoomName());
				//System.out.println("hostelRoomCOde:"+roomFacilityDetails.getRoomCode());
				roomFacilityDetails.setRoomFacilityObjectId(util.getBase64EncodedID("HostelDAOImpl"));
				@SuppressWarnings("unused")
				int insertInfo = session.insert("editRoomFacility",roomFacilityDetails);
				session.commit();
			}
			RoomType rt=session.selectOne("checkRoomAvailability",room);
			if(rt!=null && rt.getStatus()!=null){
				room.setRoomCode(rt.getStatus());
				session.update("updateRoomAvailability", room);
			}else{
				session.insert("insertRoomAvailability", room);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public List<Room> getRoomList() {
		logger.info("In getRoomList() In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		List<Room> roomList = null;
		try {
			roomList = session.selectList("roomList");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return roomList;
	}

	@Override
	public Hostel getRoomDetails(Room room) {
		logger.info("In getRoomDetails(Room room) In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		Room roomObj = new Room();
		Room roomObjForFacility = new Room();
		Hostel hostel = new Hostel();
		List<RoomFacility> roomFacilityList = null;
		List<RoomFacility> roomFacilityListForRoom = null;
		try {
			List<RoomFacility> roomFacilityListForJsp = new ArrayList<RoomFacility>();
			Map<String, Object> roomFacilityMapDB = new HashMap<String, Object>();
			roomFacilityList = session.selectList("hostelRoomFcilityList");
			roomObj = session.selectOne("editRoomAndFacilitiesList", room);

			if (roomObj != null) {
				roomFacilityListForRoom = roomObj.getRoomFacilityList();

				for (RoomFacility roomFacilityForRoom : roomFacilityListForRoom) {
					roomFacilityMapDB.put(roomFacilityForRoom.getRoomFacilityCode(),roomFacilityForRoom.getRoomFacilityCode());
				}
				for (RoomFacility roomFacility : roomFacilityList) {
					RoomFacility roomFacilityObj = new RoomFacility();
					RoomFacility roomFacilityObjForQuantityDB = new RoomFacility();
					if (roomFacilityMapDB != null && roomFacilityMapDB.size() != 0) {
						if (roomFacilityMapDB.containsKey(roomFacility.getRoomFacilityCode())) {
							if (roomFacilityMapDB.get(roomFacility.getRoomFacilityCode()).equals(roomFacility.getRoomFacilityCode())) {
								roomFacility.setRoom(room);
								roomFacilityObjForQuantityDB = session.selectOne("selectFacilityQuantity",roomFacility);
								roomFacilityObj.setRoomFacilityCode(roomFacility.getRoomFacilityCode());
								roomFacilityObj.setRoomFacilityName(roomFacility.getRoomFacilityName());
								roomFacilityObj.setRoomFacilityQuantity(roomFacilityObjForQuantityDB.getRoomFacilityQuantity());
								roomFacilityObj.setStatus("Checked");
							} else {
								roomFacilityObj.setRoomFacilityCode(roomFacility.getRoomFacilityCode());
								roomFacilityObj.setRoomFacilityName(roomFacility.getRoomFacilityName());
							}

						} else {
							roomFacilityObj.setRoomFacilityCode(roomFacility.getRoomFacilityCode());
							roomFacilityObj.setRoomFacilityName(roomFacility.getRoomFacilityName());
						}
					}
					roomFacilityListForJsp.add(roomFacilityObj);
				}
				roomObjForFacility.setRoomFacilityList(roomFacilityListForJsp);
				roomObjForFacility.setRoomCode(roomObj.getRoomCode());
				roomObjForFacility.setRoomName(roomObj.getRoomName());
				roomObjForFacility.setBedTotal(roomObj.getBedTotal());
				roomObjForFacility.setRoomTypeList(roomObj.getRoomTypeList());
				roomObjForFacility.setRoomDesc(roomObj.getRoomDesc());
				roomObjForFacility.setHostelName(roomObj.getHostelName());

			} else {
				roomObjForFacility = session.selectOne("selectRoomForNoFacility", room);
				for (RoomFacility roomFacility : roomFacilityList) {
					RoomFacility roomFacilityObj = new RoomFacility();
					roomFacilityObj.setRoomFacilityCode(roomFacility.getRoomFacilityCode());
					roomFacilityObj.setRoomFacilityName(roomFacility.getRoomFacilityName());
					roomFacilityListForJsp.add(roomFacilityObj);

				}
				roomObjForFacility.setRoomFacilityList(roomFacilityListForJsp);
			}
			List<Resource> resourceList = new ArrayList<Resource>();
			List<Resource> resourceListForHostelRoom = new ArrayList<Resource>();
			resourceList = session.selectList("hostelSelectStudentList", room);
			for (Resource resource : resourceList) {
				String userId = resource.getUserId();
				Address address = new Address();
				address = session.selectOne("selectPresentAddressForStudent",userId);

				resource.setAddress(address);
				resourceListForHostelRoom.add(resource);
			}
			hostel.setResourceList(resourceListForHostelRoom);
			hostel.setRoom(roomObjForFacility);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return hostel;
	}

	@Override
	public List<Hostel> getStudentHostelList() {
		logger.info("getStudentHostelList() In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		List<Hostel> hostelListFromDB = null;
		try {
			List<Resource> studentListFromDB = session.selectList("getStudentList");
			hostelListFromDB = session.selectList("getHostelList");
			if (hostelListFromDB != null && hostelListFromDB.size() != 0) {
				hostelListFromDB.get(0).setResourceList(studentListFromDB);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		//System.out.println("studentList::"+hostelListFromDB.get(0).getResourceList()+"\t size::"+hostelListFromDB.get(0).getResourceList().size());
		return hostelListFromDB;
	}

	@Override
	public Hostel getSelectedHostelRoomTypeByStudent(Resource resource) {
		logger.info("getSelectedHostelRoomTypeByStudent() In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		Hostel hostel = null;
		try {
			hostel = session.selectOne("selectHostelRoomTypeByStudent",resource);
			if(hostel!=null){
				Room room = new Room();
				room.setHostelName(hostel.getHostelCode());
				if(hostel.getRoom()!=null){
					room.setRoomTypeName(hostel.getRoom().getRoomTypeCode());
				}
				List<Room> roomNameList = session.selectList("getRoomList", room);
				hostel.setRoomList(roomNameList);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("getSelectedHostelRoomTypeByStudent() In HostelDAOImpl.java: NullPointerException", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getSelectedHostelRoomTypeByStudent() In HostelDAOImpl.java: Exception", e);
		} finally {
			session.close();
		}
		return hostel;
	}

	@Override
	public Resource getStudentDetails(String rollNumber) {
		logger.info("getStudentDetails(String registrationId) In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		Resource resource = null;
		try {
			//System.out.println("roll::"+rollNumber);
			resource = session.selectOne("getStudentDetails", rollNumber);
		} catch (NullPointerException e) {
			logger.error("getStudentDetails(String registrationId) In HostelController.java: NullPointerException"+ e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("getStudentDetails(String registrationId) In HostelController.java: Exception"+ e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resource;
	}

	@Override
	public List<RoomType> getRoomTypeList(String hostelName) {
		logger.info("getRoomTypeList(String hostelName) In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		List<RoomType> roomTypeList = null;
		try {
			roomTypeList = session.selectList("getRoomTypeList", hostelName);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		//System.out.println("roomTypeList Size:"+roomTypeList.size());
		return roomTypeList;
	}

	@Override
	public List<Room> getRoomNameList(Room room) {
		logger.info("getRoomNameList(Room room) In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		List<Room> roomNameList = null;
		try {
			//System.out.println("roomType::"+room.getRoomTypeName()+"\nHostelname::"+room.getHostelName());
			roomNameList = session.selectList("getRoomList", room);
		} catch (NullPointerException e) {
			logger.error("getRoomNameList(Room room) In HostelController.java: NullPointerException"
					+ e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("getRoomNameList(Room room) In HostelController.java: Exception"
					+ e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return roomNameList;
	}

	@Override
	public Room getRoomFacilities(Room room) {
		logger.info("getRoomFacilities(Room room) In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		Room rfl = new Room();
		try {
			//System.out.println("roomCode::"+room.getRoomCode());
			rfl = session.selectOne("getRoomFacilities", room);
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("getRoomFacilities(Room room) In HostelDAOImpl.java: NullPointerException", e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getRoomFacilities(Room room) In HostelDAOImpl.java: Exception", e);
		} finally {
			session.close();
		}
		return rfl;
	}
	
	/**
	 * modified by ranita.sur
	 * 24062017*/

	@Override
	public List<Hostel> saveStudentRoomHostelMapping(Hostel hostel, String userId) {
		logger.info("saveStudentRoomHostelMapping(Hostel hostel) In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		List<Hostel> listHostel = null;
		Utility util = new Utility();
		try {
			hostel.setObjectId(util.getBase64EncodedID("HostelDAOImpl"));
			hostel.setUpdatedBy(userId);
			hostel.setHostelAbbreviation((hostel.getResource().getUserId()));
			hostel.setHostelDesc(hostel.getRoom().getRoomName());
			session.insert("insertStudentHostelRoom", hostel);
			session.commit();

			String code = hostel.getRoom().getRoomName();
			session.update("updateBed", code);
			session.commit();

			listHostel = getStudentRoomHostelMappingList();
		} catch (NullPointerException e) {
			e.printStackTrace(); 
			logger.info("saveStudentRoomHostelMapping(Hostel hostel) In HostelDAOImpl.java: NullPointerException");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("saveStudentRoomHostelMapping(Hostel hostel) In HostelDAOImpl.java: Exception");
		} finally {
			session.close();
		}
		return listHostel;
	}
	
	@Override
	public List<Hostel> getStudentRoomHostelMappingList() {
		logger.info("getStudentRoomHostelMappingList() In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		List<Hostel> studentsInHostel = null;
		try {
			studentsInHostel = session.selectList("getStudentsInHostelList");
		} catch (NullPointerException e) {
			logger.error("getStudentRoomHostelMappingList() In HostelDAOImpl.java: NullPointerException"+ e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("getStudentRoomHostelMappingList() In HostelDAOImpl.java: Exception"+ e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return studentsInHostel;
	}

	@Override
	public void deAllocateStudentFromHostel(String userIdNew, String userId, String hostelName) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Hostel h=session.selectOne("getStudentHostelDetails", userIdNew);
			if(h!=null){
				h.setUpdatedBy(userId);
				int status = session.update("updateBedAfterDeallocation", h);
				//System.out.println("updateStatus:"+status);
				Resource r = new Resource();
				r.setUpdatedBy(userId);
				r.setUserId(userIdNew);
				//System.out.println("updatedBY:"+r.getUpdatedBy()+"roll:"+r.getRollNumber());
				session.update("deAllocateStudentFromHostel", r);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("getSocialCategoryList() In HostelDAOImpl.java: NullPointerException"+ e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getSocialCategoryList() In HostelDAOImpl.java: Exception"+ e);
		} finally {
			session.close();
		}
	}

	@Override
	public String saveHostelExpense(List<HostelExpense> hostelExpenseList, String userId) {
		SqlSession session = sqlSessionFactory.openSession();
		String status=null;
		try {
			Utility util = new Utility();
			for (HostelExpense me : hostelExpenseList) {
				me.setHostelExpenseObjectId(util.getBase64EncodedID("AdminDAOImpl"));
				me.setUpdatedBy(userId);
				session.insert("saveHostelExpense", me);
				session.update("consumeFromStock", me);
			}
			status = "success";
			session.commit();
		} catch (NullPointerException e) {
			status="fail";
			e.printStackTrace();
			logger.error("saveMessExpense() In AdminDAOImpl.java: NullPointerException"+ e);
		} catch (Exception e) {
			status="fail";
			e.printStackTrace();
			logger.error("saveMessExpense() In AdminDAOImpl.java: Exception"+ e);
		} finally {
			session.close();
		}
		return status;
	}

	@Override
	public String getAllCommodityListForHostelExpense() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Commodity> commodityList = null;
		String allCommodities = null;
		try{
			commodityList = session.selectList("getAllCommodityListForHostelExpense");
			StringBuilder stb = new StringBuilder();
			for(Commodity com: commodityList){
				stb.append(com.getCommodityName()+"*");
				allCommodities = stb.toString().substring(0, stb.toString().length()-1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return allCommodities;
	}

	@Override
	public String getCommodityStock(String commodityName) {
		String stock = null;
		String allocateAbleStck = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			allocateAbleStck = session.selectOne("getCommodityStock", commodityName);
			if(allocateAbleStck!=null){
				stock = allocateAbleStck;
			}else{
				stock="0";
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return stock;
	}

	@Override
	public List<Room> updateRoomFacilities(List<RoomFacility> addroomFacilityList) {
		logger.info("In updateRoomFacilities(List<Room> roomList) In HostelDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		List<Room> roomList = null;
		Utility util = new Utility();
		try {
			for(RoomFacility roomFacilityDetails : addroomFacilityList) {
				roomFacilityDetails.setRoomFacilityObjectId(util.getBase64EncodedID("HostelDAOImpl"));
				int insertInfo = session.insert("editRoomFacility",roomFacilityDetails);
				session.commit();
			}
			roomList = session.selectList("roomList");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return roomList;
	}
	
	@Override
	public String addHostelType(Hostel hostel) {
		String submitResponse = "Fail";
		int insertStatus=0;
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			logger.info("Executing addDepartment() from ERPDaoImpl");
			hostel.setObjectId(encryptDecrypt.encrypt("HostelDaoImpl"));	
			
			 insertStatus = session.insert("insertIntoHostelType", hostel);			 
			if(insertStatus !=0){
				submitResponse = "Success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occured while executing addHostelType() from HOSTELDAOImpl", e);			
		} finally {
			session.close();
		}
		return submitResponse;
	}
	
	@Override
	public List<Hostel> getAllHostelTypeList() {
		SqlSession session =sqlSessionFactory.openSession();
		List<Hostel> hostTypeList = null;
		try{
			logger.info("Executing getAllHostelTypeList() from HOSTELDaoImpl");
			hostTypeList = session.selectList("getHostelType");
		}catch(Exception e){
			e.printStackTrace();
			logger.error("Exception occured while executing getAllHostelTypeList() from HOSTELDAOImpl", e);	
		}finally {
			session.close();
		}
		return hostTypeList;
	}
	
	@Override
	public List<HouseType> getAllHouseTypeList() {
		List<HouseType> houseTypeList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In List<HouseType> getAllHouseTypeList() of HostelDaoImpl.java");
			houseTypeList = session.selectList("getAllHouseTypeList");
		}catch (Exception e) {
			logger.error("Exception in List<HouseType> getAllHouseTypeList() of HostelDaoImpl.java:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return houseTypeList;
	}
	
	@Override
	public String addNewHouse(House house) {
		String insertStatus = "fail";
		SqlSession session = sqlSessionFactory.openSession();
		int status = 0;
		try {
			logger.info("In String addNewHouse(House house) of HostelDaoImpl.java");
			house.setHouseObjectId(encryptDecrypt.getBase64DecodedID("HostelDAOImpl"));
			if(""!=house.getHouseType().getHouseTypeCode()) {
				status = session.insert("addNewHouse", house);
			}
			else {
				status = session.insert("addNewHouseWithoutType", house);
			}
			if (status != 0) {
				insertStatus = "success";
			}
		}catch (Exception e) {
			logger.error("Exception in String addNewHouse(House house) of HostelDaoImpl.java:"+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return insertStatus;
	}
	
	@Override
	public List<House> getAllHouseList() {
		List<House> houseList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In List<House> getAllHouseList() of HostelDaoImpl.java");
			houseList = session.selectList("getAllHouseList");
		}catch (Exception e) {
			logger.error("Exception in List<House> getAllHouseList() of HostelDaoImpl.java:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return houseList;
	}
	
	@Override
	public String submitHouseResidenceMapping(HouseResidenceMapping houseResidenceMapping) {
		String insertStatus = "fail";
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In String submitHouseResidenceMapping(HouseResidenceMapping houseResidenceMapping) of HostelDaoImpl.java");
			houseResidenceMapping.setHouseResidenceMappingObjectId(encryptDecrypt.getBase64DecodedID("HostelDAOImpl"));
			int status = session.insert("submitHouseResidenceMapping", houseResidenceMapping);
			if(status != 0) {
				insertStatus = "success";
			}
		}catch (Exception e) {
			logger.error("Exception in String submitHouseResidenceMapping(HouseResidenceMapping houseResidenceMapping) of HostelDaoImpl.java:"+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return insertStatus;
	}
	
	@Override
	public List<HouseResidenceMapping> getAllHouseResidenceMapping() {
		List<HouseResidenceMapping> houseResidenceMappingList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In List<HouseResidenceMapping> getAllHouseResidenceMapping() of HostelDAOImpl.java");
			houseResidenceMappingList = session.selectList("getAllHouseResidenceMapping");
		} catch (Exception e) {
			logger.error("Exception in List<HouseResidenceMapping> getAllHouseResidenceMapping() of HostelDAOImpl.java:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return houseResidenceMappingList;
	}
	
	@Override
	public List<HouseMaster> getHouseMasterData(String academicYear) {
		List<HouseMaster> houseMasterList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In List<HouseMaster> getHouseMasterData(String academicYear) method of HostelDaoImpl.java");
			houseMasterList = session.selectList("getHouseMasterData" , academicYear);
			if(null == houseMasterList) {
				List<House> houseList = session.selectList("getAllHouseList");
				for(House h : houseList) {
					House house = new House();
					house.setHouseCode(h.getHouseCode());
					house.setHouseName(h.getHouseName());
					HouseMaster hm = new HouseMaster();
					hm.setHouse(h);
					houseMasterList.add(hm);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in List<HouseMaster> getHouseMasterData(String academicYear) method of HostelDaoImpl.java:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return houseMasterList;
	}
	
	@Override
	public String submitUpdateOfHouseForCadet(Resource resource) {
		SqlSession session = sqlSessionFactory.openSession();
		String updateStatus = "fail";
		try{
			logger.info("In String submitUpdateOfHouseForCadet(Resource resource) of HostelDaoImpl.java");
			int status = session.update("submitUpdateOfHouseForCadet", resource);
			if(status != 0){
				updateStatus = "success";
			}
		}catch (Exception e) {
			logger.error("Excption in String submitUpdateOfHouseForCadet(Resource resource) of HostelDaoImpl.java:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return updateStatus;
	}
	
	/**
	 * @author anup.roy*/
	@Override
	public Resource getHouseAndInfoOfCadet(String userId) {
		SqlSession session = sqlSessionFactory.openSession();
		Resource resource = null;
		try{
			logger.info("In Resource getHouseAndInfoOfCadet(String userId) of HostelDaoImpl.java");
			resource = session.selectOne("getHouseAndInfoOfCadet",userId);
		}catch (Exception e) {
			logger.error("Exception in Resource getHouseAndInfoOfCadet(String userId) of HostelDaoImpl.java:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return resource;
	}
}
