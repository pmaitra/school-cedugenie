<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Book Request List</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Book Request List</h2>
</header>
<div class="content-padding">
	<c:choose>
	<c:when test="${bookRequestIdList == null}" >
		<div class="alert alert-danger" style="visibility: visible;">
			<span id="errormsg">No book request Found</span>
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
					<h2 class="panel-title">Requests</h2>
				</header>
				<div class="panel-body">
					<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
						<thead>
							<tr>
								<th>Request ID</th>	
								<th>Requested By(User ID)</th>	
								<th>Requested Date</th>
								<th>Request Expire Date</th>
								<th>Status</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="bookRequestResult" items="${bookRequestIdList}">
								<tr class="gradeC">
									<td>${bookRequestResult.bookRequestCode}</td>
									<td>${bookRequestResult.bookRequestFor.userId}</td>
									<td>${bookRequestResult.bookRequestOpenDate}</td>
									<td>${bookRequestResult.bookRequestCloseDate}</td>
									<td>${bookRequestResult.bookRequestStatus}</td>
									<td>
										<c:choose>
											<c:when test="${bookRequestResult.bookRequestStatus=='CLOSE'}">
												Allocated
											</c:when>
											<c:otherwise>
												<a href="issuingBookForBookAllocation.html?requestId=${bookRequestResult.bookRequestCode}">Allocate</a>
											</c:otherwise>
										</c:choose>
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
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script> 
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>