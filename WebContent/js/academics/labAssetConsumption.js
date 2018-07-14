$(document).ready (function(){
	$(".submitbtn").click(function(){
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var consumedQuantity = $("#consumedQuantity").val();
		var unit = $("#unit").val();
		var currentQuantity = parseInt($("#currentQuantity").val(),10);
			 
	    if ((Date.parse(startDate) >= Date.parse(endDate))) {
	        document.getElementById("endDate").value = "";
	        document.getElementById("warningbox").style.visibility = 'visible';
			document.getElementById("warningmsg").innerHTML = "To Date can not be Before From Date.";
			return false;
	    }
		
		
		if(startDate == ""){
			document.getElementById("warningbox").style.visibility = 'visible';
			document.getElementById("warningmsg").innerHTML = "From Date can not be Empty";
			return false;
		}
		if(endDate == ""){
			document.getElementById("warningbox").style.visibility = 'visible';
			document.getElementById("warningmsg").innerHTML = "To Date can not be Empty";		
			return false;
		}
		if(consumedQuantity == "" || consumedQuantity == 0.0){
			document.getElementById("warningbox").style.visibility = 'visible';
			document.getElementById("warningmsg").innerHTML = "Please Provide a Quantity";		
			return false;
		}
		var consumeQuantity = parseInt(consumedQuantity,10);
		if(consumeQuantity > currentQuantity){
			document.getElementById("warningbox").style.visibility = 'visible';
			document.getElementById("warningmsg").innerHTML = "You can not consume more than the available Quantity";		
			return false;
		}
		if(unit == ""){
			document.getElementById("warningbox").style.visibility = 'visible';
			document.getElementById("warningmsg").innerHTML = "Please Select an Unit";		
			return false;
		}
	});
});


function setZero(box){
	if(box.value=="0.0"){
		box.value="";
	}
}