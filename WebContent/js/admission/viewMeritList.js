	var i=0;
	


	$(document).ready(function() {
		/*document.getElementById(" ").style.visibility='collapse';
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
			}else{
				$('#getMeritList').submit();
			}
		});*/
		
		
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
	
	function resetCandidateStatus(index,meritListType,status){
		if(meritListType=='EXAM'){
			if(status == 'REVIEW'){
				document.getElementById("selectedStatus"+index).checked = false;
				document.getElementById("rejectedStatus"+index).checked = false;
			}
			if(status == 'REJECTED'){
				document.getElementById("selectedStatus"+index).checked = false;
				document.getElementById("reviewStatus"+index).checked = false;
			}
		}
		
		if(meritListType=='PI AND MEDICAL'){
			if(status == 'REVIEW'){
				document.getElementById("waitingStatus"+index).checked = false;
				document.getElementById("selectedStatus"+index).checked = false;
				document.getElementById("rejectedStatus"+index).checked = false;
			}
			if(status == 'REJECTED'){
				document.getElementById("waitingStatus"+index).checked = false;
				document.getElementById("selectedStatus"+index).checked = false;
				document.getElementById("reviewStatus"+index).checked = false;
			}
			if(status == 'QUEUED'){
				document.getElementById("selectedStatus"+index).checked = false;
				document.getElementById("reviewStatus"+index).checked = false;
				document.getElementById("rejectedStatus"+index).checked = false;
			}
		}
	}
	
	
	
	function validMeritListPage(){
		if(i==1)
		{
		document.getElementById("tabUpdateMeritList").style.visibility='collapse';
		i=0;
		}
		var standard = document.getElementById("standard").value;
		if(standard == ""){
			document.getElementById("warningbox").style.visibility = 'visible';
			document.getElementById("warningmsg").innerHTML = "Please Select Standard ";
			return false;
		}
		var meritListType = document.getElementById("meritListType").value;
		if(meritListType == ""){
			document.getElementById("warningbox").style.visibility = 'visible';
			document.getElementById("warningmsg").innerHTML = "Please Select Merit List Type";
			return false;
		}
		var selectionType = document.getElementById("selectionType").value;
		if(selectionType == ""){
			document.getElementById("warningbox").style.visibility = 'visible';
			document.getElementById("warningmsg").innerHTML = "Please Select selectionType";
			return false;
		}
		
		var intRegx=/^[0-9]{1,}$/;
		var fromRollNoId = document.getElementById("fromRollNoId");
		var fromRollNoIdValue=fromRollNoId.value;
		//alert(fromRollNoIdValue);
		fromRollNoIdValue=fromRollNoIdValue.replace(/\s{1,}/g,'');
		fromRollNoId.value=fromRollNoIdValue;
		
		var toRollNoId = document.getElementById("toRollNoId");
		var toRollNoIdValue=toRollNoId.value;
		toRollNoIdValue=toRollNoIdValue.replace(/\s{1,}/g,'');
		toRollNoId.value=toRollNoIdValue;
		
		//alert(toRollNoIdValue +"\t\t"+ fromRollNoIdValue);
		
		if(fromRollNoIdValue != "")
		{
			if(!fromRollNoIdValue.match(intRegx))
				{
					document.getElementById("warningbox").style.visibility = 'visible';
					document.getElementById("warningmsg").innerHTML = "Please Enter Integer value for FromRollNo";
					return false;
				}
		}
		if(toRollNoIdValue != "")
		{
			if(!toRollNoIdValue.match(intRegx))
				{
					document.getElementById("warningbox").style.visibility = 'visible';
					document.getElementById("warningmsg").innerHTML = "Please Enter Integer value for ToRollNo";
					return false;
				}
		}
		
		if(toRollNoIdValue != "" && fromRollNoIdValue != ""){
			toRollNoIdValue = parseInt(toRollNoIdValue);
			fromRollNoIdValue = parseInt(fromRollNoIdValue);
			if(toRollNoIdValue<fromRollNoIdValue){
				toRollNoId.value=fromRollNoIdValue;
				fromRollNoId.value=toRollNoIdValue;
			}
		}
		return true;
	}