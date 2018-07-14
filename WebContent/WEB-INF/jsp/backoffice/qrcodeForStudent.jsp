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
<title>QR For Student</title>
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
		<form:form action="submitStudentAttributeForQRCode.html" method="post">
			<section class="panel">
				<header class="panel-heading">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
					</div>
					<h2 class="panel-title">Generate QR Code For Student</h2>										
				</header>
				<div style="display: block;" class="panel-body">
					<table class="table table-bordered table-striped mb-none" id="TeahetAttributeColumn"> 
						<tbody>
							<tr>
								<td>
									<!-- <div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="registrationId" id="registrationId">
										<label for="userId">Registration Id</label>
									</div> -->
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="rollNumber" id="rollNumber">
										<label for="mobile">Roll Number</label>
									</div>
								</td>
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="name" id="name">
										<label for="name">Name</label>
									</div>
								</td>
							</tr>
							<tr>											
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="gender" id="gender">
										<label for="gender">Gender</label>
									</div>
								</td>
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="dateOfBirth" id="dateOfBirth">
										<label for="dateOfBirth">Date Of Birth</label>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="fathersName" id="fathersName">
										<label for="designation">Father's Name</label>
									</div>
								</td>
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="motherName" id="motherName">
										<label for="bloodGroup">Mother's Name</label>
									</div>
								</td>
							</tr>
							<tr>											
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="klass" id="klass">
										<label for="mobile">Class</label>
									</div>
								</td>
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="section" id="section">
										<label for="emailId">Section</label>
									</div>
								</td>
							</tr>
							<tr>											
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="course" id="course">
										<label for="emailId">Course</label>
									</div>
								</td>
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="bloodGroup" id="bloodGroup">
										<label for="mobile">Blood Group</label>
									</div>
								</td>
							</tr>
							<tr>											
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="contactMailId" id="contactMailId">
										<label for="mobile">Email Id</label>
									</div>
								</td>
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="contactNumber" id="contactNumber">
										<label for="emailId">Contact Number</label>
									</div>
								</td>
							</tr>
							
						
						</tbody>
					</table>    
				</div>
				<footer style="display: block;" class="panel-footer">
					<button class="btn btn-primary" id="submit" name="submit" value="Submit" onclick="return validateStudentQRCode();">Submit </button>
					<button type="reset" class="btn btn-default" id="reset" name="reset" value="reset">Reset</button>
				</footer>
			</section>
		</form:form>
	</div>
</div>					
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script>	
$(document).ready(function() {    
//     $('#date').Zebra_DatePicker();
    
    
//     $('#date').Zebra_DatePicker({
//     	  format: 'Y-m-d',
//     	  direction: true
//     	});
    
 });
 

	function validateStudentQRCode(){	
		var dateValue=document.getElementById("date").value;
		var timeValue=document.getElementById("time").value;
		
		if(dateValue==""){
			alert("Please Enter Date");
			return false;
		}

		
		if(timeValue==""){
			alert("Please Enter Time");
			return false;
		}			
	}
</script>
</body>
</html>