<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>View Student Profile List</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

				<c:choose>
					<c:when test="${studentList == null}">
						<!-- <div class="errorbox" id="errorbox"  style="visibility:visible;">
							<span id="errormsg">No Student Profile Found</span>	
						</div> -->
						<div class="alert alert-danger">
							<strong>No Student Profile Found</strong>
						</div>
					</c:when>
				<c:otherwise>
					 <div class="row">
						<div class="col-md-6 col-md-offset-3">
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">View Student List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
									<thead>
										<tr>
											<th>Student Photo</th>
											<th>Student Name</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="student" items="${studentList}">
										<tr>
											<c:choose>
												<c:when test="${student.resource.desc ne null}">
													<td>
														<%-- <a href="viewStudentProfileDetails.html?registrationId=${student.resource.registrationId}"> --%>
														<a href="viewStudentProfileDetails.html?roll=${student.userId}">
														<img id = "preview" style="margin: 0px auto; width:60px;" class="rounded img-responsive" src="StudentImage/${student.resource.desc}" alt="Image Not Found">
														</a>
													</td>
												</c:when>
											<c:otherwise>												
											<td>
												<%-- <a href="viewStudentProfileDetails.html?registrationId=${student.resource.registrationId}"> --%>
												<a href="viewStudentProfileDetails.html?roll=${student.userId}">
													<c:choose>
														<c:when test="${student.resource.gender eq 'M'}">
															<img id="preview" style="margin: 0px auto; width:60px;" class="rounded img-responsive" src="assets/images/user.png" alt="Image Not Found">
														</c:when>
													<c:otherwise>
														<img id="preview" style="margin: 0px auto; width:60px;" class="rounded img-responsive" src="assets/images/user.png" alt="Image Not Found">	
													</c:otherwise>
													</c:choose>
												</a>
											</td>
											</c:otherwise>
											</c:choose>
											<td>
												<%-- <a href="viewStudentProfileDetails.html?registrationId=${student.resource.registrationId}"> --%>
												<a href="viewStudentProfileDetails.html?roll=${student.userId}">
												${student.studentName}	
												</a>
											</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</section>
						</div>
					</div>
				</c:otherwise>
				</c:choose>	
					


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
</body>
</html>