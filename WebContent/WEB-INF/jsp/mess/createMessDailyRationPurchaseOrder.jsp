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
<title>Mess Daily Ration Procurement</title>
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
		<h2>Mess Daily Ration Procurement</h2>
	</header>
	<div class="content-padding">
		<div class="row">
			<form action="submitMessDailyRationPO.html" method="post">
				<div class="col-md-6 col-md-offset-3"> 
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
							<h2 class="panel-title">
								<%-- Commodity Issue Voucher Id : ${nextCIVId}
								<input type="hidden" id="commodityIssueVoucherId" name="commodityIssueVoucherId" value="${nextCIVId}"/> --%>
								Vendor Details
							</h2>
						</header>
						<div class="panel-body">
							<div class="row">
								<!-- added by Sourav.Bhadra on 24-10-2017 -->
								<c:if test="${orderId ne null}">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label">Requisition ID</label>
											<input type="text" class="form-control" id="reqID" name="reqID" value="${orderId}" readonly="readonly"/>
										</div>
									</div>
								</c:if>
								<c:if test="${orderNumber ne null}">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label">Requisition Code</label>
											<input type="text" class="form-control" id="reqCode" name="reqCode" value="${orderNumber}" readonly="readonly"/>
										</div>
									</div>
								</c:if>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Inventory Session</label>
										<input type="text" class="form-control" id = "inventorySession" name = "inventorySession" readonly="readonly" value="${inventorySession}"/>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Vendor Name</label>
										<c:choose>
											<c:when test="${vendorList.size() eq 0}">
												<input type="text" class="form-control" id="vendorName" name="vendorName"/>
											</c:when>
											<c:otherwise>
												<select class="form-control" id="vendorName" name="vendorName" onchange="getDailyRationVendorDetails(this);">
													<option value="">Select</option>
													<c:forEach var="vendor" items="${vendorList}">
														<option value="${vendor.vendorCode}">${vendor.vendorName}</option>
													</c:forEach>
												</select>
											</c:otherwise>
										</c:choose>
									</div>
								</div>
								<c:if test="${vendorList.size() ne 0}">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label">Vendor Code</label>
											<input type="text" class="form-control" id="vendorCode" name="vendorCode" readonly="readonly"/>
										</div>
									</div>
								</c:if>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Vendor Address</label>
										<c:choose>
											<c:when test="${vendorList.size() eq 0}">
												<input type="text" class="form-control" id="vendorAddress" name="vendorAddress"/>
											</c:when>
											<c:otherwise>
												<input type="text" class="form-control" id="vendorAddress" name="vendorAddress" readonly="readonly"/>
											</c:otherwise>
										</c:choose>
										
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Vendor Contact No.</label>
										<c:choose>
											<c:when test="${vendorList.size() eq 0}">
												<input type="text" class="form-control" id="vendorContactNumber" name="vendorContactNumber"/>
											</c:when>
											<c:otherwise>
												<input type="text" class="form-control" id="vendorContactNumber" name="vendorContactNumber" readonly="readonly"/>
											</c:otherwise>
										</c:choose>
										
									</div>
								</div>
							</div>
						</div>
					</section>
				</div>
				<div class="col-md-12">
					<c:forEach var="unit" items="${commodityUnitList}">
						<input type="hidden" id="comUnit" name="comUnit" value="${unit.commodityName}"/>
					</c:forEach>
					<section class="panel">
						<header class="panel-heading">
						    <h2 class="panel-title">
						    Mess Daily Ration Purchase Order Code :${lastPOCode} 
							<input type="hidden" id="messDailyRationPurchaseOrderCode" name="messDailyRationPurchaseOrderCode" value="${lastPOCode}"/>
						    </h2>
						</header>
                        <div class="panel-body">
	                        <table class="table table-bordered table-striped mb-none" id="messDailyRationPo">
								<thead>
									<tr>
										<th>Commodity<span class="required" aria-required="true">*</span></th>
										<th>Unit<span class="required" aria-required="true">*</span></th>
										<th>Rate(As per Unit)<span class="required" aria-required="true">*</span></th>
										<th>Quantity<span class="required" aria-required="true">*</span></th>
										<th>Amount<span class="required" aria-required="true">*</span></th>
									</tr>
								</thead>
								<tbody class="gradeC">
									<c:choose>
										<c:when test="${perishableMaterialsList ne null || empty perishableMaterialsList}">
											<c:forEach var="pml" items="${perishableMaterialsList}" varStatus="i">
												<tr>
													<td>
														<input id="commodity${i.index}" name="commodity" class="form-control" value="${pml.commodityName}" readonly="readonly"/>
													</td>
													<td>
														<input id="commodityUnit${i.index}" name="commodityUnit" class="form-control" value="${pml.commodityUnit}" readonly="readonly"/>
													</td>
													<td>
														<input type="text" class="form-control" id="commodityRate${i.index}" name="commodityRate" required value="0.0" onfocus="this.value=''" onblur="calculateAmount('${i.index}');">
													</td>
													<td>
														<input type="text" class="form-control" id="commodityDemandedQuantity${i.index}" name="commodityDemandedQuantity" value="${pml.commodityQuantity}" readonly="readonly">
													</td>
													<td>
														<input type="text" class="form-control" id="amount${i.index}" name="amount" value="0.0" readonly="readonly">
													</td>
												</tr>
											</c:forEach>
											<tr>
												<td>
													<label class="control-label">Total Amount</label>
												</td>
												<td>
													<input type="text" class="form-control" id="totalAmount" name="totalAmount" value="0.0" readonly="readonly">
												</td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr>
												<td>There is no Perishable Material Found</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
						<footer style="display: block;" class="panel-footer">
							<button class="btn btn-primary" type="submit" id="submit" name="submit">Submit</button>
							<button type="reset" class="btn btn-default">Reset</button>
						</footer>
					</section>
				</div>
			</form>
		</div>
	</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/mess/createMessDailyRationPurchaseOrder.js"></script>
</body>
</html>