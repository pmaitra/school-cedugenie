<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Student Details Form</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       } .mb-md{
       	   display: none;
       }
</style>
<script type="text/javascript">
function editableField(){
	var inputs = document.getElementsByTagName("input");
	for(var i = 0; i < inputs.length; i++) {
		if(inputs[i].type == "checkbox") {
			inputs[i].disabled = false;
		}  
	}
	document.getElementById("clearNotificationMedium").removeAttribute('disabled');
	document.getElementById("update").removeAttribute('disabled');
}
</script>
</head>
<body>
	<header class="page-header">
			<h2>Notification Medium Setup</h2>	<!--  ADDED BY SAIF 29-03-2018 -->
	</header>
		<div class = "content-padding">
			<div class="row">
						<div class="col-md-6 col-md-offset-3">
						
						<c:choose>
							<c:when test="${notificationMediumList eq null}">
								<div class="alert alert-danger" id="errorbox">
									<strong>Notification Type And Notification Medium Not Available</strong>	
								</div>						
							</c:when>
						<c:otherwise>
						<c:set var="notificationMediumListSize" value="0" scope="page" />	
						 <form:form method="POST" id="createClassDb" name="" action="updateNotificationMediums.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Notification Medium Setup</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        <table class="table table-bordered table-striped mb-none">
                                            <thead>
                                                <tr>
                                                    <th>Notification Type</th>
                                                 	  <c:if test="${notificationMediumList[0].notificationMediums ne null}">
														<c:set var="notificationMediumSize" value="${notificationMediumList[0].notificationMediums.size()}" scope="page" />
														<c:forEach var="notificationMedium" items="${notificationMediumList[0].notificationMediums}">
															<th>
																${notificationMedium.notificationMediumName}
															</th>
														</c:forEach>
													</c:if>
                                                </tr>
                                            </thead>
                                            <tbody> 
                                            	<c:forEach var="notification" items="${notificationMediumList}" varStatus="count">                                           
	                                                <tr>                                                
	                                                    <td><input type="text" class="form-control" name="notificationLevelName" value="${notification.notificationLevel}"  placeholder=""  readonly></td>
<!-- 	                                                    <td><input type="checkbox" id="" checked="" disabled></td> -->
<!-- 	                                                    <td><input type="checkbox" id="" checked="" disabled></td> -->
															<c:if test="${notificationMediumList[0].notificationMediums ne null}">
																<c:forEach var="notificationMedium" items="${notification.notificationMediums}">
																	<td>
																		<c:choose>
																			<c:when test="${notificationMedium.status eq null}">
																				<input type="checkbox"  name="${notification.notificationLevel}" id="notificationMedium" value="${notificationMedium.notificationMediumName}" disabled="disabled" />
																			</c:when>
																			<c:otherwise>
																				<input type="checkbox"  name="${notification.notificationLevel}" id="notificationMedium" value="${notificationMedium.notificationMediumName}" checked="checked" disabled="disabled"/>
																			</c:otherwise>
																		</c:choose>	
																	</td>
																</c:forEach>
															</c:if>
	                                                </tr>
                                               </c:forEach>
                                            </tbody>
                                        </table>
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="button" class="btn btn-primary" id="editNotificationMedium" onclick="return editableField();">Edit</button>
                                        <button type="submit" id="update" disabled="disabled" class="btn btn-danger">Update</button>
										<button type="reset" class="btn btn-default" id="clearNotificationMedium" disabled="disabled">Reset</button>
									</footer>
								</section>
                            </form:form>
                           </c:otherwise>
                         </c:choose>
					</div>						
				</div>
		</div>
							
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>