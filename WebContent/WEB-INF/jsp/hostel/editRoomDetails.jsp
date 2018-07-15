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
<title>Edit Hostel Room</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class= "page-header">	<!-- Added by Saif 29-03-2018 -->
		<h2>Edit Rooms</h2>
	</header>
	<div class = "content-padding">
		<div class="row">
	<c:choose>
		<c:when test="${hostelFromDB eq null}">
			<div class="alert alert-danger">
				<strong>No Room Details Found To Edit.</strong>	
			</div>
		</c:when>
	<c:otherwise>
	<div class="col-md-8 col-md-offset-2">
	  	<form id="room" name="room" action="submitEditedRoomDetails.html" method="post">
			<section class="panel">
				<header class="panel-heading">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
					</div>

					<h2 class="panel-title">Edit Room</h2>
				</header>
				<div style="display: block;" class="panel-body">
                     <div class="row">
                         <div class="col-md-4" id="roomTable">
                             <div class="form-group">
                                 <label class="control-label">Hostel Name</label>
                                 <input type="text" id = "hostelName" name = "hostelName" class = "form-control" value="${hostelFromDB.room.hostelName}" disabled="disabled"/>
                             	  <input type="hidden" id = "oldHostelName" name = "oldHostelName" value="${hostelFromDB.room.hostelName}"/>
                             </div>
                             <div class="form-group">
                                 <label class="control-label">Room Type</label>
                                 <c:forEach var="roomType" items="${hostelFromDB.room.roomTypeList}"> 
									<input type="hidden" id="roomTypeCode" name="roomTypeCode" value="${hostelFromDB.room.roomTypeCode}"/>
									<input type="text" id="roomTypeName" name="roomTypeName" class = "form-control" value="${roomType.roomTypeName}" disabled="disabled"/>
								</c:forEach>
                             </div>
                             <div class="form-group">
                                <label class="control-label">Romm No</label>
                           	 	<input type="hidden" id="roomCode" name="roomCode" value="${hostelFromDB.room.roomCode}"/>
								<input type="text" id="roomName" name="roomName" class = "textfield1 form-control" value="${hostelFromDB.room.roomName}" disabled="disabled"/>
                             </div>
                             <div class="form-group">
                                 <label class="control-label">Room Position</label>
                                 <input type="text" id="roomDesc" name="roomDesc" class = "textfield1 form-control" value="${hostelFromDB.room.roomDesc}"  disabled="disabled"/>
                            	 <input type="hidden" id="oldRoomPosition" name="oldRoomPosition" value="${hostelFromDB.room.roomDesc}"/>
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
                                         <td><input type="checkbox" id="checkboxExample1" checked="" disabled="disabled"></td>
                                         <td>
                                         	<input type="text" id = "roomFacilityName" name = "roomFacilityName" class="form-control" value="Total Bed" disabled="disabled"/>
                                         </td>
                                         <td>
                                         	<input type="text" id = "bedTotal" name = "bedTotal" class="textfield1 form-control" value="${hostelFromDB.room.bedTotal}" disabled="disabled" />
                                       		<input type="hidden" id = "oldBedTotal" name = "oldBedTotal" value="${hostelFromDB.room.bedTotal}"/>	<!--  Added by Saif 26-03-2018 -->
                                       </td>
                                     </tr>
                                     <c:forEach var="roomfacility" items="${hostelFromDB.room.roomFacilityList}">
                                     	<c:if test="${roomfacility.status != null}">
											<tr>
												<td>
												 	<input type="checkbox" id = "facilitySerialNo" class="textfield1" name = "roomFacilityCode" value="${roomfacility.roomFacilityCode}" checked="" disabled="disabled"/>
												</td>
												<td>
												 	<input type="text" id = "roomFacilityName" class="textfield1 form-control" name = "roomFacilityName" value="${roomfacility.roomFacilityName}" disabled="disabled"/>
												</td>
												<td>
												 	<input type="text" id = "roomFacilityQuantity" class="textfield1 form-control" name = "roomFacilityQuantity" value="${roomfacility.roomFacilityQuantity}" disabled="disabled" onfocus="this.value='';"/>
												</td>
											</tr>
										</c:if>
										<c:if test="${roomfacility.status == null}">			
											<tr>
												<td>
												 	<input type="checkbox" id = "facilitySerialNo" class="textfield1" name = "roomFacilityCode" value="${roomfacility.roomFacilityCode}" disabled="disabled"/>
												</td>						
												<td>
												 	<input type="text" id = "roomFacilityName" class="textfield1 form-control" name = "roomFacilityName" value="${roomfacility.roomFacilityName}" disabled="disabled"/>
												</td>
												<td>
												 	<input type="text" id = "roomFacilityQuantity" class="textfield1 form-control" name = "roomFacilityQuantity" value="${roomfacility.roomFacilityQuantity}" disabled="disabled" onfocus="this.value='';"/>
												</td>
											</tr>
										</c:if>
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
					<button class="btn btn-danger submitbtn" type="button" id="edit">Edit</button>
					<button class="btn btn-primary submitbtn" type="Submit" id="submit" name="submit" style="display:none">Submit </button>
					<input type="hidden" id="addFacilityHide" name = "facilitydetails" value=""/>
					<input type="hidden" id="totalRoomFacilityCode" name = "totalRoomFacilityCode" value=""/>
				</footer>
			</section>
         </form>
	</div>
	</c:otherwise>
	</c:choose>
