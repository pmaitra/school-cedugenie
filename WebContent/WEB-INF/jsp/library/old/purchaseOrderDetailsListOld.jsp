<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
	<%@ include file="/file/sessionDataForChildPages.txt"%>
	<c:url value="/purchaseOrderDetailsListPagination.html" var="pagedLink">
		 <c:param name="p" value="~"/>
	</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Purchase Order Details List" />
<meta name="keywords" content="Purchase Order Details List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Purchase Order Details List</title>

<link rel="stylesheet" href="/sms/css/library/purchaseOrderDetailsList.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<link href="/sms/css/common/pagination.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/radio.js"></script>
<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/library/purchaseOrderDetailsList.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Purchase Order Details List
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>
<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<input type="checkbox" onclick="ShowAll(this)" />
	<label for="Type">All</label><br>
	<input type="checkbox" class="listShowHide" value="Requisition Code" onclick="ShowHideField('Requisition Code', 'sitable', this)" checked="checked" />
	<label for="Requisition Code">Requisition Code</label><br>
	<input type="checkbox" class="listShowHide" value="Order Code" onclick="ShowHideField('Order Code', 'sitable', this)" />
	<label for="Order Code">Order Code</label><br>
	<input type="checkbox" class="listShowHide" value="Book Name" onclick="ShowHideField('Book Name', 'sitable', this)" checked="checked" />
	<label for="Book Name">Book Name</label><br>
	<input type="checkbox" class="listShowHide" value="Author" onclick="ShowHideField('Author', 'sitable', this)" />
	<label for="Author">Author</label><br>
	<input type="checkbox" class="listShowHide" value="Publisher" onclick="ShowHideField('Publisher', 'sitable', this)" />
	<label for="Publisher">Publisher</label><br>
	<input type="checkbox" class="listShowHide" value="Edition" onclick="ShowHideField('Edition', 'sitable', this)" />
	<label for="Edition">Edition</label><br>
	<input type="checkbox" class="listShowHide" value="Qty Ordered" onclick="ShowHideField('Qty Ordered', 'sitable', this)" />
	<label for="Qty Ordered">Qty Ordered</label><br>
	<input type="checkbox" class="listShowHide" value="Qty Received" onclick="ShowHideField('Qty Received', 'sitable', this)" />
	<label for="Qty Received">Qty Received</label><br>
	<input type="checkbox" class="listShowHide" value="Qty Deficit" onclick="ShowHideField('Qty Deficit', 'sitable', this)" />
	<label for="Qty Deficit">Qty Deficit</label><br>
	<input type="checkbox" class="listShowHide" value="Catalogue" onclick="ShowHideField('Catalogue', 'sitable', this)" checked="checked" />
	<label for="Catalogue">Catalogue</label><br>
	<input type="checkbox" class="listShowHide" value="Catalogue" onclick="ShowHideField('Amount', 'sitable', this)" checked="checked" />
	<label for="Amount">Amount</label><br>
</div>
<c:choose>
	<c:when test="${pagedListHolder eq null}">		
			<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
				<span id="infomsg">No purchase Order left to add in book catalogue</span>	
			</div>		
	</c:when>
	<c:otherwise>
	<form action="saveToCatalogueAfterReceive.html" method="post">
	<table id="" cellspacing="0" cellpadding="0" class="midsec1">
	<tr>
		<th colspan="2">
			<select name="query" id="query" class="defaultselect" tabindex="1">
				<option value="" >Please Select...</option>
				<option value="RequisitionCode">Requisition Code</option>
				<option value="OrderCode">Order Code</option>
				<option value="BookName">Book Name</option>
				<option value="Author">Author</option>
				<option value="Publisher">Publisher</option>
				<option value="Edition">Edition</option>
				<option value="Amount">Total Price</option>
				
			</select>
		</th>
		<th colspan="4">
			<input type="text" class="textfield" name="data" id="data" value="Search"   onfocus="this.value='';this.style.color='orange';" onblur="javascript: if(this.value==''){this.value='Search';this.style.color='orange';}else{this.style.color='orange';}"/>
		</th>
		<th>
			<input class="editbtn" type="submit" name="purchaseOrderDetailsListSearch" value="Search">
		</th>
	</tr>
