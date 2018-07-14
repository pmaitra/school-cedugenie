<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Receive Requisition" />
<meta name="keywords" content="Receive Requisition" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Receive Requisition</title>

<link rel="stylesheet" href="/sms/css/library/receiveRequisition.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/library/receiveRequisition.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Receive Requisition
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>
<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<input type="checkbox" onclick="ShowAll(this)" />
	<label for="Type">All</label><br>
	<input type="checkbox" class="listShowHide" value="Order Details Code" onclick="ShowHideField('Order Details Code', 'rerq', this)" checked="checked"/>
	<label for="Order Details Code">Order Details Code</label><br>
	<input type="checkbox" class="listShowHide" value="Book Name" onclick="ShowHideField('Book Name', 'rerq', this)" checked="checked" />
	<label for="Book Name">Book Name</label><br>
	<input type="checkbox" class="listShowHide" value="Author Name(s)" onclick="ShowHideField('Author Name(s)', 'rerq', this)" checked="checked" />
	<label for="Author Name(s)">Author Name(s)</label><br>
	<input type="checkbox" class="listShowHide" value="Qty Ordered" onclick="ShowHideField('Qty Ordered', 'rerq', this)" checked="checked"/>
	<label for="Qty Ordered">Qty Ordered</label><br>
	<input type="checkbox" class="listShowHide" value="Qty Deficit" onclick="ShowHideField('Qty Deficit', 'rerq', this)" checked="checked"/>
	<label for="Qty Deficit">Qty Deficit</label><br>
	<input type="checkbox" class="listShowHide" value="Qty Received" onclick="ShowHideField('Qty Received', 'rerq', this)" checked="checked"/>
	<label for="Qty Received">Qty Received</label><br>
	<input type="checkbox" class="listShowHide" value="Rate" onclick="ShowHideField('Rate', 'rerq', this)" checked="checked"/>
	<label for="Rate">Rate</label><br>
	<input type="checkbox" class="listShowHide" value="Amount" onclick="ShowHideField('Amount', 'rerq', this)" checked="checked" />
	<label for="Amount">Amount</label><br>
	<input type="checkbox" class="listShowHide" value="Status" onclick="ShowHideField('Status', 'rerq', this)" checked="checked" />
	<label for="Status">Status</label><br>
	<input type="checkbox" class="listShowHide" value="Receiving Amount" onclick="ShowHideField('Receiving Amount', 'rerq', this)" checked="checked" />
	<label for="Receiving Amount">Receiving Amount</label><br>
</div>
<c:choose>
		<c:when test="${bookPurchaseOrderDetailsList==null}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="errorbox" id="errorbox" >
					<span id="errormsg">Data Not Found</span>	
				</div>
			</div>
		</c:when>
<c:otherwise>
<form action="updatePurchaseOrderDetails.html" method="post">
	<table id="sfltable" cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<td colspan="8">
			 	Purchase Order Code :: ${bookPurchaseOrderDetailsList[0].bookPurchaseOrderCode}
			 	<input type="hidden" name="bookPurchaseOrderCode" id="bookPurchaseOrderCode" value="${bookPurchaseOrderDetailsList[0].bookPurchaseOrderCode}" />
			 </td>
		</tr>
	</table>
	<table id="rerq" cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<th>Order Details Code</th>
			<th>Book Name</th>
			<th>Author Name(s)</th>
			<th>Qty Ordered</th>
			<th>Qty Deficit</th>
			<th>Qty Received</th>
			<th>Rate</th>
			<th>Amount</th>
			<th>Status</th>
			<th>Receiving Amount<img class="required" alt="Required" src="/sms/images/required.gif"></th>
		</tr>
		<c:forEach var="bpodl" items="${bookPurchaseOrderDetailsList}" varStatus="p">
			<tr>
				<td>
					${bpodl.purchaseOrderDetailsCode}
					<input type="hidden" name="purchaseOrderDetailsCode" id="purchaseOrderDetailsCode${p.index}" value="${bpodl.purchaseOrderDetailsCode}" />
				</td>
				<td>
					${bpodl.bookName}
					<input type="hidden" name="bookName" id="bookName${p.index}" value="${bpodl.bookName}" />
				</td>
				<td>
					${fn:replace(bpodl.authorName,'~', ',')}
					<input type="hidden" name="authorName" id="authorName${p.index}" value="${bpodl.authorName}" />
				</td>
				<td>
					${bpodl.qtyOrdered}
					<input type="hidden" name="qtyOrdered" id="qtyOrdered${p.index}" value="${bpodl.qtyOrdered}" />
				</td>
				<td>
					${bpodl.qtyDeficit}
					<input type="hidden" name="qtyDeficit" id="qtyDeficit${p.index}" value="${bpodl.qtyDeficit}" />
				</td>
				<td>
					${bpodl.qtyReceived}
					<input type="hidden" name="qtyReceived" id="qtyReceived${p.index}" value="${bpodl.qtyReceived}" />
				</td>				
				<td>
					${bpodl.rate}
					<input type="hidden" name="rate" id="rate${p.index}" value="${bpodl.rate}" />
				</td>
				<td>
					${bpodl.amount}
					<input type="hidden" name="amount" id="amount${p.index}" value="${bpodl.amount}" />
				</td>
				<td>
					${bpodl.status}
					<input type="hidden" name="status" id="status${p.index}"  value="${bpodl.status}" />
				</td>
				<td>
					<input type="text" class="textfield" name="qtyReceiving" id="qtyReceiving${p.index}" value="0.00" />
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
		<input type="submit" name="details" value="Receive" class="submitbtn" onclick="return validateReceiveRequisition();">	
	</div>
</form>
</c:otherwise>
</c:choose>
</body>
</html>