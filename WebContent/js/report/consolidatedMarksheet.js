/*$(document).ready(function() {

	$("#standardName").change(
			function() {					
				if($(this).val() == ""){
					return;
				}
				$.ajax({
			        url: '/icam/getSectionAgainstStandard.html',
			        dataType: 'json',
			        data: {
			        		"standard":$(this).val()
			        },
			        success: function(data) {
			        	if(data != ""){  
			        		document.getElementById("warningbox").style.visibility='collapse';
			        		$('#sectionName')
			        		    .find('option')
			        		    .remove()
			        		    .end()
			        		    .append('<option value="">Please Select</option>')
			        		    .val('');
			        		var arr = data.split(";");
			        		for (var i=0;i<arr.length;i++){
			        		  		if(arr[i]!=""){
			        		  			var arr2 = arr[i].split("*");
			        		  			$("#sectionName").append($("<option></option>").val(arr2[0]).html(arr2[1]));
			        		  		}			        				
			        			}
			        		}
			        	else{				        					        		
				        		$('#sectionName')
			        		    .find('option')
			        		    .remove()
			        		    .end()
			        		    .append('<option value="">Please Select</option>')
			        		    .val('');				        		
			        			alert("No Section Found For Selected Class");
				    		}
			        }
			});
	});
	
	$("#standardName").change(function(){
//		table.style.visibility='collapse';
//		deleteRow(table);
//		document.getElementById("exam").value="";
//		document.getElementById("insertUpdate").value="INSERT";
//		document.getElementById("btnDiv").style.visibility='collapse';
//		document.getElementById("infomsgbox1").style.visibility='collapse';
//		document.getElementById("infomsg1").innerHTML="";
		
		if($(this).val()!=''){
			
			$.ajax({
		        url: '/icam/getSectionAgainstStandard.html',
		        dataType: 'json',
		        data: {
		        		"standard":$(this).val()
		        },
		        success: function(data) {
		        	if(data != ""){  
		        		document.getElementById("warningbox").style.visibility='collapse';
		        		$('#sectionName')
		        		    .find('option')
		        		    .remove()
		        		    .end()
		        		    .append('<option value="">Please Select</option>')
		        		    .val('');
		        		var arr = data.split(";");
		        		for (var i=0;i<arr.length;i++){
	        		  		if(arr[i]!=""){
	        		  			var arr2 = arr[i].split("*");
	        		  			$("#sectionName").append($("<option></option>").val(arr2[0]).html(arr2[1]));
	        		  		}			        				
		        		}
		        	}else{				        					        		
		        		$('#sectionName')
	        		    .find('option')
	        		    .remove()
	        		    .end()
	        		    .append('<option value="">Please Select</option>')
	        		    .val('');				        		
	        			alert("No Section Found For Selected Class");
			    	}
		        }
			});
			
			$.ajax({
		        url: '/icam/getUserExamsForStandard.html',
		        data: "standard=" + $(this).val(),
		        dataType: 'json',
		        success: function(data) {
		        	var options="<option value=''>Please Select</option>";
		        	if(data != "null" && data !="")
					{
		        		var arr = data.split("*");
						for (var i=0;i<arr.length-1;i++){
							options=options+"<option value='"+arr[i]+"'>"+arr[i]+"</option>";
						}
					}
		        	document.getElementById("examName").innerHTML=options;
		        }
		    });
						
		}
		
	});
	
	
	$("#examName").change(function() {					
		if($(this).val() == "SA1" || $(this).val() == "SA2"){
			document.getElementById("submitGrade").style.visibility='visible';
		}else{
			document.getElementById("submitGrade").style.visibility='collapse';
		}
	
	});	
	
});*/

