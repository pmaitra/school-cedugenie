<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Mess Demand Voucher List</title>
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
		<h2>Indent Sheet List</h2>
	</header>
	<div class="content-padding">
		<div class="row">
			<c:choose>
				<c:when test="${messDemandVoucherList eq null || empty messDemandVoucherList}">
					<div class="alert alert-danger">
						<strong>No Indent Sheet Found.</strong>
					</div>
				</c:when>
				<c:otherwise>
					<div class="col-md-12">
						<section class="panel">
							<header class="panel-heading">
							    <h2 class="panel-title">Indent Sheet List</h2>
							</header>
	                        <div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
									<thead>
										<tr>
											<th>Indent Sheet Id</th>
											<th>Demand Created By</th>
											<th>Demand Open Date</th>
											<th>Demand Close Date</th>
											<th>Demand Status</th>
											<%-- <c:if test="${fn:containsIgnoreCase(currentRole, 'INVENTORY ADMINISTRATOR')}">
												<th>Actions</th>
											</c:if> --%>
											<th>CIV Status</th>
											<th>CRV Status</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="mdv" items="${messDemandVoucherList}">
											<tr class="gradeC">
												<td>
													${mdv.demandVoucherId}
												</td>
												<td>
													${mdv.updatedBy}
												</td>
												<td>
													${mdv.demandVoucherOpenDate}
												</td>
												<c:choose>
													<c:when test="${mdv.demandVoucherCloseDate eq null}">
														<td>
															N/A
														</td>
													</c:when>
													<c:otherwise>
														<td>
															${mdv.demandVoucherCloseDate}
														</td>
													</c:otherwise>
												</c:choose>
												<td>
													${mdv.demandVoucherStatus}
												</td>
												<%-- <c:if test="${fn:containsIgnoreCase(currentRole, 'INVENTORY ADMINISTRATOR')}">
													<td><a class="mb-xs mt-xs mr-xs btn btn-primary" href="viewIndentSheetDetails.html?indentSheetId=${mdv.demandVoucherId}">View Details</a></td>
												</c:if> --%>
												<c:choose>
													<c:when test="${mdv.demandVoucherObjectId eq null}">
														<td>
															<a href="createCIV.html?indentSheetId=${mdv.demandVoucherId}">Create CIV</a>
														</td>
													</c:when>
													<c:otherwise>
														<td>
															${mdv.demandVoucherObjectId}
														</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${mdv.demandVoucherObjectId eq null}">
														<td>
															N/A
														</td>
													</c:when>
													<c:when test="${mdv.commodityReceiveVoucher.commodityReceiveVoucherCode ne null}">
														<td>
															${mdv.commodityReceiveVoucher.commodityReceiveVoucherCode}
														</td>
													</c:when>
													<c:otherwise>
														<td>
															<a href="createCRV.html?civId=${mdv.demandVoucherObjectId}">Create CRV</a>
														</td>
													</c:otherwise>
												</c:choose>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</section>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>