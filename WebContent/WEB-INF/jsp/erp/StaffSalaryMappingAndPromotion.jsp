<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
	
<head>
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
	<title>Standard Subject Mapping</title>	
	<style type="text/css">
	       .scroll-to-top{
	           display: none !important;
	       }.mb-md{
	       	   display: none;
	       }
	</style>	
</head>
<body>					
	<!-- start: page -->
<header class="page-header">
			<h2>Staff Salary Mapping</h2>
</header>
<div class="content-padding">
	<c:if test="${submitResponse eq 'success'}">
			<div class="alert alert-success" >
				<strong>SuccessFully Mapped</strong>	
			</div>
		</c:if>
	<c:if test="${submitResponse eq 'fail'}">
			<div class="alert alert-success" >
				<strong>Failed To Map</strong>	
			</div>
	</c:if>
	 <div class="row">
		<div class="col-md-4">
		  	<c:set var="earningTotalAmount" value="0" scope="page" />
			<c:set var="deductionTotalAmount" value="0" scope="page" />
			<c:set var="earningTotalAmountExist" value="0" scope="page" />
			<c:set var="deductionTotalAmountExist" value="0" scope="page" />
			<form:form id="staffSalaryMappingAndPromotionStart" name="staffSalaryMappingAndPromotionStart" method="POST" action="submitStaffSalaryMappingAndPromotion.html">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>

						<h2 class="panel-title">Staff Salary Mapping </h2>										
					</header>
					<div style="display: block;" class="panel-body">
                                    
						<div class="form-group">
							<label class="control-label">User ID</label>
							<input type="text" class="form-control" name="userId" id="userId" placeholder="">
						</div> 
                                        
					</div>
					<footer style="display: block;" class="panel-footer">		
						<input type="submit" id="submitBttnForView" name="submitBttn" class="btn btn-sm btn-warning" value="For View" onclick="return userIdValidation();" />
						<input type="submit" id="submitBttnForUpdateFirst" name="submitBttn" class="btn btn-sm btn-danger" value="For Increment" onclick="return userIdValidation();" />
						<input type="submit" id="submitBttnForPromote" name="submitBttn" class="btn btn-sm btn-primary" value="For Promote" onclick="return userIdValidation();" />
					</footer>
				</section>
        	</form:form>
		</div>
		<form:form id="staffSalaryMappingAndPromotion" name="staffSalaryMappingAndPromotion" method="POST" action="updateAndInsertSalryAmount.html">
		<div class="col-md-8">		 
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<div class="alert alert-warning" id="javascriptmsg" style="display: none">
						  <span></span>	
						</div>
						<div id="userIdShow" style="visibility: collapse;">
							<h2 class="panel-title">Selected User ID : <input type="hidden" id="user" name="resource.userId" value="${resource.userId}">
							${resource.userId} ${link}
							</h2>
						</div>										
					</header>
					<div style="display: block;" class="panel-body" >
	                    <div class="row" id="userSalaryUpdateInfo" style="visibility: collapse;">
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label class="control-label">Designation Type</label>
	                                <select id="designationType" name="resource.designationType" class="form-control" disabled="disabled">	
										<option VALUE="">PLEASE SELECT</option>					
										<c:forEach var="designationType" items="${staffDetailsFromDB.designationTypeList}" >
											<option VALUE="${designationType.designationTypeCode}" ${designationType.designationTypeName eq staffDetailsFromDB.resource.designationType?'selected':''}>${designationType.designationTypeName}</option>	
										</c:forEach>
									</select> 
	                            </div>
	                            
	                            <div class="form-group">
	                                <label class="control-label">Designation</label>
		                                <select class="form-control" name="resource.designation" id="designation" disabled="disabled">	
										<option VALUE="">PLEASE SELECT</option>	
										<c:forEach var="designationType" items="${staffDetailsFromDB.designationTypeList}" >
											<c:choose>
												<c:when test="${designationType.designationTypeName eq staffDetailsFromDB.resource.designationType}">
													<c:forEach var="designation" items="${designationType.designationList}" >
														<option VALUE="${designation.designationCode}" ${designation.designationName eq staffDetailsFromDB.resource.designation?'selected':''}>${designation.designationName}</option>	
													</c:forEach>
												</c:when>
												<c:otherwise>
													<c:forEach var="designation" items="${designationType.designationList}" >
														<option VALUE="${designation.designationCode}">${designation.designationName}</option>	
													</c:forEach>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
	                            </div> 
	                        </div>
	                        <div class="col-md-6">  
	                            <div class="form-group">
	                            	<label class="control-label">Designation Level</label>
	                             	<select id="level" name="resource.level" class="form-control" disabled="disabled">
	                             	<option VALUE="">Select</option>	
									<c:forEach var="designationType" items="${staffDetailsFromDB.designationTypeList}" >
										<c:if test="${designationType.designationTypeName eq staffDetailsFromDB.resource.designationType}">
											<c:forEach var="designation" items="${designationType.designationList}" >
												<c:if test="${designation.designationName eq staffDetailsFromDB.resource.designation}">
													<c:forEach var="level" items="${designation.levelList}" >
														<option VALUE="${level.designationLevelCode}" ${level.designationLevelName eq staffDetailsFromDB.resource.designationLevel?'selected':''}>${level.designationLevelName}</option>	
													</c:forEach>
												</c:if>
											</c:forEach>
										</c:if>
									</c:forEach>	
									
								</select> 
	                            </div>
	                            <div class="form-group">
	                                <label class="control-label">Template Name</label>
	                                <select class="form-control" id="salaryTemplateName" name="resource.salaryTemplateCode" disabled="disabled">
										<option value="">Select...</option>	
										<c:if test="${staffDetailsFromDB.salaryTemplate.salaryTemplateCode ne null}">
											<option VALUE="${staffDetailsFromDB.salaryTemplate.salaryTemplateCode}" ${staffDetailsFromDB.salaryTemplate.salaryTemplateCode eq staffDetailsFromDB.resource.salaryTemplateCode?'selected':''}>${staffDetailsFromDB.salaryTemplate.salaryTemplateName}</option>
										</c:if>
									</select>   
	                            </div> 
	                        </div>                                            
	                    </div>
	                    
	                    <div class="row">                                            
                        	<div class="col-md-12">
	                            <hr> 
	                            <c:if test="${staffDetailsFromDB.salaryBreakUpList ne null}">
	                    			<div id="userSalaryBreakUpInfo" style="visibility: collapse;">	                    
		                    			<div class="form-group col-sm-6">
		                             		<table class="table table-bordered table-striped mb-none" >
				                                 <thead>
				                                     <tr>
				                                         <th>EARNING</th>
														<th>AMOUNT(Rs.)</th>
				                                     </tr>
				                                 </thead>
				                                 <tbody>
				                                 	<c:forEach var="salaryBreakUpType" items="${staffDetailsFromDB.salaryBreakUpList}" varStatus="varIndex">
														<c:if test="${salaryBreakUpType.salaryBreakUpType eq 'EARNING'}">
															<tr>
																<td>${salaryBreakUpType.salaryBreakUpName}</td>
																<td>${salaryBreakUpType.amount}</td>
																<c:set var="earningTotalAmountExist" value="${earningTotalAmountExist + salaryBreakUpType.amount}" scope="page"/>						
															</tr>
														</c:if>
													</c:forEach>
													<tr>
														<td style="color: black;">Gross Amount</td>
														<td style="color: black;">${earningTotalAmountExist}</td>								
													</tr>
				                                 </tbody>
		                             	</table>
		                         	</div>
	                             	<div class="form-group col-sm-6">
	                                 	<table class="table table-bordered table-striped mb-none">
	                                     	<thead>
		                                    	<tr>
		                                            <th>DEDUCTION</th>
													<th>Amount(Rs.)</th>
		                                        </tr>
	                                     </thead>
	                                     <tbody>
	                                         <c:forEach var="salaryBreakUpType" items="${staffDetailsFromDB.salaryBreakUpList}">
												<c:if test="${salaryBreakUpType.salaryBreakUpType eq 'DEDUCTION'}">
													<c:if test="${salaryBreakUpType.slab eq false}">
														<tr>
															<td>${salaryBreakUpType.salaryBreakUpName}</td>
															<td>${salaryBreakUpType.amount}</td>
															<c:set var="deductionTotalAmountExist" value="${deductionTotalAmountExist + salaryBreakUpType.amount}" scope="page"/>
														</tr>
													</c:if>
												</c:if>
											</c:forEach>
											<tr>
												<td style="color: black;">Total Deduction</td>
												<td style="color: black;">${deductionTotalAmountExist}</td>
											</tr>
	                                     </tbody>
	                                 	</table>
	                                 	<table cellspacing="0" cellpadding="0" class="midsec1">
											<tr>
												<th>NET AMOUNT</th>
											</tr>
											<tr>
												<td>${earningTotalAmountExist-deductionTotalAmountExist}</td>
											</tr>
										</table>
	                            	 </div> 
                            	 </div>  
							</c:if> 							
	                	 </div>                        		                                         
					</div>
            		</div>
						
				</section>
			</div>
			
			
			<div class="row">
                        <div class="col-md-8 col-md-offset-2">
								
								<c:if test="${link eq 'For View'}">
									<c:if test="${staffDetailsFromDB.salaryTemplate.salaryTemplateCode eq null}">
										<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
											<span id="infomsg">There Is No Salary Template For This Designation And Level.</span>
										</div>	
									</c:if>
									<c:if test="${staffDetailsFromDB.salaryTemplate.salaryTemplateCode ne null}">
										<c:if test="${staffDetailsFromDB.salaryBreakUpList eq null}">
											<div class="alert alert-warning" id="infomsgbox" style="visibility:visible;">
												<span>Salary has not been mapped yet</span>
											</div>						
										</c:if>
									</c:if>
								</c:if>		
								
								
								<c:if test="${link eq 'For Increment'}">
									<c:if test="${staffDetailsFromDB.salaryTemplate.salaryTemplateCode eq null}">
										<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
											<span id="infomsg">There Is No Salary Template For This Designation And Level.</span>
										</div>	
									</c:if>
								
								<c:if test="${staffDetailsFromDB.salaryTemplate.salaryTemplateCode ne null}"> 
									<%-- <c:if test="${staffDetailsFromDB.salaryTemplate.status eq 'MAPPED'}"> --%>
										<%-- <c:if test="${staffDetailsFromDB.salaryBreakUpList eq null}"> --%>
										<c:if test="${staffDetailsFromDB.resource.salaryTemplateCode eq null}">
											<div class="infomsgbox" id="infomsgbox1" style="visibility:visible;">
												<span id="infomsg1">Please Select Template To map The Salary.</span>
											</div>
										</c:if>
										<c:if test="${staffDetailsFromDB.salaryBreakUpListForShow ne null}">
										<table  cellspacing="0" cellpadding="0" class="table table-bordered table-striped mb-none dataTable" id="salaryStructureForFirstTime" >
											<!-- <tr> -->
												<tr>
													<th colspan="2">NEW SALARY BREAK-UP </th>
												</tr>
												<tr>
													<td style=" vertical-align: top;">
														<table cellspacing="0" cellpadding="0" class="table table-bordered table-striped mb-none dataTable" id="salaryStructureFotr">
															<tr>
																<th>EARNING</th>
																<th>AMOUNT(Rs.)</th>
																<th>FORMULA(Given)</th>
															</tr>
															<c:forEach var="salaryBreakUpListForFirstTime" items="${staffDetailsFromDB.salaryBreakUpListForShow}" varStatus="varIndex">
																<c:if test="${salaryBreakUpListForFirstTime.salaryBreakUpType eq 'EARNING'}">
																	<tr ${salaryBreakUpListForFirstTime.salaryBreakUpType eq 'EARNING'}>
																		<td>${salaryBreakUpListForFirstTime.salaryBreakUpName}<input type="hidden" id="salBreakUpHead${varIndex.index}" name="salBreakUpHead" value="${salaryBreakUpListForFirstTime.salaryBreakUpName}"/></td>
																		<td>
																			<c:if test="${salaryBreakUpListForFirstTime.salaryBreakUpDesc eq null}">
																				<input type="text" name="amount" id="ea${varIndex.index}" value="0" class="form-control" onblur="calculateAmount('${salaryBreakUpListForFirstTime.salaryBreakUpName}',this);"/>
																			</c:if>
																			<c:if test="${salaryBreakUpListForFirstTime.salaryBreakUpDesc ne null}">
																				<input type="text" name="amount" id="ea${varIndex.index}" value="0" class="form-control" readonly="readonly" onblur="calculateAmount('${salaryBreakUpListForFirstTime.salaryBreakUpName}',this);"/>
																			</c:if>
																		</td>
																		<td>${salaryBreakUpListForFirstTime.salaryBreakUpDesc}</td>
																	</tr>
																</c:if>
															</c:forEach>			
															<tr>
																<td style="color: black;">Gross Amount</td>
																<td style="color: black;"><input type="text" name="grossAmount" id="grossAmount" value="0" class="form-control" readonly="readonly"/></td>								
																<td></td>
															</tr>
														</table>			
													</td>
													<td style=" vertical-align: top;">
														<table cellspacing="0" cellpadding="0" class="table table-bordered table-striped mb-none dataTable" id="salaryStructureForded">
															<tr>
																<th>DEDUCTION</th>
																<th>AMOUNT(Rs.)</th>
																<th>FORMULA(Given)</th>
															</tr>
															<c:forEach var="salaryBreakUpListForFirstTime" items="${staffDetailsFromDB.salaryBreakUpListForShow}" varStatus="varIndex">
																<c:if test="${salaryBreakUpListForFirstTime.salaryBreakUpType eq 'DEDUCTION'}">
																	<c:if test="${salaryBreakUpListForFirstTime.slab eq false}">
																		<tr>
																			<td>${salaryBreakUpListForFirstTime.salaryBreakUpName}<input type="hidden" id="salBreakUpHead${varIndex.index}" name="salBreakUpHead" value="${salaryBreakUpListForFirstTime.salaryBreakUpName}"/></td>
																			<td>
																				<c:if test="${salaryBreakUpListForFirstTime.salaryBreakUpDesc eq null}">
																					<input type="text" name="amount" id="de${varIndex.index}" value="0" class="form-control" onblur="calculateAmount('${salaryBreakUpListForFirstTime.salaryBreakUpName}',this);"/>
																				</c:if>
																				<c:if test="${salaryBreakUpListForFirstTime.salaryBreakUpDesc ne null}">
																					<input type="text" name="amount" id="de${varIndex.index}" value="0" class="form-control" readonly="readonly" onblur="calculateAmount('${salaryBreakUpListForFirstTime.salaryBreakUpName}',this);"/>
																				</c:if>
																			</td>
																			<td>${salaryBreakUpListForFirstTime.salaryBreakUpDesc}</td>
																		</tr>
																	</c:if>
																</c:if>
															</c:forEach>					
															<tr>
																<td style="color: black;">Total Deduction</td>
																<td style="color: black;"><input type="text" name="totalDeductionAmount" id="totalDeductionAmount" value="0" class="form-control" readonly="readonly"/></td>
																<td></td>
															</tr>
														</table>
														<table cellspacing="0" cellpadding="0" class="table table-bordered table-striped mb-none">
															<tr>
																<th>NET AMOUNT</th>
															</tr>
															<tr>
																<td><input type="text" name="netAmount" id="netAmount" value="0" class="form-control" readonly="readonly"/></td>
															</tr>
														</table>
													</td>	
												</tr>
											</table>
										</c:if>
											
										<input type="reset" id="clrbtn" class="btn btn-sm btn-default" value="Clear" />
										<input type="submit" id="submitBttnForUpdate" name="submitBttnForUpdateAndPromote" class="btn btn-sm btn-primary" value="Update" onclick="return updateSalaryBreakUp();"/>
									
								</c:if>
							</c:if>
							<c:if test="${link eq 'For Promote'}">
								<c:if test="${staffDetailsFromDB.salaryTemplate.salaryTemplateCode eq null}">
									<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
										<span id="infomsg">There Is No Salary Template For This Designation And Level.</span>
									</div>	
								</c:if>
								<c:if test="${staffDetailsFromDB.salaryTemplate.salaryTemplateCode ne null}">
									<%-- <c:if test="${staffDetailsFromDB.salaryTemplate.status eq 'MAPPED'}"> --%>
										<c:if test="${staffDetailsFromDB.salaryBreakUpList eq null}">
											<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
												<span id="infomsg">Salary has not been mapped yet.</span>
											</div>					
										</c:if>
										<c:if test="${staffDetailsFromDB.salaryBreakUpList ne null}">
											<table  cellspacing="0" cellpadding="0" class="table table-bordered table-striped mb-none dataTable" id="salaryStructureForPromote" style="visibility: collapse;">
											
											</table>
											</c:if>
											<footer style="display: block;" class="panel-footer">
												<input type="reset" id="clrbtn" class="btn btn-sm btn-default" value="Clear" />
												<input type="submit" id="submitBttnForPromote" name="submitBttnForUpdateAndPromote" class="btn btn-sm btn-primary" value="Promote"  onclick="return updateSalaryBreakUp();"/>
											</footer>
									
								</c:if>
							</c:if>											
						                   
					</div>
             	</div>
             		<input type="hidden" id="designationTypeHid" name="designationTypeHid" value=""/>	
					<input type="hidden" id="designationHid" name="designationHid" value=""/>	
					<input type="hidden" id="levelHid" name="levelHid" value=""/>	
					<input type="hidden" id="templateHid" name="templateHid" value=""/>	
             </form:form>
		</div>
    </div>            
