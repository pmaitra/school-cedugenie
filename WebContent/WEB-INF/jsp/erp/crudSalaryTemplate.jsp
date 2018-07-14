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


					<div class="row">
						<div class="col-md-5">
						  <form:form name="submitSalaryTemplate" action="submitSalaryTemplate.html" method="POST">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create New Salary Template</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        										
                                        <div class="form-group">
                                            <label class="control-label">Enter Salary Template Type <span class="required" aria-required="true">*</span></label>
                                            <input type="text" class="form-control" name="salaryTemplateType" id="salaryTemplateType" readonly="readonly" placeholder="" value="FIXATION OF PAY">
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">Enter Salary Template Name <span class="required" aria-required="true">*</span></label>
                                            <input type="text" class="form-control" name="salaryTemplateName" id="salaryTemplateName" placeholder="">
                                        </div> 
                                        <div class="form-group">
                                            <label class="control-label">Enter Salary Template Description</label>
                                            <input type="text" class="form-control" name="salaryTemplateDesc" id="salaryTemplateDesc" placeholder="">
                                        </div> 
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" class="btn btn-primary" id="submitButton" onclick="return validateSalaryTemplateForm();">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
						
						<c:if test="${submitResponse ne null}">			
							<c:if test="${submitResponse eq 'Success'}">
								<div class="successbox" id="successbox" style="visibility:visible;">
									<span id="successmsg" style="visibility:visible;">Salary Template Successfully Created.</span>	
								</div>
							</c:if>
							<c:if test="${submitResponse eq 'Fail'}">
								<div class="errorbox" id="errorbox" style="visibility:visible;">
									<span id="errormsg" style="visibility:visible;">Salary Template Creation Failed.</span>	
								</div>
							</c:if>			
						</c:if>
						<c:if test="${updateResponse ne null}">	
							<c:if test="${updateResponse eq 'Success'}">
								<div class="successbox" id="successbox" style="visibility:visible;">
									<span id="successmsg" style="visibility:visible;">Salary Template Successfully Updated.</span>	
								</div>
							</c:if>
							<c:if test="${updateResponse eq 'Fail'}">
								<div class="errorbox" id="errorbox" style="visibility:visible;">
									<span id="errormsg" style="visibility:visible;">Salary Template Updatation Failed.</span>	
								</div>
							</c:if>			
						</c:if>	
						
						<c:choose>
							<c:when test="${salaryTemplateList == null}">
								<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
									<span id="infomsg">No Salary Template Created Yet</span>	
					 			</div>
							</c:when>	
						<c:otherwise>
						
						
						<div class="col-md-7">	
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">View / Edit Salary Template</h2>
                                </header>
                                <div class="panel-body">

                                    <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                        <thead>
                                            <tr>
                                                <th>Salary Template Type</th>
                                                <th>Salary Template Name</th>
                                                <th>Salary Template Desc</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody> 
                                        	<c:forEach var="salaryTemplate" items="${salaryTemplateList}" varStatus="i">                                           
	                                            <tr>                                                
	                                                <td>
	                                                	<input type="text" class="form-control" id="salaryTemplateType${i.index}" name="salaryTemplateType" placeholder="" value="${salaryTemplate.salaryTemplateType}" readonly="readonly" disabled>
                                                	</td>
	                                                <td>
	                                                	<input type="text" class="form-control" id="textSalaryTemplateName${i.index}" name="salaryTemplateName" placeholder="" value="${salaryTemplate.salaryTemplateName}" disabled>
                                                	</td>
	                                                <td>
	                                                	<input type="text" class="form-control" id="textSalaryTemplateDesc${i.index}" name="salaryTemplateDesc" placeholder="" value="${salaryTemplate.salaryTemplateDesc}" disabled>
                                                	</td>
	                                                <td>
	                                                    <a class="on-default edit-row" href="#"><i class="fa fa-pencil"></i></a>
	                                                    <a class="hidden on-editing save-row" href="#"><i class="fa fa-save"></i></a>
	                                                </td>
	                                            </tr>
	                                         </c:forEach>   
                                        </tbody>
                                    </table>
                                </div>
                            </section>
						</div>
						</c:otherwise>
						</c:choose>
					</div>	
					


<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>