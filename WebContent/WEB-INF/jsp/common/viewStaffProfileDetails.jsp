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
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       } .mb-md{
       	   display: none;
       }
</style>
</head>
<body>


 <div class="row">
						<div class="col-md-4 col-lg-4">

							<section class="panel">
								<div class="panel-body">
									<div class="thumb-info mb-md">
										<img src="assets/images/%21logged-user.jpg" class="rounded img-responsive" style="margin: 0px auto;">			
                                        <div class="thumb-info-title">
                                            <span class="thumb-info-inner">${employeeDetails.resource.userId}</span>
											<span class="thumb-info-type">${employeeDetails.designation.designationName}</span>
										</div>				
									</div>

									<hr class="dotted short">
                                    
                                    <div class="row">
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>User Id</b></label>
                                            <label class="col-md-6 control-label">${employeeDetails.resource.userId}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Employee Code</b></label>
                                            <label class="col-md-6 control-label">${employeeDetails.employeeCode}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Gender</b></label>
                                            <label class="col-md-6 control-label">${employeeDetails.resource.gender}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Date of Joining</b></label>
                                            <label class="col-md-6 control-label">${employeeDetails.dateOfJoining}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Date of Birth</b></label>
                                            <label class="col-md-6 control-label">${employeeDetails.resource.dateOfBirth}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Date of Retirement</b></label>
                                            <label class="col-md-6 control-label">${employeeDetails.dateOfRetirement}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Employee Type</b></label>
                                            <label class="col-md-6 control-label">${employeeDetails.employeeType.employeeTypeName}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Job Type</b></label>
                                            <label class="col-md-6 control-label">${employeeDetails.jobType.jobTypeName}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Designation</b></label>
                                            <label class="col-md-6 control-label">${employeeDetails.designation.designationName}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Department</b></label>
                                            <label class="col-md-6 control-label">${employeeDetails.department.departmentName}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Qualification Summary</b></label>
                                            <label class="col-md-6 control-label">${employeeDetails.qualificationSummary}</label>
                                        </div>
                                    </div>
									<hr class="dotted short">

								</div>
							</section>

						</div>
						<div class="col-md-8 col-lg-8">

							<div class="tabs">
								<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Personal Details</h2>										
									</header>
								<div class="tab-content">									
									<div id="edit" class="tab-pane active">

											<h4 class="mb-xlg">Employee's Details</h4>
											<fieldset>
                                                <div class="row">
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Employee Name</b></label>
                                                        <label class="col-md-6 control-label">${employeeDetails.resource.firstName} ${employeeDetails.resource.middleName} ${employeeDetails.resource.lastName}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-5 control-label"><b>Initial Name</b></label>
                                                        <label class="col-md-7 control-label">${employeeDetails.resource.initialName}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-5 control-label"><b>Nationality</b></label>
                                                        <label class="col-md-7 control-label">${employeeDetails.resource.nationality}</label>
                                                    </div>
                                                      <div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Mother Tongue</b></label>
                                                        <label class="col-md-6 control-label">${employeeDetails.resource.motherTongue}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-5 control-label"><b>Category</b></label>
                                                        <label class="col-md-7 control-label">${employeeDetails.resource.category}</label>
                                                    </div>
                                                     <div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Religion</b></label>
                                                        <label class="col-md-6 control-label">${employeeDetails.resource.religion}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-5 control-label"><b>Medical Attention</b></label>
                                                        <label class="col-md-7 control-label">${employeeDetails.resource.medicalStatus}</label>
                                                    </div>
                                                     <div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Passport No</b></label>
