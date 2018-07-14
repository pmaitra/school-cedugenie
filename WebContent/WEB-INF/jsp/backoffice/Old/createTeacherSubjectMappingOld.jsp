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
<title>Teacher Subject Mapping</title>

<link rel="stylesheet" href="/icam/css/backoffice/createTeacherSubjectMapping.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script> 
<script type="text/javascript" src="/icam/js/backoffice/createTeacherSubjectMapping.js"></script> 
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Teacher Subject Mapping
	</h1>
</div>
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div>
	
	<form name="subjectForm" id="subjectForm" method="POST" action="editTeacherSubjectMapping.html" >
		<c:choose>
			<c:when test="${subjectGroupList eq null || empty subjectGroupList
			 				|| subjectList eq null || empty subjectList 
			 				|| teacherList eq null || empty teacherList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<c:if test="${subjectGroupList eq null || empty subjectGroupList}">
							<span id="errormsg">Subject Group Not Found</span>	
						</c:if>
						<c:if test="${subjectList eq null || empty subjectList}">
							<span id="errormsg">Subject Not Found</span>	
						</c:if>
						<c:if test="${teacherList eq null || empty teacherList}">
							<span id="errormsg">Teachers Not Found</span>	
						</c:if>
					</div>
				</div>
			</c:when>
			<c:otherwise>			
				<table id="subjectDetailTable" class="midsec" cellspacing="0" cellpadding="0">
					<tr>
						<th>
							Teacher ::
						</th>
						<td>
							<select id="teacher" name="teacher" size="1" class="defaultselect">
								<option value="">Select</option>
								<c:forEach var="teacher" items="${teacherList}" varStatus="i">
									<option value="${teacher.userId}">${teacher.name}</option>
								</c:forEach>
							</select>
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
								<tr>
									<th></th>
									<th>
										${subjectGroup.subjectGroupName}
									</th>
								<c:forEach var="subject" items="${subjectList}" varStatus="k">
									<c:if test="${subject.subjectGroup eq subjectGroup.subjectGroupName}">
										<tr>
											<td>
												<input type="checkbox" class="subjectsCheckBox" id="${subject.subjectName}" name="subjects" value="${subject.subjectName}">
											</td>
											<td>
												${subject.subjectName}
											</td>
										</tr>
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
					<input type="submit" onclick="return saveTeacherSubjectMapping();" value="Submit" class="submitbtn"/>
				</div>
			</c:otherwise>	
		</c:choose>
	</form>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
</body>
</html>