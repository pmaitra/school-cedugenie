package com.qts.icam.service.hostel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.support.PagedListHolder;

import com.qts.icam.model.common.HostelExpense;
import com.qts.icam.model.common.HostelType;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Room;
import com.qts.icam.model.common.RoomFacility;
import com.qts.icam.model.common.RoomType;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.common.Student;
import com.qts.icam.model.hostel.Hostel;
import com.qts.icam.model.hostel.HostelFacility;
import com.qts.icam.model.hostel.House;
import com.qts.icam.model.hostel.HouseMaster;
import com.qts.icam.model.hostel.HouseResidenceMapping;
import com.qts.icam.model.hostel.HouseType;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.utility.customexception.CustomException;

public interface HostelService {
	
	List<HostelFacility> getHostelFacility() throws CustomException;

	String addhostelFacility(HostelFacility hostelFacility) throws CustomException;

	HostelFacility getHostelFacilityDetails(String hostelFacilityCode) throws CustomException;

	String editHostelFacility(HostelFacility hostelFacility) throws CustomException;
	
	PagedListHolder<HostelFacility> getHostelFacilityPaging(HttpServletRequest request) throws CustomException;

	List<HostelFacility> getSearchBasedHostelFacility(Map<String, Object> parameters) throws CustomException;
	
	List<Student> getStudentListForEachHotel()throws CustomException;

	/**@author anup.roy*
	 *total hostel portion will start form here*/
	
	public List<HostelType> getHostelTypeList();
	
	public List<Hostel> getHostel();
	
	public String addHostel(Hostel hostel) throws CustomException;
	
	public String editHostel(Hostel hostel) throws CustomException;

	public List<RoomType> getRoomTypeList();

	public List<SocialCategory> getSocialCategoryList();

	public void saveRoomType(RoomType roomType);

	public RoomType getRoomTypeDetails(String roomType);

	public void updateRoomType(RoomType roomType);

	public void saveHostelFacilities(HostelFacility hostelFacility);

	public List<HostelFacility> getHostelFacilityList();

	public HostelFacility getHostelFacilities(String hostelFacilityCode);

	public List<RoomFacility> getRoomFacilitiesList();

	public void saveRoomFacilities(Room room);

	public List<Room> getRoomList();

	public Hostel getRoomDetails(Room room);

	public List<Hostel> getStudentHostelList();

	public Hostel getSelectedHostelRoomTypeByStudent(Resource resource);

	public Resource getStudentDetails(String rollNumber);

	public List<RoomType> getRoomTypeList(String hostelName);

	public List<Room> getRoomNameList(Room room);

	public Room getRoomFacilities(Room room);

	public List<Hostel> saveStudentRoomHostelMapping(Hostel hostel, String userId);

	public List<Hostel> getStudentRoomHostelMappingList();

	public void deAllocateStudentFromHostel(String userIdNew, String userId, String hostelName);

	public String saveHostelExpense(List<HostelExpense> hostelExpenseList, String userId);

	public String getAllCommodityListForHostelExpense();
	
	public String getCommodityStock(String commodityName);

	public List<Room> updateRoomFacilities(List<RoomFacility> addroomFacilityList);
	
	public String addHostelType(Hostel hostel);
	
	public List<Hostel> getAllHostelTypeList();
	
	public List<HouseType> getAllHouseTypeList();

	public String addNewHouse(House house);

	public List<House> getAllHouseList();

	public String submitHouseResidenceMapping(HouseResidenceMapping houseResidenceMapping);

	public List<HouseResidenceMapping> getAllHouseResidenceMapping();

	public List<HouseMaster> getHouseMasterData(String academicYear);
	
	public String submitUpdateOfHouseForCadet(Resource resource);

	/**
	 * @author anup.roy*/
	public Resource getHouseAndInfoOfCadet(String userId);
}
