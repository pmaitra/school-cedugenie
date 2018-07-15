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
<title>Disbursement Salary Details</title>
<link rel="stylesheet" href="/cedugenie/css/finance/disbursementSalaryDetails.css" />
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

<script src= "/cedugenie/js/finance/disbursementSalaryDetailsInsert.js" type="text/javascript"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Disbursement Salary Details
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
<form:form method="POST" action="saveSalaryDisbursement.html">
	<input type="hidden" name="status"	id="status" value="status">
	<input type="hidden" name="month"	id="month" value="${month}">
	<input type="hidden" name="resourceType"	id="resourceType" value="${resourceType}">
	<table cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th>Month :: ${month}</th>
		<th>
			<select id="bank" name="bank" size="1" class="defaultselect" onchange="makeChequeReadOnly(this, 'cheque');">
				<c:forEach var="bank" items="${allBanks}" varStatus="i">
					<option value="${bank}">${bank}</option>
				</c:forEach>
			</select>
		</th>
		<th>
			<input type="text" name="cheque" id="cheque" class="textfield2" readonly="readonly">
		</th>
	</tr>
	<tr>
		<th>Employee Code<img class="required" src="/cedugenie/images/required.gif" alt="Required"></th>
		<td>
			<select name="employee" id="employee" class="defaultselect">
				<option value="">Select...</option>
				<c:forEach var="staffCode" items="${staffCodeList}">
					<c:if test="${fn:length(fn:trim(staffCode.employeeCode)) != 0}">										
						<option value="<c:out value="${staffCode.employeeCode}"/>"><c:out value="${staffCode.resource.name}"/></option>
					</c:if>
				</c:forEach>
			</select>
		</td>	
	
		<th>PAY</th>
		<td>
		<input type="text" class="textfield2" id="pay" name="pay" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>			
	</tr>
	<tr>
		<th>GRADE PAY</th>				
		<td>
		<input type="text" class="textfield2" id="gradePay" name="gradePay" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>	
		
		<th>Basic</th>		
		<td>
			<input type="text" class="textfield2" id="basic" name="basic" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>D A</th>		
		<td>
			<input type="text" class="textfield2" id="da" name="da" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	
		<th>E D</th>		
		<td><input type="text" class="textfield2" id="ed" name="ed" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>W C</th>		
		<td><input type="text" class="textfield2" id="wc" name="wc" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	
		<th>Free Electric Unit</th>		
		<td><input type="text" class="textfield2"   id="freeElectricCharge" name="freeElectricCharge" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>I P</th>		
		<td><input type="text" class="textfield2"  id="ip" name="ip" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	
		<th>G I P</th>		
		<td><input type="text" class="textfield2"  id="gip" name="gip" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;"> 
		</td>
	</tr>
	<tr>
		<th>P T</th>		
		<td><input type="text" class="textfield2"  id="pt" name="pt" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	
		<th>N P S</th>		
		<td><input type="text" class="textfield2"  id="nps" name="nps" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>N P S Both</th>		
		<td><input type="text" class="textfield2"  id="npsBoth" name="npsBoth" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	
		<th>T P T L</th>		
		<td><input type="text" class="textfield2" id="tptl" name="tptl" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>S M A/H M A</th>		
		<td><input type="text" class="textfield2" id="smaHma" name="smaHma" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	
		<th>M A</th>		
		<td><input type="text" class="textfield2" id="ma" name="ma" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>S A</th>		
		<td><input type="text" class="textfield2"   id="sa" name="sa" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	
		<th>G P F</th>		
		<td><input type="text" class="textfield2"  id="gpf" name="gpf" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	
	<tr>
		<th>C P F</th>		
		<td><input type="text" class="textfield2"  id="cpf" name="cpf" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
		
		<th>Meter Charge</th>		
		<td><input type="text" class="textfield2"  id="meterCharge" name="meterCharge" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>	
		<th>UPTO 100 Rate</th>		
		<td><input type="text" class="textfield2"  id="upto100ECRate" name="upto100ECRate" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
		
		<th>Above 100 Rate</th>		
		<td><input type="text" class="textfield2"  id="above100ECRate" name="above100ECRate" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	</table>
	<table cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th>Electric Meter Consumed</th>		
		<td><input type="text" class="textfield2"   id="electricMeterConsumed" name="electricMeterConsumed" value="0" onfocus="clearBox(this);" onblur="calculateECharge(this);" style="text-align: right;">
		</td>
		<th>Electric Charge</th>		
		<td><input type="text" class="textfield2"   id="electricCharge" name="electricCharge" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>Arrear</th>		
		<td><input type="text" class="textfield2"   id="arrear" name="arrear" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	
		<th>Misc. Inc</th>		
		<td><input type="text" class="textfield2"   id="miscInc" name="miscInc" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>I.T</th>		
		<td><input type="text" class="textfield2"   id="it" name="it" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	
		<th>Misc. Expenses</th>		
		<td><input type="text" class="textfield2"   id="miscExpenses" name="miscExpenses" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	<tr>
		<th>F.A</th>		
		<td><input type="text" class="textfield2"   id="fa" name="fa" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	
		<th>P.F.L</th>		
		<td><input type="text" class="textfield2"   id="pfl" name="pfl" value="0" onfocus="clearBox(this);" onblur="validateBox(this);" style="text-align: right;">
		</td>
	</tr>
	
	</table>	
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
		<input type="submit" name="update" id="updateButton" style="visibility: collapse;" class="editbtn" value="Update" onclick="return staffSalaryDetailsValidation();"> 
		<input type="reset" class="clearbtn" value="clear" />
		<input type="submit" name="submit" class="greenbtn" value="SUBMIT" id="submitButton" onclick="return staffSalaryDetailsValidation();" />
	</div>
</form:form>
</body>
</html>