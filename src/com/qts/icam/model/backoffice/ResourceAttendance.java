package com.qts.icam.model.backoffice;

import com.qts.icam.model.common.ResourceType;
import com.qts.icam.model.common.WorkShift;

public class ResourceAttendance {

		private String attendanceObjectId;
		private String attendanceCode;
		private String attendanceDesc;
		private String updatedBy;
		private String attendanceDay;
		private String month;
		private String year ;
		private String resourceId;	
		private WorkShift workShift;	
		private ResourceType resourceType;
		private boolean attendanceFlag;
		private int attendanceDetailsId;
		private String swipeTimeSlot;
		private String weekDay;
		private String attendanceMonth;
		private String attendanceYear;
		
		
		public String getAttendanceObjectId() {
			return attendanceObjectId;
		}
		public void setAttendanceObjectId(String attendanceObjectId) {
			this.attendanceObjectId = attendanceObjectId;
		}
		public String getAttendanceCode() {
			return attendanceCode;
		}
		public void setAttendanceCode(String attendanceCode) {
			this.attendanceCode = attendanceCode;
		}
		public String getAttendanceDesc() {
			return attendanceDesc;
		}
		public void setAttendanceDesc(String attendanceDesc) {
			this.attendanceDesc = attendanceDesc;
		}
		public String getUpdatedBy() {
			return updatedBy;
		}
		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}
		public String getAttendanceDay() {
			return attendanceDay;
		}
		public void setAttendanceDay(String attendanceDay) {
			this.attendanceDay = attendanceDay;
		}
		public String getMonth() {
			return month;
		}
		public void setMonth(String month) {
			this.month = month;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getResourceId() {
			return resourceId;
		}
		public void setResourceId(String resourceId) {
			this.resourceId = resourceId;
		}
		public WorkShift getWorkShift() {
			return workShift;
		}
		public void setWorkShift(WorkShift workShift) {
			this.workShift = workShift;
		}
		public ResourceType getResourceType() {
			return resourceType;
		}
		public void setResourceType(ResourceType resourceType) {
			this.resourceType = resourceType;
		}
		public boolean isAttendanceFlag() {
			return attendanceFlag;
		}
		public void setAttendanceFlag(boolean attendanceFlag) {
			this.attendanceFlag = attendanceFlag;
		}
		public int getAttendanceDetailsId() {
			return attendanceDetailsId;
		}
		public void setAttendanceDetailsId(int attendanceDetailsId) {
			this.attendanceDetailsId = attendanceDetailsId;
		}
		public String getSwipeTimeSlot() {
			return swipeTimeSlot;
		}
		public void setSwipeTimeSlot(String swipeTimeSlot) {
			this.swipeTimeSlot = swipeTimeSlot;
		}
		public String getWeekDay() {
			return weekDay;
		}
		public void setWeekDay(String weekDay) {
			this.weekDay = weekDay;
		}
		public String getAttendanceMonth() {
			return attendanceMonth;
		}
		public void setAttendanceMonth(String attendanceMonth) {
			this.attendanceMonth = attendanceMonth;
		}
		public String getAttendanceYear() {
			return attendanceYear;
		}
		public void setAttendanceYear(String attendanceYear) {
			this.attendanceYear = attendanceYear;
		}
		
}
