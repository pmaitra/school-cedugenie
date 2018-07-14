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
       }
</style>
</head>
 <div class="row">
						<div class="col-md-5">
						  <form action="insertApprovalOrder.html" method="post" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Approval Order</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label">Job Name <span class="required" aria-required="true">*</span></label>
                                             <select class="form-control" name="approverGroupDesc" id="approverGroupDesc">
                                                <option value="">Select...</option>
                                                <c:forEach var="job" items="${jobDetailsList}">
													<option value="${job.jobTypeName}">${job.jobTypeName}</option>
												</c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Applied By Group <span class="required" aria-required="true">*</span></label>
                                             <select class="form-control" name="approverGroupCode" id="approverGroupCode">
                                                <option value="">Select...</option>
                                                <c:forEach var="approver" items="${approverGroupList}">
													<option value="${approver.approverGroupName}">${approver.approverGroupName}</option>
												</c:forEach>
                                            </select>
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">Approved By Group <span class="required" aria-required="true">*</span></label>
                                             <select class="form-control" name="approverGroupName" id="approverGroupName">
                                                <option value="">Select...</option>
                                                <c:forEach var="approver" items="${approverGroupList}">
													<option value="${approver.approverGroupName}">${approver.approverGroupName}</option>
												</c:forEach>
                                            </select>
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">Approval Order</label>
                                            <input type = "text" class="form-control" name = "serialNumber" id = "serialNumber"> 
                                        </div>
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button  type = "submit" class="btn btn-primary">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
						 <div class="col-md-7">	
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Existing Approver Group Details</h2>
                                </header>
                                <div class="panel-body">

                                    <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                        <thead>
                                            <tr>
                                                <th>Approver Group Name</th>
                                                <th>Applicant Group Name</th>
                                                <th>Approval Order</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach var="approve"  items="${approvalOrderList}">
	                                            <tr>
	                                                <td>${approve.approverGroupName}</td>
	                                           		<td>${approve.approverGroupDesc}</td>
	                                           		<td>${approve.serialNumber}</td>
	                                            </tr>
                                           </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </section>
						</div> 
					</div>	

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>