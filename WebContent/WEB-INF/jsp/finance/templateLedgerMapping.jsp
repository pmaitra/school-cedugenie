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
<title>Ledger List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">
		<h2>Salary Template And Ledger Mapping List</h2>
	</header>
	<div class="content-padding">
		<c:choose>
			<c:when test="${templateList == null}">
				<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
					<span id="infomsg">Template List Not Found</span>
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
						<h2 class="panel-title">Template Ledger Mapping List</h2>
					</header>
					<div class="panel-body">
						<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
							<thead>
								<tr>
									<th>Salary Template Name</th>
									<th>Salary Template Desc</th>
									<!-- <th>Ledger Mapping Status</th> -->
									<th>View / Map</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="template" items="${templateList}" varStatus="i">
									<tr>
										<td>
											${template.salaryTemplateName}
										</td>
										<td>
											${template.salaryTemplateDesc}
										</td>
									<%-- 	<td>
											${template.ledgerMappingStatus}
										</td> --%>
										<td>
											<c:choose>
												<c:when test="${template.ledgerMappingStatus eq 'REMEANING'}">
													<a href="mapTemplateLedger.html?templateCode=${template.salaryTemplateCode}&type=MAP">
														<input type="button" class="btn btn-warning" value="MAP">
													</a>
												</c:when>
												<c:otherwise>
													<a href="mapTemplateLedger.html?templateCode=${template.salaryTemplateCode}&type=VIEW">
														<input type="button" class="btn btn-primary" value="VIEW">
													</a>
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
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>