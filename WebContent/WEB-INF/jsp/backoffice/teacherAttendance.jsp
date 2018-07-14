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
		if($(this).val() == "STUDENT") {
			$("#teacherDetails").css('display','block');
		}
		
	});
	$("#year").change(function (){
		//alert("1");
		document.getElementById("month").removeAttribute("disabled");
		//alert("2");
		
	});
	$("#month").change(function (){

		var currentMonth =	new Date().getMonth() ;
		var currentYear=new Date().getFullYear();
		var userYear=document.getElementById("year").value;
		var userMonth=document.getElementById("month").value;
		if(userYear<currentYear )
		{
		
		document.getElementById("resourceType").removeAttribute("disabled");
		document.getElementById("javascriptmsg").style.display = 'none';
		}
	else if(userYear=currentYear)
		{
		if(userMonth<=currentMonth)
			{
			
			document.getElementById("resourceType").removeAttribute("disabled");
			document.getElementById("javascriptmsg").style.display = 'none';
			}
		else
			{
			
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "!!!!! Input is greater than current Date !!!!!";
			}
		}
	 else
		 {
		
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "!!!!! Input is greater than current Date !!!!!";
		 }
	});
	$("#resourceType").change(function (){
		document.getElementById("jobType").removeAttribute("disabled");
	});
	$("#year").change(function (){
		
		 $("#month").val('0');
		 $("#resourceType").val('0');
		 $("#jobType").val('0');
		 document.getElementById("resourceType").setAttribute("disabled","disabled");
			document.getElementById("jobType").setAttribute("disabled","disabled");
			
	});
});
function validate(){
	var academicYear=document.getElementById("year").value;	
	 var monthVal=document.getElementById("month").value;
	 var resourceType=document.getElementById("resourceType").value;
	 var jobType=document.getElementById("jobType").value;
	 var fileData0 = document.getElementById("attachment").value;

	if(academicYear == ""||academicYear =='null')
		{
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Please Enter Academic Year";
		return false;
		}
	
	else if(monthVal == ""||monthVal =='null'|| monthVal==0){
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Please Enter Month";
		return false;
	}
	
	else if(resourceType == ""||resourceType =='null'|| resourceType==0)
		{
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Please Enter Type";
		return false;
		}

	else if(jobType == ""||jobType =='null'|| jobType==0){
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Please Enter Job Type";
		
		return false;
	}
	
	 
		
	else if(fileData0 == ""||fileData0 =='null'){
		document.getElementById("javascriptmsg").style.display = 'none';
		document.getElementById("javascriptmsg1").style.display = 'block';			
		document.getElementById("javascriptmsg1").innerHTML = "Please upload atleast One File";
		return false;
	} 
	else
		{
	
	return true;
		}
}

 
   
		
	 
