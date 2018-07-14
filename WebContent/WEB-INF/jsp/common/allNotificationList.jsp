<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listBookPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Closed ticket list</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<div class="row">
						<div class="col-md-12">
                            <section class="panel">								
								<div class="panel-body">
									<div class="timeline timeline-simple mt-xlg mb-md">
										<div class="tm-body">
                                            <div class="tm-title">
												<h3 class="h5 text-uppercase">Notifications</h3>
											</div>
											<ol class="tm-items">
												<c:forEach var="ticketList" items="${listOfNotification}">
													<li>
														<div class="tm-box">
															<p class="text-muted mb-none">${ticketList.notificationDate} -- ${ticketList.notificationSender}</p>
															<p>
																${ticketList.notificationDesc}
															</p>
														</div>
													</li>
												</c:forEach>
												<c:forEach var="ticketListNew" items="${listOfNotificationForTask}">
													<li>
														<div class="tm-box">
															<p class="text-muted mb-none">25th Aug 2016, -- ${ticketListNew.notificationSender}</p>
															<p>
																${ticketListNew.notificationDesc}
															</p>
														</div>
													</li>
												</c:forEach>
												<c:forEach var="ticketListNew" items="${listOfNotificationFOrmAlert}">
													<li>
														<div class="tm-box">
															<p class="text-muted mb-none"> ${ticketListNew.notificationSender}</p>
															<p>
																${ticketListNew.notificationDesc}
															</p>
														</div>
													</li>
												</c:forEach>
											</ol>
										</div>
									</div>
								</div>
							</section>
						</div>
						
					</div>	
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>