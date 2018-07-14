<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Edit Book" />
<meta name="keywords" content="Edit Book" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Edit Book</title>

<link rel="stylesheet" href="/sms/css/library/editBook.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/sms/js/library/editBook.js"></script>

<script type="text/javascript" src="/sms/js/common/jquery-1.js"></script>
<link rel="stylesheet" type="text/css" href="/sms/css/common/loggingDetails.css" />
<script type="text/javascript" src="/sms/js/common/loggingDetails.js"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Edit Book
		</h1>
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
<form:form method="POST" action="updateBook.html">
<table id="booktable" cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th>Type :: </th>
		<td>
			<input class="textfield" type="text" name="bookType" id="bookType" readonly="readonly" value="${book.bookType}" />
		</td>
	</tr>
	
	<tr>
		<th>Name<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
			<input class="textfield" type="text" id="bookName" name="bookName" readonly="readonly" value="${book.bookName}" onfocus="getBookName(this.value);"/>	
		</td>
	</tr>
	<tr>
		<th>Code :: </th>
		<td>
			<input class="textfield" type="text" id="bookCode" name="bookCode" readonly="readonly" value="${book.bookCode}" />
		</td>
	</tr>
	<tr>
		<th>Description :: </th>
		<td>
			<textarea class="txtarea" id="description" name="bookDesc" cols="20" readonly="readonly" >${book.bookDesc}</textarea>
		</td>
	</tr>
	<tr>
		<th>Resource Type<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
			<select name="bookMediumName" id="bookMediumName" disabled="disabled" class="defaultselect">
				<option value="audio" <c:if test="${book.bookMedium.bookMediumName eq 'audio'}">selected="selected"</c:if> >Audio</option>
				<option value="video" <c:if test="${book.bookMedium.bookMediumName eq 'video'}">selected="selected"</c:if> >Video</option>
				<option value="printed" <c:if test="${book.bookMedium.bookMediumName eq 'printed'}">selected="selected"</c:if> >Printed</option>
				<option value="ebook" <c:if test="${book.bookMedium.bookMediumName eq 'ebook'}">selected="selected"</c:if> >E-Book</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>ISBN :: </th>
		<td>
			<input class="textfield" type="text" id="bookIsbn" name="bookIsbn" readonly="readonly" value="${book.bookIsbn}" />
		</td>
	</tr>
	<tr>
		<th>Edition<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
			<input class="textfield" type="text" id="bookEdition" name="bookEdition" readonly="readonly" value="${book.bookEdition}" onfocus="getEdition(this.value);"/>
		</td>
	</tr>
	<tr>
		<th>Language<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
			<select name="bookLanguageName" id="bookLanguageName" class="defaultselect" tabindex="1" disabled="disabled">
					<option value="">Select...</option>
				<c:forEach var="bookLanguage" items="${book.bookLanguageList}">
					<option value="${bookLanguage.bookLanguageName}" ${bookLanguage.bookLanguageName eq  book.bookLanguage.bookLanguageName?'selected':''}>${bookLanguage.bookLanguageName}</option>
				</c:forEach>		
			</select>
	
		</td>
	</tr>
</table>
<c:if test="${book.bookType eq 'periodicals'}">
<table id="periodicals" cellspacing="0" cellpadding="0" class="midsec">
		<tr>
			<th>Place :: </th>
			<td>
				<input class="textfield" type="text" id="bookPlace" name="bookPlace" readonly="readonly" value="${book.bookPlace}" />
			</td>
		</tr>
		<tr>
			<th>Periodicity :: </th>
			<td>
				<input class="textfield" type="text" id="bookPeriodicity" name="bookPeriodicity" readonly="readonly" value="${book.bookPeriodicity}" />
			</td>
		</tr>
</table>
</c:if>
<c:if test="${book.bookType eq 'book'}">
<table id="book" cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th>Publisher<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
			<input class="textfield" type="text" id="bookPublisherName" name="bookPublisherName" readonly="readonly" value="${book.bookPublisher.bookPublisherName}" onfocus="getPublisherName(this.value);"/>
		</td>
	</tr>
	<c:forEach var="bookAuthorList" items="${book.bookAuthorList}" varStatus="status">
	<tr>
		<th>Author<img class="required" alt="Required" src="/sms/images/required.gif"> ${status.index+1} :: </th>
		<td>
			<input class="textfield1" type="text" name="authorFullName" id="authorFullName${status.index+1}" readonly="readonly" value="${bookAuthorList.authorFullName}" onfocus="getAuthorName(this.value);"/>
			<img src="/sms/images/minus_icon.png" onclick="deleteBookAuthor(this);">
		</td>
	</tr>
	</c:forEach>
	<tr>
		<th colspan="2">
			<input type="button" class="addbtn" id ="addBookAuthor" disabled="disabled" onclick="addNewAuthor();">
		</th>
	</tr>
</table>
</c:if>
<c:if test="${book.bookType eq 'note'}">
<table id="note" cellspacing="0" cellpadding="0" class="midsec">
	<c:forEach var="noteAuthorList" items="${book.bookAuthorList}" varStatus="status">
	<tr>
		<th>Author ${status.index+1} :: </th>
		<td>
			<input class="textfield1" type="text" name="noteAuthorFullName" id="noteAuthorFullName${status.index+1}" readonly="readonly" value="${noteAuthorList.authorFullName}" />
			<img src="/sms/images/minus_icon.png" onclick="deleteNoteAuthor(this);">
		</td>
	</tr>
	</c:forEach>
	<tr>
		<th colspan="2">
			<input type="button" class="addbtn" id="addNoteAuthor" disabled="disabled" onclick="addNewNoteAuthor();">
		</th>
	</tr>
</table>
</c:if>
<div class="btnsarea01">
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
	<div class="infomsgbox" id="infomsgbox" >
		<span id="infomsg"></span>	
	</div>
	<input type="button" class="editbtn" id="Edit" name="Edit" value="EDIT" onClick="activeForm();"/>
	<button type="submit" class="submitbtn" id="submitForm" disabled="disabled" >SUBMIT</button>
</div>
</form:form>
</body>
</html>