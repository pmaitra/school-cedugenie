<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Pay Requisition" />
<meta name="keywords" content="Pay Requisition" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Pay Requisition</title>

<link rel="stylesheet" href="/sms/css/library/payRequisition.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/sms/js/library/payRequisition.js"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Pay Requisition
		</h1>
</div>
<c:choose>
		<c:when test="${bookPurchaseOrder==null}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="errorbox" id="errorbox" >
					<span id="errormsg">Data Not Found</span>	
				</div>
			</div>
		</c:when>
<c:otherwise>
<form action="updatePurchaseOrderPayment.html" method="post">
	<table id="sfltable" cellspacing="0" cellpadding="0" class="midsec">
		<tr>
			<th>
				Order Code :: 
			</th>
			<td>
				${bookPurchaseOrder.purchaseOrderCode}
				<input type="hidden" readonly="readonly" name="purchaseOrderCode" id="purchaseOrderCode" value="${bookPurchaseOrder.purchaseOrderCode}" />
			</td>
		</tr>
		<tr>
			<th>
				Qty Ordered :: 
			</th>
			<td>
				${bookPurchaseOrder.totalQtyOrdered}
				<input type="hidden" readonly="readonly" name="totalQtyOrdered" id="totalQtyOrdered" value="${bookPurchaseOrder.totalQtyOrdered}" />
			</td>
		</tr>
		<tr>
			<th>
				Qty Remeaning :: 
			</th>
			<td>
				${bookPurchaseOrder.totalQtyDeficit}
				<input type="hidden" readonly="readonly" name="totalQtyDeficit" id="totalQtyDeficit" value="${bookPurchaseOrder.totalQtyDeficit}" />
			</td>
		</tr>
		<tr>
			<th>
				Qty Received :: 
			</th>
			<td>
				${bookPurchaseOrder.totalQtyReceived}
				<input type="hidden" readonly="readonly" name="totalQtyReceived" id="totalQtyReceived" value="${bookPurchaseOrder.totalQtyReceived}" />
			</td>
		</tr>
		<tr>
			<th>
				Paid Amount :: 
			</th>
			<td>
				${bookPurchaseOrder.advanceAmount}
<%-- 				<input type="hidden" readonly="readonly" name="advanceAmount" id="advanceAmount" value="${bookPurchaseOrder.advanceAmount}" /> --%>
			</td>
		</tr>
		<tr>
			<th>
				Total Amount :: 
			</th>
			<td>
				${bookPurchaseOrder.totalAmount}
				<input type="hidden" readonly="readonly"  id="totalAmount" value="${bookPurchaseOrder.totalAmount}" />
			</td>
		</tr>
		<c:choose>
			<c:when test="${bookPurchaseOrder.advanceAmount>0}">
				<tr>
					<th>
						STDS ${bookPurchaseOrder.stdsInPercent}%
						<input type="hidden" readonly="readonly"  name="status" value="DONE" />
					</th>
					<th>
						STDS Amount Rs. ${bookPurchaseOrder.stdsInAmount}
					</th>
				</tr>
				<tr>
					<th>
						Service Charge Rs. ${bookPurchaseOrder.serviceCharge}
					</th>
					<th>
						Service Tax  ${bookPurchaseOrder.serviceTaxInPercent}%
					</th>
				</tr>
				<tr>
					<th>
						TDS ${bookPurchaseOrder.tdsInPercent}%
					</th>
					<th>
						TDS Amount Rs. ${bookPurchaseOrder.tdsInAmount}
					</th>
				</tr>
				<tr>
					<th>
						Total Amount Payable
					</th>
					<td>
						${bookPurchaseOrder.netAmount}
					</td>
			    </tr>
				<tr>
					<th>
						Total Pending Amount
					</th>
					<td>
						<input type="text" class="textfield" name="pendingAmount" id="totalPendingAmount" value="${bookPurchaseOrder.pendingAmount}" readonly="readonly" />
						<input type="hidden" readonly="readonly"  id="demoPendingAmount" value="${bookPurchaseOrder.pendingAmount}" />
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<th>
						STDS <input size="5" type="text" class="textfield1" name="stdsInPercent" id="stdsInPercent" value="0.00" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}calculateTotalPayableAmount();"/>%
					</th>
					<th>
						STDS Amount Rs.<input size="10" type="text" class="textfield1" name="stdsInAmount" id="stdsInAmount" value="0.00" readonly="readonly" />
					</th>
				</tr>
				<tr>
					<th>
						Service Charge Rs.<input size="10" type="text" class="textfield1" name="serviceCharge" id="serviceCharge" value="0.00" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}calculateTotalPayableAmount();"/>
					</th>
					<th>
						Service Tax  <input size="5" type="text" class="textfield1" name="serviceTaxInPercent" id="serviceTaxInPercent" value="0.00"  onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}calculateTotalPayableAmount();" />%
					</th>
				</tr>
				<tr>
					<th>
						TDS <input size="5" type="text" class="textfield1" name="tdsInPercent" id="tdsInPercent" value="0.00" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}calculateTotalPayableAmount();"/>%
					</th>
					<th>
						TDS Amount Rs.<input size="10" type="text" class="textfield1" name="tdsInAmount" id="tdsInAmount" value="0.00" readonly="readonly" />
					</th>
				</tr>
				<tr>
					<th>
						Total Amount Payable
					</th>
					<td>
						<input type="text" class="textfield" name="netAmount" id="totalPayableAmount" value="${bookPurchaseOrder.totalAmount}" readonly="readonly" />
					</td>
			    </tr>
				<tr>
					<th>
						Total Pending Amount
					</th>
					<td>
						<input type="text" class="textfield" name="pendingAmount" id="totalPendingAmount" value="${bookPurchaseOrder.totalAmount}" readonly="readonly" />
						<input type="hidden" readonly="readonly"  id="demoPendingAmount" value="${bookPurchaseOrder.totalAmount}" />
					</td>
				</tr>
			</c:otherwise>
		</c:choose>
		
		<tr>
			<th>
				Paying Amount<img class="required" alt="Required" src="/sms/images/required.gif"> :: 
			</th>
			<td>
				<input type="text" class="textfield" name="advanceAmount" id="payingAmount" value="0.00" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}calculatePendingAmount();"/>
			</td>
		</tr>
		<tr>
			<th>Payment Mode</th>
			<td>
			<select id="transactionMode" class="defaultselect1" onchange="showBankDetails(this);" name="modeOfPayment">
			<option value="CASH">Cash</option>
			<option value="BANK">Bank</option>
			</select>
			</td>
		</tr>
		<tr>
	</table>
	<div id="paymentDetails" class="paymentDetails" style="visibility:collapse;" >
		<table id="sfltable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr><td colspan="4">:: Bank Details ::</td></tr>
			<tr><th colspan="4"></th></tr><tr><td colspan="4"></td></tr>
			<tr>
				<th>Cheque No :: <input size="10" type="text" class="textfield1" name="chequeNo" id="chequeNo" value="" disabled/></th>
				<th>Bank Code :: <input size="10" type="text" class="textfield1" name="bankCode" id="bankCode" value="" disabled/></th>
				<th>Bank Name :: <input size="20" type="text" class="textfield1" name="bankName" id="bankName" value="" disabled/></th>
				<th>Bank Location :: <input size="20" type="text" class="textfield1" name="bankLocation" id="bankLocation" value="" disabled/></th>
			</tr>
		 </table>
	</div>
	
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
		<input type="submit" value="Pay" class="submitbtn" onclick="return validatePayRequisition();">
	</div>
</form>
</c:otherwise>
</c:choose>
</body>
</html>