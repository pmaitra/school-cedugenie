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
<script type="text/javascript">
function makeEditable(rowId){
	rowId=rowId.replace("edit","");
	document.getElementById("vendorName"+rowId).removeAttribute("readonly");
	document.getElementById("vendorContact1"+rowId).removeAttribute("readonly");
	document.getElementById("vendorContact2"+rowId).removeAttribute("readonly");
	document.getElementById("vendorAddress"+rowId).removeAttribute("readonly");
};
function saveData(rowId){
	rowId=rowId.replace("save","");
	alert("In saveData :: "+rowId);
	document.getElementById("saveId").value=rowId;
	document.getElementById("function").value="UPDATE";
	//window.location="editHostel.html?saveId="+rowId;
	document.editVendorForm.submit();
	
};
</script>
</head>
<body>

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
					<div class="row">
						<div class="col-md-8 col-md-offset-2">
						  <form:form name="addVendor"  method="POST" action="submitAddVendor.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Add Vendor</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="col-md-6">
	                                        <div class="form-group">
	                                            <label class="control-label">Vendor Name</label>
	                                            <input type="text" class="form-control" name="vendorName" id="vendorName" placeholder="">
	                                        </div>
											<div class="form-group">
	                                            <label class="control-label">Vendor Type <span class="required" aria-required="true">*</span></label>
	                                            <select class="form-control" name="vendorType" id="vendorType">
	                                                <option value="">Select...</option>
	                                                <c:forEach var="vendorType" items="${vendorTypeList}">
														<option value="${vendorType.vendorTypeCode}">${vendorType.vendorTypeName}</option>
													</c:forEach>
	                                            </select>
	                                        </div>
                                        </div>
                                        <div class="col-md-6">
	                                        <div class="form-group">
	                                            <label class="control-label">Contact No. 1</label>
	                                            <input type="text" class="form-control" name="vendorContactNo1" id="vendorContactNo1" placeholder="">
	                                        </div>                                        
	                                        <div class="form-group">
	                                            <label class="control-label">Contact No. 2</label>
	                                            <input type="text" class="form-control" name="vendorContactNo2" id="vendorContactNo2" placeholder="">
	                                        </div>
										</div>
                                        <div class="form-group">
                                           	<label class="control-label">Address</label>
                                           	<input type="text" class="form-control" name="address" id="address" placeholder="">
                                       	</div>
                                        
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id="submit" type="submit" >Submit </button>
										<button type="reset" class="btn btn-default" id="clear">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
						<div class="col-md-12">
							<form name="editVendorForm" id="editVendorForm" action="updateVendorDetails.html" method="post">
							<input type="hidden" name="saveId" id="saveId">
							<input type="hidden" name="function" id="function">
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
								<h2 class="panel-title">Vendor List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-editable">
									<thead>
										<tr>
											<th>Vendor Name</th>
                                            <th>Vendor Type</th>
											<th>Contact No 1</th>
                                            <th>Contact No 2</th>
                                            <th>Address</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="vendor" items="${vendorDetailsList}" varStatus="i">
										<tr>											
												<td>
													<input type="hidden" name="oldVendorCode${i.index}" value="${vendor.vendorCode}">
													<input type="text" name="newVendorName${i.index}" class="form-control" value="${vendor.vendorName}" readonly id="vendorName${i.index}">
												</td>
												<td>
													${vendor.vendorType}
												</td>
												<td>
													<input type="text" name="newVendorContact1${i.index}" class="form-control" value="${vendor.vendorContactNo1}" readonly id="vendorContact1${i.index}">
												</td>
												<td>
													<input type="text" name="newVendorContact2${i.index}" class="form-control" value="${vendor.vendorContactNo2}" readonly id="vendorContact2${i.index}">
												</td>
												<td>
													<input type="text" name="newVendorAddress${i.index}" class="form-control" value="${vendor.address}" readonly id="vendorAddress${i.index}">
												</td>
													<td class="actions">
													<a href="#" class="hidden on-editing save-row" id="save${i.index}"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#" class="on-default edit-row" id="edit${i.index}"><i class="fa fa-pencil"></i></a>
												</td>
											
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</section>
						</form>
						</div>
					</div>	
					






<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/backoffice/addVendor.editable.js"></script>
</body>
</html>