<%--                                                         <c:set var="val" value="${employeeDetails.resource.passportNo}"/> --%>
<%-- 	                                                       <c:when test="${val !=null  }"> --%>
																  <label class="col-md-6 control-label">${employeeDetails.resource.motherTongue}</label>			
															<%-- /c:when>
															<c:otherwise>
																<label class="col-md-7 control-label">Not Provided</label>
															</c:otherwise> --%>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-5 control-label"><b>Pan Card No</b></label>
                                                        <label class="col-md-7 control-label">${employeeDetails.resource.panCardNo}</label>
                                                    </div>
                                                      <div class="form-group col-md-6">
                                                        <label class="col-md-5 control-label"><b>Aadhar Card No.</b></label>
                                                        <label class="col-md-7 control-label">${employeeDetails.resource.aadharCardNo}</label>
                                                    </div>
                                                      <div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Voter Card No</b></label>
                                                        <label class="col-md-6 control-label">${employeeDetails.resource.voterCardNo}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-5 control-label"><b>Voting Constituency</b></label>
                                                        <label class="col-md-7 control-label">${employeeDetails.resource.votingConstituency}</label>
                                                    </div>
                                                      <div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Parliamentary Constituency</b></label>
                                                        <label class="col-md-6 control-label">${employeeDetails.resource.parliamentaryConstituency}</label>
                                                    </div>
