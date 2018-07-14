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
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
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
				
				                    <h2 class="panel-title">Merit List</h2>
				                </header>
				                 <div class="panel-body">
				                    <div class="wizard-progress wizard-progress-lg">
				                        <div class="steps-progress">
				                            <div class="progress-indicator"></div>
	                        </div>
							 <c:if test="${admissionDriveStateNew eq 'INTERVIEWRESULT'}">
	                      	 <ul class="wizard-steps">
	                        	<c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.formSubmission eq true}">
		                            <li id = "driveFormSubmission">
		                             <a  data-toggle="tab"><span>1</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${StreamClassYearCourse.courseClass}"/>&year=<c:out value="${StreamClassYearCourse.admissionFormYear}"/>&driveName=<c:out value="${strDriveName}"/>&admissionDriveState=FORMSUBMISSION">Form <br> Submission</a> </a>
		                               <%--  <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>" data-toggle="tab"><span>1</span>Form <br> Submission</a> --%>
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
		                            <li id = "driveInterviewSchedule">
		                                <%-- <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=SCHEDULEINTERVIEW"  data-toggle="tab"><span>2</span>Schedule <br> Interview</a> --%>
		                            	<a  data-toggle="tab"><span>2</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${StreamClassYearCourse.courseClass}"/>&year=<c:out value="${StreamClassYearCourse.admissionFormYear}"/>&driveName=<c:out value="${strDriveName}"/>&admissionDriveState=INTERVIEWSCHEDULED">Schedule <br> Interview</a>
		                            </li>
		                         </c:if>
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
		                            <li  id = "driveInterviewResult">
		                            	 <a data-toggle="tab"><span>3</span><a href="admissionOnProcess.html?courseClass=<c:out value="${StreamClassYearCourse.courseClass}"/>&year=<c:out value="${StreamClassYearCourse.admissionFormYear}"/>&driveName=<c:out value="${strDriveName}"/>&admissionDriveState=INTERVIEWRESULT"> Interview <br> Result</a> </a>
		                                <%-- <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=INTERVIEWRESULT"  data-toggle="tab"><span>3</span>Interview <br> Result</a> --%>
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
		                            <li id= "driveMeritList">
		                            	 <a  data-toggle="tab"><span>4</span> Merit <br> List</a>
		                               
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq false}">
			                            <li  id= "driveMeritList">
			                                <a  data-toggle="tab"><span>2</span> Merit <br> List</a> 
			                            </li>
			                    </c:if>
			                    <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq false}">
			                            <li id="drivePayment">
			                                <a data-toggle="tab"><span>3</span>Payment</a>
			                            </li>
			                    </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
		                            <li id="drivePayment">
		                            	 <a  data-toggle="tab"><span>5</span>Payment</a> 
		                          
		                            </li>
		                        </c:if>
	                        </ul>
	                      </c:if>
	                        <c:if test="${admissionDriveStateNew eq 'MERITLIST'}">
		                        <ul class="wizard-steps">
		                        	<c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.formSubmission eq true}">
			                            <li id = "driveFormSubmission">
			                               <%--  <a href="admissionOnProcess.html?courseClass=<c:out value="${admissionForm.courseClass}"/>&year=<c:out value="${ admissionForm.admissionFormYear}"/>&driveName=<c:out value="${admissionForm.admissionDriveName}"/>&status='FORMSUBMISSION'"  data-toggle="tab"></a> --%>
			                           		 <a  data-toggle="tab"><span>1</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${StreamClassYearCourse.courseClass}"/>&year=<c:out value="${StreamClassYearCourse.admissionFormYear}"/>&driveName=<c:out value="${strDriveName}"/>&admissionDriveState=FORMSUBMISSION">Form <br> Submission</a> </a>
			                            </li>
			                        </c:if>
			                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
			                            <li id = "driveInterviewSchedule">
			                                <a  data-toggle="tab"><span>2</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${StreamClassYearCourse.courseClass}"/>&year=<c:out value="${StreamClassYearCourse.admissionFormYear}"/>&driveName=<c:out value="${strDriveName}"/>&admissionDriveState=INTERVIEWSCHEDULED">Schedule <br> Interview</a> </a>
			                            </li>
			                        </c:if>
			                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
			                            <li id = "driveInterviewResult">
			                               <a  data-toggle="tab"><span>3</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${StreamClassYearCourse.courseClass}"/>&year=<c:out value="${StreamClassYearCourse.admissionFormYear}"/>&driveName=<c:out value="${strDriveName}"/>&admissionDriveState=INTERVIEWRESULTT">Interview <br> Result</a> </a>
			                            </li>
			                         </c:if>
			                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true  && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true}">
			                            <li  id= "driveMeritList">
			                                <a  data-toggle="tab"><span>4</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${StreamClassYearCourse.courseClass}"/>&year=<c:out value="${StreamClassYearCourse.admissionFormYear}"/>&driveName=<c:out value="${strDriveName}"/>&admissionDriveState=MERITLIST">Merit <br> List</a> </a>
			                            </li>
			                        </c:if>
			                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq false && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true}">
			                            <li  id= "driveMeritList">
			                                <a  data-toggle="tab"><span>2</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${StreamClassYearCourse.courseClass}"/>&year=<c:out value="${StreamClassYearCourse.admissionFormYear}"/>&driveName=<c:out value="${strDriveName}"/>&admissionDriveState=MERITLIST">Merit <br> List</a> </a>
			                            </li>
			                        </c:if>
			                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq false && StreamClassYearCourse.customizedAdmissionProcess.payment eq true}">
			                            <li id="drivePayment">
			                                <a data-toggle="tab"><span>3</span> <a href="finalSelectedCandidate.html?courseClass=<c:out value="${StreamClassYearCourse.courseClass}"/>&year=<c:out value="${StreamClassYearCourse.admissionFormYear}"/>&driveName=<c:out value="${strDriveName}"/>">Payment</a> </a>
			                            </li>
			                        </c:if>
			                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
			                            <li id="drivePayment">
			                                <a  data-toggle="tab"><span>5</span> <a href="finalSelectedCandidate.html?courseClass=<c:out value="${StreamClassYearCourse.courseClass}"/>&year=<c:out value="${StreamClassYearCourse.admissionFormYear}"/>&driveName=<c:out value="${strDriveName}"/>">Payment</a> </a>
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
						                                        <h3>View Merit List</h3>
						                                    </blockquote>
						                                    </div>
						                                </div>
						                                

 														<c:if test="${admissionDriveState == null}">
					                                    <form:form method="POST" id="vmlform" action="paymentDateSetUp.html" modelAttribute="FORM">
					                                   	<table class="table table-bordered table-striped mb-none dataTable no-footer" id="datatable-tabletools">
				                                            <thead>
				                                                <tr>
				                                                 <!--   <th><input type="checkbox" onClick="toggle(this, 'formId')" /></th> -->
				                                                 	<th>Select</th>
																	<th>FORM ID</th>
																	<th>DRIVE NAME</th>
																	<th>CANDIDATE NAME</th>
																	<c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
																	<th>SCORE</th>
																	</c:if>
				                                                </tr>
				                                            </thead>
				                                            <tbody>
				                                            	<c:forEach var="interviewResult" items="${viewAdmissionMeritList}">		
																	<tr>
																		<td><input type="checkbox" name="formId" id="formid" value="<c:out value="${interviewResult.formId}"/>"/></td>
																		<td><c:out value="${interviewResult.formId}"/></td>
																		<td><c:out value="${interviewResult.formName}"/></td>
																		<td><c:out value="${interviewResult.candidateFirstName}"/><c:out value="${interviewResult.candidateMiddleName}"/> <c:out value="${interviewResult.candidateLastName}"/> 
																			<input type="hidden" name="courseClass${interviewResult.formId}" id="courseClass${interviewResult.formId}" value="<c:out value="${StreamClassYearCourse.courseClass}"/>"/>
																			<input type="hidden" name="admissionYear${interviewResult.formId}" id="admissionYear${interviewResult.formId}" value="<c:out value="${StreamClassYearCourse.admissionFormYear}"/>" />
																			<input type="hidden" name="formName${interviewResult.formId}" id="formName${interviewResult.formId}" value="<c:out value="${interviewResult.formName}"/>" />          
																		</td>
																		<c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
																		<td><c:out value="${interviewResult.totalMarks}"/>
																		</td>
																		</c:if>																			
																	</tr>
																</c:forEach>																		
				                                            </tbody>
						                                   </table>
						                                    <c:if test="${viewAdmissionMeritList.size() != 0}"> 
						                                 <%-- <%
						                                 String formId=request.getParameter("formId"); 
						                                 System.out.println("formId=="+formId);
						                                 if(formId != null){%> --%>
						                                    <div class="col-md-3">                                   
						                                    	<div class="form-group">
						                                            <label class="control-label">Fees Submission Date<span class="required" aria-required="true">*</span></label>
						                                            <div class="input-group">
						                                                <span class="input-group-addon">
						                                                    <i class="fa fa-calendar"></i>
						                                                </span>
						                                                <input type="text" class="form-control" name="lastFeesSubmissionDate"  id="strDateOfSubmissionOffees" data-plugin-datepicker="" data-date-start-date="0d" required>
						                                            </div>
					                                        	</div>
						                                    </div>
						                                    <%-- <% }%> --%>
						                                    <div class="col-md-12">&nbsp;</div>
						                                    <div class="col-md-12">
																<button class="btn btn-success" name="submit" type="submit">Submit</button>
																<a  href = "#meritListToFinalSelection" class="mb-xs mt-xs mr-xs modal-basic btn btn-primary">Next</a>
															</div>
						                                 </c:if>
						                                 <c:if test="${viewAdmissionMeritList.size() == 0}">
						                                 	<a  href = "#meritListToFinalSelection" class="mb-xs mt-xs mr-xs modal-basic btn btn-primary">Next</a>
						                                 </c:if>
														<div id="meritListToFinalSelection" class="modal-block modal-header-color modal-block-info mfp-hide">
									                     	<section class="panel">
										                         <header class="panel-heading">
										                             <h2 class="panel-title">Confirmation</h2>
										                         </header>
										                         <div class="panel-body">
										                             <div class="">
										                                 <p>Are You Sure to go to Final Selection Process?</p>
										                             </div>
										                         </div>
										                         <footer class="panel-footer">
								                                    <a href = "finalSelectedCandidate.html?courseClass=<c:out value="${StreamClassYearCourse.courseClass}"/>&driveName=<c:out value="${strDriveName}"/>&year=<c:out value="${StreamClassYearCourse.admissionFormYear}"/>" class="btn btn-success">OK</a>
								                                 	<button class="btn btn-danger modal-dismiss">Cancel</button>
										                         </footer>
									                     	</section>
									                 	</div>
					                           </form:form> 
					                           </c:if>
					                           
					                 <div class="row">   
	                                    <div class="col-md-12">&nbsp;</div>
	                                      <input type="hidden" class="form-control" id = "driveStatus" name = "driveStatus" value="${admissionDriveState}" />
	                                      	<input type="hidden" class="form-control" id = "driveStatusNew" name = "driveStatusNew" value="${admissionDriveStateNew}" />
		                                    <div class="col-md-12">
		                                        <blockquote class="b-thin rounded primary">
		                                            <h3></h3>
		                                        </blockquote>
		                                        <table class="table table-bordered table-striped mb-none dataTable no-footer" id="datatable-tabletools">
		                                            <thead>
		                                                <tr>
		                                                	<!--  <th>Select</th> -->
			                                                <th>Form ID</th>
															<th>Drive Name</th>
															<th>Candidate Name</th>
															<th>Admission Year</th>
															<c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
																<th>Total Marks</th>
															</c:if>
		                                                </tr>
		                                            </thead>
		                                            <tbody>
		                                                <c:forEach var="interviewSchedule" items="${viewAllStudentMeritList}">
															<tr>
																<%-- <td><input type="hidden" name="formId" id="formid" value="<c:out value="${interviewSchedule.formId}"/>"/></td> --%>
																<td><c:out value="${interviewSchedule.formId}"/>
																	<input type="hidden" name="formId" id="formid" value="<c:out value="${interviewSchedule.formId}"/>"/>
																</td>
																<td><c:out value="${interviewSchedule.formName}"/></td>
																<td><c:out value="${interviewSchedule.candidateFirstName}"/> <c:out value="${interviewSchedule.candidateMiddleName}"/> <c:out value="${interviewSchedule.candidateLastName}"/> </td>
																<td><c:out value="${interviewSchedule.admissionYear}"/>
																	<input type="hidden" name="courseClass" value="<c:out value="${formSubmission.courseClass}"/>">
																		<input type="hidden" name="admissionYear" id="admissionYear" value="<c:out value="${formSubmission.admissionYear}"/>"/>
																		<input type="hidden" name="formName" id="formName" value="<c:out value="${formSubmission.formName}" />"/>
																</td>
																<c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
																	<td><c:out value="${interviewSchedule.totalMarks}"/>	
																	</td>
																</c:if>
															</tr>
														</c:forEach>                                                
		                                            </tbody>
		                                        </table>   
		                                    </div>                                                        
                              			</div>
					                  </div>
					                 </div>
                                   </div>   
                                   </section>
                                  
                                   </div>                                                  
                                </div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script> 
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

</script>
</body>
</html>