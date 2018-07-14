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
											<input type="text" class= "form-control" readonly name="purchaseOrderCode" id="purchaseOrderCode" value="${commodityRequisitionDetails.purchaseOrderCode}"/>
										</div>
										<div class="form-group">
											<label class="control-label">Department</label>
											<input type="text" class= "form-control" readonly name="departmentCode" id="departmentCode" value="${commodityRequisitionDetails.departmentName}"/>
										</div>
										
										<div class="form-group">
											<label class="control-label">Vendor Name</label>
											<input type="text" class= "form-control" readonly name="vendorName" id="vendorName" value="${commodityRequisitionDetails.vendorName}"/>
										</div>
																				
 									</div>									
								</section>
							</div>
							<div class="col-md-6 col-md-offset-3" id="commodityDiv" >
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
	                                        	<c:forEach var="cpod" items="${commodityRequisitionDetails.commodityPurchaseOrderDetailsList}" varStatus="rowCount">	
	                                        		<tr>
		                                        		<td>
		                                        			${cpod.commodity}
		                                        		</td>
		                                        		<td>
		                                        			${cpod.rate}
		                                        		</td>
		                                        		<td>
		                                        			${cpod.paymentType}
		                                        		</td>
		                                        		<td>
															${cpod.qtyOrdered}
		                                        		</td>
		                                        		<td>
															${cpod.amount}
														</td>
														
		                                        	</tr>	
		                                        </c:forEach>                                        
												<tr class="gradeX">
													<td>
														Total Qty :: ${commodityRequisitionDetails.totalQtyOrdered}
													</td>
													<td>
														Total Amount :: ${commodityRequisitionDetails.payAmount}
													</td>
													
												</tr>
	                                        </tbody>
	                                    </table>
									</div>
									
								</section>
							</div>
							
						</form>
					</div>
							

			</div>
	
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>