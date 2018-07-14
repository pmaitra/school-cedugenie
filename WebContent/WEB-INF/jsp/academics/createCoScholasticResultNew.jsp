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
<title>Upload Result</title>

<link rel="stylesheet" href="/cedugenie/css/academics/createResult.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/getElementsByClassName.js"></script> 
<script type="text/javascript" src="/cedugenie/js/academics/createCoScholasticResultNew.js"></script> 
<script type="text/javascript" src="/cedugenie/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Upload Result
	</h1>
</div>
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" style="visibility: visible;" >
				<span>${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div>
	
	<form name="subjectForm" id="subjectForm" method="POST" action="saveCoScholasticResultNew.html" >
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
						<th id = "termhead">
							Term ::
						</th>
						<td>
							<select id="term" name="term" size="1" class="defaultselect">
								<option value="">Select</option>

							</select>
							
						</td>
					</tr>
					<tr>
						<th>
							Student ::
						</th>
						<td>
							<select id="studentName" name="studentName" size="1" class="defaultselect">
								<option value="">Select</option>

							</select>
							
						</td>
					</tr>
					
				</table>
				
				<table id="gradeTable" class="midsec" cellspacing="0" cellpadding="0">
									<th>
										<strong>Co Scholastic areas [ona 3 point(A-C)grading scale]</strong>
									</th>
									<c:forEach var="skill" items="${descriptiveIndicatorSkillList}">									
										<tr>
											<th>
												${skill.skillCode}
												<input type="hidden" name="skill" value="${skill.skillCode}" >
											</th>
											
											<th>
												Grade ::
											</th>
											<td>
												<select id="${skill.skillCode}-Grade" name="${skill.skillCode}Grade" size="1" class="defaultselect2">
													<option value="">Select</option>
													<option value="A">A</option>
													<option value="B">B</option>
													<option value="C">C</option>
													
												</select>
											</td>
											
										</tr>
										
									</c:forEach>
								</table>
				<div class="infomsgbox" id="infomsgbox1">
					<span id="infomsg1"></span>	
				</div>
				<!-- <table id="studentResult" class="midsec1" cellspacing="0" cellpadding="0">					
					<tr><th>Roll Number</th><th>Theory(Pass)</th><th>Practical(Pass)</th><th>Total(Pass)</th><th>Pass/Fail</th></tr>
				</table> -->
				<input type="hidden" id="loggedInUser" value="${loggedInUser}">
			<!-- 	<input type="hidden" name="insertUpdate" id="insertUpdate" value="INSERT"> -->
				<div class="btnsarea01" id="btnDiv" >
					<input type="submit" onclick="return saveCoScholasticResult();" value="Submit" class="submitbtn"/>
				</div>
			</c:otherwise>	
		</c:choose>
	</form>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
</body>
</html>