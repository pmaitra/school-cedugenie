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
<meta name="description" content="Page to Add Book" />
<meta name="keywords" content="Add Book" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Add Book</title>

<link rel="stylesheet" href="/sms/css/library/addBook.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/sms/js/library/addBook.js"></script>
<title>Add books to library</title>
</head>
<body>
<div class="ttlarea">
		<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Add Book
		<div style="float: right;">
			<div style="float: left; position: relative;">
			<a class="bookDetails" href="downloadBookDetailsExcel.html">
				<button type="button" style="width: 100%;" class="editbtn">Download Excel Sheet</button>
			</a>
			</div>&emsp;			
			<div style="float: right; position: relative;margin-bottom: 1%;">
				<form:form id="safcontents" name="safcontents" method="POST" action="uploadBookDetailsExcel.html" enctype="multipart/form-data">
<!-- 					<input class="textfield" type="file" name="imageFile" id="attachment"/> -->
					<span id="FileUpload">
					    <input type="file" size="24"  name="imageFile" id="attachment" onchange="document.getElementById('FileField').value = document.getElementById('attachment').value;" />
					    <span id="BrowserVisible"><input type="text" id="FileField" /></span>
					</span>
					<input type="submit" class="editbtn" value="Submit" onclick="if(document.getElementById('attachment').value=='')return false; ;">
				</form:form>
				</div>
		</div>
		</h1>
</div>
	<c:if test="${uploadErrorMessage ne null }">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">${uploadErrorMessage}</span>	
			</div>
	</c:if>
	<c:if test="${uploadSuccessMessage ne null }">
			<div class="successbox" id="successbox" style="visibility: visible;">
				<span id="successmsg">${uploadSuccessMessage}</span>	
			</div>
	</c:if>
	<c:choose>
		<c:when test="${Message == null}">
		</c:when>
		<c:otherwise>
			<c:if test="${Message != null}">
				<div class="btnsarea01">
					<c:if test="${Message eq 'success'}">
						<div class="successbox" id="successbox" style="visibility:visible;">
							<span id="successmsg">Data Successfully Inserted</span>
						</div>
					</c:if>
					<c:if test="${Message eq 'fail'}">
						<div class="errorbox" id="errorbox" style="visibility:visible;" >
							<span id="errormsg">Problem Occur While Saving</span>	
						</div>
					</c:if>
				</div>			
			</c:if>
		</c:otherwise>
	</c:choose>
	

