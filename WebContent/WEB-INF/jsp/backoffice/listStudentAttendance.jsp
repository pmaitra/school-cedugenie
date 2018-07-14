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
<meta name="description" content="Page to List Resource Attendance" />
<meta name="keywords" content="List Resource Attendance" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>List Resource Attendance</title>

<link rel="stylesheet" href="/sms/css/backoffice/listStudentAttendance.css" />
<link href="/sms/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/sms/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/sms/css/common/jquery-ui.css" />
<link href="/sms/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/sms/js/common/jquery-1.9.1.min.js"></script>

 <script>  
   window.onload=function()
   {
    var  sNames = "<c:out value="${studentdetails}"/>";  
    var resourceType = "<c:out value="${resourceType}"/>";
    var nameArray = sNames.split(","); 
    var table = document.getElementById("selectedStudentDetails"); 
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    
    
    
    var rowText=""+
    "<th><input type='hidden' name='month' id='month' value='"+nameArray[0]+"'> Month :: "+nameArray[0]+"</th>"+
    "<th><input type='hidden' name='year' id='year' value='"+nameArray[1]+"'> Year :: "+nameArray[1]+"</th>";
    if(resourceType != "STUDENT"){
    	rowText = rowText+"<th><input type='hidden' name='shift' id='shift' value='"+nameArray[2]+"'> Shift :: "+nameArray[2]+"</th>";
    }
   if( nameArray[3] != null){
	   rowText=rowText+"<th><input type='hidden' name='class' id='class' value='"+nameArray[2]+"'> Class :: "+nameArray[2]+"</th>"+
	   					"<th><input type='hidden' name='stream' id='stream' value='"+nameArray[3]+"'> Stream :: "+nameArray[3]+"</th>"+
	   					"<th><input type='hidden' name='section' id='section' value='"+nameArray[4]+"'> Section :: "+nameArray[4]+"</th>";
   }
   
   row.innerHTML=rowText;
   };
   
</script>  
		        
<script type="text/javascript">

