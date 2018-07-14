function addSubject(){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	
	var subjectName=document.getElementById("subjectName").value;	
	subjectName=subjectName.replace(/\s{1,}/g,' ');
	subjectName=subjectName.trim();
	subjectName=subjectName.toUpperCase();
	document.getElementById("subjectName").value=subjectName;	
	
	if(subjectName == "" || subjectName == 'null'){
		alert("Please Enter Subject Name");
		return false;
	}
	
	if(!subjectName.match(reg1)){
		//document.getElementById("warningbox").style.visibility='visible';
		//document.getElementById("warningmsg").innerHTML="Invalid Subject Name. (Alpha-Numeric. Atleat 1 character.)";
		alert("Invalid Subject Name. (Alpha-Numeric. Atleat 1 character.)");
		return false;
	}
	var oldSubjectNames = document.getElementsByName('oldSubjectNames');
	for(var i=0; i<oldSubjectNames.length;i++){
		if(oldSubjectNames[i].value==subjectName){
			//document.getElementById("warningbox").style.visibility='visible';
			//document.getElementById("warningmsg").innerHTML="Subject Name Already Exixts";
			alert("Subject Name Already Exixts");
			return false;
		}
	}
	//document.subjectForm.method="Post";
	//document.subjectForm.action="addSubject.html";
	//document.subjectForm.submit();
	var subjectGroup = document.getElementById("subjectGroup").value;
	if(subjectGroup == "" || subjectGroup == 'null'){
		alert("Please Enter Subject Group Name");
		return false;
	}
	
	return true;
}
function editSubject(rowId){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
//	var subjectName = document.getElementById('subjectName'+rowId);
	var id = 'subjectName'+rowId;
	var newName=document.getElementById(id).value;
	newName=newName.replace(/\s{1,}/g,' ');
	newName=newName.trim();
	newName=newName.toUpperCase();
	document.getElementById(id).value=newName;
	
	if(!newName.match(reg1)){
		alert("Invalid Subject Name. (Alpha-Numeric. Atleat 1 character.)");
		return false;
	}
	
		var oldSubjectNames = document.getElementsByName('oldSubjectNames');
		for(var j=0; j<oldSubjectNames.length;j++){
						if(oldSubjectNames[j].value == newName){
							alert("Subject Name Already Exixts");
							return false;
						}
				}
	return true;
}