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
<title>Teacher Attendance</title>
<%@ include file="/include/include.jsp" %>
<%@ include file="/include/js-include.jsp" %>
<link rel="stylesheet" href="/cedugenie/assets/stylesheets/calender.css" />
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
<script type="text/javascript">
$(document).ready(function() {
	 var startday = "";
	 var endday = "";
	 var startholiday= "";
	 var endYearDigit = "";
	 var startYearDigit = "";
	 var startMonthDigit = "";
	 var endMonthDigit = "";
	 var endDayDigit = "";
	 var termStartActual = "<c:out value="${currentYear.sessionStartDate}"/>" ;
	 var termEndActual = "<c:out value="${currentYear.sessionEndDate}"/>" ;
	 var yearSplitArray = termStartActual.split("/"); 
	 var yearenddateSplitArray = termEndActual.split("/"); 
	 endYearDigit = yearenddateSplitArray[2];
	 startMonthDigit = parseInt(yearSplitArray[1]);
	 startYearDigit = parseInt(yearSplitArray[2]);
	 endMonthDigit = parseInt(yearenddateSplitArray[1]);
	 endDayDigit = parseInt(yearenddateSplitArray[0]);
	 var from = new Date(startYearDigit, startMonthDigit-1, 1);  // -1 because months are from 0 to 11
	 var to   = new Date(endYearDigit, endMonthDigit-1, endDayDigit);
	
	 /* added by sourav.bhadra on 20/07/2017 */
	 var academicYear = "<c:out value="${currentYear.academicYearCode}"/>";
	 
	 $("#year").change(function() {
		 $("#month").val('0');
		 $("#resourceType").val('0');
		 $("#jobtype").val('0');
		 $("#title").hide();
		 document.getElementById("allSubmit").style.display = "none";
		 
		 /* added by sourav.bhadra on 20/07/2017 */
		 var selectedYear = $(this).val();
		var yearArr = academicYear.split("-");
		var option = "<option value='0'>Select..</option>";
		if(selectedYear==yearArr[0]){
			option += "<option value='4'>April</option>"+
	                  "<option value='5'>May</option>"+
	                  "<option value='6'>June</option>"+
	                  "<option value='7'>July</option>"+
	                  "<option value='8'>August</option>"+
	                  "<option value='9'>September</option>"+
	                  "<option value='10'>October</option>"+
	                  "<option value='11'>November</option>"+
	                  "<option value='12'>December</option>";
			document.getElementById("month").innerHTML=option;
		}else if(selectedYear==yearArr[1]){
			option += "<option value='1'>January</option>"+
		            "<option value='2'>February</option>"+
		            "<option value='3'>March</option>";
			document.getElementById("month").innerHTML=option;
		}
	 });
	
	$("#month").change(function() {
		
		$("#resourceType").val('0');
		$("#jobtype").val('0');
		$("#title").hide();
		document.getElementById("allSubmit").style.display = "none";
		
		var yearvalue = $("#year option:selected").text();
		var monthvalue = $('#month :selected').val();
		var yearvalue1 = $("#year option:selected").text().trim();
		var monthvalue1 = $('#month :selected').val();
		$("#resourceType").removeAttr('disabled');
		$("#jobtype").removeAttr('disabled');
		 if(monthvalue1 == 1 || monthvalue1 == 2 || monthvalue1 == 3 || monthvalue1 == 4 || monthvalue1 == 5 || monthvalue1 == 6 || monthvalue1 == 7 || monthvalue1 == 8 || monthvalue1 == 9 ){
			 monthvalue1 = "0"+monthvalue1;
	        }
		 	 monthvalue1 = parseInt(monthvalue1);
			var check = new Date(yearvalue1, monthvalue1-1, 1);
		    if(startMonthDigit <= monthvalue1){
			 startday = yearSplitArray[0];
			}
		    if(yearenddateSplitArray[2] == yearvalue1 && yearenddateSplitArray[1] == monthvalue1){
				endday = endDayDigit;
			 }
		//	if(check > from && check < to){
				$.ajax({
				    url: '/cedugenie/getDaysForAttendance.html',
				    	dataType: 'json',
				    	data: "yearvalue=" + yearvalue+ "&monthvalue=" +monthvalue,
				    	success: function(data){
				    		if(data != null){
				    			var table=document.getElementById("attendanceShow");
								var rowCount = table.rows.length;
								rowCount=rowCount-1;
								for(var i=rowCount;i>=0;i--){
									table.deleteRow(i);
								}
				    			
								var data1 = data.split("@");
								var specialDays = data1[1].split(";");
				    			var rownew = "";
				    			rownew = $('<tr>'); 
				    			var row = rownew.append($('<th  width="250" ></th>').html("Resource"));
				    			var arr = data1[0].split(",");
				   			 	var len = arr.length;
				   			 	var d = new Date();
					   			var month = d.getMonth() + 1;
					   			var day = d.getDate();
					   			var year = d.getYear();
					   			var today = (day<10?'0':'')+ day + '/' +(month<10?'0':'')+ month + '/' + year;
				   				for(var clist=0;clist<=arr.length-1;clist++){	
					   				var arr1 = arr[clist].split("/");
						    		for(var cfees=0;cfees<arr1.length-1;cfees++){	
						    			if(arr1[cfees] != "" || arr1[cfees] != ""){	    		
						    				if(arr1[1]==1){
						    					row =rownew.append($('<th class="cssTable" width="20" ></th>').html(arr1[0]));
						    				}else if(arr1[0] == day && monthvalue == month){
						    					row =rownew.append($('<th  class="cssTable1" width="20" ></th>').html(arr1[0]));
						    					console.log('print-td -1');
						    				}else{
				    							var holidayStatus = checkHoliday(arr1[0], specialDays);
				    							if(holidayStatus==0){
			    									row =rownew.append($('<th class="cssTable" width="20" ></th>').html(arr1[0]));
			    								}else{
			    									row =rownew.append($('<th width="20" ></th>').html(arr1[0]));
			    								}
				    						}
						    			}
						    		}
						    	}
				   				row =rownew.append($('<th  width="270" ></th>').html("Action"));
					    		row =rownew.append($('</tr>'));
						    	$('#attendanceShow').append(row);
						    }
					    }
				});
				document.getElementById("attendanceShow").style.display = "none";
			
	});
	
	
	
	$("#resourceType").change(function(){
		
		$("#jobtype").val('0');
		$("#title").hide();
		document.getElementById("allSubmit").style.display = "none";
		
		var table1=document.getElementById("attendanceShow");
		var rowCount = table1.rows.length;
		rowCount=rowCount-1;
		$("#title").hide();
		document.getElementById("attendanceShow").style.display = "none";
		if($(this).val() == "TEACHING STAFF")
		{
			for(var i=rowCount;i>0;i--){
				table1.deleteRow(i);
			} 
		}
	});
	var y=1;
	$("#jobtype").change(function() {
		var yearvalue = $("#year option:selected").text().trim();
		var monthvalue = $('#month :selected').val();
		var resourceTypeName = $("#resourceType option:selected").text();
		var monthForCal = "";
		 if(monthvalue == 1 || monthvalue == 2 || monthvalue == 3 || monthvalue == 4 || monthvalue == 5 || monthvalue == 6 || monthvalue == 7 || monthvalue == 8 || monthvalue == 9 ){
  	        	monthvalue = "0"+monthvalue;
  	        }
		 monthForCal = monthvalue;
		 monthvalue = parseInt(monthvalue);
			var check = new Date(yearvalue, monthvalue-1, 1);
		    if(startMonthDigit <= monthvalue){
			 startday = yearSplitArray[0];
		 	}
		    if(yearenddateSplitArray[2] == yearvalue && yearenddateSplitArray[1] == monthvalue){
				endday = endDayDigit;
			}
		var d = new Date();
  			var month = d.getMonth() + 1;
  			var year = d.getYear();
  		//	if(check > from && check < to){
	   			$.ajax({
			        url: '/cedugenie/getTeachersForAttendance.html',
			        data:"jobtype="+ ($(this).val()) +"&resourceTypeName=" + resourceTypeName + "&year=" + yearvalue + "&month=" + monthvalue,
			        dataType: 'json',
			        success: function(data) {
			        	if(data == null){
			        		alert("There is no teacher.");
					    	$("#title").hide();
	        				document.getElementById("attendanceShow").style.display = "none";
	        				document.getElementById("allSubmit").style.display = "none";
			        	}
			        	window.test = data;
				        	var monthToSet = parseInt(monthvalue);
				        	var noOfDaysInMonth = new Date(yearvalue, monthToSet, 0).getDate();
			        		var table1=document.getElementById("attendanceShow");
			        		var rowCount = table1.rows.length;
							rowCount=rowCount-1;
							
							for(var i=rowCount;i>0;i--){
								table1.deleteRow(i);
							}
							
			        		var arrTeacherDetails = data.split("@");
			        		var arrTeacherAttendance = arrTeacherDetails[0].split("&");
			        		for(var teacherlist=0; teacherlist<=arrTeacherDetails.length; teacherlist++){
			        			var arrTeacherNames = arrTeacherDetails[teacherlist+1].split(",");
			        			if(arrTeacherNames != "null"){
				        			$("#title").show();
				        			document.getElementById("attendanceShow").style.display = "block";
				        			var table=document.getElementById("attendanceShow");
				        			var rowc=table.rows.length;
					        		var row=table.insertRow(rowc);
					        		row.id = teacherlist;
					        		var cellteacher = row.insertCell(0);
					        		var element2 = document.createElement("input");
				        	        element2.setAttribute('readonly','readonly');
				        	        element2.type = "text"; 
				        	        element2.name="studRegId";
				        	        element2.id="studRegId"+y; 
				        	        cellteacher.appendChild(element2);
					        		var rowIndex = 0; // rowindex, in this case the first row of your table
					        		var row1 = table1.getElementsByTagName('tr')[rowIndex];
					        		var cells = row1.getElementsByTagName('th');
					        		var cellCount = cells.length;
					        		var cell = "";
					        		var element1 = "";
					        		var insertedTechId = arrTeacherDetails[0].split("&");
					        		var insertedTechIdLen = insertedTechId.length-1;
					        	    var namess = "";
					        	    var dates = "";
					        	    var label = "";
				        	   		for (var i=1;i<cellCount;i++){
					        			cell = row.insertCell(i);
					        			if(i!=cellCount){
					        	        element1 = document.createElement("input");
					        	        element1.type = "checkbox";
					        	        element1.name="serialNo";
					        	        element1.id="serialNo"+y;  
					        	        label = document.createElement("Label");
					        	        label.innerHTML="H";
					        	        label.className = "cssTable2";
					        	        
					        	        if(i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9 ){
					        	        	 element1.setAttribute("value","0"+i+"/"+monthForCal+"/"+yearvalue);
					        	        }
					        	        else{
					        	        	element1.setAttribute("value",i+"/"+monthForCal+"/"+yearvalue);
					        	        }
					        	       if(cells[i].className == "cssTable"){
											element1.style.display="none";
											cell.appendChild(label);
										} 
					        	       if(i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9 ){
					        	        	 i = "0"+i;
					        	      	}
					        	       if(startYearDigit == yearvalue && monthToSet == startMonthDigit){//sayani
					        	    		if(startday>i ){
						        	    		element1.style.display="none";
						        	       	}
					        	       }
					        	       if(startYearDigit == yearvalue && monthToSet < startMonthDigit){
					        	    		element1.style.display="none";
					        	       }
					        	       if(yearenddateSplitArray[2] == yearvalue && yearenddateSplitArray[1] == monthvalue){
					        	    
						        	       if(endday<i){
					        	    			element1.style.display="none";
					        	        	} 
						        		} 
					        	      
					        	      if(i>noOfDaysInMonth){
					        	 	
					        	    	  element1.style.display="none";
					        	      }
					        	      
					        	         cell.appendChild(element1);
					        	        y++; 
					        			}
					        	       
					        	       if(arrTeacherDetails[0] != "null" ){
					        	       
					        	    
					        			  for(var names=0;names<=insertedTechIdLen;names++){
					        				namess = insertedTechId[names].split("~");
					        				 dates = namess[1].split("/");
					        				if(namess[0] == arrTeacherNames[1]){
					        					 element1.disabled = true;
					        					if(dates[0] != i ){
					        						
							        					element1.checked = true;
							        					
					        						}
					        					 else{
					        							element1.checked = false;
					        							break;
					        							
								        			
					        					 }
					        					
							        			}				        				 
					        				 
							    			} 
					        	       }
					        	     
										<c:forEach var="specialdetails" items="${specialHolidays}">
											var holiStart = "<c:out value="${specialdetails.specialHoliday}"/>" ;
											
											var holistartArray = holiStart.split("/"); 
										   
										   	if(holistartArray[1] == monthvalue){
												 startholiday = holistartArray[0];
											}
										   
						        	       if(startholiday == i){
						        	    	   element1.style.display="none";
						        	    	  }
					        	      </c:forEach>		
					        	     }
				        		
				        	        var btn=document.createElement("BUTTON");
				        	        btn.type="submit";
				        	        btn.className = "submitbtn1 btn-primary btn-xs";
				        	        btn.name="submitAction";
				        	        btn.id="submitAction"+y; 
				        	        var t=document.createTextNode("Submit");
				        	        btn.appendChild(t);
				        	        cell.appendChild(btn); 
				        	        
				        	        var btn1=document.createElement("BUTTON");
				        	        btn1.type="button";
				        	        btn1.name="selectAction";
				        	        btn1.className = "clearbtn1 btn-success btn-xs";
				        	        btn1.id=teacherlist; 
				        	        btn1.setAttribute("onclick","selection(this.id,"+row.id+","+arrTeacherDetails.length+");");
				        	        var t1=document.createTextNode("Select All");
				        	        btn1.appendChild(t1);
				        	        cell.appendChild(btn1); 
				        	        
				        	        var btn2=document.createElement("BUTTON");
				        	        btn2.type="button";
				        	        btn2.className = "clearbtn1 btn-danger btn-xs";
				        	        btn2.name="deselectAction";
				        	        btn2.id="teacherlist"+teacherlist; 
				        	        btn2.setAttribute("onclick","cancelSelection(this.id,"+row.id+","+arrTeacherDetails.length+");");
				        	        var t2=document.createTextNode("Select None");
				        	        btn2.appendChild(t2);
				        	        cell.appendChild(btn2); 
				        	        
				        	        var btn3=document.createElement("BUTTON");
				        	        btn3.type="button";
				        	        btn3.name="editAction";
				        	        btn3.id="editAction"+y; 
				        	        btn3.className = "editbtn1 btn-warning btn-xs";
				        	        var t3=document.createTextNode("Edit");
				        	        btn3.appendChild(t3);
				        	        cell.appendChild(btn3); 
				        	       
				        	        for(var names=0;names<=insertedTechIdLen;names++){
				        				namess = insertedTechId[names].split("~");
				        				if(namess[0] == arrTeacherNames[1]){
				        					btn.disabled= true;
				        					btn2.disabled= true;
				        					btn1.disabled = true;
					        			}
					    			}
				        	        //i;
				        	    // open loop for each row and append cell
				        	  	 for(var namelist=0;namelist<=arrTeacherNames.length;namelist++){
					        			var newName = arrTeacherNames[0];
					        			var rowIndex1 = 1; // rowindex, in this case the first row of your table
						        		var row11 = table1.getElementsByTagName('tr')[rowIndex1];
						        		var cells11 = row11.getElementsByTagName('td');
						        		cellteacher.innerHTML=newName;
						        		
					        			var arrRegId = arrTeacherDetails[1];
					        		
					        			element2.value=arrTeacherNames[1];
					        			var userID=element2.value;
					        			var resourceTypeName="TEACHING STAFF";
					        			
					        			 btn.setAttribute("onclick","submitActions("+row.id+",'"+userID+"','"+resourceTypeName+"'); return submitActionAnother();");
					        			 btn3.setAttribute("onclick","editActions("+row.id+",'"+userID+"','"+resourceTypeName+"');");
				    				
							    	}	    	
			    			}
			        			else{
			        				$("#title").hide();
			        				document.getElementById("attendanceShow").style.display = "none";
			        				document.getElementById("allSubmit").style.display = "none";
			        				alert("There is no teacher.");
			        			}
			    			}
			          }
			}); 
	   			/* }//mponth wise
	   			else{
	   				$("#title").hide();
   					document.getElementById("attendanceShow").style.display = "none";
   					document.getElementById("allSubmit").style.display = "none";
	   				$("#warningbox").css('visibility', 'visible');
			    	$('#warningmsg').text("Out Of Range.");
	   			} */
	   			document.getElementById("allSubmit").style.display = "block";
	});

});

