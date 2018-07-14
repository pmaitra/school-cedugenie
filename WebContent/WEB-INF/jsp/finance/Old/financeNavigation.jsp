<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/file/sessionDataForParentPage.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="put a short description in here" />
<meta name="keywords" content="put your important keywords in here" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>SCHOOL MANAGEMENT</title>
<!--<link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>-->
<link type='text/css' href='/icam/css/common/dropDownType1.css' rel='stylesheet' media='screen' />
<link rel="icon" href="/icam/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="/icam/css/common/inuit.css" />
<link rel="stylesheet" href="/icam/css/common/fluid-grid16-1100px.css" />
<link rel="stylesheet" href="/icam/css/common/eve-styles.css" />
<link href="/icam/css/common/tab.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bootstrap.css" rel="stylesheet">
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<!--tabarea start-->
<link rel="stylesheet" href="/icam/css/common/tabs.css">
<link rel="stylesheet" href="/icam/css/common/style.css">
<link type='text/css' href='/icam/css/common/osx.css' rel='stylesheet' media='screen' />
<link type='text/css' href='/icam/css/common/modalTable.css' rel='stylesheet' media='screen' />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<!--tabarea end-->
<!--[if IE]>
<script src="js/html5.js"></script>
<![endif]-->
<!--<link href="css/bootstrap.css" rel="stylesheet">-->

<!--<link href="css/bootstrap-responsive.css" rel="stylesheet">-->

