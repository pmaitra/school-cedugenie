$(document).ready(function(){	
	 $("#studentName").change(function(){
		 var sel=document.getElementById("studentName");
		 document.getElementById("rollNumber").value=sel.options[sel.selectedIndex].text;
		 document.getElementById("name").value=sel.value;
		 document.getElementById("nameSpan").innerHTML=sel.value;
		 
		 
		 
		 var oldSubjectsDiv=document.getElementById("oldSubjectsDiv");
     	 var oldSubjects="";
     	 oldSubjectsDiv.innerHTML=oldSubjects;
		 uncheckSubjects();
		 if(($(this).val())!=""){
			$.ajax({
		        url: '/icam/getSubjectsStudiedByStudent.html',
		        dataType: 'json',
		        data: "rollNumber=" + ($("#rollNumber").val()),
		        success: function(data) {		        	
		        	var subjects = data.split("*~*");		        	
		        	for (var i=0;i<subjects.length-1;i++){
						document.getElementById(subjects[i]).checked=true;
						oldSubjects=oldSubjects+"<input type='hidden' name='oldSubjects' value='"+subjects[i]+"'>";
					}
					oldSubjectsDiv.innerHTML=oldSubjects;
		       },
		       error:function(data) {
		    	   alert("Error");
		       }
			});
		 }
		});
	

});
function uncheckSubjects(){
	var subjects=document.getElementsByName("subjects");
	for (var i=0;i<subjects.length-1;i++){
		subjects[i].checked=false;
	}
}
function saveStudentSubjectMapping(){
	var rollNumber=document.getElementById("rollNumber").value;
	if(rollNumber==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select A Student.";
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