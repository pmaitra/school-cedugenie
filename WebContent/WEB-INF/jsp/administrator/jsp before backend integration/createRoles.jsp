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

					<div class="row">
						<div class="col-md-5">
						 	 <form:form action="addRoles.html" method="post" name="createRoles" id="createRoles">
						 	 
						 	 	<c:choose>
									<c:when test="${moduleList eq null || moduleList.size() eq 0}">
										<div class="errorbox" id="errorbox" style="visibility: visible;">
											<span id="errormsg">No Module Found To create Role</span>	
										</div>
									</c:when>
								<c:otherwise>
	
	
								<c:if test="${message ne null && message eq 'SUCCESS' }">
									<div class="successbox" id="successboxmsgbox" style="visibility: visible;">
										<span>Role Created Successfully</span>	
									</div>
								</c:if>
								
								<c:if test="${message ne null && message eq 'FAILED' }">
									<div class="errorbox" id="errormsgbox" style="visibility: visible;">
										<span>Failed To Create Role</span>	
									</div>
								</c:if>
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Roles</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label">Module Name <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" name="moduleCode" id="moduleName">
                                                <option value="">Select...</option>
                                                <c:forEach var="module" items="${moduleList}">
													<option value="${module.moduleCode}">${module.moduleName}</option>
												</c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Role Name <span class="required" aria-required="true">*</span></label>
                                            <input type="text" class="form-control" name="roleName" id="roleName" placeholder="">
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">Role Description</label>
                                            <textarea maxlength="140" data-plugin-maxlength="" rows="3" class="form-control" name="roleDescription" id="roleDescription"></textarea>
                                        </div>
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submitButton" onclick="return validateRole();">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </c:otherwise>
                            </c:choose>
                            </form:form>
                            
						</div>
						<div class="col-md-7">	
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Existing Roles</h2>
                                </header>
                                <div class="panel-body">

                                    <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                        <thead>
                                            <tr>
                                                <th>Role Name</th>
                                                <th>Role Description</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>ACADEMICS ADMINISTRATOR</td>
                                                <td><input type="text" class="form-control" name="firstname" placeholder="" value="ACADEMICS ADMINISTRATOR" disabled></td>
                                                <td>
                                                    <a class="on-default edit-row" href="#"><i class="fa fa-pencil"></i></a>
                                                    <a class="hidden on-editing save-row" href="#"><i class="fa fa-save"></i></a>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>ACADEMICS EXECUTIVE</td>
                                                <td><input type="text" class="form-control" name="firstname" placeholder="" value="ACADEMICS EXECUTIVE" disabled></td>
                                                <td>
                                                    <a class="on-default edit-row" href="#"><i class="fa fa-pencil"></i></a>
                                                    <a class="hidden on-editing save-row" href="#"><i class="fa fa-save"></i></a>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </section>
						</div>
					</div>	
					



<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>