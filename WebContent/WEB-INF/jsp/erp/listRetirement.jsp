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
<title>List Retirement</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<c:choose>
	<c:when test="${retiredStaffList eq null}">
		<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
			<span id="infomsg">No Retirement List Found</span>	
		</div>								
	</c:when>
<c:otherwise>	
		<form:form method="POST" action="editRetirement.html">			
			<section role="main" class="content-body">			
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
							<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
						</div>
				
						<h2 class="panel-title">List Retirement</h2>
					</header>
					<div class="panel-body">
						<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
							<thead>
								<tr>
									<th>Code</th>
									<th>Name</th>
									<th>Designation</th>
									<th>Join Date</th>
									<th>Retirement Date</th>
									<th>Mode Of Retirement</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="staff" items="${retiredStaffList}"> 
									<tr class="gradeC">
										 <td>${staff.employeeCode}</td>
										  <td><c:out value="${staff.resource.firstName} ${staff.resource.middleName} ${staff.resource.lastName}"/></td>
										  <td>${staff.designation.designationName}</td>
										  <td>${staff.dateOfJoining}</td>		
										  <td>${staff.dateOfRetirement}</td>
										  <td>${staff.modeOfRetirement}</td>
									</tr>
								</c:forEach>								
							</tbody>							
						</table>
					</div>
				</section>			
			</section>			
		</form:form>
	</c:otherwise>
</c:choose>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>