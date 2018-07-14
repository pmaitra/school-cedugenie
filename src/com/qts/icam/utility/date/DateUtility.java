package com.qts.icam.utility.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.qts.icam.utility.ldap.Ldap;

public class DateUtility {
	public static Logger logger = Logger.getLogger(DateUtility.class);

	/**
	 * 
	 * @param epochDate
	 * @return
	 */
	public String epochToHumanReadableFormat(String epochDate) {
		String readableDate = new SimpleDateFormat("dd/MM/yyyy")
				.format(new Date(Integer.parseInt(epochDate) * 1000L));
		return readableDate;
	}

	/**
	 * 
	 * @param normalDate
	 * @return
	 */
	public int humanReadableFormatToEpoch(String normalDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		long epoch = 0L;
		try {
			Date date = sdf.parse(normalDate);
			epoch = date.getTime() / 1000;
		} catch (Exception e) {
			logger.error(e);
		}
		return (int) epoch;
	}

	

	/**
	 * 
	 * @param inputDate
	 * @return
	 */
	public String convertDDMMYYtoYYMMDD(String inputDate) {
		DateFormat userDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat dateFormatNeeded = new SimpleDateFormat("yyyy-MM-dd");
		String outputDate = null;
		try {
			outputDate = dateFormatNeeded.format(userDateFormat
					.parse(inputDate));
		} catch (ParseException e) {
			logger.error(e);
		}
		return outputDate;
	}

	public String convert12HoursTimeTo24HoursTime(String hoursTime12){
		String hoursTime24 = null;
		try {
			SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
		       SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
		       Date date = parseFormat.parse(hoursTime12);
		       hoursTime24 = displayFormat.format(date).toString();
		} catch (Exception e) {
		    logger.error(e);
		}
	return hoursTime24;
	}
	
	
	public Set<String> getDatesBetweenTwoDate(String timeTableDetailsStartDate, String timeTableDetailsEndDate) {
		Set<String> dates = new HashSet<String>();
	try{
		    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		    Date startDate = (Date)formatter.parse(timeTableDetailsStartDate); 
		    Date endDate = (Date)formatter.parse(timeTableDetailsEndDate);
		    long interval = 1000 * 60 * 60; // 1 hour in milliseconds
		    long endTime = endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
		    long curTime = startDate.getTime();
		    while (curTime <= endTime) {
		    	 String ds = formatter.format(new Date(curTime));  
		    	 dates.add(ds);
		    	    curTime += interval;
		    }    
	}catch(Exception e){
		logger.error("Exception in getDatesBetweenTwoDate(String, String) method in Utility:  EXCEPTION: ",e);
	}
		return dates;
	}
	
	
	/**
	 * this method get current system date and time
	 * @return String
	 */
	public String getCurrentSystemDateTime() {
		String  currentSystemDateAndTime = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
			   Date date = new Date();
			   currentSystemDateAndTime = dateFormat.format(date);	
		}catch (Exception e) {
			logger.error(e);
		}
		return currentSystemDateAndTime;
	}
	
	/**
	 * compare validate expire period with installation time and current server time
	 * 
	 * @param dbLicenseInfo
	 * @return boolean
	 */
	public boolean validateExpireDate(Long epochTime,int day) {
		boolean trialPeriodStatus = false;
		try{
			String installationTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a").format(new Date(epochTime));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(installationTime));
			c.add(Calendar.DATE, day); // Adding trial period(days) into installation date
			long expireDate =  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a").parse(sdf.format(c.getTime())).getTime();
			long currentSystemDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss a").parse(getCurrentSystemDateTime()).getTime(); 
			if(currentSystemDate > expireDate){
				trialPeriodStatus = true;
			}
		}catch(Exception e){
			logger.error(e);
		}
		return trialPeriodStatus;
	}
}
