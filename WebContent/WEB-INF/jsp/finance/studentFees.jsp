<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page To Add Social Category" />
<meta name="keywords" content="Page To Add Social Category" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Student Fees</title>

<link rel="stylesheet" href="/cedugenie/css/finance/studentFees.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/cedugenie/Cal/default.css"/>

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/cedugenie/js/finance/studentFees.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/iframeHeight.js"></script>
<script src="/cedugenie/Cal/zebra_datepicker.js"></script>
</head>

<body>
<div class="ttlarea">
	<h1>
		<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Student Fees
	</h1>
</div>
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div>
	
	
	<c:choose>
		<c:when test="${standardList eq null || empty standardList}">
			<div class="btnsarea01" >
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<c:if test="${standardList eq null || empty standardList}">
						<span id="errormsg">Standard Not Found</span>	
					</c:if>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<form method="POST" action="saveStudentFees.html" >
				<table id="subjectDetailTable" class="midsec" cellspacing="0" cellpadding="0">
					<tr>
						<th>
							Standard ::
						</th>
						<td>
							<select id="standard" name="standard" size="1" class="defaultselect">
								<option value="">Select</option>
								<c:forEach var="standard" items="${standardList}" varStatus="i">
									<option value="${standard.standardCode}">${standard.standardName}</option>
								</c:forEach>
							</select>
						</td>
						<th>
							Section ::
						</th>
						<td>
							<select id="section" name="section" size="1" class="defaultselect">
								<option value="">Select</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>
							Name(Roll Number) ::
						</th>
						<td>
							<select id="rollNumber" name="rollNumber" size="1" class="defaultselect">
								<option value="">Select</option>
							</select>
						</td>
						<th>Narration</th>
						<td>
							<textarea name="narration"></textarea>
						</td>
					</tr>
					<tr>
						<th>
							Date ::
						</th>
						<td>
							<input type="text" name="date" id="date" class="textfield" >
						</td>
					</tr>
				</table>
				
				<table id="feesTable" cellspacing="0" cellpadding="0" class="midsec1">
					<tr>
						<th>Fees</th>
						<th>Ledger</th>
						<th>Total</th>
						<th>Paid</th>
						<th>Pending</th>
						<th>Payable</th>
					</tr>
				</table>
				
				<table id="paymentTable" cellspacing="0" cellpadding="0" class="midsec1">
					<tr>
						<th>Ledger</th>
						<th>Amount</th>
						<th>Cheque/DD</th>
						<th><input type="button" id="add" name="add" class="addbtn"  onclick="addLedger();"/></th>
					</tr>
					<tr>
						<td>
							<select id="bankLedger0" name="bankLedger" size="1" class="defaultselect" onchange="makeChequeReadOnly(this, 'chequeDraft0');">
								<c:forEach var="bank" items="${allBanks}" varStatus="i">
									<option value="${bank}">${bank}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<input type="text" name="ledgerAmount" id="ledgerAmount0" class="textfield3" value="0.0" onfocus="clearAmount(this);" onblur="checkLedgerAmount(this);">
						</td>
						<td>
							<input type="text" name="chequeDraft" id="chequeDraft0" class="textfield2" readonly="readonly">
						</td>
						<td><img src='/cedugenie/images/minus_icon.png' onclick='deleteLedger(this);'></td>
					</tr>
				</table>
				
				<input type="hidden" name="status" id="status">
				<div class="btnsarea01" id="submitButtonDiv">
					<input type="submit" value="Submit" class="submitbtn" onclick="return validate();"/>
				</div>
			</form>
			<div style="visibility: collapse;">
				<select id="bankLedger" size="1" class="defaultselect">
					<c:forEach var="bank" items="${allBanks}" varStatus="i">
						<option value="${bank}">${bank}</option>
					</c:forEach>
				</select>
			</div>
		</c:otherwise>
	</c:choose>

	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
</body>
</html>