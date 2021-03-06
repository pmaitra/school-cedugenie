$(document).ready(function(){	
	$("#standard").change(
	function() {
		var oldSubjectsDiv=document.getElementById("oldSubjectsDiv");
		oldSubjectsDiv.innerHTML="";
		var checkBoxes=getElementsByClassName("subjectsCheckBox");
		for(var subjectBox=0;subjectBox<checkBoxes.length;subjectBox++)
			checkBoxes[subjectBox].checked=false;
		if($(this).val()!=''){
			$.ajax({
		        url: '/cedugenie/getSubjectsForStandard.html',
		        data: "standard=" + ($(this).val()),
		        dataType: 'json',
		        success: function(data) {
		        	if(data!=null && data!=""){
		        		var oldSubjectBoxes="";		        		
		        		var oldSubjectsArray=data.split("*~*");
		        		for(var oldSubjects=0;oldSubjects<oldSubjectsArray.length-1;oldSubjects++){
		        			document.getElementById(oldSubjectsArray[oldSubjects]).checked=true;
		        			oldSubjectBoxes=oldSubjectBoxes+"<input type='hidden' name='oldSubjects' value='"+oldSubjectsArray[oldSubjects]+"'>";
		        		}
		        		oldSubjectsDiv.innerHTML=oldSubjectBoxes;
		        	}
		        }
		    });
		}
	});
});

function saveClassSubjectMapping(){
	var standard=document.getElementById("standard").value;
	if(standard==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Standard.";
		return false;
	}
	var subjects=document.getElementsByName('subjects');
	var counter=0;
	for(var i=0; i<subjects.length;i++){
		if(subjects[i].checked)
			counter=counter+1;
	}
	if(counter<=0){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select atleast one subject to update";
		return false;
	}
	return true;
}