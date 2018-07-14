<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!--
 * login.html - This html is responsible for creating user login platform.
 * @author vinod.Singh and binod.sharma
 * @version 1.0
-->
<!doctype html>
<html lang="de">
<head> 
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="This html is responsible for creating user login platform" />
<meta name="keywords" content="Change Password" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Manage User Password</title>
<link rel="icon" href="/icam/images/favicon.ico" type="image/x-icon">
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/icam/css/administrator/createAndUpdateUserPassword.css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" 	href="/icam/css/login/fluid-grid16-1100px-login.css" />
<link href="/icam/css/login/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />        
</head>
<div class="ttlarea">
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Manage User Password
	</h1>
</div>
<body>
<form:form method="POST" name="createAndUpdateUserPassword" id="createAndUpdateUserPassword" commandName="createAndUpdateUserPassword" action="createAndUpdateUserPassword.html">

<c:if test="${message ne null}">
	<c:if test="${message eq 'success'}">
		<div class="successbox" id="successbox" style="visibility: visible;">
				<span id="infomsg" style="visibility: visible;">updated successfully!</span>	
		</div>	
</c:if>
	<c:if test="${message eq 'fail'}">
		<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
				<span id="infomsg" style="visibility: visible;">update fail!</span>	
		</div>	
	</c:if>
</c:if>
<table id="tabCreateAndUpdateUserPassword" cellspacing="0" cellpadding="0" class="midsec">		
	     <tr>
	    	 <th> Resource Type</th>
	    	  <td>
	    	  	<select name='resourceType' id="resourceType" class="defaultselect">
	    	  		<option value="">Select...</option>
							<c:forEach var="resourceType" items="${resourceTypeList}">
							<option value="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
							</c:forEach>										
						</select> 
	    	  </td>
	     </tr>       	          
	     <tr>
	    	 <th> User Id</th> 
	    	 <td>	    	 	
				<input type="text" name="userId" id="userId" class="userId textfield1" >  
	    	 </td>
	    </tr>
	    <tr>
	    	 <th> Name</th> 
	    	 <td>
	    	 	 <input type="text" class="textfield1" id="name" name="name" readonly="readonly">  
	    	 </td>
	    </tr>        
	    <tr>
	    	 <th> New Password</th> 
	    	 <td>
	    	 	 <input type="password" class="textfield1" id="newPassword" name="newPassword">
	    	 </td>
	    </tr>                           
<!-- 	            <img src="/icam/images/login-shadow.png" class="shadow"/>         -->
</table>
	<input type="reset" value="Reset" class="clearbtn">                
    <input type="submit" id="submitNewPassword" name="submit" value="Submit New Password" class="submitbtn" disabled="disabled">
    <input type="submit" id="changePassword" name="change" value="Update Password" class="editbtn" disabled="disabled"> 
    <input type="submit" id="delete" name="delete" value="Delete" class="clearbtn" disabled="disabled">
</form:form>
<br/><br/>
	 <div id="warningbox" class="warningbox" >
		   <span id="warningmsg" class="warningmsg"></span>
	 </div>
	 <div class="infomsgbox" id="infomsgbox1">
				<span id="infomsg1" style="visibility: visible;"></span>	
	</div>
 <script type="text/javascript"> 
 
 $("#resourceType").change(function (){
		if(($("#resourceType").val()!='')){			
			$("#userId").autocomplete({
				source: '/icam/getUserIdForResourceType.html?resourceType='+($("#resourceType").val()) 
			});
		}
	});   
 $(document).ready(function() {
	
		$("#name").focus(function(){
		    $.ajax({
				url: '/icam/getUserNameForId.html',
				dataType: 'json',
				data: "userId=" + ($("#userId").val()),
				success: function(data) {
					if(data != null && data!=""){
						($("#name").val(data));
						document.getElementById("warningbox").style.visibility='collapse';
						 $.ajax({			
							    url: '/icam/getUserNameAndStatus.html',
							    	dataType: 'json',
							    	data: "resourceId=" + ($("#userId").val()),		    	
							    	 success: function(data) {
							    	if(data != ""){
							    		var name = document.getElementById("name");
							    		 data=data.split("*");
							    		 document.getElementById("name").value=data[0];
							    		 if(data[1]=='exist'){
							    			 document.getElementById("submitNewPassword").setAttribute("disabled","disabled");
							    			 document.getElementById("changePassword").removeAttribute("disabled");
							    			 document.getElementById("delete").removeAttribute("disabled");
							    			 document.getElementById("infomsgbox1").style.visibility='visible';
											 document.getElementById("infomsg1").innerHTML="Password already exist. Update password";
							    		 }
							    		 if(data[1]=='notExist'){
							    			 document.getElementById("submitNewPassword").removeAttribute("disabled");
							    			 document.getElementById("changePassword").setAttribute("disabled","disabled");
							    			 document.getElementById("delete").setAttribute("disabled","disabled");
							    		 }
							    		 
							    	}	
							    }	
								});
					}
					else{	   
						document.getElementById("warningbox").style.visibility='visible';
						document.getElementById("warningmsg").innerHTML="User Name Not Found";
					}
				}			        
			});
		});
		
	});
   </script>
</body>
</html>