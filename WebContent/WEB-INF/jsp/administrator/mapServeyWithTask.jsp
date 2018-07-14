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
<title>Closed ticket list</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
				<h2>Map Category With Survey</h2>
			</header>
			<div class="content-padding">
					<c:if test="${status eq 'success'}">
						<div class="alert alert-success" id="successboxmsgbox" >
							<span>Mapped Successfully</span>	
						</div>
					</c:if>
			
					<c:if test="${status eq 'fail'}">
							<div class="alert alert-danger" id="errormsgbox" >
								<span>Mapped Not Successfully</span>	
							</div>
					</c:if> 
					
           <div class="row">
						<div class="col-md-5">
						  <form action="submitMapWithServey.html" method="post" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<!-- <h2 class="panel-title">Map Task With Survey</h2> -->										
									</header>
									<c:forEach var="approve"  items="${approvalOrderList}">
                                           <input type="hidden" name="hiddenApprovers" value="${approve.approverGroupName}*${approve.approverGroupDesc}"/>
                                     </c:forEach>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label">Category Name <span class="required" aria-required="true">*</span></label>
                                             <select class="form-control" name="approverGroupDesc" id="approverGroupDesc" required>
                                                <option value="">Select...</option>
                                                <c:forEach var="job" items="${categoryList}">
													<option value="${job.jobTypeCode}">${job.category}</option>
												</c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Survey<span class="required" aria-required="true">*</span></label>
                                             <select class="form-control" name="surveyName" id="surveyName" required>
                                                <option value="">Select...</option>
                                                <c:forEach var="survey" items="${surveyList}">
													<option value="${survey.surveyId}">${survey.surveyName}</option>
												</c:forEach>
                                            </select>
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

                                    <h2 class="panel-title">Existing Category Mapping List</h2>
                                </header>
                                <div class="panel-body">

                                    <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                        <thead>
                                            <tr>
                                                <th>Task Name</th>
                                                <th>Survey Name</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach var="taskSurvey"  items="${taskSurveyList}">
                                        	
	                                            <tr>
	                                                <td>${taskSurvey.approverGroupName}</td>
	                                           		<td>${taskSurvey.jobTypeName}</td>
	                                           		
	                                           		
	                                            </tr>
                                           </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </section>
						</div> 
					</div>	
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/administrator/mapServeyWithTask.js"></script>

</body>
</html>