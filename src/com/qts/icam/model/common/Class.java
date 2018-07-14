package com.qts.icam.model.common;

import java.util.List;

import com.qts.icam.model.backoffice.Term;
import com.qts.icam.model.common.AcademicYear;
import com.qts.icam.model.common.Course;
import com.qts.icam.model.academics.CourseType;
import com.qts.icam.model.backoffice.Exam;
import com.qts.icam.model.common.ExamType;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Section;

import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.academics.SubjectGroup;
import com.qts.icam.model.common.SubjectType;
import com.qts.icam.model.common.TeachingLevel;

public class Class {
	

	
	private String classObjectId;
	private String classCode;
	private String classDesc;
	private String updatedBy;
	private String className;
	private int totalSeats;
	private int allocatedSeats;
	private List<Section> sectionList;
	private Section section;
//	private Stream stream;
//	private List<Stream> streamList;
	private List<Resource> resourceList;
	private String status;	
	private List<Course> courseList;
	private String criteria;	
	private List<Exam> examList;
	private List<ExamType> examTypeList;
	private ExamType examType;
	private AcademicYear academicYear;
	private Term term;
	private TeachingLevel teachingLevel;
	private String subjectType;
	private String subjectName;
	private String classTeacher;	
	private List<Subject> resourcePerSubjectList;
	private List<Subject> subjectList;		
	private Course course;	
	private List<CourseType> courseTypeList;
	private String subjectGroupName;	
	
	private List<SubjectGroup> subjectGroupList;	
	private String subjectGroup;	
	private String resourceId;
	private List<SubjectType> subjectTypeList;	
	
	

	public List<ExamType> getExamTypeList() {
		return examTypeList;
	}
	public void setExamTypeList(List<ExamType> examTypeList) {
		this.examTypeList = examTypeList;
	}
	public ExamType getExamType() {
		return examType;
	}
	public void setExamType(ExamType examType) {
		this.examType = examType;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public String getClassObjectId() {
		return classObjectId;
	}
	public void setClassObjectId(String classObjectId) {
		this.classObjectId = classObjectId;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassDesc() {
		return classDesc;
	}
	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getAllocatedSeats() {
		return allocatedSeats;
	}
	public void setAllocatedSeats(int allocatedSeats) {
		this.allocatedSeats = allocatedSeats;
	}
	public List<Section> getSectionList() {
		return sectionList;
	}
	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}
//	public Stream getStream() {
//		return stream;
//	}
//	public void setStream(Stream stream) {
//		this.stream = stream;
//	}
//	public List<Stream> getStreamList() {
//		return streamList;
//	}
//	public void setStreamList(List<Stream> streamList) {
//		this.streamList = streamList;
//	}
	public List<Resource> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	public String getCriteria() {
		return criteria;
	}
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	public List<Exam> getExamList() {
		return examList;
	}
	public void setExamList(List<Exam> examList) {
		this.examList = examList;
	}

	public AcademicYear getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(AcademicYear academicYear) {
		this.academicYear = academicYear;
	}
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	public TeachingLevel getTeachingLevel() {
		return teachingLevel;
	}
	public void setTeachingLevel(TeachingLevel teachingLevel) {
		this.teachingLevel = teachingLevel;
	}
	public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getClassTeacher() {
		return classTeacher;
	}
	public void setClassTeacher(String classTeacher) {
		this.classTeacher = classTeacher;
	}
	public List<Subject> getResourcePerSubjectList() {
		return resourcePerSubjectList;
	}
	public void setResourcePerSubjectList(List<Subject> resourcePerSubjectList) {
		this.resourcePerSubjectList = resourcePerSubjectList;
	}
	public List<Subject> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public List<CourseType> getCourseTypeList() {
		return courseTypeList;
	}
	public void setCourseTypeList(List<CourseType> courseTypeList) {
		this.courseTypeList = courseTypeList;
	}
	public String getSubjectGroupName() {
		return subjectGroupName;
	}
	public void setSubjectGroupName(String subjectGroupName) {
		this.subjectGroupName = subjectGroupName;
	}
	public List<SubjectGroup> getSubjectGroupList() {
		return subjectGroupList;
	}
	public void setSubjectGroupList(List<SubjectGroup> subjectGroupList) {
		this.subjectGroupList = subjectGroupList;
	}
	public String getSubjectGroup() {
		return subjectGroup;
	}
	public void setSubjectGroup(String subjectGroup) {
		this.subjectGroup = subjectGroup;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public List<SubjectType> getSubjectTypeList() {
		return subjectTypeList;
	}
	public void setSubjectTypeList(List<SubjectType> subjectTypeList) {
		this.subjectTypeList = subjectTypeList;
	}

}
