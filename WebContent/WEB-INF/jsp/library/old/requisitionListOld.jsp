<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/viewRequisitionListPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Book Requisition List" />
<meta name="keywords" content="Book Requisition List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Book Requisition List</title>

<link rel="stylesheet" href="/sms/css/library/requisitionList.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />
<link href="/sms/css/common/pagination.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/library/requisitionList.js"></script>
<script type="text/javascript" src="/sms/js/common/showHideField.js"></script>
<script type="text/javascript" src="/sms/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/sms/js/common/validateSearch.js"></script>
</head>
<body>
	<div class="ttlarea">	
		<h1>
			<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;Book Requisition List
		</h1>
	</div>
	<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
		<h1>Show / Hide Columns</h1>
	</div>
	<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	        <input type="checkbox" onclick="ShowAll(this)" />
			<label for="Type">All</label><br>
	        <input type="checkbox" class="listShowHide" value="Requisition ID" onclick="ShowHideField('Requisition ID', 'reqli', this)" checked="checked" />
			<label for="Requisition ID">Requisition ID</label><br>
			<input type="checkbox" class="listShowHide" value="Open Date" onclick="ShowHideField('Open Date', 'reqli', this)" />
			<label for="Open Date">Open Date</label><br>
			<input type="checkbox" class="listShowHide" value="Status" onclick="ShowHideField('Status', 'reqli', this)" />
			<label for="Status">Status</label><br>
			<input type="checkbox" class="listShowHide" value="Receive" onclick="ShowHideField('Receive', 'reqli', this)" checked="checked" />
			<label for="Receive">Receive</label><br>
			<input type="checkbox" class="listShowHide" value="Create Purchase Order" onclick="ShowHideField('Create Purchase Order', 'reqli', this)" checked="checked" />
			<label for="Create Purchase Order">Create Purchase Order</label><br>
	</div>
<form:form method="POST" id="paydonefrm" action="searchRequisition.html">
	<c:choose>
		<c:when test="${pagedListHolder eq null}">
			<div class="btnsarea01" >
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No Book Requisition Found</span>
				</div>
			</div>
		</c:when>
	<c:otherwise>
			<table id="sitable" cellspacing="0" cellpadding="0" class="midsec1">
				<tr>
					<th colspan="3"> :: Book Requisition Details :: </th>
				</tr>
				<tr>
					<td>
						<input type="text" class="textfield" name="data" id="data" value="Search" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='Search';}" />
					</td>
					<td>
						<select name="query" id="query" class="defaultselect">
							<option value="">Please Select...</option>
							<option value="Requisition ID">Requisition ID</option>
							<option value="Open Date">Open Date</option>
							<option value="Status">Requisitions Status</option>
						</select>
					</td>
					<td>
						<input type="submit" name="SearchLodgingRequest" class="editbtn" value="Search" onclick="return validateSearch('query','data','warningbox','warningmsg');">
					</td>
				</tr>
			</table>
			<table id="reqli" cellspacing="0" cellpadding="0" class="midsec1">	
				<tr>
					<th>Requisition ID</th>
					<th>Open Date</th>
					<th>Status</th>
					<th>Receive</th>
					<th>Create Purchase Order</th>				
				</tr>
				<c:forEach var="bookRequisitionList" items="${pagedListHolder.pageList}"> 
					<tr>
						<td>	
							${bookRequisitionList.bookRequisitionCode}					
						</td>
						<td>
							${bookRequisitionList.bookRequisitionOpenDate}	
						</td>
						<td>
							${bookRequisitionList.bookRequisitionStatus}										 	
						</td>								
						<c:choose>
							<c:when test="${bookRequisitionList.purchaseOrderCreated eq true}">
								<td>
									<a href="listPurchaseOrder.html?requisitionCode=${bookRequisitionList.bookRequisitionCode}">List Purchase Order</a>
								</td>
								<td>
									Purchase Order Created
								</td>
							</c:when>
							<c:otherwise>
								<td>
									Purchase Order Not Created
								</td>
								<td>
									<a href="createPurchaseOrderForBookRequisition.html?requisitionCode=${bookRequisitionList.bookRequisitionCode}&requisitionStatus=${bookRequisitionList.bookRequisitionStatus}">Create Purchase Order</a>
								</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5" id="toolbar">
						<c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
					</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
	</div>
</form:form>
</body>
</html>