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
<meta name="description" content="Page to Access Type - Contact Mapping" />
<meta name="keywords" content="Access Type - Contact Mapping" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Access Type - Contact Mapping</title>
<link rel="stylesheet" href="/icam/css/administrator/accessTypeContactMapping.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript">    
$(document).ready( function(){      
	$("#name").focus(function (){
		document.getElementById("warningbox").style.visibility='collapse';
		$.ajax({
		  url: '/icam/getUserNameForId.html',
		  dataType: 'json',
		  data: "userId=" + $("#userId").val(),
		  success: function(data) {		
			  	if(data != null && data != ""){				  		
					var name=document.getElementById("name");
					name.value=data;					
		     	}else{			     		
		     		var name=document.getElementById("name");
					name.value= "";
		     		document.getElementById("warningbox").style.visibility='visible';
		    		document.getElementById("warningmsg").innerHTML="User Name Not Found";
		     	}
        	}			        
		});
	});
});
function validate(){
	var accessTypeName=document.getElementById("accessTypeName").value;
	var resourceTypeName=document.getElementById("resourceTypeName").value;
	var userId=document.getElementById("userId").value;
	
	if(accessTypeName=="null" || accessTypeName==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select Access Type Name";
		return false;
	}
	if(resourceTypeName=="null" || resourceTypeName==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select Resource Type Name";
		return false;
	}
	if(userId==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Enter User ID";
		return false;
	}
	return true;
}
</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Access Type - Contact Mapping
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
<form method="POST" action="insertAccessTypeContactMapping.html">
	<c:choose>
		<c:when test="${resource.accessTypeList eq null || resource.accessTypeList.size() eq 0}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">No Contact Access Type Mapping Found</span>	
			</div>						
		</c:when>
		<c:when test="${message ne null}">
			<div class="successbox" id="successbox" style="visibility: visible;">
				<span id="errormsg">${message}</span>	
			</div>						
		</c:when>
	<c:otherwise>
	<table cellspacing="0" cellpadding="0" class="midsec">		
		<tr>
			<th>Access Type Name <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
			<td>
				<select name="accessTypeCode" id="accessTypeName" class="defaultselect">
					<option value="null">Please Select</option>
					<c:forEach var="accessType" items="${resource.accessTypeList}">
					<option value="${accessType.accessTypeCode}">${accessType.accessTypeName}</option>
					</c:forEach>								
				</select>
			</td>
		</tr>
		<tr>
			<th>Resource Type <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
			<td>
				<select name="resourceTypeCode" id="resourceTypeName" class="defaultselect">
					<option value="null">Please Select</option>
					<c:forEach var="resourceType" items="${resource.resourceTypeList}">
					<option value="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
					</c:forEach>								
				</select>
			</td>
		</tr>
		<tr>
			<th>User Id <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
			<td>			
				<input type="text" name="userId" id="userId" class="textfield1">
			</td>
		</tr>
		<tr>
			<th>Contact Name </th>
			<td>
				<input type="text" readonly="readonly" name="name" id="name" class="textfield1">
			</td>
		</tr>
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>			
		<input type="button" value="Clear" class="clearbtn" />
		<input type="submit" class="submitbtn" value="Submit" onclick="return validate();"/>
	</div>
	</c:otherwise>
</c:choose>

</form>


<script type="text/javascript">
$("#resourceTypeName").change(function (){
	if(($("#resourceTypeName").val()!=null)){
		$("#userId").autocomplete({
			source: '/icam/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val())   
	 	});
	}
});
</script>
</body>
</html>