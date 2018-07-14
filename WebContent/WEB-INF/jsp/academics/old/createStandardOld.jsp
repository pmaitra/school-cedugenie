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

<link rel="stylesheet" href="/icam/css/academics/createStandard.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/academics/createStandard.js"></script> 
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Standard
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
			<th>
				Sections::
			</th>
			<td>
				<input type="text" id="sections" name="sections" class="textfield1" placeholder="Comma separated values"  />
			</td>
		</tr>
	</table>
	<br>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>	
	<br>
	<div class="btnsarea01">
		<input type="button" onclick="return addStandard();" value="Submit" class="submitbtn"/>
	</div>
	<br>
	<c:choose>
	<c:when test="${standardList eq null || empty standardList}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Standard Not Created Yet</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<table id="previousStandardDetail" class="midsec1" cellspacing="0" cellpadding="0">
		<thead>
		<tr>
			<th colspan="4">
				:: Existing Standards ::
			</th>			
		</tr>
		<tr>
			<th></th>
			<th>Standards</th>
			<th>Sections</th>
			<th>New Sections</th>
		</tr>
		<c:forEach var="standard" items="${standardList}" varStatus="i">
		<tr>
			<td>
				<input type="hidden" name="oldStandardNames" value="${standard.standardCode}">
				<input type="checkbox" id="standardCode${i.index}" name="standardCode" value="${standard.standardCode}"/>
			</td>
			<td>
				<input type="text" id="standardName${i.index}" name="${standard.standardCode}Name" class="textfield1" value="<c:out value="${standard.standardName}"/>" />
			</td>
			<td>
				<c:forEach var="section" items="${standard.sectionList}" varStatus="j">
					<c:if test="${section.sectionName ne 'NA'}">${section.sectionName}</c:if>
				</c:forEach>
			</td>
			<td>
				<input type="text" id="standardSection${i.index}" name="${standard.standardCode}Section" class="textfield1" placeholder="Comma Separated Value" />
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
		<input type="button" name="editPre" value="Edit"  class="editbtn"/>
		<input type="button" onclick="return editStandard();" value="Update" class="submitbtn"/>
	</div>
	</c:otherwise>
	</c:choose>
	<br>
	<div class="infomsgbox" id="infomsgbox" >
		<span id="infomsg"></span>	
	</div>
</form>
</body>
</html>