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
						<div class="col-md-5">
						  <form method="POST" id="createNewJob" name="createNewJob" action="createNewJob.html" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Job</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
										 <div class="form-group">
                                            <label class="control-label">Job Name</label>
                                            <input type="text" class="form-control"  id="jobTypeName" name="jobTypeName" placeholder="">
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label">Job Description</label>
                                            <textarea maxlength="140" data-plugin-maxlength="" rows="3" class="form-control"  id = "jobTypeDesc"  name = "jobTypeDesc"></textarea>
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
						<c:choose>
							<c:when test="${jobDetailsList eq null}">		
									<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
										<span id="infomsg">No Job Found</span>	
									</div>		
							</c:when>
						<c:otherwise>
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Existing Jobs</h2>
                                </header>
                                <div class="panel-body">

                                   <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                            <thead>
                                                <tr>
                                                    <th>Job Name</th>
                                                    <th>Job Description</th>
                                                    
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<c:forEach var="job"  items="${jobDetailsList}">
                                            	
                                               		 <tr>
                                                		 <td>${job.jobTypeName}</td>
														 <td>${job.jobTypeDesc}</td>
														 
                                                    	 
                                               		 <!-- 	Naimisha -->
                                               		 </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                </div>
                            </section>
                            </c:otherwise>
                            </c:choose>
						</div>
					</div>	
		

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>