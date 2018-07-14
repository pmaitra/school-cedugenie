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
<title>Pending Task List</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<c:choose>
		<c:when test="${pendingLeaveList eq null}">			
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No Pending Task For You</span>	
			</div>								
		</c:when>
	<c:otherwise>
		<form:form method="POST" action="searchOnListPendingTask.html">		
			<section role="main" class="content-body">		
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Pending Task List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Serial No.</th>
											<th>Request ID</th>
											<th>Title</th>					
											<th>Requested By</th>											
											<th>Applied On</th>							
											<th>Task Status</th>	
											<th>ACTION</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach var="leave" items="${pendingLeaveList}" varStatus="serialNo">	
												<tr class="gradeC">
													<td><c:out value="${(serialNo.index)+1}"/></td>
													<td><c:out value="${leave.leaveCode}"/></td>																	
													<td><c:out value="${leave.title}"/></td>
													<td><c:out value="${leave.userId}"/></td>	
													<td><c:out value="${leave.appliedOn}"/></td>
													<td><c:out value="${leave.status}"/></td>													
													<td><a href="pendingLeaveDetails.html?leaveCode=${leave.leaveCode}"><input type="button" name="details" value="DETAILS" class="btn btn-primary"></a></td>
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
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>