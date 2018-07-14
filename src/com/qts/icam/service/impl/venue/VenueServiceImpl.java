package com.qts.icam.service.impl.venue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.qts.icam.dao.venue.VenueDAO;
import com.qts.icam.model.venue.Venue;
import com.qts.icam.model.common.Location;
import com.qts.icam.model.common.State;
import com.qts.icam.model.facility.Facility;
import com.qts.icam.service.venue.VenueService;

@Service
public class VenueServiceImpl implements VenueService {

	
	@Autowired
	VenueDAO venueDAO;
	
	@Override
	public String addZone(Venue venue) {
		return venueDAO.addZone(venue);
	}

	@Override
	public List<Venue> getZoneList() {
		return venueDAO.getZoneList();
	}

	@Override
	public String editZone(Venue venue) {
		return venueDAO.editZone(venue);
	}

	@Override
	public List<State> getStateListAgainstZone(Venue venue) {
		return venueDAO.getStateListAgainstZone(venue);
	}

	@Override
	public String addLocation(Location location) {
		return venueDAO.addLocation(location);
	}

	@Override
	public List<Location> getLocationList() {
		return venueDAO.getLocationList();
	}

	@Override
	public String editLocation(Location location) {
		return venueDAO.editLocation(location);
	}

	@Override
	public String inactiveLocation(Location location) {
		return venueDAO.inactiveLocation(location);
	}

	@Override
	public String addVenue(Venue venue) {
		return venueDAO.addVenue(venue);
	}

	@Override
	public List<Venue> getVenueList() {
		return venueDAO.getVenueList();
	}

	@Override
	public String editVenue(Venue venue) {
		return venueDAO.editVenue(venue);
	}

	@Override
	public List<Facility> getFacilityList() {
		return venueDAO.getFacilityList();
	}

	@Override
	public String addVenueFacilityMapping(Venue venue) {
		return venueDAO.addVenueFacilityMapping(venue);
	}

	@Override
	public List<Venue> getFacilityListAgainstVenue(String venue) {
		return venueDAO.getFacilityListAgainstVenue(venue);
	}

	@Override
	public String updateVenueFacilityMapping(Venue venue) {
		return venueDAO.updateVenueFacilityMapping(venue);
	}

	@Override
	public List<Venue> getVenueTypeList() {
		return venueDAO.getVenueTypeList();
	}

	@Override
	public List<Venue> getVenueListAgainstVenueType(String venueTypeCode) {
		return venueDAO.getVenueListAgainstVenueType(venueTypeCode);
	}

	@Override
	public String getBuildingAgainstVenue(String venueCode) {
		return venueDAO.getBuildingAgainstVenue(venueCode);
	}

	/*@Override
	public String insertVenue(Venue venue) {
		return venueDAO.insertVenue(venue);
	}
*/
	@Override
	public List<Venue> getAllocatedVenueList() {
		return venueDAO.getAllocatedVenueList();
	}

	@Override
	public Venue getAllocatedVenueAgainstVenue(Venue venue) {
		return venueDAO.getAllocatedVenueAgainstVenue(venue);
	}

	@Override
	public String insertVenueALlocation(Venue venue) {
		return venueDAO.insertVenueALlocation(venue);
	}

	@Override
	public List<Venue> getVenueFacilityList() {
		return venueDAO.getVenueFacilityList();
	}

	@Override
	public String inactiveVenue(Venue venue) {
		return venueDAO.inactiveVenue(venue);
	}
}
