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
	<header class="page-header">
			<h2>Create Recepient Group</h2>	<!--  ADDED BY SAIF 28-03-2018 -->
	</header>
		<div class = "content-padding">
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
						<div class="col-md-12">
						  <form method="POST" action="insertApprovers.html">
						  	<c:choose>
								<c:when test="${resourceTypeList eq null || resourceTypeList.size() eq 0}">
									<div class="errorbox" id="errorbox" style="visibility: visible;">
										<span id="errormsg">No Resource Type Found To Create Approvers</span>	
									</div>						
								</c:when>
							<c:otherwise>
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Approver Group</h2>										
									</header>
									<c:forEach var="approverGroup"  items="${pagedListHolder}">	
                                       	<input type="hidden" class="form-control" name="hiddenApproverGroupName" value = "${approverGroup.approverGroupName}" />
                                      </c:forEach>
									<div style="display: block;" class="panel-body">                                        
										<div class="col-md-5">
                                            <div class="form-group">
                                                <label class="control-label">Resource Type <span class="required" aria-required="true">*</span></label>
                                                <select class="form-control" name="resourceTypeName" id="resourceTypeName" required>
                                                    <option value="" >Select...</option>
                                                  	  <c:forEach var="resourceType" items="${resourceTypeList}">
															<option value="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
														</c:forEach>
                                                </select>
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">Approver Group Name <span class="required" aria-required="true">*</span></label>
                                                <input type="text" class="form-control" name="approverGroupName" id="approverGroupName" placeholder=""  pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$"  title="The full name can consist of alphabetical characters and spaces only" required>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Approver Group Description <span class="required" aria-required="true">*</span></label>
                                                <textarea maxlength="140" data-plugin-maxlength="" rows="3" class="form-control" name="approverGroupDesc" id="approverGroupDesc" required></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Approval Process <span class="required" aria-required="true">*</span></label>
                                                <div style="margin-top: 5px;" class="form-group">
                                                    <label class="radio-inline radio-primary"> 
                                                        <input type="radio" checked="" name="approvalProcess" value="SERIAL"> SERIAL  
                                                    </label>
                                                    <label class="radio-inline radio-primary"> 
                                                        <input type="radio" name="approvalProcess" value="PARALLEL"> PARALLEL 
                                                    </label>
                                                 </div>
                                            </div> 
                                            
                                        </div>
                                        <div class="col-md-7">
                                            <table class="table table-bordered table-striped mb-none" id = "userTable">
                                                <thead>
                                                    <tr>
                                                        <th>User Id</th>
                                                        <th>Contact Name</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>											
                                                        <td><input type="text" class="form-control" name="userName" id="userId0" placeholder="" required></td>
                                                        <td><input type="text" class="form-control" id="name0" name="name" placeholder="" readonly></td>
                                                        <td>
                                                            <a class="mb-xs mt-xs mr-xs  btn btn-info" id="addrow" href="javascript:addrows()">Add</a>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id = "submit" onclick = "return validate()">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
							</c:otherwise>
						</c:choose>
                      </form>
					</div>
					   <div class="col-md-12">
						 <form:form >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Approver Group List</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                        
                                        <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                            <thead>
                                                <tr>
                                                    <th>Approver Group Name</th>
                                                    <th>Created On</th>
                                                    <th>Approver Group Description</th>
                                                    <th>Approver Process</th>
                                                    <th>Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                             <c:forEach var="approverGroup"  items="${pagedListHolder}">	
                                                <tr>
                                                    <td>${approverGroup.approverGroupName}</td>
                                                    <td>${approverGroup.status}</td>
                                                    <td>${approverGroup.approverGroupDesc} </td>
                                                    <td>
														<c:if test="${approverGroup.serialApproval eq true}">
															SERIAL
														</c:if>
														<c:if test="${approverGroup.parallelApproval eq true}">
															Parallel
														</c:if>
													</td>
                                                    <td>
                                                        <a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic btn btn-info" onclick = "showApproverGroupDetails('${approverGroup.approverGroupCode}')">Details</a>
                                                        <a href="#" class="mb-xs mt-xs mr-xs modal-basic btn btn-danger" onclick="deleteApproverGroupDetails('${approverGroup.approverGroupCode}')">Delete</a>
                                                    </td>
                                                </tr>
                                               </c:forEach>
                                            </tbody>
                                        </table>
                                      <div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
                                            <section class="panel">
                                                <header class="panel-heading">
                                                    <!-- <h2 class="panel-title">Approver Group Name - PO_Approver</h2> -->
                                                </header>
                                                <div class="panel-body">
                                                    <table class="table table-bordered table-striped mb-none" id = "approverGroupDetails">
                                                        <thead>
                                                            <tr>
                                                                <th>Group Name</th>
                                                                <th>User ID</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
<!--                                                             <tr> -->
<!--                                                                 <td>WG CDR VIJIT YUJAL</td> -->
<!--                                                                 <td>ssp_akv</td> -->
<!--                                                             </tr> -->
<!--                                                             <tr> -->
<!--                                                                 <td>LT COL P S LATA</td> -->
<!--                                                                 <td>ssp_vp</td> -->
<!--                                                             </tr> -->
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <footer class="panel-footer">
                                                    <div class="row">
                                                        <div class="col-md-12 text-right">
                                                            <button class="btn btn-info modal-dismiss">OK</button>
                                                        </div>
                                                    </div>
                                                </footer>
                                            </section>
                                        </div> 
									</div>
                                    
								</section>                                  
                            </form:form>
						</div>
				</div>
		</div>
			
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
 <script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/administrator/createApprovers.js"></script>

 
</body>
</html>