</div>
	</div>


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
	$("#edit").click(function(){
		$('#edit').css('display','none');
		$('#submit').css('display','block');
		$('.textfield1').removeAttr('disabled');
	});
	var retval = null; 
	var facilityDetails = "";
	$('#submit').click(function(){		
		var bedTotal = $('#bedTotal').val();		
		var namereg = /^[a-zA-Z]+( [a-zA-Z]+)*$/;		
		if(bedTotal == 0){
			/* document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Enter Total Bed!"; */
			alert("Please Enter Total Bed!");
			retval = false;
			return false;
		}
		bedTotal = parseInt(bedTotal);
		if(isNaN(bedTotal)){
			/* document.getElementById("warningbox").style.visibility='visible';				
			document.getElementById("warningmsg").innerHTML = "Please Enter Total Bed in neumerical format."; */
			alert("Please Enter Total Bed in neumerical format.");
			retval = false;
			return false;
		} 
		$('input:checkbox').each(function(){		
			if($(this).not(':checked')){
				/* document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML="Please Select atleast one Checkbox !";	 */
				alert("Please Select atleast one Checkbox !");
				retval = false;
				return false;
			}
		});		
		$('input:checkbox').each(function(){	
			if ($(this).is(':checked')){					
				//document.getElementById("warningbox").style.visibility='collapse';					
				var roomfacilitycode = $(this).val();				
	   			var roomfacilityname = $(this).parent().next().find('input:text').val();
	   			var roomfacilityquantity = $(this).parent().next().next().find('input:text').val();	 	   				
	  			if(roomfacilityname==""){	    
	  				/* document.getElementById("warningbox").style.visibility='visible';
	  				document.getElementById("warningmsg").innerHTML="Please Enter Room Type !"; */
	  				alert("Please Enter Room Type !");
	  				retval = false;
	  				return false;
	  			}	
	  			if((!namereg.test(roomfacilityname))){
	  				/* document.getElementById("warningbox").style.visibility='visible';
	  				document.getElementById("warningmsg").innerHTML="Please Enter Valid Room Type !"; */
	  				alert("Please Enter Valid Room Type !");
	  				retval = false;
	  				return false;
	 			 }
	  			if(roomfacilityquantity == 0){
	  				/* document.getElementById("warningbox").style.visibility='visible';
	  				document.getElementById("warningmsg").innerHTML="Please Enter Room Facility Quantity !"; */
	  				alert("Please Enter Room Facility Quantity !");
	  				retval = false;
	  				return false;
	  			}
	  			roomfacilityquantity = parseInt(roomfacilityquantity);
	  			if(isNaN(roomfacilityquantity)){
	  				/* document.getElementById("warningbox").style.visibility='visible';
	  				document.getElementById("warningmsg").innerHTML="Please Enter Room Facility Quantity in neumerical format !"; */
	  				alert("Please Enter Room Facility Quantity in numerical format !");
	  				retval = false;
	  				return false;
	  			}
	  			else{ 	  				
	  				//document.getElementById("warningbox").style.visibility='collapse';	  				
	  				facilityDetails = roomfacilitycode + "-" + roomfacilityname + "-" + roomfacilityquantity + "/" + facilityDetails;		
	  				$("#addFacilityHide").val(facilityDetails);
	  				retval = true;
	  				return true;
	  			}
			}
		});	
		return retval; 		    			
	});
</script>
</body>
</html>