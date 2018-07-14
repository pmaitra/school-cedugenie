<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/viewApproversListPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to View Approver Group List" />
<meta name="keywords" content="View Approver Group List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Approver Group List</title>
<link rel="stylesheet" href="/icam/css/administrator/listAccessTypeContactMapping.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<!-- Pagination css -->
<link href="/icam/css/common/pagination.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>

</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Approver Group List
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
	<c:when test="${null eq pagedListHolder}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No Approver Group Found</span>	
			</div>				
	</c:when>
<c:otherwise>
<form:form action="getApproverGroupDetails.html" method="POST">
	<table id="accessTypeContactMapping" cellspacing="0" cellpadding="0" class="midsec1">		   		
   		<tr>
   			<th>Select <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
			<th>Approver Group Name</th>
			<th>Created On</th>
			<th>Approver Group Description</th>	
			<th>Approver Process</th>						
		</tr>
		<c:forEach var="approverGroup"  items="${pagedListHolder.pageList}">	
			<tr>
				<td>
					<input type="radio" name="approverGroupCode" id="approverGroupCode" value="${approverGroup.approverGroupCode}"/>
				</td>				
				<td>
					${approverGroup.approverGroupName}
				</td>
				<td>
					${approverGroup.status}
				</td>
				<td>
					<textarea readonly="readonly" class="txtarea" >${approverGroup.approverGroupDesc}</textarea>
				</td>
				<td>
					<c:if test="${approverGroup.serialApproval eq true}">
						SERIAL
					</c:if>
					<c:if test="${approverGroup.parallelApproval eq true}">
						Parallel
					</c:if>
				</td>
			</tr>
		</c:forEach>		
		<tr>
			<td colspan="5" id="toolbar">
				<c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
			</td>
		</tr>					
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>		
		<input type="submit" id="submit" class="submitbtn" name="delete" value="Delete" onclick="return valradio('approverGroupCode','warningbox','warningmsg');"/>	
		<input type="submit" id="submit" class="editbtn" name="details"  value="Details" onclick="return valradio('approverGroupCode','warningbox','warningmsg');"/>	
	</div>
</form:form>
</c:otherwise>
</c:choose>	
</body>
</html>