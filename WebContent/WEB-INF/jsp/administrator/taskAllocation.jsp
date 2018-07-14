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
<title>Closed ticket list</title>

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
	<c:if test="${insertStatus eq 'success'}">
			<div class="alert alert-success">
				<strong>${successMessage}</strong>
			</div>
	</c:if>
	<c:if test="${insertStatus eq 'fail'}">
		<div class="alert alert-danger">
			<strong>${errorMessage}</strong>
		</div>
	</c:if>	
	<div class="row">
		<form method="POST" id="taskAllocate" name="taskAllocate" action="allocateTask.html" >
			<input type="hidden" name="taskIndex" id="taskIndex">
			<input type="hidden" name="resourceIndex" id="resourceIndex">
			<div class="col-md-5">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Allocate Task</h2>										
					</header>
					<div style="display: block;" class="panel-body">
					  <input type="hidden" id="jsonData">
						<div class="form-group">
							<label class="control-label"> Approval Group <span class="required" aria-required="true">*</span></label>
							<select class="form-control" name="name" id="resourceName" required >
								<option value="">Select...</option>
								  <c:forEach var="approver" items="${approverGroupList}">
										<option value="${approver.approverGroupCode}">${approver.approverGroupName}</option>
								  </c:forEach>
							</select>
						</div>	
						<div class="form-group">
							<label class="control-label">Task Type <span class="required" aria-required="true">*</span></label>
							<select class="form-control" name="roleName" id="jobTypeName" required>
								<option value="">Select...</option>
							</select>
						</div>
					</div>
				</section>
			</div>
			<div class="col-md-7" id = "tasks" style = "display:none">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						</div>
						<h2 class="panel-title">Allocation</h2>
					</header>
					<div class="panel-body">
						<table class="table table-bordered table-striped mb-none" id = "datatable-tabletools">
							<thead>
								<tr>
									<th>Task Name</th>
									<th>Start date</th>
									<th>Closed date</th>
									<th>Description</th>
									<th>Status</th>
									<th>Allocate</th>
								</tr>
							</thead>
							<tbody id = "taskTable">
								
							</tbody>
						</table>
					</div>
				</section>
			</div> 
			<div class="col-md-12"  style = "display:none" id = "resource">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						</div>
						<h2 class="panel-title">Allocation</h2>
					</header>
					<div class="panel-body">
						<c:forEach var="resourceType" items="${resourceTypeList}">
							<input type = "hidden" name = "resourceTypeCode" value="${resourceType.resourceTypeCode}"/>
							<input type = "hidden" name = "resourceTypeName" value="${resourceType.resourceTypeName}"/>
						</c:forEach>
						<table class="table table-bordered table-striped mb-none" id = "userTable">
							<thead>
								<tr>
									<th>Resource Type</th>
									<th>User Id</th>
									<th>User Name</th>
									<th>Description</th>
									<th>Add</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<select class="form-control" name="resourceTypeName" id="resourceTypeName" required>
											<option value="" >Select...</option>
											<c:forEach var="resourceType" items="${resourceTypeList}">
												<option value="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
											</c:forEach>
										 </select>
									</td>
									<td>
										 <input type="text" class="form-control" name="userName0" id="userId0"  required/>
									</td>
									<td>
										 <input type="text" class="form-control" name="name0" id="name0" readonly/>
									</td>
									<td>
										 <textarea maxlength="140" data-plugin-maxlength="" rows="2" class="form-control" name="desc0" id="desc0" required></textarea>
									</td>
									<td>
										<a class="mb-xs mt-xs mr-xs  btn btn-info" id="addrow" href="javascript:addrows()">Add</a>
									</td>
								</tr> 
							</tbody>
						</table>
					</div>
				</section>
			</div>
			<div class="col-md-12" style = "display:none" id = "footer">
				<footer style="display: block;" class="panel-footer">
					<button class="btn btn-primary" onclick = "setIndex()">Submit </button>
					<button  class="btn btn-default" id = "reset" onclick = "resetValue();">Reset</button>
				</footer>
			</div>
		</form>
		<div id="modalInfo1" class="col-md-12"  style = "display:none">
			<section class="panel">
				<header class="panel-heading">
				   <!-- <h2 class="panel-title">Approver Group Name - PO_Approver</h2> -->
				</header>
				<div class="panel-body">
					<table class="table table-bordered table-striped mb-none" id = "ticketDetailsTable">
						<thead>
							<tr>
								<th>Date Of Comment</th>
								<th>Name</th>
								<th>Comment</th>
							</tr>
						</thead>
					</table>
				</div>
			</section>
		</div>
	</div>	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/administrator/taskAllocation.js"></script>
</body>
</html>