<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listBookPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Ledger List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<c:choose>
	<c:when test="${brsList eq null}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">BRS Not Found</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<section role="main" class="content-body">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
							<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
						</div>
				
						<h2 class="panel-title">BRS For ${bank} From ${from} To ${to} </h2>
					</header>
					<div class="panel-body">
						<table class="table table-bordered table-striped mb-none" id="groupList" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
							<thead>
								<tr>
									<th>Cheque Number</th>
									<th>Date</th>
									<th>Narration</th>
									<th>Voucher Number</th>
									<th>Voucher Type</th>
									<th>Debit Amount</th>
									<th>Credit Amount</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="brs" items="${brsList}" varStatus="i">
								<c:if test="${brs.transactionPassbook eq 'TRANSACTION'}">
									<tr>
										<td>${brs.chequeNumber}</td>
										<td>${brs.date}</td>
										<td>${brs.narration}</td>
										<td>${brs.voucherNumber}</td>
										<td>${brs.voucherType}</td>
										<c:choose>
											<c:when test="${brs.debit eq true}">
												<td>${brs.amount}</td><td>--</td>
											</c:when>
											<c:otherwise>
												<td>--</td><td>${brs.amount}</td>
											</c:otherwise>
										</c:choose>
									</tr>
								</c:if>
							</c:forEach>										
							</tbody>
						</table>
						<table>
							<tr>
								<th>Balance As Per Company's Book :: ${companyBook}</th>
							</tr>
							<tr>
								<th>Amount not reflected in bank :: ${notInBank}</th>
							</tr>
							<tr>
								<th>Amount not reflected in company's book :: ${notInComp}</th>
							</tr>
						</table>
					</div>
				</section>			
		</section>		
	</c:otherwise>
</c:choose>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>