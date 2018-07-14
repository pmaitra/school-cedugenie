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
<meta name="description" content="Page To Upload Question Paper" />
<meta name="keywords" content="Upload Question Paper" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Download Question Paper</title>

<link rel="stylesheet" href="/icam/css/academics/downloadQuestion.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script> 
<script type="text/javascript" src="/icam/js/academics/downloadQuestion.js"></script> 
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Download Question Paper
	</h1>
</div>
	<div class="btnsarea01">
		<c:if test="${uploadQPaperStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${uploadQPaperStatus}</span>	
			</div>
		</c:if>
	</div>
	<form name="subjectForm" id="subjectForm" method="POST" action="uploadQuestionPaper.html" enctype="multipart/form-data">
		<c:choose>
			<c:when test="${academicYearList eq null || empty academicYearList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<c:if test="${academicYearList eq null || empty academicYearList}">
							<span id="errormsg">No Question paper uploaded yet.</span>	
						</c:if>
					</div>
				</div>
			</c:when>
			<c:otherwise>			
				<table id="downloadQP" class="midsec" cellspacing="0" cellpadding="0">
					<tr>
						<th>Academic Year ::</th>
						<td>
							<select id="year" name="academicYear" size="1" class="defaultselect" onchange="getQuestionFolderNames(this, 'standardDir');">
								<option value="">Select</option>
								<c:forEach var="academicYear" items="${academicYearList}" varStatus="i">
									<option value="${academicYear}">${academicYear}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>Standard ::</th>
						<td>
							<select id="standard" name="standard" size="1" class="defaultselect" onchange="getQuestionFolderNames(this, 'examDir');">
								<option value="">Select</option>
							</select>
						</td>
					</tr>					
					<tr>
						<th>Exam ::</th>
						<td>
							<select id="exam" name="exam" size="1" class="defaultselect" onchange="getQuestionFolderNames(this, 'subjectDir');">
								<option value="">Select</option>
							</select>
						</td>
					</tr>					
					<tr>
						<th>Subject ::</th>
						<td>
							<select id="subject" name="subject" size="1" class="defaultselect" onchange="getQuestionFolderNames(this, 'paperFiles');">
								<option value="">Select</option>								
							</select>
						</td>
					</tr>
				</table>
				
				<div id="downloadDiv" class="btnsarea01" style="visibility: collapse;">
					<table id="downloadDoc" class="midsec1" >
						
					</table>
				</div>
				
				
			</c:otherwise>	
		</c:choose>
	</form>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
	
</body>
</html>