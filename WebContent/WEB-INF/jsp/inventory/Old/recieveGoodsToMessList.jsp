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
<c:url value="/purchaseOrderListPagination.html" var="pagedLink">
	  <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Purchase Order List" />
<meta name="keywords" content="Purchase Order List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Goods To Mess List</title>

<link rel="stylesheet" href="/icam/css/inventory/recieveGoodsToMessList.css" />
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
<script type="text/javascript" src="/icam/js/inventory/recieveGoodsToMessList.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Goods To Mess List
	</h1>
</div>
	
	<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
		<h1>Show / Hide Columns</h1>
	</div>
	<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
		<input type="checkbox" onclick="ShowAll(this)" checked="checked" />
		<label for="Type">All</label><br>
		<input type="checkbox" class="listShowHide" value="Order No" onclick="ShowHideField('Order No', 'purchaseOrderTable', this)" checked="checked" />
			<label for="Order No">Order No</label><br>
	    <input type="checkbox" class="listShowHide" value="Quarter Master Session" onclick="ShowHideField('Quarter Master Session', 'purchaseOrderTable', this)" checked="checked" />
			<label for="Quarter Master Session">Quarter Master Session</label><br>
		<input type="checkbox" class="listShowHide" value="Order Status" onclick="ShowHideField('Order Status', 'purchaseOrderTable', this)" checked="checked" />
			<label for="Order Status">Order Status</label><br>
		<input type="checkbox" class="listShowHide" value="Receive Status" onclick="ShowHideField('Receive Status', 'purchaseOrderTable', this)" checked="checked"/>
			<label for="Receive Status">Receive Status</label><br>
		<input type="checkbox" class="listShowHide" value="Order Issue Date" onclick="ShowHideField('Order Issue Date', 'purchaseOrderTable', this)" checked="checked" />
			<label for="Order Issue dDate">Order Issue Date</label><br>
		<input type="checkbox" class="listShowHide" value="Order Close Date" onclick="ShowHideField('Order Close Date', 'purchaseOrderTable', this)" checked="checked" />
			<label for="Order Close Date">Order Close Date</label><br>
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
	
	
	<form:form id="searchGoodsToMess" name="searchGoodsToMess" action="searchGoodsToMessList.html" method="POST">
	
		<table cellspacing="0" cellpadding="0" class="midsec1">
			<tr>	
				<td>
					<select name="query" id="query" class="defaultselect1">
						<option value="">Please Select...</option>
						<option value="OrderNo">Order No</option>
						<option value="HouseMasterSession">Quarter Master Session</option>
						<option value="OrderStatus">Order Status</option>
						<option value="ReceiveStatus">Receive Status</option>
						<option value="OrderIssueDate">Order Issue Date</option>
						<option value="OrderCloseDate">Order Close Date</option>
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
	<c:choose>
	<c:when test="${purchaseOrderPagedListHolder.pageList eq null}">
		<div class="btnsarea01" >
			<div class="infomsgbox" style="visibility: visible;">
				<span>No Goods For Mess Found. </span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<table  id="purchaseOrderTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Order No</th>
				<th>Quarter Master Session</th>
				<th>Order Status</th>
				<th>Receive Status</th>
				<th>Order Issue Date</th>
				<th>Order Close Date</th>
				<th></th>
			</tr>
			<c:forEach var="purchaseOrder" items="${purchaseOrderPagedListHolder.pageList}" varStatus="i">
				<tr>
					<td>
						${purchaseOrder.purchaseOrderId}
					</td>
					<td>
						${purchaseOrder.inventorySession.academicYearName}
					</td>
					<td>
						${purchaseOrder.orderStatus}
					</td>
					<td>
						${purchaseOrder.receiveStatus}
					</td>
					<td>
						${purchaseOrder.orderOpenDate}
					</td>
					<td>
						${purchaseOrder.orderCloseDate}
					</td>
					
					<td>
						<a class="hlink" onClick="window.open('goodsToMessListDetails.html?goodsToMessOrderCode=${purchaseOrder.purchaseOrderId}','_self')" style="cursor:pointer;color: #000080;"> details</a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="9" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${purchaseOrderPagedListHolder}" pagedLink="${pagedLink}"/></td>
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
	
	</c:otherwise>
</c:choose>
</body>
</html>