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
<meta name="description" content="Page to Staff Details" />
<meta name="keywords" content="Staff Details" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Employee Details</title>
<link rel="stylesheet" href="/icam/css/erp/employeeDetails.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>

<script src="/icam/js/erp/validateEmployeeDetails.js"></script>
<script src="/icam/js/common/upload.js"></script>

<script src="/icam/js/erp/employeeDetails.js"></script>
<script src="/icam/js/erp/employeeAddAndEdit.js"></script>

	
	
	<script>
/* 		$(document).ready(function() {
			$('#trainingFromDate0').Zebra_DatePicker();
			 
			$('#trainingFromDate0').Zebra_DatePicker({
				format: 'd/m/Y'
				//direction: true
			});
		});
		$(document).ready(function() {
			$('#trainingToDate0').Zebra_DatePicker();
			 
			$('#trainingToDate0').Zebra_DatePicker({
				format: 'd/m/Y'
				//direction: true
			});
		}); */
		$(document).ready(function() {
			$(".trainingDate").each(function(){
				$(this).Zebra_DatePicker();
			    
			    $(this).Zebra_DatePicker({
			    	  format: 'd/m/Y'
			    	});
			});
			
		});
	</script>

</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Employee Details
		<div style="float: right;">
		<div style="float: left; position: relative;">
		<a class="bookDetails" href="downloadStaffDetailsExcel.html">
			<button type="button" style="width: 100%;" class="editbtn">Download Excel Sheet</button>
		</a>
		</div>&emsp;			
		<div style="float: right; position: relative;margin-bottom: 1%;">
			<form:form id="safcontents" name="safcontents" method="POST" action="uploadStaffDetailsExcel.html" enctype="multipart/form-data">
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
	<c:if test="${excelDataInsertStatus ne 0 }">
		<div class="successbox" id="successbox" style="visibility: visible;">
			<span id="successmsg">Excel Uploaded Successfully</span>	
		</div>
	</c:if>
	<c:if test="${excelDataInsertStatus eq 0 }">
		<div class="successbox" id="successbox" style="visibility: visible;">
			<span id="successmsg">Problem Occured While Uploading Excel</span>	
		</div>
	</c:if>
</c:if>
<form:form name="employeeDetailsForm" id="employeeDetailsForm"  enctype="multipart/form-data" action="submitEmployeeDetails.html" method="POST">		
<h3 style="color:white; margin-left: 1%;cursor: pointer;">Basic Details
<img id="i1" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'basicDetails',this.id);"/></h3>	
<div id="basicDetails">	
	<table cellspacing="0" cellpadding="0" class="midsec" >	
	<tr>
		<th id="tdata">User Id<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td><input name="resource.userId" id="userId" class="textfield2"/></td>
		<th>Employee Code<img class="required" src="/icam/images/required.gif"></th>		
		<td><input name="employeeCode" id="employeeCode" class="textfield2"/></td>	
	</tr>
	
	<tr>
		<th>Gender<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<select name="resource.gender" class="defaultselect" id="gender">
					<option value="M">Male</option>
					<option value="F">Female</option>
				</select>
		</td>		
		<th>Date Of Birth<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>			
			<input type="text" class="textfield2" id="dateOfBirth" name="resource.dateOfBirth" readonly="readonly" onblur="countRetirementDate();"/>
		</td>		
	</tr>
	
	<tr>		
		<th>Date Of Join<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td><input name="dateOfJoining" id="dateOfJoining" readonly="readonly" class="textfield2"/></td>
		<th>Date Of Retirement<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td><input name="dateOfRetirement" id="dateOfRetirement" class="textfield2" readonly="readonly"/></td>	
	</tr>
	
	<tr>
		<th>Employee Type<img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<td>
				<select id="employeeTypeName" name="employeeType.employeeTypeName" class="defaultselect" onchange="getDesignationForResourceType(this);">
					<option VALUE="">Please Select</option>
					<c:forEach var="employeeType" items="${employee.employeeTypeList}" >
						<option VALUE="${employeeType.employeeTypeCode}">${employeeType.employeeTypeName}</option>
					</c:forEach>															
				</select>
			</td>
			<th>Job Type<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
			<td>
				<select id="jobTypeName" name="jobType.jobTypeName" class="defaultselect">
					<option VALUE="" >Please Select</option>
					<c:forEach var="jobType" items="${employee.jobTypeList}" >
						<option VALUE="${jobType.jobTypeCode}">${jobType.jobTypeName}</option>
					</c:forEach>							
				</select>
			</td>		
	</tr>		
	<tr>
		<th>Designation<img class="required" src="/icam/images/required.gif" alt="Required"></th>			
		<td>
			<select id="designationName" name="designation.designationName" class="defaultselect">
				<option VALUE="" >Please Select</option>
			</select>
		</td>		
		<th>Department<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<select id="department" name="department.departmentName" class="defaultselect">
				<option VALUE="" >Please Select</option>
				<c:forEach var="department" items="${employee.departmentList}" >
					<option VALUE="${department.departmentCode}">${department.departmentName}</option>
				</c:forEach>							
			</select>
		</td>	
	</tr>	
	<tr>		
		<th>Qualification Summary<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<input type="text" class="textfield2" id="qualificationSummary" name="qualificationSummary" />
		</td>		
	</tr>	
	</table>
