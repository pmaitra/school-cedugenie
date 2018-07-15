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
<title>Student Details Form</title>
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
		<h2>Commodity Vendor Mapping For Perishable Material</h2>
	</header>
	<div class="content-padding">
					<div class="row" id="wrap">
						<c:if test="${message ne null}">
							<div class = "alert alert-success">
								<strong>${message}</strong>
							</div>
						</c:if>
						<c:choose>
						<c:when test="${vendorList eq null || empty vendorList}">
							<div class = "alert alert-danger">
								<strong>Sorry! No such vendors found.</strong>
							</div>
						</c:when>
						<c:otherwise>
						<form:form name="mapCommodityVendorForPerishableMaterial"  method="POST" action="mapCommodityVendorForPerishableMaterial.html">
							<div class="col-md-4 col-md-offset-4">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Commodity Vendor Mapping</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="control-label">Vendor Name</label>
											<select class="form-control" name="vendorName" id="vendorName">
												<option value="">Select...</option>
												<c:forEach var="vendor" items="${vendorList}">
													<option value="${vendor.vendorCode}">${vendor.vendorName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<label class="control-label">Vendor Code</label>
											<input type="text" name="vendorCode" id="vendorCode" readonly class="form-control" placeholder="" >
										</div>
 									</div>									
								</section>
							</div>
							<div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Commodity List</h2>
	                                </header>
	                                
	                                <c:choose>
										<c:when test="${commodityList eq null || empty commodityList}">
											<div class = "alert alert-danger">
												<strong>Sorry! No commodity found.</strong>
											</div>
										</c:when>
										<c:otherwise>
			                                <div class="panel-body">
			                                    <table class="table table-bordered table-striped mb-none">
			                                        <thead>
			                                            <tr>
			                                                <th>Select</th>
															<th>Commodity Name</th>
															<th>Commodity Rate Per Unit</th>
															<th>Unit</th>
															<th>Price History</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        
			                                        	<c:forEach var="commodity" items="${commodityList}" varStatus="i">
														<tr class="gradeX">
															<td>
																<input id="check${i.index}" type="checkbox" name="commodity" value="${commodity.commodityName}" />
															</td>
															<td>
																${commodity.commodityName}
															</td>
															<td>
																<input type="text" class="form-control" name="${commodity.commodityName}" id="txt${i.index}" value="0.00" />
																<input type="hidden" name="old${commodity.commodityName}" id="old${i.index}" value="0.00" />
															</td>
															<td>
																${commodity.modelNo}
															</td>
															<td>
																<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic btn btn-info" id="${commodity.commodityCode}" onclick = "return showBookPriceHistoryPopUp(this.id)">History</a>
															</td>
														</tr>
														</c:forEach>
			                                        </tbody>
			                                    </table>
			                                    <div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
					                            <section class="panel">
					                                <header class="panel-heading">
					                                    <h2 class="panel-title">Commodity Supply History</h2>
					                                </header>
					                                <div class="panel-body">
					                                    <table class="table table-bordered table-striped mb-none" id = "priceHistoryPopUp">
					                                        <thead>
					                                            <tr>
					                                                <th>Date</th>
					                                                <th>Price</th>
					                                            </tr>
					                                        </thead>
					                                        <tbody>
					                                        
					                                        </tbody>
					                                    </table>
					                                </div>
														<footer class="panel-footer">
															<div class="row">
																<div class="col-md-12 text-right">
																	<button class="btn btn-info modal-dismiss">OK</button>
																</div>
															</div>
														</footer>
													</section>
												</div>
			                                </div>
	                                </c:otherwise>
	                                </c:choose>
	                                <footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submit" name="submit" onclick = "return validate()">Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
	                            </section>
							</div>
						</form:form>
						</c:otherwise>
						</c:choose>
					</div>
	</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script type="text/javascript" src="/cedugenie/js/mess/messDailyRationCommodityVendorMapping.js"></script>
<script type="text/javascript">
function showBookPriceHistoryPopUp(id){
	var commodityCode=id;
	var vendorCode=document.getElementById("vendorCode").value;
	$.ajax({
		url:'/cedugenie/vendorCommodityPriceHistoryForPerishableMaterial.html',
		dataType: 'json',
		data:"vendorCode=" + vendorCode+ "&commodityCode=" +commodityCode,
		success: function(data){
			$('#priceHistoryPopUp > tbody').empty();
			if(null!=data && data!=""){
				dataArray=data.split("#");
				var row = "<tbody>";
				for(var i=0;i<dataArray.length-1;i++){
					var dateAndRate = dataArray[i].split("*");
					row+= "<tr><td>"+dateAndRate[0]+"</td><td>"+dateAndRate[1]+"</td></tr>";
				}
				$("#priceHistoryPopUp").append(row);
 			}
			$('#modalInfo').fadeIn("fast");
		}
	});
}
</script>
</body>
</html>