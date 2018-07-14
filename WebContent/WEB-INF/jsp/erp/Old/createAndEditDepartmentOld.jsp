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
<title>Create And Edit Department</title>
<link rel="stylesheet" href="/icam/css/erp/createDepartment.css" />

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/erp/createDepartment.js"></script>

</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create And Edit Department
		</h1>
	</div>
		
	<form:form name="submitDepartment" action="submitDepartment.html" method="POST">		
		
		<table cellspacing="0" cellpadding="0" class="midsec" border="1">		
			<tr>
				<th colspan="3" style="text-align:center;">
					:: Create New Department ::
				</th>						
			</tr>				
			<tr>
				<th>
					Enter Department Name<img class="required" src="/icam/images/required.gif" alt="Required">
				</th>
				<td>
					<input type="text" class="textfield1" name="departmentName" id="departmentName" >
				</td>
				<td>
					<input type="submit" class="submitbtn" value="SUBMIT" id="submitButton" onclick="return validateDepartmentForm();"/>
				</td>
			</tr>					
		</table>		
	</form:form>
		
		<c:if test="${submitResponse ne null}">				
			<c:if test="${submitResponse eq 'Success'}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">Department Successfully Created</span>	
				</div>
			</c:if>
			<c:if test="${submitResponse eq 'Fail'}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">Department Creation Failed</span>	
				</div>
			</c:if>			
		</c:if>
		<c:if test="${updateResponse ne null}">			
			<c:if test="${updateResponse eq 'Success'}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">Department Successfully Updated</span>	
				</div>
			</c:if>
			<c:if test="${updateResponse eq 'Fail'}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">Department Updatation Failed</span>	
				</div>
			</c:if>		
		</c:if>
			
		<div class="infomsgbox" id="infomsgbox" >
			<span id="infomsg"></span>	
		</div>
						
						
	<c:choose>
		<c:when test="${departmentList eq null || empty departmentList}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No Department Created Yet</span>	
 			</div>
		</c:when>	
		<c:otherwise>			
			<form:form name="editDepart" action="editDepartment.html" method="POST">		
					
				<table id="editDept" cellspacing="0" cellpadding="0" class="midsec1" border="1">	
					<tr>
						<th colspan="2">View / Edit Department</th>
					</tr>				
					<tr>
						<th>Select</th>
						<th>Department Name</th>
					</tr>
					<c:forEach var="dept" items="${departmentList}" varStatus="i">
						<tr>
							<c:choose>
								<c:when test="${dept.objectId eq 'DEPT-OBJ'}">
								<td><input type="radio" id="radioDept${i.index}" value="${dept.departmentCode}" disabled="disabled"/></td>
								<td>
									<input type="hidden" name="oldDepartmentNames" value="${dept.departmentName}">${dept.departmentName}
								</td>
								</c:when> 
								<c:otherwise>
									<td><input type="radio" name="departmentCode" id="radioDept${i.index}" value="${dept.departmentCode}" /></td>
									<td>
										<input type="hidden" name="oldDepartmentNames" value="${dept.departmentName}">
										<input type="text" class="textfield1" id="textDept${i.index}" name="departmentName" value="${dept.departmentName}" disabled="disabled">
									</td>
								</c:otherwise>	
							</c:choose>						
						</tr>	
					</c:forEach>
					<tr>
						<td colspan="2">
							<input type="submit" class="submitbtn" value="SUBMIT" id="submitt" disabled="disabled" onclick="return validateEditDepartmentForm();"/>
							<input type="button" class="clearbtn" id="editButton" value="EDIT" onclick="return removeDisabled('departmentCode','warningbox','warningmsg');">
						</td>
					</tr>
				</table>										
			</form:form>
		</c:otherwise>
	</c:choose>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>				
	</div>
		
</body>
</html>