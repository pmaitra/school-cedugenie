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
	</head>
	<body>
		<section class="body">

			<!-- start: header -->
			<header class="header">
				<div class="logo-container">
					<a href="index.html" class="logo">
						<img src="assets/images/logo.png" height="56" alt="cEduGenie Admin" />
					</a>
					<div class="visible-xs toggle-sidebar-left" data-toggle-class="sidebar-left-opened" data-target="html" data-fire-event="sidebar-left-opened">
						<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
					</div>
				</div>
			
				<!-- start: search & user box -->
				<div class="header-right">
			
					<form action="#" class="search nav-form">
						<div class="input-group input-search">
							<input type="text" class="form-control" name="q" id="q" placeholder="Search...">
							<span class="input-group-btn">
								<button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
							</span>
						</div>
					</form>
			
					<span class="separator"></span>
			
					<ul class="notifications">
						<li>
							<a href="#" class="dropdown-toggle notification-icon" data-toggle="dropdown">
								<i class="fa fa-tasks"></i>
								<span class="badge">3</span>
							</a>
			
							<div class="dropdown-menu notification-menu large">
								<div class="notification-title">
									<span class="pull-right label label-default">3</span>
									Tasks
								</div>
			
								<div class="content">
									<ul>
										<li>
											<p class="clearfix mb-xs">
												<span class="message pull-left">Generating Sales Report</span>
												<span class="message pull-right text-dark">60%</span>
											</p>
											<div class="progress progress-xs light">
												<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;"></div>
											</div>
										</li>
			
										<li>
											<p class="clearfix mb-xs">
												<span class="message pull-left">Importing Contacts</span>
												<span class="message pull-right text-dark">98%</span>
											</p>
											<div class="progress progress-xs light">
												<div class="progress-bar" role="progressbar" aria-valuenow="98" aria-valuemin="0" aria-valuemax="100" style="width: 98%;"></div>
											</div>
										</li>
			
										<li>
											<p class="clearfix mb-xs">
												<span class="message pull-left">Uploading something big</span>
												<span class="message pull-right text-dark">33%</span>
											</p>
											<div class="progress progress-xs light mb-xs">
												<div class="progress-bar" role="progressbar" aria-valuenow="33" aria-valuemin="0" aria-valuemax="100" style="width: 33%;"></div>
											</div>
										</li>
									</ul>
								</div>
							</div>
						</li>
						<li>
							<a href="#" class="dropdown-toggle notification-icon" data-toggle="dropdown">
								<i class="fa fa-envelope"></i>
								<span class="badge">4</span>
							</a>
			
							<div class="dropdown-menu notification-menu">
								<div class="notification-title">
									<span class="pull-right label label-default">230</span>
									Messages
								</div>
			
								<div class="content">
									<ul>
										<li>
											<a href="#" class="clearfix">
												<figure class="image">
													<img src="assets/images/%21sample-user.jpg" alt="Joseph Doe Junior" class="img-circle" />
												</figure>
												<span class="title">Joseph Doe</span>
												<span class="message">Lorem ipsum dolor sit.</span>
											</a>
										</li>
										<li>
											<a href="#" class="clearfix">
												<figure class="image">
													<img src="assets/images/%21sample-user.jpg" alt="Joseph Junior" class="img-circle" />
												</figure>
												<span class="title">Joseph Junior</span>
												<span class="message truncate">Truncated message. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet lacinia orci. Proin vestibulum eget risus non luctus. Nunc cursus lacinia lacinia. Nulla molestie malesuada est ac tincidunt. Quisque eget convallis diam, nec venenatis risus. Vestibulum blandit faucibus est et malesuada. Sed interdum cursus dui nec venenatis. Pellentesque non nisi lobortis, rutrum eros ut, convallis nisi. Sed tellus turpis, dignissim sit amet tristique quis, pretium id est. Sed aliquam diam diam, sit amet faucibus tellus ultricies eu. Aliquam lacinia nibh a metus bibendum, eu commodo eros commodo. Sed commodo molestie elit, a molestie lacus porttitor id. Donec facilisis varius sapien, ac fringilla velit porttitor et. Nam tincidunt gravida dui, sed pharetra odio pharetra nec. Duis consectetur venenatis pharetra. Vestibulum egestas nisi quis elementum elementum.</span>
											</a>
										</li>
										<li>
											<a href="#" class="clearfix">
												<figure class="image">
													<img src="assets/images/%21sample-user.jpg" alt="Joe Junior" class="img-circle" />
												</figure>
												<span class="title">Joe Junior</span>
												<span class="message">Lorem ipsum dolor sit.</span>
											</a>
										</li>
										<li>
											<a href="#" class="clearfix">
												<figure class="image">
													<img src="assets/images/%21sample-user.jpg" alt="Joseph Junior" class="img-circle" />
												</figure>
												<span class="title">Joseph Junior</span>
												<span class="message">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sit amet lacinia orci. Proin vestibulum eget risus non luctus. Nunc cursus lacinia lacinia. Nulla molestie malesuada est ac tincidunt. Quisque eget convallis diam.</span>
											</a>
										</li>
									</ul>
			
									<hr />
			
									<div class="text-right">
										<a href="#" class="view-more">View All</a>
									</div>
								</div>
							</div>
						</li>
						<li>
							<a href="#" class="dropdown-toggle notification-icon" data-toggle="dropdown">
								<i class="fa fa-bell"></i>
								<span class="badge">3</span>
							</a>
			
							<div class="dropdown-menu notification-menu">
								<div class="notification-title">
									<span class="pull-right label label-default">3</span>
									Alerts
								</div>
			
								<div class="content">
									<ul>
										<li>
											<a href="#" class="clearfix">
												<div class="image">
													<i class="fa fa-thumbs-down bg-danger"></i>
												</div>
												<span class="title">Server is Down!</span>
												<span class="message">Just now</span>
											</a>
										</li>
										<li>
											<a href="#" class="clearfix">
												<div class="image">
													<i class="fa fa-lock bg-warning"></i>
												</div>
												<span class="title">User Locked</span>
												<span class="message">15 minutes ago</span>
											</a>
										</li>
										<li>
											<a href="#" class="clearfix">
												<div class="image">
													<i class="fa fa-signal bg-success"></i>
												</div>
												<span class="title">Connection Restaured</span>
												<span class="message">10/10/2014</span>
											</a>
										</li>
									</ul>
			
									<hr />
			
									<div class="text-right">
										<a href="#" class="view-more">View All</a>
									</div>
								</div>
							</div>
						</li>
					</ul>
			
					<span class="separator"></span>
			
					<div id="userbox" class="userbox">
						<a href="#" data-toggle="dropdown">
							<figure class="profile-picture">
								<img src="assets/images/%21logged-user.jpg" alt="Joseph Doe" class="img-circle" data-lock-picture="assets/images/%21logged-user.jpg" />
							</figure>
							<div class="profile-info" data-lock-name="John Doe" data-lock-email="johndoe@okler.com">
								<span class="name">John Doe Junior</span>
								<span class="role">administrator</span>
							</div>
			
							<i class="fa custom-caret"></i>
						</a>
			
						<div class="dropdown-menu">
							<ul class="list-unstyled">
								<li class="divider"></li>
								<li>
									<a role="menuitem" tabindex="-1" href="#"><i class="fa fa-user"></i> My Profile</a>
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
										<a target="_self" href="notificationPage.html">
											<i class="fa fa-home" aria-hidden="true"></i>
											<span>Dashboard</span>
										</a>
									</li>
									

												<li class="nav-parent nav-active nav-expanded">
													<a>
														<i class="fa fa-institution" aria-hidden="true"></i>
														<span>Finance</span>
													</a>
													
													
													<ul class="nav nav-children">
														<c:set var="i" value="0" scope="page" />
															<c:forEach var="functionality" items="${role.functionalityList}" >
																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Financial Year')}">
																	<c:if test="${functionality.update eq true || functionality.insert eq true || functionality.view eq true}">
																		<c:set var="i" value="1" scope="page" />
																	</c:if>
																</c:if>
															</c:forEach>
														<c:if test="${i eq 1}">													
															<li class="nav-parent">
																<a>
																	Configure Financial Year
																</a>
																<ul class="nav nav-children">																	
