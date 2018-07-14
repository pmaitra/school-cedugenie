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
<meta name="description" content="Page to Server Configuration LDAP" />
<meta name="keywords" content="Server Configuration LDAP" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Directory Server Configuration</title>

<link rel="stylesheet" href="/icam/css/administrator/serverConfigurationLDAP.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>
<head>

</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Directory Server Configuration
	</h1>
</div>
<form action="insertServerConfigurationLDAP.html" method="post">	
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
	<div class="body">
	<table class="midsec">
		<tr>
			<th>Directory Server Type<img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<td>
				<select name="directoryServerType" id="directoryServerType" class="defaultselect">
					<option value="">Please Select</option>
					<c:choose>
							<c:when  test="${serverConfiguration.directoryServerType eq 'msad'}">	
								<option value="msad" selected="selected">MS Active Directory</option>
							</c:when>					
							<c:otherwise>					
								<option value="msad">MS Active Directory</option>
							</c:otherwise>
					</c:choose>
					
					<c:choose >
						<c:when test="${serverConfiguration.directoryServerType eq 'ldap'}">
							<option value="ldap" selected="selected">Apache LDAP</option>
						</c:when>					
						<c:otherwise>
							<option value="ldap">Apache LDAP</option>
						</c:otherwise>
					</c:choose>	
				</select>
			</td>
		</tr>
	</table>
		<div id="showHide">
			<table class="midsec">
			<tr>
				<th>Directory Server URL <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="directoryServerUrl" value="${serverConfiguration.directoryServerUrl}" class="textfield1"></td>
			</tr>
			<tr>
				<th>Directory Server Security Authentication Type <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input type="text" name="directoryServerSecurityAuthenticationType" class="textfield1" value="${serverConfiguration.directoryServerSecurityAuthenticationType}"></td>
			</tr>
			<tr>
				<th>Directory Server Port <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="directoryServerPort" value="${serverConfiguration.directoryServerPort}" class="textfield1"></td>
			</tr>
			<tr>
				<th>Directory Server User DN <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="directoryServerUserDN" value="${serverConfiguration.directoryServerUserDN}" class="textfield1"></td>
			</tr>
			<tr>
				<th>Directory Server Base DN <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="directoryServerBaseDN" value="${serverConfiguration.directoryServerBaseDN}" class="textfield1"></td>
			</tr>
			<tr>
				<th>Directory Server Password <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="password" name="directoryServerPassword" value="${serverConfiguration.directoryServerPassword}" class="textfield1"></td>
			</tr>
			<tr>
				<th>Directory Server Filter <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="directoryServerFilter" value="${serverConfiguration.directoryServerFilter}" class="textfield1"></td>
			</tr>
			</table>
		</div>	
	</div>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>		
		<input type="submit" id="submit" class="submitbtn" value="Submit" />	
		<input type="reset" class="clearbtn" value="Clear"/>			
	</div>	
</form>
</body>
</html>