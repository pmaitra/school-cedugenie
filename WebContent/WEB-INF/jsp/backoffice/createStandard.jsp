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
<title>Add Standard</title>

<link rel="stylesheet" href="/cedugenie/css/backoffice/createStandard.css" />
<link href="/cedugenie/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/cedugenie/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
<link href="/cedugenie/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/backoffice/createStandard.js"></script> 
<script type="text/javascript" src="/cedugenie/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/cedugenie/images/titleicon01.png" alt="" />&nbsp;&nbsp;Add Standard
	</h1>
</div>
<form name="standardForm" id="standardForm">
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
				Standard Name::
			</th>
			<td>
				<input type="text" id="standardName" name="standardName" class="textfield1"  />
			</td>
		</tr>
	</table>
	<div class="btnsarea01">
		<input type="button" onclick="return addStandard();" value="Submit" class="submitbtn"/>
	</div>
	<br>
	<c:choose>
	<c:when test="${standardList eq null || empty standardList}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Fees Not Created Yet</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<table id="previousStandardDetail" class="midsec1" cellspacing="0" cellpadding="0">
		<thead>
		<tr>
			<th colspan="2">
				:: Existing Standards ::
			</th>			
		</tr>
		<c:forEach var="standard" items="${standardList}" varStatus="i">
		<tr>
			<td>
				<input type="hidden" name="oldFeesNames" value="${standard.standardCode}">
				<input type="checkbox" id="feesCode${i}" name="standardCode" value="${standard.standardCode}"/>
			</td>
			<td>
				<input type="text" id="standardName${i}" name="${standard.standardCode}Name" class="textfield1" value="<c:out value="${standard.standardName}"/>" />
			</td>
		</tr>
		</c:forEach>
		</thead>
		
	</table>
	<div class="btnsarea01">
		<input type="button" name="editPre" value="Edit"  class="editbtn"/>
		<input type="button" onclick="return editStandard();" value="Update" class="submitbtn"/>
	</div>
	</c:otherwise>
	</c:choose>
	<br>
	<br>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>	
	<br>
	<br>
	<div class="infomsgbox" id="infomsgbox" >
		<span id="infomsg"></span>	
	</div>
</form>
</body>
</html>