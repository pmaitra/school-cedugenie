<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/purchaseOrderListPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Purchase Order List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Receive Book</h2>
</header>
<div class="content-padding">
	<div class="row">
		<c:choose>
			<c:when test="${bookPurchaseOrderList==null}">
				<div class="alert alert-danger">
					<strong>No Book Purchase Order Found</strong>
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
					
							<h2 class="panel-title">Purchase Order List<br>Book Requisition Code :: ${bookPurchaseOrderList[0].bookRequisition}</h2>
						</header>
						<div class="panel-body">
							<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
								<thead>
									<tr>
										<th>Order Code</th>
										<th>Open Date</th>
										<th>Close Date</th>
										<th>Qty. Ordered</th>
										<th>Qty. Deficit</th>
										<th>Qty. Received</th>
										<th>Total Amount</th>
										<th>Paid Amount</th>
										<th>Receive Status</th>
										<th>Payment Status</th>
										<th>Vendor</th>
										<th>Receive</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="bookPurchaseOrderList" items="${bookPurchaseOrderList}">
									<tr class="gradeC">
										<td>
											${bookPurchaseOrderList.purchaseOrderCode}
										</td>
										<td>
											${bookPurchaseOrderList.purchaseOrderOpenDate}
										</td>
										<td>
											${bookPurchaseOrderList.purchaseOrderCloseDate}
										</td>
										<td>
											${bookPurchaseOrderList.totalQtyOrdered}
										</td>
										<td>
											${bookPurchaseOrderList.totalQtyDeficit}
										</td>
										<td>
											${bookPurchaseOrderList.totalQtyReceived}
										</td>
										<td>
											${bookPurchaseOrderList.totalAmount}
										</td>
										<td>
											${bookPurchaseOrderList.advanceAmount}
										</td>
										<td>
											${bookPurchaseOrderList.status}
										</td>
										<td>
											${bookPurchaseOrderList.amountStatus}
										</td>
										<td>
											${bookPurchaseOrderList.vendorCode}
										</td>
										<td>
											<c:choose>
												<c:when test="${bookPurchaseOrderList.amountStatus eq 'CLOSED' && bookPurchaseOrderList.amountStatus eq 'CLOSED'}">
													NA
												</c:when>
												<c:otherwise>
													<!-- modified by sourav.bhadra on 31-07-2017
													a new parameter 'vendorCode' added to get vendor's ledger -->
													<a href="updateReceiveForBook.html?purchaseOrderCode=${bookPurchaseOrderList.purchaseOrderCode}&vendorCode=${bookPurchaseOrderList.ledger}&totalPaidAmount=${bookPurchaseOrderList.advanceAmount}">Receive</a>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</section>
				</section>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/library/listBookPurchaseOrder.js"></script>
</body>
</html>