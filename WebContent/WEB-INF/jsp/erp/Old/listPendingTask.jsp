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
<c:url value="/listPendingTaskPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Pending Task List" />
<meta name="keywords" content="Pending Task List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Pending Task List</title>

<link rel="stylesheet" href="/icam/css/erp/pendingTaskList.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/showHideField.js"></script>
<script type="text/javascript" src="/icam/js/common/validateSearch.js"></script>
<script type="text/javascript" src="/icam/js/erp/pendingTaskList.js"></script>
</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;List Pending Task 
		</h1>
	</div>	
	<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
		<h1>Show / Hide Columns</h1>
	</div>
	<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">	<input type="checkbox" onclick="ShowAll(this)" />
	<label for="Type">All</label><br>
		<input type="checkbox" class="listShowHide" value="Serial No." onclick="ShowHideField('Serial No.', 'pending', this)" checked="checked" />
				<label for="Serial No.">Serial No.</label><br>
		    <input type="checkbox" class="listShowHide" value="Title" onclick="ShowHideField('Title', 'pending', this)" checked="checked" />
				<label for="Title">Title</label><br>
		    <input type="checkbox" class="listShowHide" value="User ID" onclick="ShowHideField('User ID', 'pending', this)" checked="checked" />
				<label for="User ID">User ID</label><br>
		    <input type="checkbox" class="listShowHide" value="Created By" onclick="ShowHideField('Created By', 'pending', this)" checked="checked" />
				<label for="Created By">Created By</label><br>
		    <input type="checkbox" class="listShowHide" value="Applied On" onclick="ShowHideField('Applied On', 'pending', this)" checked="checked" />
				<label for="Applied On">Applied On</label><br>
		    <input type="checkbox" class="listShowHide" value="Task Status" onclick="ShowHideField('Task Status', 'pending', this)" checked="checked" />
				<label for="Task Status">Task Status</label><br>
	</div>
	<c:choose>
		<c:when test="${pagedListHolder eq null}">			
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No Pending Task For You</span>	
			</div>								
		</c:when>
	<c:otherwise>
	<form:form method="POST" action="searchOnListPendingTask.html">
		<table cellspacing="0" cellpadding="0" class="midsec1">		
			<tr>
				<td>
					<select name="query" id="query" class="defaultselect">
						<option value="">Select...</option>
						<option value="requestId">Request ID</option>					
						<option value="userId">User ID</option>					
						<option value="appliedOn">Applied On</option>
						<option value="taskStatus">Task Status</option>
					</select>
				</td>		
				<td>
					<input type="text" class="textfield2" name="data" id="data" value="Search" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='Search';}" />
				</td>		
				<td>
					<input type="submit" value="Search" class="editbtn" name="searchSubmit"  id="searchSubmit" onclick="return validateSearch('query','data','warningbox','warningmsg')" />	 
				</td>
			</tr>
		</table>
	</form:form>							
		<table id="pending" cellspacing="0" cellpadding="0" class="midsec1">
				<tr>
					<th>Serial No.</th>
					<th>Request ID</th>
					<th>Title</th>					
					<th>Requested By</th>
					<th>User ID</th>
					<th>Applied On</th>							
					<th>Task Status</th>	
					<th>ACTION</th>
				</tr>
				<c:forEach var="task" items="${pagedListHolder.pageList}" varStatus="serialNo">						
				<tr>
					<td><c:out value="${(serialNo.index)+1}"/></td>
					<td><c:out value="${task.taskCode}"/></td>
					<td><c:out value="${task.taskName}"/></td>					
					<td><c:out value="${task.taskOwnerName}"/></td>
					<td><c:out value="${task.leave.userId}"/></td>
					<td><c:out value="${task.activationTime}"/></td>
					<td><c:out value="${task.processStatus}"/></td>
					<td><a href="pendingLeaveDetails.html?taskCode=${task.taskCode}"><input type="button" name="details" value="DETAILS" class="submitbtn"></a></td>					
				</tr>						
				</c:forEach>
				<tr>
					<td colspan="7" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
				</tr>
		</table>			
	</c:otherwise>		
	</c:choose>
</body>
</html>