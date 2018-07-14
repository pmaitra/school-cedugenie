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
<title>Resource Profile</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
<style type="text/css">
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
		<h2>Profile</h2>
	</header>
	<div class="content-padding">	
		<div class="row">
        <div class="col-xs-12">
        <input type="hidden" id="currentRole" name="currentRole" value="${currentRole}">
        <input type="hidden" id="currentUserId" name="currentUserId" value="${userId}">
          <div class="tabs tabs-primary">
            <div class="tab-content">
              <div id="Student" class="tab-pane active">
                <div class="row">
                  <div class="col-sm-12">
                    <div class="col-xs-3">
                      <ul class="nav nav-tabs tabs-left">
                        <li class="active"><a href="#Personal" data-toggle="tab">Personal Information</a></li>
                        <li id="ACADEMICS" style="display: none"><a href="#ACADEMICSTAB" data-toggle="tab">Academics</a></li>
                        <li id="LIBRARY" style="display: none"><a href="#LIBRARYTAB" data-toggle="tab">Library</a></li>
                        <li id="ADMISSION" style="display: none"><a href="#ADMISSIONTAB" data-toggle="tab">Admission</a></li>
                        <li id="ERP" style="display: none"><a href="#ERPTAB" data-toggle="tab">ERP</a></li>
                        <li id="FACILITY MANAGEMENT" style="display: none"><a href="#FACILITY MANAGEMENTTAB" data-toggle="tab">FACILITY MANAGEMENT</a></li>
                        <li id="GRADING SYSTEM" style="display: none"><a href="#GRADING SYSTEMTAB" data-toggle="tab">Grading System</a></li>
                        <li id="TICKETING" style="display: none"><a href="#TICKETINGTAB" data-toggle="tab">Ticketing</a></li>
                        <li id="HOSTEL" style="display: none"><a href="#HOSTELTAB" data-toggle="tab">Hostel</a></li>
                        <li id="FINANCE" style="display: none"><a href="#FINANCETAB" data-toggle="tab">Finance</a></li>
                        <li id="OFFICE ADMINISTRATION" style="display: none"><a href="#OFFICE ADMINISTRATIONTAB" data-toggle="tab">Office Administration</a></li>
                        <li id="SYSTEM ADMINISTRATION" style="display: none"><a href="#SYSTEM ADMINISTRATIONTAB" data-toggle="tab">System Administration</a></li>
                        <li id="INVENTORY" style="display: none"><a href="#INVENTORYTAB" data-toggle="tab">Inventory</a></li>
                        <li id="VENUE" style="display: none"><a href="#VENUETAB" data-toggle="tab">Venue</a></li>
                        <li id="REPORT" style="display: none"><a href="#REPORTTAB" data-toggle="tab">Report</a></li>
                      </ul>
                    </div>
                    <div class="col-xs-9 tab-right-content"> 
                      <div class="tab-content">
                        <div class="tab-pane active" id="Personal">
                          <h3>Personal Information</h3>
                          <div class="row well well-sm">
                            <div class="col-md-6">
                              <div class="form-group">
                                <label class="control-label">Name</label>
                                <input type="text" class="form-control" id="resourceName" name="resourceName" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">Father's Name</label>
                                <input type="text" class="form-control" id="resourceFatherName" name="resourceFatherName" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">Mother's Name</label>
                                <input type="text" class="form-control" id="resourceMotherName" name="resourceMotherName" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">Date Of Birth</label>
                                <input type="text" class="form-control" id="DOB" name="DOB" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">Email</label>
                                <input type="text" class="form-control" id="email" name="email" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">Gender</label>
                                <input type="text" class="form-control" id="gender" name="gender" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">Mobile No</label>
                                <input type="text" class="form-control" id="mobile" name="mobile" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">Aadhaar No</label>
                                <input type="text" class="form-control" id="aadhaarNo" name="aadhaarNo" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">Social Category</label>
                                <input type="text" class="form-control" id="socialCategory" name="socialCategory" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">Hostel</label>
                                <input type="text" class="form-control" id="hostel" name="hostel" readonly="readonly">
                              </div>
                            </div>
                            <div class="col-md-6">
                              <div class="form-group">
                                <label class="control-label">Address Line</label>
                                <input type="text" class="form-control" id="address" name="address" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">Post Office</label>
                                <input type="text" class="form-control" id="postOffice" name="postOffice" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">Postal Code</label>
                                <input type="text" class="form-control" id="postalCode" name="postalCode" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">Police Station</label>
                                <input type="text" class="form-control" id="policeStation" name="policeStation" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">Railway Station</label>
                                <input type="text" class="form-control" id="railWayStation" name="railWayStation" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">District</label>
                                <input type="text" class="form-control" id="district" name="district" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">City</label>
                                <input type="text" class="form-control" id="city" name="city" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">State</label>
                                <input type="text" class="form-control" id="state" name="state" readonly="readonly">
                              </div>
                              <div class="form-group">
                                <label class="control-label">Country</label>
                                <input type="text" class="form-control" id="country" name="country" readonly="readonly">
                              </div>
                            </div>
                            <div class="col-md-12">&nbsp;</div>
                            <div class="col-md-12">
                              <button class="btn btn-danger" type="button">Submit</button>
                            </div>
                          </div>
                        </div>
                        <div class="tab-pane" id="LIBRARYTAB">
                          <h3>Library Details</h3>
                        </div>
                        <div class="tab-pane" id="Fees">
                          <h3>Fees & Payment</h3>
                        </div>
                        <div class="tab-pane" id="Hostel">
                          <h3>Hostel & Mess</h3>
                        </div>
                        <div class="tab-pane" id="ACADEMICSTAB">
                          <h3>Academics</h3>
                        </div>
                        <div class="tab-pane" id="Ticketing">
                          <h3>Ticketing</h3>
                        </div>
                      </div>
                    </div>
                    <div class="clearfix"></div>
                  </div>
                </div>
              </div>
              <div id="Teacher" class="tab-pane"> 
                <div class="row">
                  <div class="col-sm-12">
                    <div class="col-xs-3"> 
                      <!-- required for floating --> 
                      <!-- Nav tabs -->
                      <ul class="nav nav-tabs tabs-left">
                        <li class="active"><a href="#Subject-teacher" data-toggle="tab">Subject & Exams</a></li>
                        <li><a href="#Personal-teacher" data-toggle="tab">Personal Information</a></li>
                        <li><a href="#Library-teacher" data-toggle="tab">Library & ERP</a></li>
                        <li><a href="#Hostel-teacher" data-toggle="tab">Hostel</a></li>
                        <li><a href="#Fees-teacher" data-toggle="tab">Fees & Payment</a></li>
                        <li><a href="#Ticketing-teacher" data-toggle="tab">Ticketing</a></li>
                      </ul>
                    </div>
                    <div class="col-xs-9 tab-right-content"> 
                      <!-- Tab panes -->
                      <div class="tab-content">
                        <div class="tab-pane active" id="Subject-teacher">
                          <h3>Subject & Exams</h3>
                              <table class="table table-bordered table-striped mb-none display">
                                <thead>
                                    <tr>
                                        <th>Date</th>
                                        <th>Order ID</th>
                                        <th>Vendor</th>
                                        <th>Amount</th>
                                        <th>Coupon Amount</th>                                                
                                        <th>Commission</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>                      
                                        <td>24/02/2017</td>
                                        <td><a href=""> 201702101807</a></td>
                                        <td>Supriya Tours & Travels (3333) <br>
                                          SUTAPA DAS (2220) 
                                        </td>
                                        <td align="right">100.00<br>
                                          80.00   
                                        </td>
                                        <td align="right">10.00</td>
                                        <td align="right">50.00</td>
                                        <td align="right">240.00</td>
                                    </tr>
                                    <tr>                      
                                        <td>16/02/2017</td>
                                        <td><a href=""> 501702104607</a></td>
                                        <td>SUTAPA DAS (2220) </td>
                                        <td align="right">200.00</td>
                                        <td align="right">0.00</td>
                                        <td align="right">70.00</td>
                                        <td align="right">270.00</td>
                                    </tr>
                                </tbody>
                              </table>
                              <hr>
                              <table class="table table-bordered table-striped mb-none display">
                                <thead>
                                    <tr>
                                        <th>Date</th>
                                        <th>Order ID</th>
                                        <th>Vendor</th>
                                        <th>Amount</th>
                                        <th>Coupon Amount</th>                                                
                                        <th>Commission</th>
                                        <th>Total</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>                      
                                        <td>24/02/2017</td>
                                        <td><a href=""> 201702101807</a></td>
                                        <td>Supriya Tours & Travels (3333) <br>
                                          SUTAPA DAS (2220) 
                                        </td>
                                        <td align="right">100.00<br>
                                          80.00   
                                        </td>
                                        <td align="right">10.00</td>
                                        <td align="right">50.00</td>
                                        <td align="right">240.00</td>
                                    </tr>
                                    <tr>                      
                                        <td>16/02/2017</td>
                                        <td><a href=""> 501702104607</a></td>
                                        <td>SUTAPA DAS (2220) </td>
                                        <td align="right">200.00</td>
                                        <td align="right">0.00</td>
                                        <td align="right">70.00</td>
                                        <td align="right">270.00</td>
                                    </tr>
                                </tbody>
                              </table>
                        </div>
                        <div class="tab-pane" id="Personal-teacher">
                          <h3>Personal Information</h3>
                        </div>
                        <div class="tab-pane" id="Library-teacher">
                          <h3>Library & ERP</h3>
                        </div>
                        <div class="tab-pane" id="Hostel-teacher">
                          <h3>Hostel</h3>
                        </div>
                        <div class="tab-pane" id="Fees-teacher">
                          <h3>Fees & Payment</h3>
                        </div>
                        <div class="tab-pane" id="Ticketing-teacher">
                          <h3>Ticketing</h3>
                        </div>
                      </div>
                    </div>
                    <div class="clearfix"></div>
                  </div>
                </div>
              </div>
              <div id="Non-Academic" class="tab-pane"> </div>
            </div>
          </div>
        </div>
      </div>
	</div>				
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/backoffice/viewResourceFullProfile.js"></script>
</body>
</html>