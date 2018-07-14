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
<title>Create Update the Condemnation</title>
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
									<strong>Condemnation Successfully Inserted</strong>
							</div>
					</c:if>
					<c:if test="${insertStatus eq 'fail'}">
							<div class="alert alert-danger">
									<strong>Error in Inserting Condemnation</strong>
							</div>
					</c:if>
			
			<c:choose>
			<c:when test="${condemnationList eq null}">	
						<div class="errorbox" id="errorbox" style="visibility:visible;">
								<span id="errormsg"><strong>No Stock is Recommended for Condemnation</strong></span>
						</div>					
				</c:when>
			<c:otherwise>

					<div class="row">
						<div class="col-md-12">
						  <form:form name="submitCondemnation"  method="POST" action="submitCondemnation.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										
										<!-- <h2>Condemnation</h4> -->
										<h2 class="panel-title">CONDEMNATION BOARD FOR ${financialYear.financialYearName}</h2>
										<%-- <input type="hidden" name="department" value="${department}"> --%>
										<input type="hidden" name="submitType" value="${submitType}">										
									</header>
									<div style="display: block;" class="panel-body">
                                        <div class = "table-responsive">
                                            <table class="table table-bordered table-striped mb-none" cellspacing="0" cellpadding="0">
                                                <thead>
                                                    <tr>
                                                        <th>Items</th>
														<th>Ledger/Page</th>
														<th>A/C Unit</th>
														<th>Total Stock</th>
														 <th>Qty Condemned</th>
														<th style="width:30px;">Qty to be Condemned</th> 
														<th>Sentenced For Servicing</th>
														<th>Sentenced For Repairing</th>
														<th>Sentenced For UNS</th>
														<!-- <th>Purchase Date</th> -->
														<th>Rate</th> 
														<th>Cost Amount</th>
														<th>Remarks</th>
                                                    </tr>
                                                </thead>
                                                <tbody>  
                                                <c:forEach var="condemnation" items="${condemnationList}" varStatus="i">                                                  
                                                    <tr>
                                                        <td>
	                                                        ${condemnation.annualStock.commodity.commodityName}
															<input type="hidden" name="listCondemnationStock[${i.index}].annualStock.annualStockId" value="${condemnation.annualStock.annualStockId}">
															<%-- <input type="hidden" name="listCondemnationStock[${i.index}].annualStock.asset.assetId" value="${condemnation.annualStock.asset.assetId}"> --%>
															<input type="hidden" name="listCondemnationStock[${i.index}].condemnationId" value="${condemnation.condemnationId}">
															<input type="hidden" name="listCondemnationStock[${i.index}].annualStock.commodity.commodityName" value="${condemnation.annualStock.commodity.commodityName}"/>
                                                        </td>
                                                       
                                                        <td><input type="text" id="l-pNumber${i.index}" name="listAnnualStock[${i.index}].ledgerNumber" value="${annualStock.ledgerNumber}" class="form-control" style="width:100px; display: inline-block;"/></td>
														
                                                        <td>
                                                            <select class="form-control" id="unit0" name="listCondemnationStock[${i.index}].unit" style="width: 90px;">
																<option value="Kg" ${condemnation.unit eq 'Kg' ? 'selected=selected' : ''}>Kg</option>
																<option value="Pkt" ${condemnation.unit eq 'Pkt' ? 'selected=selected' : ''}>Pkt</option>
																<option value="Bottle" ${condemnation.unit eq 'Bottle' ? 'selected=selected' : ''}>Bottle</option>
																<option value="Ltr" ${condemnation.unit eq 'Ltr' ? 'selected=selected' : ''}>Ltr</option>
																<option value="Pcs" ${condemnation.unit eq 'Pcs' ? 'selected=selected' : ''}>Pcs</option>
																<option value="-" ${condemnation.unit eq '-' ? 'selected=selected' : ''}>UNKNOWN</option>
                                                            </select>
                                                        </td>
                                                         <td>
                                                        	${condemnation.annualStock.commodity.inStock}
                                                        	<input type="hidden" id="iStock${i.index}" value="${condemnation.annualStock.commodity.inStock}">
                                                        </td>
                                                        <td>
															${condemnation.qtyProducedForCB}
															<input type="hidden" id="prCo${i.index}" value="${condemnation.qtyProducedForCB}">
														</td> 
                                                        <td>
                                                        	<input type="text" class="form-control" style="width:100px; display: inline-block;" id="cbPr${i.index}" name="listCondemnationStock[${i.index}].qtyProducedForCB" placeholder="" style="width:50px;" onblur="calculateStock(${i.index});" value="0" required>
                                         				 </td>
                                                        <td>
                                                        	${condemnation.annualStock.serviceable}
                                                        </td>
                                                        <td>
                                                        	${condemnation.annualStock.repairable}
                                                        </td>
                                                        <td>
                                                        	<input type="text" class="form-control" style="width:100px; display: inline-block;" id="quns${i.index}" name="listCondemnationStock[${i.index}].qtySentencedToUNS" value="${condemnation.qtySentencedToUNS}" placeholder="" style="width:50px;" onblur="calculateStock(${i.index});">
                                                        </td>
                                                        <%-- <td>
                                                        	${condemnation.annualStock.asset.purchaseDate}
                                                        </td> --%>
                                                         <td>
                                                        	${condemnation.annualStock.commodity.sellingRate}
															<input type="hidden" id="rate${i.index}" name="listCondemnationStock[${i.index}].annualStock.commodity.sellingRate" value="${condemnation.annualStock.commodity.sellingRate}">
                                                        </td>
                                                        <td>
                                                        	<input type="text" class="form-control" style="width:100px; display: inline-block;" id="cost${i.index}" name="listCondemnationStock[${i.index}].costAmount" value="${condemnation.costAmount}" placeholder="" style="width:50px;" readonly="readonly">
                                                        </td>
                                                        <td>
                                                        	<input type="text" class="form-control" style="width:100px; display: inline-block;" name="listCondemnationStock[${i.index}].remarks" value="${condemnation.remarks}" placeholder="" style="width:100px;">
                                                        </td>
                                                    </tr>
													</c:forEach>
                                                </tbody>
                                            </table>  
                                            </div>
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" class="btn btn-primary" id="submitButton">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
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
	var iStock = document.getElementById("iStock"+thisObj).value;
	var cbPrId = document.getElementById("cbPr"+thisObj).id;
	var obj = cbPrId.substring(0,4);
	var onlyID = cbPrId.slice(-1);
	if(obj == "cbPr"){
		var cbPrBal = document.getElementById("cbPr"+onlyID).value;		
		if(cbPrBal != ""){
			if (!cbPrBal.match(digits)) {
				alert("Enter numeric Condemned Quantity");
				document.getElementById("cbPr"+onlyID).value = 0;
				var cbBal= document.getElementById("cbPr"+onlyID).value;
				cbBal= parseInt(cbBal);
				var rateBal = document.getElementById("rate"+onlyID).value;
				rateBal = parseInt(rateBal);
				var prevCondemn = document.getElementById("prCo"+onlyID).value;
				prevCondemn = parseInt(prevCondemn);
				document.getElementById("cost"+onlyID).value = (cbBal + prevCondemn) * rateBal;
				return false;
			}
			document.getElementById("cost"+onlyID).value = 0.0;
			
			 var iStock = document.getElementById("iStock"+onlyID).value;
			 iStock = parseInt(iStock); 
			
			var cbBal = document.getElementById("cbPr"+onlyID).value;
			cbBal = parseInt(cbBal);
			
			var prevCondemn = document.getElementById("prCo"+onlyID).value;
			prevCondemn = parseInt(prevCondemn);
			
			var difference = iStock-cbBal;
			difference = parseInt(difference);
//			alert(difference);
			if(difference > 0){
				var rateBal = document.getElementById("rate"+onlyID).value;
				rateBal = parseInt(rateBal);
				
				document.getElementById("cost"+onlyID).value = (cbBal + prevCondemn) * rateBal;
			}
			if(difference < 0){
				alert("CB quantity can not be more than Total Stock.");
				document.getElementById("cbPr"+onlyID).value = 0;
				var rateBal = document.getElementById("rate"+onlyID).value;
				rateBal = parseInt(rateBal);
				
				document.getElementById("cost"+onlyID).value = (cbBal + prevCondemn) * rateBal;
				return false;
//				document.getElementById("surp"+onlyID).value = Math.abs(difference);
				}
			} 
		if(cbPrBal == "")
			{
				alert("Enter numeric Condemned Quantity");
				document.getElementById("cbPr"+onlyID).value = 0;
				var cbBal= document.getElementById("cbPr"+onlyID).value;
				cbBal= parseInt(cbBal);
				var rateBal = document.getElementById("rate"+onlyID).value;
				rateBal = parseInt(rateBal);
				var prevCondemn = document.getElementById("prCo"+onlyID).value;
				prevCondemn = parseInt(prevCondemn);
				document.getElementById("cost"+onlyID).value = (cbBal + prevCondemn) * rateBal;
				return false;
			}
	}
	var qunsId = document.getElementById("quns"+thisObj).id;
	var obj = qunsId.substring(0,4);
	var onlyID = qunsId.slice(-1);
	if(obj == "quns"){
		var unsVal = document.getElementById("quns"+onlyID).value;
		if(unsVal != ""){
			if (!unsVal.match(digits)) {
				alert("Enter numeric UNS quantity");
				document.getElementById("quns"+onlyID).value = 0;
				return false;
			}
		}
		if(unsVal == ""){
			alert("Enter numeric UNS quantity");
			document.getElementById("quns"+onlyID).value = 0;
			return false;
		}
	}
}
</script>
</body>
</html>