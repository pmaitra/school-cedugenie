<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/commodityVendorMappingPagination.html" var="pagedLink" >
	  <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Enter Daily Mess Consumption" />
<meta name="keywords" content="Daily Mess Consumption" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Daily Mess Consumption</title>

<link rel="stylesheet" href="/icam/css/inventory/createDailyMessConsumption.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/showHideField.js"></script>
<script type="text/javascript" src="/icam/js/inventory/createDailyMessConsumption.js"></script>
<script type="text/javascript" src="/icam/js/common/validateSearch.js"></script>
<script type="text/javascript">


$(document).ready(function() {
	var currentDate = new Date();
	var currentMonth = currentDate.getMonth();
	currentMonth = currentMonth + 1;
	currentMonth = "0"+currentMonth;
	document.getElementById("consumptionDate").value = currentDate.getDate()+"/"+currentMonth+"/"+currentDate.getFullYear();	
	var counter=1;
    	$("#addButton").click(function(){        
    		var table = document.getElementById("messConsuptionTable");	
    		 var row = $('<tr>'); 
             row.append($('<td><select id="commodityType'+counter+'" name="commodityList['+counter+'].commodityType" class="defaultselect" onchange="validateCommodityType(this,\'remaining'+counter+'\',\'rate'+counter+'\',\'hiddenRate'+counter+'\',\'totConsumption'+counter+'\',\'costing'+counter+'\');">'+
						'<option VALUE="" >Please select</option>'+
						'<option value="Tender">Tender</option>'+
						'<option value="NonTender">Non Tender</option>'+
						'<option value="DailyRation">Daily Ration</option>'+
						'<option value="Miscellaneous">Miscellaneous</option>'+
						'</select></td>'+
                     	'<td><input type="text" value="" id="commodityName'+counter+'" name="commodityList['+counter+'].commodityName" class="textfieldName" onfocus="addAutoComplete(this);" onblur="fetchStockAndRate(this,\'remaining'+counter+'\',\'rate'+counter+'\',\'hiddenRate'+counter+'\',\'totConsumption'+counter+'\',\'costing'+counter+'\',\'messStock'+counter+'\');"></td>'+
            		 	 '<td><input type="text" value="0.0" id="messStock'+counter+'" name="commodityList['+counter+'].messStock" class="textfieldRate" readonly="readonly" onblur="validateMessStock(this,\'receipt'+counter+'\',\'totalStock'+counter+'\',\'totConsumption'+counter+'\',\'remaining'+counter+'\');"></td>'+
            		 	 '<td><input type="text" value="0.0" id="receipt'+counter+'" name="commodityList['+counter+'].receipt" class="textfieldRate" readonly="readonly" onblur="validateReceipt(this,\'messStock'+counter+'\',\'totalStock'+counter+'\',\'totConsumption'+counter+'\',\'costing'+counter+'\',\'remaining'+counter+'\');"></td>'+
            		 	 '<td><input type="text" value="0.0" id="totalStock'+counter+'" name="totalStock'+counter+'" class="textfieldRate" readonly="readonly"></td>'+
            			 '<td><input type="text" onblur="validateCommodityRate(this,\'hiddenRate'+counter+'\',\'totConsumption'+counter+'\',\'costing'+counter+'\')" value="0.00" id="rate'+counter+'" name="commodityList['+counter+'].purchaseRate" class="textfieldRate">'+
            			 '<input type="hidden" value="0.00" id="hiddenRate'+counter+'" name="hiddenRate'+counter+'" class="textfieldRate"></td>'));
			            <c:forEach var="menuText" items="${messMenuList}" varStatus="i">
							var textName = "<c:out value="${menuText.messMenuTimeCode}"/>";
							var count = "<c:out value="${i.count}"/>";
			             	var cellInLoop =$('<td><input type="text" value="0.0" id="consumptionForMessMenu'+counter+''+count+'" name="'+textName+'" class="textfieldRate" onfocus="this.value=\'\';" onblur="calculateReceiviengQuantity(this,\'rate'+counter+'\','+counter+',\'commodityName'+counter+'\',\'totalStock'+counter+'\',\'remaining'+counter+'\',\'totConsumption'+counter+'\',\'costing'+counter+'\');"></td>');
			             	row.append(cellInLoop);
			            </c:forEach> 	 
            	var cell=$('<td><input type="text" value="0.0" id="ymen'+counter+'" name="ymen'+counter+'" class="textfieldRate" readonly="readonly"></td>'+
            		 	 '<td><input type="text" value="0.0" id="totConsumption'+counter+'" name="commodityList['+counter+'].purchaseAmount" class="textfieldRate" readonly="readonly"></td>'+
            		 	 '<td><input type="text" value="0.00" id="costing'+counter+'" name="commodityList['+counter+'].sellingRate" class="textfieldRate" readonly="readonly"></td>'+
            		 	 '<td><input type="text" value="0.00" id="remaining'+counter+'" name="commodityList['+counter+'].quantity" class="textfieldRate" readonly="readonly"></td>'+
            		 	 '<td><img  src="/icam/images/minus_icon.png" onclick="deleteThisRow(this);"></td>');
            	row.append(cell);
            	counter++;
             $(table).append(row); 
		});
});
function calculateReceiviengQuantity(thisObj,purchaseRate,rowCount,commodityNameId,totalStockId,remainingId,totConsumptionId,costingId){
	
	var textBoxId = $(thisObj).attr('id');
	var size = "${messMenuList.size()}";	
	var commodityName = document.getElementById(commodityNameId).value;
	var totalStockVal = document.getElementById(totalStockId).value;
	var totConsumptionVal = document.getElementById(totConsumptionId).value;
	var purchaseRateVal = document.getElementById(purchaseRate).value;
	var sum = 0;
	var excessFlag = 0;
	var regAmount = '^[0-9.]+$';
	var amount = document.getElementById(textBoxId).value;
	if(amount != ""){
		if(amount != "0.00"){
			if(!amount.match(regAmount)) {
				document.getElementById(textBoxId).value = "0.0";
				document.getElementById("warningbox").style.visibility="visible";
				document.getElementById("warningmsg").innerHTML="Please enter correct qunatity for "+commodityName+"."+" Quantity should be numeric.";
			}
			else{
				for(var nextIndex = 1;nextIndex <= size ;nextIndex++){
					var eachId = "consumptionForMessMenu"+rowCount+nextIndex;
					var val = document.getElementById(eachId).value;
					val = parseFloat(val);
					sum = sum + val;
					sum = parseFloat(sum);
					totConsumptionVal = parseFloat(totConsumptionVal);
					totalStockVal = parseFloat(totalStockVal);
					purchaseRateVal = parseFloat(purchaseRateVal);
					if(sum>totalStockVal){
						document.getElementById(textBoxId).value = "0.0";
						excessFlag = 1;
						document.getElementById("warningbox").style.visibility="visible";
						document.getElementById("warningmsg").innerHTML="Quantity is excess stock for "+commodityName+".";
						
					}
					if(sum<=totalStockVal){
						document.getElementById("warningbox").style.visibility="collapse";
						document.getElementById("warningmsg").innerHTML="";
						document.getElementById(costingId).value = purchaseRateVal*sum;
						document.getElementById(totConsumptionId).value = sum;
						document.getElementById(remainingId).value = parseFloat(totalStockVal - sum);
					}
				}
				if(excessFlag == 1){
					validateConsumption(rowCount,totalStockVal,purchaseRateVal,totConsumptionId,remainingId,costingId);
				}
				
			}
		}
		/*if(amount == "0.00"){
			alert("in 0.0");
			document.getElementById(remainingId).value = "0.0";
			document.getElementById(totConsumptionId).value = "0.0";
		}*/
	}
	if(amount == ""){
		document.getElementById("warningbox").style.visibility="collapse";
		document.getElementById("warningmsg").innerHTML="";
		document.getElementById(textBoxId).value = "0.0";
		for(var nextIndex = 1;nextIndex <= size ;nextIndex++){
			var eachId = "consumptionForMessMenu"+rowCount+nextIndex;
			var val = document.getElementById(eachId).value;
			val = parseFloat(val);
			sum = sum + val;
			totConsumptionVal = parseFloat(totConsumptionVal);
			totalStockVal = parseFloat(totalStockVal);
			document.getElementById(totConsumptionId).value = sum;
			document.getElementById(remainingId).value = parseFloat(totalStockVal - sum);
		}
	}
	
	
}

