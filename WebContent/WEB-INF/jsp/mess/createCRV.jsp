<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Commodity Receive Voucher</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
	.scroll-to-top{
	    display: none !important;
	 }
</style>
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
</head>
<body>
<header class="page-header">
	<h2>Commodity Receive Voucher</h2>
</header>
<div class="content-padding">
	<div class= "row">
		<c:choose>
			<c:when test="${issueVoucher eq null}">
				<div class="alert alert-danger">
					<strong>No Details Found Against This Issue Voucher.</strong>
				</div>
			</c:when>
			<c:otherwise>
				<form action="submitCommodityReceiveVoucher.html" method = "POST">
					<div class="col-md-6 col-md-offset-3">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>
								<h2 class="panel-title">
									Commodity Receive Voucher Id : ${nextCRVId}
									<input type="hidden" id="commodityReceiveVoucherId" name="commodityReceiveVoucherId" value="${nextCRVId}"/>
									<input type="hidden" id="commodityIssueVoucherId" name="commodityIssueVoucherId" value="${issueVoucher.commodityIssueVoucherCode}"/>
								</h2>
							</header>
							<div class="panel-body">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label">Indent No</label>
											<input type="text" class="form-control" readonly="readonly" id = "demandVoucherId" name = "demandVoucherId" value="${issueVoucher.messDemanVoucher.demandVoucherId}"/>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label">Open Date</label>
											<input type="text" class="form-control" readonly="readonly" id="demandVoucherOpenDate" name="demandVoucherOpenDate" value="${issueVoucher.messDemanVoucher.demandVoucherOpenDate}"/>
										</div>
									</div>
								</div>
							</div>
						</section>
					</div>
					<div class="col-md-6 col-md-offset-3">
						<div class="alert alert-warning" id="warningDiv" style="display: none">
							<span id = "warningMsg"></span>
						</div>
					</div>
					<div class="col-md-12">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-actipon-toggle" data-panel-toggle= ""></a>
								</div>
								<h2 class="panel-title">Goods Details</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
									<thead>
										<tr>
											<th>Commodity</th>
											<th>Commodity A/U</th>
											<th>Demanded Quantity</th>
											<th>Quantity Issued</th>
											<th>Rate</th>
											<th>LP No</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="demand" items="${issueVoucher.commodityIssueVoucherDetailsList}" varStatus ="i" >
											<tr>
												<td>
													<input type="text" class="form-control" readonly="readonly" id="commodityName" name = "commodityName" value ="${demand.commodityName}"/>
												</td>
												<td>
													<input type="text" class="form-control" readonly="readonly" value="${demand.messDemandVoucherDetails.commodityUnit}">
												</td>
												<td>
													<input type="text" class="form-control" readonly="readonly" value="${demand.messDemandVoucherDetails.demandedQuantity}">
												</td>
												<td>
													<input type="text" readonly="readonly" class="form-control" id="issuedQuantityToMess" name = "issuedQuantityToMess" value ="${demand.issuedQuantityToMess}"/>
												</td>
												<td>
													<input type="text" class="form-control" readonly="readonly" value="${demand.messDemandVoucherDetails.commodityRate}">
												</td>
												<td>
													<input type="text" class="form-control" id="lpNo" name="lpNo" required>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<footer class="panel-footer">
								<button type="submit" id="submitBtn" class="btn btn-primary">Receive</button>
								<button type="button" class="btn btn-warning" onclick="window.location='viewDemandVoucherList.html'">Back</button>
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
</body>
</html>