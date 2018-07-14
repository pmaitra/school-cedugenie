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
       } .mb-md{
       	   display: none;
       }
</style>
</head>
<body>

		 <div class="row">
						<div class="col-md-4">
						<c:if test="${successStatus != null}">
							<div class="successbox" id="successbox" style="visibility:visible;">
								<span id="successmsg" style="visibility:visible;">Successfully Updated!</span>	
							</div>
						</c:if>
						<c:if test="${failStatus != null}">
								<div class="errorbox" id="errorbox" style="visibility: visible;">
									<span id="errormsg">Update Fail!</span>	
								</div>
						</c:if>
						  <form:form method="POST" action="submitSeatAllotment.html">	
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Seat Allotment</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label">Venue</label>
                                            <select class="form-control" name="venueId" id="venue">
                                                <option value="">Select...</option>
                                                <c:forEach var="venue" items="${venueList}">
													<option value="<c:out value="${venue.venueId}"/>"><c:out value="${venue.venueName}"/></option>
												</c:forEach>
                                            </select>
                                        </div>
                                        <div class="checkbox-custom checkbox-default">
                                            <input type="checkbox" id="venueWiseForm" name="status" checked="">
                                            <label for="checkboxExample1">Venue Wise Form</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Availabvle Capacity</label>
                                            <input type="text" class="form-control" id="capacity" name="capacity" placeholder="">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Start Seat Roll No</label>
                                            <input type="text" class="form-control" id="startSeatRollNo" name="startSeatRollNo" placeholder="">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">End Seat Roll No</label>
                                            <input type="text" class="form-control" id="endSeatRollNo" name="endSeatRollNo" placeholder="">
                                        </div>
                                        <input type="hidden" name="numberOfCandidate" id="numberOfCandidate" value="0" readonly="readonly"/>
										<input type="hidden" name="prevStartSeatRollNo" id="prevStartSeatRollNo" value="0" readonly="readonly"/>		
										<input type="hidden" name="prevEndSeatRollNo" id="prevEndSeatRollNo" value="0" readonly="readonly"/>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id="submitButton" onclick="return validateSeatAllotmentForm();">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
						<div class="col-md-8">	
                            <form>
                                <section class="panel">
                                    <header class="panel-heading">
                                        <div class="panel-actions">
                                            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                        </div>

                                        <h2 class="panel-title">Seat Allotment</h2>
                                    </header>
                                    <div class="panel-body">
                                        <table class="table table-bordered table-striped mb-none">
                                            <thead>
                                                <tr>
                                                    <th>Venue</th>
                                                    <th>Total Capacity</th>
                                                    <th>Available Capacity</th>
                                                    <th>Reset Seat Allotment</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<c:forEach var="venue" items="${venueList}">
	                                                <tr>
	                                                    <td>${venue.venueName}</td>
	                                                    <td>{venue.capacity}</td>
	                                                    <td>${venue.availableSeat}</td>
	                                                    <td><button type="reset" class="btn btn-danger" name="resetSeatAllotment" id="resetSeatAllotment" readonly="readonly" onClick="window.open('resetSeatAllotment.html?venueId=${venue.venueId}','_self')" style="cursor:pointer;">Reset</button></td>
	                                                </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div> 
                                </section>
                            </form>
						</div>
					</div>	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>