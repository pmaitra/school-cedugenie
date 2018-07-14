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
<title>Edit Employee Details</title>
<link rel="stylesheet" href="/icam/css/erp/employeeDetails.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/Cal/zebra_datepicker.js"></script>
<link rel="stylesheet" href="/icam/Cal/default.css" type="text/css">
<script src="/icam/js/erp/validateEmployeeDetails.js"></script>
<script src="/icam/js/common/upload.js"></script>
<script src="/icam/js/erp/makeEmployeeDetailsEditable.js"></script>
<script src="/icam/js/erp/editEmployeeDetails.js"></script>
<script src="/icam/js/erp/employeeAddAndEdit.js"></script>

<script>
		$(document).ready(function() {
			$(".trainingDate").each(function(){
				$(this).Zebra_DatePicker();
			    
			    $(this).Zebra_DatePicker({
			    	  format: 'd/m/Y'
			    	});
			});
			
		});
		/* $(document).ready(function() {
			$('#trainingToDate0').Zebra_DatePicker();
			 
			$('#trainingToDate0').Zebra_DatePicker({
				format: 'd/m/Y',
				direction: true
			});
		}); */
	</script>
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Edit Employee Details
	</h1>
</div>	
<%-- <form:form name="employeeDetailsForm" id="employeeDetailsForm"  enctype="multipart/form-data" action="submitEmployeeDetails.html" method="POST">		 --%>
<h3 style="color:white; margin-left: 1%;cursor: pointer;">Basic Details
<img id="i1" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'basicDetails',this.id);"/></h3>	
<div id="basicDetails">	
	<form:form name="updateEmployeeBasicDetails" id="updateEmployeeBasicDetails" action="updateEmployeeBasicDetails.html">			
	<table cellspacing="0" cellpadding="0" class="midsec" >	
	<tr>
		<th id="tdata">User Id<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td><input name="resource.userId" id="userId" value="${employeeDetails.resource.userId}" readonly="readonly" class="textfield2"/></td>
		<th>Employee Code<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td><input name="employeeCode" id="employeeCode" class="textfield2" value="${employeeDetails.employeeCode}" readonly="readonly"/></td>	
	</tr>
	
	<tr>
		<th>Gender<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<select name="resource.gender" class="defaultselect" id="gender" disabled="disabled">
				<option value="M" ${employeeDetails.resource.gender eq 'M' ? 'selected=selected' : ''} >Male</option>
				<option value="F" ${employeeDetails.resource.gender eq 'F' ? 'selected=selected' : ''}>Female</option>
			</select>
		</td>
		<th>Date Of Birth<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>			
			<input type="text" class="textfield2" id="dateOfBirth" value="${employeeDetails.resource.dateOfBirth}"  name="resource.dateOfBirth" disabled="disabled"/>
		</td>		
	</tr>
	
	<tr>	
		<th>Date Of Join<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td><input name="dateOfJoining" id="dateOfJoining" value="${employeeDetails.dateOfJoining}" disabled="disabled" class="textfield2"/></td>		
		<th>Date Of Retirement<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td><input name="dateOfRetirement" id="dateOfRetirement" value="${employeeDetails.dateOfRetirement}" disabled="disabled" class="textfield2"/></td>			
	</tr>	
	
	<tr>
		<th>Employee Type<img class="required" src="/icam/images/required.gif" alt="Required"></th>
			<td>
				<select id="employeeTypeName" name="employeeType.employeeTypeName" class="defaultselect" disabled="disabled" onchange="getDesignationForResourceType(this);">
					<c:forEach var="et" items="${employee.employeeTypeList}">
						<c:if test="${et.employeeTypeName eq employeeDetails.employeeType.employeeTypeName}">
							<option value="<c:out value="${et.employeeTypeCode}"/>"><c:out value="${et.employeeTypeName}"/></option>									
						</c:if>
					</c:forEach>
					 <c:forEach var="et" items="${employee.employeeTypeList}">
						<c:if test="${et.employeeTypeName ne employeeDetails.employeeType.employeeTypeName}">
							<option value="<c:out value="${et.employeeTypeCode}"/>"><c:out value="${et.employeeTypeName}"/></option>
						</c:if>
					</c:forEach>															
				</select>
			</td>
			<th>Job Type<img class="required" src="/icam/images/required.gif" alt="Required" ></th>		
			<td>
				<select id="jobTypeName" name="jobType.jobTypeName" class="defaultselect" disabled="disabled">
					<c:forEach var="jobt" items="${employee.jobTypeList}">
						<c:if test="${jobt.jobTypeName eq employeeDetails.jobType.jobTypeName}">
							<option value="<c:out value="${jobt.jobTypeCode}"/>"><c:out value="${jobt.jobTypeName}"/></option>									
						</c:if>
					</c:forEach>
					 <c:forEach var="jobt" items="${employee.jobTypeList}">
						<c:if test="${jobt.jobTypeName ne employeeDetails.jobType.jobTypeName}">
							<option value="<c:out value="${jobt.jobTypeCode}"/>"><c:out value="${jobt.jobTypeName}"/></option>
						</c:if>
					</c:forEach>	
				</select>
			</td>		
	</tr>		
	<tr>
		<th>Designation<img class="required" src="/icam/images/required.gif" alt="Required"></th>			
		<td>
			<select id="designationName" name="designation.designationName" class="defaultselect" disabled="disabled">
<%-- 				<c:forEach var="desig" items="${employee.designationList}"> --%>
<%-- 					<c:if test="${desig.designationName eq employeeDetails.designation.designationName}"> --%>
						<option value="<c:out value="${employeeDetails.designation.designationName}"/>"><c:out value="${employeeDetails.designation.designationName}"/></option>								
<%-- 					</c:if> --%>
<%-- 				</c:forEach> --%>
<%-- 				 <c:forEach var="desig" items="${employee.designationList}"> --%>
<%-- 					<c:if test="${desig.designationName ne employeeDetails.designation.designationName}"> --%>
<%-- 						<option value="<c:out value="${desig.designationCode}"/>"><c:out value="${desig.designationName}"/></option> --%>
<%-- 					</c:if> --%>
<%-- 				</c:forEach>	 --%>
			</select>
		</td>		
		<th>Department<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<select id="department" name="department.departmentName" class="defaultselect" disabled="disabled" disabled="disabled">
				<c:forEach var="depart" items="${employee.departmentList}">
					<c:if test="${depart.departmentName eq employeeDetails.department.departmentName}">
						<option value="<c:out value="${depart.departmentCode}"/>"><c:out value="${depart.departmentName}"/></option>									
					</c:if>
				</c:forEach>
				 <c:forEach var="depart" items="${employee.departmentList}">
					<c:if test="${depart.departmentName ne employeeDetails.department.departmentName}">
						<option value="<c:out value="${depart.departmentCode}"/>"><c:out value="${depart.departmentName}"/></option>
					</c:if>
				</c:forEach>	
			</select>
		</td>	
	</tr>
