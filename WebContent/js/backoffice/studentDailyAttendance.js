$(document).ready(function() {
	$("#class").change(function() {
		document.getElementById("allSubmit").style.display = "none";
		$("#title").hide();
		$("#emptyMsg").hide();
		$.ajax({
	        url: '/cedugenie/getSectionForAttendance.html',
	        data:"class=" + ($("#class").val()),
	        dataType: 'json',
	        success: function(data) {
	        	if(data == null){
	        		alert("There is no section for the selected class.");
	        		var sectionid=document.getElementById("sectionone");
	        		$("#title").hide();
    				//document.getElementById("attendanceShow").style.display = "none";
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

	
	$("#sectionone").change(function() {
		var d = new Date();
		var month = d.getMonth() + 1;
		var year = d.getYear();
		/* ajax call to get the roll numbers of the students who are already present for the current date */
		$.ajax({
	        url: '/cedugenie/getStudentsRollNumbersForAlreadyAttendedStudents.html',
	        data:"class=" + ($("#class").val()) + "&section=" + ($(this).val()) + "&currentDate=" + ($("#currentDate").val()),
	        dataType: 'json',
	        success: function(dataNew) { 
	        	window.presentStudents = dataNew.split("*");
	        }
		});
		/* ajax call to get the roll numbers of all students */
	   	$.ajax({
	        url: '/cedugenie/getStudentsForDailyAttendance.html',
	        data:"class=" + ($("#class").val()) + "&section=" + ($(this).val()) + "&currentDate=" + ($("#currentDate").val()),
	        dataType: 'json',
	        success: function(data) { 
	        	if(data == null || data == ""){
	        		document.getElementById("emptyMsg").innerHTML = "<strong>There is no student for the selected class and section.</strong>";
			    	document.getElementById("emptyMsg").style.display = "block";
       				document.getElementById("allSubmit").style.display = "none";
       				document.getElementById("title").style.display = "none";
	        	}else{
		        	var splitedDatas = 	data.split("#");
		        	if(null == splitedDatas[0] || splitedDatas[0] == "" || splitedDatas[0] == "null"){
		        		document.getElementById("emptyMsg").innerHTML = "<strong>There is no student for the selected class and section.</strong>";
		        		document.getElementById("emptyMsg").style.display = "block";
		        		document.getElementById("allSubmit").style.display = "none";
		        		document.getElementById("title").style.display = "none";
		        	}else{
		        		var studentTable = "";
		        		var studentArray = splitedDatas[0].split("@");
		        		var chkBox = "";
			        	for(var i=0; i<studentArray.length; i++){
			        		var studentDetails = studentArray[i].split(",");
			        		for(var j=0; j<presentStudents.length; j++){
			        			var status = "";
			        			if(studentDetails[3] == presentStudents[j]){
			        				status = "Chk";
			        				break;
			        			}
			        		}
			        		if(status == "Chk"){
			        			chkBox = "<input class='checkedBox' type='checkbox'id='checkbox_"+studentDetails[3]+"' name='checkedBox_"+studentDetails[3]+"' checked disabled>";
			        		}else{
			        			chkBox = "<input class='checkedBox' type='checkbox'id='checkbox_"+studentDetails[3]+"' name='checkedBox_"+studentDetails[3]+"'>";
			        		}
			        		studentTable += "<tr><td><input type='hidden' id='studentRoll"+i+"' name='studentRoll' value='"+studentDetails[3]+"'>"+studentDetails[3]+"</td>"+
				        					"<td><input type='hidden' id='studentName"+i+"' name='studentName' value='"+studentDetails[0]+studentDetails[1]+studentDetails[2]+"'>"+studentDetails[0]+" "+studentDetails[1]+" "+studentDetails[2]+"</td>"+
				        					"<td id='attendanceChk_"+studentDetails[3]+"'>"+chkBox+"</td>"+
				        					"<td id='leaveComment_"+studentDetails[3]+"'></td>"+
				        					"</tr>";
			        	}
			        	$("#attendanceShow").html(studentTable);
			        	var studentLeave = splitedDatas[1].split("/");
			        	if(studentLeave.length != 0){
				        	for(var i=0; i<studentLeave.length; i++){
				        		if(null != studentLeave[i] && studentLeave[i] != ""){
					        		var leaveDetails = studentLeave[i].split(",");
					        		$("#attendanceChk_"+leaveDetails[0]).html("<input type='hidden' name='checkedBox_"+leaveDetails[0]+"' value='On Leave'><strong>On Leave</strong>");
					        		$("#leaveComment_"+leaveDetails[0]).html(leaveDetails[1]);
				        		}
				        	}
			        	}
			        	document.getElementById("title").style.display = "block";
			        	document.getElementById("allSubmit").style.display = "block";
			        	document.getElementById("emptyMsg").style.display = "none";
			        	
			        	setTimeout(studentAttendanceCheck, 1000);
		        	}
		        }
          	}
		}); 
		
 	});
});

$('#selectChecked').click(function(e) {
	$('[class="checkedBox"]').prop('checked', this.checked);
});

$('[class="checkedBox"]').click(function(e) {
	if ($('[class="checkedBox"]:checked').length == $('[class="checkedBox"]').length || !this.checked)
		$('#selectChecked').prop('checked', this.checked);
});

function studentAttendanceCheck(){
	
}
