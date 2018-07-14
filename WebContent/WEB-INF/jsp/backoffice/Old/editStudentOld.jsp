<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page to Student Details Form" />
<meta name="keywords" content="Student Details Form" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Student Details Update Form</title>
<link rel="stylesheet" href="/icam/css/backoffice/editStudent.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="/icam/Cal/default.css"/>
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script src="/icam/Cal/zebra_datepicker.js"></script>
<script src="/icam/js/backoffice/editStudent.js"></script>
<script src="/icam/js/common/upload.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Update Student Details
	</h1>
</div>
	<c:if test="${fileDeleteStatus eq 'Deleted'}">
		<div class="infomsgbox" id="infomsgdiv" style="visibility: visible;">
			<span id="infomsg">Attachment Deleted.</span>	
		</div>
	</c:if>
	<form:form name="editStudent" id="editStudent" enctype="multipart/form-data" action="editStudent.html" method="POST" >	
		<table cellspacing="0" cellpadding="0" class="midsec"  >			
			<tr>
				<th>Roll Number <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input class="textfield1" type="text" name="rollNumber" id="rollNumber" value="${student.rollNumber}" readonly="readonly" /></td>				
			</tr>
		</table>
		
	<h3 style="color:white; margin-left: 1%;cursor: pointer;">Student Details
	<img id="i0" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'personalDetails',this.id);"/></h3>
	<div id="personalDetails">
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th>First Name <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input class="textfield1" type="text" name="resource.firstName" value="${student.resource.firstName}" id="firstName" /></td>
				<th>Middle Name</th>
				<td><input class="textfield1" type="text" name="resource.middleName" value="${student.resource.middleName}" id="middleName" /></td>
				<th>Last Name <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input class="textfield1" type="text" name="resource.lastName" value="${student.resource.lastName}" id="lastName" /></td>		
			</tr>
			<tr>								
				<th>Standard <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td>
					<input type="hidden" id="oldStd" value="${student.standard}">
					<select id="standard" name="standard" class="defaultselect">
						<option value="">Select</option>
						<c:forEach var="standard" items="${standardList}" >
							<c:choose>
								<c:when test="${standard.standardCode eq student.standard}">
									<option value="${standard.standardCode}" selected="selected">${standard.standardName}</option>
								</c:when>
								<c:otherwise>
									<option value="${standard.standardCode}">${standard.standardName}</option>
								</c:otherwise>
							</c:choose>
							
						</c:forEach>
					</select>
				</td>
				<th>Section</th>				
				<td>
					<input type="hidden" id="oldSec" value="${student.section}">
					<c:if test="${student.section eq 'NA'}">
						Not Defined Yet<input type="hidden" name="section" id="section" value="${student.section}">
					</c:if>
					<c:if test="${student.section ne 'NA'}">
						<span id="sectionSpan">
							<input type="text" id="section" name="section" value="${student.section}" readonly="readonly" class="textfield1">
						</span>
					</c:if>
				</td>
				<th>House <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td>
					<select id="house" name="house" class="defaultselect">
						<c:forEach var="hostel" items="${hostelList}" >
							<option value="${hostel.hostelCode}" ${hostel.hostelCode eq student.house ? 'selected=selected' : ''}>${hostel.hostelName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>								
				<th>DOB <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input class="textfield1" name="resource.dateOfBirth" value="${student.resource.dateOfBirth}" id="dateOfBirth" ></td>
				<th>Admission Date <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input class="textfield1" name="dateOfAdmission" value="${student.dateOfAdmission}" id="dateOfAdmission" ></td>
				<th>State Of Domicile <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td>
					<select id="stateOfDomicile" name="stateOfDomicile" class="defaultselect">
						<c:forEach var="state" items="${stateList}" >
							<option value="${state.stateCode}" ${state.stateCode eq student.stateOfDomicile ? 'selected=selected' : ''}>${state.stateName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>			
			<tr>								
				<th>Bank Name</th>
				<td><input class="textfield1" name="resource.bankName" value="${student.resource.bankName}" id="bankName" ></td>
				<th>Branch</th>				
				<td><input class="textfield1" name="resource.bankBranch" value="${student.resource.bankBranch}" id="bankBranch" ></td>
				<th>A/C No.</th>				
				<td><input class="textfield1" name="resource.accountNumber" value="${student.resource.accountNumber}" id="accountNumber" ></td>
			</tr>
			<tr>
				<th>Gender</th>				
				<td>
					<select id="gender" name="resource.gender" class="defaultselect">
						<c:choose>
							<c:when test="${student.resource.gender eq 'Male'}">
								<option value="Male" selected="selected">Male</option>
								<option value="Female">Female</option>
							</c:when>
							<c:otherwise>
								<option value="Male">Male</option>
								<option value="Female" selected="selected">Female</option>
							</c:otherwise>
						</c:choose>
					</select>
				</td>
				<th>Blood Group</th>
				<td>
					<select id="bloodGroup" name="resource.bloodGroup" class="defaultselect">
						<option value="A+" ${student.resource.bloodGroup eq 'A+' ? 'selected=selected' : ''}>A+</option>
						<option value="A-" ${student.resource.bloodGroup eq 'A-' ? 'selected=selected' : ''}>A-</option>
						<option value="B+" ${student.resource.bloodGroup eq 'B+' ? 'selected=selected' : ''}>B+</option>
						<option value="B-" ${student.resource.bloodGroup eq 'B-' ? 'selected=selected' : ''}>B-</option>
						<option value="AB+" ${student.resource.bloodGroup eq 'AB+' ? 'selected=selected' : ''}>AB+</option>
						<option value="AB-" ${student.resource.bloodGroup eq 'AB-' ? 'selected=selected' : ''}>AB-</option>
						<option value="O+" ${student.resource.bloodGroup eq 'O+' ? 'selected=selected' : ''}>O+</option>
						<option value="O-" ${student.resource.bloodGroup eq 'O-' ? 'selected=selected' : ''}>O-</option>
						<option value="UNKNOWN" ${student.resource.bloodGroup eq 'UNKNOWN' ? 'selected=selected' : ''}>UNKNOWN</option>
					</select>
				</td>
				<th>Religion</th>
				<td><input class="textfield1" name="resource.religion" value="${student.resource.religion}" id="religion" ></td>
			</tr>
			<tr>
				<th>Social Category</th>				
				<td>
					<select id="category" name="resource.category" class="defaultselect">
						<c:forEach var="socialCategory" items="${socialCategoryList}" >
							<option value="${socialCategory.socialCategoryCode}" ${student.resource.category eq socialCategory.socialCategoryCode ? 'selected=selected' : ''}>${socialCategory.socialCategoryName}</option>
						</c:forEach>
					</select>
				</td>
				<th>Medical Status</th>				
				<td>
					<select id="medicalStatus" name="resource.medicalStatus" class="defaultselect">
						<option value="Fit" ${student.resource.medicalStatus eq 'Fit' ? 'selected=selected' : ''}>Fit</option>
						<option value="Unfit" ${student.resource.medicalStatus eq 'Unfit' ? 'selected=selected' : ''}>Unfit</option>
					</select>
				</td>
				<th>Mothers Tongue</th>				
				<td><input class="textfield1" name="resource.motherTongue" value="${student.resource.motherTongue}" id="motherTongue" ></td>
			</tr>
			<tr>
				<th>Nationality</th>				
				<td><input class="textfield1" name="resource.nationality" value="${student.resource.nationality}" id="nationality" ></td>
				<th>Scholarship</th>				
				<td>
					<select id="scholarship" name="scholarship" class="defaultselect">
						<option value="">Select</option>
						<c:forEach var="scholarship" items="${scholarshipList}" >
							<option value="${scholarship.scholarshipCode}" ${student.scholarship eq scholarship.scholarshipCode ? 'selected=selected' : ''}>${scholarship.scholarshipName}</option>
						</c:forEach>
					</select>
				</td>
				<th>Email</th>				
				<td><input class="textfield1" name="resource.emailId" value="${student.resource.emailId}" id="emailId" ></td>
			</tr>
			<tr>
				<th>Second Language <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td>
					<select id="secondLanguage" name="secondLanguage" class="defaultselect">
						<option value="">Select</option>
						<c:forEach var="subject" items="${student.subjectList}" >
							<option value="${subject.subjectCode}" ${subject.subjectCode eq student.secondLanguage ? 'selected=selected' : ''}>${subject.subjectName}</option>
						</c:forEach>
					</select>
				</td>
				
				<th>Addhar No</th>				
				<td><input class="textfield1" name="resource.aadharCardNo" id="aadharCardNo" value="${student.resource.aadharCardNo}"></td>
			</tr>
		</table>
	</div>
	
	
	<h3 style="color:white; margin-left: 1%;cursor: pointer;">Family Details
	<img id="i1" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'guardianDetails',this.id);"/></h3>
	<div id="guardianDetails">
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th colspan="8" style="text-align: center;text-decoration: underline;">Fathers Details</th>
			</tr>
			<tr>
				<th>First Name <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input class="textfield1" type="text" name="resource.fatherFirstName" value="${student.resource.fatherFirstName}" id="fatherFirstName" /></td>
				<th>Middle Name</th>
				<td><input class="textfield1" type="text" name="resource.fatherMiddleName" value="${student.resource.fatherMiddleName}" id="fatherMiddleName" /></td>
				<th>Last Name <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input class="textfield1" type="text" name="resource.fatherLastName" value="${student.resource.fatherLastName}" id="fatherLastName" /></td>
			</tr>
			<tr>								
				<th>In Defence</th>
				<td>
					<select id="fatherInDefence" name="resource.fatherInDefence" class="defaultselect">
						<option value="false" ${student.resource.fatherInDefence eq false ? 'selected=selected' : ''}>No</option>
						<option value="true" ${student.resource.fatherInDefence eq true ? 'selected=selected' : ''}>Yes</option>
					</select>
				</td>
				<th>Service Status</th>
				<td>
					<select id="fatherServiceStatus" name="resource.fatherServiceStatus" class="defaultselect">
						<option value="" ${student.resource.fatherServiceStatus eq '' ? 'selected=selected' : ''}>Select</option>
						<option value="Ex-Defence" ${student.resource.fatherServiceStatus eq 'Ex-Defence' ? 'selected=selected' : ''}>Ex-Defence</option>
						<option value="Defence" ${student.resource.fatherServiceStatus eq 'Defence' ? 'selected=selected' : ''}>Defence</option>
					</select>
				</td>
				<th>Defence Category</th>				
				<td>
					<select id="fatherDefenceCategory" name="resource.fatherDefenceCategory" class="defaultselect">
						<option value="" ${student.resource.fatherDefenceCategory eq '' ? 'selected=selected' : ''}>Select</option>
						<option value="Air-Force" ${student.resource.fatherDefenceCategory eq 'Air-Force' ? 'selected=selected' : ''}>Air Force</option>
						<option value="Army" ${student.resource.fatherDefenceCategory eq 'Army' ? 'selected=selected' : ''}>Army</option>
						<option value="Navy" ${student.resource.fatherDefenceCategory eq 'Navy' ? 'selected=selected' : ''}>Navy</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>Rank</th>
				<td><input class="textfield1" name="resource.fatherRank" value="${student.resource.fatherRank}" id="fatherRank" ></td>								
				<th>Mobile</th>
				<td><input class="textfield1" name="resource.fatherMobile" value="${student.resource.fatherMobile}" id="fatherMobile" ></td>
				<th>Email</th>
				<td><input class="textfield1" name="resource.fatherEmail" value="${student.resource.fatherEmail}" id="fatherEmail" ></td>
			</tr>
			<tr>								
				<th colspan="8" style="text-align: center;text-decoration: underline;">Mothers Details</th>
			</tr>
			<tr>
				<th>First Name <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input class="textfield1" type="text" name="resource.motherFirstName" value="${student.resource.motherFirstName}" id="motherFirstName" /></td>
				<th>Middle Name</th>
				<td><input class="textfield1" type="text" name="resource.motherMiddleName" value="${student.resource.motherMiddleName}" id="motherMiddleName" /></td>
				<th>Last Name <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input class="textfield1" type="text" name="resource.motherLastName" value="${student.resource.motherLastName}" id="motherLastName" /></td>
			</tr>
			<tr>								
				<th>Mobile</th>
				<td><input class="textfield1" type="text" name="resource.motherMobile" value="${student.resource.motherMobile}" id="motherMobile" ></td>
				<th>Email</th>
				<td><input class="textfield1" type="text" name="resource.motherEmail" value="${student.resource.motherEmail}" id="motherEmail" ></td>
			</tr>
			<tr>								
				<th colspan="8" style="text-align: center;text-decoration: underline;">Income Details</th>
			</tr>
			<tr>
				<th>Father's Income</th>
				<td><input class="textfield1" name="fatherIncome" value="${student.fatherIncome}" id="fatherIncome" ></td>
				<th>Mother's Income</th>
				<td><input class="textfield1" name="motherIncome" value="${student.motherIncome}" id="motherIncome" ></td>
				<th>Student's Income</th>
				<td><input class="textfield1" name="studentIncome" value="${student.studentIncome}" id="studentIncome" ></td>
			</tr>
			<tr>
				<th>Family Income</th>
				<td><input class="textfield1" name="familyIncome" value="${student.familyIncome}" id="familyIncome" ></td>
			</tr>
			<tr>								
				<th colspan="8" style="text-align: center;text-decoration: underline;">Local Guardian's Details</th>
			</tr>
			<tr>
				<th>First Name</th>
				<td><input class="textfield1" type="text" name="guardianFirstName" value="${student.guardianFirstName}" id="guardianFirstName" /></td>
				<th>Middle Name</th>
				<td><input class="textfield1" type="text" name="guardianMiddleName" value="${student.guardianMiddleName}" id="guardianMiddleName" /></td>
				<th>Last Name</th>
				<td><input class="textfield1" type="text" name="guardianLastName" value="${student.guardianLastName}" id="guardianLastName" /></td>
			</tr>
			<tr>
				<th>Mobile</th>
				<td><input class="textfield1" name="guardianMobile" value="${student.guardianMobile}" id="guardianMobile" ></td>
				<th>Email</th>
				<td><input class="textfield1" name="guardianEmail" value="${student.guardianEmail}" id="guardianEmail" ></td>
			</tr>
		</table>
	</div>
	

	<h3 style="color:white; margin-left: 1%;cursor: pointer;">Address 
	<img id="i2" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'location',this.id);"/></h3>
	<div id="location">
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th colspan="6" style="text-align: center;text-decoration: underline;">Correspondence Address</th>
			</tr>
			<tr>
				<th>Address1 <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td colspan="5"><input type="text" class="textfield1" id="presentAddressLine" name="address.presentAddressLine" value="${student.address.presentAddressLine}" /></td>
			</tr>			
			<tr>				
				<th>Landmark</th>				
				<td><input type="text" class="textfield1" id="presentAddressLandmark" name="address.presentAddressLandmark" value="${student.address.presentAddressLandmark}" /></td>
				<th>City/Village <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="presentAddressCityVillage" name="address.presentAddressCityVillage" value="${student.address.presentAddressCityVillage}" /></td>
				<th>Railway Station</th>				
				<td><input type="text" class="textfield1" id="presentAddressRailwayStation" name="address.presentAddressRailwayStation" value="${student.address.presentAddressRailwayStation}" /></td>
			</tr>
			<tr>
				<th>Post Office <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="presentAddressPostOffice" name="address.presentAddressPostOffice" value="${student.address.presentAddressPostOffice}" /></td>
				<th>Police Station <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="presentAddressPoliceStation" name="address.presentAddressPoliceStation" value="${student.address.presentAddressPoliceStation}" /></td>
				<th>Pin code <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="presentAddressPinCode" name="address.presentAddressPinCode" value="${student.address.presentAddressPinCode}" /></td>
			</tr>			
			<tr>
				<th>District <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="presentAddressDistrict" name="address.presentAddressDistrict" value="${student.address.presentAddressDistrict}" /></td>
				<th>State <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td>
					<select name="address.presentAddressState" id="presentAddressState" class="defaultselect">
						<c:forEach var="state" items="${presentStateList}" >
							<option value="${state.stateCode}" ${student.address.presentAddressState eq state.stateCode ? 'selected=selected' : ''}>${state.stateName}</option>
						</c:forEach>
					</select>  
				</td>
				<th>Country <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td>
					<select id="presentAddressCountry" name="address.presentAddressCountry" class="defaultselect">
						<c:forEach var="country" items="${countryList}" >
							<option value="${country.countryCode}" ${student.address.presentAddressCountry eq country.countryCode ? 'selected=selected' : ''}>${country.countryName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>Phone</th>				
				<td><input type="text" class="textfield1" id="presentAddressPhone" name="address.presentAddressPhone" value="${student.address.presentAddressPhone}" /></td>
			</tr>
			
			
			
			<tr>
				<th colspan="6" style="text-align: center;text-decoration: underline;">
					Permanent Address
					(<input type="checkbox" onchange="copyAddress(this);"/>
					 Same as above)
				</th>				
			</tr>
			<tr>
				<th>Address1 <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td colspan="5"><input type="text" class="textfield1" id="permanentAddressLine" name="address.permanentAddressLine" value="${student.address.permanentAddressLine}" /></td>
			</tr>			
			<tr>				
				<th>Landmark</th>				
				<td><input type="text" class="textfield1" id="permanentAddressLandmark" name="address.permanentAddressLandmark" value="${student.address.permanentAddressLandmark}" /></td>
				<th>City/Village <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="permanentAddressCityVillage" name="address.permanentAddressCityVillage" value="${student.address.permanentAddressCityVillage}" /></td>
				<th>Railway Station</th>				
				<td><input type="text" class="textfield1" id="permanentAddressRailwayStation" name="address.permanentAddressRailwayStation" value="${student.address.permanentAddressRailwayStation}" /></td>
			</tr>			
			<tr>
				<th>Post Office <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="permanentAddressPostOffice" name="address.permanentAddressPostOffice" value="${student.address.permanentAddressPostOffice}" /></td>
				<th>Police Station <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="permanentAddressPoliceStation" name="address.permanentAddressPoliceStation" value="${student.address.permanentAddressPoliceStation}" /></td>
				<th>Pin code <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="permanentAddressPinCode" name="address.permanentAddressPinCode" value="${student.address.permanentAddressPinCode}" /></td>
			</tr>			
			<tr>
				<th>District <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="permanentAddressDistrict" name="address.permanentAddressDistrict" value="${student.address.permanentAddressDistrict}" /></td>
				<th>State <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td>
					<select name="address.permanentAddressState" id="permanentAddressState" class="defaultselect">
						<c:forEach var="state" items="${permanentStateList}" >
							<option value="${state.stateCode}" ${student.address.permanentAddressState eq state.stateCode ? 'selected=selected' : ''}>${state.stateName}</option>
						</c:forEach>
					</select>  
				</td>
				<th>Country <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td>
					<select id="permanentAddressCountry" name="address.permanentAddressCountry" class="defaultselect">
						<c:forEach var="country" items="${countryList}" >							
							<option value="${country.countryCode}" ${student.address.permanentAddressCountry eq country.countryCode ? 'selected=selected' : ''}>${country.countryName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>Phone</th>				
				<td><input type="text" class="textfield1" id="permanentAddressPhone" name="address.permanentAddressPhone" value="${student.address.permanentAddressPhone}" /></td>
			</tr>
			
			
			
			<tr>
				<th colspan="6" style="text-align: center;text-decoration: underline;">Local Guardian Address</th>
			</tr>
			<tr>
				<th>Address1</th>				
				<td colspan="5"><input type="text" class="textfield1" id="guardianAddressLine" name="address.guardianAddressLine" value="${student.address.guardianAddressLine}" /></td>
			</tr>			
			<tr>				
				<th>Landmark</th>				
				<td><input type="text" class="textfield1" id="guardianAddressLandmark" name="address.guardianAddressLandmark" value="${student.address.guardianAddressLandmark}" /></td>
				<th>City/Village</th>				
				<td><input type="text" class="textfield1" id="guardianAddressCityVillage" name="address.guardianAddressCityVillage" value="${student.address.guardianAddressCityVillage}" /></td>
				<th>Railway Station</th>				
				<td><input type="text" class="textfield1" id="guardianAddressRailwayStation" name="address.guardianAddressRailwayStation" value="${student.address.guardianAddressRailwayStation}" /></td>
			</tr>			
			<tr>
				<th>Post Office</th>				
				<td><input type="text" class="textfield1" id="guardianAddressPostOffice" name="address.guardianAddressPostOffice" value="${student.address.guardianAddressPostOffice}" /></td>
				<th>Police Station</th>				
				<td><input type="text" class="textfield1" id="guardianAddressPoliceStation" name="address.guardianAddressPoliceStation" value="${student.address.guardianAddressPoliceStation}" /></td>
				<th>Pin code</th>				
				<td><input type="text" class="textfield1" id="guardianAddressPinCode" name="address.guardianAddressPinCode" value="${student.address.guardianAddressPinCode}" /></td>
			</tr>			
			<tr>
				<th>District</th>				
				<td><input type="text" class="textfield1" id="guardianAddressDistrict" name="address.guardianAddressDistrict" value="${student.address.guardianAddressDistrict}" /></td>
				<th>State</th>				
				<td>
					<select name="address.guardianAddressState" id="guardianAddressState" class="defaultselect">
						<c:forEach var="state" items="${guardianStateList}" >
							<option value="${state.stateCode}" ${student.address.guardianAddressState eq state.stateCode ? 'selected=selected' : ''}>${state.stateName}</option>
						</c:forEach>
					</select>
				</td>
				<th>Country</th>				
				<td>
					<select id="guardianAddressCountry" name="address.guardianAddressCountry" class="defaultselect">
						<option value="">Select</option>
						<c:forEach var="country" items="${countryList}" >
							<option value="${country.countryCode}" ${student.address.guardianAddressCountry eq country.countryCode ? 'selected=selected' : ''}>${country.countryName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>Phone</th>				
				<td><input type="text" class="textfield1" id="guardianAddressPhone" name="address.guardianAddressPhone" value="${student.address.guardianAddressPhone}" /></td>
			</tr>
		</table>
	</div>
	
	<h3 style="color:white; margin-left: 1%;cursor: pointer;">Previous Educational Details 
	<img id="i3" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'studentPreviousEducationalDetails',this.id);"/></h3>
	<div id="studentPreviousEducationalDetails">
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th>School Name</th>			
				<td><input type="text" class="textfield1" id="previousSchoolName" name="previousSchoolName" value="${student.previousSchoolName}" /></td>
				<th>Phone</th>				
				<td><input type="text" class="textfield1" id="previousSchoolPhone" name="previousSchoolPhone" value="${student.previousSchoolPhone}" /></td>
			</tr>			
			<tr>
				<th>Website</th>
				<td><input type="text" class="textfield1" id="previousSchoolWebsite" name="previousSchoolWebsite" value="${student.previousSchoolWebsite}" /></td>
				<th>Email</th>
				<td><input type="text" class="textfield1" id="previousSchoolEmail" name="previousSchoolEmail" value="${student.previousSchoolEmail}" /></td>
			</tr>
			<tr>
				<th>Address</th>
				<td><textarea class="textfield1" id="previousSchoolAddress" name="previousSchoolAddress">${student.previousSchoolAddress}</textarea></td>
			</tr>
			<tr>
				<th>Download Attachments</th>
			<c:choose>
				<c:when test="${student.resource.attachmentList eq null || student.resource.attachmentList.size() == 0}">												
					<td>No Attachment Found</td>													
				</c:when>
				<c:otherwise>
					<c:if test="${student.resource.attachmentList != null}">
						<c:forEach var="attachment" items="${student.resource.attachmentList}" >
							<c:if test="${attachment.attachmentType ne 'profile_image'}">
								<td>
									<a class="alnk" onClick="window.open('downloadStudentAttachments.html?fileLocation=${attachment.storageRootPath}&fileName=${attachment.attachmentName}','_self')" style="cursor:pointer;">${attachment.attachmentName}</a>
									<a class="alnk" onClick="window.open('deleteStudentAttachmentFromHardDrive.html?fileLocation=${attachment.storageRootPath}&fileName=${attachment.attachmentName}&fileId=${attachment.storageObjectId}&roll=${student.rollNumber}','_self')"><img src="images/minus_icon.png" ></a>
								</td>
							</c:if>
						</c:forEach>
					</c:if>
				</c:otherwise>
			</c:choose> 	
			</tr>
			<tr>
	 			<th>Upload Documents</th>				
	 			<td><input type="file" name="resource.uploadFile.fileData" id="fileData0"/></td>
				<td><input id="addFile2" class="addFileClassName addbtn" type="button" /></td>	 			      
			</tr>			
		</table>	
	</div>
	
	<h3 style="color:white; margin-left: 1%;cursor: pointer;">Upload Image
	<img id="i4" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'uploadImage',this.id);"/></h3>
	<div id="uploadImage">
	<table cellspacing="0" cellpadding="0" class="midsec">			
			<tr>
				<th colspan="2" style="text-align: center;text-decoration: underline;">Student Image</th>
			</tr>	
			<tr>
				<td colspan ="2">  			
					<div id="uploadImage" name="uploadImage" >	
					<h3>Please Select Image File</h3>					 
					 	<img id="preview" src="data:image/jpg;base64,${student.resource.image.imageName}" style="width:200px;height:200px;"/>
					 	<div class="container">
				            <div class="upload_form_cont">	
					            <div>
					                 <div><input type="file" name="resource.image.imageData" id="image_upload" onchange="fileSelected();"/></div> 
					            </div>
					        </div>
					    </div>
					</div>
				</td>
			</tr>
						
						
<%-- 	<img id="preview" src="data:image/jpg;base64, ${employeeDetails.resource.image.imageName}" style="width:200px;height:200px;"/> --%>
					 	
<!-- 	<div><input type="file" name="imageData" id="image_upload" onchange="validateFile();"/></div>  -->
					    
						
					
		</table>
	</div>
	
	<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
		<input class="submitbtn" type="submit" id="submit" name="submit" onclick="return validate();" value="Submit" />
		<input type="reset" value="Clear" id="Clear" class="clearbtn" />			
	</div>	
</form:form>
<script type="text/javascript">
	$(document).ready(function() {
		$("#presentAddressCityVillage").autocomplete({
		 	source: '/icam/getCityList.html'}); 
		$("#permanentAddressCityVillage").autocomplete({
			source: '/icam/getCityList.html'});
		$("#guardianAddressCityVillage").autocomplete({
			source: '/icam/getCityList.html'});
		$("#presentAddressDistrict").autocomplete({
	 		source: '/icam/getDistrictList.html'}); 
		$("#permanentAddressDistrict").autocomplete({
			source: '/icam/getDistrictList.html'});
		$("#guardianAddressDistrict").autocomplete({
			source: '/icam/getDistrictList.html'});
		
		
	});
</script>
</body>
</html>