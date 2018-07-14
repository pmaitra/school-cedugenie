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
		
	function getQuestionFolderNames(element, dirName){	
		document.getElementById("downloadDiv").setAttribute("style", "display: none;");
		if($(element.id).val() != ''){
			var paperDirName = '';
			/* if(dirName == "standardDir"){
				paperDirName = $(element).val();
			} */
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
			$.ajax({
		        url: '/cedugenie/getQuestionFolderNames.html',
		        data: "paperDirName=" + paperDirName,
		        dataType: 'json',
		        success: function(data) {
	        		var options = "<option value='0'>Select</option>";
	        		var rowText = "<tr><th>Download Question Papers</th></tr>";
		        	if(data != null && data != ""){
		        		data = data.split("~");
		        		for (var i=0;i<data.length-1;i++){
		        			if(dirName == "paperFiles"){
		        				rowText = rowText + "<tr><td><a href='viewDownloadQuestionPapers.html?folderParam="+paperDirName+"&fileParam="+data[i]+"'>" + data[i] + "</a></td></tr>";
		        			}else{
		        				options = options+"<option value='"+data[i]+"'>"+data[i]+"</option>";
		        			}
						}
		        		/* if(dirName == "standardDir"){
		        			var standardObject = document.getElementById("standard");
		        			standardObject.innerHTML = options;
		        		} */
		        		if(dirName == "courseDir"){
		        			var courseObject = document.getElementById("course");
		        			courseObject.innerHTML = options;
		        		}
		        		if(dirName == "examDir"){
		        			var examObject = document.getElementById("exam");
		        			examObject.innerHTML = options;
		        		}
		        		if(dirName == "subjectDir"){
		        			var subjectObject = document.getElementById("subject");
		        			subjectObject.innerHTML = options;
		        		}
		        		if(dirName == "paperFiles"){
		        			document.getElementById("downloadDoc").innerHTML = rowText;
		        			document.getElementById("downloadDiv").setAttribute("style", "visibility: visible;");
		        		}
		        	}
		        }
			});
		}	
	}
	function message()
	{
		
		 $("#course").val('0');
		 $("#subject").val('0');
		 $("#exam").val('0');
	}
	</script>
</head>
	<body>
		<div class="btnsarea01">
		<c:if test="${uploadQPaperStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${uploadQPaperStatus}</span>	
			</div>
		</c:if>
		</div>
			<c:choose>
			<c:when test="${academicYearList eq null || empty academicYearList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<c:if test="${academicYearList eq null || empty academicYearList}">
							<span id="errormsg">No Question paper uploaded yet.</span>	
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

										<h2 class="panel-title">Programme &amp; Course</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Session</label>
		                                            <select class="form-control" id="year" name="academicYear" onchange="message()">
		                                            	<option value="">Select</option>
														<c:forEach var="academicYear" items="${academicYearList}" varStatus="i">
															<option value="${academicYear}">${academicYear}</option>
														</c:forEach>
		                                            </select>
		                                        </div>
											</div>
											
											<%--  <div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Standard</label>
		                                            <select class="form-control" id="standard" name="standard" onchange="getQuestionFolderNames(this, 'courseDir');">
		                                            	<option value="">Select</option>
														<c:forEach var="standard" items="${standardList}" varStatus="i">
															<option value="${standard.standardCode}">${standard.standardName}</option>
														</c:forEach>
													</select>
		                                        </div>	
											</div>  --%>
											
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Programme</label>
		                                            <select class="form-control" id="course" name="course" onchange="getQuestionFolderNames(this, 'examDir');" >
		                                            	<option value="0">Select</option>
		                                            	 <c:forEach var="course" items="${courseList}" varStatus="i">
															<option value="${course.courseCode}">${course.courseName}</option>
														</c:forEach> 
		                                            </select>
		                                        </div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Exam</label>
		                                            <select class="form-control" id="exam" name="exam" onchange="getQuestionFolderNames(this, 'subjectDir');" >
		                                            	<option value="0">Select</option>
		                                            </select>
		                                        </div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Course</label>
		                                            <select class="form-control" id="subject" name="subject" onchange="getQuestionFolderNames(this, 'paperFiles');" >
		                                            	<option value="0">Select</option>
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
							
									<h2 class="panel-title">Download Question Papers</h2>
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