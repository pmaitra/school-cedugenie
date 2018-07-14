<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Server Configuration EMAIL" />
<meta name="keywords" content="Server Configuration EMAIL" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Server Configuration EMAIL</title>

<link rel="stylesheet" href="/icam/css/administrator/serverConfigurationEMAIL.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;EMAIL Server Configuration 
	</h1>
</div>
		<c:if test="${successMessage ne null}">
			<div class="successbox" id="successboxmsgbox" style="visibility: visible;">
				<span>${successMessage}</span>	
			</div>
		</c:if>
		
		<c:if test="${errorMessage ne null}">
				<div class="errorbox" id="errormsgbox" style="visibility: visible;">
					<span>${errorMessage}</span>	
				</div>
		</c:if>
<form action="insertServerConfigurationEMAIL.html" method="post">

		<div>		
			<table class="midsec">
			<tr>
				<th>EMAIL Server IP <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="mailServerIp" value="${serverConfiguration.mailServerIp}" class="textfield1"></td>
			</tr>
			<tr>
				<th>EMAIL Server Port <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="mailServerPort" value="${serverConfiguration.mailServerPort}" class="textfield1"></td>
			</tr>
			<tr>
				<th>EMAIL User Name <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="mailUserName" value="${serverConfiguration.mailUserName}" class="textfield1"></td>
			</tr>
			<tr>
				<th>EMAIL Password <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="password" name="mailPassword" value="${serverConfiguration.mailPassword}" class="textfield1"></td>
			</tr>
			<tr>
				<th>EMAIL Transport Protocol <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="mailTransportProtocol" value="${serverConfiguration.mailTransportProtocol}" class="textfield1"></td>					
			</tr>
			<tr>
				<th>EMAIL SMTP Authorization <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="mailSmtpAuth" value="${serverConfiguration.mailSmtpAuth}" class="textfield1"></td>					
			</tr>
			<tr>
				<th>EMAIL SMTP Starttls <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="mailSmtpStarttlsEnable" value="${serverConfiguration.mailSmtpStarttlsEnable}" class="textfield1"></td>					
			</tr>
			<tr>
				<th>EMAIL Debug <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="mailDebug" value="${serverConfiguration.mailDebug}" class="textfield1"></td>					
			</tr>
			</table>
		</div>	

	<div class="btnsarea01" style="margin-left: 40%;margin-top:12%;">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>		
		<input type="submit" id="submit" class="submitbtn" value="Submit" />	
		<input type="reset" class="clearbtn" value="Clear"/>			
	</div>


</form>
</body>
</html>