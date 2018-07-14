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
<title>Edit Student Details</title>
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
</head>
<body>

			<c:if test="${fileDeleteStatus eq 'Deleted'}">
				<div class="infomsgbox" id="infomsgdiv" style="visibility: visible;">
					<span id="infomsg">Attachment Deleted.</span>	
				</div>
			</c:if>

			<div class="row">
				
				    <div class="col-xs-12">
				        <section class="panel form-wizard" id="w4">
				        <form:form name="editStudent" id="editStudent" enctype="multipart/form-data" action="editStudent.html" method="POST" >
				            <header class="panel-heading">
				                <div class="panel-actions">
				                    <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
				                </div>
				
				                <h2 class="panel-title">Student Details</h2>
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
				                            <a href="#w4-Upload" data-toggle="tab"><span>3</span>Upload <br> Image</a>
				                        </li>
				                    </ul>
				                </div>
				
				                
				                    <div class="tab-content">
				                        <div id="w4-Personal" class="tab-pane active">
				                            <div class="row"> 
				                                <div class="col-md-3">
				                                    <div class="form-group">
				                                        <label class="control-label">School Number<span class="required" aria-required="true">*</span></label>
				                                        <input class="form-control" type="text" name="resourceUserId" id="userId" value="${student.userId}" readonly>
				                                    </div>
				                                </div>
				                            </div>
				                            <hr>
				                            <div class="row" id="personalDetails">
				                                <div class="col-md-12">
				                                <blockquote class="b-thin rounded primary">
				                                    <h3>Student Details</h3>
				                                </blockquote>
				                                </div>
				                                <div class="col-md-3">                                                            
				                                    <div class="form-group">
				                                        <label class="control-label">First Name<span class="required" aria-required="true">*</span></label>
				                                        <input class="form-control" type="text" name="resource.firstName" value="${student.resource.firstName}" id="firstName" required>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Middle Name</label>
				                                        <input type="text" class="form-control" name="resource.middleName" value="${student.resource.middleName}" id="middleName">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Last Name</label>
				                                        <input type="text" class="form-control" name="resource.lastName" value="${student.resource.lastName}" id="lastName">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Date Of Birth<span class="required" aria-required="true">*</span></label>
				                                        <div class="input-group">
				                                            <span class="input-group-addon">
				                                                <i class="fa fa-calendar"></i>
				                                            </span>
				                                            <input class="form-control" placeholder="__/__/____" data-input-mask="99/99/9999" data-plugin-masked-input="" name="resource.dateOfBirth" value="${student.resource.dateOfBirth}" id="dateOfBirth" required>
				                                        </div>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Admission Date<span class="required" aria-required="true">*</span></label>
				                                        <div class="input-group">
				                                            <span class="input-group-addon">
				                                                <i class="fa fa-calendar"></i>
				                                            </span>
				                                            <input class="form-control" placeholder="__/__/____" data-input-mask="99/99/9999" data-plugin-masked-input="" name="dateOfAdmission" value="${student.dateOfAdmission}" id="dateOfAdmission" required>
				                                        </div>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Gender</label>
				                                        <select id="gender" name="resource.gender" class="form-control">
															<c:choose>
																<c:when test="${student.resource.gender eq 'Male' || student.resource.gender eq 'male'}">
																	<option value="Male" selected="selected">Male</option>
																	<option value="Female">Female</option>
																</c:when>
																<c:otherwise>
																	<option value="Male">Male</option>
																	<option value="Female" selected="selected">Female</option>
																</c:otherwise>
															</c:choose>
														</select>
				                                    </div>
				                                </div>
				                                <div class="col-md-3">                                    
				                                    <div class="form-group">
				                                        <label class="control-label">Blood Group</label>
				                                        <select class="form-control" id="bloodGroup" name="resource.bloodGroup">
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
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Category</label>
				                                        <select class="form-control" id="category" name="resource.category">
				                                            <c:forEach var="socialCategory" items="${socialCategoryList}" >
																<option value="${socialCategory.socialCategoryCode}" ${student.resource.category eq socialCategory.socialCategoryCode ? 'selected=selected' : ''}>${socialCategory.socialCategoryName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Religion</label>
				                                        <input class="form-control" name="resource.religion" value="${student.resource.religion}" id="religion">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Mothers Tongue</label>
				                                        <input class="form-control" name="resource.motherTongue" value="${student.resource.motherTongue}" id="motherTongue">
				                                    </div> 
				                                    <div class="form-group">
				                                        <label class="control-label">Nationality</label>
				                                        <input class="form-control" name="resource.nationality" value="${student.resource.nationality}" id="nationality">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Aadhaar No</label>
				                                        <input class="form-control" name="resource.aadharCardNo" id="aadharCardNo" value="${student.resource.aadharCardNo}">
				                                    </div>
				                                </div>
				                                <div class="col-md-3">
				                                    <div class="form-group">
				                                        <label class="control-label">Standard Name<span class="required" aria-required="true">*</span></label>
				                                        <select class="form-control" name ="courseId" id="courseId" required disabled>
					                                    	<c:forEach var="course" items="${courseList}" >
																<option value="${course.courseCode}" ${course.courseCode eq student.courseId ? 'selected=selected' : ''}>${course.courseName}</option>
															</c:forEach>
														</select>	
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Section :</label>
				                                        <input type="hidden" id="oldSec" value="${student.section}">
				                                        <c:if test="${student.section eq 'NA'}">
															<input type = "text" class = "form-control" value = "Not Defined Yet"/>
															<input type="hidden" name="section" id="section" value="${student.section}">
														</c:if>
														<c:if test="${student.section ne 'NA'}">
															<span id="sectionSpan">
																<input type="text" id="section" name="section" value="${student.section}" readonly="readonly" class="form-control">
															</span>
														</c:if>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">House</label>
				                                        <select class="form-control" id="house" name="house" >
				                                            <option value="">Please select</option>
				                                            <c:forEach var="house" items="${houseList}" >
																<option value="${house.houseCode}" ${house.houseCode eq student.houseData.houseCode ? 'selected=selected' : ''}>${house.houseName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    
				                                    <div class="form-group">
				                                        <label class="control-label">State Of Domicile</label>
				                                        <select class="form-control" id="stateOfDomicile" name="stateOfDomicile">
				                                            <option value="">Please select</option>
				                                            <c:forEach var="state" items="${stateList}" >
																<option value="${state.stateCode}" ${state.stateCode eq student.stateOfDomicile ? 'selected=selected' : ''}>${state.stateName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Resident Type</label>
				                                        <select class="form-control" id="residentType" name="residentType" >
				                                            <option value="">Please select</option>
				                                            <c:forEach var="residentType" items="${residentTypeList}" >
																<option value="${residentType.residentTypeCode}" ${residentType.residentTypeCode eq student.residentTypeData.residentTypeCode ? 'selected=selected' : ''}>${residentType.residentTypeName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Contact Number (Mobile)<span class="required" aria-required="true">*</span></label>
				                                        <div class="input-group">
				                                            <span class="input-group-addon">
				                                                <i class="fa fa-mobile"></i>
				                                            </span>
				                                            <input class="form-control" placeholder="(91) 98300-98300" data-input-mask="(99) 99999-99999" data-plugin-masked-input="" name="resource.mobile" id="mobile" value="${student.resource.mobile}" required>
				                                        </div>
				                                    </div>
				                                    <%-- <div class="form-group">
				                                        <label class="control-label">Scholarship </label>
				                                        <select class="form-control" id="scholarship" name="scholarship">
				                                            <option value="">Please select</option>
				                                            <c:forEach var="scholarship" items="${scholarshipList}" >
																<option value="${scholarship.scholarshipCode}" ${student.scholarship eq scholarship.scholarshipCode ? 'selected=selected' : ''}>${scholarship.scholarshipName}</option>
															</c:forEach>
				                                        </select>
				                                    </div> --%>
				                                    
				                                </div>
				                                <div class="col-md-3"> 
				                                    <div class="form-group">
				                                        <label class="control-label">Bank Name</label>
				                                        <input class="form-control" name="resource.bankName" value="${student.resource.bankName}" id="bankName">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Branch</label>
				                                        <input class="form-control" name="resource.bankBranch" value="${student.resource.bankBranch}" id="bankBranch">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">A/C No.</label>
				                                        <input class="form-control" name="resource.accountNumber" value="${student.resource.accountNumber}" id="accountNumber">
				                                    </div>
				                                    
				                                    <div class="form-group">
				                                        <label class="control-label">Medical Status </label>
				                                        <select class="form-control" id="medicalStatus" name="resource.medicalStatus">
				                                           	<option value="Fit" ${student.resource.medicalStatus eq 'Fit' ? 'selected=selected' : ''}>Fit</option>
															<option value="Unfit" ${student.resource.medicalStatus eq 'Unfit' ? 'selected=selected' : ''}>Unfit</option>
				                                        </select>
				                                    </div>
				                                    
				                                    <div class="form-group">
				                                        <label class="control-label">E-Mail</label>
				                                        <input placeholder="eg.: email@email.com" class="form-control" name="resource.emailId" value="${student.resource.emailId}" id="emailId">
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
				                                        <input type="text" class="form-control" name="resource.fatherFirstName" value="${student.resource.fatherFirstName}" id="fatherFirstName">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Middle Name</label>
				                                        <input type="text" class="form-control" name="resource.fatherMiddleName" value="${student.resource.fatherMiddleName}" id="fatherMiddleName">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Last Name</label>
				                                        <input type="text" class="form-control" name="resource.fatherLastName" value="${student.resource.fatherLastName}" id="fatherLastName">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">In Defence</label>
				                                        <select class="form-control" id="fatherInDefence" name="resource.fatherInDefence"> 
				                                            <option value="false" ${student.resource.fatherInDefence eq false ? 'selected=selected' : ''}>No</option>
															<option value="true" ${student.resource.fatherInDefence eq true ? 'selected=selected' : ''}>Yes</option>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Service Status</label>
				                                        <select class="form-control" id="fatherServiceStatus" name="resource.fatherServiceStatus">
				                                            <option value="" ${student.resource.fatherServiceStatus eq '' ? 'selected=selected' : ''}>Select</option>
															<option value="Ex-Defence" ${student.resource.fatherServiceStatus eq 'Ex-Defence' ? 'selected=selected' : ''}>Ex-Defence</option>
															<option value="Defence" ${student.resource.fatherServiceStatus eq 'Defence' ? 'selected=selected' : ''}>Defence</option>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Defence Category</label>
				                                        <select class="form-control" id="fatherDefenceCategory" name="resource.fatherDefenceCategory">
				                                            <option value="" ${student.resource.fatherDefenceCategory eq '' ? 'selected=selected' : ''}>Select</option>
															<option value="Air-Force" ${student.resource.fatherDefenceCategory eq 'Air-Force' ? 'selected=selected' : ''}>Air Force</option>
															<option value="Army" ${student.resource.fatherDefenceCategory eq 'Army' ? 'selected=selected' : ''}>Army</option>
															<option value="Navy" ${student.resource.fatherDefenceCategory eq 'Navy' ? 'selected=selected' : ''}>Navy</option>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Rank</label>
				                                        <input class="form-control" name="resource.fatherRank" value="${student.resource.fatherRank}" id="fatherRank">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Mobile</label>
				                                        <div class="input-group">
				                                            <span class="input-group-addon">
				                                                <i class="fa fa-mobile"></i>
				                                            </span>
				                                            <input class="form-control" placeholder="(91) 98300-98300" data-input-mask="(99) 99999-99999" data-plugin-masked-input="" name="resource.fatherMobile" value="${student.resource.fatherMobile}" id="fatherMobile">
				                                        </div>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">E-Mail</label>
				                                        <input placeholder="eg.: email@email.com" class="form-control" name="resource.fatherEmail" value="${student.resource.fatherEmail}" id="fatherEmail">
				                                    </div>
				                                </div>
				                                <div class="col-md-3">
				                                    <blockquote class="b-thin rounded primary">
				                                        <h3>Mother's Details</h3>
				                                    </blockquote>
				                                    <div class="form-group">
				                                        <label class="control-label">First Name</label>
				                                        <input type= "text" class="form-control" name="resource.motherFirstName" value="${student.resource.motherFirstName}" id="motherFirstName">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Middle Name</label>
				                                        <input type="text" class="form-control" name="resource.motherMiddleName" value="${student.resource.motherMiddleName}" id="motherMiddleName">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Last Name</label>
				                                        <input type="text" class="form-control" name="resource.motherLastName" value="${student.resource.motherLastName}" id="motherLastName">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Mobile</label>
				                                        <div class="input-group">
				                                            <span class="input-group-addon">
				                                                <i class="fa fa-mobile"></i>
				                                            </span>
				                                            <input class="form-control" placeholder="(91) 98300-98300" data-input-mask="(99) 99999-99999" data-plugin-masked-input="" name="resource.motherMobile" value="${student.resource.motherMobile}" id="motherMobile">
				                                        </div>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">E-Mail</label>
				                                        <input placeholder="eg.: email@email.com" class="form-control" name="resource.motherEmail" value="${student.resource.motherEmail}" id="motherEmail">
				                                    </div>
				                                </div>
				                                <div class="col-md-3">
				                                    <blockquote class="b-thin rounded primary">
				                                        <h3>Guardian's Details</h3>
				                                    </blockquote>
				                                    <div class="form-group">
				                                        <label class="control-label">First Name</label>
				                                        <input type="text" class="form-control" name="guardianFirstName" value="${student.guardianFirstName}" id="guardianFirstName">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Middle Name</label>
				                                        <input type="text" class="form-control" name="guardianMiddleName" value="${student.guardianMiddleName}" id="guardianMiddleName">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Last Name</label>
				                                        <input type="text" class="form-control" name="guardianLastName" value="${student.guardianLastName}" id="guardianLastName">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Mobile</label>
				                                        <div class="input-group">
				                                            <span class="input-group-addon">
				                                                <i class="fa fa-mobile"></i>
				                                            </span>
				                                            <input class="form-control" placeholder="(91) 98300-98300" data-input-mask="(99) 99999-99999" data-plugin-masked-input="" name="guardianMobile" value="${student.guardianMobile}" id="guardianMobile">
				                                        </div>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">E-Mail</label>
				                                        <input type="email" placeholder="eg.: email@email.com" class="form-control" name="guardianEmail" value="${student.guardianEmail}" id="guardianEmail">
				                                    </div>
				                                </div>
				                                <div class="col-md-3">
				                                    <blockquote class="b-thin rounded primary">
				                                        <h3>Income Details</h3>
				                                    </blockquote>
				                                    <div class="form-group">
				                                        <label class="control-label">Father's Income</label>
				                                        <input class="form-control" name="fatherIncome" value="${student.fatherIncome}" id="fatherIncome">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Mother's Income</label>
				                                        <input class="form-control" name="motherIncome" value="${student.motherIncome}" id="motherIncome">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Student's Income</label>
				                                        <input class="form-control" name="studentIncome" value="${student.studentIncome}" id="studentIncome">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Family Income</label>
				                                        <input class="form-control" name="familyIncome" value="${student.familyIncome}" id="familyIncome">
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
				                                        <input type="text" class="form-control" id="presentAddressLine" name="address.presentAddressLine" value="${student.address.presentAddressLine}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Landmark</label>
				                                        <input type="text" class="form-control" id="presentAddressLandmark" name="address.presentAddressLandmark" value="${student.address.presentAddressLandmark}" >
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">City/Village</label>
				                                        <input type="text" class="form-control" id="presentAddressCityVillage" name="address.presentAddressCityVillage" value="${student.address.presentAddressCityVillage}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Pin code</label>
				                                        <input type="text" class="form-control" id="presentAddressPinCode" name="address.presentAddressPinCode" value="${student.address.presentAddressPinCode}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">District</label>
				                                        <input type="text" class="form-control" id="presentAddressDistrict" name="address.presentAddressDistrict" value="${student.address.presentAddressDistrict}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">State</label>
				                                        <select class="form-control" name="address.presentAddressState" id="presentAddressState">
				                                            <c:forEach var="state" items="${stateList}" >
																<option value="${state.stateCode}" ${student.address.presentAddressState eq state.stateCode ? 'selected=selected' : ''}>${state.stateName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Country</label>
				                                        <select class="form-control" id="presentAddressCountry" name="address.presentAddressCountry">
				                                            <c:forEach var="country" items="${countryList}" >
																<option value="${country.countryCode}" ${student.address.presentAddressCountry eq country.countryCode ? 'selected=selected' : ''}>${country.countryName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Post Office</label>
				                                        <input type="text" class="form-control" id="presentAddressPostOffice" name="address.presentAddressPostOffice" value="${student.address.presentAddressPostOffice}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Police Station</label>
				                                        <input type="text" class="form-control" id="presentAddressPoliceStation" name="address.presentAddressPoliceStation" value="${student.address.presentAddressPoliceStation}">
				                                    </div>
				                                </div>
				                                <!-- <div class="col-md-4" style="margin-top: 130px;">
				                                    <div class="checkbox-custom checkbox-default">
				                                        <input type="checkbox" onchange="copyAddress(this);">
				                                        <label for="checkboxExample1">Same as Present Address</label>
				                                    </div>
				                                </div> -->
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
				                                        <input type="text" class="form-control" id="permanentAddressLine" name="address.permanentAddressLine" value="${student.address.permanentAddressLine}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Landmark</label>
				                                        <input type="text" class="form-control" id="permanentAddressLandmark" name="address.permanentAddressLandmark" value="${student.address.permanentAddressLandmark}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">City/Village</label>
				                                        <input type="text" class="form-control" id="permanentAddressCityVillage" name="address.permanentAddressCityVillage" value="${student.address.permanentAddressCityVillage}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Pin code</label>
				                                        <input type="text" class="form-control" id="permanentAddressPinCode" name="address.permanentAddressPinCode" value="${student.address.permanentAddressPinCode}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">District</label>
				                                        <input type="text" class="form-control" id="permanentAddressDistrict" name="address.permanentAddressDistrict" value="${student.address.permanentAddressDistrict}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">State</label>
				                                        <select class="form-control" name="address.permanentAddressState" id="permanentAddressState">
				                                            <option value="">Please Select</option>
				                                            <c:forEach var="state" items="${stateList}" >
																<option value="${state.stateCode}" ${student.address.permanentAddressState eq state.stateCode ? 'selected=selected' : ''}>${state.stateName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Country</label>
				                                        <select class="form-control" id="permanentAddressCountry" name="address.permanentAddressCountry">
				                                            <c:forEach var="country" items="${countryList}" >							
																<option value="${country.countryCode}" ${student.address.permanentAddressCountry eq country.countryCode ? 'selected=selected' : ''}>${country.countryName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Post Office</label>
				                                        <input type="text" class="form-control" id="permanentAddressPostOffice" name="address.permanentAddressPostOffice" value="${student.address.permanentAddressPostOffice}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Police Station</label>
				                                        <input type="text" class="form-control" id="permanentAddressPoliceStation" name="address.permanentAddressPoliceStation" value="${student.address.permanentAddressPoliceStation}">
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
				                                        <input type="text" class="form-control" id="guardianAddressLine" name="address.guardianAddressLine" value="${student.address.guardianAddressLine}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Landmark</label>
				                                        <input type="text" class="form-control" id="guardianAddressLandmark" name="address.guardianAddressLandmark" value="${student.address.guardianAddressLandmark}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">City/Village</label>
				                                        <input type="text" class="form-control" id="guardianAddressCityVillage" name="address.guardianAddressCityVillage" value="${student.address.guardianAddressCityVillage}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Pin code</label>
				                                        <input type="text" class="form-control" id="guardianAddressPinCode" name="address.guardianAddressPinCode" value="${student.address.guardianAddressPinCode}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">District</label>
				                                        <input type="text" class="form-control" id="guardianAddressDistrict" name="address.guardianAddressDistrict" value="${student.address.guardianAddressDistrict}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">State</label>
				                                        <select class="form-control" name="address.guardianAddressState" id="guardianAddressState">
				                                            <option value="">Please select</option>
				                                            <c:forEach var="state" items="${stateList}" >
																<option value="${state.stateCode}"  ${student.address.guardianAddressState eq state.stateCode ? 'selected=selected' : ''}>${state.stateName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Country</label>
				                                        <select class="form-control" id="guardianAddressCountry" name="address.guardianAddressCountry">
				                                            <option value="">Please select</option>
				                                            <c:forEach var="country" items="${countryList}" >
																<option value="${country.countryCode}" ${student.address.guardianAddressCountry eq country.countryCode ? 'selected=selected' : ''}>${country.countryName}</option>
															</c:forEach>
				                                        </select>
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Post Office</label>
				                                        <input type="text" class="form-control" id="guardianAddressPostOffice" name="address.guardianAddressPostOffice" value="${student.address.guardianAddressPostOffice}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Police Station</label>
				                                        <input type="text" class="form-control" id="guardianAddressPoliceStation" name="address.guardianAddressPoliceStation" value="${student.address.guardianAddressPoliceStation}">
				                                    </div>
				                                </div>
				                            </div>
				                        </div>
				                        <div id="w4-Educational" class="tab-pane">
				                            <div class="row" >
				                                <div class="col-md-12">
				                                    <blockquote class="b-thin rounded primary">
				                                        <h3>Previous Educational Details</h3>
				                                    </blockquote>
				                                </div>    
				                                <div class="col-md-6" id="studentPreviousEducationalDetails">
				                                    <div class="form-group">
				                                        <label class="control-label">School Name</label>
				                                        <input type="text" class="form-control" id="previousSchoolName" name="previousSchoolName" value="${student.previousSchoolName}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Website</label>
				                                        <input type="text" class="form-control" id="previousSchoolWebsite" name="previousSchoolWebsite" value="${student.previousSchoolWebsite}">
				                                    </div>
				                                    <div class="form-group">
				                                        <label class="control-label">Address</label>
				                                        <input type="text" class="form-control" id="previousSchoolAddress" name="previousSchoolAddress" value = "${student.previousSchoolAddress}">
				                                    </div>
				                                    <div class="form-group">
									                 <label class="control-label">Phone</label>
				                                        <div class="input-group">
				                                            <span class="input-group-addon">
				                                                <i class="fa fa-mobile"></i>
				                                            </span>
				                                            <input id="previousSchoolPhone" name="previousSchoolPhone" value="${student.previousSchoolPhone}"  data-plugin-masked-input="" data-input-mask="(99) 99999-99999" placeholder="(91) 98300-98300" class="form-control valid">
				                                        </div>
				                                    </div>
				                                    <div class="form-group">
										            <label class="control-label">E-Mail</label>
				                                        <input type= "text" placeholder="eg.: email@email.com" class="form-control" aria-invalid="true" id="previousSchoolEmail" name="previousSchoolEmail" value="${student.previousSchoolEmail}">
				                                    </div>
				                                </div>
				                                <div class="col-md-6">
					                                <div class="form-group">
					                                <label class="control-label">Download Attachments</label>
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
																				<a class="alnk" onClick="window.open('deleteStudentAttachmentFromHardDrive.html?fileLocation=${attachment.storageRootPath}&fileName=${attachment.attachmentName}&fileId=${attachment.storageObjectId}&roll=${student.userId}','_self')"><img src="images/minus_icon.png" ></a>
																			</td>
																		</c:if>
																	</c:forEach>
																</c:if>
															</c:otherwise>
														</c:choose> 	
													</div>
				                                    <div class="form-group">
				     				                    <label class="control-label">Upload Documents</label>
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
																	 <img id="preview" src="data:image/jpg;base64, ${student.resource.image.imageName}" style="width:200px;height:200px;"/>
																</div>
																<span class="btn btn-default btn-file"> <span class="fileupload-exists">Change</span> 
																<span class="fileupload-new">Select file</span> 
																<input type="file" name="resource.image.imageData" id="image_upload" onchange="fileSelected();"/>
																<!-- <input type="file" name="resource.image.imageData" id="image_upload" onchange="fileSelected();" disabled="disabled"/> -->
																</span> <a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">Remove</a>
															</div>
														</div>
													</div>
												</div>
												<div class="col-md-12">
													<button id="submit" class="btn btn-primary pull-right" type="submit" name="submit">Submit </button>
			                                    </div>
				                            </div>    
				                        </div>
				                    </div>      
				            </div>
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
				            <div class="warningbox" id="warningbox" >
								<span id="warningmsg"></span>	
							</div>
							</form:form>
				        </section>
					</div>
					
			</div>
			


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/backoffice/editStudent.js"></script>
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
</script>
</body>
</html>