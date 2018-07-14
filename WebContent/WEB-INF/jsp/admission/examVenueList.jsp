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
<title>List Books</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<c:choose>
	<c:when test="${examVenuePagedListHolder.pageList == null || empty examVenuePagedListHolder.pageList}">
		<div class="btnsarea01" style="visibility: visible;">
			<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
				<span id="infomsg">No Exam Venue created.</span>	
			</div>
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
						
								<h2 class="panel-title">Exam Venue List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Venue</th>
											<th>Capacity</th>
											<th>Contact No</th>	
											<th>Email Id</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var = "examVenue" items="${examVenuePagedListHolder.pageList}">
											<tr class="gradeC" onClick="window.open('examVenueDetails.html?venueId=${examVenue.venueId}','_self')" style="cursor:pointer;">
												<td><c:out value="${examVenue.venueName}"></c:out></td>		
												<td><c:out value="${examVenue.capacity}"></c:out></td>
												<td><c:out value="${examVenue.contactNo}"></c:out></td>
												<td><c:out value="${examVenue.email}"></c:out></td>			
											</tr>
										</c:forEach>
										
									</tbody>
									<tr>
										<td colspan="11" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${examVenuePagedListHolder}" pagedLink="${pagedLink}"/></td>
									</tr>
								</table>
							</div>
						</section>			
					</section>
	</c:otherwise>
</c:choose>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>