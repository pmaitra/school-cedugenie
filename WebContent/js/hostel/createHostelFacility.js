function validate(){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	
	var hostelFacilityName=document.getElementById("hostelFacilityName").value;	
	hostelFacilityName=hostelFacilityName.replace(/\s{1,}/g,' ');
	hostelFacilityName=hostelFacilityName.trim();
	hostelFacilityName=hostelFacilityName.toUpperCase();
	document.getElementById("hostelFacilityName").value=hostelFacilityName;	
	
	if(!hostelFacilityName.match(reg1)){
		alert("Invalid Hostel Facility Name. (Alpha-Numeric. Atleat 1 character.)");
		return false;
	}
	var oldHostelFacilities = document.getElementsByName('oldHostelFacilities');
	for(var i=0; i<oldHostelFacilities.length;i++){
		if(oldHostelFacilities[i].value==hostelFacilityName){
			alert("Facility Name Already Exixts");
			return false;
		}
	}
	
	var hostelCodes = document.getElementsByName('hostel');
	var counter=0;
	for(var i=0; i<hostelCodes.length;i++){
		if(hostelCodes[i].checked)
			counter=counter+1;
	}
	if(counter<=0){
		//document.getElementById("warningbox").style.visibility='visible';
		//document.getElementById("warningmsg").innerHTML="Select atleast hostel.";
		alert("Select atleast hostel.");
		return false;
	}
	
	return true;
}

function makeEditable(rowId){
	rowId=rowId.replace("edit","");
	  
	document.getElementById("facilityName"+rowId).removeAttribute("readonly");
	document.getElementById("ulList"+rowId).removeAttribute("readonly");
	
	
};
function saveData(rowId){
	rowId = rowId.replace("save","");
	//validate();
	var reg1 = /^[A-Za-z0-9 ]{1,50}$/;
	
	var hostelFacilityName = document.getElementById("facilityName"+rowId).value;	
	hostelFacilityName = hostelFacilityName.replace(/\s{1,}/g,' ');
	hostelFacilityName = hostelFacilityName.trim();
	hostelFacilityName = hostelFacilityName.toUpperCase();
	document.getElementById("hostelFacilityName").value = hostelFacilityName;	
	
	if(!hostelFacilityName.match(reg1)){
		alert("Invalid Hostel Facility Name. (Alpha-Numeric. Atleat 1 character.)");
		return false;
	}
	var oldHostelFacilities = document.getElementsByName('oldHostelFacilities');
	for(var i=0; i<oldHostelFacilities.length;i++){
		if(oldHostelFacilities[i].value == hostelFacilityName){
			alert("Facility Name Already Exixts");
			return false;
		}
	}
	var hostelCodes = document.getElementsByName('hostel'+rowId);
	var counter=0;
	for(var i=0; i<hostelCodes.length;i++){
		if(hostelCodes[i].checked)
			counter=counter+1;
	}
	if(counter<=0){
		//document.getElementById("warningbox").style.visibility='visible';
		//document.getElementById("warningmsg").innerHTML="Select atleast hostel.";
		alert("Select atleast hostel.");
		return false;
	}
	document.getElementById("saveId").value = rowId;
	
	//window.location="editHostel.html?saveId="+rowId;
	document.editHostelFacility.submit();
	return true;
};