<%-- 																	<c:forEach var="functionality" items="${role.functionalityList}" > --%>
<%-- 																		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Configure Financial Year')}"> --%>
<%-- 																			<c:if  test="${functionality.insert eq true}"> --%>
<!-- 																				<li><a href ="configureFinancialYear.html" target="frame">Configure Financial Year</a></li> -->
<%-- 																			</c:if> --%>
<%-- 																		</c:if> --%>
<%-- 																	</c:forEach> --%>
																	<li><a href ="configureFinancialYear.html" target="frame">Configure Financial Year</a></li>
																</ul>
															</li>
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
															<li class="nav-parent">
																<a>
																	Group
																</a>
																<ul class="nav nav-children">
																
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
															</li>
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
															<li class="nav-parent">
																<a>
																	Ledger
																</a>
																<ul class="nav nav-children">
																
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
																				<li><a href ="templateLedgerMapping.html" target="frame">Ledger Mapping</a></li>
																</ul>
															</li>
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
														<li class="nav-parent">
															<a>
																Create Transaction
															</a>
															<ul class="nav nav-children">
																	<li><a href ="createTransactionPage.html" target="frame">Create Transaction</a></li>
																	<li><a href ="transactionWorkingAreaList.html" target="frame">Working Area</a></li>
																	<li><a href ="securityDeposit.html" target="frame">Security Deposit</a></li>
																	<li><a href ="passbook.html" target="frame">Passbook Entry</a></li>
