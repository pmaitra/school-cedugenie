$(document).ready(function(){
	//First time data insert for a particular section
	var eventid=0;	
	var eventid1 =0;
	var valueevene = "";
	var flag = 0;
	
	$("#popupBoxYes").click(function(){
		
		
		var regPositiveNum = /^[+]?([0-9]+(?:[\.][0-9]*)?|\.[0-9]+)$/;
		var noofPeriod = $("#schoolPeriodNo").val();	
		var splitedStartTime = $("#SchoolStartTime").val();
		splitedStartTime = splitedStartTime.split(":");
		var schoolStart = splitedStartTime[0];	
		if(noofPeriod == ""){
			flag = 1;
			$("#showErrorMsg").css('visibility','visible');
			$("#showErrorMsg").text('Enter No Of Period.');
		}
		if(schoolStart == ""){
			flag = 1;
			$("#showErrorMsg").css('visibility','visible');
			$("#showErrorMsg").text('Enter Start Time.');
		}
		if(noofPeriod != "" && schoolStart != ""){
			if(!noofPeriod.match(regPositiveNum)){
				flag = 1;
				$("#showErrorMsg").css('visibility','visible');
				$("#showErrorMsg").text('Enter Proper In Numeric.');
			}
			if(!schoolStart.match(regPositiveNum)){
				flag = 1;
				$("#showErrorMsg").css('visibility','visible');
				$("#showErrorMsg").text('Enter Proper Time In Numeric.');
			}
			if(noofPeriod.match(regPositiveNum) && schoolStart.match(regPositiveNum)){
				flag = 0;
				$("#showErrorMsg").css('visibility','collapse');
				$("#showErrorMsg").text('');
			}
			
		}
		if(flag == 0){
			
			$("#showShiftTime").css('visibility','visible');
			$("#showErrorMsg").css('visibility','collapse');
			$("#showErrorMsg").text('');
			$("#submitButtonAtFirst").css('visibility','visible');
			$("#editButton").css('visibility','visible');
			$("#subjectGroupTabId").css('visibility','visible');
			
					noofPeriod = (parseInt(schoolStart)+ parseInt(noofPeriod));
					
					$("#hiddenperiodtoset").val(noofPeriod);
					var i=0;		
					var date = new Date();
					var d = date.getDate();
					var m = date.getMonth();
					var y = date.getFullYear();
					var entertitle = "";
					var valueevene = "";
					var valTeacher = "";
					var eventid=0;	
					var eventTeacherId = 0;
					$('#calendar').remove();
					$( "#wrap" ).append( "<div id='calendar'></div>" );
					calendar = $('#calendar').fullCalendar({
						header: {
							left: '',
							center: 'title',
							right: ''
						},
						year:2013,
						month:8,
						date:22,
						allDay:false,
						droppable: true,
						drop: function(date, allDay) {
							var cellHour = date.getHours(); 
	                		if(date.getDay() != 0){
	              				$("#infomsgbox").css('visibility','collapse');
	    	    				$("#infomsg").text("");
	               				var originalEventTeacherUserId = $(this).data('eventTeacherUserUd');
			    				var originalEventType = $(this).data('eventType');
			    				var originalEventObject = $(this).data('eventObject');
			    				var copiedEventObject = $.extend({}, originalEventObject);
			    				copiedEventObject.start = date;
	              				 $.ajax({
	       		        	        url: '/cedugenie/getDurationForValidationForParticularSlot.html',
	       		        	        data: "standard=" + ($("#hiddenclasstoset").val()) + "&section=" + ($("#hiddensectiontoset").val()) +"&timeSlot=" + cellHour +"&year=" + ($("#radioYearId").val()),
	       		        	        dataType: 'json',
	       		        	        success: function(data) {
	       		        	        	if(data != null){
	       		        	        	$("#infomsgbox").css('visibility','collapse');
	    	    	    				$("#infomsg").text("");
	    	    	    				var subjectGroup = $("#subjectselected option:selected").val();
	    	    	    				
	    			    				copiedEventObject.allDay = allDay;
	    			    				//copiedEventObject.className = 'eventAll';
	    			    				 if(originalEventType != "Teacher"){
	    			    					copiedEventObject.id = eventid+"#"+subjectGroup;
	    			    					copiedEventObject.className = 'eventAll';
	    			    					copiedEventObject.bordercolor= 'black';
	    			    					eventid++;
	    			    				}
	    			    				if(originalEventType == "Teacher"){
	    			    					copiedEventObject.id = eventid;
	    			    					copiedEventObject.className = 'eventAllTeacher';
	    			    					copiedEventObject.bordercolor= originalEventTeacherUserId;
	    			    					eventTeacherId++;
	    			    				} 
	    			    				copiedEventObject.color = 'black';
	    			    				copiedEventObject.textColor = 'white';
	    			    				
	    			    				$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
	       		        	        	}
	       		        	        	if(data == null){
	       		        	        		$("#infomsgbox").css('visibility','visible');
	    	    	    					$("#infomsg").text("You can not assign subject before Duration slot.");
	       		        	        	}
	       		        	       }//end of success
	       		        		});// end of ajax 
	              				
	            				}
////							/////////////////////////////////////////////////////////////////////
		                
	               				if(date.getDay() == 0){
	               					$("#infomsgbox").css('visibility','visible');
	   	    	    				$("#infomsg").text("You can not assign subject in Duration slot.");
	               				}

						},
						
						timeFormat: '',
						firstHour: 11,
						minTime: schoolStart,
						maxTime: noofPeriod,
						dayClick: function(date, allDay, jsEvent, view) {
						 	var dayinn = date.getDay();
							$(this).css('background-color', 'pink');
						},
						selectable: true,
						selectHelper: true,
						select: function(start, end, allDay) {
							var eventColor = "#0066CC";
							var d = start.getDate();
						    var m = start.getMonth() + 1;
						    var y = start.getFullYear();
						    var day = start.getDay();
						    starthours   = +/ (\d+):/.exec(start)[1]; 
							startminutes = +/:(\d+):/.exec(start)[1]; 
									if(start.getDay() == 0){
										if(schoolStart == starthours){
											loadPopupBox1();
											$("#warningbox").css('visibility','collapse');
											$("#warningmsg").text("");
										}
										if(schoolStart != starthours){
											 $("#warningbox").css('visibility','visible');
											 $("#warningmsg").text("Please Enter the Duration Of First Period.");
										}
									} 
								eventsid = parseInt(eventid)+1;
								eventid++;
								$("#popupBoxNo1").click(function(){
									$("#dialog").hide();
									unloadPopupBox1();
									eventid--;
								});
								$("#hiddenStartTimetoset").val(starthours);
								$("#hiddenDaytoset").val(day);
					 			$("#hiddenDatetoset").val('' + (d <= 9 ? '0' + d : d) + '/' + (m<=9 ? '0' + m : m) + '/' + y);
					 			
								calendar.fullCalendar('unselect');
								i++;
							},
						editable: true,
						eventMouseover: function(calEvent, jsEvent, view) {
				              savBg = $(this).css("background-color");
				              savClr = $(this).css("color");
				              $(this).css( { color:'#FF3300', backgroundColor:"#27A9E3" } );
				              $(this).fadeTo('slow',.5);//.css(text-align,'right');
				          },
				         eventMouseout: function(calEvent, jsEvent, view) {
				              $(this).css( { color:savClr, backgroundColor:savBg } );
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
				    		eventAfterRender: function(event, element) {
				              var events = $(this).fullCalendar('clientEvents');
				              for(var i = 0; i < events.length; i++){
				            	    if(events[i].className == 'eventAll'){
				    					var str = events[i].start;
			    					 	starthours   = +/ (\d+):/.exec(str)[1]; 
			    					    startminutes = +/:(\d+):/.exec(str)[1]; 
			    					    valueevene= valueevene  + events[i].id + "," +  events[i].title + "," +  events[i].start.getDate() + "," +events[i].start.getMonth()+ "," + events[i].start.getFullYear()+ "," + starthours+ "," +events[i].start.getDay() +  "/";
			    						
				            	    }
				                    if(events[i].className == 'eventAllTeacher'){
				                    	valTeacher = valTeacher  +  events[i].bordercolor + "," +  events[i].start.getDate() + "," +events[i].start.getMonth()+ "," + events[i].start.getFullYear()+ "," + starthours+ "," +events[i].start.getDay() + "/";
				                    	
				                    }
				                 }
				        
				               $("#storeDraggedTeacherEventCount").val(eventTeacherId);
				               $("#storeDraggedTeacherEvent").val(valTeacher);
				               $("#storeDraggedEventCount").val(eventid);
				               $("#storeDraggedEvent").val(valueevene);
				               
				               
				            }     
				            });//end of calendar
				unloadPopupBox();
		}
			});// end of pop upbox submit
	
	
});