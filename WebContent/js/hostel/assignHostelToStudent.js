$(document).ready(function(){
	$("#hostelName").change(function() {
		$.ajax({
			url:' /cedugenie/getRoomType.html',
			data:'hostelName='+($(this).val()),
			dataType: 'json',
			success: function(data){	
			var s1=document.getElementById("roomTypeName");
			for(var i=s1.length;i>0;i--){
				s1.remove(i);
			}
			document.getElementById("roomTypeName").value="";
			var roomType=data.split("*");
			var roomTypeLength=roomType.length-1;
			var i;
								
			for(i=0;i<roomTypeLength;i++){
				var roomTypeCodeName=roomType[i].split("~");
				var roomTypeCode=roomTypeCodeName[0];
				var roomTypeName=roomTypeCodeName[1];
				s1.add(new Option(roomTypeName, roomTypeCode),null);
			}	
		}
			
	}); 
});
		
	$("#roomTypeName").change(function() {
		$.ajax({
			url:' /cedugenie/getRoomList.html',
			data:'roomTypeName='+($(this).val()) + "&hostelName=" + ($("#hostelName").val()),
			dataType: 'json',
			success: function(data){
				var i;
				var s1=document.getElementById("roomName");
				for(i=s1.length;i>0;i--){
					s1.remove(i);
				}
				document.getElementById("roomName").value="";
				var roomName=data.split("*");
				var roomNameLength=roomName.length-1;
						
				for(i=0;i<roomNameLength;i++){
					var roomCodeName=roomName[i].split("~");
					var Code=roomCodeName[0];
					var Name=roomCodeName[1];						
					s1.add(new Option(Name, Code),null);
				}
						
			}
				
		}); 
	});
		
	$("#userId").change(function() {
		$.ajax({
			url:' /cedugenie/getStudent.html',
			
			data:'rollNumber='+($(this).val()),
			
			dataType: 'json',
			success: function(data){
				//alert(data);
				var student=data.split("~");
				document.getElementById("name").value="";
				
				
				document.getElementById("name").value=student[0];
				
			}
		}); 
	});
		
	$("#roomName").change(function() {
		//document.getElementById("warningbox").style.visibility='collapse';
		$.ajax({
			url:' /cedugenie/getRoomFacilities.html',
			data:'roomName='+($(this).val()),
			dataType: 'json',
			success: function(data){				
				document.getElementById("bedsTableDiv").style.display='block';
				var table=document.getElementById("roomfac");
				var rowCount = table.rows.length;
				for(var i=rowCount-1;i>1;i--){
					table.deleteRow(i);
				}
				
				var roomFacilityQuantity=data.split("*");
				var roomFacilityQuantityLength=roomFacilityQuantity.length-1;
				var i;
				if(roomFacilityQuantityLength>1)
					document.getElementById("roomfacTableDiv").style.display='block';
				for(i=1;i<roomFacilityQuantityLength;i++){
					var roomFacilityAndQuantity=roomFacilityQuantity[i].split("~");
					var roomFacility=roomFacilityAndQuantity[0];
					var roomQuantity=roomFacilityAndQuantity[1];
					
					rowCount = table.rows.length;
					var row = table.insertRow(rowCount);
					
		            var cell0 = row.insertCell(0);
		            var element0 = document.createTextNode(roomFacility);
		            cell0.appendChild(element0);
		            
		            var cell1 = row.insertCell(1);
		            var element1 = document.createTextNode(roomQuantity);
		            cell1.appendChild(element1);
				}
				var totalBedAndAvailableBed=roomFacilityQuantity[0].split("~");
				document.getElementById("bedTotal").value=totalBedAndAvailableBed[0];
				if(totalBedAndAvailableBed[1]==0){
					document.getElementById("bedVaccent").value=totalBedAndAvailableBed[1];
					document.getElementById("submitButton").setAttribute('disabled','disabled');
					document.getElementById("clearButton").setAttribute('disabled','disabled');
				}else{
					document.getElementById("bedVaccent").value=totalBedAndAvailableBed[1];
					document.getElementById("submitButton").removeAttribute('disabled');
					document.getElementById("clearButton").removeAttribute('disabled');
				}
				if(totalBedAndAvailableBed[0]<totalBedAndAvailableBed[1]){
					alert("No Seats Available In This Room !");			
					document.getElementById("roomName").value="";
				}
			}
					
		}); 
	});
});
	
	
	/*function validateForm(){
		var registrationId=document.getElementById("registrationId").value;
		var roomName=document.getElementById("roomName").value;
		var roomTypeName=document.getElementById("roomTypeName").value;
		var hostelName=document.getElementById("hostelName").value;
		
		if(registrationId==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Select Registration ID !";		
			return false;
		}
	
		if(hostelName==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Select Hostel Name !";	
			return false;
		}
	
		if(roomTypeName==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Select Room Type !";		
			return false;
		}
	
		if(roomName==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Select Room Name !";	
			return false;
		}
		return true;
	}*/