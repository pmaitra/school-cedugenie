$(document).ready( function(){
	$("#courseName").change(function (){   	
			//alert("within js");
			var table = document.getElementById("assignRollTable");
			$.ajax({
				    url: '/cedugenie/getStudentsForAssignRollNumber.html',
				    dataType: 'json',
				    data:"course=" + $("#courseName").val(),
				    success: function(data) {	
				    	//alert(data);
				    	 $('#rollTable > tbody').empty();
				    	var options='<option value="">Select</option>';
				    	if(data!=null && data != ""){	        		
				    		var dataDB=data.split("#");
				        	
				        	for(var i=1;i<dataDB.length;i++){
				        		var dataArr = dataDB[i].split("*");
				                var rowCount = table.rows.length;
				                var row = table.insertRow(rowCount);
				                
				                var cell,element;
				                
				                /*cell = row.insertCell(0);		
				                element = document.createElement("input");
				                element.type = "checkbox";
				                element.name="rollNumber";
				                element.className="form-control";
				                element.value=data[0];
				                cell.appendChild(element);*/
				                
				                cell = row.insertCell(0);		
				                element = document.createTextNode(dataArr[0]);
				                cell.appendChild(element);
				                
				                cell = row.insertCell(1);		
				                element = document.createTextNode(dataArr[1]);
				                cell.appendChild(element);
		        				        				
				    	}
				    	//document.getElementById("courseName0").innerHTML=options;
				    	/*else{
				    		alert("No Course Found For Class ")+ (thisClassNode.value);
				    	}*/
				        document.getElementById("assignRoll").style.display = "block";
				        document.getElementById("footer").style.display = "none";
				    }else{
				    	var course = $("#courseName").val();
				    	var academicYear = document.getElementById("academicYearCode").value 
				    	//alert("academicYear===="+academicYear);
				    	document.getElementById("courseId").value = course;
				    	 document.getElementById("assignRoll").style.display = "none";
				    	document.getElementById("generate").setAttribute("href","generateRollNumberForStudent.html?courseCode="+course+"&academicYear="+academicYear);
				    	 document.getElementById("footer").style.display = "block";
				    }
				    	
				}	
			
			});
	});
	
	/*function genarateRollNumber(){
		//alert("inactiveApproverGroupDetails.html?approverGroupCode="+url);
		var course = $("#courseName").val();
    	var academicYear = document.getElementById("academicYearCode").value 
		window.location="inactiveApproverGroupDetails.html?approverGroupCode="+url;
	}*/
});