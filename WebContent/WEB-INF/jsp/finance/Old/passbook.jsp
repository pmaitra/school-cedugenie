<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="profitAndLoss" />
<meta name="keywords" content="profitAndLoss" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>PassBook</title>

<link rel="stylesheet" type="text/css" href="/icam/css/finance/passbook.css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>

<link rel="stylesheet" type="text/css" href="/icam/Cal/default.css"/>
<script src="/icam/Cal/zebra_datepicker.js"></script>
<script>
$(document).ready(function() {
	 $('#date0').Zebra_DatePicker({
		 format: 'd/m/Y'
	 });
	 
	 $('#instrumentDate0').Zebra_DatePicker({
	 	  format: 'd/m/Y'
	 	});
 });
 
var date=1;
function addRow(){
	var table = document.getElementById("passbookTable"); 
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    
    var cell;
    var element;
    var id1="date"+date;
    var id2="instrumentDate"+date;
    
    cell = row.insertCell(0);
    element = document.getElementById("bank").cloneNode(true);
    element.name="bank";
    cell.appendChild(element);
    
    
    cell = row.insertCell(1);
    element = document.createElement("input");
    element.type = "text";
    element.name="date";
    element.id=id1;
    element.setAttribute("class","date");    
	cell.appendChild(element);
	 
    cell = row.insertCell(2);
    element = document.createElement("textarea");
    element.name="particulars";
    element.setAttribute("class","txtarea");
    cell.appendChild(element);
    
    cell = row.insertCell(3);
    element = document.createElement("select");
    element.innerHTML="<option value='true'>Withdraw</option><option value='false'>Deposit</option>";
    element.name="withdraw";
    element.setAttribute("class","defaultdropdown");
    cell.appendChild(element);
    
    cell = row.insertCell(4);
    element = document.createElement("input");
    element.type="text";
    element.name="instrumentNumber";
    element.setAttribute("class","textfield");
    cell.appendChild(element);
    
    cell = row.insertCell(5);
    element = document.createElement("input");
    element.type="text";
    element.name="instrumentDate";
    element.id=id2;
    element.setAttribute("class","date");
    cell.appendChild(element);
    
    cell = row.insertCell(6);
    element = document.createElement("select");
    element.innerHTML="<option value='true'>Debit</option><option value='false'>Credit</option>";
    element.name="debit";
    element.setAttribute("class","defaultdropdown");
    cell.appendChild(element);
    
    cell = row.insertCell(7);
    element = document.createElement("input");
    element.type="text";
    element.name="balance";
    element.setAttribute("class","textfield");
    cell.appendChild(element);
    
    cell = row.insertCell(8);
    element = "<img src='/icam/images/minus_icon.png' onclick='deleteRow(this);'>";
    cell.innerHTML=element;
    
    id1="#"+id1;
    id2="#"+id2;
    $(id1).Zebra_DatePicker();	 
	$(id1).Zebra_DatePicker({
	 	  format: 'd/m/Y'
	});
	$(id2).Zebra_DatePicker();	 
	$(id2).Zebra_DatePicker({
	 	  format: 'd/m/Y'
	});
    date++;
}

function deleteRow(obj){	
	var table = document.getElementById("passbookTable");
	var rowCount = table.rows.length;	
	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
		document.getElementById("warningbox").style.visibility='collapse';
		document.getElementById("warningmsg").innerHTML="";
	}else{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Row Required.";
	}
}
 </script>
</head>
<body>
<div class="ttlarea">
	<h1>
	<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Passbook
	</h1>
</div>
	<c:if test="${message ne null}">		
			<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
				<span id="infomsg">${message}</span>	
			</div>		
	</c:if>
	<form:form name="" action="savePassbook.html" method="POST" >		
		<table id="passbookTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Bank</th>
				<th>Date</th>
				<th>Particulars</th>
				<th>Withdraw/Deposit</th>
				<th>Instrument No.</th>
				<th>Instrument Dt.</th>
				<th>Debit/Credit</th>
				<th>Balance</th>
				<th><input type="button" id="add" name="add" class="addbtn"  onclick="addRow();"/></th>
			</tr>
			<tr>
				<td>
				<select name="bank" class="defaultdropdown" >
					<option value="">Select</option>
					<c:if test="${allBanks ne null}">
						<c:forEach var="banks" items="${allBanks}">
							<option value="${banks}">${banks}</option>
						</c:forEach>
					</c:if>
				</select>
				</td>
				<td><input type="text" id="date0" class="textfield" name="date"></td>
				<td><textarea class="txtarea" name="particulars"></textarea></td>
				<td>
					 <select name="withdraw" class="defaultdropdown">
						 <option value='true'>Withdraw</option>
						 <option value='false'>Deposit</option>
					 </select>
				</td>				
				<td><input type="text" class="textfield" name="instrumentNumber"></td>
				<td><input type="text" id="instrumentDate0" class="textfield" name="instrumentDate"></td>
				<td>
					 <select name="debit" class="defaultdropdown">
						 <option value='true'>Debit</option>
						 <option value='false'>Credit</option>
					 </select>
				</td>				
				<td><input type="text" class="textfield" name="balance"></td>				
				<td><img src='/icam/images/minus_icon.png' onclick='deleteRow(this);'></td>				
			</tr>
		</table>		
		<div style="visibility: collapse;">
			<select id="bank" class="defaultdropdown" >
				<option value="">Select</option>
				<c:if test="${allBanks ne null}">
					<c:forEach var="banks" items="${allBanks}">
						<option value="${banks}">${banks}</option>
					</c:forEach>
				</c:if>
			</select>
		</div>
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			<input name="" class="clearbtn" type="reset" value="CLEAR">
			<input name="" class="submitbtn" type="submit" value="SUBMIT" id="submit" >	
		</div>
	</form:form>
</body>
</html>