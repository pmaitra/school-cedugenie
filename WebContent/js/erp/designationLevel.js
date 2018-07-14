function validateDesignationLevelForm(){
	var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;
	var successmsg=document.getElementById("successmsg");
	if(null != successmsg){
		successmsg.innerHTML = "";
		document.getElementById("successbox").style.visibility='collapse';
	}				
	// Validate DesignationType Name
	var obj = document.getElementById("designationLevelName");	
	var designationLevelName=obj.value;
	designationLevelName=designationLevelName.replace(/\s{1,}/g,' ');
	designationLevelName=designationLevelName.trim();
	designationLevelName=designationLevelName.toUpperCase();
	obj.value=designationLevelName;
	if (designationLevelName == "") {			
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Please Enter Designation Level";		
		return false;
	}
	
	if (designationLevelName != '') {
		if (!designationLevelName.match(alphaNumeric)) {			
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Invalid Designation Level ";
			return false;
		}
	}
	
		var oldDesignationLevelNames = document.getElementsByName('oldDesignationLevelNamesforDuplicateChecking');
		for(var i=0; i<oldDesignationLevelNames.length;i++){
			if(oldDesignationLevelNames[i].value==designationLevelName){
				document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "Designation Level Already Exists";				
				return false;
			}
		}		
}

function validateEditDesignationLevelForm(rowID ){			
	var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;			
	var val="";
	var obj = document.getElementById(rowID);		
	val=obj.value;		
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;
	
	if (val == "") {					
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Please Enter Designation Level";
		return false;
	}else{					
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "";
	}
			
	if(!val.match(alphaNumeric)){
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Invalid Designation Level";				
		return false;
	}else{
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "";
	}		
	
	var desigTypeList = document.getElementsByName("oldDesignationLevelNamesforDuplicateChecking");		
	for(var i=0; i<desigTypeList.length; i++){
		var oldVal=desigTypeList[i].value;
		if(oldVal==val){
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Duplicate Designation Level";
			return false;
		}
	}		
	return true;
	
}
	