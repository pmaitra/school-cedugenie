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
					<c:if test="${successMessage ne null}">
						<div class="alert alert-success" id="successboxmsgbox" >
							<span>${successMessage}</span>	
						</div>
					</c:if>
			
					<c:if test="${errorMessage ne null}">
							<div class="alert alert-danger" id="errormsgbox" >
								<span>${errorMessage}</span>	
							</div>
					</c:if>
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
									<c:forEach var="approve"  items="${approvalOrderList}">
                                           <input type="hidden" name="hiddenApprovers" value="${approve.approverGroupName}*${approve.approverGroupDesc}"/>
                                     </c:forEach>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label">Job Name <span class="required" aria-required="true">*</span></label>
                                             <select class="form-control" name="approverGroupDesc" id="approverGroupDesc" required>
                                                <option value="">Select...</option>
                                                <c:forEach var="job" items="${jobDetailsList}">
													<option value="${job.jobTypeName}">${job.jobTypeName}</option>
												</c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Applied By Group <span class="required" aria-required="true">*</span></label>
                                             <select class="form-control" name="approverGroupCode" id="approverGroupCode" required>
                                                <option value="">Select...</option>
                                                <c:forEach var="approver" items="${approverGroupList}">
													<option value="${approver.approverGroupName}">${approver.approverGroupName}</option>
												</c:forEach>
                                            </select>
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">Approved By Group <span class="required" aria-required="true">*</span></label>
                                             <select class="form-control" name="approverGroupName" id="approverGroupName" required>
                                                <option value="">Select...</option>
                                                <c:forEach var="approver" items="${approverGroupList}">
													<option value="${approver.approverGroupName}">${approver.approverGroupName}</option>
												</c:forEach>
                                            </select>
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">Approval Order</label>
                                            <input type = "text" class="form-control" name = "serialNumber" id = "serialNumber" pattern="^[1-9]\d*$" required> 
                                        </div>
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button  type = "submit" class="btn btn-primary" onclick ="return validate()">Submit </button>
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
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach var="approve"  items="${approvalOrderList}">
                                        	
	                                            <tr>
	                                                <td>${approve.approverGroupName}</td>
	                                           		<td>${approve.approverGroupDesc}</td>
	                                           		<td>${approve.serialNumber}</td>
	                                           		<td><a class="on-default remove-row" href="inactiveApprovalOrder.html?approvalOrder=${approve.approverGroupName}~${approve.approverGroupDesc}" ><i class="fa fa-trash-o"></i></a></td>
	                                            </tr>
                                           </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </section>
						</div> 
					</div>	

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
function validate(){
	var userIdAndAccessTypeList = document.getElementsByName("hiddenApprovers");
	var applicant = document.getElementById("approverGroupCode").value;
	var approver = document.getElementById("approverGroupName").value;
	for(var i=0;i<userIdAndAccessTypeList.length;i++){
		var arr = (userIdAndAccessTypeList[i].value).split("*");
		if(approver==arr[0]){
			if(applicant==arr[1]){
				alert("Mapping of Approver And Applicant Already Exists");
				return false;
			}
		}
	}
	return true;
}
</script>
</body>
</html>