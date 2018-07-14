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

<header class="page-header">
			<h2>List Ticket</h2>
		</header>
	<c:if test="${ticket != null}">			
				<c:if test="${ticket.status == 'success'}">
					<div class="alert alert-success">
						<strong>Ticket Updated Successfully. Your ticket ID is - ${ticket.ticketCode}</strong>	
					</div>					
				</c:if>
				<c:if test="${ticket.status == 'fail'}">
					<div class="alert alert-danger">
						<strong>Problem Occur While Updating Ticket</strong>	
					</div>					
				</c:if>
			</c:if>
<%-- <c:choose>
	<c:when test="${ticketList eq null}">		
		<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
			<span id="infomsg">No Ticket List Found</span>	
		</div>		
	</c:when>
<c:otherwise> --%>

		<form method="post" action="editTicket.html" >
			
			
			
			
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
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Ticket Code</th>
											<th>Open Date/Time</th>	
											<th>Affected User </th> 
											<th>Reported By</th>
											<th>Category</th>
											<th>Summary</th>			
											<th>Status</th>
											<th>View Details</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="ticketList" items="${ticketList}">
											<tr class="gradeC">
												<td><c:out value="${ticketList.ticketCode}"></c:out></td>
												<td><c:out value="${ticketList.ticketOpenDate}"></c:out></td>
												<td><c:out value="${ticketList.affectedUser}"></c:out></td> 
												<td><c:out value="${ticketList.reportedBy}"></c:out></td>		
												<td><c:out value="${ticketList.ticketService.ticketServiceName}"></c:out></td>			
												<td><c:out value="${ticketList.ticketSummary}"></c:out></td>		
												<td><c:out value="${ticketList.taskStatus}"></c:out></td>
												<td><a href="editTicketForMyService.html?ticketCode=${ticketList.ticketCode}"><input type="button" name="details" value="DETAILS" class="btn btn-info"></a></td>
											</tr>
										</c:forEach>
											<%-- <tr>
												<td colspan="8" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
											</tr> --%>
									</tbody>
								</table>
							</div>
						</section>			
					</section>
<!-- 			<input type="submit" name="details" value="DETAILS" class="submitbtn"> -->
		</form>
	<%-- </c:otherwise>
</c:choose> --%>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>