function selection(clicked_id,row_id,row_count){
	var row=document.getElementById('attendanceShow').rows[row_id+1];
	var all=row.getElementsByTagName("input");
	for(var i=0;i<all.length;i++)
	{
		if(all[i].type=="checkbox")
		{
			all[i].checked=true;
		}
	}
}

function cancelSelection(clicked_id,row_id,row_count){
	var row=document.getElementById('attendanceShow').rows[row_id+1];
	var all=row.getElementsByTagName("input");
	for(var i=0;i<all.length;i++)
	{
		if(all[i].type=="checkbox")
		{
			if(all[i].checked == true){
			all[i].checked=false;
			}
		}
	} 
}

function submitActions(row_id,user_id,resourceTypeName){
	var row=document.getElementById('attendanceShow').rows[row_id+1];
	var all=row.getElementsByTagName("input");
	$("#hiddenId").val(user_id);
	var days = "";
	var details = "";
	var yearvalue = $("#year option:selected").text().trim();
	var monthvalue = $('#month :selected').val();
	for(var i=0;i<all.length;i++){
		if(all[i].type=="checkbox"){
			if(all[i].checked == false && all[i].style.display != "none"){
			days = days  + all[i].value+",";
			$("#hiddenAbsentDay").val(days);
			}
		}
	} 
	details = details +monthvalue+","+yearvalue;
	if(resourceTypeName == "TEACHING STAFF"){
	$("#hiddenDetails").val(monthvalue+","+yearvalue);
	$("#hiddenType").val(resourceTypeName);
	}
	
}