<!--Hide the hr img because of ugly borders in IE7. You can change the color of border-top to display a line -->
<!--[if lte IE 7]>
<style>
    hr { display:block; height:1px; border:0; border-top:1px solid #fff; margin:1em 0; padding:0; }
    .grid-4{ width:22% }
</style>
<![endif]-->
<!--side menu start-->
<script src="/icam/js/common/jquery.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/ddaccordion.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery.min.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/js/common/browserClose.js"></script>
<script type="text/javascript">
ddaccordion.init({
	headerclass: "expandable", //Shared CSS class name of headers group that are expandable
	contentclass: "categoryitems", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content
	onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: true, //persist state of opened contents within browser session?
	toggleclass: ["", "openheader"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["prefix", "", ""], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "normal", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
})
</script>
<!--side menu end-->

<link href="/icam/css/common/notificationCalendar/jquery-ui-1.9.2.custom.css" rel="stylesheet">
<script src="/icam/js/common/notificationCalendar/jquery-ui-1.9.2.custom.js"></script>

<!--Group Chat -->
<!--     <script type="text/javascript" src="/icam/js/common/chat/jquery-ui-1.8.2.custom.min.js"></script> -->
    <link type="text/css" href="/icam/css/common/chat/jquery.ui.chatbox.css" rel="stylesheet" />
    <script type="text/javascript" src="/icam/js/common/chat/jquery.ui.chatbox.js"></script>    
 	<script type="text/javascript" src="/icam/js/common/chat/groupChat.js"></script>
<!-- Group Chat Ends -->

<!-- Individual Chat  --> 
<link href="/icam/css/common/chat/individualChat.css" rel="stylesheet">
<script src="/icam/js/common/chat/jquery.ui.individualChatbox.js"></script>
<script src="/icam/js/common/chat/individualChat.js"></script>
 <!-- Individual Chat Ends -->

<script>	
function ChatDetails(){
	$.ajax({
		url: '/icam/getChatCall.html',
		data:"userName=<c:out value="${sessionScope.sessionObject.userName}"/>",			       
		success: function(dataDB) {	
			if(dataDB!= ''){	        		
				var arr=dataDB.split(":");
				var from= arr[0];
				var to = arr[1];
				$("#chatMsg").html("Individual Chat Opened For You By "+to);
				document.getElementById("chatInfo").style.display='block';	
				$("#chatInfo").fadeOut(800).fadeIn(800).fadeOut(400).fadeIn(400).fadeOut(400).fadeIn(400);
				$(".close").click(function(){
			        $("#chatInfo").animate({left:"+=10px"}).animate({left:"-5000px"});
			        setInterval(function(){},1000);
			        document.getElementById("chatInfo").style.display='none';
			    });
			}				
	   }
	});
}
$(document).ready(function() { 
	setInterval(function(){ChatDetails();},3000);
	$(".roleChange").each(function(){		
		$(this).click(function(){
			var roleName=this.name;
			if(roleName!=''){					
				$( "#roleName" ).val(roleName);					
				$( "#financeNavigation" ).submit();
				}	
		});
  }); 
  
  $(function(){
		$(".dropdown-menu > li > a.trigger").on("click",function(e){
			var current=$(this).next();
			var grandparent=$(this).parent().parent();
			if($(this).hasClass('left-caret')||$(this).hasClass('right-caret'))
				$(this).toggleClass('right-caret left-caret');
			grandparent.find('.left-caret').not(this).toggleClass('right-caret left-caret');
			grandparent.find(".sub-menu:visible").not(current).hide();
			current.toggle();
			e.stopPropagation();
		});
		$(".dropdown-menu > li > a:not(.trigger)").on("click",function(){
			var root=$(this).closest('.dropdown');
			root.find('.left-caret').toggleClass('right-caret left-caret');
			root.find('.sub-menu:visible').hide();
		});
	});
	
	$("#totalNotifications").html("<c:out value="${notification.newNotification+notification.newEmailNotification}"/>");
	$(".notificationDetails").each(function(){		
		$(this).click(function(){				
			var notificationDesc=this.id;
			var clearName=this.name;
			var clearClass=document.getElementsByName(clearName);
			for(var x=0;x<clearClass.length;x++){
				clearClass[x].removeAttribute("class");
				clearClass[x].setAttribute("class","notificationDetails");
				}	
			var desc = notificationDesc.split('~');				
			$("#dialog1").dialog({
				autoOpen: false,
				modal: true,
				resizable: false,
				minWidth:500,
				width:500,
				minHeight:300,
				height:300,			
				dialogClass: "dlg-no-close",
				buttons: {
					"Close": function() {
						$(this).dialog("close");
					}
				}
			});
			$.ajax({
				url:'/icam/getNotificationDetails.html',
				data:"notificationId="+desc[0],
				dataType: 'json'
				});						
			desc[1]=desc[1].replace(/\n/g, "<br/>");
			document.getElementById("dialog1").innerHTML = desc[1];			
			$("#dialog1").dialog("open");
	});		
	});

	$(".emailNotificationDetails").each(function(){		
		$(this).click(function(){				
			var emailDesc=this.id;
			var clearName=this.name;
			var clearClass=document.getElementsByName(clearName);
			for(var x=0;x<clearClass.length;x++){
				clearClass[x].removeAttribute("class");
				clearClass[x].setAttribute("class","emailNotificationDetails");
				}	
			var emailContent = emailDesc.split('~');				
			$("#dialog2").dialog({
				autoOpen: false,
				modal: true,
				resizable: false,
				minWidth:600,
				width:800,
				minHeight:400,
				height:400,			
				dialogClass: "dlg-no-close",
				buttons: {
					"Close": function() {
						$(this).dialog("close");
					}
				}
			});
			$.ajax({
				url:'/icam/changeMailReadStatus.html',
				data:"emailAlertCode="+emailContent[0]					
				});					
			emailContent[1]=emailContent[1].replace(/\n/g, "<br/>");
			document.getElementById("dialog2").innerHTML = emailContent[1];			
			$("#dialog2").dialog("open");
	});	
	});
});	

var readEmail=true;
var readNotification=true;
		
function hideEmailNotification(){
	document.getElementById("noOfEmail").style.display="none";		
	if(parseInt(<c:out value="${notification.newNotification}"/>)!=0 && readNotification){
		$("#totalNotifications").html("<c:out value="${notification.newNotification}"/>");
		readEmail=false;
		}else{
			document.getElementById("totalNotifications").style.display="none";
		}		  
}

function hideNotification(){
	document.getElementById("noOfNotification").style.display="none";
	if(parseInt(<c:out value="${notification.newEmailNotification}"/>)!=0 && readEmail){
		$("#totalNotifications").html("<c:out value="${notification.newEmailNotification}"/>");
		readNotification=false;
		}else{
			document.getElementById("totalNotifications").style.display="none";				
		}
}
</script>

<style>		
	.urgentNotification,.emailDetailsUnread {
		text-decoration: none;
		font-size:20px;
		cursor: pointer;
	}
	#chatInfo{
    border: 1px solid;
    margin: 10px 0px;
    padding:15px 10px 15px 50px;
    background-repeat: no-repeat;
    background-position: 10px center;
    position:relative;
    color: #00529B;    
	background-color: #dfeff5;
    background-image: url(/icam/images/info.png);
    display: none;
	}
</style>

</head>
<body>
<!-- modal content -->
		<div id="osx-modal-content">
			<div id="osx-modal-title">Hi ! ${sessionScope.sessionObject.userName}</div>
			<div class="close"><a href="#" class="simplemodal-close">x</a></div>
			<div id="osx-modal-data">
				<h1>USER PROFILE</h1>
				<c:choose>
					<c:when test="${null ne resource.image.imagepath}">			
						<img src="data:image/jpg;base64, ${resource.image.imagepath}" alt="No Image" style="min-height:50px;min-width:50px; height: 80px; width:80px; float:right;" />
					</c:when>
					<c:otherwise>
							<c:choose>
								<c:when test="${fn:toLowerCase('FEMALE') eq fn:toLowerCase(resource.gender)}">			
									<img src="StaffImage/female_default_images.jpg" alt="No Image" style="min-height:50px;min-width:50px; height: 80px; width:80px; float:right;" />
								</c:when>
								<c:otherwise>
									<img src="StaffImage/male_default_images.jpg" alt="No Image" style="min-height:50px;min-width:50px; height: 80px; width:80px; float:right;"/>	
								</c:otherwise>
							</c:choose>
					</c:otherwise>	
				</c:choose>
				<h6>Your Current Designation is ${sessionScope.sessionObject.currentRoleOrAccess}</h6>					
					<table id="modaltable" cellspacing='0'> <!-- cellspacing='0' is important, must stay -->
						<tr><th>Name</th><td>${resource.name}</td></tr>
						
						<c:if test="${resource.fatherFirstName ne null}">
						<tr class='even'><th>Fathers Name</th><td>${resource.fatherFirstName}</td></tr>					
						</c:if>
						<c:if test="${resource.dateOfBirth ne null}">
						<tr><th>Date Of Birth</th><td>${resource.dateOfBirth}</td></tr>
						</c:if>
<%-- 						<c:if test="${resource.address ne null}"> --%>
<%-- 						<tr class='even'><th>Address</th><td>${resource.address.presentAddress1}, ${resource.address.presentAddress2}, ${resource.address.presentPostOffice}, ${resource.address.presentCity},  ${resource.address.presentState} - ${resource.address.presentPin},  ${resource.address.presentCountry}</td></tr> --%>
<%-- 						</c:if> --%>
						<c:if test="${resource.emailId ne null}">
						<tr><th>Contact Details</th><td>
						<a style="color:red;" href="mailto:${resource.emailId}" target="_top">${resource.emailId}</a> / ${resource.mobile}</td>
						</tr>
						</c:if>	
					</table>			
				<p><button class="simplemodal-close">Close</button> <span>(or press ESC or click the overlay)</span></p>
			</div>
		</div>
		
		<!-- Password-->
		<div id="osx-modal-content-password">
			<div id="osx-modal-title-password">Hi ! ${sessionScope.sessionObject.userName}</div>
			<div class="close"><a href="#" class="simplemodal-close-password">x</a></div>
			<div id="osx-modal-data-password">
					<form:form method="POST" name="changeUserPassword" id="changeUserPassword"  action="updatePassword.html">
						<table id="modaltable-password" cellspacing='0'> <!-- cellspacing='0' is important, must stay -->
							<tr>
								<th>
									Current Password :<input type="hidden"  name="status" value="changeUserPassword"> 
								</th>
							<td>
									<input type="password" class="txtfld" id="password" name="password" autocomplete="off">  
								</td>		
							</tr>
							<tr>
								<th>
									New Password :
								</th>
								<td>
									<input type="password" class="txtfld" id="newPassword" name="newPassword">
								</td>		
							</tr>
							<tr>
								<th>
									Retype New Password :
								</th>
								<td>
									<input type="password" class="txtfld" id="reTypeNewPassword" name="reTypeNewPassword">   
								</td>		
							</tr>		
						</table>
							<input type="reset" value="Reset" class="clearbtn" style="float:right">  
					 		<input type="submit" id="changePassword" name="change" value="Update" class="editbtn" style="float:right"> 
					</form:form> 
				<p><button class="simplemodal-close">Close</button> <span>(or press ESC or click the overlay)</span></p>
			</div>
		</div>


<!-- Email-->
		
		<div id="osx-modal-content-email">
		<div id="osx-modal-title-email">Hi ! ${sessionScope.sessionObject.userName}</div>
		<div class="close"><a href="#" class="simplemodal-close-email">x</a></div>
		<div id="osx-modal-data-email">
			<h1>Your Inbox</h1><br/>
				<c:choose>
				<c:when test="${emailDetailsList == null  && fn:length(emailDetailsList) lt 1}">
						  <div class="errorbox" id="errorbox"  style="visibility:visible;">
							<span id="errormsg">No mail in your inbox</span>	
						  </div> 
						  <br/><br/> 				
				 </c:when> 
				 <c:otherwise>
					<table id="modaltable-email" cellspacing='0'>
						<tr>
							<th>Date &amp; Time</th>
							<th>Subject</th>
							<th>Sender</th>
						</tr>
						<c:forEach var="emailDetails" items="${emailDetailsList}" varStatus="i" begin="0" end="5">			
							<tr>					
								<td>
									<c:choose>
										<c:when test="${emailDetails.status eq 'A'}">
											<a class="emailDetailsUnread emailNotificationDetails" href="#" name="mailData${i.index}" id="${emailDetails.emailDetailsCode}~${emailDetails.emailDetailsDesc}">${emailDetails.time}</a>
										</c:when>
									<c:otherwise>
											<a class="emailNotificationDetails" href="#" name="mailData${i.index}" id="${emailDetails.emailDetailsCode}~${emailDetails.emailDetailsDesc}">${emailDetails.time}</a>
									</c:otherwise>
									</c:choose>
								</td>			
								<td>
									<c:choose>
											<c:when test="${emailDetails.status eq 'A'}">
												<a class="emailDetailsUnread emailNotificationDetails" href="#" name="mailData${i.index}" id="${emailDetails.emailDetailsCode}~${emailDetails.emailDetailsDesc}">${emailDetails.emailDetailsSubject}</a>
											</c:when>
											<c:otherwise>
												<a class="emailNotificationDetails" href="#" name="mailData${i.index}" id="${emailDetails.emailDetailsCode}~${emailDetails.emailDetailsDesc}">${emailDetails.emailDetailsSubject}</a>
											</c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:choose>
										<c:when test="${emailDetails.status eq 'A'}">
											<a class="emailDetailsUnread emailNotificationDetails" href="#" name="mailData${i.index}" id="${emailDetails.emailDetailsCode}~${emailDetails.emailDetailsDesc}">${emailDetails.emailDetailsSender}</a>
										</c:when>
									<c:otherwise>
											<a class="emailNotificationDetails" href="#" name="mailData${i.index}" id="${emailDetails.emailDetailsCode}~${emailDetails.emailDetailsDesc}">${emailDetails.emailDetailsSender}</a>
									</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>							
					</table><br/>
				</c:otherwise>
				</c:choose>
				
				<c:if test="${fn:length(notificationList) gt 6}">
				  <div class="infomsgbox" id="infomsgbox"  style="visibility:visible;">
					<span id="infomsg">To View All Mails,Visit My Services</span>	
				  </div> 
				  <br/> 				
				</c:if>
			<p><button class="simplemodal-close">Close</button> <span>(or press ESC or click the overlay)</span></p>
		</div>
		</div>

	<!-- Email Ends-->	

	<div id="osx-modal-content-notification">
	<div id="osx-modal-title-notification">Hi ! ${sessionScope.sessionObject.userName}</div>
	<div class="close"><a href="#" class="simplemodal-close-notification">x</a></div>
	<div id="osx-modal-data-notification">
		<h1>Your Notifications</h1><br/>					
			<c:choose>
			<c:when test="${notificationList == null  && fn:length(notificationList) lt 1}">
					  <div class="errorbox" id="errorbox"  style="visibility:visible;">
						<span id="errormsg">No notification available for you</span>	
					  </div> 
					  <br/><br/> 				
			 </c:when> 
			 <c:otherwise>		
				<table id="modaltable-notification" cellspacing='0'>
						<tr>
							<th>Date &amp; Time</th>
							<th>Subject</th>
							<th>Sender</th>
						</tr>
						<c:forEach var="notif" items="${notificationList}" varStatus="i" begin="0" end="5">	
						<c:choose>
							<c:when test="${notif.status == 'A'}">
							<tr>							
								<td>						
									<a href="#" id="${notif.notificationId}~${notif.notificationDesc}"  name="data${i.index}" class="notificationDetails urgentNotification" >
									${notif.notificationDate}
									</a>											
								</td>					
								<td>						
									<a href="#" id="${notif.notificationId}~${notif.notificationDesc}"  name="data${i.index}" class="notificationDetails urgentNotification" >
										${notif.notificationSubject}
									</a>						
								</td>
								<td>						
									<a href="#" id="${notif.notificationId}~${notif.notificationDesc}"  name="data${i.index}" class="notificationDetails urgentNotification" >
									${notif.notificationSender}	
									</a>									
								</td>							
							</tr>								
							</c:when>	
							<c:otherwise>
							<tr>
								<td>						
									<a href="#" id="${notif.notificationId}~${notif.notificationDesc}"  name="data${i.index}" class="notificationDetails">${notif.notificationDate}</a>						
								</td>
								<td>						
									<a href="#" id="${notif.notificationId}~${notif.notificationDesc}"  name="data${i.index}" class="notificationDetails">${notif.notificationSubject}</a>						
								</td>
								<td>
									<a href="#" id="${notif.notificationId}~${notif.notificationDesc}"  name="data${i.index}" class="notificationDetails">${notif.notificationSender}</a>													
								</td>
							</tr>
							</c:otherwise>
							</c:choose>						
						</c:forEach>		
				</table>
			</c:otherwise>
			</c:choose>	<br/>
			
			<c:if test="${fn:length(notificationList) gt 6}">
					  <div class="infomsgbox" id="infomsgbox"  style="visibility:visible;">
						<span id="infomsg">To View Previous Notifications,Visit My Services To Find More</span>	
					  </div> 
					  <br/> 				
			 </c:if>			  
			<p><button class="simplemodal-close">Close</button> <span>(or press ESC or click the overlay)</span></p>
		</div>
		</div>

<!-- End Of modal content -->

<div id="dialog1" title="Notification"></div>
<div id="dialog2" title="Email Inbox"></div>

<form id="financeNavigation" name="financeNavigation" action="changeRoleForUser.html" method="post">		
<!-- End Of modal content -->
<section class="wrapper">
	<!--header start-->    
    <header>    
    	<div class="headlcon">        
        	<div id="logo">
            <a href="#"><img src="/icam/images/logo.jpg" style="height:40px;width:140px;float:center;"  alt="school management" title="school management" /></a>
           </div>        
      </div>        
        <div class="headrcon">        
        	<div id="user-nav" class="navbar navbar-inverse">
              <ul class="nav">
                <li  class="dropdown" id="profile-messages" ><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user icon-2x"></i>  <span class="text">Welcome User</span><b class="caret"></b></a>
                  <ul class="dropdown-menu">
                    <li><a href="#" class='osx'><i class="icon-user"></i> My Profile</a></li>
                    <li class="divider"></li>
                    <li><a href="#" class='osx1'><i class="icon-key"></i> Change Password</a></li>
        			<li class="divider"></li> 
                    <li><a href="createNotification.html" target="frame"><i class="icon icon-envelope"></i> Create Notification</a></li>
                     <li class="divider"></li>
                    <li><a href="logOut.html"><i class="icon icon-share-alt"></i> Log Out</a></li>
                     <li class="divider"></li>
                  </ul>
                </li>
				<li class="dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-wrench icon-2x"></i> <span class="text" >Roles And Access Type</span><b class="caret"></b></a>
					<ul class="dropdown-menu">
					<li><a class="sAdd" title="" href="#"><i class="icon icon-dashboard"></i> Current Role ::<span style="font-family:'bahamas'; color: #445fa2;text-transform: lowercase;font-size:16px;"> ${sessionScope.sessionObject.currentRoleOrAccess}</span></a></li>
					<li class="divider"></li>					
					<li>
						<a class="trigger right-caret">Available Roles ::</a>
						<ul class="dropdown-menu sub-menu">
							<c:forEach var="availableRolesAndAccess" items="${sessionScope.sessionObject.availableRoles}">				
								<c:if test="${sessionScope.sessionObject.currentRoleOrAccess ne availableRolesAndAccess}">
									<li><a href="#" name="<c:out value="${availableRolesAndAccess}" />" class="roleChange" ><c:out value="${availableRolesAndAccess}" /></a></li>
								</c:if>
							</c:forEach>							
						</ul>
					</li>					
					</ul>
				</li>
				<c:if test="${not empty sessionScope.sessionObject}">	                
					 <li  class="dropdown" id="chatmenu"><a title="" href="#" data-toggle="dropdown" data-target="#chatmenu" class="dropdown-toggle">
						<c:if test="${notification.newNotification+notification.newEmailNotification ne 0}">
											<span id="totalNotifications" style="font-size: large;color: black;" class="label label-important"></span>
						</c:if>
						<i class="icon icon-envelope icon-2x"></i><span class="text">             	
							&nbsp;Chat &amp; Mesaage</span><b class="caret"></b></a>		                  
						  <ul class="dropdown-menu">
								<li>
									<a href="#" class='osx3' onclick="hideNotification();">
									<i class="icon icon-globe"></i>									
									Notification Alert
										<c:if test="${notification.newNotification ne 0}">
										<span id="noOfNotification" style="font-size:medium ;color: black;"  class="label label-important">${notification.newNotification}</span>
										</c:if>				                    
									</a>
								</li>
								<li class="divider"></li>			                    	                    
								<li>
									<a href="#" class='osx2' onclick="hideEmailNotification();">
									<i class="icon-inbox"></i>				                    
									&nbsp;E-Mail
										<c:if test="${notification.newEmailNotification ne 0}">
											<span id="noOfEmail" style="font-size:medium;color: black;" class="label label-important">${notification.newEmailNotification}</span>
										</c:if>
									</a>
								</li>
								<li class="divider"></li>
								<li><a href="#" class="minibtn"><i class="icon-comment"></i>&nbsp;Individual Chat</a></li>
								<li class="divider"></li>
								<li><a href="#" id="groupChat"><i class="icon-group"></i>&nbsp;Group Chat</a></li>
								<li class="divider"></li>
						  </ul>
					</li>
				 </c:if>
              </ul>               
            </div>                     
      </div>    
    </header>
	<!--header end-->
	<!--body start-->
	<div id="chatInfo"> 
		<span id="chatMsg"></span><a href="#" class="close">Click Here To Close</a>   
	</div>    
    <section class="bodyarea">    
    	<!--left navigation start-->        
        <article class="leftcon">        
        	<!--side menu start-->            
			<div class="arrowlistmenu">				
                <a href="notificationPage.html" target="_self" style="color: yellow; font-weight: bolder; text-decoration: none; ">
	                <h3 class="menuheader" style="background:url('/icam/images/a.jpg');">
	                <i class="icon icon-home"></i>&nbsp;&nbsp;<span style="color:white;">HOME -> </span> <span style="color:#9fda58">Finance</span></h3>
                </a>
                
                <c:set var="i" value="0" scope="page" />
				<c:forEach var="functionality" items="${role.functionalityList}" >
					<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Financial Year')}">
						<c:if test="${functionality.update eq true || functionality.insert eq true || functionality.view eq true}">
							<c:set var="i" value="1" scope="page" />
						</c:if>
					</c:if>
				</c:forEach>
				<c:out value="${i }"></c:out>
				<c:if test="${i eq 1}">
				<h3 class="menuheader expandable">Configure Financial Year</h3>
	                <ul class="categoryitems">
	                	
	                	<c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Financial Year')}">
								<c:if  test="${functionality.insert eq true}">
									<li><a href ="configureFinancialYear.html" target="frame">Configure Financial Year</a></li>
								</c:if>
							</c:if>
						</c:forEach>						
	                </ul>
	              </c:if>
	              
	            <c:set var="i" value="0" scope="page" />				
				<c:forEach var="functionality" items="${role.functionalityList}" >
					<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Group')
								 || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Group')}">
						<c:if  test="${functionality.update eq true || functionality.insert eq true || functionality.view eq true}">
							<c:set var="i" value="1" scope="page" />
						</c:if>
					</c:if>
				</c:forEach>
				<c:if test="${i eq 1}">                                 
	                <h3 class="menuheader expandable">Group</h3>
	                <ul class="categoryitems">
	                	
	                	<c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Group')}">
								<c:if  test="${functionality.insert eq true}">
									<li><a href ="groupCreatePage.html" target="frame">Create Group</a></li>
								</c:if>
							</c:if>
						</c:forEach>
	                	<c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Group')}">
								<c:if  test="${functionality.view eq true}">
									<li><a href ="groupListPage.html" target="frame">List Group</a></li>
								</c:if>
							</c:if>
						</c:forEach>						
						
	                </ul>
                </c:if>
				
				 <c:set var="i" value="0" scope="page" />				
				<c:forEach var="functionality" items="${role.functionalityList}" >
					<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Ledger')
								 || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Ledger')}">
						<c:if  test="${functionality.update eq true || functionality.insert eq true || functionality.view eq true}">
							<c:set var="i" value="1" scope="page" />
						</c:if>
					</c:if>
				</c:forEach>
				<c:if test="${i eq 1}">                                 
	                <h3 class="menuheader expandable">Ledger</h3>
	                <ul class="categoryitems">
	                	
	                	<c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Ledger')}">
								<c:if  test="${functionality.insert eq true}">
									<li><a href ="ledgerCreatePage.html" target="frame">Create Ledger</a></li>
								</c:if>
							</c:if>
						</c:forEach>
	                	<c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Ledger')}">
								<c:if  test="${functionality.view eq true}">
									<li><a href ="ledgerListPage.html" target="frame">List Ledger</a></li>
								</c:if>
							</c:if>
						</c:forEach>						
                     </ul>
                </c:if>               
			  
			   <c:set var="i" value="0" scope="page" />
                <c:forEach var="functionality" items="${role.functionalityList}" >
					<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Fees Template Ledger Mapping')
								 || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Student Fees')
								 || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Other Transactions')
								 || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Passbook Entry')}">
						<c:if  test="${functionality.update eq true || functionality.insert eq true || functionality.view eq true}">
							<c:set var="i" value="1" scope="page" />
						</c:if>
					</c:if>
				</c:forEach>
				
					<c:if test="${i eq 1}">  		   
	                <h3 class="menuheader expandable">Create Transaction</h3>
	                <ul class="categoryitems">
	                	
	                	<c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Fees Template Ledger Mapping')}">
								<c:if  test="${functionality.insert eq true}">
									<li><a href ="feesTemplateLedgerMapping.html" target="frame">Fees Template Ledger Mapping</a></li>
								</c:if>
							</c:if>
						</c:forEach>
					    <c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Student Fees')}">
								<c:if  test="${functionality.insert eq true}">
									<li><a href ="getStudentFees.html" target="frame">Student Fees</a></li>
								</c:if>
							</c:if>
						</c:forEach>
	                	<c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Other Transactions')}">
								<c:if  test="${functionality.view eq true}">
									<li><a href ="createTransactionPage.html" target="frame">Create Other Transactions</a></li>
								</c:if>
							</c:if>
						</c:forEach>
                        <c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Passbook Entry')}">
								<c:if  test="${functionality.view eq true}">
									<li><a href ="passbook.html" target="frame">Passbook Entry</a></li>
								</c:if>
							</c:if>
						</c:forEach>						
                     </ul>
                </c:if>
                
               <c:set var="i" value="0" scope="page" />  
               <c:forEach var="functionality" items="${role.functionalityList}" >
					<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Employee Salary Details')
								 || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Designation Salary Details')
								 || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Salary Disbursement List')}">
						<c:if  test="${functionality.update eq true || functionality.insert eq true || functionality.view eq true}">
							<c:set var="i" value="1" scope="page" />
						</c:if>
					</c:if>
				</c:forEach>	
					
					
					
					<c:if test="${i eq 1}">	   
	                <h3 class="menuheader expandable">Employee Salary Transaction</h3>
	                <ul class="categoryitems">
	                	
	                	<c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Employee Salary Details')}">
								<c:if  test="${functionality.insert eq true}">
									<li><a href ="getEmployeeSalaryDetails.html" target="frame">Employee Salary Details</a></li>
								</c:if>
							</c:if>
						</c:forEach>
					    <c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Designation Salary Details')}">
								<c:if  test="${functionality.insert eq true}">
									<li><a href ="designationSalaryDetails.html" target="frame">Designation Salary Details</a></li>
								</c:if>
							</c:if>
						</c:forEach>
	                	<c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Salary Disbursement List')}">
								<c:if  test="${functionality.view eq true}">
									<li><a href ="getSalaryDisbursementList.html?month=NA" target="frame">Salary Disbursement List</a></li>
								</c:if>
							</c:if>
						</c:forEach>
                        						
                     </ul>
                </c:if>
                
				<c:set var="i" value="0" scope="page" />
                 	<c:forEach var="functionality" items="${role.functionalityList}" >
					<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Vendor Payment')}">
						<c:if  test="${functionality.update eq true || functionality.insert eq true || functionality.view eq true}">
							<c:set var="i" value="1" scope="page" />
						</c:if>
					</c:if>	
					</c:forEach>
					
					<c:if test="${i eq 1}">	   			
	                <h3 class="menuheader expandable">Vendor Payment Transaction</h3>
	                <ul class="categoryitems">
	                	
	                	<c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Vendor Payment')}">
								<c:if  test="${functionality.insert eq true}">
									<li><a href ="getAllPurchaseOrdersForPayment.html" target="frame">Vendor Payment</a></li>
								</c:if>
							</c:if>
						</c:forEach>
                        						
                     </ul>
                </c:if>
                
				<c:set var="i" value="0" scope="page" /> 
                <c:forEach var="functionality" items="${role.functionalityList}" >
					<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Trial Balance')
								 || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Income & Expenditure')
								 || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('CashBook')
								 || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('DayBook')
								 || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Balance Sheet')
								 || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Bank Reconcilation Statement')}">
						<c:if  test="${functionality.update eq true || functionality.insert eq true || functionality.view eq true}">
							<c:set var="i" value="1" scope="page" />
						</c:if>
					</c:if>
				</c:forEach>
				
				<c:if test="${i eq 1}">					
	                <h3 class="menuheader expandable">Financial Output</h3>
	                <ul class="categoryitems">
	                	
	                	<c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Trial Balance')}">
								<c:if  test="${functionality.insert eq true}">
									<li><a href ="trialBalance.html" target="frame">Trial Balance</a></li>
								</c:if>
							</c:if>
						</c:forEach>
					    <c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Income & Expenditure')}">
								<c:if  test="${functionality.insert eq true}">
									<li><a href ="createIncomeAndExpense.html" target="frame">Income & Expenditure</a></li>
								</c:if>
							</c:if>
						</c:forEach>
	                	<c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('CashBook')}">
								<c:if  test="${functionality.view eq true}">
									<li><a href ="cashBook.html" target="frame">CashBook </a></li>
								</c:if>
							</c:if>
						</c:forEach>
