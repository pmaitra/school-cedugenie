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
<meta name="description" content="Page to Purchase Order " />
<meta name="keywords" content="Purchase Order Details" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Goods To Mess Details</title>

<link rel="stylesheet" href="/icam/css/inventory/goodsToMessDetails.css" />
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
<script type="text/javascript" src="/icam/js/inventory/goodsToMessDetails.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Goods To Mess Details
	</h1>
</div>
<c:choose>
	<c:when test="${purchaseOrder eq null}">
		<div class="btnsarea01" >
			<div class="infomsgbox" style="visibility: visible;">
				<span>No Goods For Mess found. </span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	
	<form:form id="goodsOrderDetails" name="goodsOrderDetails" action="submitGoodsToMessDetails.html" method="POST">
		<c:set var="numberOfCloseReceivedOnPage" value="0" scope="page" />
		<table  id="purchaseOrderTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Order No</th>
				<th>House Master Session</th>
				<th>Order Status</th>
				<th>Receive Status</th>
				<th>Order Issue Date</th>
				<th>Order Close Date</th>
				<th></th>
			</tr>
			<tr>
				<td>
					${purchaseOrder.purchaseOrderId}
					<input type="hidden"  name="purchaseOrderId" value="${purchaseOrder.purchaseOrderId}" readonly="readonly">
				</td>
				<td>
					${purchaseOrder.inventorySession.academicYearName}
				</td>				
				<td>
					${purchaseOrder.orderStatus}
				</td>
				<td>
					${purchaseOrder.receiveStatus}
				</td>
				<td>
					${purchaseOrder.orderOpenDate}
				</td>
				<td>
					${purchaseOrder.orderCloseDate}
				</td>
				<td>
					<c:if test="${purchaseOrder.orderStatus ne 'CLOSED'}">
						<input id="submitClose" class="submitbtn" type="submit" value="Close" name="submitClose" >
					</c:if>
				</td>
			</tr>
		</table>
		<c:if test="${purchaseOrder.orderStatus ne 'CLOSED'}">
			<input id="upperSubmit" class="greenbtn" type="submit" value="Submit" name="submit" onclick="return validatePurchaseOrderDetailsForm();">
			<input id="upperClear" class="clearbtn" type="reset" value="Clear">
		</c:if>
		<table  id="purchaseOrderTableDetails" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>SL. No</th>
				<th>Commodity</th>
				<th>In Stock</th>
				<th>Ordered</th>
				<th>Received</th>
				<th>Remeaning</th>
				<th>Receiving</th>
				<th>Remarks</th>
			</tr>
			<c:forEach var="purchaseOrderDetails" items="${purchaseOrder.purchaseOrderDetailsList}" varStatus="i">
				<tr>
					<td>
						${i.count}
					</td>
					<td>
						${purchaseOrderDetails.purchaseOrderDetailsName}
						<input type="hidden"  id="commodityName${i.count}" name="purchaseOrderDetailsList[${i.index}].purchaseOrderDetailsName"  value="${purchaseOrderDetails.purchaseOrderDetailsName}" readonly="readonly">
						<input type="hidden"  name="purchaseOrderDetailsList[${i.index}].purchaseOrderDetailsId" value="${purchaseOrderDetails.purchaseOrderDetailsId}" readonly="readonly">
						<c:set var="numberOfCommodityOnPage" value="${i.count}" scope="page" />
					</td>
					<td>
						${purchaseOrderDetails.quantity}
						<input type="hidden"  id="inStockQuan${i.count}" name="inStockQuan${i.count}"  value="${purchaseOrderDetails.quantity}" readonly="readonly">
					</td>
					<td>
						${purchaseOrderDetails.quantityOrdered}
						<input type="hidden"  id="ordered${i.count}" name="purchaseOrderDetailsList[${i.index}].quantityOrdered" value="${purchaseOrderDetails.quantityOrdered}" readonly="readonly">
					</td>
					<td>
						${purchaseOrderDetails.quantityReceived}
						<input type="hidden"  id="received${i.count}" value="${purchaseOrderDetails.quantityReceived}" readonly="readonly">
					</td>
					<td>
						<input type="text"  id="remeaning${i.count}" class="textfield1" value="${purchaseOrderDetails.quantityOrdered-purchaseOrderDetails.quantityReceived}" readonly="readonly">
					</td>
					<td>
					<c:choose>
						<c:when test="${(purchaseOrderDetails.quantityOrdered <= purchaseOrderDetails.quantityReceived)}">
							<input type="text" value="0.00" class="textfieldDisabled" disabled="disabled">
							<input type="hidden" onblur="validatePurchaseOrderDetailsQuantity(this,${i.count})" onfocus="this.value='';" value="0.00" id="quantity${i.count}" name="purchaseOrderDetailsList[${i.index}].quantity" class="textfield1">
							<c:set var="numberOfCloseReceivedOnPage" value="${numberOfCloseReceivedOnPage+1}" scope="page" />
						</c:when>
						<c:otherwise>
							<input type="text" onblur="validatePurchaseOrderDetailsQuantity(this,${i.count})" onfocus="this.value='';" value="0.00" id="quantity${i.count}" name="purchaseOrderDetailsList[${i.index}].quantity" class="textfield1" ${purchaseOrder.orderStatus eq 'CLOSED' ? 'disabled' : ''}>
						</c:otherwise>
					</c:choose>	
					</td>
					<td>
						<input type="text" id="purchaseOrderDetailsDesc${i.count}" name="purchaseOrderDetailsList[${i.index}].purchaseOrderDetailsDesc" class="textfield2">
					</td>
				</tr>
			</c:forEach>
		</table>	
		
		<input id="numberOfCommodityOnPage"  type="hidden" value="${numberOfCommodityOnPage}">
		<input id="numberOfCloseReceivedOnPage"  type="hidden" value="${numberOfCloseReceivedOnPage}">
		<c:if test="${purchaseOrder.orderStatus ne 'CLOSED'}">
			<c:if test="${numberOfCommodityOnPage ne numberOfCloseReceivedOnPage}">
				<input id="lowerSubmit" class="greenbtn" type="submit" value="Submit" name="submit" onclick="return validatePurchaseOrderDetailsForm();">
				<input id="lowerClear" class="clearbtn" type="reset" value="Clear">
			</c:if>
		</c:if>
		<script>
			validateNumberOfCloseReceivedOnPage();
		</script>
		
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