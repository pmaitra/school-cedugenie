<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Create Roles" />
<meta name="keywords" content="Create Roles" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Roles</title>

<link rel="stylesheet" href="/icam/css/administrator/createRoles.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/js/administrator/createRoles.js"></script>
<script>

</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Roles
	</h1>
</div>
<form:form action="addRoles.html" method="post" name="createRoles" id="createRoles">	
	<c:choose>
		<c:when test="${moduleList eq null || moduleList.size() eq 0}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">No Module Found To create Role</span>	
			</div>
		</c:when>
	<c:otherwise>
	
	
			<c:if test="${message ne null && message eq 'SUCCESS' }">
				<div class="successbox" id="successboxmsgbox" style="visibility: visible;">
					<span>Role Created Successfully</span>	
				</div>
			</c:if>
			
			<c:if test="${message ne null && message eq 'FAILED' }">
				<div class="errorbox" id="errormsgbox" style="visibility: visible;">
					<span>Failed To Create Role</span>	
				</div>
			</c:if>
		
	
		
	<input type="hidden" id="jsonData">
		<table cellspacing="0" cellpadding="0" class="midsec" id="createRoleTable">	
			<tr>
				<th>Module Name<img class="required" src="/icam/images/required.gif" alt="Required">   </th>			
				<td>
					<select name="moduleCode" id="moduleName" class="defaultselect">
						<option value="null">Please Select</option>
						<c:forEach var="module" items="${moduleList}">
							<option value="${module.moduleCode}">${module.moduleName}</option>
						</c:forEach>								
					</select>
				</td>
			</tr>
			<tr>
				<th>Role Name<img class="required" src="/icam/images/required.gif" alt="Required">   </th>			
				<td>
					<input type="text" name="roleName" id="roleName" class="textfield1">
				</td>
			</tr>
			<tr>
				<th>Role Description  </th>			
				<td>
					<textarea name="roleDescription" id="roleDescription" class="txtarea"></textarea>
				</td>
			</tr>
		</table>
		
		<br/>
		
		<div class="btnsarea01">	
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>
			</div>
			<input type="submit" class="submitbtn" value="Submit" id="submitButton" onclick="return validateRole();" />
			<input type="reset" class="clearbtn" value="Clear" />
			<input type="submit" class="editbtn" value="Delete" id="deleterow" onclick="return deleteRole();" style="visibility:collapse;" />
		</div>
		
		<br/>
		
		<table style="visibility:collapse;" cellspacing="0" cellpadding="0" class="midsec1" id="existingRolesTable">	
			<tr>
				<th colspan="3"> Existing Roles </th>
			</tr>
			<tr>
				<th>Select</th><th>Role Name</th><th>Role Description</th>
			</tr>						
		</table>
		
	</c:otherwise>
	</c:choose>
	
</form:form>

</body>
</html>