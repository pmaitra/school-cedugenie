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
						<div class="col-md-4">
						 <form method="POST" action="insertAccessTypeContactMapping.html">
						 	<c:choose>
								<c:when test="${resource.accessTypeList eq null || resource.accessTypeList.size() eq 0}">
									<div class="errorbox" id="errorbox" style="visibility: visible;">
										<span id="errormsg">No Contact Access Type Mapping Found</span>	
									</div>						
								</c:when>
								<c:when test="${message ne null}">
									<div class="successbox" id="successbox" style="visibility: visible;">
										<span id="errormsg">${message}</span>	
									</div>						
								</c:when>
							<c:otherwise>
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Access Type - Contact Mapping</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label">Access Type Name <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" name="accessTypeCode" id="accessTypeName">
                                                <option value="">Select...</option>
                                                <c:forEach var="accessType" items="${resource.accessTypeList}">
													<option value="${accessType.accessTypeCode}">${accessType.accessTypeName}</option>
												</c:forEach>	
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Resource Type <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" name="resourceTypeCode" id="resourceTypeName">
                                                <option value="">Select...</option>
                                                <c:forEach var="resourceType" items="${resource.resourceTypeList}">
													<option value="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
												</c:forEach>	
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">User Id <span class="required" aria-required="true">*</span></label>
                                            <input type="text" class="form-control"  placeholder="" name="userId" id="userId">
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">Contact Name</label>
                                            <input type="text" class="form-control" name="name" id="name"  placeholder="">
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
						<div class="col-md-8">	
                            <form:form action="accessTypeContactMappingSearch.html" method="POST">	
                                <section class="panel">
                                    <header class="panel-heading">
                                        <div class="panel-actions">
                                            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                        </div>

                                        <h2 class="panel-title">Access Type Contact Mapping List</h2>
                                    </header>
                                    <div class="panel-body">
                                        <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                            <thead>
                                                <tr>
                                                    <th>User Id</th>
                                                    <th>Contact Name</th>
                                                    <th>Access Type</th>
                                                    <th>Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<c:forEach var="resource"  items="${pagedListHolder.pageList}">	
                                               		 <tr>
                                                		 <td>${resource.userId}</td>
														 <td>${resource.name}</td>
														 <td>${resource.accessType.accessTypeName}</td>
                                                   	     <td><a class="on-default remove-row" href="#" id="submit"><i class="fa fa-trash-o"></i></a></td>
                                               		 </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div> 
                                </section>
                            </form:form>
						</div>
					</div>	
			
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>