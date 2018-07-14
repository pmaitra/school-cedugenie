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
<title>List Task History</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<c:choose>
		<c:when test="${empty pagedListHolder}">
		<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
			<span id="infomsg">No Task History is available</span>	
		</div>					
		</c:when>
		<c:otherwise>

		<form method="post" action="editBook.html" >
		
			
			<section role="main" class="content-body">
					
			
			
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">List Task History</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>From Date</th>
											<th>To Date</th>
											<th>No Of Days</th>
											<th>Description</th>
											<th>Details</th>	
										</tr>
									</thead>
									<tbody>
										<c:forEach var="task" items="${pagedListHolder.pageList}">
											<tr class="gradeC">
												<td>${task.startDate}</td>
											<td>${task.endDate}</td>
											<td>${task.numberofDayRequestedFor}</td>
											<td>${task.taskDesc}</td>
											<td>
												<c:choose>
													<c:when test="${task.leaveList == null || empty task.leaveList}">
														<div class="btnsarea01" style="visibility: visible;">
															<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
																<span id="infomsg">Levae Type Not Found.</span>	
															</div>
														</div>
													</c:when>
													<c:otherwise>
														<c:forEach var="leave" items="${task.leaveList}">
															<p style="margin:0em;">${leave.leaveType}(${leave.totalRequestedLeave})</p>
														</c:forEach>
													</c:otherwise>
												</c:choose>
											</td>
										</c:forEach>
										</tr>	
									</tbody>
									<tr>
										<td colspan="8" id="toolbar">Displaying ${first} to ${last} of ${total} items<tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
									</tr>
								</table>
							</div>
						</section>			
					</section>
			<input type="submit" name="details" value="DETAILS" class="submitbtn">
		</form>
	</c:otherwise>
</c:choose>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>