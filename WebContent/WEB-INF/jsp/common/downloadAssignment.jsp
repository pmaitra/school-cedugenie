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
	$(document).ready( function(){
		$("#academicYear").change(function (){
			document.getElementById("term").removeAttribute("disabled");
			//alert("in");

			/* $.ajax({
		        url: '/icam/getSectionAgainstCourse.html',
		        dataType: 'json',
		        data:"course=" + $("#course").val(),
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
		        	document.getElementById("batch").innerHTML=options;
		       }
			}); */
			
			
			
			/* $.ajax({
				url: '/icam/getExamsForCoursesession.html',
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
			    url: '/icam/getTermsForACourse.html',
			    dataType: 'json',
			    data:"course=" + $("#course").val(),
			    success: function(data) {	
			    	//alert("data ====="+data);
			    	var options='<option value="">Select</option>';
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
		//	document.getElementById("batch").removeAttribute("disabled");
			
		});
		
		
		$("#term").change(function (){
			document.getElementById("subject").removeAttribute("disabled");
			
			//alert("1");
			$.ajax({
				url: '/icam/getSubjectsForTermAndCourse.html',
				dataType: 'json',
				data: "course=" + $("#course").val()+"&term=" + ($("#term").val()),
				success: function(data) {					
					var options="<option value=''>Select</option>";
		        	if(data != null && data !="")
					{
		        		data=data.split("#*#");
						for (var i=0;i<data.length-1;i++){   
							var sub=data[i].split("#~#");
							options=options+"<option value='"+sub[1]+"'>"+sub[1]+"</option>";
						}
					}
		        	document.getElementById("subject").innerHTML=options;					
				}
			});
			//alert("2");
			$.ajax({
				url: '/icam/getExamsForTermAndCourse.html',
				dataType: 'json',
				data: "course=" + $("#course").val()+ "&term=" + ($("#term").val()),
				success: function(data) {
					var options='<option value="">Select</option>';
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
			
			
		});
		
		$("#subject").change(function (){
			
			document.getElementById("exam").removeAttribute("disabled");
			
	
			
			//alert("hiiiiiii");
			$.ajax({
				url: '/icam/getTeacherAgainstCourse.html',
				dataType: 'json',
				data: "course=" + $("#course").val()+ "&term=" + ($("#term").val())+ "&subject=" + ($("#subject").val()),
				success: function(data) {
					//alert(data);
					var options='<option value="">Select</option>';
					if(data!=""){
						data=data.split("~*~");
						for(var i=0;i<data.length-1;i++){				
							options=options+'<option value="'+data[0]+'">'+data[1]+'</option>';
					}
					}
					document.getElementById("teacher").innerHTML=options;
				}
			});
		});
		
		$("#exam").change(function (){
			document.getElementById("teacher").removeAttribute("disabled");
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
		
	function getAssignmentFolderNames(element, dirName){	
		document.getElementById("downloadDiv").setAttribute("style", "display: none;");
		if($(element.id).val() != ''){
			var paperDirName = '';
		
			//alert("dirName=="+dirName);
			if(dirName == "teacherdir"){
				paperDirName = $("#academicYear").val()+"/"+$("#subject").val()+"/"+$("#term").val()+"/"+$("#teacher").val()+"/"+$("#exam").val()+"/"+$("#course").val()+"/"+$("#batch").val();
				//alert("paperDirName"+paperDirName);
			}
			$.ajax({
		        url: '/icam/getAssignmentFolderNames.html',
		        data: "paperDirName=" + paperDirName,
		        dataType: 'json',
		        success: function(data) {
		        	//alert("kaustav"+data);
	        		var options = "<option value=''>Select</option>";
	        		var rowText = "<tr><th>Download Assignment Papers</th></tr>";
		        	if(data != null && data != ""){
		        		data = data.split("~");
		        		for (var i=0;i<data.length-1;i++){
		        			if(dirName == "teacherdir"){
		        				
		        				rowText = rowText + "<tr><td><a href='viewDownloadAssignment.html?folderParam="+paperDirName+"&fileParam="+data[i]+"'>" + data[i] + "</a></td></tr>"; 
		        						        		
		        			}else{
		        				
		        				options = options+"<option value='"+data[i]+"'>"+data[i]+"</option>";
		        			}
						}
		        		if(dirName == "teacherdir"){
		        			document.getElementById("downloadDoc").innerHTML = rowText;
		        			document.getElementById("downloadDiv").setAttribute("style", "visibility: visible;");
		        			}
		        		}
		        		
		        	}
		        })
			}
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
			<%-- <c:choose>
			<c:when test="${academicYearList eq null || empty academicYearList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<c:if test="${academicYearList eq null || empty academicYearList}">
							<span id="errormsg">No Question paper uploaded yet.</span>	
						</c:if>
					</div>
				</div>
			</c:when>
			<c:otherwise> --%>
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

										<h2 class="panel-title">Programme &amp; Course</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Session</label>
		                                            <select class="form-control" id="academicYear" name="academicYear" onchange="getAssignmentFolderNames(this, 'standardDir');" required>
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
		                                            <label class="control-label">Programme<span class="required" aria-required="true">*</span></label>
		                                          <%--   <select class="form-control" id="course" name="course" onchange="getAssignmentFolderNames(this, 'courseDir');" required>
		                                            	<option value="">Select</option>
		                                            	<c:forEach var="course" items="${courseList}" varStatus="i">
															<option value="${course.courseCode}">${course.courseName}</option>
														</c:forEach>
		                                            </select> --%>
		                                            <input type = "hidden" name = "course" id ="course" value = "${course.courseCode}" >
		                                            <input type = "text" class="form-control" name = "courseName" id ="courseName" value = "${course.courseName}" readonly>
		                                        </div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Batch<span class="required" aria-required="true">*</span></label>
		                                           <!--  <select class="form-control" id="batch" name="batch" onchange="getAssignmentFolderNames(this, 'batchDir');" required disabled>
		                                            	<option value="">Select</option>
		                                            </select> -->
		                                            <input type = "hidden" name = "batch" id ="batch" value = "${course.desc}" >
		                                            <input type = "text" class="form-control" name = "batchName" id ="batchName" value = "${course.desc}" readonly>
		                                        </div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Term<span class="required" aria-required="true">*</span></label>
		                                            <select class="form-control" id="term" name="term" onchange="getAssignmentFolderNames(this, 'paperFiles');" required disabled >
		                                            	<option value="">Select</option>
		                                            </select>
		                                        </div>
											</div>
											<div class="col-md-6">
											<div class="form-group">
		                                            <label class="control-label">Course<span class="required" aria-required="true">*</span></label>
		                                            <select class="form-control" id="subject" name="subject" onchange="getAssignmentFolderNames(this, 'subjectDir');" required disabled >
		                                            	<option value="">Select</option>
		                                            </select>
		                                        </div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Exam<span class="required" aria-required="true">*</span></label>
		                                            <select class="form-control" id="exam" name="exam" onchange="getAssignmentFolderNames(this, 'examDir');" required disabled>
		                                            	<option value="">Select</option>
		                                            </select>
		                                        </div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Select Teacher <span class="required" aria-required="true">*</span></label>
		                                            <select class="form-control" id="teacher" name="teacher" onchange="getAssignmentFolderNames(this, 'teacherdir');" required disabled>
		                                            	<option value="">Select</option>
		                                            	
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
							
									<h2 class="panel-title">Download Assignment Papers</h2>
								</header>
								<div class="panel-body" id="downloadDiv" style="display: none;">
									<table class="table table-bordered table-striped mb-none" id="downloadDoc">
										
									</table>
								</div>
	                            
                            </section>
                        </div>
			</form>
			<%-- </c:otherwise>
			</c:choose> --%>

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
	</body>


</html>