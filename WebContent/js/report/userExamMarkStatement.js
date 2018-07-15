$(document).ready(function(){
	
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
		        url: '/cedugenie/getSectionAgainstStandard.html',
		        dataType: 'json',
		        data: {
		        		"standard":$(this).val()
		        },
		        success: function(data) {
		        	if(data != ""){  
		        		//document.getElementById("warningbox").style.visibility='collapse';
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
		        url: '/cedugenie/getUserExamsForStandard.html',
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
		if($('#sectionName').val() == "" || $("#standardName").val()== ""){
			return;
		}
		$.ajax({
	        url: '/cedugenie/getStudentsAgainstStandardAndSection.html',
	        dataType: 'json',
	        data: {
        		"standard":$("#standardName").val(),
        		"section":$('#sectionName').val()
	        },
	        success: function(data) {
	        	if(data != ""){  
	        		//document.getElementById("warningbox").style.visibility='collapse';
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

