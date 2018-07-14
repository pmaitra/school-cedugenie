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
<title>Student Details Form</title>
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
		<h2>Miscelleneous Tax Type Slab</h2>
	</header>
		<div class = "content-padding">
			<div class="row">
						<div class="col-md-5">
						<c:choose>
						<c:when test="${salBreakUpList == null}">
							<div class="errorbox" id="errorbox" style="visibility:visible;">
								<span id="errormsg">No Salary BreakUp Found to set Slab</span>	
				 			</div>
						</c:when>	
						<c:otherwise>
							<form:form name="pTaxSalarySlab" action="submitMiscTaxSlab.html" method="POST">									
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Miscellaneous Tax Slab</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                        
                                        <div class="form-group">
                                            <label class="control-label">Tax Type</label>                                  
                                            <select name="miscellaneousTaxType" id="miscTaxTypeName" class="form-control" required>
												<option value="">Select...</option>
												<c:forEach var= "sbUp" items="${salBreakUpList}">
													<option value="${sbUp.salaryBreakUpCode}">${sbUp.salaryBreakUpName}</option>					
												</c:forEach>
											</select>
                                        </div>                                        									
                                        <div class="form-group">
                                            <label class="control-label">Tax Type Based On</label>                      
                                            <select name="taxBasedOn" id="taxBasedOn" class="form-control" required>
												<option value="">Select...</option>
												<c:forEach var= "sbUp" items="${nonSlabBreakUpList}">
													<option value="${sbUp.salaryBreakUpCode}">${sbUp.salaryBreakUpName}</option>					
												</c:forEach>
											</select>
                                        </div>                                         
                                        <div class="form-group">
                                            <label class="control-label">Figure Type</label>
                                           <select name="taxFigureType" id="figureType" class="form-control" required>
												<option value="">Select...</option>
												<option value="AMOUNT">AMOUNT</option>					
												<option value="PERCENTAGE">PERCENTAGE</option>
											</select>
                                        </div>                                             
									</div>
									<div id="salarySlab" style="visibility: collapse;">
										<table id="pTaxSalarySlab" class="table table-bordered table-striped mb-none">					
											<tr>
												<th>
													Exceed (Monthly)
												</th>
												<th>
													Not Exceed (Monthly)
												</th>
												<th>
													
												</th>
											</tr>
											<tr>
												<td>
													<input type="text" class="form-control" name = "startSlabIndex" id="startSlabIndex0" value="0.00" onfocus="{this.value='';}" onblur="setZero(this);" style="text-align: right;">
												</td>
												<td>
													<input type="text" class="form-control" name = "endSlabIndex" id="endSlabIndex0" value="0.00" onfocus="{this.value='';}" onblur="setZero(this);" style="text-align: right;">
												</td>
												<td>
													<input type="text" class="form-control" name = "taxAmount" id="taxAmount0" value="0.00" onfocus="{this.value='';}" onblur="setZero(this);" style="text-align: right;">
												</td>
											</tr>	
											<tr>
												<td colspan="7">
													<input type="button" value="Add Rows" class="addbtn" onclick="addrows();">
												</td>
											</tr>
										</table>										
										<input type="hidden" id ="hidVal" name="hidVal" value=""/>
									</div>
									<br>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submitButton">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form:form>
						</c:otherwise>
						</c:choose>						  	
						</div>						
				</div>
		</div>
				
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/erp/designation.editable.js"></script>
<script type="text/javascript" src="/cedugenie/js/erp/pTaxSlab.js"></script>
</body>
</html>