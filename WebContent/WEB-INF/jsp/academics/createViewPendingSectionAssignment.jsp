<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listStudentsPagination.html" var="pagedLink">
	  <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Asset List" />
<meta name="keywords" content="Asset List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Student List</title>

<link rel="stylesheet" href="/cedugenie/css/academics/createViewPendingSectionAssignment.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />
</head>
<body>
<div class="ttlarea">	
	<h1><img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Pending Assignment List
	</h1>
</div>
<c:choose>	
	<c:when test="${pendingList eq null || empty pendingList}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">All Students Assigned</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<table  id="candidateTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Roll Number</th>
				<th>Name</th>
				<th>Standard</th>
				<th>Second Language</th>
			</tr>
			<c:forEach var="pending" items="${pendingList}">
				<tr>
					<td>
						${pending.rollNumber}
					</td>
					<td>
						${pending.studentName}
					</td>
					<td>
						${pending.standard}
					</td>
					<td>
						${pending.secondLanguage}
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
</body>
</html>