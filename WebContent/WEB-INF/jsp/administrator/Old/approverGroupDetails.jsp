<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Approver Group Details" />
<meta name="keywords" content="Approver Group Details" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Approver Group Details</title>

<link rel="stylesheet" href="/icam/css/administrator/roleContactMapping.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Approver Group Details
	</h1>
</div>
<c:choose>
	<c:when test="${null eq approver}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">Approver Details Not Found</span>	
			</div>				
	</c:when>
<c:otherwise>			
<form method="POST" action="getApproverGroupDetails.html">		
		<table cellspacing="0" cellpadding="0" class="midsec1">	
			<c:if test="${approver.approverGroupName ne null}">
			<tr>
				<th colspan="2"> Approver Group Name :- ${approver.approverGroupName}</th>				
			</tr>
			</c:if>
			<tr>
				<th>User Name</th>
				<th>User ID</th>
			</tr>
			<c:forEach var="resource" varStatus="roll" items="${approver.resourceList}">
			<tr>				
				<td>
					${resource.name}
				</td>
				<td>
					${resource.userId}
				</td>
			</tr>			
			</c:forEach>
			</table>
			<input type="hidden" name="approverGroupCode" value="${approver.approverGroupCode}">			
			<div class="btnsarea01">
				<input type="submit" id="submit" class="submitbtn" name="delete" value="Delete"/>
				<input type="submit" id="submit" class="submitbtn" name="back" value="Back"/>			
			</div>	
</form>
</c:otherwise>
</c:choose>	
</body>
</html>