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
<title>Candidate List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">		<!-- Added by Saif -->
		<h2>Candidate Schedule List</h2>
	</header>
	
	<div class = "content-padding">
		<%-- <c:choose>
	<c:when test="${empList == null}">
		<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
			<span id="infomsg">No Teacher Interview Scheduled</span>
		</div>
	</c:when>
<c:otherwise> --%>	
<c:if test="${insertStatus == 'success'}">
	<div class="alert alert-success">
			<strong>Data Successfully Inserted</strong>	
	</div>			
</c:if>

<c:if test="${insertStatus == 'fail'}">
	<div class="alert alert-danger" >
					<strong>Problem Occur While Saving</strong>	
	</div>			
</c:if>	
	<section role="main" class="content-body">		
		<section class="panel">
			<header class="panel-heading">
				<div class="panel-actions">
					<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
					<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
				</div>						
				<h2 class="panel-title">Candidate List</h2>
			</header>
			<div class="panel-body">
				<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
					<thead>
						<tr>
							<th>Candidate Code</th>
							<th>Name</th>
							<th>Qualification</th>
							<!-- <th>Room No</th> -->
							<th>Venue</th>
							<th>Interview Date</th>
							<th>Interview Time</th>
							<th>Referred By</th>
							<th>Details</th>	
						</tr>
					</thead>
					<tbody>							
							<c:forEach var="emp" items="${empList}">
								<tr class="gradeC">
									<td>${emp.employeeCode}</td>
								    <td>${emp.resource.firstName} ${emp.resource.middleName} ${emp.resource.lastName}</td>	
								    <td>${emp.qualification.qualificationName}</td>
								    <td>${emp.roomNumber}</td>		
								    <%-- <td>${emp.venue}</td> --%>	
								    <td>${emp.dateOfInterview}</td>	
								    <td>${emp.timeOfInterview}</td>
								    <td>${emp.referredBy}</td>
								    <td><a href="editTeacherInterviewSchedule.html?employeeCode=${emp.employeeCode}"><input type="button" name="details" value="DETAILS" class="btn btn-primary"></a></td>
								</tr>										
							</c:forEach>										
						</tbody>									
					</table>
				</div>
			</section>			
		</section>		
	<%-- </c:otherwise>
</c:choose> --%>
</div>

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>