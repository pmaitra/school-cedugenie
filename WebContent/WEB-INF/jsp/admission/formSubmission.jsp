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
                    <h2 class="panel-title">Form Submission</h2>
                </header>
                <div class="panel-body">
                    <div class="wizard-progress wizard-progress-lg">
                        <div class="steps-progress">
                            <div class="progress-indicator"></div>
                        </div>
                        <c:if test="${admissionDriveStateNew eq 'NOTSTARTED'}">
	                        <ul class="wizard-steps">
	                        	 <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.formSubmission eq true}">
		                           <!--  <li  class="active"> -->
		                           <li id = "driveFormSubmission">
		                             <a data-toggle="tab"><span>1</span>Form <br> Submission</a>
	                            </li>
	                            </c:if>
	                            <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
		                            <li id = "driveInterviewSchedule">
		                            	<a  data-toggle="tab"><span>2</span> Schedule <br> Interview</a>
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
		                            <li id = "driveInterviewResult">
		                            	 <a data-toggle="tab"><span>3</span> Interview <br> Result</a> 
		                
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true}">
		                            <li id= "driveMeritList">
		                            	 <a  data-toggle="tab"><span>4</span> Merit <br> List</a> 
		                            </li>
		                        </c:if> 
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq false}">
		                            <li id= "driveMeritList">
		                            	 <a  data-toggle="tab"><span>2</span> Merit <br> List</a> 
		                              
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true && StreamClassYearCourse.customizedAdmissionProcess.payment eq true}">
		                            <li id="drivePayment">
		                             <a  data-toggle="tab"><span>5</span> Payment</a> 
		                             
		                            </li>
		                        </c:if>
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq false }">
		                            <li id="drivePayment">
		                             <a data-toggle="tab"><span>3</span> Payment</a> 
		                             
		                            </li>
		                        </c:if>
	                        </ul>
	                    </c:if>
	                      <c:if test="${admissionDriveStateNew eq 'FORMSUBMITTED'}">
	                        <ul class="wizard-steps">
	                        	 <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.formSubmission eq true}">
		                           <!--  <li  class="active"> -->
		                           <li id = "driveFormSubmission">
		                             <a data-toggle="tab"><span>1</span><a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&admissionDriveState=FORMSUBMISSION">Form <br> Submission</a></a> 
		                             
		                            </li>
	                            </c:if>
	                            <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
		                            <li id = "driveInterviewSchedule">
		                            	<a  data-toggle="tab"><span>2</span><a  href = "scheduleInterview.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>"> Schedule <br> Interview</a></a>
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
		                            <li id = "driveInterviewResult">
		                            	 <a data-toggle="tab"><span>3</span> Interview <br> Result</a> 
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true}">
		                            <li id= "driveMeritList">
		                            	 <a  data-toggle="tab"><span>4</span> Merit <br> List</a> 
		                            </li>
		                        </c:if> 
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq false}">
		                            <li id= "driveMeritList">
		                            	 <a  data-toggle="tab"><span>2</span> Merit <br> List</a> 
		                              
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true && StreamClassYearCourse.customizedAdmissionProcess.payment eq true}">
		                            <li id="drivePayment">
		                             <a  data-toggle="tab"><span>5</span> Payment</a> 
		                             
		                            </li>
		                        </c:if>
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq false }">
		                            <li id="drivePayment">
		                             <a data-toggle="tab"><span>3</span> Payment</a> 
		                             
		                            </li>
		                        </c:if>
	                        </ul>
	                    </c:if>
	                    <c:if test="${admissionDriveStateNew eq 'INTERVIEWSCHEDULED'}">
	                        <ul class="wizard-steps">
	                        	 <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.formSubmission eq true}">
		                           <!--  <li  class="active"> -->
		                           <li id = "driveFormSubmission">
		                             <a data-toggle="tab"><span>1</span><a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&admissionDriveState=FORMSUBMISSION">Form <br> Submission</a></a> 
		                             
		                            </li>
	                            </c:if>
	                            <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
		                            <li id = "driveInterviewSchedule">
		                            	<a  data-toggle="tab"><span>2</span><a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&admissionDriveState=INTERVIEWSCHEDULED"> Schedule <br> Interview</a></a>
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
		                            <li id = "driveInterviewResult">
		                            	 <a data-toggle="tab"><span>3</span><a  href = "addInterviewResult.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>"> Interview <br> Result</a> </a>
		                
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true}">
		                            <li id= "driveMeritList">
		                            	 <a  data-toggle="tab"><span>4</span> Merit <br> List</a> 
		                            </li>
		                        </c:if> 
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq false}">
		                            <li id= "driveMeritList">
		                            	 <a  data-toggle="tab"><span>2</span> Merit <br> List</a> 
		                              
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true && StreamClassYearCourse.customizedAdmissionProcess.payment eq true}">
		                            <li id="drivePayment">
		                             <a  data-toggle="tab"><span>5</span> Payment</a> 
		                             
		                            </li>
		                        </c:if>
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq false }">
		                            <li id="drivePayment">
		                             <a data-toggle="tab"><span>3</span> Payment</a> 
		                             
		                            </li>
		                        </c:if>
	                        </ul>
	                    </c:if>
	                    <c:if test="${admissionDriveStateNew eq 'INTERVIEWRESULT'}">
	                        <ul class="wizard-steps">
	                        	 <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.formSubmission eq true}">
		                           <!--  <li  class="active"> -->
		                           <li id = "driveFormSubmission">
		                             <a data-toggle="tab"><span>1</span><a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&admissionDriveState=FORMSUBMISSION">Form <br> Submission</a></a> 
		                             
		                            </li>
	                            </c:if>
	                            <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
		                            <li id = "driveInterviewSchedule">
		                            	<a  data-toggle="tab"><span>2</span><a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&admissionDriveState=INTERVIEWSCHEDULED"> Schedule <br> Interview</a></a>
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
		                            <li id = "driveInterviewResult">
		                            	 <a data-toggle="tab"><span>3</span><a  href = "admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/> &admissionDriveState=INTERVIEWRESULT" >Interview <br> Result</a> </a>
		                
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true}">
		                            <li id= "driveMeritList">
		                            	 <a  data-toggle="tab"><span>4</span><a  href = "viewMeritList.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>" > Merit <br> List</a> </a>
		                            </li>
		                        </c:if> 
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq false}">
		                            <li id= "driveMeritList">
		                            	 <a  data-toggle="tab"><span>2</span><a  href = "viewMeritList.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>">Merit <br> List</a> </a>
		                              
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true && StreamClassYearCourse.customizedAdmissionProcess.payment eq true}">
		                            <li id="drivePayment">
		                             <a  data-toggle="tab"><span>5</span> Payment</a> 
		                             
		                            </li>
		                        </c:if>
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq false }">
		                            <li id="drivePayment">
		                             <a data-toggle="tab"><span>3</span> Payment</a> 
		                             
		                            </li>
		                        </c:if>
	                        </ul>
	                    </c:if>
	                     <c:if test="${admissionDriveStateNew eq 'MERITLIST'}">
	                              <ul class="wizard-steps">
                        	 <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.formSubmission eq true}">
	                           <!--  <li  class="active"> -->
	                           <li id = "driveFormSubmission">
	                             <a data-toggle="tab"><span>1</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&admissionDriveState=FORMSUBMISSION">Form <br> Submission</a> </a>
	                             
	                            </li>
                            </c:if>
                            <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
	                            <li id = "driveInterviewSchedule">
	                            	<a  data-toggle="tab"><span>2</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&admissionDriveState=INTERVIEWSCHEDULED">Schedule <br> Interview</a>
	                            </li>
	                        </c:if>
	                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
	                            <li id = "driveInterviewResult">
	                            	 <a data-toggle="tab"><span>3</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&admissionDriveState=INTERVIEWRESULT">Interview <br> Result</a> </a>
	                
	                            </li>
	                        </c:if>
	                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true}">
	                            <li id= "driveMeritList">
	                            	 <a  data-toggle="tab"><span>4</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&admissionDriveState=MERITLIST">Merit <br> List</a> </a>
	                            </li>
	                        </c:if> 
	                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq false}">
	                            <li id= "driveMeritList">
	                            	 <a  data-toggle="tab"><span>2</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&admissionDriveState=MERITLIST">Merit <br> List</a> </a>
	                              
	                            </li>
	                        </c:if>
	                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true && StreamClassYearCourse.customizedAdmissionProcess.payment eq true}">
	                            <li id="drivePayment">
	                             <a  data-toggle="tab"><span>5</span><a  href = "finalSelectedCandidate.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>">Payment</a> </a>
	                            </li>
	                        </c:if>
	                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq false }">
	                            <li id="drivePayment">
	                             <a data-toggle="tab"><span>3</span> <a  href = "finalSelectedCandidate.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>">Payment</a> </a>
	                            </li>
	                        </c:if>
                        </ul>
	                 </c:if>
                    </div>

                  
                        <div class="tab-content">
                            <div id="w4-Submission" class="tab-pane active">
                                <div class="row">
                                    <div class="col-md-12">
                                    <blockquote class="b-thin rounded primary">
                                        <h3>Form Submission</h3>
                                    </blockquote>
                                    </div>
                                </div>
                                <c:if test="${admissionDriveState == null}">
                                 <form modelAttribute="FORM" action="submitNewAdmissionForm.html" id="safcontents" name="safcontents" enctype="multipart/form-data"  method = "POST">
	                                <div class="row well well-sm">
	                                	<input type="hidden" name="control" value="submissionForm"/>
										<input type="hidden" name="decision" value="new"/>
										<input type="hidden" name="type" value="admission"/>
			
										<input type="hidden" id="admissionDriveStartDate" name="admissionDriveStartDate" value="${StreamClassYearCourse.admissionDriveStartDate}"/>
										<input type="hidden" id="admissionDriveExpectedEndDate" name="admissionDriveExpectedEndDate" value="${StreamClassYearCourse.admissionDriveExpectedEndDate}"/>
	                                    <div class="col-md-3">
	                                    	 <div class="form-group">
	                                            <label class="control-label">Academic Year</label>
	                                            <input type="text" class="form-control"  value="${StreamClassYearCourse.admissionFormYear}" readonly>
	                                        </div> 
	                                        <div class="form-group">
	                                            <label class="control-label">Standard Name</label>
	                                            <input type="text" class="form-control" value="${StreamClassYearCourse.courseName}" readonly>
	                                        </div> 
	                                        <div class="form-group">
	                                            <label class="control-label">Course Type</label>
	                                            <input type="text" class="form-control" value="${StreamClassYearCourse.courseType}" readonly>
	                                        </div> 
	                                    </div>
	                                    <div class="col-md-3">
	                                        <div class="form-group">
	                                            <label class="control-label">Form ID<span class="required" aria-required="true">*</span></label>
	                                            <select class="form-control" name="formId" id="formID" required>
	                                                <option value="NULL">Please Select</option>
	                                            	<c:forEach var="formSubmission" items="${FORMLIST}">										
														<option value="<c:out value="${formSubmission.formId}"/>"><c:out value="${formSubmission.formId}"/></option>
													</c:forEach>
												</select>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Date Of Submission<span class="required" aria-required="true">*</span></label>
	                                             <div class="input-group">
	                                                <span class="input-group-addon">
	                                                    <i class="fa fa-calendar"></i>
	                                                </span>
	                                                <input type="text" class="form-control" id="formSubmissionDate" name="formSubmissionDate" data-plugin-datepicker="" data-date-start-date="0d" required>
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Student First Name<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="candidateFirstName" id="candidateFirstName" placeholder="First Name" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" required>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Student Middle Name</label>
	                                            <input type="text" class="form-control" name="candidateMiddleName" id="candidateMiddleName" placeholder="Middle Name" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$">
	                                        </div>
	                                    </div>
	                                    <div class="col-md-3">                                                  
	                                         <div class="form-group">
	                                            <label class="control-label">Student Last Name<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="candidateLastName" id="candidateLastName" placeholder="Last Name" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" required>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Gender<span class="required" aria-required="true">*</span></label>
	                                            <div style="margin-top: 5px;" class="form-group">
	                                            <label class="radio-inline radio-primary"> 
	                                                <input type="radio" checked="" value="M" id="gender"  name="gender"> Male 
	                                            </label>
	                                            <label class="radio-inline radio-primary"> 
	                                                <input type="radio" value="F" id="gender" name="gender"> Female 
	                                            </label>
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Date Of Birth<span class="required" aria-required="true">*</span></label>
	                                             <div class="input-group">
	                                                <span class="input-group-addon">
	                                                    <i class="fa fa-calendar"></i>
	                                                </span>
	                                                <input type="text" class="form-control datepicker" id="dateOfBirth" name="dateOfBirth"  data-plugin-datepicker="" data-date-end-date="0d" required>
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Category<span class="required" aria-required="true">*</span></label>
	                                            <select id="category" name="category" class="form-control" required>
	                                                <option value="">Please select</option>
	                                              	  <c:forEach var="cat" items="${socialCategoryList}" >
																<option value="${cat.socialCategoryName}">${cat.socialCategoryName}</option>
														</c:forEach>
	                                            </select>
	                                        
	                                        </div>
	                                    </div>
	                                    <div class="col-md-3">
	                                         <div class="form-group">
	                                            <label class="control-label">Class</label>
	                                            <input type="text" class="form-control" name="courseClass" value="${FORMLIST[0].courseClass}" readonly />
	                                        	<input type="hidden" name="courseClass" id="courseClass" value="<c:out value="${AdmissionOnProcessClass.courseClass}"/>" /> 
												<input type="hidden" name="admissionYear" id="admissionYear" value="<c:out value="${AdmissionOnProcessClass.admissionYear}"/>" />
												<input type="hidden" name="formName" id="formName" value="<c:out value="${AdmissionOnProcessClass.formName}"/>" />
	                                       	</div>
	                                        <div class="form-group">
	                                            <label class="control-label">Contact Number<span class="required" aria-required="true">*</span></label>
	                                            <div class="input-group">
	                                                <span class="input-group-addon">
	                                                    <i class="fa fa-mobile"></i>
	                                                </span>
	                                             <input  type = "text" class="form-control"  name="candidateContactNo" id="candidateContactNo" pattern="^[1-9]\d*$" required>
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">E-Mail<span class="required" aria-required="true">*</span></label>
	                                            <input type="text"  placeholder="eg.: email@email.com" class="form-control" name="candidateEmail" id="candidateEmail"  pattern="^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$" required>
	                                        </div>
	                                    </div>
	                                    <div class="col-md-12">&nbsp;</div>
	                                    <div class="col-md-12">
											<button class="btn btn-success" type="submit" >Submit</button>
											 <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
 												<a href="#fromSubmissionToInterviewScheduling" class="mb-xs mt-xs mr-xs modal-basic btn btn-primary">Next</a>
											</c:if>
											 <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true &&  StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq false}">
											 	<a href="#fromSubmissionToMeritList" class="mb-xs mt-xs mr-xs modal-basic btn btn-primary">Next</a>
											 </c:if>
										</div>
										<div id="fromSubmissionToInterviewScheduling" class="modal-block modal-header-color modal-block-info mfp-hide">
						                     <section class="panel">
						                         <header class="panel-heading">
						                             <h2 class="panel-title">Confirmation</h2>
						                         </header>
						                         <div class="panel-body">
						                             <div class="">
						                                 <p>Are You Sure to go to Interview Scheduling Process?</p>
						                             </div>
						                         </div>
						                         <footer class="panel-footer">
				                                    <a href = "scheduleInterview.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>" class="btn btn-success">OK</a>
				                                 	<button class="btn btn-danger modal-dismiss">Cancel</button>
						                         </footer>
						                     </section>
						                 </div>
						                 <div id="fromSubmissionToMeritList" class="modal-block modal-header-color modal-block-info mfp-hide">
						                     <section class="panel">
						                         <header class="panel-heading">
						                             <h2 class="panel-title">Confirmation</h2>
						                         </header>
						                         <div class="panel-body">
						                             <div class="">
						                                 <p>Are You Sure to go to Merit List Stage?</p>
						                             </div>
						                         </div>
						                         <footer class="panel-footer">
				                                    <a href = "viewMeritList.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>" class="btn btn-success">OK</a>
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
                                            <h3>Submitted Form List </h3>
                                        </blockquote>
	                                        <table class="table table-bordered table-striped mb-none dataTable no-footer" id="datatable-tabletools">
	                                            <thead>
	                                                <tr>
	                                                	<!-- <th>Select</th> -->
	                                                    <th>Form ID</th>
														<th>Drive Name</th>
														<th>Candidate Name</th>
														<th>Form Submission Date</th>
														<th>Status</th>
														 <th>Actions</th>
	                                                </tr>
	                                            </thead>
	                                            <tbody>
	                                            	<c:forEach var="formSubmission" items="${submittedAdmissionFormList}">
		                                                <tr>
		                                                    <td><c:out value="${formSubmission.formId}"/>
		                                                    <input type="hidden" name="formId" id="formid" value="${formSubmission.formId}"/></td>
															<td><c:out value="${formSubmission.formName}"/></td>
															<td><c:out value="${formSubmission.candidateFirstName}"/> <c:out value="${formSubmission.candidateMiddleName}"/> <c:out value="${formSubmission.candidateLastName}"/></td>
															<td><c:out value="${formSubmission.formSubmissionDate}"/></td>
															<td><c:out value="${formSubmission.formStatus}"/></td>
															<td>
                                                       			<input type="hidden" name="courseClass" value="<c:out value="${formSubmission.courseClass}"/>">
																<input type="hidden" name="admissionYear" id="admissionYear" value="<c:out value="${formSubmission.admissionYear}"/>"/>
																<input type="hidden" name="formName" id="formName" value="<c:out value="${formSubmission.formName}" />"/>
																<!-- <input type="hidden" name="pageName"  value="previousSubmittedFormList" /> -->
                                                       			 <%-- <button class="btn btn-primary" type="button"  onclick = "showSubmittedFormDetails('${formSubmission.formId}','${formSubmission.courseClass}','${formSubmission.admissionYear}','${formSubmission.formName}')">Details</button> --%>
                                                       		   <a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic btn btn-info" onclick = "showSubmittedFormDetails('${formSubmission.formId}','${formSubmission.courseClass}','${formSubmission.admissionYear}','${formSubmission.formName}')">Details</a>
                                                  		    </td>
		                                                </tr> 
		                                             </c:forEach>                                               
	                                            </tbody>
	                                        </table>
                                    </div>                               
                                </div>                                
                            </div>
                            <div id="w4-Interview" class="tab-pane">
                            </div>
                            <div id="w4-Result" class="tab-pane">
                            </div>
                            <div id="w4-Merit" class="tab-pane">
                            </div>
                            <div id="w4-Payment" class="tab-pane">   
                            </div>
                        </div>
                </div>
          
                <div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
                     <section class="panel">
                         <header class="panel-heading">
                             <h2 class="panel-title">Form Details</h2>
                         </header>
                         <div class="panel-body">
                             <table class="table table-bordered table-striped mb-none" id = "selectedFormDetails">
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
<script type="text/javascript" src="/cedugenie/js/admission/generateAdmissionForm.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script src="/cedugenie/assets/custom-caleder/jquery-ui.js" type="text/javascript"></script>
<script type="text/javascript" src="/cedugenie/js/admission/submitNewAdmissionForm.js"></script>
<script>
$(document).ready(function() {
	var state = $('#driveStatus').val();
	var stateNew = $('#driveStatusNew').val();
		
	if(state == 'DONE'){
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

function showSubmittedFormDetails(formId,courseClass,admissionYear,formName)
{	
	$.ajax({
		url: '/cedugenie/viewPreviousAdmissionFormDetails.html',
		dataType: 'json',
		data: "courseClass="+courseClass+"&admissionYear="+admissionYear+"&formName=" +formName+"&formId="+formId,
	    success: function(data) {	 
	    	 $('#selectedFormDetails > tbody').empty();
	     	if(data != null && data!=""){
	     		
	     		var dataarr = data.split(";");	     		
	     		/* document.getElementById("formSubmissionDate").value = dataarr[5];
	     		document.getElementById("candidateFirstName").value = dataarr[6];
	     		document.getElementById("candidateMiddleName").value = dataarr[7];
	     		document.getElementById("candidateLastName").value = dataarr[8];	   */  
	     		var row = "<tbody>";
	     		row += "<tr><td>Academic Year</td><td>"+dataarr[1]+"</td></tr>";
	     		row += "<tr><td>Course Name</td><td>"+dataarr[2]+"</td></tr>"; 
	     		row += "<tr><td>Course Type</td><td>"+dataarr[3]+"</td></tr>"; 
	     		row += "<tr><td>Form Id</td><td>"+dataarr[4]+"</td></tr>"; 
	     		row += "<tr><td>Date Of Submission</td><td>"+dataarr[5]+"</td></tr>"; 
	     		row += "<tr><td>Student's First Name</td><td>"+dataarr[6]+"</td></tr>"; 
	     		row += "<tr><td>Student's Middle Name</td><td>"+dataarr[7]+"</td></tr>"; 
	     		row += "<tr><td>STudent's Last Name</td><td>"+dataarr[8]+"</td></tr>"; 
	     		row += "<tr><td>Class</td><td>"+dataarr[9]+"</td></tr>"; 
	     		row += "<tr><td>Email</td><td>"+dataarr[10]+"</td></tr>"; 
	     		row += "<tr><td>Contact No</td><td>"+dataarr[11]+"</td></tr>"; 
	     		$("#selectedFormDetails").append(row);
		     	}  
	     	$('#modalInfo').fadeIn("fast");
	    }
	
	});
} 
</script>
<script type="text/javascript">
$('.datepicker').datepicker({
    format: 'mm-dd-yyyy',
    endDate: '+0d',
    autoclose: true
});

</script>
</body>
</html>