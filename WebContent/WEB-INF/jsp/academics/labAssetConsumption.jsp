<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>

<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Add Vendor" />
<meta name="keywords" content="Add Car Maintenance" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Lab Asset Consumption</title>

<link rel="stylesheet" href="/cedugenie/css/academics/labAssetConsumption.css" />

<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/cedugenie/Cal/default.css"/>

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/academics/labAssetConsumption.js"></script>

	<script src="/cedugenie/Cal/zebra_datepicker.js"></script>
	<script>
		 $(document).ready(function() {
			 
			 $('#startDate').Zebra_DatePicker();
			 $('#startDate').Zebra_DatePicker({
			 	format: 'd/m/Y',
			 	direction: false
			 });
		
			 $('#endDate').Zebra_DatePicker();
			 $('#endDate').Zebra_DatePicker({
			 	format: 'd/m/Y',
			 	direction: false
			 });			 
			 
		});
		 
	
	</script>
</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Asset Consumption
		</h1>
	</div>
	
	<c:if test="${!insertStatus eq 'fail'}">
		<div class="successbox" id="successbox" style="visibility:visible;">
			<span id="successmsg" style="visibility:visible;">${insertStatus}</span>	
		</div>
	</c:if>
	<c:if test="${insertStatus eq 'fail'}">
		<div class="errorbox" id="errorbox" style="visibility: visible;">
			<span id="errormsg">Problem Occurred While Saving data !</span>	
		</div>
	</c:if>
		
	<form:form method="POST" action="submitAssetConsumption.html">
		<table id="assetConsumptionTable" cellspacing="0" cellpadding="0" class="midsec">	
			<tr>
				<th>Asset :: </th>
				<th>${assetName}</th>				
			</tr>		
			<tr>
				<th>From Date :: </th>
				<td><input type="text" class="textfield" name="startDate" id="startDate"/></td>				
			</tr>		
			<tr>	
				<th>To Date :: </th>
				<td><input type="text" class="textfield" name="endDate" id="endDate"/></td>								
			</tr>			
			<tr>				
				<th>Consumed Quantity :: </th>
				<td><input type="text" class="textfield" name="consumedQuantity" value="0.0" id="consumedQuantity" onfocus="setZero(this);"/></td>
			</tr>			
			<tr>	
				<th>Unit :: </th>
				<td>
					<select id="unit" name="unit" class="defaultselect" >
						<option value="${assetUnit}" ${assetUnit eq 'Pcs' ? 'selected=selected' : ''}>Pcs</option>
						<option value="${assetUnit}" ${assetUnit eq 'Kg' ? 'selected=selected' : ''}>Kg</option>
						<option value="${assetUnit}" ${assetUnit eq 'Pkt' ? 'selected=selected' : ''}>Pkt</option>
						<option value="${assetUnit}" ${assetUnit eq 'Bottle' ? 'selected=selected' : ''}>Bottle</option>
						<option value="${assetUnit}" ${assetUnit eq 'Ltr' ? 'selected=selected' : ''}>Ltr</option>				
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input id="clear" class="clearbtn" type="reset" value="Clear">					
					<input id="submit" class="submitbtn" type="submit" value="Submit">
				</td>
			</tr>
		</table>
		<input type="hidden" name="asset.assetId" id="assetId" value="${assetId}"/>
		<input type="hidden" name="currentQuantity" id="currentQuantity" value="${currentQuantity}"/>
	</form:form>
	
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>			
	</div>
	
</body>
</html>