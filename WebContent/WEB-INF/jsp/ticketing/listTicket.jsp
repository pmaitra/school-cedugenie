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
<title>List Ticket</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<%-- <c:choose>
	<c:when test="${pagedListHolder eq null}">		
		<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
			<span id="infomsg">No Ticket List Found</span>	
		</div>		
	</c:when>
<c:otherwise> --%>
	<header class="page-header">
			<h2>List Ticket</h2>
		</header>
<div class="content-padding">
	 <div class="row">
		<form method="post" action="editBook.html" >
			
			
			
			
			<section role="main" class="content-body">
					
			
			
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">List Ticket</h2>
							</header>
							<div class="panel-body">
							<div class = "col-md-12">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Ticket Code</th>
											<th>Open Date/Time</th>	
											<th>Affected User</th>						
											<th>Reported By</th>
											<th>Category</th>
											<th>Summary</th>			
											<th>Status</th>
											<th>View Details</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="ticket" items="${ticketList}">
											<tr class="gradeC">
												<td><c:out value="${ticket.ticketCode}"></c:out></td>
												<td><c:out value="${ticket.ticketOpenDate}"></c:out></td>
												<td><c:out value="${ticket.affectedUser}"></c:out></td>
												<td><c:out value="${ticket.reportedBy}"></c:out></td>		
												<td><c:out value="${ticket.ticketService.ticketServiceName}"></c:out></td>			
												<td><c:out value="${ticket.ticketSummary}"></c:out></td>		
												<td><c:out value="${ticket.taskStatus}"></c:out></td>
												<td><a href="editTicket.html?ticketCode=${ticket.ticketCode}&ticketServiceCode=${ticket.ticketService.ticketServiceCode}&status=${ticket.queryStatus}"><input type="button" name="details" value="DETAILS" class="btn btn-info"></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								</div>
							</div>
						</section>			
					</section>
			<!-- <input type="submit" name="details" value="DETAILS" class="submitbtn"> -->
		</form>
		</div>
	</div>
<%-- 	</c:otherwise>
</c:choose> --%>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>