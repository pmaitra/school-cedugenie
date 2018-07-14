package com.qts.icam.model.academics;

public class UserExamMarks {
		private int serialId;
		private String objectId;
		private String updatedBy;
		private String desc;
		private String status;
		
		private String course;
		private String subject;
		private String exam;
		private int theory;
		private int practical;
		private int theoryPass;
		private int practicalPass;
		private int total;
		private int pass;
		
		
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
		public String getCourse() {
			return course;
		}
		public void setCourse(String course) {
			this.course = course;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getExam() {
			return exam;
		}
		public void setExam(String exam) {
			this.exam = exam;
		}
		public int getTheory() {
			return theory;
		}
		public void setTheory(int theory) {
			this.theory = theory;
		}
		public int getPractical() {
			return practical;
		}
		public void setPractical(int practical) {
			this.practical = practical;
		}
		public int getTheoryPass() {
			return theoryPass;
		}
		public void setTheoryPass(int theoryPass) {
			this.theoryPass = theoryPass;
		}
		public int getPracticalPass() {
			return practicalPass;
		}
		public void setPracticalPass(int practicalPass) {
			this.practicalPass = practicalPass;
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		public int getPass() {
			return pass;
		}
		public void setPass(int pass) {
			this.pass = pass;
		}
}
