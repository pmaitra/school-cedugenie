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
<title>Add Employee Type</title>
<link rel="stylesheet" href="/cedugenie/css/erp/employeeType.css" />

<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/radio.js"></script>
<script type="text/javascript" src="/cedugenie/js/erp/employeeType.js"></script>

</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Add Employee Type
		</h1>
	</div>
		
	<form:form name="submitEmployeeType" action="submitEmployeeType.html" method="POST">		
		
		<table cellspacing="0" cellpadding="0" class="midsec" border="1">		
			<tr>
				<th colspan="3" style="text-align:center;">
					:: Create New Employee Type ::
				</th>						
			</tr>				
			<tr>
				<th>
					Enter Employee Type<img class="required" src="/cedugenie/images/required.gif" alt="Required">
				</th>
				<td>
					<input type="text" class="textfield1" name="employeeTypeName" id="employeeTypeName" >
				</td>
				<td>
					<input type="submit" class="submitbtn" value="SUBMIT" id="submitButton" onclick="return validateEmployeeTypeForm();"/>
				</td>
			</tr>					
		</table>		
	</form:form>
		
		<c:if test="${submitResponse ne null}">			
			<c:if test="${submitResponse eq 'Success'}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">Employee Type Successfully Created</span>	
				</div>
			</c:if>
			<c:if test="${submitResponse eq 'Fail'}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">Employee Type Creation Failed</span>	
				</div>
			</c:if>		
		</c:if>
		<c:if test="${updateResponse ne null}">				
				<c:if test="${updateResponse eq 'Success'}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">Employee Type Successfully Updated</span>	
				</div>
			</c:if>
			<c:if test="${updateResponse eq 'Fail'}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">Employee Type Updatation Failed</span>	
				</div>
			</c:if>			
		</c:if>
			
		<div class="infomsgbox" id="infomsgbox" >
			<span id="infomsg"></span>	
		</div>
						
						
	<c:choose>
		<c:when test="${employeeTypeList == null}">
			<div class="errorbox" id="errorbox" style="visibility:visible;">
				<span id="errormsg">No Employee Type Created Yet</span>	
 			</div>
		</c:when>	
		<c:otherwise>			
			<form:form name="editEmpType" action="editEmployeeType.html" method="POST">		
					
				<table id="editDept" cellspacing="0" cellpadding="0" class="midsec1" border="1">	
					<tr>
						<th colspan="2">
							View / Edit Employee Type
						</th>
					</tr>				
					<tr>
						<th>
							Select
						</th>
						<th>
							Employee Type
						</th>
					</tr>
					<c:forEach var="empType" items="${employeeTypeList}" varStatus="i">
						<tr>
							<td><input type="radio" name="employeeTypeCode" id="radioEmpType${i.index}" value="${empType.employeeTypeCode}" /></td>
							<td>
								<input type="text" class="textfield1" id="textEmpType${i.index}" name="employeeTypeName" value="${empType.employeeTypeName}" disabled="disabled"> 
							</td>							
						</tr>	
					</c:forEach>
					<tr>
						<td colspan="2">
							<input type="submit" class="submitbtn" value="SUBMIT" id="submitt" disabled="disabled" />
							<input type="button" class="clearbtn" id="editButton" value="EDIT" onclick="return removeDisabled('employeeTypeCode','warningbox','warningmsg');">
						</td>
					</tr>
				</table>
				<div class="btnsarea01">
					<div class="warningbox" id="warningbox" >
						<span id="warningmsg"></span>	
					</div>				
				</div>
										
			</form:form>
		</c:otherwise>
	</c:choose>
		
</body>
</html>