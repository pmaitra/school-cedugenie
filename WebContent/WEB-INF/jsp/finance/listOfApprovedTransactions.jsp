<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Transaction List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">
		<h2>Sanctioned Transactions List</h2>
	</header>
	<c:if test="${status eq 'Update Success'}">
		<div class="alert alert-success"  >
			<strong>Transaction Is Sanctioned</strong>	
		</div>
	</c:if>
	<c:if test="${status eq 'Update Failed'}">
		<div class="alert alert-danger"  >
			<strong>Failed To Sanction Transaction</strong>	
		</div>
	</c:if>
	<c:if test="${status eq 'noBudget'}">
		<div class="alert alert-danger"  >
			<strong>Required Budget is Not Availble For Sanction Of Transaction</strong>	
		</div>
	</c:if>
	<c:if test="${status eq 'moreThanBudget'}">
		<div class="alert alert-danger"  >
			<strong>Budget is Not Available</strong>	
		</div>
	</c:if>
	<c:if test="${status1 eq 'Update Successful'}">
		<c:if test="${status2 eq 'Notification'}">
			<div class="alert alert-danger"  >
				<strong>Budget is Low</strong>	
			</div>
		</c:if>
	</c:if>
	<c:if test="${status1 eq 'Update Successful'}">
		<c:if test="${status2 eq 'Notification Failed'}">
			<div class="alert alert-success"  >
				<strong>Successfully Updated</strong>	
			</div>
		</c:if>
	</c:if>
	<c:choose>
		<c:when test="${transactionalWorkingAreaListOfApprovedTransactions == null}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">Sanctioned Transaction Working Area List Not Found</span>
			</div>
		</c:when>
		<c:otherwise>
		<form:form id="updateTransactionWorkingArea" name="updateTransactionWorkingArea" action="updateTransactionWorkingArea.html" method="POST">	
			<section role="main" class="content-body">		
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>						
								<h2 class="panel-title">Sanctioned Transactions List</h2>
							</header>
							<div class="panel-body">
							<input class = "form-control" type ="hidden" value = "" name = "saveId" id = "saveId" readonly>
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Department</th>
											<th>Mode</th>
											<!-- <th>Type</th>
											<th>Sub-Type</th>
											<th>Resource Type</th> -->
											<th>Name</th>
											<!-- <th>Pay Mode</th>
											<th>Bank Name</th>
											<th>Cheque No</th> -->
											<th>Date</th>
											<!-- <th>Year</th>
											<th>Month</th>
											<th>Gross Amt</th> -->
											<th>Net Amt</th>
											<!-- <th>Amount</th> -->
											<th>Financial Year</th>
											<th>Ticket No.</th>
											<th>Status</th>
											<!-- <th>Ledger Name</th> -->
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="twa" items="${transactionalWorkingAreaListOfApprovedTransactions}" varStatus="i">
											<tr class="gradeC">
												<td>
													${twa.department}
												</td>
												<td>
													${twa.incomeExpense}
												</td>				
												<%-- <td>
													${twa.reasonOfTransaction}
												</td>
												<td>
													${twa.transactionalWorkingAreaName}
												</td>
												<td>
													${twa.resourceType}
												</td> --%>
												<td>
													${twa.resource}
												</td>
												<%-- <td>
													${twa.transactionMode}
												</td>
												<td>
													${twa.bankName}
												</td>
												<td>
													${twa.chequeNo}
												</td> --%>
												<td>
													${twa.transactionDate}
												</td>
												<%-- <td>
													${twa.transactionYear}
												</td>
												<td>
													${twa.transactionMonth}
												</td>
												<td>
													${twa.grossAmount}
												</td> --%>
												<td>
													${twa.netAmount}
												</td>
												<%-- <td>
													<c:choose>
														<c:when test="${twa.bankAmount eq '0.0'}">
															${twa.cashAmount}
														</c:when>
														<c:otherwise>
															${twa.bankAmount}
														</c:otherwise>
													</c:choose>								
												</td> --%>
												<td>
													${twa.academicYear}
												</td>
												<td>
													${twa.codeId}
												</td>
												<td>
													${twa.transactionStatus}
												</td>
												<%-- <td>
													<input class = "form-control" type ="text" value = "${twa.objectId}" name = "ledgerCode${i.index}" id = "ledgerCode${i.index}" readonly>
													<input type="hidden" value=${twa.paidAgainst}">
													
													
													<select name="selectledgerCode${i.index}" id="selectledgerCode${i.index}" class="form-control" style="display:none;">
                                        				<option value="">Select</option>
													</select>
													<input class = "form-control" type ="hidden" value = "${i.index}" name = "rowId" id = "rowId" readonly>
													<input type ="hidden" value = "${twa.transactionalWorkingAreaCode}" name = "transactionCode${i.index}" id = "transactionCode${i.index}" readonly>
												</td> --%>
												<td>
													<c:if test="${twa.transactionStatus eq 'SANCTIONED'}">
															<a href="transactionWorkingAreaDetails.html?twaCode=${twa.transactionalWorkingAreaCode}">
																<input type="button" class="clearbtn btn-primary btn-xs" value="Details">
															</a>
															
															<c:if test="${twa.incomeExpense eq 'INCOME'}">
																<a href="createTransactionPage.html?twaCode=${twa.transactionalWorkingAreaCode}" style="float: right;">
																	<input type="button" class="btn btn-success btn-xs" value="Receive">
																</a>
															</c:if>
															<c:if test="${twa.incomeExpense eq 'OTHER EXPENSE' || twa.incomeExpense eq 'EXPENSE'}">
																<a href="createTransactionPage.html?twaCode=${twa.transactionalWorkingAreaCode}" style="float: right;">
																	<input type="button" class="btn btn-warning btn-xs" value="Payment">
																</a>
															</c:if>
														</c:if>
																						
												</td>
											</tr>										
										</c:forEach>										
									</tbody>									
								</table>
							</div>
						</section>			
					</section>			
		</form:form>
	</c:otherwise>
</c:choose>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/finance/transactionWorkingArea.js"></script>
</body>
</html>