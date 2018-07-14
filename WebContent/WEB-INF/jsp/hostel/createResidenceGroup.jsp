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
<title>Create Residence Group</title>
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
	<header class= "page-header">
		<h2>Create Residence Group</h2>
	</header>
	<div class = "content-padding">
		<div class="row">
			<div class="col-md-5">
				<form:form name="submitResidenceGroup" action="submitResidenceGroup.html" method="POST">
					<section class="panel">
						<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>	
						<h2 class="panel-title">Create New Residence Group</h2>										
						</header>
						<div style="display: block;" class="panel-body">	                                   										
		                    <div class="form-group">
		                        <label class="control-label">Residence Group Name:<span class="required" aria-required="true">*</span></label>
		                        <input type="text" class="form-control" name="residenceGroupName" id="residenceGroupName" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" title="Resident group name can consist of alphabetical characters and spaces only" required>
		                    </div>
						</div>
						<footer style="display: block;" class="panel-footer">
							<button class="btn btn-primary" type="submit" id="submitButton">Submit</button>
							<button type="reset" class="btn btn-default">Reset</button>
						</footer>
					</section>
		    	</form:form>
			</div>
			
			<div class="col-md-7">
				<c:if test="${submitResponse ne null}">
					<c:if test="${submitResponse eq 'success'}">
						<div class="alert alert-success">
						  <strong>Residence group successfully created.</strong> 
						</div>
					</c:if>
					<c:if test="${submitResponse eq 'fail'}">				
						<div class="alert alert-danger">
						  <strong>Residence group creation failed.</strong> 
						</div>
					</c:if>			
				</c:if>
				<c:if test="${updateResponse ne null}">			
					<c:if test="${updateResponse eq 'success'}">				
						<div class="alert alert-success">
						  <strong>Residence group successfully updated.</strong> 
						</div>
					</c:if>
					<c:if test="${updateResponse eq 'fail'}">
						<div class="alert alert-danger">
						  <strong>Residence group updation failed.</strong> 
						</div>
					</c:if>
				</c:if>
				<c:choose>
					<c:when test="${null eq residenceGroupList || empty residenceGroupList}">			
						<div class="alert alert-danger">
						  <strong>No residence group created yet.</strong> 
						</div>
					</c:when>
				<c:otherwise>
					<input type="hidden" name="saveId" id="saveId">	
	                <section class="panel">
	                    <header class="panel-heading">
	                        <div class="panel-actions">
	                            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
	                        </div>
	                        <h2 class="panel-title">View Residence Groups</h2>
	                    </header>
	                    <div class="panel-body">
	                        <table class="table table-bordered table-striped mb-none" id="datatable-editable">
	                            <thead>
	                                <tr>
	                                    <th>Residence Group Name</th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                            	<c:forEach var="res" items="${residenceGroupList}" varStatus="i">
			                             <tr>
											<td>
												<input type="text" class="form-control" id="residentGroupName${i.index}" name="residentGroupName${i.index}" value="${res.residenceGroupName}" readonly>
												<input type="hidden" value="${res.residenceGroupCode}">
											</td>
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
	</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>