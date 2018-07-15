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
				<h2>Commodity Requisition </h2>
			</header>
			<div class="content-padding">
				<c:if test="${status ne null}">
					<c:if test="${status ne 'fail'}">
						<div class="alert alert-success"  >
							<strong>${status}</strong>	
						</div>
					</c:if>
				</c:if>
					<c:if test="${status eq 'fail'}">
						<div class="alert alert-danger" >
							<strong>Failed to generate requisition</strong>	
						</div>
					</c:if>
					<div class="row">
						<%--<c:choose>
						 <c:when test="${vendorList eq null || empty vendorList || departmentList eq null || empty departmentList || commodityPurchaseOrderCode eq null}">
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
							<c:if test="${commodityPurchaseOrderCode eq null}">
								<div class="alert alert-danger">
									<strong>Purchase Order Code Generation Error. Try Again.</strong>
								</div>
							</c:if>
						</c:when>
						<c:otherwise> --%>
						<form action="createCommodityRequisition.html" method="post">
							<div class="col-md-6 col-md-offset-3">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Create Commodity Requisition</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="control-label">Requisition code</label>											
											<input type="text" class= "form-control" readonly name="purchaseOrderCode" id="purchaseOrderCode" value="${commodityRequisitionCode}"/>
										</div>
										<div class="form-group">
											<label class="control-label">Financial Year</label>
											<select class="form-control" name="financialYear" id="financialYear">
												<option value="">Select...</option>
												<c:forEach var="finance" items="${financialYearList}">
													<option value="${finance.financialYearCode}">${finance.financialYearName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<label class="control-label">Task No</label>
											<select class="form-control" name="purchaseId" id="purchaseId" required>
												<option>Select...</option>
												<c:forEach var="task" items="${taskList}">
													<option value="${task.taskCode}">${task.taskCode}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<label class="control-label">Ticket No</label>
											<input type="text" class= "form-control"  name="ticket" id="ticket" value="" readonly/>
										</div>
										
										<div class="form-group">
											<label class="control-label">Department</label>
											<select class="form-control" name="departmentCode" id="departmentCode" required>
												<option>Select...</option>
												<c:forEach var="department" items="${departmentList}">
													<option value="${department.departmentCode}">${department.departmentName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<table class="table table-bordered table-striped mb-none" id="departmentBudgetTable"  style="display: none;">
												<thead>
													<th>Budget Amount</th>
													<th>Balance Used</th>
													<th>Balance Remaining</th>
												</thead>
												<tbody>
												<tr class="gradeX">
													<td>
														<input type="text" class= "form-control" id="budgetAmount" readonly="readonly">
													</td>
													<td>
														<input type="text" class= "form-control" id="totalExpence" readonly="readonly">
													</td>
													<td>
														<input type="text" class= "form-control" id="balance" readonly="readonly">
													</td>
												</tr>
												</tbody>
											</table>
											<div class="alert alert-danger" id="budgetProblem" style="display: none;">
												<strong>Budget is not allocated for this department. Please allocate the budget first.</strong>
											</div>
										</div>
										
										<div class="form-group">
											<label class="control-label">Vendor Name</label>
											<select class="form-control" name="vendorCode" id="vendor" required>
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
													<!-- <th><a href="#" class="mb-xs mt-xs mr-xs modal-basic btn btn-info" id="addNewCommodityButton">Add</a></th> -->
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
					</div>
							

			</div>
	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/inventory/commodityRequisition.js"></script>
</body>
</html>