<!-- 	<tr> -->
<!-- 		<th>Salary Template<img class="required" src="/icam/images/required.gif" alt="Required"></th>			 -->
<!-- 		<td> -->
<!-- 			<select	name="salaryTemplate.salaryTemplateCode" class="defaultselect" id="salaryTemplate"  disabled="disabled" onchange="getGradesForSalaryTemplate(this);"> -->
<%-- 				<c:forEach var="salaryTemplate" items="${employee.salaryTemplateList}"> --%>
<%-- 					<c:if test="${salaryTemplate.salaryTemplateCode eq employeeDetails.salaryTemplate.salaryTemplateCode}"> --%>
<%-- 						<option value="<c:out value="${salaryTemplate.salaryTemplateCode}"/>"><c:out value="${salaryTemplate.salaryTemplateName}"/></option>									 --%>
<%-- 					</c:if> --%>
<%-- 				</c:forEach> --%>
<%-- 				 <c:forEach var="salaryTemplate" items="${employee.salaryTemplateList}"> --%>
<%-- 					<c:if test="${salaryTemplate.salaryTemplateCode ne employeeDetails.salaryTemplate.salaryTemplateCode}"> --%>
<%-- 						<option value="<c:out value="${salaryTemplate.salaryTemplateCode}"/>"><c:out value="${salaryTemplate.salaryTemplateName}"/></option> --%>
<%-- 					</c:if> --%>
<%-- 				</c:forEach>						 --%>
<!-- 			</select>						 -->
<!-- 		</td> -->
<!-- 		<th>Pay Band<img class="required" src="/icam/images/required.gif" alt="Required"></th>		 -->
<!-- 		<td> -->
<!-- 			<select	name="salaryTemplate.fixationOfPay.fixationOfPayCode" class="defaultselect" id="payBand" onchange="getAppointmentsToPostsWithGradePay(this);"> -->
<%-- 				<option VALUE="<c:out value="${employeeDetails.salaryTemplate.fixationOfPay.fixationOfPayCode}"/>" ><c:out value="${employeeDetails.salaryTemplate.fixationOfPay.fixationOfPayName} ( ${employeeDetails.salaryTemplate.fixationOfPay.fixationOfPayStartRange}-${employeeDetails.salaryTemplate.fixationOfPay.fixationOfPayEndRange})"/></option>											 --%>
<!-- 			</select> -->
<!-- 		</td>	 -->
<!-- 	</tr>	 -->
<!-- 	<tr> -->
<!-- 		<th>Grade Pay<img class="required" src="/icam/images/required.gif" alt="Required"></th>		 -->
<!-- 		<td> -->
<!-- 			<select id="gradePay" name="salaryTemplate.fixationOfPay.fixationOfPayDetails.fixationOfPayDetailsCode" class="defaultselect" disabled="disabled"> -->
<%-- 				<option VALUE="<c:out value="${employeeDetails.salaryTemplate.fixationOfPay.fixationOfPayDetails.fixationOfPayDetailsCode}"/>" ><c:out value="${employeeDetails.salaryTemplate.fixationOfPay.fixationOfPayDetails.appointmentToPostsWithGradePay}"/></option>										 --%>
<!-- 			</select> -->
<!-- 		</td> -->
<!-- 		<th>Basic Pay<img class="required" src="/icam/images/required.gif" alt="Required"></th>		 -->
<!-- 		<td> -->
<%-- 			<input type="text" class="textfield2" value="${employeeDetails.basicPay}" id="basicPay" name="basicPay" readonly="readonly"/> --%>
<!-- 		</td>	 -->
<!-- 	</tr> -->
	<tr>
		<th>Qualification Summary</th>		
		<td>
			<input type="text" class="textfield2" id="qualificationSummary" value="${employeeDetails.qualificationSummary}" name="qualificationSummary" readonly="readonly"/>
		</td>		
	</tr>
	<tr>
		<td colspan="4"><input type="button" class="editbtn" id="basicDetailsTableEdit" name="edit" value="Edit" onclick="makeBasicDetailsFieldEditable();" />		
		<input type="submit" id="basicDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="submitbtn" onclick="return validateEmployeeBasicDetails();"/>
		<input type="reset" id="basicDetailsTableCancel" class="clearbtn" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeBasicDetailsFieldInEditable();"/></td>
	</tr>	
	</table>
	</form:form>
</div>

<h3 style="color:white; margin-left: 1%;cursor: pointer;">Personal Details
<img id="i2" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'personalDetails',this.id);"/></h3>
<div id="personalDetails" >
	<form:form name="updateEmployeePersonalDetails" id="updateEmployeePersonalDetails" action="updateEmployeePersonalDetails.html">		
	<table cellspacing="0" cellpadding="0" class="midsec">
	<tr>
		<th colspan="4" style="text-align: center;text-decoration: underline;">Employee's Details</th>
	</tr>			
	<tr>
		<th>Employee Name<img class="required" src="/icam/images/required.gif" alt="Required"></th>			
		<td>
			<input type="text" class="textfield2" id="firstName" name="resource.firstName" value="${employeeDetails.resource.firstName}"  placeholder="First Name" readonly="readonly"/>
		</td>
		<td>
			<input type="text" class="textfield2" id="middleName" name="resource.middleName" value="${employeeDetails.resource.middleName}" placeholder="Middle Name" readonly="readonly"/>
		</td>				
		<td>
			<input type="text" class="textfield2" id="lastName" name="resource.lastName" value="${employeeDetails.resource.lastName}" placeholder="Last Name" readonly="readonly"/>
		</td>
	</tr>			
	<tr>
	<th>Blood Group<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<select id="bloodGroup" name="resource.bloodGroup" class="defaultselect" disabled="disabled">
				<option value="A+" ${employeeDetails.resource.bloodGroup eq 'A+' ? 'selected=selected' : ''}>A+</option>
				<option value="A-" ${employeeDetails.resource.bloodGroup eq 'A-' ? 'selected=selected' : ''}>A-</option>
				<option value="B+" ${employeeDetails.resource.bloodGroup eq 'B+' ? 'selected=selected' : ''}>B+</option>
				<option value="B-" ${employeeDetails.resource.bloodGroup eq 'B-' ? 'selected=selected' : ''}>B-</option>
				<option value="AB+" ${employeeDetails.resource.bloodGroup eq 'AB+' ? 'selected=selected' : ''}>AB+</option>
				<option value="AB-" ${employeeDetails.resource.bloodGroup eq 'AB-' ? 'selected=selected' : ''}>AB-</option>
				<option value="O+" ${employeeDetails.resource.bloodGroup eq 'O+' ? 'selected=selected' : ''}>O+</option>
				<option value="O-" ${employeeDetails.resource.bloodGroup eq 'O-' ? 'selected=selected' : ''}>O-</option>
				<option value="UNKNOWN" ${employeeDetails.resource.bloodGroup eq 'UNKNOWN' ? 'selected=selected' : ''}>UNKNOWN</option>
									
			</select>
		</td>
		<th>Nationality<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<input id="nationality" class="textfield2" value="${employeeDetails.resource.nationality}" name="resource.nationality" readonly="readonly"/>
		</td>
	</tr>				
	<tr>
		<th>Mother Tongue<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<input class="textfield2" id="motherTongue" name="resource.motherTongue" value="${employeeDetails.resource.motherTongue}" readonly="readonly"/>
		</td>
		<th>Category<img class="required" src="/icam/images/required.gif" alt="Required"></th>
		<td>
			<select id="category" name="resource.category" class="defaultselect" disabled="disabled">					
				<c:forEach var="sc" items="${employee.socialCategoryList}">
					<c:if test="${sc.socialCategoryName eq employeeDetails.resource.category}">
						<option value="<c:out value="${sc.socialCategoryName}"/>"><c:out value="${sc.socialCategoryName}"/></option>									
					</c:if>
				</c:forEach>
				 <c:forEach var="sc" items="${employee.socialCategoryList}">
					<c:if test="${sc.socialCategoryName ne employeeDetails.resource.category}">
						<option value="<c:out value="${sc.socialCategoryName}"/>"><c:out value="${sc.socialCategoryName}"/></option>
					</c:if>
				</c:forEach>			
			</select>
		</td>
	</tr>	
	<tr>
		<th>Religion<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<input id="religion" class="textfield2" name="resource.religion" value="${employeeDetails.resource.religion}" readonly="readonly"/>
		</td>
		<th>Medical Attention<img class="required" src="/icam/images/required.gif" alt="Required"></th>
		<td>			
			<select name="resource.medicalStatus" class="defaultselect" id="medicalStatus" disabled="disabled">				
				<c:if test="${employeeDetails.resource.medicalStatus eq 'FIT'}">
					<option value="FIT">FIT</option>
					<option value="UNFIT">UNFIT</option>
				</c:if>					
				<c:if test="${employeeDetails.resource.medicalStatus eq 'UNFIT'}">
					<option value="UNFIT">UNFIT</option>
					<option value="FIT">FIT</option>
				</c:if>					
			</select>
		</td>
	</tr>
	<tr>		
		<th>Passport No</th>		
			<td>
				<input id="passportNo" class="textfield2" name="resource.passportNo" value="${employeeDetails.resource.passportNo}" readonly="readonly"/>
			</td>		
		<th>Pan Card No</th>		
		<td>
			<input id="panCardNo" class="textfield2" name="resource.panCardNo" value="${employeeDetails.resource.panCardNo}" readonly="readonly"/>
		</td>		
	</tr>
	<tr>			
		<th>Aadhar Card No.</th>		
		<td>
			<input id="aadharCardNo" class="textfield2" name="resource.aadharCardNo" value="${employeeDetails.resource.aadharCardNo}" readonly="readonly"/>
		</td>
		<th>Voter Card No</th>		
		<td>
			<input id="voterCardNo" class="textfield2" name="resource.voterCardNo" value="${employeeDetails.resource.voterCardNo}" readonly="readonly"/>
		</td>		
	</tr>
	<tr>
		<th>Voting Constituency</th>		
		<td>
			<input id="votingConstituency" class="textfield2" name="resource.votingConstituency" value="${employeeDetails.resource.votingConstituency}" readonly="readonly"/>
		</td>	
		<th>Parliamentary Constituency</th>		
		<td>
			<input id="parliamentaryConstituency" class="textfield2" name="resource.parliamentaryConstituency" value="${employeeDetails.resource.parliamentaryConstituency}" readonly="readonly"/>
		</td>		
	</tr>						
	<tr>
		<th colspan="4" style="text-align: center;text-decoration: underline;">Father's Details</th>
	</tr>			
	<tr>
		<th>Father Name<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<input type="text" class="textfield2" id="fatherFirstName" name="resource.fatherFirstName" value="${employeeDetails.resource.fatherFirstName}" placeholder="First Name" readonly="readonly"/>
		</td>				
		<td>
			<input type="text" class="textfield2" id="fatherMiddleName" name="resource.fatherMiddleName" value="${employeeDetails.resource.fatherMiddleName}" placeholder="Middle Name" readonly="readonly"/>
		</td>		
		<td>
			<input type="text" class="textfield2" id="fatherLastName" name="resource.fatherLastName" value="${employeeDetails.resource.fatherLastName}" placeholder="Last Name" readonly="readonly"/>
		</td>
	</tr>			
	<tr>
		<th>Occupation<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<input name="fatherOccupation" id="fatherOccupation" value="${employeeDetails.fatherOccupation}" class="textfield2" readonly="readonly"/>
		</td>
	</tr>			
	<tr>
		<th colspan="4" style="text-align: center;text-decoration: underline;">Mother's Details</th>
	</tr>			
	<tr>
		<th>Mother Name<img class="required" src="/icam/images/required.gif" alt="Required"></th>		
		<td>
			<input  id="motherFirstName" class="textfield2" name="resource.motherFirstName" value="${employeeDetails.resource.motherFirstName}" placeholder="First Name" readonly="readonly"/>
		</td>			
		<td>
			<input  id="motherMiddleName" class="textfield2" name="resource.motherMiddleName" value="${employeeDetails.resource.motherMiddleName}" placeholder="Middle Name" readonly="readonly"/>
		</td>		
		<td>
			<input id="motherLastName" class="textfield2" name="resource.motherLastName" value="${employeeDetails.resource.motherLastName}" placeholder="last Name" readonly="readonly"/>
		</td>
	</tr>			
		<tr>
			<th>Occupation<img class="required" src="/icam/images/required.gif" alt="Required"></th>			
			<td>
				<input name="motherOccupation" class="textfield2" value="${employeeDetails.motherOccupation}" id="motherOccupation" readonly="readonly"/>
			</td>
		</tr>			
		<tr>
			<th colspan="4" style="text-align: center;text-decoration: underline;">Contact Details</th>
		</tr>			
		<tr>
			<th>Contact Number<img class="required" src="/icam/images/required.gif" alt="Required"></th>							
			<td>
				<input type="text" class="textfield2" name="resource.mobile" id="mobile" value="${employeeDetails.resource.mobile}" size="20" readonly="readonly"/>
			</td>
			<th>E-Mail<img class="required" src="/icam/images/required.gif" alt="Required"></th>			
			<td>
				<input type="text" class="textfield2" id="emailId" name="resource.emailId"  value="${employeeDetails.resource.emailId}" size="20" readonly="readonly"/>
			</td>
		</tr>	
		
			<tr>
			<th colspan="4" style="text-align: center;text-decoration: underline;">Marital Status</th>
		</tr>			
		<tr>
			<th>Marital Status<img class="required" src="/icam/images/required.gif" alt="Required"></th>							
			<td>
				<select name="maritalStatus" class="defaultselect" id="maritalStatus" disabled="disabled">
				<c:if test="${employeeDetails.maritalStatus eq 'MARRIED'}">
					<option value="MARRIED">MARRIED</option>
					<option value="UNMARRIED">UNMARRIED</option>				
				</c:if>
				<c:if test="${employeeDetails.maritalStatus eq 'UNMARRIED'}">
					<option value="UNMARRIED">UNMARRIED</option>
					<option value="MARRIED">MARRIED</option>
				</c:if>					
				</select>
			</td>
			<th>Spouse's Name</th>			
			<td>
				<input type="text" class="textfield2" id="spouseName" name="spouseName" value="${employeeDetails.spouseName}" size="20" readonly="readonly"/>
			</td>
		</tr>
		<tr>
			<td colspan="4"><input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
			<input type="button" class="editbtn" id="personalDetailsTableEdit" name="edit" value="Edit" onclick="makePersonalDetailsFieldEditable();" />
			<input type="submit" id="personalDetailsTableSubmit" name="submit" value="Save" class="submitbtn" style="visibility:collapse" onclick="return validateEmployeePersonalDetails();"/>		
			<input type="reset" id="personalDetailsTableCancel" class="clearbtn" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makePersonalDetailsFieldInEditable();"/></td>					
		</tr>		
	</table>
	</form:form>
