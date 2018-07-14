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
<title>Location Details Form</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
			<header class="page-header">	<!-- Added by Saif 28/03/2018 -->
				<h2>Create Location</h2>
			</header>
			
		<div class = "content-padding">
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
		
			
	                 <div class="row">
						<div class="col-md-12">
						  <form action="createLocation.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Location</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="col-sm-4">
											<div class="form-group">
												<label class="control-label"><b>Location Name: <span class="required" aria-required="true">*</span></b></label>
												<input name="locationName" id="locationName" class="form-control" type="text" required>
											</div>
											<div class="form-group">
											<label class="control-label"><b>Description:</b></label>
												<input name="locationDesc" id="locationDesc" class="form-control" type="text" placeholder="" >
											</div>
	                                    	<div class="form-group">
												<label class="control-label"><b>Area:</b></label>
													<input name="area" id="area" class="form-control" type="text" placeholder="">
											</div>
										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<label class="control-label"><b>Zone: <span class="required" aria-required="true">*</span></b></label>
													<select class="form-control" name="zone" id="zone" required>
	                                                    <option value="">Select...</option>
	                                                    <c:forEach var="zone" items="${zoneList}" varStatus="i">
															<option value="${zone.zoneCode}">${zone.zoneName}</option>
														</c:forEach>
	                                                </select>
											</div>
											<div class="form-group">
												<label class="control-label"><b>Country: <span class="required" aria-required="true">*</span></b></label>
													<select class="form-control" name="country" id="country" required>
	                                                    <option value="">Select...</option>
	                                                    <c:forEach var="country" items="${countryList}" varStatus="i">
															<option value="${country.countryCode}">${country.countryName}</option>
														</c:forEach>
	                                                </select>
											</div>
											<div class="form-group">
												<label class="control-label"><b>State :<span class="required" aria-required="true">*</span></b></label>
													<select class="form-control" name="state" id="state" required>
	                                                    <option value="">Select...</option>
	                                                </select>
											</div>
	                                    	
										</div>
										<div class="col-sm-4">
											<div class="form-group">
												<label class="control-label"><b>City : <span class="required" aria-required="true">*</span></b></label>
													 <input name="city" id="city" class="form-control" type="text" placeholder="" required>
											</div>
											 
											<div class="form-group">
												<label class="control-label"><b>Pin : <span class="required" aria-required="true">*</span></b></label>
													 <input name="pin" id="pin" class="form-control" type="text" placeholder="" required> 
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
					<form name="editLocation" id="editLocation" action="editLocation.html" method="post">
                         <div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Location List</h2>
	                                </header>
	                                <input type="hidden" name="saveId" id="saveId">
	                                <input type="hidden" name="newLocationName" id="newLocationName">
	                                	<div class="panel-body">
		                                	<c:forEach var="location" items="${locationList}" varStatus="i">
												<input type="hidden" name="oldLocationNames" value="${location.locationName}">
											</c:forEach>
			                                   <table id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
			                                        <thead>
			                                            <tr>
			                                            	<th>Location Name</th>
			                                                <th>Area</th>
															<th>City</th>
															<th>State</th>
															<th>country</th>
															<th>Zone</th>
															<th>Pin</th>
															<th>Actions</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        
			                                        	<c:forEach var="location" items="${locationList}" varStatus="i">
			                                        		
																<tr >
																	<td>
																		<input type="hidden" id="locationCode${i.index}" name="locationCode${i.index}" value="${location.locationCode}">
																		<input type="hidden" id="locationName${i.index}" name="locationName${i.index}" class="form-control" value="${location.locationName}" readonly />
																		${location.locationName}
																	</td>
																	<td>
																		<input type="hidden" id="area${i.index}" name="area${i.index}" class="form-control" value="${location.area}" readonly />
																		${location.area}
																	</td>
																	<td>
																		<input type="hidden" id="city${i.index}" name="city${i.index}" class="form-control" value="${location.city}" readonly />
																		${location.city}
																	</td>
																	<td>
																		<input type="hidden" id="state${i.index}" name="state${i.index}" class="form-control" value="${location.state}" readonly />
																		${location.state}
																	</td>
																	<td>
																		<input type="hidden" id="country${i.index}" name="country${i.index}" class="form-control" value="${location.country}" readonly />
																		${location.country}
																	</td>
																	<td>
																		<input type="hidden" id="zone${i.index}" name="zone${i.index}" class="form-control" value="${location.zone}" readonly />
																		${location.zone}
																	</td>
																	<td>
																		<input type="hidden" id="pin${i.index}" name="pin${i.index}" class="form-control" value="${location.pin}" readonly />
																		${location.pin}
																	</td>
																	<td class="actions">
																		<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" onclick="updateLocation('${i.index}')"><i class="fa fa-pencil"></i></a>
																		<a class="on-default remove-row" href="inactiveLocation.html?locationCode=${location.locationCode}" ><i class="fa fa-trash-o"></i></a>
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
											<table class="table table-bordered table-striped mb-none" id="updateLocationDetails">
												<thead>
													<tr>
														<th>Location Name</th>
		                                                <th>Area</th>
														<th>City</th>
														<th>State</th>
														<th>country</th>
														<th>Zone</th>
														<th>Pin</th>
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
													<button id="updateLocation" class="btn btn-success">Update</button>
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
<script src="/icam/js/venue/createLocation.editable.js"></script>
<script src="/icam/js/venue/createLocation.js"></script> 
<script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script>
</body>
</html>
