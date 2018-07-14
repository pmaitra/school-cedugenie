function addFees(){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	
	var feesName=document.getElementById("feesName").value;	
	feesName=feesName.replace(/\s{1,}/g,' ');
	feesName=feesName.trim();
	feesName=feesName.toUpperCase();
	document.getElementById("feesName").value=feesName;	
	
	/*if(!feesName.match(reg1)){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Invalid Fees Name. (Alpha-Numeric. Atleat 1 character.)";
		alert("This feees name is not valid.");
		return false;
	}*/
	var oldFeesNames = document.getElementsByName('oldFeesNamesForDuplicateCheck');
	for(var i=0; i<oldFeesNames.length;i++){
		if(oldFeesNames[i].value==feesName){
			/*document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Fees Name Already Exixts";*/
			alert("Fess name already exists!");
			return false;
		}
	}
	return true;
}

function preventDuplicateFeesName(rowId){
	var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;
	var lettersOnly=/^([a-zA-Z]+\s)*[a-zA-Z]+$/;
	var obj = document.getElementById(rowId);
	val=obj.value;
	val=val.replace(/\s{1,}/g,' ');
	val=val.trim();
	val=val.toUpperCase();
	obj.value=val;
	/*if (val == "") {
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Please Enter A Category Name.";
		return false;
	}*/
	
	if(!val.match(lettersOnly)){
		/*document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Invalid Category Name.";*/
		alert("Invalid Category Name.");
		return false;
	}
		
	var feesList = document.getElementsByName("oldFeesNamesForDuplicateCheck");		
	for(var i=0; i<feesList.length; i++){
		var oldVal=feesList[i].value;
		if(oldVal==val){
			/*document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = " Duplicate Social Category Name!";*/
			alert("Duplicate fees name not allowed!");
			return false;
		}
	}
	return true;
}