</div>		
	
<h3 style="color:white; margin-left: 1%;cursor: pointer;">Qualification Details
<img id="i3" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'QualificationDetails',this.id);"/></h3>
<div id="QualificationDetails">
	<form:form name="staffQualificationDetailsForm" id="staffQualificationDetailsForm" enctype="multipart/form-data" action="updateStaffQualificationDetails.html">		
	<table id="SchoolingDetails" cellspacing="0" cellpadding="0" class="midsec1" >
		<tr>	
			<td colspan="6"><input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
			<input type="button" class="editbtn" id="qualificationDetailsTableEdit" name="edit" value="Edit" onclick="makeQualificationDetailsFieldEditable();" />
			<input type="submit" id="qualificationDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="submitbtn" onclick="return validateEmployeeQualificationDetails();"/>		
			<input type="reset" id="qualificationDetailsTableCancel" class="clearbtn" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeQualificationDetailsFieldInEditable();"/>	</td>
				
		</tr>
		<tr>
			<th colspan="7" style="text-align: center;text-decoration: underline;" >
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
		
		<c:set var="i" value="0" scope="page" />
		<c:forEach var="quali" items="${employeeDetails.qualificationList}">
			<c:if test="${quali.qualificationType eq 'MADHYAMIK'}">
				<c:set var="i" value="1" scope="page" />
				<tr>		
					<td><input type="text" class="textfield1" name="qualificationList[0].examName" value="${quali.qualificationName}" id="examName0" value="10th" readonly="readonly"/></td>
					<td><input type="text" class="textfield1" name="qualificationList[0].specialization" value="${quali.specialization}" id="specialization0" readonly="readonly"></td>
					<td><input type="text" class="textfield1" name="qualificationList[0].schoolCollege" value="${quali.schoolCollege}" id="schoolCollege0" readonly="readonly"></td>
					<td><input type="text" class="textfield1" name="qualificationList[0].boardUniversity" value="${quali.boardUniversity}" id="boardUniversity0" readonly="readonly"></td>
					<td><input type="text" class="textfield1" name="qualificationList[0].marks" value="${quali.marks}" id="marks0" readonly="readonly"></td>
					<td>
						<input type="text" class="textfield1" name="qualificationList[0].passingYear" value="${quali.passingYear}" id="passingYear0" readonly="readonly">
						<input type="hidden" class="textfield1" name="qualificationList[0].qualificationType" id="qualificationType0" value="MADHYAMIK">
					</td>					
				</tr>
			</c:if>
		</c:forEach>
		<c:if test="${i eq 0}">
			<tr>		
				<td><input type="text" class="textfield1" name="qualificationList[0].examName" id="examName0" value="10th" readonly="readonly"/></td>
				<td><input type="text" class="textfield1" name="qualificationList[0].specialization" id="specialization0" readonly="readonly"></td>
				<td><input type="text" class="textfield1" name="qualificationList[0].schoolCollege" id="schoolCollege0" readonly="readonly"></td>
				<td><input type="text" class="textfield1" name="qualificationList[0].boardUniversity" id="boardUniversity0" readonly="readonly"></td>
				<td><input type="text" class="textfield1" name="qualificationList[0].marks" id="marks0" readonly="readonly"></td>
				<td>
					<input type="text" class="textfield1" name="qualificationList[0].passingYear" id="passingYear0">
					<input type="hidden" class="textfield1" name="qualificationList[0].qualificationType" id="qualificationType0" value="MADHYAMIK">
				</td>					
			</tr>
		</c:if>
		
		<c:set var="i" value="0" scope="page" />
		<c:forEach var="quali" items="${employeeDetails.qualificationList}">
			<c:if test="${quali.qualificationType eq 'HS'}">
				<c:set var="i" value="1" scope="page" />	
				<tr>			
					<td><input type="text" class="textfield1" name="qualificationList[1].examName" id="examName1" value="12th" readonly="readonly"/></td>
					<td><input type="text" class="textfield1" name="qualificationList[1].specialization" value="${quali.specialization}" id="specialization1" readonly="readonly"/></td>
					<td><input type="text" class="textfield1" name="qualificationList[1].schoolCollege" value="${quali.schoolCollege}"  id="schoolCollege1" readonly="readonly" /></td>
					<td><input type="text" class="textfield1" name="qualificationList[1].boardUniversity" value="${quali.boardUniversity}" id="boardUniversity1" readonly="readonly"></td>
					<td><input type="text" class="textfield1" name="qualificationList[1].marks" value="${quali.marks}" id="marks1" readonly="readonly" /></td>
					<td>
						<input type="text" class="textfield1" name="qualificationList[1].passingYear" value="${quali.passingYear}" id="passingYear1" readonly="readonly"/>
						<input type="hidden" class="textfield1" name="qualificationList[1].qualificationType" id="qualificationType1" value="HS" readonly="readonly"/>
					</td>				
				</tr>
			</c:if>
		</c:forEach>
		<c:if test="${i eq 0}">
			<tr>			
				<td><input type="text" class="textfield1" name="qualificationList[1].examName" id="examName1" value="12th" readonly="readonly"/></td>
				<td><input type="text" class="textfield1" name="qualificationList[1].specialization" id="specialization1" readonly="readonly"></td>
				<td><input type="text" class="textfield1" name="qualificationList[1].schoolCollege" id="schoolCollege1" readonly="readonly"></td>
				<td><input type="text" class="textfield1" name="qualificationList[1].boardUniversity" id="boardUniversity1" readonly="readonly"></td>
				<td><input type="text" class="textfield1" name="qualificationList[1].marks" id="marks1" readonly="readonly"></td>
				<td>
					<input type="text" class="textfield1" name="qualificationList[1].passingYear" id="passingYear1" readonly="readonly">
					<input type="hidden" class="textfield1" name="qualificationList[1].qualificationType" id="qualificationType1" value="HS">
				</td>				
			</tr>
		</c:if>
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
		<c:set var="i" value="0" scope="page" />		
		<c:forEach var="quali" items="${employeeDetails.qualificationList}">
			<c:if test="${quali.qualificationType eq 'GRADUATION'}">
			<c:set var="i" value="1" scope="page" />
		<tr>			
			<td><input type="text"  class="textfield1" name="qualificationList[2].examName" value="${quali.qualificationName}" id="examName2" readonly="readonly"/></td>
			<td><input type="text"  class="textfield1" name="qualificationList[2].specialization" value="${quali.specialization}"  id="specialization2" readonly="readonly"/></td>
			<td><input type="text"  class="textfield1" name="qualificationList[2].schoolCollege" value="${quali.schoolCollege}" id="schoolCollege2" readonly="readonly"></td>
			<td><input type="text"  class="textfield1" name="qualificationList[2].boardUniversity" value="${quali.boardUniversity}" id="boardUniversity2" readonly="readonly"/></td>
			<td><input type="text"  class="textfield1" name="qualificationList[2].marks" value="${quali.marks}" id="marks2" readonly="readonly"/></td>
			<td>
				<input type="text"  class="textfield1" name="qualificationList[2].passingYear" value="${quali.passingYear}" id="passingYear2" readonly="readonly"/>
				<input type="hidden" class="textfield1" name="qualificationList[2].qualificationType" id="qualificationType2" value="GRADUATION" readonly="readonly">
			</td>
		</tr>	
		</c:if>
		</c:forEach>
		<c:if test="${i eq 0}">
			<tr>			
				<td><input type="text"  class="textfield1" name="qualificationList[2].examName" id="examName2" readonly="readonly"/></td>
				<td><input type="text"  class="textfield1" name="qualificationList[2].specialization" id="specialization2" readonly="readonly"></td>
				<td><input type="text"  class="textfield1" name="qualificationList[2].schoolCollege" id="schoolCollege2" readonly="readonly"></td>
				<td><input type="text"  class="textfield1" name="qualificationList[2].boardUniversity" id="boardUniversity2" readonly="readonly"></td>
				<td><input type="text"  class="textfield1" name="qualificationList[2].marks" id="marks2" readonly="readonly"></td>
				<td>
					<input type="text"  class="textfield1" name="qualificationList[2].passingYear" id="passingYear2" readonly="readonly">
					<input type="hidden" class="textfield1" name="qualificationList[2].qualificationType" id="qualificationType2" value="GRADUATION">
				</td>
			</tr>		
		</c:if>		
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
		<c:set var="i" value="0" scope="page" />	
		<c:forEach var="quali" items="${employeeDetails.qualificationList}">
			<c:if test="${quali.qualificationType eq 'POSTGRADUATION'}">
				<c:set var="i" value="1" scope="page" />
			<tr>			
				<td><input type="text" class="textfield1"  name="qualificationList[3].examName" value="${quali.qualificationName}" id="examName3" readonly="readonly"/></td>
				<td><input type="text" class="textfield1"  name="qualificationList[3].specialization" value="${quali.specialization}" id="specialization3" readonly="readonly"></td>
				<td><input type="text" class="textfield1"  name="qualificationList[3].schoolCollege" value="${quali.schoolCollege}" id="schoolCollege3" readonly="readonly"></td>
				<td><input type="text" class="textfield1"  name="qualificationList[3].boardUniversity" value="${quali.boardUniversity}" id="boardUniversity3" readonly="readonly"></td>
				<td><input type="text" class="textfield1"  name="qualificationList[3].marks" value="${quali.marks}" id="marks3" readonly="readonly"></td>
				<td>
					<input type="text" class="textfield1"  name="qualificationList[3].passingYear" value="${quali.passingYear}" id="passingYear3" readonly="readonly">
					<input type="hidden" class="textfield1" name="qualificationList[3].qualificationType" id="qualificationType3" value="POSTGRADUATION" readonly="readonly">
				</td>
			</tr>	
		</c:if>
		</c:forEach>
			
		<c:if test="${i eq 0}">
			<tr>			
			<td><input type="text" class="textfield1"  name="qualificationList[3].examName" id="examName3" readonly="readonly"/></td>
			<td><input type="text" class="textfield1"  name="qualificationList[3].specialization" id="specialization3" readonly="readonly"></td>
			<td><input type="text" class="textfield1"  name="qualificationList[3].schoolCollege" id="schoolCollege3" readonly="readonly"></td>
			<td><input type="text" class="textfield1"  name="qualificationList[3].boardUniversity" id="boardUniversity3" readonly="readonly"></td>
			<td><input type="text" class="textfield1"  name="qualificationList[3].marks" id="marks3" readonly="readonly"></td>
			<td>
				<input type="text" class="textfield1"  name="qualificationList[3].passingYear" id="passingYear3" readonly="readonly">
				<input type="hidden" class="textfield1" name="qualificationList[3].qualificationType" id="qualificationType3" value="POSTGRADUATION">
			</td>
		</tr>			
		</c:if>		
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
			<td><input type="text" class="textfield1"  name="othersExamName" id="othersExamName" readonly="readonly"/></td>
			<td><input type="text" class="textfield1"  name="othersSpecialization" id="othersSpecialization" readonly="readonly"/></td>
			<td><input type="text" class="textfield1"  name="othersSchoolCollege" id="othersSchoolCollege" readonly="readonly"/></td>
			<td><input type="text" class="textfield1"  name="othersBoardUniversity" id="othersBoardUniversity" readonly="readonly"/></td>
			<td><input type="text" class="textfield1"  name="othersMarks" id="othersMarks" readonly="readonly"/></td>
			<td>
				<input type="text" class="textfield1"  name="othersPassingYear" id="othersPassingYear" readonly="readonly"/>
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
	<table id="fileTable" cellspacing="0" cellpadding="0" class="midsec">
		<thead>
			<tr>
				<th colspan="2">Upload Qualification Document</th>
			</tr>
		</thead>
		<tbody>
			<tr>
	 			<td colspan="2">	 				
					<input type="file" name="resource.uploadFile.qualificationRelatedFile"/>
					<input id="addFile" class="addFileClassName addbtn" type="button" />				
	 			</td>      
			</tr>
			<tr>
		<th>Attached Documents :: </th>			
			<td>
				<table class="midsec">
					<c:choose>
						<c:when test="${employeeDetails.attachmentList eq null || employeeDetails.attachmentList.size()==0}">												
							<tr><th>No Attachment Found</th></tr>													
						</c:when>
					<c:otherwise>
						<tr><th>Download Attachments</th></tr>
						<c:if test="${employeeDetails.attachmentList != null}">
							<c:forEach var="attachment" items="${employeeDetails.attachmentList}" >
								<c:if test="${attachment.attachmentType == 'qualification_doc'}">
								<tr>
									<td><a href="downloadEmployeeAttachments.html?attachmentType=<c:out value="${attachment.attachmentType}"></c:out>&attachmentName=<c:out value="${attachment.attachmentName}"></c:out>&userId=<c:out value="${employeeDetails.resource.userId}"></c:out>">${attachment.attachmentName}</a></td>								
								</tr>
								</c:if>								
							</c:forEach>
						</c:if>
					</c:otherwise>
					</c:choose>						
				</table>
			</td>
		</tr>
			
		</tbody>
	</table>
	</form:form>	
