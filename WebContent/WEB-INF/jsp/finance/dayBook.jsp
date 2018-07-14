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
<title>Day Book</title>
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
		<h2>Day Book</h2>
	</header>
	<div class="content-padding">
		<div class="row">
			<div class="col-md-4">
				<form:form name="getDayBook" action="getDayBook.html" method="GET" > 
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Date Details</h2>										
					</header>
					<div style="display: block;" class="panel-body">
						<div class="row">
							<div class="col-md-12">
		                  		<div class="form-group">
		                    		<label class="control-label">From Date: </label>
	                        		<div class="input-group">
		                            	<span class="input-group-addon">
	                     					<i class="fa fa-calendar"></i>
		                                </span>
		                                <!-- modified by sourav.bhadra on 10-08-2017
										to display fromDate after search -->
		                                <c:choose>
											<c:when test="${dayBookListList ne null}">
												<input type="text" class="form-control" name="fromDate" id="fromDate" value="${fromDate}" data-plugin-datepicker=""/>
											</c:when>
											<c:otherwise>
												<input type="text" class="form-control" name="fromDate" id="fromDate" data-plugin-datepicker=""/>
											</c:otherwise>
										</c:choose>
									</div>                            
		                       	</div>   
		               			<div class="form-group">
		                           <label class="control-label">To Date: </label>
		                           <div class="input-group">
		                               <span class="input-group-addon">
		                                   <i class="fa fa-calendar"></i>
		                               </span>
		                               <!-- modified by sourav.bhadra on 10-08-2017
										to display toDate after search -->
										<c:choose>
											<c:when test="${dayBookListList ne null}">
												<input type="text" class="form-control" name="toDate" id="toDate" value="${toDate}" data-plugin-datepicker=""/>
											</c:when>
											<c:otherwise>
												<input type="text" class="form-control" name="toDate" id="toDate" data-plugin-datepicker=""/>
											</c:otherwise>
										</c:choose>
										
		                           </div>
		                      	</div>
		                    </div>
	                 	</div>
					</div>
					<footer style="display: block;" class="panel-footer">
						<button type="submit"  class="btn btn-primary">Get Details</button>
					</footer>
				</section>
			 </form:form>
			</div>
			<!-- Added By ranita.sur on 08/08/2017 -->
			<c:if test="${dayBookListList ne null}">
				<div class="col-md-12">
							<form name="editDaybookDetails" id="editDaybookDetails" action="editDaybookDetails.html" method="POST"> 
							<input type="hidden" name="saveId" id="saveId">
							<input type="hidden" name="getNewDate" id="getNewDate">
							<input type="hidden" name="getDebitStatus" id="getDebitStatus">
							<input type="hidden" name="getNewLedgerName" id="getNewLedgerName">
							<input type="hidden" name="getNewVoucherType" id="getNewVoucherType">
							<input type="hidden" name="getNewVoucherNo" id="getNewVoucherNo">
							<input type="hidden" name="getNewNarration" id="getNewNarration">
							<input type="hidden" name="getNewCredit" id="getNewCredit">
							<input type="hidden" name="getNewDebit" id="getNewDebit">
							<!-- added b y sourav.bhadra on 10-08-2017 to store fromDate & toDate -->
							<c:if test="${dayBookListList ne null}">
								<input type="hidden" name="newFromDate" value="${fromDate}">
								<input type="hidden" name="newToDate" value="${toDate}">
							</c:if>
							<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
								<h2 class="panel-title">DayBookList</h2>
							</header>
							<div class="panel-body">
							<div class="row">
								<div class="col-md-12 table-responsive">				
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
									<thead>
										<tr>
											<th>Date</th>
                                            <th>Particulars</th>
											<th>Voucher Type</th>
                                            <th>Voucher No</th>
                                            <th>Ticket No</th>
                                            <th>Narration</th>
                                            <th>Debit</th>
                                            <th>Credit</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="dayBook" items="${dayBookListList}" varStatus="i">
										<tr>	
																				
											<td>
											
										<input type="hidden" class="form-control" id="transactionId${i.index}" name="transactionId${i.index}" value="${dayBook.transactionSerialId}" />
										
												<input type="hidden" class="form-control" id="date${i.index}" name="date${i.index}" value="${dayBook.date}" />
												${dayBook.date}
											</td>
											<td>
												<input type="hidden" class="form-control" id="ledgerCode${i.index}" name="ledgerCode${i.index}" value="${dayBook.ledgerCode}" />
												${dayBook.ledgerCode} 
												</td>
												<td>	
												<input type="hidden" class="form-control" id="voucherType${i.index}" name="voucherType${i.index}" value="${dayBook.voucherType}" />
												${dayBook.voucherType}
											</td>
											<td>
												<input type="hidden" class="form-control" id="voucherNumber${i.index}" name="voucherNumber${i.index}" value="${dayBook.voucherNumber}" />
												${dayBook.voucherNumber}
												
											</td>
											<td>
												<input type="hidden" class="form-control" id="ticketNo${i.index}" name="ticketNo${i.index}" value="${dayBook.voucherNumber}" />
												${dayBook.updatedBy}
											</td>
											<td>	
												<input type="hidden" class="form-control" id="narration${i.index}" name="narration${i.index}" value="${dayBook.narration}" />
												${dayBook.narration}
											</td>
											 <c:choose>
											 
								         <c:when test="${dayBook.debit eq true}">
											<td>
												<input type="hidden" class="form-control" id="amount${i.index}" name="amount${i.index}" value="${dayBook.amount}" />
												${dayBook.amount}
											</td>
											<td>
												
											</td>			
								          </c:when>
								          
											<c:otherwise>
											
											<td>
												
											</td>
											<td>
												<input type="hidden" class="form-control" id="amount${i.index}" name="amount${i.index}" value="${dayBook.amount}" />
												${dayBook.amount}
											</td>
											</c:otherwise>
											</c:choose>
											<td class="actions">
												<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic " onclick = "showdayBookDetails('${dayBook.date}','${dayBook.ledgerCode} ','${dayBook.voucherType}','${dayBook.voucherNumber}','${dayBook.narration}','${dayBook.amount}','${dayBook.debit}','${i.index}')"><i class="fa fa-pencil"></i></a>
											</td>
										</tr>
									</c:forEach>
									<tr>
									<td ></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td><strong>Total</strong></td>
									<td>${debit}</td>
									<td>${credit}</td>
										<td></td>
									</tr>
								</tbody>
							</table>
							</div>
							</div>
						</div>
						<!-- popup Window code -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide" style="max-width: 900px;">
									<section class="panel">
										<header class="panel-heading">
											 <h2 class="panel-title">DayBook Details</h2> 
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id = "daybookDetails">
												<thead>
													<tr>
															<th>Date</th>
				                                            <th>Particulars</th>
															<th>Voucher Type</th>
				                                            <th>Voucher No</th>
				                                            <th>Narration</th>
				                                            <th>Debit</th>
				                                            <th>Credit</th>
															
													</tr>
												</thead>
												<tbody>
								
												</tbody>
											</table>
											<div class="alert alert-warning" id="warningmsg1" style="display: none">
												<span></span>	
											</div>
										</div>
										
										<footer class="panel-footer">
											<div class="row">
												<div class="col-md-12 text-right">
													<button id="updateDaybook" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
					</section>
					 </form>
				</div>
			</c:if>	
		</div>
	</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
 <script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script>
<script type="text/javascript" src="/icam/js/finance/dayBook.js"></script>
<script type="text/javascript" src="/icam/js/finance/nullValidation.js"></script>
</body>
</html>