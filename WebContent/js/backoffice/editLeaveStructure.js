$(document).ready(function(){
	$('#leaveValid').Zebra_DatePicker();
	$('#leaveValid').Zebra_DatePicker({
		format: 'd/m/Y'
	});
});
function validate(){
	var leaveDuration= document.getElementById("leaveDuration"); 
	var leaveEncashment= document.getElementById("leaveEncashment"); 
	var leaveLimit= document.getElementById("leaveLimit"); 
	var leaveValid= document.getElementById("leaveValid");
	
		if(leaveDuration.value == ""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Enter Duration.";
			return false;
			}
		if(leaveEncashment.value == "select"){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Select Encashment option.";
			return false;
			}
		if(leaveLimit.value == ""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Enter the leavelimit.";
			return false;
			}
		if(leaveValid.value == ""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Enter the leaveValid.";
			return false;
			}
    	
}