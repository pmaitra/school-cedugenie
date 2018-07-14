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
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/ticketing/editTicket.js"></script>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       } .mb-md{
       	   display: none;
       }
</style>
<script>

function addNewTask(){
	//alert("hii");
	document.getElementById("allTaskDiv").style.display = "block";
	document.getElementById("addTaskButton").style.display = "block";
	document.getElementById("addButtonDiv").style.display = "block";
}
function editable(){
	document.getElementById("status").removeAttribute("disabled");
	document.getElementById("fileData").removeAttribute("disabled");
	document.getElementById("description").removeAttribute("readonly");
	document.getElementById("updateDB").removeAttribute("disabled");
	document.getElementById("comment").removeAttribute("readonly");
	document.getElementById("addNewtask").removeAttribute("disabled");
	document.getElementById("approval1").removeAttribute("disabled");
	document.getElementById("task1").removeAttribute("disabled");
	document.getElementById("level1").removeAttribute("disabled");
}
function outwardEditable(){
	document.getElementById("fileData").removeAttribute("disabled");
	document.getElementById("comment").removeAttribute("readonly");
	document.getElementById("updateDB").removeAttribute("disabled");
}
function outwardEditableOnSubmit(){
	document.getElementById("status").removeAttribute("disabled");
	document.getElementById("affectedUser").removeAttribute("disabled");
}

$(document).ready(function(){
	//alert("hii");
    var description = document.getElementById("description")
   
    var statusSelected= document.getElementById("status").value;
	//var original= content.defaultValue;
    $('#updateDB').click(function() {
    	//alert("hii");
    	var changes = "";
    	var statusNow = document.getElementById("status").value;
        if (description.value != description.defaultValue) {
        	var old = description.defaultValue;
        	var newValue = description.value;
        	changes = changes + "Description(old="+old+", new="+newValue+")"+",";
        	//alert("The value of field Description has changed!" + " \n" +"Previous value was ::"+ description.defaultValue+ " \n"+ "New value is:: "+ description.value);
        }
       /*  if($("input[name='gender']:checked").val() != gender)
        	/* {
        	alert("The value of field 'Gender' is changed"+ " \n" +"Previous value was ::"+ gender+ " \n"+ "New value is:: "+ $("input[name='gender']:checked").val());
        	}  */
        if(statusNow != statusSelected){
        		changes = changes + "Status(old="+statusSelected+", new="+statusNow+")"+",";
        		//alert("The value of field Status is changed" + " \n" +"Previous value was ::"+ statusSelected + " \n"+ "New value is:: "+ statusNow);
        }
        	
      // alert("changes==="+changes);
       document.getElementById("ticketDesc").value = changes;
       var ticetDesc = document.getElementById("ticketDesc").value;
      // alert("value = "+ticetDesc);
    });
   
});
</script>

<script type='text/javascript'>
	CharacterCount = function(TextArea,FieldToCount){
		var myField = document.getElementById(TextArea);
		var myLabel = document.getElementById(FieldToCount);
		if(!myField || !myLabel){return false}; // catches errors
		var MaxChars =  myField.maxLengh;
		if(!MaxChars){MaxChars =  myField.getAttribute('maxlength') ; }; 	if(!MaxChars){return false};
		var remainingChars =   MaxChars - myField.value.length
		myLabel.innerHTML = remainingChars+" Characters Remaining of Maximum "+MaxChars
	}
	setInterval(function(){CharacterCount('description','CharCountLabel1')},55);
	
	
</script>

</head>
<body>
	<header class="page-header">
			<h2>Edit Ticket</h2>
		</header>
