function addExam(){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	
	var examName=document.getElementById("examName").value;	
	examName=examName.replace(/\s{1,}/g,' ');
	examName=examName.trim();
	examName=examName.toUpperCase();
	document.getElementById("examName").value=examName;	
	
	if(!examName.match(reg1)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Exam Name. (Alpha-Numeric. Atleat 1 character.)";
		return false;
	}
	var oldExamNames = document.getElementsByName('oldExamNames');
	for(var i=0; i<oldExamNames.length;i++){
		if(oldExamNames[i].value==examName){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Exam Name Already Exixts";
			return false;
		}
	}
	
	document.examForm.method="Post";
	document.examForm.action="addUserDefinedExams.html";
	document.examForm.submit();
	return true;
}
function editExam(){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	var examCodes = document.getElementsByName('examCodes');
	var counter=0;
	for(var i=0; i<examCodes.length;i++){
		if(examCodes[i].checked)
			counter=counter+1;
	}
	if(counter<=0){
		document.getElementById("warningbox1").style.visibility='visible';
		document.getElementById("warningmsg1").innerHTML="Select atleast one exam to update";
		return false;
	}else{
		var oldExamNames = document.getElementsByName('oldExamNames');
		for(var j=0; j<oldExamNames.length;j++){
			for(var i=0; i<examCodes.length;i++){
				if(examCodes[i].checked){
					var id=(examCodes[i].id).replace("examCode","examName");
					var newName=document.getElementById(id).value;
					newName=newName.replace(/\s{1,}/g,' ');
					newName=newName.trim();
					newName=newName.toUpperCase();
					document.getElementById(id).value=newName;
					
					if(!newName.match(reg1)){
						document.getElementById("warningbox1").style.visibility='visible';
						document.getElementById("warningmsg1").innerHTML="Invalid Exam Name. (Alpha-Numeric. Atleat 1 character.)";
						return false;
					}					
					
					if(oldExamNames[j].value==newName && i!=j){
						document.getElementById("warningbox1").style.visibility='visible';
						document.getElementById("warningmsg1").innerHTML="Exam Name Already Exixts";
						return false;
					}
				}
			}
			
			
			
		}
	}
	document.examForm.method="Post";
	document.examForm.action="editUserDefinedExams.html";
	document.examForm.submit();
	return true;
}