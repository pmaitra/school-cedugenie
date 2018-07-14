package com.qts.icam.dao.tender;

import java.util.List;

import com.qts.icam.model.tender.Tender;
import com.qts.icam.model.tender.TenderCategory;
import com.qts.icam.model.tender.TenderType;

public interface TenderDao {
	
	public List <TenderType> getAllTenderType();
	
	public List <TenderCategory> getAllTenderCategory();
	
	public List <TenderCategory> getAllTenderSubCategory(String tenderSubCategory);

	public String submitTenderAttachmentDoc(Tender tender);

	public String submitTenderForm(Tender tender);

}
