	$(document).ready(function() {
		document.getElementById("warningbox").style.visibility='collapse';
		document.getElementById("warningmsg").innerHTML="";
		
		$('#selectionType').change(function(){
			//Validate Standard Name
			standard = document.getElementById("standard").value;
			standard = standard.replace(/\s{1,}/g,'');
			if(standard == ""){	
				document.getElementById("warningbox").style.visibility="visible";
				document.getElementById("warningmsg").innerHTML="Please Select Class";
				return false;
			}
			//Validate Merit List Type
			meritListType = document.getElementById("meritListType").value;
			meritListType = meritListType.replace(/\s{1,}/g,'');
			if(meritListType == ""){	
				document.getElementById("warningbox").style.visibility="visible";
				document.getElementById("warningmsg").innerHTML="Please Select Merit List Type";
				return false;
			}
		});
		
		
		$("#standard").change(function() {
			document.getElementById("meritListType").value="";
			document.getElementById("selectionType").value="";
			document.getElementById("warningbox").style.visibility='collapse';
			document.getElementById("warningmsg").innerHTML="";
			document.getElementById("selectionType").innerHTML="<option value=''>Select...</option>"
		});
		$("#meritListType").change(function() {
			document.getElementById("selectionType").value="";
			document.getElementById("warningbox").style.visibility='collapse';
			document.getElementById("warningmsg").innerHTML="";
			document.getElementById("selectionType").innerHTML="<option value=''>Select...</option>"
			if($("#meritListType").val()=='EXAM'){
				$("#selectionType").append('<option value="SELECTED">Selected</option>');
				$("#selectionType").append('<option value="REVIEW">Under Review</option>');
				$("#selectionType").append('<option value="REJECTED">Rejected</option>');
			}
			if($("#meritListType").val()=='PI AND MEDICAL'){
				$("#selectionType").append('<option value="SELECTED">Selected</option>');
				$("#selectionType").append('<option value="QUEUED">Waiting</option>');
				$("#selectionType").append('<option value="REVIEW">Under Review</option>');
				$("#selectionType").append('<option value="REJECTED">Rejected</option>');
			}
			
		});
	});
	
	function validateForm(){
		document.getElementById("warningbox").style.visibility="collapse";
		document.getElementById("warningmsg").innerHTML="";
		
		standard = document.getElementById("standard").value;
		standard = standard.replace(/\s{1,}/g,'');
		if(standard == ""){	
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please select class";
			return false;
		}
		meritListType = document.getElementById("meritListType").value;
		meritListType = meritListType.replace(/\s{1,}/g,'');
		if(meritListType == ""){	
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please select merit list type";
			return false;
		}
		selectionType = document.getElementById("selectionType").value;
		selectionType = selectionType.replace(/\s{1,}/g,'');
		if(selectionType == ""){	
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please select Selection Type";
			return false;
		}
	}
	
	