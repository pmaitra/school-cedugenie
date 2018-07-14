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
<meta name="description" content="profitAndLoss" />
<meta name="keywords" content="profitAndLoss" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Ledger Wise Details</title>

<link rel="stylesheet" type="text/css" href="/icam/css/finance/financialOutput.css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/Cal/default.css"/>

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/finance/ledgerWiseView.js"></script>
<script type="text/javascript" src="/icam/js/finance/nullValidation.js"></script>
<script src="/icam/Cal/zebra_datepicker.js"></script>
</head>
<body>
<div class="ttlarea">
	<h1>
	<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Ledger Wise Details
	</h1>
</div>
	<form:form method="POST" action="getBrs.html" target="_blank" >
		<table id="passbookTable" cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th>Bank</th>
				<td>
					<select name="ledger" id="ledger" class="defaultdropdown" >
						<option value="">Select</option>
						<c:if test="${ledgerList ne null}">
							<c:forEach var="ledger" items="${ledgerList}">
								<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
							</c:forEach>
						</c:if>
					</select>
				</td>
			</tr>
			<tr>
				<th>From Date</th>
				<td>
					<input type="text" name="fromDate" id="fromDate" class="textfield" >
				</td>
			</tr>
			<tr>
				<th>To Date</th>
				<td>
					<input type="text" name="toDate" id="toDate" class="textfield" >
				</td>				
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" id="get" class="submitbtn">Get</button>
				</td>
			</tr>
		</table>
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>	
		</div>
		<div id="tbDiv">
		</div>
	</form:form>
</body>
</html>