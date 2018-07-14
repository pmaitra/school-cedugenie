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
		document.getElementById("textDesig"+rowId).removeAttribute("readonly");	
		document.getElementById("designationName"+rowId).removeAttribute("disabled");
	};
	
	function saveData(rowId){
		rowId=rowId.replace("save","");	
		document.getElementById("saveId").value=rowId;	
		var validateStatus = validateEditDesignationForm("textDesig"+rowId);
		if(validateStatus == true){
			document.editDesignationForm.submit();
		}		
	};
</script>
</head>
<header class="page-header">	 <!-- Added by Saif 21-03-2018 -->
	<h2>Map Department To Head</h2>
</header>
<body>
			<c:if test="${updatestatus eq 'Success'}">
				<div class="alert alert-success">
					<strong>DepartmentHead successfully inserted.</strong>	
				</div>
			</c:if>
			<c:if test="${updatestatus eq 'Fail'}">
				<div class="alert alert-danger">
					<strong>Insertion failed.</strong>
				</div>
			</c:if>	
			<c:if test="${status eq 'Success'}">
				<div class="alert alert-success">
					<strong>DepartmentHead successfully updated.</strong>	
				</div>
			</c:if>
			<c:if test="${status eq 'Fail'}">
				<div class="alert alert-danger">
					<strong>Updation failed.</strong>
				</div>
			</c:if>		
		
	<div class="row">
		<div class="col-md-5">
		  	<form:form name="submitDepartmentHeadDetails" id="submitDepartmentHeadDetails" action="submitDepartmentHeadDetails.html" method="POST">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>

						<h2 class="panel-title">Map Department To Head</h2>										
					</header>
					<div style="display: block;" class="panel-body">
                                    
                                    <div class="form-group">
                                        <label class="control-label">Department Name: <span class="required" aria-required="true">*</span></label>
                                        <select class="form-control" id="departmentCode" name="departmentCode" >
                                            <option value="">Select...</option>
                                            <c:forEach var="department" items="${departmentList}" >
												<option VALUE="${department.departmentCode}">${department.departmentName}</option>
											</c:forEach>
                                        </select>
                                    </div>	
                                    <div class="form-group">
                                    <label class="control-label">Resource Type: <span class="required" aria-required="true">*</span></label>
                                         <select class="form-control" name="resourceTypeName" id="resourceTypeName${i.index}"  onchange="getUserDetails('${i.index}');" >
                                               <option value="">Select...</option>
                                               <c:forEach var="resourceType" items="${resourceTypeList}">
												<option value="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
											</c:forEach>	
                                           </select>
                               		</div>									
                                    <div class="form-group">
                                    <label class="control-label">HOD: <span class="required" aria-required="true">*</span></label>
                                          <select class="form-control" id="resourceUserId${i.index}" name="resourceUserId" >
                                         	<option value="0">Select</option>
                                         </select>
		                           </div>
                                        
					</div>
					<footer style="display: block;" class="panel-footer">
						<button class="btn btn-primary" type="submit" id="submitButton" onclick="return validateDesignationForm();">Submit </button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
				</section>
        	</form:form>
        	<div class="alert alert-warning" id="javascriptmsg" style="display: none">
			  <span></span>	
			</div>
		</div>
		
		
			
		
		<c:choose>
		<c:when test="${departmentList eq null || empty departmentList}">
			<div class="alert alert-danger">
				<strong>No department created yet.</strong>	
 			</div>
		</c:when>	
		<c:otherwise>
		
		<div class="col-md-7">	
			
			<input type="hidden" name="saveId" id="saveId">
                        <section class="panel">
                            <header class="panel-heading">
                                <div class="panel-actions">
                                    <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                </div>

                                <h2 class="panel-title">View / Edit Department HOD </h2>
                            </header>
                            <div class="panel-body">

                                <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                                    <thead>
                                        <tr>
                                            <th>Department</th>
                                            <th>Resource Type</th>
                                            <th>HOD</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="department" items="${deapartmentDetailsList}" varStatus="i">
                                        <tr class="gradeX">
											<td>
												<%-- <input type="text" class="form-control" id="textDesig${i.index}" name="designationName${i.index}" placeholder="" value="${designation.designationName}" readonly>
												<input type="hidden" name="oldDesignationNames${i.index}" value="${designation.designationName}">
												<input type="hidden" name="oldDesignationNamesForDuplicateCheck" value="${designation.designationName}"> --%>
												<input type="hidden" class="form-control" name="departmentCode" id="departmentCode${i.index}"  value="${department.departmentCode}" /> 
												${department.departmentCode }
												 
											</td>	
											<td>
												 <input type="text" class="form-control" name="departmentResourceType" id="departmentResourceType${i.index}"  value="${department.departmentDescription}" readonly/>
												 <%-- <input type="hidden" class="form-control" name="oldDepartmentHOD" id="oldDepartmentHOD"  value="${department.departmentDescription}"/>
		                                          --%><input type="hidden" id="select${i.index}" value="${department.departmentDescription}">
		                                            <select class="form-control" name="resourceTypeName" id="resourceTypeNames${i.index}"  onchange="getUsersDetails('${i.index}');" style="display:none" >
		                                                <option value="">Select...</option>
		                                                <c:forEach var="resourceType" items="${resourceTypeList}">
															<option value="${resourceType.resourceTypeCode}"  >${resourceType.resourceTypeName}</option>
														</c:forEach>	
		                                            </select>
		                                    </td>
		                                    <td>
		                                    		<input type="text" class="form-control" name="departmentResource" id="departmentResource${i.index}"  value="${department.departmentResource}" readonly/>
	                                        		<!-- <div id="userDetails" class="form-group" style="display:none"> -->
		                                        		<%-- <input type="text" class="form-control" name="departmentResource" id="departmentResource${i.index}"  value="${department.departmentResource}"  /> --%>
		                                              	
				                                            <select class="form-control" id="resourceUserIds${i.index}" name="resourceUserId"   style="display:none">
				                                            	<option value="0">Select</option>
				                                            </select>
			                                        <!-- </div> -->
		                                    </td>
											
		                                        <%-- <a href="#" class="hidden on-editing save-row" id="save${i.index}"><i class="fa fa-save"></i></a>
												<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
												<a href="#" class="on-default edit-row" id="edit${i.index}"><i class="fa fa-pencil"></i></a> --%>
												<td><input type="button" class="btn btn-primary" id="edit${i.index}" value="EDIT" onclick="validatingDetails('${i.index}')">
												<a class="btn btn-warning" id="save${i.index}" onclick="getDepartmentResource('${i.index}')" style="display:none">SAVE</a></td>
											
		                                  
										</tr>
                                     </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </section>
                       
		</div>
		</c:otherwise>
		</c:choose>
	</div>	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/erp/mapDepartmentToHead.js"></script>
</body>
</html>