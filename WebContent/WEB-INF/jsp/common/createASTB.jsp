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
<title>Create ASTB</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
					<header class="page-header">
								<h2>Condemnation</h2>
					</header>
					<c:if test="${insertStatus eq 'success'}">
							<div class="alert alert-success">
									<strong>ASTB Successfully Inserted</strong>
							</div>
					</c:if>
					<c:if test="${insertStatus eq 'fail'}">
							<div class="alert alert-danger">
									<strong>ASTB Insertion failed</strong>
							</div>
					</c:if>
			<c:choose>
				<c:when test="${annualStockList eq null}">	
						<div class="errorbox" id="errorbox" style="visibility:visible;">
								<span id="errormsg"><strong>No Stock Record Found</strong></span>
						</div>					
				</c:when>
			<c:otherwise>
					 <div class="row">
						<div class="col-md-12">
						  <form:form name="submitASTB"  method="POST" action="submitASTB.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create ASTB</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                      <div class="row">
											<table class="table table-bordered table-striped mb-none" cellspacing="0" cellpadding="0">	
													<tr>
														<th>					
															ANNUAL STOCK TAKING / BOARD FOR ${financialYear.financialYearName}
															 <input type="hidden" name="submitType" value="${submitType}">
														</th>
													</tr>		
											</table>
											<div class = "table-responsive">
											<table class="table table-bordered table-striped mb-none" cellspacing="0" cellpadding="0">
											<tr>
												<th>Ledger No.</th>
												<th>Page No.</th>
												<th>Items</th>
												<th>In Stock.</th>
												<th>Ground Bal.</th>
												<th>Surplus</th>
												<th>Deficient</th>
												<!-- <th>DOP</th>
												<th>Cost/Unit</th> -->
												<th>Serviceable</th>
												<th>Repairable</th>
												<th>Condemnation</th>
												<th>Remarks</th>	
											</tr>
											<c:forEach var="annualStock" items="${annualStockList}" varStatus="i">
											<tr>		
														<td><input type="text" id="lNumber${i.index}" name="listAnnualStock[${i.index}].ledgerNumber" value="${annualStock.ledgerNumber}" class="form-control" style="width:100px; display: inline-block;"/></td>
														<td><input type="text" id="pNumber${i.index}" name="listAnnualStock[${i.index}].pageNumber" value="${annualStock.ledgerNumber}" class="form-control" style="width:100px; display: inline-block;"/></td>
														<td>
															<input type="hidden" name="listAnnualStock[${i.index}].annualStockId" value="${annualStock.annualStockId}">
															<input type="hidden" name="listAnnualStock[${i.index}].commodity.commodityName" value="${annualStock.commodity.commodityName}"/>
															${annualStock.commodity.commodityName}
														</td>					
														<td><input type="text" id="iStock${i.index}" name="listAnnualStock[${i.index}].commodity.inStock" value="${annualStock.commodity.inStock}" class="form-control" style="width:100px; display: inline-block;" readonly="readonly"/>
															<input type="hidden" id="iStock" name="inStock" value="${annualStock.commodity.inStock}"/>
														</td>					
														<td><input type="text" id="gBal${i.index}" name="listAnnualStock[${i.index}].groundBalance" value="${annualStock.groundBalance}" class="form-control" style="width:100px; display: inline-block;" onblur="calculateStock(${i.index});"/></td>
														<td><input type="text" id="surp${i.index}" name="listAnnualStock[${i.index}].surplus" value="${annualStock.surplus}" class="form-control" style="width:100px; display: inline-block;" readonly="readonly"/></td>
														<td><input type="text" id="defi${i.index}" name="listAnnualStock[${i.index}].deficient" value="${annualStock.deficient}" class="form-control" style="width:100px; display: inline-block;" readonly="readonly"/></td>
														<%-- <td>${annualStock.asset.purchaseDate}</td>
														<td>${annualStock.asset.assetPrice}</td> --%>
														<td><input type="text" id="serv${i.index}" name="listAnnualStock[${i.index}].serviceable" value="${annualStock.serviceable}" class="form-control" style="width:100px; display: inline-block;" onblur="calculateStock(${i.index});"/></td>	
														<td><input type="text" id="repa${i.index}" name="listAnnualStock[${i.index}].repairable" value="${annualStock.repairable}" class="form-control" style="width:100px; display: inline-block;" onblur="calculateStock(${i.index});" readonly="readonly"/></td>
														<td>
															<select name="listAnnualStock[${i.index}].condemnation" class="form-control" >
																<option value="true" ${annualStock.condemnation eq true ? 'selected=selected' : ''}>Yes</option>
																<option value="false" ${annualStock.condemnation eq false ? 'selected=selected' : ''}>No</option>
															</select>
														</td>				
														<td><input type="text" name="listAnnualStock[${i.index}].remarks" value="${annualStock.remarks}" class="form-control" style="width:100px; display: inline-block;" /></td>
													</tr>
											</c:forEach>
										</table>
									</div>
                                    </div>  
                                     <button class="btn btn-primary" type="submit" id="submitASTB" name= "submitASTB" value="SUBMIT">Submit </button>                  
                                  </div> 
								</section>
                            </form:form>
						</div>
				</div>	
            </c:otherwise>
       </c:choose>

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript">

