<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Create Demand Voucher" />
<meta name="keywords" content="Create Demand Voucher" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Demand Voucher</title>
<link rel="stylesheet" href="/icam/css/inventory/createDemandVoucher.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" />


<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>

<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">
<link rel="stylesheet" href="/icam/Cal/jsDatePick_ltr.min.css">
<script src="/icam/Cal/jsDatePick.min.1.3.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
<script src="/icam/js/inventory/createDemandVoucher.js"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Demand Voucher
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

	<c:choose>
		<c:when test="${commodityList == null || empty commodityList}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
					<span id="infomsg">No commodity found.</span>	
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<form:form id="createDemandVoucher" name="createDemandVoucher" action="submitCreateDemandVoucher.html" method="POST">
				<input id="upperSubmit" class="greenbtn" type="submit" value="Submit" name="submit" onclick="return validateSubmitCreateDemandVoucherForm();">
				<input id="upperClear" class="clearbtn" type="reset" value="Clear">
				<br>
				<table  id="commodityTable" cellspacing="0" cellpadding="0" class="midsec1">
					<tr>
						<th colspan="7">
						Quarter Master Session: ${inventorySession.academicYearName}<input type="hidden" value="${inventorySession.academicYearName}" id="houseMasterSession" name="inventorySession.academicYearName" class="textfield2" readonly="readonly" >
					</th>
					</tr>
					<tr>
						<th>SL.No.</th>
						<th>Commodity Name</th>
						<th>QM Section Balance</th>
						<th>Mess Section Balance</th>
						<th>Demand</th>
						<th>Remarks</th>
					</tr>
					<c:forEach var="commodity" items="${commodityList}" varStatus="i">
						<tr>
							<td>
								${i.count}
							</td>
							<td>
								${commodity.commodityName}
								<input type="hidden" value="${commodity.commodityName}" id="commodityName${i.count}" readonly="readonly">
								<input type="hidden" value="${commodity.commodityId}" id="commodityId${i.count}" name="commodityList[${i.index}].commodityId" readonly="readonly">
								<c:set var="numberOfCommodityOnPage" value="${i.count}" scope="page" />
							</td>
							<td>
								${commodity.inStock}
							</td>
							<td>
								${commodity.messStock}
							</td>
							<td>
								<input type="text" onblur="validateCreateDemandVoucherQuantity(this)" onfocus="this.value='';" value="0.00" id="quantity${i.count}" name="commodityList[${i.index}].quantity" class="textfield1">
							</td>
							<td>
								<input type="text" id="commodityDesc${i.count}" name="commodityList[${i.index}].commodityDesc" class="textfield2">
							</td>
						</tr>
					</c:forEach>
			</table>	
				<input id="lowerSubmit" class="greenbtn" type="submit" value="Submit" name="submit" onclick="return validateSubmitCreateDemandVoucherForm();">
				<input id="lowerClear" class="clearbtn" type="reset" value="Clear">
				<input id="numberOfCommodityOnPage"  type="hidden" value="${numberOfCommodityOnPage}">
				
		<div class="btnsarea01">
			<div class="infomsgbox" id="infomsgbox" >
				<span id="infomsg"></span>	
			</div>
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
			
		</div>
	</form:form>
		</c:otherwise>
	</c:choose>
	<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
	</div>

</body>
</html>