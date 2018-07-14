<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/lendingHistoryPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
	 <c:param name="strBookCode" value="${strBookCode}"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>View Book Profile</title>

<%@ include file="/include/include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<!-- <script type="text/javascript" src="/icam/js/library/viewBookProfile.js"></script> -->
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<c:choose>
	<c:when test="${BookProfile == null}">
		<div class="btnsarea01" style="visibility: visible;">
			<div class="errorbox" id="errorbox" >
				<span id="errormsg">No Books Found</span>	
			</div>
		</div>
	</c:when>
<c:otherwise>	
	<div id="viewBookProfile" >
		<input type="hidden" name="strBookCode" value="${strBookCode}">
			<form:form method="POST" id="sicontents" name="sicontents" action="">
				<section role="main" class="content-body">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
							</div>
					
							<h2 class="panel-title">Book Profile</h2>
						</header>
						<div class="panel-body">
							<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
								<thead>
									<tr>
										<th>Type</th>
										<th>Code</th>
										<th>Name</th>
										<th>Genre</th>
										<th>Edition</th>
										<th>Author</th>
										<th>Publisher</th>
									</tr>
								</thead>
								<tbody>
									<tr class="gradeC">
										<td>
											${BookProfile.bookType}
										</td>
										<td>
											${BookProfile.bookCode}
										</td>
										<td>
											${BookProfile.bookName}
										</td>
										<td>
											${BookProfile.genre.genreName}
										</td>
										<td>
										 	${BookProfile.bookEdition}
										</td>
										<td>
											<c:if test="${BookProfile.bookAuthorList ne null}">
												<c:forEach var="bookAuthor" items="${BookProfile.bookAuthorList}">
													<c:out value="${bookAuthor.authorFullName}"></c:out> ; 
												</c:forEach>
											</c:if>
										</td>
										<td>
										 	${BookProfile.bookPublisher.bookPublisherName}
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<br>
					<c:choose>		
						<c:when test="${bookAllocationList!=null}">	
							<section role="main" class="content-body">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
											<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
										</div>
										<h2 class="panel-title">Lending History Of This Book</h2>
									</header>
									<div class="panel-body">
										<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
											<thead>
		                                          <tr>
													<th>Resource Name</th>
													<th>UserId/Roll Number</th>
													<th>Rating</th>
													<th>Comments</th>
												</tr>
											</thead>
											<tbody>
												 <c:forEach var="lendingHistory" items="${bookAllocationList}">
													<tr class="gradeC">
														<td>
															<c:out value="${lendingHistory.bookIssuedTo.name}"/>
														</td>
														<td>
															<c:out value="${lendingHistory.bookIssuedTo.userId}"/><input type="hidden" name="userId" readonly="readonly" value="${lendingHistory.bookIssuedTo.userId}"/>
														</td>
														<td>
															<c:out value="${lendingHistory.bookRating.bookRatingDesc}"/><input type="hidden" name="bookCode" readonly="readonly" value="${BookProfile.bookCode}"/>
														</td>
														<td>
															<c:out value="${lendingHistory.bookRating.bookRatingComments}"/>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</section>
							</section>		
						</c:when>
					<c:otherwise>
					<br>
					<div class="alert alert-danger">
					  <strong>This book isn't allocated to anyone right now.</strong>
					</div>
					</c:otherwise>
					</c:choose>				
					</section>
					<input type="button" class="mb-xs mt-xs mr-xs btn btn-warning pull-right" value="Back" onclick="window.location='viewBookStock.html' ">			
				</section>
			</form:form>
		</div>	
	</c:otherwise>
</c:choose>

	                   
<script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script> 
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>