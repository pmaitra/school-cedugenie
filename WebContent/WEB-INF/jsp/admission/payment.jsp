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

                    <h2 class="panel-title">Payment Process</h2>
                </header>
                <div class="panel-body">
                    <div class="wizard-progress wizard-progress-lg">
                        <div class="steps-progress">
                            <div class="progress-indicator"></div>
                        </div>
                    	 <ul class="wizard-steps">
                        	<c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.formSubmission eq true}">
	                            <li id = "driveFormSubmission">
	                             <a  data-toggle="tab"><span>1</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionDriveState=FORMSUBMISSION">Form <br> Submission</a> </a>
	                               <%--  <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>" data-toggle="tab"><span>1</span>Form <br> Submission</a> --%>
	                            </li>
	                        </c:if>
	                        <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
	                            <li id = "driveInterviewSchedule">
	                                <%-- <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=SCHEDULEINTERVIEW"  data-toggle="tab"><span>2</span>Schedule <br> Interview</a> --%>
	                            	<a  data-toggle="tab"><span>2</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionDriveState=INTERVIEWSCHEDULED">Schedule <br> Interview</a>
	                            </li>
	                        </c:if>
	                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
	                            <li id = "driveInterviewResult">
	                            	 <a data-toggle="tab"><span>3</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionDriveState=INTERVIEWRESULT">Interview <br> Result</a> </a>
	                                <%-- <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=INTERVIEWRESULT"  data-toggle="tab"><span>3</span>Interview <br> Result</a> --%>
	                            </li>
	                         </c:if>
	                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true  && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq false}">
	                            <li id= "driveMeritList">
	                            	 <a  data-toggle="tab"><span>2</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionDriveState=MERITLIST">Merit <br> List</a> </a>
	                               <%--  <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=MERITlIST"  data-toggle="tab"><span>4</span>Merit <br> List</a> --%>
	                            </li>
	                         </c:if>
	                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true}">
	                            <li id= "driveMeritList">
	                            	 <a  data-toggle="tab"><span>4</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionDriveState=MERITLIST">Merit <br> List</a> </a>
	                               <%--  <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=MERITlIST"  data-toggle="tab"><span>4</span>Merit <br> List</a> --%>
	                            </li>
	                         </c:if>
	                         <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true &&  StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq false}">
	                            <li  id="drivePayment">
	                             	<a  data-toggle="tab"><span>3</span> Payment</a>
	                              <%--   <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionStates=PAYMENT"  data-toggle="tab"><span>5</span>Payment</a> --%>
	                            </li>
                            </c:if>
                             <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true && StreamClassYearCourse.customizedAdmissionProcess != null}">
	                            <li  id="drivePayment">
	                             	<a  data-toggle="tab"><span>5</span>Payment</a> 
	                              <%--   <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionStates=PAYMENT"  data-toggle="tab"><span>5</span>Payment</a> --%>
	                            </li>
                            </c:if>
                        </ul>
                    </div>

                    <form:form id="parentForm" method = "POST" action ="completeDrive.html">
                        <div class="tab-content">
                            <div id="w4-Submission" class="tab-pane ">
                            </div>
                            <div id="w4-Interview" class="tab-pane">
                            </div>
                            <div id="w4-Result" class="tab-pane">
                            </div>
                            <div id="w4-Merit" class="tab-pane">
                            </div>
                            <div id="w4-Payment" class="tab-pane active">
                                <div class="row">
                                    <div class="col-md-12">
                                        <blockquote class="b-thin rounded primary">
                                            <h3>Payment</h3>
                                        </blockquote>
                                    </div>
                                </div>
                                <div class="row"> 
                                    <div class="col-md-12">                                        
                                        <table class="table table-bordered table-striped mb-none dataTable no-footer" id="datatable-editable">
                                            <thead>
                                                <tr>
													<!-- <th><input type="radio" disabled="disabled"></th> -->
													<th>Form ID</th>
													<th>Drive Name</th>
													<th>Candidate Name</th>
													<th>Payment Status</th>
													<th>Last Date Of Fees Submission</th>
													<th>Actions</th>
												</tr>
												<c:forEach var="InterviewResult" items="${finalSelectedCandidateList}">
													<tr>
														<td><c:out value="${InterviewResult.formId}"/>
														<input type="hidden" name="formId" id="formid" value="<c:out value="${InterviewResult.formId}"/>"/>
														</td>
														<td><c:out value="${InterviewResult.formName}"/>
															<input type="hidden" name="admissionDriveName" id="admissionDriveName" value="<c:out value="${InterviewResult.formName}" />"/>
														</td>
														<td><c:out value="${InterviewResult.candidateFirstName}"/> <c:out value="${InterviewResult.candidateMiddleName}"/>  <c:out value="${InterviewResult.candidateLastName}"/>
														<td>
															<c:choose>
																<c:when test="${InterviewResult.status == 'ADMITTED'}">
																	<font color="green"><c:out value="${InterviewResult.status}"/></font>
																</c:when>
																<c:when  test="${InterviewResult.formStatus == 'PAID'}">
																	<c:out value="${InterviewResult.formStatus}"/>
																</c:when>
																<c:otherwise>
																	<c:out value="${InterviewResult.category}"/>
																</c:otherwise>
															</c:choose>
														</td>
														<td><c:out value="${InterviewResult.lastFeesSubmissionDate}"/></td>
														<td>
															<input type="hidden" name="courseClass" value="<c:out value="${InterviewResult.courseClass}"/>">
															<input type="hidden" name="admissionYear" id="admissionYear" value="<c:out value="${InterviewResult.admissionYear}"/>"/>
															<input type="hidden" name="formName" id="formName" value="<c:out value="${InterviewResult.formName}" />"/>
															<input type="hidden" name="admissionDrive" id="admissionDrive" value="<c:out value="${InterviewResult.formName}:${InterviewResult.courseClass}:${InterviewResult.admissionYear}"/>" />
															<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic btn btn-info" onclick = "showPaymentDetails('${InterviewResult.formId}','${InterviewResult.courseClass}','${InterviewResult.admissionYear}','${InterviewResult.formName}')">Details</a>
														</td>
													</tr>
												</c:forEach>
				                           </tbody>
                                        </table>
                                    </div>                                                        
                                </div>    
                            </div>
                        </div>
                        <div class="col-md-12">&nbsp;</div>
                          <input type="hidden" class="form-control" id = "driveStatus" name = "driveStatus" value="${admissionDriveState}" />
                           	<input type="hidden" class="form-control" id = "driveStatusNew" name = "driveStatusNew" value="${admissionDriveStateNew}" />     
                            <div class="col-md-12">
                           		<c:if test = "${statusOfAdmission eq null || empty statusOfAdmission || statusOfAdmission ne 'DONE'}">
                           			<c:if test="${finalSelectedCandidateList.size() != 0}">
                           				<a href="#completeDrivePopUp" class="mb-xs mt-xs mr-xs modal-basic btn btn-primary">Complete Drive</a>
                           			</c:if>
									<!-- <button type="submit" class="mb-xs mt-xs mr-xs btn btn-primary">Complete Drive</button> -->
								</c:if>
							</div>
                    	</form:form>
                </div>
                <div id="completeDrivePopUp" class="modal-block modal-header-color modal-block-info mfp-hide">
			                   	<section class="panel">
			                        <header class="panel-heading">
			                            <h2 class="panel-title">Confirmation</h2>
			                        </header>
			                        <div class="panel-body">
			                            <div class="">
			                                <p>Are You Sure to complete drive?</p>
			                            </div>
			                        </div>
			                        <footer class="panel-footer">
			                            <!-- <a href = "completeDrive.html" class="btn btn-success">OK</a> -->
			                            <button type="button" id="popUpSubmitButton" class="mb-xs mt-xs mr-xs btn btn-primary">Ok</button>
			                            <button class="btn btn-danger modal-dismiss">Cancel</button>
			                        </footer>
			                   	</section>
			               	</div>
                <div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
	                <section class="panel">
	                    <header class="panel-heading">
	                        <h2 class="panel-title">Payment Details</h2>
	                    </header>
	                    <div class="panel-body">
	                        <table class="table table-bordered table-striped mb-none" id = "paymentDetails">
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
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/admission/paymentSetUp.js"></script>
<script>


