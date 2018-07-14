<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Student Details Form</title>
<%@ include file="/include/include.jsp"%>

<style type="text/css">
.scroll-to-top {
	display: none !important;
}

.mb-md {
	display: none;
}
</style>
</head>
<body>
<header class="page-header">
		<h2>Edit Employee Details</h2>
</header>
	<div class="content-padding">
		<div class="row">
			<c:if test="${updateResponse eq 'Success'}">
				<div class="alert alert-success"  >
					<strong>${updateMessage}</strong>	
				</div>
			</c:if>
			<c:if test="${updateResponse eq 'Fail'}">
				<div class="alert alert-danger" >
					<strong>${updateMessage}</strong>	
				</div>
			</c:if>
			<div class="row">
				<div class="col-xs-12">
					<section class="panel form-wizard" id="w4">			
						<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle"
								data-panel-toggle></a>
						</div>
						<h2 class="panel-title">Employee Details Form</h2>
						</header>
						<div class="panel-body">
							<div class="wizard-progress wizard-progress-lg">
								<div class="steps-progress">
									<div class="progress-indicator"></div>
								</div>
								<ul class="wizard-steps">
									<li class="active"><a href="#w4-Personal" data-toggle="tab"><span>1</span>Personal
											<br> Details</a></li>
									<li><a href="#w4-Emplyoee" data-toggle="tab"><span>2</span>Employee
											<br> Details</a></li>
									<li><a href="#w4-Bank" data-toggle="tab"><span>3</span>Bank
											<br> Details</a></li>
									<li><a href="#w4-Work" data-toggle="tab"><span>4</span>Working
											<br> Details</a></li>
									<li><a href="#w4-Upload" data-toggle="tab"><span>5</span>Upload
											<br> Image</a></li>
								</ul>
							</div>
		
		
							<div class="tab-content">
								<div id="w4-Personal" class="tab-pane active">	
								<form:form name="updateEmployeePersonalDetails" id="updateEmployeePersonalDetails" action="updateEmployeePersonalDetails.html">							
									<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">							
										<div class="col-md-12">
											<blockquote class="b-thin rounded primary">
												<h3>Employee's Personal Details</h3>
											</blockquote>
										</div>
										
										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">First Name</label> 
												<input type="text" class="form-control" id="firstName" name="resource.firstName" value="${employeeDetails.resource.firstName}" readonly required>
											</div>
											<div class="form-group">
												<label class="control-label">Middle Name</label> 
												<input	type="text" class="form-control" id="middleName" name="resource.middleName" value="${employeeDetails.resource.middleName}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">Last Name</label> 
												<input type="text" class="form-control" id="lastName" name="resource.lastName" value="${employeeDetails.resource.lastName}" readonly required>
											</div>
											<!-- new change done -->
											<div class="form-group">
												<label class="control-label">Initial Name</label> 
												<input type="text" class="form-control" id="initialName" name="resource.initialName" value="${employeeDetails.resource.initialName}" readonly required>
											</div>
											<div class="form-group">
												<label class="control-label">Contact Number</label>
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="fa fa-mobile"></i>
													</span> <input class="form-control" placeholder="(91) 98300-98300" data-input-mask="(99) 99999-99999" data-plugin-masked-input="" name="resource.mobile" id="mobile" value="${employeeDetails.resource.mobile}" readonly required>
												</div>
											</div>
											
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">Medical Attention</label> 
												<select	class="form-control" name="resource.medicalStatus" id="medicalStatus" disabled>
													<c:if test="${employeeDetails.resource.medicalStatus eq 'FIT'}">
														<option value="FIT">FIT</option>
														<option value="UNFIT">UNFIT</option>
													</c:if>					
													<c:if test="${employeeDetails.resource.medicalStatus eq 'UNFIT'}">
														<option value="UNFIT">UNFIT</option>
														<option value="FIT">FIT</option>
													</c:if>	
												</select>
											</div>
											<div class="form-group">
												<label class="control-label">Blood Group</label> 
												<select class="form-control" id="bloodGroup" name="resource.bloodGroup" disabled required>
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
											</div>
											<div class="form-group">
												<label class="control-label">Category</label> 
												<select class="form-control" id="category" name="resource.category" disabled required>
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
											</div>
											<div class="form-group">
												<label class="control-label">Voting Constituency</label> 
												<input type="text" class="form-control" id="votingConstituency" name="resource.votingConstituency" value="${employeeDetails.resource.votingConstituency}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">E-Mail</label> 
												<input type="email" placeholder="eg.: email@email.com" class="form-control" id="emailId" name="resource.emailId" aria-invalid="true" value="${employeeDetails.resource.emailId}" readonly required>
											</div>
											
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">Religion</label> 
												<input type="text" class="form-control" id="religion" name="resource.religion" value="${employeeDetails.resource.religion}" readonly required>
											</div>
											<div class="form-group">
												<label class="control-label">Nationality</label> 
												<input type="text" class="form-control" id="nationality" name="resource.nationality" value="${employeeDetails.resource.nationality}" readonly required>
											</div>
											<div class="form-group">
												<label class="control-label">Mother Tongue</label> 
												<input type="text" class="form-control" id="motherTongue" name="resource.motherTongue" value="${employeeDetails.resource.motherTongue}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">Parliamentary
													Constituency</label> <input type="text" class="form-control" id="parliamentaryConstituency" name="resource.parliamentaryConstituency" value="${employeeDetails.resource.parliamentaryConstituency}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">Gender</label>
												<div class="form-group" style="margin-top: 5px;">
												<c:choose>
														<c:when test="${staffDetailsFromDB.resource.gender eq 'M'}">
															<label class="radio-inline radio-primary" name="resource.gender" id="gender"> 
		                                                        <input type="radio" name="gender_is" id="male" value="M" checked disabled> Male 
		                                                    </label>
		                                                    <label class="radio-inline radio-primary" name="resource.gender" id="gender"> 
		                                                        <input type="radio" name="gender_is" id="female" value="F" disabled> Female 
		                                                    </label>
														</c:when>
													<c:otherwise>												
															<label class="radio-inline radio-primary" name="resource.gender" id="gender"> 
		                                                        <input type="radio" name="gender_is" id="male" value="M" disabled> Male 
		                                                    </label>
		                                                    <label class="radio-inline radio-primary" name="resource.gender" id="gender"> 
		                                                        <input type="radio" name="gender_is" id="female" value="F" checked disabled> Female 
		                                                    </label>											
													</c:otherwise>
												</c:choose>
												
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">Passport No</label> 
												<input type="text" class="form-control" id="passportNo" name="resource.passportNo" value="${employeeDetails.resource.passportNo}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">Pan Card No</label> 
												<input type="text" class="form-control" id="panCardNo" name="resource.panCardNo" value="${employeeDetails.resource.panCardNo}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">Aadhar Card No</label> 
												<input type="text" class="form-control" id="aadharCardNo" name="resource.aadharCardNo" value="${employeeDetails.resource.aadharCardNo}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">Voter Card No</label> 
												<input type="text" class="form-control" id="voterCardNo" name="resource.voterCardNo" value="${employeeDetails.resource.voterCardNo}" readonly>
											</div>
										</div>							
										<div class="col-md-12">&nbsp;</div>
									</div>
									
									<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">
																
										<div class="col-md-12">&nbsp;</div>
										<div class="col-md-4">
											<blockquote class="b-thin rounded primary">
												<h3>Father's Details</h3>
											</blockquote>
											<div class="form-group">
												<label class="control-label">First Name</label> 
												<input type="text" class="form-control" id="fatherFirstName" name="resource.fatherFirstName" value="${employeeDetails.resource.fatherFirstName}" readonly required>
											</div>
											<div class="form-group">
												<label class="control-label">Middle Name</label> 
												<input type="text" class="form-control" id="fatherMiddleName" name="resource.fatherMiddleName" value="${employeeDetails.resource.fatherMiddleName}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">Last Name</label> 
												<input type="text" class="form-control" id="fatherLastName" name="resource.fatherLastName" value="${employeeDetails.resource.fatherLastName}" readonly required>
												
											</div>
										</div>
										<div class="col-md-4">
											<blockquote class="b-thin rounded primary">
												<h3>Mother's Details</h3>
											</blockquote>
											<div class="form-group">
												<label class="control-label">First Name</label> 
												<input type="text" class="form-control" id="motherFirstName" name="resource.motherFirstName" value="${employeeDetails.resource.motherFirstName}" readonly required>
											</div>
											<div class="form-group">
												<label class="control-label">Middle Name</label> 
												<input type="text" class="form-control" id="motherMiddleName" name="resource.motherMiddleName" value="${employeeDetails.resource.motherMiddleName}" readonly >
											</div>
											<div class="form-group">
												<label class="control-label">Last Name</label> 
												<input type="text" class="form-control" id="motherLastName" name="resource.motherLastName" value="${employeeDetails.resource.motherLastName}" readonly required>
											</div>
										</div>
										<div class="col-md-4">
											<blockquote class="b-thin rounded primary">
												<h3>Marital Status</h3>
											</blockquote>
											<div class="form-group">
												<label class="control-label">Marital Status</label> 
												<select class="form-control" name="maritalStatus" id="maritalStatus" disabled>
													<c:if test="${employeeDetails.maritalStatus eq 'MARRIED'}">
													<option value="MARRIED">MARRIED</option>
													<option value="UNMARRIED">UNMARRIED</option>				
												</c:if>
												<c:if test="${employeeDetails.maritalStatus eq 'UNMARRIED'}">
													<option value="UNMARRIED">UNMARRIED</option>
													<option value="MARRIED">MARRIED</option>
												</c:if>		
												</select>
											</div>
											<div class="form-group">
												<label class="control-label">Spouse's Name</label> 
												<input type="text" class="form-control" id="spouseName" name="spouseName" value="${employeeDetails.spouseName}" readonly>
											</div>
										</div>
										<div class="col-md-12">&nbsp;</div>																
									</div>
									<footer style="display: block; margin-top: 0;" class="panel-footer">
										<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
										<input type="button" class="btn btn-primary" id="personalDetailsTableEdit" name="edit" value="Edit" onclick="makePersonalDetailsFieldEditable();" />
										<input type="submit" id="personalDetailsTableSubmit" name="submit" value="Save" class="btn btn-primary" style="visibility:none" />		
										<input type="reset" id="personalDetailsTableCancel" class="btn btn-default" value="Cancel" readonly="readonly" style="visibility:none" onclick="makePersonalDetailsFieldInEditable();"/>				
									</footer>
									</form:form>							
									
									<hr>	
									<form:form name="staffQualificationDetailsForm" id="staffQualificationDetailsForm" enctype="multipart/form-data" action="updateStaffQualificationDetails.html">		
									<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">
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
													<c:set var="i" value="0" scope="page" />
													<c:forEach var="quali" items="${employeeDetails.qualificationList}">
														<c:if test="${quali.qualificationType eq 'MADHYAMIK'}">
															<c:set var="i" value="1" scope="page" />
															<tr>		
																<td><input type="text" class="form-control" name="qualificationList[0].examName" value="${quali.qualificationName}" id="examName0" value="10th" readonly required/></td>
																<td><input type="text" class="form-control" name="qualificationList[0].specialization" value="${quali.specialization}" id="specialization0" readonly required></td>
																<td><input type="text" class="form-control" name="qualificationList[0].schoolCollege" value="${quali.schoolCollege}" id="schoolCollege0" readonly required></td>
																<td><input type="text" class="form-control" name="qualificationList[0].boardUniversity" value="${quali.boardUniversity}" id="boardUniversity0" readonly required></td>
																<td><input type="text" class="form-control" name="qualificationList[0].marks" value="${quali.marks}" id="marks0" readonly></td>
																<td>
																	<input type="text" class="form-control" name="qualificationList[0].passingYear" value="${quali.passingYear}" id="passingYear0" readonly required>
																	<input type="hidden" class="form-control" name="qualificationList[0].qualificationType" id="qualificationType0" value="MADHYAMIK">
																</td>					
															</tr>
														</c:if>
													</c:forEach>
													<c:if test="${i eq 0}">
														<tr>		
															<td><input type="text" class="form-control" name="qualificationList[0].examName" id="examName0" value="10th" readonly required/></td>
															<td><input type="text" class="form-control" name="qualificationList[0].specialization" id="specialization0" readonly required></td>
															<td><input type="text" class="form-control" name="qualificationList[0].schoolCollege" id="schoolCollege0" readonly required></td>
															<td><input type="text" class="form-control" name="qualificationList[0].boardUniversity" id="boardUniversity0" readonly required></td>
															<td><input type="text" class="form-control" name="qualificationList[0].marks" id="marks0" readonly></td>
															<td>
																<input type="text" class="form-control" name="qualificationList[0].passingYear" id="passingYear0" readonly required>
																<input type="hidden" class="form-control" name="qualificationList[0].qualificationType" id="qualificationType0" value="MADHYAMIK">
															</td>					
														</tr>
													</c:if>	
													
													<c:set var="i" value="0" scope="page" />
													<c:forEach var="quali" items="${employeeDetails.qualificationList}">
														<c:if test="${quali.qualificationType eq 'HS'}">
															<c:set var="i" value="1" scope="page" />	
															<tr>			
																<td><input type="text" class="form-control" name="qualificationList[1].examName" id="examName1" value="12th" readonly/></td>
																<td><input type="text" class="form-control" name="qualificationList[1].specialization" value="${quali.specialization}" id="specialization1" readonly required/></td>
																<td><input type="text" class="form-control" name="qualificationList[1].schoolCollege" value="${quali.schoolCollege}"  id="schoolCollege1" readonly required /></td>
																<td><input type="text" class="form-control" name="qualificationList[1].boardUniversity" value="${quali.boardUniversity}" id="boardUniversity1" readonly required></td>
																<td><input type="text" class="form-control" name="qualificationList[1].marks" value="${quali.marks}" id="marks1" readonly required /></td>
																<td>
																	<input type="text" class="form-control" name="qualificationList[1].passingYear" value="${quali.passingYear}" id="passingYear1" readonly required/>
																	<input type="hidden" class="form-control" name="qualificationList[1].qualificationType" id="qualificationType1" value="HS"/>
																</td>				
															</tr>
														</c:if>
													</c:forEach>
													<c:if test="${i eq 0}">
														<tr>			
															<td><input type="text" class="form-control" name="qualificationList[1].examName" id="examName1" value="12th" readonly required/></td>
															<td><input type="text" class="form-control" name="qualificationList[1].specialization" id="specialization1" readonly required></td>
															<td><input type="text" class="form-control" name="qualificationList[1].schoolCollege" id="schoolCollege1" readonly required></td>
															<td><input type="text" class="form-control" name="qualificationList[1].boardUniversity" id="boardUniversity1" readonly required></td>
															<td><input type="text" class="form-control" name="qualificationList[1].marks" id="marks1" readonly required></td>
															<td>
																<input type="text" class="form-control" name="qualificationList[1].passingYear" id="passingYear1" readonly required>
																<input type="hidden" class="form-control" name="qualificationList[1].qualificationType" id="qualificationType1" value="HS">
															</td>				
														</tr>
													</c:if>	
												</tbody>
											</table>
										</div>
									</div>
									<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">
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
													<c:set var="i" value="0" scope="page" />		
													<c:forEach var="quali" items="${employeeDetails.qualificationList}">
														<c:if test="${quali.qualificationType eq 'GRADUATION'}">
														<c:set var="i" value="1" scope="page" />
													<tr>			
														<td><input type="text"  class="form-control" name="qualificationList[2].examName" value="${quali.qualificationName}" id="examName2" readonly required/></td>
														<td><input type="text"  class="form-control" name="qualificationList[2].specialization" value="${quali.specialization}"  id="specialization2" readonly required/></td>
														<td><input type="text"  class="form-control" name="qualificationList[2].schoolCollege" value="${quali.schoolCollege}" id="schoolCollege2" readonly required></td>
														<td><input type="text"  class="form-control" name="qualificationList[2].boardUniversity" value="${quali.boardUniversity}" id="boardUniversity2" readonly required/></td>
														<td><input type="text"  class="form-control" name="qualificationList[2].marks" value="${quali.marks}" id="marks2" readonly required/></td>
														<td>
															<input type="text"  class="form-control" name="qualificationList[2].passingYear" value="${quali.passingYear}" id="passingYear2" readonly required/>
															<input type="hidden" class="form-control" name="qualificationList[2].qualificationType" id="qualificationType2" value="GRADUATION" readonly required>
														</td>
													</tr>	
													</c:if>
													</c:forEach>
													<c:if test="${i eq 0}">
														<tr>			
															<td><input type="text"  class="form-control" name="qualificationList[2].examName" id="examName2" readonly required/></td>
															<td><input type="text"  class="form-control" name="qualificationList[2].specialization" id="specialization2" readonly required></td>
															<td><input type="text"  class="form-control" name="qualificationList[2].schoolCollege" id="schoolCollege2" readonly required></td>
															<td><input type="text"  class="form-control" name="qualificationList[2].boardUniversity" id="boardUniversity2" readonly required></td>
															<td><input type="text"  class="form-control" name="qualificationList[2].marks" id="marks2" readonly required></td>
															<td>
																<input type="text"  class="form-control" name="qualificationList[2].passingYear" id="passingYear2" readonly required>
																<input type="hidden" class="form-control" name="qualificationList[2].qualificationType" id="qualificationType2" value="GRADUATION">
															</td>
														</tr>		
													</c:if>		
		
												</tbody>
											</table>
										</div>
									</div>
									<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">
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
													<c:set var="i" value="0" scope="page" />	
													<c:forEach var="quali" items="${employeeDetails.qualificationList}">
														<c:if test="${quali.qualificationType eq 'POSTGRADUATION'}">
															<c:set var="i" value="1" scope="page" />
														<tr>			
															<td><input type="text" class="form-control"  name="qualificationList[3].examName" value="${quali.qualificationName}" id="examName3" readonly required/></td>
															<td><input type="text" class="form-control"  name="qualificationList[3].specialization" value="${quali.specialization}" id="specialization3" readonly required></td>
															<td><input type="text" class="form-control"  name="qualificationList[3].schoolCollege" value="${quali.schoolCollege}" id="schoolCollege3" readonly required></td>
															<td><input type="text" class="form-control"  name="qualificationList[3].boardUniversity" value="${quali.boardUniversity}" id="boardUniversity3" readonly required></td>
															<td><input type="text" class="form-control"  name="qualificationList[3].marks" value="${quali.marks}" id="marks3" readonly required></td>
															<td>
																<input type="text" class="form-control"  name="qualificationList[3].passingYear" value="${quali.passingYear}" id="passingYear3" readonly required>
																<input type="hidden" class="form-control" name="qualificationList[3].qualificationType" id="qualificationType3" value="POSTGRADUATION" readonly required>
															</td>
														</tr>	
													</c:if>
													</c:forEach>
														
													<c:if test="${i eq 0}">
														<tr>			
														<td><input type="text" class="form-control"  name="qualificationList[3].examName" id="examName3" readonly required/></td>
														<td><input type="text" class="form-control"  name="qualificationList[3].specialization" id="specialization3" readonly required></td>
														<td><input type="text" class="form-control"  name="qualificationList[3].schoolCollege" id="schoolCollege3" readonly required></td>
														<td><input type="text" class="form-control"  name="qualificationList[3].boardUniversity" id="boardUniversity3" readonly required></td>
														<td><input type="text" class="form-control"  name="qualificationList[3].marks" id="marks3" readonly required></td>
														<td>
															<input type="text" class="form-control"  name="qualificationList[3].passingYear" id="passingYear3" readonly required>
															<input type="hidden" class="form-control" name="qualificationList[3].qualificationType" id="qualificationType3" value="POSTGRADUATION">
														</td>
													</tr>			
													</c:if>	
												</tbody>
											</table>
										</div>
									</div>
									<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">
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
												<tbody id = "OtherQualificationDetails">	
												<c:set var="i" value="0" scope="page" /> 
													<c:forEach var="quali" items="${employeeDetails.qualificationList}">
														<c:if test="${quali.qualificationType eq 'OTHERS'}">
															<%-- <c:set var="i" value="1" scope="page" />		
																	 --%>
															<tr>						
																<td><input type="text" class="form-control"  name="othersExamName" value="${quali.qualificationName}" id="othersExamName" readonly required/></td>
																<td><input type="text" class="form-control"  name="othersSpecialization" value="${quali.specialization}" id="othersSpecialization" readonly required></td>
																<td><input type="text" class="form-control"  name="othersSchoolCollege" value="${quali.schoolCollege}" id="othersSchoolCollege" readonly required></td>
																<td><input type="text" class="form-control"  name="othersBoardUniversity" value="${quali.boardUniversity}" id="othersBoardUniversity" readonly  required></td>
																<td><input type="text" class="form-control"  name="othersMarks" value="${quali.marks}" id="othersMarks" readonly required></td>
																<td>
																	<input type="text" class="form-control"  name="othersPassingYear" value="${quali.passingYear}" id="othersPassingYear" readonly required>
																	<input type="hidden" class="form-control" name="othersQualificationType" id="othersQualificationType" value="OTHERS" readonly required>
																</td>
															</tr>
														</c:if>
													</c:forEach>
													 <c:if test="${i eq 0}">
														<tr>
																<!-- <td><input type="checkbox" disabled="disabled" /></td> -->
																<td><input type="text" class="form-control"  name="othersExamName" id="othersExamName" readonly required/></td>
																<td><input type="text" class="form-control"  name="othersSpecialization" id="othersSpecialization" readonly required/></td>
																<td><input type="text" class="form-control"  name="othersSchoolCollege" id="othersSchoolCollege" readonly required/></td>
																<td><input type="text" class="form-control"  name="othersBoardUniversity" id="othersBoardUniversity" readonly required/></td>
																<td><input type="text" class="form-control"  name="othersMarks" id="othersMarks" readonly required/></td>
																<td>
																	<input type="text" class="form-control"  name="othersPassingYear" id="othersPassingYear" readonly required/>
																	<input type="hidden" class="form-control" name="othersQualificationType" id="othersQualificationType" value="OTHERS">
																</td>
																<td>
																	<a class="mb-xs mt-xs mr-xs btn btn-primary"  id = "addOtherQualificationDetails" href="javascript:addOtherQualificationDetails()" style = "display:none">Add</a>
																</td>				
															</tr>
													</c:if> 
												</tbody>
											</table>
											
		<!-- 									<table class="table no-border"> -->
		<!-- 										<tbody> -->
		<!-- 											<tr> -->
		<!-- 												<td> -->
		<!-- 													<div class="form-group"> -->
		<!-- 														<label class="control-label">Upload Qualification -->
		<!-- 															Document</label> -->
		<!-- 														<div data-provides="fileupload" -->
		<!-- 															class="fileupload fileupload-new"> -->
		<!-- 															<div class="input-append"> -->
		<!-- 																<div class="uneditable-input"> -->
		<!-- 																	<span class="fileupload-preview"></span> -->
		<!-- 																</div> -->
		<!-- 																<span class="btn btn-default btn-file"> <span -->
		<!-- 																	class="fileupload-exists">Change</span> <span -->
		<!-- 																	class="fileupload-new">Select file</span> <input -->
		<!-- 																	type="file" id="addFile" type="button"> -->
		<!-- 																</span> <a data-dismiss="fileupload" -->
		<!-- 																	class="btn btn-default fileupload-exists" href="#">Remove</a> -->
		<!-- 															</div> -->
		<!-- 														</div> -->
		<!-- 													</div> -->
		<!-- 												</td> -->
		<!-- 											</tr> -->
		<!-- 											<tr> -->
		<!-- 												<td> -->
		<!-- 													<button type="button" -->
		<!-- 														class="mb-xs mt-xs mr-xs btn btn-primary">Add more documents</button> -->
		<!-- 												</td> -->
		<!-- 											</tr> -->
		<!-- 										</tbody> -->
		<!-- 									</table> -->
										</div>
									</div>
									
									<footer style="display: block; margin-top: 0;" class="panel-footer">
										<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
										<input type="button" class="btn btn-primary" id="qualificationDetailsTableEdit" name="edit" value="Edit" onclick="makeQualificationDetailsFieldEditable();" />
										<input type="submit" id="qualificationDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="btn btn-primary"/>		
										<input type="reset" id="qualificationDetailsTableCancel" class="btn btn-defalut" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeQualificationDetailsFieldInEditable();"/>
									</footer>
									</form:form>
									
									<hr>
									<form:form name="updateEmployeeAddressDetails" id="updateEmployeeAddressDetails" action="updateEmployeeAddressDetails.html">
									<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">
										<div class="col-md-12">&nbsp;</div>
										<div class="col-md-5">
											<blockquote class="b-thin rounded primary">
												<h3>Correspondence Address</h3>
											</blockquote>
											<div class="form-group">
												<label class="control-label">Address</label> 
												<input type="text" class="form-control" id="presentAddressLine" name="address.presentAddressLine" value="${employeeDetails.address.presentAddressLine}" readonly required>
											</div>
											<div class="form-group">
												<label class="control-label">Landmark</label> 
												<input type="text" class="form-control" id="presentAddressLandmark" name="address.presentAddressLandmark" value="${employeeDetails.address.presentAddressLandmark}" readonly >
											</div>
											<div class="form-group">
												<label class="control-label">City/Village</label> 
												<input type="text" class="form-control" id="presentAddressCityVillage" name="address.presentAddressCityVillage" value="${employeeDetails.address.presentAddressCityVillage}" readonly required>
											</div>
											<div class="form-group">
												<label class="control-label">Pin code</label> 
												<input  type="text" class="form-control" id="presentAddressPinCode" name="address.presentAddressPinCode" value="${employeeDetails.address.presentAddressPinCode}" readonly required>
											</div>
											<div class="form-group">
												<label class="control-label">District</label> 
												<input type="text" class="form-control" id="presentAddressDistrict" name="address.presentAddressDistrict" value="${employeeDetails.address.presentAddressDistrict}" readonly required>
											</div>
											<div class="form-group">
												<label class="control-label">State</label> 
												<select class="form-control" name="address.presentAddressState" id="presentAddressState" disabled required>
													<option value="">Please select</option>
													<c:forEach var="state" items="${stateList}" >
														<option value="${state.stateCode}" ${employeeDetails.address.presentAddressState eq state.stateName ? 'selected=selected' : ''}>${state.stateName}</option>
													</c:forEach>
												</select>
											</div>
											<div class="form-group">
												<label class="control-label">Country</label> 
												<select class="form-control" id="presentAddressCountry"	name="address.presentAddressCountry" disabled required>
													<c:forEach var="country" items="${countryList}" >
														<option value="${country.countryCode}" ${employeeDetails.address.presentAddressCountry eq country.countryCode ? 'selected=selected' : ''}>${country.countryName}</option>
													</c:forEach>
												</select>
											</div>
											<div class="form-group">
												<label class="control-label">Post Office</label> 
												<input type="text" class="form-control" id="presentAddressPostOffice" name="address.presentAddressPostOffice" value="${employeeDetails.address.presentAddressPostOffice}" readonly required>
											</div>
											<div class="form-group">
												<label class="control-label">Police Station</label> 
												<input type="text" class="form-control" id="presentAddressPoliceStation" name="address.presentAddressPoliceStation" value="${employeeDetails.address.presentAddressPoliceStation}" readonly required>
											</div>
										</div>
										<div class="col-md-2" style="margin-top: 130px;">
											<div class="checkbox-custom checkbox-default">
												<input type="checkbox" id="checkboxExample1" checked=""
													onchange="copyAddress(this);"> 
													<label for="checkboxExample1">Same as Present Address</label>
											</div>
										</div>
										<div class="col-md-5">
											<blockquote class="b-thin rounded primary">
												<h3>Permanent Address</h3>
											</blockquote>
											<div class="form-group">
												<label class="control-label">Address</label> 
												<input type="text" class="form-control" id="permanentAddressLine" name="address.permanentAddressLine" value="${employeeDetails.address.permanentAddressLine}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">Landmark</label> 
												<input type="text" class="form-control" id="permanentAddressLandmark" name="address.permanentAddressLandmark" value="${employeeDetails.address.permanentAddressLandmark}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">City/Village</label> 
												<input type="text" class="form-control" id="permanentAddressCityVillage" name="address.permanentAddressCityVillage" value="${employeeDetails.address.permanentAddressCityVillage}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">Pin code</label> 
												<input 
													type="text" class="form-control" id="permanentAddressPinCode" name="address.permanentAddressPinCode" value="${employeeDetails.address.permanentAddressPinCode}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">District</label> 
												<input type="text" class="form-control" id="permanentAddressDistrict" name="address.permanentAddressDistrict" value="${employeeDetails.address.permanentAddressDistrict}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">State</label> <select
													class="form-control" name="address.permanentAddressState" id="permanentAddressState" disabled>
													<c:forEach var="state" items="${stateList}" >
														<option value="${state.stateCode}" ${employeeDetails.address.permanentAddressState eq state.stateName ? 'selected=selected' : ''}>${state.stateName}</option>
													</c:forEach>
												</select>
											</div>
											<div class="form-group">
												<label class="control-label">Country</label> 
												<select class="form-control" id="permanentAddressCountry" name="address.permanentAddressCountry" disabled>
													<c:forEach var="country" items="${countryList}" >
														<option value="${country.countryCode}" ${employeeDetails.address.permanentAddressCountry eq country.countryCode ? 'selected=selected' : ''}>${country.countryName}</option>
													</c:forEach>
												</select>
											</div>
											<div class="form-group">
												<label class="control-label">Post Office</label> <input
													type="text" class="form-control" id="permanentAddressPostOffice" name="address.permanentAddressPostOffice" value="${employeeDetails.address.permanentAddressPostOffice}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">Police Station</label> <input
													type="text" class="form-control" id="permanentAddressPoliceStation" name="address.permanentAddressPoliceStation" value="${employeeDetails.address.permanentAddressPoliceStation}" readonly>
											</div>
										</div>
										<div class="col-md-12">&nbsp;</div>																
									</div>														
									<footer style="display: block; margin-top: 0;" class="panel-footer">
										<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
										<input type="button" class="btn btn-primary" id="addressDetailsTableEdit" name="edit" value="Edit" onclick="makeAddressDetailsFieldEditable();" />
										<input type="submit" id="addressDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="btn btn-primary"/>
										<input type="reset" id="addressDetailsTableCancel" class="btn btn-default" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeAddressDetailsFieldInEditable();"/>
									</footer>
									</form:form>							
								</div>
								<div id="w4-Emplyoee" class="tab-pane">
									
										<form:form name="updateEmployeeBasicDetails" id="updateEmployeeBasicDetails" action="updateEmployeeBasicDetails.html">
										<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">
										<div class="col-md-12">
											<blockquote class="b-thin rounded primary">
												<h3>Employee's Official Details</h3>
											</blockquote>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">User Id</label> 
												<input type="text" class="form-control" name="resource.userId" id="userId" value="${employeeDetails.resource.userId}" readonly required>
											</div>
											<div class="form-group">
												<label class="control-label">Employee Code</label> 
												<input type="text" class="form-control" name="employeeCode"	id="employeeCode" value="${employeeDetails.employeeCode}" readonly required>
											</div>
											<div class="form-group">
												<label class="control-label">Date Of Birth</label>
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="fa fa-calendar"></i>
													</span> 
														<input type="text" class="form-control" data-date-end-date="0d"  data-plugin-masked-input="" id="dateOfBirth"
														name="resource.dateOfBirth"  value="${employeeDetails.resource.dateOfBirth}" disabled required>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label">Date Of Join</label>
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="fa fa-calendar"></i>
													</span> <input class="form-control"  data-plugin-masked-input=""
														name="dateOfJoining" id="dateOfJoining" value="${employeeDetails.dateOfJoining}" disabled required>
												</div>
											</div>								
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">Date Of Retirement</label>
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="fa fa-calendar"></i>
													</span> <input class="form-control"  data-plugin-masked-input=""
														name="dateOfRetirement" id="dateOfRetirement" value="${employeeDetails.dateOfRetirement}" readonly required>
												</div>
											</div>
											<div class="form-group">
		 										<label class="control-label">Employee Type</label>  
		 										<select class="form-control" id="employeeTypeName" name="employeeType.employeeTypeName" disabled required> 
													<c:forEach var="et" items="${employee.resourceTypeList}">
														<c:if test="${et.resourceTypeName eq employeeDetails.employeeType.employeeTypeName}">
															<option value="<c:out value="${et.resourceTypeCode}"/>"><c:out value="${et.resourceTypeName}"/></option>									
														</c:if>
													</c:forEach>
												 	<c:forEach var="et" items="${employee.resourceTypeList}">
														<c:if test="${et.resourceTypeName ne employeeDetails.employeeType.employeeTypeName}">
															<option value="<c:out value="${et.resourceTypeCode}"/>"><c:out value="${et.resourceTypeName}"/></option>
														</c:if>
													</c:forEach>	 
												</select> 
											</div> 
											<div class="form-group">
												<label class="control-label">Job Type</label> 
												<select	class="form-control" id="jobTypeName" name="jobType.jobTypeName" disabled required>
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
											</div>
											<div class="form-group">
												<label class="control-label">Designation</label> 
												<select class="form-control" id="designationName" name="designation.designationName" disabled required>
													<c:forEach var="desig" items="${employee.designationList}"> 
														<c:if test="${desig.designationName eq employeeDetails.designation.designationName}">
															<option value="<c:out value="${employeeDetails.designation.designationName}"/>"><c:out value="${employeeDetails.designation.designationName}"/></option>								
														</c:if>
													</c:forEach>
													 <c:forEach var="desig" items="${employee.designationList}">
														<c:if test="${desig.designationName ne employeeDetails.designation.designationName}">
															<option value="<c:out value="${desig.designationCode}"/>"><c:out value="${desig.designationName}"/></option>
														</c:if>
													</c:forEach>	
												</select>
											</div>								
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">Qualification Summary</label> 
												<input type="text" class="form-control" id="qualificationSummary" name="qualificationSummary" value="${employeeDetails.qualificationSummary}" readonly required>
											</div>
											<div class="form-group">
												<label class="control-label">Department</label> 
													<select class="form-control" id="department" name="department.departmentName" disabled required>
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
											</div>	
											<div class="form-group">
												<label class="control-label">Teaching Level</label> 
													<select class="form-control" id="teachingLevelName" name="teachingLevelName" disabled required>
														<c:forEach var="teachingLevel" items="${employee.teachingLevelList}">
															<c:if test="${teachingLevel.teachingLevelName eq employeeDetails.teachingLevelName}">
																<option value="<c:out value="${teachingLevel.teachingLevelName}"/>"><c:out value="${teachingLevel.teachingLevelName}"/></option>									
															</c:if>
														</c:forEach>
														 <c:forEach var="teachingLevel" items="${employee.teachingLevelList}">
															<c:if test="${teachingLevel.teachingLevelName ne employeeDetails.teachingLevelName}">
																<option value="<c:out value="${teachingLevel.teachingLevelName}"/>"><c:out value="${teachingLevel.teachingLevelName}"/></option>									
															</c:if>
														</c:forEach>
													</select>
											</div>
											<div class="form-group">
												<label class="control-label">Designation Level</label> 
												<input type="text" class="form-control" id=designationLevelInput  value="${employeeDetails.designationLevel.designationLevelName}" disabled required>
												<div class="form-group" id = "designationLevelDiv" style="display:none">
						                            <!--   <label class="control-label">Designation Level</label>
						                              <span class="required" aria-required="true">*</span>	  -->                            
						                              <select id="designationLevel" name="designationLevel.designationLevelName" class="form-control" required>
															<option VALUE="" >Please Select</option>											
													  </select>
						                        </div>
											</div>								
										</div>
										<div class="col-md-12">&nbsp;</div>
										</div>
										<footer style="display: block;margin-top: 0;" class="panel-footer">
											<input type="button" class="btn btn-primary" id="basicDetailsTableEdit" name="edit" value="Edit" onclick="makeBasicDetailsFieldEditable();" />		
											<input type="submit" id="basicDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="btn btn-primary" />
											<input type="reset" id="basicDetailsTableCancel" class="btn btn-default" value="Cancel"  style="visibility:collapse" onclick="makeBasicDetailsFieldInEditable();"/>
										</footer>
										</form:form>
										
										<hr>
										
										<form:form name="updateEmployeeDependentsDetails" id="updateEmployeeDependentsDetails" action="updateEmployeeDependentsDetails.html">
										<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">
										<div class="col-md-12">
											<blockquote class="b-thin rounded primary">
												<h3>Employee's Childern</h3>
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
												<tbody id ="childOfEmployee">
												<c:set var="i" value="0" scope="page" /> 
													<c:forEach var="ed" items="${employeeDetails.employeeDependentList}">
														<tr>
															<td>
																<div class="form-group">
																	<input type="text" class="form-control" id="childName" name="childName" value="${ed.childName}" readonly>
																</div>
															</td>
															<td>
																<div class="form-group">
																	<input id="childDateOfBirth" name="childDateOfBirth" readonly data-plugin-masked-input="" data-input-mask="99/99/9999" placeholder="__/__/____" class="form-control" value="${ed.childDOB}">
																</div>
															</td>
															<td>
																<div class="form-group">
																	<select class="form-control" name="childGender" id="childGender" readonly>
																		<option value="M" ${ed.childGender eq 'M' ? 'selected=selected' : ''} >Male</option>
																		<option value="F" ${ed.childGender eq 'F' ? 'selected=selected' : ''}>Female</option>
																	</select>
																</div>
															</td>
														</tr>
													</c:forEach>
													<%-- <c:if test="${employeeDetails.employeeDependentList == null}"> --%>
															<tr>
																<td>
																	<div class="form-group">
																		<input type="text" class="form-control" id="childName" name="childName" readonly>
																	</div>
																</td>
																<td>
																	<div class="form-group">
																		<input id="childDateOfBirth" name="childDateOfBirth" readonly data-plugin-masked-input="" data-input-mask="99/99/9999" placeholder="__/__/____" class="form-control">
																	</div>
																</td>
																<td>
																	<div class="form-group">
																		<select class="form-control" name="childGender" id="childGender" readonly>
																			<option value="">Please select</option>
																			<option value="M">MALE</option>
																			<option value="F">FEMALE</option>
																		</select>
																	</div>
																</td>
																<td>
																	<a class="mb-xs mt-xs mr-xs btn btn-primary"  id = "addChilOfEmployee" href="javascript:addChilOfEmployee()" style = "display:none">Add</a>
																</td>	
															</tr>
													<%-- </c:if> --%>
												</tbody>
											</table>
											<!-- <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button"	onclick="addChildRows();">Add more</button> -->
											
											
										</div>
										<div class="col-md-12">&nbsp;</div>
										</div>
										<footer style="display: block;margin-top: 0;" class="panel-footer">
											<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
											<input type="button" class="btn btn-primary" id="employeeDependentsDetailsTableEdit" name="edit" value="Edit" onclick="makeEmployeeDependentsDetailsFieldEditable();" />
											<input type="submit" id="employeeDependentsDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="btn btn-primary" onclick="return validateEmployeeDependentsDetails();"/>
											<input type="reset" id="employeeDependentsDetailsTableCancel" class="btn btn-default" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeeDependentsDetailsFieldInEditable();"/></td>
											
										</footer>
										</form:form>
										
										<hr>
										
										<form:form name="updateEmployeeNomineeDetails" id="updateEmployeeNomineeDetails" action="updateEmployeeNomineeDetails.html">
										<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">
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
												<tbody id = "nomineeTable">
													<c:set var="i" value="0" scope="page" /> 
													<c:forEach var="nd" items="${employeeDetails.nomineeDetailsList}">	
													<tr>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="nomineeName" name="nomineeName" value="${nd.nomineeName}" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="relationship" name="relationship" value="${nd.relationship}" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="nomineePercent" name="nomineePercent" onblur="calculateHundred();" value="${nd.percentage}" readonly>
															</div>
														</td>
													</tr>
													</c:forEach>
												<%-- 	<c:if test="${employeeDetails.nomineeDetailsList == null}"> --%>
														<tr>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="nomineeName" name="nomineeName" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="relationship" name="relationship" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
															<!-- 	<input type="text" class="form-control" id="nomineePercent" name="nomineePercent" onblur="calculateHundred();" readonly> -->
																<input type="text" class="form-control" id="nomineePercent" name="nomineePercent" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<a class="mb-xs mt-xs mr-xs btn btn-primary"  id = "addNominee" href="javascript:addNominee()" style = "display:none">Add</a>
															</div>
														</td>	
													</tr>
													<%-- </c:if> --%>
													
												</tbody>
											</table>
											<!-- <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button"	onclick="addNominee();">Add more</button> -->
											
										</div>
										<div class="col-md-12">&nbsp;</div>
										</div>
										<footer style="display: block;margin-top: 0;" class="panel-footer">
											<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
											<input type="button" class="btn btn-primary" id="employeeNomineeDetailsTableEdit" name="edit" value="Edit" onclick="makeEmployeeNomineeDetailsFieldEditable();" />
											<input type="submit" id="employeeNomineeDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="btn btn-primary" onclick="return validateEmployeeNomineeDetails();"/>
											<input type="reset" id="employeeNomineeDetailsTableCancel" class="btn btn-default" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeeNomineeDetailsFieldInEditable();"/></td>
										</footer>
										</form:form>
										<!-- <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button" id="addMoreWorkShopButton">Add more Awards & Recognization Details</button> -->
								</div>
								<div id="w4-Bank" class="tab-pane">
									<form:form name="updateEmployeeBankDetails" id="updateEmployeeBankDetails" action="updateEmployeeBankDetails.html">	
									<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">
										<div class="col-md-12">
											<blockquote class="b-thin rounded primary">
												<h3>Employee's Bank Details</h3>
											</blockquote>
										</div>							
									
										<div class="col-md-3">									
											<div class="form-group">
												<label class="control-label">Bank Name</label> 
												<input type="text" class="form-control" id="bankName" name="bankName" value="${employeeDetails.resource.bankName}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">Account Type</label> 
												<input type="text" class="form-control" id="accountType" name="accountType" value="${employeeDetails.resource.accountType}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">Account Number</label> 
												<input type="text" class="form-control" id="accountNumber" name="accountNumber" value="${employeeDetails.resource.accountNumber}" readonly> 
													
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label class="control-label">Account Holder Name</label> 
												<input type="text" class="form-control" id="accountHolderName" name="accountHolderName" value="${employeeDetails.resource.accountHolderName}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">Branch</label> 
												<input type="text" class="form-control" id="branchCode" name="branchCode" value="${employeeDetails.resource.bankName}" readonly>
											</div>
											<div class="form-group">
												<label class="control-label">Branch IFSC Code</label> 
												<input type="text" class="form-control" id="branchIFSCCode" name="branchIFSCCode" value="${employeeDetails.branchIFSCCode}" readonly>
											</div>									
										</div>
										<div class="col-md-12">&nbsp;</div>
									</div>
										<footer style="display: block; margin-top: 0;" class="panel-footer">
											<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
											<input type="button" class="btn btn-primary" id="employeeBankDetailsTableEdit" name="edit" value="Edit" onclick="makeEmployeeBankDetailsFieldEditable();" />
											<input type="submit" id="employeeBankDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="btn btn-primary" onclick="return validateEmployeeBankDetails();"/>
											<input type="reset" id="employeeBankDetailsTableCancel" class="btn btn-default" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeeBankDetailsFieldInEditable();"/></td>
										</footer>							
									</form:form>							
								</div>
								<div id="w4-Work" class="tab-pane">
									
									<form:form name="updateEmployeeWorkingDetails" id="updateEmployeeWorkingDetails" enctype="multipart/form-data" action="updateEmployeeWorkingDetails.html">
										<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">
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
														<th>
															<div class="form-group">
																<a class="mb-xs mt-xs mr-xs btn btn-primary"  id = "staffPreviousWorkDetailsButton" href="javascript:addMoreExperience()" style = "display:none">Add</a>
															</div>
														</th>
													</tr>
												</thead>
												<tbody id = "staffPreviousWorkDetailsTable">
												<%-- <c:choose>
													<c:when test="${employeeDetails.organizationList!=null && employeeDetails.organizationList.size()!=0}"> --%>
														<c:forEach var="org" items="${employeeDetails.organizationList}">
															<tr>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="organizationName" name="organizationName" value="${org.organizationName}" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" id="" name="fromDate" data-plugin-masked-input="" data-input-mask="99/99/9999" placeholder="__/__/____" class="form-control" value="${org.fromDate}" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" id="" name="toDate" data-plugin-masked-input="" data-input-mask="99/99/9999" placeholder="__/__/____" class="form-control" value="${org.toDate}" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="organizationContactNo" name="organizationContactNo" value="${org.organizationContactNo}" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="organizationWebSite" name="organizationWebSite" value="${org.organizationWebSite}" readonly>
															</div>
														</td>
													</tr>
														</c:forEach>		
													<%-- </c:when>			
												<c:otherwise> --%>
													<!-- <tr>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="organizationName" name="organizationName" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" id="" name="fromDate" data-plugin-masked-input="" data-input-mask="99/99/9999" placeholder="__/__/____" class="form-control" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" id="" name="toDate" data-plugin-masked-input="" data-input-mask="99/99/9999" placeholder="__/__/____" class="form-control" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="organizationContactNo" name="organizationContactNo" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="organizationWebSite" name="organizationWebSite" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<a class="mb-xs mt-xs mr-xs btn btn-primary"  id = "staffPreviousWorkDetailsButton" href="javascript:addMoreExperience()" style = "display:none">Add</a>
															</div>
														</td>	
													</tr> -->
												<%-- </c:otherwise>
												</c:choose> --%>
												
												
												
													
												</tbody>
											</table>
											<!-- <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button"	id="staffPreviousWorkDetailsButton" onclick="addMoreExperience();">Add more Organization</button> -->
											<br>
											<table class="table no-border">
												<tbody>
													<tr>
														<td>
															<div class="form-group">
																<label class="control-label">Upload Documents</label>
																<div class="panel-body" id="downloadDiv">
																	<table class="table table-bordered table-striped mb-none" id="downloadPreviousDoc">
																		<c:forEach var="org" items="${employeeDetails.previousDocumentsAttachmentList}">
																			<tr>
																				<td>
																					<%-- <a href='viewDownloadQuestionPapers.html?folderParam="${org.storageRootPath}"&fileParam="${org.attachmentName}"'>${org.attachmentName}</a> --%>
																					<a href="downloadEmployeeAttachments.html?attachmentType=${org.attachmentType}&attachmentName=${org.attachmentName}&userId=${org.updatedBy}">${org.attachmentName}</a>
																				</td>
																			</tr>
																		</c:forEach>
																	</table>
																</div>
																<!-- <div class="fileupload fileupload-new"
																	data-provides="fileupload">
																	<div class="input-append">
																		<div class="uneditable-input">
																			<span class="fileupload-preview"></span>
																		</div>
																		<span class="btn btn-default btn-file"> <span
																			class="fileupload-exists">Change</span> <span
																			class="fileupload-new">Select file</span> <input
																			type="file"
																			name="resource.uploadFile.experienceRelatedFile" />
																		</span> <a href="#" class="btn btn-default fileupload-exists"
																			data-dismiss="fileupload">Remove</a>
																	</div>
																</div> -->
																
															</div>
														</td>
														<td>
															<!-- <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button">Add more documents</button> -->
															<input id="addFile2" class="mb-xs mt-xs mr-xs btn btn-primary" type="button" value="ADD" style = "display:none"/>
														</td>
													</tr>
													<tr>
														<td>
															<input type="file" name="resource.uploadFile.experienceRelatedFile" id = "experienceRelatedFile" style = "display:none" />
														</td>
													</tr>
													
													<!-- <tr>
														
													</tr> -->
												</tbody>
											</table>
											</div>
											<div class="col-md-12">&nbsp;</div>									
										</div>									
										<footer style="display: block; margin-top: 0;" class="panel-footer">
											<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
											<input type="button" class="btn btn-primary" id="employeePreviousWorkDetailsTableEdit" name="edit" value="Edit" onclick="makeEmployeePreviousWorkDetailsFieldEditable();" />
											<input type="submit" id="employeePreviousWorkDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="btn btn-primary" onclick="return validateEmployeePreviousWorkDetailsDetails();"/>
											<input type="reset" id="employeePreviousWorkDetailsTableCancel" class="btn btn-default" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeePreviousWorkDetailsFieldInEditable();"/></td>
										</footer>
										</form:form>
										
										<hr>
										
										<form:form name="updateEmployeeWorkShopAndTrainingDetails" id="updateEmployeeWorkShopAndTrainingDetails" action="updateEmployeeWorkShopAndTrainingDetails.html">
										
										<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">
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
														<th>
															<div class="form-group">
																<a class="mb-xs mt-xs mr-xs btn btn-primary"  id = "addMoreWorkShopButton" href="javascript:addMoreWorkShop()" style = "display:none">Add</a>
															</div>
														</th>
													</tr>
												</thead>
												<tbody id = "workShopAndTrainingDetailsTable">
													<c:forEach var="wst" items="${employeeDetails.workShopAndTrainingList}" varStatus="j">
														<tr>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="subject" name="subject" value="${wst.subject}" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="venue" name="venue" value="${wst.venue}" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" id="trainingFromDate0" name="trainingFromDate" onblur="calculateDateDifference(0);" data-plugin-masked-input="" data-input-mask="99/99/9999" placeholder="__/__/____" class="form-control" value="${wst.trainingFromDate}" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" id="trainingToDate0" name="trainingToDate" onblur="calculateDateDifference(0);" data-plugin-masked-input="" data-input-mask="99/99/9999" placeholder="__/__/____" class="form-control" value="${wst.trainingToDate}" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="organizedBy" name="organizedBy" value="${wst.organizedBy}" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="duration0" name="duration" value="${wst.duration}" readonly>
															</div>
														</td>
													</tr>
													</c:forEach>
													<c:if test="${employeeDetails.workShopAndTrainingList == null}">
														<tr>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="subject" name="subject" placeholder="" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="venue" name="venue" placeholder="" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" id="trainingFromDate0" name="trainingFromDate" onblur="calculateDateDifference(0);" data-plugin-masked-input="" data-input-mask="99/99/9999" placeholder="__/__/____" class="form-control" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" id="trainingToDate0" name="trainingToDate" onblur="calculateDateDifference(0);" data-plugin-masked-input="" data-input-mask="99/99/9999" placeholder="__/__/____" class="form-control" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="organizedBy" name="organizedBy" placeholder="" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="duration0" name="duration" readonly placeholder="" readonly>
															</div>
														</td>
													</tr>
													 </c:if>	 									
												</tbody>
											</table>
											<!-- <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button" id="addMoreWorkShopButton" onclick="addMoreWorkShop();">Add more work details</button> -->
											</div>
											<div class="col-md-12">&nbsp;</div>										
										</div>
										<footer style="display: block;margin-top: 0;" class="panel-footer">
											<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
											<input type="button" class="btn btn-primary" id="employeeWorkShopAndTrainingDetailsTableEdit" name="edit" value="Edit" onclick="makeEmployeeWorkShopAndTrainingDetailsFieldEditable();" />
											<input type="submit" id="employeeWorkShopAndTrainingDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="btn btn-primary" onclick="return validateEmployeeWorkShopAndTrainingDetails();"/>
											<input type="reset" id="employeeWorkShopAndTrainingDetailsTableCancel" class="btn btn-default" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeeWorkShopAndTrainingDetailsFieldInEditable();"/></td>
										</footer>
										</form:form>
										
										<hr>
										
										<form:form name="updateEmployeeAwardsAndRecognizationDetails" id="updateEmployeeAwardsAndRecognizationDetails" action="updateEmployeeAwardsAndRecognizationDetails.html">
										<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">
										<div class="col-md-12">&nbsp;</div>
										<div class="col-md-12">
											<blockquote class="b-thin rounded primary">
												<h3>Awards &amp; Recognization</h3>
											</blockquote>
											
											<table class="table table-bordered mb-none dataTable no-footer">
												<thead>
													<tr>
														<th>Award Name</th>
														<th>Presented By</th>
														<th>Presented On</th>
														<th>
															<div class="form-group">
																<a class="mb-xs mt-xs mr-xs btn btn-primary"  id = "addAwardAndRecognition" href="javascript:addMoreAwardsAndRecognization()" style = "display:none">Add</a>
															</div>
														</th>
													</tr>
												</thead>
												<tbody id = "awardsAndRecognizationTable">
													<c:forEach var="aar" items="${employeeDetails.awardsAndRecognizationList}">
														<tr>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="awardName" nname="awardName" value="${aar.awardName}" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="presentedBy" name="presentedBy" value="${aar.presentedBy}" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" id="presentedOn" name="presentedOn" data-plugin-masked-input="" data-input-mask="99/99/9999" placeholder="__/__/____" class="form-control" value="${aar.presentedOn}" readonly>
															</div>
														</td>
													</tr>
													</c:forEach>
													<c:if test="${employeeDetails.awardsAndRecognizationList == null}">
														<tr>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="awardName" name="awardName" placeholder="" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="presentedBy" name="presentedBy" placeholder="" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input type="text" id="presentedOn" name="presentedOn" data-plugin-masked-input="" data-input-mask="99/99/9999" placeholder="__/__/____" class="form-control" readonly>
															</div>
														</td>
													</tr>
													</c:if>											
												</tbody>
											</table>
																				
										</div>
										<div class="col-md-12">&nbsp;</div>
										</div>
										<footer style="display: block;margin-top: 0;" class="panel-footer">
											<!-- <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button" id="addMoreWorkShopButton">Add more Awards & Recognization Details</button> -->
											<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
											<input type="button" class="btn btn-primary" id="employeeAwardsAndRecognizationDetailsTableEdit" name="edit" value="Edit" onclick="makeEmployeeAwardsAndRecognizationDetailsFieldEditable();" />
											<input type="submit" id="employeeAwardsAndRecognizationDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="btn btn-primary" onclick="return validateEmployeeAwardsAndRecognizationDetails();"/>
											<input type="reset" id="employeeAwardsAndRecognizationDetailsTableCancel" class="btn btn-default" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeeAwardsAndRecognizationDetailsFieldInEditable();"/>
										</footer>
										</form:form>
										
										<hr>
										
										<form:form name="updateEmployeePublicationDetails" id="updateEmployeePublicationDetails" enctype="multipart/form-data" action="updateEmployeePublicationDetails.html">
										<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">	
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
														<th>
															<div class="form-group">
																<a class="mb-xs mt-xs mr-xs btn btn-primary"  id = "addPublicationsDetailsButton" href="javascript:new_publish()()" style = "display:none">Add</a>
															</div>
														</th>
													</tr>
												</thead>
												<tbody id = "publicationsDetails">	
												<c:choose>
													<c:when test="${employeeDetails.publicationList!=null && employeeDetails.publicationList.size()!=0}">
														<c:forEach var="pub" items="${employeeDetails.publicationList}">
															<tr>
														<td>
															<div class="form-group">
																<input type="text" class="form-control"
																	id="publicationName" name="publicationName"
																	placeholder="" value="${pub.publicationName}" readonly>
															</div>
														</td>
		
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="coPublisher"
																	name="coPublisher" placeholder="" value="${pub.coPublisher}" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input id="dateOfPublication" name="dateOfPublication"
																	data-plugin-masked-input="" data-input-mask="99/99/9999"
																	placeholder="__/__/____" class="form-control" value="${pub.dateOfPublication}" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<textarea maxlength="140" data-plugin-maxlength=""
																	rows="3" class="form-control" id="publicationDesc"
																	name="publicationDesc" readonly>${pub.publicationDesc}</textarea>
															</div>
														</td>
													</tr>
														</c:forEach>
													</c:when>
												 	<c:otherwise>
														<tr>
														<td>
															<div class="form-group">
																<input type="text" class="form-control"
																	id="publicationName" name="publicationName"
																	placeholder="" readonly>
															</div>
														</td>
		
														<td>
															<div class="form-group">
																<input type="text" class="form-control" id="coPublisher"
																	name="coPublisher" placeholder="" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<input id="dateOfPublication" name="dateOfPublication"
																	data-plugin-masked-input="" data-input-mask="99/99/9999"
																	placeholder="__/__/____" class="form-control" readonly>
															</div>
														</td>
														<td>
															<div class="form-group">
																<textarea maxlength="140" data-plugin-maxlength=""
																	rows="3" class="form-control" id="publicationDesc" readonly
																	name="publicationDesc"></textarea>
															</div>
														</td>
													</tr>
													</c:otherwise> 
												</c:choose>											
												</tbody>
											</table>
											<!-- <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button" id="addPublicationsDetailsButton" onclick="new_publish();">Add more Publications Details</button> -->
											<!-- <table class="table no-border">
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
																		<span class="btn btn-default btn-file"> <span class="fileupload-exists">Change</span> 
																		<span class="fileupload-new">Select file</span> 
																			<input type="file" name="resource.uploadFile.publicationRelatedFile" />
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
											</table>	 -->	
											<table class="table no-border">
											<tbody>
													<tr>
														<td>
															<div class="form-group">
																<label class="control-label">Upload Documents</label>
																<div class="panel-body" id="downloadDiv">
																	<table class="table table-bordered table-striped mb-none" id="downloadPreviousDoc">
																		<c:forEach var="org" items="${employeeDetails.publicationDocAttachmentList}">
																			<tr>
																				<td>
																					<%-- <a href='viewDownloadQuestionPapers.html?folderParam="${org.storageRootPath}"&fileParam="${org.attachmentName}"'>${org.attachmentName}</a> --%>
																					<a href="downloadEmployeeAttachments.html?attachmentType=${org.attachmentType}&attachmentName=${org.attachmentName}&userId=${org.updatedBy}">${org.attachmentName}</a>
																				</td>
																			</tr>
																		</c:forEach>
																	</table>
																</div>
																
															</div>
														</td>
														<td>
															<!-- <button class="mb-xs mt-xs mr-xs btn btn-primary" type="button">Add more documents</button> -->
															<input id="addFile3" class="mb-xs mt-xs mr-xs btn btn-primary" type="button" value="ADD" style = "display:none"/>
														</td>
													</tr>
													<tr>
														<td>
															<input type="file" name="resource.uploadFile.publicationRelatedFile" id = "publicationRelatedFile" style = "display:none" />
														</td>
													</tr>
													
												</tbody>					
											</table>
										</div>
										<div class="col-md-12">&nbsp;</div>
										</div>
										<footer style="display: block;margin-top: 0;" class="panel-footer">
											<td colspan="4"><input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
											<input type="button" class="btn btn-primary" id="employeePublicationDetailsTableEdit" name="edit" value="Edit" onclick="makeEmployeePublicationDetailsFieldEditable();" />		
											<input type="submit" id="employeePublicationDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="btn btn-primary" onclick="return validateEmployeePublicationDetails();"/>
											<input type="reset" id="employeePublicationDetailsTableCancel" class="btn btn-default" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeePublicationDetailsFieldInEditable();"/></td>
										</footer>
										</form:form>
										
										<hr>
										
										<form:form name="updateEmployeeConfidentialDetails" id="updateEmployeeConfidentialDetails" action="updateEmployeeConfidentialDetails.html">
										<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">
										<div class="col-md-12">
											<blockquote class="b-thin rounded primary">
												<h3>Confidential</h3>
											</blockquote>
											<div class="form-group">
												<label for="textareaDefault" class="col-md-2 control-label">Note</label>
												
												
												<div class="col-md-6">
													<textarea maxlength="240" data-plugin-maxlength="" rows="3" class="form-control" id="confidentialInformation" name="confidentialInformation"  readonly>${employeeDetails.confidentialInformation}</textarea>
													<p>
														
														<code>max-length</code>
														set to 240.
													</p>
												</div>
												
																						
											</div>
										</div>
										</div>
										<footer style="display: block;margin-top: 0;" class="panel-footer">
											<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
											<input type="button" class="btn btn-primary" id="employeeConfidentialDetailsTableEdit" name="edit" value="Edit" onclick="makeEmployeeConfidentialFieldEditable();" />
											<input type="submit" id="employeeConfidentialDetailsTableSubmit" name="submit" value="Save" style="visibility:collapse" class="btn btn-primary" onclick="return validateEmployeeConfidentialDetails();"/>
											<input type="reset" id="employeeConfidentialDetailsTableCancel" class="btn btn-default" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeeConfidentialDetailsFieldInEditable();"/>
										</footer>
										</form:form>				
								</div>
								<div id="w4-Upload" class="tab-pane">
								<form:form name="updateEmployeeImage" id="updateEmployeeImage" enctype="multipart/form-data" action="updateEmployeeImage.html">
									<div class="row" style="border: 1px solid #e1e1e1; border-bottom: none;">
										<div class="col-md-12">
											<blockquote class="b-thin rounded primary">
												<h3>Upload Image</h3>
											</blockquote>
										</div>								
										<div class="col-md-6">
											<div class="form-group">
												<label class="control-label">Staff's Image</label>
												<div class="fileupload fileupload-new"
													data-provides="fileupload">
													<div class="input-append">
														<div class="uneditable-input">
															<span class="fileupload-preview"></span>
															 <img id="preview" src="data:image/jpg;base64, ${employeeDetails.resource.image.imageName}" style="width:200px;height:200px;"/>
														</div>
														<span class="btn btn-default btn-file"> <span class="fileupload-exists">Change</span> 
														<span class="fileupload-new">Select file</span> 
														<input type="file" name="resource.image.imageData" id="image_upload" onchange="fileSelected();" disabled="disabled"/>
														</span> <a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload">Remove</a>
													</div>
												</div>
											</div>
										</div>	
										<div class="col-md-12">&nbsp;</div>															
									</div>
									<footer style="display: block;margin-top: 0;" class="panel-footer">
											<input type="hidden" name="resource.userId" value="${employeeDetails.resource.userId}" />
											<input type="button" class="btn btn-warning" id="employeeImageTableEdit" name="edit" value="Edit" onclick="makeEmployeeImageEditable();" />		
											<input type="submit" id="employeeImageTableSubmit" name="submit" value="Save" style="visibility:collapse" class="btn btn-danger"/>
											<input type="reset" id="employeeImageTableCancel" class="btn btn-default" value="Cancel" readonly="readonly" style="visibility:collapse" onclick="makeEmployeePublicationDetailsFieldInEditable();"/></td>
										</footer>
									</form:form>
								</div>
							</div>
						</div>
						<div class="panel-footer">
							<ul class="pager">
								<li class="previous disabled">
									<a><i class="fa fa-angle-left"></i> Previous</a>
								</li>
								<li class="finish hidden pull-right"></li>
								<li class="next"><a>Next <i class="fa fa-angle-right"></i></a>
								</li>
							</ul>
						</div>			
					</section>
				</div>
			</div>

	</div>
</div>


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp"%>
<script src="/cedugenie/js/erp/makeEmployeeDetailsEditable.js"></script>
<script src="/cedugenie/js/common/upload.js"></script>
<script src="/cedugenie/js/erp/editEmployeeDetails.js"></script>
<script src="/cedugenie/js/erp/employeeAddAndEdit.js"></script>
<script src="/cedugenie/js/erp/validateEmployeeDetails.js"></script>
<script src="/cedugenie/js/erp/employeeDetails.js"></script>
<script type="text/javascript">
	$('.datepicker').datepicker({
	    format: 'dd/mm/yyyy',
	    endDate: '+0d',
	    autoclose: true
	});
</script>
</body>

</html>
    
    