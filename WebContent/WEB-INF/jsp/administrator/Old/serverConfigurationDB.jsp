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
<meta name="description" content="Page to Server Configuration DB" />
<meta name="keywords" content="Server Configuration DB" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Server Configuration DB</title>

<link rel="stylesheet" href="/icam/css/administrator/serverConfigurationDB.css" />
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
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;DB Server Configuration 
	</h1>
</div>
			<c:if test="${errorMessage ne null}">
				<div class="errorbox" id="errormsgbox" style="visibility: visible;">
					<span>${errorMessage}</span>	
				</div>
			</c:if>
			
			<c:if test="${successMessage ne null}">
			<div class="successbox" id="successboxmsgbox" style="visibility: visible;">
				<span>${successMessage}</span>	
			</div>
			</c:if>
<form action="insertServerConfigurationDB.html" method="post">
		<div>		
			<table class="midsec">
			<tr>
				<th>Database Server URL <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="jdbcURL" value="${serverConfiguration.jdbcURL}" class="textfield1"></td>
			</tr>
			<tr>
				<th>Database Server Port <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="jdbcPort" value="${serverConfiguration.jdbcPort}" class="textfield1"></td>
			</tr>
			<tr>
				<th>Database User Name <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="jdbcUserName" value="${serverConfiguration.jdbcUserName}" class="textfield1"></td>
			</tr>
			<tr>
				<th>Database Password <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="password" name="jdbcPassword" value="${serverConfiguration.jdbcPassword}" class="textfield1"></td>
			</tr>
			<tr>
				<th>Database Database Name <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="jdbcDatabaseName" value="${serverConfiguration.jdbcDatabaseName}" class="textfield1"></td>
			</tr>
			
			<tr>
				<th>Database Max Statement <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="jdbcMaxStatement" value="${serverConfiguration.jdbcMaxStatement}" class="textfield1"></td>
			</tr>
			
			<tr>
				<th>Database Statement Cache  Deferred Closed Thread Number<img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="jdbcStatementCacheNumDeferredCloseThread" value="${serverConfiguration.jdbcStatementCacheNumDeferredCloseThread}" class="textfield1"></td>
			</tr>
			
			<tr>
				<th>Database Max Idle Time (in )<img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="jdbcMaxIdleTime" value="${serverConfiguration.jdbcMaxIdleTime}" class="textfield1"></td>
			</tr>
			
			<tr>
				<th>Database Driver Class Name<img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="jdbcDriverClassName" value="${serverConfiguration.jdbcDriverClassName}" class="textfield1"></td>
			</tr>
			
			<tr>
				<th>Database Max Active Connection<img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="jdbcMaxActive" value="${serverConfiguration.jdbcMaxActive}" class="textfield1"></td>
			</tr>
			
			<tr>
				<th>JDBC Dialect<img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="jdbcDialect" value="${serverConfiguration.jdbcDialect}" class="textfield1"></td>
			</tr>
			
			<tr>
				<th>JDBC Initial Size<img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="jdbcInitialSize" value="${serverConfiguration.jdbcInitialSize}" class="textfield1"></td>
			</tr>
			
			<tr>
				<th>JDBC Acquire Increment Size<img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td><input type="text" name="jdbcAcquireIncrement" value="${serverConfiguration.jdbcAcquireIncrement}" class="textfield1"></td>
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