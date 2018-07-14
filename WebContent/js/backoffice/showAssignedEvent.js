
function loadPopupBox() {    // To Load the Popupbox
    $('#comformation').fadeIn("fast");
    $("#wrap").css({ // this is just for style
        "opacity": "0.3"
     });  
  } 
function unloadPopupBox() {    // TO Unload the Popupbox
    $('#comformation').fadeOut("fast");
    $("#wrap").css({ // this is just for style       
        "opacity": "1" 
    });
} 

function unloadPopupBoxAnother(){ // TO Unload the Popupbox
    $('#comformationForDrop').fadeOut("fast");
    $("#wrap").css({ // this is just for style       
        "opacity": "1" 
    });
	
}

function loadPopupBoxAnother(){ // TO load the Popupbox
    $('#comformationForDrop').fadeIn("fast");
    $("#wrap").css({ // this is just for style
        "opacity": "0.3"
     });  
	
}

$(document).ready(function() {
	 var clorcode =165;
	 var clorcode1 =20;
	 var clorcode2 =42;
	// var cancelStatus = 0;
	$("#eventViewer").change(function(){
	 var eventViewer = $("#eventViewer option:selected").val();
	 var eventType = $("#eventTypeSelect option:selected").val();
	 if(eventViewer == "Roll Based"){
		 $("#roleNames").css('visibility','visible');
	 	 $("#labelText").css('visibility','visible');
	 	$('#calendar').remove();
	 	$( "#wrap" ).append( "<div id='calendar'></div>" );
	 	$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			theme:true,
			aspectRatio: 1.5,
			droppable: true,
			drop: function(date, allDay) {
				var originalEventObject = $(this).data('eventObject');
				var copiedEventObject = $.extend({}, originalEventObject);
				copiedEventObject.start = date;
				copiedEventObject.allDay = allDay;
				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
			},
			editable: false,
		}); 	//End fullcalendar
	 }
	 if(eventViewer == "All User"){
		 if(eventType == ""){
			 $("#infomsgAnother").css('visibility', 'visible');
			 $("#infomsgAnother").text("Select Event Type.");
		 }
		 if(eventType != ""){
	
				 $("#roleNames").css('visibility','collapse');
				 $("#labelText").css('visibility','collapse');
				 	$.ajax({
			    	    url: '/icam/getCalendarEventFromDBForAllUser.html',
			    	    	dataType: 'json',
			    	    	data: "eventType="+eventType,
			    	    	success: function(data) {
					    		$('#calendar').remove();
			    	    		var eventObject = null;
			    	    		var events=[];     
			    	    		var event=[];
			    	    		var calendarSpecificStaffEventArray = null;
			    	    		var calendarEventArray = null;
			    	    		var datedaystart = null;
			    	    		var datedayend = null;
			    	    		var perfectday = null;
			    	    		var startHour = null;
			    	    		var endHour = null;
			    	    		var eventcolor= null;
			    	    		$( "#wrap" ).append( "<div id='calendar'></div>" );
			    	    		$("#infomsgAnother").css('visibility', 'collapse');
			    	    		$("#infomsgAnother").text("");
			    	    		$('#calendar').fullCalendar({
			    	    			header: {
			    	    				left: 'prev,next today',
			    	    				center: 'title',
			    	    				right: 'month,agendaWeek,agendaDay'
			    	    			},
			    	    			theme:true,
			    	    			aspectRatio: 1.5,
			    	    			droppable: true,
			    	    			editable: false,	        	    			
			    	    			eventMouseover: function(calEvent, jsEvent, view) {
			    	    	              savBg = $(this).css("background-color");
			    	    	              savClr = $(this).css("color");
			    	    	              $(this).css( { color:'white', backgroundColor:"#006" } );
			    	    	 			  $(this).append(calEvent.tooltip);
			    	    	              $("#tooltip")
			    	    	              .css({position:'absolute', bottom: '30px'});
			    	    	            
			    	    	             /*  .css("top",(jsEvent.clientY - xOffset) + "px")
			    	    	              .css("left",(jsEvent.clientX + yOffset) + "px"); */  
			    	    						
			    	    	          },
			    	    	         eventMouseout: function(calEvent, jsEvent, view) {
			    	    	              $(this).css( { color:savClr, backgroundColor:savBg } );
			    	    	              //$("#test").css( { color:savClr, backgroundColor:savBg } );
			    	    	              $(this).fadeTo('slow',1);
			    	    	               $("#tooltip").remove();	 
			    	    	          },
			    	    	          eventClick: function(calEvent, jsEvent, view) {
			    	    	        	  var variable = calEvent.id;
			    	    	        	  $("#calendarIntEventCode").val(variable);
			    	    	        	  $("#editedCalendarEventName").val(calEvent.title);
			    	    	        	  loadPopupBox();
			    	    	               $(this).css( { color:'white', backgroundColor:"#006" } );
			    	    	              $(this).css('border-color', 'red');
			    	    	              
			    	    	          },
			    	    	          /*eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) {
				    	    				alert(
				    	        	            event.title + " was moved " +
				    	        	            dayDelta + " days and " +
				    	        	            minuteDelta + " minutes."
				    	        	        );
			    	    	        	  document.getElementById("showMsg").innerHTML = event.title + " was moved " + dayDelta + " days and " +  minuteDelta + " minutes.";
				    	    				
				    	        	        if (allDay) {
				    	        	        	 $('#calendar').fullCalendar( 'gotoDate', event.start );
				    	        	           // alert("Event is now all-day");
				    	        	        }else{
				    	        	           // alert("Event has a time-of-day"+event.start);
				    	        	        }
				    	        	        loadPopupBoxAnother();
				    	        	        alert("cancelStatus"+cancelStatus);
				    	        	        function check(){
				    	        	        	alert("cancelStatus"+cancelStatus);
				    	        	        }
				    	        	        if (cancelStatus != 0) {
				    	        	            revertFunc();
				    	        	            $('#calendar').fullCalendar( 'gotoDate', event.start );
				    	        	        }
				    	        	     
				    	        	    },*/
			    	    		}); 	//End fullcalendar	 
			    	    		
			    	    		
			    	    		if(data == null){
			    	    			$("#infomsgAnother").css('visibility', 'visible');
			    					$("#infomsgAnother").text("There is no value,Please select Add button to add events.");
			    					$("#addEvent").css('visibility', 'visible');
			    				}	 
			    	    		if(data != null){
			    	    			calendarEventArray = data.split("*");
			    		    	 	for(var calendarEventlist=0;calendarEventlist<calendarEventArray.length;calendarEventlist++)
			    		    			{		
			    		    	 		clorcode = clorcode + 8;
			    		    	 		clorcode1 = clorcode1 + 25;
			    		    	 		clorcode2 = clorcode2 + 45;
			    		    				calendarSpecificStaffEventArray = calendarEventArray[calendarEventlist].split(",");
			    		    						
			    		    						event[0] = calendarSpecificStaffEventArray[0]; 
			    		    						event[1] = calendarSpecificStaffEventArray[1];   
			    		    						event[2] = calendarSpecificStaffEventArray[2];         // its a date string.
			    		    						event[3] = calendarSpecificStaffEventArray[3];         // its a date string
			    		    					 	datedaystart =  calendarSpecificStaffEventArray[2].split("/");
			    		    					 	datedayend =  calendarSpecificStaffEventArray[3].split("/");
			    		    					 	startHour = calendarSpecificStaffEventArray[4].split("$");
			    		    					 	endHour = calendarSpecificStaffEventArray[5].split("$");
			    		    					 	eventcolor = calendarSpecificStaffEventArray[6];
			    		    					 
			    		    						perfectday = parseInt(datedayend[0]) + 1;
			    		    					 
			    		    						eventObject = new Object();
			    		    						eventObject.id=calendarSpecificStaffEventArray[0];
			    		    						eventObject.title = calendarSpecificStaffEventArray[1];
			    		    						eventObject.start =  new Date(datedaystart[2],datedaystart[1]-1, datedaystart[0],startHour[0],startHour[1],0);
			    		    						eventObject.end = new Date(datedayend[2],datedayend[1]-1,datedayend[0],endHour[0],endHour[1],0);
			    		    						
			    		    						if(endHour[0] == 0){
			    		    							eventObject.end = new Date(datedayend[2],datedayend[1]-1, datedayend[0],startHour[0],1,0);
			    		    						}
			    		    						eventObject.color = eventcolor;
			    		    						eventObject.textColor = 'black';
			    		    						var setStartMonth = eventObject.start.getMonth() + 1;
			    		    						var setEndMonth =eventObject.end.getMonth() + 1;
			    		    						
			    		    						eventObject.className=  "holiday";
			    		    						eventObject.tooltip= "<div id='tooltip' class='tooltipClass'><strong>" +eventObject.start.getDate()+"/"+setStartMonth+"/"+eventObject.start.getFullYear() +"-" + eventObject.end.getDate()+"/"+setEndMonth+"/"+eventObject.end.getFullYear()+ "</strong></div>";
			    		    						eventObject.allDay = false;  
			    		    						events.push(eventObject);    						    				   						    						
			    		    						$('#calendar').fullCalendar('renderEvent', eventObject, true);	
			    		    				 
			    		    			}   //End of first for loop
			    		    	 	
			    	    			}   //End if(data != "null")
			    	    	}   //End Success
			    	});    //End Ajax call 
		 	}
	 	}
	 
	 if(eventViewer == "Personal"){
		 
		 if(eventType == ""){
			 $("#infomsgAnother").css('visibility', 'visible');
			 $("#infomsgAnother").text("Select Event Type.");
		 }
		 if(eventType != ""){
				 $("#roleNames").css('visibility','collapse');
				 $("#labelText").css('visibility','collapse');
				 	$.ajax({
			    	    url: '/icam/getCalendarEventFromDBForPersonal.html',
			    	    	dataType: 'json',
			    	    	data: "eventType="+eventType,
			    	    	success: function(data) {
					    		$('#calendar').remove();
			    	    		var eventObject = null;
			    	    		var events=[];     
			    	    		var event=[];
			    	    		var calendarSpecificStaffEventArray = null;
			    	    		var calendarEventArray = null;
			    	    		var datedaystart = null;
			    	    		var datedayend = null;
			    	    		var perfectday = null;
			    	    		var startHour = null;
			    	    		var endHour = null;
			    	    		var eventcolor= null;
			    	    		$( "#wrap" ).append( "<div id='calendar'></div>" );
			    	    		$("#infomsgAnother").css('visibility', 'collapse');
			    	    		$("#infomsgAnother").text("");
			    	    		$('#calendar').fullCalendar({
			    	    			header: {
			    	    				left: 'prev,next today',
			    	    				center: 'title',
			    	    				right: 'month,agendaWeek,agendaDay'
			    	    			},
			    	    			theme:true,
			    	    			aspectRatio: 1.5,
			    	    			droppable: true,
			    	    			editable: false,	        	    			
			    	    			eventMouseover: function(calEvent, jsEvent, view) {
			    	    	              savBg = $(this).css("background-color");
			    	    	              savClr = $(this).css("color");
			    	    	              $(this).css( { color:'white', backgroundColor:"#006" } );
			    	    	 			  $(this).append(calEvent.tooltip);
			    	    	              $("#tooltip")
			    	    	              .css({position:'absolute', bottom: '30px'});
			    	    	            
			    	    	             /*  .css("top",(jsEvent.clientY - xOffset) + "px")
			    	    	              .css("left",(jsEvent.clientX + yOffset) + "px"); */  
			    	    						
			    	    	          },
			    	    	         eventMouseout: function(calEvent, jsEvent, view) {
			    	    	              $(this).css( { color:savClr, backgroundColor:savBg } );
			    	    	              //$("#test").css( { color:savClr, backgroundColor:savBg } );
			    	    	              $(this).fadeTo('slow',1);
			    	    	               $("#tooltip").remove();	 
			    	    	          },
			    	    	          eventClick: function(calEvent, jsEvent, view) {
			    	    	        	  var variable = calEvent.id;
			    	    	        	  $("#calendarIntEventCode").val(variable);
			    	    	        	  $("#editedCalendarEventName").val(calEvent.title);
			    	    	        	  loadPopupBox();
			    	    	               $(this).css( { color:'white', backgroundColor:"#006" } );
			    	    	              $(this).css('border-color', 'red');
			    	    	              
			    	    	          },
			    	    	          /*eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) {
				    	    				alert(
				    	        	            event.title + " was moved " +
				    	        	            dayDelta + " days and " +
				    	        	            minuteDelta + " minutes."
				    	        	        );
			    	    	        	  document.getElementById("showMsg").innerHTML = event.title + " was moved " + dayDelta + " days and " +  minuteDelta + " minutes.";
				    	    				
				    	        	        if (allDay) {
				    	        	        	 $('#calendar').fullCalendar( 'gotoDate', event.start );
				    	        	           // alert("Event is now all-day");
				    	        	        }else{
				    	        	           // alert("Event has a time-of-day"+event.start);
				    	        	        }
				    	        	        loadPopupBoxAnother();
				    	        	        alert("cancelStatus"+cancelStatus);
				    	        	        function check(){
				    	        	        	alert("cancelStatus"+cancelStatus);
				    	        	        }
				    	        	        if (cancelStatus != 0) {
				    	        	            revertFunc();
				    	        	            $('#calendar').fullCalendar( 'gotoDate', event.start );
				    	        	        }
				    	        	     
				    	        	    },*/
			    	    		}); 	//End fullcalendar	 
			    	    		
			    	    		
			    	    		if(data == null){
			    	    			$("#infomsgAnother").css('visibility', 'visible');
			    					$("#infomsgAnother").text("There is no value,Please select Add button to add events.");
			    					$("#addEvent").css('visibility', 'visible');
			    				}	 
			    	    		if(data != null){
			    	    			calendarEventArray = data.split("*");
			    		    	 	for(var calendarEventlist=0;calendarEventlist<calendarEventArray.length;calendarEventlist++)
			    		    			{		
			    		    	 		clorcode = clorcode + 8;
			    		    	 		clorcode1 = clorcode1 + 25;
			    		    	 		clorcode2 = clorcode2 + 45;
			    		    				calendarSpecificStaffEventArray = calendarEventArray[calendarEventlist].split(",");
			    		    						
			    		    						event[0] = calendarSpecificStaffEventArray[0]; 
			    		    						event[1] = calendarSpecificStaffEventArray[1];   
			    		    						event[2] = calendarSpecificStaffEventArray[2];         // its a date string.
			    		    						event[3] = calendarSpecificStaffEventArray[3];         // its a date string
			    		    					 	datedaystart =  calendarSpecificStaffEventArray[2].split("/");
			    		    					 	datedayend =  calendarSpecificStaffEventArray[3].split("/");
			    		    					 	startHour = calendarSpecificStaffEventArray[4].split("$");
			    		    					 	endHour = calendarSpecificStaffEventArray[5].split("$");
			    		    					 	eventcolor = calendarSpecificStaffEventArray[6];
			    		    					 
			    		    						perfectday = parseInt(datedayend[0]) + 1;
			    		    					 
			    		    						eventObject = new Object();
			    		    						eventObject.id=calendarSpecificStaffEventArray[0];
			    		    						eventObject.title = calendarSpecificStaffEventArray[1];
			    		    						eventObject.start =  new Date(datedaystart[2],datedaystart[1]-1, datedaystart[0],startHour[0],startHour[1],0);
			    		    						eventObject.end = new Date(datedayend[2],datedayend[1]-1,datedayend[0],endHour[0],endHour[1],0);
			    		    						
			    		    						if(endHour[0] == 0){
			    		    							eventObject.end = new Date(datedayend[2],datedayend[1]-1, datedayend[0],startHour[0],1,0);
			    		    						}
			    		    						eventObject.color = eventcolor;
			    		    						eventObject.textColor = 'black';
			    		    						var setStartMonth = eventObject.start.getMonth() + 1;
			    		    						var setEndMonth =eventObject.end.getMonth() + 1;
			    		    						
			    		    						eventObject.className=  "holiday";
			    		    						eventObject.tooltip= "<div id='tooltip' class='tooltipClass'><strong>" +eventObject.start.getDate()+"/"+setStartMonth+"/"+eventObject.start.getFullYear() +"-" + eventObject.end.getDate()+"/"+setEndMonth+"/"+eventObject.end.getFullYear()+ "</strong></div>";
			    		    						eventObject.allDay = false;  
			    		    						events.push(eventObject);    						    				   						    						
			    		    						$('#calendar').fullCalendar('renderEvent', eventObject, true);	
			    		    				 
			    		    			}   //End of first for loop
			    		    	 	
			    	    			}   //End if(data != "null")
			    	    	}   //End Success
			    	});    //End Ajax call 
		 	}
	 	}
	});
	
	$("#roleNames").change(function(){
		 var rollName = $("#roleNames option:selected").text();
		 var eventType = $("#eventTypeSelect option:selected").val();
		 if(rollName == ""){
			 $("#infomsgAnother").css('visibility', 'visible');
			 $("#infomsgAnother").text("Please Select Any Roll");
		 }
		 if(rollName != ""){
			 
			 if(eventType == ""){
				 $("#infomsgAnother").css('visibility', 'visible');
				 $("#infomsgAnother").text("Please Select Event Type");
			 }
			 if(eventType != ""){
				 
				 $("#infomsgAnother").css('visibility', 'collapse');
				 $("#infomsgAnother").text("");
				 	$.ajax({
			    	    url: '/icam/getCalendarEventFromDBForRoleBased.html',
			    	    	dataType: 'json',
			    	    	data: "rollName="+rollName+ "&eventType=" + eventType,
			    	    	success: function(data) {
					    		$('#calendar').remove();
			    	    		var eventObject = null;
			    	    		var events=[];     
			    	    		var event=[];
			    	    		var calendarSpecificStaffEventArray = null;
			    	    		var calendarEventArray = null;
			    	    		var datedaystart = null;
			    	    		var datedayend = null;
			    	    		var perfectday = null;
			    	    		var startHour = null;
			    	    		var endHour = null;
			    	    		var eventcolor= null;
			    	    		$( "#wrap" ).append( "<div id='calendar'></div>" );
			    	    		$("#infomsgAnother").css('visibility', 'collapse');
			    	    		$("#infomsgAnother").text("");
			    	    		$('#calendar').fullCalendar({
			    	    			header: {
			    	    				left: 'prev,next today',
			    	    				center: 'title',
			    	    				right: 'month,agendaWeek,agendaDay'
			    	    			},
			    	    			theme:true,
			    	    			aspectRatio: 1.5,
			    	    			droppable: true,
			    	    			drop: function(date, allDay) {
			    	    				var originalEventObject = $(this).data('eventObject');
			    	    				var copiedEventObject = $.extend({}, originalEventObject);
			    	    				copiedEventObject.start = date;
			    	    				copiedEventObject.allDay = allDay;
			    	    				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
			    	    				if ($('#drop-remove').is(':checked')) {
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
			    	    	              .css({position:'absolute', bottom: '30px'});
			    	    	            
			    	    	             /*  .css("top",(jsEvent.clientY - xOffset) + "px")
			    	    	              .css("left",(jsEvent.clientX + yOffset) + "px"); */  
			    	    						
			    	    	          },
			    	    	         eventMouseout: function(calEvent, jsEvent, view) {
			    	    	              $(this).css( { color:savClr, backgroundColor:savBg } );
			    	    	              //$("#test").css( { color:savClr, backgroundColor:savBg } );
			    	    	              $(this).fadeTo('slow',1);
			    	    	               $("#tooltip").remove();	 
			    	    	          },
			    	    	          eventClick: function(calEvent, jsEvent, view) {
			    	    	        	  var variable = calEvent.id;
			    	    	        	  $("#calendarIntEventCode").val(variable);
			    	    	        	  $("#editedCalendarEventName").val(calEvent.title);
			    	    	        	  loadPopupBox();
			    	    	              $(this).css( { color:'white', backgroundColor:"#006" } );
			    	    	              $(this).css('border-color', 'red');
			    	    	              
			    	    	          }
			    	    		}); 	//End fullcalendar	 
			    	    		
			    	    		
			    	    		if(data == null){
			    	    			$("#infomsgAnother").css('visibility', 'visible');
			    					$("#infomsgAnother").text("There is no value,Please select Add button to add events.");
			    					$("#addEvent").css('visibility', 'visible');
			    				}	 
			    	    		if(data != null){
			    	    			calendarEventArray = data.split("*");
			    		    	 	for(var calendarEventlist=0;calendarEventlist<calendarEventArray.length;calendarEventlist++)
			    		    			{		
			    		    	 		clorcode = clorcode + 8;
			    		    	 		clorcode1 = clorcode1 + 25;
			    		    	 		clorcode2 = clorcode2 + 45;
			    		    				calendarSpecificStaffEventArray = calendarEventArray[calendarEventlist].split(",");
			    		    						
			    		    						event[0] = calendarSpecificStaffEventArray[0]; 
			    		    						event[1] = calendarSpecificStaffEventArray[1];   
			    		    						event[2] = calendarSpecificStaffEventArray[2];         // its a date string.
			    		    						event[3] = calendarSpecificStaffEventArray[3];         // its a date string
			    		    					 	datedaystart =  calendarSpecificStaffEventArray[2].split("/");
			    		    					 	datedayend =  calendarSpecificStaffEventArray[3].split("/");
			    		    					 	startHour = calendarSpecificStaffEventArray[4].split("$");
			    		    					 	endHour = calendarSpecificStaffEventArray[5].split("$");
			    		    					 	eventcolor = calendarSpecificStaffEventArray[6];
			    		    					 
			    		    						perfectday = parseInt(datedayend[0]) + 1;
			    		    					 
			    		    						eventObject = new Object();
			    		    						eventObject.id=calendarSpecificStaffEventArray[0];
			    		    						eventObject.title = calendarSpecificStaffEventArray[1];
			    		    						eventObject.start =  new Date(datedaystart[2],datedaystart[1]-1, datedaystart[0],startHour[0],startHour[1],0);
			    		    						eventObject.end = new Date(datedayend[2],datedayend[1]-1,datedayend[0],endHour[0],endHour[1],0);
			    		    						
			    		    						if(endHour[0] == 0){
			    		    							eventObject.end = new Date(datedayend[2],datedayend[1]-1, datedayend[0],startHour[0],1,0);
			    		    						}
			    		    						eventObject.color = eventcolor;
			    		    						eventObject.textColor = 'black';
			    		    						var setStartMonth = eventObject.start.getMonth() + 1;
			    		    						var setEndMonth =eventObject.end.getMonth() + 1;
			    		    						
			    		    						eventObject.className=  "holiday";
			    		    						eventObject.tooltip= "<div id='tooltip' class='tooltipClass'><strong>" +eventObject.start.getDate()+"/"+setStartMonth+"/"+eventObject.start.getFullYear() +"-" + eventObject.end.getDate()+"/"+setEndMonth+"/"+eventObject.end.getFullYear()+ "</strong></div>";
			    		    						eventObject.allDay = false;  
			    		    						events.push(eventObject);    						    				   						    						
			    		    						$('#calendar').fullCalendar('renderEvent', eventObject, true);	
			    		    				 
			    		    			}   //End of first for loop
			    		    	 	
			    	    			}   //End if(data != "null")
			    	    	}   //End Success
			    	});    //End Ajax call 
		 	}
		 }
	});

	$("#cancelUpdate").click(function(){
		unloadPopupBox();
	});
	$("#cancelChange").click(function(){
	//	cancelStatus = 1;
		unloadPopupBoxAnother();
	});
	$("#editButton").click(function(){
		var editedTitle = $("#editedCalendarEventName").val();
		$("#calendarEventName").val(editedTitle);
	});
});