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
<title>Perishable Material Requisition Individual Details</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

				<%-- <form:form method="GET" id="listOrderForm" name="listOrderForm" action="commodityPurchaseOrderList.html">
					<strong>Status ::</strong>
							<select name="status" id="status" onchange="this.form.submit();">
							<c:if test="${status eq 'OPEN'}">
								<option value="OPEN" selected="selected">OPEN</option>
								<option value="CLOSED">CLOSED</option>
							</c:if>
							<c:if test="${status eq 'CLOSED'}">
								<option value="OPEN">OPEN</option>
								<option value="CLOSED" selected="selected">CLOSED</option>
							</c:if>
						</select>
				</form:form> --%>
				<c:choose>
					<c:when test="${perishableMaterialsList eq null || empty perishableMaterialsList}">
						<%-- <span>No ${status} Commodity PO List Found</span> --%>
						<div class = "alert alert-danger">
							<strong>Perishable Material Requisition List Not Found</strong>
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
								<h2 class="panel-title">Perishable Material Requisition Individual Details</h2>
							</header>
							<div class="col-md-6 col-md-offset-3"> 
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">
											Order Details
										</h2>
									</header>
									<div class="panel-body">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label">Order ID</label>
													<input type="text" class="form-control" id="orderId" name="orderId" readonly="readonly" value="${orderId}"/>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label class="control-label">Order Number</label>
													<input type="text" class="form-control" id="" name="" readonly="readonly" value="${orderNumber}"/>
												</div>
											</div>
										</div>
									</div>
								</section>
							</div>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="" >
									<thead>
										<tr>
											<th>Commodity</th>
											<th>Unit</th>
											<th>Quantity</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="pml" items="${perishableMaterialsList}">
												<tr class="gradeC">
													<td>
														${pml.commodityName}
													</td>
													<td>
														${pml.commodityUnit}
													</td>
													<td>
														${pml.commodityQuantity}
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
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>