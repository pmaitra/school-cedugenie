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
<title>Closed ticket list</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
			<h2>Closed Ticket List</h2>
		</header>
<div class="content-padding">
<c:choose>
	<c:when test="${ticketList eq null}">		
			<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
				<span id="infomsg">No Closed Ticket List Found</span>	
			</div>		
	</c:when>
<c:otherwise>

	
			
			<section role="main" class="content-body">
					
			
			
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Closed ticket list</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Ticket Code</th>
											<th>Open Date</th>
											<th>Close Date</th>
											<!-- <th>Affected User</th>			 -->			
											<th>Reported By</th>
											<th>Service Type</th>
											<th>Ticket Summary</th>
											<th>Status</th>
											<th>View Details</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="ticketList" items="${ticketList}">
											<tr class="gradeC">
												<td>${ticketList.ticketCode}</td>
												<td>${ticketList.ticketOpenDate}</td>
												<td>${ticketList.ticketCloseDate}</td>
												<%-- <td>${ticketList.affectedUser}</td> --%>
												<td>${ticketList.reportedBy}</td>		
												<td>${ticketList.ticketService.ticketServiceName}</td>
												<td>${ticketList.ticketSummary}</td>
												<td>${ticketList.taskStatus}</td>
												<td><a href="closedTicketDetails.html?ticketCode=${ticketList.ticketCode}"><input type="button" name="details" value="DETAILS" class="btn btn-info"></a></td>
											</tr>
										</c:forEach>
										
									</tbody>
								</table>
							</div>
						</section>			
					</section>
			
		
	</c:otherwise>
</c:choose>
</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>