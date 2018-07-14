$("#edit").click(function(){
	document.getElementById("clearButton").removeAttribute('disabled');
	document.getElementById("submitButton").removeAttribute('disabled');
	
	var academicYearCode = document.getElementsByName("academicYearCode");
	
	for(var i=0; i<academicYearCode.length; i++){
		if(academicYearCode[i].checked == true){
			var code = academicYearCode[i].id;
			document.getElementById('academicYearName'+code).removeAttribute("disabled");
		}
	}
});

var fyID;
function validateEdit(){
	var academicYearCode=document.getElementsByName("financialYearCode");	
	for(var i=0; i<academicYearCode.length; i++){
		if(academicYearCode[i].checked == true){
			fyID = academicYearCode[i].id;				
		}
	}
	var academicYearName = document.getElementById("academicYearName"+fyID).value;
	if(academicYearName == ""){
		document.getElementById("warningbox1").style.display = 'block';
		document.getElementById("warningbox1").innerHTML = 'Please provide year name';
		return false;
	}
	var academicYearClass = document.getElementsByName("financialYearNames");
	for(var i=0;varf<academicYearClass.length;varf++){
		if(academicYearClass[i].value == academicYearName){
			document.getElementById("warningbox1").style.display = 'block';
			document.getElementById("warningbox1").innerHTML = 'Academic Year Name Already Exists';
			return false;
		}
	}
	return true;
}


function validateFrom(){
	var academicYearName = document.getElementById("academicYearName").value;	
	var sessionStartDate = document.getElementById("sessionStartDate").value;
	var sessionEndDate = document.getElementById("sessionEndDate").value;
	if(sessionStartDate > sessionEndDate){
		document.getElementById("warningbox1").style.display = 'block';
		document.getElementById("warningbox1").innerHTML = 'Session End Date can not before Session Start Date';
		return false;
	}
	var academicYearClass = document.getElementsByName("academicYearNames");
	for(var i=0; i<academicYearClass.length; i++){
		if(academicYearClass[i].value == academicYearName){
			document.getElementById("warningbox1").style.display = 'block';
			document.getElementById("warningbox1").innerHTML = 'Academic Year Name Already Exists';
			return false;
			}
		}
	return true;
}