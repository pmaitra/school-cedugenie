function validate(){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	
	var hostelFacilityName=document.getElementById("hostelFacilityName").value;	
	hostelFacilityName=hostelFacilityName.replace(/\s{1,}/g,' ');
	hostelFacilityName=hostelFacilityName.trim();
	hostelFacilityName=hostelFacilityName.toUpperCase();
	document.getElementById("hostelFacilityName").value=hostelFacilityName;	
	
	if(!hostelFacilityName.match(reg1)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Hostel Facility Name. (Alpha-Numeric. Atleat 1 character.)";
		return false;
	}
	var oldHostelFacilities = document.getElementsByName('oldHostelFacilities');
	var hostelFacilityCode=document.getElementById("hostelFacilityCode").value;	
	for(var i=0; i<oldHostelFacilities.length;i++){
		if(oldHostelFacilities[i].value==hostelFacilityName && hostelFacilityName!=hostelFacilityCode){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Facility Name Already Exixts";
			return false;
		}
	}
	
	var hostelCodes = document.getElementsByName('hostelCodes');
	var counter=0;
	for(var i=0; i<hostelCodes.length;i++){
		if(hostelCodes[i].checked)
			counter=counter+1;
	}
	if(counter<=0){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select atleast hostel to update";
		return false;
	}
	
	return true;
}