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
<title>Mess Demand Voucher</title>
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
		<h2>Create Indent Sheet</h2>
	</header>
	<div class="content-padding">
		<div class="row">
			<c:if test="${insertStatus eq 'success'}">
				<div class="alert alert-success">
					<strong>${indentSheetId} is created Successfully.</strong>
				</div>
			</c:if>
			<c:if test="${insertStatus eq 'fail'}">
				<div class="alert alert-danger">
					<strong>${indentSheetId} creation failed.</strong>
				</div>
			</c:if>
			<c:choose>
				<c:when test="${commodityList eq null || empty commodityList}">
					<div class="alert alert-danger">
						<strong>There no Commodity for Mess</strong>
					</div>
				</c:when>
				<c:otherwise>
					<form action="submitMessDemandVoucher.html" method="post">
						<div class="col-md-12">
							<section class="panel">
								<header class="panel-heading">
								    <h2 class="panel-title">
								    Indent No/${messVoucherRequestId} 
									<input type="hidden" id="demandVoucherId" name="demandVoucherId" value="${messVoucherRequestId}"/>
								    </h2>
								</header>
		                        <div class="panel-body">
			                        <c:forEach var="commodity" items="${commodityList}">
										<input type = "hidden" name = "commodityCode" value="${commodity.commodityCode}"/>
										<input type = "hidden" name = "commodityName" value="${commodity.commodityName}"/>
									</c:forEach>
									<table class="table table-bordered table-striped mb-none" id="createDemandVoucherTable">
										<thead>
											<tr>
												<th>Commodity<span aria-required="true" class="required">*</span></th>
												<th>A/U</th>
												<th>Stock In Hand(As per QM)</th>
												<th>Quantity Demanded<span aria-required="true" class="required">*</span></th>
												<!-- <th>Rate(As per Unit)</th> -->
												<th>Actions</th>
											</tr>
										</thead>
										<tbody class="gradeC">
											<tr>
												<td>
													<select id="commodity0" name="commodity" class="form-control" onchange="getUnitStockRate(this);" required>
														<option value="">Select</option>	<!-- modified by sourav.bhadra on 12-10-2017 -->
														<c:forEach var="commodity" items="${commodityList}">
															<option value="${commodity.commodityCode}">${commodity.commodityName}</option>
														</c:forEach>
													</select>
												</td>
												<td>
													<input type="text" class="form-control" id="commodityUnit0" name="commodityUnit" readonly>
												</td>
												<td>
													<input type="text" class="form-control textfield2" id="commodityStock0" name="commodityStock" readonly>
												</td>
												<td>
													<!-- modified by sourav.bhadra on 12-10-2017 -->
													<input type="text" class="form-control textfield1" id="commodityDemandedQuantity0" name="commodityDemandedQuantity" pattern="^\s*(?=.*[1-9])\d*(?:\.\d{1,2})?\s*$" onblur="checkWithStock(this);" required>
												</td>
												<!-- <td>
													<input type="text" class="form-control" id="commodityRate0" name="commodityRate" readonly>
												</td> -->
												<td>
													<button type="button" class="mb-xs mt-xs mr-xs modal-basic btn btn-primary" id="addNewCommodityButton" onclick = "addNewCommodity()">Add</button>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<footer style="display: block;" class="panel-footer">
									<button class="btn btn-primary" type="submit" id="submit" name="submit">Submit</button>	<!-- modified by sourav.bhadra on 12-10-2017 -->
									<!-- onclick = "return validateIndentSheet();" -->
									<button type="reset" class="btn btn-default">Reset</button>
								</footer>
								<div class="alert alert-danger" id="warningDiv" style="display:none">
									<span id="warningmsg"></span>
								</div>
							</section>
						</div>
					</form>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/mess/createMessDemandVoucher.js"></script>
</body>
</html>