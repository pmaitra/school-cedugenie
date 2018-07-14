<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Create Inventory Purchase Order" />
<meta name="keywords" content="Create Inventory Purchase Order" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Purchase Order</title>
<link rel="stylesheet" href="/icam/css/inventory/createInventoryPurchaseOrder.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" />


<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>

<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">
<link rel="stylesheet" href="/icam/Cal/jsDatePick_ltr.min.css">
<script src="/icam/Cal/jsDatePick.min.1.3.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
<script src="/icam/js/inventory/createInventoryPurchaseOrder.js"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Purchase Order
		</h1>
</div>
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
<form:form name="createInventoryPurchaseOrder" method="POST" action="getCommodityVendorMappingForPO.html">		
		
	<table cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<td>Quarter Master Session</td>
			 <td>
				<input type="text" value="${inventorySession.academicYearName}" id="houseMasterSession" name="inventorySession.academicYearName" class="textfield2" readonly="readonly" >
			</td>
			<td>Vendor<img class="required" src="/icam/images/required.gif" alt="Required"></td>
			<td>
				<select id="vendorCode" name="vendor.vendorCode" class="defaultselect">
							<option VALUE="" >Please select</option>
							<c:forEach var="vendor" items="${vendorList}" varStatus="i">
								<option value="${vendor.vendorCode}">${vendor.vendorName}</option>
							</c:forEach>
				</select>
			</td>
			<td>
				Horse Ration
			</td>
			<td>
				<input id="horseRationYes" type="checkbox" value="true" name="horseRation">
			</td>
			<td colspan="3" >
				<input class="greenbtn" type="submit" id="submit" name="submit"  value="Submit" onclick="return validateCreateInventoryPurchaseOrderForm();"/>
		</tr>
	</table>
</form:form>
<c:if test="${commodity != null}">
	<c:choose>
		<c:when test="${commodityList == null || empty commodityList}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
					<span id="infomsg">No commodity found.</span>	
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<form:form id="submitCommodityPurchaseOrder" name="submitCommodityPurchaseOrder" action="submitCommodityPurchaseOrder.html" method="POST">
				<input id="upperSubmit" class="greenbtn" type="submit" value="Submit" name="submit" onclick="return validateSubmitCommodityPurchaseOrderForm();">
				<input id="upperClear" class="clearbtn" type="reset" value="Clear">
				<br>
				<table  id="commodityTable" cellspacing="0" cellpadding="0" class="midsec1">
					<tr>
						<th colspan="7">
						House Master Session: ${commodity.inventorySession.academicYearName}<input type="hidden" value="${commodity.inventorySession.academicYearName}" id="houseMasterSession" name="inventorySession.academicYearName" class="textfield2" readonly="readonly" >
						&nbsp;&nbsp;
						
						Vendor: ${commodityList[0].vendor.vendorName}<input type="hidden" value="${commodity.vendor.vendorCode}" id="vendorCode" name="vendor.vendorCode" readonly="readonly" >
					</th>
					</tr>
					<tr>
						<th>SL.No.</th>
						<th>Commodity Name</th>
						<th>Commodity Type</th>
						<th>A/C-Unit</th>
						<th>Rate</th>
						<th>Quantity</th>
						<th>Remarks</th>
					</tr>
					<c:forEach var="commodity" items="${commodityList}" varStatus="i">
						<tr>
							<td>
								${i.count}
							</td>
							<td>
								${commodity.commodityName}
								<input type="hidden" value="${commodity.commodityName}" id="commodityName${i.count}" readonly="readonly">
								<input type="hidden" value="${commodity.commodityId}" id="commodityId${i.count}" name="commodityList[${i.index}].commodityId" readonly="readonly">
								<c:set var="numberOfCommodityOnPage" value="${i.count}" scope="page" />
							</td>
							<td>
								${commodity.commoditySubType}
							</td>
							<td>
								${commodity.unitName}
							</td>
							<td>
								${commodity.purchaseRate}
								<input type="hidden" name="commodityList[${i.index}].purchaseRate" value="${commodity.purchaseRate}">
							</td>
							<td>
								<input type="text" onblur="validateSubmitCommodityPurchaseOrderQuantity(this)" onfocus="this.value='';" value="0.00" id="quantity${i.count}" name="commodityList[${i.index}].quantity" class="textfield1">
							</td>
							<td>
								<input type="text" id="commodityDesc${i.count}" name="commodityList[${i.index}].commodityDesc" class="textfield2">
							</td>
						</tr>
					</c:forEach>
				
			</table>	
				<input id="lowerSubmit" class="greenbtn" type="submit" value="Submit" name="submit" onclick="return validateSubmitCommodityPurchaseOrderForm();">
				<input id="lowerClear" class="clearbtn" type="reset" value="Clear">
				<input id="numberOfCommodityOnPage"  type="hidden" value="${numberOfCommodityOnPage}">
				
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
</c:if>	
	<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
	</div>

</body>
</html>