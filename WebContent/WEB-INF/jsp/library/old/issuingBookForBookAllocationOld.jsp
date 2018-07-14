<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<c:url value="/issuingBookListPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<c:url value="/issuedBookListPagination.html" var="pagedLink">
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
<title>Book Allocation Request Details</title>

<link rel="stylesheet" href="/sms/css/library/issuingBookForBookAllocation.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<link href="/sms/css/common/pagination.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/library/issuingBookForBookAllocation.js"></script>
<script src= "/sms/Cal/zebra_datepicker.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media="all" href="/sms/Cal/default.css" />
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Book Allocation Request Details
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>
<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<input type="checkbox" onclick="ShowAll(this)" />
		<label for="Type">All</label><br>
        <input type="checkbox" class="listShowHide" value="Book Code" onclick="ShowHideField('Book Code', 'rdet', this)"/>
		<label for="Book Code">Book Code</label><br>
		<input type="checkbox" class="listShowHide" value="Requested Date" onclick="ShowHideField('Requested Date', 'rdet', this)" />
		<label for="Requested Date">Requested Date</label><br>
		<input type="checkbox" class="listShowHide" value="Request Expire Date" onclick="ShowHideField('Request Expire Date', 'rdet', this)" />
		<label for="Request Expire Date">Request Expire Date</label><br>
		<input type="checkbox" class="listShowHide" value="Check Waiting List" onclick="ShowHideField('Check Waiting List', 'rdet', this)" checked="checked" />
		<label for="Check Waiting List">Check Waiting List</label><br>
		<input type="checkbox" class="listShowHide" value="Available Book ID" onclick="ShowHideField('Available Book ID', 'rdet', this)" checked="checked" />
		<label for="Available Book ID">Available Book ID</label><br>
		<input type="checkbox" class="listShowHide" value="Available copies" onclick="ShowHideField('Available copies', 'rdet', this)" checked="checked" />
		<label for="Available copies">Available copies</label><br>
		<input type="checkbox" class="listShowHide" value="Threshold" onclick="ShowHideField('Threshold', 'rdet', this)" checked="checked" />
		<label for="Threshold">Threshold</label><br>
		</div>
<c:choose>
	<c:when test="${errorMessageDisplay!=null}" >
		<div class="btnsarea01" style="visibility: visible;">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Data Not Found</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<form:form method="POST" id="sflcontents" name="bookAllocation" action="submitIssuingBookForBookAllocation.html">
		<div class="infomsgbox" id="infomsgbox" >
			<span id="infomsg"></span>	
		</div>
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th>Requested Id ::</th>	
				<td>
					${BookRequestResult.bookRequestCode}
					<input type="hidden" name="bookRequestedId" value="${BookRequestResult.bookRequestCode}"/>
				</td>
			</tr>
			<tr>				
				<th>User Id ::</th>	
				<td>
					${BookRequestResult.bookRequestFor.userId}
					<input type="hidden" name="userId" value="${BookRequestResult.bookRequestFor.userId}"/>
				</td>					
			</tr>
		</table>
		
		
		<table cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<td colspan="4">:: Request Details ::</td>
			</tr>
		</table>
		
		<c:if test="${message!=null}" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">${message}</span>
			</div>
		</c:if>
		
		<table id="rdet" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Book Code</th>	
				<th>Book Name</th>					
				<th>Requested Date</th>
				<th>Request Expire Date</th>
				<th>Check Waiting List</th>
				<th>Available Book ID</th>
				<th>Available copies</th>
				<th>Threshold</th>
				<th>Expected Return Date</th>
			</tr>
			<c:forEach var="bookRequestResults" items="${pagedListHolder.pageList}" varStatus="status">
				<tr>
					<td>${bookRequestResults.bookCode}</td>
					<td>${bookRequestResults.bookName}</td>							
					<td>${BookRequestResult.bookRequestOpenDate}</td>
					<td>${BookRequestResult.bookRequestCloseDate}</td>
					<td>
					<select name='waitingBookId' id="waitingBookId" class="defaultselect">
							<option value="">Select...</option>	
							<c:forEach var="waitingBookId" items="${bookRequestResults.bookWaitingList}"> 
										<option value="${waitingBookId.userId}">${waitingBookId.userId}</option> 
							</c:forEach>								  
						</select>
					</td>
					<td>
					<select name='allocatedBookId' id="allocatedBookId" onChange="checkThreshold(this);" class="defaultselect">
							<option value="">Select...</option>	
							<c:forEach var="varBookId" items="${bookRequestResults.bookIdList}"> 
										<option value="${varBookId.bookId}">${varBookId.bookId}</option> 
							</c:forEach>								  
						</select>
					</td>
					<td>${fn:length(bookRequestResults.bookIdList)}<input type="hidden" name="totalAvlBook" id="totalAvlBook" value="${fn:length(bookRequestResults.bookIdList)}"/></td>
					<td>${bookRequestResults.book.threshold} <input type="hidden" name="threshold" id="threshold" value="${bookRequestResults.book.threshold}"/></td>									
					<td><input type="text" readonly="readonly" name="returnDate" class ="returnDate" id="returnDate${status.index}" value="${bookRequestResults.expectedBookReturnDate}"/></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
			</tr>
		</table>
			
			
			<table cellspacing="0" cellpadding="0" class="midsec1">
			<c:choose>
				<c:when test="${pagedListHolder2.pageList==null}">
					<th>:: Currently '${BookRequestResult.bookRequestFor.userId}' Has No Allocated Book ::</th>
				</c:when>
				<c:otherwise>
					<tr>
						<th colspan="6">:: Already Allocated Books::</th>
					</tr>
					<tr>
							<th>Book Allocation Code</th>
							<th>Book Name</th>						
							<th>Book ID</th>
							<th>Issued Date</th>	
							<th>Expected Return Date</th>				
							<th>Status</th>
						</tr>
					<c:forEach var="bookAllocation" items="${pagedListHolder2.pageList}">
						<c:forEach var="bookAllocationDetails" items="${bookAllocation.bookAllocationDetails}">
							<tr>
								<td>${bookAllocation.bookAllocationCode}</td>						
								<td>${bookAllocationDetails.bookName}</td>						
								<td>${bookAllocationDetails.bookId}</td>
								<td>${bookAllocationDetails.bookIssueDate}</td>
								<td>${bookAllocationDetails.bookReturnDate}</td>
								<td>${bookAllocationDetails.status}</td>
							</tr>
						</c:forEach>
					</c:forEach>
					<tr>
						<td colspan="6" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder2}" pagedLink="${pagedLink}"/></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			<input  type="button" value="Back" class="clearbtn" onclick="window.location='bookAllocation.html' ">
			<input type="submit" id="submitButton" class="submitbtn" value="SUBMIT" >
		</div>
	</form:form>
	</c:otherwise>
</c:choose>
</body>
</html>