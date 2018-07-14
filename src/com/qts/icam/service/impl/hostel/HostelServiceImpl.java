package com.qts.icam.service.impl.hostel;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;

import com.qts.icam.dao.hostel.HostelDAO;
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
import com.qts.icam.service.hostel.HostelService;
import com.qts.icam.utility.customexception.CustomException;

@Service
public class HostelServiceImpl implements HostelService {
	@Autowired
	HostelDAO hostelDAO;
	
	@Autowired
	private ServletContext servletContext;
	
	public List<HostelFacility> listHostelFacility = null;

	
	@Override
	public List<HostelFacility> getHostelFacility() throws CustomException {
		listHostelFacility = hostelDAO.getHostelFacility();
		return listHostelFacility;
	}

	@Override
	public String addhostelFacility(HostelFacility hostelFacility) throws CustomException {
		return hostelDAO.addhostelFacility(hostelFacility);
	}

	@Override
	public HostelFacility getHostelFacilityDetails(String hostelFacilityCode) throws CustomException {
		return hostelDAO.getHostelFacilityDetails(hostelFacilityCode);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String editHostelFacility(HostelFacility hostelFacility) throws CustomException {
		return hostelDAO.editHostelFacility(hostelFacility);
	}
	
	@Override
	public PagedListHolder<HostelFacility> getHostelFacilityPaging(
			HttpServletRequest request) throws CustomException {
		PagedListHolder<HostelFacility> pagedListHolder = new PagedListHolder<HostelFacility>(
				listHostelFacility);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}

	@Override
	public List<HostelFacility> getSearchBasedHostelFacility(
			Map<String, Object> parameters) throws CustomException {
		listHostelFacility = hostelDAO.getSearchBasedHostelFacility(parameters);
		return listHostelFacility;
	}
	
	@Override
	public List<Student> getStudentListForEachHotel() throws CustomException {
		return hostelDAO.getStudentListForEachHotel();
	}

	/**
	 * @author anup.roy*
	 * total hostel portion will start form here**/
	
	@Override
	public List<HostelType> getHostelTypeList() {
		return hostelDAO.getHostelTypeList();
	}
	
	@Override
	public List<Hostel> getHostel(){
		return hostelDAO.getHostel();
	}
	
	@Override
	public String addHostel(Hostel hostel) throws CustomException {
		return hostelDAO.addHostel(hostel);
	}
	
	@Override
	public String editHostel(Hostel hostel) throws CustomException {
		return hostelDAO.editHostel(hostel);
	}

	@Override
	public List<RoomType> getRoomTypeList() {
		return hostelDAO.getRoomTypeList();
	}

	@Override
	public List<SocialCategory> getSocialCategoryList() {
		return hostelDAO.getSocialCategoryList();
	}

	@Override
	public void saveRoomType(RoomType roomType) {
		hostelDAO.saveRoomType(roomType);
	}

	@Override
	public RoomType getRoomTypeDetails(String roomType) {
		return hostelDAO.getRoomTypeDetails(roomType);
	}

	@Override
	public void updateRoomType(RoomType roomType) {
		hostelDAO.updateRoomType(roomType);
		
	}

	@Override
	public void saveHostelFacilities(HostelFacility hostelFacility) {
		hostelDAO.saveHostelFacilities(hostelFacility);
	}

	@Override
	public List<HostelFacility> getHostelFacilityList() {
		return hostelDAO.getHostelFacilityList();
	}

	@Override
	public HostelFacility getHostelFacilities(String hostelFacilityCode) {
		return hostelDAO.getHostelFacilities(hostelFacilityCode);
	}

	@Override
	public List<RoomFacility> getRoomFacilitiesList() {
		return hostelDAO.getRoomFacilitiesList();
	}

	@Override
	public void saveRoomFacilities(Room room) {
		hostelDAO.saveRoomFacilities(room);
	}

	@Override
	public List<Room> getRoomList() {
		return hostelDAO.getRoomList();
	}

	@Override
	public Hostel getRoomDetails(Room room) {
		return hostelDAO.getRoomDetails(room);
	}

	@Override
	public List<Hostel> getStudentHostelList() {
		return hostelDAO.getStudentHostelList();
	}

	@Override
	public Hostel getSelectedHostelRoomTypeByStudent(Resource resource) {
		return hostelDAO.getSelectedHostelRoomTypeByStudent(resource);
	}

	@Override
	public Resource getStudentDetails(String rollNumber) {
		return hostelDAO.getStudentDetails(rollNumber);
	}

	@Override
	public List<RoomType> getRoomTypeList(String hostelName) {
		return hostelDAO.getRoomTypeList(hostelName);
	}

	@Override
	public List<Room> getRoomNameList(Room room) {
		return hostelDAO.getRoomNameList(room);
	}

	@Override
	public Room getRoomFacilities(Room room) {
		return hostelDAO.getRoomFacilities(room);
	}

	@Override
	public List<Hostel> saveStudentRoomHostelMapping(Hostel hostel, String userId) {
		return hostelDAO.saveStudentRoomHostelMapping(hostel, userId);
	}

	@Override
	public List<Hostel> getStudentRoomHostelMappingList() {
		return hostelDAO.getStudentRoomHostelMappingList();
	}

	@Override
	public void deAllocateStudentFromHostel(String userIdNew, String userId, String hostelName) {
		hostelDAO.deAllocateStudentFromHostel(userIdNew,userId,hostelName);
	}

	@Override
	public String saveHostelExpense(List<HostelExpense> hostelExpenseList, String userId) {
		return hostelDAO.saveHostelExpense(hostelExpenseList, userId);
	}

	@Override
	public String getAllCommodityListForHostelExpense() {
		return hostelDAO.getAllCommodityListForHostelExpense();
	}

	@Override
	public String getCommodityStock(String commodityName) {
		return hostelDAO.getCommodityStock(commodityName);
	}

	@Override
	public List<Room> updateRoomFacilities(List<RoomFacility> addroomFacilityList) {
		return hostelDAO.updateRoomFacilities(addroomFacilityList);
	}
	
	@Override
	public String addHostelType(Hostel hostel) {
		return hostelDAO.addHostelType(hostel);
	}

	@Override
	public List<Hostel> getAllHostelTypeList() {
		return hostelDAO.getAllHostelTypeList();
	}
	
	@Override
	public List<HouseType> getAllHouseTypeList() {
		return hostelDAO.getAllHouseTypeList();
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String addNewHouse(House house) {
		return hostelDAO.addNewHouse(house);
	}
	
	@Override
	public List<House> getAllHouseList() {
		return hostelDAO.getAllHouseList();
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	@Override
	public String submitHouseResidenceMapping(HouseResidenceMapping houseResidenceMapping) {
		return hostelDAO.submitHouseResidenceMapping(houseResidenceMapping);
	}
	
	@Override
	public List<HouseResidenceMapping> getAllHouseResidenceMapping() {
		return hostelDAO.getAllHouseResidenceMapping();
	}
	
	@Override
	public List<HouseMaster> getHouseMasterData(String academicYear) {
		return hostelDAO.getHouseMasterData(academicYear);
	}
	
	@Override
	public String submitUpdateOfHouseForCadet(Resource resource) {
		return hostelDAO.submitUpdateOfHouseForCadet(resource);
	}
	
	/**
	 * @author anup.roy*/
	@Override
	public Resource getHouseAndInfoOfCadet(String userId) {
		return hostelDAO.getHouseAndInfoOfCadet(userId);
	}
}
