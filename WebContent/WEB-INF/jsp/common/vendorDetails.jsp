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
<script type="text/javascript" src="/icam/js/common/vendorDetails.js"></script>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
</style>
</head>
<body>
					<div class="row">
						<div class="col-md-8 col-md-offset-2">
						  <form:form name="updateVendorDetails"  method="POST" action="updateVendorDetails.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a data-panel-toggle="" class="panel-action panel-action-toggle" href="#"></a>
										</div>

										<h2 class="panel-title">Vendor Details</h2>										
									</header>
									<div class="panel-body" style="display: block;"> 
									<div class="row">  
									<div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label">Vendor Name</label>
                                            <input type="text" placeholder="" name="vendorName" id="vendorName" value="${vendor.vendorName}" readonly="readonly" class="form-control">
                                        	<input type="hidden"  name="vendorCode" readonly="readonly" value="${vendor.vendorCode}"/>
                                        </div>
										<div class="form-group">
                                            <label class="control-label">Vendor Type <span aria-required="true" class="required">*</span></label>
                                                <input type="hidden" class="form-control" name="assetId" value="${asset.assetId}" placeholder="">
                                                <input class="form-control" disabled="disabled" value = "${vendor.vendorType}"/>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Contact No. 1</label>
                                            <input type="text" placeholder="" name="vendorContactNo1" id="vendorContactNo1" value="${vendor.vendorContactNo1}" readonly="readonly" class="form-control">
                                        </div>
                                        </div>
                                        <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label">Contact No. 2</label>
                                            <input type="text" placeholder="" name="vendorContactNo2" id="vendorContactNo2" value="${vendor.vendorContactNo2}" readonly="readonly" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Address</label>
                                            <input type="text" placeholder="" name="address" id="address" value="${vendor.address}" readonly="readonly" class="form-control">
                                        </div>
                                        </div>
                                     </div>       
									</div>
									<footer class="panel-footer" style="display: block;">
										<button class="btn btn-primary" id="edit" type="button" value="Edit" onclick="editableVendorDetailsForm();">Edit</button>
										<button class="btn btn-default" type="reset" id = "clear" value = "Clear">Reset</button>
										<button class="mb-xs mt-xs mr-xs modal-basic btn btn-danger" type="submit" id = "delete" value="Delete" name="delete" disabled="disabled">Delete</button>
										<button type="submit" class="btn btn-danger" id="update" value="Update" name="update" disabled="disabled" onclick="return validateVendorDetailsForm();">Update</button>
									</footer>
								</section>
                            </form:form>
						</div>
						</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>