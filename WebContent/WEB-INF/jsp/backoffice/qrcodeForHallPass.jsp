<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>QR Code For Hall Pass </title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }       
</style>
</head>
<body>
<div class="row">
	<div class="col-md-6 col-md-offset-3">
		<form method="post" action="submitHallPassAttributeForQRCode.html">
			<section class="panel">
				<header class="panel-heading">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
					</div>
					<h2 class="panel-title">Generate QR Code For Hall Pass </h2>										
				</header>
				<div style="display: block;" class="panel-body">
					<div class="row">
						<div class="col-md-6 col-md-offset-3">
							<div class="form-group"> 
								<label class="col-md-6 control-label"><b>Exam Session</b></label>
	                            <c:choose>
									<c:when test="${academicYear eq null}">
										<div class="alert alert-danger">
											<label class="col-md-6 control-label"><strong>No Academic Year Found</strong></label>
										</div>
									</c:when>
									<c:otherwise>
										<input type="hidden" name="year" id="examSession" value="${academicYear.academicYearCode}" />
										<label class="col-md-6 control-label"><b>${academicYear.academicYearName}</b></label>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
                                <label class="control-label">Standard</label>
	                                <select class="form-control" id="class" name="className">
	                                     <option value="">Select..</option>
	                                     <c:choose>
											<c:when test="${academicYear.classList eq null}">
												<div class="alert alert-danger">
													<strong>No Class Found</strong>
												</div>
											</c:when>
											<c:otherwise>
												<c:forEach var="klass" items="${academicYear.classList}">
													<option value="${klass.standardCode}">${klass.standardName}</option>
												</c:forEach>
											</c:otherwise>
										</c:choose>
	                                </select>
                            </div>
                            <div class="form-group">
                               <label class="control-label">Course</label>
	                               <select class="form-control" id="course" name="courseName" >
	                                    <option value="">Select...</option>
	                               </select>
	                        </div>
	                     </div>
	                     <div class="col-md-6">
	                     	<div class="form-group">
                               <label class="control-label">Section</label>
                               <select class="form-control" id="section" name="section">
	                               <option value="">Select...</option>
	                           </select>
	                        </div>
	                        <div class="form-group">
                               <label class="control-label">Exam Name</label>
	                               <select class="form-control" id="examName" name="examName">
	                                    <option value="">Select...</option>
	                               </select>
	                        </div>
						</div>
					</div>
				</div>
				<table id="subMar" class="" style="visibility: collapse;">
				</table>
				<footer style="display: block;" class="panel-footer">
					<button class="btn btn-primary" id="submit" name="submit" value="Submit" onclick="return validateHallPassQRCode();">Submit </button>
					<button type="reset" class="btn btn-default" id="reset" name="reset" value="reset">Reset</button>
				</footer>
			</section>
		</form>
	</div>
</div>					
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/backoffice/qrcodeForHallPass.js"></script>
<script>
function validateHallPassQRCode(){
	var dateValue=document.getElementById("date").value;
	var timeValue=document.getElementById("time").value;
	var classValue=document.getElementById("class").value;
	var courseValue=document.getElementById("course").value;
	//var examTypeValue=document.getElementById("examType").value;
	//var termValue=document.getElementById("term").value;
	//var streamValue=document.getElementById("stream").value;
	var sectionValue=document.getElementById("section").value;
	var examNameValue=document.getElementById("examName").value;
	
	if(classValue==""){
		/* document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Select Proper Class"; */
		alert("Please Select Proper Standard");
		return false;
	}
	
	if(courseValue==""){
		/* document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Select Proper Course"; */
		alert("Please Select Proper Course");
		return false;
	}
	
	
	if(sectionValue==""){
		/* document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Select Proper Section"; */
		alert("Please Select Proper Section");
		return false;
	}
	
	if(examNameValue==""){
		/* document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Select Proper Exam Name"; */
		alert("Please Select Proper Exam Name");
		return false;
	}
	
	
	if(dateValue==""){
		/* document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Date"; */
		alert("Please Select Date");
		return false;
	}

	
	if(timeValue==""){
		/* document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Time"; */
		alert("Please Select Proper time");
		return false;
	}			
}
</script>
<!-- <script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script> -->
</body>
</html>