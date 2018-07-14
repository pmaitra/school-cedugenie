<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Create Staff Retirement" />
<meta name="keywords" content="Create Staff Retirement" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Staff Retirement</title>
<link rel="stylesheet" href="/icam/css/erp/createRetirement.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>

<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">
<link rel="stylesheet" href="/icam/Cal/jsDatePick_ltr.min.css">
<script src="/icam/Cal/jsDatePick.min.1.3.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>

<script src= "/icam/js/erp/staffRetirement.js" type="text/javascript"></script>




</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Staff Retirement
		</h1>
</div>
<form:form method="POST" action="submitStaffRetirement.html">		
	<table cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th>User Id<img class="required" src="/icam/images/required.gif" alt="Required"></th>
		<td>
			<select name="resource.userId" id="staffUserId" class="defaultselect">
				<option value="">Select...</option>
				<c:forEach var="staffUserId" items="${resourceStaffUserIdList}">
					<c:if test="${fn:length(fn:trim(staffUserId.userId)) != 0}">										
						<option value="<c:out value="${staffUserId.userId}"/>"><c:out value="${staffUserId.userId}"/></option>
					</c:if>
				</c:forEach>
			</select>
		</td>	
	</tr>
	<tr>
		<th>Code</th>		
		<td><input type="text" class="textfield2" id="staffCode" name="staffCode" readonly="readonly"></td>
	</tr>
	<tr>
		<th>Name</th>		
		<td><input type="text" class="textfield2" id="staffName" name="staffName" readonly="readonly"></td>
	</tr>
	<tr>
		<th>Employee Type</th>		
		<td><input type="text" class="textfield2"   id="employeeType" name="employeeType.employeeTypeName" readonly="readonly"></td>
	</tr>
	<tr>
		<th>Designation</th>		
		<td><input type="text" class="textfield2"   id="designation" name="designation.designationName" readonly="readonly"></td>
	</tr>
	<tr>
		<th>Job Type</th>		
		<td><input type="text" class="textfield2"  id="jobType" name="jobType.jobTypeName" readonly="readonly"></td>
	</tr>
	<tr>
		<th>Date Of Join</th>		
		<td><input type="text" class="textfield2"  id="doj" name="dateOfJoining" readonly="readonly"></td>
	</tr>
	<tr>
		<th>Date Of Retirement</th>		
		<td><input type="text" class="textfield2"  id="dor" name="dateOfRetirement" readonly="readonly"></td>
	</tr>
	</table>				
	
		
		
	<table id="retirementTable" cellspacing="0" cellpadding="0" class="midsec">
		<tr>
			<th>Mode Of Retirement<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
			<td><select name="modeOfRetirement" id="modeOfRetirement" class="defaultselect">
					<option value="">Select...</option>
					<option value="RETIRE">RETIRE</option>
					<option value="RESIGN">RESIGN</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>Actual Date Of Retirement<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
			<td><input name="actualDateOfRetirement" class="textfield2" id=actualDateOfRetirement readonly="readonly"/></td>
		</tr>
		<tr>
			<th>Reason<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
			<td><textarea name="reasonOfRetirement" id="reasonOfRetirement" class="txtarea"/></textarea> </td>
		</tr>
	</table>		
		
	
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
		<c:if test="${updateResponse ne null}">				
			<c:if test="${updateResponse eq 'Success'}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">Employee Successfully Retired</span>	
				</div>
			</c:if>
			<c:if test="${updateResponse eq 'Fail'}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">Employee Retirement Failed</span>	
				</div>
			</c:if>		
		</c:if>
		<input type="submit" class="submitbtn" value="SUBMIT" id="submitButton" onclick="return staffRetirementValidation();" />
		<input type="reset" class="clearbtn" value="clear" />
	</div>
</form:form>
</body>
</html>