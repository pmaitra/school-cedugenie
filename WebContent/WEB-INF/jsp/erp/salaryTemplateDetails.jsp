<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Template List</title>
<link rel="stylesheet" href="/cedugenie/css/erp/salaryTemplate.css" />
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
		<h2>Edit Custom Salary Template</h2>
</header>
	<div class="content-padding">
		<div class="row">		
					<%-- <c:choose>
						<c:when test="${salaryBreakUpList == null}">
							<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
							<span id="infomsg"> Salary Breakup Found</span>
						</div>	
						</c:when>
					<c:otherwise> --%>
					<form:form method="POST" action="updateSalaryTemplate.html">
					 <div class="row">
						<div class="col-md-4">						  
							<section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
									</div>	
									<h2 class="panel-title">View/Edit Custom Salary Template </h2>										
								</header>
								<div style="display: block;" class="panel-body">                       
	                                <div class="form-group">
	                                    <label class="control-label col-md-5">Designation</label>
	                                    <label class="control-label col-md-7">${salaryTemplate.designation}</label>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-md-5">Level</label>
	                                    <label class="control-label col-md-7">${salaryTemplate.designationLevel}</label>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label col-md-5">Template Name</label>
	                                    <div class="col-md-7">
	                                        <input type="hidden" id="salaryTemplateCode" name="salaryTemplateCode" value="${salaryTemplate.salaryTemplateCode}"/>
											<input type="text" class="form-control" id="salaryTemplateName" name="salaryTemplateName" value="${salaryTemplate.salaryTemplateName}" readonly = "readonly"/>
	                                    </div>
	                                </div>  
								</div>
							</section>
						</div>
						<div class="col-md-8">	
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Note: Formula Once Set Cannot Be Changed</h2>
                                </header>
                                <div class="panel-body">

                                    <table class="table table-bordered table-striped mb-none" id="formulaSetUpTable">
                                        <thead>
                                            <tr>
                                                <th colspan="4" style="background:#eee; text-align:center;">EARNING</th>
                                            </tr>
                                            <tr>
                                                <th>Salary Head</th>
                                                <th>Formula</th>
                                                <th>Action</th>
                                                <th>Amount (Sample)</th>
                                            </tr>
                                            <c:forEach var="salaryBreakUpType" items="${salaryTemplate.salaryTemplateDetailsList}" varStatus="varEarning">								 
												<c:if test="${salaryBreakUpType.salaryBreakUpType eq 'EARNING'}">
														<tr>
															<td>
																<input class="form-control" name="salaryBreakUpName" value="${salaryBreakUpType.salaryBreakUpName}" readonly>
															</td>
															<td>
																<c:if test="${salaryBreakUpType.salaryBreakUpName ne 'BASIC'}">
																	<c:if test="${salaryBreakUpType.salaryHeadFormula ne null}">
																		<input class="salaryBreakUpFormulaAnother form-control" name="${salaryBreakUpType.salaryBreakUpName}" id="eafa${varEarning.index}" value="${salaryBreakUpType.salaryHeadFormula}" readonly >
																	</c:if>
																	<c:if test="${salaryBreakUpType.salaryHeadFormula eq null}">
																		<input class="salaryBreakUpFormula form-control" name="${salaryBreakUpType.salaryBreakUpName}" id="eaf${varEarning.index}" value="${salaryBreakUpType.salaryHeadFormula}" readonly >
																	</c:if>
																</c:if>
															</td>
															<td>
																<c:if test="${salaryBreakUpType.salaryBreakUpName ne 'BASIC'}">
																	<input type="button" class="calbtn form-control" id="calButtons${varEarning.index}" name="calButtonForFormula" value="Calculate" />
																</c:if>
															</td>
															<td id="af${varEarning.index}">
																<c:if test="${salaryBreakUpType.salaryBreakUpName eq 'BASIC'}">
																	10000
																</c:if>
															</td>															
														</tr>
												</c:if>
											</c:forEach>
                                        </thead>                                       
                                        <thead>
                                            <tr>
                                                <th colspan="4" style="background:#eee; text-align:center;">DEDUCTION</th>
                                            </tr>
                                            <tr>
                                                <th>Salary Head</th>
                                                <th>Formula</th>
                                                <th>Action</th>
                                                <th>Amount (Sample)</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <c:forEach var="salaryBreakUpType" items="${salaryTemplate.salaryTemplateDetailsList}" varStatus="varEarning">								 
											<c:if test="${salaryBreakUpType.salaryBreakUpType eq 'DEDUCTION'}">
													<tr>
														<td>
															<input class="form-control" name="salaryBreakUpName" value="${salaryBreakUpType.salaryBreakUpName}" readonly >
														</td>
														<td>
														<c:if test="${salaryBreakUpType.slab eq false}">
															<c:if test="${salaryBreakUpType.salaryHeadFormula ne null}">
																<input class="salaryBreakUpFormulaAnother form-control" name="${salaryBreakUpType.salaryBreakUpName}" id="eada${varEarning.index}" value="${salaryBreakUpType.salaryHeadFormula}" readonly>
															</c:if>
															<c:if test="${salaryBreakUpType.salaryHeadFormula eq null}">
																<input class="salaryBreakUpFormula form-control" name="${salaryBreakUpType.salaryBreakUpName}" id="ead${varEarning.index}" value="${salaryBreakUpType.salaryHeadFormula}" readonly>
															</c:if>
														</c:if>
														</td>
														<td>
															<c:if test="${salaryBreakUpType.slab eq false}">
																<input type="button" class="calbtn form-control" id="calButtons${varEarning.index}" name="calButtonForFormula" value="Calculate" />
															</c:if>
														</td>
														<td id="ad${varEarning.index}">
														</td>
													</tr>
											</c:if>								
										</c:forEach>                                           
                                        </tbody>
                                         <thead>
                                            <tr>
                                                <th colspan="4" style="background:#eee; text-align:center;">MANUAL</th>
                                            </tr>
                                            <tr>
                                                <th>Salary Head</th>
                                                <th>Formula</th>
                                                <th>Action</th>
                                                <th>Amount (Sample)</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <c:forEach var="salaryBreakUpType" items="${salaryTemplate.salaryTemplateDetailsList}" varStatus="varEarning">								 
											<c:if test="${salaryBreakUpType.salaryBreakUpType eq 'MANUAL'}">
													<tr>
														<td>
															<input class="form-control" name="salaryBreakUpName" value="${salaryBreakUpType.salaryBreakUpName}" readonly >
														</td>
														<td>
														
														</td>
														<td>
															
														</td>
														<td>
														</td>
													</tr>
											</c:if>								
										</c:forEach>                                           
                                        </tbody>
                                    </table>
                                </div>                                
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
                            </section>
						</div>
					</div>	
					</form:form>
				<%-- 	</c:otherwise>
					</c:choose> --%>
									

