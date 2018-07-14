<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Staffs List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<%-- <c:choose>
	<c:when test="${staffList == null}">
		<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
			<span id="infomsg">No Staff Found</span>
		</div>
	</c:when>
<c:otherwise> --%>
<header class="page-header">
		<h2>Staff List</h2>
</header>
	<div class="content-padding">
		<div class="row">	
			<form:form action="viewStaffDetails.html" method="POST">		
				<section role="main" class="content-body">		
							<section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
										<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
									</div>						
									<h2 class="panel-title">Staffs List</h2>
								</header>
								<div class="panel-body">
									<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
										<thead>
											<tr>
												<th>User Id</th>
												<th>Code</th>
												<th>Name</th>
												<th>Employee Type</th>													
												<th>Designation</th>			
												<th>Details</th>	
											</tr>
										</thead>
										<tbody>
											<c:set var="count" value="${first}" scope="page" />
											<c:forEach var="emp" items="${staffList}">
												<tr class="gradeC"> 
													<td>${emp.resource.userId}</td> 
													<td>${emp.employeeCode}</td>													
													<td><c:out value="${emp.resource.firstName} ${emp.resource.middleName} ${emp.resource.lastName}"/></td>	
													<td>${emp.employeeType.employeeTypeName}</td>
													<td>${emp.designation.designationName}</td>			
													<td><a href="viewEditStaffDetails.html?userId=${emp.resource.userId}"><input type="button" name="details" class="btn btn-primary" value="DETAILS" class="submitbtn"></a></td>
													<c:if test="${count lt total}">			
														<c:set var="count" value="${count + 1}" scope="page"/>
													</c:if>
												</tr>										
											</c:forEach>										
										</tbody>									
									</table>
								</div>
							</section>			
						</section>
				
			</form:form>
		</div>
	</div>
<%-- 	</c:otherwise>
</c:choose> --%>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>