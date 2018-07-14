
window.onload = function(){
	/*var checkbox = getElementsByClassName("listShowHide");*/
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'lbpo', checkbox[i]);
	}
};