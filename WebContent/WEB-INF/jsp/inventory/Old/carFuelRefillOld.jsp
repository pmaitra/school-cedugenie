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
	<title>Car Fuel Refill</title>
	<link rel="stylesheet" href="/icam/css/inventory/carFuelRefill.css" />
	<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
	<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
	<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="/icam/Cal/default.css"/>
	
	<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
	<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/icam/js/inventory/carFuelRefill.js"></script>
	<script src="/icam/Cal/zebra_datepicker.js"></script>
	<script>
	 $(document).ready(function() {
		 $('#dateOfRefill').Zebra_DatePicker();				 
		
		 $('#dateOfRefill').Zebra_DatePicker({
		 	  format: 'd/m/Y',
		 	 direction: false
		 	});
});
</script>
</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Car Fuel Refill 
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
	
	<form:form name="carFuelRefill"  method="POST" action="updateFuelRefill.html">
		<table cellspacing="0" cellpadding="0" class="midsec">	
		<tr>	
				<th>Date :: </th>
				<td><input type="text" class="textfield" name="dateOfRefill" id="dateOfRefill"/></td>				
			</tr>			
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
				<th>Enter the Fuel Quantity :: </th>
				<td><input type="text" class="textfield" name="Quantity" id="Quantity"/></td>				
			</tr>
			<tr>	
				<th>Enter the Price :: </th>
				<td><input type="text" class="textfield" name="price" id="price"/></td>				
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