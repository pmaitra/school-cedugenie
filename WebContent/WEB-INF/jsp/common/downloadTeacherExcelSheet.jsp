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
	<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
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
		/* if($(element.id).val() != ''){
			var paperDirName = '';
			if(dirName == "standardDir"){
				paperDirName = $(element).val();
			}
			if(dirName == "courseDir"){
				paperDirName = $("#year").val()+"/"+$(element).val();
			}
			if(dirName == "examDir"){
				paperDirName = $("#year").val()+"/"+$(element).val();
			}
			if(dirName == "subjectDir"){
				paperDirName = $("#year").val()+"/"+$("#course").val()+"/"+$(element).val();
			}
			if(dirName == "paperFiles"){
				paperDirName = $("#year").val()+"/"+$("#course").val()+"/"+$("#exam").val()+"/"+$(element).val();
			}
		} */
		paperDirName =  $("#year").val()+"/"+$("#month").val()+"/"+$("#jobType").val()+"/"
		
		$.ajax({
	        url: '/icam/getAssignmentExcelName.html',
	        data: "paperDirName=" + paperDirName,
	        dataType: 'json',
	        success: function(data) {
	        	
        		var options = "<option value=''>Select</option>";
        		var rowText = "<tr><th>Download Question Papers</th></tr>";
	        	if(data != null && data != ""){
	        		data = data.split("~");
	        	
	        		for (var i=0;i<data.length-1;i++){
	        			//if(dirName == "paperFiles"){
	        				
	        				rowText = rowText + "<tr><td><a href='resourceAttendanceDownload.html?folderParam="+paperDirName+"&fileParam="+data[i]+"'>" + data[i] + "</a></td></tr>";
	        			//}
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
			document.getElementById("jobType").removeAttribute("disabled");
		});
		$("#year").change(function (){
			
			
			 $("#month").val('0');
			 $("#resourceType").val('0');
			 $("#jobType").val('0');
			 document.getElementById("resourceType").setAttribute("disabled","disabled");
				document.getElementById("jobType").setAttribute("disabled","disabled");
				
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

										<h2 class="panel-title"> Select Teacher Details</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Session</label>
		                                            <select class="form-control" id="year" name="academicYear" required>
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
																	<c:if test="${resourceType.resourceTypeName eq 'TEACHING STAFF'}">
																		<option VALUE="${resourceType.resourceTypeName}">${resourceType.resourceTypeName}</option>
																	</c:if>
																</c:forEach>
							                              </select>
							                      </div>
											</div>
											<div class="col-md-6">
						                        <div class="form-group">
						                          <label class="control-label">JOB Type</label>
						                              <select class="form-control" id="jobType" name="jobType" onchange="getAttendanceFolderNames()"required disabled>
						                                  <option value="0">Select...</option>
						                                  <c:forEach var="jobType" items="${jobType}">
														
															<option VALUE="${jobType.jobTypeName}">${jobType.jobTypeName}</option>
									
															</c:forEach>
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
							
									<h2 class="panel-title">Download Teacher Attendance</h2>
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

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
	</body>


</html>