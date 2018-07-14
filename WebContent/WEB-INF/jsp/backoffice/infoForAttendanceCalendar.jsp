<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Read QR Code</title>
<%@ include file="/include/include.jsp" %>
<link rel="stylesheet" href="/cedugenie/assets/stylesheets/calender.css" />
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$("#resourceType").change(function(){
// 		var table1 = document.getElementById("attendanceShow");
// 		var rowCount = table1.rows.length;
// 		rowCount=rowCount-1;
// 		$("#title").hide();
// 		document.getElementById("attendanceShow").style.display = "none";
		if($(this).val() == "STUDENT") {
			$("#classDetails").css('display','block');
//			$("#workShift").css('display','none');
//     		for(var i=rowCount;i>0;i--){
// 				table1.deleteRow(i);
// 			} 
		}
	});
	
	$.ajax({
        url: '/cedugenie/getClassForAttendance.html',
        dataType: 'json',
        success: function(data) {
        	if(data != "null"){
        		var arrClass = data.split(",");
        		var classid=document.getElementById("class");
        		for(var classlist=0;classlist<=arrClass.length;classlist++)
    			{
        			classid.add(new Option(arrClass[classlist], arrClass[classlist],null));
        		}
        		classid.remove(classlist);
        	}
        } 
	}); 
	
	$("#class").change(
			function() {
				$.ajax({
			        url: '/cedugenie/getCourseForAttendance.html',
			        data: "class=" + ($(this).val()),
			        dataType: 'json',
			        success: function(data) {
			        	if(data == null){
// 			        		$("#warningbox").css('visibility', 'visible');
// 					    	$('#warningmsg').text("There is no Course for the selected class.");
							alert("There is no Course for the selected class.");
			        		var streamid=document.getElementById("course");
				        		for(var i= streamid.length; i>0; i--){
				        			streamid.remove(i);
				        		}
			        	}
			        	
			        	if(data != null){
// 			        		$("#warningbox").css('visibility', 'collapse');
// 					    	$('#warningmsg').text("");
			  				var arrCourse = data.split("@");
			        		var courseid=document.getElementById("course");
			        		for(var i=courseid.length;i>0;i--) {
			        			courseid.remove(i);
			        		}
			        		for(var courselist=0;courselist<=arrCourse.length;courselist++) {
			        			var arrSplitedData = arrCourse[courselist].split(",");
			        			courseid.add(new Option(arrSplitedData[1], arrSplitedData[0],null));
			    			}
			        		courseid.remove(courselist);
			        	}
			        } 
			}); 
	});
	
	$("#class").change(function() {
				$.ajax({
			        url: '/cedugenie/getSectionForAttendance.html',
			        data:"class=" +  ($(this).val()),
			        dataType: 'json',
			        success: function(data) {
			        	if(data == null){
// 			        		$("#warningbox").css('visibility', 'visible');
// 					    	$('#warningmsg').text("There is no section for the selected class and stream.");
							alert("There is no section for the selected class and stream.");
			        		var sectionid=document.getElementById("sectionone");
				        	for(var i=sectionid.length;i>0;i--) {
				        		sectionid.remove(i);
				        	}
			        	}
			        	if(data != null){
// 			        		$("#warningbox").css('visibility', 'collapse');
// 					    	$('#warningmsg').text("");
				        	var sectionid=document.getElementById("sectionone");
				        	for(var i=sectionid.length;i>0;i--) {
				        		sectionid.remove(i);
				        	}
				        	var arrsection=data.split(",");
				        	for(var sectionlist=0;sectionlist<arrsection.length;sectionlist++) {
				        		sectionid.add(new Option(arrsection[sectionlist], arrsection[sectionlist],null));
				        	}
			        	}
			        }
				}); 
	});
	
	$('#sectionone').change(function(){
		var yearvalue = $("#year option:selected").text().trim();
		var monthvalue = $('#month :selected').val();
		$.ajax({
	        url: '/cedugenie/getSelectedStudentsForView.html',
	    	data:	"class=" + ($("#class").val()) + "&section=" + ($(this).val())  + 
	     			"&year=" + yearvalue + "&month=" + monthvalue,
	        dataType: 'json',
	        success: function(data) {
	        	if(data == null){
// 	        		$("#warningbox").css('visibility', 'visible');
// 			    	$('#warningmsg').text("There is no student for the selected class and section.");
					alert("There is no student for the selected class and section.");
	        		var resourceFieldId = document.getElementById("resourceName");
		        	for(var i = resourceFieldId.length; i>0; i--) {
		        		resourceFieldId.remove(i);
		        	}
	        	}
	        	if(data != null){
	        		$("#warningbox").css('visibility', 'collapse');
			    	$('#warningmsg').text("");
		        	var resourceFieldId = document.getElementById("resourceName");
		        	for(var i = resourceFieldId.length; i>0; i--) {
		        		resourceFieldId.remove(i);
		        	}
		        	var arrayresource = data.split("@");
		        	for(var resourcename = 0; resourcename < arrayresource.length; resourcename++) {
		        		var nameAndRoll = arrayresource[resourcename].split("*");	
		        		resourceFieldId.add(new Option(nameAndRoll[0], nameAndRoll[1]));
		        	}
	        	}
	        }
		});
	});	

		$('#resourceName').change(function(){
			var yearvalue = $("#year option:selected").text().trim();
			var monthvalue = $('#month :selected').val();			
			$.ajax({
		        url: '/cedugenie/getStudentAttendanceForCalendar.html',
		    	data:	"&resourceName=" + ($(this).val())  + "&year=" + yearvalue + "&month=" + monthvalue,
		        dataType: 'json',
		        success: function(data) {
		        	if(data == null){
						alert("There is no student for the selected class and section.");		        		
		        	}
		        	if(data != null){		        		
		        		document.getElementById('calenderDiv').style.display = 'block';
		        		displayCalendar(monthvalue, yearvalue, data);
		        	}
		        }
			});
		});		
	
});	
</script>
</head>
<body>
	<div class= "row" >		
		<div class="col-md-6 col-md-offset-3">
	           <section class="panel">
	               <header class="panel-heading">
	                   <div class="panel-actions">
	                       <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
	                   </div>
	                   <h2 class="panel-title">Student Attendance</h2>										
	               </header>
	                  <div style="display: block;" class="panel-body">
	                      <div class="col-md-4">
	                          <div class="form-group">
	                          <label class="control-label">Year</label>  
	                              <select class="form-control" id="year" name="year">
	                                  <option value="">Select...</option>
	                                  <c:if test="${year ne null}">
									<c:forEach var="year" items="${year}">
										<option>${year.academicYearName}</option>
									</c:forEach>
								</c:if>
	                              </select>
	                          </div>
	                      </div>
	                      <div class="col-md-4">
	                          <div class="form-group">
	                          <label class="control-label">Month</label>
	                              <select class="form-control" id="month" name="month">
	                                  <option value="">Select...</option>
	                                  <option value="1">January</option>
								<option value="2">February</option>
								<option value="3">March</option>
								<option value="4">April</option>
								<option value="5">May</option>
								<option value="6">June</option>
								<option value="7">July</option>
								<option value="8">August</option>
								<option value="9">September</option>
								<option value="10">October</option >
								<option value="11">November</option>
								<option value="12">December</option>
	                              </select>
	                          </div>
	                      </div>
	                      <div class="col-md-4">
	                          <div class="form-group">
	                          <label class="control-label">Resource Type</label>
	                              <select class="form-control" id="resourceType" name="resourceType">
	                                  <option value="">Select...</option>
	                                  <c:forEach var="resourceType" items="${resourceTypeList}">
									<c:if test="${resourceType.resourceTypeName eq 'STUDENT'}">
										<option VALUE="${resourceType.resourceTypeName}">${resourceType.resourceTypeName}</option>
									</c:if>
								</c:forEach>
	                              </select>
	                          </div>
	                      </div>
	                  </div>                  
	                  <div id="classDetails" style="display: none;" class="panel-body">
	                      <div class="col-md-3">
	                          <div class="form-group">
	                          <label class="control-label">Class</label>
	                              <select class="form-control" id="class" name="class">
	                                  <option value="">Select...</option>
	                              </select>
	                          </div>
	                      </div>
	                      <div class="col-md-6">
	                          <div class="form-group">
	                          <label class="control-label">Select Course</label>
	                              <select class="form-control" id="course" name="class">
	                                  <option value="">Select...</option>
	                              </select>
	                          </div>
	                      </div>
	                      
	                      <div class="col-md-3">
	                          <div class="form-group">
	                          <label class="control-label">Section</label>
	                              <select class="form-control" id="sectionone" name="sectionone">
	                                  <option value="">Select...</option>
	                              </select>
	                          </div>
	                      </div>
	                  </div> 
	                  <div style="display: block;" class="panel-body">
	                  	<div class="col-md-8">
	                             <div class="form-group">
	                             <label class="control-label">Resource Name</label>
	                                 <select class="form-control" id = "resourceName" name = "resourceName">
	                                     <option value="">Select...</option>
	                                 </select>
	                             </div>
	                         </div>
	                  </div>                     
				</section>
			</div>		
			<div class="col-md-12" id="calenderDiv" style="display: none">
	           <section class="panel">
	           		<header class="panel-heading">
	                    <div class="panel-actions">
	                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
	                    </div>
                    	<h2 class="panel-title">Student Attendance</h2>										
                	</header>
                	<div style="display: block;" class="panel-body">                                   
	                    <div class="row">
	                        <!-- <div class="col-md-3">
	                            <div class="form-group">
	                            <label class="control-label">Resource Name</label>
	                                <select class="form-control">
	                                    <option value="">Select...</option>
	                                </select>
	                            </div>
	                        </div> -->
	                        <div class="col-md-7 col-md-offset-1">
	                            <div class="calendar-container">
	                                <div id="calendar"></div>
	
	                                <div class="ring-left"></div>
	                                <div class="ring-right"></div>
	                                <!-- <div class="left"><span class="present-day role"></span> Present Day</div> -->
	                                <div class="left"><span class="off-day role"></span> OFF Day</div>
	                                <!-- <div class="left"><span class="half-day role"></span> Half Day</div> -->
	                            </div>
	                        </div>
	                    </div>
                </div>
            </section>
		</div>
	</div>
