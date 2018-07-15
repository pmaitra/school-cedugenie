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
<title>Venue Assigned To Resource</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
			<c:choose>
				<c:when test="${venueList eq null}">
					<div class="alert alert-danger">
						<strong>No venue assigned to any resource yet!</strong>
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
											<th>Block</th>
											<th>Room NO</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="venue" items="${venueList}">
											<tr class="gradeC">
												<td>
													${venue.resource.userId}
												</td>
												<td>${venue.resource.resourceName}</td>
												<td>${venue.building}
												<input type = hidden value = "${venue.venueCode}"/>
												</td>
												<td>
													${venue.venueName}
													<input type = hidden value = "${venue.venueCode}"/>
												</td>
												<td>
													<a href="deAllocateVenueFromResource.html?userId=${venue.resource.userId}&venueName=${venue.venueCode}" target="frame">
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
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>