<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Commodity Details" />
<meta name="keywords" content="Commodity Details" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Commodity Details</title>

<link rel="stylesheet" href="/icam/css/inventory/commodityDetails.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/inventory/commodityDetails.js"></script>

</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Commodity Details
	</h1>
</div>

<form:form method="POST" id="updateCommodity" name="updateCommodity" action="updateCommodity.html" >
	<table cellspacing="0" cellpadding="0" class="midsec" id="tabUpdateCommodity">		
		<tr>
			<th>Commodity Name :: </th>
			<td>
				${commodity.commodityName}
				<input type="hidden" name="commodityId" id="commodityId" value="${commodity.commodityId}" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th>Commodity Desc :: </th>
			<td>
				<input type="text" name="commodityDesc" id="commodityDesc" class="textfield1" value="${commodity.commodityDesc}" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th>Commodity Type :: <img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<td>
				<select name="commodityType" id="commodityType" class="defaultselect" disabled="disabled">
					<option value="">Select...</option>
					<c:forEach var="varCommodityType" items="${commodityTypeList}">
							<option value="${varCommodityType.commodityTypeCode}" ${varCommodityType.commodityTypeName eq commodity.commodityType ? 'selected=selected' : ''}>${varCommodityType.commodityTypeName}</option>
					</c:forEach>
 				</select>
			</td>
		</tr>
		<tr>
			<th>Commodity Sub Type :: <img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<td>
				<select name="commoditySubType" id="commoditySubType" class="defaultselect" disabled="disabled">
					<option value="">Select...</option>
					<c:forEach var="varCommoditySubType" items="${commoditySubTypeList}">
							<option value="${varCommoditySubType.commoditySubTypeCode}" ${varCommoditySubType.commoditySubTypeName eq commodity.commoditySubType ? 'selected=selected' : ''}>${varCommoditySubType.commoditySubTypeName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>Horse Ration :: <img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<th>
				<input type="checkbox" name="horseRation" id="horseRationYes" value="true" ${commodity.horseRation eq true ? 'checked' : ''} disabled="disabled"> Yes
			</th>
		</tr>
		<tr>
		<th>Ledger No. :: <input type="text" class="smalltextfield" name="ledgerNumber" id="ledgerNumber" value="${commodity.ledgerNumber}" readonly="readonly"></th>
		<th>Page No.:: <input type="text" class="smalltextfield" name="pageNumber" id="pageNumber" value="${commodity.pageNumber}" readonly="readonly"></th>
		</tr>
		<tr>
			<th>
				Threshold ::
			</th>
			<th>
				<input type="text" class="textfield1" name="threshold" id="threshold" value="${commodity.threshold}" onblur="validateUpdateCommodityThreshold(this)" onfocus="this.value='';" disabled="disabled">
			</th>
	</tr>
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>			
		<input id="update" class="greenbtn" type="submit" value="Update" name="update" disabled="disabled" onclick="return validateCommodityDetailsForm();">
		<input id="delete" class="submitbtn" type="submit" value="Delete" name="delete" disabled="disabled">
		<input id="clear" class="clearbtn" type="reset" value="Clear">
		<input id="edit" class="editbtn" type="button" value="Edit"  onclick="editableCommodityDetailsForm();">
	</div>
</form:form>
</body>
</html>