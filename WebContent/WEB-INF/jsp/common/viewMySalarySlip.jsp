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
<title>Salary Break Up</title>
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
			<h2>View Salary Slip</h2>
		</header>

<div class="content-padding">
	<c:if test="${status eq 'NA'}">
		<div class="alert alert-danger" >
			<strong>${msg}</strong>	
		</div>
	</c:if>
	<div class="row">
		<div class="col-md-5">
		  	
		
				<form:form method="POST" id="getMySalaryDetails" name="getMySalaryDetails" action="getMySalaryDetails.html">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
	
							<h2 class="panel-title">Employee's Salary Slip</h2>										
						</header>
						<div style="display: block;" class="panel-body">                                    
                          	<!-- <div class="form-group">
                              	<label class="control-label">User Id</label>
                              	<input type="text" class="form-control" name="userId" id="userId" placeholder="" required>
                          	</div>	 -->									
	                       		<div class="form-group">
	                              	<label class="control-label">Select Year</label>                     			
									<select id="year" name="year" class="form-control" required>
										<option value="">select year</option> 
										<c:if test="${year ne null}">
											<c:forEach var="year" items="${year}">
												<option>
												${year.academicYearName}
												</option>
											</c:forEach>
										</c:if>
									</select>                                           
	                          	</div>  
	                          <div class="form-group">
	                          		<label class="control-label">Salary Month</label>
	                          		<select id="month" name="month" class="form-control" required>				
									<option value="">select Month</option> 
									<option value="01">January</option>
									<option value="02">February</option>
									<option value="03">March</option>
									<option value="04">April</option>
									<option value="05">May</option>
									<option value="06">June</option>
									<option value="07">July</option>
									<option value="08">August</option>
									<option value="09">September</option>
									<option value="10">October</option >
									<option value="11">November</option>
									<option value="12">December</option>
								</select>                     	                                            
							</div>
						</div>
						<footer style="display: block;" class="panel-footer">
							<button class="btn btn-primary" type="submit" >Submit </button>
							<button type="reset" class="btn btn-default">Reset</button>											
						</footer>					
					</section>
                </form:form>
              
			</div>					
		</div>

		</div>
	
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/erp/viewStaffSalarySlip.js"></script>

</body>
</html>