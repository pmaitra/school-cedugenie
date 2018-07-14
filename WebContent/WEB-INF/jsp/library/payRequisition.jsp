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
<title>Pay Requisition</title>
<%@ include file="/include/include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/library/payRequisition.js"></script>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

<c:choose>
	<c:when test="${bookPurchaseOrder == null}">
		<div class="btnsarea01" style="visibility: visible;">
			<div class="errorbox" id="errorbox" >
				<span id="errormsg">Data Not Found</span>	
			</div>
		</div>
	</c:when>
<c:otherwise>
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<form action="updatePurchaseOrderPayment.html" method="post">
		          <section class="panel">
                        <header class="panel-heading">
                            <div class="panel-actions">
                                <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
                            </div>
                            <h2 class="panel-title">Pay Requisition</h2>										
                        </header>
		                <div style="display: block;" class="panel-body"> 
		                     <div class="row">
		                          <div class="col-md-10 col-md-offset-1">
	                                   <div class="form-group">
	                                       <label class="col-md-6 control-label">Order Code</label>
	                                       <label class="col-md-6 control-label">${bookPurchaseOrder.purchaseOrderCode}</label>
	                                       <input type="hidden" readonly="readonly" name="purchaseOrderCode" id="purchaseOrderCode" value="${bookPurchaseOrder.purchaseOrderCode}"/>
	                                   </div>
	                                   <div class="form-group">
	                                       <label class="col-md-6 control-label">Qty Ordered</label>
	                                       <label class="col-md-6 control-label">${bookPurchaseOrder.totalQtyOrdered}</label>
	                                       <input type="hidden" readonly="readonly" name="totalQtyOrdered" id="totalQtyOrdered" value="${bookPurchaseOrder.totalQtyOrdered}" />
	                                   </div>
	                                   <div class="form-group">
	                                       <label class="col-md-6 control-label">Qty Remeaning</label>
	                                       <label class="col-md-6 control-label">${bookPurchaseOrder.totalQtyDeficit}</label>
	                                       <input type="hidden" readonly="readonly" name="totalQtyDeficit" id="totalQtyDeficit" value="${bookPurchaseOrder.totalQtyDeficit}" />
	                                   </div>
	                                   <div class="form-group">
	                                       <label class="col-md-6 control-label">Qty Received</label>
	                                       <label class="col-md-6 control-label">${bookPurchaseOrder.totalQtyReceived}</label>
	                                       <input type="hidden" readonly="readonly" name="totalQtyReceived" id="totalQtyReceived" value="${bookPurchaseOrder.totalQtyReceived}" />
	                                   </div>
	                                   <div class="form-group">
	                                       <label class="col-md-6 control-label">Paid Amount</label>
	                                       <label class="col-md-6 control-label">${bookPurchaseOrder.advanceAmount}</label>
	                                       <%-- <input type="hidden" readonly="readonly" name="advanceAmount" id="advanceAmount" value="${bookPurchaseOrder.advanceAmount}" /> --%>
	                                   </div>
	                                   <div class="form-group">
	                                       <label class="col-md-6 control-label">Total Amount</label>
	                                       <label class="col-md-6 control-label">${bookPurchaseOrder.totalAmount}</label>
	                                       <input type="hidden" readonly="readonly"  name = "totalAmount" id="totalAmount" value="${bookPurchaseOrder.totalAmount}" />
	                                   </div>
		                               <c:choose>
											<c:when test="${bookPurchaseOrder.advanceAmount>0}">
			                                   <div class="form-group">
			                                       <label class="col-md-3 control-label">STDS ${bookPurchaseOrder.stdsInPercent}%</label>
			                                       <!-- <input type="text" placeholder="0.00" name="firstname" class="col-md-3 form-control" style="width:80px;"> -->
			                                       <input type="hidden" readonly="readonly"  name="status" value="DONE" />
			                                       <label class="col-md-3 control-label">STDS Amount Rs. ${bookPurchaseOrder.stdsInAmount}</label>
			                                       <!-- <input type="text" placeholder="0.00" name="firstname" class="col-md-3 form-control" style="width:80px;"> -->
			                                   </div>
			                                   <div class="form-group">
			                                       <label class="col-md-3 control-label">Service Charge Rs. ${bookPurchaseOrder.serviceCharge}</label>
			                                       <!-- <input type="text" placeholder="0.00" name="firstname" class="col-md-3 form-control" style="width:80px;"> -->
			                                       <label class="col-md-3 control-label">Service Tax  ${bookPurchaseOrder.serviceTaxInPercent}%</label>
			                                       <!-- <input type="text" placeholder="0.00" name="firstname" class="col-md-3 form-control" style="width:80px;"> -->
			                                   </div>
			                                   <div class="form-group">
			                                       <label class="col-md-3 control-label">TDS ${bookPurchaseOrder.tdsInPercent}%</label>
			                                       <!-- <input type="text" placeholder="0.00" name="firstname" class="col-md-3 form-control" style="width:80px;"> -->
			                                       <label class="col-md-3 control-label">TDS Amount Rs. ${bookPurchaseOrder.tdsInAmount}</label>
			                                       <!-- <input type="text" placeholder="0.00" name="firstname" class="col-md-3 form-control" style="width:80px;"> -->
			                                   </div>
			                                   <div class="form-group">
			                                       <label class="col-md-6 control-label">Total Amount Payable</label>
			                                       <label class="col-md-6 control-label">${bookPurchaseOrder.netAmount}</label>
			                                       <!-- <div class="col-md-6" >
			                                       <input type="text" name="firstname" class="col-md-6 form-control" value="6183.00"> 
			                                       </div> -->
			                                   </div>
			                                   <div class="form-group">
			                                       <label class="col-md-6 control-label">Total Pending Amount</label>
			                                       <div class="col-md-6">
				                                       <input type="text" name="pendingAmount" class="col-md-6 form-control" id="totalPendingAmount" value="${bookPurchaseOrder.pendingAmount}" readonly="readonly"> 
				                                       <input type="hidden" readonly="readonly"  id="demoPendingAmount" value="${bookPurchaseOrder.pendingAmount}" />
			                                   	   </div>
			                                   </div>
			                               </c:when>
			                           <c:otherwise>
	                                   <div class="form-group">
	                                       <label class="col-md-3 control-label">STDS(%)</label>
	                                       <input type="text" placeholder="0.00" class="col-md-3 form-control" style="width:80px;" name="stdsInPercent" id="stdsInPercent" value="0.00" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}calculateTotalPayableAmount();">
	                                       <!-- <input type="hidden" readonly="readonly"  name="status" value="DONE" /> -->
	                                       <label class="col-md-3 control-label">STDS Amount Rs.</label>
	                                       <input type="text" placeholder="0.00" class="col-md-3 form-control" style="width:80px;" name="stdsInAmount" id="stdsInAmount" value="0.00" readonly="readonly">
	                                   </div>
	                                   <div class="form-group">
	                                       <label class="col-md-3 control-label">Service Charges Rs.</label>
	                                       <input type="text" placeholder="0.00" class="col-md-3 form-control" style="width:80px;" name="serviceCharge" id="serviceCharge" value="0.00" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}calculateTotalPayableAmount();">
	                                       <label class="col-md-3 control-label">Service Tax (%)</label>
	                                       <input type="text" placeholder="0.00" class="col-md-3 form-control" style="width:80px;" name="serviceTaxInPercent" id="serviceTaxInPercent" value="0.00"  onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}calculateTotalPayableAmount();">
	                                   </div>
	                                   <div class="form-group">
	                                       <label class="col-md-3 control-label">TDS(%)</label>
	                                       <input type="text" placeholder="0.00" class="col-md-3 form-control" style="width:80px;" name="tdsInPercent" id="tdsInPercent" value="0.00" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}calculateTotalPayableAmount();">
	                                       <label class="col-md-3 control-label">TDS Amount Rs.</label>
	                                       <input type="text" placeholder="0.00" class="col-md-3 form-control" style="width:80px;" name="tdsInAmount" id="tdsInAmount" value="0.00" readonly="readonly">
	                                   </div>
	                                   <div class="form-group">
	                                       <label class="col-md-6 control-label">Total Amount Payable</label>
	                                      <%--  <label class="col-md-6 control-label">${bookPurchaseOrder.netAmount}</label> --%>
	                                       <div class="col-md-6" >
	                                       	<input type="text" class="col-md-6 form-control" name="netAmount" id="totalPayableAmount" value="${bookPurchaseOrder.totalAmount}" readonly="readonly"> 
	                                       </div>
	                                   </div>
	                                   <div class="form-group">
	                                       <label class="col-md-6 control-label">Total Pending Amount</label>
	                                       <div class="col-md-6">
	                                       <input type="text" class="col-md-6 form-control" name="pendingAmount" id="totalPendingAmount" value="${bookPurchaseOrder.totalAmount}" readonly="readonly"> 
	                                       <input type="hidden" readonly="readonly"  id="demoPendingAmount" value="${bookPurchaseOrder.totalAmount}" />
	                                       </div>
	                                   </div>
		                               </c:otherwise>
		                               </c:choose>
	                                   <div class="form-group">
	                                       <label class="col-md-6 control-label">Paying Amount</label>
	                                       <div class="col-md-6">
	                                       <input type="text" class="col-md-6 form-control" name="advanceAmount" id="payingAmount" value="0.00" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}calculatePendingAmount();">
	                                       </div>
	                                   </div>
	                                   <div class="form-group">
	                                       <label class="col-md-6 control-label">Payment Mode</label>
	                                       <div class="col-md-6">
		                                       <select class="col-md-6 form-control" id="transactionMode" onchange="showBankDetails(this);" name="modeOfPayment">
		                                           <option value="">Select...</option>
		                                           <option value="CASH">Cash</option>
												   <option value="BANK">Bank</option>
		                                       </select> 
	                                       </div>
	                                   </div>
	                                   <div class="form-group">
	                                       <label class="col-md-6 control-label">Ledger</label>
	                                       <div class="col-md-6">
		                                       <select class="col-md-6 form-control" id="ledger" name = "ledger">
		                                           <option value="">Select...</option>
			                                          <c:forEach var="ledger" items="${ledgerList}">
														<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
													</c:forEach>
		                                       </select> 
	                                       </div>
	                                   </div>
		                          </div>   
	                              <div class="col-md-12" id="paymentDetails">
	                                  <hr>
	                                  <table class="table table-bordered table-striped mb-none">
	                                      <thead>
	                                          <tr>
	                                              <th>Cheque No.</th>
	                                              <th>Bank Code</th>											
	                                              <th>Bank Name</th>
	                                              <th>Bank Location</th>
	                                          </tr>
	                                      </thead>
	                                      <tbody>
	                                          <tr>											
	                                              <td><input type="text" class="form-control" name="chequeNo" id="chequeNo" value="" disabled></td>
	                                              <td><input type="text" class="form-control" name="bankCode" id="bankCode" value="" disabled></td>
	                                              <td><input type="text" class="form-control" name="bankName" id="bankName" value="" disabled></td>
	                                              <td><input type="text" class="form-control" name="bankLocation" id="bankLocation" value="" disabled></td>
	                                          </tr>
	
	                                      </tbody>
	                                  </table>
	                              </div>
		                      </div>
		                  </div>
		                  <footer class="panel-footer">
							<button class="btn btn-primary" type="submit" value="Pay" onclick="return validatePayRequisition();">Pay </button>
						  </footer>
		              </section>
		           </form>    
		     </div>
		</div>
	</c:otherwise>
</c:choose>		
                   

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>