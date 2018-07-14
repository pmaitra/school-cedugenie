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
<title>Purchase Order Approve</title>

<link rel="stylesheet" href="/icam/css/inventory/inventoryPurchaseOrderDetails.css" />
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
</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Purchase Order Approve
	</h1>
</div>
	
	
		<c:set var="numberOfCloseReceivedOnPage" value="0" scope="page" />
		<table id="purchaseOrderTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>			
			<c:choose>
				<c:when test="${userAccessType == 'PRINCIPAL' || userAccessType == 'VICE PRINCIPAL' || userAccessType == 'ADMINISTRATIVE OFFICER'}">
				<th colspan="7">
					PO Details
				</th>
				</c:when>
				<c:otherwise>								
				<th colspan="8">
					PO Details
				</th>
				</c:otherwise>
			</c:choose>
			</tr>
			<tr>
				<th>Order No</th>
				<th>Quarter Master Session</th>
				<th>Vendor Name</th>
				<th>Order Status</th>
				<th>Receive Status</th>
				<th>Issue Date</th>
				<th>Close Date</th>
				<c:choose>
					<c:when test="${userAccessType == 'PRINCIPAL' || userAccessType == 'VICE PRINCIPAL' || userAccessType == 'ADMINISTRATIVE OFFICER'}">
					</c:when>
					<c:otherwise>					
						<th>Recieve PO</th>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<td>
					${purchaseOrder.purchaseOrderCode}
				</td>
				<td>
					${purchaseOrder.inventorySession.academicYearName}
				</td>
				<td>
					${purchaseOrder.vendor.vendorName}
<%-- 				<input type="hidden"  name="vendor.vendorName" value="${purchaseOrder.vendor.vendorName}" readonly="readonly"> --%>
<%-- 				<input type="hidden"  name="vendor.vendorCode" value="${purchaseOrder.vendor.vendorCode}" readonly="readonly"> --%>
					
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
				<c:choose>
					<c:when test="${userAccessType == 'PRINCIPAL' || userAccessType == 'VICE PRINCIPAL' || userAccessType == 'ADMINISTRATIVE OFFICER'}">
					</c:when>
					<c:otherwise>					
						<c:choose>
							<c:when test="${purchaseOrder.registrarApprove == true && purchaseOrder.vicePrincipalApprove == true && purchaseOrder.principalApprove == true}">				
								<td>
									<input type="button" class="greenbtn" value="RECIEVE" onclick="window.open('purchaseOrderDetails.html?purchaseOrderCode=${purchaseOrder.purchaseOrderId}&poStatus=Complete','_self')">
								</td>
							</c:when>
							<c:otherwise>					
								<td>PO not Approved by all till now.</td>
							</c:otherwise>
						</c:choose>									
					</c:otherwise>
				</c:choose>
			</tr>
		</table>
		
		<form:form id="approvePO" action="approvePurchaseOrder.html" method="POST">
		<c:if test="${userAccessType == 'PRINCIPAL' || userAccessType == 'VICE PRINCIPAL' || userAccessType == 'ADMINISTRATIVE OFFICER'}">
		<table id="poApproveTable" class="midsec1">
			<tr>
				<th>
					Purchase Order Approve Status
					<input type="hidden"  name="purchaseOrderId" value="${purchaseOrder.purchaseOrderId}" readonly="readonly">
				</th>
			</tr>
			<tr>
				<input type="hidden" id="accessType" value="${userAccessType}">
				<c:if test="${userAccessType == 'ADMINISTRATIVE OFFICER'}">
					<c:if test="${purchaseOrder.registrarApprove == false}">
					<td style="text-align: center;">
						<input id="registrarApproveSubmit" class="submitbtn" type="submit" value="Approve PO" name="registrar" >
					</td>
					</c:if>
					<c:if test="${purchaseOrder.registrarApprove == true}">
					<td>This PO is Approved by You.</td>
					</c:if>
				</c:if>
				<c:if test="${userAccessType == 'VICE PRINCIPAL'}">
					<c:if test="${purchaseOrder.registrarApprove == false}">
					<td>Registrar Has Not Yet Approved this PO</td>
					</c:if>
					<c:if test="${purchaseOrder.registrarApprove == true && purchaseOrder.vicePrincipalApprove == false}">
					<td>
						<input id="vcApproveSubmit" class="submitbtn" type="submit" value="Approve PO" name="vicePrincipal" >
					</td>
					</c:if>
					<c:if test="${purchaseOrder.registrarApprove == true && purchaseOrder.vicePrincipalApprove == true}">
					<td>This PO is Approved by You.</td>
					</c:if>			
				</c:if>
				<c:if test="${userAccessType == 'PRINCIPAL'}">
					<c:if test="${purchaseOrder.registrarApprove == false}">
					<td> Approval for this PO is required from the REGISTRAR. </td>
					</c:if>
					<c:if test="${purchaseOrder.registrarApprove == true && purchaseOrder.vicePrincipalApprove == false}">
					<td> Approval for this PO is required from the VICE-PRINCIPAL. </td>
					</c:if>	
					<c:if test="${purchaseOrder.registrarApprove == true && purchaseOrder.vicePrincipalApprove == true && purchaseOrder.principalApprove == false}">
					<td>
						<input id="princiApproveSubmit" class="submitbtn" type="submit" value="Approve PO" name="principal" >
					</td>
					</c:if>
					<c:if test="${purchaseOrder.registrarApprove == true && purchaseOrder.vicePrincipalApprove == true && purchaseOrder.principalApprove == true}">
					<td> This PO is Approved by You and Ready to be recieved. </td>
					</c:if>	
				</c:if>
			</tr>
		</table>
		</c:if>
		</form:form>
		
		<table id="purchaseOrderTableDetails" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th colspan="6">Item Details</th>
			</tr>
			<tr>
				<th>SL. No</th>
				<th>Commodity</th>
				<th>Commodity Type</th>
				<th>Unit</th>
				<th>Ordered</th>
				<th>Received</th>
			</tr>
			<c:forEach var="purchaseOrderDetails" items="${purchaseOrder.purchaseOrderDetailsList}" varStatus="i">
				<tr>
					<td>
						${i.count}
					</td>
					<td>
						${purchaseOrderDetails.purchaseOrderDetailsName}
