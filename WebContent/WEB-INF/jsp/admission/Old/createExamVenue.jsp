<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Payment Details" />
<meta name="keywords" content="Payment Details" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Payment Details</title>

<link rel="stylesheet" href="/icam/css/admission/createExamVenue.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>

<script src="/icam/js/admission/createExamVenue.js"></script>

</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Exam Venue
		</h1>
	</div>
		<c:if test="${successStatus != null}">
			<div class="successbox" id="successbox" style="visibility:visible;">
				<span id="infomsg" style="visibility:visible;">Successfully Updated!</span>	
			</div>
		</c:if>
		<c:if test="${failStatus != null}">
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">Update Fail!</span>	
				</div>
		</c:if>
		<form:form method="POST" id="vmlform" action="submitExamVenue.html" >
			<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th>Venue <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input type="text" class="textfield1" id="venue" name="venueName" /></td>
			</tr>
			<tr>
				<th>Contact No <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input type="text" class="textfield1" id="contactNo" name="contactNo" /></td>
				<th>Mobile No</th>
				<td><input type="text" class="textfield1" id="mobileNo" name="mobileNo" /></td>
			</tr>
			<tr>
				<th>Email</th>
				<td><input type="text" class="textfield1" id="email" name="email" /></td>
				<th>Capacity <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input type="text" class="textfield1" id="capacity" name="capacity" /></td>
			</tr>
			<tr>
				<th colspan="4" style="text-align: center;text-decoration: underline;">Address</th>
			</tr>
			<tr>
				<th>Address<img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td colspan="3" ><input type="text" class="textfield2" id="presentAddress1" name="address.presentAddressLine" /></td>
			</tr>			
			<tr>				
				<th>Landmark</th>				
				<td><input type="text" class="textfield1" id="presentAddressLandmark" name="address.presentAddressLandmark" /></td>
				<th>City/Village<img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="presentAddressCityVillage" name="address.presentAddressCityVillage" /></td>
			</tr>			
			<tr>
				<th>Post Office<img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="presentAddressPostOffice" name="address.presentAddressPostOffice" /></td>
				<th>Police Station<img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="presentAddressPoliceStation" name="address.presentAddressPoliceStation" /></td>
			</tr>			
			<tr>				
				<th>Pin code<img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="presentAddressPinCode" name="address.presentAddressPinCode" /></td>
				<th>District<img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="presentAddressDistrict" name="address.presentAddressDistrict" /></td>			
			</tr>			
			<tr>
				<th>Country<img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td>
					<select id="presentAddressCountry" name="address.presentAddressCountry" class="presentAddressCountry">
						<OPTION VALUE="">Please select</option>
						<c:forEach var="country" items="${countryList}" >
							<OPTION VALUE="${country.countryCode}">${country.countryName}</option>
						</c:forEach>					
					</select>
				</td>
				<th>State<img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td>
					<select name="address.presentAddressState" id="presentAddressState"  class="presentAddressState">
						<OPTION VALUE="" >Please select</option>
					</select>  
				</td>				
			</tr>	
			</table>
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			<input class="submitbtn" type="submit" id="submit" name="submit"  value="Submit" onclick="return validateCreateExamVenueForm();"/>
			<input type="reset" value="Clear" id="Clear" class="clearbtn" />
		</form:form>		
</body>

</html>
		