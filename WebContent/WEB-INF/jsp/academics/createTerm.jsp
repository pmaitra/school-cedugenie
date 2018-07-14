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
<title>Create Term</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
       
       .datepicker-dropdown{
        display: none !important;
       }
       #ui-datepicker-div{
       	z-index:99999 !important;
       }
</style>
</head>
<body>
				<header class="page-header">
					<h2>Create Term</h2>
				</header>
				<div class="content-padding">
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
					<c:if test="${insertUpdateStatus eq 'duplicate'}">
						<div class="alert alert-danger">
							<strong>${msg}</strong>
						</div>
					</c:if>
					<div class="alert alert-danger" id="javascriptmsg1" style="display: none">
						<span> </span>	
					</div>
                    <div class="row">
						<div class="col-md-6 col-md-offset-3" >
						  <form action="createTerm.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Create Term</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="col-sm-5 control-label">Course Name: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<select class="form-control" id="course" name="courseName" required>
													<option value="">Select</option>
													<c:forEach var="program" items="${programList}" varStatus="i">
														<option value="${program.courseCode}">${program.courseName}</option>
													</c:forEach>
												</select>
											</div>
										</div>	
										<div class="form-group">
											<label class="col-sm-5 control-label">Term:  <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<input id="termName" name="termName" class="form-control" type="text" pattern="[a-zA-Z0-9\s]+" required title = "Charecter And atleast One Number Only">
											</div>
										</div>
										 <div class="form-group">
											<label class="col-sm-5 control-label">Session: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<select class="form-control" id="academicYear" name="academicYear" required>
													<option value="">Select...</option>
													<option value="${academicYear}">${academicYear}</option>
												</select>
											</div>
										</div> 
										<!-- <div class="form-group">
		                                        <label class=control-label"><b>Start Month :<span class="required" aria-required="true">*</span></b></label>
		                                        <label class="control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                <input type="text" class="form-control" name="startMonth" id="startMonth"  required  /> 
		                                           	</div>
		                                        </label>
	                                    	</div>
	                                    	<div class="form-group">
		                                        <label class=control-label"><b>End Month :<span class="required" aria-required="true">*</span></b></label>
		                                        <label class="control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                <input type="text" class="form-control" name="interviewDate" id="interviewDate"  required  disabled/> 
		                                           	</div>
		                                        </label>
	                                    	</div> -->
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" onclick="return termValidation()">Submit </button>
										<button type="reset" class="btn btn-default" onclick="removeMsg()">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
					</div>
					<div class="row">	
					<%-- <c:choose>
						<c:when test="${termList eq null || empty termList}">
							No Term Found
						</c:when>
						<c:otherwise> --%>
					
							<form name="editTerm" id="editTerm" action="editTerm.html" method="post">
							<div class="col-md-12">
									
									<input type="hidden" name="saveId" id="saveId">
									<input type="hidden" name="getTerm" id="getTerm">
									<input type="hidden" name="getProgram" id="getProgram">
									<!-- <input type="hidden" name="saveId" id="saveId"> -->
		                            <section class="panel">
									<header class="panel-heading">
										<h2 class="panel-title">Existing Terms</h2>
									</header>
									<div class="panel-body">
										<%-- <c:forEach var="terms" items="${termList}" varStatus="i">
											<input type="hidden" name="termCode" value="${courseType.courseTypeName}">
										</c:forEach> --%>
										<table  id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
											<thead>
												<tr>
		                                            
													<th>Course Name</th>
													<th>Term</th>
													<th>Session</th>
													<th>Actions</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach var="terms" items="${termList}" varStatus="i">
												<tr class="gradeX">
													<td>
														<input type="hidden" name="termId${i.index}" value="${terms.termObjectId}">
														<input type="hidden" name="termCode${i.index}" value="${terms.termCode}">
														<input type="hidden" id="course.courseCode${i.index}" name="course.courseCode${i.index}" class="form-control" value="${terms.course.courseCode}" readonly >
														${terms.course.courseName}
													</td>
													<td>
														${terms.termName}
													</td>
													<td>
														${terms.academicYear}
													</td>
													<td class="actions">
														<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic " onclick = "showTermDetails('${terms.course.courseName}','${terms.termName}','${i.index}')"><i class="fa fa-pencil"></i></a>
														<a href="inactiveTerm.html?termCode=${terms.termCode}" id = "delete${i.index}"><i class="fa fa-trash-o"></i></a>
													</td>
												</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
									</section>
									
								</div>
								<!-- popup Window code -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
									<section class="panel">
										<header class="panel-heading">
											<!-- <h2 class="panel-title">Approver Group Name - PO_Approver</h2> -->
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id = "programTermDetails">
												<thead>
													<tr>
														<th>Course Name</th>
														<th>Term</th>
													</tr>
												</thead>
												<tbody>
								
												</tbody>
											</table>
											<div class="alert alert-warning" id="warningmsg" style="display: none">
												<span></span>	
											</div>
										</div>
										
										<footer class="panel-footer">
											<div class="row">
												<div class="col-md-12 text-right">
													<button id="updateTerms" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
							</form>
							
						<%-- </c:otherwise>
					</c:choose>  --%>
					</div>
				</div>	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/academics/createTerm.js"></script>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
</body>
</html>