<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Create Subject Group</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>

</head>
<body>
				<%-- <c:if test="${insertUpdateStatus eq success}">
					<div class="alert alert-success">
						<strong>Data inserted successfully.</strong>	
					</div>
				</c:if>
				<c:if test="${insertUpdateStatus eq success}">
					<div class="alert alert-danger">
						<strong>Data insertion failed.</strong>	
					</div>
				</c:if> --%>
                    <div class="row">
                    <form action="editGradingSystem.html" method="post">
						<div class="col-md-4">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Grading System</h2>
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
                                            <label class="control-label">Programme Name</label>
                                            <select class="form-control" id="standard" name="standard" required>
                                            <option value="">Select..</option>
                                                <c:forEach var="standard" items="${standardList}" varStatus="i">
													<option value="${standard.standardCode}">${standard.standardName}</option>
												</c:forEach>
                                            </select>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label">Subject</label>
                                            <select class="form-control" id="course" name="course" required>
                                            	<option value="">Select..</option>
                                            </select>
                                        </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
						</div>

						
					
						<div class="col-md-8">
								
	                            <section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									</div>
							
									<h2 class="panel-title">Course Wise Grade-Grade Point</h2>
								</header>
								<div class="panel-body">
									
									<table class="table table-bordered table-striped mb-none">
										<thead>
											<tr>
												<th>Marks Range</th>
												<th>Grade</th>
												<th>Grade Point</th>
											</tr>
										</thead>
										<tbody>
											<tr  class="gradeX">
												<td>91 - 100<input type="hidden" name="range" value="91-100"></td>
												<td><input type="text" class="form-control" name="grade91-100" id="grade91-100" required></td>
												<td><input type="text" class="form-control" name="point91-100" id="point91-100" required></td>
											</tr>
											<tr  class="gradeX">
												<td>81 - 90<input type="hidden" name="range" value="81-90"></td>
												<td><input type="text" class="form-control" name="grade81-90" id="grade81-90" required></td>
												<td><input type="text" class="form-control" name="point81-90" id="point81-90" required></td>
											</tr>
											<tr  class="gradeX">
												<td>71 - 80<input type="hidden" name="range" value="71-80"></td>
												<td><input type="text" class="form-control" name="grade71-80" id="grade71-80" required></td>
												<td><input type="text" class="form-control" name="point71-80" id="point71-80" required></td>
											</tr>
											<tr  class="gradeX">
												<td>61 - 70<input type="hidden" name="range" value="61-70"></td>
												<td><input type="text" class="form-control" name="grade61-70" id="grade61-70" required></td>
												<td><input type="text" class="form-control" name="point61-70" id="point61-70" required></td>
											</tr>
											<tr  class="gradeX">
												<td>51 - 60<input type="hidden" name="range" value="51-60"></td>
												<td><input type="text" class="form-control" name="grade51-60" id="grade51-60" required></td>
												<td><input type="text" class="form-control" name="point51-60" id="point51-60" required></td>
											</tr>
											<tr  class="gradeX">
												<td>41 - 50<input type="hidden" name="range" value="41-50"></td>
												<td><input type="text" class="form-control" name="grade41-50" id="grade41-50" required></td>
												<td><input type="text" class="form-control" name="point41-50" id="point41-50" required></td>
											</tr>
											<tr  class="gradeX">
												<td>33 - 40<input type="hidden" name="range" value="33-40"></td>
												<td><input type="text" class="form-control" name="grade33-40" id="grade33-40" required></td>
												<td><input type="text" class="form-control" name="point33-40" id="point33-40" required></td>
											</tr>
											<tr  class="gradeX">
												<td>21 - 32<input type="hidden" name="range" value="21-32"></td>
												<td><input type="text" class="form-control" name="grade21-32" id="grade21-32" required></td>
												<td><input type="text" class="form-control" name="point21-32" id="point21-32" required></td>
											</tr>
											<tr  class="gradeX">
												<td>0 - 20<input type="hidden" name="range" value="0-20"></td>
												<td><input type="text" class="form-control" name="grade0-20" id="grade0-20" required></td>
												<td><input type="text" class="form-control" name="point0-20" id="point0-20" required></td>
											</tr>
										</tbody>
									</table>
								</div>
								</section>
							</div>
						</form>
					</div>
 					<div id="dialog" class="modal-block mfp-hide">
						<section class="panel">
							<header class="panel-heading">
								<h2 class="panel-title">Are you sure?</h2>
							</header>
							<div class="panel-body">
								<div class="modal-wrapper">
									<div class="modal-text">
										<p>Are you sure that you want to delete this row?</p>
									</div>
								</div>
							</div>
							<footer class="panel-footer">
								<div class="row">
									<div class="col-md-12 text-right">
										<button id="dialogConfirm" class="btn btn-primary">Confirm</button>
										<button id="dialogCancel" class="btn btn-default">Cancel</button>
									</div>
								</div>
							</footer>
						</section>
					</div>
					
					
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
	$(document).ready( function(){
		$("#standard").change(function (){
			$.ajax({
				url: '/icam/getCourseForStandard.html',
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
		});
		
		$("#course").change(function (){
			$.ajax({
				url: '/icam/getGradingSystemForCourse.html',
				dataType: 'json',
				data: "course=" + $("#course").val(),
				success: function(data) {
					if(data!=""){
						data=data.split("~");
						for(var i=0;i<data.length-1;i++){
							var subData=data[i].split("*");
							if(subData[0]!="null"){
								if(subData[1]!="null")
									document.getElementById("grade"+subData[0]).value=subData[1];
								if(subData[2]!="null")
									document.getElementById("point"+subData[0]).value=subData[2];
							}
						}
					}
				}
			});
		});
	});
</script>
</body>
</html>