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
	<div class="row">
		<div class="col-md-5">
		  	<c:set var="earningTotalAmount" value="0" scope="page" />
			<c:set var="deductionTotalAmount" value="0" scope="page" />
			<c:set var="extraTotalAmount" value="0" scope="page" />
			<c:if test="${staffForViewStaffSalaryDetails.resource.userId eq null}">
				<form:form method="POST" id="getStaffSalaryDetails" name="getStaffSalaryDetails" action="getStaffSalaryDetails.html">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
	
							<h2 class="panel-title">Employee's Salary Slip</h2>										
						</header>
						<div style="display: block;" class="panel-body">                                    
                          	<div class="form-group">
                              	<label class="control-label">User Id</label>
                              	<input type="text" class="form-control" name="userId" id="userId" placeholder="" required>
                          	</div>										
                       		<div class="form-group">
                              	<label class="control-label">Select Year</label>                     			
								<select id="year" name="year" class="form-control" required>
									<option value="select">select year</option> 
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
								<option value="select">select Month</option> 
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
							<button class="btn btn-primary" type="submit" id="submitButton">Submit </button>
							<button type="reset" class="btn btn-default">Reset</button>											
						</footer>					
					</section>
                </form:form>
                </c:if>               
			</div>					
		</div>

		<c:if test="${staffForViewStaffSalaryDetails ne null}">	
			<c:choose>
				<c:when test="${staffForViewStaffSalaryDetails.salaryBreakUpList eq null ||  staffForViewStaffSalaryDetails.salaryBreakUpList.size() eq 0}">
					<div class="errorbox" id="errorbox" style="visibility:visible;">
							<span id="errormsg">Result not available</span>
					</div>			
				</c:when>
				<c:otherwise>
				<section role="main" class="content-body">
						<!-- start: page -->
					 <div class="row">
						<div class="col-md-8 col-md-offset-2">
						  <form:form method="POST" id="saveStaffSalaryDetails" name="saveStaffSalaryDetails" action="saveStaffSalaryDetails.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
				
										<h2 class="panel-title">Salary Details for the month of <c:if test="${salaryMonth eq 1}">January</c:if>
																								<c:if test="${salaryMonth eq 2}">February</c:if>
																								<c:if test="${salaryMonth eq 3}">March</c:if>
																								<c:if test="${salaryMonth eq 4}">April</c:if>
																								<c:if test="${salaryMonth eq 5}">May</c:if>
																								<c:if test="${salaryMonth eq 6}">June</c:if>
																								<c:if test="${salaryMonth eq 7}">July</c:if>
																								<c:if test="${salaryMonth eq 8}">August</c:if>
																								<c:if test="${salaryMonth eq 9}">September</c:if>
																								<c:if test="${salaryMonth eq 10}">October</c:if>
																								<c:if test="${salaryMonth eq 11}">November</c:if>
																								<c:if test="${salaryMonth eq 12}">December</c:if> ${salaryYear} 
											<input type="hidden" name="resourceAttendance.month" id="selectedMonth" value="${salaryMonth}"/>
											<input type="hidden" name="resourceAttendance.year" id="selectedYear" value="${salaryYear}"/>
										</h2>										
									</header>
									<div style="display: block;" class="panel-body">
					                    <div class="row">
					                        <div class="col-md-4">
					                            <div class="form-group">
					                                <label class="control-label"><b>Employee User ID</b></label>
					                                <div>${staffForViewStaffSalaryDetails.resource.userId}</div>
					                                <input type="hidden" name="resource.userId" id="hiddenId" value="${staffForViewStaffSalaryDetails.resource.userId}"/>
					                            </div>
					                            <div class="form-group">
					                                <label class="control-label"><b>Employee Name</b></label>
					                                <div>${staffForViewStaffSalaryDetails.resource.name}</div>
					                            </div>
					                        </div>
					                        <div class="col-md-4">
					                            <div class="form-group">
					                                <label class="control-label"><b>Designation</b></label>
					                                <div>${staffForViewStaffSalaryDetails.designation.designationName}</div>
					                            </div>
					                            <div class="form-group">
					                                <label class="control-label"><b>Job Type</b></label>
					                                <div>${staffForViewStaffSalaryDetails.jobType.jobTypeName}</div>
					                            </div>
					                        </div>
					                        
											<c:if test="${staffForViewStaffSalaryDetails.status eq null}">
					                       		 <div class="col-md-4" id="ledgerCode">
						                            <div class="form-group" >
						                                <label class="control-label"><b>Ledger</b></label>
						                                <%-- <div>${staffForViewStaffSalaryDetails.resource.panCardNo}</div> --%>
						                               <select class="form-control" name="ledgerCode" id="ledgerCode" required>
															<option value="">Select...</option>
															<c:forEach var="ledger" items="${ledgerList}">
																<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
															</c:forEach>
														</select>
						                            </div>
					                        	</div>
					                        </c:if>
					                    </div>
				                        <div class="row">
				                            <hr>
				                            <div class="col-md-6">
				                                <table class="table table-bordered table-striped mb-none dataTable">
				                                    <thead>
				                                        <tr>
				                                            <td style="background:#eee; text-align:center;" colspan="3"><b>Attendance Details of the month</b></td>
				                                        </tr>
				                                        <tr>
				                                            <th>Working Days</th>
				                                            <th>Absent Days</th>
				                                            <th>Extra Working Days</th>
				                                        </tr>
				                                    </thead>
				                                    <tbody>
				                                        <tr>
				                                            <td>${staffForViewStaffSalaryDetails.resourceAttendance.attendanceDesc}<input type="hidden" name="studentAttendance.totalDays" value="${staffForViewStaffSalaryDetails.resourceAttendance.attendanceDesc}"/></td>
															<td>${staffForViewStaffSalaryDetails.resourceAttendance.absentDay}<input type="hidden" name="studentAttendance.presentDays" value="${staffForViewStaffSalaryDetails.resourceAttendance.absentDay}"/></td>
				                                            <td>${staffForViewStaffSalaryDetails.resourceAttendance.extraWorkingDays}</td>
				                                        </tr>
				                                    </tbody>
				                                </table>				                                			                                
				                            </div>
				                            <div class="col-md-6">
				                                <table class="table table-bordered table-striped mb-none dataTable">
				                                    <thead>
				                                        <tr>
				                                            <td style="background:#eee; text-align:center;" colspan="3"><b>Leave Details</b></td>
				                                        </tr>
				                                        <tr>
				                                            <th>Leave Type</th>
				                                            <th>Avails</th>
				                                            <th>Remains</th>
				                                        </tr>
				                                    </thead>
				                                    <tbody>
				                                         <c:if test= "${staffForViewStaffSalaryDetails.leaveList ne null && staffForViewStaffSalaryDetails.leaveList.size() ne 0}">
															<c:forEach var="leave" items="${staffForViewStaffSalaryDetails.leaveList}">
															<tr>
															<td>${leave.leaveType}</td>															
															<td>${leave.totalAvailLeave}</td>																
															<td>${leave.remainingLeave}</td>		
															</tr>						
															</c:forEach>
														</c:if>	
				                                    </tbody>
				                                </table>				                               
				                            </div>
				                        </div>
				                     	<div class="row">
				                            <hr>                                            
				                            <div class="col-md-12">
				                              <c:if test="${staffForViewStaffSalaryDetails.salaryBreakUpList ne null}">
				                    			<div id="userSalaryBreakUpInfo">	                    
					                    			<div class="form-group col-sm-6">
					                    				
															<b>SALARY BREAK-UP <input type="hidden" name="resource.salaryTemplateCode" value="${staffForViewStaffSalaryDetails.resource.salaryTemplateCode}"/></b>
														
					                             		<table cellspacing="0" cellpadding="0" class="table table-bordered table-striped mb-none" id="salaryStructure">
															<tr>
																<th>EARNING</th>
																<th>AMOUNT(Rs.)</th>
															</tr>
															<c:forEach var="salaryBreakUpType" items="${staffForViewStaffSalaryDetails.salaryBreakUpList}" varStatus="varIndex">
																<c:if test="${salaryBreakUpType.salaryBreakUpType eq 'EARNING'}">
																	<tr>
																		<td>${salaryBreakUpType.salaryBreakUpName}</td>
																		<td>${salaryBreakUpType.amount}</td>
																		<c:set var="earningTotalAmount" value="${earningTotalAmount + salaryBreakUpType.amount}" scope="page"/>						
																	</tr>
																</c:if>
															</c:forEach>
														</table>	
						                         	</div>
					                             	<div class="form-group col-sm-6">
					                             		<b> </b>
					                                 	<table cellspacing="0" cellpadding="0" class="table table-bordered table-striped mb-none" id="salaryStructureForDeduction">
															<tr>
																<th>DEDUCTION</th>
																<th>Amount(Rs.)</th>
															</tr>
															<c:forEach var="salaryBreakUpType" items="${staffForViewStaffSalaryDetails.salaryBreakUpList}">
																<c:if test="${salaryBreakUpType.salaryBreakUpType eq 'DEDUCTION'}">
																<tr>
																	<td>${salaryBreakUpType.salaryBreakUpName}
																	</td>		
																	<td>${salaryBreakUpType.amount}</td>
																	<c:set var="deductionTotalAmount" value="${deductionTotalAmount + salaryBreakUpType.amount}" scope="page"/>
																	<c:if test="${salaryBreakUpType.slab eq true}">
																		<input type="hidden" name="slabTax" id="${salaryBreakUpType.salaryBreakUpName}" class="textfield1" value="${salaryBreakUpType.salaryBreakUpCode}-${salaryBreakUpType.salaryBreakUpName}-${salaryBreakUpType.amount}" />
																	</c:if>
																</tr>
																<c:if test="${salaryBreakUpType.salaryBreakUpCode eq staffForViewStaffSalaryDetails.individualDeduction.salaryBreakUpCode}">
																	<input type="hidden" name="indiCut" id="indiCut" class="textfield1" value="${salaryBreakUpType.salaryBreakUpDesc}-${salaryBreakUpType.amount}" />
																</c:if>
																</c:if>
															
															</c:forEach>	
															<tr>
																<td style="color: black;">Total Deduction</td>
																<td style="color: black;">${deductionTotalAmount}
																</td>
															</tr>
															
