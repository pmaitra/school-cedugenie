function checkAllCheckBox(box, checkBoxClass){
	var allBoxes=getElementsByClassName(checkBoxClass);
	if(box.checked){
		for(var i=0;i<allBoxes.length;i++){
			allBoxes[i].checked=true;
		}
	}else{
		for(var i=0;i<allBoxes.length;i++){
			allBoxes[i].checked=false;
		}
	}
	
}