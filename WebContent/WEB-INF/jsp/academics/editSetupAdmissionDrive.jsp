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
<title>Create Subject Group</title>
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
	<header class="page-header">
			<h2>Enter Programme Details</h2>
		</header>
		<c:if test="${insertUpdateStatus eq 'success'}">
			<div class="alert alert-success"  >
				<strong>${msg}</strong>	
			</div>
		</c:if>
		<c:if test="${insertUpdateStatus eq 'fail'}">
			<div class="alert alert-danger" >
				<strong>${msg}</strong>	
			</div>
		</c:if>
		
					<!-- start: page -->
                    <div class="row">
						<div class="col-md-12">
						  <form action="editSetUpAdmissionDrive.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Edit Admission Drive Details</h2>										
									</header>
									<div style="display: block;" class="panel-body">
									
									<div class = "row">
										<div class="col-sm-4">
										<div class="form-group">
												<label class="control-label"><b>Programme Name: <span class="required" aria-required="true">*</span></b></label>
												<%-- <select class="form-control" name="courseName" id="courseName" required >
                                                    <option value="">Select...</option>
                                                    <c:forEach var="course" items="${courseList}" varStatus="i">
														<option value="${course.courseCode}">${course.courseName}</option>
													</c:forEach>
                                                </select>  --%>
                                                <input type="hidden" class="form-control" name="courseCode" id="courseCode"  value = "${courseDetails.courseCode}" required readonly/> 
                                                <input type="text" class="form-control" name="courseName" id="courseName"  value = "${courseDetails.courseName}" required readonly/> 
											</div>
											<div class="form-group">
		                                        <label class=control-label"><b>Form Issuance Date :<span class="required" aria-required="true">*</span></b></label>
		                                        <label class="control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                <input type="text" class="form-control" name="formIssuanceDate" id="formIssuanceDate"  value = "${courseDetails.formIssuanceDate}" required /> 
		                                           	</div>
		                                        </label>
	                                    	</div>
	                                    	<div class="form-group">
		                                        <label class=control-label"><b>Last Form Submission Date :<span class="required" aria-required="true">*</span></b></label>
		                                        <label class="control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                <input type="text" class="form-control" name="lastFormSubmissionDate" id="lastFormSubmissionDate" required value = "${courseDetails.lastFormSubmissionDate}" disabled/> 
		                                           	</div>
		                                        </label>
	                                    	</div>
	                                    	<div class="form-group">
		                                        <label class=control-label"><b>Candidate Scrutiny Date :<span class="required" aria-required="true">*</span></b></label>
		                                        <label class="control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                <input type="text" class="form-control" name="scrutinyDate" id="scrutinyDate"  required value = "${courseDetails.scrutinyDate}" disabled/> 
		                                           	</div>
		                                        </label>
	                                    	</div>
	                                    	<div class="form-group">
		                                        <label class=control-label"><b>Interview Date :<span class="required" aria-required="true">*</span></b></label>
		                                        <label class="control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                <input type="text" class="form-control" name="interviewDate" id="interviewDate" value = "${courseDetails.interviewDate}" required  disabled/> 
		                                           	</div>
		                                        </label>
	                                    	</div>
											
	                                    	
	                                    	
										</div>
										<div class="col-sm-4">
											
	                                    	<div class="form-group">
	                                            <label class="control-label"><b>Interview Start Time :<span class="required" aria-required="true">*</span></b></label>
	                                            <div class="input-group">
	                                                <span class="input-group-addon">
	                                                    <i class="fa fa-clock-o"></i>
	                                                </span>
	                                                <input type="text" class="form-control" id="interviewStartTime" name="interviewStartTime" data-plugin-timepicker="" required value = "${courseDetails.interviewStartTime}" >
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label"><b>Interview End Time :<span class="required" aria-required="true">*</span></b></label>
	                                            <div class="input-group">
	                                                <span class="input-group-addon">
	                                                    <i class="fa fa-clock-o"></i>
	                                                </span>
	                                                <input type="text" class="form-control" id="interviewEndTime" name="interviewEndTime" data-plugin-timepicker="" required value = "${courseDetails.interviewEndTime}" disabled>
	                                            </div>
	                                        </div>
	                                        <div class="form-group">
		                                        <label class=control-label"><b>Marks Submission Date :<span class="required" aria-required="true">*</span></b></label>
		                                        <label class="control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                <input type="text" class="form-control" name="marksSubmissionDate" id="marksSubmissionDate" value = "${courseDetails.marksSubmissionDate}" required disabled/> 
		                                           	</div>
		                                        </label>
	                                    	</div>
											<div class="form-group">
		                                        <label class=control-label"><b>Admission Available From Date :<span class="required" aria-required="true">*</span></b></label>
		                                        <label class="control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                <input type="text" class="form-control" name="admissionDriveStartDate" id="admissionDriveStartDate"  required value = "${courseDetails.admissionDriveStartDate}" disabled/> 
		                                           	</div>
		                                        </label>
	                                    	</div>
	                                    	<div class="form-group">
		                                        <label class="control-label"><b>Admission Available To Date :<span class="required" aria-required="true">*</span></b></label>
		                                        <label class="control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                 <input type="text" class="form-control" name="admissionDriveExpectedEndDate" id="admissionDriveExpectedEndDate"  required value = "${courseDetails.admissionDriveExpectedEndDate}" disabled/> 
		                                            </div>
		                                        </label>
	                                    	</div>
	                                    	
	                                        
	                                    </div>
	                                    <div class="col-sm-4">
											<div class="form-group">
		                                        <label class="control-label"><b>Programme Start Date :<span class="required" aria-required="true">*</span></b></label>
		                                        <label class="control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                <input type="text" class="form-control" name="courseStartDate" id="courseStartDate" data-plugin-datepicker="" required value = "${courseDetails.courseStartDate}" disabled/> 
		                                            </div>
		                                        </label>
	                                    	</div>
	                                    
	                                         <div class="form-group">
	                                            <label class="control-label"><b>Programme Start Time :<span class="required" aria-required="true">*</span></b></label>
	                                            <div class="input-group">
	                                                <span class="input-group-addon">
	                                                    <i class="fa fa-clock-o"></i>
	                                                </span>
	                                                <input type="text" class="form-control" id="courseStartTime" name="courseStartTime" data-plugin-timepicker="" required value = "${courseDetails.courseStartTime}">
	                                            </div>
	                                        </div> 
											
	                                    	 <div class="form-group">
	                                            <label class="control-label"><b>Programme End Time:<span class="required" aria-required="true">*</span></b></label>
	                                            <div class="input-group">
	                                                <span class="input-group-addon">
	                                                    <i class="fa fa-clock-o"></i>
	                                                </span>
	                                                <input type="text" class="form-control" id="courseEndTime" name="courseEndTime" data-plugin-timepicker="" required value = "${courseDetails.courseEndTime}" >
	                                            </div>
	                                        </div> 
	                                        <div class="form-group">
												<label class="control-label"><b>Form Fees:<span class="required" aria-required="true">*</span></b></label>
												<input name="courseFormFees" id="courseFormFees" class="form-control" type="text" placeholder="" value = "${courseDetails.courseFormFees}" required>
											</div>
	                                    </div>
									</div> 
								</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
										<a href="setupAdmissionDrive.html" class="btn btn-warning" style="position: absolute; z-index: 999;">BACK</a>
									</footer>
								</section>
                            </form>
						</div>
					</div>
			
					
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/academics/createCourse.editable.js"></script>
<script src="/cedugenie/js/academics/setUpAdmissionDrive.js"></script>
<script src="/cedugenie/assets/custom-caleder/jquery-ui.js" type="text/javascript"></script>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>