</div>

	<h3 style="color:white; margin-left: 1%;cursor: pointer;">Address 
	<img id="i4" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'location',this.id);"/></h3>
	<div id="location">
		<form:form name="updateEmployeeAddressDetails" id="updateEmployeeAddressDetails" action="updateEmployeeAddressDetails.html">
		<table cellspacing="0" cellpadding="0" class="midsec">
			<tr>
				<th colspan="6" style="text-align: center;text-decoration: underline;">Correspondence Address</th>
			</tr>
			<tr>
				<th>Address</th>				
				<td colspan="5"><input type="text" class="textfield1" id="presentAddressLine" name="address.presentAddressLine" value="${employeeDetails.address.presentAddressLine}" readonly="readonly"/></td>
			</tr>			
			<tr>				
				<th>Landmark</th>				
				<td><input type="text" class="textfield1" id="presentAddressLandmark" name="address.presentAddressLandmark" value="${employeeDetails.address.presentAddressLandmark}" readonly="readonly"/></td>
				<th>City/Village</th>				
				<td><input type="text" class="textfield1" id="presentAddressCityVillage" name="address.presentAddressCityVillage" value="${employeeDetails.address.presentAddressCityVillage}" readonly="readonly"/></td>
				<th>Railway Station</th>				
				<td><input type="text" class="textfield1" id="presentAddressRailwayStation" name="address.presentAddressRailwayStation" value="${employeeDetails.address.presentAddressRailwayStation}" readonly="readonly"/></td>
			</tr>			
			<tr>
				<th>Post Office</th>				
				<td><input type="text" class="textfield1" id="presentAddressPostOffice" name="address.presentAddressPostOffice" value="${employeeDetails.address.presentAddressPostOffice}" readonly="readonly"/></td>
				<th>Police Station</th>				
				<td><input type="text" class="textfield1" id="presentAddressPoliceStation" name="address.presentAddressPoliceStation" value="${employeeDetails.address.presentAddressPoliceStation}" readonly="readonly"/></td>
				<th>Pin code</th>				
				<td><input type="text" class="textfield1" id="presentAddressPinCode" name="address.presentAddressPinCode" value="${employeeDetails.address.presentAddressPinCode}" readonly="readonly"/></td>
			</tr>			
			<tr>
				<th>District</th>				
				<td><input type="text" class="textfield1" id="presentAddressDistrict" name="address.presentAddressDistrict" value="${employeeDetails.address.presentAddressDistrict}" readonly="readonly"/></td>
				<th>State</th>				
				<td>
					<select name="address.presentAddressState" id="presentAddressState" class="defaultselect" disabled="disabled">
						<c:forEach var="state" items="${stateList}" >
							<option value="${state.stateCode}" ${employeeDetails.address.presentAddressState eq state.stateName ? 'selected=selected' : ''}>${state.stateName}</option>
						</c:forEach>
					</select>  
				</td>
				<th>Country</th>				
				<td>
					<select id="presentAddressCountry" name="address.presentAddressCountry" class="defaultselect" disabled="disabled">
						<c:forEach var="country" items="${countryList}" >
							<option value="${country.countryCode}" ${employeeDetails.address.presentAddressCountry eq country.countryCode ? 'selected=selected' : ''}>${country.countryName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>Phone</th>				
				<td><input type="text" class="textfield1" id="presentAddressPhone" name="address.presentAddressPhone" value="${employeeDetails.address.presentAddressPhone}" readonly="readonly"/></td>
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
				<td colspan="5"><input type="text" class="textfield1" id="permanentAddressLine" name="address.permanentAddressLine" value="${employeeDetails.address.permanentAddressLine}" readonly="readonly"/></td>
			</tr>			
			<tr>				
				<th>Landmark</th>				
				<td><input type="text" class="textfield1" id="permanentAddressLandmark" name="address.permanentAddressLandmark" value="${employeeDetails.address.permanentAddressLandmark}" readonly="readonly"/></td>
				<th>City/Village</th>				
				<td><input type="text" class="textfield1" id="permanentAddressCityVillage" name="address.permanentAddressCityVillage" value="${employeeDetails.address.permanentAddressCityVillage}" readonly="readonly"/></td>
				<th>Railway Station</th>				
				<td><input type="text" class="textfield1" id="permanentAddressRailwayStation" name="address.permanentAddressRailwayStation" value="${employeeDetails.address.permanentAddressRailwayStation}" readonly="readonly"/></td>
			</tr>			
			<tr>
				<th>Post Office</th>				
				<td><input type="text" class="textfield1" id="permanentAddressPostOffice" name="address.permanentAddressPostOffice" value="${employeeDetails.address.permanentAddressPostOffice}" readonly="readonly"/></td>
				<th>Police Station</th>				
				<td><input type="text" class="textfield1" id="permanentAddressPoliceStation" name="address.permanentAddressPoliceStation" value="${employeeDetails.address.permanentAddressPoliceStation}" readonly="readonly"/></td>
				<th>Pin code</th>				
				<td><input type="text" class="textfield1" id="permanentAddressPinCode" name="address.permanentAddressPinCode" value="${employeeDetails.address.permanentAddressPinCode}" readonly="readonly"/></td>
			</tr>			
			<tr>
				<th>District</th>				
				<td><input type="text" class="textfield1" id="permanentAddressDistrict" name="address.permanentAddressDistrict" value="${employeeDetails.address.permanentAddressDistrict}" readonly="readonly"/></td>
				<th>State</th>				
				<td>
					<select name="address.permanentAddressState" id="permanentAddressState" class="defaultselect" disabled="disabled">
						<c:forEach var="state" items="${stateList}" >
							<option value="${state.stateCode}" ${employeeDetails.address.permanentAddressState eq state.stateName ? 'selected=selected' : ''}>${state.stateName}</option>
						</c:forEach>
					</select>  
				</td>
				<th>Country</th>				
				<td>
					<select id="permanentAddressCountry" name="address.permanentAddressCountry" class="defaultselect" disabled="disabled">
						<c:forEach var="country" items="${countryList}" >
							<option value="${country.countryCode}" ${employeeDetails.address.permanentAddressCountry eq country.countryCode ? 'selected=selected' : ''}>${country.countryName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>Phone</th>				
				<td><input type="text" class="textfield1" id="permanentAddressPhone" name="address.permanentAddressPhone" value="${employeeDetails.address.permanentAddressPhone}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td colspan="6"><input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
				<input type="button" class="editbtn" id="addressDetailsTableEdit" name="edit" value="Edit" onclick="makeAddressDetailsFieldEditable();" />
				<input type="submit" id="addressDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="submitbtn" onclick="return validateEmployeeAddress();"/>
				<input type="reset" id="addressDetailsTableCancel" class="clearbtn" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeAddressDetailsFieldInEditable();"/></td>
			</tr>			
		</table>		
		</form:form>
	</div>

<h3 style="color:white; margin-left: 1%;cursor: pointer;">Employee's Dependents
<img id="i5" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'employeesDependents',this.id);"/></h3>
<div id="employeesDependents">
	<div id="cloneDiv">
	<form:form name="updateEmployeeDependentsDetails" id="updateEmployeeDependentsDetails" action="updateEmployeeDependentsDetails.html">
		<table class="employeesDependentsTableClass midsec" id="employeesDependentsTable"  cellspacing="0" cellpadding="0" >
			<tr>
				<th style="text-align: center;text-decoration: underline;">
					Employee's Childern
				</th>
			</tr>
			<tr>
				<th>Name</th><th>Gender</th><th>DOB</th>	
			</tr>
			<c:forEach var="ed" items="${employeeDetails.employeeDependentList}">			
				<tr>			
					<td><input type="text" class="textfield2" id="childName" name="childName" value="${ed.childName}" readonly="readonly"/></td>						
					<td>
						<select name="childGender" class="defaultselect" id="childGender" disabled="disabled">
							<option value="M" ${ed.childGender eq 'M' ? 'selected=selected' : ''} >Male</option>
							<option value="F" ${ed.childGender eq 'F' ? 'selected=selected' : ''}>Female</option>
						</select>
					</td>
					<td><input type="text" class="childDateOfBirthClass"  name="childDateOfBirth" value="${ed.childDOB}" disabled="disabled""/></td>
					<td>
					<input type="image" src="/icam/images/minus_icon.png" onclick="deleteEmployeeDependents(this);">
					</td>	
				</tr>
			</c:forEach>
			<c:if test="${employeeDetails.employeeDependentList == null}">
			<tr>			
				<td><input type="text" class="textfield2" id="childName" name="childName" readonly="readonly"/></td>
				<td>			
				<select name="childGender" class="defaultselect" id="childGender" disabled="disabled">
						<option value="M">MALE</option>
						<option value="F">FEMALE</option>
					</select>
				</td>
				<td><input type="text" class="childDateOfBirthClass" id="childDateOfBirth" name="childDateOfBirth" readonly="readonly"/></td>
			</tr>
			</c:if>
			<tr>
				<td colspan="3">
				<input type="button" class="addbtn" style="margin-top: 10px;" onclick="addChildRows();">
				<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
				<input type="button" class="editbtn" id="employeeDependentsDetailsTableEdit" name="edit" value="Edit" onclick="makeEmployeeDependentsDetailsFieldEditable();" />
				<input type="submit" id="employeeDependentsDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="submitbtn" onclick="return validateEmployeeDependentsDetails();"/>
				<input type="reset" id="employeeDependentsDetailsTableCancel" class="clearbtn" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeeDependentsDetailsFieldInEditable();"/></td>
			</tr>		
		</table>
	</form:form>	
	</div>		
</div>

<h3 style="color:white; margin-left: 1%;cursor: pointer;">Nominee
<img id="i6" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'nominee',this.id);"/></h3>
<div id="nominee">
	<div id="cloneDiv">
	<form:form name="updateEmployeeNomineeDetails" id="updateEmployeeNomineeDetails" action="updateEmployeeNomineeDetails.html">	
		<table class="midsec" id="nomineeTable"  cellspacing="0" cellpadding="0" >
			<tr>
				<th style="text-align: center;text-decoration: underline;">
					Nominee Details
				</th>
			</tr>
			<tr>
				<th>Nominee Name</th><th>Relationship</th><th>Nominee( % )</th>
			</tr>
			<c:forEach var="nd" items="${employeeDetails.nomineeDetailsList}">	
			<tr>
				<td>
					<input type="text" class="textfield2" id="nomineeName" name="nomineeName" value="${nd.nomineeName}" readonly="readonly"/>
				</td>
				<td>			
					<input type="text" class="textfield2" id="relationship" name="relationship" value="${nd.relationship}" readonly="readonly"/>
				</td>
				<td>
					<input type="text" class="textfield2" id="nomineePercent" name="nomineePercent" value="${nd.percentage}" readonly="readonly" onblur="calculateHundred();"/>
				</td>			 
				<td>
					<input type="image" src="/icam/images/minus_icon.png" onclick="deleteNomineeRow(this);">
				</td>		
			</tr>
			</c:forEach>
			<c:if test="${employeeDetails.nomineeDetailsList == null}">
			<tr>										
				<td>
					<input type="text" class="textfield2" id="nomineeName" name="nomineeName" readonly="readonly"/>
				</td>
				<td>			
					<input type="text" class="textfield2" id="relationship" name="relationship" readonly="readonly"/>
				</td>
				<td>
					<input type="text" class="textfield2" id="nomineePercent" name="nomineePercent" readonly="readonly" onblur="calculateHundred();"/>
				</td>
			</tr>
			</c:if>			
			<tr>
				<td colspan="3">
				<input type="button" class="addbtn" style="margin-top: 10px;" onclick="addNominee();">
				<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
				<input type="button" class="editbtn" id="employeeNomineeDetailsTableEdit" name="edit" value="Edit" onclick="makeEmployeeNomineeDetailsFieldEditable();" />
				<input type="submit" id="employeeNomineeDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="submitbtn" onclick="return validateEmployeeNomineeDetails();"/>
				<input type="reset" id="employeeNomineeDetailsTableCancel" class="clearbtn" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeeNomineeDetailsFieldInEditable();"/></td>
			</tr>		
		</table>
	</form:form>		
	</div>		
