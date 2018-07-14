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
<meta name="description" content="Page to Create Dispatch Admit Card for admission" />
<meta name="keywords" content="Generate Merit List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Generate Merit List</title>
<link rel="stylesheet" href="/icam/css/admission/generateMeritList.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">
<link rel="stylesheet" href="/icam/Cal/jsDatePick_ltr.min.css">

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script src="/icam/Cal/jsDatePick.min.1.3.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
<script src="/icam/js/admission/generateMeritList.js"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Generate Merit List
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
<form:form id="getCandidateForGenerateMeritListForm" name="getCandidateForGenerateMeritListForm" method="POST" action="getCandidatesForGenerateMerit.html">		
	<table cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th>Class<img class="required" src="/icam/images/required.gif" alt="Required"></th>
		<td >
			<select name="standard.standardCode" id="standard" class="defaultselect">
				<option value="">Select...</option>
				<c:forEach var="standard" items="${standardList}">
					<option value="<c:out value="${standard.standardCode}"/>"><c:out value="${standard.standardName}"/></option>
				</c:forEach>
			</select>
		</td>	
		<th>Merit List Type<img class="required" src="/icam/images/required.gif" alt="Required"></th>
		<td >
			<select name="meritListType.meritListTypeCode" id="meritListType" class="defaultselect">
				<option value="">Select...</option>
				<c:forEach var="meritListType" items="${meritListTypes}">
					<option value="<c:out value="${meritListType.meritListTypeCode}"/>"><c:out value="${meritListType.meritListTypeCode}"/></option>
				</c:forEach>
			</select>
		</td>	
	</tr>
	</table>
</form:form>


<c:if test="${admissionForm != null}">
	<form:form name="submitMeritList" method="POST" action="submitMeritList.html">
		<table cellspacing="0" cellpadding="0" class="midsec1">
			<c:choose>
				<c:when test="${admissionForm.candidateList == null || empty admissionForm.candidateList}">
				
					<div class="btnsarea01" style="visibility: visible;">
					 
						<div class="infomsgbox"  id="infomsgbox"  style="visibility: visible;">
							<span id="infomsg">No candidate found for ${admissionForm.meritListType.meritListTypeCode} requested for class ${admissionForm.standard.standardCode}</span>	
						</div>
					</div>
				</c:when>
				<c:otherwise>
				<tr>
						<td colspan="7">
							<input class="greenbtn" type="submit" id="upperSubmit" name="upperSubmit"  value="submit"/>
							<input class="submitbtn" type="reset" id="upperReset" name="upperReset"  value="Reset"/>
						</td>
				</tr>
				<tr>								
						<th>Class: ${admissionForm.standard.standardCode} <input type="hidden"  name="standard.standardCode" value="${admissionForm.standard.standardCode}"></th>
						<th></th>
						<th></th>
						<th></th>
						<th>Requested Merit List Type: ${admissionForm.meritListType.meritListTypeCode} <input type="hidden"  name="meritListType.meritListTypeCode" value="${admissionForm.meritListType.meritListTypeCode}"></th>
						<th></th>
				</tr>
				<tr>								
							<th>Name</th>
							<th>Form Id</th>
							<th>Date Of Birth</th>	
							<th>State</th>	
							<th>Status</th>
							<th></th>
						</tr>
						<c:forEach var = "can" items="${admissionForm.candidateList}" varStatus="row">
							<tr>
								<td><c:out value="${can.candidateName}"></c:out></td>
								<td onClick="window.open('getCandidateDetails.html?formId=${can.admissionFormId}','_self')" style="cursor:pointer;"><c:out value="${can.admissionFormId}-${can.examCentreAlias}"></c:out>
								<input type="hidden" id="formId${row.index}" name="candidateList[${row.index}].admissionFormId" value="${can.admissionFormId}">
								</td>		
								<td><c:out value="${can.resource.dateOfBirth}"></c:out></td>
								<td><c:out value="${can.address.presentAddressState}"></c:out></td>
								<td>
									<c:if test="${admissionForm.meritListType.meritListTypeCode eq 'EXAM'}">
										<input  type="radio" id="selectedStatus${row.index}" name="candidateList[${row.index}].status"  value="SELECTED"/>Selected
										<input  type="radio" id="reviewStatus${row.index}" name="candidateList[${row.index}].status"  value="REVIEW"/>Under Review
									 	<input  type="radio" id="rejectedStatus${row.index}" name="candidateList[${row.index}].status"  value="REJECTED"/>Rejected
		    						</c:if>
									<c:if test="${admissionForm.meritListType.meritListTypeCode eq 'PI AND MEDICAL'}" >
										<input  type="radio" id="waitingStatus${row.index}" name="candidateList[${row.index}].status"  value="QUEUED"/>Waiting
		        						<input  type="radio" id="selectedStatus${row.index}" name="candidateList[${row.index}].status"  value="SELECTED"/>Selected
										<input  type="radio" id="reviewStatus${row.index}" name="candidateList[${row.index}].status"  value="REVIEW"/>Under Review
									 	<input  type="radio" id="rejectedStatus${row.index}" name="candidateList[${row.index}].status"  value="REJECTED"/>Rejected
		    						</c:if>
								</td>
								<td>
									<input type="button" id="reset" name="recet" value="Reset" onclick="resetCandidateStatus(${row.index},'${admissionForm.meritListType.meritListTypeCode}');"/>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="7">
								<input class="greenbtn" type="submit" id="lowerSubmit" name="lowerSubmit"  value="submit"/>
								<input class="submitbtn" type="reset" id="lowerReset" name="lowerReset"  value="Reset"/>
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>		
	</form:form>
</c:if>

<div class="infomsgbox" id="infomsgbox" >
			<span id="infomsg"></span>	
	</div>		
	<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
	</div>

</body>
</html>