<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/addVendorItemPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Map Book Vendor" />
<meta name="keywords" content="Map Book Vendor" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Map Book Vendor</title>

<link rel="stylesheet" href="/sms/css/library/mapBookVendor.css" />

<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/library/mapBookVendor.js"></script>
</head>
<body>
<div class="ttlarea">
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Map Book And Vendor
		<div style="float: right;">
			<div style="float: left; position: relative;">
			<a class="bookDetails" href="downloadBookDetailsExcel.html">
				<button type="button" style="width: 100%;" class="editbtn">Download Excel Sheet</button>
			</a>
			</div>&emsp;			
			<div style="float: right; position: relative;margin-bottom: 2%;">
				<form:form id="safcontents" name="safcontents" method="POST" action="uploadBookVendorExcel.html" enctype="multipart/form-data">
<!-- 					<input class="textfield" type="file" name="imageFile" id="attachment"/> -->
					<span id="FileUpload">
					    <input type="file" size="24"  name="imageFile" id="attachment" onchange="document.getElementById('FileField').value = document.getElementById('attachment').value;" />
					    <span id="BrowserVisible"><input type="text" id="FileField" /></span>
					</span>
					<input type="submit" class="submitbtn" value="Submit" onclick="if(document.getElementById('attachment').value=='')return false; ;">
				</form:form>
				</div>
		</div>		
	</h1>
</div>
<div id="columnsDiv" style="background-color: orange; visibility: collapse; float: right; position: relative;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	    <input type="checkbox" onclick="ShowAll(this)" />
		<label for="Type">All</label><br>
        <input type="checkbox" class="listShowHide" value="Book Code" onclick="ShowHideField('Book Code', 'table', this)" checked="checked" />
		<label for="Book Code">Book Code</label><br>
		<input type="checkbox" class="listShowHide" value="Book Name" onclick="ShowHideField('Book Name', 'table', this)" checked="checked" />
		<label for="Book Name">Book Name</label><br>
		<input type="checkbox" class="listShowHide" value="Book Rate" onclick="ShowHideField('Book Rate', 'table', this)" checked="checked" />
		<label for="Book Rate">Book Rate</label><br>
		<input type="checkbox" class="listShowHide" value="Price History" onclick="ShowHideField('Price History', 'table', this)" checked="checked" />
		<label for="Price History">Price History</label><br>
		</div>
<div id="wrap">
	<c:if test="${uploadErrorMessage ne null }">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">${uploadErrorMessage}</span>	
			</div>
	</c:if>
	<c:if test="${uploadSuccessMessage ne null }">
			<div class="successbox" id="successbox" style="visibility: visible;">
				<span id="successmsg">${uploadSuccessMessage}</span>	
			</div>
	</c:if>
<form:form name="addVendor"  method="POST" action="updateBookVendorMaping.html">
<div class="infomsgbox" id="infomsgbox" >
	<span id="infomsg"></span>	
</div>
	<table cellspacing="0" cellpadding="0" class="midsec">
		<tr>
			<th>Vendor Name<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
			<td>
				<select name="vendorName" id="vendorName" class="defaultselect">
					<option value="">Select...</option>
					<c:forEach var="vendor" items="${vendorType.vendorList}">
						<option value="${vendor.vendorCode}">${vendor.vendorName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>Vendor Code :: </th>
			<td>
				<input type="text" class="textfield" name="vendorCode" id="vendorCode" readonly="readonly" />
			</td>
		</tr>
	</table>
	<c:choose>
		<c:when test="${vendorType.itemList == null && vendorType.itemList.size()== 0}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="errorbox" id="errorbox" >
					<span id="errormsg">Item List Not Found</span>	
				</div>
			</div>
		</c:when>
	<c:otherwise>
		<table id="table" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th><input type="checkbox" disabled="disabled"></th>	
				<th>Book Code</th>
				<th>Book Name</th>	
				<th>Book Rate</th>
				<th>Price History</th>
			</tr>
			<c:forEach var="item" items="${vendorType.itemList}" varStatus="indexVal"> 
				<tr>
					<td>
						<input id="check${indexVal.index+1}" type="checkbox" name="vendorItems[${indexVal.index}].itemCode" value="${item.itemCode}" />
					</td>
					<td>
						${item.itemCode}
					</td>
					<td>
						${item.itemName}
					</td>
					<td>
						<input type="text" class="textfield1" name="vendorItems[${indexVal.index}].sellingRate" id="txt${indexVal.index+1}" value="0.0" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}"/>
					</td>
					<td>
						<input type="button" class="clearbtn" id="${item.itemCode}" value="History" />
					</td>
				</tr>
			</c:forEach>
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

<div id="dialog">
	<table id="ShowData" cellspacing="0" cellpadding="0" class="midsec1">
	</table>
	<button type="button" class="editbtn" id="popupBoxNo">OK</button>
</div>


</body>
</html>