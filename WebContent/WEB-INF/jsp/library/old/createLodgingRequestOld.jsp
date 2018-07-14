<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/lodgingRequestPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Add Book To Library" />
<meta name="keywords" content="Add Book To Library" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Add Book To Library</title>

<link rel="stylesheet" href="/sms/css/library/createLodgingRequest.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<link href="/sms/css/common/pagination.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.js"></script>
<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/common/validateSearch.js"></script>
<script type="text/javascript" src="/sms/js/library/createLodgingRequest.js"></script>

</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;List Books
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>
<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<input type="checkbox" onclick="ShowAll(this)" />
	<label for="Type">All</label><br>
	<input type="checkbox" class="listShowHide" value="Book Code" onclick="ShowHideField('Book Code', 'clr', this)" />
	<label for="Book Code">Book Code</label><br>
	<input type="checkbox" class="listShowHide" value="Book Name" onclick="ShowHideField('Book Name', 'clr', this)" checked="checked" />
	<label for="Book Name">Book Name</label><br>
	<input type="checkbox" class="listShowHide" value="Author" onclick="ShowHideField('Author', 'clr', this)" checked="checked" />
	<label for="Author">Author</label><br>
	<input type="checkbox" class="listShowHide" value="Publisher" onclick="ShowHideField('Publisher', 'clr', this)" />
	<label for="Publisher">Publisher</label><br>
	<input type="checkbox" class="listShowHide" value="Edition" onclick="ShowHideField('Edition', 'clr', this)" />
	<label for="Edition">Edition</label><br>
</div>

<c:choose>
	<c:when test="${pagedListHolder==null}">
		<div class="btnsarea01" style="visibility: visible;">
			<div class="errorbox" id="errorbox"  style="visibility: visible;">
				<span id="errormsg">No Books Found</span>	
			</div>
		</div>
	</c:when>
<c:otherwise>
	<form:form method="POST" id="sflcontents" name="createLodgingRequest">
		<c:if test="${maxNoOfBooksPerRequest!=null}">
			<input type="hidden" id="maxNoOfBooksPerRequest" name="maxNoOfBooksPerRequest" value="${maxNoOfBooksPerRequest}" readonly="readonly">
		</c:if>
		<table cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th colspan="3">:: Select Book ::</th>
			</tr>				
			<tr>
				<td>
					<input type="text" class="textfield2" name="data" id="data" value="Search" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='Search Lodging Request';}"/>
				</td>
				<td>
					<select name="query" id="query" class="defaultselect">
						<option value="">Please Select...</option>
						<option value="BookCode">Book Code</option>
						<option value="BookName">Book Name</option>
						<option value="Author">Author</option>
						<option value="Publisher">Publisher</option>
						<option value="Edition">Edition</option>
						<option value="All">All</option>
					</select>
				</td>
				<td>
					<input type="submit" class="editbtn" name="SearchLodgingRequest" value="Search" onclick="return onSearchLodgingRequest();">
				</td>
			</tr>
		</table>
		<table id="clr" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Select<img class="required" src="/sms/images/required.gif" alt="Required"></th>
				<th>Book Code</th>
				<th>Book Name</th>
				<th>Author</th>
				<th>Publisher</th>
				<th>Edition</th>
			</tr>
			<c:forEach var="lodgingRequest" items="${pagedListHolder.pageList}">
				<tr>
					<td>
						<input type="checkbox" name="bookRequestBookCode" value="${lodgingRequest.bookCode}" class="checkClass" onchange="go(this);">
					</td>
					<td>
						<c:out value="${lodgingRequest.bookCode}"/>
					</td>
					<td>
						<c:out value="${lodgingRequest.bookName}"/>
					</td>
					<td>
						<c:forEach var="bookAuthor" items="${lodgingRequest.bookAuthorList}">
							<c:out value="${bookAuthor.authorFullName}"/> ; 
						</c:forEach>
					</td>
					<td>
						<c:out value="${lodgingRequest.bookPublisher.bookPublisherName}"/>
					</td>
					<td>
						<c:out value="${lodgingRequest.bookEdition}"/>
					</td>					
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
			</tr>
		</table>
	  	<table cellspacing="0" cellpadding="0" class="midsec">
	  		<tr>
	  			<th>Selected Books :: </th>
	  			 <td >
	  			 	<ul id="names"></ul>
	  			 </td> 
	  		</tr>
		  	<tr>
		  		<th>Book Request ID: </th>
		  		<td>
		  			${lastBookRequestId}
		  			<input type="hidden" align="left" name="bookRequestCode" readonly="readonly" value="${lastBookRequestId}" />
		  		</td>
		  	</tr>
		  	<tr>
		  		<th>User Name :: </th>
		  		<td>
		  			${sessionScope.sessionObject.userName}(${sessionScope.sessionObject.userId})
		  			<input type="hidden" class="textfield" name="userId" id ="userId" value="${sessionScope.sessionObject.userId}"/>
		  		</td>
		  	</tr>
	  	</table>
  		<div class="btnsarea01">
			<div class="errorbox" id="errorbox" >
				<span id="errormsg"></span>	
			</div>
			<div class="infomsgbox" id="infomsgbox" >
				<span id="infomsg"></span>	
			</div>
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			<input type="submit" value="SUBMIT" id="submitForm" class="submitbtn" >
		</div>
	</form:form>
</c:otherwise>
</c:choose>
</body>
</html>