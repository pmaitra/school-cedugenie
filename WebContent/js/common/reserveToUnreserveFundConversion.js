$(document).ready(function(){
	var reserve = document.getElementById("reserveFund").value;
	var resFund = parseFloat(reserve);
	if(resFund <= 0.0){
		document.getElementById("messageDiv").style.display = "block";
		document.getElementById("conversionDiv").style.display = "none";
		document.getElementById("submitDiv").style.display = "none";
	}
});
function calculateTotalUnreserve(){
	var available = document.getElementById("unreserveFund").value;
	var reserve = document.getElementById("resFund").value;
	var dedicated = document.getElementById("dedicatedAmount").value;
	if(isNaN(dedicated)){
		dedicated = 0.0;
		document.getElementById("dedicatedAmount").value = dedicated;
	}
	if(parseFloat(dedicated) > parseFloat(reserve)){
		document.getElementById("errorMessageDiv").style.display = "block";
		document.getElementById("submitDiv").style.display = "none";
	}else{
		document.getElementById("errorMessageDiv").style.display = "none";
		document.getElementById("submitDiv").style.display = "block";
	}
	var unreserve = parseFloat(parseFloat(available) + parseFloat(dedicated));
	var reserveAmt = parseFloat(parseFloat(reserve) - parseFloat(dedicated));
	document.getElementById("availableFund").value = unreserve;
	document.getElementById("reserveFund").value = reserveAmt;
}