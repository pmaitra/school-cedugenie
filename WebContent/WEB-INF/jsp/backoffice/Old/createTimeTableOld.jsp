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
<meta name="description" content="Page To Set Academic Time table" />
<meta name="keywords" content="Page To Set Academic Time table" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Academic Time Table</title>

<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />

<!-- Time Table -->
<link rel="stylesheet" href="/icam/css/backoffice/createTimeTable.css" />
<link href='/icam/css/backoffice/TimeTable.css' rel='stylesheet' />
<link href='/icam/css/common/fullcalendar.print.css' rel='stylesheet' media='print' />
<link href='/icam/css/backoffice/timeTableStructure.css' rel='stylesheet' /><!-- change -->
	<!-- For Time Picker -->
<link rel="stylesheet" type="text/css" media="all" href="/icam/css/common/calendar/jquery-ui-1.10.4.custom.min.css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery.ptTimeSelect.css" />
	<!-- For Time Picker -->
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-1.8.2.js"></script>
<script type="text/javascript" language="javascript" src="/icam/js/common/jquery-1.9.0.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery.ptTimeSelect.js"></script>
<script src='/icam/js/backoffice/timeTable.js'></script><!-- change -->
<script src='/icam/js/backoffice/getTimeTableDetails.js'></script> 

<!------------------------------------------ DWR Start----------------------------------------->
	<script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/engine.js'></script>
    <script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/util.js'></script>
	<script type='text/javascript' language="javascript" src='<%=request.getContextPath()%>/dwr/interface/backOfficeService.js'></script>
    
 <!------------------------------------------ DWR End----------------------------------------->

<script src='/icam/js/backoffice/getSelectedValuesForTimeTable.js'></script>
<script src='/icam/js/backoffice/createTimeTable.js'></script>
<script src='/icam/js/backoffice/popUpBoxForTimeTableStructure.js'></script>
<!--  **** -->

<script type="text/javascript">
/* Method for submit and edit data  */

//insert time
function submitinfo(){
	
	 document.academicTimeTableForm.method="post";
	 document.academicTimeTableForm.action="addPeriodDurationforTimeTable.html";
	 document.academicTimeTableForm.submit();             // back from the page
	 return true;
}

//submit subject And type
function submitInfoForSubjectAndTeacher(){
	// var title = $("#subjectTypeselected option:selected").val();
	// $("#hidvaluetoset").val(title);
	 document.academicTimeTableForm.method="Post";
	 document.academicTimeTableForm.action="addSubjectBreakAndTeacherForTimeTable.html";
	 document.academicTimeTableForm.submit();             // back from the page
	 return true;
}
//To open the edit page
function editPageShow(){
	document.academicTimeTableForm.method="post";
	document.academicTimeTableForm.action="editPageForTimeTable.html";
	document.academicTimeTableForm.submit();             // back from the page
	return true;
}

//delete the dragged elemenet(which is form backend) from time table
function deleteDraggedEvent(){
	 document.academicTimeTableForm.method="Post";
	 document.academicTimeTableForm.action="deleteDraggedElementForTimeTable.html";
	 document.academicTimeTableForm.submit();             // back from the page
	 return true;
}

//edit the duration
function editAction(){
	 document.academicTimeTableForm.method="Post";
	 document.academicTimeTableForm.action="updateAssignedDuration.html";
	 document.academicTimeTableForm.submit();             // back from the page
	 return true;
} 

