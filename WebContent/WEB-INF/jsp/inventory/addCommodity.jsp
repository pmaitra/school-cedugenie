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
<title>Commodity Details</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
			<header class="page-header">
				<h2>Add Commodity to Catalogue</h2>
			</header>
			<div class="content-padding">
					<!-- start: page -->
					<div class="row">
						<c:if test="${status eq 'Success'}">
							<div class = "alert alert-success">
								<strong>${msg}</strong>
							</div>
						</c:if>
						<c:if test="${status eq 'Failed'}">
							<div class = "alert alert-danger">
								<strong>${msg}</strong>
							</div>
						</c:if>
						<!-- added by ranita.sur on 11092017 -->
						
					<div class="alert alert-danger" id="javascriptmsg" style="display: none">
						<span></span>	
						</div>
						<div class="col-md-6 col-md-offset-3">
						  <form:form method="POST" id="saveCommodity" name="saveCommodity" action="saveCommodity.html" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Add Commodity to Catalogue</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Commodity Name<span class="required" aria-required="true">*</span></label>
                                                <input type="text" name="commodityName" id="commodityName" class="form-control" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" placeholder="eg.: Chair" title="Only Characters and spaces between words are allowed" required>
												<input type="hidden" name="commodityCode" id="commodityCode" >
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Commodity Description</label>
                                                <!-- modified by sourav.bhadra on 17-08-2017 -->
                                                <input type="text" name="commodityDesc" id="commodityDesc" class="form-control" value="" pattern="^[a-zA-Z0-9\s]+$" placeholder="es.: Highback Chair" title="Only Characters, numbers and spaces between words are allowed">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Commodity Type<span class="required" aria-required="true">*</span></label>
                                                <select class="form-control" name="commodityType" id="commodityType" onchange="createSubType(this);" required>
													<option value="">Select</option>
													<option value="ASSET">Asset</option>
													<option value="STOCK">Stock</option>
                                                </select>
                                            </div> 
                                            
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Commodity Sub Type</label>
                                                <!-- modified by sourav.bhadra on 27-07-2017 to make commodity sub type disabled -->
                                                <select name="commoditySubType" id="commoditySubType" class="form-control" disabled="disabled">
                                                	<option value="">Select</option>
                                               </select>
                                            </div>
                                            <!-- modified by sourav.bhadra on 28-07-2017 to add commodity unit -->
                                            <div class="form-group">
                                                <label class="control-label">Commodity Unit<span class="required" aria-required="true">*</span></label>
                                                <select class="form-control" name="modelNo" id="modelNo" required>
													<option value="">Select</option>
													<c:forEach var="unit" items="${commodityUnitList}">
														<option value="${unit.commodityName}">${unit.commodityName}</option>
													</c:forEach>
                                                </select>
                                            </div>
                                            <!-- <div class="form-group">
                                                <label class="control-label">Commodity Threshold<span class="required" aria-required="true">*</span></label>
                                                <input type="text" name="threshold" id="threshold" class="form-control" pattern="^[1-9]\d*$" placeholder="eg.: 10" title="Only numbers are allowed." required>
                                            </div>-->
                                        </div>    
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submit" name="submit" onclick="return ValidateForm()">Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
						<div class="col-md-12">
							<form name="editCommodity" id="editCommodity" action="editCommodity.html" method="post">
							<input type="hidden" name="saveId" id="saveId">
							<input type="hidden" name="commodityNewName" id="commodityNewName">
							<input type="hidden" name="commodityNewDesc" id="commodityNewDesc">
							<!--  <input type="hidden" name="newThreshold" id="newThreshold">-->
							<input type="hidden" name="commodityNewType" id="commodityNewType">
							<input type="hidden" name="commodityNewSubType" id="commodityNewSubType">
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Commodity List</h2>
                                </header>
                                
                                <c:choose>
									<c:when test="${commodityList eq null}">
										<span>${message}</span>	
									</c:when>
									<c:otherwise>
										<div class="panel-body">
											<table id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
												<thead>
													<tr>
														<th>Commodity Name</th>
														<th>Commodity Desc</th>
														<th>Unit</th>
														<!--<th>In Stock</th>-->
														<!--  <th>Threshold</th>-->
														<th>Commodity Type</th>
														<th>Commodity Sub Type</th>
														<th>Actions</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="commodity" items="${commodityList}" varStatus="i">
														<tr class="gradeX">
															<td>
																<input type="hidden" id="oldCommodityCode${i.index}" name="oldCommodityCode${i.index}" value="${commodity.commodityCode}" />
																<input type="hidden" class="form-control" id="newCommodityName${i.index}" name="newCommodityName${i.index}" value="${commodity.commodityName}" disabled onblur="checkCommodityName(this,'oldCommodityCode${i.index}');"/>
																${commodity.commodityName}
															</td>
															<td>
																<input type="hidden" class="form-control" id="commodityDesc${i.index}" name="commodityDesc${i.index}" value="${commodity.commodityDesc}" />	<!--Changes by Saif 23-03-2018 all disabled are removed from hidden-->
																${commodity.commodityDesc}
															</td>
															<td>
																<input type="hidden" class="form-control" id="modelNo${i.index}" name="modelNo${i.index}" value="${commodity.modelNo}" />
																${commodity.modelNo}
															</td>
															<%--<td>
																<input type="hidden" class="form-control" id="inStock${i.index}" name="inStock${i.index}" value="${commodity.inStock}"/>
																${commodity.inStock}
															</td>--%>
															<%-- <td>
																<input type="hidden" class="form-control" id="threshold${i.index}" name="threshold${i.index}" value="${commodity.threshold}" />
																${commodity.threshold}
															</td> --%>
															<td>
																<input type="hidden" class="form-control" id="commodityType${i.index}" name="commodityType${i.index}" value="${commodity.commodityType}" />
																${commodity.commodityType}
															</td>
															<td>
																<input type="hidden" class="form-control" id="commoditySubType${i.index}" name="commoditySubType${i.index}" value="${commodity.commoditySubType}" />
																${commodity.commoditySubType}
															</td>
															<td class="actions">
																<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" onclick="updateCommodity('${i.index}')"><i class="fa fa-pencil"></i></a>
															</td>
														</tr>
													</c:forEach>
												
												</tbody>
											</table>
										</div>
									</c:otherwise>
                                </c:choose>
                            </section>
                            <!-- popup Window code -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide" style="max-width: 800px">
									<section class="panel">
										<header class="panel-heading">
											<!-- <h2 class="panel-title">Approver Group Name - PO_Approver</h2> -->
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id="updateCommodityDetails">
												<thead>
													<tr>
														<th>Commodity Name</th>
														<th>Commodity Desc</th>
														<!--<th>In Stock</th>-->
														<!--  <th>Threshold</th>-->
														<th>Commodity Type</th>
														<th>Commodity Sub Type</th>
													</tr>
												</thead>
												<tbody>
								
												</tbody>
											</table>
											<div class="alert alert-warning" id="warningmsg" style="display: none">
												<span></span>	
											</div>
										</div>
										<footer class="panel-footer">
											<div class="row">
												<div class="col-md-12 text-right">
													<button id="updateCommodity" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
                            </form>
						</div>
					</div>
                   
					<!-- end: page -->
	</div>

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/icam/js/inventory/createCommodity.editable.js"></script>
<script type="text/javascript" src="/icam/js/inventory/addCommodity.js"></script>
<script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script>
</body>
</html>