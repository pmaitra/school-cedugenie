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
<title>Create Course Details</title>
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
<link href="/cedugenie/assets/custom-caleder/jquery-ui.css" type="text/css" rel="stylesheet"> 
</head>
<body>
	<header class="page-header">
			<h2>Enter Standard Details</h2>
		</header>
		<div class="content-padding">
		<c:if test="${insertUpdateStatus eq 'success'}">
			<div class="alert alert-success"  >
				<strong>${msg}</strong>	
			</div>
		</c:if>
		<c:if test="${insertUpdateStatus eq 'failed'}">
			<div class="alert alert-danger" >
				<strong>${msg}</strong>	
			</div>
		</c:if>
		
					
                    <div class="row">
						<div class="col-md-12">
						  <form action="createCourse.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Enter Standard Details</h2>										
									</header>
									<div style="display: block;" class="panel-body">
									<div class = "row">
										<div class="col-sm-4">
											<div class="form-group">
												<label class="control-label"><b>Standard Name: <span class="required" aria-required="true">*</span></b></label>
												<select class="form-control" name="courseName" id="courseName" required >
                                                    <option value="">Select...</option>
                                                    <c:forEach var="standard" items="${standardList}" varStatus="i">
														<option value="${standard.standardCode}">${standard.standardName}</option>
													</c:forEach>
                                                </select> 
											</div>
											 <div class="form-group">
												<label class="control-label"><b>Total Seat: <span class="required" aria-required="true">*</span></b></label>
												<input name="noOfOpenings" id="noOfOpenings" class="form-control" type="text" placeholder="" pattern="^[1-9]\d*$" required onblur = "setNoOfOpenings()" readonly>
											</div> 
	                                    	<div class="form-group">
                                            	<label class="control-label"><b>Admission Mode</b></label>
                                   					<div class="form-group" style="margin-top: 5px;">
                                               			<label class="radio-inline radio-primary"> 
	                                                 		  <input type="radio" name="searchStatus" id="searchStatus" value="OFFLINE" checked readonly="readonly"> OFFLINE 
                                               			</label>
                                               			<label class="radio-inline radio-primary"> 
	                                                  		 <input type="radio" name="searchStatus" id="searchStatus" value="ONLINE" disabled> ONLINE 
                                               			</label>
                               			 	 		</div>
                               			 	 </div>
										</div>
										
										<div class="col-sm-4">
											<div class="form-group">
												<label class="control-label"><b>Course Type: <span class="required" aria-required="true">*</span></b></label>
													<select class="form-control" name="courseType" id="courseType" required>
	                                                    <option value="">Select...</option>
	                                                    <c:forEach var="courseType" items="${courseTypeList}" varStatus="i">
															<option value="${courseType.courseTypeName}">${courseType.courseTypeName}</option>
														</c:forEach>
	                                                </select>
											</div>
											<div class="form-group">
												<label class="control-label"><b>Course Acronym: <span class="required" aria-required="true">*</span></b></label>
												<input name="courseAcronym" id="courseAcronym" class="form-control" type="text" placeholder="" pattern="^[A-Za-z]+$" required>
											</div>
											<!-- <div class="form-group">
												<label class="control-label"><b>Openings :<span class="required" aria-required="true">*</span></b></label>
												<input name="noOfOpenings" id="noOfOpenings" class="form-control" type="text" placeholder="" pattern="^[1-9]\d*$" required readonly>
											</div> -->
	                                    	
										</div>
										<div class="col-sm-4">
											<div class="form-group">
	                                               <label class="control-label"><b>Admission Available status</b></label>
	                                               		<div class="form-group" style="margin-top: 5px;">
	                                               			<label class="radio-inline radio-primary"> 
	                                                 		  <input type="radio" name="status" id="status" value="ACTIVE" onchange="activeInactiveAdmissionAvailableFromToDate(this.value);" checked> Yes 
	                                               			</label>
	                                               			<label class="radio-inline radio-primary"> 
	                                                  		 <input type="radio" name="status" id="status" value="INACTIVE"  onchange="activeInactiveAdmissionAvailableFromToDate(this.value);"> No 
	                                               			</label>
	                                       			  </div>
                               			 	 </div>
                               			 	 <div class="form-group">
												<label class="control-label"><b>Duration (Month): <span class="required" aria-required="true">*</span></b></label>
													<input name="courseDuration" id="courseDuration" class="form-control" type="text" placeholder="" pattern="^[1-9]\d*$" required>
											</div>
                               			 	
	                                    	
											
										</div>
									</div>
									
									<div class = "row">
										<div id = sectionDiv class="col-md-4 col-md-offset-4" style = "display:none">
											<h3 class="well well-sm">Capacity In Section</h3>
											<table class="table table-bordered table-striped mb-none">
													<thead>
														<tr>
															<th>Section</th>
															<th>Available Seats<span class="required" aria-required="true">*</span></th>
														</tr>
													</thead>
													<tbody id = "sectionbody">
													</tbody>
											</table>
										</div>
									</div>
								
								</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
					</div>
					<div class="row">   
						<form name="editCourse" id="editCourse" action="editCourse.html" method="post">
						<input type="hidden" name="saveId" id="saveId">
						<input type="hidden" name="newAdmissionEndDate" id="newAdmissionEndDate">
						<input type="hidden" name="newCourseStartDate" id="newCourseStartDate">
						<input type="hidden" name="newOpenings" id="newOpenings">
						<input type="hidden" name="newDuration" id="newDuration">
                            <div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Course List</h2>
	                                </header>
	                                	<div class="panel-body">
		                                	<c:forEach var="course" items="${courseList}" varStatus="i">
												<input type="hidden" name="oldCourseNames" value="${course.courseName}">
											</c:forEach>
			                                    <table id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
			                                        <thead>
			                                            <tr>
			                                            	<th>Course</th>
															<!-- <th>Status</th> -->
															<!-- <th>Admission Available From Date</th>
															<th>Admission Available To Date</th>
															<th>Course Start Date</th> -->
															<th>Openings</th>
															<th>Duration(In Months)</th>
															<th>Credit</th>
															<th>Actions</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        
			                                        	<c:forEach var="course" items="${courseList}" varStatus="i">
			                                        		
																	<tr >
																		<td>
																			<c:out value="${course.courseName}"></c:out>
																			<input type="hidden" id="courseCode${i.index}" name="courseCode${i.index}" class="form-control" value="${course.courseCode}" readonly />
																		</td>
																		<%-- <td><c:out value="${course.searchStatus}"></c:out></td> --%>
																		<%-- <td>
																			<c:out value="${course.admissionDriveStartDate}"></c:out>
																			<input type="hidden" id="admissionDriveStartDate${i.index}" name="admissionDriveStartDate${i.index}" class="form-control" value="${course.admissionDriveStartDate}" readonly />
																		</td>
																		<td>
																			${course.admissionDriveExpectedEndDate}
																		</td>
																		<td>
																			${course.courseStartDate}
																		</td> --%>
																		<td>
																			${course.noOfOpenings}
																		</td>
																		<td>
																			${course.courseDuration}
																		</td>
																		<td>
																			${course.admissionFormId}
																		</td>
																		<td class="actions">
																			<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" onclick="updateAdmission('${i.index}','${course.courseName}','${course.noOfOpenings}','${course.courseDuration}')"><i class="fa fa-pencil"></i></a>
																		</td>
																	</tr>
																	
														</c:forEach>
			                                        
			                                        </tbody>
			                                    </table>
			                                </div>
	                            </section>
							</div>
							
							<!-- popup Window code -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide" style="max-width: 800px">
									<section class="panel">
										<header class="panel-heading">
											<!-- <h2 class="panel-title">Approver Group Name - PO_Approver</h2> -->
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id="updateAdmissionDetails">
												<thead>
													<tr>
														<th>Program</th>
														<!-- <th>Admission Available To Date</th>
														<th>Course Start Date</th> -->
														<th>Openings</th> 
														<th>Duration(In Months)</th>
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
													<button id="updateAdmission" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
                           </form>                                               
                     </div>   
				</div>	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/academics/createCourse.editable.js"></script>
<script src="/cedugenie/js/academics/createCourse.js"></script>
<script src="/cedugenie/assets/custom-caleder/jquery-ui.js" type="text/javascript"></script>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
</body>
</html>