//edit the position of dragged element
function editDraggedEvent(){
	 document.academicTimeTableForm.method="Post";
	 document.academicTimeTableForm.action="updateAssignedSubjectAndTeacher.html";
	 document.academicTimeTableForm.submit();             // back from the page
	 return true;
}
$(document).ready(function(){
var selectionFlag = 0;
var idFlag = 0;
$('#popupBoxYes1').click( function() {
		
		//var timeregex="^(1[0-2]|0[1-9]):([0-5][0-9]) ([AP]M)?\$";
		var valPrdStartTime = $("#PeriodstartTime").val();
		var valPrdEndTime = $("#hiddenEndTimetoset").val();
		var valSchoolStartTimeFromDB = $('#showStartSchoolTime').text();
		var valSchoolEndTimeFromDB = $('#showEndSchoolTime').text();
		var splitedValSchoolStartTime = valSchoolStartTimeFromDB.split(":");
		var splitedValSchoolTimeFirstPart = splitedValSchoolStartTime[1].split(" ");
		var splitedValSchoolEndTime = valSchoolEndTimeFromDB.split(":");
		var splitedValSchoolEndTimeFirstPart = splitedValSchoolEndTime[1].split(" ");
		
		$("#hiddenSchoolStartTime").val(splitedValSchoolStartTime[0]);
		if(valPrdStartTime == ""){
			$("#showErrorMsg1").css('visibility','visible');
			$("#showErrorMsg1").text('Enter Start Time.');
			return false;
			retVal = false;
		}
		if(valPrdEndTime == ""){
			$("#showErrorMsg1").css('visibility','visible');
			$("#showErrorMsg1").text('Enter End Time.');
			return false;
			retVal = false;
		}
		if(valPrdStartTime != "" && valPrdEndTime != ""){
			
			/* if(!valPrdStartTime.match(timeregex)){
				$("#showErrorMsg1").css('visibility','visible');
				$("#showErrorMsg1").text('Enter Proper f In 00:00 AM/PM time format.');
				return false;
				retVal = false;
			}
			if(!valPrdEndTime.match(timeregex)){
				$("#showErrorMsg1").css('visibility','visible');
				$("#showErrorMsg1").text('Enter Proper h In 00:00 AM/PM time format.');
				return false;
				retVal = false;
			} */
		//	if(valPrdStartTime.match(timeregex) && valPrdEndTime.match(timeregex)){
			
				var splitedValPrdStartTime = valPrdStartTime.split(":");
				var splitedValPrdStartTimeFirstPart = splitedValPrdStartTime[1].split(" ");
				var splitedValPrdEndTime = valPrdEndTime.split(":");
				var splitedValPrdEndTimeFirstPart = splitedValPrdEndTime[1].split(" ");
				
				var getTimeStartTimeFromDB = new Date( '1 Jan 1900 '+splitedValSchoolStartTime[0]+':'+splitedValSchoolTimeFirstPart[0]+':00'+' '+ splitedValSchoolTimeFirstPart[1] );
				var getTimeEndTimeFromDB = new Date( '1 Jan 1900 '+splitedValSchoolEndTime[0]+':'+splitedValSchoolEndTimeFirstPart[0]+':00'+' '+ splitedValSchoolEndTimeFirstPart[1] );
				
				var getTimeStartTimeFromFront = new Date( '1 Jan 1900 '+splitedValPrdStartTime[0]+':'+splitedValPrdStartTimeFirstPart[0]+':00'+' '+ splitedValPrdStartTimeFirstPart[1] );
				var getTimeEndTimeFromFront = new Date( '1 Jan 1900 '+splitedValPrdEndTime[0]+':'+splitedValPrdEndTimeFirstPart[0]+':00'+' '+ splitedValPrdEndTimeFirstPart[1] );
				
				var flagStartStatus = (getTimeStartTimeFromDB.getTime() <= getTimeStartTimeFromFront.getTime()) && (getTimeEndTimeFromDB.getTime() >= getTimeStartTimeFromFront.getTime());
				var flagEndStatus = (getTimeStartTimeFromDB.getTime() <= getTimeEndTimeFromFront.getTime()) && (getTimeEndTimeFromDB.getTime() >= getTimeEndTimeFromFront.getTime());
				
			
				if(flagStartStatus == true && flagEndStatus == true){
					if(selectionFlag == 1){
		     	 		  var  valToSet = valPrdStartTime+"-"+valPrdEndTime;
		     	 		  $("#hiddenUpdateId").val(idFlag);
			              $("#hiddenUpdateTime").val(valToSet);
			              editAction();
			              unloadPopupBox1();
		     	 	}
					if(selectionFlag != 1){
						submitinfo();
						unloadPopupBox1();
					}
				}
				if(flagStartStatus == true && flagEndStatus == false){
					$("#showErrorMsg1").css('visibility','visible');
					$("#showErrorMsg1").text('Enter Proper shift time within '+valSchoolStartTimeFromDB+" and "+valSchoolEndTimeFromDB);
					return false;
					retVal = false;
					
				}
				
				if(flagStartStatus == false && flagEndStatus == true){
					$("#showErrorMsg1").css('visibility','visible');
					$("#showErrorMsg1").text('Enter Proper shift time within '+valSchoolStartTimeFromDB+" and "+valSchoolEndTimeFromDB);
					return false;
					retVal = false;
					
				}
				
				if(flagStartStatus == false && flagEndStatus == false){
					$("#showErrorMsg1").css('visibility','visible');
					$("#showErrorMsg1").text('Enter Proper shift time within '+valSchoolStartTimeFromDB+" and "+valSchoolEndTimeFromDB);
					return false;
					retVal = false;
				}
				
		}
		//
		
	});

////////////////////////////////////////////////////////////
	/*  Script for retrieving data after pressing edit button  */
	var events=[];    
	var noofPeriods;
	var schoolStartTime1;
	var size = "${timeTableDetails.size()}";	
	var sizeAnother = "${timeTableSubjectCount.size()}";
	if(sizeAnother == ""){
		$("#CountDiv").css('visibility','collapse');
		$('#CountTable').css('visibility','collapse');
		
	}
	if(sizeAnother != ""){
		$("#CountDiv").css('visibility','visible');
		$('#CountTable').css('visibility','visible');
		$('#CountTable tbody').remove();
		<c:forEach var="countDetails" items="${timeTableSubjectCount}">
			var row = "";
			row = $('<tr>'); 
	        row.append($('<td></td>').html("${countDetails.timeTableDetailsSubjectName}"));
	        row.append($('<td></td>').html("${countDetails.timeTableDetailsSubjectCount}"));
	        $('#CountTable').append(row);	           
		</c:forEach>
	}
	if(size != ""){
	$("#showShiftTime").css('visibility','visible');
	<c:forEach var="detailsTime1" items="${timeTableDetails}">
	noofPeriods = "<c:out value="${detailsTime1.totalSlot}"/>";
	$("#selectParam").remove();
	//$("#selectYear").remove();
	$("#showTable").css('visibility','visible');
	//$("#showTableForYear").css('visibility','visible');
	$("#hiddensectiontosetschool").remove();
	$("#radioClassNameschool").remove();
	$("#academicYearIdToSchool").remove();
	$("#classLabel").remove();
	$("#yearLabel").remove();
	
	var setclass1 = "<c:out value="${detailsTime1.timeTableClass.standardCode}"/>";
	var setsection1 = "<c:out value="${detailsTime1.timeTableSection.sectionCode}"/>";
	var setAcademicYear = "<c:out value="${academicYear.academicYearCode}"/>";
	$("#hiddenperiodtoset").val(noofPeriods);
	
	//$("#showTableForYear").append('<table id="yearLabel" class="midsec"><tr></tr></table>');
	$("#showTable").append('<table id="classLabel" class="midsec"><tr><td>Academic Year ::</td><td><input type="text" name="radioYearName" class="textfield1" id="academicYearIdToSchool" value="'+ setAcademicYear + '" readonly="readonly"/></td><td>Standard ::</td><td><input type="text" name="radioClassName" class="textfield1" id="radioClassNameschool" value="'+ setclass1 + '" readonly="readonly"/></td><td>SECTION ::</td><td><input type="text" name="hiddensectiontoset" class="textfield1" id="hiddensectiontosetschool" value="'+ setsection1 + '" readonly="readonly"/></td></tr></table>');
	$("#schoolClass").val(setclass1);
		
	<c:forEach var="detailsList1" items="${detailsTime1.listAcademicTimeTableDetails}">
	schoolStartTime1 = "<c:out value="${detailsList1.schoolStartTime}"/>"; 
	$("#hiddenSchoolStartTime").val(schoolStartTime1);
	
	</c:forEach>
	</c:forEach>
	
	
	$("#submitButtonAtFirst").css('visibility','visible');
	//ajax call for fetching subjectGroup For particular course change of "hiddensectiontoset" select box
	$("#subjectGroupTabId").css('visibility','visible');
/* 	$.ajax({
	        url: '/sms/getSubjectsForClassForTimeTable.html',
	      	data: "course=" + ($("#hiddencoursetosetschool").val()),
	        dataType: 'json',
	        success: function(data) {
	        	if(data != null){
	        		$("#subjectGroupTabId").css('visibility','visible');
		        	var breaktitle = data.split("$");
		         	var sec=data.split("*");			        	
		         	var arraydata;
					var subjectGroupSelectBox = document.getElementById("subjectselected");
					 for(var i=subjectGroupSelectBox.length;i>0;i--)
						{
						 subjectGroupSelectBox.remove(i);
						} 
					
					 for(var i=0;i<sec.length-1;i++)
						{
						arraydata = sec[i].split(",");
						subjectGroupSelectBox.add(new Option(arraydata[1], arraydata[0]),null);
						} 
					 subjectGroupSelectBox.add(new Option(breaktitle[1], breaktitle[1]),null);
				       }//end of not null	
					if(data == null){
					  	$("#warningbox").css('visibility','visible');
						$("#warningmsg").text("there is no subject for particular course.");			
									}//end of null
	        	}//end of success
				
			 });//end of ajax */
	var classN = $("#radioClassNameschool").val();
	var sectionN = $("#hiddensectiontosetschool").val();
	$("#hiddenclasstoset").val(classN);
	$("#hiddenSectiontoset").val(sectionN);
	var eventid=0;
	var eventid1=0;
	var eventTeacherId = 0;
	var valTeacher = "";
	var valueevene = "";
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
				var subjectGroup = $("#subjectselected option:selected").val();
				 $.ajax({
	        	        url: '/icam/getDurationForValidationForParticularSlot.html',
	        	        data: "standard=" + ($("#hiddenclasstoset").val()) + "&section=" + ($("#hiddensectiontosetschool").val()) +"&timeSlot=" + cellHour +"&year=" + ($("#academicYearIdToSchool").val()),
	        	        dataType: 'json',
	        	        success: function(data) {
	        	        	if(data != null){
	        	        	$("#infomsgbox").css('visibility','collapse');
		    				$("#infomsg").text("");
		    				eventsid = parseInt(eventid)+1;
		    				eventid++;
		    				copiedEventObject.start = date;
		    				//copiedEventObject.id = eventid;
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
	 	    					var splitedPeriodTime = data.split("~");
	        					 	for(var index = 0;index<splitedPeriodTime.length-1;index++){
	        					 		var  splitedTime = splitedPeriodTime[index].split(",");
	        					 		$.ajax({
	        					 			url: '/icam/getTeacherConflictionForTimeTable.html',
		         		        	        data: "standard=" + ($("#hiddenclasstoset").val()) + "&section=" + ($("#hiddensectiontosetschool").val()) +"&daySlot=" + (date.getDay()) +"&year=" + ($("#academicYearIdToSchool").val()) +"&userId=" + originalEventTeacherUserId+"&periodStartTime=" + splitedTime[0]+"&periodEndTime=" + splitedTime[1],
		         		        	        dataType: 'json',
		         		        	        success: function(data) {
		         		        	        	if(data == null){     
		         		        	        	copiedEventObject.id = eventid; 	   
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
		    					$("#infomsg").text("You can not assign subject before Duration slot.");
	        	        	}
	        	       }//end of success
	        		});// end of ajax 
				
			}
	        		/////////////////////////////////////////////////////////////////////////
	
				if(date.getDay() == 0){
					$("#infomsgbox").css('visibility','visible');
					$("#infomsg").text("You can not assign subject in Duration slot.");
				}
	        	
		},
		eventDrop: function(event,dayDelta,minuteDelta,allDay,revertFunc) {
			if(event.start.getDay() != 0){
				$.ajax({
					    url: '/icam/getDurationForValidationForParticularSlot.html',
	        	        data: "standard=" + ($("#hiddenclasstoset").val()) + "&section=" + ($("#hiddensectiontosetschool").val()) +"&timeSlot=" + (event.start.getHours()) +"&year=" + ($("#academicYearIdToSchool").val()),
	        	        dataType: 'json',
	        	        success: function(data) {
	        	        	if(data == null){
	        	        		revertFunc();
		    					$("#infomsgbox").css('visibility','visible');
		    					$("#infomsg").text("You can not drop subject before assigning Duration slot.");
	        	        	}
	        	        	if(data != null){
	        	        		
	        	        		var str1 = event.start;
	    					 	var starthours1   = +/ (\d+):/.exec(str1)[1]; 
	    					 	var splitedPeriodTime = data.split("~");
	        	        		var confirmationTitle=confirm("Are you sure about this change?");
	        					if(confirmationTitle == true){
	        					if(event.className != "eventAll" && event.className != "eventAllTeacher"){
	        					 	for(var index = 0;index<splitedPeriodTime.length-1;index++){
	        					 		var  splitedTime = splitedPeriodTime[index].split(",");
	        					 		
	        					 		$.ajax({
	        					 				url: '/icam/getTeacherConflictionForTimeTable.html',
			         		        	        data: "standard=" + ($("#hiddenclasstoset").val()) + "&section=" + ($("#hiddensectiontosetschool").val()) +"&daySlot=" + (event.start.getDay()) +"&year=" + ($("#academicYearIdToSchool").val()) +"&userId=" + (event.bordercolor)+"&periodStartTime=" + splitedTime[0]+"&periodEndTime=" + splitedTime[1],
			         		        	        dataType: 'json',
			         		        	        success: function(data) {
			         		        	        	if(data != null){
			         		        	        		revertFunc();
			         		        	        		$("#infomsgbox").css('visibility','visible');
					    	    	    				$("#infomsg").text("You can not assign this Teacher");
			         		        	        	}
			         		        	        	if(data == null){
			         		        	        		var valueToEdit = event.id + "," + event.start.getDate() + "," +event.start.getMonth()+ "," + event.start.getFullYear()+ "," + starthours1+ "," +event.start.getDay()+ ","+splitedTime[0]+","+splitedTime[1]+","+event.title;
			    	        					 		$("#draggedElementIdForUpdate").val(valueToEdit);
			    		        						$("#storeDraggedEventCount").val(eventid1);
			    		        			            $("#storeDraggedEvent").val(valueevene);
			    		        			            $("#storeDraggedTeacherEventCount").val(eventTeacherId);
			    		        			            $("#storeDraggedTeacherEvent").val(valTeacher);
			    		        			            editDraggedEvent();
			         		        	        	}
			         		        	        }
		        	    					 });
	        					 	}
	        					}
	        					if(event.className == "eventAllTeacher"){
	        						for(var index = 0;index<splitedPeriodTime.length-1;index++){
	        					 		var  splitedTime = splitedPeriodTime[index].split(",");
	        					 		$.ajax({
	        					 				url: '/icam/getTeacherConflictionForTimeTable.html',
			         		        	        data: "standard=" + ($("#hiddenclasstoset").val()) + "&section=" + ($("#hiddensectiontosetschool").val()) +"&daySlot=" + (event.start.getDay()) +"&year=" + ($("#academicYearIdToSchool").val()) +"&userId=" + (event.bordercolor)+"&periodStartTime=" + splitedTime[0]+"&periodEndTime=" + splitedTime[1],
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
	        		        	/*  var title = prompt( { buttons: { DragEvent: true, Delete: false} }); */
	        					 
	        					 if (confirmationTitle == null || confirmationTitle == false) { // Canceled
	        			            revertFunc();
	        			        } 
	        	        	}
	        	        }
					});
				$("#infomsgbox").css('visibility','collapse');
					$("#infomsg").text("");
				
			}
			if(event.start.getDay() == 0){
				revertFunc();
				$("#infomsgbox").css('visibility','visible');
				$("#infomsg").text("You can not drop subject in Duration slot.");
			}
			
		 },
		timeFormat: '',
		firstHour: 11,
		minTime: schoolStartTime1,
		maxTime: noofPeriods,
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
	      eventClick: function(calEvent, jsEvent, view) {
	   		  $("#hiddenUpdateId").val(calEvent.id);
	   		  var timeregex="^(1[0-2]|0[1-9]):([0-5][0-9]) ([AP]M)?\$";
	    	  if(calEvent.start.getUTCDay() == 0){
	    		    var title = calEvent.title;
		     		var splitedTime = title.split("-");
		     		$("#PeriodstartTime").val(splitedTime[0]);
		     		$("#hiddenEndTimetoset").val(splitedTime[1]);
		     		selectionFlag = 1;
		     		idFlag = calEvent.id;
	    		    loadPopupBox1();
	    		}
	    	  if(calEvent.start.getUTCDay() != 0){
	    		  var deleteConfirmation = confirm("Do you want to delete?");
				 	if (deleteConfirmation) {
				    if(calEvent.className != "eventAll"){
				 		var eventId = calEvent.id;
						$("#draggedElementIdForDelete").val(eventId);
					 	$("#storeDraggedEventCount").val(eventid1);
		               	$("#storeDraggedEvent").val(valueevene);
		               	$("#storeDraggedTeacherEventCount").val(eventTeacherId);
			           	$("#storeDraggedTeacherEvent").val(valTeacher);
						deleteDraggedEvent();
						
						
				 	}
				 	if(calEvent.className == "eventAll"){
				 		valueevene = "";
				 		eventid--;
					 calendar.fullCalendar('removeEvents', calEvent.id);
				 	}
				 }
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
	                    	valTeacher = valTeacher  +  events[i].bordercolor + "," +  events[i].start.getDate() + "," +events[i].start.getMonth()+ "," + events[i].start.getFullYear()+ "," + hours+ "," +events[i].start.getDay() + ","+timeSlot+  "/";
	                    	
	                    }
	                 }
	         
	               $("#storeDraggedTeacherEventCount").val(eventTeacherId);
	               $("#storeDraggedEventCount").val(eventid1);
	               $("#storeDraggedEvent").val(valueevene);
	               $("#storeDraggedTeacherEvent").val(valTeacher);
	               
	            }
	        
	});//End of calendar
	//sayani 
	var daystart;
	<c:forEach var="detailsTime" items="${timeTableDetails}">
	eventObject = new Object();
	eventObject.id = "<c:out value="${detailsTime.timeintTableCode}"/>";
	<c:forEach var="detailsList" items="${detailsTime.listAcademicTimeTableDetails}">
	var strt = "<c:out value="${detailsList.timeTableDetailsStartDate}"/>";
	var endTime = "<c:out value="${detailsList.timeTableDetailsEndTime}"/>";
	var previousEndTime = "<c:out value="${detailsList.timeTableDetailsStartTime}"/>";
	$("#hiddenPeriodEnd").val(endTime);
	var strtime = "<c:out value="${detailsList.individualSlot}"/>";
	var subjectTitle = "<c:out value="${detailsList.timeTableDetailsSubjectName}"/>"+"::"+"<c:out value="${detailsList.timeTableDetailsTeacherName}"/>";
	daystart = strt.split("/");
	eventObject.start= new Date(daystart[2],daystart[1]-1, daystart[0],strtime,00,1);
	eventObject.end=  new Date(daystart[2],daystart[1]-1, daystart[0],strtime,59,1);
	var showstrttime = $("#SchoolStartTime").val();
	if( subjectTitle != "::"){
		eventObject.title= subjectTitle;
		eventObject.bordercolor = "<c:out value="${detailsList.timeTableDetailsUserId}"/>";
	} 
	 if( subjectTitle == "::"){
		 if(endTime == 0){
			 eventObject.title= "<c:out value="${detailsList.breakFlag}"/>";
		 }
		 if(endTime != 0){
		 		eventObject.title= previousEndTime+ "-" +endTime;
		 }
	 }
	 
	    /* var splitedValPrdStartTime = previousEndTime.split(":");
		var splitedValPrdStartTimeFirstPart = splitedValPrdStartTime[1].split(" ");
		var splitedValPrdEndTime = endTime.split(":");
		var splitedValPrdEndTimeFirstPart = splitedValPrdEndTime[1].split(" ");
		
		var splitedValShiftStartTime =  $("#showStartShiftTime").text().split(":");
		var splitedValShiftStartTimeFirstPart = splitedValShiftStartTime[1].split(" ");
		var splitedValShiftEndTime = $("#showEndShiftTime").text().split(":");
		var splitedValShiftEndTimeFirstPart = splitedValShiftEndTime[1].split(" ");
		
		var getTimeStartTimeFromDB = new Date( '1 Jan 1900 '+splitedValShiftStartTime[0]+':'+splitedValShiftStartTimeFirstPart[0]+':00'+' '+ splitedValShiftStartTimeFirstPart[1] );
		var getTimeEndTimeFromDB = new Date( '1 Jan 1900 '+splitedValShiftEndTime[0]+':'+splitedValShiftEndTimeFirstPart[0]+':00'+' '+ splitedValShiftEndTimeFirstPart[1] );
		
		var getTimeStartTimeFromFront = new Date( '1 Jan 1900 '+splitedValPrdStartTime[0]+':'+splitedValPrdStartTimeFirstPart[0]+':00'+' '+ splitedValPrdStartTimeFirstPart[1] );
		var getTimeEndTimeFromFront = new Date( '1 Jan 1900 '+splitedValPrdEndTime[0]+':'+splitedValPrdEndTimeFirstPart[0]+':00'+' '+ splitedValPrdEndTimeFirstPart[1] );
		
		var flagStartStatus = (getTimeStartTimeFromDB.getTime() <= getTimeStartTimeFromFront.getTime()) && (getTimeEndTimeFromDB.getTime() >= getTimeStartTimeFromFront.getTime());
		var flagEndStatus = (getTimeStartTimeFromDB.getTime() <= getTimeEndTimeFromFront.getTime()) && (getTimeEndTimeFromDB.getTime() >= getTimeEndTimeFromFront.getTime()); */
	eventObject.color="#000000"; 
	</c:forEach>
	eventObject.allDay = false; 
	events.push(eventObject);
	$('#calendar').fullCalendar('renderEvent', eventObject, true);	
	</c:forEach>
	}
//////////////////////////////////////////////////////////////////////////
});
</script>

</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Time Table
	</h1>
</div>
	 <div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div>   
	<c:choose>
	<c:when test="${timeTableDetails eq null}">			

	</c:when>
	<c:otherwise>			
			<c:set var="timeTableDetails"  scope="request"> </c:set>
	</c:otherwise>
</c:choose>
	<div class="infomsgbox" id="infomsgbox" >
		<span id="infomsg"></span>	
	</div>
	<form name="academicTimeTableForm" id="academicTimeTableForm">
		<%-- <c:choose> --%>
		<%-- 	<c:when test="${year eq null || empty year
			 				|| listStandard eq null || empty listStandard
			 				|| subjectGroupList eq null || empty subjectGroupList
			 				|| schoolDetails eq null || empty schoolDetails
			 				|| subjectGroupList eq null || empty subjectGroupList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<c:if test="${year eq null || empty year}">
							<span id="errormsg">Academic Year Not Found</span>	
						</c:if>
						<c:if test="${listStandard eq null || empty listStandard}">
							<span id="errormsg">Standard not found</span>	
						</c:if>
						<c:if test="${subjectGroupList eq null || empty subjectGroupList}">
							<span id="errormsg">Subject group not found</span>	
						</c:if>
						<c:if test="${schoolDetails eq null || empty schoolDetails}">
							<span id="errormsg">School Details not found</span>	
						</c:if>
						<c:if test="${subjectGroupList eq null || empty subjectGroupList}">
							<span id="errormsg">Subject Group not found</span>	
						</c:if>
					</div>
				</div>
			</c:when> --%>
			<%-- <c:otherwise>		 --%>
				<table id="selectParam"  class="midsec">
					<tr>
						<td>
							Academic Year:
						</td>
						<td>
							<select id="radioYearId" name="radioYearName" class="defaultselect">
								<option value="">Select</option>				
								<option value="${year.academicYearCode}">${year.academicYearName}</option>			
							</select>
						</td>
						<td>
							Class:
						</td>
						<td>
							<select id="radioClassId" name="radioClassName" class="defaultselect">
								<option value="">Select</option>				
								<c:forEach var="className" items="${listStandard}">
										<option value="${className.standardCode}">${className.standardName}</option>			
								</c:forEach>
							</select>
						</td>
						<td>
							Section:
						</td>
						<td>
							<select id="hiddensectiontoset" name="hiddensectiontoset" class="defaultselect">
								<option value="">Select</option>				
							</select>
						</td>
					</tr>
				</table>
				<div id="showTable" >	
	
				</div>
				<table id="showShiftTime"  class="midsec" style="visibility: collapse;">
					<tr>
						<td>
							Shift Start Time:
						</td>
						<td>
							<span id="showStartSchoolTime">${schoolDetails.schoolStartTime}</span>
						</td>
						<td>
							Shift End Time:
						</td>
						<td>
							<span id="showEndSchoolTime">${schoolDetails.schoolEndTime}</span>
						</td>
					</tr>
					</table>
			<%-- </c:otherwise>	
		</c:choose> --%>
		
		<div id='wrap' style="float:right;margin-right:3%;">
			<div id='calendar'></div>	
			<div style='clear:both'></div>
		</div>
		
		<table id="subjectGroupTabId" class="midsec3" style="visibility: collapse;">	
			<tr>
						<td>SubjectGroup :: </td>
						<td>
							<select id="subjectselected" name="subjectselected" class="defaultselect">
			                	<option value="" >Select</option>
			                	<c:forEach var="subGroups" items="${subjectGroupList}">
										<option value="${subGroups.subjectGroupCode}">${subGroups.subjectGroupName}</option>			
								</c:forEach>
								<option value="BREAK" >BREAK</option>
			              	</select>
						</td>			
					</tr>
		</table>
		<p/>	
		<div id='external-events' style="visibility: collapse;">
			<span class="graggableEvents">Draggable Events</span>
			<div id="evenets"></div>
		</div>
		<p/>
		<table id="subjectTabId" class="midsec3" style="visibility: collapse;">	
			<tr>
						<td>Subject:: </td>
						<td>
							<select id="subjectIndividualselected" name="subjectIndividualselected" class="defaultselect">
			                	<option value="Select" >Select</option>
			              	</select>
						</td>			
					</tr>
		</table>
		<div id='external-events-another' style="visibility: collapse;">
			<span class="graggableEvents">Draggable Events</span>
			<div id="teacher"></div>
		</div>
		<div id="CountDiv" class="CountDivCss">
		<table id="CountTable" style="visibility:collapse;" class="midsec3">
			<thead>
				<tr>
					<th>Subject</th>
					<th>TotalNo</th>		
				</tr>
			</thead>	
			<tbody>				
		       <tr id="countDetails">
		      		
		       </tr>
		      <tr></tr>  
			
			</tbody>
		</table>
		</div>
		
			<div class="btnsarea01" style="clear: both;">		
				<!-- EditButton To Go Edit Page -->
				<input type="submit" id="editButton" name="editButton" style="visibility:collapse;" onclick ="editPageShow();" value="Edit" class="editbtn"></input>
				<!-- submitButton To submit -->
				<input type="submit" id="submitButtonAtFirst" name="submitButtonAtFirst" onclick="submitInfoForSubjectAndTeacher();" style="visibility: collapse;"  value="Submit" class="submitbtn"></input>
			</div>
			<!-- Creat the time table structure pop up -->
			<div id="dialog" title="Basic dialog">	
			<table class="midsec">
			<tr>
				<th>School Start Time</th>
				<td> :: </td>
				<td><input type="text" id="SchoolStartTime" name="SchoolStartTime" value="${schoolDetails.schoolStartTime}" class="textfield1" readonly="readonly">
				<input type="hidden" id="hiddenSchoolStartTime" name="hiddenSchoolStartTime" class="textfield1" value="${schoolDetails.schoolStartTime}" readonly="readonly">
				</td>		
			</tr>
			<tr>
				<th>School End Time</th>
				<td> :: </td>
				<td><input type="text" id="SchoolEndTime" name="SchoolEndTime" class="textfield1" value="${schoolDetails.schoolEndTime}" readonly="readonly"></td>		
			</tr>
			<tr>
				<th>No Of Periods</th>
				<td> :: </td>
				<td><input type="text" id="schoolPeriodNo" class="textfield1"></td>		
			</tr>
			<tr>
				<th><input type="button" id="popupBoxYes"  value="Submit" class="submitBtnForPopUp"></input></th>
				<td>   </td>
				<td><input type="button" id="popupBoxNo" value="Cancel" class="clearBtnForPopup"></td>	
			</tr>	
			</table>
			<label id="showErrorMsg" style="visibility: collapse;"></label>
			</div>
			
			<!-- For Time Duration Pop up -->
			<div id="Duration" title="Basic dialog">	
			<table class="midsec">
		
			<tr>
				<th>Start Time</th>
				<td> :: </td>
				<td><input type="text" id="PeriodstartTime"  name="PeriodstartTime" class="textfield1"></td>		
			</tr>
			<tr>
				<th>End Time</th>
				<td> :: </td>
				<td><input type="text" id="hiddenEndTimetoset" name="hiddenEndTimetoset" class="textfield1"></td>		
			</tr>
			<tr>
				<th><input type="submit" id="popupBoxYes1" value="Submit" class="submitBtnForPopUp"></input></th>
				<td>   </td>
				<td><input type="button" id="popupBoxNo1" value="Cancel" class="clearBtnForPopup"></td>	
			</tr>	
			</table>
			<label id="showErrorMsg1"></label>
			</div>
			<!-- Hidden Fields	 -->
	<input type="hidden" id="hidvaluetoset" name="hidvaluetoset" />
	<input type="hidden" id="hiddenDatetoset" name="hiddenDatetoset" />
	<input type="hidden" id="hiddenTeachertoset" name="hiddenTeachertoset" />
	<input type="hidden" id="hiddenclasstoset" name="hiddenclasstoset" />
	<input type="hidden" id="hiddenSectiontoset" name="hiddenSectiontoset" />
	<input type="hidden" id="hiddenWorkShifttoset" name="hiddenWorkShifttoset" />
	<input type="hidden" id="hidCourseToSet" name="hidCourseToSet" />
	<input type="hidden" id="hiddenDaytoset" name="hiddenDaytoset" />
	<input type="hidden" id="hiddenperiodtoset" name="hiddenperiodtoset" />
	<input type="hidden" id="hiddenStartTimetoset" name="hiddenStartTimetoset" />
	<input type="hidden" id="hiddenPeriodEnd" name="hiddenPeriodEnd" />
	<input type="hidden" id="hiddenUpdateId" name="hiddenUpdateId" />
	<input type="hidden" id="hiddenUpdateTime" name="hiddenUpdateTime" />
	<input type="hidden" id="hiddenstreamtoset11" name="hiddenstreamtoset11" />
	<input type="hidden" id="storeDraggedEvent" name="storeDraggedEvent" />
	<input type="hidden" id="storeDraggedTeacherEvent" name="storeDraggedTeacherEvent" />
	<input type="hidden" id="storeDraggedEventCount" name="storeDraggedEventCount" />
	<input type="hidden" id="storeDraggedTeacherEventCount" name="storeDraggedTeacherEventCount" />
	<input type="hidden" id="draggedElementIdForDelete" name="draggedElementIdForDelete" />
	<input type="hidden" id="draggedElementIdForUpdate" name="draggedElementIdForUpdate" />
	<input type="hidden" id="draggedMakeUp" name="draggedMakeUp" />
	<br>
	<br>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
	</form>	
	
</body>
</html>