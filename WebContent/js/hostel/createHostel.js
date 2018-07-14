function addHostel(){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	
	var hostelName=document.getElementById("hostelName").value;	
	hostelName=hostelName.replace(/\s{1,}/g,' ');
	hostelName=hostelName.trim();
	hostelName=hostelName.toUpperCase();
	document.getElementById("hostelName").value=hostelName;	
	
	if(!hostelName.match(reg1)){
		alert("Invalid Residence Name. (Alpha-Numeric. Atleat 1 character.)");
		return false;
	}
	var oldHostelNames = document.getElementsByName('oldHostelNames');
	for(var i=0; i<oldHostelNames.length;i++){
		if(oldHostelNames[i].value==hostelName){
			alert("Residence Name Already Exixts");
			return false;
		}
	}
	var desc = document.getElementById("desc").value;	
	desc = desc.replace(/\s{1,}/g,' ');
	desc = desc.trim();
	hostelName = desc.toUpperCase();
	document.getElementById("desc").value = desc;	
	
	if(!desc.match(reg1)){
		alert("Invalid Residence Name. (Alpha-Numeric. Atleat 1 character.)");
		return false;
	}
	
	return true;
}
function editHostel(){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	var hostelCodes = document.getElementsByName('hostelCodes');
	var counter=0;
	for(var i=0; i<hostelCodes.length;i++){
		if(hostelCodes[i].checked)
			counter=counter+1;
	}
	if(counter<=0){
		document.getElementById("warningbox1").style.visibility='visible';
		document.getElementById("warningmsg1").innerHTML="Select atleast one Residence to update";
		return false;
	}else{
		var oldHostelNames = document.getElementsByName('oldHostelNames');
		for(var j=0; j<oldHostelNames.length;j++){
			for(var i=0; i<hostelCodes.length;i++){
				if(hostelCodes[i].checked){
					var id=(hostelCodes[i].id).replace("hostelCode","hostelName");
					var newName=document.getElementById(id).value;
					newName=newName.replace(/\s{1,}/g,' ');
					newName=newName.trim();
					newName=newName.toUpperCase();
					document.getElementById(id).value=newName;	
					
					if(!newName.match(reg1)){
						document.getElementById("warningbox1").style.visibility='visible';
						document.getElementById("warningmsg1").innerHTML="Invalid Residence Name. (Alpha-Numeric. Atleat 1 character.)";
						return false;
					}
				}
			}
		}
		alert(1);
		var newName = [];
		var lengthCount=0;
		for(var i=0; i<hostelCodes.length;i++){
			var id=(hostelCodes[i].id).replace("hostelCode","hostelName");
			newName.push(document.getElementById(id).value);
			lengthCount++;
		}
		newName = newName.sort();
		for (var i = 0; i < lengthCount - 1; i++) {
			if (newName[i + 1] == newName[i]) {
				document.getElementById("warningbox1").style.visibility='visible';
				document.getElementById("warningmsg1").innerHTML="Dublicate Name Already Exixts";
				return false;
			}
		}
		
	}
	document.hostelForm.method="Post";
	document.hostelForm.action="editHostel.html";
	document.hostelForm.submit();
	return true;
}