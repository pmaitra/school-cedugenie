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

										<h2 class="panel-title">Create Task</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        <div class="row">
	                                        <div class="col-md-6">
												 <div class="form-group">
		                                            <label class="control-label">Task Name <span class="required" aria-required="true">*</span></label>
		                                            <input type="text" class="form-control"  id="jobTypeName" name="jobTypeName" placeholder=""  pattern="^[a-zA-Z '.,-/]+$"  title="The full name can consist of alphabetical characters and spaces only" required>
		                                        </div>
		                                     </div>
		                                     <div class="col-md-6">
			                                     <div class="form-group">
			                                         <label class="control-label">Approval Required:<span class="required" aria-required="true">*</span></label>
			                                         <select class = "form-control" id = "approvalRequired" name = "approvalRequired" required>
			                                           		<option value = "">SELECT</option>
			                                           		<option value = "YES">YES</option>
			                                           		<option value = "NO">NO</option>
			                                          </select>
	                                           </div>
                                        	</div>
                                        </div>
                                        <div class="row">
                                        	 <div class="col-md-10">
		                                        <div class="form-group">
		                                            <label class="control-label">Task Description<span class="required" aria-required="true">*</span></label>
		                                            <textarea maxlength="140" data-plugin-maxlength="" rows="3" class="form-control"  id = "jobTypeDesc"  name = "jobTypeDesc" pattern="^[a-zA-Z '.,-/]+$"  title="The description can consist of alphabetical characters  only" required></textarea>
		                                        </div>
		                                      </div>
                                        </div>
                                        <div class="row">
                                        <hr>
                                        	<div class="col-md-4">
												 <div class="form-group">
												 	<label>Finance :    <br></label>
												 	<input type="checkbox" id="isFinance" name="isFinance" value="finance" >
												 </div>
											</div>
										</div>
                                        <div class="row">
                                        	<div class="col-md-4">
												 <div class="form-group">
												 	<label>Linked     :    <br></label>
												 	<input type="checkbox" id="isLinked" name="isLinked"  value="linked" onclick = "visibleLink()">
												 </div>
											</div>
											<div class="col-md-4" id="linkDiv" style="display:none">
												 <div class="form-group">
												 	<label>Link:  <br></label>
												 	 <select class="form-control" name="functionality" id="linkName">
				                                     	<option value="">Select...</option>
				                                     	<c:forEach var="fun"  items="${functionality}">
			                                     			<option value="${fun.functionalityName}">${fun.functionalityName}</option>
			                                     		</c:forEach>
				                                     </select>
												 </div>
												 <input type="radio" id="add" name="action"  value="ADD" checked>ADD
												 <input type="radio" id="edit" name="action"  value="EDIT" >EDIT
											</div>
											<div class="col-md-4" id="noteDiv" style="display:none">
												 <div class="form-group">
												 	<label class="control-label">Note<span class="required" aria-required="true">*</span></label>
		                                            <textarea maxlength="80" data-plugin-maxlength="" rows="3" class="form-control"  id = "note"  name = "note" pattern="^[a-zA-Z '.,-/]+$"  title="The description can consist of alphabetical characters  only" ></textarea>
												 </div>
											</div>
											<hr>
                                        </div>
                                        
                                        <hr>
                                         <div class="row">
                                        	<div class="col-md-4">
                                        		<label>Task Assignee :   <span class="required" aria-required="true">*</span><br></label>
											 	 <select class="form-control" name="taskAssignee" id="taskAssignee" onchange="checkTaskAssignee();" required>
			                                     	<option value="">Select...</option>
			                                     	<option value="designation">Designation</option>
			                                     	<option value="reportingManager">Reporting Manager</option>
			                                     	<option value="classTeacher">Class Teacher</option>
			                                     </select>
                                        	</div>
                                        </div>
                                        <div class="row">
                                        	<div id="departmentDiv" class="col-md-4" style="display:none">
                                        		<label>Department:  <br></label>
											 	 <select class="form-control" name="department" id="department">
			                                     	<option value="">Select...</option>
			                                     	<c:forEach var="dept"  items="${departmentList}">
			                                     		<option value="${dept.departmentCode}">${dept.departmentName}</option>
			                                     	</c:forEach>
			                                     </select>
                                        	</div>
                                        	<div id="designationDiv" class="col-md-4" style="display:none">
                                        		<label>Designation:  <br></label>
											 	 <select class="form-control" name="designation" id="designation">
			                                     	<option value="">Select...</option>
			                                     </select>
                                        	</div>
                                        	<div id="designationLevelDiv" class="col-md-4" style="display:none">
                                        		<label>Designation Level:  <br></label>
											 	 <select class="form-control" name="designationLevel" id="designationLevel">
			                                     	<option value="">Select...</option>
			                                     </select>
                                        	</div>
                                        </div>
                                     	
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id="submit" type = "submit"  >Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 col-md-offset-2">	
						<%-- <c:choose>
							<c:when test="${jobDetailsList eq null}">		
									<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
										<span id="infomsg">No Job Found</span>	
									</div>		
							</c:when>
						<c:otherwise> --%>
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Existing Tasks</h2>
                                </header>
                                <div class="panel-body">

                                   <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                            <thead>
                                                <tr>
                                                    <th>Task Name</th>
                                                    <th>Task Description</th>
                                                    <th>Approval Required</th>
                                                    <th>Actions</th> 
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<c:forEach var="job"  items="${jobDetailsList}">
                                            	
                                               		 <tr>
                                                		 <td>${job.jobTypeName}</td>
														 <td>${job.jobTypeDesc}</td>
														 <td>${job.approvalRequired}</td>
														 <td><a href="taskDetailsAgainstTaskCode.html?taskCode=${job.jobTypeCode}" class="mb-xs mt-xs mr-xs modal-basic btn btn-info">Details</a></td>
                                                    	 
                                               		
                                               		 </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                </div>
                            </section>
                           <%--  </c:otherwise>
                            </c:choose> --%>
						</div>
					</div>	
				</div>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/administrator/createNewJob.js"></script>
</body>
</html>