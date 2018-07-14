function validate(){
	var depositeBoxes=document.getElementsByClassName("textfield");
	for(var i=0;i<depositeBoxes.length;i++){
		if(!(numericValidation(depositeBoxes[i]))){
			alert("Enter Valid Deposit Amount");
			return false;
		}
	}
	return true;
}