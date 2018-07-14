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
<title>Assigned Template List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
			<header class="page-header">
				<h2>Assigned Fees Templates</h2>
			</header>
			<div class="content-padding">
				<div class= "col-md-12">
					<section role="main" class="content-body">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Assigned Templates</h2>
							</header>
							<div class="panel-body">
							<c:choose>
								<c:when test="${studentFeesTemplateWithAmountList eq null}">
									<div class="alert alert-danger">
										<strong>No fees assigned template found!</strong>
									</div>
								</c:when>
							<c:otherwise>
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Template Name</th>
											<th>Standard Mapped</th>
											<th>Edit</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="tempAmount" items="${studentFeesTemplateWithAmountList}">
											<tr class="gradeC">
												<td>${tempAmount.studentFeesTemplateName}</td>
												<td>${tempAmount.course.courseName}</td>
												<td><a href="viewStudentFeesTemplateAmountDetails.html?courseName=${tempAmount.course.courseName}&templateName=${tempAmount.studentFeesTemplateName}&templateCode=${tempAmount.studentFeesTemplateCode}"><input type="button" class="btn btn-primary" value="Edit"></a></td>
												<%-- <td><a class="on-default remove-row" href="deleteStudentFeesTemplateAmountDetails.html?courseName=${tempAmount.course.courseName}" ><i class="fa fa-trash-o"></i></a></td> --%>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:otherwise>
							</c:choose>
							</div>
						</section>
					</section>
				</div>
			</div>	
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>