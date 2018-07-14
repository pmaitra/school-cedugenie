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
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>


		<c:choose>
			<c:when test="${insertStatus == null}">
			</c:when>
			<c:otherwise>
				<c:if test="${insertStatus != null}">
					<c:if test="${insertStatus == 'success'}">
						<div class="successbox" id="successbox"  style="visibility:visible;">
								<span id="successmsg">Data Successfully Inserted</span>	
						</div>			
					</c:if>
			
					<c:if test="${insertStatus == 'fail'}">
						<div class="errorbox" id="errorbox"  style="visibility:visible;">
										<span id="errormsg">Problem Occur While Saving</span>	
						</div>			
					</c:if>
				
				</c:if>
			</c:otherwise>
		</c:choose>	
		
		
		<c:choose>
			<c:when test="${resourceInterviewSchedule == null}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
							<span id="errormsg">Interview Details Not Found</span>	
				</div>
			</c:when>
		<c:otherwise>
			<div class="row">
				<div class="col-md-12">
					<form:form modelAttribute="FORM" method="POST" id="sicontents" name="sicontents" action="updateTeacherInterviewSchedule.html">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>

							<h2 class="panel-title">Teacher Interview Schedule</h2>										
						</header>
						<div style="display: block;" class="panel-body">
                                    <div class="row">                                                     
                                         <div class="col-md-3">
                                             <div class="form-group">
                                                 <label class="control-label">Candidate Code </label>
                                                 <input class="form-control"  type="text" name="employeeCode" id="employeeCode" value="${resourceInterviewSchedule.employeeCode}" readonly>                                                
                                             </div>
                                              <div class="form-group">
                                                 <label class="control-label">Teacher First Name </label>
                                                 <input class="form-control"  type="text" name="resource.firstName" id="firstName" value="${resourceInterviewSchedule.resource.firstName}" readonly>                                                
                                             </div>
                                             <div class="form-group">
                                                 <label class="control-label">Teacher Middle Name </label>
                                                 <input class="form-control" type="text" name="resource.middleName" id="middleName" value="${resourceInterviewSchedule.resource.middleName}" readonly>
                                             </div>
                                             <div class="form-group">
                                                 <label class="control-label">Teacher Last Name</label>
                                                 <input class="form-control" type="text" name="resource.lastName" id="lastName" value="${resourceInterviewSchedule.resource.lastName}" readonly>
                                             </div>
                                             <div class="form-group">
                                             	<c:choose>
													<c:when test="${resourceInterviewSchedule.resource.gender eq 'M'}">
														<input type="radio" name="resource.gender" value="M" id="genderM" class="css-checkbox" checked="checked" disabled="disabled"/>
														<label for="genderM" class="css-label">Male</label>				
													</c:when>
													<c:otherwise>
														<input type="radio" name="resource.gender" value="M" id="genderM" class="css-checkbox" disabled="disabled"/>
															<label for="genderM" class="css-label">Male</label>				
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${resourceInterviewSchedule.resource.gender eq 'F'}">
														<input type="radio" name="resource.gender" value="F" id="genderF" class="css-checkbox" checked="checked" disabled="disabled"/>
														<label for="genderF" class="css-label">Female</label>				
													</c:when>
													<c:otherwise>
														<input type="radio" name="resource.gender" value="F" id="genderF" class="css-checkbox"  disabled="disabled"/>
														<label for="genderF" class="css-label">Female</label>				
													</c:otherwise>
												</c:choose>                                                 
                                             </div> 
                                             <div class="form-group">
                                       	     	<div class="form-group">
                                                     <label class="control-label">Date Of Birth</label>
                                                     <div class="input-group">
                                                         <span class="input-group-addon">
                                                             <i class="fa fa-calendar"></i>
                                                         </span>
                                                         <input type="text" class="form-control" data-plugin-datepicker="" readonly value="${resourceInterviewSchedule.resource.dateOfBirth}" id="dateOfBirth" name="resource.dateOfBirth"/>
                                                     </div>
                                                 </div>
                                             </div>
                                         </div>
                                         <div class="col-md-3">
                                             <div class="form-group">
                                                 <label class="control-label">Highest Qualification</label>
                                                 <input class="form-control"  type="text" name="qualification.qualificationName" id="qualification" value="${resourceInterviewSchedule.qualification.qualificationName}" readonly>
                                             </div>
                                             <div class="form-group">
                                                 <label class="control-label">Subject Specialization</label>
                                                 <input class="form-control" type="text" name="qualification.specialization" id="specialization" value="${resourceInterviewSchedule.qualification.specialization}" readonly>
                                             </div>
                                             <div class="form-group">
                                                 <label class="control-label">Experience</label>
                                                 <input class="form-control"  type="text" name="experience" id="experience" value="${resourceInterviewSchedule.experience}" readonly>
                                             </div>
                                             <div class="form-group">
                                                 <label class="control-label">Date Of Interview</label>
                                                 <div class="input-group">
                                                      <span class="input-group-addon">
                                                          <i class="fa fa-calendar"></i>
                                                      </span>
                                                      <input type="text" class="form-control" data-plugin-datepicker="" id="dateOfInterview" name="dateOfInterview" value="${resourceInterviewSchedule.dateOfInterview}" readonly>
                                                  </div>
                                             </div>
                                             <div class="form-group">
                                                 <label class="control-label">Time Of Interview</label>
                                                 <input class="form-control"  type="text" id="interviewTime" name="timeOfInterview" value="${resourceInterviewSchedule.timeOfInterview}"  data-plugin-datepicker="" readonly/>
                                             </div>                                             
                                         </div>                               
                                         
                                         <div class="col-md-3">                                             
                                             <div class="form-group">
                                             	<label class="control-label">Teaching Level</label>                                                
												<select name="teachingLevel.teachingLevelName" id="teachingLevelName"  class="form-control" disabled >
													<c:forEach var="teachingLevel" items="${teachingLevelList}">
														<c:if test="${teachingLevel.teachingLevelName eq resourceInterviewSchedule.teachingLevel.teachingLevelName}">
															<option selected="selected" value="<c:out value="${teachingLevel.teachingLevelName}"/>"><c:out value="${teachingLevel.teachingLevelName}"/></option>									
														</c:if>
													</c:forEach>
													 <c:forEach var="teachingLevel" items="${teachingLevelList}">
														<c:if test="${teachingLevel.teachingLevelName ne resourceInterviewSchedule.teachingLevel.teachingLevelName}">
															<option value="<c:out value="${teachingLevel.teachingLevelName}"/>"><c:out value="${teachingLevel.teachingLevelName}"/></option>
														</c:if>
													</c:forEach>		
												</select>
                                             </div>
                                              <div class="form-group">
												<label class="control-label">Examiner Name</label>
												<div>