function calculateStock(thisObj){
	var digits =/^[0-9]{1,}$/;
	//var marks=thisObj.value;
	var iStock = document.getElementById("iStock"+thisObj).value;
	if(iStock == 0)
		{
			document.getElementById("gBal"+thisObj).value= 0;
			document.getElementById("surp"+thisObj).value= 0;
			document.getElementById("defi"+thisObj).value= 0;
			document.getElementById("serv"+thisObj).value= 0;
			document.getElementById("repa"+thisObj).value= 0;
		}
	var gBalId = document.getElementById("gBal"+thisObj).id;
	var obj = gBalId.substring(0,4);
	var onlyID = gBalId.slice(-1);
	if(obj == "gBal"){
		var groundBal = document.getElementById("gBal"+onlyID).value;
		var serviceable= document.getElementById("serv"+onlyID).value;
		
		var numgroundBal = new Number(groundBal);
		var numiStock = new Number(iStock);
		var numServiceable= new Number(serviceable);
		
		if(groundBal == "")
			{
				alert("Ground Balance Cannot be blank");
				document.getElementById("gBal"+onlyID).value = 0;
				var numgroundBal = document.getElementById("gBal"+onlyID).value;
				if(numgroundBal > numiStock)
				{
					var surplus= numgroundBal - numiStock;
					document.getElementById("surp"+onlyID).value = surplus;
					document.getElementById("defi"+onlyID).value = 0;
				}
				if(numgroundBal < numiStock)
				{
					var deficient=  numiStock - numgroundBal;
					document.getElementById("defi"+onlyID).value = deficient;
					document.getElementById("surp"+onlyID).value = 0;
				}
				document.getElementById("serv"+onlyID).value = 0;
				document.getElementById("repa"+onlyID).value = 0;
				return false;
			}
		if(numgroundBal != ""){
			if (!groundBal.match(digits)) {
				alert("Enter numeric Ground Balance");
				document.getElementById("gBal"+onlyID).value = 0;
				var numgroundBal = document.getElementById("gBal"+onlyID).value;
				if(numgroundBal > numiStock)
				{
					var surplus= numgroundBal - numiStock;
					document.getElementById("surp"+onlyID).value = surplus;
					document.getElementById("defi"+onlyID).value = 0;
				}
				if(numgroundBal < numiStock)
				{
					var deficient=  numiStock - numgroundBal;
					document.getElementById("defi"+onlyID).value = deficient;
					document.getElementById("surp"+onlyID).value = 0;
				}
				document.getElementById("serv"+onlyID).value = 0;
				document.getElementById("repa"+onlyID).value = 0;
				return false;
			}
			if(numgroundBal >= numiStock)
				{
					var surplus= numgroundBal - numiStock;
					document.getElementById("surp"+onlyID).value = surplus;
					document.getElementById("defi"+onlyID).value = 0;
					if(numServiceable > numgroundBal)
					{
						alert("Serviceable value cannot be greater than the ground value!!");
						document.getElementById("serv"+onlyID).value = 0;
						var service = document.getElementById("serv"+onlyID).value;
						 var remaining= numgroundBal - service;
						document.getElementById("repa"+onlyID).value = remaining; 
						return false;
					}
					var service = document.getElementById("serv"+onlyID).value;
					 var remaining= numgroundBal - service;
					document.getElementById("repa"+onlyID).value = remaining; 
				}
			if(numgroundBal < numiStock)
			{
				var deficient=  numiStock - numgroundBal;
				document.getElementById("defi"+onlyID).value = deficient;
				document.getElementById("surp"+onlyID).value = 0;
				//var serviceable= document.getElementById("serv"+onlyID).value;
				if(numServiceable > numgroundBal)
				{
					alert("Serviceable value cannot be greater than the ground value!!");
					document.getElementById("serv"+onlyID).value = 0;
					var service = document.getElementById("serv"+onlyID).value;
					 var remaining= numgroundBal - service;
					document.getElementById("repa"+onlyID).value = remaining; 
					return false;
				}
				var service = document.getElementById("serv"+onlyID).value;
				 var remaining= numgroundBal - service;
				document.getElementById("repa"+onlyID).value = remaining; 
			}
		}
	}
	
	/* calculation for serviceable */
	var servId = document.getElementById("serv"+thisObj).id;
	var obj = servId.substring(0,4);
	var onlyID = servId.slice(-1);
	if(obj == "serv"){
		var groundBal= document.getElementById("gBal"+onlyID).value;
		var numgroundBal = new Number(groundBal);
		var serviceable= document.getElementById("serv"+onlyID).value;
		var numserviceable = new Number(serviceable);
		var repairable= document.getElementById("repa"+onlyID).value;
		var numrepairable = new Number(repairable);
		if(numserviceable != "")
			{
				if (!serviceable.match(digits)) {
					alert("Enter numeric Serviceable Balance");
					document.getElementById("serv"+onlyID).value = 0;
					var serviceable= document.getElementById("serv"+onlyID).value;
					var remaining= numgroundBal - serviceable;
					document.getElementById("repa"+onlyID).value = remaining;
					return false;
				}
				if(numserviceable > numgroundBal)
				{
					alert("Serviceable value cannot be greater than the ground value!!");
					document.getElementById("serv"+onlyID).value = 0;
					var service = document.getElementById("serv"+onlyID).value;
					 var remaining= numgroundBal - service;
					document.getElementById("repa"+onlyID).value = remaining; 
					return false;
				}
				if(numserviceable <= numgroundBal)
				{
					var remaining= numgroundBal - numserviceable;
					document.getElementById("repa"+onlyID).value = remaining;
				}
			}
		if(serviceable == "")
			{
				alert("Serviceable Balance cannot be blank");
				document.getElementById("serv"+onlyID).value = 0;
				if(numserviceable > numgroundBal)
				{
					alert("Serviceable value cannot be greater than the ground value!!");
					document.getElementById("serv"+onlyID).value = 0;
					var service = document.getElementById("serv"+onlyID).value;
					 var remaining= numgroundBal - service;
					document.getElementById("repa"+onlyID).value = remaining; 
					return false;
				}
				if(numserviceable <= numgroundBal)
				{
					var remaining= numgroundBal - numserviceable;
					document.getElementById("repa"+onlyID).value = remaining;
				}
				return false;
			}
	}
}
</script>
</body>
</html>