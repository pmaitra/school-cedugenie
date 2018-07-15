<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Hostels Assigned To Students</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class= "page-header">	<!-- Added by Saif 29-03-2018 -->
		<h2>Allocated List</h2>
	</header>
		<div class = "content-padding">
			<c:choose>
				<c:when test="${hostelList eq null}">
					<div class="alert alert-danger">
						<strong>No Hostel Assigned To Any Student Yet!</strong>
					</div>
				</c:when>
			<c:otherwise>
					<section role="main" class="content-body">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
								<h2 class="panel-title">Allocated List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>User Id</th>
											<th>Name</th>
											<th>Standard</th>
											<th>Section</th>
											<th>Room Name</th>
											<th>Hostel Name</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="student" items="${hostelList}">
											<tr class="gradeC">
												<td>
													${student.resource.userId}
													<input type= "hidden" id= "studentUserId" name = "studentUserId" value= "${student.resource.userId}">
												</td>
												<td>
													${student.resource.firstName} ${student.resource.middleName} ${student.resource.lastName}
													<input type= "hidden" id= "studentName" name = "studentName" value= "${student.resource.firstName} ${student.resource.middleName} ${student.resource.lastName}">
												</td>
												<td>
													${student.resource.klass}
													<input type= "hidden" id= "studentStandard" name = "studentStandard" value= "${student.resource.klass}">
												</td>
												<td>
													${student.resource.sectionName}
													<input type= "hidden" id= "studentSection" name = "studentSection" value= "${student.resource.sectionName}">
												</td>
												<td>
													${student.room.roomName}
													<input type= "hidden" id= "studentRoomName" name = "studentRoomName" value= "${student.room.roomName}">
												</td>
												<td>
													${student.resource.category}
													<input type= "hidden" id= "studentHostelName" name = "studentHostelName" value= "${student.resource.category}">	<!-- Hidden fields are added by Saif 27-03-2018 -->
												</td>
												<td>
													<a href="deAllocateStudentFromHostel.html?userId=${student.resource.userId}&hostelName=${student.resource.category}" target="frame">
														<button type="submit" class="btn btn-danger">De-Allocate</button>
													</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</section>
					</section>
			</c:otherwise>
			</c:choose>
		</div>
			
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>