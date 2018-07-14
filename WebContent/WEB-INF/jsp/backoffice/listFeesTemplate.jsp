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
<title>Fees Template List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<c:if test="${insertUpdateStatus ne null}">
		<div class="alert alert-success">
			<strong>${insertUpdateStatus}</strong>	
		</div>
	</c:if>
	
<c:choose>	
	<c:when test="${feesTemplateList eq null || empty feesTemplateList}">
		<div class="alert alert-danger" >
			<strong>Fees Template List Not Found</strong>
		</div>
	</c:when>
	<c:otherwise>

		<form:form id="getFeesTemplateDetails" name="getFeesTemplateDetails" action="getFeesTemplateDetails.html" method="POST">
			<section role="main" class="content-body">
				<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Fees Template List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Select</th>
											<th>Template Name</th>
											<th>Standard</th>
											<th>Applied</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="template" items="${feesTemplateList}">
											<tr class="gradeC">
												<td>
													<input type="radio" name="templateCode" value="${template.templateCode}" />
												</td>
												<td>
													${template.templateName}
												</td>
												<td>
													${template.standard}
												</td>
												<td>
													<c:if test="${template.applied eq true}">
														<!-- <img src="/cedugenie/images/yes_png.png" alt="YES" /> -->
														<i class="fa fa-check" aria-hidden="true"></i>
													</c:if>
													<c:if test="${template.applied eq false}">
														<!-- <img src="/cedugenie/images/no_png.png" alt="NO" /> -->
														<i class="fa fa-close" aria-hidden="true"></i>
													</c:if>
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
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
$(document).ready(function() { 
   $('input[name=templateCode]').change(function(){
        $('form').submit();
   });
});
</script>
</body>
</html>