<script type="text/javascript">

/* $(function(){
	   var formIssuanceDate = document.getElementById("formIssuanceDate").value;
	   alert("formIssuanceDate===="+formIssuanceDate);
	     */
	    $("#formIssuanceDate").datepicker({
	        minDate: 0,
	        maxDate: '+1Y+6M',
			 dateFormat: 'dd/mm/yy',
	        onSelect: function (dateStr) {
	            var min = $(this).datepicker('getDate'); // Get selected date
	            $("#lastFormSubmissionDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
	            $("#lastFormSubmissionDate").removeAttr('disabled','disabled');
	        }
	    });
	    
	    $("#lastFormSubmissionDate").datepicker({
	        minDate: 0,
	        maxDate: '+1Y+6M',
			 dateFormat: 'dd/mm/yy',
	        onSelect: function (dateStr) {
	            var min = $(this).datepicker('getDate'); // Get selected date
	            $("#scrutinyDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
	            $("#scrutinyDate").removeAttr('disabled','disabled');
	        }
	    });
	    $("#scrutinyDate").datepicker({
	        minDate: 0,
	        maxDate: '+1Y+6M',
			 dateFormat: 'dd/mm/yy',
	        onSelect: function (dateStr) {
	            var min = $(this).datepicker('getDate'); // Get selected date
	            $("#interviewDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
	            $("#interviewDate").removeAttr('disabled','disabled');
	        }
	    });
	    
	    $("#interviewDate").datepicker({
	        minDate: 0,
	        maxDate: '+1Y+6M',
			 dateFormat: 'dd/mm/yy',
	        onSelect: function (dateStr) {
	            var min = $(this).datepicker('getDate'); // Get selected date
	            $("#marksSubmissionDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
	            $("#marksSubmissionDate").removeAttr('disabled','disabled');
	        }
	    });
	    $("#marksSubmissionDate").datepicker({
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

	    
	    $("#admissionDriveExpectedEndDate").datepicker({
	        minDate: '0',
	        maxDate: '+1Y+6M',
			dateFormat: 'dd/mm/yy',
	        onSelect: function (dateStr) {
	            var min = $(this).datepicker('getDate'); // Get selected date
	            $('#courseStartDate').datepicker('option', 'minDate', min || '0'); // Set other max, default to +18 months
	            $("#courseStartDate").removeAttr('disabled','disabled');
	        }
	    });
	    
	    $("#courseStartDate").datepicker({
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

