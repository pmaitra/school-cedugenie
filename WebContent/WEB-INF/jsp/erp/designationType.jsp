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
	document.getElementById("textDesignationType"+rowId).removeAttribute("disabled");		
};
/* function saveData(rowId){
	rowId=rowId.replace("save","");
	document.getElementById("saveId").value=rowId;	
	var validateStatus = validateEditDesignationTypeForm("textDesignationType"+rowId);
	if(validateStatus == true){
		document.editDesignationTypeForm.submit();
	}
}; */
</script>
</head>
<body>
	<header class="page-header">	<!-- Added by Saif 29-03-2018-->
			<h2>Add Designation Type</h2>
	</header>
	
	<div class = "content-padding">
		<div class="row">
		<div class="col-md-5">
			<div class="alert alert-warning" id="javascriptmsg" style="display: none">
			  <span></span>	
			</div>
			<c:if test="${submitResponse ne null}">			
			<c:if test="${submitResponse eq 'Success'}">
				<div class="alert alert-success">
					<strong>Designation type successfully created.</strong>
				</div>
			</c:if>
			<c:if test="${submitResponse eq 'Fail'}">
				<div class="alert alert-danger">
					<strong>Designation type creation failed.</strong>	
				</div>
			</c:if>
		</c:if>
		  	<form:form name="submitDesignationType" action="submitDesignationType.html" method="POST">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
	
						<h2 class="panel-title">Create New Designation Type</h2>										
					</header>
					<div style="display: block;" class="panel-body">
	                                   										
	                                   <div class="form-group">
	                                       <label class="control-label">Enter Designation Type <span class="required" aria-required="true">*</span></label>
	                                       <input type="text" class="form-control" name="designationTypeName" id="designationTypeName" placeholder="">
	                                   </div> 
	                                       
					</div>
					<footer style="display: block;" class="panel-footer">
						<button class="btn btn-primary" type="submit" id="submitButton" onclick="return validateDesignationTypeForm();">Submit </button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
				</section>
	        </form:form>
		</div>
		
		
		
		
		<c:choose>
			<c:when test="${designationTypeList == null ||  empty designationTypeList}">
				<div class="alert alert-danger">
					<strong>No designation type created yet</strong>
	 			</div>
			</c:when>	
		<c:otherwise>
		<div class="col-md-7">	
		<c:if test="${updateResponse ne null}">
			<c:if test="${updateResponse eq 'Success'}">
				<div class="alert alert-success">
					<strong>Designation type successfully updated.</strong>	
				</div>
			</c:if>
			<c:if test="${updateResponse eq 'Fail'}">
				<div class="alert alert-danger">
					<strong>Designation type updation failed.</strong>
				</div>
			</c:if>
		</c:if>
		
        <section class="panel">
        	<form name="editDesignationType" id="editDesignationType" action="editDesignationType.html" method="post">
			<input type="hidden" name="saveId" id="saveId">
			<input type="hidden" name="getDesignationType" id="getDesignationType">
              <header class="panel-heading">
                  <div class="panel-actions">
                      <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                  </div>

                  <h2 class="panel-title">View / Edit Designation Type</h2>
              </header>
              <div class="panel-body">

                  <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                      <thead>
                          <tr>
                              <th>Designation Type</th>
                              <th>Actions</th>
                          </tr>
                      </thead>
                      <tbody>
                      	<c:forEach var="designationType" items="${designationTypeList}" varStatus="i">
                         <tr>
                             <td>
                             	<input type="hidden" name="oldDesignationTypeNames${i.index}" value="${designationType.designationTypeName}">                             	
                             	<input type="hidden" id ="oldDesgnationTypeNamesforDuplicateChecking${i.index}" name="oldDesgnationTypeNamesforDuplicateChecking" value="${designationType.designationTypeName}">
                             	<input type="hidden" class="form-control" id="designationTypeName${i.index}" name="designationTypeName${i.index}" value="${designationType.designationTypeName}" disabled="disabled">
                             	${designationType.designationTypeName}
                            	</td>
                            <td class="actions">
                            <a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic " onclick = "showDesignationTypeDetails('${designationType.designationTypeName}','${i.index}')"><i class="fa fa-pencil"></i></a>
								<%-- <a href="#" class="hidden on-editing save-row" id="save${i.index}"><i class="fa fa-save"></i></a>
								<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
								<a href="#" class="on-default edit-row" id="edit${i.index}"><i class="fa fa-pencil"></i></a> --%>
							</td>
                         </tr>
                      </c:forEach>   
                    </tbody>
                </table>
            </div>
            </form>
        </section>
		</div>
		
		<!-- popup Window code -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide" style="max-width: 800px;">
									<section class="panel">
										<header class="panel-heading">
											 <h2 class="panel-title">Edit Designation Type</h2> 
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id = "designationType">
												<thead>
													<tr>
				                                        <th>Designation Type</th>
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
													<button id="updateDesignationType" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
	</c:otherwise>
	</c:choose>	
	</div>	
	</div>
	
	<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
	<%@ include file="/include/js-include.jsp" %>
	<script src="/icam/js/erp/designationType.editable.js"></script>
	<script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script>
	<script src="/icam/js/erp/designationType.js"></script>
</body>
</html>