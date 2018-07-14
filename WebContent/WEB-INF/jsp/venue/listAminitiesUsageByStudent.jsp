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
<title>Student Details Form</title>
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
	<header class= "page-header">	<!-- Added by Saif 29-03-2018 -->
		<h2>Aminities Usage by Students</h2>
	</header>
	<div class = "content-padding">
		<c:if test="${insertUpdateStatus eq 'success'}">
				<div class="alert alert-success">
					<strong>${msg}</strong>
				</div>
			</c:if>
			
			<c:if test="${insertUpdateStatus eq 'fail'}">
				<div class="alert alert-danger">
					<strong>${msg}</strong>
				</div>
			</c:if>
			<%-- <c:choose>
				<c:when test="${courseList eq null || empty courseList}">
					<div class="btnsarea01" >
						<div class="errorbox" id="errorbox" style="visibility: visible;">
							<c:if test="${standardList eq null || empty standardList}">
								<span id="errormsg">Program Not Found</span>	
							</c:if>
						</div>
					</div>
				</c:when>
			<c:otherwise> --%>

					<!-- <div class="row"> -->
					<div class="row">	
					<form action="getAminitiesUsageDetaulsByStudent.html" method="post">
						
							<div class="col-md-4 col-md-offset-4">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Aminities Usage</h2>										
									</header>
									
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="col-sm-4 control-label">Programme: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<select class="form-control" id="standard" name="standard">
                                                    <option value="">Select...</option>
                                                    <option value="All">All</option>
                                                    <c:forEach var="course" items="${courseList}" varStatus="i">
														<option value="${course.courseCode}">${course.courseName}</option>
													</c:forEach>
                                                </select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 control-label">Batches :<span class="required" aria-required="true">*</span> </label>
											<div class="col-sm-7">
												<select class="form-control" id="section" name="section">
                                                </select>
											</div>
										</div>
									</div>
								</section>	
						</div>
						<div class="col-md-12">	
						<section class="panel">		
							<div class="panel-body">
								<c:forEach var="subject" items="${subjectList}" varStatus="i">
									<input type="hidden" name="oldSubjectNames" value="${subject.subjectName}">
								</c:forEach>
								<table class="table table-bordered table-striped mb-none" id="datatable-editable" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Select</th>
											<th>Roll Number</th>
											<th>Name</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
							
							<!-- <footer style="display: block;" class="panel-footer">
								<button type="submit" class="btn btn-primary">Details </button>
								<button type="reset" class="btn btn-default">Reset</button>
							</footer>  -->
						</section>
							</div>
							
						<!-- </section> -->
                     </form>
				 </div> 
		<%-- //</c:otherwise>
	//</c:choose>	 --%>
	</div>
				
                  


<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/icam/js/hostel/createHostel.editable.js"></script>
<script src="/icam/js/venue/listAminitiesUsageByStudent.js"></script>
</body>
</html>