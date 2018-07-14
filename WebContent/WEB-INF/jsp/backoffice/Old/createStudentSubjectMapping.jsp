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

<link rel="stylesheet" href="/icam/css/backoffice/createStudentSubjectMapping.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/checkAllCheckBox.js"></script>
<script type="text/javascript" src="/icam/js/backoffice/createStudentSubjectMapping.js"></script> 
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Student Subject Mapping
		
		<div style="float: right;">
			<div style="float: left; position: relative;">
				<a class="bookDetails" href="downloadStudentSubjectMappExcel.html?standard=${student.standard}&section=${student.section}">
					<button type="button" style="width: 100%;" class="editbtn">Download Excel Sheet</button>
				</a>
			</div>&emsp;			
			<div style="float: right; position: relative;margin-bottom: 1%;">
				<form:form id="safcontents" name="safcontents" method="POST" action="uploadStudentSubjectMappExcel.html" enctype="multipart/form-data">
					<span id="FileUpload">
					    <input type="file" size="24"  name="imageFile" id="attachment" onchange="document.getElementById('FileField').value = document.getElementById('attachment').value;" />
					    <span id="BrowserVisible"><input type="text" id="FileField" /></span>
					</span>
					<input type="submit" class="editbtn" value="Submit" onclick="if(document.getElementById('attachment').value=='')return false; ;">
				</form:form>
			</div>
		</div>
		
	</h1>
</div>	
	<form name="subjectForm" id="subjectForm" method="POST" action="insertStudentSubjectMapping.html" >
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
						<th style="text-align: center;">
							Standard ::
							<input type="hidden" name="standard" value="${student.standard}">
							${student.standard}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							Section ::
							<input type="hidden" name="section" value="${student.section}">
							${student.section}
						</th>
					</tr>
					<tr>
						<th>
							<table id="subjectTable" class="midsec1" cellspacing="0" cellpadding="0">
								<tr><th colspan="4">Roll Number & Name &nbsp;&nbsp;&nbsp;<input type="checkbox" onchange="checkAllCheckBox(this, 'rollNumber');">All</th></tr>
								
								<c:set var="j" value="0" scope="page" />
								<c:forEach var="student" items="${studentList}">
									<c:if test="${j eq 0}"><tr></c:if>
										<c:set var="j" value="${j+1}" scope="page" />
										<td style="color: black;"><input type="checkbox" class="rollNumber" name="rollNumber" value="${student.rollNumber}">${student.rollNumber} (${student.studentName})</td>
									<c:if test="${j eq 4}">
										</tr>
										<c:set var="j" value="0" scope="page" />
									</c:if>
								</c:forEach>
								<c:if test="${j ne 4}">
									</tr>
								</c:if>
							</table>
						</th>
					</tr>
				</table>
				<c:set var="i" value="0" scope="page" />
				<table id="previousStandardDetail" class="midsec2" cellspacing="0" cellpadding="0">
					<c:forEach var="subjectGroup" items="${subjectGroupList}" varStatus="j">
						<c:if test="${i eq 0}"><tr></c:if>
						<c:set var="i" value="${i+1}" scope="page" />
						<td>
							<table id="subjectTable" class="midsec1" cellspacing="0" cellpadding="0">
								<tr><th><input type="checkbox" onchange="checkAllCheckBox(this, '${subjectGroup.subjectGroupName}');"></th><th>${subjectGroup.subjectGroupName}</th>
								<c:forEach var="subject" items="${subjectList}" varStatus="k">
									<c:if test="${subject.subjectGroup eq subjectGroup.subjectGroupName}">
										<tr><td><input type="checkbox" class="${subjectGroup.subjectGroupName}" id="${subject.subjectName}" name="subjects" value="${subject.subjectName}"></td><td>${subject.subjectName}</td></tr>
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