<!-- 													<select class="form-control" multiple="multiple" data-plugin-multiselect data-plugin-options='{ "maxHeight": 200 }' id="examinerName" name="examinerName"> -->
<%-- 														<c:forEach var="examiner" items="${resourceInterviewSchedule}">  --%>
<%-- 															<option value="<c:out value="${examiner.resource.userId}"/>"><c:out value="${examiner.resource.userId}"/></option>									  --%>
<%-- 														</c:forEach> --%>
<!-- 													</select> -->
														<%-- <select class="form-control" multiple="multiple" data-plugin-multiselect data-plugin-options='{ "maxHeight": 200 }' id="examinerName" name="examinerName">
															<c:forEach var="examiner" items="${resourceInterviewSchedule.resourceList}">
																<c:choose>
																	<c:when test="${examiner.status ne null}">
																		<option value="<c:out value="${examiner.userId}"/>"><c:out value="${examiner.userId}"/></option>
																	</c:when>
																	<c:otherwise>															
																	</c:otherwise>
																</c:choose>													
															</c:forEach>
													</select> --%>
													<select class="form-control" id="examinerName" name="examinerName" disabled>
														 <c:forEach var="examiner" items="${resourceInterviewSchedule.resourceList}">
															<option value="${examiner.userId}" ${examiner.userId eq resourceInterviewSchedule.employeeName? 'selected=selected' : ''}>${examiner.userId}</option>
														</c:forEach>
													</select>
												</div>
											</div>
                                             <div class="form-group">
                                                 <label class="control-label">Room Number</label>
                                                 <input class="form-control"  type="text" name="roomNumber" id="roomNo" value="${resourceInterviewSchedule.roomNumber}" readonly/>
                                             </div>
                                           <%--   <div class="form-group">
                                                 <label class="control-label">Venue</label>
                                                 <input class="form-control"  type="text" name="venue" id="venue" value="${resourceInterviewSchedule.venue}" readonly/>
												 <input type="hidden" name="decision" value="newInterviewSchedule"/>
                                             </div> --%>
                                              <div class="form-group">
                                                 <label class="control-label">Referal(If Any)</label>
                                                 <input class="form-control"  type="text" name="referredBy" id="referredBy" value="${resourceInterviewSchedule.referredBy}" readonly/>
                                             </div>
                                               <!-- <div class="form-group">
                                                 <label class="control-label">Attach Resume</label>
                                                 <input class="form-control" type="file" name="fileData" id="fileData">
                                             </div> -->
                                         </div>                                  
                                    </div>
							</div>
						<footer style="display: block;" class="panel-footer">							
							<button class="btn btn-primary" type="submit" value="SUBMIT" id="submitForm" disabled="disabled">Submit </button>
							<button class="btn btn-primary" type="button" id="Edit" name="Edit" value="EDIT" onclick="makeTeacherInterviewScheduleEditable();">Edit</button>
							
						</footer>
					</section>
					</form:form>
				</div>
			</div>
		</c:otherwise>
		</c:choose>
			
		




<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src= "/cedugenie/js/erp/teacherInterviewSchedule.js" type="text/javascript"></script>
</body>
</html>