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
<title>Exam Type</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Exam Type</h2>
</header>
<div class="content-padding">
	<div class="row">
		<c:choose>
		<c:when test="${examTypeListFromDB eq null || empty examTypeListFromDB}">
			<div class="alert alert-danger">
				<strong>Exam type not found</strong>
			</div>
		</c:when>
		<c:otherwise>
			<section role="main" class="content-body">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						</div>
						<h2 class="panel-title">Exam Types</h2>
					</header>
					<div class="panel-body">
						<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
							<thead>
								<tr>
									<th>Exam Type Name</th>
									<th>Term</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="examType" items="${examTypeListFromDB}">
									<tr class="gradeC">
										<td>
											${examType.examTypeName}
										</td>
										<td>
											${examType.examTypeDesc}
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
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>