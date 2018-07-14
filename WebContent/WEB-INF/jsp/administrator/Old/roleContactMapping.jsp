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
<meta name="description" content="Page to Role Contact Mapping" />
<meta name="keywords" content="Role Contact Mapping" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Role Contact Mapping</title>

<link rel="stylesheet" href="/icam/css/administrator/roleContactMapping.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript">    
$(document).ready ( function(){      
	$("#name0").focus(function(){
	    $.ajax({
			url: '/icam/getUserNameForId.html',
			dataType: 'json',
			data: "userId=" + ($("#userId0").val()),
			success: function(data) {
				if(data != null && data!=""){
					($("#name0").val(data));
					document.getElementById("warningbox").style.visibility='collapse';
				}
				else{	   
					document.getElementById("warningbox").style.visibility='visible';
					document.getElementById("warningmsg").innerHTML="User Name Not Found";
				}
			}			        
		});
	});
 });


function validate(){
	var roleName=document.getElementById("roleName").value;
	var resourceTypeName=document.getElementById("resourceTypeName").value;
	
	if(roleName=="null" || roleName==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select Role Name";
		return false;
	}
	if(resourceTypeName=="" || resourceTypeName=="null"){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select Resource Type";
		return false;
	}
	
	
	var userIds=document.getElementsByName("userId");
	var names=document.getElementsByName("name");
	
	for(var i=0;i<userIds.length;i++){
		var userId=userIds[i].value;
		var name=names[i].value;
		
		if(userId==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Enter User Id In Row "+(i+1);
			return false;
		}
		if(name==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Enter Contact Name In Row "+(i+1);
			return false;
		}
	}
	
	return true;
}




var index=1;
function addrows(){		
	
	document.getElementById("warningbox").style.visibility='collapse';
	
	var userIds=document.getElementsByName("userId");
	var names=document.getElementsByName("name");
	
	for(var i=0;i<userIds.length;i++){
		var userId=userIds[i].value;
		var name=names[i].value;
		
		if(userId==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Enter User Id In Row "+(i+1);
			return false;
		}
		if(name==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Enter Contact Name In Row "+(i+1);
			return false;
		}
	}
	
	
	
	var table = document.getElementById("userTable");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount-1);           
		         
	var cell0 = row.insertCell(0);		
	var element0 = document.createElement("input");
	element0.type = "text";
	element0.name="userId";
	element0.id="userId"+index;
	element0.className="userId textfield1";	
	cell0.appendChild(element0);	            
	            
	var cell1 = row.insertCell(1);		
	var element1 = document.createElement("input");
	element1.type = "text";
	element1.name="name";
	element1.className="name textfield1";
	element1.id="name"+index;
	element1.size=25;
	element1.setAttribute("readonly","readonly");
	cell1.appendChild(element1);	        
		        
	var cell2= row.insertCell(2);
	var element2 = document.createElement("img");
	element2.setAttribute("src","/icam/images/minus_icon.png");		
	element2.setAttribute("onclick", "deleteRow(this);");		
	cell2.appendChild(element2);		
		
	var userId="#userId"+index;
	var name="#name"+index;
	auto(userId,name);
	index++;
}
 
function deleteRow(obj){	
	document.getElementById("warningbox").style.visibility='collapse';
	var table = document.getElementById("userTable");
	var rowCount = table.rows.length;	
	if(rowCount>3){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}else{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
	}
} 

</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Role Contact Mapping
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
<form method="POST" action="addRoleContactMapping.html">
	<c:choose>
		<c:when test="${resource.roleList eq null || resource.roleList.size() eq 0}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">No Role Found To Map Contacts</span>	
			</div>						
		</c:when>
	<c:otherwise>		
		<table cellspacing="0" cellpadding="0" class="midsec">	
			<tr>
				<th>Role Name <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td>
					<select name="roleName" id="roleName" class="defaultselect">
						<option value="null">Please Select</option>
						<c:forEach var="role" items="${resource.roleList}">
						<option value="${role.roleCode}">${role.roleName}</option>
						</c:forEach>								
					</select>
				</td>
			</tr>
			<tr>
				<th>Resource Type <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td>
					<select name="resourceTypeName" id="resourceTypeName" class="defaultselect">
						<option value="">Please Select</option>
						<c:forEach var="resourceType" items="${resource.resourceTypeList}">
							<option value="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
						</c:forEach>								
					</select>
				</td>
			</tr>
			</table>
			
			<table cellspacing="0" cellpadding="0" class="midsec1" id="userTable">	
				<tr>					
					<th>User Id<img class="required" src="/icam/images/required.gif" alt="Required"></th>
					<th>Contact Name</th>
					<th>Delete</th>
				</tr>
				<tr>					
					<td>
						<input type="text" name="userId" id="userId0" class="userId textfield1" >
					</td>
					<td>
						<input type="text" readonly="readonly" id="name0" name="name" size="25" class="name textfield1">
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
</form>
<script type="text/javascript">


$("#resourceTypeName").change(function (){
	if(($("#resourceTypeName").val()!=null)){			
		$("#userId0").autocomplete({
			source: '/icam/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val()) 
		});
	}
});
function auto(userId,name){	
	$(userId).autocomplete({	 
		source: '/icam/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val())
	});	
	$(name).focus(function(){	
		document.getElementById("warningbox").style.visibility='collapse';
		$.ajax({
			url: '/icam/getUserNameForId.html',
			dataType: 'json',
			data: "userId=" + ($(userId).val()),
		    success: function(data) {
		     	if(data != null && data!=""){
		  			($(name).val(data));
		  			
		     	}else{	  
		     		$(name).val("");
		     		document.getElementById("warningbox").style.visibility='visible';
		    		document.getElementById("warningmsg").innerHTML="User Name Not Found";			        	
		     	}
		  	}
		});
	});
}
</script>
</body>
</html>