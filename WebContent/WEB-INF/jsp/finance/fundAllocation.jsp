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
<title>Fund Allocation</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">
		<h2>Fund Allocation</h2>
	</header>
	<div class="content-padding">
		 <c:if test="${status1 eq 'Insert Successful'}">
			<div class="alert alert-success">
				<strong>UPDATE Successful</strong>
			</div>
		</c:if>
		 <c:if test="${status2 eq 'Insert Successful'}">
			<div class="alert alert-success">
				<strong>UPDATE Successful</strong>
			</div>
		</c:if>
			
		<c:choose>
			<c:when test ="${financialYear==null}">
				<div class="btnsarea01">
					<div class="errorbox" id="errorbox" style="visibility:visible;" >
						<span id="errormsg">Financial Year Not Available.</span>	
					</div>
				</div>
			</c:when>
			<c:otherwise>
                 <div class="row">
					<div class="col-md-4 col-md-offset-4">
					  <form action="updateBudgetDetails.html" method="post">
							<section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
									</div>

									<h2 class="panel-title">Reserve Fund Details</h2>										
								</header>
								<div style="display: block;" class="panel-body">
									<div class="form-group">
										 <label class="control-label">Financial Year<span aria-required="true" class="required">*</span></label>
										 <input name="academicYear" id="academicYear" class="form-control" type="text" placeholder="" value="${financialYear}" readonly >
                                    </div>
								</div>
								<div style="display: block;" class="panel-body">  
										<div class="form-group">
										<label class="control-label"><b>Budget Amount:</b></label>
											<input name="budgetAmount" id="budgetAmount" class="form-control" type="text" placeholder="" value="${budgetAmount}" readonly >
										</div>
                                    
								</div>
									<div style="display: block;" class="panel-body">  
										<div class="form-group">
										<label class="control-label"><b>Reserve Fund:</b></label>
											<input type="hidden" id="resFund" value="${reserveFund}">
											<input name="reserveFund" id="reserveFund" class="form-control" type="text" placeholder="" value="${reserveFund}" readonly >
										</div>
                                    </div>
									  <div style="display: block;" class="panel-body">  
										<div class="form-group">
											<label class="control-label"><b>Department : <span class="required" aria-required="true">*</span></b></label>
												<select class="form-control" name="department" id="department" required>
                                                    <option value="0">Select...</option>
                                                    
                                                    <c:forEach var="department" items="${departmentList}" varStatus="i">
                                                    
														<option value="${department.departmentCode}">${department.departmentCode}</option>
													
													</c:forEach>
                                                </select> 
										</div>
									 </div> 
									 <div class="panel-body">
										<label class="control-label" ><b>Percentage:</b></label>
										<input name="percentage" id="percentage" class="form-control" type="text" placeholder="Enter percentage" onblur="per()" required >
									</div>
									<div class="panel-body">
										<label class="control-label" ><b>Amount:</b></label>
										<input name="amount" id="amount" class="form-control" type="text" placeholder="Amount value" readonly >
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit">Submit </button>
										<button type="reset" class="btn btn-default" onclick= "remove()">Reset</button>
									</footer>
								</section>
							</form>
						</div>
					</div>
			</c:otherwise>
		</c:choose>
	</div>									
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/finance/fundAllocation.js"></script> 
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
</body>
</html>
