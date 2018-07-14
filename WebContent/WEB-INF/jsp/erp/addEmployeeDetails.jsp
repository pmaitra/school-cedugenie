<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Employee Details Form</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Enter Employee Details</h2>
</header>
<div class="content-padding">
	<c:if test="${submitResponse eq 'Success'}">
		<div class="alert alert-success">
			<strong>Employee is Successfully Created!!!</strong>
		</div>
	</c:if>
	<c:if test="${submitResponse eq 'Fail'}">
		<div class="alert alert-danger">
			<strong>Employee is Not Created!!!</strong>
		</div>
	</c:if>
	<c:if test="${null ne userIdRegisterStatus}">
		<div class="alert alert-info">
			<strong>${userIdRegisterStatus}</strong>
		</div>
	</c:if>
	<div class="row">						
		<div class="col-xs-12">
			<section class="panel form-wizard" id="w4">
			<form name="employeeDetailsForm" id="employeeDetailsForm"  enctype="multipart/form-data" action="submitEmployeeDetails.html" method="POST">
				<header class="panel-heading">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
					</div>	
					<h2 class="panel-title">Employee Details Form</h2>
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
								<a href="#w4-Emplyoee" data-toggle="tab"><span>2</span>Employee <br> Details</a>
							</li>
							<li>
								<a href="#w4-Bank" data-toggle="tab"><span>3</span>Bank <br> Details</a>
							</li>
							<li>
								<a href="#w4-Work" data-toggle="tab"><span>4</span>Working <br> Details</a>
							</li>
	                                          <li>
								<a href="#w4-Upload" data-toggle="tab"><span>5</span>Upload <br> Image</a>
							</li>
						</ul>
					</div>					
					<div class="tab-content">
						<div id="w4-Personal" class="tab-pane active">
                        	<div class="row">
                           		<div class="col-md-12">
                                   <blockquote class="b-thin rounded primary">
                                   		<h3>Employee's Personal Details</h3>
                                   </blockquote>
                                </div>
                                <div class="col-md-3">                                                            
	                                <div class="form-group">
	                                    <label class="control-label">First Name</label>
	                                    <span class="required" aria-required="true">*</span>
	                                    <input type="text" class="form-control" id="firstName" name="resource.firstName" value="${employee.resource.firstName}" placeholder="First Name" required>
	                                </div>
	                                <div class="form-group">
	                                    <label class="control-label">Middle Name</label>
	                                    <input type="text" class="form-control" id="middleName" name="resource.middleName" value="${employee.resource.middleName}" placeholder="Middle Name">
	                                </div>
                                    <div class="form-group">
                                        <label class="control-label">Last Name</label>
                                        <input type="text" class="form-control" id="lastName" name="resource.lastName" value="${employee.resource.lastName}" placeholder="Last Name">
                                    </div>
									<!-- new change -->
                                    <div class="form-group">
                                        <label class="control-label">Initial Name</label>
                                        <span class="required" aria-required="true">*</span>
                                        <input type="text" class="form-control" id="initialName" name="resource.initialName" placeholder="Initial Name" required>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Contact Number</label>
                                        <span class="required" aria-required="true">*</span>
                                        <div class="input-group">
                                            <span class="input-group-addon">
                                                <i class="fa fa-mobile"></i>
                                            </span>
                                            <input type="text" class="form-control" value="076622 57109" name="resource.mobile" id="mobile" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">
	                                <div class="form-group">
	                                    <label class="control-label">Medical Attention</label>
	                                    <select class="form-control" name="resource.medicalStatus" id="medicalStatus">
	                                    	<option value="FIT">FIT</option>
											<option value="UNFIT">UNFIT</option>
	                                    </select>
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
	                                	<label class="control-label">Category</label>
	                                	<span class="required" aria-required="true">*</span>
	                                    	<select class="form-control" id="category" name="resource.category" required="required">
	                                        	<option value="">Please select</option>						
	                                        	<c:forEach var="socialCategory" items="${employee.socialCategoryList}" >
													<option value="${socialCategory.socialCategoryCode}">${socialCategory.socialCategoryName}</option>
												</c:forEach>
	                                      </select>
	                                </div>
                                  <div class="form-group">
                                      <label class="control-label">Voting Constituency</label>
                                      <input type="text" class="form-control" id="votingConstituency" name="resource.votingConstituency">
                                  </div>
                                  	<div class="form-group">
                                        <label class="control-label">E-Mail</label>
                                        <input type="email" class="form-control" id="emailId" name="resource.emailId" aria-invalid="true">
                                    </div>
                                     
                              </div>
                              <div class="col-md-3">
                                  <div class="form-group">
                                      <label class="control-label">Religion</label>
                                      <input type="text" class="form-control" id="religion" name="resource.religion">
                                  </div>
                                  <div class="form-group">
                                      <label class="control-label">Nationality</label>
                                      <span class="required" aria-required="true">*</span>
                                      <input type="text" class="form-control" id="nationality" name="resource.nationality" required>
                                  </div>
                                  <div class="form-group">
                                      <label class="control-label">Mother Tongue</label>
                                      <input type="text" class="form-control" id="motherTongue" name="resource.motherTongue">
                                  </div>
                                  <div class="form-group">
                                      <label class="control-label">Parliamentary Constituency</label>
                                      <input type="text" class="form-control" id="parliamentaryConstituency" name="resource.parliamentaryConstituency" >
                                  </div>
                                  <div class="form-group">
                                      <label class="control-label">Gender</label>
                                      <span class="required" aria-required="true">*</span>
                                      <div class="form-group" style="margin-top: 5px;">
                                      <label class="radio-inline radio-primary"  id="gender"> 
                                          <input required type="radio" name="resource.gender" id="male" value="M" <c:if test="${employee.resource.gender eq 'M'}">checked</c:if>> Male 
                                      </label>
                                      <label class="radio-inline radio-primary"  id="gender"> 
                                          <input type="radio" name="resource.gender" id="female" value="F" <c:if test="${employee.resource.gender eq 'F'}">checked</c:if>> Female 
                                      </label>
                                      </div>	                                                          
                                  </div>
                              </div>
                              <div class="col-md-3">
                                  <div class="form-group">
                                      <label class="control-label">Passport No</label>
                                      <input type="text" class="form-control" id="passportNo" name="resource.passportNo">
                                  </div>
                                  <div class="form-group">
                                      <label class="control-label">Pan Card No</label>
                                      <input type="text" class="form-control" id="panCardNo" name="resource.panCardNo">
                                  </div>
                                  <div class="form-group">
                                      <label class="control-label">Aadhaar Card No</label>
                                      <input type="text" class="form-control" id="aadharCardNo" name="resource.aadharCardNo">
                                  </div>
                                  <div class="form-group">
                                      <label class="control-label">Voter Card No</label>
                                      <input type="text" class="form-control" id="voterCardNo" name="resource.voterCardNo">
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
                                      <input type="text" class="form-control" id="fatherFirstName" name="resource.fatherFirstName" placeholder="First Name">
                                  </div>
                                  <div class="form-group">
                                      <label class="control-label">Middle Name</label>
                                      <input type="text" class="form-control" id="fatherMiddleName" name="resource.fatherMiddleName" placeholder="Middle Name" >
                                  </div>
                                  <div class="form-group">
                                      <label class="control-label">Last Name</label>
                                      <input type="text" class="form-control" id="fatherLastName" name="resource.fatherLastName" placeholder="Last Name"/>
                                  </div>	                                   
                              </div>
                              <div class="col-md-4">
                                  <blockquote class="b-thin rounded primary">
                                      <h3>Mother's Details</h3>
                                  </blockquote>
                                  <div class="form-group">
                                      <label class="control-label">First Name</label>
                                      <input type="text" class="form-control" id="motherFirstName" name="resource.motherFirstName" placeholder="First Name"/>
                                  </div>
                                  <div class="form-group">
                                      <label class="control-label">Middle Name</label>
                                      <input type="text" class="form-control" id="motherMiddleName" name="resource.motherMiddleName" placeholder="Middle Name">
                                  </div>
                                  <div class="form-group">
                                      <label class="control-label">Last Name</label>
                                      <input type="text" class="form-control" id="motherLastName" name="resource.motherLastName" placeholder="last Name"/>
                                  </div>	                                                      
                              </div>
                              <div class="col-md-4">
                                  <blockquote class="b-thin rounded primary">
                                      <h3>Marital Status</h3>
                                  </blockquote>
                                  <div class="form-group">
                                      <label class="control-label">Marital Status</label>
                                      <select class="form-control" name="maritalStatus" id="maritalStatus">
                                      		<option value="MARRIED">MARRIED</option>
											<option value="UNMARRIED">UNMARRIED</option>
                                      </select>
                                  </div>
                                  <div class="form-group">
                                      <label class="control-label">Spouse's Name</label>
                                      <input type="text" class="form-control" id="spouseName" name="spouseName" placeholder="Spouse's Name">
                                  </div> 
                              </div>
						</div>							
                        <div class="row">   
                            <div class="col-md-12">&nbsp;</div>
                            <div class="col-md-12">
                                <blockquote class="b-thin rounded primary">
                                    <h3>Schooling Details</h3>
                                </blockquote>
                                <table class="table table-bordered mb-none dataTable no-footer">
                                    <thead>
                                        <tr>
                                            <th>Exam Name/Degree</th>
                                            <th>Specialization</th>
                                            <th>School/College</th>
                                            <th>Board/University</th>
                                            <th>Marks(%)</th>
                                            <th>Passing Year</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[0].examName" id="examName0" value="10th" readonly="readonly">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[0].specialization" id="specialization0" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[0].schoolCollege" id="schoolCollege0" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[0].boardUniversity" id="boardUniversity0" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[0].marks" id="marks0">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[0].passingYear" id="passingYear0">
                                                <input type="hidden" class="textfield1" name="qualificationList[0].qualificationType" id="qualificationType0" value="MADHYAMIK">
                                            </div>
                                        </td>    
                                        </tr>
                                        <tr>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[1].examName" id="examName1" readonly="readonly" value="12th">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[1].specialization" id="specialization1" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[1].schoolCollege" id="schoolCollege1" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[1].boardUniversity" id="boardUniversity1" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[1].marks" id="marks1" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[1].passingYear" id="passingYear1" >
                                                <input type="hidden" class="textfield1" name="qualificationList[1].qualificationType" id="qualificationType1" value="HS">
                                            </div>
                                        </td>    
                                        </tr>
                                    </tbody>
                                </table>   
                            </div>                                                        
                        </div>
                        <div class="row"> 
                            <div class="col-md-12">&nbsp;</div>
                            <div class="col-md-12">
                                <blockquote class="b-thin rounded primary">
                                    <h3>Graduation Details</h3>
                                </blockquote>
                                <table class="table table-bordered mb-none dataTable no-footer">
                                    <thead>
                                        <tr>
                                            <th>Exam Name/Degree</th>
                                            <th>Specialization</th>
                                            <th>School/College</th>
                                            <th>Board/University</th>
                                            <th>Marks(%)</th>
                                            <th>Passing Year</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[2].examName" id="examName2" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[2].specialization" id="specialization2" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[2].schoolCollege" id="schoolCollege2" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[2].boardUniversity" id="boardUniversity2" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[2].marks" id="marks2" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[2].passingYear" id="passingYear2" >
                                                <input type="hidden" class="textfield1" name="qualificationList[2].qualificationType" id="qualificationType2" value="GRADUATION">
                                            </div>
                                        </td>    
                                        </tr>                                        
                                    </tbody>
                                </table>   
                            </div>                                                        
                        </div>
                        <div class="row"> 
                            <div class="col-md-12">&nbsp;</div>
                            <div class="col-md-12">
                                <blockquote class="b-thin rounded primary">
                                    <h3>Post Graduation Details</h3>
                                </blockquote>
                                <table class="table table-bordered mb-none dataTable no-footer">
                                    <thead>
                                        <tr>
                                            <th>Exam Name/Degree</th>
                                            <th>Specialization</th>
                                            <th>School/College</th>
                                            <th>Board/University</th>
                                            <th>Marks(%)</th>
                                            <th>Passing Year</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[3].examName" id="examName3" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[3].specialization" id="specialization3" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[3].schoolCollege" id="schoolCollege3" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[3].boardUniversity" id="boardUniversity3" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[3].marks" id="marks3" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="qualificationList[3].passingYear" id="passingYear3" >
                                            	<input type="hidden" class="textfield1" name="qualificationList[3].qualificationType" id="qualificationType3" value="POSTGRADUATION">
                                            </div>
                                        </td>    
                                        </tr>
                                        
                                    </tbody>
                                </table>   
                            </div>                                                        
                        </div>
                        <div class="row"> 
                            <div class="col-md-12">&nbsp;</div>
                            <div class="col-md-12">
                                <blockquote class="b-thin rounded primary">
                                    <h3>Other Qualification Details</h3>
                                </blockquote>
                                <table class="table table-bordered mb-none dataTable no-footer">
                                    <thead>
                                        <tr>
                                            <th>Exam Name/Degree</th>
                                            <th>Specialization</th>
                                            <th>School/College</th>
                                            <th>Board/University</th>
                                            <th>Marks(%)</th>
                                            <th>Passing Year</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="othersExamName" id="othersExamName" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="othersSpecialization" id="othersSpecialization" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="othersSchoolCollege" id="othersSchoolCollege" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="othersBoardUniversity" id="othersBoardUniversity" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="othersMarks" id="othersMarks" >
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group">
                                                <input type="text" class="form-control" name="othersPassingYear" id="othersPassingYear" >
                                            	<input type="hidden" class="textfield1" name="othersQualificationType" id="othersQualificationType" value="OTHERS">
                                            </div>
                                        </td>    
                                        </tr>
                                        
                                    </tbody>
                                </table>
                                <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button" onclick="addOtherQualificationDetails();">Add more</button>
                            </div>                                                        
                        </div>
                        <div class="row">
                            <div class="col-md-12">&nbsp;</div>
                            <div class="col-md-5">
                                <blockquote class="b-thin rounded primary">
                                    <h3>Correspondence Address</h3>
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
                            <div class="col-md-2" style="margin-top: 130px;">
                           		<div class="checkbox-custom checkbox-default">
					 			<input type="checkbox" id="checkboxExample1" onchange="copyAddress(this);">
								 <label for="checkboxExample1">Same as Present Address</label>
								</div>
                            </div>
                            <div class="col-md-5">
                                <blockquote class="b-thin rounded primary">
                                    <h3>Permanent Address</h3>
                                </blockquote>
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
                   </div>
				</div>
				<div id="w4-Emplyoee" class="tab-pane">
					<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">
						<div class="col-md-12">
							<blockquote class="b-thin rounded primary">
							<h3>Employee's Official Details</h3>
							</blockquote>
						</div>								
						<div class="col-md-4">                                                            
                      		<div class="form-group">
                          		<label class="control-label">User Id</label>
                          		<span class="required" aria-required="true">*</span>
                          		<input type="text" class="form-control" name="resource.userId" id="userId" placeholder="User Id" required>
                      		</div>
                  		 	<div class="alert alert-danger" id="warningbox" style = "display:none">
							</div>                      
		                    <div class="form-group">
		                    	<label class="control-label">Employee Code</label>
		                        <input type="text" class="form-control" name="employeeCode" id="employeeCode" placeholder="Employee Code">
	                     	</div> 
                      		<div class="form-group">
	                          	<label class="control-label">Date Of Birth</label>
	                          	<span class="required" aria-required="true">*</span>
	                          	<div class="input-group">
                              		<span class="input-group-addon">
                                  		<i class="fa fa-calendar"></i>
                             	 	</span>
                            	 	<input type="text" class="form-control" placeholder="01/01/1970" data-plugin-datepicker="" data-date-end-date="0d" id="dateOfBirth" name="resource.dateOfBirth" value="${employee.resource.dateOfBirth}" required>
	                    		</div>
              				</div>
              				<div class="form-group">
                       	  		<label class="control-label">Date Of Interview</label>
                        	  	<input type="text" class="form-control" data-plugin-datepicker="" name="dateOfInterview" id="dateOfInterview" value="${employee.resource.dateOfInterview}" readonly="readonly">
                         	</div>
                     	 	<div class="form-group">
                       	  		<label class="control-label">Date Of Join</label>
                        	  	<span class="required" aria-required="true">*</span>
                        	  	<div class="input-group">
                       	      		<span class="input-group-addon">
                       	          		<i class="fa fa-calendar"></i>
                       	      		</span>
                        	      	<input type="text" class="form-control" data-plugin-datepicker="" data-date-end-date="0d" name="dateOfJoining" id="dateOfJoining" required>
                         	 	</div>
                     		</div>
               			</div>	
	                   	<div class="col-md-4">
	                         <div class="form-group">
	                             <label class="control-label">Date Of Retirement</label>
	                             <div class="input-group">
	                                 <span class="input-group-addon">
	                                     <i class="fa fa-calendar"></i>
	                                 </span>
	                                 <input class="form-control" data-plugin-datepicker="" name="dateOfRetirement" id="dateOfRetirement" readonly>
	                             </div>
	                         </div>
	                         <div class="form-group">
	                             <label class="control-label">Employee Type</label>
	                             <span class="required" aria-required="true">*</span>
	                             <select class="form-control" id="employeeTypeName" name="employeeType.employeeTypeName" required>
	                                 <option value="">Please select</option>
	                                 <c:forEach var="resourceType" items="${employee.resourceTypeList}" >
										<option VALUE="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
									</c:forEach>
	                             </select>
	                         </div>
	                         <div class="form-group">
	                             <label class="control-label">Job Type</label>
	                             <span class="required" aria-required="true">*</span>
	                             <select class="form-control" id="jobTypeName" name="jobType.jobTypeName" required>
	                                 <option value="">Please select</option>
	                                 <c:forEach var="jobType" items="${employee.jobTypeList}" >
										<option VALUE="${jobType.jobTypeCode}">${jobType.jobTypeName}</option>
									</c:forEach>	
	                             </select>
	                         </div>
	                         <div class="form-group">
	                             <label class="control-label">Designation</label>
	                             <span class="required" aria-required="true">*</span>
	                             <select class="form-control" id="designationName" name="designation.designationName" required>
	                                  <option value="">Please select</option>
	                                  <c:forEach var="designation" items="${employee.designationList}" >
										<option VALUE="${designation.designationCode}">${designation.designationName}</option>
										</c:forEach>
	                              </select>
	                          </div>
	                          <div class="form-group">
	                          	<label class="control-label">Reporting Manager</label>
	                          	<input type="text" class="form-control" name="reportingManger" id="reportingManger" placeholder="" >
	                          	
	                          </div>
	                         
	                  		</div>	
	                  		 <div class="col-md-4">
	                          <div class="form-group">
	                              <label class="control-label">Department</label>
	                              <span class="required" aria-required="true">*</span>
	                              <select class="form-control" id="department" name="department.departmentName" required>
	                                  <option value="">Please select</option>	
	                                  <c:forEach var="department" items="${employee.departmentList}" >
									 <option value="${department.departmentCode}">${department.departmentName}</option>
									</c:forEach>
	                              </select>
	                          </div>
	                          <div class="form-group">
	                              <label class="control-label">Teaching Level</label>
	                              <select class="form-control" id="teachingLevel" name="teachingLevel.teachingLevelName">
	                              	<option value="">Please select</option>	
	                                <c:forEach var="teachingLevel" items="${employee.teachingLevelList}" >
			                             <option value="${teachingLevel.teachingLevelName}" ${teachingLevel.teachingLevelName eq employee.resource.objectId ? 'selected=selected' : ''}>${teachingLevel.teachingLevelName}</option>
			                        </c:forEach>
                                  </select>
	                          </div>
	                          <div class="form-group">
	                              <label class="control-label">Designation Level</label>
	                              <span class="required" aria-required="true">*</span>	                             
	                              <select id="designationLevel" name="designationLevel.designationLevelName" class="form-control" required>
										<option VALUE="" >Please Select</option>											
									</select>
	                          </div>
	                          <div class="form-group">
	                              <label class="control-label">Qualification Summary</label>
	                              <input type="text" class="form-control" id="qualificationSummary" name="qualificationSummary" value="${employee.resource.code}">
	                          </div>
	                          <div class="form-group">
	                           	<label class="control-label">Name</label>
	                          	<input type="text" class="form-control" id="reportingMangerName" name="reportingMangerName" placeholder="" readonly>
	                          </div>
	                      </div>
                         <div class="row">
                             <div class="col-md-12">
                                 <blockquote class="b-thin rounded primary">
                                     <h3>Employee's Children</h3>
                                 </blockquote>
                             </div>
                             <div class="col-md-12">
                             <table class="table table-bordered mb-none dataTable no-footer">
                                 <thead>
                                     <tr>
                                         <th>Name</th>
                                         <th>Date Of Birth</th>
                                         <th>Gender</th>
                                     </tr>
                                 </thead>
                                 <tbody>
                                     <tr>
                                     <td>
                                         <div class="form-group">
                                             <input type="text" class="form-control" id="childName" name="childName" >
                                         </div>
                                     </td>
                                     <td>
                                         <div class="form-group">
                                         	<input id="childDateOfBirth" name="childDateOfBirth" readonly="readonly" data-plugin-datepicker="" class="form-control">
                                         </div>
                                     </td> 
                                     <td>
                                       <div class="form-group">
                                           <select class="form-control" name="childGender" id="childGender">
                                           		<option value="">Please select</option>	
                                               	<option value="M">MALE</option>
				 			 					<option value="F">FEMALE</option>
                                           </select>
                                       </div>
                                     </td>                                                                       
                                     </tr>
                                 </tbody>
                             </table>
                             <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button" onclick="addChildRows();">Add more</button>
                    	</div>    
                     <div class="col-md-12">
                         <blockquote class="b-thin rounded primary">
                             <h3>Nominee Details</h3>
                         </blockquote>
                     </div>
                     <div class="col-md-12">
                     	<table class="table table-bordered mb-none dataTable no-footer">
                          <thead>
                              <tr>
                                  <th>Nominee Name</th>
                                  <th>Relationship</th>
                                  <th>Nominee( % )</th>
                              </tr>
                          </thead>
                          <tbody>
                              <tr>
                              <td>
                                  <div class="form-group">
                                      <input type="text" class="form-control" id="nomineeName" name="nomineeName" >
                                  </div>
                              </td>
                              <td>
                                  <div class="form-group">
                                      <input type="text" class="form-control" id="relationship" name="relationship" >
                                  </div>
                              </td>
                              <td>
                                  <div class="form-group">
                                      <input type="text" class="form-control" id="nomineePercent" name="nomineePercent" onblur="calculateHundred();" >
                                  </div>
                              </td>                                                                        
                              </tr>
                          </tbody>
                     	</table>
                     	<button class="mb-xs mt-xs mr-xs btn btn-primary" type="button" onclick="addNominee();">Add more</button>    
                    </div>    
                   </div>
                 </div>      
                </div>                 					
				<div id="w4-Bank" class="tab-pane">
               		<div class="row">
                   		<div class="col-md-12">
                        <blockquote class="b-thin rounded primary">
                            <h3>Employee's Bank Details</h3>
                        </blockquote>    
                    </div> 
                    	<div class="col-md-3"> 
                              <div class="form-group">
                                  <label class="control-label">Bank Name</label>
                                  <input type="text" class="form-control" id="bankName" name="bankName">
                              </div>
                              <div class="form-group">
                                  <label class="control-label">Account Type</label>
                                  <input type="text" class="form-control" id="accountType" name="accountType" >
                              </div>
                               <div class="form-group">
                                  <label class="control-label">Account Number</label>
                                  <input type="text" class="form-control" id="accountNumber" name="accountNumber" >
                              </div>
                          </div>
                          <div class="col-md-3">
                              <div class="form-group">
                                  <label class="control-label">Account Holder Name</label>
                                  <input type="text" class="form-control" id="accountHolderName" name="accountHolderName">
                              </div>
                              <div class="form-group">
                                  <label class="control-label">Branch</label>
                                  <input type="text" class="form-control" id="branchCode" name="branchCode">
                              </div>
                              <div class="form-group">
                                  <label class="control-label">Branch IFSC Code</label>
                                  <input type="text" class="form-control" id="branchIFSCCode" name="branchIFSCCode">
                              </div>                                                                                        
                          </div>                          
                   	</div>
				</div>
				<div id="w4-Work" class="tab-pane">
					<div class="row">                                                        
	                    <div class="col-md-12">
	                        <blockquote class="b-thin rounded primary">
	                            <h3>Previous Organization</h3>
	                        </blockquote>
	                        <table class="table table-bordered mb-none dataTable no-footer">
	                            <thead>
	                                <tr>
	                                    <th>Previous Organization Name</th>
	                                    <th>From Date</th>
	                                    <th>To Date</th>
	                                    <th>Contact</th>
	                                    <th>Website</th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                                <tr>
	                                <td>
	                                    <div class="form-group">
	                                        <input type="text" class="form-control" id="organizationName" name="organizationName" >
	                                    </div>
	                                </td>
	                                <td>
	                                    <div class="form-group">
	                                         <input type="text" id="" name="fromDate" data-plugin-datepicker="" class="form-control">
	                                    </div>
	                                </td>
	                                <td>
	                                    <div class="form-group">                                                                            
	                                            <input type="text" id="" name="toDate" data-plugin-datepicker="" class="form-control">
	                                    </div>
	                                </td>
	                                <td>
	                                    <div class="form-group">
	                                        <input type="text" class="form-control" id="organizationContactNo" name="organizationContactNo" >
	                                    </div>
	                                </td>
	                                <td>
	                                    <div class="form-group">
	                                        <input type="text" class="form-control" id="organizationWebSite" name="organizationWebSite" >
	                                    </div>
	                                </td>    
	                                </tr>
	                            </tbody>
	                        </table>
	                        <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button" id="staffPreviousWorkDetailsButton" onclick="addMoreExperience();">Add more Organization</button><br>
	                        <table class="table no-border">
	                            <tbody>
	                                <tr>
	                                    <td>
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
	                                                        <input type="file" name="resource.uploadFile.experienceRelatedFile"/>
	                                                    </span>
	                                                    <a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">Remove</a>
	                                                </div>
	                                            </div>
	                                        </div>
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>
	                                        <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button">Add more documents</button>
	                                    </td>
	                                </tr>
	                            </tbody>
	                        </table>	                        
	                    </div>
                        <div class="col-md-12">
                            <blockquote class="b-thin rounded primary">
                                <h3>Work Shop & Training</h3>
                            </blockquote>
                            <table class="table table-bordered mb-none dataTable no-footer">
                                <thead>
                                    <tr>
                                        <th>Subject</th>
                                        <th>Venue</th>
                                        <th>From Date</th>
                                        <th>To Date</th>
                                        <th>Organized By</th>
                                        <th>Duration</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                    <td>
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="subject" name="subject" placeholder="">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="venue" name="venue" placeholder="">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                                <input type="text" id="trainingFromDate0" name="trainingFromDate" onblur="calculateDateDifference(0);" data-plugin-datepicker="" class="form-control">  
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                                <input type="text" id="trainingToDate0" name="trainingToDate" onblur="calculateDateDifference(0);" data-plugin-datepicker="" class="form-control">   
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="organizedBy" name="organizedBy" placeholder="">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="duration0" name="duration" readonly="readonly" placeholder="">
                                        </div>
                                    </td>    
                                    </tr>
                                </tbody>
                            </table>
                            <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button" id="addMoreWorkShopButton" onclick="addMoreWorkShop();">Add more work details</button>
                        </div>
                        <div class="col-md-12">&nbsp;</div>
                        <div class="col-md-12">
                            <blockquote class="b-thin rounded primary">
                                <h3>Awards & Recognization</h3>
                            </blockquote>
                            <table class="table table-bordered mb-none dataTable no-footer">
                                <thead>
                                    <tr>
                                        <th>Award Name</th>
                                        <th>Presented By</th>
                                        <th>Presented On</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                    <td>
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="awardName" name="awardName" placeholder="">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="presentedBy" name="presentedBy" placeholder="">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                                <input type="text" id="presentedOn" name="presentedOn" data-plugin-masked-input="" data-input-mask="99/99/9999" placeholder="__/__/____" class="form-control">
                                        </div>
                                    </td>                                                                        
                                    </tr>
                                </tbody>
                            </table>
                            <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button" id="addMoreWorkShopButton">Add more Awards & Recognization Details</button>
                        </div>
                        <div class="col-md-12">&nbsp;</div>
                        <div class="col-md-12">
                            <blockquote class="b-thin rounded primary">
                                <h3>Publications Details</h3>
                            </blockquote>
                            <table class="table table-bordered mb-none dataTable no-footer">
                                <thead>
                                    <tr>
                                        <th>Title/Publication Name</th>
                                        
                                        <th>Co Publisher</th>
                                        <th>Date of Publication</th>
                                        <th>Publication Description</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                    <td>
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="publicationName" name="publicationName" placeholder="">
                                        </div>
                                    </td>
                                    
                                    <td>
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="coPublisher" name="coPublisher" placeholder="">
                                        </div>
                                    </td> 
                                    <td>
                                        <div class="form-group">
                                                <input id="dateOfPublication" name="dateOfPublication" data-plugin-datepicker="" class="form-control">
                                        </div>
                                    </td>
                                    <td>
                                         <div class="form-group">
                                            <textarea maxlength="140" data-plugin-maxlength="" rows="3" class="form-control" id="publicationDesc" name="publicationDesc"></textarea>
                                        </div>
                                    </td>    
                                    </tr>
                                </tbody>
                            </table>
                            <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button" id="addPublicationsDetailsButton" onclick="new_publish();">Add more Publications Details</button>
                            <table class="table no-border">
                                <tbody>
                                    <tr>
                                        <td>
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
                                                            <input type="file" name="resource.uploadFile.publicationRelatedFile"/>
                                                        </span>
                                                        <a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">Remove</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button">Add more documents</button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="col-md-12">
                            <blockquote class="b-thin rounded primary">
                                <h3>Confidential</h3>
                            </blockquote>
                            <div class="form-group">
                                <label for="textareaDefault" class="col-md-2 control-label">Note</label>
                                <div class="col-md-6">
                                    <textarea maxlength="240" data-plugin-maxlength="" rows="3" class="form-control" id="confidentialInformation" name="confidentialInformation"></textarea>
                                    <p>
                                        <code>max-length</code> set to 240.
                                    </p>
                                </div>
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
	                                    <label class="control-label">Staff's Image</label>
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
	                            	<button id="submitButton" class="btn btn-primary pull-right" type="submit">Submit </button>
	                            	<!-- onclick="return validateEmployeeForm();" -->
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
						<li class="finish hidden pull-right">
							
						</li>
						<li class="next">
						<!-- /*  added by ranita.sur for userId validation on 02082017*/ -->
							<a  onclick = "userIdValidation()" >Next <i class="fa fa-angle-right"></i></a>
						</li>
					</ul>
				</div>	
				</form>			
			</section>
			</div>
		</div>
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script src="/cedugenie/js/erp/validateEmployeeDetails.js"></script>
<script src="/cedugenie/js/common/upload.js"></script>
<script src="/cedugenie/js/erp/employeeDetails.js"></script>
<script src="/cedugenie/js/erp/employeeAddAndEdit.js"></script>
<script type="text/javascript">
$('.datepicker').datepicker({
    format: 'dd/mm/yyyy',
    endDate: '+0d',
    autoclose: true
});
</script>
</body>
</html>