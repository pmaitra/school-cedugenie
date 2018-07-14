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
<title>Reset Result</title>

<link rel="stylesheet" href="/icam/css/academics/resetResult.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/academics/resetResult.js"></script> 
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Reset Result
	</h1>
</div>
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" style="visibility: visible;" >
				<span>${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div>
	
	<form name="subjectForm" id="subjectForm" method="POST" action="resetStudentResult.html" >
		<c:choose>
			<c:when test="${standardList eq null || empty standardList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<c:if test="${standardList eq null || empty standardList}">
							<span id="errormsg">Standard Not Found</span>	
						</c:if>
<%-- 						<c:if test="${examList eq null || empty examList}"> --%>
<!-- 							<span id="errormsg">Exams Not Found</span>	 -->
<%-- 						</c:if> --%>
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
							<select id="standard" name="standard" size="1" class="defaultselect">
								<option value="">Select</option>
								<c:forEach var="standard" items="${standardList}" varStatus="i">
									<option value="${standard.standardCode}">${standard.standardName}</option>
								</c:forEach>
							</select>
						</td>
						<th>
							Section ::
						</th>
						<td>
							<select id="section" name="section" size="1" class="defaultselect">
								<option value="">Select</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>
							Subject Name ::
						</th>
						<td>
							<select id="subject" name="subject" size="1" class="defaultselect">
								<option value="">Select</option>
							</select>
						</td>
						<th>
							Exam ::
						</th>
						<td>
							<select id="exam" name="exam" size="1" class="defaultselect">
								<option value="">Select</option>
<%-- 								<c:forEach var="exam" items="${examList}" varStatus="i"> --%>
<%-- 									<option value="${exam.examCode}">${exam.examName}</option> --%>
<%-- 								</c:forEach> --%>
							</select>
						</td>
					</tr>
				</table>
				<div class="infomsgbox" id="infomsgbox1">
					<span id="infomsg1"></span>	
				</div>
				<div id="dialog" title="Confirmation Box">	
					<h3 id="conformationMessage"></h3>
					<input type="submit" value="YES" id="yesbtn" class="submitbtn" onclick="return closePopup();"/>
					<input type="button" value="NO" id="nobtn" class="clearbtn" onclick="return closePopup();"/>
				</div>
				<div class="btnsarea01" id="btnDiv" style="visibility: collapse;">
					<input type="button" onclick="return resetStudentResult();" value="Submit" class="submitbtn"/>
				</div>
			</c:otherwise>	
		</c:choose>
	</form>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
</body>
</html>