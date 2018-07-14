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
<meta name="description" content="Page to Edit Vendor" />
<meta name="keywords" content="Vendor Details" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Vendor Details</title>

<link rel="stylesheet" href="/icam/css/common/vendorDetails.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/vendorDetails.js"></script>

</head>
<body>
<div class="ttlarea">	
	<h1>
	<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Vendor Details
	</h1>
</div>
<form:form name="updateVendorDetails"  method="POST" action="updateVendorDetails.html">
	<table cellspacing="0" cellpadding="0" class="midsec">
		<tr>
			<th>Vendor Type :: </th>
			<td>${vendor.vendorType}</td>
		</tr>	
		<tr>	
			<th>Vendor Name :: </th>
			<td>
				<input type="text" class="textfield" name="vendorName" id="vendorName" value="${vendor.vendorName}" readonly="readonly"/>
				<input type="hidden"  name="vendorCode" readonly="readonly" value="${vendor.vendorCode}"/>
			</td>				
		</tr>			
		<tr>				
			<th>Contact No. 1 :: </th>
			<td><input type="text" class="textfield" name="vendorContactNo1" id="vendorContactNo1" value="${vendor.vendorContactNo1}" readonly="readonly"/></td>
		</tr>			
		<tr>				
			<th>Contact No. 2 :: </th>
			<td><input type="text" class="textfield" name="vendorContactNo2" id="vendorContactNo2" value="${vendor.vendorContactNo2}" readonly="readonly"/></td>
		</tr>			
		<tr>	
			<th>Address 1 :: </th>
			<td><input type="text" class="textfield" name="address" id="address" value="${vendor.address}" readonly="readonly"/></td>								
		</tr>
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
		<input id="update" class="greenbtn" type="submit" value="Update" name="update" disabled="disabled" onclick="return validateVendorDetailsForm();">
		<input id="delete" class="submitbtn" type="submit" value="Delete" name="delete" disabled="disabled">
		<input id="clear" class="clearbtn" type="reset" value="Clear">
		<input id="edit" class="editbtn" type="button" value="Edit"  onclick="editableVendorDetailsForm();">
	</div>
</form:form>		
</body>
</html>