<div class="content-padding">
		<div class="row">
	<c:if test="${ticket != null}">			
				<c:if test="${ticket.message == 'success'}">
					<div class="alert alert-success">
						<strong>Ticket Raised Successfully. Your ticket ID is - ${ticket.ticketRecId}</strong>	
					</div>					
				</c:if>
				<c:if test="${ticket.message == 'fail'}">
					<div class="alert alert-danger">
						<strong>Problem Occur While Raising Ticket</strong>	
					</div>					
				</c:if>
			</c:if>
		<c:choose>
			<c:when test="${ticket eq null}">	
					<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
						<span id="infomsg">No Ticket Found to Edit</span>	
					</div>		
			</c:when>
		 <c:otherwise>
		 <div class="row">
		  <form:form modelAttribute="FORM" method="POST" id="updateTicket" name="updateTicket" action="updateTicket.html" enctype="multipart/form-data">
								
						<div class="col-md-10 col-md-offset-1">
						  
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Edit Ticket</h2>										
									</header>
									<div style="display: block;" class="panel-body"> 
									
									<c:forEach var="task" items="${taskList}">
										<input type="hidden" name="hiddenTaskCode" value="${task.taskCode}" />
										<input type="hidden" name="hiddenTaskName" value="${task.taskName}"/>
									</c:forEach>
									<c:forEach var="task" items="${allTaskList}">
										<input type="hidden" name="hiddenAllTaskCode" value="${task.jobTypeCode}" />
										<input type="hidden" name="hiddenAllTaskName" value="${task.jobTypeName}"/>
									</c:forEach>
									<c:forEach var="resourceType" items="${resourceTypeList}">
										<input type = "hidden" name = "resourceTypeCode" value="${resourceType.resourceTypeCode}"/>
										<input type = "hidden" name = "resourceTypeName" value="${resourceType.resourceTypeName}"/>
									</c:forEach>
									<c:forEach var="group" items="${recipientGroupList}" >
                                    	<input type = "hidden" name = "groupCode" value="${group.approverGroupCode}"/>
										<input type = "hidden" name = "groupName" value="${group.approverGroupName}"/>
                                    </c:forEach>	
									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
                                                <label class="control-label">Ticket Code</label>
                                                <input type="text" class="form-control" name="ticketCode" id="ticketCode" value="${ticket.ticketCode}" readonly="readonly"  placeholder="">  
                                               <input type="hidden" class="form-control" name="ticketRecId" id="ticketRecId" value="${ticket.ticketRecId}" readonly="readonly" placeholder="">
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                <label class="control-label">Reported by</label>
                                                <input type="text" class="form-control" name="reportedBy" id="reportedBy" value="${ticket.reportedBy}" readonly="readonly"  placeholder="">
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                <label class="control-label">Status</label>
                                                
                                                <c:if test="${ticketStatusList != null}">                                             
                                                   <select class="form-control valid" name="status" id="status" disabled="disabled">
                                                       
                                                    	   <c:forEach var="ticketStatus" items="${ticketStatusList}" >
															<c:if test="${ticketStatus.ticketStatusCode eq ticket.status}">
																<option value="${ticketStatus.ticketStatusCode}" >${ticketStatus.ticketStatusName}</option>
															</c:if>
														</c:forEach>
														 <c:forEach var="ticketStatus" items="${ticketStatusList}" >
															<c:if test="${ticketStatus.ticketStatusCode ne ticket.status}">
																<option value="${ticketStatus.ticketStatusCode}">${ticketStatus.ticketStatusName}</option>
															</c:if>
														</c:forEach>
                                                   </select>
                                                  </c:if>
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                <label class="control-label">Category</label>
                                                <input type="text" class="form-control" name="ticketService.ticketServiceName" id="ticketServiceName" value="${ticket.ticketService.ticketServiceName}" readonly="readonly" placeholder="">
                                                	<input type="hidden" class="form-control" name="ticketService.ticketServiceCode" id="ticketServiceCode" value="${ticket.ticketService.ticketServiceCode}" readonly="readonly" placeholder="">
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                <label class="control-label">Ticket Summary</label>
                                                <input type="text" class="form-control" name="ticketSummary" id="ticketSummary" value="${ticket.ticketSummary}" readonly="readonly" placeholder="">  
                                            </div>
										</div>
										<div class="col-md-12">
											<div class="form-group">
                                                <label class="control-label">Description</label>
                                                <textarea class="form-control" name="description"  id="description"  readonly="readonly"  rows="3" data-plugin-maxlength=""   maxlength="500">${ticket.ticketDesc}</textarea> 
                                                <input type="hidden" class="form-control" name="ticketDesc" id="ticketDesc">       
                                            </div>
										</div>
									</div>
									<div class="row">
										<hr>
										<div class="col-md-4">
										</div>
										<div class="col-md-4">
												<table class="table table-bordered table-striped mb-none">
													
													<tbody>
														<c:forEach var="tket" items="${ticket.serviceTypeList}" varStatus="i">
															<tr>
																<td>${tket.ticketServiceCode}</td>
																<td>${tket.ticketServiceName}</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
											<div class="col-md-4">
											</div>
									</div> 
									<div class="row">
										<c:if test="${stuLeaveDetailsObj ne null}">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label">Standard : </label>${stuLeaveDetailsObj.standard}
													 <input type="hidden" class="form-control" name="standard" id="standard" value =" ${stuLeaveDetailsObj.standard}">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label">Section : </label>${stuLeaveDetailsObj.section}
													 <input type="hidden" class="form-control" name="section" id="section" value = "${stuLeaveDetailsObj.section}">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label">Roll Number : </label>${stuLeaveDetailsObj.rollNumber}
													 <input type="hidden" class="form-control" name="rollNumber" id="rollNumber" value = "${stuLeaveDetailsObj.rollNumber}"">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label">From Date : </label>${stuLeaveDetailsObj.fromDate}
													 <input type="hidden" class="form-control" name="fromDate" id="fromDate" value = "${stuLeaveDetailsObj.fromDate}">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label">To Date : </label>${stuLeaveDetailsObj.toDate}
													 <input type="hidden" class="form-control" name="toDate" id="toDate" value = "${stuLeaveDetailsObj.toDate}">
												</div>
											</div>
										</c:if>
										<c:if test="${commodityPurchaseOrder ne null}">
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label"><b>Commodity Purchase Order :  </b></label><b>${commodityPurchaseOrder.purchaseOrderCode}</b>
													 <input type="hidden" class="form-control" name="purchaseOrderCode" id="purchaseOrderCode" value =" ${commodityPurchaseOrder.purchaseOrderCode}">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label"><b>Date :  </b> </label><b>${commodityPurchaseOrder.purchaseOrderOpenDate}</b>
													 <input type="hidden" class="form-control" name="purchaseOrderOpenDate" id="purchaseOrderOpenDate" value = "${commodityPurchaseOrder.purchaseOrderOpenDate}">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label"><b>Vendor Name : </b></label><b>${commodityPurchaseOrder.vendorName}</b>
													 <input type="hidden" class="form-control" name="vendorName" id="vendorName" value = "${commodityPurchaseOrder.vendorName}"">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group">
													<label class="control-label"><b>Total Amount :   </b> </label><b>${commodityPurchaseOrder.netTotal}</b>
													 <input type="hidden" class="form-control" name="netTotal" id="netTotal" value = "${commodityPurchaseOrder.netTotal}">
												</div>
											</div>
											<div class="col-md-4">
											</div>
											<div class="col-md-4">
											</div>
											<div class="col-md-4">
												<table class="table table-bordered table-striped mb-none">
													<thead>
														<tr>
															<td>Commodity</td>
															<td>Ordered Qty(kg)</td>
															<td>Rate</td>
															<td>Amount</td>
														</tr>
													</thead>
													<tbody>
														<c:forEach var="commodity" items="${commodityPurchaseOrder.commodityPurchaseOrderDetailsList}" varStatus="i">
															<tr>
																<td>${commodity.commodity}</td>
																<td>${commodity.qtyOrdered}</td>
																<td>${commodity.rate}</td>
																<td>${commodity.amount}</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
											
										</c:if>
									</div>
									<hr>
									 <input type = "button" class="btn btn-warning" id="addNewtask" onclick="addNewTask();" value ="Add Task" disabled/>  
									<div  id = "addTaskButton" style = "display:none">
										<a class="btn btn-primary btn-sm" onclick="additional_address_fields();"  ><i class="fa fa-plus" aria-hidden="true"></i></a>
									</div>
									<div id  = "allTaskDiv" style="display:none">
										<hr>
										<c:if test="${allTaskList ne null && allTaskList.size() !=0}">  
											<div class="row">
												<div class="col-md-12">
													<h4>Task List</h4>
													<table class="table table-striped table-no-more mb-none">
														<thead>
															<tr>
																<td>Sl No.</td>
																<td>Task</td>
																<td>Level</td>
																<td>Resource - Group / Individual</td>
																<td class="text-center">Action</td>
															</tr>
														</thead>
														<tbody id="additional_address_fields">
															
															<tr class = "removeclass1">
																<td width="10%">1</td>
																<td width="25%">
																	<select class="form-control" id = "additinalTask1" name = "additinalTask">
					                                                    <option value="">Please select</option>
					                                                    <c:forEach var="task" items="${allTaskList}" >
					                                                    	<option value = "${task.jobTypeCode}">${task.jobTypeName}</option>
					                                                    </c:forEach>	
					                                                </select>
																</td>
																<td width="25%">
																	<select class="form-control" id = "additionalLevel1" name="additionalLevel">
					                                                    <option value="">Please select</option>
					                                                  	 <c:forEach var="level" items="${levelList}" >
					                                                  	 		<option value = "${level}">${level}</option>
					                                                  	 </c:forEach>	
					                                                </select>
																</td>
																<td>
																	<select class="form-control" name="additinoalApproval" id="additionalApproval1"  onchange ="checkResource(this);">
																		<option value="">Please select</option>
					                                                    <option value="group">Group</option>	
					                                                    <option value="individual">Individual</option>	
																	</select>
																	
																	<select class="form-control" name = "additionalIndividual1" id = "additionalIndividual1" onchange = "getUserIdNew(this);" style="display:none;margin-top:10px;">
					                                                    <option value="">Please Select Resource Type</option>	
					                                                    <c:forEach var="resourceType" items="${resourceTypeList}" >
					                                                    	<option value = "${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
					                                                    </c:forEach>	
					                                                </select>
					                                                
					                                                 <select class="form-control" id = "additionalGroup1" name = "additionalGroup1" style="display:none;margin-top:10px;">
					                                                    <option value="">Please Select Recipient Group</option>	
					                                                    <c:forEach var="group" items="${recipientGroupList}" >
					                                                    	<option value = "${group.approverGroupCode}">${group.approverGroupName}</option>
					                                                    </c:forEach>	
					                                                </select>
								                                                
					                                                <input class="form-control" name="additionalResource1"  id= "additionalResource1" type="text" placeholder = "Select User Id" style="display:none;margin-top:10px;" >
					                                                
																	
																</td>
																 <td class="text-center" width="10%">
																 	<div id = "addButtonDiv" style="display:none;">
																		<a class="btn btn-danger btn-sm fa fa-times" id = "delete1"  onclick="remove_address_fields(this);" ></a>
																	</div>
																</td> 
															</tr>
														</tbody>
													</table>
												</div>
											</div> 
										</c:if> 
									</div>
									<hr>
									<c:if test="${taskList ne null && taskList.size() !=0}">  
										<div class="row">
											<div class="col-md-12">
												<h4>Task List</h4>
												<table class="table table-striped table-no-more mb-none">
													<thead>
														<tr>
															<td>Sl No.</td>
															<td>Task</td>
															<td>Level</td>
															<td>Resource - Group / Individual</td>
															<td class="text-center">Action</td>
														</tr>
													</thead>
													<tbody id="address_fields">
														
														<tr>
															<td width="10%">1</td>
															<td width="25%">
																<select class="form-control" id = "task1" name = "task" disabled>
				                                                    <option value="">Please select</option>
				                                                    <c:forEach var="task" items="${taskList}" >
				                                                    	<option value = "${task.taskCode}">${task.taskName}</option>
				                                                    </c:forEach>	
				                                                </select>
															</td>
															<td width="25%">
																<select class="form-control" id = "level1" name="level" disabled>
				                                                    <option value="">Please select</option>
				                                                     <c:forEach var="level" items="${levelList}" >
				                                                  	 		<option value = "${level}">${level}</option>
				                                                  	 </c:forEach>
			                                                </select>
															</td>
															<td>
																<select class="form-control" name="approval" id="approval1"  onchange ="checkresource(this);" disabled>
																	<option value="">Please select</option>
				                                                    <option value="group">Group</option>	
				                                                    <option value="individual">Individual</option>	
																</select>
																
																<select class="form-control" name = "individual1" id = "individual1" onchange = "getUserId(this);" style="display:none;margin-top:10px;">
				                                                    <option value="">Please Select Resource Type</option>	
				                                                    <c:forEach var="resourceType" items="${resourceTypeList}" >
				                                                    	<option value = "${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
				                                                    </c:forEach>	
				                                                </select>
				                                                
				                                                 <select class="form-control" id = "group1" name = "group1" style="display:none;margin-top:10px;">
				                                                    <option value="">Please Select Recipient Group</option>	
				                                                    <c:forEach var="group" items="${recipientGroupList}" >
				                                                    	<option value = "${group.approverGroupCode}">${group.approverGroupName}</option>
				                                                    </c:forEach>	
				                                                </select>
							                                                
				                                                <input class="form-control" name="resource1"  id= "resource1" type="text" placeholder = "Select User Id" style="display:none;margin-top:10px;" >
				                                                
																
															</td>
															<td class="text-center" width="10%">
																<a class="btn btn-primary btn-sm" onclick="address_fields();"><i class="fa fa-plus" aria-hidden="true"></i></a>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div> 
									</c:if> 
									<c:if test="${taskListForTicket ne null && taskListForTicket.size()!=0}"> 
										<div class="row">
											<div class="col-md-12">
												<h4>Task List</h4>
												<table class="table table-striped table-no-more mb-none">
													<thead>
														<tr>
															<td>Sl No.</td>
															<td>Task</td>
															<td>Level</td>
															<td>Resource - Group / Individual</td>
															<td>Status</td>
															<!-- <td class="text-center">Action</td> -->
														</tr>
													</thead>
													<tbody id="address_fields">
														<c:forEach var="task" items="${taskListForTicket}" varStatus="i" >
															<tr>
															<td>${i.index +1}</td>
															<td>${task.taskName}</td>
															<td>${task.objectId}</td>
															<td>${task.userId}</td>
															<td>${task.status}</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div> 
									</c:if>                                   
									<hr>
									<div class="row">
										<div class="col-md-12">
											<h4>Attached Documents</h4>
											
											
											<div class="form-group">
                                                    <!-- <label class="control-label">Attached Document</label> -->
                                                    <c:choose>
														<c:when test="${ticket.attachmentList eq null || ticket.attachmentList.size()==0}">												
															<label class="control-label">No Attachment Found</label>													
														</c:when>
													<c:otherwise>
														<label class="control-label">Download Attachments</label>
														<c:if test="${ticket.attachmentList != null}">
															<c:forEach var="attachment" items="${ticket.attachmentList}" >
																<li>
																	<a href="downloadTicketRelatedAttachments.html?ticketNumber=<c:out value="${ticket.ticketCode}"></c:out>&fileName=<c:out value="${attachment.attachmentName}"></c:out>">${attachment.attachmentName}</a>
																</li>
															</c:forEach>
														</c:if>
													</c:otherwise>
													</c:choose>
												</div>
										</div>
									</div>	                                                                              
                                    <hr>
									<div class="row">
										<div class="col-md-12">
											<h4>Previous Comments</h4>
											 <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
												<c:choose>
													<c:when test="${ticket.commentList eq null || ticket.commentList.size()==0}">
														<tr><td>No Comment Given Yet</td></tr>
													</c:when>
												<c:otherwise>
													<tr><th>Date</th><th>Comment By</th><th>Comments</th></tr>
													<c:if test="${ticket.commentList != null}">
														<c:forEach var="commentList" items="${ticket.commentList}" >
															<tr>
																<td>${commentList.ticketCommentDate}</td>
																<td>${commentList.updatedBy}</td>
																<td><textarea class="form-control" readonly="readonly">${commentList.comment}</textarea></td>
															</tr>
														</c:forEach>
													</c:if>
												</c:otherwise>
												</c:choose>						
											</table>        
										</div>
									</div>
									<hr>
									 <div class="row">
                                            <div class="col-md-8">
                                                <div class="form-group">
                                                    <label class="control-label">Previous Comments For Task</label>
										                 <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
															<c:choose>
																<c:when test="${ticket.taskCommentList eq null || ticket.taskCommentList.size()==0}">
																	<tr><td>No Comment Given Yet</td></tr>
																</c:when>
															<c:otherwise>
																<tr><th>Date</th><th>Comment By</th><th>Comments</th></tr>
																<c:if test="${ticket.taskCommentList ne null}">
																	<c:forEach var="taskComment" items="${ticket.taskCommentList}" >
																		<c:if test="${taskComment.updatedBy ne null}">
																			<tr>
																				<td>${taskComment.taskCommentDate}</td>
																				<td>${taskComment.updatedBy}</td>
																				<td><textarea class="form-control" readonly="readonly">${taskComment.comment}</textarea></td>
																			</tr>
																		</c:if>
																	</c:forEach>
																</c:if>
															</c:otherwise>
															</c:choose>						
														</table>                                                    
                                                </div>
                                            </div>
                                        </div>
									<hr>
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
                                                <label class="control-label">Add Comments<span class="required" aria-required="true">*</span></label>
                                                 <textarea class="form-control"  name="comment" id="comment" rows="3" data-plugin-maxlength="" maxlength="250" pattern="^[a-zA-Z0-9]+$" title="Special Character not allowed" readonly="readonly" required></textarea> 
                                            </div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
                                                <label class="control-label">Upload Document</label>
                                               <!--  <input type="file" class="form-control" name="firstname"> -->
                                                <table>
                                                    <tr>
                                                        <td><input type="file"  name="uploadFile.ticketingRelatedFile" id="fileData" disabled="disabled"></td>
                                                    	<td><input id="addFile2" class="addFileClassName" type="button" value="ADD"/></td>
                                                    </tr>
                                                </table> 
                                            </div>
										</div>
									</div>  
									  <hr>
                                         <div class="row">
                                        
                                            <div class="col-md-8">
                                            	 <c:if test="${questionAnswerList ne null}">
                                                	<div class="form-group">
                                                    <label class="control-label">Survey Details</label>
										                 <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
															 <c:choose>
																<c:when test="${questionAnswerList eq null || questionAnswerList.size()==0}">
																	<tr><td>No Survey Available</td></tr>
																</c:when>
															<c:otherwise> 
																<tr><th>Question</th><th>Answer</th></tr>
																
																	<c:forEach var="survey" items="${questionAnswerList}" >
																		<tr>
																			<td>${survey.questionId}</td>
																			<td>${survey.answerId}</td></tr>
																	</c:forEach>
																
															 </c:otherwise>
															</c:choose>		 				
														</table>                                                    
                                                </div>
                                                </c:if>
                                            </div>
                                            
                                        </div>     
									</div>
									<footer class="panel-footer">
										<c:if test="${statusType eq 'REJECTED'|| statusType eq 'CLOSED' || statusType eq 'COMPLETED'}">
											<a href="listClosedTicket.html" class="mb-xs mt-xs mr-xs btn btn-info">Back</a>
										</c:if>
										<c:if test="${statusType eq 'OPEN' || statusType eq null}">
											<input type="button" id="edit" class="btn btn-primary editbtn" value="Edit" onclick="editable();" >	
											<button class="btn btn-warning" id="updateDB" disabled>Submit </button>
											<button type="reset" class="btn btn-default">Reset</button>
											<a href="inwardListTicket.html" class="mb-xs mt-xs mr-xs btn btn-info">Back</a>
										</c:if>
									</footer>
								</section>
                            
						</div>
						</form:form>						
					</div>		
		</c:otherwise>
		</c:choose>
	</div>
</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/ticketing/editTicket.js"></script>

</body>
</html>