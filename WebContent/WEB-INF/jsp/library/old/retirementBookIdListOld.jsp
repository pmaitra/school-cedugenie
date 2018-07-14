<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/retirementBookIdListPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
	 <c:param name="bkCode" value="${ViewBookIdList.bookCode}"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Individual Book List" />
<meta name="keywords" content="Individual Book List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Individual Book List</title>

<link rel="stylesheet" href="/sms/css/library/retirementBookIdList.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<link href="/sms/css/common/pagination.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/library/retirementBookIdList.js"></script>

<script type="text/javascript" src="/sms/js/common/jquery-1.js"></script>
<link rel="stylesheet" type="text/css" href="/sms/css/common/loggingDetails.css" />
<script type="text/javascript" src="/sms/js/common/loggingDetails.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Individual Book List
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>

<div class="pollSlider">
	<div id="pollSlider-button" >
		<button id="dataFetch">Log Details</button>
	</div>
	<table cellspacing="0" cellpadding="0" class="midsec1" id = "loggingDetails">
		<tr>
			<th >Updated By</th>
			<th >Updated ON</th>
			<th >Description</th>
		</tr>
	</table>
</div>

<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<input type="checkbox" onclick="ShowAll(this)" />
		<label for="Type">All</label><br>
        <input type="checkbox" class="listShowHide" value="Book Code" onclick="ShowHideField('Book Code', 'rbidl', this)" checked="checked" />
		<label for="Book Code">Book Code</label><br>
		<input type="checkbox" class="listShowHide" value="Book Name" onclick="ShowHideField('Book Name', 'rbidl', this)" checked="checked" />
		<label for="Book Name">Book Name</label><br>
		<input type="checkbox" class="listShowHide" value="Author" onclick="ShowHideField('Author', 'rbidl', this)" checked="checked" />
		<label for="Author">Author</label><br>
		<input type="checkbox" class="listShowHide" value="Publisher" onclick="ShowHideField('Publisher', 'rbidl', this)" checked="checked"  />
		<label for="Publisher">Publisher</label><br>
		<input type="checkbox" class="listShowHide" value="Edition" onclick="ShowHideField('Edition', 'rbidl', this)" checked="checked" />
		<label for="Edition">Edition</label><br>
		<input type="checkbox" class="listShowHide" value="ISBN" onclick="ShowHideField('ISBN', 'rbidl', this)" checked="checked" />
		<label for="ISBN">ISBN</label><br>
		</div>
<c:choose>
	<c:when test="${ViewBookIdList==null}">
		<div class="btnsarea01" style="visibility: visible;">
			<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
				<span id="infomsg">Books not available</span>
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<form:form method="POST" id="sflcontents" name="sflcontents" action="retiredBookList.html" >
		

		
		<table id="rbidl" cellspacing="0" cellpadding="0" class="midsec1">	
			<tr>
				<th colspan="6">:: Book Details ::</th>
			</tr>
			<tr>
				<th>Book Code</th>
				<th>Book Name</th>
				<th>Author</th>
				<th>Publisher</th>
				<th>Edition</th>
				<th>ISBN</th>
			</tr>
			<tr>
				<td><c:out value="${ViewBookIdList.bookCode}"/><input readonly="readonly" type="hidden" id ="bookCode" name="bookCode"  value="${ViewBookIdList.bookCode}" /></td>
				<td><c:out value="${ViewBookIdList.bookName}"/><input readonly="readonly" type="hidden" id ="bookName" name="bookName"  value="${ViewBookIdList.bookName}" /></td>
				<td>
					<c:forEach var="bookAuthor" items="${ViewBookIdList.bookAuthorList}">
						<c:out value="${bookAuthor.authorFullName}"/> ; 
					</c:forEach>
				</td>
				<td><c:out value="${ViewBookIdList.bookPublisher.bookPublisherName}"/></td>
				<td><c:out value="${ViewBookIdList.bookEdition}"/></td>
				<td><c:out value="${ViewBookIdList.bookIsbn}"/></td>
			</tr>
		</table>
		<c:choose>
			<c:when test="${pagedListHolder==null}">
				<div class="btnsarea01" style="visibility: visible;">
					<div class="errorbox" id="errorbox" >
						<span id="errormsg">No Copy of This Book Exist</span>
					</div>
				</div>
			</c:when>
			<c:otherwise>
					<table id="" cellspacing="0" cellpadding="0" class="midsec1">
						<tr>
							<th colspan="2">
								<select name="query" id="query" class="defaultselect" tabindex="1">
									<option value="" >Please Select...</option>
									<option value="BookID">Book ID</option>
									<option value="EntryDate">EntryDate</option>
								</select>
							</th>
							<th colspan="4">
								<input type="text" class="textfield1" name="data" id="data" value="Search"   onfocus="this.value='';this.style.color='orange';" onblur="javascript: if(this.value==''){this.value='Search';this.style.color='orange';}else{this.style.color='orange';}"/>
							</th>
							<th>
								<input class="editbtn" type="submit" name="bookIDRetireSearch" value="Search">
							</th>
						</tr>
					</table>
				<table cellspacing="0" cellpadding="0" class="midsec1">
					<tr>
						<th colspan="4">::Select Book ID(s) To Retire::</th>
					</tr>
					<tr>
						<th>Select<img class="required" alt="Required" src="/sms/images/required.gif"></th>
						<th>Book ID</th>
						<th>Entry Date</th>
						<th>price</th>
					</tr>
					<c:forEach var="bookIdentifier" items="${pagedListHolder.pageList}">
					<tr>
						<td><input type="checkbox" class="bookId" name="bookIdentity" value="${bookIdentifier.bookId}"></td>
						<td><c:out value="${bookIdentifier.bookId}"/></td>
						<td><c:out value="${bookIdentifier.newBookEntryDate}"/></td>
						<td>
						<c:choose>
							<c:when test="${bookIdentifier.price ne null}">
								${bookIdentifier.price}
							</c:when>
							<c:otherwise>
								<input class="textfield" type="text" id="bookId" name="${bookIdentifier.bookId}"  value="" />
							</c:otherwise>
						</c:choose>	
					</tr>
					</c:forEach>
					<tr>
						<td colspan="4" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
					</tr>
				</table>
			</c:otherwise>
		</c:choose>
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			
			<input type="submit" id="Retire" class="submitbtn" value="retire">
			<input type="button" id="Back" class="clearbtn" value="back" onclick="window.location='retirementBookCodeList.html' ">
		</div>
	</form:form>
	</c:otherwise>
</c:choose>
</body>
</html>