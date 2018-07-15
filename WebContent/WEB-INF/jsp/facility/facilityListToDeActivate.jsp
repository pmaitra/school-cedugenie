<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Facility List</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">	<!-- Added by Saif 28/03/2018 -->
			<h2>De - Activate Facility</h2>
	</header>
		<div class = "content-padding">
			<c:choose>
				<c:when test="${facilityList eq null}">
					<div class="alert alert-danger">
						<strong>No facility has created yet.</strong>
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
								<h2 class="panel-title">Facility List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" >
									<thead>
										<tr>
											<th>Facility Name</th>
											<th>Facility Description</th>
											<th>Pay Status</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="fac" items="${facilityList}">
											<tr class="gradeC">
												<td>
													${fac.facilityName}	<!-- Hidden fields are added by Saif 28-03-2018 -->
													<input type = "hidden" name = "oldFacilityName" id = "oldFacilityName" value= "${fac.facilityName}">
												</td>
												<td>
													${fac.facilityDesc}
													<input type = "hidden" name = "oldFacilityDesc" id = "oldFacilityDesc" value= "${fac.facilityDesc}">
													<input type = "hidden" name = "oldFacilityStatus" id = "oldFacilityStatus" value= "${fac.ispaid}">
												</td>
												<c:choose>
													<c:when test="${fac.ispaid eq true}">
														<td>
															Paid
														</td>
													</c:when>
													<c:otherwise>
														<td>
															Unpaid
														</td>
													</c:otherwise>
												</c:choose>
												<td>
													<a href="facilityDeactivation.html?facilityCode=${fac.facilityCode}&oldFacilityName=${fac.facilityName}&oldFacilityDesc=${fac.facilityDesc}&oldFacilityStatus=${fac.ispaid}" target="frame">
														<button type="submit" class="btn btn-danger">De-Activate</button>
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