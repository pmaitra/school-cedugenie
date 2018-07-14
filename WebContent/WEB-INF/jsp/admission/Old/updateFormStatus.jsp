<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Update Form Status for admission" />
<meta name="keywords" content="Generate Merit List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Update Form Status</title>
<link rel="stylesheet" href="/icam/css/admission/updateFormStatus.css" />
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
<script src="/icam/js/admission/updateFormStatus.js"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Update Form Status
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
<form:form name="formStatus" method="POST" action="getAdmissionFormsForStatusUpdate.html">		
	<table cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<td>Class<img class="required" src="/icam/images/required.gif" alt="Required"></td>
			<td>
				<select name="standard.standardName" id="standard" class="defaultselect">
					<option value="">Select...</option>
					<c:forEach var="standard" items="${standardList}">
						<option value="<c:out value="${standard.standardCode}"/>"><c:out value="${standard.standardName}"/></option>
					</c:forEach>
				</select>
			</td>
			
			<td>Status<img class="required" src="/icam/images/required.gif" alt="Required"></td>
			<td>
				<select name="status" id="status" class="defaultselect">
					<option value="">Select...</option>
						<option value="SUBMITTED">Submitted Form</option>
						<option value="RECEIVED">Received Form</option>
				</select>
			</td>
			<td >
				<input class="greenbtn" type="submit" id="submit" name="submit"  value="submit"  onclick="return validateUpdateFormStatusForm();"/>
			</td>
		</tr>
		
	</table>
</form:form>

<c:if test="${admissionForm != null}">

<form:form name="formStatus" method="POST" action="submitUpdateFormStatus.html">
	<table cellspacing="0" cellpadding="0" class="midsec1">
		
		<c:choose>
			<c:when test="${admissionForm.candidateList == null || empty admissionForm.candidateList}">
				<div class="btnsarea01" style="visibility: visible;">
					<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
						<span id="infomsg">No candidate found for ${admissionForm.status eq 'SUBMITTED' ? 'Submitted Form' : 'Received Form'} Request for class ${admissionForm.standard.standardName}</span>	
					</div>
				</div>
			</c:when>
			<c:otherwise>
			<tr>
						<td colspan="7">
							<input class="greenbtn" type="submit" id="upperSubmit" name="upperSubmit"  value="submit"/>
						</td>
					</tr>
			<tr>								
					<th>Class: </th><th>${admissionForm.standard.standardName}<input type="hidden"  name="standard.standardName" value="${admissionForm.standard.standardName}"> </th>
					<th colspan="1">Requested Status: </th><th colspan="4">${admissionForm.status eq 'SUBMITTED' ? 'Submitted Form' : 'Received Form'}</th>
			</tr>
			<tr>								
						<th>Form Id</th>
						<th>Name</th>
						<th>Date Of Birth</th>	
						<th>State</th>	
						<th>${admissionForm.status eq 'SUBMITTED' ? 'Receive Status' : 'Acceptance Status'}</th>
						<th colspan="2">Remarks</th>									
					</tr>
					<c:forEach var = "can" items="${admissionForm.candidateList}" varStatus="row">
						<tr>
							<td onClick="window.open('getCandidateDetails.html?formId=${can.admissionFormId}','_self')" style="cursor:pointer;"><c:out value="${can.admissionFormId}"></c:out>
							<input type="hidden" id="formId${row.index}" name="candidateList[${row.index}].admissionFormId" value="${can.admissionFormId}">
							</td>		
							<td><c:out value="${can.candidateName}"></c:out></td>		
							<td><c:out value="${can.resource.dateOfBirth}"></c:out></td>
							<td><c:out value="${can.address.presentAddressState}"></c:out></td>
							<td>
								<c:if test="${admissionForm.status eq 'SUBMITTED'}">
	        						<input type="radio" id="receivedStatus${row.index}" name="candidateList[${row.index}].status" value="RECEIVED">Received
	    						</c:if>
								<c:if test="${admissionForm.status eq 'RECEIVED'}" >
	        						<input type="radio"  id="acceptedStatus${row.index}" name="candidateList[${row.index}].status" value="ACCEPTED">Accepted
	        						<input type="radio" id="rejectedStatus${row.index}"  name="candidateList[${row.index}].status" value="REJECTED">Rejected
	    						</c:if>
							</td>
							<td>
								<input type="text" class="textfield2" id="reasonOfRejection${row.index}" name="candidateList[${row.index}].reasonOfRejection" value="" />
							</td>
							<td>
								<input type="button" id="reset" name="recet" value="Reset" onclick="resetCandidateStatus(${row.index},'${admissionForm.status}');"/>
							</td>		
						</tr>
					</c:forEach>
					<tr>
						<td colspan="7">
							<input class="greenbtn" type="submit" id="lowerSubmit" name="lowerSubmit"  value="submit"/>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>		
</form:form>



</c:if>

	
	<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
	</div>

</body>
</html>