$(document).ready( function(){
			
			
			$("#course").change(function (){
				//alert("ajax call");
				$.ajax({
					
				    url: '/cedugenie/getTermNameForCourse.html',
				    dataType: 'json',
				    data:"course=" +  $("#course").val(),
				    success: function(data) {	
				    	var options='<option value="">Select</option>';
				    	if(data!=null && data != ""){	        		
			    			var termArr1 = data.split("*~*");
			    			
			    			for(var a=0; a<termArr1.length;a++){
			    				if(termArr1[a] != null && termArr1[a] != ""){
			    					var termNameAndCode=termArr1[a].split("#@#");  
											options=options+'<option value="'+termNameAndCode[0]+'">'+termNameAndCode[1]+'</option>';
									}        				
			    			}	        				
				    	}
				    	document.getElementById("term").innerHTML=options;
				    	/*else{
				    		alert("No Course Found For Class ")+ (thisClassNode.value);
				    	}*/
				    }
				}); 
			});
			
			$("#term").change(function (){
				//alert("ajax call for term");
				$.ajax({
				url: '/cedugenie/getSubjectsForTermAndCourse.html',
				dataType: 'json',
				data: "course=" + ($("#course").val())+ "&term=" + ($("#term").val()),
				success: function(data) {
					$("input[name='subjects']:checkbox").removeAttr('checked');
						document.getElementById("oldSubjects").innerHTML="";
						var oldSubjects="";
						if(data != "" || data !=''){
							data1 = data.split("#*#");
							//alert("data1==="+data1+"data size : "+data1.length);
							for(var i=0; i<data1.length-1; i++){
								var sub=data1[i].split("#~#");
							//	alert(sub[0]);
								//alert(sub[1]);
								document.getElementById(sub[1]).checked=true;
								oldSubjects=oldSubjects+"<input type='hidden' name='oldSubjects' value='"+sub[1]+"'/>";
							}
							//alert("hii")
							//alert("oldSubjects : "+oldSubjects);
							document.getElementById("oldSubjects").innerHTML=oldSubjects;
							
							
						}/*else{
						//alert("got null");
					}*/
				}
				});
			});
			
			
		});


function validate(){
	var standard=document.getElementById("standard").value;
	
	if(standard == ""||standard =='null'){
		alert("Please Enter Subject");
		return false;
	}
	
	var course=document.getElementById("course").value;
	if(course == ""||course =='null'){
		alert("Please Enter Course");
		return false;
	}
	return true;
}

function validationField()
{
	
	var course=document.getElementById("course").value;
	if(course == ""||course =='null'){
		document.getElementById("javascriptmsg2").style.display = 'block';			
		document.getElementById("javascriptmsg2").innerHTML = "Select A Program";
		return false;
	}
	
	var term=document.getElementById("term").value;
	
	if(term == ""||term =='null'){
		document.getElementById("javascriptmsg2").style.display = 'block';			
		document.getElementById("javascriptmsg2").innerHTML = "Select A Term";
		return false;
	}
	
	
	var status='';
	var status=validating();
	
	if (status==true)
		{
		return true;
		}
	else
		{
		document.getElementById("javascriptmsg2").style.display = 'block';			
		document.getElementById("javascriptmsg2").innerHTML = "No Mapping has been done yet";
		return false;
		}
}

function validating()
{
	
	var check=document.getElementsByName("subjects");

	for(var i=0;i<check.length;i++)
		{
		if(check[i].checked)
			{
			
			document.getElementById("javascriptmsg2").style.display = 'none';
			return true;
			
			
			}
		else
			{
		
			
			document.getElementById("javascriptmsg2").style.display = 'block';			
			document.getElementById("javascriptmsg2").innerHTML = "No Mapping has been done yet";
			
			
			}
		}
	return false;
	}

function removeMsg()
{
	document.getElementById("javascriptmsg2").style.display = 'none';
	}
function removeMSG()
{
	document.getElementById("javascriptmsg2").style.display = 'none';	
}