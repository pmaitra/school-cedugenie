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
<title>Student's Profile</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
      .scroll-to-top{
          display: none !important;
      }.mb-md{
      	   display: none;
      }
      /*=========tabs Settings==============*/
	.tabs-left ul li:before {
		display: none;
	}
	.tabs-left, .tabs-right {
		border-bottom: none;
		padding-top: 2px;
	}
	.tabs-left {
		border-right: 1px solid #ddd;
	}
	.tabs-right {
		border-left: 1px solid #ddd;
	}
	.tabs-left>li, .tabs-right>li {
		float: none;
		margin-bottom: 2px;
	}
	.tabs-left>li {
		margin-right: -1px;
		display: block;
	}
	.tabs-right>li {
		margin-left: -1px;
	}
	.tabs-left>li.active>a, .tabs-left>li.active>a:hover, .tabs-left>li.active>a:focus {
		border-bottom-color: #ddd;
		border-right-color: transparent;
		border-top: 1px solid #ddd !important;
	}
	.tabs-right>li.active>a, .tabs-right>li.active>a:hover, .tabs-right>li.active>a:focus {
		border-bottom: 1px solid #ddd;
		border-left-color: transparent;
		border-top: 1px solid #ddd !important;
	}
	.tabs-left>li>a {
		border-radius: 4px 0 0 4px;
		margin-right: 0;
		display: block;
		border: none;
		background: none;
	}
	.tabs-left>li>a:hover {
		border-top: 1px solid #ddd !important;
	}
	.tabs-right>li>a {
		border-radius: 0 4px 4px 0;
		margin-right: 0;
	}
	.tab-right-content .tab-content {
		border: none;
		padding: 0;
		box-shadow: none;
	}
	.tab-right-content .tab-content h3 {
		margin-top: 0px;
	}
	.vertical-text {
		margin-top: 50px;
		border: none;
		position: relative;
	}
	.vertical-text>li {
		height: 20px;
		width: 120px;
		margin-bottom: 100px;
	}
	.vertical-text>li>a {
		border-bottom: 1px solid #ddd;
		border-right-color: transparent;
		text-align: center;
		border-radius: 4px 4px 0px 0px;
	}
	.vertical-text>li.active>a, .vertical-text>li.active>a:hover, .vertical-text>li.active>a:focus {
		border-bottom-color: transparent;
		border-right-color: #ddd;
		border-left-color: #ddd;
	}
	.vertical-text.tabs-left {
		left: -50px;
	}
	.vertical-text.tabs-right {
		right: -50px;
	}
	.vertical-text.tabs-right>li {
		-webkit-transform: rotate(90deg);
		-moz-transform: rotate(90deg);
		-ms-transform: rotate(90deg);
		-o-transform: rotate(90deg);
		transform: rotate(90deg);
	}
	.vertical-text.tabs-left>li {
		-webkit-transform: rotate(-90deg);
		-moz-transform: rotate(-90deg);
		-ms-transform: rotate(-90deg);
		-o-transform: rotate(-90deg);
		transform: rotate(-90deg);
	}
