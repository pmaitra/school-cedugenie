$(document).ready(function(){
	$('#rejectCancelButton').click( function() {
		document.getElementById("formId").value="";
		document.getElementById("rollNumber").value="";
		document.getElementById("reason").value="";
		
		unloadRejectBox();
		
		document.getElementById("rejectId").value="";
		document.getElementById("nameReject").innerHTML="";
		document.getElementById("reasonText").value="";
	}); 
	
	$('#rejectButton').click( function() {
		document.getElementById("formId").value=document.getElementById("rejectId").value;
		document.getElementById("reason").value=document.getElementById("reasonText").value;
		document.veryfyDocumentForm.action="rejectDocument.html";
		document.veryfyDocumentForm.submit();
		unloadRejectBox();
	}); 		

	function loadRejectBox() {    // To Load the Popupbox
		$('#rejectDialog').fadeIn("fast");
		$("#wrap").css({ // this is just for style
		    "opacity": "0.3"
		});
	}
	
	function unloadRejectBox() {    // TO Unload the Popupbox
	    $('#rejectDialog').fadeOut("fast");
	    $("#wrap").css({ // this is just for style       
	        "opacity": "1" 
	    });
	}
	
	$(".submitbtn").each(function(){		
		$(this).click(function(){			
			document.getElementById("nameReject").innerHTML=$(this).attr('id');
			document.getElementById("rejectId").value=$(this).attr('name');
			document.getElementById("reasonText").value="";			
			loadRejectBox();
		});
	});
	
	//**********************************
	
	
	
	
	
	
	
	$('#approveCancelButton').click( function() {
		id="";
		document.getElementById("formId").value="";
		document.getElementById("rollNumber").value="";
		document.getElementById("reason").value="";
		
		unloadConfirmBox();
		
		document.getElementById("confirmId").value="";
		document.getElementById("nameApprove").innerHTML="";
		document.getElementById("rollNumberText").value="";
		
	});
	$("#rollNumberText").bind('blur',function(){
		var intRegx=numeric=/^[0-9]{1,}$/;
		var rNum=document.getElementById("rollNumberText").value;
		rNum=rNum.trim();
		rNum=rNum.replace(/\s{1,}/g,'');
		document.getElementById("rollNumberText").value=rNum;
		if(rNum!=""){
			if(!rNum.match(intRegx)){
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML="Only Numeric Roll Number Allowed";
			}else{
				$.ajax({
			        url: '/cedugenie/checkAvailableRollNumber.html',
			        data: "rollNumber=" + rNum,
			        dataType: 'json',
			        success: function(data){
			        	if(data == 'not-available'){		        		
			        		document.getElementById("warningbox").style.visibility='visible';
			    			document.getElementById("warningmsg").innerHTML="Roll Number "+rNum+" Not Available";
			    			document.getElementById("rollNumberText").value="";
			        	}else{
			        		document.getElementById("warningbox").style.visibility='collapse';
			    			document.getElementById("warningmsg").innerHTML="";
			        	}
			        }
			    });
			}
		}		
	});
	$('#approveButton').click( function() {
		if(document.getElementById("rollNumberText").value!=""){
			document.getElementById("formId").value=document.getElementById("confirmId").value;
			document.getElementById("rollNumber").value=document.getElementById("rollNumberText").value;
			alert(document.getElementById("formId").value+"\n\n"+document.getElementById("rollNumber").value);
			document.veryfyDocumentForm.action="approveDocument.html";
			document.veryfyDocumentForm.submit();
			
			unloadConfirmBox();
		}else{
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Enter Roll Number.";
		}
		
	});

	function loadConfirmBox() {    // To Load the Popupbox
		$('#conFirmDialog').fadeIn("fast");
		$("#wrap").css({ // this is just for style
		    "opacity": "0.3"
		});
	}
	
	function unloadConfirmBox() {    // TO Unload the Popupbox
	    $('#conFirmDialog').fadeOut("fast");
	    $("#wrap").css({ // this is just for style       
	        "opacity": "1" 
	    });
	}
	
	$(".greenbtn").each(function(){		
		$(this).click(function(){
			document.getElementById("nameApprove").innerHTML=$(this).attr('id');
			document.getElementById("confirmId").value=$(this).attr('name');
			document.getElementById("rollNumberText").value="";
			loadConfirmBox();
		});
	});
	
});