<%-- 	                	<c:forEach var="functionality" items="${role.functionalityList}" > --%>
<%-- 							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('DayBook')}"> --%>
<%-- 								<c:if  test="${functionality.view eq true}"> --%>
									<li><a href ="dayBook.html" target="frame">DayBook </a></li>
<%-- 								</c:if> --%>
<%-- 							</c:if> --%>
<%-- 						</c:forEach> --%>
						<c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Balance Sheet')}">
								<c:if  test="${functionality.view eq true}">
									<li><a href ="createBalanceSheet.html" target="frame">Balance Sheet </a></li>
								</c:if>
							</c:if>
						</c:forEach>
						<c:forEach var="functionality" items="${role.functionalityList}" >
							<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Bank Reconcilation Statement')}">
								<c:if  test="${functionality.view eq true}">
									<li><a href ="brs.html" target="frame">Bank Reconcilation Statement</a></li>
								</c:if>
							</c:if>
						</c:forEach>
                        						
                     </ul>
                </c:if>
                             
</div> 
        	<!--side menu end-->
        </article>    
    	<!--left navigation end-->    
    	<!--right con start-->        
        <aside class="rightcon"> 
        	<!--content start-->            
            <div class="innercon">            
            	<div class="boxcon">
					<iframe src="" name="frame" id="frame" onload='javascript:resizeIframe(this);'>				
					</iframe>
                </div>            
            </div>        
        	<!--content end-->        
        </aside>    
    	<!--right con end-->    
    </section>
	<!--body end-->
	
	
	<!--Individual Chat Box Body -->		
		<div id="chat_div1"></div>
		
		<div id="pop">
            <div id="close">X</div>
            <div id="contentPop">
            	<div class="container chat-signin">	
					<br/><br/><label for="chatroom">Select User</label> &emsp;&thinsp;&nbsp;
					<select  id="chatroom">
						<option value="null">Please Select</option>
						<c:forEach var="resource" items="${resourceList}">
							<option value="${resource.name}">${resource.name}</option>
						</c:forEach>
					</select><br/><p/><br/>			
					<button class="btn btn-large btn-primary" type="submit" id="enterRoom">Start Chat</button>
				</div>
            </div>
      </div>
      <div id="mask">
            <div id="page-wrap">
            </div>
      </div>
	<!--Individual Chat Box Body -->



	<!--Group Chat Box Body -->
		
	<div id="chat_div">
		<div class="activeUsers" id="activeUsers">
	       &emsp;&nbsp;&thinsp;Active Users<br/><br/>
	        <div class="contentDiv" id="nicknamesBox">
	        </div>
      	</div>
		<div id="chatBox">
        </div>                     
    </div>
	<!-- Group Chat Box Body end-->	
	<input type="hidden" id="nickname" value='&nbsp;&nbsp;<c:out value="${sessionScope.sessionObject.userName}"/>' />
	<input type="hidden" name="roleName" id="roleName"/>
	
	<!--footer start-->    
    <footer>
    &copy; <a href="http://www.google.com" target="_blank" style="font-weight: bolder; color: orange;">
				Quantalogi Technosoft India PVT LTD</a>, 2015. All Rights Reserved.<br/> 
    </footer>
	<!--footer end-->
</section>
<script type="text/javascript" src='/icam/js/common/jquery-ui-1.10.2.custom.min.js'></script>	
<script type="text/javascript" src="/icam/js/common/bootstrap.min.js"></script>
<script type="text/javascript" src='/icam/js/common/jquery.simplemodal.js'></script>
<script type="text/javascript" src='/icam/js/common/osx.js'></script>	
<script type="text/javascript" src="/icam/js/common/jquery.easing.1.3.js"></script>
<!--tabarea start-->		
<script src="/icam/js/common/responsiveTabs.js"></script>
<script>
$(document).ready(function() {
    RESPONSIVEUI.responsiveTabs();
});
</script>        
<!--tabarea end-->	 
</form>
</body>
</html>