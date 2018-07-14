window.onload=function(){
	var checkbox=getElementsByClassName("listShowHide");
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'details', checkbox[i]);
	}
};

function ShowAll(cb){
	if(cb.checked){
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			checkbox[i].checked=true;
			ShowHideField(checkbox[i].value, 'details', checkbox[i]);
		}
	}
	else{
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			checkbox[i].checked=false;
			ShowHideField(checkbox[i].value, 'details', checkbox[i]);
		}
	}
	
}

$(document).ready(function(){
	//var valid= null;
	$('#edit').click(function(){
		var chk=0;
		$('input:radio').each(function()
				{	
					
					if ($(this).is(':checked'))
					{					
						chk=1;		
		    			showtwo();
		    			
					}
				});
					if (chk==0){
						document.getElementById("warningbox").style.visibility='visible';
						document.getElementById("warningmsg").innerHTML="Please Select An Option";
						return false;
					}
					
				});
		
	});

function showone(){
	 document.struc.method="get";
	 document.struc.action="leaveStructure.html";
	 document.struc.submit();             // back from the page
	 return true;
}		

function showtwo(){
	 document.struc.method="get";
	 document.struc.action="editLeaveStructure.html";
	 document.struc.submit();             // back from the page
	 return true;
}