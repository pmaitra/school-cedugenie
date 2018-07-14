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
<c:url value="/getCommodityListPaging.html" var="pagedLink">
	  <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Asset List" />
<meta name="keywords" content="Asset List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Asset List</title>

<link rel="stylesheet" href="/sms/css/inventory/listCommodity.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/sms/js/common/radio.js"></script>
<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/inventory/listCommodity.js"></script>
<script type="text/javascript" src="/sms/js/common/validateSearch.js"></script>

</head>
<body>
<div class="ttlarea">	
	<h1><img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Commodity List
	</h1>
</div>
<c:choose>
	<c:when test="${pagedListHolder.pageList eq null}">
		<div class="btnsarea01" >
			<div class="infomsgbox" style="visibility: visible;">
				<span>${message}</span>	
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
	    <input type="checkbox" class="listShowHide" value="In Stock" onclick="ShowHideField('In Stock', 'commodityTable', this)" checked="checked" />
			<label for="In Stock">In Stock</label><br>
		<input type="checkbox" class="listShowHide" value="Threshold" onclick="ShowHideField('Threshold', 'commodityTable', this)" checked="checked" />
			<label for="Threshold">Threshold</label><br>
		<input type="checkbox" class="listShowHide" value="Commodity Type" onclick="ShowHideField('Commodity Type', 'commodityTable', this)" checked="checked" />
			<label for="Commodity Type">Commodity Type</label><br>
		<input type="checkbox" class="listShowHide" value="Commodity Sub Type" onclick="ShowHideField('Commodity Sub Type', 'commodityTable', this)" checked="checked" />
			<label for="Commodity Sub Type">Commodity Sub Type</label><br>
	</div>
	
	
	<form:form id="editCommodity" name="editCommodity" action="editCommodity.html" method="POST">
	
		<table cellspacing="0" cellpadding="0" class="midsec1">
			<tr>	
				<td>
					<select name="query" id="query" class="defaultselect1">
						<option value="">Please Select...</option>
						<option value="Commodity Name">Commodity Name</option>
						<option value="Commodity Type">Commodity Type</option>
						<option value="Commodity Sub Type">Commodity Sub Type</option>
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
				<th><input type="radio" disabled="disabled"></th>
				<th>Commodity Name</th>
				<th>Commodity Desc</th>
				<th>In Stock</th>
				<th>Threshold</th>
				<th>Commodity Type</th>
				<th>Commodity Sub Type</th>
			</tr>
			<c:forEach var="commodity" items="${pagedListHolder.pageList}" varStatus="i">
				<tr>
					<td>
						<input type="radio" name="commodityCode" id="${i.index}"  value="${commodity.commodityCode}" />
					</td>
					<td>
						<input type="hidden" id="commodityCode${i.index}" name="${commodity.commodityCode}CODE" value="${commodity.commodityCode}" />
						<input type="text" class="textfield" id="commodityName${i.index}" name="${commodity.commodityCode}NAME" value="${commodity.commodityName}" readonly="readonly" onblur="checkCommodityName(this,'commodityCode${i.index}');"/>
					</td>
					<td>
						<input type="text" class="textfield" id="commodityDesc${i.index}" name="${commodity.commodityCode}DESC" value="${commodity.commodityDesc}" readonly="readonly" />
					</td>
					<td>
						${commodity.inStock}
					</td>
					<td>
						<input type="text" class="textfield" id="threshold${i.index}" name="${commodity.commodityCode}THR" value="${commodity.threshold}" readonly="readonly" />
					</td>
					<td>
						<select name="${commodity.commodityCode}TYPE" id="commodityType${i.index}" class="defaultselect" onchange="createSubType(this);" disabled="disabled">
							<c:if test="${commodity.commodityType eq 'ASSET'}">
								<option value="ASSET" selected="selected">Asset</option>
								<option value="STOCK">Stock</option>
							</c:if>
							<c:if test="${commodity.commodityType eq 'STOCK'}">
								<option value="ASSET">Asset</option>
								<option value="STOCK" selected="selected">Stock</option>
							</c:if>
						</select>
					</td>
					<td>
						<select name="${commodity.commodityCode}SUBTYPE" id="commoditySubType${i.index}" class="defaultselect" disabled="disabled" >
							<c:if test="${commodity.commodityType eq 'ASSET'}">
								<c:if test="${commodity.commoditySubType eq 'IT'}">
									<option value="IT" selected="selected">It</option>
									<option value="NONIT">Non It</option>
								</c:if>
								<c:if test="${commodity.commoditySubType eq 'NONIT'}">
									<option value="IT">It</option>
									<option value="NONIT" selected="selected">Non It</option>
								</c:if>
							</c:if>
							<c:if test="${commodity.commodityType eq 'STOCK'}">
								<c:if test="${commodity.commoditySubType eq 'FOODING'}">
									<option value="FOODING" selected="selected">Fooding</option>
									<option value="LODGING">Lodging</option>
								</c:if>
								<c:if test="${commodity.commoditySubType eq 'LODGING'}">
									<option value="FOODING">Fooding</option>
									<option value="LODGING" selected="selected">Lodging</option>
								</c:if>
							</c:if>
						</select>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
			</tr>
		</table>	
		<div class="btnsarea01">
			<div class="infomsgbox" id="infomsgbox" >
				<span id="infomsg"></span>	
			</div>
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			<input type="button" class="editbtn" id="edit" name="edit" value="Edit" onclick="editable();">
			<input type="submit" class="submitbtn" id="submitToEdit" disabled="disabled" value="Submit" onclick="return validate();">
			<!-- <a href="listCommodity.html"><input type="button" class="clearbtn" id="ViewList" value="All List"></a> -->
		</div>
	</form:form>
	</c:otherwise>
</c:choose>
</body>
</html>