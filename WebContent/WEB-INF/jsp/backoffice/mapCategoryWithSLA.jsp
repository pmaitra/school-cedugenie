<!-- added By ranita.sur on 28082017 for mapping with survey -->
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
<title></title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
				<h2>Map Category With SLA</h2>
			</header>
			<div class="content-padding">
					<c:if test="${insertStatus eq 'success'}">
						<div class="alert alert-success" id="successboxmsgbox" >
							<span>Mapped Successfully</span>	
						</div>
					</c:if>
			
					<c:if test="${insertStatus eq 'fail'}">
							<div class="alert alert-danger" id="errormsgbox" >
								<span>Mapped Not Successfully</span>	
							</div>
					</c:if> 
					
           <div class="row">
						<div class="col-md-5">
						  <form action="mapCategoryWithSLA.html" method="post" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<!-- <h2 class="panel-title">Map Task With Survey</h2> -->										
									</header>
									<%-- <c:forEach var="approve"  items="${approvalOrderList}">
                                           <input type="hidden" name="hiddenApprovers" value="${approve.approverGroupName}*${approve.approverGroupDesc}"/>
                                     </c:forEach> --%>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label">Category Name <span class="required" aria-required="true">*</span></label>
                                             <select class="form-control" name="category" id="category" required>
                                                <option value="">Select...</option>
                                                <c:forEach var="job" items="${categoryList}">
													<option value="${job.jobTypeCode}">${job.category}</option>
												</c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                        	<label class="control-label">Acknowledgement Time(hrs and mins)</label>
                                        	 <input type="text" class="form-control" name="acknowledgementTime" id="acknowledgementTime" data-plugin-masked-input data-input-mask="99-99" placeholder="12-12" value=""  placeholder="">
                                        </div>
                                        <div class="form-group">
                                        	<label class="control-label">Completion Time(hrs and mins)</label>
                                        	 <input type="text" class="form-control" name="completionTime" id="completionTime" data-plugin-masked-input data-input-mask="99-99" placeholder="12-12"  value=""   placeholder="">
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

                                    <h2 class="panel-title">Existing Category SLA Mapping List</h2>
                                </header>
                                <div class="panel-body">

                                    <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                        <thead>
                                            <tr>
                                                <th>Category Name</th>
                                                <th>Acknowledgement Time(mins)</th>
                                                <th>Completion Time(mins)</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach var="categorySLA"  items="${allCategorySLAList}">
                                        	
	                                            <tr>
	                                                <td>${categorySLA.ticketCode}</td>
	                                           		<td>${categorySLA.ticketMinDays}</td>
	                                           		<td>${categorySLA.ticketMaxDays}</td>
	                                           		
	                                            </tr>
                                           </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </section>
						</div> 
					</div>	
</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<!-- <script type="text/javascript" src="/icam/js/administrator/mapServeyWithTask.js"></script> -->

</body>
</html>