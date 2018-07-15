<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="profitAndLoss" />
<meta name="keywords" content="profitAndLoss" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Salary Break Up Calculator</title>
<link rel="stylesheet" href="/cedugenie/css/finance/salaryBreakUpCalculator.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>

<script type="text/javascript" src="/cedugenie/js/finance/salaryBreakUpCalculator.js"></script>
</head>
<body>
	<div class="ttlarea">	
			<h1>
			<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp; Salary Break Up Calculator
			</h1>
	</div>
	<c:choose>
		<c:when test="${staffCodeList == null}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg"> Staff Code not Found</span>
			</div>	
		</c:when>
	<c:otherwise>
		<form:form>
			<table cellspacing="0" cellpadding="0" class="midsec">
				<tr>
					<th>Employee Code<img class="required" src="/cedugenie/images/required.gif" alt="Required"></th>
					<td>
						<select name="employeeCode" id="employeeCode" class="defaultselect">
							<option value="">Select...</option>
							<c:forEach var="staffCode" items="${staffCodeList}">
								<c:if test="${fn:length(fn:trim(staffCode.employeeCode)) != 0}">										
									<option value="<c:out value="${staffCode.employeeCode}"/>"><c:out value="${staffCode.employeeCode}"/></option>
								</c:if>
							</c:forEach>
						</select>
					</td>	
				</tr>
				</table>		
				<table id="formulaSetUpTable"  cellspacing="0" cellpadding="0" class="midsec1" >
					<tr>
						<th colspan="4" style="color: red">Note: Calculate Salary</th>
					</tr>
					<tr>
						<th colspan="4">EARNING</th>
					</tr>
					<tr>
						<th style="text-align: center">Salary Head</th><th style="text-align: center;">Formula</th><th style="text-align: center;">Amount</th>
					</tr>
						<c:forEach var="salaryBreakUpObject" items="${breakUpList}" varStatus="varEarning">								 
							<c:if test="${salaryBreakUpObject.salaryBreakUpType eq 'EARNING'}">
									<tr>
										<td>
											<input class="textfield2" name="salaryBreakUpName" id="payEar${varEarning.index}" value="${salaryBreakUpObject.salaryBreakUpName}" readonly="readonly">
										</td>
										<td>
											<c:if test="${salaryBreakUpObject.salaryBreakUpName ne 'BASIC'}">
												<input class="salaryBreakUpFormula" name="${salaryBreakUpObject.salaryBreakUpName}" id="eaf${varEarning.index}" value="" readonly="readonly" >
											</c:if>
										</td>
										<c:if test="${salaryBreakUpObject.salaryBreakUpName eq 'BASIC'}">
										<td id="basicAmountPerStaff">
											
										</td>
										</c:if>
										<c:if test="${salaryBreakUpObject.salaryBreakUpName ne 'BASIC'}">
										<td id="af${varEarning.index}"  class="formulaValueClass">
											
										</td>
										</c:if>
									</tr>
							</c:if>								
						</c:forEach>								
						<tr>
							<th colspan="4">DEDUCTION</th>
						</tr>
						<tr>
							<th style="text-align: center">Salary Head</th><th style="text-align: center;">Formula</th><th style="text-align: center;">Amount</th>
						</tr>
						<c:forEach var="salaryBreakUpObject" items="${breakUpList}" varStatus="varEarning">								 
							<c:if test="${salaryBreakUpObject.salaryBreakUpType eq 'DEDUCTION'}">
									<tr>
										<td>
											<input class="textfield2" name="salaryBreakUpName" id="payDed${varEarning.index}" value="${salaryBreakUpObject.salaryBreakUpName}" readonly="readonly">
										</td>
										<td>
											<input class="salaryBreakUpFormula" name="${salaryBreakUpType.salaryBreakUpName}"  id="ead${varEarning.index}" value="" readonly="readonly">	
										</td>
										<td id="ad${varEarning.index}"  class="formulaValueClass" >
										</td>
									</tr>
							</c:if>								
						</c:forEach>									
			</table>
								
			
			<div class="btnsarea01">
				<div class="warningbox" id="warningbox" >
					<span id="warningmsg"></span>	
				</div>
			</div>	
		</form:form>
	</c:otherwise>
	</c:choose>
	
	<div class="box" id="calculatorDiv">
	<!-- <div class="display"><input type="text" readonly="readonly" id="displayFormula" name="displayFormula" class="textfield2"></div> -->
	<div class="keys">
	    <p><input type="button" class="button gray" 
	    value="(" onclick='v("(")'>
		
	    <input type="button" class="button gray" 
	    value=")" onclick='v(")")'>
		
	    <input type="button" class="button black" value="Clear" 
	    onclick='c("")'>
		
	    <input type="button" class="button black" value="C" 
	    onclick='return doBackSpace();'>
		
	    <p><input type="button" class="button black" 
	    value="7" onclick='v("7")'><input type="button" 
	    class="button black" value="8" onclick='v("8")'>
	    <input type="button" class="button black" value="9" 
	    onclick='v("9")'><input type="button" 
	    class="button pink" value=" * " onclick='v(" * ")'></p>
		
	    <p><input type="button" class="button black" 
	    value="4" onclick='v("4")'><input type="button" 
	    class="button black" value="5" onclick='v("5")'>
	    <input type="button" class="button black" value="6" 
	    onclick='v("6")'><input type="button" 
	    class="button pink" value=" - " onclick='v(" - ")'></p>
		
	    <p><input type="button" class="button black" 
	    value="1" onclick='v("1")'><input type="button" 
	    class="button black" value="2" onclick='v("2")'>
	    <input type="button" class="button black" value="3" 
	    onclick='v("3")'><input type="button" 
	    class="button pink" value=" + " onclick='v(" + ")'></p>
		
	    <p><input type="button" class="button black" 
	    value="0" onclick='v("0")'><input type="button" 
	    class="button black" value="." onclick='v(".")'>
	    <input type="button" class="button pink"
	     value=" % " onclick='v(" % ")'><input type="button" 
	    class="button pink" value=" / " onclick='v(" / ")'></p>
	    
	    <p>
	    <c:forEach var="salaryBreakUpObject" items="${breakUpList}" varStatus="varEarning">								 
			<input type="button" class="button gray" 
	    	value="${salaryBreakUpObject.salaryBreakUpName}" onclick='v("${salaryBreakUpObject.salaryBreakUpName}")'>
		</c:forEach>
	    </p>
	  	<input type="button" class="button black" id="calButton" name="calButton" value="=" /> 
	    <p>
	    	<input type="button" class="clearbtn" id="closeButton" name="closeButton" value="Close" />
	    </p>
	</div>
	</div>
	<input type="hidden" id="hidVal" name="hidVal" >
	<input type="hidden" id="hidFormula" name="hidFormula" >
	<input type="hidden" id="hidNextCell" name="hidNextCell" >
	
</body>
</html>