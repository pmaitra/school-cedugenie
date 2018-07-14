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
<title>Sub Departments Budget Allocation</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	
		<header class="page-header">
			<h2>Sub Departments Budget Allocation</h2>
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
					<form:form name="libraryPolicy"  method="POST" action="saveSubDeptsBudget.html">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>
								<h2 class="panel-title">Sub Departments Budget Allocation</h2>										
							</header>
							<div style="display: block;" class="panel-body">
								<div class="row">            
									<div class="col-md-6 col-md-offset-3">                                                            
										<div class="form-group">
                                                   <label class="control-label">Financial Year<span aria-required="true" class="required">*</span></label>
                                                   <select class="form-control" id="financialYear" name="financialYear" required>
                                                       <option value="">Select...</option>
                                                      	<c:forEach var="fy" items="${financialYearList}">
														<option value="${fy.financialYearName}">${fy.financialYearName}</option>
													</c:forEach>
                                                   </select>
										</div> 
										<div class="form-group">
											<label class="control-label">Department</label>
											<div>
												<input id="department" name="department" class="form-control" type="text" value="${department}" readonly="readonly">
											</div>	
										</div>
										<div class="form-group">
											<label class="control-label">Allocated Amount</label>
											<div>
												<input id="allocatedAmount" name="allocatedAmount" class="form-control" type="text" value="0.0" readonly="readonly">
											</div>	
										</div>
										<div class="form-group">
											<label class="control-label">Available Amount</label>
											<div>
												<input id="availableFund" name="availableFund" class="form-control" type="text" value="0.0" readonly="readonly">
											</div>	
										</div>
										<div class="form-group">
											<label class="control-label">Reserve Fund</label>
											<div>
												<input id="reserveFund" name="reserveFund" class="form-control" type="text" value="0.0" readonly="readonly">
											</div>	
										</div>  
                                     </div>
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
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/subDepartmentsBudgetAllocation.js"></script>
<script type="text/javascript">

</script>
</body>
</html>