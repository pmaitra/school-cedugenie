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
/* 	function makeEditable(rowId){
		rowId=rowId.replace("edit","");
		document.getElementById("textDesig"+rowId).removeAttribute("readonly");	
		document.getElementById("designationName"+rowId).removeAttribute("disabled");
	}; */
	
	
</script>
</head>
<body>
<!-- added by ranita.sur on 21092017 -->
<div class="alert alert-danger" id="javascriptmsg" style="display: none">
			  <span></span>	
			</div>
<header class="page-header">
	<h2>Add Designation</h2>
</header>
<div class="content-padding">
	<div class="row">
		<div class="col-md-5">
		  	<form:form name="submitDesignation" id="submitDesignation" action="submitDesignation.html" method="POST">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>

						<h2 class="panel-title">Create New Designation</h2>										
					</header>
					<div style="display: block;" class="panel-body">
                        <div class="form-group">
	                        <label class="control-label">Designation Type <span class="required" aria-required="true">*</span></label>
	                        <select class="form-control" required id="designationTypeName" name="designationType.designationTypeName" onchange="getDesignationForDesignationType(this);">
	                            <option value="">Select...</option>
	                            <c:forEach var="designationType" items="${designationTypeList}" >
									<option VALUE="${designationType.designationTypeCode}">${designationType.designationTypeName}</option>
								</c:forEach>
	                        </select>
	                    </div>
	                    <div class="form-group">
	                        <label class="control-label">Enter Designation Name <span class="required" aria-required="true">*</span></label>
	                        <input type="text" class="form-control" name="designationName" id="designationName" placeholder="" required>
	                    </div>
					</div>
					<footer style="display: block;" class="panel-footer">
						<button class="btn btn-primary" type="submit" id="submitButton" onclick="return validateDesignationForm();">Submit </button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
				</section>
        	</form:form>
        	
		</div>
		
		<c:if test="${submitResponse ne null}">				
			<c:if test="${submitResponse eq 'Success'}">
				<div class="alert alert-success">
					<strong>Designation created successfully.</strong>	
				</div>
			</c:if>
			</c:if>
			<c:if test="${submitResponse ne null}">				
			<c:if test="${submitResponse eq 'duplicate'}">
				<div class="alert alert-danger">
					<strong>Designation duplicate.</strong>	
				</div>
			</c:if>
			<c:if test="${submitResponse eq 'Fail'}">
				<div class="alert alert-danger">
					<strong>Designation creation failed.</strong>	
				</div>
			</c:if>
		</c:if>
		<c:if test="${updateResponse ne null}">				
			<c:if test="${updateResponse eq 'Success'}">
				<div class="alert alert-success">
					<strong>Designation successfully updated.</strong>	
				</div>
			</c:if>
			<c:if test="${updateResponse eq 'Fail'}">
				<div class="alert alert-danger">
					<strong>Designation updation failed.</strong>
				</div>
			</c:if>		
		</c:if>
		
		<c:choose>
		<c:when test="${designationList eq null || empty designationList}">
			<div class="alert alert-danger">
				<strong>No designation created yet.</strong>	
 			</div>
		</c:when>	
		<c:otherwise>
		
		<div class="col-md-7">	
			<form name="editDesignationForm" id="editDesignationForm" action="editDesignation.html" method="post">
			<input type="hidden" name="saveId" id="saveId">
			<input type="hidden" name="getNewDesignation" id="getNewDesignation">
			<input type="hidden" name="getNewDesignationType" id="getNewDesignationType">
                        <section class="panel">
                            <header class="panel-heading">
                                <div class="panel-actions">
                                    <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                </div>

                                <h2 class="panel-title">View / Edit Designation</h2>
                            </header>
                            <div class="panel-body">
                              <div id=oldDesignationType> 
								<c:forEach var="designation" items="${designationTypeList}" >
									<input type="hidden" class="form-control" id="olddesignation" name="olddesignation" value="${designation.designationTypeCode}" />
								</c:forEach>
                             </div>   
                                <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                                    <thead>
                                        <tr>
                                            <th>Designation</th>
                                            <th>Designation Type</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="designation" items="${designationList}" varStatus="i">
                                        <tr class="gradeX">
											<td>
												<input type="hidden" class="form-control" id="designationName${i.index}" name="designationName${i.index}" placeholder="" value="${designation.designationName}" >
												<input type="hidden" name="oldDesignationNames${i.index}" value="${designation.designationName}">
												<input type="hidden" name="oldDesignationNamesForDuplicateCheck" value="${designation.designationName}">
												 ${designation.designationName}
											</td>	
											<td>
												<input type="hidden" id="designationTypeDesignationTypeName${i.index}" name="designationTypeDesignationTypeName${i.index}" value="${designation.designationType.designationTypeCode}">
		                                         ${designation.designationType.designationTypeCode}
		                                    </td>
											<td class="actions">
											 <a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic " onclick = "showDesignationDetails('${i.index}');"><i class="fa fa-pencil"></i></a>
		                                        <%-- <a href="#" class="hidden on-editing save-row" id="save${i.index}"><i class="fa fa-save"></i></a>
												<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a> --%>
												<%-- <a href="#" class="on-default edit-row" id="edit${i.index}"><i class="fa fa-pencil"></i></a> --%>
		                                    </td>
										</tr>
                                     </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </section>
                        </form>
                        <!-- popup Window code -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide" style="max-width: 800px;">
									<section class="panel">
										<header class="panel-heading">
											 <h2 class="panel-title">Edit Designation Type</h2> 
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id = "designationTypeNames">
												<thead>
													<tr>
				                                        <th>Designation </th>
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
													<button id="updateDesignation" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
		</div>
		</c:otherwise>
		</c:choose>
	</div>
</div>	
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/icam/js/erp/designation.editable.js"></script>
<script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script>
<script src="/icam/js/erp/designation.js"></script>
</body>
</html>