<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/addThresholdPaginationForBook.html" var="pagedLink">
	<c:param name="p" value="~"/>
</c:url>

<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Add Book's Threshold" />
<meta name="keywords" content="Add Book's Threshold" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Add Book's Threshold</title>

<link rel="stylesheet" href="/sms/css/library/addThresholdForBook.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<link href="/sms/css/common/pagination.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/library/addThresholdForBook.js"></script>
<script type="text/javascript" src="/sms/js/common/validateSearch.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Add Book's Threshold ${Message}
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>
<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<input type="checkbox" onclick="ShowAll(this)" />
	<label for="Type">All</label><br>
	<input type="checkbox" class="listShowHide" value="Book Code" onclick="ShowHideField('Book Code', 'thres', this)" />
	<label for="Book Code">Book Code</label><br>
	<input type="checkbox" class="listShowHide" value="Book Name" onclick="ShowHideField('Book Name', 'thres', this)" checked="checked" />
	<label for="Book Name">Book Name</label><br>
	<input type="checkbox" class="listShowHide" value="Threshold" onclick="ShowHideField('Threshold', 'thres', this)" checked="checked" />
	<label for="Threshold">Threshold</label><br>
</div>
			<c:if test="${Message != null}">
				<div class="btnsarea01">
					<c:if test="${Message eq 'success'}">
						<div class="successbox" id="successbox" style="visibility:visible;">
							<span id="successmsg">Data Successfully Inserted</span>
						</div>
					</c:if>
					<c:if test="${Message eq 'fail'}">
						<div class="errorbox" id="errorbox" >
							<span id="errormsg">Problem Occur While Saving</span>	
						</div>
					</c:if>
				</div>			
			</c:if>
	
<c:choose>
	<c:when test="${pagedListHolder==null}">
		<div class="errorbox" id="errorbox" style="visibility:visible;">
			<span class="errormsg">Data Not Found</span>
		</div>
	</c:when>
	<c:otherwise>
	<form:form id="paydonefrm" name="addThresholdDetails">
		<table id="table" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<td>
					<input type="text" class="textfield" name="data" id="data" value="Search" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='Search';}" />
				</td>
				<td>
					<select name="query" id="query" class="defaultselect">
						<option value="" selected>Select...</option>
						<option value="BookCode">Book Code</option>
						<option value="BookName">Book Name</option>
						<option value="BookThresholdValue">Threshold</option>
					</select>
				</td>
				<td>
					<input type="submit" class="editbtn" name="BookSearch" value="Search" onclick=" return onSearchingProducts();" />
				</td>
			</tr>
		</table>
		<table id="thres" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Select</th>
				<th>Book Code</th>
				<th>Book Name</th>
				<th>Threshold</th>
			</tr>	
			<c:forEach var="item" items="${pagedListHolder.pageList}" varStatus="ind">
			<tr>
				<td>
					<input id="ch${ind.index}" type="checkbox" name="itemThresholdValue" value="${item.itemCode}" disabled="disabled" />
				</td>
				<td>${item.itemCode}</td>
				<td>${item.itemName}</td>
				<td>
					<input type="text"  id="tx${ind.index}" class="textfield" readonly="readonly" name="${item.itemCode}" value="${item.threshold}"/>
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="4" id="toolbar">
					<c:out value="Displaying ${first} to ${last} of ${total} items"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
				</td>
			</tr>
		</table>

		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			<div class="infomsgbox" id="infomsgbox" >
				<span id="infomsg"></span>	
			</div>
			<button type="button" class="clearbtn"  name="editBut" id="editBut" onclick="edit();">EDIT</button>
			<button type="submit" class="submitbtn" name="submitButton" disabled="disabled" id="submitButton" onclick="return onCheckBoxSubmit();">SUBMIT</button>
		</div>
	</form:form>
	</c:otherwise>
</c:choose>
</body>
</html>