$(document).ready( function(){
			
		/*	
			$("#standard").change(function (){
				$.ajax({
				url: '/icam/getCourseForStandard.html',
				dataType: 'json',
				data: "standard=" + $("#standard").val(),
				success: function(data) {
					var options='<option value="">Select</option>';
					if(data!=""){
						data = data.split("#*#");	
						data1 = data[0].split("~");						
						for(var i=0;i<data1.length-1;i++){
							var course=data1[i].split("*");
							options=options+'<option value="'+course[0]+'">'+course[1]+'</option>';
						}
					}
					document.getElementById("course").innerHTML=options;
					data2 = data[1].split("###");
					for(var i=1;i<data2.length;i++){
						//alert("data2 value ====="+data2[i]);
						document.getElementById(data2[i]).checked = false;
					}
				}
				});
			});*/
			
			$("#course").change(function (){
				$.ajax({
				url: '/icam/getSubjectsForACourse.html',
				dataType: 'json',
				data: "course=" + $("#course").val(),
				success: function(data) {
					//alert(data);
					$("input[name='subjects']:checkbox").removeAttr('checked');
						document.getElementById("oldSubjects").innerHTML="";
						var oldSubjects="";
							if(data!="" ||data !=''){
								data1 = data.split("*~*");
								for(var i=0;i<data1.length-1;i++){
									//alert("within");
									var sub=data1[i].split("#@#");
									document.getElementById(sub[1]).checked=true;
									oldSubjects=oldSubjects+"<input type='hidden' name='oldSubjects' value='"+sub[1]+"'/>";
								}
								//alert(oldSubjects);
								document.getElementById("oldSubjects").innerHTML=oldSubjects;
							}
						
					
				}
				});
			});
			
			
		});


function validation()
{
	
	var check=document.getElementsByName("subjects");
		var course=document.getElementById("course").value;
	if(course == ""||course =='null'){
		document.getElementById("javascriptmsg2").style.display = 'block';			
		document.getElementById("javascriptmsg2").innerHTML = "No Program Selected";	
		return false;
	}
	
	
	for (var i=0;i<check.length;i++)
		{
		if(check[i].checked)
			{
			
			return true;
			}
		
		document.getElementById("javascriptmsg2").style.display = 'block';			
		document.getElementById("javascriptmsg2").innerHTML = "No Mapping done yet";	
		}
	
	return false;
}
function removeMsg()
{
	document.getElementById("javascriptmsg2").style.display = 'none';
	}