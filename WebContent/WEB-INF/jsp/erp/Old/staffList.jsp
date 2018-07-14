<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/staffListPagination.html?resourceType=${ResourceType}" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Staffs List" />
<meta name="keywords" content="Staffs List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Staffs List</title>
<link rel="stylesheet" href="/icam/css/erp/staffList.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="/icam/css/common/pagination.css">
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/showHideField.js"></script>
<script type="text/javascript">
	window.onload=function(){
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			ShowHideField(checkbox[i].value, 'det', checkbox[i]);
		}
	};
	
	function ShowAll(cb){
		if(cb.checked){
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked=true;
				ShowHideField(checkbox[i].value, 'det', checkbox[i]);
			}
		}
		else{
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked=false;
				ShowHideField(checkbox[i].value, 'det', checkbox[i]);
			}
		}
		
	}
</script>

</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Staffs List		
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>	

<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">

<input type="checkbox" onclick="ShowAll(this)" />
	<label for="Type">All</label><br>
	<input type="checkbox" class="listShowHide" value="User ID" onclick="ShowHideField('User ID', 'det', this)" checked="checked" />
			<label for="User ID">User ID</label><br>
	    <input type="checkbox" class="listShowHide" value="Name" onclick="ShowHideField('Name', 'det', this)" checked="checked" />
			<label for="Name">Name</label><br>
	    <input type="checkbox" class="listShowHide" value="Contact NO." onclick="ShowHideField('Contact NO.', 'det', this)" checked="checked" />
			<label for="Contact NO.">Contact NO.</label><br>
	    <input type="checkbox" class="listShowHide" value="EMAIL" onclick="ShowHideField('EMAIL', 'det', this)" checked="checked" />
			<label for="EMAIL">EMAIL</label><br>
	    <input type="checkbox" class="listShowHide" value="View Task" onclick="ShowHideField('View Task', 'det', this)" checked="checked" />
			<label for="View Task">View Task</label><br>
</div>
<c:choose>
	<c:when test="${pagedListHolder.pageList == null}">
		<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
			<span id="infomsg">No Staff Found</span>
		</div>
	</c:when>
<c:otherwise>
<form:form action="viewStaffDetails.html" method="POST">
<table cellspacing="0" cellpadding="0" class="midsec1">	
	<tr>		
		<td>
			<select name="query" id="query" class="defaultselect">
				<option value="0" selected>Please Select...</option>
				<option value="UserID">User ID</option>
				<option value="firstName">First Name</option>
				<option value="middleName">Middle Name</option>
				<option value="lastName">Last Name</option>
				<option value="designation">Designation</option>
				<option value="employeeType">Employee Type</option>
				<option value="Email">Email</option>										
			</select>
		</td>
		<td>
			<input type="text" class="textfield2" name="data" value="Search Staff" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='Search';}" />
		</td>
		<td>		
			<input type="submit" name="searchSubmit" class="editbtn" value="Search" />
			<input type="hidden" name="resourceType" value="${ResourceType}" readonly="readonly" />
		</td>
	</tr>
</table>
</form:form>
	<table id="det" cellspacing="0" cellpadding="0" class="midsec1">	
		<tr>
			<th>Sl No.</th>
			<th>User ID</th>
			<th>Code</th>
			<th>Employee Type</th>
			<th>Name</th>		
			<th>Designation</th>			
			<th>Details</th>	
		</tr>
		<c:set var="count" value="${first}" scope="page" />
		<c:forEach var="emp" items="${pagedListHolder.pageList}">
		<tr>
			<td>${count}</td> 
			<td>${emp.resource.userId}</td> 
			<td>${emp.employeeCode}</td>
			<td>${emp.employeeType.employeeTypeName}</td>	
			<td><c:out value="${emp.resource.firstName} ${emp.resource.middleName} ${emp.resource.lastName}"/></td>	
			<td>${emp.designation.designationName}</td>			
			<td><a href="viewEditStaffDetails.html?userId=${emp.resource.userId}"><input type="button" name="details" value="DETAILS" class="submitbtn"></a></td>
			<c:if test="${count lt total}">			
				<c:set var="count" value="${count + 1}" scope="page"/>
			</c:if>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="8" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
		</tr>
	</table>
</c:otherwise>
</c:choose>

</body>
</html>