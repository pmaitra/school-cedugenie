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
		<header class="page-header">	<!-- Added by Saif 29-03-2018 -->
				<h2>Tender Wise Pricing</h2>
		</header>
				<div class = "content-padding">
					<div class="row" id="wrap">
						<c:if test="${status eq 'success'}">
							<div class = "alert alert-success">
								<strong>${message}</strong>
							</div>
						</c:if>
						
						<c:if test="${status eq 'fail'}">
							<div class = "alert alert-danger">
								<strong>${message}</strong>
							</div>
						</c:if>
						
						<form:form name="mapCommodityVendor"  method="POST" action="tenderWisePricing.html">
							<div class="col-md-4 col-md-offset-4">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Tender Wise Pricing</h2>										
									</header>
									<div style="display: block;" class="panel-body">
									
										<div class="form-group">
											<label class="control-label">Financial Year</label>
											<select class="form-control" name="financialYear" id="financialYear">
												<option value="">Select...</option>
												<c:forEach var="finance" items="${financialYearList}">
													<option value="${finance.financialYearCode}">${finance.financialYearName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<label class="control-label">Commodity</label>
											<select class="form-control" name="commodityName" id="commodityName">
												<option value="">Select...</option>
												
											</select>
										</div>
										
 									</div>									
								</section>
							</div>
							<div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Vendor List</h2>
	                                </header>
	                                
	                               <%--  <c:choose>
										<c:when test="${commodityList eq null || empty commodityList}">
											<div class = "alert alert-danger">
												<strong>Sorry! No commodity found.</strong>
											</div>
										</c:when>
										<c:otherwise> --%>
			                                <div class="panel-body">
			                                    <table class="table table-bordered table-striped mb-none">
			                                        <thead>
			                                            <tr>
			                                                <th>Select</th>
															<th>Vendor Name</th>
															<th>Unit</th>
															<th>Commodity Rate Per Unit</th>
															
			                                            </tr>
			                                        </thead>
			                                        <tbody id="vendorPricingTable">
			                                        
			                                        	
			                                        </tbody>
			                                    </table>
			                                   
			                                </div>
	                               
	                                <footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submit" name="submit" >Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
	                            </section>
							</div>
						</form:form>
						
					</div>
				</div>
					
                   
                   
                  


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script type="text/javascript" src="/cedugenie/js/inventory/termWisePricing.js"></script>
</body>
</html>