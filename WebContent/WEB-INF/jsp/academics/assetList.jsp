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
<title>Asset List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<c:choose>
	<c:when test="${assetPagedListHolder.pageList eq null}">
		<div class="btnsarea01" >
			<div class="infomsgbox" style="visibility: visible;">
				<span>No Asset found. </span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>	

		<form:form id="searchAsset" name="searchAsset" action="searchAcademicsAsset.html" method="POST">
			
			
			
			
			<section role="main" class="content-body">
					
			
			
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Asset List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Asset Type</th>
											<th>Asset Sub Type</th>
											<th>Asset Name</th>
											<th>Department</th>
											<th>Price</th>
											<th>Details</th>
											<th>Consume</th>
											<th>Consume List</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="asset" items="${assetPagedListHolder.pageList}" varStatus="i">
											<tr class="gradeC">
												<td>
													${asset.assetType}
												</td>
												<td>
													${asset.assetSubType}
												</td>
												<td>
													${asset.assetName}
												</td>
												<td>
													${asset.department.departmentName}
												</td>
												<td>
													${asset.assetPrice}
												</td>
												
													<td>	<input type="button" value="EDIT DETAILS" id="editDetails" class="greenbtn" onclick="window.open('getAcademicsAssetDetails.html?assetID=${asset.assetId}','_self')">
													</td>
													<td>
														<input type="button" value="CONSUMPTION" id="consumption" class="submitbtn" onclick="window.open('viewAcademicsAssetConsumption.html?assetID=${asset.assetId}&assetName=${asset.assetName}','_self')">
													</td>
													<td>
														<input type="button" value="CONSUMPTION LIST" id="consumpList" class="clearbtn" onclick="window.open('viewAcademicsAssetConsumptionList.html?assetID=${asset.assetId}&assetName=${asset.assetName}','_self')">
													</td>
											</tr>
										</c:forEach>
										
									</tbody>
								</table>
							</div>
						</section>			
					</section>
			<input type="submit" name="details" value="DETAILS" class="submitbtn">
		</form:form>
	</c:otherwise>
</c:choose>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>