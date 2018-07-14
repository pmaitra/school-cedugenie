
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Add Designation" />
<meta name="keywords" content="Add Designation" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Add Designation</title>
<link rel="stylesheet" href="/icam/css/erp/designation.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/erp/designation.js"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Add Designation
		</h1>
</div>	
	<form:form name="submitDesignation" id="submitDesignation" action="submitDesignation.html" method="POST">
		<table cellspacing="0" cellpadding="0" class="midsec" border="1">		
			<tr>
				<th colspan="3" style="text-align:center;">
					:: Create New Designation ::
				</th>
			</tr>	
			<tr>
				<th>
					Employee Type<img class="required" src="/icam/images/required.gif" alt="Required">
				</th>
				<td>
					<select id="employeeTypeName" name="employeeType.employeeTypeName" class="defaultselect" onchange="getDesignationForResourceType(this);">
						<option VALUE="">Please Select</option>
						<c:forEach var="employeeType" items="${employeeTypeList}" >
							<option VALUE="${employeeType.employeeTypeCode}">${employeeType.employeeTypeName}</option>
						</c:forEach>															
					</select>
				</td>
			</tr>			
			<tr>
				<th>
					Enter Designation Name<img class="required" src="/icam/images/required.gif" alt="Required">
				</th>
				<td>
					<input type="text" class="textfield1" name="designationName" id="designationName" >
				</td>
				<td>
					<input type="submit" class="submitbtn" value="SUBMIT" id="submitButton" onclick="return validateDesignationForm();"/>
				</td>
			</tr>					
		</table>		
	</form:form>
	
		<c:if test="${submitResponse ne null}">				
			<c:if test="${submitResponse eq 'Success'}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">Designation Successfully Created</span>	
				</div>
			</c:if>
			<c:if test="${submitResponse eq 'Fail'}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">Designation Creation Failed</span>	
				</div>
			</c:if>		
		</c:if>
		<c:if test="${updateResponse ne null}">				
			<c:if test="${updateResponse eq 'Success'}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">Designation Successfully Updated</span>	
				</div>
			</c:if>
			<c:if test="${updateResponse eq 'Fail'}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">Designation Updatation Failed</span>	
				</div>
			</c:if>		
		</c:if>
			
		<div class="infomsgbox" id="infomsgbox" >
			<span id="infomsg"></span>	
		</div>
		
	<form:form action="editDesignation.html" method="post">
		<c:choose>
		<c:when test="${designationList eq null || empty designationList}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No Designation Created Yet</span>	
 			</div>
		</c:when>	
		<c:otherwise>			
			<form:form name="editDepart" action="editDepartment.html" method="POST">					
				<table id="editDept" cellspacing="0" cellpadding="0" class="midsec1" border="1">	
					<tr>
						<th colspan="3">
							View / Edit Designation
						</th>
					</tr>				
					<tr>
						<th>
							Select
						</th>
						<th>
							Designation Name
						</th>
						<th>
							Employee Type
						</th>
					</tr>
					<c:forEach var="designation" items="${designationList}" varStatus="i">
						<tr>
							<td><input type="radio" name="designationCode" id="radioDesig${i.index}" value="${designation.designationCode}" /></td>
							<td>
								<input type="hidden" name="oldDesignationNames" value="${designation.designationName}">
								<input type="text" class="textfield1" id="textDesig${i.index}" name="designationName" value="${designation.designationName}" disabled="disabled"> 
							</td>							
							<td>
								<input type="hidden" id="select${i.index}" value="${designation.employeeType.employeeTypeCode}">
								<select id="employeeTypeName${i.index}" name="employeeType.employeeTypeName" class="defaultselect" onchange="getDesignationForResourceType(this);" disabled="disabled">								
									<c:forEach var="employeeType" items="${employeeTypeList}">
										<option value="${employeeType.employeeTypeCode}" ${employeeType.employeeTypeCode eq designation.employeeType.employeeTypeCode ? 'selected=selected' : ''}>${employeeType.employeeTypeName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>	
					</c:forEach>
						<tr>
							<td colspan="3">
								<input type="submit" class="submitbtn" value="SUBMIT" id="submitt" disabled="disabled" onclick="return validateEditDesignationForm();"/>
								<input type="button" class="clearbtn" id="editButton" value="EDIT" onclick="return removeDisabled('designationCode','warningbox','warningmsg');">
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
</form:form>	
</body>
</html>