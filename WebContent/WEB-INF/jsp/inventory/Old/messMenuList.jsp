<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>

<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Mess Menu List" />
<meta name="keywords" content="Mess Menu List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Mess Menu List</title>
<link rel="stylesheet" href="/icam/css/inventory/messMenuList.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
</head>
	<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Mess Menu List		
		</h1>
	</div>
	<c:choose>
		<c:when test="${messMenuRoutineList == null}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No Menu list Found</span>
			</div>
		</c:when>
	<c:otherwise>
	<table id="det" cellspacing="0" cellpadding="0" class="midsec1">	
		<tr>
			<th>Mess Menu</th>
			<th>From </th>		
			<th>To</th>					
			<th>Details</th>	
		</tr>
	<c:forEach var="mml" items="${messMenuRoutineList}">
		<tr>
			<td>${mml.messMenuRoutineName}</td> 					
			<td>${mml.startDate}</td>
			<td>${mml.endDate}</td>
			<td><a href="viewMessMenuDetails.html?messMenuCode=${mml.messMenuRoutineCode}"><input type="button" name="details" value="DETAILS" class="submitbtn"></a></td>
		</tr>
		</c:forEach>		
	</table>
	</c:otherwise>
	</c:choose>
</body>
</html>