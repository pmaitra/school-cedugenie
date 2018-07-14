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
<meta name="description" content="Page to Functionality Role Mapping" />
<meta name="keywords" content="Functionality Role Mapping" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Functionality Role Mapping</title>

<link rel="stylesheet" href="/icam/css/administrator/functionalityRoleMapping.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript">

function selection(thisCheck){	
	if(thisCheck.checked){			
		$(thisCheck).parent().next().find('input').prop('checked', true);   //view	
		$(thisCheck).parent().next().next().find('input').prop('checked', true);	//insert	
		$(thisCheck).parent().next().next().next().find('input').prop('checked', true);		//update	
	}else{		
		$(thisCheck).parent().next().find('input').removeAttr('checked');	//view
		$(thisCheck).parent().next().next().find('input').removeAttr('checked');	//insert
		$(thisCheck).parent().next().next().next().find('input').removeAttr('checked');	//update
	}
}
</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Functionality Role Mapping
	<div style="float: right;">
			<div style="float: left; position: relative;">
				<a href="functionalityRoleMappingExcelDownload.html?moduleCode=${module.moduleCode}&roleCode=${module.moduleDescription}" >
					<button type="button" style="width: 100%;" class="editbtn">Download Excel Sheet</button>
				</a>
			</div>&emsp;			
			<div style="float: right; position: relative;margin-bottom: 2%;">
				<form:form method="POST" action="functionalityRoleMappingExcelUpload.html" enctype="multipart/form-data">
<!-- 					<input class="textfield" type="file" name="imageFile" id="imageFile"> -->
					<input type="hidden" value="${module.moduleCode}" name="moduleCode">
					
					<span id="FileUpload">
					    <input type="file" size="24"  name="imageFile" id="attachment" onchange="document.getElementById('FileField').value = document.getElementById('attachment').value;" />
					    <span id="BrowserVisible"><input type="text" id="FileField" /></span>
					</span>
					<input type="submit" class="submitbtn" value="Submit" name="ExcelUpload" onclick="if(document.getElementById('attachment').value=='')return false; ;">
				</form:form>
			</div>
	</div>
	</h1>		
</div>

<c:choose>
		<c:when test="${errorMessage ne null}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">${errorMessage}</span>	
			</div>						
		</c:when>
	<c:otherwise>
	<c:if test="${message ne null }">
		<div class="successbox" id="successbox" style="visibility: visible;">
			<span id="successmsg">${message}</span>	
		</div>
	</c:if>
	<c:if test="${uploadErrorMessage ne null }">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">${uploadErrorMessage}</span>	
			</div>
	</c:if>
	<form:form action="insertFunctionalityRoleMapping.html" method="post">		
	<input type="hidden" value="${module.moduleCode}" name="moduleCode">	
	<table cellspacing="0" cellpadding="0" class="midsec1">	
		<c:forEach var="role" varStatus="roll" items="${module.roleList}">
			<tr>
				<th colspan="5">Role Name :: ${role.roleName}
				<input type="hidden" value="${role.roleCode}" name="roleCode">
				</th>
			</tr>
			<tr>
				<th>Functionality Name</th>
				<th>Select All</th>
				<th>View</th>
				<th>Insert</th>
				<th>Update</th>
			</tr>
			<c:forEach var="functionality"  items="${role.functionalityList}">
				<tr>			
					<td>
						<input type="text" readonly="readonly" name="functionality${roll.index}" value="${functionality.functionalityName}" class="textfield1">
					</td>
					<td>					
		       	 		<input type="checkbox" onchange="selection(this);">
					</td>			
					<td>
						<c:choose>
		    				<c:when test="${functionality.view.equals(true)}">
		        				<input type="checkbox" name="view" checked="checked" value="${role.roleCode}#${functionality.functionalityName}~VIEW">
		    				</c:when>
		    				<c:otherwise>
		       	 				<input type="checkbox" name="view" value="${role.roleCode}#${functionality.functionalityName}~VIEW">
		    				</c:otherwise>
						</c:choose>									
					</td>
					<td>			
						<c:choose>
		    				<c:when test="${functionality.insert.equals(true)}">
		        				<input type="checkbox" checked="checked" name="insert" value="${role.roleCode}#${functionality.functionalityName}~INSERT">
		    				</c:when>
		    				<c:otherwise>
		       	 				<input type="checkbox" name="insert" value="${role.roleCode}#${functionality.functionalityName}~INSERT">
		    				</c:otherwise>
						</c:choose>									
					</td>
					<td>
						<c:choose>
		    				<c:when test="${functionality.update.equals(true)}">
		        				<input type="checkbox" checked="checked" name="update" value="${role.roleCode}#${functionality.functionalityName}~UPDATE">
		    				</c:when>
		    				<c:otherwise>
		       	 				<input type="checkbox" name="update" value="${role.roleCode}#${functionality.functionalityName}~UPDATE">
		    				</c:otherwise>
						</c:choose>										
					</td>				
				</tr>
			</c:forEach>
		</c:forEach>			
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>		
		<input type="submit" class="submitbtn" value="Submit"/>
		<input type="reset" class="clearbtn" value="Clear"/>
	</div>
	</form:form>
</c:otherwise>
</c:choose>

</body>
</html>