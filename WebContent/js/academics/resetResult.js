$(document).ready(function(){	
	$("#standard").change(function(){
		
			$.ajax({
		        url: '/icam/getSubjectsForStandardQP.html',
		        data: "standard=" + ($(this).val()),
		        dataType: 'json',
		        success: function(data) {
	        		var options = "<option value=''>Select</option>";
		        	if(data!=null && data!=""){
		        		data=data.split("*~*");
		        		var subjectObject = document.getElementById("subject");
		        		for (var i=0;i<data.length-1;i++){
							options = options+"<option value='"+data[i]+"'>"+data[i]+"</option>";
						}
		        	}
		        	subjectObject.innerHTML = options;
		        }
		     });
			
			$.ajax({
		        url: '/icam/getExamForStandard.html',
		        dataType: 'json',
		        data: "standard=" + ($("#standard").val()),
		        success: function(dataDB) {
		        	var options="<option value=''>Select</option><option value='BOARD'>BOARD</option>";
		        	if(dataDB != "null" && dataDB !="")
					{
		        		var examObject = document.getElementById("exam");
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
	
	$("#subject").change(function(){
		if($(this).val()!=''){
			document.getElementById("uploadDiv").setAttribute("style", "visibility: visible;");
		}
	});
	
	var p = 1;
	$(".addFileClassName").each(function(){
   	$(this).click(function(){                        		
   		var tableNode = $(this).parent().parent().parent();
   		var row = $('<tr><td><input type="file" name="uploadFile.fileData" id="fileData'+p+'"/></td><td><img  src="images/minus_icon.png" onclick="deleteThisRow(this);"></td></tr>');
           $(tableNode).append(row); 
           p++;
		});
	});
	
});

function deleteThisRow(obj){	
	var parent = obj.parentNode.parentNode;
	parent.parentNode.removeChild(parent);
}

function validatePage(){
	var year = document.getElementById("year").value;
	if(year == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML = "Please Select Academic Year ";
		return false;
	}
	var standard = document.getElementById("standard").value;
	if(standard == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML = "Please Select a Standard";
		return false;
	}
	var exam = document.getElementById("exam").value;
	if(exam == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML = "Please Select a Exam";
		return false;
	}
	var subject = document.getElementById("subject").value;
	if(subject == ""){
		document.getElementById("warningbox").style.visibility = 'visible';
		document.getElementById("warningmsg").innerHTML = "Please Select a Subject";
		return false;
	}
}
