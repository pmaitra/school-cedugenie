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
<title>Student Attendance</title>
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
		$("#class").val('0');
		$("#course").val('0');
		$("#sectionone").val('0');
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
	 
	$("#month").change(function(){
		$("#resourceType").val('0');
		$("#class").val('0');
		$("#course").val('0');
		$("#sectionone").val('0');
		$("#title").hide();
		document.getElementById("allSubmit").style.display = "none";
		
		var yearvalue = $("#year option:selected").text();
		var monthvalue = $('#month :selected').val();
		var yearvalue1 = $("#year option:selected").text().trim();
		var monthvalue1 = $('#month :selected').val();
		$("#resourceType").removeAttr('disabled');
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
			
			$.ajax({
		        url: '/cedugenie/getClassForAttendance.html',
		        dataType: 'json',
		        success: function(data) {
		        	if(data != "null"){
		        		var arrClass = data.split(",");
		        		var classid=document.getElementById("class");
		        		for(var classlist=0;classlist<=arrClass.length;classlist++){
		        			classid.add(new Option(arrClass[classlist], arrClass[classlist],null));
		        		}
		        		classid.remove(classlist);
		        	}
		        }
			});
	});
	
	
	
	
	$("#resourceType").change(function(){
		$("#class").val('0');
		$("#course").val('0');
		$("#sectionone").val('0');
		$("#title").hide();
		document.getElementById("allSubmit").style.display = "none";
		
		var table1=document.getElementById("attendanceShow");
		var rowCount = table1.rows.length;
		rowCount=rowCount-1;
		$("#title").hide();
		document.getElementById("attendanceShow").style.display = "none";
		if($(this).val() == "STUDENT"){
			$("#classDetails").css('display','block');
			for(var i=rowCount;i>0;i--){
				table1.deleteRow(i);
			} 
		}
	});
	

	$("#class").change(function() {
		$("#course").val('0');
		$("#sectionone").val('0');
		$("#title").hide();
		document.getElementById("allSubmit").style.display = "none";
		 
		$.ajax({
	        url: '/cedugenie/getCourseForAttendance.html',
	        data: "class=" + ($(this).val()),
	        dataType: 'json',
	        success: function(data) {
	        	if(data == null){
	        		alert("There is no course for the selected class.");
			    	$("#title").hide();
    				document.getElementById("attendanceShow").style.display = "none";
    				document.getElementById("allSubmit").style.display = "none";
	        		var streamid=document.getElementById("course");
	        		for(var i=streamid.length;i>0;i--){
	        			streamid.remove(i);
	        		}
	        		var table1=document.getElementById("attendanceShow");
	        		var rowCount = table1.rows.length;
					rowCount=rowCount-1;
					
					for(var i=rowCount;i>0;i--){
						table1.deleteRow(i);
						
					} 
	        	}
	        	
	        	if(data != null){
	        		var arrCourse = data.split("@");
	        		var courseid=document.getElementById("course");
	        		for(var i=courseid.length;i>0;i--){
	        			courseid.remove(i);
	        		}
	        		for(var courselist=0;courselist<=arrCourse.length;courselist++){
	        			var arrSplitedData = arrCourse[courselist].split(",");
	        			courseid.add(new Option(arrSplitedData[1], arrSplitedData[0],null));
	    			}
	        		courseid.remove(courselist);
	        	}
	        }
		});
	});

	
	$("#course").change(function() {
		$("#sectionone").val('0');
		$("#title").hide();
		document.getElementById("allSubmit").style.display = "none";
		 
		$.ajax({
	        url: '/cedugenie/getSectionForAttendance.html',
	        data:"class=" + ($("#class").val()),
	        dataType: 'json',
	        success: function(data) {
	        	if(data == null){
	        		alert("There is no section for the selected class.");
	        		var sectionid=document.getElementById("sectionone");
	        		$("#title").hide();
    				document.getElementById("attendanceShow").style.display = "none";
    				document.getElementById("allSubmit").style.display = "none";
		        	for(var i=sectionid.length;i>0;i--){
		        		sectionid.remove(i);
		        	}
		        	var table1=document.getElementById("attendanceShow");
	        		var rowCount = table1.rows.length;
					rowCount=rowCount-1;
					for(var i=rowCount;i>0;i--){
						table1.deleteRow(i);
					}
	        	}
	        	if(data != null){
	        		var sectionid=document.getElementById("sectionone");
		        	for(var i=sectionid.length;i>0;i--){
		        		sectionid.remove(i);
		        	}
		        	var arrsection=data.split(",");
		        	for(var sectionlist=0;sectionlist<arrsection.length;sectionlist++){
		        		sectionid.add(new Option(arrsection[sectionlist], arrsection[sectionlist],null));
		        	}
	        	}
	        }
		}); 
	});

	var y=1;
	$("#sectionone").change(function() {
		var yearvalue = $("#year option:selected").text().trim();
		var monthvalue = $('#month :selected').val();
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
	   	$.ajax({
	        url: '/cedugenie/getStudentsForAttendance.html',
	        data:"class=" + ($("#class").val()) + "&section=" + ($(this).val()) + "&year=" + yearvalue + "&month=" + monthvalue,
	        dataType: 'json',
	        success: function(data) {
	        	 if(data == null){
	        		alert("There is no student for the selected class and section.");
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
					
	        	var arrStudentDetails = data.split("@");
	        		
        		for(var studentlist=0;studentlist<=arrStudentDetails.length;studentlist++){
        			var arrStudentNames = arrStudentDetails[studentlist+1].split(",");
        			if(arrStudentNames != "null"){
	        			$("#title").show();
	        			document.getElementById("attendanceShow").style.display = "block";
	        			var table=document.getElementById("attendanceShow");
	        			var rowc=table.rows.length;
		        		var row=table.insertRow(rowc);
		        		row.id = studentlist;
		        		var cellstudent = row.insertCell(0);
		        		var element2 = document.createElement("input");
	        	        element2.setAttribute('readonly','readonly');
	        	        element2.type = "text"; 
	        	        element2.name="studRegId";
	        	        element2.id="studRegId"+y; 
	        	        cellstudent.appendChild(element2);
		        		var rowIndex = 0; // rowindex, in this case the first row of your table
		        		var row1 = table1.getElementsByTagName('tr')[rowIndex];
		        		var cells = row1.getElementsByTagName('th');
		        		var cellCount = cells.length;
		        		var cell = "";
		        		var element1 = "";
		        		var insertedStudId = arrStudentDetails[0].split("#");
		        	    var insertedStudIdLen = insertedStudId.length-1;
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
		        	        }else{
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
					        	       
	        	       if(arrStudentDetails[0] != "null" ){
		        	       for(var names=0;names<=insertedStudIdLen;names++){
								namess = insertedStudId[names].split("~");
		        				dates = namess[1].split("/");
		        				if(namess[0] == arrStudentNames[3]){
		        					element1.disabled = true;
		        					if(dates[0] != i ){
		        						element1.checked = true;
				        			}else{
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
	        	        btn1.id=studentlist; 
	        	        btn1.setAttribute("onclick","selection(this.id,"+row.id+","+arrStudentDetails.length+");");
	        	        var t1=document.createTextNode("Select All");
	        	        btn1.appendChild(t1);
	        	        cell.appendChild(btn1); 
	        	        
	        	        var btn2=document.createElement("BUTTON");
	        	        btn2.type="button";
	        	        btn2.className = "clearbtn1 btn-danger btn-xs";
	        	        btn2.name="deselectAction";
	        	        btn2.id="studentlist"+studentlist; 
	        	        btn2.setAttribute("onclick","cancelSelection(this.id,"+row.id+","+arrStudentDetails.length+");");
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
	        	       
	        	        for(var names=0;names<=insertedStudIdLen;names++){
	        				namess = insertedStudId[names].split("~");
	        				if(namess[0] == arrStudentNames[3]){
	        					btn.disabled= true;
	        					btn2.disabled= true;
	        					btn1.disabled = true;
		        			}
		    			}
	        	    // open loop for each row and append cell
	        	  	 for(var namelist=0;namelist<=arrStudentNames.length;namelist++){
	        			var newName = arrStudentNames[0] + " " + arrStudentNames[1] + " " + arrStudentNames[2];
	        			var rowIndex1 = 1; // rowindex, in this case the first row of your table
		        		var row11 = table1.getElementsByTagName('tr')[rowIndex1];
		        		var cells11 = row11.getElementsByTagName('td');
		        		cellstudent.innerHTML=newName;
		        		
	        			var arrRegId = arrStudentDetails[3];
	        		
	        			element2.value=arrStudentNames[3];
	        			var vl=element2.value;
	        			var resourceTypeName="STUDENT";
	        			
	        			btn.setAttribute("onclick","submitActions("+row.id+",'"+vl+"','"+resourceTypeName+"'); return submitActionAnother();");
	        			btn3.setAttribute("onclick","editActions("+row.id+",'"+vl+"','"+resourceTypeName+"');");
    				}	    	
    			}else{
        				$("#title").hide();
        				document.getElementById("attendanceShow").style.display = "none";
        				document.getElementById("allSubmit").style.display = "none";
        				/* $("#warningbox").css('visibility', 'visible');
				    	$('#warningmsg').text("There is no student."); */
				    	alert("There is no student.");
        			}
    			}
          	}
		}); 
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

function submitActions(row_id,vl,resourceTypeName){
	var row=document.getElementById('attendanceShow').rows[row_id+1];
	var all=row.getElementsByTagName("input");
	$("#hiddenId").val(vl);
	var days = "";
	var details = "";
	var yearvalue = $("#year option:selected").text().trim();
	var monthvalue = $('#month :selected').val();
	var classvalue = $("#class option:selected").text().trim();
	var streamvalue = $("#stream option:selected").text().trim();
	var sectionvalue = $("#sectionone option:selected").text().trim();
	for(var i=0;i<all.length;i++){
		
		if(all[i].type=="checkbox"){
			if(all[i].checked == false && all[i].style.display != "none"){
			days = days  + all[i].value+",";
			$("#hiddenAbsentDay").val(days);
			}
		}
	} 
	details = details +monthvalue+","+yearvalue+","+classvalue+","+streamvalue+","+sectionvalue;
	if(resourceTypeName == "STUDENT"){
	$("#hiddenDetails").val(monthvalue+","+yearvalue+","+classvalue+","+streamvalue+","+sectionvalue);
	$("#hiddenType").val(resourceTypeName);
	}
	
}

function editActions(row_id,vl,resourceType){
	var yearvalue = $("#year option:selected").text().trim();
	var monthvalue = $('#month :selected').val();
	var row=document.getElementById('attendanceShow').rows[row_id+1];
	var all=row.getElementsByTagName("input");
	var buttons = row.getElementsByTagName("button");
	if(resourceType == "STUDENT"){
	$.ajax({
        url: '/cedugenie/getDateOfCreationForInsertedStudent.html',
        data:"month=" + monthvalue + "&year=" + yearvalue+ "&studentId=" + vl,
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
	if(resourceType != "STUDENT"){
		$.ajax({
	        url: '/cedugenie/getDateOfCreationForInsertedResource.html',
	        data:"month=" + monthvalue + "&year=" + yearvalue+ "&userId=" + vl+ "&shift=" + workShift+ "&resourceType=" + resourceType,
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
       		      var presentDate = new Date(y,m,d); //present date
       		     for(var i=0;i<buttons.length;i++)
       				{
       					if(buttons[i].disabled = true){
       						buttons[i].disabled = false;
       					}
       						
       				} 
       				for(var i=0;i<all.length;i++)
       				{
       					all[i].disabled = false;
       						
       				}
	        	}
	        }
		});
	}
 
}
 function submitActionAnother(){
	document.attendance.method="GET";
	document.attendance.action="insertStudentAttendance.html";
	document.attendance.submit();             // back from the page
	return true;  
} 
 
 function submitActionAll(){
	document.attendance.method="get";
	document.attendance.action="insertAllStudentAttendance.html";
	document.attendance.submit();             // back from the page
	return true;  
} 
 
 function submitAll(){
	var days = "";
	var details = "";
	var roll_nos = "";
	var yearvalue = $("#year option:selected").text().trim();
	var monthvalue = $('#month :selected').val();
	var status = "all";
	$("#insertStatus").val(status);	
	var classvalue = $("#class option:selected").text().trim();
	var sectionvalue = $("#sectionone option:selected").text().trim();
	details = details+monthvalue+","+yearvalue+","+classvalue+","+sectionvalue;
	var arrStudentDetails = test.split("@");
	var resourceType = "STUDENT";
	if(resourceType == "STUDENT"){
		$("#hiddenDetails").val(monthvalue+","+yearvalue+","+classvalue+","+sectionvalue);
		$("#hiddenType").val(resourceType);
	}
	if(resourceType != "STUDENT"){
		$("#hiddenDetails").val(monthvalue+","+yearvalue);
		$("#hiddenType").val(resourceType);
	}
	for(var studentlist=0; studentlist<arrStudentDetails.length-1; studentlist++){
		var arrStudentNames = arrStudentDetails[studentlist+1].split(",");
		var rollNo = arrStudentNames[arrStudentNames.length-1];
		var row_id = studentlist;
		roll_nos += rollNo+" >>NEXT ROLL >>";
		var row=document.getElementById('attendanceShow').rows[studentlist+1];
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
	$("#hiddenId").val(roll_nos);
	$("#hiddenRoll").val(roll_nos);
	submitActionAll();
 }
 
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
		<h2>Student Attendance</h2>
	</header>
	<div class="content-padding">				
					 
	<div class="row">
		 <form name="attendance">
			 <c:choose>
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
			</c:choose>
			<div class="col-md-8 col-md-offset-2">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Details For Student Attendance</h2>										
					</header>
					<div style="display: block;" class="panel-body">
						<div class="col-md-4">
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
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label">Month</label>
								<select class="form-control" id="month" name="month">
									<option value="0">Select...</option>
								</select>
							</div>
						</div>
						<div class="col-md-4">
							<div class="form-group">
								<label class="control-label">Resource Type</label>
								<select class="form-control" id="resourceType" name="resourceType">
									<option value="0">Select...</option>
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
						<div class="col-md-5">
							<div class="form-group">
								<label class="control-label">Class</label>
								<select class="form-control" id="class" name="class">
									<option value="0">Select...</option>
								</select>
							</div>
						</div>
						<div class="col-md-5">
							<div class="form-group">
								<label class="control-label">Course</label>
								<select class="form-control" id="course" name="class">
									<option value="0">Select...</option>
								</select>
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<label class="control-label">Section</label>
								<select class="form-control" id="sectionone" name="sectionone">
									<option value="0">Select...</option>
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