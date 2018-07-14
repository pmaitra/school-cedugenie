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
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/ticketing/editTicket.js"></script>
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
	document.getElementById("description").removeAttribute("readonly");
	document.getElementById("updateDB").removeAttribute("disabled");
	document.getElementById("comment").removeAttribute("readonly");
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
      
        if(statusNow != statusSelected){
        		changes = changes + "Status(old="+statusSelected+", new="+statusNow+")"+",";
        		//alert("The value of field Status is changed" + " \n" +"Previous value was ::"+ statusSelected + " \n"+ "New value is:: "+ statusNow);
        }
        	
       //alert("changes==="+changes);
       document.getElementById("ticketDesc").value = changes;
       var ticetDesc = document.getElementById("ticketDesc").value;
     //  alert("value = "+ticetDesc);
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
			<h2>EditTicket Details</h2>
		</header>
		
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
	<div class="content-padding">
		  <div class="row">
						<div class="col-md-8 col-md-offset-2">
						 <form:form modelAttribute="FORM" method="POST" id="updateMyTicket" name="updateMyTicket" action="updateMyTicket.html" enctype="multipart/form-data">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Edit My Ticket</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										<div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Ticket Code : </label>
                                                    <input type="text" class="form-control" name="ticketCode" id="ticketCode" value="${ticket.ticketCode}" readonly="readonly"  placeholder="">  
                                                    <input type="hidden" class="form-control" name="ticketRecId" id="ticketRecId" value="${ticket.ticketRecId}" readonly="readonly" placeholder="">                                                  
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Reported By : </label>                                                
                                                    <input type="text" class="form-control" name="reportedBy" id="reportedBy" value="${ticket.reportedBy}" readonly="readonly"  placeholder="">
                                                </div>
                                            </div>
                                           <%--  <c:if test="${resourceType ne 'STUDENT'}">
	                                            <div class="col-md-4">
	                                                <div class="form-group">
	                                                    <label class="control-label">Affected User Group : </label>                                                
	                                                    <input type="text" class="form-control" name="affectedUser" id="affectedUser" value="${ticket.affectedUser}" readonly="readonly" placeholder="">
	                                                </div>
	                                            </div>
                                            </c:if> --%>
                                        </div>
                                        <div class="row">
                                        	
	                                            <div class="col-md-4">
	                                                <div class="form-group">
	                                                    <label class="control-label">Status</label>   
	                                                    <c:if test="${ticketStatusList != null}">                                             
		                                                    <select class="form-control" name="status" id="status" disabled="disabled">
		                                                        
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
                                                    <label class="control-label">Service Type</label>                                                
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
                                            <div class="col-md-4">
                                            </div>
                                        </div>
                                       <div class="row">
                                           <div class="col-md-12">
                                           <div class="form-group">
                                                <label class="control-label">Description</label>
                                                <textarea class="form-control" name="description"  id="description"  readonly="readonly"  rows="3" data-plugin-maxlength=""   maxlength="500">${ticket.ticketDesc}</textarea>                                                    
                                            	<input type="hidden" class="form-control" name="ticketDesc" id="ticketDesc">
                                            </div>
                                           </div>
                                       </div>
                                      
                                        <hr>
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Attached Document</label>
                                                    <c:choose>
														<c:when test="${ticket.attachmentList eq null || ticket.attachmentList.size()==0}">												
															<label class="control-label">No Attachment Found</label>													
														</c:when>
													<c:otherwise>
														<label class="control-label">Download Attachments</label>
														<c:if test="${ticket.attachmentList != null}">
															<c:forEach var="attachment" items="${ticket.attachmentList}" >
																<a href="downloadTicketRelatedAttachments.html?ticketNumber=<c:out value="${ticket.ticketRecId}"></c:out>&fileName=<c:out value="${attachment.attachmentName}"></c:out>&affectedUser=<c:out value="${ticket.affectedUser}"></c:out>">${attachment.attachmentName}</a>
															</c:forEach>
														</c:if>
													</c:otherwise>
													</c:choose>
												</div>
                                            </div>
                                        </div>
                                            <hr>
                                        <div class="row">
                                            <div class="col-md-8">
                                                <div class="form-group">
                                                    <label class="control-label">Previous Comments</label>
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
									<footer style="display: block;" class="panel-footer">
									
										<c:choose>
											<c:when test="${sessionScope.sessionObject.userId ne ticket.reportedBy}">
												<input type="button" id="edit" class="btn btn-primary editbtn" value="Edit" onclick="editable();" >		
												<input type="submit" name="update" id="updateDB" class="btn btn-warning submitbtn" value="Update" disabled="disabled">
											</c:when>
											<c:otherwise>
												<input type="button" id="edit" class="btn btn-primary editbtn" value="Edit" onclick="outwardEditable();" >		
												<input type="submit" name="update" id="updateDB" class="btn btn-warning submitbtn" value="Update" onclick="return outwardEditableOnSubmit();" disabled="disabled">
											</c:otherwise>
										</c:choose>
									</footer>
								</section>
                            </form:form>
						</div>	
                        
					</div>	
				</div>
		</c:otherwise>
		</c:choose>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/ticketing/editTicket.js"></script>
</body>
</html>