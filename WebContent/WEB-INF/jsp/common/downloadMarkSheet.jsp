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
	$(document).ready( function(){
		$("#term").change(function (){
			$.ajax({
				url: '/cedugenie/getMarksheet.html',
				dataType: 'json',
				data: "course=" + $("#course").val()+ "&term=" + ($("#term").val())+ "&batch=" + ($("#batch").val())+ "&userId="+($("#userId").val()),
				success: function(data) {
					//alert(data);
					var rowText = "<tr><th>Download MarkSheet Papers</th></tr>";
		        	if(data != null && data != ""){
		        	var	data1 = data.split(";");
		        	var data2 = data1[0].split("~");	
		        		for (var i=0;i<data2.length-1;i++){
		        			//if(dirName == "paperFiles"){
		        				rowText = rowText + "<tr><td><a href='viewDownloadMarkSheet.html?folderParam="+data1[1]+"&fileParam="+data2[i]+"'>" + data2[i] + "</a></td></tr>";
		        			//}
		        		}
		        		
		        	}
		        	document.getElementById("downloadDoc").innerHTML = rowText;
        			document.getElementById("downloadDiv").setAttribute("style", "visibility: visible;");
				}
			});
		});
		

		
		
		
	});
		

	
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

										<h2 class="panel-title">Download MarkSheet</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										<div class="row">
											
											
											<div class="col-md-6">
												<div class="form-group">
		                                            <label class="control-label">Programme<span class="required" aria-required="true">*</span></label>
		                                         
		                                            <input type = "hidden" name = "course" id ="course" value = "${course.courseCode}" >
		                                             <input type = "hidden" name = "userId" id ="userId" value = "${userId}" >
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
		                                            <select class="form-control" id="term" name="term" required  >
		                                            	<option value="">Select</option>
		                                            	<c:forEach var="term" items="${termList}" varStatus="i">
															<option value="${term.termCode}">${term.termName}</option>
														</c:forEach>
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
							
									<h2 class="panel-title">Download MarkSheet Papers</h2>
								</header>
								<div class="panel-body" id="downloadDiv" style="display: none;">
									<table class="table table-bordered table-striped mb-none" id="downloadDoc">
										
									</table>
								</div>
	                            
                            </section>
                        </div>
                       </div>
			</form>
			<%-- </c:otherwise>
			</c:choose> --%>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
	</body>


</html>