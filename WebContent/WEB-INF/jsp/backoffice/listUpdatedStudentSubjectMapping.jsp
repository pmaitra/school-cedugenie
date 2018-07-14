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
<title>Student Subject Mapping List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<c:choose>	
	<c:when test="${null eq studentSubjectMappingList || empty studentSubjectMappingList}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">List Not Found</span>
			</div>
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
						
								<h2 class="panel-title">Student Subject Mapping List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th colspan="2" style="font-size:18px; text-align:center;">
												Student Subject Mapping :: Standard :- ${student.standard} &nbsp;&nbsp;&nbsp;  Section :- ${student.section}
												<input type="button" class="greenbtn" value="PRINT REPORT" onclick="window.open('studentSubjectMapping.html?standard=${student.standard}&section=${student.section}','_self')">
											</th>
										</tr>
										<tr>
											<th>Roll Number (Name)</th>
											<th>Subjects</th>
										</tr>
										<c:forEach var="stud" items="${studentSubjectMappingList}">
											<tr>
												<td>
													${stud.status}
												</td>
												<td>
													<c:forEach var="sub" items="${stud.subjects}">
														${sub.subjectName} &nbsp;&nbsp;&nbsp;&nbsp;
													</c:forEach>
												</td>
											</tr>
											</c:forEach>
									</thead>
									<tbody>
										
									</tbody>
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