<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>List of Teacher Interview</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<%-- <c:choose>
	<c:when test="${teacherInterviewList == null}">
		<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
			<span id="infomsg">Teacher Interview Details Not Found</span>
		</div>
	</c:when>
<c:otherwise> --%>
<header class="page-header">
	<h2>List of Teacher Interview</h2>
</header>
<div class="content-padding">
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
				<h2 class="panel-title">Staffs List</h2>
			</header>
			<div class="panel-body">
				<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
					<thead>
						<tr>
							<th>Candidate Code</th>
							<th>Name</th>
							<th>Qualification</th>
							<th>Specialization</th>
							<th>Experience</th>
							<th>Referred By</th>
							<th>Interview Date</th>
							<th>Interview Status</th>
							<th>Add Staff</th>
							<th>Details</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="staff" items="${teacherInterviewList}">
							<tr class="gradeC">
								<td>${staff.employeeCode}</td>
							    <td>${staff.resource.firstName} ${staff.resource.middleName} ${staff.resource.lastName}</td>	
							    <td>${staff.qualification.qualificationName}</td>
							    <td>${staff.qualification.specialization}</td>		
							    <td>${staff.experience}</td>	
							    <td>${staff.referredBy}</td>
							    <td>${staff.dateOfInterview}</td>	
							    <td>${staff.interviewStatus}</td>			   
							    <td>
				  				<c:if test="${staff.interviewStatus eq 'SELECTED'}">
							  		<c:choose>
							  		<c:when test="${staff.status eq null}">
			 				  			<a href="getFormDetailsForStaffDetailsInfo.html?strStaffCode=${staff.employeeCode}&firstName=${staff.resource.firstName}&middleName=${staff.resource.middleName}&lastName=${staff.resource.lastName}&dateOfInterview=${staff.dateOfInterview}">Enter Staff Details</a>
									</c:when>
									<c:otherwise>
						  				Inserted
				 				 	</c:otherwise>
				 					</c:choose>
				   				</c:if> 
							    </td>
							    <td>
					       		<c:choose>
						  		<c:when test="${staff.status eq null}">
			 				  		 <a href="editTeacherInterview.html?employeeCode=${staff.employeeCode}"><input type="button" name="details" value="DETAILS" class="btn btn-primary"></a>
								</c:when> 
								<c:otherwise>
						  		 	<a href="editTeacherInterview.html?employeeCode=${staff.employeeCode}"><input type="button" name="details" value="DETAILS" class="btn btn-primary" disabled="disabled"></a>
			 				 	</c:otherwise>
			      			    </c:choose>		      
						       </td>
							</tr>
						</c:forEach>										
					</tbody>									
				</table>
			</div>
		</section>
	</section>
<%-- </c:otherwise>
</c:choose --%>
</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>