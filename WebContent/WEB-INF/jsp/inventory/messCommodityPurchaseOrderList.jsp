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
<title>Mess Commodity Purchase Order List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

				<form:form method="GET" id="listOrderForm" name="listOrderForm" action="viewMessPurchaseOrderList.html">
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
				</form:form>
				<c:choose>
					<c:when test="${commodityPurchaseOrderList eq null || empty commodityPurchaseOrderList}">
						<%-- <span>No ${status} Commodity PO List Found</span> --%>
						<div class = "alert alert-danger">
							<strong>No ${status} Mess Commodity Purchase Order List Found</strong>
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
								<h2 class="panel-title">${status} Commodity PO List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="${hostel.hostelName}" >
									<thead>
										<tr>
											<th>Order Code</th>
											<th>Receiving Status</th>
											<th>Amount Status</th>
											<th>Order Status</th>
											<c:if test="${status eq 'OPEN'}">
												<th>Receive</th>
												<!-- <th>Pay</th> -->
												<th>Close Order</th>
											</c:if>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="po" items="${commodityPurchaseOrderList}">
												<tr class="gradeC">
													<td>
														${po.purchaseOrderCode}
													</td>
													<td>
														${po.receiveStatus}
													</td>
													<td>
														${po.amountStatus}
													</td>
													<td>
														${po.orderStatus}
													</td>
													<c:if test="${status eq 'OPEN'}">
														<td>
															<a href="receiveMessCommodityOrder.html?orderID=${po.purchaseOrderCode}">Receive / Defect</a>
														</td>
														<%-- <td>
															<a href="commodityPayment.html?orderID=${po.purchaseOrderCode}">Payment</a>
														</td> --%>
														<td>
															<a href="closeMessCommodityOrder.html?orderID=${po.purchaseOrderCode}">CLOSE</a>
														</td>
													</c:if>
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