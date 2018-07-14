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
       }.mb-md{
       	   display: none;
       }
</style>
</head>
<body>
	<header class="page-header">
			<h2>Create Ticket Status</h2>
		</header>

		<div class="content-padding">
	
			<c:if test="${insertStatus eq 'success'}">
				<div class="alert alert-success">
					<strong>${msg}</strong>
				</div>
			</c:if>
			
			<c:if test="${insertStatus eq 'fail'}">
				<div class="alert alert-danger">
					<strong>${msg}</strong>
				</div>
			</c:if>
			<div class="row">
				<div class="row">
						<div class="col-md-6 col-md-offset-3">
						  <form:form method="POST" id="createTicketStatus" name="createTicketStatus" action="createTicketStatus.html" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Ticket Status</h2>										
									</header>
									
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label"> Ticket Status :<span class="required" aria-required="true">*</span></label>
                                         	 <input type="text" class="form-control" name="status" id="status" placeholder="" required>
                                        </div>                                       
                                         <div  class="form-group" id = "approvalType" >
                                            <label class="control-label">Type:<span class="required" aria-required="true">*</span></label>
                                             
                                                   <input type="radio"  id = "open"  name="approval" value="OPEN" checked> OPEN  
                                               
                                                   <input type="radio" id = "closed" name="approval" value="CLOSED"> CLOSED
                                                   
                                                   <input type="radio" id = "completed" name="approval" value="COMPLETED"> COMPLETED
                                                   
                                                   <input type="radio" id = "rejected" name="approval" value="REJECTED"> REJECTED 
                                              
                                          </div>  
                                   		  <div class="alert alert-danger" id="warningmsg1" style="display: none">
												<span></span>	
											</div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<input class="btn btn-primary" type="button"  onclick ="validateTicketStatus()" value ="Submit" /> 
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 col-md-offset-3">						  
							
	                        
	                        <form>
	                        	<section class="panel">
		                        	<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
										</div>
								
										<h2 class="panel-title">Ticket Status</h2>
									</header>
							
								<div class="panel-body">
									<c:forEach var="ticketStatus" items="${ticketStatusList}" varStatus="i">
										<input type="hidden" name="oldTicketStatus" id="oldTicketStatus" value="${ticketStatus.ticketDesc}">
									</c:forEach>
									<table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
										<thead>
											<tr>
	                                            
												
												<th>Ticket Status</th>
	   											<th>Type</th>                                         
												<!-- <th>Actions</th> -->
											</tr>
										</thead>
										<tbody>
											<c:forEach var="ticketStatus" items="${ticketStatusList}" varStatus="i">
												<tr>											
													<td>
														<input type="hidden" class="form-control" name="ticketCode${i.index}" value="${ticketStatus.ticketCode}" id="ticketCode${i.index}" readonly>
														${ticketStatus.ticketDesc}
													</td>
													<td>
														 <input type="hidden" name="approval${i.index}" id="approval${i.index}" value="${ticketStatus.approval}">
														${ticketStatus.approval}
													</td>
													
													<%-- <td class="actions">
														<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" onclick="updateTaskStatus('${i.index}')"><i class="fa fa-pencil"></i></a>
													</td> --%>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								
	                        </section>
	                       </form>
                    	</div>
                    </div>
			</div>
		</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script>
	function validateTicketStatus(){
		var newTicketStatus = document.getElementById("status").value.toUpperCase();
		var ticketStatusList = document.getElementsByName("oldTicketStatus");	
		var flag = 0;
		for(var i=0; i<ticketStatusList.length; i++){
			var oldVal=ticketStatusList[i].value;
			if(oldVal==newTicketStatus){
				
				flag = 1;
				
			}
		}
		
		if(flag == 1){
			document.getElementById("warningmsg1").style.display = 'block';			
			document.getElementById("warningmsg1").innerHTML = "Duplicate Ticket Status Name";
			return false;
		}
			
		else{
			document.getElementById("warningmsg1").style.display = 'none';
			document.getElementById("warningmsg1").innerHTML = "";
			document.createTicketStatus.submit();
		}
			
	}
</script>
	</body>
</html>
