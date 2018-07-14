<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>

<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Add Vendor" />
<meta name="keywords" content="Add Car Maintenance" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Asset Maintenance</title>

<link rel="stylesheet" href="/icam/css/inventory/addAssetMaintenance.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/icam/Cal/default.css"/>

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/inventory/assetMaintenance.js"></script>
	<script src="/icam/Cal/zebra_datepicker.js"></script>
	<script>
		 $(document).ready(function() {
			 
			 $('#billReceivingDate').Zebra_DatePicker();
			 $('#billReceivingDate').Zebra_DatePicker({
			 	format: 'd/m/Y',
			 	direction: false
			 });
		
			 $('#billDate').Zebra_DatePicker();
			 $('#billDate').Zebra_DatePicker({
			 	format: 'd/m/Y',
			 	direction: false
			 });			 
			 
		});
		 
	
	</script>
</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Asset Maintenance
		</h1>
	</div>
	
	<c:if test="${successStatus != null}">
		<div class="successbox" id="successbox" style="visibility:visible;">
			<span id="successmsg" style="visibility:visible;">Successfully Updated!</span>	
		</div>
	</c:if>
	<c:if test="${failStatus != null}">
		<div class="errorbox" id="errorbox" style="visibility: visible;">
			<span id="errormsg">Update Fail!</span>	
		</div>
	</c:if>
	
	<form:form name="createInventoryPurchaseOrder" method="POST" action="getAssetMaintenanceList.html">				
		<table cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<td>Asset :: <img class="required" src="/icam/images/required.gif" alt="Required"></td>
				<td>
					<select name="assetCode" id="asstCode" class="defaultselect" >
						<option value="">Select...</option>
						<c:forEach var="asset" items="${assetList}">
							<option value="${asset.assetCode}">${asset.assetName}</option>
						</c:forEach>
					</select>
				</td>
				<td colspan="3" >
					<input class="greenbtn" type="submit" id="submitbtn" name="submitbtn"  value="VIEW BILLS" onclick="return validateAssetIdTable();"/>
				</td>
				<td>
					<input type="button" id="button" name="addMintenance" class="submitbtn" value="ADD NEW BILL" onclick="showAssetMaintenanceTable();">
				</td>
			</tr>		
		</table>
	</form:form>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>			
	</div>
	
	<form:form name="carMaintenance"  method="POST" action="submitAssetMaintenance.html">
		<table id="assetMaintenanceTable" cellspacing="0" cellpadding="0" class="midsec" style="visibility:collapse;">			
			<tr>				
				<th>Type Of Repairing :: </th>
				<td><input type="text" class="textfield" name="typeOfRepairing" id="typeOfRepairing"/></td>
			</tr>				
			<tr>
				<th>Bill Receiving Date  :: </th>
				<td><input type="text" class="textfield" name="billReceivingDate" id="billReceivingDate"/></td>				
			</tr>
			<tr>				
				<th>Bill No :: </th>
				<td><input type="text" class="textfield" name="billNo" id="billNo"/></td>
			</tr>			
			<tr>	
				<th>Bill Date :: </th>
				<td><input type="text" class="textfield" name="billDate" id="billDate"/></td>								
			</tr>			
			<tr>				
				<th>Amount :: </th>
				<td><input type="text" class="textfield" name="amount" value="0.0" id="amount" onblur="addToCumlative(this);" onfocus="setZero(this);"/></td>
			</tr>			
			<tr>	
				<th>Cumulative Amount :: </th>
				<td>
					<input type="hidden" value="0.0" id="initialCmulativeAmount"/>
					<input type="text" class="textfield" name="cumulativeAmount" id="cumulativeAmount" readonly="readonly"/>
				</td>								
			</tr>		
			<tr>				
				<th>Supplier/Repairer :: </th>
				<td><input type="text" class="textfield" name="supplierRepairer" id="supplierRepairer"/></td>
			</tr>		
			<tr>
				<td colspan="2">
					<input id="edit" class="editbtn" type="button" value="CANCEL" onclick="hideAssetMaintenanceTable();">
					<input id="clear" class="clearbtn" type="reset" value="Clear">					
					<input id="submit" class="submitbtn" type="submit" value="Submit">
				</td>
			</tr>
		</table>
		<input type="hidden" class="textfield" name="assetCode" id="assetCode" value=""/>
	</form:form>
	<c:if test="${showMaintenanceList == 'showMaintenanceList'}">
		<c:choose>		
			<c:when test="${assetMaintenanceList == null || empty assetMaintenanceList}">
				<div class="btnsarea01" style="visibility: visible;">
					<div class="warningbox" id="warningbox2" style="visibility: visible;">
						<span id="warningmsg">Maintenance Log for this Asset Not Found</span>	
					</div>
				</div>
			</c:when>
			<c:otherwise>	
	   	  		<table cellspacing="0" cellpadding="0" class="midsec1" id="assetListTable">   	  		
	   	  			<tr>
			   			<th>Bill Receiving Date</th>
						<th>Type Of Repairing</th>
						<th>Bill No</th>
						<th>Bill Date</th>
						<th>Amount</th>
						<th>Cumulative Amount</th>
						<th>Supplier /Repairer</th>
					</tr>   	  		
					<c:forEach var="asst" items="${assetMaintenanceList}">	
					<tr>
						<td>
							${asst.billReceivingDate}
						</td>			
						<td>
							${asst.typeOfRepairing}
						</td>
						<td>
							${asst.billNo}
						</td>
						<td>
							${asst.billDate}
						</td>
						<td>
							${asst.amount}
						</td>
						<td>
							${asst.cumulativeAmount}
						</td>
						<td>
							${asst.supplierRepairer}
						</td>
					</tr>
					</c:forEach>			
				</table>		
			</c:otherwise>
		</c:choose>
	</c:if>	
</body>
</html>