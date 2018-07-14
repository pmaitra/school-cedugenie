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
	<meta name="description" content="Page to Car Trip Log" />
	<meta name="keywords" content="Car Details" />
	<meta name="revisit-after" content="7 days" />
	<meta name="robots" content="index,follow" />
	<title>EDIT Car Details</title>
	<link rel="stylesheet" href="/icam/css/inventory/editCarDetails.css" />
	<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
	<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
	<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="/icam/Cal/default.css"/>
	<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
	<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/icam/js/inventory/editCarDetails.js"></script>
	<script src="/icam/Cal/zebra_datepicker.js"></script>
	<script>
		 $(document).ready(function() {
			 $('#registrationDate').Zebra_DatePicker();				 
			
			 $('#registrationDate').Zebra_DatePicker({
			 	  format: 'd/m/Y',
			 	 direction: false
			 	});
			 $('#insuranceExpiryDate').Zebra_DatePicker({
			 	  format: 'd/m/Y',
			 	 direction: true
			 	});
			 $('#lastDateOfTax').Zebra_DatePicker({
			 	  format: 'd/m/Y',
			 	 direction: true
			 	});
		});
	</script>
</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Edit Car Details
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
	
	<form:form name="updateCarDetails"  method="POST" action="updateCarDetails.html">
		<table cellspacing="0" cellpadding="0" class="midsec">				
			<tr>	
				<th>Car Name :: <input type="hidden" name="carDetailsId" value="${car.carDetailsId}"/></th>
				<td><input type="text" class="textfield" name="carName" id="carName" value="${car.carName}" readonly="readonly"/></td>				
			</tr>			
			<tr>				
				<th>Color :: </th>
				<td><input type="text" class="textfield" name="carColor" id="carColor" value="${car.carColor}" readonly="readonly"/></td>
			</tr>
			<tr>				
				<th>Chassis Number :: </th>
				<td><input type="text" class="textfield" name="chassisNumber" id="chassisNumber" value="${car.chassisNumber}" readonly="readonly"/></td>
			</tr>			
			<tr>	
				<th>Engine Number :: </th>
				<td><input type="text" class="textfield" name="engineNumber" id="engineNumber" value="${car.engineNumber}" readonly="readonly"/></td>								
			</tr>
			<tr>				
				<th>Registration Number :: </th>
				<td><input type="text" class="textfield" name="registrationNumber" id="registrationNumber" value="${car.registrationNumber}" readonly="readonly"/></td>
			</tr>			
			<tr>	
				<th>Registration Date :: </th>
				<td><input type="text" class="textfield" name="registrationDate" id="registrationDate" value="${car.registrationDate}" disabled="disabled"/></td>								
			</tr>
			<tr>	
				<th>Insurance :: </th>
				<td><input type="text" class="textfield" name="insurance" id="insurance" value="${car.insurance}" readonly="readonly"/></td>								
			</tr>
			<tr>	
				<th>Insurance Expiry Date:: </th>
				<td><input type="text" class="textfield" name="insuranceExpiryDate" id="insuranceExpiryDate" value="${car.insuranceExpiryDate}" disabled="disabled"/></td>								
			</tr>
			<tr>	
				<th>Tax Last Date:: </th>
				<td><input type="text" class="textfield" name="lastDateOfTax" id="lastDateOfTax" value="${car.lastDateOfTax}" disabled="disabled"/></td>								
			</tr>
		</table>
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			<input id="submit" class="submitbtn" type="submit" value="UPDATE" disabled="disabled">
			<input id="edit" class="editbtn" type="button" value="EDIT" onclick="return makeEditable();">
		</div>
	</form:form>
			
	</body>
</html>