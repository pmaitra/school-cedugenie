
$(document).ready(function(){	
$("#emailId").bind('keyup blur',function(){
	var emailId=$("#emailId").val();	
	$.ajax({
	     url: '/cedugenie/serverSideValidationOfVendorEmailId.html',
	     dataType: 'json',
	     data: "emailId=" +emailId,
	     success: function(data) {
	    	if(data != null && data!= ""){
	    	document.getElementById("warningbox").style.display='block';
	 			$("#warningbox").text("EmailId  already exists");
	    	  	return false;
	    		}	
	    	else{	    		
	    		document.getElementById("warningbox").style.display='none';
	    		}
	    	}  
		});		
	   
	 	
});

});


