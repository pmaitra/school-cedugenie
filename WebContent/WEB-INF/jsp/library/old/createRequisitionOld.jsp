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

<link rel="stylesheet" href="/sms/css/library/createRequisition.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/sms/js/library/createRequisition.js"></script>
</head>
<body>
<div class="ttlarea">
	<h1>
	<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Book Requisition
	</h1>
</div>
<c:set var="concatAuthor" value="" scope="page" />
<form:form method="POST" id="" name=""  action="addRequisition.html" >
	<table id="booktable" cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<th colspan="5">
				Requisition ID :: ${strRequisitionUpComingId.bookRequisitionCode}
				<input type="hidden" id="name" name="bookRequisitionCode" value="${strRequisitionUpComingId.bookRequisitionCode}"/>
 			</th>
 			<th>
 				<input type="button" class="addbtn" onclick="addTab('booktable')" >
 			</th>
		</tr>
		<tr>			
			<th>Book Name<img class="required" src="/sms/images/required.gif" alt="Required"></th>		
			<th>Author<img class="required" src="/sms/images/required.gif" alt="Required"></th>
			<th>Edition<img class="required" src="/sms/images/required.gif" alt="Required"></th>
			<th>Publisher<img class="required" src="/sms/images/required.gif" alt="Required"></th>		
			<th>Quantity<img class="required" src="/sms/images/required.gif" alt="Required"></th>
			<th></th>
		</tr>
			<tr>
				<td>
					<input type="text" class="textfield" id="bookName0" name="bookName" onfocus="getBookName(this);" value="${bookDetails.bookName}" onblur="getAllBookDetails(this);"/>
				</td>
				<td>
				<c:choose>
					<c:when test="${bookDetails==null}">
					<input type="text" class="textfield" id="bookAuthor0" name="bookAuthor" onfocus="getAuthorName(this);" onblur="submitAction(this);" value=""/>
					<img src="/sms/images/plus_icon.png" onclick="addAuthorName(this);">
				</c:when>	
				<c:otherwise>
					<c:forEach var="author" items="${bookDetails.bookAuthorList}" varStatus = "status">
						<input type="text" class="textfield" id="bookAuthor${status.index}" name="bookAuthor" onfocus="getAuthorName(this);" onblur="submitAction(this);" value="${author.authorFullName}"/>
						<br>
					</c:forEach>
				</c:otherwise>
				</c:choose>
				</td>
				<td>
					<input type="text" class="textfield" id="bookEdition0" onfocus="getEdition(this);" name="bookEdition" value="${bookDetails.bookEdition}"/>
				</td>
				<td>
					<input type="text" class="textfield" id="bookPublisher0" onfocus="getPublisherName(this);" name="bookPublisher" value="${bookDetails.bookPublisher.bookPublisherName}"/>
				</td>
				<td>
					<input type="text" class="textfield1" id="numberOfBooksRequisitioned0" value="" name="numberOfBooksRequisitioned" id="numberOfBooksRequisitioned0" onblur="calculateTotal(this);" />
				</td>
				<td>
				<c:choose>
					<c:when test="${bookDetails==null}">
						<input type="hidden" id="hiddenVal" name="hiddenval" value=" "/>
					</c:when>	
				<c:otherwise>
					`	<c:forEach var="author" items="${bookDetails.bookAuthorList}" varStatus = "status">
							<c:choose>
								<c:when test="${concatAuthor != ''}">
									<c:set var="concatAuthor" value="${concatAuthor}~${author.authorFullName}" scope="page" />	
								</c:when>	
								<c:otherwise>
									<c:set var="concatAuthor" value="${author.authorFullName}" scope="page" />	
								</c:otherwise>
							</c:choose>
						</c:forEach>
					<input type="hidden" id="hiddenVal" name="hiddenval" value="${concatAuthor}"/>
				</c:otherwise>
				</c:choose>
					
					<img src='/sms/images/minus_icon.png' onclick='deleteRow1(this);'>
				</td>
			</tr>
		</table>
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
		</div>
		<input type="submit" class="submitbtn" onclick="return validateBookRequisition();" value="SUBMIT">
	</form:form>
</body>
</html>