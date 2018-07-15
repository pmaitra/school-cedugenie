$(document).ready(function(){	    
	
	$("#staffUserId").change(function(){		
		var table = document.getElementById("availableLeaveDetailsTab"); 
	    var rowCount = table.rows.length;
	    for(var i=rowCount; i>2; i--)
		{
	   		table.deleteRow(i-1);	   		  
	    }
	    document.getElementById("availableLeaveDetailsTab").style.visibility="collapse";
	    
	    document.getElementById("title").value="";
	    document.getElementById("startDate").value="";
	    document.getElementById("endDate").value="";
	    document.getElementById("totalRequestedLeave").value="";
	    document.getElementById("desc").value="";
	    document.getElementById("remarks").value="";		
		
		if($(this).val()==''){
			return;
		}
		$.ajax({
	        url: '/cedugenie/getSpecificEmployeeCompleteLeaveDetails.html',
	        data: "staffUserId=" +($(this).val()),
	        success: function(data) {
	        	if(null!=data && data!=""){
		        	data=data.split("#");
		        	for(var i=0;i<data.length-1;i++){
		        		var p= data[i].split("*");
		        		//alert("p=="+p);
		        		var table = document.getElementById("availableLeaveDetailsTab"); 
		        	    var rowCount = table.rows.length;
		        	    var row = table.insertRow(rowCount);				
		        	    					
		        	    var cell1 = row.insertCell(0);
		        	    var element1 = document.createElement("input");
		        	    element1.type = "hidden";
		        	    element1.name="leaveList["+i+"].leaveType";
		        	    element1.value=p[0];
		        	    element1.className ="form-control";		        	    
		        	    cell1.appendChild(element1);
		        	    element1 = document.createTextNode(p[0]);
		        	    cell1.appendChild(element1);
		        	    
		        	    var cell2 = row.insertCell(1);		
		        	    var element2 = document.createTextNode(p[1]);		        	
		        	    cell2.appendChild(element2);         	

		        	    var cell3 = row.insertCell(2);		
		        	    var element3 = document.createTextNode(p[2]);		        	  
		        	    cell3.appendChild(element3);    
		        	    
		        	    var cell4 = row.insertCell(3);		
		        	    var element4 = document.createTextNode(p[4]);		        		
		        	    cell4.appendChild(element4);
		        	    
		        	/*    var cell5 = row.insertCell(4);		
		        	    var element5 = document.createTextNode(p[4]);		        	    
		        	    cell5.appendChild(element5);	*/				        
		        	    
		        	    var calculate_fun="calculateRevisedLeave("+i+");";
		        	    var cell6 = row.insertCell(4);		
		        	    var element6 = document.createElement("input");
		        	    element6.type = "text";
		        	    element6.value = "0";
		        	    element6.setAttribute("style","width:50px;");
		        	    element6.setAttribute("onblur",calculate_fun);
		        	    element6.setAttribute("onClick","clearApplyingLeaveOnClick("+i+");");
		        	    element6.name="leaveList["+i+"].totalRequestedLeave";	
		        	    element6.id="applyingLeave"+i;
		        	    element6.className ="form-control";
		        	    cell6.appendChild(element6);
		        	    
		        	    var cell7 = row.insertCell(5);		
		        	    var element7 = document.createElement("input");
		        	    element7.type = "text";
		        	    element7.value=p[4];
		        	    element7.setAttribute("readonly","readonly");
		        	    element7.setAttribute("style","text-align: right;");
		        	    element7.setAttribute("style","width:50px;");
		        	    element7.name="revisedRemainingLeave"+i;	
		        	    element7.id="revisedRemainingLeave"+i;
		        	    element7.className ="form-control";
		        	    cell7.appendChild(element7);
		        	    
		        	    var element8 = document.createElement("input");
		        	    element8.type = "hidden";
		        	    element8.value=p[4];
		        	    element8.id="remainingLeaveHidden"+i;
		        	    element8.name="remainingLeaveHidden"+i;
		        	    element8.className ="form-control";
		        	    cell7.appendChild(element8);		        		
		        	}
		        	document.getElementById("availableLeaveDetailsTab").style.visibility="visible";
	        	}else{
	        		
	        	}
	        	
	        }
		});		
	});
});

$(document).ready(function() {    
    $('#startDate').Zebra_DatePicker();    
    $('#startDate').Zebra_DatePicker({
    	  format: 'd/m/Y',
    	  direction: false
    	});  
    
    $('#endDate').Zebra_DatePicker();    
    $('#endDate').Zebra_DatePicker({
    	  format: 'd/m/Y',
    	  direction: false
    	});    
 });



