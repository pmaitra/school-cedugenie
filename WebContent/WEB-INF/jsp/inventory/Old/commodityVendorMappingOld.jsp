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
<title>Commodity Vendor Mapping</title>

<link rel="stylesheet" href="/icam/css/inventory/commodityVendorMapping.css" />
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
<script type="text/javascript" src="/icam/js/inventory/commodityVendorMapping.js"></script>
<script type="text/javascript" src="/icam/js/common/validateSearch.js"></script>

</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Commodity Vendor Mapping
	</h1>
</div>
<c:choose>
	<c:when test="${commodityVendorMappingPagedListHolder.pageList eq null}">
		<div class="btnsarea01" >
			<div class="infomsgbox" style="visibility: visible;">
				<span>No Commodity found. </span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	
	<c:if test="${successStatus != null}">
	<div class="successbox" id="successbox" style="visibility:visible;">
		<span id="successmsg" style="visibility:visible;">Successfully Updated!</span>	
	</div>
	</c:if>
	<c:if test="${failStatus != null}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Update Fail!</span>	
			</div>
	</c:if>
	
	
	<form:form id="searchCommodityVendorMapping" name="searchCommodityVendorMapping" action="searchCommodityVendorMapping.html" method="POST">
	
		<table cellspacing="0" cellpadding="0" class="midsec1">
			<tr>	
				<td>
					<select name="query" id="query" class="defaultselect1">
						<option value="">Please Select...</option>
						<option value="CommodityName">Commodity Name</option>
					</select>
				</td>		
				<td>
					<input type="text" name="data" id="data" class="textfield1" value="Search" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='Search';}" />
				</td>		
				<td>
					<input type="submit" id="search" class="editbtn" name="search" value="Search" onclick="return validateSearch('query','data','warningbox','warningmsg');">
				</td>									
			</tr>
		</table>
	</form:form>
	<form:form id="submitCommodityVendorMapping" name="submitCommodityVendorMapping" action="submitCommodityVendorMapping.html" method="POST">
		<table  id="commodityTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Quarter Master Session: 
					<input type="text" value="${inventorySession.academicYearName}" id="houseMasterSession" name="inventorySession.academicYearName" class="textfield" readonly="readonly" >
				</th>
				<th colspan="4"> Vendor:
				<select id="vendorCode" name="vendor.vendorCode" class="defaultselect">
							<option VALUE="">Please select</option>
							<c:forEach var="vendor" items="${vendorList}" varStatus="i">
								<option value="${vendor.vendorCode}">${vendor.vendorName}</option>
							</c:forEach>
				</select>
				</th>
			</tr>
			
			<tr>
				<th>Commodity Name</th>
				<th>Commodity Type</th>
				<th>Commodity Desc</th>
				<th>A/C-Unit</th>
				<th>Rate</th>
			</tr>
			<c:forEach var="commodity" items="${commodityVendorMappingPagedListHolder.pageList}" varStatus="i">
				<tr>
					<td>
						${commodity.commodityName}
						<input type="hidden" value="${commodity.commodityName}" id="commodityName${i.count}" readonly="readonly">
						<input type="hidden" value="${commodity.commodityId}" id="commodityId${i.count}" name="commodityList[${i.index}].commodityId" readonly="readonly">
						<c:set var="numberOfCommodityOnPage" value="${i.count}" scope="page" />
					</td>
					<td>
						${commodity.commodityType}
					</td>
					<td>
						${commodity.commodityDesc}
					</td>
					<td>
						<select id="unit${i.count}" name="commodityList[${i.index}].unitName" class="defaultselect">
							<option VALUE="" >Please select</option>
							<option value="Kg">Kg</option>
							<option value="Pkt">Pkt</option>
							<option value="Bott">Bott</option>
							<option value="Ltr">Ltr</option>
							<option value="-">UNKNOWN</option>						
						</select>
					</td>
					<td>
						<input type="text" onblur="validateCommodityVendorMappingRate(this)" onfocus="this.value='';" value="0.00" id="rate${i.count}" name="commodityList[${i.index}].purchaseRate" class="textfieldRate">
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${commodityVendorMappingPagedListHolder}" pagedLink="${pagedLink}" /></td>
			</tr>
			
		</table>	
				<input id="submit" class="greenbtn" type="submit" value="Submit" name="submit" onclick="return validateSubmitCommodityVendorMappingForm(${numberOfCommodityOnPage});">
				<input id="clear" class="clearbtn" type="reset" value="Clear">
		<div class="btnsarea01">
			<div class="infomsgbox" id="infomsgbox" >
				<span id="infomsg"></span>	
			</div>
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			
		</div>
	</form:form>
	</c:otherwise>
</c:choose>
</body>
</html>