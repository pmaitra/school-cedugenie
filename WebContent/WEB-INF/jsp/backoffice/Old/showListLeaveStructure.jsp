<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/showListLeaveStructurePagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page To Show List Leave Structure" />
<meta name="keywords" content="Page To Show List Leave Structure" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>List Leave Structure</title>

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/backoffice/listLeaveStructure.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">
<script type="text/javascript" src="/icam/js/backoffice/listLeaveStructure.js"></script>

</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;List Leave Structure
	</h1>
</div>
<div style="background-color: #99FF66; margin-top: 2px;" onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<h1>Show / Hide Columns</h1>
</div>

<div id="columnsDiv" style="background-color: #99FF66; position:fixed; visibility: collapse; " onmouseover="showColumnsDiv();" onmouseout="hideColumnsDiv();">
	<input type="checkbox" onclick="ShowAll(this)" />
		<label for="Type">All</label><br>
	<input type="checkbox" class="listShowHide" value="Academic Year" onclick="ShowHideField('Academic Year', 'details', this)" checked="checked" />
		<label for="Type">Year</label><br>
    <input type="checkbox" class="listShowHide" value="Type of Leave" onclick="ShowHideField('Type of Leave', 'details', this)" checked="checked" />
		<label for="Code">Type Leave</label><br>
    <input type="checkbox" class="listShowHide" value="Duration(Days)" onclick="ShowHideField('Duration(Days)', 'details', this)" checked="checked" />
		<label for="Name">Duration(Days)</label><br>
    <input type="checkbox" class="listShowHide" value="Encashment" onclick="ShowHideField('Encashment', 'details', this)" />
		<label for="Edition">Encashment</label><br>
    <input type="checkbox" class="listShowHide" value="Limitation(At a Time)" onclick="ShowHideField('Limitation(At a Time)', 'details', this)" checked="checked" />
		<label for="Medium">Limitation(At a Time)</label><br>
    <input type="checkbox" class="listShowHide" value="Valid Upto" onclick="ShowHideField('Valid Upto', 'details', this)" checked="checked" />
		<label for="ISBN">Valid Upto</label>
</div>

<form name="struc">
<c:choose>
	<c:when test="${pagedListHolder eq null}">
		<div class="errorbox" id="errorbox" style="visibility: visible;">
			<span id="errormsg">No Leave Type Found</span>	
		</div>	
	</c:when>
<c:otherwise>
<table id="details" class="midsec1" cellspacing="0" cellpadding="0">
	<tr>
		<th>Select</th>
		<th>Academic Year</th>
		<th>Type of Leave</th>
		<th>Duration(Days)</th>
		<th>Encashment</th>
		<th>Limitation(At a Time)</th>
		<th>Valid Upto</th>
	</tr>
				
		
		<c:forEach var="listStructure" items="${pagedListHolder.pageList}">
	<tr>
		<td>
			<input type="radio" id="code" name="code" value="<c:out value="${listStructure.leaveCode}"/>"/>
		</td>
		<td>
			${listStructure.leaveDesc}
			<input type="hidden" id="leaveYear" name="leaveYear" value="<c:out value="${listStructure.leaveDesc}"/>" />
		</td>
		<td>
			${listStructure.leaveName}
			<input type="hidden" id="leaveName" name="leaveName" value="<c:out value="${listStructure.leaveName}"/>" />
		</td>
		<td>
			${listStructure.leaveDuration}
			<input type="hidden" id="leaveDuration" name="leaveDuration" value="<c:out value="${listStructure.leaveDuration}"/>" />
		</td>
		<td>
			${listStructure.leaveEncashment}
			<input type="hidden" id="leaveEncashment" name="leaveEncashment" value="<c:out value="${listStructure.leaveEncashment}"/>" />
		</td>
		<td>
			${listStructure.leaveLimit}
			<input type="hidden" id="leaveLimit" name="leaveLimit" value="<c:out value="${listStructure.leaveLimit}"/>" />
		</td>
		<td>
			${listStructure.leaveValidUpto}
			<input type="hidden" id="leaveValid" name="leaveValid" value="<c:out value="c"/>" />
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="7" id="toolbar">
			<c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
		</td>
	</tr>
</table>
<div class="btnsarea01">
	<input type="submit" id="edit" name="edit" value="Edit" class="editbtn"/>
	<input type="submit" id="back" name="back" value="Back" class="submitbtn" onclick="showone();"/>
</div>
</c:otherwise>
</c:choose>

<br>
<br>
<div class="warningbox" id="warningbox" >
	<span id="warningmsg"></span>	
</div>
</form>
</body>
</html>