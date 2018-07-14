
function saveStudentSubjectMapping(){
	var subjects=document.getElementsByName('subjects');
	var counter=0;
	for(var i=0; i<subjects.length;i++){
		if(subjects[i].checked)
			counter=counter+1;
	}
	if(counter<=0){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Atleast One Subject.";
		return false;
	}
	
	var rollNumber=document.getElementsByName('rollNumber');
	counter=0;
	for(var i=0; i<rollNumber.length;i++){
		if(rollNumber[i].checked)
			counter=counter+1;
	}
	if(counter<=0){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Atleast One Student.";
		return false;
	}
	
	
	return true;
}