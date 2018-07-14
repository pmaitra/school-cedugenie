function validate(){
	var ledger=getElementsByClassName("defaultdropdown");
	for(var i=0;i<ledger.length;i++){
		if(!(nullValidation(ledger[i]))){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML = "Ledger Should Be Selected For All Breakup";
			return false;
		}
	}
	document.getElementById("warningbox").style.visibility='collapse';
	return true;
}