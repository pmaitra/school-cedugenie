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
	<header class= "page-header">	<!-- Added by Saif 29-03-2018 -->
		<h2>Employer Contribution</h2>
	</header>
	
	<div class = "content-padding">
		<c:if test="${submitResponse ne null}">				
			<c:if test="${submitResponse eq 'Success'}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">Successful</span>	
				</div>
			</c:if>
			<c:if test="${submitResponse eq 'Fail'}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">Failed</span>	
				</div>
			</c:if>		
		</c:if>
	<c:choose>
	<c:when test="${salBreakUpList == null}">
		<div class="infobox" id="infobox" style="visibility:visible;">
			<span id="infomsg">No Salary BreakUp Found to set Slab</span>	
		</div>
	</c:when>	
	<c:otherwise>
		<div class="row">
	 	<form:form name="employerContribution" action="submitEmployerContribution.html" method="POST">
		<div class="col-md-4">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
	
						<h2 class="panel-title">Employer Contribution </h2>										
					</header>
					<div style="display: block;" class="panel-body">	                                   
						<div class="form-group">
                         	<label class="control-label">Tax Type</label>
                         	<select name="miscellaneousTaxType" id="miscTaxTypeName" class="form-control">
								<option value="">Select...</option>
								<c:forEach var= "sbUp" items="${salBreakUpList}">
								<c:if test="${sbUp.salaryBreakUpType eq 'DEDUCTION'}">
									<c:if test="${sbUp.salaryBreakUpName ne 'INCOME TAX'}">
										<option value="${sbUp.salaryBreakUpCode}">${sbUp.salaryBreakUpName}</option>	
									</c:if>
								</c:if>
								</c:forEach>
							</select>
                     	</div>										
                    	<div class="form-group">
                        <label class="control-label">Tax Type Based On</label>                     			
							<select name="taxBasedOn" id="taxBasedOn" class="form-control" >
								<option value="">Select...</option>
								<c:forEach var= "sbUp" items="${nonSlabBreakUpList}">
									<option value="${sbUp.salaryBreakUpCode}">${sbUp.salaryBreakUpName}</option>					
								</c:forEach>
							</select>                                           
                       	</div>  
                        <div class="form-group">
                         		<label class="control-label">Figure Type</label>
                         		<select name="taxFigureType" id="figureType" class="form-control">
								<option value="">Select...</option>
								<option value="AMOUNT">AMOUNT</option>					
								<option value="PERCENTAGE">PERCENTAGE</option>
							</select>        	                                            
						</div>	                                       
					</div>
				</section>
		</div>
		<div class="col-md-8" id="salarySlab" style="visibility: collapse;">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>	
						<h2 class="panel-title">Slab</h2>										
					</header>
					<div style="display: block;" class="panel-body">	                    
						<div class="row">                                            
	                        <div class="col-md-12" >	   
	                        	<table id="contributionSlab" class="table table-bordered table-striped mb-none dataTable">					
									<thead>
										<tr>
											<th>
											Exceed (Monthly)
										</th>
										<th>
											Not Exceed (Monthly)
										</th>
										<th>
											
										</th>
										<th>
										
										</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>  
								<table id="contributionSlabForUpdate" class="table table-bordered table-striped mb-none dataTable" style="visibility: collapse;">					
									<thead>
										<tr>
											<th>
											Exceed (Monthly)
										</th>
										<th>
											Not Exceed (Monthly)
										</th>
										<th>
											
										</th>
										<th>
										
										</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
								<footer style="display: block;" class="panel-footer">				
									<input type="button" class="btn btn-warning" value="EDIT" id="editButton"/>	
									<input type="submit" class="btn btn-primary" value="SUBMIT" id="submitButton"/>									
									<input type="reset" class="btn btn-default" value="clear" id="clearButton"/>
									<input type="hidden" id ="hidVal" name="hidVal" value=""/>
								</footer>
							</div>                        
	                    </div>	                                       
					</div>
				</section>
			</div>
		</form:form>
	</div>
	</c:otherwise>
	</c:choose>
	</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/erp/employerContribution.js"></script>

</body>
</html>