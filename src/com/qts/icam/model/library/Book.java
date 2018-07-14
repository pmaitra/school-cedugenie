
package com.qts.icam.model.library;

import java.util.List;

import com.qts.icam.model.academics.Subject;
import com.qts.icam.model.common.Genre;



/**
 * @author saikat.das
 * 
 */
public class Book {
	private String bookObjectId;
	private List<BookId> bookIdList;
	private BookId bookId;
	private String bookCode;
	private String bookName;
	private String bookDesc;
	private BookPublisher bookPublisher;
	private String bookPublisherId;
	private List<Author> bookAuthorList;
	private BookCategory bookCategory;
	private BookCategory bookParentCategory;
	private String bookType;
	private BookMedium bookMedium;
	private String bookMediumId;
	private String bookIsbn;
	private String bookEdition;
	private BookLanguage bookLanguage;
	private String bookLanguageId;
	private String bookPlace;
	private String bookPeriodicity;
	private String bookBackIssue;
	private int totalNumberOfBookCopies;
	private int totalNumberOfBookCopiesAvailable;
	private int totalNumberOfBookCopiesLended;
	private int totalNumberOfBookCopiesReserved;
	private int totalNumberOfBookCopiesRetired;
	private int averageBookRating;
	private int userBookRating;
	private List<BookRequisition> bookRequisitionList;
	private List<BookAllocation> bookAllocationList;
	private BookRequest bookRequest;
	private String updatedBy;
	private int requisitionReceivedDateDetailsId;
	private String bookNote;
	private int threshold;
	private String bookPeriodicityPublisher;
	private Double price;
	private List<BookLanguage> bookLanguageList;
	private Genre genre;
	private String genreId;
	private List<Genre> genreList;

	private String accessionNumber;
	private Subject bookSubject;
	/**
	 * @author anup.roy
	 * specific fields for SSR*/
	private String bookEntryDate;
	private String publishingYear;
	private String pages;
	private String volume;
	private String source;
	private String billNo;
	private String billDate;	
	private double cost;
	private String classNo;
	private String bookNo;
	private String withdrawalNo;
	private String withdrawalDate;
	private String remarks;
	private String author;
	private String statusOfItemName;
	
	public String getBookObjectId() {
		return bookObjectId;
	}

	public void setBookObjectId(String bookObjectId) {
		this.bookObjectId = bookObjectId;
	}

	public List<BookId> getBookIdList() {
		return bookIdList;
	}

	public void setBookIdList(List<BookId> bookIdList) {
		this.bookIdList = bookIdList;
	}

	public String getBookCode() {
		return bookCode;
	}

	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