</div>

<h3 style="color:white; margin-left: 1%;cursor: pointer;">Personal Details
<img id="i2" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'personalDetails',this.id);"/></h3>
<div id="personalDetails" >
	<table cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th colspan="4" style="text-align: center;text-decoration: underline;">Employee's Details</th>
	</tr>			
	<tr>
		<th>Employee Name<img class="required" src="/icam/images/required.gif" alt="Required"></th>			
		<td>
			<input type="text" class="textfield2" id="firstName" name="resource.firstName" placeholder="First Name"/>
		</td>
		<td>
			<input type="text" class="textfield2" id="middleName" name="resource.middleName" placeholder="Middle Name"/>
		</td>				
		<td>
			<input type="text" class="textfield2" id="lastName" name="resource.lastName" placeholder="Last Name"/>
		</td>
	</tr>	
	<tr>
	<th>Blood Group<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<select id="bloodGroup" name="resource.bloodGroup" class="defaultselect">
				<option value="A+">A+</option>
				<option value="A-">A-</option>
				<option value="B+">B+</option>
				<option value="B-">B-</option>
				<option value="AB+">AB+</option>
				<option value="AB-">AB-</option>
				<option value="O+">O+</option>
				<option value="O-">O-</option>
				<option value="UNKNOWN">UNKNOWN</option>						
			</select>
		</td>
		<th>Nationality<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<input id="nationality" class="textfield2" name="resource.nationality" value="INDIAN"/>
		</td>
	</tr>				
	<tr>
		<th>Mother Tongue<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<input class="textfield2" id="motherTongue" name="resource.motherTongue"/>
		</td>
		<th>Category<img class="required" src="/icam/images/required.gif" alt="Required"></th>
		<td>
			<select id="category" name="resource.category" class="defaultselect">
				<option VALUE="" >Please select</option>
				<c:forEach var="socialCategory" items="${employee.socialCategoryList}" >
					<OPTION VALUE="${socialCategory.socialCategoryCode}">${socialCategory.socialCategoryName}</option>
				</c:forEach>	
			</select>
		</td>
	</tr>	
	<tr>
		<th>Religion<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<input id="religion" class="textfield2" name="resource.religion"/>
		</td>
		<th>Medical Attention<img class="required" src="/icam/images/required.gif" alt="Required"></th>
		<td>			
			<select name="resource.medicalStatus" class="defaultselect" id="medicalStatus">
					<option value="FIT">FIT</option>
					<option value="UNFIT">UNFIT</option>
				</select>
		</td>
	</tr>
	<tr>		
		<th>Passport No</th>		
			<td>
				<input id="passportNo" class="textfield2" name="resource.passportNo"/>
			</td>		
		<th>Pan Card No</th>		
		<td>
			<input id="panCardNo" class="textfield2" name="resource.panCardNo"/>
		</td>		
	</tr>
	<tr>			
		<th>Aadhar Card No</th>		
		<td>
			<input id="aadharCardNo" class="textfield2" name="resource.aadharCardNo"/>
		</td>
		<th>Voter Card No</th>		
		<td>
			<input id="voterCardNo" class="textfield2" name="resource.voterCardNo"/>
		</td>		
	</tr>
	<tr>
		<th>Voting Constituency</th>		
		<td>
			<input id="votingConstituency" class="textfield2" name="resource.votingConstituency"/>
		</td>	
		<th>Parliamentary Constituency</th>		
		<td>
			<input id="parliamentaryConstituency" class="textfield2" name="resource.parliamentaryConstituency"/>
		</td>		
	</tr>						
	<tr>
		<th colspan="4" style="text-align: center;text-decoration: underline;">Father's Details</th>
	</tr>			
	<tr>
		<th>Father Name<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<input type="text" class="textfield2" id="fatherFirstName" name="resource.fatherFirstName" placeholder="First Name"/>
		</td>				
		<td>
			<input type="text" class="textfield2" id="fatherMiddleName" name="resource.fatherMiddleName" placeholder="Middle Name">
		</td>		
		<td>
			<input type="text" class="textfield2" id="fatherLastName" name="resource.fatherLastName" placeholder="Last Name"/>
		</td>
	</tr>			
	<tr>
		<th>Occupation<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<input name="fatherOccupation" id="fatherOccupation" class="textfield2"/>
		</td>
	</tr>			
	<tr>
		<th colspan="4" style="text-align: center;text-decoration: underline;">Mother's Details</th>
	</tr>			
	<tr>
		<th>Mother Name<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<input  id="motherFirstName" class="textfield2" name="resource.motherFirstName" placeholder="First Name"/>
		</td>			
		<td>
			<input  id="motherMiddleName" class="textfield2" name="resource.motherMiddleName" placeholder="Middle Name">
		</td>		
		<td>
			<input id="motherLastName" class="textfield2" name="resource.motherLastName" placeholder="last Name"/>
		</td>
	</tr>			
		<tr>
			<th>Occupation<img class="required" src="/icam/images/required.gif" alt="Required"></th>			
			<td>
				<input name="motherOccupation" class="textfield2" id="motherOccupation"/>
			</td>
		</tr>			
		<tr>
			<th colspan="4" style="text-align: center;text-decoration: underline;">Contact Details</th>
		</tr>			
		<tr>
			<th>Contact Number<img class="required" src="/icam/images/required.gif" alt="Required"></th>							
			<td>
				<input type="text" class="textfield2" name="resource.mobile" id="mobile" size="20" />
			</td>
			<th>E-Mail<img class="required" src="/icam/images/required.gif" alt="Required"></th>			
			<td>
				<input type="text" class="textfield2" id="emailId" name="resource.emailId" size="20">
			</td>
		</tr>	
		
			<tr>
			<th colspan="4" style="text-align: center;text-decoration: underline;">Marital Status</th>
		</tr>			
		<tr>
			<th>Marital Status<img class="required" src="/icam/images/required.gif" alt="Required"></th>							
			<td>
				<select name="maritalStatus" class="defaultselect" id="maritalStatus">					
					<option value="MARRIED">MARRIED</option>
					<option value="UNMARRIED">UNMARRIED</option>
				</select>
			</td>
			<th>Spouse's Name</th>			
			<td>
				<input type="text" class="textfield2" id="spouseName" name="spouseName" size="20">
			</td>
		</tr>		
	</table>
