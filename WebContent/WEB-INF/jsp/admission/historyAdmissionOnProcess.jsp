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
       }
</style>


</head>
<body>
    <div class="row">
        <div class="col-xs-12">
            <section class="panel form-wizard" id="w4">
                <header class="panel-heading">
                    <div class="panel-actions">
                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                    </div>

                    <h2 class="panel-title">Admission List</h2>
                </header>
                <div class="panel-body">
                    <div class="wizard-progress wizard-progress-lg">
                        <div class="steps-progress">
                            <div class="progress-indicator"></div>
                        </div>
                        <ul class="wizard-steps">
                            <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.formSubmission eq true}">
	                            <li>
	                             <a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>" data-toggle="tab"><span>1</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&admissionDriveState=FORMSUBMISSION">Form <br> Submission</a> </a>
	                               <%--  <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>" data-toggle="tab"><span>1</span>Form <br> Submission</a> --%>
	                            </li>
                            </c:if>
                            <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.scheduleInterview eq true}">
	                            <li>
	                                <%-- <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=SCHEDULEINTERVIEW"  data-toggle="tab"><span>2</span>Schedule <br> Interview</a> --%>
	                            	<a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>" data-toggle="tab"><span>2</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&admissionDriveState=SCHEDULEINTERVIEW">Schedule <br> Interview</a>
	                            </li>
                            </c:if>
                            <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.interviewResult eq true}">
	                            <li>
	                            	 <a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="{$AdmissionOnProcessClass.formName}"/>" data-toggle="tab"><span>3</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&admissionDriveState=INTERVIEWRESULT">Interview <br> Result</a> </a>
	                                <%-- <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=INTERVIEWRESULT"  data-toggle="tab"><span>3</span>Interview <br> Result</a> --%>
	                            </li>
                            </c:if>
                            <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.meritList eq true}">
	                            <li>
	                            	 <a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>" data-toggle="tab"><span>4</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&admissionDriveState=MERITLIST">Merit <br> List</a> </a>
	                               <%--  <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${ finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionState=MERITlIST"  data-toggle="tab"><span>4</span>Merit <br> List</a> --%>
	                            </li>
                            </c:if>
                            <c:if test="${StreamClassYearCourse != null && StreamClassYearCourse.customizedAdmissionProcess != null && StreamClassYearCourse.customizedAdmissionProcess.payment eq true}">
	                            <li >
	                             <a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>" data-toggle="tab"><span>5</span> <a href="admissionOnProcess.html?courseClass=<c:out value="${AdmissionOnProcessClass.courseClass}"/>&year=<c:out value="${AdmissionOnProcessClass.admissionYear}"/>&driveName=<c:out value="${AdmissionOnProcessClass.formName}"/>&admissionDriveState=PAYMENT">Payment</a> </a>
	                              <%--   <a href="admissionOnProcess.html?courseClass=<c:out value="${finalSelectedCandidateList[0].courseClass}"/>&year=<c:out value="${finalSelectedCandidateList[0].admissionYear}"/>&driveName=<c:out value="${finalSelectedCandidateList[0].formName}"/>&admissionStates=PAYMENT"  data-toggle="tab"><span>5</span>Payment</a> --%>
	                            </li>
                            </c:if>
                        </ul>
                    </div>
                    
                </div>
                
            </section>
        </div>
    </div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>

</body>
</html>