<!--                                                     <div class="form-group col-md-12"> -->
<!--                                                         <label class="col-md-3 control-label"><b>Present Address</b></label> -->
<!--                                                         <label class="col-md-9 control-label">Howrah, Post Office Salkia, ZipCode: 711106, West Bengal, India</label> -->
<!--                                                     </div> -->
                                                </div>
											</fieldset>
											<hr class="dotted tall">
											<h4 class="mb-xlg">Father's Details</h4>
											<fieldset>
												<div class="row">
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Father Name</b></label>
                                                        <label class="col-md-6 control-label">${employeeDetails.resource.fatherFirstName} ${employeeDetails.resource.fatherMiddleName} ${employeeDetails.resource.fatherLastName}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-5 control-label"><b>Occupation</b></label>
                                                        <label class="col-md-7 control-label">${employeeDetails.fatherOccupation}</label>
                                                    </div>
                                                    
                                                </div>
											</fieldset>
											<hr class="dotted tall">
											<h4 class="mb-xlg">Mother's Details</h4>
											<fieldset>
												<div class="row">
													<div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Mother Name</b></label>
                                                        <label class="col-md-6 control-label">${employeeDetails.resource.motherFirstName} ${employeeDetails.resource.motherMiddleName} ${employeeDetails.resource.motherLastName}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-5 control-label"><b>Occupation</b></label>
                                                        <label class="col-md-7 control-label">${employeeDetails.motherOccupation}</label>
                                                    </div>
												</div>
											</fieldset>
											<hr class="dotted tall">
											<h4 class="mb-xlg">Contact Details</h4>
											<fieldset class="mb-xl">
												<div class="row">
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Contact Number</b></label>
                                                        <label class="col-md-6 control-label">${employeeDetails.resource.mobile}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-5 control-label"><b>E-Mail</b></label>
                                                        <label class="col-md-7 control-label">${employeeDetails.resource.emailId}</label>
                                                    </div>
                                                   
                                                </div>
											</fieldset>
											<hr class="dotted tall">
											<h4 class="mb-xlg">Marital Status</h4>
											<fieldset class="mb-xl">
												<div class="row">
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Marital Status</b></label>
                                                        <label class="col-md-6 control-label">${employeeDetails.maritalStatus}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-5 control-label"><b>Spouse's Name</b></label>
                                                        <label class="col-md-7 control-label">${employeeDetails.spouseName}</label>
                                                    </div>
                                                   
                                                </div>
											</fieldset>
											<hr class="dotted tall">
											<h4 class="mb-xlg">School Details</h4>
											<fieldset class="mb-xl">
												<div class="row">
													<div class="panel-body">
				                                        <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
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
																			<td>10th</td>
																			<td>${quali.specialization}</td>
																			<td>${quali.schoolCollege}</td>
																			<td>${quali.boardUniversity}</td>
																			<td>${quali.marks}</td>
																			<td>
																				${quali.passingYear}
																				<input type="hidden" class="textfield1" name="qualificationList[0].qualificationType" id="qualificationType0" value="MADHYAMIK">
																			</td>					
																		</tr>
																	</c:if>
																</c:forEach>
																<c:set var="i" value="0" scope="page" />
																	<c:forEach var="quali" items="${employeeDetails.qualificationList}">
																		<c:if test="${quali.qualificationType eq 'HS'}">
																			<c:set var="i" value="1" scope="page" />	
																			<tr>			
																				<td>12th</td>
																				<td>${quali.specialization}</td>
																				<td>${quali.schoolCollege}</td>
																				<td>${quali.boardUniversity}</td>
																				<td>${quali.marks}</td>
																				<td>
																					${quali.passingYear}
																					<input type="hidden" class="textfield1" name="qualificationList[1].qualificationType" id="qualificationType1" value="HS" readonly="readonly"/>
																				</td>				
																			</tr>
																		</c:if>
																	</c:forEach>
				                                            </tbody>
				                                        </table>
				                                    </div> 
												</div>
											</fieldset>
											<hr class="dotted tall">
											<h4 class="mb-xlg">Graduation Details</h4>
											<fieldset class="mb-xl">
												<div class="row">
													<div class="panel-body">
				                                        <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
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
																		<td>12th</td>
																		<td>${quali.specialization}</td>
																		<td>${quali.schoolCollege}</td>
																		<td>${quali.boardUniversity}</td>
																		<td>${quali.marks}</td>
																		<td>
																			${quali.passingYear}
																			<input type="hidden" class="textfield1" name="qualificationList[1].qualificationType" id="qualificationType1" value="GRADUATION" readonly="readonly"/>
																		</td>	
																	</tr>	
																</c:if>
														</c:forEach>
				                                           </tbody>
				                                          </table>
				                                         </div>
				                                      </div>
				                                 </fieldset>
				                             <hr class="dotted tall">
											<h4 class="mb-xlg">Post Graduation Details</h4>
											<fieldset class="mb-xl">
												<div class="row">
													<div class="panel-body">
				                                        <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
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
																		<td>12th</td>
																		<td>${quali.specialization}</td>
																		<td>${quali.schoolCollege}</td>
																		<td>${quali.boardUniversity}</td>
																		<td>${quali.marks}</td>
																		<td>
																			${quali.passingYear}
																			<input type="hidden" class="textfield1" name="qualificationList[1].qualificationType" id="qualificationType1" value="POSTGRADUATION" readonly="readonly"/>
																		</td>	
																	</tr>	
																</c:if>
																</c:forEach>
				                                           </tbody>
				                                          </table>
				                                         </div>
				                                      </div>
				                                 </fieldset>
				                             <hr class="dotted tall">
											<h4 class="mb-xlg">Other Qualification Details</h4>
											<fieldset class="mb-xl">
												<div class="row">
													<div class="panel-body">
				                                        <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
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
																		<td>12th</td>
																		<td>${quali.specialization}</td>
																		<td>${quali.schoolCollege}</td>
																		<td>${quali.boardUniversity}</td>
																		<td>${quali.marks}</td>
																		<td>
																			${quali.passingYear}
																			<input type="hidden" class="textfield1" name="qualificationList[1].qualificationType" id="qualificationType1" value="POSTGRADUATION" readonly="readonly"/>
																		</td>	
																	</tr>	
																</c:if>
																</c:forEach>
				                                           </tbody>
				                                          </table>
				                                         </div>
				                                      </div>
				                                 </fieldset>
				                                 <hr class="dotted tall">
												<h4 class="mb-xlg">Uploaded Qualified Document</h4>
													<fieldset>
														<div class="row">
														<c:choose>
															<c:when test="${employeeDetails.attachmentList eq null || employeeDetails.attachmentList.size()==0}">												
																<<label class="col-md-6 control-label">No Attachment Found</label>													
															</c:when>
														<c:otherwise>
															<div class="form-group col-md-5">
		                                                        <label class="col-md-6 control-label"><b>Attatched Document</b></label>
