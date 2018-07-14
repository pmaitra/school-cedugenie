<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/retiredBookListPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Retired Book List" />
<meta name="keywords" content="Retired Book List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Retired Book List</title>

<link href="/sms/css/common/pagination.css" rel="stylesheet" />
<link rel="stylesheet" href="/sms/css/library/retiredBookList.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
</head>	
<body>
<div class="ttlarea">	
		<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Retired Book List
		</h1>
</div>
<form:form method="POST" id="sflcontents" name="sflcontents" action="retiredBookSearch.html" >
	<c:choose>
		<c:when test="${bookPagedListHolder ne null}">
		<table id="" cellspacing="0" cellpadding="0" class="midsec1">
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
					<input class="editbtn" type="submit" name="retiredBookListSearch" value="Search">
				</th>
			</tr>
		</table>
			<table id="brt" cellspacing="0" cellpadding="0" class="midsec1">
				<tr>
					<th colspan="5">:: Book With Retired Copy's ::</th>
				</tr>
				<tr>
					<th>Book Code</th>
					<th>Book Name</th>
					<th>Total No. Of Copies</th>
					<th>No. Of Retired Copies</th>
					<th>Details</th>
				</tr>
				<c:forEach var="viewRetiredBookList" items="${bookPagedListHolder.pageList}">
				<tr>
					<td><c:out value="${viewRetiredBookList.bookCode}"/></td>
					<td><c:out value="${viewRetiredBookList.bookName}"/></td>
					<td><c:out value="${viewRetiredBookList.totalNumberOfBookCopies}"/></td>
					<td><c:out value="${viewRetiredBookList.totalNumberOfBookCopiesRetired}"/></td>
					<td><a href="retiredBookDetails.html?bookCode=<c:out value="${viewRetiredBookList.bookCode}"/>" >DETAILS</a></td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="10" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${bookPagedListHolder}" pagedLink="${pagedLink}"/></td>
				</tr>
			</table>
	</c:when>
		<c:otherwise>
			<div class="btnsarea01" style="visibility: visible;">
					<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
						<span id="infomsg">No retired books found </span>	
					</div>
			</div>		
		</c:otherwise>
	</c:choose>	
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
		<c:if test="${BookRetirement ne null}">
			<input type="button" class="submitbtn" value="Back" onclick="window.location='retirementBookCodeList.html' ">	
		</c:if>
	</div>
</form:form>
</body>
</html>