$(document).ready(function(){
	
	$("#standardName").change(function(){
		
		if($(this).val()!=''){
			
			$.ajax({
		        url: '/icam/getSectionAgainstStandard.html',
		        dataType: 'json',
		        data: {
		        		"standard":$(this).val()
		        },
		        success: function(data) {
		        	if(data != ""){  
		        		document.getElementById("warningbox").style.visibility='collapse';
		        		$('#sectionName')
		        		    .find('option')
		        		    .remove()
		        		    .end()
		        		    .append('<option value="">Please Select</option>')
		        		    .val('');
		        		var arr = data.split(";");
		        		for (var i=0;i<arr.length;i++){
	        		  		if(arr[i]!=""){
	        		  			var arr2 = arr[i].split("*");
	        		  			$("#sectionName").append($("<option></option>").val(arr2[0]).html(arr2[1]));
	        		  		}			        				
		        		}
		        	}else{				        					        		
		        		$('#sectionName')
	        		    .find('option')
	        		    .remove()
	        		    .end()
	        		    .append('<option value="">Please Select</option>')
	        		    .val('');				        		
	        			alert("No Section Found For Selected Class");
			    	}
		        }
			});
			
			/*$.ajax({
		        url: '/icam/getExamForStandard.html',
		        data: "standard=" + $(this).val(),
		        dataType: 'json',
		        success: function(data) {
		        	var options="<option value=''>Please Select</option>";
		        	if(data != "null" && data !="")
					{
		        		var arr = data.split("*");
						for (var i=0;i<arr.length-1;i++){
							var codeName=arr[i].split("~");
							options=options+"<option value='"+codeName[0]+"'>"+codeName[1]+"</option>";
						}
					}
		        	document.getElementById("examName").innerHTML=options;
		        	
		        }
		    });*/
			
			var standardName = $(this).val();
			if(standardName != ""){
				if(standardName == "VI" || standardName == "VII" || standardName == "VIII" || standardName == "IX" || standardName == "X"){
					$('#examName')
        		    .find('option')
        		    .remove()
        		    .end()
        		    .append('<option value="">Please Select</option>')
        		    .val('');						
					$("#examName").append($("<option></option>").val('SA1').html('SA1'));
					$("#examName").append($("<option></option>").val('SA2').html('SA2'));
					$("#examName").append($("<option></option>").val('FA1').html('FA1'));
					$("#examName").append($("<option></option>").val('FA2').html('FA2'));
					$("#examName").append($("<option></option>").val('FA3').html('FA3'));
					$("#examName").append($("<option></option>").val('FA4').html('FA4'));
				}else if(standardName == "XI"){
					$('#examName')
        		    .find('option')
        		    .remove()
        		    .end()
        		    .append('<option value="">Please Select</option>')
        		    .val('');	
					$("#examName").append($("<option></option>").val('Term_1').html('Term 1'));
					$("#examName").append($("<option></option>").val('M1').html('Monthly 1'));
					$("#examName").append($("<option></option>").val('M2').html('Monthly 2'));
					$("#examName").append($("<option></option>").val('PC').html('Pre Centralise'));
					$("#examName").append($("<option></option>").val('IE').html('Internal Examination'));
				}else if( standardName == "XII"){
					$('#examName')
        		    .find('option')
        		    .remove()
        		    .end()
        		    .append('<option value="">Please Select</option>')
        		    .val('');	
					$("#examName").append($("<option></option>").val('Term_1').html('Term 1'));
					$("#examName").append($("<option></option>").val('M1').html('Monthly 1'));
					$("#examName").append($("<option></option>").val('M2').html('Monthly 2'));
					$("#examName").append($("<option></option>").val('PB').html('Pre Board'));
					
				}
			}	
						
		}
		
	});
	
	
	$("#academicYearName").change(
			function() {
				document.getElementById("standardName").value="";
				document.getElementById("sectionName").value="";
				document.getElementById("examName").value="";
				document.getElementById("studentName").innerHTML="";
		});
		$("#sectionName").change(function() {
				document.getElementById("examName").value="";
				document.getElementById("studentName").innerHTML="";
		});

		$("#examName").change(function() {					
			if($(this).val() == "SA1" || $(this).val() == "SA2"){
				document.getElementById("submitGrade").style.visibility='visible';
			}else{
				document.getElementById("submitGrade").style.visibility='collapse';
			}
		
		});	
		
	});


function validateForm() {
	
	if($("#academicYearName").val()==''){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select Academic Year";
		return false;
	}		
	if($("#standardName").val()==''){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select Class name";
		return false;
	}			
	if($("#sectionName").val()==''){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select Stream Name";
		return false;
	}
	if($("#examName").val()==''){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select Section Name";
		return false;
	}	
		
	return true;
}
