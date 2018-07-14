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
<meta name="description" content="Page to Role Access Mapping" />
<meta name="keywords" content="Role Access Mapping" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Role Access Mapping</title>

<link rel="stylesheet" href="/icam/css/administrator/roleAccessMapping.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script>
var count=0;
function addrows(){
	document.getElementById("warningbox").style.visibility='collapse';
	
	var roleName=document.getElementsByName("roleName");
	for(var i=0;i<roleName.length;i++){
		if(roleName[i].value==""){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Select Role Name No. "+(i+1);
			return false;
		}
	}
	
	var table = document.getElementById("roleTable");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount-1);           
 			
 	var cell0 = row.insertCell(0);
	var existingRoles=document.getElementsByName("roleName");	
	
	var newSelect=document.getElementById("roleNameDefault").cloneNode(true);
	newSelect.removeAttribute("style");
	newSelect.setAttribute("name","roleName");
	var newid="newSelect"+count;
	newSelect.id=newid;		
	count++;
	
	for(var x=0;x<existingRoles.length;x++){
		for(var i=0;i<newSelect.length;i++){		
			if(existingRoles[x].value==newSelect.options[i].value)
				newSelect.remove(i);
		}
	}
	cell0.appendChild(newSelect);
	
	var cell1 = row.insertCell(1);
	var element1 = document.createElement("img");
	element1.setAttribute("src","/icam/images/minus_icon.png");		
	element1.setAttribute("onclick", "deleteRow(this);");		
	cell1.appendChild(element1);
}


function deleteRow(obj){	
	var table = document.getElementById("roleTable");
	var rowCount = table.rows.length;	
	if(rowCount>3){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}else{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
	}
}

function validate(){
	
	var accessTypeName=document.getElementById("accessTypeName").value;
	if(accessTypeName==""){
		document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Access Type Name";
		return false;
	}
	
	var roleName=document.getElementsByName("roleName");
	for(var i=0;i<roleName.length;i++){
		if(roleName[i].value==""){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Select Role Name No. "+(i+1);
			return false;
		}
	}
	return true;
}
</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Role Access Mapping
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
<form:form action="addRoleAccessMapping.html" method="POST">	
	<c:choose>
		<c:when test="${roleList eq null || roleList.size() eq 0}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">No Role Found To Map Access Type</span>	
			</div>						
		</c:when>
	<c:otherwise>
		<table cellspacing="0" cellpadding="0" class="midsec">	
			<tr>
				<th>Access Type Name <img class="required" src="/icam/images/required.gif" alt="Required"> </th>		
				<td><input type="text" name="accessTypeName" id="accessTypeName" class="textfield1"></td>
			</tr>
			<tr>
				<th>Access Type Description :: </th>		
				<td><textarea name="accessTypeDesc" id="accessTypeDesc" class="txtarea"></textarea></td>
			</tr>
		</table>
		<table cellspacing="0" cellpadding="0" class="midsec1" id="roleTable">		
			<tr>				
				<th>Role Name<img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<th>Delete</th>
			</tr>
			<tr>		
				<td>
					<select name="roleName" class="roleName defaultselect">
						<option value="">Please Select</option>
						<c:forEach var="role" items="${roleList}">
						<option value="${role.roleCode}">${role.roleName}</option>					
						</c:forEach>								
					</select>
				</td>
				<td>					
					<img src="/icam/images/minus_icon.png" onclick="deleteRow(this);">	
				</td>
			</tr>
			<tr>
				<td colspan="3"><input class="addbtn" type="button"  id="addrow" onclick="addrows();"/></td>
			</tr>
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>
		</div>		
		<input type="submit" id="submit" class="submitbtn" value="Submit" onclick="return validate();" />	
		<input type="reset" class="clearbtn" value="Clear"/>			
	</div>
</c:otherwise>
</c:choose>
</form:form>

<select id="roleNameDefault" style="visibility: collapse;" class = "roleName defaultselect">
	<option value="">Please Select</option>
	<c:forEach var="role" items="${roleList}">
		<option value="${role.roleCode}">${role.roleName}</option>					
	</c:forEach>								
</select>
</body>
</html>