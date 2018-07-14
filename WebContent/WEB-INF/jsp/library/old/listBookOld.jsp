<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listBookPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to List Books" />
<meta name="keywords" content="List Books" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>List Books</title>

<link rel="stylesheet" href="/sms/css/library/listBook.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<link href="/sms/css/common/pagination.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/radio.js"></script>
<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/library/listBook.js"></script>
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
        <input type="checkbox" class="listShowHide" value="Type" onclick="ShowHideField('Type', 'subMar', this)" checked="checked" />
		<label for="Type">Type</label><br>
		<input type="checkbox" class="listShowHide" value="Code" onclick="ShowHideField('Code', 'subMar', this)" />
		<label for="Code">Code</label><br>
		<input type="checkbox" class="listShowHide" value="Name" onclick="ShowHideField('Name', 'subMar', this)" checked="checked" />
		<label for="Name">Name</label><br>
		<input type="checkbox" class="listShowHide" value="Edition" onclick="ShowHideField('Edition', 'subMar', this)" checked="checked"/>
		<label for="Edition">Edition</label><br>
		<input type="checkbox" class="listShowHide" value="Medium" onclick="ShowHideField('Medium', 'subMar', this)" />
		<label for="Medium">Medium</label><br>
		<input type="checkbox" class="listShowHide" value="ISBN" onclick="ShowHideField('ISBN', 'subMar', this)" />
		<label for="ISBN">ISBN</label><br>
		<input type="checkbox" class="listShowHide" value="Author" onclick="ShowHideField('Author', 'subMar', this)" checked="checked"/>
		<label for="Author">Author</label><br>
		<input type="checkbox" class="listShowHide" value="Publisher" onclick="ShowHideField('Publisher', 'subMar', this)" checked="checked"/>
		<label for="Publisher">Publisher</label><br>
		<input type="checkbox" class="listShowHide" value="Language" onclick="ShowHideField('Language', 'subMar', this)" />
		<label for="Language">Language</label><br>
</div>
<c:choose>
	<c:when test="${pagedListHolder eq null}">
		<div class="btnsarea01" style="visibility: visible;">
			<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
				<span id="infomsgbox">Books Not Available</span>	
			</div>
		</div>
	</c:when>
<c:otherwise>

<form method="post" action="editBook.html" >
<table id="" cellspacing="0" cellpadding="0" class="midsec1">
	<tr>
		<th colspan="2">
			<select name="query" id="query" class="defaultselect" tabindex="1">
				<option value="" >Please Select...</option>
				<option value="Type">Type</option>
				<option value="Code">Code</option>
				<option value="Name">Name</option>
				<option value="Edition">Edition</option>
				<option value="Medium">Medium</option>
				<option value="ISBN">ISBN</option>
				<option value="Author">Author</option>
				<option value="Publisher">Publisher</option>
				<option value="Language">Language</option>
			</select>
		</th>
		<th colspan="4">
			<input type="text" class="textfield" name="data" id="data" value="Search"   onfocus="this.value='';this.style.color='orange';" onblur="javascript: if(this.value==''){this.value='Search';this.style.color='orange';}else{this.style.color='orange';}"/>
		</th>
		<th>
			<input class="editbtn" type="submit" name="bookListSearch" value="Search">
		</th>
	</tr>
</table>

<table id="subMar" cellspacing="0" cellpadding="0" class="midsec1">
	<tr>
		<th>Select</th>
		<th>Type</th>
		<th>Code</th>
		<th>Name</th>
		<th>Edition</th>
		<th>Medium</th>
		<th>ISBN</th>
		<th>Author</th>
		<th>Publisher</th>
		<th>Language</th>
	</tr>
	<c:forEach var="bookList" items="${pagedListHolder.pageList}">
	<tr>			
		<td><input type="radio" name="bookCode" value="${bookList.bookCode}"/></td>
		<td><c:out value="${bookList.bookType}"></c:out></td>
		<td><c:out value="${bookList.bookCode}"></c:out></td>
		<td><c:out value="${bookList.bookName}"></c:out></td>
		<td><c:out value="${bookList.bookEdition}"></c:out></td>
		<td><c:out value="${bookList.bookMedium.bookMediumName}"></c:out></td>
		<td><c:out value="${bookList.bookIsbn}"></c:out></td>
		<td>
			<c:if test="${bookList.bookAuthorList ne null}">
				<c:forEach var="bookAuthorList" items="${bookList.bookAuthorList}">
					<c:out value="${bookAuthorList.authorFullName}"></c:out> ; 
				</c:forEach>
			</c:if>
		</td>
		<td><c:out value="${bookList.bookPublisher.bookPublisherName}"></c:out></td>
		<td><c:out value="${bookList.bookLanguage.bookLanguageName}"></c:out></td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="10" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
	</tr>
</table>
<div class="btnsarea01">
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
	<input type="submit" name="details" value="DETAILS" class="submitbtn" onclick="return valradio('bookCode','warningbox','warningmsg');">	
</div>
</form>
</c:otherwise>
</c:choose>
</body>
</html>