<%-- 																<c:forEach var="functionality" items="${role.functionalityList}" > --%>
<%-- 																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Fees Template Ledger Mapping')}"> --%>
<%-- 																		<c:if  test="${functionality.insert eq true}"> --%>
<!-- 																			<li><a href ="feesTemplateLedgerMapping.html" target="frame">Fees Template Ledger Mapping</a></li> -->
<%-- 																		</c:if> --%>
<%-- 																	</c:if> --%>
<%-- 																</c:forEach> --%>
																
<%-- 																<c:forEach var="functionality" items="${role.functionalityList}" > --%>
<%-- 																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Student Fees')}"> --%>
<%-- 																		<c:if  test="${functionality.insert eq true}"> --%>
<!-- 																			<li><a href ="getStudentFees.html" target="frame">Student Fees</a></li> -->
<%-- 																		</c:if> --%>
<%-- 																	</c:if> --%>
<%-- 																</c:forEach> --%>
																
<%-- 																<c:forEach var="functionality" items="${role.functionalityList}" > --%>
<%-- 																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Create Other Transactions')}"> --%>
<%-- 																		<c:if  test="${functionality.view eq true}"> --%>
<!-- 																			<li><a href ="createTransactionPage.html" target="frame">Create Other Transactions</a></li> -->
<%-- 																		</c:if> --%>
<%-- 																	</c:if> --%>
<%-- 																</c:forEach> --%>
																
<%-- 																<c:forEach var="functionality" items="${role.functionalityList}" > --%>
<%-- 																	<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Passbook Entry')}"> --%>
<%-- 																		<c:if  test="${functionality.view eq true}"> --%>
<!-- 																			<li><a href ="passbook.html" target="frame">Passbook Entry</a></li> -->
<%-- 																		</c:if> --%>
<%-- 																	</c:if> --%>
<%-- 																</c:forEach> --%>
															</ul>
														</li>
														</c:if>	
															
