<!-- Added By ranita.sur on 29072017 -->
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
<title>Entry In Day Book</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
   .scroll-to-top{
	   display: none !important;
   }
</style>
</head>
<body>
	<c:choose>
		<c:when test="${message ne null}">		
				<div class="alert alert-danger">
					<span>${message}</span>	
				</div>		
		</c:when>
		<c:otherwise>
		
			<header class="page-header">
				<h2>Daily Entry</h2>
			</header>
			<div class="content-padding">
			<c:if test="${status eq 'Insert Successful'}">
				<div class="alert alert-success">
									<strong>Transaction is Successfully Created!!!</strong>
				</div>
						
			 </c:if>
	        <c:if test="${status eq 'Insert Failed'}">
		       <div class="alert alert-danger">
							<strong>Transaction Failed!!!</strong>
		      </div>
		
	       </c:if>
			<div class="row">
				<div class="col-md-12">
					<form:form name="saveForDayBook" action="saveForDayBook.html" method="POST" > 
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>
	
								<!-- <h2 class="panel-title">Daily Entry</h2> -->										
							</header>
 							
							<div style="display: block;" class="panel-body">
                                    <div class="row">
                                         <div class="col-md-3">
                                         	<c:choose>
                                         		<c:when test="${transactionalWorkingArea.resource ne null}">
                                         			<div class="form-group">
		                                                 <label class="control-label">Resource Name</label>
		                                                 <input type = "text" name="vendorName" id="vendorName" class="form-control" value="${transactionalWorkingArea.resource}" readonly="readonly">                                            
		                                            </div>
                                         		</c:when>
                                         	</c:choose>
											<div class="form-group">
                                                 <label class="control-label">Voucher Type</label>
                                                 <select name="voucherTypeCode" id="voucherTypeCode" class="form-control" onchange="checkValues();" required>
                                                 <!-- modified by ranita.sur on 25082017 for petty cash entry -->
                                                   <option value="0">Select</option>
													<c:if test="${voucherTypeList ne null}">
														<c:forEach var="vt" items="${voucherTypeList}">
															<option value="${vt.voucherTypeCode}">${vt.voucherTypeName}</option>
														</c:forEach>
													</c:if>		
												</select>                                                 
                                             </div>
                                             <div class="form-group" id="ticketNoDiv" style="display:none;">
                                                 <label class="control-label">Ticket No.</label>
                                                 <c:choose>
                                                 	<c:when test="${transactionalWorkingArea.codeId ne null}">
                                                 		<input type="text" class="form-control" name="ticketNo" id="ticketNo" value="${transactionalWorkingArea.codeId}" readonly="readonly"/>
                                                 	</c:when>
                                                 	<c:otherwise>
                                                 		<select name="ticketNo" id="ticketNo" class="form-control" onchange="" >
		                                                 	<option value="0">Select</option>
															<c:if test="${ticketList ne null}">
																<c:forEach var="ticket" items="${ticketList}">
																	<option value="${ticket.ticketCode}">${ticket.ticketCode}</option>
																</c:forEach>
															</c:if>
														</select>
                                                 	</c:otherwise>
                                                 </c:choose>
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
                                                 <label class="control-label">Payment Mode</label>
                                                 <!-- modified by ranita.sur on 25082017 for petty cash entry -->
                                              	 <select name="paymentMode" id="paymentMode" class="form-control" onchange="getval(this);" >
													<option value="">Select</option>
													<option value="CASH">Cash</option>
													<option value="BANK">Bank</option>
													 <!-- added by sourav.bhadra on 22-08-2017 -->
													<option value="PETTY_CASH">Petty Cash</option>
												</select>
											</div>
                                         </div>
                                          <div class="col-md-3"> 
                                         	 <div class="form-group"  id="departmentDiv" style="display:none;">
                                             	<label class="control-label">Department</label>
                                              		<c:choose>
                                              			<c:when test="${transactionalWorkingArea.department ne null}">
                                              				<input type = "text" name="departmentName" id="departmentName" class="form-control" value = "${transactionalWorkingArea.department}" readonly >
                                            				<input type = "hidden" name="transactionCode" id="transactionCode" class="form-control" value = "${transactionalWorkingArea.transactionalWorkingAreaCode}"  >
                                              			</c:when>
                                              			<c:otherwise>
                                              				<select class="form-control" name="departmentName" id="departmentName0">
			                                            		<option value="">Select...</option>
			                                           			 <c:forEach var="department" items="${departmentList}">
																	<option value="${department.departmentCode}">${department.departmentName}</option>
																</c:forEach>
															</select>
                                              			</c:otherwise>
                                              		</c:choose>
	                                          	</div>                                          
                                             <div class="form-group">
                                                 <label class="control-label">Narration</label>
                                                 <textarea name="narration" id="narration" rows="4" data-plugin-maxlength="" maxlength="140" class="form-control"></textarea>
                                             </div>                                             
                                         </div>
                                         <div class="col-md-3">
                                         	<div class="form-group" id="incExpDiv" style="display:none;">
	                                            <label class="control-label"><b>Inc/Exp</b></label>
	                                            <c:choose>
                                              			<c:when test="${transactionalWorkingArea.incomeExpense ne null}">
                                              				<input type="text"  class="form-control" name="incomeExpense" value = "${transactionalWorkingArea.incomeExpense}" readonly="readonly">
                                              			</c:when>
                                              			<c:otherwise>
                                              				<select id="incomeExpense" name="incomeExpense" class="form-control" >
																<!--   changesForOptionSelection by ranita.sur on 16082017 -->
				                                            	<option>Select...</option>
				                                            	<option value="INCOME">INCOME</option>
				                                            	<option value="EXPENSE">EXPENSE</option>
				                                            </select>
                                              			</c:otherwise>
                                              		</c:choose>
											</div>
										</div>
										</div>
										<table class="table table-bordered table-striped mb-none" id="forBank" style="display: none;">
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
										        <table class="table table-bordered table-striped mb-none" id="forBankReciept" style="display: none;">
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
										                    <td><input type="text" class="form-control" name="chequeNos" id="chequeNos" placeholder="eg.: 123456"></td>
										                    <td>
										                      <input type="text" class="form-control" name="bankNames" id="bankNames" placeholder="eg.: SBI">
											                   
															</td>
										                    <td><input type="text" class="form-control" name="accountNos" id="accountNos" placeholder="eg.: 123456"></td>
										                    <td><input type="text" class="form-control" name="bankCodes" id="bankCodes" placeholder="eg.: 1234" ></td>
										                   	<td><input type="text" class="form-control" name="bankLocations" id="bankLocations" placeholder="eg.: Kolkata"></td>
										                </tr>
													</tbody>
										        </table>
										
										    
											<!-- modified by ranita.sur on 25082017 for petty cash entry -->
										
							
										
										<hr>
										
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
				                                           		<select name="creditLedger" class="form-control" id="creditLedger0" onchange="createCheckNumber(this);" required>
																	<option value="">Select</option>
																	<c:forEach var="ledger" items="${ledgerList}" >
																		<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
																	</c:forEach>
																</select>
																<input type="hidden" name="creditBank" id="creditBank0" >
				                                           	</td> 
				                                           	<td></td>                                           
															<td>
																<c:choose>
			                                              			<c:when test="${transactionalWorkingArea.netAmount ne null}">
			                                              				<input type="text" name="creditAmount" class="form-control" id="creditAmount0" value = "${transactionalWorkingArea.netAmount}"  required>
			                                              			</c:when>
			                                              			<c:otherwise>
			                                              				<input type="text" name="creditAmount" class="form-control" id="creditAmount0"  required>
			                                              			</c:otherwise>
			                                              		</c:choose>
																
															</td>                                          
				                                        </tr>
													</tbody>
												</table>
				
										
										
										<hr>
									
												<table class="table table-bordered table-striped mb-none" id="debitTable">
													<thead>
														<tr>
				                                            <th>Debit Account</th>
				                                            <th></th>
				                                            <th>Amount</th>
				                                            <th>Action</th>
														</tr>
													</thead>
													<tbody>
														<tr>
				                                            <td>
																<select name="debitLedger" class="form-control" id="debitLedger0" onchange="createCheckNumber(this);" required>
																	<option value="">Select</option>
																	<c:forEach var="ledger" items="${ledgerList}" >
																		<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
																	</c:forEach>
																</select>
																<input type="hidden" name="debitBank" id="debitBank0">
				                                           	</td>    
				                                           	<td></td>                                        
															<td>
																<c:choose>
			                                              			<c:when test="${transactionalWorkingArea.netAmount ne null}">
			                                              				<input type="text" name="debitAmount" class="form-control" id="debitAmount0"  value = "${transactionalWorkingArea.netAmount}" required>
			                                              			</c:when>
			                                              			<c:otherwise>
			                                              				<input type="text" name="debitAmount" class="form-control" id="debitAmount0" required>
			                                              			</c:otherwise>
			                                              		</c:choose>
															</td>
															<td  id="debitAdd" style="display:none;">
																<button class="btn btn-primary" onclick="addDebit();">+</button>
															</td>                                         
				                                        </tr>
													</tbody>
												</table>
									
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
								<button class="btn btn-primary" type="submit" value="SUBMIT">Submit </button>
								<button type="reset" class="btn btn-default">Reset</button>
							</footer>
						</section>
					</form:form> 
				</div>
			</div>
			</div>
		</c:otherwise>
	</c:choose>			
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/finance/entryForDayBook.js"></script>
<script type="text/javascript" src="/cedugenie/js/finance/nullValidation.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>