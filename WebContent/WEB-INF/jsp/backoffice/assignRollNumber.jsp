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
<title>Assign Roll Number</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
</style>
</head>
<body>
				<header class="page-header">
					<h2>Assign Roll Number</h2>
				</header>
				<div class="content-padding">
					<c:if test="${insertStatus eq 'success'}">
						<div class="alert alert-success">
							<strong>${msg}</strong>
						</div>
					</c:if>
					
					<c:if test="${insertStatus eq 'fail'}">
						<div class="alert alert-danger">
							<strong>${msg}</strong>
						</div>
					</c:if>
					<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<form:form method="POST" id="assignRollNumber" name="assignRollNumber" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Academic Session</h2>										
									</header>
									<div style="display: block;" class="panel-body"> 
									<c:forEach var="academicYear" items="${academicYearList}">
										<c:if test="${academicYear.yearStatus eq 'CURRENT'}">   
                                        <div class="form-group">
                                            <label class="col-md-5 control-label">Academic Session :</label>
                                            <div class="col-md-7 control-label">
                                            	<input type="hidden" name="academicYearCode" id = "academicYearCode" value="${academicYear.academicYearCode}">
                                                ${academicYear.academicYearName}
                                            </div>  
                                        </div>
                                        </c:if>
                                        </c:forEach>
                                        <div class="form-group">
                                            <label class="col-md-5 control-label">Standard :</label>
                                            <div class="col-md-7">
                                                <select class="form-control" name="courseName" id="courseName" required >
                                                    <option value="">Select...</option>
                                                    <c:forEach var="course" items="${allCourseList}" varStatus="i">
														<option value="${course.courseCode}">${course.courseName}</option>
													</c:forEach>
                                                </select> 
                                            </div>
                                        </div>
									</div>
									<div class="warningbox" id="warningbox" >
										<span id="warningmsg"></span>	
									</div>
									<div  style="display: none;" id = "footer">
										<input type="hidden" name="courseId" id = "courseId">
										<footer style="display: block;" class="panel-footer">
											<!-- 	<button class="btn btn-primary" type="submit" id="submit" name="submit" >Generate  </button>onclick = "genarateRollNumber()" -->
											  <a id = "generate" class="mb-xs mt-xs mr-xs modal-basic btn btn-info" >Generate</a>
										</footer>
									</div>
								</section>
							</form:form>	                          
						</div>
					</div>	
				<div class="row">
					<div id="assignRoll" class="col-md-12" style="display: none;" >	
						<section class="panel">		
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="rollTable" >
									<thead>
										<tr>
											<th>Roll Number</th>
											<th>Name</th>
										</tr>
									</thead>
									<tbody id = assignRollTable>
									</tbody>
								</table>
							</div>
						</section>
					</div>
				</div>
			</div>
				
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/backoffice/assignRollNumber.js"></script>
</body>
</html>