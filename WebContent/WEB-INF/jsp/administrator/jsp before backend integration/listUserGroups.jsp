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
<title>User Group List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<c:choose>
	<c:when test="${null eq pagedListHolder}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No User Group Found</span>	
			</div>				
	</c:when>
<c:otherwise>
<form:form action="getUserGroupDetails.html" method="POST">
		
			<section role="main" class="content-body">
					
			
			
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">User Group List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Select</th>
											<th>Group Name</th>
											<th>Created On</th>
											<th>Group Description</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="userGroup"  items="${pagedListHolder.pageList}">	
											<tr class="gradeC">
												<td>
												<input type="radio" name="userGroupCode" id="userGroupCode" value="${userGroup.userGroupCode}"/>
												</td>				
												<td>
													${userGroup.userGroupName}
												</td>
												<td>
													${userGroup.status}
												</td>
												<td>
													<textarea readonly="readonly" class="txtarea" >${userGroup.userGroupDesc}</textarea>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</section>			
					</section>
			<input type="submit" name="details" value="DETAILS" class="submitbtn">
			<input type="submit" id="submit" class="submitbtn" name="delete" value="Delete" onclick="return valradio('userGroupCode','warningbox','warningmsg');"/>	
			<input type="submit" id="submit" class="editbtn" name="details"  value="Details" onclick="return valradio('userGroupCode','warningbox','warningmsg');"/>	
		</form:form>
	</c:otherwise>
</c:choose>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>