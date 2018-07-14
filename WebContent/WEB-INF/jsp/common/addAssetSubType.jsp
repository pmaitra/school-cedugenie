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
<title>Add Asset Sub Type</title>
<link rel="stylesheet" href="/cedugenie/css/common/assetSubType.css" />

<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/radio.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/assetSubType.js"></script>

</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Add Asset Sub Type
		</h1>
	</div>
	
	<c:if test="${submitResponse ne null}">			
		<c:if test="${submitResponse eq 'Success'}">
			<div class="successbox" id="successbox" style="visibility:visible;">
				<span id="successmsg" style="visibility:visible;">Asset Sub Type Successfully Created.</span>	
			</div>
		</c:if>
		<c:if test="${submitResponse eq 'Fail'}">
			<div class="errorbox" id="errorbox" style="visibility:visible;">
				<span id="errormsg" style="visibility:visible;">Asset Sub Type Creation Failed.</span>	
			</div>
		</c:if>			
	</c:if>
		
	<form:form name="submitAssetSubType" action="submitAssetSubType.html" method="POST">		
		
		<table cellspacing="0" cellpadding="0" class="midsec" border="1">		
			<tr>
				<th colspan="2" style="text-align:center;">
					:: Create New Asset Sub Type ::
				</th>						
			</tr>
			<tr>
				<th>
					Select Asset Type<img class="required" src="/cedugenie/images/required.gif" alt="Required">
				</th>
				<td>
					<select name="assetType" id="assetType" class="defaultselect" >
						<option value="">Select...</option>
						<c:forEach var="comodityType" items="${assetTypeList}">
							<option value="${comodityType.assetTypeCode}">${comodityType.assetTypeName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					Enter Asset Sub Type Name<img class="required" src="/cedugenie/images/required.gif" alt="Required">
				</th>
				<td>
					<input type="text" class="textfield1" name="assetSubTypeName" id="assetSubTypeName" >
				</td>
			</tr>
			<tr>				
				<td colspan="2">
					<input type="submit" class="submitbtn" value="CREATE" id="submitButton" onclick="return validateAssetSubTypeForm();"/>
				</td>
			</tr>
		</table>		
	</form:form>
		
		
	<c:if test="${updateResponse ne null}">	
		<c:if test="${updateResponse eq 'Success'}">
			<div class="successbox" id="successbox" style="visibility:visible;">
				<span id="successmsg2" style="visibility:visible;">Asset Sub Type Successfully Updated.</span>	
			</div>
		</c:if>
		<c:if test="${updateResponse eq 'Fail'}">
			<div class="errorbox" id="errorbox" style="visibility:visible;">
				<span id="errormsg" style="visibility:visible;">Asset Sub Type Updatation Failed.</span>	
			</div>
		</c:if>			
	</c:if>			
	<div class="infomsgbox" id="infomsgbox">
		<span id="infomsg"></span>	
	</div>
						
						
	<c:choose>
		<c:when test="${assetSubTypeList == null || empty assetSubTypeList}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No Asset Sub Type Created Yet</span>	
 			</div>
		</c:when>	
		<c:otherwise>			
			<form:form name="editAssetSubType" action="editAssetSubType.html" method="POST">		
					
				<table id="editAssetSubType" cellspacing="0" cellpadding="0" class="midsec1" border="1">	
					<tr>
						<th colspan="3">View / Edit Asset Sub Type</th>
					</tr>				
					<tr>
						<th>Select</th>
						<th>Asset Type</th>
						<th>Asset Sub Type Name</th>
					</tr>
					<c:forEach var="commoSub" items="${assetSubTypeList}" varStatus="i">
						<tr>
							<td><input type="radio" name="assetSubTypeId" id="radioAssetSubType${i.index}" value="${commoSub.assetSubTypeId}" /></td>
							<td>${commoSub.assetType}</td>
							<td>
								<input type="hidden" name="oldAssetSubTypeNames" id="oldAssetSubTypeNamesId${commoSub.assetSubTypeId}" value="${commoSub.assetSubTypeName}">
								<input type="text" class="textfield1" id="textAssetSubType${i.index}" name="assetSubTypeName" value="${commoSub.assetSubTypeName}" disabled="disabled"> 
							</td>							
						</tr>	
					</c:forEach>
					<tr>
						<td colspan="3">
							<input type="submit" class="submitbtn" name="deleteAssetSubType" value="DELETE" id="delete" disabled="disabled" />
							<input type="submit" class="editbtn" name="editAssetSubType" value="UPDATE" id="submitt" disabled="disabled" onclick="return validateEditAssetSubTypeForm();"/>
							<input type="button" class="clearbtn" id="editButton" value="EDIT" onclick="return removeDisabled('assetSubTypeId','warningbox','warningmsg');">
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