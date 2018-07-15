<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Hostel Room Type</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">	<!-- Added by Saif 28/03/2018 -->
			<h2>Edit Venue Fcaility Mapping</h2>
	</header>
	<div class= "content-padding">
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
						<c:choose>
							 <c:when test="${facilityList eq null && facilityList.size() eq 0}">
								<!-- <div class="alert alert-danger">
									<strong>You've to create venue before adding venue facility.</strong>
								</div> -->						
							</c:when>
							<c:otherwise>
								<div class="col-md-8 col-md-offset-2">
									<form id="editVenueFacility" name="editVenueFacility" action="editVenueFacility.html" method="post">
										<section class="panel">
											<header class="panel-heading">
												<div class="panel-actions">
													<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
												</div>
												<h2 class="panel-title">Edit Venue Facility</h2>										
											</header>
											<div style="display: block;" class="panel-body">
		                                        
												<div class="form-group">
													<label class="col-sm-8 control-label">Venue Name</label>
													<div class="col-sm-8">
														<select class="form-control" name="venueCode" id="venueCode" required>
		                                                    <option value="">Select...</option>
		                                                    <c:forEach var="venue" items="${venueList}" varStatus="i">
																<option value="${venue.venueCode}">${venue.venueName}</option>
															</c:forEach>
		                                                </select>
													</div>
												</div>       
		                                        <hr>
		                                      <div class="col-md-8 col-md-offset-2">
		                                            <table class="table table-bordered table-striped mb-none" id = "editFacility"  style="visibility: hidden">
		                                                <thead>
		                                                    <tr>
		                                                        <th colspan="2" style="background:#eee; text-align:center;">Facility Name</th>
		                                                    </tr>
		                                                </thead>
		                                                <tbody id = "editFacilityBody">
		                                                	<%-- <c:forEach var="facility" items="${facilityList}">
		                                                		<tr>
			                                                        <td>
			                                                        	<input type="checkbox" id = "facilityCode" name="facilityCode" value="${facility.facilityCode}">
		                                                        	</td>
			                                                        <td>${facility.facilityName}</td>
			                                                    </tr>
		                                                	</c:forEach> --%>
	                                                   	</tbody>
		                                            </table>
		                                        </div>                                        
		                                           
											</div>
											<footer style="display: block;" class="panel-footer">
												<input type="button" class="btn btn-primary editbtn" id="edit" name="edit" onclick = "makeEditable()" value = "Edit"></input>
												<input type = "submit" class="submitbtn btn btn-primary" id="submit" name="submit" value = "Submit"   style="visibility: hidden">
												<button type="reset" class="btn btn-default" style="visibility: hidden">Reset</button>
											</footer>
										</section>
		                            </form>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
	</div>
	



<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/venue/editVenueFunctionalityMapping.js"></script> 
</body>
</html>