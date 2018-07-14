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
<title>Library Policy</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
						
					 <div class="row">
						<div class="col-md-12">
						  <form:form name="addVendor"  method="POST" action="addVendorRating.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Vendor Rating Policy</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        <div class="form-group">
											<label class="col-md-2 control-label">Vendor Type</label>
											<div class="col-md-3">
												<select class="form-control" name="vendorTypeSelect" id="vendorTypeSelect" required>
                                                    <option value="">Select...</option>
                                                    <c:forEach var="vendorTypeList" items="${vendorTypeList}">
														<option value="${vendorTypeList.vendorTypeCode}">${vendorTypeList.vendorTypeName}</option>
													</c:forEach>
                                                </select>
											</div>
										</div>
                                        <br>
										<div class = "form-group" id="policyDetails" style="visibility: collapse;">
										<u style="text-transform: uppercase;"><span id="titleToShow">Vendor Rating Policy</span></u><br>
											<p>
                                                Max No. Of days to supply the ordered materials <input type="text" name="maxSupplyDay" id="maxSupplyDay" value= "" class="form-control" style="width: 100px; display: inline-block;" required>
                                            </p>
                                            <p>
                                                Max No. Of supplied damaged assets <input type="text" name="maxNoDeffects" id="maxNoDeffects" value= "" class="form-control" style="width: 100px; display: inline-block;" required>
                                            </p>
                                            <p>
                                                Vendor should not disclose any transaction with the organization for security reason.
                                            </p>
										</div>
									</div>                                    
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id="submit" type="submit">Submit </button>
									</footer>
								</section>
								<div class="btnsarea01">
								<div class="warningbox" id="warningbox" >
									<span id="warningmsg"></span>	
								</div>
							</div>
                            </form:form>
						</div>
						
					</div>	
					
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/backoffice/vendorRating.js"></script>
</body>
</html>