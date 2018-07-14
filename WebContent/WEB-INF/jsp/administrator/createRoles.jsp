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
       } .mb-md{
       	   display: none;
       }
</style>

</head>
<body>
	<header class="page-header">
			<h2>Create New Roles</h2>	<!--  ADDED BY SAIF 28-03-2018 -->
	</header>
		<div class = "content-padding">
			<div class="row">
						<div class="col-md-5">
						 	 <form:form  name="createRoles" id="createRoles" action="addRoles.html" method="POST">
						 	 
						 	 	<c:choose>
									<c:when test="${moduleList eq null || moduleList.size() eq 0}">
										<div class="errorbox" id="errorbox" style="visibility: visible;">
											<span id="errormsg">No Module Found To create Role</span>	
										</div>
									</c:when>
								<c:otherwise>
	
	
								<c:if test="${message ne null && message eq 'SUCCESS' }">
									<div class="alert alert-success">
										<strong>${msg}</strong>
									</div>
								</c:if>
								
								<c:if test="${message ne null && message eq 'FAILED' }">
									<div class="alert alert-danger" id="errormsgbox">
										<strong>${msg}</strong>	
									</div>
								</c:if>
								
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Roles</h2>										
									</header>
									<input type="hidden" class="form-control" id="moduleCodeHidden" name = "moduleCodeHidden" value = "${moduleCode}">
									<input type = "hidden" class="form-control" name="updatedBy" id="updatedBy" value = "${updatedBy}" >
									<div style="display: block;" class="panel-body">
                                        <input type="hidden" id="jsonData">
										<div class="form-group">
                                            <label class="control-label">Module Name<span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" name="moduleCode" id="moduleName" required>
                                                <option value="">Select...</option>
                                                <c:forEach var="module" items="${moduleList}">
													<option value="${module.moduleCode}">${module.moduleName}</option>
												</c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Role Name<span class="required" aria-required="true">*</span></label>
                                            <input type="text" class="form-control" name="roleName" id="roleName" placeholder="" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$"  title="The full name can consist of alphabetical characters and spaces only"required/>
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">Role Description<span class="required" aria-required="true">*</span></label>
                                            <textarea maxlength="140" data-plugin-maxlength="" rows="3" class="form-control" name="roleDescription" id="roleDescription" pattern="^[a-zA-Z0-9]+$" required></textarea>
                                        </div>
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submitButton" >Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </c:otherwise>
                            </c:choose>
                            </form:form>
                            
						</div>
						<div class="col-md-7" id = "roleDiv" style = "display:none">	
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Existing Roles</h2>
                                </header>
                                <div class="panel-body">

                                    <table class="table table-bordered table-striped mb-none" id="datatable-editable" >
                                        <thead>
                                            <tr>
                                                <th>Role Name</th>
                                                <th>Role Description</th>
                                                <th>Actions</th> 
                                            </tr>
                                        </thead>
                                        <tbody id = "roleTableBody">
<!--                                             <tr> -->
<!--                                                 <td>ACADEMICS ADMINISTRATOR</td> -->
<!--                                                 <td><input type="text" class="form-control" name="firstname" placeholder="" value="ACADEMICS ADMINISTRATOR" disabled></td> -->
<!--                                                 <td> -->
<!--                                                     <a class="on-default edit-row" href="#"><i class="fa fa-pencil"></i></a> -->
<!--                                                     <a class="hidden on-editing save-row" href="#"><i class="fa fa-save"></i></a> -->
<!--                                                 </td> -->
<!--                                             </tr> -->
<!--                                             <tr> -->
<!--                                                 <td>ACADEMICS EXECUTIVE</td> -->
<!--                                                 <td><input type="text" class="form-control" name="firstname" placeholder="" value="ACADEMICS EXECUTIVE" disabled></td> -->
<!--                                                 <td> -->
<!--                                                     <a class="on-default edit-row" href="#"><i class="fa fa-pencil"></i></a> -->
<!--                                                     <a class="hidden on-editing save-row" href="#"><i class="fa fa-save"></i></a> -->
<!--                                                 </td> -->
<!--                                             </tr> -->
                                        </tbody>
                                    </table>
                                </div>
                            </section>
						</div>
					</div>
		</div>


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/administrator/createRoles.js"></script>
</body>
</html>