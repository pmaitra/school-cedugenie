<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- ADDED JSTL TAG LIBRARY -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<c:url value="/examVenuePaging.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Current Opening List" />
<meta name="keywords" content="Current Opening List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Exam Venue List</title>

<link rel="stylesheet" href="/icam/css/admission/examVenueList.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/showHideField.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>

<script type="text/javascript" src="/icam/js/common/jquery-1.js"></script>
<link rel="stylesheet" type="text/css" href="/icam/css/common/loggingDetails.css" />
<script type="text/javascript" src="/icam/js/common/loggingDetails.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Exam Venue List
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>

<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">	
	<input type="checkbox" class="listShowHide" value="Venue" onclick="ShowHideField('Venue', 'copl', this)" checked="checked"/>
	<label for="Venue">Venue</label><br>
	<input type="checkbox" class="listShowHide" value="Capacity" onclick="ShowHideField('Capacity', 'copl', this)" checked="checked"/>
	<label for="Capacity">Capacity</label><br>
	<input type="checkbox" class="listShowHide" value="Contact No" onclick="ShowHideField('Contact No', 'copl', this)" checked="checked"/>
	<label for="Contact No">Contact No</label><br>
	<input type="checkbox" class="listShowHide" value="Email Id" onclick="ShowHideField('Email Id', 'copl', this)" checked="checked"/>
	<label for="Contact No">Email Id</label><br>
</div>
	
	<c:choose>
		<c:when test="${examVenuePagedListHolder.pageList == null || empty examVenuePagedListHolder.pageList}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
					<span id="infomsg">No Exam Venue created.</span>	
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<form:form method="POST" action="searchExamVenue.html">
				<table cellspacing="0" cellpadding="0" class="midsec1">
					<tr>
					<th colspan="3"> :: Exam Venue List :: </th>
					</tr>
					<tr>					
						<td>
							<select name="query" id="query" class="defaultselect">
								<option value="">Select...</option>
								<option value="Venue">Venue</option>
								<option value="EmailId">Email Id</option>
							</select>
						</td>
						<td>
							<input type="text" class="textfield" name="data" id="data" value="Search" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='Search';}" />
						</td>
						<td>
							<input type="submit" name="searchExamVenue" value="Search" class="editbtn" onclick="return validateSearch('query','data','warningbox','warningmsg');">
						</td>
					</tr>
			</table>
		</form:form>
		<table id="copl" cellspacing="0" cellpadding="0" class="midsec1">
		<tr>								
			<th>Venue</th>
			<th>Capacity</th>
			<th>Contact No</th>	
			<th>Email Id</th>									
		</tr>
		<c:forEach var = "examVenue" items="${examVenuePagedListHolder.pageList}">
			<tr onClick="window.open('examVenueDetails.html?venueId=${examVenue.venueId}','_self')" style="cursor:pointer;">
				<td><c:out value="${examVenue.venueName}"></c:out></td>		
				<td><c:out value="${examVenue.capacity}"></c:out></td>
				<td><c:out value="${examVenue.contactNo}"></c:out></td>
				<td><c:out value="${examVenue.email}"></c:out></td>			
			</tr>
		</c:forEach>
		<tr>
       		<td colspan="11" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${examVenuePagedListHolder}" pagedLink="${pagedLink}"/></td>
		</tr>
		
		</table>
	</c:otherwise>
</c:choose>
<div class="btnsarea01">
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
</div>				
</div>	
</body>
</html>