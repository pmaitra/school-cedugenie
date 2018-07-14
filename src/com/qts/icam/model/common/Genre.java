package com.qts.icam.model.common;

/**
 * @author anup.roy
 * */

public class Genre {
	
	private String genreName;
	private String genreCode;
	private String genreDesc;
	private String genreObjectId;
	private String updatedBy;
	
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	public String getGenreCode() {
		return genreCode;
	}
	public void setGenreCode(String genreCode) {
		this.genreCode = genreCode;
	}
	public String getGenreDesc() {
		return genreDesc;
	}
	public void setGenreDesc(String genreDesc) {
		this.genreDesc = genreDesc;
	}
	public String getGenreObjectId() {
		return genreObjectId;
	}
	public void setGenreObjectId(String genreObjectId) {
		this.genreObjectId = genreObjectId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	} 
}
