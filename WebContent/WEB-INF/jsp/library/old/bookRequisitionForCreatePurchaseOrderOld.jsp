<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Add Book" />
<meta name="keywords" content="Add Book" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Add Book</title>

<link rel="stylesheet" href="/sms/css/library/bookRequisitionForCreatePurchaseOrder.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/sms/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/sms/js/library/bookRequisitionForCreatePurchaseOrder.js"></script>

</head>
<body>
<div class="ttlarea">
	<h1>
	<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Purchase Order
	</h1>
</div>
<form id="purchaseOrder" action="submitBookPurchaseOrder.html" method="post">
	<c:choose>
		<c:when test="${RequisitionDetails==null}">
			<div class="btnsarea01">
				<div class="errorbox" id="errorbox" style="visibility:visible;" >
					<span id="errormsg">Requisition List Not Found</span>	
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<form:form method="POST" id="sflcontents" name="sflcontents" action="" >
				<div id="popup_box">   
				   	<table id="vendorPriceTable" cellspacing="0" cellpadding="0" class="midsec1">
						<thead>
							<tr>
								<th></th>
								<th>VendorName</th>
								<th>Price</th>				
							</tr>
						</thead>	
						<tbody>				
						</tbody>
					</table>
					<a id="popupBoxYes"><button type=button class="submitbtn" id="yes" >Ok</button></a>
					<a id="popupBoxNo"><button type=button class="clearbtn" id="No">Cancel</button></a>  
				</div>
				<table id="ReqId" cellspacing="0" cellpadding="0" class="midsec">
					<tr>
						<th>Requisition ID :: </th>
						<td>
							<input type="hidden" name="bookRequisitionCode" value="${RequisitionDetails.bookRequisitionCode}"/>
							${RequisitionDetails.bookRequisitionCode}
						</td>
						<th>Open Date :: </th>
						<td>
							${RequisitionDetails.bookRequisitionOpenDate}
						</td>								
					</tr>
				</table>
				<table id="Details" cellspacing="0" cellpadding="0" class="midsec1">
					<tr>
						<th>Book Name</th>
						<th>Author Name</th>
						<th>Book Edition</th>
						<th>Publisher Name</th>
						<th>Total Order</th>
						<th>Selection</th>
						<th>Vendor Code</th>
						<th>Vendor<img class="required" alt="Required" src="/sms/images/required.gif"></th>
						<th>price<img class="required" alt="Required" src="/sms/images/required.gif"></th>
						<th>Discount</th>
						<th>Tax</th>
						<th>Total Price</th>
					</tr>
					<c:forEach var="bookrRequisitionDetails" items="${RequisitionDetails.bookRequisitionDetailsList}" varStatus="status">
						<tr>
							<td>
								<input class="textfield" type="text" name="bookName" readonly="readonly" value=" ${bookrRequisitionDetails.bookName}"/>
							</td>
							<td>
								<input class="textfield" type="hidden" name="bookAuthorName" readonly="readonly" value=" ${bookrRequisitionDetails.bookAuthor}"/>
								<input  class="textfield" type="text"  readonly="readonly" value="${fn:replace(bookrRequisitionDetails.bookAuthor,'~', ',')}"/>
							</td>
							<td>
								<input class="textfield" type="text" name="bookEdition" readonly="readonly" value=" ${bookrRequisitionDetails.bookEdition}"/>
							</td>
							<td>
								<input class="textfield" type="text" name="bookPublisher" readonly="readonly" value=" ${bookrRequisitionDetails.bookPublisher}"/>
							</td>
							<td>
								<input class="textfield1" type="text" id="individualTotOrder${status.index}" name="individualTotOrder" readonly="readonly" value="<c:out value="${bookrRequisitionDetails.numberOfBooksRequisitioned}"/>" size="5" onblur="calculate('${status.index}');" />
							</td>
							<td>
								<button type="button" id="vendorMapping" name="vendorMapping" class="editbtn">Mapping</button>
							</td>
							<td>
								<input class="textfield" type="text" id="vendorCode" readonly="readonly" name="vendorCode" />
							</td>
							<td>
								<input class="vendorClass" type="text" id="vendorName"  onfocus="getVendorName(this);"readonly="readonly" name="vendorName" />
							</td>
							<td>
								<input class="textfield1" type="text" id="individualPrice${status.index}" name="individualPrice" readonly="readonly" size="10" onblur="calculate('${status.index}');" value="0.0" onfocus="this.value='';" />
							</td>
							<td>
								<input class="textfield1" type="text" id="individualDiscount${status.index}" name="individualDiscount" value="0.0" size="5" onblur="calculate('${status.index}');" onfocus="this.value='';" />
							</td>
							<td>
								<input class="textfield1" type="text" id="tax${status.index}" name="tax" value="0.0" size="5" onblur="calculate('${status.index}');" onfocus="this.value='';" />
							</td>
							<td>
								<input class="textfield1" type="text" id="total${status.index}" name="totalPrice" value="0.0" size="10" readonly="readonly" />
							</td>
						</tr>
					</c:forEach>
				</table>
				<div class="btnsarea01">
					<div class="warningbox" id="warningbox" >
						<span id="warningmsg"></span>	
					</div>
					<div class="infomsgbox" id="infomsgbox" >
						<span id="infomsg"></span>	
					</div>
					<input type="submit" value="SUBMIT" class="submitbtn" onclick="return validateReceivedRequisition();">
					<input  type="button" value="BACK" class="clearbtn" onclick="window.location='viewRequisition.html'">	
				</div>
				<table id="sfltable"></table>
			</form:form>
		</c:otherwise>
	</c:choose>
</form>
</body>
</html>