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
<title>Email Event And Template Mapping</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
       .mb-md{
       	   display: none;
       }
</style>
</head>
<body>

		<header class="page-header">
			<h2> Event And Template Mapping</h2>
		</header>
		<div class="content-padding">
			<c:if test="${status eq 'fail'}">
				<div class="alert alert-danger" id="errormsgbox">
					<strong>Problem in Event and Template Mapping!!</strong>	
				</div>
			</c:if>
			<c:if test="${status eq 'success'}">
				<div class="alert alert-success" id="successboxmsgbox">
					<strong>Event and Template mapped Successfully!!</strong>	
				</div>
			</c:if>
					<div class="row">
						<div class="col-md-8">
							<form action="insertEventAndTemplateMapping.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Event and Template Mapping</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											 <label class="control-label"><b>Event Name: <span class="required" aria-required="true">*</span></b></label>
												<select class="form-control" name="eventName" id="eventName" required >
                                                    <option value="">Select...</option>
                                                    <c:forEach var="event" items="${emailEventListForMapping}" varStatus="i">
														<option value="${event.eventName}">${event.eventName}</option>
													</c:forEach>
                                                </select>
									    </div>
									</div>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											 <label class="control-label"><b>Template Name: <span class="required" aria-required="true">*</span></b></label>
												<select class="form-control" name="emailSubject" id="emailSubject" required >
                                                    <option value="">Select...</option>
                                                    <c:forEach var="template" items="${emailTemplateListForMapping}" varStatus="i">
														<option value="${template.emailSubject}">${template.emailSubject}</option>
													</c:forEach>
                                                </select>
									    </div>
									</div>
									 <div class="alert alert-warning" id="message1" style="display: none">
							  							<span></span>	
									</div>	
									<footer style="display: block;" class="panel-footer">
										<button type="submit" id="submit" class="btn btn-primary">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
						  <c:choose>
							<c:when test="${emailEventTemplateMappingList eq null || empty emailEventTemplateMappingList}">
								<div class="btnsarea01" >
									<div class="errorbox" id="errorbox" style="visibility: visible;">
										<span id="errormsg">No Event Yet Mapped with the Template!!</span>	
									</div>
								</div>
							</c:when>
						<c:otherwise>
						<div class="col-md-8">
							<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Event and Template List</h2>
							</header>
							<div class="panel-body" id="datatable-editable">
								<table class="table table-bordered table-striped mb-none"  id="datatable-tabletools">
									<thead>
										<tr>
											<th>Event</th>
											<th>Template</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="emailTemplate" items="${emailEventTemplateMappingList}" varStatus="i" >
										<tr>
											<td>
												${emailTemplate.eventName}
											</td>
											<td>
												${emailTemplate.emailSubject}
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
			</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/academics/createStandard.editable.js"></script>
 <script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
</body>
</html>