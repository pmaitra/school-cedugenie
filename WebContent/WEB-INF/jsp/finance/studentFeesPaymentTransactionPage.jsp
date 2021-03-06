<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Student Fees Payment" />
<meta name="keywords" content="Student Fees Payment" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Student Fees Payment</title>

<link rel="stylesheet" type="text/css" href="/cedugenie/css/finance/studentFeesPaymentTransactionPage.css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/Cal/default.css"/>

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/finance/studentFeesPaymentTransactionPage.js"></script>
<script type="text/javascript" src="/cedugenie/js/finance/nullValidation.js"></script>
<script src="/cedugenie/Cal/zebra_datepicker.js"></script>
</head>
<body>
<div class="ttlarea">
	<h1>
	<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Student Fees Payment
	</h1>
</div>
<c:choose>
	<c:when test="${message ne null}">		
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">${message}</span>	
			</div>		
	</c:when>
<c:otherwise>
	<form:form name="" action="insertStudentFeesPayment.html" method="POST" >

		<table cellspacing="0" cellpadding="0" class="midsec">
		<tr>
				<th>Voucher Type :: </th>
				<td>
					<select name="voucherTypeCode" id="voucherTypeCode" class="defaultdropdown" >
						<c:if test="${voucherTypeList ne null}">
							<c:forEach var="vt" items="${voucherTypeList}">
								<option value="${vt.voucherTypeCode}">${vt.voucherTypeName}</option>
							</c:forEach>
						</c:if>
					</select>
				</td>
			</tr>
			
		<tr>
				<th>Date :: </th>
				<td><input type="text" name="transactionDate" id="transactionDate" class="textfield" ></td>
			</tr>
		     <tr>
				<th>Receipt Number :: </th>
				<td><input type="text" name="receiptNumber" id="receiptNumber" class="textfield" ></td>
			</tr>
			
			<tr> 
				<th>Roll Number:: </th> 
 				<td><input type="text" name="rollNumber" id="rollNumber" class="textfield" ></td> 
 			</tr> 
			<tr> 
				<th>Name of Student:: </th> 
 				<td><input type="text" name="firstName" id="firstName" class="textfield" ></td> 
 			</tr>
 			<tr> 
				<th>Name of Parent:: </th> 
 				<td><input type="text" name="firstName" id="firstName" class="textfield" ></td> 
 			</tr>
 			
 			
 			<tr>
				<th>Remarks :: </th>
				<td><textarea name="remarks" id="remarks" class="txtarea" ></textarea></td>
			</tr>			
			<tr>
				<th colspan="2">
					<table id="creditTable" cellspacing="0" cellpadding="0" class="midsec1">
						<tr>
							<th>Credit Account</th>
							<th></th>
							<th>Amount</th>
							<th><input type="button" id="add" name="add" class="addbtn"  onclick="addCredit();"/></th>
						</tr>
						<tr>
							<td><select name="creditLedger" class="defaultdropdown" id="creditLedger0" onchange="createCheckNumber(this);">
									<option value="">Select</option>
									<c:forEach var="ledger" items="${ledgerList}" >
										<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="creditBank" id="creditBank0">
							</td>
							<td></td>
							<td><input type="text" name="creditAmount" class="textfield" id="creditAmount0" ></td>
							
							<td><img src='/cedugenie/images/minus_icon.png' onclick='deleteCredit(this);'></td>
						</tr>
					</table>
				</th>
			</tr>
			<tr>
				<th colspan="2">
					<table id="debitTable" cellspacing="0" cellpadding="0" class="midsec1">
						<tr>
							<th>Debit Account</th>
							<th></th>
							<th>Amount</th>
							<th><input type="button" id="add" name="add" class="addbtn"  onclick="addDebit();"/></th>
						</tr>
						<tr>
							<td><select name="debitLedger" class="defaultdropdown" id="debitLedger0" onchange="createCheckNumber(this);">
									<option value="">Select</option>
									<c:forEach var="ledger" items="${ledgerList}" >
										<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="debitBank" id="debitBank0">
							</td>
							<td></td>
							<td><input type="text" name="debitAmount" class="textfield" id="debitAmount0" ></td>
							
							<td><img src='/cedugenie/images/minus_icon.png' onclick='deleteDebit(this);'></td>
						</tr>
					</table>
				</th>
			</tr>
		</table>
		<div style="visibility: collapse;">
			<select name="allLedger" id="allLedger" class="defaultdropdown" onchange="createCheckNumber(this);">
				<option value="">Select</option>
				<c:forEach var="ledger" items="${ledgerList}">
					<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
				</c:forEach>
			</select>
		</div>
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			<input name="" class="clearbtn" type="reset" value="CLEAR">
			<input name="" class="submitbtn" type="submit" value="SUBMIT" id="submit" onclick="return validate();">	
		</div>
	</form:form>
</c:otherwise>
</c:choose>
</body>
</html>
