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
<title>Template List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<%-- <c:choose>
	<c:when test="${salaryTemplateList == null}">
		<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
			<span id="infomsg">No Template Found</span>
		</div>
	</c:when>
<c:otherwise> --%>
<header class="page-header">
		<h2>Salary Template List</h2>
</header>
	<div class="content-padding">
		<div class="row">
			<form:form method="POST" id="salaryTemplate" name="salaryTemplate" action="submitSalaryTemplate.html">		
				<section role="main" class="content-body">		
							<section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
										<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
									</div>						
									<h2 class="panel-title">Template List</h2>
								</header>
								<div class="panel-body">
									<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
										<thead>
											<tr>
												<th>TEMPLATE NAME</th>
												<th>TEMPLATE DESC</th>
												<th>DESIGNATION</th>
												<th>LEVEL</th>
												<th>View/Edit</th>													
											</tr>
										</thead>
										<tbody>										
											<c:forEach var="template" items="${salaryTemplateList}">
												<tr class="gradeC">												 
													<td>${template.salaryTemplateName}</td>
													<td>${template.salaryTemplateDesc}</td>
													<td>${template.designation}</td>
													<td>${template.designationLevel}</td>		
													<td><a href="viewEditTemplate.html?templateCode=${template.salaryTemplateCode}"><input type="button" name="details" class="btn btn-primary" value="View/Edit" class="submitbtn"></a></td>
												</tr>										
											</c:forEach>										
										</tbody>									
									</table>
								</div>
							</section>			
						</section>
				
			</form:form>
		</div>
	</div>
<%-- 	</c:otherwise>
</c:choose> --%>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>