<%-- 														<c:set var="i" value="0" scope="page" /> --%>
<%-- 															<c:forEach var="functionality" items="${role.functionalityList}" > --%>
<%-- 																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Employee Salary Details') --%>
<%-- 																			 || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Designation Salary Details') --%>
<%-- 																			 || fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Salary Disbursement List')}"> --%>
<%-- 																	<c:if  test="${functionality.update eq true || functionality.insert eq true || functionality.view eq true}"> --%>
<%-- 																		<c:set var="i" value="1" scope="page" /> --%>
<%-- 																	</c:if> --%>
<%-- 																</c:if> --%>
<%-- 															</c:forEach> --%>
<%-- 														<c:if test="${i eq 1}"> --%>
<!-- 															<li class="nav-parent"> -->
<!-- 																<a> -->
<!-- 																	Employee Salary Transaction -->
<!-- 																</a> -->
<!-- 																<ul class="nav nav-children"> -->
<%-- 																	<c:forEach var="functionality" items="${role.functionalityList}" > --%>
<%-- 																		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Employee Salary Details')}"> --%>
<%-- 																			<c:if  test="${functionality.insert eq true}"> --%>
<!-- 																				<li><a href ="getEmployeeSalaryDetails.html" target="frame">Employee Salary Details</a></li> -->
<%-- 																			</c:if> --%>
<%-- 																		</c:if> --%>
<%-- 																	</c:forEach> --%>
																	
<%-- 																	<c:forEach var="functionality" items="${role.functionalityList}" > --%>
<%-- 																		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Designation Salary Details')}"> --%>
<%-- 																			<c:if  test="${functionality.insert eq true}"> --%>
<!-- 																				<li><a href ="designationSalaryDetails.html" target="frame">Designation Salary Details</a></li> -->
<%-- 																			</c:if> --%>
<%-- 																		</c:if> --%>
<%-- 																	</c:forEach> --%>
																
<%-- 																	<c:forEach var="functionality" items="${role.functionalityList}" > --%>
<%-- 																		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Salary Disbursement List')}"> --%>
<%-- 																			<c:if  test="${functionality.view eq true}"> --%>
<!-- 																				<li><a href ="getSalaryDisbursementList.html?month=NA" target="frame">Salary Disbursement List</a></li> -->
<%-- 																			</c:if> --%>
<%-- 																		</c:if> --%>
<%-- 																	</c:forEach> --%>
<!-- 																</ul> -->
<!-- 															</li> -->
<%-- 														</c:if>	 --%>
														
