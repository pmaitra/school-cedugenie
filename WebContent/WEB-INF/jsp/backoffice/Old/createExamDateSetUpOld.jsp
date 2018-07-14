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
<meta name="description" content="Page To Add Exam Date" />
<meta name="keywords" content="Page To Add Exam Date" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Exam Date Set Up</title>

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/backoffice/createExamDateSetUp.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">

<script type="text/javascript" src="/icam/js/backoffice/createExamDateSetUp.js"></script>

</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Exam Date Set Up
	</h1>
</div>
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div> 
	
	<form name="examForm" id="examForm" method="POST" action="addExamDateSetUp.html" >
		<c:choose>
			<c:when test="${termList eq null || empty termList
			 				|| examList eq null || empty examList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<c:if test="${termList eq null || empty termList}">
							<span id="errormsg">Term is not assigned</span>	
						</c:if>
						<c:if test="${examList eq null || empty examList}">
							<span id="errormsg">Exams not found</span>	
						</c:if>
					</div>
				</div>
			</c:when>
			<c:otherwise>			
				
				<c:set var="i" value="0" scope="page" />
				<table id="termForExamDetail">					
					<c:forEach var="termObj" items="${termList}" varStatus="j">
						<c:if test="${i eq 0}"><tr></c:if>
						<c:set var="i" value="${i+1}" scope="page" />
						<td>
							<table id="examTable" class="midsec1" cellspacing="0" cellpadding="0">
								<tr>
									<th colspan="3">
										${termObj.term}
									</th>
								</tr>
								<tr>
									<th>
										Exam Name
									</th>
									<th>
										Start Date
									</th>
									<th>
										End Date
									</th>
								 <c:forEach var="examObj" items="${examList}" varStatus="k">
									<c:if test="${termObj.term eq examObj.term}">
										<tr>
											<td>
												${examObj.examName}
												<input type="hidden" id="${examObj.examCode}" name="examCode" value="${examObj.examCode}" class="textfield1"  />
											</td>
											<td>
												<input type="text" id="${examObj.examCode}startDate" name="examStartDate" value="${examObj.examStartDate}" class="textfield1" readonly="readonly" />
											</td>
											<td>
												<input type="text" id="${examObj.examCode}endDate" name="examEndDate" value="${examObj.examEndDate}" class="textfield1" readonly="readonly" />
											</td>
										</tr>
									</c:if>
								</c:forEach> 
							</table>
						</td>
						<c:if test="${i eq 2}">
							</tr>
							<c:set var="i" value="0" scope="page" />
						</c:if>
					</c:forEach>
					<c:if test="${i ne 2}">
						</tr>
					</c:if>
				</table>
				<div id="oldSubjectsDiv"></div>
				<div class="btnsarea01">
					<input type="submit" value="Submit" class="submitbtn"/>
				</div>
			</c:otherwise>	
		</c:choose>
	</form>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
</body>
</html>