function editActions(row_id,user_id,resourceType){
	var yearvalue = $("#year option:selected").text().trim();
	var monthvalue = $('#month :selected').val();
	var row=document.getElementById('attendanceShow').rows[row_id+1];
	var all=row.getElementsByTagName("input");
	var buttons = row.getElementsByTagName("button");
	if(resourceType == "TEACHING STAFF"){
	$.ajax({
       url: '/cedugenie/getDateOfCreationForInsertedTeacher.html',
       data:"month=" + monthvalue + "&year=" + yearvalue+ "&teacherId=" + user_id,
       dataType: 'json',
       success: function(data) {
       	if(data != ""){
       		var splitedDateOfCreation = data.split("/");
       		  var specificMonth = splitedDateOfCreation[1] - 1;
       		  var dateOfCreationFormat = new Date(splitedDateOfCreation[2], specificMonth, splitedDateOfCreation[0]); //Year, Month, Date
       		  dateOfCreationFormat.setDate(dateOfCreationFormat.getDate() + 2);
       		  var date = new Date();
       		  var d = date.getDate();
       		  var m = date.getMonth();
       		  var y = date.getFullYear();
      		  for(var i=0;i<buttons.length;i++){
      					if(buttons[i].disabled = true){
      						buttons[i].disabled = false;
      					}	
      				}
      				for(var i=0;i<all.length;i++){
      					all[i].disabled = false;
      				} 

       	}
       }
	});
	}
}
function submitActionAnother(){
	document.attendance.method="GET";
	document.attendance.action="insertTeacherAttendance.html";
	document.attendance.submit();             // back from the page
	return true;  
} 

