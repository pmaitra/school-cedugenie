//ajax call for fetching course For particular class on change of "radioClassId" select box
$(document).ready(function(){
	$("#radioClassId").change(function(){			
		var s3 = $(this).val();
		$("#hiddenclasstoset").val(s3);
			$.ajax({
			        url: '/icam/getSectionAgainstStandard.html',
			        data: "standard=" + ($(this).val()),
			        dataType: 'json',
			        success: function(data) {	
			        	var s4 = document.getElementById("hiddensectiontoset");
			        	if(data == null){
			        		$("#infomsgbox").css('visibility','visible');
    	    				$("#infomsg").text("Section is not asseigned for this Class.");
    	    				for(var i=s4.length;i>0;i--)
							{
								s4.remove(i);
							} 
			        	}
			        	if(data != null){
			        		$("#infomsgbox").css('visibility','collapse');
		    				$("#infomsg").text("");
		    				var arr = data.split(";");
							for(var i=s4.length;i>0;i--)
								{
									s4.remove(i);
								}  
							for(var i=0;i<arr.length;i++)
								{
									var section = arr[i].split("*");
									s4.add(new Option(section[1], section[0]));
								}      	
				            }
			        }
			        }); 
			});
	
// ajax call for fetching subject For particular subjectGroup change of "subjectselected" select box
	
	
	$("#subjectselected").change(function(){
		$("#evenets").remove();
		$("#external-events").css('visibility','visible');
		$("#external-events-another").css('visibility','collapse');
		$("#teacher").remove();
		var subjectIndividualSelectBox = document.getElementById("subjectIndividualselected");
		var SubjectTypeName = $(this).val();
		var standard = $("#hiddenclasstoset").val();
		if(SubjectTypeName == "BREAK"){
			
			/* $("#external-events-another").css('visibility','collapse');
    		$("#teacher").remove(); */
			$("#subjectTabId").css('visibility','collapse');
			var parentDiv1 = document.createElement("div");
     		parentDiv1.setAttribute("id","evenets");
     		var breakList = "SHORT BREAK,LONG BREAK,";
     		var spliteBreakList = breakList.split(",");
     		for(var j=subjectIndividualSelectBox.length;j>0;j--)
			{
				 subjectIndividualSelectBox.remove(j);
			} 
     		 for(var i=0;i<spliteBreakList.length-1;i++)
				{
		     		var div1 = document.createElement("div");
					var textDiv1 = spliteBreakList[i];
					
					div1.setAttribute("class","external-event");
					div1.setAttribute("id","subjectTypeselected");
					div1.appendChild(document.createTextNode(textDiv1));
					div1.setAttribute("Style", "background-color: #F74D4D;border-color: black;color: black;");
					parentDiv1.appendChild(div1);
				}
			 document.getElementById("external-events").appendChild(parentDiv1);
			 
			 $(".external-event").each(function() {
	    			
	    			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
	    			// it doesn't need to have a start or end
	    			var eventObject = {
	    				title: $.trim($(this).text()) // use the element's text as the event title
	    			};
	    			var eventType = "Break";
	        		$(this).data('eventType', eventType);
	    			// store the Event Object in the DOM element so we can get to it later
	    			$(this).data('eventObject', eventObject);
	    			
	    			// make the event draggable using jQuery UI
	    			$(this).draggable({
	    				zIndex: 999,
	    				revert: true,      // will cause the event to go back to its
	    				revertDuration: 0  //  original position after the drag
	    			});
	    		});
				}//end of if
		
		// ajax call for fetching subject For particular subjectGroup and course change of "subjectselected" select box
		//	if(!SubjectTypeName.equals("BREAK")){
		else{	
			
		
	 	$.ajax({
	        url: '/icam/getSubjectsBasedOnStandardAndSubjectGroup.html',
	        data: "standard=" + standard+ "&subjectGroup=" + SubjectTypeName,
	        dataType: 'json',
	        success: function(data) {	
	        	if(data != null){
	        		var sec=data.split("*");			        	
	         		var arraydata = "";
					var parentDiv2 = document.createElement("div");
	         		parentDiv2.setAttribute("id","evenets");
	         		$("#subjectTabId").css('visibility','visible');
					for(var i=subjectIndividualSelectBox.length;i>0;i--)
					{
						 subjectIndividualSelectBox.remove(i);
					} 
					for(var i=0;i<sec.length-1;i++)
					{
						arraydata = sec[i].split(",");
						subjectIndividualSelectBox.add(new Option(arraydata[1], arraydata[0]),null);
					 	var div2 = document.createElement("div");
						var textDiv = arraydata[1];
						div2.setAttribute("class","external-event");
						div2.setAttribute("id","subjectTypeselected");
						div2.appendChild(document.createTextNode(textDiv));
						div2.setAttribute("Style", "background-color: #F74D4D;border-color: black;color: black;");
						parentDiv2.appendChild(div2);
						
					}  
					 document.getElementById("external-events").appendChild(parentDiv2);
					 //subjectIndividualSelectBox.add(new Option(breaktitle[1], breaktitle[1]),null);	
				}	/* end if for not null */
	        	if(data == null){
	        		$("#warningbox").css('visibility','visible');
    				$("#warningmsg").text("There is no subject for particular subjectGroup");
	        	} /* end if for null */
	        	
	        	$(".external-event").each(function() {
	    			
	    			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
	    			// it doesn't need to have a start or end
	    			var eventObject = {
	    				title: $.trim($(this).text()) // use the element's text as the event title
	    			};
	    			var eventType = "Subject";
	        		$(this).data('eventType', eventType);
	    			// store the Event Object in the DOM element so we can get to it later
	    			$(this).data('eventObject', eventObject);
	    			
	    			// make the event draggable using jQuery UI
	    			$(this).draggable({
	    				zIndex: 999,
	    				revert: true,      // will cause the event to go back to its
	    				revertDuration: 0  //  original position after the drag
	    			});
	    		});
	       	 }// end of success
	 	
			}); // end of ajax
	 	
		}// end of else
		
		});
	
	
	
	//Get Teacher Per SubjectGroup and Subject subjectIndividualselected  ($("#hidCourseToSet").val())+ "&subjectType=" + SubjectTypeName,
	$("#subjectIndividualselected").change(function(){
		$.ajax({
	        url: '/icam/getTeacherPerSubject.html',
	        dataType: 'json',
	        data: "subjectName="+($("#subjectIndividualselected :selected").text()),
	        success: function(data) {
	        	if(data!=""){  
	        		$("#infomsgbox").css('visibility','collapse');
					$("#infomsg").text("");
	        		$("#external-events-another").css('visibility','visible');
	        		$("#teacher").remove();
	        		var parentDiv1 = document.createElement("div");
	         		parentDiv1.setAttribute("id","teacher");
					var arr = data.split("*~*");	
					if(arr != ""){
						for (var i=0;i<arr.length-1;i++){
		        			var nameAndId=arr[i].split("*@#");
		        			//document.getElementById(selectId).add(new Option(nameAndId[1],nameAndId[0] ),null);
		        			var div2 = document.createElement("div");
							var textDiv = nameAndId[1];
							div2.setAttribute("class","external-event-for-teacher");
							div2.setAttribute("id",nameAndId[0]);
							div2.appendChild(document.createTextNode(textDiv));
							div2.setAttribute("Style", "background-color: #F74D4D;border-color: black;color: black;");
							parentDiv1.appendChild(div2);
		        		}
		        		document.getElementById("external-events-another").appendChild(parentDiv1);
					}
					if(arr == ""){
						$("#external-events-another").css('visibility','collapse');
		        		$("#teacher").remove();
		        		$("#infomsgbox").css('visibility','visible');
						$("#infomsg").text("There is no teacher is not assigned yet.");
					}
	        		 
	        	}
	        	if(data ==""){  
	        		$("#external-events-another").css('visibility','collapse');
	        		$("#teacher").remove();
	        		$("#infomsgbox").css('visibility','visible');
					$("#infomsg").text("There is no teacher is not assigned yet.");
	        	}
	        	
	        	$(".external-event-for-teacher").each(function() {
	        		// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
	        		// it doesn't need to have a start or end
	        		var eventObject = {
	        			title: $.trim($(this).text()) // use the element's text as the event title
	        		};
	        		var eventType = "Teacher";
	        		var eventTeacherUserUd = $(this).attr('id');
	        		// store the Event Object in the DOM element so we can get to it later
	        		$(this).data('eventTeacherUserUd', eventTeacherUserUd);
	        		$(this).data('eventType', eventType);
	        		$(this).data('eventObject', eventObject);
	        		
	        		// make the event draggable using jQuery UI
	        		$(this).draggable({
	        			zIndex: 999,
	        			revert: true,      // will cause the event to go back to its
	        			revertDuration: 0  //  original position after the drag
	        		});
	        	});
	        }
		}); 
		
	});
	
});