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
<title>Budget</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	
		<header class="page-header">
			<h2>Budget</h2>
		</header>
		<div class="content-padding">
			<c:if test="${status eq 'success'}">
			
				<div class="alert alert-success">
					<strong>Budget Created SuccessFully</strong>	
				</div>
			</c:if>
			<c:if test="${status eq 'fail'}">
			
				<div class="alert alert-danger">
					<strong>Failed To Assign Budget</strong>	
				</div>
			</c:if>
			<c:if test="${status eq 'update'}">
			
				<div class="alert alert-success">
					<strong>Budget Updated SuccessFully</strong>	
				</div>
			</c:if>
			
			<div class="row">
				<div class="col-md-12">
					<form:form name="libraryPolicy"  method="POST" action="saveBudget.html">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>
								<h2 class="panel-title">Budget</h2>										
							</header>
							<div style="display: block;" class="panel-body">
								<div class="row">            
									<div class="col-md-6 col-md-offset-3">                                                            
										<div class="form-group">
                                                   <label class="control-label">Financial Year<span aria-required="true" class="required">*</span></label>
                                                   <select class="form-control" id="academicYear" name="academicYear" required>
                                                       <option value="">Select...</option>
                                                      	<c:forEach var="fy" items="${financialYearList}">
														<option value="${fy.financialYearName}">${fy.financialYearName}</option>
													</c:forEach>
                                                   </select>
                                               </div>   
                                           </div>
                                          </div>
                                          <hr>
                                          <div class="row" id="balanceDiv">
                                          	<div id="unallocatedBalancediv" class="form-group col-sm-4" style=" display:block">
                                            	<label class="control-label">Previous Year Unallocated Amount:</label>
											<div>
												<input id="unallocatedBalance" name="unallocatedBalance" class="form-control" type="text" value="0.0" readonly="readonly">
											</div>	
										</div>
                                          	<div id="reserveBalancediv" class="form-group col-sm-4" style=" display:block">
                                            	<label class="control-label">Previous Year Reserve Balance:</label>
											<div>
												<input id="reserveBalance" name="reserveBalance" class="form-control" type="text" value="0.0" readonly="readonly">
											</div>	
										</div>  
										<div id="currentAmountdiv" class="form-group col-sm-4" style=" display:block">
                                            	<label class="control-label">Current Year Budget Amount: <span class="required" aria-required="true">*</span></label>
											<div>
												<input id="currentAmount" name="currentAmount" class="form-control" type="text" value="0.0" onfocus="this.value=''" onblur="calculateTotalAmount()" required >
											</div>	
										</div>
                                        	</div>
                                          <hr>
                                          <div class="row">
                                          	<div id="totalIncomediv" class="form-group col-sm-6 col-sm-offset-3" style=" display:block">
                                            	<label class="col-sm-6 control-label">Total Amount: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<input id="totalIncome" name="totalIncome" class="form-control" type="text" value="0.0" readonly="readonly" >
											</div>	
										</div>                                            
                                        	</div>
                                        	<hr>
                                        	<div class="row" id="remainingDiv">
										<table id="balanceDetailsTable"  class="table table-bordered table-striped mb-none" >
											<td>
												<label class="col-sm-6 control-label">Remaining Percentage:</label>
												<div class="col-sm-6">
													<input id="remainingPercentage" name="remainingPercentage" class="form-control"  value="100" readonly>
												</div>
											</td>
											<td>
												<label class="col-sm-6 control-label">Remaining Balance:</label>
												<div class="col-sm-6">
													<input id="remainingBalance" name="remainingBalance" class="form-control"   readonly>
												</div>
											</td>
										</table>
                                        	</div>
                                        	<div id="messageDiv" class="alert alert-success" style="display: none">
                                        		<p align="center">
                                        			<strong>Budget is already created for this Financial Year. For further fund allocation go to the link  <i>Reserve Fund Allocation</i></strong>
                                        		</p>
                                        	</div>
                                        	<hr>
                                          <table id="budgetTable"  class="table table-bordered table-striped mb-none" style="visibility: collapse;">
                                          	<tr>
											<th>Department</th>
											<th>Percentage</th>
											<th>Actual Amount</th>				
											<th>Total Expense</th>
											<th>Balance</th>
											<th>Reserve Balance</th>
										</tr>
									</table>
                            </div>
									<footer id="submitDiv" style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type= "submit" id="submitLibraryPolicy" name="submitLibraryPolicy">Submit </button>
										<button type="reset" class="btn btn-default" name="reset" >Reset</button>
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
<script type="text/javascript" src="/icam/js/finance/budget.js"></script>
<script type="text/javascript">

</script>
</body>
</html>