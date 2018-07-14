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
<title>Pending Leave Details</title>
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
<!-- start: page -->
<form action="leaveResponse.html" method="post">
<div class="row">
	<div class="col-md-12">						  
		<section class="panel">
		<header class="panel-heading">
			<div class="panel-actions">
				<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
			</div>										
			<h2 class="panel-title">Leave Request Details</h2>										
			</header>
			<div style="display: block;" class="panel-body">                                        
	            <div class="col-md-6">
	               <div class="form-group">
	                    <label class="control-label">Request ID</label>                                         
	                    <input type="text" class="form-control" name="taskCode" id="taskCode" value="${leaveDetails.leaveCode}" readonly>
	                </div>
	                <div class="form-group">
	                    <label class="control-label">Applied On</label>
	                    <input type="text" class="form-control" name="appliedDate" id="appliedDate" value="${leaveDetails.appliedOn}" readonly>
	                </div>
	                <div class="form-group">
	                    <label class="control-label">User Id</label>
	                    <input type="text" class="form-control" name="userId" id="userId" value ="${leaveDetails.userId}" readonly>                                               
	                </div> 
	                 <div class="form-group">
	                    <label class="control-label">No of Leave Applied</label>
	                    <input type="text" class="form-control" name="totalRequestedLeave" id="totalRequestedLeave" value ="${leaveDetails.totalRequestedLeave}" readonly>                                               
	                </div>                                              
	            </div>
	            <div class="col-md-6">
	               <div class="form-group">
	                    <label class="control-label">Title</label>
	                    <input type="text" class="form-control" name="title" id="title" value="${leaveDetails.title}" readonly />
	                </div>
	                <div class="form-group">
	                    <label class="control-label">Requested Leave Type</label>
	                   <input type="text" class="form-control" name="leaveType" id="leaveType" value="${leaveDetails.leaveType}" readonly>
	                </div>                        
	                
	                <div class="form-group">
	                    <label class="control-label">Leave Start Date</label>
	                    <input type="text" class="form-control" name="startDate" id="startDate" value="${leaveDetails.startDate}" readonly>
	                </div>
	                <div class="form-group">
	                    <label class="control-label">Leave End Date</label>
	                   <input type="text" class="form-control" name="endDate" id="end" value="${leaveDetails.endDate}" readonly>
	                </div>
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
	                 <h2 class="panel-title">Leave Details</h2>
	             </header>                                
	             <c:choose>
				<c:when test="${staffLeaveDetails eq null}">
					<span>${message}</span>	
				</c:when>
				<c:otherwise> 
		 		<div class="panel-body">
                <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                    <thead>
                        <tr>
                            <th>Leave Type</th>                           
                            <th>Alloted leave</th>                         
                            <th>Available Leave</th>
                            <th>Assign For(days)</th>
                            <th>Revised Available Leave</th>
                        </tr>            
                    </thead>
                    <tbody>
		                <c:if test= "${staffLeaveDetails ne null && staffLeaveDetails.size() ne 0}">
							<c:forEach var="leave" items="${staffLeaveDetails}" varStatus="indx">
								<tr>
									<td>
										${leave.leaveType}
										<input type="hidden" name="leaveList[${indx.index}].leaveType" value="${leave.leaveType}" readonly="readonly"/>
									</td>						
									<td>${leave.totalAvailLeave}</td>								
									
									<td>
										${leave.remainingLeave}
										<input type="hidden" name="remainingLeave" id="remainingLeave${indx.index}" value="${leave.remainingLeave}" readonly="readonly"/>
									</td>
									<td><input type="text" class="form-control" name="leaveList[${indx.index}].totalRequestedLeave" id="applyingLeave${indx.index}" value="0" style="width:50px;" onClick="clearApplyingLeaveOnClick(${indx.index})" onblur="calculateRevisedLeave(${indx.index})"/></td>
									<td>
										<input type="text" class="form-control" name="remainingLeave" id="revisedRemainingLeave${indx.index}" readonly="readonly" value="${leave.remainingLeave}" style="width:50px;"/>
										<input type="hidden" id="${indx.index}" value="${leave.totalAvailLeave}"/>
									</td>		
								</tr>						
							</c:forEach>
						</c:if>                                      
                    </tbody>
                </table>
                </div>                               
              	</c:otherwise>
              </c:choose>
             </section> 
             <footer style="display: block;" class="panel-footer">
				<button class="btn btn-primary" type="submit" id="submit" name="submit" >Submit</button>
				<button type="reset" class="btn btn-default">Reset</button>
			</footer>                  
	</div>
</div> 
</form>      
<!-- end: page -->


<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/erp/pendingLeaveDetails.js"></script>
</body>
</html>