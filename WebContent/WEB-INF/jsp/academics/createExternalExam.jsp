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
<title>Configure Exam</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
       .datepicker-dropdown{
        display: none !important;
       }
</style>




</head>
<body>

	
		<header class="page-header">
			<h2>Configure Exam Seating</h2>
		</header>
		<c:if test="${updateStatus eq 'success'}">
				<div class="alert alert-success">
					<strong>${msg}</strong>
				</div>
			</c:if>
			
			<c:if test="${updateStatus eq 'fail'}">
				<div class="alert alert-danger">
					<strong>${msg}</strong>
				</div>
			</c:if>
			<c:if test="${updateStatus eq 'ALLOCATED'}">
				<div class="alert alert-danger">
					<strong>${msg}</strong>
				</div>
			</c:if>
		<div class="content-padding">
			
	

					<!-- start: page -->
                    <div class="row">
						<div class="col-md-8 col-md-offset-2">
							 <form action="createExternalExam.html" method="POST">
							 <input type="hidden" name="venueIndex" id="venueIndex">
							<input type="hidden" name="programIndex" id="programIndex">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Configure Exam Seating</h2>										
									</header>
									<div style="display: block;" class="panel-body">
									<div class = "row">
										 <div class="col-md-12"  >
											<div class="form-group">
												<div class="col-sm-6">	
													<label class="control-label"><b>Examination Name: <span class="required" aria-required="true">*</span></b></label>
													
													<input type = "text" id = "examName" name = "examName" class = "form-control" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" title = "Alphabetic charecters And space only" value = "" required>											
												</div>
												<div class="col-sm-6">	
														<label class="control-label"><b>Algorithm: <span class="required" aria-required="true">*</span></b></label>
														<select class="form-control" name="algorithm" id="algorithm" required>
		                                                    <option value="">Select...</option>
		                                                    <c:forEach var="algorithm" items="${algorithmList}" varStatus="i">
																<option value="${algorithm.algorithmName}">${algorithm.algorithmName}</option>
															</c:forEach>
		                                                </select>
												
													</div>
												
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-6">
		                                        <label class=control-label"><b> From Date :<span class="required" aria-required="true">*</span></b></label>
		                                        <label class="control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                <input class="form-control" placeholder="__/__/____" data-input-mask="99/99/9999" data-plugin-masked-input="" name="examStartDate" id="examStartDate" required/>
		                                            </div>
		                                        </label>
		                                        </div>
	                                    	<!-- </div>
	                                
											<div class="form-group"> -->
												<div class="col-sm-6">
			                                        <label class="control-label"><b>To Date :<span class="required" aria-required="true">*</span></b></label>
			                                        <label class="control-label">
			                                            <div class="input-group">
			                                                <span class="input-group-addon">
			                                                    <i class="fa fa-calendar"></i>
			                                                </span>
			                                                <input class="form-control" placeholder="__/__/____" data-input-mask="99/99/9999" data-plugin-masked-input="" name="examEndDate" id="examEndDate" required />
			                                            </div>
			                                        </label>
			                                     </div>
	                                    	</div>
	                                    	
	                                    	
											<div class="form-group">
												<div class="col-sm-6">
		                                            <label class="control-label"><b>Start Time :<span class="required" aria-required="true">*</span></b></label>
		                                            
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-clock-o"></i>
		                                                </span>
		                                                <input type="text" class="form-control" id="startTime" name="startTime" data-plugin-timepicker="">
		                                            </div>
		                                        </div>
	                                        
											
											
												<div class="col-sm-6">
		                                            <label class="control-label"><b>End Time:<span class="required" aria-required="true">*</span></b></label>
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-clock-o"></i>
		                                                </span>
		                                                <input type="text" class="form-control" id="endTime" name="endTime" data-plugin-timepicker="" >
		                                            </div>
		                                        </div>
	                                        </div>
	                                       
	                                    
											<div class="form-group">
												<div class="col-sm-6"> 	
			                                        <label class="control-label"><b>No Of Students<span class="required" aria-required="true">*</span></b></label>
			                                        <input type = "text" id = "serialId" name = serialId class = "form-control" value = "0" pattern="^[1-9]\d*$" required readonly>
		                                    	</div>
		                                    </div> 
		                                   </div> 
		                                    <div class="col-md-12" id = "program">
												<section class="panel">
													
													<div class="panel-body">
														<c:forEach var="course" items="${courseList}">
															<input type = "hidden" name = "programCode" value="${course.courseClass}"/>
															<input type = "hidden" name = "programName" value="${course.courseClass}"/>
														</c:forEach>
														<table class="table table-bordered table-striped mb-none" id = "programTable">
															<thead>
																<tr>
																	<th>Program Name</th>
																	<th>Capacity</th>
																	<th>Action</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>
																		<select class="form-control" name="programName0" id="programName0" required>
																			<option value="" >Select...</option>
																			<c:forEach var="course" items="${courseList}">
																				<option value="${course.courseClass}">${course.courseClass}</option>
																			</c:forEach>
																		 </select>
																	</td>
																	<td>
																		 <input type="text" class="form-control" name="capacity0" id="capacity0"  required readonly/>
																	</td>
																	
																	<td>
																		<a class="mb-xs mt-xs mr-xs  btn btn-info" id="addrow" href="javascript:addrows()">Add</a>
																	</td>
																</tr> 
															</tbody>
														</table>
													</div>
												</section>
											</div>
											  <div class="col-md-12" id = "program">
												<section class="panel">
													
													<div class="panel-body">
														<c:forEach var="venue" items="${venueList}">
															<input type = "hidden" name = "venueCode" value="${venue.venueCode}"/>
															<input type = "hidden" name = "venueName" value="${venue.venueName}"/>
														</c:forEach>
														<table class="table table-bordered table-striped mb-none" id = "venueTable">
															<thead>
																<tr>
																	<th>Venue Name</th>
																	<th>Venue Capacity</th>
																	<th>Row Number</th>
																	<th>Column Number</th>
																	<th>Action</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>
																		<select class="form-control col-sm-4" name="venueCode0" id="venueCode0" required>
						                                                    <option value="">Select...</option>
						                                                    <c:forEach var="venue" items="${venueList}" varStatus="i">
																				<option value="${venue.venueCode}">${venue.venueName}</option>
																			</c:forEach>
						                                                </select>
																	</td>
																	<td>
																		 <input type="text" class="form-control" name="availableSeat0" id="availableSeat0"  required readonly/>
																	</td>
																	<td>
																		<input type = "text" id = "rowNumber0" name = "rowNumber0" class = "form-control" value = "" pattern="^[1-9]\d*$" required>
																	</td>
																	<td>
																		<input type = "text" id = "columnNumber0" name = "columnNumber0" class = "form-control" value = "" pattern="^[1-9]\d*$" required>
																	</td>
																	<td>
																		<a class="mb-xs mt-xs mr-xs  btn btn-info" id="addrow" href="javascript:addRowsForVenue()">Add</a>
																	</td>
																</tr> 
															</tbody>
														</table>
													</div>
												</section>
											</div>
										</div>
										<footer style="display: block;" class="panel-footer">
											<button class="btn btn-primary" type="submit"  id ="submit"  onclick = "setIndex()">Submit </button>
											<button type="reset" class="btn btn-default">Reset</button>
										</footer>
									</section>
								</form>
						</div>
					</div>
					
				<div class="alert alert-danger" id = "errorMsg" style = "display:none">
					<strong>Expected Capacity does not Match with actual capacity</strong>
				</div>
			
					<%-- <div class="row">   
					<form name="" id="" action="" method="post">
                         <div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Venue List</h2>
	                                </header>
	                                <input type="hidden" name="saveId" id="saveId">
	                                	<div class="panel-body">
		                                	<c:forEach var="venue" items="${venueList}" varStatus="i">
												<input type="hidden" name="oldVenueNames" value="${venue.venueName}">
											</c:forEach>
			                                   <table class="table table-bordered table-striped mb-none" id="datatable-editable">
			                                        <thead>
			                                            <tr>
			                                            	<th>Venue Name</th>
			                                                <th>Start Date</th>
															<th>End Date</th>
															<th>Start Time</th>
															<th>End Time</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        
			                                        	<c:forEach var="venue" items="${allocatedVenueList}" varStatus="i">
			                                        		<tr >
																<td>
																	<input type="hidden" id="venueCode${i.index}" name="venueCode${i.index}" value="${venue.venueCode}">
																	<input type="text" id="venueName${i.index}" name="venueName${i.index}" class="form-control" value="${venue.venueName}" readonly />
																</td>
																<td>
																	<input type="text" id="startDate${i.index}" name="startDate${i.index}" class="form-control" value="${venue.startDate}" readonly />
																</td>
																<td>
																	<input type="text" id="endDate${i.index}" name="endDate${i.index}" class="form-control" value="${venue.endDate}" readonly />
																</td>
																<td>
																	<input type="text" id="startTime${i.index}" name="startTime${i.index}" class="form-control" value="${venue.startTime}" readonly />
																</td>
																<td>
																	<input type="text" id="endTime${i.index}" name="endTime${i.index}" class="form-control" value="${venue.endTime}" readonly />
																</td>
															</tr>
														</c:forEach>
			                                        </tbody>
			                                    </table>
			                                </div>
		                            </section>
								</div>
	                        </form>                                                  
	                     </div> --%>
					</div>
						
				
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>	
<script src="/cedugenie/js/academics/createExternalExam.js"></script>	
<script src="/cedugenie/assets/custom-caleder/jquery-1.9.1.js" type="text/javascript"></script>
<script src="/cedugenie/assets/custom-caleder/jquery-ui.js" type="text/javascript"></script>
<link href="/cedugenie/assets/custom-caleder/jquery-ui.css" type="text/css" rel="stylesheet">
  <script type="text/javascript">
  $(function(){
	    $("#startDate").datepicker({
	        minDate: 0,
	        maxDate: '+1Y+6M',
			dateFormat: 'dd/mm/yy',
	        onSelect: function (dateStr) {
	            var min = $(this).datepicker('getDate'); // Get selected date
	            $("#endDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
	        }
	    });

	    $("#endDate").datepicker({
	        minDate: '0',
	        maxDate: '+1Y+6M',
			dateFormat: 'dd/mm/yy',
	        onSelect: function (dateStr) {
	            var max = $(this).datepicker('getDate'); // Get selected date
	            $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M'); // Set other max, default to +18 months
	        }
	    });
	    }); 

  </script>			
</body>
</html>