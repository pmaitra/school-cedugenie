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
<meta name="keywords" content="Dispatch Admit Card" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Dispatch Admit Card</title>
<link rel="stylesheet" href="/icam/css/admission/dispatchAdmitCard.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>

<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery.ptTimeSelect.css" />
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
<script type="text/javascript" src="/icam/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/icam/js/common/selectall.js"></script>
<script src="/icam/js/admission/dispatchAdmitCard.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="/icam/css/common/calendar/jquery-ui-1.10.4.custom.min.css" />
<script type="text/javascript" src="/icam/js/common/jquery.ptTimeSelect.js"></script>

</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Dispatch Admit Card
		</h1>
</div>
<c:if test="${successStatus != null}">
	<div class="successbox" id="successbox" style="visibility:visible;">
		<span id="successmsg" style="visibility:visible;">Successfully Updated!</span>	
	</div>
</c:if>
<c:if test="${failStatus != null}">
		<div class="errorbox" id="errorbox" style="visibility: visible;">
			<span id="errormsg">${failStatus}</span>	
		</div>
</c:if>
<form:form method="POST" action="getCandidateForDispatchAdmitCard.html" id="getCandidateForm">		
	<table cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th>Venue<img class="required" src="/icam/images/required.gif" alt="Required"></th>
		<td colspan="2">
			<select name="venueId" id="venue" class="defaultselect">
				<option value="">Select...</option>
				<c:forEach var="venue" items="${venueList}">
					<option value="<c:out value="${venue.venueId}"/>"><c:out value="${venue.venueName}"/></option>
				</c:forEach>
			</select>
		</td>	
	</tr>
	</table>
</form:form>



<c:if test="${candidatesForDispatchAdmitCard != null}">

<form:form name="submitDispatchAdmitCard" method="POST" action="submitDispatchAdmitCard.html">
	<table cellspacing="0" cellpadding="0" class="midsec1">
		
		<c:choose>
			<c:when test="${candidatesForDispatchAdmitCard.candidateList == null || empty candidatesForDispatchAdmitCard.candidateList}">
				<div class="btnsarea01" style="visibility: visible;">
					<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
						<span id="infomsg">No candidate found to dispatch admit Card for exam centre ${candidatesForDispatchAdmitCard.venue.venueName}</span>	
					</div>
				</div>
			</c:when>
			<c:otherwise>
			<tr>
						<td colspan="7">
							<input class="greenbtn" type="submit" id="upperSubmit" name="upperSubmit"  value="Dispatched" onclick="return validateSubmitDispatchAdmitCardForm();"/>
							<input class="editbtn" type="reset" id="lowerReset" name="lowerReset"  value="Reset"/>
							<c:if test="${candidatesForDispatchAdmitCard.noOfDispatchedCandidate > 0}">
								<input class="submitbtn" type="submit" id="upperResetDispatchAdmitCardTime" name="upperCancelSchecdule"  value="Cancel Schecdule"/>
							</c:if>
						</td>
					</tr>
			<tr>								
					<th colspan="4">Centre of Examination: </th><th>${candidatesForDispatchAdmitCard.venue.venueName}<input type="hidden"  name="venue.venueId" value="${candidatesForDispatchAdmitCard.venue.venueId}">
					<input type="hidden"  name="venue.venueName" value="${candidatesForDispatchAdmitCard.venue.venueName}">
					 </th>
			</tr>
			<tr>								
						<th>Form Id</th>
						<th>Name</th>
						<th>Date Of Birth</th>	
						<th>State</th>	
						<th><input type="checkbox" id="selectAllCheck" name="selectAllCheck" onclick="toggleByClass(this,'check');">Select All</th>
					</tr>
					<c:forEach var = "can" items="${candidatesForDispatchAdmitCard.candidateList}" varStatus="row">
						<tr>
							<td><c:out value="${can.admissionFormId}-${can.examCentreAlias}"></c:out>
							<input type="hidden" id="formId${row.index}" name="candidateList[${row.index}].admissionFormId" value="${can.admissionFormId}">
							</td>		
							<td><c:out value="${can.candidateName}"></c:out></td>		
							<td><c:out value="${can.resource.dateOfBirth}"></c:out></td>
							<td><c:out value="${can.address.presentAddressState}"></c:out></td>
							<td><input type="checkbox" class="check" id="generatedStatus${row.index}" name="candidateList[${row.index}].status" value="GENERATED"></td>
						</tr>
					</c:forEach>
					<tr>
						<th>Exam Date<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
						<th><input type="text" class="textfield2"   id="examDate" name="examinationDate" ></th>
		
						<th>Exam Time<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
						<th><input type="text" class="textfield2"   id="examTime" name="examinationTime" readonly="readonly"></th>	
						<th></th>
					</tr>
					<tr>
						<td colspan="7">
							<input class="greenbtn" type="submit" id="lowerSubmit" name="lowerSubmit"  value="Dispatched" onclick="return validateSubmitDispatchAdmitCardForm();"/>
							<input class="editbtn" type="reset" id="lowerReset" name="lowerReset"  value="Reset"/>
							<c:if test="${candidatesForDispatchAdmitCard.noOfDispatchedCandidate > 0}">
								<input class="submitbtn" type="submit" id="lowerResetDispatchAdmitCardTime" name="lowerCancelSchecdule"  value="Cancel Schecdule"/>
							</c:if>
							${candidatesForDispatchAdmitCard.noOfDispatchedCandidate} forms are dispatched out of  ${candidatesForDispatchAdmitCard.noOfAllotedCandidate} for exam centre ${candidatesForDispatchAdmitCard.venue.venueName}
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