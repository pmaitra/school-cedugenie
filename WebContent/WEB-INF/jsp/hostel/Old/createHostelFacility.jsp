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
<title>Create Hostel Facility</title>

<link rel="stylesheet" href="/icam/css/hostel/createHostelFacility.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/hostel/createHostelFacility.js"></script> 
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Hostel Facility
	</h1>
</div>
<form name="hostelFacilityForm" id="hostelFacilityForm" method="post" action="addhostelFacility.html">
	<c:choose>
		<c:when test="${hostelList eq null || empty hostelList}">
			<div class="btnsarea01" >
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">Hostel Not Created Yet</span>	
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<table id="hostelDetail" class="midsec" class="midsec" cellspacing="0" cellpadding="0">
				<tr>
					<th>
						Hostel Facility Name::
					</th>
					<td>
						<input type="text" id="hostelFacilityName" name="hostelFacilityName" class="textfield1"  />
					</td>
				</tr>
			</table>
			<c:forEach var="oldHostelFaciliti" items="${hostelFacilityList}">
				<input type="hidden" name="oldHostelFacilities" value="${oldHostelFaciliti.hostelFacilityCode}">
			</c:forEach>
			<table id="hostelDetail" class="midsec1" cellspacing="0" cellpadding="0">
				<thead>
				<tr>
					<th><input type="checkbox" disabled="disabled">
					<th>Hostels ::</th>
				</tr>
				<c:forEach var="hostel" items="${hostelList}" varStatus="i">
				<tr>
					<td>
						<input type="checkbox" id="hostelCode${i.index}" name="hostelCodes" value="${hostel.hostelCode}"/>
					</td>
					<td>
						${hostel.hostelName}
					</td>
				</tr>
				</c:forEach>
				</thead>
				
			</table>
			<div class="btnsarea01">
				<input type="submit" value="Submit" class="submitbtn" onclick="return validate();" />
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