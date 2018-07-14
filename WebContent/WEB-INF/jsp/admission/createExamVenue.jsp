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
       } .mb-md{
       	   display: none;
       }
</style>

</head>
<body>


 <div class="row">
						<div class="col-md-8 col-md-offset-2">
						<c:if test="${successStatus != null}">
							<div class="successbox" id="successbox" style="visibility:visible;">
								<span id="infomsg" style="visibility:visible;">Successfully Updated!</span>	
							</div>
						</c:if>
						<c:if test="${failStatus != null}">
								<div class="errorbox" id="errorbox" style="visibility: visible;">
									<span id="errormsg">Update Fail!</span>	
								</div>
						</c:if>
						 <form:form method="POST" id="vmlform" action="submitExamVenue.html" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Exam Venue</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										<div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Venue</label>
                                                    <input type="text" class="form-control" id="venue" name="venueName" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Contact No</label>                                                
                                                    <input type="text" class="form-control" id="contactNo" name="contactNo" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Mobile No</label>                                                
                                                    <input type="text" class="form-control" id="mobileNo" name="mobileNo" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Email</label>                                                
                                                    <input type="text" class="form-control" id="email" name="email" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Capacity</label>                                                
                                                    <input type="text" class="form-control" id="capacity" name="capacity" placeholder="">
                                                </div>
                                            </div>
                                        </div> 
                                        <hr>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <label class="control-label">Address</label>
                                                    <input type="text" class="form-control" id="presentAddress1" name="address.presentAddressLine"  placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Landmark</label>                                                
                                                    <input type="text" class="form-control" id="presentAddressLandmark" name="address.presentAddressLandmark" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">City/Village</label>                                                
                                                    <input type="text" class="form-control" id="presentAddressCityVillage" name="address.presentAddressCityVillage"placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Post Office</label>                                                
                                                    <input type="text" class="form-control" id="presentAddressPostOffice" name="address.presentAddressPostOffice" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Police Station</label>                                                
                                                    <input type="text" class="form-control" id="presentAddressPoliceStation" name="address.presentAddressPoliceStation"  placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Pin code</label>                                                
                                                    <input type="text" class="form-control" id="presentAddressPinCode" name="address.presentAddressPinCode" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">District</label>                                                
                                                    <input type="text" class="form-control" id="presentAddressDistrict" name="address.presentAddressDistrict" placeholder="">
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Country</label>                                                
                                                    <select class="form-control" id="presentAddressCountry" name="address.presentAddressCountry">
                                                        <option value="">Select...</option>
                                                        	<c:forEach var="country" items="${countryList}" >
																<option VALUE="${country.countryCode}">${country.countryName}</option>
															</c:forEach>	
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">State</label>                                                
                                                    <select class="form-control" name="address.presentAddressState" id="presentAddressState">
                                                        <option value="">Select...</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id="submit" name="submit">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                           </form:form>
						</div>						
					</div>	
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>

<script src="/cedugenie/js/admission/createExamVenue.js"></script>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>