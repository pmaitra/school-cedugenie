<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Student Attendance</title>
<%@ include file="/include/include.jsp" %>
<%@ include file="/include/js-include.jsp" %>
<link rel="stylesheet" href="/icam/assets/stylesheets/calender.css" />
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
<script type="text/javascript">

</script>
</head>
<body>

	<header class="page-header">
		<h2>Student Attendance</h2>
	</header>
	<div class="content-padding">				
					 
	<div class="row">
		<c:if test="${insertStatusSuccess ne null }">
			<div class = "alert alert-success">
				<strong>${insertStatusSuccess}</strong>
			</div>
		</c:if>
		<c:if test="${insertStatusFail ne null }">
			<div class = "alert alert-danger">
				<strong>${insertStatusFail}</strong>
			</div>
		</c:if>
		 <form name="attendance" action="submitStudentsDailyAttendance.html" method="POST">
			 <div class="col-md-8 col-md-offset-2">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Details For Student Attendance</h2>										
					</header>
					<div id="classDetails" class="panel-body">
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">Class</label>
								<select class="form-control" id="class" name="class">
									<option value="0">Select...</option>
									<c:if test="${classList ne null}">
										<c:forEach var="cl" items="${classList}">
											<option value="${cl}">${cl}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label class="control-label">Section</label>
								<select class="form-control" id="sectionone" name="sectionone">
									<option value="0">Select...</option>
								</select>
							</div>
						</div>
					</div> 
				</section>
			</div>
			<div class="col-md-12">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Attendance</h2>
					</header>
					<div id="emptyMsg" class="panel-body" style="display: none;"></div>
					<div id="title" class="panel-body" style="display: none;">
						<table class="table table-bordered table-striped table-condensed mb-none" >
							<thead>
								<tr>
									<th>Roll No.</th>
									<th>Student Name</th>
									<th><input type="checkbox" id="selectChecked" name="selectChecked">${todayDate}</th>
									<th>Reason For Leave</th>
								</tr>
							</thead>
							<tbody id="attendanceShow">
								
							</tbody>
						</table>
					</div>
					<div id="container" style="display:none"></div>
					<input type="hidden" id="currentDate" name="currentDate" value="${todayDate}"/>
					
					<footer style="display: block;" class="panel-footer">
						<button type="submit" id="allSubmit" class="btn btn-primary" style="display: none;">Submit</button>
					</footer>
				</section>
			</div>
		</form>
	</div>	
	</div>				
	<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
	<%@ include file="/include/js-include.jsp" %>
	<script src="/icam/js/backoffice/studentDailyAttendance.js"></script>
</body>
</html>