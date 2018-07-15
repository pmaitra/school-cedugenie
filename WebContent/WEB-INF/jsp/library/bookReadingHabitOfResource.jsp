<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de" class="fixed header-dark">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Return Book</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
	.scroll-to-top{
	    display: none !important;
	}.mb-md{
		   display: none;
	}
</style>
<script type="text/javascript">
</script>
</head>
<body>

				
	<div class="row">
		<c:choose>
			<c:when test="${bookLendedByResource == null}" >
					<div class="alert alert-danger" id = "errorBox" style="display: block;">
					<strong>No book found.</strong>
				</div>
			</c:when>
		<c:otherwise> 
			<div class="col-md-12" id = "mainForm" style="display: block;">
 				<form:form id="sicontents" name="sicontents" action="/cedugenie/userForCheckBookReadingHabit.html" method="GET">
 					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
							</div>
							<h2 class="panel-title">
								Issued Book Details For User ID :: <strong>${bookLendedByResource[0].bookIssuedTo.userId}</strong>
								<input type="hidden" name="userId" value="${bookLendedByResource[0].bookIssuedTo.userId}"/>
							</h2>
						</header>
						<div class="panel-body">
							<table class="table table-bordered table-striped mb-none">
								<thead>
									<tr>
										<th>Book Code</th>
										<th>Book Name</th>
										<th>Genre Name</th>
										<th>Issued Date</th>
										<!-- <th>Expected Return Date</th>
										<th>Actual Return Date</th> -->
										<th>Status</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="bookAllocation" items="${bookLendedByResource}">
									<c:forEach var="bookAllocationDetails" items="${bookAllocation.bookAllocationDetails}">
									<tr class="gradeX">
										<td>
											${bookAllocationDetails.bookCode}
											<input type="hidden" id="bookCode" name="bookCode" value="${bookAllocationDetails.bookCode}"/>
										</td>				
										<td>
											${bookAllocationDetails.bookName}
										</td>
										<td>
											${bookAllocationDetails.genreName}
										</td>
										<td>
											${bookAllocationDetails.bookIssueDate}
										</td>
										<%-- <td>
											${bookAllocationDetails.bookReturnDate}
										</td>
										<td>
											${bookAllocationDetails.actualReturnDate}
										</td> --%>
										<c:choose>
											<c:when test="${bookAllocationDetails.status == 'ALTD'}">
												<td>
													Not Returned
											</c:when>
											<c:when test="${bookAllocationDetails.status == 'RTND'}">
												<td>
													Returned
											</c:when>
										</c:choose>
									</tr>
									</c:forEach>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<footer style="display: block;" class="panel-footer">
							<button class="btn btn-warning" type="submit">Ok</button>
						</footer>
					</section>
				</form:form>	
         	</div>
         </c:otherwise>
      </c:choose>
	</div>