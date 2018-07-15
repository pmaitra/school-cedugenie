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
<title>Create Subject Group</title>
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
			<h2>Venue Allocation</h2>
		</header>
		<div class="content-padding">
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
	

					<!-- start: page -->
                    <div class="row">
						<div class="col-md-6 col-md-offset-3">
							 <form action="insertVenueAllocation.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Venue Allocation</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="col-sm-12">
											<div class="form-group">
												<label class="control-label"><b>Venue Name: <span class="required" aria-required="true">*</span></b></label>
												<select class="form-control" name="venueCode" id="venueCode" required>
                                                    <option value="">Select...</option>
                                                    <c:forEach var="venue" items="${venueList}" varStatus="i">
														<option value="${venue.venueCode}">${venue.venueName}</option>
													</c:forEach>
                                                </select>
											
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
		                                                <input class="form-control" placeholder="__/__/____" data-input-mask="99/99/9999" data-plugin-masked-input="" name="startDate" id="startDate" required/>
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
			                                                <input class="form-control" placeholder="__/__/____" data-input-mask="99/99/9999" data-plugin-masked-input="" name="endDate" id="endDate" required />
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
	                                        
											
											<!-- <div class="form-group"> -->
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
		                                        <label class="control-label"><b>Description<span class="required" aria-required="true">*</span></b></label>
		                                        <textarea class="form-control" rows="4" data-plugin-maxlength="" maxlength="140" name="zoneDesc" id="zoneDesc" required></textarea>
		                                    </div>
										</div>
										<footer style="display: block;" class="panel-footer">
											<button class="btn btn-primary" type="submit"  id ="submit" >Submit </button>
											<button type="reset" class="btn btn-default">Reset</button>
										</footer>
									</section>
								</form>
						</div>
					</div>
					<div class="row">   
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
																<%-- 	<input type="hidden" id="venueCode${i.index}" name="venueCode${i.index}" value="${venue.venueCode}"> --%>
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
	                     </div>
					</div>
						
				
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>	
<script src="/cedugenie/js/venue/venueAllocation.js"></script>	
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