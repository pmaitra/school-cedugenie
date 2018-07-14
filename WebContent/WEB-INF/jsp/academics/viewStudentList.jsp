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
<title>Student List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<c:if test="${insertUpdateStatus ne null}">
		<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
			<span id="infomsg1">${insertUpdateStatus}</span>	
		</div>
	</c:if>
	<c:if test="${message ne null}">
		<div class="errorbox" id="errorboxmsg" style="visibility: visible;" >
			<span id="errorbox">${message}</span>	
		</div>
	</c:if>
	<c:choose>
		<c:when test="${studentList eq null || empty studentList}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
					<span id="infomsgbox">Student List Not Found</span>	
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<form:form id="studentListForm" name="studentListForm" action="getStudentDetailsToView.html" method="POST">
				<section role="main" class="content-body">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
							</div>
					
							<h2 class="panel-title">Student List</h2>
						</header>
						<div class="panel-body">
							<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
								<thead>
									<tr>
										
										<th>Name</th>
										<th>Standard</th>
										<th>Term</th>
										<th>Section</th>
										<th>Roll Number</th>
										<!-- <th>House</th> -->
									</tr>
								</thead>
								<tbody>
									<c:forEach var="student" items="${studentList}">
										<tr class="gradeC"  onClick="window.open('getStudentDetailsToView.html?userId=${student.userId}','_self')" style="cursor:pointer;">
											
											<td>
												${student.studentName}
											</td>
											<td>
												${student.standard}
											</td>
											
											<td>
												${student.studentPassFail}
											</td> 
											<td>							
												${student.section}
											</td>
											<td>
												<c:if test="${student.roll ne null}">
													${student.roll}
												</c:if>
												<c:if test="${student.roll eq null}">
													NA
												</c:if>
											</td>
										</tr>
									</c:forEach>
									
								</tbody>
							</table>
						</div>
					</section>			
				</section>
			</form:form>
		</c:otherwise>
	</c:choose>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>