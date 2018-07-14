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
<title>Income Tax Salary Slab</title>
<%@ include file="/include/include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<c:choose>
		<c:when test="${itParameterList == null}">		
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">IT Parameters Not Found</span>	
				</div>		
		</c:when>
		<c:otherwise>
			<div class="row">
				<div class="col-md-12">
					<form:form name="incomeTaxSalarySlab" id="incomeTaxSalarySlab" action="submitIncomeTaxSalarySlab.html" method="POST">							
					<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>
	
								<h2 class="panel-title">Slab And Rates for F Y : ${financialYear.financialYearCode}</h2>										
							</header>
							<div style="display: block;" class="panel-body">
                                    <div class="row">
                                         <div class="col-md-3">
											<div class="form-group">
                                                <label class="control-label">INCOME OF</label>
                                                <input type="text" name="incomeAge" id="incomeAge" value="${strIncomeAge}" readonly="readonly" class="form-control">
                                            </div>   
                                            <c:choose>	
												<c:when test="${slabCalculationParameter == null}">
													    <div class="form-group">
					                                        <label class="control-label">Income Tax Will base On <span class="required" aria-required="true">*</span></label>
															 <select name="salaryBreakUp" id="salaryBreakUp" class="form-control">
																<option value="">Select</option>
																<c:forEach var="salaryBreakUp" items="${salaryBreakUpListForCreateUpdate}">
																	<option value="${salaryBreakUp.salaryBreakUpCode}">${salaryBreakUp.salaryBreakUpName}</option>
																</c:forEach>
															</select>					                                    
					                                    </div> 	
												</c:when>				
											<c:otherwise>
												<div class="form-group">
			                                        <label class="control-label">Income Tax Will base On <span class="required" aria-required="true">*</span></label>
			                                        <input type="text" name="salaryBreakUp" value="${slabCalculationParameter.incomeTaxbasedOn}" class="form-control" readonly="readonly">
			                                    </div> 
											</c:otherwise>
											</c:choose> 
                                         </div>                                                       
									</div>
									<div class="row">
										<hr>
										<div class="col-md-12">
												<table class="table table-bordered table-striped mb-none" id="incomeTaxSalarySlabTable">
													<thead>
														<tr>							
															<c:forEach var= "itParameter" items="${itParameterList}">
																<th>
																	${itParameter.incomeTaxParamName}
																	<input type="hidden" name = "itParameter" id = "itParameter" value = "${itParameter.incomeTaxParamCode}">
																</th>
															</c:forEach>						
															<th></th>
														</tr>
													</thead>
													<tbody>
														<tr>
					                                    <c:forEach var= "itParameter" items="${itParameterList}">
															<td>
																<select name="${itParameter.incomeTaxParamCode}" id="${itParameter.incomeTaxParamCode}" class="form-control">
																	<option value="">Select</option>								
																	<option value="AMOUNT">AMOUNT</option>
																	<option value="PERCENTAGE">PERCENTAGE</option>
																</select>
															</td>
														</c:forEach>						
														<th></th>
														</tr>													
														<tr>
															<td colspan="7">
																<input type="hidden" name = "itParameterSize" id = "itParameterSize" value = "${itParameterList.size()}">
																<input type="button" class="addbtn" value="ADD" onclick="addrows();">
															</td>
														</tr>
													</tbody>
												</table>
											</div> 
										</div>									
									</div>						
								<footer style="display: block;" class="panel-footer">
									<input type="submit" class="btn btn-primary" value="SUBMIT" id="submitButton" onclick="return validateITSlabSubmitForm();"/>
									<input type="reset" class="btn btn-default" value="clear" />	
								</footer>
								<input type="hidden" id="hidVal" name="hidVal" value='<c:out value="${calCulationFor}"/>'/>	
						</section>
					</form:form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>			
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/erp/incomeTaxSalarySlab.js"></script>
<script type="text/javascript" src="/icam/js/erp/commonIncomeTaxSalarySlab.js"></script>
</body>
</html>