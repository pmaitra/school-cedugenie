<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<title>Books Ready For Add To Catalogue</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Books</h2>
</header>
<div class="content-padding">
	<div class="row">
		<c:choose>
			<c:when test="${bookPODetails eq null}">		
				<div class = "alert alert-danger">
					<strong> No book is left to add in catalogue.</strong>
				</div>
			</c:when>
			<c:otherwise>
				<form action="saveToCatalogueAfterReceive.html" method="post">
					<section role="main" class="content-body">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
								<h2 class="panel-title">Books And PO Details</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Select</th>
											<th>Requisition Code</th>
											<th>Order Code</th>
											<th>Book Name</th>
											<th>Author</th>
											<th>Publisher</th>
											<th>Edition</th>
											<th>Qty Ordered</th>
											<th>Qty Received</th>
											<th>Qty Deficit</th>
											<th>Catalogue</th>
											<th>Total Price</th>
											<th>Unit Price</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="bookPurchaseOrderDetailsList" items="${bookPODetails}">
											<tr class="gradeC">
												<td>
													<input type="radio" id = "bcode" name="bcode" value="${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}"/>
												</td>
												<td>
													<input type="hidden" value="${bookPurchaseOrderDetailsList.updatedBy}" name="reqCode${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
													${bookPurchaseOrderDetailsList.updatedBy}
												</td>
												<td>
													<input type="hidden" value="${bookPurchaseOrderDetailsList.bookPurchaseOrderCode}" name="poCode${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
													<input type="hidden" value="${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" name="podCode${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
													${bookPurchaseOrderDetailsList.bookPurchaseOrderCode}
												</td>
												<td>
													<input type="hidden" value="${bookPurchaseOrderDetailsList.bookName}" name="bookName${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
													${bookPurchaseOrderDetailsList.bookName}
													<input type="hidden" value="${bookPurchaseOrderDetailsList.genre}" name="genre${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}"/>
												</td>
												<td>
													<c:forEach var="message" items="${fn:split(bookPurchaseOrderDetailsList.authorName, ',')}">
														<input type="hidden" value="${message}" name="author${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
										                <c:out value="${message}" /> ; 
										            </c:forEach>
												</td>
												<td>
													<input type="hidden" value="${bookPurchaseOrderDetailsList.publisherName}" name="publisher${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
													${bookPurchaseOrderDetailsList.publisherName}
												</td>
												<td>
													<input type="hidden" value="${bookPurchaseOrderDetailsList.edition}" name="edition${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
													${bookPurchaseOrderDetailsList.edition}
												</td>
												<td>
													<input type="hidden" value="${bookPurchaseOrderDetailsList.qtyOrdered}" name="qtyOrd${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
													${bookPurchaseOrderDetailsList.qtyOrdered}
												</td>
												<td>
													<input type="hidden" value="${bookPurchaseOrderDetailsList.qtyReceived}" name="qtyRcv${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
													${bookPurchaseOrderDetailsList.qtyReceived}
												</td>
												<td>
													<input type="hidden" value="${bookPurchaseOrderDetailsList.qtyDeficit}" name="qtyDef${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
													${bookPurchaseOrderDetailsList.qtyDeficit}
												</td>
												<td>
													<input type="hidden" value="${bookPurchaseOrderDetailsList.qtyDefect}" name="qtyCat${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
													${bookPurchaseOrderDetailsList.qtyDefect}
												</td>
												<td>
													<input type="hidden" value="${bookPurchaseOrderDetailsList.amount}" name="amount${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
													${bookPurchaseOrderDetailsList.amount}
												</td>
												<td>
													<input type="hidden" value="${bookPurchaseOrderDetailsList.rate}" name="rate${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
													${bookPurchaseOrderDetailsList.rate}
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<footer style="display: block;" class="panel-footer">
								<button type="submit" class="btn btn-primary" onclick="return valradio();">Add Book</button>
							</footer>
						</section>
					</section>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/library/purchaseOrderDetailsList.js"></script>
</body>
</html>