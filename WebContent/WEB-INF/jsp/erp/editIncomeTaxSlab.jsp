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
<title>View Income Tax Salary Slab</title>
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
		<c:when test="${itIncomeTaxSlabsDetails.incomeTaxSlabList == null}">		
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">Income Tax Salary Slab Not Found</span>	
					<input type="button" class="submitbtn" name="submit" value="CREATE NEW" onClick="window.location='/icam/incomeTaxSalarySlab.html?calculationFor=<c:out value="${calCulationFor}"/>&incomeAge=<c:out value="${strIncomeAge}"/>' " />
				</div>		
		</c:when>
		<c:otherwise>
			<div class="row">
				<div class="col-md-12">
					<form:form method="POST" action="editIncomeTaxSalarySlabDetails.html">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>
	
								<h2 class="panel-title">INCOME TAX SALARY SLAB FOR ${itIncomeTaxSlabsDetails.financialYear}</h2>										
							</header>
							<div style="display: block;" class="panel-body">
                                    <div class="row">
                                         <div class="col-md-3">
											<div class="form-group">
                                                <label class="control-label">Income Age</label>
                                           		${itIncomeTaxSlabsDetails.incomeAge.incomeAgeName}
												<input type="hidden" id="incomeAge" name="incomeAge" value="${itIncomeTaxSlabsDetails.incomeAge.incomeAgeCode}"/>
                                            </div>                                             
                                            <div class="form-group">
                                                <label class="control-label">Income Tax Will base On</label>
                                               	<select name="salaryBreakUp" id="salaryBreakUp" class="form-control" disabled="disabled">
												<c:forEach var="salaryBreakUp" items="${salaryBreakUpListForCreateUpdate}">
													<c:if test="${salaryBreakUp.salaryBreakUpName eq itIncomeTaxSlabsDetails.incomeTaxbasedOn}">
														<option value="<c:out value="${salaryBreakUp.salaryBreakUpCode}"/>"><c:out value="${salaryBreakUp.salaryBreakUpName}"/></option>
													</c:if>
												</c:forEach>
												 <c:forEach var="salaryBreakUp" items="${salaryBreakUpListForCreateUpdate}">
													<c:if test="${salaryBreakUp.salaryBreakUpName ne itIncomeTaxSlabsDetails.incomeTaxbasedOn}">
														<option value="<c:out value="${salaryBreakUp.salaryBreakUpCode}"/>"><c:out value="${salaryBreakUp.salaryBreakUpName}"/></option>
													</c:if>
												</c:forEach>
											</select>                                                 
                                            </div>      
                                        </div>  
									</div>
									<div class="row">
										<hr>
										<div class="col-md-12">
												<table class="table table-bordered table-striped mb-none" id="amountTable">
													<thead>
														<tr>							
															<c:forEach var= "itParameter" items="${itIncomeTaxSlabsDetails.incomeTaxParameters}">
																<th style="text-align: center">
																	${itParameter.incomeTaxParamName}
																	<input type="hidden" name = "itParameter" id = "itParameterHeaderListName" value = "${itParameter.incomeTaxParamCode}">
																</th>								
															</c:forEach>
															<td></td>
														</tr>
													</thead>
													<tbody>
														<tr>
					                                       	<c:forEach var="itParametersaa" items="${itIncomeTaxSlabsDetails.incomeTaxSlabList[0].incomeTaxParameterList}">						
																<c:if test="${itParametersaa.figureType eq 'AMOUNT'}">
																	<td>
																		<select name="${itParametersaa.incomeTaxParamCode}" id="${itParametersaa.incomeTaxParamCode}" class="form-control" disabled="disabled">
																			<option value="AMOUNT">AMOUNT</option>
																			<option value="PERCENTAGE">PERCENTAGE</option>
																		</select>
																	</td>
																</c:if>
																<c:if test="${itParametersaa.figureType eq 'PERCENTAGE'}">
																	<td>
																		<select name="${itParametersaa.incomeTaxParamCode}" id="${itParametersaa.incomeTaxParamCode}" class="form-control" disabled="disabled">									
																			<option value="PERCENTAGE">PERCENTAGE</option>
																			<option value="AMOUNT">AMOUNT</option>
																		</select>
																	</td>				
																</c:if>
															</c:forEach>
														</tr>
														<c:forEach var= "itSlab" items="${itIncomeTaxSlabsDetails.incomeTaxSlabList}">
															<tr>							
																<c:forEach var= "itParameter" items="${itSlab.incomeTaxParameterList}">
																	<td>
																		<input class="form-control" type="text" style="text-align: right" name="${itParameter.incomeTaxParamCode}" value="${itParameter.figure}" readonly="readonly" />
																	</td>
																</c:forEach>
																<td><input type="image" src="/icam/images/minus_icon.png" name="deleteButton" onclick="return deleteRow(this);" disabled="disabled"></td>
															</tr>							
														</c:forEach>
														<tr>
															<td colspan="7">
															<input type="hidden" name = "itParameterSize" id = "itParameterSize" value = "${itIncomeTaxSlabsDetails.incomeTaxParameters.size()}">
															<input type="button" id="addNewRows" class="addbtn" onclick="addrows();" disabled="disabled" >							
															</td>
														</tr>	
													</tbody>
												</table>
											</div> 
										</div>									
									</div>						
								<footer style="display: block;" class="panel-footer">		
									<input type="button" class="btn btn-primary" name="submit" value="BACK" onClick="window.location='/icam/editIncomeTaxSalarySlab.html?calculationFor=<c:out value="${calCulationFor}"/>' " />
									<input type="button" class="btn btn-warning" value="EDIT" id="editButton" onclick="makepageEditbale();"/>
									<input type="submit" class="btn btn-error" value="SUBMIT" id="submitButton" disabled="disabled"/>
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
<script type="text/javascript" src="/icam/js/erp/editIncomeTaxSalarySlab.js"></script>
<script type="text/javascript" src="/icam/js/erp/commonIncomeTaxSalarySlab.js"></script>
</body>
</html>