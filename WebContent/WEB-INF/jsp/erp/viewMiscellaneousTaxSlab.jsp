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
	<header class= "page-header">
		<h2>Miscelleneous Tax Slab List</h2>
	</header>
	
	<div class = "content-padding">
	<c:choose>
	<c:when test="${salBreakUpList == null}">
		<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
			<span id="infomsg">No Salary BreakUp Found</span>
		</div>
	</c:when>
<c:otherwise>
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
											<th>SALARY BREAKUP NAME</th>
											<th>VIEW / EDIT</th>																								
										</tr>
									</thead>
									<tbody>										
										<c:forEach var="sbUp" items="${salBreakUpList}">
											<tr class="gradeC">	
												<td>${sbUp.salaryBreakUpName}</td>
												<td><a href="getMiscellaneousSlabForEdit.html?miscTaxTypeCode=${sbUp.salaryBreakUpCode}&miscTaxTypeName=${sbUp.salaryBreakUpName}"><input type="button" class="btn btn-primary" value="VIEW / EDIT" class="submitbtn"></a></td>
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
</body>
</html>