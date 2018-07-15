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
<title>Employee Salary Details</title>
<link rel="stylesheet" href="/cedugenie/css/finance/staffSalaryDetails.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>

<link rel="stylesheet" href="/cedugenie/Cal/default.css" type="text/css">
<link rel="stylesheet" href="/cedugenie/Cal/jsDatePick_ltr.min.css">
<script src="/cedugenie/Cal/jsDatePick.min.1.3.js"></script>
<script type="text/javascript" src="/cedugenie/Cal/zebra_datepicker.js"></script>

<script src= "/cedugenie/js/finance/staffSalaryDetails.js" type="text/javascript"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Employee Salary Details
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
<form:form method="POST" action="submitStaffSalaryDetails.html">		
	<table cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th>Employee Name<img class="required" src="/cedugenie/images/required.gif" alt="Required"></th>
		<td>
			<select name="employee.employeeCode" id="employeeCode" class="defaultselect">
				<option value="">Select...</option>
				<c:forEach var="staffCode" items="${staffCodeList}">
					<c:if test="${fn:length(fn:trim(staffCode.employeeCode)) != 0}">										
						<option value="<c:out value="${staffCode.employeeCode}"/>"><c:out value="${staffCode.resource.name}"/></option>
					</c:if>
				</c:forEach>
			</select>
		</td>	
	</tr>
	<tr>
		<th>TEMPLATE</th>
		<td>
			<select name="salaryTemplate.salaryTemplateCode" id="salaryTemplate" class="defaultselect" onchange="getGradesForSalaryTemplate(this);">
				<option value="">Select...</option>
				<c:forEach var="tempCode" items="${salaryTemplateList}">
					<c:if test="${fn:length(fn:trim(tempCode.salaryTemplateCode)) != 0}">										
						<option value="<c:out value="${tempCode.salaryTemplateCode}"/>"><c:out value="${tempCode.salaryTemplateName}"/></option>
					</c:if>
				</c:forEach>
				<option value="xx">xx</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>Pay Band</th>		
		<td>
			<select	name="salaryTemplate.fixationOfPay.fixationOfPayCode" class="defaultselect" id="payBand" onchange="getAppointmentsToPostsWithGradePay(this);">
			<option value="" >Please Select</option>								
				 <c:forEach var="fixationOfPay" items="${employee.fixationOfPayList}">									
					<option value="<c:out value="${fixationOfPay.fixationOfPayCode}"/>"><c:out value="${fixationOfPay.fixationOfPayName}"/></option>
				</c:forEach>		
			</select>						
		</td>			
	</tr>
	<tr>
		<th>GRADE PAY</th>				
		<td>
			<select id="gradePay" name="salaryTemplate.fixationOfPay.fixationOfPayDetails.fixationOfPayDetailsCode" class="defaultselect">
				<option VALUE="" >Please Select</option>											
			</select>
		</td>	
		</tr>
	<tr>
		<th>Basic</th>		
		<td>
			<input type="text" class="textfield2" id="basic" name="basic" value="0" onfocus="{this.value='';}" onblur="setZero(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>G P F</th>		
		<td><input type="text" class="textfield2" id="gpf" name="gpf" value="0" onfocus="{this.value='';}" onblur="setZero(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>E D</th>		
		<td><input type="text" class="textfield2" id="ed" name="ed" value="0" onfocus="{this.value='';}" onblur="setZero(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>W C</th>		
		<td><input type="text" class="textfield2" id="wc" name="wc" value="0" onfocus="{this.value='';}" onblur="setZero(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>Free Electric Unit</th>		
		<td><input type="text" class="textfield2"   id="freeElectricCharge" name="freeElectricCharge" value="0" onfocus="{this.value='';}" onblur="setZero(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>I P</th>		
		<td><input type="text" class="textfield2"  id="ip" name="ip" value="0" onfocus="{this.value='';}" onblur="setZero(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>G I P</th>		
		<td><input type="text" class="textfield2"  id="gip" name="gip" value="0" onfocus="{this.value='';}" onblur="setZero(this);" style="text-align: right;"> 
		</td>
	</tr>
	<tr>
		<th>P T</th>		
		<td><input type="text" class="textfield2"  id="pt" name="pt" value="0" onfocus="{this.value='';}" onblur="setZero(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>N P S</th>		
		<td><input type="text" class="textfield2"  id="nps" name="nps" value="0" onfocus="{this.value='';}" onblur="setZero(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>N P S Both</th>		
		<td><input type="text" class="textfield2"  id="npsBoth" name="npsBoth" value="0" onfocus="{this.value='';}" onblur="setZero(this);" style="text-align: right;">
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
		<input type="submit" name="update" id="updateButton" style="visibility: collapse;" class="greenbtn" value="Update" onclick="return staffSalaryDetailsValidation();"> 
		<input type="reset" class="clearbtn" value="clear" />
		<input type="submit" name="submit" class="greenbtn" value="SUBMIT" id="submitButton" onclick="return staffSalaryDetailsValidation();" />
	</div>
</form:form>
</body>
</html>