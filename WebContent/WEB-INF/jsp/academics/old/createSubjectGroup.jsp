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
<meta name="description" content="Page To Add Social Category" />
<meta name="keywords" content="Page To Add Social Category" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Subject Group</title>

<link rel="stylesheet" href="/icam/css/academics/createSubjectGroup.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/academics/createSubjectGroup.js"></script> 
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Subject Group
	</h1>
</div>
<form name="subjectGroupForm" id="subjectGroupForm">
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div>
	<table id="standardDetail" class="midsec" class="midsec" cellspacing="0" cellpadding="0">
		<tr>
			<th>
				Subject Group Name::
			</th>
			<td>
				<input type="text" id="subjectGroupName" name="subjectGroupName" class="textfield1"  />
			</td>
		</tr>
		<tr>
			<th>
				Subject Group Order::
			</th>
			<td>
				<input type="text" id="subjectGroupOrderId" name="subjectGroupOrderId" class="textfield1" value="0" onfocus="clearOrder(this);" onblur="validateOrder(this, 'warningbox', 'warningmsg');"/>
			</td>
		</tr>
	</table>
	<br>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>	
	<br>
	<div class="btnsarea01">
		<input type="button" onclick="return addSubjectGroup();" value="Submit" class="submitbtn"/>
	</div>
	<br>
	<c:choose>
	<c:when test="${subjectGroupList eq null || empty subjectGroupList}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Subject Group Not Created Yet</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<table id="previousStandardDetail" class="midsec1" cellspacing="0" cellpadding="0">
		<thead>
		<tr>
			<th colspan="3">
				:: Existing Subject Groups ::
			</th>
		</tr>
		<c:forEach var="subjectGroup" items="${subjectGroupList}" varStatus="i">
		<tr>
			<td>
				<input type="hidden" name="oldSubjectGroupNames" value="${subjectGroup.subjectGroupCode}">
				<input type="checkbox" id="subjectGroupCode${i.index}" name="subjectGroupCode" value="${subjectGroup.subjectGroupCode}"/>
			</td>
			<td>
				<input type="text" id="subjectGroupName${i.index}" name="${subjectGroup.subjectGroupCode}Name" class="textfield1" value="<c:out value="${subjectGroup.subjectGroupName}"/>" />
			</td>
			<td>
				<input type="text" id="subjectGroupOrderId${i.index}" name="${subjectGroup.subjectGroupCode}Order" class="textfield1" value="${subjectGroup.subjectGroupOrderId}" onfocus="clearOrder(this);" onblur="validateOrder(this, 'warningbox1', 'warningmsg1');"/>
			</td>
		</tr>
		</c:forEach>
		</thead>		
	</table>
	<br>
	<div class="warningbox" id="warningbox1" >
		<span id="warningmsg1"></span>	
	</div>	
	<br>
	<div class="btnsarea01">
		<input type="button" onclick="return editSubjectGroup();" value="Update" class="submitbtn"/>
	</div>
	</c:otherwise>
	</c:choose>
	<br>
	<br>
	<div class="infomsgbox" id="infomsgbox" >
		<span id="infomsg"></span>	
	</div>
</form>
</body>
</html>