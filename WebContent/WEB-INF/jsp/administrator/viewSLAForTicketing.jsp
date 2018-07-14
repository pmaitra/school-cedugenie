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
<script>

function updateSLATicket(rowId){
	var moduleName = document.getElementById("moduleName"+rowId).value;
	var ticketStatus = document.getElementById("ticketStatus"+rowId).value;
	var maximumDays = document.getElementById("maxDays"+rowId).value;
	
	$('#updateSLADetails > tbody').empty();
 	if((moduleName != null && moduleName!="") && (ticketStatus != null && ticketStatus!="") && (maximumDays != null && maximumDays!="")){
 		var row = "<tbody>";
 		row += "<tr><td>"+moduleName+"</td>"
 		+"<td>"+ticketStatus+"</td>"
 		+"<td><input type='text' id='maximumdays' name='maximumdays' class='form-control' value='"+maximumDays+"' required></td></tr>";    				
 		
 		$("#updateSLADetails").append(row);
 	} 
	$('#modalInfo').fadeIn("fast");
    
 	var btn = document.getElementById("updateSLA");
 	btn.setAttribute("onclick","saveSLATicket('"+rowId+"','"+moduleName+"','"+ticketStatus+"');");
};

function saveSLATicket(rowid, moduleName, ticketStatus){
	var reg2=/^[1-9]\d*$/;
	var newmaxday = document.getElementById("maximumdays").value;
	if(newmaxday == null || newmaxday == ""){
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Maximum Days can not be Empty.";
		return false;
	}else{
		if(!newmaxday.match(reg2)){
			document.getElementById("warningmsg").style.display = 'block';			
			document.getElementById("warningmsg").innerHTML = "Maximum Days can contain numbers greater than Zero only.";
			return false;
		}else{
			document.getElementById("serialNoDB").value = rowid;
			document.getElementById("newModuleName").value=moduleName;
			document.getElementById("newTicketStatus").value=ticketStatus;
			document.getElementById("newMaxDays").value=newmaxday;
			
			document.getElementById("warningmsg").style.display = 'none';	
			document.updateTicketingSLA.submit();
		}
	}
	
};

function closeWarning(){
	document.getElementById("warningmsg").style.display = 'none';	
};
</script>
</head>
<body>
	<header class="page-header">
			<h2>Set up SLA For Ticketing</h2>	<!--  ADDED BY SAIF 29-03-2018 -->
	</header>
		<div class = "content-padding">
			<div class="row">
						<div class="col-md-5">
							<c:if test="${errorMessage ne null}">
								<div id="errorbox" class="alert alert-danger" style="visibility:visible;">
									<span class="errormsg">${errorMessage}</span>
								</div>
							</c:if>
							<c:if test="${updateSuccessStatus ne null}">
									<div id="successbox" class="alert alert-success" style="visibility:visible;">
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
											<label class="col-sm-4 control-label">Approver Group Name :<span class="required" aria-required="true">*</span> </label>
											<div class="col-sm-8">
											<ul class="ulList">
												<c:forEach var="approver" items="${approverList}" varStatus="i">
												<li>
													<input type="checkbox" value="${approver.approverGroupCode}" name="approverGroupName"> ${approver.approverGroupName}
												</li>
												</c:forEach>
											</ul>
											</div>
										</div>
                                        
                                        <div class="form-group">
                                            <label class="control-label">Status <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" name="status" id="status" >
                                                <option value="">Select...</option>
                                              	  <c:forEach var="ticketStatus" items="${ticketStatusList}">
														<option value="${ticketStatus.ticketStatusName}">${ticketStatus.ticketStatusName}</option>
													</c:forEach>
                                            </select>
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">Maximum Days Granted <span class="required" aria-required="true">*</span></label>
                                            <input type="text" class="form-control" name="ticketMaxDays" id="ticketMaxDays" placeholder="">
                                        </div> 
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" class="btn btn-primary" onclick = "return validate()">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
						<div class="col-md-7">
						<c:if test="${ticketList ne null && !empty ticketList }">
                            <form id="updateTicketingSLA" name="updateTicketingSLA" action="updateTicketingSLA.html" method="post">
                              <input type="hidden"  name ="serialNoDB"  id ="serialNoDB" > 
                              <input type="hidden"  name ="newModuleName"  id ="newModuleName" > 
                              <input type="hidden"  name ="newTicketStatus"  id ="newTicketStatus" > 
                              <input type="hidden"  name ="newMaxDays"  id ="newMaxDays" >    
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">View Ticketing SLA</h2>
                                </header>
                                <div class="panel-body">

                                    <table id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
                                        <thead>
                                            <tr>
                                                <th>Module Name</th>
                                                <th>Status</th>
                                                <th>Maximum Days Granted</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody> 
                                      		<div id="ticketStatusNames">
                                      			<c:forEach var="ticketStatusName" items="${ticketStatusList}">
	                                      			<input type="hidden"  name ="ticketStatusList"  id ="ticketStatusList" value="${ticketStatusName.ticketStatusName}">
	                                      		</c:forEach>
                                      		</div>
                                        		<c:forEach var="ticket" items="${ticketList}" varStatus="i">      
                                         		
		                                            <tr>                                                
		                                                <td>
		                                                	
		                                                	<input type="hidden" class="form-control" id="moduleName${i.index}" name="moduleName${i.index}" value="${ticket.moduleName}"  placeholder="" readonly>
		                                                	${ticket.moduleName}
		                                                </td>
		                                                
		                                                <td>
		                                                <input type="hidden" class="form-control" id="slaId${i.index}" name="slaId${i.index}" value="${ticket.ticketRecId}"  placeholder="" readonly>
		                                                    <c:forEach var="ticketStatus" items="${ticketStatusList}">
																<c:choose>
																	<c:when test="${ticketStatus.ticketStatusName eq ticket.ticketSummary}">
																		<input type="hidden" class="form-control" id="ticketStatus${i.index}" name="status${i.index}" value="${ticketStatus.ticketStatusName}" placeholder=""  readonly>
																		${ticketStatus.ticketStatusName}
																	</c:when>
																			
																</c:choose>					
																	</c:forEach>	
		                                                  <!--   </select> -->
		                                                </td>
		                                                <td>
		                                                	<input type="hidden" class="form-control" id="maxDays${i.index}" name="ticketMaxDays${i.index}" value="${ticket.ticketMaxDays}" placeholder=""  readonly>
		                                                	${ticket.ticketMaxDays}
		                                                </td>
		                                               <td class="actions">
															<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" onclick="updateSLATicket('${i.index}')"><i class="fa fa-pencil"></i></a>
															<a class="on-default remove-row" href="inactiveSLATicket.html?slaID=${ticket.ticketRecId}" ><i class="fa fa-trash-o"></i></a>
														</td>
		                                            </tr>
                                           		 </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </section>
                            <!-- popup Window code -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
									<section class="panel">
										<header class="panel-heading">
											<!-- <h2 class="panel-title">Approver Group Name - PO_Approver</h2> -->
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id="updateSLADetails">
												<thead>
													<tr>
														<th>Module Name</th>
		                                                <th>Status</th>
		                                                <th>Maximum Days Granted</th>
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
													<button id="updateSLA" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
                            </form>
                            </c:if>
						</div>
					</div>
		</div>
	 	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/administrator/viewTicketingSLA.js"></script>
<script src="/cedugenie/js/administrator/viewSLAForTicketing.editable.js"></script>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
</body>
</html>