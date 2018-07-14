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
<title>Add Vendor Type Form</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
</style>
<script type="text/javascript">
/* added by ranita.sur on 14092017 for showing pop up */
function showVendorTypeDetails(vendorTypeName,index)
{
	//alert("HII");
	//alert("LN 97 ::"+departmentName);
	$('#vendorTypeDetails> tbody').empty();
 	if(vendorTypeName != null && vendorTypeName!=""){
 		var row = "<tbody>";
 		row += "<tr><td><input type='text' name='vendorTypeName' id='vendorTypeName' class = 'form-control'  value='"+vendorTypeName+"'> </td></tr>";
 		$("#vendorTypeDetails").append(row);
 	}
 	
 	$('#modalInfo').fadeIn("fast");
 	var btn = document.getElementById("updateVendorType");
 	btn.setAttribute("onclick","saveData('"+index+"','"+vendorTypeName+"');");
	
	}
/* added by ranita.sur on 14092017 for showing pop up */
function saveData(rowId,vendorTypeName){
	var vendorTypeName=document.getElementById("vendorTypeName").value;
	document.getElementById("saveId").value=rowId;
	document.getElementById("getVendorType").value = vendorTypeName;
  
	var validateStatus = validateEditVendorTypeForm(rowId);
	if(validateStatus == true){
		document.editVendorTypeForm.submit();
	}
}
/* added by ranita.sur on 14092017 for showing pop up */
function closeWarning(){
	document.getElementById("warningmsg1").style.display = 'none';	
}


</script>
</head>
<body>
	<header class="page-header">
		<h2>Add Vendor Type</h2>
	</header>
	<c:if test="${submitResponse ne null}">			
			<c:if test="${submitResponse eq 'Success'}">
				<div class="alert alert-success">
					<strong>Vendor type successfully created.</strong>
				</div>
			</c:if>
			<c:if test="${submitResponse eq 'Fail'}">
				<div class="alert alert-danger">
					<strong>Vendor type creation failed.</strong>	
				</div>
			</c:if>
		</c:if>
		<div class="alert alert-warning" id="javascriptmsg" style="display: none">
		  <span></span>	
		</div>
		<c:if test="${updateResponse ne null}">
			<c:if test="${updateResponse eq 'Success'}">
				<div class="alert alert-success">
					<strong>Vendor type successfully updated.</strong>	
				</div>
			</c:if>
			<c:if test="${updateResponse eq 'Fail'}">
				<div class="alert alert-danger">
					<strong>Vendor type updation failed.</strong>
				</div>
			</c:if>
		</c:if>
		
	<div class="content-padding">
	<div class="row">
		<div class="col-md-5">
		  	<form:form name="submitVendorType" action="submitVendorType.html" method="POST">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
	
						<h2 class="panel-title">Create New Vendor Type</h2>										
					</header>
					<div style="display: block;" class="panel-body">
	                    <div class="form-group">
	                        <label class="control-label">Enter Vendor Type <span class="required" aria-required="true">*</span></label>
	                        <input type="text" class="form-control" name="vendorTypeName" id="vendorTypeName" placeholder="eg.: Book Vendor">
	                    </div> 
	                </div>
					<footer style="display: block;" class="panel-footer">
						<button class="btn btn-primary" type="submit" id="submitButton" onclick="return validateVendorTypeForm();">Submit </button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
				</section>
	        </form:form>
		</div>
		
		
		
		
		<c:choose>
			<c:when test="${vendorTypeList == null ||  empty vendorTypeList}">
				<div class="alert alert-danger">
					<strong>No Vendor type created yet</strong>
	 			</div>
			</c:when>	
		<c:otherwise>
		<div class="col-md-7">	
        <section class="panel">
        	<form name="editVendorTypeForm" id="editVendorTypeForm" action="editVendorType.html" method="post">
			<input type="hidden" name="saveId" id="saveId">
		<!-- 	/* added by ranita.sur on 14092017 for showing pop up */ -->
			<input type="hidden" name="getVendorType" id="getVendorType">
              <header class="panel-heading">
                  <div class="panel-actions">
                      <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                  </div>
					
                  <h2 class="panel-title">View / Edit Vendor Type</h2>
              </header>
              <div class="panel-body">

                  <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                      <thead>
                          <tr>
                              <th>Vendor Type</th>
                              <th>Actions</th>
                          </tr>
                      </thead>
                      <tbody>
                      	<c:forEach var="vendorType" items="${vendorTypeList}" varStatus="i">
                         <tr>
                             <td>
                             	<input type="hidden" name="oldVendorTypeNames${i.index}" value="${vendorType.vendorTypeName}">
                             	<input type="hidden" name="vendorTypeCode${i.index}" value="${vendorType.vendorTypeCode}">                             	
                             	<input type="hidden" name="oldVendorTypeNamesforDuplicateChecking" value="${vendorType.vendorTypeName}">
                             	<input type="hidden" class="form-control" id="vendorTypeName${i.index}" name="vendorTypeName${i.index}" value="${vendorType.vendorTypeName}" disabled="disabled">
                             	${vendorType.vendorTypeName}
                            	</td>
                            <td class="actions">
                           <!--  /* added by ranita.sur on 14092017 for showing pop up */ -->
								 <a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic " onclick = "showVendorTypeDetails('${vendorType.vendorTypeName}','${i.index}')"><i class="fa fa-pencil"></i></a>
												
							</td>
                         </tr>
                      </c:forEach> 
                    </tbody>
                </table>
            </div>
            <!-- popup Window code -->
             <!--  /* added by ranita.sur on 14092017 for showing pop up */ -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide" style="max-width: 800px;">
									<section class="panel">
										<header class="panel-heading">
											 <h2 class="panel-title">Edit VendorType</h2> 
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id = "vendorTypeDetails">
												<thead>
													<tr>
				                                        <th>Vendor Type</th>
				                                   </tr>
												</thead>
												<tbody>
								
												</tbody>
											</table>
											<div class="alert alert-warning" id="warningmsg1" style="display: none">
												<span></span>	
											</div>
										</div>
										
										<footer class="panel-footer">
											<div class="row">
												<div class="col-md-12 text-right">
													<button id="updateVendorType" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
            </form>
        </section>
		</div>
	</c:otherwise>
	</c:choose>	
	</div>	
	</div>
	<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
	<%@ include file="/include/js-include.jsp" %>
	<script src="/icam/js/erp/designationType.editable.js"></script>
	 <!--  /* added by ranita.sur on 14092017 for showing pop up */ -->
	<script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script>
	<script src="/icam/js/common/vendorType.js"></script>
</body>
</html>