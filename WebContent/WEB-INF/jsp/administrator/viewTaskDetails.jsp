<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listBookPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />


<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

	<header class="page-header">
			<h2>Task Configuration</h2>
		</header>

	<div class="content-padding">
			<c:if test="${insertStatus eq 'success'}">
				<div class="alert alert-success">
					<strong>${msg}</strong>
				</div>
			</c:if>
			
			<c:if test="${insertStatus eq 'fail'}">
				<div class="alert alert-danger">
					<strong>${msg}</strong>
				</div>
			</c:if>

	
			
			 <div class="row">
					<div class="col-md-8 col-md-offset-2">
						  <form method="POST" id="createNewJob" name="createNewJob" action="createNewTask.html" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Task Details</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        <div class="row">
	                                        <div class="col-md-6">
												 <div class="form-group">
		                                            <label class="control-label"><b>Task Name: </b></label> 
		                                         	<label class="control-label">${taskDetails.jobTypeName}</label>
		                                         </div>
		                                     </div>
		                                     <div class="col-md-6">
			                                     <div class="form-group">
			                                         <label class="control-label"><b>Approval Required :</b></label>
			                                        <label class="control-label">${taskDetails.approvalRequired}</label>
	                                           </div>
                                        	</div>
                                        </div>
                                        <div class="row">
                                        	 <div class="col-md-10">
		                                        <div class="form-group">
		                                            <label class="control-label"><b>Task Description :</b></label>
		                                            <label class="control-label">${taskDetails.jobTypeDesc}</label>
		                                        </div>
		                                      </div>
                                        </div>
                                        <div class="row">
                                       
                                        	
                                        	<c:if test="${taskDetails.jobTypeObjectId eq 'checked'}">
                                        	 <hr>
	                                        	<div class="col-md-4">
													 <div class="form-group">
													 	<label><b>Finance :    </b><br></label>
													 	<input type="checkbox" id="isFinance" name="isFinance" checked/>
													 </div>
												</div>
											</c:if>
										</div>
                                        <div class="row">
                                        	<c:if test="${taskDetails.category eq 'checked'}">
	                                        	<div class="col-md-4">
													 <div class="form-group">
													 	<label><b>Linked     :   </b> <br></label>
													 	<input type="checkbox" id="isLinked" name="isLinked" checked>
													 </div>
												</div>
												
												<div class="col-md-4" id="linkDiv" style="display:block">
													 <div class="form-group">
													 	<label><b>Link :  </b><br></label>
													 	<label class="control-label">${taskDetails.functionality}</label>
													 </div>
												</div>
												<div class="col-md-4" id="noteDiv" style="display:block">
													 <div class="form-group">
													 	<label class="control-label"><b>Note :</b></label>
			                                             <label class="control-label">${taskDetails.note}</label>
			                                         </div>
												</div>
												<hr>
											</c:if>
                                        </div>
                                        <hr>
                                         <div class="row">
                                        	<div class="col-md-4">
                                        		<label><b>Task Assignee :  </b></label>
											 	<label class="control-label">${taskDetails.taskAssignee}</label>
                                        	</div>
                                        </div>
                                        <c:if test="${taskDetails.taskAssignee eq 'designation'}">
	                                        <div class="row">
	                                        	<div id="departmentDiv" class="col-md-4">
	                                        		<label><b>Department:  </b><br></label>
												 	 <label class="control-label">${taskDetails.department}</label>
	                                        	</div>
	                                        	<div id="designationDiv" class="col-md-4">
	                                        		<label><b>Designation:  </b><br></label>
												 	<label class="control-label">${taskDetails.designation}</label>
	                                        	</div>
	                                        	<div id="designationLevelDiv" class="col-md-4">
	                                        		<label><b>Designation Level:  </b><br></label>
												 	 <label class="control-label">${taskDetails.designationLevel}</label>
	                                        	</div>
	                                        </div>
                                     	</c:if>
									</div>
									<footer style="display: block;" class="panel-footer">
										<a href="createNewTask.html" class="mb-xs mt-xs mr-xs btn btn-info">Back</a>
                                                    	 
									</footer>
								</section>
                            </form>
						</div>
					</div>
					
				</div>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/administrator/createNewJob.js"></script>
</body>
</html>