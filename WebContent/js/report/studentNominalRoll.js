$(document).ready(function() {

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
});

function validateForm() {
	
	if($("#academicYearName").val()==''){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select Academic Year";
		return false;
	}
	
	var checkedAtLeastOne = false;
	$('input[type="checkbox"]').each(function() {
	    if ($(this).is(":checked")) {
	        checkedAtLeastOne = true;
	    }
	});
	
	if(checkedAtLeastOne==false){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select At Lest One Column";
		return false;
	}	
	return true;
}

function validateFormStaff() {
	
	if($("#resourceTypeName").val()==''){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select Resource Type Name ";
		return false;
	}
	
	var checkedAtLeastOne = false;
	$('input[type="checkbox"]').each(function() {
	    if ($(this).is(":checked")) {
	        checkedAtLeastOne = true;
	    }
	});
	
	if(checkedAtLeastOne==false){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select At Lest One Column";
		return false;
	}	
	return true;
}
