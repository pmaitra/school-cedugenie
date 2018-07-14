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
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="This html is responsible for creating user login platform" />
<meta name="keywords" content="Login SCHOOL Management" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>SCHOOL MANAGEMENT</title>
<link rel="icon" href="/icam/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet"
	href="/icam/css/login/fluid-grid16-1100px-login.css" />
<link rel="stylesheet" href="/icam/css/login/eve-styles-login.css" />
<link href="/icam/css/login/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" /> 
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/icam/css/login/clock/styles.css" />
<link rel="stylesheet" type="text/css" href="/icam/css/login/clock/jquery.tzineClock.css" />
 
<script src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script src= "/icam/js/login/login.js" type="text/javascript"></script>  

<link rel="stylesheet" type="text/css" href="/icam/css/common/calendar/jquery-ui-1.10.4.custom.min.css" />
<script src="/icam/js/common/calendar/jquery-ui.custom.min.js"></script>
<script src="/icam/js/common/calendar/jquery.min.js"></script>

<style type="text/css">      
   .slide-out-div {
       padding: 0px;
       width: 400px; 
       font-family: bahamas;
   } 
   
   .slide-out-div-about{
       padding: 15px;
       width:450px;
       background: #ccc;
       border: 1px solid #29216d; 
       font-family: bahamas;
       color: #445fa2;
       text-align: justify;
       
   }
     
   .noticeBoard {
	  -moz-box-shadow:  1px 1px 4px 2px  #6d8832;  
	  -webkit-box-shadow: 1px 1px 4px 2px  #6d8832;  
	  box-shadow:1px 1px 4px 2px  #6d8832;  
	  border-radius: 7px;
	  margin-right: 5%;
}

.noticeDate {
	content: "• ";	
}

.noticeDate {
    -webkit-animation-name: blinker;
    -webkit-animation-duration: 4s;
    -webkit-animation-timing-function: linear;
    -webkit-animation-iteration-count: infinite;

    -moz-animation-name: blinker;
    -moz-animation-duration: 4s;
    -moz-animation-timing-function: linear;
    -moz-animation-iteration-count: infinite;

    animation-name: blinker;
    animation-duration: 4s;
    animation-timing-function: linear;
    animation-iteration-count: infinite;
}

@-moz-keyframes blinker {  
    0% { opacity: 1.0; }
    50% { opacity: 0.0; }
    100% { opacity: 1.0; }
}

@-webkit-keyframes blinker {  
    0% { opacity: 1.0; }
    50% { opacity: 0.0; }
    100% { opacity: 1.0; }
}

@keyframes blinker {  
    0% { opacity: 1.0; }
    50% { opacity: 0.0; }
    100% { opacity: 1.0; }
}

.dlg-no-close .ui-dialog-titlebar-close {
			display: none;
		}
		.dlg-no-title .ui-dialog-titlebar {
			display: none;
		}
#dialog1{
font-family: bahamas;
font-size: 18px;
text-padding:2%;
}
 </style>      
</head>
<body>
<div id="dialog1" title="Notice"></div>	
<form:form method="POST" name="login" id="loginId" commandName="loginForm" action="home.html" onsubmit=" return val();">
		<div class="slide-out-div">
            <a class="handle" href="#">Login</a>
	           <article style="width:96%;">               
		        	<div class="loginbx">            
		            	<h1><img src="/icam/images/login-icon.png" alt="Login"/>&nbsp;Login</h1>                
		                <div class="field">                
		                	<label>Username :</label>
		                    <input type="text" class="txtfld" id="userId" name="userId">                
		                </div>                
		                <div class="field">                
		                	<label>Password :</label>
		                    <input type="password" class="txtfld" id="password" name="password">                
		                </div>                
		                <input type="reset" value="Reset" class="resetbt">                
		                <input type="submit" value="Login" class="logbt">  
		            </div>
		            	                           
		            <div id="warningbox" class="warningbox" >
		            	<span id="warningmsg" class="warningmsg"></span>
					</div>
	        </article>
		</div>
		
		<div class="slide-out-div-about">
		<c:choose>
			<c:when test="${SchoolDetails ne null}">								
            <a class="about-handle" href="#">About</a>
	         <h3 style="color: black;text-decoration: underline;font-weight: bold;text-shadow: maroon;">Welcome to ${SchoolDetails.schoolDetailsName}</h3><br/>
	            <p>${SchoolDetails.schoolDetailsDesc}</p><br/>
	            <p>Principal - <span style="color:green;font-weight: bolder;">${SchoolDetails.principal}</span></p><br/>
	             <p>Headmaster - <span style="color:green;font-weight: bolder;">${SchoolDetails.headmaster}</span></p><br/>
	            <c:if test="${SchoolDetails.boardUniversity ne null && SchoolDetails.boardUniversity ne ''}">
	            <p>Affiliated To - ${SchoolDetails.boardUniversity} , Affiliation Code - ${SchoolDetails.boardUniversityCode}</p><br/>
	            </c:if>
	            <p>Address - 
	            <c:if test="${SchoolDetails.schoolAddress ne null && SchoolDetails.schoolAddress ne ''}">	            
	             ${SchoolDetails.schoolAddress}<br/><br/>
	             </c:if>
	             <c:if test="${SchoolDetails.phone ne null && SchoolDetails.phone ne ''}">	            
	             Contact Number - ${SchoolDetails.phone} /  ${SchoolDetails.mobile}<br/> 
	             </c:if>	             
	             Email - ${SchoolDetails.email}</p>	
	                    
			</c:when>
			<c:otherwise>
				<div class="errorbox" id="errorbox" style="visibility: visible;">
						<span id="errormsg">No School Details Found</span>	
				</div>	
			</c:otherwise>
		</c:choose>
		</div>
