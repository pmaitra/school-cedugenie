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
<title>All Messages list</title>

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
												<h3 class="h5 text-uppercase">Messages</h3>
											</div>
											<ol class="tm-items">
												<c:forEach var="messageList" items="${listOfAlerts}">
													<li>
														<div class="tm-box">
															<p class="text-muted mb-none">${messageList.receiveTime} -- ${messageList.notificationSender}</p>
															<p>
																${messageList.notificationDesc}
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
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>