package com.qts.icam.model.administrator;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class SalaryForXml {
	List<SalaryDetailsForXml> salarydetailsList;

	public List<SalaryDetailsForXml> getSalarydetailsList() {
		return salarydetailsList;
	}
	@XmlElement(name="Breakup")
	public void setSalarydetailsList(List<SalaryDetailsForXml> salarydetailsList) {
		this.salarydetailsList = salarydetailsList;
	}
}
