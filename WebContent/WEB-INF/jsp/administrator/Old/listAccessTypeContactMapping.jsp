<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listAccessTypeContactMappingPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Access Type Contact Mapping List" />
<meta name="keywords" content="Access Type Contact Mapping List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Access Type Contact Mapping List</title>

<link rel="stylesheet" href="/icam/css/administrator/listAccessTypeContactMapping.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<!-- Pagination css -->
<link href="/icam/css/common/pagination.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/showHideField.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/js/admission/common/validateAdmissionSearch.js"></script>
<script type="text/javascript">
	window.onload=function(){
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			ShowHideField(checkbox[i].value, 'accessTypeContactMapping', checkbox[i]);
		}
	};
	
	function ShowAll(cb){
		if(cb.checked){
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked=true;
				ShowHideField(checkbox[i].value, 'accessTypeContactMapping', checkbox[i]);
			}
		}
		else{
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked=false;
				ShowHideField(checkbox[i].value, 'accessTypeContactMapping', checkbox[i]);
			}
		}
		
	}
</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Access Type Contact Mapping List		
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>
<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<input type="checkbox" onclick="ShowAll(this)" />
	<label for="Type">All</label><br>
		<input type="checkbox" class="listShowHide" value="Code" onclick="ShowHideField('Code', 'accessTypeContactMapping', this)" checked="checked" />
			<label for="Code">Code</label><br>
	    <input type="checkbox" class="listShowHide" value="Contact Name" onclick="ShowHideField('Contact Name', 'accessTypeContactMapping', this)" checked="checked" />
			<label for="Contact Name">Contact Name</label><br>
	    <input type="checkbox" class="listShowHide" value="Access Type" onclick="ShowHideField('Access Type', 'accessTypeContactMapping', this)" checked="checked" />
			<label for="Access Type">Access Type</label><br>
</div>
		<c:if test="${successMessage ne null}">
				<div class="successbox" id="successboxmsgbox" style="visibility: visible;">
					<span>${successMessage}</span>	
				</div>
		</c:if>
			
		<c:if test="${errorMessage ne null}">
					<div class="errorbox" id="errormsgbox" style="visibility: visible;">
						<span>${errorMessage}</span>	
					</div>
		</c:if>
<c:choose>
		<c:when test="${pagedListHolder eq null}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No Access Type Contact Mapping List Found</span>	
			</div>				
		</c:when>
<c:otherwise>
<form:form action="accessTypeContactMappingSearch.html" method="POST">	
	<table cellspacing="0" cellpadding="0" class="midsec1">
		<tr>					
				<td>
					<select name="query" id="query" class="defaultselect">
						<option value="0">Select...</option>
						<option value="contactName">Contact Name</option>
						<option value="accessType">Access Type</option>
						<option value="userId">User ID</option>						
					</select>
				</td>
				<td>
					<input type="text" class="textfield" name="data" id="data" value="Search" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='Search';}" />
				</td>
				<td>
					<input type="submit" name="" value="Search" class="editbtn" onclick="return validateSearch('query','data','warningbox','warningmsg');">
				</td>
		</tr>
	</table>
</form:form>
<form:form action="inactiveAccessTypeContactMapping.html" method="POST">	
	<table id="accessTypeContactMapping" cellspacing="0" cellpadding="0" class="midsec1">		   		
   		<tr>
   			<th>Select</th>
			<th>User ID</th>
			<th>Contact Name</th>
			<th>Access Type</th>			
		</tr>
		<c:forEach var="resource"  items="${pagedListHolder.pageList}">	
			<tr>
				<td>
					<input type="radio" name="resourceId" value="${resource.userId}~${resource.accessType.accessTypeCode}"/>
				</td>				
				<td>
					${resource.userId}
				</td>
				<td>
					${resource.name}
				</td>
				<td>
					${resource.accessType.accessTypeName}
				</td>
			</tr>
		</c:forEach>		
		<tr>
			<td colspan="4" id="toolbar">
				<c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
			</td>
		</tr>					
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>		
		<input type="submit" id="submit" class="submitbtn" value="Delete" onclick="return valradio('resourceId','warningbox','warningmsg');"/>	
	</div>
</form:form>
</c:otherwise>
</c:choose>	
</body>
</html>