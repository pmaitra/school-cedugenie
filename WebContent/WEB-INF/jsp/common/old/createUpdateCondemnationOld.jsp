<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Standard Pay Scales" />
<meta name="keywords" content="Create Condemnation" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Condemnation</title>
<link rel="stylesheet" href="/icam/css/common/createASTV.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">
<script type="text/javascript" src="/icam/js/common/createCondemnation.js"> </script>

</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Condemnation
		</h1>
	</div>		
	<div>
					
			<c:if test="${successMessage ne null}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">${successMessage}</span>	
				</div>
			</c:if>
			<c:if test="${errorMessage ne null}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">${errorMessage}</span>	
				</div>
			</c:if>		
		
	</div>
	<div class="infomsgbox" id="infomsgbox" >
		<span id="infomsg"></span>	
	</div>
	<c:choose>
		<c:when test="${condemnationList eq null}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
					<span id="infomsgbox">No Stock is Recommended for Condemnation for this Department.</span>	
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<form:form  action="submitCondemnation.html" method="POST">
			<table cellspacing="0" cellpadding="0" class="midsec">					
				<tr>
					<th>					
						CONDEMNATION BOARD FOR ${academicYear.academicYearName}
						<input type="hidden" name="department" value="${department}">
						<input type="hidden" name="submitType" value="${submitType}">
					</th>
				</tr>				
			</table>
							
			<table id="tableCondemn" cellspacing="0" cellpadding="0" class="midsec" >					
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
					<th>Purchase Date</th>
					<th>Rate</th>
					<th>Cost Amount</th>
					<th>Remarks</th>
				</tr>
				<c:forEach var="condemnation" items="${condemnationList}" varStatus="i">
				<tr>				
					<td>
						${condemnation.annualStock.asset.assetName}
						<input type="hidden" name="listCondemnationStock[${i.index}].annualStock.annualStockId" value="${condemnation.annualStock.annualStockId}">
						<input type="hidden" name="listCondemnationStock[${i.index}].annualStock.asset.assetId" value="${condemnation.annualStock.asset.assetId}">
						<input type="hidden" name="listCondemnationStock[${i.index}].condemnationId" value="${condemnation.condemnationId}">
						<input type="hidden" name="listCondemnationStock[${i.index}].annualStock.asset.assetName" value="${condemnation.annualStock.asset.assetName}"/>						
					</td>
					<td>
						${condemnation.annualStock.asset.ledgerNumber}-${condemnation.annualStock.asset.pageNumber}
						<input type="hidden" id="lBal${i.index}" name="listCondemnationStock[${i.index}].annualStock.asset.ledgerBalance" value="${condemnation.annualStock.asset.ledgerBalance}">
					</td>				
					<td>
						<select id="unit0" name="listCondemnationStock[${i.index}].unit" class="defaultselect">
							<option VALUE="" ${condemnation.unit eq '' ? 'selected=selected' : ''}>Please select</option>
							<option value="Kg" ${condemnation.unit eq 'Kg' ? 'selected=selected' : ''}>Kg</option>
							<option value="Pkt" ${condemnation.unit eq 'Pkt' ? 'selected=selected' : ''}>Pkt</option>
							<option value="Bottle" ${condemnation.unit eq 'Bottle' ? 'selected=selected' : ''}>Bottle</option>
							<option value="Ltr" ${condemnation.unit eq 'Ltr' ? 'selected=selected' : ''}>Ltr</option>
							<option value="Pcs" ${condemnation.unit eq 'Pcs' ? 'selected=selected' : ''}>Pcs</option>
							<option value="-" ${condemnation.unit eq '-' ? 'selected=selected' : ''}>UNKNOWN</option>	
						</select>
					</td>	
					<td>${condemnation.annualStock.asset.ledgerBalance}</td>
					<td>
					${condemnation.qtyProducedForCB}
					<input type="hidden" id="prCo${i.index}" value="${condemnation.qtyProducedForCB}">
					</td>
					<td>
					<input type="text" id="cbPr${i.index}" name="listCondemnationStock[${i.index}].qtyProducedForCB" value=0 class="textfield" onblur="calculateStock(this);"/>
					</td>
					<td>${condemnation.annualStock.serviceable}</td>
					<td>${condemnation.annualStock.repairable}</td>
					<td>
					<input type="text" id="quns${i.index}" name="listCondemnationStock[${i.index}].qtySentencedToUNS" value="${condemnation.qtySentencedToUNS}" class="textfield" onblur="calculateStock(this);"/>
					</td>
					<td>${condemnation.annualStock.asset.purchaseDate}</td>
					<td>
						${condemnation.annualStock.asset.assetPrice}
						<input type="hidden" id="rate${i.index}" name="listCondemnationStock[${i.index}].annualStock.asset.assetPrice" value="${condemnation.annualStock.asset.assetPrice}">
					</td>
					<td>
					<input type="text" id="cost${i.index}" name="listCondemnationStock[${i.index}].costAmount" value="${condemnation.costAmount}" class="textfield" onblur="calculateStock(this);"/>
					</td>
					<td>
					<input type="text" name="listCondemnationStock[${i.index}].remarks" value="${condemnation.remarks}" class="textfield2" />
					</td>									
				</tr>
				</c:forEach>			
			</table>
				
			<div class="btnsarea01">
				<div class="warningbox" id="warningbox" >
					<span id="warningmsg"></span>	
				</div>
				<input type="submit" class="submitbtn" value="SUBMIT" id="submitButton"/>
				<input type="reset" class="clearbtn" value="clear" />				
			</div>			
			</form:form>
		</c:otherwise>
	</c:choose>				
	
</body>
</html>