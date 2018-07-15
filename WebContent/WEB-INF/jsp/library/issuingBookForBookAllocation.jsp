<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Allocate Book To Resource</title>
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
	<h2>Allocate Book To Resource</h2>
</header>
<div class="content-padding">
	<div class="row">
		<c:choose>
			<c:when test="${errorMessageDisplay!=null}">
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">Data Not Found</span>	
				</div>
			</c:when>
		<c:otherwise>
			<form:form method="POST" name="bookAllocation" action="submitIssuingBookForBookAllocation.html">
				<div class="col-md-4 col-md-offset-4">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
							<h2 class="panel-title">Book Allocation Request Details</h2>										
						</header>
						<div style="display: block;" class="panel-body">
                            <div class="form-group">
                            	<label class="control-label">Book Request Code</label>
								<input type="text" class = "form-control" readonly name="bookRequestedId" value="${BookRequestResult.bookRequestCode}"/>
                            </div>
                            <div class="form-group">
                            	<label class="control-label">Requested by ( User ID )</label>
								<input type="text" class = "form-control" readonly name="userId" value="${BookRequestResult.bookRequestFor.userId}"/>
                           	</div>
						</div>
					</section>
				</div>
				<div class="col-md-12">	
                	<section class="panel">
                    	<header class="panel-heading">
                        	<div class="panel-actions">
                            	<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                            </div>
                            <h2 class="panel-title">Request Details</h2>
                      	</header>
                        <c:choose>                                
							<c:when test="${message!=null}">
								<div class = "alert alert-danger">
									<strong>${message}</strong>
								</div>
							</c:when>
						<c:otherwise>                                
                        	<div class="panel-body">
                            	<table class="table table-bordered table-striped mb-none">
                                	<thead>
                                    	<tr>
                                            <th>Accession Number</th>	
											<th>Book Name</th>					
											<th>Requested Date</th>
											<th>Request Expire Date</th>
											<!-- <th>Check Waiting List</th> -->
											<!-- <th>Available Book ID</th> -->
											<!-- <th>Available copies</th> -->
											<!-- <th>Threshold</th> -->
											<th>Expected Return Date</th>
                                        </tr>
                                   	</thead>
                                    <tbody>
                                    	<c:forEach var="bookRequestResults" items="${bookRequestDetailsList}" varStatus="i">
											<tr class="gradeX">
												<td>${bookRequestResults.bookCode}</td>
												<td>${bookRequestResults.bookName}</td>							
												<td>${BookRequestResult.bookRequestOpenDate}</td>
												<td>${BookRequestResult.bookRequestCloseDate}</td>
												<%-- <td>
													<select name='waitingBookId' id="waitingBookId" class="form-control">
														<option value="">Select...</option>	
														<c:forEach var="waitingBookId" items="${bookRequestResults.bookWaitingList}"> 
															<option value="${waitingBookId.userId}">${waitingBookId.userId}</option> 
														</c:forEach>								  
													</select>
												</td> --%>
												<%-- <td>
													<select name='allocatedBookId' id="allocatedBookId" onChange="checkThreshold(this);" class="form-control">
														<option value="">Select...</option>	
														<c:forEach var="varBookId" items="${bookRequestResults.bookIdList}"> 
															<option value="${varBookId.bookId}">${varBookId.bookId}</option> 
														</c:forEach>
													</select>
												</td> --%>
												<%-- <td>
													${fn:length(bookRequestResults.bookIdList)}
													<input type="hidden" name="totalAvlBook" id="totalAvlBook" value="${fn:length(bookRequestResults.bookIdList)}"/>
												</td> --%>
												<%-- <td>
													${bookRequestResults.book.threshold} 
													<input type="hidden" name="threshold" id="threshold" value="${bookRequestResults.book.threshold}"/>
												</td> --%>
												<td>
													<input type="text" name="returnDate" class ="form-control" id="returnDate${status.index}" value="${bookRequestResults.expectedBookReturnDate}"/>
												</td>
											</tr>
										</c:forEach>
                                   	</tbody>
                               	</table>
                       		</div>
                       	</c:otherwise>
                        </c:choose>
               		</section>
				</div>
				<div class="col-md-12">	
                	<section class="panel">
                    	<header class="panel-heading">
                        	<div class="panel-actions">
                           		<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                            </div>
							<h2 class="panel-title">Already Allocated Books</h2>
                      	</header>
                        <c:choose>                                
							<c:when test="${bookAllocationList==null}">
								<div class="panel-body">
									<div class="alert alert-danger">
										<strong>Currently '${BookRequestResult.bookRequestFor.userId}' Has No Allocated Book</strong>
									</div>
								</div>
							</c:when>
						<c:otherwise>                                
                        	<div class="panel-body">
                            	<table class="table table-bordered table-striped mb-none">
                                	<thead>
                                    	<tr>
                                        	<th>Book Allocation Code</th>
											<th>Book Name</th>
											<th>Book ID</th>
											<th>Issued Date</th>
											<th>Expected Return Date</th>
											<th>Status</th>
                                       	</tr>
                               		</thead>
                                   	<tbody>
                                  		<c:forEach var="bookAllocation" items="${bookAllocationList}">
											<c:forEach var="bookAllocationDetails" items="${bookAllocation.bookAllocationDetails}">
												<tr class="gradeX">
													<td>${bookAllocation.bookAllocationCode}</td>						
													<td>${bookAllocationDetails.bookName}</td>						
													<td>${bookAllocationDetails.bookId}</td>
													<td>${bookAllocationDetails.bookIssueDate}</td>
													<td>${bookAllocationDetails.bookReturnDate}</td>
													<td>${bookAllocationDetails.status}</td>
												</tr>
											</c:forEach>
                                       	</c:forEach>
                               		</tbody>
                       			</table>
                       		</div>
               			</c:otherwise>
                        </c:choose>
                       	<footer style="display: block;" class="panel-footer">
							<button type="submit" id="submitButton" class="btn btn-primary" value="SUBMIT" >Submit</button>
							<button type="button" value = "Back" class="btn btn-primary" onclick="window.location='bookAllocation.html'">Back</button>
						</footer>
                  		</section>
				</div>
			</form:form>
		</c:otherwise>
		</c:choose>
	</div>
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/library/issuingBookForBookAllocation.js"></script>
</body>
</html>