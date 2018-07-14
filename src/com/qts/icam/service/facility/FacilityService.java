package com.qts.icam.service.facility;

import java.util.List;

import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.SocialCategory;
import com.qts.icam.model.facility.Facility;
import com.qts.icam.model.hostel.Hostel;
import com.qts.icam.model.venue.Venue;

public interface FacilityService{

	public List<SocialCategory> getSocialCategoryList();

	public void saveFacilities(Facility facility);

	public List<Facility> getAllFacilities();

	public Facility getFacilityDetails(String facilityCode);

	public void updateFacilityDetails(Facility facility);

	public List<Venue> getVenueList();

	public String getBlocksOfVenue(String venueName);
	
	public String getAvailableRoomOfBlock(String block);

	public String getAvailableBedsInRoom(String roomNo);

	public List<Resource> getResourceIdList();

	public Resource getResourceDetails(String userId);

	public void submitAssignedVenueToResource(Venue venue);

	public List<Venue> getListVenuesAssignedToResource();

	public void deAllocateVenueFromResource(Venue venue);

	public void facilityDeactivation(String facilityCode);
	
	
}