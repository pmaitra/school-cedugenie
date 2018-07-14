$(document).ready(function(){
	/*var table = document.getElementById("studentResult");
	table.style.visibility='collapse'*/;
	var standardValue = ($("#standard").val());
	/*if(standardValue=='IX'||standardValue == 'X'){
		document.getElementById("term").style.display = 'none';	
		document.getElementById("termhead").style.display = 'none';	
		alert("within1");
		document.getElementById("term").value = "Term1";
		alert($("#term").val());
	}*/
	 $("#standard").change(function(){
		 document.getElementById('studentName').selectedIndex = 0;
		/*table.style.visibility='collapse';
					
		removeOption(sectionObject);
		var subjectObject=document.getElementById("subject");				
		removeOption(subjectObject);
		var examObject=document.getElementById("exam");				
		removeOption(examObject);
		deleteRow(table);
		document.getElementById("insertUpdate").value="INSERT";
		document.getElementById("btnDiv").style.visibility='collapse';
		document.getElementById("infomsgbox1").style.visibility='collapse';
		document.getElementById("infomsg1").innerHTML="";*/
		
		 var sectionObject=document.getElementById("section");	
		$.ajax({
	        url: '/cedugenie/getSectionAgainstStandard.html',
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
		
		var standardValue = ($("#standard").val());
		var termObject=document.getElementById("term");		
		if(standardValue=='VI'||standardValue == 'VII'||standardValue=='VIII'){
			document.getElementById("term").style.display = 'block';	
			document.getElementById("termhead").style.display = 'block';
			
					
			//removeOption(termObject);
			document.getElementById("warningbox").style.visibility='collapse';
			$.ajax({
		        url: '/cedugenie/getTermForStandard.html',
		        dataType: 'json',
		        data: "standard=" + ($("#standard").val()),
		        success: function(dataDB) {
		        
		        	var options="<option value=''>Select</option>";
		        	if(dataDB != "null" && dataDB !="")
					{
		        		var arr = dataDB.split("*");
						for (var i=0;i<arr.length-1;i++){
							//var codeName=arr[i].split("~");
							options=options+"<option value='"+arr[i]+"'>"+arr[i]+"</option>";
						}
					}
		        	termObject.innerHTML=options;
		       }
			});
		}
		if(standardValue=='IX'||standardValue == 'X'){
			
			//alert("within");
			
			var options="<option value=''>Select</option>";
			options=options+"<option value='AnnualExam1'>Annual Exam</option>";
			termObject.innerHTML=options;
			
			document.getElementById("term").value = "AnnualExam1";
			//document.getElementById('term').selectedIndex = 1;
			document.getElementById("term").style.display = 'none';	
			document.getElementById("termhead").style.display = 'none';	
			//document.getElementById("term").value = "Term1";
			//alert($("#term").val());
		}
	});
	 
	 
	 $("#section").change(function(){
		
			$.ajax({
		        url: '/cedugenie/getStudentsAgainstStandardAndSectionForNewReport.html',
		        dataType: 'json',
		        data: {
	        		"standard":$("#standard").val(),
	        		"section":$('#section').val()
		        },
		        success: function(data) {
		        	
		        	$('#studentName').empty();
		        	
		        	if(data != ""){  
		        		document.getElementById("warningbox").style.visibility='collapse';
		        		$('#studentName')
		        		 .append('<option value="">Select</option>')
		        		 .val('');
		        		var arr = data.split(";");
		        		for (var i=0;i<arr.length;i++){
	        		  		if(arr[i]!=""){
	        		  			var arr2 = arr[i].split("*");
	        		  			$("#studentName").append($("<option></option>").val(arr2[0]+"-"+arr2[1]).html(arr2[0]+"-"+arr2[1]));
	        		  		}			        				
	        			}
		        	}else{				        					        		
		        		/*$('#studentName')
	        		    .find('option')
	        		    .remove()
	        		    .end()					        		   
	        		    .append('<option value="ALL">All Students</option>')
	        		    .val('');*/
	        			alert("No Student Found For Selected Class & Section");
		    		}
		        }
			});
	 });
	
	 
	 $("#term").change(function(){
		 document.getElementById('studentName').selectedIndex = 0;
	 });
	 $("#studentName").change(function(){
		 $.ajax({
		        url: '/cedugenie/getCoScolasticResultAgainsRollNumber.html',
		        dataType: 'json',
		        data: {
	        		"rollName":$("#studentName").val(),
	        		"loggedInUser":$("#loggedInUser").val(),
	        		"term":$("#term").val()
		        },
		        success: function(data) {
		        	//alert(data);
		        	if(data != 'NE'){
		        		var arrData = data.split("~");
			        	for (var j=1;j<arrData.length-1;j++){
			        		var grade = arrData[j].split("*");
			        	//	var selectElement = document.getElementById(arrData[0]+'-Grade');
			        	//	alert(grade[0]);
			        		var elmnt = document.getElementById(grade[0]+'-Grade');
			        		for(var i=0; i < elmnt.options.length; i++)
			        		  {
			        		    if(elmnt.options[i].value === grade[1]) {
			        		      elmnt.selectedIndex = i;
			        		      break;
			        		    }
			        		  }
			        	}
		        	}else{
		        		//alert("else");
		        		document.getElementById('WORK EDUCATION-Grade').selectedIndex = 0;
		        		document.getElementById('HEALTH EDUCATION-Grade').selectedIndex = 0;
		        		document.getElementById('PHYSICAL EDUCATION-Grade').selectedIndex = 0;
		        		document.getElementById('DISCIPLINE-Grade').selectedIndex = 0;
		        	}
		        	
		        }
		 });
	
	 });
	
});


function saveCoScholasticResult(){
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
	var standard = document.getElementById("standard").value;
	if(standard == 'VI' ||standard =='VII'||standard=='VIII'){
		if(document.getElementById("term").value==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Select Term.";
			return false;
		}
	}
	
	
	if(document.getElementById("studentName").value==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Student.";
		return false;
	}
	if(document.getElementById("WORK EDUCATION-Grade").value==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Grade For Work Education.";
		return false;
	}
	if(document.getElementById("HEALTH EDUCATION-Grade").value==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Grade For Health Education.";
		return false;
	}
	if(document.getElementById("PHYSICAL EDUCATION-Grade").value==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Grade For Physical Education.";
		return false;
	}
	if(document.getElementById("DISCIPLINE-Grade").value==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Grade For Discipline.";
		return false;
	}
	
	
	return true;
}