</script>
</head>
<body>
		<div class="alert alert-danger" id="javascriptmsg" style="display: none">
							 <span> </span>	
		</div> 
			<div class= "row" >	
			<form method="POST" action="teacherAttendanceUpload.html" enctype="multipart/form-data">
		
				<c:if test="${returnMessage eq 'error'}">
					<div class="alert alert-success">
					<strong>${uploadErrorMessage}</strong>
			</div>
				</c:if>
				<c:if test="${returnMessage eq 'success'}">
				<div class="alert alert-success">
					<strong>${uploadErrorMessage}</strong>
				</div>
				</c:if>
				<c:choose>
				<c:when test="${listTerm eq null && listTerm.size() eq 0}">			
					<div class="alert alert-danger">
						<strong>No academic term found.</strong>	
					</div>		
				</c:when>
				<c:otherwise>			
					<c:set var="listTerm"  scope="page"> </c:set>
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${currentYear eq null}">			
					<div class="alert alert-danger">
						<strong>No academic year found.</strong>	
					</div>
				</c:when>
				<c:otherwise>			
					<c:set var="currentYear"  scope="page"> </c:set>
				</c:otherwise>
				</c:choose>
				<c:choose>
				<c:when test="${specialHolidays eq null && specialHolidays.size() eq 0}">			
					<div class="alert alert-danger">
						<strong>No special holidays found.</strong>
					</div>	
				</c:when>
				<c:otherwise>			
				<c:set var="specialHolidays"  scope="page"> </c:set>
				</c:otherwise>
				</c:choose>
			<div class="col-md-8 col-md-offset-2">
	           <section class="panel">
	               <header class="panel-heading">
	                   <div class="panel-actions">
	                       <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
	                   </div>
	                   <h2 class="panel-title">Teacher Attendance</h2>										
	               </header>
	                  <div style="display: block;" class="panel-body">
	                      <div class="col-md-6">
	                          <div class="form-group">
	                          <label class="control-label">Year</label> <span class="required" aria-required="true">*</span> 
	                              <select class="form-control" id="year" name="year" required>
	                                  <option value="">Select...</option>
	                                  <c:if test="${year ne null}">
									<c:forEach var="year" items="${year}">
										<option>${year.academicYearName}</option>
									</c:forEach>
								</c:if>
	                              </select>
	                          </div>
	                      </div>
	                      <div class="col-md-6">
	                          <div class="form-group">
	                          <label class="control-label">Month</label><span class="required" aria-required="true">*</span>
	                              <select class="form-control" id="month" name="month" disabled required>
	                                 	<option value="0">Select...</option>
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
	                      <div class="col-md-6">
	                          <div class="form-group">
	                          <label class="control-label">Resource Type</label><span class="required" aria-required="true">*</span>
	                              <select class="form-control" id="resourceType" name="resourceType" disabled required>
	                                  <option value="0">Select...</option>
	                                  <c:forEach var="resourceType" items="${resourceTypeList}">
									<c:if test="${resourceType.resourceTypeName eq 'TEACHING STAFF'}">
										<option VALUE="${resourceType.resourceTypeName}">${resourceType.resourceTypeName}</option>
									</c:if>
								</c:forEach>
	                              </select>
	                          </div>
	                      </div>
	                       <div class="col-md-6">
	                          <div class="form-group">
	                          <label class="control-label">JOB Type</label><span class="required" aria-required="true">*</span>
	                              <select class="form-control" id="jobType" name="jobType" required disabled>
	                                  <option value="0">Select...</option>
	                                  <c:forEach var="jobType" items="${jobType}">
									
										<option VALUE="${jobType.jobTypeName}">${jobType.jobTypeName}</option>
									
								</c:forEach>
	                              </select>
	                          </div>
	                      </div>
                  		 
	                  </div>                  
	            
	         </section>
		</div>	
		     <div class="col-md-12">
                            <section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									</div>
							
									<h2 class="panel-title">Upload Attendance Sheet</h2>
								</header>
								<div class="alert alert-danger" id="javascriptmsg1" style="display: none">
							 <span> </span>	
								</div> 
								<div class="panel-body" id="tableDiv">
									<table class="table table-bordered table-striped mb-none">
										<thead>
											<tr><th>File</th></tr>
										</thead>
										<tbody>
											<tr>
												<td><input type="file" name="imageFile" id="attachment" /></td>
					 							<!-- <td><input id="addFile2" class="addFileClassName" type="button" value="ADD"/></td> -->
				 							</tr>
										</tbody>
									</table>
								</div>
	                            <footer style="display: block;" class="panel-footer">
	                                <button class="btn btn-primary" onclick="return validate()">Submit </button>
	                                <button type="reset" class="btn btn-default">Reset</button>
	                            </footer>
                            </section>
                        </div>
                 </form>	
		<div class="col-md-12" id="calenderDiv" style="display: none">
	           <section class="panel">
	           		<header class="panel-heading">
	                    <div class="panel-actions">
	                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
	                    </div>

                    	<h2 class="panel-title">Attendance</h2>										
                	</header>
                	<div style="display: block;" class="panel-body">                                   
	                    <div class="row">
	                        <div class="col-md-7 col-md-offset-1">
	                            <div class="calendar-container">
	                                <div id="calendar"></div>
	
	                                <div class="ring-left"></div>
	                                <div class="ring-right"></div>
	                                <div class="left"><span class="present-day role"></span> Present Day</div>
	                                <!-- <div class="left"><span class="off-day role"></span> OFF Day</div> -->
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
				htmlContent +="<td class='present-day'>"+counter+"</td>";
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