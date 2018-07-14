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
       }
</style>

</head>
<body>
	<header class="page-header">
		<h2>Admission Fees Submition</h2>
	</header>
	<div class="content-padding">
	<div class="row">	
	<form:form method="POST" id="" name=""  action="submitFeesForStudent.html" enctype="multipart/form-data">					
		<div class="col-md-6 col-md-offset-3">
		  	<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Candidate For Fees Submission</h2>										
					</header>
					<div style="display: block;" class="panel-body">
						<div class = "col-md-6">
							<div class="form-group">
	                            <label class="control-label">Academic Year</label>
	                            <input type="text" class="form-control" readonly id="year" name="year" value="${selectedCandidatesForFeesSubmission.admissionYear}">
	                        </div>
	                        <div class="form-group">
	                            <label class="control-label">Standard</label>
	                            <input type="text" class="form-control" readonly id="klass" name="klass" value="${selectedCandidatesForFeesSubmission.courseClass}">
	                        </div>
	                        <div class="form-group">
	                            <label class="control-label">Course Name</label>
	                            <input type="text" class="form-control" readonly id="courseName" name="courseName" value="${selectedCandidatesForFeesSubmission.courseName}">
	                        </div>
	                        <div class="form-group">
	                            <label class="control-label">Drive Name</label>
	                            <input type="text" class="form-control" readonly id="driveName" name="driveName" value="${selectedCandidatesForFeesSubmission.formName}">
	                        </div>
						</div>
						<div class = "col-md-6">
							<div class="form-group">
	                            <label class="control-label">Candidate Form ID</label>
	                            <select class="form-control" name='formId' id="formId" tabindex="1" required>
	                                <option value="">Select...</option>
	                                <c:if test="${selectedCandidatesForFeesSubmission.resourceList!=null}">
										<c:forEach var="formId" items="${selectedCandidatesForFeesSubmission.resourceList}">
											<option value="${formId.userId}">${formId.userId}</option>
										</c:forEach>
									</c:if>
	                            </select>
	                        </div>
	                        <div class="form-group">
	                            <label class="control-label">Candidate Name</label>
	                            <input readonly="readonly" type = "text" id="candidateName" name="candidateName" class="form-control" />
								<input readonly="readonly" type="hidden" name="amountExceptHostel" id="amountExceptHostel"/>
	                        </div>
	                        <div class="form-group">
	                            <label class="control-label">Gender</label>
	                            <div class="form-group" style="margin-top: 5px;">
	                            <label class="radio-inline radio-primary"> 
	                                <input type="radio" name="gender" value="M" id="mGender" disabled="disabled"/> Male 
	                            </label>
	                            <label class="radio-inline radio-primary"> 
	                                <input type="radio" name="gender" value="F" id="fGender" disabled="disabled"/> Female 
	                            </label>
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="control-label">Category</label>
	                            <input type="text" class="form-control" id="category" name="category" readonly>
	                        </div>
						</div> 
					</div>			
	          	</section>
			</div>
		<div>
		<table id="table2" class="table table-bordered table-striped mb-none">
				<tr>
<!-- 				<th>Type</th><th>Category</th><th>Amount</th> -->
				</tr>		
			</table>
		</div>
		<input type="hidden" id="totalSubmittedValue" name="totalSubmittedValue" value=""/>
	      	</form:form>
	</div>
	
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
	</div>	
	</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/backoffice/selectedCandidatesForFeesSubmission.js"></script>

</body>
</html>