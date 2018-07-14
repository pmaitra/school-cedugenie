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

                    <h2 class="panel-title">Interview Result</h2>
                </header>
                <div class="panel-body">
                    <div class="wizard-progress wizard-progress-lg">
                        <div class="steps-progress">
                            <div class="progress-indicator"></div>
                        </div>
                         <c:if test="${admissionDriveStateNew eq 'INTERVIEWSCHEDULED'}">
	                        <ul class="wizard-steps">
	                        	<c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.formSubmission eq true}">
		                            <li id = "driveFormSubmission">
		                             	<a  data-toggle="tab"><span>1</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=FORMSUBMISSION">Form <br> Submission</a> </a>
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
		                            <li id = "driveInterviewSchedule">
		                                <a  data-toggle="tab"><span>2</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=INTERVIEWSCHEDULED">Schedule <br> Interview</a>
		                            </li>
		                         </c:if>
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
		                            <li  id = "driveInterviewResult">
		                            	 <a data-toggle="tab"><span>3</span> Interview <br> Result</a> 
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true}">
		                            <li id= "driveMeritList">
		                            	 <a  data-toggle="tab"><span>4</span> Merit <br> List</a>
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true}">
		                            <li id="drivePayment">
		                            	<a  data-toggle="tab"><span>5</span>Payment</a> 
		                           	</li>
		                        </c:if>
	                        </ul>
	                     </c:if>
	                      <c:if test="${admissionDriveStateNew eq 'INTERVIEWRESULT'}">
	                      	 <ul class="wizard-steps">
	                        	<c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.formSubmission eq true}">
	                            	<li id = "driveFormSubmission">
		                             	<a  data-toggle="tab"><span>1</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=FORMSUBMISSION">Form <br> Submission</a> </a>
		                           	</li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
		                            <li id = "driveInterviewSchedule">
		                                <a  data-toggle="tab"><span>2</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=INTERVIEWSCHEDULED">Schedule <br> Interview</a>
		                            </li>
		                         </c:if>
		                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
		                            <li  id = "driveInterviewResult">
		                            	 <a data-toggle="tab"><span>3</span>Interview <br> Result</a> 
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true}">
		                            <li id= "driveMeritList">
		                            	 <a  data-toggle="tab"><span>4</span><a  href = "viewMeritList.html?courseClass=<c:out value="${contactForm.courseClass}"/>&driveName=<c:out value="${contactForm.formName}"/>&year=<c:out value="${contactForm.admissionYear}"/>"> Merit <br> List</a></a>
		                            </li>
		                        </c:if>
		                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true}">
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
			                             	<a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>" data-toggle="tab"><span>1</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=FORMSUBMISSION">Form <br> Submission</a> </a>
			                            </li>
			                        </c:if>
			                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
			                            <li id = "driveInterviewSchedule">
			                                <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>" data-toggle="tab"><span>2</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=INTERVIEWSCHEDULED">Schedule <br> Interview</a>
			                            </li>
			                         </c:if>
			                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
			                            <li  id = "driveInterviewResult">
			                            	 <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>" data-toggle="tab"><span>3</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=INTERVIEWRESULT">Interview <br> Result</a> </a>
			                                
			                            </li>
			                        </c:if>
			                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true}">
			                            <li id= "driveMeritList">
			                            	 <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>" data-toggle="tab"><span>4</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>&admissionDriveState=MERITLIST">Merit <br> List</a> </a>
			                               
			                            </li>
			                        </c:if>
			                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true}">
			                            <li id="drivePayment">
			                            	 <a href="finalSelectedCandidate.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>" data-toggle="tab"><span>5</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${contactForm.courseClass}"/>&year=<c:out value="${contactForm.admissionYear}"/>&driveName=<c:out value="${contactForm.formName}"/>">Payment</a> </a>
			                           	</li>
			                        </c:if>
		                        </ul>
	                       </c:if>
                   		</div>

                   
                        <div class="tab-content">
                            <div id="w4-Submission" class="tab-pane ">                                
                            </div>
                            <div id="w4-Interview" class="tab-pane">
                            </div>
                            <div id="w4-Result" class="tab-pane active">
                                <div class="row">
                                    <div class="col-md-12">
                                        <blockquote class="b-thin rounded primary">
                                            <h3>Interview Result</h3>
                                        </blockquote>    
                                    </div>
                                </div>
                                 <c:if test="${admissionDriveState == null}">
	                                 <form:form method="POST" id="aircontent" name="aircontent"  action="newAddInterviewResult.html">
		                                <div class="row well well-sm">
		                                   <div class="row">
		                                    <div class="col-md-3">                                   
		                                    	<div class="form-group">
		                                            <label class="control-label">Academic Year</label>
		                                            <input type="text" class="form-control" value="${StreamClassYearCourse.admissionFormYear}" readonly>
		                                        </div> 
		                                        <div class="form-group">
		                                            <label class="control-label">Course Name</label>
		                                            <input type="text" class="form-control"  value="${StreamClassYearCourse.courseName}" readonly>
		                                        </div>                                          
		                                        
		                                    </div>
		                                    <div class="col-md-3">
		                                        <div class="form-group">
		                                            <label class="control-label">Course Type</label>
		                                            <input type="text" class="form-control"  value="${StreamClassYearCourse.courseType}" readonly>
		                                        </div> 
		                                        
		                                    </div>
		                                    <div class="col-md-3"> 
		                                       <div class="form-group">
		                                            <label class="control-label">Form ID<span class="required" aria-required="true">*</span></label>
		                                            <select class="form-control" name="formId" id="strFormID">
		                                                <option value="">Please select</option>
		                                                <c:forEach var="interviewSchedule" items="${FORMID}">										
															<option value="<c:out value="${interviewSchedule.formId}"/>"><c:out value="${interviewSchedule.formId}"/></option>
														</c:forEach>
		                       	                     </select>
		                                        </div>
		                                       <div class="form-group">
		                                            <label class="control-label">Class</label>
		                                            <input type="text" class="form-control" value="${FORMID[0].courseClass}" readonly>
		                                            <input type="hidden" name="courseClass" value="<c:out value="${FORMID[0].courseClass}"/>" id="strCourseClass"/>
													<input type="hidden" name="formName" value="<c:out value="${contactForm.formName}"/>" id="strFormName"/>
													<input type="hidden" name="admissionYear" value="<c:out value="${contactForm.admissionYear}"/>" id="strAdmissionYear"/>
		                                        </div>                                         
		                                    </div>
		                                    <div class="col-md-3">
		                                        <div class="form-group">
		                                            <label class="control-label">Name</label>
		                                            <input type="text" class="form-control" name="name" id="name" readonly="readonly">
		                                        </div>
		                                    </div>
		                                    </div>
		                                    <br>
		                                    <div class="row">                                    
		                                        <div class="col-md-4">
		                                            <div class="form-group">
		                                                <label class="control-label">Comment<span class="required" aria-required="true">*</span></label>
		                                                <textarea maxlength="140" data-plugin-maxlength="" rows="3" class="form-control" id="strComment" name="comment"></textarea>
		                                            </div>
		                                            <div class="form-group">
		                                                <label class="control-label">Decision<span class="required" aria-required="true">*</span></label>
		                                                <div style="margin-top: 5px;" class="form-group">
		                                                    <label class="radio-inline radio-primary"> 
		                                                        <input type="radio" checked="" value="SELECTED" id="strStatus" name="formStatus"> Selected 
		                                                    </label>
		                                                    <label class="radio-inline radio-primary"> 
		                                                        <input type="radio" value="NOTSELECTED" id="strStatus" name="formStatus"> Not Selected 
		                                                    </label>
		                                                    <label class="radio-inline radio-primary"> 
		                                                        <input type="radio" value="REVIEW" id="strStatus" name="formStatus"> Review 
		                                                    </label>
		                                                </div>
		                                            </div>
		                                        </div>
		                                        <div class="col-md-8">
		                                            <table class="table table-bordered table-striped mb-none dataTable" id = "dataTable">
		                                                <thead>
		                                                    <tr>
		                                                        <th>Subject<span class="required" aria-required="true">*</span></th>
		                                                        <th>Marks<span class="required" aria-required="true">*</span></th>
		                                                        <th>Delete</th>
		                                                    </tr>
		                                                </thead>
		                                                <tbody>
		                                                    <tr>
		                                                        <td><input type="text" class="form-control" name="subject"></td>
		                                                        <td><input type="text" class="form-control" name="marks"></td>
		                                                        <td><a href="#" class="on-default" onclick="deleteRow(this);"><i class="fa fa-minus-square"></i></a></td>
		                                                    </tr>
		                                                </tbody>
		                                                <tfoot>
		                                                    <tr>
		                                                        <td></td>
		                                                        <td></td>
		                                                        <td><button class="btn btn-xs btn-primary" type="button" onclick="addRow();">Add</button></td>
		                                                    </tr>
		                                                </tfoot>
		                                            </table>                                                        
		                                        </div>
		                                    </div>
		                                    <div class="col-md-12">&nbsp;</div>
		                                    <div class="col-md-12">
		                                    <input type="hidden" name="control" value="SubmitInterviewResult">
												<button class="btn btn-success" type="submit" onclick = "return validate()">Submit</button>
												<a href = "#interviewResultToMeritList" class="mb-xs mt-xs mr-xs modal-basic btn btn-primary">Next</a>
											</div>
											<div id="interviewResultToMeritList" class="modal-block modal-header-color modal-block-info mfp-hide">
						                     	<section class="panel">
							                         <header class="panel-heading">
							                             <h2 class="panel-title">Confirmation</h2>
							                         </header>
							                         <div class="panel-body">
							                             <div class="">
							                                 <p>Are You Sure to go to Merit List Page?</p>
							                             </div>
							                         </div>
							                         <footer class="panel-footer">
					                                    <a href = "viewMeritList.html?courseClass=<c:out value="${contactForm.courseClass}"/>&driveName=<c:out value="${contactForm.formName}"/>&year=<c:out value="${contactForm.admissionYear}"/>" class="btn btn-success">OK</a>
					                                 	<button class="btn btn-danger modal-dismiss">Cancel</button>
							                         </footer>
						                     	</section>
						                 	</div>
		                                </div>
		                             </form:form>
		                        </c:if>
                                <div class="row">   
                                  <input type="hidden" class="form-control" id = "driveStatus" name = "driveStatus" value="${admissionDriveState}" />
                                   	<input type="hidden" class="form-control" id = "driveStatusNew" name = "driveStatusNew" value="${admissionDriveStateNew}" /> 
                                    <div class="col-md-12">&nbsp;</div>
                                    <form:form method="GET" action="editSubmittedInterviewResult.html" modelAttribute="FORM">
                                    <div class="col-md-12">
                                        <blockquote class="b-thin rounded primary">
                                            <h3>Interview Result List</h3>
                                        </blockquote>
                                        
	                                        <table class="table table-bordered table-striped mb-none dataTable no-footer" id="datatable-tabletools">
	                                            <thead>
	                                                <tr>
	                                                	<th>Select</th>
	                                                    <th>Form ID</th>
	                                                    <th>Drive Name</th>
	                                                    <th>Candidate Name</th>
	                                                    <th>Score</th>
	                                                    <th>Status</th>
	                                                   <!--  <th>Action</th> -->
	                                                </tr>
	                                            </thead>
	                                            <tbody>
	                                            	<c:forEach var="formSubmission" items="${interviewResultListFromDb}">
			                                                <tr>
			                                                 	<td><input type="radio" name="formId" id="formid" value="${formSubmission.formId}"/></td>
			                                                    <td><c:out value="${formSubmission.formId}"/></td>
																<td><c:out value="${formSubmission.formName}"/></td>
																<td><c:out value="${formSubmission.candidateFirstName}"/> <c:out value="${formSubmission.candidateMiddleName}"/> <c:out value="${formSubmission.candidateLastName}"/></td>
																<td><c:out value="${formSubmission.totalMarks}"/></td>
																<td><c:out value="${formSubmission.formStatus}"/>
																	<%-- <input type="hidden" name="formId" value="<c:out value="${formSubmission.formId}"/>"> --%>
	                                                       			<input type="hidden" name="courseClass${formSubmission.formId}" id = "courseClass${formSubmission.formId}" value="<c:out value="${formSubmission.courseClass}"/>">
																	<input type="hidden" name="admissionYear${formSubmission.formId}" id="admissionYear${formSubmission.formId}" value="<c:out value="${formSubmission.admissionYear}"/>"/>
																	<input type="hidden" name="formName${formSubmission.formId}" id="formName${formSubmission.formId}" value="<c:out value="${formSubmission.formName}" />"/>
																	<!-- <input type="hidden" name="pageName"  value="previousSubmittedFormList" /> -->
	                                                       			 <%-- <button class="btn btn-primary" type="button"  onclick = "showSubmittedFormDetails('${formSubmission.formId}','${formSubmission.courseClass}','${formSubmission.admissionYear}','${formSubmission.formName}')">Details</button> --%>
	                                                       		   <%-- a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic btn btn-info" onclick = "showSubmittedFormDetails('${formSubmission.formId}','${formSubmission.courseClass}','${formSubmission.admissionYear}','${formSubmission.formName}')">Details</a> --%>
	                                                  		   		<!-- <button class="btn btn-xs btn-primary" type="button">Edit</button> -->
																</td>
			                                                </tr> 
			                                             </c:forEach>
	                                                                                        
	                                            </tbody>
	                                        </table>
	                                      
                                    </div>  
                                    <div class="col-md-12">&nbsp;</div>
                                    <c:if test="${admissionDriveState ne 'DONE'}">
	                                    <div class="col-md-12">
	                                    <!-- <input type="hidden" name="control" value="SubmitInterviewResult"> -->
											<button class="btn btn-xs btn-primary pull-right" type="submit">EDIT</button>
										</div>
									</c:if>
                                   </form:form>                                                      
                                </div>
                            </div>
                            <div id="w4-Merit" class="tab-pane">
                               
                            </div>
                            <div id="w4-Payment" class="tab-pane">   
                            </div>
                        </div>
                </div>
              
            </section>
        </div>
    </div>



<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script> 
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/admission/submitInterviewResult.js"></script>
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