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
<c:url value="/listFeesTemplatePagination.html" var="pagedLink">
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
<title>Fees Template List</title>

<link rel="stylesheet" href="/icam/css/backoffice/listFeesTemplate.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/backoffice/listFeesTemplate.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>

<script type="text/javascript" src="/icam/js/common/validateSearch.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Fees Template List
	</h1>
</div>
<div class="btnsarea01">
	<c:if test="${insertUpdateStatus ne null}">
		<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
			<span id="infomsg1">${insertUpdateStatus}</span>	
		</div>
	</c:if>
</div>


<c:choose>	
	<c:when test="${pagedListHolder eq null || empty pagedListHolder}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Fees Template List Not Found</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<form:form id="getFeesTemplateDetails" name="getFeesTemplateDetails" action="getFeesTemplateDetails.html" method="POST">
		
		<table id="add"  cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Search Parameter</th>
				<th>Search Value</th>
				<th>
				</th>
			</tr>
			<tr>			
				<td>
					<select id="searchKey" name="searchKey" class="defaultselect">
						<option value="">Please Select</option>
						<option value="TemplateName">Template Name</option>
						<option value="Standard">Standard</option>
					</select>
				</td>
				<td>
					<input type="text" class="textfield2" name="searchValue" id="another"/>
				</td>
				<td>
					<input type="submit" name="searchSubmit" id="search" onclick="return validateSearch('searchKey','another','warningbox','warningmsg');" value="Search" class="editbtn">
				</td>
			</tr>		
		</table>
		
		<table  id="templateTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th><input type="radio" disabled="disabled"></th>
				<th>Template Name</th>
				<th>Standard</th>
				<th>Applied</th>
			</tr>
			<c:forEach var="template" items="${pagedListHolder.pageList}">
				<tr>
					<td>
						<input type="radio" name="templateCode" value="${template.templateCode}" />
					</td>
					<td>
						${template.templateName}
					</td>
					<td>
						${template.standard}
					</td>
					<td>
						<c:if test="${template.applied eq true}">
							<img src="/icam/images/yes_png.png" alt="YES" />
						</c:if>
						<c:if test="${template.applied eq false}">
							<img src="/icam/images/no_png.png" alt="NO" />
						</c:if>
					</td>	
				</tr>
			</c:forEach>
			<tr>
 				<td colspan="8" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
			</tr>
		</table>	
		<div class="btnsarea01">
			<div class="infomsgbox" id="infomsgbox" >
				<span id="infomsg"></span>	
			</div>
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			<input type="submit" class="submitbtn" value="Submit" onclick="return valradio('templateCode', 'warningbox', 'warningmsg');">
		</div>
	</form:form>
	</c:otherwise>
</c:choose>
</body>
</html>