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
       } .mb-md{
       	   display: none;
       }
</style>
</head>
<body>
	 <div class="row">
						<div class="col-md-5">
							<c:if test="${errorMessage ne null}">
								<div id="errorbox" class="errorbox" style="visibility:visible;">
									<span class="errormsg">${errorMessage}</span>
								</div>
							</c:if>
							<c:if test="${updateSuccessStatus ne null}">
									<div id="successbox" class="successbox" style="visibility:visible;">
										<span class="successmsg">${updateSuccessStatus}</span>
									</div>
							</c:if>
						
						 <form action="createTicketingSLA.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Ticketing SLA</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        										
                                        <div class="form-group">
                                            <label class="control-label">Module Name <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" multiple="multiple" name="moduleName1" data-plugin-multiselect data-plugin-options='{ "maxHeight": 200, "includeSelectAllOption": true }' id="ms_example5">
                                                <optgroup label="Module Name">
	                                                <c:forEach var="module" items="${moduleList}">
	                                                	<option value="${module.moduleName}" name="Div0clsName">${module.moduleName}</option>
	<!--                                            onclick="addText(this,'w0');"          <option value="analysis">Analysis</option> -->
	<!--                                                     <option value="algebra">Linear Algebra</option> -->
	<!--                                                     <option value="discrete">Discrete Mathematics</option> -->
	<!--                                                     <option value="numerical">Numerical Analysis</option> -->
	<!--                                                     <option value="probability">Probability Theory</option> -->
													</c:forEach>
                                                </optgroup>
                                            </select>
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">Status <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" name="status1" id="status" >
                                                <option value="">Select...</option>
                                              	  <c:forEach var="ticketStatus" items="${ticketStatusList}">
														<option value="${ticketStatus.ticketStatusName}">${ticketStatus.ticketStatusName}</option>
													</c:forEach>
                                            </select>
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">Maximum Days Granted <span class="required" aria-required="true">*</span></label>
                                            <input type="text" class="form-control" name="ticketMaxDays" class="ticketMaxDays" placeholder="">
                                        </div> 
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
						<div class="col-md-7">
						<c:if test="${ticketList ne null && !empty ticketList }">
                            <form action="updateTicketingSLA.html" method="post">
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">View Ticketing SLA</h2>
                                </header>
                                <div class="panel-body">

                                    <table class="table table-bordered table-striped mb-none">
                                        <thead>
                                            <tr>
                                                <th>Module Name</th>
                                                <th>Status</th>
                                                <th>Maximum Days Granted</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>    
                                        		<c:forEach var="ticket" items="${ticketList}" varStatus="i">                                        
		                                            <tr>                                                
		                                                <td><input type="text" class="form-control" nid="moduleName-${i.index}" name="moduleName${i.index}" value="${ticket.moduleName}"  placeholder="" disabled></td>
		                                                <td>
		                                                    <select class="form-control" disabled>
		                                                        <option value="">Select...</option>
		                                                        	<c:forEach var="ticketStatus" items="${ticketStatusList}">
																		<c:choose>
																			<c:when test="${ticketStatus.ticketStatusName eq ticket.ticketSummary}">
																				<option value="${ticketStatus.ticketStatusName}" selected="selected">${ticketStatus.ticketStatusName}</option>
																			</c:when>
																			<c:otherwise>
																				<option value="${ticketStatus.ticketStatusName}">${ticketStatus.ticketStatusName}</option>
																			</c:otherwise>
																		</c:choose>					
																	</c:forEach>	
		                                                    </select>
		                                                </td>
		                                                <td><input type="text" class="form-control" id="maxDays${i.index}" name="ticketMaxDays${i.index}" value="${ticket.ticketMaxDays}" placeholder=""  disabled></td>
		                                                <td>
		                                                    <a class="on-default edit-row" href="#"><i class="fa fa-pencil"></i></a>
		                                                    <a class="hidden on-editing save-row" href="#"><i class="fa fa-save"></i></a>
		                                                </td>
		                                            </tr>
                                           		 </c:forEach>
<!--                                             <tr>                                                 -->
<!--                                                 <td><input type="text" class="form-control" name="firstname" placeholder="" value="ADMISSION" disabled></td> -->
<!--                                                 <td> -->
<!--                                                     <select class="form-control" disabled> -->
<!--                                                         <option value="">Select...</option> -->
<!--                                                     </select> -->
<!--                                                 </td> -->
<!--                                                 <td><input type="text" class="form-control" name="firstname" placeholder="" value="5" disabled></td> -->
<!--                                                 <td> -->
<!--                                                     <a class="on-default edit-row" href="#"><i class="fa fa-pencil"></i></a> -->
<!--                                                     <a class="hidden on-editing save-row" href="#"><i class="fa fa-save"></i></a> -->
<!--                                                 </td> -->
<!--                                             </tr> -->
                                        </tbody>
                                    </table>
                                </div>
                                <footer style="display: block;" class="panel-footer">
                                    <button class="btn btn-primary">Submit </button>
                                    <button type="reset" class="btn btn-default">Reset</button>
                                </footer>
                            </section>
                            </form>
                            </c:if>
						</div>
					</div>	
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>