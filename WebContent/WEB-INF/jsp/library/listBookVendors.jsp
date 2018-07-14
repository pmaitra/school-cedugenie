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
<title>List Books</title>

<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class= "page-header">		<!-- added by Saif 29-03-2018 -->
		<h2>Vendor List</h2>
	</header>
	<div class = "content-padding">
		<c:choose>
	<c:when test="${vendorList eq null}">		
		<div id="errorbox" style="visibility:visible;">
			<span class="errormsg">No Book Vendor List Found</span>
		</div>
	</c:when>
<c:otherwise>
		<form:form name="viewVendorListDetails" id="viewVendorListDetails" action = "editBookVendor.html" method = "POST">
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
											<th>Vendor Code</th>
											<th>Vendor Name</th>
											<th>Contact No 1</th>
											<th>Contact No 2</th>
											<th>Address</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="vendor" items="${vendorList}">
											<tr class="gradeC">
												<td>
													<input type="radio" name="vendorCode" value="${vendor.vendorCode}"/>
												</td>				
												<td>
													${vendor.vendorCode}
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
	</div>

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">

$(document).ready(function() { 
   $('input[name=vendorCode]').change(function(){
        $('form').submit();
   });
});
</script>
</body>
</html>