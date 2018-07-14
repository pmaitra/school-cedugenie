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

<div class="btnsarea01">
	<c:if test="${insertUpdateStatus ne null}">
		<div class="infomsgbox" style="visibility: visible;" >
			<span>${insertUpdateStatus}</span>	
		</div>
	</c:if>
</div>
<c:if test="${excelDataInsertStatus ne null }">
	<c:if test="${excelDataInsertStatus ne 0 }">
		<div class="successbox" id="successbox" style="visibility: visible;">
			<span id="successmsg">Excel Uploaded Successfully</span>	
		</div>
	</c:if>
	<c:if test="${excelDataInsertStatus eq 0 }">
		<div class="successbox" id="successbox" style="visibility: visible;">
			<span id="successmsg">Problem Occured While Uploading Excel</span>	
		</div>
	</c:if>
</c:if>



<c:choose>	
	<c:when test="${null eq mappingList || empty mappingList}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">List Not Found</span>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<form:form id="studentListForm" name="studentListForm" action="getStudentDetails.html" method="POST">
			<table  id="candidateTable" cellspacing="0" cellpadding="0" class="midsec1">
				<tr>
					<th>Standard</th>
					<th>Section</th>
					<th>Total</th>
					<th>Uploaded</th>
					<th>Remaining</th>
					<th>Update</th>
				</tr>
				<c:forEach var="status" items="${mappingList}">
					<tr>
						<td>
							${status.student.standard}
						</td>
						<td>							
							${status.student.section}
						</td>
						<td>
							${status.total}
						</td>
						<td>
							${status.completed}
						</td>
						<td>
							${status.total-status.completed}
						</td>
						<td>
							<c:if test="${status.total-status.completed gt 0}">
								<a onClick="window.open('createStudentSubjectMapping.html?standard=${status.student.standard}&section=${status.student.section}','_self')"><input type="button" value="INSERT" class="editbtn"></a>
							</c:if>
							<c:if test="${status.completed gt 0}">
								<a onClick="window.open('updateStudentSubjectMapping.html?standard=${status.student.standard}&section=${status.student.section}','_self')"><input type="button" value="VIEW/UPDATE" class="editbtn"></a>
								<a onClick="window.open('listUpdatedStudentSubjectMapping.html?standard=${status.student.standard}&section=${status.student.section}','_self')"><input type="button" value="LIST" class="editbtn"></a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</form:form>
	</c:otherwise>
</c:choose>
</body>
</html>