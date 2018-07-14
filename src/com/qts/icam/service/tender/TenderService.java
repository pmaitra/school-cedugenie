package com.qts.icam.service.tender;

import java.util.List;

import com.qts.icam.model.tender.Tender;
import com.qts.icam.model.tender.TenderCategory;
import com.qts.icam.model.tender.TenderType;

public interface TenderService {

	public List<TenderType> getAllTenderType();
	
	public List<TenderCategory> getAllTenderCategory();
	
	public List<TenderCategory> getAllTenderSubCategory(String tenderCategory);

	public void tenderDocumentUpload(Tender tender);

	public String submitTenderForm(Tender tender);
	
}
