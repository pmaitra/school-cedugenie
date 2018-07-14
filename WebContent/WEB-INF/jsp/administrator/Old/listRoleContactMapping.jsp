<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listRoleContactMappingPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Role Contact Mapping List" />
<meta name="keywords" content="Role Contact Mapping List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Role Contact Mapping List</title>

<link rel="stylesheet" href="/icam/css/administrator/listRoleContactMapping.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<!-- Pagination Css -->
<link href="/icam/css/common/pagination.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/js/admission/common/validateAdmissionSearch.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Role Contact Mapping List
	</h1>
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
					<span id="errormsg">No Role Contact Mapping List Found</span>	
			</div>				
		</c:when>
	<c:otherwise>
<form:form action="searchRoleContactmapping.html" method="POST">	
	<table cellspacing="0" cellpadding="0" class="midsec1">
		<tr>					
				<td>
					<select name="query" id="query" class="defaultselect">
						<option value="0">Select...</option>
						<option value="contactName">Contact Name</option>
						<option value="roleName">Role Name</option>
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

<form:form action="getRoleContactMapping.html" method="POST">
	
	<table cellspacing="0" cellpadding="0" class="midsec1">	
		<c:forEach var="role" items="${pagedListHolder.pageList}">
			<tr>
				<th colspan="2">
					<input type="radio" name="roleName" value="${role.roleCode}" >&nbsp;&nbsp;&nbsp;&nbsp;Role Name :: ${role.roleName}
				</th>
			</tr>
			<tr>
				<th>User ID</th>
				<th>Name</th>				
			</tr>
			<c:forEach var="resource" items="${role.resourceList}">	
				<tr>					
					<td>
						<input type="text" readonly="readonly" value="${resource.userId}" class="textfield1">
					</td>			
					<td>
		       	 		<input type="text" value="${resource.name}" class="textfield1">
		    		</td>			
				</tr>
			</c:forEach>
		</c:forEach>		
			<tr>
				<td colspan="2" id="toolbar">
					<c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
				</td>
			</tr>
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>		
		<input type="submit" id="submit" class="submitbtn" value="Edit" onclick="return valradio('roleName','warningbox','warningmsg');"/>	
		<input type="reset" class="clearbtn" value="Clear"/>			
	</div>	
</form:form>
</c:otherwise>
</c:choose>
</body>
</html>