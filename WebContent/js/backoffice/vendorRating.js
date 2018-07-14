var retval = null;
$(document).ready(function(){
	
	$("#submit").click(function(){
		$(".textfield").each(function(){
			var valNumeric = $(this).val();
			if(isNaN(valNumeric)){
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML="Please Enter the input in neumerical format !";
				retval = false;
				return false;
			} 
			else{
				document.getElementById("warningbox").style.visibility='collapse';
				document.getElementById("warningmsg").innerHTML="";
				retval = true;
				return true;
			}
		});
		return retval;
	});
$("#vendorTypeSelect").change(function(){	
	if($(this).val() == ""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Proper value!";
		document.getElementById("policyDetails").style.visibility='collapse';
	}
	if($(this).val() != ""){
	$.ajax({
        url: '/cedugenie/getVendorPloicyToShow.html',
        data: "vendorTypeSelect=" + ($(this).val()),
        dataType: 'json',
        success: function(data) {        		
	        	document.getElementById("warningbox").style.visibility='collapse';
	    		document.getElementById("warningmsg").innerHTML="";
        		document.getElementById("policyDetails").style.visibility='visible';
        		var splitedData = data.split("^");
        			
        			$("#maxSupplyDay").val(splitedData[0]);
        			$("#maxNoDeffects").val(splitedData[1]);
	        		
        	}

	});
	}
});
});