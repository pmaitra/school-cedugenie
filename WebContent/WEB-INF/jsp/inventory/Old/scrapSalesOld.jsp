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
	<meta name="description" content="Page to Scrap Log" />
	<meta name="keywords" content="Scrap Details" />
	<meta name="revisit-after" content="7 days" />
	<meta name="robots" content="index,follow" />
	<title>Scrap Sales Details</title>
	
	<link rel="stylesheet" href="/icam/css/inventory/createScrapSales.css" />
	<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
	<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
	<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
	<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="/icam/Cal/default.css"/>
	<link href="/icam/js/inventory/createScrapSales.js" rel="stylesheet" />
	
	<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
	<script type="text/javascript" src="/icam/js/inventory/createScrapSales.js"></script>
	
	<script>
		$(document).ready(function() {
			$('#date').Zebra_DatePicker();
			 
			$('#date').Zebra_DatePicker({
				format: 'd/m/Y',
				direction: true
			});
		});
	</script>
	
</head>

<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;Scrap Sales
		</h1>
	</div>
	
	<c:if test="${successStatus != null}">
		<div class="successbox" id="successbox" style="visibility:visible;">
			<span id="successmsg" style="visibility:visible;">Details saved successfully.</span>	
		</div>
	</c:if>
	<c:if test="${failStatus != null}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Insertion Failed!</span>	
			</div>
	</c:if>
		<form:form name="addScrapDetailsLog"  method="POST" action="submitScrapDetails.html">
		<table id="detailsForScrapDetails" cellspacing="0" cellpadding="0" class="midsec1">				
			<tr>
				<td>Date :: </td>
				<td><input type="text" class="textfield" name="date" id="date" value="${academicYear.sessionStartDate}"></td>							
			</tr>
			<tr>
				<td>Vendor :: </td>
				<td><input type="text" class="textfield" name="vendor" id="vendor"/></td>
			</tr>
			<tr>
				<td>Contact No. :: </td>
				<td><input type="text" class="textfield" name="contactNo" id="contactNo"/></td>
			</tr>
			<tr>	
				<td>Address :: </td>
				<td><input type="text" class="textfield" name="address" id="address"/></td>								
			</tr>
		</table>
		<table id="ScrapSalesTable" cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Scrap</th>
				<th>Unit</th>
				<th>Rate</th>
				<th>Quantity</th>
				<th>Amount</th>
				<th>Comments</th>
				<th>Action</th>
			</tr>
			<tr>
				<td><input type="text" class= "textfield" name="scrapItemName" id="scrapItemName0"></td>
				<td>
					<select class= "textfield" name="scrapItemUnit" id="scrapItemUnit0">
						<option value="">Select...</option>
						<option value="ltr">Litre</option>
						<option value="kg">Kilogram</option>
						<option value="pc">Piece(s)</option>
						<option value="bottle">Bottle(s)</option>
						<option value="pkt">Packet(s)</option>
					</select>
				</td>
				<td><input type="text" class="textfield" name="scrapItemRate" id="scrapItemRate0" value="0.00" onfocus="makeZero(this);" onblur="calculate(0);"></td>
				<td><input type="text" class="textfield" name="scrapItemQty" id="scrapItemQty0" value="0.00" onfocus="makeZero(this);" onblur="calculate(0);"></td>
				<td><input type="text" class="textfield" name="scrapItemAmount" id="scrapItemAmount0" value="0.00" readonly="readonly"></td>
				<td><input type="text" class="textfield" name="comment" id="scrapItemComment0"></td>
				<td>
					<input id="addButton" class="addbtn" type="button" value="" name="addButton" onclick = "return addRow();">
				</td>
			</tr>
			
			<tr>
				<td>Total</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><input type="text" class= "textfield" name="scrapItemTotal" id="scrapItemTotal" value="0.00"></td>
			</tr>
		</table>
		
		<div style="visibility: collapse;">
				<input type="text" class= "textfield" id="scrapItemName">
				<select class= "textfield" id="scrapItemUnit">
					<option value="">Select...</option>
					<option value="ltr">Litre</option>
					<option value="kg">Kilogram</option>
					<option value="pc">Piece(s)</option>
					<option value="bottle">Bottle(s)</option>
					<option value="pkt">Packet(s)</option>  
				</select>
				<input type="text" class= "textfield" id="scrapItemRate" value="0.00">
				<input type="text" class= "textfield" id="scrapItemQty" value="0.00">
				<input type="text" class= "textfield" id="scrapItemAmount" value="0.00" readonly="readonly">
				<input type="text" class= "textfield" id="comment">
		</div>
		
		
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>
			</div>
			<input id="submit" class="submitbtn" type="submit" value="SUBMIT" onclick = "return submitForm();">
			<input id="clear" class="clearbtn" type="reset" value="Clear">
		</div>
	</form:form>
	</body> 
</html>