
function validate(){
	if(!(nullValidation(document.getElementById("academicYear")))){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML = "Select Valid Academic Year";
		return false;
	}
	var amount=getElementsByClassName("textfield1");
	for(var i=0;i<amount.length;i++){
		if(!(numericValidation(amount[i]))){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML = "Enter Valid Amount";
			return false;
		}
	}
	return true;
}

function per(){
	/* modified by sourav.bhadra on 22-03-2018 */
	var budgetAmount=document.getElementById("budgetAmount").value;
	var reserveFund=document.getElementById("resFund").value;
	var percentageValue=document.getElementById("percentage").value;
	var calculatedValue=(budgetAmount)*(percentageValue)/100;
	document.getElementById("amount").value=calculatedValue;
	var remaining= reserveFund-calculatedValue;
	document.getElementById("reserveFund").value=remaining;
}

$("#department").change(function() {
	document.getElementById("amount").value="";
	document.getElementById("percentage").value="";
});

