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
<title>Create Fees Structure</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
<script type="text/javascript">
function makeEditable(rowId){
	rowId = rowId.replace("edit","");
	document.getElementById("idCategoryName"+rowId).removeAttribute("readonly");
	document.getElementById("idCategoryDuration"+rowId).removeAttribute("disabled");
};


</script>

</head>
<body>
			<header class="page-header">
				<h2>Create Fees Structure</h2>
			</header>
			<div class="content-padding">
		
             		<div class="row">
	                    <c:if test="${insertUpdateStatus ne null}">				
							<c:if test="${insertUpdateStatus eq 'success'}">
								<div class="alert alert-success">
								  <strong>Category successfully created.</strong> 
								</div>
							</c:if>
							<c:if test="${insertUpdateStatus eq 'fail'}">
								<div class="alert alert-danger">
								  <strong>Category creation failed.</strong> 
								</div>
							</c:if>
						</c:if>
						<c:if test="${updateResponse ne null}">			
							<c:if test="${updateResponse eq 'success'}">				
								<div class="alert alert-success">
								  <strong>Category successfully updated.</strong> 
								</div>
							</c:if>
							<c:if test="${updateResponse eq 'fail'}">
								<div class="alert alert-danger">
								  <strong>Category updation failed.</strong> 
								</div>
							</c:if>
						</c:if>
						<div class="col-md-4">
					  		<form action="submitFeesCategory.html" method="POST">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Create Fees Structure</h2>										
									</header>
									
									<div style="display: block;" class="panel-body" id ="feesDetail">
										<div class="form-group">
											<label class="col-sm-5 control-label">Category Name:</label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id = "feesCategoryName" name = "feesCategoryName" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" title="The  name can consist only alphabetical characters and spaces only" required>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label">Duration:</label>
											<div class="col-sm-7">
												<select class="form-control" id = "feesCategoryDuration" name = "feesCategoryDuration" required>
                                                <option value="">Select...</option>
                                                <c:forEach var="feesDuration" items="${listFeesDuration}">
													<option value="${feesDuration.feesDurationCode}" ><c:out value="${feesDuration.feesDurationName}"></c:out></option>	
												</c:forEach>
                                            </select>
											</div>
										</div>
									</div>
									<div class="alert alert-warning" id="javascriptmsg1" style="display: none">
		  									<span> </span>	
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" onclick = "return validation();">Submit</button>
									</footer>
								</section>
								<div class="alert alert-warning" id="javascriptmsg1" style="display: none">
	  									<span> </span>	
								</div>
                            </form>
						</div>
								
						<div class="col-md-8">
							<form id="updateFeesCategories" name="updateFeesCategories" action="editFeesStructure.html" method="POST">
								<input type="hidden" name="saveId" id="saveId">
								<input type="hidden" name="getName" id="getName">
								<input type="hidden" name="getDuration" id="getDuration">
		                         <section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
										</div>
										<h2 class="panel-title">Existing Categories</h2>
									</header>
									<div class="panel-body">
								 	<c:forEach var="feesDurationJsp" items="${listFeesDuration}">
												<input type="hidden" name="details" value ="${feesDurationJsp.feesDurationName}">
											</c:forEach>
										<c:choose> 
											<c:when test="${listCategory eq null || listCategory.size() eq 0}">
												<div class="alert alert-danger" >
													<strong>No Fees category has been created yet.</strong> 
												</div>
											</c:when>
										<c:otherwise>
										   
										
										<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
											<thead>
												<tr>
													<th>Category Name</th>
													<th>Duration</th>								
													<th>Actions</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="feesList" items="${listCategory}" varStatus="i">
												<tr class="gradeX">
													<td>
														<input type="hidden" name="oldCategoryCode${i.index}" value="${feesList.feesDuration.feesDurationCode}">
														<input type="hidden" name="oldCategoryNames" class="form-control" value="${feesList.feesCategoryName}" >
														<input type="hidden" name="oldCategoryName${i.index}" class="form-control" value="${feesList.feesCategoryName}" >
														<input type="hidden" id="idCategoryName${i.index}" name="nameCategoryName${i.index}" class="form-control" value="${feesList.feesCategoryName}" readonly >
														${feesList.feesCategoryName}
													</td>
													<td>
														<input type="hidden" id="oldFeesDuration${i.index}" name="oldFeesDuration${i.index}"value="${feesList.feesDuration.feesDurationCode}">
														<select id="idCategoryDuration${i.index}" name="nameCategoryDuration${i.index}" class="form-control" disabled>
															<c:forEach var="feesDurationJsp" items="${listFeesDuration}">
																<option value="${feesDurationJsp.feesDurationCode}" ${feesDurationJsp.feesDurationCode eq feesList.feesDuration.feesDurationCode?'selected':value}>${feesDurationJsp.feesDurationName}</option>
															</c:forEach>
														</select>
													</td>
													<td class="actions">
														
														<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
														<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" id="edit${i.index}" onclick = "showfeeDetails('${i.index}','${feesList.feesCategoryName}','${feesList.feesDuration.feesDurationCode}')"><i class="fa fa-pencil"></i></a>
													</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</c:otherwise>
								</c:choose>	
							</div>
						</section>
						  <div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
                               <section class="panel">
                                   <header class="panel-heading">
                                   </header>
                                   <div class="panel-body">
                                       <table class="table table-bordered table-striped mb-none" id = "approverGroupDetails">
                                           <thead>
                                               <tr>
                                                   <th>Category Name</th>
                                                   <th>Duration</th>
                                               </tr>
                                           </thead>
                                           <tbody>
                                           </tbody>
                                       </table>
                                   </div>
                                   <footer class="panel-footer">
									<div class="row">
											<div class="col-md-12 text-right">
												<button id="updateFees" class="btn btn-success">Update</button>
												<button class="btn btn-danger modal-dismiss"  onclick="removemsg()">Cancel</button>
											</div>
										</div>
									</footer>
  								<div class="alert alert-warning" id="javascriptmsg" style="display: none">
									<span> </span>	
								</div>                                             
                           	</section>
                       	</div>
					</form>
				</div>
			</div>
		</div>
 					
					

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/backoffice/createFeesStructure.editable.js"></script>
<script type="text/javascript" src="/cedugenie/js/backoffice/createFessStructure.js"></script>
 <script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
</body>
</html>