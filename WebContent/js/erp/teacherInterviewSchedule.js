function validateForm(){
	ran=/^[A-Za-z]{3,}$/;
	ran1 = /^[A-Za-z0-9_.-]{1,}$/;
	
	var firstName=document.getElementById("firstName").value;
	firstName=firstName.replace(/\s{1,}/g,'');	
	if(firstName==""){
		document.getElementById("javascriptmsg").style.display = 'block';	
		document.getElementById("warningmsg").innerHTML="Please Enter First Name";
		return false;
	}	
	if(!firstName.match(ran)){
		document.getElementById("javascriptmsg").style.display = 'block';	
		document.getElementById("warningmsg").innerHTML="Invalid First Name";
		return false;
	}	
	var middleName=document.getElementById("middleName").value;
	middleName=middleName.replace(/\s{1,}/g,'');
	if(middleName!=""){
		if(!middleName.match(ran)){
			document.getElementById("javascriptmsg").style.display = 'block';	
			document.getElementById("warningmsg").innerHTML="Invalid Middle Name";
			return false;
		}
	}	
	var lastName=document.getElementById("lastName").value;
	lastName=lastName.replace(/\s{1,}/g,'');
	if(lastName==""){
		document.getElementById("javascriptmsg").style.display = 'block';	
		document.getElementById("warningmsg").innerHTML="Please Enter  Last Name";
		return false;
	}
	if(!lastName.match(ran)){
		document.getElementById("javascriptmsg").style.display = 'block';	
		document.getElementById("warningmsg").innerHTML="Invalid Last Name";
		return false;
	}	
	gndr = document.getElementsByName("resource.gender");
	if ( ( gndr[0].checked == false ) && ( gndr[1].checked == false ))
	{
		document.getElementById("javascriptmsg").style.display = 'block';	
		document.getElementById("warningmsg").innerHTML="Please select Gender";
	return false;
	}	
	if(document.getElementById("dateOfBirth").value==""){
		document.getElementById("javascriptmsg").style.display = 'block';	
		document.getElementById("warningmsg").innerHTML="Please Enter Date Of Birth";
		return false;
	}
	if(document.getElementById("qualification").value==""){
		document.getElementById("javascriptmsg").style.display = 'block';	
		document.getElementById("warningmsg").innerHTML="Please Enter Qualification";
		return false;
	}
	if(document.getElementById("specialization").value==""){
		document.getElementById("javascriptmsg").style.display = 'block';	
		document.getElementById("warningmsg").innerHTML="Please Enter Specialization";
		return false;
	}
	
	var experience=document.getElementById("experience").value;
	experience=experience.replace(/\s{1,}/g,'');
	if(experience!=""){
		if(!experience.match(ran1)){
			document.getElementById("javascriptmsg").style.display = 'block';	
			document.getElementById("warningmsg").innerHTML="Please Enter Valid Experience";
			return false;
		}
	}			
	
	if(document.getElementById("dateOfInterview").value==""){
		document.getElementById("javascriptmsg").style.display = 'block';	
		document.getElementById("warningmsg").innerHTML="Please Enter DateOfInterview";
		return false;
	}
	
	if(document.getElementById("interviewTime").value==""){
		document.getElementById("javascriptmsg").style.display = 'block';	
		document.getElementById("warningmsg").innerHTML="Please Enter Interview Time";
		return false;
	}
	
	if(document.getElementById("teachingLevelName").value==""){
		document.getElementById("javascriptmsg").style.display = 'block';	
		document.getElementById("warningmsg").innerHTML="Please Select Teaching Level";
		return false;
	}	
	
	if(document.getElementById("roomNumber").value==""){
		document.getElementById("javascriptmsg").style.display = 'block';	
		document.getElementById("warningmsg").innerHTML="Please Enter Valid RoomNo";
		return false;
	}	
		
    if(document.getElementById("examinerName").value == ""){
    	document.getElementById("javascriptmsg").style.display = 'block';	
		document.getElementById("warningmsg").innerHTML="Please Select atleast One Examiner Name";
        return false;
    }
	return true;
}


function makeTeacherInterviewScheduleEditable(){
	var i;	
	var input = document.getElementsByTagName("input");	
	for(i=0;i<input.length;i++){
		input[i].removeAttribute("readonly");
	}
		
	document.getElementById("employeeCode").setAttribute("readonly", "readonly");		
	document.getElementById("genderM").removeAttribute("disabled");	
	document.getElementById("genderF").removeAttribute("disabled");
	document.getElementById("teachingLevelName").removeAttribute("disabled");	
	document.getElementById("submitForm").removeAttribute('disabled');	
	document.getElementById("examinerName").removeAttribute('disabled');
	/*var inputChkBox = document.getElementsByName("examinerName");	
	for(i=0;i<inputChkBox.length;i++)
	{
		inputChkBox[i].removeAttribute("disabled");
	}*/			    
}


