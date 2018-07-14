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
<c:url value="/listNonTenderOrderPagination.html" var="pagedLink">
	  <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Purchase Order List" />
<meta name="keywords" content="Purchase Order List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Non Tender Order List</title>

<link rel="stylesheet" href="/icam/css/inventory/nonTenderPurchaseOrderList.css" />
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
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Non Tender Order List
	</h1>
</div>

	<c:if test="${successStatus != null}">
	<div class="successbox" id="successbox" style="visibility:visible;">
		<span id="successmsg" style="visibility:visible;">Order Successfully Updated.</span>	
	</div>
	</c:if>
	<c:if test="${failStatus != null}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Order Update Fail!</span>	
			</div>
	</c:if>
	
	<c:choose>
	<c:when test="${commodityPagedListHolder.pageList eq null}">
		<div class="btnsarea01" >
			<div class="infomsgbox" style="visibility: visible;">
				<span>No More Order(s) To Approve</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<table  id="purchaseOrderTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>				
				<th>Session</th>
				<th>Order Created On</th>
				<th>Order Number</th>
				<th>Order Status</th>
				<th>Remarks</th>
				<th>Approve Order</th>
				<th>Delete Order</th>
			</tr>
			<c:forEach var="commodity" items="${commodityPagedListHolder.pageList}" varStatus="i">
				<tr>
					
					<td>
						${commodity.inventorySession.academicYearName}
					</td>
					<td>
						${commodity.date}
					</td>
					<td>
						${commodity.orderNumber}
					</td>
					<td>
						${commodity.status}
					</td>
					<td>
						${commodity.commodityDesc}
					</td>
					<td>
						<a href="updateNonTenderOrder.html?orderCode=${commodity.commodityCode}&value=approve" ><input type="button" value="Approved" class="submitbtn"  name="Approved"/></a>
					</td>
					<td>
						<a href="updateNonTenderOrder.html?orderCode=${commodity.commodityCode}&value=delete" ><input type="button" value="Delete" class="clearbtn" name="Delete"/></a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${commodityPagedListHolder}" pagedLink="${pagedLink}"/></td>
			</tr>
		</table>	
		<div class="btnsarea01">
			<div class="infomsgbox" id="infomsgbox" >
				<span id="infomsg"></span>	
			</div>
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			
		</div>
	
	</c:otherwise>
</c:choose>
</body>
</html>