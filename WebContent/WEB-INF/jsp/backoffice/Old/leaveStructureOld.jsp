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
<meta name="description" content="Page To Add Leave Structure" />
<meta name="keywords" content="Page To Add Leave Structure" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Leave Structure</title>

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/backoffice/leaveStructure.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/backoffice/leaveStructure.js"></script>

</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Assign Leave Structure
	</h1>
</div>
	<div class="btnsarea01">
			<c:if test="${submitResponse ne null}">				
				<c:if test="${submitResponse eq 'Success'}">
					<div class="successbox" id="successbox" style="visibility:visible;">
						<span id="successmsg" style="visibility:visible;">Leave Structure Successfully Updated</span>	
					</div>
				</c:if>
				<c:if test="${submitResponse eq 'Fail'}">
					<div class="errorbox" id="errorbox" style="visibility:visible;">
						<span id="errormsg" style="visibility:visible;">Leave Structure Updation Failed</span>	
					</div>
				</c:if>		
			</c:if>
	</div> 
	<c:choose>	
	<c:when test="${AcademicYear eq null || empty AcademicYear || leavetypes eq null || empty leavetypes}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<c:if test="${AcademicYear eq null || empty AcademicYear}">
							<span id="errormsg">Academic Year is not assigned</span>	
						</c:if>
						<c:if test="${leavetypes eq null || empty leavetypes}">
							<span id="errormsg">Leave Types not found</span>	
						</c:if>
					</div>
				</div>
			</c:when>
	<c:otherwise>
	<form name="form1" action="listLeaveStructure.html" method="post">
<table class="midsec" cellspacing="0" cellpadding="0">
	<tr>
		<th>
			Academic Year ::
		</th>
		<td>
			<select id ="year" name="academicYear.academicYearName" class="defaultselect">
				<option value="">select...</option>
					<c:forEach var="year" items="${AcademicYear}">
						<option value="<c:out value="${year.academicYearName}"></c:out>"><c:out value="${year.academicYearName}"></c:out></option>
					</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<th>
			Employee Type ::
		</th>
		<td>
			<select id ="employeeTypeName" name="employeeType.employeeTypeName" class="defaultselect">
				<option value="">select...</option>
					<c:forEach var="etl" items="${employeeTypeList}">
						<option value="<c:out value="${etl.employeeTypeCode}"></c:out>">
										<c:out value="${etl.employeeTypeName}"></c:out>
						</option>
					</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<th>
			JOb Type::
		</th>
		<td>
			<select id ="jobTypeName" name="jobType.jobTypeName" class="defaultselect">
				<option value="">select...</option>
					<c:forEach var="jt" items="${jobTypeList}">
						<option value="<c:out value="${jt.jobTypeCode}"></c:out>">
										<c:out value="${jt.jobTypeName}"></c:out>
						</option>
					</c:forEach>
			</select>
		</td>
	</tr>
</table>
<br>

	<table id="details" class="midsec1" cellspacing="0" cellpadding="0" style="visibility: collapse;">
		<tr>
			<th>Leave Type</th>
			<th>Duration(Days)</th>
			<th>Limitation</th>
			<th>Encashment</th>
			<th colspan="2">Leave Carry Forward</th>
		</tr>
		<c:forEach var="types" items="${leavetypes}">
		<tr>
			<td>
				<input type="text" id="leaveType" name="leaveType" class="textfield1" value="${types.leaveCategoryName}" readonly="readonly"/>
			</td>
			<td>
				<input type="text" id="${types.leaveCategoryName}leaveDuration" name="leaveDuration" class="textfield2" value="0" style="text-align: right" onfocus="removeZero(this);" onblur="setZero(this);"/>
			</td>
			<td>
				<input type="text" id="${types.leaveCategoryName}leaveLimit" name="leaveLimit" class="textfield2" value="0" style="text-align: right" onfocus="removeZero(this);" onblur="setZero(this);"/>
			</td>
			<td>
				<select id="${types.leaveCategoryName}leaveEncashment" name="leaveEncashment" class="defaultselect1">
					<option value="false">Not Allowed</option>
					<option value="true">Allowed</option>					
				</select>
			</td>				
			<td>
				<select id="${types.leaveCategoryName}leaveCarryForward" name="leaveCarryForward" class="defaultselect1">
					<option value="false">No</option>
					<option value="true">Yes</option>					
				</select>
			</td>				
			<td>
			</td>		
		</tr>
		</c:forEach>			
	</table>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
	
	<br>
	<br>
	<div class="btnsarea01">
		<input type="submit" id="submit" name="submit" value="Submit" class="submitbtn" onclick="return validate();"/>
	</div>
	<input type="hidden" id="status" name="status" value="INSERT">
</form>
</c:otherwise>
</c:choose>
</body>
</html>