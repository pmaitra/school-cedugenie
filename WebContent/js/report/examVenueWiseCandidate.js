
	function validateForm(){
		document.getElementById("warningbox").style.visibility="collapse";
		document.getElementById("warningmsg").innerHTML="";
		
		venueId = document.getElementById("venueId").value;
		venueId = venueId.replace(/\s{1,}/g,'');
		if(venueId == ""){	
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please select Exam Centre";
			return false;
		}
		
	}
	
	