</div>		
	
<h3 style="color:white; margin-left: 1%;cursor: pointer;">Qualification Details
<img id="i3" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'QualificationDetails',this.id);"/></h3>
<div id="QualificationDetails">
	<table id="SchoolingDetails" cellspacing="0" cellpadding="0" class="midsec1" >
		<tr>
			<th colspan="6" style="text-align: center;text-decoration: underline;" >
				Schooling Details<img class="required" src="/icam/images/required.gif" alt="Required">
			</th>
		</tr>
		<tr>
			<th>Exam Name/Degree</th>
			<th>Specialization</th>
			<th>School/College</th>
			<th>Board/University</th>
			<th>Marks(%)</th>
			<th>Passing Year</th>
		</tr>
		<tr>		
			<td><input type="text" class="textfield1" name="qualificationList[0].examName" id="examName0" value="10th" readonly="readonly"/></td>
			<td><input type="text" class="textfield1" name="qualificationList[0].specialization" id="specialization0"></td>
			<td><input type="text" class="textfield1" name="qualificationList[0].schoolCollege" id="schoolCollege0"></td>
			<td><input type="text" class="textfield1" name="qualificationList[0].boardUniversity" id="boardUniversity0"></td>
			<td><input type="text" class="textfield1" name="qualificationList[0].marks" id="marks0"></td>
			<td>
				<input type="text" class="textfield1" name="qualificationList[0].passingYear" id="passingYear0">
				<input type="hidden" class="textfield1" name="qualificationList[0].qualificationType" id="qualificationType0" value="MADHYAMIK">
			</td>					
		</tr>
		<tr>			
			<td><input type="text" class="textfield1" name="qualificationList[1].examName" id="examName1" value="12th" readonly="readonly"/></td>
			<td><input type="text" class="textfield1" name="qualificationList[1].specialization" id="specialization1"></td>
			<td><input type="text" class="textfield1" name="qualificationList[1].schoolCollege" id="schoolCollege1"></td>
			<td><input type="text" class="textfield1" name="qualificationList[1].boardUniversity" id="boardUniversity1"></td>
			<td><input type="text" class="textfield1" name="qualificationList[1].marks" id="marks1"></td>
			<td>
				<input type="text" class="textfield1" name="qualificationList[1].passingYear" id="passingYear1">
				<input type="hidden" class="textfield1" name="qualificationList[1].qualificationType" id="qualificationType1" value="HS">
			</td>				
		</tr>
	</table>
	
	<table id="GraduationDetails"  cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<th colspan="6" style="text-align: center;text-decoration: underline;">
				Graduation Details<img class="required" src="/icam/images/required.gif" alt="Required">
			</th>
		</tr>
		<tr>
			<th>Exam Name/Degree</th>
			<th>Specialization</th>
			<th>School/College</th>
			<th>Board/University</th>
			<th>Marks(%)</th>
			<th>Passing Year</th>
		</tr>
		<tr>			
			<td><input type="text"  class="textfield1" name="qualificationList[2].examName" id="examName2"/></td>
			<td><input type="text"  class="textfield1" name="qualificationList[2].specialization" id="specialization2"></td>
			<td><input type="text"  class="textfield1" name="qualificationList[2].schoolCollege" id="schoolCollege2"></td>
			<td><input type="text"  class="textfield1" name="qualificationList[2].boardUniversity" id="boardUniversity2"></td>
			<td><input type="text"  class="textfield1" name="qualificationList[2].marks" id="marks2"></td>
			<td>
				<input type="text"  class="textfield1" name="qualificationList[2].passingYear" id="passingYear2">
				<input type="hidden" class="textfield1" name="qualificationList[2].qualificationType" id="qualificationType2" value="GRADUATION">
			</td>
		</tr>			
	</table>
	
	<table id="PostGraduationDetails" cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<th colspan="6" style="text-align: center;text-decoration: underline;">
				Post Graduation Details
			</th>
		</tr>
		<tr>
			<th>Exam Name/Degree</th>
			<th>Specialization</th>
			<th>School/College</th>
			<th>Board/University</th>
			<th>Marks(%)</th>
			<th>Passing Year</th>
		</tr>
		<tr>			
			<td><input type="text" class="textfield1"  name="qualificationList[3].examName" id="examName3"/></td>
			<td><input type="text" class="textfield1"  name="qualificationList[3].specialization" id="specialization3"></td>
			<td><input type="text" class="textfield1"  name="qualificationList[3].schoolCollege" id="schoolCollege3"></td>
			<td><input type="text" class="textfield1"  name="qualificationList[3].boardUniversity" id="boardUniversity3"></td>
			<td><input type="text" class="textfield1"  name="qualificationList[3].marks" id="marks3"></td>
			<td>
				<input type="text" class="textfield1"  name="qualificationList[3].passingYear" id="passingYear3">
				<input type="hidden" class="textfield1" name="qualificationList[3].qualificationType" id="qualificationType3" value="POSTGRADUATION">
			</td>
		</tr>			
	</table>
	
	<table id="OtherQualificationDetails" cellspacing="0" cellpadding="0" class="midsec1">
		<tr>
			<th colspan="7" style="text-align: center;text-decoration: underline;">
				Other Qualification Details
			</th>
		</tr>
		<tr>
			<th>Select</th>
			<th>Exam Name/Degree</th>
			<th>Specialization</th>
			<th>School/College</th>
			<th>Board/University</th>
			<th>Marks(%)</th>
			<th>Passing Year</th>
		</tr>
		<tr>
			<td><input type="checkbox" disabled="disabled" /></td>
			<td><input type="text" class="textfield1"  name="othersExamName" id="othersExamName"/></td>
			<td><input type="text" class="textfield1"  name="othersSpecialization" id="othersSpecialization"></td>
			<td><input type="text" class="textfield1"  name="othersSchoolCollege" id="othersSchoolCollege"></td>
			<td><input type="text" class="textfield1"  name="othersBoardUniversity" id="othersBoardUniversity"></td>
			<td><input type="text" class="textfield1"  name="othersMarks" id="othersMarks"></td>
			<td>
				<input type="text" class="textfield1"  name="othersPassingYear" id="othersPassingYear">
				<input type="hidden" class="textfield1" name="othersQualificationType" id="othersQualificationType" value="OTHERS">
			</td>				
		</tr>
		<tr>
			<td colspan="6">
				<input type="button"  onclick="addOtherQualificationDetails();" class="addbtn"/>				
			</td>
			<td >
				<input type="button"  value="Delete" onclick="deleteTable(OtherQualificationDetails);" class="clearbtn"/>
			</td>
		</tr>				
	</table>
		
	<table id="fileTable" cellspacing="0" cellpadding="0" class="midsec1">
		<thead>
			<tr>
				<th>Upload Qualification Document</th>
			</tr>
		</thead>
		<tbody>
			<tr>
	 			<td>
	 				<input type="file" name="resource.uploadFile.qualificationRelatedFile"/>
					<input id="addFile" class="addFileClassName addbtn" type="button" />				
	 			</td>      
			</tr>
		</tbody>
	</table>		
