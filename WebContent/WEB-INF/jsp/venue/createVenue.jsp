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
<title>Venue Details</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
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
				<header class="page-header">	<!-- Added by Saif 28/03/2018 -->
						<h2>Add Venue</h2>
				</header>
				<div class= "content-padding">
						<div class="row">
						<div class="col-md-12">
						  <form action="createVenue.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Venue</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="col-sm-4">
											<div class="form-group">
												<label class="control-label"><b>Venue Name: <span class="required" aria-required="true">*</span></b></label>
												<input name="venueName" id="venueName" class="form-control" pattern="^[a-zA-Z ]+$" title="Please Enter Proper Venue Name" type="text" required>
											</div>
											<div class="form-group">
											<label class="control-label"><b>Description:</b></label>
												<input name="venueDesc" id="venueDesc" class="form-control" pattern="^[a-zA-Z '.,-/]+$" title="Please Enter Proper Description" type="text" placeholder="" >
											</div>
	                                    	<div class="form-group">
												<label class="control-label" id = "noOfSeatsLabel"><b>No of Seats : <span class="required" aria-required="true">*</span></b></label>
													 <input name="availableSeat" id="availableSeat" class="form-control" type="text" placeholder="0"  required>
											</div>
											<!-- <div class="form-group">
												<label class="control-label"  id = "hostelTypeLabel"><b>Hostel Type : <span class="required" aria-required="true">*</span></b></label>
													<select class="form-control" name="hostelType" id="hostelType" >
	                                                    
	                                                </select>
											</div> -->
										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<label class="control-label"><b>Location : <span class="required" aria-required="true">*</span></b></label>
													<select class="form-control" name="location" id="location" required>
	                                                    <option value="">Select...</option>
	                                                    <c:forEach var="location" items="${locationList}" varStatus="i">
															<option value="${location.locationCode}">${location.locationName}</option>
														</c:forEach>
	                                                </select>
											</div>
											<div class="form-group">
												<label class="control-label"><b>Parent Venue :</b></label>
													<select class="form-control" name="venueCode" id="venueCode" >
	                                                    <option >Select...</option>
	                                                </select>
											</div>

											<div class="form-group">
												<label class="control-label"  id = ""><b>Room No : <span class="required" aria-required="true">*</span></b></label>
													 <input name="roomNo" id="roomNo" class="form-control" type="text" placeholder="" required>
											</div>

										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<label class="control-label"><b>Venue Type : <span class="required" aria-required="true">*</span></b></label>
													<select class="form-control" name="venueTypeCode" id="venueTypeCode" required>
	                                                    <option value="">Select...</option>
	                                                    <c:forEach var="venue" items="${venueTypeList}" varStatus="i">
															<option value="${venue.venueTypeCode}">${venue.venueTypeName}</option>
														</c:forEach>
	                                                </select>
											</div>
											<div class="form-group">
												<label class="control-label" ><b>Building:</b></label>
													<input name="building" id="building" class="form-control" pattern="^[a-zA-Z0-9 ]+$" title="Please Enter Proper Building No" type="text" placeholder="">
											</div>
											<div class="form-group">
												<label class="control-label" id = "floorLabel"><b>Floor : <span class="required" aria-required="true">*</span></b></label>
												<input name="floor" id="floor" class="form-control" pattern="^[a-zA-Z0-9 ]+$" title="Please Enter Proper Floor No" type="text" placeholder="" required>
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
					<form name="editVenue" id="editVenue" action="editVenue.html" method="post">
                         <div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Venue List</h2>
	                                </header>
	                                <input type="hidden" name="saveId" id="saveId">
	                                <input type="hidden" name="newvenuename" id="newvenuename">
	                                <input type="hidden" name="newvenuestatus" id="newvenuestatus">
	                                	<div class="panel-body">
		                                	<c:forEach var="venue" items="${venueList}" varStatus="i">
												<input type="hidden" name="oldVenueNames" value="${venue.venueName}">
											</c:forEach>
			                                   <table id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
			                                        <thead>
			                                            <tr>
			                                            	<th>Venue Name</th>
			                                                <th>Building</th>
															<th>Floor</th>
															<th>Room No</th>
															<th>Location</th>
															<th>Total Seats</th>
															<th>Status</th>
															<th>Actions</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        
			                                        	<c:forEach var="venue" items="${venueList}" varStatus="i">
			                                        		
																<tr >
																	<td>
																		<input type="hidden" id="venueCode${i.index}" name="venueCode${i.index}" value="${venue.venueCode}">
																		<input type="hidden" id="venueName${i.index}" name="venueName${i.index}" class="form-control" value="${venue.venueName}"/>
																		${venue.venueName}
																	</td>
																	<td>
																		<input type="hidden" id="building${i.index}" name="building${i.index}" class="form-control" value="${venue.building}"/>
																		${venue.building}
																	</td>
																	<td>
																		<input type="hidden" id="floor${i.index}" name="floor${i.index}" class="form-control" value="${venue.floor}"/>
																		${venue.floor}
																	</td>
																	<td>
																		<input type="hidden" id="roomNo${i.index}" name="roomNo${i.index}" class="form-control" value="${venue.roomNo}"/>
																		${venue.roomNo}
																	</td>
																	<td>
																		<input type="hidden" id="location${i.index}" name="location${i.index}" class="form-control" value="${venue.location}"/>
																		${venue.location}
																	</td>
																	<td>
																		<input type="hidden" id="availableSeat${i.index}" name="availableSeat${i.index}" class="form-control" value="${venue.availableSeat}"/>
																		${venue.availableSeat}
																	</td>
																	<td>
																		<input type="hidden" id="venueStatus${i.index}" name="venueStatus${i.index}" value="${venue.availability}">
																		<c:if test="${venue.availability eq 't'}">
																			Enable
																		</c:if>
																		<c:if test="${venue.availability eq 'f'}">
																			Disable
																		</c:if>
																		
																	</td>
																	<td class="actions">
																		<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" onclick="updateVenue('${i.index}')"><i class="fa fa-pencil"></i></a>
																		<a class="on-default remove-row"  href="inactiveVenue.html?venueCode=${venue.venueCode}" ><i class="fa fa-trash-o"></i></a>
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
											<table class="table table-bordered table-striped mb-none" id="updateVenueDetails">
												<thead>
													<tr>
														<th>Venue Name</th>
		                                                <th>Status</th>
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
													<button id="updateVenue" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
                        </form>                                                  
                     </div> 
				</div>
				
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/icam/js/venue/createVenue.editable.js"></script>
<script src="/icam/js/venue/createVenue.js"></script> 
<script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script>
</body>
</html>
