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
<title>Student Details Form</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       } .mb-md{
       	   display: none;
       }
</style>

</head>
<body>
		<header class="page-header">
			<h2>In Active Programs</h2>	<!--  ADDED BY SAIF 29-03-2018 -->
		</header>
			<div class = "content-padding">
									<div class="row">
						<c:if test="${updateFailStatus ne null }">
							<div class="alert alert-success" id="successboxmsgbox">
								<strong>${updateFailStatus}</strong>	
							</div>
						</c:if>
						<c:if test="${updateSuccessStatus ne null }">
							<div class="alert alert-success" id="successboxmsgbox">
								<strong>${updateSuccessStatus}</strong>	
							</div>
						</c:if>
						<div class="col-md-12 ">	
                           	<input type="hidden" name="saveId" id="saveId">
                           	<input type="hidden" name="statusValue" id="statusValue">
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Inactive Programs </h2>
                                </header>
                                <div class="panel-body">

                                    <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                        <thead>
                                            <tr>
												<th>Standard</th>
												<th>Name</th>
												<th>Type</th>
												<th>Duration</th>
												<th>Admission Available From Date</th>
												<th>Admission Available To Date</th>		
												<th>Actions</th>					
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <c:forEach var="course"  items="${courseList}" varStatus="i">
	                                                <tr>
	                                             		<td>${course.courseClass}</td>
	                                             		<td>${course.courseName}</td>
	                                             		<td>${course.courseType}</td>
 	                                                    <td>${course.courseDuration}</td>
 	                                                    <td>${course.admissionDriveStartDate}</td>
 	                                                    <td>${course.admissionDriveExpectedEndDate}</td>
 	                                                    <td class="actions">
															<a href="setCourseInactive.html?courseCode=${course.courseCode}&oldStandard=${course.courseClass}&oldName=${course.courseName}&oldType=${course.courseType}&oldDuaration=${course.courseDuration}&oldAdmissionAvailableFromdate=${course.admissionDriveStartDate}&oldAdmissionAvailableTodate=${course.admissionDriveExpectedEndDate}" class="on-default remove-row" id = "delete"><i class="fa fa-trash-o"></i></a>
														 </td>
	                                                </tr>
	                                              </c:forEach> 
                                        </tbody>
                                    </table>
                                </div>
                            </section>
						</div>
					</div>

			</div>
					
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>