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
<c:url value="/getMeritListPaging.html" var="pagedLink">
	 <c:param name="p" value="~"/>
	 <c:param name="paramStandard" value="${candidate.standard.standardName}"/>
	 <c:param name="paramMeritListType" value="${candidate.meritListType.meritListTypeName}"/>
	 <c:param name="paramSelectionType" value="${candidate.status}"/>
</c:url>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to View Merit List for admission" />
<meta name="keywords" content="Generate Merit List" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>View Merit List</title>
<link rel="stylesheet" href="/icam/css/admission/viewMeritList.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/css/common/pagination.css" rel="stylesheet" />


<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>

<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">
<link rel="stylesheet" href="/icam/Cal/jsDatePick_ltr.min.css">
<script src="/icam/Cal/jsDatePick.min.1.3.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
<script src="/icam/js/admission/viewMeritList.js"></script>
</head>
<body>
<div class="ttlarea">	
		<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;View Merit List
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
<form:form id="getMeritList" name="getMeritList" method="POST" action="getMeritList.html">		
	<table cellspacing="0" cellpadding="0" class="midsec">
		<tr>
			<td>Class<img class="required" src="/icam/images/required.gif" alt="Required"></td>
			<td>
				<select name="standard.standardName" id="standard" class="defaultselect" name="standard">
					<option value="">Select...</option>
					<c:forEach var="standard" items="${standardList}">
						<option value="<c:out value="${standard.standardCode}"/>"><c:out value="${standard.standardName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td>Merit List Type<img class="required" src="/icam/images/required.gif" alt="Required"></td>
			<td>
			
				<select name="meritListType.meritListTypeName" id="meritListType" class="defaultselect">
					<option value="">Select...</option>
					<c:forEach var="meritListType" items="${meritListTypes}">
						<option value="<c:out value="${meritListType.meritListTypeCode}"/>"><c:out value="${meritListType.meritListTypeCode}"/></option>
					</c:forEach>
				</select>
			</td>
			<td>Selection Type<img class="required" src="/icam/images/required.gif" alt="Required"></td>
			<td>
				<select name="status" id="selectionType" class="defaultselect">
					<option value="">Select...</option>
				</select>
			</td>
		</tr>
		<tr>
		<td>Venue</td>
			<td>
				<select name="venue.venueName" id="venue" class="defaultselect">
					<option value="">Select...</option>
					<c:forEach var="venue" items="${venueList}">
						<option value="<c:out value="${venue.venueCode}"/>"><c:out value="${venue.venueName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td>Category</td>
			<td>
				<select name="socialCategory.socialCategoryName" id="category" class="defaultselect">
					<option value="">Select...</option>
					<c:forEach var="category" items="${socialCategoryList}">
						<option value="<c:out value="${category.socialCategoryCode}"/>"><c:out value="${category.socialCategoryName}"/></option>
					</c:forEach>
				</select>
			</td>
			<td>State</td>
			<td>
				<select name="address.presentAddressState" id="state" class="defaultselect">
					<option value="">Select...</option>
					<c:forEach var="state" items="${stateList}">
						<option value="<c:out value="${state.stateName}"/>"><c:out value="${state.stateName}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
		<td>
		From Roll No :
		</td>
		<td><input id = "fromRollNoId" class="textfield2" type="text" name="formRollNoId"/></td>
		<td>
		To Roll No :
		</td>
		<td><input  id = "toRollNoId" class="textfield2" type="text" name="toRollNoId"/></td>
		<td><input class="greenbtn" type="submit" id="submit" name="submit"  value="submit" onclick="return validMeritListPage()"/></td>
		</tr>
		
	</table>