</div>
<div class="box" id="calculatorDiv">
	<!-- <div class="display"><input type="text" readonly="readonly" id="displayFormula" name="displayFormula" class="textfield2"></div> -->
	<div class="keys">
	    <p><input type="button" class="button gray" 
	    value="(" onclick='v("(")'>
		
	    <input type="button" class="button gray" 
	    value=")" onclick='v(")")'>
		
	    <input type="button" class="button black" value="Clear" 
	    onclick='c("")'>
		
	    <input type="button" class="button black" value="C" 
	    onclick='return doBackSpace();'>
		
	    <p><input type="button" class="button black" 
	    value="7" onclick='v("7")'><input type="button" 
	    class="button black" value="8" onclick='v("8")'>
	    <input type="button" class="button black" value="9" 
	    onclick='v("9")'><input type="button" 
	    class="button pink" value=" * " onclick='v(" * ")'></p>
		
	    <p><input type="button" class="button black" 
	    value="4" onclick='v("4")'><input type="button" 
	    class="button black" value="5" onclick='v("5")'>
	    <input type="button" class="button black" value="6" 
	    onclick='v("6")'><input type="button" 
	    class="button pink" value=" - " onclick='v(" - ")'></p>
		
	    <p><input type="button" class="button black" 
	    value="1" onclick='v("1")'><input type="button" 
	    class="button black" value="2" onclick='v("2")'>
	    <input type="button" class="button black" value="3" 
	    onclick='v("3")'><input type="button" 
	    class="button pink" value=" + " onclick='v(" + ")'></p>
		
	    <p><input type="button" class="button black" 
	    value="0" onclick='v("0")'><input type="button" 
	    class="button black" value="." onclick='v(".")'>
	    <input type="button" class="button pink"
	     value=" % " onclick='v(" % ")'><input type="button" 
	    class="button pink" value=" / " onclick='v(" / ")'></p>
	    
	    <p>
	    <c:forEach var="salaryBreakUpType" items="${salaryTemplate.salaryTemplateDetailsList}" varStatus="varEarning">								 
			<input type="button" class="button gray" 
	    	value="${salaryBreakUpType.salaryBreakUpName}" onclick='v("${salaryBreakUpType.salaryBreakUpName}")'>
		</c:forEach>
	    </p>
	  <!--   <input type="button" class="button black" id="calButton" name="calButton" value="=" /> -->
	    <p>
	    	<input type="button" class="clearbtn" id="closeButton" name="closeButton" value="Close" />
	    </p>
	</div>
</div>
</div>
	<input type="hidden" id="hidVal" name="hidVal" >
	<input type="hidden" id="hidFormula" name="hidFormula" >
	<input type="hidden" id="hidNextCell" name="hidNextCell" >

<script src="assets/javascripts/ui-elements/examples.modals.js"></script>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>

<script type="text/javascript" src="/cedugenie/js/erp/editSalaryTemplate.js"></script>
</body>
</html>