$(document).ready(function() {
	$("#designationType").change(function(){
    	$.ajax({
	        url: '/icam/getDesignationBasedOnDesignationType.html',
	        dataType: 'json',
	        data: "designationTypeCode=" + ($(this).val()),
	        success: function(dataDB) {
	        	var x=document.getElementById("designation");
	        	if(dataDB != null)
				{
	        		for(var i=x.length;i>0;i--){
		        		x.remove(i);
		        	}
	        		var arr = dataDB.split("@");
	        			
					for (var i=0;i<arr.length;i++){   
						var designationDetails = arr[i].split(",");
	                    $("#designation").append($("<option></option>").val(designationDetails[0]).html(designationDetails[1]));
					}
				}
	        	if(dataDB == null){
	        		for(var j=x.length;j>0;j--){
		        		x.remove(j);
		        	}
	        	}
	       }
		});
    });
	
	$("#designation").change(function(){
    	$.ajax({
	        url: '/icam/getLevelBasedOnDesignation.html',
	        dataType: 'json',
	        data: "designationCode=" + ($(this).val()),
	        success: function(dataDB) {	
	        	var x=document.getElementById("level");
	        	if(dataDB != null)
				{
	        		for(var i=x.length;i>0;i--){
		        		x.remove(i);
		        	}
	        		var arr = dataDB.split("@");
	        			
					for (var i=0;i<arr.length;i++){   
						var level = arr[i].split(",");
	                    $("#level").append($("<option></option>").val(level[0]).html(level[1]));
					}
				}
	        	if(dataDB == null){
	        		for(var j=x.length;j>0;j--){
		        		x.remove(j);
		        	}
	        	}
	       }
		});
    });
	
	
	
	$(document).ready( function() { 	
		$("#salaryTemplateName").bind('keyup blur',function(){
			var salaryTemplateName=$("#salaryTemplateName").val();	
			 $.ajax({
		    url: '/icam/getSalaryTemplateName.html',
		    dataType: 'json',
		     data: "salaryTemplateName=" +salaryTemplateName,
		     success: function(data) {
			    	if(data != null && data!= "" ){
			    		document.getElementById("warningbox").style.display='block';
			 			$("#warningbox").text("Template Name already exists");	 			
			    	  	$('#submitButton').attr("disabled",true);
			    	  	return false;
			    	}else{
			    		$('#submitButton').attr("disabled",false);
			    		document.getElementById("warningbox").style.display='none';
			    	}
		    		}  
				});			
			});	
		
		$("#level").bind('keyup blur',function(){
				$.ajax({
			        url: '/icam/getTemplateForDesignationTypeAndDesignationAndLevel.html',
			        dataType: 'json',
			        data:"designationType=" + $("#designationType").val() + "&designation=" + $("#designation").val() + "&level="+ $("#level").val(),
			        success: function(dataDB) {	
			        	if(dataDB != null && dataDB!= "" ){
				    		document.getElementById("warningbox").style.display='block';
				 			$("#warningbox").text("Template For This Combination already exsists!! ");	 			
				    	  	$('#submitButton').attr("disabled",true);
				    	  	return false;
				    	}else{
				    		$('#submitButton').attr("disabled",false);
				    		document.getElementById("warningbox").style.display='none';
				    	}
			        }
				});
			});
		});	
	});

function validateTemplate(){
	var salaryTemplateName=document.getElementById("salaryTemplateName").value.replace(/\s{1,}/g,'');
	var designationType=document.getElementById("designationType").value;
	var designation=document.getElementById("designation").value;
	var level=document.getElementById("level").value;
	
	var salaryBreakUpCode = document.getElementsByName("salaryBreakUpCode");



	 if(designationType==""){
//		 document.getElementById("warningbox").style.visibility='visible';
//		 document.getElementById("warningbox").innerHTML="Select Designation Type";
		 alert("Select Designation Type");
		 return false;
	 }
	 if(designation==""){
//		 document.getElementById("warningbox").style.visibility='visible';
//		 document.getElementById("warningbox").innerHTML="Select Designation";
		 alert("Select Designation");
		 return false;
	 }
	 if(level==""){
//		 document.getElementById("warningbox").style.visibility='visible';
//		 document.getElementById("warningbox").innerHTML="Select Level";
		 alert("Select Level");
		 return false;
	 }
	 
	 if(salaryTemplateName=="" || salaryTemplateName.length==0){
//		 document.getElementById("warningbox").style.visibility='visible';
//		 document.getElementById("warningbox").innerHTML="Enter Template Name";
		 alert("Enter Template Name");
		 return false;
	 }
	 
	 var counter=0;
	 for(var i=0;i<salaryBreakUpCode.length;i++){
		 if(salaryBreakUpCode[i].checked==true){			 
			 counter++;
		 }
	 }
	 if(counter<=0){
//		 document.getElementById("warningbox").style.visibility='visible';
//		 document.getElementById("warningbox").innerHTML="Select Pay Heads";
		 alert("Select Pay Heads");
		 return false;
	 }
	 return true;
}