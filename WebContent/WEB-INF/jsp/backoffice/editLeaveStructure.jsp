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
<meta name="description" content="Page To Edit Leave Structure" />
<meta name="keywords" content="Page To Edit Leave Structure" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Edit Leave Structure</title>

<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/backoffice/editLeaveStructure.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/iframeHeight.js"></script>
<script type="text/javascript" src="/cedugenie/Cal/zebra_datepicker.js"></script>
<link rel="stylesheet" href="/cedugenie/Cal/default.css" type="text/css">
<script type="text/javascript" src="/cedugenie/js/backoffice/editLeaveStructure.js"></script>

</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Edit Leave Structure
	</h1>
</div>
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div> 
	
	<form name="editstruc" action="updateLeaveStructure.html" method="get">
<table id="details" class="midsec1" cellspacing="0" cellpadding="0" >
	<tr>
		<th></th>
		<th>Type of Leave</th>
		<th>Duration(Days)</th>
		<th>Encashment</th>
		<th>Limitation(At a Time)</th>
		<th>Valid Upto</th>
		<th>Leave Carry Forward</th>
	</tr>
	<c:choose>
			<c:when test="${listspecleavestructure eq null && listspecleavestructure.size() eq 0}">			
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No Leave Structure Details Found</span>	
				</div>			
			</c:when>
			<c:otherwise>			
				<c:forEach var="listspecleavestructure" items="${listspecleavestructure}">
				<tr>
					<td>
						<input type="hidden" id="leaveCode" name="leaveCode" value="<c:out value="${listspecleavestructure.leaveCode}"/>" readonly="readonly" class="textfield1"/>
					</td>
					<td>
						<input type="text" id="leaveType" name="leaveType" value="<c:out value="${listspecleavestructure.leaveName}"/>" readonly="readonly" class="textfield1"/>
					</td>
					<td>
						<input type="text" id="leaveDuration" name="leaveDuration" value="<c:out value="${listspecleavestructure.leaveDuration}"/>" class="textfield1"/>
					</td>
					<td>
						<select id="leaveEncashment" name="leaveEncashment" class="defaultselect">
							<option value="">select</option>
							<c:if test="${listspecleavestructure.leaveEncashment eq true}">
								<option selected="selected" value="true">Allowed</option>
								<option value="false">Not Allowed</option>
							</c:if>
							<c:if test="${listspecleavestructure.leaveEncashment eq false}">
								<option selected="selected" value="false">Not Allowed</option>
								<option value="true">Allowed</option>
							</c:if>
						</select>
					</td>
					<td>
						<input type="text" id="leaveLimit" name="leaveLimit" value="<c:out value="${listspecleavestructure.leaveLimit}"/>" class="textfield1"/>
					</td>
					<td>
						<input type="text" id="leaveValid" name="leaveValid" value="<c:out value="${listspecleavestructure.leaveValidUpto}"/>" class="textfield1"/>
					</td>
					<td>
						<select id="leaveCarryForward" name="leaveCarryForward" class="defaultselect">
								<option value="true" ${listspecleavestructure.leaveCarryForward eq true?'selected':''}>Yes</option>
								<option value="false" ${listspecleavestructure.leaveCarryForward eq false?'selected':''}>No</option>
						</select>
					</td>
				</tr>
				</c:forEach>
	</c:otherwise>
	</c:choose>
</table>
<div class="btnsarea01">
	<input type="submit" id="submit" name="submit" value="Submit" onclick="return validate();" class="editbtn"/>
</div>
<br>
<br>
<div class="warningbox" id="warningbox" >
	<span id="warningmsg"></span>	
</div>
</form>
</body>
</html>