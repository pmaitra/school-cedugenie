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
<title>Create User Defined Exams</title>

<link rel="stylesheet" href="/icam/css/academics/createUserdefinedExams.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/academics/createUserdefinedExams.js"></script> 
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create User Defined Exams
	</h1>
</div>
<form name="examForm" id="examForm">
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div>
	<table id="examDetail" class="midsec" class="midsec" cellspacing="0" cellpadding="0">
		<tr>
			<th>
				Exam Name ::
			</th>
			<td>
				<input type="text" id="examName" name="examName" class="textfield1"  />
			</td>
		</tr>
		<tr>
			<th>
				Standard ::
			</th>
			<td>
				<c:set var="i" value="0" scope="page" />
				<c:forEach var="standard" items="${standardList}">					
					<c:set var="i" value="${i+1}" scope="page" />
					<input type="checkbox" name="standard" value="${standard.standardCode}">${standard.standardName}
					<c:if test="${i eq 7}">
						<br><c:set var="i" value="0" scope="page" />
					</c:if>
				</c:forEach>
			</td>
		</tr>
	</table>
	<br>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
	<br>
	<div class="btnsarea01">
		<input type="button" onclick="return addExam();" value="Submit" class="submitbtn"/>
	</div>
	<br>
	<c:choose>
	<c:when test="${examList eq null || empty examList}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Exam Not Created Yet</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<table id="previousExamDetail" class="midsec1" cellspacing="0" cellpadding="0">
		<thead>
		<tr>
			<th colspan="2">
				User Defined Exams
			</th>
			<th>
				Standard
			</th>
		</tr>
		<c:forEach var="exam" items="${examList}" varStatus="i">
		<tr>
			<td>
				<input type="hidden" name="oldExamNames" value="${exam.examCode}">
				<input type="checkbox" id="examCode${i.index}" name="examCodes" value="${exam.examCode}"/>
			</td>
			<td>
				<input type="text" id="examName${i.index}" name="${exam.examCode}Name" class="textfield1" value="${exam.examName}" />
			</td>
			<td>
				<c:set var="j" value="0" scope="page" />
				<c:forEach var="standard" items="${standardList}">
					<c:set var="j" value="${j+1}" scope="page" />
					
					<c:set var="k" value="0" scope="page" />
					<c:forEach var="selectedStandard" items="${exam.standardList}">
						<c:if test="${standard.standardCode eq selectedStandard}">
							<input type="checkbox" checked="checked" name="${exam.examCode}standard" value="${standard.standardCode}">${standard.standardName}
							<c:set var="k" value="1" scope="page" />
						</c:if>
					</c:forEach>
					<c:if test="${k eq 0}">
						<input type="checkbox" name="${exam.examCode}standard" value="${standard.standardCode}">${standard.standardName}
					</c:if>
					
					<c:if test="${j eq 7}">
						<br><c:set var="j" value="0" scope="page" />
					</c:if>
				</c:forEach>
				<c:forEach var="selectedStandard" items="${exam.standardList}">
					<input type="hidden" name="oldStd${exam.examCode}" value="${selectedStandard}">
				</c:forEach>
			</td>
		</tr>
		</c:forEach>
		</thead>		
	</table>
	<br>
	<div class="warningbox" id="warningbox1" >
		<span id="warningmsg1"></span>	
	</div>
	<br>
	<div class="btnsarea01">
		<input type="button" onclick="return editExam();" value="Update" class="submitbtn"/>
	</div>
	</c:otherwise>
	</c:choose>
	<div class="infomsgbox" id="infomsgbox" >
		<span id="infomsg"></span>	
	</div>
</form>
</body>
</html>