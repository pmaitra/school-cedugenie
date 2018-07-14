$(document).ready( function(){
	
	
	$("#zone").change(function (){
		$.ajax({
		url: '/icam/getStatesAgainstZone.html',
		dataType: 'json',
		data: "zone=" + $("#zone").val(),
		success: function(data) {
			var options='<option value="">Select</option>';
			if(data!=""){
				data=data.split(",");						
				for(var i=1;i<data.length;i++){
					var state=data[i].split(":");
					options=options+'<option value="'+state[0]+'">'+state[1]+'</option>';
				}
			}
			document.getElementById("state").innerHTML=options;
		}
		});
	})
});

function updateLocation(rowId){
	var locationName = document.getElementById("locationName"+rowId).value;
	var locationArea = document.getElementById("area"+rowId).value;
	var locationCity = document.getElementById("city"+rowId).value;
	var locationState = document.getElementById("state"+rowId).value;
	var locationCountry = document.getElementById("country"+rowId).value;
	var locationZone = document.getElementById("zone"+rowId).value;
	var locationPin = document.getElementById("pin"+rowId).value;
	
	$('#updateLocationDetails > tbody').empty();
 	if((locationName != null && locationName!="") && (locationArea != null && locationArea!="") 
 		&& (locationCity != null && locationCity!="") && (locationState != null && locationState!="")
 		&& (locationCountry != null && locationCountry!="") && (locationZone != null && locationZone!="")
 		&& (locationPin != null && locationPin!="")){
 		var row = "<tbody>";
 		row += "<tr><td><input type='text' id='locationname' name='newHostelName' class='form-control' value='"+locationName+"' required></td>"
 		+"<td>"+locationArea+"</td>"
 		+"<td>"+locationCity+"</td>"
 		+"<td>"+locationState+"</td>"
 		+"<td>"+locationCountry+"</td>"
 		+"<td>"+locationZone+"</td>"
 		+"<td>"+locationPin+"</td></tr>";    				
 		
 		$("#updateLocationDetails").append(row);
 	}
	$('#modalInfo').fadeIn("fast");
    
 	var btn = document.getElementById("updateLocation");
 	btn.setAttribute("onclick","saveLocation('"+rowId+"');");
};

function saveLocation(rowid){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	var newlocationname = document.getElementById("locationname").value;
	
	if(newlocationname == null || newlocationname == ""){
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Location Name can not be Empty.";
		return false;
	}else{
		newlocationname = newlocationname.replace(/\s{1,}/g,' ');
		newlocationname = newlocationname.trim();
		newlocationname = newlocationname.toUpperCase();
		
		
		if(!newlocationname.match(reg1)){
			document.getElementById("warningmsg").style.display = 'block';			
			document.getElementById("warningmsg").innerHTML = "Invalid Location Name. (Alpha-Numeric. Atleat 1 character, and special caracter not allowed.)";
			return false;
		}else{
			document.getElementById("saveId").value=rowid;
			document.getElementById("newLocationName").value=newlocationname;
			
			document.getElementById("warningmsg").style.display = 'none';	
			document.editLocation.submit();
		}
	}
	
};

function closeWarning(){
	document.getElementById("warningmsg").style.display = 'none';	
};