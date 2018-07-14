<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listBookPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Available Course List</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Available Courses</h2>
</header>
<div class="content-padding">
 	<c:if test="${insertUpdateStatus eq 'success'}">
		<div class="alert alert-success"  >
				<strong>${msg}</strong>	
		</div>
	</c:if>
	<c:if test="${insertUpdateStatus eq 'exist'}">
		<div class="alert alert-danger"  >
				<strong>${msg}</strong>	
		</div>
	</c:if>
<%--	<c:if test="${message ne null}">
		<div class="errorbox" id="errorboxmsg" style="visibility: visible;" >
			<span id="errorbox">${message}</span>	
		</div>
	</c:if> --%>
<%-- 	<c:choose>
		<c:when test="${studentList eq null || empty studentList}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
					<span id="infomsgbox">Student List Not Found</span>	
				</div>
			</div>
		</c:when>
		<c:otherwise> --%>
			<form:form id="studentListForm" name="studentListForm" action="" method="POST">
				<section role="main" class="content-body">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
							</div>
					
							<h2 class="panel-title">Admission Drive List</h2>
						</header>
						<div class="panel-body">
							<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
								<thead>
									<tr>
										
										<th>Course Name</th>
										<th>No Of Openings</th>
										<th>Course Type</th>
										<!-- <th>Status</th> -->
									</tr>
								</thead>
								<tbody>
									<c:forEach var="course" items="${courseList}">
										<tr class="gradeC"  onClick="window.open('setupAdmissionDrive.html?courseCode=${course.courseCode}&courseName=${course.courseName}&courseType=${course.courseDesc}&noOfSeats=${course.serialId}','_self')" style="cursor:pointer;">
											
											<td>
												${course.courseName}
											</td>
											<td>
												${course.serialId}
											</td>
											<td>
												${course.courseDesc}
											</td>
											<!-- <td>
												
													Yet To Publish
												
											</td> -->
										</tr>
									</c:forEach>
									
								</tbody>
							</table>
						</div>
					</section>			
				</section>
			</form:form>
		<%-- </c:otherwise>
	</c:choose> --%>
</div>	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>