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
				
				
				$.ajax({
			        url: '/cedugenie/getSectionAgainstStandard.html',
			        dataType: 'json',
			        data: "standard=" + ($(this).val()),
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
				
			}); */
			
			$("#course").change(function (){

				$.ajax({
			        url: '/cedugenie/getSectionAgainstCourse.html',
			        dataType: 'json',
			        data:"course=" + $("#course").val(),
			        success: function(dataDB) {
			        	var options="<option value='0'>Select</option>";
			        	if(dataDB != "null" && dataDB !="")
						{
			        		var arr = dataDB.split(";");
							for (var i=0;i<arr.length;i++){   
								var section = arr[i].split("*");
								options=options+"<option value='"+section[0]+"'>"+section[1]+"</option>";
							}
						}
			        	document.getElementById("batch").innerHTML=options;
			       }
				});
				
				
				
				/* $.ajax({
					url: '/cedugenie/getExamsForCoursesession.html',
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
				}); */
				
				
				
				$.ajax({
				    url: '/cedugenie/getTermsForACourse.html',
				    dataType: 'json',
				    data:"course=" + $("#course").val(),
				    success: function(data) {	
				    	//alert("data ====="+data);
				    	var options='<option value="0">Select</option>';
				    	if(data!=null && data != ""){	        		
			    			var termArr1 = data.split("*~*");
			    			//var courseArr = courseArr1[0].split("#@#");
			    		
			    			for(var a=0; a<termArr1.length;a++){
			    				if(termArr1[a] != null && termArr1[a] != ""){
			    					var termNameAndCode=termArr1[a].split("#@#");  
											options=options+'<option value="'+termNameAndCode[0]+'">'+termNameAndCode[1]+'</option>';
									
			    				}        				
			    			}	        				
				    	}
				    	document.getElementById("term").innerHTML=options;
				    	/*else{
				    		alert("No Course Found For Class ")+ (thisClassNode.value);
				    	}*/
				    }
				}); 
				document.getElementById("batch").removeAttribute("disabled");
			});
			
			$("#batch").change(function (){
				document.getElementById("term").removeAttribute("disabled");
			});
			$("#term").change(function (){
				$.ajax({
					url: '/cedugenie/getSubjectsForACourseAndTermAndTeacher.html',
					dataType: 'json',
					data: "course=" + $("#course").val()+ "&term=" + ($("#term").val())+"&section=" + ($("#batch").val()),
					success: function(data) {					
						var options="<option value='0'>Select</option>";
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
					url: '/cedugenie/getExamsForTermAndCourse.html',
					dataType: 'json',
					data: "course=" + $("#course").val()+ "&term=" + ($("#term").val()),
					success: function(data) {
						var options='<option value="0">Select</option>';
						if(data!=""){
							data=data.split("*~*");						
							for(var i=0;i<data.length-1;i++){
								var examcodeName = data[i].split("#@#")
								options=options+'<option value="'+examcodeName[0]+'">'+examcodeName[1]+'</option>';
							}
						}
						document.getElementById("exam").innerHTML=options;
					}
				});
				document.getElementById("subject").removeAttribute("disabled");
			});
			
			$("#subject").change(function (){
				document.getElementById("exam").removeAttribute("disabled");
			});
			var p = 1;
			$(".addFileClassName").each(function(){
		   	$(this).click(function(){                        		
		   		var tableNode = $(this).parent().parent().parent();
		   		var row = $('<tr><td><input type="file" name="uploadFile.fileData" id="fileData'+p+'"/></td><td><img  src="images/minus_icon.png" onclick="deleteThisRow(this);" alt="Delete"></td></tr>');
		           $(tableNode).append(row); 
		           p++;
				});
			});
			
			
			
		});
		function deleteThisRow(obj){	
			var parent = obj.parentNode.parentNode;
			parent.parentNode.removeChild(parent);
		}
		
		
	function validate(){
		var academicYear=document.getElementById("academicYear").value;	
		if(academicYear == ""||academicYear =='null'){
			alert("Please Enter Academic Year");
			return false;
		}
		var standard=document.getElementById("standard").value;	
		if(standard == ""||"standard" =='null'){
			alert("Please Enter Standard");
			return false;
		}
		
		var course=document.getElementById("course").value;	
		if(course == ""||course =='null'){
			alert("Please Enter Course");
			return false;
		}
		
		var subject=document.getElementById("subject").value;	
		if(subject == ""||subject =='null'){
			alert("Please Enter Subject");
			return false;
		}
		var exam=document.getElementById("exam").value;	
		if(exam == ""||exam =='null'){
			alert("Please Enter Exam");
			return false;
		}
		var fileData0 = document.getElementById("fileData0").value;	
		if(fileData0 == ""||fileData0 =='null'){
			alert("Please Upload Atleast One File");
			return false;
		}
		
		return true;
	}
	
	function message()
	{
		 $("#course").val('0');
		 $("#batch").val('0');
		 $("#term").val('0');
		 $("#subject").val('0');
		 $("#exam").val('0');
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
							<span id="errormsg">Programme Not Found</span>	
						</c:if>
					</div>
				</div>
			</c:when>
			<c:otherwise> 
			<form method="POST" action="uploadAssignment.html" enctype="multipart/form-data">
				<input type="hidden" name="type" value="create">
				<!-- start: page -->
					 <div class="row">
						<div class="col-md-8 col-md-offset-2">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Upload Assignment</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Session<span class="required" aria-required="true">*</span></label>
		                                            <select class="form-control" id="academicYear" name="academicYear" required onchange="message()" >
		                                            	<option value="0">Select</option>
														<c:forEach var="acadYear" items="${academicYearList}" varStatus="i">
															<option value="${acadYear.academicYearCode}">${acadYear.academicYearName}</option>
														</c:forEach>
		                                            </select>
		                                        </div>
											</div>
											
											<%-- <div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Standard<span class="required" aria-required="true">*</span></label>
		                                            <select class="form-control" id="standard" name="standard">
		                                            	<option value="">Select</option>
														<c:forEach var="standard" items="${standardList}" varStatus="i">
															<option value="${standard.standardCode}">${standard.standardName}</option>
														</c:forEach>
													</select>
		                                        </div>	
											</div> --%>
											<!-- <input type = "hidden" name = "standard" id = "standard"> -->
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Programme<span class="required" aria-required="true">*</span></label>
		                                            <select class="form-control" id="course" name="course" required>
		                                            	<option value="0">Select</option>
		                                            	<c:forEach var="course" items="${courseList}" varStatus="i">
															<option value="${course.courseCode}">${course.courseName}</option>
														</c:forEach>
		                                            </select>
		                                        </div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Batch<span class="required" aria-required="true">*</span></label>
		                                            <select class="form-control" id="batch" name="batch" required disabled>
		                                            	<option value="0">Select</option>
		                                            </select>
		                                        </div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Term<span class="required" aria-required="true">*</span></label>
		                                            <select class="form-control" id="term" name="term" required disabled>
		                                            	<option value="0">Select</option>
		                                            </select>
		                                        </div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Course<span class="required" aria-required="true">*</span></label>
		                                            <select class="form-control" id="subject" name="subject" required disabled>
		                                            	<option value="0">Select</option>
		                                            </select>
		                                        </div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Exam<span class="required" aria-required="true">*</span></label>
		                                            <select class="form-control" id="exam" name="exam" required disabled>
		                                            	<option value="0 ">Select</option>
		                                            </select>
		                                        </div>
											</div>
											
										</div>
                                          
									</div>
								</section>
						</div>
                        
                        <div class="col-md-12">
                            <section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									</div>
							
									<h2 class="panel-title">Upload Assignment</h2>
								</header>
								<div class="panel-body" id="tableDiv">
									<table class="table table-bordered table-striped mb-none">
										<thead>
											<tr><th>File</th><th>Delete</th></tr>
										</thead>
										<tbody>
											<tr>
												<td><input type="file" name="uploadFile.fileData" id="fileData0" required/></td>
					 							<td><input id="addFile2" class="addFileClassName" type="button" value="ADD"/></td>
				 							</tr>
										</tbody>
									</table>
								</div>
	                            <footer style="display: block;" class="panel-footer">
	                                <button class="btn btn-primary" onclick = "return validate()">Submit </button>
	                                <button type="reset" class="btn btn-default">Reset</button>
	                            </footer>
                            </section>
                        </div>
			</form>
			</c:otherwise>
			</c:choose>
 
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
	</body>


</html>