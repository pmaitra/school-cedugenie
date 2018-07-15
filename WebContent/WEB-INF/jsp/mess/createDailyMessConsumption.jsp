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
<title>Mess Daily Ration Consumption</title>
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
		<h2>Mess Daily Ration Consumption</h2>
	</header>
	<div class="content-padding">
		<div class="row">
			<form action="submitMessDailyConsumption.html" method="post">
				<div class="col-md-6 col-md-offset-3"> 
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
							<h2 class="panel-title">Consumption Sheet</h2>
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
			                          	<label class="control-label">Date Of Issue</label>
			                          	<div class="input-group">
			                              	<span class="input-group-addon">
			                                  <i class="fa fa-calendar"></i>
			                             	</span>
			                             	<input type="text" class="form-control" id="dateOfIssue" name="dateOfIssue"  readonly="readonly">
			                    		</div>
           							</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">CIV No:<span class="required" aria-required="true">*</span></label>
										<input type = "text" class="form-control" id="issueToKitchenCiv" name="issueToKitchenCiv" value="${lastConsumptionCIVCode}" required>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label">Issued By:</label>
										<input type = "text" class="form-control" id="userName" name="userName" value="${userName}">
										<input type = "hidden" class="form-control" id="userId" name="userId" value="${userId}">
									</div>
								</div>
							</div>
						</div>
					</section>
				</div>
				<div class="col-md-12">
					<section class="panel">
						<header class="panel-heading">
						    <h2 class="panel-title">Commodity Details</h2>
						</header>
                        <div class="panel-body">
							<table class="table table-bordered table-striped mb-none" id="messDailyConsumptionDiv">
								<thead>
									<tr>
										<th>LP No</th>
										<th>Commodity Type<span class="required" aria-required="true">*</span></th>
										<th>Commodity Name<span class="required" aria-required="true">*</span></th>
										<th>A/U<span class="required" aria-required="true">*</span></th>
										<th>Stock</th>
										<th>Rate<span class="required" aria-required="true">*</span></th>
										<th>Issuing Quantity<span class="required" aria-required="true">*</span></th>
										<th>Amount</th>
										<th>Actions</th>
									</tr>
								</thead>
								<tbody class="gradeC">
									<tr>
										<td>
											<input type="text" class="form-control" name="lpNo" id="lpNo0">
										</td>
										<td>
											<select id="commodityType0" name="commodityType" class="form-control" onchange="getCommodities(this);" required>
												<option value="">Select..</option>
												<option value="dailyration">Daily Ration Items</option>
												<option value="nondailyration">Other Items</option>
											</select>
										</td>
										<td>
											<select class="form-control" id="commodityName0" name="commodityName" onchange="getCommodityStock(this);" required>
												<option value="">Select..</option>
											</select>
										</td>
										<td>
											<input type="text" class="form-control" id="commodityAU0" name="commodityAU" readonly="readonly">
										</td>
										<td>
											<input type="text" class="form-control" id="oldStockInForIssue0" name="oldStockInForIssue" value="0.0" readonly="readonly">
										</td>
										<td>
											<input type="text" class="form-control" id="rate0" name="rate">
										</td>
										<td>
											<input type="text" class="form-control" id="issuingQuantity0" name="issuingQuantity" value="0.0" onfocus="this.value=''" onblur="calculateAmount(this);">
										</td>
										<td>
											<input type="text" class="form-control" id="amount0" name="amount" readonly="readonly">
										</td>
										<td>
											<button type="button" class="mb-xs mt-xs mr-xs modal-basic btn btn-primary" id="addNewCommodityButton" onclick = "addNewCommodity()">Add</button>
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
<script type="text/javascript" src="/cedugenie/js/mess/createDailyMessConsumption.js"></script>
</body>
</html>