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
<title>Ledger List</title>

<link rel="stylesheet" href="/icam/css/finance/financialOutput.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Ledger List
	</h1>
</div>

<c:choose>
	<c:when test="${ledgerList eq null}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Ledger List Not Found</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<table  id="ledgerList" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Ledger Name</th>
				<th>Parent Ledger Name</th>
				<th>Parent Group Name</th>
				<th>Opening balance</th>
				<th>Current balance</th>
			</tr>
			<c:forEach var="ledger" items="${ledgerList}" varStatus="i">
			<tr>
				<td>
					${ledger.ledgerName}
				</td>
				<td>
					${ledger.parentLedgerCode}
				</td>
				<td>
					${ledger.parentGroupCode}
				</td>
				<td>
					<c:if test="${ledger.openingBal ge 0}">Dr. ${ledger.openingBal}</c:if>
					<c:if test="${ledger.openingBal lt 0}">Cr. ${ledger.openingBal * -1}</c:if>
				</td>
				<td>
					<c:if test="${ledger.currentBal ge 0}">Dr. ${ledger.currentBal}</c:if>
					<c:if test="${ledger.currentBal lt 0}">Cr. ${ledger.currentBal * -1}</c:if>
					
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
</body>
</html>