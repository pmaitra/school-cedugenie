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
<title>View Salary Templates</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<c:choose>
		<c:when test="${salaryTemplateList eq null}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No Salary Templates Found</span>	
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
						
								<h2 class="panel-title">View Salary Templates</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											 <th>Salary Templates Name</th>
											   <th>Salary Templates Type</th>	
											   <th>Salary Templates Description</th>
											   <th>Details</th>	
										</tr>
									</thead>
									<tbody>
										 <c:forEach var="stl" items="${salaryTemplateList}"> 
											<tr class="gradeC">
												<td align="center">${stl.salaryTemplateName}</td>
											   <td align="center">${stl.salaryTemplateType}</td>	
											   <td align="center">${stl.salaryTemplateDesc}</td>
											   <td><a href="viewSalaryTemplateDetails.html?salaryTemplateCode=${stl.salaryTemplateCode}&salaryTemplateType=${stl.salaryTemplateType}"><input type="button" name="details" value="DETAILS" class="submitbtn"></a></td>
											</tr>
										</c:forEach>
										
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