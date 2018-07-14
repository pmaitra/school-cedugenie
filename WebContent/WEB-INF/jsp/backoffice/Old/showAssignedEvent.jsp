<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page To Show And Update Assigned Event" />
<meta name="keywords" content="Page To Show And Update Assigned Event" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Show And Update Assigned Event</title>

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/icam/css/backoffice/showAssignedEvent.css" />

<!-- Calender (Existing fom old)-->
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script src='/icam/js/common/jquery-ui-1.10.2.custom.min.js'></script>
<script src='/icam/js/common/fullcalendar.min.js'></script>

<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>

<link href="/icam/css/common/notificationCalendar/jquery-ui-1.9.2.custom.css" rel="stylesheet">

<link href='/icam/css/common/calendar/fullcalendar.css' rel='stylesheet' />
<link href='/icam/css/common/calendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<!-- Calender -->

<script type="text/javascript" src="/icam/js/backoffice/showAssignedEvent.js"></script>

<script>

$(document).ready(function() {
	
	var startYearDigit = "";
	var startMonthDigit = "";
	var sizeOfYear = "${year}";
	if(sizeOfYear != ""){
		 var termStartActual = "<c:out value="${year.sessionStartDate}"/>" ;
		 var termEndActual = "<c:out value="${year.sessionEndDate}"/>" ;
		 var yearSplitArray = termStartActual.split("/"); 
		 var yearenddateSplitArray = termEndActual.split("/"); 
		startMonthDigit = parseInt(yearSplitArray[1]);
		startYearDigit = parseInt(yearSplitArray[2]);
		startMonthDigit = startMonthDigit - 1;
		$("#hiddenYearstart").val(termStartActual);
		$("#hiddenYearend").val(termEndActual);
	}
	
	if(sizeOfYear == ""){
		var dateAnother = new Date();
		var d = dateAnother.getDate();
		var m = dateAnother.getMonth();
		var y = dateAnother.getFullYear();
		startMonthDigit = m;
		startYearDigit = y;
	}

	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		year:startYearDigit,
		month:startMonthDigit,
		theme:true,
		aspectRatio: 1.5,
		droppable: true,
		drop: function(date, allDay) {
			var originalEventObject = $(this).data('eventObject');
			var copiedEventObject = $.extend({}, originalEventObject);
			copiedEventObject.start = date;
			copiedEventObject.allDay = allDay;
			$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
			if ($('#drop-remove1').is(':checked')) {
				$(this).remove();
			}
			
		},
		editable: false,
		eventMouseover: function(calEvent, jsEvent, view) {
              savBg = $(this).css("background-color");
              savClr = $(this).css("color");
              $(this).css( { color:'white', backgroundColor:"#006" } );
              $(this).append(calEvent.tooltip);
              $("#tooltip")
              .css('position','absolute');
             /*  .css("top",(jsEvent.clientY - xOffset) + "px")
              .css("left",(jsEvent.clientX + yOffset) + "px"); */  
					
          },
         eventMouseout: function(calEvent, jsEvent, view) {
              $(this).css( { color:savClr, backgroundColor:savBg } );
              //$("#test").css( { color:savClr, backgroundColor:savBg } );
              $(this).fadeTo('slow',1);
              $("#tooltip").remove();	
          },
          
	}); 	//End fullcalendar	 
	});
</script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Show And Update Assigned Event
	</h1>
</div>
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div>  
	<div class="infomsgbox" id="infomsgAnother" >
		<span id="infomsgAnother"></span>	
	</div>	
	<div class="infomsgbox" id="infomsgboxForEvent" >
		<span id="infomsgboxForEvent"></span>	
	</div>	
	<form name="eventForm" id="eventForm" method="POST" action="updateCalenderEvent.html" >
		<c:choose>
			<c:when test="${year eq null || empty year
			 				|| roleList eq null || empty roleList 
			 				|| eventTypeList eq null || empty eventTypeList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<c:if test="${year eq null || empty year}">
							<span id="errormsg">Academic Year Not Found</span>	
						</c:if>
						<c:if test="${roleList eq null || empty roleList}">
							<span id="errormsg">Roles not found</span>	
						</c:if>
						<c:if test="${eventTypeList eq null || empty eventTypeList}">
							<span id="errormsg">Event Types not found</span>	
						</c:if>
					</div>
				</div>
			</c:when>
			<c:otherwise>		
				<table class="midsec">
					<tr>
						<td>
							Event Type::
						</td>
						<td>
							<select id="eventTypeSelect" name="eventTypeCode" class="defaultselect">
								<option value="">Please Select</option>
									<c:forEach var="eventTypeList" items="${eventTypeList}">	
										<option value="${eventTypeList.eventTypeCode}">${eventTypeList.eventTypeName}</option>
									</c:forEach>
							</select>
						</td>
						<td>
							Event Viewer::
						</td>
						<td>
							<select id="eventViewer" name="eventTypeDesc" class="defaultselect">
								<option value="">Select</option>
								<option value="All User">All User</option>
								<option value="Roll Based">Roll Based</option>
								<option value="Personal">Personal</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<span id="labelText" style="visibility: collapse;">Role Name ::</span>
						</td>
						<td>
							<select id="roleNames" style="visibility: collapse;" name="roleNames" class="defaultselect">
								<option value="">Select</option>
								<c:forEach var="role" items="${roleList}">
									<option value="${role.roleName}">${role.roleName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				
				<div id='wrap'>
					<div id='calendar'></div>
					<div style='clear:both'></div>
				</div>
				<div id="popup"></div>
				
				<div id="comformation" title="Basic dialog">
				Select Option::
					<input type="text" id="editedCalendarEventName" name="editedCalendarEventName"  />
					<input type=submit id="deleteButton" name="deleteButton" class="submitbtn" value="Delete"></input>
					<input type="submit" id="editButton" name="editButton" class="editbtn" value="Edit"/>
					<input type="button" id="cancelUpdate"  class="editbtn" value="Cancel"/>	
				</div>
				
				<div id="comformationForDrop" title="Basic dialog">
				<label id="showMsg">cxf</label>
					<input type="submit" id="okButton" name="okButton" class="editbtn" value="Ok"/>
					<input type="button" id="cancelChange"  class="editbtn" value="Cancel"/>	
				</div>
				
				<div class="btnsarea01">
					<input type="submit" id="addEvent" name="addEvent"  class="submitbtn" value="AddEvent" style="visibility: collapse;"/>
				</div>
				
				<input type="hidden" id="calendarIntEventCode" name="calendarIntEventCode" />
				<input type="hidden" id="calendarEventName" name="calendarEventName" />
				<input type="hidden" id="hiddenYearstart" name="hiddenYearstart" value="">
				<input type="hidden" id="hiddenYearend" name="hiddenYearend" value="">
			</c:otherwise>	
		</c:choose>
	</form>	
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
</body>
</html>