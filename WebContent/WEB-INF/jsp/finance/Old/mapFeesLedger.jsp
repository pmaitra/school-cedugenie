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
<title>Fees Ledger Template List</title>

<link rel="stylesheet" href="/icam/css/finance/mapFeesLedger.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp; Fees Template Ledger Mapping List
	</h1>
</div>

<c:choose>
	<c:when test="${feesList eq null || empty feesList || ledgerList eq null || empty ledgerList}">
		<div class="btnsarea01" >
			<c:if test="${feesList eq null || empty feesList}">
				<div class="errorbox" style="visibility: visible;">
					<span>Fees List Not Found</span>	
				</div>
			</c:if>
			<c:if test="${ledgerList eq null || empty ledgerList}">
				<div class="errorbox" style="visibility: visible;">
					<span>Ledger List Not Found</span>	
				</div>
			</c:if>
		</div>
	</c:when>
	<c:otherwise>
		<form:form action="saveFeesLedgerMapping.html" method="POST">
			<table cellspacing="0" cellpadding="0" class="midsec1">
				<tr>
					<th>Fees</th>
					<th>Ledger</th>
				</tr>
				<c:forEach var="fees" items="${feesList}" varStatus="i">
					<tr>
						<td>
							${fees.feesName}
							<input type="hidden" name="fees" value="${fees.feesName}">
						</td>
						<td>
							<select name="${fees.feesName}" size="1" class="defaultselect">
								<option value="">Select</option>
								<c:forEach var="ledger" items="${ledgerList}" varStatus="i">
									<c:if test="${fees.ledger eq ledger.ledgerCode}">
										<option value="${ledger.ledgerCode}" selected="selected">${ledger.ledgerName}</option>
									</c:if>
									<c:if test="${fees.ledger ne ledger.ledgerCode}">
										<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="btnsarea01">
				<input type="submit" value="Submit" class="submitbtn"/>
			</div>
		</form:form>
	</c:otherwise>
</c:choose>
</body>
</html>