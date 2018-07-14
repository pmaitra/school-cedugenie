<!-- /*added by ranita.sur on 29082017 for configute Time Table Parameter*/ -->
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
       }.mb-md{
       	   display: none;
       }
</style>

</head>
<body>
				<header class="page-header">
				<h2>Configure Time Table Parameters</h2>
			</header>
			<div class="content-padding">
				<c:if test="${successStatus != null}">
					<!-- <div class="successbox" id="successbox" style="visibility:visible;">
						<span id="successmsg" style="visibility:visible;">Successfully Updated!</span>	
					</div> -->
					<div class="alert alert-success">
						<strong> Details Saved Successfully </strong>
					</div>
				</c:if>
				<c:if test="${failStatus != null}">
					<!-- <div class="errorbox" id="errorbox" style="visibility: visible;">
						<span id="errormsg">Update Fail!</span>	
					</div> -->
					<div class="alert alert-danger">
						<strong>Sorry!Insertion Failed.</strong>
					</div>
				</c:if>
					<div class="row">
						<div class="col-md-6 col-md-offset-3">
						  <form:form name="submitConfigureTimeTableParameter"  method="POST" action="submitConfigureTimeTableParameter.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title"></h2>										
									</header>
									<div style="display: block;" class="panel-body">  
										<div class="col-md-12">
										<div class="form-group">
											<label class="col-sm-8 control-label">No of working days in a week: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-4">
												<input name="noOfWorkingDays" id="noOfWorkingDays" class="form-control" type="text" value="${timeTableParameter.noOfWorkingDays}" pattern = "^[0-9]\d*$"  title="Numeric only" required  >
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-8 control-label">No of periods in a day: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-4">
												<input name="noOfPeriods" id="noOfPeriods" class="form-control" type="text" value="${timeTableParameter.noOfPeriods}" pattern = "^[0-9]\d*$"  title="Numeric only" required  >
											</div>
										</div>
										<%-- <div class="form-group">
											<label class="col-sm-8 control-label">Max No of consequtive periods for each theoretical class: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-4">
												<input name="noOfTheoryClass" id="noOfTheoryClass" class="form-control" type="text" value="${timeTableParameter.noOfTheoryClass}" pattern = "^[0-9]\d*$"  title="Numeric only" required  >
											</div>
										</div> --%>
										<div class="form-group">
											<label class="col-sm-8 control-label">No of consequtive periods for each practical class: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-4">
												<input name="noOfConsequtivePracticalClass" id="noOfConsequtivePracticalClass" class="form-control" type="text" value="${timeTableParameter.noOfConsequtivePracticalClass}" pattern = "^[0-9]\d*$"  title="Numeric only" required  >
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-8 control-label">No of teachers:: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-4">
												<input name="noOfTeachers" id="noOfTeachers" class="form-control" type="text" value="${timeTableParameter.noOfTeachers}" pattern = "^[0-9]\d*$"  title="Numeric only" required  >
											</div>
										</div>
	                                      <%--  <div class="form-group">
											<label class="col-sm-8 control-label">No of Programs and section: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-4">
												<input name="noOfClasses" id="noOfClasses" class="form-control" type="text"  value="${timeTableParameter.noOfClasses}" pattern = "^[0-9]\d*$"  title="Numeric only" required >
											</div>
										</div> --%>
	                                        <div class="form-group">
											<label class="col-sm-8 control-label">Maximum no Of consequtive classes a teacher can take: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-4">
												<input name="noOfConsequtiveClasses" id="noOfConsequtiveClasses" class="form-control" type="text" value="${timeTableParameter.noOfConsequtiveClasses}" pattern = "^[0-9]\d*$"  title="Numeric only" required >
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-8 control-label">Maximum no of  classes a teacher can take in a week: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-4">
												<input name="noOfMaximumClasses" id="noOfMaximumClasses" class="form-control" type="text" value="${timeTableParameter.noOfMaximumClasses}" pattern = "^[0-9]\d*$"  title="Numeric only" required  >
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-8 control-label">Practical classes will be held in the Last periods:<span class="required" aria-required="true">*</span></label>
											<div class="col-sm-4">
												<input name="noOfPracticalClass" id="noOfPracticalClass" class="form-control" type="text" value="${timeTableParameter.noOfPracticalClass}" pattern = "^[0-9]\d*$"  title="Numeric only" required  > 
											</div>
										</div>
	                                     
	                                        
											
										</div> 
								
									</div>
									 <footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id="submit" type="submit" >Submit </button>
										<button type="reset" class="btn btn-default" id="clear">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
						
						
			</div>
			</div>
					






<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<!-- <script type="text/javascript" src="/cedugenie/js/backoffice/addBank.js"></script> -->
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script src="/cedugenie/js/common/addVendor.editable.js"></script>
</body>
</html>