</div>


<h3 style="color:white; margin-left: 1%;cursor: pointer;">Employee's Bank Details
<img id="i7" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'employeesBankDetails',this.id);"/></h3>
<div id="employeesBankDetails">
	<div id="cloneDiv">
	<form:form name="updateEmployeeBankDetails" id="updateEmployeeBankDetails" action="updateEmployeeBankDetails.html">	
			<table class="employeesBankDetailsTableClass midsec" id="employeesBankDetailsTable"  cellspacing="0" cellpadding="0" >
				<tr>
					<th colspan="6" style="text-align: center;text-decoration: underline;">
						Employee's Bank Details
					</th>
				</tr>
				<tr>
					<th>Bank Name</th>			
					<td><input type="text" class="textfield2" id="bankName" name="bankName" value="${employeeDetails.resource.bankName}" readonly="readonly"/></td>
					<th>Branch Code</th>			
					<td><input type="text" class="textfield2" id="branchCode" name="branchCode" value="${employeeDetails.resource.bankName}" readonly="readonly"/></td>				
				</tr>		
				<tr>
					<th>Account Type</th>			
					<td><input type="text" class="textfield2" id="accountType" name="accountType" value="${employeeDetails.resource.accountType}" readonly="readonly"/></td>
					<th>Branch IFSC Code</th>			
					<td><input type="text" class="textfield2" id="branchIFSCCode" name="branchIFSCCode" value="${employeeDetails.branchIFSCCode}" readonly="readonly"/></td>
				</tr>
				<tr>
					<th>Account Holder Name</th>			
					<td><input type="text" class="textfield2" id="accountHolderName" name="accountHolderName" value="${employeeDetails.resource.accountHolderName}" readonly="readonly"/></td>
					<th>Account Number</th>			
					<td><input type="text" class="textfield2" id="accountNumber" name="accountNumber" value="${employeeDetails.resource.accountNumber}" readonly="readonly"/></td>				
				</tr>	
				<tr>
				<td colspan="4"><input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
				<input type="button" class="editbtn" id="employeeBankDetailsTableEdit" name="edit" value="Edit" onclick="makeEmployeeBankDetailsFieldEditable();" />
				<input type="submit" id="employeeBankDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="submitbtn" onclick="return validateEmployeeBankDetails();"/>
				<input type="reset" id="employeeBankDetailsTableCancel" class="clearbtn" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeeBankDetailsFieldInEditable();"/></td>
			</tr>	
			</table>
	</form:form>	
	</div>		
