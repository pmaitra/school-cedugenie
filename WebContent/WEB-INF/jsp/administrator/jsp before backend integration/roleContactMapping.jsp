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
						 <form method="POST" action="addRoleContactMapping.html">
						 	<c:choose>
								<c:when test="${resource.roleList eq null || resource.roleList.size() eq 0}">
									<div class="errorbox" id="errorbox" style="visibility: visible;">
										<span id="errormsg">No Role Found To Map Contacts</span>	
									</div>						
								</c:when>
								<c:otherwise>	
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Role Contact Mapping</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label">Role Name <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control">
                                                <option value="">Select...</option>
                                                <c:forEach var="role" items="${resource.roleList}">
													<option value="${role.roleCode}">${role.roleName}</option>
												</c:forEach>	
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Resource Type <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control">
                                                <option value="">Select...</option>
                                                <c:forEach var="resourceType" items="${resource.resourceTypeList}">
													<option value="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
												</c:forEach>
                                            </select>
                                        </div> 
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" class="btn btn-primary" id="submit" onclick="return validate();">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								
								</section>
								</c:otherwise>
								</c:choose>
                            </form>
						</div>
						<div class="col-md-8">	
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Role Contact Mapping</h2>
                                </header>
                                <div class="panel-body">
                                    <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                        <thead>
                                            <tr>
                                                <th>User Id</th>
                                                <th>Contact Name</th>
                                                <th>Update</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td><input type="text" class="form-control" name="userId" id="userId0" placeholder=""></td>
                                                <td><input type="text" class="form-control" name="firstname" id="name0" name="name" placeholder=""></td>
                                                <td><a class="mb-xs mt-xs mr-xs modal-basic btn btn-info" href="#modalInfo" id="addrow" onclick="addrows();">Add</a></td>
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