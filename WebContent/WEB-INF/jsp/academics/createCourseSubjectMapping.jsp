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
		<header class="page-header">
			<h2>Standard Subject Mapping</h2>
		</header>
		<div class="content-padding">
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
			<c:when test="${subjectGroupList eq null || empty subjectGroupList
			 				|| subjectList eq null || empty subjectList}">
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<c:if test="${subjectGroupList eq null || empty subjectGroupList}">
						<span id="errormsg">Subject Not Found</span>	
					</c:if>
					<c:if test="${subjectList eq null || empty subjectList}">
						<span id="errormsg">Subject Not Found</span>	
					</c:if>
				</div>
			</c:when>
			<c:otherwise>
			<form name="subjectForm" id="subjectForm" method="POST" action="editCourseSubjectMapping.html" >
				<div class="col-md-4 col-md-offset-4">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
							<h2 class="panel-title">Standard</h2>										
						</header>
						<div style="display: block;" class="panel-body">
                        	<div class="form-group">
                            	<label class="control-label">Standard <span class="required" aria-required="true">*</span></label>
                                <select class="form-control" id="course" name="course" required>
                               		<option value="">Select</option>
                                   	<c:forEach var="course" items="${courseList}" varStatus="i">
										<option value="${course.courseCode}">${course.courseName}</option>
									</c:forEach>
                                </select>
                           </div>
						</div>
					</section>
				</div>
				<div class="col-md-12">
                	<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
							</div>
							<div class="alert alert-danger" id="javascriptmsg2" style="display: none">
	 							<span> </span>	
							</div>
							<h2 class="panel-title">Subject &amp; Mapping</h2>
						</header>
						<div class="panel-body">
							<c:set var="i" value="0" scope="page" />
							<c:forEach var="subjectGroup" items="${subjectGroupList}" varStatus="j">
						 	<c:if test="${i eq 0}"></c:if>
							<c:set var="i" value="${i+1}" scope="page" />                                
                       		<div class="col-md-3">
	                            <div class="well well-sm info no-margin">
	                                <div>
	                                    Subject Name :: ${subjectGroup.subjectGroupName}
	                                </div>
	                            </div>
                                <table class="table table-bordered table-striped mb-none">
                                    <thead>
                                        <tr>
                                            <th>Select</th>
                                            <th>Subject Name</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="subject" items="${subjectList}" varStatus="k">
	                                    	<c:if test="${subject.subjectGroup eq subjectGroup.subjectGroupName}">
		                                         <tr>
		                                             <td><input type="checkbox" class="subjectsCheckBox" id="${subject.subjectName}" name="subjects" value="${subject.subjectName}" onclick="removeMsg()"></td>
		                                             <td>${subject.subjectName}</td>
		                                         </tr>
		                                    </c:if>
	                                    </c:forEach>
                                    </tbody>
                                </table>								    
                           	</div>
                           	</c:forEach>
                            <c:if test="${i eq 4}">
							<c:set var="i" value="0" scope="page" />
							</c:if>
                      	</div>
						
						<c:if test="${i ne 4}"></c:if>
						<footer style="display: block;" class="panel-footer">
		                    <button class="btn btn-primary" type = "submit" onclick = " return validation()">Submit </button>
		                    <button type="reset" class="btn btn-default">Reset</button>
		                </footer>
					</section>	
               	</div>
				
                <div id="oldSubjects" style="display: none;"></div>
			</form>
			</c:otherwise>
			</c:choose>
			</div>
		</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/academics/createCourseSubjectMapping.js"></script>
</body>
</html>