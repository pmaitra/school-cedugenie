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
<meta name="keywords" content="Create Gate Pass" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create ASTB</title>
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
<script type="text/javascript" src="/icam/js/common/createASTV.js"> </script>
<script src="/icam/Cal/zebra_datepicker.js"></script>
<script>
	 $(document).ready(function() {
		 $('.dateOfPurchaseClass').Zebra_DatePicker();				 
		
		 $('.dateOfPurchaseClass').Zebra_DatePicker({
		 	 format: 'd/m/Y'
		 });
	});		
</script>

</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create ASTB
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
		<c:when test="${annualStockList eq null}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
					<span id="infomsgbox">No Stock Record Found for this Department.</span>	
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<form:form  action="submitASTV.html" method="POST">
			<table cellspacing="0" cellpadding="0" class="midsec">					
				<tr>
					<th>					
						ANNUAL STOCK TAKING / BOARD FOR ${academicYear.academicYearName}
						<input type="hidden" name="department" value="${department}">
						<input type="hidden" name="submitType" value="${submitType}">
					</th>
				</tr>				
			</table>
							
			<table id="tableASTV" cellspacing="0" cellpadding="0" class="midsec" >					
				<tr>
					<th>Ledger No.</th>
					<th>Page No.</th>					
					<th>Items</th>
					<th>Ledger Bal.</th>
					<th>Ground Bal.</th>
					<th>Surplus</th>
					<th>Deficient</th>
					<th>DOP</th>
					<th>Cost/Unit</th>
					<th>Serviceable</th>
					<th>Repairable</th>
					<th>Condemnation</th>
					<th>Remarks</th>	
				</tr>
				<c:forEach var="annualStock" items="${annualStockList}" varStatus="i">
				<tr>				
					<td>${annualStock.asset.ledgerNumber}</td>
					<td>${annualStock.asset.pageNumber}</td>					
					<td>
						<input type="hidden" name="listAnnualStock[${i.index}].annualStockId" value="${annualStock.annualStockId}">
						<input type="hidden" name="listAnnualStock[${i.index}].asset.assetName" value="${annualStock.asset.assetName}"/>
						${annualStock.asset.assetName}
					</td>
					<td>
						<input type="text" id="lBal${i.index}" name="listAnnualStock[${i.index}].asset.ledgerBalance" value="${annualStock.asset.ledgerBalance}" class="textfield" readonly="readonly"/>
					</td>					
					<td>
						<input type="text" id="gBal${i.index}" name="listAnnualStock[${i.index}].groundBalance" value="${annualStock.groundBalance}" class="textfield" onblur="calculateStock(this);"/>
					</td>
					<td>
						<input type="text" id="surp${i.index}" name="listAnnualStock[${i.index}].surplus" value="${annualStock.surplus}" class="textfield" />
					</td>
					<td>
						<input type="text" id="defi${i.index}" name="listAnnualStock[${i.index}].deficient" value="${annualStock.deficient}" class="textfield" />
					</td>
					<td>${annualStock.asset.purchaseDate}</td>
					<td>${annualStock.asset.assetPrice}</td>
					<td>
						<input type="text" id="serv${i.index}" name="listAnnualStock[${i.index}].serviceable" value="${annualStock.serviceable}" class="textfield1" onblur="calculateStock(this);"/>
					</td>	
					<td><input type="text" id="repa${i.index}" name="listAnnualStock[${i.index}].repairable" value="${annualStock.repairable}" class="textfield1" onblur="calculateStock(this);"/></td>
					<td>
						<select name="listAnnualStock[${i.index}].condemnation" class="defaultselect" >
							<option value="true" ${annualStock.condemnation eq true ? 'selected=selected' : ''}>Yes</option>
							<option value="false" ${annualStock.condemnation eq false ? 'selected=selected' : ''}>No</option>
						</select>
					</td>				
					<td><input type="text" name="listAnnualStock[${i.index}].remarks" value="${annualStock.remarks}" class="textfield2" /></td>
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