
window.onload = function(){
	var checkbox = getElementsByClassName("listShowHide");
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'copl', checkbox[i]);
	}
};

function ShowAll(cb){
	if(cb.checked){
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			checkbox[i].checked = true;
			ShowHideField(checkbox[i].value, 'copl', checkbox[i]);
		}
	}
	else{
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			checkbox[i].checked = false;
			ShowHideField(checkbox[i].value, 'copl', checkbox[i]);
		}
	}
	
}

function getLoggedData(){
	 $.ajax({
		    url: '/sms/getAdmissionDriveLogDetails.html',
		    dataType: 'json',
		    success: function(data) {
		    	if(data!=''){
		    		createTable(data);
			    }
		    }
	 });
}