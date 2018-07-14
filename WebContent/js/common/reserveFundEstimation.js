$(document).ready(function(){
	var reserve = document.getElementById("reserveFund").value;
	var resFund = parseFloat(reserve);
	if(resFund > 0.0){
		document.getElementById("reserveFund").setAttribute("readonly","readonly");
		document.getElementById("messageDiv").style.display = "block";
		document.getElementById("submitDiv").style.display = "none";
	}
});
function calculateReserveUnreserve(){
	var available = document.getElementById("unreserveFund").value;
	var reserve = document.getElementById("reserveFund").value;
	if(isNaN(reserve)){
		reserve = 0.0;
		document.getElementById("reserveFund").value = reserve;
	}
	var unreserve = parseFloat(parseFloat(available) - parseFloat(reserve));
	document.getElementById("availableFund").value = unreserve;
}