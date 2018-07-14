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
<c:choose>
	<c:when test="${leaveHistoryList eq null}">		
			<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
				<span id="infomsg">No Leave Found</span>	
			</div>		
	</c:when>
<c:otherwise>
	<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Leave History List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Leave Type</th>
											<th>From Date</th>						
											<th>To Date</th>
											<th>No Of Days</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="leave" items="${leaveHistoryList}">
											<tr class="gradeC">
												<td>${leave.leaveType}</td>
												<td>${leave.startDate}</td>
												<td>${leave.endDate}</td>
												<td>${leave.usedLeave}</td>
											</tr>
										</c:forEach>
										
									</tbody>
								</table>
							</div>
						</section>			
					
			</c:otherwise>
	</c:choose>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>