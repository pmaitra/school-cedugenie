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
<title>Create Scholarship</title>

<link rel="stylesheet" href="/icam/css/backoffice/createScholarship.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/backoffice/createScholarship.js"></script> 
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Scholarship
	</h1>
</div>
<form name="feesForm" id="feesForm">
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div>
	<table id="feesDetail" class="midsec" class="midsec" cellspacing="0" cellpadding="0">
		<tr>
			<th>
				Scholarship Name ::
			</th>
			<td>
				<input type="text" id="scholarshipName" name="scholarshipName" class="textfield1"  />
			</td>
		</tr>
		<tr>
			<th>
				Concession ::
			</th>
			<td>
				<input type="text" id="concession" name="concession" class="textfield1" value="0.00" />
			</td>
		</tr>
		<tr>
			<th>
				Concession Unit ::
			</th>
			<td>
				<select id="concessionUnit" name="concessionUnit" size="1" class="defaultselect">					
					<option value="%">%</option>
					<option value="INR">INR</option>
				</select>
			</td>
		</tr>
	</table>
	<br>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>	
	<br>
	<div class="btnsarea01">
		<input type="button" onclick="return addScholarship();" value="Submit" class="submitbtn"/>
	</div>
	<br>
	<c:choose>
	<c:when test="${scholarshipList eq null || empty scholarshipList}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Scholarship Not Created Yet</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<table id="previousFeesDetail" class="midsec1" cellspacing="0" cellpadding="0">
		<thead>
		<tr>
			<th colspan="4">
				:: Existing Scholarships ::
			</th>
		</tr>
		<tr>
			<th></th><th>Scholarship Name</th><th>Concession</th><th>Unit</th>
		</tr>
		<c:forEach var="scholarship" items="${scholarshipList}" varStatus="i">
			<tr>
				<td>
					<input type="hidden" name="oldScholarshipNames" value="${scholarship.scholarshipCode}">
					<input type="checkbox" id="code${i.index}" name="scholarshipCodes" value="${scholarship.scholarshipCode}"/>
				</td>
				<td>
					<input type="text" id="name${i.index}" name="${scholarship.scholarshipCode}Name" class="textfield1" value="<c:out value="${scholarship.scholarshipName}"/>" />
				</td>
				<td>
					<input type="text" id="concession${i.index}" name="${scholarship.scholarshipCode}Concession" class="textfield2" value="<c:out value="${scholarship.concession}"/>" />
				</td>
				<td>
					<select id="unit${i.index}" name="${scholarship.scholarshipCode}Unit" size="1" class="defaultselect">
						<c:choose>						
							<c:when test="${scholarship.concessionUnit eq '%'}">						
								<option value="%" selected="selected">%</option>
								<option value="INR">INR</option>
							</c:when><c:otherwise>						
								<option value="INR" selected="selected">INR</option>
								<option value="%">%</option>
							</c:otherwise>
						</c:choose>
					</select>
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
		<input type="button" onclick="return editScholarship();" value="Update" class="submitbtn"/>
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