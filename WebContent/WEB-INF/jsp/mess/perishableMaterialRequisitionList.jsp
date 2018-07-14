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
<title>Perishable Material Requisition List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">
		<h2>Perishable Material Requisition List</h2>
	</header>
	<div class="content-padding">
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
								<h2 class="panel-title">Perishable Material Requisition List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="" >
									<thead>
										<tr>
											<th>Order ID</th>
											<th>Order Number</th>
											<th>Opening Date</th>
											<th>Closing Date</th>
											<th>Current Academic Session</th>
											<th>Current Financial Session</th>
											<th>View</th>
											<th>Receive Status</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="pml" items="${perishableMaterialsList}">
												<tr class="gradeC">
													<td>
														${pml.orderId}
													</td>
													<td>
														${pml.orderNumber}
													</td>
													<td>
														${pml.openDate}
													</td>
													<td>
														${pml.closeDate}
													</td>
													<td>
														${pml.academicsession}
													</td>
													<td>
														${pml.financialSession}
													</td>
													<td>
														<a href="viewIndividualPerishableMaterialRequisitionDetails.html?orderID=${pml.orderId}&orderNumber=${pml.orderNumber}">Order Details</a>
													</td>
													<td>
														<c:choose>
															<c:when test="${pml.objectId eq 'RECEIVED' }">
																Received
															</c:when>
															<c:otherwise>
																<a href="createMessDailyRationPurchaseOrder.html?orderID=${pml.orderId}&orderNumber=${pml.orderNumber}">Receive</a>
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
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>