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
<title>Student List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Student List</h2>
</header>
<div class="content-padding">
	<div class="row">
		<c:choose>
		<c:when test="${studentList eq null || empty studentList}">
			<div class="btnsarea01" >
				<!-- <div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">Student List Not Found</span>	
				</div> -->
				<div class="alert alert-danger">
					<strong>Student List Not Found</strong>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<form:form id="studentListForm" name="studentListForm" action="getStudentDetailsToEdit.html" method="POST">
				<section role="main" class="content-body">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
							</div>
							<h2 class="panel-title">Student List</h2>
						</header>
						<div class="panel-body" id="candidateTable">
							<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
								<thead>
									<tr>
										<th>Details</th>
										<th>User Id</th>
										<th>School Number</th>
										<th>Name</th>
										<th>Standard</th>
										<th>Section</th>
										<th>House</th>
										<th>Resident Type</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="student" items="${studentList}">
										<tr class="gradeC" onClick="window.open('getStudentDetailsToEdit.html?rollNumber=${student.userId}  ','_self')" style="cursor:pointer;">
											<td>
												<input type="radio" name="rollNumber" value="${student.roll}"/>
											</td>
											 <td>
												${student.userId}
											</td>
											<td>
												${student.roll}
											</td>
											<td>
												${student.studentName}
											</td>
											<td>
												${student.standard}
												<input type="hidden" name="courseId" id = "courseId" value="${student.courseId}"/>
												<input type="hidden" name="courseName" id = "courseName" value="${student.courseName}"/>
											</td>
											<td>
												${student.section}
											</td>
											<td>
												${student.house}
											</td>
											<td>
												${student.residentType}
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</section>			
				</section>
			</form:form>	
		</c:otherwise>
	</c:choose>
	</div>
</div>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>