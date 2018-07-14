<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="de" class="fixed header-dark">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Receive Requisition</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
</style>
</head>
<body>
<!-- <header class="page-header">
	<h2>Receive Book</h2>
</header>
<div class="content-padding"> -->
	<div class="row">
		<c:choose>
			<c:when test="${bookPurchaseOrderDetailsList==null}">
				<div class="alert alert-danger">
					<strong>Sorry! No such data found.</strong>
				</div>	
			</c:when>
			<c:otherwise>
				<form action="updatePurchaseOrderDetails.html" method="post">
					<div class="col-md-4 col-md-offset-4">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>
								<h2 class="panel-title">
									Purchase Order Code :: ${bookPurchaseOrderDetailsList[0].bookPurchaseOrderCode}
									<input type="hidden" name="bookPurchaseOrderCode" id="bookPurchaseOrderCode" value="${bookPurchaseOrderDetailsList[0].bookPurchaseOrderCode}" />
								</h2>
							</header>
						</section>
					</div>
					<div class="col-md-12">	
					    <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
								<h2 class="panel-title">Receive Purchase Order</h2>
							</header>
							<div class="panel-body">
								<div class="form-group">
						            <table class="table table-bordered table-striped mb-none">
										<thead>
											<tr>
												<th>Order Details Code</th>
												<th>Book Name</th>
												<th>Author Name(s)</th>
												<th>Qty Ordered</th>
												<th>Qty Deficit</th>
												<th>Qty Received</th>
												<th>Rate</th>
												<th>Tax</th>
												<th>Discount</th>
												<th>Amount</th>
												<th>Paid Amount</th>
												<th>Receive Status</th>
												<th>Receiving Qty</th>
												<th>Total Amount</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="bpodl" items="${bookPurchaseOrderDetailsList}" varStatus="p">
											<tr>
												<td>
													${bpodl.purchaseOrderDetailsCode}
													<input type="hidden" name="purchaseOrderDetailsCode" id="purchaseOrderDetailsCode${p.index}" value="${bpodl.purchaseOrderDetailsCode}" />
												</td>
												<td>
													${bpodl.bookName}
													<input type="hidden" name="bookName" id="bookName${p.index}" value="${bpodl.bookName}" />
													<input type="hidden" name="genre" id="genre${p.index}" value="${bpodl.itemCode}" />
												</td>
												<td>
													${fn:replace(bpodl.authorName,'~', ',')}
													<input type="hidden" name="authorName" id="authorName${p.index}" value="${bpodl.authorName}" />
												</td>
												<td>
													${bpodl.qtyOrdered}
													<input type="hidden" name="qtyOrdered" id="qtyOrdered${p.index}" value="${bpodl.qtyOrdered}" />
												</td>
												<td>
													${bpodl.qtyDeficit}
													<input type="hidden" name="qtyDeficit" id="qtyDeficit${p.index}" value="${bpodl.qtyDeficit}" />
												</td>
												<td>
													${bpodl.qtyReceived}
													<input type="hidden" name="qtyReceived" id="qtyReceived${p.index}" value="${bpodl.qtyReceived}" />
												</td>				
												<td>
													${bpodl.rate}
													<input type="hidden" name="rate" id="rate${p.index}" value="${bpodl.rate}" />
												</td>
												<td>
													${bpodl.tax}
													<input type="hidden" name="tax" id="tax${p.index}" value="${bpodl.tax}" />
												</td>
												<td>
													${bpodl.discount}
													<input type="hidden" name="discount" id="discount${p.index}" value="${bpodl.discount}" />
												</td>
												<td>
													${bpodl.amount}
													<input type="hidden" name="amount" id="amount${p.index}" value="${bpodl.amount}" />
												</td>
												<td>
													${bpodl.paidAmount}
													<input type="hidden" name="paidAmount" id="paidAmount${p.index}" value="${bpodl.paidAmount}" />
												</td>
												<td>
													${bpodl.status}
													<input type="hidden" name="status" id="status${p.index}"  value="${bpodl.status}" />
												</td>
												<td>
												<!-- Modified by ranita.sur on 23082017 for tabindex adding -->
													<input type="text" pattern="^[1-9]\d*$" tabindex="1" class="form-control" name="qtyReceiving" id="qtyReceiving${p.index}" value="" onblur="checkWithDeficit(${p.index});"/>
												</td>
												<td>
													<input type="text" class="form-control" name="totalAmount" id="totalAmount${p.index}" value="0.0" readonly="readonly"/>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
						  	</div>
							<div class="col-md-6 col-md-offset-3"> 
								<div class="form-group">
									<label class="col-md-6 control-label">Paid Amount</label>
									<div class="col-md-6">
										<input type="text" class="col-md-6 form-control" name="totalPaidAmount" id="totalPaidAmount" value="${totalPaidAmount}" readonly="readonly">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label">Paying Amount</label>
									<div class="col-md-6">
										<input type="text" class="col-md-6 form-control" name="payingAmount" id="payingAmount" value="0.00" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}" readonly="readonly">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label">Ledger</label>
									<div class="col-md-6">
										<%-- <select class="col-md-6 form-control" id="ledger" name = "ledger">
											<option value="">Select...</option>
											<c:forEach var="ledger" items="${ledgerList}">
												<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
											</c:forEach>
										</select>  --%>
										<!-- modified by sourav.bhadra on 31-07-2017
											a new text field added in place of select option to display vendor's ledger -->
										<input type="text" class="form-control" name="ledger" id="ledger" value="${vendorLedger}" readonly/>
									</div>
								</div>
							</div>
						</div>
						<div class="alert alert-danger" id="warningDiv" style="display:none">
							<span id="warningmsg"></span>
						</div>
						<footer style="display: block;" class="panel-footer">
							<!-- Modified by ranita.sur on 23082017 for tabindex adding -->
							<input type="submit" tabindex="2" name="details" value="Receive" class="btn btn-primary" onclick="return validateReceiveRequisition();">
						</footer>
						</section>
					</div>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
<!-- </div> -->
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/library/receiveRequisition.js"></script>
</body>
</html>