$(document).ready(function() {
	var state = $('#driveStatus').val();
	var stateNew = $('#driveStatusNew').val();
	if(state!=''){
		if(state == 'DONE'){
			//alert("hiii");
			$('#driveFormSubmission').addClass("completed");
			$('#driveInterviewSchedule').addClass("completed");
			$('#driveInterviewResult').addClass("completed");
			$('#driveMeritList').addClass("completed");
			$('#drivePayment').addClass("completed");
		}
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
function showPaymentDetails(formId,courseClass,admissionYear,formName)
{
	$.ajax({
		url: '/cedugenie/payment.html',
		dataType: 'json',
		data: "courseClass="+courseClass+"&admissionYear="+admissionYear+"&formName=" +formName+"&formId="+formId,
	    success: function(data) {	
	    	 $('#paymentDetails > tbody').empty();
	     	if(data != null && data!=""){
	     		var dataarr = data.split(";");
	     		var row = "<tbody>";
	     		row += "<tr><td>Classr</td><td>"+dataarr[1]+"</td></tr>";
	     		row += "<tr><td>Academic Year</td><td>"+dataarr[2]+"</td></tr>"; 
	     		row += "<tr><td>Course Name</td><td>"+dataarr[3]+"</td></tr>"; 
	     		row += "<tr><td>Course Type</td><td>"+dataarr[4]+"</td></tr>"; 
	     		row += "<tr><td>Form Id</td><td>"+dataarr[5]+"</td></tr>"; 
	     		row += "<tr><td>Drive Name</td><td>"+dataarr[6]+"</td></tr>"; 
	     		row += "<tr><td>Candidate Name</td><td>"+dataarr[7]+"</td></tr>"; 
	     		row += "<tr><td>Admission Status</td><td>"+dataarr[8]+"</td></tr>"; 
	     		if(dataarr[8]=="ADMITTED"){
	     			row += "<tr><td>Day Of Payment</td><td>"+dataarr[9]+"</td></tr>";
	     		}else{
	     			row += "<tr><td>Last Date Of Fees Submission</td><td>"+dataarr[9]+"</td></tr>";
	     		}
	     		
	     		$("#paymentDetails").append(row);
		     	}
	     	$('#modalInfo').fadeIn("fast");
	    }
	});
}
$("#popUpSubmitButton").click(function(){
	document.getElementById("parentForm").submit();
});
</script>
</body>
</html>