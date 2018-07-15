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
<title>Create Transactions</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
   .scroll-to-top{
	   display: none !important;
   }
</style>
</head>
<body>
	<header class="page-header">
		<h2>Create Transaction</h2>
	</header>
	<div class="content-padding">
	<c:choose>
		<c:when test="${message ne null}">		
				<div class="alert alert-danger">
					<span>${message}</span>	
				</div>		
		</c:when>
		<c:otherwise>
			<div class="row">
				<div class="col-md-12">
					<form:form name="" action="saveTransaction.html" method="POST" >
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>
	
								<h2 class="panel-title">Create Transactions</h2>										
							</header>
							<div style="display: block;" class="panel-body">
                                    <div class="row">
                                         <div class="col-md-3">
											<div class="form-group">
                                                 <label class="control-label">Vendor Name</label>
                                                 <input type = "text" name="vendorName" id="vendorName" class="form-control" value="${transactionalWorkingArea.resource}" required readonly >                                            
                                            </div>
											<div class="form-group">
                                                 <label class="control-label">Voucher Type</label>
                                                 <select name="voucherTypeCode" id="voucherTypeCode" class="form-control" onchange="checkValues();" required>
													<c:if test="${voucherTypeList ne null}">
														<c:forEach var="vt" items="${voucherTypeList}">
															<option value="${vt.voucherTypeCode}">${vt.voucherTypeName}</option>
														</c:forEach>
													</c:if>		
												</select>                                                 
                                             </div>      
                                         </div>
                                         <div class="col-md-3">
                                         	<div class="form-group">
		                                        <label class="control-label">Date</label>
		                                        <label class="control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                <input type="text" class="form-control" name="transactionDate" id="transactionDate" data-plugin-datepicker="" required/>
		                                            </div>
		                                        </label>
	                                    	</div>
                                         	<div id="paymentModeDiv" class="form-group" style="display:none">
                                                 <label class="control-label">Mode</label>
                                              	 <select name="paymentMode" id="paymentMode" class="form-control" onchange="getval(this);" ><!-- modified by sourav.bhadra on 20-09-2017 -->
													<option value="">Select</option>
													<option value="CASH">Cash</option>
													<option value="BANK">Bank</option>
													<option value="MONEY_TRANSFER">Money Transfer</option>
													 <!-- added by sourav.bhadra on 22-08-2017 -->
													<option value="PETTY_CASH">Petty Cash</option>
												</select>
											</div>
                                         </div>
                                         <div class="col-md-3"> 
                                         	<div class="form-group">
                                                <label class="control-label">Department</label>
                                              	<input type = "text" name="department" id="department" class="form-control" value = "${transactionalWorkingArea.department}" required readonly >
                                            	<input type = "hidden" name="transactionCode" id="transactionCode" class="form-control" value = "${transactionalWorkingArea.transactionalWorkingAreaCode}"  >
                                            </div>                                            
                                             <div class="form-group">
                                                 <label class="control-label">Narration</label>
                                                 <textarea name="narration" id="narration" rows="4" data-plugin-maxlength="" maxlength="140" class="form-control"></textarea>
                                             </div>                                             
                                         </div>
                                         <div class="col-md-3">
                                         	<div class="form-group">
	                                            <label class="control-label"><b>Inc/Exp</b></label>
	                                            <input type="hidden" name="incomeExpense" value = "${transactionalWorkingArea.incomeExpense}">
	                                            <select id="incomeExpense" name="incomeExpense" class="form-control" disabled>
	                                            	<option value="INCOME" ${transactionalWorkingArea.incomeExpense eq 'INCOME'?'selected':value}>INCOME</option>
	                                            	<option value="EXPENSE" ${transactionalWorkingArea.incomeExpense eq 'EXPENSE'?'selected':value}>EXPENSE</option>
	                                            	<option value="ASSET" ${transactionalWorkingArea.incomeExpense eq 'ASSET'?'selected':value}>ASSET</option>
	                                            </select>
                                            </div>
                                         </div>
										</div>
										
										<div class="row">
											<div class="col-md-12" id="forBank" style="display: none;">
										        <hr>
										        <table class="table table-bordered table-striped mb-none">
										            <thead>
										                <tr>
										                    <th>Cheque No.</th>
										                    <th>Bank Name</th>
										                    <th>AccountNo</th>
										                    <th>Bank Code</th>											
										                    <th>Bank Location</th>
										                </tr>
										            </thead>
										            <tbody>
										                <tr>											
										                    <td><input type="text" class="form-control" name="chequeNo" id="chequeNo" placeholder="eg.: 123456"></td>
										                    <td>
											                   	<div class="form-group">
																	<select class="col-md-6 form-control" id="bankName" name ="bankName" >
																		<option value="">Select...</option>
																		<c:forEach var="bank" items="${bankList}">
																			<option value="${bank.bankName}">${bank.bankName}</option>
																		</c:forEach>
																	</select> 
																</div>
															</td>
										                    <td><input type="text" class="form-control" name="accountNo" id="accountNo" readonly="readonly"></td>
										                    <td><input type="text" class="form-control" name="bankCode" id="bankCode" readonly="readonly"></td>
										                   	<td><input type="text" class="form-control" name="bankLocation" id="bankLocation" readonly="readonly"></td>
										                </tr>
													</tbody>
										        </table>
										    </div>
										    
											<div class="col-md-12" id="forMoneyTransfer" style="display: none;">
										        <hr>
										        <table class="table table-bordered table-striped mb-none">
										            <thead>
										                <tr>
										                    <th>Bank Name</th>
										                    <th>AccountNo</th>
										                    <th>IFSC Code</th>
										                    <th>Bank Code</th>											
										                    <th>Bank Location</th>
										                </tr>
										            </thead>
										            <tbody>
										                <tr>											
										                    <td><input type="text" class="form-control" name="bankNames" id="bankNames" readonly placeholder="" /></td>
															<td><input type="text" class="form-control" name="vendorAccountNo" id="vendorAccountNo" pattern="^[0-9]\d+$" title="Numbers Only." readonly placeholder="eg.:123456" /></td>
															<td><input type="text" class="form-control" name="vendorBankIfscCode" id="vendorBankIfscCode"  readonly placeholder="" /></td>
															<td><input type="text" class="form-control" name="vendorBankCode" id="vendorBankCode" pattern="^[a-zA-Z0-9]+$" readonly placeholder="eg.: ABC1234" /></td>
															<td><input type="text" class="form-control" name="vendorBankLocation" id="vendorBankLocation" pattern="^[a-zA-Z0-9]+$" readonly placeholder="eg.: Kolkata" /></td>
										                </tr>
													</tbody>
										        </table>
										    </div>
											
										</div>
										
										<div class="row">
										<hr>
										<div class="col-md-12">
												<table class="table table-bordered table-striped mb-none" id="creditTable">
													<thead>
														<tr>
				                                            <th>Credit Account</th>
				                                            <th></th>
				                                            <th>Amount</th>
														</tr>
													</thead>
													<tbody>
														<tr>
				                                            <td>
				                                           		<select name="creditLedger" class="form-control" id="creditLedger0" required>
																	<option value="">Select</option>
																	<c:forEach var="ledger" items="${ledgerList}" >
																		<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
																	</c:forEach>
																</select>
																<input type="hidden" name="creditBank" id="creditBank0" >
				                                           	</td> 
				                                           	<td></td>                                           
															<td>
																<input type="text" name="creditAmount" class="form-control" id="creditAmount0" value = "${transactionalWorkingArea.netAmount}" readonly required>
															</td>                                          
				                                        </tr>
													</tbody>
												</table>
										</div> 
										</div>
										<div class="row">
										<hr>
										<div class="col-md-12">
												<table class="table table-bordered table-striped mb-none" id="debitTable">
													<thead>
														<tr>
				                                            <th>Debit Account</th>
				                                            <th></th>
				                                            <th>Amount</th>
														</tr>
													</thead>
													<tbody>
														<tr>
				                                            <td>
																<select name="debitLedger" class="form-control" id="debitLedger0" required>
																	<option value="">Select</option>
																	<c:forEach var="ledger" items="${ledgerList}" >
																		<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
																	</c:forEach>
																</select>
																<input type="hidden" name="debitBank" id="debitBank0">
				                                           	</td>    
				                                           	<td></td>                                        
															<td>
																<input type="text" name="debitAmount" class="form-control" id="debitAmount0"  value = "${transactionalWorkingArea.netAmount}" readonly required>
															</td>                                          
				                                        </tr>
													</tbody>
												</table>
										</div>                                
                                    </div>
							</div>
							<div style="visibility: collapse;">
								<select name="allLedger" id="allLedger" class="defaultdropdown" onchange="createCheckNumber(this);">
									<option value="">Select</option>
									<c:forEach var="ledger" items="${ledgerList}">
										<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
									</c:forEach>
								</select>
							</div>
							<footer style="display: block;" class="panel-footer">
								<button class="btn btn-primary" type="submit" value="SUBMIT" onclick="return validate();">Submit </button>
								<button type="reset" class="btn btn-default">Reset</button>
							</footer>
						</section>
					</form:form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>	
	</div>		
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/finance/transactionCreate.js"></script>
<script type="text/javascript" src="/cedugenie/js/finance/nullValidation.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>