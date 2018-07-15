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
<title>Create Perishable Material Requisition</title>
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
		<h2>Create Perishable Material Requisition</h2>
	</header>
	<div class="content-padding">
		<div class="row">
			<form action="submitPerishableMaterialRequisition.html" method="post">
				<div class="col-md-9 col-md-offset-2"> 
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
							<h2 class="panel-title">
								<%-- Commodity Issue Voucher Id : ${nextCIVId}
								<input type="hidden" id="commodityIssueVoucherId" name="commodityIssueVoucherId" value="${nextCIVId}"/> --%>
								Order Details
							</h2>
						</header>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Inventory Session</label>
										<input type="text" class="form-control" id = "inventorySession" name = "inventorySession" readonly="readonly" value="${inventorySession}"/>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Order ID</label>
										<input type="text" class="form-control" id="orderId" name="orderId" readonly="readonly" value="${nextPMR}"/>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Order Code</label>
										<input type="text" class="form-control" id="orderNumber" name="orderNumber" readonly="readonly" value="${nextPMROrderNo}"/>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">End Date<span class="required" aria-required="true">*</span></label>
										<div class="input-group">
			                            	<span class="input-group-addon">
		                     					<i class="fa fa-calendar"></i>
			                                </span>
											<input type="text" class="form-control" id="endDate" name="endDate" data-plugin-datepicker="" data-date-start-date="0d" required/>
										</div>
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
						    	Perishable Material Requisition Requisition ::
								<input type="hidden" id="messDailyRationPurchaseOrderCode" name="messDailyRationPurchaseOrderCode" value="${lastPOCode}"/>
						    </h2>
						</header>
                        <div class="panel-body">
	                        <table class="table table-bordered table-striped mb-none" id="messDailyRationPo">
								<thead>
									<tr>
										<th>Commodity<span class="required" aria-required="true">*</span></th>
										<th>Unit<span class="required" aria-required="true">*</span></th>
										<th>Quantity<span class="required" aria-required="true">*</span></th>
										<th>Actions</th>
									</tr>
								</thead>
								<tbody class="gradeC">
									<tr>
										<td>
											<input id="commodity0" name="commodity" class="form-control commodityNameClass" onkeydown="makeAutocomplete(this);" required/>
										</td>
										<td>
											<!-- <input type="text" class="form-control" id="commodityUnit0" name="commodityUnit"> -->
											<select id="commodityUnit0" name="commodityUnit" class="form-control" required>
												<option>Select...</option>
												<c:forEach var="commodityUnit" items="${commodityUnitList}">
													<option value="${commodityUnit.commodityName}">${commodityUnit.commodityName}</option>
												</c:forEach>
											</select>
										</td>
										<td>
											<input type="text" class="form-control" id="commodityDemandedQuantity0" name="commodityDemandedQuantity" required value="0.0" onfocus="this.value=''">
										</td>
										<td>
											<button type="button" class="mb-xs mt-xs mr-xs btn btn-primary" id="addNewCommodityButton" onclick = "addNewCommodity()">Add</button>
										</td>
									</tr>
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
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/mess/createPerishableMaterialRequisition.js"></script>
</body>
</html>