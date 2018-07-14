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
	<title></title>
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
		
	
	
	function getAttendanceFolderNames(){	
		document.getElementById("downloadDiv").setAttribute("style", "display: none;");
		
		paperDirName =  $("#year").val()+"/"+$("#month").val()+"/"+$("#course").val()+"/"+$("#batch").val()
	
		$.ajax({
	        url: '/cedugenie/getAssignmentStudentExcelName.html',
	        data: "paperDirName=" + paperDirName,
	        dataType: 'json',
	        success: function(data) {
	        	
        		var options = "<option value=''>Select</option>";
        		var rowText = "<tr><th>Download Question Papers</th></tr>";
	        	if(data != null && data != ""){
	        		data = data.split("~");
	        		
	        		for (var i=0;i<data.length-1;i++){
	        		
	        			rowText = rowText + "<tr><td><a href='resourceStudentAttendanceDownload.html?folderParam="+paperDirName+"&fileParam="+data[i]+"'>" + data[i] + "</a></td></tr>";
	        			
	        		}
	        	}
	        		    		document.getElementById("downloadDoc").innerHTML = rowText;
	    		document.getElementById("downloadDiv").setAttribute("style", "visibility: visible;");
	        }
		});
		
	}
	$(document).ready(function(){	
		$("#year").change(function (){
			
			document.getElementById("month").removeAttribute("disabled");
		});
		$("#month").change(function (){
			var currentMonth =	new Date().getMonth() ;
			var currentYear=new Date().getFullYear();
			var userYear=document.getElementById("year").value;
			var userMonth=document.getElementById("month").value;
			
			
			if(userYear<currentYear )
				{
				
				document.getElementById("resourceType").removeAttribute("disabled");
				document.getElementById("javascriptmsg").style.display = 'none';
				}
			else if(userYear=currentYear)
				{
				if(userMonth<=currentMonth)
					{
					
					document.getElementById("resourceType").removeAttribute("disabled");
					document.getElementById("javascriptmsg").style.display = 'none';
					}
				else
					{
					
					document.getElementById("javascriptmsg").style.display = 'block';			
					document.getElementById("javascriptmsg").innerHTML = "!!!!! Input is greater than current Date !!!!!";
					}
				}
			 else
				 {
				
				document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "!!!!! Input is greater than current Date !!!!!";
				 }
			
		});
		$("#resourceType").change(function (){
			document.getElementById("course").removeAttribute("disabled");
		});
		$("#course").change(function (){
			
			document.getElementById("batch").removeAttribute("disabled");
		});
		$("#course").change(function (){
				$.ajax({
			        url: '/cedugenie/getStudentBatchAgainstCourse.html',
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
				})
			
			});
		$("#year").change(function (){
			

			 $("#month").val('0');
			 $("#resourceType").val('0');
			 $("#course").val('0');
				$("#batch").val('0');
		
			
				document.getElementById("resourceType").setAttribute("disabled","disabled");
				document.getElementById("course").setAttribute("disabled","disabled");
		
		});
	});
		
		
			
	</script>
</head>
	<body>
	<div class="alert alert-danger" id="javascriptmsg" style="display: none">
							  									<span> </span>	
								</div> 
		
			<c:choose>
			<c:when test="${academicYearList eq null || empty academicYearList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<c:if test="${academicYearList eq null || empty academicYearList}">
							<span id="errormsg">No Attendance uploaded yet.</span>	
						</c:if>
					</div>
				</div>
			</c:when>
			<c:otherwise>
			<form method="POST" action="uploadQuestionPaper.html" enctype="multipart/form-data">
				<input type="hidden" name="type" value="create">
				<!-- start: page -->
					 <div class="row">
						<div class="col-md-8 col-md-offset-2">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Select   Student   Details</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Session</label>
		                                            <select class="form-control" id="year" name="academicYear"  required>
		                                            	<option value="">Select</option>
														<c:forEach var="academicYear" items="${academicYearList}" varStatus="i">
															<option value="${academicYear}">${academicYear}</option>
														</c:forEach>
		                                            </select>
		                                        </div>
											</div>
											
										
											<div class="col-md-6">
												<div class="form-group">
							                          <label class="control-label">Month</label>
							                              <select class="form-control" id="month" name="month" disabled required>
							                                 	<option value="0">Select...</option>
						                                  		<option value="1">January</option>
																<option value="2">February</option>
																<option value="3">March</option>
																<option value="4">April</option>
																<option value="5">May</option>
																<option value="6">June</option>
																<option value="7">July</option>
																<option value="8">August</option>
																<option value="9">September</option>
																<option value="10">October</option >
																<option value="11">November</option>
																<option value="12">December</option>
							                              </select>
							                       </div>
											</div>
											<div class="col-md-6">
												 <div class="form-group">
							                          <label class="control-label">Resource Type</label>
							                              <select class="form-control" id="resourceType" name="resourceType" disabled required>
							                                  <option value="0">Select...</option>
								                                  <c:forEach var="resourceType" items="${resourceTypeList}">
																	<c:if test="${resourceType.resourceTypeName eq 'STUDENT'}">
																		<option VALUE="${resourceType.resourceTypeName}">${resourceType.resourceTypeName}</option>
																	</c:if>
																</c:forEach>
							                              </select>
							                      </div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Programme<span class="required" aria-required="true">*</span></label>
		                                            <select class="form-control" id="course" name="course" disabled required>
		                                            	<option value="0">Select...</option>
		                                            	<c:forEach var="course" items="${courseList}" varStatus="i">
															<option value="${course.courseCode}">${course.courseName}</option>
														</c:forEach>
		                                            </select>
		                                        </div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Batch<span class="required" aria-required="true">*</span></label>
		                                            <select class="form-control" id="batch" name="batch" onchange="getAttendanceFolderNames()" disabled required>
		                                            	<option value="0">Select</option>
		                                            </select>
		                                        </div>
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
							
									<h2 class="panel-title">Download Student Attendance</h2>
								</header>
								<div class="panel-body" id="downloadDiv" style="display: none;">
									<table class="table table-bordered table-striped mb-none" id="downloadDoc">
										
									</table>
								</div>
	                            
                            </section>
                        </div>
			</form>
			</c:otherwise>
			</c:choose>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
	</body>


</html>