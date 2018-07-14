<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
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

<link rel="stylesheet" href="/sms/css/library/addBookAfterReceive.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/sms/js/library/addBookAfterReceive.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Add Book To Library 
	</h1>
</div>
<form:form method="POST" id="addNewBook" name="addNewBook" action="addNewBookToCat.html">
	<input type="hidden" name="bookObjectId" id="bookObjectId" value="${book.bookObjectId}"/>
	<table id="booktable" cellspacing="0" cellpadding="0" class="midsec">
		<tr>
			<th>Resource :: </th>
			<td>
				Book
				<input type="hidden" name="bookType" id="bookType" value="book"/>
			</td>
		</tr>
		<tr>
			<th>Name :: </th>
			<td>
				${book.bookName}
				<input type="hidden" id="bookName" name="bookName" value="${book.bookName}" />	
			</td>
		</tr>
		<tr>
			<th>Code :: </th>
			<td>
				<c:choose>
					<c:when test="${(book.bookCode ne null) && (not empty book.bookCode)}">
						<input type="hidden" id="bookCode" name="bookCode" value="${book.bookCode}" />
						${book.bookCode}
					</c:when>
					<c:otherwise>
						<input type="text" class="textfield" id="bookCode" name="bookCode" />
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th>Description :: </th>
			<td>
				<textarea id="description" name="bookDesc" class="txtarea" >${book.bookDesc}</textarea>
			</td>
		</tr>
		<tr>
			<th>Resource Type :: </th>
			<td>
				<select name="bookMediumName" id="bookMediumName" class="defaultselect">
						<option value="">Select...</option>
						<option value="audio" ${book.bookMedium.bookMediumName eq 'audio'?'selected':value}>Audio</option>
						<option value="video" ${book.bookMedium.bookMediumName eq 'video'?'selected':value}>Video</option>
						<option value="printed" ${book.bookMedium.bookMediumName eq 'printed'?'selected':value}>Printed</option>
						<option value="ebook" ${book.bookMedium.bookMediumName eq 'ebook'?'selected':value}>E-Book</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>ISBN :: </th>
			<td>
				<input type="text" class="textfield" id="bookIsbn" name="bookIsbn" value = "${book.bookIsbn}"/>
			</td>
		</tr>
		<tr>
			<th>Edition :: </th>
			<td>
				<input type="hidden" id="bookEdition" name="bookEdition" value="${book.bookEdition}" />
				${book.bookEdition}
			</td>
		</tr>
		<tr>
			<th>Language :: </th>
			<td>
				<select name="bookLanguageName" id="bookLanguageName" class="defaultselect" tabindex="1">
					<option value="">Select...</option>
				<c:forEach var="bookLan" items="${bookLanguageList}">
					<option value="${bookLan.bookLanguageName}" ${bookLan.bookLanguageName eq book.bookLanguage.bookLanguageName?'selected':value}>${bookLan.bookLanguageName}</option>
				</c:forEach>		
			</select>
			</td>
		</tr>
		<tr>
			<th>No. Of Copies :: </th>
			<td>
				${book.totalNumberOfBookCopies}
				<input type="hidden" id="totalNumberOfBookCopies" name="totalNumberOfBookCopies" value="${book.totalNumberOfBookCopies}" />
			</td>
		</tr>
		<tr>
			<th> Price :: </th>
			<td>
				${book.price}
				<input type="hidden" id="price" name="price" value="${book.price}" />
			</td>
		</tr>
	</table>
	<table cellspacing="0" cellpadding="0" class="midsec">
		<tr>
			<th>Author :: </th>
			<td>
				<c:forEach var="author" items="${book.bookAuthorList}">
					${author.authorFullName}
					<input type="hidden" name="authorFullName" id="authorFullName" value="${author.authorFullName}" /><br>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>Publisher :: </th>
			<td>
				${book.bookPublisher.bookPublisherName}
				<input type="hidden" id="bookPublisherName" name="bookPublisherName" value="${book.bookPublisher.bookPublisherName}" />
			</td>
		</tr>
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
	</div>	
	<button type="submit" id="submitForm" class="submitbtn" >SUBMIT</button>
</form:form>

</body>
</html>