<%-- 		                                                        <label class="col-md-6 control-label">${employeeDetails.resource.motherFirstName} ${employeeDetails.resource.motherMiddleName} ${employeeDetails.resource.motherLastName}</label> --%>
		                                                  		<c:if test="${employeeDetails.attachmentList != null}">
																	<c:forEach var="attachment" items="${employeeDetails.attachmentList}" >
																		<c:if test="${attachment.attachmentType == 'qualification_doc'}">
																	
																			<label class="col-md-6 control-label"><a href="downloadEmployeeAttachments.html?attachmentType=<c:out value="${attachment.attachmentType}"></c:out>&attachmentName=<c:out value="${attachment.attachmentName}"></c:out>&userId=<c:out value="${employeeDetails.resource.userId}"></c:out>">${attachment.attachmentName}</a></label>							
																		
																		</c:if>								
																	</c:forEach>
																</c:if>
		                                                  
		                                                    </div>
		                                                   </c:otherwise>
		                                                  </c:choose>
		                                                    
														</div>
													</fieldset>
													<hr class="dotted tall">
													<h4 class="mb-xlg">Correspondence Address</h4>
													<fieldset class="mb-xl">
														<div class="row">
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-6 control-label"><b>Address</b></label>
		                                                        <label class="col-md-6 control-label">${employeeDetails.address.presentAddressLine}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-5 control-label"><b>Landmark</b></label>
		                                                        <label class="col-md-7 control-label">${employeeDetails.address.presentAddressLandmark}</label>
		                                                    </div>
		                                                   	 <div class="form-group col-md-6">
		                                                        <label class="col-md-6 control-label"><b>City/Village</b></label>
		                                                        <label class="col-md-6 control-label">${employeeDetails.address.presentAddressCityVillage}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-5 control-label"><b>Railway Station</b></label>
		                                                        <label class="col-md-7 control-label">${employeeDetails.address.presentAddressRailwayStation}</label>
		                                                    </div>
		                                                     <div class="form-group col-md-6">
		                                                        <label class="col-md-6 control-label"><b>Post Office</b></label>
		                                                        <label class="col-md-6 control-label">${employeeDetails.address.presentAddressPostOffice}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-5 control-label"><b>Police Station</b></label>
		                                                        <label class="col-md-7 control-label">${employeeDetails.address.presentAddressPoliceStation}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-6 control-label"><b>Pin code</b></label>
		                                                        <label class="col-md-6 control-label">${employeeDetails.address.presentAddressPinCode}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-5 control-label"><b>District</b></label>
		                                                        <label class="col-md-7 control-label">${employeeDetails.address.presentAddressDistrict}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-6 control-label"><b>State</b></label>
		                                                        <label class="col-md-6 control-label">${state.stateName}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-5 control-label"><b>Country</b></label>
		                                                        <label class="col-md-7 control-label">${country.countryName}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-5 control-label"><b>Phone</b></label>
		                                                        <label class="col-md-7 control-label">${employeeDetails.address.presentAddressPhone}</label>
		                                                    </div>
		                                                </div>
													</fieldset>
													<hr class="dotted tall">
													<h4 class="mb-xlg">	Permanent Address</h4>
													<fieldset class="mb-xl">
														<div class="row">
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-6 control-label"><b>Address</b></label>
		                                                        <label class="col-md-6 control-label">${employeeDetails.address.permanentAddressLine}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-5 control-label"><b>Landmark</b></label>
		                                                        <label class="col-md-7 control-label">${employeeDetails.address.permanentAddressLandmark}</label>
		                                                    </div>
		                                                   	 <div class="form-group col-md-6">
		                                                        <label class="col-md-6 control-label"><b>City/Village</b></label>
		                                                        <label class="col-md-6 control-label">${employeeDetails.address.permanentAddressCityVillage}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-5 control-label"><b>Railway Station</b></label>
		                                                        <label class="col-md-7 control-label">${employeeDetails.address.permanentAddressRailwayStation}</label>
		                                                    </div>
		                                                     <div class="form-group col-md-6">
		                                                        <label class="col-md-6 control-label"><b>Post Office</b></label>
		                                                        <label class="col-md-6 control-label">${employeeDetails.address.permanentAddressPostOffice}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-5 control-label"><b>Police Station</b></label>
		                                                        <label class="col-md-7 control-label">${employeeDetails.address.permanentAddressPoliceStation}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-6 control-label"><b>Pin code</b></label>
		                                                        <label class="col-md-6 control-label">${employeeDetails.address.permanentAddressPinCode}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-5 control-label"><b>District</b></label>
		                                                        <label class="col-md-7 control-label">${employeeDetails.address.permanentAddressDistrict}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-6 control-label"><b>State</b></label>
		                                                        <label class="col-md-6 control-label">${state.stateName}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-5 control-label"><b>Country</b></label>
		                                                        <label class="col-md-7 control-label">${country.countryName}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-5 control-label"><b>Phone</b></label>
		                                                        <label class="col-md-7 control-label">${employeeDetails.address.permanentAddressPhone}</label>
		                                                    </div>
		                                                </div>
													</fieldset>
													<hr class="dotted tall">
													<h4 class="mb-xlg">Employee's Childern</h4>
													<fieldset class="mb-xl">
														<div class="row">
																<c:forEach var="ed" items="${employeeDetails.employeeDependentList}">		
				                                                    <div class="form-group col-md-6">
				                                                        <label class="col-md-6 control-label"><b>Name</b></label>
				                                                        <label class="col-md-6 control-label">${ed.childName}</label>
				                                                    </div>
				                                                    <div class="form-group col-md-6">
				                                                        <label class="col-md-5 control-label"><b>Gender</b></label>
				                                                        <c:if test = "${ed.childGender eq 'M'}">
				                                                       		 <label class="col-md-6 control-label">Male</label>
				                                                        </c:if>
				                                                         <c:if test = "${ed.childGender eq 'F'}">
				                                                       		 <label class="col-md-6 control-label">FeMale</label>
				                                                        </c:if>
				                                                    </div>
				                                                   	 <div class="form-group col-md-6">
				                                                        <label class="col-md-6 control-label"><b>DOB</b></label>
				                                                        <label class="col-md-6 control-label">${ed.childDOB}</label>
				                                                    </div>
		                                            
		                                            			</c:forEach>
		                                                </div>
													</fieldset>
													<hr class="dotted tall">
													<h4 class="mb-xlg">Employee's Bank Details</h4>
													<fieldset class="mb-xl">
														<div class="row">
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-6 control-label"><b>Bank Name</b></label>
		                                                        <label class="col-md-6 control-label">${employeeDetails.resource.bankName}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-5 control-label"><b>Branch Code</b></label>
		                                                        <label class="col-md-7 control-label">"${employeeDetails.resource.bankName}</label>
		                                                    </div>
		                                                   	 <div class="form-group col-md-6">
		                                                        <label class="col-md-6 control-label"><b>Account Type</b></label>
		                                                        <label class="col-md-6 control-label">${employeeDetails.resource.accountType}</label>
		                                                    </div>
		                                                    <div class="form-group col-md-6">
		                                                        <label class="col-md-5 control-label"><b>Account Holder Name</b></label>
		                                                        <label class="col-md-7 control-label">${employeeDetails.resource.accountHolderName}</label>
		                                                    </div>
		                                                     <div class="form-group col-md-6">
		                                                        <label class="col-md-6 control-label"><b>Account Number</b></label>
		                                                        <label class="col-md-6 control-label">${employeeDetails.resource.accountNumber}</label>
		                                                    </div>
		                                                    
		                                                </div>
													</fieldset>
													<hr class="dotted tall">
													<h4 class="mb-xlg">Previous Organization Details</h4>
													<fieldset class="mb-xl">
														<div class="row">
															<c:choose>
																<c:when test="${employeeDetails.organizationList!=null && employeeDetails.organizationList.size()!=0}">
																	<c:forEach var="org" items="${employeeDetails.organizationList}">
					                                                    <div class="form-group col-md-6">
					                                                        <label class="col-md-6 control-label"><b>Previous Organization Name</b></label>
					                                                        <label class="col-md-6 control-label">${org.organizationName}</label>
					                                                    </div>
					                                                    <div class="form-group col-md-6">
					                                                        <label class="col-md-5 control-label"><b>From Date</b></label>
					                                                        <label class="col-md-7 control-label">${org.fromDate}</label>
					                                                    </div>
					                                                   	 <div class="form-group col-md-6">
					                                                        <label class="col-md-6 control-label"><b>To Date</b></label>
					                                                        <label class="col-md-6 control-label">${org.toDate}</label>
					                                                    </div>
					                                                    <div class="form-group col-md-6">
					                                                        <label class="col-md-5 control-label"><b>Contact</b></label>
					                                                        <label class="col-md-7 control-label">${org.organizationContactNo}</label>
					                                                    </div>
					                                                     <div class="form-group col-md-6">
					                                                        <label class="col-md-6 control-label"><b>Website</b></label>
					                                                        <label class="col-md-6 control-label">${org.organizationWebSite}</label>
					                                                    </div>
					                                                    
		                                                    		</c:forEach>
		                                                    	</c:when>
		                                                   </c:choose>
		                                                </div>
													</fieldset>
													<hr class="dotted tall">
													<h4 class="mb-xlg">Uploaded Previous Organization Document</h4>
														<fieldset>
															<div class="row">
															<c:choose>
																<c:when test="${employeeDetails.attachmentList eq null || employeeDetails.attachmentList.size()==0}">												
																	<<label class="col-md-6 control-label">No Attachment Found</label>													
																</c:when>
															<c:otherwise>
																<div class="form-group col-md-5">
			                                                        <label class="col-md-6 control-label"><b>Attatched Document</b></label>
	<%-- 		                                                        <label class="col-md-6 control-label">${employeeDetails.resource.motherFirstName} ${employeeDetails.resource.motherMiddleName} ${employeeDetails.resource.motherLastName}</label> --%>
			                                                  		<c:if test="${employeeDetails.attachmentList != null}">
																		<c:forEach var="attachment" items="${employeeDetails.attachmentList}" >
																			<c:if test="${attachment.attachmentType == 'previous_organization_doc'}">
																		
																				<label class="col-md-6 control-label"><a href="downloadEmployeeAttachments.html?attachmentType=<c:out value="${attachment.attachmentType}"></c:out>&attachmentName=<c:out value="${attachment.attachmentName}"></c:out>&userId=<c:out value="${employeeDetails.resource.userId}"></c:out>">${attachment.attachmentName}</a></label>							
																			
																			</c:if>								
																		</c:forEach>
																	</c:if>
			                                                  
			                                                    </div>
			                                                   </c:otherwise>
			                                                  </c:choose>
			                                                    
															</div>
														</fieldset>
											<div class="panel-footer">
												<div class="row">
													<div class="col-md-12">
														<a class="btn btn-primary ml-sm pull-right" target="_blank" href=""><i class="fa fa-print"></i> Print</a>
													</div>
												</div>
											</div>

										

									</div>
								</div>
							</div>
						</div>
                        
					</div>	

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<!-- <script src="/cedugenie/assets/javascripts/tables/accessTypeContactMapping.editable.js"></script>
<script src="/cedugenie/assets/javascripts/tables/examples.datatables.editable.js"></script> -->
</body>
</html>