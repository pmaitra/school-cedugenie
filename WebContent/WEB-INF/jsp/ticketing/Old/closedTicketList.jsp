<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/closedTicketListPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Closed ticket list" />
<meta name="keywords" content="Closed ticket list" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Closed ticket list</title>

<link rel="stylesheet" href="/icam/css/ticketing/closedTicketList.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/showHideField.js"></script>
<script type="text/javascript">
	window.onload=function(){
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			ShowHideField(checkbox[i].value, 'closedTicketList', checkbox[i]);
		}
	};
	function ShowAll(cb){
		if(cb.checked){
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked=true;
				ShowHideField(checkbox[i].value, 'closedTicketList', checkbox[i]);
			}
		}
		else{
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked=false;
				ShowHideField(checkbox[i].value, 'closedTicketList', checkbox[i]);
			}
		}
		
	}
</script>
</head>
<body>
<div class="ttlarea">
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Closed ticket list
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>
<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	    <h1><input type="checkbox" onclick="ShowAll(this)" />
		    <label for="Type">All</label><br>
		<input type="checkbox" class="listShowHide" value="Ticket Code" onclick="ShowHideField('Ticket Code', 'closedTicketList', this)" checked="checked" />
		    <label for="Ticket Code">Ticket Code</label><br>
		<input type="checkbox" class="listShowHide" value="Open Date" onclick="ShowHideField('Open Date', 'closedTicketList', this)" checked="checked" />
			<label for="Open Date">Open Date</label><br>
		<input type="checkbox" class="listShowHide" value="Close Date" onclick="ShowHideField('Close Date', 'closedTicketList', this)" checked="checked" />
			<label for="Close Date">Close Date</label><br>
		<input type="checkbox" class="listShowHide" value="Affected User" onclick="ShowHideField('Affected User', 'closedTicketList', this)" checked="checked" />
			<label for="Affected User">Affected User</label><br>
		<input type="checkbox" class="listShowHide" value="Reported By" onclick="ShowHideField('Reported By', 'closedTicketList', this)" />
			<label for="Reported By">Reported By</label><br>
		<input type="checkbox" class="listShowHide" value="Service Type" onclick="ShowHideField('Service Type', 'closedTicketList', this)" checked="checked" />
			<label for="Service Type">Service Type</label><br>
		<input type="checkbox" class="listShowHide" value="Description" onclick="ShowHideField('Ticket Summary', 'closedTicketList', this)" checked="checked" />
			<label for="Ticket Summary">Ticket Summary</label><br>
		<input type="checkbox" class="listShowHide" value="Status" onclick="ShowHideField('Status', 'closedTicketList', this)" />
			<label for="Status">Status</label><br>
	</h1>
</div>
<c:choose>
	<c:when test="${pagedListHolder eq null}">		
			<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
				<span id="infomsg">No Closed Ticket List Found</span>	
			</div>		
	</c:when>
<c:otherwise>
	<table id="closedTicketList" class="midsec1" cellspacing="0" cellpadding="0" >
		<tr>			
			<th>Ticket Code</th>
			<th>Open Date</th>
			<th>Close Date</th>
			<th>Affected User</th>						
			<th>Reported By</th>
			<th>Service Type</th>
			<th>Ticket Summary</th>
			<th>Status</th>
			<th>View Details</th>
		</tr>
		<c:forEach var="ticketList" items="${pagedListHolder.pageList}">
		<tr>
			<td>${ticketList.ticketCode}</td>
			<td>${ticketList.ticketOpenDate}</td>
			<td>${ticketList.ticketCloseDate}</td>
			<td>${ticketList.affectedUser}</td>
			<td>${ticketList.reportedBy}</td>		
			<td>${ticketList.ticketService.ticketServiceName}</td>
			<td>${ticketList.ticketSummary}</td>
			<td>${ticketList.status}</td>
			<td><a href="closedTicketDetails.html?ticketCode=${ticketList.ticketCode}"><input type="button" name="details" value="DETAILS" class="submitbtn"></a></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="9" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
		</tr>
	</table>
</c:otherwise>
</c:choose>
</body>
</html>