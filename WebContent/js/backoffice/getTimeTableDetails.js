$(document).ready(function(){
var flagForValidation = 0;
var eventid=0;	
var eventid1 =0;
var valueevene = "";
var flag = 0;
	// result after checnging the section
	$("#hiddensectiontoset").change(function(){	
		$("#hiddenSectiontoset").val($(this).val());
		var sectionForValidation = $(this).val();
		var startArray = null;
		var schlStartTime = "";
		var periodStartForValidation = "";
		var insertedStatus = "";
		var noperiod;
		var valTeacher = "";
		var eventTeacherId = 0;
	
		// ajax call for fetching subjectGroup and timeTableDetails  For particular class and course and stream  and sectionon change of "hiddensectiontoset" select box
		var academicTimeTable = { timeTableName:"sayani", timeTableCode:"Datta" };
	//	alert("in");
		////////////////////////////////////////////////////////////////////////////////////////////
		var classNameString = $("#hiddenclasstoset").val();
		var sectionNameString = $("#hiddensectiontoset").val();
		var academicYearString = $("#radioYearId").val();
		var events3=[];
    	var individualArray = null;
		
		
		
    	backOfficeService.getTimeTableTotalDetails(classNameString,sectionNameString,academicYearString,{callback: function(data)
	         {
	        
	         if(data == ""){
	        	
	            	if(sectionForValidation != ""){
	            		$("#showShiftTime").css('visibility','collapse');
	            		$("#subjectGroupTabId").css('visibility','collapse');
	            		$("#CountDiv").css('visibility','collapse');
	            		$("#CountTable").css('visibility','collapse');
	            		$("#submitButtonAtFirst").css('visibility','collapse');
	            		$("#external-events").css('visibility','collapse');
	            		$("#subjectTabId").css('visibility','collapse');
	            		$("#external-events-another").css('visibility','collapse');
	            		$("#editButton").css('visibility','collapse');
	            		$("#warningbox").css('visibility','collapse');
	    				$("#warningmsg").text("");
	    				$("#showErrorMsg").css('visibility','collapse');
	    				$("#showErrorMsg").text('');
	    				loadPopupBox();	
	            		$('#calendar').remove();
	            		}
	            	if(sectionForValidation == ""){
	            		$("#showShiftTime").css('visibility','collapse');
	            		$("#subjectGroupTabId").css('visibility','collapse');
	            		$("#CountDiv").css('visibility','collapse');
	            		$("#CountTable").css('visibility','collapse');
	            		$("#sidebar").css('visibility','collapse');
	            		$("#submitButtonAtFirst").css('visibility','collapse');
	            		$("#external-events").css('visibility','collapse');
	            		$("#subjectTabId").css('visibility','collapse');
	            		$("#external-events-another").css('visibility','collapse');
	            		$("#editButton").css('visibility','collapse');
	            		$("#warningbox").css('visibility','visible');
	    				$("#warningmsg").text("Select Section");
	    				$('#calendar').remove();
	            	}
	         }// end null
	         
	         if(data != ""){
	        	 	//$("#dialog").css('visibility','collapse');
	        	 	$("#subjectGroupTabId").css('visibility','visible');
	        		$("#CountDiv").css('visibility','visible');
	        		$("#CountTable").css('visibility','visible');
	        		$("#editButton").css('visibility','visible');
	        		$("#submitButtonAtFirst").css('visibility','visible');
	        		$("#showShiftTime").css('visibility','visible');
	        		
	        		$("#warningbox").css('visibility','collapse');
    				$("#warningmsg").text("");
    				
    				$('#CountTable tbody').remove();
    				 var row = "";
    				
    				
    				 backOfficeService.getTimeTableSubjectsCount(classNameString,sectionNameString,academicYearString,{callback: function(subjectCountData)
    			         {
    					if(subjectCountData != ""){
    						$('#CountDiv').css('visibility','visible');
	        				$('#CountTable thead').css('visibility','visible');
	        				 $.each(subjectCountData, function() {
	        					  row = $('<tr>'); 
		    	                  row.append($('<td></td>').html(this.timeTableDetailsSubjectName));
		    	                  row.append($('<td></td>').html(this.timeTableDetailsSubjectCount));
		    	                  $('#CountTable').append(row);	
	        		           });
		        				  
    					}
    					if(subjectCountData == ""){
    						$('#CountDiv').css('visibility','collapse');
    		   				$('#CountTable').css('visibility','collapse');
    					}
    			         }});  
    				
    				
    				
    				 $.each(data, function() {
    					 noperiod = this.totalSlot;
     					 $("#hiddenperiodtoset").val(noperiod);
	        				$('#calendar').remove();
	                		$( "#wrap" ).append( "<div id='calendar'></div>" );
	                		var innerList = this.listAcademicTimeTableDetails;
	                		
	                		 $.each(innerList, function() {
	                			 schlStartTime = this.individualSlot;
			            		 periodStartForValidation = this.schoolStartTime;
			            		 insertedStatus = this.status;
	                		 });
    		           });
    				
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
      		        	        url: '/icam/getDurationForValidationForParticularSlot.html',
      		        	        data: "standard=" + ($("#hiddenclasstoset").val()) + "&section=" + ($("#hiddensectiontoset").val()) +"&timeSlot=" + cellHour +"&year=" + ($("#radioYearId").val()),
      		        	        dataType: 'json',
      		        	        success: function(data) {
      		        	        	if(data != null){
      		        	        		$("#infomsgbox").css('visibility','collapse');
		    	    	    				$("#infomsg").text("");
		    	    	    			var subjectGroup = $("#subjectselected option:selected").val();
      		        	        		eventsid = parseInt(eventid)+1;
  		                				eventid++;
  		                				
  		                				copiedEventObject.allDay = allDay;
  		                				copiedEventObject.color = 'black';
  		                				copiedEventObject.textColor = 'white';
  		                				if(originalEventType != "Teacher"){
  		                					copiedEventObject.id = eventid+"#"+subjectGroup;
  		        	    					copiedEventObject.className = 'eventAll';
  		        	    					copiedEventObject.bordercolor= 'black';
  		        	    					eventid1++;
  		        	    					$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
  		        	    				}
  		        	    				if(originalEventType == "Teacher"){
  		        	    					copiedEventObject.id = eventid;
  		        	    					var splitedPeriodTime = data.split("~");
  			        					 	for(var index = 0;index<splitedPeriodTime.length-1;index++){
  			        					 		var  splitedTime = splitedPeriodTime[index].split(",");
  			        					 		$.ajax({
	     			         		        	        url: '/icam/getTeacherConflictionForTimeTable.html',
	     			         		        	        data: "standard=" + ($("#hiddenclasstoset").val()) + "&section=" + ($("#hiddensectiontoset").val()) +"&daySlot=" + (date.getDay()) +"&year=" + ($("#radioYearId").val()) +"&userId=" + originalEventTeacherUserId+"&periodStartTime=" + splitedTime[0]+"&periodEndTime=" + splitedTime[1],
	     			         		        	        dataType: 'json',
	     			         		        	        success: function(data) {
	     			         		        	        	if(data == null){      	   
	     			         		        	        		copiedEventObject.className = 'eventAllTeacher';
	    		     		        	    					copiedEventObject.bordercolor= originalEventTeacherUserId;
	    		     		        	    					eventTeacherId++;
	    			         		        	        		$('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
	     			         		        	        	}
	     			         		        	        	if(data != null){
	     			         		        	        		$("#infomsgbox").css('visibility','visible');
	     					    	    	    				$("#infomsg").text("You can not assign this Teacher");
	     			         		        	        	}
	     			         		        	        }
	     		        	    					 });
  			        					 	}
  		        	    					 
  		        	    					
  		        	    					
  		        	    				} 

      		        	        	}
      		        	        	if(data == null){
      		        	        		$("#infomsgbox").css('visibility','visible');
		    	    	    				$("#infomsg").text("You can not assign subject in Duration slot.");
      		        	        	}
      		        	       }//end of success
      		        		});// end of ajax 
             				
         				}
	                
             				if(date.getDay() == 0){
             					$("#infomsgbox").css('visibility','visible');
 	    	    				$("#infomsg").text("You can not assign subject in Duration slot.");
             				}
             			},
             			timeFormat: '',
             			firstHour: 11,
             			minTime: schlStartTime,
             			maxTime: noperiod,
             			dayClick: function(date, allDay, jsEvent, view) {
             			
             				 var dayinn = date.getDay();
             				
             				 
             				 $(this).css('background-color', 'pink');
             				
             			},
             			selectable: true,
             			selectHelper: true,
             			select: function(start, end, allDay) {
             				var d = start.getDate();
         				    var m = start.getMonth() + 1;
         				    var y = start.getFullYear();
         				    var day = start.getDay();
       
         				    starthours   = +/ (\d+):/.exec(start)[1]; 
         					startminutes = +/:(\d+):/.exec(start)[1]; 
         					
         					$("#hiddenStartTimetoset").val(starthours);
         					$("#hiddenDaytoset").val(day);
         		 			$("#hiddenDatetoset").val('' + (d <= 9 ? '0' + d : d) + '/' + (m<=9 ? '0' + m : m) + '/' + y);
             				 if(start.getDay() == 0){
             					
         						 loadPopupBox1();
         						}  
     							eventsid = parseInt(eventid1)+1;
	            					eventid1++; 
	            					$("#popupBoxNo1").click(function(){
	            					
	            						$("#dialog").hide();
	            						unloadPopupBox1();
	            						eventid1--;
	            					});
	            					calendar.fullCalendar('unselect');
		            					i++;
		                					
		                			},
             			editable: true,
             			eventMouseover: function(calEvent, jsEvent, view) {
             	            savBg = $(this).css("background-color");
             	            savClr = $(this).css("color");
             	            $(this).css( { color:'#FF3300', backgroundColor:"#27A9E3"} );
             	            $(this).fadeTo('slow',.5);//.css(text-align,'right');
         	             },
         	        	 eventMouseout: function(calEvent, jsEvent, view) {
         	              $(this).css( { color:savClr, backgroundColor:savBg } );
         	              $(this).fadeTo('slow',1);
         	             },
							eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) {
					    			if(event.start.getDay() != 0){
					    				$("#infomsgbox").css('visibility','collapse');
					     				$("#infomsg").text("");
					     				$.ajax({
		         		        	        url: '/icam/getDurationForValidationForParticularSlot.html',
		         		        	        data: "standard=" + ($("#hiddenclasstoset").val()) + "&section=" + ($("#hiddensectiontoset").val()) +"&timeSlot=" + (event.start.getHours()) +"&year=" + ($("#radioYearId").val()),
		         		        	        dataType: 'json',
		         		        	        success: function(data) {
		         		        	        	if(data == null){
		         		        	        		revertFunc();
								    				$("#infomsgbox").css('visibility','visible');
								    				$("#infomsg").text("You can not drop subject before assigning Duration slot.");
		         		        	        	}
		         		        	        	if(data != null){
		         		        	        		if(event.className == "eventAllTeacher"){
		         		        	        			var splitedPeriodTime = data.split("~");
		     			        					 	for(var index = 0;index<splitedPeriodTime.length-1;index++){
		     			        					 		var  splitedTime = splitedPeriodTime[index].split(",");
		     			        					 			$.ajax({
		     			        					 				url: '/icam/getTeacherConflictionForTimeTable.html',
		    	     			         		        	        data: "standard=" + ($("#hiddenclasstoset").val()) + "&section=" + ($("#hiddensectiontoset").val()) +"&daySlot=" + (date.getDay()) +"&year=" + ($("#radioYearId").val()) +"&userId=" + originalEventTeacherUserId+"&periodStartTime=" + splitedTime[0]+"&periodEndTime=" + splitedTime[1],
			     			         		        	        dataType: 'json',
			     			         		        	        success: function(data) {
			     			         		        	        	if(data != null){
			     			         		        	        		revertFunc();
			     			         		        	        		$("#infomsgbox").css('visibility','visible');
			     					    	    	    				$("#infomsg").text("You can not assign this Teacher");
			     			         		        	        	}
			     			         		        	        }
			     		        	    					 });
		     			        					 	}
		         		        	        		}
		         		        	        	}
		         		        	        }
					     				  });
					    				}
					    			if(event.start.getDay() == 0){
					    				revertFunc();
					    				$("#infomsgbox").css('visibility','visible');
					    				$("#infomsg").text("You can not drop subject in Duration slot.");
					    			}
					    			
					    	 },
    	         			 eventAfterRender: function(event, element) {
    	         				 var events = $(this).fullCalendar('clientEvents');
    	         				 var timeSlot = "";
    	         				 for(var i = 0; i < events.length; i++){
    	         					 if(events[i].className == 'eventAll'){
		     	             		  var str = events[i].start;
		     	             		  starthours   = +/ (\d+):/.exec(str)[1]; 
		     	             	      startminutes = +/:(\d+):/.exec(str)[1]; 
		     	             	      
		     	             	      valueevene= valueevene  + events[i].id + "," +  events[i].title + "," +  events[i].start.getDate() + "," +events[i].start.getMonth()+ "," + events[i].start.getFullYear()+ "," + starthours+ "," +events[i].start.getDay() +  "/";
    	         				}
		                    if(events[i].className == 'eventAllTeacher'){
		                     var str1 = events[i].start;
   	             		     var hours   = +/ (\d+):/.exec(str1)[1]; 
		                     var date2 = new Date(2013, 8, 22,hours,00,1);
		                     var todaysEvents = $('#calendar').fullCalendar('clientEvents', function(event) {
	                    	  return date2; });
    	             	      for(var j = 0; j < todaysEvents.length; j++){
    	             	    	if(todaysEvents[j].start.getTime() == date2.getTime()){
    	             	    		timeSlot = todaysEvents[j].title;
	             	    		}
    	             	      }
		                    	    valTeacher = valTeacher  +  events[i].bordercolor + "," +  events[i].start.getDate() + "," +events[i].start.getMonth()+ "," + events[i].start.getFullYear()+ "," + hours+ "," +events[i].start.getDay() + ","+timeSlot+ "/";
		                    }
		                 }
  		               $("#storeDraggedTeacherEventCount").val(eventTeacherId);
  		               $("#storeDraggedEventCount").val(eventid1);
  		               $("#storeDraggedEvent").val(valueevene);
  		               $("#storeDraggedTeacherEvent").val(valTeacher);
  		          }  
         	            });//End of calendar
    				 var daystart3;
    				 
    				 $.each(data, function() {
    						
    						var innerList = this.listAcademicTimeTableDetails;
    						
    				                		 $.each(innerList, function() {
    				                			 
    				                			 schlStartTime = this.schoolStartTime;
    				     						 $("#hiddenSchoolStartTime").val(this.schoolStartTime);
    				     						
    				                			 eventObject3 = new Object();
    				                      		 eventObject3.id = this.timeintTableCode;
    				                      		 daystart3 = this.timeTableDetailsStartDate.split("/");
    				                      		 
    				                      		
    				                      				 
    				                      				 
    				                      				 
    				                      		 eventObject3.start= new Date(daystart3[2],daystart3[1]-1, daystart3[0],this.individualSlot,00,1);
    				                     		 eventObject3.end=  new Date(daystart3[2],daystart3[1]-1, daystart3[0],this.individualSlot,59,1);
    				                     		 
    				                     	//	 alert("this.timeTableDetailsSubject"+this.timeTableDetailsSubject);
    				                     		if(this.timeTableDetailsSubjectName != null){
    				                     		//	alert("subject not null");
    				                     		//	alert("this.timeTableDetailsTeacherName"+this.timeTableDetailsTeacherName);
    				    	                		if(this.timeTableDetailsTeacherName != "null"){
    				    	                		//	alert("teacher not null");
    				                 				eventObject3.title= this.timeTableDetailsSubjectName+"::"+this.timeTableDetailsTeacherName;
    				    	                		}
    				    	                		if(this.timeTableDetailsTeacherName == "null"){
    				    	                		//	alert("teacher  null");
    				                 				eventObject3.title= this.timeTableDetailsSubject;
    				    	                		}
    				                     	 }
    				                     		if(this.timeTableDetailsSubjectName == null){
    				                     			if(this.timeTableDetailsEndTime == 0){
    				                     	//			alert("end time zero");
    				                     				eventObject3.title= this.breakFlag;
    				                     			}
    				                     			if(this.timeTableDetailsEndTime != 0){
    				                     			eventObject3.title= this.timeTableDetailsStartTime+"-"+this.timeTableDetailsEndTime;
    				                     			}
    				                     		}
    				                     	//	alert("one end");
    				                     		eventObject3.allDay = false; 
    				                     		eventObject3.color="#000000";
    				                     		events3.push(eventObject3);
    				                     		$('#calendar').fullCalendar('renderEvent', eventObject3, true);	
    				                     //		alert("two end");
    										 });
    										 
    										 });
    				 
    				
             
    				
	         }// end not null
          
			 
	         }});  
	});
	
	
	
	
});