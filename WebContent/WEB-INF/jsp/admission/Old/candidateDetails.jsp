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
<link rel="stylesheet" href="/icam/css/admission/admissionForm.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script src="/icam/js/common/upload.js"></script>
<link rel="stylesheet" type="text/css" href="/icam/Cal/default.css"/>
<script src="/icam/Cal/zebra_datepicker.js"></script>

<script src="/icam/js/admission/admissionForm.js"></script>
<script src="/icam/js/admission/validateCandidateDetails.js"></script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Candidate Details- ${candidate.resource.firstName} ${candidate.resource.middleName} ${candidate.resource.lastName}(${candidate.admissionFormId})
	</h1>
</div>


		
		<table cellspacing="0" cellpadding="0" class="midsec"  >			
			<tr>
				<th colspan="6" style="text-align: center;text-decoration: underline;">
					Academic Year : ${candidate.academicYear.academicYearName}
					<input type="hidden" name="academicYear.academicYearName" id="year" readonly="readonly" value="${candidate.academicYear.academicYearCode}"/>	
				</th>
			</tr>
			<tr>
				<th>Class</th>				
				<td>
					<select id="standard" name="standard.standardName" class="defaultselect" disabled="disabled">
						<option VALUE="${candidate.standard.standardName}">${candidate.standard.standardName}</option>
					</select>
				</td>
			</tr>			
		</table>
		
	<h3 style="color:white; margin-left: 1%;cursor: pointer;">Personal Details
	<img id="i1" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'personalDetails',this.id);"/></h3>
	<div id="personalDetails">
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th colspan="6" style="text-align: center;text-decoration: underline;" >Candidate's Details</th>
			</tr>			
			<tr>
				<th>First Name</th>				
				<td><input type="text" class="textfield2" id="firstName" name="resource.firstName" value="${candidate.resource.firstName}" readonly="readonly"/></td>
				<th>Middle Name</th>				
				<td><input type="text" class="textfield2" id="middleName" name="resource.middleName"  value="${candidate.resource.middleName}" readonly="readonly"></td>
				<th>Last Name</th>				
				<td><input type="text" class="textfield2" id="lastName" name="resource.lastName"  value="${candidate.resource.lastName}" readonly="readonly"/></td>
			</tr>			
			<tr>
				<th>Gender</th>				
				<td>${candidate.resource.gender eq 'M' ?'Male':'Female'} </td>
				<th>Date Of Birth</th>				
				<td><input type="text" class="textfield2" name="resource.dateOfBirth" value="${candidate.resource.dateOfBirth}" readonly="readonly"/></td>
				<th>Blood Group</th>					
				<td>
					<select id="bloodGroup" name="resource.bloodGroup" class="defaultselect" disabled="disabled">
						<OPTION VALUE="" >${candidate.resource.bloodGroup}</option>
					</select>
				</td>
			</tr>			
			<tr>
				<th>Category</th>				
				<td>
					<select id="category" name="socialCategory.socialCategoryName" class="defaultselect" disabled="disabled">
						<OPTION VALUE="${candidate.socialCategory.socialCategoryName}" >${candidate.socialCategory.socialCategoryName}</option>
					</select>
				</td>
				
				<th>Religion</th>				
					<td > <input class="textfield2" type="text" id="religion" name="resource.religion" value="${candidate.resource.religion}" readonly="readonly"/> </td>		
			</tr>
			<tr>
				<th>Nationality</th>				
				<td><input class="textfield2" type="text" id="nationality" name="resource.nationality" value="${candidate.resource.nationality}" readonly="readonly"/></td>
				<th>Identification Mark</th> 
				<td > <input class="textfield2" type="text" id="identificationMark" name="resource.identificationMark" value="${candidate.resource.identificationMark}" readonly="readonly"/> </td>		
			</tr>
			
			<tr>
				<th colspan="6" style="text-align: center;text-decoration: underline;">Father's Details </th>
			</tr>			
			<tr>
				<th>First Name</th>				
				<td><input type="text" class="textfield2"id="fatherFirstName" name="resource.fatherFirstName" value="${candidate.resource.fatherFirstName}" readonly="readonly"/></td>
				<th>Middle Name</th>				
				<td><input type="text"class="textfield2" id="fatherMiddleName" name="resource.fatherMiddleName" value="${candidate.resource.fatherMiddleName}" readonly="readonly"></td>
				<th>Last Name</th>				
				<td><input type="text" class="textfield2" id="fatherLastName" name="resource.fatherLastName" value="${candidate.resource.fatherLastName}" readonly="readonly"/></td>
			</tr>			
			<tr>
				<th colspan="6" style="text-align: center;text-decoration: underline;">Mother's Details </th>
			</tr>			
			<tr>
				<th>First Name</th>				
				<td><input type="text" class="textfield2" id="motherFirstName" name="resource.motherFirstName" value="${candidate.resource.motherFirstName}" readonly="readonly"/></td>
				<th>Middle Name</th>				
				<td><input type="text" class="textfield2" id="motherMiddleName" name="resource.motherMiddleName" value="${candidate.resource.motherMiddleName}" readonly="readonly"></td>
				<th>Last Name</th>				
				<td><input type="text" class="textfield2" id="motherLastName" name="resource.motherLastName" value="${candidate.resource.motherLastName}" readonly="readonly"/></td>
			</tr>	
			<tr>
				<th colspan="6" style="text-align: center;text-decoration: underline;">Guardian's Details</th>
			</tr>			
			<tr>
				<th>First Name</th>	
				<td><input type="text" class="textfield2" id="guardianFirstName" name="guardianFirstName" value="${candidate.guardianFirstName}" readonly="readonly"/></td>
				<th>Middle Name</th>				
				<td><input type="text" class="textfield2" id="guardianMiddleName" name="guardianMiddleName" value="${candidate.guardianMiddleName}" readonly="readonly"></td>
				<th>Last Name</th>	
				<td><input type="text" class="textfield2" id="guardianLastName" name="guardianLastName" value="${candidate.guardianLastName}" readonly="readonly"/></td>
			</tr>
			<tr>
				<th>Relationship <br> With Guardian</th>	
				<td><input type="text" class="textfield2" id="relationshipWithGuardian" name="relationshipWithGuardian" value="${candidate.relationshipWithGuardian}" readonly="readonly"/></td>
			</tr>
					
			<tr>
				<th colspan="6" style="text-align: center;text-decoration: underline;">Contact Details</th>
			</tr>			
			<tr>
				<th>Contact Number</th>								
				<td><input type="text" class="textfield2" name="resource.mobile" id="mobile" value="${candidate.resource.mobile}" readonly="readonly" />
				<th>E-Mail</th>				
				<td><input type="text" class="textfield2" id="emailId" name="resource.emailId" value="${candidate.resource.emailId}" readonly="readonly"></td>
				<td colspan="4">
				</td>
			</tr>	
			<tr>
				<th colspan="6" style="text-align: center;text-decoration: underline;">Occupation & Monthly Income of Parents/Guardian</th>
			</tr>	
			<tr>
				<th colspan="6"> 
						<table cellspacing="0" cellpadding="0" class="midsec1">
							<tr>
								<th colspan="8"></th>
							</tr>
							<tr>
								<th colspan="8"></th>
							</tr>
							<tr>
								<th></th>
								<th>Occupation</th>
								<th colspan="6" style="text-align: center;">INCOME</th>
							</tr>
							<tr>
								<th colspan="2"/><th>Agriculture</th><th>Business</th><th>Salary</th><th>Pension</th><th>Others</th><th>Total</th>
							</tr>
							<tr>
								<th>Father</th><th><input type="text" class="textfield2" name="fatherOccupation" id="fatherOccupation" value="${candidate.fatherOccupation}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="fatherAgricultureIncome" id="fatherAgricultureIncome" value="${candidate.fatherAgricultureIncome}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="fatherBusinessIncome" id="fatherBusinessIncome" value="${candidate.fatherBusinessIncome}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="fatherSalaryIncome" id="fatherSalaryIncome" value="${candidate.fatherSalaryIncome}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="fatherPensionIncome" id="fatherPensionIncome" value="${candidate.fatherPensionIncome}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="fatherOthersIncome" id="fatherOthersIncome" value="${candidate.fatherOthersIncome}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="fatherTotalIncome" id="fatherTotalIncome" value="${candidate.fatherTotalIncome}" readonly="readonly"/></th>
							<tr>
								<th>Mother</th><th><input type="text" class="textfield2" name="motherOccupation" id="motherOccupation" value="${candidate.motherOccupation}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="motherAgricultureIncome" id="motherAgricultureIncome" value="${candidate.motherAgricultureIncome}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="motherBusinessIncome" id="motherBusinessIncome" value="${candidate.motherBusinessIncome}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="motherSalaryIncome" id="motherSalaryIncome" value="${candidate.motherSalaryIncome}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="motherPensionIncome" id="motherPensionIncome" value="${candidate.motherPensionIncome}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="motherOthersIncome" id="motherOthersIncome" value="${candidate.motherOthersIncome}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="motherTotalIncome" id="motherTotalIncome" value="${candidate.motherTotalIncome}" readonly="readonly"/></th>
							</tr>
							<tr>
								<th>Guardian</th><th><input type="text" class="textfield2" name="guardianOccupation" id="guardianOccupation" value="${candidate.guardianOccupation}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="guardianAgricultureIncome" id="guardianAgricultureIncome" value="${candidate.guardianAgricultureIncome}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="guardianBusinessIncome" id="guardianBusinessIncome" value="${candidate.guardianBusinessIncome}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="guardianSalaryIncome" id="guardianSalaryIncome" value="${candidate.guardianSalaryIncome}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="guardianPensionIncome" id="guardianPensionIncome" value="${candidate.guardianPensionIncome}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="guardianOthersIncome" id="guardianOthersIncome" value="${candidate.guardianOthersIncome}" readonly="readonly"/></th><th><input type="text" class="textfieldRs" name="guardianTotalIncome" id="guardianTotalIncome" value="${candidate.guardianTotalIncome}" readonly="readonly"/></th>
							</tr>
						</table>
				</th>
			</tr>
			<tr>
				<th colspan="6" style="text-align: center;text-decoration: underline;">If you belong to defence category,Please furnish the following details:</th>
			</tr>
			<tr>
				<th colspan="6"> 
						<table cellspacing="0" cellpadding="0" class="midsec">
						<tr>
							<th colspan="7"></th>
						</tr>
						<tr>
							<th>Whether serving <br> or Ex- service personnel</th>
							<td><input type="text" class="textfield2" name="exServicePersonnel" id="exServicePersonnel" value="${candidate.exServicePersonnel}"/></td>
							<th>Service(Army/ <br> Navy/Air Force)</th>
							<td><input type="text" class="textfield2" name="service" id="service" value="${candidate.service}" readonly="readonly"/></td>
						</tr>
						<tr>
							<th>Service Number</th>
							<td><input type="text" class="textfield2" name="serviceNumber" id="serviceNumber" value="${candidate.serviceNumber}" readonly="readonly"/></td>
							<th>Rank</th>
							<td><input type="text" class="textfield2" name="rank" id="rank" value="${candidate.rank}" readonly="readonly"/></td>
						</tr>	
						<tr>
							<th>Date of Enrolment/
							Commission</th>
							<td><input type="text" class="textfield2" name="dateOfEnrolment"  value="${candidate.dateOfEnrolment}" readonly="readonly"/></td>
							<th>Date of Discharge</th>
							<td><input type="text" class="textfield2" name="dateOfDischarge" value="${candidate.dateOfDischarge}" readonly="readonly"/></td>
						</tr>
						<tr>
							<th>Name of the Record Office</th>
							<td><input type="text" class="textfield2" name="recordOfficeName" id="recordOfficeName" value="${candidate.recordOfficeName}" readonly="readonly"/></td>
						</tr>
						</table>
				</th>
			</tr>	
		</table>
	</div>
	
			
	<h3 style="color:white; margin-left: 1%;cursor: pointer;">Location 
	<img id="i2" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'location',this.id);"/></h3>
	<div id="location">
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th colspan="4" style="text-align: center;text-decoration: underline;">Present Address</th>
			</tr>
			<tr>
				<th>Address</th>				
				<td colspan="5"><input type="text" class="textfield1" id="presentAddress1" name="address.presentAddressLine" value="${candidate.address.presentAddressLine}" readonly="readonly"/></td>
				
			</tr>			
			<tr>				
				<th>Landmark</th>				
				<td><input type="text" class="textfield2" id="presentAddressLandmark" name="address.presentAddressLandmark" value="${candidate.address.presentAddressLandmark}" readonly="readonly"/></td>
				<th>City/Village</th>				
				<td><input type="text" class="textfield2" id="presentAddressCityVillage" name="address.presentAddressCityVillage" value="${candidate.address.presentAddressCityVillage}" readonly="readonly"/></td>
			</tr>			
			<tr>
				<th>Post Office</th>				
				<td><input type="text" class="textfield2" id="presentAddressPostOffice" name="address.presentAddressPostOffice" value="${candidate.address.presentAddressPostOffice}" readonly="readonly"/></td>
				<th>Police Station</th>				
				<td><input type="text" class="textfield2" id="presentAddressPoliceStation" name="address.presentAddressPoliceStation" value="${candidate.address.presentAddressPoliceStation}" readonly="readonly"/></td>
			</tr>			
			<tr>				
				<th>Pin code</th>				
				<td><input type="text" class="textfield2" id="presentAddressPinCode" name="address.presentAddressPinCode" value="${candidate.address.presentAddressPinCode}" readonly="readonly"/></td>
				<th>District</th>				
				<td><input type="text" class="textfield2" id="presentAddressDistrict" name="address.presentAddressDistrict" value="${candidate.address.presentAddressDistrict}" readonly="readonly"/></td>			
			</tr>			
			<tr>
				<th>Country</th>				
				<td>
					<select id="presentAddressCountry" name="address.presentAddressCountry" class="presentAddressCountry" disabled="disabled">
						<OPTION VALUE="${candidate.address.presentAddressCountry}">${candidate.address.presentAddressCountry}</option>
					</select>
				</td>
				<th>State</th>				
				<td>
					<select name="address.presentAddressState" id="presentAddressState"  class="presentAddressState" disabled="disabled">
						<OPTION VALUE="${candidate.address.presentAddressState}" >${candidate.address.presentAddressState}</option>
					</select>  
				</td>				
			</tr>			
			<tr>
				<th colspan="4" style="text-align: center;text-decoration: underline;">Permanent Address</th>				
			</tr>
			<tr>				
				<td colspan="4"></td>
			</tr>
				<tr>
					<th>Address</th>				
					<td colspan="5"><input type="text" class="textfield1" id="permanentAddress1" name="address.permanentAddressLine" value="${candidate.address.permanentAddressLine}" readonly="readonly"/></td>
				</tr>			
				<tr>				
					<th>Landmark</th>				
					<td><input type="text" class="textfield2" id="permanentAddressLandmark" name="address.permanentAddressLandmark" value="${candidate.address.permanentAddressLandmark}" readonly="readonly"/></td>
					<th>City/Village</th>				
					<td><input type="text" class="textfield2" id="permanentAddressCityVillage" name="address.permanentAddressCityVillage" value="${candidate.address.permanentAddressCityVillage}" readonly="readonly"/></td>
				</tr>			
				<tr>				
					<th>Post Office</th>				
					<td><input type="text" class="textfield2" id="permanentAddressPostOffice" name="address.permanentAddressPostOffice" value="${candidate.address.permanentAddressPostOffice}" readonly="readonly"/></td>
					<th>Police Station</th>				
					<td><input type="text" class="textfield2" id="permanentAddressPoliceStation" name="address.permanentAddressPoliceStation" value="${candidate.address.permanentAddressPoliceStation}" readonly="readonly"/></td>
				</tr>			
				<tr>				
					<th>Pincode</th>				
					<td><input type="text" class="textfield2" id="permanentAddressPinCode" name="address.permanentAddressPinCode" value="${candidate.address.permanentAddressPinCode}" readonly="readonly"/></td>
					<th>District</th>				
					<td><input type="text" class="textfield2" id="permanentAddressDistrict" name="address.permanentAddressDistrict" value="${candidate.address.permanentAddressDistrict}" readonly="readonly"/></td>				
				</tr>			
				<tr>
					<th>Country</th>				
					<td>
						<select id="permanentAddressCountry" class="permanentAddressCountry" name="address.permanentAddressCountry" disabled="disabled">
							<OPTION VALUE="${candidate.address.permanentAddressCountry}">${candidate.address.permanentAddressCountry}</option>
						</select>
					</td>				
					<th>State</th>				
					<td>
						<select name="address.permanentAddressState" id="permanentAddressState" class="permanentAddressState" disabled="disabled">
							<OPTION VALUE="${candidate.address.permanentAddressState}" >${candidate.address.permanentAddressState}</option>						
						</select> 
					</td>				
				</tr>		
	
		</table>
	</div>
	
	<h3 style="color:white; margin-left: 1%;cursor: pointer;">Previous Educational Details 
	<img id="i3" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'studentPreviousEducationalDetails',this.id);"/></h3>
	<div id="studentPreviousEducationalDetails">
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th colspan="4" style="text-align: center;text-decoration: underline;">Previous School Details</th>
			</tr>
			<tr>
				<th>Previous School Name				
				<td><input type="text" class="textfield2" id="previousSchoolName" name="previousSchoolName" value="${candidate.previousSchoolName}" readonly="readonly"/></td>
				<th>Contact</th>				
				<td><input type="text" class="textfield2" id="previousSchoolContact" name="previousSchoolContact" value="${candidate.previousSchoolContact}" readonly="readonly"/></td>
			</tr>			
			<tr>
				<th>Website</th>				
				<td><input type="text" class="textfield2" id="previousSchoolWebsite" name="previousSchoolWebsite" value="${candidate.previousSchoolWebsite}" readonly="readonly"/></td>							
	 			<th>Class</th>	 			
	 			<td><input type="text" class="textfield2" name="previousSchoolStandard" id="previousSchoolStandard" value="${candidate.previousSchoolStandard}" readonly="readonly"/></td>      
			</tr>	
				
		</table>	
	</div>
	
	<h3 style="color:white; margin-left: 1%;cursor: pointer;">Examination Details 
	<img id="i4" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'examinationDetails',this.id);"/></h3>
	<div id="examinationDetails">
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th colspan="4" style="text-align: center;text-decoration: underline;">Examination Details</th>
			</tr>
			<tr>
				<th>Centre for Entrance Examination:(if one)<br>				
				<td>
					<select id="examinationCentre" name="examinationCentre" class="defaultselect">
					<OPTION value="${candidate.examinationCentre}">${candidate.examinationCentre}</option>
					</select>
			</tr>			
			<tr>
				<th>Medium for Entrance Examination: <br>(For Class VI-English,Bengali,Hindi-Any One)<br>(For Class IX-English Only)</th>				
				<td><input type="text" class="textfield2" id="examMedium" name="examinationMedium" value="${candidate.examinationMedium}" readonly="readonly"/></td>							
	 			</tr>	
			<tr>
	 			<th>Are you prepared to send your son/ward to any other <br>
	 			    sainik School (Other than Sainik School Purulia)if allotted?<br>
	 			    If so,give name of the school in order of preference.</th>	 			
	 			<td>
 				1)<input type="text" class="textfield2" name="preferenceSchool1" id="preferenceSchool1" value="${candidate.preferenceSchool1}" readonly="readonly"/><p><br>
 				2)<input type="text" class="textfield2" name="preferenceSchool2" id="preferenceSchool2" value="${candidate.preferenceSchool2}" readonly="readonly"/><p><br>
 				3)<input type="text" class="textfield2" name="preferenceSchool3" id="preferenceSchool3" value="${candidate.preferenceSchool3}" readonly="readonly"/>
	 			</td>      
			</tr>		
		</table>	
	</div>
	
	<h3 style="color:white; margin-left: 1%;cursor: pointer;">Bank Details 
	<img id="i5" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'bankDetails',this.id);"/></h3>
	<div id="bankDetails">
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th colspan="4" style="text-align: center;text-decoration: underline;">Details of Bank Draft/IPO submitted(for Application Forms downloaded from Website)</th>
			</tr>
			<tr>
				<th>D.D./I.P.O. No.</th>				
				<th>Date</th>		
				<th>Amount</th>		
				<th>Name of the Issuing branch</th>		
			</tr>			
			<tr>
				<td><input type="text" class="textfield2" id="bankDdIpoNo" name="bankDdIpoNo" value="${candidate.bankDdIpoNo}" readonly="readonly"/></td>	
				<td><input type="text" class="textfield2" name="bankDate" value="${candidate.bankDate}" readonly="readonly"/></td>	
				<td><input type="text" class="textfield2" id="bankAmount" name="bankAmount"value="${candidate.bankAmount}" readonly="readonly"/></td>		
				<td><input type="text" class="textfield2" id="issuingBankBranchName" name="issuingBankBranchName" value="${candidate.issuingBankBranchName}" readonly="readonly"/></td>			
	 		</tr>	
		</table>	
	</div>
	
	<h3 style="color:white; margin-left: 1%;cursor: pointer;">Cast/Trible
	<img id="i6" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'uploadImage',this.id);"/></h3>
	<div id="uploadImage">
	<table cellspacing="0" cellpadding="0" class="midsec">	
			<tr>
				<th colspan="2" style="text-align: center;text-decoration:">Whether the candidate belongs to Schedule Cast/Trible:<br>
				 </th>
				 
				 <td>
				 	<input type="text" class="textfield2" name="scheduledCastCommunity" id="scheduledCastCommunity" value="${candidate.scheduledCastCommunity}" readonly="readonly"/>
				 </td>
			</tr>
			<tr><td> </td></tr>	</table>
	</div>
	
	<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
		
	</div>	
</body>
</html>