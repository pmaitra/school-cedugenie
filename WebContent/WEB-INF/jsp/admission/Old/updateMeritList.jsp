<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Update Merit List for admission" />
<meta name="keywords" content="Generate Merit List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Update Merit List</title>
<link rel="stylesheet" href="/icam/css/admission/updateMeritList.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />


<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>

<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">
<link rel="stylesheet" href="/icam/Cal/jsDatePick_ltr.min.css">
<script src="/icam/Cal/jsDatePick.min.1.3.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Update Merit List
		</h1>
</div>
<c:if test="${successStatus != null}">
	<div class="successbox" id="successbox" style="visibility:visible;">
		<span id="successmsg" style="visibility:visible;">Successfully Updated!</span>	
	</div>
</c:if>
<c:if test="${failStatus != null}">
		<div class="errorbox" id="errorbox" style="visibility: visible;">
			<span id="errormsg">Update Fail!</span>	
		</div>
</c:if>
<form:form name="updateMeritList" method="POST" action="updateMeritList.html">		
	<table cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th>Class</th>
		<td >
			<select name="standard.standardName" id="standard" class="defaultselect">
				<option value="${candidate.standard.standardName}">${candidate.standard.standardName}</option>
			</select>
		</td>	
		<th>Merit List Type</th>
		<td >
			<select name="meritListType.meritListTypeName" id="meritListType" class="defaultselect">
				<option value="${candidate.meritListType.meritListTypeName}">${candidate.meritListType.meritListTypeName}</option>
			</select>
		</td>	
	</tr>
	<tr>
		<th>Form ID</th>
		<td><input type="text" class="textfield2" value="${candidate.admissionFormId}" id="admissionFormId" name="admissionFormId" readonly="readonly"/></td>
		<th>Selection Status</th>
		<td >
			<c:if test="${candidate.status eq 'SELECTED'}">
				Selected
			</c:if>
			<c:if test="${candidate.status eq 'REVIEW'}">
				Under Review
			</c:if>
			<c:if test="${candidate.status eq 'REJECTED'}">
				Rejected
			</c:if>
			<c:if test="${candidate.status eq 'QUEUED'}">
				Waiting
			</c:if>
		</td>
	</tr>
	</table>

	<table id="tabSubmitMeritList" cellpadding="0" class="midsec">
			
		<tr>
			<th>Candidate Name</th><td id="tdCandidateName">${candidate.resource.name}</td>
		</tr>
		<tr>
			<th>Date Of Birth </th><td id="tdDateOfBirth">${candidate.resource.dateOfBirth}</td>
			<th>Gender </th><td id="tdGender">${candidate.resource.gender}</td>
		</tr>
		<tr>
			<th>Category</th><td id="tdCategory">${candidate.resource.category}</td>
		</tr>
		<tr>
				<th colspan="2">
					<input class="greenbtn" type="submit" id="selected" name="selected"  value="SELECTED"/>
					<c:if test="${candidate.status eq 'REJECTED' or candidate.status eq 'QUEUED'}">
						<input class="clearbtn" type="submit" id="review" name="review"  value="REVIEW"/>
					</c:if>
					<c:if test="${candidate.status eq 'REVIEW' or candidate.status eq 'QUEUED'}">
						<input class="submitbtn" type="submit" id="rejected" name="rejected"  value="REJECTED"/>
					</c:if>
					
				</th>
		</tr>
	</table>
</form:form>
<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
</div>

</body>
</html>