<!-- end: page -->
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/include.jsp" %>	
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/erp/StaffSalaryMappingAndPromotion.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		 var linkStr = "${link}";	
		 if(linkStr == "For View"){
			 document.getElementById("userSalaryUpdateInfo").style.visibility='visible';
			 document.getElementById("userIdShow").style.visibility='visible';
			 document.getElementById("userSalaryBreakUpInfo").style.visibility='visible';
		 }
		 
		if(linkStr == "For Increment"){
			document.getElementById("userSalaryUpdateInfo").style.visibility='visible';	
			//document.getElementById("salaryTemplateName").removeAttribute("disabled");
			document.getElementById("userIdShow").style.visibility='visible';
			document.getElementById("userSalaryBreakUpInfo").style.visibility='visible';
			document.getElementById("salaryStructureForUpdate").style.visibility='visible';
		}
			 
		if(linkStr == "For Promote"){
			document.getElementById("userSalaryUpdateInfo").style.visibility='visible';
			document.getElementById("userIdShow").style.visibility='visible';
			document.getElementById("designationType").removeAttribute("disabled");
			document.getElementById("designation").removeAttribute("disabled");
			document.getElementById("level").removeAttribute("disabled");
			document.getElementById("salaryTemplateName").removeAttribute("disabled");
			document.getElementById("userSalaryBreakUpInfo").style.visibility='visible';
			document.getElementById("salaryStructureForPromote").style.visibility='visible';
		}
	});
	</script>
</body>


</html>