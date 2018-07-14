<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Mess Commodity Purchase Order Receive</title>
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
				<header class="page-header">
					<h2>Receive Mess Commodity Order</h2>
				</header>
				<div class="content-padding">
					<div class="row">
						<c:choose>
							<c:when test="${message1 != null}">
								<div class="btnsarea01">
									<div class="alert alert-danger">
										<strong>${message1}</strong>
									</div>
								</div>
							</c:when>
							<c:otherwise>
							<form:form method="POST" id="makeCommodityReceive" name="makeCommodityReceive" action="makeMessCommodityReceive.html" >
							<input type="hidden" name="amountStatus" value="${commodityPurchaseOrder.amountStatus}" >
							<input type="hidden" name="totalQtyDeficit" value="${commodityPurchaseOrder.totalQtyDeficit}" >
							<input type="hidden" name="totalQtyReceived" value="${commodityPurchaseOrder.totalQtyReceived}" >
							<div class="col-md-4 col-md-offset-4">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Receive Commodity Order</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="control-label">Order ID</label>
											<input type="text" class="form-control" readonly name="purchaseOrderCode" value="${commodityPurchaseOrder.purchaseOrderCode}"/>
										</div>
										<div class="form-group">
											<label class="control-label">Vendor Name</label>
											<input type= "hidden" class="form-control" id="vendorCode" name="vendorCode" value ="${commodityPurchaseOrder.vendorCode}"/>
											<input type= "text" class="form-control" readonly value ="${commodityPurchaseOrder.vendorName}"/>
										</div>
										<div class="form-group">
											<label class="control-label">Open Date</label>
											<input type= "text" class="form-control" readonly value ="${commodityPurchaseOrder.purchaseOrderOpenDate}"/>
										</div>
										<div class="form-group">
											<label class="control-label">Department</label>
											<input type = "text" id = "departmentCode" name ="departmentCode" class="form-control" readonly value = "${commodityPurchaseOrder.departmentName}"/>
										</div>
										<div class="form-group">
											<label class="control-label">Total Amount</label>
											<input type="text" class="form-control" id="netTotal" name="netTotal"   value = "${commodityPurchaseOrder.netTotal}" readonly>
										</div>
										<div class="form-group">
											<label class="control-label">Advanced Amount</label>
											<input type="text" class="form-control" id="advanceAmount" name="advanceAmount" value = "${commodityPurchaseOrder.advanceAmount}" readonly>
										</div>
										<div class="form-group">
											<label class="control-label">Pending Amount</label>
											<input type="text" class="form-control"  id="pendingAmount" name="pendingAmount" value = "${commodityPurchaseOrder.pendingAmount}" readonly>
										</div>
 									</div>
 								</section>
							</div>
							
							<div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Ordered Commodity</h2>
	                                </header>
									<div class="panel-body">
										<table class="table table-bordered table-striped mb-none">
											<thead>
												<tr>
													<th>Commodity Name</th>
													<th>Rate</th>
													<th>Discount(%)</th>
													<th>Tax Details</th>
													<th>Total Order</th>
													<th>Received</th>
													<th>Remaining</th>
													<th>Damage Record</th>
													<th>Receiving</th>
													<th>Defects</th>
												</tr>
											</thead>
											<tbody>			                                        
												<c:forEach var="pod" items="${commodityPurchaseOrder.commodityPurchaseOrderDetailsList}" varStatus="status">
												<tr class="gradeX">
													<td>
														${pod.commodity}
														<input type="hidden" name="commodity" value="${pod.commodity}"/>
													</td>
													<td>
														${pod.rate}
														<input type="hidden" id = "rate${status.index}" value="${pod.rate}"/>
													</td>
													<td>
														${pod.discount}
														<input type="hidden" id = "discount${status.index}" value="${pod.discount}"/>
													</td>
													<td>
														${commodityPurchaseOrder.taxName}(${commodityPurchaseOrder.taxPercentage})
														<input type="hidden" id = "taxPercentage${status.index}" value="${commodityPurchaseOrder.taxPercentage}"/>
													</td>
													<td>
														${pod.qtyOrdered}
														<input type="hidden" value="${pod.qtyOrdered}"/>
													</td>
													<td>
														${pod.qtyReceived}
														<input type="hidden" name="${pod.commodity}received" value="${pod.qtyReceived}"/>
													</td>
													<td>
														${pod.qtyDeficit}
														<input type="hidden" id="C${status.index}" name="${pod.commodity}remeaning" value="${pod.qtyDeficit}" />
													</td>
													<td>
														${pod.damage}
														<input type="hidden" name="${pod.commodity}oldDamage" value="${pod.damage}"/>
													</td>
													<td>
														<input class="form-control" type="text" id="A${status.index}" name="${pod.commodity}receiving" value="0.0" onfocus="this.value=''" onblur="calculateAmount(${status.index});"  pattern="^[0-9.]+$" required />
													</td>
													<td>
														<input class="form-control" type="text" id="B${status.index}" name="${pod.commodity}damage" value="0.0" onfocus="this.value=''" pattern = "^[0-9.]+$" onblur="calculateAmount(${status.index});"/>
													</td>
												</tr>
												</c:forEach>
												<tr class="gradeX">
													<td>
														Total Amount ::
														<input class="form-control" type="text" id="total" name="total" value="0.0" readonly="readonly">
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</section>
							</div>
							
							<%-- <div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Expences</h2>
	                                </header>
									<div class="panel-body">
										<table class="table table-bordered table-striped mb-none">
											<thead>
												<tr>
													<th>Commodity Name</th>
													<th>Description</th>
													<th>Cost Type</th>
													<th>Net Amount</th>
													<th>Paying Amount</th>
												</tr>
											</thead>
											<tbody>			                                        
												<c:forEach var="pod" items="${commodityPurchaseOrder.commodityPurchaseOrderDetailsList}" varStatus="status">
													<tr class="gradeX">
														<td>
															${pod.commodity}
															<input type="hidden" name="commodity" value="${pod.commodity}"/>
														</td>
														<td>
															${pod.expenceDesc}
															<input type="hidden" id = "expenceDesc${status.index}" value="${pod.expenceDesc}"/>
														</td>
														<td>
															${pod.paymentType}
															<input type="hidden" id = "paymentType${status.index}" value="${pod.paymentType}"/>
														</td>
														<td>
															${pod.expenceAmount}
															<input type="hidden" id = "expenceAmount${status.index}" value="${pod.expenceAmount}"/>
														</td>
														<td>
															<input class="form-control" type="text" id="expenceAmt${status.index}" name="${pod.commodity}expenceAmt" value="0.0" pattern = "^[0-9.]+$" onfocus="this.val=''" onblur="calculatePayingAmount('${status.index}');" required />
														</td>
													</tr>
												</c:forEach>
												<tr class="gradeX">
													<td>
														Total Amount ::
														<input class="form-control" type="text" id="grandTotal" name="grandTotal" value="0.0" readonly="readonly">
													</td>
												</tr>
											</tbody>
										</table>
									</div>
	                            </section>
							</div> --%>
							
							<div class  = "row">
								<section class="panel">
									<div style="display: block;" class="panel-body">
										<div class="col-md-6 col-md-offset-3"> 
											<!-- <div class="form-group">
												<label class="col-md-6 control-label">Type</label>
												<select class="col-md-6 form-control" name="type" id="type">
													<option value="">Select...</option>
													<option value="ASSET">ASSET</option>
													<option value="EXPENSE">EXPENSE</option>
												</select>
											
											</div> -->
											<div class="form-group">
												<label class="col-md-6 control-label">Paying Amount</label>
												<div class="col-md-6">
													<input type="text" class="col-md-6 form-control" name="payAmount" id="payAmount" value="0.0"  readonly="readonly">
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-6 control-label">Ledger</label>
												<div class="col-md-6">
													<select class="col-md-6 form-control" id="ledger" name = "ledger">
														<option value="">Select...</option>
														<c:forEach var="ledger" items="${ledgerList}">
															<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
														</c:forEach>
													</select> 
												</div>
											</div>
										</div>
									</div>
						    			
						    			 <footer style="display: block;" class="panel-footer">
											<button class="mb-xs mt-xs mr-xs btn btn-primary" type="submit" id="submit" name="submit" onclick = "return validateReceive()">Submit</button>
											<button class="mb-xs mt-xs mr-xs btn btn-warning" type ="button" onclick="window.location='commodityPurchaseOrderList.html?status=OPEN'">Back</button>
										</footer>
						    		</section>
						    	</div>
							</form:form>
							</c:otherwise>
						</c:choose>
					</div>
				</div>

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/inventory/commodityPurchaseOrderReceive.js"></script>
<script type="text/javascript">
function validateReceive(){
	var receivingBooks = $("#A").val();
	var regnum = '^[0-9]+$';
	if(receivingBooks == ""){
		alert("Please enter the no of books you want to receive.");
		return false;
	}
	if(receivingBooks != ""){
		if(!receivingBooks.match(regnum)) {
			alert("The input is not valid. Please enter numeric value.");
			return false;
		}
	}else{
		return true;
	}
};
</script>
</body>
</html>