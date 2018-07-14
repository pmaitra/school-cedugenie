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
<title>Schedule Teacher Interview</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">
		<h2>Schedule Teacher Interview</h2>
	</header>
	<div class="content-padding">
		<div class="row">
			<div class="alert alert-danger" id="javascriptmsg" style="display: none">
			  <span id="warningmsg"></span>
			</div>
			<c:choose>
				<c:when test="${resourceInterviewSchedule == null}">
					<div class="alert alert-danger">
						<strong>No Examiner Found For Interview </strong>
					</div>
				</c:when>
				<c:when test="${insertStatus != null}">
					<c:if test="${insertStatus == 'success'}">
						<div class="alert alert-success">
							<strong>Interview Scheduled Successfully</strong>
						</div>
					</c:if>
					<c:if test="${insertStatus == 'fail'}">
						<div class="alert alert-success">
							<strong>Interview Scheduling Failed</strong>
						</div>
					</c:if>
				</c:when>
				<c:otherwise>
					<div class="col-md-12">
						<form:form modelAttribute="FORM" method="POST" id="sicontents" name="sicontents" action="setTeacherInterviewSchedule.html" enctype="multipart/form-data">
							<section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
									</div>
									<h2 class="panel-title">Schedule Teacher Interview</h2>
								</header>
								<div class="panel-body" style="display: block">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label">Teacher First Name <span class="required" aria-required="true">*</span></label>
                                       			<input class="form-control"  type="text" name="resource.firstName" id="firstName" required>
											</div>
											<div class="form-group">
												<label class="control-label">Teacher Middle Name </label>
                                       			<input class="form-control" type="text" name="resource.middleName" id="middleName" >
											</div>
											<div class="form-group">
												<label class="control-label">Teacher Last Name <span class="required" aria-required="true">*</span></label>
                                       			<input class="form-control" type="text" name="resource.lastName" id="lastName" required>
											</div>
											<div class="form-group">
												<div>
				                                   	<label class="control-label">Gender<span class="required" aria-required="true">*</span></label>
			                                   	</div>
			                                   	<input type="radio" name="resource.gender" value="M" id="genderM" class="css-checkbox" />
												<label for="genderM" class="css-label">Male</label>		
												<input type="radio" name="resource.gender" value="F" id="genderF" class="css-checkbox"  />
												<label for="genderF" class="css-label">Female</label>
											</div>
											<div class="form-group">
												<div class="form-group">
													<label class="control-label">Date Of Birth <span class="required" aria-required="true">*</span></label>
                                           			<div class="input-group">
		                                               <span class="input-group-addon">
		                                                   <i class="fa fa-calendar"></i>
		                                               </span>
		                                               <input type="text" class="form-control" id="dateOfBirth" name="resource.dateOfBirth" data-plugin-datepicker="" data-date-end-date="0d" required/>
                                           			</div>
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label">Highest Qualification <span class="required" aria-required="true">*</span></label>
                                       			<input class="form-control"  type="text" name="qualification.qualificationName" id="qualification" required>
											</div>
											<div class="form-group">
												<label class="control-label">Subject Specialization <span class="required" aria-required="true">*</span></label>
                                       			<input class="form-control" type="text" name="qualification.specialization" id="specialization">
											</div>
											<div class="form-group">
												<label class="control-label">Experience</label>
                                       			<input class="form-control"  type="text" name="experience" id="experience">
											</div>
											<div class="form-group">
												<label class="control-label">Date Of Interview <span class="required" aria-required="true">*</span></label>
                                       			<div class="input-group">
		                                            <span class="input-group-addon">
		                                                <i class="fa fa-calendar"></i>
		                                            </span>
		                                            <input type="text" class="form-control" data-plugin-datepicker="" data-date-start-date="0d" id="dateOfInterview" name="dateOfInterview" required/>
	                                        	</div>
											</div>
											<div class="form-group">
												<label class="control-label">Time Of Interview</label>
                                       			<input class="form-control"  type="text" id="interviewTime" name="timeOfInterview" data-plugin-timepicker="" required/>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label">Teaching Level <span class="required" aria-required="true">*</span></label>
												<select name="teachingLevel.teachingLevelName" id="teachingLevelName" class="form-control">
													<option value="">Select..</option>
														<c:forEach var="teachingLevel" items="${teachingLevelList}">
															<option value="<c:out value="${teachingLevel.teachingLevelName}"/>"><c:out value="${teachingLevel.teachingLevelName}"/></option>									
														</c:forEach>		
												</select>
											</div>
											<div class="form-group">
												<label class="control-label">Examiner Name <span class="required" aria-required="true">*</span></label>
												<select class="form-control"  id="examinerName" name="examinerName" required>
												<option value = "">Select</option>
													<c:forEach var="examiner" items="${resourceInterviewSchedule}"> 
														<option value="<c:out value="${examiner.resource.userId}"/>"><c:out value="${examiner.resource.userId}"/></option>									 
													</c:forEach>
												</select>
											</div>
											<div class="form-group">
									 			<label class="control-label">Venue <span class="required" aria-required="true">*</span></label>
                                       			<select class="form-control"  name="roomNumber" id="roomNumber" required>
                                       				<option value = ""> Select </option>
													<c:forEach var="room" items="${venueList}"> 
														<option value="${room.venueCode}"><c:out value="${room.venueName}"/></option>									 
													</c:forEach>
                                   				</select>
											</div>
											<div class="form-group">
												<label class="control-label">Referal(If Any)</label>
                                       			<input class="form-control"  type="text" name="referredBy" id="referredBy"/>
											</div>
										</div>
									</div>
								</div>
								<footer style="display: block;" class="panel-footer">
									<button class="btn btn-primary" type="submit" value="SUBMIT" onclick="return validateForm();">Submit </button>
									<button type="reset" class="btn btn-default">Reset</button>
								</footer>
							</section>
						</form:form>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/erp/teacherInterviewSchedule.js"></script>
</body>
</html>