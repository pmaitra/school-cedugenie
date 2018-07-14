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
						<div class="col-md-12">
						  <form method="POST" action="insertUserGroup.html">
						  <c:choose>
							<c:when test="${resourceTypeList eq null || resourceTypeList.size() eq 0}">
								<div class="errorbox" id="errorbox" style="visibility: visible;">
									<span id="errormsg">No Resource Type Found To Create User Group</span>	
								</div>						
							</c:when>
							<c:otherwise>
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create User Group</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                        
										<div class="col-md-5">
                                            <div class="form-group">
                                                <label class="control-label">Resource Type <span class="required" aria-required="true">*</span></label>
                                                <select class="form-control" name="resourceTypeCode" id="resourceTypeName">
                                                    <option value="">Select...</option>
                                                   		 <c:forEach var="resourceType" items="${resourceTypeList}">
															<option value="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
														</c:forEach>	
                                                </select>
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">User Group Name <span class="required" aria-required="true">*</span></label>
                                                <input type="text" class="form-control" name="userGroupName" id="userGroupName" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">User Group Description <span class="required" aria-required="true">*</span></label>
                                                <textarea maxlength="140" data-plugin-maxlength="" rows="3" class="form-control" name="userGroupDesc" id="userGroupDesc"></textarea>
                                            </div>
                                        </div>
                                        <div class="col-md-7">
                                            <table class="table table-bordered table-striped mb-none">
                                                <thead>
                                                    <tr>
                                                        <th>User Id</th>
                                                        <th>Contact Name</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>											
                                                        <td><input type="text" class="form-control" name="userName" id="userId0" placeholder=""></td>
                                                        <td><input type="text" class="form-control" id="name0" name="name" placeholder=""></td>
                                                        <td>
                                                            <a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic btn btn-info" id="addrow" onclick="addrows();">Add</a>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
							</c:otherwise>
						</c:choose>
                      </form>
					</div>
                         <div class="col-md-12">
						  <form:form action="getUserGroupDetails.html" method="POST">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">User Group List</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                        
                                        <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                            <thead>
                                                <tr>
                                                    <th>Group Name</th>
                                                    <th>Created On</th>
                                                    <th>Group Description</th>
                                                    <th>Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<c:forEach var="userGroup"  items="${pagedListHolder.pageList}">
	                                                <tr>
	                                                    <td>${userGroup.userGroupName}</td>
	                                                    <td>${userGroup.status}</td>
	                                                    <td>${userGroup.userGroupDesc}</td>
	                                                    <td>
	                                                        <a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic btn btn-info">Details</a>
	                                                        <a href="#" class="mb-xs mt-xs mr-xs modal-basic btn btn-danger">Delete</a>
	                                                    </td>
	                                                </tr>
	                                              </c:forEach>
                                               
                                            </tbody>
                                        </table>
                                        <div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
                                            <section class="panel">
                                                <header class="panel-heading">
                                                	<c:if test="${userGroup.userGroupName ne null}">
                                                    	<h2 class="panel-title">User Group Name - ${userGroup.userGroupName}</h2>
                                                    </c:if>
                                                </header>
                                                <div class="panel-body">
                                                    <table class="table table-bordered table-striped mb-none">
                                                        <thead>
                                                            <tr>
                                                                <th>Group Name</th>
                                                                <th>User ID</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                        	<c:forEach var="resource" varStatus="roll" items="${userGroup.resourceList}">
                                                            <tr>
                                                                <td>${resource.name}</td>
                                                                <td>${resource.userId}</td>
                                                            </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <footer class="panel-footer">
                                                    <div class="row">
                                                        <div class="col-md-12 text-right">
                                                            <button class="btn btn-info modal-dismiss">OK</button>
                                                        </div>
                                                    </div>
                                                </footer>
                                            </section>
                                        </div>
									</div>
                                    
								</section>                                  
                            </form:form>
						</div>
					</div>	

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>