<%-- 														<c:set var="i" value="0" scope="page" /> --%>
<%-- 															<c:forEach var="functionality" items="${role.functionalityList}" > --%>
<%-- 																<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Vendor Payment')}"> --%>
<%-- 																	<c:if  test="${functionality.update eq true || functionality.insert eq true || functionality.view eq true}"> --%>
<%-- 																		<c:set var="i" value="1" scope="page" /> --%>
<%-- 																	</c:if> --%>
<%-- 																</c:if>	 --%>
<%-- 															</c:forEach> --%>
<%-- 														<c:if test="${i eq 1}"> --%>
<!-- 															<li class="nav-parent"> -->
<!-- 																<a> -->
<!-- 																	Vendor Payment Transaction -->
<!-- 																</a> -->
<!-- 																<ul class="nav nav-children"> -->
<%-- 																	<c:forEach var="functionality" items="${role.functionalityList}" > --%>
<%-- 																		<c:if test="${fn:toLowerCase(functionality.functionalityName) eq fn:toLowerCase('Vendor Payment')}"> --%>
<%-- 																			<c:if  test="${functionality.insert eq true}"> --%>
<!-- 																				<li><a href ="getAllPurchaseOrdersForPayment.html" target="frame">Vendor Payment</a></li> -->
<%-- 																			</c:if> --%>
<%-- 																		</c:if> --%>
<%-- 																	</c:forEach> --%>
<!-- 																</ul> -->
<!-- 															</li> -->
<%-- 														</c:if> --%>
														
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
															<li class="nav-parent">
																<a>
																	Financial Output
																</a>
																<ul class="nav nav-children">
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
															</li>
														</c:if>
														
														
													</ul>
												</li>
												
												<li class="nav-parent">
													<a href="getModuleDetails.html?moduleName=ACADEMICS">
														<i class="fa fa-mortar-board" aria-hidden="true"></i>
														<span>Academics</span>
													</a>
												</li>
												
												<li class="nav-parent">
													<a href="getModuleDetails.html?moduleName=ADMISSION">
														<i class="fa fa-institution" aria-hidden="true"></i>
														<span>Admission</span>
													</a>        
												</li>

												<li class="nav-parent">
													<a href="getModuleDetails.html?moduleName=LIBRARY">
														<i class="fa fa-book" aria-hidden="true"></i>
														<span>Libary</span>
													</a>        
												</li>

												<li class="nav-parent">
													<a href="getModuleDetails.html?moduleName=OFFICE ADMINISTRATION">
														<i class="fa fa-align-left" aria-hidden="true"></i>
														<span>General Section</span>
													</a>       
												</li>

												

												<li class="nav-parent">
													<a href="getModuleDetails.html?moduleName=ERP">
														<i class="fa fa-server" aria-hidden="true"></i>
														<span>ERP</span>
													</a>        
												</li>

												   <li class="nav-parent">
													<a href="getModuleDetails.html?moduleName=HOSTEL">
														<i class="fa fa-hotel" aria-hidden="true"></i>
														<span>Hostel</span>
													</a>        
												</li>

												<li class="nav-parent">
													<a href="getModuleDetails.html?moduleName=SYSTEM ADMINISTRATION">
														<i class="fa fa-gear" aria-hidden="true"></i>
														<span>System Administration</span>
													</a>        
												</li>

												<li class="nav-parent">
													<a href="getModuleDetails.html?moduleName=TICKETING">
														<i class="fa fa-tasks" aria-hidden="true"></i>
														<span>Ticketing</span>
													</a>        
												</li>

												<li class="nav-parent">
													<a href="getModuleDetails.html?moduleName=INVENTORY">											
														<i class="fa fa-file-text-o" aria-hidden="true"></i>
														<span>Inventory</span>
													</a>        
												</li>


										 <li class="nav-parent">
											<a href="getModuleDetails.html?moduleName=REPORT">
												<i class="fa fa-bar-chart" aria-hidden="true"></i>
												<span>Report</span>
											</a>		   
									   </li>
										<li class="nav-parent">
											<a href="userServices.html">
												<i class="fa fa-wrench" aria-hidden="true"></i>
												<span>My Services</span>
											</a>		   
										</li>
								</ul>
							</nav>				
							
						</div>
				
					</div>
				
				</aside>
				<!-- end: sidebar -->

				<section role="main" class="content-body">
					<header class="page-header">
						<h2>Dashboard</h2>
					
						<div class="right-wrapper pull-right">
							<ol class="breadcrumbs">
								<li>
									<a href="index.html">
										<i class="fa fa-home"></i>
									</a>
								</li>
								<li><span>Dashboard</span></li>
							</ol>
					
							<!--<a class="sidebar-right-toggle" data-open="sidebar-right"><i class="fa fa-chevron-left"></i></a>-->
						</div>
					</header>

					<!-- start: page -->
						
						<iframe src="admissionPocessFlow.html" name="frame" id="frame" scrolling="no" frameborder="0" class="iframe" width="100%">
					
						</iframe>
					<!-- end: page -->
				</section>
			</div>
			
			
		</section>
        <%@ include file="/include/js-include.jsp" %>
        <script src="/icam/assets/vendor/autosize/iframeResizer.min.js"></script>
         <script type="text/javascript">

			iFrameResize({
				log                     : true,                  // Enable console logging
				inPageLinks             : true,
				
			});

		</script>
	</body>

</html>