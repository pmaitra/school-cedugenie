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

<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Create non tender Purchase Order" />
<meta name="keywords" content="Create non tender Purchase Order" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Non Tender Order</title>

<link rel="stylesheet" href="/icam/css/inventory/createInventoryNonTenderPurchaseOrder.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>

<script>
var i=1;
function addTableRow(){	
		var table = document.getElementById("nonTenderCommodityTable");
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		
		var cell = row.insertCell(0);
		var element = document.createElement("input");
		element.type = "text";
		element.className = "textfield1";
		element.name = "commodityList["+i+"].commodityName";
		cell.appendChild(element);		
		
		var cell = row.insertCell(1);
		var element = document.createElement("input");
		element.type = "text";
		element.className = "textfield";
		element.name = "commodityList["+i+"].quantity";
		cell.appendChild(element);
					
		
		var scat = document.getElementById("unit0");	
	    var sgrp = scat.innerHTML;
	    

		var cell = row.insertCell(2);
		var element = document.createElement("select");		
		element.className = "defaultselect";
		element.name = "commodityList["+i+"].unitName";
		element.innerHTML=sgrp;
	   	
		cell.appendChild(element);
		
		var cell = row.insertCell(3);
		var element = document.createElement("input");
		element.type = "text";
		element.className = "textfield";
		element.name = "commodityList["+i+"].purchaseRate";
		cell.appendChild(element);		

		var cell = row.insertCell(4);
		var element = document.createElement("img");
		element.setAttribute("src","/icam/images/minus_icon.png");	
		element.setAttribute("onclick", "deleteRow(this);");
		cell.appendChild(element);
		 i++;
		var innerHeight2=document.body.scrollHeight;
	 	var frame=window.parent.document.getElementById("frame");	    	
	 	frame.style.height = innerHeight2+50+ 'px';
}	
		

function deleteRow(obj){	
	var table = document.getElementById("nonTenderCommodityTable");
	var rowCount = table.rows.length;	
	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
		var innerHeight2=document.body.scrollHeight;
    	var frame=window.parent.document.getElementById("frame");	    	
    	frame.style.height = innerHeight2+25+ 'px';
	}else{
		var innerHeight2=document.body.scrollHeight;
    	var frame=window.parent.document.getElementById("frame");	    	
    	frame.style.height = innerHeight2+25+ 'px';
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
	}
}

function validareForm(){

	if(document.getElementById("orderNumber").value==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Provide Order Number";
		return false;
	}		
	return true;
}



</script>

</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Non Tender Order
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
	
	<form:form id="submitNonTenderOrder" name="submitNonTenderOrder" action="submitNonTenderOrder.html" method="POST">
	
		<table  cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th>
					Order Number
				</th>
				<td>
					<input type="text" id="orderNumber" name="orderNumber" class="textfield1" >					
				</td>
			</tr>
			<tr>
				<th>
					Remarks
				</th>
				<td>
					<textarea name="commodityDesc" id="commodityDesc" class="txtarea"></textarea>				
				</td>				
			</tr>
		</table>
		
		<table  id="nonTenderCommodityTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Item Name</th>
				<th>Quantity</th>
				<th>A/C-Unit</th>
				<th>Estimated Rate</th>
				<th></th>
			</tr>
				<tr>
					<td>
						<input type="text" id="commodityName0" name="commodityList[0].commodityName" class="textfield1" >					
					</td>					
					<td>
						<input type="text" id="quantity0" name="commodityList[0].quantity" class="textfield">
					</td>					
					<td>
						<select id="unit0" name="commodityList[0].unitName" class="defaultselect">
							<option VALUE="" >Please select</option>
							<option value="Kg">Kg</option>
							<option value="Pkt">Pkt</option>
							<option value="Bott">Bott</option>
							<option value="Ltr">Ltr</option>
							<option value="-">UNKNOWN</option>						
						</select>
					</td>
					<td>
						<input type="text" id="rate0" name="commodityList[0].purchaseRate" class="textfield">
					</td>					
					<td>
						<input id="addButton" class="greenbtn" type="button" value="Add" name="addButton" onclick="addTableRow()">
					</td>
				</tr>			
		</table>	
				<input id="submit" class="submitbtn" type="submit" value="Submit" onclick="return validareForm();">
				<input id="clear" class="clearbtn" type="reset" value="Clear">
		<p/>
		<div class="btnsarea01">
			<div class="infomsgbox" id="infomsgbox" >
				<span id="infomsg"></span>	
			</div>
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
		</div>
		
	</form:form>
</body>
</html>