<%-- 						<input type="hidden"  id="commodityName${i.count}" name="purchaseOrderDetailsList[${i.index}].purchaseOrderDetailsName"  value="${purchaseOrderDetails.purchaseOrderDetailsName}" readonly="readonly"> --%>
<%-- 						<input type="hidden"  name="purchaseOrderDetailsList[${i.index}].purchaseOrderDetailsId" value="${purchaseOrderDetails.purchaseOrderDetailsId}" readonly="readonly"> --%>
						<c:set var="numberOfCommodityOnPage" value="${i.count}" scope="page" />
					</td>
					<td>
						${purchaseOrderDetails.horseRation eq true ? 'Horse Ration' : ''}
					</td>
					<td>
						${purchaseOrderDetails.unitName}
<%-- 					  <input type="hidden"  name="purchaseOrderDetailsList[${i.index}].unitName"  value="${purchaseOrderDetails.unitName}" readonly="readonly"> --%>
						
					</td>
					<td>
						${purchaseOrderDetails.quantityOrdered}
<%-- 						<input type="hidden"  id="ordered${i.count}" name="purchaseOrderDetailsList[${i.index}].quantityOrdered" value="${purchaseOrderDetails.quantityOrdered}" readonly="readonly"> --%>
					</td>
					<td>
						${purchaseOrderDetails.quantityReceived}
<%-- 						<input type="hidden"  id="received${i.count}" value="${purchaseOrderDetails.quantityReceived}" readonly="readonly"> --%>
					</td>
				</tr>
			</c:forEach>
		</table>	
		
		<input type="button" class="editbtn" value="BACK" onclick="window.open('purchaseOrderList.html','_self')">
				
		<input id="numberOfCommodityOnPage"  type="hidden" value="${numberOfCommodityOnPage}">
		<input id="numberOfCloseReceivedOnPage"  type="hidden" value="${numberOfCloseReceivedOnPage}">
		
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
</body>
</html>