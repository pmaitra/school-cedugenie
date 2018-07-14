$(document).ready(function(){
	 $("#standard").change(function(){
		var sectionObject=document.getElementById("section");				
		removeOption(sectionObject);
		var subjectObject=document.getElementById("subject");				
		removeOption(subjectObject);
		var examObject=document.getElementById("exam");				
		removeOption(examObject);
		document.getElementById("btnDiv").style.visibility='collapse';
		document.getElementById("infomsgbox1").style.visibility='collapse';
		document.getElementById("infomsg1").innerHTML="";
		document.getElementById("btnDiv").style.visibility='collapse';
		
		$.ajax({
	        url: '/icam/getSectionAgainstStandard.html',
	        dataType: 'json',
	        data: "standard=" + ($(this).val()),
	        success: function(dataDB) {
	        	var options="<option value=''>Select</option>";
	        	if(dataDB != "null" && dataDB !="")
				{
	        		var arr = dataDB.split(";");
					for (var i=0;i<arr.length;i++){   
						var section = arr[i].split("*");
						options=options+"<option value='"+section[0]+"'>"+section[1]+"</option>";
					}
				}
	        	sectionObject.innerHTML=options;
	       }
		});
	});
	 
	 
	 $("#section").change(function(){
		var subjectObject=document.getElementById("subject");				
		removeOption(subjectObject);
		var examObject=document.getElementById("exam");				
		removeOption(examObject);
		document.getElementById("btnDiv").style.visibility='collapse';
		document.getElementById("infomsgbox1").style.visibility='collapse';		
		document.getElementById("infomsg1").innerHTML="";
		document.getElementById("btnDiv").style.visibility='collapse';
		$.ajax({
	        url: '/icam/getSubjectGroupForStandard.html',
	        dataType: 'json',
	        data: "standard=" + ($("#standard").val()),
	        success: function(dataDB) {
	        	var options="<option value=''>Select</option>";
	        	if(dataDB != "null" && dataDB !="")
				{
	        		var arr = dataDB.split("*~*");
					for (var i=0;i<arr.length-1;i++){
						options=options+"<option value='"+arr[i]+"'>"+arr[i]+"</option>";
					}
				}
	        	subjectObject.innerHTML=options;
	       }
		});
	});
	
	$("#subject").change(function(){
		var examObject=document.getElementById("exam");				
		removeOption(examObject);
		document.getElementById("btnDiv").style.visibility='collapse';
		document.getElementById("infomsgbox1").style.visibility='collapse';
		document.getElementById("infomsg1").innerHTML="";
		document.getElementById("btnDiv").style.visibility='collapse';
		
		$.ajax({
	        url: '/icam/getExamForStandard.html',
	        dataType: 'json',
	        data: "standard=" + ($("#standard").val()),
	        success: function(dataDB) {
	        	var options="<option value=''>Select</option>";
	        	if(dataDB != "null" && dataDB !="")
				{
	        		var arr = dataDB.split("*");
					for (var i=0;i<arr.length-1;i++){
						var codeName=arr[i].split("~");
						options=options+"<option value='"+codeName[0]+"'>"+codeName[1]+"</option>";
					}
				}
	        	examObject.innerHTML=options;
	       }
		});
	});
	
	$("#exam").change(function(){
		document.getElementById("btnDiv").style.visibility='collapse';
		document.getElementById("infomsgbox1").style.visibility='collapse';
		document.getElementById("infomsg1").innerHTML="";
				
		if(document.getElementById("standard").value==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Select Standard.";
			return false;
		}
		if(document.getElementById("section").value==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Select Section.";
			return false;
		}
		if(document.getElementById("subject").value==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Select Subject.";
			return false;
		}
		
		document.getElementById("btnDiv").style.visibility='visible';
		
	});
	
	
	
	
});

function removeOption(x)
{
	for(var i=x.length;i>=0;i--)
	{
		x.remove(i);
	}
	x.innerHTML="<option value=''>Select</option>";
}

function downloadExel(){
	if(document.getElementById("standard").value==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Standard.";
		return false;
	}
	if(document.getElementById("section").value==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Section.";
		return false;
	}
	if(document.getElementById("exam").value==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Exam.";
		return false;
	}
	if(document.getElementById('subject').value==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Subject";
		return false;
	}
	
	return true;
}