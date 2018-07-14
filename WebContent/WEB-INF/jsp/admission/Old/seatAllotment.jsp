<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Create Seat Allotment for exam-admission" />
<meta name="keywords" content="Seat Allotment" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Seat Allotment</title>
<link rel="stylesheet" href="/icam/css/admission/seatAllotment.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>

<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">
<script src="/icam/js/admission/seatAllotment.js"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Seat Allotment
		</h1>
</div>
<c:if test="${successStatus != null}">
	<div class="successbox" id="successbox" style="visibility:visible;">
		<span id="successmsg" style="visibility:visible;">Successfully Updated!</span>	
	</div>
</c:if>
<c:if test="${failStatus != null}">
		<div class="errorbox" id="errorbox" style="visibility: visible;">
			<span id="errormsg">Update Fail!</span>	
		</div>
</c:if>
<form:form method="POST" action="submitSeatAllotment.html">		
	<table cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th>Venue<img class="required" src="/icam/images/required.gif" alt="Required"></th>
		<td colspan="2">
			<select name="venueId" id="venue" class="defaultselect">
				<option value="">Select...</option>
				<c:forEach var="venue" items="${venueList}">
					<option value="<c:out value="${venue.venueId}"/>"><c:out value="${venue.venueName}"/></option>
				</c:forEach>
			</select>
		</td>	
		<th><input type="checkbox" id="venueWiseForm" name="status" value="checked">Venue Wise Form</th>
	</tr>
	<tr>
		<th>Availabvle Capacity</th>		
		<td><input type="text" class="textfield2" id="capacity" name="capacity" readonly="readonly"></td>
	</tr>
	<tr>
		<th>Start Seat Roll No<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td><input type="text" class="textfield2" id="startSeatRollNo" name="startSeatRollNo" ></td>
		<th>End Seat Roll No<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td><input type="text" class="textfield2" id="endSeatRollNo" name="endSeatRollNo" ></td>
	</tr>
	</table>
	<div class="infomsgbox" id="infomsgbox" >
			<span id="infomsg"></span>	
	</div>		
	<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
	</div>	
	<input type="hidden" name="numberOfCandidate" id="numberOfCandidate" value="0" readonly="readonly"/>
	<input type="hidden" name="prevStartSeatRollNo" id="prevStartSeatRollNo" value="0" readonly="readonly"/>		
	<input type="hidden" name="prevEndSeatRollNo" id="prevEndSeatRollNo" value="0" readonly="readonly"/>		
			
	<input type="submit" class="submitbtn" value="SUBMIT" id="submitButton" onclick="return validateSeatAllotmentForm();" />
	<input type="reset" class="clearbtn" value="clear" />
</form:form>
<br>
	<table cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<th>Venue</th><th>Total Capacity</th><th>Available Capacity</th><th colspan="2">Reset Seat Allotment</th>
		</tr>
		<c:forEach var="venue" items="${venueList}">
		<tr>
			<td>${venue.venueName}</td><td>${venue.capacity}</td><td>${venue.availableSeat}</td><td><input type="button" class="submitbtn" name="resetSeatAllotment" id="resetSeatAllotment" value="Reset" readonly="readonly" onClick="window.open('resetSeatAllotment.html?venueId=${venue.venueId}','_self')" style="cursor:pointer;"/> <td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>