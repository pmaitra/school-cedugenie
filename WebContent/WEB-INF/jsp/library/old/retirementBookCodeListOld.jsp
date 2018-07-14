<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/retirementBookCodeListPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Retirement Book List" />
<meta name="keywords" content="Retirement Book List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Retirement Book List</title>

<link rel="stylesheet" href="/sms/css/library/retirementBookCodeList.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<link href="/sms/css/common/pagination.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/radio.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/library/retirementBookCodeList.js"></script>
<script type="text/javascript" src="/sms/js/common/validateSearch.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Retirement Book List
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>
<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<input type="checkbox" onclick="ShowAll(this)" />
	<label for="Type">All</label><br>
    <input type="checkbox" class="listShowHide" value="Book Code" onclick="ShowHideField('Book Code', 'rbl', this)" />
	<label for="Book Code">Book Code</label><br>
	<input type="checkbox" class="listShowHide" value="Book Name" onclick="ShowHideField('Book Name', 'rbl', this)" checked="checked" />
	<label for="Book Name">Book Name</label><br>
	<input type="checkbox" class="listShowHide" value="Total Stock" onclick="ShowHideField('Total Stock', 'rbl', this)" checked="checked" />
	<label for="Total Stock">Total Stock</label><br>
</div>
		<br/>
<c:choose>
	<c:when test="${pagedListHolder==null}">
		<div class="btnsarea01" style="visibility: visible;">
			<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
				<span id="infomsg">Books not Found</span>	
			</div>
		</div>
	</c:when>
<c:otherwise>
	<form:form method="POST" id="sflcontents" name="retirementBookIdList">
		<table cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<td>
					<input type="text" class="textfield" name="data" style="color:orange;" id="data" value="Search" onfocus="this.value='';this.style.color='orange';" onblur="javascript: if(this.value==''){this.value='Search';this.style.color='orange';}else{this.style.color='orange';}" />
				</td>
				<td>
					<select name="query" id="query" class="defaultselect">
						<option value="">Select...</option>
						<option value="BookCode">Book Code</option>									
						<option value="BookName">Book Name</option>
					</select>
				</td>
				<td>								
					<input type="submit" class="editbtn" name="SearchLodgingRequest" value="Search" onclick="return onSearchingBook();">
				</td>
			</tr>
		</table>
		<table id="rbl" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Select</th>
				<th>Book Code</th>
				<th>Book Name</th>
				<th>Total Stock</th>
			</tr>
			<c:forEach var="book" items="${pagedListHolder.pageList}">
				<tr>
					<td><input type="radio" name="bookCode" value="${book.bookCode}"/></td>
					<td><c:out value="${book.bookCode}"/></td>
					<td><c:out value="${book.bookName}"/></td>
					<td><c:out value="${book.totalNumberOfBookCopies}"/></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
			</tr>
		</table>
	</form:form>
	</c:otherwise>
</c:choose>
<div class="btnsarea01">
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
	<input type="button" id="Back" class="clearbtn" value="Retired Books" onclick="window.location='retiredBookList.html' ">
	<input type="submit" class="submitbtn" name="next" id="nextbutton" value="next" onclick=" return onCheckBoxSubmit();">
</div>
</body>
</html>