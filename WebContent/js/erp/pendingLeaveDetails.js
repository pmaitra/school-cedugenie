function validateForm(){
	var decision = document.getElementById("decision").value;
	if(decision==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select The Decision";
		return false;
	}
	
	if(decision == "SCHEDULED"){
		var requested=parseInt(document.getElementById("totalRequestedLeave").value);
		var granted=0;
		var tb=getElementsByClassName("textfield1");
		for(var i=0;i<tb.length;i++){
			granted=granted+parseInt(tb[i].value);
		}
		if(requested!=granted){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Requested And Granted Leaves Should Be Equal.";
			return false;
		}
	}
	return true;
}


function resetAssignLeaveToZero(selectBox){
	var selectBoxVal = selectBox.value;	
	var tb=getElementsByClassName("textfield1");
	if(selectBoxVal == "REJECTED"){
		for(var i=0;i<tb.length;i++){
			tb[i].value="0";
			tb[i].setAttribute("readonly", "readonly");
			document.getElementById("revisedRemainingLeave"+i).value=document.getElementById(i).value;
		}
	}else{
		alert("2::"+selectBoxVal+"::");
		for(var i=0;i<tb.length;i++){
			tb[i].value="0";
			tb[i].removeAttribute("readonly", "readonly");
			document.getElementById("revisedRemainingLeave"+i).value=document.getElementById(i).value;
		}
	}
}



function clearApplyingLeaveOnClick(index){
	var val=document.getElementById("applyingLeave"+index).value;
	val=val.replace(/\s{1,}/g,'');
	document.getElementById("applyingLeave"+index).value=val;
	if(val=="0"){
		document.getElementById("applyingLeave"+index).value="";
	}
}

function calculateRevisedLeave(index){
	document.getElementById("revisedRemainingLeave"+index).value=document.getElementById(index).value;
	var intRegx=numeric=/^[0-9]{1,}$/;
	var val=document.getElementById("applyingLeave"+index).value;
	val=val.replace(/\s{1,}/g,'');
	if(val=="")
		val="0";
	document.getElementById("applyingLeave"+index).value=val;
	
	var val1=document.getElementById("applyingLeave"+index).value;
	var val2=document.getElementById("revisedRemainingLeave"+index).value;	
	
	if(!val1.match(intRegx)){
		document.getElementById("applyingLeave"+index).value="0";
		val1="0";
	}else{
		val1 = parseInt(val1);
		if(val1<0){
			document.getElementById("applyingLeave"+index).value="0";
			val1="0";
		}
	}
	val1 = parseInt(val1);
	val2 = parseInt(val2);
	
	if(val1>val2){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML = "Leave Greater than Available";
		document.getElementById("applyingLeave"+index).value="0";
	}else{
		document.getElementById("revisedRemainingLeave"+index).value=val2-val1;
	}
	
	/*
	var flag = true;
	document.getElementById("warningbox").style.visibility='collapse';
	document.getElementById("warningmsg").innerHTML = "";
	ran1 = /^\d+?\d*$/;
	var excessLeave = document.getElementById("excessLeave").value;
	var applyingLeave = document.getElementById("applyingLeave"+index).value;
	var remainingLeave = document.getElementById("remainingLeave"+index).value;
	
	 if (!applyingLeave.match(ran1)) {
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML = "Please Enter Numeric Positive Value or field should not be blank";
			//document.getElementById("applyingLeave"+index).value=0;
			document.getElementById("revisedRemainingLeave"+index).value=(remainingLeave-0);
			flag = false;
			return false;
	}
	applyingLeave = parseInt(applyingLeave);
	remainingLeave = parseInt(remainingLeave);
	excessLeave = parseInt(excessLeave);
	if(remainingLeave<applyingLeave){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML = "Applying Leave should less or equal with Available Leave.";
		//document.getElementById("applyingLeave"+index).value=0;
		document.getElementById("revisedRemainingLeave"+index).value=(remainingLeave-0);
		flag = false;
		return false;
	}
	document.getElementById("revisedRemainingLeave"+index).value=(remainingLeave-applyingLeave);
	var totalRequestedLeave = document.getElementById("totalRequestedLeave").value;
	totalRequestedLeave = parseInt(totalRequestedLeave);
	var table = document.getElementById("availableLeaveDetailsTab"); 
    var rowCount = table.rows.length-2;
    var totalApplyingLeaveCount=0;
    for(var i=0;i<rowCount;i++){
    	totalApplyingLeaveCount = totalApplyingLeaveCount+parseInt(document.getElementById("applyingLeave"+i).value);
    }
	if((excessLeave+totalApplyingLeaveCount)!= totalRequestedLeave){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML = "Total Assign For and excess leave should be equal with Total Requested Leave.";
		flag = false;
		return false;
	}
	
	return flag;
	*/
}

$(document).ready(function() { 	
	$("#startDate").click(function(){		
		$("#endDate").val("");
	});
	
	$("#endDate").blur(function(){		
		showRequestLeaveCount();
	});	
	 // assuming the controls you want to attach the plugin to 
    // have the "datepicker" class set
    $('#startDate').Zebra_DatePicker();
    $('#endDate').Zebra_DatePicker();
    document.getElementById("endDate").value="";
    
    $('#startDate').Zebra_DatePicker({
    	  format: 'd/m/Y',
    	  pair: $('#endDate')
    	});
    $('#endDate').Zebra_DatePicker({
  	  format: 'd/m/Y',
  	 direction: true
  	});
});