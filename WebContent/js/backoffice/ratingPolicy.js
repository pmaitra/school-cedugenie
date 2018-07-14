var retval = null;
$(document).ready(function(){
	
	$("#submitButton").click(function(){
		$(".textfield1").each(function(){
			var valNumeric = parseInt($(this).val());
			if(isNaN(valNumeric)){
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("waringmsg").innerHTML="Please Enter the input in neumerical format !";
				retval = false;
				return false;
			} 
			else{
				document.getElementById("warningbox").style.visibility='collapse';
				document.getElementById("waringmsg").innerHTML="";
				retval = true;
				return true;
			}
		});
		return retval;
	});
});