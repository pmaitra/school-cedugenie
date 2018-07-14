<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/commodityVendorMappingPagination.html" var="pagedLink" >
	  <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Create Daily Ration Purchase Order" />
<meta name="keywords" content="Create Daily Ration Purchase Order" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Daily Ration Purchase Order</title>

<link rel="stylesheet" href="/icam/css/inventory/createDailyRationPurchaseOrder.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/showHideField.js"></script>
<script type="text/javascript" src="/icam/js/inventory/createDailyRationPurchaseOrder.js"></script>
<script type="text/javascript" src="/icam/js/common/validateSearch.js"></script>

</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Daily Ration Purchase Order
	</h1>
</div>
<c:choose>
	<c:when test="${inventorySession eq null}">
		<div class="btnsarea01" >
			<div class="infomsgbox" style="visibility: visible;">
				<span>No Academic Year found. </span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	
	<c:if test="${successStatus != null}">
	<div class="successbox" id="successbox" style="visibility:visible;">
		<span id="successmsg" style="visibility:visible;">Successfully Updated!</span>	
	</div>
	</c:if>
	<c:if test="${failStatus != null}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Update Fail!</span>	
			</div>
	</c:if>
	
	<form:form id="submitDailyRationPO" name="submitDailyRationPO" action="submitDailyRationPO.html" method="POST">
		<table  id="nonTenderVendorTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<td>Session: </td>
				<td> <input type="text" value="${inventorySession.academicYearName}" id="houseMasterSession" name="inventorySession.academicYearName" class="textfield" readonly="readonly" > </td>
			</tr>
			<tr>
				<td>Daily Ration Vender: </td>
				<td>
					<input type="text" value="" id="nonTenderVendor" name="vendor.vendorName" class="textfield" >
				</td>
			</tr>
			<tr>
				<td>Daily Ration Vender Contact No: </td>
				<td>
					<input type="text" value="" id="nonTenderVendorContact" name="vendor.vendorContactNumber" class="textfield" >
				</td>
			</tr>
			<tr>
				<td>Daily Ration Vendor Address: </td>
				<td>
					<input type="text" value="" id="nonTenderVendorAddress" name="vendor.address" class="textfield" >
				</td>
			</tr>
		</table>
		<table  id="dailyRationCommodityTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Commodity Name</th>
				<th>Commodity Desc</th>
				<th>A/C-Unit</th>
				<th>Rate</th>
				<th>Quantity</th>
				<th>Action</th>
			</tr>
				<tr>
					<td>
						<input type="text" value="" id="commodityName0" name="commodityList[0].commodityName" class="textfieldName" >					
					</td>
					<td>
						<input type="text" value="" id="commodityDesc0" name="commodityList[0].commodityDesc" class="textfield" >	
					</td>
					<td>
						<select id="unit0" name="commodityList[0].unitName" class="defaultselect">
							<option VALUE="" >Please select</option>
							<option value="Kg">Kg</option>
							<option value="Pkt">Pkt</option>
							<option value="Bott">Bott</option>
							<option value="Ltr">Ltr</option>
							<option value="-">UNKNOWN</option>						
						</select>
					</td>
					<td>
						<input type="text" onblur="validateCommodityRate(this)" onfocus="this.value='';" value="0.00" id="rate0" name="commodityList[0].purchaseRate" class="textfieldRate">
					</td>
					<td>
						<input type="text" onblur="validateCommodityRate(this)" onfocus="this.value='';" value="0.00" id="quantity0" name="commodityList[0].quantity" class="textfieldRate">
					</td>
					<td>
						<input id="addButton" class="greenbtn" type="button" value="Add" name="addButton">
					</td>
				</tr>			
		</table>	
				<input id="submit" class="greenbtn" type="submit" value="Submit" name="submit" onclick="return validateSubmitCommodityPurchaseOrderForm();">
				<input id="clear" class="clearbtn" type="reset" value="Clear">
		<br>
		<br>
		<div class="btnsarea01">
			<div class="infomsgbox" id="infomsgbox" >
				<span id="infomsg"></span>	
			</div>
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			
		</div>
	</form:form>
	</c:otherwise>
</c:choose>
</body>
</html>