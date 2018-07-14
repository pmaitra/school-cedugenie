/**
 * 
 */
package com.qts.icam.model.common;

import java.util.List;


/**
 * @author saikat.das
 * 
 */
public class VendorType {
	
	private int vendorId;
	private String vendorObjectId;
	private String updatedBy;
	private String vendorTypeCode;
	private String vendorTypeDesc;
	private String vendorTypeName;
	private List<Vendor> vendorList;
	private List<Item> itemList;
	
	
	
	
	public List<Vendor> getVendorList() {
		return vendorList;
	}
	public void setVendorList(List<Vendor> vendorList) {
		this.vendorList = vendorList;
	}
	
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getVendorObjectId() {
		return vendorObjectId;
	}
	public void setVendorObjectId(String vendorObjectId) {
		this.vendorObjectId = vendorObjectId;
	}
	public String getVendorTypeCode() {
		return vendorTypeCode;
	}
	public void setVendorTypeCode(String vendorTypeCode) {
		this.vendorTypeCode = vendorTypeCode;
	}
	public String getVendorTypeDesc() {
		return vendorTypeDesc;
	}
	public void setVendorTypeDesc(String vendorTypeDesc) {
		this.vendorTypeDesc = vendorTypeDesc;
	}
	public String getVendorTypeName() {
		return vendorTypeName;
	}
	public void setVendorTypeName(String vendorTypeName) {
		this.vendorTypeName = vendorTypeName;
	}
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}
	public List<Item> getItemList() {
		return itemList;
	}
	
	

	
}
