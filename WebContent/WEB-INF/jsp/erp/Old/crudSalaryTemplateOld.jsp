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
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Add Salary Templates</title>
<link rel="stylesheet" href="/icam/css/erp/crudSalaryTemplate.css" />

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/erp/crudSalaryTemplate.js"></script>

</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Add Salary Templates
		</h1>
	</div>
		
	<form:form name="submitSalaryTemplate" action="submitSalaryTemplate.html" method="POST">		
		
		<table cellspacing="0" cellpadding="0" class="midsec" border="1">		
			<tr>
				<th colspan="3" style="text-align:center;">
					:: Create New Salary Template ::
				</th>						
			</tr>				
			<tr>
				<th>
					Enter Salary Template Type<img class="required" src="/icam/images/required.gif" alt="Required">
				</th>
				<td>					
					<input type="text" class="textfield1" name="salaryTemplateType" id="salaryTemplateType" value="FIXATION OF PAY" readonly="readonly">		
				</td>
			</tr>
			<tr>
				<th>
					Enter Salary Template Name<img class="required" src="/icam/images/required.gif" alt="Required">
				</th>
				<td>
					<input type="text" class="textfield1" name="salaryTemplateName" id="salaryTemplateName" >
				</td>
			</tr>	
			<tr>
				<th>
					Enter Salary Template Description
				</th>
				<td>
					<input type="text" class="textfield1" name="salaryTemplateDesc" id="salaryTemplateDesc" >
				</td>
			</tr>					
			<tr>
				<td>
					<input type="submit" class="submitbtn" value="SUBMIT" id="submitButton" onclick="return validateSalaryTemplateForm();"/>
				</td>
			</tr>					
		</table>
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>				
		</div>				
	</form:form>
		
		<c:if test="${submitResponse ne null}">			
			<c:if test="${submitResponse eq 'Success'}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">Salary Template Successfully Created.</span>	
				</div>
			</c:if>
			<c:if test="${submitResponse eq 'Fail'}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">Salary Template Creation Failed.</span>	
				</div>
			</c:if>			
		</c:if>
		<c:if test="${updateResponse ne null}">	
			<c:if test="${updateResponse eq 'Success'}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">Salary Template Successfully Updated.</span>	
				</div>
			</c:if>
			<c:if test="${updateResponse eq 'Fail'}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">Salary Template Updatation Failed.</span>	
				</div>
			</c:if>			
		</c:if>			
		<div class="infomsgbox" id="infomsgbox">
			<span id="infomsg"></span>	
		</div>
						
						
	<c:choose>
		<c:when test="${salaryTemplateList == null}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No Salary Template Created Yet</span>	
 			</div>
		</c:when>	
		<c:otherwise>			
			<form:form name="editSalaryTemplate" action="editSalaryTemplate.html" method="POST">		
					
				<table id="editSalaryTemplate" cellspacing="0" cellpadding="0" class="midsec1" border="1">	
					<tr>
						<th colspan="4">
							View / Edit Salary Template
						</th>
					</tr>
					<tr>
						<th>
							Select
						</th>
						<th>
							Salary Template Type
						</th>
						<th>
							Salary Template Name
						</th>
						<th>
							Salary Template Desc
						</th>
						
					</tr>
					<c:forEach var="salaryTemplate" items="${salaryTemplateList}" varStatus="i">
						<tr>
							<td>
								<input type="radio" name="salaryTemplateCode" id="radioSalaryTemplate${i.index}" value="${salaryTemplate.salaryTemplateCode}" />
							</td>
							<td>								
								<input type="text" class="textfield1" id="salaryTemplateType${i.index}" name="salaryTemplateType" value="${salaryTemplate.salaryTemplateType}" readonly="readonly">
							</td>	
							<td>
								<input type="text" class="textfield1" id="textSalaryTemplateName${i.index}" name="salaryTemplateName" value="${salaryTemplate.salaryTemplateName}" disabled="disabled"> 
							</td>
							<td>
								<input type="text" class="textfield1" id="textSalaryTemplateDesc${i.index}" name="salaryTemplateDesc" value="${salaryTemplate.salaryTemplateDesc}" disabled="disabled"> 
							</td>						
						</tr>	
					</c:forEach>
					<tr>
						<td colspan="4">
							<input type="submit" class="submitbtn" value="SUBMIT" id="submitt" disabled="disabled" onclick="return validateEditSalaryTemplateForm();"/>
							<input type="button" class="clearbtn" id="editButton" value="EDIT" onclick="return removeDisabled('salaryTemplateCode','warningbox','warningmsg');">
						</td>
					</tr>
				</table>												
			</form:form>
		</c:otherwise>
	</c:choose>
		
</body>
</html>