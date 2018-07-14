<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Set Exam Marks(Old System)</title>
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
		<h2>Set Exam Marks (Old System)</h2>
	</header>
	<div class = "content-padding">
		<div class="row">
			<c:if test="${insertUpdateStatus eq 'success'}">
				<div class="alert alert-success">
					<strong>${msg}</strong>
				</div>
			</c:if>
			
			<c:if test="${insertUpdateStatus eq 'fail'}">
				<div class="alert alert-danger">
					<strong>${msg}</strong>
				</div>
			</c:if>
			<c:choose>
			<c:when test="${courseList eq null || empty courseList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<c:if test="${courseList eq null || empty courseList}">
							<span id="errormsg">Standard Not Found</span>	
						</c:if>
					</div>
				</div>
			</c:when>
			<c:otherwise>
			<form method="POST" action="editExamMarks.html" >
				<input type="hidden" name="type" value="create">
				<input type="hidden" name="length" id="length">
					<div class="col-md-4 col-md-offset-4">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>
								<h2 class="panel-title">Standard &amp; Exam</h2>
							</header>
							<div style="display: block;" class="panel-body">
                              <div class="form-group">
                                  <label class="control-label">Standard<span class="required" aria-required="true">*</span></label>
                                  <select class="form-control" id="standard" name="standard" required>
                                  	<option value="">Select..</option>
                                  	 <c:forEach var="course" items="${courseList}" varStatus="i">
										<option value="${course.courseCode}">${course.courseName}</option>
									</c:forEach>
                                  </select>
                              </div>
                              <!-- <div class="form-group">
                                  <label class="control-label">Term  <span class="required" aria-required="true">*</span></label>
                                  <select class="form-control" id="term" name="term" required>
                                  	<option value="">Select</option>
                                  </select>
                              </div> -->
                              <div class="form-group">
                                  <label class="control-label">Exam<span class="required" aria-required="true">*</span></label>
                                  <select class="form-control" id="exam" name="exam" required>
                                  	<option value="">Select..</option>
                                  </select>
                              </div>
							</div>
						</section>
					</div>
                   	<div class="col-md-12" id = "setExamMarksDiv" style = "display:none">
                        <section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
							</div>
							<h2 class="panel-title">Set Exam Marks</h2>
						</header>
						<div class="panel-body">
							<table class="table table-bordered table-striped mb-none">
								<thead>
									<tr>
										<th>Subject</th>
										<th>Theory</th>
										<th>Theory Pass</th>
										<th>Practical</th>
										<th>Practical Pass</th>
										<th>Total</th>
										<th>Total Pass</th>
									</tr>
								</thead>
								<tbody id="tableBody">
								</tbody>
							</table>
							<div class="alert alert-danger" id="javascriptmsg2" style="display: none">
	 							<span></span>	
							</div>
						</div>
                         <footer style="display: block;" class="panel-footer">
                             <button class="btn btn-primary" id="submitMarks" onclick="return detailsSubmit()">Submit </button>
                             <button type="reset" class="btn btn-default">Reset</button>
                         </footer>
                        </section>
                    </div>
				</form>
			</c:otherwise>
			</c:choose>
		</div>
	</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/academics/setExamMarks.js"></script>
</body>
</html>