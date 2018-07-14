package com.qts.icam.dao.venue;

import java.util.List;

import com.qts.icam.model.venue.Venue;
import com.qts.icam.model.common.Location;
import com.qts.icam.model.common.State;
import com.qts.icam.model.facility.Facility;

public interface VenueDAO {

	public String addZone(Venue venue);

	public List<Venue> getZoneList();

	public String editZone(Venue venue);

	public List<State> getStateListAgainstZone(Venue venue);

	public String addLocation(Location location);

	public List<Location> getLocationList();

	public String editLocation(Location location);

	public String inactiveLocation(Location location);

	public String addVenue(Venue venue);

	public List<Venue> getVenueList();

	public String editVenue(Venue venue);

	public List<Facility> getFacilityList();

	public String addVenueFacilityMapping(Venue venue);

	public List<Venue> getFacilityListAgainstVenue(String venue);

	public String updateVenueFacilityMapping(Venue venue);

	public List<Venue> getVenueTypeList();

	public List<Venue> getVenueListAgainstVenueType(String venueTypeCode);

	public String getBuildingAgainstVenue(String venueCode);

	//public String insertVenue(Venue venue);

	public List<Venue> getAllocatedVenueList();

	public Venue getAllocatedVenueAgainstVenue(Venue venue);

	public String insertVenueALlocation(Venue venue);

	public List<Venue> getVenueFacilityList();
	
	public String inactiveVenue(Venue venue);

}
