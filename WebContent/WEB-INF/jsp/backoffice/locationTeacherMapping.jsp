<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listBookPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Location Teacher Mapping</title>

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
		<c:if test="${insertStatus eq 'success'}">
				<div class="alert alert-success">
					<strong>${successMessage}</strong>
				</div>
		</c:if>
			
			<c:if test="${insertStatus eq 'fail'}">
				<div class="alert alert-danger">
					<strong>${errorMessage}</strong>
				</div>
			</c:if>

	
			
			 <div class="row">
			 	 <form method="POST" id="venueTeacherMapping" name="venueTeacherMapping" action="insertVenueTeacherMapping.html" >
			 	 <input type="hidden" name="taskIndex" id="taskIndex">
			 	 <input type="hidden" name="resourceIndex" id="resourceIndex">
						
						 <div class="col-md-6 col-md-offset-3">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Location Teacher Mapping</h2>										
									</header>
									<div style="display: block;" class="panel-body">
									<div class="form-group">
											<label class="col-sm-4 control-label">Program: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<select class="form-control" id="standard" name="standard" required>
                                                    <option value="">Select...</option>
                                                    <c:forEach var="standard" items="${standardList}" varStatus="i">
														<option value="${standard.courseCode}">${standard.courseName}</option>
													</c:forEach>
                                                </select>
											</div>
										</div>
									  <input type="hidden" id="jsonData">
										<div class="form-group">
                                            <label class="col-sm-4 control-label"> Venue Type: <span class="required" aria-required="true">*</span></label>
                                           	<div class="col-sm-7">
	                                            <select class="form-control" name="venueTypeCode" id="venueTypeCode" required >
	                                                <option value="">Select...</option>
	                                                  <c:forEach var="venueType" items="${venueTypeList}">
	                                                  		<option value="${venueType.venueTypeCode}">${venueType.venueTypeName}</option>
	                                                  </c:forEach>
	                                            </select>
                                            </div>
                                        </div>	
                                       
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label">Venue:<span class="required" aria-required="true">*</span></label>
                                            <div class="col-sm-7">
	                                            <select class="form-control" name="venueCode" id="venueCode" required>
	                                                <option value="">Select...</option>
	                                                <c:forEach var="job" items="${taskNameList}">
														<option value="${job.jobTypeCode}">${job.jobTypeName}</option>
													</c:forEach>	
	                                            </select>
	                                          </div>
                                        </div>
                                        </div>	
                                         </section>
									</div>
									
 									
                                        
                                       
									
								
                           
						
						<div class="col-md-12"  id = "resource">
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title"></h2>
                                </header>
                                <div class="panel-body">
								<c:forEach var="resourceType" items="${resourceTypeList}">
									<input type = "hidden" name = "resourceTypeCode" value="${resourceType.resourceTypeCode}"/>
									<input type = "hidden" name = "resourceTypeName" value="${resourceType.resourceTypeName}"/>
								</c:forEach>
                                   <table class="table table-bordered table-striped mb-none" id = "userTable">
                                            <thead>
                                                <tr>
                                                    <th>Resource Type</th>
                                                    <th>User Id</th>
                                                    <th>User Name</th>
                                                    <th>Add</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<tr>
                                                	<td>
                                                		<select class="form-control" name="resourceTypeName" id="resourceTypeName" required>
                                                    		<option value="" >Select...</option>
                                                  	  			<c:forEach var="resourceType" items="${resourceTypeList}">
																	<option value="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
																</c:forEach>
                                               			 </select>
                                               		</td>
													<td>
														 <input type="text" class="form-control" name="userName0" id="userId0"  required/>
													</td>
													<td>
														 <input type="text" class="form-control" name="name0" id="name0" readonly/>
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
						 
						<div class="col-md-12"  id = "footer">
							<footer style="display: block;" class="panel-footer">
								<button class="btn btn-primary" onclick = "setIndex()">Submit </button>
								<button  class="btn btn-default" id = "reset" onclick = "resetValue();">Reset</button>
							</footer>
						</div>
						</form> 
					</div>	
					<div class = "row">
						 <div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Programme List</h2>
	                                </header>
	                                	<div class="panel-body">
		                                	<c:forEach var="course" items="${courseList}" varStatus="i">
												<input type="hidden" name="oldCourseNames" value="${course.courseName}">
											</c:forEach>
			                                    <table id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
			                                        <thead>
			                                            <tr>
			                                            	<th>Venue Name</th>
															<th>City</th>
															<th>Interview Date</th>
															<th>Interview Panel</th>
															<!-- <th>Actions</th> -->
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        
			                                        	<c:forEach var="location" items="${locationDetailsList}" varStatus="i">
			                                        		
																	<tr>
																		<td>
																			${location.venueName}
																			<input type="hidden" id="locationId${i.index}" name="locationId${i.index}" class="form-control" value="${location.venueTeacherMappingId}" readonly />
																			<input type="hidden" id="venueCode${i.index}" name="venueCode${i.index}" class="form-control" value="${location.venueCode}" readonly />
																		</td>
																		<td>${location.city}</td>
																		<td>
																			${location.interviewDate}
																		</td>
																		<td>
																			<c:forEach var="resource" items="${location.nameIdList}" varStatus="i">
																				${resource.name},
																			</c:forEach>
																		</td>
																		
																		<%-- <td class="actions">
																			<a href="inactiveVenueTeacherMapping.html?venueTeacherMappingId=${location.venueTeacherMappingId}" id = "delete${i.index}"><i class="fa fa-trash-o"></i></a>
																		</td> --%>
																	</tr>
																	
														</c:forEach>
			                                        
			                                        </tbody>
			                                    </table>
			                                </div>
	                            </section>
							</div>
					</div>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/backoffice/locationTeacherMapping.js"></script>
</body>
</html>