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
<title>Create Co-Scholastic Result</title>

<link rel="stylesheet" href="/cedugenie/css/academics/createCoScholasticResult.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/getElementsByClassName.js"></script> 
<script type="text/javascript" src="/cedugenie/js/academics/createCoScholasticResult.js"></script> 
<script type="text/javascript" src="/cedugenie/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Co-Scholastic Result
	</h1>
</div>
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" style="visibility: visible;" >
				<span>${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div>
	
	<form name="" id="coScolasticForm" method="POST" action="saveCoScholasticResult.html" >
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
							Name ::
						</th>
						<td>
							${student.name}
							<input type="hidden" name="name" value="${student.name}">
						</td>
						<th>
							Standard ::
						</th>
						<td>
							${student.standard}
							<input type="hidden" name="standard" value="${student.standard}">
						</td>
					</tr>
					<tr>
						<th>
							Section ::
						</th>
						<td>
							${student.section}
							<input type="hidden" name="section" value="${student.section}">
						</td>
						<th>
							Roll ::
						</th>
						<td>
							${student.rollNumber}
							<input type="hidden" name="rollNumber" value="${student.rollNumber}">
						</td>
					</tr>
				</table>
				<c:forEach var="skill" items="${descriptiveIndicatorSkillList}">
					<c:if test="${skill.skillCode eq 'ATTENDANCE'}">
						<table id="studentResult" class="midsec1" cellspacing="0" cellpadding="0">
							<tr>
								<th>
									${skill.skillCode} ::
									<input type="hidden" name="skill" value="${skill.skillCode}" >
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
												<input type="text" name="${heads.headCode}Indicator" class="textfield1">
											</td>
											<th>
												Out Of
											</th>
											<td>
												<input type="text" name="${heads.headCode}Grade" class="textfield1">
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
												<input type="hidden" name="${heads.headCode}Indicator" value="${heads.headCode}">
											</th>
											<td>
												<input type="text" name="${heads.headCode}Grade" class="textfield1">
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
												<textarea class="textarea" placeholder="Specify Others" name="${heads.headCode}IndicatorOther"></textarea>
											</td>
										</tr>
									
									</c:forEach>
								</table>
							</td></tr>
						</table>
					</c:if>
				</c:forEach>
				<div class="btnsarea01">
					<input type="submit" value="Save & Continue" class="submitbtn"/>
				</div>
				<c:forEach var="gSys" items="${gradingSystemList}">
					<c:if test="${gSys.type eq 'CO-SCHOLASTIC'}">
						<input type="hidden" name="gradePointRange" id="${gSys.grade}" value="${gSys.range}">
					</c:if>
				</c:forEach>
			</c:otherwise>	
		</c:choose>
	</form>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
</body>
</html>