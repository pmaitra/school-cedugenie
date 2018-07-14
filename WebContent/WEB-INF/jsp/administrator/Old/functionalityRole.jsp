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
<meta name="keywords" content="Functionality Role" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Functionality For Role </title>

<link rel="stylesheet" href="/icam/css/administrator/createRoles.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script>
$(document).ready(function() { 
	$("#moduleName").change(function() {
		document.getElementById("warningbox").style.visibility='collapse';			
		if($("#moduleName").val()== "null" || $("#moduleName").val()==''){
			return;
		}
		$.ajax({
	        url: '/icam/getRolesForModule.html',
	        dataType: 'json',
	        data: "Module=" + ($("#moduleName").val()),
	        success: function(data){        					
	        	if(data != ''){	
		        	removeOption();
	        	    var roleArray=data.split("#");	        	   
	        	    for (var count=0;count<roleArray.length-1;count++)	{  
		        	    var roleDesc= new Array();
		        	    roleDesc=roleArray[count].split("*"); 
		        	    $("#roleName").append($("<option></option>").val(roleDesc[1]).html(roleDesc[0]));  
	        	    }
	        	}else{
	        		document.getElementById("warningbox").style.visibility='visible';	        		
	        		document.getElementById("warningmsg").innerHTML = "No Role Created For This Module";
	        	}
	        }			        
		}); 		
	});
});
function removeOption(){
	var roleName=document.getElementById("roleName");
	for(var count=roleName.length;count>0;count--){
		roleName.remove(count);
	}
}

function validate() {
	var moduleName=document.getElementById("moduleName").value;
	var roleName=document.getElementById("roleName").value;
	
	if(moduleName=="" || moduleName=='null'){		
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select a Module";		
		return false;
	}
	
	if(roleName=="" || roleName=='null'){		
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select Role Name";		
		return false;
	}
}
</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Functionality - Role Mapping
	</h1>
</div>
			<c:if test="${successMessage ne null}">
				<div class="successbox" id="successboxmsgbox" style="visibility: visible;">
					<span>${successMessage}</span>	
				</div>
			</c:if>
			
			<c:if test="${errorMessage ne null}">
					<div class="errorbox" id="errormsgbox" style="visibility: visible;">
						<span>${errorMessage}</span>	
					</div>
			</c:if>
<form:form action="functionalityRoleMapping.html" method="post" name="functionalityRoleMapping" id="functionalityRoleMapping">	
	<c:choose>
		<c:when test="${moduleList eq null || moduleList.size() eq 0}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">No Module Found</span>	
			</div>
		</c:when>
	<c:otherwise>
		
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
					<select name="roleCode" id="roleName" class="defaultselect">
						<option value="null">Please Select</option>													
					</select>
				</td>
			</tr>			
		</table>		
		
		<div class="btnsarea01">	
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>
			</div>
			<input type="submit" class="submitbtn" value="Next" id="submitButton" onclick="return validate();" />
			<input type="reset" class="clearbtn" value="Clear" />			
		</div>	
	</c:otherwise>
	</c:choose>
	
</form:form>

</body>
</html>