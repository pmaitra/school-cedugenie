package com.qts.icam.model.library;

/**
 * @author anup.roy*/
public class Magazine {

	private String bookType;
	private String magazineObjectId;
	private String updatedBy;
	private String magazineEntryDate;
	private String magazineName;
	private MagazinePublisher magazinePublisher;
	private int magazinePeriod;
	private String magazineBillNo;
	private String magazineBillDate;
	private String magazineReceiveDate;
	private double magazineCost;
	private String magazineCode;	/* added by sourav.bhadra on 13-02-2018 */
	private String magazineCategory;	/* added by sourav.bhadra on 13-02-2018 */
	private String statusOfItemName;

	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	/**
	 * @return the magazineObjectId
	 */
	public String getMagazineObjectId() {
		return magazineObjectId;
	}
	/**
	 * @param magazineObjectId the magazineObjectId to set
	 */
	public void setMagazineObjectId(String magazineObjectId) {
		this.magazineObjectId = magazineObjectId;
	}
	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}
	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * @return the magazineEntryDate
	 */
	public String getMagazineEntryDate() {
		return magazineEntryDate;
	}
	/**
	 * @param magazineEntryDate the magazineEntryDate to set
	 */
	public void setMagazineEntryDate(String magazineEntryDate) {
		this.magazineEntryDate = magazineEntryDate;
	}
	/**
	 * @return the magazineName
	 */
	public String getMagazineName() {
		return magazineName;
	}
	/**
	 * @param magazineName the magazineName to set
	 */
	public void setMagazineName(String magazineName) {
		this.magazineName = magazineName;
	}
	/**
	 * @return the magazinePublisher
	 */
	public MagazinePublisher getMagazinePublisher() {
		return magazinePublisher;
	}
	/**
	 * @param magazinePublisher the magazinePublisher to set
	 */
	public void setMagazinePublisher(MagazinePublisher magazinePublisher) {
		this.magazinePublisher = magazinePublisher;
	}
	/**
	 * @return the magazinePeriod
	 */
	public int getMagazinePeriod() {
		return magazinePeriod;
	}
	/**
	 * @param magazinePeriod the magazinePeriod to set
	 */
	public void setMagazinePeriod(int magazinePeriod) {
		this.magazinePeriod = magazinePeriod;
	}
	/**
	 * @return the magazineBillNo
	 */
	public String getMagazineBillNo() {
		return magazineBillNo;
	}
	/**
	 * @param magazineBillNo the magazineBillNo to set
	 */
	public void setMagazineBillNo(String magazineBillNo) {
		this.magazineBillNo = magazineBillNo;
	}
	/**
	 * @return the magazineBillDate
	 */
	public String getMagazineBillDate() {
		return magazineBillDate;
	}
	/**
	 * @param magazineBillDate the magazineBillDate to set
	 */
	public void setMagazineBillDate(String magazineBillDate) {
		this.magazineBillDate = magazineBillDate;
	}
	/**
	 * @return the magazineReceiveDate
	 */
	public String getMagazineReceiveDate() {
		return magazineReceiveDate;
	}
	/**
	 * @param magazineReceiveDate the magazineReceiveDate to set
	 */
	public void setMagazineReceiveDate(String magazineReceiveDate) {
		this.magazineReceiveDate = magazineReceiveDate;
	}
	/**
	 * @return the magazineCost
	 */
	public double getMagazineCost() {
		return magazineCost;
	}
	/**
	 * @param magazineCost the magazineCost to set
	 */
	public void setMagazineCost(double magazineCost) {
		this.magazineCost = magazineCost;
	}
		public String getMagazineCode() {
		return magazineCode;
	}
	public void setMagazineCode(String magazineCode) {
		this.magazineCode = magazineCode;
	}
	public String getMagazineCategory() {
		return magazineCategory;
	}
	public void setMagazineCategory(String magazineCategory) {
		this.magazineCategory = magazineCategory;
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
