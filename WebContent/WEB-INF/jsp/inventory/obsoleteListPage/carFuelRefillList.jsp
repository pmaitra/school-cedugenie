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
<title>Car Fuel Refill List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<form:form method="POST" action="getCarFuelRefillList.html" target="_blank" >
			
			
			
			<section role="main" class="content-body">
					
			
			
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Car Fuel Refill List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									
										<tr>
											<th>From Date</th>
											<td>
												<input type="text" name="fromDate" id="fromDate" class="date" >
											</td>
										</tr>
										<tr>
											<th>To Date</th>
											<td>
												<input type="text" name="toDate" id="toDate" class="date" >
											</td>
										</tr>
										<tr>
											<th>Car ::</th>
											<td>
												<select name="carDetails.carDetailsCode" id="carNameId" class="defaultselect" >
													<option value="">Select...</option>
													<c:forEach var="car" items="${carList}">
														<option value="${car.carDetailsCode}">${car.carDetailsName}</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<th colspan="2">
												<input type="button" value="GET" id="get" class="submitbtn"/>
											</th>
										</tr>
									
								</table>
							</div>
						</section>			
					</section>
			
		</form:form>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>