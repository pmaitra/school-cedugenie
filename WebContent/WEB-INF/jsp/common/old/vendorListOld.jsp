<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/vendorListPagination.html" var="pagedLink">
	  <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to List Books" />
<meta name="keywords" content="List Books" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Vendor List</title>

<link rel="stylesheet" href="/icam/css/common/vendorList.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/showHideField.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript">
	window.onload=function(){
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			ShowHideField(checkbox[i].value, 'vendorList', checkbox[i]);
		}
	};
	function ShowAll(cb){
		if(cb.checked){
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked=true;
				ShowHideField(checkbox[i].value, 'vendorList', checkbox[i]);
			}
		}
		else{
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked=false;
				ShowHideField(checkbox[i].value, 'vendorList', checkbox[i]);
			}
		}
		
	}
</script>
</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Vendor List &emsp; &emsp; &emsp; &emsp;
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>

<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<input type="checkbox" onclick="ShowAll(this)" />
		<label for="Type">All</label><br>
    <input type="checkbox" class="listShowHide" value="Vendor Name" onclick="ShowHideField('Vendor Name', 'vendorList', this)" checked="checked" />
		<label for="Name">Name</label><br>
    <input type="checkbox" class="listShowHide" value="Contact No 1" onclick="ShowHideField('Contact No 1', 'vendorList', this)" checked="checked" />
		<label for="Edition">Contact No 1</label><br>
    <input type="checkbox" class="listShowHide" value="Contact No 2" onclick="ShowHideField('Contact No 2', 'vendorList', this)" checked="checked" />
		<label for="Medium">Contact No 2</label><br>
    <input type="checkbox" class="listShowHide" value="Address" onclick="ShowHideField('Address', 'vendorList', this)" checked="checked"/>
		<label for="ISBN">Address</label><br>
    <input type="checkbox" class="listShowHide" value="Vendor Type" onclick="ShowHideField('Vendor Type', 'vendorList', this)" checked="checked"/>
		<label for="Author">Vendor Type</label>
</div>


<c:choose>
		<c:when test="${vendorPagedListHolder eq null}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">Vendor List Not Found</span>	
				</div>
			</div>
		</c:when>
<c:otherwise>
	<form:form name="viewVendorListDetails" action="vendorDetails.html" method="POST">
   	  	<table cellspacing="0" cellpadding="0" class="midsec1" id="vendorList">
	   		<tr>
	   			<th>Select</th>
				<th>Vendor Name</th>
				<th>Contact No 1</th>
				<th>Contact No 2</th>
				<th>Address</th>
				<th>Vendor Type</th>
			</tr>	
			
			<c:forEach var="vendor" items="${vendorPagedListHolder.pageList}">	
				<tr>
					<td>
						<input type="radio" name="radio" value="${vendor.vendorCode}"/>
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
					<td>
						${vendor.vendorType}
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tg:paging pagedListHolder="${vendorPagedListHolder}" pagedLink="${pagedLink}"/></td>
			</tr>
		</table>
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			<input type="submit" value="DETAILS" class="submitbtn" onclick="return valradio('radio','warningbox','warningmsg');">	
		</div>
   </form:form>
</c:otherwise>
</c:choose>
</body>
</html>