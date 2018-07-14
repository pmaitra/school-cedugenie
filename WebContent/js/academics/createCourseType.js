
function validate(){
	
	var reg1 = /^[A-Za-z0-9 ]{1,50}$/;
	
	var courseTypeName = document.getElementById("courseTypeName").value;	
	courseTypeName = courseTypeName.replace(/\s{1,}/g,' ');
	courseTypeName = courseTypeName.trim();
	courseTypeName = courseTypeName.toUpperCase();
	document.getElementById("courseTypeName").value = courseTypeName;
	
	if(courseTypeName == "" || courseTypeName == 'null'){
		alert("Please Enter Course Type Name");
		return false;
	}
	
	if(!courseTypeName.match(reg1)){
		//document.getElementById("warningbox").style.visibility='visible';
		//document.getElementById("warningmsg").innerHTML="Invalid Subject Name. (Alpha-Numeric. Atleat 1 character.)";
		alert("Invalid Course Type Name. (Alpha-Numeric. Atleat 1 character.)");
		return false;
	}
	var oldCourseTypeNames = document.getElementsByName('oldCourseTypeNames');
	for(var i=0; i<oldCourseTypeNames.length;i++){
		if(oldCourseTypeNames[i].value == courseTypeName){
			//document.getElementById("warningbox").style.visibility='visible';
			//document.getElementById("warningmsg").innerHTML="Subject Name Already Exixts";
			alert("Course Type Name Already Exixts");
			return false;
		}
	}
	
	var desc = document.getElementById("desc").value;
	if(desc == "" || desc == 'null'){
		alert("Please Enter Description");
		return false;
	}
	return true;
}

function editSubject(rowId){
	var reg1 = /^[A-Za-z0-9 ]{1,50}$/;
//	var subjectName = document.getElementById('subjectName'+rowId);
	var id = 'courseTypeName'+rowId;
	
	var newName = document.getElementById(id).value;
	newName = newName.replace(/\s{1,}/g,' ');
	newName = newName.trim();
	newName = newName.toUpperCase();
	document.getElementById(id).value = newName;
	
	if(!newName.match(reg1)){
		alert("Invalid Course Type. (Alpha-Numeric. Atleat 1 character.)");
		return false;
	}
	
	var oldCourseTypeNames = document.getElementsByName('oldCourseTypeNames');
	for(var j=0; j<oldCourseTypeNames.length;j++){
		if(oldCourseTypeNames[j].value == newName){
			alert("Programme Name Already Exixts");
			return false;
			}
		}
	return true;
}