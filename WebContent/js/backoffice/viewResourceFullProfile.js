$(document).ready(function(){
	var presentRole = $("#currentRole").val();
	$.ajax({
		 url: '/icam/getModulesForRole.html',
	     dataType: 'json',
	     data: "currentRole=" + presentRole,
	     success: function(dataDB){
	    	 var array = dataDB.split("#");
	    	 for(var i=0; i<array.length-1; i++){
	    		 var moduleToDisplay = array[i];
	    		 document.getElementById(moduleToDisplay).style.display = "block";
	    	 }
	     }
	})
	var presentUserId = $("#currentUserId").val();
	$.ajax({
		url: '/icam/getPersonalDetailsForOwnProfile.html',
		dataType: 'json',
		data: "currentUserId="+presentUserId,
		success: function(dataDB){
			alert(dataDB);
			var arr = dataDB.split("#");
			if(arr[0] == null || arr[0] == ''){
				document.getElementById("resourceName").value = "";
			}
			else{
				document.getElementById("resourceName").value = arr[0];
			}
			if(arr[1] == null || arr[1] == ''){
				document.getElementById("resourceFatherName").value = "";
			}
			else{
				document.getElementById("resourceFatherName").value = arr[1];
			}
			if(arr[2] == null || arr[2] == ''){
				document.getElementById("resourceMotherName").value = "";
			}
			else{
				document.getElementById("resourceMotherName").value = arr[2];
			}
			if(arr[3] == null || arr[3] == ''){
				document.getElementById("DOB").value = "";
			}
			else{
				document.getElementById("DOB").value = arr[3];
			}
			if(arr[4] == null || arr[4] == ''){
				document.getElementById("email").value = "";
			}
			else{
				document.getElementById("email").value = arr[4];
			}
			if(arr[5] == null || arr[5] == ''){
				document.getElementById("gender").value = "";
			}
			else{
				document.getElementById("gender").value = arr[5];
			}			
			if(arr[6] == null || arr[6] == ''){
				document.getElementById("mobile").value = "";
			}
			else{
				document.getElementById("mobile").value = arr[6];
			}
			if(arr[7] == null || arr[7] == ''){
				document.getElementById("aadhaarNo").value = "";
			}
			else{
				document.getElementById("aadhaarNo").value = arr[7];
			}
			if(arr[8] == null || arr[8] == ''){
				document.getElementById("socialCategory").value = "";
			}
			else{
				document.getElementById("socialCategory").value = arr[8];
			}
			if(arr[9] == null || arr[9] == ''){
				document.getElementById("hostel").value = "";
			}
			else{
				document.getElementById("hostel").value = arr[9];
			}
			if(arr[10] == null || arr[10] == ''){
				document.getElementById("address").value = "";
			}
			else{
				document.getElementById("address").value = arr[10];
			}
			if(arr[11] == null || arr[11] == ''){
				document.getElementById("postOffice").value = "";
			}
			else{
				document.getElementById("postOffice").value = arr[11];
			}			
			if(arr[12] == null || arr[12] == ''){
				document.getElementById("postalCode").value = "";
			}
			else{
				document.getElementById("postalCode").value = arr[12];
			}
			if(arr[13] == null || arr[13] == ''){
				document.getElementById("policeStation").value = "";
			}
			else{
				document.getElementById("policeStation").value = arr[13];
			}
			if(arr[14] == null || arr[14] == ''){
				document.getElementById("railWayStation").value = "";
			}
			else{
				document.getElementById("railWayStation").value = arr[14];
			}
			if(arr[15] == null || arr[15] == ''){
				document.getElementById("city").value = "";
			}
			else{
				document.getElementById("city").value = arr[15];
			}
			if(arr[16] == null || arr[16] == ''){
				document.getElementById("district").value = "";
			}
			else{
				document.getElementById("district").value = arr[16];
			}
			if(arr[17] == null || arr[17] == ''){
				document.getElementById("state").value = "";
			}
			else{
				document.getElementById("state").value = arr[17];
			}			
			if(arr[18] == null || arr[18] == ''){
				document.getElementById("country").value = "";
			}
			else{
				document.getElementById("country").value = arr[18];
			}
		}
	})
});