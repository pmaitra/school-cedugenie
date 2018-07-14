window.onload = function(){
	var checkbox = getElementsByClassName("listShowHide");
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'rbl', checkbox[i]);
	}
};



function onCheckBoxSubmit(){
	if($('input[name=bookCode]:checked').length<=0){
		 alert("Please Select An Option.");
		 return false;
	}
	else{
		document.retirementBookIdList.method = "POST";
		document.retirementBookIdList.action = "retirementBookIdList.html";
		document.retirementBookIdList.submit();
	}
}