<form:form method="POST" id="addNewBook" name="addNewBook" action="addNewBook.html">
<table id="booktable" cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th>Book/Periodicals<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
			<select name="bookType" id="bookType" onchange="javascript: bp(this.value);" class="defaultselect" tabindex="1">
				<option value="">Select...</option>
				<option value="book">Book</option>
				<option value="periodicals">Periodicals</option>
				<option value="note">Note</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>Name<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
			<input class="textfield" type="text" id="bookName" name="bookName" value="${BookStatus.bookRequisitionDetails.bookName}" /> <!--onblur="getCodeForBook(this.value);" --> 
		</td>
	</tr>
	<tr>
		<th>Code<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
			<input class="textfield" type="text" id="bookCode" name="bookCode" onblur="checkAvailability(this.value);" disabled="disabled"/>
		</td>
	</tr>
	<tr>
		<th>Description :: </th>
		<td>
			<textarea id="description" class="txtarea" name="bookDesc" cols="20"  ></textarea>
		</td>
	</tr>
	<tr>
		<th>Resource Type<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
			<select name="bookMediumName" id="bookMediumName" class="defaultselect" tabindex="1">
					<option value="">Select...</option>
					<option value="audio">Audio</option>
					<option value="video">Video</option>
					<option value="printed">Printed</option>
					<option value="ebook">E-Book</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>ISBN :: </th>
		<td>
			<input class="textfield" type="text" id="bookIsbn" name="bookIsbn" />
		</td>
	</tr>
	<tr>
		<th>Edition<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
			<input class="textfield" type="text" id="bookEdition" name="bookEdition" value="${BookStatus.bookRequisitionDetails.bookEdition}"/>
		</td>
	</tr>
	<tr>
		<th>Language<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
		<select name="bookLanguageName" id="bookLanguageName" class="defaultselect" tabindex="1">
					<option value="">Select...</option>
				<c:forEach var="bookLanguage" items="${bookLanguageList}">
					<option value="${bookLanguage.bookLanguageName}">${bookLanguage.bookLanguageName}</option>
				</c:forEach>		
			</select>
		</td>
	</tr>
	<tr>
		<th>No. Of Copies<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
			<input class="textfield" type="text" id="totalNumberOfBookCopies" name="totalNumberOfBookCopies" value="${BookStatus.bookRequisitionDetails.numberOfBooksReceived}"/>
		</td>
	</tr>
	<tr>
		<th>Operational Status<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
			<select name="operationalStatus" id="operationalStatus" class="defaultselect" tabindex="1">
					<option value="">Select...</option>
				<c:forEach var="oparational" items="${BookStatus.bookOperationalStatusList}">
					<option value="<c:out value="${oparational.bookOperationalStatusCode}"/>"><c:out value="${oparational.bookOperationalStatusDesc}"/></option>
				</c:forEach>		
			</select>
		</td>
	</tr>
	<tr>
		<th>LifeCycle Status<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
			<select name="lifecycleStatus" id="lifecycleStatus" class="defaultselect" tabindex="1">
					<option value="">Select...</option>
				<c:forEach var="lifecycle" items="${BookStatus.bookLifeCycleStatusList}">
					<option value="<c:out value="${lifecycle.bookLifeCycleStatusCode}"/>"><c:out value="${lifecycle.bookLifeCycleStatusDesc}"/></option>
				</c:forEach>														
			</select>
		</td>
	</tr>
	<tr>
		<th>Price :: </th>
		  <td>
			<input class="textfield" type="text" id="price" name="price" />
		  </td>
	</tr>
</table>

<table id="periodicals" cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th>Place :: </th>
		<td>
			<input class="textfield" type="text" id="bookPlace" name="bookPlace" />
		</td>
	</tr>
	<tr>
		<th>Periodicity :: </th>
		<td>
			<input class="textfield" type="text" id="bookPeriodicity" name="bookPeriodicity" />
		</td>
	</tr>
	<tr>
		<th>Publisher<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
			<input class="textfield" type="text" id="bookPeriodicityPublisher" name="bookPeriodicityPublisher" />
		</td>
	</tr>
</table>

<table id="book" cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th>Publisher<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
			<input class="textfield" type="text" id="bookPublisherName" name="bookPublisherName" value="${BookStatus.bookRequisitionDetails.bookPublisher}" onfocus="getPublisherName(this.value);"/>
		</td>
	</tr>
	<tr>
		<th>Author<img class="required" alt="Required" src="/sms/images/required.gif"> :: </th>
		<td>
			<input class="textfield1" type="text" name="authorFullName" value="${BookStatus.bookRequisitionDetails.bookAuthor}" onfocus="getAuthorName(this.value);"/>
			<img src="/sms/images/minus_icon.png" onclick="deleteThisRow(this);">
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" class="addbtn" onclick="new_link();">
		</td>
	</tr>
</table>
<table id="note" cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th>Author :: </th>
		<td>
			<input class="textfield1" type="text" name="noteAuthorFullName" />
			<img src="/sms/images/minus_icon.png" onclick="deleteThisRow1(this);">
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" class="addbtn" onclick="new_link1();">
		</td>
	</tr>
</table>

<div class="btnsarea01">
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
	<input type="submit" value="SUBMIT" class="submitbtn" >
	<c:if test="${BookStatus.bookRequisitionDetails.bookName != null}">
		<input type="button" value="BACK" class="clearbtn" onclick="window.location='viewRequisition.html' ">	
	</c:if>	
</div>

</form:form>

</body>
</html>