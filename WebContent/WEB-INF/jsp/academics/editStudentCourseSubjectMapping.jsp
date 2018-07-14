<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de" class="fixed header-dark">
<head>
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
	<title>Standard Subject Mapping</title>
	<%@ include file="/include/include.jsp" %>
	<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
	<style type="text/css">
	       .scroll-to-top{
	           display: none !important;
	       }.mb-md{
	       	   display: none;
	       }
	</style>
	<script type="text/javascript">
		$(document).ready( function(){
			$("#course").change(function (){
				document.getElementById("programLists").style.display = 'block';
				uncheckAllSubjects();
				document.getElementById("student").innerHTML='<option value="">Select</option>';
				$.ajax({
					url: '/cedugenie/getSubjectsForACourse.html',
					dataType: 'json',
					data: "course=" + $("#course").val(),
					success: function(data) {
						if(null == data || data == ""){
							document.getElementById("programLists").style.display = 'none';
							document.getElementById("warningmsg").style.display = 'block';			
							document.getElementById("warningmsg").innerHTML = "<b>There is no Subject available in this program.</b>";
						}
						data=data.split("*~*");
						var subjectGroupArray = [];
						for(var i=0;i<data.length-1;i++){
							var subjectAndGroup=data[i].split("#@#");
							subjectGroupArray.push(subjectAndGroup[0]);
						}
						var tmp = [];
						for(var i = 0; i < subjectGroupArray.length; i++){
							if(tmp.indexOf(subjectGroupArray[i]) == -1){
							tmp.push(subjectGroupArray[i]);
							}
						}
						
						var text1='';
						var counter=0;
						for(var i=0;i<tmp.length;i++){
							if(counter==0){
								text1=text1+'<div class="row">';
							}
							text1=text1+'<div class="col-md-3"><div class="well well-sm info no-margin"><div>Subject Name :: '+tmp[i];
							text1=text1+'</div></div><table class="table table-bordered table-striped mb-none">';
							text1=text1+'<thead><tr><th>Select</th><th>Subject Name</th></tr></thead><tbody id="'+tmp[i]+'">';
							text1=text1+'</tbody></table></div>';
							counter++;
							if(counter==4){
								text1=text1+"</div><hr>";
								counter=0;
							}
						}
						if(counter!=0){
							text1=text1+"</div><hr>";
						}
						text1=text1+'</div>';
						document.getElementById("panelBody1").innerHTML=text1;
						for(var i=0;i<data.length-1;i++){
							var subjectAndGroup=data[i].split("#@#");
							text1='<tr><td><input type="checkbox" value="'+subjectAndGroup[1]+'" name="subject" id="subject'+subjectAndGroup[1]+'"></td><td>'+subjectAndGroup[1]+'</td></tr>';
							var row=document.getElementById(subjectAndGroup[0]).innerHTML;
							row=row+text1;
							document.getElementById(subjectAndGroup[0]).innerHTML=row;
						}
					}
				});				
				
				$.ajax({
					url: '/cedugenie/getSubjectsMappedStudents.html',
					dataType: 'json',
					data: "course=" + $("#course").val(),
					success: function(data) {
						var options='<option value="">Select</option>';
						if(data!=""){
							data=data.split("~");						
							for(var i=0;i<data.length-1;i++){
								var student=data[i].split("*");
								options=options+'<option value="'+student[2]+'">'+student[0]+' ('+student[1]+')</option>';
							}
						}
						document.getElementById("student").innerHTML=options;
					}
				});
			});
			
			
			$("#student").change(function (){
				uncheckAllSubjects();
				$.ajax({
					url: '/cedugenie/getSubjectsStudiedByStudentInCourse.html',
					dataType: 'json',
					data: "course=" + $("#course").val(),
					data: "course=" + $("#course").val()+ "&roll=" + $("#student").val(),
					success: function(data) {
						data=data.split("*~*");
						for(var i=0;i<data.length-1;i++){
							var sub=data[i].split("#@#");
							document.getElementById("subject"+sub[1]).checked=true;
						}
					}
				});
			});
		});
		
		function uncheckAllSubjects(){
			var subject=document.getElementsByName("subject");
			if(subject.length>=1){
				for(var i=0;i<subject.length;i++){
					subject[i].checked=false;
				}
			}
		}
		
		function validate(){
			var standard=document.getElementById("standard").value;
			
			
			if(standard == ""||standard =='null'){
				alert("Please Enter Subject");
				return false;
			}
			
			var course=document.getElementById("course").value;
			if(course == ""||course =='null'){
				alert("Please Enter Course");
				return false;
			}
			var student = document.getElementById("student").value;
			if(student == ""||student =='null'){
				alert("Please Enter Student");
				return false;
			}
			var table=document.getElementsByName("subject");
			if(table[i].checked)
				{
				document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "No Mapping being done";
				return true;
				} 
			
			return true;
		}
	</script>
</head>
	<body>
		<c:if test="${insertUpdateStatus eq 'success'}">
			<div class="alert alert-success">
				<strong>${msg}</strong>
			</div>
		</c:if>
		
		<c:if test="${insertUpdateStatus eq 'fail'}">
			<div class="alert alert-danger">
				<strong>${msg}</strong>
			</div>
		</c:if>
			<c:choose>
				<c:when test="${courseList eq null || empty courseList}">
					<div class="btnsarea01" >
						<div class="errorbox" id="errorbox" style="visibility: visible;">
							<c:if test="${courseList eq null || empty courseList}">
								<span id="errormsg">Course Not Found</span>	
							</c:if>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<form name="subjectForm" id="subjectForm" method="POST" action="createStudentCourseSubjectMapping.html" >
						<input type="hidden" name="type" value="edit">
						<!-- start: page -->
							 <div class="row">
								<div class="col-md-4 col-md-offset-4">
								  <form id="form1">
										<section class="panel">
											<header class="panel-heading">
												<div class="panel-actions">
													<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
												</div>

												<h2 class="panel-title">Standard &amp; Subject</h2>										
											</header>
											<div style="display: block;" class="panel-body">                                       
												<div class="form-group">
													<label class="control-label">Standard<span class="required" aria-required="true">*</span></label>
													<select class="form-control" id="course" name="course" required>
														<option value="">Select</option>
														<c:forEach var="course" items="${courseList}" varStatus="i">
															<option value="${course.courseCode}">${course.courseName}</option>
														</c:forEach>
													</select>
												</div>
												
												<div class="form-group">
													<label class="control-label">Student<span class="required" aria-required="true">*</span></label>
													<select class="form-control" id="student" name="student" required>
														<option value="">Select</option>
													</select>
												</div>
											</div>
										</section>
									</form>
								</div>
														
								<div id="programLists" class="col-md-12" style="display: none">	
									<section class="panel">
										<header class="panel-heading">
											<div class="panel-actions">
												<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
											</div>
											<div class="alert alert-danger" id="javascriptmsg" style="display: none">
												<span> </span>	
											</div>
											<h2 class="panel-title">Student  Standard Subject Mapping</h2>
										</header>
										<div class="panel-body" id="panelBody1">
										
										
										</div>
										<footer style="display: block;" class="panel-footer">
											<button class="btn btn-primary" onclick = "return validate()">Submit </button>
											<button type="reset" class="btn btn-default">Reset</button>
										</footer>
									</section>
								</div>
						</div>
					</form>
				</c:otherwise>
			</c:choose>
			<div class="alert alert-warning" id="warningmsg" style="display: none" align="center">
		  		<span></span>	
			</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
	</body>


</html>