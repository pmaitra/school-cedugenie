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
<title>Current Drives</title>
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
			<h2>Current Drives</h2>
		</header>
		<div class="content-padding">
			<div class="row">
				<div class="col-md-12">
                     <section class="panel">
                         <header class="panel-heading">
				        	<c:choose>
							<c:when test="${drivestate == 'completed'}">
									 <h2 class="panel-title"> Previous Admission Drives</h2>
							</c:when>
							<c:otherwise>
									 <h2 class="panel-title">Current Admission Drives </h2>
							</c:otherwise>
							</c:choose>
	                        </header>
                         	<div class="panel-body">
                               <table id="datatable-tabletools" class="table table-bordered table-striped mb-none">
                                   <thead>
                                       <tr>
                                           	<th>Drive name</th>
											<th>Standard</th>
											<th>Number Of Openings</th>
											<th>Status</th>	
                                       </tr>
                                   </thead>
                                   <tbody>
                               			<c:forEach var="driveName" items="${AdmissionDriveList}">
                                   		<c:choose>
										<c:when test="${AdmissionDriveList[0].status == 'DONE'}">
											<tr class="gradeX" onClick="window.open('admissionOnProcess.html?courseClass=<c:out value="${driveName.courseClass}"/>&year=<c:out value="${driveName.admissionFormYear}"/>&admissionDriveState=<c:out value="${AdmissionDriveList[0].status}"/>&driveName=<c:out value="${driveName.admissionDriveName}"/>'),'_blank'" style="cursor:pointer;">
												<td>
													<c:out value="${driveName.admissionDriveName}"/>
												</td>
												<td><c:out value="${driveName.courseName}"/></td>
												<td><c:out value="${driveName.noOfOpenings}"/></td>
												<td><c:out value="${driveName.status}"/></td>
											</tr>
										</c:when>	
										<c:otherwise>
											<tr>
												<td>
													<c:out value="${driveName.admissionDriveName}"/>
												</td>
												<td><c:out value="${driveName.courseName}"/></td>
												<td><c:out value="${driveName.noOfOpenings}"/></td>
												<td><c:out value="${driveName.status}"/></td>
											</tr>
										</c:otherwise>
										</c:choose>
									</c:forEach>
                                    </tbody>
                                </table>
                           </div>
                      	</section>
					</div>
				</div>
			</div>
<script src="/cedugenie/js/admission/viewMeritList.js"></script>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>