function submitActionAll(){
	document.attendance.method="get";
	document.attendance.action="insertAllTeacherAttendance.html";
	document.attendance.submit();             // back from the page
	return true;  
} 

function submitAll(){
	var days = "";
	var details = "";
	var userIDs = "";
	var yearvalue = $("#year option:selected").text().trim();
	var monthvalue = $('#month :selected').val();
	var status = "all";
	$("#insertStatus").val(status);	
	details = details+monthvalue+","+yearvalue;
	var arrTeacherDetails = test.split("@");
	var resourceType = "TEACHING STAFF";
	if(resourceType == "TEACHING STAFF"){
		$("#hiddenDetails").val(monthvalue+","+yearvalue);
		$("#hiddenType").val(resourceType);
	}
	

	for(var teacherlist=0; teacherlist<arrTeacherDetails.length-1; teacherlist++){
		var arrTeacherNames = arrTeacherDetails[teacherlist+1].split(",");
		var user_id = arrTeacherNames[1];
		var row_id = teacherlist;
		userIDs += user_id+" >>NEXT USER >>";
		var row=document.getElementById('attendanceShow').rows[teacherlist+1];
		var all=row.getElementsByTagName("input");
		for(var i=0;i<all.length;i++){
			if(all[i].type=="checkbox"){
				if(all[i].checked == false && all[i].style.display != "none"){
					days = days  + all[i].value+",";
				}
			}
		}
		days = days+" >>>NEXT ATTENDANCE >>>";
	}
	$("#hiddenAbsentDay").val(days);
	$("#hiddenId").val(userIDs);
	submitActionAll();
	
}

