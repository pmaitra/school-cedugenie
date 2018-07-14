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

<link rel="stylesheet" href="/icam/css/finance/financialOutput.css" />
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
	<c:when test="${templateList eq null}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Template List Not Found</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<table cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Fees Template Name</th>
				<th>Fees Template Description</th>
				<th>Ledger Mapping Status</th>
				<th>View / Map</th>
			</tr>
			<c:forEach var="template" items="${templateList}" varStatus="i">
			<tr>
				<td>
					${template.feesTemplateName}
				</td>
				<td>
					${template.feesTemplateDesc}
				</td>
				<td>
					${template.ledgerMappingStatus}
				</td>
				<td>
					<c:choose>
						<c:when test="${template.ledgerMappingStatus eq 'REMEANING'}">
							<a href="mapFeesLedgerTemplate.html?templateCode=${template.feesTemplateCode}&type=MAP">
								<input type="button" class="submitbtn" value="MAP">
							</a>
						</c:when>
						<c:otherwise>
							<a href="mapFeesLedgerTemplate.html?templateCode=${template.feesTemplateCode}&type=VIEW">
								<input type="button" class="clearbtn" value="VIEW">
							</a>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
</body>
</html>