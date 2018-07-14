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
	<header class= "page-header">	<!-- Added by Saif 29-03-2018 -->
		<h2>Assign Hostel To Student</h2>
	</header>
		<div class = "content-padding">
			<div class="row">
						<div class="col-md-8 col-md-offset-2">
							<form:form action="assignedHostel.html" method="POST">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Student And Hostel Details</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        <div class="row">
                                        <c:choose>
										<c:when test="${studentFromStudentManagement != null}">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">User Id</label>
                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" name="rollNumber" id="rollNumber" readonly="readonly" value="${studentFromStudentManagement.userId}">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Name</label>
                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" name="name" id="name" readonly="readonly" value="${studentFromStudentManagement.firstName} ${studentFromStudentManagement.middleName} ${studentFromStudentManagement.lastName}">
                                                    </div>
                                                </div>
                                                <%-- <div class="form-group">
                                                    <label class="col-sm-6 control-label">Standard</label>
                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" name="klass" id="klass" readonly="readonly" value="${studentFromStudentManagement.klass}">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Section</label>
                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" name="sectionName" id="sectionName" readonly="readonly" value="${studentFromStudentManagement.section}">
                                                    </div>
                                                </div> --%>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                        	<div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">User Id</label>
                                                    <div class="col-sm-6">
                                                    	<select class = "form-control" name = "userId" id="userId">
                                                    		<option>Select..</option>
                                                    		<c:if test="${hostelList!= null && hostelList.size()!= 0}">
																<c:if test="${hostelList.get(0).resourceList != null && hostelList.get(0).resourceList.size()!= 0}">
																	<c:forEach var="resourceList" items="${hostelList.get(0).resourceList}">
																		<option value="${resourceList.userId}">${resourceList.userId}</option>
																	</c:forEach>
																</c:if>
															</c:if>
                                                    	</select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Name</label>
                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" name="name" id="name" readonly="readonly">
                                                    </div>
                                                </div>
                                               <!--  <div class="form-group">
                                                    <label class="col-sm-6 control-label">Standard</label>
                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" name="klass" id="klass" readonly="readonly">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Section</label>
                                                    <div class="col-sm-6">
                                                        <input type="text" class="form-control" name="sectionName" id="sectionName" readonly="readonly">
                                                    </div>
                                                </div> -->
                                            </div>
                                        </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                        <c:when test="${hostelRoomTypeByStudent != null}">
                                        	<div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Hostel Name</label>
                                                    <div class="col-sm-6">
                                                    	<input type="hidden" name="hostelName" id="hostelName" readonly="readonly" class="textfield1"  value="${hostelRoomTypeByStudent.hostelCode}"/>
                                                        <input type="text" class="form-control" id="hostelNameNh" readonly="readonly" value="${hostelRoomTypeByStudent.hostelName}">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Room Type</label>
                                                    <div class="col-sm-6">
                                                    	<input type="hidden" name="roomTypeName" id="roomTypeName" readonly="readonly" class="textfield1"  value="${hostelRoomTypeByStudent.room.roomTypeCode}"/>
                                                        <input type="text" class="form-control" id="roomTypeNameNh" readonly="readonly" value="${hostelRoomTypeByStudent.room.roomTypeName}">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Room Name</label>
                                                    <div class="col-sm-6">
                                                        <select name="roomName" id="roomName" class="form-control" >
															<option value="">Select...</option>
															<c:forEach var="room" items="${hostelRoomTypeByStudent.roomList}">
																<option value="${room.roomCode}">${room.roomName}</option>
															</c:forEach>
														</select>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                        	<div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Hostel Name</label>
                                                    <div class="col-sm-6">
                                                    	<select name="hostelName" id="hostelName" class="form-control">
															<option value="">Select...</option>
															<c:if test="${hostelList!= null && hostelList.size()!= 0}">
																<c:forEach var="hostelList" items="${hostelList}">
																	<option value="${hostelList.hostelCode}">${hostelList.hostelName}</option>
																</c:forEach>
															</c:if>
														</select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Room Type</label>
                                                    <div class="col-sm-6">
                                                    	<select name="roomTypeName" id="roomTypeName" class="form-control">
															<option value="">Select...</option>
														</select>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Room Name</label>
                                                    <div class="col-sm-6">
                                                        <select name="roomName" id="roomName" class="form-control">
															<option value="">Select...</option>
														</select>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:otherwise>
                                        </c:choose>
                                        </div>
                                        <hr>
                                        <div class="form-group col-sm-6" id="bedsTableDiv" style="display: none">
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
                                        <div class="form-group col-sm-6" id="roomfacTableDiv" style="display: none">
                                            <table class="table table-bordered table-striped mb-none" id="roomfac">
                                                <thead>
                                                    <tr>
                                                        <th colspan="2" style="background:#eee; text-align:center;">Facilities</th>
                                                    </tr>
                                                    <tr>
                                                        <th style="background:#eee; text-align:center;">Facilities Name</th>
                                                        <th style="background:#eee; text-align:center;">No Of Facilities</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>                                        
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submitButton" name="submit">Submit </button>
										<button type="reset" class="btn btn-default" id="clearButton">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
					</div>
		</div>

					

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/icam/js/hostel/assignHostelToStudent.js"></script>
</body>
</html>