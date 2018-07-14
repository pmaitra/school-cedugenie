package com.qts.icam.model.common;

public class ChartQueryData {

	String sqlId;
	String sql;
	String role;
	boolean isDefault;
	String chartType;
	String chartLabel;
	String caption;
	String subCaption;
	String xaxisname;
	String yaxisname;
	String numberprefix;
	String theme;
	
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

	public String getSqlId() {
		return sqlId;
	}
	
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
	
	public String getSql() {
		return sql;
	}
	
	public void setSql(String sql) {
		this.sql = sql;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean isDefault() {
		return isDefault;
	}
	
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
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
	
	public String toString(){
		return "id : " + getSqlId() + " , sql : " + getSql() + " , role : " + getRole() + " , default : " + isDefault + " , Chart Type : " + getChartType() + " , Chart label : " + getChartLabel();
	}
}
