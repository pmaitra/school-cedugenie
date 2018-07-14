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
<meta name="description" content="Page to Add Asset" />
<meta name="keywords" content="Add Asset Details" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Add Academics Asset Details</title>

<link rel="stylesheet" href="/cedugenie/css/academics/addAssetDetails.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/cedugenie/Cal/default.css"/>

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/academics/addAssetDetails.js"></script>
	<script src="/cedugenie/Cal/zebra_datepicker.js"></script>
	<script>
		 $(document).ready(function() {
			 $('#purchaseDate').Zebra_DatePicker();				 
			
			 $('#purchaseDate').Zebra_DatePicker({
			 	 format: 'd/m/Y'
			 });
		});		
	</script>	
</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;New Asset Details
			<div style="float: right;">
			<div style="float: left; position: relative;">
				<a class="bookDetails" href="downloadAcademicsAssetDetailsExcel.html">
					<button type="button" style="width: 100%;" class="editbtn">Download Excel Sheet</button>
				</a>
			</div>&emsp;			
			<div style="float: right; position: relative;margin-bottom: 1%;">
				<form:form id="safcontents" name="safcontents" method="POST" action="uploadAcademicsAssetDetailsExcel.html" enctype="multipart/form-data">
					<span id="FileUpload">
					    <input type="file" size="24"  name="imageFile" id="attachment" onchange="document.getElementById('FileField').value = document.getElementById('attachment').value;" />
					    <span id="BrowserVisible"><input type="text" id="FileField" /></span>
					</span>
					<input type="submit" class="editbtn" value="Submit" onclick="if(document.getElementById('attachment').value=='')return false; ;">
				</form:form>
			</div>
		</div>
		</h1>
	</div>
	
	<c:if test="${excelDataInsertStatus ne null }">
		<div class="successbox" id="successbox" style="visibility: visible;">
			<span id="successmsg">${excelDataInsertStatus}</span>	
		</div>
	</c:if>
	
	<c:if test="${successStatus != null}">
		<div class="successbox" id="successbox" style="visibility:visible;">
			<span id="successmsg" style="visibility:visible;">New Asset Successfully Added!</span>	
		</div>
	</c:if>
	<c:if test="${failStatus != null}">
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Could not Add Asset. Problem Occurred.</span>	
			</div>
	</c:if>
	
	<form:form name="assetDetails" method="POST" action="addAcademicsAssetDetails.html">				
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th>Department Name <img class="required" src="/cedugenie/images/required.gif" alt="Required"></th>
				<th>::</th>
				<td colspan="3">
					<select name="department.departmentCode" id="departmentName" class="defaultselect" >
						<option value="">Select...</option>
						<c:forEach var="dept" items="${departmentList}">
							<c:if test="${dept.departmentName eq 'PHYSICS LAB' 
										|| dept.departmentName eq 'CHEMISTRY LAB' 
										|| dept.departmentName eq 'BIOLOGY LAB' 
										|| dept.departmentName eq 'COMPUTER LAB'}">
								<option value="${dept.departmentCode}">${dept.departmentName}</option>
							</c:if>
						</c:forEach>
					</select>
				</td>				
			</tr>		
			<tr>
				<th>Asset Type<img class="required" src="/cedugenie/images/required.gif" alt="Required"></th>
				<th>::</th>
				<td colspan="3">
					<select name="assetType" id="assetType" class="defaultselect">
						<option value="">Select...</option>
							<c:forEach var="varAssetType" items="${assetTypeList}">
								<option value="${varAssetType.assetTypeCode}">${varAssetType.assetTypeName}</option>
							</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>Asset Sub Type<img class="required" src="/cedugenie/images/required.gif" alt="Required"></th>
				<th>::</th>
				<td colspan="3">
					<select name="assetSubType" id="assetSubType" class="defaultselect">
						<option value="">Select...</option>
					</select>
				</td>
			</tr>		
			<tr>				
				<th>Asset Name</th>
				<th>::</th>
				<td colspan="3"><input type="text" class="textfield" name="assetName" id="assetName"/></td>
			</tr>
			<tr>				
				<th>Asset Property</th>
				<th>::</th>
				<td colspan="3"><textarea class="txtarea" name="assetProperty" id="assetProperty"></textarea></td>
			</tr>		
			<tr>				
				<th>Asset Price</th>
				<th>::</th>
				<td colspan="3"><input type="text" class="textfield" name="assetPrice" id="amount"/></td>
			</tr>			
			<tr>	
				<th>Purchased On</th>
				<th>::</th>
				<td colspan="3"><input type="text" class="textfield" name="purchaseDate" id="purchaseDate"/></td>								
			</tr>
			<tr>	
				<th>Ledger Balance</th>
				<th>::</th>
				<td><input type="text" class="textfield2" name="ledgerBalance" id="ledgerBalance"/></td>	
				<th>Unit  ::</th>
				<td>
					<select id="assetUnit" name="assetUnit" class="unitSelect">
						<option VALUE="">Select..</option>
						<option value="Pcs">Pcs</option>
						<option value="Pkts">Pkts</option>
						<option value="No.">No.</option>
						<option value="Nos">Nos</option>
						<option value="ml">ml</option>
						<option value="gm">gm</option>
						<option value="kg">kg</option>
						<option value="gms">gms</option>
						<option value="Set">Set</option>
						<option value="Box">Box</option>
						<option value="Pairs">Pairs</option>
						<option value="Roll">Roll</option>
						<option value="Rolls">Rolls</option>
					</select>
				</td>							
			</tr>
			<tr>	
				<th>Ledger No.</th>
				<th>::</th>
				<td><input type="text" class="textfield2" name="ledgerNumber" id="ledgerNumber"/></td>					
				<th>Page No.  ::</th>
				<td><input type="text" class="textfield2" name="pageNumber" id="pageNumber"/></td>							
			</tr>
			<tr>								
			</tr>
			<tr>
				<td colspan="5">
					<input id="submit" class="submitbtn" type="submit" value="Submit" onclick="return validateAssetDetails();">
					<input id="clear" class="clearbtn" type="reset" value="Clear">
				</td>
			</tr>
		</table>
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>			
		</div>
	</form:form>
	
</body>
</html>