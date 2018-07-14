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
<title>Receive Session Fees</title>
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
	<h2>Receive Session Fees</h2>
</header>
<div class="content-padding">
	<div class="row">
		<c:if test="${status ne null}">
			<div class = "alert alert-success">
				<strong>${status}</strong>
			</div>
		</c:if>
		<c:choose>
			<c:when test="${aY eq null}">
				<div class="alert alert-danger">
					<strong>Academic Year is not defined yet. Please create academic year first.</strong>
				</div>
			</c:when>
				<c:otherwise>
					<form:form method="POST" action="updateStudentFees.html" >
						<div class="row">
							<div class="col-md-6 col-md-offset-3">
						  		<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Student Details</h2>										
									</header>
									<div style="display: block;" class="panel-body">
	                               		<div class="col-md-6">
		                                    <div class="form-group">
		                                        <label class="control-label">Exam Session:</label>
		                                        <input type="text" class="form-control" value="${aY.academicYearName}" readonly="readonly">
												<input type="hidden" name="academicSsession" id="academicSsession" value="${aY.academicYearCode}" />
		                                    </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Standard:</label>
	                                            <select id="standardName" name="standardName" class="form-control defaultselect">
	                                            	<option value="">Select..</option>
	                                            	<c:forEach var="standard" items="${standardList}">
														<option value="${standard.standardName}">${standard.standardName}</option>
													</c:forEach>
	                                            </select>
	                                        </div>
	                                        <div class="form-group">
	                                   			<label class="control-label">Section:</label>
	                                           	<select id="sectionName" name="sectionName" class="form-control defaultselect">
													<option value="">Select...</option>
												</select>
	                                   		</div>
	                           			</div>
	                   					<div class="col-md-6">
	                                       	<div class="form-group">
	                                          	<label class="control-label">Student Name:</label>
	                                          	<select id="studentName" name="studentName" class="form-control defaultselect">
													<option value="">Select...</option>
												</select>
	                                   		</div>
		                                    <div class="form-group">
		                                        <label class="control-label">Roll Number:</label>
		                                       	<input id="rollNumber" class="textfield form-control" type="text" name="rollNumber" readonly="readonly"/>
		                                    </div>
	                                   	</div>
									</div>
								</section>
							</div>
						</div>
						<div class="alert alert-danger" id="warningbox" style="display: none">
							<span id="warningmsg"></span>
						</div>
						<div class="row">
							<div class="col-md-12" id="feesDetailsTableDiv" style="display: none;">
		                       	<section class="panel">
		                            <header class="panel-heading">
		                                <div class="panel-actions">
		                                    <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
		                                </div>
		                                <h2 class="panel-title">Details</h2>
		                            </header>
		                            <div class="panel-body" id="tableDiv">
		                           		<input type="hidden" id="netTotAmount" name="netTotAmount">
		                                <table class="table table-bordered table-striped mb-none" id="studentFeeDetails">
		                                    <thead>
		                                    	<tr>
													<th><input type="checkbox" disabled="disabled"></th>
													<th>Fees Category Name</th>
													<th>Duration</th>
													<th>Total Payable Amount</th>
													<th>Paid Amount</th>
													<th>Previous payment date and comments</th>
													<th>Paying Amount</th>
													<th>Comment</th>
												</tr>
		                                    </thead>
		                                    <tbody id="studentFeeDetailsBody">
		                                    
		                                    </tbody>
		                                </table>
		                            </div>
		                            <footer style="display: none;" class="panel-footer" id="footerId">
										<button class="btn btn-primary" type="submit" id="submit" name="submit">Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
		                    	</section>
							</div>
						</div>
				</form:form>
			</c:otherwise>
		</c:choose>
	</div>
</div>

				
					
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/backoffice/receiveSessionFee.js"></script>
</body>
</html>