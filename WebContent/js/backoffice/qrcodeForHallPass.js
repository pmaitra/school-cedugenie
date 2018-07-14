
function deleteRow(table){
    var rowCount = table.rows.length;
    for(var i=rowCount; i>=1; i--){
   		table.deleteRow(i-1);
    }
}

$(document).ready(function(){
	$("#class").change(
			function() {
			$.ajax({
		        url: '/cedugenie/getCourseInClass.html',
		        data: "class=" + ($(this).val()),
		        dataType: 'json',
		        success: function(data) {
		        	var course = document.getElementById("course");
		        	if(data == ""){
		        		for(var i=course.length-1;i>0;i--){
							course.remove(i);
						}
		        		alert("Course is not created for particular class.");
		        	}
		        	if(data != ""){
		        	var data = data.split("~*~");
					
					for(var i=course.length-1;i>0;i--){
						course.remove(i);
						} 
					for(var i=0;i<data.length-1;i++){
						var x=data[i].split("*~*");
						course.add(new Option(x[1], x[0]),null);
						} 			        	
		            }
		        }
			});			
		});
	
	$("#course").change(
			function(){
			var examName = document.getElementById("examName");
			if($("#class").val()!= "" && $("#course").val()!= ""){
				$.ajax({
			        url: '/cedugenie/getExamsForTermCourseAndExamType.html',
			        data: "courseCode=" + ($("#course").val())+ "&classCode=" + ($("#class").val()),
			        dataType: 'json',
			        success: function(data){
			        	if(data == null){
			        		for(var i=examName.length-1;i>0;i--){
								examName.remove(i);
							}
			        		alert("No exam is assigned for the selected standard and course.");
				        }
			        	if(data != null){
					        var data=data.split("~*~");
						
							for(var i=examName.length-1;i>0;i--){
								examName.remove(i);
							}
							for(var i=0;i<data.length-1;i++){
								var x=data[i].split("*~*");
								examName.add(new Option(x[1], x[0]),null);
							}
			        	}
			        }
			    });			
			}
			if($(this).val()== "" || $("#course").val()== ""){
				for(var i=examName.length-1;i>0;i--){
					examName.remove(i);
				}
				alert("Select proper option.");
			}
		});
	
	$("#class").change(
			function() {
			var section = document.getElementById("section");
			if($(this).val()!= ""){
				$.ajax({
			        url: '/cedugenie/getSectionForClassAndStream.html',
			        data: "classCode=" + ($("#class").val()),
			        dataType: 'json',
			        success: function(data){
			        	if(data == null){
			        		for(var i=section.length-1;i>0;i--){
								section.remove(i);
							}
			        	}
			        	if(data != null){
				        	var data=data.split("~*~");
							
							for(var i=section.length-1;i>0;i--){
								section.remove(i);
								} 
							for(var i=0;i<data.length-1;i++){
								var x=data[i].split("*~*");
								section.add(new Option(x[1], x[0]),null);
							}
			           }
			        }
			   });		
			}
			if($(this).val()== ""){
				for(var i=section.length-1;i>0;i--){
					section.remove(i);
				} 
			}
		});
	
	$("#examName").change(
			function() {
			//var student = document.getElementById("student");
			if($(this).val()!= ""){
				alert($(this).val());				
				$.ajax({
			        url: '/cedugenie/getStudentForClassStreamSectionAndCourse.html',
			        data: "classCode=" + ($("#class").val())+"&sectionCode="+($("#section").val())+ "&courseCode=" + ($("#course").val()),
			        method: 'GET',
			        dataType: 'json',
			        success: function(data){
			        	if(data == ""){
			        		deleteRow(document.getElementById("subMar"));
			        		alert("Student is not assigned for particular class,secton and course.");
			        	}
			        	if(data != ""){
				        	var data=data.split("~*~");
						
				        	deleteRow(document.getElementById("subMar"));
				        	
							for(var i=0;i<data.length-1;i++){
								var x=data[i].split("*~*");
								
								var table = document.getElementById("subMar"); 
						        var rowCount = table.rows.length;
						        var row = table.insertRow(rowCount);
						        
								var cell1 = row.insertCell(0);		
						        var element1 = document.createElement("input");
						        element1.type = "hidden";
						        element1.name="StudentID";
						        element1.value=x[0];
								cell1.appendChild(element1);
								
								var cell2 = row.insertCell(1);		
						        var element2 = document.createElement("input");
						        element2.type = "hidden";
						        element2.name=x[0];
						        element2.value=x[1];
								cell2.appendChild(element2);
								
							}
			          }
			        }
			   });
			}
			if($(this).val()== ""){
				for(var i=student.length-1;i>0;i--){
					deleteRow(document.getElementById("subMar"));
				} 
			}
		});
	});