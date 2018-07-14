$(document).ready(function() { 
	$("#department").change(
			function() {
				$.ajax({
			        url: '/icam/getDepartmentWiseUserList.html',
			        dataType: 'json',
			        data: "department=" + ($(this).val()),
			        success: function(dataDB) {
			        var x=document.getElementById("userId");
			        	removeOption(x);
			        	if(dataDB != "null" && dataDB !=""){
			        		document.getElementById("userId").removeAttribute("disabled", "disabled");
			        		var arr = dataDB.split(";");	
							for (var i=0;i<arr.length;i++)	{        									
								$("#userId").append($("<option></option>").val(arr[i]).html(arr[i]));
							}							
						}
			       }
				});	      
			});
	
	
			
	});

function removeOption(x){
	for(var i=x.length;i>0;i--){
		x.remove(i);
	}
}
function check(abc) {
	var p=abc.id;
	p=p.replace("serviceListDepartment","serviceName");
	$.ajax({
        url: '/icam/getDepartmentWiseUserList.html',
        dataType: 'json',
        data: "department=" + (abc.value),
        success: function(dataDB) {
        	 var x=document.getElementById(p);
        	removeOption(x);
        	x.remove(0);
        	if(dataDB != "null" && dataDB !=""){
        		var arr = dataDB.split(";");	
				for (var i=0;i<arr.length;i++)	{
					$("#"+p).append($("<option></option>").val(arr[i]).html(arr[i]));
				}							
			}else{
				$("#"+p).append($("<option></option>").val('').html('Select..'));
				alert("No Service Owner found.");
			}
       }
	});
	
}


function editRow(){	
	if(valradio('radio','warningbox','warningmsg')){		
		$('input:radio').each(function(){			
			if ($(this).is(':checked')){
				 document.getElementById("warningbox").style.visibility='collapse';				
				$(this).parent().next().find('input').attr('readonly',false);
				$(this).parent().next().next().find('select').attr('disabled',false);
				$(this).parent().next().next().next().find('select').attr('disabled',false);
				$("#updateDB").attr('disabled',false);	
			}
		});	
	}
};

function checkServiceName(){
	var previousNames = document.getElementsByName('allServiceType');
	var currentName=document.getElementById("ticketServiceName").value;	
	var userId = document.getElementById("userId").value;
	if(currentName == ""){
		alert("Please Enter Service Name!");
		return false;
	}
	for(var i=0;i<previousNames.length;i++){
		currentName = currentName.replace(/^\s+|\s+$/gm,'');
		currentName = currentName.toUpperCase();
		if(previousNames[i].value.toUpperCase() == currentName){
			alert("Name Already Exists!");
			return false;
		}
	}
	if(userId == ""){
		//document.getElementById("cwarningbox").style.visibility='visible';
		//document.getElementById("cwarningmsg").innerHTML="Select service owner!";
		alert("Select service owner!");
		return false;		
	}
	return true;
}

function checkNameToUpdate(){
	var ret=  null;
	$('input:radio').each(function(){			
		if ($(this).is(':checked')){
			var serviceNameDB = $(this).parent().next().find('input:text').val();
			if(serviceNameDB != ""){
				serviceNameDB = $.trim(serviceNameDB);
				serviceNameDB = serviceNameDB.toUpperCase();			
				var previousNamesDB=document.getElementsByName("radio");			
				for(var i=0;i<previousNamesDB.length;i++){				
					 if(previousNamesDB[i].value.toUpperCase() == serviceNameDB){				
						document.getElementById("warningbox").style.visibility='visible';				
						document.getElementById("warningmsg").innerHTML="Name Already Exists!";
						ret = false;
						return false;
					}else{
						document.getElementById("warningbox").style.visibility='collapse';					
					} 
				}
				return ret;
			}
			else{
				document.getElementById("warningbox").style.visibility='visible';				
				document.getElementById("warningmsg").innerHTML="Service Name is empty!";
				ret = false;
				return false;
			}
		}
		return ret;
	});
	return ret;
}
		

function verifyToDelete(){	
	if(valradio('radio','warningbox','warningmsg') == true){	
		var r=confirm("Are You Sure To Delete");
		if (r==true){			
			return true;
		}
		else{	
			document.getElementById("warningbox").style.visibility='collapse';		
			return false;
		}		
	}else{
		valradio('radio','warningbox','warningmsg');		
		return false;
	}
}
