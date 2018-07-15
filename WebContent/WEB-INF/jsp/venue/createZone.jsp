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
       }
</style>
</head>
<body>

			<c:if test="${updateStatus eq 'success'}">
				<div class="alert alert-success">
					<strong>Zone Created Successfully.</strong>
				</div>
			</c:if>
			
			<c:if test="${updateStatus eq 'fail'}">
				<div class="alert alert-danger">
					<strong>Zone Creation Failed.</strong>
				</div>
			</c:if>
			<c:if test="${updateStatus eq 'updatesuccess'}">
				<div class="alert alert-success">
					<strong>Zone Updated Successfully.</strong>
				</div>
			</c:if>
			
			<c:if test="${updateStatus eq 'updatefail'}">
				<div class="alert alert-danger">
					<strong>Zone Updation Failed.</strong>
				</div>
			</c:if>

					<div class="row">
						<div class="col-md-4">
							<form action="createZone.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Zone</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="col-sm-5 control-label">Zone Name: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<input id="zoneName" name="zoneName" class="form-control" type="text">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label">Description: <span class="required" aria-required="true">*</span> </label>
											<div class="col-sm-7">
												<input id="zoneDesc" name="zoneDesc" class="form-control" type="text">
											</div>
										</div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" class="btn btn-primary" >Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
						
						<c:choose>
							<c:when test="${zoneList eq null || empty zoneList}">
								<div class="btnsarea01" >
									<div class="errorbox" id="errorbox" style="visibility: visible;">
										<span id="errormsg">Standard Not Created Yet</span>	
									</div>
								</div>
							</c:when>
						<c:otherwise>						
						
						<div class="col-md-8">						  
							<form name="editZone" id="editZone" action="editZone.html" method="post">
							<input type="hidden" name="saveId" id="saveId">
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Existing Standards</h2>
							</header>
							<div class="panel-body">
								<c:forEach var="zone" items="${zoneList}" varStatus="i">
								<input type="hidden" name="oldZoneNames" value="${zone.zoneCode}">
								</c:forEach>
								<table class="table table-bordered table-striped mb-none" id="datatable-editable">
									<thead>
										<tr>
											<th>Zone Nmae</th>
											<th>Zone Desc</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="zone" items="${zoneList}" varStatus="i">
										<tr>
											<td>
												<%-- <input type="hidden" name="standardId${i.index}" value="${standard.standardId}"> --%>
												
												<input type="hidden" id="zoneCode${i.index}" name="zoneCode${i.index}" value="${zone.zoneCode}">
												<input type="text" id="zoneName${i.index}" name="zoneName${i.index}" class="form-control" value="${zone.zoneName}" readonly />
											</td>
											<td>
												<input type="text" id="zoneDesc${i.index}" name="zoneDesc${i.index}" class="form-control" value="${zone.zoneDesc}" readonly />
											</td>
											<td class="actions">
												<a href="#" class="hidden on-editing save-row" id="save${i.index}"><i class="fa fa-save"></i></a>
												<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
												<a href="#" class="on-default edit-row" id="edit${i.index}"><i class="fa fa-pencil"></i></a>
											</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</section>
						</form>
						</div>
					</c:otherwise>
					</c:choose>	 
					</div>
                    

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/venue/createZone.editable.js"></script>
<script src="/cedugenie/js/venue/createZone.js"></script> 

</body>
</html>