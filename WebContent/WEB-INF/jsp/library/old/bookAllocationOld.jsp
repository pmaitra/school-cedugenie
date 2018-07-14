<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/bookAllocationListPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Book Allocation" />
<meta name="keywords" content="Book Allocation" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Book Allocation</title>

<link rel="stylesheet" href="/sms/css/library/bookAllocation.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<link href="/sms/css/common/pagination.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/library/bookAllocation.js"></script>
<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/common/validateSearch.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Book Allocation
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>
<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<input type="checkbox" onclick="ShowAll(this)" />
	<label for="Type">All</label><br>
<input type="checkbox" class="listShowHide" value="Request ID" onclick="ShowHideField('Request ID', 'ballo', this)" checked="checked" />
	<label for="Request ID">Request ID</label><br>
	<input type="checkbox" class="listShowHide" value="Registration ID" onclick="ShowHideField('Registration ID', 'ballo', this)" checked="checked" />
	<label for="Registration ID">Registration ID</label><br>
	<input type="checkbox" class="listShowHide" value="Requested Date" onclick="ShowHideField('Requested Date', 'ballo', this)" checked="checked"/>
	<label for="Requested Date">Requested Date</label><br>
	<input type="checkbox" class="listShowHide" value="Request Expire Date" onclick="ShowHideField('Request Expire Date', 'ballo', this)" checked="checked"/>
	<label for="Request Expire Date">Request Expire Date</label><br>
	<input type="checkbox" class="listShowHide" value="Status" onclick="ShowHideField('Status', 'ballo', this)" checked="checked"/>
	<label for="Status">Status</label><br>
	<input type="checkbox" class="listShowHide" value="Allocate" onclick="ShowHideField('Allocate', 'ballo', this)" checked="checked" />
	<label for="Allocate">Allocate</label><br>
</div>
<c:choose>
	<c:when test="${pagedListHolder==null}" >
		<div class="btnsarea01" style="visibility: visible;">
			<div class="errorbox" id="errorbox" >
				<span id="errormsg">No Books Found</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<table cellspacing="0" cellpadding="0" class="midsec1">
		<form:form method="POST" id="sflcontents" name="sflcontents" action="searchForBookRequest.html" >
			<tr>
				<td>
					<input type="text" name="data" class="textfield" id="data" value="Search Book Request" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='Search Library';}" />
				</td>
				<td>
					<select name="query" id="query" class="defaultselect">
						<option value="" selected>Please Select...</option>
						<option value="RequestID">Request ID</option>
						<option value="UserID">User ID</option>
						<option value="RequestedDate">Requested Date</option>
						<option value="RequestExpireDate">Request Expire Date</option>
						<option value="Status">Status</option>
					</select>
				</td>
				<td>
					<input type="submit" class="editbtn" name="searchForBookRequest" value="Search" onclick="return validateSearch('query','data','warningbox','warningmsg');">
				</td>
			</tr>
		</form:form>
	</table>
	<table id="ballo" cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<th>Request ID</th>	
			<th>User ID</th>	
			<th>Requested Date</th>
			<th>Request Expire Date</th>
			<th>Status</th>
			<th>Allocate</th>
		</tr>
		<c:forEach var="bookRequestResult" items="${pagedListHolder.pageList}">
			<tr>
				<td>${bookRequestResult.bookRequestCode}</td>
				<td>${bookRequestResult.bookRequestFor.userId}</td>
				<td>${bookRequestResult.bookRequestOpenDate}</td>
				<td>${bookRequestResult.bookRequestCloseDate}</td>
				<td>${bookRequestResult.bookRequestStatus}</td>
				<td>
					<c:choose>
						<c:when test="${bookRequestResult.bookRequestStatus=='CLOSE'}">
							Allocated
						</c:when>
						<c:otherwise>
							<a href="issuingBookForBookAllocation.html?requestId=${bookRequestResult.bookRequestCode}">Allocate</a>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
		</tr>
	</table>
	</c:otherwise>
</c:choose>
<div class="btnsarea01">
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
</div>
</body>
</html>