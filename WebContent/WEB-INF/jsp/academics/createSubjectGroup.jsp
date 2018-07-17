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
<title>Create Subject</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>

<script type="text/javascript">
function makeEditable(rowId){
	rowId=rowId.replace("edit","");
	document.getElementById("subjectGroupOrderId"+rowId).removeAttribute("readonly");
	document.getElementById("subjectGroupName"+rowId).removeAttribute("readonly");
	document.getElementById("totalHRSForCourse"+rowId).removeAttribute("readonly");
};
/* function saveData(rowId){
	rowId=rowId.replace("save","");
	document.getElementById("saveId").value=rowId;
	document.editSubjectGroup.submit();
	
}; */
</script>
</head>
<body>

	<header class="page-header">
		<h2>Create Subject</h2>
	</header>
	<div class="content-padding">
		<c:if test="${insertUpdateStatus eq 'success'}">
			<div class="alert alert-success"  >
				<strong>${msg}</strong>	
			</div>
		</c:if>
		<c:if test="${insertUpdateStatus eq 'fail'}">
			<div class="alert alert-danger" >
				<strong>${msg}</strong>	
			</div>
		</c:if>
					
                    <div class="row">
						<div class="col-md-4">
						  <form action="addSubjectGroup.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Subject</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="col-sm-5 control-label">Scholastic Type: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<select class="form-control" id="scholasticTypeName" name="scholasticTypeName" required>
													<option value="">Select..</option>
													<c:forEach var="type" items= "${scholasticTypeList}">
														<option value="${type.scholasticTypeCode}">${type.scholasticTypeName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label">Subject Name: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<input name="subjectGroupName" id="subjectGroupName" class="form-control" type="text" required pattern = "[A-Za-z\s]{1,50}"  title="Subject name contains character only" >
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label">Subject Credit: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<input id="subjectGroupOrderId" name="subjectGroupOrderId" class="form-control" type="text" placeholder="0" required pattern="[0-9]{1,1}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label">Total Subject Duration(in hours): <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<input id="totalHRSForCourse" name="totalHRSForCourse" class="form-control" type="text" placeholder="0" required pattern="[0-9]{1,3}">
											</div>
										</div>
										<!-- Added on 18052018 for ticket and task no -->
										<!-- start -->
										<!-- PRAD COMMENTED OUT ON JULY 17 2018 -->
										<%-- <div class="form-group">
											<label class="col-sm-5 control-label">Task No</label>
											<div class="col-sm-7">
												<select class="form-control" name="taskNo" id="taskNo" >
													<option>Select...</option>
													<c:forEach var="task" items="${addTaskList}">
														<option value="${task.taskCode}">${task.taskCode}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label">Ticket No</label>
											<div class="col-sm-7">
												<input type="text" class= "form-control"  name="ticketNo" id="ticketNo" value="" readonly/>
											</div>
										</div> --%>
									<!-- end -->
									</div>
									
									<div class="alert alert-danger" id="javascriptmsg2" style="display: none">
			  							<span></span>	
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" onclick="return validating()">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
									<%-- <footer style="display: block;" class="panel-footer">
										<c:if test="${addTaskList ne null && addTaskList.size() != 0}">
											<button class="btn btn-primary" type="submit" onclick="return validating()">Submit </button>
										</c:if>
										<c:if test="${addTaskList.size() == 0}">
											<button class="btn btn-primary" type="submit" onclick="return validating()" disabled>Submit </button>
										</c:if>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer> --%>
								</section>
                            </form>
						</div>
						
						<div class="col-md-8">
							<c:choose>
							<c:when test="${subjectGroupList eq null || empty subjectGroupList}">
								<div class="alert alert-danger">
									No Subject Found.
								</div>							
							</c:when>
							<c:otherwise>
							<form name="editSubjectGroup" id="editSubjectGroup" action="editSubjectGroup.html" method="post">
								<input type="hidden" name="saveId" id="saveId">
								<input type="hidden" name="getCredit" id="getCredit">
								<input type="hidden" name="getHours" id="getHours">
								<input type="hidden" name="taskNoUpdate" id="taskNoUpdate"> 
								<input type="hidden" name="ticketNoUpdate" id="ticketNoUpdate">
	                            <section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									</div>
									<h2 class="panel-title">Existing Subjects</h2>
								</header>
								<div class="panel-body">
									<c:forEach var="group" items="${subjectGroupList}" varStatus="i">
									<input type="hidden" name="oldSubjectGroupNames" value="${group.subjectGroupName}">
									</c:forEach>
									
									
									<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
										<thead>
											<tr>
												<th>Scholastic Type</th>
												<th>Subject Name</th>
												<th>Subject Credit</th>
												<th>Total Hrs For Subject</th>
												<th>Actions</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="group" items="${subjectGroupList}" varStatus="i">
											<tr class="gradeX">
												<td>
													${group.scholasticTypeName}
												</td>
												<td>
													<input type="hidden" name="subjectGroupId${i.index}" value="${group.subjectGroupId}">
													<input type="hidden" name="subjectGroupName${i.index}" class="form-control" value="${group.subjectGroupName}" readonly id="subjectGroupName${i.index}">
													${group.subjectGroupName}
												</td>
												<td>
													<input type="hidden" name="subjectGroupOrderId${i.index}" class="form-control" value="${group.subjectGroupOrderId}" readonly id="subjectGroupOrderId${i.index}">												 
													 ${group.subjectGroupOrderId} 
												</td>
												<td>
													 <input type="hidden" name="totalHRSForCourse${i.index}" class="form-control" value="${group.totalHRSForCourse}" readonly id="totalHRSForCourse${i.index}">												 
													${group.totalHRSForCourse} 
												</td>
												<%-- <td class="actions">
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<c:if test="${editTaskList ne null && editTaskList.size() != 0}">
														<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" id="edit${i.index}" onclick = "showCourseDetails('${i.index}','${group.scholasticTypeName}','${group.subjectGroupName}','${group.subjectGroupOrderId}','${group.totalHRSForCourse}')"><i class="fa fa-pencil"></i></a>
														<a href="inactiveDeleteCourse.html?groupName=${group.subjectGroupName}" id = "delete${i.index}"><i class="fa fa-trash-o"></i></a> 
													</c:if>
												</td> --%>
												<td class="actions">
													<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
													<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" id="edit${i.index}" onclick = "showCourseDetails('${i.index}','${group.scholasticTypeName}','${group.subjectGroupName}','${group.subjectGroupOrderId}','${group.totalHRSForCourse}')"><i class="fa fa-pencil"></i></a>
													<a href="inactiveDeleteCourse.html?groupName=${group.subjectGroupName}" id = "delete${i.index}"><i class="fa fa-trash-o"></i></a> 
												</td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								</section>
									  <div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide" style="width: 10000px">
                                            <section class="panel">
                                                <header class="panel-heading">
                                                    <!-- <h2 class="panel-title">Approver Group Name - PO_Approver</h2> -->
                                                </header>
                                                <div class="panel-body">
                                                    <table class="table table-bordered table-striped mb-none" id = "approverGroupDetails">
                                                        <thead>
                                                            <tr>
                                                            	<th>Scholastic Type</th>
                                                                <th>Subject Name</th>
                                                                <th>Subject Credit</th>
                                                                <th>Total Hours for credit</th>
                                                                <!-- <th>Task No</th>
                                                                <th>Ticket No</th> --> 
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                        	<tr>
                                                        		<td><input type="text" class = "form-control" name="type" id="type" value="" readonly></td>
                                                        		<td><input type="text" class = "form-control" name="name" id="name" value="" readonly></td>
                                                        		<td><input type="text" class = "form-control" name="id" id="code"  class = "form-control" style="width: 50px" value="" ></td>
											     				<td><input type="text" name="hrs" id="hrs"  class = "form-control" style="width: 50px" value="" ></td>
											     				<%-- <td>
											     					<select name="taskNo" id="taskNoEdit"  class = "form-control" style="width: 100px">
											     						<option value="">select..</option>
												     					<c:forEach var="task" items="${taskList}">
																			<option  value="${task.taskCode}">${task.taskCode}</option>
																		</c:forEach>
											     					</select>
											     				</td>
											     				<td><input type="text" class= "form-control"  name="ticketNo" id="ticketNoEdit" style="width: 50px" value="" readonly></td> --%>
											     			</tr>
                                                        </tbody>
                                                    </table>
                                                </div>                                                
                                                <footer class="panel-footer">
													<div class="row">
														<div class="col-md-12 text-right">
															<button id="updateTerms" class="btn btn-success">Update</button>
															<button id="cancel" class="btn btn-danger modal-dismiss" onclick=" deletemsg()" >Cancel</button>
														</div>
													</div>
												</footer>
												<div class="alert alert-danger" id="javascriptmsg" style="display: none">
				  									<span> </span>
												</div>
												<div class="alert alert-danger" id="javascriptmsg1" style="display: none">
				  									<span> </span>	
												</div>
									         	<div class="alert alert-danger" id="javascriptmsg2" style="display: none">
				  									<span> </span>
												</div>
                                            </section>
                                        </div>
									</form>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
	             </div> 
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<!-- <script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script> -->
<script src="/cedugenie/js/academics/createSubjectGroup.editable.js"></script>
<script src="/cedugenie/js/academics/createSubjectGroup.js"></script>
 <script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
</body>
</html>