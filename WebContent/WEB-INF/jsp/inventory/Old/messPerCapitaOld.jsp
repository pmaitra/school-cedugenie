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
<meta name="description" content="Page To Create Mess Per Capita" />
<meta name="keywords" content="Create Mess Per Capita" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Mess Per Capita</title>

<link rel="stylesheet" href="/icam/css/inventory/createMessPerCapita.css" />
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
<script type="text/javascript" src="/icam/js/inventory/createMessPerCapita.js"></script>
<script type="text/javascript" src="/icam/js/common/validateSearch.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">

</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Mess Per Capita
	</h1>
</div>
	
	<form:form id="submitMessPerCapita" name="submitMessPerCapita" action="submitMessPerCapita.html" method="POST">
		<table  id="messConsuptionDateAndTypeTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<td>
					Start Date :: 
				</td>
				<td>
					<input type="text" value="${startDateFromFront}" id="startDate" name="startDate" class="textfield" readonly="readonly">					
				</td>
				<td>
					End Date ::  
				</td>
				<td>
					<input type="text" value="${endDateFromFront}" id="endDate" name="endDate" class="textfield" readonly="readonly">					
				</td>
			</tr>
		</table>
		<input id="submit" class="greenbtn" type="submit" value="Submit" name="submit" onclick="return validateSubmitMessPerCapitaForm();">
		<input id="clear" class="clearbtn" type="reset" value="Clear">
		<br>
		<br>
		</form:form>
		<form:form id="messPerCapitaDetails" name="messPerCapitaDetails">
		<c:choose>
			<c:when test="${listDetails eq null}">
				
			</c:when>	
		<c:otherwise>
		<c:if test="${empty listDetails}">
		 		<div class="btnsarea01" >
					<div class="infomsgbox" style="visibility: visible;">
						<span>There is no Consumption</span>	
					</div>
				</div>
		</c:if>
		<c:if test="${not empty listDetails}">
			<table  id="messConsuptionTable" cellspacing="0" cellpadding="0" class="midsec1">
				<tr>
					<th>Consumption Date</th>
					<th>Boys Strength</th>
					<th>Staff Strength</th>
					<th>Dry</th>
					<th>Fresh</th>
					<th>Miscllaneous</th>
					<th>Amount</th>
					<th>
						PerCapita
						<c:set var="strengthTotal" value="0"/>
						<c:set var="amountTotal" value="0"/>
						<c:set var="perCapitaTotal" value="0"/></th>
				</tr>
					<c:forEach var="perCapita" items="${listDetails}" varStatus="i">
						<tr>
							<td>
								${perCapita.consumptionDate}
							</td>
							<td>
								${perCapita.studentStrength}
								<c:set var="strengthTotal" value="${strengthTotal+perCapita.studentStrength}"/>
							</td>
							<td>
								${perCapita.staffStrength}
							</td>
							<td>
								${perCapita.inStock}
							</td>
							<td>
								${perCapita.messStock}
							</td>
							<td>
								${perCapita.purchaseAmount}
							</td>
							<td>
								${perCapita.purchaseRate}
								<c:set var="amountTotal" value="${amountTotal+perCapita.purchaseRate}"/>
							</td>
							<td>
								<fmt:formatNumber value="${perCapita.sellingRate}" maxFractionDigits="2"/>								
							</td>
						</tr>	
					</c:forEach>		
					<tr>
						<td></td>
						<td>${strengthTotal}</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td>${amountTotal}</td>
						<td><fmt:formatNumber var="perCapitaTotal" value="${amountTotal/strengthTotal}"/></td>
					</tr>
					<tr>
						<td colspan="8">
							<span>Percapita Expenditure = Net.Expenditure/No.of Cadets = Rs.${amountTotal}/${strengthTotal}=Rs.${perCapitaTotal}</span>
						</td>
					</tr>
					<tr>
						<td colspan="8" style="position: relative;">
							<span>Percapita Rs.${perCapitaTotal}</span>
						</td>
					</tr>
			</table>
			</c:if>	
		</c:otherwise>
		</c:choose>
				
		<br>
		<br>
		<div class="btnsarea01">
			<div class="infomsgbox" id="infomsgbox" >
				<span id="infomsg"></span>	
			</div>
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			
		</div>
	</form:form>

</body>
</html>