</table>
	<table id="sitable" cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<th>Select</th>
			<th>Requisition Code</th>
			<th>Order Code</th>
			<th>Book Name</th>
			<th>Author</th>
			<th>Publisher</th>
			<th>Edition</th>
			<th>Qty Ordered</th>
			<th>Qty Received</th>
			<th>Qty Deficit</th>
			<th>Catalogue</th>
			<th>Total Price</th>
			<th>Unit Price</th>
		</tr>
	 	<c:forEach var="bookPurchaseOrderDetailsList" items="${pagedListHolder.pageList}">
	 	<tr>
	 		<td>
	 			<input type="radio" name="radio" value="${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
	 		</td>
			<td>
				<input type="hidden" value="${bookPurchaseOrderDetailsList.updatedBy}" name="reqCode${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
				${bookPurchaseOrderDetailsList.updatedBy}
			</td>
			<td>
				<input type="hidden" value="${bookPurchaseOrderDetailsList.bookPurchaseOrderCode}" name="poCode${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
				<input type="hidden" value="${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" name="podCode${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
				${bookPurchaseOrderDetailsList.bookPurchaseOrderCode}
			</td>
			<td>
				<input type="hidden" value="${bookPurchaseOrderDetailsList.bookName}" name="bookName${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
				${bookPurchaseOrderDetailsList.bookName}
			</td>
			<td>
				<c:forEach var="message" items="${fn:split(bookPurchaseOrderDetailsList.authorName, ',')}">
					<input type="hidden" value="${message}" name="author${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
	                <c:out value="${message}" /> ; 
	            </c:forEach>
			</td>
			<td>
				<input type="hidden" value="${bookPurchaseOrderDetailsList.publisherName}" name="publisher${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
				${bookPurchaseOrderDetailsList.publisherName}
			</td>
			<td>
				<input type="hidden" value="${bookPurchaseOrderDetailsList.edition}" name="edition${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
				${bookPurchaseOrderDetailsList.edition}
			</td>
			<td>
				<input type="hidden" value="${bookPurchaseOrderDetailsList.qtyOrdered}" name="qtyOrd${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
				${bookPurchaseOrderDetailsList.qtyOrdered}
			</td>
			<td>
				<input type="hidden" value="${bookPurchaseOrderDetailsList.qtyReceived}" name="qtyRcv${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
				${bookPurchaseOrderDetailsList.qtyReceived}
			</td>
			<td>
				<input type="hidden" value="${bookPurchaseOrderDetailsList.qtyDeficit}" name="qtyDef${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
				${bookPurchaseOrderDetailsList.qtyDeficit}
			</td>
			<td>
				<input type="hidden" value="${bookPurchaseOrderDetailsList.qtyDefect}" name="qtyCat${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
				${bookPurchaseOrderDetailsList.qtyDefect}
			</td>
			<td>
				<input type="hidden" value="${bookPurchaseOrderDetailsList.amount}" name="amount${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
				${bookPurchaseOrderDetailsList.amount}
			</td>
			<td>
				<input type="hidden" value="${bookPurchaseOrderDetailsList.rate}" name="rate${bookPurchaseOrderDetailsList.purchaseOrderDetailsCode}" />
				${bookPurchaseOrderDetailsList.rate}
			</td>
			
		</tr>
	 	</c:forEach>
	 	<tr>
			<td colspan="12" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
		</tr>
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>	
	</div>	
	<input type="submit" value="Add To Catalogue" class="submitbtn" onclick="return valradio('radio','warningbox','warningmsg');">
</form>
</c:otherwise>
</c:choose>
</body>
</html>