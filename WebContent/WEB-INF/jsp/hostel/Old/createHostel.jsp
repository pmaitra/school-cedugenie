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
<title>Create Hostel</title>

<link rel="stylesheet" href="/icam/css/hostel/createHostel.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/hostel/createHostel.js"></script> 
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Hostel
	</h1>
</div>
<form name="hostelForm" id="hostelForm">
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div>
	<table id="hostelDetail" class="midsec" class="midsec" cellspacing="0" cellpadding="0">
		<tr>
			<th>
				Hostel Name::
			</th>
			<td>
				<input type="text" id="hostelName" name="hostelName" class="textfield1"  />
			</td>
		</tr>
		<tr>
			<th>
				Abbreviation ::
			</th>
			<td>
				<input type="text" id="desc" name="desc" class="textfield1"  />
			</td>
		</tr>
	</table>
	<br>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
	<br>
	<div class="btnsarea01">
		<input type="button" onclick="return addHostel();" value="Submit" class="submitbtn"/>
	</div>
	<br>
	<c:choose>
	<c:when test="${hostelList eq null || empty hostelList}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Hostel Not Created Yet</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<table id="previousStandardDetail" class="midsec1" cellspacing="0" cellpadding="0">
		<thead>
		<tr>
			<th colspan="3">
				:: Existing Hostels ::
			</th>			
		</tr>
		<tr>
			<th></th>
			<th>
				Hostel
			</th>
			<th>
				Abbreviation
			</th>	
		</tr>
		<c:forEach var="hostel" items="${hostelList}" varStatus="i">
		<tr>
			<td>
				<input type="hidden" name="oldHostelNames" value="${hostel.hostelCode}">
				<input type="checkbox" id="hostelCode${i.index}" name="hostelCodes" value="${hostel.hostelCode}"/>
			</td>
			<td>
				<input type="text" id="hostelName${i.index}" name="${hostel.hostelCode}Name" class="textfield1" value="${hostel.hostelName}" />
			</td>
			<td>
				<input type="text" id="hostelDesc${i.index}" name="${hostel.hostelCode}Desc" class="textfield1" value="${hostel.desc}" />
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
		<input type="button" onclick="return editHostel();" value="Update" class="submitbtn"/>
	</div>
	</c:otherwise>
	</c:choose>
	<div class="infomsgbox" id="infomsgbox" >
		<span id="infomsg"></span>	
	</div>
</form>
</body>
</html>