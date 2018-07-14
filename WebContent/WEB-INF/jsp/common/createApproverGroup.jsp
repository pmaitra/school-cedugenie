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
<body>
	<div class="row">
			<div class="col-md-12">
				  <form method="POST" id="createApproverGroup" name="createApproverGroup" action="createApproverGroup.html" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Approver</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                       <div class="col-md-5"> 
										 <div class="form-group">
                                            <label class="control-label">Approver Group Name</label>
                                            <input type="text" class="form-control"  id="approverGroupName" name="approverGroupName" placeholder="">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Approver Group Code</label>
                                            <input type="text" class="form-control"  id="approverGroupCode" name="approverGroupCode" placeholder="">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Approver Group Description</label>
                                            <input type="text" class="form-control"  id="approverGroupDesc" name="approverGroupDesc" placeholder="">
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
<!--                                         <div class="panel-body"> -->

		                                   <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
		                                            <thead>
		                                                <tr>
		                                                	<th>Select</th>
		                                                    <th>Resource Name</th>
		                                                    <th>Designation</th>
		                                                    
		                                                </tr>
		                                            </thead>
		                                            <tbody>
		                                            	<c:forEach var="resource"  items="${resourceDetailsList}" varStatus="i">
		                                            	
		                                               		 <tr>
		                                                		 <td><input type="checkbox" name="userName"  id = "userName${i.index}" value = "${resource.code}"></td>
																 <td><input type = "text" name ="name" id = "${resource.name}${i.index}"  value = "${resource.name}" readonly></td>
																 <td><input type = "text" name ="${resource.resourceTypeName}" id = "${resource.resourceTypeName}${i.index}" value = "${resource.resourceTypeName}" readonly></td>
		                                               		
		                                               		 </tr>
		                                                </c:forEach>
		                                            </tbody>
		                                        </table>
		                                </div>
<!--                                          </div>   -->
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" class="btn btn-primary">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
                            </div>
						</div>


<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>