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
<title>Student List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">
		<h2>Ledger Transaction Details</h2>
	</header>
	<div class="content-padding">
		<div class="col-md-5">
			<form:form name="getLedgerDetailsWithinDateRange" action="getLedgerDetailsWithinDateRange.html" method="GET" > 
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
		                                <c:choose>
											<c:when test="${status ne null}">
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
		                               <c:choose>
											<c:when test="${status ne null}">
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
				<input type="hidden" name="openingBalance" value="${ledgerOpeningBalance}">
				<input type="hidden" name="currentBalance" value="${ledgerCurrentBalance}">
				<input type="hidden" name="ledgerGroup" value="${ledgerGroup}">
				<input type="hidden" name="ledgerCode" value="${ledgerCode}">
			 </form:form>
		</div>
		<div class="col-md-12">
		 <form:form id="LedgerEditForm" name="LedgerEditForm" action="updateLedgerDetails.html" method="POST"> 
		 <input type="hidden" id="saveId" name="saveId">
				<section role="main" class="content-body">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
							</div>
							<h2 class="panel-title">Ledger List</h2>
						</header>
						<div class="panel-body" id="candidateTable">
							<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
								<thead>
									<!-- added by sourav.bhadra on 22-08-2017 --> 
									<tr>
										<th colspan="2">Opening Balance :: </th><th colspan="2">${ledgerOpeningBalance}</th>
										<th colspan="2">Current Balance :: </th>
										<th colspan="2">
											<c:choose>
												<c:when test="${ledgerGroup eq 'ASSETS'}">
													<c:if test="${ledgerCurrentBalance lt 0}">
														${- ledgerCurrentBalance}(Cr)
													</c:if>
													<c:if test="${ledgerCurrentBalance gt 0}"> 
														${ledgerCurrentBalance}(Dr)
													</c:if>
													<c:if test="${ledgerCurrentBalance eq 0}">
														${ledgerCurrentBalance}
													</c:if> 
												</c:when>
												<c:when test="${ledgerGroup eq 'EQUITY AND LIABILITIES'}">
													<c:if test="${ledgerCurrentBalance lt 0}">
														${- ledgerCurrentBalance}(Dr)
													</c:if>
													<c:if test="${ledgerCurrentBalance gt 0}">
													 ${ledgerCurrentBalance}(Cr)
													</c:if>
													
													<c:if test="${ledgerCurrentBalance eq 0}">
														${ledgerCurrentBalance}
													</c:if>
												</c:when>
												<c:otherwise>
													<!-- modified by sourav.bhadra on 28-03-2018 --> 
													<c:if test="${ledgerCurrentBalance lt 0}">
														${- ledgerCurrentBalance}
													</c:if>
													<c:if test="${ledgerCurrentBalance gt 0}">
														${ledgerCurrentBalance}
													</c:if>
													<c:if test="${ledgerCurrentBalance eq 0}">
														${ledgerCurrentBalance}
													</c:if>
										   		</c:otherwise>
											</c:choose>
										</th>
									</tr>
									<tr>
										<th>Ledger Name</th>
										<th>Date</th>
										<th>Credit</th>
										<th>Debit</th>
										<th>Voucher Type</th>
										<th>Voucher Number</th>
										<th>Particulars</th>
										<th>Narration</th>
										<th>Action</th>
										
									</tr>
								</thead>
								<tbody>
									<c:forEach var="transactionWorkingArea" items="${transactionWorkingAreaList}" varStatus="i">
										<tr >
											<td>
												<input type="hidden" name="transactionalWorkingAreaDesc${i.index}" id="transactionalWorkingAreaDesc${i.index}" value="${transactionWorkingArea.transactionalWorkingAreaDesc}" />
												${transactionWorkingArea.transactionalWorkingAreaDesc}
											</td>
											<td>
												<input type="hidden" name="transactionDate${i.index}" id="transactionDate${i.index}" value="${transactionWorkingArea.transactionDate}" />
												${transactionWorkingArea.transactionDate}
											</td>
											<c:choose>
												<c:when test="${transactionWorkingArea.debit eq true}">
													<td>
														 
													</td>
													<td>
														<input type="text" class = "form-control" name="netAmount${i.index}" id="netAmount${i.index}" value="${transactionWorkingArea.netAmount}" readonly/>
													</td>
												</c:when>
												<c:when test="${transactionWorkingArea.debit eq false}">
													<td>
														<input type="text" class = "form-control" name="netAmount${i.index}" id="netAmount${i.index}" value="${transactionWorkingArea.netAmount}" readonly/>
													</td>
													<td>
														 
													</td>
												</c:when>
												<c:otherwise>
													<td>
														 
													</td>
													<td>
														
													</td>
												</c:otherwise>
											</c:choose>
											
											
											<td>
												<input type="hidden" name="transactionalWorkingAreaCode${i.index}" id="transactionalWorkingAreaCode${i.index}" value="${transactionWorkingArea.transactionalWorkingAreaCode}" />
												${transactionWorkingArea.transactionalWorkingAreaCode}
											</td>
											<td>
												<input type="hidden" name="objectId${i.index}" id="objectId${i.index}" value="${transactionWorkingArea.objectId}" />
												${transactionWorkingArea.objectId}
											</td>	
											<td>
												<input type="hidden" name="paidAgainst${i.index}" id="paidAgainst${i.index}" value="${transactionWorkingArea.paidAgainst}" />
												${transactionWorkingArea.paidAgainst}
											</td>
											<td>
												<!-- modified by sourav.bhadra on 20-09-2017 -->
												<textarea class = "form-control" name="transactionalWorkingAreaName${i.index}" id="transactionalWorkingAreaName${i.index}" readonly>${transactionWorkingArea.transactionalWorkingAreaName}</textarea> 
											</td>
											<%-- <td>
												${transactionWorkingArea.transactionalWorkingAreaName}
											</td> --%>	
											<td>
												<!-- modified by sourav.bhadra on 22-08-2017 -->
												<button type="button" id="edit${i.index}" class="btn btn-primary" onClick="functionEditable('${i.index}')">Edit </button>
												<button type="submit" id="update${i.index}" class="btn btn-success" style="display: none">Update</button>
												<!-- added by sourav.bhadra on 22-08-2017 for cancel operation -->
												<button type="button" id="cancel${i.index}" class="btn btn-danger" style="display: none" onClick="cancelEditable('${i.index}');">Cancel</button>
											</td>	
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<footer style="display: block;" class="panel-footer">
							<a href="ledgerCreatePage.html" class="btn btn-primary" style="position: absolute; z-index: 999;">BACK</a>
						</footer>
					</section>
				</section>
			</form:form> 
		</div>	
	</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script>
	function functionEditable(rowId){
		document.getElementById("saveId").value=rowId;
		document.getElementById("netAmount"+rowId).removeAttribute("readonly");
		document.getElementById("transactionalWorkingAreaName"+rowId).removeAttribute("readonly");
		document.getElementById("edit"+rowId).style.display="none";
		document.getElementById("update"+rowId).style.display="block";
		document.getElementById("cancel"+rowId).style.display="block";
	}
	
	/* added by sourav.bhadra on 21-08-2017 for cancel button */
	function cancelEditable(rowId){
		document.getElementById("netAmount"+rowId).setAttribute("readonly", "readonly");
		document.getElementById("transactionalWorkingAreaName"+rowId).setAttribute("readonly", "readonly");
		document.getElementById("edit"+rowId).style.display="block";
		document.getElementById("update"+rowId).style.display="none";
		document.getElementById("cancel"+rowId).style.display="none";
	}

</script>

</body>
</html>