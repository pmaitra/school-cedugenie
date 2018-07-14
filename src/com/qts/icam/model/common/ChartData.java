package com.qts.icam.model.common;

import java.util.List;

public class ChartData {

	String chartType;
	String chartLabel;
	String caption;
	String subCaption;
	String xaxisname;
	String yaxisname;
	String numberprefix;
	String theme;
	List<ChartValuesModel> chartValuesModelList;
	
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getXaxisname() {
		return xaxisname;
	}
	public void setXaxisname(String xaxisname) {
		this.xaxisname = xaxisname;
	}
	public String getYaxisname() {
		return yaxisname;
	}
	public void setYaxisname(String yaxisname) {
		this.yaxisname = yaxisname;
	}
	public String getNumberprefix() {
		return numberprefix;
	}
	public void setNumberprefix(String numberprefix) {
		this.numberprefix = numberprefix;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getSubCaption() {
		return subCaption;
	}
	public void setSubCaption(String subCaption) {
		this.subCaption = subCaption;
	}
	public List<ChartValuesModel> getChartValuesModelList() {
		return chartValuesModelList;
	}
	public void setChartValuesModelList(List<ChartValuesModel> chartValuesModelList) {
		this.chartValuesModelList = chartValuesModelList;
	}
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	public String getChartLabel() {
		return chartLabel;
	}
	public void setChartLabel(String chartLabel) {
		this.chartLabel = chartLabel;
	}
	
	
}
