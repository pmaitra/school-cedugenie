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
			<h2>Facility List</h2>
	</header>
		<div class = "content-padding">
			<c:choose>
				<c:when test="${facilityList eq null}">
					<div class="alert alert-danger">
						<strong>No facility has created yet.</strong>
					</div>
				</c:when>
			<c:otherwise>
				<form:form name="facilityListForm" id="facilityListForm" action="editFacility.html" method="post">
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
											<th>Select</th>
											<th>Facility Name</th>
											<th>Facility Description</th>
											<th>Pay Status</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="fac" items="${facilityList}">
											<tr class="gradeC">
												<td><input type="radio" name="facilityCode" id="facilityCode" value="${fac.facilityCode}"/></td>
												<td>${fac.facilityName}</td>
												<td>${fac.facilityDesc}</td>
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


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
   $('input[name=facilityCode]').change(function(){
        $('form').submit();
   });
});
</script>
</body>
</html>