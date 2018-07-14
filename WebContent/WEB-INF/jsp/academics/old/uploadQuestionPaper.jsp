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
<title>Upload Question Paper</title>

<link rel="stylesheet" href="/icam/css/academics/uploadQuestion.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script> 
<script type="text/javascript" src="/icam/js/academics/uploadQuestion.js"></script> 
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Upload Question Paper
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
			<c:when test="${academicYearList eq null || empty academicYearList
			 				|| standardList eq null || empty standardList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
<%-- 						<c:if test="${examList eq null || empty examList}"> --%>
<!-- 							<span id="errormsg">Exam Not Found</span>	 -->
<%-- 						</c:if> --%>
						<c:if test="${academicYearList eq null || empty academicYearList}">
							<span id="errormsg">Academic Year Not Found</span>	
						</c:if>
						<c:if test="${standardList eq null || empty standardList}">
							<span id="errormsg">Standard Not Found</span>
						</c:if>
					</div>
				</div>
			</c:when>
			<c:otherwise>			
				<table id="uploadQP" class="midsec" cellspacing="0" cellpadding="0">
					<tr>
						<th>Academic Year ::</th>
						<td>
							<select id="year" name="academicYear" size="1" class="defaultselect">
								<option value="">Select</option>
								<c:forEach var="acadYear" items="${academicYearList}" varStatus="i">
									<option value="${acadYear.academicYearCode}">${acadYear.academicYearName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th>Standard ::</th>
						<td>
							<select id="standard" name="standard" size="1" class="defaultselect">
								<option value="">Select</option>
								<c:forEach var="standard" items="${standardList}" varStatus="i">
									<option value="${standard.standardCode}">${standard.standardName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>					
					<tr>
						<th>Exam ::</th>
						<td>
							<select id="exam" name="exam" size="1" class="defaultselect">
								<option value="">Select</option>
								<option value="BOARD">BOARD</option>
<%-- 								<c:forEach var="exam" items="${examList}" varStatus="i"> --%>
<%-- 									<option value="${exam.examCode}">${exam.examName}</option> --%>
<%-- 								</c:forEach> --%>
							</select>
						</td>
					</tr>					
					<tr>
						<th>Subject ::</th>
						<td>
							<select id="subject" name="subject" size="1" class="defaultselect">
								<option value="">Select</option>								
							</select>
						</td>
					</tr>
				</table>
				
				<div id="uploadDiv" class="btnsarea01" style="visibility: collapse;">
					<table id="uploadDoc" class="midsec1" >
						<tr>
				 			<th colspan="2">Upload Question Papers</th>
				 		</tr>
				 		<tr>			
				 			<td><input type="file" name="uploadFile.fileData" id="fileData0"/></td>
				 			<td><input id="addFile2" class="addFileClassName addbtn" type="button" /></td>
						</tr>
					</table>
					<input type="submit" onclick="return validatePage();" value="Submit" class="submitbtn"/>
				</div>
				
				
			</c:otherwise>	
		</c:choose>
	</form>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
	
</body>
</html>