</div>


<h3 style="color:white; margin-left: 1%;cursor: pointer;">Staff Previous Working Details
<img id="i8" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'staffPreviousWorkDetails',this.id);"/></h3>
<div id="staffPreviousWorkDetails" >
	<form:form name="updateEmployeeWorkingDetails" id="updateEmployeeWorkingDetails" enctype="multipart/form-data" action="updateEmployeeWorkingDetails.html">
	<div id="clonedstaffPreviousWorkDetailsDiv">	
	<table class="staffPreviousWorkDetailsTableClass midsec" id="staffPreviousWorkDetailsTable"  cellspacing="0" cellpadding="0" >
		<tr>
			<th colspan="6" style="text-align: center;text-decoration: underline;">
				Previous Organization Details
			</th>
		</tr>
		<c:choose>
			<c:when test="${employeeDetails.organizationList!=null && employeeDetails.organizationList.size()!=0}">
				<c:forEach var="org" items="${employeeDetails.organizationList}">
					<tr>
						<th>Previous Organization Name</th>			
						<td><input type="text" class="textfield2" id="organizationName" name="organizationName" value="${org.organizationName}" readonly="readonly"/></td>
						<th>From Date</th>			
						<td><input type="text" class="textfield2" id="" name="fromDate" value="${org.fromDate}"  placeholder="MM/YYYY" readonly="readonly"/></td>	
						<th>To Date</th>			
						<td><input type="text" class="textfield2" id="" name="toDate" value="${org.toDate}" placeholder="MM/YYYY" readonly="readonly"/></td>
					</tr>
					<tr>
						<th>Contact</th>			
						<td><input type="text" class="textfield2" id="organizationContactNo" name="organizationContactNo" value="${org.organizationContactNo}" readonly="readonly"/></td>
						<th>Website </th>			
						<td><input type="text"  class="textfield2" id="organizationWebSite" name="organizationWebSite" value="${org.organizationWebSite}" readonly="readonly"/></td>
						
					</tr>
				</c:forEach>		
			</c:when>			
		<c:otherwise>
			<tr>
				<th>Previous Organization Name</th>			
				<td><input type="text" class="textfield2" id="organizationName" name="organizationName" readonly="readonly"/></td>
				<th>From Date</th>			
				<td><input type="text" class="textfield2" id="" name="fromDate"  placeholder="MM/YYYY" readonly="readonly"/></td>	
				<th>To Date</th>			
				<td><input type="text" class="textfield2" id="" name="toDate"  placeholder="MM/YYYY" readonly="readonly"/></td>
			</tr>
			<tr>
				<th>Contact</th>			
				<td><input type="text" class="textfield2" id="organizationContactNo" name="organizationContactNo" readonly="readonly"/></td>
				<th>Website </th>			
				<td><input type="text"  class="textfield2" id="organizationWebSite" name="organizationWebSite" readonly="readonly"/></td>
			</tr>
		</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="6">
			<input id="staffPreviousWorkDetailsButton" style="margin-top: 10px;" class="addbtn" type="button" onclick="addMoreExperience();"/>
			<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
			<input type="button" class="editbtn" id="employeePreviousWorkDetailsTableEdit" name="edit" value="Edit" onclick="makeEmployeePreviousWorkDetailsFieldEditable();" />
			<input type="submit" id="employeePreviousWorkDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="submitbtn" onclick="return validateEmployeePreviousWorkDetailsDetails();"/>
			<input type="reset" id="employeePreviousWorkDetailsTableCancel" class="clearbtn" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeePreviousWorkDetailsFieldInEditable();"/></td>
		</tr>	
	</table>
	</div>
	
			
	<table id="fileTable2" cellspacing="0" cellpadding="0" class="midsec">
		<thead>
			<tr>
				<th colspan="2">Upload Previous Organization Document</th>
			</tr>
		</thead>
		<tbody>
			<tr>  				
 				<td colspan="2">					
					<input type="file" name="resource.uploadFile.experienceRelatedFile"/>
					<input id="addFile2" class="addFileClassName addbtn" type="button" />				
 				</td>      
			</tr>
			<tr>
		<th>Attached Documents :: </th>
			
			<td>
				<table class="midsec">
					<c:choose>
						<c:when test="${employeeDetails.attachmentList eq null || employeeDetails.attachmentList.size()==0}">												
							<tr><th>No Attachment Found</th></tr>													
						</c:when>
					<c:otherwise>
						<tr><th>Download Attachments</th></tr>
						<c:if test="${employeeDetails.attachmentList != null}">
							<c:forEach var="attachment" items="${employeeDetails.attachmentList}" >
								<c:if test="${attachment.attachmentType == 'previous_organization_doc'}">
								<tr>									
									<td><a href="downloadEmployeeAttachments.html?attachmentType=<c:out value="${attachment.attachmentType}"></c:out>&attachmentName=<c:out value="${attachment.attachmentName}"></c:out>&userId=<c:out value="${employeeDetails.resource.userId}"></c:out>">${attachment.attachmentName}</a></td>
								</tr>
								</c:if>								
							</c:forEach>
						</c:if>
					</c:otherwise>
					</c:choose>						
				</table>
			</td>
		</tr>
		</tbody>
	</table>
	</form:form>	
