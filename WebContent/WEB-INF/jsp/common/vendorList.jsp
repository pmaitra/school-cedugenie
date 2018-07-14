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
<title>List Books</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<c:choose>
	<c:when test="${vendorPagedListHolder eq null}">
		<div class="btnsarea01" style="visibility: visible;">
			<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
				<span id="infomsgbox">Vendor Not Available</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>

		<form:form name="viewVendorListDetails" action="vendorDetails.html" method="POST">
			
			
			<section role="main" class="content-body">
			
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Vendor List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Select</th>
											<th>Vendor Name</th>
											<th>Contact No 1</th>
											<th>Contact No 2</th>
											<th>Address</th>
											<th>Vendor Type</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="vendor" items="${vendorPagedListHolder}">
											<tr class="gradeC">
												<td>
													<input type="radio" name="radio" value="${vendor.vendorCode}"/>
												</td>				
												<td>
													${vendor.vendorName}
												</td>
												<td>
													${vendor.vendorContactNo1}
												</td>
												<td>
													${vendor.vendorContactNo2}
												</td>
												<td>
													${vendor.address}
												</td>
												<td>
													${vendor.vendorType}
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</section>			
					</section>
				<input type="submit" value="DETAILS" class="mb-xs mt-xs mr-xs modal-basic btn btn-info" onclick="return valradio('radio','warningbox','warningmsg');">
			</form:form>
		</c:otherwise>
	</c:choose>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>