</div>

	<h3 style="color:white; margin-left: 1%;cursor: pointer;">Address 
	<img id="i4" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'location',this.id);"/></h3>
	<div id="location">
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th colspan="6" style="text-align: center;text-decoration: underline;">Correspondence Address</th>
			</tr>
			<tr>
				<th>Address</th>				
				<td colspan="5"><input type="text" class="textfield1" id="presentAddressLine" name="address.presentAddressLine" /></td>
			</tr>			
			<tr>				
				<th>Landmark</th>				
				<td><input type="text" class="textfield1" id="presentAddressLandmark" name="address.presentAddressLandmark" /></td>
				<th>City/Village</th>				
				<td><input type="text" class="textfield1" id="presentAddressCityVillage" name="address.presentAddressCityVillage" /></td>
				<th>Railway Station</th>				
				<td><input type="text" class="textfield1" id="presentAddressRailwayStation" name="address.presentAddressRailwayStation" /></td>
			</tr>			
			<tr>
				<th>Post Office</th>				
				<td><input type="text" class="textfield1" id="presentAddressPostOffice" name="address.presentAddressPostOffice" /></td>
				<th>Police Station</th>				
				<td><input type="text" class="textfield1" id="presentAddressPoliceStation" name="address.presentAddressPoliceStation" /></td>
				<th>Pin code</th>				
				<td><input type="text" class="textfield1" id="presentAddressPinCode" name="address.presentAddressPinCode" /></td>
			</tr>			
			<tr>
				<th>District</th>				
				<td><input type="text" class="textfield1" id="presentAddressDistrict" name="address.presentAddressDistrict" /></td>
				<th>State</th>				
				<td>
					<select name="address.presentAddressState" id="presentAddressState" class="defaultselect">
						<c:forEach var="state" items="${stateList}" >
							<option value="${state.stateCode}">${state.stateName}</option>
						</c:forEach>
					</select>  
				</td>
				<th>Country</th>				
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
				<th>Address</th>				
				<td colspan="5"><input type="text" class="textfield1" id="permanentAddressLine" name="address.permanentAddressLine" /></td>
			</tr>			
			<tr>				
				<th>Landmark</th>				
				<td><input type="text" class="textfield1" id="permanentAddressLandmark" name="address.permanentAddressLandmark" /></td>
				<th>City/Village</th>				
				<td><input type="text" class="textfield1" id="permanentAddressCityVillage" name="address.permanentAddressCityVillage" /></td>
				<th>Railway Station</th>				
				<td><input type="text" class="textfield1" id="permanentAddressRailwayStation" name="address.permanentAddressRailwayStation" /></td>
			</tr>			
			<tr>
				<th>Post Office</th>				
				<td><input type="text" class="textfield1" id="permanentAddressPostOffice" name="address.permanentAddressPostOffice" /></td>
				<th>Police Station</th>				
				<td><input type="text" class="textfield1" id="permanentAddressPoliceStation" name="address.permanentAddressPoliceStation" /></td>
				<th>Pin code</th>				
				<td><input type="text" class="textfield1" id="permanentAddressPinCode" name="address.permanentAddressPinCode" /></td>
			</tr>			
			<tr>
				<th>District</th>				
				<td><input type="text" class="textfield1" id="permanentAddressDistrict" name="address.permanentAddressDistrict" /></td>
				<th>State</th>				
				<td>
					<select name="address.permanentAddressState" id="permanentAddressState" class="defaultselect">
						<c:forEach var="state" items="${stateList}" >
							<option value="${state.stateCode}">${state.stateName}</option>
						</c:forEach>
					</select>  
				</td>
				<th>Country</th>				
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
		</table>
	</div>

