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
	<c:when test="${commodityVendorMappingPagedListHolder.pageList eq null}">
		<div class="btnsarea01" >
			<div class="infomsgbox" style="visibility: visible;">
				<span>No Commodity found. </span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	
	<c:if test="${successStatus != null}">
		<div class="successbox" id="successbox" style="visibility:visible;">
			<span id="successmsg" style="visibility:visible;">Successfully Updated!</span>	
		</div>
	</c:if>
	<c:if test="${failStatus != null}">
		<div class="errorbox" id="errorbox" style="visibility: visible;">
			<span id="errormsg">Update Fail!</span>	
		</div>
	</c:if>

		<form:form id="submitCommodityVendorMapping" name="submitCommodityVendorMapping" action="submitCommodityVendorMapping.html" method="POST">
			
			
			
			
			
			
			
			
			
			
			
			<section role="main" class="content-body">
					
			
			
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Commodity Vendor Mapping</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Quarter Master Session: <input type="text" value="${inventorySession.academicYearName}" id="houseMasterSession" name="inventorySession.academicYearName" class="textfield" readonly="readonly" ></th>
											<th colspan="4"> Vendor:
												<select id="vendorCode" name="vendor.vendorCode" class="defaultselect">
													<option VALUE="">Please select</option>
													<c:forEach var="vendor" items="${vendorList}" varStatus="i">
														<option value="${vendor.vendorCode}">${vendor.vendorName}</option>
													</c:forEach>
												</select>
											</th>
										</tr>
										<tr>
											<th>Commodity Name</th>
											<th>Commodity Type</th>
											<th>Commodity Desc</th>
											<th>A/C-Unit</th>
											<th>Rate</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="commodity" items="${commodityVendorMappingPagedListHolder.pageList}" varStatus="i">
											<tr class="gradeC">
												<td>
													${commodity.commodityName}
													<input type="hidden" value="${commodity.commodityName}" id="commodityName${i.count}" readonly="readonly">
													<input type="hidden" value="${commodity.commodityId}" id="commodityId${i.count}" name="commodityList[${i.index}].commodityId" readonly="readonly">
													<c:set var="numberOfCommodityOnPage" value="${i.count}" scope="page" />
												</td>
												<td>
													${commodity.commodityType}
												</td>
												<td>
													${commodity.commodityDesc}
												</td>
												<td>
													<select id="unit${i.count}" name="commodityList[${i.index}].unitName" class="defaultselect">
														<option VALUE="" >Please select</option>
														<option value="Kg">Kg</option>
														<option value="Pkt">Pkt</option>
														<option value="Bott">Bott</option>
														<option value="Ltr">Ltr</option>
														<option value="-">UNKNOWN</option>						
													</select>
												</td>
												<td>
													<input type="text" onblur="validateCommodityVendorMappingRate(this)" onfocus="this.value='';" value="0.00" id="rate${i.count}" name="commodityList[${i.index}].purchaseRate" class="textfieldRate">
												</td>
											</tr>
										</c:forEach>
										
									</tbody>
								</table>
							</div>
						</section>			
					</section>
					<input id="submit" class="greenbtn" type="submit" value="Submit" name="submit" onclick="return validateSubmitCommodityVendorMappingForm(${numberOfCommodityOnPage});">
					<input id="clear" class="clearbtn" type="reset" value="Clear">
		</form:form>
	</c:otherwise>
</c:choose>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>