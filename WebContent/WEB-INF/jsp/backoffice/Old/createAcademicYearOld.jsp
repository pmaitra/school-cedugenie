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
<meta name="description" content="Page to Add Hostel" />
<meta name="keywords" content="Add Hostel" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Academic Year</title>

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link href="/icam/css/backoffice/createAcademicYear.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/icam/Cal/default.css"/>

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script src="/icam/Cal/zebra_datepicker.js"></script>
<script type="text/javascript" src="/icam/js/backoffice/createAcademicYear.js"></script>
<script>
	 $(document).ready(function() {
		 $('#sessionStartDate').Zebra_DatePicker();
		 $('#sessionEndDate').Zebra_DatePicker();
		 
		 $('#sessionStartDate').Zebra_DatePicker({
		 	  format: 'd/m/Y',
		 	  pair: $('#sessionEndDate')
		 	});
		 $('#sessionEndDate').Zebra_DatePicker({
		 	  format: 'd/m/Y',
		 	 direction: true
		 	});
});
</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Academic Year		
	</h1>
</div>
<form:form method="POST" id="addAcademicYear" name="addAcademicYear" action="editAcademicYear.html" >
	<div class="btnsarea01">
		<div class="infomsgbox" id="infomsgbox0" style="visibility: visible;" >
			<span id="infomsg0">Session Start And End Date Once Entered Cannot Be Edited</span>	
		</div>
		<c:if test="${updateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${updateStatus}</span>	
			</div>
		</c:if>
	</div>
			
	<table cellspacing="0" cellpadding="0" class="midsec" id="academicYearTable">
		<c:forEach var="academicYear" items="${academicYearList}">
			<c:if test="${academicYear.yearStatus eq 'CURRENT'}">
				<tr>
					<th>Current Session :: </th>
					<td>
						<input type="hidden" name="academicYearCode" value="${academicYear.academicYearCode}">
						${academicYear.academicYearName}
					</td>
				</tr>
				<tr>
					<th>Start Date :: <img class="required" src="/icam/images/required.gif" alt="Required"></th>
					<td>
						<input class="textfield1" name="sessionStartDate" id="sessionStartDate"  value="${academicYear.sessionStartDate}">
					</td>
				</tr>
				<tr>
					<th>End Date :: <img class="required" src="/icam/images/required.gif" alt="Required"></th>
					<td>
						<input class="textfield1" name="sessionEndDate" id="sessionEndDate"  value="${academicYear.sessionEndDate}">
					</td>
				</tr>
				<c:set var="i" value="0" scope="page" />
				<c:if test="${academicYear.sessionStartDate ne null && academicYear.sessionEndDate ne null}">
					<c:set var="i" value="1" scope="page" />
				</c:if>
			</c:if>
		</c:forEach>
		<c:forEach var="academicYear" items="${academicYearList}">
			<c:if test="${academicYear.yearStatus eq 'NEXT'}">
				<tr>
					<th colspan="2">Next Academic Session Is <b>${academicYear.academicYearName}</b></th>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
		<c:if test="${i ne 1}">
			<input type="button" value="Clear" class="clearbtn" />
			<input type="submit" id="submit" name="submit" class="submitbtn" value="Submit" onclick="return validate();" />
		</c:if>
	</div>
</form:form>
</body>
</html>