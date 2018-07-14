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

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/finance/mapTemplateLedger.js"></script>
<script type="text/javascript" src="/icam/js/finance/nullValidation.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Template Ledger Mapping Details
	</h1>
</div>

<c:choose>	
	<c:when test="${templateDetailsList eq null}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span>Template List Not Found</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<c:if test="${ledgerList eq null && type eq 'MAP'}">
			<div class="btnsarea01" >
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span>Ledger List Not Found</span>	
				</div>
			</div>
		</c:if>
		<form:form action="mapFeesLedgerTemplate.html" method="POST" >
			<table cellspacing="0" cellpadding="0" class="midsec1">
				<tr>
					<th colspan="3">Template :: ${templateDetailsList.get(0).feesTemplateName}</th>
				</tr>
				<tr>
					<th>Fees Breakup Name</th>
					
					<th>Ledger</th>
				</tr>
				<c:forEach var="templateDetails" items="${templateDetailsList}" varStatus="i">
				<tr>
					<td>
						${templateDetails.feesBreakupName}
					</td>
					
					<td>
						<c:choose>
							<c:when test="${type eq 'VIEW'}">
								${templateDetails.ledger}
							</c:when>
							<c:otherwise>
								<input type="hidden" name="tempDetCode" value="${templateDetails.feesTemplateDetailsCode}">
								<select name="${templateDetails.feesTemplateDetailsCode}" class="defaultdropdown">
									<option value="">Select...</option>
									<c:forEach var="ledger" items="${ledgerList}">
										<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
									</c:forEach>
								</select>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
			</table>		
			<c:if test="${type eq 'MAP'}">
				<input type="hidden" name="templateCode" value="${templateCode}">
				<div class="btnsarea01">
					<div class="warningbox" id="warningbox" >
						<span id="warningmsg"></span>	
					</div>
					<input class="clearbtn" type="reset" value="CLEAR">
					<input class="submitbtn" type="submit" value="SUBMIT" id="submit" onclick="return validate();">	
				</div>
			</c:if>
		</form:form>
	</c:otherwise>
</c:choose>
</body>
</html>