	public BookPublisher getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(BookPublisher bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public String getBookPublisherId() {
		return bookPublisherId;
	}

	public void setBookPublisherId(String bookPublisherId) {
		this.bookPublisherId = bookPublisherId;
	}

	public List<Author> getBookAuthorList() {
		return bookAuthorList;
	}

	public void setBookAuthorList(List<Author> bookAuthorList) {
		this.bookAuthorList = bookAuthorList;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	public BookCategory getBookParentCategory() {
		return bookParentCategory;
	}

	public void setBookParentCategory(BookCategory bookParentCategory) {
		this.bookParentCategory = bookParentCategory;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public BookMedium getBookMedium() {
		return bookMedium;
	}

	public void setBookMedium(BookMedium bookMedium) {
		this.bookMedium = bookMedium;
	}

	public String getBookMediumId() {
		return bookMediumId;
	}

	public void setBookMediumId(String bookMediumId) {
		this.bookMediumId = bookMediumId;
	}

	public String getBookIsbn() {
		return bookIsbn;
	}

	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}

	public String getBookEdition() {
		return bookEdition;
	}

	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}

	public BookLanguage getBookLanguage() {
		return bookLanguage;
	}

	public void setBookLanguage(BookLanguage bookLanguage) {
		this.bookLanguage = bookLanguage;
	}

	public String getBookLanguageId() {
		return bookLanguageId;
	}

	public void setBookLanguageId(String bookLanguageId) {
		this.bookLanguageId = bookLanguageId;
	}

	public String getBookPlace() {
		return bookPlace;
	}

	public void setBookPlace(String bookPlace) {
		this.bookPlace = bookPlace;
	}

	public String getBookPeriodicity() {
		return bookPeriodicity;
	}

	public void setBookPeriodicity(String bookPeriodicity) {
		this.bookPeriodicity = bookPeriodicity;
	}

	public String getBookBackIssue() {
		return bookBackIssue;
	}

	public void setBookBackIssue(String bookBackIssue) {
		this.bookBackIssue = bookBackIssue;
	}

	public int getTotalNumberOfBookCopies() {
		return totalNumberOfBookCopies;
	}

	public void setTotalNumberOfBookCopies(int totalNumberOfBookCopies) {
		this.totalNumberOfBookCopies = totalNumberOfBookCopies;
	}

	public int getTotalNumberOfBookCopiesAvailable() {
		return totalNumberOfBookCopiesAvailable;
	}

	public void setTotalNumberOfBookCopiesAvailable(
			int totalNumberOfBookCopiesAvailable) {
		this.totalNumberOfBookCopiesAvailable = totalNumberOfBookCopiesAvailable;
	}

	public int getTotalNumberOfBookCopiesLended() {
		return totalNumberOfBookCopiesLended;
	}

	public void setTotalNumberOfBookCopiesLended(
			int totalNumberOfBookCopiesLended) {
		this.totalNumberOfBookCopiesLended = totalNumberOfBookCopiesLended;
	}

	public int getTotalNumberOfBookCopiesReserved() {
		return totalNumberOfBookCopiesReserved;
	}

	public void setTotalNumberOfBookCopiesReserved(
			int totalNumberOfBookCopiesReserved) {
		this.totalNumberOfBookCopiesReserved = totalNumberOfBookCopiesReserved;
	}

	public int getTotalNumberOfBookCopiesRetired() {
		return totalNumberOfBookCopiesRetired;
	}

	public void setTotalNumberOfBookCopiesRetired(
			int totalNumberOfBookCopiesRetired) {
		this.totalNumberOfBookCopiesRetired = totalNumberOfBookCopiesRetired;
	}

	public int getAverageBookRating() {
		return averageBookRating;
	}

	public void setAverageBookRating(int averageBookRating) {
		this.averageBookRating = averageBookRating;
	}

	public List<BookRequisition> getBookRequisitionList() {
		return bookRequisitionList;
	}

	public void setBookRequisitionList(List<BookRequisition> bookRequisitionList) {
		this.bookRequisitionList = bookRequisitionList;
	}

	public List<BookAllocation> getBookAllocationList() {
		return bookAllocationList;
	}

	public void setBookAllocationList(List<BookAllocation> bookAllocationList) {
		this.bookAllocationList = bookAllocationList;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public BookRequest getBookRequest() {
		return bookRequest;
	}

	public void setBookRequest(BookRequest bookRequest) {
		this.bookRequest = bookRequest;
	}

	public int getRequisitionReceivedDateDetailsId() {
		return requisitionReceivedDateDetailsId;
	}

	public void setRequisitionReceivedDateDetailsId(
			int requisitionReceivedDateDetailsId) {
		this.requisitionReceivedDateDetailsId = requisitionReceivedDateDetailsId;
	}
	public String getBookNote() {
		return bookNote;
	}

	public void setBookNote(String bookNote) {
		this.bookNote = bookNote;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public String getBookPeriodicityPublisher() {
		return bookPeriodicityPublisher;
	}

	public void setBookPeriodicityPublisher(String bookPeriodicityPublisher) {
		this.bookPeriodicityPublisher = bookPeriodicityPublisher;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<BookLanguage> getBookLanguageList() {
		return bookLanguageList;
	}

	public void setBookLanguageList(List<BookLanguage> bookLanguageList) {
		this.bookLanguageList = bookLanguageList;
	}

	public int getUserBookRating() {
		return userBookRating;
	}

	public void setUserBookRating(int userBookRating) {
		this.userBookRating = userBookRating;
	}

	public BookId getBookId() {
		return bookId;
	}

	public void setBookId(BookId bookId) {
		this.bookId = bookId;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public String getGenreId() {
		return genreId;
	}

	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}

	public List<Genre> getGenreList() {
		return genreList;
	}

	public void setGenreList(List<Genre> genreList) {
		this.genreList = genreList;
	}

	public String getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(String accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public Subject getBookSubject() {
		return bookSubject;
	}

	public void setBookSubject(Subject bookSubject) {
		this.bookSubject = bookSubject;
	}

	/**
	 * @return the bookEntryDate
	 */
	public String getBookEntryDate() {
		return bookEntryDate;
	}

	/**
	 * @param bookEntryDate the bookEntryDate to set
	 */
	public void setBookEntryDate(String bookEntryDate) {
		this.bookEntryDate = bookEntryDate;
	}

	/**
	 * @return the publishingYear
	 */
	public String getPublishingYear() {
		return publishingYear;
	}

	/**
	 * @param publishingYear the publishingYear to set
	 */
	public void setPublishingYear(String publishingYear) {
		this.publishingYear = publishingYear;
	}

	/**
	 * @return the pages
	 */
	public String getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(String pages) {
		this.pages = pages;
	}

	/**
	 * @return the volume
	 */
	public String getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(String volume) {
		this.volume = volume;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the billNo
	 */
	public String getBillNo() {
		return billNo;
	}

	/**
	 * @param billNo the billNo to set
	 */
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	/**
	 * @return the billDate
	 */
	public String getBillDate() {
		return billDate;
	}

	/**
	 * @param billDate the billDate to set
	 */
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * @return the classNo
	 */
	public String getClassNo() {
		return classNo;
	}

	/**
	 * @return the bookNo
	 */
	public String getBookNo() {
		return bookNo;
	}

	/**
	 * @return the withdrawalNo
	 */
	public String getWithdrawalNo() {
		return withdrawalNo;
	}

	/**
	 * @return the withdrawalDate
	 */
	public String getWithdrawalDate() {
		return withdrawalDate;
	}

	/**
	 * @param classNo the classNo to set
	 */
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	/**
	 * @param bookNo the bookNo to set
	 */
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	/**
	 * @param withdrawalNo the withdrawalNo to set
	 */
	public void setWithdrawalNo(String withdrawalNo) {
		this.withdrawalNo = withdrawalNo;
	}

	/**
	 * @param withdrawalDate the withdrawalDate to set
	 */
	public void setWithdrawalDate(String withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the statusOfItemName
	 */
	public String getStatusOfItemName() {
		return statusOfItemName;
	}

	/**
	 * @param statusOfItemName the statusOfItemName to set
	 */
	public void setStatusOfItemName(String statusOfItemName) {
		this.statusOfItemName = statusOfItemName;
	}


}
