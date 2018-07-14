package com.qts.icam.model.common;

import java.util.List;

import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Class;

public class TeachingLevel {
	
	private String teachingLevelId;
	private String teachingLevelObjectId;
	private String teachingLevelCode;
	private String teachingLevelDesc;
	private String updatedBy;
	private String teachingLevelName;
	private String status;
	private List<Class> classes;
	private AcademicYear academicYear;
	
	public String getTeachingLevelId() {
		return teachingLevelId;
	}
	public void setTeachingLevelId(String teachingLevelId) {
		this.teachingLevelId = teachingLevelId;
	}
	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	public List<Class> getClasses() {
		return classes;
	}
	public void setClasses(List<Class> classes) {
		this.classes = classes;
	}
	public String getTeachingLevelObjectId() {
		return teachingLevelObjectId;
	}
	public void setTeachingLevelObjectId(String teachingLevelObjectId) {
		this.teachingLevelObjectId = teachingLevelObjectId;
	}
	public String getTeachingLevelCode() {
		return teachingLevelCode;
	}
	public void setTeachingLevelCode(String teachingLevelCode) {
		this.teachingLevelCode = teachingLevelCode;
	}
	public String getTeachingLevelDesc() {
		return teachingLevelDesc;
	}
	public void setTeachingLevelDesc(String teachingLevelDesc) {
		this.teachingLevelDesc = teachingLevelDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getTeachingLevelName() {
		return teachingLevelName;
	}
	public void setTeachingLevelName(String teachingLevelName) {
		this.teachingLevelName = teachingLevelName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}
