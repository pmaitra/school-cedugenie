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
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/finance/transactionCreate.js"></script>
<script type="text/javascript" src="/icam/js/finance/nullValidation.js"></script>


<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">
		<h2>Transaction Details</h2>
	</header>
	<div class="content-padding">
	<c:choose>
			<c:when test="${transactionalWorkingArea eq null}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<span id="errormsg">Transaction Working Area Details Not Found</span>	
					</div>
				</div>
			</c:when>
		<c:otherwise>
			<div class="row">
				<div class="col-md-12">
					<form:form id="editGroup" name="editGroup" action="editGroup.html" method="POST">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>
	
								<h2 class="panel-title">Transaction Details</h2>										
							</header>
							<div style="display: block;" class="panel-body">
                                    <div class="row">
                                         <div class="col-md-3">
                                         
											<div class="form-group">
		                                        <label class="control-label">Department</label>		                                        
		                                        <input type="text" value="${transactionalWorkingArea.department}" readonly class="form-control">                                    </label>
	                                    	</div>                                             
                                             <div class="form-group">
                                                 <label class="control-label">Mode</label>
                                                <input type="text" value="${transactionalWorkingArea.incomeExpense}" readonly class="form-control">                                                
                                             </div> 
                                             <div class="form-group">
                                                 <label class="control-label">Type</label>
                                                 <input type="text" value="${transactionalWorkingArea.reasonOfTransaction}" readonly class="form-control">
                                             </div>
                                             <div class="form-group">
                                                 <label class="control-label">Sub-Type</label>                                                
                                                 <input type="text" value="${transactionalWorkingArea.transactionalWorkingAreaName}" readonly class="form-control">
                                             </div>   
                                              <div class="form-group">
                                                 <label class="control-label">Resource Type</label>
												<input type="text" value="${transactionalWorkingArea.resourceType}" readonly class="form-control">
											</div>
                                             <div class="form-group">
                                                 <label class="control-label">Status</label>                                                
                                                 <input type="text" value="${transactionalWorkingArea.transactionStatus}" readonly class="form-control">
                                             </div>    
                                         </div>
                                         <div class="col-md-3">
                                         	<div class="form-group">
		                                        <label class="control-label">Name</label>		                                        
		                                        <input type="text" value="${transactionalWorkingArea.resource}" readonly class="form-control">                                    </label>
	                                    	</div>                                             
                                              <div class="form-group">
	                                       <label class="col-md-7 control-label">Payment Mode</label>
	                                       <div class="col-md-7">
		                                       <select class="col-md-6 form-control" id="transactionMode" onchange="showBankDetails(this);" name="modeOfPayment">
		                                           <option value="">Select...</option>
		                                           <option value="CASH">Cash</option>
												   <option value="BANK">Bank</option>
												   <!-- added by sourav.bhadra on 22-08-2017 -->
												   <option value="PETTY_CASH">Petty Cash</option>
		                                       </select> 
	                                       </div>
	                                   </div>
                                             <div class="form-group">
                                                 <label class="control-label">Bank Name</label>
                                                 <input type="text" value="${transactionalWorkingArea.bankName}" id="bankName"   disabled class="form-control">
                                             </div>
                                             <div class="form-group">
                                                 <label class="control-label">Cheque No.</label>                                                
                                                 <input type="text" value="${transactionalWorkingArea.chequeNo}"   id="chequeNo"   disabled class="form-control">
                                             </div>   
                                             <div class="form-group">
                                                 <label class="control-label">Date</label>
												<input type="text" value="${transactionalWorkingArea.transactionDate}" readonly class="form-control" >
											</div>
                                         	<div class="form-group">
                                                 <label class="control-label">Ticket No.</label>
													<input type="text" value="${transactionalWorkingArea.codeId}" readonly class="form-control" >
												</div>
                                        </div>                           
                                         <div class="col-md-3">   
                                         	   	<div class="form-group">
		                                        <label class="control-label">Year</label>		                                        
		                                        <input type="text" value="${transactionalWorkingArea.transactionYear}" readonly class="form-control">                                    </label>
	                                    	</div>                                             
                                             <div class="form-group">
                                                 <label class="control-label">Month</label>
                                                <input type="text" value="${transactionalWorkingArea.transactionMonth}" readonly class="form-control">                                                
                                             </div> 
                                             <div class="form-group">
                                                 <label class="control-label">Gross Amount</label>
                                                 <input type="text" value="${transactionalWorkingArea.grossAmount}" readonly class="form-control">
                                             </div>
                                             <div class="form-group">
                                                 <label class="control-label">Net Amount</label>                                                
                                                 <input type="text" value="${transactionalWorkingArea.netAmount}" readonly class="form-control">
                                             </div>   
                                              <div class="form-group">
                                                 <label class="control-label">Amount</label>
                                                 <c:choose>
													<c:when test="${transactionalWorkingArea.bankAmount eq '0.0'}">
														<input type="text" value="${transactionalWorkingArea.cashAmount}" readonly class="form-control">
													</c:when>
													<c:otherwise>
														<input type="text" value="${transactionalWorkingArea.bankAmount}" readonly class="form-control">
													</c:otherwise>
												</c:choose>
											</div>
                                             <div class="form-group">
                                                 <label class="control-label">Academic Year</label>
                                                 <input type="text" value="${transactionalWorkingArea.academicYear}" readonly class="form-control">
                                             </div>                                                                              
                                         </div>
									</div>	
									<br>	
									<div class="row">							
									<div class="col-md-12">
										<table class="table table-bordered table-striped mb-none" id="debitTable">
											<thead>
												<tr>
		                                           <th>Heads</th>
													<th>Amount</th>														
												</tr>
											</thead>
											<tbody>
												<c:forEach var="twad" items="${transactionalWorkingArea.trWorkingDetList}" varStatus="i">
													<tr>
														<td>
															${twad.transactionHeadName}
														</td>
														<td>
															${twad.transactionHeadAmount}
														</td>
													</tr>
												</c:forEach>														
											</tbody>
										</table>
									</div>  
									</div>   																			
								</div>							
							<footer style="display: block;" class="panel-footer">
								<%-- <c:if test="${transactionalWorkingArea.transactionStatus eq 'PENDING'}">								
									<a href="transactionWorkingAreaSanction.html?twaCode=${transactionalWorkingArea.transactionalWorkingAreaCode}">
										<input type="button" class="btn btn-primary" value="Approve">
									</a>
								</c:if>	 --%>
								<button type="button" class="mb-xs mt-xs mr-xs btn btn-warning" onclick="window.location='transactionWorkingAreaList.html'">Back</button>																				
							</footer>
						</section>
					</form:form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>	
	</div>		
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>