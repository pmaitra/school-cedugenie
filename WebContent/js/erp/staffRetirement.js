
$(document).ready(function(){
$("#staffUserId").change(
		function() {
			var staffUserId=document.getElementById("staffUserId").value;	
			if(staffUserId== "null" || staffUserId==''){
				return;
			}
			$.ajax({
		        url: '/cedugenie/getStaffDetails.html',
		        data: "staffUserId=" +($(this).val()),
		        success: function(data) {
		        	var sm=data.split("*");
		        	document.getElementById("staffCode").value=sm[0];
		        	document.getElementById("staffName").value=sm[1];
		        	document.getElementById("employeeType").value=sm[2];
		        	document.getElementById("designation").value=sm[3];
		        	document.getElementById("jobType").value=sm[4];
		        	document.getElementById("doj").value=sm[5];
		        	document.getElementById("dor").value=sm[6];
		        }
		}); 
	});
});


$(document).ready(function() {    
    $('#dateOfRetirement').Zebra_DatePicker();    
    $('#dateOfRetirement').Zebra_DatePicker({
    	  format: 'd/m/Y',
    	  direction: true
    	});  
 });



$(document).ready(function() {    
    $('#actualDateOfRetirement').Zebra_DatePicker();    
    $('#actualDateOfRetirement').Zebra_DatePicker({
    	  format: 'd/m/Y',
    	  direction: true
    	});  
 });
	
function staffRetirementValidation(){
	if(document.getElementById("staffUserId").value.replace(/\s{1,}/g,'').length ==0){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML="Please select User Id";
		alert("Please select User Id");
		return false;
	}
	if(document.getElementById("modeOfRetirement").value.replace(/\s{1,}/g,'').length ==0){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML="Please select Mode Of Retirement";
		alert("Please select Mode Of Retirement");
		return false;
	}
	if(document.getElementById("dateOfRetirement").value.replace(/\s{1,}/g,'').length ==0){
//		document.getElementById("warningbox").style.visibility='visible';
//		document.getElementById("warningbox").innerHTML="Please select Actual Date Of Retirement";
		alert("Please select Actual Date Of Retirement");
		return false;
	}
	return true;
}	




