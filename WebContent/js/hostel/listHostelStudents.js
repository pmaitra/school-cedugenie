window.onload=function(){
	hideAll();
};

function showStudents(hostel){
	hideAll();
	var hostelName = hostel.value;
	if(hostelName!=""){
		document.getElementById("studentTable").style.visibility = "visible";
		document.getElementById(hostelName).style.visibility = "visible";
	}
}

function hideAll(){
	document.getElementById("studentTable").style.visibility = "collapse";
	var studentTables = document.getElementsByClassName("studentsTables");
	for(var i=0;i<studentTables.length;i++){
		studentTables[i].style.visibility = "collapse";
	}
}