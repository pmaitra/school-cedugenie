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
<c:url value="/commodityListPagination.html" var="pagedLink">
	  <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Commodity List" />
<meta name="keywords" content="Commodity List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Commodity List</title>

<link rel="stylesheet" href="/icam/css/inventory/commodityList.css" />
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

<script type="text/javascript" src="/icam/js/inventory/commodityList.js"></script>



</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Commodity List
	</h1>
</div>
<c:choose>
	<c:when test="${commodityPagedListHolder.pageList eq null}">
		<div class="btnsarea01" >
			<div class="infomsgbox" style="visibility: visible;">
				<span>No Commodity found. </span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	
	<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
		<h1>Show / Hide Columns</h1>
	</div>
	
	<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
		<input type="checkbox" onclick="ShowAll(this)" checked="checked" />
		<label for="Type">All</label><br>
		<input type="checkbox" class="listShowHide" value="Commodity Name" onclick="ShowHideField('Commodity Name', 'commodityTable', this)" checked="checked" />
			<label for="Commodity Name">Commodity Name</label><br>
	    <input type="checkbox" class="listShowHide" value="Commodity Desc" onclick="ShowHideField('Commodity Desc', 'commodityTable', this)" checked="checked" />
			<label for="Commodity Desc">Commodity Desc</label><br>
		<input type="checkbox" class="listShowHide" value="Commodity Type" onclick="ShowHideField('Commodity Type', 'commodityTable', this)" checked="checked" />
			<label for="Commodity Type">Commodity Type</label><br>
		<input type="checkbox" class="listShowHide" value="Commodity Sub Type" onclick="ShowHideField('Commodity Sub Type', 'commodityTable', this)" checked="checked" />
			<label for="Commodity Sub Type">Commodity Sub Type</label><br>
	</div>
	
	
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
	
	
	<form:form id="searchCommodity" name="searchCommodity" action="searchCommodity.html" method="POST">
	
		<table cellspacing="0" cellpadding="0" class="midsec1">
			<tr>	
				<td>
					<select name="query" id="query" class="defaultselect1">
						<option value="">Please Select...</option>
						<option value="CommodityName">Commodity Name</option>
						<option value="CommodityType">Commodity Type</option>
						<option value="CommoditySubType">Commodity Sub Type</option>
						<option value="LedgerNo">Ledger No</option>
						<option value="PageNo">Page No</option>
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
	
	
		<table  id="commodityTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Ledger No.</th>
				<th>Commodity Name</th>
				<th>Commodity Desc</th>
				<th>Commodity Type</th>
				<th>Stock</th>
				<th>Commodity Sub Type</th>
				<th>Edit</th>
				<th>Price History</th>
			</tr>
			<c:forEach var="commodity" items="${commodityPagedListHolder.pageList}" varStatus="i">
				<tr>
					<td>
						${commodity.ledgerNumber}
					</td>
					<td>
						${commodity.commodityName}
					</td>
					<td>
						${commodity.commodityDesc}
					</td>
					<td>
						${commodity.commodityType}
					</td>
					<td>
						${commodity.inStock}
					</td>
					<td>
						${commodity.commoditySubType}
					</td>
					<td>
						<a class="hlink" onClick="window.open('commodityDetails.html?commodityId=${commodity.commodityId}','_self')" style="cursor:pointer; color:#000080;"> Edit</a>
					</td>
					<td>
						<input type="button" value="Price History" id="priceButton" class="pricebtn" onclick="getPriceHistory(${commodity.commodityId},'${commodity.commodityName}');">
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="8" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${commodityPagedListHolder}" pagedLink="${pagedLink}"/></td>
			</tr>
		</table>	
		<div id="dialog" title="Price dialog" >	
			<table id="dialogTable" class="midsec1">
				
			</table>
			<input type="button" value="OK" id="okbtn" class="pricebtn" onclick="cleanPopup();"/>	
		</div>
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