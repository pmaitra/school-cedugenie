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
<title>Student Details Form</title>
<link rel="stylesheet" href="/icam/css/backoffice/createStudent.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="/icam/Cal/default.css"/>
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script src="/icam/Cal/zebra_datepicker.js"></script>
<script src="/icam/js/backoffice/createStudent.js"></script>
<script src="/icam/js/common/upload.js"></script>

<script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/util.js'></script>
<script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/interface/backOfficeService.js'></script>
    




</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Student Details
		<div style="float: right;">
			<div style="float: left; position: relative;">
				<a class="bookDetails" href="downloadStudentDetailsExcel.html">
					<button type="button" style="width: 100%;" class="editbtn">Download Excel Sheet</button>
				</a>
			</div>&emsp;
			<div style="float: right; position: relative;margin-bottom: 1%;">
				<form:form id="safcontents" name="safcontents" method="POST" action="uploadStudentDetailsExcel.html" enctype="multipart/form-data">
					<span id="FileUpload">
					    <input type="file" size="24"  name="imageFile" id="attachment" onchange="document.getElementById('FileField').value = document.getElementById('attachment').value;" />
					    <span id="BrowserVisible"><input type="text" id="FileField" /></span>
					</span>
					<input type="submit" class="editbtn" value="Submit" onclick="if(document.getElementById('attachment').value=='')return false; ;">
				</form:form>
			</div>
		</div>
	</h1>
