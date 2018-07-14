/**
 * 
 */
package com.qts.icam.model.library;

import java.util.List;

/**
 * @author saikat.das
 * 
 */
public class Author {

	private String authorObjectId;
	private String authorFullName;
	private String authorFirstName;
	private String authorMiddleName;
	private String authorLastName;
	private String authorTitle;
	private String authorEmail;
	private String authorContactNumber;
	private List<Book> authorBookList;
	private String updatedBy;

	public String getAuthorObjectId() {
		return authorObjectId;
	}

	public void setAuthorObjectId(String authorObjectId) {
		this.authorObjectId = authorObjectId;
	}

	public String getAuthorFullName() {
		return authorFullName;
	}

	public void setAuthorFullName(String authorFullName) {
		this.authorFullName = authorFullName;
	}

	public String getAuthorFirstName() {
		return authorFirstName;
	}

	public void setAuthorFirstName(String authorFirstName) {
		this.authorFirstName = authorFirstName;
	}

	public String getAuthorMiddleName() {
		return authorMiddleName;
	}

	public void setAuthorMiddleName(String authorMiddleName) {
		this.authorMiddleName = authorMiddleName;
	}

	public String getAuthorLastName() {
		return authorLastName;
	}

	public void setAuthorLastName(String authorLastName) {
		this.authorLastName = authorLastName;
	}

	public String getAuthorTitle() {
		return authorTitle;
	}

	public void setAuthorTitle(String authorTitle) {
		this.authorTitle = authorTitle;
	}

	public String getAuthorEmail() {
		return authorEmail;
	}

	public void setAuthorEmail(String authorEmail) {
		this.authorEmail = authorEmail;
	}

	public String getAuthorContactNumber() {
		return authorContactNumber;
	}

	public void setAuthorContactNumber(String authorContactNumber) {
		this.authorContactNumber = authorContactNumber;
	}

	public List<Book> getAuthorBookList() {
		return authorBookList;
	}

	public void setAuthorBookList(List<Book> authorBookList) {
		this.authorBookList = authorBookList;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
