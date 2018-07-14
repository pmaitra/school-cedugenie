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
<title>Add Book</title>
<%@ include file="/include/include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>


				
					 <div class="row">
						<div class="col-md-4 col-lg-4">
							<section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
									</div>
									<h2 class="panel-title">${studentDetailsFromDB.studentName}'s Profile</h2>										
								</header>
								<div class="panel-body">
									<c:choose>
										<c:when test="${studentDetailsFromDB.resource.desc ne null}">							
											<img id="preview" src="StudentImage/${studentDetailsFromDB.resource.desc}" alt="&nbsp;&nbsp;&nbsp;Image Not Found"/>							
										</c:when>
										<c:otherwise>				
											<c:choose>
													<c:when test="${studentDetailsFromDB.resource.gender eq 'M'}">
														<img id="preview" src="StudentImage/male_default_images.jpg" alt="Image Not Found" />
													</c:when>
													<c:otherwise>
														<img id="preview" src="StudentImage/female_default_images.jpg" alt="Image Not Found" />
													</c:otherwise>
											</c:choose>							
										</c:otherwise>
									</c:choose>
									<hr class="dotted short">
                                    <div class="row">
                                    	<div class="form-group">
                                            <label class="col-md-6 control-label"><b>Registration ID</b></label>
                                            <label class="col-md-6 control-label">${studentDetailsFromDB.resource.registrationId}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Full Name</b></label>
                                            <label class="col-md-6 control-label">${studentDetailsFromDB.studentName}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Date Of Birth</b></label>
                                            <label class="col-md-6 control-label">${studentDetailsFromDB.resource.dateOfBirth}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Date of Admission</b></label>
                                            <label class="col-md-6 control-label">${studentDetailsFromDB.dateOfAdmission}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Standard</b></label>
                                            <label class="col-md-6 control-label">${studentDetailsFromDB.standard}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Section</b></label>
                                            <label class="col-md-6 control-label">${studentDetailsFromDB.section}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Gender</b></label>
                                            <label class="col-md-6 control-label">${studentDetailsFromDB.resource.gender}</label>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-6 control-label"><b>Blood Group</b></label>
                                            <label class="col-md-6 control-label">${studentDetailsFromDB.resource.bloodGroup}</label>
                                        </div>
                                    </div>
									<hr class="dotted short">
								</div>
							</section>
						</div>
						<div class="col-md-8 col-lg-8">
							<div class="tabs">
								<div class="tab-content">									
									<div id="edit" class="tab-pane active">
											<h4 class="mb-xlg">Contact Details</h4>
											<fieldset>
                                                <div class="row">
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Contact No.</b></label>
                                                        <label class="col-md-6 control-label">${studentDetailsFromDB.resource.mobile}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-5 control-label"><b>E-mail</b></label>
                                                        <label class="col-md-7 control-label">${studentDetailsFromDB.resource.emailId}</label>
                                                    </div>
                                                    <div class="form-group col-md-12">
                                                        <label class="col-md-3 control-label"><b>Present Address</b></label>
                                                        <label class="col-md-9 control-label">
                                                        	<c:out value="${studentDetailsFromDB.address.presentAddressLine}"/>&thinsp;
                                                        	<c:if test="${studentDetailsFromDB.address.presentAddressLandmark ne null && not empty studentDetailsFromDB.address.presentAddressLandmark }">
															, Landmark: <c:out value="${studentDetailsFromDB.address.presentAddressLandmark}"/>&thinsp;
															</c:if>		
															<c:if test="${studentDetailsFromDB.address.presentAddressPostOffice ne null && not empty studentDetailsFromDB.address.presentAddressPostOffice}">
															, Post Office: <c:out value="${studentDetailsFromDB.address.presentAddressPostOffice}"/>&thinsp;
															</c:if>	<br>
															<c:if test="${studentDetailsFromDB.address.presentAddressCityVillage ne null && not empty studentDetailsFromDB.address.presentAddressCityVillage}">
																City: <c:out value="${studentDetailsFromDB.address.presentAddressCityVillage}"/>&thinsp;
															</c:if>
															<c:if test="${studentDetailsFromDB.address.presentAddressPinCode ne null && not empty studentDetailsFromDB.address.presentAddressPinCode}">
															, ZIP/PostalCode: <c:out value="${studentDetailsFromDB.address.presentAddressPinCode}"/>&thinsp;
															</c:if>	
															<c:if test="${studentDetailsFromDB.address.presentAddressPoliceStation ne null&& not empty studentDetailsFromDB.address.presentAddressPoliceStation}">
															, Police Station: <c:out value="${studentDetailsFromDB.address.presentAddressPoliceStation}"/>
															</c:if><br>
															<c:if test="${studentDetailsFromDB.address.presentAddressDistrict ne null && not empty studentDetailsFromDB.address.presentAddressDistrict}">
																District: <c:out value="${studentDetailsFromDB.address.presentAddressDistrict}"/>&thinsp;
															</c:if>	
															<c:if test="${studentDetailsFromDB.address.presentAddressState ne null && not empty studentDetailsFromDB.address.presentAddressState}">
															, State: <c:out value="${studentDetailsFromDB.address.presentAddressState}"/>&thinsp;  
															</c:if>
															<c:if test="${studentDetailsFromDB.address.presentAddressCountry ne null && not empty studentDetailsFromDB.address.presentAddressCountry}">
															, Country: <c:out value="${studentDetailsFromDB.address.presentAddressCountry}"/>&thinsp;
															</c:if>					
                                                        </label>
                                                    </div>
                                                    <div class="form-group col-md-12">
                                                        <label class="col-md-3 control-label"><b>Permanent Address</b></label>
                                                        <label class="col-md-9 control-label">
                                                        	<c:out value="${studentDetailsFromDB.address.permanentAddressLine}"/>&thinsp;
                                                        	<c:if test="${studentDetailsFromDB.address.permanentAddressLandmark ne null && not empty studentDetailsFromDB.address.permanentAddressLandmark }">
															, Landmark: <c:out value="${studentDetailsFromDB.address.permanentAddressLandmark}"/>&thinsp;
															</c:if>		
															<c:if test="${studentDetailsFromDB.address.permanentAddressPostOffice ne null && not empty studentDetailsFromDB.address.permanentAddressPostOffice}">
															, Post Office: <c:out value="${studentDetailsFromDB.address.permanentAddressPostOffice}"/>&thinsp;
															</c:if>	<br>
															<c:if test="${studentDetailsFromDB.address.permanentAddressCityVillage ne null && not empty studentDetailsFromDB.address.permanentAddressCityVillage}">
																City: <c:out value="${studentDetailsFromDB.address.permanentAddressCityVillage}"/>&thinsp;
															</c:if>
															<c:if test="${studentDetailsFromDB.address.permanentAddressPinCode ne null && not empty studentDetailsFromDB.address.permanentAddressPinCode}">
															, ZIP/PostalCode: <c:out value="${studentDetailsFromDB.address.permanentAddressPinCode}"/>&thinsp;
															</c:if>	
															<c:if test="${studentDetailsFromDB.address.permanentAddressPoliceStation ne null&& not empty studentDetailsFromDB.address.permanentAddressPoliceStation}">
															, Police Station: <c:out value="${studentDetailsFromDB.address.permanentAddressPoliceStation}"/>
															</c:if><br>
															<c:if test="${studentDetailsFromDB.address.permanentAddressDistrict ne null && not empty studentDetailsFromDB.address.permanentAddressDistrict}">
																District: <c:out value="${studentDetailsFromDB.address.permanentAddressDistrict}"/>&thinsp;
															</c:if>	
															<c:if test="${studentDetailsFromDB.address.permanentAddressState ne null && not empty studentDetailsFromDB.address.permanentAddressState}">
															, State: <c:out value="${studentDetailsFromDB.address.permanentAddressState}"/>&thinsp;  
															</c:if>
															<c:if test="${studentDetailsFromDB.address.permanentAddressCountry ne null && not empty studentDetailsFromDB.address.permanentAddressCountry}">
															, Country: <c:out value="${studentDetailsFromDB.address.permanentAddressCountry}"/>&thinsp;
															</c:if>					
                                                        </label>
                                                    </div>
                                                </div>
											</fieldset>
											<hr class="dotted tall">
											<h4 class="mb-xlg">Guardian's Details</h4>
											<fieldset>
												<div class="row">
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Father</b></label>
                                                        <label class="col-md-6 control-label">${studentDetailsFromDB.resource.fatherFirstName} ${studentDetailsFromDB.resource.fatherMiddleName} ${studentDetailsFromDB.resource.fatherLastName}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Mother</b></label>
                                                        <label class="col-md-6 control-label">${studentDetailsFromDB.resource.motherFirstName} ${studentDetailsFromDB.resource.motherMiddleName} ${studentDetailsFromDB.resource.motherLastName}</label>
                                                    </div>
                                                </div>
											</fieldset>
											<hr class="dotted tall">
											<h4 class="mb-xlg">Other Details</h4>
											<fieldset class="mb-xl">
												<div class="row">
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Mother Tongue</b></label>
                                                        <label class="col-md-6 control-label">${studentDetailsFromDB.resource.motherTongue}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-5 control-label"><b>Category</b></label>
                                                        <label class="col-md-7 control-label">${studentDetailsFromDB.resource.category}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Nationality</b></label>
                                                        <label class="col-md-6 control-label">${studentDetailsFromDB.resource.nationality}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-5 control-label"><b>Religion</b></label>
                                                        <label class="col-md-7 control-label">${studentDetailsFromDB.resource.religion}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-5 control-label"><b>Hostel</b></label>
                                                        <label class="col-md-7 control-label">${studentDetailsFromDB.house}</label>
                                                    </div>
                                                </div>
											</fieldset>
											<hr class="dotted tall">
											<h4 class="mb-xlg">Previous School Details</h4>
											<fieldset class="mb-xl">
												<div class="row">
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Previous School Name</b></label>
                                                        <label class="col-md-6 control-label">${studentDetailsFromDB.previousSchoolName}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-5 control-label"><b>Previous School Phone</b></label>
                                                        <label class="col-md-7 control-label">${studentDetailsFromDB.previousSchoolPhone}</label>
                                                    </div>
                                                    <div class="form-group col-md-6">
                                                        <label class="col-md-6 control-label"><b>Previous School Website</b></label>
                                                        <label class="col-md-6 control-label">${studentDetailsFromDB.previousSchoolWebsite}</label>
                                                    </div>
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
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
</body>
</html>