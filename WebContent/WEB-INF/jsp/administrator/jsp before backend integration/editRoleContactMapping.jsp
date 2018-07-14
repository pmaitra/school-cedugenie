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
<meta name="description" content="Page to Edit Role Contact Mapping" />
<meta name="keywords" content="Edit Role Contact Mapping" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Edit Role Contact Mapping</title>

<link rel="stylesheet" href="/icam/css/administrator/editRoleContactMapping.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript">    
$(document).ready ( function(){      
	$("#userId0").change(function(){		
		$(this).focusout(function(){
			document.getElementById("warningbox").style.visibility='collapse';
			$.ajax({
		        url: '/icam/getUserNameForId.html',
		        dataType: 'json',
		        data: "userId=" + ($(this).val()),
		        success: function(data) {
		        	if(data != null && data!=""){
		        		($("#name0").val(data));		        		
			     	}else{	
			     		($("#name0").val(""));			     					        	
			     	}
     			}			        
			});
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
	
	
	var userIds=document.getElementsByName("userID");
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
	
	var userIds=document.getElementsByName("userID");
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
	element0.name="userID";
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

$(".userId").each(function(){		
	$(this).autocomplete({	 
		source: '/icam/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val())
});
});
function auto(userId,name){
	$(".userId").each(function(){		
		$(this).autocomplete({	 
			source: '/icam/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val())
	});	
	});	
	$(userId).change(function(){		
		$(this).focusout(function(){
		    $.ajax({
		        url: '/icam/getUserNameForId.html',
		        dataType: 'json',
		        data: "userId=" + ($(this).val()),
		        success: function(data) {
		        	if(data != null && data!=""){
			    		($(name).val(data));
			    		document.getElementById("warningbox").style.visibility='collapse';
			     	}else{	        
			     		$(name).val("");			     					        	
			     	}
        		}
			
			});
    	});
 	 });
}
</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Edit Role Contact Mapping
	</h1>
</div>
<form method="POST" action="editRoleContactMapping.html">
	<c:choose>
		<c:when test="${resourceList eq null || resourceList.size() eq 0 || roleName eq null}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">No Role Contact Mapping Found To Edit</span>	
			</div>						
		</c:when>
	<c:otherwise>
		<table cellspacing="0" cellpadding="0" class="midsec">	
			<tr>
				<th>Role Name <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td>
					<input type="text" id="roleName" readonly="readonly" value="${roleName}" class="textfield1">
					<input type="hidden"  name="roleName" value="${code}" />
				</td>
			</tr>
			<tr>
				<th>Resource Type <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
				<td>					
					<input type="text" readonly="readonly" id="resourceTypeName" value="${resourceTypeName}" class="textfield1">	
					<input type="hidden"  name="resourceTypeName" value="${desc}" />
				</td>
			</tr>
		</table>			
		<table id="userTable" class="midsec1" cellspacing="0" cellpadding="0">
			<tr>				
				<th>User Id<img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<th>Contact Name</th>
				<th>Delete</th>
			</tr>
			<c:forEach var="resource" items="${resourceList}">	
				<tr>
					<td>
						<input type="text" readonly="readonly"  name="userID" id="userId0" class="userId textfield1" value="${resource.userId}">
					</td>
					<td>						
						<c:choose>
		    				<c:when test="${resource.middleName.equals('null')}">
		        				<input type="text" readonly="readonly" id="name0" name="name" size="25" class="name textfield1" value="${resource.firstName} ${resource.lastName}">
		    				</c:when>
		    				<c:otherwise>
		       	 				<input type="text" readonly="readonly" id="name0" name="name" size="25" class="name textfield1" value="${resource.firstName} ${resource.middleName} ${resource.lastName}">
		    				</c:otherwise>
						</c:choose>						
					</td>
					
					<td>
						<img src="/icam/images/minus_icon.png" onclick="deleteRow(this);">	
					</td>
				</tr>								
			</c:forEach>
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
</body>
</html>