<h3 style="color:white; margin-left: 1%;cursor: pointer;">Employee's Dependents
<img id="i5" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'employeesDependents',this.id);"/></h3>
<div id="employeesDependents">
	<div id="cloneDiv">
	<table class="employeesDependentsTableClass midsec" id="employeesDependentsTable"  cellspacing="0" cellpadding="0" >
		<tr>
			<th style="text-align: center;text-decoration: underline;">
				Employee's Childern
			</th>
		</tr>
		<tr>
			<th>Name</th>
			<th>Gender</th>
			<th>DOB</th>
		</tr>
		<tr>			
			<td><input type="text" class="textfield2" id="childName" name="childName"/></td>
			<td>			
			<select name="childGender" class="defaultselect" id="childGender">
					<option value="M">MALE</option>
					<option value="F">FEMALE</option>
				</select>
			</td>
			<td><input type="text" class="childDateOfBirthClass" id="childDateOfBirth" name="childDateOfBirth" readonly="readonly"/></td>
		</tr>
		<tr>
			<td colspan="3">
				<input type="button" class="addbtn" onclick="addChildRows();">
			</td>
		</tr>		
	</table>	
	</div>		
</div>

<h3 style="color:white; margin-left: 1%;cursor: pointer;">Nominee
<img id="i6" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'nominee',this.id);"/></h3>
<div id="nominee">
	<div id="cloneDiv">
	<table class="midsec" id="nomineeTable"  cellspacing="0" cellpadding="0" >
		<tr>
			<th style="text-align: center;text-decoration: underline;">
				Nominee Details
			</th>
		</tr>
		<tr>
			<th>Nominee Name</th>
			<th>Relationship</th>
			<th>Nominee( % )</th>
		</tr>
		<tr>							
			<td>
				<input type="text" class="textfield2" id="nomineeName" name="nomineeName"/>
			</td>
			<td>			
				<input type="text" class="textfield2" id="relationship" name="relationship"/>
			</td>
			<td>
				<input type="text" class="textfield2" id="nomineePercent" name="nomineePercent" onblur="calculateHundred();"/>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="button" class="addbtn" onclick="addNominee();">
			</td>			
		</tr>		
	</table>	
	</div>		
