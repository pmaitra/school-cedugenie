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
<title>Student Details Form</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

<c:if test="${successStatus != null}">
	<div class="successbox" id="successbox" style="visibility:visible;">
		<span id="infomsg" style="visibility:visible;">${successStatus}</span>	
	</div>
</c:if>
<c:if test="${failStatus != null}">
		<div class="errorbox" id="errorbox" style="visibility: visible;">
			<span id="errormsg">Update Fail!</span>	
		</div>
</c:if>

	<div class="row">
		<div class="col-xs-12">
			<section class="panel form-wizard" id="w4">
				<header class="panel-heading">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
					</div>
	
					<h2 class="panel-title">Admission Form</h2>
				</header>
				<div class="panel-body">
					<div class="wizard-progress wizard-progress-lg">
						<div class="steps-progress">
							<div class="progress-indicator"></div>
						</div>
						<ul class="wizard-steps">
							<li class="active">
								<a href="#w4-Personal" data-toggle="tab"><span>1</span>Personal <br> Details</a>
							</li>
							<li>
								<a href="#w4-Educational" data-toggle="tab"><span>2</span>Previous <br> Educational Details</a>
							</li>
							<li>
								<a href="#w4-Examination" data-toggle="tab"><span>3</span>Examination <br> Details</a>
							</li>
							<li>
								<a href="#w4-Bank" data-toggle="tab"><span>4</span>Bank <br> Details</a>
							</li>
                                           <li>
								<a href="#w4-Upload" data-toggle="tab"><span>5</span>Upload <br> Document</a>
							</li>
						</ul>
					</div>
	
					<form:form name="candidateForm" id="candidateForm" enctype="multipart/form-data" action="submitAdmissionForm.html" method="POST" class="" >	
						<div class="tab-content">
							<div id="w4-Personal" class="tab-pane active">
                               <div class="row">
                                   <div class="col-md-12">
                                       <blockquote class="b-thin rounded primary">
                                           <h3>Academic Year : ${candidate.academicYear.academicYearName} </h3>
											<input type="hidden" name="academicYear.academicYearName" id="year" readonly="readonly" value="${candidate.academicYear.academicYearCode}"/>	
                                       </blockquote>
                                   </div>
                                    <div class="col-md-3">                                                            
                                       <div class="form-group">
                                           <label class="control-label">Class</label>
                                           <select id="standard" name="standard.standardName" class="form-control">
                                               <option value="NULL">Please select</option>	
												<c:forEach var="standard" items="${candidate.standardList}" >
												<OPTION value="${standard.standardCode}">${standard.standardName}</option>
												</c:forEach>
                                           </select>
                                       </div>
                                   </div>
                               </div>
                               <hr>
                               <div class="row">
                                   <div class="col-md-12">
                                   <blockquote class="b-thin rounded primary">
                                       <h3>Candidate's Details</h3>
                                   </blockquote>
                                   </div>
                                   <div class="col-md-3">                                                            
                                       <div class="form-group">
                                           <label class="control-label">First Name</label>
                                           <input type="text" class="form-control" id="firstName" name="resource.firstName" placeholder="First Name" required>
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Middle Name</label>
                                           <input type="text" class="form-control" id="middleName" name="resource.middleName" placeholder="Middle Name">
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Last Name</label>
                                           <input type="text" class="form-control" id="lastName" name="resource.lastName" placeholder="Last Name" required>
                                       </div>                                                           
                                   </div>
                                   <div class="col-md-3">
                                       <div class="form-group">
                                           <label class="control-label">Date Of Birth</label>
                                           <div class="input-group">
                                               <span class="input-group-addon">
                                                   <i class="fa fa-calendar"></i>
                                               </span>
                                               <input class="form-control" placeholder="__/__/____" data-input-mask="99/99/9999" data-plugin-masked-input="" id="dateOfBirth" name="resource.dateOfBirth" required>
                                           </div>
                                           <label id="dateOfBirth-error" class="error" for="dateOfBirth"></label>
                                       </div>
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
                                               <option value="UNKNOWN">UNKNOWN</option>
                                           </select>
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Gender</label>
                                           <div class="form-group" style="margin-top: 5px;">
                                           <label class="radio-inline radio-primary"> 
                                               <input type="radio" name="resource.gender" value="M" id="male" checked> Male 
                                           </label>
                                           <label class="radio-inline radio-primary"> 
                                               <input type="radio" name="resource.gender" value="F" id="female" > Female 
                                           </label>
                                           </div>
                                           
                                       </div>
                                   </div>
                                   <div class="col-md-3">                                                            
                                       <div class="form-group">
                                           <label class="control-label">Category</label>
                                           <select class="form-control" id="category" name="socialCategory.socialCategoryName">
                                               <OPTION VALUE="" >Please select</option>
												<c:forEach var="socialCategory" items="${candidate.socialCategoryList}" >
												<OPTION VALUE="${socialCategory.socialCategoryCode}">${socialCategory.socialCategoryName}</option>
												</c:forEach>
                                           </select>
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Religion</label>
                                           <input type="text" class="form-control" id="religion" name="resource.religion">
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Nationality</label>
                                           <input type="text" class="form-control" id="nationality" name="resource.nationality">
                                       </div>                                                           
                                   </div>
                                   <div class="col-md-3">                                                            
                                       <div class="form-group">
                                           <label class="control-label">Identification Mark</label>
                                           <input type="text" class="form-control" id="identificationMark" name="resource.identificationMark">
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Contact Number</label>
                                           <div class="input-group">
                                               <span class="input-group-addon">
                                                   <i class="fa fa-mobile"></i>
                                               </span>
                                               <input class="form-control" placeholder="(91) 98300-98300" data-input-mask="(99) 99999-99999" data-plugin-masked-input="" name="resource.mobile" id="mobile">
                                           </div>
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">E-Mail</label>
                                           <input type="email" required="" placeholder="eg.: email@email.com" class="form-control" id="emailId" name="resource.emailId" aria-required="true" aria-invalid="true">
                                       </div>                                                           
                                   </div>
                                   <div class="col-md-12">&nbsp;
                                   </div>
                               </div>
                               <hr>
                               <div class="row">
                                   <div class="col-md-12">&nbsp;
                                   </div>
                                   <div class="col-md-4">
                                       <blockquote class="b-thin rounded primary">
                                           <h3>Father's Details</h3>
                                       </blockquote>
                                       <div class="form-group">
                                           <label class="control-label">First Name</label>
                                           <input type="text" class="form-control" id="fatherFirstName" name="resource.fatherFirstName" placeholder="Father's First Name">
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Middle Name</label>
                                           <input type="text" class="form-control" id="fatherMiddleName" name="resource.fatherMiddleName" placeholder="Father's Middle Name">
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Last Name</label>
                                           <input type="text" class="form-control" id="fatherLastName" name="resource.fatherLastName" placeholder="Father's Last Name">
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Occupation</label>
                                           <select class="form-control">
                                               <option value="">Please select</option>
                                               <option>Agriculture</option>
                                               <option>Business</option>
                                               <option>Salary</option>
                                               <option>Pension</option>
                                               <option>Others</option>
                                           </select>
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Total Income</label>
                                           <input type="text" class="form-control" name="income" placeholder="Total Income">
                                       </div>
                                   </div>
                                   <div class="col-md-4">
                                       <blockquote class="b-thin rounded primary">
                                           <h3>Mother's Details</h3>
                                       </blockquote>
                                       <div class="form-group">
                                           <label class="control-label">First Name</label>
                                           <input type="text" class="form-control" id="motherFirstName" name="resource.motherFirstName" placeholder="Mother's First Name">
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Middle Name</label>
                                           <input type="text" class="form-control" id="motherMiddleName" name="resource.motherMiddleName" placeholder="Mother's Middle Name">
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Last Name</label>
                                           <input type="text" class="form-control" id="motherLastName" name="resource.motherLastName" placeholder="Mother's Last Name">
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Occupation</label>
                                           <select class="form-control">
                                               <option value="">Please select</option>
                                               <option>Agriculture</option>
                                               <option>Business</option>
                                               <option>Salary</option>
                                               <option>Pension</option>
                                               <option>Others</option>
                                           </select>
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Total Income</label>
                                           <input type="text" class="form-control" name="income" placeholder="Total Income">
                                       </div>
                                   </div>
                                   <div class="col-md-4">
                                       <blockquote class="b-thin rounded primary">
                                           <h3>Guardian's Details</h3>
                                       </blockquote>
                                       <div class="form-group">
                                           <label class="control-label">First Name</label>
                                           <input type="text" class="form-control" id="guardianFirstName" name="guardianFirstName" placeholder="Guardian's First Name">
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Middle Name</label>
                                           <input type="text" class="form-control" id="guardianMiddleName" name="guardianMiddleName" placeholder="Guardian's Middle Name">
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Last Name</label>
                                           <input type="text" class="form-control" id="guardianLastName" name="guardianLastName" placeholder="Guardian's Last Name">
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Occupation</label>
                                           <select class="form-control">
                                               <option value="">Please select</option>
                                               <option>Agriculture</option>
                                               <option>Business</option>
                                               <option>Salary</option>
                                               <option>Pension</option>
                                               <option>Others</option>
                                           </select>
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Total Income</label>
                                           <input type="text" class="form-control" name="income" placeholder="Total Income">
                                       </div>
                                   </div>
                               </div>

                               <div class="row">
                                   <div class="col-md-12">&nbsp;
                                   </div>
                                   <div class="col-md-12">
                                       <blockquote class="b-thin rounded primary">
                                           <h3>If you belong to defence category,Please furnish the following details:</h3>
                                       </blockquote>
                                   </div>
                                   <div class="col-md-6">
                                       <div class="form-group">
                                           <label class="control-label">Whether serving or Ex- service personnel</label>
                                           <input type="text" class="form-control" name="exServicePersonnel" id="exServicePersonnel">
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Service Number</label>
                                           <input type="text" class="form-control" name="serviceNumber" id="serviceNumber">
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Date of Enrolment/ Commission</label>
                                           <div class="input-group">
                                               <span class="input-group-addon">
                                                   <i class="fa fa-calendar"></i>
                                               </span>
                                               <input class="form-control" placeholder="__/__/____" data-input-mask="99/99/9999" data-plugin-masked-input="" name="dateOfEnrolment" id="dateOfEnrolment">
                                           </div>
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Name of the Record Office</label>
                                           <input type="text" class="form-control" name="recordOfficeName" id="recordOfficeName">
                                       </div>
                                   </div>
                                   <div class="col-md-6">
                                       <div class="form-group">
                                           <label class="control-label">Service(Army/Navy/Air Force)</label>
                                           <input type="text" class="form-control" name="service" id="service">
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Rank</label>
                                           <input type="text" class="form-control" name="rank" id="rank">
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">Date of Discharge</label>
                                           <div class="input-group">
                                               <span class="input-group-addon">
                                                   <i class="fa fa-calendar"></i>
                                               </span>
                                               <input class="form-control" placeholder="__/__/____" data-input-mask="99/99/9999" data-plugin-masked-input="" name="dateOfDischarge" id="dateOfDischarge">
                                           </div>
                                       </div>
                                   </div>
                               </div>
                               <div class="row">
                                   <div class="col-md-12">&nbsp;</div>
                                   <div class="col-md-5">
                                       <blockquote class="b-thin rounded primary">
                                           <h3>Present Address</h3>
                                       </blockquote>
                                       <div class="form-group">
                                           <label class="control-label">Address</label>
                                           <input type="text" class="form-control" id="presentAddress1" name="address.presentAddressLine">
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
                                           <label class="control-label">Country</label>
                                           <select class="form-control" id="presentAddressCountry" name="address.presentAddressCountry">
                                               <OPTION VALUE="">Please select</option>
												<c:forEach var="country" items="${candidate.countryList}" >
													<OPTION VALUE="${country.countryCode}">${country.countryName}</option>
												</c:forEach>
                                           </select>
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">State</label>
                                           <select class="form-control" name="address.presentAddressState" id="presentAddressState">
                                               <option value="">Please select</option>
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
                                   <div class="col-md-2" style="margin-top: 130px;">
                                         <div class="checkbox-custom checkbox-default">
										<input type="checkbox" onclick="makeReadOnly(this.value);">
											<label for="checkboxExample1">Same as Present Address</label>
										</div>
                                     </div>
                                     <div class="col-md-5">
                                         <blockquote class="b-thin rounded primary">
                                             <h3>Permanent Address</h3>
                                         </blockquote>
                                         <div class="form-group">
                                             <label class="control-label">Address</label>
                                             <input type="text" class="form-control" id="permanentAddress1" name="address.permanentAddressLine">
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
                                             <label class="control-label">Country</label>
                                             <select class="form-control" id="permanentAddressCountry" name="address.permanentAddressCountry">
                                                 <OPTION VALUE="">Please select</option>
														<c:forEach var="country" items="${candidate.countryList}" >
													<OPTION VALUE="${country.countryCode}">${country.countryName}</option>
												</c:forEach>
                                           </select>
                                       </div>
                                       <div class="form-group">
                                           <label class="control-label">State</label>
                                           <select class="form-control" name="address.permanentAddressState" id="permanentAddressState">
                                               <option value="">Please select</option>
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
                               </div>
							</div>
							<div id="w4-Educational" class="tab-pane">
                                <div class="row">
                                    <div class="col-md-12">
                                        <blockquote class="b-thin rounded primary">
                                            <h3>Previous School Details</h3>
                                        </blockquote>
                                    </div>    
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label">Previous School Name</label>
                                            <input type="text" class="form-control" id="previousSchoolName" name="previousSchoolName">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Website</label>
                                            <input type="text" class="form-control" id="previousSchoolWebsite" name="previousSchoolWebsite">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Upload Documents</label>
                                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                                <div class="input-append">
                                                    <div class="uneditable-input">                                                                            
                                                        <span class="fileupload-preview"></span>
                                                    </div>
                                                    <span class="btn btn-default btn-file">
                                                        <span class="fileupload-exists">Change</span>
                                                        <span class="fileupload-new">Select file</span>
                                                        <input type="file" name="resource.uploadFile.fileData" id="fileData0" onchange="docSelected();"/>
                                                    </span>
                                                    <a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">Remove</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label">Contact</label>
                                            <div class="input-group">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-mobile"></i>
                                                </span>
                                                <input id="previousSchoolContact" name="previousSchoolContact" data-plugin-masked-input="" data-input-mask="(99) 99999-99999" placeholder="(91) 98300-98300" class="form-control valid" aria-invalid="false">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Class</label>
                                            <input type="text" class="form-control" name="previousSchoolStandard" id="previousSchoolStandard">
                                        </div>
                                    </div>
                                </div>
							</div>
							<div id="w4-Examination" class="tab-pane">
                                 <div class="row">
                                     <div class="col-md-12">
                                         <blockquote class="b-thin rounded primary">
                                             <h3>Examination Details</h3>
                                         </blockquote>    
                                     </div>    
                                     <div class="col-md-12">
                                         <div class="form-group">
                                             <label class="col-sm-5 control-label" for="">Centre for Entrance Examination:(if one)</label>
                                             <div class="col-sm-7">
                                                 <select class="form-control" id="examinationCentre" name="examinationCentre">
                                                     <OPTION value=null>ANY ONE</option>
													<c:forEach var="venue" items="${examVenueList}" >
													<OPTION value="${venue.venueName}">${venue.venueName}</option>
													</c:forEach>
                                                 </select>
                                             </div>
                                         </div>
                                         <div class="form-group">
                                             <label class="col-sm-5 control-label" for="">Medium for Entrance Examination:
                                             (For Class VI-English,Bengali,Hindi-Any One)
                                             (For Class IX-English Only)</label>
                                             <div class="col-sm-7">
                                                 <input type="text" class="form-control" id="examMedium" name="examinationMedium">
                                             </div>
                                         </div>
                                         <div class="form-group">
                                             <label class="col-sm-5 control-label" for="">Are you prepared to send your son/ward to any other
                                             sainik School (Other than Sainik School Purulia)if allotted?
                                             If so,give name of the school in order of preference.</label>
                                             <div class="col-sm-7">
                                                 <input type="text" class="form-control" name="preferenceSchool1" id="preferenceSchool1" placeholder="1"><br>
                                                 <input type="text" class="form-control" name="preferenceSchool2" id="preferenceSchool2" placeholder="2"><br>
                                                 <input type="text" class="form-control" name="preferenceSchool3" id="preferenceSchool3" placeholder="3">
                                             </div>
                                         </div>
                                     </div>                                                        

                                 </div>
							</div>
							<div id="w4-Bank" class="tab-pane">
								<div class="row">
                                    <div class="col-md-12">
                                        <blockquote class="b-thin rounded primary">
                                            <h3>Details of Bank Draft/IPO submitted(for Application Forms downloaded from Website)</h3>
                                        </blockquote>    
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label">D.D./I.P.O. No.</label>
                                            <input type="text" id="bankDdIpoNo" name="bankDdIpoNo" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Date</label>
                                            <div class="input-group">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-calendar"></i>
                                                </span>
                                                <input type="text" id="bankDate" name="bankDate" class="form-control" data-plugin-datepicker="">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="control-label">Amount</label>
                                            <div class="input-group mb-md">
                                                <span class="input-group-addon">
                                                    <i class="fa fa-inr"></i>
                                                </span>
                                                <input type="text" id="bankAmount" name="bankAmount" class="form-control">
                                                <span class="input-group-addon ">.00</span>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Name of the Issuing branch</label>
                                            <input type="text" id="issuingBankBranchName" name="issuingBankBranchName" class="form-control">
                                        </div>
                                    </div>
                                </div>
							</div>
                            <div id="w4-Upload" class="tab-pane">
							<div class="row">
                                     <div class="col-md-12">
                                         <blockquote class="b-thin rounded primary">
                                             <h3>Upload Document</h3>
                                         </blockquote>    
                                     </div>
                                     <div class="col-md-6">
                                         <div class="form-group">
                                             <label class="control-label">Whether the candidate belongs to Schedule Cast/Trible:<br>
                                             (If Yes,mention community and submit Community Certificate) 	</label>
                                             <input type="text" name="scheduledCastCommunity" id="scheduledCastCommunity" class="form-control">
                                         </div>
                                         <div class="form-group">
                                             <label class="control-label">Upload Documents</label>
                                             <div class="fileupload fileupload-new" data-provides="fileupload">
                                                 <div class="input-append">
                                                     <div class="uneditable-input">                                                                            
                                                         <span class="fileupload-preview"></span>
                                                     </div>
                                                     <span class="btn btn-default btn-file">                                                         
                                                         <input type="file" name="resource.uploadFile.file" id="fileDatae"/>
                                                     </span>
                                                     <a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">Remove</a>
                                                 </div>
                                             </div>
                                         </div>
                                     </div>
                                     <div class="col-md-3">
                                         <div class="form-group">                                                                
                                             <form action="#" class="dropzone dz-square" id="dropzone-example"></form>
                                         </div>
                                         <div class="panel-body" style="width:277px;">
                                             <form action="#" class="dropzone dz-square" id="dropzone-example"></form>
                                         </div>
                                     </div>
                                 </div>    
							</div>
						</div>
					</form:form>
				</div>
				<div class="panel-footer">
					<ul class="pager">
						<li class="previous disabled">
							<a><i class="fa fa-angle-left"></i> Previous</a>
						</li>
						<li class="finish hidden pull-right">
							<a>Finish</a>
						</li>
						<li class="next">
							<a>Next <i class="fa fa-angle-right"></i></a>
						</li>
					</ul>
				</div>
			</section>
		</div>
	</div>

					
					
			
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>