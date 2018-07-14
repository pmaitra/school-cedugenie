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
<title>Approver Group List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

<c:if test="${successMessage ne null}">
				<div class="successbox" id="successboxmsgbox" style="visibility: visible;">
					<span>${successMessage}</span>	
				</div>
		</c:if>
			
		<c:if test="${errorMessage ne null}">
					<div class="errorbox" id="errormsgbox" style="visibility: visible;">
						<span>${errorMessage}</span>	
					</div>
		</c:if>
<c:choose>
	<c:when test="${null eq pagedListHolder}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No Approver Group Found</span>	
			</div>				
	</c:when>
<c:otherwise>
		<form:form action="getApproverGroupDetails.html" method="POST">
			
			
			
			
			<section role="main" class="content-body">
					
			
			
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Approver Group List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Select <img class="required" src="/icam/images/required.gif" alt="Required"> </th>
											<th>Approver Group Name</th>
											<th>Created On</th>
											<th>Approver Group Description</th>	
											<th>Approver Process</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="approverGroup"  items="${pagedListHolder.pageList}">	
											<tr class="gradeC">
												<td>
													<input type="radio" name="approverGroupCode" id="approverGroupCode" value="${approverGroup.approverGroupCode}"/>
												</td>				
												<td>
													${approverGroup.approverGroupName}
												</td>
												<td>
													${approverGroup.status}
												</td>
												<td>
													<textarea readonly="readonly" class="txtarea" >${approverGroup.approverGroupDesc}</textarea>
												</td>
												<td>
													<c:if test="${approverGroup.serialApproval eq true}">
														SERIAL
													</c:if>
													<c:if test="${approverGroup.parallelApproval eq true}">
														Parallel
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
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>