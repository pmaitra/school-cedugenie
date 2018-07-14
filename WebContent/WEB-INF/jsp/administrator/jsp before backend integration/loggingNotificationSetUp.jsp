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
</head>
<body>
	 <div class="row">
						<div class="col-md-12">
                           <form:form action="updateLoggingNotificationSetUp.html" method="POST">	
                           	<c:choose>
								<c:when test="${moduleForNotification eq null }">
									<div class="errorbox" id="errorbox" style="visibility: visible;">
										<span id="errormsg">Data Not Found</span>	
									</div>						
								</c:when>
		
								<c:otherwise>
			                            <section class="panel">
			                                <header class="panel-heading">
			                                    <div class="panel-actions">
			                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
			                                    </div>
			
			                                    <h2 class="panel-title">Module Name :: ${moduleForNotification.moduleName}</h2>
			                                    <input type="hidden" value="${moduleForNotification.moduleName}" name="moduleName">
			                                    
			                                </header>
			                                <div class="panel-body">
			
			                                    <table class="table table-bordered table-striped mb-none">
			                                        <thead>
			                                            <tr>
			                                                <th>Functionality Name</th>
			                                                <th>Task</th>
			                                                <th>Activity Log</th>
			                                                <th>Notification</th>
			                                                <th colspan="2">Notification Level</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody> 
			                                        	<c:forEach var="functionality"  items="${moduleForNotification.functionalityList}" varStatus="flag0">                                           
				                                            <tr style="background:#eee;">                                                
				                                                <td>${functionality.functionalityName} </td>
				                                                <td></td>
				                                                <td></td>
				                                                <td></td>
				                                                <td>URGENT</td>
				                                                <td>NORMAL</td>
				                                            </tr>
				                                           <c:forEach var="loggingAspect"  items="${functionality.loggingAspectList}" varStatus="flag1"> 
					                                            <tr>                                                
					                                                <td></td>
					                                                <td>
					                                                	<input type="hidden" name="functionalityList[${flag0.index}].loggingAspectList[${flag1.index}].methodName"  value="${loggingAspect.methodName}">
																		<input type="hidden" value="${loggingAspect.task}">
																		${loggingAspect.task}
																	</td>
																	<td>
																		<c:choose>
	    																	<c:when test="${loggingAspect.activityLog.equals(true)}">
	    																		<input type="checkbox" name="functionalityList[${flag0.index}].loggingAspectList[${flag1.index}].activityLog" checked="checked">
	    																	</c:when>
	    																	<c:otherwise>
	       	 																	<input type="checkbox" name="functionalityList[${flag0.index}].loggingAspectList[${flag1.index}].activityLog" >
	    																	</c:otherwise>
																		</c:choose>	
																	</td>
																	<td>
																		<c:choose>
														    				<c:when test="${loggingAspect.notification.equals(true)}">
														        				<input type="checkbox" class="chk" checked="checked" name="functionalityList[${flag0.index}].loggingAspectList[${flag1.index}].notification" >
														    				</c:when>
														    				<c:otherwise>
														       	 				<input type="checkbox" class="chk" name="functionalityList[${flag0.index}].loggingAspectList[${flag1.index}].notification" >
														    				</c:otherwise>
																		</c:choose>
																	</td>
			
					                                                <td>
					                                                    <select class="form-control" multiple="multiple" name="functionalityList[${flag0.index}].loggingAspectList[${flag1.index}].urgentNotificationResourceList[${flag1.index}].userId" data-plugin-multiselect data-plugin-options='{ "maxHeight": 200, "includeSelectAllOption": true }' id="ms_example5">
																			 <c:forEach var="urgent"  items="${loggingAspect.urgentNotificationResourceList}" varStatus="flag2" >
				 																	<option value="${urgent.code}" <c:if test="${urgent.status!=null}">selected="selected"</c:if> itemLabel="isicClassName">${urgent.userId}</option>
																			 </c:forEach>
																			

																		</select>
					                                                </td>
					                                                <td>
					                                                    <select class="form-control" multiple="multiple" name="functionalityList[${flag0.index}].loggingAspectList[${flag1.index}].normalNotificationResourceList[${flag1.index}].userId" data-plugin-multiselect data-plugin-options='{ "maxHeight": 200, "includeSelectAllOption": true }' id="ms_example5">
																	        <c:forEach var="normal"  items="${loggingAspect.normalNotificationResourceList}" >
				 																<option value="${normal.code}" <c:if test="${normal.status!=null}">selected="selected"</c:if> itemLabel="isicClassName">${normal.userId}</option>
																			 </c:forEach>
																	      </select>

			                                            	</c:forEach>
			                                            </c:forEach>
			                                        </tbody>
			                                    </table>
			                                </div>
			                                <footer style="display: block;" class="panel-footer">
			                                    <button type="submit" class="btn btn-primary">Update </button>
			                                    <button type="reset" class="btn btn-default">Reset</button>
			                                </footer>
			                            </section>
			                          </c:otherwise>
			                        </c:choose>
                           </form:form>
						</div>
					</div>	
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>