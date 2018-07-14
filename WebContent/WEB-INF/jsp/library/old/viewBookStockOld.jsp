<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listBookStockPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to View Book Stock" />
<meta name="keywords" content="View Book Stock" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>View Book Stock</title>


<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<link href="/sms/css/common/pagination.css" rel="stylesheet" />
<link rel="stylesheet" href="/sms/css/library/viewBookStock.css" />
<link rel="stylesheet" href='/sms/css/common/popup.css'/>
<link rel="stylesheet" type="text/css" href="/sms/css/common/calendar/jquery-ui-1.10.4.custom.min.css" />


<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/common/validateSearch.js"></script>
<script src='/sms/js/common/calendar/jquery-ui.custom.min.js'></script>
<script src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/library/viewBookStock.js"></script> 


</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;View Book Stock
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>
<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">	
		<input type="checkbox" onclick="ShowAll(this)" />
		<label for="Type">All</label><p>
        <input type="checkbox" class="listShowHide" value="Book Code" onclick="ShowHideField('Book Code', 'sfltable', this)"  checked="checked"/>
		<label for="Book Code">Book Code</label><p>
		<input type="checkbox" class="listShowHide" value="Book Name" onclick="ShowHideField('Book Name', 'sfltable', this)" checked="checked" />
		<label for="Book Name">Book Name</label><p>
		<input type="checkbox" class="listShowHide" value="Available" onclick="ShowHideField('Available', 'sfltable', this)" checked="checked" />
		<label for="Available">Available</label><p>
		<input type="checkbox" class="listShowHide" value="Lended" onclick="ShowHideField('Lended', 'sfltable', this)" checked="checked" />
		<label for="Lended">Lended</label><p>
		<input type="checkbox" class="listShowHide" value="Reserved" onclick="ShowHideField('Reserved', 'sfltable', this)" checked="checked" />
		<label for="Reserved">Reserved</label><p>
		<input type="checkbox" class="listShowHide" value="Total Stock" onclick="ShowHideField('Total Stock', 'sfltable', this)" checked="checked" />
		<label for="Total Stock">Total Stock</label><p>
		<input type="checkbox" class="listShowHide" value="Book Profile" onclick="ShowHideField('Book Profile', 'sfltable', this)" />
		<label for="Book Profile">Book Profile</label><p>
		<input type="checkbox" class="listShowHide" value="Create Requisition" onclick="ShowHideField('Create Requisition', 'sfltable', this)" />
		<label for="Create Requisition">Create Requisition</label><p>
</div>
<c:choose>
	<c:when test="${pagedListHolder==null}">
		<div id="errorbox" style="visibility:visible;">
			<span class="errormsg">Book Stock Not Available</span>
		</div>
	</c:when>
	<c:otherwise>
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
		</div>
		<div id="libraryRatingTransparancyDialog" title="Rating Transparancy">
				<table id="libraryRatingTransparancyDialogTable" cellspacing="0" cellpadding="0" class="midsec1">
					<thead></thead>	
					<tbody></tbody>
				</table>
				 <a id="libraryRatingTransparancyDialogClose"><button type=button id="close" class="editbtn" >Close</button></a>
		</div>
		<div id="viewBookStock" >
		<form:form method="POST" id="sflcontents" name="sflcontents" action="searchForViewBookStock.html" >
			<table id="" cellspacing="0" cellpadding="0" class="midsec">
				<tr>
					<th colspan="2">
						<select name="query" id="query" class="defaultselect" tabindex="1">
							<option value="" >Please Select...</option>
							<option value="BookCode">Book Code</option>
							<option value="BookName">Book Name</option>
						</select>
					</th>
					<th colspan="4">
						<input type="text" class="textfield" name="data" id="data" value="Search"   onfocus="this.value='';this.style.color='orange';" onblur="javascript: if(this.value==''){this.value='Search';this.style.color='orange';}else{this.style.color='orange';}"/>
					</th>
					<th>
						<input class="editbtn" type="submit" name="SearchLodgingRequest" value="SEARCH">
					</th>
				</tr>
			</table>
			
			<table id="sfltable" cellspacing="0" cellpadding="0" class="midsec1">
				<tr>
					<th>Book Code</th>
					<th>Book Name</th>
					<th>Available</th>
					<th>Lended</th>
					<th>Reserved</th>
					<th>Total Stock</th>
					<th>Rating Transparancy</th>
					<th>Book Profile</th>
					<th>Create Requisition</th>
				</tr>
				<c:forEach var="book" items="${pagedListHolder.pageList}">
					<tr>
						<td>
							<c:out value="${book.bookCode}"/>
						</td>
						<td>
							<c:out value="${book.bookName}"/>
						</td>
						<td>
							<c:out value="${book.totalNumberOfBookCopiesAvailable}"/>
						</td>
						<td>
							<c:out value="${book.totalNumberOfBookCopiesLended}"/>
						</td>
						<td>
							<c:out value="${book.totalNumberOfBookCopiesReserved}"/>
						</td>
						<td>
							<c:out value="${book.totalNumberOfBookCopies}"/>
						</td>
						<td>
							<a href="#" id="${book.averageBookRating}~${book.userBookRating}~${book.bookCode}~${book.bookName}" class="alertLink">Transparancy</a>
						</td>
						
						<td>
							<a class="profile" href="viewLendingHistory.html?bookCode=${book.bookCode}">Profile</a>
						</td>
						<td>
							<a class="profile" href="createRequisition.html?bookCode=${book.bookCode}">Create Requisition</a>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="9" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
				</tr>
			</table>
		</form:form>
		</div>	
	</c:otherwise>
</c:choose>
</body>
</html>