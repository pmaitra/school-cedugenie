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

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<c:choose>
	<c:when test="${miscTaxList == null}">
		<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
			<span id="infomsg">Slab Details Not Found</span>
		</div>
	</c:when>
<c:otherwise>		
		<form:form name="editMiscTaxSlab" action="editMiscTaxSlab.html" method="POST">	
			<section role="main" class="content-body">		
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>						
								<h2 class="panel-title">Template List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<tr>
										<th colspan="4">
											Slab Details for ${taxTypeName}
											<input type="hidden" name="miscellaneousTaxType" id="taxTypeCode" value="${taxTypeCode}">
											
										</th>
									</tr>
									<tr>
										<th>
											Tax Based On
										</th>
										<th>
											<select name="taxBasedOn" id="taxBasedOn" class="form-group" disabled >
												<option value="">Select...</option>
												<c:forEach var= "sbUp" items="${nonSlabBreakUpList}">									
													<option value="${sbUp.salaryBreakUpCode}" ${sbUp.salaryBreakUpCode eq taxBasedOnCode?'selected':''}>${sbUp.salaryBreakUpName}</option>					
												</c:forEach>
											</select>
										</th>
										<th>
											Figure Type
										</th>
										<th>
											<select name="taxFigureType" id="figureType" class="form-group" disabled >
												<option value="">Select...</option>
												<option value="AMOUNT" ${'AMOUNT' eq figureType?'selected':''}>AMOUNT</option>					
												<option value="PERCENTAGE" ${'PERCENTAGE' eq figureType?'selected':''}>PERCENTAGE</option>
											</select>
										</th>
									</tr>																	
									<tr>
										<th>
											StartSlabIndex (Monthly)
										</th>
										<th>
											EndSlabIndex (Monthly)
										</th>
										<th>
											Tax ${figureType} (Monthly)
										</th>
										<th>
											Delete
										</th>																								
									</tr>									
									<c:forEach var="misc" items="${miscTaxList}" varStatus="i">
										<tr>
											<td>
												<input type="text" class="form-control" name="startSlabIndex" id="startSlabIndex${i.index}" readonly="readonly" value="${misc.startSlabAmount}" onblur="setZero(this);" style="text-align: right;">
											</td>
											<td>
												<input type="text" class="form-control" name="endSlabIndex" id="endSlabIndex${i.index}" readonly="readonly" value="${misc.endSlabAmount}" onblur="setZero(this);" style="text-align: right;">
											</td>
											<td>
												<input type="text" class="form-control" name="taxAmount" id="taxAmount${i.index}" readonly="readonly" onblur="setZero(this);" value="${misc.taxRate}" style="text-align: right;">
											</td>
											<td>
												<input type="image" src="/icam/images/minus_icon.png" name="deleteButton" onclick="return deleteRow(this);" disabled="disabled">
											</td>							
										</tr>
									</c:forEach>	
									<tr>
										<td colspan="4">
											<input type="button" class="addbtn" id="addNewRows" value="Add" onclick="addrows();" disabled="disabled">
										</td>
									</tr>															
								</table>
							</div>
						</section>			
					</section>					
					<footer style="display: block;" class="panel-footer">						
						<input type="button" class="btn btn-default" name="submit" value="BACK" onClick="window.location='/icam/viewMiscTaxSlab.html?' " />
						<input type="submit" class="btn btn-primary" value="SUBMIT" id="submitButton" disabled="disabled"/>
						<input type="button" id="editButton"  value="edit" class="btn btn-default" onclick="return removeDisabled();">			
					</footer>			
		</form:form>
	</c:otherwise>
</c:choose>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/erp/editMiscTaxSlab.js"></script>
</body>
</html>