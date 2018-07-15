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
</head>
<body>


 					<div class="row">
						<div class="col-md-4">
						  <form:form method="POST" id="serviceType" name="serviceType" action="serviceType.html" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Service Type</h2>										
									</header>
									<c:forEach var="serviceTypeList" items="${serviceTypeList}">
									<input type="hidden" name="allServiceType" value="${serviceTypeList.ticketServiceName}" />
									</c:forEach>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label">Service Category <span class="required" aria-required="true">*</span></label>
                                            <input type="text" class="form-control" name="ticketServiceName" id="ticketServiceName" placeholder="">
                                        </div>                                       
                                        
										<div class="form-group">
                                            <label class="control-label">Department <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" name="department.departmentCode" id="department">
                                                <option value="">Select...</option>
                                                <c:if test="${departmentFromDB != null}">
													<c:forEach var="dept" items="${departmentFromDB}" >
														<option value="${dept.departmentCode}">${dept.departmentName}</option>
													</c:forEach>
												</c:if>
                                            </select>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label">Service Owner <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" name="ticketServiceOner.userId" id="userId">
                                                <option value="">Please select</option>
                                            </select>
                                        </div>
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit"  onclick = "return checkServiceName()">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
						<div class="col-md-8">						  
						
                        <section class="panel">
                        	<c:choose>
								<c:when test="${serviceTypeList eq null || serviceTypeList.size() eq 0}">
									<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
										<span id="infomsg">No Service Type created Yet. Please Create New.</span>	
									</div>						
								</c:when>
							<c:otherwise>
                            <form:form id="editServiceType" name="editServiceType" action="updateServicetype.html" method="post">
							<input type="hidden" id="saveId" name="saveId">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Service Type</h2>
							</header>
							
							<div class="panel-body">
								
								<table class="table table-bordered table-striped mb-none" id="datatable-editable">
									<thead>
										<tr>
                                            
											<th>Service Name</th>
											<th>Service Owner</th>
                                            <th>Department</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="serviceTypeList" items="${serviceTypeList}" varStatus="rowCount">
										<tr>											
											<td>
												<input type="hidden" name="oldService${rowCount.index}" value="${serviceTypeList.ticketServiceName}" >
												<input type="text" class="form-control" name="newService${rowCount.index}" value="${serviceTypeList.ticketServiceName}" id="serviceName${rowCount.index}" readonly>
											</td>
											<td>
                                                <select class="form-control" readonly id="serviceOwner${rowCount.index}" name="newOwner${rowCount.index}">
                                                    <c:forEach var="userIdList" items="${serviceTypeList.resourceList}" >
														<c:if test="${userIdList.userId eq serviceTypeList.ticketServiceOner.userId}">
															<option value="${userIdList.userId}" >${userIdList.userId}</option>
														</c:if>
													</c:forEach>
													 <c:forEach var="userIdList" items="${serviceTypeList.resourceList}" >
														<c:if test="${userIdList.userId ne serviceTypeList.ticketServiceOner.userId}">
															<option value="${userIdList.userId}">${userIdList.userId}</option>
														</c:if>
													</c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <select class="form-control" readonly onchange="check(this);" id="department${rowCount.index}" name="newDepartment${rowCount.index}">
                                                   <c:forEach var="department" items="${departmentFromDB}" >
														<c:if test="${department.departmentCode eq serviceTypeList.department.departmentCode}">
															<option value="${department.departmentCode}" >${department.departmentName}</option>
														</c:if>
													</c:forEach>
													
													 <c:forEach var="department" items="${departmentFromDB}" >
														<c:if test="${department.departmentCode ne serviceTypeList.department.departmentCode}">
															<option value="${department.departmentCode}" >${department.departmentName}</option>
														</c:if>
													</c:forEach>
                                                </select>
                                            </td>
											<td class="actions">
												<a href="#" class="hidden on-editing save-row" id="save${rowCount.index}"><i class="fa fa-save"></i></a>
												<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
												<a href="#" class="on-default edit-row" id="edit${rowCount.index}"><i class="fa fa-pencil"></i></a>
											</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							</form:form>
							</c:otherwise>
							</c:choose>
						</section>
						</div>
					</div>	
					
					<div id="dialog" class="modal-block mfp-hide">
						<section class="panel">
							<header class="panel-heading">
								<h2 class="panel-title">Are you sure?</h2>
							</header>
							<div class="panel-body">
								<div class="modal-wrapper">
									<div class="modal-text">
										<p>Are you sure that you want to delete this row?</p>
									</div>
								</div>
							</div>
							<footer class="panel-footer">
								<div class="row">
									<div class="col-md-12 text-right">
										<button id="dialogConfirm" class="btn btn-primary">Confirm</button>
										<button id="dialogCancel" class="btn btn-default">Cancel</button>
									</div>
								</div>
							</footer>
						</section>
					</div>




<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/ticketing/serviceType.js"></script>
<script src="/cedugenie/js/ticketing/servicetype.editable.js"></script>

<script type="text/javascript">
	$(document).ready(function() { 
		$("#department").change(function() {
			$.ajax({
		        url: '/cedugenie/getDepartmentWiseUserList.html',
		        dataType: 'json',
		        data: "department=" + ($(this).val()),
		        success: function(dataDB) {
		        var x=document.getElementById("userId");
		        	removeOption(x);
		        	if(dataDB != "null" && dataDB !=""){
		        		document.getElementById("userId").removeAttribute("disabled", "disabled");
		        		var arr = dataDB.split(";");	
						for (var i=0;i<arr.length;i++)	{        									
							$("#userId").append($("<option></option>").val(arr[i]).html(arr[i]));
						}							
					}
		       }
			});	      
		});
	});
	function removeOption(x){
		for(var i=x.length;i>0;i--){
			x.remove(i);
		}
	}
	function check(abc) {
		var p=abc.id;
				
		p=p.replace("department","serviceOwner");
		$.ajax({
	        url: '/cedugenie/getDepartmentWiseUserList.html',
	        dataType: 'json',
	        data: "department=" + (abc.value),
	        success: function(dataDB) {
	        	 var x=document.getElementById(p);
	        	removeOption(x);
	        	x.remove(0);
	        	if(dataDB != "null" && dataDB !=""){
	        		var arr = dataDB.split(";");	
					for (var i=0;i<arr.length;i++)	{
						$("#"+p).append($("<option></option>").val(arr[i]).html(arr[i]));
					}							
				}else{
					$("#"+p).append($("<option></option>").val('').html('Select..'));
					alert("No Service Owner found.");
				}
	       }
		});
		
	}
	function makeEditable(rowId){
		rowId=rowId.replace("edit","");
		document.getElementById("serviceName"+rowId).removeAttribute("readonly");
		document.getElementById("serviceOwner"+rowId).removeAttribute("readonly");
		document.getElementById("department"+rowId).removeAttribute("readonly");
	};
	
	function saveData(rowId){
		rowId=rowId.replace("save","");
		document.getElementById("saveId").value=rowId;
		document.editServiceType.submit();
	};
</script>



</body>
</html>