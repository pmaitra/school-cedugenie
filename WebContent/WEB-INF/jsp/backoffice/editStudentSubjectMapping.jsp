<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page To Add Social Category" />
<meta name="keywords" content="Page To Add Social Category" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Student Subject Mapping</title>

<link rel="stylesheet" href="/cedugenie/css/backoffice/editStudentSubjectMapping.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/getElementsByClassName.js"></script> 
<script type="text/javascript" src="/cedugenie/js/backoffice/editStudentSubjectMapping.js"></script> 
<script type="text/javascript" src="/cedugenie/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Student Subject Mapping
	</h1>
</div>
	<form name="subjectForm" id="subjectForm" method="POST" action="editStudentSubjectMapping.html" >
		<c:choose>
			<c:when test="${subjectList eq null || empty subjectList || studentList eq null || empty studentList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<span id="errormsg">
							<c:if test="${subjectList eq null || empty subjectList}">
								Subjects Not Found. 
							</c:if>
							<c:if test="${studentList eq null || empty studentList}">
								Students Not Found.
							</c:if>
						</span>	
					</div>
				</div>
			</c:when>
			<c:otherwise>			
				<table id="subjectDetailTable" class="midsec" cellspacing="0" cellpadding="0">
					<tr>
						<th>
							Standard ::
						</th>
						<td>
							<input type="hidden" name="standard" value="${student.standard}">
							${student.standard}
						</td>
						<th>
							Section ::
						</th>
						<td>
							<input type="hidden" name="section" value="${student.section}">
							${student.section}
						</td>
					</tr>
					<tr>
						<th>
							Roll Number ::
						</th>
						<td>
							<input type="hidden" name="rollNumber" id="rollNumber" value="">
							<select id="studentName" name="studentName" size="1" class="defaultselect">
								<option value="">Select</option>
								<c:forEach var="student" items="${studentList}" varStatus="i">
									<option value="${student.studentName}">${student.rollNumber}</option>
								</c:forEach>
							</select>
						</td>
						<th>
							Name ::
						</th>
						<td>
							<span id="nameSpan"></span>
							<input type="hidden" name="name" id="name">
						</td>
					</tr>
				</table>
				<c:set var="i" value="0" scope="page" />
				<table id="previousStandardDetail" class="midsec2" cellspacing="0" cellpadding="0">
					<c:forEach var="subjectGroup" items="${subjectGroupList}" varStatus="j">
						<c:if test="${i eq 0}"><tr></c:if>
						<c:set var="i" value="${i+1}" scope="page" />
						<td>
							<table id="subjectTable" class="midsec1" cellspacing="0" cellpadding="0">
								<tr><th></th><th>${subjectGroup.subjectGroupName}</th>
								<c:forEach var="subject" items="${subjectList}" varStatus="k">
									<c:if test="${subject.subjectGroup eq subjectGroup.subjectGroupName}">
										<tr><td><input type="checkbox" id="${subject.subjectName}" name="subjects" value="${subject.subjectName}"></td><td>${subject.subjectName}</td></tr>
									</c:if>
								</c:forEach>
							</table>
						</td>
						<c:if test="${i eq 3}">
							</tr>
							<c:set var="i" value="0" scope="page" />
						</c:if>
					</c:forEach>
					<c:if test="${i ne 3}">
						</tr>
					</c:if>
				</table>
				<div id="oldSubjectsDiv"></div>
				<div class="btnsarea01">
					<input type="submit" onclick="return saveStudentSubjectMapping();" value="Submit" class="submitbtn"/>
				</div>
			</c:otherwise>	
		</c:choose>
	</form>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
</body>
</html>