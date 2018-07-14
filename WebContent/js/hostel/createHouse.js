function validateHouseCreation(){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	var houseName = document.getElementById("houseName").value;	
	houseName = houseName.replace(/\s{1,}/g,' ');
	houseName = houseName.trim();
	houseName = houseName.toUpperCase();
	
	document.getElementById("houseName").value=houseName;	
	
	if(!houseName.match(reg1)){
		document.getElementById("warningBox").style.display = 'block';
		document.getElementById("warningMsg").innerHTML = "Invalid House Name. (Alpha-Numeric. Atleat 1 character.)";
		return false;
	}
	var oldHouseNames = document.getElementsByName('oldHouseNames');
	for(var i=0; i<oldHouseNames.length;i++){
		if(oldHouseNames[i].value==houseName){
			document.getElementById("warningBox").style.display = 'block';
			document.getElementById("warningMsg").innerHTML = "House Name Already Exixts";
			return false;
		}
	}	
	return true;
}