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
<title>Create Term</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
<script type="text/javascript">

</script>




</head>
<body>
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
		
					<!-- start: page -->
                    <div class="row">
						<div class="col-md-6 col-md-offset-3" >
						  <form action="getAminitiesUsedByStudent.html" method="GET">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Student Facility</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="col-sm-5 control-label"><b>Student Name:</b> </label>
											<div class="col-sm-7">
												<label ><b>${student.studentName}</b> </label>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label"><b>Roll Number:</b></label>
											<div class="col-sm-7">
												<label ><b>${student.rollNumber}</b> </label>
												
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label"><b>Facility List: </b></label>
											<div class="col-sm-7">
											<%-- 	<c:forEach var="facility" items="${student.facilityList}" varStatus="i">
													<input id="facility" name="facility" class="form-control"  value ="${facility.facilityName}" >
												</c:forEach>--%>
											</div> 
											<table class="table table-bordered table-striped mb-none" id="TeahetAttributeColumn"> 
												<tbody>
													<c:forEach var="facility" items="${student.facilityList}" varStatus="i">
														<tr>
															<td>
																<div class="">
																	<input id="facility" name="facility" class="form-control"  value ="${facility.facilityName}" readonly>
																</div>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											</div>
										</div>
									</div>
									<footer class="panel-footer">
										<button class="btn btn-primary" type="submit">Back </button>
									</footer> 
								</section>
                            </form>
						</div>
					</div>				
					
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>