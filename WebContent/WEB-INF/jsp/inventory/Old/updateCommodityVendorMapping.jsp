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
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/commodityVendorMappingPagination.html" var="pagedLink" >
	  <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Commodity Vendor Mapping" />
<meta name="keywords" content="Commodity Vendor Mapping" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Update Commodity Vendor Mapping</title>

<link rel="stylesheet" href="/icam/css/inventory/updateCommodityVendorMapping.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/inventory/updateCommodityVendorMapping.js"></script>

</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Update Commodity Vendor Mapping
	</h1>
</div>
<c:choose>
	<c:when test="${vendorList eq null}">
		<div class="btnsarea01" >
			<div class="errorbox" style="visibility: visible;">
				<span>No Mess Vendor found. </span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<c:if test="${insertUpdateStatus != null}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span>${insertUpdateStatus}</span>	
			</div>
		</c:if>
		
		<form:form id="searchCommodityVendorMapping" name="searchCommodityVendorMapping" action="searchCommodityVendorMapping.html" method="POST">
			<table cellspacing="0" cellpadding="0" class="midsec">
				
			</table>
		</form:form>
		<form:form action="updateCommodityVendorMapping.html" method="POST">
			<table cellspacing="0" cellpadding="0" class="midsec">
				<tr>
					<th>
						Quarter Master Session: 
					</th>
					<td>
						<input type="hidden" value="${inventorySession.academicYearName}" name="inventorySession.academicYearName" id="inventorySession">
						${inventorySession.academicYearName}
					</td>
				</tr>
				<tr>
					<th> Vendor:</th>
					<td>
						<select id="vendorCode" name="vendor.vendorCode" class="defaultselect">
								<option VALUE="" >Please select</option>
								<c:forEach var="vendor" items="${vendorList}" varStatus="i">
									<option value="${vendor.vendorCode}">${vendor.vendorName}</option>
								</c:forEach>
						</select>
					</td>
				</tr>
			</table>
			<table  id="commodityTable" cellspacing="0" cellpadding="0" class="midsec1">
				<tr>
					<th>Commodity Name</th>
					<th>Commodity Type</th>
					<th>Commodity Desc</th>
					<th>A/C-Unit</th>
					<th>Rate</th>
				</tr>
			</table>
			<div style="visibility: collapse;">
				<select class="defaultselect" id="unit">
					<option value="Kg">Kg</option>
					<option value="Pkt">Pkt</option>
					<option value="Bott">Bott</option>
					<option value="Ltr">Ltr</option>
					<option value="-">UNKNOWN</option>						
				</select>
			</div>
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>	
			<div class="btnsarea01" id="buttonDiv" style=" visibility: collapse;">
				<input id="submit" class="greenbtn" type="submit" value="Submit" name="submit" onclick="return updateCommodityVendorMapping();">
				<input id="clear" class="clearbtn" type="reset" value="Clear">
			</div>
		</form:form>
	</c:otherwise>
</c:choose>
</body>
</html>