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
<c:url value="/assetListPagination.html" var="pagedLink">
	  <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Commodity List" />
<meta name="keywords" content="Asset List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Asset List</title>

<link rel="stylesheet" href="/icam/css/inventory/assetList.css" />
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
<script type="text/javascript" src="/icam/js/common/validateSearch.js"></script>

<script type="text/javascript" src="/icam/js/inventory/assetList.js"></script>



</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Asset List
	</h1>
</div>
<c:choose>
	<c:when test="${assetPagedListHolder.pageList eq null}">
		<div class="btnsarea01" >
			<div class="infomsgbox" style="visibility: visible;">
				<span>No Asset found. </span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>	
		
	
	<form:form id="searchAsset" name="searchAsset" action="searchAsset.html" method="POST">
	
		<table cellspacing="0" cellpadding="0" class="midsec1">
			<tr>	
				<td>
					<select name="query" id="query" class="defaultselect1">
						<option value="">Please Select...</option>
						<option value="AssetType">Asset Type</option>
						<option value="AssetSubType">Asset Sub Type</option>
						<option value="AssetName">Asset Name</option>
						<option value="Department">Department</option>
						<option value="PurchaseDate">Purchase Date</option>
						
					</select>
				</td>		
				<td>
					<input type="text" name="data" id="data" class="textfield1" value="Search" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='Search';}" />
				</td>		
				<td>
					<input type="submit" id="search" class="editbtn" name="searchAsset" value="Search" onclick="return validateSearch('query','data','warningbox','warningmsg');">
				</td>									
			</tr>
		</table>
	
	
		<table id="commodityTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Asset Name</th>
				<th>Department</th>
				<th>Price</th>
				<th>Purchase Date</th>
				<th>Asset Type</th>
				<th>Asset Sub Type</th>
				<th></th>
			</tr>
			<c:forEach var="asset" items="${assetPagedListHolder.pageList}" varStatus="i">
				<tr>
					<td>
						${asset.assetName}
					</td>
					<td>
						${asset.department.departmentName}
					</td>
					<td>
						${asset.assetPrice}
					</td>
					<td>
						${asset.purchaseDate}
					</td>
					<td>
						${asset.assetType}
					</td>
					<td>
						${asset.assetSubType}
					</td>
					<td>
						<input type="button" value="DETAILS" id="priceButton" class="greenbtn" onclick="window.open('getAssetDetails.html?assetID=${asset.assetId}','_self')">
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${assetPagedListHolder}" pagedLink="${pagedLink}"/></td>
			</tr>
		</table>
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