</div>

<h3 style="color:white; margin-left: 1%;cursor: pointer;">Employee's Bank Details
<img id="i7" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'employeesBankDetails',this.id);"/></h3>
<div id="employeesBankDetails">
	<div id="cloneDiv">
	<table class="employeesBankDetailsTableClass midsec" id="employeesBankDetailsTable"  cellspacing="0" cellpadding="0" >
		<tr>
			<th colspan="6" style="text-align: center;text-decoration: underline;">
				Employee's Bank Details
			</th>
		</tr>
		<tr>
			<th>Bank Name</th>			
			<td><input type="text" class="textfield2" id="bankName" name="bankName" /></td>
			<th>Branch Code</th>			
			<td><input type="text" class="textfield2" id="branchCode" name="branchCode"/></td>				
		</tr>		
		<tr>
			<th>Account Type</th>			
			<td><input type="text" class="textfield2" id="accountType" name="accountType"/></td>
			<th>Branch IFSC Code</th>			
			<td><input type="text" class="textfield2" id="branchIFSCCode" name="branchIFSCCode"/></td>
		</tr>
		<tr>
			<th>Account Holder Name</th>			
			<td><input type="text" class="textfield2" id="accountHolderName" name="accountHolderName" /></td>
			<th>Account Number</th>			
			<td><input type="text" class="textfield2" id="accountNumber" name="accountNumber"/></td>				
		</tr>		
	</table>	
	</div>		