</div>


<h3 style="color:white; margin-left: 1%;cursor: pointer;">Work Shop & Training
<img id="i9" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'workShopAndTrainingDetails',this.id);"/></h3>
<div id="workShopAndTrainingDetails" >
	<div id="clonedWorkShopAndTrainingDetailsDiv">
	<form:form name="updateEmployeeWorkShopAndTrainingDetails" id="updateEmployeeWorkShopAndTrainingDetails" action="updateEmployeeWorkShopAndTrainingDetails.html">
	<table class="workShopAndTrainingDetailsTableClass midsec" id="workShopAndTrainingDetailsTable"  cellspacing="0" cellpadding="0" >
		<tr>
			<th colspan="4" style="text-align: center;text-decoration: underline;">
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
		<c:forEach var="wst" items="${employeeDetails.workShopAndTrainingList}" varStatus="j">	
		<tr>			
			<td><input type="text" class="textfield1" id="subject${j.index}" name="subject" value="${wst.subject}" readonly="readonly"/></td>
			<td><input type="text" class="textfield1" id="venue${j.index}" name="venue" value="${wst.venue}" readonly="readonly"/></td>
			<td><input type="text" class="trainingDate" id="trainingFromDate${j.index}" name="trainingFromDate" value="${wst.trainingFromDate}" readonly="readonly" onblur="calculateDateDifference(${j.index});"/></td>
			<td><input type="text" class="trainingDate" id="trainingToDate${j.index}" name="trainingToDate"  value="${wst.trainingToDate}" readonly="readonly" onblur="calculateDateDifference(${j.index});"/></td>						
			<td><input type="text" class="textfield1" id="organizedBy${j.index}" name="organizedBy" value="${wst.organizedBy}" readonly="readonly"/></td>						
			<td><input type="text" class="textfield1" id="duration${j.index}" name="duration" value="${wst.duration}" readonly="readonly"/></td>	
			<td><input type="image" src="/icam/images/minus_icon.png" onclick="deleteWorkShopRow(this);"></td>		
		</tr>	
		</c:forEach>
		<c:if test="${employeeDetails.workShopAndTrainingList == null}">
			<tr>
				<td><input type="text" class="textfield1" id="subject" name="subject" readonly="readonly"/></td>
				<td><input type="text" class="textfield1" id="venue" name="venue" readonly="readonly"/></td>
				<td><input type="text" class="trainingDate" id="trainingFromDate0" name="trainingFromDate" onblur="calculateDateDifference(0);" /></td>
				<td><input type="text" class="trainingDate" id="trainingToDate0" name="trainingToDate" onblur="calculateDateDifference(0);" /></td>						
				<td><input type="text" class="textfield1" id="organizedBy" name="organizedBy" readonly="readonly"/></td>						
				<td><input type="text" class="textfield1" id="duration" name="duration" readonly="readonly"/></td>			
			</tr>		
		</c:if>		
		<tr>
				<td colspan="4">
				<input id="addMoreWorkShopButton" type="button" style="margin-top: 10px;" class="addbtn" onclick="addMoreWorkShop();"/>	
				<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
				<input type="button" class="editbtn" id="employeeWorkShopAndTrainingDetailsTableEdit" name="edit" value="Edit" onclick="makeEmployeeWorkShopAndTrainingDetailsFieldEditable();" />
				<input type="submit" id="employeeWorkShopAndTrainingDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="submitbtn" onclick="return validateEmployeeWorkShopAndTrainingDetails();"/>
				<input type="reset" id="employeeWorkShopAndTrainingDetailsTableCancel" class="clearbtn" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeeWorkShopAndTrainingDetailsFieldInEditable();"/></td>
			</tr>		
		</table>
	</form:form>	
	</div>	
</div>


<h3 style="color:white; margin-left: 1%;cursor: pointer;">Awards & Recognization
<img id="i10" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'awardsAndRecognization',this.id);"/></h3>
<div id="awardsAndRecognization" >
	<div id="awardsAndRecognizationsDiv">
	<form:form name="updateEmployeeAwardsAndRecognizationDetails" id="updateEmployeeAwardsAndRecognizationDetails" action="updateEmployeeAwardsAndRecognizationDetails.html">
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
		<c:forEach var="aar" items="${employeeDetails.awardsAndRecognizationList}">			
		<tr>
			<td><input type="text" class="textfield2" id="awardName" name="awardName" value="${aar.awardName}" readonly="readonly"/></td>
			<td><input type="text" class="textfield2" id="presentedBy" name="presentedBy" value="${aar.presentedBy}" readonly="readonly"/></td>						
			<td><input type="text" class="presentedOnClass" id="presentedOn" name="presentedOn" value="${aar.presentedOn}" readonly="readonly"/ ></td>	
			<td><input type="image" src="/icam/images/minus_icon.png" onclick="deleteAwardRecognizationRow(this);"></td>					
		</tr>		
		</c:forEach>		
		<c:if test="${employeeDetails.awardsAndRecognizationList == null}">
			<tr>
				<td><input type="text" class="textfield2" id="awardName" name="awardName" readonly="readonly"/></td>
				<td><input type="text" class="textfield2" id="presentedBy" name="presentedBy" readonly="readonly"/></td>						
				<td><input type="text" class="presentedOnClass" id="presentedOn" name="presentedOn" readonly="readonly"/></td>						
			</tr>	
		</c:if>		
		<tr>
			<td colspan="3">
			<input id="addMoreWorkShopButton" type="button" style="margin-top: 10px;" class="addbtn" onclick="addMoreAwardsAndRecognization();"/>	
			<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
			<input type="button" class="editbtn" id="employeeAwardsAndRecognizationDetailsTableEdit" name="edit" value="Edit" onclick="makeEmployeeAwardsAndRecognizationDetailsFieldEditable();" />
			<input type="submit" id="employeeAwardsAndRecognizationDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="submitbtn" onclick="return validateEmployeeAwardsAndRecognizationDetails();"/>
			<input type="reset" id="employeeAwardsAndRecognizationDetailsTableCancel" class="clearbtn" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeeAwardsAndRecognizationDetailsFieldInEditable();"/></td>
		</tr>		
		</table>
	</form:form>		
	</div>	
</div>


