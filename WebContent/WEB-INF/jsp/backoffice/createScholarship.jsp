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
<title>Create Scholarship</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
<script type="text/javascript">
function makeEditable(rowId){
	rowId=rowId.replace("edit","");
	document.getElementById("name"+rowId).removeAttribute("readonly");
	document.getElementById("concession"+rowId).removeAttribute("readonly");
	document.getElementById("unit"+rowId).removeAttribute("disabled");
};
function saveData(rowId){
	rowId=rowId.replace("save","");
	document.getElementById("saveId").value=rowId;
	var status = editValidation(rowId);
	if(status == true){
		document.editScholarship.submit();
	}
	
};

</script>

</head>
<body>

		<c:if test="${insertUpdateStatus ne null}">
			
				<div class="alert alert-success">
						<strong>${insertUpdateStatus}</strong>
				</div>
		</c:if>

                    <div class="row">
						<div class="col-md-4">
						  <form action="addScholarship.html" method="POST">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Scholarship</h2>										
									</header>
									
									<div style="display: block;" class="panel-body" id ="feesDetail">
										<div class="form-group">
											<label class="col-sm-5 control-label">Scholarship Name: </label>
											<div class="col-sm-7">
												<input type="text" class="form-control" id="scholarshipName" name="scholarshipName" required>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label">Concession: </label>
											<div class="col-sm-7">
												 <input type="text" class="form-control" id="concession" name="concession" required>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label">Unit: </label>
											<div class="col-sm-7">
												<select class="form-control" id="concessionUnit" name="concessionUnit" required>
                                                <option value="">Select...</option>
                                                <option value="%">%</option>
												<option value="INR">INR</option>
                                            </select>
											</div>
										</div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" onclick = "return validation();">Submit</button>
									</footer>
								</section>
                            </form>
						</div>
						
						
						
								<c:choose>
									<c:when test="${scholarshipList eq null || empty scholarshipList}">
										<div class="btnsarea01" >
											<div class="errorbox" id="errorbox" style="visibility: visible;">
												<span id="errormsg">Scholarship Not Created Yet</span>	
											</div>
										</div>
									</c:when>
								<c:otherwise>
								<div class="col-md-8">
									<form name="editScholarship" id="editScholarship" action="editScholarship.html" method="POST">
									<input type="hidden" name="saveId" id="saveId">
		                            <section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
										</div>
								
										<h2 class="panel-title">Existing Scholarships</h2>
									</header>
									<div class="panel-body">
										
										<table class="table table-bordered table-striped mb-none" id="datatable-editable">
											<thead>
												<tr>
													<th>Scholarship Name</th>
													<th>Concession</th>
													<th>Unit</th>								
													<th>Actions</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="scholarship" items="${scholarshipList}" varStatus="i">
												<tr class="gradeX">
													<td>
														<input type="hidden" name="oldScholarshipCode${i.index}" value="${scholarship.scholarshipCode}">
														<input type="hidden" name="oldScholarshipNames" class="form-control" value="${scholarship.scholarshipName}" >
														<input type="text" id="name${i.index}" name="Name${i.index}" class="form-control" value="${scholarship.scholarshipName}" readonly >
													</td>
													<td>
														<input type="text" id="concession${i.index}" name="Concession${i.index}" class="form-control" value="${scholarship.concession}" readonly>
														<input type="hidden" name="oldConcessionAmount${i.index}" id= "oldConcessionAmount${i.index}" value="${scholarship.concession}" >
													</td>
													<td>
														<select id="unit${i.index}" name="Unit${i.index}" class="form-control" disabled>
															<c:choose>
																<c:when test="${scholarship.concessionUnit eq '%'}">						
																	<option value="%" selected="selected">%</option>
																	<option value="INR">INR</option>
																</c:when><c:otherwise>
																	<option value="INR" selected="selected">INR</option>
																	<option value="%">%</option>
																</c:otherwise>
															</c:choose>
														</select>	
														<input type="hidden" name="oldConcessionUnit${i.index}" id= "oldConcessionUnit${i.index}" value="${scholarship.concessionUnit}" >
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
							</c:otherwise>
						</c:choose>
					</div>
 		
 					
					

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/backoffice/createScholarship.js"></script>
<script src="/cedugenie/js/backoffice/createScholarship.editable.js"></script>
</body>
</html>