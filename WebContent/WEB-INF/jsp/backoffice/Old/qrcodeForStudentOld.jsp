<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Add Asset" />
<meta name="keywords" content="Add Asset" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Generate QR Code For Student</title>

<link rel="stylesheet" href="/sms/css/backoffice/qrcodeForStudent.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/Cal/zebra_datepicker.js"></script>
<link rel="stylesheet" href="/sms/Cal/default.css" type="text/css">

<script>	
$(document).ready(function() {    
    $('#date').Zebra_DatePicker();
    
    
    $('#date').Zebra_DatePicker({
    	  format: 'Y-m-d',
    	  direction: true
    	});
    
 });
 

	function validateTeacherQRCode(){	
		var dateValue=document.getElementById("date").value;
		var timeValue=document.getElementById("time").value;
		
		if(dateValue==""){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Enter Date";
			return false;
		}

		
		if(timeValue==""){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Enter Time";
			return false;
		}			
		}
</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
	<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Generate QR Code For Student
	</h1>
</div>
	
	<form:form action="submitStudentAttributeForQRCode.html" method="post">
		<table id="StudentAttributeColumn" cellspacing="0" cellpadding="0" class="midsec1">		
		<tr>
			<th colspan="6" style="text-align: center;">
				Generate Student's QR Code
			</th>
		</tr>			
		<tr>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="registrationId" id="registrationId"/>REGISTRATION ID</td>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="name" id="name"/>NAME</td>						
		</tr>
		
		<tr>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="gender" id="gender"/>GENDER</td>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="dateOfBirth" id="dateOfBirth"/>DATE OF BIRTH</td>
		</tr>		
		
		<tr>		
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="fathersName" id="fathersName"/>FATHER's NAME</td>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="motherName" id="motherName"/>MOTHER's NAME</td>
		</tr>
		
		<tr>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="klass" id="klass"/>CLASS</td>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="section" id="section"/>SECTION</td>
		</tr>
		
		<tr>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="stream" id="stream"/>STREAM</td>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="rollNumber" id="rollNumber"/>ROLL NUMBER</td>
		</tr>		
		
		<tr>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="course" id="course"/>COURSE</td>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="bloodGroup" id="bloodGroup"/>BLOOD GROUP</td>
		</tr>			
		
		<tr>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="contactNumber" id="contactNumber"/>CONTACT NUMBER</td>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="contactMailId" id="contactMailId"/>CONTACT MAIL ID</td>
		</tr>	
		
		<tr>
			<td>Date :: <input type="text" name="date" title="YYYY-MM-DD" id="date" class="textfield1" readonly="readonly"/></td>
			<td>Time :: <input type="text" name="time" title="HH:MM:SS" class="textfield1" id="time"/></td>
		</tr>	
		
			
	</table>
		<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
		<input type="reset" id="reset" name="reset" value="reset" class="editbtn"/>		
		<input type="submit" id="submit" name="submit" value="Submit" class="submitbtn"  onclick="return validateTeacherQRCode();"/>
	</div>
</form:form>
</body>
</html>