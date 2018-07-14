<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="description" content="Page to Create / List Ticketing SLA" />
<meta name="keywords" content="Create / List Ticketing SLA" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create / View Ticketing SLA</title>
<link rel="stylesheet" href="/icam/css/administrator/viewTicketingSLA.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/js/common/multipleSelect.js"></script>
<script type="text/javascript" src="/icam/js/administrator/viewTicketingSLA.js"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create / View Ticketing SLA
		</h1>	
</div>
<c:if test="${errorMessage ne null}">
		<div id="errorbox" class="errorbox" style="visibility:visible;">
			<span class="errormsg">${errorMessage}</span>
		</div>
</c:if>
<c:if test="${updateSuccessStatus ne null}">
		<div id="successbox" class="successbox" style="visibility:visible;">
			<span class="successmsg">${updateSuccessStatus}</span>
		</div>
</c:if>

<form action="createTicketingSLA.html" method="post">
<input type="hidden" id="numberOfRows" name="numberOfRows" value="1">
	<table id="sectionTable" cellspacing="0" cellpadding="0" class="midsec1">
		<tr>			
			<th>Module Name<img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<th>Status<img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<th>Maximum Days Granted<img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<th>Delete</th>
		</tr>
		<tr>			
			<td>
				<input type="text" name="moduleName1" id="w0" readonly="readonly" onmouseover="showDiv('Div0');" onmouseout="hideDiv('Div0');" class="moduleName" /><br>
				<div id="Div0" class="multipleSelectDiv" onmouseout="hideDiv('Div0');" onmouseover="showDiv('Div0');">
					<input type="checkbox" value="${moduleListStr}" id="allCheck" onclick="checkAll(this,'Div0clsName','allCheck','w0','x0','y0');"/>Select All
					<c:forEach var="module" items="${moduleList}">
						<span>
							<input type="checkbox" value="${module.moduleName}" name="Div0clsName" onclick="addText(this,'w0');"/>${module.moduleName}
						</span>
					</c:forEach>
				</div>
			</td>
			<td>
				<select name="status1" id="status" class="status">
					<option value="">Select...</option>
					<c:forEach var="ticketStatus" items="${ticketStatusList}">
						<option value="${ticketStatus.ticketStatusName}">${ticketStatus.ticketStatusName}</option>
					</c:forEach>
				</select>
			</td>
			<td>				
				<input type="text" name="ticketMaxDays" class="ticketMaxDays" class="textfield2"/>
			</td>
			<td>
				&thinsp;&thinsp;<img src="/icam/images/minus_icon.png" onclick="deleteRow(this);">
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="button" class="addbtn" onclick="addrows();">
			</td>
		</tr>
	</table>
	<div class="btnsarea01">
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
		<input type="submit" class="submitbtn" value="SUBMIT"  onclick="return validate();"/>
	</div>
</form>
<br/>
<c:if test="${ticketList ne null && !empty ticketList }">
<form action="updateTicketingSLA.html" method="post">
	<table id="sectionTableDB" cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<th>Select<img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<th>Module Name</th>
			<th>Status</th>
			<th>Maximum Days Granted</th>			
		</tr>
		<c:forEach var="ticket" items="${ticketList}" varStatus="i">
			<tr>
				<td>
					<input type="radio" id="${i.index}" name ="serialNoDB" value="${i.index}">
				</td>
				<td>
					<input type="text" class="textfield2" id="moduleName-${i.index}" name="moduleName${i.index}" value="${ticket.moduleName}" readonly="readonly"/>
				</td>	
				<td>
					<select id="ticketStatus${i.index}" name="status${i.index}" disabled="disabled" class="defaultdropdown">
					<c:forEach var="ticketStatus" items="${ticketStatusList}">
					<c:choose>
						<c:when test="${ticketStatus.ticketStatusName eq ticket.ticketSummary}">
							<option value="${ticketStatus.ticketStatusName}" selected="selected">${ticketStatus.ticketStatusName}</option>
						</c:when>
						<c:otherwise>
							<option value="${ticketStatus.ticketStatusName}">${ticketStatus.ticketStatusName}</option>
						</c:otherwise>
					</c:choose>					
					</c:forEach>										
					</select>
				</td>
				<td>
					<input type="text" class="textfield2" id="maxDays${i.index}" name="ticketMaxDays${i.index}" value="${ticket.ticketMaxDays}" readonly="readonly"/>
				</td>				
			</tr>
		</c:forEach>		
	</table>
	<div class="btnsarea01">
		<div class="infomsgbox" id="infomsgbox" >
			<span id="infomsg"></span>	
		</div>
		<div class="warningbox" id="warningbox1" >
			<span id="warningmsg1"></span>	
		</div>
		<input type="button" id="edit" class="editbtn" onclick="editRow('serialNoDB','warningbox1','warningmsg1');" value="EDIT" />
		<input type="submit" value="SUBMIT" class="submitbtn" onclick="return validateDB();">	
	</div>
</form>
	</c:if>
</body>
</html>