</div>
<h3 style="color:white; margin-left: 1%;cursor: pointer;">Staff Previous Working Details
<img id="i8" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'staffPreviousWorkDetails',this.id);"/></h3>
<div id="staffPreviousWorkDetails" >
	<div id="clonedstaffPreviousWorkDetailsDiv">
	<table class="staffPreviousWorkDetailsTableClass midsec" id="staffPreviousWorkDetailsTable"  cellspacing="0" cellpadding="0" >
		<tr>
			<th colspan="6" style="text-align: center;text-decoration: underline;">
				Previous Organization Details
			</th>
		</tr>
		<tr>
			<th>Previous Organization Name</th>			
			<td><input type="text" class="textfield2" id="organizationName" name="organizationName" /></td>
			<th>From Date</th>			
			<td><input type="text" class="textfield2" id="" name="fromDate"  placeholder="MM/YYYY"/></td>	
			<th>To Date</th>			
			<td><input type="text" class="textfield2" id="" name="toDate"  placeholder="MM/YYYY"/></td>
		</tr>
		<tr>
			<th>Contact</th>			
			<td><input type="text" class="textfield2" id="organizationContactNo" name="organizationContactNo" /></td>
			<th>Website </th>			
			<td><input type="text"  class="textfield2" id="organizationWebSite" name="organizationWebSite" /></td>
		</tr>		
		<tr>
			<td colspan="4">
				<input id="staffPreviousWorkDetailsButton" class="addbtn" type="button" onclick="addMoreExperience();"/>	
			</td>
		</tr>
	</table>	
	</div>		
	<table id="fileTable2" cellspacing="0" cellpadding="0" class="midsec1">
		<thead>
			<tr>
				<th>Upload Previous Organization Document</th>
			</tr>
		</thead>
		<tbody>
			<tr>  				
 				<td>
 					<input type="file" name="resource.uploadFile.experienceRelatedFile"/>
					<input id="addFile2" class="addFileClassName addbtn" type="button" />				
 				</td>      
			</tr>
		</tbody>
	</table>	
</div>

<h3 style="color:white; margin-left: 1%;cursor: pointer;">Work Shop & Training
<img id="i9" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'workShopAndTrainingDetails',this.id);"/></h3>
<div id="workShopAndTrainingDetails" >
	<div id="clonedWorkShopAndTrainingDetailsDiv">
	<table class="workShopAndTrainingDetailsTableClass midsec" id="workShopAndTrainingDetailsTable"  cellspacing="0" cellpadding="0" >
		<tr>
			<th colspan="6" style="text-align: center;text-decoration: underline;">
				Work Shop & Training Details
			</th>
		</tr>
		<tr>			
			<th>Subject</th>
			<th>Venue</th>	
			<th>From Date</th>	
			<th>To Date</th>
			<th>Organized By</th>
			<th>Duration</th>		
		</tr>
		<tr>
			<td><input type="text" class="textfield1" id="subject" name="subject" /></td>
			<td><input type="text" class="textfield1" id="venue" name="venue"/></td>
			<td><input type="text" class="trainingDate" id="trainingFromDate0" name="trainingFromDate" onblur="calculateDateDifference(0);" /></td>
			<td><input type="text" class="trainingDate" id="trainingToDate0" name="trainingToDate" onblur="calculateDateDifference(0);" /></td>
			<td><input type="text" class="textfield1" id="organizedBy" name="organizedBy"/></td>
			<td><input type="text" class="textfield1" id="duration0" name="duration" readonly="readonly"/></td>
		</tr>	
		
		<tr>
			<td colspan="6">
				<input id="addMoreWorkShopButton" class="addbtn" type="button" onclick="addMoreWorkShop();"/>	
			</td>			
		</tr>
	</table>	
	</div>	
</div>


<h3 style="color:white; margin-left: 1%;cursor: pointer;">Awards & Recognization
<img id="i10" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'awardsAndRecognization',this.id);"/></h3>
<div id="awardsAndRecognization" >
	<div id="awardsAndRecognizationsDiv">
	<table class="awardsAndRecognizationClass midsec" id="awardsAndRecognizationTable"  cellspacing="0" cellpadding="0" >
		<tr>
			<th colspan="6" style="text-align: center;text-decoration: underline;">
				Awards & Recognization
			</th>
		</tr>
		<tr>
			<th>Award Name</th>			
			<th>Presented By</th>			
			<th>Presented On</th>						
		</tr>
		<tr>
			<td><input type="text" class="textfield2" id="awardName" name="awardName" /></td>
			<td><input type="text" class="textfield2" id="presentedBy" name="presentedBy"/></td>						
			<td><input type="text" class="presentedOnClass" id="presentedOn" name="presentedOn"/></td>						
		</tr>		
		<tr>
			<td colspan="4">
				<input id="addMoreWorkShopButton" class="addbtn" type="button" />	
			</td>			
		</tr>
	</table>	
	</div>	
</div>


