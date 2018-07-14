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
<title>Edit Facility</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>


							<div class="row">
								<div class="col-md-8 col-md-offset-2">
									<form:form id="editFacilityDetails" name="editFacilityDetails" action="updateFacilityDetails.html" method="post">
										<section class="panel">
											<header class="panel-heading">
												<div class="panel-actions">
													<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
												</div>
												<h2 class="panel-title">Edit Facility</h2>
											</header>
											<div style="display: block;" class="panel-body">
												<div class="row">
													<div class="col-md-4">
														<div class="form-group">
															<label class="control-label">Facility Name</label>
															<div class="">
																<input type="hidden" class="form-control" id="facilityCode" name="facilityCode" value="${facilityDetails.facilityCode}">
																<input type="text" class="form-control class1" id="facilityName" name="facilityName" value="${facilityDetails.facilityName}" disabled="disabled" >
															</div>
														</div>
													</div>
													<div class="col-md-4">
				                                        <div class="form-group">
															<label class="control-label">Description</label>
															<div class="">
																<input type="text" class="form-control class1" id="facilityDesc" name="facilityDesc" value="${facilityDetails.facilityDesc}" disabled="disabled" >
															</div>
														</div>
													</div>
													<div class="col-md-4">
														<div class="form-group" id="paidUnpaid">
															<label class="control-label">Payment Status</label>
															<div>
																<c:choose>
																	<c:when test="${facilityDetails.ispaid eq true}">
																		<label class="radio-inline radio-primary class2">
																			<input type="radio" name="ispaid" value="true" id="paid" checked="checked" disabled>Paid
																		</label>
																		<label class="radio-inline radio-primary class2">
																			<input type="radio" name="ispaid" value="false" id="unpaid" disabled>Unpaid
																		</label>
																	</c:when>
																	<c:otherwise>
																		<label class="radio-inline radio-primary class2">
																			<input type="radio" name="ispaid" value="true" id="paid" disabled>Paid
																		</label>
																		<label class="radio-inline radio-primary class2">
																			<input type="radio" name="ispaid" value="false" id="unpaid" checked="checked" disabled>Unpaid
																		</label>	
																	</c:otherwise>
																</c:choose>
															</div>
														</div>
													</div>
												</div>
												<hr>
												<c:choose>
													<c:when test="${facilityDetails.ispaid eq true}">
														<div class="form-group col-sm-6 col-md-offset-3" style="display:block" id="chargeTable">
				                                           	<table class="table table-bordered table-striped mb-none">
				                                                <thead>
				                                                    <tr>
				                                                        <th style="background:#eee; text-align:center;">Category</th>
				                                                        <th style="background:#eee; text-align:center;">Charge(Yearly)</th>
				                                                    </tr>
				                                                </thead>
				                                                <tbody>
				                                                	<c:forEach var="sc" items="${facilityDetails.socialCategoryList}">
					                                                    <tr>
					                                                        <td>
																				<input type="hidden" name="category" value="${sc.socialCategoryCode}" />
																				${sc.socialCategoryName}
																			</td>
					                                                        <td><input type="text" class="form-control class1" name="${sc.socialCategoryCode}" value = "${sc.amount}" disabled="disabled"></td>
					                                                    </tr>
				                                                    </c:forEach>
				                                                </tbody>
				                                            </table>
			                                        	</div>
													</c:when>
													<c:otherwise>
														<div class="form-group col-sm-6 col-md-offset-3" style="display:none" id="chargeTable">
				                                           	<table class="table table-bordered table-striped mb-none">
				                                                <thead>
				                                                    <tr>
				                                                        <th style="background:#eee; text-align:center;">Category</th>
				                                                        <th style="background:#eee; text-align:center;">Charge(Yearly)</th>
				                                                    </tr>
				                                                </thead>
				                                                <tbody>
				                                                	<c:forEach var="sc" items="${socialCategoryList}">
					                                                    <tr>
					                                                        <td>
																				<input type="hidden" name="category" value="${sc.socialCategoryCode}" />
																				${sc.socialCategoryName}
																			</td>
					                                                        <td><input type="text" class="form-control" name="${sc.socialCategoryCode}" value = "0.00" required></td>
					                                                    </tr>
				                                                    </c:forEach>
				                                                </tbody>
				                                            </table>
			                                        	</div>
													</c:otherwise>
												</c:choose>
											</div>
											<footer style="display: block;" class="panel-footer">
												<button class="btn btn-primary" id="submit" name="submit" type="submit" style="display:none">Submit</button>
												<button type="reset" id = "reset" class="btn btn-default" style="display:none">Reset</button>
												<button class="btn btn-danger" id="edit" name="edit" type="button">Edit</button>	
											</footer>
										</section>
		                            </form:form>
								</div>
							</div>



<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/facility/editFacilityDetails.js"></script>
</body>
</html>