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
<title>Bank Reconciliation Statement</title>

<link rel="stylesheet" href="/icam/css/finance/financialOutput.css" />
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
<script type="text/javascript" src="/icam/js/finance/groupList.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;BRS For ${bank} From ${from} To ${to} 
	</h1>
</div>

<c:choose>
	<c:when test="${brsList eq null}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">BRS Not Found</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<table  id="groupList" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Cheque Number</th>
				<th>Date</th>
				<th>Narration</th>
				<th>Voucher Number</th>
				<th>Voucher Type</th>
				<th>Debit Amount</th>
				<th>Credit Amount</th>
			</tr>
			<c:forEach var="brs" items="${brsList}" varStatus="i">
			<c:if test="${brs.transactionPassbook eq 'TRANSACTION'}">
				<tr>
					<td>${brs.chequeNumber}</td>
					<td>${brs.date}</td>
					<td>${brs.narration}</td>
					<td>${brs.voucherNumber}</td>
					<td>${brs.voucherType}</td>
					<c:choose>
						<c:when test="${brs.debit eq true}">
							<td>${brs.amount}</td><td>--</td>
						</c:when>
						<c:otherwise>
							<td>--</td><td>${brs.amount}</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:if>
		</c:forEach>
		</table>
		
		<table cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Balance As Per Company's Book :: ${companyBook}</th>
			</tr>
			<tr>
				<th>Amount not reflected in bank :: ${notInBank}</th>
			</tr>
			<tr>
				<th>Amount not reflected in company's book :: ${notInComp}</th>
			</tr>
		</table>
	</c:otherwise>
</c:choose>
</body>
</html>