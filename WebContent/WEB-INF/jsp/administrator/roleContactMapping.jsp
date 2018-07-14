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
			<h2>Role Contact Mapping</h2>	<!--  ADDED BY SAIF 28-03-2018 -->
	</header>
		<div class = "content-padding">
			<c:if test="${successMessage ne null}">
				<div class="alert alert-success" id="successboxmsgbox" >
					<strong>${successMessage}</strong>	
				</div>
			</c:if>
			
			<c:if test="${errorMessage ne null}">
					<div class="alert alert-danger" id="errormsgbox" >
						<strong>${errorMessage}</strong>	
					</div>
			</c:if>
 <div class="row">
 	 <form method="POST" action="addRoleContactMapping.html">
						<div class="col-md-4">
						
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
                                            <select class="form-control" name="roleName" id="roleName" required>
                                                <option value="">Select...</option>
                                                <c:forEach var="role" items="${resource.roleList}">
													<option value="${role.roleCode}">${role.roleName}</option>
												</c:forEach>	
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Resource Type <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" name="resourceTypeName" id="resourceTypeName" required>
                                                <option value="">Select...</option>
                                                <c:forEach var="resourceType" items="${resource.resourceTypeList}">
													<option value="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
												</c:forEach>
                                            </select>
                                        </div> 
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" class="btn btn-primary" id="submit" >Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								
								</section>
								</c:otherwise>
								</c:choose>
                            
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
                                    <table class="table table-bordered table-striped mb-none" id="userTable">
                                        <thead>
                                            <tr>
                                                <th>User Id</th>
                                                <th>Contact Name</th>
                                                <th>Add</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td><input type="text" class="form-control" name="userId" id="userId0" placeholder="" required></td>
                                                <td><input type="text" class="form-control" id="name0" name="name" placeholder="" readonly></td>
                                                <td><a class="mb-xs mt-xs mr-xs modal-basic btn btn-info" href="javascript:addrows()" id="addrow" >Add</a></td>
                                            </tr>
                                            
                                        </tbody>
                                    </table>
                                </div> 
                            </section>
						</div>
						</form>
					</div>
		</div>
				
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/administrator/roleContactMapping.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>

</body>
</html>