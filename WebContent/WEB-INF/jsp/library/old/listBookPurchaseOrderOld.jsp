<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/purchaseOrderListPagination.html" var="pagedLink">
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
<title>Purchase Order List</title>

<link rel="stylesheet" href="/sms/css/library/requisitionList.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<link href="/sms/css/common/pagination.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/library/listBookPurchaseOrder.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Purchase Order List
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>
<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<input type="checkbox" onclick="ShowAll(this)" />
	<label for="Type">All</label><br>
	<input type="checkbox" class="listShowHide" value="Order Code" onclick="ShowHideField('Order Code', 'lbpo', this)" checked="checked" />
	<label for="Order Code">Order Code</label><br>
	<input type="checkbox" class="listShowHide" value="Open Date" onclick="ShowHideField('Open Date', 'lbpo', this)" checked="checked"/>
	<label for="Open Date">Open Date</label><br>
	<input type="checkbox" class="listShowHide" value="Close Date" onclick="ShowHideField('Close Date', 'lbpo', this)" checked="checked"/>
	<label for="Close Date">Close Date</label><br>
	<input type="checkbox" class="listShowHide" value="Qty Ordered" onclick="ShowHideField('Qty Ordered', 'lbpo', this)" checked="checked" />
	<label for="Qty Ordered">Qty Ordered</label><br>
	<input type="checkbox" class="listShowHide" value="Qty Deficit" onclick="ShowHideField('Qty Deficit', 'lbpo', this)" checked="checked"/>
	<label for="Qty Deficit">Qty Deficit</label><br>
	<input type="checkbox" class="listShowHide" value="Qty Received" onclick="ShowHideField('Qty Received', 'lbpo', this)" checked="checked"/>
	<label for="Qty Received">Qty Received</label><br>
	<input type="checkbox" class="listShowHide" value="Total Amount" onclick="ShowHideField('Total Amount', 'lbpo', this)" checked="checked"/>
	<label for="Total Amount">Total Amount</label><br>
	<input type="checkbox" class="listShowHide" value="Paid Amount" onclick="ShowHideField('Paid Amount', 'lbpo', this)" checked="checked"/>
	<label for="Paid Amount">Paid Amount</label><br>
	<input type="checkbox" class="listShowHide" value="Receive Status" onclick="ShowHideField('Receive Status', 'lbpo', this)" checked="checked"/>
	<label for="Receive Status">Receive Status</label><br>
	<input type="checkbox" class="listShowHide" value="Payment Status" onclick="ShowHideField('Payment Status', 'lbpo', this)" checked="checked" />
	<label for="Payment Status">Payment Status</label><br>
	<input type="checkbox" class="listShowHide" value="Vendor" onclick="ShowHideField('Vendor', 'lbpo', this)" checked="checked" />
	<label for="Vendor">Vendor</label><br>
	<input type="checkbox" class="listShowHide" value="Receive" onclick="ShowHideField('Receive', 'lbpo', this)" checked="checked" />
	<label for="Receive">Receive</label><br>
	<input type="checkbox" class="listShowHide" value="Pay" onclick="ShowHideField('Pay', 'lbpo', this)" checked="checked" />
	<label for="Pay">Pay</label><br>
</div>
<c:choose>
	<c:when test="${pagedListHolder==null}">
		<div class="btnsarea01" style="visibility: visible;">
			<div class="errorbox" id="errorbox" >
				<span id="errormsg">No Book Purchase Order Found</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<table id="sfltable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<td colspan="5"> Book Requisition Code :: ${pagedListHolder.pageList[0].bookRequisition}</td>
			</tr>
		</table>
		<table id="lbpo" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Order Code</th>
				<th>Open Date</th>
				<th>Close Date</th>
				<th>Qty Ordered</th>
				<th>Qty Deficit</th>
				<th>Qty Received</th>
				<th>Total Amount</th>
				<th>Paid Amount</th>
				<th>Receive Status</th>
				<th>Payment Status</th>
				<th>Vendor</th>
				<th>Receive</th>
				<th>Pay</th>
			</tr>
			<c:forEach var="bookPurchaseOrderList" items="${pagedListHolder.pageList}">
				<tr>
					<td>
						${bookPurchaseOrderList.purchaseOrderCode}
					</td>
					<td>
						${bookPurchaseOrderList.purchaseOrderOpenDate}
					</td>
					<td>
						${bookPurchaseOrderList.purchaseOrderCloseDate}
					</td>
					<td>
						${bookPurchaseOrderList.totalQtyOrdered}
					</td>
					<td>
						${bookPurchaseOrderList.totalQtyDeficit}
					</td>
					<td>
						${bookPurchaseOrderList.totalQtyReceived}
					</td>
					<td>
						${bookPurchaseOrderList.totalAmount}
					</td>
					<td>
						${bookPurchaseOrderList.advanceAmount}
					</td>
					<td>
						${bookPurchaseOrderList.status}
					</td>
					<td>
						${bookPurchaseOrderList.amountStatus}
					</td>
					<td>
						${bookPurchaseOrderList.vendorCode}
					</td>
					<td>
						<a href="updateReceiveForBook.html?purchaseOrderCode=${bookPurchaseOrderList.purchaseOrderCode}">Receive</a>
					</td>
					<td>
						<a href="updatePayForBook.html?purchaseOrderCode=${bookPurchaseOrderList.purchaseOrderCode}">Pay</a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="13" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
			</tr>
		</table>
	</c:otherwise>
</c:choose>
</body>
</html>