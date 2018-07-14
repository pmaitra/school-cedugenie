function nullValidation(obj){
	var objectValue=obj.value;
	objectValue = objectValue.replace(/\s{1,}/g, ' ');
	obj.value=objectValue;
	 if(objectValue==null || objectValue=="" || objectValue==" "){
		 obj.value="";
		 return false;
	 }
	 return true;
}

function numericValidation(obj){
	var objectValue = obj.value.replace(/^\s+|\s+$/g,'');	
	if(objectValue != ""){
		if(objectValue != "0.00" || objectValue != "0.0" || objectValue != "0"){
			if(isNaN(objectValue)) {
				obj.value="0.00";
				return false;
			}else{
				var val=parseFloat(objectValue);
				if(val<0.0){
					obj.value="0.00";
					return false;
				}
			}
		}
	}else{
		obj.value="0.00";
	}
	return true;
}


function validateDateRange(startId,endId){
	if(document.getElementById(startId).value==""){
		return false;
	}
	if(document.getElementById(endId).value==""){
		return false;
	}
	return true;
}