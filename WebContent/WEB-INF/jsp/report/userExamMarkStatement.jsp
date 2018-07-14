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
	<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
	<style type="text/css">
	       .scroll-to-top{
	           display: none !important;
	       }.mb-md{
	       	   display: none;
	       }
	</style>
</head>
<body>
		<c:if test="${insertUpdateStatus ne null}">
			<div class="alert alert-success" id="infomsgbox1"  >
				<strong>${insertUpdateStatus}</strong>	
			</div>
		</c:if>
		<div class="row">
						<div class="col-md-6 col-md-offset-3">
						  <form action="getStudentReportFORXI_XII.html" method="post" class="form-horizontal">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">User Exam Mark Statement </h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
											<label class="col-sm-4 control-label">Academic Year </label>
											<div class="col-sm-8">
												<select class="form-control" id="academicYearName" name="academicYearCode" required>
                                                    <option value="">Select...</option>
                                                    <c:forEach var="academicYear" items="${academicYearList}">	
														<option value="${academicYear.academicYearCode}">${academicYear.academicYearName}</option>
													</c:forEach>
                                                </select>
											</div>
										</div> 
                                        <div class="form-group">
											<label class="col-sm-4 control-label">Select Class </label>
											<div class="col-sm-8">
												<select class="form-control" id="standardName" name="standardCode" required>
                                                    <option value="">Select...</option>
                                                    <c:forEach var="standard" items="${standardList}">	
														<option value="${standard.standardCode}">${standard.standardName}</option>
													</c:forEach>
                                                </select>
											</div>
										</div> 
                                        <div class="form-group">
											<label class="col-sm-4 control-label">Select Section </label>
											<div class="col-sm-8">
												<select class="form-control" id="sectionName" name="sectionCode" required>
                                                    <option value="">Select...</option>
                                                </select>
											</div>
										</div> 
                                        <div class="form-group">
											<label class="col-sm-4 control-label">Select Examination </label>
											<div class="col-sm-8">
												<select class="form-control" id="examName" name="examName" required>
                                                    <option value="">Select...</option>
                                                </select>
											</div>
										</div> 
                                        
										<div class="form-group">
											<label class="col-sm-4 control-label">Select Student(s) </label>
											<div class="col-sm-8">	
												<select class="form-control" id="studentName" name="roll" multiple = "multiple" required>
                                                  <!--   <option value="">Select...</option> -->
                                                </select>											
                                                <!-- <ul class="ulList form-control" style="height:auto; padding: 6px 12px; max-height:200px;">
                                                    <li><input type="checkbox" id="check-1"/> <label for="check-1">Select All Student</label></li>
                                                    <li><input type="checkbox" id="check-2"/> <label for="check-2">4425 - Amar Das</label></li>
                                                    <li><input type="checkbox" id="check-3"/> <label for="check-3">4426 - Amit Das</label></li>
                                                    <li><input type="checkbox" id="check-4"/> <label for="check-4">4427 - Amar Das</label></li>
                                                    <li><input type="checkbox" id="check-5"/> <label for="check-5">4428 - Asim Das</label></li>
                                                    <li><input type="checkbox" id="check-6"/> <label for="check-6">4429 - Avik Das</label></li>
                                                    <li><input type="checkbox" id="check-5"/> <label for="check-5">4428 - Asim Das</label></li>
                                                    <li><input type="checkbox" id="check-6"/> <label for="check-6">4429 - Avik Das</label></li>
                                                    <li><input type="checkbox" id="check-5"/> <label for="check-5">4428 - Asim Das</label></li>
                                                    <li><input type="checkbox" id="check-6"/> <label for="check-6">4429 - Avik Das</label></li>
                                                </ul> -->
											</div>
										</div>
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button  class="btn btn-primary">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
						
					</div>	
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/report/userExamMarkStatement.js"></script>
</body>


</html>