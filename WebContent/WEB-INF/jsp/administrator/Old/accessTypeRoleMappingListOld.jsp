<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<c:url value="/roleAccessMappingPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>

<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Access Type - Role Mapping List" />
<meta name="keywords" content="Access Type - Role Mapping List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Access Type - Role Mapping List</title>

<link rel="stylesheet" href="/icam/css/administrator/accessTypeRoleMapping.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<!-- Pagination Css -->
<link href="/icam/css/common/pagination.css" rel="stylesheet" />

<head>

</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Access Type - Role Mapping List
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
<form method="POST" action="createNewAccessType.html" name="accessTypeRoleMappingList" id="accessTypeRoleMappingList">
	<c:choose>
		<c:when test="${pagedListHolder eq null}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">No Role Access Type Mapping List Found</span>	
			</div>
		</c:when>
	<c:otherwise>
	<table cellspacing="0" cellpadding="0" class="midsec1">	
		<tr>
			<th colspan="3">Existing Access Type(s)</th>
		</tr>
		<tr>
			<th>Select<img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<th>Access Type Name</th>
			<th>Access Type Description</th>
		</tr>
		<c:forEach var="accessType"  items="${pagedListHolder.pageList}">
			<tr>
				<td><input type="radio" name="accessTypeRadio" value="${accessType.accessTypeCode}"/></td>
				<td><input type="text" readonly="readonly" class="textfield1" value="${accessType.accessTypeName}" style="text-transform:uppercase;" /></td>
				<td><textarea name="accessTypeDesc" id="accessTypeDesc" readonly="readonly" class="txtarea" >${accessType.accessTypeDesc}</textarea></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="3" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
		</tr>
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>		
		<input type="submit" id="submit" name="edit" class="editbtn" value="Edit" onclick="return valradio('accessTypeRadio','warningbox','warningmsg');"/>	
		<input type="submit" value="Delete" name="delete" class="clearbtn" onclick="return valradio('accessTypeRadio','warningbox','warningmsg');"/>			
		<input type="submit" class="submitbtn" value="Create"/>
	</div>

</c:otherwise>
</c:choose>
</form>
</body>
</html>