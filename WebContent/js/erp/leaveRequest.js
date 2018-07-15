$(document).ready(function() { 	
	$("#leaveType").change(
			function() {
				$("#remainingLeave").val("");
				$("#leaveTypeName").text("");
				if(($(this).val())== "null" || ($(this).val())=='' ){
					 $("#leaveTypeName").text(" ");
					return;
				}
				$.ajax({
		        url: '/cedugenie/getRemainingLeave.html',
		        dataType: 'json',
		        data: "leaveType=" + ($(this).val()),
		        success: function(dataDB) {				        	
		        	if(dataDB !=null)		        	{	  	
	                    $("#leaveTypeName").text("Available : "+dataDB);
	                    $("#remainingLeave").val(dataDB);
		        	}else{
	        			$("#leaveTypeName").text("Leave Not Available");
	        		}
		        }
			});
	});	
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
    	pair: $('#endDate'),
    	//direction: true
    	});
    $('#endDate').Zebra_DatePicker({
  	  	format: 'd/m/Y',
  	  	direction: true
  	});
    
    //add more file components if Add is clicked
    $(".addFileClassName").each(function(){
    	$(this).click(function(){                      		
    		var tableNode = $(this).parent().parent().parent().parent();
    		var row = $('<tr>'); 
            row.append($('<td></td><td><input type="file" name="fileData" /><img  src="/sms/images/minus_icon.png" onclick="deleteThisRow(this);"></td>'));
            $(tableNode).append(row); 
		});
    });
	
});
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
		/*
		var start = $("#startDate").val();
		var end = $("#endDate").val();
		start = start.split("/");
		end = end.split("/");
		var sDate = new Date(start[1]+"/"+start[0]+"/"+start[2]);
		var eDate = new Date(end[1]+"/"+end[0]+"/"+end[2]);

		var startDays = Math.floor(sDate.getTime()/(3600*24*1000));
		var endDays = Math.floor(eDate.getTime()/(3600*24*1000));
		var diff = (endDays - startDays) + 1;
		$("#requestedNumberOfLeave").text("Requested Total Leave : "+diff);
		 $('#totalRequestedLeave').val(diff);
		 */
	
		$.ajax({
		    url: '/cedugenie/getWorkingDaysForLeaveRequest.html',		   
		    dataType: 'json',
		    data: "startDate=" + ($('#startDate').val())+"&"+"endDate=" +($('#endDate').val()),
		    success: function(data) {		    	
		    	if(data != null){
		    		$("#requestedNumberOfLeave").text("Requested Total Leave : "+data);
		   		 	$('#totalRequestedLeave').val(data);
	    		}else{
	    			$("#requestedNumberOfLeave").text("Requested Total Leave : "+0);
	    			 $('#totalRequestedLeave').val(0);
	    		}
		    }  
		});
	}
}

function showdaydiff(){
	if(document.getElementById("title").value==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningbox").innerHTML="Please Enter The Subject";
		return false;		
	}
	if(document.getElementById("leaveType").value==""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningbox").innerHTML="Please Select Leave Type";
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
	
	var totalRequestedLeave = document.getElementById("totalRequestedLeave").value;
	if(!parseInt(totalRequestedLeave)>0){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningbox").innerHTML="Requested leave should be more then 0";
		return false;
	}
	var remainingLeave = document.getElementById("remainingLeave").value;
	if(remainingLeave!="" && !parseInt(remainingLeave)>0){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningbox").innerHTML="Available leave should be more then 0 or select EXCESS LEAVE";
		return false;
	}
				
	function deleteThisRow(obj){	
		var parent = obj.parentNode.parentNode;
		parent.parentNode.removeChild(parent);
	}
		
//	var date1 = document.getElementById('startDate').value; 
//	var date2 = document.getElementById('endDate').value; 
//	date1 = date1.split("/");
//	date2 = date2.split("/");
//	var sDate = new Date(date1[1]+"/"+date1[0]+"/"+date1[2]);
//	var eDate = new Date(date2[1]+"/"+date2[0]+"/"+date2[2]);
//	/*sDate.create().format('{Weekday} {d} {Month}, {yyyy}'); */
//	var daysApart = Math.abs(Math.round((sDate-eDate)/86400000))+1;
//	document.getElementById("totalRequestedLeave").value=daysApart;
}