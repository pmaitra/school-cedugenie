/*$(document).ready(function(){
});*/
	$("#standardName").change(function(){
		if($(this).val()!=''){
			$.ajax({
		        url: '/cedugenie/getSectionAgainstStandard.html',
		        dataType: 'json',
		        data:"standard="+$(this).val(),
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
			
			/*var standardName = $(this).val();*/
			if($(this).val()!= ""){
				$.ajax({
			        url: '/cedugenie/getExamsForStandard.html',
			        dataType: 'json',
			        data: "standard=" + $(this).val(),
			        success: function(dataDB) {
			        	var options="<option value=''>Select..</option>";
			        	if(dataDB != "null" && dataDB !=""){
			        		var arr = dataDB.split("*");
							for (var i=0;i<arr.length-1;i++){
								var codeName=arr[i].split("~");
								options=options+"<option value='"+codeName[0]+"'>"+codeName[1]+"</option>";
							}
						}
			        	document.getElementById("examName").innerHTML=options;
			       }
				});
				/*if(standardName == "VI" || standardName == "VII" || standardName == "VIII"){
					$('#examName')
	    		    .find('option')
	    		    .remove()
	    		    .end()
	    		    .append('<option value="">Please Select</option>')
	    		    .val('');						
					$("#examName").append($("<option></option>").val('Term1').html('TERM 1'));
					$("#examName").append($("<option></option>").val('Term2').html('TERM 2'));
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
					$("#examName").append($("<option></option>").val('Centralise').html('Centralise'));
					
				}
				else if( standardName == "XII"){
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
				}else if(standardName == "IX" || standardName == "X"){
					$('#examName')
	    		    .find('option')
	    		    .remove()
	    		    .end()
	    		    .append('<option value="">Please Select</option>')
	    		    .val('');	
					$("#examName").append($("<option></option>").val('PT1').html('Periodic Test 1'));
					$("#examName").append($("<option></option>").val('PT2').html('Periodic Test 2'));
					$("#examName").append($("<option></option>").val('PT3').html('Periodic Test 3'));
					$("#examName").append($("<option></option>").val('AnnualExam1').html('ANNUAL EXAM'));
				}*/
			}									
		}
	});


	$("#academicYearName").change(function() {
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
		if($('#sectionName').val() == "" || $("#standardName").val()== ""){
			return;
		}
		$.ajax({
	        url: '/cedugenie/getStudentsAgainstStandardAndSectionForNewReport.html',
	        dataType: 'json',
	        data: {
	    		"standard":$("#standardName").val(),
	    		"section":$('#sectionName').val()
	        },
	        success: function(data) {
	        	if(data != ""){  
	        		document.getElementById("warningbox").style.visibility='collapse';
	        		$('#studentName')
	        		    .find('option')
	        		    .remove()
	        		    .end()					        		    
	        		    .append('<option value="ALL">ALL Students</option>')
	        		    .val('');
	        		var arr = data.split(";");
	        		for (var i=0;i<arr.length;i++){
	    		  		if(arr[i]!=""){
	    		  			var arr2 = arr[i].split("*");
	    		  			$("#studentName").append($("<option></option>").val(arr2[0]).html(arr2[0]+" - "+arr2[1]));
	    		  		}			        				
	    			}
	        	}else{				        					        		
	        		$('#studentName')
	    		    .find('option')
	    		    .remove()
	    		    .end()					        		   
	    		    .append('<option value="ALL">All Students</option>')
	    		    .val('');
	    			alert("No Student Found For Selected Class & Section");
	    		}
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
	if($("#studentName").val()==''){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select Course Name";
		return false;
	}		
	
	
	if($("#studentName").val()=='ALL'){
		var all_values = [];
		$('#studentName option').map( function() { 
		    if (this.value.length) { 
		    	if(this.value!='ALL'){
		    		all_values.push(this.value);
		    	}		       
		    }
		});
		$("#studentName").val(all_values);
	}	
	return true;
}