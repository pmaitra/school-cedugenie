<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Add Products" />
<meta name="keywords" content="Add Products" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Add Commodity</title>

<link rel="stylesheet" href="/icam/css/inventory/addCommodity.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/inventory/addCommodity.js"></script>

</head>
<body>
<div class="ttlarea">	
	<h1>
	<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Add Commodity	
	<div style="float: right;">
		<div style="float: left; position: relative;">
		<a class="bookDetails" href="downloadCommodityDetailsExcel.html">
			<button type="button" style="width: 100%;" class="editbtn">Download Excel Sheet</button>
		</a>
		</div>&emsp;			
		<div style="float: right; position: relative;margin-bottom: 1%;">
			<form:form id="safcontents" name="safcontents" method="POST" action="uploadCommodityDetailsExcel.html" enctype="multipart/form-data">
				<span id="FileUpload">
				    <input type="file" size="24"  name="imageFile" id="attachment" onchange="document.getElementById('FileField').value = document.getElementById('attachment').value;" />
				    <span id="BrowserVisible"><input type="text" id="FileField" /></span>
				    <input type="submit" class="editbtn" value="Submit" onclick="if(document.getElementById('attachment').value=='')return false; ;">
				</span>				
			</form:form>
		</div>
	</div>
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
<c:if test="${excelDataInsertStatus ne null }">
	<c:if test="${excelDataInsertStatus ne 0 }">
		<div class="successbox" id="successbox" style="visibility: visible;">
			<span id="successmsg">Excel Uploaded Successfully</span>	
		</div>
	</c:if>
	<c:if test="${excelDataInsertStatus eq 0 }">
		<div class="successbox" id="successbox" style="visibility: visible;">
			<span id="successmsg">Problem Occured While Uploading Excel</span>	
		</div>
	</c:if>
</c:if>
<form:form method="POST" id="submitAddCommodity" name="submitAddCommodity" action="submitAddCommodity.html" >
	<table cellspacing="0" cellpadding="0" class="midsec" id="productDetails">		
		<tr>
			<th>Commodity Name :: <img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<td>
				<input type="text" name="commodityName" id="commodityName" class="textfield1" >
				<input type="hidden" name="commodityCode" id="commodityCode" class="textfield1" >
			</td>
		</tr>
		<tr>
			<th>Commodity Desc :: </th>
			<td>
				<input type="text" name="commodityDesc" id="commodityDesc" class="textfield1" value="">
			</td>
		</tr>
		<tr>
			<th>Commodity Type :: <img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<td>
				<select name="commodityType" id="commodityType" class="defaultselect">
					<option value="">Select...</option>
						<c:forEach var="varCommodityType" items="${commodityTypeList}">
							<option value="${varCommodityType.commodityTypeCode}">${varCommodityType.commodityTypeName}</option>
						</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>Commodity Sub Type :: <img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<td>
				<select name="commoditySubType" id="commoditySubType" class="defaultselect">
					<option value="">Select...</option>
				</select>
			</td>
		</tr>
		<tr>
		<th>Horse Ration ::</th>
		<th>
			<input type="checkbox" name="horseRation" id="horseRationYes" value="true"> Yes
		</th>
		</tr>
		<tr>
		<th>Ledger No. :: <input type="text" class="smalltextfield" name="ledgerNumber" id="ledgerNumber" value=""></th>
		<th>Page No.:: <input type="text" class="smalltextfield" name="pageNumber" id="pageNumber" value=""></th>
		</tr>
		<tr>
		<th>
			Threshold ::
		</th>
		<th>
			<input type="text" class="textfield1" name="threshold" id="threshold" value="0.0" onblur="validateSubmitAddCommodityThreshold(this)" onfocus="this.value='';">
		</th>
	</tr>
	</table>
	
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>		
		<input type="submit" id="submit" name="submit" class="submitbtn" value="Submit" onclick="return validateAddCommodityForm();">
		<input type="reset" value="Clear" class="clearbtn" />
	</div>
</form:form>
</body>
</html>