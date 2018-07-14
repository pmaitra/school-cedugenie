<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to View Admission Merit List Report" />
<meta name="keywords" content="Admission Merit List Report" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Exam Venue Wise Candidate</title>
<link rel="stylesheet" href="/icam/css/report/examVenueWiseCandidate.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">
<link rel="stylesheet" href="/icam/Cal/jsDatePick_ltr.min.css">
<script src="/icam/Cal/jsDatePick.min.1.3.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
<script src="/icam/js/report/examVenueWiseCandidate.js"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Exam Venue Wise Candidate
		</h1>
</div>

<c:if test="${message != null}">
		<div class="errorbox" id="errorbox" style="visibility: visible;">
			<span id="errormsg">${message}</span>	
		</div>
</c:if>
<form:form id="examVenueWiseCandidateReport" name="examVenueWiseCandidateReport" method="POST" action="examVenueWiseCandidateReport.html">		
	<table cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<td>Exam Centre<img class="required" src="/icam/images/required.gif" alt="Required"></td>
			<td>
				<select name="venue.venueId" id="venueId" class="defaultselect">
					<option value="">Select...</option>
					<c:forEach var="examVenue" items="${examVenueList}">
						<option value="<c:out value="${examVenue.venueId}"/>"><c:out value="${examVenue.venueName}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
			<input type="submit" id="submit" name="submit" value="Submit" class="submitbtn" onclick="return validateForm();"/>
	
</form:form>
	<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
	</div>

</body>
</html>