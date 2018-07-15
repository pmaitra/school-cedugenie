<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Add Commodity Details</title>
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
				<h2>Individual Commodity Details</h2>
			</header>
			<div class="content-padding">
					<div class="row">
						<c:if test="${status ne null }">
							<div class = "alert alert-success">
								<strong>${status}</strong>
							</div>
						</c:if>
						<c:choose>
						<c:when test="${commodityList eq null && commodityList.size() eq 0}">
							<span>Asset Commodities Not Found.</span>
						</c:when>
						<c:otherwise>
						<form id="individualAssetDetails" name="individualAssetDetails" method="post">
							<div class="col-md-4 col-md-offset-4">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Individual Commodity Details</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="control-label">Commodity</label>
											<select class="form-control" id="commodityCode" name= "commodityDetailsIndividual">	<!-- name is added by saif 23-03-2018 -->
												<option value="">Select</option>
												<c:forEach var="commodity" items="${commodityList}">
													<option value="${commodity.commodityCode}">${commodity.commodityName}</option>				
												</c:forEach>
											</select>
										</div>
 									</div>
								</section>
							</div>
							<div class="col-md-12" id="commodityDetailsTable" style="display: none;">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Individual Commodity List</h2>
	                                </header>
			                                <div class="panel-body">
			                                    <table class="table table-bordered table-striped mb-none">
			                                        <thead>
			                                            <tr>
			                                                <th></th>
															<th>Commodity Code</th>
															<th>Purchase Date</th>
															<th>Model No.</th>
															<th>Warranty(Years)</th>
															<th>Depreciation(%)</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody id="tableBody">
			                                        
			                                        </tbody>
			                                    </table>
			                                </div>
	                                <footer style="display: block;" class="panel-footer">
										<input type="button" id="edit" class="btn btn-primary" onclick="onEdit();" value="Edit" />
										<input type="button" class="btn btn-primary" id="update" onclick="return onUpdate();" value="Update" />
										<input type="button" id="retire" class="btn btn-primary" onclick="return onRetire();" value="Retire" />
									</footer>
	                            </section>
							</div>
						</form>
						</c:otherwise>
						</c:choose>
					</div>
                  </div> 

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/inventory/individualCommodityDetails.js"></script>
</body>
</html>