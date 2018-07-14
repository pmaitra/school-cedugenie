<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listBookVendorPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Book Vendor List" />
<meta name="keywords" content="Book Vendor List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Book Vendor List</title>

<link rel="stylesheet" href="/sms/css/library/listBookVendors.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/css/common/pagination.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/sms/js/common/validateSearch.js"></script>

<script type="text/javascript">

window.onload = function(){
	var checkbox = getElementsByClassName("listShowHide");
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'vbst', checkbox[i]);
	}
};
function ShowAll(cb){
	if(cb.checked){
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			checkbox[i].checked=true;
			ShowHideField(checkbox[i].value, 'vbst', checkbox[i]);
		}
	}
	else{
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			checkbox[i].checked=false;
			ShowHideField(checkbox[i].value, 'vbst', checkbox[i]);
		}
	}
	
}

function onSearchingProducts(){
	if(validateSearch('query','data','warningbox','warningmsg')){
		document.viewVendorListDetails.method="POST";
		document.viewVendorListDetails.action="searchBookVendorList.html";
		document.viewVendorListDetails.submit();             // Submit the page
	  	return true;
	}else{
		return false;
	}
}  

function editAction(){
	document.viewVendorListDetails.method="POST";
	document.viewVendorListDetails.action="editBookVendor.html";
	document.viewVendorListDetails.submit();             // Submit the page
  	return true;
}

</script>
</head>
<body>
<div class="ttlarea">
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Book Vendor List
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>
<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<input type="checkbox" onclick="ShowAll(this)" />
	<label for="Type">All</label><br>
	<input type="checkbox" class="listShowHide" value="Vendor Code" onclick="ShowHideField('Vendor Code', 'vbst', this)" />
	<label for="Vendor Code">Vendor Code</label><br>
	<input type="checkbox" class="listShowHide" value="Vendor Name" onclick="ShowHideField('Vendor Name', 'vbst', this)" checked="checked" />
	<label for="Vendor Name">Vendor Name</label><br>
	<input type="checkbox" class="listShowHide" value="Contact No 1" onclick="ShowHideField('Contact No 1', 'vbst', this)" checked="checked" />
	<label for="Contact No 1">Contact No 1</label><br>
	<input type="checkbox" class="listShowHide" value="Contact No 2" onclick="ShowHideField('Contact No 2', 'vbst', this)" />
	<label for="Contact No 2">Contact No 2</label><br>
	<input type="checkbox" class="listShowHide" value="Address" onclick="ShowHideField('Address', 'vbst', this)" checked="checked" />
	<label for="Address">Address</label><br>
</div>
<form:form name="viewVendorListDetails" id="viewVendorListDetails" >

<c:choose>
	<c:when test="${pagedListHolder eq null}">		
		<div id="errorbox" style="visibility:visible;">
			<span class="errormsg">No Book Vendor List Found</span>
		</div>
	</c:when>
<c:otherwise>
   	  	<table cellspacing="0" cellpadding="0" class="midsec1">
	   	  	<tr>	
				<td>
					<select class="defaultselect" name="query" id="query">
						<option value="">Please Select...</option>
						<option value="VendorCode">Vendor Code</option>
						<option value="VendorName">Vendor Name</option>	
						<option value="ContactNo1">Contact No 1</option>			
						<option value="ContactNo2">Contact No 2</option>
						<option value="Address">Address</option>				
					</select>
				</td>		
				<td>
					<input type="text" class="textfield" name="data" id="data" />
				</td>
				<td>
					<button class="editbtn" type="submit" id="submit" onclick="return onSearchingProducts();">	Search </button>
				</td>
			</tr>
		</table>
		<table id="vbst" cellspacing="0" cellpadding="0" class="midsec1">
	   		<tr>
	   			<th>Select</th>
				<th>Vendor Code</th>
				<th>Vendor Name</th>
				<th>Contact No 1</th>
				<th>Contact No 2</th>
				<th>Address</th>		
			</tr>
			<c:forEach var="vendor" items="${pagedListHolder.pageList}">	
				<tr>
					<td>
						<INPUT type="radio" name="vendorCode" value="${vendor.vendorCode}"/>
					</td>				
					<td>
						${vendor.vendorCode}
					</td>
					<td>
						${vendor.vendorName}
					</td>
					<td>
						${vendor.vendorContactNo1}
					</td>
					<td>
						${vendor.vendorContactNo2}
					</td>
					<td>
						${vendor.address}
					</td>
				</tr>
			</c:forEach>
			<tr>
				 <td colspan="6" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
			</tr>	
			</table>
	  
	  <div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			<button type="submit" class="submitbtn" id="submitAction" onclick="editAction();">Edit</button>
		</div>
	  </c:otherwise>
   </c:choose>
   </form:form>
   
</body>
</html>