function validateManualLeaveDetails(){
	var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;
	var intRegx=/^[0-9]{1,}$/;
	var obj,val;	
	
	obj=document.getElementById("staffUserId");
	val=obj.value;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select User Id.";
		return false;
	}
	
	obj=document.getElementById("title");
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	obj.value=val;	
	if(!val.match(alphaNumeric)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Enter The Title";
		return false;
	}
	
	
	if(document.getElementById("startDate").value==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningbox").innerHTML="Please Provide Start Date";
		return false;		
	}
	
	if(document.getElementById("endDate").value==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningbox").innerHTML="Please Provide End Date";
		return false;		
	}

	obj=document.getElementById("totalRequestedLeave");
	val=obj.value;
	val=val.replace(/\s{1,}/g,'');
	obj.value=val;
	if((!val.match(intRegx))){
    	document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningbox").innerHTML="Invalid Requested Leave";
		return false;
    }
	
	var totalRequestedLeave = document.getElementById("totalRequestedLeave").value;
	if(!parseInt(totalRequestedLeave)>0){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningbox").innerHTML="Requested leave should be more then 0";
		return false;
	}
	
	obj=document.getElementById("desc");
	val=obj.value;	
	obj.value=val;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningbox").innerHTML="Invalid Desc";
		return false;
	}
	
	obj=document.getElementById("remarks");
	val=obj.value;	
	obj.value=val;
	if(val==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningbox").innerHTML="Invalid Remarks";
		return false;
	}

	var totalRequestedLeave = document.getElementById("totalRequestedLeave").value;
	
	if(parseInt(totalRequestedLeave)>0){
		var tb=getElementsByClassName("form-control");
		var tot=0;
		for(var i=0;i<tb.length;i++){
			if(!tb[i].value.match(intRegx)){
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningbox").innerHTML="Requested Leave and alloted leave must be equal";
				return false;
			}else{
				tot=parseInt(tb[i].value)+tot;
			}
		}
		if(tot!=parseInt(totalRequestedLeave)){			
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML="Requested Leave and alloted leave must be equal";
			return false;
		}else{
			
		}
	}	
	
	return true;
	
}


function showRequestLeaveCount(){
	if(($("#endDate").val())== "null" || ($("#endDate").val())=='' ){
		 $("#requestedNumberOfLeave").text("");
		return;
	}
	if(($("#startDate").val())== "null" || ($("#startDate").val())=='' ){
		 $("#requestedNumberOfLeave").text(" ");
		return;
	}
	if(($("#endDate").val()!= "") && ($("#startDate").val()!= "")){
		
		var start = $("#startDate").val();
		var end = $("#endDate").val();
		start = start.split("/");
		end = end.split("/");
		var sDate = new Date(start[1]+"/"+start[0]+"/"+start[2]);
		var eDate = new Date(end[1]+"/"+end[0]+"/"+end[2]);

		var startDays = Math.floor(sDate.getTime()/(3600*24*1000));
		var endDays = Math.floor(eDate.getTime()/(3600*24*1000));
		var diff = endDays - startDays;
		$("#requestedNumberOfLeave").text("Requested Total Leave : "+diff);
		 $('#totalRequestedLeave').val(diff);
	}	

}
	function clearApplyingLeaveOnClick(index){		
		document.getElementById("applyingLeave"+index).value="";
	}
	
	function calculateRevisedLeave(index){
		
		var flag = true;
		//document.getElementById("warningbox").style.visibility='collapse';
		//document.getElementById("warningmsg").innerHTML = "";
		
		intRegx=/^[0-9]{1,}$/;
		var applyingLeave = document.getElementById("applyingLeave"+index).value;
		var remainingLeave = document.getElementById("revisedRemainingLeave"+index).value;
		var remainingLeaveHidd = document.getElementById("remainingLeaveHidden"+index).value;
		var applyingLeaveValue = parseInt(applyingLeave);
		var remainingLeaveValue = parseInt(remainingLeave);
		var remainingLeaveHiddValue = parseInt(remainingLeaveHidd);
		
		
		 if (!applyingLeave.match(intRegx)) {
				//document.getElementById("warningbox").style.visibility='visible';
				//document.getElementById("warningmsg").innerHTML = "Please Enter Numeric Positive Value or field should not be blank";
			 alert("Please Enter Numeric Positive Value or field should not be blank");
				document.getElementById("applyingLeave"+index).value="0";
				applyingLeave=0;
				document.getElementById("revisedRemainingLeave"+index).value=remainingLeaveHidd;
				flag = false;
				return false;
		}
		
		
		 
		if(remainingLeaveValue<applyingLeaveValue){
			//document.getElementById("warningbox").style.visibility='visible';
			//document.getElementById("warningmsg").innerHTML = "Applying Leave should less or equal with Available Leave.";
			alert("Applying Leave should less or equal with Available Leave.");
			document.getElementById("applyingLeave"+index).value=0;
			applyingLeave=0;
			document.getElementById("revisedRemainingLeave"+index).value=remainingLeaveHidd;
			flag = false;
			return false;
		}
		
		var revised=remainingLeaveHiddValue-applyingLeaveValue;
		//alert("Revised==="+revised);
		document.getElementById("revisedRemainingLeave"+index).value=revised;
		var totalRequestedLeave = document.getElementById("totalRequestedLeave").value;
		totalRequestedLeave = parseInt(totalRequestedLeave);
		var table = document.getElementById("availableLeaveDetailsTab"); 
	    var rowCount = table.rows.length-2;
	    var totalApplyingLeaveCount=0;
	    for(var i=0;i<rowCount;i++){
	    	totalApplyingLeaveCount = totalApplyingLeaveCount+parseInt(document.getElementById("applyingLeave"+i).value);
	    }
		if(totalApplyingLeaveCount != totalRequestedLeave){
			flag = false;
			return false;
		}
		
		return flag;
		}
	