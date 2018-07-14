package com.qts.icam.service.impl.facility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qts.icam.dao.facility.FacilityDao;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.facility.Facility;
import com.qts.icam.model.hostel.Hostel;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.service.facility.FacilityService;


@Service
public class FacilityServiceImpl implements FacilityService{

	@Autowired
	FacilityDao facilityDao;
	
	@Override
	public List<SocialCategory> getSocialCategoryList() {
		return facilityDao.getSocialCategoryList();
	}

	@Override
	public void saveFacilities(Facility facility) {
		facilityDao.saveFacilities(facility);
	}

	@Override
	public List<Facility> getAllFacilities() {
		return facilityDao.getAllFacilities();
	}

	@Override
	public Facility getFacilityDetails(String facilityCode) {
		return facilityDao.getFacilityDetails(facilityCode);
	}

	@Override
	public void updateFacilityDetails(Facility facility) {
		facilityDao.updateFacilityDetails(facility);
	}

	@Override
	public List<Venue> getVenueList() {
		return facilityDao.getVenueList();
	}

	@Override
	public String getAvailableRoomOfBlock(String block) {
		return facilityDao.getAvailableRoomOfBlock(block);
	}

	@Override
	public String getBlocksOfVenue(String venueName) {
		return facilityDao.getBlocksOfVenue(venueName);
	}

	@Override
	public String getAvailableBedsInRoom(String roomNo) {
		return facilityDao.getAvailableBedsInRoom(roomNo);
	}

	@Override
	public List<Resource> getResourceIdList() {
		return facilityDao.getResourceIdList();
	}

	@Override
	public Resource getResourceDetails(String userId) {
		return facilityDao.getResourceDetails(userId);
	}

	@Override
	public void submitAssignedVenueToResource(Venue venue) {
		facilityDao.submitAssignedVenueToResource(venue);
	}

	@Override
	public List<Venue> getListVenuesAssignedToResource() {
		return facilityDao.getListVenuesAssignedToResource();
	}

	@Override
	public void deAllocateVenueFromResource(Venue venue) {
		facilityDao.deAllocateVenueFromResource(venue);
	}
	
	@Override
	public void facilityDeactivation(String facilityCode) {
		facilityDao.facilityDeactivation(facilityCode);
	}
	
}