<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%>
<c:url value="/exStudentsPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page To Add Social Category" />
<meta name="keywords" content="Page To Add Social Category" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Search Ex Students</title>

<link rel="stylesheet" href="/icam/css/backoffice/searchExStudents.css" />
<link rel="stylesheet" href="/icam/css/common/pagination.css">
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/backoffice/searchExStudents.js"></script> 
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Search Ex Students
	</h1>
</div>

<form method="POST" action="searchExStudents.html">	
	<table class="midsec" cellspacing="0" cellpadding="0">
		<tr>
			<th>
				First Name::
			</th>
			<td>
				<input type="text" id="firstName" name="firstName" class="textfield1" value="${parameters.firstName}" />
			</td>
			<th>
				Middle Name::
			</th>
			<td>
				<input type="text" id="middleName" name="middleName" class="textfield1" value="${parameters.middleName}" />
			</td>
			<th>
				Last Name::
			</th>
			<td>
				<input type="text" id="lastName" name="lastName" class="textfield1" value="${parameters.lastName}" />
			</td>
		</tr>
		<tr>
			<th>
				Roll Number::
			</th>
			<td>
				<input type="text" id="rollNumber" name="rollNumber" class="textfield1" value="${parameters.rollNumber}" />
			</td>
			<th>
				Year::
			</th>
			<td>
				<input type="text" id="year" name="year" class="textfield1" value="${parameters.year}"  />
			</td>
			<td colspan="2">
				<input type="submit" name="searchSubmit" id="search"  value="Search" class="editbtn">
			</td>
		</tr>
	</table>
	<br>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>	
	<br>
	<c:choose>	
		<c:when test="${pagedListHolder eq null || empty pagedListHolder}">			
		</c:when>
		<c:otherwise>
			<table id="" class="midsec1" cellspacing="0" cellpadding="0">
				<tr>
					<th>Roll</th>
					<th>Name</th>
					<th>Father Name</th>
					<th>Mother Name</th>
					<th>DOB</th>
					<th>Date Of Admission</th>
					<th>Email</th>
					<th>Mobile</th>
					<th>Joining Std.</th>
					<th>Leaving Std.</th>
					<th>Character</th>
				</tr>
				<c:forEach var="student" items="${pagedListHolder.pageList}">
					<tr>
						<td>
							${student.rollNumber}
						</td>
						<td>
							${student.name}
						</td>
						<td>
							${student.fatherName}
						</td>
						<td>
							${student.motherName}
						</td>
						<td>
							${student.dateOfBirth}
						</td>
						<td>							
							${student.dateOfAdmission}
						</td>
						<td>
							${student.email}
						</td>
						<td>
							${student.mobile}
						</td>
						<td>
							${student.joinStandard}
						</td>
						<td>
							${student.lastStandard}
						</td>
						<td>
							${student.studentCharacter}
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="11" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
	<div class="infomsgbox" id="infomsgbox" >
		<span id="infomsg"></span>	
	</div>
</form>
</body>
</html>