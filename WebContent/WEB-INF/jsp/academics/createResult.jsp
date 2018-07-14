<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de" class="fixed header-dark">
	
<head>

	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
	<title>Standard Subject Mapping</title>
	<%@ include file="/include/include.jsp" %>
	<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
	<style type="text/css">
	       .scroll-to-top{
	           display: none !important;
	       }.mb-md{
	       	   display: none;
	       }
	</style>
</head>
	<body>
	<c:if test="${insertUpdateStatus eq 'success' }">
		<div class="alert alert-success">
			<strong> ${msg}</strong>	
		</div>
	</c:if>
	<c:if test="${insertUpdateStatus eq 'fail' }">
		<div class="alert alert-danger">
			<strong>${msg} </strong>	
		</div>
	</c:if>
			<c:choose>
			<c:when test="${courseList eq null || empty courseList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<c:if test="${courseList eq null || empty courseList}">
							<span id="errormsg">Programme Not Found</span>	
						</c:if>
					</div>
				</div>
			</c:when>
			<c:otherwise>
			<%-- <form method="POST" action="editStudentResult.html" > --%>
			<!-- New CBSE System start -->
			<form name="subjectForm" id="subjectForm" method="POST" action="editIntoStudentResult.html" >
			<!-- New CBSE System end -->
				<input type="hidden" name="type" value="create">
				<!-- start: page -->
					 <div class="row">
						<div class="col-md-8 col-md-offset-2">
						  <form id="form1">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Programme &amp; Marks</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Programme</label>
		                                            <select class="form-control" id="course" name="course" required>
		                                            <option value="">Select</option>
		                                                <c:forEach var="course" items="${courseList}" varStatus="i">
															<option value="${course.courseCode}">${course.courseName}</option>
														</c:forEach>
		                                            </select>
		                                        </div>	
											</div>
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Batch</label>
		                                            <select class="form-control" id="section" name="section" disabled required>
		                                            	<option value="">Select</option>
		                                            </select>
		                                        </div>
											</div>
											<input type="hidden" name="standard" id ="standard">
											<!-- New CBSE System start -->
										 	<div class="col-md-6" id="termhead">
										 	<!-- New CBSE System end -->
												<div class="form-group">
		                                            <label class="control-label">Term</label>
		                                            <select class="form-control" id="term" name="term" disabled required>
		                                            	<option value="">Select</option>
		                                            </select>
		                                        </div>
											</div> 
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Course</label>
		                                            <select class="form-control" id="subject" name="subject" disabled required>
		                                            	<option value="">Select</option>
		                                            </select>
		                                        </div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Exam</label>
		                                            <select class="form-control" id="exam" name="exam" disabled required>
		                                            	<option value="">Select</option>
		                                            </select>
		                                        </div>
											</div>
											<input type="hidden" name="insertUpdate" id="insertUpdate" value="INSERT">
										</div>
                                          
									</div>
								</section>
                            </form>
						</div>
                        
                        <div class="col-md-12" id = "createResultDiv" style = "display:none">
                            <section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									</div>
							
									<h2 class="panel-title">Set Exam Marks</h2>
								</header>
								<div class="panel-body" id="tableDiv">
									<table class="table table-bordered table-striped mb-none">
										<thead id="tableHead">										
										</thead>
										<tbody id="tableBody">
										</tbody>
									</table>
								</div>
	                            <footer style="display: block;" class="panel-footer">
	                                <button class="btn btn-primary">Submit </button>
	                                <button type="reset" class="btn btn-default">Reset</button>
	                            </footer>
                            </section>
                        </div>
			</form>
			</c:otherwise>
			</c:choose>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
	<script type="text/javascript" src="/cedugenie/js/academics/createResult.js"></script>
	</body>


</html>