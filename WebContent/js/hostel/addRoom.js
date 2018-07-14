/* function addrows(){		
        var table = document.getElementById("roomFacilityTable"); 
        var rowCount = table.rows.length;          
        var row = table.insertRow(rowCount-1);

        var cell1 = row.insertCell(0);
        var element1 = document.createElement("input");
        element1.type = "checkbox";
        element1.name="roomFacilityCode";       
        element1.value="RFID";
        cell1.appendChild(element1);

		var cell2 = row.insertCell(1);		
        var element2 = document.createElement("input");
        element2.type = "text";
        element2.name="roomFacilityName";
        element2.className = "textfield1";
		cell2.appendChild(element2);
			
		var cell3 = row.insertCell(2);		
        var element3 = document.createElement("input");
        element3.type = "text";
        element3.value="0";
        element3.className = "textfield1";
        element3.setAttribute("onfocus","this.value=''");
        element3.name="roomFacilityQuantity";        
        cell3.appendChild(element3); 
         
	   	
        var serialNo=document.getElementsByName("facilitySerialNo");
        for(var i=0;i<serialNo.length;i++){
        	serialNo[i].setAttribute("id",i);
    	}
}
function deleteRow(){	
    var table = document.getElementById("roomFacilityTable");
    var rowCount = table.rows.length;		
	var count=1;
	
	for(var i=2; i<rowCount - 1; i++){
		var row = table.rows[i];
		var chkbox = row.cells[0].childNodes[0];
		if(true == chkbox.checked){
			count=count+1;
		}
		
	}		
	if(count == 1){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Select Atleast One Option !";		
		return false;
	}	
	if(rowCount < count+1){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Row Required !";		
		return false;
	}		
    for(var i=0; i<rowCount-1; i++){
        var row = table.rows[i];
        var chkbox = row.cells[0].childNodes[0];
        if(null != chkbox && true == chkbox.checked){
            table.deleteRow(i);
            rowCount--;
            i--;
            document.getElementById("warningbox").style.visibility='collapse';    				
        }
    }
} */   

//$(document).ready(function(){		 
	 var retval = null; 
	 var facilityDetails = "";
	$('#submit').click(function(){		
		//alert("submit called");
		var hostelName = $('#hostelName').val();
		var roomTypeName = $('#roomTypeCode').val();		
		var roomName = $('#roomName').val();
		var roomDesc = $('#roomDesc').val();
		var bedTotal = $('#bedTotal').val();			
		
		if(hostelName == ""){	
			alert("Please Select hostel Name!");			
			retval = false;
			return false;
		}
		if(roomTypeName == ""){
			alert("Please select Room Type!");
			retval = false;
			return false;
		}	
		if(roomName==""){
			alert("Please Enter Room Name!");
			retval = false;
			return false;
		}
		if(roomDesc==""){
			alert("Please Enter Room Position!");	
			retval = false;
			return false;
		}
		if(bedTotal==0){
			alert("Please Enter Total Bed!");	
			retval = false;
			return false;
		}	
		bedTotal = parseInt(bedTotal);
		if(isNaN(bedTotal)){
			alert("Please Enter Total Bed in numerical format!");
			retval = false;
			return false;
		}
		$('input:checkbox').each(function(){
			if ($(this).is(':checked')){
				//document.getElementById("warningbox").style.visibility='collapse';
				var roomfacilitycode = $(this).val();				
	   			var roomfacilityname = $(this).parent().next().find('input:text').val();
	   			var roomfacilityquantity = $(this).parent().next().next().find('input:text').val();
	  			if(roomfacilityname==""){
	  				/*document.getElementById("warningbox").style.visibility='visible';
	  				document.getElementById("warningmsg").innerHTML="Atleast One Row Required !";*/
	  				alert("Atleast One Row Required !");
	  				retval = false;
	  				return false;
	  			}
	  			if(roomfacilityquantity == 0){	  				
	  				/*document.getElementById("warningbox").style.visibility='visible';
	  				document.getElementById("warningmsg").innerHTML="Please Enter Room Facility Quantity !";*/
	  				alert("Please Enter Room Facility Quantity !");
	  				retval = false;
	  				return false;
	  			}
	  			roomfacilityquantity = parseInt(roomfacilityquantity);
	  			if(isNaN(roomfacilityquantity)){	  				
	  				/*document.getElementById("warningbox").style.visibility='visible';
	  				document.getElementById("warningmsg").innerHTML="Please Enter Room Facility Quantity in neumerical format !";*/	
	  				alert("Please Enter Room Facility Quantity in numerical format !");
	  				retval = false;
	  				return false;
	  			}
	  			else{   
	  				//document.getElementById("warningbox").style.visibility='collapse';
	  				facilityDetails = roomfacilitycode + "-" + roomfacilityname + "-" + roomfacilityquantity + "/" + facilityDetails;		
	  				
	  				$("#addFacilityHide").val(facilityDetails);
	  				//alert($("#addFacilityHide").val);
	  				retval = true;
	  				return true;
	  			} 	
			}
		});  		
		return retval; 		    			
	});	 
	
	 /*$("#roomName").bind('blur',function(){
		var roomName=$("#roomName").val();	
		var hostelName = $('#hostelName').val();
		var roomTypeCode = $('#roomTypeCode').val();
		if(hostelName == ""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Select Hostel Name !";	
			$("#roomName").val("");
			return false;
			}
		if(roomTypeCode == ""){
			
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Select Room Type !";	
			$("#roomName").val("");
			return false;
			}
		else{
			document.getElementById("warningbox").style.visibility='collapse';
		$.ajax({
		    url: '/sms/checkRoomName.html',
		    dataType: 'json',
		    data: "hostelName=" + hostelName+ "&roomName=" +roomName,
		    success: function(data) {		    	
		    	if(data == "YES"){ 		
		    		document.getElementById("warningbox").style.visibility='visible';
		    		document.getElementById("warningmsg").innerHTML="Room Name already exists !";	
	        	  	$("#roomName").val("");	        	  
	        	  	return false;
	        		}	
		    	else{	
		    		document.getElementById("warningbox").style.visibility='collapse';		    		
		    		}
		    	}  
			});
		}
		}); 
	 
	 
	 $("#roomName").blur(function(){
			var hostelName=$("#hostelName").val();
			var roomName=$("#roomName").val();
			if(hostelName==""){
				document.getElementById("warningbox").style.visibility='visible';
	    		document.getElementById("warningmsg").innerHTML="Select Hostel Name First.";	 
			}else{
				$.ajax({
				    url: '/sms/checkRoomName.html',		   
				    dataType: 'json',
				    data: "roomName=" + roomName+ "&hostelName=" +hostelName,
				    success: function(data) {		    	
				    	if(data == "YES"){ 
				    		document.getElementById("warningbox").style.visibility='visible';
				    		document.getElementById("warningmsg").innerHTML="Room Name already exists For Selected Hostel.";	        	  	
			        	  	$("#hostelName").val("");
			        	  	$("#roomName").val("");
			        	  	return false;
			        	}	
				    	else{
				    		$("#roomCode").val(roomName);
				    		document.getElementById("warningbox").style.visibility='collapse';
				    	}
				    }  
				});
			}
		});*/
//});
