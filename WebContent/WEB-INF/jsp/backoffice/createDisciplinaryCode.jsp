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


<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

	<header class="page-header">
			<h2>Create Disciplinary Code</h2>
		</header>

	<div class="content-padding">
			<c:if test="${status eq 'success'}">
				<div class="alert alert-success">
					<strong>${msg}</strong>
				</div>
			</c:if>
			
			<c:if test="${status eq 'fail'}">
				<div class="alert alert-danger">
					<strong>${msg}</strong>
				</div>
			</c:if>

	
			
			 <div class="row">
					<div class="col-md-8 col-md-offset-2">
						  <form method="POST" id="createDisciplinaryCode" name="createDisciplinaryCode" action="createDisciplinaryCode.html" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Disciplinary Code</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        <div class="row">
	                                        <div class="col-md-6">
												 <div class="form-group">
		                                            <label class="control-label">Disciplinary Code <span class="required" aria-required="true">*</span></label>
		                                            <input type="text" class="form-control"  id="disciplinaryCode" name="disciplinaryCode" placeholder=""  required>
		                                        </div>
		                                     </div>
		                                     
                                        </div>
                                        <div class="row">
                                        	 <div class="col-md-10">
		                                        <div class="form-group">
		                                            <label class="control-label">Description<span class="required" aria-required="true">*</span></label>
		                                            <textarea maxlength="140" data-plugin-maxlength="" rows="3" class="form-control"  id = "description"  name = "description"  required></textarea>
		                                        </div>
		                                      </div>
                                        </div>
                                        
                                        
                                        
                                       
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id="submit" type = "submit"  >Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8 col-md-offset-2">	
						
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Existing Disciplinary Code</h2>
                                </header>
                                <div class="panel-body">

                                   <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                            <thead>
                                                <tr>
                                                    <th>Disciplinary Code</th>
                                                    <th> Description</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="disciplinary"  items="${disciplinaryCodeList}"> 
                                            	
                                               		 <tr>
                                                		 <td>${disciplinary.disciplinaryCode }</td>
														 <td>${disciplinary.description}</td>
                                               		
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
<script src="/cedugenie/js/administrator/createNewJob.js"></script>
</body>
</html>