<!-- 															<tr> -->
<%-- 																<c:set var="deductionForExcessLeave" value="${((earningTotalAmount/daysInMonth)*staffForViewStaffSalaryDetails.leave.excessLeave)}" scope="page"/> --%>
<!-- 																<td style="color: black;">Excess Leave Deduction<img class="required" alt="Required" src="/sms/images/required.gif"></td> -->
<%-- 																<td style="color: black;">${deductionForExcessLeave} --%>
<!-- 																</td> -->
<!-- 															</tr> -->
														</table>
														<br>
														<input type="hidden"  name="totalDeductionAmount" id="totalDeductionAmount" value="${deductionTotalAmount}"/>
															<table cellspacing="0" cellpadding="0" class="table table-bordered table-striped mb-none" id="grossNetTable">
																<tr>
																	<th>GROSS AMOUNT <input type="hidden" name="grossAmount" id="grossAmount" value="${earningTotalAmount}"/></th>
																	<th colspan="2">NET AMOUNT</th>
																</tr>
																<tr>
																	<td style="color: #000080;">${earningTotalAmount+extraTotalAmount}</td>
																	<td>
																		<input type="hidden" name="netAmount" id="netAmount" value="${earningTotalAmount-(deductionTotalAmount+deductionForExcessLeave)}"/>
																	</td>
																	<td style="color: #000080;">${(earningTotalAmount+extraTotalAmount)-(deductionTotalAmount+deductionForExcessLeave)}</td>
																</tr>
															</table>
						                            	 </div> 
					                            	 </div>  
												</c:if> 							
				                            </div>
				                            <div class="col-md-4 col-md-offset-4">
				                            <hr>    
