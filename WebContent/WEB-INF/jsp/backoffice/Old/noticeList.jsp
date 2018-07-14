<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/noticeListPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>

<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Access Type Contact Mapping List" />
<meta name="keywords" content="Access Type Contact Mapping List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Notice List</title>

<link rel="stylesheet" href="/icam/css/administrator/listAccessTypeContactMapping.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<!-- pagination css -->
<link href="/icam/css/common/pagination.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/showHideField.js"></script>

</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Notice List &emsp; &emsp; &emsp; &emsp;
		
	</h1>
</div>
<c:if test="${ErrorMessage ne null}">
	<div class="errorbox" id="errorbox" style="visibility: visible;">
		${ErrorMessage}
	</div>
</c:if>
<c:if test="${SuccessMessage ne null}">
	<div class="successbox" id="successbox" style="visibility: visible;">
		${SuccessMessage}
	</div>
</c:if>
<form:form action="viewAndDeleteNotice.html" method="POST">
	<c:choose>
		<c:when test="${pagedListHolder eq null}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No Notice Found</span>	
			</div>				
		</c:when>
	<c:otherwise>
	<table cellspacing="0" cellpadding="0" class="midsec1">
		<tr>	
			<td colspan="2">
				<select name="query" id="query" class="defaultselect" >
					<option value="">Select...</option>
					<option value="Date">Date</option>
					<option value="NoticeSubject">Notice Subject</option>
				</select>
			</td>
			<td>
				<input type="text" class="textfield" name="data" id="data" value="Search Notice" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='Search Notice';}" />
			</td>		
			<td>
				<input type="submit" class="editbtn" id="submitToSearch" name="submitToSearch" value="Search">
			</td>
		</tr>
	</table>
	<table id="noticeList"  class="midsec1" cellspacing="0" cellpadding="0">		   		
   		<tr>
   			<th>Select</th>
   			<th>Date</th>
			<th>Notice Subject</th>
		</tr>
		<c:forEach var="notice" items="${pagedListHolder.pageList}">	
			<tr>
				<td>
					<input type="radio" name="noticeCode" value="${notice.noticeCode}"/>
				</td>	
				<td>
					${notice.time}
				</td>			
				<td>
					${notice.noticeName}
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="3" id="toolbar">
				<c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
			</td>
		</tr>							
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>		
		<input type="submit" id="delete" name="delete" class="submitbtn" value="Delete" />	
		<input type="submit" id="edit"  name="edit" class="editbtn" value="Edit" />	
	</div>
		
	</c:otherwise>
	</c:choose>	
</form:form>
</body>
</html>