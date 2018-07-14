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
<title>Student Individual Marksheet</title>
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
	<h2>Student Individual Marksheet</h2>
</header>
<div class="content-padding">
	<c:if test="${message ne null}">	
		<div class="alert alert-danger" id="errorbox" style="display: block">
			<span id="errormsg">${message}</span>
		</div>
	</c:if>
	<c:choose>
		<c:when test="${academicYearList eq null  || standardList eq null}">
			<div class="alert alert-danger" id="errorbox" style="display: block">
				<span id="errormsg">No Academic Year/Standard/Exam Found</span>
			</div>
		</c:when>
	<c:otherwise>
	<div class= "row">
		<div class="col-md-6 col-md-offset-3">
			<form action="getStudentReportnew.html" method="post">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Details</h2>										
					</header>
					<div style="display: block;" class="panel-body">
						<div class="form-group">
							<label class="col-sm-4 control-label">Academic Year</label>
							<div class="col-sm-8">
								<select class="form-control" id="academicYearName" name="academicYearCode" required>
	                                <option value="">Select...</option>
	                                <c:forEach var="academicYear" items="${academicYearList}">	
										<option value="${academicYear.academicYearCode}">${academicYear.academicYearName}</option>
									</c:forEach>
                               	</select>
							</div>
						</div> 
                        <div class="form-group">
							<label class="col-sm-4 control-label">Standard</label>
							<div class="col-sm-8">
								<select class="form-control" id="standardName" name="standardCode" required>
                                	<option value="">Select...</option>
                                    <c:forEach var="standard" items="${standardList}">	
										<option value="${standard.standardCode}">${standard.standardName}</option>
									</c:forEach>
                                </select>
							</div>
						</div> 
                        <div class="form-group">
							<label class="col-sm-4 control-label">Section</label>
							<div class="col-sm-8">
								<select class="form-control" id="sectionName" name="sectionCode" required>
                                	<option value="">Select...</option>
                                </select>
							</div>
						</div>
                        <div class="form-group">
							<label class="col-sm-4 control-label">Exam</label>
							<div class="col-sm-8">
								<select class="form-control" id="examName" name="examName" required>
	                            	<option value="">Select...</option>
                       			</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">Student(s)</label>
							<div class="col-sm-8">
								<select class="form-control" id="studentName" name="roll" multiple = "multiple" required>
                               	</select>
							</div>
						</div>
					</div>
					<footer style="display: block;" class="panel-footer">
						<button  class="btn btn-primary">Submit </button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
					<div class="warningbox" id="warningbox">
						<span id="warningmsg"></span>	
					</div>
				</section>
        	</form>
		</div>
	</div>
	</c:otherwise>
	</c:choose>
</div>	
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/report/studentMarkSheetNewPattern.js"></script>
</body>
</html>