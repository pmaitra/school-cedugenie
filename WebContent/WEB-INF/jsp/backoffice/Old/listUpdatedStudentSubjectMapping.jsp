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
<title>Student Subject Mapping List</title>

<link rel="stylesheet" href="/icam/css/backoffice/listStudentSubjectMapping.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/backoffice/listStudentSubjectMapping.js"></script>
<script type="text/javascript" src="/icam/js/common/validateSearch.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Student Subject Mapping List
	</h1>
</div>

<c:choose>	
	<c:when test="${null eq studentSubjectMappingList || empty studentSubjectMappingList}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">List Not Found</span>
			</div>
		</div>
	</c:when>
	<c:otherwise>
			<table cellspacing="0" cellpadding="0" class="midsec1">
				<tr>
					<th colspan="2" style="font-size:18px; text-align:center;">
						Student Subject Mapping :: Standard :- ${student.standard} &nbsp;&nbsp;&nbsp;  Section :- ${student.section}
						<input type="button" class="greenbtn" value="PRINT REPORT" onclick="window.open('studentSubjectMapping.html?standard=${student.standard}&section=${student.section}','_self')">
					</th>
				</tr>
				<tr>
					<th>Roll Number (Name)</th>
					<th>Subjects</th>
				</tr>
				<c:forEach var="stud" items="${studentSubjectMappingList}">
					<tr>
						<td>
							${stud.status}
						</td>
						<td>
							<c:forEach var="sub" items="${stud.subjects}">
								${sub.subjectName} &nbsp;&nbsp;&nbsp;&nbsp;
							</c:forEach>
						</td>
					</tr>
				</c:forEach>
			</table>
	</c:otherwise>
</c:choose>
</body>
</html>