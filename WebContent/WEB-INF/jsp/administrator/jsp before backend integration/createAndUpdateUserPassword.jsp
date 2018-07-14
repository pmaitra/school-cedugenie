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
						<div class="col-md-6 col-md-offset-3">
						  <form:form method="POST" name="createAndUpdateUserPassword" id="createAndUpdateUserPassword" commandName="createAndUpdateUserPassword" action="createAndUpdateUserPassword.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Manage User Password</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        <div class="form-group">
                                            <label class="control-label">Resource Type</label>
                                            <select class="form-control" name='resourceType' id="resourceType" >
                                                <option value="">Select...</option>
                                                <c:forEach var="resourceType" items="${resourceTypeList}">
													<option value="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
												</c:forEach>	
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">User Id</label>
                                            <input type="text" class="form-control" name="userId" id="userId" placeholder="">
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">Name</label>
                                            <input type="text" class="form-control" id="name" name="name" placeholder="">
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">New Password</label>
                                            <input type="text" class="form-control" id="newPassword" name="newPassword" placeholder="">
                                        </div>
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id="changePassword" name="change" >Update Password</button>
                                        <button class="btn btn-danger" id="submitNewPassword" name="submit" >Submit New Password</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                           </form:form>
						</div>						
					</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>