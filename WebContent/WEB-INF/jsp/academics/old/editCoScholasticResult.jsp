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
<title>Edit CoScholastic Result</title>

<link rel="stylesheet" href="/icam/css/academics/editCoScholasticResult.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script> 
<script type="text/javascript" src="/icam/js/academics/editCoScholasticResult.js"></script> 
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>



<meta http-equiv="cache-control" content="max-age=0" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="pragma" content="no-cache" />


<meta http-equiv="Expires" content="-1">
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Edit Co-Scholastic Result
	</h1>
</div>
	<form name="" id="coScolasticForm" method="POST" action="editCoScholasticResult.html" >
		<c:choose>
			<c:when test="${null eq descriptiveIndicatorSkillList || empty descriptiveIndicatorSkillList || null eq gradingSystemList || empty gradingSystemList}">
				<div class="btnsarea01" >
					<div class="errorbox" style="visibility: visible;">
						<c:if test="${null eq descriptiveIndicatorSkillList || empty descriptiveIndicatorSkillList}">
							<span id="errormsg">Skills Not Found</span>
						</c:if>
						<c:if test="${null eq gradingSystemList || empty gradingSystemList}">
							<span id="errormsg">Grading System Not Found</span>
						</c:if>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<table id="studentTasble" class="midsec" cellspacing="0" cellpadding="0">
					<tr>
						<th>
							Standard ::
						</th>
						<td>
							${studentList.get(0).standard}
							<input type="hidden" name="standard" value="${student.standard}">
						</td>
						<th>
							Section ::
						</th>
						<td>
							${studentList.get(0).section}
							<input type="hidden" name="section" value="${student.section}">
						</td>
					</tr>
					<tr>
						<th>
							Roll ::
						</th>
						<td>
							<select size="1" class="defaultselect1" id="rollSelect">
								<option value="">Select</option>
								<c:forEach var="student" items="${studentList}">
									<option value="${student.name}">${student.rollNumber}</option>
								</c:forEach>
							</select>
							<input type="hidden" name="rollNumber" id="rollNumber">
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
				<div class="warningbox" id="warningbox0">
					<span>You Are Not Authorised To Update This Page</span>
				</div>
				<c:forEach var="skill" items="${descriptiveIndicatorSkillList}">
					<c:if test="${skill.skillCode eq 'ATTENDANCE'}">
						<table id="studentResult" class="midsec1" cellspacing="0" cellpadding="0">
							<tr>
								<th>
									${skill.skillCode} ::
									<input type="hidden" name="skill" value="${skill.skillCode}">
								</th>
							</tr>
							<tr><td>
								<table id="gradeTable" class="midsec" cellspacing="0" cellpadding="0">
									<c:forEach var="heads" items="${skill.headList}">										
										<tr>
											<th>
												${heads.headCode} ::
												<input type="hidden" name="${skill.skillCode}head" value="${heads.headCode}">
											</th>
											<td>
												<input type="text" name="${heads.headCode}Indicator" id="${heads.headCode}-Indicator" class="textfield1">													
											</td>
											<th>
												Out Of
											</th>
											<td>
												<input type="text" name="${heads.headCode}Grade" id="${heads.headCode}-Grade" class="textfield1">
											</td>
										</tr>
									</c:forEach>
								</table>
							</td></tr>
						</table>
					</c:if>
				</c:forEach>
				<c:forEach var="skill" items="${descriptiveIndicatorSkillList}">
					<c:if test="${skill.skillCode eq 'HEIGHT & WEIGHT'}">
						<table id="studentResult" class="midsec1" cellspacing="0" cellpadding="0">
							<tr>
								<th>
									${skill.skillCode} ::
									<input type="hidden" name="skill" value="${skill.skillCode}" >
								</th>
							</tr>
							<tr><td>
								<table id="gradeTable" class="midsec" cellspacing="0" cellpadding="0">
									<tr>
										<c:forEach var="heads" items="${skill.headList}">
											<th>
												${heads.headCode} ::
												<input type="hidden" name="${skill.skillCode}head" value="${heads.headCode}">
												<input type="hidden" name="${heads.headCode}Indicator" id="${heads.headCode}-Indicator" value="${heads.headCode}">
											</th>
											<td>
												<input type="text" name="${heads.headCode}Grade" id="${heads.headCode}-Grade" class="textfield1">
											</td>
										</c:forEach>
									</tr>
								</table>
							</td></tr>
						</table>
					</c:if>					
				</c:forEach>
				<c:forEach var="skill" items="${descriptiveIndicatorSkillList}">
					<c:if test="${skill.skillCode ne 'ATTENDANCE' && skill.skillCode ne 'HEIGHT & WEIGHT'}">
							<table id="studentResult" class="midsec1" cellspacing="0" cellpadding="0">					
								<tr>
									<th>
										${skill.skillCode} :: Select - ${skill.maxHeads}
										<input type="hidden" name="skill" value="${skill.skillCode}">
									</th>
								</tr>
								<tr><td>
									<table id="gradeTable" class="midsec" cellspacing="0" cellpadding="0">
										<c:forEach var="heads" items="${skill.headList}">
											<tr>
												<th>
													${heads.headCode} ::
													<input type="hidden" name="${skill.skillCode}head" value="${heads.headCode}">
												</th>
												<td>
													<select id="${heads.headCode}-Indicator" name="${heads.headCode}Indicator" size="1" class="defaultselect" onchange="showHideTR(this);">
														<option value="">Select</option>
														<c:forEach var="indicator" items="${heads.indicatorList}">
															<option value="${indicator.indicatorCode}">${indicator.indicatorName}</option>
														</c:forEach>
														<option value="OTHERS">Others</option>
													</select>
												</td>
												<th>
													Grade ::
												</th>
												<td>
													<select id="${heads.headCode}-Grade" name="${heads.headCode}Grade" size="1" class="defaultselect2">
														<option value="">Select</option>
														<c:forEach var="gSys" items="${gradingSystemList}">
															<c:if test="${gSys.type eq 'CO-SCHOLASTIC'}">
																<option value="${gSys.grade}">${gSys.grade}</option>
															</c:if>
														</c:forEach>
													</select>
												</td>
												<th>
													Grade Point ::
												</th>
												<td>
													<input type="text" id="${heads.headCode}-GradePoint" name="${heads.headCode}GradePoint" class="textfield1">
												</td>
											</tr>
											<tr id="${heads.headCode}-Others" style="visibility: collapse;">
												<td colspan="4">
													<textarea class="textarea" placeholder="Specify Others" id="${heads.headCode}-Text" name="${heads.headCode}IndicatorOther"></textarea>
												</td>
											</tr>
										</c:forEach>
									</table>
								</td></tr>
							</table>
						</c:if>
					</c:forEach>
				<div class="btnsarea01" id="submitbuttonDiv" style="visibility: collapse;">
					<input type="submit" value="Update" class="submitbtn"/>
				</div>
			</c:otherwise>	
		</c:choose>
		<c:forEach var="gSys" items="${gradingSystemList}">
			<c:if test="${gSys.type eq 'CO-SCHOLASTIC'}">
				<input type="hidden" name="gradePointRange" id="${gSys.grade}" value="${gSys.range}">
			</c:if>
		</c:forEach>
		<input type="hidden" name="loggedInUser" id="loggedInUser" value="${loggedInUser}">
	</form>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
</body>
</html>