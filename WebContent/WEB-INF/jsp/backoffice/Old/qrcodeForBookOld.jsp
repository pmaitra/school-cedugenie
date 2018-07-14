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
<title>Generate QR Code For Book</title>

<link rel="stylesheet" href="/sms/css/backoffice/qrcodeForBook.css" />
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
	<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Generate QR Code For Book
	</h1>
</div>
	
	<form:form action="submitBookAttributeForQRCode.html" method="post">
		<table id="TeahetAttributeColumn" cellspacing="0" cellpadding="0" class="midsec1">		
		<tr>
			<th colspan="2" style="text-align: center;">
				Generate Book QR Code
			</th>
		</tr>			
		<tr>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="bookName" id="bookName"/>BOOK NAME</td>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="individualCode" id="individualCode"/>INDIVIDUAL CODE</td>						
		</tr>		
		
		<tr>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="dateOfEntry" id="dateOfEntry"/>DATE OF ENTRY</td>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="price" id="price"/>PRICE</td>
		</tr>			
		
		<tr>		
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="author" id="author"/>AUTHOR</td>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="publisher" id="publisher"/>PUBLISHER</td>
		</tr>
		
		<tr>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="isbn" id="isbn"/>ISBN NUMBER</td>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="medium" id="medium"/>BOOK MEDIUM</td>
		</tr>		
		
		<tr>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="edition" id="edition"/>EDITION</td>
			<td><input type="checkbox" checked="checked" name="attributeColumn" value="bookLanguage" id="bookLanguage"/>BOOK LANGUAGE</td>
		</tr>
		
		<tr>
			<td>Date :: <input type="text" name="date" title="YYYY-MM-DD" id="date" class="textfield1" readonly="readonly"/></td>
			<td>Time :: <input type="text" name="time" title="HH:MM:SS" id="time" class="textfield1"/></td>
		</tr>
		
		
	</table>
		<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
		<input type="reset" id="reset" name="reset" value="reset" class="editbtn"/>		
		<input type="submit" id="submit" name="submit" value="Submit" class="submitbtn" onclick="return validateTeacherQRCode();"/>
	</div>
</form:form>
</body>
</html>