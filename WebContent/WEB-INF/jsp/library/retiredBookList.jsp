<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/retiredBookListPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Retired Book List</title>

<%@ include file="/include/include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

			<div class = "row">
				<section role="main" class="content-body">
					<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
								<h2 class="panel-title">Book With Retired Copies</h2>
							</header>
							<div class="panel-body" id = "brt">
							<c:choose>
							<c:when test="${bookPagedListHolder ne null}">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Book Code</th>
											<th>Book Name</th>
											<th>Total No. Of Copies</th>
											<th>No. Of Retired Copies</th>
											<th>Details</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="viewRetiredBookList" items="${bookPagedListHolder.pageList}">
											<tr class="gradeC">
												<td><c:out value="${viewRetiredBookList.bookCode}"/></td>
												<td><c:out value="${viewRetiredBookList.bookName}"/></td>
												<td><c:out value="${viewRetiredBookList.totalNumberOfBookCopies}"/></td>
												<td><c:out value="${viewRetiredBookList.totalNumberOfBookCopiesRetired}"/></td>
												<td><a href="retiredBookDetails.html?bookCode=<c:out value="${viewRetiredBookList.bookCode}"/>" >DETAILS</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
							<c:otherwise>
									<div class="btnsarea01" style="visibility: visible;">
											<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
												<span id="infomsg">No retired books found </span>	
											</div>
									</div>		
							</c:otherwise>
							</c:choose>	
							</div>
						</section>			
					</section>
				</div>


<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>