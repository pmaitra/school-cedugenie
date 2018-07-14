<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@ include file="/file/sessionDataForParentPage.txt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="de">
<head>
<meta charset="UTF-8" />
<link rel="icon" href="/icam/images/favicon.ico" type="image/x-icon">
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="This is Notification Page" />
<meta name="keywords" content="Notification" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>SCHOOL MANAGEMENT</title>
<link type='text/css' href='/icam/css/common/accordionStyle.css' rel='stylesheet' media='screen' />
<link rel="stylesheet" href="/icam/css/notification/inuit.css" />
<link rel="stylesheet" href="/icam/css/notification/fluid-grid16-1100px.css" />
<link rel="stylesheet" href="/icam/css/notification/eve-styles.css" />
<link href="/icam/css/notification/bootstrap-notification.css" rel="stylesheet"> <!-- height -->
<link rel="stylesheet" href="/icam/css/notification/style-notification.css"> <!-- margin -->
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />

<link type='text/css' href='/icam/css/common/osx.css' rel='stylesheet' media='screen' />
<link type='text/css' href='/icam/css/common/dropDownType1.css' rel='stylesheet' media='screen' />
<link type='text/css' href='/icam/css/common/modalTable.css' rel='stylesheet' media='screen' />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery.min.js"></script>
<script type="text/javascript" src="/icam/js/common/browserClose.js"></script>
<!-- 	Calendar -->
<link rel="stylesheet" href='/icam/css/common/popup.css'/>
<link href='/icam/css/common/calendar/fullcalendar.css' rel='stylesheet' />
<link href='/icam/css/common/calendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<script src='/icam/js/common/calendar/fullcalendar.min.js'></script>
<script src="/icam/js/common/jquery-ui.js"></script>
<link href="/icam/css/common/notificationCalendar/jquery-ui-1.9.2.custom.css" rel="stylesheet">
<script src="/icam/js/common/notificationCalendar/jquery-ui-1.9.2.custom.js"></script>

<!-- 	Calendar -->

<!--Group Chat -->
    <link type="text/css" href="/icam/css/common/chat/jquery.ui.chatbox.css" rel="stylesheet" />
    <script type="text/javascript" src="/icam/js/common/chat/jquery.ui.chatbox.js"></script>    
 	<script type="text/javascript" src="/icam/js/common/chat/groupChat.js"></script>
<!-- Group Chat Ends -->

<!-- Individual Chat  --> 
<link href="/icam/css/common/chat/individualChat.css" rel="stylesheet">
<script src="/icam/js/common/chat/jquery.ui.individualChatbox.js"></script>
<script src="/icam/js/common/chat/individualChat.js"></script>
 <!-- Individual Chat Ends --> 
