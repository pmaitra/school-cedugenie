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

<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Purchase Order Details" />
<meta name="keywords" content="Purchase Order Details" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Purchase Order Details</title>

<link rel="stylesheet" href="/icam/css/inventory/inventoryNonTenderPurchaseOrderDetails.css" />
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
<script type="text/javascript" src="/icam/js/common/validateSearch.js"></script>
<script type="text/javascript" src="/icam/js/inventory/inventoryNonTenderPurchaseOrderDetails.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Purchase Order Details
	</h1>
</div>
<c:choose>
	<c:when test="${purchaseOrder eq null}">
		<div class="btnsarea01" >
			<div class="infomsgbox" style="visibility: visible;">
				<span>No purchase order found. </span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	
	<form:form id="nonTenderPurchaseOrderDetails" name="nonTenderPurchaseOrderDetails" action="submitNonTenderPurchaseOrderDetails.html" method="POST">
		<input type="hidden"  name="purchaseOrderId"  value="${purchaseOrder.purchaseOrderId}">
		<input type="hidden"  name="objectId"  value="${purchaseOrder.objectId}">
		
		<table  id="nonTenderVendorTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<td>Order Number</td>
				<td> <input type="text" value="${purchaseOrder.purchaseOrderName}" id="purchaseOrderName" name="purchaseOrderName" class="textfield2" readonly="readonly" > </td>
			</tr>
			<tr>
				<td>Non Tender Vendor: </td>
				<td>
					<input type="text" value="${purchaseOrder.vendor.vendorName}" id="nonTenderVendor" name="vendor.vendorName" class="textfield2" >
				</td>
			</tr>
			<tr>
				<td>Non Tender Vendor Contact No: </td>
				<td>
					<input type="text" value="${purchaseOrder.vendor.vendorContactNumber}" id="nonTenderVendorContact" name="vendor.vendorContactNumber" class="textfield2" >
				</td>
			</tr>
			<tr>
				<td>Non Tender Address: </td>
				<td>
					<input type="text" value="${purchaseOrder.vendor.address}" id="nonTenderVendorAddress" name="vendor.address" class="textfield2" >
				</td>
			</tr>
		</table>
		
		<c:choose>
		<c:when test="${purchaseOrder.receiveStatus eq null}">
				<table  id="purchaseOrderTableDetails" cellspacing="0" cellpadding="0" class="midsec1">
					<tr>				
						<th>Serial No</th>
						<th>Item Name</th>
						<th>Quantity</th>
						<th>A/C-Unit </th>
						<th>Estimated Rate</th>	
						<th>Actual Paid</th>
						<th>Remarks</th>
					</tr>
					<c:forEach var="commodity" items="${purchaseOrder.commodityList}" varStatus="i">
						<tr>
							<td>
								${i.count}
								<input type="hidden"  id="commodityName${i.count}" name="commodityList[${i.index}].commodityDetailsId"  value="${commodity.commodityDetailsId}" readonly="readonly">
							</td>
							<td>
								${commodity.commodityName}
								<input type="hidden"  id="commodityName${i.count}" name="commodityList[${i.index}].commodityName"  value="${commodity.commodityName}" readonly="readonly">
							</td>
							<td>
								${commodity.quantity}
								<input type="hidden"  id="ordered${i.count}" name="commodityList[${i.index}].quantity" value="${commodity.quantity}" readonly="readonly">
							</td>					
							<td>
								${commodity.unitName}
							  <input type="hidden"  name="commodityList[${i.index}].unitName"  value="${commodity.unitName}" readonly="readonly">
							</td>
							<td>
								${commodity.purchaseRate}
							  <input type="hidden"  name="commodityList[${i.index}].purchaseRate"  value="${commodity.purchaseRate}" readonly="readonly">
							</td>
							<td>
								<input type="text"  id="sellingRate${i.count}" name="commodityList[${i.index}].sellingRate" class="textfield1">
							</td>				
							<td>
								<input type="text" id="purchaseOrderDetailsDesc${i.count}" name="commodityList[${i.index}].commodityDesc" class="textfield3">
							</td>
						</tr>
					</c:forEach>
				</table>
				
				<input type="submit" class="submitbtn" value="SUBMIT" id="submitButton" />
				<input type="reset" class="clearbtn" value="clear" />	
		</c:when>
		<c:otherwise>
			<table  id="purchaseOrderTableDetails" cellspacing="0" cellpadding="0" class="midsec1">
					<tr>				
						<th>Serial No</th>
						<th>Item Name</th>
						<th>Quantity</th>
						<th>A/C-Unit </th>
						<th>Estimated Rate</th>	
						<th>Actual Paid</th>
						<th>Remarks</th>
					</tr>
					<c:forEach var="commodity" items="${purchaseOrder.commodityList}" varStatus="i">
						<tr>
							<td>
								${i.count}
							</td>
							<td>
								${commodity.commodityName}								
							</td>
							<td>
								${commodity.quantity}
							</td>					
							<td>
								${commodity.unitName}
							</td>
							<td>
								${commodity.purchaseRate}
							</td>
							<td>
								${commodity.sellingRate}
							</td>				
							<td>
								${commodity.remarks}								
							</td>
						</tr>
					</c:forEach>
				</table>		
		</c:otherwise>
		</c:choose>				
		
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