</form:form>
<c:if test="${candidate != null}">
<script>
i=1;
</script>
	<c:choose>
		<c:when test="${candidatePagedListHolder == null || empty candidatePagedListHolder}">
			<div class="btnsarea01" style="visibility: visible;">
				<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
					<span id="infomsg">No candidate found.</span>	
				</div>
			</div>
		</c:when>
		<c:otherwise>
		<form:form id="updateMeritList" name="updateMeritList" method="POST" action="updateMeritList.html">	
					<table id="tabUpdateMeritList" cellspacing="0" cellpadding="0" class="midsec1">
					<c:if test="${candidate.status ne 'SELECTED'}">
						<tr>
								<td colspan="7">
									<input class="greenbtn" type="submit" id="lowerSubmit" name="lowerSubmit"  value="submit"/>
									<input class="submitbtn" type="reset" id="lowerReset" name="lowerReset"  value="Reset"/>
								</td>
							</tr>
						</c:if>
					
						<tr>
							<th>Class:: ${candidate.standard.standardName}<input type="hidden"  name="standard.standardName" value="${candidate.standard.standardName}"></th>
							<th></th>
							<th colspan="2">Merit List Type:: ${candidate.meritListType.meritListTypeName}
							<input type="hidden"  name="meritListType.meritListTypeCode" value="${candidate.meritListType.meritListTypeName}">
							</th>
							<th>
							</th>
							<th>
							</th>
							<th>Selection Type ::
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
								<c:if test="${candidate.status eq 'ACCEPTED'}">
									Accepted
								</c:if>
							</th>
							
						</tr>
						<tr>
							<th>Candidate Name</th><th>Form ID</th><th>Date Of Birth </th><th>Gender</th><th>Category</th><th>State</th><th><c:if test="${candidate.status ne 'SELECTED'}">Status</c:if></th>
						</tr>
						<c:forEach var="varCandidate" items="${candidatePagedListHolder.pageList}" varStatus="row">
						<tr>
							<td>${varCandidate.resource.name}</td>
							<td onClick="window.open('getCandidateDetails.html?formId=${varCandidate.admissionFormId}','_self')" style="cursor:pointer;">${varCandidate.admissionFormId}-${varCandidate.examCentreAlias}
							     <input type="hidden" id="formId${row.index}" name="candidateList[${row.index}].admissionFormId" value="${varCandidate.admissionFormId}">
							</td>
							<td>${varCandidate.resource.dateOfBirth}</td>
							<td>${varCandidate.resource.gender}</td>
							<td>${varCandidate.resource.category}</td>
							<td>${varCandidate.address.presentAddressState}</td>
							<td>
							<c:choose>
								<c:when test="${candidate.status eq 'SELECTED'}">
								</c:when>
								<c:otherwise>
								<c:if test="${candidate.meritListType.meritListTypeName eq 'EXAM'}">
										<c:if test="${candidate.status eq 'REVIEW'}">
											<input  type="radio" id="selectedStatus${row.index}" name="candidateList[${row.index}].status"  value="SELECTED"/>Selected
											<input  type="radio" id="rejectedStatus${row.index}" name="candidateList[${row.index}].status"  value="REJECTED"/>Rejected
										</c:if>
										<c:if test="${candidate.status eq 'REJECTED'}">
											<input  type="radio" id="selectedStatus${row.index}" name="candidateList[${row.index}].status"  value="SELECTED"/>Selected
											<input  type="radio" id="reviewStatus${row.index}" name="candidateList[${row.index}].status"  value="REVIEW"/>Under Review
										</c:if>
								</c:if>
								<c:if test="${candidate.meritListType.meritListTypeName eq 'PI AND MEDICAL'}">
										<c:if test="${candidate.status eq 'REVIEW'}">
											<input  type="radio" id="waitingStatus${row.index}" name="candidateList[${row.index}].status"  value="QUEUED"/>Waiting
											<input  type="radio" id="selectedStatus${row.index}" name="candidateList[${row.index}].status"  value="SELECTED"/>Selected
											<input  type="radio" id="rejectedStatus${row.index}" name="candidateList[${row.index}].status"  value="REJECTED"/>Rejected
										</c:if>
										<c:if test="${candidate.status eq 'REJECTED'}">
											<input  type="radio" id="waitingStatus${row.index}" name="candidateList[${row.index}].status"  value="QUEUED"/>Waiting
											<input  type="radio" id="selectedStatus${row.index}" name="candidateList[${row.index}].status"  value="SELECTED"/>Selected
											<input  type="radio" id="reviewStatus${row.index}" name="candidateList[${row.index}].status"  value="REVIEW"/>Under Review
										</c:if>
										<c:if test="${candidate.status eq 'QUEUED'}">
											<input  type="radio" id="selectedStatus${row.index}" name="candidateList[${row.index}].status"  value="SELECTED"/>Selected
											<input  type="radio" id="reviewStatus${row.index}" name="candidateList[${row.index}].status"  value="REVIEW"/>Under Review
											<input  type="radio" id="rejectedStatus${row.index}" name="candidateList[${row.index}].status"  value="REJECTED"/>Rejected
										</c:if>
								</c:if>
								<input type="button" id="reset" name="recet" value="Reset" onclick="resetCandidateStatus(${row.index},'${candidate.meritListType.meritListTypeName}','${candidate.status}');"/>
								</c:otherwise>
							</c:choose>
							</td>
							</tr>
						</c:forEach>
						<c:if test="${candidate.status ne 'SELECTED'}">
						<tr>
								<td colspan="7">
									<input class="greenbtn" type="submit" id="lowerSubmit" name="lowerSubmit"  value="submit"/>
									<input class="submitbtn" type="reset" id="lowerReset" name="lowerReset"  value="Reset"/>
								</td>
							</tr>
						</c:if>
						<tr>
	       					<td colspan="11" id="toolbar"><c:out value="Displaying ${first} to ${last} of ${total} items"/><tg:paging pagedListHolder="${candidatePagedListHolder}" pagedLink="${pagedLink}"/></td>
						</tr>
					</table>
				</form:form>
		</c:otherwise>
	</c:choose>
</c:if>	
	<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
	</div>

</body>
</html>