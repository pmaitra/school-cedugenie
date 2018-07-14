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
<title>Assign hostel to student</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">	<!-- Added by Saif 28/03/2018 -->
			<h2>Venue Resource Mapping</h2>
	</header>
		<div class = "content-padding">
			<div class="row">
						<c:choose>
							<c:when test="${null eq userIdList && userIdList.size()!= 0}">
								<div class="alert alert-danger">
									<strong>No resource left for assign any venue.</strong>
								</div>
							</c:when>
						<c:otherwise>
						<div class="col-md-8 col-md-offset-2">
							<form:form action="submitAssignedVenueToResource.html" method="POST">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Venue Details</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        <div class="row">
                                        	<div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Venue Name</label>
                                                    <div class="col-sm-6">
                                                    	<select class = "form-control" name = "venueName" id="venueName">
                                                    		<option value="">Select</option>
                                                    		<c:if test="${venueList!= null && venueList.size()!= 0}">
																<c:forEach var="venue" items="${venueList}">
																	<option value="${venue.venueCode}">${venue.venueName}</option>
																</c:forEach>
															</c:if>
                                                    	</select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Block</label>
                                                    <div class="col-sm-6">
                                                        <select class = "form-control" name = "block" id="block">
                                                        	<option value="">Select</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Room No</label>
                                                    <div class="col-sm-6">
                                                        <select class = "form-control" name = "roomNo" id="roomNo">
                                                        	<option value="">Select</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        	<div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">User Id</label>
                                                    <div class="col-sm-6">
                                                    	<select class = "form-control" name = "userId" id="userId">
                                                    		<option value="">Select</option>
                                                    		<c:if test="${userIdList!= null && userIdList.size()!= 0}">
																<c:forEach var="user" items="${userIdList}">
																	<option value="${user.userId}">${user.userId}</option>
																</c:forEach>
															</c:if>
                                                    	</select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Name</label>
                                                    	<input type="text" class="form-control" name="resourceName" id="resourceName" readonly="readonly">
                                                    </div>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="form-group col-sm-6 col-md-offset-3" id="bedsTableDiv" style="display:none">
                                            <table class="table table-bordered table-striped mb-none" id="beds">
                                                <thead>
                                                    <tr>
                                                        <th colspan="2" style="background:#eee; text-align:center;">Beds</th>
                                                    </tr>
                                                    <tr>
                                                        <th style="background:#eee; text-align:center;">Available</th>
                                                        <th style="background:#eee; text-align:center;">Out Of</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td><input type="text" name="bedVaccent" id="bedVaccent" readonly="readonly" class="form-control"></td>
                                                        <td><input type="text" name="bedTotal" id="bedTotal" readonly="readonly" class="form-control"></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submitButton" name="submit" onclick = "return validateAssign()">Assign</button>
										<button type="reset" class="btn btn-default" id="clearButton">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
						</c:otherwise>
						</c:choose>
				</div>
		</div>
		
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/facility/assignVenueToResource.js"></script>
</body>
</html>