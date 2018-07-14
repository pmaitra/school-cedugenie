function validate(){		
	var p=0;
	var  regPositiveNum = /^[0-9]{1,}$/;
	var valid = true;
	var moduleName = document.getElementsByName("approverGroupName");			
	for(var i=0; i<moduleName.length;i++){				
		if(moduleName[i].checked)
		p=p+1;
	}
	if(p<=0){
		//document.getElementById("warningbox").style.visibility='visible';
		alert("Please Select One Module Name");
		valid = false;
		return false;
	}
	var status = document.getElementById("status").value;
	if(status ==""){
		alert("Please Enter Status. ");
		return false;
	}
	var ticketMaxDays = document.getElementById("ticketMaxDays").value;
		
	if(ticketMaxDays ==""){
		alert("Please Enter Maximum Days Granted. ");
		return false;
	}
	if (ticketMaxDays != '') {
		if (!ticketMaxDays.match(regPositiveNum)) {
			alert("Please Enter Numeric Value");
			return false;
		}
	}
	return true;
}