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
<title>Co Scholastic List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<c:choose>	
	<c:when test="${null eq resultStatusList || empty resultStatusList}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">
					<c:if test="null eq descriptiveIndicatorHeadList || empty descriptiveIndicatorHeadList">Student Not Found
					<c:if test="null ne status">${status}PPP</c:if>
				</c:if>
				</span>
			</div>
		</div>
	</c:when>
	<c:otherwise>

		<form:form id="studentListForm" name="studentListForm" action="getStudentDetails.html" method="POST">
		
			<section role="main" class="content-body">
					
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Co Scholastic List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Standard</th>
											<th>Section</th>
											<th>Total</th>
											<th>Uploaded</th>
											<th>Remeaning</th>
											<th>Update</th>
										</tr>
									</thead>
									<tbody>
											<c:forEach var="status" items="${resultStatusList}">
											<tr class="gradeC">
												<td class="center hidden-phone">
													${status.standard}
												</td>
												<td class="center hidden-phone">							
													${status.section}
												</td>
												<td class="center hidden-phone">
													${status.total}
												</td>
												<td class="center hidden-phone">
													${status.completed}
												</td>
												<td class="center hidden-phone">
													${status.total-status.completed}
												</td>
												<td >
													<c:if test="${status.total-status.completed gt 0}">
														<a onClick="window.open('createCoScholasticResult.html?standard=${status.standard}&section=${status.section}','_self')"><input type="button" value="INSERT" class="editbtn"></a>
													</c:if>
													<c:if test="${status.completed gt 0}">
														<a onClick="window.open('updateCoScholasticResult.html?standard=${status.standard}&section=${status.section}','_self')"><input type="button" value="VIEW/UPDATE" class="editbtn"></a>
													</c:if>
												</td>
											</tr>
										</c:forEach>
										
									</tbody>
								</table>
							</div>
						</section>			
					</section>
			<input type="submit" name="details" value="DETAILS" class="submitbtn">
		</form:form>
	</c:otherwise>
</c:choose>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>