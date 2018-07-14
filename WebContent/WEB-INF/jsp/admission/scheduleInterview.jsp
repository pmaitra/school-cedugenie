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
<title>Step by Step Admission Process</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Step by Step Admission Process</h2>
</header>
<div class="content-padding">
    <div class="row">
        <div class="col-xs-12">
            <section class="panel form-wizard" id="w4">
                <header class="panel-heading">
                    <div class="panel-actions">
                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                    </div>

                    <h2 class="panel-title">Step by Step Admission Process</h2>
                </header>
                <div class="panel-body">
                    <div class="wizard-progress wizard-progress-lg">
                        <div class="steps-progress">
                            <div class="progress-indicator"></div>
                        </div>
                         <c:if test="${admissionDriveStateNew eq 'FORMSUBMITTED'}">
	                        <ul class="wizard-steps">
	                        	<c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.formSubmission eq true}">
		                             <li id = "driveFormSubmission">
		                             <a  data-toggle="tab"><span>1</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=FORMSUBMISSION">Form <br> Submission</a> </a>
		                               <%--  <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>" data-toggle="tab"><span>1</span>Form <br> Submission</a> --%>
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
		                            <li  id = "driveInterviewSchedule">
		                                <%-- <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=SCHEDULEINTERVIEW"  data-toggle="tab"><span>2</span>Schedule <br> Interview</a> --%>
		                            	<a  data-toggle="tab"><span>2</span>Schedule <br> Interview</a>
		                            </li>
		                        </c:if>
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
		                            <li id = "driveInterviewResult">
		                            	 <a data-toggle="tab"><span>3</span> Interview <br> Result</a> 
		                                <%-- <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=INTERVIEWRESULT"  data-toggle="tab"><span>3</span>Interview <br> Result</a> --%>
		                            </li>
		                         </c:if>
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true}">
		                            <li id= "driveMeritList">
		                            	 <a data-toggle="tab"><span>4</span>Merit <br> List</a> 
		                               <%--  <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=MERITlIST"  data-toggle="tab"><span>4</span>Merit <br> List</a> --%>
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true}">
		                            <li id="drivePayment">
		                             	<a data-toggle="tab"><span>5</span> Payment</a> 
		                              <%--   <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionStates=PAYMENT"  data-toggle="tab"><span>5</span>Payment</a> --%>
		                            </li>
		                         </c:if>
	                        </ul>
	                     </c:if>
	                      <c:if test="${admissionDriveStateNew eq 'INTERVIEWSCHEDULED'}">
	                      		<ul class="wizard-steps">
	                        	<c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.formSubmission eq true}">
		                             <li id = "driveFormSubmission">
		                             <a  data-toggle="tab"><span>1</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=FORMSUBMISSION">Form <br> Submission</a> </a>
		                               <%--  <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>" data-toggle="tab"><span>1</span>Form <br> Submission</a> --%>
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
		                            <li  id = "driveInterviewSchedule">
		                                <%-- <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=SCHEDULEINTERVIEW"  data-toggle="tab"><span>2</span>Schedule <br> Interview</a> --%>
		                            	<a  data-toggle="tab"><span>2</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=INTERVIEWSCHEDULED">Schedule <br> Interview</a></a>
		                            </li>
		                        </c:if>
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
		                            <li id = "driveInterviewResult">
		                            	 <a data-toggle="tab"><span>3</span><a  href = "addInterviewResult.html?courseClass=<c:out value="${contactForm.courseClass}"/>&driveName=<c:out value="${contactForm.formName}"/>&year=<c:out value="${contactForm.admissionYear}"/>"> Interview <br> Result</a> </a>
		                                <%-- <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=INTERVIEWRESULT"  data-toggle="tab"><span>3</span>Interview <br> Result</a> --%>
		                            </li>
		                         </c:if>
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true}">
		                            <li id= "driveMeritList">
		                            	 <a data-toggle="tab"><span>4</span>Merit <br> List</a> 
		                               <%--  <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=MERITlIST"  data-toggle="tab"><span>4</span>Merit <br> List</a> --%>
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true}">
		                            <li id="drivePayment">
		                             	<a data-toggle="tab"><span>5</span> Payment</a> 
		                              <%--   <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionStates=PAYMENT"  data-toggle="tab"><span>5</span>Payment</a> --%>
		                            </li>
		                         </c:if>
	                        </ul>
	                      </c:if>
	                      <c:if test="${admissionDriveStateNew eq 'INTERVIEWRESULT'}">
	                      	<ul class="wizard-steps">
	                        	<c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.formSubmission eq true}">
		                             <li id = "driveFormSubmission">
		                             <a  data-toggle="tab"><span>1</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=FORMSUBMISSION">Form <br> Submission</a> </a>
		                               <%--  <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>" data-toggle="tab"><span>1</span>Form <br> Submission</a> --%>
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
		                            <li  id = "driveInterviewSchedule">
		                                <%-- <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=SCHEDULEINTERVIEW"  data-toggle="tab"><span>2</span>Schedule <br> Interview</a> --%>
		                            	<a  data-toggle="tab"><span>2</span><a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=INTERVIEWSCHEDULED">Schedule <br> Interview</a></a>
		                            </li>
		                        </c:if>
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
		                            <li id = "driveInterviewResult">
		                            	 <a data-toggle="tab"><span>3</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=INTERVIEWRESULT">Interview <br> Result</a> </a>
		                                <%-- <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=INTERVIEWRESULT"  data-toggle="tab"><span>3</span>Interview <br> Result</a> --%>
		                            </li>
		                         </c:if>
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true}">
		                            <li id= "driveMeritList">
		                            	 <a data-toggle="tab"><span>4</span><a  href = "viewMeritList.html?courseClass=<c:out value="${contactForm.courseClass}"/>&driveName=<c:out value="${contactForm.formName}"/>&year=<c:out value="${contactForm.admissionYear}"/>" >Merit <br> List</a> </a>
		                               <%--  <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=MERITlIST"  data-toggle="tab"><span>4</span>Merit <br> List</a> --%>
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true}">
		                            <li id="drivePayment">
		                             	<a data-toggle="tab"><span>5</span> Payment</a> 
		                              <%--   <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionStates=PAYMENT"  data-toggle="tab"><span>5</span>Payment</a> --%>
		                            </li>
		                         </c:if>
	                        </ul>
	                      </c:if>
	                       <c:if test="${admissionDriveStateNew eq 'MERITLIST'}">
	                       		 <ul class="wizard-steps">
                        	<c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.formSubmission eq true}">
	                             <li id = "driveFormSubmission">
	                             <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>" data-toggle="tab"><span>1</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=FORMSUBMISSION">Form <br> Submission</a> </a>
	                               <%--  <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>" data-toggle="tab"><span>1</span>Form <br> Submission</a> --%>
	                            </li>
	                        </c:if>
	                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
	                            <li  id = "driveInterviewSchedule">
	                                <%-- <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=SCHEDULEINTERVIEW"  data-toggle="tab"><span>2</span>Schedule <br> Interview</a> --%>
	                            	<a data-toggle="tab"><span>2</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=INTERVIEWSCHEDULED">Schedule <br> Interview</a>
	                            </li>
	                        </c:if>
	                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
	                            <li id = "driveInterviewResult">
	                            	 <a data-toggle="tab"><span>3</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=INTERVIEWRESULT">Interview <br> Result</a> </a>
	                                <%-- <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=INTERVIEWRESULT"  data-toggle="tab"><span>3</span>Interview <br> Result</a> --%>
	                            </li>
	                         </c:if>
	                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true}">
	                            <li id= "driveMeritList">
	                            	 <a  data-toggle="tab"><span>4</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=MERITLIST">Merit <br> List</a> </a>
	                               <%--  <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=MERITlIST"  data-toggle="tab"><span>4</span>Merit <br> List</a> --%>
	                            </li>
	                        </c:if>
	                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true}">
	                            <li id="drivePayment">
	                             	<a data-toggle="tab"><span>5</span> <a href="finalSelectedCandidate.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>">Payment</a> </a>
	                              <%--   <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionStates=PAYMENT"  data-toggle="tab"><span>5</span>Payment</a> --%>
	                            </li>
	                         </c:if>
                        </ul>
	                       </c:if>
                    </div>

                   
                        <div class="tab-content">
                            <div id="w4-Submission" class="tab-pane ">
                                                          
                            </div>
                            <div id="w4-Interview" class="tab-pane active">
                                <div class="row">
                                    <div class="col-md-12">
                                        <blockquote class="b-thin rounded primary">
                                            <h3>Schedule Interview</h3>
                                        </blockquote>
                                    </div>   
                                </div>
                                <c:if test="${admissionDriveState == null}">
	                                <form method="POST" id="sicontents" name="sicontents" action="newScheduleInterview.html">
									<input type="hidden" id="admissionDriveStartDate" name="admissionDriveStartDate" value="${StreamClassYearCourse.admissionDriveStartDate}"/>
									<input type="hidden" id="admissionDriveExpectedEndDate" name="admissionDriveExpectedEndDate" value="${StreamClassYearCourse.admissionDriveExpectedEndDate}"/>
	                                <div class="row well well-sm">
	                                    <div class="col-md-3">            
	                                      <div class="form-group">
	                                            <label class="control-label">Academic Year</label>
	                                            <input type="text" class="form-control" value="${StreamClassYearCourse.admissionFormYear}" readonly>
	                                        </div> 
	                                        <div class="form-group">
	                                            <label class="control-label">Course Name</label>
	                                            <input type="text" class="form-control"  value="${StreamClassYearCourse.courseName}" readonly>
	                                        </div> 
	                                        <div class="form-group">
	                                            <label class="control-label">Course Type</label>
	                                            <input type="text" class="form-control" value="${StreamClassYearCourse.courseType}" readonly>
	                                        </div>  
	                                        
	                                    </div>
	                                    <div class="col-md-3">
	                                       
	                                        <div class="form-group">
	                                            <label class="control-label">Form ID<span class="required" aria-required="true">*</span></label>
	                                            <select class="form-control" id="formId" name="formId">
	                                                <option value="">Please select</option>
														<c:forEach var="formSubmission" items="${contactForm.formList}">
														<%-- <option value=<c:out value="${formSubmission.strFormId}"/>><c:out value="${formSubmission.strFormId}"/></option> --%>
														<option value="${formSubmission.strFormId}">${formSubmission.strFormId}</option>
													</c:forEach>
	                                            </select>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Date Of Interview<span class="required" aria-required="true">*</span></label>
	                                            <div class="input-group">
	                                                <span class="input-group-addon">
	                                                    <i class="fa fa-calendar"></i>
	                                                </span>
	                                                <input type="text" class="form-control" id="interviewDate" name="interviewDate" data-plugin-datepicker="">
	                                            </div>
	                                        </div>
	                                    </div>
	                                    <div class="col-md-3"> 
	                                        <div class="form-group">
	                                            <label class="control-label">Time Of Interview<span class="required" aria-required="true">*</span></label>
	                                            <div class="input-group">
	                                                <span class="input-group-addon">
	                                                    <i class="fa fa-clock-o"></i>
	                                                </span>
	                                                <input type="text" class="form-control" id="interviewTime" name="interviewTime" data-plugin-timepicker="">
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Class</label>
	                                            <input type="text" class="form-control"  value="${ contactForm.courseClass}" readonly>
	                                            <input type="hidden" name="courseClass" value="<c:out value="${ contactForm.courseClass}"/>" id="courseClass" />
												<input type="hidden" name="admissionYear" id="admissionYear" value="<c:out value="${contactForm.admissionYear}"/>" />
												<input type="hidden" name="formName" id="formName" value="<c:out value="${contactForm.formName}"/>" />
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Examiner Name<span class="required" aria-required="true">*</span></label>
	                                            <select class="form-control" name="examinerName" id="examinerName" >
	                                                <option value="">Please select</option>
	                                                <c:forEach var="examiner" items="${contactForm.resourceList}">
														<%-- <option value=<c:out value="${examiner.userId}"/>><c:out value="${examiner.userId}"/></option> --%>
														<option value="${examiner.userId}">${examiner.userId}</option>
													</c:forEach>
	                                            </select>
	                                        </div>                                        
	                                    </div>
	                                    
	                                    <div class="col-md-3">
	                                        <div class="form-group">
	                                            <label class="control-label">Reviewer Name<span class="required" aria-required="true">*</span></label>
	                                            <select class="form-control" name="reviewerName" id="reviewerName">
	                                                <option value="">Please select</option>
	                                                <c:forEach var="reviewer" items="${contactForm.resourceList}">
														<%-- <option value=<c:out value="${reviewer.userId}"/>><c:out value="${reviewer.userId}"/></option> --%>
														<option value="${reviewer.userId}">${reviewer.userId}</option>
													</c:forEach>	
	                                            </select>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Room Number</label>
	                                            <input type="text" name="roomNo" id="roomNo" class="form-control">
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Venue<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" name="venue" id="venue" class="form-control">
	                                        </div>
	                                    </div>
	                                    <div class="col-md-12">&nbsp;</div>
	                                    <div class="col-md-12">
	                                   		<input type="hidden" name="decision" value="newInterviewSchedule">
											<button class="btn btn-success" type="submit" name="submit" onclick = "return validate()">Submit</button>
											<a href = "#scheduleInterviewToInterviewResult" class="mb-xs mt-xs mr-xs modal-basic btn btn-primary">Next</a>
										</div>
										<div id="scheduleInterviewToInterviewResult" class="modal-block modal-header-color modal-block-info mfp-hide">
						                     <section class="panel">
						                         <header class="panel-heading">
						                             <h2 class="panel-title">Confirmation</h2>
						                         </header>
						                         <div class="panel-body">
						                             <div class="">
						                                 <p>Are You Sure to go to Interview Result Process?</p>
						                             </div>
						                         </div>
						                         <footer class="panel-footer">
				                                    <a href = "addInterviewResult.html?courseClass=<c:out value="${contactForm.courseClass}"/>&driveName=<c:out value="${contactForm.formName}"/>&year=<c:out value="${contactForm.admissionYear}"/>" class="btn btn-success">OK</a>
				                                 	<button class="btn btn-danger modal-dismiss">Cancel</button>
						                         </footer>
						                     </section>
						                 </div>
	                                </div>
	                                </form>
	                            </c:if>
                                <div class="row">   
                                    <div class="col-md-12">&nbsp;</div>
                                      <input type="hidden" class="form-control" id = "driveStatus" name = "driveStatus" value="${admissionDriveState}" />
                                   	<input type="hidden" class="form-control" id = "driveStatusNew" name = "driveStatusNew" value="${admissionDriveStateNew}" /> 
                                     <div class="col-md-12">
                                        <blockquote class="b-thin rounded primary">
                                            <h3>Scheduled Form List</h3>
                                        </blockquote>
                                        <table class="table table-bordered table-striped mb-none dataTable no-footer" id="datatable-tabletools">
                                            <thead>
                                                <tr>
	                                                <th>Form ID</th>
													<th>Drive Name</th>
													<th>Candidate Name</th>
													<th>Interview Date</th>
													<th>Interview Time</th>
													<th>Status</th>
													<th>Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="interviewSchedule" items="${submittedAdmisionInterviewScheduleList}">
													<tr>
														<td><c:out value="${interviewSchedule.formId}"/>
															<input type="hidden" name="formId" id="formid" value="<c:out value="${interviewSchedule.formId}"/>"/>
														</td>
														<td><c:out value="${interviewSchedule.formName}"/></td>
														<td><c:out value="${interviewSchedule.candidateFirstName}"/> <c:out value="${interviewSchedule.candidateMiddleName}"/> <c:out value="${interviewSchedule.candidateLastName}"/> </td>
														<td ><c:out value="${interviewSchedule.interviewDate}"/></td>
														<td><c:out value="${interviewSchedule.interviewTime}"/></td>
														<td><c:out value="${interviewSchedule.formStatus}"/></td>
														<td>
															<input type="hidden" name="courseClass" value="<c:out value="${formSubmission.courseClass}"/>">
															<input type="hidden" name="admissionYear" id="admissionYear" value="<c:out value="${formSubmission.admissionYear}"/>"/>
															<input type="hidden" name="formName" id="formName" value="<c:out value="${formSubmission.formName}" />"/>
															<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic btn btn-info" onclick = "showScheduledInterviewDetails('${interviewSchedule.formId}','${interviewSchedule.courseClass}','${interviewSchedule.admissionYear}','${interviewSchedule.formName}')">Details</a>
														</td>
													</tr>
												</c:forEach>                                                
                                            </tbody>
                                        </table>   
                                    </div>                                                        
                                </div>
                            </div>
                            <div id="w4-Result" class="tab-pane">
                            </div>
                            <div id="w4-Merit" class="tab-pane">

                            </div>
                            <div id="w4-Payment" class="tab-pane">
    
                            </div>
                        </div>
                </div>
                <div class="panel-footer">
                    <ul class="pager">
                        <li class="previous disabled">
                            <a><i class="fa fa-angle-left"></i> Previous</a>
                        </li>
                        <li class="finish hidden pull-right">
                            <a>Finish</a>
                        </li>
                        <li class="next">
                            <a>Next <i class="fa fa-angle-right"></i></a>
                        </li>
                    </ul>
                </div>
                <div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
                    <section class="panel">
                        <header class="panel-heading">
                            <h2 class="panel-title">Scheduled Form Details</h2>
                        </header>
                        <div class="panel-body">
                            <table class="table table-bordered table-striped mb-none" id = "scheduleFormDetails">
                                <thead>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <footer class="panel-footer">
                            <div class="row">
                                <div class="col-md-12 text-right">
                                    <button class="btn btn-info modal-dismiss">OK</button>
                                </div>
                            </div>
                        </footer>
                    </section>
                </div>
            </section>
        </div>
    </div>
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script> 
<script type="text/javascript" src="/cedugenie/js/admission/scheduleNewInterview.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script>

 $(document).ready(function() {
	var state = $('#driveStatus').val();
	var stateNew = $('#driveStatusNew').val();
	if(state == 'DONE'){
		//alert("hiii");
		$('#driveFormSubmission').addClass("completed");
		$('#driveInterviewSchedule').addClass("completed");
		$('#driveInterviewResult').addClass("completed");
		$('#driveMeritList').addClass("completed");
		$('#drivePayment').addClass("completed");
	}
	
	if(stateNew == 'FORMSUBMITTED'){
		//alert("hiii");
		$('#driveFormSubmission').addClass("completed");
		$('#driveInterviewSchedule').addClass("active");
		//$('#driveInterviewResult').addClass("completed");
		//$('#driveMeritList').addClass("completed");
		//$('#drivePayment').addClass("completed");
	}
	if(stateNew == 'INTERVIEWSCHEDULED'){
		//alert("hiii");
		$('#driveFormSubmission').addClass("completed");
		$('#driveInterviewSchedule').addClass("completed");
		$('#driveInterviewResult').addClass("active");
		//$('#driveMeritList').addClass("completed");
		//$('#drivePayment').addClass("completed");
	}
	if(stateNew == 'INTERVIEWRESULT'){
		$('#driveFormSubmission').addClass("completed");
		$('#driveInterviewSchedule').addClass("completed");
		$('#driveInterviewResult').addClass("completed");
		$('#driveMeritList').addClass("active");
		//$('#drivePayment').addClass("completed");
	}
	if(stateNew == 'MERITLIST'){
		$('#driveFormSubmission').addClass("completed");
		$('#driveInterviewSchedule').addClass("completed");
		$('#driveInterviewResult').addClass("completed");
		$('#driveMeritList').addClass("completed");
		$('#drivePayment').addClass("active");
	}
}); 
function showScheduledInterviewDetails(formId,courseClass,admissionYear,formName)
{	
	$.ajax({
		url: '/cedugenie/viewPreviousScheduleInterviewDetails.html',
		dataType: 'json',
		data: "courseClass="+courseClass+"&admissionYear="+admissionYear+"&formName=" +formName+"&formId="+formId,
	    success: function(data) {	
	    	 $('#scheduleFormDetails > tbody').empty();
	     	if(data != null && data!=""){
	     		
	     		var dataarr = data.split(";");
	     		var row = "<tbody>";
	     		row += "<tr><td>Academic Year</td><td>"+dataarr[1]+"</td></tr>";
	     		row += "<tr><td>Course Name</td><td>"+dataarr[2]+"</td></tr>"; 
	     		row += "<tr><td>Course Type</td><td>"+dataarr[3]+"</td></tr>"; 
	     		row += "<tr><td>Form Id</td><td>"+dataarr[4]+"</td></tr>"; 
	     		row += "<tr><td>Date Of Interview</td><td>"+dataarr[5]+"</td></tr>"; 
	     		row += "<tr><td>Time Of Interview</td><td>"+dataarr[6]+"</td></tr>"; 
	     		row += "<tr><td>Class</td><td>"+dataarr[7]+"</td></tr>"; 
	     		row += "<tr><td>Examiner Name</td><td>"+dataarr[8]+"</td></tr>"; 
	     		row += "<tr><td>Reviewer Name</td><td>"+dataarr[9]+"</td></tr>";
	     		$("#scheduleFormDetails").append(row);
		     	}  
	     	$('#modalInfo').fadeIn("fast");
	    }
	
	});
} 
</script>  
</body>
</html>