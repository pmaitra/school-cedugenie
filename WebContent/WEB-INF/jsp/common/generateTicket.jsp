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
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       } .mb-md{
       	   display: none;
       }
</style>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
       .datepicker-dropdown{
        display: none !important;
       }
       #ui-datepicker-div{
       	z-index:99999 !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Generate Ticket</h2>
</header>
<div class="content-padding">
		<div class="row">				
			<%-- <c:if test="${ticket != null}">		 --%>	
				<c:if test="${ticket.status == 'success'}">
					<div class="alert alert-success">
						<strong>Ticket Raised Successfully. Your ticket ID is - ${ticket.ticketRecId}</strong>	
					</div>					
				</c:if>
				<c:if test="${ticket.status == 'fail'}">
					<div class="alert alert-danger">
						<strong>Problem Occur While Raising Ticket</strong>	
					</div>					
				</c:if>
			<%-- </c:if> --%>
		
					 <div class="row">
						<div class="col-md-8 col-md-offset-2">
						  <form:form modelAttribute="FORM" method="POST" id="generateTicket" name="generateTicket" action="generateTicket.html" enctype="multipart/form-data">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Generate Ticket</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										<div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Reported By <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" name="reportedBy" id="reportedBy" value="${reportedBy}" readonly="readonly"  placeholder="" required>                                                    
                                               		
                                                </div>
                                            </div>
                                             <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Department <span aria-required="true" class="required">*</span></label>                                                
                                                    <select class="form-control" name="department" id="department" required>
                                                        <option value="">Select...</option>
															<c:forEach var="dept" items="${departmentList}" >
																<%-- <option value="${dept.departmentCode}">${dept.departmentName}</option> --%>
																<option value="${dept.departmentCode}" ${dept.departmentCode eq departmentObj.departmentCode?'selected':value} >${dept.departmentName}</option>
															</c:forEach>
															
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Status <span aria-required="true" class="required">*</span></label>                                                
                                                     <%--<div class="form-group" style="margin-top: 5px;">
                                                        <label class="radio-inline radio-primary"> 
                                                            <input type="radio" name="status" id="status" checked="checked" value="${ticketStatusObj.ticketStatusCode}" checked> ${ticketStatusObj.ticketStatusName}  --%>
                                                        	 <select class="form-control" name="status" id="status" required>
	                                                        	<option value="">Select...</option>
	                                                       
																<c:forEach var="ticketStatus" items="${ticketStatusObjList}" >
																	<option value="${ticketStatus.ticketStatusCode}">${ticketStatus.ticketStatusName}</option>
																</c:forEach>
														
                                                   			 </select>
                                                       <!--  </label> 
                                                    </div>-->
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                          
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Ticket Summary <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" name="ticketSummary" id="ticketSummary" placeholder="" required>                                                    
                                                </div>
                                            </div>
                                           <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Category <span aria-required="true" class="required">*</span></label>                                                
                                                    <select class="form-control" name="ticketService.ticketServiceCode" id="ticketServiceName" required>
                                                        <option value="">Select...</option>
                                                        <%-- <c:if test="${userIdList != null}"> --%>
															<c:forEach var="job" items="${categoryList}" >
																<option value="${job.taskCode}">${job.taskName}</option>
															</c:forEach>
														<%-- </c:if> --%>
                                                    </select>
                                                </div>
                                            </div> 
                                        </div>
                                        <div class="row">
                                        	<div class="col-md-4" style = "display:none" id = "standardDiv">
                                            	<label class="control-label">Standard <span aria-required="true" class="required">*</span></label>
                                            		<select class="form-control" name="standard" id="standard" >
                                            			<option value="">Select...</option>
                                            			<c:forEach var="standard" items="${standardList}" >
															<option value="${standard.standardCode}">${standard.standardCode}</option>
														</c:forEach>
                                            		</select>
                                            </div>
                                             <div class="col-md-4" style = "display:none" id = "sectionDiv">
                                            	<label class="control-label">Section <span aria-required="true" class="required" >*</span></label>
                                            		<select class="form-control" name="section" id="section" disabled>
                                            			<option value="">Select...</option>
                                            			
                                            		</select>
                                            </div>
                                             <div class="col-md-4" style = "display:none" id = "rollDiv">
                                            	<label class="control-label">Name: <span aria-required="true" class="required" >*</span></label>
                                            		<select class="form-control" name="rollNumber" id="rollNumber" disabled>
                                            			<option value="">Select...</option>
                                            			
                                            		</select>
                                            </div>
                                        </div>
                                        <div class="row">
                                        	 
                                            <div class="col-md-4" style = "display:none" id = "leaveStartDiv">
	                                            <label class="control-label"><b>From Date :<span class="required" aria-required="true">*</span></b></label>
			                                        <label class="control-label">
			                                            <div class="input-group">
			                                                <span class="input-group-addon">
			                                                    <i class="fa fa-calendar"></i>
			                                                </span>
			                                                <input type="text" class="form-control" name="fromDate" id="fromDate"   /> 
			                                           	</div>
		                                        </label>
                                            </div>
                                            
                                            <div class="col-md-4" style = "display:none" id = "leaveEndDiv">
	                                            <label class="control-label"><b>To Date :<span class="required" aria-required="true">*</span></b></label>
			                                        <label class="control-label">
			                                            <div class="input-group">
			                                                <span class="input-group-addon">
			                                                    <i class="fa fa-calendar"></i>
			                                                </span>
			                                                <input type="text" class="form-control" name="toDate" id="toDate"   disabled/> 
			                                           	</div>
		                                        </label>
                                            </div>
                                        </div>
                                       <div class="row">
                                           <div class="col-md-12">
                                           <div class="form-group">
                                                <label class="control-label">Description <span aria-required="true" class="required">*</span></label>
                                                <textarea class="form-control" rows="3" data-plugin-maxlength="" maxlength="500" onfocus="clearContents(this);" name="description" required></textarea>                                                    
                                            </div>
                                           </div>
                                       </div>
                                        
                                         
                                        <div id = divForKey style="display:none">
                                       
                                        </div>
                                         
                                        <div class="row">
                                        	<hr>
                                            <div class="col-md-4">
                                               <div class="form-group">
                                                <label class="control-label">Upload Related Document </label>
                                                <table>
                                                    <tr>
                                                        <td><input type="file" name="uploadFile.ticketingRelatedFile" ></td>
                                                        <td><input id="addFile2" class="addFileClassName" type="button" value="ADD"/></td>
                                                    </tr>
                                                    <!-- tr>
                                                        <td><input type="file"></td>
                                                        <td><a style="margin:10px;" href="#" class="on-default remove-row"><i class="fa fa-plus-square"></i></a></td>
                                                    </tr> -->
                                                </table>                                                    
                                               </div>
                                            </div>
                                        </div>
                                         <div class="row">
                                         	<div class="col-md-12"  id = "surveyDiv" style = "display:none">
												<section class="panel">
													<header class="panel-heading">
														<div class="panel-actions">
															<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
														</div>
														<!-- <h2 class="panel-title">Allocation</h2> -->
													</header>
													<div class="panel-body">
														
														<table class="table table-bordered table-striped mb-none" id = "surveyTable">
															 <thead id = "surveyhead">
																<!-- <tr>
																	<th>Resource Type</th>
																	<th>User Id</th>
																	<th>User Name</th>
																	<th>Description</th>
																	<th>Add</th>
																</tr> -->
															</thead> 
															<tbody id = "surveyBody">
																
															</tbody>
														</table>
													</div>
												</section>
											</div>
                                         </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary"  type="submit">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                           </form:form>
						</div>	
                        
					</div>	
				</div>
			</div>


<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/ticketing/generateTicket.js"></script>
<script src="/icam/assets/custom-caleder/jquery-ui.js" type="text/javascript"></script>
<script type="text/javascript">
	$(".datepicker").datepicker({dateFormat: 'dd/mm/yyyy'});
	$("#fromDate").datepicker({
	    minDate: 0,
	    maxDate: '+1Y+6M',
		dateFormat: 'dd/mm/yy',
	    onSelect: function (dateStr) {
	        var min = $(this).datepicker('getDate'); // Get selected date
	        $("#toDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
	        $("#toDate").removeAttr('disabled','disabled');
	    }
	});
	
	$("#toDate").datepicker({
	    minDate: 0,
	    maxDate: '+1Y+6M',
		dateFormat: 'dd/mm/yy',
	    onSelect: function (dateStr) {
	        var min = $(this).datepicker('getDate'); // Get selected date
	    }
	});
</script>
</body>
</html>