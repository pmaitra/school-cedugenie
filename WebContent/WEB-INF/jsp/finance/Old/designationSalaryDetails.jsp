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
<meta name="description" content="Page to Staff Salary Details" />
<meta name="keywords" content="Staff Salary Details" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Designation Salary Details</title>
<link rel="stylesheet" href="/icam/css/finance/designationSalaryDetails.css" />
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

<script src= "/icam/js/finance/designationSalaryDetails.js" type="text/javascript"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Designation Salary Details
		</h1>
</div>
<c:if test="${insertStatus ne null}">				
	<c:if test="${insertStatus eq 'Success'}">
		<div class="successbox" id="successbox" style="visibility:visible;">
			<span id="successmsg" style="visibility:visible;">Employee Salary Details Added</span>	
		</div>
	</c:if>
	<c:if test="${insertStatus eq 'Fail'}">
		<div class="errorbox" id="errorbox" style="visibility:visible;">
			<span id="errormsg" style="visibility:visible;">Problem Occured While Adding Salary Details</span>	
		</div>
	</c:if>		
</c:if>
<c:if test="${updateStatus ne null}">				
	<c:if test="${updateStatus eq 'Success'}">
		<div class="successbox" id="successbox" style="visibility:visible;">
			<span id="successmsg" style="visibility:visible;">Employee Salary Details Updated</span>	
		</div>
	</c:if>
	<c:if test="${updateStatus eq 'Fail'}">
		<div class="errorbox" id="errorbox" style="visibility:visible;">
			<span id="errormsg" style="visibility:visible;">Problem Occured While Updating Salary Details</span>	
		</div>
	</c:if>		
</c:if>
<form:form method="POST" action="saveDesignationSalaryDetails.html">
	<input type="hidden" name="status"	id="status" value="status">
	<table cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th>Designation<img class="required" src="/icam/images/required.gif" alt="Required"></th>
		<td>
			<select name="designation" id="designation" class="defaultselect">
				<option value="">Select...</option>
				<c:forEach var="designation" items="${designationList}">
					<option value="<c:out value="${designation.designationCode}"/>"><c:out value="${designation.designationName}"/></option>
				</c:forEach>
			</select>
		</td>	
	</tr>
	<tr>
		<th>D A</th>		
		<td>
			<input type="text" class="textfield2" id="da" name="da" value="0" onfocus="clearMarks(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>T P T L</th>		
		<td><input type="text" class="textfield2" id="tptl" name="tptl" value="0" onfocus="clearMarks(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>S M A/H M A</th>		
		<td><input type="text" class="textfield2" id="smaHma" name="smaHma" value="0" onfocus="clearMarks(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>M A</th>		
		<td><input type="text" class="textfield2" id="ma" name="ma" value="0" onfocus="clearMarks(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>S A</th>		
		<td><input type="text" class="textfield2"   id="sa" name="sa" value="0" onfocus="clearMarks(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>G P F</th>		
		<td><input type="text" class="textfield2"  id="gpf" name="gpf" value="0" onfocus="clearMarks(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>C P F</th>		
		<td><input type="text" class="textfield2"  id="cpf" name="cpf" value="0" onfocus="clearMarks(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>Meter Charge</th>		
		<td><input type="text" class="textfield2"  id="meterCharge" name="meterCharge" value="0" onfocus="clearMarks(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>UPTO 100 Rate</th>		
		<td><input type="text" class="textfield2"  id="upto100ECRate" name="upto100ECRate" value="0" onfocus="clearMarks(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>Above 100 Rate</th>		
		<td><input type="text" class="textfield2"  id="above100ECRate" name="above100ECRate" value="0" onfocus="clearMarks(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	</table>	
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
		<c:if test="${updateResponse ne null}">				
			<c:if test="${updateResponse eq 'Success'}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">Successfully</span>	
				</div>
			</c:if>
			<c:if test="${updateResponse eq 'Fail'}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">Failed</span>	
				</div>
			</c:if>		
		</c:if>
		<input type="submit" name="update" id="updateButton" style="visibility: collapse;" class="editbtn" value="Update" onclick="return staffSalaryDetailsValidation();"> 
		<input type="reset" class="clearbtn" value="clear" />
		<input type="submit" name="submit" class="submitbtn" value="SUBMIT" id="submitButton" onclick="return staffSalaryDetailsValidation();" />
	</div>
</form:form>
</body>
</html>