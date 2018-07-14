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
<meta name="description" content="Page To Create Assign Event" />
<meta name="keywords" content="Page To Create Assign Event" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Assign Event</title>

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/icam/css/backoffice/createAssignEvent.css" />

<!-- Calender (Existing fom old)-->
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script src='/icam/js/common/jquery-ui-1.10.2.custom.min.js'></script>
<script src='/icam/js/common/fullcalendar.min.js'></script>

<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>

<link href="/icam/css/common/notificationCalendar/jquery-ui-1.9.2.custom.css" rel="stylesheet">

<link href='/icam/css/common/calendar/fullcalendar.css' rel='stylesheet' />
<link href='/icam/css/common/calendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<!-- Calender -->

<script type="text/javascript" src="/icam/js/backoffice/createAssignEvent.js"></script>

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

	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
	var entertitle = "";
	var valueevene = "";
	var eventid=0;
	var count=0;
	var totalValSubmit = "";
	var strEventViewerVal = "";
	var strEventTypeVal = "";
	var strRollNameVal = "";
	var eventColor = "";
	calendar = $('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		year:startYearDigit,
		month:startMonthDigit,
		droppable: true,
		theme:true,
		aspectRatio: 1.5,
		drop: function(date, allDay) {
			var eventViewer = $("#eventViewer option:selected").val();
			var eventType = $("#eventTypeSelect option:selected").val();
            var rollName = $("#roleNames").val();
            if(rollName == ""){
            	rollName = "No Roll";
            } 
			if(eventViewer != "Select"){
				var dayint = date.getDay();
				//if(dayint !=0 && dayint !=6){
					 $("#infomsgbox").css('visibility', 'collapse');
		        	 $("#infomsgmsg").text("");
		        	 if(eventViewer == "All User"){
					   eventColor = "#FFFF00";	
		        	 }
		        	 if(eventViewer == "Roll Based"){
						eventColor = "#FF3300";	
			        }
		        	 if(eventViewer == "Personal"){
						eventColor = "#CC0033";	
			        }
		        	eventsid = parseInt(eventid)+1;
					eventid++;
					// retrieve the dropped element's stored Event Object
					var originalEventObject = $(this).data('eventObject');
					// we need to copy it, so that multiple events don't have a reference to the same object
					var copiedEventObject = $.extend({}, originalEventObject);
					copiedEventObject.id = eventid + "$$" +eventType+ "$$" +eventViewer+ "$$" +rollName;
					// assign it the date that was reported
					copiedEventObject.start = date;
					copiedEventObject.end = (date.getTime() + 900)/1000,
					copiedEventObject.allDay = allDay;
					
					// render the event on the calendar
					// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
					$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
					
					// is the "remove after drop" checkbox checked?
					if ($('#drop-remove').is(':checked')) {
						// if so, remove the element from the "Draggable Events" list
						$(this).remove();
					}
					copiedEventObject.color = eventColor;
					copiedEventObject.textColor = 'black';
					copiedEventObject.bordercolor= 'black';
					//copiedEventObject.title = "e";
					copiedEventObject.className = "eventAll";
					copiedEventObject.allDay=true;
					calendar.fullCalendar('renderEvent', copiedEventObject, true);
					/* if ($('#drop-remove').is(':checked')) {
						$(this).remove();
					} */
				//}	
				/*  if(dayint ==0 || dayint ==6){
					 $("#infomsgboxForEvent").css('visibility', 'visible');
		        	 $("#infomsgboxForEvent").text("sorry you can not assign any event");
				}  */
		}
			if(eventViewer == "Select"){
				 $("#infomsgboxForEvent").css('visibility', 'visible');
	        	 $("#infomsgboxForEvent").text("Please Select Event Viewer.");
			}
			if(eventViewer != "Select"){
				if(eventType == ""){
					 $("#infomsgboxForEvent").css('visibility', 'visible');
		        	 $("#infomsgboxForEvent").text("Please Select Event Type.");
				}
				if(eventType != ""){
					 $("#infomsgboxForEvent").css('visibility', 'collapse');
		        	 $("#infomsgboxForEvent").text("");
				}
			}
			$('#calendar').fullCalendar( 'gotoDate', date );
		},
		dayClick: function(date, allDay, jsEvent, view) {
			 $('#calendar').fullCalendar( 'gotoDate', date );
			  var dayinn = date.getDay();
			 $(this).css('background-color', 'pink');
			 $('#hidval').val(dayinn);  
		},
		selectable: true,
		selectHelper: true,
		select: function(start, end, allDay) {
			var eventViewer = $("#eventViewer option:selected").val();
			var eventType = $("#eventTypeSelect option:selected").val();
            var rollName = $("#roleNames").val();
            if(rollName == ""){
            	rollName = "No Roll";
            } 
			if(eventViewer != "Select"){
				
				$("#infomsgboxForEvent").css('visibility', 'collapse');
	        	$("#infomsgboxForEvent").text("");
				var ind =  $('#hidval').val();
				if(eventViewer == "All User"){
					eventColor = "#FFFF00";	
		        }
		        if(eventViewer == "Roll Based"){
					eventColor = "#FF3300";	
			    }
		        if(eventViewer == "Personal"){
					eventColor = "#CC0033";	
			    }
		        
				/*  if(ind == 0 || ind == 6){
					$("#infomsgboxForEvent").css('visibility', 'visible');
	        		$("#infomsgboxForEvent").text("sorry you can not assign any event");
				 }  */
				 //else{
					/* $("#infomsgboxForEvent").css('visibility', 'collapse');
		        	$("#infomsgboxForEvent").text(""); */
					var title = prompt('Event Title:');
					eventsid = parseInt(eventid)+1;
					eventid++;
				
					totalValSubmit = eventsid + "$$" +eventType+ "$$" +eventViewer+ "$$" +rollName;
					
					if (title === null || title === false) { // Canceled
						eventid--;
					}
					if (title) {
						calendar.fullCalendar('renderEvent',
							{
								id: totalValSubmit,
								title: title,
								start: start,
								className:"eventAll",
								end: (start.getTime() + 900)/1000,
								color:  eventColor,
	    						textColor : 'black',
	    						borderColor :'blue',
								allDay:true
							},
							true// make the event "stick"
						);
					}
					
					calendar.fullCalendar('unselect');
					i++;
				 //}
			}
			if(eventViewer == "Select"){
				$("#infomsgboxForEvent").css('visibility', 'visible');
	        	$("#infomsgboxForEvent").text("Please Select Event Viewer.");
			}
			if(eventViewer != "Select"){
				if(eventType == ""){
					 $("#infomsgboxForEvent").css('visibility', 'visible');
		        	 $("#infomsgboxForEvent").text("Please Select Event Type.");
				}
				if(eventType != ""){
					 $("#infomsgboxForEvent").css('visibility', 'collapse');
		        	 $("#infomsgboxForEvent").text("");
				}
			}
		},
		editable: true,
		eventMouseover: function(calEvent, jsEvent, view) {
              savBg = $(this).css("background-color");
              savClr = $(this).css("color");
              $(this).css( { color:'#ffff00', backgroundColor:"#006" } );
              //$(this).css('background-image', 'url(images/dustbin.png)');
              //$("#test").css( { color:'#ffff00', backgroundColor:"#006" } );
              $(this).fadeTo('slow',.5);//.css(text-align,'right');
          },
         eventMouseout: function(calEvent, jsEvent, view) {
              $(this).css( { color:savClr, backgroundColor:savBg } );
              //$(this).css('background-image', 'url(images/delete.png)');
              //$("#test").css( { color:savClr, backgroundColor:savBg } );
              $(this).fadeTo('slow',1);
          },
          eventClick: function(calEvent, jsEvent, view) {
           	  var title = prompt('Event Title:', calEvent.title, { buttons: { Ok: true, Delete: false} });
           	 
              if (title){
                  calEvent.title = title;
                  calEvent.className = "eventAll",
                  calendar.fullCalendar('updateEvent',calEvent);
                 
              }
    		},
    		eventResize: function(event,dayDelta,minuteDelta,revertFunc) {
    			alert(
    	            "The end date of " + event.title + "has been moved " +
    	            dayDelta + " days and " +
    	            minuteDelta + " minutes."
    	        );
    	        $('#calendar').fullCalendar( 'gotoDate', event.start );
    	        if (!confirm("is this okay?")) {
    	            revertFunc();
    	            $('#calendar').fullCalendar( 'gotoDate', event.start );
    	        }
		 },
    	 eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) {
				alert(
    	            event.title + " was moved " +
    	            dayDelta + " days and " +
    	            minuteDelta + " minutes."
    	        );
    	        if (allDay) {
    	        	 $('#calendar').fullCalendar( 'gotoDate', event.start );
    	            alert("Event is now all-day");
    	        }else{
    	            alert("Event has a time-of-day"+event.start);
    	        }

    	        if (!confirm("Are you sure about this change?")) {
    	            revertFunc();
    	            $('#calendar').fullCalendar( 'gotoDate', event.start );
    	        }
    	     
    	    },
    	    eventAfterRender: function(event, element) {
                var events = $(this).fullCalendar('clientEvents');
               	for(var i = 0; i < events.length; i++){
                    if(events[i].className == 'eventAll'){
                    	var totalValue = (events[i].id).split("$$");
                		strEventViewerVal = totalValue[2];
                		strEventTypeVal = totalValue[1];
                		strRollNameVal = totalValue[3];
                    	var str = events[i].start;
					 	var str1 = events[i].end;
					 	starthours   = +/ (\d+):/.exec(str)[1]; 
					    startminutes = +/:(\d+):/.exec(str)[1]; 
					  	endhours   = +/ (\d+):/.exec(str1)[1]; 
	    				endminutes = +/:(\d+):/.exec(str1)[1]; 
	    				var starttime = starthours+"$"+startminutes;
	    				var endtime = endhours+"$"+endminutes;
  					   	valueevene= valueevene  + totalValue[0] + "%%" +  events[i].title + "%%" +  events[i].start.getDate() + "%%" +events[i].start.getMonth()+ "%%" + events[i].start.getFullYear()+ "%%" + starttime+ "%%" +events[i].end.getDate() + "%%" + events[i].end.getMonth() + "%%" + events[i].end.getFullYear() + "%%" + endtime+ "%%" +events[i].color+ "%%" +strEventViewerVal + "%%" +strRollNameVal+ "%%" +strEventTypeVal+ "/";
                    }
            
              }
             
           $('#hiddenevent1').val(eventid);
           $('#hiddenevent').val(valueevene);
       }   
    });
	});
