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
       }
</style>
</head>
<body>

<header class="page-header">
			<h2>List Task</h2>
		</header>
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
		<div class="col-md-12">
			<section class="panel">
				<header class="panel-heading">
					<h2 class="panel-title"> Task List</h2>
				</header>
				<div class="panel-body">
					<input type = "hidden" id = "ticketCode" name = "ticketCode"/>
					<input type = "hidden" id = "taskId" name = "taskId"/>
					<input type = "hidden" id = "taskName" name = "taskName"/>
					<input type = "hidden" id = "taskComment" name = "taskComment"/>
					<table id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
						<thead>
							<tr>
								<th>Task No</th>
								<th>Ticket No</th>
								<th>Delegated On</th>
								<th>Delegated By</th>
								<th>Present User</th>
								<th>Task Description</th>
								<th>Status</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="task" items="${delegatedTaskList}"  varStatus="i">
								<tr>
									<td>${task.taskCode}</td>
									<td>${task.taskObjectId}</td>
									<td>
										${task.activationTime}
									</td>
									<td>${task.createdById}
									</td>
									<td>${task.taskOwnerName}</td>
									<td>${task.taskDesc}</td>
									<td>${task.status}</td>
									<td>
										<input type = "hidden" id = "taskId${i.index}" name = "taskId${i.index}" value = "${task.taskId}"/>
										<input type = "hidden" id = "taskStatus${i.index}" name = "taskStatus${i.index}" value = "${task.status}"/>
										<input type = "hidden" id = "ticketCode${i.index}" name = "ticketCode${i.index}" value = "${task.taskCode}"/>
										<input type = "hidden" id = "taskName${i.index}" name = "taskName${i.index}" value = "${task.taskName}"/>
										<%-- <c:if test="${task.status eq 'OPEN'}"> 
											<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic btn btn-xs btn-success" onclick = "addComment('${i.index}','ACCEPT')">Accept</a>
											<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic btn btn-xs btn-danger" onclick = "addComment('${i.index}','REJECT')">REJECT</a>
										</c:if> 
										<c:if test="${task.status ne 'OPEN'}">
											<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic btn btn-xs btn-info" onclick = "addComment('${i.index}','CLOSE')">Close</a>
										</c:if> --%>
										<a href="editTask.html?taskCode=${task.taskCode}&taskType=${task.taskType}"><input type="button" name="details" value="DETAILS" class="btn btn-info"></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
					<section class="panel">
						<header class="panel-heading">
							<!-- <h2 class="panel-title">Approver Group Name - PO_Approver</h2> -->
						</header>
						<div class="panel-body">
							<table class="table table-bordered table-striped mb-none" id = "commentTable">
								<thead>
									<tr>
										<th>Date Of Comment</th>
										<th>Previous Comment</th>
										<th>New Comment</th>
									</tr>
								</thead>
								<tbody>
									 
								</tbody>
							</table>
							<div class="alert alert-warning" id="warningmsg" style="display: none">
								<span></span>	
							</div>
						</div>
						<footer class="panel-footer">
							<div class="row">
								<div class="col-md-12 text-right">
									<button id = "submit" name = "submit" class="btn btn-success">Submit</button>
									<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
								</div>
							</div>
						</footer>
					</section>
				</div> 
			</section>
		</div>
	</div>

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/administrator/delegatedTask.js"></script> 
</body>
</html>