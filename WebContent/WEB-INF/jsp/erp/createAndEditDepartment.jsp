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
/* modified by ranita.sur on 14092017 for showing department in pop up */
function showDepartmentNameDetails(departmentName,index)
{
	//alert("HII");
	//alert("LN 97 ::"+departmentName);
	$('#departmentType> tbody').empty();
 	if(departmentName != null && departmentName!=""){
 		var row = "<tbody>";
 		row += "<tr><td><input type='text' name='departmentName' id='departmentName' class = 'form-control'  value='"+departmentName+"'> </td></tr>";
 		$("#departmentType").append(row);
 	}
 	
 	$('#modalInfo').fadeIn("fast");
 	var btn = document.getElementById("updateDepartment");
 	btn.setAttribute("onclick","saveData('"+index+"','"+departmentName+"');");
	
	}
function saveData(rowId,departmentName){
	var departmentName=document.getElementById("departmentName").value;
	document.getElementById("saveId").value=rowId;
	document.getElementById("getDepartmentType").value = departmentName;
  
	var validateStatus = validateEditDepartmentForm(rowId);
	if(validateStatus == true){
		document.editDepartmentForm.submit();	
	}
}

function closeWarning(){
	document.getElementById("warningmsg1").style.display = 'none';	
}

</script>
</head>
<header class="page-header">
	<h2>Add Department</h2>
</header>
<body>
					<!-- added by ranita.sur on 21092017 -->

                          <div class="alert alert-danger" id="javascriptmsg" style="display: none">
							    <span></span>
							</div>
		
	<div class="row">
		<div class="col-md-5">
			<form:form name="submitDepartment" action="submitDepartment.html" method="POST">
				<section class="panel">
					<header class="panel-heading">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
					</div>	
					<h2 class="panel-title">Create New Department</h2>										
					</header>
					<div style="display: block;" class="panel-body">	                                   										
                    <div class="form-group">
                        <label class="control-label">Enter Department Name<span class="required" aria-required="true">*</span></label>
                        <input type="text" class="form-control" name="departmentName" id="departmentName" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" title="Department name can consist of alphabetical characters and spaces only" required>
                    </div>
                    <div class="form-group">
                          <label class="control-label">Parent Department </label>
                            <select class="form-control" id="departmentShowName" name="departmentShowName" >
                                <option value="">Select...</option>
                                  <c:forEach var="departmentType" items="${departmentShowList}" >
							<option VALUE="${departmentType.departmentCode}">${departmentType.departmentName}</option>
								</c:forEach>
                             </select>
                      </div>
					</div>
					 	
					<footer style="display: block;" class="panel-footer">
						<button class="btn btn-primary" type="submit" id="submitButton" onclick="return validateDepartmentForm();">Submit </button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
				</section>
	    	</form:form>
		</div>
		
		
		<c:if test="${submitResponse ne null}">				
			<c:if test="${submitResponse eq 'Success'}">
				<div class="alert alert-success">
				  <strong>Department successfully created.</strong> 
				</div>

			</c:if>
			<c:if test="${submitResponse eq 'Fail'}">				
				<div class="alert alert-danger">
				  <strong>Department creation failed.</strong> 
				</div>
			</c:if>			
		</c:if>
		<c:if test="${updateResponse ne null}">			
			<c:if test="${updateResponse eq 'Success'}">				
				<div class="alert alert-success">
				  <strong>Department successfully updated.</strong> 
				</div>
			</c:if>
			<c:if test="${updateResponse eq 'Fail'}">
				<div class="alert alert-danger">
				  <strong>Department updation failed.</strong> 
				</div>
			</c:if>		
		</c:if>					
		<c:choose>
			<c:when test="${departmentList eq null || empty departmentList}">			
				<div class="alert alert-danger">
				  <strong>No department created yet.</strong> 
				</div>
			</c:when>
		<c:otherwise>
			<div class="col-md-7">
				<form name="editDepartmentForm" id="editDepartmentForm" action="editDepartment.html" method="post">
					<input type="hidden" name="saveId" id="saveId">	
					<input type="hidden" name="getDepartmentType" id="getDepartmentType">	
                    <section class="panel">
                        <header class="panel-heading">
                            <div class="panel-actions">
                                <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                            </div>

                            <h2 class="panel-title">View / Edit Department</h2>
                        </header>
                        <div class="panel-body">
                            <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                                <thead>
                                    <tr>
                                        <th>Department Name</th>
                                        
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="dept" items="${departmentList}" varStatus="i">
	                                <tr>
										<c:choose>
											<c:when test="${dept.objectId eq 'DEPT-OBJ'}">
											<td>
												<input type="hidden" name="oldDepartmentNames" value="${dept.departmentName}">${dept.departmentName}
												<input type="hidden" name="oldDepartmentNamesforDuplicateChecking" value="${dept.departmentName}">
											</td>
											<td></td>
											</c:when> 
											<c:otherwise>
												<td>
													<input type="hidden" name="oldDepartmentNames${i.index}" value="${dept.departmentName}">
													<input type="hidden" name="oldDepartmentNamesforDuplicateChecking" value="${dept.departmentName}">
													<input type="hidden" class="form-control" id="departmentName${i.index}" name="departmentName${i.index}" value="${dept.departmentName}" readonly>
													${dept.departmentName}
												</td>
												
												<td class="actions">
												 <a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic " onclick = "showDepartmentNameDetails('${dept.departmentName}','${i.index}')"><i class="fa fa-pencil"></i></a>
													<%-- <a href="#" class="hidden on-editing save-row" id="save${i.index}"><i class="fa fa-save"></i></a>
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a> --%>
													<%-- <a href="#" class="on-default edit-row" id="edit${i.index}"><i class="fa fa-pencil"></i></a> --%>
												</td>
											</c:otherwise>
										</c:choose>						
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
											 <h2 class="panel-title">Edit Department</h2> 
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id = "departmentType">
												<thead>
													<tr>
				                                        <th>Department Name</th>
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
													<button id="updateDepartment" class="btn btn-success">Update</button>
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
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/erp/createAndEditDepartment.editable.js"></script>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script src="/cedugenie/js/erp/createDepartment.js"></script>
</body>
</html> 