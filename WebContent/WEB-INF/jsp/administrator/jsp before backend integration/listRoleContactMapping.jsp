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
								<c:choose>
										<c:when test="${pagedListHolder eq null}">
											<div class="errorbox" id="errorbox" style="visibility: visible;">
													<span id="errormsg">No Role Contact Mapping List Found</span>	
											</div>				
										</c:when>
									<c:otherwise>
										  <form:form action="searchRoleContactmapping.html" method="POST">
												<section class="panel">
													<header class="panel-heading">
														<div class="panel-actions">
															<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
														</div>
				
														<h2 class="panel-title">Search Role Contact Mapping</h2>										
													</header>
													<div style="display: block;" class="panel-body">                                       
														
				                                        <div class="col-md-4">
				                                            <div class="form-group">
				                                                <label class="control-label">Search Type</label>
				                                                <select class="form-control" name="query" id="query" >
				                                                    <option value="">Select...</option>
				                                                    <option value="contactName">Contact Name</option>
																	<option value="roleName">Role Name</option>
																	<option value="userId">User ID</option>	
				                                                </select>
				                                            </div>
				                                        </div>
				                                        <div class="col-md-4">
				                                            <div class="form-group">
				                                                <label class="control-label">Search</label>                                                
				                                                <input type="text" class="form-control" name="data" id="data"  placeholder="Search" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='Search';}">
				                                            </div>
				                                        </div>
				                                            
													</div>
													<footer style="display: block;" class="panel-footer">
														<button class="btn btn-primary" type="submit" name=""  onclick="return validateSearch('query','data','warningbox','warningmsg');">Search </button>
														<button type="reset" class="btn btn-default">Reset</button>
													</footer>
												</section>
				                           </form:form>
				                           </c:otherwise>
				                        </c:choose>
							</div>
						<div class="col-md-12">						  
								
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Role Contact Mapping List </h2>
							</header>
							<div class="panel-body">
                                <div class="row">
                                <c:forEach var="role" items="${pagedListHolder.pageList}">
								<div class="col-md-4">
                                    <div class="well well-sm info no-margin">
										<div class="radio-custom">
                                            <input type="radio" name="radioExample" id="radioExample1">
                                            <label for="radioExample1" name="roleName" value="${role.roleCode}">Role Name :: ${role.roleName}</label>
                                        </div>
									</div>
                                    <table class="table table-bordered table-striped mb-none">
                                        <thead>
                                            <tr>
                                                <th>User ID</th>
                                                <th>Name</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="resource" items="${role.resourceList}">	
                                            <tr>
                                                <td><input type="text" class="form-control" name="firstname" placeholder="" value="${resource.userId}" disabled></td>
                                                <td><input type="text" class="form-control" name="firstname" placeholder="" value="${resource.name}" disabled></td>
                                            </tr>
                                         </c:forEach>
                                        </tbody>
                                    </table>								    
                                </div>
                               
                                </c:forEach>

                                </div>
                                
							</div>
                            <footer style="display: block;" class="panel-footer">
                                <button class="btn btn-primary" id="submit">Edit </button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </footer>    
						    </section>                            
						</div>
					</div>	
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>