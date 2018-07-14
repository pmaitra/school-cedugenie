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
       }.mb-md{
       	   display: none;
       }
</style>
</head>
<body>
	<header class="page-header">
			<h2>Create Task Status</h2>
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
 					<div class="row">
						<div class="col-md-6 col-md-offset-3">
						  <form:form method="POST" id="createTaskStatus" name="createTaskStatus" action="createTaskStatus.html" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Task Status</h2>										
									</header>
									
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label"> Task Status :<span class="required" aria-required="true">*</span></label>
                                         	 <input type="text" class="form-control" name="taskStatus" id="taskStatus" placeholder="" required>
                                        </div>                                       
                                         <div  class="form-group" id = "approvalType" >
                                            <label class="control-label">Task Type:<span class="required" aria-required="true">*</span></label>
                                             
                                                   <input type="radio"  id = "serial"  name="approval" value="APPROVAL" checked> Approval  
                                               
                                                   <input type="radio" id = "parallel" name="approval" value="NONAPPROVAL"> Non Approval 
                                              
                                          </div>  
                                          <div  class="form-group" id = "type" >
                                            <label class="control-label"> Type:<span class="required" aria-required="true">*</span></label>
                                             
                                                   <input type="radio"  id = "OPEN"  name="ticketDesc" value="OPEN" checked> OPEN  
                                               
                                                   <input type="radio" id = "CLOSED" name="ticketDesc" value="CLOSED"> CLOSED
                                                   
                                                    <input type="radio" id = "COMPLETED" name="ticketDesc" value="COMPLETED"> COMPLETED
                                                    
                                                    <input type="radio" id = "REJECTED" name="ticketDesc" value="REJECTED"> REJECTED
                                              
                                          </div>  
                                          
										   <div class="form-group">
	                                           <label class="control-label">Ticket Status:<span class="required" aria-required="true">*</span></label>
	                                           <select class = "form-control" id = "status" name = "status" required>
	                                           		<option value = "">Select</option>
	                                           		<c:forEach var="ticket" items="${ticketStatusList}">
	                                           			<option value = "${ticket.ticketCode}">${ticket.ticketDesc}</option>
	                                           		</c:forEach>
	                                           		
	                                           </select>
	                                    </div>
                                   		
                                   		<div class="alert alert-danger" id="warningmsg2" style="display: none">
												<span></span>	
										</div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<input  class="btn btn-primary" type="button"  value="Submit" onclick="checkDuplicate()"/> 
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
					</div>
					 <div class="row">
						<div class="col-md-6 col-md-offset-3">						  
						
                        <section class="panel">
                        
                            <form:form id="editTaskStatus" name="editTaskStatus" action="editTaskStatus.html" method="post">
							<input type="hidden" id="saveId" name="saveId">
							<input type="hidden" id="getTaskStatus" name="getTaskStatus">
							<input type="hidden" id="getTaskType" name="getTaskType">
							<input type="hidden" id="getTicketStatus" name="getTicketStatus">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Task Status</h2>
							</header>
							
							<div class="panel-body">
								<div id=oldTaskType> 
									<c:forEach var="taskStatus" items="${ticketStatusList}" >
										<input type="hidden" class="form-control" id="oldTask" name="oldTask" value="${taskStatus.ticketCode}" />
									</c:forEach>
									
									<c:forEach var="taskStatus" items="${ticketStatusList}" >
									<input type="hidden" class="form-control" id="oldTicketStatusName" name="oldTicketStatusName" value="${taskStatus.ticketDesc}" />
								</c:forEach>
	                             </div> 
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
									<thead>
										<tr>
                                            <th>Task Status</th>
                                            <th>Type</th>
   											<th>Task Type</th>
   											<th>Ticket Status</th>                                         
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="taskStatus" items="${taskStatusList}" varStatus="i">
										<tr>											
											<td>
												<input type="hidden" class="form-control" name="ticketCode${i.index}" value="${taskStatus.ticketCode}" id="ticketCode${i.index}" readonly>
												 <input type="hidden" name="status${i.index}" id="status${i.index}" value="${taskStatus.status}">
												  <input type="hidden" name="oldTaskStatusNamesForDuplicateCheck" value="${taskStatus.status}">
												${taskStatus.status}
											</td>
											<td>
												 <input type="hidden" name="type${i.index}" id="type${i.index}" value="${taskStatus.type}">
												${taskStatus.type}
											</td>
											<td>
												 <input type="hidden" name="approval${i.index}" id="approval${i.index}" value="${taskStatus.approval}">
												${taskStatus.approval}
											</td>
											<td>
												 <input type="hidden" name="ticketDesc${i.index}" id="ticketDesc${i.index}" value="${taskStatus.ticketDesc}">
												 <input type="hidden" name="description${i.index}" id="description${i.index}" value="${taskStatus.description}">
												${taskStatus.ticketDesc}
											</td>
                                           
											<td class="actions">
												<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" onclick="updateTaskStatus('${i.index}')"><i class="fa fa-pencil"></i></a>
											</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							
							
						</section>
						<!-- /**Edit Task Status Details By Ranita.Sur 24102017**/ -->
                                <!-- popup Window code -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
									<section class="panel">
										<header class="panel-heading">
											 <h2 class="panel-title">Edit Task Status</h2>		<!-- it was closed open by Saif 21-03-2018 -->
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id="updateTaskStatusDetails">
												<thead>
													<tr>
														 <th>Task Status</th>
		                                                <th>Task Type</th>
		                                                <th>Ticket Status</th>
		                                          	</tr>
												</thead>
												<tbody>
								
												</tbody>
											</table>
											<div class="alert alert-warning" id="warningmsg1" style="display: none">
												<span></span>	
											</div>
										</div>
										<footer class="panel-footer">
											<div class="row">
												<div class="col-md-12 text-right">
													<button id="updateTaskStatusButton" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
								</form:form>
						</div>
					</div>	 
		</div>	
	</div>		
			



<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script src="/cedugenie/js/ticketing/servicetype.editable.js"></script>
<script src="/cedugenie/js/administrator/createTaskStatus.js"></script>


</body>
</html>