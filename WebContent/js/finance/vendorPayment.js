$(document).ready(function(){
	
	function loadConfirmBox() {    // To Load the Popupbox
		$('#rejectDialog').fadeIn("fast");
		$("#wrap").css({ // this is just for style
		    "opacity": "0.3"
		});
	}
	
	function unloadConfirmBox() {    // TO Unload the Popupbox
	    $('#rejectDialog').fadeOut("fast");
	    $("#wrap").css({ // this is just for style       
	        "opacity": "1" 
	    });
	}
	
	$(".greenbtn").each(function(){		
		$(this).click(function(){
			document.getElementById("warningmsg1").innerHTML="";
			document.getElementById("warningbox1").style.visibility="collapse";
			
			var data=$(this).attr('name');
			data=data.split("~");
			alert(data[0]+"\t"+data[1]+"\t"+data[2]);
			document.getElementById("maxAmount").value=data[2];
			document.getElementById("poCode").value=data[0];
			document.getElementById("poType").value=data[1];
			loadConfirmBox();
		});
	});	
	
	$('#rejectCancelButton').click( function() {
		document.getElementById("maxAmount").value="";
		document.getElementById("poCode").value="";
		document.getElementById("poType").value="";
		unloadConfirmBox();
	});
	
});

function validate(){
	var amount=parseFloat(document.getElementById("amount").value);
	var maxAmount=parseFloat(document.getElementById("maxAmount").value);
	if(amount<=0){
		document.getElementById("warningmsg1").innerHTML="Amount Cannot Be Less Than Or Equals To 0";
		document.getElementById("warningbox1").style.visibility="visible";
		return false;
	}else{
		if(document.getElementById("poType").value=="MESS"){		
			if(amount<maxAmount){
				document.getElementById("warningmsg1").innerHTML="Full Payment required";
				document.getElementById("warningbox1").style.visibility="visible";
				return false;
			}else{
				return true;
			}
		}else if(document.getElementById("poType").value=="TENDER"){
			if(amount>maxAmount){
				document.getElementById("warningmsg1").innerHTML="Payment Cannot Be Greater Than Max Amount";
				document.getElementById("warningbox1").style.visibility="visible";
				return false;
			}else{
				return true;
			}
		}
	}
	return true;
}