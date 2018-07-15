<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/viewRequisitionListPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Book Requisition List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

			<header class="page-header">
				<h2>View Requisition</h2>
			</header>
			<div class="content-padding">
				<div class="row">
					<c:choose>
							<c:when test="${bookRequisitionList eq null}">
								<div class="btnsarea01" >
									<div class="errorbox" id="errorbox" style="visibility: visible;">
										<span id="errormsg">No Book Requisition Found</span>
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
								
										<h2 class="panel-title">Book Requisition List</h2>
									</header>
									<div class="panel-body">
										<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
											<thead>
												<tr>
													<th>Requisition ID</th>
													<th>Open Date</th>
													<th>Status</th>
													<th>Receive</th>
													<th>Create Purchase Order</th>	
												</tr>
											</thead>
											<tbody>
												<c:forEach var="bookRequisitionList" items="${bookRequisitionList}"> 
													<tr class="gradeC">
														<td>
															${bookRequisitionList.bookRequisitionCode}					
														</td>
														<td>
															${bookRequisitionList.bookRequisitionOpenDate}
														</td>
														<td>
															${bookRequisitionList.bookRequisitionStatus}
														</td>
													<c:choose>
														<c:when test="${bookRequisitionList.purchaseOrderCreated eq true}">
															<td>
																<a href="listPurchaseOrder.html?requisitionCode=${bookRequisitionList.bookRequisitionCode}">List Purchase Order</a>													</td>
															<td>
																Purchase Order Created
															</td>
														</c:when>
														<c:otherwise>
															<td>
																Purchase Order Not Created
															</td>
															<td>
																<a href="createPurchaseOrderForBookRequisition.html?requisitionCode=${bookRequisitionList.bookRequisitionCode}&requisitionStatus=${bookRequisitionList.bookRequisitionStatus}">Create Purchase Order</a>
															</td>
														</c:otherwise>
													</c:choose>
												</tr>
											</c:forEach>
										</table>
									</div>
								</section>			
							</section>
						</c:otherwise>
					</c:choose>
				</div>	
			</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/library/requisitionList.js"></script>
</body>
</html>