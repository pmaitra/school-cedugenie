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
</head>
<body>
	<header class="page-header">
			<h2>View Closed Ticket Details</h2>
		</header>
		<div class="content-padding">
<%-- <c:choose>
			<c:when test="${ticket eq null}">	
					<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
						<span id="infomsg">No Closed Ticket Found</span>	
					</div>		
			</c:when>
		 <c:otherwise> --%>
		  <div class="row">
						<div class="col-md-8 col-md-offset-2">
						 <form:form >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">View Closed Ticket</h2>										
									</header>
									<div style="display: block;" class="panel-body" id='salarySlip-printarea'> 
									<input type = "hidden" id = "ticketServiceCode" name = "ticketCode" value = "${ticket.ticketService.ticketServiceCode}"/>                                      
										<div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Ticket Code ::</label>
                                                  	 <label class="control-label"> ${ticket.ticketCode}  </label>                                              
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Reported By ::</label>                                                
                                                    <label class="control-label">${ticket.reportedBy}</label>
                                                </div>
                                            </div>
                                           <%--  <c:if test="${resourceType ne 'STUDENT'}">
	                                            <div class="col-md-4">
	                                                <div class="form-group">
	                                                    <label class="control-label">Affected End User Group ::</label>                                                
	                                                     <label class="control-label">${ticket.affectedUser}</label>
	                                                </div>
	                                            </div>
                                            </c:if> --%>
                                        </div>
                                        <div class="row">
                                        	
	                                            <div class="col-md-4">
	                                                <div class="form-group">
	                                                    <label class="control-label">Status ::</label>   
	                                                    <label class="control-label">${ticket.status}</label>
	                                                </div>
	                                            </div>
                                          
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Category::</label>                                                
                                                     <label class="control-label">${ticket.ticketService.ticketServiceName}</label>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Ticket Summary  ::</label>
                                                     <label class="control-label">${ticket.ticketSummary}</label>                                                    
                                                </div>
                                            </div>
                                           
                                        </div>
                                       <div class="row">
                                           <div class="col-md-12">
                                           <div class="form-group">
                                                <label class="control-label">Description ::</label>
                                                <textarea class="form-control" name="description"  id="description"  readonly="readonly"  rows="3" data-plugin-maxlength="" maxlength="500">${ticket.ticketDesc}</textarea>                                                    
                                            </div>
                                           </div>
                                       </div>
                                    
                                        <hr>
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                   
                                                    <c:choose>
														<c:when test="${ticket.attachmentList eq null || ticket.attachmentList.size()==0}">												
															 <label class="control-label">Attached Document ::</label>
															<label class="control-label">No Attachment Found</label>													
														</c:when>
													<c:otherwise>
														<label class="control-label">Download Attachments ::</label>
														<c:if test="${ticket.attachmentList != null}">
															<c:forEach var="attachment" items="${ticket.attachmentList}" >
																<a href="downloadTicketRelatedAttachments.html?ticketNumber=<c:out value="${ticket.ticketCode}"></c:out>&fileName=<c:out value="${attachment.attachmentName}"></c:out>">${attachment.attachmentName}</a>								
																
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
	                                                    <label class="control-label">Previous Comments ::</label>
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
	                                       <hr>
                                         <div class="row">
                                        
                                            <div class="col-md-8">
                                            	 <c:if test="${questionAnswerList ne null || questionAnswerList.size() != 0}">
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
                                        	<hr>
                                        	
													 <a href="closedTicketList.html" target="frame"><input type="button" class="mb-xs mt-xs mr-xs btn btn-info" value="Back" /></a>
													
										</div>
									</section>
								</form:form>
							</div>
						</div>
					</div>
					<%-- </c:otherwise>
				</c:choose> --%>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<!-- <script src="/cedugenie/assets/javascripts/tables/accessTypeContactMapping.editable.js"></script>
<script src="/cedugenie/assets/javascripts/tables/examples.datatables.editable.js"></script> -->


<script type="text/javascript">

			$("#printinvoice").click (function () {
	            var divContents = $("#salarySlip-printarea").html();
	            var printWindow = window.open('', '', 'height=500,width=900');
	            printWindow.document.write('<html><head><title>Note Sheet</title>');
	            printWindow.document.write('<link rel="stylesheet" href="/cedugenie/assets/stylesheets/custom-print.css"/>');
	            printWindow.document.write('</head><body onload="window.print()">');
	            printWindow.document.write(divContents);
	            printWindow.document.write('</body></html>');
	            printWindow.document.close();	            
	        });
 
 		</script>
</body>
</html>