<script>
function ChatDetails(){
	$.ajax({
		url: '/icam/getChatCall.html',
		data:"userName=<c:out value="${sessionScope.sessionObject.userName}"/>",			       
		success: function(dataDB) {	
			if(dataDB!= ''){	        		
				var arr=dataDB.split(":");
				var from= arr[0];
				var to = arr[1];
				$("#chatMsg").html("Individual Chat Opened For You By "+to);
				document.getElementById("chatInfo").style.display='block';	
				$("#chatInfo").fadeOut(800).fadeIn(800).fadeOut(400).fadeIn(400).fadeOut(400).fadeIn(400);
				$(".close").click(function(){
			        $("#chatInfo").animate({left:"+=10px"}).animate({left:"-5000px"});
			        setInterval(function(){},1000);
			        document.getElementById("chatInfo").style.display='none';
			    });
			}				
	   }
	});
}

	$(document).ready(function() {
		setInterval(function(){ChatDetails();},3000);				 
		$(".notificationDetails").each(function(){		
			$(this).click(function(){				
				var notificationDesc=this.id;
				var clearName=this.name;
				var clearClass=document.getElementsByName(clearName);
				for(var x=0;x<clearClass.length;x++){
					clearClass[x].removeAttribute("class");
					clearClass[x].setAttribute("class","notificationDetails");
					}	
				var desc = notificationDesc.split('~');				
				$("#dialog1").dialog({
					autoOpen: false,
					modal: true,
					resizable: false,
					minWidth:500,
					width:500,
					minHeight:300,
					height:300,			
					dialogClass: "dlg-no-close",
					buttons: {
						"Close": function() {
							$(this).dialog("close");
						}
					}
				});
				$.ajax({
					url:'/icam/getNotificationDetails.html',
					data:"notificationId="+desc[0],
					dataType: 'json'
					});						
				desc[1]=desc[1].replace(/\n/g, "<br/>");
				document.getElementById("dialog1").innerHTML = desc[1];			
				$("#dialog1").dialog("open");
		});	
				
		});

		$(".emailNotificationDetails").each(function(){		
			$(this).click(function(){				
				var emailDesc=this.id;
				var clearName=this.name;
				var clearClass=document.getElementsByName(clearName);
				for(var x=0;x<clearClass.length;x++){
					clearClass[x].removeAttribute("class");
					clearClass[x].setAttribute("class","emailNotificationDetails");
					}	
				var emailContent = emailDesc.split('~');				
				$("#dialog2").dialog({
					autoOpen: false,
					modal: true,
					resizable: false,
					minWidth:600,
					width:800,
					minHeight:400,
					height:400,			
					dialogClass: "dlg-no-close",
					buttons: {
						"Close": function() {
							$(this).dialog("close");
						}
					}
				});
				$.ajax({
					url:'/icam/changeMailReadStatus.html',
					data:"emailAlertCode="+emailContent[0]					
					});					
				emailContent[1]=emailContent[1].replace(/\n/g, "<br/>");
				document.getElementById("dialog2").innerHTML = emailContent[1];			
				$("#dialog2").dialog("open");
		});	
				
		});
		
		
	  $(".accord").dblclick(function(){
		  var hr=$(this).attr("value");
		  window.open(hr,"_parent");
	  });


	  $(".roleChange").each(function(){		
			$(this).click(function(){
				var roleName=this.name;
				if(roleName!=''){					
					$( "#roleName" ).val(roleName);					
					$( "#notificationPage" ).submit();
					}	
			});
	  }); 
	  
	  $(function(){
			$(".dropdown-menu > li > a.trigger").on("click",function(e){
				var current=$(this).next();
				var grandparent=$(this).parent().parent();
				if($(this).hasClass('left-caret')||$(this).hasClass('right-caret'))
					$(this).toggleClass('right-caret left-caret');
				grandparent.find('.left-caret').not(this).toggleClass('right-caret left-caret');
				grandparent.find(".sub-menu:visible").not(current).hide();
				current.toggle();
				e.stopPropagation();
			});
			$(".dropdown-menu > li > a:not(.trigger)").on("click",function(){
				var root=$(this).closest('.dropdown');
				root.find('.left-caret').toggleClass('right-caret left-caret');
				root.find('.sub-menu:visible').hide();
			});
		}); 
	});

		
	var readEmail=true;
	var readNotification=true;
			
	function hideEmailNotification(){
		document.getElementById("noOfEmail").style.display="none";		
		if(parseInt(<c:out value="${notification.newNotification}"/>)!=0 && readNotification){
			$("#totalNotifications").html("<c:out value="${notification.newNotification}"/>");
			readEmail=false;
			}else{
				document.getElementById("totalNotifications").style.display="none";
			}		  
	}
	
	function hideNotification(){
		document.getElementById("noOfNotification").style.display="none";
		if(parseInt(<c:out value="${notification.newEmailNotification}"/>)!=0 && readEmail){
			$("#totalNotifications").html("<c:out value="${notification.newEmailNotification}"/>");
			readNotification=false;
			}else{
				document.getElementById("totalNotifications").style.display="none";				
			}
	}
	
	$(document).ready(function() {
		var daystart;
		var dayend;
		var splitedStartHour;
		var splitedEndHour;
		
		var dayStartAnother;
		var dayStartForExam;
		var dayEndForExam;
		
		var dayStartForExamForMonth;
		var dayEndForExamForMonth;
		
		var daystart1;
		
		var daystart2;
		var dayend2;
		var splitedStartHour2;
		var splitedEndHour2;
		
		var daystart7;
		var dayend7;
		var splitedStartHour7;
		var splitedEndHour7;
		
		var dayStartAnother8;
		
		var events=[];  
		var events1=[];  
		var events2=[];  
		var eventsAnother=[];
		var eventsExam = [];
		var events7=[];
		var eventsAnother8=[];
		var sizeOfYear = "${year}";
		if(sizeOfYear != ""){
					var yearStart = "${year.sessionStartDate}";
					$("#hiddenYearstart").val(yearStart);
					var yearEnd = "${year.sessionEndDate}";
					$("#hiddenYearend").val(yearEnd);
				}
		$('#calendar').fullCalendar({
			theme: true,
			height: 450,
			contentHeight: 420,			
			titleFormat  : {
				month: 'MMM yyyy', 		
				},
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
				
			},			
			editable: false,			
			selectable:false,
			dayClick: function(date, allDay, jsEvent, view) {
				$('#calendar')
	                .fullCalendar('changeView', 'agendaDay'/* or 'basicDay' */)
	                .fullCalendar('gotoDate',
	                    date.getFullYear(), date.getMonth(), date.getDate());	     
			    },
				select: function(start, end, allDay) {
					$('#calendar').fullCalendar('changeView', 'agendaDay'/* or 'basicDay' */).fullCalendar( 'gotoDate', start );	
				},
			    eventClick: function(calEvent, jsEvent, view) {
			    	
			    	$('#calendar').fullCalendar('changeView', 'agendaDay'/* or 'basicDay' */).fullCalendar( 'gotoDate', calEvent.start );	
			    },
		    viewDisplay: function(view) {
		    	var TotalCount = 0;
		    	var TotalCountForAll = 0;
		    	var TotalCountForAll8 = 0;
		    	var eventOBJ = "${eventFromDb}";
		    	var sizeAnother = "${eventForAllUserFromDb.size()}";
		    	var sizeCount = "${eventCountForAllUserFromDb.size()}";
		    	var sizeCountAnother = "${listAssignedExam.size()}";
		    	var currentRole ="${sessionScope.sessionObject.currentRoleOrAccess}";
				var eventForSuperAdmin ="${eventForSuperadminFromDb.size()}";
				var eventCountForSuperadminFromDb ="${eventCountForSuperadminFromDb.size()}";
		    	if(view.name != "month"){
		    		$('#calendar').fullCalendar('removeEvents');
		    		if(currentRole == 'SUPER ADMIN'){
		    			if(eventForSuperAdmin > 0){
		    				<c:forEach var="allCalendarEvent" items="${eventForSuperadminFromDb}">
		    				var eventObject7 = new Object();
		    				eventObject7.id = 7;
							var strt7 = "<c:out value="${allCalendarEvent.calendarEventStartDate}"/>";
							var end7 = "<c:out value="${allCalendarEvent.calendarEventEndDate}"/>";
							var startTime7 = "<c:out value="${allCalendarEvent.calendarEventStartTime}"/>";
							var endTime7 = "<c:out value="${allCalendarEvent.calendarEventEndTime}"/>";
							var subjectTitle7 = "<c:out value="${allCalendarEvent.calendarEventName}"/>";
							var eventViewer = "<c:out value="${allCalendarEvent.calendarEventDesc}"/>";
							daystart7 = strt7.split("/");
							dayend7 = end7.split("/");
							splitedStartHour7 = startTime7.split("$");
							splitedEndHour7 = endTime7.split("$");
							eventObject7.title = subjectTitle7;
							eventObject7.start= new Date(daystart7[2],daystart7[1]-1, daystart7[0],splitedStartHour7[0],splitedStartHour7[1],1);
							eventObject7.end=  new Date(dayend7[2],dayend7[1]-1, dayend7[0],splitedEndHour7[0],splitedEndHour7[1],1);
							//eventObject.color="<c:out value="${calendarEvent.calendarEventEndColor}"/>";
							if(eventViewer == 'Roll Based'){
								eventObject7.color="#3C0";
								eventObject7.className = "eventRollBased";
							}
							if(eventViewer == 'All User'){
								eventObject7.color="#F60";
								eventObject7.className = "eventAll";
							}
							eventObject7.textColor = "#000000";
							eventObject7.allDay = false; 
							events7.push(eventObject7);
							$('#calendar').fullCalendar('renderEvent', eventObject7, true);
		    				</c:forEach>
		    			}
		    		}
		    		if(currentRole != 'SUPER ADMIN'){
		    		if(eventOBJ != null){
				    	<c:forEach var="role" items="${eventFromDb}">
					    	var size1 = "${role.calendarEventList.size()}";
					    	if(size1 > 0){
					    	<c:forEach var="calendarEvent" items="${role.calendarEventList}">
					    		var eventObject = new Object();
								eventObject.id = "<c:out value="${calendarEvent.calendarEventCode}"/>";
								var strt = "<c:out value="${calendarEvent.calendarEventStartDate}"/>";
								var end = "<c:out value="${calendarEvent.calendarEventEndDate}"/>";
								var startTime = "<c:out value="${calendarEvent.calendarEventStartTime}"/>";
								var endTime = "<c:out value="${calendarEvent.calendarEventEndTime}"/>";
								var subjectTitle = "<c:out value="${calendarEvent.calendarEventName}"/>";
								daystart = strt.split("/");
								dayend = end.split("/");
								splitedStartHour = startTime.split("$");
								splitedEndHour = endTime.split("$");
								eventObject.title = subjectTitle;
								eventObject.start= new Date(daystart[2],daystart[1]-1, daystart[0],splitedStartHour[0],splitedStartHour[1],1);
								eventObject.end=  new Date(dayend[2],dayend[1]-1, dayend[0],splitedEndHour[0],splitedEndHour[1],1);
								//eventObject.color="<c:out value="${calendarEvent.calendarEventEndColor}"/>";
								eventObject.color="#3C0";
								eventObject.textColor = "#000000";
								eventObject.allDay = false; 
								events.push(eventObject);
								$('#calendar').fullCalendar('renderEvent', eventObject, true);
							</c:forEach>
					    	}
						</c:forEach>
		    		}
		    		if(sizeAnother > 0){
					<c:forEach var="eventForAllUser" items="${eventForAllUserFromDb}">
						var eventObject3 = new Object();
						eventObject3.id = "<c:out value="${eventForAllUser.calendarEventCode}"/>";
						var strt3 = "<c:out value="${eventForAllUser.calendarEventStartDate}"/>";
						var end3 = "<c:out value="${eventForAllUser.calendarEventEndDate}"/>";
						var startTime3 = "<c:out value="${eventForAllUser.calendarEventStartTime}"/>";
						var endTime3 = "<c:out value="${eventForAllUser.calendarEventEndTime}"/>";
						var subjectTitle3 = "<c:out value="${eventForAllUser.calendarEventName}"/>";
						daystart2 = strt3.split("/");
						dayend2 = end3.split("/");
						splitedStartHour2 = startTime3.split("$");
						splitedEndHour2 = endTime3.split("$");
						eventObject3.title = subjectTitle3;
						eventObject3.start= new Date(daystart2[2],daystart2[1]-1, daystart2[0],splitedStartHour2[0],splitedStartHour2[1],1);
						eventObject3.end=  new Date(dayend2[2],dayend2[1]-1, dayend2[0],splitedEndHour2[0],splitedEndHour2[1],1);
						//eventObject3.color="<c:out value="${eventForAllUser.calendarEventEndColor}"/>";
						eventObject3.color="#F60";
						eventObject3.textColor = "#000000";
						eventObject3.allDay = false; 
						events2.push(eventObject3);
						$('#calendar').fullCalendar('renderEvent', eventObject3, true);	
	  				</c:forEach>
		    		}
		    		}


		    		/* if(sizeCountAnother > 0){
		    			<c:forEach var="AssignedExam" items="${listAssignedExam}">
							var eventObject6 = new Object();
							//eventObject6.id = "<c:out value="${AssignedExam.examCode}"/>";
							eventObject6.id = 1;
							var startDate = "<c:out value="${AssignedExam.examStartDate}"/>";
							var endDate = "<c:out value="${AssignedExam.examEndDate}"/>";
							dayStartForExam = startDate.split("/");
							dayEndForExam = endDate.split("/");
			
							eventObject6.title = "<c:out value="${AssignedExam.examName}"/>";
							eventObject6.start= new Date(dayStartForExam[2],dayStartForExam[1]-1, dayStartForExam[0],0,0,1);
							eventObject6.end=  new Date(dayEndForExam[2],dayEndForExam[1]-1, dayEndForExam[0],0,0,60);
							eventObject6.color="#3399FF";
							eventObject6.textColor = "#FFFFFF";
							eventObject6.className = "examEvent";
							eventObject6.borderColor= '#F60';
							eventObject6.allDay = true; 
							eventsExam.push(eventObject6);
							$('#calendar').fullCalendar('renderEvent', eventObject6, true);	
				   		</c:forEach>
		    		} */
				}
		    	if(view.name == "month"){
		    		$('#calendar').fullCalendar('removeEvents');
		    		if(currentRole == 'SUPER ADMIN'){
		    			if(eventCountForSuperadminFromDb > 0){
		    				<c:forEach var="allCalendarEvent" items="${eventCountForSuperadminFromDb}">
			    				var eventObject8 = new Object();
								eventObject8.id = 2;
								var strt8 = "<c:out value="${allCalendarEvent.calendarEventObjectId}"/>";
								var countTitle8 = "<c:out value="${allCalendarEvent.calendarIntEventCode}"/>";
								var eventViewer = "<c:out value="${allCalendarEvent.calendarEventDesc}"/>"
								dayStartAnother8 = strt8.split("/");
								countTitle8 = parseInt(countTitle8);
								TotalCountForAll8 = countTitle8;
								var title8 = TotalCountForAll8.toString();
								eventObject8.title = title8;
								eventObject8.start= new Date(dayStartAnother8[2],dayStartAnother8[1]-1, dayStartAnother8[0],0,0,1);
								eventObject8.end=  new Date(dayStartAnother8[2],dayStartAnother8[1]-1, dayStartAnother8[0],0,0,60);
								if(eventViewer == 'Roll Based'){
									eventObject8.color="#666666";
									eventObject8.textColor = "#FFFFFF";
									eventObject8.className = "eventRollBased";
									eventObject8.borderColor= '#3C0';
								}
								if(eventViewer == 'All User'){
									eventObject8.color="#666666";
									eventObject8.textColor = "#FFFFFF";
									eventObject8.className = "eventAll";
									eventObject8.borderColor= '#F60';
								}
								eventObject8.allDay = true; 
								eventsAnother8.push(eventObject8);
								$('#calendar').fullCalendar('renderEvent', eventObject8, true);	
		    				</c:forEach>
		    			}
		    		}
		    		if(currentRole != 'SUPER ADMIN'){
		    		if(eventOBJ != null){
			    		var eventObject1 = null;
			    		<c:forEach var="role" items="${eventFromDb}">
				    		var size = "${role.calendarEventCountList.size()}";
				    		if(size > 0){
			    			<c:forEach var="calendarEvent" items="${role.calendarEventCountList}">
					    		eventObject1 = new Object();
								eventObject1.id = 1;
								var strt1 = "<c:out value="${calendarEvent.calendarEventObjectId}"/>";
								var countTitle = "<c:out value="${calendarEvent.calendarIntEventCode}"/>";
								countTitle = parseInt(countTitle);
								TotalCount = countTitle;
								var title1 = TotalCount.toString();
								daystart1 = strt1.split("/");
								eventObject1.title = title1;
								eventObject1.className = "eventRollBasedClass";
								eventObject1.start= new Date(daystart1[2],daystart1[1]-1, daystart1[0],0,0,1);
								eventObject1.end=  new Date(daystart1[2],daystart1[1]-1, daystart1[0],0,0,60);
								eventObject1.color="#666666";
								eventObject1.textColor = "#FFFFFF";
								eventObject1.className = "eventRollBased";
								eventObject1.borderColor= '#3C0';
								eventObject1.allDay = true; 
								events1.push(eventObject1);
								$('#calendar').fullCalendar('renderEvent', eventObject1, true);	
							</c:forEach>
							}
						</c:forEach>
						
		    		}
		    		if(sizeCount > 0){
						<c:forEach var="eventCountForAllUser" items="${eventCountForAllUserFromDb}">
							var eventObject2 = new Object();
							eventObject2.id = 2;
							var strt2 = "<c:out value="${eventCountForAllUser.calendarEventObjectId}"/>";
							var countTitle = "<c:out value="${eventCountForAllUser.calendarIntEventCode}"/>";
							dayStartAnother = strt2.split("/");
							countTitle = parseInt(countTitle);
							TotalCountForAll = countTitle;
							var title1 = TotalCountForAll.toString();
							eventObject2.title = title1;
							eventObject2.start= new Date(dayStartAnother[2],dayStartAnother[1]-1, dayStartAnother[0],0,0,1);
							eventObject2.end=  new Date(dayStartAnother[2],dayStartAnother[1]-1, dayStartAnother[0],0,0,60);
							eventObject2.color="#666666";
							eventObject2.textColor = "#FFFFFF";
							eventObject2.className = "eventAll";
							eventObject2.borderColor= '#F60';
							eventObject2.allDay = true; 
							eventsAnother.push(eventObject2);
							$('#calendar').fullCalendar('renderEvent', eventObject2, true);	
					   </c:forEach>
		    		}
		    		}

		    		/* if(sizeCountAnother > 0){
		    			<c:forEach var="AssignedExam" items="${listAssignedExam}">
							var eventObject5 = new Object();
							eventObject5.id = "<c:out value="${AssignedExam.examCode}"/>";
							
							var startDate = "<c:out value="${AssignedExam.examStartDate}"/>";
							var endDate = "<c:out value="${AssignedExam.examEndDate}"/>";
							dayStartForExamForMonth = startDate.split("/");
							dayEndForExamForMonth = endDate.split("/");
			
							eventObject5.title = "<c:out value="${AssignedExam.examName}"/>";
							eventObject5.start= new Date(dayStartForExamForMonth[2],dayStartForExamForMonth[1]-1, dayStartForExamForMonth[0],0,0,1);
							eventObject5.end=  new Date(dayEndForExamForMonth[2],dayEndForExamForMonth[1]-1, dayEndForExamForMonth[0],0,0,60);
							eventObject5.color="#3399FF";
							eventObject5.textColor = "#FFFFFF";
							eventObject5.className = "examEvent";
							eventObject5.borderColor= '#F60';
							eventObject5.allDay = true; 
							eventsExam.push(eventObject5);
							$('#calendar').fullCalendar('renderEvent', eventObject5, true);	
					   </c:forEach>
		    		} */
		    	}
		    	
		    	 if(sizeCountAnother > 0){
		    		 if(view.name == "month"){
		    			//$('#calendar').fullCalendar('removeEvents');
						<c:forEach var="AssignedExam" items="${listAssignedExam}">
							var eventObject5 = new Object();
							eventObject5.id = "<c:out value="${AssignedExam.examCode}"/>";
							
							var startDate = "<c:out value="${AssignedExam.examStartDate}"/>";
							var endDate = "<c:out value="${AssignedExam.examEndDate}"/>";
							dayStartForExamForMonth = startDate.split("/");
							dayEndForExamForMonth = endDate.split("/");
			
							eventObject5.title = "<c:out value="${AssignedExam.examName}"/>";
							eventObject5.start= new Date(dayStartForExamForMonth[2],dayStartForExamForMonth[1]-1, dayStartForExamForMonth[0],0,0,1);
							eventObject5.end=  new Date(dayEndForExamForMonth[2],dayEndForExamForMonth[1]-1, dayEndForExamForMonth[0],0,0,60);
							eventObject5.color="#3399FF";
							eventObject5.textColor = "#FFFFFF";
							eventObject5.className = "examEvent";
							eventObject5.borderColor= '#F60';
							eventObject5.allDay = true; 
							eventsExam.push(eventObject5);
							$('#calendar').fullCalendar('renderEvent', eventObject5, true);	
					   </c:forEach>
		    		} 
		    		if(view.name != "month"){
		    			<c:forEach var="AssignedExam" items="${listAssignedExam}">
							var eventObject6 = new Object();
							//eventObject6.id = "<c:out value="${AssignedExam.examCode}"/>";
							eventObject6.id = 1;
							var startDate = "<c:out value="${AssignedExam.examStartDate}"/>";
							var endDate = "<c:out value="${AssignedExam.examEndDate}"/>";
							dayStartForExam = startDate.split("/");
							dayEndForExam = endDate.split("/");
			
							eventObject6.title = "<c:out value="${AssignedExam.examName}"/>";
							eventObject6.start= new Date(dayStartForExam[2],dayStartForExam[1]-1, dayStartForExam[0],0,0,1);
							eventObject6.end=  new Date(dayEndForExam[2],dayEndForExam[1]-1, dayEndForExam[0],0,0,60);
							eventObject6.color="#3399FF";
							eventObject6.textColor = "#FFFFFF";
							eventObject6.className = "examEvent";
							eventObject6.borderColor= '#F60';
							eventObject6.allDay = true; 
							eventsExam.push(eventObject6);
							$('#calendar').fullCalendar('renderEvent', eventObject6, true);	
				   		</c:forEach>
		    		}
	    		} 
		    }
		});	
	});
