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
<title>Read QR Code</title>

<link rel="stylesheet" href="/sms/css/backoffice/readQRCode.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>

<script>
	function checkForBook(type){
		if(type.value=="book"){
			$("#idd").autocomplete({
				source: '/sms/getBookIdForQrCode.html'
			});
		}
	};
		
	
	function viewCode(){		
		var idValue=document.getElementById("idd").value;
			if(idValue==""){
				document.getElementById("warningbox").style.visibility="visible";
				document.getElementById("warningmsg").innerHTML="Please Enter ID";
				return false;
			}else{
					var source=document.getElementById("initialPath").value;
					if(document.getElementById("type").value=="book"){
						source=source+idValue+".png";
					}
					else if(document.getElementById("type").value=="teacher"){
						source=source.replace("Book","Teacher");
						source=source+idValue+".png";
					}
					else if(document.getElementById("type").value=="student"){
						source=source.replace("Book","Student");
						source=source+idValue+".png";
					}else{
						document.getElementById("warningbox").style.visibility="visible";
						document.getElementById("warningmsg").innerHTML="Please Select Type";
						return false;
					}
					
					document.getElementById("path").value=source;				
				}	
		}
</script>
</head>
<body>

<div class="ttlarea">	
	<h1>
	<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Print QR Code
	</h1>
</div>
<form action="getQRCode.html" target="_blank" method="post">
	<table id="TeahetAttributeColumn" cellspacing="0" cellpadding="0" class="midsec">
		<tr>
			<th>Type :: </th>
			<td>
				<select id="type" onchange="checkForBook(this);" class="defaultselect">
					<option value="">Select</option>
					<option value="teacher">Teacher</option>
					<option value="student">Student</option>
					<option value="book">Book</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>ID :: </th>
			<td>
				<input type="text" name="idd" id="idd" class="textfield">
			</td>
		</tr>
	</table>
	<input type="hidden" name="initialPath" id="initialPath" value="${path}">
	<input type="hidden" name="path" id="path" >
	
	
	
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>
		</div>
		<button type="submit" class="submitbtn" id="getCode" onclick="return viewCode();">Get QR Code</button>
	</div>
</form>
	
</body>
</html>