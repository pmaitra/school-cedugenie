<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listBookPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Closed ticket list</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
       .datepicker-dropdown{
        display: none !important;
       }
       #ui-datepicker-div{
       	z-index:99999 !important;
       }
</style>
<link href="/cedugenie/assets/custom-caleder/jquery-ui.css" type="text/css" rel="stylesheet"> 
</head>
<body>
<c:choose>
	<c:when test="${admissionForm == null}">
		<div class="errorbox" id="errorbox" style="visibility: visible;" >
			<span id="errormsg">Data Not Found</span>	
		</div>
	</c:when>
	<c:otherwise>
 				<div class="row">						
						<div class="col-md-12">
							<form:form method="POST" id="newAdmissionForm" name="newAdmissionForm" action="admissionForSelectedYear.html">
                            	<section class="panel">
                                	<header class="panel-heading">
	                                    <div class="panel-actions">
	                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
	                                    </div>
	
	                                    <h2 class="panel-title">Create Admission Drive And Configure Admission Process</h2>										
	                                </header>
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label class="col-md-4 control-label"><b>Year</b></label>
                                        <label class="col-md-8 control-label">${currentYear.academicYearName}</label>
										<input type="hidden" id="admissionFormYear" name="admissionFormYear" value="${currentYear.academicYearName}" />
                                    </div>
                                   <%--  <div class="form-group">
                                        <label class="col-md-4 control-label"><b>Class</b></label>
                                        <label class="col-md-8 control-label">${admissionForm.courseClass}</label>
                                        <input type="hidden" id="courseClass" name="courseClass" value="<c:out value="${admissionForm.courseClass}"/>"/>
                                    </div> --%>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label"><b>Admission Drive Name</b></label>
                                        <label class="col-md-8 control-label">${admissionForm.admissionDriveName}</label>
                                        <input type="hidden" id="admissionDriveName" name="admissionDriveName" value="${admissionForm.admissionDriveName}"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label"><b>Standard Name</b></label>
                                        <label class="col-md-8 control-label">${admissionForm.courseName}</label>
                                        <input type="hidden" id="courseName" name="courseName" value="<c:out value="${admissionForm.courseName}"/>"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label"><b>Course Type</b></label>
                                        <label class="col-md-8 control-label">${admissionForm.courseType}</label>
                                        <input type="hidden" id="courseType" name="courseType" value="<c:out value="${admissionForm.courseType}"/>"/>
                                    	 <input type="hidden" id="courseCode" name="courseCode" value="<c:out value="${admissionForm.courseCode}"/>"/>
                                    </div>
                                    <%-- <div class="form-group">
                                        <label class="col-md-4 control-label"><b>Course Code</b></label>
                                        <label class="col-md-8 control-label">${admissionForm.courseCode}</label>
                                       
                                    </div> --%>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label"><b>Form Fee</b><span class="required" aria-required="true">*</span></label>
                                        <label class="col-md-2 control-label"><input type="text" placeholder="" name="formFees" id="formFees" class="form-control" pattern="^[1-9]\d*$" required></label>
                                    </div>
                                   <%--  <div class="form-group">
                                        <label class="col-md-4 control-label"><b>Admission Start Date</b></label>
                                        <label class="col-md-8 control-label">${admissionForm.admissionDriveStartDate}</label>
                                        <input type="hidden" id="admissionDriveStartDate" name="admissionDriveStartDate" value="${admissionForm.admissionDriveStartDate}"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label"><b>Admission Expected End Date</b></label>
                                        <label class="col-md-8 control-label">${admissionForm.admissionDriveExpectedEndDate}</label>
                                        <input type="hidden" id="admissionDriveExpectedEndDate" name="admissionDriveExpectedEndDate" value="${admissionForm.admissionDriveExpectedEndDate}"/>
                                    </div> --%>
                                    <div class="form-group">
                                        <label class="col-md-4 control-label"><b>Last Date of Form Submission</b><span class="required" aria-required="true">*</span></label>
                                        <label class="col-md-2 control-label">
                                            <div class="input-group">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </span>
                                                <input type="text" class="form-control" id="admissionFormSubmissionLastDate" name="admissionFormSubmissionLastDate"  required/>
                                            </div>
                                        </label>
                                    </div>
                                    <div class="form-group">
		                                        <label class="col-md-4 control-label"><b>Admission Start Date :<span class="required" aria-required="true">*</span></b></label>
		                                        <label class="col-md-2 control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                <input type="text" class="form-control" name="admissionDriveStartDate" id="admissionDriveStartDate"  required disabled/> 
		                                           	</div>
		                                        </label>
	                                   </div>
	                                   <div class="form-group">
		                                        <label class="col-md-4 control-label"><b>Admission Expected End Date :<span class="required" aria-required="true">*</span></b></label>
		                                        <label class="col-md-2 control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                 <input type="text" class="form-control" name="admissionDriveExpectedEndDate" id="admissionDriveExpectedEndDate"  required disabled/> 
		                                            </div>
		                                        </label>
	                                   </div>
                                    
                                    <div class="form-group">
                                        <label class="col-md-4 control-label"><b>Admission Process State</b><span class="required" aria-required="true">*</span></label>
                                        <div class="col-md-8">
                                            <table class="table table-bordered table-striped mb-none">
                                                <thead>
                                                    <tr>
                                                        <th>Select</th>
                                                        <th>Form Submission</th>											
                                                        <th>Schedule Interview</th>
                                                        <th>Interview Result</th>
                                                        <th>Merit List</th>
                                                        <th>Payment</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                	<c:forEach var="customizedAdmissionProcess" items="${admissionForm.customizedAdmissionProcessList}" varStatus="status">	
	                                                    <tr>											
	                                                        <td><input type="radio"  id="cap${status.index}" class="customizedAdmissionProcessCode" name="customizedAdmissionProcess.customizedAdmissionProcessCode" value="${customizedAdmissionProcess.customizedAdmissionProcessCode}"/ required></td>   
		                                                    <td align= "center">${(customizedAdmissionProcess.formSubmission eq true) ? '<i aria-hidden="true"  class="fa fa-check-circle text-success">' : '<i aria-hidden="true" class="fa fa-times-circle text-danger">'}</td>
															<td align= "center">${(customizedAdmissionProcess.scheduleInterview eq true) ? '<i aria-hidden="true"  class="fa fa-check-circle text-success">' : '<i aria-hidden="true" class="fa fa-times-circle text-danger">'}</td>
															<td align= "center">${(customizedAdmissionProcess.interviewResult eq true) ? '<i aria-hidden="true"  class="fa fa-check-circle text-success">' : '<i aria-hidden="true" class="fa fa-times-circle text-danger">'} </td>
															<td align= "center">${(customizedAdmissionProcess.meritList eq true) ?'<i aria-hidden="true"  class="fa fa-check-circle text-success">' : '<i aria-hidden="true" class="fa fa-times-circle text-danger">'}</td>
															<td align= "center">${(customizedAdmissionProcess.payment eq true) ? '<i aria-hidden="true"  class="fa fa-check-circle text-success">' : '<i aria-hidden="true" class="fa fa-times-circle text-danger">'}</td>

	                                                    </tr>
													</c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>  
                                </div>
                                <footer style="display: block;" class="panel-footer">
                                    <button type="submit" class="btn btn-primary" onclick="return validate();">Submit </button>
                                    <button type="reset" class="btn btn-default">Reset</button>
                                </footer>
                            </section>
                            </form:form>
						</div>
                        
					</div>	
				</c:otherwise>
			</c:choose>
				
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<!-- <script type="text/javascript" src="/cedugenie/js/admission/newAdmissionForm.js"></script> -->
<!-- <script src="/bootstrap-validator/vendor/momentjs/moment.min.js"></script> -->
<script src="/cedugenie/assets/custom-caleder/jquery-ui.js" type="text/javascript"></script>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>

