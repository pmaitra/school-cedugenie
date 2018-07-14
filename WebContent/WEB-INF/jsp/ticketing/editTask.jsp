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
function editable(){
	document.getElementById("status").removeAttribute("disabled");
	document.getElementById("fileData").removeAttribute("disabled");
	//document.getElementById("description").removeAttribute("readonly");
	document.getElementById("updateDB").removeAttribute("disabled");
	document.getElementById("comment").removeAttribute("readonly");
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
			<h2>Edit Task</h2>
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
		<%-- <c:choose>
			<c:when test="${ticket eq null}">	
					<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
						<span id="infomsg">No Ticket Found to Edit</span>	
					</div>		
			</c:when>
		 <c:otherwise> --%>
	
		  <div class="row">
						<div class="col-md-8 col-md-offset-2">
						 <form:form modelAttribute="FORM" method="POST" id="editTask" name="editTask" action="editTask.html" enctype="multipart/form-data">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Edit Task</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										<div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label">Task Code : </label>
                                                    <input type="text" class="form-control" name="ticketCode" id="ticketCode" value="${taskDetails.ticketCode}" readonly="readonly"  placeholder="">  
                                                    <input type="hidden" class="form-control" name="ticketRecId" id="ticketRecId" value="${taskDetails.message}" readonly="readonly" placeholder="">   
                                                                                                    
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label">Allocated By : </label>                                                
                                                    <input type="text" class="form-control" name="reportedBy" id="reportedBy" value="${taskDetails.reportedBy}" readonly="readonly"  placeholder="">
                                                </div>
                                            </div>
                                           
                                        </div>
                                        <div class="row">
                                        	
	                                            <div class="col-md-6">
	                                                <div class="form-group">
	                                                    <label class="control-label">Status</label>   
	                                                    <c:if test="${taskStatusList ne null}">    
	                                                                                         
		                                                    	<select class="form-control" name="status" id="status" disabled="disabled">
		                                                        
		                                                     	   <c:forEach var="taskStatus" items="${taskStatusList}" >
																		<c:if test="${taskStatus.ticketStatusCode eq taskDetails.status}">
																			<option value="${taskStatus.ticketStatusCode}" >${taskStatus.ticketStatusName}</option>
																		</c:if>
																	</c:forEach>
																	 <c:forEach var="taskStatus" items="${taskStatusList}" >
																		<c:if test="${taskStatus.ticketStatusCode ne taskDetails.status}">
																			<option value="${taskStatus.ticketStatusCode}">${taskStatus.ticketStatusName}</option>
																		</c:if>
																	</c:forEach>
		                                                    	</select>
		                                                   </c:if>
	                                                </div>
	                                            </div>
                                          
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label">Category</label>                                                
                                                    <input type="text" class="form-control" name="ticketService.ticketServiceName" id="ticketServiceName" value="${taskDetails.ticketObjectId}" readonly="readonly" placeholder="">
                                                    <input type="hidden" class="form-control" name="ticketService.ticketServiceCode" id="ticketServiceCode" value="${taskDetails.comment}" readonly="readonly" placeholder="">
                                                </div>
                                            </div>
                                          <%--   <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Ticket Summary</label>
                                                    <input type="text" class="form-control" name="ticketSummary" id="ticketSummary" value="${ticket.ticketSummary}" readonly="readonly" placeholder="">                                                    
                                                </div>
                                            </div> --%>
                                            <div class="col-md-4">
                                            </div>
                                        </div>
                                        <div class="row">
                                           <div class="col-md-6"  id = "userDiv" style = "display:none">
	                                           <div class="form-group">
	                                           		<label class="control-label">User Name</label>   
	                                           		<select class="form-control" name="userName" id="userName">
	                                           			<option value="">Please Select</option>
	                                           		</select>
	                                           </div>
                                           </div>
                                         </div>
                                       <div class="row">
                                           <div class="col-md-12">
                                           <div class="form-group">
                                                <label class="control-label">Description</label>
                                                <textarea class="form-control" name="description"  id="description"  readonly="readonly"  rows="3" data-plugin-maxlength=""   maxlength="500">${taskDetails.ticketDesc}</textarea> 
                                                <input type="hidden" class="form-control" name="ticketDesc" id="ticketDesc">                                                    
                                            </div>
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
									</div>
                                       
                                       <div class="row">
											<hr>
											<div class="col-md-4">
											</div>
											<div class="col-md-4">
													<table class="table table-bordered table-striped mb-none">
														
														<tbody>
															<c:forEach var="tket" items="${taskDetails.serviceTypeList}" varStatus="i">
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
                                        <hr>
                                       <c:if test="${isLinked ne false}">
                                       		<div class="row">			
                                       			<div class="col-md-6">
	                                                <div class="form-group">
	                                                    <label class="control-label">Link : </label>                                                
	                                                    <input type="text" class="form-control"  value="${taskDetails.functionalityName}" disabled  placeholder="">
	                                                </div>
	                                            </div>
	                                            <div class="col-md-6">
	                                                <div class="form-group">
	                                                    <label class="control-label">Note : </label>                                                
	                                                    <textarea class="form-control"  value="" disabled  placeholder="">${taskDetails.note}</textarea>
	                                                </div>
	                                            </div>
                                       		</div>
                                       		<hr>
                                       </c:if>
                                       <c:if test="${isFinance ne false}">
                                       		
                                       		<div class = "row">
                                       			<input type="hidden" id = "availableBudget" name="availableBudget" value="${availableBudget}">
                                       			<div class="col-md-6">
													<div class="form-group">
														 <label class="control-label">Department :</label>
														<%-- <input type = "hidden" id = "department" name = "department" value = "${taskDetails.department}"> --%>
														<select class = "form-control" id = "department" name = "department"  readonly>
															<c:forEach var="dept" items="${departmentList}" >
																<option value="${dept.departmentCode}" ${dept.departmentCode eq taskDetails.department?'selected':value}  >${dept.departmentName}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label">Mode :</label>
														<select class="form-control" name="mode" id="mode" required>
															<option value = "">Select..</option>
															<option value ="INCOME">INCOME</option>
															<option value = "EXPENSE">EXPENSE</option>
														</select>
													</div>
												</div>
											</div>
											<div class = "row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label"> Payment Mode :</label>
														<select class="form-control" name="paymentMode" id="maymentMode" required>
															<option value = "">Select..</option>
															<option value ="CASH">CASH</option>
															<option value = "BANK">BANK</option>
														</select>
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label"> Ledger :</label>
														<select class="form-control" name="ledger" id="ledger"  required>
	                                                        <option value = ""> Select..</option>
	                                                     	   <c:forEach var="ledger" items="${ledgerList}" >
																	<option value="${ledger.ledgerCode}" >${ledger.ledgerName}</option>
																</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label"> Amount :</label>
														<input type = "text" class = "form-control" id = "amount" name = "amount" value = "0" required onblur="validateBudget()">
													</div>
													<div class="col-md-6">
														<div class="form-group">
															<div class="alert alert-danger" id="msg" style="display:none">
																<strong>Budget Not Available</strong>
															</div>
														</div>
													</div>
												</div>
											</div>
                                       		<hr>
                                       </c:if>
                                        <c:if test="${commodityPurchaseOrder ne null}">
                                         	<div class = "row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label"><b>Commodity Purchase Order :  </b></label><b>${commodityPurchaseOrder.purchaseOrderCode}</b>
														 <input type="hidden" class="form-control" name="purchaseOrderCode" id="purchaseOrderCode" value =" ${commodityPurchaseOrder.purchaseOrderCode}">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label"><b>Date :  </b> </label><b>${commodityPurchaseOrder.purchaseOrderOpenDate}</b>
														 <input type="hidden" class="form-control" name="purchaseOrderOpenDate" id="purchaseOrderOpenDate" value = "${commodityPurchaseOrder.purchaseOrderOpenDate}">
													</div>
												</div>
											</div>
											<div class = "row">
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label"><b>Vendor Name : </b></label><b>${commodityPurchaseOrder.vendorName}</b>
														 <input type="hidden" class="form-control" name="vendorName" id="vendorName" value = "${commodityPurchaseOrder.vendorName}"">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group">
														<label class="control-label"><b>Total Amount :   </b> </label><b>${commodityPurchaseOrder.netTotal}</b>
														 <input type="hidden" class="form-control" name="netTotal" id="netTotal" value = "${commodityPurchaseOrder.netTotal}">
													</div>
												</div>
											</div>
											<div class = "row">
												<div class="col-md-4 col-md-offset-3">
													<table class="table table-bordered table-striped mb-none">
														<thead>
															<tr>
																<td>Commodity</td>
																<td>Ordered Qty</td>
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
											</div>
											 <hr>
										</c:if>
                                        
                                       
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                 <c:choose>
														<c:when test="${taskDetails.attachmentList eq null || taskDetails.attachmentList.size()==0}">	
                                                   			 <label class="control-label">Attached Document</label>
                                                   											
															<label class="control-label">No Attachment Found</label>													
														</c:when>
													<c:otherwise>
														<label class="control-label">Download Attachments</label>
														<c:if test="${taskDetails.attachmentList != null}">
															<c:forEach var="attachment" items="${taskDetails.attachmentList}" >
																<a href="downloadTicketRelatedAttachments.html?ticketNumber=<c:out value="${taskDetails.ticketRecId}"></c:out>&fileName=<c:out value="${attachment.attachmentName}"></c:out>">${attachment.attachmentName}</a>
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
                                                <div class="form-group">
                                                    <label class="control-label">Previous Comments For Ticket</label>
										                 <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
															<c:choose>
																<c:when test="${taskDetails.commentList eq null || taskDetails.commentList.size()==0}">
																	<tr><td>No Comment Given Yet</td></tr>
																</c:when>
															<c:otherwise>
																<tr><th>Date</th><th>Comment By</th><th>Comments</th></tr>
																<c:if test="${taskDetails.commentList != null}">
																	<c:forEach var="commentList" items="${taskDetails.commentList}" >
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
                                        </div>
                                        <hr>
                                         <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label class="control-label">Previous Comments For Task</label>
										                 <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
															<c:choose>
																<c:when test="${taskDetails.taskCommentList eq null || taskDetails.taskCommentList.size()==0}">
																	<tr><td>No Comment Given Yet</td></tr>
																</c:when>
															<c:otherwise>
																<tr><th>Date</th><th>Comment By</th><th>Comments</th></tr>
																<c:if test="${taskDetails.taskCommentList != null}">
																	<c:forEach var="taskComment" items="${taskDetails.taskCommentList}" >
																		<tr>
																			<td>${taskComment.taskCommentDate}</td>
																			<td>${taskComment.updatedBy}</td>
																			<td><textarea class="form-control" readonly="readonly">${taskComment.comment}</textarea></td>
																		</tr>
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
                                                    <label class="control-label">Add New Comment</label>
                                                    <textarea class="form-control"  name="comment" id="comment" rows="3" data-plugin-maxlength="" maxlength="250" pattern="^[a-zA-Z0-9]+$" title="Special Character not allowed" readonly="readonly" required></textarea>                                                    
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                               <div class="form-group">
                                                <label class="control-label">Upload Related Document </label>
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
                                     
									</div>
									
										<footer style="display: block;" class="panel-footer">
											<c:if test="${taskType eq 'OPEN'}">
												<input type="button" id="edit" class="btn btn-primary editbtn" value="Edit" onclick="editable();" >		
												<input type="submit" name="update" id="updateDB" class="btn btn-warning submitbtn" value="Update" disabled="disabled">
											</c:if>
											<a href="inwardDelegatedTask.html" class="mb-xs mt-xs mr-xs btn btn-info">Back</a>
										</footer>
									
								</section>
                            </form:form>
						</div>	
                        
					</div>	
	<%-- 	</c:otherwise>
		</c:choose> --%>
	</div>
</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/ticketing/editTicket.js"></script>
<script type="text/javascript">
/* $(':input').each(function() { 
    $(this).data('initialValue', $(this).val()); 
}); */ 

/* window.onbeforeunload = function(){  
    var msg = 'You haven\'t saved your changes.';
    var isDirty = false; 

    $(':input').each(function () { 
        if($(this).data('initialValue') != $(this).val()){ 
            isDirty = true; 
        } 
    }); 

    if(isDirty == true){ 
        return msg; 
    } 
}; */
$("#status").change(function (){

	var status = $("#status").val();

	var ticketRecId = $("#ticketRecId").val();

	if(status == 'USER_INPUT_REQUIRED'){
		$.ajax({
			url: '/icam/getUserListAssociatedWithATicket.html',
			dataType: 'json',
			data: "ticketCode=" + ticketRecId,
			success: function(data) {
				var dataArr = data.split("#");
				for(var i=0;i<dataArr.length;i++){
					var arr = dataArr[i].split("*");
					 $("#userName").append($("<option></option>").val(arr[0]).html(arr[1]));
				}
			}
		}); 
		document.getElementById("userDiv").style.display = "block";
	}else{
		document.getElementById("userDiv").style.display = "none";
		var userObject=document.getElementById("userName");		
		removeOption(userObject);
	}
});

function removeOption(x)
{
	for(var i=x.length;i>=0;i--)
	{
		x.remove(i);
	}
	x.innerHTML="<option value=''>Please Select</option>";
}

function validateBudget(){
	//alert("hii");
	var availableBudget = $("#availableBudget").val();
	//alert("availableBudget==="+availableBudget)
	var amount = $("#amount").val();
	//alert("amount=="+amount);
	if(parseFloat(amount)>parseFloat(availableBudget)){
		$("#amount").val(0);
		$("#msg").css("display","block");
	}else{
		$("#msg").css("display","none");
	}
}

$("#department").change(function(){
	var dept = $(this).val();
	$.ajax({
	    url: '/icam/getDepartmentBudgetDetails.html',
	    	dataType: 'json',
	    	data: "departmentCode=" + dept,		    	
	    	success: function(data) {
	    		if(null != data){
	    			var budget = data.split("~");
	    			
	    			$("#availableBudget").val(budget[2]) ;
	    		
	    		}
	    	}
	});
});
</script>
</body>
</html>