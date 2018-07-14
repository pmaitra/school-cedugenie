<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/studentBookAllocationListPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to List Student Book Allocation" />
<meta name="keywords" content="List Student Book Allocation" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>List Student Book Allocation</title>

<link rel="stylesheet" href="/sms/css/library/listStudentBookAllocation.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<link href="/sms/css/common/pagination.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/library/listStudentBookAllocation.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;List Student Book Allocation&nbsp;&nbsp;&nbsp;&nbsp;
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>
<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<input type="checkbox" onclick="ShowAll(this)" />
	<label for="Type">All</label><br>
	<input type="checkbox" class="listShowHide" value="Book Code" onclick="ShowHideField('Book Code', 'lstbk', this)" />
	<label for="Book Code">Book Code</label><br>
	<input type="checkbox" class="listShowHide" value="Book Name" onclick="ShowHideField('Book Name', 'lstbk', this)" checked="checked" />
	<label for="Book Name">Book Name</label><br>
	<input type="checkbox" class="listShowHide" value="Book ID" onclick="ShowHideField('Book ID', 'lstbk', this)" />
	<label for="Book ID">Book ID</label><br>
	<input type="checkbox" class="listShowHide" value="Open Date" onclick="ShowHideField('Open Date', 'lstbk', this)" checked="checked" />
	<label for="Open Date">Open Date</label><br>
	<input type="checkbox" class="listShowHide" value="Close Date" onclick="ShowHideField('Close Date', 'lstbk', this)" checked="checked" />
	<label for="Close Date">Close Date</label><br>
</div>
<c:choose>
		<c:when test="${pagedListHolder==null}" >
			<div class="btnsarea01" style="visibility: visible;">
				<div class="errorbox" id="errorbox" >
					<span id="errormsg">No Allocated Books Found</span>	
				</div>
			</div>
		</c:when>
<c:otherwise>
<form method="POST" id="sflcontents" name="bookAllocation" action="submitIssuingBookForBookAllocation.html">

	<c:forEach var="bookRequestList" items="${pagedListHolder.pageList}">
	<table cellspacing="0" cellpadding="0" class="midsec">
		<tr>
			<th>Requested ID :: </th>	
			<td>
				${bookRequestList.bookRequestCode}
				<input type="hidden" name="bookRequestedId" value="${bookRequestList.bookRequestCode}"/>
			</td>
		</tr>
		<tr>			
			<th>User ID :: </th>	
			<td>
				${bookRequestList.bookRequestFor.userId}
				<input type="hidden" name="userId" value="${bookRequestList.bookRequestFor.userId}"/>
			</td>
		</tr>
		<tr>
			<th>Name :: </th>
			<td>
				${bookRequestList.bookRequestFor.firstName} ${bookRequestList.bookRequestFor.middleName} ${bookRequestList.bookRequestFor.lastName}
			</td>					
		</tr>
	</table>
	<table cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<td colspan="5">:: Request Details ::</td>
		</tr>
	</table>
	<table id="lstbk" cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<th>Book Code</th>					
			<th>Book Name</th>
			<th>Book ID</th>
			<th>Open Date</th>
			<th>Close Date</th>
		</tr>
		<c:forEach var="bookRequestResults" items="${bookRequestList.bookRequestDetailsList}">
			<tr>
				<td>${bookRequestResults.bookCode}</td>	
				<td>${bookRequestResults.bookName}</td>	
				<td>${bookRequestResults.bookId}</td>				
				<td>${bookRequestList.bookRequestOpenDate}</td>
				<td>${bookRequestList.bookRequestCloseDate}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
		</tr>
	</table>
	</c:forEach>

</form>
</c:otherwise>
</c:choose>
</body>
</html>