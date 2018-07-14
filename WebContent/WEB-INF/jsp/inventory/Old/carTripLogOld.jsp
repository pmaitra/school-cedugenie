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
	<meta name="keywords" content="Car Trip Log" />
	<meta name="revisit-after" content="7 days" />
	<meta name="robots" content="index,follow" />
	<title>Car Trip Log</title>
	<link rel="stylesheet" href="/icam/css/inventory/carTripLog.css" />
	<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
	<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
	<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="/icam/Cal/default.css"/>
	
	<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
	<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/icam/js/inventory/carTripLog.js"></script>
	<script src="/icam/Cal/zebra_datepicker.js"></script>
	<script>
	 $(document).ready(function() {
		 $('#journeyDate').Zebra_DatePicker();				 
		
		 $('#journeyDate').Zebra_DatePicker({
		 	  format: 'd/m/Y',
		 	 direction: false
		 	});
});
</script>
</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Add Car Trip Log
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
	
	<form:form name="addCarTripLog"  method="POST" action="submitCarTripLog.html">
		<table cellspacing="0" cellpadding="0" class="midsec">				
			<tr>
				<th>Car ::</th>
				<td>
					<select name="carDetails.carDetailsCode" id="carNameId" class="defaultselect" >
						<option value="">Select...</option>
						<c:forEach var="car" items="${carList}">
							<option value="${car.carDetailsCode}">${car.carDetailsName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>			
			<tr>	
				<th>Date :: </th>
				<td><input type="text" class="textfield" name="journeyDate" id="journeyDate"/></td>				
			</tr>			
			<tr>				
				<th>Specific nature Of Duty :: </th>
				<td><input type="text" class="textfield" name="natureOfDuty" id="natureOfDuty"/></td>
			</tr>
			<tr>				
				<th>From :: </th>
				<td><input type="text" class="textfield" name="rideStartFrom" id="rideStartFrom"/></td>
			</tr>			
			<tr>	
				<th>To :: </th>
				<td><input type="text" class="textfield" name="rideEndsTo" id="rideEndsTo"/></td>								
			</tr>
			<tr>				
				<th>Kilometer Rides :: </th>
				<td><input type="text" class="textfield" name="kilometerRides" id="kilometerRides"/></td>
			</tr>			
			<tr>	
				<th>Mileage / Km Rides :: </th>
				<td><input type="text" class="textfield" name="mileagePerKm" id="mileagePerKm"/></td>								
			</tr>
		</table>
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			<input id="submit" class="submitbtn" type="submit" value="Submit">
			<input id="clear" class="clearbtn" type="reset" value="Clear">
		</div>
	</form:form>
			
	</body>
</html>