</script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Assign Event
	</h1>
</div>
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div>  
	
	<div class="infomsgbox" id="infomsgboxForEvent" >
		<span id="infomsgboxForEvent"></span>	
	</div>	
	<form name="eventForm" id="eventForm" method="POST" action="addCalenderEvent.html" >
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
							Event Viewer::
						</td>
						<td>
							<select id="eventViewer" name="eventTypeDesc" class="defaultselect">
								<option value="Select">Select</option>
								<option value="All User">All User</option>
								<option value="Roll Based">Roll Based</option>
								<option value="Personal">Personal</option>
							</select>
						</td>
						<td>
							Event Type::
						</td>
						<td>
							<select id="eventTypeSelect" name="eventTypeCode" class="defaultselect">
								<option value="" >Please Select</option>
									<c:forEach var="eventTypeList" items="${eventTypeList}">	
										<option value="${eventTypeList.eventTypeCode}">${eventTypeList.eventTypeName}</option>
									</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							Create Event::
						</td>
						<td>
							<input type="text" id="eventGenerator" name="eventGenerator" class="textfield2"/><input type="button"  class="addbtn" id="buttonEventGenerator" name="buttonEventGenerator" />
						</td>
						<td>
							<span id="labelText" style="visibility: collapse;">Role Name ::</span>
						</td>
						<td>
							<input style="visibility: collapse;" type="text" name="roleNames" id="roleNames" readonly="readonly" onmouseover="showDiv('Div${serial.index}');" onmouseout="hideDiv('Div${serial.index}');" class="textfield2" /><br>
								<div id="Div${serial.index}" class="multipleSelectDiv" onmouseout="hideDiv('Div${serial.index}');" onmouseover="showDiv('Div${serial.index}');" >	
									<c:forEach var="role" items="${roleList}">
									<span>
										<input type="checkbox" value="${role.roleName}" onclick="addText(this,'roleNames');"/>
										${role.roleName}
									</span><p>
									</c:forEach>
								</div>	
						</td>
					</tr>
				</table>
				
				<div id='wrap'>
					<div id='calendar'></div>
					<div style='clear:both'></div>
				</div>
				<div id='external-events' >
					<h4>Pre Defined Events</h4>
					<div class='external-event'>Parent Teacher Meeting</div>
					<div class='external-event'>Strike</div>
					<div class='external-event'>Surprise Test</div>
					<div class='external-event'>Staff Meeting</div>
					<div id='external-events1' >
					</div>
					<p>
						<input type='checkbox' id='drop-remove' /> <label for='drop-remove'>remove after drop</label>
					</p>
				</div>
				
				<div id="popup"></div>
				
				<div id="comformation" title="Basic dialog">
				Select Option::
					<input type="text" id="editedCalendarEventName" name="editedCalendarEventName" style="visibility: collapse;" />
					<input type=button id="popupBox1Yes" onclick="deleteAction();" class="submitbtn" value="Delete"></input>
					<input type="button" id="popupBox1No"  class="editbtn" value="Edit"/>
					<input type="button" id="cancelUpdate"  class="editbtn" value="Cancel"/>
					<input type="button" id="editButton"  class="editbtn" style="visibility: collapse;" onclick="editAction();" value="Edit"/>
				</div>
				
					<input type="submit" id="submitEvent" name="submitEvent" class="submitbtnIndividual"  value="Submit"/>
				
					<input type="hidden" id="hiddenevent" name="hiddenevent" />
					<input type="hidden" id="hiddenevent1" name="hiddenevent1" />
					<input type="hidden" id="hidval" name="hidval" />
				
				<input type="hidden" id="hiddencolorcode" name="hiddencolorcode"/>
				
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