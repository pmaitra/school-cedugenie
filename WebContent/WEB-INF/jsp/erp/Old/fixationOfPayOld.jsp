<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Income Tax Salary Slab" />
<meta name="keywords" content="Income Tax Salary Slab" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Fixation Of Pay</title>
<link rel="stylesheet" href="/icam/css/erp/fixationOfPay.css" />

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>

<script type="text/javascript" src="/icam/js/erp/fixationOfPay.js"></script>

</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Fixation Of Pay
		</h1>
	</div>
	<div class="infomsgbox" id="infomsgbox" >
			<span id="infomsg"></span>	
	</div>
	<div>
		<c:if test="${submitResponse ne null}">				
			<c:if test="${submitResponse eq 'Success'}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">Fixation Of Pay Successfully Created</span>	
				</div>
			</c:if>
			<c:if test="${submitResponse eq 'Fail'}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">Fixation Of Pay Creation Failed</span>	
				</div>
			</c:if>		
		</c:if>
	</div>
					
	<form:form name="fixationOfPay" action="submitFixationOfPay.html" method="POST">		
		<table cellspacing="0" cellpadding="0" class="midsec" border="1">
			<tr>
				<th>
					Template Name
				</th>
				<td>
					<select	name="salaryTemplateCode" class="defaultselect"	id="salaryTemplateCode">
						<option value="" >Select...</option>							
						 <c:forEach var="salaryTemplate" items="${salaryTemplateList}"> 
						 	<option value="<c:out value="${salaryTemplate.salaryTemplateCode}"/>"><c:out value="${salaryTemplate.salaryTemplateName}"/></option>
						</c:forEach>
					</select>
				</td>
			</tr>									
			<tr>
				<th>
					Pay Band Name
				</th>
				<td>
					<input type="text" class="textfield1" name="fixationOfPayName" id="fixationOfPayName">
				</td>
			</tr>
			<tr>
				<th>
					Pay Band Start Range
				</th>
				<td>
					<input type="text" class="textfield1" name="fixationOfPayStartRange" id="fixationOfPayStartRange" >
				</td>
			</tr>
			<tr>
				<th>
					Pay Band End Range
				</th>
				<td>
					<input type="text" class="textfield1" name="fixationOfPayEndRange" id="fixationOfPayEndRange" >
				</td>
			</tr>
		</table>		
		
				
		<div id="divPayBandDetails">
			<table id="payBandDetails" cellspacing="0" cellpadding="0" class="midsec1" border="1">					
				<tr>
					<th>
						Appointments to Posts with Grade pay
					</th>
					<th>
						Pay in the Pay Band
					</th>
					<th>
						Total
					</th>
					<th>
						Delete
					</th>
				</tr>
				<tr>
					<td>
						<input type="text" class="textfield1" name = "appointmentToPostsWithGradePay" id="appointmentsToPostsWithGradePay" value="0" onfocus="{this.value='';}" onblur="setZero(this);" style="text-align: right;">
					</td>
					<td>
						<input type="text" class="textfield1" name = "payInThePayBand" id="payInThePayBand" value="0" onfocus="{this.value='';}" onblur="setZero(this);" style="text-align: right;">
					</td>
					<td>
						<input type="text" class="textfield1" name = "totalAmount" id="totalAmount" value="0" onfocus="{this.value='';}" onblur="setZero(this);" style="text-align: right;">
					</td>
					<td>
						
					</td>
				</tr>	
				<tr>
					<td colspan="7">
						<input type="button" class="addbtn" onclick="addrows();">
					</td>
				</tr>
			</table>
			<div class="btnsarea01">
				<div class="warningbox" id="warningbox" >
					<span id="warningmsg"></span>	
				</div>
				<input type="submit" class="submitbtn" value="SUBMIT" id="submitButton" onclick="return validateFixationOfPay();"/>
				<input type="reset" class="clearbtn" value="clear" />				
			</div>
			<input type="hidden" id ="hidVal" name="hidVal" value=""/>
		</div>			
	</form:form>
		
		
</body>
</html>