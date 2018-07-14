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
       }  .mb-md{
       	   display: none;
       } 
</style>
</head>
<body>
				<c:if test="${successMessage ne null}">
				<div class="successbox" id="successboxmsgbox" style="visibility: visible;">
					<span>${successMessage}</span>	
				</div>
				</c:if>
				
				<c:if test="${errorMessage ne null}">
						<div class="errorbox" id="errormsgbox" style="visibility: visible;">
							<span>${errorMessage}</span>	
						</div>
				</c:if>
					 <div class="row">
						<div class="col-md-4">
						  <form:form action="functionalityRoleMapping.html" method="post" name="functionalityRoleMapping" id="functionalityRoleMapping">
								<c:choose>
									<c:when test="${moduleList eq null || moduleList.size() eq 0}">
										<div class="errorbox" id="errorbox" style="visibility: visible;">
											<span id="errormsg">No Module Found</span>	
										</div>
									</c:when>
								<c:otherwise>
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Functionality - Role Mapping</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label">Module Name <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control"  name="moduleCode" id="moduleName">
                                                <option value="">Select...</option>
                                                <c:forEach var="module" items="${moduleList}">
													<option value="${module.moduleCode}">${module.moduleName}</option>
												</c:forEach>	
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Role Name <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" name="roleCode" id="roleName">
                                                <option value="">Select...</option>
                                            </select>
                                        </div> 
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submitButton" onclick="return validate();">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
								</c:otherwise>
								</c:choose>
                           </form:form>
						</div>
						
						<c:choose>
							<c:when test="${errorMessage ne null}">
								<div class="errorbox" id="errorbox" style="visibility: visible;">
									<span id="errormsg">${errorMessage}</span>	
								</div>						
							</c:when>
						<c:otherwise>
 						<div class="col-md-8">	
                            <section class="panel">
                            	<form:form action="insertFunctionalityRoleMapping.html" method="post">
                            		
	                                <header class="panel-heading">
	                                    <div class="panel-actions">
	                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
	                                    </div>
 										<c:forEach var="role" varStatus="roll" items="${module.roleList}"> 
	                                    <h2 class="panel-title">Role Name :: ${role.roleName}</h2>
 	                                    </c:forEach> 
	                                </header>	
	                                <div class="panel-body">
	 								<%-- <c:forEach var="role" varStatus="roll" items="${module.roleList}"> --%>
	                                    <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
		                                       
			                                        <thead>
			                                      		                                           
			                                            <tr>
			                                                <th>Functionality Name</th>
			                                                <th>Select All</th>
			                                                <th>View</th>
			                                                <th>Insert</th>
			                                                <th>Update</th>
			                                            </tr>
			                                       
			                                        </thead>
			                                        <tbody>
			                                        	<c:forEach var="functionality"  items="${role.functionalityList}">
				                                            <tr>
				                                                <td>
				                                                	<input type="text" class="form-control" name="functionality${roll.index}" value="${functionality.functionalityName}" disabled>
			                                                	</td>
				                                                <td>
				                                                	<input type="checkbox" id="checkboxExample1" onchange="selection(this);">
			                                                	</td>
				                                                <!-- <td><input type="checkbox" id="checkboxExample1"></td>
				                                                <td><input type="checkbox" id="checkboxExample1"></td>
				                                                <td><input type="checkbox" id="checkboxExample1"></td>  -->
				                                            	 <td>
																	<c:choose>
													    				<c:when test="${functionality.view.equals(true)}">
													        				<input type="checkbox" name="view" checked="checked" value="${role.roleCode}#${functionality.functionalityName}~VIEW">
													    				</c:when>
													    				<c:otherwise>
													       	 				<input type="checkbox" name="view" value="${role.roleCode}#${functionality.functionalityName}~VIEW">
													    				</c:otherwise>
																	</c:choose>									
																</td>
																<td>			
																	<c:choose>
													    				<c:when test="${functionality.insert.equals(true)}">
													        				<input type="checkbox" checked="checked" name="insert" value="${role.roleCode}#${functionality.functionalityName}~INSERT">
													    				</c:when>
													    				<c:otherwise>
													       	 				<input type="checkbox" name="insert" value="${role.roleCode}#${functionality.functionalityName}~INSERT">
													    				</c:otherwise>
																	</c:choose>									
																</td>
																<td>
																	<c:choose>
													    				<c:when test="${functionality.update.equals(true)}">
													        				<input type="checkbox" checked="checked" name="update" value="${role.roleCode}#${functionality.functionalityName}~UPDATE">
													    				</c:when>
													    				<c:otherwise>
													       	 				<input type="checkbox" name="update" value="${role.roleCode}#${functionality.functionalityName}~UPDATE">
													    				</c:otherwise>
																	</c:choose>										
																</td> 
				                                            </tr>
				                                             <!-- <tr> 
				                                                <td><input type="text" class="form-control" name="firstname" placeholder="" value="Student Result Activity Log" disabled></td>
				                                                <td><input type="checkbox" id="checkboxExample1"></td>
				                                                <td><input type="checkbox" id="checkboxExample1"></td>
				                                                <td><input type="checkbox" id="checkboxExample1"></td>
				                                                <td><input type="checkbox" id="checkboxExample1"></td>
				                                            </tr>  -->
			                                            </c:forEach>
			                                        </tbody>
	                                    </table>
	                                </div> 
	                                                             
	                                <footer style="display: block;" class="panel-footer">
	                                    <button class="btn btn-primary" type="submit">Submit </button>
	                                    <button type="reset" class="btn btn-default">Reset</button>
	                                </footer>
	                               
                                </form:form>
                            </section>
						</div> 
						</c:otherwise>
						</c:choose>
					</div>	

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>