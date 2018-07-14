package com.qts.icam.model.academics;

import com.qts.icam.model.common.Standard;

public class Course {
		private int serialId;
		private String objectId;
		private String updatedBy;
		private String desc;
		private String status;
		
		private String courseCode;
		private String courseName;
		private String courseDesc;
		private Standard standard;
		private CourseType courseType;
		
		
		public int getSerialId() {
			return serialId;
		}
		public void setSerialId(int serialId) {
			this.serialId = serialId;
		}
		public String getObjectId() {
			return objectId;
		}
		public void setObjectId(String objectId) {
			this.objectId = objectId;
		}
		public String getUpdatedBy() {
			return updatedBy;
		}
		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getCourseCode() {
			return courseCode;
		}
		public void setCourseCode(String courseCode) {
			this.courseCode = courseCode;
		}
		public String getCourseName() {
			return courseName;
		}
		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}
		public String getCourseDesc() {
			return courseDesc;
		}
		public void setCourseDesc(String courseDesc) {
			this.courseDesc = courseDesc;
		}
		public Standard getStandard() {
			return standard;
		}
		public void setStandard(Standard standard) {
			this.standard = standard;
		}
		public CourseType getCourseType() {
			return courseType;
		}
		public void setCourseType(CourseType courseType) {
			this.courseType = courseType;
		}
}
