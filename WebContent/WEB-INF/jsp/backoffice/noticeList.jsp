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
<title>Notice List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<c:if test="${ErrorMessage ne null}">
	<div class="errorbox" id="errorbox" style="visibility: visible;">
		${ErrorMessage}
	</div>
</c:if>
<c:if test="${SuccessMessage ne null}">
	<div class="successbox" id="successbox" style="visibility: visible;">
		${SuccessMessage}
	</div>
</c:if>
<form:form action="viewAndDeleteNotice.html" method="POST">
<c:choose>
	<c:when test="${pagedListHolder eq null}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No Notice Found</span>	
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
						
								<h2 class="panel-title">Notice List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Select</th>
											<th>Date</th>
											<th>Notice Subject</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="notice" items="${pagedListHolder.pageList}">
											<tr class="gradeC">
												<td>
													<input type="radio" name="noticeCode" value="${notice.noticeCode}"/>
												</td>	
												<td>
													${notice.time}
												</td>			
												<td>
													${notice.noticeName}
												</td>
											</tr>
										</c:forEach>
										
									</tbody>
								</table>
							</div>
						</section>			
					</section>
			<input type="submit" id="delete" name="delete" class="submitbtn" value="Delete" />	
			<input type="submit" id="edit"  name="edit" class="editbtn" value="Edit" />
	</c:otherwise>
</c:choose>
</form:form>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>