</style>
</head>
<body>

		<header class="page-header">
			<h2>Student's Profile</h2>
		</header>
		<div class="content-padding">
			<div class="row">
        <div class="col-xs-12">
          <div class="tabs tabs-primary">
            <ul class="nav nav-tabs">
              <li class="active"> <a href="#Personal" data-toggle="tab" aria-expanded="true">Personal Details</a> </li>
              <li class=""> <a href="#Academic" data-toggle="tab" aria-expanded="false">Academic Details</a> </li>
              <li class=""> <a href="#Curricular" data-toggle="tab" aria-expanded="false">Extra Curricular Activities</a> </li>
              <li class=""> <a href="#Games" data-toggle="tab" aria-expanded="false">Games/Sports</a> </li>
              <li class=""> <a href="#NCC" data-toggle="tab" aria-expanded="false">NCC</a> </li>
              <li class=""> <a href="#Conduct" data-toggle="tab" aria-expanded="false">Conduct</a> </li>
              <li class=""> <a href="#Medical" data-toggle="tab" aria-expanded="false">Medical Info</a> </li>
            </ul>
            <div class="tab-content">
              <div id="Personal" class="tab-pane active">
                <div class="row">
                  <div class="col-md-12">
                    <blockquote class="b-thin rounded primary">
                      <h3>Summary</h3>
                    </blockquote>
                  </div>
                  <div class="col-md-3">
                  	<div class="form-group">
                      <label class="control-label">School Number</label>
                      <input class="form-control" value="${student.userId}" readonly="readonly">
                    </div>
                  </div>
                  <div class="col-md-3">
                  	<div class="form-group">
                      <label class="control-label">Current Standard</label>
                      <input class="form-control" value="${student.standard}" readonly="readonly">
                    </div>
                  </div>
                  <div class="col-md-3">
                  	<div class="form-group">
                      <label class="control-label">Admission Class</label>
                      <input class="form-control" value="${student.courseId}" readonly="readonly">
                    </div>
                  </div>
                  <div class="col-md-3">
	                  <div class="form-group">
	               		<div class="fileupload fileupload-new" data-provides="fileupload">
							<div class="input-append">
								<div class="uneditable-input">
									<span class="fileupload-preview"></span>
									 <img id="preview" src="data:image/jpg;base64, ${student.resource.image.imageName}" style="width:150px;height:150px;"/>
								</div>
							</div>
						</div>
					</div>	
				</div>	
                </div>
                <hr>
                <div class="row">
                  <div class="col-md-12">
                    <blockquote class="b-thin rounded primary">
                      <h3>Student's Details</h3>
                    </blockquote>
                  </div>
                  <div class="col-md-3">
                    <div class="form-group">
                      <label class="control-label">Name</label>
                      <input type="text" class="form-control" value="${student.studentName}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Date Of Birth</label>
                      <div class="input-group"> <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
                        <input class="form-control" value="${student.resource.dateOfBirth}" readonly="readonly">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="control-label">Blood Group</label>
                      <input type="text" class="form-control" value="${student.resource.bloodGroup}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Category</label>
                      <input type="text" class="form-control" value="${student.resource.category}" readonly="readonly">
                    </div>
                  </div>
                  <div class="col-md-3">
                  	<div class="form-group">
                      <label class="control-label">House</label>
                      <input type="text" class="form-control" value="${student.house}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">State Of Domicile</label>
                      <input type="text" class="form-control" value="${student.stateOfDomicile}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Bank A/C No.</label>
                      <input type="text" class="form-control" value="${student.resource.accountNumber}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Aadhaar No.</label>
                      <input type="text" class="form-control" value="${student.resource.aadharCardNo}" readonly="readonly">
                    </div>
                  </div>
                  <div class="col-md-3">
                    <div class="form-group">
                      <label class="control-label">Child Id</label>
                      <input type="text" class="form-control" value="${student.resource.childId}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Food Preference</label>
                      <input type="text" class="form-control" value="${student.resource.foodPreference}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Pickup Place</label>
                      <input type="text" class="form-control" value="${student.resource.firstPickUpPlace}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Hobbies</label>
                      <input type="text" class="form-control" value="${student.resource.hobbies}" readonly="readonly">
                    </div>
                  </div>
                  <div class="col-md-3">
                    <div class="form-group">
                      <label class="control-label">Personal Identification Mark</label>
                      <input type="text" class="form-control" value="${student.resource.personalIdentificationMark}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Previous Achievements</label>
                      <input type="text" class="form-control" value="${student.previousAchivement}" readonly="readonly">
                    </div>
                  </div>
                  <div class="col-md-12">&nbsp; </div>
                </div>
                <hr>
                <div class="row">
                  <div class="col-md-12">
                    <blockquote class="b-thin rounded primary">
                      <h3>Parent's Photo</h3>
                    </blockquote>
                  </div>
                  <div class="col-md-12">
                    <%-- <div class="thumbnail" style="width: 250px; float: left; margin-right: 15px; padding: 10px;">
                      <h5 class="mg-title text-weight-semibold text-center">Student</h5> 
                      <img src="" class="img-responsive">
                      <div class="fileupload fileupload-new" data-provides="fileupload">
						<div class="input-append">
							<div class="uneditable-input">
								<span class="fileupload-preview"></span>
								 <img id="preview" src="data:image/jpg;base64, ${student.resource.image.imageName}" style="width:225px;height:200px;"/>
							</div>
						</div>
					</div>
                    </div> --%>
                    <div class="thumbnail" style="width: 250px; float: left; margin-right: 15px; padding: 10px;">
                      <h5 class="mg-title text-weight-semibold text-center">Parents</h5> 
                      <img src="" class="img-responsive">
                    </div>
                  </div>
                </div>
                <hr>
                <div class="row">
                  <div class="col-md-12">&nbsp; </div>
                  <div class="col-md-3">
                    <blockquote class="b-thin rounded primary">
                      <h3>Father's Details</h3>
                    </blockquote>
                    <div class="form-group">
                      <label class="control-label">Name</label>
                      <input type="text" class="form-control" value="${student.resource.fatherFirstName}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Occupation</label>
                      <input type="text" class="form-control" value="${student.resource.fatherOccupation}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Annual Income</label>
                      <input type="text" class="form-control" value="${student.fatherIncome}" readonly="readonly">
                    </div>
                  </div>
                  <div class="col-md-3">
                    <blockquote class="b-thin rounded primary">
                      <h3>Mother's Details</h3>
                    </blockquote>
                    <div class="form-group">
                      <label class="control-label">Name</label>
                      <input type="text" class="form-control" value="${student.resource.motherFirstName}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Occupation</label>
                      <input type="text" class="form-control" value="${student.resource.motherOccupation}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Annual Income</label>
                      <input type="text" class="form-control" value="${student.motherIncome}" readonly="readonly">
                    </div>
                  </div>
                  <div class="col-md-3">
                    <blockquote class="b-thin rounded primary">
                      <h3>Guardian's Details</h3>
                    </blockquote>
                    <div class="form-group">
                      <label class="control-label">Name</label>
                      <input type="text" class="form-control" value="${student.guardianFirstName}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Address</label>
                      <input type="text" class="form-control" value="${student.address.guardianAddressLine}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">City/Village</label>
                      <input type="text" class="form-control" value="${student.address.guardianAddressCityVillage}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">District</label>
                      <input type="text" class="form-control" value="${student.address.guardianAddressDistrict}" readonly="readonly">
                    </div>
                  </div>
                  <div class="col-md-3">
                    <blockquote class="b-thin rounded primary">
                      <h3>Guardian's Details</h3>
                    </blockquote>
                    <div class="form-group">
                      <label class="control-label">Post Office</label>
                      <input type="text" class="form-control" value="${student.address.guardianAddressPostOffice}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Pin Code</label>
                      <input type="text" class="form-control" value="${student.address.guardianAddressPinCode}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">State</label>
                      <input type="text" class="form-control" value="${student.address.guardianAddressState}" readonly="readonly">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Country</label>
                      <input type="text" class="form-control" value="${student.address.guardianAddressCountry}" readonly="readonly">
                    </div>
                  </div>
                </div>
                <!-- <div class="row">
                  <div class="col-md-12">&nbsp; </div>
                  <div class="col-md-12">
                    <blockquote class="b-thin rounded primary">
                      <h3>If you belong to defence category,Please furnish the following details:</h3>
                    </blockquote>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <label class="control-label">Whether serving or Ex- service personnel</label>
                      <input type="text" class="form-control" name="firstname">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Service Number</label>
                      <input type="text" class="form-control" name="firstname">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Date of Enrolment/ Commission</label>
                      <div class="input-group"> <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
                        <input class="form-control" placeholder="__/__/____" data-input-mask="99/99/9999" data-plugin-masked-input="" id="date">
                      </div>
                    </div>
                    <div class="form-group">
                      <label class="control-label">Name of the Record Office</label>
                      <input type="text" class="form-control" name="firstname">
                    </div>
                  </div>
                  <div class="col-md-6">
                    <div class="form-group">
                      <label class="control-label">Service(Army/Navy/Air Force)</label>
                      <input type="text" class="form-control" name="firstname">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Rank</label>
                      <input type="text" class="form-control" name="firstname">
                    </div>
                    <div class="form-group">
                      <label class="control-label">Date of Discharge</label>
                      <div class="input-group"> <span class="input-group-addon"> <i class="fa fa-calendar"></i> </span>
                        <input class="form-control" placeholder="__/__/____" data-input-mask="99/99/9999" data-plugin-masked-input="" id="date">
                      </div>
                    </div>
                  </div>
                </div> -->
                <div class="row">
                  <div class="col-md-12">&nbsp;</div>
	                  <div class="col-md-5">
	                    <blockquote class="b-thin rounded primary">
	                      <h3>Present Address</h3>
	                    </blockquote>
	                    <div class="form-group">
	                      <label class="control-label">Address</label>
	                      <input type="text" class="form-control" value="${student.address.presentAddressLine}" readonly="readonly">
	                    </div>
	                    <div class="form-group">
	                      <label class="control-label">City/Village</label>
	                      <input type="text" class="form-control" value="${student.address.presentAddressCityVillage}" readonly="readonly">
	                    </div>
	                    <div class="form-group">
	                      <label class="control-label">District</label>
	                      <input type="text" class="form-control" value="${student.address.presentAddressDistrict}" readonly="readonly">
	                    </div>
	                    <div class="form-group">
	                      <label class="control-label">Post Office</label>
	                      <input type="text" class="form-control" value="${student.address.presentAddressPostOffice}" readonly="readonly">
	                    </div>
	                    <div class="form-group">
	                      <label class="control-label">Pin Code</label>
	                      <input type="text" class="form-control" value="${student.address.presentAddressPinCode}" readonly="readonly">
	                    </div>
	                    <div class="form-group">
	                      <label class="control-label">State</label>
	                      <input type="text" class="form-control" value="${student.address.presentAddressState}" readonly="readonly">
	                    </div>
	                    <div class="form-group">
	                      <label class="control-label">Country</label>
	                      <input type="text" class="form-control" value="${student.address.presentAddressCountry}" readonly="readonly">
	                    </div>
	                  </div>
	                  <div class="col-md-2" style="margin-top: 130px;">
	                    
	                  </div>
	                  <div class="col-md-5">
	                    <blockquote class="b-thin rounded primary">
	                      <h3>Permanent Address</h3>
	                    </blockquote>
	                    <div class="form-group">
	                      <label class="control-label">Address</label>
	                      <input type="text" class="form-control" value="${student.address.permanentAddressLine}" readonly="readonly">
	                    </div>
	                    <div class="form-group">
	                      <label class="control-label">City/Village</label>
	                      <input type="text" class="form-control" value="${student.address.permanentAddressCityVillage}" readonly="readonly">
	                    </div>
	                    <div class="form-group">
	                      <label class="control-label">District</label>
	                      <input type="text" class="form-control" value="${student.address.permanentAddressDistrict}" readonly="readonly">
	                    </div>
	                    <div class="form-group">
	                      <label class="control-label">Post Office</label>
	                      <input type="text" class="form-control" value="${student.address.permanentAddressPostOffice}" readonly="readonly">
	                    </div>
	                    <div class="form-group">
	                      <label class="control-label">Pin Code</label>
	                      <input type="text" class="form-control" value="${student.address.permanentAddressPinCode}"readonly="readonly">
	                    </div>
	                    <div class="form-group">
	                      <label class="control-label">State</label>
	                      <input type="text" class="form-control" value="${student.address.permanentAddressState}" readonly="readonly">
	                    </div>
	                    <div class="form-group">
	                      <label class="control-label">Country</label>
	                      <input type="text" class="form-control" value="${student.address.permanentAddressCountry}" readonly="readonly">
	                    </div>
	                  </div>
                	</div>
                <hr>
              </div>
              <div id="Academic" class="tab-pane">
                <div class="row">
                  <div class="col-sm-12">
                    <div class="clearfix"></div>
                  </div>
                </div>
              </div>
              <div id="Curricular" class="tab-pane"> </div>
              <div id="Games" class="tab-pane"> </div>
              <div id="NCC" class="tab-pane"> </div>
              <div id="Conduct" class="tab-pane"> </div>
              <div id="Medical" class="tab-pane"> </div>
            </div>
          </div>
        </div>
      </div>
		</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>