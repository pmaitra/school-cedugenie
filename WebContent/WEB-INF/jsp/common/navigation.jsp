<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@ include file="/file/sessionDataForParentPage.txt"%>
 
<!doctype html>
<html class="fixed header-dark">
<head>
	<!-- Basic -->
	<meta charset="UTF-8">
	<title>Dashboard | cEduGenie Admin - BETA</title>
	<meta name="keywords" content="" />
	<meta name="description" content="">
	<meta name="author" content="">
	<!-- Mobile Metas -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<%@ include file="/include/include.jsp" %>
	<link type="text/css" href="/icam/css/common/chat/jquery.ui.chatbox.css" rel="stylesheet" />
	<link href="/icam/css/common/chat/individualChat.css" rel="stylesheet">
	<style>		
		/*  #chatInfo{
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
		}  */
`	</style>
	<script>
		function hideNotification(){
			//alert("hii");
			document.getElementById("notification").innerHTML='<i class="fa fa-bell"></i>';			
			$.ajax({
				url: '/icam/updateTaskNotification.html',
				dataType: 'json',			       
				success: function(dataDB) {
					if(dataDB != "null" && dataDB !=""){
						$('#notificationUL > li').remove();
						var arr = dataDB.split(";");
						var arrsize = null;
						if(arr.length<6){
							arrsize = 1;
						}else{
							arrsize = arr.length-5;
						}
						for(var i=arr.length-1;i>=arrsize;i--){
							var row = "<li>";
							row += '<a class="clearfix" href="#">';
							row += '<div class="image">';
							row += '<i class="fa fa-check-square bg-warning"></i>';
							row += "</div>";
							row += '<span class="message">'+arr[i]+'</span>';
							row += "</li>";
							$("#notificationUL").append(row);
						}
					}
					$("#notificationLI").addClass("open");
			   }
			});
		}
		
		function hideAlerts(){
			document.getElementById("alerts").innerHTML='<i class="fa fa-tasks"></i>';			
			$.ajax({
				url: '/icam/viewAlerts.html',
				dataType: 'json',
				success: function(dataDB) {
					if(dataDB != "null" && dataDB !=""){
						$('#alertsUL > li').remove();
						var arr = dataDB.split("*");
						
						var arrsize = null;
						if(arr.length<6){
							arrsize = 1;
						}else{
							arrsize = arr.length-5;
						}
						for(var i=arr.length-1;i>=arrsize;i--){
					        var row = "<li>";
					        row += '<a class="clearfix" href="#">';
					        row += '<div class="image">';
					        row += '<i class="fa fa-check-square bg-warning"></i>';
					        row += "</div>";
					        row += '<span class="message">'+arr[i]+'</span>';
					        row += "</li>";
					        $("#alertsUL").append(row);    
						 }
						$("#alertsLI").addClass("open");
					}
			   	}
			});
		}
		
		function hideEmails(){
			document.getElementById("emails").innerHTML='<i class="fa fa-envelope"></i>';			
			$.ajax({
				url: '/icam/viewEmails.html',
				 dataType: 'json',
				success: function(dataDB) {
					if(dataDB != "null" && dataDB !=""){
						$('#emailsUL > li').remove();
						var arr = dataDB.split("*");
						
						var arrsize = null;
						if(arr.length<6){
							arrsize = 1;
						}else{
							arrsize = arr.length-5;
						}
						for(var i=arr.length-1;i>=arrsize;i--){
					        var row = "<li>";
					        row += '<a class="clearfix" href="#">';
					        row += '<div class="image">';
					        row += '<i class="fa fa-check-square bg-warning"></i>';
					        row += "</div>";
					        row += '<span class="message">'+arr[i]+'</span>';
					        row += "</li>";
					        $("#emailsUL").append(row);    
						 }
						$("#emailsLI").addClass("open");
					}
			   	}
			});
		}

		function changeCourseCode(){
			var programCode = $("#programCode").val();
			if(programCode!=''){
				$( "#programName" ).val(programCode);					
				$( "#programPage" ).submit();
			}
		}
	</script>
</head>
<body>
<%-- <%System.out.println(request.getSession().getAttribute("courseCode")); 
System.out.println(request.getSession().getAttribute("resourceType")); %> --%>
<section class="body">
	<!-- start: header -->
		<header class="header">
			<div class="logo-container">
				<a href="#" class="logo">
					<img src="assets/images/logo.png" height="56" alt="cEduGenie Admin" />
				</a>
				<div class="visible-xs toggle-sidebar-left" data-toggle-class="sidebar-left-opened" data-target="html" data-fire-event="sidebar-left-opened">
					<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
				</div>
			</div>
		
			<!-- start: search & user box -->
			<div class="header-right">
				<%-- <form action="#" class="search nav-form">
					<div class="input-group input-search">
						<input type="text" class="form-control" name="q" id="q" placeholder="Search...">
						<span class="input-group-btn">
							<button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
						</span>
					</div>
				</form> --%>
				<%-- <form action="#" class="search nav-form form-inline" style="width: 250px;">
					<div class="form-group">
					<label>Program Name</label>
					<select class="form-control" id = "programCode" name = "programCode">
						<c:forEach var="course" items="${courseList}" varStatus="i">
							<option value="${course.courseCode}">${course.courseName}</option>
						</c:forEach>
					</select>
					</div>
			</form> --%>
			<% String resourceType = request.getSession().getAttribute("resourceType").toString();
				System.out.println ("resourceType====="+resourceType);
				if(resourceType.equalsIgnoreCase("STUDENT")){
				String courseCode = request.getSession().getAttribute("courseCode").toString();
				System.out.println ("courseCode====="+courseCode);%>
				<form id="programPage" name="programPage" action="changeProgramForStudent.html" method="post" class="search nav-form form-inline" style="width: 500px;">
					<input type="hidden" name="programName" id="programName"/>
					<div class="form-group">
						<label style = "color:#fff">Program Name</label>
						<select class="form-control" id = "programCode" name = "programCode" onchange = "changeCourseCode();">
							<c:forEach var="course" items="${sessionScope.sessionObject.courseList}" varStatus="i">
								<option value="${course.courseCode}" ${course.courseCode eq sessionScope.sessionObject.courseCode?'selected':value}>${course.courseName}</option>
								<%-- <option value="${course.courseCode}">${course.courseName}</option> --%>
							</c:forEach>
						</select>
					</div>
				</form>
			<%} %>
			<span class="separator"></span>
				<ul class="notifications">
					<li id ="alertsLI">
						<!-- <a href="#" class="dropdown-toggle notification-icon">
							<i class="fa fa-envelope"></i>
							<span class="badge">4</span>
						</a> -->
						<a href="#" title="Alerts" class="dropdown-toggle notification-icon" data-toggle="dropdown" id="alerts" onclick = "return hideAlerts()">
							<i class="fa fa-tasks"></i>
							<c:if test="${alertCount ne 0}">
								<span class="badge">${alertCount}</span>
							</c:if>
						</a>
						<div class="dropdown-menu notification-menu">
							<div class="notification-title">
								<span class="pull-right label label-default"></span>
								Alerts
							</div>
							<div class="content" id = "alertsDiv">
								<ul id = "alertsUL"></ul>
								<hr />
								<div class="text-right">
									<a href="fetchAllAlertForCurrentUser.html" target = "frame" class="view-more">View All</a>
								</div>
							</div>
						</div>
					</li>
					<li id="emailsLI">
						<!-- <a href="#" title="Emails" class="dropdown-toggle notification-icon" data-toggle="dropdown" id="emails" onclick = "return hideEmails()"> -->
						<a href="getEmailDetails.html" title="Emails" class="notification-icon" id="emails" target="frame">
						<i class="fa fa-envelope"></i>
							<c:if test="${emailCount ne 0}">
								<span class="badge">${emailCount}</span>
							</c:if>
						</a>
						<div class="dropdown-menu notification-menu">
							<div class="notification-title">
								<span class="pull-right label label-default"></span>
								E-Mail
							</div>
		
							<div class="content">
								<ul id="emailsUL">
									
								</ul>
								<hr />
								<div class="text-right">
									<a href="#" class="view-more">View All</a>
								</div>
							</div>
						</div>
					</li>
					<li id ="notificationLI">
						<!-- <a href="#" class="dropdown-toggle notification-icon" data-toggle="dropdown">
							<i class="fa fa-bell"></i>
							<span class="badge">3</span>
						</a> -->
						<a href="#" title="Notifiactions" class="dropdown-toggle notification-icon" data-toggle="dropdown" id="notification" onclick = "return hideNotification()">
							<i class="fa fa-bell"></i>
							<c:if test="${taskCount ne 0}">
								<span class="badge">${taskCount}</span>
							</c:if>
						</a>
		
						<div class="dropdown-menu notification-menu">
							<div class="notification-title">
								<span class="pull-right label label-default"></span>
								Notifications
							</div>
							<div class="content" id = "notificatinDiv">
								  <ul id = "notificationUL"> 
							  </ul>  
		
								<hr />
								<div class="text-right">
								<a href="fetchAllNotificationForCurrentUser.html"  target = "frame" class="view-more">View All</a>
							</div>
							</div>
						</div>
					</li>
					<li>
						<a href="#" title="Roles" class="dropdown-toggle notification-icon" data-toggle="dropdown">
							<i class="fa fa-user"></i>
						</a>
		
						<div class="dropdown-menu notification-menu">
							<div class="notification-title">
								<span class="pull-right label label-default">${sessionScope.sessionObject.currentRoleOrAccess}</span>
								Current Role
							</div>
							<form id="notificationPage" name="notificationPage" action="changeRoleForUser.html" method="post">
							<input type="hidden" id="nickname" value='<c:out value="${sessionScope.sessionObject.userName}"/>' />
							<input type="hidden" name="roleName" id="roleName"/>
							
							<div class="content role-content">
								<ul>
									<c:forEach var="availableRolesAndAccess" items="${sessionScope.sessionObject.availableRoles}">				
											<c:if test="${sessionScope.sessionObject.currentRoleOrAccess ne availableRolesAndAccess}">
												<li>
													<a href="#" name="${availableRolesAndAccess}" class="roleChange clearfix" >
														<div class="image">
															<i class="fa fa-user bg-info"></i>
														</div>
														<span class="title">${availableRolesAndAccess}</span>
													</a>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</div>
							</form>
						</div>
					</li>
					<li>
						<a href="#" title="Chat" class="dropdown-toggle notification-icon" data-toggle="dropdown">
							<i class="fa fa-wechat"></i>
						</a>
						<div class="dropdown-menu notification-menu">
							<div class="notification-title">
								Chat
							</div>
							<div class="content">
								<ul>
									<li>
										<a href="#modalInfo" class="clearfix minibtn" >
											<div class="image">
												<i class="fa fa-wechat bg-success"></i>
											</div>
											<span class="title">Personal Chat</span>
										</a>
									</li>
									<li>
										<a href="#" class="clearfix" id="groupChat">
											<div class="image">
												<i class="fa fa-wechat bg-warning"></i>
											</div>
											<span class="title">Group Chat</span>
										</a>
									</li>
								</ul>                                    
							</div>
						</div>
					</li>
				</ul>
				<span class="separator"></span>
				<div id="userbox" class="userbox">
					<a href="#" data-toggle="dropdown">
						<figure class="profile-picture">
							<%-- <img src="data:image/jpg;base64, ${resource.image.imagepath}" alt="Joseph Doe" class="img-circle" data-lock-picture="assets/images/%21logged-user.jpg" /> --%>
							<img src="assets/images/logo-blackbg.png;base64, ${resource.image.imagepath}" alt="Joseph Doe" class="img-circle" data-lock-picture="assets/images/%21logged-user.jpg" />
						</figure>
						<div class="profile-info">
							<span class="name">${sessionScope.sessionObject.userName}</span>
							<span class="role">${sessionScope.sessionObject.currentRoleOrAccess}</span>
						</div>
						<i class="fa custom-caret"></i>
					</a>
					<div class="dropdown-menu">
						<ul class="list-unstyled">
							<li class="divider"></li>
							<li>
								<a role="menuitem" tabindex="-1" href="viewStaffProfileDetails.html?userId=${sessionScope.sessionObject.userId}" target="frame">
								<i class="fa fa-user"></i> My Profile</a>
							</li>
							<li>
								<a role="menuitem" tabindex="-1" href="logOut.html"><i class="fa fa-power-off"></i> Logout</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- end: search & user box -->
		</header>
			<!-- end: header -->
		<div class="inner-wrapper">
			<div id ="chatInfo" class="ui-pnotify stack-bottomright" style="width: 300px; opacity: 1; display: none; cursor: auto; position: absolute; z-index: 1; top: 120px; right: 15px;">
				<div class="notification ui-pnotify-container notification-success panel" style="min-height: 16px;" id = "chatNotificationDiv" onclick = "openChatWindow()">
					<div class="ui-pnotify-closer panel-actions" style="cursor: pointer; visibility: visible;">
						<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss=""></a>
					</div>
					<div class="ui-pnotify-icon"><span class="fa fa-info"></span></div>
					<h4 class="ui-pnotify-title" style="margin-top: 0px;">Notification</h4>
					<div class="ui-pnotify-text" id="chatMsg" >Some notification text.</div>
					<input type = "hidden" id = "chatBy" name = "chatBy" >
					<input type = "hidden" id = "chatTo" name = "chatTo" >  
					<div style="margin-top: 5px; clear: both; text-align: right; display: none;"></div>
				</div>
			</div>
				<!-- start: sidebar -->
			<aside id="sidebar-left" class="sidebar-left">
				<div class="sidebar-header">
					<div class="sidebar-title">
						Navigation
					</div>
					<div class="sidebar-toggle hidden-xs" data-toggle-class="sidebar-left-collapsed" data-target="html" data-fire-event="sidebar-left-toggle">
						<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
					</div>
				</div>
				<div class="nano">
					<div class="nano-content">
						<nav id="menu" class="nav-main" role="navigation">
							<ul class="nav nav-main doc">
								<li>
									<a href="backToHome.html" target="_self" >
										<i class="fa fa-home" aria-hidden="true"></i>
										<span>Dashboard</span>
									</a>
								</li>
								<c:forEach var="role" items="${roleList}">
									<c:if test="${role.moduleName eq 'ADMISSION'}">
										<li class="nav-parent">
											<a href="#">
												<i class="fa fa-edit" aria-hidden="true"></i>
												<span>Admission</span>
											</a>
											<ul class="nav nav-children">
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Previous Drives')
													             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Current Drives')
													             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Active Drives')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Admission Drives
														</a>
														<ul class = "nav nav-children">
															<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Previous Drives')}">
																	<li><a href="admissionList.html?drive=completed" target="frame">Previous Drives</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Current Drives')}">
																	<li><a href="admissionList.html?drive=current" target="frame">Current Drives</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Active Drives')}">
																	<li><a href="activeAdmissionDrives.html" target="frame">Active Drives</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Current Openings')
													             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Form Sale Details')
													             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Admission On Process')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">	
													<li class="nav-parent">
														<a>
															Admission Openings
														</a>
														<ul class = "nav nav-children">
															<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Current Openings')}">
																	<li><a href="admissionFormsList.html" target="frame">Current Openings</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Form Sale Details')}">
																	<li><a href="saleFormDetails.html" target="frame">Form Sale Details</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Admission On Process')}">
																	<li><a href="admissionsOnProcessList.html" target="frame">Admission On Process</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Admitted Students List')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Admitted Students
														</a>
														<ul class = "nav nav-children">
															<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Admitted Students List')}">
																	<li><a href="admittedDriveList.html" target="frame">Admitted Students List</a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>
												<!-- <li class="nav-parent">
														<a>
															Portal Data
														</a>
														<ul class = "nav nav-children">
															<li>
																<a href="sendLocationDetailsToPortal.html" target="frame">Send Location Details</a>
															</li>
														</ul>
														<ul class = "nav nav-children">
															<li>
																<a href="getCourseListForAllCandidates.html" target="frame">Receive All Candidates</a>
															</li>
														</ul>
														<ul class = "nav nav-children">
															<li>
																<a href="getCourseListForScrutinizedCandidates.html" target="frame">Receive Scrutinized Candidates</a>
															</li>
														</ul>
														<ul class = "nav nav-children">
															<li>
																<a href="getCourseListForSelectedCandidates.html" target="frame">Receive Selected Candidates</a>
															</li>
														</ul>
														<ul class = "nav nav-children">
															<li>
																<a href="getCourseListForAdmittedCandidates.html" target="frame">Receive Admitted Candidates</a>
															</li>
														</ul>
														<ul class = "nav nav-children">
															<li>
																<a href="sendAlumniDataToPortal.html" target="frame">Send Passout Data and Document</a>
															</li>
														</ul>
														<ul class = "nav nav-children">
															<li>
																<a href="publishNoticeToPortal.html" target="frame">Publish Notice</a>
															</li>
														</ul>
													</li> -->
												<!-- Added by sourav.bhadra on 18-04-2018 -->
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')
													             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')
													             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')
													             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Budget Evaluation
														</a>
														<ul class="nav nav-children">
															<c:set var="i" value="0" scope="page"/>
															<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')
																             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')}">
																	<c:set var="i" value="1" scope="page" />
																</c:if>
															</c:forEach>
															<c:if test="${i eq 1}">
																<li class="nav-parent">
																	<a>
																		Reserve-Unreserve Fund Conversion
																	</a>
																	<ul class="nav nav-children">
																		<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')}">
																				<li><a href ="reserveFundEstimationForADepartment.html" target="frame">Reserve Fund Estimation</a></li>
																			</c:if>
																		</c:forEach>
																		<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')}">
																				<li><a href ="reserveToUnreserveFundConversion.html" target="frame">Unreserve Conversion</a></li>
																			</c:if>
																		</c:forEach>																	
																	</ul>
																</li>
															</c:if>
															<c:set var="i" value="0" scope="page"/>
															<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')
																             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
																	<c:set var="i" value="1" scope="page" />
																</c:if>
															</c:forEach>
															<c:if test="${i eq 1}">
																<li class="nav-parent">
																	<a>
																		Sub Department Budget Allocation
																	</a>
																	<ul class="nav nav-children">
																		<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')}">
																				<li><a href ="subDepartmentsBudgetAllocation.html" target="frame">Sub Department Budget Allocation</a></li>
																			</c:if>
																		</c:forEach>
																		<c:forEach var="functionality" items="${ADMISSIONfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
																				<li><a href ="reserveFundAllocationForSubDepartments.html" target="frame">Reserve Fund Allocation</a></li>
																			</c:if>
																		</c:forEach>
																	</ul>
																</li>
															</c:if>	
														</ul>
													</li>
												</c:if>
											</ul>
										</li>	
									</c:if>
									<c:if test="${role.moduleName eq 'LIBRARY'}">
										<li class="nav-parent">
											<a href="#">
												<i class="fa fa-book" aria-hidden="true"></i>
												<span>Library</span>
											</a>
											<ul class="nav nav-children">
												<%-- <c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Item Status')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Status Creation
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Item Status')}">
																	<li><a href="createStatusOfItem.html" target="frame">Create Item Status</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if> --%>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Book Cataloging')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Accession Register')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Books')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Book Threshold')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Book Retirement')}">
														<c:set var="i" value="1" scope="page" />		
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Book Management
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Book Cataloging')}">
																	<li><a href="getBookCatalogue.html" target="frame">Book Cataloging</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Accession Register')}">
																	<li><a href="addBook.html" target="frame">Accession Register</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Books')}">
																	<li><a href="listBook.html" target="frame">List Books</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Book Threshold')}">
																	<li><a href="addThresholdForBook.html" target="frame">Add Book Threshold</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Book Retirement')}">
																	<li><a href="retirementBookCodeList.html" target="frame">Book Retirement</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>	
												<li class="nav-parent">
													<a>
														Book-Vendor Mapping
													</a>
													<ul class="nav nav-children">																	
														<li><a href="mapBookVendor.html" target="frame">Map Book-Vendor</a></li>
														<li><a href="bookVendorList.html" target="frame">Vendor List</a></li>
													</ul>
												</li>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Stock & Profiling')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Book Requisition')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('View Book Requisition')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Book Requisition
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Stock & Profiling')}">
																	<li><a href="viewBookStock.html" target="frame">Stock & Profiling</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Book Requisition')}">
																	<li><a href="createRequisition.html" target="frame">Create Book Requisition</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('View Book Requisition')}">
																	<li><a href="viewRequisition.html" target="frame">View Book Requisition</a></li>
																</c:if>
															</c:forEach>
															<!-- <li><a href="purchaseOrderDetailsList.html" target="frame">Add Book To Catalogue</a></li> -->
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Library Policy')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Rating Policy')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Policy Configure 
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Library Policy')}">
																	<li><a href="libraryPolicy.html" target="frame">Library Policy</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Rating Policy')}">
																	<li><a href="ratingPolicy.html" target="frame">Rating Policy</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Loan Request')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Loan Request')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Return Book')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Library Fine')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Book Loan Request
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Loan Request')}">
																	<li><a href="createLodgingRequest.html" target="frame">Create Loan Request</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Loan Request')}">
																	<li><a href="bookAllocation.html" target="frame">List Loan Request</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Return Book')}">
																	<li><a href="userIdForReturnBook.html" target="frame">Return Book</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Library Fine')}">
																	<li><a href="getBookDetailsForFine.html" target="frame">Library Fine</a>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Book Allocation Details')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Book Allocated Student List')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Book Allocation
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Book Allocation Details')}">
																	<li><a href="listStudentBookAllocation.html" target="frame">Book Allocation Details</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Book Allocated Student List')}">
																	<li><a href="getBookAllocatedStudentDetails.html" target="frame">Book Allocated Student List</a>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
												<!-- <li class="nav-parent">
													<a>
														Reading Habit
													</a>
													<ul class="nav nav-children">																	
														<li><a href="userForCheckBookReadingHabit.html" target="frame">Reading Habit of Resource</a></li>
													</ul>
												</li> -->
												<!-- Added by sourav.bhadra on 18-04-2018 -->
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')
													             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')
													             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')
													             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
												<li class="nav-parent">
														<a>
															Budget Evaluation
														</a>
														<ul class="nav nav-children">
															<c:set var="i" value="0" scope="page"/>
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')
																             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')}">
																	<c:set var="i" value="1" scope="page" />
																</c:if>
															</c:forEach>
															<c:if test="${i eq 1}">
																<li class="nav-parent">
																	<a>
																		Reserve-Unreserve Fund Conversion
																	</a>
																	<ul class="nav nav-children">
																		<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')}">
																				<li><a href ="reserveFundEstimationForADepartment.html" target="frame">Reserve Fund Estimation</a></li>
																			</c:if>
																		</c:forEach>
																		<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')}">
																				<li><a href ="reserveToUnreserveFundConversion.html" target="frame">Unreserve Conversion</a></li>
																			</c:if>
																		</c:forEach>																	
																	</ul>
																</li>
															</c:if>
															<c:set var="i" value="0" scope="page"/>
															<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')
																             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
																	<c:set var="i" value="1" scope="page" />
																</c:if>
															</c:forEach>
															<c:if test="${i eq 1}">
																<li class="nav-parent">
																	<a>
																		Sub Department Budget Allocation
																	</a>
																	<ul class="nav nav-children">
																		<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')}">
																				<li><a href ="subDepartmentsBudgetAllocation.html" target="frame">Sub Department Budget Allocation</a></li>
																			</c:if>
																		</c:forEach>
																		<c:forEach var="functionality" items="${LIBRARYfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
																				<li><a href ="reserveFundAllocationForSubDepartments.html" target="frame">Reserve Fund Allocation</a></li>
																			</c:if>
																		</c:forEach>
																	</ul>
																</li>
															</c:if>	
														</ul>
													</li>
												</c:if>	
											</ul>
										</li>
									</c:if>
									<c:if test="${role.moduleName eq 'OFFICE ADMINISTRATION'}">
										<li class="nav-parent">
											<a href="#">
												<i class="fa fa-align-left" aria-hidden="true"></i>
												<span>Office Administration</span>
											</a>
											<ul class="nav nav-children">
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Department')
													             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Department To Head')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Department 
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Department')}">
																	<li><a href="viewDepartment.html" target="frame">Add Department</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Department To Head')}">
																	<li><a href="mapDepartmentToHead.html" target="frame">Map Department To Head</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Time Table')
													             ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Time Table Parameter')
													             ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('View Time Table')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Class Time Table 
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Time Table')}">
																	<li><a href="configureTimeTable.html" target="frame">Configure Time Table</a></li>
																</c:if>
															</c:forEach>		
															<!-- /*added by ranita.sur on 29082017 for configute Time Table Parameter*/ -->
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Time Table Parameter')}">
																	<li><a href="configureTimeTableParameter.html" target="frame"> Time Table Parameter</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('View Time Table')}">
																	<li><a href="viewTimeTable.html" target="frame">View Time Table</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Social Category')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Manage Social Category
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Social Category')}">
																	<li><a href="getSocialCategory.html" target="frame">Social Category</a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Vendor Type')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Vendor')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Vendor Management
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Vendor Type')}">
																	<li><a href="addVendorType.html" target="frame">Add Vendor Type</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Vendor')}">
																	<li><a href="addVendor.html" target="frame">Add Vendor</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('School Bank details')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Bank Details 
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('School Bank details')}">																	
																	<li><a href="getBankDetails.html" target="frame">School Bank details</a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Fees Structure')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Fees Template')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Assign Amount')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Assigned Template List')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Admission Fees')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Session Fees')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Fees Management
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Fees Structure')}">
																	<li><a href="createFeesStructure.html" target="frame">Create Fees Structure</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Fees Template')}">
																	<li><a href="createStudentFeesTemplate.html" target="frame">Create Fees Template</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Assign Amount')}">
																	<li><a href="assignAmountToStudentFeesTemplate.html" target="frame">Assign Amount</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Assigned Template List')}">
																	<li><a href="getStudentFeesTemplateWithAmountList.html" target="frame">Assigned Template List</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Admission Fees')}">
																	<li><a href="admissionDriveListForFeesSubmission.html" target="frame">Admission Fees</a>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Session Fees')}">
																	<li><a href="receiveSessionFee.html" target="frame">Session Fees</a></li>
																</c:if>
															</c:forEach>
															<!-- <li><a href="assignAmountToProgramAndTermWiseFeesTemplate.html" target="frame">Program-Term Wise Fees</a></li> -->
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Leave Category')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Leave Policy')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Set Leave Structure')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Leave Structure
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Leave Category')}">
																	<li><a href="leaveCategory.html" target="frame">Leave Category</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Leave Policy')}">
																	<li><a href="leavePolicy.html" target="frame">Leave Policy</a></li> <!-- added by Saif to set leave policy -->
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Set Leave Structure')}">
																	<li><a href="leaveStructure.html" target="frame">Set Leave Structure</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Student')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Student')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('View Profile of Student')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Student Management 
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Student')}">
																	<li><a href="getStudentDetails.html" target="frame">Create Student</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Student')}">
																	<li><a href="getStudentList.html" target="frame">List Student</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('View Profile of Student')}">
																	<!-- <li><a href="viewStudentProfileList.html" target="frame">Student Profile</a></li> -->
																	<li><a href="getStudentRollNoForProfile.html" target="frame">View Profile of Student</a></li>
																</c:if>
															</c:forEach>															
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Scholarship')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Scholarship
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Scholarship')}">
																	<li><a href="getScholarship.html" target="frame">Create Scholarship</a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Academic Year')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Academic Term')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Financial Year')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Location Teacher Mapping')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Academic Setup
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Academic Year')}">
																	<li><a href="getAcademicYear.html" target="frame">Create Academic Year</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Academic Term')}">
																	<li><a href="createAcademicTerm.html" target="frame">Create Academic Term</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Financial Year')}">
																	<li><a href="configureFinancialYear.html" target="frame">Configure Financial Year</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Location Teacher Mapping')}">
																	<li><a href="getLocationTeacherMapping.html" target="frame">Location Teacher Mapping</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('QR Code For Teacher')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('QR Code For Student')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('QR Code For Book')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('QR Code For Hall Pass')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Read QR Code')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															QR Code
														</a>
														<ul class="nav nav-children">
															<c:set var="i" value="0" scope="page"/>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('QR Code For Teacher')
																			||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('QR Code For Student')
																			||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('QR Code For Book')
																			||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('QR Code For Hall Pass')}">
																	<c:set var="i" value="1" scope="page" />
																</c:if>
															</c:forEach>
															<c:if test="${i eq 1}">
																<li class="nav-parent">
																	<a>Generate QR Code</a>
																	<ul class="nav nav-children">
																		<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('QR Code For Teacher')}">
																				<li><a href="qrcodeForTeacher.html" target="frame">QR Code For Teacher</a></li>
																			</c:if>
																		</c:forEach>
																		<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('QR Code For Student')}">
																				<li><a href="qrcodeForStudent.html" target="frame">QR Code For Student</a></li>
																			</c:if>
																		</c:forEach>
																		<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('QR Code For Book')}">
																				<li><a href="qrcodeForBook.html" target="frame">QR Code For Book</a></li>
																			</c:if>
																		</c:forEach>
																		<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('QR Code For Hall Pass')}">
																				<li><a href="createResult.html?link=hallPass" target="frame">QR Code For Hall Pass</a></li>
																			</c:if>
																		</c:forEach>
																	</ul>
																</li>
															</c:if>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Read QR Code')}">
																	<li><a href="readQRCode.html" target="frame">Read QR Code</a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Working Days')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Configure Working Days')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Special Days')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Configure Special Days')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Working Days Setup
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Working Days')}">
																	<li><a href="configureWorkingDays.html" target="frame">Configure Working Days</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Configure Working Days')}">
																	<li><a href="showListConfigureWorkingDays.html" target="frame">List Configure Working Days</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Special Days')}">
																	<li><a href="configureSpecialDays.html" target="frame">Configure Special Days</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Configure Special Days')}">
																	<li><a href="showListSpecialDays.html" target="frame">List Configure Special Days</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Student Attendance')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Teacher Attendance')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Attendance Calendar')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload Teacher Attendance')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Download Teacher Attendance')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload Student Attendance')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Download Student Attendance')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Attendance
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Student Attendance')}">
																	<li><a href="studentDailyAttendance.html" target="frame">Student Attendance</a></li>	<!-- NEW LINK -->
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Teacher Attendance')}">
																	<li><a href="teacherAttendancePost.html" target="frame">Teacher Attendance</a></li>	<!-- NEW LINK -->
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Attendance Calendar')}">
																	<li><a href="attendanceCalendar.html" target="frame">Attendance Calendar</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload Teacher Attendance')}">
																	<li><a href="teacherAttendanceShow.html" target="frame">Upload Teacher Attendance</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Download Teacher Attendance')}">
																	<li><a href="resourceAttendanceDownload.html?folderParam=AcademicYear&fileParam=noFile" target="frame">Download Teacher Attendance</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload Student Attendance')}">
																	<li><a href="studentAttendanceShow.html" target="frame">Upload Student Attendance</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload Student Attendance')}">
																	<li><a href="resourceStudentAttendanceDownload.html?folderParam=AcademicYear&fileParam=noFile" target="frame">Download Student Attendance</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
											<!-- <li class="nav-parent">
													<a>
														IT Sections
													</a>
													<ul class="nav nav-children">																	
														<li><a href="addITSections.html" target="frame">Add I.T. Sections</a></li>
														<li><a href="viewITSectionRebates.html" target="frame">Create/Edit I.T. Section Rebates</a></li>
														<li><a href="deductionAmountLimit.html" target="frame">I.T. Section Deduction Limit</a></li>
														<li><a href="viewRebateAmountLimit.html" target="frame">Create I.T. Section Rebate Limit</a></li>
														<li><a href="editRebateAmountLimit.html" target="frame">View/Edit I.T. Section Rebate Limit</a></li>
													</ul>
												</li> -->
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Vendor Rating Policy')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Attendance Policy')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Exam Policy')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Policy Configure 
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Vendor Rating Policy')}">
																	<li><a href="vendorRatingPolicy.html" target="frame">Vendor Rating Policy</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Attendance Policy')}">
																	<li><a href="attendancePolicy.html" target="frame">Attendance Policy</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Exam Policy')}">
																	<li><a href="examPolicy.html" target="frame">Exam Policy</a></li>
																</c:if>
															</c:forEach>
															<!-- <li><a href="programPolicy.html" target="frame">Program Policy</a></li> -->
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create And Update Notice')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Notice Management 
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create And Update Notice')}">
																	<li><a href="noticeBoard.html" target="frame">Create And Update Notice</a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create And Update Events')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Events Management 
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create And Update Events')}">
																	<li><a href="majorEvents.html" target="frame">Create And Update Events</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('TC Generation')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															TC Management
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('TC Generation')}">																	
																	<li><a href="getTC.html" target="frame">TC Generation</a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>	
												<!-- <li class="nav-parent">
													<a>
														View Roll Number
													</a>
													<ul class="nav nav-children">																	
														<li><a href="assignRollNumber.html" target="frame">View Roll Number</a></li>
													</ul>
												</li> -->
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Task')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Category')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Category Recipient')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Ticket Status')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Task Status')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Category With Survey')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Category With SLA')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Category-Template-User For SLA')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Task With Event Template')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Key With Category')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Department With Category')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Approval
														</a>
														<ul class="nav nav-children">
														<%-- <li class="nav-parent">
																<a>
																	Approval Groups
																</a>
																<ul class="nav nav-children">	
																	<!-- <li><a href="createUserGroup.html" target="frame">Create User Group</a></li> -->
	<!-- 																<li><a href="viewAllUserGroups.html" target="frame">View All User Groups</a></li> -->
																<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
															 		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create User Group')}">
																	<li><a href="createApprovers.html" target="frame">Create User Group</a></li>
																	</c:if>
																	</c:forEach>
																</ul>
															</li>	 --%>
															<li class="nav-parent">
																<a>
																	Task Configuration
																</a>
																<ul class="nav nav-children">
																	<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																 		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Task')}">	
																			<li><a href="createNewTask.html" target="frame">Create Task</a></li>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																 		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Category')}">
																			<li><a href="createCategory.html" target="frame">Create Category</a></li>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																 		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Category Recipient')}">
																			<li><a href="createRecepientCategoryMapping.html" target="frame">Create Category Recipient</a></li>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																 		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Ticket Status')}">
																			<li><a href="createTicketStatus.html" target="frame">Create Ticket Status</a></li>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																 		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Task Status')}">
																			<li><a href="createTaskStatus.html" target="frame">Create Task Status</a></li>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																 		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Category With Survey')}">
																			<li><a href="mapCategoryWithSurvey.html" target="frame">Map Category With Survey</a></li>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																 		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Category With SLA')}">
																			<li><a href="mapCategoryWithSLA.html" target="frame">Map Category With SLA</a></li>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																 		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Category-Template-User For SLA')}">
																			<li><a href="mapCategoryTemplateUserSLA.html" target="frame">Map Category-Template-User For SLA</a></li>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																 		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Task With Event Template')}">
																			<li><a href="mapTaskWithEventTemplate.html" target="frame">Map Task With Event Template</a></li>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																 		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Key With Category')}">
																			<li><a href="mapKeyWithCategory.html" target="frame">Map Key With Category</a></li>
																		</c:if>
																	</c:forEach>
																	<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																 		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Department With Category')}">
																			<li><a href="mapDepartmentWithCategory.html" target="frame">Map Department With Category</a></li>
																		</c:if>
																	</c:forEach>
																	<%-- <c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																 		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Task Allocation')}">	
																			<li><a href="allocateTask.html" target="frame">Task Allocation</a></li>
																		</c:if>
																		</c:forEach>	 --%>
																		<!-- <li><a href="createApprovers.html" target="frame">Configure Approver Group</a></li> -->
																		<!-- <li><a href="createApprovalOrder.html" target="frame">Configure Approval Order</a></li> -->
																	</ul>
																</li>
															
															<%-- <li class="nav-parent">
																<a>
																	Configure Task Approvers
																</a>
																<ul class="nav nav-children">	
																	<!-- <li><a href="createApprovers.html" target="frame">Setup Approvers</a></li> -->
	<!-- 																<li><a href="viewApproversList.html" target="frame">View All Approvers</a></li> -->
																<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
															 		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Approval Order')}">
																		<li><a href="createApprovalOrder.html" target="frame">Configure Approval Order</a></li>
																		</c:if>
																	</c:forEach>
																</ul>
															</li> --%>
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Question & Answer')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Survey Configuration')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Edit Survey Configuration')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Survey & Feedback
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Question & Answer')}">
																	<li><a href="configureQuestionAnswer.html" target="frame">Configure Question & Answer</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Survey Configuration')}">
																	<li><a href="configureSurveyQuestionAnswer.html" target="frame">Survey Configuration</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Edit Survey Configuration')}">
																	<li><a href="configureSurveyQuestionAnswerForEdit.html" target="frame">Edit Survey Configuration</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Disciplianry Code')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Disciplinary Action')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Create Disciplinary Action
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Disciplinary Code')}">
																	<li><a href="createDisciplinaryCode.html" target="frame">Create Disciplinary Code</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Disciplinary Action')}">
																	<li><a href="addDisciplinaryAction.html" target="frame">Disciplinary Action</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Activity Log List')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">	
													<li class="nav-parent">		<!-- Added by Saif Date- 12/03/2018 -->
														<a>
															Activity Log Management
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${OFFICEADMINISTRATIONfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Activity Log List')}">
																	<li><a href="showActivityLogList.html" target="frame">Activity Log List</a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>
											</ul>
										</li>
									</c:if>
									<c:if test="${role.moduleName eq 'ACADEMICS'}">
										<li class="nav-parent">
											<a href="#">
												<i class="fa fa-mortar-board" aria-hidden="true"></i>
												<span>Academics</span>
											</a>
											<ul class="nav nav-children">
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Subject')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Configure Subject
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Subject')}">
																	<li><a href="getSubjectGroup.html" target="frame">Create Subject</a></li>
																	<!-- <li><a href="getSubject.html" target="frame">Create Subject</a></li> -->
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Course Type')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Standard & Section')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Enter Standard Details')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Standard Subject Mapping')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Courses
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Course Type')}">
																	<li><a href="getCourseType.html" target="frame">Create Course Type</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Standard & Section')}">
																	<li><a href="getStandard.html" target="frame">Create Standard & Section</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Enter Standard Details')}">
																	<li><a href="getCourse.html" target="frame">Enter Standard Details</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Standard Subject Mapping')}">
																	<li><a href="getCourseSubjectMapping.html" target="frame">Standard Subject Mapping</a></li>
																</c:if>
															</c:forEach>
																<!-- <li><a href="listCourse.html" target="frame">Course List</a></li> -->
																<!-- <li><a href="getTerm.html" target="frame">Create Term</a></li>
																<li><a href="getTermCourseMapping.html" target="frame">Standard Term Subject Mapping</a></li> 
																<li class="nav-parent">
																<a>
																	Admission Drive
																</a>
																<ul class="nav nav-children">
																	<li><a href="listOfProgramsToPublish.html" target="frame"> Available Course List</a></li>
																	<li><a href="listOfPublishedPrograms.html" target="frame"> Published Drives </a></li>
																</ul>
															</li> -->
														</ul>
													</li>
												</c:if>	
												<!-- <li class="nav-parent">
													<a>
														Semester
													</a>
													<ul class="nav nav-children">
														<li><a href="getTerm.html" target="frame">Create Semester</a></li>
														<li><a href="getTermCourseMapping.html" target="frame">Programme Semester Course Mapping</a></li>
													</ul>
												</li> -->
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Teacher-Subject')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Teacher-Subject Mapping List')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Teacher-Standard Allotment	
														</a>																
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Teacher-Subject')}">
																	<li><a href="viewTeacherSubjectMapping.html" target="frame">Map Teacher-Subject</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Teacher-Subject Mapping List')}">
																	<li><a href="teacherSubjectMappingList.html" target="frame">Teacher-Subject Mapping List</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Teacher-Class')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Class Teacher')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Teacher-Class Allotment	
														</a>																
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Teacher-Class')}">
																	<li><a href="teacherClassMapping.html" target="frame">Map Teacher-Class</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Class Teacher')}">
																	<li><a href="createClassTeacher.html" target="frame">Create Class Teacher </a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Student List')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Student Standard Subject Mapping')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Edit Student Standard Subject Mapping')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Student Promotion')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Search Ex Students')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Event')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Student Achievements')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Student
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Student List')}">
																	<li><a href="getStudentList.html" target="frame">Student List</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Student Standard Subject Mapping')}">
																	<li><a href="studentCourseSubjectMapping.html?link=create" target="frame">Create Student Standard Subject Mapping</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Edit Student Standard Subject Mapping')}">		
																	<li><a href="studentCourseSubjectMapping.html?link=edit" target="frame">Edit Student Standard Subject Mapping</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Student Promotion')}">
																	<li><a href="getStudentPromotion.html" target="frame">Student Promotion</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Search Ex Students')}">
																	<li><a href="getSearchExStudents.html" target="frame">Search Ex Students</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Event')}">
																	<li><a href="createEvent.html" target="frame">Create Event</a></li>
																</c:if>
															</c:forEach>	
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Student Achievements')}">
																	<li><a href="getStudentAchievments.html" target="frame">Student Achievements</a></li>
																</c:if>
															</c:forEach>		
															
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Assign Section To Student')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Assign Section
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Assign Section To Student')}">
																	<li><a href="createAssignSection.html" target="frame">Assign Section To Student</a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Exam Type')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Exam')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Set Exam Marks')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload Result')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Exam & Result
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Exam Type')
																			||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Exam')
																			||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Set Exam Marks')
																			||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload Result')}">
																	<c:set var="i" value="1" scope="page" />
																</c:if>
															</c:forEach>
															<c:if test="${i eq 1}">
																<li class="nav-parent">
																	<a>
																		New System 
																	</a>
																	<ul class= "nav nav-children">
																		<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Exam Type')}">
																				<li><a href="examTypeNew.html" target="frame">Exam Type</a></li>
																			</c:if>
																		</c:forEach>
																		<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Exam')}">
																				<li><a href="createExamNew.html" target="frame">Create Exam</a></li>
																			</c:if>
																		</c:forEach>
																		<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Set Exam Marks')}">
																				<li><a href="setExamMarksNew.html" target="frame">Set Exam Marks</a></li>
																			</c:if>
																		</c:forEach>
																		<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload Result')}">
																				<li><a href="setUploadResultNew.html" target="frame">Upload Result</a></li>
																			</c:if>
																		</c:forEach>		
																	</ul>
																</li>
															</c:if>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Exam')
																			||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Set Exam Marks')
																			||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload Result')}">
																	<c:set var="i" value="1" scope="page" />
																</c:if>
															</c:forEach>
															<c:if test="${i eq 1}">
																<li class="nav-parent">
																	<a>
																		Old System 
																	</a>
																	<ul class= "nav nav-children">
																		<!-- <li><a href="createExamTypeName.html" target="frame">Create Exam Type Name</a></li> -->
																		<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Exam')}">
																				<li><a href="createExam.html" target="frame">Create Exam</a></li>
																			</c:if>
																		</c:forEach>
																		<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Set Exam Marks')}">
																				<li><a href="setExamMarks.html" target="frame">Set Exam Marks</a></li>
																			</c:if>
																		</c:forEach>
																		<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																			<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload Result')}">	
																				<li><a href="getUploadResult.html" target="frame">Upload Result</a></li>
																			</c:if>
																		</c:forEach>		
																	</ul>
																</li>
															</c:if>	
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create User Defined Exams')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Set User Defined Exams Marks')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload User Defined Exams Result')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															User Defined Exam
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create User Defined Exams')}">
																	<li><a href="createUserdefinedExams.html" target="frame">Create User Defined Exams</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Set User Defined Exams Marks')}">
																	<li><a href="createUserdefinedExamsMarks.html" target="frame">Set User Defined Exams Marks</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload User Defined Exams Result')}">
																	<li><a href="getUserExamUploadResult.html" target="frame">Upload User Defined Exams Result</a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Student Result Activity Log')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload Question Papers')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload Assignment')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Download Question Papers')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reset Result')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Co-Scholastic Result List New')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload Co-Scholastic Result New')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Examination
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Student Result Activity Log')}">
																	<li><a href="getActivityLog.html?module=ACADEMICS&functionality=STUDENT RESULT" target="frame">Student Result Activity Log</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload Question Papers')}">
																	<li><a href="viewUploadQuestionPapers.html" target="frame">Upload Question Papers</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Upload Assignment')}">
																	<li><a href="uploadAssignment.html" target="frame">Upload Assignment</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Download Question Papers')}">
																	<li><a href="viewDownloadQuestionPapers.html?folderParam=AcademicYear&fileParam=noFile" target="frame">Download Question Papers</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reset Result')}">
																	<li><a href="getResetResult.html" target="frame">Reset Result</a></li>
																</c:if>
															</c:forEach>
															<!-- New CBSE System start -->
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Co-Scholastic Result List New')}">
																	<li><a href="getCoScholasticResultList.html" target="frame">Co-Scholastic Result List New</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Co-Scholastic Result List New')}">
																	<li><a href="createCoScholasticResultNew.html" target="frame">Upload Co-Scholastic Result New</a></li>
																</c:if>
															</c:forEach>		
															<!-- New CBSE System end -->
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Set Promotional Exam')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Promotional Exam
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Set Promotional Exam')}">
																	<li><a href="setPromotionalExam.html" target="frame">Set Promotional Exam</a></li>
																</c:if>
															</c:forEach>
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Exam Seating')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Display Exam Seating')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Display Exam Routine')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Exam Seating Arrangement
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Exam Seating')}">
																	<li><a href="setExternalExam.html" target="frame">Configure Exam Seating</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Display Exam Seating')}">
																	<li><a href="displaySittingArrangement.html" target="frame">Display Exam Seating</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Display Exam Routine')}">		
																	<li><a href="displayExamRoutine.html" target="frame">Display Exam Routine</a></li>
															   	</c:if>
															</c:forEach>		
														</ul>
													</c:if>
													<!-- Added by sourav.bhadra on 18-04-2018 -->
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')
														             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')
														             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')
														             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Budget Evaluation
															</a>
															<ul class="nav nav-children">
																<c:set var="i" value="0" scope="page"/>
																<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')
																	             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')}">
																		<c:set var="i" value="1" scope="page" />
																	</c:if>
																</c:forEach>
																<c:if test="${i eq 1}">
																	<li class="nav-parent">
																		<a>
																			Reserve-Unreserve Fund Conversion
																		</a>
																		<ul class="nav nav-children">
																			<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')}">
																					<li><a href ="reserveFundEstimationForADepartment.html" target="frame">Reserve Fund Estimation</a></li>
																				</c:if>
																			</c:forEach>
																			<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')}">
																					<li><a href ="reserveToUnreserveFundConversion.html" target="frame">Unreserve Conversion</a></li>
																				</c:if>
																			</c:forEach>																	
																		</ul>
																	</li>
																</c:if>
																<c:set var="i" value="0" scope="page"/>
																<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')
																	             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
																		<c:set var="i" value="1" scope="page" />
																	</c:if>
																</c:forEach>
																<c:if test="${i eq 1}">
																	<li class="nav-parent">
																		<a>
																			Sub Department Budget Allocation
																		</a>
																		<ul class="nav nav-children">
																			<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')}">
																					<li><a href ="subDepartmentsBudgetAllocation.html" target="frame">Sub Department Budget Allocation</a></li>
																				</c:if>
																			</c:forEach>
																			<c:forEach var="functionality" items="${ACADEMICSfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
																					<li><a href ="reserveFundAllocationForSubDepartments.html" target="frame">Reserve Fund Allocation</a></li>
																				</c:if>
																			</c:forEach>
																		</ul>
																	</li>
																</c:if>	
															</ul>
														</li>
													</c:if>
												</ul>
											</li>
										</c:if>
										<c:if test="${role.moduleName eq 'ERP'}">
											<li class="nav-parent">
												<a href="#">
													<i class="fa fa-server" aria-hidden="true"></i>
													<span>Payroll</span>
												</a>
												<ul class="nav nav-children">
													<c:set var="i" value="0" scope="page" />
													<c:forEach var="functionality" items="${ERPfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Designation Type')
														           || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Designation')
														           || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Designation Level')
														           || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Designation Level Mapping')
														           || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Edit Designation Level Mapping')
														           || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Job Type')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Payroll SetUp
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Designation Type')}">
																		<li><a href="viewDesignationType.html" target="frame">Add Designation Type</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Designation')}">
																		<li><a href="viewDesignation.html" target="frame">Add Designation</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Designation Level')}">
																		<li><a href="viewDesignationLevel.html" target="frame">Add Designation Level</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Designation Level Mapping')}">
																		<li><a href="designationLevelMapping.html" target="frame">Designation Level Mapping</a></li>
																	</c:if>
																</c:forEach>		
																<!--	modified by ranita.sur on 20092017 -->
																<!-- <li><a href="editDesignationLevelMapping.html" target="frame">Edit Designation Level Mapping</a></li> -->
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Job Type')}">
																		<li><a href="viewJobType.html" target="frame">Job Type</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page" />
													<c:forEach var="functionality" items="${ERPfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Schedule Interview')
														           || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Scheduled Interview List')
														           || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Teacher Interview Process')
														           || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Interview List')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Interview
															</a>																
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Schedule Interview')}">
																		<li><a href="teacherInterviewSchedule.html" target="frame">Schedule Interview</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Scheduled Interview List')}">		
																		<li><a href="teacherInterviewScheduleList.html" target="frame">Scheduled Interview List</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Teacher Interview Process')}">
																		<li><a href="teacherInterview.html" target="frame">Teacher Interview Process</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Interview List')}">
																		<li><a href="teacherInterviewList.html" target="frame">Interview List</a></li>
																	</c:if>
																</c:forEach>	
															</ul>
														</li>
													</c:if>	
													<c:set var="i" value="0" scope="page" />
													<c:forEach var="functionality" items="${ERPfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Salary BreakUp')
														           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Salary Template')
														           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Salary Template List')
														           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Miscellaneous Tax Type Slab')
														           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('View Slabs For Miscellaneous Tax')
														           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Salary Mapping And Promotion')
														           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Employer Contribution')
														           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Disburse Salary')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">			
														<li class="nav-parent">
															<a>
																Salary Details
															</a>																
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Salary BreakUp')}">
																		<li><a href="viewSalaryBreakUp.html" target="frame">Salary BreakUp</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Salary Template')}">
																		<li><a href="salaryTemplate.html" target="frame">Salary Template</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Salary Template List')}">
																		<li><a href="salaryTemplateList.html" target="frame">Salary Template List</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Miscellaneous Tax Type Slab')}">
																		<li><a href="professionalTaxSlab.html" target="frame">Miscellaneous Tax Type Slab</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('View Slabs For Miscellaneous Tax')}">
																		<li><a href="viewMiscTaxSlab.html" target="frame">View Slabs For Miscellaneous Tax</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Salary Mapping And Promotion')}">
																		<li><a href="staffSalaryMappingAndPromotion.html" target="frame">Salary Mapping And Promotion</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Employer Contribution')}">
																		<li><a href="employerContribution.html" target="frame">Employer Contribution</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Disburse Salary')}">
																		<li><a href="viewStaffSalarySlip.html" target="frame">Disburse Salary</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>	
													<c:set var="i" value="0" scope="page" />
													<c:forEach var="functionality" items="${ERPfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Enter Employee Details')
														           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Staff List')
														           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('View Staff Profile')
														           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Staff Logging Activity Details')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Employee Details
															</a>																
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Enter Employee Details')}">
																		<li><a href="viewEmployeeDetails.html" target="frame">Enter Employee Details</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Staff List')}">
																		<li><a href="staffList.html?resourceType=TEACHER" target="frame">Staff List</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('View Staff Profile')}">
																		<li><a href="viewStaffProfile.html" target="frame">View Staff Profile</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Staff Logging Activity Details')}">
																		<li><a href="getActivityLog.html?module=ERP&functionality=STAFF DETAILS" target="frame">Staff Logging Activity Details </a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>		
													<c:set var="i" value="0" scope="page" />
													<c:forEach var="functionality" items="${ERPfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Manual Leave Update')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Leave Of Employee
															</a>																
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Manual Leave Update')}">
																		<!-- <li><a href="listPendingTask.html" target="frame">Pending Leave Tasks</a></li>
																		<li><a href="listTaskHistory.html" target="frame">Leave Task History</a></li> -->
																		<li><a href="manualLeaveUpdate.html" target="frame">Manual Leave Update</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>	
													<c:set var="i" value="0" scope="page" />
													<c:forEach var="functionality" items="${ERPfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Staff Retirement')
														           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Retired Staff List')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">			
														<li class="nav-parent">
															<a>
																Retirement Of Employee
															</a>																
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Staff Retirement')}">
																		<li><a href="createRetirement.html" target="frame">Staff Retirement</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Retired Staff List')}">
																		<li><a href="retirementList.html" target="frame">Retired Staff List</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>	
													<!-- Added by sourav.bhadra on 18-04-2018 -->
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${ERPfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')
														             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')
														             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')
														             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Budget Evaluation
															</a>
															<ul class="nav nav-children">
																<c:set var="i" value="0" scope="page"/>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')
																	             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')}">
																		<c:set var="i" value="1" scope="page" />
																	</c:if>
																</c:forEach>
																<c:if test="${i eq 1}">
																	<li class="nav-parent">
																		<a>
																			Reserve-Unreserve Fund Conversion
																		</a>
																		<ul class="nav nav-children">
																			<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')}">
																					<li><a href ="reserveFundEstimationForADepartment.html" target="frame">Reserve Fund Estimation</a></li>
																				</c:if>
																			</c:forEach>
																			<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')}">
																					<li><a href ="reserveToUnreserveFundConversion.html" target="frame">Unreserve Conversion</a></li>
																				</c:if>
																			</c:forEach>																	
																		</ul>
																	</li>
																</c:if>
																<c:set var="i" value="0" scope="page"/>
																<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')
																	             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
																		<c:set var="i" value="1" scope="page" />
																	</c:if>
																</c:forEach>
																<c:if test="${i eq 1}">
																	<li class="nav-parent">
																		<a>
																			Sub Department Budget Allocation
																		</a>
																		<ul class="nav nav-children">
																			<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')}">
																					<li><a href ="subDepartmentsBudgetAllocation.html" target="frame">Sub Department Budget Allocation</a></li>
																				</c:if>
																			</c:forEach>
																			<c:forEach var="functionality" items="${ERPfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
																					<li><a href ="reserveFundAllocationForSubDepartments.html" target="frame">Reserve Fund Allocation</a></li>
																				</c:if>
																			</c:forEach>
																		</ul>
																	</li>
																</c:if>	
															</ul>
														</li>
													</c:if>
												</ul>
											</li>
										</c:if>
										<c:if test="${role.moduleName eq 'FINANCE'}">
											<li class="nav-parent">
												<a href="#">
													<i class="fa fa-inr" aria-hidden="true"></i>
													<span>Finance</span>
												</a>
												<ul class="nav nav-children">
													<c:set var="i" value="0" scope="page" />
													<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Group')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Group
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Group')}">
																		<li><a href ="groupCreatePage.html" target="frame">Create Group</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page" />
													<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Ledger')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Ledger
															</a>
															<ul class="nav nav-children">
																<li><a href ="ledgerCreatePage.html" target="frame">Create Ledger</a></li>
															</ul>
														</li>
													</c:if>	
													<!-- added by sourav.bhadra on 20-04-2018 -->
													<c:set var="i" value="0" scope="page" />
													<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Voucher Type')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Voucher Type
															</a>
															<ul class="nav nav-children">	
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Voucher Type')}">
																		<li><a href ="createVoucherType.html" target="frame">Create Voucher Type</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page" />
													<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('TAX Setup')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																TAX
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('TAX Setup')}">
																		<li><a href ="setupTaxPercentages.html" target="frame">TAX Setup</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page" />
													<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Working Area')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Approved Lists')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Paid Lists')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Security Deposit')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Transaction
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Working Area')}">
																		<!-- <li><a href ="createTransactionPage.html" target="frame">Create Transaction</a></li> -->
																		<li><a href ="transactionWorkingAreaList.html" target="frame">Working Area</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Approved Lists')}">
																		<li><a href ="approvedList.html" target="frame">Approved Lists</a></li><!-- Added by Saif 19-09-2017 -->
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Paid Lists')}">
																		<li><a href ="paymentDoneList.html" target="frame">Paid Lists</a></li><!-- Added by Saif 19-09-2017 -->
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Security Deposit')}">
																		<li><a href ="securityDeposit.html" target="frame">Security Deposit</a></li>
																		<!-- <li><a href ="passbook.html" target="frame">Passbook Entry</a></li> -->
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page" />
													<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Trial Balance')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Income & Expenditure')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('CashBook')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Balance Sheet')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Bank Reconcilation Statement')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Profit & Loss Account')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Entry In Day Book')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('View Day Book')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Ledger Wise View')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Financial Output
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Trial Balance')}">
																		<li><a href ="trialBalance.html" target="frame">Trial Balance</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Income & Expenditure')}">
																		<li><a href ="createIncomeAndExpense.html" target="frame">Income & Expenditure</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('CashBook')}">
																		<li><a href ="cashBook.html" target="frame">CashBook </a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Balance Sheet')}">
																		<li><a href ="createBalanceSheet.html" target="frame">Balance Sheet</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Bank Reconcilation Statement')}">		
																		<li><a href ="brs.html" target="frame">Bank Reconcilation Statement</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Profit & Loss Account')}">
																		<li><a href ="createProfitAndLoss.html" target="frame">Profit & Loss Account</a></li>
																	</c:if>	
																</c:forEach>
																<c:set var="i" value="0" scope="page" />
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Entry In Day Book')
																				||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('View Day Book')}">
																		<c:set var="i" value="1" scope="page" />
																	</c:if>
																</c:forEach>
																<c:if test="${i eq 1}">		
																	<li class="nav-parent">
																		<a>
																			DayBook
																		</a>
																		<ul class="nav nav-children">
																			<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Entry In Day Book')}">
																					<li><a href ="getEntryInDayBook.html" target="frame">Entry In Day Book</a></li>
																				</c:if>
																			</c:forEach>
																			<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('View Day Book')}">
																					<li><a href ="dayBook.html" target="frame">View Day Book</a></li>
																				</c:if>
																			</c:forEach>		
																		</ul>
																	</li>
																</c:if>
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Ledger Wise View')}">
																		<li><a href ="ledgerWiseView.html" target="frame">Ledger Wise View</a></li>
																	</c:if>
																</c:forEach>
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page" />
													<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Ledger Mapping')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Salary Breakup And Ledger Mapping')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Salary
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Ledger Mapping')}">
																		<li><a href ="templateLedgerMapping.html" target="frame">Ledger Mapping</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Salary Breakup And Ledger Mapping')}">
																		<li><a href ="salaryBreakupLedgerMapping.html" target="frame"> Salary Breakup And Ledger Mapping</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page" />
													<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Budget')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">	
														<li class="nav-parent">
															<a>
																Budgeting
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Budget')}">
																		<li><a href ="budget.html" target="frame">Budget</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund')}">
																		<li><a href ="fundAllocation.html" target="frame">Reserve Fund</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<!-- Added by sourav.bhadra on 09-04-2018 -->
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')
														             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')
														             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')
														             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Budget Evaluation
															</a>
															<ul class="nav nav-children">
																<c:set var="i" value="0" scope="page"/>
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')
																	             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')}">
																		<c:set var="i" value="1" scope="page" />
																	</c:if>
																</c:forEach>
																<c:if test="${i eq 1}">
																	<li class="nav-parent">
																		<a>
																			Reserve-Unreserve Fund Conversion
																		</a>
																		<ul class="nav nav-children">
																			<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')}">
																					<li><a href ="reserveFundEstimationForADepartment.html" target="frame">Reserve Fund Estimation</a></li>
																				</c:if>
																			</c:forEach>
																			<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')}">
																					<li><a href ="reserveToUnreserveFundConversion.html" target="frame">Unreserve Conversion</a></li>
																				</c:if>
																			</c:forEach>																	
																		</ul>
																	</li>
																</c:if>
																<c:set var="i" value="0" scope="page"/>
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')
																	             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
																		<c:set var="i" value="1" scope="page" />
																	</c:if>
																</c:forEach>
																<c:if test="${i eq 1}">
																	<li class="nav-parent">
																		<a>
																			Sub Department Budget Allocation
																		</a>
																		<ul class="nav nav-children">
																			<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')}">
																					<li><a href ="subDepartmentsBudgetAllocation.html" target="frame">Sub Department Budget Allocation</a></li>
																				</c:if>
																			</c:forEach>
																			<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
																					<li><a href ="reserveFundAllocationForSubDepartments.html" target="frame">Reserve Fund Allocation</a></li>
																				</c:if>
																			</c:forEach>
																		</ul>
																	</li>
																</c:if>	
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Dealer Details')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Dealers
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${FINANCEfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Dealer Details')}">
																		<li><a href ="dealerDetails.html" target="frame">Dealer Details</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>	
												</ul>
											</li>
										</c:if>
										<%-- <c:if test="${role.moduleName eq 'VENUE'}">
											<li class="nav-parent">
												<a href="#">
													<i class="fa fa-institution" aria-hidden="true"></i>
													<span>Venue Management</span>
												</a>
												
												<ul class="nav nav-children">															
													<!-- <li class="nav-parent">
														<a>
															Zone
														</a>
														<ul class="nav nav-children">
															<li><a href ="getZone.html" target="frame">Create Zone</a></li>
															<li><a href ="groupListPage.html" target="frame">List Group</a></li>
														</ul>
													</li> -->
													<li class="nav-parent">
														<a>
															Location
														</a>
														<ul class="nav nav-children">
															<li><a href ="getLocation.html" target="frame">Create Location</a></li>
														</ul>
													</li>
													<li class="nav-parent">
														<a>
															Venue
														</a>
														<ul class="nav nav-children">
															<li><a href ="getVenue.html" target="frame">Create Venue</a></li> 
															<li><a href ="getVenueFacilityMapping.html" target="frame">Facility Venue Mapping</a></li>
															<li><a href ="editVenueFacilityMapping.html" target="frame">Edit Facility Venue Mapping</a></li>
															<li><a href ="allocateVenue.html" target="frame">Venue Allocation</a></li> 
														</ul>
													</li>
												</ul>
											</li>
										</c:if> --%>
										<c:if test="${role.moduleName eq 'SYSTEM ADMINISTRATION'}">
											<li class="nav-parent">
												<a href="#">
													<i class="fa fa-wrench" aria-hidden="true"></i>
													<span>System Administration</span>
												</a>	
												<ul class="nav nav-children">
													<c:set var="i" value="0" scope="page" />
									                <c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create New Role')
														           || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Functionality Role Mapping')
														           || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Role-Contact')
														           || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Role Contact Mapping List')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Roles
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create New Role')}">
																		<li><a href="createRoles.html" target="frame">Create New Role</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Functionality Role Mapping')}">
																		<li><a href="functionalityRole.html" target="frame">Functionality Role Mapping</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Role-Contact')}">
																		<li><a href="roleContactMapping.html" target="frame">Map Role-Contact</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Role Contact Mapping List')}">
																		<li><a href="listRoleContactMapping.html" target="frame">Role Contact Mapping List</a></li>
																	</c:if>
																</c:forEach>
															</ul>
														</li>	
													</c:if>		
													<c:set var="i" value="0" scope="page" />
									                <c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Access Type Role Mapping')
														           || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Access Type-Contact')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Access Type
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Access Type Role Mapping')}">
																		<li><a href="roleAccessMapping.html" target="frame">Access Type Role Mapping</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Access Type-Contact')}">
																		<li><a href="accessTypeContactMapping.html" target="frame">Map Access Type-Contact</a></li>
																		<!-- <li><a href="listAccessTypeContactMapping.html" target="frame">Access Type Contact Mapping List</a></li> -->
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>	
													<!-- <li class="nav-parent">
														<a>
															Task Configuration
														</a>
														<ul class="nav nav-children">	
															<li><a href="createNewTask.html" target="frame">Create Task</a></li>
															<li><a href="allocateTask.html" target="frame">Task Allocation</a></li>
															<li><a href="createApprovers.html" target="frame">Configure Approver Group</a></li>
															<li><a href="createApprovalOrder.html" target="frame">Configure Approval Order</a></li>
														</ul>
													</li> -->
													<c:set var="i" value="0" scope="page" />
									                <c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Recipient Group')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Configure Recipient Group
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Recipient Group')}">
																		<li><a href="createApprovers.html" target="frame">Create Recipient Group</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>	
													<!-- <li class="nav-parent">
														<a>
															Configure Approvers
														</a>
														<ul class="nav nav-children">	
															<li><a href="createApprovers.html" target="frame">Setup Approvers</a></li>
															<li><a href="viewApproversList.html" target="frame">View All Approvers</a></li>
															<li><a href="createApprovalOrder.html" target="frame">Configure Approval Order</a></li>
														</ul>
													</li> -->
													<c:set var="i" value="0" scope="page" />
									                <c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Directory Server')
														            ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure DB')
														            ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure EMAIL')
														            ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure SMS')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Configure Servers
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Directory Server')}">
																		<li><a href="directoryServerConfiguration.html" target="frame">Configure Directory Server</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure DB')}">
																		<li><a href="serverConfigurationDB.html" target="frame">Configure DB</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure EMAIL')}">
																		<li><a href="serverConfigurationEMAIL.html" target="frame">Configure EMAIL</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure SMS')}">		
																		<li><a href="serverConfigurationSMS.html" target="frame">Configure SMS</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<li class="nav-parent">
														<a>
															Logging-Notification SetUp
														</a>
														<ul class="nav nav-children">	
															<li><a href="setNotificationMedium.html" target="frame">Set Notification Medium</a></li>        				
															<li><a href="getLoggingNotificationSetUp.html?module=ACADEMICS" target="frame">Academics</a></li>
															<li><a href="getLoggingNotificationSetUp.html?module=ADMISSION" target="frame">Admission</a></li>
															<li><a href="getLoggingNotificationSetUp.html?module=LIBRARY" target="frame">Library</a></li>
															<li><a href="getLoggingNotificationSetUp.html?module=TICKETING" target="frame">Ticketing</a></li>
															<li><a href="getLoggingNotificationSetUp.html?module=HOSTEL" target="frame">Hostel</a></li>
															<li><a href="getLoggingNotificationSetUp.html?module=SYSTEM ADMINISTRATION" target="frame">System Administration</a></li>						
															<li><a href="getLoggingNotificationSetUp.html?module=ERP" target="frame">ERP</a></li>						
															<li><a href="getLoggingNotificationSetUp.html?module=INVENTORY" target="frame">Inventory</a></li>							
															<li><a href="getLoggingNotificationSetUp.html?module=OFFICE ADMINISTRATION" target="frame">General Section</a></li>
														</ul>
													</li>
													<c:set var="i" value="0" scope="page" />
									                <c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Set Up SLA For Ticketing')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																SLA
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Set Up SLA For Ticketing')}">
																		<li><a href="viewSLAForTicketing.html" target="frame">Set Up SLA For Ticketing</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page" />
									                <c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create / Update User Password')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Manage User Password
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create / Update User Password')}">
																		<li><a href="createAndUpdateUserPassword.html" target="frame">Create / Update User Password</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page" />
									                <c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Inactive Programs')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Inactive Student')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Inactive Employee')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Inactive Course')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Inactive Record(s)
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Inactive Programs')}">
																		<li><a href="inactiveCourse.html" target="frame">Inactive Programs</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Inactive Student')}">
																		<li><a href="inactiveStudent.html" target="frame">Inactive Student</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Inactive Employee')}">
																		<li><a href="inactiveStaff.html" target="frame">Inactive Employee</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Inactive Course')}">
																		<li><a href="inactiveSubject.html" target="frame">Inactive Course</a></li>
																		<!-- <li><a href="inactiveStaff.html?resourceType=NONTEACHINGSTAFF" target="frame">Inactive Supporting Staff</a></li> -->
																		<!-- <li><a href="inactiveHostel.html" target="frame">Inactive Hostel</a></li> -->
																		<!-- <li><a href="inactiveSubjectGroup.html" target="frame">Inactive Subject Group</a></li> -->
																	</c:if>
																</c:forEach>
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page" />
									                <c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Read Excel')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Update Database')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">	
														<li class="nav-parent">
															<a>
																Previous Data
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Read Excel')}">
																		<li><a href="openReadExcel.html" target="frame">Read Excel</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Update Database')}">
																		<li><a href="openUpdateDatabase.html" target="frame">Update Database</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page" />
									                <c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Repository Directory')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Configure Directory Structure
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Repository Directory')}">
																		<li><a href="configureRepositoryDirectory.html" target="frame">Configure Repository Directory</a></li>
																	</c:if>
																</c:forEach>
															</ul>
														</li>
													</c:if>	
														<!-- <li class="nav-parent">
															<a>
																Profile Matrix
															</a>
															<ul class="nav nav-children">	
																<li><a href="setProfileViewMatrix.html" target="frame">View Profile Matrix</a></li>
															</ul>
														</li>
														<li class="nav-parent">
															<a>
																Configure Rest APIsadded by Saif for Rest APIs
															</a>
															<ul class="nav nav-children">	
																<li><a href="configureRestAPIs.html" target="frame">Configure Rest APIs</a></li>
															</ul>
														</li> -->
													<c:set var="i" value="0" scope="page" />
									                <c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Event')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Template')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Template List')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Event And Template')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Email Template<!-- added by Saif to create Email Template and Event -->
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Event')}">
																		<li><a href="createEventForTemplate.html" target="frame">Create Event</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Template')}">
																		<li><a href="createTemplateForEvent.html" target="frame">Create Template</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Template List')}">
																		<li><a href="emailTemplateList.html" target="frame">Template List</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${SYSTEMADMINISTRATIONfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Event And Template')}">
																		<li><a href="mapEventAndTemplate.html" target="frame">Map Event And Template</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>	
												</ul>
											</li>
										</c:if>
										<c:if test="${role.moduleName eq 'TICKETING'}">
											<li class="nav-parent">
												<a href="#">
													<i class="fa fa-ticket" aria-hidden="true"></i>
													<span>Ticketing</span>
												</a>
												<ul class="nav nav-children">
													<c:set var="i" value="0" scope="page" />
									                <c:forEach var="functionality" items="${TICKETINGfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Inward Ticket List')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Closed Ticket List')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Ticket
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${TICKETINGfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Inward Ticket List')}">
																		<li><a href="inwardListTicketForTicketAdministrator.html" target="frame">Inward Ticket List</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${TICKETINGfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Inward Ticket List')}">
																		<li><a href="listClosedTicketForForTicketAdministrator.html" target="frame">Closed Ticket List</a></li>
																	</c:if>
																</c:forEach>
															</ul>
														</li>
													</c:if>	
												</ul>
											</li>
										</c:if>
										<c:if test="${role.moduleName eq 'INVENTORY'}">
											<li class="nav-parent">
												<a href="#">											
													<i class="fa fa-file-text-o" aria-hidden="true"></i>
													<span>Inventory</span>
												</a>
												<ul class="nav nav-children">
													<c:set var="i" value="0" scope="page" />
									                <c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Commodity Master Catalogue')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Commodity Allotment History')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Commodity Details')}">
																<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Commodity Management
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Commodity Master Catalogue')}">
																		<li><a href="addCommodity.html" target="frame">Commodity Master Catalogue</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Commodity Allotment History')}">
																		<li><a href="commodityAllotmentHistory.html" target="frame">Commodity Allotment History</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Commodity Details')}">
																		<li><a href="commodityDetails.html" target="frame">Commodity Details</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page" />
									                <c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Commodity Vendor')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Tender Wise Pricing')}">
																<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Commodity Vendor Mapping
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Map Commodity Vendor')}">
																		<li><a href="commodityVendorMapping.html" target="frame">Map Commodity Vendor</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Tender Wise Pricing')}">
																		<li><a href="tenderWisePricing.html" target="frame">Tender Wise Pricing</a></li>
																	</c:if>
																</c:forEach>
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page" />
									                <c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Commodity Requisition')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Commodity Requisition List')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Commodity PO')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Commodity PO List')}">
																<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Commodity PO
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Commodity Requisition')}">
																		<li><a href="commodityRequisition.html" target="frame">Commodity Requisition</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Commodity Requisition List')}">
																		<li><a href="commodityRequisitionList.html" target="frame">Commodity Requisition List</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Commodity PO')}">
																		<li><a href="commodityPurchaseOrder.html" target="frame">Commodity PO</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Commodity PO List')}">
																		<li><a href="commodityPurchaseOrderList.html?status=OPEN" target="frame">Commodity PO List</a></li>
																	</c:if>
																</c:forEach>
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page" />
									                <c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Allocate Commodity')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('De-Allocate Commodity')}">
																<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Commodity Allocation And De-Allocation
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Allocate Commodity')}">
																		<li><a href="allocateCommodity.html" target="frame">Allocate Commodity</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Allocate Commodity')}">
																		<li><a href="deAllocateCommodity.html" target="frame">De-Allocate Commodity</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page" />
									                <c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create ASTB')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Condemnation')}">
																<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">	
														<li class="nav-parent">
															<a>
																Annual Stock Verification Board
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create ASTB')}">
																		<li><a href="createASTB.html" target="frame">Create ASTB</a></li><!-- added by saif to create ASTB-->
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Condemnation')}">
																		<li><a href="createUpdateCondemnation" target="frame">Condemnation</a></li><!-- added by saif to create or update the condemnation-->
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>	
													<!-- <li class="nav-parent">
														<a>
															Mess
														</a>
														<ul class="nav nav-children">																	
															<li><a href="createMessPurchaseOrder.html" target="frame">Create Mess Purchase Order</a></li>
															<li><a href="viewMessPurchaseOrderList.html?status=OPEN" target="frame">Mess Purchase Order List</a></li>
															<li><a href="viewInventoryStock.html" target="frame">Inventory Stock</a></li>
															<li><a href="viewIssueVoucher.html" target="frame">Issue Voucher</a></li>
															<li><a href="viewDailyRationPurchaseOrderList.html" target="frame">Daily Ration Purchase Order List</a></li>
														</ul>
													</li> -->
													<!-- Added by sourav.bhadra on 18-04-2018 -->
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')
														             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')
														             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')
														             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Budget Evaluation
															</a>
															<ul class="nav nav-children">
																<c:set var="i" value="0" scope="page"/>
																<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')
																	             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')}">
																		<c:set var="i" value="1" scope="page" />
																	</c:if>
																</c:forEach>
																<c:if test="${i eq 1}">
																	<li class="nav-parent">
																		<a>
																			Reserve-Unreserve Fund Conversion
																		</a>
																		<ul class="nav nav-children">
																			<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Estimation')}">
																					<li><a href ="reserveFundEstimationForADepartment.html" target="frame">Reserve Fund Estimation</a></li>
																				</c:if>
																			</c:forEach>
																			<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Unreserve Conversion')}">
																					<li><a href ="reserveToUnreserveFundConversion.html" target="frame">Unreserve Conversion</a></li>
																				</c:if>
																			</c:forEach>																	
																		</ul>
																	</li>
																</c:if>
																<c:set var="i" value="0" scope="page"/>
																<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')
																	             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
																		<c:set var="i" value="1" scope="page" />
																	</c:if>
																</c:forEach>
																<c:if test="${i eq 1}">
																	<li class="nav-parent">
																		<a>
																			Sub Department Budget Allocation
																		</a>
																		<ul class="nav nav-children">
																			<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Sub Department Budget Allocation')}">
																					<li><a href ="subDepartmentsBudgetAllocation.html" target="frame">Sub Department Budget Allocation</a></li>
																				</c:if>
																			</c:forEach>
																			<c:forEach var="functionality" items="${INVENTORYfunctionalityList}" >
																				<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Reserve Fund Allocation')}">
																					<li><a href ="reserveFundAllocationForSubDepartments.html" target="frame">Reserve Fund Allocation</a></li>
																				</c:if>
																			</c:forEach>
																		</ul>
																	</li>
																</c:if>	
															</ul>
														</li>
													</c:if>
												</ul>
											</li>
										</c:if>	
										<c:if test="${role.moduleName eq 'MESS'}">
											<li class="nav-parent">
												<a href="#">											
													<i class="fa fa-file-text-o" aria-hidden="true"></i>
													<span>Mess</span>
												</a>
												<ul class="nav nav-children">
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${MESSfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Indent Sheet Request')
														             || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Indent Sheet Request List')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Indent Sheet
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${MESSfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Indent Sheet Request')}">
																		<li><a href="createMessDemandVoucher.html" target="frame">Indent Sheet Request</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${MESSfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Indent Sheet Request List')}">
																		<li><a href="viewDemandVoucherList.html" target="frame">Indent Sheet Request List</a></li>
																	</c:if>
																</c:forEach>
															</ul>
														</li>
													</c:if>	
													<!-- <li><a href="viewIssueVoucherList.html" target="frame">Issue Voucher List</a></li> -->
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${MESSfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Non Perishable Items Stock')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Mess Stock
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${MESSfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Non Perishable Items Stock')}">
																		<li><a href="viewMessStock.html" target="frame">Non Perishable Items Stock</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${MESSfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Daily Ration Vendor')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Daily Ration Vendor Commodity Mapping')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Perishable Material Vendor
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${MESSfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Daily Ration Vendor')}">
																		<li><a href="addDailyRationVendor.html" target="frame">Add Daily Ration Vendor</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${MESSfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Daily Ration Vendor Commodity Mapping')}">
																		<li><a href="dailyRationVendorCommodityMapping.html" target="frame">Daily Ration Vendor Commodity Mapping</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${MESSfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Perishable Material Requisition')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Perishable Material Requisition List')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Perishable Material
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${MESSfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Perishable Material Requisition')}">
																		<li><a href="createPerishableMaterialRequisition.html" target="frame">Create Perishable Material Requisition</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${MESSfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Perishable Material Requisition List')}">		
																		<li><a href="perishableMaterialRequisitionList.html" target="frame">Perishable Material Requisition List</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${MESSfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Mess Menu')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Mess Menu List')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Mess Menu
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${MESSfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Mess Menu')}">
																		<li><a href="createMessMenu.html" target="frame">Create Mess Menu</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${MESSfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Mess Menu List')}">		
																		<li><a href="viewMessMenuList.html" target="frame">Mess Menu List</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${MESSfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Issue Daily Goods to Kitchen')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Daily Mess Consumption
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${MESSfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Issue Daily Goods to Kitchen')}">
																		<li><a href="createDailyMessConsumption.html" target="frame">Issue Daily Goods to Kitchen</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${MESSfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Mess Per Capita')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Mess Per Capita
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${MESSfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Mess Per Capita')}">
																		<li><a href="messPerCapita.html" target="frame">Mess Per Capita</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${MESSfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Activity Log List')}">
														     <c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">		<!-- Added by Saif Date- 21/03/2018 -->
															<a>
																Activity Log List
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${MESSfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Activity Log List')}">
																		<li><a href="showActivityLogList.html" target="frame">Activity Log List</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>																	
												</ul>
											</li>
										</c:if>
										
										<!-- PRAD May 24 2018 -->
										<c:if test="${role.moduleName eq 'TENDER'}">
											<li class="nav-parent">
												<a href="#">
													<i class="fa fa-file-text-o" aria-hidden="true"></i>
														<span>Tender</span>
												</a>
												<ul class="nav nav-children">
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${TENDERfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Float Tender')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Quotation')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Auction')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
													<li class="nav-parent">
															<a>
																Tender Management
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${TENDERfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Float Tender')}">
																		<li><a href="getTenderForm.html" target="frame">Float Tender</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${TENDERfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Quotation')}">
																		<li><a href="getQuotation.html" target="frame">List Quotation</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${TENDERfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Auction')}">
																		<li><a href="getAuction.html" target="frame">Auction</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													</ul>
											 </li>
										</c:if>
										<!-- ENDS HERE -->
										
										<c:if test="${role.moduleName eq 'HOSTEL'}">
											<li class="nav-parent">
												<a href="#">
													<i class="fa fa-hotel" aria-hidden="true"></i>
													<span>Hostel</span>
												</a>
												<ul class="nav nav-children">
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Hostel Type')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Hostel')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Residence Management
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Hostel Type')}">
																		<li><a href="getHostelType.html" target="frame">Create Residence Type</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Hostel')}">
																		<li><a href="getHostel.html" target="frame">Create Residence</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create House')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('House Residence Mapping')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Assign House Master')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Update House of Cadet')}">
															<c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																House Management
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create House')}">
																		<li><a href="getHouse.html" target="frame">Create House</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('House Residence Mapping')}">
																		<li><a href="getHouseResidenceMapping.html" target="frame">House Residence Mapping</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Assign House Master')}">
																		<li><a href="getAssignedHouseMaster.html" target="frame">Assign House Master</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Update House of Cadet')}">
																		<li><a href="updateHostelOfCadet.html" target="frame">Update House of Cadet</a></li>
																	</c:if>
																</c:forEach>
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Hostel Facility')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Hostel Facility')}">
														     <c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Hostel Facility
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Hostel Facility')}">
																		<li><a href="addHostelFacility.html" target="frame">Add Hostel Facility</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Hostel Facility')}">
																		<li><a href="listHostelFacility.html" target="frame">List Hostel Facility</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Room Type')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Room Type List')}">
														     <c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Room Type
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Room Type')}">
																		<li><a href="addRoomType.html" target="frame">Add Room Type</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Room Type List')}">
																		<li><a href="listRoomType.html" target="frame">Room Type List</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Room in Hostel')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Of Room in Hostel')}">
														     <c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Room
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Add Room in Hostel')}">
																		<li><a href="addRoom.html" target="frame">Add Room in Hostel</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Of Room in Hostel')}">		
																		<li><a href="listRoom.html" target="frame">List Of Room in Hostel</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>
													<c:set var="i" value="0" scope="page"/>
													<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
														<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Assign Hostel to Student')
																	||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Of Hostel Assigned Students')}">
														     <c:set var="i" value="1" scope="page" />
														</c:if>
													</c:forEach>
													<c:if test="${i eq 1}">
														<li class="nav-parent">
															<a>
																Student Assign
															</a>
															<ul class="nav nav-children">
																<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Assign Hostel to Student')}">
																		<li><a href="assignHostelToStudent.html" target="frame">Assign Hostel to Student</a></li>
																	</c:if>
																</c:forEach>
																<c:forEach var="functionality" items="${HOSTELfunctionalityList}" >
																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('List Of Hostel Assigned Students')}">		
																		<li><a href="listStudentAssignedHostel.html" target="frame">List Of Hostel Assigned Students</a></li>
																	</c:if>
																</c:forEach>		
															</ul>
														</li>
													</c:if>	
												<!-- <li class="nav-parent">
													<a>
														Expense
													</a>
													<ul class="nav nav-children">
														<li><a href="hostelExpense.html" target="frame">Hostel Expense</a></li>
													</ul>
												</li> -->
											</ul>
										</li>
									</c:if>
									<c:if test="${role.moduleName eq 'REPORT'}">
										<li class="nav-parent">
											<a href="#">
												<i class="fa fa-line-chart" aria-hidden="true"></i>
												<span>Report</span>
											</a>
											<ul class="nav nav-children">
											<!-- New CBSE System start -->
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${REPORTfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Generate Student Marksheet')
																||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Generate Consolidated Marksheet')}">
													     <c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															New Academic Report
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${REPORTfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Generate Student Marksheet')}">
																	<li><a href="newStudentMarksheet.html" target="frame">Generate Student Marksheet</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${REPORTfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Generate Consolidated Marksheet')}">
																	<li><a href="newClassConsolidatedMarksheet.html" target="frame">Generate Consolidated Marksheet</a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>			
												<!-- New CBSE System end -->
												<!-- <li class="nav-parent">
													<a>
														Academic Report
													</a>
													<ul class="nav nav-children">
														<li><a href="getCertificate.html" target="frame">Certificate</a></li> 
														<li><a href="studentMarksheet.html" target="frame">Individual MarkSheet</a></li>
														<li><a href="userExamMarkStatement.html" target="frame">User Exam Marks Statement</a></li>
														 <li><a href="userExamConsolidatedMarksheet.html" target="frame">User Exam Consolidated Mark Sheet</a></li> 
													</ul>
												</li>
												
												<li class="nav-parent">
													<a>
														Module Wise Report
													</a>
													<ul class="nav nav-children">
														<li><a href='moduleWiseReport.html?moduleName=LIBRARY' target="frame">Library</a></li> 
														<li><a href='moduleWiseReport.html?moduleName=ACADEMICS' target="frame">Academics</a></li>
														<li><a href='moduleWiseReport.html?moduleName=PAYROLL' target="frame">Payroll</a></li>  
														<li><a href='moduleWiseReport.html?moduleName=FINANCE' target="frame">Finance</a></li>
														<li><a href='moduleWiseReport.html?moduleName=INVENTORY' target="frame">Inventory</a></li>
														<li><a href='moduleWiseReport.html?moduleName=ADMISSION' target="frame">Admission</a></li>
														<li><a href='moduleWiseReport.html?moduleName=OFFICE ADMINISTRATION' target="frame">Office Administration</a></li>
														<li><a href='moduleWiseReport.html?moduleName=SYSTEM ADMINISTRATION' target="frame">System Administration</a></li>
														<li><a href='moduleWiseReport.html?moduleName=TICKETING' target="frame">Ticketing</a></li>
														<li><a href='moduleWiseReport.html?moduleName=HOSTEL' target="frame"> Hostel</a></li>
														<li><a href='moduleWiseReport.html?moduleName=FACILITY' target="frame"> Facility & Venue Management</a></li>
													</ul>
												</li>
												<li class="nav-parent">
													<a>
														General Report
													</a>
													<ul class="nav nav-children">
														<li><a href="studentNominalRoll.html" target="frame">Nominal Roll List</a></li> 
														<li><a href="staffDetailsList.html" target="frame">Staff Details List</a></li>
														<li><a href="getStudentAddress.html" target="frame">Student Address</a></li>
														<li><a href="socialCategoryWiseClassStrength.html" target="frame">Social Category Wise Class Student</a></li>
													</ul>
												</li>
												<li class="nav-parent">
													<a>
														Admission Report
													</a>
													<ul class="nav nav-children">
														<li><a href="meritListForAdmisionReport.html" target="frame">Candidate Merit List Report</a></li>
														<li><a href="examVenueWiseCandidate.html" target="frame">Exam Venue Wise Report</a></li>
														<li><a href="createTCReport.html" target="frame">Create TC Report</a></li>
																										</ul>
												</li>
												<li class="nav-parent">
													<a>
														Inventory
													</a>
													<ul class="nav nav-children">
														<li><a href="createPurchaseOrderReport.html" target="frame">Purchase Order</a></li>
														<li><a href="gatePassReport.html" target="frame">Gate Pass</a></li>
														<li><a href="carTripDetails.html" target="frame">Car Trip Details</a></li>
														<li><a href="carMaintenanceDetails.html" target="frame">Car Maintenance Details</a></li>	
														<li><a href="lpgCylinderRefillingReport.html" target="frame">LPG Cylinder Refill</a></li>
														<li><a href="condemnationReport.html" target="frame">Codemnation</a></li>	
													</ul>
												</li> -->
											</ul>
										</li>
									</c:if>
									<c:if test="${role.moduleName eq 'GRADING SYSTEM'}">
										<li class="nav-parent">
											<a href="#">
												<i class="fa fa-wpforms" aria-hidden="true"></i>
												<span>Grading System</span>
											</a>
											<ul class="nav nav-children">
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${GRADINGSYSTEMfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Grading System')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a href="#">
															<i class="fa fa-wpforms" aria-hidden="true"></i>
															<span>New System</span>
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${GRADINGSYSTEMfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Grading System')}">
																	<li><a href="getGradingSystemNew.html" target="frame">Create Grading System</a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page"/>
												<c:forEach var="functionality" items="${GRADINGSYSTEMfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Grading System')}">
														<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">	
													<li class="nav-parent">
														<a href="#">
															<i class="fa fa-wpforms" aria-hidden="true"></i>
															<span>Old System</span>
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${GRADINGSYSTEMfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Grading System')}">
																	<li><a href="createGradingSystem.html" target="frame">Create Grading System</a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>	
											</ul>
										</li>
									</c:if>
									<c:if test="${role.moduleName eq 'FACILITY MANAGEMENT'}">
										<li class="nav-parent">
											<a href="#">
												<i class="fa fa-support" aria-hidden="true"></i>
												<span>Facility & Venue Management</span>
											</a>
											<ul class="nav nav-children">
												<c:set var="i" value="0" scope="page" />
												<c:forEach var="functionality" items="${FACILITYMANAGEMENTfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Facility')
													           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Facility List')
													           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Facility De-activation')
													           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Venue Resource Mapping')
													           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Venue Resource Mapping List')
													           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Amenities Usage By Students')
													           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Location')
													           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Venue')
													           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Ceate Facility Venue Mapping')
													           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Edit Facility Venue Mapping')
													           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Venue Allocation')}">
															<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">
													<li class="nav-parent">
														<a>
															Facility
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${FACILITYMANAGEMENTfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Facility')}">
																	<li><a href="createFacility.html" target="frame">Create Facility</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${FACILITYMANAGEMENTfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Facility List')}">
																	<li><a href="listFacility.html" target="frame">Facility List</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${FACILITYMANAGEMENTfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Facility De-activation')}">
																	<li><a href="deactivateFacilty.html" target="frame">Facility De-activation</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${FACILITYMANAGEMENTfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Venue Resource Mapping')}">
																	<li><a href="assignVenueToResource.html" target="frame">Venue Resource Mapping</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${FACILITYMANAGEMENTfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Venue Resource Mapping List')}">
																	<li><a href="listVenuesAssignedToResource.html" target="frame">Venue Resource Mapping List</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${FACILITYMANAGEMENTfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Amenities Usage By Students')}">		
																	<li><a href ="getAminitiesUsedByStudent.html" target="frame">Amenities Usage By Students</a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page" />
												<c:forEach var="functionality" items="${FACILITYMANAGEMENTfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Location')}">
															<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">	
													<li class="nav-parent">
														<a>
															Location
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${FACILITYMANAGEMENTfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Location')}">
																	<li><a href ="getLocation.html" target="frame">Create Location</a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>
												<c:set var="i" value="0" scope="page" />
												<c:forEach var="functionality" items="${FACILITYMANAGEMENTfunctionalityList}" >
													<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Venue')
													           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Ceate Facility Venue Mapping')
													           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Edit Facility Venue Mapping')
													           ||fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Venue Allocation')}">
															<c:set var="i" value="1" scope="page" />
													</c:if>
												</c:forEach>
												<c:if test="${i eq 1}">	
													<li class="nav-parent">
														<a>
															Venue
														</a>
														<ul class="nav nav-children">
															<c:forEach var="functionality" items="${FACILITYMANAGEMENTfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Venue')}">
																	<li><a href ="getVenue.html" target="frame">Create Venue</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${FACILITYMANAGEMENTfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Facility Venue Mapping')}">
																	<li><a href ="getVenueFacilityMapping.html" target="frame">Create Facility Venue Mapping</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${FACILITYMANAGEMENTfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Edit Facility Venue Mapping')}">
																	<li><a href ="editVenueFacilityMapping.html" target="frame">Edit Facility Venue Mapping</a></li>
																</c:if>
															</c:forEach>
															<c:forEach var="functionality" items="${FACILITYMANAGEMENTfunctionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Venue Allocation')}">		
																	<li><a href ="allocateVenue.html" target="frame">Venue Allocation</a></li>
																</c:if>
															</c:forEach>		
														</ul>
													</li>
												</c:if>	
											</ul>
										</li>
									</c:if>
								</c:forEach>
												<li class="nav-parent">
													<a>											
														<i class="fa fa-gear" aria-hidden="true"></i>
														<span>My Services</span>
													</a>
													
													<ul class="nav nav-children">
														<li class="nav-parent">
															<a href="#">
																My Personal Details
															</a>
															<ul class="nav nav-children">	
																<li><a href="viewStaffProfileDetails.html?userId=${sessionScope.sessionObject.userId}" target="frame">View Personal Details</a></li>
																<li><a href="viewAttendance.html" target="frame">View Attendance</a></li>
															</ul>
														</li>	
														<li class="nav-parent">
															<a href="#">
																My Fees Details
															</a>
															<ul class="nav nav-children">
															<%-- <% String resourceTypeVal = request.getSession().getAttribute("resourceType").toString();
																	System.out.println ("resourceType====="+resourceTypeVal);
																	if(resourceTypeVal.equalsIgnoreCase("STUDENT")){
																		 String courseCodeValue = request.getSession().getAttribute("courseCode").toString();
																		   System.out.println ("courseCodeValue====="+courseCodeValue); %> --%>	
																<li><a href="viewFeesDetails.html?userId=${sessionScope.sessionObject.userId}&courseCode=${sessionScope.sessionObject.courseCode}" target="frame">View Fees Details</a></li>
																<!-- <li><a href="feesRecieptDownload.html" target="frame">Fees Reciept</a></li> -->
															<%-- <% }%> --%>
															</ul>
														</li>
														<li class="nav-parent">
															<a href="#">
																My Assignment
															</a>
															<ul class="nav nav-children">
																<%-- <% String resourceTypeValue = request.getSession().getAttribute("resourceType").toString();
																	System.out.println ("resourceType====="+resourceTypeValue);
																	if(resourceTypeValue.equalsIgnoreCase("STUDENT")){
																		 String courseCodeValue = request.getSession().getAttribute("courseCode").toString();
																		   System.out.println ("courseCodeValue====="+courseCodeValue); %> --%>
																	<li><a href='viewDownloadAssignment.html?folderParam=AcademicYear&fileParam=noFile&courseCode=${sessionScope.sessionObject.courseCode}' target="frame">Download Assignment</a></li>
																	<%-- <% }%> --%>
																  
																
																		<%-- <li><a href='viewDownloadAssignment.html?folderParam=AcademicYear&fileParam=noFile&courseCode=<%=courseCodeValue%>' target="frame">Download Assignment</a></li> --%>
																
																	<!-- <li><a href='viewDownloadAssignment.html?folderParam=AcademicYear&fileParam=noFile' target="frame">Download Assignment</a></li> -->
															
															</ul>
														</li>
														<li class="nav-parent">
															<a>
																Library
															</a>
															<ul class="nav nav-children">	
																<li><a href="viewBookStock.html" target="frame">View Book List</a></li>
																<li><a href="createLodgingRequest.html" target="frame">Create Book Request</a></li>
<!-- 																<li><a href="listAccessTypeContactMapping.html" target="frame">Access Type Contact Mapping List</a></li> -->
															</ul>
														</li>
													
														<li class="nav-parent">
															<a>
																My Leaves
															</a>
															<ul class="nav nav-children">	
																<li><a href="leaveRequest.html" target="frame">Available Leave</a></li>
																<li><a href="listPersonalLeaveHistoryForMyService.html" target="frame">Leave History</a></li>
																<!-- <li><a href="applyForLeave.html" target="frame">Request For Leave</a></li> -->
																<!-- <li><a href="notificatinForTask.html" target="frame">Notification</a></li> -->
																 
															</ul>
														</li>
													
													<!-- <ul class="nav nav-children">
														<li class="nav-parent">
															<a>
																Personal Calender
															</a>
															<ul class="nav nav-children">	
																<li><a href="personalCalendar.html" target="frame">Assign Personal Event</a></li>
																<li><a href="showPersonalEvent.html" target="frame">View Personal Event</a></li>
																<li><a href="viewApproversList.html" target="frame">View All Approvers</a></li>
															</ul>
														</li>
													</ul> -->
													
														<li class="nav-parent">
															<a>
																View Delegated Task
															</a>
															<ul class="nav nav-children">	
																<li><a href="inwardDelegatedTask.html" target="frame">Inward Delegated Task</a></li>
																<li><a href="outwardDelegatedTask.html" target="frame">Outward Delegated Task</a></li>
															
															</ul>
														</li>
													
														<li class="nav-parent">
															<a>
																My Tickets
															</a>
															<ul class="nav nav-children">	
																<li><a href="generateTicket.html" target="frame">Generate Ticket</a></li>        				
																<li><a href="listTicket.html" target="frame">List Generated Ticket</a></li>
																<li><a href="closedTicketList.html" target="frame">List Closed Ticket</a></li>
															</ul>
														</li>
													
														<li class="nav-parent">
															<a>
																My Assigned Tickets
															</a>
															<ul class="nav nav-children">	
																<li><a href="inwardListTicket.html" target="frame">Inward Ticket List</a></li>
																<li><a href="listClosedTicket.html" target="frame">Closed Ticket List</a></li>
															</ul>
														</li>
														
														<li class="nav-parent">
															<a>
																My Email
															</a>
															<ul class="nav nav-children">	
																<li><a href="createNotification.html" target="frame">Compose</a></li>        				
																<li><a href="getEmailDetails.html" target="frame">Inbox</a></li>
																<li><a href="getSentEmailDetails.html" target="frame">Sentbox</a></li>
															</ul>
														</li>
														<li class="nav-parent">
															<a>
																My Time Table
															</a>
															<ul class="nav nav-children">	
																<li><a href="viewMyTimeTable.html" target="frame">My Time Table</a></li> 
															</ul>
														</li>
														<li class="nav-parent">
															<a>
																Survey & Feedback
															</a>
															<ul class="nav nav-children">																	
																<li><a href="giveSurvey.html?userId=${sessionScope.sessionObject.userId}&courseCode=${sessionScope.sessionObject.courseCode}" target="frame">Take Survey</a></li>
															</ul>
														</li>
														<li class="nav-parent">
															<a>
																Download MarkSheet
															</a>
															<ul class="nav nav-children">																	
																<li><a href="downloadMarkSheet.html?courseCode=${sessionScope.sessionObject.courseCode}" target="frame">Download MarkSheet</a></li>
															</ul>
														</li>
														
														<li class="nav-parent">
															<a>
																My Salary Slip
															</a>
															<ul class="nav nav-children">																	
																<li><a href="getMySalarySlip.html" target="frame">Salary Slip</a></li>
															</ul>
														</li>	
														<li class="nav-parent">
															<a>
																My School Note
															</a>
															<ul class="nav nav-children">																	
																<li><a href="getMySchoolNote.html" target="frame">School Note</a></li>
															</ul>
														</li>
														
													</ul>
												</li>
													
												<li class="nav-parent">
													<a>											
														<i class="fa fa-gear" aria-hidden="true"></i>
														<span>Profile</span>
													</a>
													<ul class="nav nav-children">
														<li>
															<a href="viewOwnProfile.html?userId=${sessionScope.sessionObject.userId}&role=${sessionScope.sessionObject.currentRoleOrAccess}" target="frame">View Personal Profile</a>
														</li>
													</ul>		
												</li>
												
														
											
												
								</ul>
							</nav>				
							
						</div>
				
					</div>
				
				</aside>
				<!-- end: sidebar -->

				<section role="main" class="content-body">
					

					<!-- start: page -->
						
						<iframe src="dashboard.html" name="frame" id="frame" scrolling="no" frameborder="0" class="iframe" width="100%">

						</iframe>
					<!-- end: page -->
				</section>
			</div>
			
			
		</section>
		<div id="chat_div1"></div>
		
		<%--  <div id="pop1">
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
      </div> --%>
      <div id="pop" class="modal-block modal-header-color modal-block-info">
        <section class="panel">
            <header class="panel-heading">
                <h2 class="panel-title">User List</h2>
            </header>
            <div class="panel-body">
                <div class="form-group">
                     <label for="chatroom" class="control-label" >Select User</label>
                     <select class="form-control" id="chatroom">
                         <option value="null">Please Select</option>
	                        <%--  <c:forEach var="resource" items="${resourceList}">
								<option value="${resource.name}">${resource.name}</option>
							</c:forEach> --%>	
                     </select>
                 </div>
            </div>
            <footer class="panel-footer">
                <div class="row">
                    <div class="col-md-12 text-right">
                        <!-- <button class="btn btn-info modal-dismiss">OK</button> -->
                        <button class="btn btn-large btn-primary" type="submit" id="enterRoom">Start Chat</button>
                    </div>
                </div>
            </footer>
        </section>
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
        <%@ include file="/include/js-include.jsp" %>
        <script src="/icam/assets/vendor/autosize/iframeResizer.min.js"></script>
        <script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script>
        <!--Group Chat -->
		<!--     <script type="text/javascript" src="/icam/js/common/chat/jquery-ui-1.8.2.custom.min.js"></script> -->
		  
		    <script type="text/javascript" src="/icam/js/common/chat/jquery.ui.chatbox.js"></script>    
		 	<script type="text/javascript" src="/icam/js/common/chat/groupChat.js"></script>
		<!-- Group Chat Ends -->
		
		<!-- Individual Chat  --> 
		
		<script src="/icam/js/common/chat/jquery.ui.individualChatbox.js"></script>
		<script src="/icam/js/common/chat/individualChat.js"></script>
         <script type="text/javascript">

			iFrameResize({
				log                     : true,                  // Enable console logging
				inPageLinks             : true,
				
			});
			
			
			
			
			  $(".roleChange").each(function(){
					$(this).click(function(){
						var roleName=this.name;
						if(roleName!=''){
							$( "#roleName" ).val(roleName);					
							$( "#notificationPage" ).submit();
						}	
				});
			  });
			  
			var box= null;  
			  function openChatWindow(){
				  
				  var  to = document.getElementById("chatBy").value;
					var from = document.getElementById("chatTo").value
					
					$.ajax({
				        url: '/icam/notifyChat.html',
				        data:{
				        	From:from,
				        	To:to			        	
				        },
				        success:function(data){
				        	//alert("data=="+data);
				        	var arr = data.split(";");
							room = arr[0];
							
							var IP = arr[1];
							connectToChatserver(IP);
							
				        }
					});
					
					
					if(box)	{				
						box.individualChatbox("option", "boxManager").toggleBox();
						leaveRoom();
						$("#chat_div1").html('');
					}
					else{
						
						$.ajax({
					        url: '/icam/getChatDetailsForIndividualChatForAUser.html',
					        data:{
					        	from:from,
					        	to:to			        	
					        },
					        success:function(data){
					        	
					        	var arr = data.split(";");
					        	var chatMsg = '';
					        	for(var i= 0;i< arr.length-1;i++){
					        		var dataArr = arr[i].split("*");
					        		
					        		chatMsg = chatMsg +"<div><span class = 'username' style='color:#ff0000'>"+dataArr[0]+"</span><span style='color:#333'>"+dataArr[1]+"</span><br></div>"
					        	}
					        	$("#chat_div1").html(chatMsg);
					        	
								
					        }
						});
						
						status=false;
						
						box = $("#chat_div1").individualChatbox({
							offset: 0,
							id:"chat_div1", 
		                    user:{key : "value"},
							title : "Personal Chat ",						
							messageSent : function(id, user, msg){						
								id=$("#nickname").val(); 
								//alert("id==="+id);
								$("#chat_div1").individualChatbox("option", "boxManager").addMsg(id, msg);
								//alert("sendMessage")
								sendMessage(id, msg) ;
								
		                    },
		                    boxClosed: function(id) {
		                    	leaveRoom();
		                    	$("#chat_div1").html('');
		                    }
						});
						
						document.getElementById("chatNotificationDiv").style.display = "none";
					}

			  }
			  
			  function ChatDetails(){
					 // alert("hiiii");
						$.ajax({
							url: '/icam/getChatCall.html',
							data:"userName=<c:out value="${sessionScope.sessionObject.userName}"/>",			       
							success: function(dataDB) {	
								//alert("within=="+dataDB);
								if(dataDB!= ''){	        		
									var arr=dataDB.split(":");
									var from= arr[0];
									var to = arr[1];
									//alert("to"+to);
									$("#chatMsg").html("Personal Chat Opened For You By "+to);
									document.getElementById("chatBy").value = to;
									document.getElementById("chatTo").value = from;
									
									document.getElementById("chatInfo").style.display='block';	
									$("#chatInfo").fadeOut(1000).fadeIn(1000).fadeOut(1000).fadeIn(1000).fadeOut(1000).fadeIn(1000);
									 document.getElementById("chatInfo").style.display='none';
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
						setInterval(function(){ChatDetails();},1000);
						$(".roleChange").each(function(){		
							$(this).click(function(){
								var roleName=this.name;
								if(roleName!=''){					
									$( "#roleName" ).val(roleName);					
									$( "#navigation" ).submit();
									}	
							});
					  }); 
					});
		</script>
	</body>

</html>