$(document).ready( function(){
	$("#venueTypeCode").change(function (){
		var venueType = $("#venueTypeCode").val();
		//document.getElementById("noOfSeatsLabel").style.display  = 'none';
		//document.getElementById("availableSeat").style.display  = 'none';
		//document.getElementById("roomNoLabel").style.display  = 'none';
		//document.getElementById("roomNo").style.display  = 'none';
		//document.getElementById("floorLabel").style.display  = 'none';
		//document.getElementById("floor").style.display  = 'none';
		
			$.ajax({
				url: '/cedugenie/getVenueAgainstVenueType.html',
				dataType: 'json',
				data: "venueTypeCode=" + $("#venueTypeCode").val(),
				success: function(data) {
					var options='<option value="">Select</option>';
					if(data!=""){
						data=data.split(",");						
						for(var i=1;i<data.length;i++){
							var venue=data[i].split(":");
							options=options+'<option value="'+venue[0]+'">'+venue[1]+'</option>';
						}
					}
					document.getElementById("venueCode").innerHTML=options;
				}
			});
				
				
	})
	$("#venueCode").change(function (){
		//document.getElementById("noOfSeatsLabel").style.display  = 'block';
	//	document.getElementById("availableSeat").style.display  = 'block';
		//document.getElementById("roomNoLabel").style.display  = 'block';
		////document.getElementById("roomNo").style.display  = 'block';
		//document.getElementById("floorLabel").style.display  = 'block';
		//document.getElementById("floor").style.display  = 'block';
		$.ajax({
			url: '/cedugenie/getBuildingAgainstParentVenue.html',
			dataType: 'json',
			data: "venueCode=" + $("#venueCode").val(),
			success: function(data) {
				document.getElementById("building").value  = data;
				$("#building").attr('readonly', 'readonly');
			}
		});
	})
});

function updateVenue(rowId){
	var venueName = document.getElementById("venueName"+rowId).value;
	var venueStatus = document.getElementById("venueStatus"+rowId).value;
	
	var status1 = true;
	var status2 = false;
	var status3 = "Enable";
	var status4 = "Disable";
	var options="";
	if(venueStatus == 't'){
		options=options+"<option value='"+status1+"' selected>"+status3+"</option>";
		options=options+"<option value='"+status2+"'>"+status4+"</option>";
	}else{
		options=options+"<option value='"+status1+"'>"+status3+"</option>";
		options=options+"<option value='"+status2+"' selected>"+status4+"</option>";
	}
	
	$('#updateVenueDetails > tbody').empty();
 	if((venueName != null && venueName!="") && (venueStatus != null && venueStatus!="")){
 		var row = "<tbody>";
 		row += "<tr><td><input type='text' id='venuename' name='venuename' class='form-control' value='"+venueName+"' required></td>"
 		+"<td><select id='venuestatus' name='venuestatus' class='form-control'>'"+options+"'</select></td></tr>";    				
 		
 		$("#updateVenueDetails").append(row);
 	}
	$('#modalInfo').fadeIn("fast");
    
 	var btn = document.getElementById("updateVenue");
 	btn.setAttribute("onclick","saveVenue('"+rowId+"');");
};

function saveVenue(rowid){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	var newvenuename = document.getElementById("venuename").value;
	var newvenuestatus = document.getElementById("venuestatus").value;
	
	if(newvenuename == null || newvenuename == ""){
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Location Name can not be Empty.";
		return false;
	}else{
		newvenuename = newvenuename.replace(/\s{1,}/g,' ');
		newvenuename = newvenuename.trim();
		newvenuename = newvenuename.toUpperCase();
		
		
		if(!newvenuename.match(reg1)){
			document.getElementById("warningmsg").style.display = 'block';			
			document.getElementById("warningmsg").innerHTML = "Invalid Location Name. (Alpha-Numeric. Atleat 1 character, and special caracter not allowed.)";
			return false;
		}else{
			document.getElementById("saveId").value=rowid;
			document.getElementById("newvenuename").value=newvenuename;
			document.getElementById("newvenuestatus").value=newvenuestatus;
			
			document.getElementById("warningmsg").style.display = 'none';	
			document.editVenue.submit();
		}
	}
	
};

function closeWarning(){
	document.getElementById("warningmsg").style.display = 'none';	
};