</script>



<c:if test="${fn:length(roleList) lt 3 }">	
			<script>
				$(function() {	
					$('#st-accordion').accordion({
						 // index of opened item. -1 means all are closed by default.
					    	open            : 0,
					    // if set to true, only one item can be opened.
					    // Once one item is opened, any other that is
					    // opened will be closed first
					    	oneOpenedItem   : true,
					    // speed of the open / close item animation
						//  speed           : 600,
					    // easing of the open / close item animation
						// 	easing          : 'easeInOutExpo',
					    // speed of the scroll to action animation
						//  scrollSpeed     : 900,
					    // easing of the scroll to action animation
						// scrollEasing    : 'easeInOutExpo'
					});		
				});
			</script>
</c:if>	
<c:if test="${fn:length(roleList) gt 2}">	
			<script>
			$(function() {	
				$('#st-accordion').accordion({
					oneOpenedItem	: true	
				});
			});
			</script>
</c:if>

<script>
$(document).ready(function(){
	$("#totalNotifications").html("<c:out value="${notification.newNotification+notification.newEmailNotification}"/>");

	// hide #back-top first
	$("#back-top").hide();	
	// fade in #back-top
	$(function () {
		$(window).scroll(function () {
			if ($(this).scrollTop() > 100) {
				$('#back-top').fadeIn();
			} else {
				$('#back-top').fadeOut();
			}
		});

		// scroll body to 0px on click
		$('#back-top a').click(function () {
			$('body,html').animate({
				scrollTop: 0
			}, 500);
			return false;
		});
	});
});
</script>

