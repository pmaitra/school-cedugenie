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
<title>Student Details Form</title>
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

					<!-- start: page -->
					<div class="row">
						<c:choose>
							<c:when test="${message1 != null}">
								<%-- <div class="btnsarea01">
									<div class="errorbox" style="visibility: visible;" >
										<span>${message1}</span>
									</div>
								</div> --%>
								<div class="alert alert-danger">
									<strong>${message1}</strong>
								</div>
							</c:when>
						<c:otherwise>
						<form:form method="POST" id="makeCommodityPayment" name="makeCommodityPayment" action="makeCommodityPayment.html" >
						<input type="hidden" name="receiveStatus" value="${commodityPurchaseOrder.receiveStatus}" />
						<div class="col-md-6 col-md-offset-3">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Order Details</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Order ID</label>
                                                <%-- <label class="control-label">${commodityPurchaseOrder.purchaseOrderCode}</label> --%>
												<input type="text" class="form-control" readonly id="purchaseOrderCode" name="purchaseOrderCode" value="${commodityPurchaseOrder.purchaseOrderCode}" />
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Vendor Name(Code)</label>
                                                <%-- <label class="control-label">${commodityPurchaseOrder.vendorName} (${commodityPurchaseOrder.vendorCode})</label> --%>
												<input type="text" class="form-control" readonly id="vendorCode" name="vendorCode" value="${commodityPurchaseOrder.vendorName} (${commodityPurchaseOrder.vendorCode})" />
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Total Amount</label>
                                                <%-- <label class="control-label">${commodityPurchaseOrder.netTotal}</label> --%>
												<input type="text" class="form-control" readonly id="netTotal" name="netTotal"  value="${commodityPurchaseOrder.netTotal}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Department</label>
                                                <%-- <label class="control-label">${commodityPurchaseOrder.departmentName}</label> --%>
                                                <input type = "text" class="form-control" readonly value="${commodityPurchaseOrder.departmentName}">
												<input type="hidden" id="departmentCode" name="departmentCode"  value="${commodityPurchaseOrder.departmentCode}">
                                            </div>
                                        </div>
									</div>
								</section>
						</div>
						<div class="col-md-12">
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
                                                <label class="control-label">Advance Amount</label>
                                                <input type="text" id="advanceAmount" name="advanceAmount" value="${commodityPurchaseOrder.advanceAmount}" readonly class="form-control" placeholder="" >
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Pending(Rupees)</label>
                                                <input type="text" id="pendingAmount" name="pendingAmount" value="${commodityPurchaseOrder.pendingAmount}" readonly class="form-control" placeholder="" >
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Pay Amount</label>
                                                <input type="text" id="payAmount" name="payAmount" value="0.00" onblur="validate(this);" class="form-control" placeholder="" >
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Ledger</label>
                                                <select class="form-control" id="ledger" name = "ledger">
		                                           <option value="">Select...</option>
			                                          <c:forEach var="ledger" items="${ledgerList}">
														<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
													</c:forEach>
		                                       </select> 
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Payment Mode</label>
                                                <select name="transactionMode" id="transactionMode" onchange="bankDetails(this);" class="form-control">
                                                	<option value="CASH">Cash</option>
													<option value="BANK">Bank</option>
												</select>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Cheque No</label>
                                                <input type="text" name="chequeNo" id="chequeNo" readonly class="form-control" >
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Bank Name</label>
                                                <input type="text" name="bankName" id="bankName" readonly class="form-control" >
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Bank Code</label>
                                                <input type="text" name="bankCode" id="bankCode" readonly class="form-control" >
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Bank Location</label>
                                                <input type="text" name="bankLocation" id="bankLocation" readonly class="form-control" >
                                            </div>
                                        </div>    
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
									<!-- 
										<button class="btn btn-primary" type="submit" id="submit" name="submit" >Submit</button>
										<a href="commodityPurchaseOrderList.html?status=OPEN"><input type="button" class="editbtn" value="Back" ></a> -->
										<button class="mb-xs mt-xs mr-xs btn btn-primary" type="submit" id="submit" name="submit" onclick = "return validatePayment()">Submit</button>
										<button class="mb-xs mt-xs mr-xs btn btn-warning" type ="button" onclick="window.location='commodityPurchaseOrderList.html?status=OPEN'">Back</button>												
									
									</footer>
								</section>
                            
						</div>
						</form:form>
						</c:otherwise>
						</c:choose>
					</div>
                   
					<!-- end: page -->


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/inventory/commodityPurchaseOrderPayment.js"></script>
<script type="text/javascript">
function validatePayment(){
	var ran = /^\d+.?\d*$/;
	var regnum = '^[0-9]+$';
	var payAmount = $("#payAmount").val(); 
	var payMode= $("#transactionMode").val();
	if(payAmount == ""){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningmsg").innerHTML="Please Enter number of copies.";
		alert("Please enter the amount you want to pay now.");
		return false;
	}
	if(payAmount != ""){
		if(!payAmount.match(ran)) {
//			document.getElementById("warningbox").style.visibility='visible';
//			document.getElementById("warningmsg").innerHTML="Please Enter Numeric number of copies.";
			alert("The input is not valid. Please enter positive numeric value.");
			return false;
		}
	}
	if(payMode == ""){
		alert("please select the mode of payment.");
		return false;
	}
	else{
		return true;
	}
		
};
</script>
</body>
</html>