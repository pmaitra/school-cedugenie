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
<title>Upload Result</title>
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
	<h2>Upload Result</h2>
</header>
<div class="content-padding">
	<div class= "row">
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
			<c:when test="${standardList eq null || empty standardList}">
				<div class="alert alert-danger">
					<strong>No Standard found for upload result</strong>
				</div>
			</c:when>
			<c:otherwise>
				<form name="subjectForm" id="subjectForm" method="POST" action="editIntoStudentResultNew.html" novalidate>
					<div class="row">
						<div class="col-md-8 col-md-offset-2">
							<section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
									</div>
									<h2 class="panel-title">Details</h2>										
								</header>
								<div style="display: block;" class="panel-body" id="subjectDetailTable">                                       
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
	                                            <label class="control-label">Standard</label>
	                                            <select class="form-control" id="standard" name="standard" required>
	                                            	<option value="">Select..</option>
	                                                <c:forEach var="standard" items="${standardList}" varStatus="i">
														<option value="${standard.standardCode}">${standard.standardName}</option>
													</c:forEach>
	                                            </select>
	                                        </div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
	                                            <label class="control-label">Section</label>
	                                            <select class="form-control" id="section" name="section" disabled required>
	                                            	<option value="">Select..</option>
	                                            </select>
	                                        </div>
										</div>
									 	<div class="col-md-6" id="termhead">
											<div class="form-group">
	                                            <label class="control-label">Term</label>
	                                            <select class="form-control" id="term" name="term" disabled required>
	                                            	<option value="">Select..</option>
	                                            </select>
	                                        </div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
	                                            <label class="control-label">Subject</label>
	                                            <select class="form-control" id="subject" name="subject" disabled required>
	                                            	<option value="">Select..</option>
	                                            </select>
	                                        </div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
	                                            <label class="control-label">Exam</label>
	                                            <select class="form-control" id="exam" name="exam" disabled required>
	                                            	<option value="">Select..</option>
	                                            </select>
	                                        </div>
										</div>
										<input type="hidden" name="insertUpdate" id="insertUpdate" value="INSERT">
									</div>
								</div>
								<div class="alert alert-warning" id="infomsgbox1" style="display:none">
									<span id="infomsg1"></span>
								</div>
								<div class="alert alert-warning" id="warningbox" style="display:none">
									<span id="warningmsg"></span>	
								</div>
							</section>
						</div>
						<div class="col-md-12" id = "uploadResultNewDiv" style = "display:none">
                            <section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									</div>
									<h2 class="panel-title">Set Exam Marks</h2>
								</header>
								<div class="panel-body">
									<table class="table table-bordered table-striped mb-none" id="studentResult">
										<thead id="tableHead">
										</thead>
										<tbody id="tableBody">
										</tbody>
									</table>
								</div>
								<input type="hidden" id="loggedInUser" value="${loggedInUser}">
								<input type="hidden" name="insertUpdate" id="insertUpdate" value="INSERT">
	                            <footer id="btnDiv" style="display: none;" class="panel-footer">
	                                <button class="btn btn-primary" type="submit" onclick="return saveStudentResult();">Submit </button>
	                                <button type="reset" class="btn btn-default">Reset</button>
	                            </footer>
                            </section>
                        </div>
					</div>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/academics/createResultNew.js"></script>
</body>
</html>