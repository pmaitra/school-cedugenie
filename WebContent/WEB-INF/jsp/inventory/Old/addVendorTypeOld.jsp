<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Add Vendor Type</title>
<link rel="stylesheet" href="/icam/css/erp/jobType.css" />

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/inventory/vendorType.js"></script>

</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Add Vendor Type
		</h1>
	</div>
	
	<c:if test="${submitResponse ne null}">			
		<c:if test="${submitResponse eq 'Success'}">
			<div class="successbox" id="successbox" style="visibility:visible;">
				<span id="successmsg" style="visibility:visible;">Vendor Type Successfully Created.</span>	
			</div>
		</c:if>
		<c:if test="${submitResponse eq 'Fail'}">
			<div class="errorbox" id="errorbox" style="visibility:visible;">
				<span id="errormsg" style="visibility:visible;">Vendor Type Creation Failed.</span>	
			</div>
		</c:if>			
	</c:if>
		
	<form:form name="submitVendorType" action="submitVendorType.html" method="POST">		
		
		<table cellspacing="0" cellpadding="0" class="midsec" border="1">		
			<tr>
				<th colspan="3" style="text-align:center;">
					:: Create New Vendor Type ::
				</th>						
			</tr>				
			<tr>
				<th>
					Enter Vendor Type Name<img class="required" src="/icam/images/required.gif" alt="Required">
				</th>
				<td>
					<input type="text" class="textfield1" name="vendorTypeName" id="vendorTypeName" >
				</td>
				<td>
					<input type="submit" class="submitbtn" value="CREATE" id="submitButton" onclick="return validateVendorTypeForm();"/>
				</td>
			</tr>					
		</table>		
	</form:form>
		
		
	<c:if test="${updateResponse ne null}">	
		<c:if test="${updateResponse eq 'Success'}">
			<div class="successbox" id="successbox" style="visibility:visible;">
				<span id="successmsg2" style="visibility:visible;">Vendor Type Successfully Updated.</span>	
			</div>
		</c:if>
		<c:if test="${updateResponse eq 'Fail'}">
			<div class="errorbox" id="errorbox" style="visibility:visible;">
				<span id="errormsg" style="visibility:visible;">Vendor Type Updatation Failed.</span>	
			</div>
		</c:if>			
	</c:if>			
	<div class="infomsgbox" id="infomsgbox">
		<span id="infomsg"></span>	
	</div>
						
						
	<c:choose>
		<c:when test="${vendorTypeList == null ||  empty vendorTypeList}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No Vendor Type Created Yet</span>	
 			</div>
		</c:when>	
		<c:otherwise>			
			<form:form name="editVendorType" action="editVendorType.html" method="POST">		
					
				<table id="editVendorType" cellspacing="0" cellpadding="0" class="midsec1" border="1">	
					<tr>
						<th colspan="2">View / Edit Vendor Type</th>
					</tr>				
					<tr>
						<th>Select</th>
						<th>Vendor Type Name</th>
					</tr>
					<c:forEach var="vType" items="${vendorTypeList}" varStatus="i">
						<tr>
							<td><input type="radio" name="vendorId" id="radioVendorType${i.index}" value="${vType.vendorId}" /></td>
							<td>
								<input type="hidden" name="oldVendorTypeNames" id="oldVendorTypeNamesId${vType.vendorId}" value="${vType.vendorTypeName}">
								<input type="text" class="textfield1" id="textVendorType${i.index}" name="vendorTypeName" value="${vType.vendorTypeName}" disabled="disabled"> 
							</td>							
						</tr>	
					</c:forEach>
					<tr>
						<td colspan="2">
							<input type="submit" class="editbtn" value="UPDATE" id="submitt" disabled="disabled" onclick="return validateEditVendorTypeForm();"/>
							<input type="button" class="clearbtn" id="editButton" value="EDIT" onclick="return removeDisabled('vendorId','warningbox','warningmsg');">
						</td>
					</tr>
				</table>										
			</form:form>
		</c:otherwise>		
	</c:choose>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>				
	</div>
		
</body>
</html>