<h3 style="color:white; margin-left: 1%;cursor: pointer;">Publication Details 
<img id="i11" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'publicationsDetailsDiv',this.id);"/></h3>
<div id="publicationsDetailsDiv" >
	<form:form name="updateEmployeePublicationDetails" id="updateEmployeePublicationDetails" enctype="multipart/form-data" action="updateEmployeePublicationDetails.html">
	<div id="clonePublicationsDetails" >		
			<table id="publicationsDetails" cellspacing="0" cellpadding="0" class="midsec">		
				<tr>
					<th colspan="4" style="text-align: center;text-decoration: underline;">
						Publications Details
					</th>
				</tr>
				
				
				 <c:choose>
					<c:when test="${employeeDetails.publicationList!=null && employeeDetails.publicationList.size()!=0}">
						<c:forEach var="pub" items="${employeeDetails.publicationList}">
							<tr>
								<th>Title/Publication Name</th>			
								<td><input id="publicationName" name="publicationName" class="textfield2" value="${pub.publicationName}" readonly="readonly"/></td>
								<th>Date of Publication</th>			
								<td><input class="textfield2" id="dateOfPublication" name="dateOfPublication" placeholder="MM/YYYY" value="${pub.dateOfPublication}" readonly="readonly"/></td>
							</tr>
							<tr>
								<th>Co Publisher</th>			
								<td><input type="text" id="coPublisher" name="coPublisher" class="textfield2" value="${pub.coPublisher}" readonly="readonly"/></td>
								<th>Publication Description</th>	
							 	<td>
									<textarea id="publicationDesc" name="publicationDesc" class="txtarea"  readonly="readonly">${pub.publicationDesc}</textarea>				
								</td> 
							</tr>	
						</c:forEach>
					</c:when>
				 	<c:otherwise>
						<tr>
							<th>Title/Publication Name</th>			
							<td><input id="publicationName" name="publicationName" class="textfield2" readonly="readonly"/></td>
							<th>Date of Publication</th>			
							<td><input class="textfield2" id="dateOfPublication" name="dateOfPublication" placeholder="MM/YYYY" readonly="readonly"/></td>
						</tr>
						<tr>
							<th>Co Publisher</th>			
							<td><input type="text" id="coPublisher" name="coPublisher" class="textfield2" readonly="readonly"/></td>
							<th>Publication Description</th>	
							<td>
								<textarea id="publicationDesc" name="publicationDesc" class="txtarea" readonly="readonly"></textarea>				
							</td>
						</tr>
					</c:otherwise> 
				</c:choose>	
				<tr>
					<td colspan="4">
						<input type="button" id="addPublicationsDetailsButton" onclick="new_publish();" class="addbtn" />
					</td>
				</tr>					
				<tr>
					<td colspan="4"><input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
					<input type="button" class="editbtn" id="employeePublicationDetailsTableEdit" name="edit" value="Edit" onclick="makeEmployeePublicationDetailsFieldEditable();" />		
					<input type="submit" id="employeePublicationDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="submitbtn" onclick="return validateEmployeePublicationDetails();"/>
					<input type="reset" id="employeePublicationDetailsTableCancel" class="clearbtn" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeePublicationDetailsFieldInEditable();"/></td>
				</tr>				
		</table>		
	</div>
	
	<table cellspacing="0" cellpadding="0" class="midsec" >
		<tr>	
			<th style="text-align: center;text-decoration: underline;" colspan="2">
					Upload Publication Document
			</th>
		<tr>	
		<tr>			
			<td colspan="2">				
				<input type="file" name="resource.uploadFile.publicationRelatedFile"/>
				<input id="addFile3" class="addFileClassName addbtn" type="button" />
			</td>
		</tr>
		<tr>
		<th>Attached Documents :: </th>
			
			<td>
				<table class="midsec">
					<c:choose>
						<c:when test="${employeeDetails.attachmentList eq null || employeeDetails.attachmentList.size()==0}">												
							<tr><th>No Attachment Found</th></tr>													
						</c:when>
					<c:otherwise>
						<tr><th>Download Attachments</th></tr>
						<c:if test="${employeeDetails.attachmentList != null}">
							<c:forEach var="attachment" items="${employeeDetails.attachmentList}" >
								<c:if test="${attachment.attachmentType == 'publication_doc'}">
								<tr>								
									<td><a href="downloadEmployeeAttachments.html?attachmentType=<c:out value="${attachment.attachmentType}"></c:out>&attachmentName=<c:out value="${attachment.attachmentName}"></c:out>&userId=<c:out value="${employeeDetails.resource.userId}"></c:out>">${attachment.attachmentName}</a></td>
								</tr>
								</c:if>								
							</c:forEach>
						</c:if>
					</c:otherwise>
					</c:choose>						
				</table>
			</td>
		</tr>
	</table>	
	</form:form>
</div>	


<h3 style="color:white; margin-left: 1%;cursor: pointer;">Confidential
<img id="i12" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'confidential',this.id);"/></h3>
<div id="confidential" >
	<div id="confidentialDiv">
	<form:form name="updateEmployeeConfidentialDetails" id="updateEmployeeConfidentialDetails" action="updateEmployeeConfidentialDetails.html">
	<table class="confidentialClass midsec" id="confidentialTable"  cellspacing="0" cellpadding="0" >
		<tr>
			<th colspan="3" style="text-align: center;text-decoration: underline;">
				Confidential
			</th>
		</tr>
		<tr>
			<th>Note</th>			
			<td>
				<textarea id="confidentialInformation" name="confidentialInformation" class="txtarea"  rows="8" cols="100" disabled="disabled">${employeeDetails.confidentialInformation}</textarea>		
			</td>						
		</tr>		
		<tr>
			<td colspan="3">			
			<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
			<input type="button" class="editbtn" id="employeeConfidentialDetailsTableEdit" name="edit" value="Edit" onclick="makeEmployeeConfidentialFieldEditable();" />
			<input type="submit" id="employeeConfidentialDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="submitbtn" onclick="return validateEmployeeConfidentialDetails();"/>
			<input type="reset" id="employeeConfidentialDetailsTableCancel" class="clearbtn" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeeConfidentialDetailsFieldInEditable();"/>
			</td>
		</tr>		
	</table>
	</form:form>		
	</div>	
</div>
			
				
	
	
<h3 style="color:white; margin-left: 1%;cursor: pointer;">Upload Image
<img id="i13" class="plus" src="/icam/images/plus_icon.png" value="Collapse" onclick="check(this.value,'uploadImage',this.id);"/></h3>
<div id="uploadImage" name="uploadImage">
	<form:form name="updateEmployeeImage" id="updateEmployeeImage" enctype="multipart/form-data" 

action="updateEmployeeImage.html">
		<table cellspacing="0" cellpadding="0" class="midsec1">			
			<tr>
				<th colspan="4" style="text-align: center;text-decoration: underline;">Staff's Image</th>
			</tr>	
			<tr>
				<td colspan ="4">  			
					<div id="uploadImage" name="uploadImage" >	
					<h3>Please select image file<img class="required" src="/icam/images/required.gif" alt="Required"></h3>
					 <img id="preview" src="data:image/jpg;base64, ${employeeDetails.resource.image.imageName}" style="width:200px;height:200px;"/>
					 	<div class="container">
				            <div class="upload_form_cont">	
					            <div>
					                 <div>
					                 	<input type="file" name="resource.image.imageData" id="image_upload" onchange="validateFile();" disabled="disabled"/>					                 	
					                 </div> 
					            </div>
					        </div>
					    </div>
					</div>		
				</td>
			</tr>
			<tr>
				<td><input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
				<input type="button" class="editbtn" id="employeeImageTableEdit" name="edit" value="Edit" onclick="makeEmployeeImageEditable();" />		
				<input type="submit" id="employeeImageTableSubmit" name="submit" value="Save" style="visibility:collapse" class="submitbtn"/>
				<input type="reset" id="employeeImageTableCancel" class="clearbtn" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeePublicationDetailsFieldInEditable();"/></td>
			</tr>			
		</table>
	</form:form>
</div>

<div class="btnsarea01">
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
	<div class="errorbox" id="fileError" >
		<span id="error"></span>	
	</div>
		<c:if test="${updateResponse ne null}">				
		<c:if test="${updateResponse eq 'Success'}">
			<div class="successbox" id="successbox" style="visibility:visible;">
				<span id="successmsg" style="visibility:visible;">${updateMessage}</span>	
			</div>
		</c:if>
		<c:if test="${updateResponse eq 'Fail'}">
			<div class="errorbox" id="errorbox" style="visibility:visible;">
				<span id="errormsg" style="visibility:visible;">${updateMessage}</span>	
			</div>
		</c:if>		
	</c:if>
</div>
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