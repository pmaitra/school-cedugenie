<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listMedFitPagination.html" var="pagedLink">
	  <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Asset List" />
<meta name="keywords" content="Asset List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Student Document Verification</title>

<link rel="stylesheet" href="/icam/css/backoffice/listMedFitCandidate.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" type="text/css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/radio.js"></script>
<script type="text/javascript" src="/icam/js/backoffice/listMedFitCandidate.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/validateSearch.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1><img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Student Document Verification
	</h1>
</div>
<div class="btnsarea01">
	<c:if test="${insertUpdateStatus ne null}">
		<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
			<span id="infomsg1">${insertUpdateStatus}</span>	
		</div>
	</c:if>
</div>
<c:choose>	
	<c:when test="${pagedListHolder eq null || empty pagedListHolder}">
		<div class="btnsarea01" >
			<div class="errorbox" id="errorbox" style="visibility: visible;">
				<span id="errormsg">Fit Candidate List Not Found</span>	
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<form:form id="getMedFitDocumentsSearchForm" name="getMedFitDocumentsSearchForm" action="getMedFitDocumentsSearchDetails.html" method="POST">
	<table id="add"  cellspacing="0" cellpadding="0" class="midsec1">
			<tr>
				<th>Search Parameter</th>
				<th>Search Value</th>
				<th>
				</th>
			</tr>
			<tr>			
				<td>
					<select id="searchKey" name="searchKey" class="defaultselect">
						<option value="">Please Select</option>
						<option value="FormId">Form Id</option>
						<option value="Standard">Standard</option>
						<option value="FirstName">First Name</option>
						<option value="MiddleName">Middle Name</option>
						<option value="LastName">Last Name</option>
						<option value="DOB">Date Of Birth</option>
					</select>
				</td>
				<td>
					<input type="text" class="textfield2" name="searchValue" id="another"/>
				</td>
				<td>
					<input type="submit" name="searchSubmit" id="search" onclick="return validateSearch('searchKey','another','warningbox','warningmsg');" value="Search" class="editbtn">
				</td>
			</tr>		
		</table>
	</form:form>
		<div id="wrap">
			<table  id="candidateTable" cellspacing="0" cellpadding="0" class="midsec1">
				<tr>
					<th>Form Id</th>
					<th>Standard</th>
					<th>Gender</th>
					<th>Name</th>
					<th>DOB</th>
					<th>Father's Name</th>
					<th>Approve/Reject</th>
				</tr>
				<c:forEach var="candidate" items="${pagedListHolder.pageList}">
					<tr>
						<td>
							${candidate.admissionFormId}
						</td>
						<td>
							${candidate.standard.standardName}
						</td>
						<td>
							<c:if test="${candidate.resource.gender eq 'M'}">
								Male
							</c:if>
							<c:if test="${candidate.resource.gender eq 'F'}">
								Female
							</c:if>
						</td>
						<td>
							${candidate.resource.name}
						</td>
						<td>
							${candidate.resource.dateOfBirth}
						</td>
						<td>
							${candidate.resource.fatherFirstName}
						</td>
						<td>
							<input type="button" name="${candidate.admissionFormId}" id="${candidate.resource.name}" class="greenbtn" value="Approve">
							<input type="button" name="${candidate.admissionFormId}" id="${candidate.resource.name}" class="submitbtn" value="Reject">
						</td>	
					</tr>
				</c:forEach>
					<tr>
		 				<td colspan="8" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/></td>
					</tr>
			</table>
		</div>
		
		
		<div id="conFirmDialog">
			<table id="ShowData" cellspacing="0" cellpadding="0" class="midsec">
				<tr>
					<th>Form Id</th>
					<td><input type="text" class="textfield1" name="confirmId" id="confirmId" readonly="readonly"></td>
				</tr>
				<tr>
					<th>Name</th>
					<td id="nameApprove"></td>
				</tr>					
				<tr >
					<th>Roll Number</th>
					<td><input type="text" class="textfield1" name="rollNumberText" id="rollNumberText"></td>
				</tr>
			</table>
			<div class="btnsarea01">
				<div class="warningbox" id="warningbox" >
					<span id="warningmsg"></span>	
				</div>
			</div>
			<button type="button" class="clearbtn" id="approveCancelButton">Cancel</button>
			<button type="button" class="editbtn" id="approveButton">APPROVE</button>
		</div>
		
		
		<div id="rejectDialog">
			<table id="ShowData" cellspacing="0" cellpadding="0" class="midsec">
				<tr>
					<th>Form Id</th>
					<td><input type="text" class="textfield1" name="rejectId" id="rejectId" readonly="readonly"></td>
				</tr>
				<tr>
					<th>Name</th>
					<td id="nameReject"></td>
				</tr>					
				<tr >
					<th>Reason Of Rejection</th>
					<td><input type="text" class="textfield1" name="reasonText" id="reasonText"></td>
				</tr>
			</table>
			<div class="btnsarea01">
				<div class="warningbox" id="warningbox1" >
					<span id="warningmsg1"></span>	
				</div>
			</div>
			<button type="button" class="clearbtn" id="rejectCancelButton">Cancel</button>
			<button type="button" class="editbtn" id="rejectButton">REJECT</button>
		</div>
		
		<form:form id="veryfyDocumentForm" name="veryfyDocumentForm" action="" method="POST">
			<input type="hidden" name="admissionFormId" id="formId">
			<input type="hidden" name="rollNumber" id="rollNumber">
			<input type="hidden" name="reasonOfRejection" id="reason">
		</form:form>
	</c:otherwise>
</c:choose>
</body>
</html>