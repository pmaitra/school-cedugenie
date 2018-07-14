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
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Create Student</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
       #dropzone-example{
           display: none !important;
       }
</style>
<!-- <link href="/cedugenie/assets/custom-caleder/jquery-ui.css" type="text/css" rel="stylesheet">  -->
</head>
<body>

		<header class="page-header">
			<h2>Create Student</h2>
		</header>
		<div class="content-padding">
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
			<c:if test="${repository.repositoryPathName eq null }">
                <div class="alert alert-danger"  >
					<strong>Please Configure Repository Structure</strong>	
				</div>
            </c:if>
			<div class="row">
				<div class="col-xs-12">
				    <section class="panel form-wizard" id="w4">
				    	<form:form name="addStudent" id="addStudent" enctype="multipart/form-data" action="addStudent.html" method="POST" >
				            <header class="panel-heading">
				                <div class="panel-actions">
				                    <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
				                </div>
				               <!--   <h2 class="panel-title alert alert-danger">Please Create Repository Before Uploading Documents And Photo</h2> -->
				                
				                <h2 class="panel-title">Student Details</h2>
				            </header>
				            <div class="panel-body">
				                <div class="wizard-progress wizard-progress-lg">
				                    <div class="steps-progress">
				                        <div class="progress-indicator"></div>
				                    </div>
				                    <ul class="wizard-steps">
				                        <li class="active">
				                            <a href="#w4-Personal" data-toggle="tab"><span>1</span>Personal <br>Details</a>
				                        </li>
				                        <li>
				                            <a href="#w4-Educational" data-toggle="tab"><span>2</span>Previous <br>Educational Details</a>
				                        </li>
				                        <li>
				                            <a href="#w4-Upload" data-toggle="tab"><span>3</span>Upload <br>Image</a>
				                        </li>
				                    </ul>
				                </div>
				                    <div class="tab-content">
				                        <div id="w4-Personal" class="tab-pane active">
				                            <div class="row">
				                                <div class="col-md-12">
				                                    <blockquote class="b-thin rounded primary">
				                                    	<h3>Academic Year:<br>${currentAcademicYear.academicYearName}</h3>
				                                	</blockquote>
				                                </div>
				                                <div class="col-md-3">
				                                    <div class="form-group">
				                                        <label class="control-label">Standard<span class="required" aria-required="true">*</span></label>
				                                        <select class="form-control" id="courseId" name="courseId" required>
				                                            <option value="NULL">Please select</option>
				                                            <c:forEach var="course" items="${courseList}" >
																<option value="${course.courseCode}">${course.courseName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                </div>
				                                <!--  <div class="col-md-3">
				                              		<div class="form-group">
			                                            <label class="control-label">Admission Drive<span class="required" aria-required="true">*</span></label>
			                                            <select class="form-control" id="driveId" name="driveId" required>
			                                            	<option value="">Select</option>
			                                            </select>
		                                         	</div>
		                                        </div>  -->
				                               <%--  <div class="col-md-3">
				                                    <div class="form-group">
				                                        <label class="control-label">Form Id</label>
				                                       	<select class="form-control" id="strFormId" name="strFormId">
				                                            <option value="">Please select</option>
				                                            <c:forEach var="candidate" items="${formId}" >
																<option value="${candidate.admissionFormId}">${candidate.admissionFormId}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                </div> --%>
				                                <!-- <div class="col-md-3">
				                                    <div class="form-group">
				                                        <label class="control-label">Registration Id</label>
				                                        <input type="text" class="form-control" name="registrationId" id="registrationId"   placeholder="" required>
				                                    </div>
				                                </div> -->
				                                <div class="col-md-3">
				                                    <div class="form-group">
				                                        <label class="control-label">School Number<span class="required" aria-required="true">*</span></label>
				                                        <input type="text" class="form-control" name="resourceUserId" id="userId" placeholder="" required>
				                                    </div>
				                                </div>
				                            </div>
				                            <hr>
				                            <div class="row">
				                                <div class="col-md-12">
				                                <blockquote class="b-thin rounded primary">
				                                    <h3>Student Details</h3>
				                                </blockquote>
				                                </div>
				                                <div class="col-md-3">                                                            
				                                    <div class="form-group">
				                                        <label class="control-label">First Name<span class="required" aria-required="true">*</span></label>
				                                        <input type="text" class="form-control" name="resource.firstName" id="firstName" placeholder="First Name" required>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Middle Name</label>
				                                        <input type="text" class="form-control" name="resource.middleName" id="middleName" placeholder="Middle Name" >
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Last Name</label>
				                                        <input type="text" class="form-control" name="resource.lastName" id="lastName" placeholder="Last Name">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Date Of Birth<span class="required" aria-required="true">*</span></label>
				                                        <div class="input-group">
				                                            <span class="input-group-addon">
				                                                <i class="fa fa-calendar"></i>
				                                            </span>
				                                             <input class="form-control" name="resource.dateOfBirth" id="dateOfBirth" data-plugin-datepicker="" data-date-end-date="0d" required>
				                                        </div>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Admission Date<span class="required" aria-required="true">*</span></label>
				                                        <div class="input-group">
				                                            <span class="input-group-addon">
				                                                <i class="fa fa-calendar"></i>
				                                            </span>
				                                            <input type = "text" class="form-control" name="dateOfAdmission" id="dateOfAdmission" data-plugin-datepicker="" data-date-end-date="0d" required>
				                                        </div>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Gender</label>
				                                        <div class="form-group" style="margin-top: 5px;">
				                                        <label class="radio-inline radio-primary">
				                                            <input type="radio" id="genderMale" name="resource.gender"  value="male" checked="checked"> Male 
				                                        </label>
				                                        <label class="radio-inline radio-primary">
				                                            <input type="radio" id="genderFemale" name="resource.gender"  value="female"> Female 
				                                        </label>
				                                        </div>
				                                    </div>
				                                </div>
				                                <div class="col-md-3">                                    
				                                    <div class="form-group">
				                                        <label class="control-label">Blood Group</label>
				                                        <select class="form-control" id="bloodGroup" name="resource.bloodGroup">
				                                            <option value="">Please select</option>
				                                            <option value="A+">A+</option>
															<option value="A-">A-</option>
															<option value="B+">B+</option>
															<option value="B-">B-</option>
															<option value="AB+">AB+</option>
															<option value="AB-">AB-</option>
															<option value="O+">O+</option>
															<option value="O-">O-</option>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Category</label>
				                                        <select class="form-control" id="category" name="resource.category">
				                                            <option value="">Please select</option>						
				                                            <c:forEach var="socialCategory" items="${socialCategoryList}" >
																<option value="${socialCategory.socialCategoryCode}">${socialCategory.socialCategoryName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Religion</label>
				                                        <input type="text" class="form-control" name="resource.religion" id="religion">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Mothers Tongue</label>
				                                        <input type="text" class="form-control" name="resource.motherTongue" id="motherTongue">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Aadhaar No</label>
				                                        <input type="text" class="form-control" name="resource.aadharCardNo" id="aadharCardNo">
				                                    </div>
				                                </div>
				                                <div class="col-md-3">
				                                    <div class="form-group">
				                                        <label class="control-label">Nationality</label>
				                                        <input type="text" class="form-control" name="resource.nationality" id="nationality">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Child ID</label>
				                                        <input type="text" class="form-control" name="resource.childId" id="childId">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">House<span class="required" aria-required="true">*</span></label>
				                                        <select class="form-control" id="house" name="house" required>
				                                            <option value="">Please select</option>
				                                            <c:forEach var="house" items="${houseList}" >
																<option value="${house.houseCode}">${house.houseName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Student Resident Type</label>
				                                        <select class="form-control" id="residentType" name="residentType">
				                                            <option value="">Please select</option>
				                                            <c:forEach var="srt" items="${residentTypeList}" >
																<option value="${srt.residentTypeCode}">${srt.residentTypeName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">State Of Domicile</label>
				                                        <select class="form-control" id="stateOfDomicile" name="stateOfDomicile">
				                                            <option value="">Please select</option>
				                                            <c:forEach var="state" items="${stateList}" >
																<option value="${state.stateCode}">${state.stateName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <%-- <div class="form-group">
				                                        <label class="control-label">Scholarship </label>
				                                        <select class="form-control" id="scholarship" name="scholarship">
				                                            <option value="">Please select</option>
				                                            <c:forEach var="scholarship" items="${scholarshipList}" >
																<option value="${scholarship.scholarshipCode}">${scholarship.scholarshipName}</option>
															</c:forEach>
				                                        </select>
				                                    </div> --%>
				                                </div>
				                                <div class="col-md-3"> 
				                                    <div class="form-group">
				                                        <label class="control-label">Bank Name</label>
				                                        <input type="text" class="form-control" name="resource.bankName" id="bankName">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Branch</label>
				                                        <input type="text" class="form-control" name="resource.bankBranch" id="bankBranch">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">A/C No.</label>
				                                        <input type="text" class="form-control" name="resource.accountNumber" id="accountNumber">
				                                    </div>
				                                    
				                                    <div class="form-group">
				                                        <label class="control-label">Medical Status</label>
				                                        <select class="form-control" id="medicalStatus" name="resource.medicalStatus">
				                                            <option value="">Please select</option>
				                                            <option value="Fit">Fit</option>
															<option value="Unfit">Unfit</option>
				                                        </select>
				                                    </div>
				                                    <!-- PRAD JUNE 14 2018 -->
				                                    <div class="form-group">
				                                        <label class="control-label">Contact Number (Mobile)<span class="required" aria-required="true">*</span></label>
				                                        <div class="input-group">
				                                            <span class="input-group-addon">
				                                                <i class="fa fa-mobile"></i>
				                                            </span>
				                                            <input class="form-control" placeholder="(91) 98300-98300" data-input-mask="(99) 99999-99999" data-plugin-masked-input="" name="resource.mobile" id="mobile" required>
				                                        </div>
				                                    </div>
				                                    <!-- PRAD ENDS -->
				                                    <div class="form-group">
				                                        <label class="control-label">E-Mail</label>
				                                        <input type="email" placeholder="eg.: email@email.com" class="form-control" name="resource.emailId" id="emailId" aria-invalid="true">
				                                    </div>
				                                </div>
				                                <div class="col-md-12">&nbsp;
				                                </div>
				                            </div>
				                            <hr>
				                            <div class="row">
				                                <div class="col-md-12">&nbsp;
				                                </div>
				                                <div class="col-md-3">
				                                    <blockquote class="b-thin rounded primary">
				                                        <h3>Father's Details</h3>
				                                    </blockquote>
				                                    <div class="form-group">
				                                        <label class="control-label">First Name</label>
				                                        <input type="text" class="form-control" name="resource.fatherFirstName" id="fatherFirstName"  placeholder="Father's First Name">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Middle Name</label>
				                                        <input type="text" class="form-control" name="resource.fatherMiddleName" id="fatherMiddleName" placeholder="Father's Middle Name">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Last Name</label>
				                                        <input type="text" class="form-control" name="resource.fatherLastName" id="fatherLastName" placeholder="Father's Last Name">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Occupation</label>
				                                        <input type="text" class="form-control" name="resource.fatherOccupation" id="fatherOccupation" placeholder="Father's Occupation">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">In Defence</label>
				                                        <select class="form-control" id="fatherInDefence" name="resource.fatherInDefence"> 
				                                            <option value="">Please select</option>
				                                            <option value="false">No</option>
															<option value="true">Yes</option>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Service Status</label>
				                                        <select class="form-control" id="fatherServiceStatus" name="resource.fatherServiceStatus">
				                                            <option value="">Please select</option>
				                                            <option value="Ex-Defence">Ex-Defence</option>
															<option value="Defence">Defence</option>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Defence Category</label>
				                                        <select class="form-control" id="fatherDefenceCategory" name="resource.fatherDefenceCategory">
				                                            <option value="">Please select</option>
				                                            <option value="Air-Force">Air Force</option>
															<option value="Army">Army</option>
															<option value="Navy">Navy</option>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Rank</label>
				                                        <input type="text" class="form-control" name="resource.fatherRank" id="fatherRank" placeholder="">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Mobile</label>
				                                        <div class="input-group">
				                                            <span class="input-group-addon">
				                                                <i class="fa fa-mobile"></i>
				                                            </span>
				                                            <input class="form-control" placeholder="(91) 98300-98300" data-input-mask="(99) 99999-99999" data-plugin-masked-input="" name="resource.fatherMobile" id="fatherMobile">
				                                        </div>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">E-Mail</label>
				                                        <input type="email" placeholder="eg.: email@email.com" class="form-control" name="resource.fatherEmail" id="fatherEmail" aria-required="true" aria-invalid="true">
				                                    </div>
				                                </div>
				                                <div class="col-md-3">
				                                    <blockquote class="b-thin rounded primary">
				                                        <h3>Mother's Details</h3>
				                                    </blockquote>
				                                    <div class="form-group">
				                                        <label class="control-label">First Name</label>
				                                        <input type="text" class="form-control" name="resource.motherFirstName" id="motherFirstName" placeholder="Mother's First Name">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Middle Name</label>
				                                        <input type="text" class="form-control" name="resource.motherMiddleName" id="motherMiddleName" placeholder="Mother's Middle Name" >
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Last Name</label>
				                                        <input type="text" class="form-control" name="resource.motherLastName" id="motherLastName" placeholder="Mother's Last Name">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Mobile</label>
				                                        <div class="input-group">
				                                            <span class="input-group-addon">
				                                                <i class="fa fa-mobile"></i>
				                                            </span>
				                                            <input class="form-control" placeholder="(91) 98300-98300" data-input-mask="(99) 99999-99999" data-plugin-masked-input="" name="resource.motherMobile" id="motherMobile">
				                                        </div>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">E-Mail</label>
				                                        <input type="email" placeholder="eg.: email@email.com" class="form-control" name="resource.motherEmail" id="motherEmail" aria-required="true" aria-invalid="true">
				                                    </div>
				                                </div>
				                                <div class="col-md-3">
				                                    <blockquote class="b-thin rounded primary">
				                                        <h3>Guardian's Details</h3>
				                                    </blockquote>
				                                    <div class="checkbox-custom checkbox-default">
				                                        <input type="checkbox" onchange="copyMotherDetailsToGuardian(this);">
				                                        <label for="checkboxExample1">Same as Mother's Details</label>
				                                    </div>
				                                    <div class="checkbox-custom checkbox-default">
				                                        <input type="checkbox" onchange="copyFatherDetailsToGuardian(this);">
				                                        <label for="checkboxExample1">Same as Father's Details</label>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">First Name</label>
				                                        <input type="text" class="form-control" name="guardianFirstName" id="guardianFirstName" placeholder="Guardian's First Name">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Middle Name</label>
				                                        <input type="text" class="form-control" name="guardianMiddleName" id="guardianMiddleName" placeholder="Guardian's Middle Name">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Last Name</label>
				                                        <input type="text" class="form-control" name="guardianLastName" id="guardianLastName" placeholder="Guardian's Last Name">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Mobile</label>
				                                        <div class="input-group">
				                                            <span class="input-group-addon">
				                                                <i class="fa fa-mobile"></i>
				                                            </span>
				                                            <input class="form-control" placeholder="(91) 98300-98300" data-input-mask="(99) 99999-99999" data-plugin-masked-input="" name="guardianMobile" id="guardianMobile">
				                                        </div>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">E-Mail</label>
				                                        <input type="email" placeholder="eg.: email@email.com" class="form-control" name="guardianEmail" id="guardianEmail" aria-required="true" aria-invalid="true">
				                                    </div>
				                                </div>
				                                <div class="col-md-3">
				                                    <blockquote class="b-thin rounded primary">
				                                        <h3>Income Details</h3>
				                                    </blockquote>
				                                    <div class="form-group">
				                                        <label class="control-label">Father's Income</label>
				                                        <input type="text" class="form-control" name="fatherIncome" id="fatherIncome" placeholder="">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Mother's Income</label>
				                                        <input type="text" class="form-control" name="motherIncome" id="motherIncome" placeholder="">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Student's Income</label>
				                                        <input type="text" class="form-control" name="studentIncome" id="studentIncome" placeholder="">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Family Income</label>
				                                        <input type="text" class="form-control" name="familyIncome" id="familyIncome" placeholder="">
				                                    </div>
				                                </div>
				                            </div>
				                            <div class="row">
				                                <div class="col-md-12">&nbsp;</div>
				                                <div class="col-md-4">
				                                    <blockquote class="b-thin rounded primary">
				                                        <h3>Present Address</h3>
				                                    </blockquote>
				                                    <div class="form-group">
				                                        <label class="control-label">Address</label>
				                                        <input type="text" class="form-control" id="presentAddressLine" name="address.presentAddressLine">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Landmark</label>
				                                        <input type="text" class="form-control" id="presentAddressLandmark" name="address.presentAddressLandmark">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">City/Village</label>
				                                        <input type="text" class="form-control" id="presentAddressCityVillage" name="address.presentAddressCityVillage">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Pin code</label>
				                                        <input type="text" class="form-control" id="presentAddressPinCode" name="address.presentAddressPinCode">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">District</label>
				                                        <input type="text" class="form-control" id="presentAddressDistrict" name="address.presentAddressDistrict">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">State</label>
				                                        <select class="form-control" name="address.presentAddressState" id="presentAddressState">
				                                            <option value="">Please select</option>
				                                            <c:forEach var="state" items="${stateList}" >
																<option value="${state.stateCode}">${state.stateName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Country</label>
				                                        <select class="form-control" id="presentAddressCountry" name="address.presentAddressCountry">
				                                            <option value="">Please select</option>
				                                            <c:forEach var="country" items="${countryList}" >
																<option value="${country.countryCode}" ${country.countryCode eq 'IND' ?'selected="slected"':''}>${country.countryName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Post Office</label>
				                                        <input type="text" class="form-control" id="presentAddressPostOffice" name="address.presentAddressPostOffice">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Police Station</label>
				                                        <input type="text" class="form-control" id="presentAddressPoliceStation" name="address.presentAddressPoliceStation">
				                                    </div>
				                                </div>
				                                <div class="col-md-4">
				                                    <blockquote class="b-thin rounded primary">
				                                        <h3>Permanent Address</h3>
				                                    </blockquote>
				                                    <div class="checkbox-custom checkbox-default">
				                                        <input type="checkbox" onchange="copyPresentAddressToPermanentAddress(this);">
				                                        <label for="checkboxExample1">Same as Present Address</label>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Address</label>
				                                        <input type="text" class="form-control" id="permanentAddressLine" name="address.permanentAddressLine">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Landmark</label>
				                                        <input type="text" class="form-control" id="permanentAddressLandmark" name="address.permanentAddressLandmark">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">City/Village</label>
				                                        <input type="text" class="form-control" id="permanentAddressCityVillage" name="address.permanentAddressCityVillage">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Pin code</label>
				                                        <input type="text" class="form-control" id="permanentAddressPinCode" name="address.permanentAddressPinCode">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">District</label>
				                                        <input type="text" class="form-control" id="permanentAddressDistrict" name="address.permanentAddressDistrict">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">State</label>
				                                        <select class="form-control" name="address.permanentAddressState" id="permanentAddressState">
				                                            <option value="">Please select</option>
				                                            <c:forEach var="state" items="${stateList}" >
																<option value="${state.stateCode}">${state.stateName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Country</label>
				                                        <select class="form-control" id="permanentAddressCountry" name="address.permanentAddressCountry">
				                                            <option value="">Please select</option>
				                                            <c:forEach var="country" items="${countryList}" >							
																<option value="${country.countryCode}" ${country.countryCode eq 'IND' ?'selected="slected"':''}>${country.countryName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Post Office</label>
				                                        <input type="text" class="form-control" id="permanentAddressPostOffice" name="address.permanentAddressPostOffice">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Police Station</label>
				                                        <input type="text" class="form-control" id="permanentAddressPoliceStation" name="address.permanentAddressPoliceStation">
				                                    </div>
				                                </div>
				                                <div class="col-md-4">
				                                    <blockquote class="b-thin rounded primary">
				                                        <h3>Local Guardian's Address</h3>
				                                    </blockquote>
				                                    <div class="checkbox-custom checkbox-default">
				                                        <input type="checkbox" onchange="copyPresentAddressToGuardianAddress(this);">
				                                        <label for="checkboxExample1">Same as Present Address</label>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Address</label>
				                                        <input type="text" class="form-control" id="guardianAddressLine" name="address.guardianAddressLine">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Landmark</label>
				                                        <input type="text" class="form-control" id="guardianAddressLandmark" name="address.guardianAddressLandmark">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">City/Village</label>
				                                        <input type="text" class="form-control" id="guardianAddressCityVillage" name="address.guardianAddressCityVillage">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Pin code</label>
				                                        <input type="text" class="form-control" id="guardianAddressPinCode" name="address.guardianAddressPinCode">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">District</label>
				                                        <input type="text" class="form-control" id="guardianAddressDistrict" name="address.guardianAddressDistrict">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">State</label>
				                                        <select class="form-control" name="address.guardianAddressState" id="guardianAddressState">
				                                            <option value="">Please select</option>
				                                            <c:forEach var="state" items="${stateList}" >
																<option value="${state.stateCode}">${state.stateName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Country</label>
				                                        <select class="form-control" id="guardianAddressCountry" name="address.guardianAddressCountry">
				                                            <option value="">Please select</option>
				                                            <c:forEach var="country" items="${countryList}" >
																<option value="${country.countryCode}" ${country.countryCode eq 'IND' ?'selected="slected"':''}>${country.countryName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Post Office</label>
				                                        <input type="text" class="form-control" id="guardianAddressPostOffice" name="address.guardianAddressPostOffice">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Police Station</label>
				                                        <input type="text" class="form-control" id="guardianAddressPoliceStation" name="address.guardianAddressPoliceStation">
				                                    </div>
				                                </div>                                                       
				                            </div>
				                            <hr>
				                            <div class="row">
				                                <div class="col-md-12">
				                                    <blockquote class="b-thin rounded primary">
				                                    	<h3>Other Details</h3>
				                                </blockquote>
				                                </div>
				                                <div class="col-md-3">
				                                    <div class="form-group">
				                                        <label class="control-label">Food Preference</label>
				                                        <select class="form-control" id="foodPreference" name="resource.foodPreference">
				                                            <option value="">Please select</option>
															<option value="Veg">Veg</option>
															<option value="Nonveg">Non-Veg</option>
				                                        </select>
				                                    </div>
				                                </div>
				                                <div class="col-md-3">
				                              		<div class="form-group">
			                                            <label class="control-label">Pickup Place</label>
			                                            <input type="text" class="form-control" id="firstPickUpPlace" name = "resource.firstPickUpPlace">
		                                         	</div>
		                                        </div>
				                                <div class="col-md-3">
				                                    <div class="form-group">
				                                        <label class="control-label">Hobbies</label>
				                                        <input type="text" class="form-control" id="hobbies" name="resource.hobbies">
				                                    </div>
				                                </div>
				                                <div class="col-md-3">
				                                    <div class="form-group">
				                                        <label class="control-label">Personal Identification</label>
				                                        <input type="text" class="form-control" name="resource.personalIdentificationMark" id="personalIdentificationMark">
				                                    </div>
				                                </div>
				                            </div>
				                        </div>
				                        <div id="w4-Educational" class="tab-pane">
				                            <div class="row">
				                                <div class="col-md-12">
				                                    <blockquote class="b-thin rounded primary">
				                                        <h3>Previous Education Details</h3>
				                                    </blockquote>
				                                </div>    
				                                <div class="col-md-6">
				                                    <div class="form-group">
				                                        <label class="control-label">School Name</label>
				                                        <input type="text" class="form-control" id="previousSchoolName" name="previousSchoolName">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Website</label>
				                                        <input type="text" class="form-control" id="previousSchoolWebsite" name="previousSchoolWebsite">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Address</label>
				                                        <input type="text" class="form-control" id="previousSchoolAddress" name="previousSchoolAddress">
				                                    </div>
				                                    <div class="form-group">
										                  <label class="control-label">Phone</label>
				                                        <div class="input-group">
				                                            <span class="input-group-addon">
				                                                <i class="fa fa-mobile"></i>
				                                            </span>
				                                            <input id="previousSchoolPhone" name = "previousSchoolPhone"  data-plugin-masked-input="" data-input-mask="(99) 99999-99999" placeholder="(91) 98300-98300" class="form-control valid" aria-invalid="false">
				                                        </div>
				                                    </div>
				                                    <div class="form-group">
											            <label class="control-label">E-Mail</label>
				                                        <input type="text" id="previousSchoolEmail" name="previousSchoolEmail"  placeholder="eg.: email@email.com" class="form-control" aria-required="true" aria-invalid="true">
				                                    </div>
				                                    <div class="form-group">
											            <label class="control-label">Previous Achievements</label>
				                                        <input type="text" class="form-control" id="previousAchivement" name="previousAchivement">
				                                    </div>
				                                </div>
				                                <div class="col-md-6">
				                                	<div class="form-group">
		                                                <label class="control-label">Upload Documents </label>
		                                                <table>
		                                                    <tr>
		                                                        <td><span class="btn btn-default btn-file"><input type="file" id="fileData0" name="resource.uploadFile.fileData" ></span></td>
		                                                        <td><input id="addFile2" class="mb-xs mt-xs mr-xs btn btn-primary addFileClassName" type="button" value="Add More"/></td>
		                                                    </tr>
		                                                    <!-- tr>
		                                                        <td><input type="file"></td>
		                                                        <td><a style="margin:10px;" href="#" class="on-default remove-row"><i class="fa fa-plus-square"></i></a></td>
		                                                    </tr> -->
		                                                </table>                                                    
		                                               </div>
				                                    <!-- <div class="form-group">
				                         				<label class="control-label">Upload Documents</label>
				                                        <div class="fileupload fileupload-new" data-provides="fileupload">
				                                            <div class="input-append">				                                                
				                                                <span class="btn btn-default btn-file">
				                                                    <input type="file" name="resource.uploadFile.fileData" id="fileData0"/>
				                                                </span>
				                                                <a href="#" class="btn btn-danger fileupload-exists" data-dismiss="fileupload">Remove</a>
				                                            </div>
				                                        </div>
				                                    </div>
				                                    <input id="addFile2" class="mb-xs mt-xs mr-xs btn btn-primary addFileClassName" type="button" value="Add more documents"/> -->
				                                    <!-- <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button"></button> -->
				                                </div>
				                            </div>
				                        </div>
				                        <div id="w4-Upload" class="tab-pane">
				                            <div class="row">
				                                <div class="col-md-12">
				                                    <blockquote class="b-thin rounded primary">
								                         <h3>Upload Image</h3>
				                                    </blockquote>
				                                </div>
				                                <div class="col-md-6">
					                                <div class="form-group">
					                                    <label class="control-label">Student's Image</label>
					                                    <div class="fileupload fileupload-new" data-provides="fileupload">
					                                        <div class="input-append">
					                                            <div class="uneditable-input">
					                                                <span class="fileupload-preview"></span>
					                                                <img id="preview" src="images/upload.png" style="width:200px;height:200px;"/>
					                                            </div>
				                                            	<span class="btn btn-default btn-file">
					                                                <span class="fileupload-exists">Change</span>
					                                                <span class="fileupload-new">Select file</span>
					                                         	</span>
					                                         <input type="file" name="resource.image.imageData" id="image_upload" onchange="fileSelected();"/>
					                                            <a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">Remove</a>
					                                        </div>
					                                    </div>
					                                </div>
					                            </div>
			                                <div class="col-md-12">
												<button id="submit" class="btn btn-primary pull-right" type="submit">Submit </button>
		                                    </div>
			                            </div>
			                        </div>
		                        </div>             
				            </div>
				            <c:if test="${repository.repositoryPathName ne null }">
					            <div class="panel-footer">
					                <ul class="pager">
					                    <li class="previous disabled">
					                        <a><i class="fa fa-angle-left"></i> Previous</a>
					                    </li>
	<!-- 				                    <li class="finish hidden pull-right"> -->
	<!-- 				                        <button type="submit" id="submit" name="submit" >Submit</button> -->
	<!-- 				                    </li> -->
					                    <li class="next">
					                        <a>Next <i class="fa fa-angle-right"></i></a>
					                    </li>
					                </ul>
					            </div>
					         </c:if>
			            </form:form>
			        </section>    
				</div>
			</div>

		</div>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<!-- <script src="/cedugenie/assets/custom-caleder/jquery-ui.js" type="text/javascript"></script> -->
<script src="/cedugenie/js/backoffice/createStudent.js"></script>
<script src="/cedugenie/js/common/upload.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#presentAddressCityVillage").autocomplete({
		 	source: '/cedugenie/getCityList.html'}); 
		$("#permanentAddressCityVillage").autocomplete({
			source: '/cedugenie/getCityList.html'});
		$("#guardianAddressCityVillage").autocomplete({
			source: '/cedugenie/getCityList.html'});
		$("#presentAddressDistrict").autocomplete({
	 		source: '/cedugenie/getDistrictList.html'}); 
		$("#permanentAddressDistrict").autocomplete({
			source: '/cedugenie/getDistrictList.html'});
		$("#guardianAddressDistrict").autocomplete({
			source: '/cedugenie/getDistrictList.html'});
	});
	/* $("#dateOfBirth").datepicker({
		 dateFormat: 'dd/mm/yy'
    });
	$("#dateOfAdmission").datepicker({
		 dateFormat: 'dd/mm/yy'
    }); */
</script>
</body>
</html>