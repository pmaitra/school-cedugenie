<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Retired Book Details" />
<meta name="keywords" content="Retired Book Details" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Retired Book Details</title>

<link rel="stylesheet" href="/sms/css/library/retiredBookDetails.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;List Books
		</h1>
</div>
	<table cellspacing="0" cellpadding="0" class="midsec1">
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
			<td><c:out value="${ViewRetiredBookDetails.bookCode}"/></td>
			<td><c:out value="${ViewRetiredBookDetails.bookName}"/></td>
			<td>
			<c:forEach var="bookAuthor" items="${ViewRetiredBookDetails.bookAuthorList}">
				<c:out value="${bookAuthor.authorFullName}"/> ; 
			</c:forEach>
			</td>
			<td><c:out value="${ViewRetiredBookDetails.bookPublisher.bookPublisherName}"/></td>
			<td><c:out value="${ViewRetiredBookDetails.bookEdition}"/></td>
			<td><c:out value="${ViewRetiredBookDetails.bookIsbn}"/></td>
		</tr>
	</table>
	<table cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<th colspan="4">:: Book's Existing Period ::</th>
		</tr>
		<tr>
			<th>Book Id</th>
			<th>Entry Date</th>
			<th>Retirement Date</th>
			<th>price</th>
		</tr>
		<c:forEach var="bookIdentifier" items="${ViewRetiredBookDetails.bookIdList}">
		<tr>
			<td><c:out value="${bookIdentifier.bookId}"/></td>
			<td><c:out value="${bookIdentifier.newBookEntryDate}"/></td>
			<td><c:out value="${bookIdentifier.bookRetirementDate}"/></td>
			<td><c:out value="${bookIdentifier.price ne null ? bookIdentifier.price: 'N/A' }"/></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>