<header>
<a href="#"><img style="float: left;position: relative; height:90px;width:100px;" src="/icam/images/school_logo.jpg" alt="School Management"/><img style="float: right;position: relative; height:40px;width:140px; margin:15px;" src="/icam/images/qts.png" alt="QTS"/></a>
</header>
<section class="bodydiv">	
<img src="/icam/images/school.jpg" style="float: left;width:47%;"  /> 
	<div class="wrapper">
        <aside><br/>
        <!-- <aside style="position: fixed; z-index: -1;"><br/> -->
        	<span class="notice">School Time &nbsp;&nbsp;<span id="date" style="color:white;"></span></span>
        	<div id="fancyClock"></div>       <br/>  <br/>	 
	        	<span class="notice" style="padding-left:40%;">Notice Board</span> <br/>
	        <div id="noticeBoard" class="noticeBoard">        
	        	<div id="content_2" class="content" >                  
	                <c:choose>
						<c:when test="${noticelist ne null && not empty noticelist}">
							<c:forEach var="notice" items="${noticelist}"><br/>
								<c:if test="${notice.noOfDay lt 5}">
									<blink>	
									<a href="#" id="${notice.noticeCode}~${notice.noticeDesc}" class="noticeLink" style="text-decoration: none; font-size: 24px;font-weight: bold; color: #0BB5FF;">&emsp;<span class="noticeDate" >
										${notice.time} 
										&emsp;${notice.noticeName}</span>										
									</a>
									</blink> <br/>
								</c:if>	
								<c:if test="${notice.noOfDay ge 5}">
										&emsp;&emsp;<a href="#" id="${notice.noticeCode}~${notice.noticeDesc}" class="noticeLink"  style="text-decoration: none; font-size:24px;font-weight:normal; color: white;">&emsp;<span class="noticeDate1" >
										${notice.time} 
										&emsp;${notice.noticeName}</span>										
									</a><br/>
								</c:if>
							</c:forEach>		
						</c:when>
					<c:otherwise><br/><br/>&emsp;&emsp;No New Notices To Display</c:otherwise>
					</c:choose>
	            </div> 
           </div>               
        </aside>         
	        <c:if test="${loginForm.message != null}">			
			<div id="infomsgbox" class="infomsgbox" style="visibility:visible;margin-left: 2em;">
				<span id="infomsg" class="infomsg"><c:out value=" ${loginForm.message}"/></span>
			</div>
			</c:if>        
	</div>	
</section>
</form:form>
<script src="/icam/js/login/jquery.minlogin.js"></script>
<script>!window.jQuery && document.write(unescape('%3Cscript src="jquery/jquery-1.7.2.min.js"%3E%3C/script%3E'))</script>
<script src="/icam/js/common/jquery-ui.min.js"></script>
<script>!window.jQuery.ui && document.write(unescape('%3Cscript src="jquery/jquery-ui-1.8.21.custom.min.js"%3E%3C/script%3E'))</script>
<script src="/icam/js/common/jquery.mousewheel.min.js"></script>
<script src="/icam/js/common/jquery.mCustomScrollbar.js"></script>
<script src="/icam/js/common/scroll.js"></script>
<script src="/icam/js/login/jquery.tabSlideOut.v1.3.js"></script>

<script type="text/javascript" src="/icam/js/login/clock/jquery.tzineClock.js"></script>
<script type="text/javascript" src="/icam/js/login/clock/script.js"></script>

 <script type="text/javascript">
    $(function(){
        $('.slide-out-div').tabSlideOut({
            tabHandle: '.handle',                     //class of the element that will become your tab
            pathToTabImage: '/icam/images/login_slide.png', //path to the image for the tab //Optionally can be set using css
            imageHeight: '122px',                     //height of tab image           //Optionally can be set using css
            imageWidth: '40px',                       //width of tab image            //Optionally can be set using css
            tabLocation: 'left',                      //side of screen where tab lives, top, right, bottom, or left
            speed: 800,                               //speed of animation
            action: 'click',                          //options: 'click' or 'hover', action to trigger animation
            topPos: '97px',
            right:'10px',
            fixedPosition: false                      //options: true makes it stick(fixed position) on scroll
        });
        
        $('.slide-out-div-about').tabSlideOut({
            tabHandle: '.about-handle',                     //class of the element that will become your tab
            pathToTabImage: '/icam/images/about.png', //path to the image for the tab //Optionally can be set using css
            imageHeight: '110px',                     //height of tab image           //Optionally can be set using css
            imageWidth: '85px',                       //width of tab image            //Optionally can be set using css
            tabLocation: 'top',                      //side of screen where tab lives, top, right, bottom, or left
            speed: 800,                               //speed of animation
            action: 'click',                          //options: 'click' or 'hover', action to trigger animation
            leftPos: '195px',
            fixedPosition: false                      //options: true makes it stick(fixed position) on scroll
        });
    });    
   </script> 
   
			<footer>
			<!-- <footer style="margin-top: 35%;"> -->
				&copy; <a href="http://www.google.com" target="_blank" style="font-weight: bolder; color: orange;">
				Quantalogi Technosoft India PVT LTD</a>, 2015. All Rights Reserved.<br/>
				 No part of this software or any of its contents may be reproduced, 
				 copied, modified or adapted, without the prior written consent from the office, 
				 unless otherwise indicated for stand-alone materials.
			</footer>	
</body>
</html>