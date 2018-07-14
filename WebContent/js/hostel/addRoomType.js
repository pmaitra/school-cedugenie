function checkRoomTypeName(){
	var roomTypeNameDB = document.getElementsByName("roomTypeNameDB");
	var roomTypeName = document.getElementById("roomTypeName").value;	
	roomTypeName = roomTypeName.trim();	
	if(roomTypeName == ""){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Enter Room Type.";
		return false;
	}else{
		roomTypeName = roomTypeName.toUpperCase();
	
		for(var i=0; i<roomTypeNameDB.length ; i++){	
			var roomTypeNameDBVal  = roomTypeNameDB[i].value;		
			roomTypeNameDBVal = roomTypeNameDBVal.trim();	
			roomTypeNameDBVal = roomTypeNameDBVal.toUpperCase();		
			if(roomTypeName == roomTypeNameDBVal){
				document.getElementById("roomTypeName").value="";
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML="Room type exists.";
				return false;
				break;
			}else{
				document.getElementById("warningbox").style.visibility='collapse';
			}		
		}
	}
	return true;
}