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
<c:url value="/listProductsPagination.html" var="pagedLink">
	  <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Group List" />
<meta name="keywords" content="Asset List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>PURCHASE ORDERS LIST FOR PAYMENT</title>

<link rel="stylesheet" href="/cedugenie/css/finance/vendorPayment.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />


<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/radio.js"></script>
<script type="text/javascript" src="/cedugenie/js/finance/vendorPayment.js"></script>


</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;PURCHASE ORDERS LIST FOR PAYMENT
	</h1>
</div>

<c:choose>
	<c:when test="${vendorPaymentList eq null}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">There is no pending payment for purchase order.</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div id="wrap">
		<table  id="poTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>PAY</th>
				<th>PO Code</th>
				<th>Vendor</th>
				<th>Vendor Phone</th>
				<th>Vendor Address</th>
				<th>Open Date</th>
				<th>Close Date</th>
				<th>Amount Status</th>
				<th>Total</th>
				<th>Paid</th>
				<th>Paid Status</th>
				<th>Receive Status</th>
				<th>Order Status</th>
			</tr>
			<c:forEach var="po" items="${vendorPaymentList}" varStatus="i">
			<tr>
				<td>
					<input type="button" class="greenbtn" value="PAY" name="${po.poCode}~${po.status}~${po.totalAmount-po.paidAmount}">
				</td>
				<td>
					${po.poCode}
				</td>
				<td>
					${po.vendor}
				</td>
				<td>
					${po.vendorContact}
				</td>
				<td>
					${po.vendorAddress}
				</td>
				<td>
					${po.openDate}
				</td>
				<td>
					${po.closeDate}
				</td>
				<td>
					${po.status}
				</td>
				<td>
					${po.totalAmount}
				</td>
				<td>
					${po.paidAmount}
				</td>
				<td>
					${po.paidStatus}
				</td>
				<td>
					${po.receiveStatus}
				</td>
				<td>
					${po.orderStatus}
				</td>
			</tr>
		</c:forEach>
		</table>
		</div>
		<div id="rejectDialog">
			<form:form id="makeVendorPayment" name="makeVendorPayment" action="makeVendorPayment.html" method="POST">
				<table id="ShowData" cellspacing="0" cellpadding="0" class="midsec">
					<tr>
						<th>PO Code</th>
						<td>
							<input type="text" class="textfield1" name="poCode" id="poCode" readonly="readonly">
							<input type="hidden" name="poType" id="poType">
						</td>
					</tr>
					<tr>
						<th>Cash/Bank</th>
						<td>
							<select id="bankLedger" name="bankLedger" size="1" class="defaultselect" onchange="makeChequeReadOnly(this, 'chequeDraft0');">
								<c:forEach var="bank" items="${allBanks}" varStatus="i">
									<option value="${bank}">${bank}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>Cheque No.</th>
						<td><input type="text" class="textfield1" name="chequeNo" id="chequeNo"></td>
					</tr>
					<tr>
						<th>Amount</th>
						<td>
							<input type="text" class="textfield1" name="amount" id="amount" value="0.0">
							<input type="hidden" name="maxAmount" id="maxAmount">
						</td>
					</tr>
					<tr >
						<th>Narration</th>
						<td><input type="text" class="textfield1" name="narration" id="rollNumber"></td>
					</tr>
				</table>
				<div class="btnsarea01">
					<div class="warningbox" id="warningbox1" >
						<span id="warningmsg1"></span>	
					</div>
				</div>
				<button type="button" class="clearbtn" id="rejectCancelButton">Cancel</button>
				<button type="submit" class="editbtn" id="rejectButton" onclick="return validate();">submit</button>
			</form:form>
		</div>
		
		
		
	</c:otherwise>
</c:choose>
</body>
</html>