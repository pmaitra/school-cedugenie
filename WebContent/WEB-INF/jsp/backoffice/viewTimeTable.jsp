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
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/backoffice/viewTimeTable.js"></script>

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
								
								
							</h2> 
							</header>
							<div class="panel-body">
							<label>Time-Table For :</label> 
								<select class="form-control" name="semsterName" id="semsterName" style="width:200px" onchange="viewTimeTableData()">
                                     <option value="">Select...</option>
										<c:forEach var="term" items="${termList}">
											<option value="${term.termName}">${term.termName}</option>
										</c:forEach>
                                </select>
                                <hr>
								<div class="table-responsive" style ="display:none" id = "routine">
								<table class="table table-bordered table-striped mb-none" id="timetable">
									<thead>
										<tr>
										    <th></th>
                                            <th colspan="7" >Monday</th>
                                            <th colspan="7" >Tuesday</th>
											<th colspan="7" >Wednesday</th>
											<th colspan="7" >Thursday</th>
											<th colspan="7" >Friday</th>
											<th colspan="7" >Saturday</th>
										</tr>
										</thead>
										<thead>
										<tr>
											<th></th>
                                            
                                            <th>1</th>
                                            <th>2</th>
											<th>3</th>
											<th>4</th>
											<th>5</th>
											<th>6</th>
											<th>7</th>
											
											<th>1</th>
                                            <th>2</th>
											<th>3</th>
											<th>4</th>
											<th>5</th>
											<th>6</th>
											<th>7</th>
											
											<th>1</th>
                                            <th>2</th>
											<th>3</th>
											<th>4</th>
											<th>5</th>
											<th>6</th>
											<th>7</th>
											
											<th>1</th>
                                            <th>2</th>
											<th>3</th>
											<th>4</th>
											<th>5</th>
											<th>6</th>
											<th>7</th>
											
											<th>1</th>
                                            <th>2</th>
											<th>3</th>
											<th>4</th>
											<th>5</th>
											<th>6</th>
											<th>7</th>
											
											<th>1</th>
                                            <th>2</th>
											<th>3</th>
											<th>4</th>
											<th>5</th>
											<th>6</th>
											<th>7</th>
											
										</tr>
									</thead>
									<tbody id = "routineTableBody">
										<%--  <c:forEach var="teacher" items="${teacherList}" varStatus="loop"> 
														
											<tr>
												<td id = "${loop.index}_0">${teacher.teacherName}</td>
                                            
	                                            <td id = "${loop.index}_1"></td>
	                                            <td id = "${loop.index}_2"></td>
	                                            <td id = "${loop.index}_3"></td>
	                                            <td id = "${loop.index}_4"></td>
	                                            <td id = "${loop.index}_5"></td>
	                                            <td id = "${loop.index}_6"></td>
	                                            <td id = "${loop.index}_7"></td>
	                                            
	                                            <td id = "${loop.index}_8"></td>
	                                            <td id = "${loop.index}_9"></td>
	                                            <td id = "${loop.index}_10"></td>
	                                            <td id = "${loop.index}_11"></td>
	                                            <td id = "${loop.index}_12"></td>
	                                            <td id = "${loop.index}_13"></td>
	                                            <td id = "${loop.index}_14"></td>
	                                            
	                                            <td id = "${loop.index}_15"></td>
	                                            <td id = "${loop.index}_16"></td>
	                                            <td id = "${loop.index}_17"></td>
	                                            <td id = "${loop.index}_18"></td>
	                                            <td id = "${loop.index}_19"></td>
	                                            <td id = "${loop.index}_20"></td>
	                                            <td id = "${loop.index}_21"></td>
	                                            
	                                            <td id = "${loop.index}_22"></td>
	                                            <td id = "${loop.index}_23"></td>
	                                            <td id = "${loop.index}_24"></td>
	                                            <td id = "${loop.index}_25"></td>
	                                            <td id = "${loop.index}_26"></td>
	                                            <td id = "${loop.index}_27"></td>
	                                            <td id = "${loop.index}_28"></td>
	                                            
	                                            <td id = "${loop.index}_29"></td>
	                                            <td id = "${loop.index}_30"></td>
	                                            <td id = "${loop.index}_31"></td>
	                                            <td id = "${loop.index}_32"></td>
	                                            <td id = "${loop.index}_33"></td>
	                                            <td id = "${loop.index}_34"></td>
	                                            <td id = "${loop.index}_35"></td>
	                                            
	                                            <td id = "${loop.index}_36"></td>
	                                            <td id = "${loop.index}_37"></td>
	                                            <td id = "${loop.index}_38"></td>
	                                            <td id = "${loop.index}_39"></td>
	                                            <td id = "${loop.index}_40"></td>
	                                            <td id = "${loop.index}_41"></td>
	                                            <td id = "${loop.index}_42"></td>								
											
											</tr>
										</c:forEach> --%>
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
	

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>

</body>
</html>