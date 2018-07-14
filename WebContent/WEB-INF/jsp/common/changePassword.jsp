<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>Change Password</title>
<link rel="icon" href="/icam/images/favicon.ico" type="image/x-icon">
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" /> 


<link rel="stylesheet" 	href="/icam/css/login/fluid-grid16-1100px-login.css" />
<link rel="stylesheet" href="/icam/css/login/eve-styles-login.css" />
<link href="/icam/css/login/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />


    
 <style type="text/css">      
   .slide-out-div {
       padding: 0px;
       width: 400px; 
   }      
   </style>      
</head>

<body>
<form:form method="POST" name="changePassword" id="changePassword" commandName="changePassword" action="updatePassword.html">
		<c:if test="${NewPassword ne null}">
			<a href="logOut.html"><i class="icon icon-share-alt"></i> Log Out</a>
		</c:if>

<c:if test="${message ne null}">
	<c:if test="${message eq 'success'}">
		<div class="successbox" id="successbox" style="visibility: visible;">
				<span id="infomsg" style="visibility: visible;">Password changed successfully!</span>	
		</div>
	</c:if>
	<c:if test="${message eq 'fail'}">
		<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
				<span id="infomsg" style="visibility: visible;">Password not updated!</span>	
		</div>
		<span id="errormsg"></span>	
	</c:if>

</c:if>



<div class="slide-out-div">
	           <article>               
	        	<div class="loginbx">            
	            	<h2><img src="/icam/images/login-icon.png" />&nbsp;Change Password<input type="hidden" id="status" name="status" value="status" /></h2>                
	                <div class="field">  
		                <c:if test="${NewPassword ne null}">
		                <input type="hidden" class="txtfld" id="message" name="message" value=""> 
		                </c:if>              
	                	<label>Current Password :</label>
	                    <input type="password" class="txtfld" id="password" name="password" autocomplete="off">                
	                </div>                
	                <div class="field">                
	                	<label>New Password :</label>
	                    <input type="password" class="txtfld" id="newPassword" name="newPassword">                
	                </div>  
	                <div class="field">                
	                	<label>Retype New Password :</label>
	                    <input type="password" class="txtfld" id="reTypeNewPassword" name="reTypeNewPassword">                
	                </div>              
	                <input type="reset" value="Reset" class="resetbt">                
	                <input type="submit" value="Submit" class="logbt">  
	            </div>
	            	                           
	            <div id="warningbox" class="warningbox" >
	            	<span id="warningmsg" class="warningmsg"></span>
				</div>				
<!-- 	            <img src="/icam/images/login-shadow.png" class="shadow"/>         -->
	        </article>
</div>

</form:form>

</body>
</html>