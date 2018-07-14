<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>

<html>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>View Time Table</title>
<%@ include file="/include/include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>


<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: center;
}
.editableTable {
    border:solid 1px;
    width:100%
}
 
.editableTable td {
    border:solid 1px;
}
 
.editableTable .cellEditing {
    padding: 0;
}
 
.editableTable .cellEditing input[type=text]{
    width:100%;
    border:0;
    background-color:rgb(255,253,210);  
}

.tdconflictBackground{
  background-color:red;
}

.tdnoconflictBackground{
  background-color:white;
}
</style>
</head>
<body>
<header class="page-header">
			<h2>Configure Time Table</h2>
		</header>

	<div class="content-padding">
	<c:if test="${insertStatus eq 'success'}">
		<div class="alert alert-success">
			<strong>${msg}</strong>
		</div>
	</c:if>
			
	<c:if test="${insertStatus eq 'fail'}">
		<div class="alert alert-danger">
			<strong>${msg}</strong>
		</div>
	</c:if>

		<div class="row">
			<form:form method="" action="" >
                 <div class="col-md-12">						  
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
							</div>
							<h2 class="panel-title">
								Time Table For : ${teacherName}
								
							</h2> 
							</header>
							<div class="panel-body">
							<label>Program :</label> 
								<select class="form-control" name="program" id="program" style="width:200px" required>
                                     <option value="">Select...</option>
										<c:forEach var="course" items="${courseList}">
											<option value="${course.courseName}">${course.courseName}</option>
										</c:forEach>
                               </select>
                                
								<label>Semester :</label> 
								<select class="form-control" name="semsterName" id="semsterName" style="width:200px" onchange="viewMyTimeTableData()" required>
                                     <option value="">Select...</option>
										<c:forEach var="term" items="${termList}">
											<option value="${term.termName}">${term.termName}</option>
										</c:forEach>
                                </select>
                                <input type = "hidden" name = "userId" id = "userId" value = "${userId}">
                                <hr>
								<div class="table-responsive" style ="display:none" id = "routine">
								<table class="table table-bordered table-striped mb-none" id="timetable">
									<thead>
										<tr>
										   
                                            <th>Monday</th>
                                            <th>Tuesday</th>
											<th>Wednesday</th>
											<th>Thursday</th>
											<th>Friday</th>
											<th>Saturday</th>
										</tr>
										</thead>
										
									<tbody id="timetableBody">
										
									</tbody>
								</table> 
								</div>
							</div>
                            <footer style="display: block;" class="panel-footer">
                                
                            </footer>
						</section>
					</div>
                </form:form>
            </div>	
		</div>		
	

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/common/viewTimeTableForStudent.js"></script>
</body>
</html>