<script type="text/javascript">

	function displayCalendar(monthVal, yearVal, data){		 
		 var dateVal = data.split("~");		
		 
		 var htmlContent ="";
		 var FebNumberOfDays ="";
		 var counter = 1;
		 
		 var dateNow = new Date();
		 var month = dateNow.getMonth();
		 month = monthVal - 1 ;
		
		 var nextMonth = month+1; //+1; //Used to match up the current month with the correct start date.
		 var prevMonth = month -1;
		 var day = dateNow.getDate();
		 var year = dateNow.getFullYear();		 
		 year = yearVal;		 
		 
		 //Determing if February (28,or 29)  
		 if (month == 1){
		    if ( (year%100!=0) && (year%4==0) || (year%400==0)){
		      FebNumberOfDays = 29;
		    }else{
		      FebNumberOfDays = 28;
		    }
		 }
		 
		 // names of months and week days.
		 var monthNames = ["January","February","March","April","May","June","July","August","September","October","November", "December"];
		 var dayNames = ["Sunday","Monday","Tuesday","Wednesday","Thrusday","Friday", "Saturday"];
		 var dayPerMonth = ["31", ""+FebNumberOfDays+"","31","30","31","30","31","31","30","31","30","31"];
		 
		 
		 // days in previous month and next one , and day of week.
		 var nextDate = new Date(nextMonth +' 1 ,'+year);		 
		 var weekdays= nextDate.getDay();		 
		 var weekdays2 = weekdays;		 
		 var numOfDays = dayPerMonth[month];
		 
		 // this leave a white space for days of pervious month.
		 while (weekdays>0){
		    htmlContent += "<td class='monthPre'></td>";
		 
		 // used in next loop.
		     weekdays--;
		 }
		 
		 // loop to build the calander body.
		 
		 while (counter <= numOfDays){		 	
		    // When to start new line.
		    if (weekdays2 > 6){
		        weekdays2 = 0;
		        htmlContent += "</tr><tr>";
		    }		 
		 
		    // if counter is current day.
		    // highlight current day using the CSS defined in header.
		    
 		    //logic will be here			
			if(checkContains(counter, dateVal)){
				htmlContent +="<td class='off-day'>"+counter+"</td>";
			}else{
				htmlContent +="<td>"+counter+"</td>";
			}
		    
		    weekdays2++;
		    counter++;
		 }		 
		 
		 
		 // building the calendar html body.				
		 
		 var calendarBody = "<header><div class='month'>"+monthNames[month]+" "+ year +"</div></header>";
		 calendarBody += "<table class='calendar'>";    
		 calendarBody +="<thead><tr> <td>Sun</td> <td>Mon</td> <td>Tue</td>"+
		 "<td>Wed</td> <td>Thu</td> <td>Fri</td> <td>Sat</td> </tr></thead>";
		 calendarBody += "<tbody><tr>";
		 calendarBody += htmlContent;
		 calendarBody += "</tr></tbody></table>";
		 // set the content of div .
		 document.getElementById("calendar").innerHTML=calendarBody;		 
	}
	
	function checkContains(counter, dateVal){			
		for(var i=0; i < dateVal.length; i++ ) {			 
	        if( dateVal[i] == counter) {		          
	           return true;	
	        }		       
	     }
	}

</script>					
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>