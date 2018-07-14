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
			
			
			/* $("#standard").change(function (){
				$.ajax({
				url: '/cedugenie/getCourseForStandard.html',
				dataType: 'json',
				data: "standard=" + $("#standard").val(),
				success: function(data) {
					var options='<option value="">Select</option>';
					if(data!=""){
						data=data.split("~");						
						for(var i=0;i<data.length-1;i++){
							var course=data[i].split("*");
							options=options+'<option value="'+course[0]+'">'+course[1]+'</option>';
						}
					}
					document.getElementById("course").innerHTML=options;
				}
				});
				
				
				
				
			}); */
			
			$("#course").change(function (){
				
				$.ajax({
			        url: '/cedugenie/getSectionAgainstCourse.html',
			        dataType: 'json',
			        data: "course=" + ($(this).val()),
			        success: function(dataDB) {
			        	var options="<option value=''>Select</option>";
			        	if(dataDB != "null" && dataDB !="")
						{
			        		var arr = dataDB.split(";");
							for (var i=0;i<arr.length;i++){   
								var section = arr[i].split("*");
								options=options+"<option value='"+section[0]+"'>"+section[1]+"</option>";
							}
						}
			        	document.getElementById("section").innerHTML=options;
			       }
				});
				
				
				$.ajax({
				url: '/cedugenie/getSubjectsForACourse.html',
				dataType: 'json',
				data: "course=" + $("#course").val(),
				success: function(data) {
					var options="<option value=''>Select</option>";
		        	if(data != null && data !="")
					{
		        		data=data.split("*~*");
						for (var i=0;i<data.length-1;i++){   
							var sub=data[i].split("#@#");
							options=options+"<option value='"+sub[1]+"'>"+sub[1]+"</option>";
						}
					}
		        	document.getElementById("subject").innerHTML=options;
					
					
					
				}
				});
				
				
				
				
				$.ajax({
					url: '/cedugenie/getExamsForCourse.html',
					dataType: 'json',
					data: "course=" + $("#course").val(),
					success: function(data) {
						var options='<option value="">Select</option>';
						if(data!=""){
							data=data.split("~");						
							for(var i=0;i<data.length-1;i++){
								options=options+'<option value="'+data[i]+'">'+data[i]+'</option>';
							}
						}
						document.getElementById("exam").innerHTML=options;
					}
					});
			});
			
			
		});

	</script>
</head>
	<body>
		<c:if test="${insertUpdateStatus eq 'success' }">
		<div class="alert alert-success" id="successboxmsgbox">
			<strong> ${msg}</strong>	
		</div>
	</c:if>
	<c:if test="${insertUpdateStatus eq 'fail' }">
		<div class="alert alert-danger" id="errormsgbox">
			<strong>${msg} </strong>	
		</div>
	</c:if>
			<c:choose>
			<c:when test="${courseList eq null || empty courseList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<c:if test="${courseList eq null || empty courseList}">
							<span id="errormsg">Programme Not Found</span>	
						</c:if>
					</div>
				</div>
			</c:when>
			<c:otherwise>
			<form method="POST" action="resetStudentResult.html" >
				<input type="hidden" name="type" value="create">
				<!-- start: page -->
					 <div class="row">
						<div class="col-md-8 col-md-offset-2">
						  <form id="form1">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Standard &amp; Course</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Programme</label>
		                                            <select class="form-control" id=course name="course">
		                                            <option value="">Select</option>
		                                                <c:forEach var="course" items="${courseList}" varStatus="i">
															<option value="${course.courseCode}">${course.courseName}</option>
														</c:forEach>
		                                            </select>
		                                        </div>	
											</div>
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Section</label>
		                                            <select class="form-control" id="section" name="section">
		                                            	<option value="">Select</option>
		                                            </select>
		                                        </div>
											</div>
											<!-- <div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Course</label>
		                                            <select class="form-control" id="course" name="course">
		                                            	<option value="">Select</option>
		                                            </select>
		                                        </div>
											</div> -->
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Subject</label>
		                                            <select class="form-control" id="subject" name="subject">
		                                            	<option value="">Select</option>
		                                            </select>
		                                        </div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Exam</label>
		                                            <select class="form-control" id="exam" name="exam">
		                                            	<option value="">Select</option>
		                                            </select>
		                                        </div>
											</div>
										</div>
                                          
									</div>
									<footer style="display: block;" class="panel-footer">
		                                <button class="btn btn-primary">Submit </button>
		                                <button type="reset" class="btn btn-default">Reset</button>
		                            </footer>
								</section>
                            </form>
						</div>
                        
			</form>
			</c:otherwise>
			</c:choose>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
	</body>


</html>