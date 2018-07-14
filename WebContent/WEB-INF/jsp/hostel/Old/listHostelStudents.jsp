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
<c:url value="/listProductsPagination.html" var="pagedLink">
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
<title>Hostel Student List</title>

<link rel="stylesheet" href="/icam/css/hostel/listHostelStudents.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/hostel/listHostelStudents.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Hostel Student List
	</h1>
</div>
<c:choose>	
	<c:when test="${hostelList eq null || empty hostelList || studentList eq null || empty studentList}">
		<div class="btnsarea01" >
			<c:if test="${hostelList eq null || empty hostelList}">
				<div class="errorbox" id="errorbox0" style="visibility: visible;">
					<span id="errormsg0">Hostel List Not Found</span>	
				</div>
			</c:if>
			<c:if test="${studentList eq null || empty studentList}">
				<div class="errorbox" id="errorbox1" style="visibility: visible;">
					<span id="errormsg1">Student List Not Found</span>	
				</div>
			</c:if>
		</div>
	</c:when>
	<c:otherwise>
		<table  id="hostelTable" cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th>Hostel</th>
				<td>
					<select class="defaultselect" id="hostel" onchange="showStudents(this);">
						<option value="">Select</option>
						<c:forEach var="hostel" items="${hostelList}">
							<option value="${hostel.hostelCode}">${hostel.hostelName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
		<div id="studentTable">
			<c:forEach var="hostel" items="${hostelList}">
				<table  id="${hostel.hostelCode}" cellspacing="0" cellpadding="0" class="midsec1">
					<tr>
						<th>Roll Number</th>
						<th>Name</th>
						<th>Standard</th>
						<th>Section</th>
						<th>House</th>
					</tr>
					<c:set var="i" value="0" scope="page" />
					<c:forEach var="student" items="${studentList}">
						<c:if test="${hostel.hostelName eq student.house}">
							<c:set var="i" value="${i+1}" scope="page" />
							<tr>
								<td>${student.rollNumber}</td>
								<td>${student.studentName}</td>
								<td>${student.standard}</td>
								<td>${student.section}</td>
								<td>${student.house}</td>
							</tr>
						</c:if>
					</c:forEach>
					<c:if test="${i eq 0}">
						<tr>
							<td colspan="5">Sorry No Students Found In This Hostel</td>
						</tr>
					</c:if>
				</table>
			</c:forEach>
		</div>
	</c:otherwise>
</c:choose>
</body>
</html>