</div>

	<c:if test="${excelDataInsertStatus ne null }">
		<div class="successbox" id="successbox" style="visibility: visible;">
			<span id="successmsg">${excelDataInsertStatus}</span>	
		</div>
	</c:if>
	<c:if test="${uploadErrorMessage ne null }">
		<div class="errorbox" id="errorbox" style="visibility: visible;">
			<span id="errorboxmsg">${uploadErrorMessage}</span>	
		</div>
	</c:if>


	<form:form name="addStudent" id="addStudent" enctype="multipart/form-data" action="addStudent.html" method="POST" >	
		<table cellspacing="0" cellpadding="0" class="midsec"  >			
			<tr>
				<th>From Id</th>
				<td>
					<select id="formId" name="formId" class="defaultselect">
						<option value="">Select</option>
						<c:forEach var="candidate" items="${candidateList}" >
							<option value="${candidate.admissionFormId}">${candidate.admissionFormId}</option>
						</c:forEach>
					</select>
				</td>
				<th>Roll Number <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input class="textfield1" type="text" name="rollNumber" id="rollNumber" /></td>				
			</tr>
		</table>
		
	<h3 style="color:white; margin-left: 1%;cursor: pointer;">Student Details
	<img id="i0" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'personalDetails',this.id);"/></h3>
	<div id="personalDetails">
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th>First Name <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input class="textfield1" type="text" name="resource.firstName" id="firstName" /></td>
				<th>Middle Name</th>
				<td><input class="textfield1" type="text" name="resource.middleName" id="middleName" /></td>
				<th>Last Name <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input class="textfield1" type="text" name="resource.lastName" id="lastName" /></td>		
			</tr>
			<tr>								
				<th>Standard <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td>
					<select id="standard" name="standard" class="defaultselect">
						<option value="">Select</option>
						<c:forEach var="standard" items="${standardList}" >
							<option value="${standard.standardCode}">${standard.standardName}</option>
						</c:forEach>
					</select>
					<input type="hidden" id="section" name="section" value="NA">
				</td>
				
				<th>House <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td>
					<select id="house" name="house" class="defaultselect">
						<c:forEach var="hostel" items="${hostelList}" >
							<option value="${hostel.hostelCode}">${hostel.hostelName}</option>
						</c:forEach>
					</select>
				</td>
				<th>Second Language <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td>
					<select id="secondLanguage" name="secondLanguage" class="defaultselect">
						<option value="">Select</option>
					</select>				
				</td>
			</tr>
			<tr>								
				<th>DOB <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input class="textfield1" name="resource.dateOfBirth" id="dateOfBirth" ></td>
				<th>Admission Date <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input class="textfield1" name="dateOfAdmission" id="dateOfAdmission" ></td>
				<th>State Of Domicile <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td>
					<select id="stateOfDomicile" name="stateOfDomicile" class="defaultselect">
						<c:forEach var="state" items="${stateList}" >
							<option value="${state.stateCode}">${state.stateName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>			
			<tr>								
				<th>Bank Name</th>
				<td><input class="textfield1" name="resource.bankName" id="bankName" ></td>
				<th>Branch</th>				
				<td><input class="textfield1" name="resource.bankBranch" id="bankBranch" ></td>
				<th>A/C No.</th>				
				<td><input class="textfield1" name="resource.accountNumber" id="accountNumber" ></td>
			</tr>
			<tr>
				<th>Gender</th>				
				<td>
					<select id="gender" name="resource.gender" class="defaultselect">
						<option value="Male">Male</option>
						<option value="Female">Female</option>
					</select>
				</td>
				<th>Blood Group</th>				
				<td>
					<select id="bloodGroup" name="resource.bloodGroup" class="defaultselect">
						<option value="UNKNOWN">UNKNOWN</option>
						<option value="A+">A+</option>
						<option value="A-">A-</option>
						<option value="B+">B+</option>
						<option value="B-">B-</option>
						<option value="AB+">AB+</option>
						<option value="AB-">AB-</option>
						<option value="O+">O+</option>
						<option value="O-">O-</option>						
					</select>
				</td>
				<th>Religion</th>				
				<td><input class="textfield1" name="resource.religion" id="religion" ></td>
			</tr>
			<tr>
				<th>Social Category</th>				
				<td>
					<select id="category" name="resource.category" class="defaultselect">
						<c:forEach var="socialCategory" items="${socialCategoryList}" >
							<option value="${socialCategory.socialCategoryCode}">${socialCategory.socialCategoryName}</option>
						</c:forEach>
					</select>
				</td>
				<th>Medical Status</th>				
				<td>
					<select id="medicalStatus" name="resource.medicalStatus" class="defaultselect">
						<option value="Fit">Fit</option>
						<option value="Unfit">Unfit</option>
					</select>
				</td>
				<th>Mothers Tongue</th>				
				<td><input class="textfield1" name="resource.motherTongue" id="motherTongue" ></td>
			</tr>
			<tr>
				<th>Nationality</th>				
				<td><input class="textfield1" name="resource.nationality" id="nationality" ></td>
				<th>Scholarship</th>				
				<td>
					<select id="scholarship" name="scholarship" class="defaultselect">
						<option value="">Select</option>
						<c:forEach var="scholarship" items="${scholarshipList}" >
							<option value="${scholarship.scholarshipCode}">${scholarship.scholarshipName}</option>
						</c:forEach>
					</select>
				</td>
				<th>Email</th>				
				<td><input class="textfield1" name="resource.emailId" id="emailId" ></td>
			</tr>
			<tr>
				<th></th>				
				<td></td>
				<th>Addhar No</th>				
				<td><input class="textfield1" name="resource.aadharCardNo" id="aadharCardNo" ></td>
				<th></th>				
				<td></td>
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
				<td><input class="textfield1" type="text" name="resource.fatherFirstName" id="fatherFirstName" /></td>
				<th>Middle Name</th>
				<td><input class="textfield1" type="text" name="resource.fatherMiddleName" id="fatherMiddleName" /></td>
				<th>Last Name <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input class="textfield1" type="text" name="resource.fatherLastName" id="fatherLastName" /></td>
			</tr>
			<tr>								
				<th>In Defence</th>
				<td>
					<select id="fatherInDefence" name="resource.fatherInDefence" class="defaultselect">
						<option value="false">No</option>
						<option value="true">Yes</option>
					</select>
				</td>
				<th>Service Status</th>				
				<td>
					<select id="fatherServiceStatus" name="resource.fatherServiceStatus" class="defaultselect">
						<option value="">Select</option>
						<option value="Ex-Defence">Ex-Defence</option>
						<option value="Defence">Defence</option>
					</select>
				</td>
				<th>Defence Category</th>				
				<td>
					<select id="fatherDefenceCategory" name="resource.fatherDefenceCategory" class="defaultselect">
						<option value="">Select</option>
						<option value="Air-Force">Air Force</option>
						<option value="Army">Army</option>
						<option value="Navy">Navy</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>Rank</th>
				<td><input class="textfield1" name="resource.fatherRank" id="fatherRank" ></td>								
				<th>Mobile</th>
				<td><input class="textfield1" name="resource.fatherMobile" id="fatherMobile" ></td>
				<th>Email</th>
				<td><input class="textfield1" name="resource.fatherEmail" id="fatherEmail" ></td>
			</tr>
			<tr>								
				<th colspan="8" style="text-align: center;text-decoration: underline;">Mothers Details</th>
			</tr>
			<tr>
				<th>First Name <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input class="textfield1" type="text" name="resource.motherFirstName" id="motherFirstName" /></td>
				<th>Middle Name</th>
				<td><input class="textfield1" type="text" name="resource.motherMiddleName" id="motherMiddleName" /></td>
				<th>Last Name <img class="required" src="/icam/images/required.gif" alt="Required"></th>
				<td><input class="textfield1" type="text" name="resource.motherLastName" id="motherLastName" /></td>
			</tr>
			<tr>								
				<th>Mobile</th>
				<td><input class="textfield1" type="text" name="resource.motherMobile" id="motherMobile" ></td>
				<th>Email</th>
				<td><input class="textfield1" type="text" name="resource.motherEmail" id="motherEmail" ></td>
			</tr>
			<tr>								
				<th colspan="8" style="text-align: center;text-decoration: underline;">Income Details</th>
			</tr>
			<tr>
				<th>Father's Income</th>
				<td><input class="textfield1" name="fatherIncome" id="fatherIncome" ></td>
				<th>Mother's Income</th>
				<td><input class="textfield1" name="motherIncome" id="motherIncome" ></td>
				<th>Student's Income</th>
				<td><input class="textfield1" name="studentIncome" id="studentIncome" ></td>
			</tr>
			<tr>
				<th>Family Income</th>
				<td><input class="textfield1" name="familyIncome" id="familyIncome" ></td>
			</tr>
			<tr>								
				<th colspan="8" style="text-align: center;text-decoration: underline;">Local Guardian's Details</th>
			</tr>
			<tr>
				<th>First Name</th>
				<td><input class="textfield1" type="text" name="guardianFirstName" id="guardianFirstName" /></td>
				<th>Middle Name</th>
				<td><input class="textfield1" type="text" name="guardianMiddleName" id="guardianMiddleName" /></td>
				<th>Last Name</th>
				<td><input class="textfield1" type="text" name="guardianLastName" id="guardianLastName" /></td>
			</tr>
			<tr>
				<th>Mobile</th>
				<td><input class="textfield1" name="guardianMobile" id="guardianMobile" ></td>
				<th>Email</th>
				<td><input class="textfield1" name="guardianEmail" id="guardianEmail" ></td>
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
				<td colspan="5"><input type="text" class="textfield1" id="presentAddressLine" name="address.presentAddressLine" /></td>
			</tr>			
			<tr>				
				<th>Landmark</th>				
				<td><input type="text" class="textfield1" id="presentAddressLandmark" name="address.presentAddressLandmark" /></td>
				<th>City/Village <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="presentAddressCityVillage" name="address.presentAddressCityVillage" /></td>
				<th>Railway Station</th>				
				<td><input type="text" class="textfield1" id="presentAddressRailwayStation" name="address.presentAddressRailwayStation" /></td>
			</tr>			
			<tr>
				<th>Post Office <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="presentAddressPostOffice" name="address.presentAddressPostOffice" /></td>
				<th>Police Station <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="presentAddressPoliceStation" name="address.presentAddressPoliceStation" /></td>
				<th>Pin code <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="presentAddressPinCode" name="address.presentAddressPinCode" /></td>
			</tr>			
			<tr>
				<th>District <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="presentAddressDistrict" name="address.presentAddressDistrict" /></td>
				<th>State <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td>
					<select name="address.presentAddressState" id="presentAddressState" class="defaultselect">
						<c:forEach var="state" items="${stateList}" >
							<option value="${state.stateCode}">${state.stateName}</option>
						</c:forEach>
					</select>  
				</td>
				<th>Country <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td>
					<select id="presentAddressCountry" name="address.presentAddressCountry" class="defaultselect">
						<c:forEach var="country" items="${countryList}" >
							<option value="${country.countryCode}" ${country.countryCode eq 'IND' ?'selected="slected"':''}>${country.countryName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>Phone</th>
				<td><input type="text" class="textfield1" id="presentAddressPhone" name="address.presentAddressPhone" /></td>
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
				<td colspan="5"><input type="text" class="textfield1" id="permanentAddressLine" name="address.permanentAddressLine" /></td>
			</tr>			
			<tr>				
				<th>Landmark</th>				
				<td><input type="text" class="textfield1" id="permanentAddressLandmark" name="address.permanentAddressLandmark" /></td>
				<th>City/Village <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="permanentAddressCityVillage" name="address.permanentAddressCityVillage" /></td>
				<th>Railway Station</th>				
				<td><input type="text" class="textfield1" id="permanentAddressRailwayStation" name="address.permanentAddressRailwayStation" /></td>
			</tr>			
			<tr>
				<th>Post Office <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="permanentAddressPostOffice" name="address.permanentAddressPostOffice" /></td>
				<th>Police Station <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="permanentAddressPoliceStation" name="address.permanentAddressPoliceStation" /></td>
				<th>Pin code <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="permanentAddressPinCode" name="address.permanentAddressPinCode" /></td>
			</tr>			
			<tr>
				<th>District <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td><input type="text" class="textfield1" id="permanentAddressDistrict" name="address.permanentAddressDistrict" /></td>
				<th>State <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td>
					<select name="address.permanentAddressState" id="permanentAddressState" class="defaultselect">
						<c:forEach var="state" items="${stateList}" >
							<option value="${state.stateCode}">${state.stateName}</option>
						</c:forEach>
					</select>  
				</td>
				<th>Country <img class="required" src="/icam/images/required.gif" alt="Required"></th>				
				<td>
					<select id="permanentAddressCountry" name="address.permanentAddressCountry" class="defaultselect">
						<c:forEach var="country" items="${countryList}" >							
							<option value="${country.countryCode}" ${country.countryCode eq 'IND' ?'selected="slected"':''}>${country.countryName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>Phone</th>				
				<td><input type="text" class="textfield1" id="permanentAddressPhone" name="address.permanentAddressPhone" /></td>
			</tr>
			
			
			
			<tr>
				<th colspan="6" style="text-align: center;text-decoration: underline;">Local Guardian Address</th>
			</tr>
			<tr>
				<th>Address1</th>				
				<td colspan="5"><input type="text" class="textfield1" id="guardianAddressLine" name="address.guardianAddressLine" /></td>
			</tr>			
			<tr>				
				<th>Landmark</th>				
				<td><input type="text" class="textfield1" id="guardianAddressLandmark" name="address.guardianAddressLandmark" /></td>
				<th>City/Village</th>				
				<td><input type="text" class="textfield1" id="guardianAddressCityVillage" name="address.guardianAddressCityVillage" /></td>
				<th>Railway Station</th>				
				<td><input type="text" class="textfield1" id="guardianAddressRailwayStation" name="address.guardianAddressRailwayStation" /></td>
			</tr>			
			<tr>
				<th>Post Office</th>				
				<td><input type="text" class="textfield1" id="guardianAddressPostOffice" name="address.guardianAddressPostOffice" /></td>
				<th>Police Station</th>				
				<td><input type="text" class="textfield1" id="guardianAddressPoliceStation" name="address.guardianAddressPoliceStation" /></td>
				<th>Pin code</th>				
				<td><input type="text" class="textfield1" id="guardianAddressPinCode" name="address.guardianAddressPinCode" /></td>
			</tr>			
			<tr>
				<th>District</th>				
				<td><input type="text" class="textfield1" id="guardianAddressDistrict" name="address.guardianAddressDistrict" /></td>
				<th>State</th>				
				<td>
					<select name="address.guardianAddressState" id="guardianAddressState" class="defaultselect">
						<option value="">Select</option>
					</select>
				</td>
				<th>Country</th>				
				<td>
					<select id="guardianAddressCountry" name="address.guardianAddressCountry" class="defaultselect">
						<option value="">Select</option>
						<c:forEach var="country" items="${countryList}" >
							<option value="${country.countryCode}" ${country.countryCode eq 'IND' ?'selected="slected"':''}>${country.countryName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>Phone</th>				
				<td><input type="text" class="textfield1" id="guardianAddressPhone" name="address.guardianAddressPhone" /></td>
			</tr>
		</table>
	</div>
	
	<h3 style="color:white; margin-left: 1%;cursor: pointer;">Previous Educational Details 
	<img id="i3" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'studentPreviousEducationalDetails',this.id);"/></h3>
	<div id="studentPreviousEducationalDetails">
		<table id="pedDet" cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th>School Name				
				<td><input type="text" class="textfield1" id="previousSchoolName" name="previousSchoolName"/></td>
				<th>Phone</th>				
				<td><input type="text" class="textfield1" id="previousSchoolPhone" name="previousSchoolPhone"/></td>
			</tr>			
			<tr>
				<th>Website</th>
				<td><input type="text" class="textfield1" id="previousSchoolWebsite" name="previousSchoolWebsite"/></td>
				<th>Email</th>
				<td><input type="text" class="textfield1" id="previousSchoolEmail" name="previousSchoolEmail"/></td>
			</tr>
			<tr>
				<th>Address</th>
				<td><textarea class="textfield1" id="previousSchoolAddress" name="previousSchoolAddress"></textarea></td>
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
					<div id="uploadImage" >	
					<h3>Select image file</h3>					 
					 	<img id="preview" src="images/upload.png" style="width:200px;height:200px;"/>
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