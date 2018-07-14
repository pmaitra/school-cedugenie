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
<title>Published Drives</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
		<header class="page-header">
			<h2>Published Drives</h2>
		</header>
		<div class="content-padding">
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
										<th>Drive Name</th>
										<th>Status</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="course" items="${courseList}">
										<tr>
											<td>
												${course.courseName}
											</td>
											<td>
												${course.serialId}
											</td>
											<td>
												${course.courseDesc}
											</td>
											<td>
												${course.desc}
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</section>			
				</section>
			</form:form>
		</div>	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>