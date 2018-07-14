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
<title>Commodity Purchase Order</title>
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
				<h2>Commodity Purchase Order</h2>
			</header>
			<div class="content-padding">
					<div class="row">
						<c:choose>
						<c:when test="${commodityPurchaseOrderCode eq null}">
							
							
							<c:if test="${commodityPurchaseOrderCode eq null}">
								<div class="alert alert-danger">
									<strong>Purchase Order Code Generation Error. Try Again.</strong>
								</div>
							</c:if>
						</c:when>
						<c:otherwise>
						<form action="createCommodityPurchaseOrder.html" method="post">
							<div class="col-md-6 col-md-offset-3">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Create Commodity Order</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="control-label">Purchase code</label>											
											<input type="text" class= "form-control" readonly name="purchaseOrderCode" id="purchaseOrderCode" value="${commodityPurchaseOrderCode}"/>
										</div>
										<div class="form-group">
											<label class="control-label">Task No</label>											
											<input type="text" class= "form-control"  name="purchaseId" id="purchaseId" value="" />
										</div>
										<div class="form-group">
											<label class="control-label">Requisition Id</label>
											<select class="form-control" name="purchaseOrderDesc" id="purchaseOrderDesc" onchange="getRequisitionDetails()" required>
												<option>Select...</option>
												<c:forEach var="requisition" items="${commodityRequisitionList}">
													<option value="${requisition.purchaseOrderCode}">${requisition.purchaseOrderCode}</option>
												</c:forEach>
											</select>
										</div>
										
										<div class="form-group">
											<label class="control-label">Department</label>											
											<input type="text" class= "form-control"  name="departmentCode" id="departmentCode" value="" readonly/>
										</div>
										
										<div class="form-group">
											<label class="control-label">Vendor</label>											
											<input type="text" class= "form-control"  name="vendor" id="vendor" value="" disabled/>
											<input type="hidden" class= "form-control"  name="vendorCode" id="vendorCode" value="" />
										</div>
										
										
 									</div>									
								</section>
							</div>
							<div class="col-md-12" id="commodityDiv" style="display: none;">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Commodity</h2>
	                                </header>
                               		<div class="panel-body">
	                                    <table class="table table-bordered table-striped mb-none"  style="display: block;">
	                                        <thead>
	                                            <tr>
	                                                <th>Commodity</th>
													<th>Rate</th>
													<th>Unit</th>	<!-- added by sourav.bhadra on 28-07-2017 to display commodity unit -->
													<th>Quantity</th>
													<th>Total</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody id="commodityTable">			                                        
												<tr class="gradeX">
													<td>
														Total Qty ::<input type="text" class="form-control" name="totalQtyOrdered" id="totalQtyOrdered" readonly value="0.00" />
													</td>
													<td>
														Total Amount ::<input type="text" class="form-control" name="totalCommodityAmount" id="totalCommodityAmount" readonly value="0.00" />
													</td>
													
													
												</tr>
	                                        </tbody>
	                                    </table>
									</div>
									<!-- <footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submit" name="submit" >Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer> -->
								</section>
							</div>
							
							<div class="col-md-12" id="expenseDiv" style="display: none;">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Expense</h2>
	                                </header>
                               		<div class="panel-body">
	                                    <table class="table table-bordered table-striped mb-none"  style="display: block;">
	                                        <thead>
	                                            <tr>
	                                                <th>Commodity</th>
													<th>Description</th>	
													<th>Expense type</th>
													<th>Amount</th>
												</tr>
	                                        </thead>
	                                        <tbody id="expenseTable">			                                        
												<tr class="gradeX">
													<td>
														Total Amount ::<input type="text" class="form-control" name="totalCommodityExpenceAmount" id="totalCommodityExpenceAmount" disabled value="0.00"/>
													</td>
													<td>
														Tax ::
														<select class="form-control" id="taxStatus" name="taxName" onchange="getTaxPercentages();">
					                                  		<option value="">Select</option>
					                                  		<c:forEach var="tax" items="${taxList}">
					                                  			<c:if test="${tax.taxStatus eq 'ACTIVE' }">
					                                  				<option value="${tax.taxCode}">${tax.taxName}</option>
					                                  			</c:if>
					                                  		</c:forEach>
					                                  	</select>
													</td>
													<td>
														Percentage ::<input type="text" class="form-control" name="percentage" id="percentage" disabled value="0.00"/>
													</td>
												</tr>
	                                        </tbody>
	                                    </table>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submit" name="submit" >Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
							</div>
							
							
						</form>
						</c:otherwise>
						</c:choose>
					</div>
	</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/inventory/commodityPurchaseOrder.js"></script>
</body>
</html>