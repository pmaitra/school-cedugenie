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
<title>Cash Book</title>
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
		<h2>Cash Book</h2>
	</header>
	<div class="content-padding">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
		<form:form name="getCashBook" action="getCashBook.html" method="GET" > 
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Cash Book</h2>										
					</header>
					<div style="display: block;" class="panel-body">
					<div class="row">
					 <div class="col-md-12">
                  		<div class="form-group">
                            <label class="control-label">FROM</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </span>
                                <input type="text" class="form-control" name="fromDate" id="fromDate" data-plugin-datepicker=""/>
                            </div>                            
                       	</div>   
               			<div class="form-group">
                           <label class="control-label">TO</label>
                           <div class="input-group">
                               <span class="input-group-addon">
                                   <i class="fa fa-calendar"></i>
                               </span>
                               <input type="text" class="form-control" name="toDate" id="toDate" data-plugin-datepicker=""/>
                           </div>
                      	</div>  
                      	</div>
                      </div>	                                    	
					</div>
					<footer style="display: block;" class="panel-footer">				
						<button type="submit"  class="btn btn-primary">Get</button>
					</footer>
				</section>
			</form:form>
		</div>	
		
			<c:if test="${cbList ne null}">
			<div class="col-md-12">
							<input type="hidden" name="saveId" id="saveId">
							<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
								<h2 class="panel-title">CashBookList</h2>
							</header>
							<div class="panel-body">
							<div class="row">
								<div class="col-md-12 table-responsive">				
								<table class="table table-bordered table-striped mb-none" id="datatable-editable">
									<thead>
									<tr>
										<th colspan="2">Opening Balance::</th>
										<th colspan="2">${openingbalance}</th>
										<th colspan="2">Closing Balance::</th>
										<th colspan="2">
											<c:choose>
												<c:when test="${closingbalance lt 0}">
													${- closingbalance}(Cr)
												</c:when>
												<c:when test="${closingbalance gt 0}">
													${closingbalance}(Dr)
												</c:when>
												<c:otherwise>
													${closingbalance}
										   		</c:otherwise>
											</c:choose>
										</th>
                                    </tr>
										<tr>
											<th>Date</th>
                                            <th>LedgerName</th>
											<th>Voucher Type</th>
                                            <th>Voucher No</th>
                                            <th>Particulars</th>
                                            <th>Narration</th>
                                            <th>Debit</th>
                                            <th>Credit</th>
											
										</tr>
									</thead>
									<tbody>
									
									<c:forEach var="transactionalWorkingArea" items="${cbList}" varStatus="i">
									<tr>	
											<td>
												<input type="hidden" class="form-control" id="transactionDate${i.index}" name="transactionDate${i.index}" value="${transactionalWorkingArea.transactionDate}" />
												${transactionalWorkingArea.transactionDate}
											</td>
											<td>
												<input type="hidden" class="form-control" id="transactionalWorkingAreaDesc${i.index}" name="transactionalWorkingAreaDesc${i.index}" value="${transactionalWorkingArea.transactionalWorkingAreaDesc}" />
												${transactionalWorkingArea.transactionalWorkingAreaDesc} 
												</td>
												<td>	
												<input type="hidden" class="form-control" id="transactionalWorkingAreaCode${i.index}" name="transactionalWorkingAreaCode${i.index}" value="${cashBook.transactionalWorkingAreaCode}" />
												${transactionalWorkingArea.transactionalWorkingAreaCode}
											</td>
											<td>
												<input type="hidden" class="form-control" id="objectId${i.index}" name="objectId${i.index}" value="${cashBook.objectId}" />
												${transactionalWorkingArea.objectId}
												
											</td>
											<td>	
												<input type="hidden" name="paidAgainst${i.index}" id="paidAgainst${i.index}" value="${transactionalWorkingArea.paidAgainst}" />
											${transactionalWorkingArea.paidAgainst}
											</td>
											<td>	
												<input type="hidden" class="form-control" id="transactionalWorkingAreaName${i.index}" name="transactionalWorkingAreaName${i.index}" value="${cashBook.transactionalWorkingAreaName}" />
												${transactionalWorkingArea.transactionalWorkingAreaName}
											</td>
											 <c:choose>
											 
								         <c:when test="${transactionalWorkingArea.debit eq true}">
											<td>
												<input type="hidden" class="form-control" id="netAmount${i.index}" name="netAmount${i.index}" value="${cashBook.netAmount}" />
												${transactionalWorkingArea.netAmount}
											</td>
											<td>
												
											</td>			
								          </c:when>
								          
											<c:otherwise>
											
											<td>
												
											</td>
											<td>
												<input type="hidden" class="form-control" id="netAmount${i.index}" name="netAmount${i.index}" value="${cashBook.netAmount}" />
												${transactionalWorkingArea.netAmount}
											</td>
											</c:otherwise>
											</c:choose>
											
										</tr>
									</c:forEach>
									
								</tbody>
							</table>
							</div>
							</div>
							</section>
						</div>	
						</c:if>				
		          </div>
		        </div>  
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/finance/cashBook.js"></script>
<script type="text/javascript" src="/icam/js/finance/nullValidation.js"></script>
</body>
</html>