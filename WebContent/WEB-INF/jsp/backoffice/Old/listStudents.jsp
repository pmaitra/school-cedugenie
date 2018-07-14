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

<link rel="stylesheet" href="/icam/css/backoffice/listStudents.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/backoffice/listStudents.js"></script>
<script type="text/javascript" src="/icam/js/common/validateSearch.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Student List
	</h1>
</div>
<div class="btnsarea01">
	<c:if test="${insertUpdateStatus ne null}">
		<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
			<span id="infomsg1">${insertUpdateStatus}</span>	
		</div>
	</c:if>
</div>
<c:if test="${message ne null}">
		<div class="errorbox" id="errorboxmsg" style="visibility: visible;" >
			<span id="errorbox">${message}</span>	
		</div>
</c:if>
<c:choose>	
	<c:when test="${pagedListHolder eq null || empty pagedListHolder}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Student List Not Found</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<form:form id="studentListForm" name="studentListForm" action="getStudentDetailsToEdit.html" method="POST">
			<table id="add"  cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Search Parameter</th>
				<th>Search Value</th>
				<th>
				</th>
			</tr>
			<tr>			
				<td>
					<select id="searchKey" name="searchKey" class="defaultselect">
						<option value="">Please Select</option>
						<option value="RollNumber">Roll Number</option>
						<option value="FirstName">First Name</option>
						<option value="MiddleName">Middle Name</option>
						<option value="LastName">Last Name</option>
						<option value="Standard">Standard</option>
						<option value="Section">Section</option>
						<option value="House">House</option>
						<option value="SecondLanguage">Second Language</option>
					</select>
				</td>
				<td>
					<input type="text" class="textfield2" name="searchValue" id="another"/>
				</td>
				<td>
					<input type="submit" name="searchSubmit" id="search" onclick="return validateSearch('searchKey','another','warningbox','warningmsg');" value="Search" class="editbtn">
				</td>
			</tr>		
		</table>
			
			
			<table  id="candidateTable" cellspacing="0" cellpadding="0" class="midsec1">
				<tr>
					<th></th>
					<th>Roll Number</th>
					<th>Name</th>
					<th>Standard</th>
					<th>Section</th>
					<th>House</th>					
				</tr>
				<c:forEach var="student" items="${pagedListHolder.pageList}">
					<tr onClick="window.open('getStudentDetailsToEdit.html?rollNumber=${student.rollNumber}','_self')" style="cursor:pointer;">
						<td><input type="radio" name="rollNumber" value="${student.rollNumber}"></td>
						<td>
							${student.rollNumber}
						</td>
						<td>
							${student.studentName}
						</td>
						<td>
							${student.standard}
						</td>
						<td>							
							${student.section}
						</td>
						<td>
							${student.house}
						</td>						
					</tr>
				</c:forEach>
				<tr>
	 				<td colspan="8" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
				</tr>
			</table>
			
			<div class="btnsarea01">
				<div class="infomsgbox" id="infomsgbox" >
					<span id="infomsg"></span>	
				</div>
				<div class="warningbox" id="warningbox" >
					<span id="warningmsg"></span>	
				</div>
				<input type="submit" class="submitbtn" value="Submit" onclick="return valradio('rollNumber', 'warningbox', 'warningmsg');">
			</div>
			
		</form:form>
	</c:otherwise>
</c:choose>
</body>
</html>