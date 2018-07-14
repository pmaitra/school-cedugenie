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
<title>Mess Purchase Order</title>
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

					<div class="row">
						
						<c:choose>
						<c:when test="${vendorList eq null || empty vendorList || departmentList eq null || empty departmentList}">
							<c:if test="${vendorList eq null}">
								<div class="alert alert-danger">
									<strong>Create Commodity Vendor First.</strong>
								</div>
							</c:if>
							<c:if test="${departmentList eq null}">
								<div class="alert alert-danger">
									<strong>Department List Not Found.</strong>
								</div>
							</c:if>
							<%-- <c:if test="${commodityPurchaseOrderCode eq null}">
								<div class="alert alert-danger">
									<strong>Purchase Order Code Generation Error. Try Again.</strong>
								</div>
							</c:if> --%>
						</c:when>
						<c:otherwise>
						<form action="createMessPurchaseOrder.html" method="post">
							<div class="col-md-4 col-md-offset-4">
								<section class="panel">
									<header class="page-header">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
												<h2>Mess Purchase Order</h2>										
									</header>
									
									<div style="display: block;" class="panel-body">
										 <div class="form-group">
											<label class="control-label">Purchase code</label>											
											<input type="text" class= "form-control" readonly name="purchaseOrderCode" id="purchaseOrderCode" value="${messCommodityPurchaseOrderCode}"/>
										</div>
										<div class="form-group">
											<label class="control-label">Department</label>
											<select class="form-control" name="departmentCode" id="departmentCode">
												<c:forEach var="department" items="${departmentList}">
													<option value="${department.departmentCode}">${department.departmentName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<label class="control-label">Vendor Name</label>
											<select class="form-control" name="vendorCode" id="vendor">
												<option value="">Select...</option>
												<c:forEach var="vendor" items="${vendorList}">
													<option value="${vendor.vendorCode}">${vendor.vendorName} (${vendor.vendorType})</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<label class="control-label">Vendor Code</label>
											<input type="text" id="vendorCode" disabled class="form-control" placeholder="" >
										</div>
										<!-- <div class="form-group">
											<label class="control-label">Type</label>
											<select class="form-control" name="type" id="type" required>
												<option value="">Select...</option>
												<option value="ASSET">ASSET</option>
												<option value="EXPENSE">EXPENSE</option>
											</select>
										</div> -->
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
													<th>Qty(no/kg)</th>
													<th>Discount(%)</th>
													<th>Total</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody id="commodityTable">			                                        
												<tr class="gradeX">
													<td>
														Total Qty ::<input type="text" class="form-control" name="totalQtyOrdered" id="totalQtyOrdered" disabled value="0.00" />
													</td>
													<td>
														Total Amount ::<input type="text" class="form-control" name="totalCommodityAmount" id="totalCommodityAmount" disabled value="0.00" />
													</td>
													<td></td>
													<td>
														<a href="#" class="mb-xs mt-xs mr-xs modal-basic btn btn-info" id="addNewCommodityButton">Add</a>
													</td>
												</tr>
	                                        </tbody>
	                                    </table>
									</div>
								</section>
							</div>
							
							<div class="col-md-12" id="expenseDiv" style="display: none;">
	                            <section class="panel">
                               		<div class="panel-body">
	                                    <table class="table table-bordered table-striped mb-none"  style="display: block;">
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
								</section>
							</div> 
							
							<div class="col-md-12" id="amountTable" style="display: none;">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Payment Option</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label">Amount Payable To Vendor</label>											
												<input type="text" class="form-control" name="vendorPayable" id="vendorPayable" readonly value="0.00" placeholder="" />
											</div>
											<div class="form-group">
												<label class="control-label">Net Amount</label>
												<input type="text" class="form-control" name="netTotal" id="netTotal" readonly value="0.00" placeholder="" />
											</div>
											<div class="form-group">
												<label class="control-label">Pending Amount</label>
												<input type="text" class="form-control" name="pendingAmount" id="pendingAmount" readonly value="0.00" placeholder="" />
											</div>
											<div class="form-group">
												<label class="control-label">Advance Amount</label>											
												<input type="text" class="form-control" name="advanceAmount" id="advanceAmount" value="0.00" onblur="pendingCal();" onfocus="this.value='';" placeholder="" />
											</div>
											<div class="form-group">
												<label class="control-label">Ledger</label>
												<select class="col-md-6 form-control" id="ledger" name = "ledger">
													<option value="">Select...</option>
													<c:forEach var="ledger" items="${ledgerList}">
														<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
													</c:forEach>
												</select> 
											</div>
										</div>
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

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/inventory/messPurchaseOrder.js"></script>
</body>
</html>