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
<title>Search Ex-Students</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Search Ex-Students</h2>
</header>
<div class="content-padding">
	<div class="row">
		<div class="col-md-8 col-md-offset-2">
			<form method="POST" action="searchExStudents.html">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Search Ex-Students</h2>
					</header>
					<div style="display: block;" class="panel-body">
						<div class="col-md-4">
                        	<div class="form-group">
                               <label class="control-label">First Name</label>
                               <input type="text" class="form-control" id="firstName" name="firstName" placeholder="" value="${parameters.firstName}" pattern = "[A-Za-z\s]{1,50}"  title="First Name Contains Charecter">
                           </div>
                   		</div>
                       	<div class="col-md-4">
	                        <div class="form-group">
	                            <label class="control-label">Middle Name</label>
	                            <input type="text" class="form-control" id="middleName" name="middleName" placeholder="" value="${parameters.middleName}" pattern = "[A-Za-z\s]{1,50}"  title="Middle Name Contains Charecter">
	                        </div>
                        </div>
                        <div class="col-md-4">
	                        <div class="form-group">
	                            <label class="control-label">Last Name</label>
	                            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="" value="${parameters.lastName}" pattern = "[A-Za-z\s]{1,50}"  title="Last Name Contains Charecter">
	                        </div>
                        </div>
                       	<div class="col-md-4">
	                        <div class="form-group">
	                            <label class="control-label">Roll Number<span class="required" aria-required="true">*</span></label>
	                            <input type="text" class="form-control" id="rollNumber" name="rollNumber" placeholder="" value="${parameters.rollNumber}" required>
	                        </div>
                        </div>
                        <div class="col-md-4">
	                        <div class="form-group">
	                            <label class="control-label">Year</label>
	                            <input type="text" class="form-control" id="year" name="year" placeholder="" value="${parameters.year}">
	                        </div>
                        </div>
					</div>
					<footer style="display: block;" class="panel-footer">
						<button type="submit" name="searchSubmit" id="search" class="btn btn-primary">Search </button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
				</section>
            </form>
		</div>
	</div>		
	
	<div class="row"> 
 		<div class="col-md-12">
	 		<section class="panel">
		 		<div class="panel-body">
					<table id="datatable-tabletools" class="table table-bordered table-striped mb-none">
						<tr>
							<th>Roll</th>
							<th>Name</th>
							<th>Father Name</th>
							<th>Mother Name</th>
							<th>DOB</th>
							<th>Date Of Admission</th>
							<th>Email</th>
							<th>Mobile</th>
							<th>Joining Std.</th>
							<th>Leaving Std.</th>
							<th>Character</th>
						</tr>
						<c:forEach var="student" items="${exStudentsList}">
							<tr>
								<td>
									${student.rollNumber}
								</td>
								<td>
									${student.name}
								</td>
								<td>
									${student.fatherName}
								</td>
								<td>
									${student.motherName}
								</td>
								<td>
									${student.dateOfBirth}
								</td>
								<td>							
									${student.dateOfAdmission}
								</td>
								<td>
									${student.email}
								</td>
								<td>
									${student.mobile}
								</td>
								<td>
									${student.joinStandard}
								</td>
								<td>
									${student.lastStandard}
								</td>
								<td>
									${student.studentCharacter}
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</section>
		</div>
	</div>
</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>