<!-- 				                            <table class="table table-bordered table-striped mb-none dataTable"> -->
<!-- 				                                <thead> -->
<!-- 				                                    <tr> -->
<!-- 				                                        <td style="background:#eee; text-align:center;"><b>Income Tax</b></td> -->
<!-- 				                                    </tr> -->
<!-- 				                                </thead> -->
<!-- 				                                <tbody> -->
<!-- 				                                    <tr> -->
<!-- 				                                        <td align="center">0.00</td> -->
<!-- 				                                    </tr> -->
<!-- 				                                </tbody> -->
<!-- 				                            </table> -->
												<table class="table table-bordered table-striped mb-none dataTable" id="incomeTaxTable">
													<c:forEach var="incomeTax" items="${staffForViewStaffSalaryDetails.salaryBreakUpList}" varStatus="varIndex">
														<c:if test="${incomeTax.salaryBreakUpName eq 'INCOME TAX'}">
															<tr>
																<td>
																	<c:out value="${incomeTax.salaryBreakUpName}"></c:out>
																	<c:if test="${incomeTax.slab eq true}">
																		<input type="hidden" name="slabTax" id="slabTaxForIncomeTax" class="form-control" value="${incomeTax.salaryBreakUpCode}-${incomeTax.salaryBreakUpName}-${incomeTax.amount}" />
																	</c:if>
																</td>
																<td>
																	<c:out value="${incomeTax.amount}"></c:out>
																	
																</td>
															</tr>
														</c:if>
													</c:forEach>
												</table>
				                            <table class="table table-bordered table-striped mb-none dataTable">
				                                <thead>
				                                    <tr>
				                                        <td style="background:#eee; text-align:center;"><b>Paid Date</b></td>
				                                    </tr>
				                                </thead>
				                                <tbody>
				                                    <tr>
				                                        <td align="center">
															<c:choose>
																<c:when test="${staffForViewStaffSalaryDetails.status eq null}">
																	<input class="form-control" data-plugin-datepicker="" type="text" id="paidDate" name="paymentDate" readonly="readonly" required/>
																</c:when>
																<c:otherwise>
																	${staffForViewStaffSalaryDetails.status}
																</c:otherwise>
															</c:choose>
														</td>
				                                    </tr>
				                                </tbody>
				                            </table>
				                            </div>
				                        </div>                                        
									</div>  
									<input type="hidden" id="hiddenSal" name="hiddenSal" value='<c:out value="${earningTotalAmount+extraTotalAmount}">${earningTotalAmount+extraTotalAmount}</c:out>'/>                                 
									<footer style="display: block;" class="panel-footer">
										<c:if test="${staffForViewStaffSalaryDetails.status eq null}">
											<button class="btn btn-primary">Submit </button>
										</c:if>	
										
									</footer>
								</section>
				              </form:form>
						</div>
					</div>	
		<!-- end: page -->
	</section>
			</c:otherwise>
		</c:choose>
	</c:if>
	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/erp/viewStaffSalarySlip.js"></script>

</body>
</html>