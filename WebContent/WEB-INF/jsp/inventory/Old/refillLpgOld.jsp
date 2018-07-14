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
<meta name="description" content="Page to Refill LPG" />
<meta name="keywords" content="Refill LPG" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Refill LPG</title>

<link rel="stylesheet" href="/icam/css/inventory/addCommodity.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/inventory/refillLpg.js"></script>

</head>
<body>
<div class="ttlarea">	
	<h1>
	<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Refill LPG	

	</h1>
</div>
<c:if test="${successStatus != null}">
	<div class="successbox" id="successbox" style="visibility:visible;">
		<span id="infomsg" style="visibility:visible;">Successfully Updated!</span>	
	</div>
</c:if>
<c:if test="${failStatus != null}">
		<div class="errorbox" id="errorbox" style="visibility: visible;">
			<span id="errormsg">${failStatus}</span>	
		</div>
</c:if>

<form:form method="POST" id="updateRefillLPG" name="updateRefillLPG" action="updateRefillLPG.html" >
	<table cellspacing="0" cellpadding="0" class="midsec" id="productDetails">		
		
		<tr>
			<th>Order No. :: </th>
			<td>
				<input type="text" name="orderNo" id="orderNo" class="textfield1" value="">
			</td>
		</tr>
		<tr>
			<th>LPG Company :: <img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<td>
				<select name="lpgDetailsId" id="companyName" class="defaultselect">
					<option value="">Select...</option>
						<c:forEach var="lpgCompany" items="${lpgCompanyList}">
							<option value="${lpgCompany.lpgDetailsId}">${lpgCompany.companyName}</option>
						</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>Distributors :: <img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<td>
				<select name="vendor.vendorId" id="vendorName" class="defaultselect">
					<option value="">Select...</option>
					<c:forEach var="lpgDistributor" items="${lpgDistributorList}">
							<option value="${lpgDistributor.vendorId}">${lpgDistributor.vendorName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
		<th>No. Of Full Cylinder Received:: <input type="text" class="smalltextfield" name="inStock" id="inStock" value="0.00" onfocus="this.value='';" onblur="validateQuantatity(this)"></th>
		</tr>
		<tr>
		<th>No. Of Empty Cylinder Returned :: <input type="text" class="smalltextfield" name="noOfEmpty" id="noOfEmpty" value="0.00" onfocus="this.value='';" onblur="validateQuantatity(this)"></th>
		</tr>
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>		
		<input type="submit" id="submit" name="submit" class="submitbtn" value="Submit" onclick="return validateUpdateRefillLPGForm();">
		<input type="reset" value="Clear" class="clearbtn" />
	</div>
</form:form>
</body>
</html>