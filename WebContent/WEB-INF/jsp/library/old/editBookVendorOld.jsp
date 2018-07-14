<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 

<c:url value="/bookVendorPaginationForEdit.html?vendorName=${vendor.vendorName}&vendorCode=${vendor.vendorCode}" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Edit Book Vendor" />
<meta name="keywords" content="Edit Book Vendor" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Edit Book Vendor</title>

<link rel="stylesheet" href="/sms/css/library/editBookVendor.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/pagination.css" rel="stylesheet" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/sms/js/library/editBookVendor.js"></script>

</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Edit Book Vendor
		</h1>
</div>
<c:choose>
		<c:when test="${vendor == null}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">Vendor not found</span>	
				</div>
			</div>
		</c:when>
<c:otherwise>
<div id="wrap">
<form:form name="editVendor"  method="POST" action="updateBookVendorMaping.html">
	<table cellspacing="0" cellpadding="0" class="midsec">
		<tr>
			<th>Vendor Name :: </th>
			<td>
				${vendor.vendorName}
				<input type="hidden" name="vendorName" id="vendorName" value="${vendor.vendorName}"/>
			</td>
		</tr>
		<tr>
			<th>Vendor Code :: </th>
			<td>
				${vendor.vendorCode}
				<input type="hidden" name="vendorCode" id="vendorCode" value="${vendor.vendorCode}" />
			</td>
		</tr>
	</table>
	<c:choose>
		<c:when test="${vendorBookMappingPagedListHolder == null}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">Books  Not Found</span>	
				</div>
			</div>
		</c:when>
	<c:otherwise>
		<table id="" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th colspan="2">
					<select name="query" id="query" class="defaultselect" tabindex="1">
						<option value="" >Please Select...</option>
						<option value="BookCode">Book Code</option>
						<option value="BookName">Book Name</option>
					</select>
				</th>
				<th colspan="4">
					<input type="text" class="textfield" name="data" id="data" value="Search"   onfocus="this.value='';this.style.color='orange';" onblur="javascript: if(this.value==''){this.value='Search';this.style.color='orange';}else{this.style.color='orange';}"/>
				</th>
				<th>
					<input class="editbtn" type="submit" name="bookVendorPaginationForEditSearch" value="Search">
				</th>
			</tr>
		</table>
	
		<table id="table" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th></th>	
				<th>Book Code</th>
				<th>Book Name</th>	
				<th>Book Rate</th>	
				<th>Price History</th>			
			</tr>
			<c:forEach var="item" items="${vendorBookMappingPagedListHolder.pageList}" varStatus="indexVal"> 
				<tr>
					<c:choose>
						<c:when test="${item.purchaseRate == 0}">
							<td><input id="check${indexVal.index+1}" type="checkbox" name="vendorItems[${indexVal.index}].itemCode" value="${item.itemCode}" /></td>
						</c:when>
						<c:otherwise>
							<td><input id="check${indexVal.index+1}" checked="checked" type="checkbox" name="vendorItems[${indexVal.index}].itemCode" value="${item.itemCode}" /></td>
						</c:otherwise>
					</c:choose>
						<td>
							${item.itemCode}
						</td>
						<td>
							${item.itemName}
						</td>
						<td>
							<input name="vendorItems[${indexVal.index}].sellingRate"  id="txt${indexVal.index+1}" value="${item.purchaseRate}" onfocus="this.value='';" class="textfield1" onblur="javascript: if(this.value==''){this.value='0.0';}"/>
						</td>
						<td>
							<input type="button" class="clearbtn" id="${item.itemCode}" value="History" />
						</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="10" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${vendorBookMappingPagedListHolder}" pagedLink="${pagedLink}"/></td>
			</tr>
		</table>
	</c:otherwise>
	</c:choose>
	
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
		<input type="submit" id="submitButton" value="SUBMIT" class="submitbtn" >
	</div>
</form:form>
</div>
</c:otherwise>
</c:choose>
	
<div id="dialog">
	<table id="ShowData" cellspacing="0" cellpadding="0" class="midsec1">
	</table>
	<button type="button" class="editbtn" id="popupBoxNo">OK</button>
</div>
	
</body>
</html>