/* added by sourav.bhadra on 25-07-2017
 * to check Special Holidays
 */
function checkHoliday(currentDate, specialHolidays){
	 var retVal = 1;
	 //alert("IN checkHoliday method...\nDate :: "+currentDate+"\nHodidays :: "+specialHolidays);
	 for(var i=0; i<specialHolidays.length-1; i++){
		 if(currentDate==specialHolidays[i]){
			 retVal = 0;
			 break;
		 }else{
			 retVal = 1;
		 }
	 }
	 return retVal;
}
</script>
</head>
<body>
	<header class="page-header">
		<h2>Teacher Attendance</h2>
	</header>
	<div class="content-padding">
	<div class="row">
		<form name="attendance">
	 <%-- <c:choose>
		<c:when test="${listTerm eq null && listTerm.size() eq 0}">			
			<div class="alert alert-danger" style="display: block;">
				<strong>No Term Found.</strong>
			</div>
		</c:when>
		<c:otherwise>			
			<c:set var="listTerm"  scope="page"></c:set>						
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${currentYear eq null}">			
			<div class="alert alert-danger" style="display: block;">
				<strong>No Academic Year Found.</strong>
			</div>
		</c:when>
	<c:otherwise>
		<c:set var="currentYear"  scope="page"> </c:set>
	</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${specialHolidays eq null && specialHolidays.size() eq 0}">			
			<div class="alert alert-danger" style="display: block;">
				<strong>No Special Holidays Found.</strong>
			</div>
		</c:when>
	<c:otherwise>
		<c:set var="specialHolidays"  scope="page"> </c:set>
	</c:otherwise>
	</c:choose> --%>
			<div class="col-md-10 col-md-offset-1">
				<section class="panel">
					<header class="panel-heading">
					    <div class="panel-actions">
					        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
					    </div>
					    <h2 class="panel-title">Details For Teacher Attendance</h2>										
					</header>
					<div style="display: block;" class="panel-body">
						<div class="col-md-3">
							<div class="form-group">
								<label class="control-label">Year</label>
								<select class="form-control" id="year" name="year">
									<option value="">Select...</option>
									<c:if test="${year ne null}">
										<c:forEach var="year" items="${year}">
											<option value="${year.academicYearName}">${year.academicYearName}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label class="control-label">Month</label>
								<select class="form-control" id="month" name="month">
									<option value="0">Select...</option>
								</select>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label class="control-label">Resource Type</label>
								<select class="form-control" id="resourceType" name="resourceType">
									<option value="0">Select...</option>
									<c:forEach var="resourceType" items="${resourceTypeList}">
										<c:if test="${resourceType.resourceTypeName eq 'TEACHING STAFF'}">
											<option value="${resourceType.resourceTypeName}">${resourceType.resourceTypeName}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-md-3">
							<div class="form-group">
								<label class="control-label">Job Type</label>
								<select class="form-control" id="jobtype" name="jobtype">
									<option value="0">Select...</option>
									<c:forEach var="jobType" items="${jobTypeList }">
										<c:if test="${jobType.jobTypeName ne NULL}">
											<option value="${jobType.jobTypeName}">${jobType.jobTypeName}</option>
										</c:if>
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
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Attendance</h2>
					</header>
					<div id="title" style="display: none;" class="panel-body">
						<table id="attendanceShow" class="table table-bordered table-striped table-condensed mb-none" >
							<thead>
								<tr>
								</tr>
							</thead>
						</table>
					</div>
					<div id="container" style="display:none"></div>
					<input type="hidden" id="hiddenAbsentDay" name="hiddenAbsentDay" value=""/>
					<input type="hidden" id="hiddenDetails" name="hiddenDetails" value=""/>
					<input type="hidden" id="hiddenId" name="hiddenId" value=""/>
					<input type="hidden" id="hiddenType" name="hiddenType" value=""/>
				
					<footer style="display: block;" class="panel-footer">
						<button id="allSubmit" class="btn btn-primary" onclick = "submitAll();" style="display: none;">Submit All </button> 
					</footer>
				</section>
			</div>
		</form>
	</div>
	</div>					
	<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
	<%@ include file="/include/js-include.jsp" %>
</body>
</html>