$(document).ready(function() {
	var  sNames = "<c:out value="${studentdetails}"/>"; 
	var resourceType = "<c:out value="${resourceType}"/>";
    var nameArray = sNames.split(","); 
		var yearvalue = nameArray[1];
		var monthvalue = nameArray[0];
		$.ajax({
		    url: '/cedugenie/getDaysForAttendance.html',
		    	dataType: 'json',
		    	 data: "yearvalue=" + yearvalue+ "&monthvalue=" +monthvalue,
		    	 success: function(data) {
		    	
		    	   
		    		if(data != "null"){
		    			var table=document.getElementById("attendanceShow");
						var rowCount = table.rows.length;
						for(var i=0;i<rowCount;i++)
						{
							table.deleteRow(i);
						}
						
		    			 var rownew = "";
		    			 rownew = $('<tr>'); 
		    			 
						 var row = rownew.append($('<th  width="250" ></th>').html("Resource"));
		   			 	 var arr = data.split(",");	
		   			 	 var len = arr.length;
		   			 	 var d = new Date();
			   			 var month = d.getMonth() + 1;
			   			 var day = d.getDate();
			   			 var year = d.getYear();
			   			 var today = (day<10?'0':'')+ day + '/' +(month<10?'0':'')+ month + '/' + year;
			   				$("#classDetails").show();
				    		for(var clist=0;clist<=arr.length-1;clist++)
				    			{
									var arr1 = arr[clist].split("/");
				    				for(var cfees=0;cfees<arr1.length-1;cfees++){	
				    					if(arr1[cfees] != "" || arr1[cfees] != ""){	 
				    						if(arr1[1]==7 || arr1[1]==1 ){
				    							
				    							 row =rownew.append($('<th class="cssTable" width="20" ></th>').html(arr1[0]));
				    						}
				    						else if(arr1[0] == day && monthvalue == month){
				    							row =rownew.append($('<th  class="cssTable1" width="20" ></th>').html(arr1[0]));
				    						}
				    						else
				    							row =rownew.append($('<th  width="20" ></th>').html(arr1[0]));
				    					}
				    					
				    				}
				    				
				    			}
				    		row =rownew.append($('<th  width="270" ></th>').html("Action"));
				    		$('#attendanceShow').append(row);
				    		
				    	}
			    	
				}
	});
	
		
					var startday = "";
					var endday = "";
					var startholiday= "";
					var endYearDigit = "";
					var startYearDigit = "";
					var startMonthDigit = "";
					var endMonthDigit = "";
					 var termStartActual = "<c:out value="${currentYear.sessionStartDate}"/>" ;
					 var termEndActual = "<c:out value="${currentYear.expectedSessionEndDate}"/>" ;
					 var yearSplitArray = termStartActual.split("/"); 
					 var yearenddateSplitArray = termEndActual.split("/"); 
					 monthvalue = parseInt(monthvalue);
					 endYearDigit = yearenddateSplitArray[2];
					 startMonthDigit = parseInt(yearSplitArray[1]);
					 startYearDigit = parseInt(yearSplitArray[2]);
					 endMonthDigit = parseInt(yearenddateSplitArray[1]);
			            var y=1;
						
			   			
			   			
			            if(startMonthDigit <= monthvalue){
							 startday = yearSplitArray[0];
							
						 	}
						    if(yearenddateSplitArray[2] == yearvalue && yearenddateSplitArray[1] == monthvalue){
							 endday = yearenddateSplitArray[0];
							
						 }
				  if(resourceType == "STUDENT"){
					$.ajax({
				        url: '/cedugenie/getStudentsForAttendance.html',
				        data:"class=" + nameArray[2] + "&section=" + nameArray[4] + "&streamName=" + nameArray[3] + "&year=" +yearvalue + "&month=" +monthvalue ,
				        dataType: 'json',
				        success: function(data) {
				
				        	if(data != null){
				        		var noOfDaysInMonth = new Date(yearvalue, monthvalue, 0).getDate();
				        		var table1=document.getElementById("attendanceShow");
				        		table1.cellSpacing="5";
								var rowCount = table1.rows.length;
								for(var i=1;i<rowCount;i++){
									table1.deleteRow(i);
								} 
				        		var arrStudentDetails = data.split("@");
				        		for(var studentlist=0;studentlist<=arrStudentDetails.length;studentlist++){
				        			 var insertedStudId = arrStudentDetails[0].split(",");
				        			 var arrStudentNames = arrStudentDetails[studentlist+1].split(",");
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
					        		 var label = "";
					        		 var insertedStudId = arrStudentDetails[0].split("&");
					        	     var insertedStudIdLen = insertedStudId.length-1;
					        	     var namess = "";
					        	     var dates = "";
									 
					        		 for (var i=1;i<cellCount;i++){
					        			cell = row.insertCell(i);
					        			
					        	        element1 = document.createElement("input");
					        	        element1.type = "checkbox";
					        	        element1.name="serialNo"; 
					        	        element1.id="serialNo"+y;  
					        	        label = document.createElement("Label");
					        	        label.innerHTML="H";
					        	        label.className = "cssTable2";
					        	        if(i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9 ){
					        	        	 element1.setAttribute("value","0"+i+"/"+monthvalue+"/"+yearvalue);
					        	        }
					        	        else{
					        	        	element1.setAttribute("value",i+"/"+monthvalue+"/"+yearvalue);
					        	        }
					        	        if(cells[i].className == "cssTable"){
											element1.style.display="none";
											cell.appendChild(label);
					        			}
					        	    
					        	        if(startYearDigit == yearvalue && monthvalue == startMonthDigit){
							        	       if(startday>i ){
							        	    			element1.style.display="none";
							        	        		}
							        	       }
							        	       //have to change
							        	        if(startYearDigit == yearvalue && monthvalue < startMonthDigit){
							        	    	   element1.style.display="none";
							        	       } 
							        	      if(endYearDigit == yearvalue && monthvalue == endMonthDigit){
								        	       if(endday<i){
							        	    			element1.style.display="none";
							        	        		} 
								        			} 
							        	      if(i>noOfDaysInMonth){
							        	    	  element1.style.display="none";
							        	      }
					        	        cell.appendChild(element1);
					        	        y++;
					        			  for(var names=0;names<=insertedStudIdLen;names++){
					        				 namess = insertedStudId[names].split("~");
					        				 dates = namess[1].split("/");
					        				 if(namess[0] == arrStudentNames[3]){
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
					        		
					        			cell.removeChild(element1);
					        	        var btn=document.createElement("BUTTON");
					        	        btn.type="submit";
					        	        btn.className = "submitbtn2";
					        	        btn.name="submitAction";
					        	        btn.id="submitAction"+y; 
					        	       
					        	        var t=document.createTextNode("Submit");
					        	        btn.appendChild(t);
					        	        cell.appendChild(btn); 
					        	        var btn1=document.createElement("BUTTON");
					        	        btn1.type="button";
					        	        btn1.name="selectAction";
					        	        btn1.className = "clearbtn2";
					        	        btn1.id=studentlist; 
					        	        btn1.setAttribute("onclick","selection(this.id,"+row.id+","+arrStudentDetails.length+");");
					        	        var t1=document.createTextNode("SelectAll");
					        	        btn1.appendChild(t1);
					        	        cell.appendChild(btn1); 
					        	        var btn2=document.createElement("BUTTON");
					        	        btn2.type="button";
					        	        btn2.className = "clearbtn2";
					        	        btn2.name="selectAction";
					        	        btn2.id="studentlist"+studentlist; 
					        	        btn2.setAttribute("onclick","cancelSelection(this.id,"+row.id+","+arrStudentDetails.length+");");
					        	        var t2=document.createTextNode("SelectNone");
					        	        btn2.appendChild(t2);
					        	        cell.appendChild(btn2); 
					        	        var btn3=document.createElement("BUTTON");
					        	        btn3.type="button";
					        	        btn3.className = "editbtn2";
					        	        btn3.name="editAction";
					        	        btn3.id="editAction"+y; 
					        	      
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
										
										
					        	  	 for(var namelist=0;namelist<=arrStudentNames.length;namelist++){
						        			var newName = arrStudentNames[0] + " " + arrStudentNames[1] + " " + arrStudentNames[2];
						        			var rowIndex1 = 1; // rowindex, in this case the first row of your table
							        		var row11 = table1.getElementsByTagName('tr')[rowIndex1];
							        		var cells11 = row11.getElementsByTagName('td');
							        		cellstudent.innerHTML=newName;
							        		
						        			var arrRegId = arrStudentDetails[3];
						        			
						        			element2.value=arrStudentNames[3];
						        			var vl=element2.value;
						        			var resourceTypeName= resourceType;
						        			 btn.setAttribute("onclick","submitActions("+row.id+",'"+vl+"','"+resourceTypeName+"'); return submitActionAnother();");
						        			 btn3.setAttribute("onclick","editActions("+row.id+",'"+vl+"','"+resourceTypeName+"');");
					    				}
				    				}
					        		 
					        	}
				       		}
					}); 
				  }
				  if(resourceType != "STUDENT"){
					  
					  $.ajax({
					        url: '/cedugenie/getTeacherDetailsForAttendance.html',
					        data: "shift=" + nameArray[2] + "&year=" + yearvalue + "&month=" + monthvalue+ "&resourceTypeSelected=" + resourceType,
					        dataType: 'json',
					        success: function(data) {
					        	if(data != null){
					        		var noOfDaysInMonth = new Date(yearvalue, monthvalue, 0).getDate();
					        		var table1=document.getElementById("attendanceShow");
					        		table1.cellSpacing="5";
									var rowCount = table1.rows.length;
									for(var i=1;i<rowCount;i++)
									{
										table1.deleteRow(i);
									} 
									
					        		var arrStudentDetails = data.split("$");
					        		var arrStudentNames = arrStudentDetails[1].split("@@@");
					        		 for(var studentlist=0;studentlist<arrStudentNames.length-1;studentlist++)
					 	    			{
					        			 var arrSplitedStudentNames = arrStudentNames[studentlist].split("/");
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
						        		 var label = "";
						        		 var insertedStudId = arrStudentDetails[0].split("&");
						        	     var insertedStudIdLen = insertedStudId.length-1;
						        	     var namess = "";
						        	     var dates = "";
										 
						        		 for (var i=1;i<cellCount;i++)
						        		 {
						        			cell = row.insertCell(i);
						        			
						        	        element1 = document.createElement("input");
						        	        element1.type = "checkbox";
						        	        element1.name="serialNo"; 
						        	        element1.id="serialNo"+y;  
						        	        label = document.createElement("Label");
						        	        label.innerHTML="H";
						        	        label.className = "cssTable2";
						        	        if(i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7 || i == 8 || i == 9 ){
						        	        	 element1.setAttribute("value","0"+i+"/"+monthvalue+"/"+yearvalue);
						        	        }
						        	        else{
						        	        	element1.setAttribute("value",i+"/"+monthvalue+"/"+yearvalue);
						        	        }
						        	        if(cells[i].className == "cssTable"){
												element1.style.display="none";
												cell.appendChild(label);
						        			}
						        	        if(startYearDigit == yearvalue && monthvalue == startMonthDigit){
								        	       if(startday>i ){
								        	    			element1.style.display="none";
								        	        		}
								        	       }
								        	       //have to change
								        	        if(startYearDigit == yearvalue && monthvalue < startMonthDigit){
								        	    	   element1.style.display="none";
								        	       } 
								        	      if(endYearDigit == yearvalue && monthvalue == endMonthDigit){
									        	       if(endday<i){
								        	    			element1.style.display="none";
								        	        		} 
									        			} 
								        	      if(i>noOfDaysInMonth){
								        	    	  element1.style.display="none";
								        	      }
						        	        cell.appendChild(element1);
						        	        y++;
						        			  for(var names=0;names<=insertedStudIdLen;names++){
						        				 namess = insertedStudId[names].split("~");
						        				 dates = namess[1].split("/");
						        					
						        				 if(namess[0] == arrSplitedStudentNames[0]){
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
						        			cell.removeChild(element1);
						        	        var btn=document.createElement("BUTTON");
						        	        btn.type="submit";
						        	        btn.className = "submitbtn2";
						        	        btn.name="submitAction";
						        	        btn.id="submitAction"+y; 
						        	       
						        	        var t=document.createTextNode("Submit");
						        	        btn.appendChild(t);
						        	        cell.appendChild(btn); 
						        	        var btn1=document.createElement("BUTTON");
						        	        btn1.type="button";
						        	        btn1.name="selectAction";
						        	        btn1.className = "clearbtn2";
						        	        btn1.id=studentlist; 
						        	        btn1.setAttribute("onclick","selection(this.id,"+row.id+","+arrStudentDetails.length+");");
						        	        var t1=document.createTextNode("SelectAll");
						        	        btn1.appendChild(t1);
						        	        cell.appendChild(btn1); 
						        	        var btn2=document.createElement("BUTTON");
						        	        btn2.type="button";
						        	        btn2.name="selectAction";
						        	        btn2.className = "clearbtn2";
						        	        btn2.id="studentlist"+studentlist; 
						        	        btn2.setAttribute("onclick","cancelSelection(this.id,"+row.id+","+arrStudentDetails.length+");");
						        	        var t2=document.createTextNode("SelectNone");
						        	        btn2.appendChild(t2);
						        	        cell.appendChild(btn2); 
						        	        var btn3=document.createElement("BUTTON");
						        	        btn3.type="button";
						        	        btn3.name="editAction";
						        	        btn3.id="editAction"+y; 
						        	        btn3.className = "editbtn2";
						        	        
						        	        var t3=document.createTextNode("Edit");
						        	        btn3.appendChild(t3);
						        	       	cell.appendChild(btn3); 
						        	        for(var names=0;names<=insertedStudIdLen;names++){
						        				 namess = insertedStudId[names].split("~");
						        				 if(namess[0] == arrSplitedStudentNames[0]){
						        					 btn.disabled= true;
						        					 btn2.disabled= true;
						        					 btn1.disabled = true;
						        				 }
							    			}
											
											
						        	  	 for(var namelist=0;namelist<=arrSplitedStudentNames.length;namelist++){
							        			var newName = arrSplitedStudentNames[1];
							        			var rowIndex1 = 1; // rowindex, in this case the first row of your table
								        		var row11 = table1.getElementsByTagName('tr')[rowIndex1];
								        		var cells11 = row11.getElementsByTagName('td');
								        		cellstudent.innerHTML=newName;
							        			var arrRegId = arrStudentDetails[3];
							        			element2.value=arrSplitedStudentNames[0];
							        			var vl=element2.value;
							        			var resourceTypeName=resourceType;
							        			 btn.setAttribute("onclick","submitActions("+row.id+",'"+vl+"','"+resourceTypeName+"'); return submitActionAnother();");
							        			 btn3.setAttribute("onclick","editActions("+row.id+",'"+vl+"','"+resourceTypeName+"');");
						    				}
								    	}
					        		}
					        }
					  }); 
					
				  }
	 
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

function submitActions(row_id,vl,resorceTypename){
	var row=document.getElementById('attendanceShow').rows[row_id+1];
	var all=row.getElementsByTagName("input");
	$("#hiddenId").val(vl);
	var days = "";
	var details = "";
	var  sNames = "<c:out value="${studentdetails}"/>";  
	var nameArray = sNames.split(","); 
	for(var i=0;i<all.length;i++)
	{
		
		if(all[i].type=="checkbox")
		{
			if(all[i].checked == false && all[i].style.display != "none"){
			days = days  + all[i].value+",";
			$("#hiddenAbsentDay").val(days);
			}
		}
	} 
	if(resorceTypename == "STUDENT"){
		details = details +nameArray[0]+","+nameArray[1]+","+nameArray[2]+","+nameArray[3]+","+nameArray[4];
		$("#hiddenType").val(resorceTypename);
		$("#hiddenDetails").val(details);
	}
	if(resorceTypename != "STUDENT"){
		$("#hiddenDetails").val(nameArray[0]+","+nameArray[1]+","+nameArray[2]);
		$("#hiddenType").val(resorceTypename);
	}
	
	
	
}
function editActions(row_id,vl,resourceType){
	var  sNames = "<c:out value="${studentdetails}"/>";  
	var nameArray = sNames.split(","); 
	var row=document.getElementById('attendanceShow').rows[row_id+1];
	var all=row.getElementsByTagName("input");
	var buttons = row.getElementsByTagName("button");
	
	if(resourceType == "STUDENT"){
		$.ajax({
	        url: '/cedugenie/getDateOfCreationForInsertedStudent.html',
	        data:"month=" + nameArray[0] + "&year=" + nameArray[1]+ "&studentId=" + vl,
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
	       		      if (presentDate > dateOfCreationFormat) {
	       		    	$("#warningbox").css('visibility', 'visible');
	       		    	$('#warningmsg').text("Attendance can be changed within two days after insertion.");
	       			  }
	       		      if (presentDate <= dateOfCreationFormat) {
	       		    	$("#warningbox").css('visibility', 'collapse');
	       		    	$('#warningmsg').text(""); 
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
	        }
		});
		}
	if(resourceType != "STUDENT"){
		$.ajax({
	        url: '/cedugenie/getDateOfCreationForInsertedResource.html',
	        data:"month=" + nameArray[0] + "&year=" + nameArray[1]+ "&userId=" + vl+ "&shift=" + nameArray[2]+ "&resourceType=" + resourceType,
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
       		      if (presentDate > dateOfCreationFormat) {
       		    	$("#warningbox").css('visibility', 'visible');
       		    	$('#warningmsg').text("Attendance can be changed within two days after insertion.");
       			  }
       		      if (presentDate <= dateOfCreationFormat) {
       		    	$("#warningbox").css('visibility', 'collapse');
       		    	$('#warningmsg').text(""); 
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
	        }
		});
	}
}
function checkedAction(){
}
function submitActionAnother(){
	document.attendance.method="get";
	document.attendance.action="insertStudentAttendance.html";
	document.attendance.submit();             // back from the page
	return true;  
} 

function backAction(){
		document.attendance.method="get";
		document.attendance.action="resourceAttendance.html";
		document.attendance.submit();             // back from the page
		return true;  
} 
</script> 
</head>
<body>
<div class="ttlarea">	
	<h1>
		<img src="/sms/images/titleicon01.png" alt="" />&nbsp;&nbsp;List Student And Teacher Attendance
	</h1>
</div>
<form name="attendance">
<c:choose>
			<c:when test="${resourceType eq null && resourceType.size() eq 0}">			
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No Resource Type Found</span>	
				</div>
			</c:when>
	<c:otherwise>			
			<c:set var="resourceType"  scope="page"> </c:set>
	</c:otherwise>
	</c:choose>
<c:choose>
			<c:when test="${listTerm eq null && listTerm.size() eq 0}">			
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No Term Found</span>	
				</div>		
			</c:when>
	<c:otherwise>			
			<c:set var="listTerm"  scope="page"> </c:set>
	</c:otherwise>
</c:choose>
<c:choose>
			<c:when test="${currentYear eq null && currentYear.size() eq 0}">			
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No Academic Year Found</span>	
				</div>		
			</c:when>
	<c:otherwise>			
			<c:set var="currentYear"  scope="page"> </c:set>
	</c:otherwise>
</c:choose>
<c:choose>
		<c:when test="${studentdetails eq null && studentdetails.size() eq 0}">			
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No studentdetails Found</span>	
				</div>	
		</c:when>
		<c:otherwise>
		<c:set var="studentdetails"  scope="page">  
    	</c:set>  
	   </c:otherwise>
</c:choose>
<c:choose>
			<c:when test="${specialHolidays eq null}">			
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No Special Holidays Found</span>	
				</div>			
			</c:when>
	<c:otherwise>			
			<c:set var="specialHolidays"  scope="page"> </c:set>
	</c:otherwise>
</c:choose>	

	<br>
	
		<table  class="midsec" id="selectedStudentDetails">
			
		</table>

	<div id="title" style="margin-left:45%;">::RESOURCE ATTENDENCE::</div>
		<table   class="midsec1" id="attendanceShow" >
			<thead>
				<tr>
				</tr>
			</thead>
		</table>


	<input type="submit" style="margin-left:45%;" id="back" name="back" onclick="backAction();" value="Back" class="editbtn"/>
	<br>
	<br>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
	<input type="hidden" id="hiddenAbsentDay" name="hiddenAbsentDay" value=""/>
	<input type="hidden" id="hiddenDetails" name="hiddenDetails" value=""/>
	<input type="hidden" id="hiddenId" name="hiddenId" value=""/>
	<input type="hidden" id="hiddenType" name="hiddenType" value=""/>
	
	</form>
</body>
</html>