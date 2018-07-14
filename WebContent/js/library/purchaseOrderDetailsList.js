
window.onload = function(){
	var checkbox = getElementsByClassName("listShowHide");
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'sitable', checkbox[i]);
	}
};

function ShowAll(cb){
	if(cb.checked){
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			checkbox[i].checked=true;
			ShowHideField(checkbox[i].value, 'sitable', checkbox[i]);
		}
	}
	else{
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			checkbox[i].checked=false;
			ShowHideField(checkbox[i].value, 'sitable', checkbox[i]);
		}
	}
	
}

function valradio() {
	if($('input[name=bcode]:checked').length<=0){
	 alert("Please Select An Option");
	 return false;
	}
}