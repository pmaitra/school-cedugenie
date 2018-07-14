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
<meta name="keywords" content="Add Vendor" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Add Vendor</title>

<link rel="stylesheet" href="/icam/css/common/addVendor.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/addVendor.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Add Vendor
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

<form:form name="addVendor"  method="POST" action="submitAddVendor.html">
	<table cellspacing="0" cellpadding="0" class="midsec">
		<tr>
			<th>Vendor Type :: </th>
			<td>
				<select name="vendorType" id="vendorType" class="defaultselect" >
					<option value="">Select...</option>
					<c:forEach var="vendorType" items="${vendorTypeList}">
						<option value="${vendorType.vendorTypeCode}">${vendorType.vendorTypeName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>				
		<tr>	
			<th>Vendor Name :: </th>
			<td><input type="text" class="textfield" name="vendorName" id="vendorName"/></td>				
		</tr>			
		<tr>				
			<th>Contact No. 1 :: </th>
			<td><input type="text" class="textfield" name="vendorContactNo1" id="vendorContactNo1"/></td>
		</tr>
		<tr>				
			<th>Contact No. 2 :: </th>
			<td><input type="text" class="textfield" name="vendorContactNo2" id="vendorContactNo2"/></td>
		</tr>			
		<tr>	
			<th>Address 1 :: </th>
			<td><input type="text" class="textfield" name="address" id="address"/></td>								
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