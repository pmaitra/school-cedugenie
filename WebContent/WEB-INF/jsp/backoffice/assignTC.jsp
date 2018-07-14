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
<meta name="description" content="Page to Assign TC" />
<meta name="keywords" content="Assign TC" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Assign TC</title>
<link rel="stylesheet" href="/sms/css/backoffice/assignTC.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>	
<script type="text/javascript" src="/sms/js/backoffice/assignTC.js"></script>	

</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Assign TC
		</h1>
</div>
<form name="grantTC" action="grantTC.html" method="POST">
<table cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th>Exam Session :: </th>
		<td>
			<input type="hidden" name="academicSsession" id="session" value="${aY.academicYearCode}" />
			${aY.academicYearName}
		</td>
	</tr>
	<tr>
		<th>Cause For TC :: </th>
		<td>
			<select id="tcCause" name="tcCause" class="defaultselect">
				<option value="">Select...</option>
				<option value="diciplinary">Diciplinary</option>
				<option value="non-diciplinary">Non-Diciplinary</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>Class :: </th>
		<td>
			<select id="class" name="className" class="defaultselect">
				<option value="">Select...</option>
				<c:forEach var="classList" items="${classList}">
					<option value="${classList.classCode}">${classList.className}</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<th>Stream :: </th>
		<td>
			<select id="stream" name=streamName class="defaultselect">
				<option value="">Select...</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>Section :: </th>
		<td>
			<select id="section" name="section" class="defaultselect">
				<option value="">Select...</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>Student :: </th>
		<td>
			<select id="student" name="student" class="defaultselect">
				<option value="">Select...</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>Registration Id :: </th>
		<td>
			<input type="text" class="textfield" name="registrationId" id="stuID" />
		</td>
	</tr>
</table>
<div class="btnsarea01">
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg1"></span><br>
		<span id="warningmsg2"></span>
	</div>
	<input type="submit" id="submit" name="submit" value="Submit" class="submitbtn" disabled="disabled" />
</div>
</form>
<br>

<div class="infomsgbox" id="infomsgbox" >
	<span id="infomsg"></span>
</div>	
</body>
</html>