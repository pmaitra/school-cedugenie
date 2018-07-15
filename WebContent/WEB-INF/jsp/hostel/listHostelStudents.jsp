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
<title>List Books</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
<script type="text/javascript">
window.onload=function(){
	showStudents(document.getElementById("hostel"));
};

function showStudents(hostel){
	var studentTables=document.getElementsByName("allWrapperDivs");
	for(var i=0;i<studentTables.length;i++){
		var div=document.getElementById(studentTables[i].value);
		div.style.display="none";
	}
	
	var hostelName=hostel.value;
		document.getElementById(hostelName+"_wrapper").style.display="block";
}

</script>
</head>
<body>
<c:choose>	
	<c:when test="${hostelList eq null || empty hostelList || studentList eq null || empty studentList}">
		<div class="btnsarea01" >
			<c:if test="${hostelList eq null || empty hostelList}">
				<div class="errorbox" id="errorbox0" style="visibility: visible;">
					<span id="errormsg0">Hostel List Not Found</span>	
				</div>
			</c:if>
			<c:if test="${studentList eq null || empty studentList}">
				<div class="errorbox" id="errorbox1" style="visibility: visible;">
					<span id="errormsg1">Student List Not Found</span>	
				</div>
			</c:if>
		</div>
	</c:when>
	<c:otherwise>

			Hostel :: <select id="hostel" onchange="showStudents(this);">
						<option value="">Select....</option>
						<c:forEach var="hostel" items="${hostelList}">
							<option value="${hostel.hostelCode}">${hostel.hostelName}</option>
						</c:forEach>
					</select>
			
			<section role="main" class="content-body">
					
			
			
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Hostel Student List</h2>
							</header>
							<div class="panel-body"  id="studentTable">
								<c:forEach var="hostel" items="${hostelList}">
									
											<table class="table table-bordered table-striped mb-none studentsTables" id="${hostel.hostelName}">
												<thead>
													<tr>
														<th>Roll Number</th>
														<th>Name</th>
														<th>Standard</th>
														<th>Section</th>
														<th>House</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="student" items="${studentList}">
														<c:if test="${hostel.hostelName eq student.house}">
															<tr class="gradeC">
																<td>${student.rollNumber}</td>
																<td>${student.studentName}</td>
																<td>${student.standard}</td>
																<td>${student.section}</td>
																<td>${student.house}</td>
															</tr>
														</c:if>
													</c:forEach>									
												</tbody>
													
											</table>
										
									</c:forEach>
								
								
							</div>
						</section>			
					</section>


	</c:otherwise>
</c:choose>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/hostel/listHostelStudents.js"></script>
<!--<c:forEach var="hostel" items="${hostelList}">
 <script type="text/javascript">

(function($) {

	'use strict';

	var datatableInit = function() {
		var $table = $('#${hostel.hostelName}');

		$table.dataTable({
			sDom: "<'text-right mb-md'T>" + $.fn.dataTable.defaults.sDom,
			oTableTools: {
				sSwfPath: $table.data('swf-path'),
				aButtons: [
					{
						sExtends: 'pdf',
						sButtonText: 'PDF'
					},
					{
						sExtends: 'csv',
						sButtonText: 'CSV'
					},
					{
						sExtends: 'xls',
						sButtonText: 'Excel'
					},
					{
						sExtends: 'print',
						sButtonText: 'Print',
						sInfo: 'Please press CTR+P to print or ESC to quit'
					}
				]
			}
		});

	};

	$(function() {
		datatableInit();
	});

}).apply(this, [jQuery]);

</script> 
	<%-- <div style="display:none;">
		<c:forEach var="hostel" items="${hostelList}">
			<input type="text" name="allWrapperDivs" value="${hostel.hostelName}_wrapper">
		</c:forEach>
	</div> --%>
</c:forEach>-->
</body>
</html>