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
<title>Hostel Room</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class= "page-header">	<!-- Added by Saif 29-03-2018 -->
		<h2>Add Room in Hostel</h2>
	</header>
	
	<div class = "content-padding">
		<div class="row">
	<c:choose>
		<c:when test="${hostelNameList eq null && hostelNameList.size() eq 0}">
			<div class="alert alert-danger">
				<strong>No Hostel Found To Add room.</strong>	
			</div>
		</c:when>
	<c:otherwise>
	<div class="col-md-8 col-md-offset-2">
	  	<form id="room" name="room" action="addroomDetails.html" method="post">
			<section class="panel">
				<header class="panel-heading">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
					</div>

					<h2 class="panel-title">Add Room</h2>										
				</header>
				<div style="display: block;" class="panel-body">
                     <div class="row">
                         <div class="col-md-4" id="roomTable">
                             <div class="form-group">
                                 <label class="control-label">Hostel Name</label>
                                 <select id="hostelName" name="hostelName" size="1" class="form-control" required>
										<option value="">Select</option>
										<c:forEach var="hostelNameList" items="${hostelList}">
											<option value="${hostelNameList.hostelName}">${hostelNameList.hostelName}</option>
										</c:forEach>
								</select>
                             </div>
                             <div class="form-group">
                                 <label class="control-label">Room Type</label>
                                 <select name="roomTypeCode" id="roomTypeCode" size="1" class="form-control" required>
										<option value="">Select</option>
									<c:forEach var="roomType" items="${roomTypeList}">
										<option value="${roomType.roomTypeCode}">${roomType.roomTypeName}</option>
									</c:forEach>
								</select>
                             </div>
                             <div class="form-group">
                                <label class="control-label">Room No</label>
                                <div>
                           	 		<input type="text" id="roomName" name="roomName" class="textfield1 form-control"/>
                                </div>
							</div>
                             <div class="form-group">
                                 <label class="control-label">Room Position</label>
                                 <input type="text" id="roomDesc" name="roomDesc" class="textfield1 form-control"/>
                             </div>                                                
                         </div>
                         <div class="col-md-8">
                             <table class="table table-bordered table-striped mb-none" id="roomFacilityTable">
                                 <thead>
                                     <tr>
                                         <th style="background:#eee; text-align:center;">Select</th>
                                         <th style="background:#eee; text-align:center;">Enter Room Facility</th>
                                         <th style="background:#eee; text-align:center;">Quantity of the facility</th>
                                     </tr>
                                 </thead>
                                 <tbody>
                                     <tr>
                                         <td><input type="checkbox" id="checkboxExample1" checked=""></td>
                                         <td>
                                         	<input type="text" id = "roomFacilityName" name = "roomFacilityName" class="textfield1 form-control" value="Total Bed" readonly="readonly"/>
                                         </td>
                                         <td>
                                         	<input type="text" id = "bedTotal" name = "bedTotal" class="textfield1 form-control" value="0" onfocus="this.value='';"/>
                                       	</td>
                                     </tr>
                                     <c:forEach var="roomFacilityDetails" items="${roomFacilityList}">				
										 <tr>
											 <td>
											 	<input type="checkbox" id = "facilitySerialNo" class="textfield1" name = "roomFacilityCode" value="${roomFacilityDetails.roomFacilityCode}"/>
											 </td>						
											 <td>
											 	<input type="text" id = "roomFacilityName" class="textfield1 form-control" name = "${roomFacilityDetails.roomFacilityCode}" value="${roomFacilityDetails.roomFacilityName}"/>
											 </td>
											 <td>
											 	<input type="text" id = "roomFacilityQuantity" class="textfield1 form-control" name = "${roomFacilityDetails.roomFacilityCode}" value="0" onfocus="this.value='';"/>
											 </td>
										 </tr>
									 </c:forEach>
                                 </tbody>
                                 <!-- <tfoot>
                                     <tr>
                                         <td></td>
                                         <td><button class="btn btn-success btn-sm addbtn" id="addfacilityButton" onclick="addrows();">Add </button></td>
                                         <td><button class="btn btn-primary btn-sm editbtn" id="deletefacilityButton" onclick="deleteRow();">Delete </button></td>
                                     </tr>
                                 </tfoot> -->
                             </table>
                         </div>
                     </div>
				</div>
				<footer style="display: block;" class="panel-footer">
					<button class="btn btn-primary submitbtn" type="Submit" id="submit" name="submit">Submit </button>
					<button type="reset" class="btn btn-default clearbtn">Reset</button>
					<input type="hidden" id="addFacilityHide" name = "facilitydetails" value=""/>
				</footer>
			</section>
         </form>
	</div>
	</c:otherwise>
	</c:choose>
</div>
</div>


<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/icam/js/hostel/addRoom.js"></script>
</body>
</html>