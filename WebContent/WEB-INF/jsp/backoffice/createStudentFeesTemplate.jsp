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
<title>Create Fees Template</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
       #datatable-editable_filter{
			display: none;
		}
</style>
</head>
<body>

				<header class="page-header">
					<h2>Create Fees Template</h2>
				</header>
				<div class="content-padding">
					 <div class="row">
					 	<c:if test="${insertUpdateStatus ne null}">				
							<c:if test="${insertUpdateStatus eq 'success'}">
								<div class="alert alert-success">
								  <strong>Fees template successfully created.</strong> 
								</div>
							</c:if>
							<c:if test="${insertUpdateStatus eq 'fail'}">
								<div class="alert alert-danger">
								  <strong>Fees template creation failed.</strong> 
								</div>
							</c:if>
						</c:if>
						<c:if test="${updateResponse ne null}">			
							<c:if test="${updateResponse eq 'Success'}">				
								<div class="alert alert-success">
								  <strong>Fees template successfully updated.</strong> 
								</div>
							</c:if>
							<c:if test="${updateResponse eq 'Fail'}">
								<div class="alert alert-danger">
								  <strong>Fees template updation failed.</strong> 
								</div>
							</c:if>
						</c:if>
						
						<div class="col-md-4">
						  <form:form method="POST" id="studentFeesTemplate" name="studentFeesTemplate" action="submitStudentFeesTemplate.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Fees Template</h2>
									</header>
									<c:forEach var="oldTemp" items="${studentFeesTemplateList}">
										<input type="hidden" name="oldFeesTemplates" value="${oldTemp.studentFeesTemplateCode}">
									</c:forEach>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
											<label class="control-label">Template Name:<span class="required" aria-required="true">*</span></label>
											<input id="studentFeesTemplateName" name="studentFeesTemplateName" class="form-control" type="text" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" title="The  name can consist only alphabetical characters and spaces only" required>
										</div>
                                        
										<div class="form-group">
											<label class="control-label">Select Category(ies):  <span class="required" aria-required="true">*</span></label>
											<div class="">
											<ul class="ulList">
												<c:forEach var="category" items="${listCategory}" varStatus="i">
												<li>
													<input type="checkbox" value="${category.feesCategoryCode}" name="newtemps" id = "newtemps"> ${category.feesCategoryName}( ${category.feesDuration.feesDurationName} )
												</li>
												</c:forEach>
											</ul>
											</div>
										</div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" onclick = "return validate()">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
						<div class="col-md-8">
							<form name="editStudentFeesTemplate" id="editStudentFeesTemplate" action="editStudentFeesTemplate.html" method="post">
							<input type="hidden" name="saveId" id="saveId">
							<input type="hidden" name="getName" id="getName">
							<input type="hidden" name="getcategory" id="getcategory">
							
							
							<div id="categoryList">
							<c:forEach var="listCategory" items="${listCategory}" >
								<input type="hidden" name="oldCategories" value="${listCategory.feesCategoryCode}">
							</c:forEach>
							</div>
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Fees Template List</h2>
							</header>
							<div class="panel-body">
							<div id= "category">
							<c:forEach var="category" items="${listCategory}">
												<input type="hidden" name="details" value ="${category.feesCategoryName}">
											</c:forEach>
											</div>
								<c:choose>
									<c:when test="${listCategory eq null || empty listCategory}">
										<div class="alert alert-danger">
											<strong>No template is created yet</strong>	
										</div>
									</c:when>
								<c:otherwise>
								<c:forEach var="group" items="${studentFeesTemplateList}" varStatus="i">
									<input type="hidden" name="oldCategorydurationNames" value="${group.studentFeesTemplateName}">
								
								
								</c:forEach>
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
                                            <th>Template Name<span class="required" aria-required="true">*</span></th>
											<th>Category(Duration)<span class="required" aria-required="true">*</span></th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="studentFeesTemplates" items="${studentFeesTemplateList}" varStatus="i">
										<tr>											
											<td>
												<input type="hidden" class="form-control" value="${studentFeesTemplates.studentFeesTemplateCode}" id="idOldTemplate${i.index}" name="nameOldTemplate${i.index}">
												<input type="hidden" class="form-control" value="${studentFeesTemplates.studentFeesTemplateName}" name="nameOldTemplates">
												<input type="hidden" class="form-control" value="${studentFeesTemplates.studentFeesTemplateName}" readonly id="idNewTemplate${i.index}" name="nameNewTemplate${i.index}">${studentFeesTemplates.studentFeesTemplateName}
											</td>
											<td>
											<ul class="ulList"  id="ulList${i.index}" name="ulList${i.index}">
												<c:forEach var="allCategory" items="${listCategory}" >
                                                  	<c:set var="counter" value="0" scope="page" />
                                                  		<c:forEach var="categ" items="${studentFeesTemplates.feesCategoryList}">
                                                  			<c:if test="${categ.feesCategoryName eq allCategory.feesCategoryName}">
                                                  				<c:set var="counter" value="1" scope="page" />
                                                  			</c:if>
                                                       </c:forEach>
                                                       <c:if test="${counter eq 1}">
	                                                       <li>
		                                                       	<input type="hidden" name="oldcategories${i.index}" value="${allCategory.feesCategoryName}">
		                                                       	<input type="checkbox" value="${allCategory.feesCategoryName}" name="nametemplate${i.index}" checked="checked" id = "idtemplate" disabled> ${allCategory.feesCategoryName}
	                                                       </li>
                                                       </c:if>
                                                       <c:if test="${counter ne 1}">
	                                                       <li>
	                                                       		<input type="checkbox" value="${allCategory.feesCategoryName}" name="nametemplate${i.index}" id = "idtemplate" disabled> ${allCategory.feesCategoryName}
	                                                       </li>
                                                       </c:if>
                                                  </c:forEach>
												</ul>
                                            </td>
											<td class="actions">
												<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
												<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" id="edit${i.index}" onclick = "showFeeTemplateDetails('${i.index}','${feesList.feesCategoryName}')"><i class="fa fa-pencil"></i></a>
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
                                                <th>Template Name</th>
                                                <th>Category(Duration)</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>
                                    </table>
                                    <div class="alert alert-default" id="javascriptmsg1" style="display: none">
										<span> </span>	
									</div> 
                              	</div>
                                <footer class="panel-footer">
									<div class="row">
										<div class="col-md-12 text-right">
											<button id="updateFeesTemplate" class="btn btn-success">Update</button>
											<button class="btn btn-danger modal-dismiss" onclick="removeMsg()">Cancel</button>
										</div>
									</div>
								</footer>	
								<div class="alert alert-danger" id="javascriptmsg" style="display: none">
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
<script src="/cedugenie/js/backoffice/createStudentFeesTemplate.editable.js"></script>
<script src="/cedugenie/js/backoffice/createStudentFeesTemplate.js"></script>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
</body>
</html>