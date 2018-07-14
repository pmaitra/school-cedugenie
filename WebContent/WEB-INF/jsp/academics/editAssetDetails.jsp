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
<meta name="description" content="Page to Edit Asset Informations" />
<meta name="keywords" content="Edit Asset Details" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Edit Academics Asset Details</title>

<link rel="stylesheet" href="/cedugenie/css/academics/addAssetDetails.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/cedugenie/Cal/default.css"/>

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/academics/editAssetDetails.js"></script>
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
			<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Edit Asset Details
		</h1>
	</div>
	
	<c:if test="${successStatus != null}">
		<div class="successbox" id="successbox" style="visibility:visible;">
			<span id="successmsg" style="visibility:visible;">Asset Successfully Updated.</span>	
		</div>
	</c:if>
	<c:if test="${failStatus != null}">
		<div class="errorbox" id="errorbox" style="visibility: visible;">
			<span id="errormsg">Could not Update Asset. Problem Occured !!</span>	
		</div>
	</c:if>
	
	<form:form name="assetDetails" method="POST" action="updateAcademicsAssetDetails.html">				
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th>Department Name<img class="required" src="/cedugenie/images/required.gif" alt="Required"></th>
				<th>::</th>
				<td colspan="3">
					<select name="department.departmentCode" id="departmentName" class="defaultselect" disabled="disabled">
						<option value="">Select...</option>
						<c:forEach var="dept" items="${departmentList}">
							<c:if test="${dept.departmentName eq 'PHYSICS LAB' 
										|| dept.departmentName eq 'CHEMISTRY LAB' 
										|| dept.departmentName eq 'BIOLOGY LAB' 
										|| dept.departmentName eq 'COMPUTER LAB'}">
								<option value="${dept.departmentCode}" ${dept.departmentCode eq asset.department.departmentCode ? 'selected=selected' : ''}>${dept.departmentName}</option>
							</c:if>
						</c:forEach>
					</select>
				</td>				
			</tr>
			<tr>
				<th>Asset Type <img class="required" src="/cedugenie/images/required.gif" alt="Required"></th>
				<th>::</th>
				<td colspan="3">
					<select name="assetType" id="assetType" class="defaultselect" disabled="disabled">
						<option value="">Select...</option>
						<c:forEach var="varAssetType" items="${assetTypeList}">
							<option value="${varAssetType.assetTypeCode}" ${varAssetType.assetTypeCode eq asset.assetType ? 'selected=selected' : ''}>${varAssetType.assetTypeName}</option>
						</c:forEach>
	 				</select>
				</td>
			</tr>
			<tr>
				<th>Asset Sub Type <img class="required" src="/cedugenie/images/required.gif" alt="Required"></th>
				<th>::</th>
				<td colspan="3">
					<select name="assetSubType" id="assetSubType" class="defaultselect" disabled="disabled">
						<option value="">Select...</option>
						<c:forEach var="varAssetSubType" items="${assetSubTypeList}">
							<option value="${varAssetSubType.assetSubTypeCode}" ${varAssetSubType.assetSubTypeCode eq asset.assetSubType ? 'selected=selected' : ''}>${varAssetSubType.assetSubTypeName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>		
			<tr>				
				<th>Asset Name<input type="hidden" name="assetId" value="${asset.assetId}"></th>
				<th>::</th>
				<th>${asset.assetName}</th>
			</tr>
			<tr>				
				<th>Asset Property</th>
				<th>::</th>
				<td colspan="3"><textarea class="txtarea" name="assetProperty" id="assetProperty" readonly="readonly">${asset.assetProperty}</textarea></td>
			</tr>		
			<tr>				
				<th>Asset Price</th>
				<th>::</th>
				<td colspan="3"><input type="text" class="textfield" name="assetPrice" id="amount" value="${asset.assetPrice}" readonly="readonly"/></td>
			</tr>		
			<tr>	
				<th>Purchased On</th>
				<th>::</th>
				<td colspan="3"><input type="text" class="textfield" name="purchaseDate" id="purchaseDate" value="${asset.purchaseDate}" disabled="disabled"/></td>	
			</tr>
			<tr>
				<th>Ledger Balance</th>
				<th>::</th>
				<td><input type="text" class="textfield2" name="ledgerBalance" id="ledgerBalance" value="${asset.ledgerBalance}" readonly="readonly"/></td>	
				<th>Unit  ::</th>
				<td>
					<select id="assetUnit" name="assetUnit" class="unitSelect" disabled="disabled">
						<option VALUE="">Select..</option>
						<option value="Pcs" <c:if test="${asset.assetUnit eq 'Pcs'}">selected="selected"</c:if>>Pcs</option>
						<option value="Pkts" <c:if test="${asset.assetUnit eq 'Pkts'}">selected="selected"</c:if>>Pkts</option>
						<option value="No." <c:if test="${asset.assetUnit eq 'No.'}">selected="selected"</c:if>>No.</option>
						<option value="Nos" <c:if test="${asset.assetUnit eq 'Nos'}">selected="selected"</c:if>>Nos</option>
						<option value="ml" <c:if test="${asset.assetUnit eq 'ml'}">selected="selected"</c:if>>ml</option>
						<option value="gm" <c:if test="${asset.assetUnit eq 'gm'}">selected="selected"</c:if>>gm</option>
						<option value="kg" <c:if test="${asset.assetUnit eq 'kg'}">selected="selected"</c:if>>kg</option>
						<option value="gms" <c:if test="${asset.assetUnit eq 'gms'}">selected="selected"</c:if>>gms</option>
						<option value="Set" <c:if test="${asset.assetUnit eq 'Set'}">selected="selected"</c:if>>Set</option>
						<option value="Box" <c:if test="${asset.assetUnit eq 'Box'}">selected="selected"</c:if>>Box</option>
						<option value="Pairs" <c:if test="${asset.assetUnit eq 'Pairs'}">selected="selected"</c:if>>Pairs</option>
						<option value="Roll" <c:if test="${asset.assetUnit eq 'Roll'}">selected="selected"</c:if>>Roll</option>
						<option value="Rolls" <c:if test="${asset.assetUnit eq 'Rolls'}">selected="selected"</c:if>>Rolls</option>
					</select>
				</td>		
			</tr>
			<tr>
				<th>Ledger No.</th>
				<th>::</th>
				<td><input type="text" class="textfield2" name="ledgerNumber" id="ledgerNumber" value="${asset.ledgerNumber}" readonly="readonly"/></td>	
				<th>Page No.  ::</th>
				<td><input type="text" class="textfield2" name="pageNumber" id="pageNumber" value="${asset.pageNumber}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td colspan="5">
					<input id="clear" class="clearbtn" type="reset" value="Clear">
					<input id="submit" class="submitbtn" type="submit" value="UPDATE" onclick="return validateAssetDetails();" disabled="disabled">
					<input id="edit" class="editbtn" type="button" value="EDIT" onclick="makeEditable();">
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