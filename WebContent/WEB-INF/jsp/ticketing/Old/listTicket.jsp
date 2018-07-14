<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listTicketPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to List Ticket" />
<meta name="keywords" content="List Ticket" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>List Ticket</title>

<link rel="stylesheet" href="/icam/css/ticketing/listTicket.css" />
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
			ShowHideField(checkbox[i].value, 'tickList', checkbox[i]);
		}
	};
	function ShowAll(cb){
		if(cb.checked){
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked=true;
				ShowHideField(checkbox[i].value, 'tickList', checkbox[i]);
			}
		}
		else{
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked=false;
				ShowHideField(checkbox[i].value, 'tickList', checkbox[i]);
			}
		}
		
	}
	
	function validateTicketSearch(){	
		var data = document.getElementById("data").value;	
		if(data == 0){
			return false;
		}else{
			return true;
		}
	}
</script>

</head>
<body>
<div class="ttlarea">
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;List Ticket
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>
<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>
	<input type="checkbox" onclick="ShowAll(this)" />
		<label for="Type">All</label><br>
		<input type="checkbox" class="listShowHide" value="Ticket Code" onclick="ShowHideField('Ticket Code', 'tickList', this)" checked="checked" />
			<label for="Ticket Code">Ticket Code</label><br>
	    <input type="checkbox" class="listShowHide" value="Open Date" onclick="ShowHideField('Open Date', 'tickList', this)" checked="checked" />
			<label for="Open Date">Open Date</label><br>
	    <input type="checkbox" class="listShowHide" value="Affected User" onclick="ShowHideField('Affected User', 'tickList', this)" checked="checked" />
			<label for="Affected User">Affected User</label><br>
	    <input type="checkbox" class="listShowHide" value="Reported By" onclick="ShowHideField('Reported By', 'tickList', this)" checked="checked" />
			<label for="Reported By">Reported By</label><br>
	    <input type="checkbox" class="listShowHide" value="Service Type" onclick="ShowHideField('Service Type', 'tickList', this)" checked="checked" />
			<label for="Service Type">Service Type</label><br>
	    <input type="checkbox" class="listShowHide" value="Status" onclick="ShowHideField('Status', 'tickList', this)" checked="checked"/>
			<label for="Status">Status</label><br>
		<input type="checkbox" class="listShowHide" value="Summary" onclick="ShowHideField('Summary', 'tickList', this)" />
			<label for="Summary">Summary</label><br>
	</h1>
</div>



<form name="" method="post" action="editTicket.html">
	<table cellspacing="0" cellpadding="0" class="midsec1">	
		<tr>
			<th colspan="3">Status Based Search</th>		
		</tr>			
		<tr>
			<td colspan="2">
				<select name="data" id="data" class="defaultselect">					 
	 				<option value="0" selected>Please Select...</option>
					<option value="OPEN">OPEN</option>
					<option value="ACKNOWLEDGED">ACKNOWLEDGED</option>
					<option value="AWAITING_USER_INPUT">AWAITING_USER_INPUT</option>
					<option value="USER_INPUT_RECEIVED">USER_INPUT_RECEIVED</option>
					<option value="WORK_IN_PROGRESS">WORK_IN_PROGRESS</option>				
					<option value="REJECTED">REJECTED</option>
					<option value="ACCEPTED">ACCEPTED</option>
					<option value="TRANSFER">TRANSFER</option>																	 
	 			</select> 
			</td>
			<td>		
				<input type="submit" name="ticketSearchSubmit" class="editbtn" value="Search" onclick="return validateTicketSearch();"/>				
			</td>
		</tr>
	</table>
</form>
<c:choose>
	<c:when test="${pagedListHolder eq null}">		
		<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
			<span id="infomsg">No Ticket List Found</span>	
		</div>		
	</c:when>
<c:otherwise>
	<table id="tickList" class="midsec1" cellspacing="0" cellpadding="0" >
		<tr>			
			<th>Ticket Code</th>
			<th>Open Date/Time</th>	
			<th>Affected User</th>						
			<th>Reported By</th>
			<th>Service Type</th>
			<th>Summary</th>			
			<th>Status</th>
			<th>View Details</th>
		</tr>
		<c:forEach var="ticketList" items="${pagedListHolder.pageList}">
		<tr>			
			<td><c:out value="${ticketList.ticketCode}"></c:out></td>
			<td><c:out value="${ticketList.ticketOpenDate}"></c:out></td>
			<td><c:out value="${ticketList.affectedUser}"></c:out></td>
			<td><c:out value="${ticketList.reportedBy}"></c:out></td>		
			<td><c:out value="${ticketList.ticketService.ticketServiceName}"></c:out></td>			
			<td><c:out value="${ticketList.ticketSummary}"></c:out></td>		
			<td><c:out value="${ticketList.status}"></c:out></td>
			<td><a href="editTicket.html?ticketCode=${ticketList.ticketCode}"><input type="button" name="details" value="DETAILS" class="submitbtn"></a></td>
			
		</tr>
		</c:forEach>
		<tr>
			<td colspan="8" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
		</tr>
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
	</div>
</c:otherwise>
</c:choose>
</body>
</html>