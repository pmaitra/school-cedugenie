package com.qts.icam.model.common;

import java.util.List;

public class Item {

	private int itemId;
	private String itemObjectId;
	private String itemCode;
	private String itemName;
	private String itemDesc;
	private List<Vendor> itemVendors;
	private int threshold;
	private double sellingRate;
	private double purchaseRate;
	private boolean selected;
	private double inStock;
	private String status;
	private String itemType;
	private int allotedItem;
	private String updatedBy;
	private String vendorCode;
	private double oldSellingRate;
	private String vendor;
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemObjectId() {
		return itemObjectId;
	}
	public void setItemObjectId(String itemObjectId) {
		this.itemObjectId = itemObjectId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public List<Vendor> getItemVendors() {
		return itemVendors;
	}
	public void setItemVendors(List<Vendor> itemVendors) {
		this.itemVendors = itemVendors;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public double getSellingRate() {
		return sellingRate;
	}
	public void setSellingRate(double sellingRate) {
		this.sellingRate = sellingRate;
	}
	public double getPurchaseRate() {
		return purchaseRate;
	}
	public void setPurchaseRate(double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public double getInStock() {
		return inStock;
	}
	public void setInStock(double inStock) {
		this.inStock = inStock;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public double getOldSellingRate() {
		return oldSellingRate;
	}
	public void setOldSellingRate(double oldSellingRate) {
		this.oldSellingRate = oldSellingRate;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public int getAllotedItem() {
		return allotedItem;
	}
	public void setAllotedItem(int allotedItem) {
		this.allotedItem = allotedItem;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
		
}
