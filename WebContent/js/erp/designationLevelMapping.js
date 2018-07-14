function validate(){
	var designationCode=document.getElementById("designationCode").value;
	var level = document.getElementsByName('designationLevel');
	if(designationCode==""){		
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Select A Designation Name";
		return false;
	}
	
	var p=0;
	for(var i=0; i<level.length;i++){
		if(level[i].checked)
		p=p+1;
	}
	if(p<=0){
		document.getElementById("javascriptmsg").style.display = 'block';
		document.getElementById("javascriptmsg").innerHTML="Please select atleast one Designation Level";
		valid = false;
		return false;
	}
	return true;
}