<h3 style="color:white; margin-left: 1%;cursor: pointer;">Publication Details 
<img id="i11" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'publicationsDetailsDiv',this.id);"/></h3>
<div id="publicationsDetailsDiv" >
	<div id="clonePublicationsDetails" >
	<table id="publicationsDetails" cellspacing="0" cellpadding="0" class="midsec">
		<tr>
			<th colspan="4" style="text-align: center;text-decoration: underline;">
				Publications Details
			</th>
		</tr>
		<tr>
			<th>Title/Publication Name</th>			
			<td><input id="publicationName" name="publicationName" class="textfield2" /></td>
			<th>Date of Publication</th>			
			<td><input class="textfield2" id="dateOfPublication" name="dateOfPublication" placeholder="MM/YYYY" /></td>
		</tr>
		<tr>
			<th>Co Publisher</th>			
			<td><input type="text" id="coPublisher" name="coPublisher" class="textfield2" /></td>
			<th>Publication Description</th>	
			<td>
				<textarea id="publicationDesc" name="publicationDesc" class="txtarea" ></textarea>				
			</td>
		</tr>		
		<tr>
			<td colspan="4">
				<input type="button" id="addPublicationsDetailsButton" onclick="new_publish();" class="addbtn" />
			</td>
		</tr>
	</table>
	</div>
	<table cellspacing="0" cellpadding="0" class="midsec1" >
		<tr>	
			<th style="text-align: center;text-decoration: underline;">
					Upload Publication Document
			</th>
		<tr>	
		<tr>			
			<td>	 				
				<input type="file" name="resource.uploadFile.publicationRelatedFile"/>
				<input id="addFile3" class="addFileClassName addbtn" type="button" />	
			</td>
		</tr>
	</table>
</div>	


<h3 style="color:white; margin-left: 1%;cursor: pointer;">Confidential
<img id="i12" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'confidential',this.id);"/></h3>
<div id="confidential" >
	<div id="confidentialDiv">
	<table class="confidentialClass midsec" id="confidentialTable"  cellspacing="0" cellpadding="0" >
		<tr>
			<th colspan="6" style="text-align: center;text-decoration: underline;">
				Confidential
			</th>
		</tr>
		<tr>
			<th>Note</th>			
			<td>
				<textarea id="confidentialInformation" name="confidentialInformation" class="txtarea"  rows="8" cols="100" ></textarea>		
			</td>						
		</tr>		
	</table>	
	</div>	
</div>	
	
<h3 style="color:white; margin-left: 1%;cursor: pointer;">Upload Image
<img id="i13" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'uploadImage',this.id);"/></h3>
<div id="uploadImage" name="uploadImage">
	<table cellspacing="0" cellpadding="0" class="midsec1">			
		<tr>
			<th colspan="2" style="text-align: center;text-decoration: underline;">Staff's Image</th>
		</tr>	
		<tr>
		<td colspan ="2">  			
			<div id="uploadImage" name="uploadImage" >	
			<h3>Please select image file<img class="required" src="/icam/images/required.gif" alt="Required"></h3>
			 <img id="preview" src="images/upload.png" style="width:200px;height:200px;"/>
			 	<div class="container">
		            <div class="upload_form_cont">	
			            <div>
			                 <div>
			                 	<input type="file" name="resource.image.imageData" id="image_upload" onchange="fileSelected();"/>
			                 </div> 
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
			<c:if test="${submitResponse ne null}">				
			<c:if test="${submitResponse eq 'Success'}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">Employee Successfully Added</span>	
				</div>
			</c:if>
			<c:if test="${submitResponse eq 'Fail'}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">Problem Occured While Adding</span>	
				</div>
			</c:if>		
		</c:if>
			<input type="submit" id="submit" name="submit" value="Submit" class="submitbtn" onclick="return validateEmployeeForm();"/>
			<input type="reset" id="clear" class="clearbtn" value="Clear" readonly="readonly"/>			
</div>
</form:form>	
<script type="text/javascript">
$(document).ready(function() {
	$("#presentAddressCityVillage").autocomplete({
	 		source: '/icam/getCityList.html'}); 
	$("#permanentAddressCityVillage").autocomplete({
		source: '/icam/getCityList.html'});
	$("#presentAddressDistrict").autocomplete({
 		source: '/icam/getDistrictList.html'}); 
$("#permanentAddressDistrict").autocomplete({
	source: '/icam/getDistrictList.html'});
});
</script>
</body>

</html>