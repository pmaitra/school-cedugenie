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
       }
</style>
<script type="text/javascript">
function showCategoryNameDetails(categoryName,index)
{
	
	$('#socialCategoryNames> tbody').empty();
 	if(categoryName != null && categoryName!=""){
 	//	alert("HLw");	//closed by Saif 21-03-2018
 		var row = "<tbody>";
 		row += "<tr><td><input type='text' name='categoryName' id='categoryName' class = 'form-control'  value='"+categoryName+"'></td></tr>";
 		$("#socialCategoryNames").append(row);
 	}
 	
 	$('#modalInfo').fadeIn("fast");
 	var btn = document.getElementById("updateSocialCategory");
 	btn.setAttribute("onclick","saveData('"+index+"','"+categoryName+"');");
	
	}
function saveData(rowId,categoryName){
	var categoryName=document.getElementById("categoryName").value;
	document.getElementById("saveId").value=rowId;
	document.getElementById("getCategoryType").value = categoryName;
  
	var validateStatus = validateEditSocialCategory(rowId);
	if(validateStatus == true){
		document.editSocialCategory.submit();
	}
}
function closeWarning(){
	document.getElementById("warningmsg1").style.display = 'none';	
}

</script>

</head>
<body>
				<header class="page-header">
					<h2>Social Category</h2>
				</header>
				<div class="content-padding">
				<c:if test="${insertUpdateStatus ne null}">
					<%-- <div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
						<span id="infomsg1">${insertUpdateStatus}</span>	
					</div> --%>
					<div class="alert alert-success">
						<strong>${insertUpdateStatus}</strong>
					</div>
				</c:if>
                    <div class="row">
						<div class="col-md-4">
						  <form action="addSocialCategory.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Social Category</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="col-sm-5 control-label">Social Category Name: </label>
											<div class="col-sm-7">
												<input id="socialCategoryName" name="socialCategoryName" class="form-control" type="text" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" title="Category name can consist of alphabetical characters and spaces only" required>
											</div>
										</div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" onclick = "return validateCreateSocialCategory();">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
                            <div class="alert alert-warning" id="javascriptmsg" style="display: none">
							  <span></span>	
							</div>
						</div>
						
						
						
						<c:choose>
							<c:when test="${socialCategoryList eq null}">
								<div class="btnsarea01" >
									<!-- <div class="errorbox" id="errorbox" style="visibility: visible;">
										<span id="errormsg">Social Category Not Created Yet</span>	
									</div> -->
									<div class="alert alert-danger">
										<strong>Social Category Not Created Yet.</strong>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="col-md-8">
									<form name="editSocialCategory" id="editSocialCategory" action="editSocialCategory.html" method="post">
									<input type="hidden" name="saveId" id="saveId">
									<input type="hidden" name="getCategoryType" id="getCategoryType">
		                            <section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
										</div>
										<h2 class="panel-title">Existing Social Category</h2>
									</header>
									<div class="panel-body">
										
										<table class="table table-bordered table-striped mb-none" id="datatable-editable">
											<thead>
												<tr>
		                                            <th>Social Category Name</th>								
													<th>Actions</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="socialCategory" items="${socialCategoryList}" varStatus="i">
												<tr class="gradeX">
													<td>
														<input type="hidden" name="oldCategoryCode${i.index}" value="${socialCategory.socialCategoryCode}">
														<input type="hidden" name="oldCetegoryNamesForDuplicateCheck" value="${socialCategory.socialCategoryName}">
														<input type="hidden" id="categoryName${i.index} name="categoryName${i.index}" class="form-control" value="${socialCategory.socialCategoryName}" disabled ">
														${socialCategory.socialCategoryName}
													</td>
													<td class="actions">
													 <a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic " onclick = "showCategoryNameDetails('${socialCategory.socialCategoryName}','${i.index}')"><i class="fa fa-pencil"></i></a>
														<%-- <a href="#" class="hidden on-editing save-row" id="save${i.index}"><i class="fa fa-save"></i></a>
														<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
														<a href="#" class="on-default edit-row" id="edit${i.index}"><i class="fa fa-pencil"></i></a> --%>
													</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</section>
								
								<!-- popup Window code -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide" style="max-width: 800px;">
									<section class="panel">
										<header class="panel-heading">
											 <h2 class="panel-title">Edit Social Category</h2> 
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id = "socialCategoryNames">
												<thead>
													<tr>
				                                        <th>Social Category Name</th>
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
													<button id="updateSocialCategory" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
								</form>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>	
					
				
					

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/backoffice/createSocialCategory.editable.js"></script>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script src="/cedugenie/js/backoffice/createSocialCategory.js"></script>
</body>
</html>