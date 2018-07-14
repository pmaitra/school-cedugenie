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
<title>Server Configuration SMS</title>

<link rel="stylesheet" href="/icam/css/administrator/serverConfigurationEMAIL.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>

<script>
$( document ).ready(function() {
	 var status=$("input[type=radio][name='proxy']:checked").val();
	 if(status =="NO"){
		 $( "#proxyTable" ).hide();
	 }
	 $("input[name=proxy]:radio").change(function () {
		 var status=$("input[type=radio][name='proxy']:checked").val();
		 if(status=="NO"){
			 $( "#proxyTable").hide();
		 }else{
			 $( "#proxyTable").show();
		 }
	 });
	});
</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;SMS Server Configuration 
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
<form action="insertSMSServerConfiguration.html" method="post">

		<div>		
			<table class="midsec">
			<tr>
				<th>Authentication Key <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="authkey" value="${serverConfiguration.authkey}" class="textfield1"></td>
			</tr>
			<tr>
				<th>Sender ID<img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="senderId" value="${serverConfiguration.senderId}" class="textfield1"></td>
			</tr>
			<tr>
				<th>Route<img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="route" value="${serverConfiguration.route}" class="textfield1"></td>
			</tr>
			<tr>
				<th>SMS URL<img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="smsURL" value="${serverConfiguration.smsURL}" class="textfield1"></td>
			</tr>
			<tr>
				<th>Proxy Enabled<img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<c:choose>
				    <c:when test="${serverConfiguration.proxy =='YES'}">
					    <td>
					    <input type="radio" name="proxy"  value="YES" checked="checked"/>
					    Yes&nbsp; 
					    <input type="radio" name="proxy" value="NO"/>
					    No
					    </td>
				    </c:when>
				    <c:otherwise>
					    <td>
					    <input type="radio" name="proxy"  value="YES" />
					   	Yes&nbsp; 
					    <input type="radio" name="proxy" value="NO" checked="checked"/>
					    No
					    </td>
			    	</c:otherwise>
				</c:choose>
			</tr>
			</table>
			<br/>
			
			<div id="proxyTable">
				<table class="midsec" >
				<tr>
					<th>Proxy UserName<img class="required" src="/icam/images/required.gif" alt="Required"> </th>
					<td><input type="text" name="proxyUser" value="${serverConfiguration.proxyUser}" class="textfield1"></td>					
				</tr>
				<tr>
					<th>Proxy Password<img class="required" src="/icam/images/required.gif" alt="Required"> </th>
					<td><input type="password" name="proxyPassword" value="${serverConfiguration.proxyPassword}" class="textfield1"></td>					
				</tr>
				<tr>
					<th>Proxy Set <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
					<td><input type="text" name="proxySet" value="TRUE" class="textfield1"></td>					
				</tr>
				<tr>
					<th>Proxy Host <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
					<td><input type="text" name="proxyHost" value="${serverConfiguration.proxyHost}" class="textfield1"></td>					
				</tr>
				<tr>
					<th>Proxy Port<img class="required" src="/icam/images/required.gif" alt="Required"> </th>
					<td><input type="text" name="proxyPort" value="${serverConfiguration.proxyPort}" class="textfield1"></td>					
				</tr>
				</table>
			</div>
		</div>	

	<div class="btnsarea01" style="margin-left: 40%;">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>		
		<input type="submit" id="submit" class="submitbtn" value="Submit" />	
		<input type="reset" class="clearbtn" value="Clear"/>			
	</div>


</form>
</body>
</html>