<style>
	#calendar {
		width: 500px;	
		cursor: pointer;	
		}
		
	.urgentNotification{
		text-decoration: none;
		font-size:20px;
		cursor: pointer;
	}
	
	.emailDetailsUnread{
		text-decoration: none;
		font-size:20px;
		cursor: pointer;
	}
	#chatInfo{
    border: 1px solid;
    margin: 10px 0px;
    padding:15px 10px 15px 50px;
    background-repeat: no-repeat;
    background-position: 10px center;
    position:relative;
    color: #00529B;    
	background-color: #dfeff5;
    background-image: url(/icam/images/info.png);
    display: none;
}
</style>

<!--[if IE]>
<script src="js/html5.js"></script>
<![endif]-->
<!--<link href="css/bootstrap.css" rel="stylesheet">-->
<!--<link href="css/bootstrap-responsive.css" rel="stylesheet">-->
<!--Hide the hr img because of ugly borders in IE7. You can change the color of border-top to display a line -->
<!--[if lte IE 7]>
<style>
    hr { display:block; height:1px; border:0; border-top:1px solid #fff; margin:1em 0; padding:0; }
    .grid-4{ width:22% }
</style>
<![endif]-->
</head>
<body>
<c:if test="${PasswordChangeFailStatus ne null}">
<div class="ui-widget" style="font-family: bahamas">
	<div style="padding: 0 .7em;" class="ui-state-error ui-corner-all">
		<p><span style="float: left; margin-right: .3em;" class="ui-icon ui-icon-alert"></span>
		<strong>Error:</strong> Password not updated</p>
	</div>
</div>
</c:if>
<!-- modal content.. On click of My Profile Tool Bar shows the Following data -->
		<div id="osx-modal-content">
			<div id="osx-modal-title">Hi ! ${sessionScope.sessionObject.userName}</div>
			<div class="close"><a href="#" class="simplemodal-close">x</a></div>
			<div id="osx-modal-data">
				<h1>User Profile</h1>
				<c:choose>
					<c:when test="${null ne resource.image.imagepath}">			
						<img src="data:image/jpg;base64, ${resource.image.imagepath}" alt="No Image" style="min-height:50px;min-width:50px; height: 80px; width:80px; float:right;" />
					</c:when>
					<c:otherwise>
							<c:choose>
								<c:when test="${fn:toLowerCase('FEMALE') eq fn:toLowerCase(resource.gender)}">			
									<img src="StaffImage/female_default_images.jpg" alt="No Image" style="min-height:50px;min-width:50px; height: 80px; width:80px; float:right;" />
								</c:when>
								<c:otherwise>
									<img src="StaffImage/male_default_images.jpg" alt="No Image" style="min-height:50px;min-width:50px; height: 80px; width:80px; float:right;"/>	
								</c:otherwise>
							</c:choose>
					</c:otherwise>	
				</c:choose>			
				<h6>Your Current Designation is ${sessionScope.sessionObject.currentRoleOrAccess}</h6>					
					<table id="modaltable" cellspacing='0'> <!-- cellspacing='0' is important, must stay -->
						<tr><th>Name</th><td>${resource.name}</td></tr>
						
						<c:if test="${resource.fatherFirstName ne null  && resource.fatherFirstName ne ''}">
						<tr class='even'><th>Fathers Name</th><td>${resource.fatherFirstName}</td></tr>					
						</c:if>
						<c:if test="${resource.dateOfBirth ne null  && resource.dateOfBirth ne ''}">
						<tr><th>Date Of Birth</th><td>${resource.dateOfBirth}</td></tr>
						</c:if>
						<c:if test="${resource.status ne null && resource.status ne ''}">
						<tr class='even'><th>Address</th><td>${resource.status}</td></tr>
						</c:if>
						<c:if test="${resource.emailId ne null && resource.emailId ne ''}">
						<tr><th>Contact Details</th><td>
						<a style="color:red;" href="mailto:${resource.emailId}" target="_top">${resource.emailId}</a> / ${resource.mobile}</td>
						</tr>
						</c:if>	
					</table>			
				<p><button class="simplemodal-close">Close</button> <span>(or press ESC or click the overlay)</span></p>
			</div>
		</div>
		
		<!-- In HTML-->
		<div id="osx-modal-content-password">
			<div id="osx-modal-title-password">Hi ! ${sessionScope.sessionObject.userName}</div>
			<div class="close"><a href="#" class="simplemodal-close-password">x</a></div>
			<div id="osx-modal-data-password">
			<h1>Change Password</h1><br/>
					<form:form method="POST" name="changeUserPassword" id="changeUserPassword"  action="updatePassword.html">
						<table id="modaltable-password" cellspacing='0'> <!-- cellspacing='0' is important, must stay -->
							<tr>
								<th>
									Current Password :<input type="hidden"  name="status" value="changeUserPassword"> 
								</th>
							<td>
									<input type="password" class="txtfld" id="password" name="password" autocomplete="off">  
								</td>		
							</tr>
							<tr>
								<th>
									New Password :
								</th>
								<td>
									<input type="password" class="txtfld" id="newPassword" name="newPassword">
								</td>		
							</tr>
							<tr>
								<th>
									Retype New Password :
								</th>
								<td>
									<input type="password" class="txtfld" id="reTypeNewPassword" name="reTypeNewPassword">   
								</td>		
							</tr>		
						</table>
							<input type="reset" value="Reset" class="clearbtn" style="float:right">  
					 		<input type="submit" id="changePassword" name="change" value="Update" class="editbtn" style="float:right"> 
					</form:form> 
				<p><button class="simplemodal-close">Close</button> <span>(or press ESC or click the overlay)</span></p>
			</div>
		</div>
		
		
<!-- Email-->
		
		<div id="osx-modal-content-email">
		<div id="osx-modal-title-email">Hi ! ${sessionScope.sessionObject.userName}</div>
		<div class="close"><a href="#" class="simplemodal-close-email">x</a></div>
		<div id="osx-modal-data-email">
			<h1>Your Inbox</h1><br/>
				<c:choose>
				<c:when test="${emailDetailsList == null  && fn:length(emailDetailsList) lt 1}">
						  <div class="errorbox" id="errorbox"  style="visibility:visible;">
							<span id="errormsg">No mail in your inbox</span>	
						  </div> 
						  <br/><br/> 				
				 </c:when> 
				 <c:otherwise>
					<table id="modaltable-email" cellspacing='0'>
						<tr>
							<th>Date &amp; Time</th>
							<th>Subject</th>
							<th>Sender</th>
						</tr>
						<c:forEach var="emailDetails" items="${emailDetailsList}" varStatus="i" begin="0" end="5">			
							<tr>					
								<td>
									<c:choose>
										<c:when test="${emailDetails.status eq 'A'}">
											<a class="emailDetailsUnread emailNotificationDetails" href="#" name="mailData${i.index}" id="${emailDetails.emailDetailsCode}~${emailDetails.emailDetailsDesc}">${emailDetails.time}</a>
										</c:when>
									<c:otherwise>
											<a class="emailNotificationDetails" href="#" name="mailData${i.index}" id="${emailDetails.emailDetailsCode}~${emailDetails.emailDetailsDesc}">${emailDetails.time}</a>
									</c:otherwise>
									</c:choose>
								</td>			
								<td>
									<c:choose>
											<c:when test="${emailDetails.status eq 'A'}">
												<a class="emailDetailsUnread emailNotificationDetails" href="#" name="mailData${i.index}" id="${emailDetails.emailDetailsCode}~${emailDetails.emailDetailsDesc}">${emailDetails.emailDetailsSubject}</a>
											</c:when>
											<c:otherwise>
												<a class="emailNotificationDetails" href="#" name="mailData${i.index}" id="${emailDetails.emailDetailsCode}~${emailDetails.emailDetailsDesc}">${emailDetails.emailDetailsSubject}</a>
											</c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:choose>
										<c:when test="${emailDetails.status eq 'A'}">
											<a class="emailDetailsUnread emailNotificationDetails" href="#" name="mailData${i.index}" id="${emailDetails.emailDetailsCode}~${emailDetails.emailDetailsDesc}">${emailDetails.emailDetailsSender}</a>
										</c:when>
									<c:otherwise>
											<a class="emailNotificationDetails" href="#" name="mailData${i.index}" id="${emailDetails.emailDetailsCode}~${emailDetails.emailDetailsDesc}">${emailDetails.emailDetailsSender}</a>
									</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>							
					</table><br/>
				</c:otherwise>
				</c:choose>
				
				<c:if test="${fn:length(notificationList) gt 6}">
				  <div class="infomsgbox" id="infomsgbox"  style="visibility:visible;">
					<span id="infomsg">To View All Mails,Visit My Services</span>	
				  </div> 
				  <br/> 				
				</c:if>
			<p><button class="simplemodal-close">Close</button> <span>(or press ESC or click the overlay)</span></p>
		</div>
		</div>

	<!-- Email Ends-->	

	<div id="osx-modal-content-notification">
	<div id="osx-modal-title-notification">Hi ! ${sessionScope.sessionObject.userName}</div>
	<div class="close"><a href="#" class="simplemodal-close-notification">x</a></div>
	<div id="osx-modal-data-notification">
		<h1>Your Notifications</h1><br/>					
			<c:choose>
			<c:when test="${notificationList == null  && fn:length(notificationList) lt 1}">
					  <div class="errorbox" id="errorbox"  style="visibility:visible;">
						<span id="errormsg">No notification available for you</span>	
					  </div> 
					  <br/><br/> 				
			 </c:when> 
			 <c:otherwise>		
				<table id="modaltable-notification" cellspacing='0'>
						<tr>
							<th>Date &amp; Time</th>
							<th>Subject</th>
							<th>Sender</th>
						</tr>
						<c:forEach var="notif" items="${notificationList}" varStatus="i" begin="0" end="5">	
						<c:choose>
							<c:when test="${notif.status == 'A'}">
							<tr onclick="location.href='#'" style="cursor: pointer;" id="${notif.notificationId}~${notif.notificationDesc}"  name="data${i.index}" class="notificationDetails urgentNotification">							
								<td>						
									<a href="#" id="${notif.notificationId}~${notif.notificationDesc}"  name="data${i.index}" class="notificationDetails urgentNotification" >
									${notif.notificationDate}
									</a>											
								</td>					
								<td>						
									<a href="#" id="${notif.notificationId}~${notif.notificationDesc}"  name="data${i.index}" class="notificationDetails urgentNotification" >
										${notif.notificationSubject}
									</a>						
								</td>
								<td>						
									<a href="#" id="${notif.notificationId}~${notif.notificationDesc}"  name="data${i.index}" class="notificationDetails urgentNotification" >
									${notif.notificationSender}	
									</a>									
								</td>							
							</tr>								
							</c:when>	
							<c:otherwise>
							<tr onclick="location.href='#'" style="cursor: pointer;" id="${notif.notificationId}~${notif.notificationDesc}"  name="data${i.index}" class="notificationDetails">
								<td>						
									<a href="#" id="${notif.notificationId}~${notif.notificationDesc}"  name="data${i.index}" class="notificationDetails">${notif.notificationDate}</a>						
								</td>
								<td>						
									<a href="#" id="${notif.notificationId}~${notif.notificationDesc}"  name="data${i.index}" class="notificationDetails">${notif.notificationSubject}</a>						
								</td>
								<td>
									<a href="#" id="${notif.notificationId}~${notif.notificationDesc}"  name="data${i.index}" class="notificationDetails">${notif.notificationSender}</a>													
								</td>
							</tr>
							</c:otherwise>
							</c:choose>						
						</c:forEach>		
				</table>
			</c:otherwise>
			</c:choose>	<br/>
			
			<c:if test="${fn:length(notificationList) gt 6}">
					  <div class="infomsgbox" id="infomsgbox"  style="visibility:visible;">
						<span id="infomsg">To View Previous Notifications,Visit My Services To Find More</span>	
					  </div> 
					  <br/> 				
			 </c:if>			  
			<p><button class="simplemodal-close">Close</button> <span>(or press ESC or click the overlay)</span></p>
		</div>
		</div>

<!-- End Of modal content -->

<div id="dialog1" title="Notification"></div>
<div id="dialog2" title="Email Inbox"></div>

<form id="notificationPage" name="notificationPage" action="changeRoleForUser.html" method="post">
<!--Tools Cofig area prsent on the right most top corner -->
	<section class="wrapper" id="top" > 
	<div class="headrcon">
			<div id="schoolLogo" style="float: left; margin-left: 20px;"><img title="school management" style="height:40px;width:140px;float:left;" alt="school management" src="/icam/images/logo.jpg"></div>       
        	<div id="user-nav" class="navbar navbar-inverse">
              <ul class="nav">
                <li  class="dropdown" id="profile-messages" ><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user icon-2x"></i>  <span class="text">Welcome User</span><b class="caret"></b></a>
                  <ul class="dropdown-menu">
                    <li><a href="#" class='osx'><i class="icon-user"></i> My Profile</a></li>
                    <li class="divider"></li>
                    <li><a href="#" class='osx1'><i class="icon-key"></i> Change Password</a></li>
                    <li class="divider"></li>                                        
                    <li><a href="logOut.html"><i class="icon icon-share-alt"></i> Log Out</a></li>
                     <li class="divider"></li>
                  </ul>
                </li>
                <li class="dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-wrench icon-2x"></i> <span class="text" >Roles And Access Type</span><b class="caret"></b></a>
                  <ul class="dropdown-menu">
                  	<li><a class="sAdd" title="" href="#"><i class="icon icon-dashboard"></i> Current Role ::<span style="font-family:'bahamas'; color: #445fa2;text-transform: lowercase;font-size:16px;"> ${sessionScope.sessionObject.currentRoleOrAccess}</span></a></li>
                    <li class="divider"></li>                    
					<li>
						<a class="trigger right-caret">Available Roles ::</a>
						<ul class="dropdown-menu sub-menu">
							<c:forEach var="availableRolesAndAccess" items="${sessionScope.sessionObject.availableRoles}">				
								<c:if test="${sessionScope.sessionObject.currentRoleOrAccess ne availableRolesAndAccess}">
									<li><a href="#" name="<c:out value="${availableRolesAndAccess}" />" class="roleChange" ><c:out value="${availableRolesAndAccess}" /></a></li>
								</c:if>
							</c:forEach>							
						</ul>
					</li>					
				</ul>
                </li>
                <c:if test="${not empty sessionScope.sessionObject}">	                
	                 <li  class="dropdown" id="chatmenu"><a title="" href="#" data-toggle="dropdown" data-target="#chatmenu" class="dropdown-toggle">
	                 	<c:if test="${notification.newNotification+notification.newEmailNotification ne 0}">
											<span id="totalNotifications" style="font-size: large;color: black;" class="label label-important"></span>
						</c:if>
	                 	<i class="icon icon-envelope icon-2x"></i><span class="text">             	
	                 		&nbsp;Chat &amp; Mesaage</span><b class="caret"></b></a>		                  
		                  <ul class="dropdown-menu">
			                    <li>
			                    	<a href="#" class='osx3' onclick="hideNotification();">
				                    <i class="icon icon-globe"></i>									
				                    Notification Alert
				                    	<c:if test="${notification.newNotification ne 0}">
										<span id="noOfNotification" style="font-size:medium ;color: black;"  class="label label-important">${notification.newNotification}</span>
										</c:if>				                    
				                    </a>
			                    </li>
			                    <li class="divider"></li>			                    	                    
			                    <li>
			                    	<a href="#" class='osx2' onclick="hideEmailNotification();">
			                    	<i class="icon-inbox"></i>				                    
				                    &nbsp;E-Mail
				                    	<c:if test="${notification.newEmailNotification ne 0}">
											<span id="noOfEmail" style="font-size:medium;color: black;" class="label label-important">${notification.newEmailNotification}</span>
										</c:if>
			                    	</a>
			                    </li>
			                    <li class="divider"></li>
			                    <li><a href="#" class="minibtn"><i class="icon-comment"></i>&nbsp;Individual Chat</a></li>
			                    <li class="divider"></li>
			                    <li><a href="#" id="groupChat"><i class="icon-group"></i>&nbsp;Group Chat</a></li>
			                    <li class="divider"></li>
		                  </ul>
	                </li>
				 </c:if>
              </ul>
            </div>        
      </div>
 <!--Tool bar Config area ends--> 
     
    
	<div class="conarea">
			<!-- Header starts.. Shows Module Navigation Tabs according to user Profile -->  
			<header> 					
				<div class="navareacon">			
					<div class="navbar navbar-default">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
								<span class="icon-bar"></span> <span class="icon-bar"></span> <span	class="icon-bar"></span>
							</button>
						</div>
						<div class="navbar-collapse collapse">
						<ul class="nav navbar-nav">	
						<c:forEach var="role" items="${roleList}">	
								<c:if test="${role.moduleName eq 'ADMISSION'}">
									<li><a href="getModuleDetails.html?moduleName=ADMISSION">Admission</a></li>
								</c:if>
								<c:if test="${role.moduleName eq 'LIBRARY'}">
									<li><a href="getModuleDetails.html?moduleName=LIBRARY">Library</a></li>
								</c:if>
								<c:if test="${role.moduleName eq 'OFFICE ADMINISTRATION'}">
									<li><a href="getModuleDetails.html?moduleName=OFFICE ADMINISTRATION">General Section</a></li>
								</c:if>
								<c:if test="${role.moduleName eq 'ACADEMICS'}">
									<li><a href="getModuleDetails.html?moduleName=ACADEMICS">Academics</a></li>
								</c:if>
								<c:if test="${role.moduleName eq 'ERP'}">
									<li><a href="getModuleDetails.html?moduleName=ERP">ERP</a></li>
								</c:if>
								<c:if test="${role.moduleName eq 'HOSTEL'}">	
									<li><a href="getModuleDetails.html?moduleName=HOSTEL">Hostel</a></li>
								</c:if>
								<c:if test="${role.moduleName eq 'FINANCE'}">
									<li><a href="getModuleDetails.html?moduleName=FINANCE">Finance</a></li>
								</c:if>
								<c:if test="${role.moduleName eq 'SYSTEM ADMINISTRATION'}">
									<li><a href="getModuleDetails.html?moduleName=SYSTEM ADMINISTRATION">System Administration</a></li>
								</c:if>
								
								<c:if test="${role.moduleName eq 'TICKETING'}">
									<li><a href="getModuleDetails.html?moduleName=TICKETING">Ticketing</a></li>
								</c:if>
								<c:if test="${role.moduleName eq 'INVENTORY'}">
									<li><a href="getModuleDetails.html?moduleName=INVENTORY">Inventory</a></li>
								</c:if>
						</c:forEach>
							<li><a href="getModuleDetails.html?moduleName=REPORT">Report</a></li>
							<li><a href="userServices.html">My Services</a></li>
						</ul>
						</div>			
					</div>						
				</div>											
			<!--Module Navigation Tabs end--> 
				
			<!--Shows image of the user on the right most part of header-->	
				<div class="headrconimage">
					<div class="wlcmimg">
						
						<c:choose>
							<c:when test="${null ne resource.image.imagepath}">			
								<img src="data:image/jpg;base64, ${resource.image.imagepath}" alt="No Image" />
							</c:when>
							<c:otherwise>
									<c:choose>
										<c:when test="${fn:toLowerCase('FEMALE') eq fn:toLowerCase(resource.gender)}">			
											<img src="StaffImage/female_default_images.jpg" alt="No Image" />
										</c:when>
										<c:otherwise>
											<img src="StaffImage/male_default_images.jpg" alt="No Image" />	
										</c:otherwise>
									</c:choose>
							</c:otherwise>	
						</c:choose>
					</div>
					<c:if test="${not empty sessionScope.sessionObject}">
  						<div class="wlcmtext">
							<h1>Welcome , <c:out value="${sessionScope.sessionObject.userName}" /></h1>							
							<c:if test="${sessionScope.sessionObject.lastVisitedOn ne null}">
							  <span id="lastVisitedOn">Previous Log in at <c:out value="${sessionScope.sessionObject.lastVisitedOn}" /></span>
							 </c:if>
						</div> 
					</c:if>
				</div>				
			<!--head right con end--> 		
			</header>
		<!--header end-->		
		<!--body starts-->	
	<div id="chatInfo"> 
		<span id="chatMsg"></span><a href="#" class="close">Click Here To Close</a>   
	</div>		
	<section class="bodyarea">
		<div class="leftrightcon"> 
		<!--body content start-->				
			<article class="leftcon">
				<div class="accordionWrapper">
		        	<div id="st-accordion" class="st-accordion">
	                    <ul>						
						<c:forEach var="role" items="${roleList}">
						<c:if test="${role.moduleName eq 'ACADEMICS'}">
	                       <li>					                            
	                        <a class="accord" href="#" value="getModuleDetails.html?moduleName=ACADEMICS"><div class="overBorder">&nbsp;&nbsp;ACADEMICS<div class="in"></div></div><span class="st-arrow">Open or Close</span></a>
	                           <div class="st-content">
		                           <div class="divcon05">							
									<a href="getModuleDetails.html?moduleName=ACADEMICS"><br/>
									&thinsp;&thinsp;<img src="/icam/images/icon07.png" alt="" />								
									</a>																											
			           					<p>                        						
			    							<c:forEach begin="0" end="6"  var="functionality" items="${role.functionalityList}">
												<span class="bullet" >&bull;</span>&ensp;${functionality.functionalityName}<br />			
			    							</c:forEach>					                              						
			           					</p>  	
									</div>
								</div>											                            					                             
	                       </li> 
						</c:if>
						</c:forEach>						
	                    
                   		<c:forEach var="role" items="${roleList}">
						<c:if test="${role.moduleName eq 'ADMISSION'}">
	                        <li>					                            
	                            <a class="accord" href="#" value="getModuleDetails.html?moduleName=ADMISSION"><div class="overBorder">&nbsp;&nbsp;ADMISSION<div class="in"></div></div><span class="st-arrow">Open or Close</span></a>
	                            <div class="st-content">
	                           		<div class="divcon01">						
										<a href="getModuleDetails.html?moduleName=ADMISSION"><br/>
										&thinsp;&thinsp;<img src="/icam/images/icon01.png" alt="icon" title="" />
										</a>																											
                       					<p>                        						
                							<c:forEach begin="0" end="6"  var="functionality" items="${role.functionalityList}">
												<span class="bullet" >&bull;</span>&ensp;${functionality.functionalityName}<br />			
                							</c:forEach>					                              						
                       					</p>  	
									</div>
								</div>				                            					                             
	                        </li> 
                        </c:if>
						</c:forEach>						
												
						<c:forEach var="role" items="${roleList}">
						<c:if test="${role.moduleName eq 'ERP'}">
	                    	<li>					                            
	                           <a class="accord" href="#" value="getModuleDetails.html?moduleName=ERP"><div class="overBorder">&nbsp;&nbsp;ERP<div class="in"></div></div><span class="st-arrow">Open or Close</span></a>
	                           <div class="st-content">
	                           		<div class="divcon09">								
<!-- 								<img src="/icam/images/erp.png" alt="icon" title="" />  -->
									<a href="getModuleDetails.html?moduleName=ERP"><br/>
									&thinsp;&thinsp;<img src="/icam/images/erp1.png" alt="icon" title="" />
									</a>																											
			           					<p>                        						
			    							<c:forEach begin="0" end="6"  var="functionality" items="${role.functionalityList}">
												<span class="bullet" >&bull;</span>&ensp;${functionality.functionalityName}<br />			
			    							</c:forEach>					                              						
			           					</p>  	
									</div>
								</div>											                            					                             
	                       </li> 
						</c:if>
						</c:forEach>
						
						<c:forEach var="role" items="${roleList}">
						<c:if test="${role.moduleName eq 'FINANCE'}">
	                       <li>					                            
	                           <a class="accord" href="#" value="getModuleDetails.html?moduleName=FINANCE"><div class="overBorder">&nbsp;&nbsp;FINANCE<div class="in"></div></div><span class="st-arrow">Open or Close</span></a>
	                           <div class="st-content">
		                           <div class="divcon06">													
									<a href="getModuleDetails.html?moduleName=FINANCE"><br/>
									&thinsp;&thinsp;<img src="/icam/images/icon06.png" alt="" />
									</a>																											
			           					<p>                        						
			    							<c:forEach begin="0" end="6"  var="functionality" items="${role.functionalityList}">
												<span class="bullet" >&bull;</span>&ensp;${functionality.functionalityName}<br />			
			    							</c:forEach>					                              						
			           					</p>  	
									</div>
								</div>											                            					                             
	                       </li> 
						</c:if>
						</c:forEach>

						<c:forEach var="role" items="${roleList}">
						<c:if test="${role.moduleName eq 'OFFICE ADMINISTRATION'}">
	                        <li>					                            
	                            <a class="accord" href="#" value="getModuleDetails.html?moduleName=OFFICE ADMINISTRATION"><div class="overBorder">&nbsp;&nbsp;GENERAL SECTION<div class="in"></div></div><span class="st-arrow">Open or Close</span></a>
	                            <div class="st-content">
	                           		<div class="divcon04">
									<a href="getModuleDetails.html?moduleName=OFFICE ADMINISTRATION"><br/>
									&thinsp;&thinsp;<img src="/icam/images/icon04.png" alt="icon" title="" />
									</a>																											
	                      					<p>                        						
	               							<c:forEach begin="0" end="6"  var="functionality" items="${role.functionalityList}">
												<span class="bullet" >&bull;</span>&ensp;${functionality.functionalityName}<br />			
	               							</c:forEach>					                              						
	                      					</p>  	
									</div>
								</div>				                            					                             
	                        </li> 
						</c:if>
						</c:forEach>
						
						<c:forEach var="role" items="${roleList}">
						<c:if test="${role.moduleName eq 'HOSTEL'}">
	                        <li>
	                            <a class="accord" href="#" value="getModuleDetails.html?moduleName=HOSTEL"><div class="overBorder">&nbsp;&nbsp;HOSTEL<div class="in"></div></div><span class="st-arrow">Open or Close</span></a>
	                            <div class="st-content">
	                           		<div class="divcon03">						
										<a href="getModuleDetails.html?moduleName=HOSTEL"><br/>
										&thinsp;&thinsp;<img src="/icam/images/icon03.png" alt="icon" title="" />
										</a>																											
	                      					<p>                        						
	               							<c:forEach begin="0" end="6"  var="functionality" items="${role.functionalityList}">
												<span class="bullet" >&bull;</span>&ensp;${functionality.functionalityName}<br />			
	               							</c:forEach>					                              						
	                      					</p>  	
									</div>
								</div>				                            					                             
	                        </li> 
						</c:if>
						</c:forEach>


						<c:forEach var="role" items="${roleList}">
						<c:if test="${role.moduleName eq 'INVENTORY'}">
	                        <li>
	                            <a class="accord" href="#" value="getModuleDetails.html?moduleName=INVENTORY"><div class="overBorder">&nbsp;&nbsp;INVENTORY<div class="in"></div></div><span class="st-arrow">Open or Close</span></a>
	                            <div class="st-content">
	                           		<div class="divcon08">						
										<a href="getModuleDetails.html?moduleName=INVENTORY"><br/>
										&thinsp;&thinsp;<img src="/icam/images/inventory-icon.png" alt="icon" title="" style="width:101px;height:65px;" />
										</a>																											
	                      					<p>                        						
	               							<c:forEach begin="0" end="6"  var="functionality" items="${role.functionalityList}">
												<span class="bullet" >&bull;</span>&ensp;${functionality.functionalityName}<br />			
	               							</c:forEach>					                              						
	                      					</p>  	
									</div>
								</div>				                            					                             
	                        </li> 
						</c:if>
						</c:forEach>
						
						<c:forEach var="role" items="${roleList}">
						<c:if test="${role.moduleName eq 'LIBRARY'}">
	                        <li>					                            
	                            <a class="accord" href="#" value="getModuleDetails.html?moduleName=LIBRARY"><div class="overBorder">&nbsp;&nbsp;LIBRARY<div class="in"></div></div><span class="st-arrow">Open or Close</span></a>
	                            <div class="st-content">
	                           		<div class="divcon02">						
										<a href="getModuleDetails.html?moduleName=LIBRARY"><br/>
										&thinsp;&thinsp;<img src="/icam/images/icon02.png" alt="icon" title="" />
										</a>																											
                       					<p>                        						
                							<c:forEach begin="0" end="6"  var="functionality" items="${role.functionalityList}">
												<span class="bullet" >&bull;</span>&ensp;${functionality.functionalityName}<br />			
                							</c:forEach>					                              						
                       					</p>  	
									</div>
								</div>				                            					                             
	                        </li> 
						</c:if>
						</c:forEach>
							
						
						<c:forEach var="role" items="${roleList}">
						<c:if test="${role.moduleName eq 'SYSTEM ADMINISTRATION'}">
	                        <li>					                            
	                            <a class="accord" href="#" value="getModuleDetails.html?moduleName=SYSTEM ADMINISTRATION"><div class="overBorder">&nbsp;&nbsp;SYSTEM ADMINISTRATION<div class="in"></div></div><span class="st-arrow">Open or Close</span></a>
	                            <div class="st-content">
	                           		<div class="divcon08">															
									<a href="getModuleDetails.html?moduleName=SYSTEM ADMINISTRATION"><br/>
									&thinsp;&thinsp;<img src="/icam/images/icon08.png" alt="" />
									</a>																											
	                      					<p>
												<span class="bullet" >&bull;</span>&ensp;Create Role<br />
												<span class="bullet" >&bull;</span>&ensp;Create Access Type<br />
												<span class="bullet" >&bull;</span>&ensp;Configure Ldap And DB<br />
												<span class="bullet" >&bull;</span>&ensp;Configure Email<br />
												<span class="bullet" >&bull;</span>&ensp;Configure Events And Notes<br />
												<span class="bullet" >&bull;</span>&ensp;Update DB From Excel<br />
	                      					</p>  	
									</div>
								</div>				                            					                             
	                        </li> 
						</c:if>
						</c:forEach>
				
						<c:forEach var="role" items="${roleList}">
							<c:if test="${role.moduleName eq 'TICKETING'}">
		                       <li>					                            
		                           <a class="accord" href="#"  value="getModuleDetails.html?moduleName=TICKETING"><div class="overBorder">&nbsp;&nbsp;TICKETING<div class="in"></div></div><span class="st-arrow">Open or Close</span></a>
		                           <div class="st-content">
			                           <div class="divcon07">		                           
										<a href="getModuleDetails.html?moduleName=TICKETING"><br/>
										&thinsp;&thinsp;<img src="/icam/images/ticketing.png" alt="" />											
										</a>																											
				           					<p>                        						
				    							<c:forEach begin="0" end="6"  var="functionality" items="${role.functionalityList}">
													<span class="bullet" >&bull;</span>&ensp;${functionality.functionalityName}<br />			
				    							</c:forEach>					                              						
				           					</p>			           					 	
										</div>
									</div>											                            					                             
		                       </li> 
						</c:if>
						</c:forEach>						
                       <li>					                            
                           <a class="accord" href="#"  value="getModuleDetails.html?moduleName=REPORT"><div class="overBorder">&nbsp;&nbsp;REPORT<div class="in"></div></div><span class="st-arrow">Open or Close</span></a>
                           <div class="st-content">
	                           <div class="divcon11">		                           
								<a href="getModuleDetails.html?moduleName=REPORT"><br/>
								&thinsp;&thinsp;<img src="/icam/images/reporting.png" alt="" />											
								</a>																											
		           					<p>                        						
		    							<c:forEach begin="0" end="6"  var="functionality" items="${role.functionalityList}">
											<span class="bullet" >&bull;</span>&ensp;${functionality.functionalityName}<br />			
		    							</c:forEach>					                              						
		           					</p>			           					 	
								</div>
							</div>											                            					                             
                       </li> 
                       				
						<li>					                            
	                        <a class="accord" href="#" value="userServices.html"><div class="overBorder">&nbsp;&nbsp;MY SERVICES<div class="in"></div></div><span class="st-arrow">Open or Close</span></a>
	                           <div class="st-content">
		                           <div class="divcon15">							
									<a href="userServices.html"><br/>
									&thinsp;&thinsp;<img src="/icam/images/myServices.png" alt="My Services" />								
									</a>																											
			           					<p> 
												<span class="bullet" >&bull;</span>&ensp;View Personal Details<br />
												<span class="bullet" >&bull;</span>&ensp;View Attendance<br />
												<span class="bullet" >&bull;</span>&ensp;Generate Ticket<br />
												<span class="bullet" >&bull;</span>&ensp;View Ticket Status<br />
												<span class="bullet" >&bull;</span>&ensp;View My Calendar<br />
			           					</p>  	
									</div>
								</div>											                            					                             
	                       </li>
	                       
	                    </ul>
		        	</div>
          		</div>          		          		
			</article>
			
				<aside class="rightcon">
					<div id='calendar'>
						<c:if test="${year eq null}">		
						<div class="errorbox" id="errorbox" style="visibility: visible;">
							<span id="errormsg">No Academic Year Found To Map</span>	
						</div>	
					</c:if>
					</div>				 									
				</aside>
					
				<!--right con end-->			
			</div>
			<!--body content end-->			
			</section>
			<!--body end-->
			
	<!--Individual Chat Box Body -->		
		<div id="chat_div1"></div>
		
		<div id="pop">
            <div id="close">X</div>
            <div id="contentPop">
            	<div class="container chat-signin">	
					<br/><br/><label for="chatroom">Select User</label> &emsp;&thinsp;&nbsp;
					<select  id="chatroom">
						<option value="null">Please Select</option>
						<c:forEach var="resource" items="${resourceList}">
							<option value="${resource.name}">${resource.name}</option>
						</c:forEach>
					</select><br/><p/><br/>			
					<button class="btn btn-large btn-primary" type="button" id="enterRoom">Start Chat</button>
				</div>
            </div>
      </div>
      <div id="mask">
            <div id="page-wrap">
            </div>
      </div>
	<!--Individual Chat Box Body -->


	<!--Group Chat Box Body -->
		
	<div id="chat_div">
		<div class="activeUsers" id="activeUsers">
	       &emsp;&nbsp;&thinsp;Active Users<br/><br/>
	        <div class="contentDiv" id="nicknamesBox">
	        </div>
      	</div>
		<div id="chatBox">
        </div>                     
    </div>
	<!-- Group Chat Box Body end-->	
							
	<!--footer start-->
	<footer>	
		&copy; <a href="http://www.google.com" target="_blank" style="font-weight: bolder; color: orange;">
		Quantalogi Technosoft India PVT LTD</a>, 2015. All Rights Reserved.
		 No part of this software or any of its contents may be reproduced, 
		 copied, modified or adapted, without the prior written consent from the office, 
		 unless otherwise indicated for stand-alone materials.
	</footer>
	<!--footer end-->
	</div>
	<!--content area end-->
			<p id="back-top">
			<a href="#top">
				<span>
					<img src="/icam/images/up-arrow.png">
				</span>
			</a>
			</p>	
	</section>	
	<script type="text/javascript" src="/icam/js/common/jquery.accordion.js"></script>	
	<script type="text/javascript" src="/icam/js/common/bootstrap.min.js"></script>
	<script type="text/javascript" src='/icam/js/common/jquery.simplemodal.js'></script>
	<script type="text/javascript" src='/icam/js/common/osx.js'></script>	
	<script type="text/javascript" src="/icam/js/common/jquery.easing.1.3.js"></script>		
<c:choose>
<c:when test="${eventFromDb eq null}">			

</c:when>
<c:otherwise>			
	<c:set var="eventFromDb"  scope="request"> </c:set>
</c:otherwise>	
</c:choose>			
	<input type="hidden" id="hiddenYearstart" name="hiddenYearstart" value="">
	<input type="hidden" id="hiddenYearend" name="hiddenYearend" value="">
	<input type="hidden" id="nickname" value='<c:out value="${sessionScope.sessionObject.userName}"/>' />
	<input type="hidden" name="roleName" id="roleName"/>
</form>	
</body>
</html>