function validateConsumption(rowCount,totalStockVal,purchaseRateVal,totConsumptionId,remainingId,costingId){
	var size = "${messMenuList.size()}";	
	var sumAnother = 0;
	for(var nextInd = 1;nextInd <= size ;nextInd++){
		var eachIdAnother = "consumptionForMessMenu"+rowCount+nextInd;
		var valAnother = document.getElementById(eachIdAnother).value;
		valAnother = parseFloat(valAnother);
		sumAnother = sumAnother + valAnother;
		sumAnother = parseFloat(sumAnother);
		document.getElementById(costingId).value = purchaseRateVal*sumAnother;
		document.getElementById(totConsumptionId).value = sumAnother;
		document.getElementById(remainingId).value = parseFloat(totalStockVal - sumAnother);
	}
}



</script>
</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Daily Mess Consumption
	</h1>
</div>
<c:choose>
	<c:when test="${messMenuList eq null}">
		<div class="btnsarea01" >
			<div class="infomsgbox" style="visibility: visible;">
				<span>No Mess Menu Found. </span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	
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
	
	<form:form id="submitDailyMessConsumption" name="submitDailyMessConsumption" action="submitDailyMessConsumption.html" method="POST">
		<table  id="messConsuptionDateAndTypeTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<td>
					Date :: 
				</td>
				<td>
					<input type="text" value="" id="consumptionDate" name="consumptionDate" class="textfieldDate" >					
				</td>
				<td>
					Staff Strength :: 
				</td>
				<td>
					<input type="text" value="" id="staffStrength" name="staffStrength" class="textfieldDate" >					
				</td>
				<td>
					Student Strength :: 
				</td>
				<td>
					<input type="text" value="" id="studentStrength" name="studentStrength" class="textfieldDate" >					
				</td>
			</tr>
		</table>
		<table  id="messConsuptionTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Commodity Type</th>
				<th>Commodity Name</th>
				<th>Mess Stock</th>
				<th>Receipt</th>
				<th>Total</th>
				<th>Rate</th>
				<c:forEach var="menu" items="${messMenuList}" varStatus="i">
					<th>
						${menu.messMenuTimeName}
						<input type="hidden" value="${menu.messMenuTimeCode}" id="messMenu${i.count}" name="messMenu" class="textfieldRate">
					</th>
				</c:forEach>
				<th>ymen</th>
				<th>Total</th>
				<th>Costing</th>
				<th>Balance</th>
				<th></th>
			</tr>
				<tr>
					<td>
						<select id="commodityType0" name="commodityList[0].commodityType" class="defaultselect" onchange="validateCommodityType(this,'remaining0','rate0','hiddenRate0','totConsumption0','costing0');">
							<option VALUE="" >Please select</option>
							<option value="Tender">Tender</option>
							<option value="NonTender">Non Tender</option>
							<option value="DailyRation">Daily Ration</option>
							<option value="Miscellaneous">Miscellaneous</option>
						</select>
					</td>
					<td>
						<input type="text" value="" id="commodityName0" name="commodityList[0].commodityName" class="textfieldName" onfocus="addAutoComplete(this);" onblur="fetchStockAndRate(this,'remaining0','rate0','hiddenRate0','totConsumption0','costing0','messStock0');">					
					</td>
					<td>
						<input type="text" value="0.0" id="messStock0" name="commodityList[0].messStock" class="textfieldRate" readonly="readonly" onblur="validateMessStock(this,'receipt0','totalStock0','totConsumption0','remaining0');">	
					</td>
					<td>
						<input type="text" value="0.0" id="receipt0" name="commodityList[0].receipt" class="textfieldRate" readonly="readonly" onblur="validateReceipt(this,'messStock0','totalStock0','totConsumption0','costing0','remaining0');">	
					</td>
					<td>
						<input type="text" value="0.0" id="totalStock0" name="totalStock0" class="textfieldRate" readonly="readonly">	
					</td>
					<td>
						<input type="text" onblur="validateCommodityRate(this,'hiddenRate0','totConsumption0','costing0')" value="0.00" id="rate0" name="commodityList[0].purchaseRate" class="textfieldRate">
						<input type="hidden" value="0.00" id="hiddenRate0" name="hiddenRate0" class="textfieldRate">
					</td>
					<c:forEach var="menuText" items="${messMenuList}" varStatus="i">
						<td>
							<input type="text" value="0.0" id="consumptionForMessMenu0${i.count}" name="${menuText.messMenuTimeCode}" onfocus="this.value='';" class="textfieldRate" onblur="calculateReceiviengQuantity(this,'rate0','0','commodityName0','totalStock0','remaining0','totConsumption0','costing0');">	
						</td>
					</c:forEach>
					<td>
						<input type="text" value="0.0" id="ymen0" name="ymen0" class="textfieldRate" readonly="readonly">	
					</td>
					<td>
						<input type="text" value="0.0" id="totConsumption0" name="commodityList[0].purchaseAmount" class="textfieldRate" readonly="readonly">	
					</td>
					<td>
						<input type="text" value="0.00" id="costing0" name="commodityList[0].sellingRate" class="textfieldRate" readonly="readonly">
					</td>
					<td>
						<input type="text" value="0.00" id="remaining0" name="commodityList[0].quantity" class="textfieldRate" readonly="readonly">
					</td>
					<td>
						<input id="addButton" class="greenbtn" type="button" value="Add" name="addButton">
					</td>
				</tr>			
		</table>	
				<input id="submit" class="greenbtn" type="submit" value="Submit" name="submit" onclick="return validateSubmitDailyMessExpenseForm();">
				<input id="clear" class="clearbtn" type="reset" value="Clear">
		<br>
		<br>
		<div class="btnsarea01">
			<div class="infomsgbox" id="infomsgbox" >
				<span id="infomsg"></span>	
			</div>
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			
		</div>
	</form:form>
	</c:otherwise>
</c:choose>
</body>
</html>