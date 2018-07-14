<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
	
<head>

<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Custom Template Create</title>
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
		<%-- <div class="btnsarea01">		
		</div>
			<c:choose>		
			<c:when test="${salaryBreakUpList == null}">
				<div class="btnsarea01" >
					<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
						<span id="infomsg"> Salary Breakup Found</span>
					</div>
				</div>
			</c:when>
			<c:otherwise> --%>
<header class="page-header">
	<h2>Create Custom Salary Template</h2>
</header>
	<div class="content-padding">
	<c:if test="${status eq 'Success'}">
		<div class="alert alert-success">
			<strong>Salary Template Created Successfully.</strong>
		</div>
	</c:if>
	<c:if test="${status eq 'Fail'}">
		<div class="alert alert-danger">
			<strong>Failed To Create Salary Template.</strong>
		</div>
	</c:if>
		<div class="row">
			<form:form method="POST" id="salaryTemplate" name="salaryTemplate" action="submitSalaryTemplate.html">				
				<!-- start: page -->					 
							
								
			
								<!-- start: page -->
								 <div class="row">
									<div class="col-md-8 col-md-offset-2">
									  <form id="form1">
											<section class="panel">
												<header class="panel-heading">
													<div class="panel-actions">
														<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
													</div>
			
													<h2 class="panel-title">Create Custom Salary Template</h2>										
												</header>
												<div style="display: block;" class="panel-body">
			                                        
													<div class="form-group">
														<label class="col-sm-6 control-label">Designation Type</label>
														<div class="col-sm-6">
															 <select class="form-control" id="designationType" name="designationType" >
																<option value="">Select...</option>
																<c:forEach var="designationType" items="${designationTypeList}" >
																	<option value="${designationType.designationTypeCode}">${designationType.designationTypeName}</option>
																</c:forEach>
															</select>
														</div>
													</div>                                        
			                                        <div class="form-group">
														<label class="col-sm-6 control-label">Designation</label>
														<div class="col-sm-6">
															<select class="form-control" id="designation" name="designation" >
																<option value="">Select...</option>
															</select>
														</div>
													</div>
			                                        <div class="form-group">
														<label class="col-sm-6 control-label">Level</label>
														<div class="col-sm-6">
															<select class="form-control" id="level" name="designationLevel" >
																<option value="">Select...</option>
															</select>
														</div>
													</div>
			                                        <div class="form-group">
														<label class="col-sm-6 control-label">Template Name</label>
														<div class="col-sm-6">
															<label class="control-label">TEMPLATE NAME</label>
		                                            		<input type="text" class="form-control" id="salaryTemplateName" name="salaryTemplateName" />
														</div>
													</div>
													 <div class="form-group col-sm-6">
			                                            <table class="table table-bordered table-striped mb-none">
			                                                <thead>
			                                                    <tr>
			                                                        <th colspan="2" style="background:#eee; text-align:center;">EARNING</th>
			                                                    </tr>
			                                                </thead>
			                                                <tbody>
			                                                	<c:forEach var="salaryBreakUpType" items="${salaryBreakUpList}" varStatus="varEarning"> 
			                                                	<tr>
																	<c:if test="${salaryBreakUpType.salaryBreakUpType eq 'EARNING'}">													
																			<td><input type="checkbox" name="salaryBreakUpCode" value="${salaryBreakUpType.salaryBreakUpCode}"/></td>	
																			<td><input class="form-control" name="salaryBreakUpName" value="${salaryBreakUpType.salaryBreakUpName}" readonly="readonly"></td>
																	</c:if>
																</tr>
																</c:forEach>
			                                                </tbody>
			                                            </table>
	                                        		</div>
			                                        <div class="form-group col-sm-6">
			                                            <table class="table table-bordered table-striped mb-none">
			                                                <thead>
			                                                    <tr>
			                                                        <th colspan="2" style="background:#eee; text-align:center;">DEDUCTION</th>
			                                                    </tr>
			                                                </thead>
			                                                <tbody>
		                                                 		<c:forEach var="salaryBreakUpType" items="${salaryBreakUpList}" varStatus="varEarning">
		                                                 		<tr>
																<c:if test="${salaryBreakUpType.salaryBreakUpType eq 'DEDUCTION'}">													
																		<td><input type="checkbox" name="salaryBreakUpCode" value="${salaryBreakUpType.salaryBreakUpCode}"/></td>
																		<td><input class="form-control" name="salaryBreakUpName" value="${salaryBreakUpType.salaryBreakUpName}" readonly="readonly"></td>
																</c:if>
																</tr>
															</c:forEach>
			                                                </tbody>
			                                            </table>
			                                        </div>   
			                                         <div class="form-group col-sm-6">
			                                            <table class="table table-bordered table-striped mb-none">
			                                                <thead>
			                                                    <tr>
			                                                        <th colspan="2" style="background:#eee; text-align:center;">MANUAL</th>
			                                                    </tr>
			                                                </thead>
			                                                <tbody>
		                                                 		<c:forEach var="salaryBreakUpType" items="${salaryBreakUpList}" varStatus="varEarning">
		                                                 		<tr>
																<c:if test="${salaryBreakUpType.salaryBreakUpType eq 'MANUAL'}">													
																		<td><input type="checkbox" name="salaryBreakUpCode" value="${salaryBreakUpType.salaryBreakUpCode}"/></td>
																		<td><input class="form-control" name="salaryBreakUpName" value="${salaryBreakUpType.salaryBreakUpName}" readonly="readonly"></td>
																</c:if>
																</tr>
															</c:forEach>
			                                                </tbody>
			                                            </table>
			                                        </div>                 
												</div>
												 <footer style="display: block;" class="panel-footer">
													<button class="btn btn-primary" type="submit" id="submitButton" onclick="return validateTemplate();">Submit </button>
													<button type="reset" class="btn btn-default">Reset</button>
												</footer>
											</section>
			                            </form>
									</div>
									
								</div>	
								<!-- end: page -->
								                
				</form:form>
			</div>
		</div>
			<%-- </c:otherwise>
			</c:choose>
 --%>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/erp/salaryTemplate.js"></script>
</body>


</html>