<script type="text/javascript">

/* $(function(){
	   var formIssuanceDate = document.getElementById("formIssuanceDate").value;
	   alert("formIssuanceDate===="+formIssuanceDate);
	     */
/* 	    $("#admissionDriveStartDate").datepicker({
	        minDate: 0,
	        maxDate: '+1Y+6M',
			 dateFormat: 'dd/mm/yy',
	        onSelect: function (dateStr) {
	            var min = $(this).datepicker('getDate'); // Get selected date
	            $("#lastFormSubmissionDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
	            $("#lastFormSubmissionDate").removeAttr('disabled','disabled');
	        }
	    }); */
	    
	    $("#admissionFormSubmissionLastDate").datepicker({
	        minDate: 0,
	        maxDate: '+1Y+6M',
			 dateFormat: 'dd/mm/yy',
	        onSelect: function (dateStr) {
	            var min = $(this).datepicker('getDate'); // Get selected date
	            $("#admissionDriveStartDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
	            $("#admissionDriveStartDate").removeAttr('disabled','disabled');
	        }
	    });

	    
	    $("#admissionDriveStartDate").datepicker({
	        minDate: 0,
	        maxDate: '+1Y+6M',
			 dateFormat: 'dd/mm/yy',
	        onSelect: function (dateStr) {
	            var min = $(this).datepicker('getDate'); // Get selected date
	            $("#admissionDriveExpectedEndDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
	            $("#admissionDriveExpectedEndDate").removeAttr('disabled','disabled');
	        }
	    });

	    
/* 	    $("#admissionDriveExpectedEndDate").datepicker({
	        minDate: '0',
	        maxDate: '+1Y+6M',
			dateFormat: 'dd/mm/yy',
	        onSelect: function (dateStr) {
	            var min = $(this).datepicker('getDate'); // Get selected date
	            $('#courseStartDate').datepicker('option', 'minDate', min || '0'); // Set other max, default to +18 months
	            $("#courseStartDate").removeAttr('disabled','disabled');
	        }
	    }); */
	    
	    $("#admissionDriveExpectedEndDate").datepicker({
	        minDate: '0',
	        maxDate: '',
			dateFormat: 'dd/mm/yy',
	        onSelect: function (dateStr) {
	            var max = $(this).datepicker('getDate'); // Get selected date
	            $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M'); // Set other max, default to +18 months
	           // $("#scrutinyDate").removeAttr('disabled','disabled');
	        }
	    });
	    

	/* }); */

</script>
</body>
</html>