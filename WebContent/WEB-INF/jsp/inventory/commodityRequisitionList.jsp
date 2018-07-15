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
<title>List Books</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">		<!-- Added by Saif 29-03-2018 -->
				<h2>Commodity Requisition List</h2>
	</header>
		<div class = "content-padding">
			
				
					<section role="main" class="content-body">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
								<h2 class="panel-title">Commodity Requisition List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="${hostel.hostelName}" >
									<thead>
										<tr>
											<th>Order Code</th>
											<th>Total Qty Ordered</th>
											<th>Net Amoount</th>
											<th>Vendor</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="po" items="${commodityRequisitionList}">
												<tr class="gradeC">
													<td>
														${po.purchaseOrderCode}
													</td>
													<td>
														${po.totalQtyOrdered}
													</td>
													<td>
														${po.netTotal}
													</td>
													<td>
														${po.vendorName}
													</td>
													<td>
														<a href = "comodityRequisitionDetails.html?requisitionCode=${po.purchaseOrderCode}">Details</a>
													</td>
												</tr>
										</c:forEach>									
									</tbody>
								</table>
							</div>
						</section>			
					</section>
				
		</div>

				
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>