<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Payment Details" />
<meta name="keywords" content="Payment Details" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Payment Details</title>

<link rel="stylesheet" href="/icam/css/admission/admissionFormStatus.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/icam/Cal/default.css"/>


</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Form Status
	</h1>
</div>

		<form:form method="POST" id="vmlform" action="getAdmissionFormStatusDetails.html" >
			<table cellspacing="0" cellpadding="0" class="midsec">
				<tr>
					<th>Form Id :: </th>
					<td><input class="textfield1" type="text" name="admissionFormId" value=""/> </td>
					<td><input class="submitbtn" type="submit" id="submit" name="submit"  value="Submit"/></td>
				</tr>
			</table>
		</form:form>
	<c:if test="${status==null}">
		<c:choose>
			<c:when test="${status==null && candidate == null}">
				<div class="errorbox" id="errorbox" style="visibility: visible;" >
					<span id="errormsg">Form Status details not found. </span>	
				</div>
			</c:when>
		<c:otherwise>
			<table cellspacing="0" cellpadding="0" class="midsec">
				<tr>
					<th>Form Id :: </th><td  colspan="2">${candidate.admissionFormId}</td>
				</tr>
				<tr>
					<th>Candidate Name :: </th><td colspan="2">${candidate.resource.name}</td>
				</tr>
				<tr>
					<th>Candidate's Father Name :: </th><td>${candidate.resource.fatherFirstName}</td>
				</tr>
				<tr>
					<th>Gender :: </th><td>${candidate.resource.gender}</td><th>Date of Birth :: </th><td>${candidate.resource.dateOfBirth}</td>
				</tr>
				<tr>
					<th>Class :: </th><td>${candidate.standard.standardName}</td><th>Admission Session :: </th><td>${candidate.academicYear.academicYearName}</td>
				</tr>
				<tr>
					<th>Status :: </th>
					<td colspan="2">
						<c:if test="${candidate.status == 'SUBMITTED'}">
							Form generated. Yet to receive at school.
						</c:if>
						<c:if test="${candidate.status == 'RECEIVED'}">
							Form Received. 
						</c:if>
						<c:if test="${candidate.status == 'ACCEPTED'}">
							Application Accepted. 
						</c:if>
						<c:if test="${candidate.status == 'REJECTED'}">
							Application Not Accepted. 
						</c:if>
						<c:if test="${candidate.status == 'GENERATED'}">
							Admit Card Dispatched.
						</c:if>
					</td>
				</tr>
			</table>
		</c:otherwise>
		</c:choose>
	</c:if>
	
<%-- 	<img src="data:image/jpg;base64, ${imageB64}"  alt="Image not found"  /> --%>
</body>

</html>
		